package repositories;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import presupuestos.Presupuesto;
import proveedor.Proveedor;

import java.util.List;

public class RepositorioPresupuestos implements WithGlobalEntityManager {
    private static RepositorioPresupuestos instance = null;

    public static RepositorioPresupuestos getInstance(){
        if (instance == null) instance = new RepositorioPresupuestos();
        return instance;
    }

    public List<Presupuesto> getPresupuestos(){
        return entityManager()
                .createQuery("from Presupuesto")
                .getResultList();
    }


    public Presupuesto getPresupuesto(int id) {
        return (Presupuesto) entityManager()
                .createQuery("from Presupuesto where id = :id")
                .setParameter("id", id)
                .getSingleResult();
    }
}
