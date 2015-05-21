package aiepi_metodo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/*la clase conexion*/
import adm_logica.Conexion;

public class MetodoConfiguracion {

	public MetodoConfiguracion(){}
	
	//verificar si existe y obtener el codigo del paciente
	public ResultSet verificarPaciente(String ndoc, String tdoc){
		ResultSet rs=null;
		Statement st = null;
		try{
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("SELECT P.pac_codigo_paciente, " +
					"YEAR(CURDATE())-YEAR(P.fecha_nacimiento)+IF(DATE_FORMAT(CURDATE(),'%m-%d')>DATE_FORMAT(P.fecha_nacimiento,'%m-%d'),-1,0) anios, " +
					"MONTH(CURDATE()) - MONTH(P.fecha_nacimiento) + IF(DATE_FORMAT(CURDATE(),'%d')<DATE_FORMAT(P.fecha_nacimiento,'%d'),-1,0)  meses, " +
					"DAY(CURDATE())-DAY(P.fecha_nacimiento) + IF(DATE_FORMAT(CURDATE(),'%d')<DATE_FORMAT(P.fecha_nacimiento,'%d'),30,0) dias " +
					"FROM adm_paciente P " +
					"WHERE P.numero_documento='"+ndoc+"' AND tipo_documento='"+tdoc+"'");
								
		}catch(Exception ex){
			
		}
		return rs;
	}
	
	//obtener el codigo de la primera area de la encuesta
	public ResultSet Area(String tipo_aiepi){
		ResultSet rs=null;
		Statement st = null;
		try{
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("SELECT aiA.codigo " +
					             "FROM aiepi_area aiA, aiepi_posicion_area aiPA, aiepi_tipoaiepi aiTAI " +
					             "WHERE aiA.posicion=aiPa.cod AND aiA.estado=1 AND aiA.dependeArea='' " +
					             "AND aiTAI.codigo=aiA.codigo_tipo_fk AND aiTAI.codigo='"+tipo_aiepi+"'");
			
		}catch(Exception ex){
			
		}
		return rs;
	}
	
	//obtener preguntas por area
	public ResultSet preguntas(String codArea){
		ResultSet rs=null;
		Statement st = null;
		try{
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("SELECT aiP.codigo, aiP.descripcion, aiTR.descripcion FROM aiepi_area aiA, aiepi_preguntas aiP, aiepi_tiporespuesta aiTR WHERE aiA.codigo=aiP.cod_area AND aiTR.codigo=aiP.tipo_respuesta_fk AND aiA.codigo='"+codArea+"' AND aiP.dependencia='' ORDER BY aiP.codAut");
		}catch(Exception ex){
			
		}
		return rs;
	}
	
	//obtener el tipo de respuesta que tiene la pregunta
	public ResultSet preguntaTipoResp(String codPregunta){
		ResultSet rs=null;
		Statement st = null;
		try{
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("SELECT aiTR.descripcion FROM aiepi_preguntas aiP, aiepi_tiporespuesta aiTR WHERE aiP.tipo_respuesta_fk=aiTR.codigo AND aiP.codigo='"+codPregunta+"'");
		}catch(Exception ex){
			
		}
		return rs;
	}
	
	//obtener las posibles respuesta para una pregunta
	public ResultSet preguntaRespuestas(String codPregunta, String cdPregunta){
		ResultSet rs=null;
		Statement st = null;
		try{
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("SELECT aiR.codigo, aiR.descripcion FROM aiepi_preguntas aiP, aiepi_respuesta aiR, aiepi_tiporespuesta aiTR"+ 
								 " WHERE aiTR.codigo=aiR.cod_tipo_respuesta_fk AND aiP.tipo_respuesta_fk=aiTR.codigo AND aiP.codigo='"+codPregunta+"' and aiR.codPregunta='"+cdPregunta+"'");
		
		}catch(Exception ex){
			
		}
		return rs;
	}
	
		
	//verificar si existen preguntas que dependan de un pregunta
	public ResultSet existeDependencia(String codPregunta){
		ResultSet rs=null;
		Statement st = null;
		try{
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("SELECT aiP.respuesta_dependencia FROM aiepi_preguntas aiP WHERE aiP.dependencia='"+codPregunta+"'");
		}catch(Exception ex){
			
		}
		return rs;
	}
	
	//obtener las preguntas que dependan de un pregunta
	public ResultSet ObtenerPreguntaDependencia(String codPregunta, String codResp){
		ResultSet rs=null;
		Statement st = null;
		try{
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("SELECT aiP.codigo, aiP.descripcion FROM aiepi_area aiA, aiepi_preguntas aiP WHERE aiA.codigo=aiP.cod_area AND aiP.dependencia='"+codPregunta+"' AND aiP.respuesta_dependencia='"+codResp+"' ORDER BY aiP.codAut" );
		}catch(Exception ex){
			
		}
		return rs;
	}
	
	//obtener los diagnosticos de un area
	public ResultSet obtenerDiagnsticoArea(String area, String tipoAiepi){
		ResultSet rs=null;
		Statement st = null;
		try{
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("SELECT aiD.codigo, aiD.descripcion, aiD.tipoAlerta FROM aiepi_diagnostico aiD, aiepi_area aiA, aiepi_tipoaiepi aiTAI " +
								 "WHERE aiD.codigo_area_fk=aiA.codigo AND aiA.codigo='"+area+"' AND aiTAI.codigo=aiA.codigo_tipo_fk AND aiTAI.codigo='"+tipoAiepi+"'");
		}catch(Exception ex){
			
		}
		return rs;
	}
	
	//obtener el tipo de condicion de las respuestas del diagnostico
	public ResultSet obtenerTipoCondDiag(String codDiagnostico){
		ResultSet rs=null;
		Statement st = null;
		try{
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("SELECT aiTCD.codigo, aiTCD.descripcion FROM aiepi_diagnostico aiD, aiepi_tipocondicion aiTCD" +
								 " WHERE aiTCD.codigo=aiD.tipo_condicion_fk AND aiD.codigo='"+codDiagnostico+"'");
		}catch(Exception ex){
			
		}
		return rs;
	}
	
	//obtener las preguntas y respuestas para la condicion de un diagnostico 
	public ResultSet obtenerCondDiag(String codDiagnostico){
		ResultSet rs=null;
		Statement st = null;
		try{
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("SELECT aiCD.cod_pregunta_fk, aiCD.cod_respuesta_fk FROM aiepi_diagnostico aiD, aiepi_condiciondiagnostico aiCD" +
								 " WHERE aiD.codigo=aiCD.cod_diagnostico_fk AND aiD.codigo='"+codDiagnostico+"'");
		}catch(Exception ex){
			
		}
		return rs;
	}
	
	//obtener la posicion del area
	public ResultSet obtenerPosArea(String codArea){
		ResultSet rs=null;
		Statement st = null;
		try{
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("SELECT aiPA.cod FROM aiepi_area aiA, aiepi_posicion_area aiPA WHERE aiA.posicion = aiPA.cod AND aiA.codigo='"+codArea+"'");
		}catch(Exception ex){
			
		}
		return rs;
	}
	
	//obtener codigo de todas las areas restantes de la encuesta
	public ResultSet obtenerAreas(String tipoAiepi, String codArea, String respArea){
		ResultSet rs=null;
		Statement st = null;
		try{
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("SELECT aiA.codigo FROM aiepi_area aiA, aiepi_tipoaiepi aiTAI WHERE NOT aiA.posicion=1 AND aiTAI.codigo=aiA.codigo_tipo_fk AND aiTAI.codigo='"+tipoAiepi+"' AND aiA.dependeArea='"+codArea+"' AND aiA.respArea='"+respArea+"' ORDER BY aiA.posicion");
			
		}catch(Exception ex){
			
		}
		return rs;
	}
	
	//obtener datos de las areas
	public ResultSet datosAreas(String codArea){
		ResultSet rs=null;
		Statement st = null;
		try{
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("SELECT aiA.posicion, aiA.descripcion, aiTR.descripcion, aiA.resp_positiva FROM aiepi_area aiA, aiepi_tiporespuesta aiTR WHERE aiA.tipo_respuesta=aiTR.codigo AND aiA.codigo='"+codArea+"' AND aiA.estado=1");
		}catch(Exception ex){
			
		}
		return rs;
	}
	
	//obtener las posibles respuestas de un area
	public ResultSet areasRespuestas(String codArea){
		ResultSet rs=null;
		Statement st = null;
		try{
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("SELECT aiR.codigo FROM aiepi_area aiA, aiepi_tiporespuesta aiTR, aiepi_respuesta aiR" +
								 " WHERE aiA.tipo_respuesta=aiTR.codigo AND aiTR.codigo=aiR.cod_tipo_respuesta_fk AND aiA.codigo='"+codArea+"' AND aiA.estado=1");
		}catch(Exception ex){
			
		}
		return rs;
	}
	
	//obtener el codigo del aiepi
	public ResultSet obtenerCodAiepi(){
		ResultSet rs=null;
		Statement st = null;
		try{
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("select ai.codigo from aiepi ai order by cod");
		}catch(Exception ex){
			
		}
		return rs;
	}
	
	//guardar datos encuesta
	public void guardarAiepi(String codigo,String fecha,String hora,String tipo_fk,String cod_pac_fk, String codUsuario){
		int codUsu = Integer.parseInt(codUsuario);
		try{
			PreparedStatement ps = null;
		    Conexion con=new Conexion();
		    ps=con.conn.prepareStatement("insert into aiepi(codigo,fecha,hora,tipo_fk,cod_pac_fk,usu_codigo_fk) values (?,?,?,?,?,?)");
			ps.setString(1,codigo);
			ps.setString(2,fecha);
			ps.setString(3,hora);
			ps.setString(4,tipo_fk);
			ps.setString(5,cod_pac_fk);
			ps.setInt(6, codUsu);
			ps.executeUpdate();			
			ps.close();
			con.cerrar();
		
		}catch(Exception ex){
			System.out.print("Error en MetodoConfiguracion>>guardarAiepi "+ex);
			ex.getMessage();
		}
	}
	
	//guardar datos encuesta
	public void guardarRespAiepi(String codAiepi,String codArea,String codPregunta,String codRespuesta){
		try{
			PreparedStatement ps = null;
		    Conexion con=new Conexion();
		    ps=con.conn.prepareStatement("insert into aiepi_detalleencuesta(cod_aiepi_fk,cod_area_fk,cod_pregunta_fk,cod_respuesta_fk) values (?,?,?,?)");
			ps.setString(1,codAiepi);
			ps.setString(2,codArea);
			ps.setString(3,codPregunta);
			ps.setString(4,codRespuesta);
			ps.executeUpdate();			
			ps.close();
			con.cerrar();
		
		}catch(Exception ex){
			System.out.print("Error en MetodoConfiguracion>>guardarRespAiepi "+ex);
			ex.getMessage();
		}
	}
	
	//guardar datos encuesta
	public void guardarDiagAiepi(String codAiepi,String codArea,String codDiag){
		
		try{
			PreparedStatement ps = null;
		    Conexion con=new Conexion();
		    ps=con.conn.prepareStatement("insert into aiepi_detalle_diagnostico(cod_Aiepi_fk,cod_diagnostico_fk,cod_area_fk) values (?,?,?)");
			ps.setString(1,codAiepi);
			ps.setString(2,codDiag);
			ps.setString(3,codArea);
			ps.executeUpdate();			
			ps.close();
			con.cerrar();
		
		}catch(Exception ex){
			System.out.print("Error en MetodoConfiguracion>>guardarRespAiepi "+ex);
			ex.getMessage();
		}
	}
	
	//guardar datos encuesta
	public void guardarRespDiagAiepi(String codAiepi,String codDiag,String codPreg, String codResp){
		
		try{
			PreparedStatement ps = null;
		    Conexion con=new Conexion();
		    ps=con.conn.prepareStatement("insert into aiepi_respdiag(codAiepi_fk,codDiag_fk,cod_preg_fk,cod_resp_fk) values (?,?,?,?)");
			ps.setString(1,codAiepi);
			ps.setString(2,codDiag);
			ps.setString(3,codPreg);
			ps.setString(4,codResp);
			ps.executeUpdate();			
			ps.close();
			con.cerrar();
		
		}catch(Exception ex){
			System.out.print("Error en MetodoConfiguracion>>guardarRespAiepi "+ex);
			ex.getMessage();
		}
	}
	
	//obtener datos usuarios
	public ResultSet obtenerDatosPaciente(String codPac){
		
		ResultSet rs=null;
		Statement st = null;
		try{
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("select distinct concat(aP.nombre,' ',aP.primer_apellido,' ',aP.segundo_apellido) as Paciente, concat(aP.tipo_documento,' ',aP.numero_documento) as identificacion, " +
								 "aP.genero as genero,aE.nombre_entidad as nombre_entidad, " +
								 "YEAR(CURDATE())-YEAR(aP.fecha_nacimiento)+IF(DATE_FORMAT(CURDATE(),'%m-%d')>DATE_FORMAT(aP.fecha_nacimiento,'%m-%d'),-1,0) + if(YEAR(CURDATE())-YEAR(aP.fecha_nacimiento)+IF(DATE_FORMAT(CURDATE(),'%m-%d')>DATE_FORMAT(aP.fecha_nacimiento,'%m-%d'),-1,0)<0,1,0) as anio, " +
								 "MONTH(CURDATE())-MONTH(aP.fecha_nacimiento) + IF(DATE_FORMAT(CURDATE(),'%d')<DATE_FORMAT(aP.fecha_nacimiento,'%d'),-1,0)+if(MONTH(CURDATE())-MONTH(aP.fecha_nacimiento) + IF(DATE_FORMAT(CURDATE(),'%d')<DATE_FORMAT(aP.fecha_nacimiento,'%d'),-1,0)<0,12,0) mes, " +
								 "DAY(CURDATE())-DAY(aP.fecha_nacimiento) + IF(DATE_FORMAT(CURDATE(),'%d')<DATE_FORMAT(aP.fecha_nacimiento,'%d'),30,0) dia " +
								 "from adm_paciente aP, adm_convenio aC, adm_entidad aE " +
								 "where aP.pac_codigo_paciente='"+codPac+"' " +
								 "and aP.conv_numero_contrato_fk=aC.conv_numero_contrato " +
								 "and aC.ent_nit_contratante_fk=aE.ent_nit");
		}catch(Exception ex){
			
		}
		return rs;
	}
	
	//obtener diagnosticos usuarios
	public ResultSet obtenerDiagPaciente(String codPac, String codAiepi){
		
		ResultSet rs=null;
		Statement st = null;
		try{
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("select aiD.descripcion as Daignosticos, aiD.codigo as codDiag, AI.codigo as codAiepi, aiPl.descripcion as descripcion " +
								 "from aiepi AI, aiepi_detalle_diagnostico aiDT, aiepi_diagnostico aiD, aiepi_planesaseguir aiPl " +
								 "where AI.cod_pac_fk='"+codPac+"' " +
								 "and AI.codigo='"+codAiepi+"' " +
								 "and aiDT.cod_Aiepi_fk=AI.codigo " +
								 "and aiDT.cod_diagnostico_fk=aiD.codigo " +
								 "and aiD.codPlanASeguir=aiPL.codigo");
		}catch(Exception ex){
			
		}
		return rs;
	}
	
	//verificar si el usuario tiene diagnosticos
	public ResultSet obtenerDiagPaciente2(String codPac, String codAiepi){
		
		ResultSet rs=null;
		Statement st = null;
		try{
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("select aiDT.cod_diagnostico_fk as codDiag " +
					             "from aiepi AI, aiepi_detalle_diagnostico aiDT " +
					             "where AI.cod_pac_fk='"+codPac+"' " +
					             "and AI.codigo='"+codAiepi+"' " +
					             "and aiDT.cod_Aiepi_fk=AI.codigo");
		}catch(Exception ex){
			
		}
		return rs;
	}
	
	
	//obtener respuestas que dan con el diagnostico
	public ResultSet obtenerRespDiagPaciente(String codDiag, String codAiepi){
		
		ResultSet rs=null;
		Statement st = null;
		try{
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("select GROUP_CONCAT(if(aiR.descripcion<>'Si', " +
					             "concat(aiR.descripcion,' ',rtrim(ltrim(replace(replace(replace(replace(aiPreg.descripcion,'?',''),'¿',''),'el niño',''),'El niño','')))) " +
					             ",rtrim(ltrim(replace(replace(replace(replace(aiPreg.descripcion,'?',''),'¿',''),'el niño',''),'El niño','')))) separator	', ') Descr " +
					             "from aiepi_respdiag aiRD, aiepi_preguntas aiPreg, aiepi_respuesta aiR " +
					             "where aiRD.codAiepi_fk='"+codAiepi+"'"+
					             "and aiRD.codDiag_fk='"+codDiag+"'"+
					             "and aiPreg.codigo=aiRD.cod_preg_fk " +
					             "and aiRD.cod_resp_fk=aiR.codigo");
		}catch(Exception ex){
			
		}
		return rs;
	}
	
	
	//guardar edad del paciente y observaciones a un aiepi en especifico
	public void guardarObservaciones(String codAiepi,String edad, String observaciones){
		/***/
					
       PreparedStatement st = null;
        try{
        	Conexion con=new Conexion();
        	st= con.conn.prepareStatement("UPDATE aiepi set edadPac='"+edad+"',observaciones='"+observaciones+"' WHERE codigo='"+codAiepi+"'");
        	st.executeUpdate();
        	st.close();
        	con.cerrar();
        	
        }
        catch(Exception ex){
        	System.out.print("Error en MetodoConfiguracion>>guardarObservaciones "+ex);
        	ex.getMessage();
        
        }	
       
    }
	
	
	//obtener datos del paciente con aiepi 
	public ResultSet obtenerDatosPacAiepi(String tDoc, String nDoc){
		
		ResultSet rs=null;
		Statement st = null;
		try{
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("select distinct concat(aP.nombre,' ',aP.primer_apellido,' ',aP.segundo_apellido) as nombre, aP.genero, aP.pac_codigo_paciente  from aiepi AI, adm_paciente aP " +
					             "where aP.tipo_documento='"+tDoc+"' and aP.numero_documento='"+nDoc+"' " +
					             "and aP.pac_codigo_paciente=AI.cod_pac_fk");
		}catch(Exception ex){
			
		}
		return rs;
	}
	
	//obtener aiepi's del paciente
	public ResultSet obtenerAiepiPac(String codPac){
		
		ResultSet rs=null;
		Statement st = null;
		try{
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("select aiTA.descripcion,concat(sdt.nombre,' ',sdt.apellido),ai.fecha from " +
								 "aiepi ai, aiepi_tipoaiepi aiTA, seg_usuario su, seg_datos_personales sdt " +
								 "where ai.cod_pac_fk='"+codPac+"' " +
								 "and ai.tipo_fk=aiTA.codigo " +
								 "and ai.usu_codigo_fk=su.usu_codigo " +
								 "and su.dat_codigo_fk=sdt.dat_codigo " +
								 "order by ai.fecha ");
		}catch(Exception ex){
			
		}
		return rs;
	}
	
	
	
	//consultar si el paciente presenta AIEPI para x fecha
	public ResultSet obtenerAiepiXFecha(String codPac, String fecha){
		
		ResultSet rs=null;
		Statement st = null;
		try{
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("select AI.codigo from aiepi AI where AI.fecha='"+fecha+"' and AI.cod_pac_fk='"+codPac+"'");
		}catch(Exception ex){
			
		}
		return rs;
	}
	
	
}