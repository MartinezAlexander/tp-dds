package organizaciones.reglasEntidades;

import organizaciones.EntidadJuridica;

import java.util.List;

public class ReglaAsignacionAEntidadJuridica extends ReglaCategoriaEntidad {

    private List<EntidadJuridica> entidadJuridicasProhibidas;

    public ReglaAsignacionAEntidadJuridica(List<EntidadJuridica> entidadJuridicasProhibidas) {
        super(TipoDeRegla.REGLA_ASIGNACION_A_ENTIDAD_JURIDICA);
        this.entidadJuridicasProhibidas = entidadJuridicasProhibidas;
    }

    public void ejecutarRegla(EntidadJuridica entidadJuridicaAAsignar){
        if (entidadJuridicasProhibidas.contains(entidadJuridicaAAsignar)){
            throw new AsignacionAEntidadJuridicaException();
        }
    }
}
