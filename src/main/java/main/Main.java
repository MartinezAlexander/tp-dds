package main;
import java.util.Date;

import direcciones.ApiMercadoLibre;
import operaciones.ValidadorDeOperaciones;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

public class Main{
	public static void main(String[] args) {
		ValidadorDeOperaciones.getInstance().programarValidacionOperacionesPendientes(new Date(), 1000);
	}
}

