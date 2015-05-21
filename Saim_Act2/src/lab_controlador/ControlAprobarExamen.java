/**
 * controlador: ControlAprobarExamen se encuentra el proceso para la 
 * aprobacion de los examenes que son tipo grupo.
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

import lab_logica.MetodoAprobarExamen;
import lab_logica.MetodoAprobarExamenIndividual;

/**
 * Servlet implementation class ControlAprobarExamen
 */
public class ControlAprobarExamen extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");
		String va = req.getParameter("valor");
		String codigoResul=req.getParameter("codigoResul");
		MetodoAprobarExamenIndividual maei= new MetodoAprobarExamenIndividual();
		MetodoAprobarExamen mae = new MetodoAprobarExamen ();
		String codigou=req.getParameter("codigou");
		ResultSet rs=null;
		if (va.equals("3")){
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
			mae.ActualizarResultado(codigoResul,completo,codigou);
		}
		res.sendRedirect("lab_aprobarExamen.jsp");
		
	}	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");
		MetodoAprobarExamen mae = new MetodoAprobarExamen ();
		MetodoAprobarExamenIndividual maei= new MetodoAprobarExamenIndividual();
		MetodoPacienteLaboratorio mpl= new MetodoPacienteLaboratorio();
		String va = req.getParameter("valor");	
		String codsub=req.getParameter("codsub");
		String numero=req.getParameter("numero");
		String horas=req.getParameter("horas");
		String fecha=req.getParameter("fecha");
		String codigoResul=req.getParameter("codigoResul");
		String codigou=req.getParameter("codigou");
		String CodAsignacion=req.getParameter("CodAsignacion");
		PrintWriter out=res.getWriter();
		ResultSet rs=null; 
		
		if(va.equals("1")){
			//ResultSet rs=null;
			rs=mae.mostrartodo(); 
			out.println("<table width='100%' align='center'  ><tr><td colspan='6'  id='cabecera2'><div align='center' class='style11'>EXAMENES REALIZADOS</div></td></tr> <tr><td colspan='5'>&nbsp;</td></tr> <tr bgcolor='#b8cce4' class='style6' align='center'><td width='232' >Fecha</td><td width='188'>Nombre Del Examen</td><td width='114'>Cedula</td><td width='241'>Nombre Del Paciente </td><td width='241'>Realizado Por </td><td>Accion</td></tr>");
			try {			 
				while(rs.next()){ 					  
					String ano=rs.getString(4).substring(0,4);
					String mes=rs.getString(4).substring(5,7);
					String dia=rs.getString(4).substring(8,10);		
					String hora=rs.getString(5).substring(0,2);
					String minuto=rs.getString(5).substring(3,5); 
					String segundo=rs.getString(5).substring(6,8);					
					out.print("<tr class='style6' align='center'><td>"+rs.getString(4)+"/"+rs.getString(5)+"</td><td><a  href='#' onclick='mostarexamenes("+ano+","+mes+","+dia+","+hora+","+minuto+","+segundo+","+rs.getString(9)+","+rs.getString(10)+")'>"+rs.getString(6)+"</a></td><td>"+rs.getString(3)+"</td><td>"+rs.getString(2)+" "+rs.getString(1)+" "+rs.getString(12)+"</td> <td>"+rs.getString(11)+"</td><td><a href='#' onclick='OmitirExamen("+rs.getString(13)+")'>Omitir</a></td></tr>");
				}
				out.print("<tr><td>&nbsp;</td></tr></table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				System.out.println(e);
			}
		}
		
		if(va.equals("2")){
			//ResultSet rs=null;
			String CodAsi="";
			int contador=0;
			rs=mae.verExamenes(numero, codsub, fecha, horas);
			out.println("<table width='100%' align='center' class='style6' ><tr><td colspan='5' align='center' id='cabecera2'><div class='style11'>EXAMENES REALIZADOS</div></td></tr></table><div class='scrollbox4'><table width='100%' class='style6'><tr align='center' bgcolor='#dbe5f1'><td width='164'>NOMBRE DE EXAMEN</td><td width='107'> RESULTADO </td><td width='168'> VALOR REFERENCIA </td><td width='118'> UNIDADES </td><td width='89'><label>(SELECCIONAR TODOS)<input type='checkbox' name='all' id='all' onclick='checkAll()' /></label></td></tr>");
				try {				
				while(rs.next()){
					CodAsi=rs.getString(12);
					out.print("<tr class='style6' align='center'><td>"+rs.getString(4)+"</td><td align='center'>"+rs.getString(8)+"</td><td aling='center'>"+rs.getString(6)+"-"+rs.getString(7)+"</td><td aling='center'>"+rs.getString(5)+"</td><td align='center'><label><input name='chkvalidar' type='checkbox' id='ca' value='"+rs.getString(11)+"' /></label></td></tr>");
					contador=contador+1;
				}
				rs.getStatement().getConnection().close();
				out.print("</table></div>");
				out.print("<table width='100%'><tr><td align='center'><label><input name='btnaprobar' type='button' class='boton4'  id='btnaprobar' value='Aprobar' onclick='validar()'></label></td><td align='center'>&nbsp;</td><td align='center'>&nbsp;</td><td align='center'>&nbsp;</td><td align='center'><input name='btnomitir' type='button' class='boton4' id='btnomitir' value='Omitir' onclick='omitir()'></td></tr></table>");
				out.println("<input name='yo' id='yo' type='hidden' value="+contador+">");
				out.print("<input name='txtCodAsignacion' id='txtCodAsignacion' type='hidden' value="+CodAsi+">");
			} catch (SQLException e) {
				System.out.println("ERROR EN VA=2 "+e);
			}
		}
		
		if (va.equals("3")){
			
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
			mae.ActualizarResultado(codigoResul,completo,codigou);
		}
		
		if(va.equals("4")){		
			mae.OmitirResultado(codigoResul);			
		}
		
		if(va.equals("5")){
			
			try {
				mpl.EliminarAsignacion(CodAsignacion);
				rs=mpl.ObtenerAsignacion(CodAsignacion);
				if(rs.next()){
					out.print("Error al Eliminar Intente Nuevamente.");
				}
				else{
					out.print("Dato Omitido Exitosamente.");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
