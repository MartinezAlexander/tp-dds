package pago;

import javax.persistence.*;

/*
public interface MedioDePago {
	void realizarPago();
}
*/
@Entity
@DiscriminatorColumn(name = "medio")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class MedioDePago{

	@Id
	@GeneratedValue
	private int id;
	
	public MedioDePago(){}

	public abstract void realizarPago();
}