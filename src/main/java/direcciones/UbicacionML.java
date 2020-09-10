package direcciones;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class UbicacionML {

	@Id
	private String zip_code;
	@ManyToOne
	private City city;
	@ManyToOne
	private State state;
	@ManyToOne
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

/*
TODO
Duda:
Guardamos la UbicacionML o guardamos el codigo y vamos a la API ????
 */