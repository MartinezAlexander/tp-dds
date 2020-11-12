package repositories;

import operaciones.ItemOperacion;
import operaciones.OperacionDeEgreso;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import presupuestos.Presupuesto;

import java.util.List;

public class RepositorioItems implements WithGlobalEntityManager {
    private static RepositorioItems instance = null;

    public static RepositorioItems getInstance(){
        if (instance == null) instance = new RepositorioItems();
        return instance;
    }

    public void agregarItem(ItemOperacion item){
        entityManager().persist(item);
    }

    public List<ItemOperacion> getItems(){
        return entityManager()
                .createQuery("from ItemOperacion")
                .getResultList();
    }

    public ItemOperacion getItem(int id) {
        return (ItemOperacion) entityManager()
                .createQuery("from ItemOperacion where id = :id")
                .setParameter("id", id)
                .getSingleResult();
    }
}
