package entidades;

public class EntidadJuridica extends Entidad {
	private String razonSocial;
	private int cuit;
	private String direccionPostal;
	private int codigoInscripcionIGJ;
	private CategoriaEntidadJuridica categoria;

	public EntidadJuridica(String nombreFicticio, String razonSocial, int cuit, String direccionPostal,
						   CategoriaEntidadJuridica categoria) {
		super(nombreFicticio);
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
}
