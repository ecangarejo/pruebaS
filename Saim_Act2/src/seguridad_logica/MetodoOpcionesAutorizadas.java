
package seguridad_logica;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Date;
import java.sql.Time;
import java.sql.ResultSet;
import adm_logica.Conexion;

import seguridad_bean.OpcionesAutorizadas;


public class MetodoOpcionesAutorizadas {
	public MetodoOpcionesAutorizadas(){}

	
	public void OmitirPermisoFormatoCE(String CodigoAsignacion){
		PreparedStatement st = null;
		try{
			Conexion con=new Conexion();
			st= con.conn.prepareStatement("DELETE FROM seg_usuariosformatosce WHERE codigo="+CodigoAsignacion+" ");
			st.executeUpdate();
			st.close();
			con.cerrar();
		}
		catch(Exception ex){
			System.out.print("Error en MetodoOpcionesAutorizadas>>OmitirPermisoFormatoCE "+ex);
		}	
	}
	
	public void OmitirPermisoHC(String CodigoAsignacion){
		PreparedStatement st = null;
		try{
			Conexion con=new Conexion();
			st= con.conn.prepareStatement("DELETE FROM hic_usuariopermisoshc WHERE codigo="+CodigoAsignacion+" ");
			st.executeUpdate();
			st.close();
			con.cerrar();
		}
		catch(Exception ex){
			System.out.print("Error en MetodoOpcionesAutorizadas>>OmitirPermisoHC "+ex);
		}	
	}
	
	public void OmitirPermisoFormatoHosp(String CodigoAsignacion){
		PreparedStatement st = null;
		try{
			Conexion con=new Conexion();
			st= con.conn.prepareStatement("DELETE FROM seg_usuariosformato WHERE codigo="+CodigoAsignacion+" ");
			st.executeUpdate();
			st.close();
			con.cerrar();
		}
		catch(Exception ex){
			System.out.print("Error en MetodoOpcionesAutorizadas>>OmitirPermisoFormatoHosp "+ex);
		}	
	}

	public void insertarRelacionFormatosCE(String CodigoUsuario,String CodigoFormato){
		OpcionesAutorizadas oa=new OpcionesAutorizadas();
		oa.setUsuario(CodigoUsuario);
		oa.setCodigoFormato(CodigoFormato);
		try{
			PreparedStatement ps = null;
		    Conexion con=new Conexion();
		    ps=con.conn.prepareStatement("insert into seg_usuariosformatosce (codigoUsuario_fk,codigoFormato_fk) value (?,?)");				
			ps.setString(1, oa.getUsuario());
			ps.setString(2, oa.getCodigoFormato());
			ps.executeUpdate();
			ps.close();
			con.cerrar();
		}//fin del try
		catch(Exception ex){
			System.out.print("Error en MetodoOpcionesAutorizadas>>insertarRelacionFormatosCE "+ex);
		}//fin del catch
	}//fin insercion
	

public java.sql.ResultSet BuscarMasculino(String Fechai, String Fechaf, String  ftent){	
		/**
		 * consulta la cantidad de paciente ingresado en un determinado rango de fechas segun genero 
		 * 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(ftent.equals("todas")){
        	rs=st.executeQuery("SELECT COUNT(pac.genero) FROM adm_paciente pac, adm_convenio ac, adm_entidad ae, adm_admisiones aa "+
        						"WHERE pac.genero='Masculino' "+
        						"AND ac.conv_numero_contrato=pac.conv_numero_contrato_fk "+
        						"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
        						"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente "+
        						"AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"'");
        	System.out.println("SELECT COUNT(pac.genero), FROM adm_paciente pac, adm_convenio ac, adm_entidad ae, adm_admisiones aa "+
        						"WHERE pac.genero='Masculino' "+
        						"AND ac.conv_numero_contrato=pac.conv_numero_contrato_fk "+
        						"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
        						"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente "+
        						"AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"'");
        	
        	}else{
        		rs=st.executeQuery("SELECT COUNT(pac.genero) FROM adm_paciente pac, adm_convenio ac, adm_entidad ae, adm_admisiones aa "+
						"WHERE pac.genero='Masculino' "+
						"AND ac.conv_numero_contrato=pac.conv_numero_contrato_fk "+
						"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
						"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente "+
						"AND ae.ent_nit='"+ftent+"' AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"'"); 
        	}
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoOpcionesAutorizadas>>BuscarMasculino "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarFemenino(String Fechai, String Fechaf, String  ftent){	
		/**
		 * consulta la cantidad de paciente ingresado en un determinado rango de fechas segun genero 
		 * 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        System.out.println("Fechai "+Fechai+"Fechaf "+Fechaf+"Ent "+ftent);
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(ftent.equals("todas")){
        	rs=st.executeQuery("SELECT COUNT(pac.genero) FROM adm_paciente pac, adm_convenio ac, adm_entidad ae, adm_admisiones aa "+
        						"WHERE pac.genero='Femenino' "+
        						"AND ac.conv_numero_contrato=pac.conv_numero_contrato_fk "+
        						"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
        						"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente "+
        						"AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"'");
        	}else{
        		rs=st.executeQuery("SELECT COUNT(pac.genero) FROM adm_paciente pac, adm_convenio ac, adm_entidad ae, adm_admisiones aa "+
						"WHERE pac.genero='Femenino' "+
						"AND ac.conv_numero_contrato=pac.conv_numero_contrato_fk "+
						"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
						"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente "+
						"AND ae.ent_nit='"+ftent+"' AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"'");
        	}
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoOpcionesAutorizadas>>BuscarFemenino "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarEdades(int Ed1, int Ed2, String Fechai, String Fechaf, String  ftent){	
		/**
		 * Consulta la cantidad de ingresos de paciente clasificado por fecha y por edades
		 * 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        System.out.println("Fechai "+Fechai+"Fechaf "+Fechaf+"Ent "+ftent);
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(ftent.equals("todas")){
        	rs=st.executeQuery("SELECT COUNT(pac.pac_codigo_paciente) "+
        						"FROM adm_paciente pac,adm_convenio con, adm_entidad ent, adm_admisiones aa "+
        						"WHERE ((YEAR(aa.fecha_registro))-YEAR(pac.fecha_nacimiento)) BETWEEN '"+Ed1+"' AND '"+Ed2+"' "+
        						"AND pac.conv_numero_contrato_fk=con.conv_numero_contrato "+
        						"AND con.ent_nit_contratante_fk=ent.ent_nit "+
        						"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"'"		);
        	}else{
        		rs=st.executeQuery("SELECT COUNT(pac.pac_codigo_paciente) "+
        							"FROM adm_paciente pac,adm_convenio con, adm_entidad ent, adm_admisiones aa "+
        							"WHERE ((YEAR(aa.fecha_registro))-YEAR(pac.fecha_nacimiento)) BETWEEN '"+Ed1+"' AND '"+Ed2+"' "+
        							"AND pac.conv_numero_contrato_fk=con.conv_numero_contrato "+
        							"AND con.ent_nit_contratante_fk=ent.ent_nit  AND ent.ent_nit='"+ftent+"' "+
        							"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"'");
        	}
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoOpcionesAutorizadas>>BuscarEdades "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet BuscarEntidades(String Fechai, String Fechaf){	
		/**
		 * Consulta las entidades mas usadas
		 * 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        System.out.println("Fechai "+Fechai+"Fechaf "+Fechaf);
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT COUNT(ent.nombre_entidad) AS  Numero_Afiliados  ,ent.nombre_entidad "+
        						"FROM adm_paciente pac, adm_convenio con, adm_entidad ent , adm_admisiones aa   "+                
        						"WHERE con.`conv_numero_contrato`=pac.`conv_numero_contrato_fk` "+
        						"AND con.`ent_nit_contratante_fk`=ent.`ent_nit`  "+
        						"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente "+
        						"AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' "+
        						"GROUP BY ent.nombre_entidad ORDER BY Numero_Afiliados DESC ");
        	
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoOpcionesAutorizadas>>BuscarEntidades "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarNombEnt(String ftent){	
		/**
		 * Consulta el nombre de la entidad
		 * 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT ae.nombre_entidad FROM adm_entidad ae WHERE ae.ent_nit='"+ftent+"'");
        	
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoOpcionesAutorizadas>>BuscarNombEnt "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarPacIngreRepetido(String Fechai, String Fechaf, String ftent){	
		/**
		 * Consulta si el paciente tiene mas de una admision dentro del rango de la fecha dada y al bodega 
		 * 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(ftent.equals("todas")){
        	rs=st.executeQuery("SELECT * FROM(SELECT COUNT(ap.pac_codigo_paciente) AS contador,ap.pac_codigo_paciente, aad.adm_numero_ingreso, aad.fecha_registro, aad.hora_registro "+
        						"FROM adm_paciente ap, adm_admisiones aad, adm_convenio ac, adm_entidad ae, adm_censo_cama acen, adm_historico_cama ahc "+
        						"WHERE ap.pac_codigo_paciente=aad.pac_codigo_paciente_fk "+
        						"AND ap.conv_numero_contrato_fk=ac.conv_numero_contrato "+
        						"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
        						"AND ahc.adm_numero_ingreso_fk=aad.adm_numero_ingreso "+
        						"AND acen.cen_numero_cama=ahc.cen_numero_cama_fk "+
        						"AND servicio=1 "+
        						"AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' GROUP BY ap.pac_codigo_paciente) AS A WHERE a.contador>1");
        	
        	}else{
        		rs=st.executeQuery("SELECT * FROM(SELECT COUNT(ap.pac_codigo_paciente) AS contador,ap.pac_codigo_paciente, aad.adm_numero_ingreso, aad.fecha_registro, aad.hora_registro "+
						"FROM adm_paciente ap, adm_admisiones aad, adm_convenio ac, adm_entidad ae, adm_censo_cama acen, adm_historico_cama ahc "+
						"WHERE ap.pac_codigo_paciente=aad.pac_codigo_paciente_fk "+
						"AND ap.conv_numero_contrato_fk=ac.conv_numero_contrato "+
						"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
						"AND ahc.adm_numero_ingreso_fk=aad.adm_numero_ingreso "+
						"AND acen.cen_numero_cama=ahc.cen_numero_cama_fk "+
						"AND servicio=1 "+
						"AND ae.ent_nit='"+ftent+"' ANDaad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' GROUP BY ap.pac_codigo_paciente) AS A WHERE a.contador>1");
        	}
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoOpcionesAutorizadas>>BuscarPacIngreRepetido "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarAdmPac(String CodPac,String Fechai,String Fechaf, String ftent){	
		/**
		 * Consulta las admisiones que ha tenido el paciente segun el rango de fecha y entidad
		 * 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(ftent.equals("todas")){
        	rs=st.executeQuery("SELECT ap.pac_codigo_paciente, aad.adm_numero_ingreso, aad.fecha_registro, aad.hora_registro "+
        						"FROM adm_paciente ap, adm_admisiones aad, adm_convenio ac, adm_entidad ae, adm_censo_cama acen, adm_historico_cama ahc "+
        						"WHERE ap.pac_codigo_paciente=aad.pac_codigo_paciente_fk "+
        						"AND ap.conv_numero_contrato_fk=ac.conv_numero_contrato "+
        						"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
        						"AND ahc.adm_numero_ingreso_fk=aad.adm_numero_ingreso "+
        						"AND acen.cen_numero_cama=ahc.cen_numero_cama_fk "+
        						"AND servicio=1 "+
        						"AND ap.pac_codigo_paciente='"+CodPac+"' "+
        						"AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ");
        	}else{
        		rs=st.executeQuery("SELECT ap.pac_codigo_paciente, aad.adm_numero_ingreso, aad.fecha_registro, aad.hora_registro "+
						"FROM adm_paciente ap, adm_admisiones aad, adm_convenio ac, adm_entidad ae, adm_censo_cama acen, adm_historico_cama ahc "+
						"WHERE ap.pac_codigo_paciente=aad.pac_codigo_paciente_fk "+
						"AND ap.conv_numero_contrato_fk=ac.conv_numero_contrato "+
						"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
						"AND ahc.adm_numero_ingreso_fk=aad.adm_numero_ingreso "+
						"AND acen.cen_numero_cama=ahc.cen_numero_cama_fk "+
						"AND servicio=1 "+
						"AND ap.pac_codigo_paciente='"+CodPac+"' "+
						"AND ae.ent_nit='"+ftent+"'  AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ");
        	}
        	
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoOpcionesAutorizadas>>BuscarAdmPac "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarFalleUrg(String Fechai, String Fechaf, String ftent){	
		/**
		 * Consulta los pacientes fallecidos en urgencia. 
		 * 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(ftent.equals("todas")){
        	rs=st.executeQuery("SELECT COUNT(*) FROM hic_destinopaciente hd, adm_admisiones aad,adm_paciente pac,adm_convenio aconv , adm_entidad aen "+         
        						"WHERE hd.EstadoSalida='MUERTO' "+
        						"AND hd.DestinoPaciente='SALIDA DE URGENCIA' "+
        						"AND hd.codAdm=aad.adm_numero_ingreso "+
        						"AND hd.codPac=pac.pac_codigo_paciente "+
        						"AND pac.conv_numero_contrato_fk=aconv.conv_numero_contrato "+
        						"AND aen.ent_nit=aconv.ent_nit_contratante_fk "+
        						"AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"'");
        	}else{
        		rs=st.executeQuery("SELECT COUNT(*) FROM hic_destinopaciente hd, adm_admisiones aad,adm_paciente pac,adm_convenio aconv , adm_entidad aen "+         
						"WHERE hd.EstadoSalida='MUERTO' "+
						"AND hd.DestinoPaciente='SALIDA DE URGENCIA' "+
						"AND hd.codAdm=aad.adm_numero_ingreso "+
						"AND hd.codPac=pac.pac_codigo_paciente "+
						"AND pac.conv_numero_contrato_fk=aconv.conv_numero_contrato "+
						"AND aen.ent_nit=aconv.ent_nit_contratante_fk "+
						"AND aent.ent_nit='"+ftent+"' AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"'");
        	}
        	
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoOpcionesAutorizadas>>BuscarFalleUrg "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarPacUrgen(String Fechai, String Fechaf, String ftent){	
		/**
		 * Consulta de Paciente atendidos en urgencia 
		 * 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(ftent.equals("todas")){
        	rs=st.executeQuery("SELECT COUNT(*)  FROM adm_admisiones aa,adm_censo_cama ac, adm_paciente pac, adm_convenio acon, adm_entidad ae  "+                   
        						"WHERE aa.cen_numero_cama_fk=ac.cen_numero_cama "+
        						"AND ac.servicio=1 "+
        						"AND pac.pac_codigo_paciente=aa.pac_codigo_paciente_fk "+
        						"AND acon.conv_numero_contrato=pac.conv_numero_contrato_fk "+
        						"AND acon.ent_nit_contratante_fk=ae.ent_nit "+
        						"AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"'");
        	}else{
        		rs=st.executeQuery("SELECT COUNT(*)  FROM adm_admisiones aa,adm_censo_cama ac, adm_paciente pac, adm_convenio acon, adm_entidad ae  "+                   
						"WHERE aa.cen_numero_cama_fk=ac.cen_numero_cama "+
						"AND ac.servicio=1 "+
						"AND pac.pac_codigo_paciente=aa.pac_codigo_paciente_fk "+
						"AND acon.conv_numero_contrato=pac.conv_numero_contrato_fk "+
						"AND acon.ent_nit_contratante_fk=ae.ent_nit "+
						"AND ae.ent_nit='"+ftent+"' AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"'");
        	}
        	
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoOpcionesAutorizadas>>BuscarPacUrgen "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarMinHC(String Fechai, String Fechaf, String ftent){	
		/**
		 * Consulta los minutos transcurrido entre la admision del apciente y su atencion por el medico
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(ftent.equals("todas")){
        	rs=st.executeQuery("SELECT pac.nombre,pac.primer_apellido, "+
        						"hic.codigo_adm_fk AS codigo2, "+
        						"CONCAT(hic.fecha,' ', hic.hora) AS hora_Historia, "+
        						"ROUND(TIME_TO_SEC(TIMEDIFF(CONCAT(hic.fecha,' ', hic.hora),CONCAT(adm.fecha_registro,' ', adm.hora_registro)))/60) "+
        						"AS Minutos_Admisiones_HistoriaClinica, adm.adm_numero_ingreso "+
        						"FROM `adm_admisiones` adm ,`hic_adm_formatos_pac` hic, adm_paciente pac, adm_convenio ac, adm_entidad ae  "+
        						"WHERE hic.codigo_adm_fk = adm.adm_numero_ingreso "+
        						"AND adm.`pac_codigo_paciente_fk`=pac.pac_codigo_paciente "+
        						"AND (hic.`codigo_for_fk`=26 OR hic.codigo_for_fk=43) "+
        						"AND ac.conv_numero_contrato=pac.conv_numero_contrato_fk "+
        						"AND adm.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' "+
        						"AND ae.ent_nit=ac.ent_nit_contratante_fk ");
        	}else{
        		rs=st.executeQuery("SELECT pac.nombre,pac.primer_apellido, "+
						"hic.codigo_adm_fk AS codigo2, "+
						"CONCAT(hic.fecha,' ', hic.hora) AS hora_Historia, "+
						"ROUND(TIME_TO_SEC(TIMEDIFF(CONCAT(hic.fecha,' ', hic.hora),CONCAT(adm.fecha_registro,' ', adm.hora_registro)))/60) "+
						"AS Minutos_Admisiones_HistoriaClinica, adm.adm_numero_ingreso "+
						"FROM `adm_admisiones` adm ,`hic_adm_formatos_pac` hic, adm_paciente pac, adm_convenio ac, adm_entidad ae  "+
						"WHERE hic.codigo_adm_fk = adm.adm_numero_ingreso "+
						"AND adm.`pac_codigo_paciente_fk`=pac.pac_codigo_paciente "+
						"AND (hic.`codigo_for_fk`=26 OR hic.codigo_for_fk=43) "+
						"AND ac.conv_numero_contrato=pac.conv_numero_contrato_fk "+
						"AND adm.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' "+
						"AND ae.ent_nit=ac.ent_nit_contratante_fk AND ae.ent_nit='"+ftent+"' ");
        	}
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoOpcionesAutorizadas>>BuscarNombEnt "+ex);
        }	
        return rs;
    }
	public java.sql.ResultSet BuscarPatologia(String Fechai, String Fechaf,String ftent){	
		/**
		 * Consulta las diagnosticos mas comunes segun fecha 
		 * 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        System.out.println("Fechai "+Fechai+"Fechaf "+Fechaf);
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(ftent.equals("todas")){
        	rs=st.executeQuery("SELECT ci.nombre, COUNT(hde.codDiagnostico) AS cantidad "+
        								"FROM adm_admisiones aad,adm_censo_cama cama,hic_diagnosticoegreso hde,cie10 ci,adm_paciente apac,empresa emp, adm_convenio ac, adm_entidad ae "+
        								"WHERE aad.adm_numero_ingreso=hde.codAdm "+
        								"AND hde.TipDiag='EG' "+
        								"AND apac.pac_codigo_paciente=aad.pac_codigo_paciente_fk "+
        								"AND aad.cen_numero_cama_fk=cama.cen_numero_cama "+
        								"AND hde.CodDiag_fk=ci.codigo "+
        								"AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' "+
        								"AND ac.conv_numero_contrato=apac.conv_numero_contrato_fk "+
        								"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
        								"AND aad.estado=1 GROUP BY hde.codDiagnostico ORDER BY cantidad DESC LIMIT 15");
        	
        	}else{
        		
        		rs=st.executeQuery("SELECT ci.nombre, COUNT(hde.codDiagnostico) AS cantidad "+
						"FROM adm_admisiones aad,adm_censo_cama cama,hic_diagnosticoegreso hde,cie10 ci,adm_paciente apac,empresa emp, adm_convenio ac, adm_entidad ae "+
						"WHERE aad.adm_numero_ingreso=hde.codAdm "+
						"AND hde.TipDiag='EG' "+
						"AND apac.pac_codigo_paciente=aad.pac_codigo_paciente_fk "+
						"AND aad.cen_numero_cama_fk=cama.cen_numero_cama "+
						"AND hde.CodDiag_fk=ci.codigo "+
						"AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' "+
						"AND ac.conv_numero_contrato=apac.conv_numero_contrato_fk "+
						"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
						"AND ae.ent_nit='"+ftent+"'AND aad.estado=1 GROUP BY hde.codDiagnostico ORDER BY cantidad DESC LIMIT 15");
        	}
        	}
        catch(Exception ex){
        	System.out.println("Error en MetodoOpcionesAutorizadas>>BuscarPatologia "+ex);
        }	
        return rs;
    }
	
	
	
	public java.sql.ResultSet BuscarTraslados(String Fechai, String Fechaf, String ftent){	
		/**
		 * Consulta las entidades mas usadas en limite de 10
		 * 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        System.out.println("Fechai "+Fechai+"Fechaf "+Fechaf);
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(ftent.equals("todas")){
        	rs=st.executeQuery("SELECT acc.ubicacion_cama, ent.nombre_entidad, COUNT(acc.ubicacion_cama) "+
        						"FROM `adm_trasladourg_hosp` hosp,`adm_admisiones` adm, `adm_paciente` pac, `adm_convenio` con, `adm_entidad` ent, adm_censo_cama acc "+
        						"WHERE fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"' "+
        						"AND hosp.CodAdm = adm.`adm_numero_ingreso` "+
        						"AND adm.`pac_codigo_paciente_fk`= pac.`pac_codigo_paciente` "+
        						"AND `pac`.`conv_numero_contrato_fk`=con.conv_numero_contrato "+
        						"AND acc.cen_numero_cama=hosp.CodCamaFin "+
        						"AND con.ent_nit_contratante_fk=ent.ent_nit GROUP BY acc.ubicacion_cama ORDER BY acc.ubicacion_cama ");
        	}else{
        		
        		rs=st.executeQuery("SELECT acc.ubicacion_cama, ent.nombre_entidad, COUNT(acc.ubicacion_cama) "+
						"FROM `adm_trasladourg_hosp` hosp,`adm_admisiones` adm, `adm_paciente` pac, `adm_convenio` con, `adm_entidad` ent, adm_censo_cama acc "+
						"WHERE fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"' "+
						"AND hosp.CodAdm = adm.`adm_numero_ingreso` "+
						"AND adm.`pac_codigo_paciente_fk`= pac.`pac_codigo_paciente` "+
						"AND `pac`.`conv_numero_contrato_fk`=con.conv_numero_contrato "+
						"AND acc.cen_numero_cama=hosp.CodCamaFin "+
						"AND con.ent_nit_contratante_fk=ent.ent_nit AND ent.ent_nit='"+ftent+"' GROUP BY acc.ubicacion_cama ORDER BY acc.ubicacion_cama ");
        	}
        	
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoOpcionesAutorizadas>>BuscarTraslados "+ex);
        }	
        return rs;
    }
	


	public void insertarRelacionPermisoHC(String CodigoUsuario,String CodigoPermiso){
		OpcionesAutorizadas oa=new OpcionesAutorizadas();
		oa.setUsuario(CodigoUsuario);
		oa.setCodigoFormato(CodigoPermiso);
		try{
			PreparedStatement ps = null;
		    Conexion con=new Conexion();
		    ps=con.conn.prepareStatement("INSERT INTO hic_usuariopermisoshc (codigoUsu_fk,codigoPerhc_fk) VALUE (?,?)");				
			ps.setString(1, oa.getUsuario());
			ps.setString(2, oa.getCodigoFormato());
			ps.executeUpdate();
			ps.close();
			con.cerrar();
		}//fin del try
		catch(Exception ex){
			System.out.print("Error en MetodoOpcionesAutorizadas>>insertarRelacionPermisoHC "+ex);
		}//fin del catch
	}//fin insercion
	
	public void insertarRelacionFormatos(String CodigoUsuario,String CodigoFormato){
		OpcionesAutorizadas oa=new OpcionesAutorizadas();
		oa.setUsuario(CodigoUsuario);
		oa.setCodigoFormato(CodigoFormato);
		try{
			PreparedStatement ps = null;
		    Conexion con=new Conexion();
		    ps=con.conn.prepareStatement("insert into seg_usuariosformato (codigoUsuario_fk,codigoFormato_fk) value (?,?)");				
			ps.setString(1, oa.getUsuario());
			ps.setString(2, oa.getCodigoFormato());
			ps.executeUpdate();
			ps.close();
			con.cerrar();
		}//fin del try
		catch(Exception ex){
			System.out.println(ex);
		}//fin del catch
	}//fin insercion
	
public java.sql.ResultSet CambiarEstadoForm(String CodAdmision,String CodFormato,String CodPac,String usuario, String CodForPac){	
		
		java.sql.ResultSet rs=null;
	    Statement st = null;
	    try{
	    	Conexion con=new Conexion();
	       	st=con.conn.createStatement();
	    	st.executeUpdate("UPDATE hic_adm_formatos_pac SET estado=1 WHERE hic_adm_formatos_pac.codigo_adm_fk='"+CodAdmision+"' AND hic_adm_formatos_pac.codigo_for_fk='"+CodFormato+"' AND hic_adm_formatos_pac.codigo_pac_fk='"+CodPac+"' AND hic_adm_formatos_pac.codigo_usu_fk='"+usuario+"' AND hic_adm_formatos_pac.codigo='"+CodForPac+"'");
	    }
	    catch(Exception ex){
        	System.out.println("Error en MetodoOpcionesAutorizadas>>CambiarEstadoForm "+ex);
	    } return rs;
	   
	}
	
	public java.sql.ResultSet CambiarEstadoMen(String CodUsuario,String CodRemi,String CodMen){	
		
		java.sql.ResultSet rs=null;
	    Statement st = null;
	    try{
	    	Conexion con=new Conexion();
	       	st=con.conn.createStatement();
	    	st.executeUpdate("UPDATE seg_mensajes SET estado=2 WHERE seg_mensajes.cod_usuario='"+CodRemi+"' AND seg_mensajes.remitente='"+CodUsuario+"' AND seg_mensajes.codigo='"+CodMen+"'");
	    }
	    catch(Exception ex){
        	System.out.println("Error en MetodoOpcionesAutorizadas>>CambiarEstadoMen "+ex);
	    } return rs;
	   
	}
	public java.sql.ResultSet BuscarDatosForPend(String CodigoUsuario){	
		/**
		 * consulta tiene como parametro el codigo del usuario
		 * para buscar LOS DATOS DELOS FORMATOS PENDIENTES
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT hf.nombre_formato,CONCAT(ap.segundo_apellido,' ',ap.primer_apellido,' "
					+ "',ap.nombre) AS Nombre_Paciente, ap.pac_codigo_paciente, haf.codigo_adm_fk, haf.codigo_for_fk, "
					+ "haf.fecha, haf.hora, haf.codigo FROM hic_adm_formatos_pac haf, hic_formato hf,adm_paciente ap, "
					+ "seg_usuario su WHERE haf.codigo_pac_fk=ap.pac_codigo_paciente AND su.usu_codigo=haf.codigo_usu_fk  "
					+ "AND hf.codigo=haf.codigo_for_fk AND haf.estado = 0  AND haf.codigo_usu_fk =" + CodigoUsuario + "");
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoOpcionesAutorizadas>>BuscarPermisosMenu "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarEstadoFormato(String codigou){	
		/**
		 * consulta tiene como parametro el codigo del usuario
		 * para buscar cuales formatos se encuentran abiertos.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT COUNT(estado) FROM hic_adm_formatos_pac WHERE estado=0 AND codigo_usu_fk="+codigou+"");
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoOpcionesAutorizadas>>BuscarEstadoFormato "+ex);
        }	
        return rs;
    }
	
	public void BorrarFor(String CodFor){
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("delete from hic_adm_formatos_pac where codigo='"+CodFor+"'");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("Error en  MetodoOpcionesAutorizadas>>Borrar For "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public ResultSet listarRemi(String NomRemi) {
		/**
		 * se buscan todos los usuarios**/
		
		java.sql.ResultSet rs=null;
		Statement st=null;
		try{
			Conexion con=new Conexion();
			st=con.conn.createStatement();
			rs=st.executeQuery("SELECT sdt.nombre, sdt.apellido, su.usu_codigo FROM seg_datos_personales sdt, seg_usuario su WHERE sdt.nombre LIKE '"+NomRemi+"%' AND sdt.dat_codigo=su.dat_codigo_fk");
		}
		catch(Exception ex){
			ex.getMessage();
			System.out.println("Error  MetodoOpcionesAutorizadas>>ListarRemitentes "+ex);
		}
		return rs;
	}
	
	public java.sql.ResultSet ListaUrgPac(String CodUsuario){
		/**
		 * Consulta lista los pacientes activos de urgencia
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	
        	rs=st.executeQuery("SELECT DISTINCT ap.pac_codigo_paciente,ap.nombre,ap.primer_apellido,ap.segundo_apellido,ap.tipo_documento,ap.numero_documento,aad.fecha_registro,aad.hora_registro,acc.servicio FROM adm_paciente ap,adm_admisiones aad,adm_censo_cama acc WHERE ap.pac_codigo_paciente=aad.pac_codigo_paciente_fk AND aad.estado=0 AND aad.cen_numero_cama_fk=acc.cen_numero_cama AND acc.servicio=1   ORDER BY aad.fecha_registro,hora_registro ASC");
        		
        	//System.out.println("SELECT DISTINCT aad.adm_numero_ingreso,ap.nombre,ap.primer_apellido,ap.segundo_apellido,ap.tipo_documento,ap.numero_documento,aad.fecha_registro,aad.hora_registro,acc.servicio FROM adm_paciente ap,adm_admisiones aad,adm_censo_cama acc WHERE ap.pac_codigo_paciente=aad.pac_codigo_paciente_fk AND aad.estado=0 AND aad.atendido=0 AND aad.cen_numero_cama_fk=acc.cen_numero_cama AND acc.servicio=1   ORDER BY aad.fecha_registro,hora_registro ASC");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoOpcionesAutorizadas>>ListaUrgPac"+ex);
        }	
        return rs;
	}
	
	public java.sql.ResultSet val24h(String fecha, String hora){
		/**
		 * Consulta valida cuenta las horas 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	
        	rs=st.executeQuery("SELECT TIMESTAMPDIFF(HOUR,('"+fecha+"''"+hora+"'),TIMESTAMP(CURDATE(),CURTIME()))");
        	
        	//System.out.println("SELECT TIMESTAMPDIFF(HOUR,('"+fecha+"''"+hora+"'),TIMESTAMP(CURDATE(),CURTIME()))");
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoOpcionesAutorizadas>>ListaUrgPac"+ex);
        }	
        return rs;
	}
	
	
	public java.sql.ResultSet validaradmi(String pacientes){
		/**
		 * Consulta valida cuenta las horas 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	
        	rs=st.executeQuery("select estado, adm_numero_ingreso from adm_admisiones where pac_codigo_paciente_fk='"+pacientes+"'");
        	
        	//System.out.println("SELECT TIMESTAMPDIFF(HOUR,('"+fecha+"''"+hora+"'),TIMESTAMP(CURDATE(),CURTIME()))");
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoOpcionesAutorizadas>>ListaUrgPac"+ex);
        }	
        return rs;
	}
	
public java.sql.ResultSet reportefactrad(String Fechai, String Fechaf,String ftent){
		/**
		 * consulta tiene como parametro las fecha inicial y final ,
		 * para traer el reporte de facturas.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(ftent.equals("1")){
        	rs=st.executeQuery("SELECT CONCAT('RAD0', ffr.consRadicado) AS CuentaCobro, "+
					"fn.consecutivo,ffr.valor AS Valortotal, "+
					"ffe.fecha AS fechaEnvio,CONCAT(sdt.nombre, ' ', sdt.apellido) usuario , "+
					"CONCAT(p.nombre,' ',p.primer_apellido,' ',p.segundo_apellido) paciente, "+
                    "CONCAT(p.tipo_documento,' ',p.numero_documento) documento, ffr.fecha as fechainsercion, "+
					"fef.fecha_ingreso AS 'f.ingreso',fef.fecha_egreso  AS 'f.egreso', "+
					"fef.valor AS valorfactura,ae.nombre_entidad,ffr.valorLetra,ffra.fechaRadicado ,fn.fecha "+
                    "FROM  fact_numeradas fn, "+
					"fact_enc_factura fef, "+
                    "adm_entidad ae, "+
					"adm_paciente p, "+
					"adm_admisiones adm, "+
					"fact_factradicadas ffr, "+
					"fact_facturas_radicadas ffra, fact_facturas_enviadas ffenv, fact_factenviadas ffe , "+
					"seg_usuario su, "+
					"seg_datos_personales sdt,empresa emp "+
					"WHERE ffra.fechaRadicado between '"+Fechai+"' and '"+Fechaf+"' and ffenv.consEnvio=ffe.consEnvio and ffenv.codFact=fn.cod_fact_numerada "+
					"AND ffr.consRadicado = ffra.consRadicado "+
					"AND ffra.codFact = fn.cod_fact_numerada "+
					"AND fn.cod_enc_fact_fk = fef.cod_enc_factura "+ 
					"AND fef.cod_admision = adm.adm_numero_ingreso "+
					"AND adm.pac_codigo_paciente_fk = p.pac_codigo_paciente "+
					"AND fef.cod_eps = ae.ent_nit "+
					"AND ffr.cod_usuario = su.usu_codigo   "+      			       
					"AND su.dat_codigo_fk = sdt.dat_codigo order by ffr.consRadicado");
        	
        	System.out.println("SELECT CONCAT('RAD0', ffr.consRadicado) AS CuentaCobro, "+
					"fn.consecutivo,ffr.valor AS Valortotal, "+
					"ffe.fecha AS fechaEnvio,CONCAT(sdt.nombre, ' ', sdt.apellido) usuario , "+
					"CONCAT(p.nombre,' ',p.primer_apellido,' ',p.segundo_apellido) paciente, "+
                    "CONCAT(p.tipo_documento,' ',p.numero_documento) documento, ffr.fecha as fechainsercion, "+
					"fef.fecha_ingreso AS 'f.ingreso',fef.fecha_egreso  AS 'f.egreso', "+
					"fef.valor AS valorfactura,ae.nombre_entidad,ffr.valorLetra,ffra.fechaRadicado ,fn.fecha "+
                    "FROM  fact_numeradas fn, "+
					"fact_enc_factura fef, "+
                    "adm_entidad ae, "+
					"adm_paciente p, "+
					"adm_admisiones adm, "+
					"fact_factradicadas ffr, "+
					"fact_facturas_radicadas ffra, fact_facturas_enviadas ffenv, fact_factenviadas ffe , "+
					"seg_usuario su, "+
					"seg_datos_personales sdt,empresa emp "+
					"WHERE ffra.fechaRadicado between '"+Fechai+"' and '"+Fechaf+"' and ffenv.consEnvio=ffe.consEnvio and ffenv.codFact=fn.cod_fact_numerada "+
					"AND ffr.consRadicado = ffra.consRadicado "+
					"AND ffra.codFact = fn.cod_fact_numerada "+
					"AND fn.cod_enc_fact_fk = fef.cod_enc_factura "+ 
					"AND fef.cod_admision = adm.adm_numero_ingreso "+
					"AND adm.pac_codigo_paciente_fk = p.pac_codigo_paciente "+
					"AND fef.cod_eps = ae.ent_nit "+
					"AND ffr.cod_usuario = su.usu_codigo   "+      			       
					"AND su.dat_codigo_fk = sdt.dat_codigo order by ffr.consRadicado");
        	//System.out.println("SELECT n.consecutivo, ef.razon_social, ef.fecha, (ef.valor+ef.efectivo) AS valor , (ef.valor+ef.efectivo) AS valorFactura,ef.efectivo AS valorUsuario,(ef.valor) AS valorTotal,'FACTURADA', ef.nombre_paciente, u.usuario FROM fact_numeradas n, fact_enc_factura ef, seg_usuario u WHERE n.cod_enc_fact_fk=ef.cod_enc_factura AND n.estadoFact!=5 and ef.cod_usuario_fk=u.usu_codigo AND ef.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"'");
        	}else{
        		rs=st.executeQuery("SELECT CONCAT('RAD0', ffr.consRadicado) AS CuentaCobro, "+
        							"fn.consecutivo,ffr.valor AS Valortotal, "+
        							"ffe.fecha AS fechaEnvio,CONCAT(sdt.nombre, ' ', sdt.apellido) usuario , "+
        							"CONCAT(p.nombre,' ',p.primer_apellido,' ',p.segundo_apellido) paciente, "+
                                    "CONCAT(p.tipo_documento,' ',p.numero_documento) documento,ffr.fecha as fechainsercion, "+
        							"fef.fecha_ingreso AS 'f.ingreso',fef.fecha_egreso  AS 'f.egreso', "+
        							"fef.valor AS valorfactura,ae.nombre_entidad,ffr.valorLetra,ffra.fechaRadicado, fn.fecha "+
                                    "FROM  fact_numeradas fn, "+
        							"fact_enc_factura fef, "+
                                    "adm_entidad ae, "+
        							"adm_paciente p, "+
        							"adm_admisiones adm, "+
        							"fact_factradicadas ffr, "+
        							"fact_facturas_radicadas ffra , fact_facturas_enviadas ffenv, fact_factenviadas ffe , "+
        							"seg_usuario su, "+
        							"seg_datos_personales sdt,empresa emp "+
        							"WHERE ffra.fechaRadicado between '"+Fechai+"' and '"+Fechaf+"' and ffenv.consEnvio=ffe.consEnvio and ffenv.codFact=fn.cod_fact_numerada "+
        							"and fef.cod_eps='"+ftent+"' "+
        							"AND ffr.consRadicado = ffra.consRadicado "+
        							"AND ffra.codFact = fn.cod_fact_numerada "+
        							"AND fn.cod_enc_fact_fk = fef.cod_enc_factura "+ 
        							"AND fef.cod_admision = adm.adm_numero_ingreso "+
        							"AND adm.pac_codigo_paciente_fk = p.pac_codigo_paciente "+
        							"AND fef.cod_eps = ae.ent_nit "+
        							"AND ffr.cod_usuario = su.usu_codigo   "+      			       
        							"AND su.dat_codigo_fk = sdt.dat_codigo order by ffr.consRadicado ");
        		System.out.println("valor de entidad"+ftent);	
        		System.out.println("SELECT CONCAT('RAD0', ffr.consRadicado) AS CuentaCobro, "+
						"fn.consecutivo,ffr.valor AS Valortotal, "+
						"ffe.fecha AS fechaEnvio,CONCAT(sdt.nombre, ' ', sdt.apellido) usuario , "+
						"CONCAT(p.nombre,' ',p.primer_apellido,' ',p.segundo_apellido) paciente, "+
                        "CONCAT(p.tipo_documento,' ',p.numero_documento) documento,ffr.fecha as fechainsercion, "+
						"fef.fecha_ingreso AS 'f.ingreso',fef.fecha_egreso  AS 'f.egreso', "+
						"fef.valor AS valorfactura,ae.nombre_entidad,ffr.valorLetra,ffra.fechaRadicado, fn.fecha "+
                        "FROM  fact_numeradas fn, "+
						"fact_enc_factura fef, "+
                        "adm_entidad ae, "+
						"adm_paciente p, "+
						"adm_admisiones adm, "+
						"fact_factradicadas ffr, "+
						"fact_facturas_radicadas ffra , fact_facturas_enviadas ffenv, fact_factenviadas ffe , "+
						"seg_usuario su, "+
						"seg_datos_personales sdt,empresa emp "+
						"WHERE ffra.fechaRadicado between '"+Fechai+"' and '"+Fechaf+"' and ffenv.consEnvio=ffe.consEnvio and ffenv.codFact=fn.cod_fact_numerada "+
						"and fef.cod_eps='"+ftent+"' "+
						"AND ffr.consRadicado = ffra.consRadicado "+
						"AND ffra.codFact = fn.cod_fact_numerada "+
						"AND fn.cod_enc_fact_fk = fef.cod_enc_factura "+ 
						"AND fef.cod_admision = adm.adm_numero_ingreso "+
						"AND adm.pac_codigo_paciente_fk = p.pac_codigo_paciente "+
						"AND fef.cod_eps = ae.ent_nit "+
						"AND ffr.cod_usuario = su.usu_codigo   "+      			       
						"AND su.dat_codigo_fk = sdt.dat_codigo order by ffr.consRadicado ");
        	}
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoOpcionesAutorizadas>>reportefactrad"+ex);
        }	
        return rs;
	}

	
	public void guardareg24h(String CodUsuario,String CodPaciente, Date Fecha, Time Hora){	
		/**
		 * Guardar mensaje en la base de datos */
		registro24h r24h=new registro24h();
		
		r24h.setCodUsuario(CodUsuario);
		r24h.setCodPaciente(CodPaciente);
		r24h.setfecha(Fecha);
		r24h.sethora(Hora);
		
        try{
        	
        	PreparedStatement ps = null;
        	Conexion con=new Conexion();
        	ps = con.conn.prepareStatement("INSERT INTO reg_pac_24h (cod_usu_fk,cod_admision,fecha_lectura,hora_lectura) VALUES (?,?,?,?)");
        	ps.setString(1,r24h.getCodUsuario());
        	ps.setString(2,r24h.getCodPaciente());
        	ps.setDate(3,r24h.getfecha());
        	ps.setTime(4,r24h.gethora());
        	ps.executeUpdate();
			ps.close();
			con.cerrar();
			
			System.out.println("CoduUsuario"+CodUsuario);
			System.out.println("CodPaciente"+CodPaciente);
        }
        catch(Exception ex){
        	System.out.println("CoduUsuario"+CodUsuario);
			System.out.println("CodPaciente"+CodPaciente);
        	System.out.println("Error en MetodoOpcionesAutorizadas>>guardareg24h "+ex);
        }	
       
    }
	
	public java.sql.ResultSet reportefact(String Fechai, String Fechaf,String ftent){
		/**
		 * consulta tiene como parametro las fecha inicial y final ,
		 * para traer el reporte de facturas.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(ftent.equals("1")){
        	rs=st.executeQuery("SELECT n.consecutivo, cce.NombreCuenta, n.fecha, (ef.valor+ef.efectivo) AS valor , (ef.valor+ef.efectivo) AS valorFactura,ef.efectivo AS valorUsuario,(ef.valor) AS valorTotal,n.estadoFact,ef.fecha_ingreso, ef.fecha_egreso, ef.nombre_paciente, u.usuario, ef.cod_enc_factura FROM fact_numeradas n, fact_enc_factura ef, seg_usuario u, cont_factura cf,cont_cuentas_empresas cce WHERE n.cod_enc_fact_fk=ef.cod_enc_factura and ef.cod_usuario_fk=u.usu_codigo  AND cf.numero_factura=n.consecutivo AND cf.cod_cuenta_fk=cce.codigo AND n.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ORDER BY n.consecutivo");
        	//System.out.println("SELECT n.consecutivo, ef.razon_social, ef.fecha, (ef.valor+ef.efectivo) AS valor , (ef.valor+ef.efectivo) AS valorFactura,ef.efectivo AS valorUsuario,(ef.valor) AS valorTotal,'FACTURADA', ef.nombre_paciente, u.usuario FROM fact_numeradas n, fact_enc_factura ef, seg_usuario u WHERE n.cod_enc_fact_fk=ef.cod_enc_factura AND n.estadoFact!=5 and ef.cod_usuario_fk=u.usu_codigo AND ef.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"'");
        	}else{
        		rs=st.executeQuery("SELECT n.consecutivo, cce.NombreCuenta, n.fecha, (ef.valor+ef.efectivo) AS valor , (ef.valor+ef.efectivo) AS valorFactura,ef.efectivo AS valorUsuario,(ef.valor) AS valorTotal,n.estadoFact, ef.fecha_ingreso, ef.fecha_egreso, ef.nombre_paciente, u.usuario, ef.cod_enc_factura FROM fact_numeradas n, fact_enc_factura ef, seg_usuario u, cont_factura cf,cont_cuentas_empresas cce WHERE n.cod_enc_fact_fk=ef.cod_enc_factura and ef.cod_usuario_fk=u.usu_codigo  AND cf.numero_factura=n.consecutivo AND cf.cod_cuenta_fk=cce.codigo AND n.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"' and cce.nit='"+ftent+"' ORDER BY n.consecutivo");
        		System.out.println("valor de entidad"+ftent);	
        	}
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoOpcionesAutorizadas>>reportefact"+ex);
        }	
        return rs;
	}
	
	
	public java.sql.ResultSet reportefactnc(String Fechai, String Fechaf, String ftent){
		/**
		 * consulta tiene como parametro las fecha inicial y final ,
		 * para traer el reporte de notas creditos.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        System.out.println("reportefactnc"+ftent);
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(ftent.equals("1")){
        	//System.out.println("SELECT n.consecutivo, ef.razon_social, ef.fecha, (ef.valor+ef.efectivo) AS valor , (ef.valor+ef.efectivo) AS valorFactura,ef.efectivo AS valorUsuario,(ef.valor) AS valorTotal,'FACTURADA', ef.nombre_paciente, u.usuario FROM fact_numeradas n, fact_enc_factura ef, seg_usuario u WHERE n.cod_enc_fact_fk=ef.cod_enc_factura AND n.estadoFact!=5 and ef.cod_usuario_fk=u.usu_codigo AND ef.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"'");
        	rs=st.executeQuery("SELECT n.consecutivo, ef.razon_social, n.fecha, (ef.valor+ef.efectivo) AS valorFactura, ef.efectivo AS valorUsuario,ef.valor AS valorTotal, cdf.cantidad, (ef.valor-cdf.cantidad) AS ValorConNC, cdf.observacion  ,  n.estadoFact, ef.nombre_paciente, u.usuario,cdf.usuario "+
        			"FROM fact_numeradas n, fact_enc_factura ef, seg_usuario u, cont_detalle_factura cdf, cont_complemento_factura ccf, cont_factura cf "+
        			"WHERE n.cod_enc_fact_fk=ef.cod_enc_factura "+
        			"AND ef.cod_usuario_fk=u.usu_codigo " + 
        			"AND ccf.cod_det_factura_fk=cdf.codigo "+
        			"AND ccf.cod_factura_fk=cf.codigo "+
        			"AND n.consecutivo=cf.numero_factura AND n.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"'"); 
        	System.out.println("reportefactnc"+ftent);
        	}else{
        		rs=st.executeQuery("SELECT n.consecutivo, ef.razon_social, n.fecha, (ef.valor+ef.efectivo) AS valorFactura, ef.efectivo AS valorUsuario,ef.valor AS valorTotal, cdf.cantidad, (ef.valor-cdf.cantidad) AS ValorConNC, cdf.observacion ,  n.estadoFact, ef.nombre_paciente, u.usuario,cdf.usuario "+
        				"FROM fact_numeradas n, fact_enc_factura ef, seg_usuario u, cont_detalle_factura cdf, cont_complemento_factura ccf, cont_factura cf "+
        				"WHERE n.cod_enc_fact_fk=ef.cod_enc_factura "+
        				"AND ef.cod_usuario_fk=u.usu_codigo "+
        				"AND ccf.cod_det_factura_fk=cdf.codigo "+ 
        				"AND ccf.cod_factura_fk=cf.codigo "+
        				"AND n.consecutivo=cf.numero_factura AND n.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"' AND ef.cod_eps='"+ftent+"'"); 
        	System.out.println("reportefactnc"+ftent);
        	}
        }catch(Exception ex){
        	System.out.println("Error en MetodoOpcionesAutorizadas>>reportefactNC"+ex);
        }	
        return rs;
	}
	
	public java.sql.ResultSet reportefactanu(String Fechai, String Fechaf,String ftent){
		/**
		 * consulta tiene como parametro las fecha inicial y final ,
		 * para traer el reporte de facturas.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(ftent.equals("1")){
        	rs=st.executeQuery("SELECT n.consecutivo, cce.NombreCuenta, n.fecha, (ef.valor+ef.efectivo) AS valor , (ef.valor+ef.efectivo) AS valorFactura,ef.efectivo AS valorUsuario,(ef.valor) AS valorTotal,'ANULADA', ef.nombre_paciente, u.usuario,  fmv.usuario FROM fact_numeradas n, fact_enc_factura ef, seg_usuario u,fact_movfacturas fmv,cont_factura cf,cont_cuentas_empresas cce WHERE n.cod_enc_fact_fk=ef.cod_enc_factura AND n.estadoFact=5 AND fmv.codFactNumerada=n.cod_fact_numerada and fmv.estadoFact=5  AND cf.numero_factura=n.consecutivo AND cf.cod_cuenta_fk=cce.codigo and ef.cod_usuario_fk=u.usu_codigo AND n.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"'");
        	//System.out.println("SELECT n.consecutivo, ef.razon_social, ef.fecha, (ef.valor+ef.efectivo) AS valor , (ef.valor+ef.efectivo) AS valorFactura,ef.efectivo AS valorUsuario,(ef.valor) AS valorTotal,'FACTURADA', ef.nombre_paciente, u.usuario FROM fact_numeradas n, fact_enc_factura ef, seg_usuario u WHERE n.cod_enc_fact_fk=ef.cod_enc_factura AND n.estadoFact!=5 and ef.cod_usuario_fk=u.usu_codigo AND ef.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"'");
        	}else{
        		rs=st.executeQuery("SELECT n.consecutivo, cce.NombreCuenta, n.fecha, (ef.valor+ef.efectivo) AS valor , (ef.valor+ef.efectivo) AS valorFactura,ef.efectivo AS valorUsuario,(ef.valor) AS valorTotal,'ANULADA', ef.nombre_paciente, u.usuario,  fmv.usuario FROM fact_numeradas n, fact_enc_factura ef, seg_usuario u,fact_movfacturas fmv,cont_factura cf,cont_cuentas_empresas cce WHERE n.cod_enc_fact_fk=ef.cod_enc_factura AND n.estadoFact=5 AND fmv.codFactNumerada=n.cod_fact_numerada and fmv.estadoFact=5  AND cf.numero_factura=n.consecutivo AND cf.cod_cuenta_fk=cce.codigo and ef.cod_usuario_fk=u.usu_codigo AND n.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"' and cce.nit='"+ftent+"'");
        	}
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoOpcionesAutorizadas>>reportefact"+ex);
        }	
        return rs;
	}
	
	
	public java.sql.ResultSet reportencAnu(String Fechai, String Fechaf, String ftent){
		/**
		 * consulta tiene como parametro las fecha inicial y final ,
		 * para traer el reporte de notas creditos.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(ftent.equals("1")){
        	//System.out.println("SELECT n.consecutivo, ef.razon_social, ef.fecha, (ef.valor+ef.efectivo) AS valor , (ef.valor+ef.efectivo) AS valorFactura,ef.efectivo AS valorUsuario,(ef.valor) AS valorTotal,'FACTURADA', ef.nombre_paciente, u.usuario FROM fact_numeradas n, fact_enc_factura ef, seg_usuario u WHERE n.cod_enc_fact_fk=ef.cod_enc_factura AND n.estadoFact!=5 and ef.cod_usuario_fk=u.usu_codigo AND ef.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"'");
        	rs=st.executeQuery("SELECT ccf.codigo, cc.NombreCuenta,concat(cdf.fecha_insercion,'/',cdf.hora_insercion) as FechaInsercion,concat(sdp.nombre,' ',sdp.apellido) usuario,cf.numero_factura,cdf.cantidad,cdf.observacion,cmc.descripcion, cf.precio_factura " +
        			"FROM cont_cuentas_empresas cc,cont_movimientos_credito cmc,cont_complemento_factura ccf,cont_factura cf,cont_detalle_factura cdf,seg_usuario su,seg_datos_personales sdp " +
        			"WHERE ccf.cod_det_factura_fk=cdf.codigo AND ccf.cod_factura_fk=cf.codigo " +
        	"and su.usu_codigo=cdf.usuario " +
        	"and su.dat_codigo_fk=sdp.dat_codigo " +
        	"and cmc.codigo=ccf.tipo_pago " +
        	"and cc.codigo=cf.cod_cuenta_fk " +
        	"AND ccf.tipo_pago!=0 " +
        	"and cdf.fecha_insercion between '"+Fechai+"' and '"+Fechaf+"' AND cdf.observacion LIKE '%NOTA CREDITO ANULADA%'"); 
        	}else{
        		rs=st.executeQuery("SELECT ccf.codigo, cc.NombreCuenta,concat(cdf.fecha_insercion,'/',cdf.hora_insercion) as FechaInsercion,concat(sdp.nombre,' ',sdp.apellido) usuario,cf.numero_factura,cdf.cantidad,cdf.observacion,cmc.descripcion, cf.precio_factura " +
            			"FROM cont_cuentas_empresas cc,cont_movimientos_credito cmc,cont_complemento_factura ccf,cont_factura cf,cont_detalle_factura cdf,seg_usuario su,seg_datos_personales sdp " +
            			"WHERE ccf.cod_det_factura_fk=cdf.codigo AND ccf.cod_factura_fk=cf.codigo " +
            	"and su.usu_codigo=cdf.usuario " +
            	"and su.dat_codigo_fk=sdp.dat_codigo " +
            	"and cmc.codigo=ccf.tipo_pago " +
            	"and cc.codigo=cf.cod_cuenta_fk " +
            	"AND ccf.tipo_pago!=0 " +
            	"and cdf.fecha_insercion between '"+Fechai+"' and '"+Fechaf+"' and cc.nit='"+ftent+"' AND cdf.observacion LIKE '%NOTA CREDITO ANULADA%'"); 
        	System.out.println("valor de ent nc anulada"+ftent);
        	}
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoOpcionesAutorizadas>>reporteNC"+ex);
        }	
        return rs;
	}
	
	public java.sql.ResultSet reportercAnu(String Fechai, String Fechaf,String ftent){
		/**
		 * consulta tiene como parametro las fecha inicial y final ,
		 * para traer el reporte de facturas.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(ftent.equals("1")){
        	//System.out.println("SELECT n.consecutivo, ef.razon_social, ef.fecha, (ef.valor+ef.efectivo) AS valor , (ef.valor+ef.efectivo) AS valorFactura,ef.efectivo AS valorUsuario,(ef.valor) AS valorTotal,'FACTURADA', ef.nombre_paciente, u.usuario FROM fact_numeradas n, fact_enc_factura ef, seg_usuario u WHERE n.cod_enc_fact_fk=ef.cod_enc_factura AND n.estadoFact!=5 and ef.cod_usuario_fk=u.usu_codigo AND ef.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"'");
        	rs=st.executeQuery("SELECT DISTINCT crc.codigo rec_caja," +
        						"aent.nombre_entidad, ctc.Concepto, " +
        						"CONCAT(sdt.nombre, ' ', sdt.apellido) AS usuario, " +
        						"CONCAT(crc.fecha, '/', crc.hora) AS fecha_insercion ,crc.FechaPago, crc.obsevacion, crc.valor_total " +
        						" FROM  cont_recibo_caja crc, " +
								" adm_entidad aent, " +
								"  seg_usuario su, " +
								"  seg_datos_personales sdt, " +
								"  cont_detalle_recibo_caja cdr, " +
								"  cont_tipo_concepto ctc " +
								" WHERE crc.cod_entidad_fk = aent.ent_nit " +
								"  AND crc.concepto = ctc.codigo " +
								"  AND su.usu_codigo = crc.cod_usu_fk " +
								"  AND sdt.dat_codigo = su.dat_codigo_fk " +
								"  AND crc.codigo = cdr.cod_recibo_caja_fk " +
								    "and crc.FechaPago between '"+Fechai+"' and '"+Fechaf+"' AND crc.obsevacion LIKE '%RECIBO DE CAJA ANULADO%'"); 
        }else{
        	
        	rs=st.executeQuery("SELECT DISTINCT crc.codigo rec_caja," +
					"aent.nombre_entidad, ctc.Concepto, " +
					"CONCAT(sdt.nombre, ' ', sdt.apellido) AS usuario, " +
					"CONCAT(crc.fecha, '/', crc.hora) AS fecha_insercion ,crc.FechaPago,crc.obsevacion, crc.valor_total " +
					" FROM  cont_recibo_caja crc, " +
					" adm_entidad aent, " +
					"  seg_usuario su, " +
					"  seg_datos_personales sdt, " +
					"  cont_detalle_recibo_caja cdr, " +
					"  cont_tipo_concepto ctc " +
					" WHERE crc.cod_entidad_fk = aent.ent_nit " +
					"  AND crc.concepto = ctc.codigo " +
					"  AND su.usu_codigo = crc.cod_usu_fk " +
					"  AND sdt.dat_codigo = su.dat_codigo_fk " +
					"  AND crc.codigo = cdr.cod_recibo_caja_fk " +
					"and crc.FechaPago between '"+Fechai+"' and '"+Fechaf+"'  AND aent.ent_nit='"+ftent+"' and crc.obsevacion LIKE '%RECIBO DE CAJA ANULADO%'"); 
        }
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoOpcionesAutorizadas>>reportercAnu"+ex);
        }	
        return rs;
	}
	
	
	public java.sql.ResultSet reportenc(String Fechai, String Fechaf, String ftent){
		/**
		 * consulta tiene como parametro las fecha inicial y final ,
		 * para traer el reporte de notas creditos.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(ftent.equals("1")){
        	//System.out.println("SELECT n.consecutivo, ef.razon_social, ef.fecha, (ef.valor+ef.efectivo) AS valor , (ef.valor+ef.efectivo) AS valorFactura,ef.efectivo AS valorUsuario,(ef.valor) AS valorTotal,'FACTURADA', ef.nombre_paciente, u.usuario FROM fact_numeradas n, fact_enc_factura ef, seg_usuario u WHERE n.cod_enc_fact_fk=ef.cod_enc_factura AND n.estadoFact!=5 and ef.cod_usuario_fk=u.usu_codigo AND ef.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"'");
        	rs=st.executeQuery("SELECT ccf.codigo, cc.NombreCuenta,concat(cdf.fecha_insercion,'/',cdf.hora_insercion) as FechaInsercion,concat(sdp.nombre,' ',sdp.apellido) usuario,cf.numero_factura,cdf.cantidad,cdf.observacion,cmc.descripcion, cf.precio_factura " +
        			"FROM cont_cuentas_empresas cc,cont_movimientos_credito cmc,cont_complemento_factura ccf,cont_factura cf,cont_detalle_factura cdf,seg_usuario su,seg_datos_personales sdp " +
        			"WHERE ccf.cod_det_factura_fk=cdf.codigo AND ccf.cod_factura_fk=cf.codigo " +
        	"and su.usu_codigo=cdf.usuario " +
        	"and su.dat_codigo_fk=sdp.dat_codigo " +
        	"and cmc.codigo=ccf.tipo_pago " +
        	"and cc.codigo=cf.cod_cuenta_fk " +
        	"AND ccf.tipo_pago!=0 " +
        	"and cdf.fecha_insercion between '"+Fechai+"' and '"+Fechaf+"' AND cdf.observacion NOT LIKE '%NOTA CREDITO ANULADA%'"); 
        	}else{
        		rs=st.executeQuery("SELECT ccf.codigo, cc.NombreCuenta,concat(cdf.fecha_insercion,'/',cdf.hora_insercion) as FechaInsercion,concat(sdp.nombre,' ',sdp.apellido) usuario,cf.numero_factura,cdf.cantidad,cdf.observacion,cmc.descripcion, cf.precio_factura " +
            			"FROM cont_cuentas_empresas cc,cont_movimientos_credito cmc,cont_complemento_factura ccf,cont_factura cf,cont_detalle_factura cdf,seg_usuario su,seg_datos_personales sdp " +
            			"WHERE ccf.cod_det_factura_fk=cdf.codigo AND ccf.cod_factura_fk=cf.codigo " +
            	"and su.usu_codigo=cdf.usuario " +
            	"and su.dat_codigo_fk=sdp.dat_codigo " +
            	"and cmc.codigo=ccf.tipo_pago " +
            	"and cc.codigo=cf.cod_cuenta_fk " +
            	"AND ccf.tipo_pago!=0 " +
            	"and cdf.fecha_insercion between '"+Fechai+"' and '"+Fechaf+"' and cc.nit='"+ftent+"' AND cdf.observacion NOT LIKE '%NOTA CREDITO ANULADA%'"); 
        	System.out.println("valor en nc"+ftent);
        	}
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoOpcionesAutorizadas>>reporteNC"+ex);
        }	
        return rs;
	}
	
	
	public java.sql.ResultSet reporterc(String Fechai, String Fechaf,String ftent){
		/**
		 * consulta tiene como parametro las fecha inicial y final ,
		 * para traer el reporte de facturas.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(ftent.equals("1")){
        	//System.out.println("SELECT n.consecutivo, ef.razon_social, ef.fecha, (ef.valor+ef.efectivo) AS valor , (ef.valor+ef.efectivo) AS valorFactura,ef.efectivo AS valorUsuario,(ef.valor) AS valorTotal,'FACTURADA', ef.nombre_paciente, u.usuario FROM fact_numeradas n, fact_enc_factura ef, seg_usuario u WHERE n.cod_enc_fact_fk=ef.cod_enc_factura AND n.estadoFact!=5 and ef.cod_usuario_fk=u.usu_codigo AND ef.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"'");
        	rs=st.executeQuery("SELECT DISTINCT crc.codigo rec_caja," +
        						"aent.nombre_entidad, ctc.Concepto, " +
        						"CONCAT(sdt.nombre, ' ', sdt.apellido) AS usuario, " +
        						"CONCAT(crc.fecha, '/', crc.hora) AS fecha_insercion ,crc.FechaPago,crc.obsevacion, crc.valor_total " +
        						" FROM  cont_recibo_caja crc, " +
								" adm_entidad aent, " +
								"  seg_usuario su, " +
								"  seg_datos_personales sdt, " +
								"  cont_detalle_recibo_caja cdr, " +
								"  cont_tipo_concepto ctc " +
								" WHERE crc.cod_entidad_fk = aent.ent_nit " +
								"  AND crc.concepto = ctc.codigo " +
								"  AND su.usu_codigo = crc.cod_usu_fk " +
								"  AND sdt.dat_codigo = su.dat_codigo_fk " +
								"  AND crc.codigo = cdr.cod_recibo_caja_fk " +
								        	"and crc.FechaPago between '"+Fechai+"' and '"+Fechaf+"' and crc.obsevacion NOT LIKE '%RECIBO DE CAJA ANULADO%'"); 
        	}else{
        		rs=st.executeQuery("SELECT DISTINCT crc.codigo rec_caja," +
						"aent.nombre_entidad, ctc.Concepto, " +
						"CONCAT(sdt.nombre, ' ', sdt.apellido) AS usuario, " +
						"CONCAT(crc.fecha, '/', crc.hora) AS fecha_insercion ,crc.FechaPago,crc.obsevacion, crc.valor_total " +
						" FROM  cont_recibo_caja crc, " +
						" adm_entidad aent, " +
						"  seg_usuario su, " +
						"  seg_datos_personales sdt, " +
						"  cont_detalle_recibo_caja cdr, " +
						"  cont_tipo_concepto ctc " +
						" WHERE crc.cod_entidad_fk = aent.ent_nit " +
						"  AND crc.concepto = ctc.codigo " +
						"  AND su.usu_codigo = crc.cod_usu_fk " +
						"  AND sdt.dat_codigo = su.dat_codigo_fk " +
						"  AND crc.codigo = cdr.cod_recibo_caja_fk " +
						        	"and crc.FechaPago between '"+Fechai+"' and '"+Fechaf+"' and aent.ent_nit='"+ftent+"' and crc.obsevacion NOT LIKE '%RECIBO DE CAJA ANULADO%'"); 
        	}
        	
        	}        	
        catch(Exception ex){
        	System.out.println("Error en MetodoOpcionesAutorizadas>>reporteRC"+ex);
        }	
        return rs;
	}
	
	public java.sql.ResultSet BuscarContenidoMensaje(String CodUsuario, String CodRemi, String CodMen){	
		/**
		 * consulta tiene como parametro el codigo del usuario, codigo del remitente y el codigo del mensaje
		 * y mostrar el mensaje del usuario.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT CONCAT(sdt.nombre,' ',sdt.apellido) AS NOMBREREMI ,sg.asunto, sg.mensaje, sg.fechaenvio, sg.horaenvio FROM seg_mensajes sg, seg_datos_personales sdt, seg_usuario su WHERE sg.codigo='"+CodMen+"' AND su.usu_codigo='"+CodRemi+"' AND su.dat_codigo_fk=sdt.dat_codigo");
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoOpcionesAutorizadas>>BuscarContenidoMensaje "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ListarUsuarios(String codigou){	
		/**
		 * consulta tiene como parametro el codigo del usuario,
		 * para buscar los usuarios que tiene el usuario.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select usuario, usu_codigo from seg_usuario where usu_codigo!='"+codigou+"' and estado=1");
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoOpcionesAutorizadas>>ListarUsuarios "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet LlenarRemi(String CodRemi){	
		/**
		 * consulta tiene como parametro el codigo del Remitente,
		 * para buscar el nombre y colocarlo en txtRemi.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT CONCAT(sdt.nombre,' ',sdt.apellido) AS Nombre_Remi, su.usu_codigo FROM seg_datos_personales sdt, seg_usuario su WHERE su.usu_codigo="+CodRemi+" AND sdt.dat_codigo=su.dat_codigo_fk");
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoOpcionesAutorizadas>>LlenarRemi "+ex);
        }	
        return rs;
    }
	

	
	public java.sql.ResultSet ListarMensajes(String codigou){	
		/**
		 * consulta tiene como parametro el codigo del usuario,
		 * para buscar los mensajes que tiene el usuario.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select su.usuario, sm.mensaje, sm.asunto, sm.cod_usuario,sm.codigo,sm.fechaenvio,sm.horaenvio from seg_mensajes sm, seg_usuario su where sm.remitente="+codigou+" and sm.cod_usuario=su.usu_codigo AND sm.estado=0  order by sm.fechaenvio desc , sm.horaenvio desc ");
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoOpcionesAutorizadas>>ListarMensajes "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ListarMensajesLeidos(String codigou){	
		/**
		 * consulta tiene como parametro el codigo del usuario,
		 * para buscar los mensajes que tiene el usuario.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select su.usuario, sm.mensaje, sm.asunto, sm.cod_usuario,sm.codigo,sm.fechaenvio,sm.horaenvio from seg_mensajes sm, seg_usuario su where sm.remitente="+codigou+" and sm.cod_usuario=su.usu_codigo AND sm.estado=2 order by sm.fechaenvio desc ,sm.horaenvio desc");
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoOpcionesAutorizadas>>ListarMensajes "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet EliminarMens(String CodUsuario,String CodRemi, String CodMen){	
		/**
		 * consulta tiene como parametro el codigo del usuario, codigo del remitente, codigo del mensaje
		 * para buscar el mensaje seleccionad y eliminarlo de la bandeja de entrada del usuario, pero este mensaje seguira en la base de datos */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
           	st.executeUpdate("UPDATE seg_mensajes SET estado=1 WHERE seg_mensajes.cod_usuario='"+CodRemi+"' AND seg_mensajes.remitente='"+CodUsuario+"' AND seg_mensajes.codigo='"+CodMen+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoOpcionesAutorizadas>>EliminarMensajes "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarHistChatRec(String CodRemiChat, String codusuario,String Fecha){	
		/**
		 * consulta tiene como parametro el codigo del usuario,fecha
		 * para buscar los chats que tiene el usuario.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT  cod_usuario, mensaje, horaenvio FROM seg_chat WHERE fechaenvio='"+Fecha+"' AND cod_usuario='"+CodRemiChat+"' AND (estado=0 OR estado=1) AND codremi='"+codusuario+"' AND orden1!='"+codusuario+"' AND orden2!='"+codusuario+"' ORDER BY horaenvio DESC");
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoOpcionesAutorizadas>>BuscarHistChatRec"+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarRemiHistChatRec(String codusuario,String Fecha){	
		/**
		 * consulta tiene como parametro el codigo del usuario,fecha
		 * para buscar los chats que tiene el usuario.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT DISTINCT cod_usuario FROM seg_chat WHERE fechaenvio='"+Fecha+"' AND (estado=0 OR estado=1) AND codremi='"+codusuario+"' AND orden1!='"+codusuario+"' AND orden2!='"+codusuario+"' ORDER BY horaenvio DESC");
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoOpcionesAutorizadas>>BuscarRemiHistChatRec"+ex);
        }	
        return rs;
    }
	
	
	public void GuardarMensaje(String codremi,String asunto, String codmen, String CodUsuario,String estado,Date Fecha, Time Hora){	
		/**
		 * Guardar mensaje en la base de datos */
		Savemail sm=new Savemail();
		sm.setcodusu(CodUsuario);
		sm.setCodremi(codremi);
		sm.setasunto(asunto);
		sm.setcontmen(codmen);
		sm.setfecha(Fecha);
		sm.sethora(Hora);
		sm.setestado(estado);
        try{
        	
        	PreparedStatement ps = null;
        	Conexion con=new Conexion();
        	ps = con.conn.prepareStatement("INSERT INTO seg_mensajes (cod_usuario,remitente,estado,fechaenvio,horaenvio,asunto,mensaje) VALUES (?,?,?,?,?,?,?)");
        	ps.setString(1,sm.getcodusu());
        	ps.setString(2,sm.getCodremi());
        	ps.setString(3,sm.getestado());
        	ps.setDate(4,sm.getfecha());
        	ps.setTime(5,sm.gethora());
        	ps.setString(6,sm.getasunto());
        	ps.setString(7,sm.getcontmen());
        	ps.executeUpdate();
			ps.close();
			con.cerrar();
			
			
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoOpcionesAutorizadas>>GuardarMensajes "+ex);
        }	
       
    }
	
	public void GuardarChat(String codremi,String CodUsuario,String mensaje,String estado,Date Fecha, Time Hora,String orden1, String orden2){	
		/**
		 * Guardar mensaje en la base de datos */
		Savemail sm=new Savemail();
		sm.setcodusu(CodUsuario); 
		sm.setCodremi(codremi);
		sm.setfecha(Fecha);
		sm.sethora(Hora);
		sm.setestado(estado);
		sm.setcontmen(mensaje);
		sm.setorden1(orden1);
		sm.setorden2(orden2);
        try{
        	
        	PreparedStatement ps = null;
        	Conexion con=new Conexion();
        	ps = con.conn.prepareStatement("INSERT INTO seg_chat (cod_usuario,codremi,mensaje,fechaenvio,horaenvio,estado,orden1,orden2) VALUES (?,?,?,?,?,?,?,?)");
        	ps.setString(1,sm.getcodusu());
        	ps.setString(2,sm.getCodremi());
        	ps.setString(3,sm.getcontmen());
        	ps.setDate(4,sm.getfecha());
        	ps.setTime(5,sm.gethora());
        	ps.setString(6,sm.getestado()); 
        	ps.setString(7,sm.getorden1());
        	ps.setString(8,sm.getorden2());
        	ps.executeUpdate();
			ps.close();
			con.cerrar();
			
			
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoOpcionesAutorizadas>>GuardarChat "+ex);
        }	  
    }
	
	public java.sql.ResultSet MostrarChatRemi(String codremi,String CodigoUsuario,Date Fecha){	
		/**
		 * consulta tiene como parametro el codigo del usuario, codigo remitente y la fecha actual
		 * para buscar los chats q les han enviado determinado remitente.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT  codremi, mensaje, horaenvio FROM seg_chat WHERE fechaenvio='"+Fecha+"' AND cod_usuario='"+codremi+"' AND (estado=0 OR estado=1) AND codremi='"+CodigoUsuario+"' AND orden1!='"+CodigoUsuario+"' AND orden2!='"+CodigoUsuario+"' ORDER BY horaenvio DESC");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoOpcionesAutorizadas>>MostrarChatRemi "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet MostrarChatEnviado(String codremi,String CodigoUsuario,Date Fecha){	
		/**
		 * consulta tiene como parametro el codigo del usuario, codigo remitente y la fecha actual
		 * para buscar los chats q les han enviado determinado remitente.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT cod_usuario, mensaje, horaenvio FROM seg_chat WHERE fechaenvio='"+Fecha+"' AND cod_usuario='"+CodigoUsuario+"' AND (estado=0 OR estado=1)AND codremi='"+codremi+"' AND orden1!='"+CodigoUsuario+"' AND orden2!='"+CodigoUsuario+"' ORDER BY horaenvio DESC");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoOpcionesAutorizadas>> MostrarChatEnviado "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarNuevoChat(String CodUsuario, Date Fecha){	
		/**
		 * consulta tiene como parametro el codigo del usuario, codigo remitente y la fecha actual
		 * para buscar los chats q les han enviado determinado remitente.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT cod_usuario, mensaje, horaenvio FROM seg_chat WHERE fechaenvio='"+Fecha+"' AND (estado=0 OR estado=1)AND codremi='"+CodUsuario+"' AND orden1!='"+CodUsuario+"' AND orden2!='"+CodUsuario+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoOpcionesAutorizadas>> MostrarChatEnviado "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet LimpiarChat(String codusuario,String codremi, Date Fecha,int ordenenv, int ordenremi,  Time Hora, int codchat){	
		/**
		 * consulta tiene como parametro el codigo del usuario, codigo del remitente, fecha actual
		 * para cambiar de estado la informacion del chat y el usuario q solicita este cambio ya no mostrar en el cuadro de chat la info, simula al usuario una eliminacion de la
		 * informacion pero esta persiste en la base de datos para control*/
        java.sql.ResultSet rs=null;
        Statement st = null;
        Statement st1 = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	st1=con.conn.createStatement();
        	if((ordenremi==1)){
        		st.executeUpdate("UPDATE seg_chat SET estado=1 , orden1='"+codusuario+"' WHERE seg_chat.cod_usuario='"+codremi+"' AND seg_chat.codremi='"+codusuario+"'  AND seg_chat.fechaenvio='"+Fecha+"' and seg_chat.horaenvio<'"+Hora+"'and seg_chat.orden1=0 and seg_chat.codigochat='"+codchat+"'");
        	}else{
        		if((ordenremi==2)){
        			st.executeUpdate("UPDATE seg_chat SET estado=1 , orden2='"+codusuario+"' WHERE seg_chat.cod_usuario='"+codremi+"' AND seg_chat.codremi='"+codusuario+"'  AND seg_chat.fechaenvio='"+Fecha+"' and seg_chat.horaenvio<'"+Hora+"' and seg_chat.orden2=0 and seg_chat.codigochat='"+codchat+"'" );
        		}
           	
        	}
           	if((ordenenv==1)){
           	st1.executeUpdate("UPDATE seg_chat SET estado=1 , orden1='"+codusuario+"' WHERE seg_chat.cod_usuario='"+codusuario+"' AND seg_chat.codremi='"+codremi+"' AND seg_chat.fechaenvio='"+Fecha+"' and seg_chat.horaenvio<'"+Hora+"' and seg_chat.orden1=0 and seg_chat.codigochat='"+codchat+"'" );
           	}else{
           		if((ordenenv==2)){
           		st1.executeUpdate("UPDATE seg_chat SET estado=1 , orden2='"+codusuario+"' WHERE seg_chat.cod_usuario='"+codusuario+"' AND seg_chat.codremi='"+codremi+"' AND seg_chat.fechaenvio='"+Fecha+"' and seg_chat.horaenvio<'"+Hora+"' and seg_chat.orden2=0 and seg_chat.codigochat='"+codchat+"'"  );
           		}
           	}
           	}
           
        catch(Exception ex){
        	System.out.println("Error en MetodoOpcionesAutorizadas>>LimpiarChat "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ConsultarOrden(String codusuario,String codremi, Date Fecha, int Val, Time Hora){	
		/**
		 * consulta tiene como parametro el codigo del usuario, codigo del remitente, fecha actual
		 * para determinar si orden esta vacia o llena y asi poder llenar los datos correctamente en Limpiar Chat*/
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(Val==1){
        		//remi
        	rs=st.executeQuery("select seg_chat.orden1, seg_chat.orden2, seg_chat.estado, seg_chat.codigochat from seg_chat where  seg_chat.cod_usuario='"+codremi+"' AND seg_chat.codremi='"+codusuario+"'  AND seg_chat.fechaenvio='"+Fecha+"' and seg_chat.horaenvio<'"+Hora+"'");
        	}else{
        		if(Val==2){
        			//enviado
        		rs=st.executeQuery("select seg_chat.orden1, seg_chat.orden2,seg_chat.estado, seg_chat.codigochat from seg_chat where  seg_chat.cod_usuario='"+codusuario+"' AND seg_chat.codremi='"+codremi+"'  AND seg_chat.fechaenvio='"+Fecha+"' and seg_chat.horaenvio<'"+Hora+"'");
        		}
        	}
        	}
        catch(Exception ex){
        	System.out.println("Error en MetodoOpcionesAutorizadas>>ConsultarOrden "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet BuscarNombreUsuario(String CodigoUsuario){	
		/**
		 * consulta tiene como parametro el codigo del usuario
		 * para buscar el nombre del usuario
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT CONCAT(sdt.nombre,' ',sdt.apellido)AS nombreuser, su.usu_codigo, sdt.nombre FROM seg_datos_personales sdt, seg_usuario su WHERE su.usu_codigo='"+CodigoUsuario+"' AND sdt.dat_codigo=su.dat_codigo_fk");
        			}
        catch(Exception ex){
        	System.out.println("Error en MetodoOpcionesAutorizadas>>BuscarNombreUsuario "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarNombreRemi(String CodigoUsuario){	
		/**
		 * consulta tiene como parametro el codigo del usuario
		 * para buscar el nombre del usuario
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT CONCAT(sdt.nombre,' ',sdt.apellido)AS nombreuser, su.usu_codigo, sdt.nombre FROM seg_datos_personales sdt, seg_usuario su WHERE su.usu_codigo='"+CodigoUsuario+"' AND sdt.dat_codigo=su.dat_codigo_fk");
        			}
        catch(Exception ex){
        	System.out.println("Error en MetodoOpcionesAutorizadas>>BuscarNombreUsuario "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarPermisosMenu(String CodigoUsuario){	
		/**
		 * consulta tiene como parametro el codigo del usuario
		 * para buscar cuales modulos tiene permiso.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select distinct sm.nombre,sm.descripcion from seg_menu sm,seg_opciones_menu som,seg_opciones_disponibles sod,seg_opciones_autorizadas soa,seg_usuario su where sm.men_codigo=som.men_codigo_fk and som.opm_codigo=sod.opm_codigo_fk and sod.opd_codigo=soa.opd_codigo_fk and su.usu_codigo=soa.usu_codigo_fk and su.usu_codigo='"+CodigoUsuario+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoOpcionesAutorizadas>>BuscarPermisosMenu "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarFormatosActivosHosp(String CodigoUsuario){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT suf.codigo AS Codasignacion,hfor.nombre_formato FROM seg_usuariosformato suf,hic_formato hfor WHERE hfor.codigo=suf.codigoFormato_fk AND suf.codigoUsuario_fk="+CodigoUsuario+"");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoOpcionesAutorizadas>>BuscarFormatosActivosHosp "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarPermisosActivosHC(String CodigoUsuario){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT upc.codigo AS Codasignacion,phc.nombrepermisohc FROM hic_usuariopermisoshc upc,hic_permisoshc phc WHERE phc.codigo=upc.codigoPerhc_fk AND upc.codigoUsu_fk="+CodigoUsuario+"");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoOpcionesAutorizadas>>BuscarPermisosActivosHC "+ex);
        }	
        return rs;
    }
	public java.sql.ResultSet BuscarFormatosActivosCE(String CodigoUsuario){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT suf.codigo AS Codasignacion,hfor.nombre_formato FROM seg_usuariosformatosce suf,hic_formato hfor WHERE hfor.codigo=suf.codigoFormato_fk AND suf.codigoUsuario_fk="+CodigoUsuario+"");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoOpcionesAutorizadas>>BuscarFormatosActivosCE "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarPermisosDisponiblesHC(String CodigoUsuario){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT DISTINCT phc.codigo AS CodPermiso,phc.nombrepermisohc FROM hic_usuariopermisoshc upc,hic_permisoshc phc WHERE phc.codigo NOT IN (SELECT codigoPerhc_fk FROM hic_usuariopermisoshc WHERE codigoUsu_fk="+CodigoUsuario+") AND upc.codigoUsu_fk="+CodigoUsuario+" ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoOpcionesAutorizadas>>BuscarFormatosDisponiblesHosp "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarFormatosDisponiblesCE(String CodigoUsuario){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT DISTINCT hfor.codigo AS CodFormato,hfor.nombre_formato FROM seg_usuariosformatosce suf,hic_formato hfor WHERE hfor.codigo NOT IN (SELECT codigoFormato_fk FROM seg_usuariosformatosce WHERE codigoUsuario_fk="+CodigoUsuario+") AND suf.codigoUsuario_fk="+CodigoUsuario+" ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoOpcionesAutorizadas>>BuscarFormatosDisponiblesHosp "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarFormatosDisponiblesHosp(String CodigoUsuario){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT DISTINCT hfor.codigo AS CodFormato,hfor.nombre_formato FROM seg_usuariosformato suf,hic_formato hfor WHERE hfor.codigo NOT IN (SELECT codigoFormato_fk FROM seg_usuariosformato WHERE codigoUsuario_fk="+CodigoUsuario+") AND suf.codigoUsuario_fk="+CodigoUsuario+" ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoOpcionesAutorizadas>>BuscarFormatosDisponiblesHosp "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet BuscarUsuario(String Identificacion){	
		/**
		 * consulta tiene como parametro la Identificacion del usuario para asignarles los formatos.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select sdt.nombre,sdt.apellido,su.usu_codigo,sdt.dat_codigo from seg_datos_personales sdt,seg_usuario su where sdt.numeroDocumento='"+Identificacion+"' and su.dat_codigo_fk=sdt.dat_codigo");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoOpcionesAutorizadas>>BuscarUsuario "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet TodosPermisosHC(){	
		/**
		 * consulta tiene como fin encontrar todos los formatos en la db
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT codigo,nombrepermisohc FROM hic_permisoshc");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoOpcionesAutorizadas>>TodosPermisosHC "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet TodosFormatos(){	
		/**
		 * consulta tiene como fin encontrar todos los formatos en la db
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo,nombre_formato from hic_formato");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoOpcionesAutorizadas>>TodosFormatos "+ex);
        }	
        return rs;
    }
	
	
	 public java.sql.ResultSet PermisosPaginas(String codusu,String pagina){
	       java.sql.ResultSet rs=null;
	       Statement st = null;
	       try{
	       	Conexion con=new Conexion();
	       	st = con.conn.createStatement();
	       	rs=st.executeQuery("select seg_opciones_autorizadas.opd_codigo_fk from seg_opciones_autorizadas,seg_opciones_disponibles where seg_opciones_autorizadas.usu_codigo_fk="+codusu+" and seg_opciones_autorizadas.opd_codigo_fk=seg_opciones_disponibles.opd_codigo and seg_opciones_disponibles.url='"+pagina+"'");
	       }
	       catch(Exception ex){
	       	System.out.println(ex);
	       }
	       return rs;
	   }
	
	 public java.sql.ResultSet ObtenerMenu(String codusu){
	       java.sql.ResultSet rs=null;
	       Statement st = null;
	       try{
	       	Conexion con=new Conexion();
	       	st = con.conn.createStatement();
	       	rs=st.executeQuery("select nombre,url from seg_opciones_disponibles opd,seg_opciones_autorizadas opa,seg_usuario sgu where sgu.usu_codigo='"+codusu+"' and opa.usu_codigo_fk='"+codusu+"' and opa.opd_codigo_fk=opd.opd_codigo");
	       }
	       catch(Exception ex){
	       	System.out.println(ex);
	       }
	       return rs;
	   }
	 public java.sql.ResultSet Obtenercodigousuario(String cedula){
	       java.sql.ResultSet rs=null;
	       Statement st = null;
	       try{
	       	Conexion con=new Conexion();
	       	st = con.conn.createStatement();
	       	rs=st.executeQuery("select seg_usuario.usu_codigo from seg_usuario,seg_datos_personales where seg_datos_personales.cedula='"+cedula+"' and seg_datos_personales.dat_codigo=seg_usuario.dat_codigo_fk");
	       }
	       catch(Exception ex){
	       }
	       return rs;
	   }
		 
	
	
	public void insertarAutorizadas(String codusu,String codigo){
		OpcionesAutorizadas oa=new OpcionesAutorizadas();
		oa.setDisponible(codigo);
		oa.setUsuario(codusu);
		try{
			PreparedStatement ps = null;
		    Conexion con=new Conexion();
		    ps=con.conn.prepareStatement("insert into seg_opciones_autorizadas (usu_codigo_fk,opd_codigo_fk) value (?,?)");				
			ps.setString(1, oa.getUsuario());
			ps.setString(2, oa.getDisponible());
			ps.executeUpdate();
			ps.close();
			con.cerrar();
		}//fin del try
		catch(Exception ex){
			System.out.println(ex);
		}//fin del catch
	}//fin insercion
}
