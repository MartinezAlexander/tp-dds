package organizaciones;

import java.util.List;

import operaciones.OperacionDeEgreso;

public class Reporte {
	List<UnidadDeReporte> egresosPorEtiqueta;
	
	public Reporte(List<OperacionDeEgreso> egresosDeLaEntidad) {
		generarReporte(egresosDeLaEntidad);
	}
	
	private void generarReporte(List<OperacionDeEgreso> egresosDeLaEntidad) {
        for(OperacionDeEgreso egreso : egresosDeLaEntidad) {
        	for(String etiqueta : egreso.getEtiquetas()) {
        		if(reportePorEtiqueta(etiqueta) == null) {
        			egresosPorEtiqueta.add(new UnidadDeReporte(etiqueta));
        		}
        		reportePorEtiqueta(etiqueta).agregarEgreso(egreso);
        	}
        }
	}
	
	private UnidadDeReporte reportePorEtiqueta(String etiqueta) {
		return egresosPorEtiqueta.stream()
		.filter(unidadDeReporte -> etiqueta.equals(unidadDeReporte.getEtiqueta()))
		.findAny()
		.orElse(null);
	}

	public List<UnidadDeReporte> getEgresosPorEtiqueta() {
		return egresosPorEtiqueta;
	}	

}

class UnidadDeReporte{
	String NombreEtiqueta;
	List<OperacionDeEgreso> egresosDeLaEtiqueta;
	
	public UnidadDeReporte(String NombreEtiqueta) {
		this.NombreEtiqueta = NombreEtiqueta;
	}
	
	public void agregarEgreso(OperacionDeEgreso egreso){
		egresosDeLaEtiqueta.add(egreso);
	}
	
	String getEtiqueta() {
		return NombreEtiqueta;
	}
	
}
