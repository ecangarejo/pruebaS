/**
 * Bean Admisiones, se utiliza para el ingreso y actualizacion 
 * de la admision del paciente. 
 */
package adm_bean;

public class Admisiones {
    String tipodocumento="";
	String cedula="";
    String entidad="";    
    String numeroingreso="";
    String numeroautorizacion="";
    String medioautorizacion="";
    String autorizadopor="";
    String registradopor="";
    String fechareg="";
    String horareg="";
    String estadoafiliacion="";   
    String semanascotizadas="";
    String documentos="";
    String observaciones="";
    String remitidode="";
    String numerocama="";
    String nombrepaciente="";
    String apellidopaciente="";
    String codigo_contacto="";
    
    
    
    
    public String getDocumentos() {
		return documentos;
	}

	public void setDocumentos(String documentos) {
		this.documentos = documentos;
	}

	public Admisiones(){
    	
    }
       
    public String getTipodocumento(){
    	return tipodocumento;
    }
    public String getCedula(){
    	return cedula;
    }
    public String getEntidad(){
    	return entidad;
    }
    public String getNumeroingreso(){
    	return numeroingreso;
    }
    public String getNumeroAutorizacion(){
    	return numeroautorizacion;
    }
    public String getMedioAutorizacion(){
    	return medioautorizacion;
    }
    public String getFechaRegistro(){
    	return fechareg;
    }
    public String getHoraRegistro(){
    	return horareg;
    }
    public String getAutorizadoPor(){
    	return autorizadopor;
    }
    public String getRegistradoPor(){
    	return registradopor;
    }
    public String getRemitidoDe(){
    	return remitidode;
    }
    public String getEstadoAfilicacion(){
    	return estadoafiliacion;
    }
    public String getSemanasCotizadas(){
    	return semanascotizadas;
    }
    public String getNombrePaciente(){
    	return nombrepaciente;
    }
    public String getApellidoPaciente(){
    	return apellidopaciente;
    }
    public String getNumeroCama(){
    	return numerocama;
    }
    public String getObservaciones(){
        return observaciones;
    }
    public String getContacto(){
        return codigo_contacto;
    }
    //Modificadores
    
    public void setTipodocumento(String n){
    	tipodocumento =n;
    }
    public void setCedula(String n){
    	cedula=n;
    }
    public void setEntidad(String n){
    	entidad=n;
    }
    public void setNumeroingreso(String n){
    	numeroingreso=n;
    }
    public void setNumeroAutorizacion(String n){
    	numeroautorizacion = n;
    }
    public void setMedioAutorizacion(String n){
    	medioautorizacion=n;
    }
    public void setFechaRegistro(String n){
    	fechareg=n;
    }
    public void setHoraRegistro(String n){
    	horareg=n;
    }
    public void setAutorizadoPor(String n){
    	autorizadopor=n;
    }
    public void setRegistradodoPor(String n){
    	registradopor=n;
    }
    public void setRemitidoDe(String n){
    	remitidode=n;
    }
    public void setEstadoAfilicacion(String n){
    	estadoafiliacion=n;
    }
    public void setSemanasCotizadas(String n){
    	semanascotizadas=n;
    }
    public void setNombrePaciente(String n){
    	nombrepaciente=n;
    }
    public void setApellidoPaciente(String n){
    	apellidopaciente=n;
    }
    public void setNumeroCama(String n){
    	numerocama=n;
    }
    public void setObservaciones(String n){
        observaciones=n;
    }
    public void setContacto(String contac){
        codigo_contacto=contac;
    }
    
}

