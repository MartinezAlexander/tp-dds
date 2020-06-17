package proveedor;

import direcciones.DireccionPostal;

public class Proveedor {
	private DireccionPostal direccionPostal;
	
	private String razonSocial;
	private int cuitCuil;

	private String nombre;
	private String apellido;
	private int dni;

	public Proveedor(DireccionPostal direccionPostal, String razonSocial, int cuitCuil) {
		this.direccionPostal = direccionPostal;
		this.cuitCuil = cuitCuil;
		this.razonSocial = razonSocial;
	}
	
	public Proveedor(DireccionPostal direccionPostal, String nombre, String apellido, int dni) {
		this.direccionPostal = direccionPostal;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;

	}
	
	public DireccionPostal getDireccionPostal() {
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

