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
}
