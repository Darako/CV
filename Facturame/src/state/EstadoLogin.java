package state;

import interfacesGraficas.VentanaLogin;

/**
 * Clase que representa el estado NO logeado.
 */
/**
 * 
 * @author Jorge González Rodríguez y Javier Ruiz Rodríguez
 *
 */
public class EstadoLogin implements Estado{

	@Override
	public void ejecutar(VentanaLogin v) {
		if (v.getAuthUser().isAdmin())
			v.setEstadoActual(new EstadoAdmin());
		else
			v.setEstadoActual(new EstadoNoAdmin());
	}

}
