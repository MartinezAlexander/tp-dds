package repositories;

import operaciones.ItemOperacion;
import operaciones.OperacionDeEgreso;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

public class RepositorioItems implements WithGlobalEntityManager {
    private static RepositorioItems instance = null;

    public static RepositorioItems getInstance(){
        if (instance == null) instance = new RepositorioItems();
        return instance;
    }

    public void agregarItem(ItemOperacion item){
        entityManager().persist(item);
    }

//    public void quitarItem(ItemOperacion item){
//        entityManager()
//                .createQuery("delete from OperacionDeEgreso where id = :id")
//                .setParameter("id", operacionDeEgreso.getId());
//    }
}
