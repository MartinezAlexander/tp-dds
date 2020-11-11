package controllers;

import autenticacion.ContrasenaInvalida;
import com.google.common.base.Optional;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
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
import java.util.stream.Collectors;

public class HomeController implements WithGlobalEntityManager, TransactionalOps {

    public static ModelAndView home(Request req, Response res){
        return new ModelAndView(null, "home.hbs");
    }

    public static ModelAndView login(Request req, Response res){
        String nombre = req.queryParams("fullname");
        String contrasenia = req.queryParams("password");

        try {
            Usuario usuario = RepositorioUsuarios.getInstance().getUsuario(nombre);
            if(usuario.getPassword().equals(contrasenia)){
                req.session().attribute("usuario-logueado", nombre);
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

    public ModelAndView signup(Request req, Response res){
        String nombre = req.queryParams("newfullname");
        String contrasenia = req.queryParams("newpassword");
        String recontrasenia = req.queryParams("repassword");

        try {
            RepositorioUsuarios.getInstance().getUsuario(nombre).getNombre();
            res.redirect("/login");
            return null;
        }
        catch(NoResultException e){
            if(!contrasenia.equals(recontrasenia)) {
                res.redirect("/login");
                return null;
            }

            try {
                Usuario usuario = new Usuario(nombre, contrasenia);

                withTransaction(() -> {
                    RepositorioUsuarios.getInstance().agregarUsuario(usuario);
                });

                req.session().attribute("usuario-logueado", nombre);
                res.redirect("/home");
                return null;
            }
            catch(ContrasenaInvalida c) {
                res.redirect("/login");
                return null;
            }

        }

    }

    public static ModelAndView show(Request req, Response res){
        return new ModelAndView(null, "login.hbs");
    }

}