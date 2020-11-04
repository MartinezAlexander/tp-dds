package organizaciones.reglasEntidades;

import operaciones.OperacionDeEgreso;
import repositories.RepositorioOperaciones;
import organizaciones.Entidad;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.List;

@Entity
@DiscriminatorValue("NuevoEgreso")
public class ReglaNuevoEgreso extends ReglaCategoriaEntidad{

    @Column
    private BigDecimal montoMaximo;

    public ReglaNuevoEgreso(BigDecimal montoMaximo) {
        super(TipoDeRegla.REGLA_NUEVO_EGRESO);
        this.montoMaximo = montoMaximo;
    }

    public void ejecutarRegla(Entidad entidad, OperacionDeEgreso nuevaOperacionEgreso){
        BigDecimal total = montoTotalDeEgresos(RepositorioOperaciones.getInstance().obtenerOperacionesPorEntidad(entidad));

        if (total.add(nuevaOperacionEgreso.getValorTotal()).compareTo(montoMaximo) > 0){
            throw new EgresoAEntidadNoPermitidoException();
        }
    }

    private BigDecimal montoTotalDeEgresos(List<OperacionDeEgreso> egresos){
        return egresos.stream().map(OperacionDeEgreso::getValorTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
