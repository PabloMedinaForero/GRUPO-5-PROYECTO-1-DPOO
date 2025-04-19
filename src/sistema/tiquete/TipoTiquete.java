package sistema.tiquete;
public enum TipoTiquete {
    ORO("ORO"),
    DIAMANTE("DIAMANTE"),
    FAMILIAR("FAMILIAR"),
    PERSONAL("INDIVIDUAL"),
    BASICO("BASICO");

    private final String descripcion;

    TipoTiquete(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
