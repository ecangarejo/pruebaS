package farc_controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.text.*;
import java.util.StringTokenizer;  //para dividir por token |

import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.lang.String;

import farc_metodo.MetodoReporte;


/**
 * Servlet implementation class ControlEntradas
 */
public class ControlReporteFarc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.setContentType("text/xml; charset=ISO-8859-1");
		response.setContentType("text/html;charset=UTF-8");
		MetodoReporte me = new MetodoReporte();
		ResultSet rs = null;
		ResultSet rs1 = null;
		String va = request.getParameter("valor");
		String fechaini=request.getParameter("fechaini");
		String fechafin = request.getParameter("fechafin");
		String pi = request.getParameter("pi");
		String pf=request.getParameter("pf");
		String anio = request.getParameter("anio");
		Integer i=0;	
		long suma=0;
		PrintWriter out=response.getWriter();

		if(va.equals("1")){
			rs=me.BuscarPeriodo(fechaini, fechafin);			
			try {
				out.print("<table width='100%' ><tr><td><div align='center' class='style11' id='cabecera2'>REPORTE SISMED COMPRAS </div></td></tr></table>");
				out.print("<table border='1' width='100%'>" +
					"<tr  BGCOLOR='#CCCCCC' align='center'><td>1</td><td>2</td><td>NI</td><td>800129856</td><td>5</td><td></td><td>"+anio+"</td><td>"+pi+"</td><td>"+pf+"</td><td><div id='cj'></div></td></tr>");
				while(rs.next()){					
					i=i+1;
					String[] arrayColores = rs.getString("fecha").split("-");
					out.print("<tr><td>2</td> " +
							"<td>"+i+"</td>" +
							"<td>"+arrayColores[1] +"</td> " +
							"<td>"+rs.getString("cod_cups")+"</td>" +
							"<td>"+rs.getString("Precio_Minimo")+"</td>" +
							"<td>"+rs.getString("Precio_Maximo")+"</td>" +
							"<td>"+rs.getString("valorTotal")+"</td>" +
							"<td>"+rs.getString("cantidad")+"</td> " +
							//"<td>"+rs.getString("cantidad")+"</td> " +
									"");
					suma=suma+Integer.parseInt(rs.getString(7));
					rs1=me.Buscarfact(rs.getString("Precio_Minimo"), rs.getString("CodMedica"));
					if(rs1.next()){
						out.print("<td>"+rs1.getString("numero_factura")+"</td>");
					}else{
						out.print("<td>N/A</td>");
					}
					rs1.getStatement().close();
					rs1=me.Buscarfact(rs.getString("Precio_Maximo"), rs.getString("CodMedica"));
					if(rs1.next()){
						out.print("<td>"+rs1.getString("numero_factura")+"</td></tr>");
					}else{
						out.print("<td>N/A</td>");
					}
					rs1.getStatement().close();
				}
				out.print("<tr><td><input type='hidden' id='ii' value="+i+" /></td></tr>");
				out.print("<tr><td></td><td></td><td></td><td></td><td></td><td></td><td>"+suma+"</td><td></td><td></td><td></td></tr>");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.print("</table>");
		}
		
		if(va.equals("2")){
			rs=me.BuscarPeriodo(fechaini, fechafin);			
			try {
				out.print("<table width='100%' ><tr><td><div align='center' class='style11' id='cabecera2'>REPORTE SISMED COMPRAS </div></td></tr></table>");
				out.print("<table border='1' width='100%'>" +
					"<tr  BGCOLOR='#CCCCCC' align='center'><td>1</td><td>2</td><td>NI</td><td>800129856</td><td>5</td><td></td><td>"+anio+"</td><td>"+pi+"</td><td>"+pf+"</td><td><div id='cj'></div></td></tr>");
				while(rs.next()){					
					i=i+1;
					String[] arrayColores = rs.getString("fecha").split("-");
					out.print("<tr><td>2</td> " +
							"<td>"+i+"</td>" +
							"<td>"+arrayColores[1] +"</td> " +
							"<td>"+rs.getString("cod_cups")+"</td>" +
							"<td>"+rs.getString("Precio_Minimo")+"</td>" +
							"<td>"+rs.getString("Precio_Maximo")+"</td>" +
							"<td>"+rs.getString("valorTotal")+"</td>" +
							"<td>"+rs.getString("cantidad")+"</td> " +
							//"<td>"+rs.getString("cantidad")+"</td> " +
									"");
					suma=suma+Integer.parseInt(rs.getString(7));
					rs1=me.Buscarfact(rs.getString("Precio_Minimo"), rs.getString("CodMedica"));
					if(rs1.next()){
						out.print("<td>"+rs1.getString("numero_factura")+"</td>");
					}else{
						out.print("<td>N/A</td>");
					}
					rs1.getStatement().close();
					rs1=me.Buscarfact(rs.getString("Precio_Maximo"), rs.getString("CodMedica"));
					if(rs1.next()){
						out.print("<td>"+rs1.getString("numero_factura")+"</td></tr>");
					}else{
						out.print("<td>N/A</td>");
					}
					rs1.getStatement().close();
				}
				out.print("<tr><td><input type='hidden' id='ii' value="+i+" /></td></tr>");
				out.print("<tr><td></td><td></td><td></td><td></td><td></td><td></td><td>"+suma+"</td><td></td><td></td><td></td></tr>");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.print("</table>");
		}

	}
}
