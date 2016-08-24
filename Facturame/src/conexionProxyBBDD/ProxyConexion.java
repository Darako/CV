package conexionProxyBBDD;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import daos.CrearConexion;
import factorias.FactoriaConexion;
/**
 * Clase que sirve como proxy de seguridad para logear en disco todos los accesos a la base de datos.
 */
/**
 * 
 * @author Jorge González Rodríguez y Javier Ruiz Rodríguez
 *
 */
public class ProxyConexion implements CrearConexion {

	/**
	 * Factoria para la creación de conexiones.
	 */
	private FactoriaConexion fc;
	/**
	 * Log de conexiones
	 */
	Logger logger = Logger.getLogger("MyLog");
	/**
	 * Objeto para abrir archivos en disco.
	 */
	FileHandler fh;

	/**
	 * Constructor principal
	 * 
	 * @throws SecurityException
	 * @throws IOException
	 */
	public ProxyConexion() throws SecurityException, IOException {
		this.fh = new FileHandler("logs\\LogAccesoBD.log");
		logger.addHandler(fh);
		SimpleFormatter formatter = new SimpleFormatter();
		fh.setFormatter(formatter);
	}

	@Override
	public Conexion crearConexion() throws SQLException {
		try {
			logger.info("El usuario " + " ha cogido un objeto conexión a las " + ".");
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		if (fc == null)
			fc = new FactoriaConexion();

		return fc.crearConexion();
	}

}
