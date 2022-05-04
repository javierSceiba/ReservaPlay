package dominio;

import dominio.repositorio.ReservaRepositorio;
import org.junit.Before;
import org.junit.Test;
import persistencia.BaseDeDatos;
import persistencia.Reserva;

import java.time.LocalDate;

import static junit.framework.TestCase.assertEquals;

public class ReservaRepositorioTest extends BaseDeDatos {

    private ReservaRepositorio reservaRepositorio;

    @Before
    public void iniciarDatos() {
        reservaRepositorio = new ReservaRepositorio(getDb());
    }

    @Test
    public void insertarReservaExitosa(){
        LocalDate fechaHoy = LocalDate.now();
        Reserva reserva = new Reserva(17L,"Daniel", 1, 89748, 220000L, fechaHoy);
        Long idReserva = insertarReserva(reserva);
        assertEquals("17", String.valueOf(idReserva));
    }

    private Long insertarReserva(Reserva reserva){
        return reservaRepositorio.insertar(reserva).get();
    }

    @Test
    public void consultarReservaExitosa(){
        Reserva reserva = consultarReserva();
        assertEquals("Pedro",reserva.getNombreCliente());
        assertEquals("1",String.valueOf(reserva.getTipoUsuario()));
    }

    private Reserva consultarReserva(){
        return reservaRepositorio.buscar(15L).get();
    }

    @Test
    public void actualizarReservaExitosa(){
        LocalDate fechaHoy = LocalDate.now();
        Reserva reserva = new Reserva(16L,"Jonas", 2, 1589, 200000L, fechaHoy);
        Long idReserva = actualizarReserva(reserva, 16L);
        assertEquals(null, idReserva);
    }

    private Long actualizarReserva(Reserva reserva, Long idReserva){
        return reservaRepositorio.actualizar(reserva, idReserva).get();
    }
}
