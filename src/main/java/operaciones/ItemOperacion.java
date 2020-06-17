package operaciones;

public class ItemOperacion {
	private String descripcion;
	private ValorMonetario valor;

	public ItemOperacion(String descripcion, ValorMonetario valor) {
		this.descripcion = descripcion;
		this.valor = valor;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public ValorMonetario getValorMonetario() {
		return valor;
	}
}
