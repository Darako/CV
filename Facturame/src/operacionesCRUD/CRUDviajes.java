package operacionesCRUD;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import conexionProxyBBDD.Conexion;
import pojo.Porte;
import pojo.Viaje;
/**
 * Operaciones CRUD de viajes.
 */
/**
 * 
 * @author Jorge González Rodríguez y Javier Ruiz Rodríguez
 *
 */
public class CRUDviajes extends CRUDesquema {

	private static String selectAllViaje = "select * from \"Viaje\"";
	private static String updateViaje = "UPDATE \"Viaje\" SET \"idViaje\"=?, \"lugarInicio\"=?, \"lugarDestino\"=?, \"horaInico\"=?, \"horaLlegada\"=?, \"fechaInicio\"=?, \"fechaLlegada\"=?, \"kmViaje\"=?, \"idPorte\"=? WHERE \"idViaje\" = ?";
	private static String borrarViaje = "delete from \"Vieje\" where \"idViaje\"=?";
	private static String insertViaje = "INSERT INTO \"Viaje\"(\"idViaje\", \"lugarInicio\", \"lugarDestino\", \"horaInico\", \"horaLlegada\", \"fechaInicio\", \"fechaLlegada\", \"kmViaje\", \"idPorte\") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static String selectViaje = "select * from \"Viaje\" where \"idViaje\" = ?";
	private static String getUltimoSerial = "SELECT last_value FROM \"Viaje_idViaje_seq\"";
	private static String setUltimoSerial = "ALTER SEQUENCE \"Viaje_idViaje_seq\" RESTART WITH ";
	private static String selectViajePorPorte = "select * from \"Viaje\" where \"idPorte\" = ?";
	/**
	 * Secuencia actual del id del viaje.
	 */
	private int idViajePeticion;
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
	public CRUDviajes() throws SQLException, IOException {
		super();
		this.idViajePeticion = 0;
	}

	/**
	 * Método para establecer en la base de datos la secuencia actual del id del viaje.
	 * 
	 * @param ultimoId
	 * @return
	 * @throws SQLException
	 */
	public int setUltimoId(int ultimoId) throws SQLException {
		c = cc.crearConexion();
		int respuesta = 0;

		c.setPst(c.getCon().prepareStatement(CRUDviajes.setUltimoSerial.concat(String.valueOf(ultimoId))));
		respuesta = c.getPst().executeUpdate();

		c.cerrarObjCon();
		c.cerrarObjPst();

		return respuesta;

	}

	/**
	 * Método para coger de la BBDD la secuencia actual del id del viaje.
	 * 
	 * @return
	 * @throws SQLException
	 */
	public int getUltimoId() throws SQLException {
		c = cc.crearConexion();
		c.setSt(c.getCon().createStatement());
		c.setRs(c.getSt().executeQuery(CRUDviajes.getUltimoSerial));

		ResultSet rs = c.getRs();

		while (rs.next()) {
			idViajePeticion = rs.getInt(1);
		}

		c.cerrarObjCon();
		c.cerrarObjSt();
		c.cerrarObjRs();
		rs.close();

		return idViajePeticion;
	}

	@Override
	public ArrayList<Object> buscarTodo() throws SQLException {
		c = cc.crearConexion();
		ArrayList<Object> respuesta = new ArrayList<Object>();

		c.setSt(c.getCon().createStatement());
		c.setRs(c.getSt().executeQuery(CRUDviajes.selectAllViaje));

		String lugarInicio, lugarDestino;
		int idPorte, kmViaje, idViaje;
		Timestamp horaInicio, horaLlegada;
		Date fechaInicio, fechaLlegada;
		ResultSet rs = c.getRs();

		while (rs.next()) {
			idViaje = rs.getInt(1);
			lugarInicio = rs.getString(2);
			lugarDestino = rs.getString(3);
			horaInicio = rs.getTimestamp(4);
			horaLlegada = rs.getTimestamp(5);
			fechaInicio = rs.getDate(6);
			fechaLlegada = rs.getDate(7);
			kmViaje = rs.getInt(8);
			idPorte = rs.getInt(9);

			respuesta.add((Object) new Viaje(idViaje, lugarInicio, lugarDestino, horaInicio, horaLlegada, fechaInicio,
					fechaLlegada, kmViaje, idPorte));
		}

		c.cerrarObjCon();
		c.cerrarObjSt();
		c.cerrarObjRs();
		rs.close();

		return respuesta;

	}

	public Object buscarUno(Object entrada) throws SQLException {
		c = cc.crearConexion();
		String idViajeBuscado = String.valueOf(entrada);
		Viaje respuesta = null;

		c.setPst(c.getCon().prepareStatement(CRUDviajes.selectViaje));
		c.prepararPst(1, idViajeBuscado);
		c.setRs(c.getPst().executeQuery());

		ResultSet rs = c.getRs();
		String lugarInicio, lugarDestino;
		int idPorte, kmViaje, idViaje;
		Timestamp horaInicio, horaLlegada;
		Date fechaInicio, fechaLlegada;

		while (rs.next()) {
			idViaje = rs.getInt(1);
			lugarInicio = rs.getString(2);
			lugarDestino = rs.getString(3);
			horaInicio = rs.getTimestamp(4);
			horaLlegada = rs.getTimestamp(5);
			fechaInicio = rs.getDate(6);
			fechaLlegada = rs.getDate(7);
			kmViaje = rs.getInt(8);
			idPorte = rs.getInt(9);

			respuesta = new Viaje(idViaje, lugarInicio, lugarDestino, horaInicio, horaLlegada, fechaInicio,
					fechaLlegada, kmViaje, idPorte);
		}

		c.cerrarObjCon();
		c.cerrarObjPst();
		c.cerrarObjRs();
		rs.close();

		return (Object) respuesta;

	}

	public int insertarActualizar(Object entrada, boolean esInsert) throws SQLException {
		c = cc.crearConexion();
		Viaje viaje = (Viaje) entrada;
		int respuesta = 0;

		if (esInsert)
			c.setPst(c.getCon().prepareStatement(CRUDviajes.insertViaje));
		else
			c.setPst(c.getCon().prepareStatement(CRUDviajes.updateViaje));

		c.prepararPst(1, viaje.getIdViaje());
		c.prepararPst(2, viaje.getLugarInicio());
		c.prepararPst(3, viaje.getLugarDestino());
		c.prepararPst(4, viaje.getHoraInicio());
		c.prepararPst(5, viaje.getHoraLlegada());
		c.prepararPst(6, viaje.getFechaInicio());
		c.prepararPst(7, viaje.getFechaLlegada());
		c.prepararPst(8, viaje.getKmViaje());
		c.prepararPst(9, viaje.getIdPorte());

		if (!esInsert)
			c.prepararPst(10, viaje.getIdViaje());

		respuesta = c.getPst().executeUpdate();

		c.cerrarObjCon();
		c.cerrarObjPst();

		return respuesta;

	}

	@Override
	public int borrar(Object entrada) throws SQLException {
		c = cc.crearConexion();
		int idViaje = (int) entrada;
		int respuesta = 0;

		c.setPst(c.getCon().prepareStatement(CRUDviajes.borrarViaje));
		c.prepararPst(1, idViaje);
		respuesta = c.getPst().executeUpdate();

		c.cerrarObjCon();
		c.cerrarObjPst();

		return respuesta;

	}

	public ArrayList<Object> buscarPorPorte(Object entrada) throws SQLException {
		int idPorteBuscado = (int) entrada;
		ArrayList<Object> respuesta = new ArrayList<Object>();
		c = cc.crearConexion();

		c.setPst(c.getCon().prepareStatement(CRUDviajes.selectViajePorPorte));
		c.prepararPst(1, idPorteBuscado);
		c.setRs(c.getPst().executeQuery());

		String lugarInicio, lugarDestino;
		int idPorte, kmViaje, idViaje;
		Timestamp horaInicio, horaLlegada;
		Date fechaInicio, fechaLlegada;
		ResultSet rs = c.getRs();

		while (rs.next()) {
			idViaje = rs.getInt(1);
			lugarInicio = rs.getString(2);
			lugarDestino = rs.getString(3);
			horaInicio = rs.getTimestamp(4);
			horaLlegada = rs.getTimestamp(5);
			fechaInicio = rs.getDate(6);
			fechaLlegada = rs.getDate(7);
			kmViaje = rs.getInt(8);
			idPorte = rs.getInt(9);

			respuesta.add((Object) new Viaje(idViaje, lugarInicio, lugarDestino, horaInicio, horaLlegada, fechaInicio,
					fechaLlegada, kmViaje, idPorte));
		}

		c.cerrarObjCon();
		c.cerrarObjPst();
		c.cerrarObjRs();
		rs.close();

		return respuesta;

	}

}
