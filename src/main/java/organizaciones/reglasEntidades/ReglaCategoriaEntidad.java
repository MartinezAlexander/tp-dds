package organizaciones.reglasEntidades;

import javax.persistence.*;

@Entity
@DiscriminatorColumn(name = "tipo")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class ReglaCategoriaEntidad {

    @Id
    @GeneratedValue
    private int id;

    @Transient //No guardamos el tipo de regla, esta hardcodeado, y siempre coincide con la clase
    private TipoDeRegla tipoDeRegla;
    
    public ReglaCategoriaEntidad(){}

    public ReglaCategoriaEntidad(TipoDeRegla tipoDeRegla) {
        this.tipoDeRegla = tipoDeRegla;
    }

    public TipoDeRegla getTipoDeRegla() {
        return tipoDeRegla;
    }
}
