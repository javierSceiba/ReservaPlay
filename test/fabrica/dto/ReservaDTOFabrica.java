package fabrica.dto;

import com.github.javafaker.Faker;
import infraestructura.dto.ReservaDTO;

import java.time.LocalDate;

public class ReservaDTOFabrica {
    private static Faker faker = new Faker();
    private Long id;
    private String nombreCliente;
    private Integer tipoUsuario;
    private Integer numeroDocumento;
    private Long costoReserva;
    private LocalDate fechaReserva;

    public ReservaDTOFabrica(){
        this.id = faker.random().nextLong(100);
        this.nombreCliente = faker.name().name();
        this.tipoUsuario = 1;
        this.numeroDocumento = faker.random().nextInt(500);
        this.costoReserva = faker.random().nextLong(200000L);
        this.fechaReserva = LocalDate.now();
    }
    public ReservaDTO get() {
        return new ReservaDTO(this.id,
                this.nombreCliente,
                this.tipoUsuario,
                this.numeroDocumento,
                this.costoReserva,
                this.fechaReserva);
    }
}
