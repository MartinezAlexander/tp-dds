package direcciones;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Country {
	@Column(name = "idPais")
	private String id;
	@Column(name = "nombrePais")
	private String name;

	public Country() {
	}

	public Country(String id, String name) {
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
