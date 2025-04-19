package sistema.atraccion;

import java.util.Collection;

import sistema.Clima;
import sistema.Ubicacion;
import sistema.usuarios.Empleado;

public class AtraccionCultural extends Atraccion {

	private String tipoEvento;

	public AtraccionCultural(String nombreAtraccion, Ubicacion ubicacion, int capacidad, Collection<Empleado> empleados,
			String restricciones, Clima clima, boolean estadoOperacion, String nivelExclusividad, String tipoEvento) {
		super(nombreAtraccion, ubicacion, capacidad, empleados, restricciones, clima, estadoOperacion,
				nivelExclusividad);
		this.tipoEvento = tipoEvento;
	}

	public String getTipoEvento() {
		return tipoEvento;
	}
		
}