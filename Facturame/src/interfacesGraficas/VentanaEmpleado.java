package interfacesGraficas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.Date;
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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import adaptadorFecha.AdaptadorFechaPostgres;
import adaptadorFecha.Fecha;
import adaptadorFecha.FechaEs;
import factorias.FactoriaCRUD;
import factorias.FactoriaTrabajador;
import pojo.Trabajador;
import util.UtilVentanas;
/**
 * Ventana para la insercción de datos de empleados.
 */
/**
 * 
 * @author Jorge González Rodríguez y Javier Ruiz Rodríguez
 *
 */
public class VentanaEmpleado extends JFrame {

	private Fecha fechaAlta;
	private Fecha fechaNacimiento;
	private JPanel contentPane;
	private JTextField textDniNif;
	private JTextField textNombre;
	private JTextField textApellidos;
	private JTextField textFechaNacimiento;
	private JTextField textFechaAltaEmpleado;
	private JTextField textSueldo;
	private JTextField textRango;
	private JComboBox comboBoxSexo;
	private FactoriaTrabajador ft;
	private FactoriaCRUD fc;
	private ArrayList<JTextField> textos = new ArrayList<JTextField>();

	public VentanaEmpleado(VentanaGestion ventanaGestion) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				formWindowClosing(e, ventanaGestion);
			}
		});
		setTitle("Facturame --- Empleado");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 555, 270);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelDatosPersonales = new JPanel();
		panelDatosPersonales.setBorder(
				new TitledBorder(null, "Datos Personales", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelDatosPersonales.setBounds(10, 10, 520, 80);
		contentPane.add(panelDatosPersonales);
		panelDatosPersonales.setLayout(null);

		JLabel labelDniNif = new JLabel("DNI/NIF:");
		labelDniNif.setBounds(10, 20, 68, 15);
		panelDatosPersonales.add(labelDniNif);

		textDniNif = new JTextField();
		textDniNif.setBounds(60, 15, 86, 20);
		panelDatosPersonales.add(textDniNif);
		textDniNif.setColumns(10);

		JLabel labelNombre = new JLabel("Nombre:");
		labelNombre.setBounds(10, 50, 73, 15);
		panelDatosPersonales.add(labelNombre);

		textNombre = new JTextField();
		textNombre.setColumns(10);
		textNombre.setBounds(60, 45, 120, 20);
		panelDatosPersonales.add(textNombre);

		JLabel labelApellidos = new JLabel("Apellidos:");
		labelApellidos.setBounds(190, 50, 120, 15);
		panelDatosPersonales.add(labelApellidos);

		textApellidos = new JTextField();
		textApellidos.setColumns(10);
		textApellidos.setBounds(248, 45, 262, 20);
		panelDatosPersonales.add(textApellidos);

		JLabel labelSexo = new JLabel("Sexo:");
		labelSexo.setBounds(155, 20, 56, 15);
		panelDatosPersonales.add(labelSexo);

		comboBoxSexo = new JComboBox();
		comboBoxSexo.setModel(new DefaultComboBoxModel(new String[] { "Femenino", "Masculino" }));
		comboBoxSexo.setBounds(195, 17, 80, 20);
		panelDatosPersonales.add(comboBoxSexo);

		JLabel labelFechaNacimiento = new JLabel("Fecha de nacimiento:");
		labelFechaNacimiento.setBounds(290, 20, 130, 15);
		panelDatosPersonales.add(labelFechaNacimiento);

		textFechaNacimiento = new JTextField();
		textFechaNacimiento.setColumns(10);
		textFechaNacimiento.setBounds(420, 15, 90, 20);
		panelDatosPersonales.add(textFechaNacimiento);

		JPanel panelDatosEmpleado = new JPanel();
		panelDatosEmpleado.setBorder(
				new TitledBorder(null, "Datos Empleado", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelDatosEmpleado.setBounds(10, 101, 520, 80);
		contentPane.add(panelDatosEmpleado);
		panelDatosEmpleado.setLayout(null);

		JLabel labelFechaAltaEmpleado = new JLabel("Fecha de alta:");
		labelFechaAltaEmpleado.setBounds(240, 51, 95, 15);
		panelDatosEmpleado.add(labelFechaAltaEmpleado);

		textFechaAltaEmpleado = new JTextField();
		textFechaAltaEmpleado.setColumns(10);
		textFechaAltaEmpleado.setBounds(320, 46, 90, 20);
		panelDatosEmpleado.add(textFechaAltaEmpleado);

		JLabel labelSueldo = new JLabel("Sueldo:");
		labelSueldo.setBounds(10, 51, 47, 15);
		panelDatosEmpleado.add(labelSueldo);

		textSueldo = new JTextField();
		textSueldo.setColumns(10);
		textSueldo.setBounds(60, 46, 86, 20);
		panelDatosEmpleado.add(textSueldo);

		JLabel labelRango = new JLabel("Rango:");
		labelRango.setBounds(10, 20, 47, 15);
		panelDatosEmpleado.add(labelRango);

		textRango = new JTextField();
		textRango.setColumns(10);
		textRango.setBounds(60, 15, 450, 20);
		panelDatosEmpleado.add(textRango);

		JButton buttonAceptar = new JButton("ACEPTAR");
		buttonAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					crearEmpleado();
				} catch (SQLException sqle) {
					UtilVentanas.Alertas.mostrar(UtilVentanas.Alertas.ERROR_SQL, sqle.toString());
				} catch (IOException ioe) {
					UtilVentanas.Alertas.mostrar(UtilVentanas.Alertas.ERROR_IOE, ioe.toString());
				}
			}
		});
		buttonAceptar.setBounds(89, 195, 222, 25);
		contentPane.add(buttonAceptar);

		JButton buttonBorrar = new JButton("");
		buttonBorrar.setIcon(new ImageIcon("images\\papelera_16.png"));
		buttonBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UtilVentanas.borrarTextos(textos);
			}
		});
		buttonBorrar.setBounds(385, 195, 25, 25);
		contentPane.add(buttonBorrar);

		textos.add(textDniNif);
		textos.add(textNombre);
		textos.add(textApellidos);
		textos.add(textFechaNacimiento);
		textos.add(textFechaAltaEmpleado);
		textos.add(textSueldo);
		textos.add(textSueldo);
		textos.add(textRango);
		this.ft = new FactoriaTrabajador();
		this.fc = new FactoriaCRUD();
	}

	private void formWindowClosing(java.awt.event.WindowEvent evt, VentanaGestion ventanaGestion) {
		this.setVisible(false);
		ventanaGestion.setVisible(true);
	}

	private void crearEmpleado() throws SQLException, IOException {
		fechaAlta = new AdaptadorFechaPostgres(new FechaEs(textFechaAltaEmpleado.getText()));
		fechaNacimiento = new AdaptadorFechaPostgres(new FechaEs(textFechaAltaEmpleado.getText()));
		String fechaAltaFormateada = fechaAlta.toString();
		String fechaNacimientoFormateada = fechaNacimiento.toString();
		if (UtilVentanas.textosIncompletos(textos)) {
			if (!fechaAltaFormateada.equalsIgnoreCase("0-0-0") || !fechaNacimientoFormateada.equalsIgnoreCase("0-0-0")) {
				Trabajador trabajador = ft.crearTrabajador();
				trabajador.setApellidos(textApellidos.getText());
				trabajador.setDni(textDniNif.getText());
				trabajador.setFechaAltaEmpleado(Date.valueOf(fechaAltaFormateada));
				trabajador.setFechaNacimiento(Date.valueOf(fechaNacimientoFormateada));
				trabajador.setNombre(textNombre.getText());
				trabajador.setRango(textRango.getText());
				trabajador.setSexo(comboBoxSexo.getSelectedItem().toString());
				trabajador.setSueldo(Integer.parseInt(textSueldo.getText()));

				fc.crearCRUD(FactoriaCRUD.TIPO_EMPLEADO).insertarActualizar(trabajador, true);
				UtilVentanas.Alertas.mostrar(UtilVentanas.Alertas.EXITO_INSERT, "empleado");
			} else 
				UtilVentanas.borrarTextos(textos);
		} else {
			JOptionPane.showMessageDialog(null, "Faltan campos por rellenar");
		}
	}

}
