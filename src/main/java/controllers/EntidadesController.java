package controllers;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import organizaciones.Entidad;
import organizaciones.EntidadBase;
import organizaciones.EntidadJuridica;
import organizaciones.Organizacion;
import organizaciones.reglasEntidades.CategoriaEntidad;
import repositories.RepositorioCategoriaEntidad;
import repositories.RepositorioEntidades;
import repositories.RepositorioOrganizaciones;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;

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


        EntidadBase entidadBase = new EntidadBase(nombre, categoria, descripcion, entidadJuridica);

        organizacion.agregarEntidad(entidadBase);

        withTransaction(() -> {
            RepositorioEntidades.getInstance().agregarEntidadBase(entidadBase);
        });

        res.redirect("/carga_entidad_base");
        return null;
    }


    public ModelAndView cargaEntidadJuridica(Request req, Response res){
        return new ModelAndView(null, "carga_entidad_juridica.hbs");
    }
}
