package composite;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;

import factorias.FactoriaCRUD;
import iterador.Agregado;
import iterador.AgregadoConcreto;
import iterador.Iterador;
import operacionesCRUD.CRUDempleados;
import operacionesCRUD.CRUDsubordinados;
import pojo.Subordinado;
import pojo.Trabajador;

/**
 * El compromiso de esta clase es el de leer de BBDD la estructura composite almacenada y recrearla para la correcta visualización de los datos.
 */
/**
 * 
 * @author Jorge González Rodríguez y Javier Ruiz Rodríguez
 *
 */
public class AccesoEmpleados {

	/**
	 * Array con empleados.
	 */
	private ArrayList<Object> empleados;
	/**
	 * Array con jefes.
	 */
	private ArrayList<Object> jefes;
	/**
	 * Factoría para la generación de objetos CRUD.
	 */
	private FactoriaCRUD fc;
	/**
	 * Objeto CRUD para los empleados.
	 */
	private CRUDempleados ce;
	/**
	 * Objetos CRUD para los subordinados.
	 */
	private CRUDsubordinados cs;
	/**
	 * Estructura que representa el la jerarquía de subordinados del sistema.
	 */
	private ListMultimap<String, String> subordinados;
	/**
	 * Estructura que almacena todos los jefes del sistema.
	 */
	private HashMap<String, TrabajadorC> trabajadoresComp;
	/**
	 * Objeto Jefe para la representación del patron Composite.
	 */
	private Jefe jefe;

	/**
	 * Constructor principal que crea la jerarquía leyendo de BBDD. Si no hay jefes muestra un error por pantalla.
	 * @param dniJefe
	 * @throws SQLException
	 * @throws IOException
	 */
	public AccesoEmpleados(String dniJefe) throws SQLException, IOException {
		this.fc = new FactoriaCRUD();
		this.ce = (CRUDempleados) fc.crearCRUD(FactoriaCRUD.TIPO_EMPLEADO);
		this.cs = (CRUDsubordinados) fc.crearCRUD(FactoriaCRUD.TIPO_SUBORDINADO);
		this.empleados = new ArrayList<>(ce.buscarTodo());
		this.jefes = new ArrayList<>(cs.buscarTodo());
		this.subordinados = ArrayListMultimap.create();
		this.trabajadoresComp = new HashMap<String, TrabajadorC>();
		this.cargarTrabajadores();
		if (!subordinados.isEmpty())
			this.jefe = (Jefe) trabajadoresComp.get(dniJefe);
		else
			this.jefe = null;
	}

	/**
	 * Método de carga de los trabajadores. Se lee de BBDD y se completan las variables 'subordinados' y 'trabajadoresComp'.
	 */
	private void cargarTrabajadores() {

		Agregado agregado = new AgregadoConcreto(jefes);
		Iterador iterador = agregado.crearIterador();

		try {
			while (iterador.hayMas()) {
				Subordinado s = (Subordinado) iterador.elementoActual();
				subordinados.put(s.getDniJefe(), s.getDniSubordinado());
				iterador.siguiente();
			}
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Error: " + e.toString());
		}

		agregado = new AgregadoConcreto(empleados);
		iterador = agregado.crearIterador();

		try {
			while (iterador.hayMas()) {

				Trabajador t = (Trabajador) iterador.elementoActual();
				if (subordinados.containsKey(t.getDni()))
					trabajadoresComp.put(t.getDni(), new Jefe(t.getRango(), t.getSueldo()));
				else
					trabajadoresComp.put(t.getDni(), new Empleado(t.getRango(), t.getSueldo()));
				iterador.siguiente();
			}
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Error: " + e.toString());
		}

		for (Map.Entry entrada : subordinados.entries()) {

			Jefe t = (Jefe) trabajadoresComp.get((String) entrada.getKey());

			t.anadirSubordinado(trabajadoresComp.get((String) (entrada.getValue())));
			trabajadoresComp.put(String.valueOf(entrada.getKey()), t);
		}

	}

	/**
	 * Método para mostrar la cuantía total de los suldos del sistema.
	 * 
	 * @return
	 */
	public Double getSueldos() {
		if (jefe != null)
			return jefe.getSalarios();
		else
			return 0.0;
	}

	/**
	 * Método para la visualización de la jerarquía existente entre empleados dentro del sistema.
	 * 
	 * @return
	 */
	public String getDescripciones() {
		if (jefe != null)
			return jefe.getDescripcion();
		else
			return "No existen subordinados en la BD.";
	}
}
