package infraestructura.dto;

import infraestructura.acl.DTO;
import io.vavr.collection.Seq;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.DayOfWeek;
import java.time.LocalDate;
import io.vavr.Value;
import io.vavr.collection.List;
import io.vavr.control.Either;
import io.vavr.control.Validation;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReservaDTO implements DTO {

    private static final String SE_DEBE_INGRESAR_EL_TIPO_DE_USUARIO = "Se debe ingresar el tipo de usuario";
    private static final String SE_DEBE_INGRESAR_EL_NOMBRE_DE_CLIENTE = "Se debe ingresar el nombre del cliente";
    private static final String SE_DEBE_INGRESAR_EL_NUMERO_DE_DOCUMENTO = "Se debe ingresar el numero de documento";
    private static final int NATIVO = 1;
    private static final int UN_DIA = 1;
    private static final int PORCENTAJE_POR_DIA_SABADO = 10;
    private static final int PORCENTAJE_POR_SER_NATIVO = 20;
    private static final int PORCENTAJE_POR_ACTUALIZAR_RESERVA = 20;
    private static final int CIEN = 100;
    private static final Long TARIFA_FIJA = Long.valueOf(200000);

    private Long id;
    private String nombreCliente;
    private Integer tipoUsuario;
    private Integer numeroDocumento;
    private Long costoReserva;
    private LocalDate fechaReserva;

    public ReservaDTO(Long id, String nombreCliente, Integer tipoUsuario, Integer numeroDocumento, LocalDate fechaReserva){
        this.id = id;
        this.nombreCliente = nombreCliente;
        this.tipoUsuario = tipoUsuario;
        this.numeroDocumento = numeroDocumento;
        this.fechaReserva = (fechaReserva == null) ? calcularFechaReserva(LocalDate.now()) : fechaReserva;
        this.costoReserva = (fechaReserva == null) ? calcularCostoReserva(true) : calcularCostoReserva(false) ;
    }


    public ReservaDTO(Long id){
        this.id = id;
    }

    private LocalDate calcularFechaReserva(LocalDate fechaHoy){
        fechaHoy = fechaHoy.plusDays(UN_DIA);
        if(fechaHoy.getDayOfWeek() == DayOfWeek.SUNDAY){
            fechaHoy = fechaHoy.plusDays(UN_DIA);
        }
        return fechaHoy;
    }

    private Long calcularCostoReserva(Boolean crear){
        Long costo = Boolean.TRUE.equals(crear) ? TARIFA_FIJA : (TARIFA_FIJA +  calcularPorcentaje(PORCENTAJE_POR_ACTUALIZAR_RESERVA));
        if(this.fechaReserva.getDayOfWeek() == DayOfWeek.SATURDAY){
            costo =  costo + calcularPorcentaje(PORCENTAJE_POR_DIA_SABADO);
        }
        if(this.tipoUsuario == NATIVO){
            costo =  costo - calcularPorcentaje(PORCENTAJE_POR_SER_NATIVO);
        }
        return costo;
    }

    private Long calcularPorcentaje(Integer porcentaje){
        return ((TARIFA_FIJA * porcentaje) / CIEN);
    }

    public Validation<Seq<String>, ReservaDTO> validar() {
        return Validation.combine(
                        validarId(this.id),
                        validarNombre(this.nombreCliente),
                        validarDocumento(this.numeroDocumento),
                        validarTipo(this.tipoUsuario),
                        validarFecha(this.fechaReserva)
                ).ap(ReservaDTO::new);
    }

    public Validation<Seq<String>, ReservaDTO> validarReserva(ReservaDTO reserva) {
        return Validation.combine(
                validarId(reserva.getId()),
                validarNombre(reserva.getNombreCliente()),
                validarDocumento(reserva.getNumeroDocumento()),
                validarTipo(reserva.getTipoUsuario()),
                validarFecha(reserva.getFechaReserva())
        ).ap(ReservaDTO::new);
    }

    private Validation<String, Long> validarId(Long id) {
        return  Validation.valid(id);
    }

    private Validation<String, String> validarNombre(String nombre) {
        return !StringUtils.isBlank(nombre) ? Validation.valid(nombre) : Validation.invalid("Nombre invalido");
    }


    private Validation<String, Integer> validarDocumento(Integer documento) {
        return (documento != null) ? Validation.valid(documento) : Validation.invalid("Numero de documento invalido");
    }

    private Validation<String, Integer> validarTipo(Integer tipo) {
        return (tipo == 1 || tipo ==2) ? Validation.valid(tipo) : Validation.invalid("Tipo de usuario invalido");

    }

    private Validation<String, Long> validarCostoReserva(Long costo) {
        return (costo != null) ? Validation.valid(costo) : Validation.invalid("Costo de reserva invalido");

    }

    private Validation<String, LocalDate> validarFecha(LocalDate fecha) {
        return Validation.valid(fecha);

    }
}
