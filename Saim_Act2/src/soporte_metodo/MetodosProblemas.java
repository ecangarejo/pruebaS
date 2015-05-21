package soporte_metodo;
import java.sql.PreparedStatement;
import java.sql.Statement;

import adm_logica.Conexion;


public class MetodosProblemas {
	
	public java.sql.ResultSet BuscarProblemasTodos(){	
		/**
		 * consulta que busca todos los problemas
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select descripcion, codigo from sop_problemas");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoEspecialidades>>BuscarEspecialidadTodas "+ex);
        }	
        return rs;
    }
	public java.sql.ResultSet BuscarProblemasReq(){	
		/**
		 * consulta que busca todos los problemas de Requermiento
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select descripcion, codigo from sop_problemas_requerimientos");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoEspecialidades>>BuscarProblemasReq "+ex);
        }	
        return rs;
    }
   
	public java.sql.ResultSet MostrarAreas(){
		
		java.sql.ResultSet rs = null;
		Statement st = null;
		try{
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs=st.executeQuery("select codigo, nombre from sop_area");
			
		}catch(Exception ex){
			System.out.println("Error en el MetodoProblemas"+ex);
		}
		return rs;
	}
	
public java.sql.ResultSet ObtenerPrioridad(){
		
		java.sql.ResultSet rs = null;
		Statement st = null;
		try{
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs=st.executeQuery("select Pr.codigo, Pr.descripcion from sop_prioridad Pr");
			
		}catch(Exception ex){
			System.out.println("Error en el MetodoProblemas"+ex);
		}
		return rs;
	}
	
public java.sql.ResultSet MostrarSubAreas(String CodArea){
	
	java.sql.ResultSet rs = null;
	Statement st = null;
	try{
		Conexion con = new Conexion();
		st = con.conn.createStatement();
		rs=st.executeQuery("select Su.codigo, Su.nombre from sop_area Sa, sop_subarea Su where Su.cod_sop_area_fk=Sa.codigo and Su.cod_sop_area_fk='"+CodArea+"'");
		
	}catch(Exception ex){
		System.out.println("Error en el MetodoSubAreas"+ex);
	}
	return rs;
}

public java.sql.ResultSet ObtenerEstado(){
	
	java.sql.ResultSet rs = null;
	Statement st = null;
	try{
		Conexion con = new Conexion ();
		st = con.conn.createStatement();
		rs = st.executeQuery("select codigo from sop_estado where codigo='1'");
	}catch(Exception ex){
		System.out.println("Error en MetodosProblemas>>ObtenerEestado"+ex);
	}
	
	return rs;
}

public java.sql.ResultSet ObtenerCodigoSolicitud(){
	
	java.sql.ResultSet rs = null;
	Statement st = null;
	try{
		Conexion con = new Conexion ();
		st = con.conn.createStatement();
		rs = st.executeQuery("select codigo from sop_solicitud");
	}catch(Exception ex){
		System.out.println("Error en MetodosProblemas>>ObtenerCodigoSolicitud"+ex);
	}
	
	return rs;
}

public java.sql.ResultSet ObtenerCodigoDetSolicitud(){
	
	java.sql.ResultSet rs = null;
	Statement st = null;
	try{
		Conexion con = new Conexion ();
		st = con.conn.createStatement();
		rs = st.executeQuery("select codigo from sop_detalle_solicitud");
	}catch(Exception ex){
		System.out.println("Error en MetodosProblemas>>ObtenerCodigoDetSolicitud"+ex);
	}
	
	return rs;
}



public java.sql.ResultSet ObtenerUsuario(String Codigou){
	int cod=Integer.parseInt(Codigou);
	java.sql.ResultSet rs = null;
	Statement st = null;
	try{
		Conexion con = new Conexion();
		st = con.conn.createStatement();
		rs = st.executeQuery("select usuario from seg_usuario where usu_codigo="+cod);
	}catch(Exception ex){
		System.out.println("Error en el metodo ObtenerUsuario"+ex);
		
	}
	
	
	return rs;
}

public java.sql.ResultSet MostrarProblema(String CodProblema){
	
	java.sql.ResultSet rs = null;
	Statement st = null;
	try{
		Conexion con = new Conexion();
		st = con.conn.createStatement();
		rs = st.executeQuery("select descripcion from sop_problemas where codigo='"+CodProblema+"'");
	}catch(Exception ex){
		System.out.println("Error en el metodo MostrarProblema"+ex);
		
	}
	
	
	return rs;
}


	
public void IngresarSolicitud( String CodSubArea, String Codigou, String horaactual, String fechaactual){
	
	try{
		PreparedStatement ps = null;
	    Conexion con=new Conexion();
	    ps=con.conn.prepareStatement("insert into sop_solicitud(cod_SubArea_fk,cod_Usuario_fk,horaSolicitud,fechaSolicitud)values(?,?,?,?)");
	    ps.setString(1,CodSubArea);
	    ps.setString(2,Codigou);
	    ps.setString(3,horaactual);
	    ps.setString(4,fechaactual);
	    ps.executeUpdate();
		ps.close();
		con.cerrar();		
		
	}catch(Exception ex){
    	System.out.println("ERROR EN MetodosProblemas>>IngresarSolicitud "+ex);
		
	}
}

public void IngresarDetalle( String CodEstado, String Observa, int CodSolicitud,String codiprioridad, String TipoSolicitud){
	try{
		PreparedStatement ps = null;
		Conexion con = new Conexion();
		ps = con.conn.prepareStatement("insert into sop_detalle_solicitud(cod_est_fk,observacion,cod_solicitud_fk,cod_prioridad_fk,tiposolicitud)values(?,?,?,?,?)");
		ps.setString(1, CodEstado);
		ps.setString(2, Observa);
		ps.setInt(3, CodSolicitud);
		ps.setString(4, codiprioridad);
		ps.setString(5, TipoSolicitud);
		ps.executeUpdate();
		ps.close();
		con.cerrar();
	}catch(Exception ex){
		System.out.println("ERROR EN MetodosProblemas>>IngresarDetalle "+ex);
		
	}
	
	
}
	
public void IngresarDetalleProblema( String CodProble, String Observacion, String CodiEstado , int CodSolicitud ){
	try{
		PreparedStatement ps = null;
		Conexion con = new Conexion();
		ps = con.conn.prepareStatement("insert into sop_detalle_problema(cod_problema_fk,observacion,cod_est_problema_fk,cod_solicitud_fk)values(?,?,?,?)");
		ps.setString(1, CodProble);
		ps.setString(2, Observacion);
		ps.setString(3, CodiEstado);
		ps.setInt(4, CodSolicitud);
		ps.executeUpdate();
		ps.close();
		con.cerrar();
	}catch(Exception ex){
		System.out.println("ERROR EN MetodosProblemas>>IngresarDetalleProblema "+ex);
		
	}
	
	
}
public void IngresarDetalleProblemaReq( String CodProble, String Observacion, String CodiEstado , int CodSolicitud ){
	try{
		PreparedStatement ps = null;
		Conexion con = new Conexion();
		ps = con.conn.prepareStatement("insert into sop_detalle_problema(cod_pro_req_fk,observacion,cod_est_problema_fk,cod_solicitud_fk)values(?,?,?,?)");
		ps.setString(1, CodProble);
		ps.setString(2, Observacion);
		ps.setString(3, CodiEstado);
		ps.setInt(4, CodSolicitud);
		ps.executeUpdate();
		ps.close();
		con.cerrar();
	}catch(Exception ex){
		System.out.println("ERROR EN MetodosProblemas>>IngresarDetalleProblemaReq "+ex);
		
	}
	
	
}


}
