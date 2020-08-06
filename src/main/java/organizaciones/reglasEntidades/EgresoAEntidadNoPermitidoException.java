package organizaciones.reglasEntidades;

public class EgresoAEntidadNoPermitidoException extends RuntimeException {
    public EgresoAEntidadNoPermitidoException() {
        super("No se pudo aceptar la operacion de egreso de la entidad debido a que supera el monto maximo establecido");
    }
}
