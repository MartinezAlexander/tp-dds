package testDirecciones;

import static org.junit.Assert.*;

import org.junit.Test;

import Direcciones.ApiMercadoLibre;

public class testDireccionPostal {

	@Test
	public void test() {
		String json = ApiMercadoLibre.Instance().obtenerJson("5000");
		assertTrue(json.contains("state"));
		assertTrue(json.contains("country"));
	}

}
