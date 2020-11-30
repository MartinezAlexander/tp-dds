package operaciones;

import repositories.RepositorioOperaciones;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class ValidadorDeOperaciones {
	
	private static ValidadorDeOperaciones instance = null;
	private Timer timer;
	private TimerTask timerTask;
	
	private ValidadorDeOperaciones() {
		timer = new Timer();
		timerTask = new TimerTask() {
			@Override
			public void run() {
				System.out.println("-------------Realizando Validaciones de Operaciones Pendientes...");
				RepositorioOperaciones.getInstance().obtenerOperacionesPendientesDeValidacion()
						.forEach(OperacionDeEgreso::realizarValidacion);

				System.out.println("-------------Finalizaron las Validaciones");
			}
		};
	}
	
    public static ValidadorDeOperaciones getInstance(){
        if (instance == null) instance = new ValidadorDeOperaciones();
        return instance;
    }

    public void programarValidacionOperacionesPendientes(Date fechaInicio, int cadaCuantoTiempo){
    	timer.schedule(timerTask, fechaInicio, cadaCuantoTiempo);
    }
}