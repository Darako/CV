package factorias;

import java.io.IOException;
import java.sql.SQLException;

import operacionesCRUD.CRUDcamiones;
import operacionesCRUD.CRUDempleados;
import operacionesCRUD.CRUDempresa;
import operacionesCRUD.CRUDesquema;
import operacionesCRUD.CRUDportes;
import operacionesCRUD.CRUDsubordinados;
import operacionesCRUD.CRUDusuariosSistema;
import operacionesCRUD.CRUDviajes;
/**
 * 
 * @author Jorge González Rodríguez y Javier Ruiz Rodríguez
 *
 */
public class FactoriaCRUD {

	public static int TIPO_CAMION = 1;
	public static int TIPO_EMPLEADO = 2;
	public static int TIPO_EMPRESA = 3;
	public static int TIPO_PORTE = 5;
	public static int TIPO_SUBORDINADO = 6;
	public static int TIPO_US_SISTEMA = 7;
	public static int TIPO_VIAJE = 8;

	public CRUDesquema crearCRUD(int tipo) throws SQLException, IOException {
		switch (tipo) {
		case 1:
			return new CRUDcamiones();
		case 2:
			return new CRUDempleados();
		case 3:
			return new CRUDempresa();
		case 5:
			return new CRUDportes();
		case 6:
			return new CRUDsubordinados();
		case 7:
			return new CRUDusuariosSistema();
		case 8:
			return new CRUDviajes();
		default:
			return null;

		}
	}

}
