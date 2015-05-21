package agm_bean;

public class CrearMedico {

	String numeroDocumento="";
	String tipoDocumento="";
	String nombre="";
	String apellidos="";
	String tarjetaProfesional="";
	String profesion="";
	String codEspe_fk="";
	String direccion="";
	String telefono="";
	
	
	public String getnumeroDocumento() {
		return numeroDocumento;
	}
	public void setnumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	/******************************************/
	public String gettipoDocumento() {
		return tipoDocumento;
	}
	public void settipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	/******************************************/
	public String getnombre() {
		return nombre;
	}
	public void setnombre(String nombre) {
		this.nombre = nombre;
	}
	/******************************************/
	public String getapellidos() {
		return apellidos;
	}
	public void setapellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	/******************************************/
	public String gettarjetaProfesional() {
		return tarjetaProfesional;
	}
	public void settarjetaProfesional(String tarjetaProfesional) {
		this.tarjetaProfesional = tarjetaProfesional;
	}
	/******************************************/
	public String getprofesion() {
		return profesion;
	}
	public void setprofesion(String profesion) {
		this.profesion = profesion;
	}
	/******************************************/
	public String getcodEspe_fk() {
		return codEspe_fk;
	}
	public void setcodEspe_fk(String codEspe_fk) {
		this.codEspe_fk = codEspe_fk;
	}
	/******************************************/
	public String getdireccion() {
		return direccion;
	}
	public void setdireccion(String direccion) {
		this.direccion = direccion;
	}
	/******************************************/
	public String gettelefono() {
		return telefono;
	}
	public void settelefono(String telefono) {
		this.telefono = telefono;
	}
}
