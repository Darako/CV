package daos;

import java.sql.SQLException;

import conexionProxyBBDD.Conexion;
/**
 * Interfaz de creación de conexiones.
 */
/**
 * @author Jorge González Rodríguez y Javier Ruiz Rodríguez
 *
 */
public interface CrearConexion {
	
	/**
	 * Devuelve un objeto de tipo conexión.
	 * 
	 * @return
	 * @throws SQLException
	 */
	Conexion crearConexion() throws SQLException;

}
