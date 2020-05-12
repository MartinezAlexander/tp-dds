package usuarios;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nombre;
    private String contrasena;
    private TipoUsuario tipo;

    public Usuario(String nombre, String contrasena, TipoUsuario tipo) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.tipo = tipo;
    }

    //TODO: validacion contra recomendaciones
    public void validarContrasena(){
        if(perteneceAPeores10000(contrasena)){
            throw new ContrasenaComunException("Contrasena no valida: se encuentra dentro de las 10000 peores contrasenas");
        }
    }

    private boolean perteneceAPeores10000(String password){
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