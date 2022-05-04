package persistencia;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.time.LocalDate;

@RegisterMapper(ReservaMapper.class)
public interface ReservaDAO {

    @SqlUpdate("INSERT INTO reserva (nombre_cliente, tipo_usuario,numero_documento, costo_reserva, fecha_reserva)" +
            "VALUES (:nombreCliente, :tipoUsuario, :numeroDocumento, :costoReserva, :fechaReserva)")
    @GetGeneratedKeys
    Long insertar(@Bind("nombreCliente") String nombreCliente,
                                     @Bind("tipoUsuario") Integer tipoUsuario,
                                     @Bind("numeroDocumento") Integer numeroDocumento,
                                     @Bind("costoReserva") Long costoReserva,
                                     @Bind("fechaReserva") LocalDate fechaReserva);

    @RegisterMapper(ReservaMapper.class)
    @SqlQuery("SELECT * FROM reserva WHERE id = :idReserva;")
    Reserva buscar(@Bind("idReserva") Long idReserva);

    @SqlUpdate("UPDATE reserva SET " +
            "     nombre_cliente = :nombreCliente," +
            "     tipo_usuario = :tipoUsuario," +
            "     numero_documento = :numDocumento," +
            "     costo_reserva = :costoReserva," +
            "     fecha_reserva = :fechaReserva" +
            " WHERE id  = :idReserva ")
    @GetGeneratedKeys
    Long actualizar(
            @Bind("idReserva") Long idReserva,
            @Bind("nombreCliente") String nombreCliente,
            @Bind("tipoUsuario") Integer tipoUsuario,
            @Bind("numDocumento") Integer numDocumento,
            @Bind("costoReserva") Long costoReserva,
            @Bind("fechaReserva") LocalDate fechaReserva
    );

    @SqlQuery("select count(*) from reserva where numero_documento = :numDocumento and fecha_reserva  >= :fecha;")
    Integer validarReservaActiva(@Bind("numDocumento") Integer numDocumento, @Bind("fecha") LocalDate fecha);
}
