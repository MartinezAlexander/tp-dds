package repositories;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import organizaciones.Entidad;
import organizaciones.reglasEntidades.CategoriaEntidad;
import organizaciones.reglasEntidades.ReglaCategoriaEntidad;

import java.util.List;

public class RepositorioCategoriaEntidad implements WithGlobalEntityManager {

    private static RepositorioCategoriaEntidad instance = null;

    public static RepositorioCategoriaEntidad getInstance() {
        if (instance == null) instance = new RepositorioCategoriaEntidad();
        return instance;
    }

    public void agregarRegla(ReglaCategoriaEntidad regla) {
        entityManager().persist(regla);
    }

    public void agregarCategoriaEntidad(CategoriaEntidad categoria) {
        entityManager().persist(categoria);
    }

    public void quitarEntidad(CategoriaEntidad categoria) {
        entityManager()
                .createQuery("delete from CategoriaEntidad where id = :id")
                .setParameter("id", categoria.getId());
    }

    public List<CategoriaEntidad> getCategoriasEntidades() {
        return entityManager()
                .createQuery("from CategoriaEntidad")
                .getResultList();
    }
}
