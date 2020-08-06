package organizaciones.reglasEntidades;

public class ReglaNuevaEntidadBase extends ReglaCategoriaEntidad {

    private boolean permiteEntidadesBase;

    public ReglaNuevaEntidadBase(boolean permiteEntidadesBase) {
        super(TipoDeRegla.REGLA_NUEVA_ENTIDAD_BASE);
        this.permiteEntidadesBase = permiteEntidadesBase;
    }

    public void ejecutarRegla(){
        if (!permiteEntidadesBase){
            throw new AsignacionDeEntidadesBaseException();
        }
    }
}
