package organizaciones.reglasEntidades;

import operaciones.OperacionDeEgreso;
import organizaciones.Entidad;
import organizaciones.EntidadBase;
import organizaciones.EntidadJuridica;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class CategoriaEntidad {

    private List<ReglaCategoriaEntidad> reglas;

    public CategoriaEntidad() {
        this.reglas = new ArrayList<>();
    }

    private void ejecutarReglasPorTipo(TipoDeRegla tipo, Consumer<ReglaCategoriaEntidad> consumer){
        reglas.stream().filter(regla -> regla.getTipoDeRegla() == tipo).forEach(consumer);
    }

    public void ejecutarReglasNuevoEgreso(Entidad entidad, OperacionDeEgreso egreso){
        ejecutarReglasPorTipo(TipoDeRegla.REGLA_NUEVO_EGRESO, reglaCategoriaEntidad -> {
            ((ReglaNuevoEgreso) reglaCategoriaEntidad).ejecutarRegla(entidad, egreso);
        });
    }

    public void ejecutarReglasAsignacionAEntidadJuridica(EntidadJuridica entidadJuridica){
        ejecutarReglasPorTipo(TipoDeRegla.REGLA_ASIGNACION_A_ENTIDAD_JURIDICA, reglaCategoriaEntidad -> {
            ((ReglaAsignacionAEntidadJuridica) reglaCategoriaEntidad).ejecutarRegla(entidadJuridica);
        });
    }

    public void ejecutarReglasNuevaEntidadBase(){
        ejecutarReglasPorTipo(TipoDeRegla.REGLA_NUEVA_ENTIDAD_BASE, reglaCategoriaEntidad -> {
            ((ReglaNuevaEntidadBase) reglaCategoriaEntidad).ejecutarRegla();
        });
    }

    public void agregarNuevaRegla(ReglaCategoriaEntidad regla){ reglas.add(regla); }

    public void quitarRegla(ReglaCategoriaEntidad regla){ reglas.remove(regla); }
}
