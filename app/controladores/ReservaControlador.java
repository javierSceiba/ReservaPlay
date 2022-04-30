package controladores;

import dominio.comandos.ConsultarReservaComando;
import dominio.comandos.RegistrarReservaComando;
import play.Logger;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

import static play.mvc.Results.internalServerError;

public class ReservaControlador implements  Controlador{
    private final Logger.ALogger logger = Logger.of(this.getClass());
    private RegistrarReservaComando reserva;
    private ConsultarReservaComando consultarReserva;

    @Inject
    public ReservaControlador(RegistrarReservaComando reserva,ConsultarReservaComando consultarReserva){
        this.reserva = reserva;
        this.consultarReserva = consultarReserva;
    }

    public CompletionStage<Result> crear(Http.Request request) {
        return ejecutar(reserva, request.body().asJson());
    }

    public CompletionStage<Result> consultar(Long idReserva) {
        return ejecutar(consultarReserva, idReserva);
    }

}
