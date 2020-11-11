package web;

import controllers.EntidadesController;
import controllers.HomeController;
import controllers.OperacionesController;
import controllers.UsuarioController;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.utils.StringUtils;


public class Router {
    public static void configure(){

        HandlebarsTemplateEngine transformer = new HandlebarsTemplateEngine();

        Spark.staticFiles.location("/public");

        EntidadesController entidadesController = new EntidadesController();
        HomeController homeController = new HomeController();
        OperacionesController operacionesController = new OperacionesController();

        Spark.before(((request, response) -> {
            if(!(request.pathInfo().equals("/login") || request.pathInfo().equals("/signup"))){
                if (StringUtils.isEmpty(request.session().attribute("usuario-logueado"))){
                    response.redirect("/login");
                }
            }
        }));

        Spark.after((request, response) -> {
            PerThreadEntityManagers.getEntityManager();
            PerThreadEntityManagers.closeEntityManager();
        });

        Spark.get("/login", HomeController::show, transformer);
        Spark.post("/login", HomeController::login);
        Spark.post("/signup", homeController::signup);
        Spark.get("/home", HomeController::home, transformer);

        Spark.get("/usuario", UsuarioController::usuario, transformer);

        Spark.get("/entidades", entidadesController::entidades, transformer);
        Spark.get("/entidades/:id",entidadesController::detalleEntidades, transformer);
        Spark.get("/carga_entidad_base", entidadesController::cargaEntidadBase, transformer);
        Spark.post("/carga_entidad_base", entidadesController::crearEntidadBase);
        Spark.get("/carga_entidad_juridica", entidadesController::cargaEntidadJuridica, transformer);
        Spark.post("/carga_entidad_juridica", entidadesController::crearEntidadJuridica);

        Spark.get("/carga_operacion", operacionesController::cargaOperacion, transformer);
        Spark.post("/carga_operacion", operacionesController::crearOperacion);

    }
}