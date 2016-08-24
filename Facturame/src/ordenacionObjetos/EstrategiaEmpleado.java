package ordenacionObjetos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import pojo.Trabajador;
/**
 * Estrategia específica para la ordenación de los empleados.
 */
/**
 * 
 * @author Jorge González Rodríguez y Javier Ruiz Rodríguez
 *
 */
public class EstrategiaEmpleado implements Estrategia{

	@Override
	public void ordena(ArrayList<?> lista, String campo) {
		Comparator comparadorEmpleado = (Comparator) (Object o1, Object o2) -> {
			Trabajador t1 = (Trabajador) o1;
			Trabajador t2 = (Trabajador) o2;

			switch (campo) {
			case "nombre":
				return t1.getNombre().compareTo(t2.getNombre());
			case "apellidos":
				return t1.getApellidos().compareTo(t2.getApellidos());
			case "DNI":
				return t1.getDni().compareTo(t2.getDni());
			default:
				return t1.getNombre().compareTo(t2.getNombre());
			}
		};

		Collections.sort(lista, comparadorEmpleado);
		
	}

}
