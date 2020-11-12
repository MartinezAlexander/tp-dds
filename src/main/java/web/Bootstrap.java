package web;

import direcciones.DireccionPostal;
import operaciones.DocumentoComercial;
import operaciones.ItemOperacion;
import operaciones.OperacionDeEgreso;
import operaciones.TipoDocumento;
import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import organizaciones.*;
import organizaciones.reglasEntidades.CategoriaEntidad;
import organizaciones.reglasEntidades.ReglaAsignacionAEntidadJuridica;
import organizaciones.reglasEntidades.ReglaNuevaEntidadBase;
import organizaciones.reglasEntidades.ReglaNuevoEgreso;
import pago.Efectivo;
import pago.MedioDePago;
import pago.TarjetaDeCredito;
import presupuestos.CriterioDeSeleccion;
import presupuestos.ItemPresupuesto;
import presupuestos.Presupuesto;
import proveedor.Proveedor;
import repositories.*;
import usuarios.MensajeRevision;
import usuarios.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bootstrap implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps {
    public void init(){
        //REGLAS CATEGORIAS
        ReglaNuevaEntidadBase regla1 = new ReglaNuevaEntidadBase(false);
        ReglaNuevoEgreso regla2 = new ReglaNuevoEgreso(new BigDecimal(1000));

        //CATEGORIAS DE ENTIDADES
        CategoriaEntidad otraCategoriaParaEntidadJuridica = new CategoriaEntidad("otra categoria de juridica");
        otraCategoriaParaEntidadJuridica.agregarNuevaRegla(regla1);
        otraCategoriaParaEntidadJuridica.agregarNuevaRegla(regla2);

        //ENTIDAD
        EntidadJuridica entidadJuridica = new EntidadJuridica("Entidad Juridica 1234",
                otraCategoriaParaEntidadJuridica, "La Razon Social", 965841,
                "Una Direccion", CategoriaEntidadJuridica.EMPRESA_MEDIA_TRAMO_2);

        //REGLA DE CATEGORIA
        List<EntidadJuridica> entidadesProhibidas = new ArrayList<>();
        entidadesProhibidas.add(entidadJuridica);
        ReglaAsignacionAEntidadJuridica regla3 = new ReglaAsignacionAEntidadJuridica(entidadesProhibidas);

        //CATEGORIA DE ENTIDAD
        CategoriaEntidad unaCategoriaParaEntidadBase = new CategoriaEntidad("categoria de 2 reglas");
        unaCategoriaParaEntidadBase.agregarNuevaRegla(regla3);
        unaCategoriaParaEntidadBase.agregarNuevaRegla(regla2);

        //ENTIDAD
        EntidadBase entidadBase = new EntidadBase("E. Base",
                unaCategoriaParaEntidadBase, "una entidad base");
        EntidadBase otraEntidadBase = new EntidadBase("Otra Entidad Base",
                unaCategoriaParaEntidadBase, "una entidad base mas");
        EntidadBase ultimaEntidadBase = new EntidadBase("La Ultima Entidad Base",
                unaCategoriaParaEntidadBase, "basta de entidades base");

        //USUARIOS
        Usuario usuario1 = new Usuario("Julian Simaro", "TPscala2020");
        Usuario usuario2 = new Usuario("Agustin Cragno", "TPruby2020");

        //ORGANIZACIONES
        List<Entidad> entidades = new ArrayList<>();
        entidades.add(entidadBase);
        Organizacion org = new Organizacion(entidades, "RUBY ONG");
        List<Entidad> entidades2 = new ArrayList<>();
        entidades2.add(entidadJuridica);
        Organizacion org2 = new Organizacion(entidades2, "SCALA ONG");
        List<Entidad> entidades3 = new ArrayList<>();
        entidades3.add(otraEntidadBase);
        entidades3.add(ultimaEntidadBase);
        Organizacion org3 = new Organizacion(entidades3, "JAVA ONG");


        //OPERACIONES
        OperacionDeEgreso operacion1 = cargarOperacion(CriterioDeSeleccion.MENOR_VALOR, org, entidadBase);
        OperacionDeEgreso operacion2 = cargarOperacion(CriterioDeSeleccion.MENOR_VALOR, org2, entidadJuridica);
        OperacionDeEgreso operacion3 = cargarOperacion(CriterioDeSeleccion.MENOR_VALOR, org2, entidadBase);
        OperacionDeEgreso operacion4 = cargarOperacion(CriterioDeSeleccion.MENOR_VALOR, org2, entidadBase);
        OperacionDeEgreso operacion5 = cargarOperacion(CriterioDeSeleccion.MENOR_VALOR, org3, otraEntidadBase);
        OperacionDeEgreso operacion6 = cargarOperacion(CriterioDeSeleccion.MENOR_VALOR, org3, otraEntidadBase);
        OperacionDeEgreso operacion7 = cargarOperacion(CriterioDeSeleccion.MENOR_VALOR, org3, otraEntidadBase);
        OperacionDeEgreso operacion8 = cargarOperacion(CriterioDeSeleccion.MENOR_VALOR, org3, otraEntidadBase);

        operacion1.agregarRevisor(usuario1);
        operacion2.agregarRevisor(usuario2);
        operacion3.agregarRevisor(usuario1);
        operacion4.agregarRevisor(usuario1);
        operacion5.agregarRevisor(usuario1);
        operacion6.agregarRevisor(usuario1);
        operacion7.agregarRevisor(usuario1);
        operacion8.agregarRevisor(usuario1);

        //MENSAJES
        operacion1.realizarValidacion();
        operacion2.realizarValidacion();
        operacion3.realizarValidacion();
        operacion4.realizarValidacion();
        operacion5.realizarValidacion();
        operacion6.realizarValidacion();
        operacion7.realizarValidacion();
        operacion8.realizarValidacion();

        withTransaction(() -> {
            RepositorioCategoriaEntidad.getInstance().agregarRegla(regla1);
            RepositorioCategoriaEntidad.getInstance().agregarRegla(regla2);
            RepositorioCategoriaEntidad.getInstance().agregarCategoriaEntidad(otraCategoriaParaEntidadJuridica);

            RepositorioEntidades.getInstance().agregarEntidad(entidadJuridica);

            RepositorioCategoriaEntidad.getInstance().agregarRegla(regla3);
            RepositorioCategoriaEntidad.getInstance().agregarCategoriaEntidad(unaCategoriaParaEntidadBase);

            RepositorioEntidades.getInstance().agregarEntidad(entidadBase);
            RepositorioEntidades.getInstance().agregarEntidad(otraEntidadBase);
            RepositorioEntidades.getInstance().agregarEntidad(ultimaEntidadBase);

            RepositorioUsuarios.getInstance().agregarUsuario(usuario1);
            RepositorioUsuarios.getInstance().agregarUsuario(usuario2);

            RepositorioOperaciones.getInstance().agregarOperacion(operacion1);
            RepositorioOperaciones.getInstance().agregarOperacion(operacion2);
            RepositorioOperaciones.getInstance().agregarOperacion(operacion3);
            RepositorioOperaciones.getInstance().agregarOperacion(operacion4);
            RepositorioOperaciones.getInstance().agregarOperacion(operacion5);
            RepositorioOperaciones.getInstance().agregarOperacion(operacion6);
            RepositorioOperaciones.getInstance().agregarOperacion(operacion7);
            RepositorioOperaciones.getInstance().agregarOperacion(operacion8);

            usuario1.getBandejaMensajes().forEach(mensajeRevision -> entityManager().persist(mensajeRevision));
            usuario2.getBandejaMensajes().forEach(mensajeRevision -> entityManager().persist(mensajeRevision));

            RepositorioOrganizaciones.getInstance().agregarOrganizacion(org);
            RepositorioOrganizaciones.getInstance().agregarOrganizacion(org2);
            RepositorioOrganizaciones.getInstance().agregarOrganizacion(org3);

        });
    }

    private List<Presupuesto> cargarPresupuestos(){
        List<Presupuesto> presupuestos = new ArrayList<>();

        List<ItemPresupuesto> itemsPresupuestoUno = new ArrayList<>();
        itemsPresupuestoUno.add(new ItemPresupuesto(new ItemOperacion("Pantalon"), new BigDecimal(20), "ARS"));
        itemsPresupuestoUno.add(new ItemPresupuesto(new ItemOperacion("Remera"), new BigDecimal(20), "ARS"));
        itemsPresupuestoUno.add(new ItemPresupuesto(new ItemOperacion("Saco"), new BigDecimal(20), "ARS"));

        Proveedor proveedorUno = new Proveedor(new DireccionPostal("Av. Brasil", 1520, 5, "A", "5000"), "Juan", "Perez", 22451886);
        DocumentoComercial documentoComercialUno = new DocumentoComercial(TipoDocumento.COTIZACION, 14654564);
        LocalDate fechaUno = LocalDate.of(2019, 6, 20);

        Presupuesto presupuestoUno = new Presupuesto(proveedorUno, itemsPresupuestoUno, documentoComercialUno, fechaUno);

        List<ItemPresupuesto> itemsPresupuestoDos = new ArrayList<>();
        itemsPresupuestoDos.add(new ItemPresupuesto(new ItemOperacion("Pantalon"), new BigDecimal(60), "ARS"));
        itemsPresupuestoDos.add(new ItemPresupuesto(new ItemOperacion("Remera"), new BigDecimal(20), "ARS"));
        itemsPresupuestoDos.add(new ItemPresupuesto(new ItemOperacion("Saco"), new BigDecimal(140), "ARS"));

        Proveedor proveedorDos = new Proveedor(new DireccionPostal("Av 9 de Julio", 2075, 1, "A", "1478"), "Esteban", "Quito", 2475239);
        DocumentoComercial documentoComercialDos = new DocumentoComercial(TipoDocumento.COTIZACION, 26456451);
        LocalDate fechaDos = LocalDate.of(2019, 6, 21);

        Presupuesto presupuestoDos = new Presupuesto(proveedorDos, itemsPresupuestoDos, documentoComercialDos, fechaDos);

        List<ItemPresupuesto> itemsPresupuestoTres = new ArrayList<>();
        itemsPresupuestoTres.add(new ItemPresupuesto(new ItemOperacion("Pantalon"), new BigDecimal(30), "ARS"));
        itemsPresupuestoTres.add(new ItemPresupuesto(new ItemOperacion("Remera"), new BigDecimal(30), "ARS"));
        itemsPresupuestoTres.add(new ItemPresupuesto(new ItemOperacion("Saco"), new BigDecimal(10), "ARS"));

        Proveedor proveedorTres = new Proveedor(new DireccionPostal("Av Libertador", 1032, 15, "C", "2116"), "Elber", "Galarga", 3415728);
        DocumentoComercial documentoComercialTres = new DocumentoComercial(TipoDocumento.COTIZACION, 36456432);
        LocalDate fechaTres = LocalDate.of(2015, 1, 5);

        Presupuesto presupuestoTres = new Presupuesto(proveedorTres, itemsPresupuestoTres, documentoComercialTres, fechaTres);

        presupuestos.add(presupuestoUno);
        presupuestos.add(presupuestoDos);
        presupuestos.add(presupuestoTres);

        return presupuestos;
    }

    private OperacionDeEgreso cargarOperacion(CriterioDeSeleccion criterioDeSeleccion, Organizacion organizacion, Entidad entidad){
        List<Presupuesto> presupuestos = cargarPresupuestos();
        //TODO: Persistir todo esto
        //TODO: buscar si hay forma de que hibernate te lo haga todo de una al cargar la operacion, que seria mas facil.
        int cantidadPresupuestos = 3;
        Presupuesto presupuestoElegido = criterioDeSeleccion.elegirPresupuesto(presupuestos);

        List<ItemOperacion> itemsComprados = presupuestoElegido.getItems();
        BigDecimal valorTotal = presupuestoElegido.getCotizacion();
        Proveedor proveedor = presupuestoElegido.getProveedor();

        DocumentoComercial documentoComercial = new DocumentoComercial(TipoDocumento.COTIZACION, 125);
        LocalDate fecha = LocalDate.of(1980 + new Random().nextInt(40), new Random().nextInt(11) + 1, new Random().nextInt(29) + 1);
        MedioDePago medioDePago = new TarjetaDeCredito(new Random().nextInt(200000000));

        return new OperacionDeEgreso(documentoComercial, proveedor,
                fecha, medioDePago, valorTotal, organizacion, entidad, itemsComprados, presupuestos,
                cantidadPresupuestos, presupuestoElegido, criterioDeSeleccion);
    }
}
