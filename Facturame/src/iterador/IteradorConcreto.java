package iterador;
/**
 * Implementación de la interfaz Iterador.
 */
/**
 * 
 * @author Jorge González Rodríguez y Javier Ruiz Rodríguez
 *
 */
public class IteradorConcreto implements Iterador {

	private AgregadoConcreto agregado;
	private int indice = 0;

	/**
	 * Constructor principal.
	 * 
	 * @param agregado
	 */
	public IteradorConcreto(AgregadoConcreto agregado) {
		this.agregado = agregado;
	}

	@Override
	public Object primero() throws IndexOutOfBoundsException {
		Object objeto = null;
		if (!agregado.elementos.isEmpty()) {
			indice = 0;
			objeto = agregado.elementos.get(0);
		} else {
			throw new IndexOutOfBoundsException();
		}
		return objeto;
	}

	@Override
	public Object siguiente() throws IndexOutOfBoundsException {
		Object objeto = null;
		if (indice < agregado.elementos.size()) {
			objeto = agregado.elementos.get(indice);
			indice++;
		} else {
			throw new IndexOutOfBoundsException();
		}
		return objeto;
	}

	@Override
	public boolean hayMas() {
		boolean resultado = true;
		if (agregado.elementos.isEmpty() || indice == agregado.elementos.size()) {
			resultado = false;
		}
		return resultado;
	}

	@Override
	public Object elementoActual() throws IndexOutOfBoundsException {
		Object objeto = null;
		if (indice < agregado.elementos.size()) {
			objeto = agregado.elementos.get(indice);
		} else {
			throw new IndexOutOfBoundsException();
		}
		return objeto;
	}
}
