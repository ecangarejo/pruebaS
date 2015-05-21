/**
 * bean: CrearCita se encuentran los objetos que se necesitan para
 * la creacion y actualizacion de las citas de imagenologia.
*/

package img_bean;

public class CrearCita {
	
	String codigoPac_fk="";
	String codigoExa_fk="";
	String fecha="";
	String hora="";
	String estado="";
	String resultado="";
	String asignar_cita="";
	String aprobacion="";
	String insercion="";
	String hora_cita="";
	String fecha_cita="";
	String datosClinicos="";
	String Diagnostico="";
	String portatil="";
	String cedula="";
	
	public CrearCita(){}
	public String getcedula() {
		return cedula;
	}
	public void setcedula(String cedula) {
		this.cedula = cedula;
	}
//////////////////////////////////////////
	public String getportatil() {
		return portatil;
	}
	public void setportatil(String portatil) {
		this.portatil = portatil;
	}
	//////////////////////////////////////////
	public String getDiagnostico() {
		return Diagnostico;
	}
	public void setDiagnostico(String Diagnostico) {
		this.Diagnostico = Diagnostico;
	}
	//////////////////////////////////////////
	public String getdatosClinicos() {
		return datosClinicos;
	}
	public void setdatosClinicos(String datosClinicos) {
		this.datosClinicos = datosClinicos;
	}
	//////////////////////////////////////////
	public String getFechaCita() {
		return fecha_cita;
	}
	public void setFechaCita(String fecha_cita) {
		this.fecha_cita = fecha_cita;
	}
	//////////////////////////////////////////
	public String getHoraCita() {
		return hora_cita;
	}
	public void setHoraCita(String hora_cita) {
		this.hora_cita = hora_cita;
	}
	//////////////////////////////////////////
	public String getinsercion() {
		return insercion;
	}
	public void setinsercion(String insercion) {
		this.insercion = insercion;
	}
	//////////////////////////////////////////
	public String getaprobacion() {
		return aprobacion;
	}
	public void setaprobacion(String aprobacion) {
		this.aprobacion = aprobacion;
	}
	//////////////////////////////////////////
	public String getasignar_cita() {
		return asignar_cita;
	}
	public void setasignar_cita(String asignar_cita) {
		this.asignar_cita = asignar_cita;
	}	
	//////////////////////////////////////////
	public String getCodigoPac_fk() {
		return codigoPac_fk;
	}
	public void setCodigoPac_fk(String codigoPac_fk) {
		this.codigoPac_fk = codigoPac_fk;
	}
	//////////////////////////////////////////
	public String getCodigoExa_fk() {
		return codigoExa_fk;
	}
	public void setCodigoExa_fk(String codigoExa_fk) {
		this.codigoExa_fk = codigoExa_fk;
	}
	//////////////////////////////////////////
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	//////////////////////////////////////////
	public String gethora() {
		return hora;
	}
	public void sethora(String hora) {
		this.hora = hora;
	}
	//////////////////////////////////////////
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	//////////////////////////////////////////
	public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	//////////////////////////////////////////
}
