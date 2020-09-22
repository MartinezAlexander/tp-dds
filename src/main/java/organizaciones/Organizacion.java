package organizaciones;

import javax.persistence.*;
import java.util.List;

@Entity
public class Organizacion {

	@Id
	@GeneratedValue
	private int id;

	@OneToMany
	@JoinColumn(name = "organizacion_id")
	private List<Entidad> entidades;

	public Organizacion() {
	}

	public Organizacion(List<Entidad> entidades) {
		this.entidades = entidades;
	}
}
