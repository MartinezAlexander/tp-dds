package direcciones;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class City {
	@Column(name = "idCiudad")
	private String id;
	@Column(name = "nombreCiudad")
	private String name;

	public City() { }

	public City(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
}
