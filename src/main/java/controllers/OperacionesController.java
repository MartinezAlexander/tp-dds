package controllers;

import direcciones.DireccionPostal;
import operaciones.DocumentoComercial;
import operaciones.ItemOperacion;
import operaciones.OperacionDeEgreso;
import operaciones.TipoDocumento;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import organizaciones.*;
import organizaciones.reglasEntidades.CategoriaEntidad;
import pago.*;
import presupuestos.CriterioDeSeleccion;
import presupuestos.ItemPresupuesto;
import presupuestos.Presupuesto;
import proveedor.Proveedor;
import repositories.*;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class OperacionesController implements WithGlobalEntityManager, TransactionalOps {

    public ModelAndView cargaOperacion(Request req, Response res){
        List<TipoDocumento> tiposDocumentos = Arrays.asList(TipoDocumento.values());
        List<Proveedor> proveedores = RepositorioProveedores.getInstance().getProveedores();
        List<Organizacion> organizaciones = RepositorioOrganizaciones.getInstance().getOrganizaciones();
        List<Entidad> entidades = RepositorioEntidades.getInstance().getEntidades();
        List<TiposMedioDePago> mediosPago = Arrays.asList(TiposMedioDePago.values());
        List<CriterioDeSeleccion> criterios = Arrays.asList(CriterioDeSeleccion.values());

        HashMap<String, Object> viewModel = new HashMap<>();
        viewModel.put("documentos", tiposDocumentos);
        viewModel.put("proveedores", proveedores);
        viewModel.put("organizaciones",organizaciones);
        viewModel.put("entidades", entidades);
        viewModel.put("mediosPago", mediosPago);
        viewModel.put("criterios", criterios);

        return new ModelAndView(viewModel, "carga_operacion.hbs");
    }

    public Void crearOperacion(Request req, Response res) {

        TiposMedioDePago tipoPago = TiposMedioDePago.valueOf(req.queryParams("medio-pago"));
        String numeroMedioPago = req.queryParams("num_medio_de_pago");
        MedioDePago medioDePago;
        switch (tipoPago){
            case EFECTIVO: medioDePago = new Efectivo(); break;
            case DINERO_EN_CUENTA: medioDePago = new DineroEnCuenta(Integer.valueOf(numeroMedioPago)); break;
            case CAJERO_AUTOMATICO: medioDePago = new CajeroAutomatico(); break;
            case TARJETA_DE_CREDITO: medioDePago = new TarjetaDeCredito(Integer.valueOf(numeroMedioPago)); break;
            default: medioDePago = new TarjetaDeDebito(Integer.valueOf(numeroMedioPago)); break;
        }

        TipoDocumento tipoDoc = TipoDocumento.valueOf(req.queryParams("doc-comercial"));
        int nroDoc = Integer.valueOf(req.queryParams("num_documento"));
        DocumentoComercial documentoComercial = new DocumentoComercial(tipoDoc, nroDoc);

        int idProveedor = Integer.valueOf(req.queryParams("proveedor"));
        Proveedor proveedor = RepositorioProveedores.getInstance().getProveedor(idProveedor);

        LocalDate fecha = LocalDate.parse(req.queryParams("fecha"));

        int idOrganizacion = Integer.valueOf(req.queryParams("organizacion"));
        Organizacion org = RepositorioOrganizaciones.getInstance().getOrganizacion(idOrganizacion);

        int idEntidad = Integer.valueOf(req.queryParams("entidad"));
        Entidad entidad = RepositorioEntidades.getInstance().getEntidad(idEntidad);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        List<ItemPresupuesto> itemsPresupuestoUno = new ArrayList<>();
        itemsPresupuestoUno.add(new ItemPresupuesto(new ItemOperacion("Descripcion"), new BigDecimal(20), "ARS"));
        itemsPresupuestoUno.add(new ItemPresupuesto(new ItemOperacion("Descripcion"), new BigDecimal(20), "ARS"));
        itemsPresupuestoUno.add(new ItemPresupuesto(new ItemOperacion("Descripcion"), new BigDecimal(20), "ARS"));

        Proveedor proveedorUno = new Proveedor(new DireccionPostal("Calle", 1520, 5, "A", "5000"), "Nombre", "Apellido", 1);
        DocumentoComercial documentoComercialUno = new DocumentoComercial(TipoDocumento.COTIZACION, 1);
        LocalDate fechaUno = LocalDate.of(2019, 6, 20);

        Presupuesto presupuestoUno = new Presupuesto(proveedorUno, itemsPresupuestoUno, documentoComercialUno, fechaUno);

        ArrayList<Presupuesto> listaPresupuestos = new ArrayList<Presupuesto>();
        listaPresupuestos.add(presupuestoUno);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        OperacionDeEgreso operacionDeEgreso = new OperacionDeEgreso(documentoComercial,
                proveedor, fecha, medioDePago, new BigDecimal(1000), org, entidad,
                new ArrayList<>(), listaPresupuestos, 231, presupuestoUno, CriterioDeSeleccion.MENOR_VALOR);

        withTransaction(() -> {
            RepositorioOperaciones.getInstance().agregarOperacion(operacionDeEgreso);
        });

        res.redirect("/carga_operacion");
        return null;
    }
}
