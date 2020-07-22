package organizaciones;

import operaciones.OperacionDeEgreso;

public interface CategoriaEntidad {
    public boolean egresoPermitido(OperacionDeEgreso egreso);
    public boolean puedoAgregarEntidadBase();
    public boolean puedoSerParteDeEntidadJuridica();
}
