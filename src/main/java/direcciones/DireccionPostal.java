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

//	@ManyToOne(cascade = {CascadeType.ALL})
//	private UbicacionML ubicacionML;
	
	public DireccionPostal(){}
	
	public DireccionPostal(String calle, int altura, int piso, String departamento, String codigoPostal) {
		this.calle = calle;
		this.altura = altura;
		this.piso = piso;
		this.departamento = departamento;
		//TODO: Nota => Creo que cambiaron algo desde la api de mercadolibre y rompia por eso. lo comento por ahora
		// total no es prioridad que ande esto.
//		this.ubicacionML = ApiMercadoLibre.getInstance().obtenerUbicacionML(codigoPostal);
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
	
//	public String getProvincia() {
//		return ubicacionML.getState().getName();
//	}
//
//	public String getPais() {
//		return ubicacionML.getCountry().getName();
//	}
//
//	public String getCodigoPostal() {
//		return ubicacionML.getZipCode();
//	}
}
