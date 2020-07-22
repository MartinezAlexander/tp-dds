package organizaciones;

public class EntidadBase extends Entidad {
	private String descripcion;
	private EntidadJuridica entidadJuridica;

	public EntidadBase(String nombreFicticio, CategoriaEntidad categoria, String descripcion) {
		super(nombreFicticio, categoria);
		this.descripcion = descripcion;
	}

	public EntidadBase(String nombreFicticio, CategoriaEntidad categoria, String descripcion, EntidadJuridica entidadJuridica) {
		this(nombreFicticio, categoria, descripcion);
		this.entidadJuridica = entidadJuridica;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public EntidadJuridica getEntidadJuridica() {
		return entidadJuridica;
	}

	public void setEntidadJuridica(EntidadJuridica entidadJuridica) {
		if (categoria.puedoSerParteDeEntidadJuridica())
			this.entidadJuridica = entidadJuridica;
	}
}