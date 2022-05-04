package persistencia;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
public class ReservaRecord {
    private Long id;
    private String nombreCliente;
    private Integer tipoUsuario;
    private Integer numeroDocumento;
    private Long costoReserva;
    private LocalDate fechaReserva;

    public Long getId() {
        return id;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public Integer getTipoUsuario() {
        return tipoUsuario;
    }

    public Integer getNumeroDocumento() {
        return numeroDocumento;
    }

    public Long getCostoReserva() {
        return costoReserva;
    }

    public LocalDate getFechaReserva() {
        return fechaReserva;
    }
}
