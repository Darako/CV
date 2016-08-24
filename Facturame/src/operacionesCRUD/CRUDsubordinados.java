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
import pojo.Subordinado;
/**
 * Operaciones CRUD de subordinados.
 */
/**
 * 
 * @author Jorge González Rodríguez y Javier Ruiz Rodríguez
 *
 */
public class CRUDsubordinados extends CRUDesquema {

	private static String selectAllSubordinado = "select * from \"Subordinado\"";
	private static String updateSubordinado = "UPDATE \"Subordinado\" SET \"dniSubordinado\"=?, \"dniJefe\"=? WHERE \"dniJefe\" =?";
	private static String borrarSubordinado = "delete from \"Subordinado\" where \"dniJefe\" = ?";
	private static String insertSubordinado = "INSERT INTO \"Subordinado\"(\"dniJefe\",\"dniSubordinado\") VALUES (?, ?)";
	private static String selectSubordinado = "select * from \"Subordinado\" where \"dniJefe\" = ?";
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
	public CRUDsubordinados() throws SQLException, IOException {
		super();
	}

	@Override
	public ArrayList<Object> buscarTodo() throws SQLException {
		c = cc.crearConexion();
		ArrayList<Object> respuesta = new ArrayList<Object>();

		c.setSt(c.getCon().createStatement());
		c.setRs(c.getSt().executeQuery(CRUDsubordinados.selectAllSubordinado));

		String dniJefe, dniSubordinado;
		ResultSet rs = c.getRs();

		while (rs.next()) {
			dniJefe = rs.getString(1);
			dniSubordinado = rs.getString(2);
			respuesta.add((Object) new Subordinado(dniJefe, dniSubordinado));
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
		String dniJefeBuscado = String.valueOf(entrada);
		Object respuesta = null;

		c.setPst(c.getCon().prepareStatement(CRUDsubordinados.selectSubordinado));
		c.prepararPst(1, dniJefeBuscado);
		c.setRs(c.getPst().executeQuery());

		String dniJefe, dniSubordinado;
		ResultSet rs = c.getRs();

		while (rs.next()) {
			dniJefe = rs.getString(1);
			dniSubordinado = rs.getString(2);
			respuesta = new Subordinado(dniJefe, dniSubordinado);
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
		Subordinado subordinado = (Subordinado) entrada;
		int respuesta = 0;

		if (esInsert)
			c.setPst(c.getCon().prepareStatement(CRUDsubordinados.insertSubordinado));
		else
			c.setPst(c.getCon().prepareStatement(CRUDsubordinados.updateSubordinado));

		c.prepararPst(1, subordinado.getDniJefe());
		c.prepararPst(2, subordinado.getDniSubordinado());

		if (!esInsert)
			c.prepararPst(9, subordinado.getDniJefe());

		respuesta = c.getPst().executeUpdate();

		c.cerrarObjCon();
		c.cerrarObjPst();

		return respuesta;

	}

	@Override
	public int borrar(Object entrada) throws SQLException {
		c = cc.crearConexion();
		String dniJefe = String.valueOf(entrada);
		int respuesta = 0;

		c.setPst(c.getCon().prepareStatement(CRUDsubordinados.borrarSubordinado));
		c.prepararPst(1, dniJefe);
		respuesta = c.getPst().executeUpdate();

		c.cerrarObjCon();
		c.cerrarObjPst();

		return respuesta;

	}

}
