package testDirecciones;


import static org.junit.Assert.*;

import org.junit.Test;
import direcciones.DireccionPostal;
import direcciones.Departamento;


public class testDireccionPostal {
	
	@Test
	public void testCrearDireccion() {
		DireccionPostal direccion = new DireccionPostal("Av. Hipolito Irigoyen", 4005, 5, Departamento.A, "1824");
		assertEquals("Av. Hipolito Irigoyen",direccion.getCalle());
		assertEquals(4005,direccion.getAltura());
		assertEquals(Departamento.A,direccion.getDepartamento());
		assertEquals("Argentina",direccion.getPais());
		assertEquals("Buenos Aires",direccion.getProvincia());
		assertEquals("1824",direccion.getCodigoPostal());
		
	}
	
}
