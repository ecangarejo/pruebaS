package fact_bean;

public class Especialidad1 {
	
	//tabla especialidad
	String NombreEspecialidad="";
	String CodigoTipoEspecialidadFK="";
	String CodigoRips="";
	public String getNombreEspecialidad() {
		return NombreEspecialidad;
	}
	public void setNombreEspecialidad(String NombreEspecialidad) {
		this.NombreEspecialidad = NombreEspecialidad;
	}
	
	public String getCodigoTipoEspecialidadFK() {
		return CodigoTipoEspecialidadFK;
	}
	public void setCodigoTipoEspecialidadFK(String CodigoTipoEspecialidadFK) {
		this.CodigoTipoEspecialidadFK = CodigoTipoEspecialidadFK;
	}
	
	public String getCodigoRips() {
		return CodigoRips;
	}
	public void setCodigoRips(String CodigoRips) {
		this.CodigoRips = CodigoRips;
	}
	
	//tabla tipo especialidad
	String NombreTipoEspecialidad="";	
	public String getNombreTipoEspecialidad() {
		return NombreTipoEspecialidad;
	}
	public void setNombreTipoEspecialidad(String NombreTipoEspecialidad) {
		this.NombreTipoEspecialidad = NombreTipoEspecialidad;
	}
	
	//tabla detalle especialidad
	String Detalle="";
	String CodigoEspecialidadFK="";
	
	public String getDetalle() {
		return Detalle;
	}
	public void setDetalle(String Detalle) {
		this.Detalle = Detalle;
	}
	
	public String getCodigoEspecialidadFK() {
		return CodigoEspecialidadFK;
	}
	public void setCodigoEspecialidadFK(String CodigoEspecialidadFK) {
		this.CodigoEspecialidadFK = CodigoEspecialidadFK;
	}
	
	

}
