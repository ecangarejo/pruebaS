/**
 * Bean Censo, se utiliza para el ingreso,actualizacion y consulta 
 * del censo. 
 */

package adm_bean;

public class Censo implements java.io.Serializable {
	String cama="";
	String paciente ="";
	String diagnostico ="";
	String sexo="";
	int edad=0;
	String semanac ="";
	String eps ="";
	String medico="";
	String fechaentrada="";
	String dias="";
	String observacione="";
	String codigo ="";
	String pabellon="";
    String ubicacion="";
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public Censo(){
		
	}
	public Censo(String cama, String paciente, String diag, String sexo, int edad,
			 String semanac, String eps, String medico, String fechae, String dias,
			 String obs, String pabe,String ubi){
		cama = cama;
		paciente = paciente;
		diagnostico  =diag;
		sexo = sexo;
		edad = edad;
		semanac=semanac;
		eps = eps;
		medico = medico;
		fechaentrada = fechaentrada;
		dias = dias;
		observacione = obs;	
		pabellon=pabe;
		ubicacion=ubi;
		
	}
	
	public String getCama() {
		return cama;
	}
	public void setCama(String cama) {
		this.cama = cama;
	}
	public String getPaciente() {
		return paciente;
	}
	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}
	public String getDiagnostico() {
		return diagnostico;
	}
	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public String getSemanac() {
		return semanac;
	}
	public void setSemanac(String semanac) {
		this.semanac = semanac;
	}
	public String getEps() {
		return eps;
	}
	public void setEps(String eps) {
		this.eps = eps;
	}
	public String getMedico() {
		return medico;
	}
	public void setMedico(String medico) {
		this.medico = medico;
	}
	public String getFechaentrada() {
		return fechaentrada;
	}
	public void setFechaentrada(String fechaentrada) {
		this.fechaentrada = fechaentrada;
	}
	public String getDias() {
		return dias;
	}
	public void setDias(String dias) {
		this.dias = dias;
	}
	public String getObservacione() {
		return observacione;
	}
	public void setObservacione(String observacione) {
		this.observacione = observacione;
	}
	public void setPabellon(String pabe) {
		this.pabellon = pabe;
	}
	public String getPabellon() {
		return pabellon;
	}
	public void setUbicacion(String ubi) {
		this.ubicacion = ubi;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	
}
