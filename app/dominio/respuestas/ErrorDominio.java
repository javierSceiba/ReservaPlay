package dominio.respuestas;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.vavr.collection.List;

public class ErrorDominio implements Error {

    private String mensaje;
    private List<String> campos;
    @JsonIgnore
    private Boolean mostrar = true;

    public ErrorDominio(String mensaje, List<String> campos) {
        this.mensaje = mensaje;
        this.campos = campos;
    }

    public ErrorDominio(String mensaje) {
        this.mensaje = mensaje;
    }

    public ErrorDominio(String mensaje, String campo) {
        this.mensaje = mensaje;
        this.campos = List.of(campo);
    }

    public ErrorDominio(String mensaje, List<String> campos, Boolean mostrar) {
        this.mensaje = mensaje;
        this.campos = campos;
        this.mostrar = mostrar;
    }

    @Override
    public String getMensaje() {
        return mensaje;
    }

    public List<String> getCampos() {
        return campos;
    }

    public Boolean getMostrar() {
        return mostrar;
    }
}
