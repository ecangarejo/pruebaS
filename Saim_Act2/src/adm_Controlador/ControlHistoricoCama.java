/**
 * Controlador:ControlHistoricoCama
 * Ingresa datos en la tabla adm_historico_cama
 * Desarrollado:yosimar valega
 */

package adm_Controlador;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import adm_logica.MetodoAdmision;
import adm_logica.MetodoHistoricoCama;
/**
 * Servlet implementation class ControlHistoricoCama
 */
public class ControlHistoricoCama extends HttpServlet {
	
	/**
	 * Obtiene datos para ingresar en la tabla adm_historico_cama de ControlAdmision
	 * Se obtiene el codigo de la admision obtenerCodigo/MetodoAdmision
	 * Se ingresa los datos del historico cama en insertar/MetodoHistoricoCama
	 * 
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		String fechaIni,horaIni,fechaFinal,cod="";
		fechaIni="";
		horaIni="";
		cod=request.getParameter("cod");
		fechaIni=request.getParameter("fe");
		horaIni=request.getParameter("ho");
		String usuarioIni=request.getParameter("usuario");
		String usuarioFin="-1";
		
		fechaFinal="0000/00/00";
		String horaFinal="00:00:00";
		MetodoHistoricoCama hi=new MetodoHistoricoCama();
		MetodoAdmision ad=new MetodoAdmision();
		ResultSet rs=null;
		ResultSet rs1=null;
		rs=ad.obtenerCodigo(cod);
		String cen_num="";
		try{
			
			while(rs.next()){
				cen_num=rs.getString(1);	
			}
			rs.getStatement().getConnection().close();
		}catch(Exception ex){
			
		}
		rs1=hi.obtenernumeroingre(fechaIni, horaIni, cen_num);
		String num="";
		try{
			
			while(rs1.next()){
				num=rs1.getString(1);
			}
			rs1.getStatement().getConnection().close();
		}catch(Exception ex){
			
		}
		
		hi.insertar(cen_num, fechaIni, fechaFinal, num,horaIni,usuarioIni,horaFinal,usuarioFin);
		//response.sendRedirect("ControlReporte?cod="+num+"");
		//response.sendRedirect("adm_ReporteAdmision.jsp?cod="+num);
		
	
			
	}

}
