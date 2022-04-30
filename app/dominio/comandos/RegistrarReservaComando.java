package dominio.comandos;

import com.fasterxml.jackson.databind.JsonNode;
import dominio.respuestas.ErrorValidacion;
import dominio.respuestas.Error;
import dominio.servicios.ServicioReserva;
import infraestructura.dto.ReservaDTO;
import infraestructura.reserva.adaptador.ReservaAdaptador;
import io.vavr.concurrent.Future;
import io.vavr.control.Either;
import play.Logger;

import javax.inject.Inject;

public class RegistrarReservaComando implements Comando{
    private static final Logger.ALogger logger = Logger.of(RegistrarReservaComando.class);
    private ReservaAdaptador reservaAdaptador;
    private ServicioReserva servicioReserva;

    @Inject
    public RegistrarReservaComando(ReservaAdaptador reservaAdaptador, ServicioReserva servicioReserva){
        this.reservaAdaptador = reservaAdaptador;
        this.servicioReserva = servicioReserva;
    }
    @Override
    public Future<Consecuencia> ejecutar(JsonNode json) {
        logger.error("Se inicia el registro de una reserva " + json);
        return (Future<Consecuencia>) reservaAdaptador.transformar(json).fold(
                error -> Future.successful(obtenerConsecuenciaFallida(error.toString())),
                (this::registrarReserva)
        );
    }

    @Override
    public Future<Consecuencia> ejecutar(Long idReserva) {
        return null;
    }

    private Future<Consecuencia> registrarReserva(ReservaDTO reservaDTO){
        return (servicioReserva.insertarReserva(reservaAdaptador.transformar(reservaDTO)))
                .map(either -> either.fold(
                        error -> obtenerConsecuenciaFallida(error.getMensaje()),
                        this::obtenerConsecuenciaExitosa)
                );
    }

    private Consecuencia obtenerConsecuenciaFallida(String mensaje) {
        Error error = new ErrorValidacion(mensaje);
        return new Consecuencia(Either.left(error));
    }

    private Consecuencia obtenerConsecuenciaExitosa(Long idReserva) {
        return new Consecuencia(Either.right(reservaAdaptador.transformar(idReserva)));
    }
}
