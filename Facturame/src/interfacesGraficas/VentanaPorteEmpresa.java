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
import operacionesCRUD.CRUDempresa;
import pojo.Empresa;
import pojo.Porte;
import util.UtilVentanas;
/**
 * Ventana formulario para el alta del Porte. Información sobre la empresa.
 */
/**
 * 
 * @author Jorge González Rodríguez y Javier Ruiz Rodríguez
 *
 */
public class VentanaPorteEmpresa extends JFrame {

	private Porte p;
	private FactoriaCRUD fc;
	private PorteGrafico pb;
	private CRUDempresa ce;
	private JPanel contentPane;
	private JTextField textEmpresa;
	private JTextField textNombre;
	private JTextField textTelefono;
	private JTextField textMail;
	private JComboBox comboBoxEmpresa;

	public VentanaPorteEmpresa(PorteGrafico pb, VentanaPrincipal ventanaPrincipal) throws SQLException, IOException {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				formWindowClosing(ventanaPrincipal);
			}
		});
		this.pb = pb;
		this.p = pb.getPorte();
		this.fc = new FactoriaCRUD();
		this.ce = (CRUDempresa) fc.crearCRUD(FactoriaCRUD.TIPO_EMPRESA);
		setTitle("Facturame --- Porte --- Empresa");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 340, 260);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel labelEligeUnaEmpresa = new JLabel("Elige empresa:");
		labelEligeUnaEmpresa.setBounds(10, 10, 110, 20);
		contentPane.add(labelEligeUnaEmpresa);

		textEmpresa = new JTextField();
		textEmpresa.setBounds(120, 10, 140, 20);
		contentPane.add(textEmpresa);
		textEmpresa.setColumns(10);

		JButton buttonBuscar = new JButton("");
		buttonBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					buttonBuscarActionPerformed(e);
				} catch (SQLException sqle) {
					UtilVentanas.Alertas.mostrar(UtilVentanas.Alertas.ERROR_SQL, sqle.toString());
				}
			}
		});
		buttonBuscar.setIcon(new ImageIcon("images\\lupa_16.png"));
		buttonBuscar.setSelectedIcon(new ImageIcon("images\\lupa_16.png"));
		buttonBuscar.setBounds(270, 8, 25, 25);
		contentPane.add(buttonBuscar);

		comboBoxEmpresa = new JComboBox();
		comboBoxEmpresa.setBounds(120, 40, 140, 20);

		ArrayList<Object> empresasO = new ArrayList<Object>(ce.buscarTodo());
		Agregado agregado = new AgregadoConcreto(empresasO);
		Iterador iterador = agregado.crearIterador();

		try {
			while (iterador.hayMas()) {
				comboBoxEmpresa.addItem(((Empresa) iterador.elementoActual()).getEmpresa());
				iterador.siguiente();
			}
		} catch (IndexOutOfBoundsException iobe) {
			UtilVentanas.Alertas.mostrar(UtilVentanas.Alertas.ERROR_IOB, iobe.toString());
		}

		comboBoxEmpresa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					comboActionPerformed(e);
				} catch (SQLException sqle) {
					UtilVentanas.Alertas.mostrar(UtilVentanas.Alertas.ERROR_SQL, sqle.toString());
				}
			}
		});

		contentPane.add(comboBoxEmpresa);

		JLabel labelNombre = new JLabel("Nombre:");
		labelNombre.setBounds(10, 98, 46, 14);
		contentPane.add(labelNombre);

		textNombre = new JTextField();
		textNombre.setEditable(false);
		textNombre.setBounds(86, 95, 209, 20);
		contentPane.add(textNombre);
		textNombre.setColumns(10);

		JLabel labelTelefono = new JLabel("Tel\u00E9fono:");
		labelTelefono.setBounds(10, 129, 71, 14);
		contentPane.add(labelTelefono);

		textTelefono = new JTextField();
		textTelefono.setEditable(false);
		textTelefono.setBounds(86, 126, 209, 20);
		contentPane.add(textTelefono);
		textTelefono.setColumns(10);

		JLabel labelMail = new JLabel("Mail:");
		labelMail.setBounds(10, 160, 46, 14);
		contentPane.add(labelMail);

		textMail = new JTextField();
		textMail.setEditable(false);
		textMail.setBounds(86, 157, 209, 20);
		contentPane.add(textMail);
		textMail.setColumns(10);

		JButton buttonSiguiente = new JButton("");
		buttonSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonSiguienteActionPerformed(e);
			}
		});
		buttonSiguiente.setIcon(new ImageIcon("images\\flecha_16.png"));
		buttonSiguiente.setBounds(120, 188, 89, 23);
		contentPane.add(buttonSiguiente);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 71, 304, 14);
		contentPane.add(separator);
		
		setDefaultCombo();

	}

	private void setDefaultCombo() throws SQLException{
		JComboBox comboBox = this.comboBoxEmpresa;
		Object selected = comboBox.getItemAt(0);
		Empresa empresa = (Empresa) ce.buscarUnoNombre(selected);
		textNombre.setText(empresa.getEmpresa());
		textMail.setText(empresa.getEmail());
		textTelefono.setText(String.valueOf(empresa.getnTelefono()));

		p.setNif(empresa.getNif());
	}
	
	private void formWindowClosing(VentanaPrincipal ventanaPrincipal) {
		ventanaPrincipal.setVisible(true);
		this.setVisible(false);
	}

	private void comboActionPerformed(ActionEvent evt) throws SQLException {
		JComboBox comboBox = (JComboBox) evt.getSource();
		Object selected = comboBox.getSelectedItem();
		Empresa empresa = (Empresa) ce.buscarUnoNombre(selected);

		textNombre.setText(empresa.getEmpresa());
		textMail.setText(empresa.getEmail());
		textTelefono.setText(String.valueOf(empresa.getnTelefono()));

		p.setNif(empresa.getNif());

	}

	private void buttonSiguienteActionPerformed(java.awt.event.ActionEvent evt) {
		pb.setEspera(false);
		this.setVisible(false);
	}

	public Porte getPorte() {
		return p;
	}

	private void buttonBuscarActionPerformed(java.awt.event.ActionEvent evt) throws SQLException {
		Object selected = textEmpresa.getText();
		Empresa empresa = (Empresa) ce.buscarUno(selected);

		if (empresa != null) {
			textNombre.setText((String.valueOf(empresa.getEmpresa())));
			textTelefono.setText((String.valueOf(empresa.getnTelefono())));
			textMail.setText(empresa.getEmail());
			p.setNif(empresa.getNif());
		}
	}
}
