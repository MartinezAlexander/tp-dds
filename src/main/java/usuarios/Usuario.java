package usuarios;

import autenticacion.Autenticador;
import persistencia.EntidadPersistente;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;

@Entity
public class Usuario extends EntidadPersistente{
    private String nombre;
    private String contrasena;
    
    @Enumerated(EnumType.STRING)
    private TipoUsuario tipo;
    @OneToMany
    @JoinColumn(name = "usuario_id")
    @OrderColumn(name = "posicion")
    private List<MensajeRevision> bandejaMensajes;
    
    public Usuario(){}

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
    
    public String getUserName(){
    	return nombre;
    }
    
    public String getPassword(){
    	return contrasena;
    }
}