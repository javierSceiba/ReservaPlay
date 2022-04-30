package dominio.respuestas;

public enum CodigosError {

    SERVICIO_NO_RESPONDE("600"),
    USUARIO_RESTRINGIDO("601"),
    EMPRESA_RESTRINGIDA("602"),
    ERROR_INTERNO("604"),
    YA_EXISTE("500"),
    NO_EXISTE("501"),
    NO_ACTUALIZO("502"),
    NO_ELIMINO("503");

    private String codigo;

    CodigosError(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }
}
