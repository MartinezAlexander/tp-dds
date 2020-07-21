package presupuestos;

import java.util.Comparator;
import java.util.List;

public class MenorValor implements CriterioDeSeleccion{

	@Override
	public Presupuesto elegirPresupuesto(List<Presupuesto> presupuestos) {
		
		return presupuestos.stream().min(Comparator.comparing(Presupuesto::getCotizacion)).get();
	}	
}
