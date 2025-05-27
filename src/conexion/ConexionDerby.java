package conexion;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import sistema.Clima;
import sistema.Horario;
import sistema.Ubicacion;
import sistema.atraccion.Atraccion;
import sistema.atraccion.AtraccionCultural;
import sistema.atraccion.AtraccionMecanica;
import sistema.lugarDeServicio.LugarDeServicio;
import sistema.tiquete.Tiquete;
import sistema.usuarios.Administrador;
import sistema.usuarios.Cliente;
import sistema.usuarios.Empleado;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ConexionDerby {
	
	public static void main(String[] args) {
		
    }
	
	public static Connection conectar() {
        try {
            String jdbcURL = "jdbc:derby:informacion;create=true";
            return DriverManager.getConnection(jdbcURL);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
	
	public static void cerrarConexion(Connection connection) {
	        try {
	            if (connection != null && !connection.isClosed()) {
	                connection.close();
	                System.out.println("Conexión cerrada exitosamente.");
	            }
	        } catch (SQLException e) {
	            System.out.println("Error al cerrar la conexión.");
	            e.printStackTrace();
	        }
	    }

	//Administradores
	
	public static void insertarAdministrador(String id, String contrasenia) {
		
		if(id == null | contrasenia == null) {
			System.out.println("Se detectaron valores nulos en algun campo ");
		}
		else {
	        Connection connection = conectar();
	
	        if (connection == null) {
	            System.out.println("No se pudo establecer la conexión con la base de datos.");
	            return;
	        }
	
	        try {
	            String sql = "INSERT INTO administradores (id, contrasenia) VALUES (?, ?)";
	            PreparedStatement preparedStatement = connection.prepareStatement(sql);
	            preparedStatement.setString(1, id);
	            preparedStatement.setString(2, contrasenia);
	
	            int rows = preparedStatement.executeUpdate();
	            if (rows > 0) {
	                System.out.println("Información agregada correctamente.");
	            } else {
	                System.out.println("No se pudo agregar la información.");
	            }
	
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            cerrarConexion(connection);
	        }
		}
    }

	public static HashMap<String, Administrador> obtenerAdministradores() {
	        HashMap<String, Administrador> mapaAdministradores = new HashMap<>();
	        Connection connection = conectar();

	        if (connection == null) {
	            System.out.println("No se pudo establecer la conexión con la base de datos.");
	            return mapaAdministradores;
	        }

	        try {
	            String consulta = "SELECT * FROM administradores";
	            PreparedStatement preparedStatement = connection.prepareStatement(consulta);
	            ResultSet resultSet = preparedStatement.executeQuery();

	            while (resultSet.next()) {
	                String id = resultSet.getString("id");
	                String contrasenia = resultSet.getString("contrasenia");
	                Administrador administrador = new Administrador(id, contrasenia);
	                mapaAdministradores.put(id, administrador);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            cerrarConexion(connection);
	        }

	        return mapaAdministradores;
	    }
	
	//Atracciones
	
	public static void crearTablaAtraccion() {
	    Connection connection = conectar();

	    if (connection == null) {
	        System.out.println("No se pudo conectar a la base de datos.");
	        return;
	    }

	    try {
	        String sql = "CREATE TABLE Atraccion ("
	                + "Nombre_Atraccion VARCHAR(100), "
	                + "Ubicacion INT, "
	                + "Clima VARCHAR(50), "
	                + "Nivel_Exclusividad VARCHAR(50), "
	                + "Tipo_Atraccion VARCHAR(50), "
	                + "Restricciones VARCHAR(255), "
	                + "Nivel_Riesgo VARCHAR(50), "
	                + "Tipo_Evento VARCHAR(50), "
	                + "Empleados VARCHAR(100), "
	                + "Estado_Operacion VARCHAR(50), "
	                + "Capacidad INT"
	                + ")";
	        
	        PreparedStatement statement = connection.prepareStatement(sql);
	        statement.execute();
	        System.out.println("Tabla 'Atraccion' creada correctamente.");
	        
	    } catch (SQLException e) {
	        if (e.getSQLState().equals("X0Y32")) {
	            System.out.println("La tabla 'Atraccion' ya existe.");
	        } else {
	            e.printStackTrace();
	        }
	    } finally {
	        cerrarConexion(connection);
	    }
	}

	public static void insertarAtraccion(String nombreAtraccion, int ubicacion, String clima, String nivelExclusividad, String tipoAtraccion,
	        String restricciones, String nivelRiesgo, String tipoEvento, String empleados, String estadoOperacion, int capacidad) {
			
		if (nombreAtraccion == null | ubicacion == 0 | clima == null | nivelExclusividad == null | tipoAtraccion == null | restricciones == null | nivelRiesgo == null |tipoEvento == null | empleados == null |estadoOperacion == null | capacidad == 0) {
			System.out.println("Hay valores nulos en los ingresados ");
		}
		else {
	        Connection connection = ConexionDerby.conectar();

	        if (connection == null) {
	            System.out.println("No se pudo conectar a la base de datos.");
	            return;
	        }

	        try {
	            String sql = "INSERT INTO Atraccion ("
	                    + "Nombre_Atraccion, Ubicacion, Clima, Nivel_Exclusividad, Tipo_Atraccion, "
	                    + "Restricciones, Nivel_Riesgo, Tipo_Evento, Empleados, Estado_Operacion, Capacidad"
	                    + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	            PreparedStatement statement = connection.prepareStatement(sql);
	            statement.setString(1, nombreAtraccion);
	            statement.setInt(2, ubicacion);
	            statement.setString(3, clima);
	            statement.setString(4, nivelExclusividad);
	            statement.setString(5, tipoAtraccion);
	            statement.setString(6, restricciones);
	            statement.setString(7, nivelRiesgo);
	            statement.setString(8, tipoEvento);
	            statement.setString(9, empleados);
	            statement.setString(10, estadoOperacion);
	            statement.setInt(11, capacidad);

	            int filasInsertadas = statement.executeUpdate();

	            if (filasInsertadas > 0) {
	                System.out.println("Atracción insertada correctamente.");
	            } else {
	                System.out.println("No se pudo insertar la atracción.");
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            ConexionDerby.cerrarConexion(connection);
	        }
		}
	}
	
	public static HashMap<String, Atraccion> obtenerAtracciones() {
	    HashMap<String, Atraccion> atracciones = new HashMap<>();

	    Connection connection = ConexionDerby.conectar();

	    if (connection == null) {
	        System.out.println("No se pudo conectar a la base de datos.");
	        return atracciones;
	    }

	    try {
	        String consulta = "SELECT * FROM Atraccion";
	        PreparedStatement statement = connection.prepareStatement(consulta);
	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            String nombre = resultSet.getString("Nombre_Atraccion");
	            int ubicacion = resultSet.getInt("Ubicacion");
	            String clima = resultSet.getString("Clima");
	            String exclusividad = resultSet.getString("Nivel_Exclusividad");
	            String tipo = resultSet.getString("Tipo_Atraccion");
	            String restricciones = resultSet.getString("Restricciones");
	            String riesgo = resultSet.getString("Nivel_Riesgo");
	            String evento = resultSet.getString("Tipo_Evento");
	            String empleados = resultSet.getString("Empleados");
	            String estado = resultSet.getString("Estado_Operacion");
	            int capacidad = resultSet.getInt("Capacidad");
	            
	            boolean operacion = false;
	    		if(estado.toLowerCase().equals("true")) {
	    			operacion = true;
	    		}
	            if(empleados.equals("")) {
	            	operacion = false;
	            }
	    		Ubicacion ubicacionFinal = new Ubicacion(ubicacion);
	    		Clima climaFinla = new Clima(clima);
	    		
	    		String[] arrayEmpleados = empleados.split("-");
	    		
	    		HashMap<String, Empleado> mapaEmpleados = obtenerEmpleados();
	    		
	    		Collection<Empleado> collectionEmpleados = new ArrayList<>();;
	    		
	    		for(String e : arrayEmpleados) {
	    			collectionEmpleados.add(mapaEmpleados.get(e));
	    		}
	    		
	            if (tipo.toLowerCase().contains("mecanica")) {
	            	AtraccionMecanica atraccion = new AtraccionMecanica(nombre, ubicacionFinal, capacidad, collectionEmpleados, restricciones,
	            			climaFinla, operacion, exclusividad, evento, riesgo);
	            	atracciones.put(nombre, atraccion);
	            }
	            if (tipo.toLowerCase().contains("cultural")) {
	            	AtraccionCultural atraccion = new AtraccionCultural(nombre, ubicacionFinal, capacidad, collectionEmpleados, restricciones,
	            			climaFinla, operacion, exclusividad, evento);
	            	atracciones.put(nombre, atraccion);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        ConexionDerby.cerrarConexion(connection);
	    }
	    return atracciones;
	}

	public static void ejecutarUpdateAtraccionTexto(String campo, String nuevoValor, String nombreAtraccion) {
		
		if(campo == null | nuevoValor == null | nombreAtraccion == null) {
			System.out.println("Hay valores nulos en los parametros ");
		}
		else {
		    Connection connection = conectar();
		    if (connection == null) {
		        System.out.println("No se pudo conectar a la base de datos.");
		        return;
		    }
	
		    String sql = "UPDATE Atraccion SET " + campo + " = ? WHERE Nombre_Atraccion = ?";
		    try {
		        PreparedStatement statement = connection.prepareStatement(sql);
		        statement.setString(1, nuevoValor);
		        statement.setString(2, nombreAtraccion);
		        int filas = statement.executeUpdate();
		        if (filas > 0) {
		            System.out.println("Campo " + campo + " actualizado correctamente.");
		        } else {
		            System.out.println("Atracción no encontrada: " + nombreAtraccion);
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        cerrarConexion(connection);
		    }
		}
	}

	public static void ejecutarUpdateAtraccionInt(String campo, int nuevoValor, String nombreAtraccion) {
		
		if(campo == null | nuevoValor == 0 | nombreAtraccion == null) {
			System.out.println("Hay valores nulos en los parametros ");
		}
		else {
		    Connection connection = conectar();
		    if (connection == null) {
		        System.out.println("No se pudo conectar a la base de datos.");
		        return;
		    }
	
		    String sql = "UPDATE Atraccion SET " + campo + " = ? WHERE Nombre_Atraccion = ?";
		    try {
		        PreparedStatement statement = connection.prepareStatement(sql);
		        statement.setInt(1, nuevoValor);
		        statement.setString(2, nombreAtraccion);
		        int filas = statement.executeUpdate();
		        if (filas > 0) {
		            System.out.println("Campo " + campo + " actualizado correctamente.");
		        } else {
		            System.out.println("Atracción no encontrada: " + nombreAtraccion);
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        cerrarConexion(connection);
		    }
		}
	}

	
	//Empleados
	
	public static void crearTablaEmpleados() {
		Connection connection = conectar();

	    if (connection == null) {
	        System.out.println("No se pudo conectar a la base de datos.");
	        return;
	    }

        try {
            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE Empleados (" +
                         "Id_Empleado VARCHAR(50) PRIMARY KEY, " +
                         "Contrasenia VARCHAR(100), " +
                         "Roles VARCHAR(50), " +
                         "Capacitacion_Mecanicas VARCHAR(100), " +
                         "Turno VARCHAR(50))";
            statement.executeUpdate(sql);
            System.out.println("Tabla Empleados creada correctamente.");
        } catch (SQLException e) {
	        if (e.getSQLState().equals("X0Y32")) {
	            System.out.println("La tabla 'Empleados' ya existe.");
	        } else {
	            e.printStackTrace();
	        }
	    } finally {
	        cerrarConexion(connection);
	    }
    }

	public static void insertarEmpleado(String idEmpleado, String contrasenia, String roles, String capacitacionMecanicas, String turno) {
		
		if(idEmpleado == null | contrasenia == null | roles == null | capacitacionMecanicas ==  null | turno == null) {
			System.out.println("Hay valores nulos en los ingresados ");
		}
		else {
		    Connection connection = conectar(); 
	
		    if (connection == null) {
		        System.out.println("No se pudo conectar a la base de datos.");
		        return;
		    }
	
		    try {
		        String sql = "INSERT INTO Empleados (Id_Empleado, Contrasenia, Roles, Capacitacion_Mecanicas, Turno) VALUES (?, ?, ?, ?, ?)";
		        PreparedStatement preparedStatement = connection.prepareStatement(sql);
		        
		        preparedStatement.setString(1, idEmpleado);
		        preparedStatement.setString(2, contrasenia);
		        preparedStatement.setString(3, roles);
		        preparedStatement.setString(4, capacitacionMecanicas);
		        preparedStatement.setString(5, turno);
	
		        int rows = preparedStatement.executeUpdate(); 
	
		        if (rows > 0) {
		            System.out.println("Empleado agregado correctamente.");
		        } else {
		            System.out.println("No se pudo agregar el empleado.");
		        }
	
		    } catch (SQLException e) {
		        System.out.println("Error al agregar empleado: " + e.getMessage());
		        e.printStackTrace();
		    } finally {
		        cerrarConexion(connection); 
		    }
		}
	}

	public static HashMap<String, Empleado> obtenerEmpleados() {
	    HashMap<String, Empleado> mapaEmpleados = new HashMap<>();
	    Connection connection = conectar(); 

	    if (connection == null) {
	        System.out.println("No se pudo conectar a la base de datos.");
	        return mapaEmpleados; 
	    }

	    try {
	        String sql = "SELECT * FROM Empleados"; 
	        Statement statement = connection.createStatement();
	        ResultSet resultSet = statement.executeQuery(sql);

	        while (resultSet.next()) {
	            String idEmpleado = resultSet.getString("Id_Empleado");
	            String contrasenia = resultSet.getString("Contrasenia");
	            String roles = resultSet.getString("Roles");
	            String capacitacionMecanicas = resultSet.getString("Capacitacion_Mecanicas");
	            String turno = resultSet.getString("Turno");
	            Horario horario = new Horario(idEmpleado,"libre","libre","libre","libre","libre","libre","libre");
	            Empleado empleado = new Empleado(idEmpleado, contrasenia, horario , roles, capacitacionMecanicas, turno);
	            mapaEmpleados.put(idEmpleado, empleado);
	        }

	    } catch (SQLException e) {
	        System.out.println("Error al obtener empleados: " + e.getMessage());
	        e.printStackTrace();
	    } finally {
	        cerrarConexion(connection); 
	    }

	    return mapaEmpleados; 
	}
	
	public static void cambiarCapacitacion(String idEmpleado, String nuevaCapacitacion) {
		
		if(idEmpleado == null | nuevaCapacitacion ==  null) {
			System.out.println("Hay valores nulos en los ingresados ");
		}
		else {
		
		    Connection connection = conectar();
	
		    if (connection == null) {
		        System.out.println("No se pudo conectar a la base de datos.");
		        return;
		    }
	
		    String sql = "UPDATE Empleados SET Capacitacion_Mecanicas = ? WHERE Id_Empleado = ?";
	
		    try {
		        PreparedStatement statement = connection.prepareStatement(sql);
		        statement.setString(1, nuevaCapacitacion);
		        statement.setString(2, idEmpleado);
	
		        int filas = statement.executeUpdate();
		        if (filas > 0) {
		            System.out.println("Capacitación actualizada correctamente para el empleado con ID: " + idEmpleado);
		        } else {
		            System.out.println("No se encontró el empleado con ID: " + idEmpleado);
		        }
	
		    } catch (SQLException e) {
		        System.out.println("Error al cambiar la capacitación: " + e.getMessage());
		        e.printStackTrace();
		    } finally {
		        cerrarConexion(connection);
		    }
		}
	}

	public static void cambiarRol(String idEmpleado, String nuevoRol) {
		
		if(idEmpleado == null | nuevoRol ==  null) {
			System.out.println("Hay valores nulos en los ingresados ");
		}
		else {
		
		    Connection connection = conectar();
	
		    if (connection == null) {
		        System.out.println("No se pudo conectar a la base de datos.");
		        return;
		    }
	
		    String sql = "UPDATE Empleados SET Roles = ? WHERE Id_Empleado = ?";
	
		    try {
		        PreparedStatement statement = connection.prepareStatement(sql);
		        statement.setString(1, nuevoRol);
		        statement.setString(2, idEmpleado);
	
		        int filas = statement.executeUpdate();
		        if (filas > 0) {
		            System.out.println("Rol actualizado correctamente para el empleado con ID: " + idEmpleado);
		        } else {
		            System.out.println("No se encontró el empleado con ID: " + idEmpleado);
		        }
	
		    } catch (SQLException e) {
		        System.out.println("Error al cambiar el rol: " + e.getMessage());
		        e.printStackTrace();
		    } finally {
		        cerrarConexion(connection);
		    }
		}
	}

	//Horarios
	
	public static void crearTablaHorarios() {
	    Connection connection = conectar();

	    if (connection == null) {
	        System.out.println("No se pudo conectar a la base de datos.");
	        return;
	    }

	    try {
	        Statement statement = connection.createStatement();

	        String sql = "CREATE TABLE Horarios (" +
	                     "Id_Empleado VARCHAR(50) PRIMARY KEY, " +
	                     "Lunes VARCHAR(100), " +
	                     "Martes VARCHAR(100), " +
	                     "Miercoles VARCHAR(100), " +
	                     "Jueves VARCHAR(100), " +
	                     "Viernes VARCHAR(100), " +
	                     "Sabado VARCHAR(100), " +
	                     "Domingo VARCHAR(100), " +
	                     "FOREIGN KEY (Id_Empleado) REFERENCES Empleados(Id_Empleado))";

	        statement.executeUpdate(sql);
	        System.out.println("Tabla Horarios creada correctamente.");
	    } catch (SQLException e) {
	        if (e.getSQLState().equals("X0Y32")) {
	            System.out.println("La tabla 'Horarios' ya existe.");
	        } else {
	            System.out.println("Error al crear la tabla 'Horarios': " + e.getMessage());
	            e.printStackTrace();
	        }
	    } finally {
	        cerrarConexion(connection);
	    }
	}

	public static void insertarHorario(String idEmpleado, String lunes, String martes, String miercoles, 
            String jueves, String viernes, String sabado, String domingo) {
		
		if(idEmpleado == null | lunes == null | martes == null | miercoles == null | jueves  == null | viernes == null | sabado == null | domingo == null) {
			System.out.println("Hay valores nulos en los ingresados ");
		}
		else {
			Connection connection = conectar();
			
			if (connection == null) {
				System.out.println("No se pudo conectar a la base de datos.");
				return;
			}
			
			String sql = "INSERT INTO Horarios (Id_Empleado, Lunes, Martes, Miercoles, Jueves, Viernes, Sabado, Domingo) " +
			"VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			
			try {
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setString(1, idEmpleado);
				statement.setString(2, lunes);
				statement.setString(3, martes);
				statement.setString(4, miercoles);
				statement.setString(5, jueves);
				statement.setString(6, viernes);
				statement.setString(7, sabado);
				statement.setString(8, domingo);
				
				int filas = statement.executeUpdate();
				if (filas > 0) {
					System.out.println("Horario insertado correctamente.");
				}
			} 
			catch (SQLException e) {
				System.out.println("Error al insertar el horario: " + e.getMessage());
				e.printStackTrace();
			} 
			finally {
				cerrarConexion(connection);
			}
		}
	}

	public static HashMap<String, Horario> obtenerHorarios() {
	    HashMap<String, Horario> mapaHorarios = new HashMap<>();
	    Connection connection = conectar();

	    if (connection == null) {
	        System.out.println("No se pudo conectar a la base de datos.");
	        return mapaHorarios;
	    }

	    try {
	        String consulta = "SELECT * FROM Horarios";
	        Statement statement = connection.createStatement();
	        ResultSet resultSet = statement.executeQuery(consulta);

	        while (resultSet.next()) {
	            String idEmpleado = resultSet.getString("Id_Empleado");
	            String lunes = resultSet.getString("Lunes");
	            String martes = resultSet.getString("Martes");
	            String miercoles = resultSet.getString("Miercoles");
	            String jueves = resultSet.getString("Jueves");
	            String viernes = resultSet.getString("Viernes");
	            String sabado = resultSet.getString("Sabado");
	            String domingo = resultSet.getString("Domingo");

	            Horario horario = new Horario(idEmpleado, lunes, martes, miercoles, jueves, viernes, sabado, domingo);
	            mapaHorarios.put(idEmpleado, horario);
	        }

	    } catch (SQLException e) {
	        System.out.println("Error al obtener los horarios: " + e.getMessage());
	        e.printStackTrace();
	    } finally {
	        cerrarConexion(connection);
	    }

	    return mapaHorarios;
	}

	public static void cambiarHorario(String idEmpleado, String diaSemana, String nuevaActividad) {
		
		if(idEmpleado == null | diaSemana ==  null | nuevaActividad == null) {
			System.out.println("Hay valores nulos en los ingresados ");
		}
		else {
		    Connection connection = conectar();
	
		    if (connection == null) {
		        System.out.println("No se pudo conectar a la base de datos.");
		        return;
		    }
	
		    List<String> diasValidos = Arrays.asList("Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo");
		    if (!diasValidos.contains(diaSemana)) {
		        System.out.println("Día de la semana no válido: " + diaSemana);
		        cerrarConexion(connection);
		        return;
		    }
	
		    String sql = "UPDATE Horarios SET " + diaSemana + " = ? WHERE Id_Empleado = ?";
	
		    try {
		        PreparedStatement statement = connection.prepareStatement(sql);
		        statement.setString(1, nuevaActividad);
		        statement.setString(2, idEmpleado);
	
		        int filas = statement.executeUpdate();
		        if (filas > 0) {
		            System.out.println("Horario actualizado correctamente para " + diaSemana + ".");
		        } else {
		            System.out.println("No se encontró el empleado con ID: " + idEmpleado);
		        }
	
		    } catch (SQLException e) {
		        System.out.println("Error al cambiar el horario: " + e.getMessage());
		        e.printStackTrace();
		    } finally {
		        cerrarConexion(connection);
		    }
		}
	}

	//Lugares de servicio
	
	public static void crearTablaLugaresDeServicio() {
	    Connection connection = conectar();

	    if (connection == null) {
	        System.out.println("No se pudo conectar a la base de datos.");
	        return;
	    }

	    try {
	        Statement statement = connection.createStatement();
	        String sql = "CREATE TABLE Lugares_de_servicio (" +
	                     "Nombre_Lugar VARCHAR(100) PRIMARY KEY, " +
	                     "Cajero VARCHAR(100), " +
	                     "Tipo_Lugar VARCHAR(100), " +
	                     "Empleado_Auxiliar VARCHAR(100))";
	        statement.executeUpdate(sql);
	        System.out.println("Tabla 'Lugares_de_servicio' creada correctamente.");
	    } catch (SQLException e) {
	        if (e.getSQLState().equals("X0Y32")) {
	            System.out.println("La tabla 'Lugares_de_servicio' ya existe.");
	        } else {
	            e.printStackTrace();
	        }
	    } finally {
	        cerrarConexion(connection);
	    }
	}

	public void ejecutarUpdateLugares(String campo, String nuevoValor, String nombreLugar) {
		if(campo ==  null | nuevoValor ==  null | nombreLugar ==  null) {
			System.out.println("Hay valores nulos en los ingresados");
		}
		else {
		    Connection connection = conectar();
	
		    if (connection == null) {
		        System.out.println("No se pudo conectar a la base de datos.");
		        return;
		    }
	
		    try {
		        String sql = "UPDATE Lugares_de_servicio SET " + campo + " = ? WHERE Nombre_Lugar = ?";
		        PreparedStatement pstmt = connection.prepareStatement(sql);
		        pstmt.setString(1, nuevoValor);
		        pstmt.setString(2, nombreLugar);
		        pstmt.executeUpdate();
		        System.out.println("Campo '" + campo + "' actualizado correctamente.");
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        cerrarConexion(connection);
		    }
		}
	}

	public static void insertarLugarDeServicio(String nombreLugar, String cajero, String tipoLugar, String empleadoAuxiliar) {
		
		if(nombreLugar ==  null | cajero ==  null |  tipoLugar == null | empleadoAuxiliar ==  null) {
			System.out.println("Hay valores nulos en los ingresados");
		}
		else {
		    Connection connection = conectar();
	
		    if (connection == null) {
		        System.out.println("No se pudo conectar a la base de datos.");
		        return;
		    }
	
		    try {
		        String sql = "INSERT INTO Lugares_de_servicio (Nombre_Lugar, Cajero, Tipo_Lugar, Empleado_Auxiliar) VALUES (?, ?, ?, ?)";
		        PreparedStatement pstmt = connection.prepareStatement(sql);
		        pstmt.setString(1, nombreLugar);
		        pstmt.setString(2, cajero);
		        pstmt.setString(3, tipoLugar);
		        pstmt.setString(4, empleadoAuxiliar);
	
		        pstmt.executeUpdate();
		        System.out.println("Lugar de servicio insertado correctamente.");
		    } catch (SQLException e) {
		    	System.out.println("No se agrego...");
		    } finally {
		        cerrarConexion(connection);
		    }
		}
	}

	public static HashMap<String, LugarDeServicio> obtenerLugaresDeServicio() {
	    HashMap<String, LugarDeServicio> mapaLugares = new HashMap<>();
	    Connection connection = conectar();

	    if (connection == null) {
	        System.out.println("No se pudo conectar a la base de datos.");
	        return mapaLugares;
	    }

	    try {
	        String consulta = "SELECT * FROM Lugares_de_servicio";
	        Statement statement = connection.createStatement();
	        ResultSet resultSet = statement.executeQuery(consulta);

	        while (resultSet.next()) {
	        	HashMap<String, Empleado> empleados = obtenerEmpleados();
	            String nombre = resultSet.getString("Nombre_Lugar");
	            String cajero = resultSet.getString("Cajero");
	            String tipoLugar = resultSet.getString("Tipo_Lugar");
	            String empleadoAuxiliar = resultSet.getString("Empleado_Auxiliar");
	            Empleado cajeroEmpleado = empleados.get(cajero);
	            Empleado empeladoAux= empleados.get(empleadoAuxiliar);
	            LugarDeServicio lugar = new LugarDeServicio(nombre, cajeroEmpleado, tipoLugar, empeladoAux);
	            mapaLugares.put(nombre, lugar);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        cerrarConexion(connection);
	    }

	    return mapaLugares;
	}

	//Clientes
	
	public static void crearTablaClientes() {
	    Connection connection = conectar();

	    if (connection == null) {
	        System.out.println("No se pudo conectar a la base de datos.");
	        return;
	    }

	    try {
	        Statement statement = connection.createStatement();
	        String sql = "CREATE TABLE Clientes (" +
	                     "Nombre_Cliente VARCHAR(100) PRIMARY KEY, " +
	                     "Contrasenia VARCHAR(100))";
	        statement.executeUpdate(sql);
	        System.out.println("Tabla Clientes creada correctamente.");
	    } catch (SQLException e) {
	        if (e.getSQLState().equals("X0Y32")) {
	            System.out.println("La tabla 'Clientes' ya existe.");
	        } else {
	            e.printStackTrace();
	        }
	    } finally {
	        cerrarConexion(connection);
	    }
	}

	public static void insertarCliente(String nombreCliente, String contrasenia) {
		
		if(nombreCliente == null | contrasenia == null ) {
			System.out.println("Hay valores nulos en los ingresados");
		}
		else {
		    Connection connection = conectar();
	
		    if (connection == null) {
		        System.out.println("No se pudo conectar a la base de datos.");
		        return;
		    }
	
		    try {
		        String sql = "INSERT INTO Clientes (Nombre_Cliente, Contrasenia) VALUES (?, ?)";
		        PreparedStatement pstmt = connection.prepareStatement(sql);
		        pstmt.setString(1, nombreCliente);
		        pstmt.setString(2, contrasenia);
	
		        pstmt.executeUpdate();
		        System.out.println("Cliente insertado correctamente.");
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        cerrarConexion(connection);
		    }
		}
	}

	public static HashMap<String, Cliente> obtenerClientes() {
	    HashMap<String, Cliente> mapaClientes = new HashMap<>();
	    Connection connection = conectar();

	    if (connection == null) {
	        System.out.println("No se pudo conectar a la base de datos.");
	        return mapaClientes;
	    }

	    try {
	        String consulta = "SELECT * FROM Clientes";
	        Statement statement = connection.createStatement();
	        ResultSet resultSet = statement.executeQuery(consulta);

	        while (resultSet.next()) {
	            String nombre = resultSet.getString("Nombre_Cliente");
	            String contrasenia = resultSet.getString("Contrasenia");
	            Cliente cliente = new Cliente(nombre, contrasenia);
	            mapaClientes.put(nombre, cliente);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        cerrarConexion(connection);
	    }

	    return mapaClientes;
	}

	//Tiquetes
	
	public static void crearTablaTiquetes() {
	    Connection connection = conectar();

	    if (connection == null) {
	        System.out.println("No se pudo conectar a la base de datos.");
	        return;
	    }

	    try {
	        Statement statement = connection.createStatement();
	        String sql = "CREATE TABLE Tiquetes (" +
	                     "Numero_Tiquete VARCHAR(100) PRIMARY KEY, " +
	                     "Id_comprador VARCHAR(100), " +
	                     "Fecha_Inicial VARCHAR(50), " +
	                     "Fecha_Final VARCHAR(50), " +
	                     "Temporada VARCHAR(50), " +
	                     "Tipo_Tiquete VARCHAR(50), " +
	                     "Uso VARCHAR(50), " +
	                     "Atracciones_Validas VARCHAR(500))";
	        statement.executeUpdate(sql);
	        System.out.println("Tabla Tiquetes creada correctamente.");
	    } catch (SQLException e) {
	        if (e.getSQLState().equals("X0Y32")) {
	            System.out.println("La tabla 'Tiquetes' ya existe.");
	        } else {
	            e.printStackTrace();
	        }
	    } finally {
	        cerrarConexion(connection);
	    }
	}

	public static void insertarTiquete(String idComprador, String fechaInicial, String fechaFinal,
            String temporada, String tipoTiquete, String uso,
            String atraccionesValidas, String esFastPass) {
		
		if(idComprador == null | fechaInicial == null | fechaFinal == null | temporada == null | tipoTiquete == null | uso == null | atraccionesValidas == null | esFastPass == null) {
			System.out.println("Hay valores nulos en los ingresados");
		}
		else {
			Connection connection = ConexionDerby.conectar();
			if (connection == null) {
				System.out.println("No se pudo conectar a la base de datos.");
				return;
			}
		
			try {
				String obtenerCantidad = "SELECT COUNT(*) FROM Tiquetes";
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(obtenerCantidad);
				int nuevoNumero = 0;
				if (rs.next()) {
					nuevoNumero = rs.getInt(1) + 1;
				}
				String numeroTiquete = "TQ" + nuevoNumero;  	
				String sql = "INSERT INTO Tiquetes (Numero_Tiquete, Id_comprador, Fecha_Inicial, Fecha_Final, Temporada, Tipo_Tiquete, Uso, Atracciones_Validas, Es_Fast_Pass) " +
						"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setString(1, numeroTiquete);
				statement.setString(2, idComprador);
				statement.setString(3, fechaInicial);
				statement.setString(4, fechaFinal);
				statement.setString(5, temporada);
				statement.setString(6, tipoTiquete);
				statement.setString(7, uso);
				statement.setString(8, atraccionesValidas);
				statement.setString(9, esFastPass);
			
				int filas = statement.executeUpdate();
				if (filas > 0) {
					System.out.println("Tiquete insertado correctamente con número: " + numeroTiquete);
				}
			} 
			catch (SQLException e) {
				e.printStackTrace();
			} 
			finally {
				ConexionDerby.cerrarConexion(connection);
			}
		}
	}

	public static HashMap<String, Tiquete> obtenerTiquetes() {
	    HashMap<String, Tiquete> mapaTiquetes = new HashMap<>();
	    Connection connection = conectar();

	    if (connection == null) {
	        System.out.println("No se pudo conectar a la base de datos.");
	        return mapaTiquetes;
	    }

	    try {
	        Statement statement = connection.createStatement();
	        String sql = "SELECT * FROM Tiquetes";
	        ResultSet resultSet = statement.executeQuery(sql);

	        while (resultSet.next()) {
	            String numeroTiquete = resultSet.getString("Numero_Tiquete");
	            String idComprador = resultSet.getString("Id_comprador");
	            String fechaInicial = resultSet.getString("Fecha_Inicial");
	            String fechaFinal = resultSet.getString("Fecha_Final");
	            String temporada = resultSet.getString("Temporada");
	            String tipoTiquete = resultSet.getString("Tipo_Tiquete");
	            String uso = resultSet.getString("Uso");
	            String atraccionesValidas = resultSet.getString("Atracciones_Validas");
	            String esFastPass = resultSet.getString("Es_Fast_Pass");
	            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	            
	            LocalDate fechaI = LocalDate.parse(fechaInicial, formato);
	            LocalDate fechaF = LocalDate.parse(fechaFinal, formato);
	            
	            boolean fastPass = false;
	            if(esFastPass == null) {
	            	fastPass = false;
	            }
	            else if(esFastPass.equals("true")) {
	            	fastPass = true;
	            }
	            
	            boolean esDeTemporada = true;
	            if(temporada.equals("") || temporada.equals(" ")) {
	            	esDeTemporada = false;
	            }
	            
	            boolean usoF = false;
	            if(uso.equals("true")) {
	            	usoF = true;
	            }
	            
	            String[] partes = atraccionesValidas.split("-");
	            HashMap<String, Atraccion> mapaAtracciones = obtenerAtracciones();
	            List<String> listaAtracciones = Arrays.asList(partes);
	            List<Atraccion> listaAtraccionesValidas = new ArrayList<>();
	            
	            for(String atraccionString : listaAtracciones) {
	            	listaAtraccionesValidas.add(mapaAtracciones.get(atraccionString));
	            }
	            
	            Tiquete tiquete = new Tiquete(numeroTiquete, idComprador, fechaI, fechaF,fastPass,esDeTemporada, temporada, tipoTiquete, usoF, listaAtraccionesValidas);

	            mapaTiquetes.put(numeroTiquete, tiquete);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        cerrarConexion(connection);
	    }

	    return mapaTiquetes;
	}

	public void ejecutarUpdateTiqueteString(String campo, String nuevoValor, String numeroTiquete) {
		
		if(campo == null | nuevoValor == null | numeroTiquete == null) {
			System.out.println("Hay valores nulos en los ingresados");
		}
		else {
		    Connection connection = conectar();
		    if (connection == null) return;
	
		    try {
		        String sql = "UPDATE Tiquetes SET " + campo + " = ? WHERE Numero_Tiquete = ?";
		        PreparedStatement statement = connection.prepareStatement(sql);
		        statement.setString(1, nuevoValor);
		        statement.setString(2, numeroTiquete);
		        statement.executeUpdate();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        cerrarConexion(connection);
		    }
		}
	}
	
		
	}