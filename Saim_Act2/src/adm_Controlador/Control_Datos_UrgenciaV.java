/**
 * NOMBRE DE LA CLASE: Control_Datos_UrgenciaV
 * AUTOR: Oscar Rolong Schweiger
 * DESCRIPCION: En este Controlador se encuentra lo necesario para mostrar un reporte 
 * 				de las urgencias vitales que estan.
 * 				
 */
package adm_Controlador;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.LinkedList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import adm_bean.Urgencia_vital;
import adm_logica.MetodoUrgenciaVital;


public class Control_Datos_UrgenciaV extends HttpServlet {

	/**
	 * se obtienen mediante una consulta MetodoUrgenciaVital/CensoUrgenciaVital para asi
	 * obtener los datos para posteriormente mostrarlos en una pagina jsp.
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");
		HttpSession session = req.getSession(true);
		ResultSet rs = null;		
		MetodoUrgenciaVital muv = new MetodoUrgenciaVital();
		LinkedList listaUrg= new LinkedList();		
		try{
			rs=muv.CensoUrgenciaVital();
			while(rs.next()){
				Urgencia_vital uv=new Urgencia_vital();				
				uv.setDescripcion(rs.getString("descripcion"));
				uv.setFeha(rs.getString("fecha"));
				uv.setHora(rs.getString("hora"));
				uv.setCodigo(rs.getString("urg_codigo"));
				listaUrg.add(uv);											
			}	
		
			session.setAttribute("censourgencia", listaUrg);
			rs.getStatement().getConnection().close();
			res.sendRedirect("Adm_censo_UrgenciaVital.jsp");			
		}
		catch(Exception ex){
			ex.getMessage();
		}		
	}
}
