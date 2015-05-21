/**
 * Control:ControlPreingreso
 * Realiza una serie de consultas de area,codigo, entidaes
 * Desarrollado:yosimar valega
 */

package adm_Controlador;
import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import adm_logica.Conexion;
import adm_logica.MetodoPreingreso;
import agm_metodo.MetodoAsignarCita;

import java.sql.*;
import java.util.*;

public class ControlPreingreso extends HttpServlet{
	
	/**
	 * Muestra las area, subarea,cama de la clinica
	 * Son llamadas desde 1.js
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
		String ced, nom, pape,sape,eps,fecha,hora,tipodoc,estado,va, xx, x, y;
		va = req.getParameter("va");
		res.setContentType("text/html;charset=UTF-8");
		
		MetodoPreingreso ba = new MetodoPreingreso();	
		PrintWriter out=res.getWriter();
		ResultSet rs=null;
		/*************************************************************************************/
		if(va.equals("CCA")){
			String CodSubarea=req.getParameter("CodSubarea");
			out.print("<select name='cam'  id='cam' > <option selected='selected' value='Seleccione '>Seleccione</option>");
			rs=ba.CargarCamasSubarea(CodSubarea);
			try {
				while(rs.next()){
					out.print("<option value="+rs.getString("codigocama")+" >"+rs.getString("codigocama")+"</option>");
				}
				rs.getStatement().getConnection().close();
				out.print("</select>");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			    
			     
		}
		/*************************************************************************************
		
		estado="0";
	    eps = new String();	
		
		x = req.getParameter("x");
		y = req.getParameter("y");
		xx = req.getParameter("xx");
		//Elimina los caracteres de ENTER y retorno o salto de linea 
		//si se necesitan se complementa \r \t
		char ee=((char)13);
		
		try{
			while(eps.charAt(0)==ee){				
				 eps=eps.substring(1, eps.length());
			}
		while(eps.charAt(0)=='\n'){			
			 eps=eps.substring(1, eps.length());
		}
		
		}catch(Exception e){
			
		}	
			
		ResultSet rs=null;
		
		if(va.equals("1")){
		rs=ba.SQLArea();
		}	
		if(va.equals("2")){
			MetodoPreingreso m = new MetodoPreingreso();
			String esnumero = "";
			rs=m.CargarCodigo(x);
			try {				
					while(rs.next()){
						esnumero=rs.getString(1);						
					}
					rs.getStatement().getConnection().close();				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		rs=ba.SQLEnt(esnumero);	
		}
		if(va.equals("3")){
			MetodoPreingreso m = new MetodoPreingreso();
			String esnumero = "";
			rs=m.CargarCodigo(xx);
			try {
					while(rs.next()){
						esnumero=rs.getString(1);
					}
					rs.getStatement().getConnection().close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		rs=ba.SQLEntx(y,esnumero);
		}
		
		String v[] = new String[1000];
		try {
			int c=0;
			while(rs.next()){				
				v[c]=rs.getString(1);
				out.println(v[c]+"_");
				c++;
			}
			rs.getStatement().getConnection().close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	*/	
	}	
	
	
			
}
