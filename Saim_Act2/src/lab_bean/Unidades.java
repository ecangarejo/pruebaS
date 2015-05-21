/**
 * bean: Unidades se encuentra los objetos
 * para la creacion de las unidades que despues se le asignaran a un examen.
*/
package lab_bean;

public class Unidades {
	
	String nombre="";
	String codigoArea="";

	
public Unidades(){}
	public Unidades(String nomb){
		
		nombre = nomb;
		
		
	}
	
	public String getNombre(){
		return nombre;
	}

	//Modificar
	public void setNombre(String nomb){
		nombre = nomb;
	}


}
