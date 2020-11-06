package controllers;

import com.google.common.base.Optional;
import organizaciones.Entidad;
import organizaciones.EntidadBase;
import organizaciones.reglasEntidades.CategoriaEntidad;
import repositories.RepositorioCategoriaEntidad;
import repositories.RepositorioEntidades;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;

import static db.EntityManagerHelper.withTransaction;

public class HomeController {


    public static ModelAndView home(Request req, Response res){
        return new ModelAndView(null, "home.hbs");
    }

    public static ModelAndView login(Request req, Response res){
        return new ModelAndView(null, "login.hbs");
    }

}