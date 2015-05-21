/**
 * en el controlador ControlAsignarCama se encuentra los procedimientos
 * necesiarios para la asignacion de la cama.
 */
package adm_Controlador;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import adm_bean.Area;
import adm_bean.Cama;
import adm_bean.SubArea;
import adm_logica.MetodoCama;

import java.sql.*;
import java.util.*;

public class ControlAsignarCama extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
		HttpSession session = req.getSession(true);
		res.setContentType("text/html;charset=UTF-8");
		MetodoCama ba= new MetodoCama();
		ResultSet rs=null;
		rs= ba.obtenerArea();
		LinkedList lista= new LinkedList();
		try{	
			while(rs.next()){
				Area a = new Area();
				a.setCodigo(rs.getInt("codigo"));
				a.setNombre(rs.getString("nombre"));
				lista.add(a);
		
			}
			rs.getStatement().getConnection().close();
		}//end try
		 catch(SQLException e){
			 e.getMessage();
		 }
		session.setAttribute("list", lista);
		res.sendRedirect("ejemplo1.jsp");
		
						
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
		HttpSession session = req.getSession(true);
		res.setContentType("text/html;charset=UTF-8");
		String cb = "1";//req.getParameter("area");
		if (!cb.equals(null)){
			MetodoCama ba= new MetodoCama();
			
			
			ResultSet rs= ba.obtenerSubArea(cb); 
			
			LinkedList list= new LinkedList();
			try {
				while(rs.next()){
				SubArea a = new SubArea();
				
				//	a.setCodigo(rs.getInt("codigo"));
					a.setNombre(rs.getString("nombre"));
					//a.setCodigoArea(rs.getInt("codigoarea"));
					list.add(a);		
				}
			session.setAttribute("listasub", list);
			rs.getStatement().getConnection().close();
			res.sendRedirect("ejemplo1.jsp");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			cb = null;
		}
	}
}