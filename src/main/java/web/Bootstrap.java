package web;

import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import organizaciones.CategoriaEntidadJuridica;
import organizaciones.EntidadBase;
import organizaciones.EntidadJuridica;
import organizaciones.reglasEntidades.CategoriaEntidad;
import organizaciones.reglasEntidades.ReglaAsignacionAEntidadJuridica;
import organizaciones.reglasEntidades.ReglaNuevaEntidadBase;
import organizaciones.reglasEntidades.ReglaNuevoEgreso;
import repositories.RepositorioCategoriaEntidad;
import repositories.RepositorioEntidades;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Bootstrap implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps {
    public void init(){
        ReglaNuevaEntidadBase regla1 = new ReglaNuevaEntidadBase(false);
        ReglaNuevoEgreso regla2 = new ReglaNuevoEgreso(new BigDecimal(1000));

        CategoriaEntidad otraCategoriaParaEntidadJuridica = new CategoriaEntidad("otra categoria de juridica");
        otraCategoriaParaEntidadJuridica.agregarNuevaRegla(regla1);
        otraCategoriaParaEntidadJuridica.agregarNuevaRegla(regla2);

        EntidadJuridica entidadJuridica = new EntidadJuridica("Entidad Juridica 1234",
                otraCategoriaParaEntidadJuridica, "La Razon Social", 965841,
                "Una Direccion", CategoriaEntidadJuridica.EMPRESA_MEDIA_TRAMO_2);

        List<EntidadJuridica> entidadesProhibidas = new ArrayList<>();
        entidadesProhibidas.add(entidadJuridica);
        ReglaAsignacionAEntidadJuridica regla3 = new ReglaAsignacionAEntidadJuridica(entidadesProhibidas);

        CategoriaEntidad unaCategoriaParaEntidadBase = new CategoriaEntidad("categoria de 2 reglas");
        unaCategoriaParaEntidadBase.agregarNuevaRegla(regla3);
        unaCategoriaParaEntidadBase.agregarNuevaRegla(regla2);

        EntidadBase entidadBase = new EntidadBase("E. Base",
                unaCategoriaParaEntidadBase, "una entidad base");

        withTransaction(() -> {
            RepositorioCategoriaEntidad.getInstance().agregarRegla(regla1);
            RepositorioCategoriaEntidad.getInstance().agregarRegla(regla2);
            RepositorioCategoriaEntidad.getInstance().agregarCategoriaEntidad(otraCategoriaParaEntidadJuridica);

            RepositorioEntidades.getInstance().agregarEntidad(entidadJuridica);

            RepositorioCategoriaEntidad.getInstance().agregarRegla(regla3);
            RepositorioCategoriaEntidad.getInstance().agregarCategoriaEntidad(unaCategoriaParaEntidadBase);

            RepositorioEntidades.getInstance().agregarEntidad(entidadBase);
        });
    }
}
