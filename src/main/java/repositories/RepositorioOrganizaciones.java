package repositories;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import organizaciones.Entidad;
import organizaciones.Organizacion;
import organizaciones.reglasEntidades.CategoriaEntidad;

import java.util.List;


public class RepositorioOrganizaciones implements WithGlobalEntityManager {
    private static RepositorioOrganizaciones instance = null;

    public static RepositorioOrganizaciones getInstance(){
        if (instance == null) instance = new RepositorioOrganizaciones();
        return instance;
    }

    public void agregarOrganizacion(Organizacion organizacion){
        entityManager().persist(organizacion);
    }

    public List<Organizacion> getOrganizaciones(){
        return entityManager()
                .createQuery("from Organizacion")
                .getResultList();
    }

    public Organizacion getOrganizacion(int id) {
        return (Organizacion) entityManager()
                .createQuery("from Organizacion where id = :id")
                .setParameter("id", id)
                .getSingleResult();
    }

}
