package testPersistencia;

import db.EntityManagerHelper;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import organizaciones.reglasEntidades.CategoriaEntidad;
import organizaciones.reglasEntidades.ReglaNuevaEntidadBase;
import organizaciones.reglasEntidades.ReglaNuevoEgreso;

import javax.persistence.EntityTransaction;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class testCargarReglaCategoriaEntidad implements WithGlobalEntityManager {

    @Test
    public void testCategoriaConReglas(){

        /*
        CategoriaEntidad categoriaEntidad = new CategoriaEntidad();
        ReglaNuevaEntidadBase regla1 = new ReglaNuevaEntidadBase(false);
        ReglaNuevoEgreso regla2 = new ReglaNuevoEgreso(new BigDecimal(1000));

        categoriaEntidad.agregarNuevaRegla(regla1);
        categoriaEntidad.agregarNuevaRegla(regla2);

        EntityTransaction transaction = entityManager().getTransaction();
        transaction.begin();

        entityManager().persist(regla1);
        entityManager().persist(regla2);
        entityManager().persist(categoriaEntidad);

        transaction.commit();

         */
    }
}
