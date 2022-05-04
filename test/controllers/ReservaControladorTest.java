package controllers;

import dominio.repositorio.ReservaRepositorio;
import dominio.respuestas.Error;
import dominio.servicios.ServicioReserva;
import infraestructura.dto.ReservaDTO;
import io.vavr.concurrent.Future;
import io.vavr.control.Either;
import org.junit.Before;
import org.junit.Test;
import persistencia.Reserva;
import testDataBuilder.ReservaTestDataBuilder;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReservaControladorTest {

    private ReservaRepositorio reservaRepositorioMock;
    private ServicioReserva servicioReservaMock;
    private Reserva reserva;

    @Before
    public void iniciarDatos() {
        reservaRepositorioMock = mock(ReservaRepositorio.class);
        servicioReservaMock = new ServicioReserva(reservaRepositorioMock);
        reserva = mock(Reserva.class);
    }

    @Test
    public void insertarReserva(){
       // when(servicioReservaMock.insertarReserva(reserva)).thenReturn(Future.successful());
        Future<Either<Error, Long>> resp = servicioReservaMock.insertarReserva(reserva);
        //assertTrue("El servicioAhorrador deberia retornar un Either.left", resp.ri);
    }

}
