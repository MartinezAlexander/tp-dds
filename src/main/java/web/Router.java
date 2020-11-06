package web;

import controllers.EntidadesController;
import controllers.HomeController;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;


public class Router {
    public static void configure(){

        HandlebarsTemplateEngine transformer = new HandlebarsTemplateEngine();

        Spark.staticFiles.location("/public");

        Spark.get("/login", HomeController::login, transformer);
        Spark.get("/home", HomeController::home, transformer);
        Spark.get("/entidades", EntidadesController::entidades, transformer);
        Spark.get("/carga_entidad_base", EntidadesController::cargaEntidades, transformer);
        Spark.post("/carga_entidad_base", EntidadesController::crearEntidadBase);
    }
}