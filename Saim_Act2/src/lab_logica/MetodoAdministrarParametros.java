package lab_logica;

import java.sql.PreparedStatement;
import java.sql.Statement;

import adm_logica.Conexion;
import agm_bean.AsignarCita;
import agm_bean.CrearParametros;

public class MetodoAdministrarParametros {
	
	public void CrearAuditoriaBackup(String NombreArchivo,String NombreUsuario){
		/**
		 */
		CrearParametros cp = new CrearParametros();
		cp.setNombreArchivo(NombreArchivo);
		cp.setNombreUsuario(NombreUsuario);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("insert into backups(NombreArchivo,NombreUsuario)values(?,?)");
			    ps.setString(1,cp.getNombreArchivo());
			    ps.setString(2,cp.getNombreUsuario());
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoAdministrarParametros>>CrearAuditoriaBackup "+ex);
			}
		}
	
public java.sql.ResultSet BuscarDiagnosticos(){
		
	    java.sql.ResultSet rs=null;
	    Statement st = null;
	    try{
	    	Conexion con=new Conexion();
	    	st = con.conn.createStatement();
	    	rs=st.executeQuery("SELECT * FROM cie10 ");
	    }
	    catch(Exception ex){
	    	System.out.println("ERROR EN MetodoAdministrarParametros>>BuscarDiagnosticosIngreso "+ex);
	    }
	    return rs;
	}

public void ActualizarDiagnosticosIngreso(String CodDiag_fk, String CodDiagnostico){	
    PreparedStatement st = null;
     try{
     	Conexion con=new Conexion();
     	st= con.conn.prepareStatement("UPDATE hic_diagnosticoingreso SET CodDiag_fk="+CodDiag_fk+" WHERE codDiagnostico='"+CodDiagnostico+"'");
     	           System.out.println("UPDATE hic_diagnosticoingreso SET CodDiag_fk="+CodDiag_fk+" WHERE codDiagnostico='"+CodDiagnostico+"'");
     
     	st.executeUpdate();
     	st.close();
     	con.cerrar();
     	
     }
     catch(Exception ex){
     	System.out.println("ERROR EN MetodoAdministrarParametros>>ActualizarDiagnosticosIngreso:_ "+ex);
     	ex.getMessage();
     }	
 }

public void ActualizarDiagnosticosEgreso(String CodDiag_fk, String CodDiagnostico){	
    PreparedStatement st = null;
     try{
     	Conexion con=new Conexion();
     	st= con.conn.prepareStatement("UPDATE hic_diagnosticoegreso SET CodDiag_fk="+CodDiag_fk+" WHERE codDiagnostico='"+CodDiagnostico+"'");
     			   System.out.println("UPDATE hic_diagnosticoegreso SET CodDiag_fk="+CodDiag_fk+" WHERE codDiagnostico='"+CodDiagnostico+"'");     	
     	st.executeUpdate();
     	st.close();
     	con.cerrar();
     	
     }
     catch(Exception ex){
     	System.out.println("ERROR EN MetodoAdministrarParametros>>ActualizarDiagnosticosEgreso:_ "+ex);
     	ex.getMessage();
     }	
 }
	
	
public java.sql.ResultSet BuscarAreas(){
		
	    java.sql.ResultSet rs=null;
	    Statement st = null;
	    try{
	    	Conexion con=new Conexion();
	    	st = con.conn.createStatement();
	    	rs=st.executeQuery("select codigo,nombre from lab_area where codigo>0");
	    }
	    catch(Exception ex){
	    	System.out.println("ERROR EN MetodoAdministrarParametros>>BuscarAreas "+ex);
	    }
	    return rs;
	}

public java.sql.ResultSet NombreArea(String CodigoArea){
	
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery("select nombre from lab_area where codigo="+CodigoArea+"");
    }
    catch(Exception ex){
    	System.out.println("ERROR EN MetodoAdministrarParametros>>NombreArea "+ex);
    }
    return rs;
}
public void ActualizarArea(String NombreArea, String CodigoArea){	
    PreparedStatement st = null;
     try{
     	Conexion con=new Conexion();
     	st= con.conn.prepareStatement("update lab_area set nombre='"+NombreArea+"' where codigo='"+CodigoArea+"'");
     	st.executeUpdate();
     	st.close();
     	con.cerrar();
     	
     }
     catch(Exception ex){
     	System.out.println("ERROR EN MetodoAdministrarParametros>>ActualizarArea:_ "+ex);
     	ex.getMessage();
     }	
 }

public java.sql.ResultSet BuscarSubareas(String CodigoArea){
	
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery("select lsub.codigo,lsub.nombre from lab_area lar,lab_subarea lsub where lsub.codarea_fk="+CodigoArea+" and lar.codigo=lsub.codarea_fk");
    }
    catch(Exception ex){
    	System.out.println("ERROR EN MetodoAdministrarParametros>>BuscarSubareas "+ex);
    }
    return rs;
}

public java.sql.ResultSet NombreSubArea(String CodigoSubarea){
	
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery("select nombre from lab_subarea where codigo="+CodigoSubarea+"");
    }
    catch(Exception ex){
    	System.out.println("ERROR EN MetodoAdministrarParametros>>NombreSubArea "+ex);
    }
    return rs;
}

public void ActualizarSubArea(String NombreSubarea, String CodigoSubarea){	
    PreparedStatement st = null;
     try{
     	Conexion con=new Conexion();
     	st= con.conn.prepareStatement("update lab_subarea set nombre='"+NombreSubarea+"' where codigo="+CodigoSubarea+" ");
     	st.executeUpdate();
     	st.close();
     	con.cerrar();
     	
     }
     catch(Exception ex){
     	System.out.println("ERROR EN MetodoAdministrarParametros>>ActualizarSubArea:_ "+ex);
     	ex.getMessage();
     }	
 }

public java.sql.ResultSet BuscarExamenesArea(String CodigoArea){	
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery("select codigo,nombre from lab_examen where codigoarea_fk='"+CodigoArea+"'");
    }
    catch(Exception ex){
    	System.out.println("ERROR EN MetodoAdministrarParametros>>BuscarExamenesArea "+ex);
    }
    return rs;
}

public java.sql.ResultSet NombreExamen(String CodigoExamen){	
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery("select nombre from lab_examen where codigo="+CodigoExamen+" ");
    }
    catch(Exception ex){
    	System.out.println("ERROR EN MetodoAdministrarParametros>>NombreExamenesArea "+ex);
    }
    return rs;
}

public void ActualizarExamen(String CodigoExamen,String NombreExamen){	
    PreparedStatement st = null;
     try{
     	Conexion con=new Conexion();
     	st= con.conn.prepareStatement("update lab_examen set nombre='"+NombreExamen+"' where codigo="+CodigoExamen+" ");
     	st.executeUpdate();
     	st.close();
     	con.cerrar();
     	
     }
     catch(Exception ex){
     	System.out.println("ERROR EN MetodoAdministrarParametros>>ActualizarExamenArea:_ "+ex);
     	ex.getMessage();
     }	
 }

public java.sql.ResultSet BuscarExamenesSubarea(String CodigoSubarea){	
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery("select codigo,nombre from lab_examen where codigosubarea_fk='"+CodigoSubarea+"'");
    }
    catch(Exception ex){
    	System.out.println("ERROR EN MetodoAdministrarParametros>>BuscarExamenesSubarea "+ex);
    }
    return rs;
}

public java.sql.ResultSet BuscarUnidad(){	
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery("select codigo,nombre from lab_unidad");
    }
    catch(Exception ex){
    	System.out.println("ERROR EN MetodoAdministrarParametros>>BuscarUnidad "+ex);
    }
    return rs;
}

public java.sql.ResultSet VerNombreUnidad(String CodigoUnidad){	
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery("select codigo,nombre from lab_unidad where codigo="+CodigoUnidad+"");
    }
    catch(Exception ex){
    	System.out.println("ERROR EN MetodoAdministrarParametros>>VerNombreUnidad "+ex);
    }
    return rs;
}

public void ActualizarUnidad(String CodigoUnidad,String NombreUnidad){	
    PreparedStatement st = null;
     try{
     	Conexion con=new Conexion();
     	st= con.conn.prepareStatement("update lab_unidad set nombre='"+NombreUnidad+"' where codigo="+CodigoUnidad+" ");
     	st.executeUpdate();
     	st.close();
     	con.cerrar();
     	
     }
     catch(Exception ex){
     	System.out.println("ERROR EN MetodoAdministrarParametros>>ActualizarUnidad:_ "+ex);
     	ex.getMessage();
     }	
 }


public void EliminarArea(String CodigoArea){	
    PreparedStatement st = null;
     try{
     	Conexion con=new Conexion();
     	st= con.conn.prepareStatement("delete from lab_area where codigo="+CodigoArea+"");
     	st.executeUpdate();
     	st.close();
     	con.cerrar();
     	
     }
     catch(Exception ex){
     	System.out.println("ERROR EN MetodoAdministrarParametros>>EliminarArea:_ "+ex);
     	ex.getMessage();
     }	
 }

public void EliminarSubarea(String CodigoSubarea){	
    PreparedStatement st = null;
     try{
     	Conexion con=new Conexion();
     	st= con.conn.prepareStatement("delete from lab_subarea where codigo="+CodigoSubarea+"");
     	st.executeUpdate();
     	st.close();
     	con.cerrar();
     	
     }
     catch(Exception ex){
     	System.out.println("ERROR EN MetodoAdministrarParametros>>EliminarSubarea:_ "+ex);
     	ex.getMessage();
     }	
 }

public void EliminarExamen(String CodigoExamen){	
    PreparedStatement st = null;
     try{
     	Conexion con=new Conexion();
     	st= con.conn.prepareStatement("delete from lab_examen where codigo="+CodigoExamen+"");
     	st.executeUpdate();
     	st.close();
     	con.cerrar();
     	
     }
     catch(Exception ex){
     	System.out.println("ERROR EN MetodoAdministrarParametros>>EliminarExamen:_ "+ex);
     	ex.getMessage();
     }	
 }

}
