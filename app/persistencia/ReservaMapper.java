package persistencia;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;

public class ReservaMapper  implements ResultSetMapper<Reserva> {
    @Override
    public Reserva map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new Reserva(r.getLong("id"),
                r.getString("nombre_cliente"),
                r.getInt("tipo_usuario"),
                r.getInt("numero_documento"),
                r.getLong("costo_reserva"),
                r.getDate("fecha_reserva").toLocalDate());
    }
}
