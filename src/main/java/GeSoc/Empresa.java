package GeSoc;

enum CategoriaEmpresa{
	MICRO,
	PEQUENA,
	MEDIANA_TRAMO_1,
	MEDIA_TRAMO_2
}

public class Empresa implements CategoriaEntidadJuridica {
	private String nombreCategoria;
	private CategoriaEmpresa categoria;
	
	@Override
	public String categoria() {
		return nombreCategoria + " " + categoria.toString();
	}
	
}
