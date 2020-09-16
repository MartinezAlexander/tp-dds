package testPersistencia;

import db.EntityManagerHelper;
import direcciones.City;
import org.hibernate.Transaction;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import static org.junit.Assert.assertEquals;

public class testCargarCiudadDB implements WithGlobalEntityManager {

    @Test
    public void testCiudad(){


        City buenosAires = new City("4","Buenos Aires");
        /*
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.persist(buenosAires);
        EntityManagerHelper.commit();
        EntityManagerHelper.closeEntityManager();
        */

        /*
        EntityTransaction transaction = entityManager().getTransaction();
        transaction.begin();
        entityManager().persist(buenosAires);
        transaction.commit();

        assertEquals(buenosAires,buenosAires);
        */

    }

}