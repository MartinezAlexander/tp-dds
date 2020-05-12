package entidades;

public class Empresa implements CategoriaEntidadJuridica {
	private String nombreCategoria;
	private CategoriaEmpresa categoria;
	
	@Override
	public String categoria() {
		return nombreCategoria + " " + categoria.toString();
	}
	
}
