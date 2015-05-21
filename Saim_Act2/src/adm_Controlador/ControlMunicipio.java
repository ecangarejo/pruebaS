package adm_Controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import adm_logica.MetodoNacionalidad;



public class ControlMunicipio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest re, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");
		String nombre=re.getParameter("nom");
		String nomdepa=re.getParameter("nomdepa");		
		PrintWriter out=res.getWriter();
		ResultSet rs=null;
		MetodoNacionalidad na=new MetodoNacionalidad();
		rs = na.SQLMunicipios(nombre,nomdepa); 
		try {
			String  codmun;			
			while(rs.next()){					
				codmun=rs.getString(1);					
				out.println(codmun);
			}				
			rs.getStatement().getConnection().close();	
		} catch (SQLException e) {				
			e.printStackTrace();
		}
	}
}
