package presupuestos;

import persistencia.EntidadPersistente;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "criterio_elegido")
public abstract class CriterioDeSeleccion extends EntidadPersistente {
	public Presupuesto elegirPresupuesto(List<Presupuesto> presupuestos) {
		return null; //se overridea siempre
	}
}
