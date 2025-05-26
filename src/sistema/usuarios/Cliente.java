package sistema.usuarios;

public class Cliente implements Usuario{
	
	private String nombre;
	private String contrasenia;
	public Cliente(String nombre, String contrasenia) {
		super();
		this.nombre = nombre;
		this.contrasenia = contrasenia;
	}
	@Override
	public String getUsuario() {
		return nombre;
	}
	@Override
	public String getContrasenia() {
		return contrasenia;
	}
}
