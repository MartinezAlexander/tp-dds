package repositories;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import usuarios.Usuario;

public class RepositorioUsuarios implements WithGlobalEntityManager {
    private static RepositorioUsuarios instance = null;

    public static RepositorioUsuarios getInstance(){
        if (instance == null) instance = new RepositorioUsuarios();
        return instance;
    }

    public void agregarUsuario(Usuario usuario){
        entityManager().persist(usuario);
    }

    public Usuario getUsuario(String nombre) {
        return (Usuario) entityManager()
                .createQuery("from Usuario where nombre = :nombre")
                .setParameter("nombre", nombre)
                .getSingleResult();
    }
}
