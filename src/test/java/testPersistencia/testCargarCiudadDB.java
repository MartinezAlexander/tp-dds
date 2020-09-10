package testPersistencia;

import db.EntityManagerHelper;
import direcciones.City;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class testCargarCiudadDB {

    @Test
    public void testCiudad(){
        EntityManagerHelper.beginTransaction();
        City buenosAires = new City("2","Buenos Aires");
        EntityManagerHelper.persist(buenosAires);
        EntityManagerHelper.commit();
        EntityManagerHelper.closeEntityManager();
        assertEquals(buenosAires,buenosAires);
    }

}