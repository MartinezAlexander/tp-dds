package controllers;

import com.google.common.base.Optional;
import organizaciones.Entidad;
import organizaciones.reglasEntidades.CategoriaEntidad;
import repositories.RepositorioCategoriaEntidad;
import repositories.RepositorioEntidades;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;

public class HomeController {


    public static ModelAndView home(Request req, Response res){
        return new ModelAndView(null, "home.hbs");
    }

    public static ModelAndView login(Request req, Response res){
        return new ModelAndView(null, "login.hbs");
    }

    public static ModelAndView cargaEntidadBase(Request req, Response res){
        return new ModelAndView(null, "carga_entidad_base.hbs");
    }

    public static ModelAndView cargaEntidadJuridica(Request req, Response res){
        return new ModelAndView(null, "carga_entidad_juridica.hbs");
    }

    public static ModelAndView usuario(Request req, Response res){
        return new ModelAndView(null, "usuario.hbs");
    }

    public static ModelAndView cargaOperacion(Request req, Response res){
        return new ModelAndView(null, "carga_operacion.hbs");
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

}