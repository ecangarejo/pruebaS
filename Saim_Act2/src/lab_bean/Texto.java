/**
 * bean: Texto se encuentra los objetos
 * para la creacion de los examenes que son de tipo texto(campo abierto).
*/
package lab_bean;

public class Texto {

	String codigoexa= "";
    String descripcion = "";
    
    public Texto(){
		
	}
    
    public Texto(String codigo,String descri){
    	codigoexa = codigo;
    	descripcion = descri;
    	
	
	}
	
	public String getCodigo(){
		return codigoexa;
	}
	public String getDescripcion(){
		return descripcion;
	}

	
	//Modificar
	public void setCodigo(String cod){
		codigoexa = cod;
	}
	public void setDescripcion(String des){
		descripcion = des;
	}
}
