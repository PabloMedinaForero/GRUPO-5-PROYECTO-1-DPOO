package sistema.lugarDeServicio;

import sistema.usuarios.Empleado;

public class LugarDeServicio {
	
	private String nombreLugar;
	private Empleado cajero;
	private String tipoLugar;
	private Empleado empleadoAux;
	public LugarDeServicio(String nombreLugar, Empleado cajero, String tipoLugar, Empleado empleadoAux) {
		super();
		this.nombreLugar = nombreLugar;
		this.cajero = cajero;
		this.tipoLugar = tipoLugar;
		this.empleadoAux = empleadoAux;
	}
	public String getNombreLugar() {
		return nombreLugar;
	}
	public Empleado getCajero() {
		return cajero;
	}
	public String getTipoLugar() {
		return tipoLugar;
	}
	public Empleado getEmpleadoAux() {
		return empleadoAux;
	}
}
