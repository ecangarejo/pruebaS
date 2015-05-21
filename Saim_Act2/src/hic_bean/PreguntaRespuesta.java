/**
 * Bean PreguntaRespuesta, se encuentran los objetos necesarios
 * para hacer la relacion entre la pregunta y la respuesta.
 */
package hic_bean;

public class PreguntaRespuesta {

	String codigo_item_formato_fk="";
	String codigo_respuesta_fk="";
public PreguntaRespuesta(){}

public String getcodigo_item_formato_fk() {
	return codigo_item_formato_fk;
}
public void setcodigo_item_formato_fk(String codigo_item_formato_fk) {
	this.codigo_item_formato_fk = codigo_item_formato_fk;
}
//////////////////////////////////////////
public String getcodigo_respuesta_fk() {
	return codigo_respuesta_fk;
}
public void setcodigo_respuesta_fk(String codigo_respuesta_fk) {
	this.codigo_respuesta_fk = codigo_respuesta_fk;
}


}
