/**
 * controlador: ControlImagAprobar se encuentra el proceso para la 
 * aprobacion de los examenes.
*/
package img_controlador;

import img_logica.MetodoAprobarImg;
import img_logica.MetodoimgPa;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControlImagAprobar {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		ResultSet rs=null;
		ResultSet rs1=null;
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out=res.getWriter();
		String va = req.getParameter("valor");
		String completo="";
		String nombre="";
		String apellido="";
		MetodoAprobarImg mai=new MetodoAprobarImg ();
		
		
		if(va.equals("1")){
			rs=mai.ObtenerExamenesParaAprobacion();
			rs1=mai.ObtenerExamenesParaAprobacion();
			try{
				while(rs1.next()){
					nombre=rs1.getString(1);
					apellido=rs1.getString(2);
					
				}
				completo=nombre+" "+apellido;
				rs1.getStatement().getConnection().close();
			}
			
			catch (SQLException e) {
				System.out.println("Error en Controlador va=1.1 "+e);
				e.printStackTrace();  
			}
			out.println("<table width='100%' border='1'><tr><td width='747' colspan='2'>NOMBRE DEL PACIENTE: "+completo+"</td></tr><tr><td colspan='2' align='center'>EXAMENES A REALIZAR </td></tr>");
			try {
				while(rs.next()){
					out.println("<tr><td colspan='2' align='center'>"+rs.getString(5)+"</td></tr><tr><td colspan='2' align='center'><label><textarea name='txtdescripcion' cols='80' rows='6' id='txtdescripcion'>"+rs.getString(7)+"</textarea></label></td></tr><input name='codExaCita' id='codExaCita' type='text' value='"+rs.getString(6)+"'>");					
				}
			out.println("<tr><td colspan='2' align='center'><input name='codExaCita' id='codExaCita' type='text' value='"+rs.getString(6)+"'><label><input name='btnIngresar' type='button' id='btnIngresar' value='Ingresar' onclick='Actualizar()'></label></td></tr></table>");
			rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				System.out.println("Error en Controlador va=1 "+e);
				e.printStackTrace();
			}
			
		}
		
		
	}
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		ResultSet rs=null;	
		String cad =req.getParameter("codigo");  
		MetodoimgPa img=new MetodoimgPa();	
		res.setContentType("text/html;charset=UTF-8");
	 int accion;
     
        accion = Integer.parseInt(req.getParameter("txtAccion"));
if(accion == 26){          

        try {
            rs =img.listarParaAutocompletarPaciente(cad);
            String cadena ="";
            String nombre ="";
            cadena="[";
            while(rs.next()){
            	nombre=rs.getString(2)+" "+rs.getString(3);
            	cadena = cadena+"'"+rs.getString(1)+"|"+nombre+"'";
            	cadena = cadena +",";	
            }
            cadena = cadena+"]";
            res.getWriter().write(cadena);
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.getMessage();
			e.printStackTrace();
			}

    }
	}

}
