
package test.atracciones;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Collection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sistema.Clima;
import sistema.Horario;
import sistema.Ubicacion;
import sistema.atraccion.Atraccion;
import sistema.atraccion.AtraccionCultural;
import sistema.usuarios.Empleado;


public class TestEmpleado {
	
	private Empleado empleado;
	
	private String valorString = "";
	private Horario horario = null;
	
	@BeforeEach
	public void setUp() {
		empleado = new Empleado(valorString, valorString, horario, valorString, valorString, valorString);
	}
	

	@Test
	public void testValoresNulos() {
		assertNotNull(empleado.getUsuario());
		assertNotNull(empleado.getContrasenia());
		assertNotNull(empleado.getHorario());
		assertNotNull(empleado.getRoles());
		assertNotNull(empleado.getAtraccionesCapacitado());
		assertNotNull(empleado.getTurno());
	}
	
	@Test
	public void testValoresString() {
	    assertTrue(empleado.getUsuario() instanceof String, "IdEmpleado no es String");
	    assertTrue(empleado.getContrasenia() instanceof String, "Contrasenia no es String");
	    assertTrue(empleado.getRoles() instanceof String, "Roles no es String");
	    assertTrue(empleado.getAtraccionesCapacitado() instanceof String, "AtraccionesCapacitado no es String");
	    assertTrue(empleado.getTurno() instanceof String, "Turno no es String");
	}
	
	
}

