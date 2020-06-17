package usuarios;

import operaciones.OperacionDeEgreso;

public class MensajeRevision {
	private OperacionDeEgreso operacion;
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
