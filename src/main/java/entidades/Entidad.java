package entidades;

public abstract class Entidad {
	protected String nombreFicticio;

	public Entidad(String nombreFicticio) {
		this.nombreFicticio = nombreFicticio;
	}

	public String getNombreFicticio() {
		return nombreFicticio;
	}
}

