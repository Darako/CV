package operacionesCRUD;

import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexionProxyBBDD.Conexion;
import pojo.Trabajador;
/**
 * Operaciones CRUD de empleados.
 */
/**
 * 
 * @author Jorge González Rodríguez y Javier Ruiz Rodríguez
 *
 */
public class CRUDempleados extends CRUDesquema {

	private static String selectAllEmpleado = "select * from \"Empleado\"";
	private static String updateEmpleado = "UPDATE \"Empleado\" SET dni=?, nombre=?, apellidos=?, \"fechaNacimiento\"=?, sexo=?, \"fechaAltaEmpleado\"=?, sueldo=?, rango=?	 WHERE dni=?";
	private static String borrarEmpleado = "delete from \"Empleado\" where dni = ?";
	private static String insertEmpleado = "INSERT INTO \"Empleado\"(dni, nombre, apellidos, \"fechaNacimiento\", sexo, \"fechaAltaEmpleado\",sueldo, rango) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
	private static String selectEmpleado = "select * from \"Empleado\" where dni = ?";
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
	public CRUDempleados() throws SQLException, IOException {
		super();
	}

	@Override
	public ArrayList<Object> buscarTodo() throws SQLException {
		c = cc.crearConexion();
		ArrayList<Object> respuesta = new ArrayList<Object>();

		c.setSt(c.getCon().createStatement());
		c.setRs(c.getSt().executeQuery(CRUDempleados.selectAllEmpleado));

		String dni, nombre, apellidos, sexo, rango;
		Date fechaNacimiento, fechaAltaEmpleado;
		Double sueldo;
		ResultSet rs = c.getRs();

		while (rs.next()) {
			dni = rs.getString(1);
			nombre = rs.getString(2);
			apellidos = rs.getString(3);
			fechaNacimiento = rs.getDate(4);
			sexo = rs.getString(5);
			fechaAltaEmpleado = rs.getDate(6);
			sueldo = rs.getDouble(7);
			rango = rs.getString(8);
			respuesta.add((Object) new Trabajador(dni, nombre, apellidos, fechaNacimiento, sexo, fechaAltaEmpleado,
					rango, sueldo));
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
		String dniBuscado = String.valueOf(entrada);
		Trabajador respuesta = null;

		c.setPst(c.getCon().prepareStatement(CRUDempleados.selectEmpleado));
		c.prepararPst(1, dniBuscado);
		c.setRs(c.getPst().executeQuery());

		String dni, nombre, apellidos, sexo, rango;
		Date fechaNacimiento, fechaAltaEmpleado;
		Double sueldo;
		ResultSet rs = c.getRs();

		while (rs.next()) {
			dni = rs.getString(1);
			nombre = rs.getString(2);
			apellidos = rs.getString(3);
			fechaNacimiento = rs.getDate(4);
			sexo = rs.getString(5);
			fechaAltaEmpleado = rs.getDate(6);
			sueldo = rs.getDouble(7);
			rango = rs.getString(8);
			respuesta = new Trabajador(dni, nombre, apellidos, fechaNacimiento, sexo, fechaAltaEmpleado, rango, sueldo);
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
		Trabajador empleado = (Trabajador) entrada;
		int respuesta = 0;

		if (esInsert)
			c.setPst(c.getCon().prepareStatement(CRUDempleados.insertEmpleado));
		else
			c.setPst(c.getCon().prepareStatement(CRUDempleados.updateEmpleado));

		c.prepararPst(1, empleado.getDni());
		c.prepararPst(2, empleado.getNombre());
		c.prepararPst(3, empleado.getApellidos());
		c.prepararPst(4, empleado.getFechaNacimiento());
		c.prepararPst(5, empleado.getSexo());
		c.prepararPst(6, empleado.getFechaAltaEmpleado());
		c.prepararPst(7, empleado.getSueldo());
		c.prepararPst(8, empleado.getRango());

		if (!esInsert)
			c.prepararPst(9, empleado.getDni());

		respuesta = c.getPst().executeUpdate();

		c.cerrarObjCon();
		c.cerrarObjPst();

		return respuesta;

	}

	@Override
	public int borrar(Object entrada) throws SQLException {
		c = cc.crearConexion();
		String dniEmpleado = String.valueOf(entrada);
		int respuesta = 0;

		c.setPst(c.getCon().prepareStatement(CRUDempleados.borrarEmpleado));
		c.prepararPst(1, dniEmpleado);
		respuesta = c.getPst().executeUpdate();

		c.cerrarObjCon();
		c.cerrarObjPst();

		return respuesta;

	}
}
