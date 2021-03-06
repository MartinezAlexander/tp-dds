package organizaciones.reglasEntidades;

import operaciones.OperacionDeEgreso;
import organizaciones.Entidad;
import organizaciones.EntidadJuridica;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Entity
public class CategoriaEntidad {

    @Id
    @GeneratedValue
    private int id;

    /*
    TODO:
        Una categoria puede tener muchas reglas, eso esta claro.
        Ahora, una regla, podria tener muchas categorias?? osea una instancia de regla
        con sus atributos particulares, puede estar en varias categorias?????

        Por ahora queda como que es de uno a muchos
     */
    @OneToMany
    @JoinColumn(name = "categoria_id")
    private List<ReglaCategoriaEntidad> reglas;

    @Column
    private String nombre;

    public CategoriaEntidad() {
    }

    public CategoriaEntidad(String nombre) {
        this.reglas = new ArrayList<>();
        this.nombre = nombre;
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

    public int getId(){
        return id;
    }

    public String getNombre() {
        return nombre;
    }
}
