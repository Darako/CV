package builders;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import factorias.FactoriaPorte;
import interfacesGraficas.VentanaPrincipal;
import pojo.Porte;
import pojo.Viaje;

/**
 * Interfaz de construcción de los objetos Porte. Define de forma abstracta los métodos que tendrán todos los constructores de portes.
 */
/**
 * 
 * @author Jorge González Rodríguez y Javier Ruiz Rodríguez
 *
 */
public abstract class PorteBuilder {

	/*
	 * Objeto Porte.
	 */
	protected Porte porte;
	/*
	 * Factoría para la creación de objetos Porte.
	 */
	private FactoriaPorte fp;
	/**
	 * Array de objetos Viaje
	 */
	private ArrayList<Viaje> viajes;

	/**
	 * Constructor Principal
	 */
	public PorteBuilder() {
		this.fp = new FactoriaPorte();
		this.porte = fp.crearPorte();
		this.viajes = new ArrayList<Viaje>();
	}

	/**
	 * Formulario para los datos básicos del porte.
	 * 
	 * @param ventanaPrincipal
	 * @throws InterruptedException
	 */
	abstract void getDatosPrincipales(VentanaPrincipal ventanaPrincipal) throws InterruptedException;

	/**
	 * Formulario para los datos de la empresa a la que se le va a realizar el porte.
	 * 
	 * @param ventanaPrincipal
	 * @throws InterruptedException
	 * @throws SQLException
	 * @throws IOException
	 */
	abstract void getDatosEmpresa(VentanaPrincipal ventanaPrincipal)
			throws InterruptedException, SQLException, IOException;

	/**
	 * Formulario para los datos del transportista que va a realizar el porte.
	 * 
	 * @param ventanaPrincipal
	 * @throws InterruptedException
	 * @throws SQLException
	 * @throws IOException
	 */
	abstract void getDatosTransportista(VentanaPrincipal ventanaPrincipal)
			throws InterruptedException, SQLException, IOException;

	/**
	 * Formulario para los datos del camión asociado al porte.
	 * 
	 * @param ventanaPrincipal
	 * @throws InterruptedException
	 * @throws SQLException
	 * @throws IOException
	 */
	abstract void getDatosCamion(VentanaPrincipal ventanaPrincipal)
			throws InterruptedException, SQLException, IOException;

	/**
	 * Formulario para los datos de los viajes que se compone el porte.
	 * 
	 * @param ventanaPrincipal
	 * @throws InterruptedException
	 */
	abstract void getDatosViajes(VentanaPrincipal ventanaPrincipal) throws InterruptedException;

	/**
	 * Método para la adición de Viajes a la estructura contenedora de todos los viajes.
	 * 
	 * @param viaje
	 */
	public void anadirViaje(Viaje viaje) {
		viajes.add(viaje);
	}

	/**
	 * Método para coger todos los viajes del Porte.
	 * 
	 * @return
	 */
	public ArrayList<Viaje> cogerViajes() {
		return viajes;
	}

	/**
	 * Método para coger el Porte generado.
	 * 
	 * @return
	 */
	public Porte getPorte() {
		return porte;
	}

}
