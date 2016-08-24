package interfacesGraficas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.Timestamp;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import adaptadorFecha.AdaptadorFechaPostgres;
import adaptadorFecha.Fecha;
import adaptadorFecha.FechaEs;
import builders.PorteBuilder;
import pojo.Viaje;
/**
 * Ventana para la inserción de viajes dentro la inserción de portes.
 */
/**
 * 
 * @author Jorge González Rodríguez y Javier Ruiz Rodríguez
 *
 */
public class VentanaViaje extends JFrame {

	private VentanaPorteViaje ventanaAnterior;
	private JPanel contentPane;
	private JTextField tOrigen;
	private JTextField tDestino;
	private JTextField tHoraSalida;
	private JTextField tHoraLlegada;
	private JTextField tFechaSalida;
	private JTextField tFechaLegada;
	private JTextField tKmViaje;

	public VentanaViaje(VentanaPorteViaje ventanaAnterior) {
		this.ventanaAnterior = ventanaAnterior;
		setTitle("Facturame --- Viaje");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 231);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel labelOrigen = new JLabel("Origen: ");
		labelOrigen.setBounds(10, 10, 120, 20);
		contentPane.add(labelOrigen);

		JLabel labelDestino = new JLabel("Destino: ");
		labelDestino.setBounds(10, 40, 120, 20);
		contentPane.add(labelDestino);

		JLabel labelHoraSalida = new JLabel("Hora de salida:");
		labelHoraSalida.setBounds(10, 71, 120, 20);
		contentPane.add(labelHoraSalida);

		JLabel labelHoraLlegada = new JLabel("Hora de llegada: ");
		labelHoraLlegada.setBounds(200, 70, 120, 20);
		contentPane.add(labelHoraLlegada);

		JLabel labelFechaSalida = new JLabel("Fecha de salida:");
		labelFechaSalida.setBounds(10, 100, 120, 20);
		contentPane.add(labelFechaSalida);

		JLabel labelFechaLlegada = new JLabel("Fecha de llegada:");
		labelFechaLlegada.setBounds(200, 100, 120, 20);
		contentPane.add(labelFechaLlegada);

		JLabel labelKmViaje = new JLabel("Km del viaje:");
		labelKmViaje.setBounds(10, 130, 120, 20);
		contentPane.add(labelKmViaje);

		tOrigen = new JTextField();
		tOrigen.setBounds(55, 10, 367, 20);
		contentPane.add(tOrigen);
		tOrigen.setColumns(10);

		tDestino = new JTextField();
		tDestino.setColumns(10);
		tDestino.setBounds(55, 40, 367, 20);
		contentPane.add(tDestino);

		tHoraSalida = new JTextField();
		tHoraSalida.setBounds(95, 70, 86, 20);
		contentPane.add(tHoraSalida);
		tHoraSalida.setColumns(10);

		tHoraLlegada = new JTextField();
		tHoraLlegada.setColumns(10);
		tHoraLlegada.setBounds(290, 70, 86, 20);
		contentPane.add(tHoraLlegada);

		tFechaSalida = new JTextField();
		tFechaSalida.setColumns(10);
		tFechaSalida.setBounds(95, 100, 86, 20);
		contentPane.add(tFechaSalida);

		tFechaLegada = new JTextField();
		tFechaLegada.setColumns(10);
		tFechaLegada.setBounds(290, 100, 86, 20);
		contentPane.add(tFechaLegada);

		tKmViaje = new JTextField();
		tKmViaje.setBounds(95, 130, 86, 20);
		contentPane.add(tKmViaje);
		tKmViaje.setColumns(10);

		JButton buttonAceptar = new JButton("ACEPTAR");
		buttonAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				butonAceptarActionPerformed(e);
			}
		});
		buttonAceptar.setBounds(10, 161, 120, 25);
		contentPane.add(buttonAceptar);

		JButton buttonBorrar = new JButton("");
		buttonBorrar.setIcon(new ImageIcon("images\\papelera_16.png"));
		buttonBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				butonBorrarActionPerformed(e);
			}
		});
		buttonBorrar.setBounds(140, 161, 25, 25);
		contentPane.add(buttonBorrar);
	}

	private void formWindowClosing(java.awt.event.WindowEvent evt) {
		this.setVisible(false);
	}

	private void butonBorrarActionPerformed(java.awt.event.ActionEvent e) {
		tDestino.setText("");
		tFechaLegada.setText("");
		tFechaSalida.setText("");
		tHoraLlegada.setText("");
		tHoraSalida.setText("");
		tKmViaje.setText("");
		tOrigen.setText("");

	}

	//ultimo campo = this.pb.getPorte().getIdPorte()
	private void butonAceptarActionPerformed(java.awt.event.ActionEvent e) {
		Fecha fechaInicio = new AdaptadorFechaPostgres(new FechaEs(tFechaSalida.getText()));
		Fecha fechaFinal = new AdaptadorFechaPostgres(new FechaEs(tFechaLegada.getText()));
		String fechaIniForm = fechaInicio.toString();
		String fechaFinForm = fechaFinal.toString();
		this.ventanaAnterior.anadirViaje(new Viaje(0, tOrigen.getText(), tDestino.getText(), new Timestamp(1000),
				new Timestamp(1000), Date.valueOf(fechaIniForm), Date.valueOf(fechaFinForm),
				Integer.valueOf(tKmViaje.getText()), 0));
		this.setVisible(false);

	}

}
