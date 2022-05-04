package testDataBuilder;

import infraestructura.dto.ReservaDTO;

import java.time.LocalDate;


public class ReservaTestDataBuilder {
    private Long id;
    private String nombreCliente;
    private Integer tipoUsuario;
    private Integer numeroDocumento;
    private LocalDate fechaReserva;

    public ReservaTestDataBuilder() {
        nombreCliente = "Antonio";
        tipoUsuario = 1;
        numeroDocumento = 1018;
    }

    public ReservaTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public ReservaTestDataBuilder conNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
        return this;
    }


    public ReservaTestDataBuilder conTipoUsuario(Integer tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
        return this;
    }

    public ReservaTestDataBuilder conNumeroDocumento(Integer numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
        return this;
    }

    public ReservaTestDataBuilder conFechaReserva(LocalDate fechaReserva) {
        this.fechaReserva = fechaReserva;
        return this;
    }

    public ReservaDTO build() {
        return new ReservaDTO(id, nombreCliente, tipoUsuario, numeroDocumento, fechaReserva);
    }
}
