package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Clase para la creación de hashes para el almacenamiento de contraseñas.
 */
/**
 * 
 * @author Jorge González Rodríguez y Javier Ruiz Rodríguez
 *
 */
public class CreadorHashes {

	/**
	 * Objeto de sí mismo para el patrón singleton
	 */
	private static CreadorHashes ch;
	/**
	 * Objeto para la generación de hashes
	 */
	private MessageDigest md;

	/**
	 * Constructor principal.
	 * 
	 * @throws NoSuchAlgorithmException
	 */
	private CreadorHashes() throws NoSuchAlgorithmException {
		this.md = MessageDigest.getInstance("MD5");
	}

	/**
	 * Metodo de generación de hashes a partir de una contraseña.
	 * 
	 * @param pass
	 * @return
	 */
	public String generarHash(String pass) {
		md.update(pass.getBytes());
		byte[] digest = md.digest();
		StringBuffer sb = new StringBuffer();
		for (byte b : digest) {
			sb.append(String.format("%02x", b & 0xff));
		}

		return sb.toString();
	}

	/**
	 * Método de instanciación del objeto Singleton.
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static CreadorHashes getCreadorHashes() throws NoSuchAlgorithmException {
		if (ch == null)
			ch = new CreadorHashes();
		return ch;
	}

}
