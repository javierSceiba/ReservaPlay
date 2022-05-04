package controladores;

import com.fasterxml.jackson.databind.JsonNode;
import dominio.comandos.Comando;
import dominio.comandos.ComandoActualizar;
import dominio.comandos.ComandoConsulta;
import dominio.respuestas.CodigosError;
import dominio.respuestas.ErrorDominio;
import dominio.respuestas.ErrorTecnico;
import dominio.respuestas.ErrorValidacion;
import dominio.respuestas.Error;
import infraestructura.acl.DTO;
import io.vavr.Function1;
import io.vavr.control.Either;
import io.vavr.control.Option;
import org.slf4j.MDC;
import play.Logger;
import play.libs.Json;
import play.mvc.Result;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;
import static io.vavr.Predicates.anyOf;
import static io.vavr.Predicates.instanceOf;
import static play.mvc.Results.badRequest;
import static play.mvc.Results.internalServerError;
import static play.mvc.Results.ok;

public interface Controlador {

    Logger.ALogger logger = Logger.of(ReservaControlador.class);

    default CompletionStage<Result> ejecutar(Comando comando, JsonNode json) {
        Map<String, String> contextMap = Option.of(MDC.getCopyOfContextMap()).getOrElse(new HashMap<>());
        return comando.ejecutar(json).map(consecuencia -> {
            MDC.setContextMap(contextMap);
            return consecuencia.getRespuesta().fold(
                    this::obtenerRespuestaConsecuenciaError,
                    this::obtenerRespuestaConsecuenciaExitosa
            );
        }).recover(recuperarEjecucion()).toCompletableFuture();
    }

    default CompletionStage<Result> ejecutar(ComandoConsulta comandoConsulta, Long idReserva) {
        Map<String, String> contextMap = Option.of(MDC.getCopyOfContextMap()).getOrElse(new HashMap<>());
        return comandoConsulta.ejecutar(idReserva).map(consecuencia -> {
            MDC.setContextMap(contextMap);
            return consecuencia.getRespuesta().fold(
                    this::obtenerRespuestaConsecuenciaError,
                    this::obtenerRespuestaConsecuenciaExitosa
            );
        }).recover(recuperarEjecucion()).toCompletableFuture();
    }

    default CompletionStage<Result> ejecutar(ComandoActualizar comandoActualizar, JsonNode json, Long idReserva) {
        Map<String, String> contextMap = Option.of(MDC.getCopyOfContextMap()).getOrElse(new HashMap<>());
        return comandoActualizar.ejecutar(json, idReserva).map(consecuencia -> {
            MDC.setContextMap(contextMap);
            return consecuencia.getRespuesta().fold(
                    this::obtenerRespuestaConsecuenciaError,
                    this::obtenerRespuestaConsecuenciaExitosa
            );
        }).recover(recuperarEjecucion()).toCompletableFuture();
    }

    default Function<Throwable, Result> recuperarEjecucion() {
        return t -> obtenerErrorInterno(t, new ErrorDominio("Error interno", CodigosError.ERROR_INTERNO.getCodigo()));
    }

    default Result obtenerRespuestaConsecuenciaError(Error error) {
        if (error.getMostrar()) logger.error(error.getMensaje());
        else logger.info(error.getMensaje());
        return Match(error).of(
                Case($(instanceOf(ErrorValidacion.class)), e -> badRequest(Json.toJson(e))),
                Case($(), e -> internalServerError(Json.toJson(e))));
    }

    default Result obtenerRespuestaConsecuenciaExitosa(DTO dto) {
        logger.info("Consecuencia exitosa: " + dto.getClass());
        return ok(Json.toJson(dto));
    }

    default CompletionStage<Result> ejecutar(Comando comando, JsonNode json, Function1<Either<Error, DTO>, Result> manejarRespuesta) {
        return comando.ejecutar(json).map(consecuencia -> {
                    return manejarRespuesta.apply(consecuencia.getRespuesta());
                }).recover(throwable -> obtenerErrorInterno(throwable, new ErrorTecnico("Ocurrió un error interno procesando la petición")))
                .toCompletableFuture();
    }

    default Result obtenerErrorInterno(Throwable t, Error error) {
        logger.error("Error interno", t);
        return internalServerError(Json.toJson(error));
    }

    default Result manejarRespuesta(Either<Error, DTO> respuesta) {
        return respuesta.fold(
                error -> {
                    logger.error(Json.toJson(error).toString());
                    return Match(error).of(
                            Case($(anyOf(instanceOf(ErrorValidacion.class), instanceOf(ErrorDominio.class))), e -> badRequest(Json.toJson(e))),
                            Case($(), e -> internalServerError(Json.toJson(e))));
                },
                this::obtenerRespuestaConsecuenciaExitosa
        );
    }

}
