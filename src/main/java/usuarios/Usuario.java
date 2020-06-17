package usuarios;

import autenticacion.Autenticador;
import operaciones.OperacionDeEgreso;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nombre;
    private String contrasena;
    private TipoUsuario tipo;
    private List<MensajeRevision> bandejaMensajes;

    public Usuario(String nombre, String contrasena, TipoUsuario tipo) {
        this.nombre = nombre;
        this.tipo = tipo;

        Autenticador.getInstance().validarContrasena(contrasena);
        this.contrasena = contrasena;
        this.bandejaMensajes = new ArrayList<MensajeRevision>();
    }

    public void recibirMensajeRevision(MensajeRevision mensajeOperacion){
        this.bandejaMensajes.add(mensajeOperacion);
    }
}