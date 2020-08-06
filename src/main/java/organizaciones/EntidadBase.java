package organizaciones;

import organizaciones.reglasEntidades.CategoriaEntidad;

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

		categoria.ejecutarReglasAsignacionAEntidadJuridica(entidadJuridica);
		this.entidadJuridica = entidadJuridica;
	}
}