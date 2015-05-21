
package seguridad_logica;

import java.sql.Statement;

import adm_logica.Conexion;



public class MetodoModulos {
	

	 public java.sql.ResultSet SQLModulo(){
       java.sql.ResultSet rs=null;
       Statement st = null;
       try{
       	Conexion con=new Conexion();
       	st = con.conn.createStatement();
       	rs=st.executeQuery("select nombre,men_codigo from seg_menu");
      
       }
       catch(Exception ex){
       	
       }
       return rs;
   }
	 
	 
	 public java.sql.ResultSet permisos(String x){
	       java.sql.ResultSet rs=null;
	       Statement st = null;	       
	       try{
	       	Conexion con=new Conexion();
	       	st = con.conn.createStatement();
	       	rs=st.executeQuery("select distinct sod.nombre,sod.opd_codigo,su.usu_codigo,som.nombre as opcion_menu,sm.nombre as menu from seg_opciones_disponibles sod,seg_opciones_autorizadas soa,seg_datos_personales sdp,seg_usuario su,seg_menu sm,seg_opciones_menu som where sod.opd_codigo=soa.opd_codigo_fk and sdp.cedula='"+x+"' and sdp.estado=0 and sdp.dat_codigo=su.dat_codigo_fk and su.usu_codigo=soa.usu_codigo_fk and sm.men_codigo=som.men_codigo_fk and som.opm_codigo=sod.opm_codigo_fk ");
	      
	       }
	       catch(Exception ex){
	       	
	       }
	       return rs;
	   }
	 
	 public java.sql.ResultSet area(String nomarea){
	       java.sql.ResultSet rs=null;
	       Statement st = null;
	       try{
	       	Conexion con=new Conexion();
	       	st = con.conn.createStatement();
	       	rs=st.executeQuery("select seg_opciones_menu.nombre,seg_opciones_menu.opm_codigo from seg_opciones_menu,seg_menu where seg_opciones_menu.men_codigo_fk=seg_menu.men_codigo and seg_menu.nombre='"+nomarea+"'");
	      
	       }
	       catch(Exception ex){
	       	
	       }
	       return rs;
	   }
	 public java.sql.ResultSet opcion_disponible(String nomarea,String area){
	       java.sql.ResultSet rs=null;
	       Statement st = null;
	       try{
	       	Conexion con=new Conexion();
	       	st = con.conn.createStatement();
	       	rs=st.executeQuery("select seg_opciones_disponibles.nombre,seg_opciones_disponibles.opd_codigo from seg_opciones_disponibles,seg_opciones_menu,seg_menu where seg_menu.men_codigo=seg_opciones_menu.men_codigo_fk and  seg_opciones_disponibles.opm_codigo_fk=seg_opciones_menu.opm_codigo and seg_opciones_menu.nombre='"+nomarea+"' and seg_menu.nombre='"+area+"'");
	      
	       }
	       catch(Exception ex){
	       	
	       }
	       return rs;
	   }

}

