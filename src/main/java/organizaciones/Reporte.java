package organizaciones;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import operaciones.OperacionDeEgreso;

public class Reporte {
	/*
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
	 */
	private Map<List<String>, List<OperacionDeEgreso>> egresosPorEtiqueta;
	
	public Reporte(List<OperacionDeEgreso> egresosDeLaEntidad) {
		egresosPorEtiqueta = egresosDeLaEntidad.stream()
				.collect(Collectors.groupingBy(OperacionDeEgreso::getEtiquetas));
	}

	public Map<List<String>, List<OperacionDeEgreso>> getEgresosPorEtiqueta() {
		return egresosPorEtiqueta;
	}
}
