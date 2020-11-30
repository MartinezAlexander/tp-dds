package web;

import autenticacion.PasswordHasher;
import spark.Spark;
import spark.debug.DebugScreen;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

public class Server {
    public static void main(String[] args) {
        Bootstrap boot = new Bootstrap();
//        boot.init();

        Spark.port(9000);
        DebugScreen.enableDebugScreen();
        Router.configure();
    }
}