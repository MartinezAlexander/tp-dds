package operaciones;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ValidadorDeOperaciones {

    private static ValidadorDeOperaciones instance = null;

    private List<OperacionDeEgreso> operacionesPendientesDeValidacion;

    private ValidadorDeOperaciones() {
        operacionesPendientesDeValidacion = new ArrayList<>();
    }

    public void agregarOperacionPendienteDeValidacion(OperacionDeEgreso operacion){
        operacionesPendientesDeValidacion.add(operacion);
    }

    public void validarOperacionesPendientes(){
        //Llevo a cabo la validacion de cada operacion y dejo en mi lista solo las que no pasaron
        operacionesPendientesDeValidacion = operacionesPendientesDeValidacion.stream()
                .filter(operacion -> !operacion.realizarValidacion())
                .collect(Collectors.toList());
    }

    public static ValidadorDeOperaciones getInstance(){
        if (instance == null) return new ValidadorDeOperaciones();
        else return instance;
    }
}
