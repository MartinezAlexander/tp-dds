package controllers;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import organizaciones.*;
import organizaciones.reglasEntidades.CategoriaEntidad;
import repositories.RepositorioCategoriaEntidad;
import repositories.RepositorioEntidades;
import repositories.RepositorioOrganizaciones;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import javax.persistence.NoResultException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntidadesController implements WithGlobalEntityManager, TransactionalOps {

    public ModelAndView cargaEntidadBase(Request req, Response res){
        List<CategoriaEntidad> categorias = RepositorioCategoriaEntidad.getInstance().getCategoriasEntidades();
        List<EntidadJuridica> entidadesJuridicas = RepositorioEntidades.getInstance().getEntidadesJuridicas();
        List<Organizacion> organizaciones = RepositorioOrganizaciones.getInstance().getOrganizaciones();
        
        HashMap<String, Object> viewModel = new HashMap<>();
        viewModel.put("categorias",categorias);
        viewModel.put("entidadesJuridicas",entidadesJuridicas);
        viewModel.put("organizaciones",organizaciones);

        return new ModelAndView(viewModel, "carga_entidad_base.hbs");
    }

    public ModelAndView entidades(Request req, Response res) {

        List<CategoriaEntidad> categorias = RepositorioCategoriaEntidad.getInstance().getCategoriasEntidades();
        List<Entidad> entidades;

        String categoria_id = req.queryParams("categoria");
        if (categoria_id == null) categoria_id = "0";

        int id = Integer.valueOf(categoria_id);

        if(id == 0){
            entidades = RepositorioEntidades.getInstance().getEntidades();
        }else {
            //TODO dejo esto asi de momento despues habria que buscar directamente por id.
            CategoriaEntidad cat = categorias.stream()
                    .filter(categoriaEntidad -> categoriaEntidad.getId() == id)
                    .findFirst()
                    .get();

            entidades = RepositorioEntidades.getInstance().getEntidadesSegun(cat);
        }

        HashMap<String, Object> viewModel = new HashMap<>();
        viewModel.put("categorias",categorias);
        viewModel.put("entidades",entidades);

        return new ModelAndView(
                viewModel,
                "entidades.hbs");
    }

    public Void crearEntidadBase(Request req, Response res){
        int id_categoria = Integer.valueOf(req.queryParams("categoria"));
        int id_organizacion = Integer.valueOf(req.queryParams("organizacion"));
        int id_entidadJuridica = Integer.valueOf(req.queryParams("entidad-juridica"));

        CategoriaEntidad categoria = RepositorioCategoriaEntidad.getInstance().getCategoria(id_categoria);
        Organizacion organizacion = RepositorioOrganizaciones.getInstance().getOrganizacion(id_organizacion);
        EntidadJuridica entidadJuridica = (EntidadJuridica) RepositorioEntidades.getInstance().getEntidadJuridica(id_entidadJuridica);
        String nombre = req.queryParams("nombre_ficticio");
        String descripcion = req.queryParams("descripcion");


        EntidadBase entidadBase = new EntidadBase(nombre, categoria, descripcion, organizacion, entidadJuridica);

        organizacion.agregarEntidad(entidadBase);

        withTransaction(() -> {
            RepositorioEntidades.getInstance().agregarEntidadBase(entidadBase);
        });

        res.redirect("/entidades");
        return null;
    }


    public ModelAndView cargaEntidadJuridica(Request req, Response res){
        List<CategoriaEntidad> categorias = RepositorioCategoriaEntidad.getInstance().getCategoriasEntidades();
        List<Organizacion> organizaciones = RepositorioOrganizaciones.getInstance().getOrganizaciones();
        List<CategoriaEntidadJuridica> tiposEntidadJuridica = Arrays.asList(CategoriaEntidadJuridica.values());

        HashMap<String, Object> viewModel = new HashMap<>();
        viewModel.put("categorias", categorias);
        viewModel.put("organizaciones", organizaciones);
        viewModel.put("categorias_entidad_juridica", tiposEntidadJuridica);

        return new ModelAndView(viewModel, "carga_entidad_juridica.hbs");
    }

    public Void crearEntidadJuridica(Request req, Response res){
        //TODO: Atrapar error cuando no me pasan todos los datos.
        String nombre = req.queryParams("nombre_ficticio");
        String razonSocial = req.queryParams("razon_social");
        int id_categoria = Integer.valueOf(req.queryParams("categoria"));
        int id_organizacion = Integer.valueOf(req.queryParams("organizacion"));
        CategoriaEntidadJuridica tipoJuridica = CategoriaEntidadJuridica.valueOf(req.queryParams("categoria-ent-juridica"));
        int cuit = Integer.valueOf(req.queryParams("cuit"));
        String direccionPostal = req.queryParams("direccion_postal");
        int codigoInscripcion = Integer.valueOf(req.queryParams("codigo_inscripcion"));

        CategoriaEntidad categoria = RepositorioCategoriaEntidad.getInstance().getCategoria(id_categoria);
        Organizacion organizacion = RepositorioOrganizaciones.getInstance().getOrganizacion(id_organizacion);

        EntidadJuridica entidad = new EntidadJuridica(nombre, categoria, razonSocial, cuit, direccionPostal, codigoInscripcion, tipoJuridica, organizacion);

        organizacion.agregarEntidad(entidad);

        withTransaction(() -> {
            RepositorioEntidades.getInstance().agregarEntidad(entidad);
        });

        res.redirect("/entidades");
        return null;
    }

    public ModelAndView detalleEntidades(Request request, Response response) {
        String id_param = request.params("id");
        int id = Integer.valueOf(id_param);

        Entidad entidad;
        EntidadBase base;
        EntidadJuridica juridica;

        String nombreVista;

        Map<String,Object> modelo = new HashMap<>();

        try{
            entidad = RepositorioEntidades.getInstance().getEntidad(id);
            modelo.put("entidad",entidad);
        }catch(NoResultException e){
        }

        try{
            juridica = RepositorioEntidades.getInstance().getEntidadJuridica(id);
        }catch(NoResultException e){
            juridica = null;
        }

        try{
            base = RepositorioEntidades.getInstance().getEntidadBase(id);
        }catch(NoResultException e){
            base = null;
        }


        if(base == null){
            modelo.put("entidad-detalle",juridica);
            nombreVista = "detalle_entidad_juridica.hbs";
        }else{
            modelo.put("entidad-detalle",base);
            nombreVista = "detalle_entidad_base.hbs";
        }

        return new ModelAndView(modelo,nombreVista);
    }
}
