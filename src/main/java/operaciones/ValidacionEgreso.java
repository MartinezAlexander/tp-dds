package operaciones;

import presupuestos.Presupuesto;
import usuarios.Usuario;

import java.util.ArrayList;
import java.util.List;

public class ValidacionEgreso {

    private OperacionDeEgreso operacionAValidar;
    private List<Usuario> usuariosRevisores;
    private boolean resultadoValidacion;

    public ValidacionEgreso(OperacionDeEgreso operacionAValidar) {
        this.operacionAValidar = operacionAValidar;
        usuariosRevisores = new ArrayList<>();
    }

    public ValidacionEgreso(OperacionDeEgreso operacionAValidar, List<Usuario> usuariosRevisores) {
        this.operacionAValidar = operacionAValidar;
        this.usuariosRevisores = usuariosRevisores;
    }

    public void realizarValidacion(){
        resultadoValidacion = validar();
        notifyRevisores();
    }

    //Podriamos lanzar alguna excepcion? O el usuario quiere saber si dio negativo en su mensaje?
    private boolean validar(){
        //1. Se valida la cantidad correcta de presupuestos
        //2. Se valida que la compra se hizo en base a alguno de sus presupuestos
        //3. Se valida que el presupuesto elegido se eligio correctamente segun el criterio de seleccion
        return cantidadPresupuestosCorrecta() && compraEnBaseAPresupuesto() && eleccionDePresupuestoCorrecta();
    }

    private boolean cantidadPresupuestosCorrecta(){
        return operacionAValidar.getPresupuestos().size() == operacionAValidar.getPresupuestosNecesarios();
    }

    private boolean compraEnBaseAPresupuesto(){
        return operacionAValidar.getPresupuestos().stream().anyMatch(
                presupuesto -> presupuesto.equals(operacionAValidar.getProveedor(), operacionAValidar.getItems())
        );
    }

    private boolean eleccionDePresupuestoCorrecta(){
        Presupuesto presupuestoSupuestamenteElegido = operacionAValidar.getCriterioDeSeleccionDePresupuesto().
                elegirPresupuesto(operacionAValidar.getPresupuestos());

        return presupuestoSupuestamenteElegido.equals(operacionAValidar.getPresupuestoElegido());
    }

    private void notifyRevisores(){
        usuariosRevisores.forEach( usuario -> usuario.recibirMensajeRevision(resultadoValidacion));
    }

    public void agregarRevisor(Usuario usuario){
        usuariosRevisores.add(usuario);
    }

    public void quitarRevisor(Usuario usuario){
        usuariosRevisores.remove(usuario);
    }
}
