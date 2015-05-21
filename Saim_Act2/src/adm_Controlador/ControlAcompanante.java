/**
 * Controlador ControlAcompanante,el manejo de los datos del acompañante
 * que viene con el paciente.
 */
package adm_Controlador;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;

import adm_logica.MetodoAcompanante;

public class ControlAcompanante extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{

	} 
	public void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
		res.setContentType("text/html;charset=UTF-8");
		String ced = req.getParameter("ced");
		String nom = req.getParameter("nom");
		String ape = req.getParameter("ape");
		String dir = req.getParameter("dire");
		String tel = req.getParameter("tele");
		String par =req.getParameter("pare");
		
		String ced1 = req.getParameter("ced1");
		String nom1 = req.getParameter("nom1");
		String ape1 = req.getParameter("ape1");
		String dir1 = req.getParameter("dire1");
		String tel1 = req.getParameter("tele1");
		String par1 =req.getParameter("pare1");
		
		String Responsable="";
		String va = req.getParameter("valor");
		MetodoAcompanante ba=new MetodoAcompanante();
		if(ced==""){
			ced="0";
		}
		Responsable="Nombre: "+nom1+" "+ape1+"           Documento No:"+ced1+"\n Direccion: "+dir1+"        Telefono: "+tel1+"  Parentesco: "+par1;
		ba.insertar(ced, nom, ape, par, tel, dir,Responsable);
		PrintWriter out=res.getWriter();
		ResultSet rs=null;
		if(va.equals("1")){						
			try {
				rs=ba.obtenerCodigoDatosAcomp(nom, tel);
				if(rs.next()){
					out.print(rs.getString(1));
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	} 
	
}