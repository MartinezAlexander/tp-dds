package organizaciones;

public class EntidadBase implements Entidad {
	private String descripcion;
	private EntidadJuridica entidadJuridica;
	protected String nombreFicticio;

	public EntidadBase(String nombreFicticio, String descripcion) {
		this.nombreFicticio = nombreFicticio;
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
	public String getNombreFicticio() {
		return nombreFicticio;
	}
}