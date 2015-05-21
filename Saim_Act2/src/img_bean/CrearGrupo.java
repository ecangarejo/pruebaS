/**
 * bean: CrearGrupo se encuentran los objetos que se necesitan para
 * la creacion y actualizacion de ls grupo que van a contener los examenes.
*/

package img_bean;

public class CrearGrupo {
	String codigo="";
	String nombre="";
	
	public CrearGrupo(){
		
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
