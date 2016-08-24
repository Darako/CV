package operacionesCRUD;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexionProxyBBDD.Conexion;
import pojo.Porte;

/**
 * Operaciones CRUD de portes.
 */
/**
 * 
 * @author Jorge González Rodríguez y Javier Ruiz Rodríguez
 *
 */
public class CRUDportes extends CRUDesquema {

	private static String selectAllPorte = "select * from \"Porte\"";
	private static String updatePorte = "UPDATE \"Porte\" SET \"idPorte\"=?, \"nBastidor\"=?, dni=?, \"kgCarga\"=?, \"volumenCarga\"=?, concepto=?, precio=?, \"esGrupaje\"=?, descripcion=?, \"NIF\"=? WHERE \"idPorte\"=?";
	private static String borrarPorte = "delete from \"Porte\" where \"idPorte\"=?";
	private static String insertPorte = "INSERT INTO \"Porte\"(\"idPorte\", \"nBastidor\", dni, \"kgCarga\", \"volumenCarga\", concepto, precio, \"esGrupaje\", descripcion, \"NIF\") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static String selectPorte = "select * from \"Porte\" where \"idPorte\" = ?";
	private static String selectPortePorNif = "select * from \"Porte\" where \"NIF\" = ?";
	private static String getUltimoSerial = "SELECT last_value FROM \"Porte_idPorte_seq\"";
	private static String setUltimoSerial = "ALTER SEQUENCE \"Porte_idPorte_seq\" RESTART WITH ";
	private static String getPorFecha = "SELECT DISTINCT \"Porte\".* FROM \"Porte\" INNER JOIN \"Viaje\" ON \"Porte\".\"idPorte\" = \"Viaje\".\"idPorte\" WHERE ((\"Viaje\".\"fechaInicio\", \"Viaje\".\"fechaInicio\") OVERLAPS (?::DATE, ?::DATE) AND (\"Porte\".\"NIF\" = ?))";

	/*
	 * Secuencia del id actual.
	 */
	private int idPorteSeq;
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
	public CRUDportes() throws SQLException, IOException {
		super();
		this.idPorteSeq = 0;
	}

	/**
	 * Método para establecer en la base de datos la secuencia actual del id del porte.
	 * 
	 * @param ultimoId
	 * @return
	 * @throws SQLException
	 */
	public int setUltimoId(int ultimoId) throws SQLException {
		c = cc.crearConexion();
		int respuesta = 0;
		c.setPst(c.getCon().prepareStatement(CRUDportes.setUltimoSerial.concat(String.valueOf(ultimoId))));
		respuesta = c.getPst().executeUpdate();

		c.cerrarObjCon();
		c.cerrarObjPst();

		return respuesta;

	}

	/**
	 * Método para coger de la BBDD la secuencia actual del id de porte.
	 * 
	 * @return
	 * @throws SQLException
	 */
	public int getUltimoId() throws SQLException {
		c = cc.crearConexion();
		c.setSt(c.getCon().createStatement());
		c.setRs(c.getSt().executeQuery(CRUDportes.getUltimoSerial));

		ResultSet rs = c.getRs();

		while (rs.next()) {
			idPorteSeq = rs.getInt(1);
		}

		c.cerrarObjCon();
		c.cerrarObjSt();
		c.cerrarObjRs();
		rs.close();

		return idPorteSeq;
	}

	@Override
	public ArrayList<Object> buscarTodo() throws SQLException {
		c = cc.crearConexion();
		ArrayList<Object> respuesta = new ArrayList<Object>();

		c.setSt(c.getCon().createStatement());
		c.setRs(c.getSt().executeQuery(CRUDportes.selectAllPorte));

		String nBastidor, dni, concepto, descripcion, nif;
		int idPorte, kgCarga, volCarga;
		double precio;
		boolean esGrupaje;
		ResultSet rs = c.getRs();

		while (rs.next()) {
			idPorte = rs.getInt(1);
			nBastidor = rs.getString(2);
			dni = rs.getString(3);
			kgCarga = rs.getInt(4);
			volCarga = rs.getInt(5);
			concepto = rs.getString(6);
			precio = rs.getDouble(7);
			esGrupaje = rs.getBoolean(8);
			descripcion = rs.getString(9);
			nif = rs.getString(10);

			respuesta.add((Object) new Porte(idPorte, nBastidor, dni, kgCarga, volCarga, concepto, precio, esGrupaje,
					descripcion, nif));
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
		String idPorteBuscado = String.valueOf(entrada);
		Object respuesta = null;

		c.setPst(c.getCon().prepareStatement(CRUDportes.selectPorte));
		c.prepararPst(1, idPorteBuscado);
		c.setRs(c.getPst().executeQuery());

		String nBastidor, dni, concepto, descripcion, nif;
		int idPorte, kgCarga, volCarga;
		double precio;
		boolean esGrupaje;
		ResultSet rs = c.getRs();

		while (rs.next()) {
			idPorte = rs.getInt(1);
			nBastidor = rs.getString(2);
			dni = rs.getString(3);
			kgCarga = rs.getInt(4);
			volCarga = rs.getInt(5);
			concepto = rs.getString(6);
			precio = rs.getDouble(7);
			esGrupaje = rs.getBoolean(8);
			descripcion = rs.getString(9);
			nif = rs.getString(10);

			respuesta = new Porte(idPorte, nBastidor, dni, kgCarga, volCarga, concepto, precio, esGrupaje, descripcion,
					nif);
		}

		c.cerrarObjCon();
		c.cerrarObjPst();
		c.cerrarObjRs();
		rs.close();

		return (Object) respuesta;

	}

	@Override
	public int insertarActualizar(Object entrada, boolean esInsert) throws SQLException {
		c = cc.crearConexion();
		Porte porte = (Porte) entrada;
		int respuesta = 0;

		if (esInsert)
			c.setPst(c.getCon().prepareStatement(CRUDportes.insertPorte));
		else
			c.setPst(c.getCon().prepareStatement(CRUDportes.updatePorte));

		c.prepararPst(1, porte.getIdPorte());
		c.prepararPst(2, porte.getnBastidor());
		c.prepararPst(3, porte.getDni());
		c.prepararPst(4, porte.getKgCarga());
		c.prepararPst(5, porte.getVolCarga());
		c.prepararPst(6, porte.getConcepto());
		c.prepararPst(7, porte.getPrecio());
		c.prepararPst(8, porte.isEsGrupaje());
		c.prepararPst(9, porte.getDescripcion());
		c.prepararPst(10, porte.getNif());

		if (!esInsert)
			c.prepararPst(11, porte.getIdPorte());

		respuesta = c.getPst().executeUpdate();

		c.cerrarObjCon();
		c.cerrarObjPst();

		return respuesta;

	}

	@Override
	public int borrar(Object entrada) throws SQLException {
		c = cc.crearConexion();
		int idPorte = (int) entrada;
		int respuesta = 0;

		c.setPst(c.getCon().prepareStatement(CRUDportes.borrarPorte));
		c.prepararPst(1, idPorte);
		respuesta = c.getPst().executeUpdate();

		c.cerrarObjCon();
		c.cerrarObjPst();

		return respuesta;

	}

	/**
	 * Método para buscar portes dependiendo del NIF de empresa.
	 * 
	 * @param entrada
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Object> buscarPorNif(Object entrada) throws SQLException {
		c = cc.crearConexion();
		String nifPorteBuscado = String.valueOf(entrada);
		ArrayList<Object> respuesta = new ArrayList<Object>();

		c.setPst(c.getCon().prepareStatement(CRUDportes.selectPortePorNif));
		c.prepararPst(1, nifPorteBuscado);
		c.setRs(c.getPst().executeQuery());

		String nBastidor, dni, concepto, descripcion, nif;
		int idPorte, kgCarga, volCarga;
		double precio;
		boolean esGrupaje;
		ResultSet rs = c.getRs();

		while (rs.next()) {
			idPorte = rs.getInt(1);
			nBastidor = rs.getString(2);
			dni = rs.getString(3);
			kgCarga = rs.getInt(4);
			volCarga = rs.getInt(5);
			concepto = rs.getString(6);
			precio = rs.getDouble(7);
			esGrupaje = rs.getBoolean(8);
			descripcion = rs.getString(9);
			nif = rs.getString(10);

			respuesta.add(new Porte(idPorte, nBastidor, dni, kgCarga, volCarga, concepto, precio, esGrupaje,
					descripcion, nif));
		}

		c.cerrarObjCon();
		c.cerrarObjPst();
		c.cerrarObjRs();
		rs.close();

		return respuesta;

	}

	/**
	 * Método de detección de portes por rango de fechas.
	 * 
	 * @param fIncio
	 * @param fFinal
	 * @param empresa
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Object> buscarPorFechas(String fIncio, String fFinal, String empresa) throws SQLException {
		ArrayList<Object> respuesta = new ArrayList<Object>();
		c = cc.crearConexion();
		c.setPst(c.getCon().prepareStatement(CRUDportes.getPorFecha));
		c.prepararPst(1, fIncio);
		c.prepararPst(2, fFinal);
		c.prepararPst(3, empresa);
		c.setRs(c.getPst().executeQuery());

		String nBastidor, dni, concepto, descripcion, nif;
		int idPorte, kgCarga, volCarga;
		double precio;
		boolean esGrupaje;
		ResultSet rs = c.getRs();

		while (rs.next()) {
			idPorte = rs.getInt(1);
			nBastidor = rs.getString(2);
			dni = rs.getString(3);
			kgCarga = rs.getInt(4);
			volCarga = rs.getInt(5);
			concepto = rs.getString(6);
			precio = rs.getDouble(7);
			esGrupaje = rs.getBoolean(8);
			descripcion = rs.getString(9);
			nif = rs.getString(10);

			respuesta.add(new Porte(idPorte, nBastidor, dni, kgCarga, volCarga, concepto, precio, esGrupaje,
					descripcion, nif));
		}

		c.cerrarObjCon();
		c.cerrarObjPst();
		c.cerrarObjRs();
		rs.close();

		return respuesta;

	}

}
