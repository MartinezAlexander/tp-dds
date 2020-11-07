package controllers;

import com.google.common.base.Optional;
import organizaciones.Entidad;
import organizaciones.EntidadBase;
import organizaciones.reglasEntidades.CategoriaEntidad;
import repositories.RepositorioCategoriaEntidad;
import repositories.RepositorioEntidades;
import repositories.RepositorioUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import usuarios.Usuario;

import javax.persistence.NoResultException;
import java.util.HashMap;
import java.util.List;

import static db.EntityManagerHelper.withTransaction;

public class HomeController {

    public static ModelAndView home(Request req, Response res){
        return new ModelAndView(null, "home.hbs");
    }

    public static ModelAndView login(Request req, Response res){
        String nombre = req.queryParams("fullname");
        String contraseña = req.queryParams("password");

        try {
            Usuario usuario = RepositorioUsuarios.getInstance().getUsuario(nombre);
            if(usuario.getPassword().equals(contraseña)){
                res.redirect("/home");
            }else{
                res.redirect("/login");
            }
            return null;
        }
        catch(NoResultException e) {
            res.redirect("/login");
            return null;
        }
    }

    public static ModelAndView show(Request req, Response res){

        return new ModelAndView(null, "login.hbs");
    }

    public static ModelAndView usuario(Request req, Response res){
        return new ModelAndView(null, "usuario.hbs");
    }

    public static ModelAndView cargaOperacion(Request req, Response res){
        return new ModelAndView(null, "carga_operacion.hbs");
    }
}