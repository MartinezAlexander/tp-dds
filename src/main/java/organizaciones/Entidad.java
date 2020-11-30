package organizaciones;

import operaciones.OperacionDeEgreso;
import repositories.RepositorioOperaciones;
import organizaciones.reglasEntidades.CategoriaEntidad;
import repositories.RepositorioOrganizaciones;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Entidad {

    @Id
    @GeneratedValue//(strategy = GenerationType.TABLE)
    private int id;
    //Como estoy usando TABLE_PER_CLASS, tengo que ponerle la strategry TABLE para que
    //cada tabla tenga su propio id

    @ManyToOne
    @JoinColumn(name = "organizacion_id")
    private Organizacion organizacion;

    @Column
    protected String nombreFicticio;
    @ManyToOne
    protected CategoriaEntidad categoria;
    
    public Entidad(){}

    public Entidad(String nombreFicticio, CategoriaEntidad categoria, Organizacion organizacion) {
        this.nombreFicticio = nombreFicticio;
        this.categoria = categoria;
        this.organizacion = organizacion;
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

    public String getNombreCategoria() { return categoria.getNombre();}

    public int getId() {
        return id;
    }

    public Organizacion getOrganizacion() {
        return organizacion;
    }

    public String getNombreOrganizacion(){ return organizacion.getNombre(); }
}