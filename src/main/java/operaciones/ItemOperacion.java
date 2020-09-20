package operaciones;

import persistencia.EntidadPersistente;
import presupuestos.Presupuesto;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ItemOperacion extends EntidadPersistente{
	
	@Column
	private String descripcion;


	public ItemOperacion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getValorSegunPresupuesto(Presupuesto presupuesto){
		return new BigDecimal(0); //TODO: habria que hacer esto
	}

	public String getDescripcion() {
		return descripcion;
	}

}
