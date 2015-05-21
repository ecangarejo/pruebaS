/**
 * el metodo Conexion, se encarga de hacer la conexion de la base de datos 
 * con el programa.
 */
package adm_logica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;


public class Conexion {
	public Connection conn = null;  // La conexi�n con la BD
	private static final Conexion conexion =null;
	
	 static int cent = 0; 
		/**
		 * 
		 * Metodo: 		Conexion 
		 * Proposito:	Crear la conexi�n con la BD
		 * 
		 */
		public Conexion() throws Exception { // se encarga de realizar la conexi�n
			try {
		    // Cargamos el Driver
				Class.forName("com.mysql.jdbc.Driver");
		        // Armamos la conexi�n
				//conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/saim","root","jhosha2413" );
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/saim_kar","root","123" );
				//conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/saim","root","!d1c5ccya!" );
				//conn = DriverManager.getConnection("jdbc:mysql://192.9.9.4:3307/saim","root","123" );
		      
		    }
		    catch (Exception ex) {
		       // Regeneramos la excepcion
		        throw ex;
		    }
		 }
		
		/**
		 * Metodo: 		Conexion 
		 * Proposito:	Este m�todo cierra la conexion con la BD
		
		 */

	   public void cerrar() {
	       try {
	           conn.close();
	       }
	       catch (Exception ex) { }
	   }
	   
	   public static ResultSet Log(String r)throws SQLException{
		   Statement st=null;
		 //  st= conn.createStatement();
		   return st.executeQuery(r);
	   }
}
