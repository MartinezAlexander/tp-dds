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

	private TiposMedioDePago tipo;
	
	public MedioDePago(){}

	public MedioDePago(TiposMedioDePago tipo){
		this.tipo = tipo;
	}

	public abstract void realizarPago();

	public int getId() {
		return id;
	}
}