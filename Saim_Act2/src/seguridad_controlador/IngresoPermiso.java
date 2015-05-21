/**
 * CONTROLADOR: IngresoPermiso se encuentra el proceso para
 * asignarle permisos a el usuario escogido.
*/
package seguridad_controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import seguridad_logica.MetodoOpcionesAutorizadas;


public class IngresoPermiso extends HttpServlet {
	private static final long serialVersionUID = 1L;   
    public IngresoPermiso() {}	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		MetodoOpcionesAutorizadas moa = new MetodoOpcionesAutorizadas ();
		res.setContentType("text/html;charset=UTF-8");
		String codigo,codusu;
		codigo=req.getParameter("codigo");
		codusu=req.getParameter("codusu");
        String codigousu="";
		ResultSet rs=null;		
		 try{		 
			 rs=moa.Obtenercodigousuario(codusu);
			  if(rs.next()){
				 	codigousu=rs.getString(1);		 
			  }
			 rs.getStatement().getConnection().close();
		 }
		 catch(SQLException e1){
			 e1.getMessage();
		 }
		moa.insertarAutorizadas(codigousu, codigo);
		PrintWriter out=res.getWriter();
		out.println("");
		
	}

	

}
