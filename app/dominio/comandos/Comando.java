package dominio.comandos;
import com.fasterxml.jackson.databind.JsonNode;
import io.vavr.concurrent.Future;

public interface Comando {

    Future<Consecuencia> ejecutar(JsonNode json);
    Future<Consecuencia> ejecutar(Long idReserva);
}