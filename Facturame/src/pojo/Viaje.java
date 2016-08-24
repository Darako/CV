package pojo;

import java.sql.Date;
import java.sql.Timestamp;

public class Viaje {

	private int idViaje;
	private String lugarInicio;
	private String lugarDestino;
	private Timestamp horaInicio;
	private Timestamp horaLlegada;
	private Date fechaInicio;
	private Date fechaLlegada;
	private int kmViaje;
	private int idPorte;

	public Viaje(int idViaje, String lugarInicio, String lugarDestino, Timestamp horaInicio, Timestamp horaLlegada,
			Date fechaInicio, Date fechaLlegada, int kmViaje, int idPorte) {
		this.idViaje = idViaje;
		this.lugarInicio = lugarInicio;
		this.lugarDestino = lugarDestino;
		this.horaInicio = horaInicio;
		this.horaLlegada = horaLlegada;
		this.fechaInicio = fechaInicio;
		this.fechaLlegada = fechaLlegada;
		this.kmViaje = kmViaje;
		this.idPorte = idPorte;
	}

	public Viaje() {
	}

	public int getIdViaje() {
		return idViaje;
	}

	public void setIdViaje(int idViaje) {
		this.idViaje = idViaje;
	}

	public String getLugarInicio() {
		return lugarInicio;
	}

	public void setLugarInicio(String lugarInicio) {
		this.lugarInicio = lugarInicio;
	}

	public String getLugarDestino() {
		return lugarDestino;
	}

	public void setLugarDestino(String lugarDestino) {
		this.lugarDestino = lugarDestino;
	}

	public Timestamp getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Timestamp horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Timestamp getHoraLlegada() {
		return horaLlegada;
	}

	public void setHoraLlegada(Timestamp horaLlegada) {
		this.horaLlegada = horaLlegada;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaLlegada() {
		return fechaLlegada;
	}

	public void setFechaLlegada(Date fechaLlegada) {
		this.fechaLlegada = fechaLlegada;
	}

	public int getKmViaje() {
		return kmViaje;
	}

	public void setKmViaje(int kmViaje) {
		this.kmViaje = kmViaje;
	}

	public int getIdPorte() {
		return idPorte;
	}

	public void setIdPorte(int idPorte) {
		this.idPorte = idPorte;
	}

}
