package autenticacion;

import java.util.ArrayList;
import java.util.List;

public class Autenticador {

    private static Autenticador instance = null;

    private List<RequisitoContrasena> requisitos;

    private Autenticador() {
        requisitos = new ArrayList<>();
        requisitos.add(new RequisitoTopPeores());
        requisitos.add(new RequisitoLongitud());
        requisitos.add(new RequisitoSecuencias());
        requisitos.add(new RequisitoUnicoCaracter());
    }

    public void validarContrasena(String contrasena){
        for (RequisitoContrasena req : requisitos){
            req.validar(contrasena);
        }
    }

    public static Autenticador getInstance(){
        if (instance == null) instance = new Autenticador();
        return instance;
    }
}
