package interfacesGraficas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import composite.AccesoEmpleados;
import factorias.FactoriaCRUD;
import factorias.FactoriaTrabajador;
import iterador.Agregado;
import iterador.AgregadoConcreto;
import iterador.Iterador;
import operacionesCRUD.CRUDempleados;
import operacionesCRUD.CRUDsubordinados;
import pojo.Subordinado;
import pojo.Trabajador;
import util.UtilVentanas;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
/**
 * Ventana para la visualización de la cuantía total de los sueldo y para la visualización del árbol de personal.
 */
/**
 * 
 * @author Jorge González Rodríguez y Javier Ruiz Rodríguez
 *
 */
public class VentanaVisualizacionComposite extends JFrame {

	private JPanel contentPane;
	private AccesoEmpleados ae;
	private JComboBox comboBoxDNIJefe;
	private String dniJefe;
	private JTextPane tResultado;
	private FactoriaCRUD fc;
	private FactoriaTrabajador ft;
	private CRUDsubordinados cs;
	private ArrayList<Object> subordinados;

	public VentanaVisualizacionComposite(VentanaPrincipal principal) throws SQLException, IOException {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
		        formWindowClosing(principal);
			}
		});
		setTitle("ESTAD\u00CDSTICAS GENERALES EMPLEADOS");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 486, 458);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(
				new TitledBorder(null, "Visualizaci\u00F3n datos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 138, 449, 270);
		contentPane.add(panel);
		panel.setLayout(null);

		this.tResultado = new JTextPane();
		this.tResultado.setEditable(false);
		this.tResultado.setBounds(10, 21, 429, 269);
		panel.add(tResultado);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Seleccionar operaci\u00F3n", TitledBorder.LEADING, TitledBorder.TOP,
				null, null));
		panel_1.setBounds(10, 11, 450, 85);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JButton bSueldos = new JButton("Cuant\u00EDa total sueldos empresa");
		bSueldos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					bSueldosActionPerformed(evt);
				} catch (SQLException sqle) {
					UtilVentanas.Alertas.mostrar(UtilVentanas.Alertas.ERROR_SQL,sqle.toString());
				} catch (IOException ioe) {
					UtilVentanas.Alertas.mostrar(UtilVentanas.Alertas.ERROR_IOE,ioe.toString());
				}
			}
		});
		bSueldos.setBounds(10, 21, 205, 53);
		panel_1.add(bSueldos);

		JButton tJerarquia = new JButton("Jerarqu\u00EDa personal empresa");
		tJerarquia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					bJerarquiaActionPerformed(evt);
				} catch (SQLException sqle) {
					UtilVentanas.Alertas.mostrar(UtilVentanas.Alertas.ERROR_SQL,sqle.toString());
				} catch (IOException ioe) {
					UtilVentanas.Alertas.mostrar(UtilVentanas.Alertas.ERROR_IOE,ioe.toString());
				}
			}
		});
		tJerarquia.setBounds(235, 21, 205, 53);
		panel_1.add(tJerarquia);
		
		JLabel lblDniJefe = new JLabel("Dni Jefe:");
		lblDniJefe.setBounds(20, 110, 61, 14);
		contentPane.add(lblDniJefe);
		
		comboBoxDNIJefe = new JComboBox();
		comboBoxDNIJefe.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent ae) {
	    		seleccionarJefe();
		    }
		});
		comboBoxDNIJefe.setBounds(75, 107, 135, 20);
		contentPane.add(comboBoxDNIJefe);
		
		this.fc = new FactoriaCRUD();
		this.ft = new FactoriaTrabajador();
		this.cs = (CRUDsubordinados) fc.crearCRUD(FactoriaCRUD.TIPO_SUBORDINADO);
		this.subordinados = new ArrayList<Object>(cs.buscarTodo());
		Agregado agregado = new AgregadoConcreto(subordinados);
		Iterador iterador = agregado.crearIterador();
		while(iterador.hayMas()){
			comboBoxDNIJefe.addItem(((Subordinado) iterador.elementoActual()).getDniJefe());
			iterador.siguiente();
		}
	}

	private void bSueldosActionPerformed(ActionEvent evt) throws SQLException, IOException {
//		this.dniJefe = seleccionarJefe();
		this.ae = new AccesoEmpleados(this.dniJefe);
		tResultado.setText("La cuantía total de los sueldo de todos los empleados registrados en el sistema es de  ·······  "
				+ String.valueOf(ae.getSueldos()) + " €.");
	}
	
	private void bJerarquiaActionPerformed(ActionEvent evt) throws SQLException, IOException {
//		this.dniJefe = seleccionarJefe();
		this.ae = new AccesoEmpleados(this.dniJefe);
		tResultado.setText(ae.getDescripciones());
	}
	
	public void seleccionarJefe(){
		this.dniJefe = comboBoxDNIJefe.getSelectedItem().toString();
	}
	
	private void formWindowClosing(VentanaPrincipal principal) {
	    this.setVisible(false);
	    principal.setVisible(true);
	}
}
