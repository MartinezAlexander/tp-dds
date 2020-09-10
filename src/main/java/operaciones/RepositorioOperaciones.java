package operaciones;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import organizaciones.Entidad;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RepositorioOperaciones implements WithGlobalEntityManager {

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

    public static List<OperacionDeEgreso> obtenerOperacionesPorEntidad(Entidad entidad){
        return operaciones.stream().filter(operacionDeEgreso -> operacionDeEgreso.getEntidad() == entidad).collect(Collectors.toList());
    }
    
    public static List<OperacionDeEgreso> obtenerOperacionesPorEtiqueta(String etiqueta){
        return operaciones.stream().filter(operacionDeEgreso -> operacionDeEgreso.tieneEtiqueta(etiqueta)).collect(Collectors.toList());
    }
}
