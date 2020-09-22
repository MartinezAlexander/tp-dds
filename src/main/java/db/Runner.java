package db;

import direcciones.DireccionPostal;
import operaciones.*;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import organizaciones.Entidad;
import organizaciones.EntidadBase;
import organizaciones.Organizacion;
import organizaciones.reglasEntidades.CategoriaEntidad;
import pago.MedioDePago;
import pago.TarjetaDeCredito;
import presupuestos.CriterioDeSeleccion;
import presupuestos.ItemPresupuesto;
import presupuestos.Presupuesto;
import proveedor.Proveedor;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Runner {

    private static DocumentoComercial docComercial;
    private static Proveedor proveedor;
    private static LocalDate fecha;
    private static ItemOperacion itemUno;
    private static ItemOperacion itemDos;
    private static OperacionDeEgreso opEgreso;

    public static void main(String[] args) {

        //cargaDeDatos();
        //pruebaRepositorioOperaciones();
    }

    public static void pruebaRepositorioOperaciones(){
        EntidadBase entidad = new EntidadBase();
        entidad.setId(1);
        List<OperacionDeEgreso> operaciones = RepositorioOperaciones.getInstance().obtenerOperacionesPorEntidad(entidad);
        System.out.println("Operaciones: " + operaciones.size());

        System.out.println("Operacion => " + operaciones.get(0).getProveedor().getCuitCuil());
    }

    public static void cargaDeDatos(){
        docComercial = new DocumentoComercial(TipoDocumento.FACTURA,123456);
        DireccionPostal direccion = new DireccionPostal("UnaCalle",1234,4,"B","1826");
        proveedor = new Proveedor(direccion,"mi_razon_social",22147852);
        fecha = LocalDate.of(2015, 1, 5);
        MedioDePago medioPago = new TarjetaDeCredito(456789321);
        BigDecimal valorTotal = new BigDecimal(5000);
        CategoriaEntidad categoria = new CategoriaEntidad();
        EntidadBase entidad = new EntidadBase("nombre_ficticio",categoria,"soy_una_descripcion");

        List<Entidad> entidades = new ArrayList<>();
        entidades.add(entidad);

        Organizacion organizacion = new Organizacion(entidades);

        List<ItemOperacion> items = new ArrayList<ItemOperacion>();
        itemUno = new ItemOperacion("item_uno");
        itemDos = new ItemOperacion("item_dos");
        items.add(itemUno);
        items.add(itemDos);

        int presupuestosNecesarios = 3;
        ItemPresupuesto itemUnoPresupuesto = new ItemPresupuesto(itemUno,new BigDecimal(3000), IdsMonetarios.ARS.toString());
        ItemPresupuesto itemDosPresupuesto = new ItemPresupuesto(itemDos,new BigDecimal(5000),IdsMonetarios.ARS.toString());
        List<ItemPresupuesto> itemsPresupuesto = new ArrayList<>();
        itemsPresupuesto.add(itemUnoPresupuesto);
        itemsPresupuesto.add(itemDosPresupuesto);

        Presupuesto presupuestoElegido = new Presupuesto(proveedor,itemsPresupuesto,docComercial,fecha);

        valorTotal = presupuestoElegido.getCotizacion();

        opEgreso = new OperacionDeEgreso(docComercial,proveedor,fecha,medioPago,valorTotal,organizacion,entidad,items,presupuestosNecesarios,presupuestoElegido, CriterioDeSeleccion.MENOR_VALOR);
        opEgreso.agregarEtiqueta("Etiqueta");
        opEgreso.agregarEtiqueta("Otra Etiqueta");

        EntityManager em = PerThreadEntityManagers.getEntityManager();

        EntityTransaction trans = em.getTransaction();
        trans.begin();

        em.persist(docComercial);
        em.persist(direccion);
        em.persist(proveedor);
        em.persist(itemUno);
        em.persist(itemDos);
        em.persist(itemUnoPresupuesto);
        em.persist(itemDosPresupuesto);

        em.persist(medioPago);
        em.persist(categoria);
        em.persist(entidad);
        em.persist(organizacion);
        em.persist(presupuestoElegido);
        em.persist(opEgreso);

        trans.commit();
    }
}
