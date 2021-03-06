package repositories;

import operaciones.OperacionDeEgreso;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import organizaciones.Entidad;
import organizaciones.EntidadBase;
import organizaciones.EntidadJuridica;
import organizaciones.Organizacion;
import organizaciones.reglasEntidades.CategoriaEntidad;

import java.util.List;

public class RepositorioEntidades implements WithGlobalEntityManager {

    private static RepositorioEntidades instance = null;

    public static RepositorioEntidades getInstance(){
        if (instance == null) instance = new RepositorioEntidades();
        return instance;
    }

    public void agregarEntidad(Entidad entidad){
        entityManager().persist(entidad);
    }

    public void agregarEntidadBase(EntidadBase entidad){
        entityManager().persist(entidad);
    }

    public void quitarEntidad(Entidad entidad){
        entityManager()
                .createQuery("delete from Entidad where id = :id")
                .setParameter("id", entidad.getId());
    }

    public List<Entidad> getEntidades(){
        return entityManager()
                .createQuery("from Entidad")
                .getResultList();
    }

    public List<Entidad> getEntidadesSegun(CategoriaEntidad categoria){
        return entityManager()
                .createQuery("from Entidad where categoria = :idCategoria")
                .setParameter("idCategoria", categoria)
                .getResultList();
    }

    public List<EntidadJuridica> getEntidadesJuridicas(){
        return entityManager()
                .createQuery("from EntidadJuridica")
                .getResultList();
    }

    public Entidad getEntidad(int id){
        return (Entidad) entityManager()
                .createQuery("from Entidad where id = :id")
                .setParameter("id", id)
                .getSingleResult();
    }

    public EntidadJuridica getEntidadJuridica(int id) {
        return (EntidadJuridica) entityManager()
                .createQuery("from EntidadJuridica where id = :id")
                .setParameter("id", id)
                .getSingleResult();
    }

    public EntidadBase getEntidadBase(int id) {
        return (EntidadBase) entityManager()
                .createQuery("from EntidadBase where id = :id")
                .setParameter("id", id)
                .getSingleResult();
    }
//
//    public List<EntidadBase> getEntidadesBase(){
//        return entityManager()
//                .createQuery("from EntidadBase")
//                .getResultList();
//    }
}
