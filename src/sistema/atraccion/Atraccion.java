package sistema.atraccion;

import java.util.Collection;

import sistema.Clima;
import sistema.Ubicacion;
import sistema.usuarios.Empleado;

public abstract class Atraccion {
	private String nombreAtraccion;
	
	private Ubicacion ubicacion;
	
	private int capacidad;
	
	private Collection<Empleado> empleados;
	
	private String restricciones;
	
	private Clima clima;
	
	private boolean estadoOperacion;
	
	private String nivelExclusividad;

	public Atraccion(String nombreAtraccion, Ubicacion ubicacion, int capacidad, Collection<Empleado> empleados,
			String restricciones, Clima clima, boolean estadoOperacion, String nivelExclusividad) {
		super();
		this.nombreAtraccion = nombreAtraccion;
		this.ubicacion = ubicacion;
		this.capacidad = capacidad;
		this.empleados = empleados;
		this.restricciones = restricciones;
		this.clima = clima;
		this.estadoOperacion = estadoOperacion;
		this.nivelExclusividad = nivelExclusividad;
	}

	
	public boolean getRestriccion(String restriccion) {
		if(restricciones.contains(restriccion)) {
			return true;
		}
		return false;
	}
	
	
	public void cambiarEstadoOperacion(boolean estado) {
		estadoOperacion = estado;
	}


	public String getNombreAtraccion() {
		return nombreAtraccion;
	}


	public Ubicacion getUbicacion() {
		return ubicacion;
	}


	public int getCapacidad() {
		return capacidad;
	}


	public Collection<Empleado> getEmpleados() {
		return empleados;
	}


	public String getRestricciones() {
		return restricciones;
	}


	public Clima getClima() {
		return clima;
	}


	public boolean isEstadoOperacion() {
		return estadoOperacion;
	}


	public String getNivelExclusividad() {
		return nivelExclusividad;
	}
	
	

	
}
