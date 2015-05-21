package pyp_metodo;

import java.sql.PreparedStatement;
import java.sql.Statement;

import adm_logica.Conexion;
import pyp_bean.PYP_CrearEspecialidad;
import pyp_bean.PYP_CrearMedico;
import pyp_bean.PYP_CrearParametros;

public class PYP_MetodoMedicos {

	
	
	public void CrearHorarioMedico1(String HorasMedico,String FechaMedico,String estado,String CodigoMedico,String NombreCompleto){
		/**
		 * creamos el Medico tiene como parametros todos los datos que se necesitan para llenar.
		 */
		
		PYP_CrearParametros cp = new PYP_CrearParametros();
		cp.setHorasMedico(HorasMedico);
		cp.setFechaMedico(FechaMedico);
		cp.setestado(estado);
		cp.setCodigoMedico(CodigoMedico);

	
		
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("insert into pyp_horariomedico(horas,fechas,estado,codMedico_fk,NombrePaciente)values(?,?,?,?,?)");
			    ps.setString(1,cp.getHorasMedico());
			    ps.setString(2,cp.getFechaMedico());
			    ps.setString(3,cp.getestado());
			    ps.setString(4,cp.getCodigoMedico());
			    ps.setString(5,NombreCompleto);
			    
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN PYP_MetodoMedicos>>CrearHorarioMedico1 "+ex);
			}

		}
	
	public void ActualizarEstadoMedico(String CodMedico,String estado){
	    /**
	     * actualiza el estado del medico seleccionado.
	     */
		
		PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE pyp_medico SET estado='"+estado+"' WHERE codigo='"+CodMedico+"'");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("ERROR EN PYP_MetodoMedicos>>ActualizarEstadoMedico "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public void ActualizarMedico(String TipoDocumento,String NumeroDocumento,String Nombres,String Apellidos,String TarjetaProfesional,String Profesion,String Especialidad,String Direccion,String Telefono,String Codigo,String TiempoMedico){
	    /**
	     * actualiza todos los datos del medico seleccionado.
	     */
		System.out.println("update pyp_medico set numeroDocumento='"+NumeroDocumento+"',tipoDocumento='"+TipoDocumento+"',nombre='"+Nombres+"',apellidos='"+Apellidos+"',tarjetaProfesional='"+TarjetaProfesional+"',profesion='"+Profesion+"',codEspe_fk='"+Especialidad+"',direccion='"+Direccion+"',telefono='"+Telefono+"',tiempo_consulta='"+TiempoMedico+"' where codigo='"+Codigo+"'");
		PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("update pyp_medico set numeroDocumento='"+NumeroDocumento+"',tipoDocumento='"+TipoDocumento+"',nombre='"+Nombres+"',apellidos='"+Apellidos+"',tarjetaProfesional='"+TarjetaProfesional+"',profesion='"+Profesion+"',codEspe_fk='"+Especialidad+"',direccion='"+Direccion+"',telefono='"+Telefono+"',tiempo_consulta='"+TiempoMedico+"' where codigo='"+Codigo+"'");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("ERROR EN PYP_MetodoMedicos>>ActualizarMedico "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public void CrearHorarioMedico(String HorasMedico,String FechaMedico,String estado,String CodigoMedico){
		/**
		 * creamos el Medico tiene como parametros todos los datos que se necesitan para llenar.
		 */
		
		PYP_CrearParametros cp = new PYP_CrearParametros();
		cp.setHorasMedico(HorasMedico);
		cp.setFechaMedico(FechaMedico);
		cp.setestado(estado);
		cp.setCodigoMedico(CodigoMedico);

	
		
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("insert into pyp_horariomedico(horas,fechas,estado,codMedico_fk)values(?,?,?,?)");
			    ps.setString(1,cp.getHorasMedico());
			    ps.setString(2,cp.getFechaMedico());
			    ps.setString(3,cp.getestado());
			    ps.setString(4,cp.getCodigoMedico());
				    
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN PYP_MetodoMedicos>>CrearHorarioMedico "+ex);
			}

		}
	
	
	//
	public void CrearMedico(String TipoDocumento,String NumeroDocumento,String Nombres,String Apellidos,String TarjetaProfesional,String Profesion,String Especialidad,String Direccion,String Telefono,String TiempoMedico){		/**
		 * creamos el Medico tiene como parametros todos los datos que se necesitan para llenar.
		 */
		
		PYP_CrearMedico cm = new PYP_CrearMedico();
		cm.settipoDocumento(TipoDocumento);
		cm.setnumeroDocumento(NumeroDocumento);
		cm.setnombre(Nombres);
		cm.setapellidos(Apellidos);
		cm.settarjetaProfesional(TarjetaProfesional);
		cm.setprofesion(Profesion);
		cm.setcodEspe_fk(Especialidad);
		cm.setdireccion(Direccion);
		cm.settelefono(Telefono);
		
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("insert into pyp_medico(tipoDocumento,numeroDocumento,nombre,apellidos,tarjetaProfesional,profesion,codEspe_fk,direccion,telefono,tiempo_consulta)values(?,?,?,?,?,?,?,?,?,?)");
			    ps.setString(1,cm.gettipoDocumento() );
			    ps.setString(2,cm.getnumeroDocumento() );
			    ps.setString(3,cm.getnombre() );
			    ps.setString(4,cm.getapellidos() );
			    ps.setString(5,cm.gettarjetaProfesional() );
			    ps.setString(6,cm.getprofesion() );
			    ps.setString(7,cm.getcodEspe_fk() );
			    ps.setString(8,cm.getdireccion() );
			    ps.setString(9,cm.gettelefono() );	
			    ps.setString(10, TiempoMedico);
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN PYP_MetodoMedicos>>CrearMedico "+ex);
			}

		}
	
	
	public java.sql.ResultSet BuscarMedicoDocumento(String NumeroDocumento){	
		/**
		 * consulta tiene como parametro el NumeroDocumento del medico
		 * para buscar si ya existe un con ese NumeroDocumento.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select numeroDocumento from pyp_medico where numeroDocumento='"+NumeroDocumento+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoMedicos>>BuscarMedicoDocumento "+ex);
        }	
        return rs;
    }
	
	
	
	public java.sql.ResultSet BuscarMedicoActualizar(String NumeroDocumento,String TipoDocumento){	
		/**
		 * consulta tiene como parametro el NumeroDocumento del medico
		 * para buscar si ya existe un con ese NumeroDocumento.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select pme.numeroDocumento,pme.tipoDocumento,pme.nombre,pme.apellidos,pme.tarjetaProfesional ,pme.profesion,pme.direccion,pme.telefono,pme.codEspe_fk,pes.nombre_especialidad,pme.codigo,pme.tiempo_consulta from pyp_medico pme,pyp_especialidad pes where pme.codEspe_fk=pes.codigo and pme.numeroDocumento='"+NumeroDocumento+"' and pme.tipoDocumento='"+TipoDocumento+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoMedicos>>BuscarMedicoActualizar "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarMedicoTodos(){	
		/**
		 * busca todos los medico que esten creados en el sistema de agenda medica.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo,nombre,apellidos from pyp_medico where estado='0'");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoMedicos>>BuscarMedicoTodos "+ex);
        }	
        return rs;
    }
	
	
}
