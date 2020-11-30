package controllers;

import repositories.RepositorioUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import usuarios.Usuario;

import java.util.HashMap;

public class UsuarioController {
    public static ModelAndView usuario(Request req, Response res){
        String nombreUsuarioLoggeado = req.session().attribute("usuario-logueado");
        Usuario usuario = RepositorioUsuarios.getInstance().getUsuario(nombreUsuarioLoggeado);

        HashMap<String, Object> model = new HashMap<>();
        model.put("usuario", usuario);
        model.put("mensajes", usuario.getBandejaMensajes());

        return new ModelAndView(model, "usuario.hbs");
    }

    public static Void cerrarSesion(Request req, Response res){
        req.session().removeAttribute("usuario-logueado");

        res.redirect("/login");
        return null;
    }
}
