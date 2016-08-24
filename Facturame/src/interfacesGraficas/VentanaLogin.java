package interfacesGraficas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import factorias.FactoriaUsuarioActual;
import pojo.UsuarioAutenticacion;
import state.Estado;
import state.EstadoAdmin;
import state.EstadoLogin;
import util.UtilVentanas;
/**
 * Ventana principal del sistema. Punto de inicio visual del sistema.
 */
/**
 * 
 * @author Jorge González Rodríguez y Javier Ruiz Rodríguez
 *
 */
public class VentanaLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private static VentanaLogin ventana;
	private JPanel contentPane;
	private JTextField tNombre;
	private JTextField tPass;
	private Estado estadoActual;
	private UsuarioAutenticacion ua;
	
	private ArrayList<JTextField> textos = new ArrayList<JTextField>();

	private VentanaLogin() {
		setTitle("Facturame --- Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 320, 152);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel labelUsuario = new JLabel("Usuario:");
		labelUsuario.setBounds(61, 16, 69, 20);
		contentPane.add(labelUsuario);

		tNombre = new JTextField();
		tNombre.setColumns(10);
		tNombre.setBounds(120, 16, 140, 20);
		contentPane.add(tNombre);

		JLabel labelContrasena = new JLabel("Contrase\u00F1a:");
		labelContrasena.setBounds(43, 47, 69, 20);
		contentPane.add(labelContrasena);

		tPass = new JTextField();
		tPass.setColumns(10);
		tPass.setBounds(120, 46, 140, 20);
		contentPane.add(tPass);

		textos.add(tNombre);
		textos.add(tPass);
		
		JButton buttonAceptar = new JButton("ACEPTAR");
		buttonAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					bAceptarActionPerformed(evt);
				} catch (NoSuchAlgorithmException | SQLException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		buttonAceptar.setBounds(43, 78, 182, 25);
		contentPane.add(buttonAceptar);

		JButton buttonBorrar = new JButton("");
		buttonBorrar.setIcon(new ImageIcon(
				"images\\papelera_16.png"));
		buttonBorrar.setBounds(235, 77, 25, 25);
		buttonBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
					UtilVentanas.borrarTextos(textos);
				
			}
		});
		contentPane.add(buttonBorrar);

		this.setVisible(true);
	}

	public static VentanaLogin getLogin() {
		if (ventana == null)
			ventana = new VentanaLogin();
		return ventana;
	}

	private void bAceptarActionPerformed(ActionEvent evt) throws NoSuchAlgorithmException, SQLException, IOException {
		FactoriaUsuarioActual fua = new FactoriaUsuarioActual();
		ua = fua.crearUserAct(tNombre.getText(), tPass.getText());
		
		if(ua.getNickname() == null)
			mostrarAlerta(ua);			
		else{
			this.setVisible(false);
			JOptionPane.showMessageDialog(null, "Bienvenido al sistema " + ua.getNickname() +".");
			estadoActual = new EstadoLogin();
			estadoActual.ejecutar(this);
			resultado();
		}		
	}

	private void resultado() throws SQLException, IOException {
		estadoActual.ejecutar(this);		
	}

	private void mostrarAlerta(UsuarioAutenticacion ua) {
		JOptionPane.showMessageDialog(null, "El usuario o la contraseña no está registrado en el sistema.");
		tNombre.setText("");
		tPass.setText("");		
	}
	
	public UsuarioAutenticacion getAuthUser(){
		return ua;
	}

	public void setEstadoActual(Estado estado) {
		estadoActual = estado;		
	}
	
}
