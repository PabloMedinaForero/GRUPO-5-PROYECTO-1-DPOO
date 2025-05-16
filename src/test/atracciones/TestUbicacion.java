package test.atracciones;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sistema.Clima;
import sistema.Ubicacion;
public class TestUbicacion {
	
	private Ubicacion ubicacion;
	
	private int valorInt;
	
	@BeforeEach
	public void setUp() {
		ubicacion = new Ubicacion(valorInt);
	}
	
	@Test
	public void testValoresNulos() {
		assertNotNull(ubicacion.getNumeroPlaza());
	
	}
	
	@Test
	public void testValoresInt() {
		assertDoesNotThrow(() -> {
	        int capacidad = ubicacion.getNumeroPlaza();
	    }, "Capacidad no es int");
	}
	
}
	
