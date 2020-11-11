package pago;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CajeroAutomatico")
public class CajeroAutomatico extends MedioDePago{

	public CajeroAutomatico() {
		super(TiposMedioDePago.CAJERO_AUTOMATICO);
	}
	
	@Override
	public void realizarPago() {
		// TODO Auto-generated method stub
	}

}
