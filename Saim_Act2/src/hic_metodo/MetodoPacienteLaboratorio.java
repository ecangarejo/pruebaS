/**
 * logica: MetodoPacienteLaboratorio se encuentran las inserciones,consultas y actualizaciones
 * para ver los estudios que tienen pendientes los pacientes
 * se ve reflejado en la vista de laboratorio.
*/
package hic_metodo;

import java.sql.PreparedStatement;
import java.sql.Statement;

import adm_logica.Conexion;


public class MetodoPacienteLaboratorio {
	
	public void EliminarAsignacion(String CodAsignacion){
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("delete from ord_pac_lab where codigo="+CodAsignacion+"");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("ERROR EN MetodoPacienteLaboratorio>>EliminarAsignacion "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public java.sql.ResultSet ObtenerAsignacion(String CodAsignacion){
		/**
		 * se obtienen la asignacion del examen.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT codigo FROM ord_pac_lab WHERE codigo="+CodAsignacion+"");
        }
        catch(Exception ex){
        	System.out.println("Error en Metodo ObtenerAsignacion "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerExamenesActivos(String CodAsignacion){
		/**
		 * se obtienen la asignacion del examen.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT lres.codigo,lres.estado FROM lab_resultado lres,ord_pac_lab opl WHERE lres.codord_fk=opl.codigo AND lres.codord_fk="+CodAsignacion+" AND lres.estado=0 ");
        }
        catch(Exception ex){
        	System.out.println("Error en Metodo ObtenerExamenesActivos "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerPacPendiente(){
		/**
		 * se obtienen los datos de los pacientes con laboratorios pendientes.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select opl.codigo,ap.nombre,ap.primer_apellido,ap.segundo_apellido,count(opl.codigo_estudio) as cantidad,opl.hora,opl.fecha,ap.pac_codigo_paciente from ord_pac_lab opl,adm_paciente ap where ap.pac_codigo_paciente=opl.codigo_pac_fk and opl.estado=0 group by opl.codigo_pac_fk");
        }
        catch(Exception ex){
        	System.out.println("Error en Metodo ObtenerPacPendiente "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerListaPacientesDia(String Fecha){
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select lpac.nombre,lpac.primer_apellido,lpac.segundo_apellido,lpac.telefono_celular,lpac.telefono_oficina,lpac.telefono_fijo,opl.fecha from ord_pac_lab opl,adm_paciente lpac where opl.codigo_pac_fk=lpac.pac_codigo_paciente and opl.fecha='"+Fecha+"' group by lpac.pac_codigo_paciente ");
        }
        catch(Exception ex){
        	System.out.println("Error en Metodo ObtenerListaPacientesDia "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet EstudiosPorPaciente(String CodPac){
		/**
		 * se obtienen los estudios de los pacientes con laboratorios pendientes.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	//antes esta bien sin fecha ni hora rs=st.executeQuery("select lsa.codigo,lsa.nombre,replace(lsa.nombre,lsa.nombre,'2') as tipo,opl.codigo,ap.genero,(year(curdate())-year(ap.fecha_nacimiento)) - (right(curdate(),5) < right(ap.fecha_nacimiento, 5)) as edad from ord_pac_lab opl,adm_paciente ap,lab_area la,lab_subarea lsa where ap.pac_codigo_paciente=opl.codigo_pac_fk and opl.estado=0 and la.codigo=lsa.codarea_fk and lsa.codigo=opl.codigo_estudio and ap.pac_codigo_paciente='"+CodPac+"' and opl.estado=0 union select lex.codigo,lex.nombre,lex.tipo,opl.codigo,ap.genero,(year(curdate())-year(ap.fecha_nacimiento)) - (right(curdate(),5) < right(ap.fecha_nacimiento, 5)) as edad from ord_pac_lab opl,adm_paciente ap,lab_examen lex where lex.codigo=opl.codigo_estudio and ap.pac_codigo_paciente='"+CodPac+"' and opl.codigo_pac_fk=ap.pac_codigo_paciente and opl.estado=0");
        	rs=st.executeQuery("select lsa.codigo,lsa.nombre,replace(lsa.nombre,lsa.nombre,'2') as tipo,opl.codigo,ap.genero,(year(curdate())-year(ap.fecha_nacimiento)) - (right(curdate(),5) < right(ap.fecha_nacimiento, 5)) as edad,opl.fecha,opl.hora from ord_pac_lab opl,adm_paciente ap,lab_area la,lab_subarea lsa where ap.pac_codigo_paciente=opl.codigo_pac_fk and opl.estado=0 and la.codigo=lsa.codarea_fk and lsa.codigo=opl.codigo_estudio and ap.pac_codigo_paciente='"+CodPac+"' and opl.estado=0 union select lex.codigo,lex.nombre,lex.tipo,opl.codigo,ap.genero,(year(curdate())-year(ap.fecha_nacimiento)) - (right(curdate(),5) < right(ap.fecha_nacimiento, 5)) as edad,opl.fecha,opl.hora from ord_pac_lab opl,adm_paciente ap,lab_examen lex where lex.codigo=opl.codigo_estudio and ap.pac_codigo_paciente='"+CodPac+"' and opl.codigo_pac_fk=ap.pac_codigo_paciente and opl.estado=0");
        }
        catch(Exception ex){
        	System.out.println("Error en Metodo EstudiosPorPaciente "+ex);
        }	
        return rs;
    }

}
