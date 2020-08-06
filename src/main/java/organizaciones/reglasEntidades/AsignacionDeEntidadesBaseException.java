package organizaciones.reglasEntidades;

public class AsignacionDeEntidadesBaseException extends RuntimeException {
    public AsignacionDeEntidadesBaseException() {
        super("No se puedo asignar la entidad base debido a que su categoria no lo permite");
    }
}
