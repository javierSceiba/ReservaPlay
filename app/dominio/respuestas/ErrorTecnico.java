package dominio.respuestas;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ErrorTecnico implements Error {

    private String mensaje;
    @JsonIgnore
    private Boolean mostrar = true;

    public ErrorTecnico(String mensaje) {
        this.mensaje = mensaje;
    }

    public ErrorTecnico(String mensaje, Boolean mostrar) {
        this.mensaje = mensaje;
        this.mostrar = mostrar;
    }

    @Override
    public String getMensaje() {
        return mensaje;
    }

    public Boolean getMostrar() {
        return mostrar;
    }
}

