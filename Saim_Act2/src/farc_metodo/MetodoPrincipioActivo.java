package farc_metodo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import adm_logica.Conexion;
import farc_bean.CrearPrincipioActivo;

public class MetodoPrincipioActivo {

	public void CrearPrincipioActivo(String nombre, String cod_grupoFarmacologico_fk){
	/**
		 * crear principio activo.
	 */
		CrearPrincipioActivo ca = new CrearPrincipioActivo();
		
		//ca.setcodsustGenerica(codsustGenerica);
		ca.setnombre(nombre);
		ca.setcod_grupoFarmacologico_fk(cod_grupoFarmacologico_fk);
	
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("insert into farc_sustanciagenerica(nombre,cod_grupoFarmacologico_fk)values(?,?)");
			    //ps.setString(1,ca.getcodsustGenerica());
			    ps.setString(1,ca.getnombre());
			    ps.setString(2,ca.getcod_grupoFarmacologico_fk());
			 
			  	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoPrincipioActivo>>CrearPrincipioActivo "+ex);
			}

		}
	
	public java.sql.ResultSet verificar(String nombre){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM farc_sustanciagenerica WHERE nombre='"+nombre+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoPrincipioActivo>>verificar "+ex);
        }	
        return rs;
    }
}