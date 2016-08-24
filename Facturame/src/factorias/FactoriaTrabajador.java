package factorias;

import pojo.Subordinado;
import pojo.Trabajador;
/**
 * 
 * @author Jorge González Rodríguez y Javier Ruiz Rodríguez
 *
 */
public class FactoriaTrabajador {

	public Trabajador crearTrabajador() {
		return new Trabajador();
	}

	public Subordinado crearSubordinado() {
		return new Subordinado();
	}
}
