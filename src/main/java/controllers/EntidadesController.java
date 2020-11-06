package controllers;

import organizaciones.Entidad;
import organizaciones.EntidadBase;
import organizaciones.reglasEntidades.CategoriaEntidad;
import repositories.RepositorioCategoriaEntidad;
import repositories.RepositorioEntidades;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;

import static db.EntityManagerHelper.withTransaction;

public class EntidadesController {

    public static ModelAndView cargaEntidades(Request req, Response res){
        List<CategoriaEntidad> categorias = RepositorioCategoriaEntidad.getInstance().getCategoriasEntidades();

        HashMap<String, Object> viewModel = new HashMap<>();
        viewModel.put("categorias",categorias);

        return new ModelAndView(viewModel, "carga_entidad_base.hbs");
    }

    public static ModelAndView entidades(Request req, Response res) {

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

    public static Void crearEntidadBase(Request req, Response res){
        int id_categoria = Integer.valueOf(req.queryParams("categoria"));
        CategoriaEntidad categoria = RepositorioCategoriaEntidad.getInstance().getCategoria(id_categoria);
        System.out.println(categoria.getNombre());
        EntidadBase entidadBase = new EntidadBase(req.queryParams("nombre_ficticio"),categoria, req.queryParams("descripcion"));
        System.out.println(entidadBase.getNombreFicticio());
        withTransaction(() -> RepositorioEntidades.getInstance().agregarEntidadBase(entidadBase));
        res.redirect("/carga_entidad_base");
        return null;
    }

}
