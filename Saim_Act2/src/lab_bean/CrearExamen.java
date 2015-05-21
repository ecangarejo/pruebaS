/**
 * bean: CrearExamen se encuentra los objetos
 * para la creacion de los examenes.
*/
package lab_bean;

public class CrearExamen {
	
	String nombre="";
	String tipo="";
	String codAreaFk="";
	String codSubaFk="";
	
public CrearExamen(){}
	public CrearExamen(String codAfk,String tip, String nomEx, String codSfk){
		
		nombre = nomEx;
		tipo=tip;
		codAreaFk=codAfk;
		codSubaFk=codSfk;
		
		
	}
	
	public String getNombre(){
		return nombre;
	}
	public String getCodAreaFk(){
		return codAreaFk;
	}
	public String getCodSubaFk(){		
		return codSubaFk;
	}
	public String getTipo(){
		return tipo;		
	}
	
	//Modificar
	public void setNombre(String nomEx){
		nombre = nomEx;
	}
	public void setCodAreaFk(String codAfk){
		codAreaFk = codAfk;
	}
	public void setCodSubaFk(String codSfk){
		codSubaFk=codSfk;		
	}
	public void setTipo(String tip){
		tipo=tip;		
	}

}
