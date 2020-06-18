package direcciones;
import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import javax.ws.rs.core.MediaType;

public class ApiMercadoLibre {
	private static final String RESOURCE = "https://api.mercadolibre.com/";
	private static final String PATH = "countries/AR/zip_codes/";
	private static final String PATH_MONETARIO = "currencies/";
	
	private static String obtenerJsonCiudad(String codigoPostal) {
		String path = PATH + codigoPostal;
		return Client.create().resource(RESOURCE).path(path).accept(MediaType.APPLICATION_JSON).get(ClientResponse.class).getEntity(String.class);
	}
	
	private static String obtenerJsonMonetario(String currenceId) {
		String path = PATH_MONETARIO + currenceId;
		return Client.create().resource(RESOURCE).path(path).accept(MediaType.APPLICATION_JSON).get(ClientResponse.class).getEntity(String.class);
	}
	
	public static UbicacionML obtenerUbicacionML(String codigoPostal) {
		String json = obtenerJsonCiudad(codigoPostal);
		return new Gson().fromJson(json, UbicacionML.class);
	}
	
	public static InfoMonetaria obtenerInfoMonetariaPorId(String currenceId) {
		String json = obtenerJsonMonetario(currenceId);
		return new Gson().fromJson(json,InfoMonetaria.class);
	}
	
}
