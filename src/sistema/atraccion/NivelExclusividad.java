package sistema.atraccion;

public enum NivelExclusividad {
	ORO("ORO"),
    DIAMANTE("DIAMANTE"),
    FAMILIAR("FAMILIAR");
	private final String descripcion;

    NivelExclusividad(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}

