/**
 * bean: CrearSubExa se encuentran los objetos que se necesitan para
 * la creacion y actualizacion de los examenes que contendra cada grupo.
*/


package img_bean;

public class CrearSubExa {
	
	
	String cod_gruFk="";
	String numeroExamen="";
	String nombre="";
	String patron="";
	public CrearSubExa(){}
	//////////////////////////////////////////////////
	public String getcod_gruFk() {
		return cod_gruFk;
	}
	public void setcod_gruFk(String cod_gruFk) {
		this.cod_gruFk = cod_gruFk;
	}
	//////////////////////////////////////////////////
	public String getnumeroExamen() {
		return numeroExamen;
	}
	public void setnumeroExamen(String numeroExamen) {
		this.numeroExamen = numeroExamen;
	}
	//////////////////////////////////////////////////
	public String getnombre() {
		return nombre;
	}
	public void setnombre(String nombre) {
		this.nombre = nombre;
	}
	//////////////////////////////////////////////////
	public String getPatron() {
		return patron;
	}
	public void setPatron(String patron) {
		this.patron = patron;
	}
	//////////////////////////////////////////////////

}
