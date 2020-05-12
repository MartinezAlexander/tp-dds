package proveedor;

public abstract class Proveedor {
	private String direccionPostal;
	
	public abstract String nombre();
	public abstract int identificacion();

	public Proveedor(String direccionPostal) {
		this.direccionPostal = direccionPostal;
	}

	public String getDireccionPostal() {
		return direccionPostal;
	}
}

