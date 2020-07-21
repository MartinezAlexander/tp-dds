package operaciones;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ValidadorDeOperaciones {

    public static void validarOperacionesPendientes(){
        RepositorioOperaciones.obtenerOperacionesPendientesDeValidacion()
                .forEach(OperacionDeEgreso::realizarValidacion);
    }
}
