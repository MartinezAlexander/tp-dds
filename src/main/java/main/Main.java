package main;
import java.util.Date;
import operaciones.ValidadorDeOperaciones;

public class Main {
	public static void main(String[] args) {
		ValidadorDeOperaciones.getInstance().programarValidacionOperacionesPendientes(new Date(), 1000);
	}
}
