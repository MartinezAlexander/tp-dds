package testDirecciones;

import static org.junit.Assert.*;

import org.junit.Test;

import Direcciones.ApiMercadoLibre;
import Direcciones.UbicacionML;

public class testDireccionPostal {
	
	@Test
	public void testGson() {
		UbicacionML ubicacionML = ApiMercadoLibre.obtenerUbicacionML("5000");
		assertEquals("Argentina", ubicacionML.getCountry().getName());
		assertEquals("Córdoba", ubicacionML.getState().getName());
	}
	
	
}
