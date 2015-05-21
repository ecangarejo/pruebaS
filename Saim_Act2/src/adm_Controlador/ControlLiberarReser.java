/**
 * NOMBRE DE LA CLASE: ControlLiberarReser
 * AUTOR: Oscar Rolong Schweiger
 * DESCRIPCION: En este Controlador se encuentra lo necesario
 * 				para liberar una cama. 				
 */
package adm_Controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import adm_logica.MetodoLiberarReser;


public class ControlLiberarReser extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
		res.setContentType("text/html;charset=UTF-8");
		MetodoLiberarReser ml = new MetodoLiberarReser ();
		String va = req.getParameter("va");	
		String x=req.getParameter("x");		
		String y=req.getParameter("y");	
		String cama=req.getParameter("cama");
		PrintWriter out=res.getWriter();
		ResultSet rs=null;	
		if (va.equals("1")){
			/**
			 * se obtiene el area mediante una consulta 
			 * MetodoLiberarReser/obtenerArea
			 */
			rs= ml.obtenerArea();
			String v[] = new String[1000];
			try{
				int c=0;
				while(rs.next()){
					
					v[c]=rs.getString(1);
					out.println(v[c]+"_");
					
					c++;
				}
				rs.getStatement().getConnection().close();
			
			}
			 catch(SQLException e1){
				 e1.getMessage();
			 }
			 
		}	
		
		if(va.equals("2")){
			
			 /**
			 * se obtiene la subarea mediante una consulta 
			 * MetodoLiberarReser/obtenerSubArea
			 */
			
			 rs= ml.obtenerSubArea(x);		
			 String s[] = new String[1000];
				try{
					int k=0;
					while(rs.next()){
						s[k]=rs.getString(1);
						out.println(s[k]+"_");
						k++;								
					}
					rs.getStatement().getConnection().close();
				}		
				 catch(SQLException e1){
					 e1.getMessage();
					 }
		}
		if(va.equals("3")){
			/**
			 * se obtiene la cama de la subarea escogida mediante una consulta 
			 * MetodoLiberarReser/obtenerCama que lleva como dato la subarea
			 */
			 rs= ml.obtenerCama(y);		
			 String s[] = new String[1000];
				try{
					int k=0;
					while(rs.next()){
						s[k]=rs.getString(1);
						out.println(s[k]+"_");
						k++;								
					}
					rs.getStatement().getConnection().close();
				}		
				 catch(SQLException e1){
					 e1.getMessage();
					 }	
		}
		
		if (va.equals("4")){
			
			/**
			 * se obtiene los datos de la cama reservada mediante una consulta
			 * MetodoLiberarReser/obtenerCodigoCam que lleva como dato la cama
			 */
			rs=ml.obtenerCodigoCam(cama);
			
			 String codPread=null;	
			 String codCama=null;
			 String nombre=null;
			 String papellido=null;
			 String fecha=null;
			 String cedula=null;
			 String eps=null;
			 String tipodoc=null;
			 String sapellido=null;
			 String numauto=null;
			 String autopor=null;
			 
				try{				
					while(rs.next()){
						codPread=rs.getString(1);
						codCama=rs.getString(2);
						nombre=rs.getString(3);
						papellido=rs.getString(4);
						fecha=rs.getString(5);
						cedula=rs.getString(6);
						eps=rs.getString(7);
						tipodoc=rs.getString(8);
						sapellido=rs.getString(9);
						numauto=rs.getString(10);
						autopor=rs.getString(11);
						out.println(codPread+"_"+codCama+"_"+nombre+"_"+papellido+"_"+fecha+"_"+cedula+"_"+eps+"_"+tipodoc+"_"+sapellido+"_"+numauto+"_"+autopor);
						
					}
					rs.getStatement().getConnection().close();
				}
				 catch(SQLException e1){
					 e1.getMessage();
					 }
				 }
		
		
	}//fin del doPost
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
		/**
		 * se procede a actualizar la cama que esta reservada
		 * mediante MetodoLiberarReser/ActualizarCamaReser que actualiza el estado de la cama
		 * mediante MetodoLiberarReser/ActualizarPreadmision que actualiza el estado de la preadmision
		 */
		
		MetodoLiberarReser ml = new MetodoLiberarReser ();
		String codigoCama=req.getParameter("codigoCama");
		
		String codPre=req.getParameter("codPre");
		
		ml.ActualizarCamaReser(codigoCama);
		ml.ActualizarPreadmision(codPre);

	}//fin del doGet

}
