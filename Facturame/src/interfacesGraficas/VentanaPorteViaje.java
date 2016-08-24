package interfacesGraficas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import builders.PorteGrafico;
import pojo.Porte;
import pojo.Viaje;
/**
 * Ventana formulario para el alta del Porte. Información sobre los viajes que componen el porte.
 */
/**
 * 
 * @author Jorge González Rodríguez y Javier Ruiz Rodríguez
 *
 */
public class VentanaPorteViaje extends JFrame {

	private Porte p;
	private PorteGrafico pb;
	private JPanel contentPane;
	private JTextField textoNumeroBastidor;
	private JTextField textDni;
	private JTextField textEmpresa;
	private JTextField textKgCarga;
	private JTextField textVolumenCarga;
	private JTextField textConcepto;
	private JTextField textPrecio;
	private JLabel labelGrupaje;
	private JTextField textDescripcion;
	private JTextPane tViajes;

	public VentanaPorteViaje(PorteGrafico pb, VentanaPrincipal ventanaPrincipal) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				formWindowClosing(ventanaPrincipal);
			}
		});
		this.pb = pb;
		this.p = pb.getPorte();
		setTitle("Facturame --- Porte");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 862, 450);
		this.contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel labelNumeroBastidor = new JLabel("N\u00FAmero de bastidor:");
		labelNumeroBastidor.setBounds(10, 20, 131, 15);
		contentPane.add(labelNumeroBastidor);

		this.textoNumeroBastidor = new JTextField();
		textoNumeroBastidor.setEditable(false);
		textoNumeroBastidor.setBounds(130, 15, 90, 20);
		contentPane.add(textoNumeroBastidor);
		textoNumeroBastidor.setColumns(10);

		JLabel labelDni = new JLabel("NIF:");
		labelDni.setBounds(255, 20, 40, 15);
		contentPane.add(labelDni);

		this.textDni = new JTextField();
		textDni.setEditable(false);
		textDni.setColumns(10);
		textDni.setBounds(300, 15, 90, 20);
		contentPane.add(textDni);

		JLabel labelEmpresa = new JLabel("Empresa:");
		labelEmpresa.setBounds(10, 50, 120, 15);
		contentPane.add(labelEmpresa);

		this.textEmpresa = new JTextField();
		textEmpresa.setEditable(false);
		textEmpresa.setColumns(10);
		textEmpresa.setBounds(70, 45, 320, 20);
		contentPane.add(textEmpresa);

		JLabel labelKgCarga = new JLabel("Kg carga:");
		labelKgCarga.setBounds(10, 80, 120, 15);
		contentPane.add(labelKgCarga);

		this.textKgCarga = new JTextField();
		textKgCarga.setEditable(false);
		textKgCarga.setColumns(10);
		textKgCarga.setBounds(70, 77, 90, 20);
		contentPane.add(textKgCarga);

		JLabel labelVolumenCarga = new JLabel("Volumen carga:");
		labelVolumenCarga.setBounds(200, 80, 120, 15);
		contentPane.add(labelVolumenCarga);

		textVolumenCarga = new JTextField();
		textVolumenCarga.setEditable(false);
		textVolumenCarga.setColumns(10);
		textVolumenCarga.setBounds(300, 75, 90, 20);
		contentPane.add(textVolumenCarga);

		JPanel panelConcepto = new JPanel();
		panelConcepto.setBorder(new TitledBorder(null, "Concepto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelConcepto.setBounds(10, 105, 380, 85);
		contentPane.add(panelConcepto);
		panelConcepto.setLayout(null);

		textConcepto = new JTextField();
		textConcepto.setEditable(false);
		textConcepto.setBounds(10, 21, 360, 53);
		panelConcepto.add(textConcepto);
		textConcepto.setColumns(10);

		JLabel labelPrecio = new JLabel("Precio:");
		labelPrecio.setBounds(10, 200, 50, 15);
		contentPane.add(labelPrecio);

		textPrecio = new JTextField();
		textPrecio.setEditable(false);
		textPrecio.setColumns(10);
		textPrecio.setBounds(70, 195, 90, 20);
		contentPane.add(textPrecio);

		labelGrupaje = new JLabel("Grupaje");
		labelGrupaje.setBounds(190, 200, 55, 15);
		contentPane.add(labelGrupaje);

		JCheckBox checkBox = new JCheckBox("");
		checkBox.setBounds(240, 195, 25, 25);
		contentPane.add(checkBox);
		checkBox.setEnabled(false);

		JPanel panelDescripcion = new JPanel();
		panelDescripcion.setBorder(
				new TitledBorder(null, "Descripci\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelDescripcion.setBounds(10, 225, 380, 139);
		contentPane.add(panelDescripcion);
		panelDescripcion.setLayout(null);

		textDescripcion = new JTextField();
		textDescripcion.setEditable(false);
		textDescripcion.setBounds(10, 22, 360, 106);
		panelDescripcion.add(textDescripcion);
		textDescripcion.setColumns(10);

		JButton buttonAceptar = new JButton("ACEPTAR");
		buttonAceptar.setBounds(10, 375, 120, 25);
		contentPane.add(buttonAceptar);
		buttonAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonAnadirPorteActionPerformed(e);
			}
		});

		JButton buttonBorrar = new JButton("");
		buttonBorrar.setIcon(new ImageIcon("images\\papelera_16.png"));
		buttonBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		buttonBorrar.setBounds(270, 375, 25, 25);
		contentPane.add(buttonBorrar);

		JButton buttonAnadirViaje = new JButton("A\u00D1ADIR VIAJE");
		buttonAnadirViaje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonAnadirViajeActionPerformed(e);
			}
		});

		buttonAnadirViaje.setBounds(140, 375, 120, 25);
		contentPane.add(buttonAnadirViaje);

		JPanel panelViajes = new JPanel();
		panelViajes.setBorder(new TitledBorder(null, "Viajes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelViajes.setBounds(410, 20, 426, 380);
		contentPane.add(panelViajes);
		panelViajes.setLayout(null);

		this.tViajes = new JTextPane();
		tViajes.setEditable(false);
		tViajes.setBounds(10, 21, 406, 348);
		panelViajes.add(tViajes);

		textConcepto.setText(p.getConcepto());
		textDescripcion.setText(p.getDescripcion());
		textEmpresa.setText(p.getNif());
		textKgCarga.setText(String.valueOf(p.getKgCarga()));
		textoNumeroBastidor.setText(p.getnBastidor());
		textPrecio.setText(String.valueOf(p.getPrecio()));
		textVolumenCarga.setText(String.valueOf(p.getVolCarga()));
		textDni.setText(p.getDni());
		checkBox.setSelected(p.isEsGrupaje());
	}

	private void buttonAnadirViajeActionPerformed(java.awt.event.ActionEvent evt) {
		VentanaViaje formViaje = new VentanaViaje(this);
		formViaje.setVisible(true);
	}

	private void formWindowClosing(VentanaPrincipal ventanaPrincipal) {
		this.setVisible(false);
		ventanaPrincipal.setVisible(true);
	}

	private void buttonAnadirPorteActionPerformed(java.awt.event.ActionEvent evt) {
		pb.setEspera(false);
		this.setVisible(false);
	}

	public Porte getPorte() {
		return p;
	}

	public void anadirViaje(Viaje v) {
		pb.anadirViaje(v);
		tViajes.setText(tViajes.getText().concat("\n Dirección origen: " + v.getLugarInicio() + "\n Dirección destino: "
				+ v.getLugarDestino() + "\n   ··········"));
	}
}
