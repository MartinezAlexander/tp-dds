package operaciones;

public class DocumentoComercial {
	private TipoDocumento tipo;
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
