package pago;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("TarjetaDeDebito")
public class TarjetaDeDebito extends MedioDePago{

	@Column
	private int numeroDeTarjetaDeDebito;

	@Override
	public void realizarPago() {
		// TODO Auto-generated method stub
	}
}
