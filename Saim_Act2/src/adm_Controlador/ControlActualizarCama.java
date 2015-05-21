/**
 * NOMBRE DE LA CLASE: ControlActualizarCama
 * AUTOR: Oscar Rolong Schweiger
 * DESCRIPCION: En este Controlador se encuentra lo necesario para hacer la actualizacion
 * 				de algun dato de una cama.
 * 				
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

import adm_logica.MetodoActualizarCama;
import adm_logica.MetodoCrearCama;

public class ControlActualizarCama extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
		/**
		 * se obtienen los datos por parametros desde el adm_ActualizarCama.jsp
		 */
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out=res.getWriter();
		String va = req.getParameter("va");
		String cama=req.getParameter("cama");
		String x=req.getParameter("x");	
		String y=req.getParameter("y");	
		String tipohab="";
		String tipo =req.getParameter("tipo");	
		String tipcama=req.getParameter("tipcam");	
		String posic=req.getParameter("posic");
		MetodoActualizarCama ac=new MetodoActualizarCama();
		ResultSet rs=null;
	
		if (va.equals("1")){
			/**
			 * se obtienen las areas como esta repartida la clinica
			 * mediante una consulta MetodoActualizarCama/obtenerArea
			 * para despues enviarlos al adm_ActualizarCama.jsp
			 */
			rs=ac.obtenerArea();
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
			 * se obtienen las subareas de el area seleccionada
			 * mediante una consulta MetodoActualizarCama/obtenerSubArea
			 * que lleva como parametro el nombre del area.
			 */
			 rs= ac.obtenerSubArea(x);		
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
			 * se obtienen las camas de el subarea seleccionada
			 * mediante una consulta MetodoActualizarCama/obtenerCama
			 * que lleva como parametro el nombre de la subarea.
			 */
			 rs= ac.obtenerCama(y);		
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
			 * se obtienen los datos de la cama seleccionada
			 * mediante una consulta MetodoActualizarCama/obtenerDatosCama
			 * que lleva como parametro el nombre de la cama.
			 */
			
			 rs= ac.obtenerDatosCama(cama);
			 String posx=null;
			 String posy=null;
			 String observaciones=null;
			 String piso=null;
			 String codigo=null;
			 String posicion=null;
			 String tipocama=null;
			 String nomcama=null;
				try{				
					while(rs.next()){
						posx=rs.getString(1);
						posy=rs.getString(2);
						observaciones=rs.getString(3);
						piso=rs.getString(4);
						tipohab=rs.getString(5);
						tipo=tipohab;
						posicion=rs.getString(6);
						 if (posicion!= null){					 
						  
						 if(posicion.equals("H")){
								posicion="HORIZONTAL";
							}
						  if(posicion.equals("V")){
								posicion="VERTICAL";
							}
						 }					
						 
						tipocama=rs.getString(7);
						codigo=rs.getString(8);
						nomcama=rs.getString(9);
						out.println(posx+"_"+posy+"_"+observaciones+"_"+piso+"_"+tipohab+"_"+posicion+"_"+tipocama+"_"+codigo+"_"+nomcama);
					}
					rs.getStatement().getConnection().close();
					
				}
				 catch(SQLException e1){
					 e1.getMessage();
					 }
				 }
		
		if (va.equals("5")){
			/**
			 * se obtienen los datos de tipo de cama
			 * mediante una consulta MetodoActualizarCama/obtenerTipoCama
			 */
			rs=ac.obtenerTipoCama();
			String v[] = new String[1000];
			try{
				int c=0;
				while(rs.next()){
					v[c]=rs.getString(1);
					if(v[c].compareTo(tipcama)!=0){
						out.println(v[c]+"_");						
					}
					c++;
				}	
				rs.getStatement().getConnection().close();
			}
			 catch(SQLException e1){
				 e1.getMessage();
			 }
			 
		}
		
if (va.equals("6")){
	/**
	 * se obtienen los datos de tipo de habitacion
	 * mediante una consulta MetodoActualizarCama/obtenerTipoHab
	 */
			rs=ac.obtenerTipoHab();
			String v[] = new String[1000];
			try{
				int c=0;
				while(rs.next()){
					v[c]=rs.getString(1);
					if(v[c].compareTo(tipo)!=0){
						out.println(v[c]+"_");						
					}
					
					c++;
				}
				rs.getStatement().getConnection().close();
			}
			 catch(SQLException e1){
				 e1.getMessage();
			 }			 
		}
				
if (va.equals("0")){
	/**
	 * se obtienen los datos de la posicion de la cama dentro de la habitacion
	 * mediante una consulta MetodoActualizarCama/obtenerPosicion
	 */
	rs=ac.obtenerPosicion();
	String v[] = new String[1000];
	try{
		int c=0;
		while(rs.next()){
			v[c]=rs.getString(1);	
			if(v[c].compareTo(posic)!=0){
				out.println(v[c]+"_");	
			}
			else{
				out.println(v[c]+"_");
			}
			c++;
		}
		rs.getStatement().getConnection().close();
	}
	 catch(SQLException e1){
		 e1.getMessage();
	 }			 
}
	}	
	
	
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
		/**
		 * se reciben los datos de que se van actualizar desde el adm_ActualizarCama.jsp
		 * y se envian los datos a  MetodoActualizarCama/ActualizarCama para su
		 * posterior actualizacion.
		 */
		res.setContentType("text/html;charset=UTF-8");
		MetodoActualizarCama ac = new MetodoActualizarCama ();
		String posy=null;
		String posx=null;
		String posicion=null;
		String codigo=null;
		String tipocama=null;
		String tipohab=null;
		String observ=null;
		String piso=null;
		String nomcama=null;
		
		posy=req.getParameter("posy");		
		posx=req.getParameter("posx");
		posicion=req.getParameter("posicion");
		codigo=req.getParameter("codigo");
		tipocama=req.getParameter("tipocama");
		tipohab=req.getParameter("tipohab");
		observ=req.getParameter("observ");
		piso=req.getParameter("piso");
		nomcama=req.getParameter("nomcama");
		ac.ActualizarCama(codigo, tipocama, tipohab, posicion, observ, piso, posy, posx,nomcama);
		res.sendRedirect("adm_ActualizarCama.jsp");	
	}
	

}
