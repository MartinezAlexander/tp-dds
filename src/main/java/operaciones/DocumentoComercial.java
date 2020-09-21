package operaciones;

import persistencia.EntidadPersistente;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class DocumentoComercial extends EntidadPersistente{
	
	@Enumerated(EnumType.STRING)
	private TipoDocumento tipo;
	
	@Column
	private int numero;

	public DocumentoComercial(TipoDocumento tipo, int numero) {
		this.tipo = tipo;
		this.numero = numero;
	}

	public TipoDocumento getTipo() {
		return tipo;
	}

	public int getNumero() {
		return numero;
	}
}
