package organizaciones.reglasEntidades;

public abstract class ReglaCategoriaEntidad {
    private TipoDeRegla tipoDeRegla;

    public ReglaCategoriaEntidad(TipoDeRegla tipoDeRegla) {
        this.tipoDeRegla = tipoDeRegla;
    }

    public TipoDeRegla getTipoDeRegla() {
        return tipoDeRegla;
    }
}
