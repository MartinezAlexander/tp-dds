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

        Spark.port(9000);
        DebugScreen.enableDebugScreen();
        Router.configure();

        int segundosValidacion = 60;
        ValidadorDeOperaciones.getInstance().programarValidacionOperacionesPendientes(new Date(), segundosValidacion*1000);
    }
}