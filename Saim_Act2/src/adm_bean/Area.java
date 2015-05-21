/**
 * Bean Area, se utiliza para el ingreso y actualizacion 
 * de un area de la institucion. 
 */

package adm_bean;

public class Area {
	int codigo=0;
	String nombre="";
	
	public Area(){
		
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
