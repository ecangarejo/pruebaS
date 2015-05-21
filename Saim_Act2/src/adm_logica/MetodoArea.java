/**
 * NOMBRE DE LA CLASE: MetodoArea
 * AUTOR: Oscar Rolong Schweiger
 * DESCRIPCION: En este metodo se encuentra lo necesario para insertar
 * 				un area.
 * 				
 */
package adm_logica;
import java.sql.*;
import java.io.*;
import adm_bean.Area;
import adm_logica.Conexion;
public class MetodoArea {
	
	public MetodoArea(){
		
	}
	
		public void insertar(String nombre){
			/**
			 * en este metodo se guarda en la base de datos la nueva
			 * area creada, se toma como dato de insercion el nombre del area.
			 * */
			
			Area ar=new Area();
			ar.setNombre(nombre);
			
			try{
				
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("insert into adm_area(nombre) values(?)");				
			    ps.setString(1,ar.getNombre());
				ps.executeUpdate();
				ps.close();
				con.cerrar();
				
				
			}
			catch(Exception ex){
				
			}
			
			
		}

		private void alert(String string) {
			// TODO Auto-generated method stub
		}
		
}
