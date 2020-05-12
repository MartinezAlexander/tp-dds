package operaciones;

import java.math.BigDecimal;

public class ItemOperacion {
	private String descripcion;
	private BigDecimal valor;

	public ItemOperacion(String descripcion, BigDecimal valor) {
		this.descripcion = descripcion;
		this.valor = valor;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public BigDecimal getValor() {
		return valor;
	}
}
