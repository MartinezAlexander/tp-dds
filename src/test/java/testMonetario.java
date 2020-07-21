import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.Test;

import direcciones.ApiMercadoLibre;
import direcciones.InfoMonetaria;
import operaciones.IdsMonetarios;

public class testMonetario {

	@Test
	public void test() {
		
		InfoMonetaria moneda = ApiMercadoLibre.obtenerInfoMonetariaPorId(IdsMonetarios.ARS.toString());
		assertEquals("ARS",moneda.getId());
		assertEquals("$",moneda.getSymbol());
		assertEquals("Peso argentino",moneda.getDescription());
		assertEquals(2,moneda.getDecimal_places());
	}

}
