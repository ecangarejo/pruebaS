/**
 * Bean CrearCama, se utiliza para el ingreso y actualizacion 
 * de las camas. 
 */
package adm_bean;

public class CrearCama {

	String tipohabitacion ="";
	String area ="";
	String subarea ="";
	String piso_ubicacion ="";
	String tipo_cama="";
	String observaciones="";
	String cod_estado="";
	String cod_subarea="";
	String cod_cama="";
	String posx="";
	String posy="";
	String posicion="";
	String Direccionamiento="";
	
	
	public String getDireccionamiento() {
		return Direccionamiento;
	}
	public void setDireccionamiento(String Direccionamiento) {
		this.Direccionamiento = Direccionamiento;
	}
	
	public String getPosx() {
		return posx;
	}
	public void setPosx(String posx) {
		this.posx = posx;
	}
	
	public String getPosy() {
		return posy;
	}
	public void setPosy(String posy) {
		this.posy = posy;
	}
	
	public String getPosicion() {
		return posicion;
	}
	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}
	
	public String getTipoHabitacion() {
		return tipohabitacion;
	}
	//Modificar
	public void setTipoHabitacion(String tipo_hab){
		tipohabitacion = tipo_hab;
	}
	public String getArea() {
		return area;
	}
	//Modificar
	public void setArea(String areac){
		area = areac;
	}
	public String getSubArea() {
		return subarea;
	}
	//Modificar
	public void setSubArea(String sub_area){
		subarea = sub_area;
	}
	public String getPisoUbicacion() {
		return piso_ubicacion;
	}
	//Modificar
	public void setPisoUbicacion(String piso_ubica){
		piso_ubicacion = piso_ubica;
	}
	public String getTipoCama() {
		return tipo_cama;
	}
	//Modificar
	public void setTipoCama(String tipo_cam){
		tipo_cama = tipo_cam;
	}
	public String getObservaciones() {
		return observaciones;
	}
	//Modificar
	public void setObservaciones(String obser){
		observaciones = obser;
	}
	public String getCodigoEstado() {
		return cod_estado;
	}
	//Modificar
	public void setCodigoEstado(String cod_est){
		cod_estado = cod_est;
	}
	public String getCodigoSubarea() {
		return cod_subarea;
	}
	//Modificar
	public void setCodigoSubarea(String cod_sub){
		cod_subarea = cod_sub;
	}
	public String getCodigoCama() {
		return cod_cama;
	}
	//Modificar
	public void setCodigoCama(String cod_cam){
		cod_cama = cod_cam;
	}
	
	
	
}//fin de la clase bean cama
