package proveedor;

public class ProveedorFisico extends Proveedor {
	
	private String nombre;
	private String apellido;
	private int dni;

	public ProveedorFisico(String direccionPostal, String nombre, String apellido, int dni) {
		super(direccionPostal);
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
	}

	@Override
	public String nombre() {
		return nombre + " " + apellido;
	}

	@Override
	public int identificacion() {
		return dni;
	}

}
