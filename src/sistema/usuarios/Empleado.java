package sistema.usuarios;

import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;

import conexion.ConexionDerby;
import sistema.Horario;
import sistema.atraccion.Atraccion;

public class Empleado {

	private String idEmpleado;
	private String contrasenia;
	private Horario horario;
	private String roles;
	private String atraccionesCapacitado;
	private String turno;
	public Empleado(String idEmpleado, String contrasenia, Horario horario, String roles, String atraccionesCapacitado,
			String turno) {
		super();
		this.idEmpleado = idEmpleado;
		this.contrasenia = contrasenia;
		this.horario = horario;
		this.roles = roles;
		this.atraccionesCapacitado = atraccionesCapacitado;
		this.turno = turno;
	}
	
	public void cambiarHorario(Horario horario) {
		this.horario = horario;
	}

	public String getIdEmpleado() {
		return idEmpleado;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public Horario getHorario() {
		return horario;
	}

	public String getRoles() {
		return roles;
	}

	public String getAtraccionesCapacitado() {
		return atraccionesCapacitado;
	}

	public String getTurno() {
		return turno;
	}
	
	
	public void cambiarHorario(HashMap<String, Empleado> empleados, String idEmpleado, String dia, String nuevaActividad) {
		Empleado empleado = empleados.get(idEmpleado);
		ConexionDerby conexion = new ConexionDerby();
		if (empleado != null) {
			empleado.getHorario().cambiarHorario(dia, nuevaActividad);
			conexion.cambiarHorario(idEmpleado, dia, nuevaActividad);
		}
		else {
			System.out.println("No se ha encontrado el usuario dado...");
		}
	}
	
	public void cambiarCapacitacionesMecanicas(HashMap<String, Empleado> empleados, String idEmpleado) {
		Scanner input = new Scanner(System.in);
		Empleado empleado = empleados.get(idEmpleado);
		ConexionDerby conexion = new ConexionDerby();
		System.out.println("Desea aniadir mas capacitaciones o reiniciar el campo?");
		System.out.println("1. Aniadir mas ");
		System.out.println("2. Reiniciar campo");
		System.out.print("Ingrese la opcion ");
		int opcion = input.nextInt();
		input.nextLine();
		if (opcion == 1) {
			String atraccionActuales = empleado.getAtraccionesCapacitado();
			System.out.println("El empleado cuenta con las siguientes capacitaciones: " + atraccionActuales);
			System.out.println("Escriba cada atraccion seguida de -");
			System.out.print("Escriba las capacitaciones a continuacion: ");
			String nuevaCapacitacion = input.nextLine();
			this.atraccionesCapacitado = atraccionActuales + nuevaCapacitacion;
			System.out.println("Las atracciones capacitado son las siguientes: " + empleado.getAtraccionesCapacitado());
			conexion.cambiarCapacitacion(idEmpleado, nuevaCapacitacion);
			input.nextLine();
		}
		else if (opcion == 2) {
			System.out.println("Escriba cada atraccion seguida de -");
			System.out.print("Escriba las capacitaciones a continuacion: ");
			String nuevaCapacitacion = input.nextLine();
			this.atraccionesCapacitado = nuevaCapacitacion;
			System.out.println("Las atracciones capacitado son las siguientes: " + empleado.getAtraccionesCapacitado());
			conexion.cambiarCapacitacion(idEmpleado, nuevaCapacitacion);
			input.nextLine();
		}
	}

	public void cambiarRoles(HashMap<String, Empleado> empleados, String idEmpleado) {
		Scanner input = new Scanner(System.in);
		Empleado empleado = empleados.get(idEmpleado);
		ConexionDerby conexion = new ConexionDerby();
		System.out.println("Desea aniadir mas roles o reiniciar el campo?");
		System.out.println("1. Aniadir mas ");
		System.out.println("2. Reiniciar campo");
		System.out.print("Ingrese la opcion ");
		int opcion = input.nextInt();
		input.nextLine();
		if (opcion == 1) {
			String rolesActuales = empleado.getRoles();
			System.out.println("El empleado cuenta con las siguientes roles: " + rolesActuales);
			System.out.println("Escriba cada rol seguido de -");
			System.out.print("Escriba los roles a continuacion: ");
			String nuevosRoles = input.nextLine();
			this.roles = rolesActuales + nuevosRoles;
			System.out.println("Los roles del empleado son las siguientes: " + empleado.getAtraccionesCapacitado());
			conexion.cambiarRol(idEmpleado, nuevosRoles);
			input.nextLine();
		}
		else if (opcion == 2) {
			System.out.println("Escriba cada rol seguido de -");
			System.out.print("Escriba los roles a continuacion: ");
			String nuevosRoles = input.nextLine();
			this.roles = nuevosRoles;
			System.out.println("Los roles del empleado son las siguientes: " + empleado.getRoles());
			conexion.cambiarCapacitacion(idEmpleado, nuevosRoles);
			input.nextLine();
		}
	}
	
	public static void cambiarUso(String numeroTiquete, String nuevoUso) {
	    ConexionDerby conexion = new ConexionDerby();
	    conexion.ejecutarUpdateTiqueteString("Uso", nuevoUso, numeroTiquete);
	}
}
