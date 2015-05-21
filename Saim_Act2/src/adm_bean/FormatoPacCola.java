/**
 * Bean FormatoPacCola, se utiliza para la asignacion de un formato a un paciente
 * que se encuentra en la cola de urgencia.
 *  
 */
package adm_bean;

public class FormatoPacCola {
	String CodFormato="";
	String CodAdmCola="";
	String CedPac="";
	String usuario="";
	String fecha="";
	String hora="";
	////////////////////////
	public FormatoPacCola(){}
////////////////////////
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	////////////////////////
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	////////////////////////
	public String getusuario() {
		return usuario;
	}
	public void setCodUsuario(String usuario) {
		this.usuario = usuario;
	}	
	////////////////////////
	public String getCodFormato() {
		return CodFormato;
	}
	public void setCodFormato(String CodFormato) {
		this.CodFormato = CodFormato;
	}
	////////////////////////////////////////////////////////

	public String getCodAdmCola() {
		return CodAdmCola;
	}
	public void setCodAdmCola(String CodAdmCola) {
		this.CodAdmCola = CodAdmCola;
	}
	//
	public String getCedPac() {
		return CedPac;
	}
	public void setCedPac(String CedPac) {
		this.CedPac = CedPac;
	}
	
}
