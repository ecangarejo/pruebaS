package cont_bean;

public class Proveedor {

	
	String tipo_identificacion ="";
	String numero_documento="";
	String nombre_razonsoc="";
	String autoretenedor="";
	String tipo_regimen="";
	String direccion="";
	String telefono="";
	String contacto="" ;
	String telcontacto="";
	String emailcontac="" ; 
	
	public String gettipo_identificacion() {
		return tipo_identificacion;
	}
	public void settipo_identificacion(String tipo_identificacion) {
		this.tipo_identificacion = tipo_identificacion;
	}
	/***************************************************************/
	public String getnumero_documento() {
		return numero_documento;
	}
	public void setnumero_documento(String numero_documento) {
		this.numero_documento = numero_documento;
	}
	/***************************************************************/
	public String getnombre_razonsoc() {
		return nombre_razonsoc;
	}
	public void setnombre_razonsoc(String nombre_razonsoc) {
		this.nombre_razonsoc = nombre_razonsoc;
	}
	/***************************************************************/
	public String getautoretenedor() {
		return autoretenedor;
	}
	public void setautoretenedor(String autoretenedor) {
		this.autoretenedor = autoretenedor;
	}
	/***************************************************************/
	public String gettipo_regimen() {
		return tipo_regimen;
	}
	public void settipo_regimen(String tipo_regimen) {
		this.tipo_regimen = tipo_regimen;
	}
	/***************************************************************/
	public String getdireccion() {
		return direccion;
	}
	public void setdireccion(String direccion) {
		this.direccion = direccion;
	}
	/***************************************************************/
	public String gettelefono() {
		return telefono;
	}
	public void settelefono(String telefono) {
		this.telefono = telefono;
	}
	/***************************************************************/
	public String getcontacto() {
		return contacto;
	}
	public void setcontacto(String contacto) {
		this.contacto = contacto;
	}
	/***************************************************************/
	public String gettelcontacto() {
		return telcontacto;
	}
	public void settelcontacto(String telcontacto) {
		this.telcontacto = telcontacto;
	}
	/***************************************************************/
	public String getemailcontac() {
		return emailcontac;
	}
	public void setemailcontac(String emailcontac) {
		this.emailcontac = emailcontac;
	}
}
