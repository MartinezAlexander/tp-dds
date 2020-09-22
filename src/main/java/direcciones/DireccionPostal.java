package direcciones;

import javax.persistence.*;

import persistencia.EntidadPersistente;

@Entity
public class DireccionPostal extends EntidadPersistente{
	
	@Column
	private String calle;
	@Column
	private int altura;
	@Column
	private int piso;
	@Column
	private String departamento;
	@ManyToOne(cascade = {CascadeType.ALL})
	private UbicacionML ubicacionML;
	
	public DireccionPostal(String calle, int altura, int piso, String departamento, String codigoPostal) {
		this.calle = calle;
		this.altura = altura;
		this.piso = piso;
		this.departamento = departamento;
		this.ubicacionML = ApiMercadoLibre.getInstance().obtenerUbicacionML(codigoPostal);
	}

	public DireccionPostal() {
	}

	public String getCalle() {
		return calle;
	}

	public int getAltura() {
		return altura;
	}

	public int getPiso() {
		return piso;
	}

	public String getDepartamento() {
		return departamento;
	}
	
	public String getProvincia() {
		return ubicacionML.getState().getName();
	}
	
	public String getPais() {
		return ubicacionML.getCountry().getName();
	}
	
	public String getCodigoPostal() {
		return ubicacionML.getZipCode();
	}
}
