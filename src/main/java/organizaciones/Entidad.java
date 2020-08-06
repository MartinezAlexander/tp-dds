package organizaciones;

import operaciones.OperacionDeEgreso;
import operaciones.RepositorioOperaciones;
import organizaciones.reglasEntidades.CategoriaEntidad;

import java.util.List;

public abstract class Entidad {
    protected String nombreFicticio;
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