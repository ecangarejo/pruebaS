/**
 * Bean ResultadoFormCola, se utiliza para el ingreso y actualizacion 
 * de los resultados de los formatos que se le asignan al paciente en el preingreso. 
 */
package adm_bean;

public class ResultadoFormCola {
	String CedPac="";
	String CodAdmCola="";
	String CodigoPregunta="";
	String resultados="";
	String estado="";
	String fecha="";
	String hora="";
	String usuario="";
	String CodigoArea="";

	////////////////////////
	public ResultadoFormCola(){}
////////////////////////
	public String getCodigoArea() {
		return CodigoArea;
	}
	public void setCodigoArea(String CodigoArea) {
		this.CodigoArea = CodigoArea;
	}	
	////////////////////////
	public String getestado() {
		return estado;
	}
	public void setestado(String estado) {
		this.estado = estado;
	}
	
	////////////////////////
	public String getresultados() {
		return resultados;
	}
	public void setresultados(String resultados) {
		this.resultados = resultados;
	}
	////////////////////////
	public String getCodigoPregunta() {
		return CodigoPregunta;
	}
	public void setCodigoPregunta(String CodigoPregunta) {
		this.CodigoPregunta = CodigoPregunta;
	}
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
	///////////////////////////////////////////////////////

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
