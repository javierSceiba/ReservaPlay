package infraestructura.reserva.adaptador;

import com.fasterxml.jackson.databind.JsonNode;
import infraestructura.dto.ReservaDTO;
import io.vavr.collection.Seq;
import persistencia.Reserva;
import play.Logger;
import io.vavr.collection.List;
import io.vavr.control.Either;
import io.vavr.control.Try;
import play.libs.Json;

import javax.inject.Inject;

public class ReservaAdaptador {
    private ReservaDTO reserva;
    private static final Logger.ALogger logger = Logger.of(ReservaAdaptador.class);

    @Inject
    public ReservaAdaptador(ReservaDTO reserva){
        this.reserva = reserva;
    }

    public Either<Seq<String>, ReservaDTO> transformar(JsonNode json) {
        return validar(json).flatMap(this::validar);
    }

    private Either<Seq<String>, ReservaDTO> validar(JsonNode json) {
        return Try.of(() -> Json.fromJson(json, ReservaDTO.class))
                .toEither()
                .mapLeft(error -> {
                    String err = error.toString();
                    return List.of("Json invalido " + error.toString());

                });
    }
    public Reserva transformar(ReservaDTO dto) {
        return new Reserva(
                dto.getId(),
                dto.getNombreCliente(),
                dto.getTipoUsuario(),
                dto.getNumeroDocumento(),
                dto.getCostoReserva(),
                dto.getFechaReserva());
    }

    public ReservaDTO transformar(Reserva dto) {
        return new ReservaDTO(
                dto.getId(),
                dto.getNombreCliente(),
                dto.getTipoUsuario(),
                dto.getNumeroDocumento(),
                dto.getCostoReserva(),
                dto.getFechaReserva());
    }

    private Either<Seq<String>, ReservaDTO> validar(ReservaDTO co) {
        return reserva.validarReserva(co)
                .toEither();
    }

    public ReservaDTO transformar(Long idReserva) {
        return new ReservaDTO(idReserva);
    }
}
