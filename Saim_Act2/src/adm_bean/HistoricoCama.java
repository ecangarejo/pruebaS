/**
 * Bean HistoricoCama, se utiliza para el ingreso y actualizacion 
 * de la rotacion de la cama. 
 */

package adm_bean;

import java.sql.Date;

public class HistoricoCama{
	
		String cen_cama= "";
		String fecha_ini;
		String fecha_fi;
		String num_ingre= "";
		String hora_inicial="";
		String hora_final="";
		String usuarioIni="";
		String usuarioFin="";
		
		public HistoricoCama(){
			
		}
		public HistoricoCama(String horini,String horfin,String usuini,String usufin,String censo, String feini, String fefi, String numi){
			cen_cama = censo;
			fecha_ini = feini;
			fecha_fi = fefi;
			num_ingre = numi;
			hora_inicial=horini;
			hora_final=horfin;
			usuarioIni=usuini;
			usuarioFin=usufin;
			
		}
		public void sethora_inicial(String horini){
			hora_inicial = horini;
		}
		public void sethora_final(String horfin){
			hora_final = horfin;
		}
		public void setusuarioIni(String usuini){
			usuarioIni = usuini;
		}
		public void setusuarioFin(String usufin){
			usuarioFin = usufin;
		}
		/***************/
		
		public String gethora_inicial(){
			return hora_inicial;
		}
		public String gethora_final(){
			return hora_final;
		}
		public String getusuarioIni(){
			return usuarioIni;
		}
		public String getusuarioFin(){
			return usuarioFin;
		}
		/****************/
		public String getCama(){
			return cen_cama;
		}
		
		public String getFechai(){
			return fecha_ini;
		}
		public String getFechafi(){
			return fecha_fi;
		}
		public String getNumingreso(){
			return num_ingre;
		}
		
		//Modificar
		public void setCama(String cam){
			cen_cama = cam;
		}
		public void setFechai(String fei){
			fecha_ini = fei;
		}
		public void setFechafi(String fefi){
			fecha_fi = fefi;
		}
		public void setNumingreso(String num){
			num_ingre= num;
		}
		
}
