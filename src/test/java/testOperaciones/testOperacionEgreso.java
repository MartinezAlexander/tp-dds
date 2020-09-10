package testOperaciones;

import direcciones.DireccionPostal;
import operaciones.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import organizaciones.Reporte;
import presupuestos.CriterioDeSeleccion;
import presupuestos.MenorValor;
import presupuestos.Presupuesto;
import proveedor.Proveedor;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

public class testOperacionEgreso {

    private List<Presupuesto> presupuestos;

    @Before
    public void setUp(){
        presupuestos = new ArrayList<>();

        List<ItemOperacion> itemsPresupuestoUno = new ArrayList<>();
        itemsPresupuestoUno.add(new ItemOperacion("Descripcion", new ValorMonetario(new BigDecimal(20), "ARS")));
        itemsPresupuestoUno.add(new ItemOperacion("Descripcion", new ValorMonetario(new BigDecimal(20), "ARS")));
        itemsPresupuestoUno.add(new ItemOperacion("Descripcion", new ValorMonetario(new BigDecimal(20), "ARS")));

        Proveedor proveedorUno = new Proveedor(new DireccionPostal("Calle", 1520, 5, "A", "5000"), "Nombre", "Apellido", 1);
        DocumentoComercial documentoComercialUno = new DocumentoComercial(TipoDocumento.COTIZACION, 1);
        LocalDate fechaUno = LocalDate.of(2019, 6, 20);

        Presupuesto presupuestoUno = new Presupuesto(proveedorUno, itemsPresupuestoUno, documentoComercialUno, fechaUno);

        List<ItemOperacion> itemsPresupuestoDos = new ArrayList<>();
        itemsPresupuestoDos.add(new ItemOperacion("Descripcion", new ValorMonetario(new BigDecimal(60), "ARS")));
        itemsPresupuestoDos.add(new ItemOperacion("Descripcion", new ValorMonetario(new BigDecimal(20), "ARS")));
        itemsPresupuestoDos.add(new ItemOperacion("Descripcion", new ValorMonetario(new BigDecimal(140), "ARS")));

        Proveedor proveedorDos = new Proveedor(new DireccionPostal("Calle", 2075, 1, "A", "1478"), "Nombre", "Apellido", 2);
        DocumentoComercial documentoComercialDos = new DocumentoComercial(TipoDocumento.COTIZACION, 2);
        LocalDate fechaDos = LocalDate.of(2019, 6, 21);

        Presupuesto presupuestoDos = new Presupuesto(proveedorDos, itemsPresupuestoDos, documentoComercialDos, fechaDos);

        List<ItemOperacion> itemsPresupuestoTres = new ArrayList<>();
        itemsPresupuestoTres.add(new ItemOperacion("Descripcion", new ValorMonetario(new BigDecimal(30), "ARS")));
        itemsPresupuestoTres.add(new ItemOperacion("Descripcion", new ValorMonetario(new BigDecimal(30), "ARS")));
        itemsPresupuestoTres.add(new ItemOperacion("Descripcion", new ValorMonetario(new BigDecimal(10), "ARS")));

        Proveedor proveedorTres = new Proveedor(new DireccionPostal("Calle", 1032, 15, "C", "2116"), "Nombre", "Apellido", 3);
        DocumentoComercial documentoComercialTres = new DocumentoComercial(TipoDocumento.COTIZACION, 3);
        LocalDate fechaTres = LocalDate.of(2015, 1, 5);

        Presupuesto presupuestoTres = new Presupuesto(proveedorTres, itemsPresupuestoTres, documentoComercialTres, fechaTres);

        presupuestos.add(presupuestoUno);
        presupuestos.add(presupuestoDos);
        presupuestos.add(presupuestoTres);
    }

    @Test
    public void testValidacionPositivo(){

        int cantidadPresupuestos = 3;
        CriterioDeSeleccion criterio = new MenorValor();
        Presupuesto presupuestoElegido = criterio.elegirPresupuesto(presupuestos);

        List<ItemOperacion> itemsComprados = presupuestoElegido.getItems();
        BigDecimal valorTotal = presupuestoElegido.getCotizacion();
        Proveedor proveedor = presupuestoElegido.getProveedor();

        OperacionDeEgreso operacion = new OperacionDeEgreso(null, proveedor,
                null, null, valorTotal, null, null, itemsComprados, presupuestos,
                cantidadPresupuestos, presupuestoElegido, criterio);

        operacion.realizarValidacion();
        Assert.assertTrue(operacion.getValidada());
    }

    @Test
    public void testValidacionNegativoPorCantidadIncorrecta(){

        int cantidadPresupuestos = 5;
        CriterioDeSeleccion criterio = new MenorValor();
        Presupuesto presupuestoElegido = criterio.elegirPresupuesto(presupuestos);

        List<ItemOperacion> itemsComprados = presupuestoElegido.getItems();
        BigDecimal valorTotal = presupuestoElegido.getCotizacion();
        Proveedor proveedor = presupuestoElegido.getProveedor();

        OperacionDeEgreso operacion = new OperacionDeEgreso(null, proveedor,
                null, null, valorTotal, null, null, itemsComprados, presupuestos,
                cantidadPresupuestos, presupuestoElegido, criterio);

        operacion.realizarValidacion();
        Assert.assertFalse(operacion.getValidada());
    }

    @Test
    public void testValidacionNegativoPorCompraNoPresupuesto(){

        int cantidadPresupuestos = 3;
        CriterioDeSeleccion criterio = new MenorValor();
        Presupuesto presupuestoElegido = criterio.elegirPresupuesto(presupuestos);

        List<ItemOperacion> itemsComprados = new ArrayList<>();
        itemsComprados.add(new ItemOperacion("Descripcion", new ValorMonetario(new BigDecimal(1500), "ARS")));
        itemsComprados.add(new ItemOperacion("Descripcion", new ValorMonetario(new BigDecimal(50), "ARS")));

        BigDecimal valorTotal = presupuestoElegido.getCotizacion();
        Proveedor proveedor = presupuestoElegido.getProveedor();

        OperacionDeEgreso operacion = new OperacionDeEgreso(null, proveedor,
                null, null, valorTotal, null, null, itemsComprados, presupuestos,
                cantidadPresupuestos, presupuestoElegido, criterio);

        operacion.realizarValidacion();
        Assert.assertFalse(operacion.getValidada());
    }

    @Test
    public void testValidacionNegativoPorEleccionIncorrecta(){

        int cantidadPresupuestos = 3;
        CriterioDeSeleccion criterio = new MenorValor();
        Presupuesto presupuestoElegido = criterio.elegirPresupuesto(presupuestos);

        List<ItemOperacion> itemsComprados = presupuestoElegido.getItems();
        BigDecimal valorTotal = presupuestoElegido.getCotizacion();
        Proveedor proveedor = presupuestoElegido.getProveedor();

        OperacionDeEgreso operacion = new OperacionDeEgreso(null, proveedor,
                null, null, valorTotal, null, null, itemsComprados, presupuestos,
                cantidadPresupuestos, presupuestos.get(1), criterio);

        operacion.realizarValidacion();
        Assert.assertFalse(operacion.getValidada());
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
}
