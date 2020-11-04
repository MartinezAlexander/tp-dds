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
    public static ModelAndView home(Request req, Response res) {
//        Usuario user = UsuarioRepositorio.get().findAny();
//        String apodo = req.queryParams("apodo");
//
//        List<Captura> capturas =
//                Optional.fromNullable(apodo)
//                        .transform(it -> user.findByApodo(it))
//                        .or(user.getCapturas());
//
//        HashMap<String, Object> viewModel = new HashMap<>();
//        viewModel.put("apodo", apodo);
//        viewModel.put("capturas", capturas);

        List<CategoriaEntidad> categorias = RepositorioCategoriaEntidad.getInstance().getCategoriasEntidades();

        List<Entidad> entidades = RepositorioEntidades.getInstance().getEntidades();
        HashMap<String, Object> viewModel = new HashMap<>();
        viewModel.put("categorias",categorias);
        viewModel.put("entidades",entidades);

        return new ModelAndView(
                viewModel,
                "home.hbs");
    }
}