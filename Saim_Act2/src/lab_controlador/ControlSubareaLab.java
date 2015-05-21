/**
 * controlador: ControlSubareaLab se encuentra el proceso para  
 * la consulta de las areas y la insercion de las subareas.
*/
package lab_controlador;

import img_logica.MetodoCrearSubExa;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lab_logica.MetodoSubarea;

/**
 * Servlet implementation class ControlSubareaLab
 */
public class ControlSubareaLab extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest re, HttpServletResponse res)throws ServletException, IOException{
		/**
		 * se obtienen las areas mediante una consulta
		 * 	MetodoSubarea/SQLArea
		 */
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out=res.getWriter();
		MetodoSubarea sa = new MetodoSubarea();
        ResultSet rs=null;
		rs=sa.SQLArea();
		String v[] = new String[1000];
		//System.out.println("listo");
		
		try {
			int c=0;
			while(rs.next()){
			
				v[c]=rs.getString(1);
				out.println(v[c]+"_");
				c++;
			}
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	
	
	public void doGet(HttpServletRequest re, HttpServletResponse res)throws ServletException, IOException{
		/**
		 * 	se escoge el codigo del area escogida para posteriormente
		 *	insertarlo en la base de datos mediante MetodoSubarea/SQLCodArea
		 * 	posteriormente se hace la insercion de la subarea mediante
		 *	MetodoSubarea/insertar que lleva como parametro el nombre de la subarea
		 *	y el codigo del area.
		 * 
		 */
		String v[] = new String[1000];
		String subarea=null;
		String nombre=null;
		MetodoCrearSubExa mcs = new MetodoCrearSubExa ();
		java.util.Date fechaActual = new java.util.Date();
		java.sql.Date fecha = new java.sql.Date(fechaActual.getTime());		
		java.sql.Time hora = new java.sql.Time(fechaActual.getTime());		
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		String CodLabora="";
		String CodPrograma="";	
		String tipo_examen="1";
		nombre = re.getParameter("nombre");
		subarea=re.getParameter("subarea");
		String CodUsu=re.getParameter("CodUsu");
		MetodoSubarea sa = new MetodoSubarea();
		String cero="0";
		String blanco="";
		
		try {
			mcs.CrearPrograma(cero, cero, blanco, blanco, subarea, cero, cero, cero, cero, cero, fecha, hora, CodUsu);
			rs1=mcs.ObtenerCodigoPrograma(subarea, fecha, hora, CodUsu);
			if(rs1.next()){
				CodPrograma=rs1.getString(1);
			}
			rs1.getStatement().getConnection().close();
		} catch (SQLException e) {
			System.out.print("Error ControlCrearSubExa>>ObtenerCodigoPrograma "+e);
			e.printStackTrace();
		}	    
	    
		try {
			 rs=sa.SQLCodArea(nombre);				 
			int c=0;
			while(rs.next()){
				PrintWriter out=res.getWriter();
				v[c]=rs.getString(1);
				out.println(v[c]+"_");
				c++;
			}
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			sa.insertar(subarea,v[0]);
			rs2=sa.ObtenerCodigoSubarea(subarea,v[0]);
			if(rs2.next()){
				CodLabora=rs2.getString(1);
			}
			rs2.getStatement().getConnection().close();
		} catch (SQLException e) {
			System.out.print("Error ControlCrearSubExa>>ObtenerCodigoSubarea "+e);
			e.printStackTrace();
		}
		sa.InsertarProg_Lab(CodLabora, CodPrograma, tipo_examen);
		
		
	res.sendRedirect("lab_subarea.jsp");			
	}

}
