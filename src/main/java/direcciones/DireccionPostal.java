package direcciones;

public class DireccionPostal {
	private String calle;
	private int altura;
	private int piso;
	private Departamento departamento;
	private UbicacionML ubicacionML;
	
	public DireccionPostal(String calle, int altura, int piso, Departamento departamento, String codigoPostal) {
		this.calle = calle;
		this.altura = altura;
		this.piso = piso;
		this.departamento = departamento;
		this.ubicacionML = ApiMercadoLibre.getInstance().obtenerUbicacionML(codigoPostal);
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

	public Departamento getDepartamento() {
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
