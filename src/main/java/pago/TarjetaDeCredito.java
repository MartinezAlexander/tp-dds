package pago;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("TarjetaDeCredito")
public class TarjetaDeCredito extends MedioDePago{

	@Column
	private int numeroDeTarjetaCredito;

	public TarjetaDeCredito(){
		super(TiposMedioDePago.TARJETA_DE_CREDITO);
	}
	
	public TarjetaDeCredito(int numeroDeTarjetaCredito) {
		this();
		this.numeroDeTarjetaCredito = numeroDeTarjetaCredito;
	}

	@Override
	public void realizarPago() {
		// TODO Auto-generated method stub
	}
}
