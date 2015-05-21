/**
 * Bean Cama, se utiliza para el ingreso y actualizacion 
 * de las camas que hacen parte de la institucion. 
 */
package adm_bean;

public class Cama {
	String tipohabitacion ="";
	String pabellon ="";
	String ubicacion ="";
	String estado="";
	String codigo="";
	String piso_ubicacion ="";
	String obs="";
	
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	public String getPiso_ubicacion() {
		return piso_ubicacion;
	}
	public void setPiso_ubicacion(String piso_ubicacion) {
		this.piso_ubicacion = piso_ubicacion;
	}
	public String getTipo_cama() {
		return tipo_cama;
	}
	public void setTipo_cama(String tipo_cama) {
		this.tipo_cama = tipo_cama;
	}
	String tipo_cama ="";
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getTipohabitacion() {
		return tipohabitacion;
	}
	public void setTipohabitacion(String tipohabitacion) {
		this.tipohabitacion = tipohabitacion;
	}
	public String getPabellon() {
		return pabellon;
	}
	public void setPabellon(String pabellon) {
		this.pabellon = pabellon;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	
	
}
