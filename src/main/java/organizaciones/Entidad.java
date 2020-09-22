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
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
    //Como estoy usando TABLE_PER_CLASS, tengo que ponerle la strategry TABLE para que
    //cada tabla tenga su propio id

    @Column
    protected String nombreFicticio;
    @ManyToOne
    protected CategoriaEntidad categoria;

    public Entidad(String nombreFicticio, CategoriaEntidad categoria) {
        this.nombreFicticio = nombreFicticio;
        this.categoria = categoria;
    }

    public void aceptarNuevoEgreso(OperacionDeEgreso operacionDeEgreso){

        categoria.ejecutarReglasNuevoEgreso(this, operacionDeEgreso);
        RepositorioOperaciones.getInstance().agregarOperacion(operacionDeEgreso);
    }

    public Reporte generarReporteMensual(){
        return new Reporte(RepositorioOperaciones.getInstance().obtenerOperacionesPorEntidad(this));        
    }

    public String getNombreFicticio() {
        return nombreFicticio;
    }


    public int getId() {
        return id;
    }

}