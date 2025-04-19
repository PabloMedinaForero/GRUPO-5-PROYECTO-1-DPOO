package sistema.tiquete;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import sistema.atraccion.Atraccion;

public class Tiquete {
	
	private String numeroTiquete;
	private LocalDate fechaInicial;
	private LocalDate fechaFinal;
	private boolean esFastPass;
	private boolean esDeTemporada;
	private String temporada;
	private String tipo;
	private boolean uso;
	private String idComprador;
	private List<Atraccion> atraccionesValidas;
	
	public Tiquete(String numeroTiquete,String idComprador, LocalDate fechaInicial, LocalDate fechaFinal, boolean esFastPass, boolean esDeTemporada, String temporada,
			String tipoTiquete, boolean uso,List<Atraccion> atraccionesValidas) {
		super();
		this.numeroTiquete = numeroTiquete;
		this.fechaInicial = fechaInicial;
		this.fechaFinal = fechaFinal;
		this.esFastPass = esFastPass;
		this.esDeTemporada = esDeTemporada;
		this.temporada = temporada;
		this.tipo = tipoTiquete;
		this.idComprador = idComprador;
		this.uso = uso;
		this.atraccionesValidas = atraccionesValidas;
	}

	public String getIdComprador() {
		return idComprador;
	}

	public String getNumeroTiquete() {
		return numeroTiquete;
	}

	public LocalDate getFechaInicial() {
		return fechaInicial;
	}

	public LocalDate getFechaFinal() {
		return fechaFinal;
	}

	public boolean isEsFastPass() {
		return esFastPass;
	}

	public boolean isEsDeTemporada() {
		return esDeTemporada;
	}

	public String getTemporada() {
		return temporada;
	}

	public String getTipo() {
		return tipo;
	}

	public boolean isUso() {
		return uso;
	}

	public Collection<Atraccion> getAtraccionesValidas() {
		return atraccionesValidas;
	}

	
	
}