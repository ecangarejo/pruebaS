/**
 * bean: OpcionesAutorizadas se encuentran los objetos 
 * para la creacion y actualizacion de las opciones del programa
 * las cuales hacen parte de los modulos principales.
*/
package seguridad_bean;


public class OpcionesAutorizadas {
	
	String CodigoUsuario = "";
	String CodigoDisponible= "";
	String CodigoFormato = "";

	public OpcionesAutorizadas(){}
	
	public String getUsuario(){
		return CodigoUsuario;
	}
	
	public void setUsuario(String codusu){
		CodigoUsuario = codusu;
	}

	public String getDisponible(){
		return CodigoDisponible;
	}
	
	public void setDisponible(String codigo){
		CodigoDisponible = codigo;
	}
	
	public String getCodigoFormato(){
		return CodigoFormato;
	}
	
	public void setCodigoFormato(String CodigoFormato){
		this.CodigoFormato = CodigoFormato;
	}
	
}
