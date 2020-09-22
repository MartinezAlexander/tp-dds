package organizaciones;

import organizaciones.reglasEntidades.CategoriaEntidad;

import javax.persistence.*;

@Entity
public class EntidadBase extends Entidad {
	@Column
	private String descripcion;
	@ManyToOne
	private EntidadJuridica entidadJuridica;

	public EntidadBase() {
	}

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