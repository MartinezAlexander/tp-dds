package organizaciones;

import organizaciones.reglasEntidades.CategoriaEntidad;

import javax.persistence.*;

@Entity
public class EntidadJuridica extends Entidad {
	@Column
	private String razonSocial;
	@Column
	private int cuit;
	@Column
	private String direccionPostal;
	@Column
	private int codigoInscripcionIGJ;
	@Enumerated(EnumType.STRING)
	private CategoriaEntidadJuridica categoriaEntidadJuridica;

	public EntidadJuridica(){}
	
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
		categoria.ejecutarReglasNuevaEntidadBase();
		entidadBase.setEntidadJuridica(this);
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
