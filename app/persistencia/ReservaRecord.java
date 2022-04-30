package persistencia;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class ReservaRecord {
    private Long id;
    private String nombreCliente;
    private Integer tipoUsuario;
    private Integer numeroDocumento;
    private Long costoReserva;
    private LocalDate fechaReserva;
}
