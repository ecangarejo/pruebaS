package seguridad_logica;

import java.sql.Date;
import java.sql.Time;

public class registro24h {

	
	String CodUsuario="";
	String CodPaciente="";
	Date fecha;
	Time hora;
	
	public String getCodUsuario() {
		return CodUsuario;
	}
	public void setCodUsuario(String CodUsuario) {
		this.CodUsuario = CodUsuario;
	}
	
	public String getCodPaciente() {
		return CodPaciente;
	}
	public void setCodPaciente(String CodPaciente) {
		this.CodPaciente = CodPaciente;
	}
	
	public Time gethora() {
		return hora;
	}
	public void sethora(Time hora) {
		this.hora = hora;
	}
	
	public Date getfecha() {
		return fecha;
	}
	public void setfecha(Date fecha) {
		this.fecha = fecha;
	}
}
