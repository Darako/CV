package interfacesGraficas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import factorias.FactoriaCRUD;
import factorias.FactoriaEmpresa;
import factorias.FactoriaVehiculo;
import operacionesCRUD.CRUDempresa;
import pojo.Empresa;
import util.UtilVentanas;

/**
 * Ventana para la insercción de datos de empresas.
 */
/**
 * 
 * @author Jorge González Rodríguez y Javier Ruiz Rodríguez
 *
 */
public class VentanaEmpresa extends JFrame {

	private JPanel contentPane;
	private JTextField textNombre;
	private JTextField textNif;
	private JTextField textDireccion;
	private JTextField textTelefono;
	private JTextField textMail;	
	private JButton buttonAceptar;
	private JButton buttonBorrar;
	private FactoriaEmpresa fe;
	private FactoriaCRUD fc;
	private ArrayList<JTextField> textos = new ArrayList<JTextField>();
	
	public VentanaEmpresa(VentanaGestion gestion) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				formWindowClosing(gestion);
			}
		});
		setTitle("Facturame --- Empresa");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 482, 185);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelNombre = new JLabel("Nombre:");
		labelNombre.setBounds(10, 10, 50, 20);
		contentPane.add(labelNombre);
		
		textNombre = new JTextField();
		textNombre.setBounds(70, 10, 120, 20);
		contentPane.add(textNombre);
		textNombre.setColumns(10);
		
		JLabel labelNif = new JLabel("NIF:");
		labelNif.setBounds(10, 40, 61, 20);
		contentPane.add(labelNif);
		
		textNif = new JTextField();
		textNif.setColumns(10);
		textNif.setBounds(70, 40, 120, 20);
		contentPane.add(textNif);
		
		JLabel labelDireccion = new JLabel("Direcci\u00F3n:");
		labelDireccion.setBounds(210, 10, 61, 20);
		contentPane.add(labelDireccion);
		
		textDireccion = new JTextField();
		textDireccion.setColumns(10);
		textDireccion.setBounds(281, 10, 120, 20);
		contentPane.add(textDireccion);
		
		JLabel labelTelefono = new JLabel("Tel\u00E9fono:");
		labelTelefono.setBounds(210, 40, 61, 20);
		contentPane.add(labelTelefono);
		
		textTelefono = new JTextField();
		textTelefono.setColumns(10);
		textTelefono.setBounds(281, 40, 120, 20);
		contentPane.add(textTelefono);
		
		JLabel labelMail = new JLabel("Mail:");
		labelMail.setBounds(210, 71, 61, 20);
		contentPane.add(labelMail);
		
		textMail = new JTextField();
		textMail.setColumns(10);
		textMail.setBounds(281, 71, 120, 20);
		contentPane.add(textMail);
				
		buttonAceptar = new JButton("ACEPTAR");
		buttonAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					crearEmpresa();
				} catch (SQLException sqle) {
					UtilVentanas.Alertas.mostrar(UtilVentanas.Alertas.ERROR_SQL,sqle.toString());
				} catch (IOException ioe) {
					UtilVentanas.Alertas.mostrar(UtilVentanas.Alertas.ERROR_IOE,ioe.toString());
				}
			}
		});
		buttonAceptar.setBounds(70, 108, 201, 25);
		contentPane.add(buttonAceptar);
		
		buttonBorrar = new JButton("");
		buttonBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UtilVentanas.borrarTextos(textos);				
			}
		});
		buttonBorrar.setIcon(new ImageIcon("images\\papelera_16.png"));
		buttonBorrar.setBounds(314, 108, 25, 25);
		contentPane.add(buttonBorrar);
		

		textos.add(textNombre);
		textos.add(textNif);
		textos.add(textDireccion);
		textos.add(textTelefono);		
		textos.add(textMail);
		this.fe = new FactoriaEmpresa();
		this.fc = new FactoriaCRUD();
	}
	
	private void formWindowClosing(VentanaGestion gestion) {
        this.setVisible(false);
        gestion.setVisible(true);
    }
	
	private void crearEmpresa() throws SQLException, IOException{
		if(UtilVentanas.textosIncompletos(textos)){
			Empresa empresa = fe.crearEmpresa();
			empresa.setNif(textNif.getText());
			empresa.setEmpresa(textNombre.getText());
			empresa.setDireccion(textDireccion.getText());
			empresa.setnTelefono(Integer.parseInt(textTelefono.getText()));
			empresa.setEmail(textMail.getText());
			
			fc.crearCRUD(FactoriaCRUD.TIPO_EMPRESA).insertarActualizar(empresa, true);

			UtilVentanas.Alertas.mostrar(UtilVentanas.Alertas.EXITO_INSERT, "empresa");
		} else {
			UtilVentanas.Alertas.mostrar(UtilVentanas.Alertas.ERROR_CAMPOS_INCOMPLETOS,"");
		}
	}

}
