package presupuestos;

import java.util.List;

public class MenorValor implements CriterioDeSeleccion{

	@Override
	public Presupuesto elegirPresupuesto(List<Presupuesto> presupuestos) {
		return presupuestos.stream().map(p -> p.getCotizacion()).min()); //(pseudocodigo) implementar bien
	}	
}
