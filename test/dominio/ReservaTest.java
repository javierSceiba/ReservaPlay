package dominio;

import dominio.comandos.RegistrarReservaComando;
import infraestructura.dto.ReservaDTO;
import io.vavr.collection.Seq;
import io.vavr.control.Validation;
import org.junit.Test;
import play.Logger;
import testDataBuilder.ReservaTestDataBuilder;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ReservaTest {

    @Test
    public void deberiaCrearCorrectamenteLaReserva() {
        // arrange
        LocalDate fechaReserva = LocalDate.now();
        //act
        ReservaDTO reserva = new ReservaTestDataBuilder().conFechaReserva(fechaReserva).conId(122L).build();
        //assert
        assertEquals(Optional.of(122L), Optional.ofNullable(reserva.getId()));
        assertEquals("Antonio", reserva.getNombreCliente());
        assertEquals(Optional.of(1), Optional.of(reserva.getTipoUsuario()));
        assertEquals(Optional.of(200000L), Optional.of(reserva.getCostoReserva()));
        assertEquals(fechaReserva, reserva.getFechaReserva());
    }

    @Test
    public void deberiaFuncionarValidacion() {
        //Arrange
        ReservaDTO reserva = new ReservaTestDataBuilder().conId(1L).build();
        //act-assert
        Validation<Seq<String>, ReservaDTO> res = reserva.validar();
        assertTrue("El request es valido porque lleva los campos correctamente", res.isValid());
    }

    @Test
    public void deberiaFallarSinNombreDeCliente() {
        //Arrange
        ReservaDTO reserva = new ReservaTestDataBuilder().conNombreCliente(null).conId(1L).build();
        //act-assert
        Validation<Seq<String>, ReservaDTO> res = reserva.validar();
        assertTrue("El request es invalido porque no lleva nombre del cliente", res.isInvalid());
    }


    @Test
    public void deberiaFallarSinNumeroDocumento() {
    //Arrange
        ReservaDTO reserva = new ReservaTestDataBuilder().conNumeroDocumento(null).conId(1L).build();
        //act-assert
        Validation<Seq<String>, ReservaDTO> res = reserva.validar();
        assertTrue("El request es invalido porque no lleva numero de documento del cliente", res.isInvalid());
    }

    @Test
    public void deberiaCalcularCostoNativo() {
        // arrange
        ReservaDTO reserva = new ReservaTestDataBuilder().conTipoUsuario(1).build();
        // act
        Long costoReserva = reserva.getCostoReserva();
        //- assert
        assertEquals(Optional.of(160000L), Optional.of(costoReserva));
    }

    @Test
    public void deberiaCalcularCostoTurista() {
        // arrange
        ReservaDTO reserva = new ReservaTestDataBuilder().conTipoUsuario(2).build();
        // act
        Long costoReserva = reserva.getCostoReserva();
        //- assert
        assertEquals(Optional.of(200000L), Optional.of(costoReserva));
    }

    @Test
    public void deberiaCalcularCostoActualizacionNativo() {
        // arrange
        LocalDate fechaReserva = LocalDate.now();
        ReservaDTO reserva = new ReservaTestDataBuilder().conTipoUsuario(1).conFechaReserva(fechaReserva).build();
        // act
        Long costoReserva = reserva.getCostoReserva();
        //- assert
        assertEquals(Optional.of(200000L), Optional.of(costoReserva));
    }

    @Test
    public void deberiaCalcularCostoActualizacionNativoSabado() {
        // arrange
        LocalDate fechaReserva = LocalDate.of(2022, 02, 19);
        ReservaDTO reserva = new ReservaTestDataBuilder().conTipoUsuario(1).conFechaReserva(fechaReserva).build();
        // act
        Long costoReserva = reserva.getCostoReserva();
        //- assert
        assertEquals(Optional.of(220000L), Optional.of(costoReserva));
    }

    @Test
    public void deberiaCalcularCostoActualizacionTurista() {
        // arrange
        LocalDate fechaReserva = LocalDate.now();
        ReservaDTO reserva = new ReservaTestDataBuilder().conTipoUsuario(2).conFechaReserva(fechaReserva).build();
        // act
        Long costoReserva = reserva.getCostoReserva();
        //- assert
        assertEquals(Optional.of(240000L), Optional.of(costoReserva));
    }

    @Test
    public void deberiaCalcularCostoActualizacionTuristaSabado() {
        // arrange
        LocalDate fechaReserva = LocalDate.of(2022, 02, 19);
        ReservaDTO reserva = new ReservaTestDataBuilder().conTipoUsuario(2).conFechaReserva(fechaReserva).build();
        // act
        Long costoReserva = reserva.getCostoReserva();
        //- assert
        assertEquals(Optional.of(260000L), Optional.of(costoReserva));
    }
}
