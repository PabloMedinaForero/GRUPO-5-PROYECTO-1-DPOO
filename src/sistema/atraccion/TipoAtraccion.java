package sistema.atraccion;

public enum TipoAtraccion {
	MECANICA("MECANICA"),
	CULTURAL("CULTURAL");
	private final String descripcion;
	
	TipoAtraccion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
