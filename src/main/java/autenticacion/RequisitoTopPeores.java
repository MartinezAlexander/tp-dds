package autenticacion;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class RequisitoTopPeores implements RequisitoContrasena {
    @Override
    public String descripcion() {
        return "Una contrasena segura no deberia pertenecer a la lista de las 10000 peores contrasenas";
    }

    @Override
    public String ejemplo() {
        return "password";
    }

    @Override
    public void validar(String contrasena) {
        if(perteneceTop10000(contrasena)){
            throw new ContrasenaComunException("Contrasena no valida: se encuentra dentro de las 10000 peores contrasenas");
        }
    }

    private boolean perteneceTop10000(String password){
        List<String> listaDePeoresContrasenas = new ArrayList<>();

        String pathArchivo = "src/main/java/usuarios/peoresContrasenas.txt";

        try {
            listaDePeoresContrasenas = Files.readAllLines(Paths.get(pathArchivo));
        } catch (IOException e) {
            System.out.println("Error al leer archivo " + pathArchivo);
            e.printStackTrace();
        }

        return listaDePeoresContrasenas.contains(password);
    }
}
