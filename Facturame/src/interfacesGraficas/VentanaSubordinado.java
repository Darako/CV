package interfacesGraficas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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
import javax.swing.JButton;
import javax.swing.ImageIcon;
/**
 * Ventana para la inserción de subordinados.
 */
/**
 * 
 * @author Jorge González Rodríguez y Javier Ruiz Rodríguez
 *
 */
public class VentanaSubordinado extends JFrame {

	private JPanel contentPane;
	private JComboBox comboBoxSubordinado;
	private JComboBox comboBoxJefe;
	private FactoriaCRUD fc;
	private FactoriaTrabajador ft;
	private CRUDsubordinados cs;
	private CRUDempleados ce;
	private ArrayList<Object> empleados;
	private String dniSubordinado;
	private String dniJefe;

	public VentanaSubordinado(VentanaGestion gestion) throws SQLException, IOException {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				formWindowClosing(gestion);
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 267, 172);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelJefe = new JLabel("Seleccionar Jefe");
		labelJefe.setBounds(10, 11, 138, 14);
		contentPane.add(labelJefe);
		
		JLabel lblSeleccionarSubordinado = new JLabel("Seleccionar Subordinado");
		lblSeleccionarSubordinado.setBounds(10, 67, 138, 14);
		contentPane.add(lblSeleccionarSubordinado);
		
		comboBoxJefe = new JComboBox();
		comboBoxJefe.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
				seleccionarJefe();
		    }
		});
		comboBoxJefe.setBounds(10, 36, 150, 20);
		contentPane.add(comboBoxJefe);
		
		comboBoxSubordinado = new JComboBox();
		comboBoxSubordinado.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent ae) {
	    		try {
					seleccionarSubordinado();
				} catch (SQLException sqle) {
					UtilVentanas.Alertas.mostrar(UtilVentanas.Alertas.ERROR_SQL,sqle.toString());
				}
		    }
		});
		comboBoxSubordinado.setBounds(10, 92, 150, 20);
		contentPane.add(comboBoxSubordinado);
		
		JButton buttonAceptar = new JButton("");
		buttonAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {
					clickAceptar();
				} catch (SQLException sqle) {
					UtilVentanas.Alertas.mostrar(UtilVentanas.Alertas.ERROR_SQL, sqle.toString());
				}
			}
		});
		buttonAceptar.setIcon(new ImageIcon("images\\guardar_32.png"));
		buttonAceptar.setBounds(188, 49, 50, 50);
		contentPane.add(buttonAceptar);
		
		this.fc = new FactoriaCRUD();
		this.ft = new FactoriaTrabajador();
		this.cs = (CRUDsubordinados) fc.crearCRUD(FactoriaCRUD.TIPO_SUBORDINADO);
		this.ce = (CRUDempleados) fc.crearCRUD(FactoriaCRUD.TIPO_EMPLEADO);	
		this.empleados = new ArrayList<Object>(ce.buscarTodo());
		Agregado agregado = new AgregadoConcreto(empleados);
		Iterador iterador = agregado.crearIterador();
		while(iterador.hayMas()){
			comboBoxJefe.addItem(((Trabajador) iterador.elementoActual()).getDni());
			comboBoxSubordinado.addItem(((Trabajador) iterador.elementoActual()).getDni());
			iterador.siguiente();
		}
		
	}
	
	private void formWindowClosing(VentanaGestion gestion) {
        this.setVisible(false);
        gestion.setVisible(true);
    }
	
	public void clickAceptar() throws SQLException{
		if(!this.dniJefe.equals(this.dniSubordinado)){
			Subordinado sub = (Subordinado) ft.crearSubordinado();
			sub.setDniJefe(dniJefe);
			sub.setDniSubordinado(dniSubordinado);
			cs.insertarActualizar(sub, true);
			UtilVentanas.Alertas.mostrar(UtilVentanas.Alertas.EXITO_INSERT, "subordinado");
		} else {
			UtilVentanas.Alertas.mostrar(UtilVentanas.Alertas.ERROR_JEFE_EMPLEADO_IGUAL, "");
		}
	}
	
	public void seleccionarJefe(){
		this.dniJefe = comboBoxJefe.getSelectedItem().toString();
	}
	
	public void seleccionarSubordinado() throws SQLException{
		this.dniSubordinado = comboBoxSubordinado.getSelectedItem().toString();
	}
}
