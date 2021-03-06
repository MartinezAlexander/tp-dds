package operaciones;

import organizaciones.Entidad;
import pago.MedioDePago;
import persistencia.EntidadPersistente;
import presupuestos.CriterioDeSeleccion;
import presupuestos.Presupuesto;
import presupuestos.PresupuestoNoExistenteException;
import proveedor.Proveedor;
import usuarios.MensajeRevision;
import usuarios.Usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import organizaciones.Organizacion;

import java.math.BigDecimal;

@Entity
public class OperacionDeEgreso extends EntidadPersistente {
	
	@OneToOne(cascade = CascadeType.PERSIST)
	private DocumentoComercial documentoComercial;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Proveedor proveedor;
	
	@Column
	private LocalDate fecha;
	
	@OneToOne(cascade = CascadeType.PERSIST) //TODO: puede ser many to one
	private MedioDePago medioDePago;
	
	@Column
	private BigDecimal valorTotal;
	
	@Transient //TODO: Hay que sacar organizacion
	private Organizacion organizacion;
	
	@ManyToOne
	private Entidad entidad;
	
	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name="op_egreso_id")
	private List<ItemOperacion> items;
	
	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name="op_egreso_id")
	private List<Presupuesto> presupuestos;
	
	@Column
	private int presupuestosNecesarios;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	private Presupuesto presupuestoElegido;
	
	@Enumerated(EnumType.STRING)
	private CriterioDeSeleccion criterioDeSeleccionDePresupuesto;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name="UsuarioRevisor")
	private List<Usuario> usuariosRevisores;
	
	@Column
	private boolean validada;
	
	@ElementCollection
	private List<String> etiquetas;
	
	public OperacionDeEgreso(){}

	//TODO resolver long param?
	//TODO inicializar OperacionDeEgreso con usuariosRevisores?
	public OperacionDeEgreso(DocumentoComercial documentoComercial, Proveedor proveedor, LocalDate fecha, MedioDePago medioDePago, BigDecimal valorTotal, Organizacion organizacion, Entidad entidad, List<ItemOperacion> items, List<Presupuesto> presupuestos, int presupuestosNecesarios, Presupuesto presupuestoElegido, CriterioDeSeleccion criterioDeSeleccionDePresupuesto) {
		this.documentoComercial = documentoComercial;
		this.proveedor = proveedor;
		this.fecha = fecha;
		this.medioDePago = medioDePago;
		this.valorTotal = valorTotal;
		this.organizacion = organizacion;
		this.entidad = entidad;
		this.items = items;
		this.presupuestos = presupuestos;
		this.presupuestosNecesarios = presupuestosNecesarios;
		this.presupuestoElegido = presupuestoElegido;
		this.criterioDeSeleccionDePresupuesto = criterioDeSeleccionDePresupuesto;

		usuariosRevisores = new ArrayList<>();
		
		etiquetas = new ArrayList<>();

		this.validada = false;
	}
	
	public OperacionDeEgreso(DocumentoComercial documentoComercial,Proveedor proveedor, LocalDate fecha, 
			MedioDePago medioDePago, BigDecimal valorTotal, Organizacion organizacion, 
			Entidad entidad, List<ItemOperacion> items,int presupuestosNecesarios, 
			Presupuesto presupuestoElegido, CriterioDeSeleccion criterioDeSeleccionDePresupuesto){
		
		this.documentoComercial = documentoComercial;
		this.proveedor = proveedor;
		this.fecha = fecha;
		this.medioDePago = medioDePago;
		this.valorTotal = valorTotal;
		this.organizacion = organizacion;
		this.entidad = entidad;
		this.items = items;
		this.presupuestosNecesarios = presupuestosNecesarios;
		this.presupuestoElegido = presupuestoElegido; // TODO: podriamos crear el presupuesto aca en base a los demas datos
		this.criterioDeSeleccionDePresupuesto = criterioDeSeleccionDePresupuesto;

		usuariosRevisores = new ArrayList<>();
		this.presupuestos = new ArrayList<Presupuesto>();
		presupuestos.add(presupuestoElegido);
		
		etiquetas = new ArrayList<>();

		this.validada = false;
	}

	public void agregarPresupuesto(Presupuesto presupuesto) {
		this.presupuestos.add(presupuesto);
	}
	
	public void eliminarPresupuesto(Presupuesto presupuesto) throws PresupuestoNoExistenteException {
		if(!this.presupuestos.remove(presupuesto)) {
			throw new PresupuestoNoExistenteException("El presupuesto a eliminar no existe en la lista de presupuestos");
		}
	}

	public List<ItemOperacion> getItems() {
		return items;
	}

	//TODO: los items no me los trae por algun motivo?
	public int getCantidadDeItems(){ return presupuestoElegido.getItems().size(); }

	public DocumentoComercial getDocumentoComercial() {
		return documentoComercial;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public LocalDate getFecha() {
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

	public Entidad getEntidad() {
		return entidad;
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

	public boolean getValidada(){ return validada; }

	//Validaci??n
	public void realizarValidacion(){
		validada = validar();
        notifyRevisores(validada);
    }

    private boolean validar(){
        //1. Se valida la cantidad correcta de presupuestos
        //2. Se valida que la compra se hizo en base a alguno de sus presupuestos
        //3. Se valida que el presupuesto elegido se eligio correctamente segun el criterio de seleccion
        return cantidadPresupuestosCorrecta() && compraEnBaseAPresupuesto() && eleccionDePresupuestoCorrecta();
    }

    private boolean cantidadPresupuestosCorrecta(){
        return getPresupuestos().size() == getPresupuestosNecesarios();
    }

    private boolean compraEnBaseAPresupuesto(){
        return getPresupuestos().stream().anyMatch(
                presupuesto -> presupuesto.equals(getProveedor(), presupuesto.getItemsConValor())
        );
    }

    private boolean eleccionDePresupuestoCorrecta(){
        Presupuesto presupuestoSupuestamenteElegido = getCriterioDeSeleccionDePresupuesto().
                elegirPresupuesto(getPresupuestos());

        return presupuestoSupuestamenteElegido == getPresupuestoElegido();
    }

    private void notifyRevisores(boolean resultadoValidacion){
        usuariosRevisores.forEach( usuario -> usuario.recibirMensajeRevision(new MensajeRevision(this, resultadoValidacion)));
    }

    public void agregarRevisor(Usuario usuario){
        usuariosRevisores.add(usuario);
    }

    public void quitarRevisor(Usuario usuario){
        usuariosRevisores.remove(usuario);
    }
	
    public void agregarEtiqueta(String etiqueta){
        etiquetas.add(etiqueta);
    }

    public void quitarEtiqueta(String etiqueta){
    	etiquetas.remove(etiqueta);
    }
    
    public boolean tieneEtiqueta(String etiqueta){
    	return etiquetas.contains(etiqueta);
    }
    
	public List<String> getEtiquetas() {
		return etiquetas;
	}
    
}
