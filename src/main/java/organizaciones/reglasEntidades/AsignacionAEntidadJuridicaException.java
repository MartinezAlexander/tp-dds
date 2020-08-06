package organizaciones.reglasEntidades;

public class AsignacionAEntidadJuridicaException extends RuntimeException {
    public AsignacionAEntidadJuridicaException() {
        super("No se pudo asignar a la entidad juridica debido a que si categoria no lo permite");
    }
}
