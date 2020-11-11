package pago;


import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("DineroEnCuenta")
public class DineroEnCuenta extends MedioDePago {

	public DineroEnCuenta(){
		super(TiposMedioDePago.DINERO_EN_CUENTA);
	}
	
	@Column
	private int numeroDeCuenta;

	public DineroEnCuenta(int numeroDeCuenta) {
		this();
		this.numeroDeCuenta = numeroDeCuenta;
	}

	@Override
	public void realizarPago() {
		// TODO Auto-generated method stub
	}
}
