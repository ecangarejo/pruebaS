/**
 * Bean CrearPacLab, se encuentran los objetos necesarios
 * para la creacion y actualizacion y asignacion de estudios de laboratorio
 * al paciente.
 */
package hic_bean;

public class CrearPacLab {
	

	String codigo_pac_fk="";
	String codigo_estudio="";
	String tipoEstudio="";
	String estado="";
	String hora="";
	String fecha="";
	String usuario="";
	String TipoPyP="";
	public CrearPacLab(){}
	
	public String getTipoPyP() {
		return TipoPyP;
	}
	public void setTipoPyP(String TipoPyP) {
		this.TipoPyP = TipoPyP;
	}
	
	public String getusuario() {
		return usuario;
	}
	public void setusuario(String usuario) {
		this.usuario = usuario;
	}
	
	public String getCodigoPac() {
		return codigo_pac_fk;
	}
	public void setCodigoPac(String codigo_pac_fk) {
		this.codigo_pac_fk = codigo_pac_fk;
	}
	
	public String getCodigoEstudio() {
		return codigo_estudio;
	}
	public void setCodigoEstudio(String codigo_estudio) {
		this.codigo_estudio = codigo_estudio;
	}
	
	public String getTipoEstudio() {
		return tipoEstudio;
	}
	public void setTipoEstudio(String tipoEstudio) {
		this.tipoEstudio = tipoEstudio;
	}
	
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
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

}
