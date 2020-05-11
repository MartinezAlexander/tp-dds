package GeSoc;

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
