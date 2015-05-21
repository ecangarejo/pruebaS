/**
 * logica: lab_IngresoPac se encuentran las consultas,inserciones y actualizaciones para  
 * crear el paciente(modulo individual).
*/
package lab_logica;

import java.sql.PreparedStatement;

import adm_logica.Conexion;

import lab_bean.Paciente;

public class lab_IngresoPac {
	
	 public void insertar(String nombre, String genero,String cedula,String eps,String edad,String telefono,String direccion, String email, String apellidos, String tipo, String fechana){
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
			
			try{
		
				
				String fechapre,ini,med,fin=null;
				ini=pac.getFechana().substring(0,2);
				med=pac.getFechana().substring(3,5);
				fin=pac.getFechana().substring(6,10);
				fechapre=fin+"/"+med+"/"+ini;				
				
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("insert into lab_paciente(nombre,genero,cedula,eps,edad,telefono,direccion,email,apellidos,tipo_documento,fecha_nacimiento) values(?,?,?,?,?,?,?,?,?,?,?)");				
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
				ps.executeUpdate();
				ps.close();
				con.cerrar();			
			
			}catch(Exception ex){
				ex.getMessage();
				System.out.print(ex);
			}
			
			
		}

}
