package repositories;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import organizaciones.Organizacion;
import proveedor.Proveedor;

import java.util.List;

public class RepositorioProveedores implements WithGlobalEntityManager {
    private static RepositorioProveedores instance = null;

    public static RepositorioProveedores getInstance(){
        if (instance == null) instance = new RepositorioProveedores();
        return instance;
    }

    public List<Proveedor> getProveedores(){
        return entityManager()
                .createQuery("from Proveedor")
                .getResultList();
    }


    public Proveedor getProveedor(int id) {
        return (Proveedor) entityManager()
                .createQuery("from Proveedor where id = :id")
                .setParameter("id", id)
                .getSingleResult();
    }
}
