/**
 * Bean CrearFormato, se encuentran los objetos necesarios
 * para la creacion y actualizacion de las preguntas y tipo de respuestas.
 */
package pat_bean;

public class CrearFormato {
	String codigo="";
	String nombre="";
	String nombrePregunta="";
	String nombreRespuesta="";
	String TipoPregunta="";
	public CrearFormato(){
		
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	//////////////////////////////////
	public String getNombrePregunta() {
		return nombrePregunta;
	}
	public void setNombrePregunta(String nombrePregunta) {
		this.nombrePregunta = nombrePregunta;
	}
	public void setTipoPregunta(String TipoPregunta) {
		this.TipoPregunta = TipoPregunta;
	}
	public String getTipoPregunta() {
		return TipoPregunta;
	}
	//////////////////////////////////
	public String getNombreRespuesta() {
		return nombreRespuesta;
	}
	public void setNombreRespuesta(String nombreRespuesta) {
		this.nombreRespuesta = nombreRespuesta;
	}
}
