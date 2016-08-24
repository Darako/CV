package interfacesGraficas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import factorias.FactoriaCRUD;
import iterador.Agregado;
import iterador.AgregadoConcreto;
import iterador.Iterador;
import operacionesCRUD.CRUDcamiones;
import operacionesCRUD.CRUDempleados;
import operacionesCRUD.CRUDempresa;
import ordenacionObjetos.ContextoOrdenacion;
import ordenacionObjetos.Estrategia;
import ordenacionObjetos.EstrategiaCamion;
import ordenacionObjetos.EstrategiaEmpleado;
import ordenacionObjetos.EstrategiaEmpresa;
import pojo.Camion;
import pojo.Empresa;
import pojo.Trabajador;
import util.UtilVentanas;
/**
 * Ventana para visualización de datos. Desde esta pantalla se pueden visualizar tanto empleados, como camiones, como empresas.
 */
/**
 * 
 * @author Jorge González Rodríguez y Javier Ruiz Rodríguez
 *
 */
public class VentanaConsulta extends JFrame {

	private ContextoOrdenacion co;
	private EstrategiaCamion esC;
	private EstrategiaEmpleado esT;
	private EstrategiaEmpresa esE;
	private JPanel contentPane;
	private JPanel panelCamion;
	private JPanel panelEmpleado;
	private JPanel panelEmpresa;
	private JLabel labelClave;
	private JTextField textNombreEmpresa;
	private JTextField textNifEmpresa;
	private JTextField textTelefonoEmpresa;
	private JTextField textMatriculaCamion;
	private JTextField textNumeroBastidorCamion;
	private JTextField textVolumenCajaCamion;
	private JTextField textDniNifEmpleado;
	private JTextField textNombreEmpleado;
	private JTextField textSueldoEmpleado;
	private JComboBox<?> comboBoxTipo;
	private ArrayList<JTextField> textos = new ArrayList<JTextField>();
	private ArrayList<JTextField> textosCamiones = new ArrayList<JTextField>();
	private ArrayList<JTextField> textosEmpresas = new ArrayList<JTextField>();
	private ArrayList<JTextField> textosEmpleados = new ArrayList<JTextField>();
	private String tipo;
	private FactoriaCRUD fc;
	private CRUDcamiones cc;
	private CRUDempresa ce;
	private CRUDempleados ct;
	private ArrayList<Object> camiones;
	private ArrayList<Object> empleados;
	private ArrayList<Object> empresas;
	private Agregado agregado;
	private Iterador iterador;
	private JTextField textClave;
	private JTextArea tDatos;
	private JComboBox<?> comboOrdenacion;

	// public static void main(String[] args) throws SQLException, IOException {
	// VentanaPrincipal principal = new VentanaPrincipal();
	// principal.setVisible(false);
	// VentanaConsulta ventCons = new VentanaConsulta(principal);
	// ventCons.setVisible(true);
	// }

	public VentanaConsulta(VentanaPrincipal principal) throws SQLException, IOException {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				formWindowClosing(e, principal);
			}
		});
		this.fc = new FactoriaCRUD();
		this.tipo = "Empresa";
		this.ce = (CRUDempresa) fc.crearCRUD(FactoriaCRUD.TIPO_EMPRESA);
		this.cc = (CRUDcamiones) fc.crearCRUD(FactoriaCRUD.TIPO_CAMION);
		this.ct = (CRUDempleados) fc.crearCRUD(FactoriaCRUD.TIPO_EMPLEADO);
		this.esC = new EstrategiaCamion();
		this.esT = new EstrategiaEmpleado();
		this.esE = new EstrategiaEmpresa();
		this.co = new ContextoOrdenacion(ce.buscarTodo(), esE);
		this.empresas = new ArrayList<Object>(co.ejecutarEstrategia("NIF"));
		this.co = new ContextoOrdenacion(cc.buscarTodo(), esC);
		this.camiones = new ArrayList<Object>(co.ejecutarEstrategia("bastidor"));
		this.co = new ContextoOrdenacion(ct.buscarTodo(), esT);
		this.empleados = new ArrayList<Object>(co.ejecutarEstrategia("nombre"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 771, 275);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		comboOrdenacion = new JComboBox();
		comboOrdenacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					comboOrdenacionActionPerformed();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		comboOrdenacion.setModel(new DefaultComboBoxModel(new String[] { "NIF", "direccion", "telefono" }));
		comboOrdenacion.setBounds(259, 200, 165, 20);

		comboBoxTipo = new JComboBox();
		comboBoxTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tipo = comboBoxTipo.getSelectedItem().toString();
				try {
					comboBoxTipoCambiarSeleccion(e, comboBoxTipo.getSelectedItem().toString());
					cambiarEtiquetasComboOrd();
				} catch (SQLException sqle) {
					UtilVentanas.Alertas.mostrar(UtilVentanas.Alertas.ERROR_SQL, sqle.toString());
				}
			}
		});
		comboBoxTipo.setModel(new DefaultComboBoxModel(new String[] { "Empresa", "Cami\u00F3n", "Empleado" }));
		comboBoxTipo.setBounds(10, 11, 120, 20);
		contentPane.add(comboBoxTipo);

		panelCamion = new JPanel();
		panelCamion
				.setBorder(new TitledBorder(null, "Cami\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelCamion.setBounds(10, 42, 414, 137);
		contentPane.add(panelCamion);
		panelCamion.setLayout(null);

		panelEmpleado = new JPanel();
		panelEmpleado.setBorder(new TitledBorder(null, "Empleado", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelEmpleado.setBounds(10, 42, 414, 120);
		contentPane.add(panelEmpleado);
		panelEmpleado.setLayout(null);

		panelEmpresa = new JPanel();
		panelEmpresa.setBorder(new TitledBorder(null, "Empresa", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelEmpresa.setBounds(10, 42, 414, 120);
		contentPane.add(panelEmpresa);
		panelEmpresa.setLayout(null);

		JLabel labelNombreEmpresa = new JLabel("Nombre:");
		labelNombreEmpresa.setBounds(10, 30, 120, 20);
		panelEmpresa.add(labelNombreEmpresa);

		JLabel labelNif = new JLabel("NIF:");
		labelNif.setBounds(10, 55, 120, 20);
		panelEmpresa.add(labelNif);

		JLabel labelTelefono = new JLabel("Telefono: ");
		labelTelefono.setBounds(10, 80, 120, 20);
		panelEmpresa.add(labelTelefono);
		panelCamion.setLayout(null);

		JLabel labelMatricula = new JLabel("Matricula:");
		labelMatricula.setBounds(10, 30, 120, 20);
		panelCamion.add(labelMatricula);

		JLabel labelNumeroBastidor = new JLabel("Número bastidor:");
		labelNumeroBastidor.setBounds(10, 55, 120, 20);
		panelCamion.add(labelNumeroBastidor);

		JLabel labelVolumenCaja = new JLabel("Volumen caja: ");
		labelVolumenCaja.setBounds(10, 80, 120, 20);
		panelCamion.add(labelVolumenCaja);

		JLabel labelDniNif = new JLabel("DNI:");
		labelDniNif.setBounds(10, 30, 120, 20);
		panelEmpleado.add(labelDniNif);

		JLabel labelNombre = new JLabel("Nombre:");
		labelNombre.setBounds(10, 55, 120, 20);
		panelEmpleado.add(labelNombre);

		JLabel labelSueldo = new JLabel("Sueldo: ");
		labelSueldo.setBounds(10, 80, 120, 20);
		panelEmpleado.add(labelSueldo);

		textNombreEmpresa = new JTextField();
		textNombreEmpresa.setEditable(false);
		textNombreEmpresa.setBounds(140, 30, 264, 20);
		panelEmpresa.add(textNombreEmpresa);
		textNombreEmpresa.setColumns(10);

		textNifEmpresa = new JTextField();
		textNifEmpresa.setEditable(false);
		textNifEmpresa.setColumns(10);
		textNifEmpresa.setBounds(140, 55, 264, 20);
		panelEmpresa.add(textNifEmpresa);

		textTelefonoEmpresa = new JTextField();
		textTelefonoEmpresa.setEditable(false);
		textTelefonoEmpresa.setColumns(10);
		textTelefonoEmpresa.setBounds(140, 80, 264, 20);
		panelEmpresa.add(textTelefonoEmpresa);

		textMatriculaCamion = new JTextField();
		textMatriculaCamion.setEditable(false);
		textMatriculaCamion.setBounds(140, 30, 264, 20);
		panelCamion.add(textMatriculaCamion);
		textMatriculaCamion.setColumns(10);

		textNumeroBastidorCamion = new JTextField();
		textNumeroBastidorCamion.setEditable(false);
		textNumeroBastidorCamion.setColumns(10);
		textNumeroBastidorCamion.setBounds(140, 55, 264, 20);
		panelCamion.add(textNumeroBastidorCamion);

		textVolumenCajaCamion = new JTextField();
		textVolumenCajaCamion.setEditable(false);
		textVolumenCajaCamion.setColumns(10);
		textVolumenCajaCamion.setBounds(140, 80, 264, 20);
		panelCamion.add(textVolumenCajaCamion);

		textDniNifEmpleado = new JTextField();
		textDniNifEmpleado.setEditable(false);
		textDniNifEmpleado.setBounds(140, 30, 264, 20);
		panelEmpleado.add(textDniNifEmpleado);
		textDniNifEmpleado.setColumns(10);

		textNombreEmpleado = new JTextField();
		textNombreEmpleado.setEditable(false);
		textNombreEmpleado.setColumns(10);
		textNombreEmpleado.setBounds(140, 55, 264, 20);
		panelEmpleado.add(textNombreEmpleado);

		textSueldoEmpleado = new JTextField();
		textSueldoEmpleado.setEditable(false);
		textSueldoEmpleado.setColumns(10);
		textSueldoEmpleado.setBounds(140, 80, 264, 20);
		panelEmpleado.add(textSueldoEmpleado);

		JButton buttonCambiarBusqueda = new JButton("");
		buttonCambiarBusqueda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					buttonBuscarPorCampoActionPerformed(e, tipo, textClave.getText());
				} catch (SQLException sqle) {
					UtilVentanas.Alertas.mostrar(UtilVentanas.Alertas.ERROR_SQL, sqle.toString());
				} catch (IOException ioe) {
					UtilVentanas.Alertas.mostrar(UtilVentanas.Alertas.ERROR_IOE, ioe.toString());
				} catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		buttonCambiarBusqueda.setIcon(new ImageIcon("images\\lupa_16.png"));
		buttonCambiarBusqueda.setBounds(376, 11, 25, 25);
		contentPane.add(buttonCambiarBusqueda);

		JButton buttonBorrar = new JButton("");
		buttonBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					buttonBorrarActionPerformed(tipo);
				} catch (IndexOutOfBoundsException iobe) {
					UtilVentanas.Alertas.mostrar(UtilVentanas.Alertas.ERROR_IOB, iobe.toString());
				} catch (SQLException sqle) {
					UtilVentanas.Alertas.mostrar(UtilVentanas.Alertas.ERROR_SQL, sqle.toString());
				} catch (IOException ioe) {
					UtilVentanas.Alertas.mostrar(UtilVentanas.Alertas.ERROR_IOE, ioe.toString());
				}
			}
		});
		buttonBorrar.setIcon(new ImageIcon("images\\papelera_16.png"));
		buttonBorrar.setBounds(20, 200, 25, 25);
		contentPane.add(buttonBorrar);

		panelCamion.setVisible(false);
		panelEmpleado.setVisible(false);
		panelEmpresa.setVisible(true);
		textosEmpresas.add(textNombreEmpresa);
		textosEmpresas.add(textNifEmpresa);
		textosEmpresas.add(textTelefonoEmpresa);
		textosCamiones.add(textMatriculaCamion);
		textosCamiones.add(textNumeroBastidorCamion);
		textosCamiones.add(textVolumenCajaCamion);
		textosEmpleados.add(textDniNifEmpleado);
		textosEmpleados.add(textNombreEmpleado);
		textosEmpleados.add(textSueldoEmpleado);

		labelClave = new JLabel("NIF:");
		labelClave.setBounds(140, 14, 98, 14);
		contentPane.add(labelClave);

		textClave = new JTextField();
		textClave.setBounds(211, 14, 155, 20);
		contentPane.add(textClave);
		textClave.setColumns(10);
		comboBoxTipo.setSelectedIndex(0);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Datos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(429, 11, 321, 204);
		contentPane.add(panel);
		panel.setLayout(null);

		this.tDatos = new JTextArea();
		tDatos.setEditable(false);
		tDatos.setBounds(10, 21, 300, 172);
		JScrollPane sp = new JScrollPane(tDatos);
		sp.setBounds(10, 21, 300, 172);
		panel.add(sp);

		JButton bVerTodo = new JButton("Ver todos");
		bVerTodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					bverTodoActionPerformed(tipo);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		bVerTodo.setBounds(55, 200, 183, 23);
		contentPane.add(bVerTodo);

		co = new ContextoOrdenacion(empresas, esE);
		contentPane.add(comboOrdenacion);

		JLabel lblParmetroOrdenacin = new JLabel("Par\u00E1metro ordenaci\u00F3n:");
		lblParmetroOrdenacin.setBounds(259, 185, 165, 14);
		contentPane.add(lblParmetroOrdenacin);
	}

	private void formWindowClosing(java.awt.event.WindowEvent evt, VentanaPrincipal principal) {
		this.setVisible(false);
		principal.setVisible(true);
	}

	private void cambiarEtiquetasComboOrd() {
		switch (comboBoxTipo.getSelectedItem().toString()) {
		case "Empresa": {
			co = new ContextoOrdenacion(empresas, esE);
			comboOrdenacion.setModel(new DefaultComboBoxModel(new String[] { "NIF", "direccion", "telefono" }));
			break;
		}
		case "Cami\u00F3n": {
			co = new ContextoOrdenacion(camiones, esC);
			comboOrdenacion.setModel(new DefaultComboBoxModel(new String[] { "bastidor", "matricula", "km" }));
			break;
		}
		case "Empleado": {
			co = new ContextoOrdenacion(empleados, esT);
			comboOrdenacion.setModel(new DefaultComboBoxModel(new String[] { "nombre", "apellidos", "DNI" }));
		}
		default: {

			co = new ContextoOrdenacion(empleados, esT);
			comboOrdenacion.setModel(new DefaultComboBoxModel(new String[] { "nombre", "apellidos", "DNI" }));

			break;
		}
		}

	}

	private void bverTodoActionPerformed(String tipo) throws SQLException {
		switch (tipo) {
		case "Empresa": {
			agregado = new AgregadoConcreto(empresas);
			iterador = agregado.crearIterador();
			String datos = "";
			while (iterador.hayMas()) {
				Empresa e = (Empresa) iterador.elementoActual();
				datos += " NIF empresa: " + e.getNif() + "\n N. empresa: " + e.getEmpresa() + "\n ················\n";
				iterador.siguiente();
			}
			tDatos.setText(datos);
			break;
		}
		case "Cami\u00F3n": {
			agregado = new AgregadoConcreto(camiones);
			iterador = agregado.crearIterador();
			String datos = "";
			while (iterador.hayMas()) {
				Camion c = (Camion) iterador.elementoActual();
				datos += " Nº bastidor: " + c.getnBastidor() + "\n Matrícula: " + c.getMatricula()
						+ "\n ················\n";
				iterador.siguiente();
			}
			tDatos.setText(datos);
			break;
		}
		case "Empleado": {
			agregado = new AgregadoConcreto(empleados);
			iterador = agregado.crearIterador();
			String datos = "";
			while (iterador.hayMas()) {
				Trabajador t = (Trabajador) iterador.elementoActual();
				datos += " DNI empleado: " + t.getDni() + "\n N. empleado: "
						+ t.getNombre().concat(" " + t.getApellidos()) + "\n ················\n";
				iterador.siguiente();
			}
			tDatos.setText(datos);
			break;
		}
		default: {
			agregado = new AgregadoConcreto(empleados);
			iterador = agregado.crearIterador();
			String datos = "";
			while (iterador.hayMas()) {
				Trabajador t = (Trabajador) iterador.elementoActual();
				datos += "DNI empleado: " + t.getDni() + "\nN. empleado: "
						+ t.getNombre().concat(" " + t.getApellidos()) + "\n ················\n";
				iterador.siguiente();
			}
			tDatos.setText(datos);
			break;
		}
		}

	}

	private void comboOrdenacionActionPerformed() throws SQLException {
		switch (comboBoxTipo.getSelectedItem().toString()) {
		case "Cami\u00F3n": {
			if (comboOrdenacion.getSelectedItem().toString().equalsIgnoreCase("bastidor"))
				camiones = new ArrayList<Object>(co.ejecutarEstrategia("bastidor"));
			else if (comboOrdenacion.getSelectedItem().toString().equalsIgnoreCase("matricula"))
				camiones = new ArrayList<Object>(co.ejecutarEstrategia("matricula"));
			else
				camiones = new ArrayList<Object>(co.ejecutarEstrategia("km"));

			break;
		}
		case "Empresa": {
			if (comboOrdenacion.getSelectedItem().toString().equalsIgnoreCase("NIF"))
				empresas = new ArrayList<Object>(co.ejecutarEstrategia("NIF"));
			else if (comboOrdenacion.getSelectedItem().toString().equalsIgnoreCase("direccion"))
				empresas = new ArrayList<Object>(co.ejecutarEstrategia("direccion"));
			else
				empresas = new ArrayList<Object>(co.ejecutarEstrategia("telefono"));
			break;
		}
		case "Empleado": {
			if (comboOrdenacion.getSelectedItem().toString().equalsIgnoreCase("nombre"))
				empleados = new ArrayList<Object>(co.ejecutarEstrategia("nombre"));
			else if (comboOrdenacion.getSelectedItem().toString().equalsIgnoreCase("apellidos"))
				empleados = new ArrayList<Object>(co.ejecutarEstrategia("apellidos"));
			else
				empleados = new ArrayList<Object>(co.ejecutarEstrategia("DNI"));
			break;
		}
		default: {
			if (comboOrdenacion.getSelectedItem().toString().equalsIgnoreCase("bastidor"))
				camiones = new ArrayList<Object>(co.ejecutarEstrategia("bastidor"));
			else if (comboOrdenacion.getSelectedItem().toString().equalsIgnoreCase("matricula"))
				camiones = new ArrayList<Object>(co.ejecutarEstrategia("matricula"));
			else
				camiones = new ArrayList<Object>(co.ejecutarEstrategia("km"));

			break;
		}
		}

	}

	private void buttonBuscarPorCampoActionPerformed(java.awt.event.ActionEvent evt, String tipo, String clave)
			throws SQLException, IOException, NoSuchAlgorithmException {
		if (!textClave.getText().isEmpty()) {
			switch (tipo) {
			case "Empresa": {
				Empresa empresaAux = (Empresa) fc.crearCRUD(FactoriaCRUD.TIPO_EMPRESA).buscarUno(clave);
				if (empresaAux != null) {
					textNifEmpresa.setText(empresaAux.getNif());
					textNombreEmpresa.setText(empresaAux.getEmpresa());
					textTelefonoEmpresa.setText(String.valueOf(empresaAux.getnTelefono()));
				} else
					UtilVentanas.Alertas.mostrar(UtilVentanas.Alertas.ERROR_SQL, "No existe en la base de datos.");
				break;

			}
			case "Cami\u00F3n": {

				Camion camionAux = (Camion) fc.crearCRUD(FactoriaCRUD.TIPO_CAMION).buscarUno(clave);
				if (camionAux != null) {
					textMatriculaCamion.setText(camionAux.getMatricula());
					textNumeroBastidorCamion.setText(camionAux.getnBastidor());
					textVolumenCajaCamion.setText(String.valueOf(camionAux.getVolumenCaja()));
				} else
					UtilVentanas.Alertas.mostrar(UtilVentanas.Alertas.ERROR_SQL, "No existe en la base de datos.");
				break;
			}
			case "Empleado": {
				Trabajador empleadoAux = (Trabajador) fc.crearCRUD(FactoriaCRUD.TIPO_EMPLEADO).buscarUno(clave);
				if (empleadoAux != null) {
					textDniNifEmpleado.setText(empleadoAux.getDni());
					textNombreEmpleado.setText(empleadoAux.getNombre() + " " + empleadoAux.getApellidos());
					textSueldoEmpleado.setText(String.valueOf(empleadoAux.getSueldo()));
				} else
					UtilVentanas.Alertas.mostrar(UtilVentanas.Alertas.ERROR_SQL, "No existe en la base de datos.");
				break;
			}
			}
		} else {
			UtilVentanas.Alertas.mostrar(UtilVentanas.Alertas.ERROR_CAMPOS_INCOMPLETOS, "");
		}
	}


	
	
	private void comboBoxTipoCambiarSeleccion(java.awt.event.ActionEvent evt, String tipo) throws SQLException {
		switch (tipo) {
		case "Empresa": {
			labelClave.setText("Nif:");
			if (empresas.isEmpty()) {
				UtilVentanas.borrarTextos(textos);
			}
			panelCamion.setVisible(false);
			panelEmpleado.setVisible(false);
			panelEmpresa.setVisible(true);
			break;
		}
		case "Camión": {
			labelClave.setText("Nº Bastidor:");
			if (camiones.isEmpty()) {
				UtilVentanas.borrarTextos(textos);
			}
			panelEmpleado.setVisible(false);
			panelEmpresa.setVisible(false);
			panelCamion.setVisible(true);
			break;
		}
		case "Empleado": {
			labelClave.setText("DNI:");
			if (empleados.isEmpty()) {
				UtilVentanas.borrarTextos(textos);
			}
			panelCamion.setVisible(false);
			panelEmpresa.setVisible(false);
			panelEmpleado.setVisible(true);
			break;
		}
		}
	}

	private void buttonBorrarActionPerformed(String tipo) throws SQLException, IndexOutOfBoundsException, IOException {

		switch (tipo) {
		case "Empresa": {
			if (UtilVentanas.textosIncompletos(textosEmpresas)) {
				fc.crearCRUD(FactoriaCRUD.TIPO_EMPRESA).borrar(textNifEmpresa.getText());

				JOptionPane.showMessageDialog(null,
						"Empresa con Nif " + textNifEmpresa.getText() + " ha sido borrada.");

			} else {
				JOptionPane.showMessageDialog(null, "No hay elementos para borrar");
			}
			UtilVentanas.borrarTextos(textosEmpresas);
			tDatos.setText("");
			textClave.setText("");
			co = new ContextoOrdenacion(ce.buscarTodo(), esE);
			empresas = new ArrayList<Object>(co.ejecutarEstrategia("NIF"));
			break;
		}
		case "Cami\u00F3n": {
			if (UtilVentanas.textosIncompletos(textosCamiones)) {
				fc.crearCRUD(FactoriaCRUD.TIPO_CAMION).borrar(textMatriculaCamion.getText());

				JOptionPane.showMessageDialog(null,
						"Camión con número de bastidor " + textMatriculaCamion.getText() + " ha sido borrada.");

			} else {
				JOptionPane.showMessageDialog(null, "No hay elementos para borrar");
			}
			UtilVentanas.borrarTextos(textosCamiones);
			tDatos.setText("");
			textClave.setText("");
			co = new ContextoOrdenacion(cc.buscarTodo(), esC);
			camiones = new ArrayList<Object>(co.ejecutarEstrategia("bastidor"));
			break;
		}
		case "Empleado": {
			if (UtilVentanas.textosIncompletos(textosEmpleados)) {
				fc.crearCRUD(FactoriaCRUD.TIPO_EMPLEADO).borrar(textDniNifEmpleado.getText());

				JOptionPane.showMessageDialog(null,
						"Empleado con Dni " + textDniNifEmpleado.getText() + " ha sido borrada.");

			} else {
				JOptionPane.showMessageDialog(null, "No hay elementos para borrar");
			}
			UtilVentanas.borrarTextos(textosEmpleados);
			textClave.setText("");
			tDatos.setText("");
			co = new ContextoOrdenacion(ct.buscarTodo(), esT);
			empleados = new ArrayList<Object>(co.ejecutarEstrategia("nombre"));
			break;
		}
		}
	}

}
