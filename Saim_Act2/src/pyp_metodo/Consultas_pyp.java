package pyp_metodo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import adm_logica.Conexion;

public class Consultas_pyp {
	
	public Consultas_pyp(){}
	
	public ResultSet SinDuplicado(String tabla,String codPac,String CodPreg){
		ResultSet rs=null;
		Statement st = null;
		try{
			Conexion con = new Conexion();
			st = con.conn.createStatement(); 
			rs = st.executeQuery("SELECT COUNT(DISTINCT cod_paciente) AS cont FROM "+tabla+" WHERE cod_paciente = '"+codPac+"' AND fecha_encuesta = CURDATE() AND cod_pregunta = '"+CodPreg+"';");
		}catch(Exception ex){
			ex.getMessage();
		}
		return rs;
	}
	
	public ResultSet SinDuplicado2(String tabla,String codPac,String CodResp){
		ResultSet rs=null;
		Statement st = null;
		try{
			Conexion con = new Conexion();
			st = con.conn.createStatement(); 
			rs = st.executeQuery("SELECT COUNT(DISTINCT cod_paciente) AS cont FROM "+tabla+" WHERE cod_paciente = '"+codPac+"' AND fecha_encuesta = CURDATE() AND cod_respuesta = '"+CodResp+"';");
		}catch(Exception ex){
			ex.getMessage();
		}
		return rs;
	}
	
	public ResultSet NumControles(String codPac){
		ResultSet rs=null;
		Statement st = null;
		try{
			Conexion con = new Conexion();
			st = con.conn.createStatement(); 
			rs = st.executeQuery("SELECT COUNT(DISTINCT cod_paciente) FROM aiepi_gestante_23_3 area23 WHERE cod_paciente = '"+codPac+"' AND fecha_encuesta < CURDATE();");
		}catch(Exception ex){
			ex.getMessage();
		}
		return rs;
	}
	
	public ResultSet verificarGestante(String ndoc, String tdoc){
		ResultSet rs=null;
		Statement st = null;
		try{
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("SELECT P.* FROM adm_paciente P WHERE P.numero_documento = '"+ndoc+"'  AND tipo_documento = '"+tdoc+"' ");
		}catch(Exception ex){
			ex.getMessage();
		}
		return rs;
	}
	
	public ResultSet VerificarPermisosHC(String usuario){
		ResultSet rs = null;
		Statement st = null;
		try{
			Conexion con=new Conexion();
			st=con.conn.createStatement();
			rs=st.executeQuery("SELECT codigoPerhc_fk FROM hic_usuariopermisoshc WHERE codigoUsu_fk="+usuario+" order by codigoPerhc_fk");
		}
		catch(Exception ex){
			ex.getMessage();
			System.out.println("Error MetodoMultiplePacientes>>VerificarPermisosHC "+ex);
		}
		return rs;
	}
	
	public ResultSet devEcografia(String codigo){
		ResultSet rs=null;
		Statement st = null;
		try{
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("SELECT fecha_ecografia FROM aiepi_encuestagestante1 WHERE codigo = '"+codigo+"'");
		}catch(Exception ex){
			ex.getMessage();
		}
		return rs;
	}
	
	public ResultSet BuscarCodAdm(String ndoc, String tdoc){
		ResultSet rs=null;
		Statement st = null;
		try{
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("SELECT P.* FROM adm_paciente P WHERE P.numero_documento = '"+ndoc+"'  AND tipo_documento = '"+tdoc+"' ");
		}catch(Exception ex){
			ex.getMessage();
		}
		return rs;
	}
	
	public ResultSet cargarDatos(String tdoc, String ndoc){
		ResultSet rs=null;
		Statement st = null;
		try{
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("SELECT CONCAT(P.nombre,' ',P.primer_apellido,' ',P.segundo_apellido,' ')AS NombreC," +
					"P.estado_civil,YEAR(CURDATE())-YEAR(P.fecha_nacimiento)  AS Edad," +
					"P.tipo_afiliacion,P.nivel_cotizante,P.direccion,D.fecha_Concepcion,D.fecha_PartoGestante,P.pac_codigo_paciente,A.adm_numero_ingreso,CURDATE(),D.codigo,D.tipo_Gestacion " +
					"FROM adm_paciente P, aiepi_encuestagestante1 D, adm_admisiones A " +
					"WHERE P.numero_documento = D.ced_paciente AND P.numero_documento = '"+ndoc+"' " +
					"AND P.tipo_documento = '"+tdoc+"'AND A.pac_codigo_paciente_fk = P.pac_codigo_paciente LIMIT 1");
		}catch(Exception ex){
			ex.getMessage();
		}
		return rs;
	}
	
	public ResultSet revisarCedula(String ndoc) {
		ResultSet rs=null;
		Statement st = null;
		try{
			Conexion con = new Conexion();
			st = con.conn.createStatement(); 
			rs = st.executeQuery("SELECT COUNT(*) FROM aiepi_encuestagestante1 de WHERE de.ced_paciente = '"+ndoc+"'");
		}catch(Exception ex){
			ex.getMessage();
		}
		return rs;
	}
	
	public ResultSet devCodUser(String codUser) {
		ResultSet rs=null;
		Statement st = null;
		try{
			Conexion con = new Conexion();
			st = con.conn.createStatement(); 
			rs = st.executeQuery("SELECT CONCAT(dp.nombre,' ',dp.apellido)AS nombre,dp.cedula FROM seg_usuario,seg_datos_personales dp WHERE usu_codigo = '"+codUser+"' AND dp.dat_codigo=dat_codigo_fk");
		}catch(Exception ex){
			ex.getMessage();
		}
		return rs;
	}
	
	public ResultSet devCodPac(String ndoc, String tdoc){
		ResultSet rs=null;
		Statement st = null;
		try{
			Conexion con = new Conexion();
			st = con.conn.createStatement(); 
			rs = st.executeQuery("SELECT pac_codigo_paciente FROM adm_paciente WHERE numero_documento = '"+ndoc+"' and tipo_documento = '"+tdoc+"'");
		}catch(Exception ex){
			ex.getMessage();
		}
		return rs;
	}
	
	public ResultSet DateDif(String codPac, String Date){
		ResultSet rs=null;
		Statement st = null;
		try{
			Conexion con = new Conexion();
			st = con.conn.createStatement(); 
			rs = st.executeQuery("SELECT DATEDIFF('"+Date+"',ae.fecha_Concepcion)AS DiasDif FROM aiepi_encuestagestante1 ae WHERE ae.codigo = '"+codPac+"'");
		}catch(Exception ex){
			ex.getMessage();
		}
		return rs;
	}
	
	public ResultSet DateDif2(String codPac){
		ResultSet rs=null;
		Statement st = null;
		try{
			Conexion con = new Conexion();
			st = con.conn.createStatement(); 
			rs = st.executeQuery("SELECT DATEDIFF(CURDATE(),ae.fecha_Concepcion)AS DiasDif FROM aiepi_encuestagestante1 ae WHERE ae.cod_pacientefk = '"+codPac+"'");
		}catch(Exception ex){
			ex.getMessage();
		}
		return rs;
	}
	
	public ResultSet DifDias(String fecha){
		ResultSet rs=null;
		Statement st = null;
		try{
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("SELECT DATEDIFF(CURDATE(),'"+fecha+"')AS DiasDif");
		}catch(Exception ex){
			ex.getMessage();
		}
		return rs;
	}
	
	public ResultSet FechaParto(String fecha){
		ResultSet rs=null;
		Statement st = null;
		try{
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("SELECT DATE_ADD('"+fecha+"', INTERVAL 280 DAY)AS DiaParto;");
		}catch(Exception ex){
			ex.getMessage();
		}
		return rs;
	}
		
	public ResultSet RestFec(String fecha){
		ResultSet rs=null;
		Statement st = null;
		try{
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("SELECT DATE_SUB('"+fecha+"', INTERVAL 280 DAY)AS DiaConp;");
		}catch(Exception ex){
			ex.getMessage();
		}
		return rs;
	}
	
	public ResultSet FechaPartoEco(String fecha,String Dias){
		ResultSet rs=null;
		Statement st = null;
		try{
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("SELECT DATE_ADD('"+fecha+"', INTERVAL "+Dias+" DAY)AS DiaPartoEco;");
		}catch(Exception ex){
			ex.getMessage();
		}
		return rs;
	}
	
	public ResultSet devCedula(String ndoc) {
		ResultSet rs=null;
		Statement st = null;
		try{
			Conexion con = new Conexion();
			st = con.conn.createStatement(); 
			rs = st.executeQuery("SELECT ced_paciente FROM aiepi_encuestagestante1 WHERE ced_paciente = '"+ndoc+"'");
		}catch(Exception ex){
			ex.getMessage();
		}
		return rs;
	}
	
	public ResultSet cargarSecciones() {
		ResultSet rs=null;
		Statement st = null;
		try{
			Conexion con = new Conexion();
			st = con.conn.createStatement(); 
			rs = st.executeQuery("SELECT DISTINCT seccion,tipo_respuesta FROM aiepi_conducta_seguir_gestantes");
		}catch(Exception ex){
			ex.getMessage();
		}
		return rs;
	}
	
	public ResultSet cargarConductas(String etapa) {
		ResultSet rs=null;
		Statement st = null;
		try{
			Conexion con = new Conexion();
			st = con.conn.createStatement(); 
			rs = st.executeQuery("SELECT descripcion,codigo_conducta,tipo_respuesta FROM aiepi_conducta_seguir_gestantes WHERE etapa = '"+etapa+"'");
		}catch(Exception ex){
			ex.getMessage();
		}
		return rs;
	}
	
	public ResultSet cargarPreguntas(String etapa) {
		ResultSet rs=null;
		Statement st = null;
		try{
			Conexion con = new Conexion();
			st = con.conn.createStatement(); 
			rs = st.executeQuery("SELECT descripcion,tipo_respuesta,detalle,codigo_pregunta FROM aiepi_pregunta_gestantes WHERE etapa = '"+etapa+"'");
		}catch(Exception ex){
			ex.getMessage();
		}
		return rs;
	}
	
	public ResultSet cargarPreguntas2(String etapa) {
		ResultSet rs=null;
		Statement st = null;
		try{
			Conexion con = new Conexion();
			st = con.conn.createStatement(); 
			rs = st.executeQuery("SELECT descripcion,tipo_respuesta,detalle,codigo_pregunta FROM aiepi_pregunta_gestantes WHERE etapa = '"+etapa+"' AND tipo_respuesta='r1_seleccion1'");
		}catch(Exception ex){
			ex.getMessage();
		}
		return rs;
	}
	
	public ResultSet cargarPreguntas3(String etapa) {
		ResultSet rs=null;
		Statement st = null;
		try{
			Conexion con = new Conexion();
			st = con.conn.createStatement(); 
			rs = st.executeQuery("SELECT descripcion,tipo_respuesta,detalle,codigo_pregunta FROM aiepi_pregunta_gestantes WHERE etapa = '"+etapa+"' AND tipo_respuesta='r2_seleccion1'");
		}catch(Exception ex){
			ex.getMessage();
		}
		return rs;
	}
	
	public ResultSet cargarRespuestas3(String etapa) {
		ResultSet rs=null;
		Statement st = null;
		try{
			Conexion con = new Conexion();
			st = con.conn.createStatement(); 
			rs = st.executeQuery("SELECT descripcion,tipo_respuesta,detalle,cod_resp FROM aiepi_respuestas_gestantes WHERE etapa = '"+etapa+"' AND tipo_respuesta='r2_seleccion1'");
		}catch(Exception ex){
			ex.getMessage();
		}
		return rs;
	}
	
	public ResultSet cargarRespuestas2(String etapa) {
		ResultSet rs=null;
		Statement st = null;
		try{
			Conexion con = new Conexion();
			st = con.conn.createStatement(); 
			rs = st.executeQuery("SELECT descripcion,tipo_respuesta,detalle,cod_resp FROM aiepi_respuestas_gestantes WHERE etapa = '"+etapa+"' AND tipo_respuesta='r1_seleccion1'");
		}catch(Exception ex){
			ex.getMessage();
		}
		return rs;
	}
	
	public ResultSet cargarRespuestas(String etapa) {
		ResultSet rs=null;
		Statement st = null;
		try{
			Conexion con = new Conexion();
			st = con.conn.createStatement(); 
			rs = st.executeQuery("SELECT descripcion,tipo_respuesta,detalle,cod_resp,codigo_respuesta_fk FROM aiepi_respuestas_gestantes WHERE etapa = '"+etapa+"'");
		}catch(Exception ex){
			ex.getMessage();
		}
		return rs;
	}
	
	public ResultSet cargarRemision(){
		ResultSet rs=null;
		Statement st = null;
		try{
			Conexion con = new Conexion();
			st = con.conn.createStatement(); 
			rs = st.executeQuery("SELECT item FROM aiepi_item_respuestas WHERE tipo_item = 'r2_seleccion3'");
		}catch(Exception ex){
			ex.getMessage();
		}
		return rs;
	}
	
	public ResultSet cargarItems(){
		ResultSet rs=null;
		Statement st = null;
		try{
			Conexion con = new Conexion();
			st = con.conn.createStatement(); 
			rs = st.executeQuery("SELECT item,tipo_item,cod_item FROM aiepi_item_respuestas");
		}catch(Exception ex){
			ex.getMessage();
		}
		return rs;
	}
	
	public ResultSet cargarItems2(){
		ResultSet rs=null;
		Statement st = null;
		try{
			Conexion con = new Conexion();
			st = con.conn.createStatement(); 
			rs = st.executeQuery("SELECT item,tipo_item,cod_item FROM aiepi_item_respuestas WHERE tipo_item='r1_seleccion1'");
		}catch(Exception ex){
			ex.getMessage();
		}
		return rs;
	}
	
	public void ActualizarA1(String CodPac,String Det,String CodPreg,String CodResp,String Resp,String CodUser){
		PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	System.out.println(Resp);
	     	st= con.conn.prepareStatement("UPDATE aiepi_gestante_21_a SET cod_respuesta = '"+CodResp+"'," +
					"respuesta = '"+Resp+"',detalle_resp = '"+Det+"', cod_usuario = '"+CodUser+"' " +
					"WHERE cod_paciente = '"+CodPac+"' AND fecha_encuesta = CURDATE() " +
					"AND cod_pregunta= '"+CodPreg+"';");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoDocumentos>>ActualizarA1 "+ex);
	     	ex.getMessage();	     
	     }
	}
	
	public void EliminarA1(String CodPac,String CodPreg,String CodResp){
		PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("DELETE FROM aiepi_gestante_21_a WHERE cod_paciente = '"+CodPac+"' AND cod_pregunta = '"+CodPreg+"' AND cod_respuesta = '"+CodResp+"';");
	     	st.executeUpdate();
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoDocumentos>>EliminarA1 "+ex);
	     	ex.getMessage();	     
	     }
	}
	
	public void Etapa21_AreaA(String CodPac,String CedPac,String Etapa,String Area,
			String FechaEnc,String Trimestre, String CodPreg,String CodResp,String Resp,
			String Detalle,String CodUser,String Codigo){
		String Query1="INSERT INTO aiepi_gestante_21_A(cod_paciente,ced_paciente,etapa,area_enc,fecha_encuesta,trimestre,"+
	            "cod_pregunta,cod_respuesta,respuesta,detalle_resp,cod_usuario,codigofk)"+
	            "VALUES (?,?,?,?,?,?,?,?,?,?,?,?);";
		PreparedStatement ps = null;
		try{
		    Conexion con=new Conexion();
			ps=con.conn.prepareStatement(Query1);
			ps.setString(1,CodPac);
			ps.setString(2,CedPac);
			ps.setString(3,Etapa);
			ps.setString(4,Area);
			ps.setString(5,FechaEnc);
			ps.setString(6,Trimestre);
			ps.setString(7,CodPreg);
			ps.setString(8,CodResp);
			ps.setString(9,Resp);
			ps.setString(10,Detalle);
			ps.setString(11,CodUser);
			ps.setString(12,Codigo);
			ps.executeUpdate();	
			ps.close();
			con.cerrar();
		}catch(Exception ex){
			System.out.print("Error en Consultas_pyp>>Etapa21_AreaA"+ex);
			ex.getMessage();
		}
	}
	
	public void Etapa21_AreaB(String CodPac,String CedPac,String Etapa,String Area,
			String FechaEnc,String Trimestre, String CodPreg,String Resp,
			String CodUser,String Codigo){
		String Query1="INSERT INTO aiepi_gestante_21_b(cod_paciente,ced_paciente,etapa,area_enc,fecha_encuesta,"+
	            "trimestre,cod_pregunta,respuesta,cod_usuario,codigofk)VALUES(?,?,?,?,?,?,?,?,?,?);";
		PreparedStatement ps = null;
		try{
		    Conexion con=new Conexion();
			ps=con.conn.prepareStatement(Query1);
			ps.setString(1,CodPac);
			ps.setString(2,CedPac);
			ps.setString(3,Etapa);
			ps.setString(4,Area);
			ps.setString(5,FechaEnc);
			ps.setString(6,Trimestre);
			ps.setString(7,CodPreg);
			ps.setString(8,Resp);
			ps.setString(9,CodUser);
			ps.setString(10,Codigo);
			ps.executeUpdate();	
			ps.close();
			con.cerrar();
		}catch(Exception ex){
			System.out.print("Error en Consultas_pyp>>Etapa21_AreaB"+ex);
			ex.getMessage();
		}
	}
	
	public void Etapa21_AreaC(String CodPac,String CedPac,String Etapa,String Area,
			String FechaEnc,String Trimestre, String CodPreg,String Resp,
			String CodUser,String Codigo){
		String Query1="INSERT INTO aiepi_gestante_21_c(cod_paciente,ced_paciente,etapa,area_enc,fecha_encuesta,"+
	            "trimestre,cod_pregunta,respuesta,cod_usuario,codigofk)VALUES(?,?,?,?,?,?,?,?,?,?);";
		PreparedStatement ps = null;
		try{
		    Conexion con=new Conexion();
			ps=con.conn.prepareStatement(Query1);
			ps.setString(1,CodPac);
			ps.setString(2,CedPac);
			ps.setString(3,Etapa);
			ps.setString(4,Area);
			ps.setString(5,FechaEnc);
			ps.setString(6,Trimestre);
			ps.setString(7,CodPreg);
			ps.setString(8,Resp);
			ps.setString(9,CodUser);
			ps.setString(10,Codigo);
			ps.executeUpdate();	
			ps.close();
			con.cerrar();
		}catch(Exception ex){
			System.out.print("Error en Consultas_pyp>>Etapa21_AreaC"+ex);
			ex.getMessage();
		}
	}
	
	public void Etapa21_AreaD(String CodPac,String CedPac,String Etapa,String Area,
			String FechaEnc,String Trimestre, String CodPreg,String CodResp,String CodItem,
			String Resp,String CodUser,String Codigo){
		String Query1="INSERT INTO aiepi_gestante_21_d(cod_paciente,ced_paciente,etapa,"+
	            "area_enc,fecha_encuesta,trimestre,cod_pregunta,"+
	            "cod_respuesta,cod_item,respuesta,cod_usuario,codigofk)"+
	            "VALUES (?,?,?,?,?,?,?,?,?,?,?,?);";
		PreparedStatement ps = null;
		try{
		    Conexion con=new Conexion();
			ps=con.conn.prepareStatement(Query1);
			ps.setString(1,CodPac);
			ps.setString(2,CedPac);
			ps.setString(3,Etapa);
			ps.setString(4,Area);
			ps.setString(5,FechaEnc);
			ps.setString(6,Trimestre);
			ps.setString(7,CodPreg);
			ps.setString(8,CodResp);
			ps.setString(9,Resp);
			ps.setString(10,CodItem);
			ps.setString(11,CodUser);
			ps.setString(12,Codigo);
			ps.executeUpdate();	
			ps.close();
			con.cerrar();
		}catch(Exception ex){
			System.out.print("Error en Consultas_pyp>>Etapa21_AreaD"+ex);
			ex.getMessage();
		}
	}
	
	public void Etapa22_Area1(String CodPac,String CedPac,String Etapa,String Area,
			String FechaEnc,String Trimestre, String CodPreg,String Resp,String DetResp,
			String CodUser,String Codigo){
		String Query1="INSERT INTO aiepi_gestante_22_1(cod_paciente,ced_paciente,etapa,area_enc,fecha_encuesta,"+
	            "trimestre,cod_pregunta,respuesta,det_respuesta,cod_usuario,codigofk)VALUES(?,?,?,?,?,?,?,?,?,?,?);";
		PreparedStatement ps = null;
		try{
		    Conexion con=new Conexion();
			ps=con.conn.prepareStatement(Query1);
			ps.setString(1,CodPac);
			ps.setString(2,CedPac);
			ps.setString(3,Etapa);
			ps.setString(4,Area);
			ps.setString(5,FechaEnc);
			ps.setString(6,Trimestre);
			ps.setString(7,CodPreg);
			ps.setString(8,Resp);
			ps.setString(9,DetResp);
			ps.setString(10,CodUser);
			ps.setString(11,Codigo);
			ps.executeUpdate();	
			ps.close();
			con.cerrar();
		}catch(Exception ex){
			System.out.print("Error en Consultas_pyp>>Etapa22_Area1"+ex);
			ex.getMessage();
		}
	}
	
	public void Etapa22_Area2(String CodPac,String CedPac,String Etapa,String Area,
			String FechaEnc,String Trimestre,String CodPreg,String Resp,String Comentarios,
			String CodUser,String Codigo){
		String Query1="INSERT INTO aiepi_gestante_22_2(cod_paciente,ced_paciente,etapa,area_enc,fecha_encuesta,"+
	            "trimestre,cod_pregunta,respuesta,comentarios,cod_usuario,codigofk)VALUES(?,?,?,?,?,?,?,?,?,?,?);";
		PreparedStatement ps = null;
		try{
		    Conexion con=new Conexion();
			ps=con.conn.prepareStatement(Query1);
			ps.setString(1,CodPac);
			ps.setString(2,CedPac);
			ps.setString(3,Etapa);
			ps.setString(4,Area);
			ps.setString(5,FechaEnc);
			ps.setString(6,Trimestre);
			ps.setString(7,CodPreg);
			ps.setString(8,Resp);
			ps.setString(9,Comentarios);
			ps.setString(10,CodUser);
			ps.setString(11,Codigo);
			ps.executeUpdate();	
			ps.close();
			con.cerrar();
		}catch(Exception ex){
			System.out.print("Error en Consultas_pyp>>Etapa22_Area2"+ex);
			ex.getMessage();
		}
	}
	
	public void Etapa22_Area3(String CodPac,String CedPac,String Etapa,String Area,
			String FechaEnc,String Trimestre, String CodPreg,String Resp,String Det,
			String CodUser,String Codigo){
		String Query1="INSERT INTO aiepi_gestante_22_3(cod_paciente,ced_paciente,etapa,area_enc,fecha_encuesta,"+
	            "trimestre,cod_pregunta,respuesta,detalle,cod_usuario,codigofk)VALUES(?,?,?,?,?,?,?,?,?,?,?);";
		PreparedStatement ps = null;
		try{
		    Conexion con=new Conexion();
			ps=con.conn.prepareStatement(Query1);
			ps.setString(1,CodPac);
			ps.setString(2,CedPac);
			ps.setString(3,Etapa);
			ps.setString(4,Area);
			ps.setString(5,FechaEnc);
			ps.setString(6,Trimestre);
			ps.setString(7,CodPreg);
			ps.setString(8,Resp);
			ps.setString(9,Det);
			ps.setString(10,CodUser);
			ps.setString(11,Codigo);
			ps.executeUpdate();	
			ps.close();
			con.cerrar();
		}catch(Exception ex){
			System.out.print("Error en Consultas_pyp>>Etapa22_Area3"+ex);
			ex.getMessage();
		}
	}
	
	public void Etapa23_Area1(String CodPac,String CedPac,String Etapa,String Area,
			String FechaEnc,String Trimestre, String CodPreg,String Resp,
			String CodUser,String Codigo){
		String Query1="INSERT INTO aiepi_gestante_23_1(cod_paciente,ced_paciente,etapa,area_enc,fecha_encuesta,"+
	            "trimestre,cod_pregunta,respuesta,cod_usuario,codigofk)VALUES(?,?,?,?,?,?,?,?,?,?);";
		PreparedStatement ps = null;
		try{
		    Conexion con=new Conexion();
			ps=con.conn.prepareStatement(Query1);
			ps.setString(1,CodPac);
			ps.setString(2,CedPac);
			ps.setString(3,Etapa);
			ps.setString(4,Area);
			ps.setString(5,FechaEnc);
			ps.setString(6,Trimestre);
			ps.setString(7,CodPreg);
			ps.setString(8,Resp);
			ps.setString(9,CodUser);
			ps.setString(10,Codigo);
			ps.executeUpdate();	
			ps.close();
			con.cerrar();
		}catch(Exception ex){
			System.out.print("Error en Consultas_pyp>>Etapa23_Area1"+ex);
			ex.getMessage();
		}
	}
	
	public void Etapa23_Area2(String CodPac,String CedPac,String Etapa,String Area,
			String FechaEnc,String Trimestre, String CodPreg,String Resp,
			String CodUser,String Codigo){
		String Query1="INSERT INTO aiepi_gestante_23_2(cod_paciente,ced_paciente,etapa,area_enc,fecha_encuesta,"+
	            "trimestre,cod_pregunta,respuesta,cod_usuario,codigofk)VALUES(?,?,?,?,?,?,?,?,?,?);";
		PreparedStatement ps = null;
		try{
		    Conexion con=new Conexion();
			ps=con.conn.prepareStatement(Query1);
			ps.setString(1,CodPac);
			ps.setString(2,CedPac);
			ps.setString(3,Etapa);
			ps.setString(4,Area);
			ps.setString(5,FechaEnc);
			ps.setString(6,Trimestre);
			ps.setString(7,CodPreg);
			ps.setString(8,Resp);
			ps.setString(9,CodUser);
			ps.setString(10,Codigo);
			ps.executeUpdate();	
			ps.close();
			con.cerrar();
		}catch(Exception ex){
			System.out.print("Error en Consultas_pyp>>Etapa23_Area2"+ex);
			ex.getMessage();
		}
	}
	
	public void Etapa23_Area3(String CodPac,String CedPac,String Etapa,String Area,
			String FechaEnc,String Trimestre, String CodPreg,String Resp,
			String CodUser,String Codigo){
		String Query1="INSERT INTO aiepi_gestante_23_3(cod_paciente,ced_paciente,etapa,area_enc,fecha_encuesta,"+
	            "trimestre,cod_pregunta,respuesta,cod_usuario,codigofk)VALUES(?,?,?,?,?,?,?,?,?,?);";
		PreparedStatement ps = null;
		try{
		    Conexion con=new Conexion();
			ps=con.conn.prepareStatement(Query1);
			ps.setString(1,CodPac);
			ps.setString(2,CedPac);
			ps.setString(3,Etapa);
			ps.setString(4,Area);
			ps.setString(5,FechaEnc);
			ps.setString(6,Trimestre);
			ps.setString(7,CodPreg);
			ps.setString(8,Resp);
			ps.setString(9,CodUser);
			ps.setString(10,Codigo);
			ps.executeUpdate();	
			ps.close();
			con.cerrar();
		}catch(Exception ex){
			System.out.print("Error en Consultas_pyp>>Etapa23_Area3"+ex);
			ex.getMessage();
		}
	}
	
	public void Etapa24_Area1(String CodPac,String CedPac,String Etapa,String Area,
			String FechaEnc,String Trimestre,String CodPreg,String FecLab,String Resp,
			String CodUser,String Codigo){
		String Query1="INSERT INTO aiepi_gestante_24_1(cod_paciente,ced_paciente,etapa,area_enc,fecha_encuesta,"+
	            "trimestre,cod_pregunta,fecha_lab,respuesta,cod_usuario,codigofk)VALUES(?,?,?,?,?,?,?,?,?,?,?);";
		PreparedStatement ps = null;
		try{
		    Conexion con=new Conexion();
			ps=con.conn.prepareStatement(Query1);
			ps.setString(1,CodPac);
			ps.setString(2,CedPac);
			ps.setString(3,Etapa);
			ps.setString(4,Area);
			ps.setString(5,FechaEnc);
			ps.setString(6,Trimestre);
			ps.setString(7,CodPreg);
			ps.setString(8,FecLab);
			ps.setString(9,Resp);
			ps.setString(10,CodUser);
			ps.setString(11,Codigo);
			ps.executeUpdate();	
			ps.close();
			con.cerrar();
		}catch(Exception ex){
			System.out.print("Error en Consultas_pyp>>Etapa24_Area1"+ex);
			ex.getMessage();
		}
	}
	
	public void Etapa24_Area2(String CodPac,String CedPac,String Etapa,String Area,
			String FechaEnc,String Trimestre,String Seccion,String CodPreg,String Resp,
			String CodUser,String Codigo){
		String Query1="INSERT INTO aiepi_gestante_24_2(cod_paciente,ced_paciente,etapa,area_enc,fecha_encuesta,"+
	            "trimestre,seccion,codigo_conducta,respuesta,cod_usuario,codigofk)VALUES(?,?,?,?,?,?,?,?,?,?,?);";
		PreparedStatement ps = null;
		try{
		    Conexion con=new Conexion();
			ps=con.conn.prepareStatement(Query1);
			ps.setString(1,CodPac);
			ps.setString(2,CedPac);
			ps.setString(3,Etapa);
			ps.setString(4,Area);
			ps.setString(5,FechaEnc);
			ps.setString(6,Trimestre);
			ps.setString(7,Seccion);
			ps.setString(8,CodPreg);
			ps.setString(9,Resp);
			ps.setString(10,CodUser);
			ps.setString(11,Codigo);
			ps.executeUpdate();	
			ps.close();
			con.cerrar();
		}catch(Exception ex){
			System.out.print("Error en Consultas_pyp>>Etapa24_Area2"+ex);
			ex.getMessage();
		}
	}
	
	public void guardarEncGestante1(String numHijo,String numPadre,String numNieto,String numConyugue,
			String FechaEnc,String FechaParto, String Analfabeta,String primCompleta,String TrimAc,
			String secCompleta,String bacCompleto,String noFormal,String Universitario,String convSolo,
			String convHijos,String convPadres,String convNietos,String convConyugue,String CedPaciente,String FechaConp,String TipGes,String CodPac,String TipDoc,String CodUser,String fecEco){
		String Query1="insert into aiepi_encuestagestante1(cod_pacientefk,tipo_documento,ced_paciente,fecha_Encuesta,fecha_Concepcion,tipo_Gestacion,fecha_PartoGestante,"+
	            "trim_Encuesta,nivEdu_Analfabeta,nivEdu_primCompleta,nivEdu_SecCompleta,nivEdu_BacCompleto,"+
	            "nivEdu_EduNoFormal,nivEdu_Universitario,convivientes_Solo,convHijos,numHijos,ConvPadres,"+
	            "numPadres,convNietos,numNietos,convConyugue,numConyugue,cod_user_login,fecha_ecografia)"+
	            "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
		PreparedStatement ps = null;
		try{
		    Conexion con=new Conexion();
			ps=con.conn.prepareStatement(Query1);
			ps.setString(1,CodPac);
			ps.setString(2,TipDoc);
			ps.setString(3,CedPaciente);
			ps.setString(4,FechaEnc);
			ps.setString(5,FechaConp);
			ps.setString(6,TipGes);
			ps.setString(7,FechaParto);
			ps.setString(8,TrimAc);
			ps.setString(9,Analfabeta);
			ps.setString(10,primCompleta);
			ps.setString(11,secCompleta);
			ps.setString(12,bacCompleto);
			ps.setString(13,noFormal);
			ps.setString(14,Universitario);
			ps.setString(15,convSolo);
			ps.setString(16,convHijos);
			ps.setString(17,numHijo);
			ps.setString(18,convPadres);
			ps.setString(19,numPadre);
			ps.setString(20,convNietos);
			ps.setString(21,numNieto);
			ps.setString(22,convConyugue);
			ps.setString(23,numConyugue);
			ps.setString(24,CodUser);
			ps.setString(25,fecEco);
			ps.executeUpdate();	
			ps.close();
			con.cerrar();
		}catch(Exception ex){
			System.out.print("Error en Consultas_pyp>>guardarEncGes1"+ex);
			ex.getMessage();
		}
	}
	
	public java.sql.ResultSet ObtenerFormatosPermitidosUsuario(String CodigoUsuario){
		/**
		 * se obtienen los formatos al cual tiene acceso los usuarios
		 * para asignarselos algun paciente.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT distinct hf.codigo,hf.nombre_formato FROM hic_formato hf,seg_usuariosformato suf,seg_usuario su WHERE suf.codigoFormato_fk=hf.codigo and suf.codigoUsuario_fk=su.usu_codigo and su.usu_codigo='"+CodigoUsuario+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en Metodo ObtenerPaciente "+ex);
        }	
        return rs;
    }
}
