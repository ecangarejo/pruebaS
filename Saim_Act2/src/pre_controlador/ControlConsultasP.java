package pre_controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pre_metodo.MetodoPresupuesto;

public class ControlConsultasP extends HttpServlet{
		protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		 
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
				String va = req.getParameter("valor");
				res.setContentType("text/html;charset=UTF-8");
				PrintWriter out;
				out=res.getWriter();
				MetodoPresupuesto mpre=new MetodoPresupuesto();
				ResultSet rs=null;
				ResultSet rs1=null;
				ResultSet rs2=null;
				ResultSet rs3=null;
				ResultSet rs4=null;
				ResultSet rs5=null;
				ResultSet rs6=null;
				ResultSet rs7=null;
				ResultSet rsa=null;
				ResultSet rsb=null;
				ResultSet rsc=null;
				ResultSet rsp=null;
				ResultSet rsl=null;
				
				String user=req.getParameter("user");
				
				
	}

}
