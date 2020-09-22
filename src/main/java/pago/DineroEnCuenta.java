package pago;


import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("DineroEnCuenta")
public class DineroEnCuenta extends MedioDePago {

	@Column
	private int numeroDeCuenta;

	public DineroEnCuenta() {
	}

	@Override
	public void realizarPago() {
		// TODO Auto-generated method stub
	}
}
