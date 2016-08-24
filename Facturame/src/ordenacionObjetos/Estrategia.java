package ordenacionObjetos;

import java.util.ArrayList;
/**
 * Interfaz que representa el método que todas la estrategias deben implementar.
 */
/**
 * 
 * @author Jorge González Rodríguez y Javier Ruiz Rodríguez
 *
 */
public interface Estrategia{

	/**
	 * Método que ordena una lista de objetos por un campo concreto.
	 * 
	 * @param lista
	 * @param campo
	 */
	void ordena(ArrayList<?> lista, String campo);
}
