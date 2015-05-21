/**
 * bean: Usuario.java se encuentran los objetos 
 * para la creacion y actualizacion de los usuarios que van a usar el sistema.
*/
package seguridad_bean;

public class Usuario {
	String nombre="";
	String apellido="";
	String direccion="";
	String telefono="";
	String email="";
	String profesion="";
	String ocupacion="";
	String cedula="";
	String estado="";
	///////////////////////////
	String usuario="";
	String contrasena="";
	String codigousu="";
	String codigo="";
	String NumeroDocumento="";
	String tipoDocumento="";
	

	public Usuario() {
		// TODO Auto-generated constructor stub
	}
	
	public String getCodigousu() {
		return codigousu;
	}

	public void setCodigousu(String codigousu) {
		this.codigousu = codigousu;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	

	public String getNumeroDocumento(){
		return NumeroDocumento;
	}
	public void setNumeroDocumento(String NumeroDocumento){
		this.NumeroDocumento = NumeroDocumento;
	}
	
	
	public String getEstado(){
		return estado;
	}
	public void setEstado(String est){
		estado = est;
	}
	
	
	public String getCodperfil(){
		return codigo;
	}
	public void setCodPerfil(String cod){
		codigo = cod;
	}
	
	public String getCedula(){
		return cedula;
	}
	public void setCedula(String ced){
		cedula = ced;
	}
	
	public String getCodigo(){
		return codigousu;
	}
	public void setCodigo(String codus){
		codigousu = codus;
	}
	
	public String getUsuario(){
		return usuario;
	}
	public void setUsuario(String usu){
		usuario = usu;
	}
	public String getContrasena(){
		return contrasena;
	}
	public void setContrasena(String contra){
		contrasena = contra;
	}
	public String getNombre(){
		return nombre;
	}
	public void setNombre(String nom){
		nombre = nom;
	}
	public String getApellido(){
		return apellido;
	}
	public void setApellido(String ape){
		apellido = ape;
	}
	public String getDireccion(){
		return direccion;
	}
	public void setDireccion(String dire){
		direccion = dire;
	}
	public String getTelefono(){
		return telefono;
	}
	public void setTelefono(String tel){
		telefono = tel;
	}
	public String getEmail(){
		return email;
	}
	public void setEmail(String mail){
		email = mail;
	}
	public String getProfesion(){
		return profesion;
	}
	public void setProfesion(String pro){
		profesion = pro;
	}
	public String getOcupacion(){
		return ocupacion;
	}
	public void setOcupacion(String ocu){
		ocupacion = ocu;
	}
	
	
	//Modificar
	
	

}
