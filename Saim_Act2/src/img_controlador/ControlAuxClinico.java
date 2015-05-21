package img_controlador;

import hic_metodo.MetodoImagenologiasPendientes;
import img_logica.MetodoAuxClinico;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ControlAuxClinico
 */
public class ControlAuxClinico extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
res.setContentType("text/html;charset=UTF-8");
		

Calendar c = new GregorianCalendar();
String dia = Integer.toString(c.get(Calendar.DATE));
String mes = Integer.toString(c.get(Calendar.MONTH));
String annio = Integer.toString(c.get(Calendar.YEAR));
int m=Integer.parseInt(mes)+1;
mes=String.valueOf(m);
int d=Integer.parseInt(dia);
if(d<10){dia="0"+d;}
if(m<10){mes="0"+m;}

String fechacjmysql=annio+"-"+mes+"-"+dia; //para la base de datos

Calendar calendario = Calendar.getInstance();
int hora;
int minutos, segundos;
hora =calendario.get(Calendar.HOUR_OF_DAY);
minutos = calendario.get(Calendar.MINUTE);
segundos = calendario.get(Calendar.SECOND);
String hracjmysql= hora+":"+minutos+":"+segundos; 



		String va=req.getParameter("valor");
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		ResultSet rs3=null;
		PrintWriter out=res.getWriter();
		MetodoImagenologiasPendientes mp=new MetodoImagenologiasPendientes();
		MetodoAuxClinico mr = new MetodoAuxClinico();
		
		
		if(va.equals("9")||va.equals("todos")){
			int contador=0;
			rs=mr.ObtenerExamenesAsignadosNombreTecnico();
			
			out.println(
					"<div class='scrollbox5'><table class='style6' width='100%' border='1' bordercolor='#1C777B' >" +
					"<tr id='cabecera2' class='style11' align='center'>" +
					"<td width='147' ><strong>Fecha de la Cita </strong></td>" +
					"<td width='157'><strong>Paciente</strong></td>" +
					"<td width='94'><strong>Documento</strong></td>" +
					"<td width='157'><strong>Examenes Pendientes </strong></td>" +
					"<td width='97'><strong>Ubicacion </strong></td>" +
					"<td width='89'><strong>Trasladar a</strong></td>" +
					"<td width='89'><strong>trasladar</strong></td></tr>");
			try{				 
				while(rs.next()){
				
					
					out.println("<tr align='center'>" +
							"<td>"+rs.getString(1)+"-"+rs.getString(2)+"</td>" +
							"<td>"+rs.getString(3)+"</td>" +
							"<td>"+rs.getString(4)+"</td>" +
							"<td>"+rs.getString(5)+"</td>" +
							"<td>"+rs.getString(6)+"</td>" +
							"<td>"+rs.getString(7)+"</td>" +
							"<td><a href='#' onclick='trasladar("+rs.getString(8)+")'><strong><em>Trasladar</em></strong></a></td></tr>");
					contador=contador+1;
				}
				out.println("</table></div>");
				rs.getStatement().getConnection().close();
			}
			catch(SQLException e){
				System.out.println("Error en Controlador va=4 "+e);
				e.printStackTrace();
			}
		}
		
		
		
		
		if(va.equals("trasladar")){
			String codUsuario =  req.getParameter("CodUsuario");
			String codTraslado =  req.getParameter("codTraslado");
			String servicio =  "";
			String trasladado = ""; 
			
			System.out.println("trasladar cod "+codTraslado+" usuario "+codUsuario);
			boolean insercciones_realizadas_con_exito = false;
			rs=mr.ObtenerUbicacionPac(codTraslado);
			
			
			try{				 
				if(rs.next()){
				servicio =rs.getString(1);
				trasladado=rs.getString(2);
					}
				rs.getStatement().getConnection().close();
			}
			catch(SQLException e){
				System.out.println("Error en Controlador va=trasladar "+e);
				e.printStackTrace();
			}
			
			
			 insercciones_realizadas_con_exito=mr.trasladar(
					   codTraslado,
					   codUsuario,
					   fechacjmysql,
						hracjmysql, 
						"1",
						servicio,
						trasladado
						);
			 if(insercciones_realizadas_con_exito == true){
			      out.print("traslado exitoso ");		  
				  }else{
				  out.print("no pudo realizarse el traslado");
				  }
			
			
		}
		
		
		if(va.equals("cargarUsu")){			
			out.print("<table border='0' width='100%' ><tr><td><div id='cabecera2' class='style11' align='center'>Traslados Realizados Por Usuario </div>" +
					"</td></tr></table><table width='100%' border='0'>" +
					"<tr class='style6'><td width='8%'><label>Usuario</label></td><td>" +
					"<select name='cmbAuxiliar' class='style3' id='cmbAuxiliar' onchange=''>" +
					"<option value='Seleccione' selected='selected'>Seleccione</option>");
			rs=mr.ObtenerAuxiliares();
			try {
				while(rs.next()){
					out.print("<option value='"+rs.getString(1)+"'>"+rs.getString(2)+"</option>");
				}
				out.print("</select></td>" +
						"<td width='7%'>Fecha Inicial:</td><td width='15%'><label><input name='FI' type='text' id='FI' size='10'  onKeyup='masca(this,patron,true,0,0,0)' /></label></td>" +
						"<td width='7%'>Fecha Final:</td><td width='15%' ><label><input name='FF' type='text' id='FF' size='10' onKeyup='masca(this,patron,true,0,0,0)'   /></label></td>" +
							"<td ><input type='button' id='buscarUsuarios' value='Buscar' onclick='TrasPorUsuario2()' /></td>"+
						"</tr></table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
		}
		
		
		if(va.equals("cargarTrasUsu")){
			String idAux = req.getParameter("idAux");
			String fi = req.getParameter("fi");
			String ff = req.getParameter("ff");
			
			String a1=fi.substring(0, 2);
			String m1=fi.substring(3, 5);
			String d1=fi.substring(6, 10);
			String fin=d1+"-"+m1+"-"+a1;
						
			String a2=ff.substring(0, 2);
			String m2=ff.substring(3, 5);
			String d2=ff.substring(6, 10);
			String ffn=d2+"-"+m2+"-"+a2;
			
			out.print("<table border='0' width='100%' ></table><table width='100%' border='0'>" +
					"<tr class='style6'><td width='8%'><label>Usuario</label></td><td>" +
					"<select name='cmbAuxiliar' class='style3' id='cmbAuxiliar' onchange=''>" +
					"<option value='Seleccione' selected='selected'>Seleccione</option>");
			rs1=mr.ObtenerAuxiliares();
			try {
				while(rs1.next()){
					out.print("<option value='"+rs1.getString(1)+"'>"+rs1.getString(2)+"</option>");
				}
				out.print("</select></td>" +
						"<td width='7%'>Fecha Inicial:</td><td width='15%'><label><input name='FI' type='text' id='FI' size='10'  onKeyup='masca(this,patron,true,0,0,0)' /></label></td>" +
						"<td width='7%'>Fecha Final:</td><td width='15%' ><label><input name='FF' type='text' id='FF' size='10' onKeyup='masca(this,patron,true,0,0,0)'   /></label></td>" +
							"<td ><input type='button' id='buscarUsuarios' value='Buscar' onclick='TrasPorUsuario2()' /></td>"+
						"</tr></table>");
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			
			int contador=1;
			rs=mr.ObtenerTrasladosUsuariosFecha(idAux,fin,ffn);			
			out.println("<table width='100%'><tr><td colspan='6' align='center' id='cabecera2'>" +
					"<div align='center' class='style11'>TRASLADOS REALIZADOS POR USUARIO</span></td></tr>" +
					"<tr><td>&nbsp;</tr></td></table> <div class='scrollbox1'>" +
					"<table width='100%' border='1' bordercolor='#1C777B' class='style6' >" +
					"<tr id='cabecera2' align='center' class='style11'>" +
					"<td width='5%'><strong>#</strong></td>" +
					"<td><strong>Fecha/Hora Solicitud /Traslado</strong></td>" +
					"<td><strong>Nombre Paciente  </strong></td>" +
					"<td><strong>Documento</strong></td>" +
					"<td><strong>Estudio </strong></td>" +
					"<td><strong>Trasladado de </strong></td>" +
					"<td><strong>trasladado a</strong></td>" +
					"<td><strong>Usuario </strong></td></tr>");
			try{
				
				while(rs.next()){
					//out.println("<tr align='center'><td>"+rs.getString(1)+"-"+rs.getString(2)+"</td><td><a href='#' onclick='mostarexamenes("+rs.getString(5)+","+rs.getString(8)+")'>"+rs.getString(7)+"</a></td><td>"+rs.getString(3)+" "+rs.getString(4)+"</td><td>"+rs.getString(9)+"</td><td>"+rs.getString(6)+"</td><td><a href='#' onclick='omitir("+rs.getString(8)+")'><strong><em>Omitir</em></strong></a></td></tr>");
					out.println("<tr align='center'>" +
							    "<td>"+contador+"</td>"+
							    "<td>"+rs.getString(1)+"->"+rs.getString(2)+"</td>" +
							    "<td>"+rs.getString(3)+"</td>" +
							    "<td>"+rs.getString(4)+"</td>" +
							    "<td>"+rs.getString(5)+"</td>" +
							    "<td>"+rs.getString(6)+"</td>" +
							    "<td>"+rs.getString(7)+"</td>" +
							    "<td>"+rs.getString(8)+"</td>" +
									"</tr>");
					contador=contador+1;
				}
				out.println("</table></div>");
				rs.getStatement().getConnection().close();
			}
			catch(SQLException e){
				System.out.println("Error en Controlador va=4 "+e);
				e.printStackTrace();
			}
			
		}
		
		
		if(va.equals("todosUsu")){			
			out.print("<table border='0' width='100%' ><tr><td><div id='cabecera2' class='style11' align='center'>Traslados Realizados por Todos los Usuarios </div>" +
					"</td></tr></table><table width='100%' border='0'>" +
					"<tr class='style6'>"+
					"<td width='7%'>Fecha Inicial:</td><td width='15%'><label><input name='FI' type='text' id='FI' size='10'  onKeyup='masca(this,patron,true,0,0,0)' /></label></td>" +
					"<td width='7%'>Fecha Final:</td><td width='15%' ><label><input name='FF' type='text' id='FF' size='10' onKeyup='masca(this,patron,true,0,0,0)'   /></label></td>" +
						"<td ><input type='button' id='buscarUsuarios' value='Buscar' onclick='TrasPorUsuario3()' /></td>"+
						"</tr></table>");
				
			
		}
		
		
		if(va.equals("cargarTras")){
			String fi = req.getParameter("fi");
			String ff = req.getParameter("ff");
			
			String a1=fi.substring(0, 2);
			String m1=fi.substring(3, 5);
			String d1=fi.substring(6, 10);
			String fin=d1+"-"+m1+"-"+a1;
						
			String a2=ff.substring(0, 2);
			String m2=ff.substring(3, 5);
			String d2=ff.substring(6, 10);
			String ffn=d2+"-"+m2+"-"+a2;
			
			out.print("<table border='0' width='100%' ><tr><td><div id='cabecera2' class='style11' align='center'>Traslados Realizados por Todos los Usuarios </div>" +
					"</td></tr></table><table width='100%' border='0'>" +
					"<tr class='style6'>"+
					"<td width='7%'>Fecha Inicial:</td><td width='15%'><label><input name='FI' type='text' id='FI' size='10'  onKeyup='masca(this,patron,true,0,0,0)' /></label></td>" +
					"<td width='7%'>Fecha Final:</td><td width='15%' ><label><input name='FF' type='text' id='FF' size='10' onKeyup='masca(this,patron,true,0,0,0)'   /></label></td>" +
						"<td ><input type='button' id='buscarUsuarios' value='Buscar' onclick='TrasPorUsuario3()' /></td>"+
						"</tr></table>");
			
			
			int contador=1;
			rs=mr.ObtenerTrasladosFecha(fin,ffn);			
			out.println("<table width='100%'><tr><td colspan='6' align='center' id='cabecera2'>" +
					"<div align='center' class='style11'>TRASLADOS REALIZADOS POR USUARIO</span></td></tr>" +
					"<tr><td>&nbsp;</tr></td></table> <div class='scrollbox1'>" +
					"<table width='100%' border='1' bordercolor='#1C777B' class='style6' >" +
					"<tr id='cabecera2' align='center' class='style11'>" +
					"<td width='5%' ><strong>#</strong></td>" +
					"<td ><strong>Fecha/Hora Solicitud /Traslado</strong></td>" +
					"<td ><strong>Nombre Paciente  </strong></td>" +
					"<td ><strong>Documento</strong></td>" +
					"<td ><strong>Estudio </strong></td>" +
					"<td ><strong>Trasladado de </strong></td>" +
					"<td ><strong>trasladado a</strong></td>" +
					"<td ><strong>Usuario </strong></td></tr>");
			try{
				
				while(rs.next()){
					//out.println("<tr align='center'><td>"+rs.getString(1)+"-"+rs.getString(2)+"</td><td><a href='#' onclick='mostarexamenes("+rs.getString(5)+","+rs.getString(8)+")'>"+rs.getString(7)+"</a></td><td>"+rs.getString(3)+" "+rs.getString(4)+"</td><td>"+rs.getString(9)+"</td><td>"+rs.getString(6)+"</td><td><a href='#' onclick='omitir("+rs.getString(8)+")'><strong><em>Omitir</em></strong></a></td></tr>");
					out.println("<tr align='center'>" +
							    "<td>"+contador+"</td>"+    
							    "<td>"+rs.getString(1)+"->"+rs.getString(2)+"</td>" +
							    "<td>"+rs.getString(3)+"</td>" +
							    "<td>"+rs.getString(4)+"</td>" +
							    "<td>"+rs.getString(5)+"</td>" +
							    "<td>"+rs.getString(6)+"</td>" +
							    "<td>"+rs.getString(7)+"</td>" +
							    "<td>"+rs.getString(8)+"</td>" +
									"</tr>");
			
					contador=contador+1;
				}
				out.println("</table></div>");
				rs.getStatement().getConnection().close();
			}
			catch(SQLException e){
				System.out.println("Error en Controlador va=4 "+e);
				e.printStackTrace();
			}
			
		}
		
		
		
	}//fin do post

}
