package interfacesGraficas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.print.PrintService;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;

import org.apache.pdfbox.pdmodel.PDDocument;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import factorias.FactoriaCRUD;
import operacionesCRUD.CRUDempresa;
import operacionesCRUD.CRUDportes;
import operacionesCRUD.CRUDviajes;
import pojo.Empresa;
import pojo.Porte;
import pojo.Viaje;
/**
 * Ventana que muestra las tablas que compondran la factura y que generaa un pdf de las tablas si se requiere la impresión.
 */
/**
 * 
 * @author Jorge González Rodríguez y Javier Ruiz Rodríguez
 *
 */
public class VentanaTabla2PDF extends JFrame {

	private static final long serialVersionUID = 1L;
	private static Color color = Color.CYAN;
	private ArrayList<Object> viajes;
	private JTable tablaCuerpoFactura;
	private JTable tablaInfoEmpresa;

	private JPanel contentPane;
	private Document doc;
	private ArrayList<Object> portes;
	private FactoriaCRUD fc;
	private CRUDportes cp;
	private CRUDviajes cv;
	private Empresa empresa;
	private CRUDempresa ce;

	public VentanaTabla2PDF(String fInicio, String fFinal, String empresa, VentanaPrincipal principal) throws SQLException, IOException {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
		        formWindowClosing(principal);
			}
		});
		this.tablaCuerpoFactura = new JTable();
		this.tablaInfoEmpresa = new JTable();
		this.tablaCuerpoFactura.setPreferredSize(getPreferredSize());
		this.tablaInfoEmpresa.setPreferredSize(getPreferredSize());
		this.fc = new FactoriaCRUD();
		this.ce = (CRUDempresa) fc.crearCRUD(FactoriaCRUD.TIPO_EMPRESA);
		this.empresa = (Empresa) ce.buscarUno(empresa);
		this.cp = (CRUDportes) fc.crearCRUD(FactoriaCRUD.TIPO_PORTE);
		this.cv = (CRUDviajes) fc.crearCRUD(FactoriaCRUD.TIPO_VIAJE);
		this.portes = new ArrayList<Object>(cp.buscarPorFechas(fInicio, fFinal, empresa));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		crearBarraHerramientas();
		crearTablaCuerpo();
		crearTablaResumen();
		setSize(tablaCuerpoFactura.getSize());
		pack();
	}

	private void formWindowClosing(VentanaPrincipal principal) {
	    this.setVisible(false);
	    principal.setVisible(true);
	}
	
	private void crearTablaResumen() {
		double sumatorio = 0;
		for (Object o : portes) {
			Porte p = (Porte) o;
			sumatorio = +p.getPrecio();
		}

		String[] columnNames = { "Nombre empresa", "NIF", "Teléfono", "Precio" };
		Object[][] datos = { { empresa.getEmpresa(), empresa.getNif(), empresa.getnTelefono(),
				String.valueOf(sumatorio).concat(" €") } };
		tablaInfoEmpresa = new JTable(datos, columnNames);

	}

	private void crearTablaCuerpo() throws SQLException {
		ArrayList<ArrayList<Object>> datos = new ArrayList<ArrayList<Object>>();

		for (Object o : portes) {
			Porte p = (Porte) o;
			viajes = new ArrayList<Object>(cv.buscarPorPorte(p.getIdPorte()));
			for (Object v : viajes) {
				ArrayList<Object> lista = new ArrayList<Object>();
				lista.add(p.getIdPorte());
				lista.add(p.getNif());
				lista.add(((Viaje) v).getLugarInicio());
				lista.add(((Viaje) v).getLugarDestino());
				lista.add(((Viaje) v).getFechaInicio());
				lista.add(p.getKgCarga());
				lista.add(p.getPrecio());
				lista.add(p.isEsGrupaje());
				datos.add(lista);
			}
		}

		Object[][] datosO = new Object[datos.size()][8];

		int contador = 0;
		for (ArrayList<Object> l : datos) {
			System.out.println(contador);
			datosO[contador][0] = l.get(0);
			datosO[contador][1] = l.get(1);
			datosO[contador][2] = l.get(2);
			datosO[contador][3] = l.get(3);
			datosO[contador][4] = l.get(4);
			datosO[contador][5] = l.get(5);
			datosO[contador][6] = l.get(6);
			datosO[contador][7] = l.get(7);
			contador++;
		}

		String[] columnNames = { "ID porte", "NIF", "Origen", "Destino", "F. inicio", "Kg carga", "Precio",
				"Grupaje?" };

		tablaCuerpoFactura = new JTable(datosO, columnNames);

		JPanel tPanel = new JPanel(new BorderLayout());
		tPanel.add(tablaCuerpoFactura.getTableHeader(), BorderLayout.NORTH);
		tPanel.add(tablaCuerpoFactura, BorderLayout.CENTER);

		getContentPane().add(tPanel, BorderLayout.CENTER);
	}

	private void crearBarraHerramientas() {
		JToolBar tb = new JToolBar();

		JButton printBtn = new JButton("Imprimir");
		printBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imprimir();
			}
		});

		tb.add(printBtn);

		getContentPane().add(tb, BorderLayout.NORTH);
	}

	private void imprimir() {
		try {
			doc = new Document();
			PdfWriter.getInstance(doc, new FileOutputStream("documentacion\\table.pdf"));
			manejarDocumento(0);
			pintarTablaEnDoc(tablaInfoEmpresa);
			pintarTablaEnDoc(tablaCuerpoFactura);
			manejarDocumento(1);			
			printPDF("documentacion\\table.pdf",choosePrinter());
			File tmpFile = new File("documentacion\\table.pdf");
			tmpFile.delete();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
	}
	
	private void pintarTablaEnDoc(JTable tabla) throws FileNotFoundException, DocumentException{
		PdfPTable pdfTable = new PdfPTable(tabla.getColumnCount());		

		for (int i = 0; i < tabla.getColumnCount(); i++) {
			PdfPCell celda = new PdfPCell(new Phrase(tabla.getColumnName(i)));
			celda.setBackgroundColor(VentanaTabla2PDF.color);
			pdfTable.addCell(celda);
		}

		for (int fil = 0; fil < tabla.getRowCount(); fil++) {
			for (int col = 0; col < tabla.getColumnCount(); col++) {
				pdfTable.addCell(tabla.getModel().getValueAt(fil, col).toString());
			}
		}
		añadirLineaBlanca(pdfTable,tabla.getColumnCount());

		doc.add(pdfTable);
	}

	private void añadirLineaBlanca(PdfPTable pdfTable, int columnas) {
		for(int x = 0; x <= columnas - 1;x++){
			PdfPCell celda = new PdfPCell(new Phrase(""));
			celda.setBorder(Rectangle.NO_BORDER);
			pdfTable.addCell(celda);
		}
		
	}
	
	public static PrintService choosePrinter() {
	    PrinterJob printJob = PrinterJob.getPrinterJob();
	    if(printJob.printDialog())
	        return printJob.getPrintService();   
	    else
	        return null;
	}

	public static void printPDF(String fileName, PrintService printer)
	        throws IOException, PrinterException {
	    PrinterJob job = PrinterJob.getPrinterJob();
	    job.setPrintService(printer);
	    PDDocument pdfDoc = PDDocument.load(new File("documentacion\\table.pdf"));
	    pdfDoc.silentPrint(job);
	}

	private void manejarDocumento(int i) {
		if (i == 0)
			doc.open();
		else if (i == 1)
			doc.close();

	}
}
