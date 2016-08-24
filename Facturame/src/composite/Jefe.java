package composite;

import java.util.ArrayList;

/**
 * Compuesto. Jefe con subordinados.
 */
/**
 * 
 * @author Jorge González Rodríguez y Javier Ruiz Rodríguez
 *
 */
public class Jefe extends TrabajadorC {

	/**
	 * Lista de subordinados.
	 */
	private ArrayList<TrabajadorC> subordinados;

	/**
	 * Constructor.
	 * 
	 * @param puesto
	 *            Puesto que ocupa el trabajador.
	 * @param salario
	 *            Salario del trabajador.
	 */
	public Jefe(String puesto, double salario) {
		super(puesto, salario);
		subordinados = new ArrayList<>();// Inicializa la lista.
	}

	@Override
	public void anadirSubordinado(TrabajadorC t) {
		subordinados.add(t); // AÃ±ade elemento a la lista.
	}

	@Override
	public void eliminarSubordinado(TrabajadorC t) {
		subordinados.remove(t);// Elimina elemento de la lista.
	}

	@Override
	public double getSalarios() {
		double sum = getSalario(); // Suma el salario de este empleado.
		for (TrabajadorC subordinado : subordinados) {
			sum += subordinado.getSalarios();// Suma el salario de su
												// subordinado.
		}
		return sum;
	}

	@Override
	public String getDescripcion() {
		String desc = this.toString(); // AÃ±ade a la cadena el puesto de este
										// empleado
		for (TrabajadorC subordinado : subordinados) {
			// AÃ±ade a la cadena el puesto de su subordinado.
			desc += "\n\t### " + subordinado.getDescripcion();
		}
		return desc;
	}
}
