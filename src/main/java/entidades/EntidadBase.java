package entidades;

public class EntidadBase extends Entidad {
	private String descripcion;
	private EntidadJuridica entidadJuridica;

	public EntidadBase(String nombreFicticio, String descripcion) {
		super(nombreFicticio);
		this.descripcion = descripcion;
	}

	public EntidadBase(String nombreFicticio, String descripcion, EntidadJuridica entidadJuridica) {
		this(nombreFicticio, descripcion);
		this.entidadJuridica = entidadJuridica;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public EntidadJuridica getEntidadJuridica() {
		return entidadJuridica;
	}
}