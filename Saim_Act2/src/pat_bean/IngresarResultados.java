/**
 * Bean IngresarResultados, se encuentran los objetos necesarios
 * para la insercion de el contenido de los formatos al paciente.
 */
package pat_bean;

public class IngresarResultados {

	String codigo="";
	String codPa_fk="";
	String codAdm_fk="";
	String codPreg_fk="";
	String resultados="";
	String estado="";
	String hora="";
	String fecha="";
	String codUsu_fk="";
	String codArea_Fk="";
	String CodFormato="";
	String CodResul="";
	String CodDiagnostico="";
	String TipoDiag="";
	//////////////////////////////
	String DestinoPaciente="";
	String EstadoSalida="";
	
	public IngresarResultados(){}
	//////////////////////////////////////
	public String getEstadoSalida() {
		return EstadoSalida;
	}
	public void setEstadoSalida(String EstadoSalida) {
		this.EstadoSalida = EstadoSalida;
	}
	//////////////////////////////////////
	public String getDestinoPaciente() {
		return DestinoPaciente;
	}
	public void setDestinoPaciente(String DestinoPaciente) {
		this.DestinoPaciente = DestinoPaciente;
	}
	//////////////////////////////////////
	public String getTipoDiag() {
		return TipoDiag;
	}
	public void setTipoDiag(String TipoDiag) {
		this.TipoDiag = TipoDiag;
	}
	//////////////////////////////////////
	public String getCodDiagnostico() {
		return CodDiagnostico;
	}
	public void setCodDiagnostico(String CodDiagnostico) {
		this.CodDiagnostico = CodDiagnostico;
	}
	//////////////////////////////////////
	public String getCodResul() {
		return CodResul;
	}
	public void setCodResul(String CodResul) {
		this.CodResul = CodResul;
	}
	
	//////////////////////////////////////
	public String getCodFormato() {
		return CodFormato;
	}
	public void setCodFormato(String CodFormato) {
		this.CodFormato = CodFormato;
	}
	//////////////////////////////////////
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	//////////////////////////////////////
	public String getcodPa_fk() {
		return codPa_fk;
	}
	public void setcodPa_fk(String codPa_fk) {
		this.codPa_fk = codPa_fk;
	}
	//////////////////////////////////////
		
	public String getcodAdm_fk() {
		return codAdm_fk;
	}
	public void setcodAdm_fk(String codAdm_fk) {
		this.codAdm_fk = codAdm_fk;
	}
	/////////////////////////////////////////
	public String getcodPreg_fk() {
		return codPreg_fk;
	}
	public void setcodPreg_fk(String codPreg_fk) {
		this.codPreg_fk = codPreg_fk;
	}
	/////////////////////////////////////////
	public String getresultados() {
		return resultados;
	}
	public void setresultados(String resultados) {
		this.resultados = resultados;
	}
	///////////////////////////////////////////
	public String getestado() {
		return estado;
	}
	public void setestado(String estado) {
		this.estado = estado;
	}
	///////////////////////////////////////////
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	///////////////////////////////////////////
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	///////////////////////////////////////////
	public String getcodUsu_fk() {
		return codUsu_fk;
	}
	public void setcodUsu_fk(String codUsu_fk) {
		this.codUsu_fk = codUsu_fk;
	}
	///////////////////////////////////////////
	public String getcodArea_Fk() {
		return codArea_Fk;
	}
	public void setcodArea_Fk(String codArea_Fk) {
		this.codArea_Fk = codArea_Fk;
	}
}
