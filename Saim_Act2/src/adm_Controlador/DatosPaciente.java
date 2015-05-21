/**
 * Control:DatosPaciente
 * Se muestran los datos  de los pacientes del censo
 * Desarrollado:yosimar valega
 */

package adm_Controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import adm_logica.MetodoConsultarCenso;
import adm_logica.MetodoPaciente;

/**
 * Servlet implementation class DatosPaciente
 */
public class DatosPaciente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Se consulta los datos del paciente SQLDatosPac/MetodoPaciente
	 * Se calcula la edad calcularEdad/MetodoConsultarCenso
	 * Se redirecciona a la jsp paciente. con los datos a mostrar
	 * 
	 */
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String codigopac=request.getParameter("id");		
		MetodoPaciente pa =new MetodoPaciente();
		ResultSet rs=null;
		rs=pa.SQLDatosPac(codigopac);		
		String nombre,cedula,genero,afiliacion;
		nombre="";
		cedula="";
		genero="";
		afiliacion="";
		Date fecha;
		MetodoConsultarCenso cc = new MetodoConsultarCenso();
		try {
			int edad = 0;
			while(rs.next()){				
				nombre=rs.getString(1);
				cedula=rs.getString(2);
				fecha=rs.getDate(3);				
				Calendar cal = new GregorianCalendar();
				cal.setTime(fecha); 				
				edad = cc.calcularEdad(cal);				
				genero=rs.getString(4);
				afiliacion=rs.getString(5);				
			}
			rs.getStatement().getConnection().close();
			response.sendRedirect("paciente.jsp?nom="+nombre+"&ced="+cedula+"&ge="+genero+"&afi="+afiliacion+"&edad="+edad+"");
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
	}
}
