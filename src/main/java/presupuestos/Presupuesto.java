package presupuestos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import operaciones.DocumentoComercial;
import operaciones.ItemOperacion;
import proveedor.Proveedor;

public class Presupuesto {
	private Proveedor proveedor;
	private ArrayList<ItemOperacion> items;
	private BigDecimal cotizacion;
	private DocumentoComercial documentoComercial;
	private Date fecha;
	
	public Presupuesto(Proveedor proveedor, ArrayList<ItemOperacion> items, DocumentoComercial documentoComercial, Date fecha) {
		this.proveedor = proveedor;
		this.items = items;
		this.documentoComercial = documentoComercial;
		this.fecha = fecha;
	}
	
	public BigDecimal getCotizacion() {
		return this.items.stream()
			      .map(i -> i.getValor())
			      .sum();
	}
	
	
}
