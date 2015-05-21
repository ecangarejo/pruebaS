/**
 * Bean CrearAntecedentes, se encuentran los objetos necesarios
 * para la creacion y actualizacion de los antecedentes del paciente.
 */
package hic_bean;

public class CrearAntecedentes {

	String NomAlergia="";
	String NomReaccion="";
	String fecha="";
	String hora="";
	String CodPac="";
	String CodUsu="";
	String Familiar="";
	String CodCIE_fk="";
	String Observacion="";
	String Estudio="";
	/***/
	String tipo="";
	/***/
	String transfucion="";
	String cantidad="";
	String fechaTrans="";
	/***/
	String fechaQx="";
	String codigoQx="";
	/***/
	String codigoMd="";
	String cedula="";
	public CrearAntecedentes(){}
	
	
	/****/
	public String getEstudio() {
		return Estudio;
	}
	public void setEstudio(String Estudio) {
		this.Estudio = Estudio;
	}
	/****/
	public String getcedula() {
		return cedula;
	}
	public void setcedula(String cedula) {
		this.cedula = cedula;
	}
	/****/
	public String getcodigoMd() {
		return codigoMd;
	}
	public void setcodigoMd(String codigoMd) {
		this.codigoMd = codigoMd;
	}
	/****/
	public String getcodigoQx() {
		return codigoQx;
	}
	public void setcodigoQx(String codigoQx) {
		this.codigoQx = codigoQx;
	}
	/****/
	public String getfechaQx() {
		return fechaQx;
	}
	public void setfechaQx(String fechaQx) {
		this.fechaQx = fechaQx;
	}
	/****/
	public String getfechaTrans() {
		return fechaTrans;
	}
	public void setfechaTrans(String fechaTrans) {
		this.fechaTrans = fechaTrans;
	}
	/****/
	public String getcantidad() {
		return cantidad;
	}
	public void setcantidad(String cantidad) {
		this.cantidad = cantidad;
	}
	/****/
	public String gettransfucion() {
		return transfucion;
	}
	public void settransfucion(String transfucion) {
		this.transfucion = transfucion;
	}
	/****/
	public String gettipo() {
		return tipo;
	}
	public void settipo(String tipo) {
		this.tipo = tipo;
	}
	/****/
	public String getObservacion() {
		return Observacion;
	}
	public void setObservacion(String Observacion) {
		this.Observacion = Observacion;
	}
	/****/
	public String getCodCIE_fk() {
		return CodCIE_fk;
	}
	public void setCodCIE_fk(String CodCIE_fk) {
		this.CodCIE_fk = CodCIE_fk;
	}
	/****/
	public String getFamiliar() {
		return Familiar;
	}
	public void setFamiliar(String Familiar) {
		this.Familiar = Familiar;
	}
	/****/
	public String getCodUsu() {
		return CodUsu;
	}
	public void setCodUsu(String CodUsu) {
		this.CodUsu = CodUsu;
	}	
	/****/
	public String getCodPac() {
		return CodPac;
	}
	public void setCodPac(String CodPac) {
		this.CodPac = CodPac;
	}
	/****/
	public String getNomAlergia() {
		return NomAlergia;
	}
	public void setNomAlergia(String NomAlergia) {
		this.NomAlergia = NomAlergia;
	}
	/****/
	public String getNomReaccion() {
		return NomReaccion;
	}
	public void setNomReaccion(String NomReaccion) {
		this.NomReaccion = NomReaccion;
	}
	/****/
	public String getfecha() {
		return fecha;
	}
	public void setfecha(String fecha) {
		this.fecha = fecha;
	}
	/****/
	public String gethora() {
		return hora;
	}
	public void sethora(String hora) {
		this.hora = hora;
	}
	
}
