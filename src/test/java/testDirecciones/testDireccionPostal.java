package testDirecciones;

import static org.junit.Assert.*;

import org.junit.Test;

import direcciones.ApiMercadoLibre;
import direcciones.UbicacionML;

public class testDireccionPostal {
	
	@Test
	public void testGson() {
		UbicacionML ubicacionML = ApiMercadoLibre.obtenerUbicacionML("5000");
		assertEquals("Argentina", ubicacionML.getCountry().getName());
		assertEquals("C�rdoba", ubicacionML.getState().getName());
	}
	
	
}
