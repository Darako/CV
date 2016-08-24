package adaptadorFecha;

import java.util.StringTokenizer;

/**
 * Clase para adaptar fechas en formato de USA (MM,DD,AAAA) a fechas en formato
 * de EspaÃ±a (DD/MM/AAAA)
 */
/**
 * 
 * @author Jorge González Rodríguez y Javier Ruiz Rodríguez
 *
 */
public class AdaptadorFechaPostgres implements Fecha {

	/**
	 * Fecha en formato USA que se va a adaptar.
	 */
	private FechaEs fes;

	/**
	 * Constructor
	 * 
	 * @param fus
	 *            Fecha en formato USA.
	 */
	public AdaptadorFechaPostgres(FechaEs fes) {
		this.fes = fes;
	}

	@Override
	public String toString() {
		StringTokenizer st = new StringTokenizer(fes.toString(), "-");
		String mm = st.nextToken();
		String dd = st.nextToken();
		String aaaa = st.nextToken();

		return aaaa + "-" + mm + "-" + dd;
	}

	@Override
	public int getAnio() {
		return this.fes.getYear();
	}

	@Override
	public void setAnio(int anio) {
		this.fes.setYear(anio);
	}

	@Override
	public int getDia() {
		return this.fes.getDay();
	}

	@Override
	public void setDia(int dia) {
		this.fes.setDay(dia);
	}

	@Override
	public int getMes() {
		return this.fes.getMonth();
	}

	@Override
	public void setMes(int mes) {
		this.fes.setMonth(mes);
	}
}
