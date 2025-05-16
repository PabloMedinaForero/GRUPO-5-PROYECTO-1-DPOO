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
import sistema.atraccion.AtraccionMecanica;
import sistema.usuarios.Empleado;

public class TestAtraccionMecanica {
	

	private AtraccionMecanica atraccionMecanica;
	
	private String valorString = "";
	private int valorInt;
	private Collection<Empleado> colleccionEmpleados = null;
	private Ubicacion ubicacion = null;
	private Clima clima = null;
	private boolean valorBooleano;
	
	
	
	@BeforeEach
	public void setUp() {
		atraccionMecanica = new AtraccionMecanica(valorString, ubicacion, valorInt, colleccionEmpleados, valorString, clima, valorBooleano, valorString, valorString, valorString);
	}
	

	@Test
	public void testValoresNulos() {
		assertNotNull(atraccionMecanica.getNombreAtraccion());
		assertNotNull(atraccionMecanica.getUbicacion());
		assertNotNull(atraccionMecanica.getCapacidad());
		assertNotNull(atraccionMecanica.getClima());
		assertNotNull(atraccionMecanica.isEstadoOperacion());
		assertNotNull(atraccionMecanica.getNivelExclusividad());
		assertNotNull(atraccionMecanica.getTipoConstruccion());
		assertNotNull(atraccionMecanica.getNivelRiesgo());
	}
	
	@Test
	public void testValoresString() {
	    assertTrue(atraccionMecanica.getNombreAtraccion() instanceof String, "NombreAtraccion no es String");
	    assertTrue(atraccionMecanica.getRestricciones() instanceof String, "Restricciones no es String");
	    assertTrue(atraccionMecanica.getNivelExclusividad() instanceof String, "NivelExclusividad no es String");
	    assertTrue(atraccionMecanica.getTipoConstruccion() instanceof String, "TipoConstruccion no es String");
	    assertTrue(atraccionMecanica.getNivelRiesgo() instanceof String, "getNivelRiesgo no es String");
	
	}
	
	@Test
	public void testValoresInt() {
		assertDoesNotThrow(() -> {
	        int capacidad = atraccionMecanica.getCapacidad();
	    }, "Capacidad no es int");
	}
	
	@Test
	public void testValoresBooleanos() {
		 assertDoesNotThrow(() -> {
		        boolean estado = atraccionMecanica.isEstadoOperacion();
		    }, "EstadoOperacion no es boolean o falló al evaluarse");
	}

	
}

