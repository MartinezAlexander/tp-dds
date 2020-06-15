package presupuestos;

import java.util.List;

public interface CriterioDeSeleccion {
	Presupuesto elegirPresupuesto(List<Presupuesto> presupuestos);
}
