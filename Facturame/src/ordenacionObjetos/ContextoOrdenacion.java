package ordenacionObjetos;

import java.util.ArrayList;

import pojo.Porte;
/**
 * Clase que usa las distintas ordenaciones dependiendo de su necesidad.
 */
/**
 * 
 * @author Jorge González Rodríguez y Javier Ruiz Rodríguez
 *
 */

public class ContextoOrdenacion{

	/**
	 * Lista de objetos genéricos.
	 */
	private ArrayList<Object> lista;
	/**
	 * Objeto que representa la estrategia seleccionada.
	 */
	private Estrategia estrategia;

	/**
	 * Constructor principal.
	 * 
	 * @param lista
	 * @param estrategia
	 */
	public ContextoOrdenacion(ArrayList<Object> lista, Estrategia estrategia) {
		this.lista = new ArrayList<Object>(lista);
		this.estrategia = estrategia;
	}

	/**
	 * Método que ejecuta la estrategia de ordenación seleccionada.
	 * 
	 * @param campo
	 * @return
	 */
	public ArrayList<Object> ejecutarEstrategia(String campo) {
		estrategia.ordena(lista, campo);
		return lista;
	}

}
