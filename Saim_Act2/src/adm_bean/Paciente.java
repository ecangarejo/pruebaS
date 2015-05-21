/**
 * Bean Paciente, se utiliza para el ingreso y actualizacion 
 * de los datos demograficos del paciente. 
 */
package adm_bean;

import org.omg.CORBA.PUBLIC_MEMBER;

public class Paciente {
	
	    String tipo_documento = "";
	    String cedula = "";
	    String tipo_afiliacion="";
	    String nivel_cotizante="";
	    String papellido = "";
	    String sapellido = "";
		String nombre = "";
		String genero="";
		String nacionalidad="";
		String direccion = "";
		String telefonofijo = "";
		String telefonooficina = "";
		String telefonocelular="";
		String ocupacion="";
		String empresa_labora="";
		String zona_residencial="";
		String religion="";
		String estado_civil="";
		String raza="";
		String estrato="";
		String email="";
		String mun_codigo="";
		String fecha = null;
		String numerocontrato="";
		String longitud="";
		String latitud="";
		String barrio="";
		
	
		
		public Paciente(){
			
		}
		public Paciente(String tip, String ced,String afiliacion,String nivel,  String pape,String sape,String nom,String gene,String nacionali,String dir,String tel,String telofi,String telcel,String ocu,String emp,String zona,String reli,String estadoci,String ra,String estra,String ema,String mun_cod,String fechanaci, String numcontra,String longi, String lati,String bar){
			tipo_documento = tip;
			cedula = ced;
			tipo_afiliacion=afiliacion;
			nivel_cotizante=nivel;
			papellido = pape;
			sapellido=sape;
			nombre = nom;
			genero=gene;
			nacionalidad=nacionali;
			direccion = dir;
			telefonofijo = tel;
			telefonooficina=telofi;
			telefonocelular=telcel;
			ocupacion=ocu;
			empresa_labora=emp;
			zona_residencial=zona;
			religion=reli;
			estado_civil=estadoci;
			raza=ra;
			estrato=estra;
			email=ema;
			mun_codigo=mun_cod;
			fecha=fechanaci;
			numerocontrato=numcontra;
			longitud=longi;
			latitud=lati;
			barrio=bar;
			
		}
		
		public String getDocumento(){
			return tipo_documento;
		}
		public String getCedula(){
			return cedula;
		}
		public String getAfiliacion(){
			return tipo_afiliacion;
		}
		public String getCotizante(){
			return nivel_cotizante;
		}
		public String getPapellido(){
			return papellido;
		}
		public String getSapellido(){
			return sapellido;
		}
		public String getNombre(){
			return nombre;
		}
		public String getGenero(){
			return genero;
		}
		public String getNacionalidad(){
			return nacionalidad;
		}
		public String getDireccion(){
			return direccion;
		}
		public String getTelefonofijo(){
			return telefonofijo;
		}
		public String getTelefonooficina(){
			return telefonooficina;
		}
		public String getTelefonocelular(){
			return telefonocelular;
		}
		public String getOcupacion(){
			return ocupacion;
		}
		public String getEmpresa(){
			return empresa_labora;
		}
		public String getZona(){
			return zona_residencial;
		}
		public String getReligion(){
			return religion;
		}
		public String getEstadocivil(){
			return estado_civil;
		}
		public String getRaza(){
			return raza;
		}
		public String getEstrato(){
			return estrato;
		}
		public String getEmail(){
			return email;
		}
		public String getCodigoMun(){
			return mun_codigo;
		}
		public String getFecha(){
			return fecha;
		}
		public String getContrato(){
			return numerocontrato;
		}
	    
		public String getLongitud(){
			return longitud;
		}
		
		public String getLatitud(){
			return latitud;
		}
		
		public String getBarrio() {
			return barrio;
		}
			
		//Modificar
		public void setDocumento(String tip){
			tipo_documento = tip;
		}
		public void setCedula(String ced){
			cedula = ced;
		}
		public void setAfiliacion(String afiliacion){
			tipo_afiliacion=afiliacion;
		}
		public void setCotizante(String nivel){
			nivel_cotizante=nivel;
		}
		public void setPapellido(String pape){
			papellido = pape;
		}
		public void setSapellido(String sape){
			sapellido=sape;
		}
		public void setNombre(String nom){
			nombre = nom;
		}
		public void setGenero(String gene){
			genero=gene;
		}
		public void setNacionalidad(String nacionali){
			nacionalidad=nacionali;
		}
		public void setDireccion(String dir){
			direccion = dir;
		}
		public void setTelefonofijo(String tel){
			telefonofijo = tel;
		}
		public void setTelefonooficina(String telofi){
			telefonooficina=telofi;
		}
		
		public void setTelefonocelular(String telcel){
			telefonocelular=telcel;
		}
		public void setOcupacion(String ocu){
			ocupacion=ocu;
		}
		public void setEmpresa(String emp){
			empresa_labora=emp;
		}
        public void setZona(String zona ){
        	zona_residencial=zona;
		}
        public void setReligion(String reli){
        	religion=reli;
			
		}
        public void setEstadocivil(String estadoci){
        	estado_civil=estadoci;
		}
        public void setRaza(String ra){
        	raza=ra;
			
		}
        public void setEstrato(String estra){
        	estrato=estra;
			
		}
        public void setEmail(String ema){
        	email=ema;
		}
        public void setCodigoMun(String mun_cod){
        	mun_codigo=mun_cod;
			
		}
        public void setFecha(String fechanaci){
			fecha=fechanaci;
			
		}
		public void setContrato(String numcontra){
			numerocontrato = numcontra;
		}
		public void setLongitud(String longi){
			longitud=longi;
		}
		public void setLatitud(String lati){
			latitud=lati;
		}
		public void setBarrio(String bar){
			barrio=bar;
		}
		
		
}

