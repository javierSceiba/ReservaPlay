package persistencia;

import java.time.LocalDate;

public class Reserva {
    private Long id;
    private String nombreCliente;
    private Integer tipoUsuario;
    private Integer numeroDocumento;
    private Long costoReserva;
    private LocalDate fechaReserva;

    public Reserva(Long id, String nombreCliente, Integer tipoUsuario, Integer numeroDocumento, Long costoReserva, LocalDate fechaReserva) {
        this.id = id;
        this.nombreCliente = nombreCliente;
        this.tipoUsuario = tipoUsuario;
        this.numeroDocumento = numeroDocumento;
        this.costoReserva = costoReserva;
        this.fechaReserva = fechaReserva;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public Integer getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(Integer tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Integer getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(Integer numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public Long getCostoReserva() {
        return costoReserva;
    }

    public void setCostoReserva(Long costoReserva) {
        this.costoReserva = costoReserva;
    }

    public LocalDate getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(LocalDate fechaReserva) {
        this.fechaReserva = fechaReserva;
    }
}
