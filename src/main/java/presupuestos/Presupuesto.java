package presupuestos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import operaciones.DocumentoComercial;
import persistencia.EntidadPersistente;
import proveedor.Proveedor;

import javax.persistence.*;

import operaciones.ItemOperacion;


@Entity
public class Presupuesto extends EntidadPersistente {

	@ManyToOne(cascade = CascadeType.PERSIST)
	private Proveedor proveedor;
	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "presupuesto_id")
	private List<ItemPresupuesto> items;
	@ManyToOne(cascade = CascadeType.PERSIST)
	private DocumentoComercial documentoComercial;
	@Column
	private LocalDate fecha;
	
	public Presupuesto(){}
	
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

	public List<ItemPresupuesto> getItemsConValor() {
		return items;
	}
	
	public List<ItemOperacion> getItems(){
		return new ArrayList<ItemOperacion>(); //TODO: hay que lograr obtener de la base solo los items
	}

	public DocumentoComercial getDocumentoComercial() {
		return documentoComercial;
	}

	public LocalDate getFecha() {
		return fecha;
	}
}
