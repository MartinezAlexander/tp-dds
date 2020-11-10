package usuarios;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import operaciones.OperacionDeEgreso;
import persistencia.EntidadPersistente;

@Entity
public class MensajeRevision extends EntidadPersistente{
	@OneToOne
	private OperacionDeEgreso operacion;
	@Column
	private boolean resultadoValidacion;
	
	public MensajeRevision(){}
	
	public MensajeRevision(OperacionDeEgreso operacion, boolean resultadoValidacion) {
		this.operacion = operacion;
		this.resultadoValidacion = resultadoValidacion;
	}
	
	public OperacionDeEgreso getOperacion() {
		return operacion;
	}
//	public boolean getResultadoValidacion() {
//		return resultadoValidacion;
//	}
	public String getResultadoValidacion() {
		return resultadoValidacion ? "Validada" : "No Validada";
	}
	
}
