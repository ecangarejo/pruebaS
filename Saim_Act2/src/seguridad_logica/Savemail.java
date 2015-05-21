package seguridad_logica;

import java.sql.Date;
import java.sql.Time;

public class Savemail {

	String codusu="";
	String Codremi="";
	String estado="";
	String asunto="";
	String contmen="";
	String orden1="";
	String orden2="";
	Date fecha;
	Time hora;
		
	
	
	public String getcodusu() {
		return codusu;
	}
	public void setcodusu(String codusu) {
		this.codusu = codusu;
	}
	

	public String getorden1(){
		return orden1;
	}
	public void setorden1(String orden1){
		this.orden1= orden1;
	}
	
	public String getorden2(){
		return orden2;
	}
	public void setorden2(String orden2){
		this.orden2= orden2;
	}
	public String getCodremi() {
		return Codremi;
	}
	public void setCodremi(String Codremi) {
		this.Codremi = Codremi;
	}
	
	public String getestado() {
		return estado;
	}
	public void setestado(String estado) {
		this.estado = estado;
	}
	
	public String getasunto() {
		return asunto;
	}
	public void setasunto(String asunto) {
		this.asunto = asunto;
	}
	
	public String getcontmen() {
		return contmen;
	}
	public void setcontmen(String contmen) {
		this.contmen = contmen;
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
