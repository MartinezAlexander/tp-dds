package direcciones;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class State {
	@Column(name = "idEstado")
	private String id;
	@Column(name = "nombreEstado")
	private String name;

	public State() {
	}

	public State(String id, String name) {
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
