package iterador;

import java.util.ArrayList;
/**
 * Implementación de la interfaz Agregado y crea los iteradores concretos según necesite.
 */
/**
 * 
 * @author Jorge González Rodríguez y Javier Ruiz Rodríguez
 *
 */
public class AgregadoConcreto implements Agregado {

    public ArrayList elementos;

    /**
     * Constructor principal.
     * 
     * @param vec
     */
    public AgregadoConcreto(ArrayList vec) {
        elementos = vec;
    }

    @Override
    public Iterador crearIterador() {
        return new IteradorConcreto(this);
    }
}
