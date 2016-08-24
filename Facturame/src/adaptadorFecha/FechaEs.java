package adaptadorFecha;

import java.util.StringTokenizer;

import util.UtilVentanas;

/**
 * Clase para manejar fechas en formato de USA: MM,DD,AAAA.
 */
/**
 * 
 * @author Jorge Gonz�lez Rodr�guez y Javier Ruiz Rodr�guez
 *
 */
public class FechaEs {
	/**
	 * Entero que representa el año.
	 */
	private int year;
	/**
	 * Entero que representa el mes.
	 */
	private int month;
	/**
	 * Entero que representa el día.
	 */
	private int day;

	/**
	 * Constructor
	 * 
	 * @param month
	 *            Entero que representa el mes.
	 * @param day
	 *            Entero que representa el día.
	 * @param year
	 *            Entero que representa el año.
	 */
	public FechaEs(int year, int month, int day) {
		this.day = day;
		this.month = month;
		this.year = year;
	}

	/**
	 * Constructor
	 * 
	 * @param date
	 *            Fecha en formato de USA: MM,DD,AAAA.
	 */
	public FechaEs(String date) {
		StringTokenizer st = new StringTokenizer(date, "-");
		int contador = 0;
		String mm = "", dd = "", aa = "";
		while (st.hasMoreTokens()) {
			contador++;
			if (contador == 1)
				mm = st.nextToken();
			else if (contador == 2)
				dd = st.nextToken();
			else if (contador == 3)
				aa = st.nextToken();
		}

		if (contador >= 3) {
			this.day = Integer.parseInt(dd);
			this.month = Integer.parseInt(mm);
			this.year = Integer.parseInt(aa);
		} else
			UtilVentanas.Alertas.mostrar(UtilVentanas.Alertas.ERROR_SQL,
					"La fecha introducida no est� en el formato correcto.");
	}

	/**
	 * Devuelve una cadena con la fecha en formato de USA: MM,DD,AAAA.
	 * 
	 * @return Fecha en formato de USA: MM,DD,AAAA.
	 */
	@Override
	public String toString() {
		return day + "-" + month + "-" + year;
	}

	/**
	 * Devuelve el año.
	 * 
	 * @return Entero que representa el año.
	 */
	public int getYear() {
		return this.year;
	}

	/**
	 * Establece el año.
	 * 
	 * @param year
	 *            Entero que representa el año.
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * Devuelve el día.
	 * 
	 * @return Entero que representa el día.
	 */
	public int getDay() {
		return this.day;
	}

	/**
	 * Establece el día.
	 * 
	 * @param day
	 *            Entero que representa el día.
	 */
	public void setDay(int day) {
		this.day = day;
	}

	/**
	 * Devuelve el mes.
	 * 
	 * @return Entero que representa el mes.
	 */
	public int getMonth() {
		return this.month;
	}

	/**
	 * Establece el mes.
	 * 
	 * @param month
	 *            Entero que representa el mes.
	 */
	public void setMonth(int month) {
		this.month = month;
	}
}
