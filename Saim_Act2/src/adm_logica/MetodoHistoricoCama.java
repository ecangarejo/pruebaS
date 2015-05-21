/**
 * logica:MetodoHistoricoCama
 * Desarrollado:yosimar valega
 */

package adm_logica;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Time;

import adm_bean.HistoricoCama;


public class MetodoHistoricoCama {
public MetodoHistoricoCama(){	
		
	}

/**
 * actualiza datos de la tabla adm_historico_cama
 */

public void ActualizarHistoriaTraslado(String CamaVieja,String NumeroAdmision,String FechaFinal,String hora_final,String usuarioFin){
		
	PreparedStatement st = null;
	        try{
	        	Conexion con=new Conexion();	        	
	        	st= con.conn.prepareStatement("Update adm_historico_cama set fecha_final='"+FechaFinal+"',hora_final='"+hora_final+"',usuarioFin='"+usuarioFin+"' where adm_numero_ingreso_fk='"+NumeroAdmision+"'and cen_numero_cama_fk='"+CamaVieja+"'");
	        	st.executeUpdate();
	        	st.close();
	        	con.cerrar();
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        
	        }	
	    }


public void ActualizarHistoria(String CamaVieja,String NumeroAdmision,Date FechaFinal,Time hora_final,String usuarioFin){
	
	//HistoricoCama hc=new HistoricoCama();	
	/*String fechafinal,dia,mes,anio=null;
	dia=FechaFinal.substring(0,2);	
	mes=FechaFinal.substring(3,5);
	anio=FechaFinal.substring(6,10);
	fechafinal=anio+"/"+mes+"/"+dia;*/
	PreparedStatement st = null;
	System.out.println("Update adm_historico_cama set fecha_final='"+FechaFinal+"',hora_final='"+hora_final+"',usuarioFin='"+usuarioFin+"' where adm_numero_ingreso_fk="+NumeroAdmision+" and cen_numero_cama_fk="+CamaVieja+" ");
	        try{
	        	Conexion con=new Conexion();	        	
	        	st= con.conn.prepareStatement("Update adm_historico_cama set fecha_final='"+FechaFinal+"',hora_final='"+hora_final+"',usuarioFin='"+usuarioFin+"' where adm_numero_ingreso_fk="+NumeroAdmision+" and cen_numero_cama_fk="+CamaVieja+" ");
	        	
	        	st.executeUpdate();
	        	st.close();
	        	con.cerrar();
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        
	        }	
	    }

/**
 * obtiene numero de ingreso de un admision
 */

public void insertar2(String cen, Date fechaFinal, String fechafi, String num_ing,Time horaIni,String usuarioIni,String horaFinal,String usuarioFin){
	//, , , ,String horaIni,String usuario,String horaFinal
	HistoricoCama hi = new HistoricoCama();
	hi.setCama(cen);
	//hi.setFechai(fechaFinal);
	hi.setFechafi(fechafi);
	hi.setNumingreso(num_ing);
	
	//hi.sethora_inicial(horaIni);
	hi.setusuarioIni(usuarioIni);
	hi.sethora_final(horaFinal);
	hi.setusuarioFin(usuarioFin);
	
	try{
		/*String fechainicio,iniin,medin,finin=null;
		iniin=hi.getFechai().substring(0,2);
		medin=hi.getFechai().substring(3,5);
		finin=hi.getFechai().substring(6,10);
		fechainicio=finin+"/"+medin+"/"+iniin;*/
		
		PreparedStatement ps = null;
	    Conexion con=new Conexion();
	 
	    ps=con.conn.prepareStatement("insert into adm_historico_cama(cen_numero_cama_fk,fecha_inicial,fecha_final,adm_numero_ingreso_fk,hora_inicial,hora_final,usuarioIni,usuarioFin) values(?,?,?,?,?,?,?,?)");				
		ps.setString(1,hi.getCama());
		ps.setDate(2,fechaFinal);				
		ps.setString(3,hi.getFechafi());
		ps.setString(4,hi.getNumingreso());
		ps.setTime(5, horaIni);
		ps.setString(6, horaFinal);
		ps.setString(7, hi.getusuarioIni());
		ps.setString(8, hi.getusuarioFin());
		
		ps.executeUpdate();
		
		ps.close();
		con.cerrar();
	
	}catch(Exception ex){
		ex.getMessage();
	}
}

public java.sql.ResultSet obtenernumeroingre(String fecha,String hora,String cen){
    java.sql.ResultSet rs=null;
    Statement st = null;
    
	/*String fechainicio,iniin,medin,finin=null;
	iniin=fecha.substring(0,2);
	medin=fecha.substring(3,5);
	finin=fecha.substring(6,10);
	fechainicio=finin+"/"+medin+"/"+iniin;*/
	
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery("select adm_numero_ingreso from adm_admisiones where fecha_registro='"+fecha+"' and hora_registro='"+hora+"' and cen_numero_cama_fk="+cen+"");
    	//System.out.println("select adm_numero_ingreso from adm_admisiones where fecha_registro='"+fechainicio+"' and hora_registro='"+hora+"' and cen_numero_cama_fk="+cen+"");
    }
    catch(Exception ex){
    	ex.getMessage();
    
    }	
    return rs;
}  


/**
 * ingreso de datos en la tabla historico_cama
 */

		public void insertar(String cen, String fechain, String fechafi, String num_ing,String horaIni,String usuarioIni,String horaFinal,String usuarioFin){
			//, , , ,String horaIni,String usuario,String horaFinal
			HistoricoCama hi = new HistoricoCama();
			hi.setCama(cen);
			hi.setFechai(fechain);
			hi.setFechafi(fechafi);
			hi.setNumingreso(num_ing);
			
			hi.sethora_inicial(horaIni);
			hi.setusuarioIni(usuarioIni);
			hi.sethora_final(horaFinal);
			hi.setusuarioFin(usuarioFin);
			
			try{
				/*String fechainicio,iniin,medin,finin=null;
				iniin=hi.getFechai().substring(0,2);
				medin=hi.getFechai().substring(3,5);
				finin=hi.getFechai().substring(6,10);
				fechainicio=finin+"/"+medin+"/"+iniin;*/
				
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			 
			    ps=con.conn.prepareStatement("insert into adm_historico_cama(cen_numero_cama_fk,fecha_inicial,fecha_final,adm_numero_ingreso_fk,hora_inicial,hora_final,usuarioIni,usuarioFin) values(?,?,?,?,?,?,?,?)");				
				ps.setString(1,hi.getCama());
				ps.setString(2,fechain);				
				ps.setString(3,hi.getFechafi());
				ps.setString(4,hi.getNumingreso());
				ps.setString(5, hi.gethora_inicial());
				ps.setString(6, hi.gethora_final());
				ps.setString(7, hi.getusuarioIni());
				ps.setString(8, hi.getusuarioFin());
				
				ps.executeUpdate();
				
				ps.close();
				con.cerrar();
			
			}catch(Exception ex){
				ex.getMessage();
			}
		}
		
		public void insertarTrasUrg(String cen, String fechain, String fechafi, String num_ing,String horaIni,String usuarioIni,String horaFinal,String usuarioFin){
			//, , , ,String horaIni,String usuario,String horaFinal
			HistoricoCama hi = new HistoricoCama();
			hi.setCama(cen);
			hi.setFechai(fechain);
			hi.setFechafi(fechafi);
			hi.setNumingreso(num_ing);
			
			hi.sethora_inicial(horaIni);
			hi.setusuarioIni(usuarioIni);
			hi.sethora_final(horaFinal);
			hi.setusuarioFin(usuarioFin);
			
			try{
				
				
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			 
			    ps=con.conn.prepareStatement("insert into adm_historico_cama(cen_numero_cama_fk,fecha_inicial,fecha_final,adm_numero_ingreso_fk,hora_inicial,hora_final,usuarioIni,usuarioFin) values(?,?,?,?,?,?,?,?)");				
				ps.setString(1,hi.getCama());
				ps.setString(2,fechain);				
				ps.setString(3,hi.getFechafi());
				ps.setString(4,hi.getNumingreso());
				ps.setString(5, hi.gethora_inicial());
				ps.setString(6, hi.gethora_final());
				ps.setString(7, hi.getusuarioIni());
				ps.setString(8, hi.getusuarioFin());
				
				ps.executeUpdate();
				
				ps.close();
				con.cerrar();
			
			}catch(Exception ex){
				ex.getMessage();
			}
			
			
		}

	
}
