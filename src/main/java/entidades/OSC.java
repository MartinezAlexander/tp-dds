package entidades;

import entidades.CategoriaEntidadJuridica;

public class OSC implements CategoriaEntidadJuridica {
	private String nombreCategoria;

	@Override
	public String categoria() {
		return nombreCategoria;
	}
	
}
