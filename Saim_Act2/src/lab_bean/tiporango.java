/**
 * bean: tiporango se encuentra los objetos
 * para la creacion del tipode rango para los examenes.
*/
package lab_bean;

public class tiporango {
	String tipo="";
	String codexa="";
	
public tiporango(String codexamen,String tip){
		
		tipo=tip;
		codexa=codexamen;	
	}
public tiporango() {
	// TODO Auto-generated constructor stub
}
public String getTipo(){
	return tipo;
}
public String getCodExamen(){
	return codexa;
}

public void setTipo(String tip){
	tipo = tip;
}
public void setCodExamen(String codexamen){
	codexa = codexamen;
}
}
