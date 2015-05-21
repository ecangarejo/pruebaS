/**
 * logica: MetodoCrearExamen se encuentran las consultas,inserciones y actualizaciones para  
 * para los registros de los examenes, y la opcion de consultar los examenes
 * que se hicieron en los ultimos dos dias.
*/
package lab_logica;

import java.sql.Statement;

import adm_logica.Conexion;


public class MetodoExamen {
	
	 public java.sql.ResultSet DatosPaciente(String CodPac){
	        java.sql.ResultSet rs=null;
	        Statement st = null;	      
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select tipo_documento,numero_documento,nombre,primer_apellido,segundo_apellido from adm_paciente where pac_codigo_paciente="+CodPac+"");
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error MetodoExamen>>DatosPaciente "+ex);
	        }	      
	        return rs;	        
	    }
	
	
	 public java.sql.ResultSet ConsultaNombrePapellidoSapellido(String Nombre,String PrimerApellido,String SegundoApellido){
	        java.sql.ResultSet rs=null;
	        Statement st = null;	      
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select apac.tipo_documento,apac.numero_documento,apac.primer_apellido,apac.segundo_apellido,apac.nombre,aent.nombre_entidad,apac.pac_codigo_paciente from adm_paciente apac,adm_convenio acv,adm_entidad aent where  aent.ent_nit=acv.ent_nit_contratante_fk and acv.conv_numero_contrato=apac.conv_numero_contrato_fk and apac.nombre='"+Nombre+"' and apac.primer_apellido='"+PrimerApellido+"' and apac.segundo_apellido='"+SegundoApellido+"'");
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error MetodoExamen>>ConsultaNombrePapellidoSapellido "+ex);
	        }	      
	        return rs;	        
	    }
	 
	 public java.sql.ResultSet ConsultaNombrePapellido(String Nombre,String PrimerApellido){
	        java.sql.ResultSet rs=null;
	        Statement st = null;	      
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select apac.tipo_documento,apac.numero_documento,apac.primer_apellido,apac.segundo_apellido,apac.nombre,aent.nombre_entidad,apac.pac_codigo_paciente from adm_paciente apac,adm_convenio acv,adm_entidad aent where  aent.ent_nit=acv.ent_nit_contratante_fk and acv.conv_numero_contrato=apac.conv_numero_contrato_fk and apac.nombre='"+Nombre+"' and apac.primer_apellido='"+PrimerApellido+"'");
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error MetodoExamen>>ConsultaNombrePapellido "+ex);
	        }	      
	        return rs;	        
	    }
	 
	 public java.sql.ResultSet ConsultaNombreSapellido(String Nombre,String SegundoApellido){
	        java.sql.ResultSet rs=null;
	        Statement st = null;	      
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select apac.tipo_documento,apac.numero_documento,apac.primer_apellido,apac.segundo_apellido,apac.nombre,aent.nombre_entidad,apac.pac_codigo_paciente from adm_paciente apac,adm_convenio acv,adm_entidad aent where  aent.ent_nit=acv.ent_nit_contratante_fk and acv.conv_numero_contrato=apac.conv_numero_contrato_fk and apac.nombre='"+Nombre+"' and apac.segundo_apellido='"+SegundoApellido+"'");
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error MetodoExamen>>ConsultaNombreSapellido "+ex);
	        }	      
	        return rs;	        
	    }
	 
	 public java.sql.ResultSet ConsultaPapellidoSapellido(String PrimerApellido,String SegundoApellido){
	        java.sql.ResultSet rs=null;
	        Statement st = null;	      
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select apac.tipo_documento,apac.numero_documento,apac.primer_apellido,apac.segundo_apellido,apac.nombre,aent.nombre_entidad,apac.pac_codigo_paciente from adm_paciente apac,adm_convenio acv,adm_entidad aent where  aent.ent_nit=acv.ent_nit_contratante_fk and acv.conv_numero_contrato=apac.conv_numero_contrato_fk and apac.primer_apellido='"+PrimerApellido+"' and apac.segundo_apellido='"+SegundoApellido+"'");
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error MetodoExamen>>ConsultaPapellidoSapellido "+ex);
	        }	      
	        return rs;	        
	    }
	 
	 public java.sql.ResultSet ConsultaNombre(String Nombre){
	        java.sql.ResultSet rs=null;
	        Statement st = null;	      
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select apac.tipo_documento,apac.numero_documento,apac.primer_apellido,apac.segundo_apellido,apac.nombre,aent.nombre_entidad,apac.pac_codigo_paciente from adm_paciente apac,adm_convenio acv,adm_entidad aent where  aent.ent_nit=acv.ent_nit_contratante_fk and acv.conv_numero_contrato=apac.conv_numero_contrato_fk and apac.nombre like '"+Nombre+"%' ");
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error MetodoExamen>>ConsultaNombre "+ex);
	        }	      
	        return rs;	        
	    }
	 public java.sql.ResultSet ConsultaPapellido(String PrimerApellido){
	        java.sql.ResultSet rs=null;
	        Statement st = null;	      
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select apac.tipo_documento,apac.numero_documento,apac.primer_apellido,apac.segundo_apellido,apac.nombre,aent.nombre_entidad,apac.pac_codigo_paciente from adm_paciente apac,adm_convenio acv,adm_entidad aent where  aent.ent_nit=acv.ent_nit_contratante_fk and acv.conv_numero_contrato=apac.conv_numero_contrato_fk and apac.primer_apellido like '"+PrimerApellido+"%'");
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error MetodoExamen>>ConsultaPapellido "+ex);
	        }	      
	        return rs;	        
	    }
	 public java.sql.ResultSet ConsultaSapellido(String SegundoApellido){
	        java.sql.ResultSet rs=null;
	        Statement st = null;	      
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select apac.tipo_documento,apac.numero_documento,apac.primer_apellido,apac.segundo_apellido,apac.nombre,aent.nombre_entidad,apac.pac_codigo_paciente from adm_paciente apac,adm_convenio acv,adm_entidad aent where  aent.ent_nit=acv.ent_nit_contratante_fk and acv.conv_numero_contrato=apac.conv_numero_contrato_fk and apac.segundo_apellido like '"+SegundoApellido+"%'");
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error MetodoExamen>>ConsultaSapellido "+ex);
	        }	      
	        return rs;	        
	    }
	
	
	
	 public java.sql.ResultSet Examen(String ced,String tipo){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	      
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select distinct lpac.nombre,lpac.primer_apellido,lpac.numero_documento,lres.fecha,lres.hora,lsub.nombre,lex.nombre,lres.resultado,lpac.pac_codigo_paciente,lsub.codigo,lres.aprobaccion,lpac.segundo_apellido from lab_area lar,adm_paciente lpac,lab_resultado lres,lab_examen lex,lab_subarea lsub where lar.codigo=lsub.codarea_fk and lex.codigosubarea_fk=lsub.codigo and lpac.numero_documento='"+ced+"' and lpac.tipo_documento='"+tipo+"' and lres.codexamen_fk=lex.codigo and lpac.pac_codigo_paciente=lres.codpac_fk and lres.estado=1 and lex.codigosubarea_fk!=0 and lres.resultado!='' group by lres.fecha,lres.hora desc");
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error MetodoExamen>>Examen "+ex);
	        }
	      
	        return rs;
	        
	    }
	 public java.sql.ResultSet Fecha_anterior(String ced,String tipo){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	      
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select distinct lpac.nombre,lpac.primer_apellido,lpac.numero_documento,lres.fecha,lres.hora,lsub.nombre,lex.nombre,lres.resultado,lpac.pac_codigo_paciente,lsub.codigo,lpac.segundo_apellido from lab_area lar,adm_paciente lpac,lab_resultado lres,lab_examen lex,lab_subarea lsub where lres.fecha<=(select curdate()) and lres.fecha>=(SELECT DATE_SUB(CURDATE(),INTERVAL 2 DAY)) and lar.codigo=lsub.codarea_fk and lex.codigosubarea_fk=lsub.codigo and lpac.numero_documento="+ced+" and lpac.tipo_documento='"+tipo+"' and lres.codexamen_fk=lex.codigo and lpac.pac_codigo_paciente=lres.codpac_fk and lres.estado=1 and lex.codigosubarea_fk!=0 and lres.resultado!='' group by lres.fecha,lres.hora desc");
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error MetodoExamen>>Fecha_anterior "+ex);
	        }
	      
	        return rs;
	        
	    }
	 public java.sql.ResultSet Examentex(String ced,String tipo){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	      
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select lpac.nombre,lpac.primer_apellido,lpac.numero_documento,lres.fecha,lres.hora,lsub.nombre,lex.nombre,replace(lex.nombre,lex.nombre,'') as unidad,replace(lex.nombre,lex.nombre,'') as valorinicial,replace(lex.nombre,lex.nombre,'') as valorfinal,ltxt.descripcion,lpac.pac_codigo_paciente,lsub.codigo,lpac.segundo_apellido from adm_paciente lpac,lab_examen lex,lab_resultado lres,lab_texto ltxt,lab_subarea lsub,lab_area lare where lres.estado=1 and lpac.pac_codigo_paciente=lres.codpac_fk and lex.codigo=lres.codexamen_fk and lpac.numero_documento= "+ced+" and lpac.tipo_documento='"+tipo+"' and lres.codigo=ltxt.codresultado_fk and lare.codigo=lsub.codarea_fk and lex.codigosubarea_fk=lsub.codigo and lex.codigosubarea_fk=lsub.codigo and lex.codigosubarea_fk!=0 and lex.codigoarea_fk=0 group by lres.fecha, lres.hora desc");
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error MetodoExamen>>Examentex "+ex);
	        }
	      
	        return rs;
	        
	    }
	 
	 public java.sql.ResultSet Fecha_texto(String ced,String tipo){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	      
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select lpac.nombre,lpac.primer_apellido,lpac.numero_documento,lres.fecha,lres.hora,lsub.nombre,lex.nombre,replace(lex.nombre,lex.nombre,'') as unidad,replace(lex.nombre,lex.nombre,'') as valorinicial,replace(lex.nombre,lex.nombre,'') as valorfinal,ltxt.descripcion,lpac.pac_codigo_paciente,lsub.codigo,lpac.segundo_apellido from adm_paciente lpac,lab_examen lex,lab_resultado lres,lab_texto ltxt,lab_subarea lsub,lab_area lare where lres.fecha<=(select curdate()) and lres.fecha>=(SELECT DATE_SUB(CURDATE(),INTERVAL 2 DAY)) and  lres.estado=1 and lpac.pac_codigo_paciente=lres.codpac_fk and lex.codigo=lres.codexamen_fk and lpac.numero_documento= "+ced+" and lpac.tipo_documento='"+tipo+"' and lres.codigo=ltxt.codresultado_fk and lare.codigo=lsub.codarea_fk and lex.codigosubarea_fk=lsub.codigo and lex.codigosubarea_fk=lsub.codigo and lex.codigosubarea_fk!=0 and lex.codigoarea_fk=0 group by lres.fecha, lres.hora desc ");
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error MetodoExamen>>Fecha_texto "+ex);
	        }
	      
	        return rs;
	        
	    }
	 
	 public java.sql.ResultSet tipodocumento(String ced,String nombre){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	      
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select tipo_documento from adm_paciente where numero_documento="+ced+" and nombre='"+nombre+"'");
	        }
	        catch(Exception ex){
	        	
	        	System.out.println("Error MetodoExamen>>tipodocumento "+ex.getMessage());
	        }
	      
	        return rs;
	        
	    }
	 
	 public java.sql.ResultSet Examen_Texto(String ced,String tipo){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	      
	        try{
	        	
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select lres.fecha,lres.hora,lsub.nombre,lex.nombre,ltxt.descripcion,lpac.pac_codigo_paciente,lsub.codigo from lab_area lar,adm_paciente lpac,lab_texto ltxt,lab_resultado lres,lab_examen lex,lab_subarea lsub where lpac.numero_documento="+ced+" and lpac.tipo_documento='"+tipo+"' and lar.codigo=lsub.codarea_fk and lsub.codigo>0 and lex.codigosubarea_fk=lsub.codigo and lres.codexamen_fk=lex.codigo and ltxt.codresultado_fk=lres.codigo and lpac.pac_codigo_paciente=lres.codpac_fk and lres.estado=1 group by lres.fecha ,lres.hora order by lres.fecha desc");
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error MetodoExamen>>Examen_Texto "+ex);
	        }
	      
	        return rs;
	        
	    }
	
	 public java.sql.ResultSet BusExamenes(){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	      
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select distinct nombre,codigo,tipo from lab_examen group by nombre");
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error MetodoExamen>>BusExamenes "+ex);
	        }
	      
	        return rs;
	        
	    }
	 public java.sql.ResultSet busexamenes_hechos(String tipo,String cedula){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	      
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();	        	
	        	rs=st.executeQuery("select distinct lex.nombre,lex.codigo,lex.tipo from lab_examen lex,lab_resultado lres,adm_paciente lpac where lpac.tipo_documento='"+tipo+"' and  lpac.numero_documento="+cedula+" and lres.codpac_fk=lpac.pac_codigo_paciente and lres.codexamen_fk=lex.codigo and lres.resultado!='' and lres.resultado!='-' group by lex.nombre");
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error MetodoExamen>>busexamenes_hechos "+ex);
	        }
	      
	        return rs;
	        
	    }
	 
	 public java.sql.ResultSet Bustip(String nombre){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	      
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select distinct codigo,tipo from lab_examen where nombre='"+nombre+"' group by nombre");
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error MetodoExamen>>Bustip "+ex);
	        }
	      
	        return rs;
	        
	    }
	
	 public java.sql.ResultSet MetodoSubarea(String nombre){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select lab_subarea.nombre from lab_subarea,lab_area where  lab_area.codigo=lab_subarea.codarea_fk and lab_area.nombre='"+nombre+"'");
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error MetodoExamen>>MetodoSubarea "+ex);
	        }
	      
	        return rs;
	        
	    }
	 
	 public java.sql.ResultSet Busedadygene(String ced, String tipo){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select genero,(year(curdate())-year(fecha_nacimiento)) - (right(curdate(),5) < right(fecha_nacimiento, 5)) as edad,nombre,primer_apellido,segundo_apellido from adm_paciente  where numero_documento='"+ced+"' and tipo_documento='"+tipo+"'");
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error MetodoExamen>>Busedadygene "+ex);
	        }
	      
	        return rs;
	        
	    }
	
	 public java.sql.ResultSet MetodoExamen1(String nombre){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	      
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select distinct lab_examen.nombre from lab_examen,lab_subarea,lab_area where lab_examen.codigosubarea_fk=0 and lab_area.codigo=lab_examen.codigoarea_fk and lab_area.nombre='"+nombre+"'");
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error MetodoExamen>>MetodoExamen1 "+ex);
	        }
	      
	        return rs;
	        
	    }
	 public java.sql.ResultSet BuscaSexo(String nombre,String ge){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	      
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select distinct lab_examen.nombre,lab_examen.tipo,lab_examen.codigosubarea_fk,lab_examen.codigo,lab_rango.valorinicial,replace(lab_unidad.nombre,'9','%') as unidad,lab_rango.valorfinal from lab_examen,lab_subarea,lab_area,lab_rango,lab_unidad,lab_tipo_rango where lab_examen.tipo=1 and lab_area.codigo=lab_examen.codigoarea_fk and lab_subarea.codigo=lab_examen.codigosubarea_fk and lab_subarea.nombre='"+nombre+"'and lab_examen.codigosubarea_fk>0 and lab_area.codigo=0 and lab_rango.codtiporango_fk=lab_tipo_rango.codigo and lab_examen.codigo=lab_tipo_rango.codexamen_fk and lab_examen.codigosubarea_fk>0 and lab_area.codigo=0 and lab_rango.codtiporango_fk=lab_tipo_rango.codigo and lab_rango.codgenero_fk='"+ge+"'  and lab_rango.codunidad_fk=lab_unidad.codigo ");
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error MetodoExamen>>BuscaSexo "+ex);
	        }
	      
	        return rs;
	        
	    }
	 public java.sql.ResultSet ExameGrupo(String nombre){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	      
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select distinct lab_examen.nombre,lab_examen.tipo,lab_examen.codigosubarea_fk,lab_examen.codigo,lab_subarea.codigo from lab_examen,lab_subarea,lab_area,lab_rango where lab_area.codigo=lab_examen.codigoarea_fk and lab_subarea.codigo=lab_examen.codigosubarea_fk and lab_subarea.nombre='"+nombre+"'and lab_examen.codigosubarea_fk>0 and lab_area.codigo=0");
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error MetodoExamen>>ExameGrupo "+ex);
	        }
	      
	        return rs;
	        
	    }
	 
	 public java.sql.ResultSet ExameGrupoRango(String nombre){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	      
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select distinct lab_examen.nombre,lab_examen.tipo,lab_examen.codigosubarea_fk,lab_examen.codigo,lab_rango.valorinicial,replace(lab_unidad.nombre,'9','%') as unidad,lab_rango.valorfinal,lab_tipo_rango.tiporango,lab_rango.edadInicial,lab_rango.edadFinal from lab_examen,lab_subarea,lab_area,lab_rango,lab_unidad,lab_tipo_rango where lab_examen.tipo=1 and lab_area.codigo=lab_examen.codigoarea_fk and lab_subarea.codigo=lab_examen.codigosubarea_fk and lab_subarea.nombre='"+nombre+"'and lab_examen.codigosubarea_fk>0 and lab_area.codigo=0 and lab_rango.codtiporango_fk=lab_tipo_rango.codigo and lab_examen.codigo=lab_tipo_rango.codexamen_fk and lab_examen.codigosubarea_fk>0 and lab_area.codigo=0 and lab_rango.codtiporango_fk=lab_tipo_rango.codigo and lab_rango.codunidad_fk=lab_unidad.codigo and lab_tipo_rango.tiporango='GENERAL' order by lab_examen.codigo");
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error MetodoExamen>>ExameGrupoRango"+ex);
	        }
	      
	        return rs;
	        
	    }
	 
	 public java.sql.ResultSet ExameGrup(String nombre){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	      
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select distinct lab_examen.nombre,lab_tipo_rango.tiporango,lab_rango.edadInicial,lab_rango.edadFinal,lab_subarea.codigo from lab_examen,lab_subarea,lab_area,lab_rango,lab_unidad,lab_tipo_rango where lab_examen.tipo=1 and lab_area.codigo=lab_examen.codigoarea_fk and lab_subarea.codigo=lab_examen.codigosubarea_fk and lab_subarea.nombre='"+nombre+"'and lab_examen.codigosubarea_fk>0 and lab_area.codigo=0 and lab_rango.codtiporango_fk=lab_tipo_rango.codigo and lab_examen.codigo=lab_tipo_rango.codexamen_fk and lab_examen.codigosubarea_fk>0 and lab_area.codigo=0 and lab_rango.codtiporango_fk=lab_tipo_rango.codigo and lab_rango.codunidad_fk=lab_unidad.codigo order by lab_examen.nombre");
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error MetodoExamen>>ExameGrup "+ex);
	        }
	      
	        return rs;
	        
	    }
	 
	 public java.sql.ResultSet Examenom(String exa,String tipo,String ced,String gene,int edad){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	      
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select lpac.numero_documento,lpac.nombre,lpac.primer_apellido,DATE_FORMAT(lres.fecha,'%d/%m/%y'),lres.hora,lex.nombre as exa,lres.resultado,lran.valorinicial,lran.valorfinal,lpac.pac_codigo_paciente,lpac.genero,replace(lun.nombre,'9','%')as unidad,lar.nombre as grupo,lsub.nombre as subgrupo,(year(curdate())-year(lpac.fecha_nacimiento)) - (right(curdate(),5) < right(lpac.fecha_nacimiento, 5)) as edad,lpac.fecha_nacimiento as nacimiento from adm_paciente lpac,lab_resultado lres,lab_examen lex,lab_rango lran,lab_unidad lun,lab_subarea lsub,lab_area lar,lab_tipo_rango ltr where lres.estado=1 and lpac.numero_documento="+ced+" and lpac.tipo_documento='"+tipo+"' and lar.codigo=lsub.codarea_fk and lpac.pac_codigo_paciente=lres.codpac_fk and lres.codexamen_fk=lex.codigo and lex.codigosubarea_fk=lsub.codigo and lex.nombre='"+exa+"' and( (lran.edadInicial<="+edad+" and  lran.edadFinal>="+edad+") XOR  lran.codgenero_fk=2 xor ltr.tiporango='GENERAL') and lran.codtiporango_fk=ltr.codigo and ltr.codexamen_fk=lex.codigo and lun.codigo=lran.codunidad_fk order by  lres.fecha desc ");
	       	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error MetodoExamen>>Examenom "+ex);
	        }
	      
	        return rs;
	        
	    }
	 
	 public java.sql.ResultSet ExameGrupoEdad(String nombre, String edadi,String edadf){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	      
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select distinct lab_examen.nombre,lab_examen.tipo,lab_examen.codigosubarea_fk,lab_examen.codigo,lab_rango.valorinicial,replace(lab_unidad.nombre,'9','%') as unidad,lab_rango.valorfinal from lab_examen,lab_subarea,lab_area,lab_rango,lab_unidad,lab_tipo_rango where lab_examen.tipo=1 and lab_area.codigo=lab_examen.codigoarea_fk and lab_subarea.codigo=lab_examen.codigosubarea_fk and lab_subarea.nombre='"+nombre+"' and lab_rango.codtiporango_fk=lab_tipo_rango.codigo and lab_examen.codigo=lab_tipo_rango.codexamen_fk and lab_examen.codigosubarea_fk>0 and lab_examen.codigoarea_fk=0 and lab_rango.codtiporango_fk=lab_tipo_rango.codigo  and lab_rango.codunidad_fk=lab_unidad.codigo and  lab_rango.edadInicial='"+edadi+"' and lab_rango.edadFinal='"+edadf+"'");
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error MetodoExamen>>ExameGrupoEdad"+ex);
	        }
	      
	        return rs;
	        
	    }
	 
	 public java.sql.ResultSet BuscaTipo(String nombre){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	      
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select lab_tipo_rango.tiporango from lab_tipo_rango,lab_examen where lab_examen.codigo="+nombre+"  and lab_examen.codigo=lab_tipo_rango.codexamen_fk");
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error MetodoExamen>>BuscaTipo "+ex);
	        }
	      
	        return rs;
	        
	    }
	 public java.sql.ResultSet Buscacodge(String nombre){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	      
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select lab_genero.codigo from lab_genero where lab_genero.especificacion='"+nombre+"'");
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error MetodoExamen>>Buscacodge "+ex);
	        }
	      
	        return rs;
	        
	    }
	
      
	 public java.sql.ResultSet BuscarExamen(String nombre){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	      
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select lab_examen.tipo,lab_examen.codigoarea_fk from lab_examen where  lab_examen.nombre='"+nombre+"'");
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error MetodoExamen>>BuscarExamen "+ex);
	        }
	      
	        return rs;
	        
	    }
	 public java.sql.ResultSet CodExamenIndividual(String nombre){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	      
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select lab_examen.codigo from lab_examen where  lab_examen.nombre='"+nombre+"'and lab_examen.codigosubarea_fk=0");
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error  MetodoExamen>>CodExamenIndividual "+ex);
	        }
	      
	        return rs;
	        
	    }
	 
	 public java.sql.ResultSet CodExamenGrupo(String nombre){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	      
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select lab_examen.codigo from lab_examen where  lab_examen.nombre='"+nombre+"'and lab_examen.codigoarea_fk=0");
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error MetodoExamen>>CodExamenGrupo "+ex);
	        }
	      
	        return rs;
	        
	    }
	 public java.sql.ResultSet CodExamenInd(String nombre){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	      
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select codigo from lab_examen where  nombre='"+nombre+"' and codigosubarea_fk=0");
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error MetodoExamen>>CodExamenInd "+ex);
	        }
	      
	        return rs;
	        
	    }
	 public java.sql.ResultSet CodExamenGrup(String nombre){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	      
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select codigo from lab_examen where  nombre='"+nombre+"' and codigoarea_fk=0");
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error MetodoExamen>>CodExamenGrup "+ex);
	        }
	      
	        return rs;
	        
	    }
	 
	 public java.sql.ResultSet ExamenTexto(String ced,String tipo){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	      
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select distinct lpac.numero_documento,lpac.nombre,lpac.primer_apellido,lres.fecha,lres.hora,lex.nombre,lres.resultado,lex.codigo,lpac.pac_codigo_paciente,lres.codigo,lres.aprobaccion,lpac.segundo_apellido from adm_paciente lpac,lab_resultado lres,lab_examen lex where lpac.tipo_documento='"+tipo+"' and lpac.numero_documento='"+ced+"' and lpac.pac_codigo_paciente=lres.codpac_fk and lres.codexamen_fk=lex.codigo and lex.tipo=0  and lex.codigosubarea_fk=0 and lres.estado=1 and lres.resultado!='' order by lres.fecha,lres.hora desc");
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error MetodoExamen>>ExamenTexto "+ex);
	        }
	      
	        return rs;
	        
	    }
	 public java.sql.ResultSet Fecha_Texto(String ced,String tipo){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	      
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select distinct lpac.numero_documento,lpac.nombre,lpac.primer_apellido,lres.fecha,lres.hora,lex.nombre,lres.resultado,lex.codigo,lpac.pac_codigo_paciente,lres.codigo,lpac.segundo_apellido from adm_paciente lpac,lab_resultado lres,lab_examen lex where lres.fecha<=(select curdate()) and lres.fecha>=(SELECT DATE_SUB(CURDATE(),INTERVAL 2 DAY)) and lpac.tipo_documento='"+tipo+"' and lpac.numero_documento="+ced+" and lpac.pac_codigo_paciente=lres.codpac_fk and lres.codexamen_fk=lex.codigo and lex.tipo=0  and lex.codigosubarea_fk=0 and lres.estado=1 order by lres.fecha,lres.hora desc");
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error MetodoExamen>>Fecha_Texto "+ex);
	        }
	      
	        return rs;
	        
	    }
	 
	 public java.sql.ResultSet ExamenTexto1(String ced,String tipo, String examen){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	      
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select distinct DATE_FORMAT(lres.fecha,'%d/%m/%y'),lres.hora,lres.resultado from adm_paciente lpac,lab_resultado lres,lab_examen lex,lab_subarea lsub,lab_area lar where lpac.tipo_documento='"+tipo+"' and lpac.numero_documento="+ced+" and lpac.pac_codigo_paciente=lres.codpac_fk and lres.codexamen_fk=lex.codigo and lex.tipo=0 and lex.nombre='"+examen+"' and lar.codigo=lsub.codarea_fk and lex.codigosubarea_fk=lsub.codigo and lres.estado=1 and lres.resultado!='' order by lres.fecha,lres.hora desc");
	        	
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error MetodoExamen>>ExamenTexto1 "+ex);
	        }
	      
	        return rs;
	        
	    }
	 
	 public java.sql.ResultSet ExamenRango(String ced,String tipo,int edad,String gene){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	      
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select distinct lpac.numero_documento,lpac.nombre,lpac.primer_apellido,lres.fecha,lres.hora,lex.nombre,lres.resultado,lran.valorinicial,lran.valorfinal,lex.codigo,lpac.pac_codigo_paciente,lres.codigo,lres.aprobaccion,lpac.segundo_apellido from adm_paciente lpac,lab_resultado lres,lab_examen lex,lab_rango lran,lab_tipo_rango ltr where lpac.tipo_documento='"+tipo+"' and lpac.pac_codigo_paciente=lres.codpac_fk and lres.codexamen_fk=lex.codigo and lex.tipo=1 and lran.codtiporango_fk=ltr.codigo and ltr.codexamen_fk=lex.codigo and lex.codigosubarea_fk=0 and lex.codigoarea_fk>0 and lpac.numero_documento='"+ced+"' AND ( (lran.edadInicial<"+edad+" and  lran.edadFinal>"+edad+") XOR lran.codgenero_fk="+gene+" XOR ltr.tiporango='GENERAL') and lres.estado=1 order by lres.fecha, lres.hora desc ");
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error MetodoExamen>>ExamenRango "+ex);
	        }
	      
	        return rs;
	        
	    }
	 
	 public java.sql.ResultSet TodosExamenes(String ced,String tipo){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	      
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select distinct lpac.nombre,lpac.primer_apellido,lres.fecha,lpac.segundo_apellido,lpac.pac_codigo_paciente,(year(curdate())-year(lpac.fecha_nacimiento)) - (right(curdate(),5)< right(lpac.fecha_nacimiento, 5)) as edad from adm_paciente lpac,lab_resultado lres where lpac.tipo_documento='"+tipo+"' and lpac.numero_documento='"+ced+"' and lpac.pac_codigo_paciente=lres.codpac_fk");
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error MetodoExamen>>TodosExamenes "+ex);
	        }
	      
	        return rs;
	        
	    }
	 
	 
	 public java.sql.ResultSet Fecha_Rango(String ced,String tipo,int edad,String gene){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	      
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select distinct lpac.numero_documento,lpac.nombre,lpac.primer_apellido,lres.fecha,lres.hora,lex.nombre,lres.resultado,lran.valorinicial,lran.valorfinal,lex.codigo,lpac.pac_codigo_paciente,lres.codigo,lpac.segundo_apellido from adm_paciente lpac,lab_resultado lres,lab_examen lex,lab_rango lran,lab_tipo_rango ltr where lres.fecha<=(select curdate()) and lres.fecha>=(SELECT DATE_SUB(CURDATE(),INTERVAL 2 DAY)) and  lpac.tipo_documento='"+tipo+"' and lpac.pac_codigo_paciente=lres.codpac_fk and lres.codexamen_fk=lex.codigo and lex.tipo=1 and lran.codtiporango_fk=ltr.codigo and ltr.codexamen_fk=lex.codigo and lex.codigosubarea_fk=0 and lex.codigoarea_fk>0 and lpac.numero_documento="+ced+" AND ( (lran.edadInicial<"+edad+" and  lran.edadFinal>"+edad+") XOR lran.codgenero_fk="+gene+" XOR ltr.tiporango='GENERAL') and lres.estado=1 order by lres.fecha, lres.hora desc");
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error MetodoExamen>>Fecha_Rango "+ex);
	        }
	      
	        return rs;
	        
	    }
	 
}
