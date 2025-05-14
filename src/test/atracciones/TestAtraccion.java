package test.atracciones;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sistema.Clima;
import sistema.Ubicacion;
import sistema.atraccion.Atraccion;
import sistema.usuarios.Empleado;

public class TestAtraccion {

	private Atraccion atraccion;
	
	private String valorString = null;
	private int valorInt;
	private Collection<Empleado> colleccionEmpleados = null;
	private Ubicacion ubicacion = null;
	private Clima clima = null;
	private boolean valorBooleano;
	
	@BeforeEach
	public void setUp() {
		atraccion = new Atraccion(valorString, ubicacion, valorInt, colleccionEmpleados, valorString, clima, valorBooleano, valorString);
	}

	@Test
	public void testCreacionValoresNulos() {
		assertEquals(null, atraccion.getNombreAtraccion()); 
	}
	
	
}
