package controladores;

import dominio.comandos.ActualizarReservaComando;
import dominio.comandos.ComandoActualizar;
import dominio.comandos.ConsultarReservaComando;
import dominio.comandos.RegistrarReservaComando;
import play.Logger;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;


public class ReservaControlador implements Controlador {
    private final Logger.ALogger logger = Logger.of(this.getClass());
    private RegistrarReservaComando reserva;
    private ConsultarReservaComando consultarReserva;
    private ActualizarReservaComando actualizarReservaComando;

    @Inject
    public ReservaControlador(RegistrarReservaComando reserva, ConsultarReservaComando consultarReserva, ActualizarReservaComando actualizarReservaComando) {
        this.reserva = reserva;
        this.consultarReserva = consultarReserva;
        this.actualizarReservaComando = actualizarReservaComando;
    }

    public CompletionStage<Result> crear(Http.Request request) {
        return ejecutar(reserva, request.body().asJson());
    }

    public CompletionStage<Result> consultar(Long idReserva) {
        return ejecutar(consultarReserva, idReserva);
    }

    public CompletionStage<Result> actualizar(Http.Request request, Long idReserva) {
        return ejecutar(actualizarReservaComando, request.body().asJson(), idReserva);
    }

}
