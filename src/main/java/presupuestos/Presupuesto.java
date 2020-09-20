package presupuestos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import operaciones.DocumentoComercial;
import organizaciones.Entidad;
import persistencia.EntidadPersistente;
import proveedor.Proveedor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Presupuesto extends EntidadPersistente {

	@ManyToOne
	private Proveedor proveedor;
	@OneToMany
	@JoinColumn(name = "presupuesto_id")
	private List<ItemPresupuesto> items;
	@ManyToOne
	private DocumentoComercial documentoComercial;
	private LocalDate fecha;
	
	public Presupuesto(Proveedor proveedor, List<ItemPresupuesto> items, DocumentoComercial documentoComercial, LocalDate fecha) {
		this.proveedor = proveedor;
		this.items = items;
		this.documentoComercial = documentoComercial;
		this.fecha = fecha;
	}
	
	public BigDecimal getCotizacion() {
		return this.items.stream()
			      .map(ItemPresupuesto::getValor)
			      .reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public boolean equals(Proveedor proveedor, List<ItemPresupuesto> items){
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

	public List<ItemPresupuesto> getItems() {
		return items;
	}

	public DocumentoComercial getDocumentoComercial() {
		return documentoComercial;
	}

	public LocalDate getFecha() {
		return fecha;
	}
}
