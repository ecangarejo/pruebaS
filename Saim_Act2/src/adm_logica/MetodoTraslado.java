/**
 * NOMBRE DE LA CLASE: MetodoTraslado
 * AUTOR: Oscar Rolong Schweiger
 * DESCRIPCION: En este metodo se encuentra lo necesario para consultar y actualizar
 * 				para hacer traslados entre dependencias de la clinica.
 */

package adm_logica;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;

import adm_bean.HistoricoCama;

public class MetodoTraslado {
	
	public MetodoTraslado(){}
	
	public void ActualizarOcupadaCodigo(String CodCamaNueva){
		/***/
	
		
	       PreparedStatement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st= con.conn.prepareStatement("Update adm_censo_cama set est_codigo_estado_fk=3 where cen_numero_cama ="+CodCamaNueva+"");
	        	st.executeUpdate();
	        	st.close();
	        	con.cerrar();
	        	
	        }
	        catch(Exception ex){
	        	System.out.print("Error en MetodoTraslado>>ActualizarOcupadaCodigo "+ex);
	        	ex.getMessage();
	        
	        }	
	       
	    }
	
	public void ActualizarLibreCodigo(String CodCamaVieja){
		/***/
		PreparedStatement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st= con.conn.prepareStatement("Update adm_censo_cama set est_codigo_estado_fk=1 where cen_numero_cama ="+CodCamaVieja+" ");
	        	st.executeUpdate();
	        	st.close();
	        	
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.print("Error en MetodoTraslado>>ActualizarLibreCodigo "+ex);
	        
	        }	
	       
	    }
	
	public void ActualizarEncabezado(String CodAdm){
		/***/
		PreparedStatement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st= con.conn.prepareStatement("UPDATE fact_enc_factura SET tipo=2 WHERE cod_admision="+CodAdm+" ");
	        	st.executeUpdate();
	        	st.close();
	        	
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.print("Error en MetodoTraslado>>ActualizarEncabezado "+ex);
	        
	        }	
	       
	    }
	
	public void ActualizarEstadoFactu(String CodAdm){
		/***/
		PreparedStatement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st= con.conn.prepareStatement("Update adm_admisiones set aurg=0,ahosp=1 where adm_numero_ingreso ="+CodAdm+" ");
	        	st.executeUpdate();
	        	st.close();
	        	
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.print("Error en MetodoTraslado>>ActualizarEstadoFactu "+ex);
	        
	        }	
	       
	    }
	
	public void ActualizarMedicoTratante(String CodMedTrat){
		PreparedStatement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st= con.conn.prepareStatement("UPDATE adm_medico_tratante SET estado=1 WHERE codigo ="+CodMedTrat+" ");
	        	st.executeUpdate();
	        	st.close();	        	
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.print("Error en MetodoTraslado>>ActualizarMedicoTratante "+ex);
	        
	        }	
	       
	    }
	
	
	public void IngresarMedicoTratante(String cod_medico_fk, String medico_texto, String codAdm_fk, Date fecha_registro,Time hora_registro,String usuario_ingreso){
		try{
			PreparedStatement ps = null;
		    Conexion con=new Conexion();
		    ps=con.conn.prepareStatement("INSERT INTO adm_medico_tratante (cod_medico_fk,medico_texto,codAdm_fk,fecha_registro,hora_registro,usuario_ingreso)VALUES(?,?,?,?,?,?)");				
			ps.setString(1,cod_medico_fk);
			ps.setString(2,medico_texto);				
			ps.setString(3,codAdm_fk);
			ps.setDate(4,fecha_registro);
			ps.setTime(5,hora_registro);
			ps.setString(6,usuario_ingreso);
			ps.executeUpdate();			
			ps.close();
			con.cerrar();
		
		}catch(Exception ex){
			System.out.print("Error en MetodoTraslado>>IngresarMedicoTratante "+ex);
			ex.getMessage();
		}		
	}
	
	public void insertarTrasUrgHosp(String Fecha, String Hora, String CodAdm, String usuario,String CodCamaIni,String CodCamaFin,String Observacion,String NumAutoriza,String AutorizadoPor){
			
		try{
			PreparedStatement ps = null;
		    Conexion con=new Conexion();
		    ps=con.conn.prepareStatement("insert into adm_trasladourg_hosp(Fecha,Hora,CodAdm,usuario,CodCamaIni,CodCamaFin,Observacion,NumAutoriza,AutorizadoPor) values(?,?,?,?,?,?,?,?,?)");				
			ps.setString(1,Fecha);
			ps.setString(2,Hora);				
			ps.setString(3,CodAdm);
			ps.setString(4,usuario);
			ps.setString(5,CodCamaIni);
			ps.setString(6,CodCamaFin);
			ps.setString(7,Observacion);
			ps.setString(8, NumAutoriza);
			ps.setString(9, AutorizadoPor);
			ps.executeUpdate();			
			ps.close();
			con.cerrar();
		
		}catch(Exception ex){
			System.out.print("Error en MetodoTraslado>>insertarTrasUrgHosp "+ex);
			ex.getMessage();
		}		
	}
	
	
	public java.sql.ResultSet BuscarMedicoTratante(String CodAdm){
		/**
		 * */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM adm_medico_tratante WHERE codAdm_fk="+CodAdm+" AND estado=0");
        }
        catch(Exception ex){ 
        	System.out.print("Error en MetodoTraslado>>BuscarMedicoTratante "+ex);
        }
        return rs;
    }
	
	public java.sql.ResultSet ValidarSalidaUrgencia(String CodAdm){
		/**
		 * */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select * from hic_destinopaciente where codAdm="+CodAdm+" and DestinoPaciente='HOSPITALIZACION'");
        }
        catch(Exception ex){ 
        	System.out.print("Error en MetodoTraslado>>ValidarSalidaUrgencia "+ex);
        }
        return rs;
    }
	
	public java.sql.ResultSet ListarEspecialistas(){
		/**
		 * */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT su.usu_codigo,CONCAT(sdt.nombre,' ',sdt.apellido,'-',sdt.ocupacion) AS medico FROM seg_usuario su, seg_datos_personales sdt WHERE su.dat_codigo_fk=sdt.dat_codigo AND sdt.profesion='Especialista' ORDER BY  medico");
        }
        catch(Exception ex){ 
        	System.out.print("Error en MetodoTraslado>>ListarEspecialistas "+ex);
        }
        return rs;
    }
	
	public java.sql.ResultSet BuscarTrasladoUrgHosp(String TipoDocumento,String NumeroDocumento){
		/**
		 * */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo from adm_trasladourg_hosp ath,adm_admisiones aad,adm_paciente apac where apac.pac_codigo_paciente=aad.pac_codigo_paciente_fk and aad.adm_numero_ingreso=ath.CodAdm and apac.tipo_documento='"+TipoDocumento+"' and apac.numero_documento='"+NumeroDocumento+"' and aad.estado=0 ");
        }
        catch(Exception ex){ 
        	System.out.print("Error en MetodoTraslado>>BuscarTrasladoUrgHosp "+ex);
        }
        return rs;
    }
	
	public java.sql.ResultSet BuscarPaciente(String TipoDocumento,String NumeroDocumento){
		/**
		 * */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select CONCAT(apac.nombre,' ',apac.primer_apellido,' ',apac.segundo_apellido)as Nombre, CONCAT(acc.pabellon,'>>',acc.ubicacion_cama,'>>',acc.codigocama) as Ubicacion,aad.adm_numero_ingreso,acc.cen_numero_cama,apac.pac_codigo_paciente from adm_admisiones aad,adm_paciente apac,adm_censo_cama acc where apac.tipo_documento='"+TipoDocumento+"' and apac.numero_documento='"+NumeroDocumento+"' and aad.pac_codigo_paciente_fk=apac.pac_codigo_paciente and aad.estado=0 and acc.cen_numero_cama=aad.cen_numero_cama_fk ");
        }
        catch(Exception ex){ 
        	System.out.print("Error en MetodoTraslado>>BuscarPaciente "+ex);
        }
        return rs;
    }
	
	public java.sql.ResultSet obtenerSubAreaCodigo(String CodArea){
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select adm_subarea.nombre,adm_subarea.codigo from adm_subarea,adm_area where adm_area.codigo=adm_subarea.codigoarea and adm_area.codigo='"+CodArea+"'");
        	
        }
        catch(Exception ex){
        	System.out.print("Error en MetodoTraslado>>obtenerSubAreaCodigo "+ex);
        }
        return rs;
    }
	
	public java.sql.ResultSet obtenerCamaSubarea(String CodSubarea){
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select acc.cen_numero_cama,acc.codigocama from adm_censo_cama acc,adm_subarea asub where asub.codigo=acc.codsubarea and acc.codsubarea="+CodSubarea+" and acc.est_codigo_estado_fk=1");
        }
        catch(Exception ex){
        	
        }
        return rs;
    }

	public java.sql.ResultSet obtenerArea(){
		/**
		 * en este metodo se obtiene el nombre del area de la clinca
		 * si es urgencia,hospitalizacion,uci's o cualquier otra.
		 * */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select nombre,codigo  from adm_area");
        }
        catch(Exception ex){ 
        }
        return rs;
    }	
	
	public java.sql.ResultSet obtenerArea1(){
		/**
		 * en este metodo se obtiene el nombre del area de la clinca
		 * si es urgencia,hospitalizacion,uci's o cualquier otra.
		 * */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select nombre,codigo  from adm_area where codigo>1");
        }
        catch(Exception ex){ 
        }
        return rs;
    }
	
	public java.sql.ResultSet obtenerCodCama(String camaCod){
		/**
		 * en este metodo se obtiene el codigo de la cama seleccionada.
		 * se toma como dato de entrada el nombre de la cama.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select cen_numero_cama from adm_censo_cama where codigocama='"+camaCod+"'");
        	
        }
        
        
        catch(Exception ex){
        	
        }
        return rs;
    }
	
	public java.sql.ResultSet obtenerSubArea(String a){
		/**
		 * en este metodo se obtiene el nombre de la subarea
		 * del area previamente seleccionada.
		 * se toma como dato de entrada el nombre del area.  
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select adm_subarea.nombre  from adm_subarea,adm_area where adm_area.codigo=adm_subarea.codigoarea and adm_area.nombre='"+a+"'");
        	
        }
        catch(Exception ex){
        	
        }
        return rs;
    }
	public java.sql.ResultSet obtenerCama(String cam){
		/**
		 * en este metodo se toman todas las camas que esten ocupadas estado(3)para
		 * su posterior traslado.
		 * se toma como dato de entrada el subarea o ubicacion de la cama.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigocama,cen_numero_cama codigocama from adm_censo_cama where est_codigo_estado_fk=3 and adm_censo_cama.ubicacion_cama='"+cam+"'");
        	rs=st.executeQuery("select codigocama,cen_numero_cama codigocama from adm_censo_cama where est_codigo_estado_fk=3 and adm_censo_cama.ubicacion_cama='"+cam+"'");
        	
        }
        
        
        catch(Exception ex){
        	System.out.println("Error en MetodoTraslado>>obtenerCama "+ex);
        }
        return rs;
    }
	
	
	public void ActualizarAdmision(String codigoVieja,String codigoNueva,String codigoAd){
		/**
		 *en este metodo se actualiza la admision en cuanto a cualquier traslado
		 *de cama que se haga a esta, se actualiza el campo de codigo de la cama
		 *poniendo en cambio el codigo de la cama nueva a utilizar en esta admision.
		 *se toman como datos de entrada el codigo de la cama anterior
		 *el codigo de la cama a ocupar y el codigo de la admision.
		 */
		System.out.println("Update adm_admisiones set cen_numero_cama_fk="+codigoNueva+",aurg='0',ahosp='1'  where  adm_numero_ingreso="+codigoAd+" and cen_numero_cama_fk="+codigoVieja+" and estado=0");
	       PreparedStatement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st= con.conn.prepareStatement("Update adm_admisiones set cen_numero_cama_fk="+codigoNueva+",aurg='0',ahosp='1'  where  adm_numero_ingreso="+codigoAd+" and cen_numero_cama_fk="+codigoVieja+" and estado=0");
	        	
	        	st.executeUpdate();	   
	        	st.close();
	        	con.cerrar();
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        
	        }	
	       
	    }
	
	
	public void ActualizarOcupada(String cama){
		/**
		 * en este metodo se actualiza la cama que estaba libre
		 * cambiando su estado de disponible (1) a ocupada (3).
		 * se toma como dato de entrada el nombre de la cama.
		 */	
	       PreparedStatement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st= con.conn.prepareStatement("Update adm_censo_cama set est_codigo_estado_fk=3 where cen_numero_cama ='"+cama+"'");
	        	System.out.println("Update adm_censo_cama set est_codigo_estado_fk=3 where cen_numero_cama ='"+cama+"'");
	        	st.executeUpdate();
	        	st.close();
	        	con.cerrar();
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        }	
	    }	
	
	public ResultSet BuscarCamaOcupada(String camaOc){
		
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select est_codigo_estado_fk from adm_censo_cama where cen_numero_cama='"+camaOc+"' ");
        	System.out.println("select est_codigo_estado_fk from adm_censo_cama where cen_numero_cama='"+camaOc+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoTraslado>>BuscarCamaOcupada "+ex);
        }	
        return rs;
		
	    }
	
	public void ActualizarLibre(String camaAc){
		/**
  		* en este metodo se actualiza la cama que estaba ocupada
 		* cambiando su estado de ocupada (3) a disponible (1).
 		* se toma como dato de entrada el nombre de la cama. 
 		*/
		PreparedStatement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st= con.conn.prepareStatement("Update adm_censo_cama set est_codigo_estado_fk=1 where cen_numero_cama ="+camaAc+"");
	        	System.out.println("Update adm_censo_cama set est_codigo_estado_fk=1 where cen_numero_cama ='"+camaAc+"'");
	        	st.executeUpdate();
	        	st.close();
	        	
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        
	        }	
	       
	    }
	
	
	public java.sql.ResultSet obtenerCamaActualizar(String cam){
		/**
		 * en este metodo se toman todas las camas que esten disponibles estado(1)para
		 * su posterior traslado.
		 * se toma como dato de entrada el subarea o ubicacion de la cama.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT codigocama,cen_numero_cama FROM adm_censo_cama WHERE est_codigo_estado_fk =1 AND ubicacion_cama = '"+cam+"' ");
        	System.out.println("SELECT codigocama,cen_numero_cama FROM adm_censo_cama WHERE est_codigo_estado_fk =1 AND ubicacion_cama = '"+cam+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MedotoTraslado>>obtenerCamaActualizar "+ex);
        }
        return rs;
    }
	
	public java.sql.ResultSet obtenerPaciente(String cama){
		/**
		 * en este metodo se obtiene el numero de documento,nombre, primer apellido y 
		 * codigo de la cama que ocupa el paciente.
		 * se toma como dato de entrada el nombre de la cama.  
		 */
		System.out.println("select DISTINCT  numero_documento,nombre,primer_apellido,cen_numero_cama from adm_admisiones ad,adm_paciente pa,adm_censo_cama cc,adm_censo_estado ce where pa.pac_codigo_paciente=ad.pac_codigo_paciente_fk and ad.cen_numero_cama_fk=cc.cen_numero_cama and cc.cen_numero_cama="+cama+" and ad.estado=0");
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();        	
        	rs=st.executeQuery("select DISTINCT  pa.numero_documento,pa.nombre,pa.primer_apellido,cc.cen_numero_cama,ad.adm_numero_ingreso from adm_admisiones ad,adm_paciente pa,adm_censo_cama cc,adm_censo_estado ce where pa.pac_codigo_paciente=ad.pac_codigo_paciente_fk and ad.cen_numero_cama_fk=cc.cen_numero_cama and cc.cen_numero_cama="+cama+" and ad.estado=0");
        	System.out.println("select DISTINCT  pa.numero_documento,pa.nombre,pa.primer_apellido,cc.cen_numero_cama,ad.adm_numero_ingreso from adm_admisiones ad,adm_paciente pa,adm_censo_cama cc,adm_censo_estado ce where pa.pac_codigo_paciente=ad.pac_codigo_paciente_fk and ad.cen_numero_cama_fk=cc.cen_numero_cama and cc.cen_numero_cama="+cama+" and ad.estado=0");
        }
        
        
        catch(Exception ex){
        	System.out.println("Error en MetodoTraslado>>obtenerPaciente "+ex);
        }
        return rs;
    }

	public java.sql.ResultSet obtenerCodigoAdmision(String codigoAd,String CamaNueva){
		/**
		 * en este metodo se obtiene el codigo de la admision y el codigo de la cama
		 * a trasladar.
		 * se toma como dato de entrada la cedula del paciente y el nombre de la cama
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();        	
        	rs=st.executeQuery("select DISTINCT adm_numero_ingreso,cen_numero_cama from adm_admisiones ad,adm_paciente pa,adm_censo_cama cc where ad.pac_codigo_paciente_fk=pa.pac_codigo_paciente and pa.numero_documento='"+codigoAd+"'and cc.codigocama='"+CamaNueva+"' and ad.estado=0");
        }        
        catch(Exception ex){        	
        }
        return rs;
    }
	
	
}
