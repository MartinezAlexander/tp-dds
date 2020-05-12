package proveedor;

public class ProveedorJuridico extends Proveedor {
	
	private String razonSocial;
	private int cuitCuil;

	public ProveedorJuridico(String direccionPostal, String razonSocial, int cuitCuil) {
		super(direccionPostal);
		this.razonSocial = razonSocial;
		this.cuitCuil = cuitCuil;
	}

	@Override
	public String nombre() {
		return razonSocial;
	}

	@Override
	public int identificacion() {
		return cuitCuil;
	}
	
}
