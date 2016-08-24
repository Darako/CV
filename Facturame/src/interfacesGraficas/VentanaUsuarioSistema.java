package interfacesGraficas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.rmi.CORBA.Util;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import factorias.FactoriaCRUD;
import factorias.FactoriaUsuarioSistema;
import operacionesCRUD.CRUDusuariosSistema;
import pojo.UsuarioSistema;
import util.UtilVentanas;
/**
 * Ventana para la inserción de usuarios del sistema.
 */
/**
 * 
 * @author Jorge González Rodríguez y Javier Ruiz Rodríguez
 *
 */
public class VentanaUsuarioSistema extends JFrame {

	private FactoriaCRUD fc;
	private CRUDusuariosSistema cus;
	private JPanel contentPane;
	private JTextField textNombre;
	private JTextField textDni;
	private ArrayList<JTextField> textos = new ArrayList<JTextField>();
	private JCheckBox checkBoxAdministrador;
	private FactoriaUsuarioSistema fus;
	private JTextField tPass;

	public VentanaUsuarioSistema(VentanaGestion gestion) throws SQLException, IOException {
		this.fus = new FactoriaUsuarioSistema();
		this.fc = new FactoriaCRUD();
		this.cus = (CRUDusuariosSistema) fc.crearCRUD(FactoriaCRUD.TIPO_US_SISTEMA);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				formWindowClosing(gestion);
			}
		});
		setTitle("Facturame --- Usuario de Sistema");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 320, 199);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel labelNombre = new JLabel("Nombre:");
		labelNombre.setBounds(50, 9, 69, 20);
		contentPane.add(labelNombre);

		textNombre = new JTextField();
		textNombre.setBounds(129, 4, 120, 20);
		contentPane.add(textNombre);
		textNombre.setColumns(10);

		JLabel labelDni = new JLabel("DNI:");
		labelDni.setBounds(50, 39, 69, 20);
		contentPane.add(labelDni);

		textDni = new JTextField();
		textDni.setColumns(10);
		textDni.setBounds(129, 34, 120, 20);
		contentPane.add(textDni);

		JLabel labelAdministrador = new JLabel("Administrador:");
		labelAdministrador.setBounds(50, 97, 82, 20);
		contentPane.add(labelAdministrador);

		checkBoxAdministrador = new JCheckBox("");
		checkBoxAdministrador.setBounds(129, 92, 25, 25);
		contentPane.add(checkBoxAdministrador);

		JButton buttonAceptar = new JButton("ACEPTAR");
		buttonAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					crearUsuarioSistema(e);
				} catch (SQLException sqle) {
					UtilVentanas.Alertas.mostrar(UtilVentanas.Alertas.ERROR_SQL,sqle.toString());
				} catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		buttonAceptar.setBounds(50, 128, 153, 25);
		contentPane.add(buttonAceptar);

		JButton buttonBorrar = new JButton("");
		buttonBorrar.setIcon(new ImageIcon("images\\papelera_16.png"));
		buttonBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bBorrarActionPerformed(e);
			}
		});
		buttonBorrar.setBounds(224, 128, 25, 25);
		contentPane.add(buttonBorrar);

		textos.add(textNombre);
		textos.add(textDni);
		
		JLabel lblNewLabel = new JLabel("Contrase\u00F1a");
		lblNewLabel.setBounds(50, 65, 69, 20);
		contentPane.add(lblNewLabel);
		
		tPass = new JTextField();
		tPass.setBounds(129, 65, 120, 20);
		contentPane.add(tPass);
		tPass.setColumns(10);
		textos.add(tPass);
	}

	private void formWindowClosing(VentanaGestion gestion) {
		this.setVisible(false);
		gestion.setVisible(true);
	}

	private void crearUsuarioSistema(ActionEvent evt) throws SQLException, NoSuchAlgorithmException {
		if (UtilVentanas.textosIncompletos(textos)) {
			Calendar calendario = Calendar.getInstance();
			java.util.Date fechaAltaAux = calendario.getTime();
			Date fechaAlta = new Date(fechaAltaAux.getTime());
			UsuarioSistema usuarioSistema = fus.crearUsuarioSistema(checkBoxAdministrador.isSelected());
			usuarioSistema.setDni(textDni.getText());
			usuarioSistema.setFechaAltaUsuario(fechaAlta);
			usuarioSistema.setNickname(textNombre.getText());
			usuarioSistema.setAdmin(checkBoxAdministrador.isSelected());
			usuarioSistema.setHashContrasena(tPass.getText());

			cus.insertarActualizar(usuarioSistema, true);
			UtilVentanas.Alertas.mostrar(UtilVentanas.Alertas.EXITO_INSERT, " (Usuario sistema)");
			UtilVentanas.borrarTextos(textos);
		} else {
			UtilVentanas.Alertas.mostrar(UtilVentanas.Alertas.ERROR_CAMPOS_INCOMPLETOS,"");
		}
	}

	private void bBorrarActionPerformed(ActionEvent e) {
		UtilVentanas.borrarTextos(textos); 
		
	}
}
