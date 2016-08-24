package builders;

import java.io.IOException;
import java.sql.SQLException;

import interfacesGraficas.VentanaPorteCamion;
import interfacesGraficas.VentanaPorteDatos;
import interfacesGraficas.VentanaPorteEmpresa;
import interfacesGraficas.VentanaPorteTransportista;
import interfacesGraficas.VentanaPorteViaje;
import interfacesGraficas.VentanaPrincipal;
/**
 * Implementación gráfica de la clase abstracta PorteBuilder.
 */
/**
 * 
 * @author Jorge González Rodríguez y Javier Ruiz Rodríguez
 *
 */
public class PorteGrafico extends PorteBuilder {

	/**
	 * Determina si se debe mostrar o no el siguiente formulario.
	 */
	private boolean espera;
	/**
	 * Formulario de datos genericos.
	 */
	private VentanaPorteDatos v1;
	/**
	 * Formulario de datos de empresa.
	 */
	private VentanaPorteEmpresa v2;
	/**
	 * Formulario de datos del transportista.
	 */
	private VentanaPorteTransportista v3;
	/**
	 * Formulario de datos del camión.
	 */
	private VentanaPorteCamion v4;
	/**
	 * Formulario de datos de los viajes.
	 */
	private VentanaPorteViaje v5;

	/**
	 * Constructor principal.
	 * 
	 * @throws SQLException
	 * @throws IOException
	 */
	public PorteGrafico() throws SQLException, IOException {
		super();
		this.espera = true;
	}
	
	@Override
	void getDatosPrincipales(VentanaPrincipal ventanaPrincipal) throws InterruptedException {
		v1 = new VentanaPorteDatos(this, ventanaPrincipal);
		while (espera) {
			Thread.sleep(1500);
		}
		espera = true;
		porte = v1.getPorte();
		v1 = null;
	}

	@Override
	void getDatosEmpresa(VentanaPrincipal ventanaPrincipal) throws InterruptedException, SQLException, IOException {
		v2 = new VentanaPorteEmpresa(this, ventanaPrincipal);
		v2.setVisible(true);
		while (espera) {
			Thread.sleep(1500);
		}
		espera = true;
		porte = v2.getPorte();
		v2 = null;
	}

	@Override
	void getDatosTransportista(VentanaPrincipal ventanaPrincipal)
			throws InterruptedException, SQLException, IOException {

		this.v3 = new VentanaPorteTransportista(this, ventanaPrincipal);
		v3.setVisible(true);
		while (espera) {
			Thread.sleep(1500);
		}
		espera = true;
		porte = v3.getPorte();
		v3 = null;
	}

	@Override
	void getDatosCamion(VentanaPrincipal ventanaPrincipal) throws InterruptedException, SQLException, IOException {
		v4 = new VentanaPorteCamion(this, ventanaPrincipal);
		v4.setVisible(true);
		while (espera) {
			Thread.sleep(1500);
		}
		espera = true;
		porte = v4.getPorte();
		v4 = null;
	}

	@Override
	void getDatosViajes(VentanaPrincipal ventanaPrincipal) throws InterruptedException {
		v5 = new VentanaPorteViaje(this, ventanaPrincipal);
		v5.setVisible(true);
		while (espera) {
			Thread.sleep(1500);
		}
		espera = true;
		porte = v5.getPorte();
		porte.setIdPorte(1);
		v5 = null;
	}

	public void setEspera(boolean espera) {
		this.espera = espera;
	}

}
