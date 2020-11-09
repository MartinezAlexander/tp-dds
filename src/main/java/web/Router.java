package web;

import controllers.EntidadesController;
import controllers.HomeController;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.utils.StringUtils;


public class Router {
    public static void configure(){

        HandlebarsTemplateEngine transformer = new HandlebarsTemplateEngine();

        Spark.staticFiles.location("/public");

        EntidadesController entidadesController = new EntidadesController();
        HomeController homeController = new HomeController();

        Spark.before(((request, response) -> {
            if(!(request.pathInfo().equals("/login") || request.pathInfo().equals("/signup"))){
                if (StringUtils.isEmpty(request.session().attribute("usuario-logueado"))){
                    response.redirect("/login");
                }
            }
        }));

        Spark.get("/login", HomeController::show, transformer);
        Spark.post("/login", HomeController::login);
        Spark.post("/signup", homeController::signup);
        Spark.get("/home", HomeController::home, transformer);

        Spark.get("/carga_operacion", HomeController::cargaOperacion, transformer);
        Spark.get("/usuario", HomeController::usuario, transformer);

        Spark.get("/entidades", entidadesController::entidades, transformer);
        Spark.get("/carga_entidad_base", entidadesController::cargaEntidadBase, transformer);
        Spark.post("/carga_entidad_base", entidadesController::crearEntidadBase);
        Spark.get("/carga_entidad_juridica", entidadesController::cargaEntidadJuridica, transformer);
        Spark.post("/carga_entidad_juridica", entidadesController::crearEntidadJuridica);

    }
}