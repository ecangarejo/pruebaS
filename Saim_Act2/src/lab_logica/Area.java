/**
 * logica: Area se encuentran las consultas,inserciones y actualizaciones para  
 * crear el area de laboratorio.
*/
package lab_logica;

import java.sql.PreparedStatement;

import adm_logica.Conexion;
import lab_bean.Lab_area;



public class Area {
	
	public void insertar(String area){
		Lab_area a=new Lab_area();
	
		a.setNombre(area);
		
		try{
			String areas;
			areas=a.getNombre();
			
			PreparedStatement ps = null;
		    Conexion con=new Conexion();
		 
		    ps=con.conn.prepareStatement("insert into lab_area(nombre) values(?)");				
			ps.setString(1,a.getNombre());
			
			ps.executeUpdate();
			
			ps.close();
			con.cerrar();
	
		}catch(Exception ex){
			ex.getMessage();
		}
	}

}
