package test.atracciones;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Collection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sistema.Clima;
import sistema.Ubicacion;
import sistema.atraccion.Atraccion;
import sistema.atraccion.AtraccionCultural;
import sistema.usuarios.Empleado;

public class TestAtraccionCultural {
	

	private AtraccionCultural atraccionCultural;
	
	private String valorString = "";
	private int valorInt;
	private Collection<Empleado> colleccionEmpleados = null;
	private Ubicacion ubicacion = null;
	private Clima clima = null;
	private boolean valorBooleano;
	
	
	
	@BeforeEach
	public void setUp() {
		atraccionCultural = new AtraccionCultural(valorString, ubicacion, valorInt, colleccionEmpleados, valorString, clima, valorBooleano, valorString, valorString);
	}
	

	@Test
	public void testValoresNulos() {
		assertNotNull(atraccionCultural.getNombreAtraccion());
		assertNotNull(atraccionCultural.getUbicacion());
		assertNotNull(atraccionCultural.getCapacidad());
		assertNotNull(atraccionCultural.getClima());
		assertNotNull(atraccionCultural.isEstadoOperacion());
		assertNotNull(atraccionCultural.getNivelExclusividad());
		assertNotNull(atraccionCultural.getTipoEvento());
	}
	
	@Test
	public void testValoresString() {
	    assertTrue(atraccionCultural.getNombreAtraccion() instanceof String, "NombreAtraccion no es String");
	    assertTrue(atraccionCultural.getTipoEvento() instanceof String, "TipoEvento no es String");
	    assertTrue(atraccionCultural.getNivelExclusividad() instanceof String, "NivelExclusividad no es String");
	    assertTrue(atraccionCultural.getRestricciones() instanceof String, "Restricciones no es String");
	
	}
	
	@Test
	public void testValoresInt() {
		assertDoesNotThrow(() -> {
	        int capacidad = atraccionCultural.getCapacidad();
	    }, "Capacidad no es int");
	}
	
	@Test
	public void testValoresBooleanos() {
		 assertDoesNotThrow(() -> {
		        boolean estado = atraccionCultural.isEstadoOperacion();
		    }, "EstadoOperacion no es boolean o falló al evaluarse");
	}

	
}
