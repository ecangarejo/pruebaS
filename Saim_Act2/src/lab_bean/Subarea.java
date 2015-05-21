/**
 * bean: Subarea se encuentra los objetos
 * para la creacion de las subareas de los examenes, que hacen parte de un area.
*/
package lab_bean;

public class Subarea {
	String nombre="";
	String codigoArea="";
	String CodigoSub="";
	
public Subarea(){}
	public Subarea(String codA, String nom, String codigoSub){
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
