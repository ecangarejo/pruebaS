package fact_controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fact_metodo.MetodoIngNumAuto;



public class ControlIngNumAuto extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		
		String va = req.getParameter("valor");
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		MetodoIngNumAuto mina = new MetodoIngNumAuto();
		
		String Adm=req.getParameter("Adm");
		String Ent=req.getParameter("Ent");
		String user=req.getParameter("user");
		String Fact=req.getParameter("Fact");
		ResultSet rs = null;
		ResultSet rs1 = null;
		
		
		if(va.equals("xAdm")){
			System.out.println("usuario "+user);
			out.println("<table width='80%' >");
			out.println("<tr><td width='20%'>Digite No. de Admision</td><td width='30%'><input name='Adm' type='text' id='Admi' size='30'  /></td><td width='50%'><input type='button' value='  Buscar  ' onclick='BuscarDat("+user+")' name='Buscar' /></td></tr>");
			//out.print("<table width='421' border='0'><tr><td width='88'><div align='right'>Descripci&oacute;n:</div></td><td width='323'><input name='Descripcion' type='text' id='Descripcion' size='45' maxlength='100' onkeydown='A(this, event)' /></td></tr>");
			out.print("<tr><td colspan='3'><div id='datos' > </div></td></tr></table>");
			
			
		}
		
		if(va.equals("xFact")){
		 out.println("<table width='80%' >");
		 out.println("<tr><td width='20%'>Digite Numero de Factura : </td><td width='30%' ><input name='consec' type='text' id='consec' /></td><td width='50%' ><input type='button' value=' Buscar ' onclick='BuscarDatFact("+user+")'  name='Buscar' /></td></tr>");
		 out.print("<tr><td colspan='3'><div id='datos' > </div></td></tr></table>");
		}
		
		if(va.equals("BAdm")){
			System.out.println("BAdm user"+user);
			int sw=0;
			rs=mina.BuscarDatos(Adm);
			out.println("<table width='80%'>");
			out.println("<tr><td>Identificacion</td><td>Nombre del Paciente</td><td>Fecha de Ingreso</td><td>Entidad</td><td>No. de Autorizacion</td></tr>");
			try {
				while(rs.next()){
					out.println("<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(5)+"</td><td>"+rs.getString(4)+"</td><td><input type='button' value =' Editar ' onclick='Edit("+Adm+","+rs.getString(6)+","+user+")'</input></td></tr>");
					sw=1;
				}rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(sw==0){
				out.println("<tr><td colspan='5'><b><font color='#3B5C91'>No existen datos para la Admision No. "+Adm+"</font><b></td>");
			}
			out.println("<tr><td colspan='4'><div id='auto' ></div></td></tr>");
			out.println("</table>");
			
		}
		
		if(va.equals("BFact")){
			int sw=0;
			rs=mina.BuscarDatosFact(Fact);
			out.println("<table width='80%'>");
			out.println("<tr><td>No. de Factura </td><td>Eps</td><td>Identificacion</td><td>Nombre del Paciente</td><td>Admision</td><td>No. de Autorizacion</td></tr>");
			try {
				while(rs.next()){
					out.println("<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td><td>"+rs.getString(7)+"<input type='hidden' value='"+Fact+"' id='conseF' /></td><td><input type='button' value='  Editar  ' onclick='EditD("+user+","+rs.getString(6)+")' </input></td> </tr>");
					sw=1;
				}rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(sw==0){
				out.println("<tr><td colspan='5'><b><font color='#3B5C91'>No existen datos para la Factura No. "+Fact+"</font><b></td>");
			}
			out.println("<tr><td colspan='4'><div id='auto' ></div></td></tr>");
			out.println("</table>");
		}
		
		if(va.equals("Edit")){
		
		out.println("<table width='80%'>");
		out.println("<tr><td colspan='4' ></td><td>Para la Admision "+Adm+" Digite No. de Autorizacion :  <input type='text' size='25' id='NumAuto' /></td><td> <input type='button' value=' Guardar ' onclick='Guardar("+Adm+","+Ent+","+user+")'</td></tr>");
		out.println("</table>"); 
		
		}
		
		if(va.equals("EditF")){
			String enca=req.getParameter("enca");
			out.println("<table width='80%'>");
			out.println("<tr><td colspan='4' ></td><td>Para la Factura "+Fact+" Digite No. de Autorizacion :  <input type='text' size='25' id='NumAuto' /></td><td> <input type='button' value=' Guardar ' onclick='GuardarF("+user+","+enca+")'</td></tr>");
			out.println("</table>"); 
			
			}
		
		if(va.equals("GuardarAuto")){
			String noauto=req.getParameter("noauto");
			String fact="";
			String NombreEnt="";
			
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
			java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());
			System.out.println("noauto "+noauto);
			 mina.GuardarAutAdm(Adm,noauto);
			 mina.GuardarAutEncCero(Adm,Ent,noauto);
			 mina.GuardarHistorico(Adm, user, Ent, Fecha, Hora,"NULL", noauto);
			 rs=mina.BuscarEnca(Adm,Ent);
			 try {
				while(rs.next()){
					fact=rs.getString(1)+" , "+fact;
					NombreEnt=rs.getString(3);
					 
				}rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(fact.equals("")){
				
				out.println("");
				
			}else{
				out.println("Se encontraron las siguientes facturas asociadas a este No. "+Adm+" de Admision y a esta Entidad "+NombreEnt+": ");
				out.println(""+fact+"");
				out.println("�Desea asignarle a estas facturas el mismo numero de autorizaci�n?");
			}
			  
		}
		
		if(va.equals("GuardarAutoFact")){
			String noauto=req.getParameter("noauto");
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
			java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());
			System.out.println("noauto "+noauto);
			rs=mina.BuscarEnca(Adm, Ent);
			try {
				while(rs.next()){
					mina.ActualizarFact(rs.getString(1),noauto);
					mina.GuardarHistorico2(Adm,user,Ent,Fecha,Hora,rs.getString(1),noauto);
					
			
				}rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		if(va.equals("GuardarAutoF")){
			String noauto=req.getParameter("noauto");
			String enca=req.getParameter("enca");
			System.out.println("noauto "+noauto);
			int sw=0;
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
			java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());
			mina.ActualizarFact(enca,noauto);
			rs=mina.BuscarDatosEnca(enca);
			try {
				while(rs.next()){
				mina.GuardarHistorico2(rs.getString(1), user, rs.getString(2), Fecha, Hora, rs.getString(3), noauto);
				sw=1;
				}rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			if(sw==0){
				out.println("Ups ocurrio un error !!");
			}else{
				out.println("Numero de Autorizaci�n Actualizado !!!!");
			}
		}
		
		
	}

}
