package organizaciones;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Organizacion {

	@Id
	@GeneratedValue
	private int id;

	@OneToMany(mappedBy = "organizacion")
	private List<Entidad> entidades;

	private String nombre;
	
	public Organizacion(){}

	public Organizacion(String nombre) {
		this.nombre = nombre;
		this.entidades = new ArrayList<>();
	}

	public Organizacion(List<Entidad> entidades, String nombre) {
		this.nombre = nombre;
		this.entidades = entidades;
	}

	public String getNombre() {
		return nombre;
	}

	public void agregarEntidad(Entidad entidad){
		this.entidades.add(entidad);
	}

	public int getId() {
		return id;
	}

	public List<Entidad> getEntidades(){ return entidades;}
}
