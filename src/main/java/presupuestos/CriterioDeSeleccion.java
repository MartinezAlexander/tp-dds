package presupuestos;

import java.util.Comparator;
import java.util.List;

public enum CriterioDeSeleccion {
	MENOR_VALOR(){
		@Override
		public Presupuesto elegirPresupuesto(List<Presupuesto> presupuestos) {
			return presupuestos.stream().min(Comparator.comparing(Presupuesto::getCotizacion)).get();
		}
	};
	
	public Presupuesto elegirPresupuesto(List<Presupuesto> presupuestos){
		return null;
	}
}
