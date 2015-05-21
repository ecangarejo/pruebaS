/**
 * bean: Rango se encuentra los objetos
 * para la creacion de los rangos que va a tener cada examen que se le asigne.
*/
package lab_bean;

public class Rango {
	
	String codigoexa= "";
    String rango = "";
    
    public Rango(){
		
	}
    
    public Rango(String codigo,String rango){
    	codigoexa = codigo;
    	rango = rango;
    	
	
	}
	
	public String getCodigo(){
		return codigoexa;
	}
	public String getrango(){
		return rango;
	}

	
	//Modificar
	public void setCodigo(String cod){
		codigoexa = cod;
	}
	public void setrango(String des){
		rango = des;
	}

}
