package GeSoc;

public class ProveedorFisico extends Proveedor {
	
	private String nombre;
	private String apellido;
	private int dni;
	
	@Override
	public String nombre() {
		return nombre + " " + apellido;
	}

	@Override
	public int identificacion() {
		return dni;
	}

}
