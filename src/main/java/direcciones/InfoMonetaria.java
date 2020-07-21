package direcciones;

import operaciones.IdsMonetarios;

public class InfoMonetaria {
	private IdsMonetarios id;
	private String description;
	private String symbol;
	private int decimal_places;
	
	public InfoMonetaria(IdsMonetarios id, String description, String symbol, int decimal_places) {
		this.id = id;
		this.description = description;
		this.symbol = symbol;
		this.decimal_places = decimal_places;
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
