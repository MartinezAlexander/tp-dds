package operaciones;

import persistencia.EntidadPersistente;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ItemOperacion extends EntidadPersistente{
	
	@Column
	private String descripcion;
	@Column
	private BigDecimal valor;
	@Column
	private String currencyId;
	//private IdsMonetarios currencyId;


	public ItemOperacion(String descripcion, BigDecimal valor, String currencyId) {
		this.descripcion = descripcion;
		this.valor = valor;
		this.currencyId = currencyId;
	}


	public String getDescripcion() {
		return descripcion;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public String getCurrencyId() {
		return currencyId;
	}


}
