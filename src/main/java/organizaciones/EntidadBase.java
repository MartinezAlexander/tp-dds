package organizaciones;

import organizaciones.reglasEntidades.CategoriaEntidad;

import javax.persistence.*;

@Entity
public class EntidadBase extends Entidad {
	@Column
	private String descripcion;
	@ManyToOne
	private EntidadJuridica entidadJuridica;

	public EntidadBase(){}
	
	public EntidadBase(String nombreFicticio, CategoriaEntidad categoria, String descripcion, Organizacion organizacion) {
		super(nombreFicticio, categoria, organizacion);
		this.descripcion = descripcion;
	}

	public EntidadBase(String nombreFicticio, CategoriaEntidad categoria, String descripcion, Organizacion organizacion, EntidadJuridica entidadJuridica) {
		this(nombreFicticio, categoria, descripcion, organizacion);
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