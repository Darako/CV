package pojo;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Trabajador {

	private String dni;
	private String nombre;
	private String apellidos;
	private Date fechaNacimiento;
	private String sexo;
	private Date fechaAltaEmpleado;
	private String rango;
	private double sueldo;

	public Trabajador(String dni, String nombre, String apellidos, Date fechaNacimiento, String sexo,
			Date fechaAltaEmpleado, String rango, double sueldo) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaNacimiento = fechaNacimiento;
		this.sexo = sexo;
		this.fechaAltaEmpleado = fechaAltaEmpleado;
		this.rango = rango;
		this.sueldo = sueldo;
	}

	public Trabajador() {
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Date getFechaAltaEmpleado() {
		return fechaAltaEmpleado;
	}

	public void setFechaAltaEmpleado(Date fechaAltaEmpleado) {
		this.fechaAltaEmpleado = fechaAltaEmpleado;
	}

	public String getRango() {
		return rango;
	}

	public void setRango(String rango) {
		this.rango = rango;
	}

	public double getSueldo() {
		return sueldo;
	}

	public void setSueldo(double sueldo) {
		this.sueldo = sueldo;
	}

	@Override
	public String toString() {
		return "Trabajador [dni=" + dni + ", nombre=" + nombre + ", apellidos=" + apellidos + ", fechaNacimiento="
				+ fechaNacimiento + ", sexo=" + sexo + ", fechaAltaEmpleado=" + fechaAltaEmpleado + ", rango=" + rango
				+ ", sueldo=" + sueldo + "]";
	}

}
