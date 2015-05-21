/**
 * logica: MetodoUnidad se encuentran las consultas,inserciones y actualizaciones para  
 * para el ingreso y consulta de las diferentes unidades.
*/
package lab_logica;
import java.sql.PreparedStatement;
import java.sql.Statement;

import adm_logica.Conexion;

import lab_bean.Unidades;

public class MetodoUnidad {	
	public java.sql.ResultSet obtenerUnidad(){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select nombre from lab_unidad where codigo>0");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoUnidad>>obtenerUnidad "+ex);
        }
        return rs;
    }	
	public void insertarUnidad(String nomb){
		Unidades ud = new Unidades();
		ud.setNombre(nomb);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("insert into lab_unidad(nombre)values(?)");
			    ps.setString(1, ud.getNombre());
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
				System.out.println("Error en MetodoUnidad>>insertarUnidad "+ex);
			}
		}
	}
