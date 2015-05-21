package pyp_bean;

public class PYP_AsignarCita {
	
	
	String CodigoEspecialidad="";
	String codigoHorarioMedico="";
	String CodigoPaciente="";
	String CodigoMedico="";
	String UsuarioInsercion="";
	String UsuarioCancelar="";
	String FechaCita="";
	
	public String getFechaCita() {
		return FechaCita;
	}
	public void setFechaCita(String FechaCita) {
		this.FechaCita = FechaCita;
	}
	
	public String getUsuarioInsercion() {
		return UsuarioInsercion;
	}
	public void setUsuarioInsercion(String UsuarioInsercion) {
		this.UsuarioInsercion = UsuarioInsercion;
	}
	
	public String getCodigoEspecialidad() {
		return CodigoEspecialidad;
	}
	public void setCodigoEspecialidad(String CodigoEspecialidad) {
		this.CodigoEspecialidad = CodigoEspecialidad;
	}
	
	public String getcodigoHorarioMedico() {
		return codigoHorarioMedico;
	}
	public void setcodigoHorarioMedico(String codigoHorarioMedico) {
		this.codigoHorarioMedico = codigoHorarioMedico;
	}
	
	public String getCodigoPaciente() {
		return CodigoPaciente;
	}
	public void setCodigoPaciente(String CodigoPaciente) {
		this.CodigoPaciente = CodigoPaciente;
	}
	
	public String getCodigoMedico() {
		return CodigoMedico;
	}
	public void setCodigoMedico(String CodigoMedico) {
		this.CodigoMedico = CodigoMedico;
	}
}
