package db;

import static org.junit.Assert.*;

import direcciones.ApiMercadoLibre;
import direcciones.UbicacionML;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

public class ContextTest extends AbstractPersistenceTest implements WithGlobalEntityManager {

	@Test
	public void contextUp() {
		assertNotNull(entityManager());
	}

	@Test
	public void contextUpWithTransaction() throws Exception {
		withTransaction(() -> {});
	}

	@Override
	public void tearDown() {
		//super.tearDown();
	}

	@Test
	public void testUbicacion(){
		UbicacionML ubicacionML = ApiMercadoLibre.getInstance().obtenerUbicacionML("1824");

		entityManager().persist(ubicacionML);
		entityManager().getTransaction().commit();

		UbicacionML ubicacionEnDb = entityManager().find(UbicacionML.class, ubicacionML.getZipCode());

		assertEquals(ubicacionML, ubicacionEnDb);
	}
}
