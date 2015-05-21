/**
 * logica: MetodoCitasExamen se encuentra las consultas,inserciones y actualizaciones
 * para la creacion de los grupos actualizacion de datos demograficos.
*/

package img_logica;

import java.sql.PreparedStatement;
import java.sql.Statement;

import adm_logica.Conexion;

import img_bean.CrearGrupo;

public class MetodoCrearGrupo {
	
	 public void CrearGrupo(String nombre){
		    CrearGrupo cg = new CrearGrupo();
			 cg.setNombre(nombre);
			try{
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("insert into img_grupo(nombre)values(?)");
				    ps.setString(1, cg.getNombre());
				 	ps.executeUpdate();
					ps.close();
					con.cerrar();				
				}catch(Exception ex){
					System.out.println("Error en MetodoCrearGrupo>>CrearGrupo "+ex);
				}
			}
	 
	 
	 public java.sql.ResultSet ObtenerExamenesPacienteNuevo(String cedula,String TipoDoc){	    
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT ice.aprobacion,ice.codigo,CONCAT(ap.nombre,' ',ap.primer_apellido,' ',ap.segundo_apellido) AS NombrePaciente,CONCAT(ap.tipo_documento,'-',ap.numero_documento) AS Documento,iex.nombre,CONCAT(ice.fecha,'/',ice.hora) AS Fecha_Lectura,CONCAT(ice.fecha_cita,'/',ice.hora_cita) AS Fecha_Pedido FROM img_citas_espera ice,img_subexa iex,adm_paciente ap WHERE ice.codigoExa_fk=iex.codigo AND ap.pac_codigo_paciente=ice.codigoPac_fk AND ice.estado=1 AND ap.tipo_documento='"+TipoDoc+"' AND ap.numero_documento='"+cedula+"'");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoCrearGrupo>>ObtenerExamenesPacienteNuevo "+ex);
	        }	
	        return rs;
	    }
	 
	 
	 public java.sql.ResultSet ObtenerExamenesEco(String cedula,String TipoDoc){	    
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT  CONCAT(usu.nombre,' ',usu.apellido) AS NombreMedico, eco.idInformeEcocardio, " +
	        			"CONCAT(ap.nombre,' ',ap.primer_apellido,' ',ap.segundo_apellido) AS NombrePaciente," +
	        			"CONCAT(ap.tipo_documento,'-',ap.numero_documento) AS Documento," +
	        			"'ECOCARDIOGRAMA TRANS TORÁCICO' AS nombre_indicacion," +
	        			"CONCAT(eco.fecha_informe,'/',eco.hora_informe) AS Fecha_Lectura " +
	        			"FROM eco_encabezado_informe eco,adm_paciente ap, eco_indicaciones ind, seg_usuario seg, " +
	        			"seg_datos_personales usu WHERE  eco.codpaciente=ap.pac_codigo_paciente " +
	        			"AND eco.estado=1 AND ap.tipo_documento='"+TipoDoc+"' AND ap.numero_documento='"+cedula+"' " +
	        			"AND eco.id_indicacion_fk=ind.id_indicacion AND eco.codusuario =seg.usu_codigo " +
	        			"AND seg.dat_codigo_fk=usu.dat_codigo");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoCrearGrupo>>ObtenerExamenesEco "+ex);
	        }	
	        return rs;
	    }
	 
	 public java.sql.ResultSet ObtenerExamenesRmc(String cedula,String TipoDoc){	    
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT CONCAT(usu.nombre,' ',usu.apellido) AS NombreMedico, rmc.idInformeEcocardio, " +
	        			"CONCAT(ap.nombre,' ',ap.primer_apellido,' ',ap.segundo_apellido) AS NombrePaciente," +
	        			"CONCAT(ap.tipo_documento,'-',ap.numero_documento) AS Documento,ind.nombre_indicacion," +
	        			"CONCAT(rmc.fecha_informe,'/',rmc.hora_informe) AS Fecha_Lectura  " +
	        			"FROM rmc_encabezado_informe rmc,adm_paciente ap, rmc_indicaciones ind, seg_usuario seg, " +
	        			"seg_datos_personales usu WHERE  rmc.codpaciente=ap.pac_codigo_paciente " +
	        			"AND rmc.estado=1 AND ap.tipo_documento='"+TipoDoc+"' AND ap.numero_documento='"+cedula+"' " +
	        					"AND rmc.id_indicacion_fk=ind.id_indicacion AND rmc.codusuario =seg.usu_codigo " +
	        					"AND seg.dat_codigo_fk=usu.dat_codigo");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoCrearGrupo>>ObtenerExamenesRmc "+ex);
	        }	
	        return rs;
	    }
	 
	 
	 public java.sql.ResultSet buscarInformesCateterismosRealizados(String NumeroDocumento,String TipoDocumento) {
			java.sql.ResultSet rs = null;
			Statement st = null;
			//System.out.println("CAT "+TipoDocumento+" "+NumeroDocumento);
			try {
				Conexion con = new Conexion();
				st = con.conn.createStatement();
				rs = st.executeQuery(
						"SELECT inf.id_informe_diagnostico_hemodinamia, CONCAT(pac.nombre,' ',pac.primer_apellido,' ',pac.segundo_apellido) AS nombrepac, est.nombre_estudio_realizado, CONCAT(inf.fecha_diagnostico,'/',inf.hora_diagnostico) AS fecha, inf.id_paciente, CONCAT(usu.nombre,' ',usu.apellido) AS nombreusu FROM adm_paciente pac, cat_informe_diagnostico_hemodinamia inf, cat_estudio_realizado_hemodinamia est, seg_usuario seg, seg_datos_personales usu WHERE pac.tipo_documento ='"+TipoDocumento+"' AND pac.numero_documento='"+NumeroDocumento+"' AND pac.pac_codigo_paciente = inf.id_paciente AND  est.id_estudio_realizado_hemodinamia=inf.id_estudio_realizado_hemodinamia_fk AND seg.usu_codigo = inf.id_usuario AND usu.dat_codigo=seg.dat_codigo_fk"); 
			} catch (Exception ex) {
				System.out.println("Error en MetodoCrearGrupo>>buscarInformesCateterismosRealizados "
						+ ex);
			}
			return rs;
		}
	 
	 public java.sql.ResultSet ObtenerExamenesPaciente(String cedula,String TipoDoc){	    
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select ipac.nombre,ipac.primer_apellido,aen.nombre_entidad,isu.codigo ,isu.nombre,ice.codigo,ice.resultado ,ice.fecha,ice.hora,ipac.numero_documento,ice.aprobacion ,sgu.usu_codigo,ipac.segundo_apellido from img_grupo igr,img_citas_espera ice,adm_paciente ipac,img_subexa isu,seg_usuario sgu,seg_datos_personales sdt,adm_convenio acv,adm_entidad aen where igr.codigo=isu.cod_gruFk and isu.codigo=ice.codigoExa_fk and ipac.pac_codigo_paciente=ice.codigoPac_fk and ice.estado=1 and ipac.numero_documento='"+cedula+"' and ipac.tipo_documento='"+TipoDoc+"' and sdt.dat_codigo=sgu.dat_codigo_fk and sgu.usuario=ice.aprobacion and aen.ent_nit=acv.ent_nit_contratante_fk and acv.conv_numero_contrato=ipac.conv_numero_contrato_fk order by ice.codigo desc ");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoCrearGrupo>>ObtenerExamenesPaciente "+ex);
	        }	
	        return rs;
	    }
	 
	 public java.sql.ResultSet ObtenerPacienteActualizar(String cedula,String TipoDoc){
		 /** no valido para la union de los proyectos */
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select codigo,nombre,genero,eps,telefono,direccion,email,apellidos,fecha_nacimiento,tipo_documento from img_paciente where cedula='"+cedula+"' and tipo_documento='"+TipoDoc+"'");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoCrearGrupo>>ObtenerPacienteActualizar "+ex);
	        }	
	        return rs;
	    }
	 
	 public void ActualizarDemograficoVacio(String codigo,String nombres,String genero,String eps,String telefono,String direccion,String email,String apellidos,String nacimiento,String cedulas,String TipoDoc){
		 /**no valido para la union de los proyectos*/
		    PreparedStatement st = null;
		     try{
		    	nacimiento="(NULL)";
		     	Conexion con=new Conexion();
		     	st= con.conn.prepareStatement("update img_paciente set nombre='"+nombres+"',genero='"+genero+"',cedula='"+cedulas+"',eps='"+eps+"',telefono='"+telefono+"',direccion='"+direccion+"',email='"+email+"',apellidos='"+apellidos+"',fecha_nacimiento="+nacimiento+",tipo_documento='"+TipoDoc+"' where codigo='"+codigo+"'");
		     	st.executeUpdate();	
		     	st.close();
		     	con.cerrar();
		     }
		     catch(Exception ex){
		    	 System.out.println("Error en MetodoCrearGrupo>>ActualizarDemograficoVacio "+ex);
		     	ex.getMessage();	     
		     }	
		 }
	 public void ActualizarDemografico(String codigo,String nombres,String genero,String eps,String telefono,String direccion,String email,String apellidos,String nacimiento,String cedulas){
		 /**no valido para la union de los proyectos*/  
		 PreparedStatement st = null;
		     try{
		    	 String fechapre=null;
		    	 String ini,med,fin=null;
		    	 ini=nacimiento.substring(0,2);
		    	 med=nacimiento.substring(3,5);
		    	 fin=nacimiento.substring(6,10);
		    	 fechapre=fin+"/"+med+"/"+ini;
		     	Conexion con=new Conexion();
		     	st= con.conn.prepareStatement("update img_paciente set nombre='"+nombres+"',genero='"+genero+"',cedula='"+cedulas+"',eps='"+eps+"',telefono='"+telefono+"',direccion='"+direccion+"',email='"+email+"',apellidos='"+apellidos+"',fecha_nacimiento='"+fechapre+"' where codigo='"+codigo+"'");
		     	st.executeUpdate();	
		     	st.close();
		     	con.cerrar();
		     }
		     catch(Exception ex){
		    	 System.out.println("Error en MetodoCrearGrupo>>ActualizarDemografico "+ex);
		     	ex.getMessage();	     
		     }	
		 }

}
