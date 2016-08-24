import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import interfacesGraficas.VentanaLogin;
/**
 * Punto de partida de la aplicación. Inicializa la ventana de login y posteriormente la muestra.
 */

/**
 * 
 * @author Jorge González Rodríguez y Javier Ruiz Rodríguez
 *
 */
public class Main {

	/**
	 * Método principal del programa.
	 * 
	 * @param args
	 * @throws SQLException
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws NoSuchAlgorithmException
	 */
	public static void main(String[] args)
			throws SQLException, IOException, InterruptedException, NoSuchAlgorithmException {
		 VentanaLogin login = VentanaLogin.getLogin();
		 login.setVisible(true);

	}
}
