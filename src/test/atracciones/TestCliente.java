package test.atracciones;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sistema.Ubicacion;
import sistema.usuarios.Cliente;
public class TestCliente {
	
	private Cliente cliente;
	
	private String valorString = "";
	
	@BeforeEach
	public void setUp() {
		cliente = new Cliente(valorString, valorString);
	}
	
	@Test
	public void testValoresNulos() {
		assertNotNull(cliente.getUsuario());
		assertNotNull(cliente.getContrasenia());
	}
	
	@Test
	public void testValoresString() {
		assertTrue(cliente.getUsuario() instanceof String, "Nombre no es String");
		assertTrue(cliente.getContrasenia() instanceof String, "Nombre no es String");
	}
	
}

