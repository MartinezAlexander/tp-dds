package operaciones;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RepositorioOperaciones {

    private static List<OperacionDeEgreso> operaciones = new ArrayList<>();

    public static void agregarOperacion(OperacionDeEgreso operacionDeEgreso){
        operaciones.add(operacionDeEgreso);
    }

    public static void quitarOperacion(OperacionDeEgreso operacionDeEgreso){
        operaciones.remove(operacionDeEgreso);
    }

    public static List<OperacionDeEgreso> obtenerOperacionesPendientesDeValidacion(){
        return operaciones.stream().filter(OperacionDeEgreso::getValidada).collect(Collectors.toList());
    }
}
