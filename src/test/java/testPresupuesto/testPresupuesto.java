package testPresupuesto;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import direcciones.DireccionPostal;
import presupuestos.MenorValor;
import presupuestos.Presupuesto;
import proveedor.Proveedor;
import operaciones.DocumentoComercial;
import operaciones.ItemOperacion;
import operaciones.TipoDocumento;

public class testPresupuesto {
	
	Proveedor proveedor1;
	Proveedor proveedor2;
	Presupuesto presupuesto1;
	Presupuesto presupuesto2;
	
    @Before
    public void setUp() throws Exception {
    	List<ItemOperacion> items1 = new ArrayList<ItemOperacion>();
    	items1.add(new ItemOperacion("Descripcion", new BigDecimal(20),"ARS"));
    	items1.add(new ItemOperacion("Descripcion", new BigDecimal(30),"ARS"));
    	this.proveedor1 = new Proveedor(new DireccionPostal("Av. Hipolito Irigoyen", 4005, 5, "A", "1824"), "Proovedor", "DePrueba", 33333333);
        this.presupuesto1 = new Presupuesto(this.proveedor1, items1, new DocumentoComercial(TipoDocumento.TICKET, 22), LocalDate.now());
        List<ItemOperacion> items2 = new ArrayList<ItemOperacion>();
    	items2.add(new ItemOperacion("Descripcion", new BigDecimal(40),"ARS"));
    	items2.add(new ItemOperacion("Descripcion", new BigDecimal(60),"ARS"));
    	this.proveedor2 = new Proveedor(new DireccionPostal("Av. Hipolito Irigoyen", 4005, 5, "A", "1824"),"RazonSocialTest", 204444444);
        this.presupuesto2 = new Presupuesto(this.proveedor2, items2, new DocumentoComercial(TipoDocumento.FACTURA, 33), LocalDate.now());
    }
	
	@Test
	public void testGetCotizacion() {
		assertEquals(new BigDecimal(50),presupuesto1.getCotizacion());
	}
	
	@Test
	public void testMenorValor() {
		List<Presupuesto> lista = new ArrayList<Presupuesto>();
		lista.add(presupuesto1);
		lista.add(presupuesto2);
		assertEquals(presupuesto1, new MenorValor().elegirPresupuesto(lista));
	}

}
