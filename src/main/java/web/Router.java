package web;

import controllers.EntidadesController;
import controllers.HomeController;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;


public class Router {
    public static void configure(){

        HandlebarsTemplateEngine transformer = new HandlebarsTemplateEngine();

        Spark.staticFiles.location("/public");

        EntidadesController entidadesController = new EntidadesController();

        Spark.get("/login", HomeController::login, transformer);
        Spark.get("/home", HomeController::home, transformer);

        Spark.get("/carga_operacion", HomeController::cargaOperacion, transformer);
        Spark.get("/carga_entidad_juridica", HomeController::cargaEntidadJuridica, transformer);
        Spark.get("/usuario", HomeController::usuario, transformer);

        Spark.get("/entidades", entidadesController::entidades, transformer);
        Spark.get("/carga_entidad_base", entidadesController::cargaEntidades, transformer);
        Spark.post("/carga_entidad_base", entidadesController::crearEntidadBase);

    }
}