package organizaciones;

public class EntidadJuridica extends Entidad {
	private String razonSocial;
	private int cuit;
	private String direccionPostal;
	private int codigoInscripcionIGJ;
	private CategoriaEntidadJuridica categoriaEntidadJuridica;

	public EntidadJuridica(String nombreFicticio, CategoriaEntidad categoria, String razonSocial, int cuit, String direccionPostal,
						   CategoriaEntidadJuridica categoriaEntidadJuridica) {
		super(nombreFicticio, categoria);
		this.razonSocial = razonSocial;
		this.cuit = cuit;
		this.direccionPostal = direccionPostal;
		this.categoriaEntidadJuridica = categoriaEntidadJuridica;
	}

	public EntidadJuridica(String nombreFicticio, CategoriaEntidad categoria, String razonSocial, int cuit, String direccionPostal,
						   int codigoInscripcionIGJ, CategoriaEntidadJuridica categoriaEntidadJuridica) {
		this(nombreFicticio, categoria, razonSocial, cuit, direccionPostal, categoriaEntidadJuridica);
		this.codigoInscripcionIGJ = codigoInscripcionIGJ;
	}

	public void agregarEntidadBase(EntidadBase entidadBase){
		if (categoria.puedoAgregarEntidadBase()){
			entidadBase.setEntidadJuridica(this);
		}
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

	public CategoriaEntidadJuridica getCategoriaEntidadJuridica() {
		return categoriaEntidadJuridica;
	}
}
