/**
 * controlador: ControlAprobarExamenIndividual se encuentra el proceso para la 
 * aprobacion de los examenes que son tipo individual.
*/
package lab_controlador;

import hic_metodo.MetodoPacienteLaboratorio;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lab_logica.MetodoAprobarExamenIndividual;

/**
 * Servlet implementation class ControlAprobarExamenIndividual
 */
public class ControlAprobarExamenIndividual extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");
		String va = req.getParameter("valor");
		MetodoAprobarExamenIndividual maei= new MetodoAprobarExamenIndividual();
		String codigoResul=req.getParameter("codigoResul");
		
		String codigou=req.getParameter("codigou");
		ResultSet rs=null;
		if (va.equals("2")){
			String nombre="";
			String apellido="";
			String completo="";
			rs=maei.usuario(codigou);
			try{
				if(rs.next()){
					nombre=rs.getString(1);
					apellido=rs.getString(2);
					completo=nombre+""+apellido;
				}
				rs.getStatement().getConnection().close();
			}
			catch(SQLException e){}

			maei.ActualizarResultado(codigoResul,completo,codigou);
			res.sendRedirect("lab_aprobarExamenIndividual.jsp");
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out=res.getWriter();
		ResultSet rs=null;
		String va = req.getParameter("valor");	
		String codigoResul=req.getParameter("codigoResul");
		String codigou=req.getParameter("codigou");
		String CodAsignacion=req.getParameter("CodAsignacion");
		MetodoAprobarExamenIndividual maei= new MetodoAprobarExamenIndividual();
		MetodoPacienteLaboratorio mpl=new MetodoPacienteLaboratorio();
		
		if(va.equals("1")){
			
			int contador=0;
			rs=maei.mostrartodo();
			
			out.println("<div class='scrollbar1'><table width='100%' ><tr><td colspan='9'aling='center' id='cabecera2' align='center'><div class='style11'>EXAMENES REALIZADOS</div></td></tr> <tr><td colspan='9'>&nbsp;</td></tr> <tr class='style6' align='center' bgcolor='#b8cce4'><td width='144' >Nombre Paciente </td><td width='116'>Cedula</td><td width='118'>Fecha y Hora </td><td width='152'>Nombre De Examen</td><td width='122'>Resultado</td><td width='132'>Valor Referencia </td><td width='127'><label>(SELECCIONAR TODOS)<input type='checkbox' name='all' id='all' onclick='checkAll();' /></label></td><td width='127'>Realizado Por </td><td width='127'>Omitir</td></tr>");
				try {	
					
				while(rs.next()){						
					out.print("<tr class='style6' align='center'><td>"+rs.getString(1)+" "+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+" "+rs.getString(5)+"</td><td>"+rs.getString(6)+"</td><td aling='center'>"+rs.getString(10)+"</td><td aling='center'>"+rs.getString(8)+" "+rs.getString(9)+" "+rs.getString(7)+"</td><td align='center'><label><input name='chkvalidar' type='checkbox' id='ca' value='"+rs.getString(12)+"' /></label></td><td align='center'>"+rs.getString(13)+"</td><td align='center'><a href='#' onClick='omitir("+rs.getString(12)+","+rs.getString(15)+")'>Omitir</a></td></tr>");
					contador=contador+1;
					
				}
				rs.getStatement().getConnection().close();
				out.print("<tr><td colspan='9' align='center'><label><input name='btnaprobar' class='boton4' type='button' id='btnaprobar' value='Aprobar' onclick='validar()'></label></td></tr></table></div>");
				out.println("<input name='yo' id='yo' type='hidden' value="+contador+">");
			} catch (SQLException e) {
				System.out.println("ERROR EN VA=1 "+e);
			}

		}
		
		
		if (va.equals("2")){
			String nombre="";
			String apellido="";
			String completo="";
			rs=maei.usuario(codigou);
			try{
				if(rs.next()){
					nombre=rs.getString(1);
					apellido=rs.getString(2);
					completo=nombre+" "+apellido;
				}
				rs.getStatement().getConnection().close();
			}
			catch(SQLException e){}
			//System.out.println("entro a va= 2");
			maei.ActualizarResultado(codigoResul,completo,codigou);
			res.sendRedirect("lab_aprobarExamenIndividual.jsp");
		}
		
		if(va.equals("3")){
			int contador=0;
			rs=maei.mostrartodoTexto();
			out.println("<div class='scrollbox1'><table width='100%' ><tr><td colspan='6'aling='center' bgcolor='#01426E'><span class='style11'>EXAMENES REALIZADOS</span></td></tr><tr><td width='144'>Nombre Paciente </td><td width='116'>Cedula</td><td width='118'>Fecha y Hora </td><td width='152'>Nombre De Examen</td><td width='122'>Resultado</td><td width='132'>(SELECCIONAR TODOS)<input type='checkbox' name='all' id='all' onclick='checkAll();' /></td></tr>");
				try {				
				while(rs.next()){						
					out.print("<tr><td>"+rs.getString(1)+" "+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(5)+" "+rs.getString(6)+"</td><td><a  href='#'onclick=''>"+rs.getString(4)+"</a></td><td aling='center'>"+rs.getString(7)+"</td><td aling='center'><input name='chkvalidar' type='checkbox' id='ca' value='"+rs.getString(8)+"' /></td></tr>");
					contador=contador+1;
				}
				rs.getStatement().getConnection().close();
				out.print("<tr><td colspan='6' align='center'><label><input name='btnaprobar' class='boton4' type='button' id='btnaprobar' value='Aprobar' onclick='validar()'></label></td></tr></table></div>");
				out.println("<input name='yo' id='yo' type='hidden' value="+contador+">");
			} catch (SQLException e) {
				System.out.println("ERROR EN VA=3 "+e);
			}
		}
		if(va.equals("4")){
			maei.Omitir(codigoResul);
			mpl.EliminarAsignacion(CodAsignacion);
		}
		
	}

}
