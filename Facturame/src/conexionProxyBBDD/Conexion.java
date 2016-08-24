package conexionProxyBBDD;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

/**
 * Objeto de conexión JDBC a la base de datos PostgreSQL. CAMBIAR ESTA CLASE PARA USAR OTRA AUTENTICACIÓN CONTRA LA BBDD.
 */
/**
 * 
 * @author Jorge González Rodríguez y Javier Ruiz Rodríguez
 *
 */
public class Conexion {

	/**
	 * Objeto para la conexión con la BBDD.
	 */
	private Connection con;
	/**
	 * Objeto para el almacenamiento de las consultas.
	 */
	private Statement st;
	/**
	 * Objeto enriquecido para el almacenamiento de las consultas.
	 */
	private PreparedStatement pst;
	/**
	 * Objeto para el almacenamiento de los resultados consultas.
	 */
	private ResultSet rs;
	/**
	 * URL para acceder al servidor.
	 */
	private String url;
	/*
	 * usuario de la BBDD.
	 */
	private String userBd;
	/*
	 * contraseña de la BBDD.
	 */
	private String passwordBd;

	/**
	 * Constructor principal.
	 * 
	 * @throws SQLException
	 */
	public Conexion() throws SQLException {
		this.url = "jdbc:postgresql://localhost:5432/facturame";
		this.userBd = "postgres";
		this.passwordBd = "postgres";
		this.con = DriverManager.getConnection(this.url, this.userBd, this.passwordBd);
	}

	public Connection getCon() {
		return con;
	}

	public Statement getSt() {
		return st;
	}

	public void setSt(Statement st) {
		this.st = st;
	}

	public PreparedStatement getPst() {
		return pst;
	}

	public void setPst(PreparedStatement pst) {
		this.pst = pst;
	}

	public ResultSet getRs() {
		return rs;
	}

	public void setRs(ResultSet rs) {
		this.rs = rs;
	}

	public String getUrl() {
		return url;
	}

	public String getUserBd() {
		return userBd;
	}

	public String getPasswordBd() {
		return passwordBd;
	}

	public void cerrarObjCon() throws SQLException {
		this.con.close();
	}

	public void cerrarObjSt() throws SQLException {
		this.st.close();
	}

	public void cerrarObjRs() throws SQLException {
		this.rs.close();
	}

	public void cerrarObjPst() throws SQLException {
		this.pst.close();
	}

	public void prepararPst(int posicion, String cadena) throws SQLException {
		this.pst.setString(posicion, cadena);
	}

	public void prepararPst(int posicion, int entero) throws SQLException {
		this.pst.setInt(posicion, entero);
	}

	public void prepararPst(int posicion, Date fecha) throws SQLException {
		this.pst.setDate(posicion, fecha);

	}

	public void prepararPst(int posicion, double decimal) throws SQLException {
		this.pst.setDouble(posicion, decimal);
	}

	public void prepararPst(int posicion, boolean booleano) throws SQLException {
		this.pst.setBoolean(posicion, booleano);

	}

	public void prepararPst(int posicion, Timestamp tiempo) throws SQLException {
		this.pst.setTimestamp(posicion, tiempo);
	}

}
