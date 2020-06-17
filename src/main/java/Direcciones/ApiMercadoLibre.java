package Direcciones;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import javax.ws.rs.core.MediaType;

public class ApiMercadoLibre {
	private static ApiMercadoLibre _instance = null;
	private static final String RESOURCE = "https://api.mercadolibre.com/";
	private static final String PATH = "countries/AR/zip_codes/";
	
	protected ApiMercadoLibre() {
	}
	
	public static ApiMercadoLibre Instance() {
		if(_instance == null) {
			_instance = new ApiMercadoLibre();
		}
		return _instance;
	}
	
	public String obtenerJson(String codigoPostal) {
		String path = PATH + codigoPostal;
		return Client.create().resource(RESOURCE).path(path).accept(MediaType.APPLICATION_JSON).get(ClientResponse.class).getEntity(String.class);
	}
	
	//TODO Leer los campos del json obtenido
	
}
