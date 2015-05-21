/**
 * NOMBRE DE LA CLASE: ControlCrearCama
 * AUTOR: Oscar Rolong Schweiger
 * DESCRIPCION: En este Controlador se encuentra lo necesario
 * 				para crear una cama. 				
 */
package adm_Controlador;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import adm_bean.Area;
import adm_bean.CrearCama;
import adm_bean.SubArea;
import adm_logica.MetodoCrearCama;
import adm_logica.MetodoPreingreso;
import adm_logica.MetodoSubarea;

import java.sql.*;
import java.util.*;


public class ControlCrearCama extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
		res.setContentType("text/html;charset=UTF-8");
		HttpSession session = req.getSession(true);
		MetodoCrearCama ba= new MetodoCrearCama();		
		String va = req.getParameter("va");		
		ResultSet rs=null;	
		PrintWriter out=res.getWriter();
	if(va.equals("1")){
		/**
		 * se obtienen los datos del area mediante una consulta 
		 * MetodoCrearCama/obtenerArea
		 */
		rs= ba.obtenerArea();
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
		 
		 
		 if (va.equals("5")){	
			 /**
				 * se obtienen datos del tipo de cama mediante una consulta 
				 * MetodoCrearCama/obtenerTipoCama
				 */
				rs=ba.obtenerTipoCama();
				String v[] = new String[1000];
				try{
					int c=0;
					while(rs.next()){
						v[c]=rs.getString(1);						
						out.println(v[c]+"_");						
					
					}
					rs.getStatement().getConnection().close();
				}
				 catch(SQLException e1){
					 e1.getMessage();
				 }
			}
			
	if (va.equals("6")){
		/**
		 * se obtienen datos del tipo de habitacion mediante una consulta 
		 * MetodoCrearCama/obtenerTipoHab
		 */
				rs=ba.obtenerTipoHab();
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
		 
	if (va.equals("2")){
		/**
		 * se obtienen la posicion de la cama mediante una consulta 
		 * MetodoCrearCama/obtenerPosicion
		 */
		rs=ba.obtenerPosicion();
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
	
	
	}
	public void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
		/**
		 * se recibe la variable del area y se obtienen las subareas mediante
		 * una consulta MetodoCrearCama/obtenerSubArea.
		 */
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out=res.getWriter();
		HttpSession session = req.getSession(true);
		MetodoCrearCama ca = new MetodoCrearCama();
		String area=null;
		area = req.getParameter("x");
		String validar="2";				
		if(!validar.equals(null)){ 		
		 ResultSet rs=null;
		 rs= ca.obtenerSubArea(area);		
		 String s[] = new String[1000];
			try{
				int k=0;
				while(rs.next()){					
					s[k]=rs.getString(2);
					out.println(s[k]+"_");
					k++;								
				}
				rs.getStatement().getConnection().close();
			}			
			 catch(SQLException e1){
				 e1.getMessage();
				 }
		 }
	}
}
