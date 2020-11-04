package repositories;

import operaciones.OperacionDeEgreso;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import organizaciones.Entidad;

import java.util.List;

public class RepositorioOperaciones implements WithGlobalEntityManager {

	private static RepositorioOperaciones instance = null;
	
    public static RepositorioOperaciones getInstance(){
        if (instance == null) instance = new RepositorioOperaciones();
        return instance;
    }
	
	public void agregarOperacion(OperacionDeEgreso operacionDeEgreso){
    	entityManager().persist(operacionDeEgreso);
    }

    public void quitarOperacion(OperacionDeEgreso operacionDeEgreso){
        entityManager()
		.createQuery("delete from OperacionDeEgreso where id = :id")
		.setParameter("id", operacionDeEgreso.getId());
    }

    @SuppressWarnings("unchecked")
    public List<OperacionDeEgreso> obtenerOperacionesPendientesDeValidacion(){
        return entityManager()
				.createQuery("from OperacionDeEgreso where validada = :validada")
				.setParameter("validada", false)
				.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<OperacionDeEgreso> obtenerOperacionesPorEntidad(Entidad entidad){
        return entityManager()
				.createQuery("from OperacionDeEgreso where entidad = :entidad")
				.setParameter("entidad", entidad)
				.getResultList();
    }
    
    @SuppressWarnings("unchecked")
    public List<OperacionDeEgreso> obtenerOperacionesPorEtiqueta(String etiqueta){
		return entityManager()
				.createQuery("select OperacionDeEgreso from OperacionDeEgreso_etiquetas where etiquetas = :etiqueta")
				.setParameter("etiqueta", etiqueta)
				.getResultList();
    }
}
