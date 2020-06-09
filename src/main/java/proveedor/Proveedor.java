package proveedor;

public class Proveedor {
	private String direccionPostal;
	
	private String razonSocial;
	private int cuitCuil;

	private String nombre;
	private String apellido;
	private int dni;

	public Proveedor(String direccionPostal, String nombre, String apellido, int dni, String razonSocial, int cuitCuil) {
		this.direccionPostal = direccionPostal;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.cuitCuil = cuitCuil;
		this.razonSocial = razonSocial;
	}
	
	public String getDireccionPostal() {
		return direccionPostal;
	}

	public String getRazonSocial() {
		return razonSocial;
	}
	
	public String getNombreYApellido() {
		return nombre + " " + apellido;
	}

	public int getDni() {
		return dni;
	}

	public int getCuitCuil() {
		return cuitCuil;
	}
	
}

