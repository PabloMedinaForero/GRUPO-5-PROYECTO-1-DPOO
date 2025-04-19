package sistema.usuarios;

public class Cliente {
	
	private String nombre;
	private String contrasenia;
	public Cliente(String nombre, String contrasenia) {
		super();
		this.nombre = nombre;
		this.contrasenia = contrasenia;
	}
	public String getNombre() {
		return nombre;
	}
	public String getContrasenia() {
		return contrasenia;
	}
}
