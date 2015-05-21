package agm_metodo;

import java.sql.PreparedStatement;
import java.sql.Statement;

import adm_logica.Conexion;
import agm_bean.CrearEspecialidad;
import agm_bean.CrearMedico;
import agm_bean.CrearParametros;

public class MetodoMedicos {

	
	
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
	
	public void ActualizarEstadoMedico(String CodMedico,String estado){
	    /**
	     * actualiza el estado del medico seleccionado.
	     */
		
		PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE agm_medico SET estado='"+estado+"' WHERE codigo='"+CodMedico+"'");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("ERROR EN MetodoMedicos>>ActualizarEstadoMedico "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public void ActualizarMedico(String TipoDocumento,String NumeroDocumento,String Nombres,String Apellidos,String TarjetaProfesional,String Profesion,String Especialidad,String Direccion,String Telefono,String Codigo,String TiempoMedico){
	    /**
	     * actualiza todos los datos del medico seleccionado.
	     */
		System.out.println("update agm_medico set numeroDocumento='"+NumeroDocumento+"',tipoDocumento='"+TipoDocumento+"',nombre='"+Nombres+"',apellidos='"+Apellidos+"',tarjetaProfesional='"+TarjetaProfesional+"',profesion='"+Profesion+"',codEspe_fk='"+Especialidad+"',direccion='"+Direccion+"',telefono='"+Telefono+"',tiempo_consulta='"+TiempoMedico+"' where codigo='"+Codigo+"'");
		PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("update agm_medico set numeroDocumento='"+NumeroDocumento+"',tipoDocumento='"+TipoDocumento+"',nombre='"+Nombres+"',apellidos='"+Apellidos+"',tarjetaProfesional='"+TarjetaProfesional+"',profesion='"+Profesion+"',codEspe_fk='"+Especialidad+"',direccion='"+Direccion+"',telefono='"+Telefono+"',tiempo_consulta='"+TiempoMedico+"' where codigo='"+Codigo+"'");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("ERROR EN MetodoMedicos>>ActualizarMedico "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public void CrearHorarioMedico(String HorasMedico,String FechaMedico,String estado,String CodigoMedico){
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
			    ps=con.conn.prepareStatement("insert into agm_horariomedico(horas,fechas,estado,codMedico_fk)values(?,?,?,?)");
			    ps.setString(1,cp.getHorasMedico());
			    ps.setString(2,cp.getFechaMedico());
			    ps.setString(3,cp.getestado());
			    ps.setString(4,cp.getCodigoMedico());
				    
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoMedicos>>CrearHorarioMedico "+ex);
			}

		}
	
	
	//
	public void CrearMedico(String TipoDocumento,String NumeroDocumento,String Nombres,String Apellidos,String TarjetaProfesional,String Profesion,String Especialidad,String Direccion,String Telefono,String TiempoMedico){		/**
		 * creamos el Medico tiene como parametros todos los datos que se necesitan para llenar.
		 */
		
		CrearMedico cm = new CrearMedico();
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
			    ps=con.conn.prepareStatement("insert into agm_medico(tipoDocumento,numeroDocumento,nombre,apellidos,tarjetaProfesional,profesion,codEspe_fk,direccion,telefono,tiempo_consulta)values(?,?,?,?,?,?,?,?,?,?)");
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
	        	System.out.println("ERROR EN MetodoMedicos>>CrearMedico "+ex);
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
        	rs=st.executeQuery("select numeroDocumento from agm_medico where numeroDocumento='"+NumeroDocumento+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoMedicos>>BuscarMedicoDocumento "+ex);
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
        	rs=st.executeQuery("select ame.numeroDocumento,ame.tipoDocumento,ame.nombre,ame.apellidos,ame.tarjetaProfesional ,ame.profesion,ame.direccion,ame.telefono,ame.codEspe_fk,aes.nombre_especialidad,ame.codigo,ame.tiempo_consulta from agm_medico ame,agm_especialidad aes where ame.codEspe_fk=aes.codigo and ame.numeroDocumento='"+NumeroDocumento+"' and ame.tipoDocumento='"+TipoDocumento+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoMedicos>>BuscarMedicoActualizar "+ex);
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
        	rs=st.executeQuery("select codigo,nombre,apellidos from agm_medico where estado='0' order by nombre");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoMedicos>>BuscarMedicoTodos "+ex);
        }	
        return rs;
    }
	
	
}
