package hic_bean;


public class Formulacion {
	
	public Formulacion(){}
	
	String CodPac="";
	String CodAdm="";
	String usuario="";

	String CodCama="";
	String CodArea="";
	String CodSubarea="";
	String hora="";
	String fecha="";
	
	String Observacion="";
	String CodFormulacion_fk="";
	String CodigoMed="";
	String Cantidad="";
	String Dosificacion="";
	String Estado="";
	String CodDetFormulacion_fk="";
	
	public String getCodDetFormulacion_fk() {
		return CodDetFormulacion_fk;
	}
	public void setCodDetFormulacion_fk(String codDetFormulacion_fk) {
		CodDetFormulacion_fk = codDetFormulacion_fk;
	}
	public String getEstado() {
		return Estado;
	}
	public void setEstado(String estado) {
		Estado = estado;
	}
	public String getCodPac() {
		return CodPac;
	}
	public void setCodPac(String codPac) {
		CodPac = codPac;
	}
	public String getCodAdm() {
		return CodAdm;
	}
	public void setCodAdm(String codAdm) {
		CodAdm = codAdm;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getCodCama() {
		return CodCama;
	}
	public void setCodCama(String codCama) {
		CodCama = codCama;
	}
	public String getCodArea() {
		return CodArea;
	}
	public void setCodArea(String codArea) {
		CodArea = codArea;
	}
	public String getCodSubarea() {
		return CodSubarea;
	}
	public void setCodSubarea(String codSubarea) {
		CodSubarea = codSubarea;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getObservacion() {
		return Observacion;
	}
	public void setObservacion(String observacion) {
		Observacion = observacion;
	}
	public String getCodFormulacion_fk() {
		return CodFormulacion_fk;
	}
	public void setCodFormulacion_fk(String codFormulacion_fk) {
		CodFormulacion_fk = codFormulacion_fk;
	}
	public String getCodigoMed() {
		return CodigoMed;
	}
	public void setCodigoMed(String codigoMed) {
		CodigoMed = codigoMed;
	}
	public String getCantidad() {
		return Cantidad;
	}
	public void setCantidad(String cantidad) {
		Cantidad = cantidad;
	}
	public String getDosificacion() {
		return Dosificacion;
	}
	public void setDosificacion(String dosificacion) {
		Dosificacion = dosificacion;
	}
}
