/**
 * Bean CrearFormato, se encuentran los objetos necesarios
 * para la creacion y actualizacion y asignacion de un formato 
 * a la historia clinica del paciente. 
 */
package pat_bean;

public class CrearFormatoHic {
	String codigo="";
	String nombreFormato="";
	String codigo_area_fk="";
	String codigo_formato_fk="";
	String codigo_adm_fk="";
	String codigo_pac_fk="";
	String codigo_for_fk="";
	////////////////////////
	String CodUsuario="";
	String Fecha="";
	String Hora="";
	////////////////////////
	public CrearFormatoHic(){}
////////////////////////
	public String getHora() {
		return Hora;
	}
	public void setHora(String Hora) {
		this.Hora = Hora;
	}
	////////////////////////
	public String getFecha() {
		return Fecha;
	}
	public void setFecha(String Fecha) {
		this.Fecha = Fecha;
	}
	////////////////////////
	public String getCodUsuario() {
		return CodUsuario;
	}
	public void setCodUsuario(String CodUsuario) {
		this.CodUsuario = CodUsuario;
	}
	////////////////////////
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	////////////////////////
	public String getNombreFormato() {
		return nombreFormato;
	}
	public void setNombreFormato(String nombreFormato) {
		this.nombreFormato = nombreFormato;
	}
	/////////////////////////////////////////////
	//codigo_area_fk,codigo_formato_fk
	public String getcodigo_area_fk() {
		return codigo_area_fk;
	}
	public void setcodigo_area_fk(String codigo_area_fk) {
		this.codigo_area_fk = codigo_area_fk;
	}
	///
	public String getcodigo_formato_fk() {
		return codigo_formato_fk;
	}
	public void setcodigo_formato_fk(String codigo_formato_fk) {
		this.codigo_formato_fk = codigo_formato_fk;
	}
	////////////////////////////////////////////////////////
	//codigo_adm_fk,codigo_pac_fk,codigo_for_fk
	public String getcodigo_adm_fk() {
		return codigo_adm_fk;
	}
	public void setcodigo_adm_fk(String codigo_adm_fk) {
		this.codigo_adm_fk = codigo_adm_fk;
	}
	//
	public String getcodigo_pac_fk() {
		return codigo_pac_fk;
	}
	public void setcodigo_pac_fk(String codigo_pac_fk) {
		this.codigo_pac_fk = codigo_pac_fk;
	}
	
}
