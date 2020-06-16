package operaciones;

import pago.MedioDePago;
import presupuestos.CriterioDeSeleccion;
import presupuestos.Presupuesto;
import presupuestos.PresupuestoNoExistenteException;
import proveedor.Proveedor;

import java.util.Date;
import java.util.List;

import organizaciones.Organizacion;

import java.math.BigDecimal;


public class OperacionDeEgreso {
	private DocumentoComercial documentoComercial;
	private Proveedor proveedor;
	private Date fecha;
	private MedioDePago medioDePago;
	private BigDecimal valorTotal;
	private Organizacion organizacion;
	private List<ItemOperacion> items;
	private List<Presupuesto> presupuestos;
	private int presupuestosNecesarios;
	private Presupuesto presupuestoElegido;
	private CriterioDeSeleccion criterioDeSeleccionDePresupuesto;

	//TODO resolver long param?
	public OperacionDeEgreso(DocumentoComercial documentoComercial, Proveedor proveedor, Date fecha, MedioDePago medioDePago, BigDecimal valorTotal, Organizacion organizacion, List<ItemOperacion> items, List<Presupuesto> presupuestos, int presupuestosNecesarios, Presupuesto presupuestoElegido, CriterioDeSeleccion criterioDeSeleccionDePresupuesto) {
		this.documentoComercial = documentoComercial;
		this.proveedor = proveedor;
		this.fecha = fecha;
		this.medioDePago = medioDePago;
		this.valorTotal = valorTotal;
		this.organizacion = organizacion;
		this.items = items;
		this.presupuestos = presupuestos;
		this.presupuestosNecesarios = presupuestosNecesarios;
		this.presupuestoElegido = presupuestoElegido;
		this.criterioDeSeleccionDePresupuesto = criterioDeSeleccionDePresupuesto;
	}

	public void agregarPresupuesto(Presupuesto presupuesto) {
		this.presupuestos.add(presupuesto);
	}
	
	public void eliminarPresupuesto(Presupuesto presupuesto) throws PresupuestoNoExistenteException {
		if(!this.presupuestos.remove(presupuesto)) {
			throw new PresupuestoNoExistenteException("El presupuesto a eliminar no existe en la lista de presupuestos");
		}
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

	public Organizacion getOrganizacion() {
		return organizacion;
	}

	public List<ItemOperacion> getItems() {
		return items;
	}

	public List<Presupuesto> getPresupuestos() {
		return presupuestos;
	}

	public int getPresupuestosNecesarios() {
		return presupuestosNecesarios;
	}

	public Presupuesto getPresupuestoElegido() {
		return presupuestoElegido;
	}

	public CriterioDeSeleccion getCriterioDeSeleccionDePresupuesto() {
		return criterioDeSeleccionDePresupuesto;
	}
}
