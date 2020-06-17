package direcciones;

public class DireccionPostal {
	private String calle;
	private int altura;
	private int piso;
	private int departamento;
	private UbicacionML ubicacionML;
	
	public DireccionPostal(String calle, int altura, int piso, int departamento, String codigoPostal) {
		this.calle = calle;
		this.altura = altura;
		this.piso = piso;
		this.departamento = departamento;
		this.ubicacionML = ApiMercadoLibre.obtenerUbicacionML(codigoPostal);
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

	public int getDepartamento() {
		return departamento;
	}

	public UbicacionML getUbicacionML() {
		return ubicacionML;
	}
	
	
}
