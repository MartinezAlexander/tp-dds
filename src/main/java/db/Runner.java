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

    public static Presupuesto crearPresupuesto(int valorItemUno, int valorItemDos){
        ItemPresupuesto itemUnoPresupuesto = new ItemPresupuesto(itemUno,new BigDecimal(valorItemUno), IdsMonetarios.ARS.toString());
        ItemPresupuesto itemDosPresupuesto = new ItemPresupuesto(itemDos,new BigDecimal(valorItemDos),IdsMonetarios.ARS.toString());
        List<ItemPresupuesto> itemsPresupuesto = new ArrayList<>();
        itemsPresupuesto.add(itemUnoPresupuesto);
        itemsPresupuesto.add(itemDosPresupuesto);

        return new Presupuesto(proveedor,itemsPresupuesto,docComercial,fecha);
    }
    public static void main(String[] args) {

        docComercial = new DocumentoComercial(TipoDocumento.FACTURA,123456);
        DireccionPostal direccion = new DireccionPostal("UnaCalle",1234,4,"B","1824");
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
        Presupuesto presupuestoElegido = crearPresupuesto(3000,5000);
        valorTotal = presupuestoElegido.getCotizacion();

        opEgreso = new OperacionDeEgreso(docComercial,proveedor,fecha,medioPago,valorTotal,organizacion,entidad,items,presupuestosNecesarios,presupuestoElegido, CriterioDeSeleccion.MENOR_VALOR);


        EntityManager em = PerThreadEntityManagers.getEntityManager();

        EntityTransaction trans = em.getTransaction();
        trans.begin();

        em.persist(docComercial);
        em.persist(direccion);
        em.persist(proveedor);
        em.persist(itemUno);
        em.persist(itemDos);

        em.persist(medioPago);
        em.persist(categoria);
        //em.persist(entidad);
        em.persist(organizacion);
        em.persist(presupuestoElegido);
        //em.persist(opEgreso);

        trans.commit();

    }
}
