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
import operacionesCRUD.CRUDempleados;
import pojo.Porte;
import pojo.Trabajador;
import util.UtilVentanas;
/**
 * Ventana formulario para el alta del Porte. Información sobre el transportista.
 */
/**
 * 
 * @author Jorge González Rodríguez y Javier Ruiz Rodríguez
 *
 */
public class VentanaPorteTransportista extends JFrame {

	private Porte p;
	private FactoriaCRUD fc;
	private PorteGrafico pb;
	private CRUDempleados ce;
	private JPanel contentPane;
	private JTextField textDni;
	private JTextField textNombre;
	private JComboBox comboBoxDni;

	public VentanaPorteTransportista(PorteGrafico pb, VentanaPrincipal ventanaPrincipal) throws SQLException, IOException {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				formWindowClosing(ventanaPrincipal);
			}
		});
		this.pb = pb;
		this.p = pb.getPorte();
		this.fc = new FactoriaCRUD();
		this.ce = (CRUDempleados) fc.crearCRUD(FactoriaCRUD.TIPO_EMPLEADO);
		setTitle("Facturame --- Porte --- Transportista");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 340, 195);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setBounds(0, 0, 324, 222);
		contentPane.add(panel);

		JLabel lblEligeUnTransportista = new JLabel("Elige transportista:");
		lblEligeUnTransportista.setBounds(10, 10, 110, 20);
		panel.add(lblEligeUnTransportista);

		textDni = new JTextField();
		textDni.setColumns(10);
		textDni.setBounds(120, 10, 140, 20);
		panel.add(textDni);

		JButton buttonBuscar = new JButton("");
		buttonBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					buttonBuscarActionPerformed(e);
				} catch (SQLException sqle) {
					UtilVentanas.Alertas.mostrar(UtilVentanas.Alertas.ERROR_SQL,sqle.toString());
				}
			}
		});
		buttonBuscar.setIcon(new ImageIcon("images\\lupa_16.png"));
		buttonBuscar.setBounds(270, 8, 25, 25);
		panel.add(buttonBuscar);

		comboBoxDni = new JComboBox();
		comboBoxDni.setBounds(120, 40, 140, 20);

		ArrayList<Object> trabajadoresO = new ArrayList<Object>(ce.buscarTodo());
		Agregado agregado = new AgregadoConcreto(trabajadoresO);
		Iterador iterador = agregado.crearIterador();

		try {
			while (iterador.hayMas()) {
				comboBoxDni.addItem(((Trabajador) iterador.elementoActual()).getDni());
				iterador.siguiente();
			}
		} catch (IndexOutOfBoundsException iobe) {
			UtilVentanas.Alertas.mostrar(UtilVentanas.Alertas.ERROR_IOB,iobe.toString());
		}

		comboBoxDni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					comboActionPerformed(e);
				} catch (SQLException sqle) {
					UtilVentanas.Alertas.mostrar(UtilVentanas.Alertas.ERROR_SQL,sqle.toString());
				}
			}
		});
		panel.add(comboBoxDni);

		JLabel labelNombre = new JLabel("Nombre:");
		labelNombre.setBounds(10, 98, 46, 14);
		panel.add(labelNombre);

		textNombre = new JTextField();
		textNombre.setEditable(false);
		textNombre.setColumns(10);
		textNombre.setBounds(86, 95, 209, 20);
		panel.add(textNombre);

		JButton buttonSiguiente = new JButton("");
		buttonSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonSiguienteActionPerformed(e);
			}
		});
		buttonSiguiente.setIcon(new ImageIcon("images\\flecha_16.png"));
		buttonSiguiente.setBounds(120, 126, 89, 23);
		panel.add(buttonSiguiente);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 71, 304, 14);
		panel.add(separator);
		setDefaultCombo();
	}

	private void formWindowClosing(java.awt.event.WindowEvent evt) {
		this.setVisible(false);
	}

	private void buttonSiguienteActionPerformed(java.awt.event.ActionEvent evt) {
		pb.setEspera(false);
		this.setVisible(false);
	}

	public Porte getPorte() {
		return p;
	}

	private void setDefaultCombo() throws SQLException{
		JComboBox comboBox = this.comboBoxDni;
		Object selected = comboBox.getItemAt(0);
		Trabajador empleado = (Trabajador) ce.buscarUno(selected);

		textNombre.setText((String.valueOf(empleado.getNombre() + " " + empleado.getApellidos())));

		p.setDni(empleado.getDni());
	}
	
	private void comboActionPerformed(ActionEvent evt) throws SQLException {
		JComboBox comboBox = (JComboBox) evt.getSource();
		Object selected = comboBox.getSelectedItem();
		Trabajador empleado = (Trabajador) ce.buscarUno(selected);

		textNombre.setText((String.valueOf(empleado.getNombre() + " " + empleado.getApellidos())));

		p.setDni(empleado.getDni());

	}

	private void buttonBuscarActionPerformed(ActionEvent arg0) throws SQLException {
		Object selected = textDni.getText();
		Trabajador empleado = (Trabajador) ce.buscarUno(selected);

		if (empleado != null) {
			textNombre.setText((String.valueOf(empleado.getNombre() + " " + empleado.getApellidos())));

			p.setDni(empleado.getDni());
		}
	}
	private void formWindowClosing(VentanaPrincipal ventanaPrincipal) {
		this.setVisible(false);
		ventanaPrincipal.setVisible(true);
	}
}
