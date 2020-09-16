package organizaciones.reglasEntidades;

import javax.persistence.*;

//TODO: que la discriminator column sea el atributo TipoDeRegla
@Entity
@DiscriminatorColumn(name = "tipo")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class ReglaCategoriaEntidad {

    @Id
    @GeneratedValue
    private int id;

    @Column
    private TipoDeRegla tipoDeRegla;

    public ReglaCategoriaEntidad(TipoDeRegla tipoDeRegla) {
        this.tipoDeRegla = tipoDeRegla;
    }

    public TipoDeRegla getTipoDeRegla() {
        return tipoDeRegla;
    }
}
