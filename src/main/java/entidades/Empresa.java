package entidades;

public class Empresa implements CategoriaEntidadJuridica {
	private static final String NOMBRE_CATEGORIA = "Empresa";

	private CategoriaEmpresa categoria;

	public Empresa(CategoriaEmpresa categoria) {
		this.categoria = categoria;
	}

	@Override
	public String categoria() {
		return NOMBRE_CATEGORIA + " " + categoria.toString();
	}
	
}
