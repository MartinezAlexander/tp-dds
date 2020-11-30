package controllers;

import autenticacion.ContrasenaInvalida;
import autenticacion.PasswordHasher;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import repositories.RepositorioUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.utils.StringUtils;
import usuarios.Usuario;

import javax.persistence.NoResultException;
import java.util.HashMap;

public class HomeController implements WithGlobalEntityManager, TransactionalOps {

    public static ModelAndView home(Request req, Response res){
        return new ModelAndView(null, "home.hbs");
    }

    public static ModelAndView login(Request req, Response res){
        String nombre = req.queryParams("fullname");
        String contrasenia = PasswordHasher.getSecurePassword(req.queryParams("password"));

        try {
            Usuario usuario = RepositorioUsuarios.getInstance().getUsuario(nombre);
            if(usuario.getPassword().equals(contrasenia)){
                req.session().attribute("usuario-logueado", nombre);
                res.redirect("/home");
            }else{
                res.redirect("/login?error=true");
            }
            return null;
        }
        catch(NoResultException e) {
            res.redirect("/login?error=true");
            return null;
        }
    }

    public ModelAndView signup(Request req, Response res){
        String nombre = req.queryParams("newfullname");
        String contrasenia = req.queryParams("newpassword");
        String recontrasenia = req.queryParams("repassword");


        try {
            RepositorioUsuarios.getInstance().getUsuario(nombre).getNombre();
            res.redirect("/login?errorS=true");
            return null;
        }
        catch(NoResultException e){
            if(!contrasenia.equals(recontrasenia)) {
                res.redirect("/login?errorS=true");
                return null;
            }

            try {
                Usuario usuario = new Usuario(nombre, PasswordHasher.getSecurePassword(contrasenia));

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
        String errorLogIn = req.queryParams("error");
        String errorSignUp = req.queryParams("errorS");

        HashMap<String, Object> model = new HashMap<>();
        model.put("error", errorLogIn);
        model.put("errorS", errorSignUp);

        return new ModelAndView(model, "login.hbs");
    }

}