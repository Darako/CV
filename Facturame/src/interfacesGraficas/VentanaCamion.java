package interfacesGraficas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import factorias.FactoriaCRUD;
import factorias.FactoriaVehiculo;
import pojo.Camion;
import util.UtilVentanas;
/**
 * Ventana para la insercción de datos de camiones.
 */
/**
 * 
 * @author Jorge González Rodríguez y Javier Ruiz Rodríguez
 *
 */
public class VentanaCamion extends JFrame {
		
	private JPanel contentPane;
	private JTextField textNumeroBastidor;
	private JTextField textMatricula;
	private JTextField textNumeroPasajeros;
	private JTextField textPotenciaCv;
	private JTextField textPotenciaKwh;
	private JTextField textKmTotales;
	private JTextField textPeso;
	private JTextField textLargo;
	private JTextField textAncho;
	private JTextField textLargoCaja;
	private JTextField textAnchoCaja;
	private JTextField textPesoMaxCaja;
	private JTextField textVolumenCaja;
	private JTextField textAltoCaja;
	private JTextField textGalibo;
	private JTextField textDescripcion;
	private JCheckBox checkTrampilla;
	private JComboBox comboBoxCombustible;
	private ArrayList<JTextField> textos = new ArrayList<JTextField>();
	private FactoriaVehiculo fv;
	private FactoriaCRUD fc;
	
	public VentanaCamion(VentanaGestion gestion) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
                formWindowClosing(gestion);
			}
		});		
		setTitle("Facturame --- Camion");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 555, 440);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelNumeroBastidor = new JLabel("N\u00FAmero bastidor: ");
		labelNumeroBastidor.setBounds(20, 16, 120, 15);
		contentPane.add(labelNumeroBastidor);
		
		textNumeroBastidor = new JTextField();
		textNumeroBastidor.setColumns(10);
		textNumeroBastidor.setBounds(150, 11, 120, 20);
		contentPane.add(textNumeroBastidor);
		
		JLabel labelMatricula = new JLabel("Matr\u00EDcula: ");
		labelMatricula.setBounds(20, 46, 120, 15);
		contentPane.add(labelMatricula);
		
		textMatricula = new JTextField();
		textMatricula.setColumns(10);
		textMatricula.setBounds(150, 41, 120, 20);
		contentPane.add(textMatricula);
		
		JLabel labelCombustible = new JLabel("Combustible: ");
		labelCombustible.setBounds(285, 76, 120, 15);
		contentPane.add(labelCombustible);
		
		comboBoxCombustible = new JComboBox();
		comboBoxCombustible.setModel(new DefaultComboBoxModel(new String[] {"Diesel", "Gasolina"}));
		comboBoxCombustible.setBounds(400, 71, 120, 20);
		contentPane.add(comboBoxCombustible);
		
		JLabel labelNumeroPasajeros = new JLabel("N\u00FAmero de pasajeros: ");
		labelNumeroPasajeros.setBounds(20, 76, 120, 15);
		contentPane.add(labelNumeroPasajeros);
		
		textNumeroPasajeros = new JTextField();
		textNumeroPasajeros.setColumns(10);
		textNumeroPasajeros.setBounds(150, 71, 120, 20);
		contentPane.add(textNumeroPasajeros);
		
		JLabel labelPotenciaCv = new JLabel("Potencia (CV): ");
		labelPotenciaCv.setBounds(20, 136, 120, 15);
		contentPane.add(labelPotenciaCv);
		
		textPotenciaCv = new JTextField();
		textPotenciaCv.setColumns(10);
		textPotenciaCv.setBounds(150, 131, 60, 20);
		contentPane.add(textPotenciaCv);
		
		JLabel labelPotenciaKwh = new JLabel("Potencia (KWh): ");
		labelPotenciaKwh.setBounds(220, 136, 120, 15);
		contentPane.add(labelPotenciaKwh);
		
		textPotenciaKwh = new JTextField();
		textPotenciaKwh.setColumns(10);
		textPotenciaKwh.setBounds(305, 131, 60, 20);
		contentPane.add(textPotenciaKwh);
		
		JLabel labelKmTotales = new JLabel("Km Totales: ");
		labelKmTotales.setBounds(285, 16, 120, 15);
		contentPane.add(labelKmTotales);
		
		textKmTotales = new JTextField();
		textKmTotales.setColumns(10);
		textKmTotales.setBounds(400, 11, 120, 20);
		contentPane.add(textKmTotales);
		
		JLabel labelPeso = new JLabel("Peso: ");
		labelPeso.setBounds(285, 46, 120, 15);
		contentPane.add(labelPeso);
		
		textPeso = new JTextField();
		textPeso.setColumns(10);
		textPeso.setBounds(400, 41, 120, 20);
		contentPane.add(textPeso);
		
		JLabel labelLargo = new JLabel("Largo: ");
		labelLargo.setBounds(20, 166, 120, 15);
		contentPane.add(labelLargo);
		
		textLargo = new JTextField();
		textLargo.setColumns(10);
		textLargo.setBounds(150, 161, 60, 20);
		contentPane.add(textLargo);
		
		JLabel labelAncho = new JLabel("Ancho: ");
		labelAncho.setBounds(220, 166, 120, 15);
		contentPane.add(labelAncho);
		
		textAncho = new JTextField();
		textAncho.setColumns(10);
		textAncho.setBounds(305, 161, 60, 20);
		contentPane.add(textAncho);
		
		JLabel labelLargoCaja = new JLabel("Largo caja: ");
		labelLargoCaja.setBounds(20, 196, 120, 15);
		contentPane.add(labelLargoCaja);
		
		textLargoCaja = new JTextField();
		textLargoCaja.setColumns(10);
		textLargoCaja.setBounds(150, 191, 60, 20);
		contentPane.add(textLargoCaja);
		
		JLabel labelAnchoCaja = new JLabel("Ancho caja: ");
		labelAnchoCaja.setBounds(220, 196, 120, 15);
		contentPane.add(labelAnchoCaja);
		
		textAnchoCaja = new JTextField();
		textAnchoCaja.setColumns(10);
		textAnchoCaja.setBounds(305, 191, 60, 20);
		contentPane.add(textAnchoCaja);
		
		JLabel labelPesoMaxCaja = new JLabel("Peso (max.) caja: ");
		labelPesoMaxCaja.setBounds(20, 226, 120, 15);
		contentPane.add(labelPesoMaxCaja);
		
		textPesoMaxCaja = new JTextField();
		textPesoMaxCaja.setColumns(10);
		textPesoMaxCaja.setBounds(150, 221, 60, 20);
		contentPane.add(textPesoMaxCaja);
		
		JLabel labelVolumenCaja = new JLabel("Volumen caja: ");
		labelVolumenCaja.setBounds(220, 226, 120, 15);
		contentPane.add(labelVolumenCaja);
		
		textVolumenCaja = new JTextField();
		textVolumenCaja.setEditable(false);
		textVolumenCaja.setColumns(10);
		textVolumenCaja.setBounds(305, 221, 60, 20);
		contentPane.add(textVolumenCaja);
		
		JLabel labelAltoCaja = new JLabel("Alto caja: ");
		labelAltoCaja.setBounds(385, 196, 120, 15);
		contentPane.add(labelAltoCaja);
		
		textAltoCaja = new JTextField();
		textAltoCaja.setColumns(10);
		textAltoCaja.setBounds(460, 191, 60, 20);
		contentPane.add(textAltoCaja);
		
		JLabel labelGalibo = new JLabel("G\u00E1libo: ");
		labelGalibo.setBounds(385, 166, 120, 15);
		contentPane.add(labelGalibo);
		
		textGalibo = new JTextField();
		textGalibo.setColumns(10);
		textGalibo.setBounds(460, 161, 60, 20);
		contentPane.add(textGalibo);
		
		JLabel labelTrampilla = new JLabel("Trampilla:");
		labelTrampilla.setBounds(385, 136, 90, 15);
		contentPane.add(labelTrampilla);
		
		checkTrampilla = new JCheckBox("");
		checkTrampilla.setBounds(455, 131, 21, 23);
		contentPane.add(checkTrampilla);
		
		JButton buttonAnadir = new JButton("A\u00D1ADIR");
		buttonAnadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					crearCamion();
				} catch (SQLException sqle) {
					UtilVentanas.Alertas.mostrar(UtilVentanas.Alertas.ERROR_SQL,sqle.toString());
				} catch (IOException ioe) {
					UtilVentanas.Alertas.mostrar(UtilVentanas.Alertas.ERROR_IOE,ioe.toString());
				}
			}
		});
		buttonAnadir.setBounds(111, 356, 229, 25);
		contentPane.add(buttonAnadir);
		
		JButton buttonBorrar = new JButton("");
		buttonBorrar.setIcon(new ImageIcon("images\\papelera_16.png"));		
		buttonBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UtilVentanas.borrarTextos(textos);
			}
		});
		buttonBorrar.setBounds(410, 356, 25, 25);
		contentPane.add(buttonBorrar);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(20, 111, 500, 2);
		contentPane.add(separator);
		
		JPanel panelTextDescripcion = new JPanel();
		panelTextDescripcion.setLayout(null);
		panelTextDescripcion.setBorder(new TitledBorder(null, "Descripci\u00F3n:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTextDescripcion.setBounds(10, 256, 520, 80);
		contentPane.add(panelTextDescripcion);
		
		textDescripcion = new JTextField();
		textDescripcion.setColumns(10);
		textDescripcion.setBounds(10, 15, 500, 55);
		panelTextDescripcion.add(textDescripcion);
		
		JButton buttonCalcular = new JButton("");
		buttonCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calcularVolumen();
			}
		});
		buttonCalcular.setIcon(new ImageIcon("images\\calculadora_16.png"));
		buttonCalcular.setBounds(382, 218, 25, 25);
		contentPane.add(buttonCalcular);
		
		textos.add(textNumeroBastidor);
		textos.add(textMatricula);
		textos.add(textNumeroPasajeros);
		textos.add(textPotenciaCv);
		textos.add(textPotenciaKwh);
		textos.add(textKmTotales);
		textos.add(textPeso);
		textos.add(textLargo);
		textos.add(textAncho);
		textos.add(textLargoCaja);
		textos.add(textAnchoCaja);
		textos.add(textPesoMaxCaja);
		textos.add(textVolumenCaja);
		textos.add(textAltoCaja);
		textos.add(textGalibo);
		textos.add(textDescripcion);
		this.fv = new FactoriaVehiculo();
		this.fc = new FactoriaCRUD();		
	}
	
	public void calcularVolumen(){
		if(textLargoCaja.getText().isEmpty() || textAnchoCaja.getText().isEmpty() || textAltoCaja.getText().isEmpty()){
			UtilVentanas.Alertas.mostrar(UtilVentanas.Alertas.ERROR_CAMPOS_INCOMPLETOS,"");
		} else {
			int largoCaja = Integer.parseInt(textLargoCaja.getText());
			int anchoCaja = Integer.parseInt(textAnchoCaja.getText());
			int altoCaja = Integer.parseInt(textAltoCaja.getText());
			String volumenCaja = String.valueOf(largoCaja*anchoCaja*altoCaja);
			textVolumenCaja.setText(volumenCaja);
		}				
	}
	
	private void crearCamion() throws SQLException, IOException{
		if(UtilVentanas.textosIncompletos(textos)){
			Camion camion = (Camion) fv.crearCamion(1);
			camion.setCombustible(comboBoxCombustible.getSelectedItem().toString());	
			camion.setAncho(Double.parseDouble(textAncho.getText()));
			camion.setLargo(Double.parseDouble(textLargo.getText()));
			camion.setGalibo(Double.parseDouble(textGalibo.getText()));
			camion.setAnchoCaja(Double.parseDouble(textAnchoCaja.getText()));
			camion.setLongCaja(Double.parseDouble(textLargo.getText()));
			camion.setAlturaCaja(Double.parseDouble(textAltoCaja.getText()));
			camion.setDescripcion(textDescripcion.getText());
			camion.setKmTotales(Integer.parseInt(textKmTotales.getText()));
			camion.setMatricula(textMatricula.getText());
			camion.setnBastidor(textNumeroBastidor.getText());
			camion.setnPasajeros(Integer.parseInt(textNumeroPasajeros.getText()));
			camion.setPeso(Integer.parseInt(textPeso.getText()));
			camion.setPesoMaxCaja(Integer.parseInt(textPesoMaxCaja.getText()));
			camion.setPotenciaCV(Integer.parseInt(textPotenciaCv.getText()));
			camion.setPotenciaKWh(Integer.parseInt(textPotenciaKwh.getText()));
			camion.setTrampilla(checkTrampilla.isSelected());
			camion.setVolumenCaja(Integer.parseInt(textVolumenCaja.getText()));			
			fc.crearCRUD(FactoriaCRUD.TIPO_CAMION).insertarActualizar(camion, true);
			UtilVentanas.Alertas.mostrar(UtilVentanas.Alertas.EXITO_INSERT, "camión"); 
		} else {
			JOptionPane.showMessageDialog(null, "Faltan campos por rellenar");
		}		
	}
	
	private void formWindowClosing(VentanaGestion gestion) {
        this.setVisible(false);
        gestion.setVisible(true);
    }

}
