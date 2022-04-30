package dominio.repositorio;

import dominio.respuestas.Error;
import infraestructura.dto.ReservaDTO;
import io.vavr.concurrent.Future;
import io.vavr.control.Either;
import io.vavr.control.Option;
import org.skife.jdbi.v2.DBI;
import persistencia.*;
import play.Logger;
import play.db.Database;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ReservaRepositorio {
    private Database db;
    private final Logger.ALogger logger = Logger.of(this.getClass());

    @Inject
    public ReservaRepositorio(Database db){
        this.db = db;
    }

    public Future<Long> insertar(Reserva reserva) {
        ReservaRecord record = ReservaDAOAdaptador.transformar(reserva);
        logger.error("insertando nueva reserva: " +  record.getId()+", "+
                record.getNombreCliente()+", "+
                record.getTipoUsuario()+", "+
                record.getNumeroDocumento()+", "+
                record.getCostoReserva()+", "+
                record.getFechaReserva());
        return Future.of(() -> {
                    return new DBI(db.getDataSource()).onDemand(ReservaDAO.class).insertar(
                            record.getNombreCliente(),
                            record.getTipoUsuario(),
                            record.getNumeroDocumento(),
                            record.getCostoReserva(),
                            record.getFechaReserva());
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

    public Future<Long> actualizar(ReservaDTO reserva) {
        logger.info("Actualizando reserva: " + reserva.getId());
        return Future.of(() -> {
                    return new DBI(db.getDataSource()).onDemand(ReservaDAO.class).actualizar(
                            reserva.getId(),
                            reserva.getNombreCliente(),
                            reserva.getTipoUsuario(),
                            reserva.getNumeroDocumento(),
                            reserva.getCostoReserva(),
                            reserva.getFechaReserva()
                    );
                }
        );
    }
}
