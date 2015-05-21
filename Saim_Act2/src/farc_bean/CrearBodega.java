package farc_bean;

public class CrearBodega {
	String nombreBodega="";
	String descripcion="";
	
	String nombreEstante="";
	String observacion="";
	
	

	public String getnombreBodega() {
		return nombreBodega;
	}
	public void setnombreBodega(String nombreBodega) {
		this.nombreBodega = nombreBodega;
	}
	
	public String getdescripcion() {
		return descripcion;
	}
	public void setdescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	// bean para los estantes
	public String getnombreEstante() {
		return nombreEstante;
	}
	public void setnombreEstante(String nombreEstante) {
		this.nombreEstante = nombreEstante;
	}
	
	public String getobservacion() {
		return observacion;
	}
	public void setobservacion(String observacion) {
		this.observacion = observacion;
	}
	
}
