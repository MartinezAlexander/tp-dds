package testOperaciones;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import direcciones.DireccionPostal;
import operaciones.DocumentoComercial;
import operaciones.IdsMonetarios;
import operaciones.ItemOperacion;
import operaciones.OperacionDeEgreso;
import operaciones.TipoDocumento;
import organizaciones.Entidad;
import organizaciones.EntidadBase;
import organizaciones.Organizacion;
import organizaciones.Reporte;
import organizaciones.reglasEntidades.CategoriaEntidad;
import pago.MedioDePago;
import pago.TarjetaDeCredito;
import presupuestos.CriterioDeSeleccion;
import presupuestos.ItemPresupuesto;
import presupuestos.Presupuesto;
import proveedor.Proveedor;

public class testOperacionEgreso2
{
	private DocumentoComercial docComercial;
	private Proveedor proveedor;
	private LocalDate fecha;
	private ItemOperacion itemUno;
	private ItemOperacion itemDos;
	OperacionDeEgreso opEgreso;
	//RepositorioOperaciones repo;
	
	public Presupuesto crearPresupuesto(int valorItemUno, int valorItemDos){
		ItemPresupuesto itemUnoPresupuesto = new ItemPresupuesto(itemUno,new BigDecimal(valorItemUno),IdsMonetarios.ARS.toString());
		ItemPresupuesto itemDosPresupuesto = new ItemPresupuesto(itemDos,new BigDecimal(valorItemDos),IdsMonetarios.ARS.toString());
		List<ItemPresupuesto> itemsPresupuesto = new ArrayList<ItemPresupuesto>();
		itemsPresupuesto.add(itemUnoPresupuesto);
		itemsPresupuesto.add(itemDosPresupuesto);
		
		return new Presupuesto(proveedor,itemsPresupuesto,docComercial,fecha);
	}
	
	@Before
	public void setUp(){
		docComercial = new DocumentoComercial(TipoDocumento.FACTURA,123456);
		DireccionPostal  direccion = new DireccionPostal("UnaCalle",1234,4,"B","1824");
		proveedor = new Proveedor(direccion,"mi_razon_social",22147852);
		fecha = LocalDate.of(2015, 1, 5);
		MedioDePago medioPago = new TarjetaDeCredito(456789321);
		BigDecimal valorTotal = new BigDecimal(5000);
		CategoriaEntidad categoria = new CategoriaEntidad("cateee");
		Entidad entidad = new EntidadBase("nombre_ficticio",categoria,"soy_una_descripcion");
		
		List<Entidad> entidades = new ArrayList<>();
		entidades.add(entidad);

		Organizacion organizacion = new Organizacion(entidades, "JAVA");
		
		List<ItemOperacion> items = new ArrayList<>();
		itemUno = new ItemOperacion("item_uno");
		itemDos = new ItemOperacion("item_dos");
		items.add(itemUno);
		items.add(itemDos);
		
		int presupuestosNecesarios = 3;
		Presupuesto presupuestoElegido = this.crearPresupuesto(3000,5000);
		valorTotal = presupuestoElegido.getCotizacion();
		
		opEgreso = new OperacionDeEgreso(docComercial,proveedor,fecha,medioPago,valorTotal,organizacion,entidad,items,presupuestosNecesarios,presupuestoElegido,CriterioDeSeleccion.MENOR_VALOR);
		
		//repo = new RepositorioOperaciones();
	}
	
	@Test
	public void testValidacionPositivo(){
		opEgreso.agregarPresupuesto(this.crearPresupuesto(7000,6000));
		opEgreso.agregarPresupuesto(this.crearPresupuesto(8000,10000));
		opEgreso.realizarValidacion();
        Assert.assertTrue(opEgreso.getValidada());
	}
	
	@Test
	public void testValidacionNegativoPorCantidadIncorrecta(){
		opEgreso.agregarPresupuesto(this.crearPresupuesto(7000,6000));
		opEgreso.realizarValidacion();
		Assert.assertFalse(opEgreso.getValidada());
	}
	
	@Test
	public void testValidacionNegativoPorEleccionIncorrecta(){
		opEgreso.agregarPresupuesto(this.crearPresupuesto(1000,500));
		opEgreso.agregarPresupuesto(this.crearPresupuesto(8000,10000));
		opEgreso.realizarValidacion();
		Assert.assertFalse(opEgreso.getValidada());
	}	
	
    @Test
    public void reportePorEtiquetas(){
        OperacionDeEgreso egresoA = new OperacionDeEgreso(null, null, null, null,null, null, null, null, null, 0, null, null);

        egresoA.agregarEtiqueta("Indumentaria");
        egresoA.agregarEtiqueta("Adidas");

        OperacionDeEgreso egresoB = new OperacionDeEgreso(null, null, null, null,null, null, null, null, null, 0, null, null);

        egresoB.agregarEtiqueta("Indumentaria");
        egresoB.agregarEtiqueta("Nike");

        OperacionDeEgreso egresoC = new OperacionDeEgreso(null, null, null, null,null, null, null, null, null, 0, null, null);

        egresoC.agregarEtiqueta("Amoblamiento");
        egresoC.agregarEtiqueta("Oficina");

        List<OperacionDeEgreso> misEgresos = new ArrayList<>();
        misEgresos.add(egresoA);
        misEgresos.add(egresoB);
        misEgresos.add(egresoC);

        Reporte reporte = new Reporte(misEgresos);
        Assert.assertEquals(5, reporte.getEgresosPorEtiqueta().size());
        Assert.assertEquals(2, reporte.getEgresosPorEtiqueta().get("Indumentaria").size());
    }
    
    /*@Test
    public void testRepositorioOperaciones() {
    	repo.agregarOperacion(opEgreso);
    	
    }*/
}