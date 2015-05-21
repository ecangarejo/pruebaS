/**
 * Bean preingreso, se utiliza para el ingreso y actualizacion 
 * de los datos mas relevantes del paciente a la cola de espera. 
 */
package adm_bean;
import java.util.Date;

public class Preingreso {
    String cedula ="";
    String nombre ="";
    String papellido ="";
    String sapellido="";
    String tipodocumento ="";
    String fecha=null;
    String hora=null;
    String eps="";
    String estado="";
    String gen="";
    /////////////////////////
    String TipoSalida="";
    String TipoTriage="";
    String CodCola_Fk="";
    String CodUsu_Fk="";
    String direccionar="";
    
    
    /****/
	public String getdireccionar() {
		return direccionar;
	}
	public void setdireccionar(String direccionar) {
		this.direccionar = direccionar;
	}
    /****/
	public String getTipoSalida() {
		return TipoSalida;
	}
	public void setTipoSalida(String TipoSalida) {
		this.TipoSalida = TipoSalida;
	}
	/****/
	public String getTipoTriage() {
		return TipoTriage;
	}
	public void setTipoTriage(String TipoTriage) {
		this.TipoTriage = TipoTriage;
	}
	/****/
	public String getCodCola_Fk() {
		return CodCola_Fk;
	}
	public void setCodCola_Fk(String CodCola_Fk) {
		this.CodCola_Fk = CodCola_Fk;
	}
	/****/
	public String getCodUsu_Fk() {
		return CodUsu_Fk;
	}
	public void setCodUsu_Fk(String CodUsu_Fk) {
		this.CodUsu_Fk = CodUsu_Fk;
	}
	/****/
    public Preingreso(){
    	
    }
    public Preingreso(String ced, String nom, String pape, String sape,String ep , String f, String hor,String td, String es,String genero){
    	cedula = ced;
    	nombre = nom;
    	papellido = pape;
    	sapellido = sape;
    	eps=ep;
    	fecha = f;
    	hora = hor;
    	tipodocumento = td;
    	estado=es;
    	gen=genero;
    }
    public String getGenero(){
		return gen;
	}
	public String getCedula(){
		return cedula;
	}
	
	public String getNombre(){
		return nombre;
	}
	public String getPapellido(){
		return papellido;
	}
	public String getSapellido(){
		return sapellido;
	}
	public String getTipodocumento(){
		return tipodocumento;
	}
	public String getFecha(){
		return fecha;
	}
	public String getHora(){
		return hora;
	}
	public String getEps(){
		return eps;
	}
	public String getEstado(){
		return estado;
	}
	
	//Modificar
	public void setGenero(String genero){
		gen = genero;
	}
	public void setCedula(String ced){
		cedula = ced;
	}
	public void setNombre(String nom){
		nombre = nom;
	}
	public void setPapellido(String pape){
		papellido = pape;
	}
	public void setSapellido(String sape){
		sapellido = sape;
	}
	public void setTipodocumento(String tipo){
		tipodocumento = tipo;
	}
	public void setFecha(String fech){
		fecha = fech;
		
	}
	public void setHora(String hor){
		hora = hor;
	}
	public void setEps(String ep){
		eps = ep;
	}
	public void setEstado(String es){
		estado=es;
	}
    
}
