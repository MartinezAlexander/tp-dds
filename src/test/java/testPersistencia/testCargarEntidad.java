package testPersistencia;

import db.EntityManagerHelper;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import organizaciones.CategoriaEntidadJuridica;
import organizaciones.EntidadBase;
import organizaciones.EntidadJuridica;
import organizaciones.reglasEntidades.CategoriaEntidad;
import organizaciones.reglasEntidades.ReglaAsignacionAEntidadJuridica;
import organizaciones.reglasEntidades.ReglaNuevaEntidadBase;
import organizaciones.reglasEntidades.ReglaNuevoEgreso;

import javax.persistence.EntityTransaction;
import javax.transaction.Transaction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class testCargarEntidad implements WithGlobalEntityManager {


    @Test
    public void testCargarEntidades(){
        ReglaNuevaEntidadBase regla1 = new ReglaNuevaEntidadBase(false);
        ReglaNuevoEgreso regla2 = new ReglaNuevoEgreso(new BigDecimal(1000));


        CategoriaEntidad otraCategoriaParaEntidadJuridica = new CategoriaEntidad();
        otraCategoriaParaEntidadJuridica.agregarNuevaRegla(regla1);
        otraCategoriaParaEntidadJuridica.agregarNuevaRegla(regla2);

        EntidadJuridica entidadJuridica = new EntidadJuridica("Una Entidad Juridica",
                otraCategoriaParaEntidadJuridica, "Una Razon Social", 123456,
                "La direccion", CategoriaEntidadJuridica.EMPRESA_MICRO);

        List<EntidadJuridica> entidadesProhibidas = new ArrayList<>();
        entidadesProhibidas.add(entidadJuridica);
        ReglaAsignacionAEntidadJuridica regla3 = new ReglaAsignacionAEntidadJuridica(entidadesProhibidas);

        CategoriaEntidad unaCategoriaParaEntidadBase = new CategoriaEntidad();
        unaCategoriaParaEntidadBase.agregarNuevaRegla(regla3);
        unaCategoriaParaEntidadBase.agregarNuevaRegla(regla2);

        EntidadBase entidadBase = new EntidadBase("LA Entidad Base",
                unaCategoriaParaEntidadBase, "Somos LA entidad base");

        EntityTransaction transaction = entityManager().getTransaction();
        transaction.begin();

        entityManager().persist(regla1);
        entityManager().persist(regla2);
        entityManager().persist(otraCategoriaParaEntidadJuridica);

        entityManager().persist(entidadJuridica);

        entityManager().persist(regla3);
        entityManager().persist(unaCategoriaParaEntidadBase);

        entityManager().persist(entidadBase);

        transaction.commit();

    }
}
