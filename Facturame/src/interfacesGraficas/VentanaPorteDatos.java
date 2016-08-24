package interfacesGraficas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import builders.PorteGrafico;
import pojo.Porte;
/**
 * Ventana formulario para el alta del Porte. Información sobre datos genéricos del porte.
 */
/**
 * 
 * @author Jorge González Rodríguez y Javier Ruiz Rodríguez
 *
 */
public class VentanaPorteDatos extends JFrame {

	private Porte p;
	private JLabel labelVolumenCarga;
	private PorteGrafico pb;
	private JCheckBox checkBoxGrupaje;
	private JPanel contentPane;
	private JTextField textKgCarga;
	private JTextField textVolumenCarga;
	private JTextField textConcepto;
	private JTextField textPrecio;
	private JTextField textDescripcion;

	public VentanaPorteDatos(PorteGrafico pb, VentanaPrincipal principal) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				formWindowClosing(principal);
			}
		});
		this.pb = pb;
		this.p = pb.getPorte();
		setTitle("Facturame --- Porte --- Datos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 417, 383);
		this.contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel labelKgCarga = new JLabel("Kg carga:");
		labelKgCarga.setBounds(10, 16, 120, 15);
		contentPane.add(labelKgCarga);

		this.textKgCarga = new JTextField();
		textKgCarga.setColumns(10);
		textKgCarga.setBounds(70, 13, 90, 20);
		contentPane.add(textKgCarga);

		this.labelVolumenCarga = new JLabel("Volumen carga:");
		labelVolumenCarga.setBounds(200, 16, 120, 15);
		contentPane.add(labelVolumenCarga);

		this.textVolumenCarga = new JTextField();
		textVolumenCarga.setColumns(10);
		textVolumenCarga.setBounds(300, 11, 90, 20);
		contentPane.add(textVolumenCarga);

		JPanel panelConcepto = new JPanel();
		panelConcepto.setLayout(null);
		panelConcepto.setBorder(new TitledBorder(null, "Concepto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelConcepto.setBounds(10, 41, 380, 85);
		contentPane.add(panelConcepto);

		this.textConcepto = new JTextField();
		textConcepto.setColumns(10);
		textConcepto.setBounds(10, 21, 360, 53);
		panelConcepto.add(textConcepto);

		JLabel labelPrecio = new JLabel("Precio:");
		labelPrecio.setBounds(10, 136, 120, 15);
		contentPane.add(labelPrecio);

		this.textPrecio = new JTextField();
		textPrecio.setColumns(10);
		textPrecio.setBounds(70, 131, 90, 20);
		contentPane.add(textPrecio);

		JLabel labelGrupaje = new JLabel("Grupaje");
		labelGrupaje.setBounds(200, 136, 55, 15);
		contentPane.add(labelGrupaje);

		this.checkBoxGrupaje = new JCheckBox("");
		checkBoxGrupaje.setBounds(250, 131, 25, 25);
		contentPane.add(checkBoxGrupaje);

		JPanel panelDescripcion = new JPanel();
		panelDescripcion.setLayout(null);
		panelDescripcion.setBorder(
				new TitledBorder(null, "Descripci\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelDescripcion.setBounds(10, 162, 380, 139);
		contentPane.add(panelDescripcion);

		this.textDescripcion = new JTextField();
		textDescripcion.setColumns(10);
		textDescripcion.setBounds(10, 22, 360, 106);
		panelDescripcion.add(textDescripcion);

		JButton button = new JButton("");		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonSiguienteActionPerformed(e);
			}
		});
		button.setIcon(new ImageIcon("images\\flecha_16.png"));
		button.setBounds(146, 312, 89, 23);
		contentPane.add(button);
		setVisible(true);
		
	}

	private void formWindowClosing(VentanaPrincipal ventanaPrincipal) {
		this.setVisible(false);
		ventanaPrincipal.setVisible(true);
	}

	private void buttonSiguienteActionPerformed(java.awt.event.ActionEvent evt) {
		if (!textKgCarga.getText().equals(""))
			p.setKgCarga(Integer.parseInt(textKgCarga.getText()));
		if (!textVolumenCarga.getText().equals(""))
			p.setVolCarga(Integer.parseInt(textVolumenCarga.getText()));
		if (!textConcepto.getText().equals(""))
			p.setConcepto(textConcepto.getText());
		p.setEsGrupaje(checkBoxGrupaje.isSelected());
		if (!textDescripcion.getText().equals(""))
			p.setDescripcion(textDescripcion.getText());
		if (!textPrecio.getText().equals(""))
			p.setPrecio(Double.valueOf(textPrecio.getText()));

		pb.setEspera(false);
		this.setVisible(false);
	}

	public Porte getPorte() {
		return p;
	}
}
