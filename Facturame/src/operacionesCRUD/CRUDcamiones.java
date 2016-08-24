package operacionesCRUD;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conexionProxyBBDD.Conexion;
import pojo.Camion;
/**
 * Operaciones CRUD de camiones.
 */
/**
 * 
 * @author Jorge González Rodríguez y Javier Ruiz Rodríguez
 *
 */
public class CRUDcamiones extends CRUDesquema {

	private static String selectAllCamion = "select * from \"Camion\"";
	private static String updateCamion = "UPDATE \"Camion\" SET \"nBastidor\"=?, matricula=?, combustible=?, \"nPasajeros\"=?, \"potenciaCV\"=?, \"potenciaKWh\"=?, \"kmTotales\"=?, peso=?, largo=?, ancho=?, \"longCaja\"=?, \"anchoCaja\"=?, \"pesoMaxCaja\"=?, \"volumenCaja\"=?, trampilla=?, descripcion=?, \"altoCaja\"=?, galibo=? where \"nBastidor\" = ?";
	private static String borrarCamion = "delete from \"Camion\" where \"nBastidor\" = ?";
	private static String insertCamion = "INSERT INTO \"Camion\"(\"nBastidor\", matricula, combustible, \"nPasajeros\", \"potenciaCV\",\"potenciaKWh\", \"kmTotales\", peso, largo, ancho, \"longCaja\", \"anchoCaja\", \"pesoMaxCaja\", \"volumenCaja\", trampilla, descripcion, \"altoCaja\", galibo)VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static String selectCamion = "select * from \"Camion\" where matricula = ?";

	/**
	 * Objeto de conexion a la BBDD.
	 */
	private Conexion c;

	/**
	 * Constructor principal.
	 * 
	 * @throws SQLException
	 * @throws IOException
	 */
	public CRUDcamiones() throws SQLException, IOException {
		super();
	}

	@Override
	public ArrayList<Object> buscarTodo() throws SQLException {
		c = cc.crearConexion();
		ArrayList<Object> respuesta = new ArrayList<Object>();

		c.setSt(c.getCon().createStatement());
		c.setRs(c.getSt().executeQuery(CRUDcamiones.selectAllCamion));

		String nBastidor, matricula, combustible, descripcion;
		int nPasajeros, potenciaCV, PotenciaKWh, kmTotales, peso;
		Double largo, ancho, longCaja, anchoCaja, pesoMaxCaja, volumenCaja, altoCaja, galibo;
		boolean trampilla;
		ResultSet rs = c.getRs();

		while (rs.next()) {
			nBastidor = rs.getString(1);
			matricula = rs.getString(2);
			combustible = rs.getString(3);
			nPasajeros = rs.getInt(4);
			potenciaCV = rs.getInt(5);
			PotenciaKWh = rs.getInt(6);
			kmTotales = rs.getInt(7);
			peso = rs.getInt(8);
			largo = rs.getDouble(9);
			ancho = rs.getDouble(10);
			longCaja = rs.getDouble(11);
			anchoCaja = rs.getDouble(12);
			pesoMaxCaja = rs.getDouble(13);
			volumenCaja = rs.getDouble(14);
			trampilla = rs.getBoolean(15);
			descripcion = rs.getString(16);
			altoCaja = rs.getDouble(17);
			galibo = rs.getDouble(18);
			respuesta.add((Object) new Camion(nBastidor, matricula, combustible, nPasajeros, potenciaCV, PotenciaKWh,
					kmTotales, peso, largo, ancho, longCaja, anchoCaja, pesoMaxCaja, volumenCaja, trampilla,
					descripcion, altoCaja, galibo));
		}

		c.cerrarObjCon();
		c.cerrarObjSt();
		c.cerrarObjRs();
		rs.close();

		return respuesta;

	}

	@Override
	public Object buscarUno(Object entrada) throws SQLException {
		c = cc.crearConexion();
		String matriculaBuscada = String.valueOf(entrada);
		Camion respuesta = null;

		c.setPst(c.getCon().prepareStatement(CRUDcamiones.selectCamion));
		c.prepararPst(1, matriculaBuscada);
		c.setRs(c.getPst().executeQuery());

		String nBastidor, matricula, combustible, descripcion;
		int nPasajeros, potenciaCV, PotenciaKWh, kmTotales, peso;
		Double largo, ancho, longCaja, anchoCaja, pesoMaxCaja, volumenCaja, altoCaja, galibo;
		boolean trampilla;
		ResultSet rs = c.getRs();

		while (rs.next()) {
			nBastidor = rs.getString(1);
			matricula = rs.getString(2);
			combustible = rs.getString(3);
			nPasajeros = rs.getInt(4);
			potenciaCV = rs.getInt(5);
			PotenciaKWh = rs.getInt(6);
			kmTotales = rs.getInt(7);
			peso = rs.getInt(8);
			largo = rs.getDouble(9);
			ancho = rs.getDouble(10);
			longCaja = rs.getDouble(11);
			anchoCaja = rs.getDouble(12);
			pesoMaxCaja = rs.getDouble(13);
			volumenCaja = rs.getDouble(14);
			trampilla = rs.getBoolean(15);
			descripcion = rs.getString(16);
			altoCaja = rs.getDouble(17);
			galibo = rs.getDouble(18);
			respuesta = new Camion(nBastidor, matricula, combustible, nPasajeros, potenciaCV, PotenciaKWh, kmTotales,
					peso, largo, ancho, longCaja, anchoCaja, pesoMaxCaja, volumenCaja, trampilla, descripcion, altoCaja,
					galibo);
		}

		c.cerrarObjCon();
		c.cerrarObjPst();
		c.cerrarObjRs();
		rs.close();

		return (Object) respuesta;

	}

	// mode 0 --> insert
	// mode 1 --> update
	@Override
	public int insertarActualizar(Object entrada, boolean esInsert) throws SQLException {
		c = cc.crearConexion();
		Camion camion = (Camion) entrada;
		int respuesta = 0;

		if (esInsert)
			c.setPst(c.getCon().prepareStatement(CRUDcamiones.insertCamion));
		else
			c.setPst(c.getCon().prepareStatement(CRUDcamiones.updateCamion));

		c.prepararPst(1, camion.getnBastidor());
		c.prepararPst(2, camion.getMatricula());
		c.prepararPst(3, camion.getCombustible());
		c.prepararPst(4, camion.getnPasajeros());
		c.prepararPst(5, camion.getPotenciaCV());
		c.prepararPst(6, camion.getPotenciaKWh());
		c.prepararPst(7, camion.getKmTotales());
		c.prepararPst(8, camion.getPeso());
		c.prepararPst(9, camion.getLargo());
		c.prepararPst(10, camion.getAncho());
		c.prepararPst(11, camion.getLongCaja());
		c.prepararPst(12, camion.getAnchoCaja());
		c.prepararPst(13, camion.getPesoMaxCaja());
		c.prepararPst(14, camion.getVolumenCaja());
		c.prepararPst(15, camion.isTrampilla());
		c.prepararPst(16, camion.getDescripcion());
		c.prepararPst(17, camion.getAlturaCaja());
		c.prepararPst(18, camion.getGalibo());

		if (!esInsert)
			c.prepararPst(19, camion.getnBastidor());

		respuesta = c.getPst().executeUpdate();

		c.cerrarObjCon();
		c.cerrarObjPst();

		return respuesta;

	}

	@Override
	public int borrar(Object entrada) throws SQLException {
		c = cc.crearConexion();
		String nBastidor = String.valueOf(entrada);
		int respuesta = 0;

		c.setPst(c.getCon().prepareStatement(CRUDcamiones.borrarCamion));
		c.prepararPst(1, nBastidor);
		respuesta = c.getPst().executeUpdate();

		c.cerrarObjCon();
		c.cerrarObjPst();

		return respuesta;

	}
}
