package direcciones;

public class UbicacionML {

	private String zip_code;
	private City city;
	private State state;
	private Country country;
	
	public UbicacionML(String zipCode, City city, State state, Country country) {
		this.zip_code = zipCode;
		this.city = city;
		this.state = state;
		this.country = country;
	}
	
	public String getZipCode() {
		return zip_code;
	}

	public City getCity() {
		return city;
	}

	public State getState() {
		return state;
	}

	public Country getCountry() {
		return country;
	}

}
