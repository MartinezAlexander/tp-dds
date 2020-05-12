package operaciones;

import entidades.Entidad;
import pago.MedioDePago;
import proveedor.Proveedor;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;


public class OperacionDeEgreso {
	private DocumentoComercial documentoComercial;
	private Proveedor proveedor;
	private Date fecha;
	private MedioDePago medioDePago;
	private BigDecimal valorTotal;
	private Entidad entidad;
	private List<ItemOperacion> items;

	public OperacionDeEgreso(DocumentoComercial documentoComercial, Proveedor proveedor, Date fecha, MedioDePago medioDePago,
							 BigDecimal valorTotal, Entidad entidad, List<ItemOperacion> items) {
		this.documentoComercial = documentoComercial;
		this.proveedor = proveedor;
		this.fecha = fecha;
		this.medioDePago = medioDePago;
		this.valorTotal = valorTotal;
		this.entidad = entidad;
		this.items = items;
	}

	public DocumentoComercial getDocumentoComercial() {
		return documentoComercial;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public Date getFecha() {
		return fecha;
	}

	public MedioDePago getMedioDePago() {
		return medioDePago;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public Entidad getEntidad() {
		return entidad;
	}

	public List<ItemOperacion> getItems() {
		return items;
	}
}
