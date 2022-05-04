package dominio.comandos;

import com.fasterxml.jackson.databind.JsonNode;
import io.vavr.concurrent.Future;

public interface ComandoActualizar {

    Future<Consecuencia> ejecutar(JsonNode json, Long idReserva);
}
