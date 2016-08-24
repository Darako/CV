package composite;

/**
 * Componente. Trabajador gen√©rico de una empresa.
 */
/**
 * 
 * @author Jorge Gonz·lez RodrÌguez y Javier Ruiz RodrÌguez
 *
 */
public abstract class TrabajadorC {

	/**
	 * Puesto que ocupa el trabajador.
	 */
	private String puesto;
	/**
	 * Salario del trabajador.
	 */
	private double salario;

	/**
	 * Constructor.
	 * 
	 * @param puesto
	 *            Puesto que ocupa el trabajador.
	 * @param salario
	 *            Salario del trabajador.
	 */
	public TrabajadorC(String puesto, double salario) {
		this.puesto = puesto;
		this.salario = salario;
	}

	/**
	 * Devuelve el puesto que ocupa el trabajador.
	 * 
	 * @return Puesto que ocupa el trabajador.
	 */
	public String getPuesto() {
		return this.puesto;
	}

	/**
	 * Establece el puesto que ocupa el trabajador.
	 * 
	 * @param nuevo
	 *            Puesto que ocupa el trabajador.
	 */
	public void setPuesto(String nuevo) {
		this.puesto = nuevo;
	}

	/**
	 * Devuelve el salario del trabajador.
	 * 
	 * @return Salario del trabajador.
	 */
	public double getSalario() {
		return this.salario;
	}

	/**
	 * Establece el salario del trabajador.
	 * 
	 * @param nuevo
	 *            Salario del trabajador.
	 */
	public void setSalario(double nuevo) {
		this.salario = nuevo;
	}

	/**
	 * Devuelve los datos puesto y salario del trabajador.
	 * 
	 * @return Datos puesto y salario del trabajador.
	 */
	@Override
	public String toString() {
		return "Puesto: " + this.puesto + " - Salario: " + this.salario;
	}

	/**
	 * A√±ade un subordinado a la lista de subordinados del trabajador.
	 * 
	 * @param t
	 *            Subordinado a a√±adir.
	 */
	public abstract void anadirSubordinado(TrabajadorC t);

	/**
	 * Elimina un subordinado de la lista de subordinados del trabajador.
	 * 
	 * @param t
	 *            Subordinado a eliminar.
	 */
	public abstract void eliminarSubordinado(TrabajadorC t);

	/**
	 * Devuelve la suma de un trabajador y sus subordinados si los tuviese.
	 * 
	 * @return Suma de los salarios de un trabajador y sus subordinados.
	 */
	public abstract double getSalarios();

	/**
	 * Devuelve una descripci√≥n del trabajador y sus subordinados si los
	 * tuviese.
	 * 
	 * @return Descripci√≥n del trabajador y sus subordinados.
	 */
	public abstract String getDescripcion();
}
