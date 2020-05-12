package proveedor;

public class ProveedorJuridico extends Proveedor {
	
	private String razonSocial;
	private int cuitCuil;
	
	@Override
	public String nombre() {
		return razonSocial;
	}

	@Override
	public int identificacion() {
		return cuitCuil;
	}
	
}
