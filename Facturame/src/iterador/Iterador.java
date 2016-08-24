package iterador;
/**
 * Interfaz donde se definen los métodos de iteración que tendrán todos los iteradores concretos.
 */
/**
 * 
 * @author Jorge González Rodríguez y Javier Ruiz Rodríguez
 *
 */
public interface Iterador {

	/**
	 * Método que devuelve el primer objeto del iterador.
	 * 
	 * @return
	 * @throws IndexOutOfBoundsException
	 */
    Object primero() throws IndexOutOfBoundsException;

    /**
     * Método que devuelve el siguiente objeto del iterador.
     * 
     * @return
     * @throws IndexOutOfBoundsException
     */
    Object siguiente() throws IndexOutOfBoundsException;

    /**
     * Método que devuleve un booleano indicando si hay más objetos o no en el iterador.
     * @return
     */
    boolean hayMas();

    /**
     * Método que devuelve el objeto actual al que está apuntando el iterador.
     * @return
     * @throws IndexOutOfBoundsException
     */
    Object elementoActual() throws IndexOutOfBoundsException;
}
