/***
 * Bean Acompanante, se utiliza para obtener los objetos para la insercion, de los datos del acompañante.
 */
package adm_bean;

public class Acompanante {
	String cedula = "";
	String nombre = "";
	String apellido = "";
	String parentesco = "";
	String telefono = "";
	String direccion = "";
	
	public Acompanante(){
		
	}
	public Acompanante(String ced, String nom, String ape, String par, String tel, String dir){
		cedula = ced;
		nombre = nom;
		apellido = ape;
		parentesco = par;
		telefono = tel;
		direccion = dir;
	}
	
	public String getCedula(){
		return cedula;
	}
	
	public String getNombre(){
		return nombre;
	}
	public String getApellido(){
		return apellido;
	}
	public String getParentesco(){
		return parentesco;
	}
	public String getTelefono(){
		return telefono;
	}
	public String getDireccion(){
		return direccion;
	}
	
	//Modificar
	public void setCedula(String ced){
		cedula = ced;
	}
	public void setNombre(String nom){
		nombre = nom;
	}
	public void setApellido(String ape){
		apellido = ape;
	}
	public void setParentesco(String par){
		parentesco = par;
	}
	public void setTelefono(String tel){
		telefono = tel;
		
	}
	public void setDireccion(String dir){
		direccion = dir;
	}
}