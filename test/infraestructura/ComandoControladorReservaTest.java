package infraestructura;

import aplicacion.reserva.comando.ComandoReserva;
import infraestructura.reserva.adaptador.repositorio.ReservaService;
import infraestructura.reserva.controlador.ComandoControladorReserva;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Http;
import play.mvc.Result;
import testDataBuilder.ComandoReservaTestDataBuilder;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.GET;
import static play.test.Helpers.route;

public class ComandoControladorReservaTest {

    public ComandoControladorReserva comandoControladorReserva;

    @Before
    public void iniciarMock() {
        ReservaService postResourceHandler = mock(ReservaService.class);
        HttpExecutionContext httpExecutionContext =  mock(HttpExecutionContext.class);
        comandoControladorReserva = new ComandoControladorReserva(httpExecutionContext,postResourceHandler);
    }

    @Test
    public void deberiaCrearUnUsuario() throws Exception{
        // arrange
        Long id = 1L;
        ComandoReserva reserva = new ComandoReservaTestDataBuilder().build();
        Http.RequestImpl request = new Http.RequestBuilder()
                .method(GET)
                .uri("/reservas"+ id).build();
        Result result = comandoControladorReserva.listar(id).toCompletableFuture().get();
        assertEquals(OK, result.status());
    }
    /*
    @Test
    public void testBadRoute() {
        Http.RequestBuilder request = Helpers.fakeRequest().method(GET).uri("/xx/Kiwi");

        Result result = route(app, request);
        assertEquals(NOT_FOUND, result.status());
    }

    @Test
    void deberiaActualizarUnUsuario() throws Exception{
        // arrange
        Long id = 1L;
        ComandoReserva reserva = new ComandoReservaTestDataBuilder().build();
        // act - assert
        mocMvc.perform(put("/buceo/{id}",id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reserva)))
                .andExpect(status().isOk());
    }

    @Test
    void deberiaNoActualizarUnUsuario() throws Exception{
        // arrange
        Long id = 2L;
        ComandoReserva reserva = new ComandoReservaTestDataBuilder().build();
        // act - assert
        mocMvc.perform(put("/buceo/{id}",id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reserva)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deberiaNoCrearUnUsuario() throws Exception{
        // arrange
        ComandoReserva reserva = new ComandoReservaTestDataBuilder().conTipoUsuario(null).build();
        // act - assert
        mocMvc.perform(post("/buceo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reserva)))
                .andExpect(status().isBadRequest());
    }
*/
}
