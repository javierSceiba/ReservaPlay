package dominio.respuestas;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.vavr.collection.List;

public class ErrorValidacion implements Error{

    private String mensaje;
    private List<String> campos;
    @JsonIgnore
    private Boolean mostrar = true;


    public ErrorValidacion(String mensaje, List<String> campos) {
        this.mensaje = mensaje;
        this.campos = campos;
    }

    public ErrorValidacion(String mensaje) {
        this.mensaje = mensaje;
    }


    @Override
    public String getMensaje() {
        return mensaje;
    }

    @Override
    public Boolean getMostrar() {
        return mostrar;
    }

    public List<String> getCampos() {
        return campos;
    }
}
