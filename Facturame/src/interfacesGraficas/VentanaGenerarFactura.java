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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import adaptadorFecha.AdaptadorFechaPostgres;
import adaptadorFecha.Fecha;
import adaptadorFecha.FechaEs;
import factorias.FactoriaCRUD;
import iterador.Agregado;
import iterador.AgregadoConcreto;
import iterador.Iterador;
import operacionesCRUD.CRUDempresa;
import operacionesCRUD.CRUDportes;
import pojo.Empresa;
import pojo.Porte;
import pojo.Subordinado;
import pojo.UsuarioAutenticacion;
import util.UtilVentanas;
/**
 * Ventana que sirve para la visualización de las tablas que compondrán la factura.
 */
/**
 * 
 * @author Jorge González Rodríguez y Javier Ruiz Rodríguez
 *
 */
public class VentanaGenerarFactura extends JFrame {

	private FactoriaCRUD fc;
	private Empresa empresa;
	private Porte porte;
	private CRUDempresa ce;
	private CRUDportes cp;
	private JPanel contentPane;
	private JTextField textEmpresa;
	private JTextField textNombre;
	private JTextField textTelefono;
	private JTextField textMail;
	private JTextField tFechaInicio;
	private JTextField tFechaFinal;
	private VentanaPrincipal principal;
	private ArrayList<Object> portes;
	private JComboBox comboBoxEmpresa;

	public VentanaGenerarFactura(VentanaPrincipal principal) throws SQLException, IOException {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				formWindowClosing(principal);
			}
		});
		this.principal = principal;
		this.fc = new FactoriaCRUD();
		this.ce = (CRUDempresa) fc.crearCRUD(FactoriaCRUD.TIPO_EMPRESA);
		setTitle("Facturame --- Porte --- Empresa");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 340, 310);
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
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		buttonBuscar.setIcon(new ImageIcon("images\\lupa_16.png"));
		buttonBuscar.setBounds(270, 8, 25, 25);
		contentPane.add(buttonBuscar);

		comboBoxEmpresa = new JComboBox();
		comboBoxEmpresa.setBounds(120, 40, 140, 20);

		ArrayList<Object> empresasO = new ArrayList<Object>(ce.buscarTodo());
		Agregado agregado = new AgregadoConcreto(empresasO);
		Iterador iterador = agregado.crearIterador();

		try {
			while (iterador.hayMas()) {
				Empresa empresa = (Empresa) iterador.elementoActual();
				comboBoxEmpresa.addItem(empresa.getEmpresa());
				iterador.siguiente();
			}
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Error: " + e.toString());
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
		labelNombre.setBounds(10, 84, 46, 14);
		contentPane.add(labelNombre);

		textNombre = new JTextField();
		textNombre.setEditable(false);
		textNombre.setBounds(86, 81, 209, 20);
		contentPane.add(textNombre);
		textNombre.setColumns(10);

		JLabel labelTelefono = new JLabel("Tel\u00E9fono:");
		labelTelefono.setBounds(10, 115, 71, 14);
		contentPane.add(labelTelefono);

		textTelefono = new JTextField();
		textTelefono.setEditable(false);
		textTelefono.setBounds(86, 112, 209, 20);
		contentPane.add(textTelefono);
		textTelefono.setColumns(10);

		JLabel labelMail = new JLabel("Mail:");
		labelMail.setBounds(10, 146, 46, 14);
		contentPane.add(labelMail);

		textMail = new JTextField();
		textMail.setEditable(false);
		textMail.setBounds(86, 143, 209, 20);
		contentPane.add(textMail);
		textMail.setColumns(10);

		JButton bGenerarFactura = new JButton("Generar factura");
		bGenerarFactura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					buttonSiguienteActionPerformed();
				} catch (SQLException sqle) {
					UtilVentanas.Alertas.mostrar(UtilVentanas.Alertas.ERROR_SQL, sqle.toString());
				} catch (IOException ioe) {
					UtilVentanas.Alertas.mostrar(UtilVentanas.Alertas.ERROR_IOE, ioe.toString());
				}
			}
		});
		bGenerarFactura.setBounds(107, 238, 118, 23);
		contentPane.add(bGenerarFactura);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 71, 304, 14);
		contentPane.add(separator);

		JLabel lblFechaInicio = new JLabel("Fecha inicio:");
		lblFechaInicio.setBounds(10, 181, 71, 14);
		contentPane.add(lblFechaInicio);

		JLabel lblNewLabel = new JLabel("Fecha final:");
		lblNewLabel.setBounds(10, 212, 71, 14);
		contentPane.add(lblNewLabel);

		tFechaInicio = new JTextField();
		tFechaInicio.setBounds(86, 178, 209, 20);
		contentPane.add(tFechaInicio);
		tFechaInicio.setColumns(10);

		tFechaFinal = new JTextField();
		tFechaFinal.setBounds(86, 209, 210, 20);
		contentPane.add(tFechaFinal);
		tFechaFinal.setColumns(10);
		
		setDefaultCombo();
	}

	private void formWindowClosing(VentanaPrincipal principal) {
		this.setVisible(false);
		principal.setVisible(true);
	}

	private void setDefaultCombo() throws SQLException{
		JComboBox comboBox = this.comboBoxEmpresa;
		Object selected = comboBox.getItemAt(0);
		empresa = (Empresa) ce.buscarUnoNombre(selected);
		
		textNombre.setText(empresa.getEmpresa());
		textMail.setText(empresa.getEmail());
		textTelefono.setText(String.valueOf(empresa.getnTelefono()));
	}
	
	private void comboActionPerformed(ActionEvent evt) throws SQLException {
		JComboBox comboBox = (JComboBox) evt.getSource();
		Object selected = comboBox.getSelectedItem();
		empresa = (Empresa) ce.buscarUnoNombre(selected);
		
		textNombre.setText(empresa.getEmpresa());
		textMail.setText(empresa.getEmail());
		textTelefono.setText(String.valueOf(empresa.getnTelefono()));

	}

	private void buttonSiguienteActionPerformed() throws SQLException, IOException {		
		Fecha fechaInicio = new AdaptadorFechaPostgres(new FechaEs(tFechaInicio.getText()));
		Fecha fechaFinal = new AdaptadorFechaPostgres(new FechaEs(tFechaFinal.getText()));
		String fechaIniForm = fechaInicio.toString();
		String fechaFinForm = fechaFinal.toString();
		Boolean tienePortes = false;
		this.fc = new FactoriaCRUD();
		this.cp = (CRUDportes) fc.crearCRUD(FactoriaCRUD.TIPO_PORTE);
		portes = cp.buscarPorNif(empresa.getNif());
		if(portes.size() != 0){
			if (!fechaIniForm.equalsIgnoreCase("0-0-0") || !fechaFinForm.equalsIgnoreCase("0-0-0")) {
				this.setVisible(false);
				VentanaTabla2PDF ventanaPDF = new VentanaTabla2PDF(fechaIniForm, fechaFinForm, empresa.getNif(), principal);
				ventanaPDF.setVisible(true);
				UtilVentanas.Alertas.mostrar(UtilVentanas.Alertas.EXITO_FACTURA, "");
			} else {
				tFechaInicio.setText("");
				tFechaFinal.setText("");
			}
		}
		else{
			mostrarAlerta();
		}
		
	}

	private void mostrarAlerta() {
		JOptionPane.showMessageDialog(null, "No hay ningún Porte asociado a esta Empresa.");
	}
	private void buttonBuscarActionPerformed(java.awt.event.ActionEvent evt) throws SQLException {
		Object selected = textEmpresa.getText();
		empresa = (Empresa) ce.buscarUno(selected);

		if (empresa != null) {
			textNombre.setText((String.valueOf(empresa.getEmpresa())));
			textTelefono.setText((String.valueOf(empresa.getnTelefono())));
			textMail.setText(empresa.getEmail());
		}
	}
}
