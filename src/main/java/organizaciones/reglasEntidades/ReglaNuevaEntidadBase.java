package organizaciones.reglasEntidades;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("NuevaEntidadBase")
public class ReglaNuevaEntidadBase extends ReglaCategoriaEntidad {

    @Column
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
