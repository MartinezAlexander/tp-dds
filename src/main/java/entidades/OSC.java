package entidades;

import entidades.CategoriaEntidadJuridica;

public class OSC implements CategoriaEntidadJuridica {
	private static final String NOMBRE_CATEGORIA = "Organizaciones del sector social";

	@Override
	public String categoria() {
		return NOMBRE_CATEGORIA;
	}
	
}
