package repositories;

import operaciones.OperacionDeEgreso;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import pago.MedioDePago;

public class RepositorioMedioDePago implements WithGlobalEntityManager {

    private static RepositorioMedioDePago instance = null;

    public static RepositorioMedioDePago getInstance(){
        if (instance == null) instance = new RepositorioMedioDePago();
        return instance;
    }

//    public void agregarMedioDePago(MedioDePago medioDePago){
//        entityManager().persist(medioDePago);
//    }
//
//    public void quitarMedioDePago(MedioDePago medioDePago){
//        entityManager()
//                .createQuery("delete from MedioDePago where id = :id")
//                .setParameter("id", medioDePago.getId());
//    }
//


}
