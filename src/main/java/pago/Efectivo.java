package pago;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Efectivo")
public class Efectivo extends MedioDePago{

	public Efectivo(){
		super(TiposMedioDePago.EFECTIVO);
	}
	
	@Override
	public void realizarPago() {
		// TODO Auto-generated method stub
	}

}
