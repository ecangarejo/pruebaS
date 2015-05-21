package cig_metodo;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;

import adm_logica.Conexion;

public class MetodoProgramacion {
	
	public java.sql.ResultSet  BuscarArea()  {
		/**
		 */
		  java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select codigo, descripcion from cig_areas ");
	          }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoProgramacion>>BuscarArea  "+ex);
	        }	
	        return rs;
	}
	
	public java.sql.ResultSet  BuscarEstadoProgramacion(String tp, String fechap)  {
		/**
		 */
		  java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select estado, codigo from cig_programacion where fecha_programacion='"+fechap+"' and cod_tipofk='"+tp+"' ");
	        	System.out.println("select estado, codigo from cig_programacion where fecha_programacion='"+fechap+"' and cod_tipofk='"+tp+"' ");
	          }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoProgramacion>>BuscarEstadoProgramacion "+ex);
	        }	
	        return rs;
	}
	
	
	public java.sql.ResultSet  BuscarTipoDoc()  {
		/**
		 */
		  java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select descripcion, sigla from adm_tipodocumento ");
	        	
	          }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoProgramacion>>BuscarTipoDoc "+ex);
	        }	
	        return rs;
	}
	
	public java.sql.ResultSet  BuscarDetProg(String codigo)  {
		/**
		 */
		  java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT cd.*, ca.descripcion FROM cig_detalleprogramacion cd, cig_areas ca WHERE cd.cod_programacionfk='"+codigo+"' AND (cd.estado=0 OR cd.estado=2) AND ca.codigo=cd.cod_areafk ORDER BY cd.cod_areafk, cd.rango_hora, hora_programacion  ");
	          }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoProgramacion>> BuscarDetProg "+ex);
	        }	
	        return rs;
	}
	
	
	public java.sql.ResultSet  BuscarDetProgOnco(String codigo)  {
		/**
		 */
		  java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT cd.* FROM cig_detalleprogramacion cd WHERE cd.cod_programacionfk='"+codigo+"' AND (cd.estado=0 OR cd.estado=2) ORDER BY cd.rango_hora, cd.hora_programacion  ");
	          }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoProgramacion>> BuscarDetProgOnco "+ex);
	        }	
	        return rs;
	}
	
	public java.sql.ResultSet  BuscarEps()  {
		/**
		 */
		  java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT aent.nombre_entidad,aent.ent_nit FROM adm_entidad aent,adm_convenio acv,fact_convenios fcv WHERE fcv.cod_entidad=aent.ent_nit AND fcv.estado=0 AND acv.ent_nit_contratante_fk=aent.ent_nit ORDER BY aent.nombre_entidad ");
	          }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoProgramacion>>BuscarEps "+ex);
	        }	
	        return rs;
	}
	
	
	
	public java.sql.ResultSet  BuscarServ()  {
		/**
		 */
		  java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT asub.nombre, CONCAT(aa.nombre,'-',asub.nombre) AS Serv FROM adm_subarea asub, adm_area aa WHERE asub.codigoarea=aa.codigo ");
	          }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoProgramacion>>BuscarServ "+ex);
	        }	
	        return rs;
	}
	
	
	public java.sql.ResultSet  BuscarHab(String codserv)  {
		/**
		 */
		  java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT codigocama FROM adm_censo_cama WHERE ubicacion_cama='"+codserv+"' ");
	        	System.out.println(" SELECT codigocama FROM adm_censo_cama WHERE ubicacion_cama='"+codserv+"' ");
	          }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoProgramacion>>BuscarHab "+ex);
	        }	
	        return rs;
	}
	
	
	
	public java.sql.ResultSet  BuscarCiru()  {
		/**
		 */
		  java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT CONCAT(sdp.nombre,' ',sdp.apellido) AS nombreciru "+
	        						"FROM seg_datos_personales sdp "+
	        						"WHERE  sdp.profesion='Especialista' AND sdp.Ciru_anes=1 and estado=0 ORDER BY nombreciru ");
	          }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoProgramacion>>BuscarCiru "+ex);
	        }	
	        return rs;
	}	
	
	public java.sql.ResultSet  BuscarCiru(String tp)  {
		/**
		 */
		  java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	if(tp.equals("4")){
	        		
	        		rs=st.executeQuery("SELECT CONCAT(sdp.nombre,' ',sdp.apellido) AS nombreciru FROM seg_datos_personales sdp "+
    						"WHERE  sdp.Hemo=1 and estado=0 ORDER BY nombreciru ");
	        	}else{
	        		if(tp.equals("5")){
	        			rs=st.executeQuery("SELECT CONCAT(sdp.nombre,' ',sdp.apellido) AS nombreciru FROM seg_datos_personales sdp "+
						"WHERE  sdp.Endos=1 and estado=0 ORDER BY nombreciru ");
	        		}
	        	}
	        	
	          }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoProgramacion>>BuscarCiru "+ex);
	        }	
	        return rs;
	}
	
	public java.sql.ResultSet  BuscarAnes()  {
		/**
		 */
		  java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT CONCAT(sdp.nombre,' ',sdp.apellido) AS nombreanes "+
	        						"FROM seg_datos_personales sdp "+
	        						"WHERE  sdp.profesion='Especialista' AND sdp.Ciru_anes=2 and estado=0 ORDER BY  nombreanes ");
	          }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoProgramacion>>BuscarAnes "+ex);
	        }	
	        return rs;
	}
	
	public java.sql.ResultSet BuscarProg(String Fechai,String Fechaf,String tp)  {
		/**
		 */
		  java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT cp.codigo, cp.fecha_creacion, cp.hora_creacion,cp.fecha_programacion,sdp.nombre AS nomuser, sdp.apellido AS apellidousuar "+
	        					"FROM cig_programacion cp, cig_detalleprogramacion cd, cig_areas ca, seg_usuario su, seg_datos_personales sdp "+
	        					"WHERE ca.codigo=cd.cod_areafk  AND (cd.estado=0 OR cd.estado=2) "+
	        					"AND su.usu_codigo=cp.codusuario AND su.dat_codigo_fk=sdp.dat_codigo AND cp.codigo=cd.cod_programacionfk AND cp.cod_tipofk='"+tp+"' AND cp.estado=1 AND fecha_programacion BETWEEN '"+Fechai+"' AND '"+Fechaf+"' "+
	        					" GROUP BY cp.codigo ORDER BY hora_programacion   ");
	        	System.out.println("SELECT cp.codigo, cp.fecha_creacion, cp.hora_creacion,cp.fecha_programacion,sdp.nombre AS nomuser, sdp.apellido AS apellidousuar "+
    					"FROM cig_programacion cp, cig_detalleprogramacion cd, cig_areas ca, seg_usuario su, seg_datos_personales sdp "+
    					"WHERE ca.codigo=cd.cod_areafk  AND (cd.estado=0 OR cd.estado=2) "+
    					"AND su.usu_codigo=cp.codusuario AND su.dat_codigo_fk=sdp.dat_codigo AND cp.codigo=cd.cod_programacionfk AND cp.cod_tipofk='"+tp+"' AND cp.estado=1 AND fecha_programacion BETWEEN '"+Fechai+"' AND '"+Fechaf+"' "+
    					" GROUP BY cp.codigo ORDER BY  hora_programacion   ");
	          }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoProgramacion>>BuscarProg "+ex);
	        }	
	        return rs;
	}
	
	
	public java.sql.ResultSet BuscarProgOnco(String Fechai,String Fechaf,String tp)  {
		/**
		 */
		  java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT cp.codigo, cp.fecha_creacion, cp.hora_creacion,cp.fecha_programacion,sdp.nombre AS nomuser, sdp.apellido AS apellidousuar "+
	        					"FROM cig_programacion cp, cig_detalleprogramacion cd,seg_usuario su, seg_datos_personales sdp "+
	        					"WHERE (cd.estado=0 OR cd.estado=2) "+
	        					"AND su.usu_codigo=cp.codusuario AND su.dat_codigo_fk=sdp.dat_codigo AND cp.codigo=cd.cod_programacionfk AND cp.cod_tipofk='"+tp+"' AND cp.estado=1 AND fecha_programacion BETWEEN '"+Fechai+"' AND '"+Fechaf+"' "+
	        					" GROUP BY cp.codigo ORDER BY hora_programacion   ");
	        	
	          }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoProgramacion>>BuscarProgOnco "+ex);
	        }	
	        return rs;
	}
	
	public void FinalP(Date Fecha,Time Hora,String codigou,String codigo){

		PreparedStatement st = null;
	    try{
	    	Conexion con=new Conexion();
	    	st= con.conn.prepareStatement(" UPDATE cig_programacion SET estado='1', fecha_creacion='"+Fecha+"', hora_creacion='"+Hora+"' , codusuario='"+codigou+"' WHERE codigo='"+codigo+"' ");
	    	st.executeUpdate();
	    	st.close();
	    	con.cerrar();
	    	
	    }
	    catch(Exception ex){
	    	System.out.println("ERROR EN MetodoProgramacion>>FinalP "+ex);
	    
	    }	
	}
	
	public void Elim(String coddet){

		PreparedStatement st = null;
	    try{
	    	Conexion con=new Conexion();
	    	st= con.conn.prepareStatement(" UPDATE cig_detalleprogramacion SET estado='1' WHERE codigo='"+coddet+"' ");
	    	st.executeUpdate();
	    	st.close();
	    	con.cerrar();
	    	
	    }
	    catch(Exception ex){
	    	System.out.println("ERROR EN MetodoProgramacion>>Elim "+ex);
	    
	    }	
	}
	
	public void GuardarP(String fechap,String tp){
		//System.out.print(cod_medico);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();													
			    ps=con.conn.prepareStatement("insert into cig_programacion(fecha_programacion,cod_tipofk)values(?,?) ");
			    ps.setString(1,fechap);
			    ps.setString(2,tp);
			  
			    
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoProgramacion>>GuardarP "+ex);
			}
		}
	
	public void GuardarDetalleProgramacion(String codigo,String qui,String horap,String npac,String tel,String proc,String eps,String ciru,String ayud,String anes,String serv,String hab,String usuario,String rhora){
		//System.out.print(cod_medico);
		try{
			System.out.println(rhora);
				PreparedStatement ps = null;
			    Conexion con=new Conexion();													
			    ps=con.conn.prepareStatement("insert into cig_detalleprogramacion(cod_programacionfk,cod_areafk,hora_programacion,Nombre_pac,telefono,procedimiento,eps,cirujano,ayudante,anestesiologo,servicio,habitacion,cod_usufk,rango_hora)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
			    ps.setString(1,codigo);
			    ps.setString(2,qui);
			    ps.setString(3,horap);
			    ps.setString(4,npac);
			    ps.setString(5,tel);
			    ps.setString(6,proc);  
			    ps.setString(7,eps);
			    ps.setString(8,ciru);
			    ps.setString(9,ayud);
			    ps.setString(10,anes);
			    ps.setString(11,serv);
			    ps.setString(12,hab);
			    ps.setString(13,usuario);	
			    ps.setString(14,rhora);	
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoProgramacion>>GuardarDetalleProgramacion "+ex);
			}
		}
	
	
	public void GuardarDetalleProgHemoEndo(String codigo,String qui,String horap,String npac,String tel,String proc,String eps,String ciru,String obs,String anes,String serv,String hab,String usuario,String rhora){
		//System.out.print(cod_medico);
		try{
			System.out.println(rhora);
				PreparedStatement ps = null;
			    Conexion con=new Conexion();													
			    ps=con.conn.prepareStatement("insert into cig_detalleprogramacion(cod_programacionfk,cod_areafk,hora_programacion,Nombre_pac,telefono,procedimiento,eps,cirujano,anestesiologo,servicio,habitacion,cod_usufk,observaciones,rango_hora)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
			    ps.setString(1,codigo);
			    ps.setString(2,qui);
			    ps.setString(3,horap);
			    ps.setString(4,npac);
			    ps.setString(5,tel);
			    ps.setString(6,proc);  
			    ps.setString(7,eps);
			    ps.setString(8,ciru);
			    ps.setString(9,anes);
			    ps.setString(10,serv);
			    ps.setString(11,hab);
			    ps.setString(12,usuario);	
			    ps.setString(13,obs);
			    ps.setString(14,rhora);	
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoProgramacion>>GuardarDetalleProgHemoEndo "+ex);
			}
		}
	
	
	
	public void GuardarDetalleProgOnco(String hora,String rhora,String npac,String codigo,String usuario,String hab,String serv,String tel,String eps,String intra,String sistema,String tdoc,String numdoc,String diag,String edad,String ocita,String se,String obs){
		//System.out.print(cod_medico);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();													
			    ps=con.conn.prepareStatement("insert into cig_detalleprogramacion(cod_programacionfk,hora_programacion,Nombre_pac,telefono,eps,servicio,habitacion,cod_usufk,tipo_documento,numero_documento,Edad,observaciones,diagnostico,oportunidadcita,intratecal,sistematica,rango_hora,se)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
			    System.out.println("serv"+serv+"hab"+hab);
			    ps.setString(1,codigo);
			    ps.setString(2,hora);
			    ps.setString(3,npac);
			    ps.setString(4,tel);
			    ps.setString(5,eps);  
			    ps.setString(6,serv);
			    ps.setString(7,hab);
			    ps.setString(8,usuario);
			    ps.setString(9,tdoc);
			    ps.setString(10,numdoc);
			    ps.setString(11,edad);
			    ps.setString(12,obs);	
			    ps.setString(13,diag);	
			    ps.setString(14,ocita);	
			    ps.setString(15,intra);
			    ps.setString(16,sistema);
			    ps.setString(17,rhora);
			    ps.setString(18,se);
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoProgramacion>>GuardarDetalleProgOnco "+ex);
			}
		}
	

	}
