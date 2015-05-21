/**
 * Bean CrearPregunta, se encuentran los objetos necesarios
 * para la creacion y actualizacion y asignacion de las preguntas que
 * conformaran un formato.
 */
package pat_bean;

public class CrearPregunta {


	String codigo="";
	String nombrePregunta="";
	String codigo_pregunta_fk="";
	String codigo_tiporespuesta_fk="";
	String PatronNormal="";
	String UnidadPregunta="";
	String TipoRequerimiento="";
	
	public CrearPregunta(){}
	
	public String getTipoRequerimiento() {
		return TipoRequerimiento;
	}
	public void setTipoRequerimiento(String TipoRequerimiento) {
		this.TipoRequerimiento = TipoRequerimiento;
	}
	
	public String getUnidadPregunta() {
		return UnidadPregunta;
	}
	public void setUnidadPregunta(String UnidadPregunta) {
		this.UnidadPregunta = UnidadPregunta;
	}
	
	public String getPatronNormal() {
		return PatronNormal;
	}
	public void setPatronNormal(String PatronNormal) {
		this.PatronNormal = PatronNormal;
	}
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombrePregunta() {
		return nombrePregunta;
	}
	public void setNombrePregunta(String nombrePregunta) {
		this.nombrePregunta = nombrePregunta;
	}
	///////////////////////////////////////////////////////
	
	public String getcodigo_pregunta_fk() {
		return codigo_pregunta_fk;
	}
	public void setcodigo_pregunta_fk(String codigo_pregunta_fk) {
		this.codigo_pregunta_fk = codigo_pregunta_fk;
	}
	
	public String getcodigo_tiporespuesta_fk() {
		return codigo_tiporespuesta_fk;
	}
	public void setcodigo_tiporespuesta_fk(String codigo_tiporespuesta_fk) {
		this.codigo_tiporespuesta_fk = codigo_tiporespuesta_fk;
	}
}
