package sistema;

public class Horario {
	
	private String idEmpleado;
	private String lunes;
	private String martes;
	private String miercoles;
	private String jueves;
	private String viernes;
	private String sabado;
	private String domingo;
	
	public Horario(String idEmpleado, String lunes, String martes, String miercoles, String jueves, String viernes,
			String sabado, String domingo) {
		super();
		this.idEmpleado = idEmpleado;
		this.lunes = lunes;
		this.martes = martes;
		this.miercoles = miercoles;
		this.jueves = jueves;
		this.viernes = viernes;
		this.sabado = sabado;
		this.domingo = domingo;
	}


	public String getIdEmpleado() {
		return idEmpleado;
	}

	public String getLunes() {
		return lunes;
	}

	public String getMartes() {
		return martes;
	}

	public String getMiercoles() {
		return miercoles;
	}

	public String getJueves() {
		return jueves;
	}

	public String getViernes() {
		return viernes;
	}

	public String getSabado() {
		return sabado;
	}

	public String getDomingo() {
		return domingo;
	}
	
	public void cambiarHorario(String dia, String nuevaActividad) {
		if (dia.equals("Lunes")) {
			this.lunes = nuevaActividad;
		}
		else if (dia.equals("Martes")) {
			this.martes = nuevaActividad;
		}
		else if (dia.equals("Miercoles")) {
			this.miercoles = nuevaActividad;
		}
		else if (dia.equals("Jueves")) {
			this.jueves = nuevaActividad;
		}
		else if (dia.equals("Viernes")) {
			this.viernes = nuevaActividad;
		}
		else if (dia.equals("Sabado")) {
			this.sabado = nuevaActividad;
		}
		else if (dia.equals("Domingo")) {
			this.domingo = nuevaActividad;
		}
	}
}
