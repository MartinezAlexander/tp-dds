package web;

import repositories.RepositorioCategoriaEntidad;
import spark.Spark;
import spark.debug.DebugScreen;


public class Server {
    public static void main(String[] args) {
        Bootstrap boot = new Bootstrap();
        //boot.init();

        Spark.port(9000);
        DebugScreen.enableDebugScreen();
        Router.configure();

    }
}