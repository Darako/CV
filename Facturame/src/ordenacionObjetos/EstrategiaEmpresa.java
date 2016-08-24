package ordenacionObjetos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import pojo.Empresa;
/**
 * Estrategia específica para la ordenación de las empresas.
 */
/**
 * 
 * @author Jorge González Rodríguez y Javier Ruiz Rodríguez
 *
 */
public class EstrategiaEmpresa implements Estrategia {

	@Override
	public void ordena(ArrayList<?> lista, String campo) {
		Comparator comparadorEmpresa = (Comparator) (Object o1, Object o2) -> {
			Empresa e1 = (Empresa) o1;
			Empresa e2 = (Empresa) o2;

			switch (campo) {
			case "NIF":
				return e1.getNif().compareTo(e2.getNif());
			case "direccion":
				return e1.getDireccion().compareTo(e2.getDireccion());
			case "telefono":
				return Integer.compare(e1.getnTelefono(), e2.getnTelefono());
			default:
				return e1.getNif().compareTo(e2.getNif());
			}
		};

		Collections.sort(lista, comparadorEmpresa);

	}

}
