package direcciones;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import operaciones.IdsMonetarios;

@Entity
public class InfoMonetaria {
	@Id
	private IdsMonetarios id;
	@Column
	private String description;
	@Column
	private String symbol;
	@Column
	private int decimal_places;
	
	public InfoMonetaria(IdsMonetarios id, String description, String symbol, int decimal_places) {
		this.id = id;
		this.description = description;
		this.symbol = symbol;
		this.decimal_places = decimal_places;
	}

	public InfoMonetaria() {
	}

	public IdsMonetarios getId() {
		return id;
	}
	public String getDescription() {
		return description;
	}
	public String getSymbol() {
		return symbol;
	}
	public int getDecimal_places() {
		return decimal_places;
	}
	
	
}
