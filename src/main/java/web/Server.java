package web;

import organizaciones.Entidad;
import organizaciones.Organizacion;
import repositories.RepositorioCategoriaEntidad;
import repositories.RepositorioEntidades;
import repositories.RepositorioOrganizaciones;
import spark.Spark;
import spark.debug.DebugScreen;


public class Server {
    public static void main(String[] args) {
        Bootstrap boot = new Bootstrap();
//        boot.init();

        Spark.port(9000);
        DebugScreen.enableDebugScreen();
        Router.configure();
    }
}
