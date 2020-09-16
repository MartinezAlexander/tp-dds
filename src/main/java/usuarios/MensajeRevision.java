package usuarios;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import operaciones.OperacionDeEgreso;
import persistencia.EntidadPersistente;

@Entity
public class MensajeRevision extends EntidadPersistente{
	@OneToOne(targetEntity = OperacionDeEgreso.class)
	private OperacionDeEgreso operacion;
	@Column
	private boolean resultadoValidacion;
	
	public MensajeRevision(OperacionDeEgreso operacion, boolean resultadoValidacion) {
		this.operacion = operacion;
		this.resultadoValidacion = resultadoValidacion;
	}
	
	public OperacionDeEgreso getOperacion() {
		return operacion;
	}
	public boolean esValido() {
		return resultadoValidacion;
	}
	
}
