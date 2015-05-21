/**
 * NOMBRE DE LA CLASE: MetodoFinalizarAdmision
 * AUTOR: Oscar Rolong Schweiger
 * DESCRIPCION: En este metodo se encuentra lo necesario para las actualizaciones
 * 				al momento de hacer efectiva la finalizacion de una admision.
 * 				
 */

package adm_logica;

import java.sql.Date;
import java.sql.PreparedStatement;

import adm_bean.HistoricoCama;

public class MetodoFinalizarAdmision {
	
	public void ActualizarEncabezadoFechaEgreso(Date Fecha, String CodAdm){		
	    
	     try{
	    	 PreparedStatement st = null;
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("update fact_enc_factura set fecha_egreso='"+Fecha+"' where cod_admision="+CodAdm+"");
	     	st.executeUpdate();
	     	st.close();
	     	con.cerrar();	     	
	     }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoFinalizarAdmision>>ActualizarEncabezadoFechaEgreso "+ex);
	     	ex.getMessage();	     
	     }		    
	 }
	
	
	public void ActualizarAdmision(String NumeroAdmision){
		/**
		 * en este metodo se da por terminada la admision
		 * cambiandole el estado de activo(0) a inactivo (1).
		 * se toma como dato de entrada el numero de la admision.
		 */
	
       PreparedStatement st = null;
        try{
        	Conexion con=new Conexion();
        	st= con.conn.prepareStatement("update adm_admisiones set estado=1 where adm_numero_ingreso='"+NumeroAdmision+"'");
        	st.executeUpdate();
        	st.close();
        	con.cerrar();
        	
        }
        catch(Exception ex){
        	ex.getMessage();
        
        }	
       
    }
	

	public void ActualizarCama(String codcam){
		/**
		 * en este metodo se libera la cama que tenia la admision terminada
		 * para que posteriormente pueda ser usada en otra admision.
		 * se cambia el estado de ocupada(3) a disponible(1).
		 * se toma como dato de entrada el codigo de la cama.
		 */
	
       PreparedStatement st = null;
        try{
        	Conexion con=new Conexion();
        	st= con.conn.prepareStatement("Update adm_censo_cama set est_codigo_estado_fk=1 where cen_numero_cama ='"+codcam+"'");
        	st.executeUpdate();
        	st.close();
        	con.cerrar();
        	
        }
        catch(Exception ex){
        	ex.getMessage();
        
        }	
       
    }
	
	
	
	public void ActualizarHistoria(String codcam,String NumeroAdmision,String FechaFinal,String horaFinal,String usuarioFin){
		/**
		 * en este metodo se actualiza el seguimiento que tiene cada cama a nivel de movimientos
		 * se introduce la fecha final del ultimo moviento de la cama
		 * se toman como datos de entrada el codigo de la cama,el numero de la admision.
		 */
		
		//HistoricoCama hc=new HistoricoCama();		
		String fechafinal,dia,mes,anio=null;
		dia=FechaFinal.substring(9,10);		
		mes=FechaFinal.substring(6,8);
		anio=FechaFinal.substring(0,5);
		fechafinal=anio+"/"+mes+"/"+dia;	
		
		
		PreparedStatement st = null;
		        try{
		        	Conexion con=new Conexion();		        	
		        	st= con.conn.prepareStatement("Update adm_historico_cama set fecha_final='"+FechaFinal+"',hora_final='"+horaFinal+"',usuarioFin='"+usuarioFin+"' where adm_numero_ingreso_fk='"+NumeroAdmision+"'and cen_numero_cama_fk='"+codcam+"'");
		        	st.executeUpdate();
		        	st.close();
		        	con.cerrar();
		        	
		        }
		        catch(Exception ex){
		        	ex.getMessage();
		        
		        }	
		       
		    }
	
	
	
}
