package sistema.usuarios;
import java.util.Collection;
import java.util.HashMap;

import conexion.ConexionDerby;
import sistema.Clima;
import sistema.Horario;
import sistema.Ubicacion;
import sistema.atraccion.Atraccion;
import sistema.atraccion.AtraccionCultural;
import sistema.atraccion.AtraccionMecanica;
import sistema.lugarDeServicio.LugarDeServicio;

public class Administrador implements Usuario{
	
	private String id;
	private String contrasenia;
	public Administrador(String id, String contrasenia) {
		this.id = id;
		this.contrasenia = contrasenia;
	}
	@Override
	public String getUsuario() {
		return id;
	}
	@Override
	public String getContrasenia() {
		return contrasenia;
	}
	
	//Gestion Empleados
	
	public Empleado crearNuevoEmpleado(String idEmpleado, String contrasenia, String lunes, String martes, String miercoles, 
			String jueves, String viernes, String sabado, String domingo, String roles, String atraccionesCapacitado, String turno) {
		ConexionDerby conexion = new ConexionDerby();
		Horario horario = generarNuevoHorario(idEmpleado, lunes, martes, miercoles, jueves, viernes, sabado, domingo);
		Empleado empleado = new Empleado(idEmpleado, contrasenia, horario, roles, atraccionesCapacitado, turno);
		conexion.insertarEmpleado(idEmpleado, contrasenia, roles, atraccionesCapacitado, turno);
		return empleado;
	}
	
	//Gestion horarios
	
	public Horario generarNuevoHorario(String idEmpleado, String lunes, String martes, String miercoles, 
			String jueves, String viernes, String sabado, String domingo) {
		Horario horario = new Horario(idEmpleado, lunes, martes, miercoles, jueves, viernes, sabado, domingo);
		return horario;
	}
	
	public void registrarNuevaAtraccion(HashMap<String,Empleado> mapaEmpleados,HashMap<String,Atraccion> atracciones, String nombreAtraccion, int NumeroUbicacion, String StringClima, String nivelExclusividad, 
			String tipoAtraccion, String restricciones, String nivelRiesgo, String tipoEvento, String empleados, String estadoOperacion, int capacidad) {
		ConexionDerby conexion = new ConexionDerby();
		Ubicacion ubicacion = new Ubicacion(NumeroUbicacion);
		Clima clima = new Clima(StringClima);
		boolean operacion = false;
		if(estadoOperacion.toLowerCase().equals("true")) {
			operacion = true;
		}
		
		String[] arrayEmpleados = empleados.split("-");
		String stringEmpleado = "";
		for(String e : arrayEmpleados) {
			Empleado empleado = mapaEmpleados.get(e);
			if(empleado != null) {
				if(empleado.getAtraccionesCapacitado().contains(nombreAtraccion) == false) {
					System.out.println("El empleado: " + e + " no esta capacitado para esta atraccion... ");
				}
				else
				{
					stringEmpleado += e;
				}
			}
			else {
				System.out.println("El empleado no existe... ");
			}
			
		}
		if(tipoAtraccion.toLowerCase().equals("mecanica")) {
			conexion.insertarAtraccion(nombreAtraccion, NumeroUbicacion, StringClima, nivelExclusividad,
					tipoAtraccion, restricciones, nivelRiesgo, tipoEvento, stringEmpleado, estadoOperacion, capacidad);
		}
		else if(tipoAtraccion.toLowerCase().equals("cultural")) {
			conexion.insertarAtraccion(nombreAtraccion, NumeroUbicacion, StringClima, nivelExclusividad,
					tipoAtraccion, restricciones, nivelRiesgo, tipoEvento, stringEmpleado, estadoOperacion, capacidad);
		}
		else {
			System.out.println("No existe ese tipo de atraccion ");
		}
		
	}

	//gestion atracciones
	
	public static void cambiarUbicacion(String nombreAtraccion, int nuevaUbicacion) {
	    ConexionDerby conexion = new ConexionDerby();
	    conexion.ejecutarUpdateAtraccionInt("Ubicacion", nuevaUbicacion, nombreAtraccion);
	}

	public static void cambiarClima(String nombreAtraccion, String nuevoClima) {
	    ConexionDerby conexion = new ConexionDerby();
	    conexion.ejecutarUpdateAtraccionTexto("Clima", nuevoClima, nombreAtraccion);
	}

	public static void cambiarNivelExclusividad(String nombreAtraccion, String nuevoNivel) {
	    ConexionDerby conexion = new ConexionDerby();
	    conexion.ejecutarUpdateAtraccionTexto("Nivel_Exclusividad", nuevoNivel, nombreAtraccion);
	}

	public static void cambiarTipoAtraccion(String nombreAtraccion, String nuevoTipo) {
	    ConexionDerby conexion = new ConexionDerby();
	    conexion.ejecutarUpdateAtraccionTexto("Tipo_Atraccion", nuevoTipo, nombreAtraccion);
	}

	public static void cambiarRestricciones(String nombreAtraccion, String nuevasRestricciones) {
	    ConexionDerby conexion = new ConexionDerby();
	    conexion.ejecutarUpdateAtraccionTexto("Restricciones", nuevasRestricciones, nombreAtraccion);
	}

	public static void cambiarNivelRiesgo(String nombreAtraccion, String nuevoNivelRiesgo) {
	    ConexionDerby conexion = new ConexionDerby();
	    conexion.ejecutarUpdateAtraccionTexto("Nivel_Riesgo", nuevoNivelRiesgo, nombreAtraccion);
	}

	public static void cambiarTipoEvento(String nombreAtraccion, String nuevoTipoEvento) {
	    ConexionDerby conexion = new ConexionDerby();
	    conexion.ejecutarUpdateAtraccionTexto("Tipo_Evento", nuevoTipoEvento, nombreAtraccion);
	}

	public static void cambiarEmpleados(HashMap<String, Atraccion> atracciones,HashMap<String, Empleado> mapaEmpleados,String nombreAtraccion, String nuevosEmpleados) {
	    ConexionDerby conexion = new ConexionDerby();
	    String[] arrayEmpleados = nuevosEmpleados.split("-");
		String stringEmpleado = "";
		Atraccion atraccion = atracciones.get(nombreAtraccion);
		for(String e : arrayEmpleados) {
			Empleado empleado = mapaEmpleados.get(e);
			if(empleado != null && atraccion != null) {
				if(empleado.getAtraccionesCapacitado().contains(nombreAtraccion) == false) {
					System.out.println("El empleado: " + e + " no esta capacitado para esta atraccion... ");
				}
				else
				{
					stringEmpleado += e + "-";
				}
			}
		}
	    if (stringEmpleado.equals("")) {
	    	cambiarEstadoOperacion(nombreAtraccion, "false");
	    }
	    else {
	    	cambiarEstadoOperacion(nombreAtraccion, "true");
	    }
	    conexion.ejecutarUpdateAtraccionTexto("Empleados", stringEmpleado, nombreAtraccion);

	}

	public static void cambiarEstadoOperacion(String nombreAtraccion, String nuevoEstado) {
	    ConexionDerby conexion = new ConexionDerby();
	    conexion.ejecutarUpdateAtraccionTexto("Estado_Operacion", nuevoEstado, nombreAtraccion);
	}

	public static void cambiarCapacidad(String nombreAtraccion, int nuevaCapacidad) {
	    ConexionDerby conexion = new ConexionDerby();
	    conexion.ejecutarUpdateAtraccionInt("Capacidad", nuevaCapacidad, nombreAtraccion);
	}

	//gestion lugares de servicio
	
	public static void cambiarCajero(String nombreLugar, String nuevoCajero) {
	    ConexionDerby conexion = new ConexionDerby();
	    conexion.ejecutarUpdateLugares("Cajero", nuevoCajero, nombreLugar);
	}

	public static void cambiarEmpleadoAuxiliar(String nombreLugar, String nuevoEmpleadoAuxiliar) {
	    ConexionDerby conexion = new ConexionDerby();
	    conexion.ejecutarUpdateLugares("Empleado_Auxiliar", nuevoEmpleadoAuxiliar, nombreLugar);
	}
	

	
	
	
	
	
	
	
}//final
