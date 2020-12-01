package web;

import autenticacion.PasswordHasher;
import operaciones.ValidadorDeOperaciones;
import spark.Spark;
import spark.debug.DebugScreen;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Date;

public class Server {
    public static void main(String[] args) {
        Bootstrap boot = new Bootstrap();
//        boot.init();

        Spark.port(getHerokuAssignedPort());
        DebugScreen.enableDebugScreen();
        Router.configure();

        int segundosValidacion = 60;
        ValidadorDeOperaciones.getInstance().programarValidacionOperacionesPendientes(new Date(), segundosValidacion*1000);
    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 9000; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}