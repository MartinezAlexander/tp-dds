package organizaciones;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import operaciones.OperacionDeEgreso;

public class Reporte {

	private Map<String, List<OperacionDeEgreso>> egresosPorEtiqueta;
	
	public Reporte(List<OperacionDeEgreso> egresosDeLaEntidad) {

		egresosPorEtiqueta = new HashMap<>();

		List<String> etiquetas = egresosDeLaEntidad.stream()
				.flatMap(operacionDeEgreso -> operacionDeEgreso.getEtiquetas().stream())
				.distinct()
				.collect(Collectors.toList());

		etiquetas.forEach(etiqueta -> {
			egresosPorEtiqueta.put(etiqueta,
					egresosDeLaEntidad.stream()
					.filter(egreso -> egreso.tieneEtiqueta(etiqueta))
					.collect(Collectors.toList()));
		});
	}

	public Map<String, List<OperacionDeEgreso>> getEgresosPorEtiqueta() {
		return egresosPorEtiqueta;
	}
}

/*

		Quisimos usar grouping by, pero como nosotros tenemos un listado de etiquetas en cada
		operacion, no se podia usar directamente. Osea podriamos mapear a un grupo de etiquetas
		y no a cada etiqueta en particular. El codigo quedaba algo asi:

		egresosPorEtiqueta = egresosDeLaEntidad.stream()
				.collect(Collectors.groupingBy(OperacionDeEgreso::getEtiquetas));

		----------------------------------------------------------------------------------------

		Antes agrupabamos por unica etiqueta, ahora se agrupa por el listado particular
		de etiquetas. No estoy seguro cual es la forma ideal, pero lo que pasaba antes era que
		por ejemplo:
			Tengo una Operacion 'A' que tiene etiquetas => Amoblamiento, [Nombre Provedor]
			Entonces en nuestro reporte apareceria dos veces la misma operacion

			Reporte:
				Amoblamiento
					...
					Operacion 'A'
					...
					Operacion 'X'
					...

				[Nombre Provedor]
					...
					Operacion 'A'
					...
			Fin reporte

		No estoy seguro que es lo que nos conviene, si que aparezca una unica vez o que haya menos
		agrupamiento. Como esta ahora quedaria algo asi:

			Reporte:
				Amoblamiento
					...
					Operacion 'X'
					...

				Amoblamiento-[Nombre Provedor]
					...
					Operacion 'A'
					...
			Fin reporte

		-------------------------------------------------------------------------------------------

		Al final dejamos el formato anterior (el primero que explico aca), ya que consideramos
		que era a lo que iba el enunciado.
		De todas formas dejamos el codigo de forma mas declarativa
	 */