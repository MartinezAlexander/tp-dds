package presupuestos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import operaciones.DocumentoComercial;
import operaciones.ItemOperacion;
import proveedor.Proveedor;

public class Presupuesto {
	private Proveedor proveedor;
	private List<ItemOperacion> items;
	private DocumentoComercial documentoComercial;
	private LocalDate fecha;
	
	public Presupuesto(Proveedor proveedor, List<ItemOperacion> items, DocumentoComercial documentoComercial, LocalDate fecha) {
		this.proveedor = proveedor;
		this.items = items;
		this.documentoComercial = documentoComercial;
		this.fecha = fecha;
	}
	
	public BigDecimal getCotizacion() {
		return this.items.stream()
			      .map(i -> i.getValor())
			      .reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public boolean equals(Proveedor proveedor, List<ItemOperacion> items){
		return this.proveedor.equals(proveedor) && this.items.equals(items);
	}

	public boolean equals(Presupuesto presupuesto){
		return equals(presupuesto.proveedor, presupuesto.items)
				&& documentoComercial.equals(presupuesto.documentoComercial)
				&& fecha.equals(presupuesto.fecha);
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public List<ItemOperacion> getItems() {
		return items;
	}

	public DocumentoComercial getDocumentoComercial() {
		return documentoComercial;
	}

	public LocalDate getFecha() {
		return fecha;
	}
}
