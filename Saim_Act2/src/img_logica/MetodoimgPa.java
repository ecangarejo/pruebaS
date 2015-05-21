/**
 * logica: MetodoimgPa se encuentra las consultas,inserciones y actualizaciones
 * para la creacion de los pacientes(no valido para la union de los proyectos)
*/

package img_logica;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import adm_logica.Conexion;

import img_bean.Paciente;

public class MetodoimgPa {
	
	 public java.sql.ResultSet NombreUsuario(String CodUsu){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select sdt.nombre,sdt.apellido from seg_usuario sus,seg_datos_personales sdt where sdt.dat_codigo=sus.dat_codigo_fk and sus.usu_codigo='"+CodUsu+"'");
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error en MetodoimgPa>>Nombreusuario "+ex);
	        }
	        return rs;
	    }
	
	 public java.sql.ResultSet SQLimgPac(String numero,String Tipo){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select nombre,primer_apellido,pac_codigo_paciente,genero,(year(curdate())-year(fecha_nacimiento)) - (right(curdate(),5) < right(fecha_nacimiento, 5)) as edad,segundo_apellido from adm_paciente where tipo_documento='"+Tipo+"' and numero_documento='"+ numero+"'");
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error en MetodoimgPa>>SQLimgPac "+ex);
	        }
	        return rs;
	    }
	 public void insertar(String nombre, String genero,String cedula,String eps,String edad,String telefono,String direccion, String email, String apellidos, String tipo, String fechana,String NombreCompleto,String fechaIngreso,String horaIngreso){
		/**no valido para la union de los proyectos*/	
		 Paciente pac = new Paciente();
			pac.setNombre(nombre);
			pac.setGenero(genero);
			pac.setCedula(cedula);
			pac.setEps(eps);
			pac.setEada(edad);
			pac.setTelefono(telefono);
			pac.setDireccion(direccion);
			pac.setEmail(email);
			pac.setApellidos(apellidos);
			pac.setDocumento(tipo);
		    pac.setFechana(fechana);
		    pac.setNombreCompleto(NombreCompleto);
		    pac.setFecha(fechaIngreso);
		    pac.setHora(horaIngreso);

			try{
				String fechapre=null;
				if(fechana==""){
					fechapre=null;
				}else{				
				String ini,med,fin=null;
				ini=pac.getFechana().substring(0,2);
				med=pac.getFechana().substring(3,5);
				fin=pac.getFechana().substring(6,10);
				fechapre=fin+"/"+med+"/"+ini;
				}
				PreparedStatement ps = null;

			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("insert into img_paciente(nombre,genero,cedula,eps,edad,telefono,direccion,email,apellidos,tipo_documento,fecha_nacimiento,usuario,fecha,hora) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");				
				ps.setString(1, pac.getNombre());
				ps.setString(2,pac.getGenero());
				ps.setString(3, pac.getCedula());
				ps.setString(4,pac.getEps());
				ps.setString(5, pac.getEdad());
				ps.setString(6,pac.getTelefono());
				ps.setString(7,pac.getDireccion());
				ps.setString(8,pac.getEmail());
				ps.setString(9,pac.getApellidos());
				ps.setString(10,pac.getDocumento());
				ps.setString(11,fechapre);
				ps.setString(12, pac.getNombreCompleto());
				ps.setString(13, pac.getFecha());
				ps.setString(14, pac.getHora());
				ps.executeUpdate();
				ps.close();
				con.cerrar();
			}catch(Exception ex){
				ex.getMessage();
				System.out.println("Error en MetodoimgPa>>insertar "+ex);
			}
		}
	 
	  public ResultSet listarParaAutocompletarControl(String cod,String tipo) throws Exception {
		  java.sql.ResultSet r=null;
	        Statement st = null;
	       Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	r=st.executeQuery("SELECT numero_documento,nombre,primer_apellido,segundo_apellido FROM adm_paciente WHERE numero_documento LIKE '"+cod+"%' and tipo_documento='"+tipo+"'");
	        return r;
	    }
	  
	  public ResultSet listarParaAutocompletarPaciente(String cod) throws Exception {
		  java.sql.ResultSet r=null;
	        Statement st = null;
	       Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	r=st.executeQuery("SELECT numero_documento,nombre,primer_apellido,tipo_documento,segundo_apellido FROM adm_paciente WHERE nombre LIKE '"+cod+"%' or primer_apellido LIKE '"+cod+"%'or segundo_apellido LIKE '"+cod+"%'");
	        return r;
	    }

}