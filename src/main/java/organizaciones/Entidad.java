package organizaciones;

import operaciones.OperacionDeEgreso;
import operaciones.RepositorioOperaciones;
import organizaciones.reglasEntidades.CategoriaEntidad;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Entidad {

    @Id
    @GeneratedValue
    private int id;

    @Column
    protected String nombreFicticio;
    @Column
    protected CategoriaEntidad categoria;

    public Entidad(String nombreFicticio, CategoriaEntidad categoria) {
        this.nombreFicticio = nombreFicticio;
        this.categoria = categoria;
    }

    public void aceptarNuevoEgreso(OperacionDeEgreso operacionDeEgreso){

        categoria.ejecutarReglasNuevoEgreso(this, operacionDeEgreso);
        RepositorioOperaciones.agregarOperacion(operacionDeEgreso);
    }

    public Reporte generarReporteMensual(){
        return new Reporte(RepositorioOperaciones.obtenerOperacionesPorEntidad(this));        
    }

    public String getNombreFicticio() {
        return nombreFicticio;
    }
}