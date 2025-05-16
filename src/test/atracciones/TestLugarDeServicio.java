package test.atracciones;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sistema.lugarDeServicio.LugarDeServicio;
import sistema.usuarios.Empleado;

public class TestLugarDeServicio {

	private LugarDeServicio lugarDeServicio;
	
	private String valorString = "";
	private Empleado empleado = null;
	
	
	@BeforeEach
	public void setUp() {
		lugarDeServicio = new LugarDeServicio(valorString, empleado, valorString, empleado);
	}
	
	@Test
	public void testValoresNulos() {
		assertNotNull(lugarDeServicio.getNombreLugar());
		assertNotNull(lugarDeServicio.getCajero());
		assertNotNull(lugarDeServicio.getTipoLugar());
		assertNotNull(lugarDeServicio.getEmpleadoAux());
	}
	
	@Test
	public void testValoresString() {
	    assertTrue(lugarDeServicio.getNombreLugar() instanceof String, "NombreLugar no es String");
	    assertTrue(lugarDeServicio.getTipoLugar() instanceof String, "TipoLugar no es String");
	}
	
}
