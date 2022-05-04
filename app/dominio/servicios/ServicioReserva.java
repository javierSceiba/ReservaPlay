package dominio.servicios;

import dominio.repositorio.ReservaRepositorio;
import dominio.respuestas.Error;
import io.vavr.concurrent.Future;
import io.vavr.control.Either;
import persistencia.Reserva;

import javax.inject.Inject;

public class ServicioReserva {
    private ReservaRepositorio reservaRepositorio;

    @Inject
    public ServicioReserva(ReservaRepositorio reservaRepositorio){
        this.reservaRepositorio = reservaRepositorio;
    }

    public Future<Either<Error, Long>> insertarReserva(Reserva reserva) {
        return reservaRepositorio.insertar(reserva).map(Either::right);
    }

    public Future<Either<Error, Reserva>> consultarReserva(Long idReserva) {
        return reservaRepositorio.buscar(idReserva).map(Either::right);
    }

    public Future<Either<Error, Long>> actualizarReserva(Reserva reserva, Long idReserva) {
        return reservaRepositorio.actualizar(reserva, idReserva).map(Either::right);
    }

    public Future<Either<Error, Integer>> validarReservaActiva(Reserva reserva) {
        return reservaRepositorio.validarReservaActiva(reserva).map(Either::right);
    }
}
