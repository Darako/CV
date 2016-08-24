package interfacesGraficas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import builders.PorteGrafico;
import factorias.FactoriaCRUD;
import iterador.Agregado;
import iterador.AgregadoConcreto;
import iterador.Iterador;
import operacionesCRUD.CRUDcamiones;
import pojo.Camion;
import pojo.Porte;
import util.UtilVentanas;
/**
 * Ventana formulario para el alta del Porte. Información sobre el camión.
 */
/**
 * 
 * @author Jorge González Rodríguez y Javier Ruiz Rodríguez
 *
 */
public class VentanaPorteCamion extends JFrame {

	private Porte p;
	private FactoriaCRUD fc;
	private PorteGrafico pb;
	private CRUDcamiones cc;
	private JPanel contentPane;
	private JTextField textMatricula;
	private JSeparator separator;
	private JButton buttonSiguiente;
	private JButton buttonBuscar;
	private JLabel labelLargo;
	private JTextField textLargo;
	private JLabel labelAncho;
	private JTextField textAncho;
	private JLabel labelLargoCaja;
	private JTextField textLargoCaja;
	private JLabel labelAnchoCaja;
	private JTextField textAnchoCaja;
	private JLabel labelPesomaxCaja;
	private JTextField textPesoCaja;
	private JLabel labelVolumenCaja;
	private JTextField textVolumenCaja;
	private JLabel labelAltoCaja;
	private JTextField textAltoCaja;
	private JLabel labelGalibo;
	private JTextField textGalibo;
	private JLabel labelTrampilla;
	private JCheckBox checkBoxTrampilla;
	private JComboBox comboBoxMatricula;

	public VentanaPorteCamion(PorteGrafico pb, VentanaPrincipal ventanaPrincipal) throws SQLException, IOException {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				formWindowClosing(ventanaPrincipal);
			}
		});
		this.pb = pb;
		this.p = pb.getPorte();
		this.fc = new FactoriaCRUD();
		this.cc = (CRUDcamiones) fc.crearCRUD(FactoriaCRUD.TIPO_CAMION);
		setTitle("Facturame --- Porte --- Cami\u00F3n");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 340, 313);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel labelElige = new JLabel("Elige cami\u00F3n:");
		labelElige.setBounds(10, 11, 110, 20);
		contentPane.add(labelElige);

		textMatricula = new JTextField();
		textMatricula.setColumns(10);
		textMatricula.setBounds(120, 11, 140, 20);
		contentPane.add(textMatricula);

		comboBoxMatricula = new JComboBox();
		comboBoxMatricula.setBounds(120, 41, 140, 20);

		ArrayList<Object> camionesO = new ArrayList<Object>(cc.buscarTodo());
		Agregado agregado = new AgregadoConcreto(camionesO);
		Iterador iterador = agregado.crearIterador();

		try {
			while (iterador.hayMas()) {				
				comboBoxMatricula.addItem(((Camion) iterador.elementoActual()).getMatricula());
				iterador.siguiente();
			}
		} catch (IndexOutOfBoundsException iobe) {
			UtilVentanas.Alertas.mostrar(UtilVentanas.Alertas.ERROR_IOB,iobe.toString());
		}

		comboBoxMatricula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					comboActionPerformed(e);
				} catch (SQLException sqle) {
					UtilVentanas.Alertas.mostrar(UtilVentanas.Alertas.ERROR_SQL,sqle.toString());
				}
			}
		});

		contentPane.add(comboBoxMatricula);

		separator = new JSeparator();
		separator.setBounds(10, 74, 304, 14);
		contentPane.add(separator);

		buttonSiguiente = new JButton("");
		buttonSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonActionPerformed(e);
			}
		});

		buttonSiguiente.setIcon(new ImageIcon("images\\flecha_16.png"));
		buttonSiguiente.setBounds(119, 238, 89, 23);
		contentPane.add(buttonSiguiente);

		buttonBuscar = new JButton("");
		buttonBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					button1ActionPerformed(e);
				} catch (SQLException sqle) {
					UtilVentanas.Alertas.mostrar(UtilVentanas.Alertas.ERROR_SQL,sqle.toString());
				}
			}
		});
		buttonBuscar.setIcon(new ImageIcon("images\\lupa_16.png"));
		buttonBuscar.setBounds(271, 6, 25, 25);
		contentPane.add(buttonBuscar);

		labelLargo = new JLabel("Largo: ");
		labelLargo.setBounds(10, 92, 60, 15);
		contentPane.add(labelLargo);

		textLargo = new JTextField();
		textLargo.setEditable(false);
		textLargo.setColumns(10);
		textLargo.setBounds(95, 87, 60, 20);
		contentPane.add(textLargo);

		labelAncho = new JLabel("Ancho: ");
		labelAncho.setBounds(10, 122, 75, 15);
		contentPane.add(labelAncho);

		textAncho = new JTextField();
		textAncho.setEditable(false);
		textAncho.setColumns(10);
		textAncho.setBounds(95, 117, 60, 20);
		contentPane.add(textAncho);

		labelLargoCaja = new JLabel("Largo caja: ");
		labelLargoCaja.setBounds(165, 91, 68, 15);
		contentPane.add(labelLargoCaja);

		textLargoCaja = new JTextField();
		textLargoCaja.setEditable(false);
		textLargoCaja.setColumns(10);
		textLargoCaja.setBounds(243, 90, 60, 20);
		contentPane.add(textLargoCaja);

		labelAnchoCaja = new JLabel("Ancho caja: ");
		labelAnchoCaja.setBounds(165, 123, 75, 15);
		contentPane.add(labelAnchoCaja);

		textAnchoCaja = new JTextField();
		textAnchoCaja.setEditable(false);
		textAnchoCaja.setColumns(10);
		textAnchoCaja.setBounds(243, 120, 60, 20);
		contentPane.add(textAnchoCaja);

		labelPesomaxCaja = new JLabel("Peso(max) caja: ");
		labelPesomaxCaja.setBounds(10, 184, 89, 15);
		contentPane.add(labelPesomaxCaja);

		textPesoCaja = new JTextField();
		textPesoCaja.setEditable(false);
		textPesoCaja.setColumns(10);
		textPesoCaja.setBounds(95, 179, 60, 20);
		contentPane.add(textPesoCaja);

		labelVolumenCaja = new JLabel("Volumen caja: ");
		labelVolumenCaja.setBounds(165, 182, 89, 15);
		contentPane.add(labelVolumenCaja);

		textVolumenCaja = new JTextField();
		textVolumenCaja.setEditable(false);
		textVolumenCaja.setColumns(10);
		textVolumenCaja.setBounds(243, 179, 60, 20);
		contentPane.add(textVolumenCaja);

		labelAltoCaja = new JLabel("Alto caja: ");
		labelAltoCaja.setBounds(165, 156, 60, 15);
		contentPane.add(labelAltoCaja);

		textAltoCaja = new JTextField();
		textAltoCaja.setEditable(false);
		textAltoCaja.setColumns(10);
		textAltoCaja.setBounds(243, 153, 60, 20);
		contentPane.add(textAltoCaja);

		labelGalibo = new JLabel("G\u00E1libo: ");
		labelGalibo.setBounds(10, 155, 60, 15);
		contentPane.add(labelGalibo);

		textGalibo = new JTextField();
		textGalibo.setEditable(false);
		textGalibo.setColumns(10);
		textGalibo.setBounds(95, 148, 60, 20);
		contentPane.add(textGalibo);

		labelTrampilla = new JLabel("Trampilla:");
		labelTrampilla.setBounds(10, 210, 68, 15);
		contentPane.add(labelTrampilla);

		checkBoxTrampilla = new JCheckBox("");
		checkBoxTrampilla.setSelected(true);
		checkBoxTrampilla.setEnabled(false);
		checkBoxTrampilla.setBounds(95, 206, 21, 23);
		contentPane.add(checkBoxTrampilla);
		setDefaultCombo();
	}

	private void buttonActionPerformed(java.awt.event.ActionEvent evt) {
		pb.setEspera(false);
		this.setVisible(false);
	}

	public Porte getPorte() {
		return p;
	}

	private void setDefaultCombo() throws SQLException{
		JComboBox comboBox = this.comboBoxMatricula;
		Object selected = comboBox.getItemAt(0);
		Camion camion = (Camion) cc.buscarUno(selected);

		textAncho.setText((String.valueOf(camion.getAncho())));
		textAnchoCaja.setText((String.valueOf(camion.getAncho())));
		textAltoCaja.setText((String.valueOf(camion.getAlturaCaja())));
		textGalibo.setText((String.valueOf(camion.getGalibo())));
		textLargo.setText((String.valueOf(camion.getLargo())));
		textLargoCaja.setText((String.valueOf(camion.getLongCaja())));
		textPesoCaja.setText((String.valueOf(camion.getPesoMaxCaja())));
		textVolumenCaja.setText((String.valueOf(camion.getVolumenCaja())));
		checkBoxTrampilla.setSelected((camion.isTrampilla()));
		p.setnBastidor(camion.getnBastidor());
}
	
	private void comboActionPerformed(ActionEvent evt) throws SQLException {
		JComboBox comboBox = (JComboBox) evt.getSource();
		Object selected = comboBox.getSelectedItem();
		Camion camion = (Camion) cc.buscarUno(selected);

		textAncho.setText((String.valueOf(camion.getAncho())));
		textAnchoCaja.setText((String.valueOf(camion.getAncho())));
		textAltoCaja.setText((String.valueOf(camion.getAlturaCaja())));
		textGalibo.setText((String.valueOf(camion.getGalibo())));
		textLargo.setText((String.valueOf(camion.getLargo())));
		textLargoCaja.setText((String.valueOf(camion.getLongCaja())));
		textPesoCaja.setText((String.valueOf(camion.getPesoMaxCaja())));
		textVolumenCaja.setText((String.valueOf(camion.getVolumenCaja())));
		checkBoxTrampilla.setSelected((camion.isTrampilla()));
		p.setnBastidor(camion.getnBastidor());

	}

	private void button1ActionPerformed(java.awt.event.ActionEvent evt) throws SQLException {
		Object selected = textMatricula.getText();
		Camion camion = (Camion) cc.buscarUno(selected);

		if (camion != null) {
			textAncho.setText((String.valueOf(camion.getAncho())));
			textAnchoCaja.setText((String.valueOf(camion.getAncho())));
			textAltoCaja.setText((String.valueOf(camion.getAlturaCaja())));
			textGalibo.setText((String.valueOf(camion.getGalibo())));
			textLargo.setText((String.valueOf(camion.getLargo())));
			textLargoCaja.setText((String.valueOf(camion.getLongCaja())));
			textPesoCaja.setText((String.valueOf(camion.getPesoMaxCaja())));
			textVolumenCaja.setText((String.valueOf(camion.getVolumenCaja())));
			checkBoxTrampilla.setSelected((camion.isTrampilla()));
			p.setnBastidor(camion.getnBastidor());
		}
	}

	private void formWindowClosing(VentanaPrincipal ventanaPrincipal) {
		this.setVisible(false);
		ventanaPrincipal.setVisible(true);
		
	}

}
