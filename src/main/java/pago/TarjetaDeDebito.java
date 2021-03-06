package pago;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("TarjetaDeDebito")
public class TarjetaDeDebito extends MedioDePago{

	public TarjetaDeDebito(){
		super(TiposMedioDePago.TARJETA_DE_DEBITO);
	}

	public TarjetaDeDebito(int numeroDeTarjetaDeDebito) {
		this();
		this.numeroDeTarjetaDeDebito = numeroDeTarjetaDeDebito;
	}

	@Column
	private int numeroDeTarjetaDeDebito;

	@Override
	public void realizarPago() {
		// TODO Auto-generated method stub
	}
}
