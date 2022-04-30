package persistencia;

public class ReservaDAOAdaptador {
    public static ReservaRecord transformar(Reserva reserva){
        return new ReservaRecord(
                reserva.getId(),
                reserva.getNombreCliente(),
                reserva.getTipoUsuario(),
                reserva.getNumeroDocumento(),
                reserva.getCostoReserva(),
                reserva.getFechaReserva()
        );
    }

    public static Reserva transformar(ReservaRecord record) {
        return new Reserva(
                record.getId(),
                record.getNombreCliente(),
                record.getTipoUsuario(),
                record.getNumeroDocumento(),
                record.getCostoReserva(),
                record.getFechaReserva()
        );
    }
}
