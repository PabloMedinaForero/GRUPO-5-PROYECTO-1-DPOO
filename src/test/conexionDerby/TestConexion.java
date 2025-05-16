package test.conexionDerby;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import conexion.ConexionDerby;

public class TestConexion {
	
	private ConexionDerby conexion;
	
	@BeforeEach
	public void setUp() {
		conexion = new ConexionDerby();
	}
	
	@Test
	public void testInsertarEntradasNulas() {
		//funciones de administrador
		conexion.insertarAdministrador(null, null);
		assertEquals(null, conexion.obtenerAdministradores().get(null));
		
		//funciones atracciones
		conexion.insertarAtraccion(null, 0, null, null, null, null, null, null, null, null, 0);
		assertEquals(null, conexion.obtenerAtracciones().get(null));
		conexion.ejecutarUpdateAtraccionTexto(null, null, "Hulk");
		assertEquals("diamante-", conexion.obtenerAtracciones().get("Hulk").getNivelExclusividad());
		conexion.ejecutarUpdateAtraccionInt(null, 0, "Hulk");
		assertEquals(20, conexion.obtenerAtracciones().get("Hulk").getCapacidad());
		
		//funciones empleados
		conexion.insertarEmpleado(null, null, null, null, null);
		assertEquals(null, conexion.obtenerEmpleados().get(null));
		conexion.cambiarCapacitacion("s.gonzales", null);
		assertEquals("Hulk-", conexion.obtenerEmpleados().get("s.gonzales").getAtraccionesCapacitado());
		conexion.cambiarRol("s.gonzales", null);
		assertEquals("cocinero-servicio general-", conexion.obtenerEmpleados().get("s.gonzales").getRoles());
		
		//funciones horarios
		conexion.insertarHorario(null, null, null, null, null, null, null, null);
		assertEquals(null, conexion.obtenerHorarios().get(null));
		conexion.cambiarHorario("j.sanchez", null, null);
		assertEquals(null, conexion.obtenerHorarios().get("j.sanchez"));
		
		//funciones lugares de servicios
		conexion.insertarLugarDeServicio(null, null, null, null);
		assertEquals(null, conexion.obtenerLugaresDeServicio().get(null));
		conexion.ejecutarUpdateLugares(null, null, "SeneCafe");
		assertEquals("cafeteria", conexion.obtenerLugaresDeServicio().get("SeneCafe").getTipoLugar());
		
		//funciones clientes
		conexion.insertarCliente("feid", null);
		assertEquals(null, conexion.obtenerClientes().get("feid"));
		
		//funciones tiquetes
		conexion.insertarTiquete(null, null, null, null, null, null, null, null);
		assertEquals(null, conexion.obtenerTiquetes().get(null));
		conexion.ejecutarUpdateTiqueteString(null, null, "TQ2");
		assertEquals("oro", conexion.obtenerTiquetes().get("TQ2").getTipo());
	}
}
