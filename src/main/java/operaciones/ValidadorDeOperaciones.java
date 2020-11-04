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
				RepositorioOperaciones.getInstance().obtenerOperacionesPendientesDeValidacion()
						.forEach(OperacionDeEgreso::realizarValidacion);
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