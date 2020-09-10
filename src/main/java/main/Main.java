package main;
import java.util.Date;

import direcciones.ApiMercadoLibre;
import direcciones.City;
import operaciones.ValidadorDeOperaciones;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import javax.persistence.EntityManager;

public class Main{
	public static void main(String[] args) {
		//ValidadorDeOperaciones.getInstance().programarValidacionOperacionesPendientes(new Date(), 1000);

		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
	}
}

