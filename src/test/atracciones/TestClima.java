package test.atracciones;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sistema.Clima;
	public class TestClima {
		
	private Clima clima;
	
	private String valorString = "frio";
	
	@BeforeEach
	public void setUp() {
		clima = new Clima(valorString);
	}
	
	@Test
	public void testValoresNulos() {
		assertNotNull(clima.getTipoClima());
	
	}
	
	@Test
	public void testValoresString() {
	    assertTrue(clima.getTipoClima() instanceof String, "Clima no es String");
	}
	
}

