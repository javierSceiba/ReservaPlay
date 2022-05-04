package controladores;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import fabrica.dto.ReservaDTOFabrica;
import infraestructura.dto.ReservaDTO;
import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import play.test.WithApplication;

import static org.junit.Assert.assertEquals;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.*;
import static play.test.Helpers.route;

public class ReservaControladorTest extends WithApplication {
    private Config config;

    @Override
    protected Application provideApplication() {
        config = ConfigFactory.load("application");
        return new GuiceApplicationBuilder().build();
    }

    @Test
    public void consultarReservaPorId() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/reservas/15");

        Result result = route(app, request);
        assertEquals(OK, result.status());
    }

    @Test
    public void registrarReserva() {
        ReservaDTO dto = new ReservaDTOFabrica().get();
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(POST)
                .bodyJson(Json.toJson(dto))
                .uri("/reservas");

        Result result = route(app, request);
        assertEquals(OK, result.status());
    }

    @Test
    public void actualizarReserva() {
        ReservaDTO dto = new ReservaDTOFabrica().get();
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(PUT)
                .bodyJson(Json.toJson(dto))
                .uri("/reservas/15");

        Result result = route(app, request);
        assertEquals(OK, result.status());
    }
}
