package sistema.atraccion;

import java.util.Collection;

import sistema.Clima;
import sistema.Ubicacion;
import sistema.usuarios.Empleado;

public class AtraccionMecanica extends Atraccion {
	
	private String tipoConstruccion;
	private String nivelRiesgo;
	
	public AtraccionMecanica(String nombreAtraccion, Ubicacion ubicacion, int capacidad, Collection<Empleado> empleados,
			String restricciones, Clima clima, boolean estadoOperacion, String nivelExclusividad, String tipoConstruccion,
			String nivelRiesgo) {
		super(nombreAtraccion, ubicacion, capacidad, empleados, restricciones, clima, estadoOperacion,
				nivelExclusividad);
		this.tipoConstruccion = tipoConstruccion;
		this.nivelRiesgo = nivelRiesgo;
	}

	public String getTipoConstruccion() {
		return tipoConstruccion;
	}

	public String getNivelRiesgo() {
		return nivelRiesgo;
	}
}
