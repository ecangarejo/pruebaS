/**
 * Bean Admisiones, se utiliza para el ingreso y actualizacion 
 * del diagnostico que se le hace a un paciente en el preingreso. 
 */
package adm_bean;

public class DiagnosticoCola {
	String CodAdmCola="";
	String CedPac="";
	String usuario="";
	String codigoCIE="";
	String fecha="";
	String hora="";
	String observacion="";
	public DiagnosticoCola(){}
	////////////////////////
	public String gethora() {
		return hora;
	}
	public void sethora(String hora) {
		this.hora = hora;
	}
	public String getobservacion() {
		return observacion;
	}
	public void setobservacion(String observacion) {
		this.observacion = observacion;
	}
	////////////////////////
	public String getfecha() {
		return fecha;
	}
	public void setfecha(String fecha) {
		this.fecha = fecha;
	}
	////////////////////////
	public String getcodigoCIE() {
		return codigoCIE;
	}
	public void setcodigoCIE(String codigoCIE) {
		this.codigoCIE = codigoCIE;
	}
	////////////////////////
	public String getusuario() {
		return usuario;
	}
	public void setCodUsuario(String usuario) {
		this.usuario = usuario;
	}	
	////////////////////////////////////////////////////////
	public String getCodAdmCola() {
		return CodAdmCola;
	}
	public void setCodAdmCola(String CodAdmCola) {
		this.CodAdmCola = CodAdmCola;
	}
	////////////////////////////////////////////////////
	public String getCedPac() {
		return CedPac;
	}
	public void setCedPac(String CedPac) {
		this.CedPac = CedPac;
	}
	
}
