package dominio.comandos;

import com.fasterxml.jackson.databind.JsonNode;
import dominio.respuestas.Error;
import dominio.respuestas.ErrorValidacion;
import dominio.servicios.ServicioReserva;
import infraestructura.dto.ReservaDTO;
import infraestructura.reserva.adaptador.ReservaAdaptador;
import io.vavr.concurrent.Future;
import io.vavr.control.Either;
import persistencia.Reserva;
import play.Logger;

import javax.inject.Inject;

public class ConsultarReservaComando implements Comando{
    private static final Logger.ALogger logger = Logger.of(RegistrarReservaComando.class);
    private ReservaAdaptador reservaAdaptador;
    private ServicioReserva servicioReserva;

    @Inject
    public ConsultarReservaComando(ReservaAdaptador reservaAdaptador, ServicioReserva servicioReserva){
        this.reservaAdaptador = reservaAdaptador;
        this.servicioReserva = servicioReserva;
    }
    @Override
    public Future<Consecuencia> ejecutar(JsonNode json) {
        return null;
    }

    @Override
    public Future<Consecuencia> ejecutar(Long idReserva) {
        logger.error("Sela consulta de una reserva " + idReserva);
        return (Future<Consecuencia>) this.consultarReserva(idReserva);
    }

    private Future<Consecuencia> consultarReserva(Long idReserva){
        return (servicioReserva.consultarReserva(idReserva))
                .map(either -> either.fold(
                        error -> obtenerConsecuenciaFallida(error.getMensaje()),
                        this::obtenerConsecuenciaExitosa)
                );
    }

    private Consecuencia obtenerConsecuenciaFallida(String mensaje) {
        Error error = new ErrorValidacion(mensaje);
        return new Consecuencia(Either.left(error));
    }

    private Consecuencia obtenerConsecuenciaExitosa(Reserva reserva) {
        return new Consecuencia(Either.right(reservaAdaptador.transformar(reserva)));
    }
}
