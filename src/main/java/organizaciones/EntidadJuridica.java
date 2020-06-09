package organizaciones;

public class EntidadJuridica implements Entidad {
	private String razonSocial;
	private int cuit;
	private String direccionPostal;
	private int codigoInscripcionIGJ;
	private CategoriaEntidadJuridica categoria;
	protected String nombreFicticio;

	public EntidadJuridica(String nombreFicticio, String razonSocial, int cuit, String direccionPostal,
						   CategoriaEntidadJuridica categoria) {
		this.nombreFicticio = nombreFicticio;
		this.razonSocial = razonSocial;
		this.cuit = cuit;
		this.direccionPostal = direccionPostal;
		this.categoria = categoria;
	}

	public EntidadJuridica(String nombreFicticio, String razonSocial, int cuit, String direccionPostal,
						   int codigoInscripcionIGJ, CategoriaEntidadJuridica categoria) {
		this(nombreFicticio, razonSocial, cuit, direccionPostal, categoria);
		this.codigoInscripcionIGJ = codigoInscripcionIGJ;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public int getCuit() {
		return cuit;
	}

	public String getDireccionPostal() {
		return direccionPostal;
	}

	public int getCodigoInscripcionIGJ() {
		return codigoInscripcionIGJ;
	}

	public CategoriaEntidadJuridica getCategoria() {
		return categoria;
	}
	
	public String getNombreFicticio() {
		return nombreFicticio;
	}
}
