package agm_metodo;

import java.sql.PreparedStatement;
import java.sql.Statement;

import adm_logica.Conexion;
import agm_bean.CrearParametros;

public class MetodoParametros {
	
	public void ActualizarParametros(String HoraInicial,String HoraFinal,String tiempoCita,String CodigoParametro){
	    /**
	     * actualiza todos los datos del medico seleccionado.
	     */
		
		PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("update agm_horadia set horaInicio='"+HoraInicial+"',horaFinal='"+HoraFinal+"',tiempoCita='"+tiempoCita+"' where codigo='"+CodigoParametro+"'");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("ERROR EN MetodoParametros>>ActualizarParametros "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public java.sql.ResultSet BuscarFechaHoraSolo(String Codigo){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM agm_horariomedico WHERE codigo='"+Codigo+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoParametros>>BuscarFechaHora "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarDatosMedico(String CodUsu){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT am.codEspe_fk,am.codigo FROM agm_medico am,seg_datos_personales sd,seg_usuario su WHERE sd.dat_codigo=su.dat_codigo_fk AND sd.numeroDocumento=am.numeroDocumento AND su.usu_codigo="+CodUsu+" ");
        	System.out.println("SELECT am.codEspe_fk,am.codigo FROM agm_medico am,seg_datos_personales sd,seg_usuario su WHERE sd.dat_codigo=su.dat_codigo_fk AND sd.numeroDocumento=am.numeroDocumento AND su.usu_codigo="+CodUsu+" ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoParametros>>BuscarDatosMedico "+ex);
        }	
        return rs;
    }



	public void CrearHorarioMedico1(String HorasMedico,String FechaMedico,String estado,String CodigoMedico,String NombreCompleto){
		/**
		 * creamos el Medico tiene como parametros todos los datos que se necesitan para llenar.
		 */
		
		CrearParametros cp = new CrearParametros();
		cp.setHorasMedico(HorasMedico);
		cp.setFechaMedico(FechaMedico);
		cp.setestado(estado);
		cp.setCodigoMedico(CodigoMedico);

	
		
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("insert into agm_horariomedico(horas,fechas,estado,codMedico_fk,NombrePaciente)values(?,?,?,?,?)");
			    ps.setString(1,cp.getHorasMedico());
			    ps.setString(2,cp.getFechaMedico());
			    ps.setString(3,cp.getestado());
			    ps.setString(4,cp.getCodigoMedico());
			    ps.setString(5,NombreCompleto);
			    
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoMedicos>>CrearHorarioMedico1 "+ex);
			}

		}

	
	public void EliminarCalendarioDiaMedico(String CodigoMedico,String sql){
     	System.out.println("DELETE FROM agm_horariomedico  WHERE codMedico_fk='"+CodigoMedico+"' "+sql+"");
		
		PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("DELETE FROM agm_horariomedico  WHERE codMedico_fk='"+CodigoMedico+"' "+sql+"");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("ERROR EN MetodoParametros>>EliminarDiaMedico "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public void EliminarDiaMedico(String CodigoMedico,String Dia,String sql){
     	System.out.println("DELETE FROM agm_horariomedico  WHERE codMedico_fk='"+CodigoMedico+"' AND DATE_FORMAT(fechas,'%W')='"+Dia+"'  AND estado='0' "+sql+"");
		
		PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("DELETE FROM agm_horariomedico  WHERE codMedico_fk='"+CodigoMedico+"' AND DATE_FORMAT(fechas,'%W')='"+Dia+"'  AND estado='0' "+sql+"");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("ERROR EN MetodoParametros>>EliminarDiaMedico "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public void OmitirParametro(String CodigoParametro){
	    /**
	     * actualiza todos los datos del medico seleccionado.
	     */
		
		PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("delete from agm_horadia where codigo='"+CodigoParametro+"'");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("ERROR EN MetodoParametros>>OmitirParametro "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	

	public void CrearParametro(String Dia,String HoraInicial,String HoraFinal,String tiempoCita){
		/**
		 * creamos los parametros de la hora y dias de atencion.
		 */
		
		CrearParametros cp = new CrearParametros();
		
		cp.setDia(Dia);
		cp.setHoraInicial(HoraInicial);
		cp.setHoraFinal(HoraFinal);
		cp.settiempoCita(tiempoCita);
		
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("insert into agm_horadia(dia,horaInicio,horaFinal,tiempoCita)values(?,?,?,?)");
			    ps.setString(1,cp.getDia());
			    ps.setString(2,cp.getHoraInicial());
			    ps.setString(3,cp.getHoraFinal());
			    ps.setString(4,cp.gettiempoCita());					    
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoParametros>>CrearParametro "+ex);
			}

		}
	
	
	public void EliminarParametrosRegistroMedico(String CodHorMed){
	    /**
	     * elimina todos los datos del los parametros de hora y fecha.
	     */
		
		PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("DELETE FROM agm_horariomedico WHERE codigo='"+CodHorMed+"'");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("ERROR EN MetodoParametros>>EliminarParametrosRegistroMedico "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	
	public void EliminarParametros(){
	    /**
	     * elimina todos los datos del los parametros de hora y fecha.
	     */
		
		PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("delete from agm_horadia");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("ERROR EN MetodoParametros>>EliminarParametros "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public java.sql.ResultSet DetalleActivosHorarioMedicoPorDia(String CodigoMedico,String FechaMes){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT ah.*,am.tiempo_consulta,SUBSTRING(ah.horas, 7, 3) AS jornada FROM  agm_horariomedico ah,agm_medico am WHERE ah.codMedico_fk = am.codigo AND am.codigo = '"+CodigoMedico+"' AND ah.fechas LIKE '%"+FechaMes+"%'  ORDER BY ah.fechas,jornada");
        	//System.out.println("SELECT ah.*,am.tiempo_consulta FROM  agm_horariomedico ah,agm_medico am WHERE ah.codMedico_fk = am.codigo AND am.codigo = '"+CodigoMedico+"' AND ah.fechas LIKE '%"+FechaMes+"%'  ORDER BY ah.fechas");
        }
        
        catch(Exception ex){
        	System.out.println("Error en MetodoParametros>>DetalleActivosHorarioMedico "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet DetalleActivosHorarioMedico(String CodigoMedico,String FechaMes){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT ah.*,am.tiempo_consulta FROM  agm_horariomedico ah,agm_medico am WHERE ah.codMedico_fk = am.codigo AND am.codigo = '"+CodigoMedico+"' AND ah.fechas LIKE '%"+FechaMes+"%'  ORDER BY ah.fechas");
        	//System.out.println("SELECT ah.*,am.tiempo_consulta FROM  agm_horariomedico ah,agm_medico am WHERE ah.codMedico_fk = am.codigo AND am.codigo = '"+CodigoMedico+"' AND ah.fechas LIKE '%"+FechaMes+"%'  ORDER BY ah.fechas");
        }
        
        catch(Exception ex){
        	System.out.println("Error en MetodoParametros>>DetalleActivosHorarioMedico "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet DetalleActivosHorarioMedicoAgrupado(String CodigoMedico,String FechaMes){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT ah.*,am.tiempo_consulta FROM  agm_horariomedico ah,agm_medico am WHERE ah.codMedico_fk = am.codigo AND am.codigo = '"+CodigoMedico+"' AND ah.fechas LIKE '%"+FechaMes+"%' GROUP BY ah.fechas  ORDER BY ah.fechas");
        	//System.out.println("SELECT ah.*,am.tiempo_consulta FROM  agm_horariomedico ah,agm_medico am WHERE ah.codMedico_fk = am.codigo AND am.codigo = '"+CodigoMedico+"' AND ah.fechas LIKE '%"+FechaMes+"%'  ORDER BY ah.fechas");
        }
        
        catch(Exception ex){
        	System.out.println("Error en MetodoParametros>>DetalleActivosHorarioMedico "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet VerDiasAtencionMedico(String CodigoMedico){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT ahm.*,DATE_FORMAT(ahm.fechas,'%W') AS Dia,SUBSTRING(ahm.horas, 7, 3) AS jornada FROM agm_medico am,agm_horariomedico ahm WHERE am.codigo=ahm.codMedico_fk AND SUBSTRING(ahm.horas, 7, 3)!='MD' AND am.codigo='"+CodigoMedico+"' GROUP BY Dia,jornada ORDER BY DATE_FORMAT(ahm.fechas,'%w')");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoParametros>>VerDiasAtencionMedico "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarMesesActivosHorarioMedico(String CodigoMedico){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT  ah.*,COUNT(ah.codigo) AS cantidad,MONTH(ah.fechas),YEAR(ah.fechas),am.tiempo_consulta FROM agm_horariomedico ah,agm_medico am WHERE ah.codMedico_fk=am.codigo AND am.codigo='"+CodigoMedico+"' AND ah.fechas>CURDATE() GROUP BY MONTH(ah.fechas) ORDER BY ah.fechas ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoParametros>>BuscarMesesActivosHorarioMedico "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarFechaHora2(String HorasMedico,String FechaMedico,String CodigoMedico){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM agm_horariomedico WHERE horas='"+HorasMedico+"' AND fechas='"+FechaMedico+"' AND codMedico_fk='"+CodigoMedico+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoParametros>>BuscarFechaHora "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet CodigoTercero(String NitEnti){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM cont_terceros WHERE numero_documento LIKE '%"+NitEnti+"%'");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoParametros>>CodigoTercero "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet CodigoCuentaPrograma(String CodProg){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM cont_ninterfaz_facturacion WHERE programafk="+CodProg+" ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoParametros>>CodigoCuentaPrograma "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarFechaHora3(String Codigo){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM agm_horariomedico WHERE codigo='"+Codigo+"' and estado=1 ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoParametros>>BuscarFechaHora "+ex);
        }	
        return rs;
    }
	
	
	
	public java.sql.ResultSet BuscarFechaHora(String HorasMedico,String FechaMedico,String CodigoMedico){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM agm_horariomedico WHERE horas='"+HorasMedico+"' AND fechas='"+FechaMedico+"' AND codMedico_fk='"+CodigoMedico+"' and estado!=0 AND NombrePaciente!=null ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoParametros>>BuscarFechaHora "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarContenido(){	
		/**
		 * consulta que busca si la tabla agm_horadia tiene algun dato.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo from agm_horadia");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoParametros>>BuscarContenido "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarDatosParametros(){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo,dia,horaInicio,horaFinal,tiempoCita from agm_horadia");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoParametros>>BuscarDatosParametros "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet DiaSemana(String FechaActual){	
		/**
		 * consulta que dia es en una fecha estipulada
		 * retorna si es lunes,martes,miercoles,jueves,viernes,sabado,domingo.
		 * en ingles.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT DAYNAME('"+FechaActual+"') ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoParametros>>DiaSemana "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarLapsoMedico(String CodMedico){	
		
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT am.* FROM agm_medico am WHERE am.codigo="+CodMedico+"");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoParametros>>BuscarLapsoMedico "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarParametros(){	
		/**
		 * consulta que busca los parametros de la institucion
		 * como son los dias de atencion hora inicio , hora finalizacion y tiempo de la consulta.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select ahd.dia,ahd.horaInicio,ahd.horaFinal,ahd.tiempoCita from agm_horadia ahd");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoParametros>>BuscarParametros "+ex);
        }	
        return rs;
    }
	
}
