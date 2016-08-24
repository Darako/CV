package interfacesGraficas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import util.UtilVentanas;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
/**
 * Ventana que contiene el menu de insercción de datos.
 */
/**
 * 
 * @author Jorge González Rodríguez y Javier Ruiz Rodríguez
 *
 */
public class VentanaGestion extends JFrame {

	private JPanel contentPane;

	public VentanaGestion(VentanaPrincipal principal) {
		setTitle("Facturame --- Gesti\u00F3n");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
		        formWindowClosing(principal);
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 542, 160);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton buttonCamion = new JButton("");
		buttonCamion.setIcon(new ImageIcon("images\\camiones_32.png"));
		buttonCamion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					clickVentanaCamion();
				} catch (SQLException sqle) {
					UtilVentanas.Alertas.mostrar(UtilVentanas.Alertas.ERROR_SQL,sqle.toString());
				} catch (IOException ioe) {
					UtilVentanas.Alertas.mostrar(UtilVentanas.Alertas.ERROR_IOE,ioe.toString());
				}
			}
		});
		buttonCamion.setBounds(36, 22, 50, 50);
		contentPane.add(buttonCamion);
		
		JButton buttonEmpleados = new JButton("");
		buttonEmpleados.setIcon(new ImageIcon("images\\empleados_32.png"));
		buttonEmpleados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					clickVentanaEmpleado();
				} catch (SQLException sqle) {
					UtilVentanas.Alertas.mostrar(UtilVentanas.Alertas.ERROR_SQL,sqle.toString());
				} catch (IOException ioe) {
					UtilVentanas.Alertas.mostrar(UtilVentanas.Alertas.ERROR_IOE,ioe.toString());
				}
			}
		});
		buttonEmpleados.setBounds(134, 22, 50, 50);
		contentPane.add(buttonEmpleados);
		
		JButton buttonEmpresas = new JButton("");
		buttonEmpresas.setIcon(new ImageIcon("images\\empresas_32.png"));
		buttonEmpresas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					clickVentanaEmpresa();
				} catch (SQLException sqle) {
					UtilVentanas.Alertas.mostrar(UtilVentanas.Alertas.ERROR_SQL,sqle.toString());
				} catch (IOException ioe) {
					UtilVentanas.Alertas.mostrar(UtilVentanas.Alertas.ERROR_IOE,ioe.toString());
				}
			}
		});
		buttonEmpresas.setBounds(234, 22, 50, 50);
		contentPane.add(buttonEmpresas);
		
		JButton buttonUsuarioSistema = new JButton("");
		buttonUsuarioSistema.setIcon(new ImageIcon("images\\usuario_32.png"));
		buttonUsuarioSistema.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					clickVentanaUsuarioSistema();
				} catch (SQLException sqle) {
					UtilVentanas.Alertas.mostrar(UtilVentanas.Alertas.ERROR_SQL,sqle.toString());
				} catch (IOException ioe) {
					UtilVentanas.Alertas.mostrar(UtilVentanas.Alertas.ERROR_IOE,ioe.toString());
				}
			}
		});
		buttonUsuarioSistema.setBounds(333, 22, 50, 50);
		contentPane.add(buttonUsuarioSistema);
		
		JButton buttonSubordinado = new JButton("");
		buttonSubordinado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					clickVentanaSubordinado();
				} catch (SQLException sqle) {
					UtilVentanas.Alertas.mostrar(UtilVentanas.Alertas.ERROR_SQL,sqle.toString());
				} catch (IOException ioe) {
					UtilVentanas.Alertas.mostrar(UtilVentanas.Alertas.ERROR_IOE,ioe.toString());
				}
			}
		});
		buttonSubordinado.setIcon(new ImageIcon("images\\jefesubordinado_32.png"));
		buttonSubordinado.setBounds(432, 22, 50, 50);
		contentPane.add(buttonSubordinado);
		
		JLabel labelCamion = new JLabel("Camiones");
		labelCamion.setFont(new Font("Tahoma", Font.PLAIN, 9));
		labelCamion.setBounds(36, 73, 50, 14);
		contentPane.add(labelCamion);
		
		JLabel lblEmpleados = new JLabel("Empleados");
		lblEmpleados.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblEmpleados.setBounds(134, 73, 56, 14);
		contentPane.add(lblEmpleados);
		
		JLabel lblEmpresas = new JLabel("Empresas");
		lblEmpresas.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblEmpresas.setBounds(234, 73, 56, 14);
		contentPane.add(lblEmpresas);
		
		JLabel lblUsuarionsistema = new JLabel("Usuario\r\n");
		lblUsuarionsistema.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblUsuarionsistema.setBounds(333, 73, 56, 14);
		contentPane.add(lblUsuarionsistema);
		
		JLabel lblSistema = new JLabel("\r\nSistema");
		lblSistema.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblSistema.setBounds(333, 83, 56, 14);
		contentPane.add(lblSistema);
		
		JLabel lblSubordinado = new JLabel("Subordinado");
		lblSubordinado.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblSubordinado.setBounds(432, 73, 60, 14);
		contentPane.add(lblSubordinado);
	}
	
	private void formWindowClosing(VentanaPrincipal principal) {
	    this.setVisible(false);
	    principal.setVisible(true);
	}
	
	private void clickVentanaCamion() throws SQLException, IOException {
		VentanaCamion ventanaCamion = new VentanaCamion(this);
		ventanaCamion.setVisible(true);
        this.setVisible(false);
    }
	
	private void clickVentanaEmpleado() throws SQLException, IOException {
		VentanaEmpleado ventanaEmpleado = new VentanaEmpleado(this);
		ventanaEmpleado.setVisible(true);
	    this.setVisible(false);
	}
	
	private void clickVentanaEmpresa() throws SQLException, IOException {
		VentanaEmpresa ventanaEmpresa = new VentanaEmpresa(this);
		ventanaEmpresa.setVisible(true);
	    this.setVisible(false);
	}
	
	private void clickVentanaUsuarioSistema() throws SQLException, IOException {
		VentanaUsuarioSistema ventanaUsuarioSistema = new VentanaUsuarioSistema(this);
		ventanaUsuarioSistema.setVisible(true);
	    this.setVisible(false);
	}
	
	private void clickVentanaSubordinado() throws SQLException, IOException {
		VentanaSubordinado ventanaSubordinado = new VentanaSubordinado(this);
		ventanaSubordinado.setVisible(true);
	    this.setVisible(false);
	}
}
