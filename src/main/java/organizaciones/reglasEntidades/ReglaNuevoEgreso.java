package organizaciones.reglasEntidades;

import operaciones.OperacionDeEgreso;
import operaciones.RepositorioOperaciones;
import organizaciones.Entidad;

import java.math.BigDecimal;
import java.util.List;

public class ReglaNuevoEgreso extends ReglaCategoriaEntidad{
    private BigDecimal montoMaximo;

    public ReglaNuevoEgreso(BigDecimal montoMaximo) {
        super(TipoDeRegla.REGLA_NUEVO_EGRESO);
        this.montoMaximo = montoMaximo;
    }

    public void ejecutarRegla(Entidad entidad, OperacionDeEgreso nuevaOperacionEgreso){
        BigDecimal total = montoTotalDeEgresos(RepositorioOperaciones.obtenerOperacionesPorEntidad(entidad));

        if (total.add(nuevaOperacionEgreso.getValorTotal()).compareTo(montoMaximo) > 0){
            throw new EgresoAEntidadNoPermitidoException();
        }
    }

    private BigDecimal montoTotalDeEgresos(List<OperacionDeEgreso> egresos){
        return egresos.stream().map(OperacionDeEgreso::getValorTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}