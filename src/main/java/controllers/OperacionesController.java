package controllers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
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
        List<Presupuesto> presupuestos = RepositorioPresupuestos.getInstance().getPresupuestos();

        List<ItemOperacion> items = RepositorioItems.getInstance().getItems();

        HashMap<String, Object> viewModel = new HashMap<>();
        viewModel.put("documentos", tiposDocumentos);
        viewModel.put("proveedores", proveedores);
        viewModel.put("organizaciones",organizaciones);
        viewModel.put("entidades", entidades);
        viewModel.put("mediosPago", mediosPago);
        viewModel.put("criterios", criterios);
        viewModel.put("presupuestos", presupuestos);
        viewModel.put("items", items);

        return new ModelAndView(viewModel, "carga_operacion.hbs");
    }

    public Void crearOperacion(Request req, Response res) {

        String etiquetas_json = req.queryParams("etiquetas");
        String[] etiquetas = new Gson().fromJson(etiquetas_json, String[].class);

        String presupuestos_json = req.queryParams("presupuestos");
        int[] presupuestos_id = new Gson().fromJson(presupuestos_json, int[].class);

        String items_json = req.queryParams("items");
        int[] items_id = new Gson().fromJson(items_json, int[].class);

        TiposMedioDePago tipoPago = TiposMedioDePago.valueOf(req.queryParams("medio-pago"));
        String numeroMedioPago = req.queryParams("num-medio-de-pago");
        MedioDePago medioDePago;
        switch (tipoPago){
            case EFECTIVO: medioDePago = new Efectivo();
                break;
            case DINERO_EN_CUENTA: medioDePago = new DineroEnCuenta(Integer.valueOf(numeroMedioPago));
                break;
            case CAJERO_AUTOMATICO: medioDePago = new CajeroAutomatico();
                break;
            case TARJETA_DE_CREDITO: medioDePago = new TarjetaDeCredito(Integer.valueOf(numeroMedioPago));
                break;
            default: medioDePago = new TarjetaDeDebito(Integer.valueOf(numeroMedioPago));
        }

        TipoDocumento tipoDoc = TipoDocumento.valueOf(req.queryParams("doc-comercial"));
        int nroDoc = Integer.valueOf(req.queryParams("num-documento"));
        DocumentoComercial documentoComercial = new DocumentoComercial(tipoDoc, nroDoc);

        int idProveedor = Integer.valueOf(req.queryParams("proveedor"));
        Proveedor proveedor = RepositorioProveedores.getInstance().getProveedor(idProveedor);

        LocalDate fecha = LocalDate.parse(req.queryParams("fecha"));

        int idOrganizacion = Integer.valueOf(req.queryParams("organizacion"));
        Organizacion org = RepositorioOrganizaciones.getInstance().getOrganizacion(idOrganizacion);

        int idEntidad = Integer.valueOf(req.queryParams("entidad"));
        Entidad entidad = RepositorioEntidades.getInstance().getEntidad(idEntidad);

        List<Presupuesto> listaPresupuestos = new ArrayList<>();
        for (int id : presupuestos_id){
            Presupuesto presupuesto = RepositorioPresupuestos.getInstance().getPresupuesto(id);
            listaPresupuestos.add(presupuesto);
        }

        List<ItemOperacion> items = new ArrayList<>();
        for (int id : items_id){
            ItemOperacion itemOperacion = RepositorioItems.getInstance().getItem(id);
            items.add(itemOperacion);
        }

        //TODO: Falta elegir el presupuesto
        Presupuesto presupuestoElegido = listaPresupuestos.get(0);

        OperacionDeEgreso operacionDeEgreso = new OperacionDeEgreso(documentoComercial,
                proveedor, fecha, medioDePago, new BigDecimal(1000), org, entidad, items,
                listaPresupuestos, 231, presupuestoElegido, CriterioDeSeleccion.MENOR_VALOR);

        for (String etiqueta : etiquetas){
            operacionDeEgreso.agregarEtiqueta(etiqueta);
        }

        withTransaction(() -> {
            RepositorioOperaciones.getInstance().agregarOperacion(operacionDeEgreso);
            System.out.println("############################################################# Despues de 15 años pudimos cargar una operacion ##########################################");
        });

        res.redirect("/carga_operacion");
        return null;
    }
}
