/**
 * Bean Subarea, se utiliza para el ingreso y actualizacion 
 * de una subarea la cual pertenece a una area. 
 */
package adm_bean;

public class SubArea {

	String nombre="";
	String codigoArea="";
	String CodigoSub="";
	
public SubArea(){}
	public SubArea(String codA, String nom, String codigoSub){
		codigoArea = codA;
		nombre = nom;
		CodigoSub=codigoSub;
		
	}
	
	public String getNombre(){
		return nombre;
	}
	public String getCodigoArea(){
		return codigoArea;
	}
	public String getCodigoSub(){
		
		return CodigoSub;
	}
	
	//Modificar
	public void setNombre(String nom){
		nombre = nom;
	}
	public void setCodigoArea(String codA){
		codigoArea = codA;
	}
	public void setCodigoSub(String codigoSub){
		CodigoSub=codigoSub;
		
	}
}
