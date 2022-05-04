package dominio.comandos;

import io.vavr.concurrent.Future;

public interface ComandoConsulta {

    Future<Consecuencia> ejecutar(Long idReserva);
}
