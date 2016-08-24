package operacionesCRUD;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexionProxyBBDD.Conexion;
import pojo.Empresa;
/**
 * Operaciones CRUD de empresas.
 */
/**
 * 
 * @author Jorge González Rodríguez y Javier Ruiz Rodríguez
 *
 */
public class CRUDempresa extends CRUDesquema {

	private static String selectAllEmpresa = "select * from \"Empresa\"";
	private static String updateEmpresa = "UPDATE \"Empresa\" SET \"NIF\"=?, \"nEmpresa\"=?, \"Dirección\"=?, email=?, telefono=? WHERE \"NIF\" = ?";
	private static String borrarEmpresa = "delete from \"Empresa\" where \"NIF\" = ?";
	private static String insertEmpresa = "INSERT INTO \"Empresa\"(\"NIF\", \"nEmpresa\", \"Dirección\", email, telefono)VALUES (?, ?, ?, ?, ?)";
	private static String selectEmpresa = "select * from \"Empresa\" where \"NIF\" = ?";
	private static String selectEmpresaNombre = "select * from \"Empresa\" where \"nEmpresa\" = ?";
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
	public CRUDempresa() throws SQLException, IOException {
		super();
	}

	@Override
	public ArrayList<Object> buscarTodo() throws SQLException {
		c = cc.crearConexion();
		ArrayList<Object> respuesta = new ArrayList<Object>();

		c.setSt(c.getCon().createStatement());
		c.setRs(c.getSt().executeQuery(CRUDempresa.selectAllEmpresa));

		String nif, empresa, direccion, mail;
		int nTelefono;
		ResultSet rs = c.getRs();

		while (rs.next()) {
			nif = rs.getString(1);
			empresa = rs.getString(2);
			direccion = rs.getString(3);
			mail = rs.getString(4);
			nTelefono = rs.getInt(5);

			respuesta.add((Object) new Empresa(nif, empresa, direccion, nTelefono, mail));
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
		String nifBuscado = String.valueOf(entrada);
		Empresa respuesta = null;

		c.setPst(c.getCon().prepareStatement(CRUDempresa.selectEmpresa));
		c.prepararPst(1, nifBuscado);
		c.setRs(c.getPst().executeQuery());

		String nif, empresa, direccion, mail;
		int nTelefono;
		ResultSet rs = c.getRs();

		while (rs.next()) {
			nif = rs.getString(1);
			empresa = rs.getString(2);
			direccion = rs.getString(3);
			mail = rs.getString(4);
			nTelefono = rs.getInt(5);

			respuesta = new Empresa(nif, empresa, direccion, nTelefono, mail);
		}

		c.cerrarObjCon();
		c.cerrarObjPst();
		c.cerrarObjRs();
		rs.close();

		return (Object) respuesta;

	}
	
	/**
	 * Método que devuelve una empresa buscándola por el nombre.
	 * 
	 * @param entrada
	 * @return
	 * @throws SQLException
	 */
	public Object buscarUnoNombre(Object entrada) throws SQLException {
		c = cc.crearConexion();
		String nifBuscado = String.valueOf(entrada);
		Empresa respuesta = null;

		c.setPst(c.getCon().prepareStatement(CRUDempresa.selectEmpresaNombre));
		c.prepararPst(1, nifBuscado);
		c.setRs(c.getPst().executeQuery());

		String nif, empresa, direccion, mail;
		int nTelefono;
		ResultSet rs = c.getRs();

		while (rs.next()) {
			nif = rs.getString(1);
			empresa = rs.getString(2);
			direccion = rs.getString(3);
			mail = rs.getString(4);
			nTelefono = rs.getInt(5);

			respuesta = new Empresa(nif, empresa, direccion, nTelefono, mail);
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
		Empresa empresa = (Empresa) entrada;
		int respuesta = 0;

		if (esInsert)
			c.setPst(c.getCon().prepareStatement(CRUDempresa.insertEmpresa));
		else
			c.setPst(c.getCon().prepareStatement(CRUDempresa.updateEmpresa));

		c.prepararPst(1, empresa.getNif());
		c.prepararPst(2, empresa.getEmpresa());
		c.prepararPst(3, empresa.getDireccion());
		c.prepararPst(4, empresa.getEmail());
		c.prepararPst(5, empresa.getnTelefono());

		if (!esInsert)
			c.prepararPst(6, empresa.getNif());

		respuesta = c.getPst().executeUpdate();

		c.cerrarObjCon();
		c.cerrarObjPst();

		return respuesta;

	}

	@Override
	public int borrar(Object entrada) throws SQLException {
		c = cc.crearConexion();
		String nif = String.valueOf(entrada);
		int respuesta = 0;

		c.setPst(c.getCon().prepareStatement(CRUDempresa.borrarEmpresa));
		c.prepararPst(1, nif);
		respuesta = c.getPst().executeUpdate();

		c.cerrarObjCon();
		c.cerrarObjPst();

		return respuesta;

	}

}
