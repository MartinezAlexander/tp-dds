package testApi;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import direcciones.ApiMercadoLibre;
import direcciones.City;
import direcciones.Country;
import direcciones.InfoMonetaria;
import direcciones.State;
import direcciones.UbicacionML;
import operaciones.IdsMonetarios;

public class testApi {

	private ApiMercadoLibre apiML; 
	private String idMonetarioArgentina;
	
	//Para mock
	private InfoMonetaria monedaDefault; 
	private UbicacionML ubicacionDefault;
	
	@Before
	public void setUp() {
		idMonetarioArgentina = IdsMonetarios.ARS.toString();
		//TODO si se quiere testear contra la API hay que descomentar lo anterior y comentar lo de mock
		//apiML = ApiMercadoLibre.getInstance(); 
		
		//TODO si se quiere testear por default sin conectarse a la API hay que usar lo de abajo
		apiML = mock(ApiMercadoLibre.class);
		monedaDefault = new InfoMonetaria(IdsMonetarios.ARS, "Peso argentino", "$", 2);
		when(apiML.obtenerInfoMonetariaPorId(idMonetarioArgentina)).thenReturn(monedaDefault);
		ubicacionDefault = new UbicacionML("1824", new City("null", "null"), new State("AR-B", "Buenos Aires"), new Country("AR","Argentina"));
		when(apiML.obtenerUbicacionML("1824")).thenReturn(ubicacionDefault);
	}
	
	@Test
	public void testMonetario() {
		InfoMonetaria moneda = apiML.obtenerInfoMonetariaPorId(idMonetarioArgentina);
		assertEquals(IdsMonetarios.ARS,moneda.getId());
		assertEquals("$",moneda.getSymbol());
		assertEquals("Peso argentino",moneda.getDescription());
		assertEquals(2,moneda.getDecimal_places());
	}
	
	@Test
	public void testUbicacionPorZipCode() {
		UbicacionML ubicacion = apiML.obtenerUbicacionML("1824");
		assertEquals("1824",ubicacion.getZipCode());
		assertEquals("AR-B",ubicacion.getState().getId());
		assertEquals("Buenos Aires",ubicacion.getState().getName());
		assertEquals("AR",ubicacion.getCountry().getId());
		assertEquals("Argentina",ubicacion.getCountry().getName());
	}

}