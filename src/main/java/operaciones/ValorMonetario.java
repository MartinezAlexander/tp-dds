package operaciones;
import java.math.BigDecimal;

public class ValorMonetario {
	private BigDecimal valor;
	private String currencyId;
	//private IdsMonetarios currencyId;
	
	public ValorMonetario(BigDecimal valor, String currencyId) {
		this.valor = valor;
		this.currencyId = currencyId;
	}
	
	public BigDecimal getValor() {
		return valor;
	}
	public String getCurrencyId() {
		return currencyId;
	}
}
