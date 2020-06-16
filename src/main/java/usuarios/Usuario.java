package usuarios;

import autenticacion.Autenticador;

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
        this.tipo = tipo;

        Autenticador.getInstance().validarContrasena(contrasena);
        this.contrasena = contrasena;
    }

    public void recibirMensajeRevision(boolean operacionValidada){
        //TODO ver que hacer con el resultado, o modelar un objeto para manejar el resultado
    }
}