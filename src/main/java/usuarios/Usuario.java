package usuarios;

import autenticacion.Autenticador;
import operaciones.OperacionDeEgreso;
import persistencia.EntidadPersistente;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Usuario extends EntidadPersistente{
    private String nombre;
    private String contrasena;
    
    @Enumerated(EnumType.STRING)
    private TipoUsuario tipo;
    @OneToMany
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