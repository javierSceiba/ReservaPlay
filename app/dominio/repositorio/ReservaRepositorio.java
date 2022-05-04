package dominio.repositorio;

import io.vavr.concurrent.Future;
import org.skife.jdbi.v2.DBI;
import persistencia.*;
import play.Logger;
import play.db.Database;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.time.LocalDate;

@Singleton
public class ReservaRepositorio {
    private Database db;
    private final Logger.ALogger logger = Logger.of(this.getClass());

    @Inject
    public ReservaRepositorio(Database db){
        this.db = db;
    }

    public Future<Long> insertar(Reserva reserva) {
        logger.error("insertando nueva reserva: " +  reserva.getId()+", "+
                reserva.getNombreCliente()+", "+
                reserva.getTipoUsuario()+", "+
                reserva.getNumeroDocumento()+", "+
                reserva.getCostoReserva()+", "+
                reserva.getFechaReserva());
        return Future.of(() -> {
                    return new DBI(db.getDataSource()).onDemand(ReservaDAO.class).insertar(
                            reserva.getNombreCliente(),
                            reserva.getTipoUsuario(),
                            reserva.getNumeroDocumento(),
                            reserva.getCostoReserva(),
                            reserva.getFechaReserva());
                }
        );
    }

    public Future<Reserva> buscar(Long idReserva) {
        logger.info("Consultando reserva: " + idReserva);
        return Future.of(() -> {
                    return new DBI(db.getDataSource()).onDemand(ReservaDAO.class).buscar(
                            idReserva
                    );
                }
        );
    }

    public Future<Long> actualizar(Reserva reserva, Long idReserva) {
        logger.info("Actualizando reserva: " + reserva.getId());
        return Future.of(() -> {
                    return new DBI(db.getDataSource()).onDemand(ReservaDAO.class).actualizar(
                            idReserva,
                            reserva.getNombreCliente(),
                            reserva.getTipoUsuario(),
                            reserva.getNumeroDocumento(),
                            reserva.getCostoReserva(),
                            reserva.getFechaReserva()
                    );
                }
        );
    }

    public Future<Integer> validarReservaActiva(Reserva reserva) {
        LocalDate fechaHoy = LocalDate.now();
        logger.error("Validando existencia reserva: " + reserva.getNumeroDocumento()+ ", "+fechaHoy);
        return Future.of(() -> {
                    return new DBI(db.getDataSource()).onDemand(ReservaDAO.class).validarReservaActiva(
                            reserva.getNumeroDocumento(),
                            fechaHoy
                    );
                }
        );
    }
}
