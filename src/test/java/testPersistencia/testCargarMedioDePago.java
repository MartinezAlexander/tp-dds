package testPersistencia;

import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import pago.MedioDePago;
import pago.TarjetaDeCredito;

import javax.persistence.EntityTransaction;

public class testCargarMedioDePago implements WithGlobalEntityManager {

    @Test
    public void testMedioDePago(){
        MedioDePago medioDePago = new TarjetaDeCredito(123456789);

        EntityTransaction transaction = entityManager().getTransaction();
        transaction.begin();

        entityManager().persist(medioDePago);

        transaction.commit();
    }
}
