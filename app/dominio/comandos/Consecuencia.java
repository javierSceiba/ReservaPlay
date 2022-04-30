package dominio.comandos;

import dominio.respuestas.Error;
import infraestructura.acl.DTO;
import io.vavr.control.Either;

public class Consecuencia {
    private final Either<Error, DTO> respuesta;

    public Consecuencia(Either<Error, DTO> respuesta) {
        this.respuesta = respuesta;
    }

    public Either<Error, DTO> getRespuesta() {
        return respuesta;
    }
}