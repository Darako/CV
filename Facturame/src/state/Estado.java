package state;

import java.io.IOException;
import java.sql.SQLException;

import interfacesGraficas.VentanaLogin;

/**
 * Interfaz genérica del estado. Todos los distintos estados implementarán dicho método.
 */
/**
 * 
 * @author Jorge González Rodríguez y Javier Ruiz Rodríguez
 *
 */
public interface Estado {

	/**
	 * Método de visualización de la ventana principal de la aplicación.
	 * 
	 * @param v
	 * @throws SQLException
	 * @throws IOException
	 */
	public void ejecutar(VentanaLogin v) throws SQLException, IOException;
}