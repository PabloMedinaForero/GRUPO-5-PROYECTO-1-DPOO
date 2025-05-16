package main;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import sistema.usuarios.Cliente;
import sistema.usuarios.Empleado;
import sistema.Horario;
import sistema.atraccion.Atraccion;
import sistema.lugarDeServicio.LugarDeServicio;
import sistema.tiquete.Tiquete;
import sistema.usuarios.Administrador;
import conexion.ConexionDerby;
import java.util.List;
import java.util.Scanner;

public class Main {
	private static Object usuario;
	private static HashMap<String,Atraccion> atracciones;
	private static HashMap<String,Tiquete> tiquetes;
	private static HashMap<String,Horario> horarios;
	private static HashMap<String,Empleado> empleados;
	private static HashMap<String, Cliente> clientes;
	private static HashMap<String, LugarDeServicio> lugaresDeServicio;
	private static HashMap<String, Administrador> administradores;
	
	public static void main(String[] args) {
        new Main(null); 
        printMenu(args);
    }
	public Main(Object usuario) {
		super();
		ConexionDerby conexion = null;
		this.tiquetes = conexion.obtenerTiquetes();
		this.clientes = conexion.obtenerClientes();
		this.lugaresDeServicio = conexion.obtenerLugaresDeServicio();
		this.atracciones = conexion.obtenerAtracciones();
		if (usuario == null) {
			this.usuario = null;
		}
		else {
			this.usuario = usuario;
		}
		
		this.horarios = conexion.obtenerHorarios();
		this.administradores = conexion.obtenerAdministradores();
		this.empleados = conexion.obtenerEmpleados();
		Collection<Empleado> listaEmpleados = empleados.values();
        for (Empleado empleado: listaEmpleados) {
        	Horario horario = horarios.get(empleado.getIdEmpleado());
        	if (horario != null) {
        		empleado.cambiarHorario(horario);
        	} 
        }
	}
	
	public static void printMenu(String[] args) {
		 int opcion = printMainMenu();
	     if (opcion == 1) {
	    	 printMenuAdministrador();
	      } 
	     else if (opcion == 2) {
	         printMenuEmpleado();
	      } 
	     else if (opcion == 3) {
	    	 printMenuCliente();
	      } 
	     else if(opcion == 4) {
	    	 printMenuCompra();
	     }
	     else if(opcion == 5) {
	         System.out.println("Saliendo del sistema...");
	      }
	}
	
	
	
	
	
	
	
	public static int printMainMenu() {
		Scanner inputOpcion = new Scanner(System.in);
		System.out.println("=== Bienvenido al sistema ===");
		System.out.println("Seleccione su rol:");
		System.out.println("1. Administrador");
		System.out.println("2. Empleado");
		System.out.println("3. Cliente");
		System.out.println("4. Sistema de compras");
		System.out.println("5. Cerrar");
		System.out.print("Ingrese el número de su rol: ");
		int opcion = inputOpcion.nextInt();	
	    return opcion;
	}
	
	public static void printMenuAdministrador() {
		boolean terminado = false;
		Scanner inputSesion = new Scanner(System.in);
		Scanner inputOpcion = new Scanner(System.in);
		while (terminado != true) {
			System.out.println("=== Has ingresado como Administrador ===");
			System.out.println("Desea iniciar sesion o crear nueva cuenta: ");
			System.out.println("1. Iniciar sesion ");
			System.out.println("2. Crear cuenta ");
			System.out.println("3. Volver ");
			System.out.print("Ingrese la opcion que desea: ");
			
			int opcion = inputOpcion.nextInt();	
			if (opcion == 1) {
				System.out.println("Ingrese el id: ");
		        String id = inputSesion.nextLine();
		        System.out.println("Ingrese la contrasenia: ");
		        String contrasenia = inputSesion.nextLine();
		        if(administradores.get(id) != null) {
		        	Administrador administrador = administradores.get(id);
		        	if (contrasenia.equals(administrador.getContrasenia())) {
		        		usuario = administrador;
		        		printOpcionesAdministrador();
		        	}
		        }
			} 
			else if(opcion == 2) {
				ConexionDerby conexion = null;
				System.out.println("Ingrese el id: ");
		        String id = inputSesion.nextLine();
		        System.out.println("Ingrese la contrasenia: ");
		        String contrasenia = inputSesion.nextLine();
		        conexion.insertarAdministrador(id,contrasenia);
		        printOpcionesAdministrador();
			}
			else if (opcion == 3) {
				System.out.println("Volviendo al menu principal... ");
				String args[] = null;
				printMenu(args);
				terminado = true;
			}
		}
	}
	
	public static void printOpcionesAdministrador() {
		Scanner inputOpcion = new Scanner(System.in);
		boolean terminado = false;
		while (terminado != true) {
			System.out.println("=== Seleccione las acciones que desea realizar ===");
			System.out.println("1. Opciones de empleados... ");
			System.out.println("2. Opciones de atracciones... ");
			System.out.println("3. Opciones lugares de servicio... ");
			System.out.println("4. Volver ");
			System.out.print("Ingrese la opcion que desea: ");
			
			int opcion = inputOpcion.nextInt();	
			
			if(opcion == 1) {
				printOpcionesAdmistradorEmpleados();
			}
			else if(opcion == 2) {
				printOpcionesAdminstradorAtracciones();
			}
			else if(opcion == 3) {
				printOpcionesAdministradorLugaresDeServicio();
			}
			else if (opcion == 4) {
				terminado = true;
			}
		}
	}
	
	public static void printOpcionesAdmistradorEmpleados() {
		Scanner inputOpcion = new Scanner(System.in);
		boolean terminado = false;
		while (terminado != true) {
			System.out.println("=== Seleccione las acciones que desea realizar ===");
			System.out.println("1. Para crear nuevo empleado ");
			System.out.println("2. Para cambiar horario ");
			System.out.println("3. Cambiar capacitaciones ");
			System.out.println("4. Para cambiar roles  ");
			System.out.println("5. Para salir ");
			System.out.print("Ingrese la opcion que desea: ");
			
			int opcion = inputOpcion.nextInt();
			
			if (opcion == 1) {
				ConexionDerby conexion = new ConexionDerby();
				Scanner InputEmpleado = new Scanner(System.in);
				Scanner InputContrasenia = new Scanner(System.in);
				System.out.print("Ingrese el id del empleado ");
				String IdEmpleado = InputEmpleado.next();
				System.out.print("Ingrese la contrasenia ");
				String contrasenia = InputContrasenia.next();
				Administrador administrador = (Administrador) usuario;
				//Inputs de los dias
				Scanner sc = new Scanner(System.in);

		        System.out.print("Ingrese actividad del lunes: ");
		        String lunes = sc.nextLine();

		        System.out.print("Ingrese actividad del martes: ");
		        String martes = sc.nextLine();

		        System.out.print("Ingrese actividad del miercoles: ");
		        String miercoles = sc.nextLine();

		        System.out.print("Ingrese actividad del jueves: ");
		        String jueves = sc.nextLine();

		        System.out.print("Ingrese actividad del viernes: ");
		        String viernes = sc.nextLine();

		        System.out.print("Ingrese actividad del sabado: ");
		        String sabado = sc.nextLine();

		        System.out.print("Ingrese actividad del domingo: ");
		        String domingo = sc.nextLine();
				
		        System.out.print("Ingrese los roles ");
		        String roles = sc.nextLine();
		        
		        System.out.print("Ingrese las atracciones capacitadas ");
		        String atraccionesCapacitado = sc.nextLine();
		        
		        System.out.print("Indique el turno ");
		        String turno = sc.nextLine();
		        
				Empleado empleado = administrador.crearNuevoEmpleado(IdEmpleado, contrasenia, lunes, martes, miercoles, jueves, viernes, 
						sabado, domingo, roles, atraccionesCapacitado, turno);
				empleados.put(IdEmpleado, empleado);
				new Main(usuario);
				System.out.println("El empleado ha sido aniadido con exito ");
			}
			else if (opcion == 2) {
				Scanner InEmpleado = new Scanner(System.in);
				Scanner InDia = new Scanner(System.in);
				Scanner InActividad = new Scanner(System.in);
				System.out.print("Ingrese el id del empleado ");
				String IdEmpleado = InEmpleado.next();
				System.out.print("Ingrese el dia (primera letra en mayuscula) ");
				String dia = InDia.next();
				System.out.print("Ingrese la nueva actividad ");
				String nuevaActividad = InActividad.nextLine();
				Empleado empleado = empleados.get(IdEmpleado);
				if (empleado != null) {
					empleado.cambiarHorario(empleados, IdEmpleado, dia, nuevaActividad);
				}
			}
			else if (opcion == 3) {
				Scanner InEmpleado = new Scanner(System.in);
				System.out.print("Ingrese el id del empleado ");
				String IdEmpleado = InEmpleado.nextLine();
				Empleado empleado = empleados.get(IdEmpleado);
				if (empleado != null) {
					empleado.cambiarCapacitacionesMecanicas(empleados, IdEmpleado);
				}
			}
			else if (opcion == 4) {
				Scanner InEmpleado = new Scanner(System.in);
				System.out.print("Ingrese el id del empleado ");
				String IdEmpleado = InEmpleado.nextLine();
				Empleado empleado = empleados.get(IdEmpleado);
				if (empleado != null) {
					empleado.cambiarRoles(empleados, IdEmpleado);
				}
			}
			else if (opcion == 5) {
				terminado = true;
				System.out.println("Regresando... ");
			}
		}
	
	}

	public static void printOpcionesAdminstradorAtracciones() {
		Scanner input = new Scanner(System.in);
		boolean terminado = false;
		while (terminado != true) {
			System.out.println("=== Seleccione las acciones que desea realizar ===");
			System.out.println("1. Registrar nueva atraccion ");
			System.out.println("2. Realizar alguna cambio en algun campo en especifico ");
			System.out.println("3. volver ");
			System.out.print("Ingrese su opcion ");
			int opcion = input.nextInt();
			
			if(opcion == 1) {
				System.out.println("=== Seleccione que tipo de atraccion desea crear ===");
				System.out.println("1. Para crear una atraccion mecanica ");
				System.out.println("2. Parar crear una atraccion cultural/evento ");
				System.out.print("Ingrese su opcion ");
				int opcionMecanicaCultural = input.nextInt();
				if(opcionMecanicaCultural == 1) {
					Administrador administrador = (Administrador) usuario;
					Scanner inputValores = new Scanner(System.in);
					inputValores.nextLine();
					
					System.out.print("El nombre de la Atraccion ");
					String nombreAtraccion = inputValores.nextLine();
					inputValores.nextLine();
					
					System.out.print("Ingrese el numero de la ubicacion de la atraccion: ");
					int ubicacion = inputValores.nextInt();
					inputValores.nextLine();
					
					System.out.print("Ingrese los climas donde no puede operar la atraccion: ");
					String clima = inputValores.nextLine();
					inputValores.nextLine();
					
					System.out.print("Ingrese el nivel de exclusividad: ");
					String nivelExclusividad = inputValores.nextLine();
					inputValores.nextLine();
					
					System.out.print("Ingrese las restricciones, cada una seguida de un - (en caso de ser atraccion mecanica es obligatorio poner los minimos de estatura y peso): ");
					String restricciones = inputValores.nextLine();
					inputValores.nextLine();
					
					
					System.out.print("Ingrese el nivel de riesgo (medio o alto):  ");
					String nivelRiesgo = inputValores.nextLine();
					inputValores.nextLine();
			
					
					System.out.print("Ingrese tipo de evento / el tipo de construccion:  ");
					String tipoEvento = inputValores.nextLine();
					inputValores.nextLine();
					
					System.out.print("Ingrese los nombres de los empleados encargados cada uno seguido de -:  ");
					String StringEmpleados = inputValores.nextLine();
					inputValores.nextLine();
					
					System.out.print("ingrese la capacidad de la atraccion: ");
					int capacidad = inputValores.nextInt();
					inputValores.nextLine();
					
					administrador.registrarNuevaAtraccion(empleados,atracciones, nombreAtraccion, ubicacion, clima, nivelExclusividad, "mecanica", restricciones, 
							nivelRiesgo, tipoEvento, StringEmpleados, "true", capacidad);
				}
				else if(opcionMecanicaCultural == 2) {
					Administrador administrador = (Administrador) usuario;
					Scanner inputValores = new Scanner(System.in);
					inputValores.nextLine();
					
					System.out.print("El nombre de la Atraccion ");
					String nombreAtraccion = inputValores.nextLine();
					inputValores.nextLine();
					
					System.out.print("Ingrese el numero de la ubicacion de la atraccion: ");
					int ubicacion = inputValores.nextInt();
					inputValores.nextLine();
					
					System.out.print("Ingrese los climas donde no puede operar la atraccion: ");
					String clima = inputValores.nextLine();
					inputValores.nextLine();
					
					System.out.print("Ingrese el nivel de exclusividad: ");
					String nivelExclusividad = inputValores.nextLine();
					inputValores.nextLine();
					
					System.out.print("Ingrese las restricciones, cada una seguida de un - (en caso de ser atraccion mecanica es obligatorio poner los minimos de estatura y peso): ");
					String restricciones = inputValores.nextLine();
					inputValores.nextLine();
					
					System.out.print("Ingrese tipo de evento / el tipo de construccion:  ");
					String tipoEvento = inputValores.nextLine();
					inputValores.nextLine();
					
					System.out.print("Ingrese los nombres de los empleados encargados cada uno seguido de -:  ");
					String StringEmpleados = inputValores.nextLine();
					inputValores.nextLine();
					
					System.out.print("ingrese la capacidad de la atraccion: ");
					int capacidad = inputValores.nextInt();
					inputValores.nextLine();
					
					administrador.registrarNuevaAtraccion(empleados,atracciones, nombreAtraccion, ubicacion, clima, nivelExclusividad, "cultural", restricciones, 
							"nulo", tipoEvento, StringEmpleados, "true", capacidad);
				}
			new Main(usuario);
			}
			else if(opcion == 2) {
				Scanner inputs = new Scanner(System.in);
				Administrador administrador = (Administrador) usuario;
				System.out.println("Indique en que campo quiere realizar el cambio");
				System.out.println("1. Ubicacion ");
				System.out.println("2. Clima ");
				System.out.println("3. Nivel exclusividad");
				System.out.println("4. Cambiar restricciones ");
				System.out.println("5. Cambiar nivel riesgo ");
				System.out.println("6. Cambiar empleados ");
				System.out.println("7. Cambiar capacidad ");
				System.out.print("Ingrese su opcion: ");
				int opciones = inputs.nextInt();
				inputs.nextLine();
				System.out.print("Ingrese el nombre de la atraccion:");
				String nombreAtraccion = inputs.nextLine();
				Scanner inputsCambios = new Scanner(System.in);
				if (opciones == 1) {
					System.out.print("Ingrese la nueva ubicacion: ");
					int ubicacion = inputsCambios.nextInt();
					administrador.cambiarUbicacion(nombreAtraccion, ubicacion);
					new Main(usuario);
				} 
				else if (opciones == 2) {
					System.out.print("Ingrese el nuevo clima: ");
					String clima = inputsCambios.nextLine();
					administrador.cambiarClima(nombreAtraccion, clima);
					new Main(usuario);
				} 
				else if (opciones == 3) {
					System.out.print("Ingrese el nuevo nivel de exclusividad: ");
					String exclusividad = inputsCambios.nextLine();
					administrador.cambiarNivelExclusividad(nombreAtraccion, exclusividad);
					new Main(usuario);
				} 
				else if (opciones == 4) {
					System.out.print("Ingrese las nuevas restricciones: ");
					String restricciones = inputsCambios.nextLine();
					administrador.cambiarRestricciones(nombreAtraccion, restricciones);
					new Main(usuario);
				} 
				else if (opciones == 5) {
					System.out.print("Ingrese cambiar el nivel de riesgo: ");
					String riesgo = inputsCambios.nextLine();
					administrador.cambiarNivelRiesgo(nombreAtraccion, riesgo);
					new Main(usuario);
				} 
				else if (opciones == 6) {
					System.out.print("Ingrese los nuevos empleados: ");
					String StringEmpleados = inputsCambios.nextLine();
					administrador.cambiarEmpleados(atracciones,empleados, nombreAtraccion, StringEmpleados);
					new Main(usuario);
				} 
				else if (opciones == 7) {
					System.out.print("Ingrese la nueva capacidad: ");
					int empleados = inputsCambios.nextInt();
					administrador.cambiarCapacidad(nombreAtraccion, empleados);
					new Main(usuario);
				}  
				else {
				    System.out.println("Opcion invalida.");
				}
			}
			else if(opcion == 3) {
				terminado = true;
			}
		}
	}

	public static void printOpcionesAdministradorLugaresDeServicio() {
		Scanner input = new Scanner(System.in);
		ConexionDerby conexion = new ConexionDerby();
		boolean terminado = false;
		while (terminado != true) {
			System.out.println("=== Seleccione las acciones que desea realizar ===");
			System.out.println("1. Registrar nuevo lugar de servicio ");
			System.out.println("2. Cambiar cajero ");
			System.out.println("3. Cambiar empleado auxiliar ");
			System.out.println("4. Volver ");
			System.out.print("Ingrese su opcion ");
			int opcion = input.nextInt();
			
			if (opcion == 1) {
				System.out.print("Ingrese el nombre del lugar: ");
				String nombreLugar = input.next();
				System.out.println("Has ingresado: " + nombreLugar);
				
				
				System.out.print("Ingrese el nombre del cajero (empleado): ");
				String cajero = input.nextLine();
				//input.nextLine();
				System.out.println(cajero);///////////////////////
				
				if(empleados.get(cajero) == null) {
					System.out.println("No se ha encontrado a este empleado... ");
					cajero = "";
				}
				
				System.out.print("Ingrese el tipo de lugar (tienda, cafeteria o taquilla): ");
				String tipoLugar = input.nextLine();
				input.nextLine();
				
				
				String empleadoAux;
				if(tipoLugar.contains("taquilla") || tipoLugar.contains("tienda")) {
					empleadoAux = "";
					conexion.insertarLugarDeServicio(nombreLugar, cajero, tipoLugar, empleadoAux);
				}
				else if(tipoLugar.contains("cafeteria")) {
					System.out.print("Ingrese el nombre del cocinero: ");
					String cocinero = input.nextLine();
					input.nextLine();
					Empleado empleado = empleados.get(cocinero);
					if(empleado != null) {
						if(empleado.getRoles().toLowerCase().contains("cociner")) {
							conexion.insertarLugarDeServicio(nombreLugar, cajero, tipoLugar, cocinero);
						}
						else {
							System.out.println("El empleado seleccionado no cuenta con el rol de cocinero... ");
							conexion.insertarLugarDeServicio(nombreLugar, cajero, tipoLugar, "");
						}
					}
				}
				new Main(usuario);
			}
			else if(opcion == 2) {
				Administrador administrador = (Administrador) usuario;
				System.out.print("Ingrese el nombre del lugar: ");
				String nombreLugar = input.nextLine();
				input.nextLine();
				
				System.out.print("Ingrese el nombre del cajero: ");
				String nombreCajero = input.nextLine();
				input.nextLine();
				administrador.cambiarCajero(nombreLugar, nombreCajero);
				new Main(usuario);
			}
			else if(opcion == 3) {
				Administrador administrador = (Administrador) usuario;
				System.out.print("Ingrese el nombre del lugar: ");
				String nombreLugar = input.nextLine();
				input.nextLine();
				
				System.out.print("Ingrese el nombre del empleado auxiliar (cocinero para cafeterias): ");
				String empleadoAux = input.nextLine();
				input.nextLine();
				administrador.cambiarEmpleadoAuxiliar(nombreLugar, empleadoAux);
				new Main(usuario);
			}
			else if(opcion == 4) {
				terminado= true;
			}
			
		}
		
		
	}

	public static void printMenuTaquilla() {
		
		Scanner input = new Scanner(System.in);
		boolean terminado = false;
		while (terminado != true) {
			System.out.println("=== Seleccione las acciones que desea realizar ===");
			System.out.println("1. Comprar tiquetes ");
			System.out.println("2. Volver ");
			System.out.print("Ingrese la opcion que desea: ");
			
			int opcion = input.nextInt();
			
			if(opcion == 1) {
				printMenuCompra();
			}
			else if(opcion == 2) {
				terminado = true;
			}
		}
	}
	
	public static void printMenuEmpleado() {
		Scanner input = new Scanner(System.in);
		boolean terminado = false;
		System.out.print("Ingrese su id: ");
		String idEmpleado = input.nextLine();
		if(empleados.get(idEmpleado) != null) {
			System.out.print("Ingrese su contrasenia: ");
			String contrasenia = input.nextLine();
			if(empleados.get(idEmpleado).getContrasenia().equals(contrasenia)) {
				while (terminado != true) {
					usuario = empleados.get(idEmpleado);
					System.out.println("=== Has ingresado como Empleado ===");
					System.out.println("1. Ingresar a taquilla ");
					System.out.println("2. Ver Atracciones disponibles ");
					System.out.println("3. Validar acceso a atraccion ");
					System.out.println("4. Volver ");
					System.out.print("Ingrese la opcion que desea: ");
				
					int opcion = input.nextInt();
					
					if (opcion == 1) {
						printMenuTaquilla();
					}
					else if(opcion == 2) {
						printAtraccionesDisponibles();
					}
					else if(opcion == 3) {
						Empleado empleado = (Empleado) usuario;
						System.out.print("Ingrese el numero del tiquete");
						String numTiquete = input.nextLine();
						empleado.cambiarUso(numTiquete, "true");
					}
					else if(opcion == 4) {
						terminado = true;
					}
				}
			}
			else {
				System.out.println("La contrasenia ingresada es incorrecta...");
			}
		}
		else {
			System.out.println("No se ha encontrado al usuario digitiado...");
		}
	}
	
	public static void printMenuCompra() {
		Scanner input = new Scanner(System.in);
		boolean terminado = false;
		System.out.println("Realizar compra para: ");
		System.out.println("1. Para Clientes ");
		System.out.println("2. Empleados parque");
		System.out.println("3. Volver");
		System.out.print("Ingrese su opcion: ");
		int opcion = input.nextInt();
		input.nextLine();
		
		while (terminado != true) {
			if(opcion == 1) {
				System.out.print("Ingrese el nombre del cliente ");
				String nombreCliente = input.nextLine();
				if(clientes.get(nombreCliente) != null) {
					System.out.print("Ingrese el tipo de tiquete que desea comprar: ");
					String tipoTiquete = input.nextLine();
					input.nextLine();
					
					boolean temporada = false;
					System.out.println("Tiquete de temporada?: ");
					System.out.println("1. Si ");
					System.out.println("2. No ");
					System.out.print("Ingrese su opcion: ");
					int esDeTemporada = input.nextInt();
					input.nextLine();
					if(esDeTemporada == 1) {
						temporada = true;
					}
					
					String esFastPass = "false";
					System.out.println("Tiquete FastPass?: ");
					System.out.println("1. Si ");
					System.out.println("2. No ");
					System.out.print("Ingrese su opcion: ");
					int fastpass = input.nextInt();
					input.nextLine();
					if(fastpass == 1) {
						esFastPass = "true";
					}
					
					generarCompra(tipoTiquete, temporada, nombreCliente, false, esFastPass);
					System.out.println("Compra realizada con exito...");
					terminado = true;
				}
				else {
					System.out.println("El cliente no existe...");
					terminado = true;
				}
			}
			else if(opcion == 2) {
				System.out.print("Ingrese el nombre del empleado ");
				String nombreCliente = input.nextLine();
				if(empleados.get(nombreCliente) != null|| administradores.get(nombreCliente) != null) {
					System.out.print("Ingrese el tipo de tiquete que desea comprar: ");
					String tipoTiquete = input.nextLine();
					input.nextLine();
					
					boolean temporada = false;
					System.out.println("Tiquete de temporada?: ");
					System.out.println("1. Si ");
					System.out.println("2. No ");
					System.out.print("Ingrese su opcion: ");
					int esDeTemporada = input.nextInt();
					input.nextLine();
					if(esDeTemporada == 1) {
						temporada = true;
					}
					
					String esFastPass = "false";
					System.out.println("Tiquete FastPass?: ");
					System.out.println("1. Si ");
					System.out.println("2. No ");
					System.out.print("Ingrese su opcion: ");
					int fastpass = input.nextInt();
					input.nextLine();
					if(fastpass == 1) {
						esFastPass = "true";
					}
					generarCompra(tipoTiquete, temporada, nombreCliente, true, esFastPass);
					System.out.println("Compra realizada con exito...");
					terminado = true;
				}
				else {
					System.out.println("No se ha encontrado a este empleado...");
				}
				
				
			}
			else if(opcion == 3) {
				terminado = true;
			}
		}
	}

	public static void actualizarAtraccionesValidas(String numeroTiquete, String nuevasAtracciones) {
	    ConexionDerby conexion = new ConexionDerby();
	    conexion.ejecutarUpdateTiqueteString("Atracciones_Validas", nuevasAtracciones, numeroTiquete);
	}	
	
	public static void generarCompra(String tipoTiquete, boolean esDeTemporada, String idComprador, boolean descuento, String esFastPass) {
		ConexionDerby conexion = new ConexionDerby();
		String fechaInicial = "";
		String fechaFinal = "";
		Scanner input = new Scanner(System.in);
		Collection<Atraccion> listaAtracciones = atracciones.values();
        String atraccionesValidas = "";
		
		if(tipoTiquete.contains("individual")) {
			boolean terminado = false;
			while (terminado != true) {
				System.out.print("Ingrese el nombre de la atraccion que desea agregar: ");
				String nombreAtraccion = input.nextLine();
				atraccionesValidas += nombreAtraccion + "-";
				System.out.println("Desea terminar de agreagar atracciones?");
				System.out.println("1. Si");
				System.out.println("2. No");
				System.out.print("Ingrese su opcion ");
				int terminar = input.nextInt();
				if(terminar == 1) {
					terminado = true;
				}
			}
		}
		else if(tipoTiquete.contains("basico")) {
			atraccionesValidas = "";
		}
		else {
			
			for(Atraccion atraccion : listaAtracciones) {
				if(tipoTiquete.contains("diamante")) {
					atraccionesValidas += atraccion.getNombreAtraccion() + "-";
				}
				else if(tipoTiquete.contains("oro")) {
					if(atraccion.getNivelExclusividad().contains("familiar") || atraccion.getNivelExclusividad().contains("oro")) {
						atraccionesValidas += atraccion.getNombreAtraccion() + "-";
					}
				}
				else if(tipoTiquete.contains("familiar")) {
					if(atraccion.getNivelExclusividad().contains("familiar")) {
						atraccionesValidas += atraccion.getNombreAtraccion() + "-";
					}
				}
			}
		}
        String temporada = "";
        if(esDeTemporada) {
        	System.out.print("Ingrese el nombre de la temporada/dia especial: ");
        	temporada = input.nextLine();
        	if(temporada.contains("verano")) {
        		fechaInicial = "01/06/2025";
        		fechaFinal = "01/09/2025";
        	}
        	else if(temporada.contains("invierno")){
        		fechaInicial = "01/09/2025";
        		fechaFinal = "31/12/2025";
        	}
        	else {
        		System.out.println("No existe esa temporada...");
        		LocalDate hoy = LocalDate.now(); 
                DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                fechaInicial = hoy.format(formato);
                fechaFinal = fechaInicial;
        	}
        }
        else {
        	LocalDate hoy = LocalDate.now(); 
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            fechaInicial = hoy.format(formato);
            fechaFinal = fechaInicial;
        }
        String StringDescuento = "false";
        if(descuento) {
        	StringDescuento = "true";
        	System.out.println("Descuento aplicado");
        }
		conexion.insertarTiquete(idComprador, fechaInicial, fechaFinal, temporada, tipoTiquete, StringDescuento, atraccionesValidas, esFastPass);
	}

	public static void printAtraccionesDisponibles() {
		Collection<Atraccion> atraccionesLista = atracciones.values();
		Scanner input = new Scanner(System.in);
		
		System.out.print("Ingrese el clima de hoy: ");
		String clima = input.nextLine();
		System.out.println("Estas son las atracciones disponibles: ");
		for(Atraccion atraccion : atraccionesLista) {
			if(!atraccion.getClima().getTipoClima().contains(clima)) {
				System.out.println("----------------------------------------");
				System.out.println("Nombre de la atraccion: " + atraccion.getNombreAtraccion());
				String[] restricciones = atraccion.getRestricciones().split("-");
				System.out.println("Restricciones:");
				int conteo = 1;
				for(String restriccion: restricciones) {
					System.out.println("   " + String.valueOf(conteo) + ". " + restriccion);
					conteo++;
				}
				System.out.println("Ubicacion:" + String.valueOf(atraccion.getUbicacion().getNumeroPlaza()));
				System.out.println("Capacidad: " + String.valueOf(atraccion.getCapacidad()));
				System.out.println("Nivel Exclusividad:" + atraccion.getNivelExclusividad());
			}
		}
	}

	public static void printMenuCliente() {
		boolean terminado = false;
		Scanner input = new Scanner(System.in);
		while (terminado != true) {
			System.out.println("=== Has ingresado como Cliente ===");
			System.out.println("Desea iniciar sesion o crear nueva cuenta: ");
			System.out.println("1. Iniciar sesion ");
			System.out.println("2. Crear cuenta ");
			System.out.println("3. Volver ");
			System.out.print("Ingrese la opcion que desea: ");
			
			int opcion = input.nextInt();	
			input.nextLine();
			
			if(opcion == 1) {
				System.out.print("Ingrese el nombre de usuario: ");
				String nombreUsuario = input.nextLine();
				if(clientes.get(nombreUsuario) != null) {
					System.out.print("Ingrese la contrasenia: ");
					String contrasenia = input.nextLine();
					if(contrasenia.equals(clientes.get(nombreUsuario).getContrasenia())) {
						Collection<Tiquete> listaTiquetes = tiquetes.values();
						System.out.println("Tienes los siguientes tiquetes: ");
						for(Tiquete tiquete : listaTiquetes) {
							if(tiquete.getIdComprador().equals(nombreUsuario)) {
								System.out.println("--------------------------------");
								System.out.println("Numero tiquete: " + tiquete.getNumeroTiquete());
								System.out.println("Exclusividad: " + tiquete.getTipo());
								if(tiquete.isEsFastPass()) {
									System.out.println("Fast Pass: Si");
								}
								else {
									System.out.println("Fast Pass: No");
								}
								int conteo = 1;
								System.out.println("Atracciones disponibles: ");
								for(Atraccion atraccion : tiquete.getAtraccionesValidas()) {
									System.out.println(String.valueOf(conteo) + ". " + atraccion.getNombreAtraccion());
								}	
							}
						}
					}
				}
				
			}
			else if(opcion == 2) {
				ConexionDerby conexion = new ConexionDerby();
				System.out.print("Ingrese el nombre de usuario que desea: ");
				String nombreUsuario = input.nextLine();
				input.nextLine();
				
				System.out.print("Ingrese la contrasenia: ");
				String contrasenia = input.nextLine();
				input.nextLine();
				conexion.insertarCliente(nombreUsuario, contrasenia);
				new Main(usuario);
			}
			else if(opcion == 3) {
				terminado = true;
			}
			else {
				System.out.println("Opcion no encontrada...");
			}
		}
	}
	
	
}// final
