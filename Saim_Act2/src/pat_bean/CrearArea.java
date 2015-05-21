/**
 * Bean CrearArea, se encuentran los objetos necesarios
 * para la creacion y actualizacion de las areas las cuales se le asignan
 * preguntas. posteriorimente las areas se le asignan a un formato.
 */
package pat_bean;

public class CrearArea {

	String codigo="";
	String nombreArea="";
	String codigo_pregunta_fk="";
	String codigo_area_fk="";
	
	public CrearArea(){
		
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombreArea() {
		return nombreArea;
	}
	public void setNombreArea(String nombreArea) {
		this.nombreArea = nombreArea;
	}
	//////////////////////////////////////////////////////////
	public String getcodigo_pregunta_fk() {
		return codigo_pregunta_fk;
	}
	public void setcodigo_pregunta_fk(String codigo_pregunta_fk) {
		this.codigo_pregunta_fk = codigo_pregunta_fk;
	}
 	public String getcodigo_area_fk() {
		return codigo_area_fk;
	}
	public void setcodigo_area_fk(String codigo_area_fk) {
		this.codigo_area_fk = codigo_area_fk;
	}
	
}
