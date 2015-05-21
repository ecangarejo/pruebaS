package seguridad_controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import seguridad_logica.MetodoOpcionesAutorizadas;
/**
 * Servlet implementation class PermisosPagina
 */
public class PermisosPagina extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String va=request.getParameter("va");
		String pa=request.getParameter("pa");
		String codusu=request.getParameter("codusu");
		ResultSet rs=null;
		MetodoOpcionesAutorizadas au=new MetodoOpcionesAutorizadas();
		rs=au.PermisosPaginas(codusu,pa);
		String codi="";
		if(va.equals("1")){
		try {
			if(rs.next()){
			codi=rs.getString(1);		
			}
			rs.getStatement().getConnection().close();  
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.sendRedirect(pa+"?va=1&codigo="+codi+"");

		}
	
		
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String va=req.getParameter("valor");
		String Identificacion=req.getParameter("Identificacion");
		String CodigoUsuario=req.getParameter("CodigoUsuario");
		String CodigoFormato=req.getParameter("CodigoFormato");
		String CodigoAsignacion=req.getParameter("CodigoAsignacion");
		String CodigoPermiso=req.getParameter("CodigoPermiso");
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out=res.getWriter();
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rsp=null;
		ResultSet rsl=null;
		ResultSet rsa=null;
		MetodoOpcionesAutorizadas au=new MetodoOpcionesAutorizadas();
		
		int stop=0;


		if(va.equals("RepIndicadores")){
			String tdia=req.getParameter("tdia");
			String tmes=req.getParameter("tmeses");
			String tano=req.getParameter("tanos");
			String ftdia=req.getParameter("ftdia");
			String ftmes=req.getParameter("ftmeses");
			String ftano=req.getParameter("ftanos");
			String ftent=req.getParameter("ftent");
			String Fechai=tano+"-"+tmes+"-"+tdia;
			String Fechaf=ftano+"-"+ftmes+"-"+ftdia;
			System.out.println("Ent"+ftent);
			
			out.println("<br><br><table class='contpre' width='100%'>");
			out.println("<tr align='center'><td colspan='4' id='cabecera2' class='style11'  ><br><b>DATOS ESTADISTICOS DEMOGRAFICOS DE LA POBLACION <br></b></td>");
			if(ftent.equals("todas")){
			out.println("<tr align='center'><td><br>Rango : "+Fechai+" AL "+Fechaf+"</td><td align='rigth'><br> TODAS LAS ENTIDADES</td></tr>");
			}else{
				rs=au.BuscarNombEnt(ftent);
				try {
					while(rs.next()){
					out.println("<tr align='center'><td><br>Rango : "+Fechai+" AL "+Fechaf+"</td><td align='rigth'><br> ENTIDAD: "+rs.getString(1)+"</td></tr>");
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
					
			
				 rs=au.BuscarMasculino(Fechai,Fechaf,ftent);
				 rs1=au.BuscarFemenino(Fechai,Fechaf,ftent);
			out.println("<tr width='100%'><td><br><br>" +
					"<table  width='100%'>" +
					"<tr align='left'><td colspan='16' bgcolor='#E3E3E5'><u><b>Sexo :</u></td></tr>" +
					"<tr border='1'><td class='ind' colspan='4'><br> Femenino &nbsp;</td>");
			try {
				if(rs1.next()){
					out.println("<td class='ind' ><font color='black'><br><b>&nbsp; "+rs1.getString(1)+" </></font></td>");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			out.println("<td class='ind'><br>&nbsp; Masculino &nbsp;</td>");
			try {
				if(rs.first()){
					out.println("<td class='ind'><font color='black'><br><b>&nbsp;"+rs.getString(1)+"</></font></td>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			out.println("</tr></table></td></tr>");
			out.println("<tr align='left'><td colspan='12' bgcolor='#E3E3E5'><br><b><u>Grupo Etáreo : </><br></td></tr>");
			int Ed1=0;
			int Ed2=1;
			
			out.println("<tr align='left'><td><i>Rango</i></td><td align='center'><i>No. Pacientes</i></td></tr>");
			int cont=0;
			while(cont!=7){
				rs=au.BuscarEdades(Ed1,Ed2,Fechai,Fechaf, ftent);
				System.out.println("cont "+cont);
				System.out.println("Buscar Edades: Ed1"+Ed1+" Ed2 "+Ed2+"Fechai "+Fechai+"Fechaf "+Fechaf+" ftent"+ftent);
				if(Ed2<66){
				try {
					if(rs.next()){
					out.println("<tr align='left' class='ind'><td> De "+Ed1+" a "+Ed2+" Años </td><td align='center'><font color='black'>"+rs.getString(1)+"</font></td></tr>");
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Ed1=Ed2+1;
				if(cont==0){
					Ed2=Ed2+4;
					System.out.println("val 0 "+Ed2);
				}else{
					if(cont==1){
						Ed2=Ed2+9;
						System.out.println("val 1 "+Ed2);
					}else{
						if(cont==2){
							Ed2=Ed2+16;
							System.out.println("val 2 "+Ed2);
						}else{
							if(cont==3){
								Ed2=Ed2+20;
								System.out.println("val 3 "+Ed2);
							}else{
								if(cont==4){
								Ed2=Ed2+15;
								System.out.println("val 4 "+Ed2);
								}else{
									if(cont==5){
										Ed2=Ed2+1;
									}
								}
							}
						}
					}
				}
				}else{
					Ed1=66;
					Ed2=200;
					rs=au.BuscarEdades(Ed1, Ed2, Fechai, Fechaf, ftent);
					try {
						if(rs.next()){
						out.println("<tr align='left' class='ind'><td>Mayor a "+Ed1+" Años </td><td align='center'><font color='black'>"+rs.getString(1)+"</font></td></tr>");
						}
						rs.getStatement().getConnection().close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				cont=cont+1;
				
			}
			out.println("<tr align='left'><td colspan='12' bgcolor='#E3E3E5'><br><u>Patologia mas Frecuente </u><br></td>");
			rs=au.BuscarPatologia(Fechai,Fechaf,ftent);
			out.println("<tr><td align='left'><br><i>Nombre</i></td><td align='center'><br><i>No. Pacientes</i></td></tr>");
		
			try {
				while(rs.next()){
					out.println("<tr><td align='left' class='ind'>"+rs.getString(1)+"</td><td><font color='black'>"+rs.getString(2)+"</font></td></tr>");				
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			if(ftent.equals("todas")){
				out.println("<tr align='left'><td colspan='12' bgcolor='#E3E3E5'><u><br>Entidades Mas Frecuentes </u><br></td>");
				rs=au.BuscarEntidades(Fechai,Fechaf);
				out.println("<tr align='left'><td><i><br>Nombre EPS</i></td><td align='center'><i><br>No. Pacientes</i></td></tr>");
				try {
					while (rs.next()){
						out.println("<tr align='left' class='ind'><td>"+rs.getString(2)+"</td><td align='center'><font color='black'>"+rs.getString(1)+"</font></td></tr>");
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			out.println("<tr align='left'><td colspan='12' bgcolor='#E3E3E5'><u><br>Traslados a los Diferentes Servicios Derivados de la Urgencia </u><br></td>");
			rs=au.BuscarTraslados(Fechai,Fechaf,ftent);
			out.println("<tr align='left'><td><i>Area</i></td><td><i>Nombre Eps</i></td><td align='center'><i>No.Pacientes</i></td></tr>");
			try {
				while(rs.next()){
					out.println("<tr align='left' class='ind'><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td align='center'><font color='black'>"+rs.getString(3)+"</font></td></tr>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
				out.println("</table>");
		}
		
		
		if(va.equals("RepInd")){
			String tdia=req.getParameter("tdia");
			String tmes=req.getParameter("tmeses");
			String tano=req.getParameter("tanos");
			String ftdia=req.getParameter("ftdia");
			String ftmes=req.getParameter("ftmeses");
			String ftano=req.getParameter("ftanos");
			String ftent=req.getParameter("ftent");
			String Fechai=tano+"-"+tmes+"-"+tdia;
			String Fechaf=ftano+"-"+ftmes+"-"+ftdia;
			System.out.println("Ent "+ftent);
			
			out.println("<br><br><table class='contpre' width='100%'>");
			out.println("<tr align='center'><td colspan='4' id='cabecera2' class='style11'  ><br><b>Indicadores de Urgencia <br></b></td>");
			out.println("<tr>");
			out.println("<td><br><table width='100%' border='1'><tr><td>Sumatoria Min. Transcurridos entre la Admision del Paciente y su Atencion por el Medico</td><td>Total Pacientes Atendidos Urgencia</td><td bgcolor='#E3E3E5'>OPORTUNIDAD EN LA ATENCION EN CONSULTA DE URGENCIAS</td>");
			rs=au.BuscarMinHC(Fechai,Fechaf,ftent);
			int Minutos=0;
			int cont=0;
			float IndOportunidad;
			try {
				while(rs.next()){
					Minutos=Minutos+(rs.getInt(5));
					cont=cont+1;
				}
				if(Minutos!=0){
					System.out.println(" MINUTOS:"+Minutos);
					System.out.println("cont:"+cont);
					out.println("<tr><td><br>"+Minutos+"</td>");
				}else{
					out.println("<tr><td>No Existen registros para los parametros dados</td></tr>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			rs1=au.BuscarPacUrgen(Fechai, Fechaf, ftent);
			int PacUrg=0;
			try {
				while(rs1.next()){
					out.println("<td rowspan='3' align='center'><br>"+rs1.getInt(1)+"</td>");
					PacUrg=rs1.getInt(1);
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if((Minutos!=0)&&(cont!=0)){
				
					IndOportunidad=(float) Minutos/PacUrg;
					String val = IndOportunidad+"";  
					BigDecimal big = new BigDecimal(val);  
					big = big.setScale(2, RoundingMode.HALF_UP);  

					out.println("<td><br> "+big+"</td>");
			}
				out.println("</tr>");
				out.println("<tr><td><br>No. de Fallecidos en la Urgencia</td>");
				
				out.println("<td bgcolor='#E3E3E5'><br>TASA DE MORTALIDAD EN URGENCIAS</td></tr>");
				float IndTasaMorta;
				out.println("<tr>");
				rs=au.BuscarFalleUrg(Fechai, Fechaf, ftent);
				int FalleUrg=0;
				try {
					while(rs.next()){
						FalleUrg=rs.getInt(1);
						out.println("<td>"+FalleUrg+"</td>"); 
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(FalleUrg!=0){
					IndTasaMorta=(float) FalleUrg/PacUrg;
					String val = IndTasaMorta+"";  
					BigDecimal big = new BigDecimal(val);  
					big = big.setScale(4, RoundingMode.HALF_UP);  

					out.println("<td><br> "+big+"</td>");
				}
						out.println("</tr>");
						/*out.println("<tr><td>Paciente que reingresan al servicio de urgencia antes de 72 Horas</td>");
						out.println("<td>Total Egresos Vivos del Servicio de Urgencia</td>");
						out.println("<td bgcolor='#E3E3E5'><br>TASA DE REINGRESOS <72 HORAS</td></tr>");
						rs=au.BuscarPacIngreRepetido(Fechai, Fechaf,ftent);
						String CodPac;
						try {
							while(rs.next()){
								CodPac=rs.getString(2);
								rs1=au.BuscarAdmPac(CodPac, Fechai, Fechaf,ftent);
								if(rs1.first()){
									out.println("<tr><td>");
									System.out.println(rs1.getString(3));
								}
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}*/
						out.println("</table>");
		}
		
		if(va.equals("Alerta2")){
			String pend=req.getParameter("pend");
			rs=au.BuscarDatosForPend(CodigoUsuario);
			
			try {
				
				out.println("<table class=style13 align=center ><tr frame=hsides bordercolor=#215B8B><b><td>NOMBRE FORMATO</td><td>NOMBRE PACIENTE</td><td> ACCION </td></b></tr><br>");
				while (rs.next()) {
					String CodPac=rs.getString(3);
					String CodAdm=rs.getString(4);
					String CodFor=rs.getString(5);
					String Fecha=rs.getString(6);
					String Hora=rs.getString(7);
					String CodForPac=rs.getString(8);
					out.println("<tr>");
					out.println("<td id=pfn>"+rs.getObject("nombre_formato")+"</td>");
					out.println("<td id=pfs>"+rs.getObject("Nombre_Paciente")+"</td>");
					out.println("<td><table><tr><td><input type=radio name=accion value=1 onclick=ValRadioButtom("+CodigoUsuario+","+CodPac+","+CodAdm+","+CodFor+",&quot;"+Hora+"&quot;,&quot;"+Fecha+"&quot;,"+CodForPac+","+pend+") > preliminar </td><td>  <input type=radio name=accion value=2 onclick=ValRadioButtom("+CodigoUsuario+","+CodPac+","+CodAdm+","+CodFor+",&quot;"+Hora+"&quot;,&quot;"+Fecha+"&quot;,"+CodForPac+","+pend+")>guardar</td><td>  <input type=radio name=accion value=3 onclick=ValRadioButtom("+CodigoUsuario+","+CodPac+","+CodAdm+","+CodFor+",&quot;"+Hora+"&quot;,&quot;"+Fecha+"&quot;,"+CodForPac+","+pend+")>eliminar</td></tr></table></td>");
					out.println("</tr>");
				}
				out.println("</table>");
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("Alerta")){
			int co=0;
			rs=au.BuscarEstadoFormato(CodigoUsuario);
			
			try {
				while(rs.next()){
					co=rs.getInt(1);
				}
				if(co>0){
						out.print("TIENE "+co+" FORMATO POR CERRAR, GUARDELO O ELIMINELO PARA UTILIZAR EL SISTEMA NORMALMENTE");
					}else{
						
						stop=1;
						out.print(1);
							
					}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
if(va.equals("Val24h")){
			
			rs=au.ListaUrgPac(CodigoUsuario);
			String fechar;
			String fecha;
			String hora;
			int val=0;
			int Cont=0;
			out.println("<font class=contpre><b><k> LISTA DE PACIENTES CON MAS DE 24H <k></b></p> ");
			out.println("<br><table class=contpre>");
			out.println("<tr align=center ><td><b><br>Tipo</b></td><td></td><td><b><br>Numero de Documento</b></td><td><b><br>Nombre del Paciente</b></td><td><b> <br>Horas </b></td> <td><b><br> Dias</b></td><td><br><input type=button value=Revisar onclick=VerMultiplesP("+CodigoUsuario+") /></td></tr>");
			try {
				
				while(rs.next()){
					//fechar=rs.getString(7)+""+rs.getString(8);
					//out.println("valor de fecha de registro"+rs.getString(7));
					//out.println("valor de hora de registro"+rs.getString(8));
					fecha=rs.getString(7);
					hora=rs.getString(8);
					//out.println("valor de fecha PRUEBA STRING"+fecha);
					//out.println("valor de HORA PRUEBA STRING"+hora);
					rs1=au.val24h(fecha,hora);
					if(rs1.next()){
						val=rs1.getInt(1);
					}
					
					if(val>24){
						out.println("<tr><td>"+rs.getString(5)+"</td>");
						out.println("<td> </td>");
						out.println("<td>"+rs.getString(6)+"</td>");
						out.println("<td>"+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+"</td>");
						out.println("<td>"+val+"</td>");
						out.println("<td>"+val/24+"</td>");
						out.println("<td><input name='chkPac' type='checkbox' id='chkPac' value='"+rs.getString(1)+"' /></td><tr>");
						Cont=Cont+1;
					}
					rs1.getStatement().getConnection().close();
					
				}
				rs.getStatement().getConnection().close();
				out.print("<input name='txtCont' type='hidden' id='txtCont' value='"+Cont+"' />");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			out.println("</table>");
		}
		
		if(va.equals("registro")){
			String CodUsuario=req.getParameter("CodigoUsuario");
			String pacientes=req.getParameter("cadena");
			String CodigoPaciente="";
			String valor = pacientes;
			String admision;
			String estado;
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
			java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());
			StringTokenizer tk = new StringTokenizer(valor, "|");
			while(tk.hasMoreElements()){
				CodigoPaciente=tk.nextToken();
				//System.out.println(CodigoPaciente);
				out.println("tk"+CodigoPaciente);
				//out.println("cadena"+pacientes);
				rs=au.validaradmi(CodigoPaciente);
				try {
					while(rs.next()){
						estado=rs.getString(1);
						if(estado.equals("0")){
							admision=rs.getString(2);
							au.guardareg24h(CodUsuario,admision,Fecha,Hora);
						}
					
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		if(va.equals("SaveFor")){
			String CodAdmision=req.getParameter("CodAdmision");
			String CodFormato=req.getParameter("CodFormato");
			String CodPac=req.getParameter("CodPac");
			String usuario=req.getParameter("usuario");
			String CodForPac=req.getParameter("CodForPac");
			rs=au.CambiarEstadoForm(CodAdmision,CodFormato, CodPac, usuario,CodForPac);
		}
		
		if(va.equals("EliminarFor")){
			String CodFor=req.getParameter("CodForPac");
			au.BorrarFor(CodFor); 
		}
		
		if(va.equals("OpenMen")){
			String CodUsuario=req.getParameter("CodUsuario");
			String CodRemi=req.getParameter("CodRemi");
			String CodMen=req.getParameter("CodMen");
			String contenido=null;
			String asunto=null;
			String fecha=null;
			String hora=null;
			rs=au.BuscarContenidoMensaje(CodUsuario, CodRemi, CodMen);
			try {
				out.println("<table width=50% class=styleMensaje align=left bordercolor=#F7F6BF><tr align=center bgcolor=#F7F6BF><td><br><b>VISTA DEL MENSAJE</b><br><br></td></tr>");
				while(rs.next()){
					asunto=rs.getString(2);
					contenido=rs.getString(3);
					fecha=rs.getString(4);
					hora=rs.getString(5);
					out.println("<tr bgcolor=#FAFADF><td>De :<b>"+rs.getString("NOMBREREMI")+"</b><br><br></td></tr>");
					out.println("<tr bgcolor=#FAFADF><td>Asunto :"+rs.getString(2)+"<br><br></td></tr>");
					out.println("<tr><td><div class=styleMen><table width=100%><tr bgcolor=#F7F6BF style='border-width: 2px;border: solid; border-color: #00FF00;'><td>"+rs.getString(3)+"</td></tr><table></div></td></tr>");
				}
				out.println("<tr align=right><td><input type=button  value=Responder class=boton5 onclick='respondermen("+CodRemi+","+CodMen+","+CodUsuario+",&quot;"+asunto+"&quot;,&quot;"+contenido+"&quot;,&quot;"+fecha+"&quot;,&quot;"+hora+"&quot;)'></input></td></tr>");
				out.println("</table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			rs1=au.CambiarEstadoMen(CodUsuario,CodRemi,CodMen);
			
		}
		
		if(va.equals("ResMen")){
			String CodUsuario=req.getParameter("CodUsuario");
			String CodRemi=req.getParameter("CodRemi");
			String CodMen=req.getParameter("CodMen");
			String contenido=req.getParameter("contenido");
			String asunto=req.getParameter("asunto");
			String fecha=req.getParameter("fecha");
			String hora=req.getParameter("hora");
			
			out.println("<form id=formu onsubmit=GuardarResp() >");
			out.println("<table bgcolor=#E4F0FE width=100% class=styleMensaje><tr><td><b>MENSAJE NUEVO</b><br><br></td></tr>");
			rs=au.BuscarNombreUsuario(CodUsuario);
			rs1=au.BuscarNombreUsuario(CodRemi);
			try {
				while(rs.next()){
				out.println("<tr><td>De :<b>"+rs.getString("nombreuser")+"</b><input type=hidden name=coduser  id=coduser value="+rs.getString("usu_codigo")+"></input></td></tr>");
				}
				while(rs1.next()){
					out.println("<tr><td>Para :<b>"+rs1.getString("nombreuser")+"</b><input type=hidden name=codremi  id=codremi value="+rs1.getString("usu_codigo")+"></td></tr>");
				}
				out.println("<tr><td><br>Asunto : <input type=text name=txtasunto id=txtasunto size=50 maxlength=100 value='RE: "+asunto+"'  /></td></tr>");
				out.println("<tr><td><br><br><textarea cols=50 rows=10 id=contmen name=contmen onkeyup='return maxiLong(this,500)'>"+contenido+"</textarea></td></tr>");
				out.println("<tr><td><input type=submit value=Enviar /></td></tr>");
				
				out.println("</table>");
				out.println("</form>");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			
			
		}
		
		
		
		if(va.equals("ListMen")){
			String CodUsuario=req.getParameter("CodigoUsuario");
			
			rs=au.ListarMensajes(CodigoUsuario);
			rs1=au.ListarMensajesLeidos(CodigoUsuario);
			
			try {
				out.println("<table>");
				out.println("<tr align=center ><td><div onmousemove=ValButtonChat("+CodUsuario+")><font color=white>prueba</font>");
				out.println("</div></td></tr>");
				out.println("<tr align=center bgcolor=#D7E6FF  class=styleTituloMensaje><td><b>BANDEJA DE ENTRADA </b></td></tr>");
				out.println("<tr><td><table><tr><td>Abrir</td><td>Borrar</td><td>|</td><b><td>Remitente</td><td>Asunto</b></td><td></td></tr></table></td></tr><tr><td><div class='styleMen'><table><tr><td>");
				while (rs.next()) {
					String CodRemi=rs.getString(4);
					//rs1=au.BuscarNombreUsuario(CodRemi);
					
					String CodMen=rs.getString(5);
					String horaenv=rs.getString(7);
					  out.println("<tr><td><table><tr>");
					  out.println("<td><table><tr><td><input type=radio name=Act value=1 onclick=ValButtonMen("+CodUsuario+","+CodRemi+","+CodMen+") /></td><td> <input type=radio name=Act value=2 onclick=ValButtonMen("+CodUsuario+","+CodRemi+","+CodMen+") /></td></tr></table></td>");
					  out.println("<td> | </td>");
					  out.println("<td colspan=4>"+rs.getObject("usuario")+"</td>");
					  out.println("<td><b><u>"+rs.getObject("asunto")+"</u></b></td>");
					  out.println("<td>"+rs.getObject("fechaenvio")+"</td>");
					  out.println("<td>"+rs.getObject("horaenvio")+"</td>");
					  out.println("</tr><td></table></tr>");
				}
				out.println("</td></tr>");
				out.println("</table></div></td></tr>");
				out.println("<tr><td><br><table><tr bgcolor=#EFF4FD align=center class=styleTituloMensaje><td><b>MENSAJES LEIDOS </b></td></tr>");
				out.println("<tr><td><table><tr><td>Abrir</td><td>Borrar</td><td>|</td><b><td>Remitente</td><td>Asunto</b></td><td></td></tr></table></td></tr><tr><td><div class='styleMen'><table><tr><td>");
				while (rs1.next()) {
					String CodRemi=rs1.getString(4);
					//rs1=au.BuscarNombreUsuario(CodRemi);
					
					String CodMen=rs1.getString(5);
					String horaenv=rs1.getString(7);
					  out.println("<tr><td><table><tr>");
					  out.println("<td><table><tr><td><input type=radio name=Act value=1 onclick=ValButtonMen("+CodUsuario+","+CodRemi+","+CodMen+") /></td><td> <input type=radio name=Act value=2 onclick=ValButtonMen("+CodUsuario+","+CodRemi+","+CodMen+") /></td></tr></table></td>");
					  out.println("<td> | </td>");
					  out.println("<td colspan=4>"+rs1.getObject("usuario")+"</td>");
					  out.println("<td><b><u>"+rs1.getObject("asunto")+"</u></b></td>");
					  out.println("<td>"+rs1.getObject("fechaenvio")+"</td>");
					  out.println("<td>"+rs1.getObject("horaenvio")+"</td>");
					  out.println("</tr><td></table></td></tr>");
				}
				out.println("</td></tr>");
				
				out.println("</table></div></td></tr></table>");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		if(va.equals("ListUsu")){ 
			String CodUsuario=req.getParameter("CodigoUsuario");
			String val=req.getParameter("val");
			rs=au.ListarUsuarios(CodigoUsuario);
			int vali=Integer.parseInt(val);
			
			try {
				if(vali==2){
				out.println("<table><tr align=center ><td><div onmousemove=prueba("+CodUsuario+")><font color=white>prueba</font>");
				out.println("</div></td></tr><table>");
				}
				if(vali==1){
					out.println("<div ><table>");
					out.println("<tr><td><hr><b>USUARIOS CONECTADOS</b><hr></td></tr>");
					while (rs.next()) {
							int cond=1;
							out.println("<tr>");
							String codremi=rs.getString(2);
							out.println("<td><input type=radio value=1 name=chat onclick='chat("+codremi+","+CodUsuario+")'>"+rs.getString("usuario")+"</a></td>");
							//out.println("<td>"+rs.getString("usuario")+"</td>");
							out.println("</tr>");
							
						}
					out.println("</table></div>");
					}
				
					
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
						// TODO Auto-generated catch block
							e.printStackTrace();
						}
				
				}
		
		if(va.equals("reportefac")){
			String tanos=req.getParameter("tanos");
			String tdia=req.getParameter("tdia");
			String tmeses=req.getParameter("tmeses");
			String ftanos=req.getParameter("ftanos");
			String ftdia=req.getParameter("ftdia");
			String ftmeses=req.getParameter("ftmeses");
			String ftent=req.getParameter("ftent");
			String Fechai=tanos+"-"+tmeses+"-"+tdia;
			String Fechaf=ftanos+"-"+ftmeses+"-"+ftdia; 
			rs=au.reportefact(Fechai, Fechaf,ftent);
			int estado; 
			out.println("<table border=1 class=contpre>");
			out.println("<p class=contpre><br><br><b>Informe del "+Fechai+" al "+Fechaf+"</b><br><br></p>");
			out.println("<tr><b><td><br>Consecutivo </td><td><br>Razon Social</td><td><br>Fecha de emision</td><td><br>valor</td><td><br>Valor Factura</td><td><br>Valor Usuario</td><td><br>Valor Total</td><td><br>Estado</td><td><br>Fecha de ingreso</td><td><br>Fecha de Egreso</td><td><br>Nombre Paciente </td><td><br> Facturado Por</td></b></tr> ");
			
			try {
				while(rs.next()){
					out.println("<tr><td>"+rs.getString(1)+"</td>");
					out.println("<td>"+rs.getString(2)+"</td>");
					
					estado=rs.getInt(8);
					if(estado==5){
						out.println("<td>"+rs.getString(3)+"</td>");
						out.println("<td>"+formatMoneda("0")+"</td>");
						out.println("<td>"+formatMoneda("0")+"</td>");
						out.println("<td>"+formatMoneda("0")+"</td>");
						out.println("<td>"+formatMoneda("0")+"</td>");
						out.println("<td>ANULADA</td>");
					}else{
						out.println("<td>"+rs.getString(3)+"</td>");
						out.println("<td>"+formatMoneda(rs.getString(4))+"</td>");
						out.println("<td>"+formatMoneda(rs.getString(5))+"</td>");
						out.println("<td>"+formatMoneda(rs.getString(6))+"</td>");
						out.println("<td>"+formatMoneda(rs.getString(7))+"</td>");
						out.println("<td>ACTIVA</td>");
					}
					//out.println("<td>"+rs.getString(8)+"</td>");
					out.println("<td>"+rs.getString(9)+"</td>");
					out.println("<td>"+rs.getString(10)+"</td>");
					out.println("<td>"+rs.getString(11)+"</td>");
					out.println("<td>"+rs.getString(12)+"</td></tr>");
				}
				rs.getStatement().getConnection().close();
				
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//	out.println("</table>");
			out.println("</table>");
		}
		

	if(va.equals("reportefacrad")){
			String tanos=req.getParameter("tanos");
			String tdia=req.getParameter("tdia");
			String tmeses=req.getParameter("tmeses");
			String ftanos=req.getParameter("ftanos");
			String ftdia=req.getParameter("ftdia");
			String ftmeses=req.getParameter("ftmeses");
			String ftent=req.getParameter("ftent");
			String Fechai=tanos+"-"+tmeses+"-"+tdia;
			String Fechaf=ftanos+"-"+ftmeses+"-"+ftdia; 
			rs=au.reportefactrad(Fechai, Fechaf,ftent);
			//int estado;
			out.println("<table border=1 class=contpre>");
			out.println("<p class=contpre><br><br><b>Informe del "+Fechai+" al "+Fechaf+"</b><br><br></p>");
			out.println("<tr><b><td><br>Cuenta de Cobro</td><td><br>Nombre de Entidad</td><td><br>Consecutivo</td><td><br>Usuario</td><td><br>Paciente</td><td><br>Documento</td><td><br>Fecha de ingreso</td><td><br>Fecha de Egreso</td><td><br>Fecha de Emision</td><td><br>Fecha de Envio</td><td><br>Fecha de Insercion En Sistema</td><td><br> Fecha de Radicacion</td><td><br>Valor Factura </td></b></tr> ");
			
			try {
				while(rs.next()){
					out.println("<tr><td>"+rs.getString(1)+"</td>");
					out.println("<td>"+rs.getString(12)+"</td>");
					out.println("<td>"+rs.getString(2)+"</td>");						
					out.println("<td>"+rs.getString(5)+"</td>");
					out.println("<td>"+rs.getString(6)+"</td>");
					out.println("<td>"+rs.getString(7)+"</td>");
					out.println("<td>"+rs.getString(9)+"</td>");
					out.println("<td>"+rs.getString(10)+"</td>");
					out.println("<td>"+rs.getString(15)+"</td>");
					out.println("<td>"+rs.getString(4)+"</td>");
					out.println("<td>"+rs.getString(8)+"</td>");
					out.println("<td>"+rs.getString(14)+"</td>");					
					out.println("<td>"+formatMoneda(rs.getString(11))+"</td></tr>");
							
				}
				rs.getStatement().getConnection().close();
				
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//	out.println("</table>");
			out.println("</table>");
		}

		if(va.equals("reportenc")){
			String tanos=req.getParameter("tanos");
			String tdia=req.getParameter("tdia");
			String tmeses=req.getParameter("tmeses");
			String ftanos=req.getParameter("ftanos");
			String ftdia=req.getParameter("ftdia");
			String ftmeses=req.getParameter("ftmeses");
			String Fechai=tanos+"-"+tmeses+"-"+tdia;
			String Fechaf=ftanos+"-"+ftmeses+"-"+ftdia; 
			String ftent=req.getParameter("ftent");
			//int vali=0;
			
			rs=au.reportenc(Fechai, Fechaf,ftent);
			long ValorNota=0;
			long ValorFact=0;
			//out.println("Valor de fent -->"+ftent);
			out.println("<table border=1 class=contpre>"); 
			out.println("<b>Informe del "+Fechai+" al "+Fechaf+" </b><br><br>");
			out.println("<tr><b><td>No.</td><td><br>Empresa </td><td><br>Fecha de Inserción</td><td><br>Usuario</td><td><br>No. Factura </td><td><br>Valor Nota</td><td><br>Obervacion</td><td><br>Descripcion</td><td>Valor Factura</td></b></tr> ");
			
			try {
				while(rs.next()){
					out.println("<tr><td>"+rs.getString(1)+"</td>");
					out.println("<td>"+rs.getString(2)+"</td>");
					out.println("<td>"+rs.getString(3)+"</td>");
					out.println("<td>"+rs.getString(4)+"</td>");
					out.println("<td>"+rs.getString(5)+"</td>");
					out.println("<td>"+formatMoneda(rs.getInt(6)+"")+"</td>");
					ValorNota=ValorNota+rs.getLong(6);
					out.println("<td>"+rs.getString(7)+"</td>");
					out.println("<td>"+rs.getString(8)+"</td>");
					out.println("<td>"+formatMoneda(rs.getInt(9)+"")+"</td>");
					ValorFact=ValorFact+rs.getLong(9);
					//System.out.println("ValorFact="+ValorFact+" Factura="+rs.getString(5)+" valor factura= "+rs.getString(9));
					out.println("</tr>");
				}
				rs.getStatement().getConnection().close();
				
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//	out.println("</table>");
			
			out.println("Valor Total de Nota "+formatMoneda(ValorNota+""));
			out.println("Valor Total de Facturas"+formatMoneda(ValorFact+""));
			out.println("</table>");
			
		}
		
		
		if(va.equals("reporterc")){
			String tanos=req.getParameter("tanos");
			String tdia=req.getParameter("tdia");
			String tmeses=req.getParameter("tmeses");
			String ftanos=req.getParameter("ftanos");
			String ftdia=req.getParameter("ftdia");
			String ftmeses=req.getParameter("ftmeses");
			String Fechai=tanos+"-"+tmeses+"-"+tdia;
			String Fechaf=ftanos+"-"+ftmeses+"-"+ftdia; 
			String ftent=req.getParameter("ftent");
			rs=au.reporterc(Fechai, Fechaf,ftent);
			
			out.println("<table border=1 class=contpre>");
			out.println("<p class=contpre><br><br><b>Informe del "+Fechai+" al "+Fechaf+"</b><br><br></p>");
			out.println("<tr><br><b><td><br>Cod RC </td><td>Empresa </td><td>Concepto</td><td><br>Usuario</td><td><br>Fecha de Insercion</td><td><br>Fecha de Pago</td><td>Observacion</td><td><br>Valor Total RC</td></tr> ");
			
			try {
				while(rs.next()){
					out.println("<tr><td>"+rs.getString(1)+"</td>");
					out.println("<td>"+rs.getString(2)+"</td>");
					out.println("<td>"+rs.getString(3)+"</td>");
					out.println("<td>"+rs.getString(4)+"</td>");
					out.println("<td>"+rs.getString(5)+"</td>");
					out.println("<td>"+rs.getString(6)+"</td>");
					out.println("<td>"+rs.getString(7)+"</td>");
					out.println("<td>"+formatMoneda(rs.getLong(8)+"")+"</td>");
					out.println("</tr>");
				}
				rs.getStatement().getConnection().close();
				
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//	out.println("</table>");
			out.println("</table>");
		}
		
		if(va.equals("reporteAnu")){
			String tanos=req.getParameter("tanos");
			String tdia=req.getParameter("tdia");
			String tmeses=req.getParameter("tmeses");
			String ftanos=req.getParameter("ftanos");
			String ftdia=req.getParameter("ftdia");
			String ftmeses=req.getParameter("ftmeses");
			String ttp=req.getParameter("ttp");
			String ftent=req.getParameter("ftent");
			String Fechai=tanos+"-"+tmeses+"-"+tdia;
			String Fechaf=ftanos+"-"+ftmeses+"-"+ftdia; 
			String Codanul;
			int vali=Integer.parseInt(ttp);
			//System.out.println(ftent);
			if(vali==1){
			rs=au.reportefactanu(Fechai,Fechaf,ftent);
			
			out.println("<table border=1 class=contpre>");
			out.println("<b>Informe del "+Fechai+" al "+Fechaf+"</b><br><br>");
			out.println("<tr><b><td><br>Consecutivo </td><td><br>Razon Social</td><td><br>fecha de emision</td><td><br>valor</td><td><br>Valor Factura</td><td><br>Valor Usuario</td><td><br>Valor Total</td><td><br>Estado</td><td><br>Nombre Paciente </td><td><br>Factura Generada Por</td><td>Anulada Por:</td</b></tr> ");
			
			try {
				while(rs.next()){
					out.println("<tr><td>"+rs.getString(1)+"</td>");
					out.println("<td>"+rs.getString(2)+"</td>");
					out.println("<td>"+rs.getString(3)+"</td>");
					out.println("<td>"+rs.getString(4)+"</td>");
					out.println("<td>"+rs.getString(5)+"</td>");
					out.println("<td>"+rs.getString(6)+"</td>");
					out.println("<td>"+rs.getString(7)+"</td>");
					out.println("<td>"+rs.getString(8)+"</td>");
					out.println("<td>"+rs.getString(9)+"</td>");
					out.println("<td>"+rs.getString(10)+"</td>");
					Codanul=rs.getString(11);
					//out.println("<td>"+Codanul+"</td>");
					
					rs1=au.BuscarNombreUsuario(Codanul);
					while(rs1.next()){
						out.println("<td>"+rs1.getString(1)+"</td></tr>");
					}
				}
				rs.getStatement().getConnection().close();
				
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//	out.println("</table>");
			out.println("</table>");
			
			}else{
				if (vali==2){
					rsp=au.reportercAnu(Fechai,Fechaf,ftent);
					out.println("<table border=1 class=contpre>");
					out.println("<p class=contpre><br><br><b>Informe del "+Fechai+" al "+Fechaf+"</b><br><br></p>");
					out.println("<tr><br><b><td><br>Cod RC </td><td>Empresa </td><td>Concepto</td><td><br>Usuario</td><td><br>Fecha de Insercion</td><td><br>Fecha de Pago</td><td>Observacion</td><td><br>Valor Total RC</td></tr> ");
					
					try {
						while(rsp.next()){
							out.println("<tr><td>"+rsp.getString(1)+"</td>");
							out.println("<td>"+rsp.getString(2)+"</td>");
							out.println("<td>"+rsp.getString(3)+"</td>");
							out.println("<td>"+rsp.getString(4)+"</td>");
							out.println("<td>"+rsp.getString(5)+"</td>");
							out.println("<td>"+rsp.getString(6)+"</td>");
							out.println("<td>"+rsp.getString(7)+"</td>");
							out.println("<td>"+rsp.getString(8)+"</td>");
							out.println("</tr>");
						}
						rsp.getStatement().getConnection().close();
						
					
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				//	out.println("</table>");
					out.println("</table>");
				}else{
					if(vali==3){
						rs1=au.reportencAnu(Fechai,Fechaf,ftent);
						long ValorNota=0;
						long ValorFact=0;
						//out.println("Valor de fent -->"+ftent);
						out.println("<table border=1 class=contpre>"); 
						out.println("<b>Informe del "+Fechai+" al "+Fechaf+" </b><br><br>");
						out.println("<tr><b><td>No.</td><td><br>Empresa </td><td><br>Fecha de Inserción</td><td><br>Usuario</td><td><br>No. Factura </td><td><br>Valor Nota</td><td><br>Obervacion</td><td><br>Descripcion</td><td>Valor Factura</td></b></tr> ");
						try {
							while(rs1.next()){
								out.println("<tr><td>"+rs1.getString(1)+"</td>");
								out.println("<td>"+rs1.getString(2)+"</td>");
								out.println("<td>"+rs1.getString(3)+"</td>");
								out.println("<td>"+rs1.getString(4)+"</td>");
								out.println("<td>"+rs1.getString(5)+"</td>");
								out.println("<td>"+formatMoneda(rs1.getInt(6)+"")+"</td>");
								ValorNota=ValorNota+rs1.getLong(6);
								out.println("<td>"+rs1.getString(7)+"</td>");
								out.println("<td>"+rs1.getString(8)+"</td>");
								out.println("<td>"+formatMoneda(rs1.getInt(9)+"")+"</td>");
								ValorFact=ValorFact+rs1.getLong(9);
								//System.out.println("ValorFact="+ValorFact+" Factura="+rs.getString(5)+" valor factura= "+rs.getString(9));
								out.println("</tr>");
							}
							rs1.getStatement().getConnection().close();
							
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					out.println("Valor Total de Nota "+formatMoneda(ValorNota+""));
					out.println("Valor Total de Facturas"+formatMoneda(ValorFact+""));
					out.println("</table>");
						//out.println("No se encuentra Disponible este Reporte Todavia");
						
					}else{
						out.println("no se encontro un valor valido para ttp");
						out.println("Valor ttp-->"+ttp);
					}
				}
			}
			//rs=au.reporterc(Fechai, Fechaf);
			
			
		}
		
		if(va.equals("reporteFactNC")){
			String tanos=req.getParameter("tanos");
			String tdia=req.getParameter("tdia");
			String tmeses=req.getParameter("tmeses");
			String ftanos=req.getParameter("ftanos");
			String ftdia=req.getParameter("ftdia");
			String ftmeses=req.getParameter("ftmeses");
			String ftent=req.getParameter("ftent");
			String estado;
			String Fechai=tanos+"-"+tmeses+"-"+tdia;
			String Fechaf=ftanos+"-"+ftmeses+"-"+ftdia; 
			rs=au.reportefactnc(Fechai, Fechaf,ftent); 
			
			out.println("<table border=1 class=contpre>");
			out.println("<b>Informe del "+Fechai+" al "+Fechaf+"</b><br><br>");
			out.println("<tr><b><td><br>Consecutivo Fact </td><td><br>Razon Social</td><td><br>Fecha Emision Factura</td><td><br>Valor Factura</td><td><br>Valor Usuario</td><td><br>Valor Total Factura</td><td><br>Valor de Nota</td><td><br>Valor Total Factura NC Aplicada</td><td><br>Observacion de NC</td><td>Estado de Factura</td><td><br>Nombre Paciente </td><td><br> Factura generada por: </td><td><br>NC generada por:</td></b></tr> ");
			
			try {
				while(rs.next()){
					out.println("<tr><td>"+rs.getString(1)+"</td>");
					out.println("<td>"+rs.getString(2)+"</td>");
					out.println("<td>"+rs.getString(3)+"</td>");
					out.println("<td>"+formatMoneda(rs.getInt(4)+"")+"</td>");
					out.println("<td>"+formatMoneda(rs.getInt(5)+"")+"</td>");
					out.println("<td>"+formatMoneda(rs.getInt(6)+"")+"</td>");
					out.println("<td>"+formatMoneda(rs.getInt(7)+"")+"</td>");
					out.println("<td>"+formatMoneda(rs.getInt(8)+"")+"</td>");
					out.println("<td>"+rs.getString(9)+"</td>");
					estado=rs.getString(10);
					if(estado.equals("5")){
					out.println("<td>ANULADA</td>");
					}else{
						out.println("<td>ACTIVA</td>");
					}
					out.println("<td>"+rs.getString(11)+"</td>");
					out.println("<td>"+rs.getString(12)+"</td>");
					rs1=au.BuscarNombreUsuario(rs.getString(13));
					if(rs1.next()){
					out.println("<td>"+rs1.getString(1)+"</td></tr>");
					}
				}
				rs.getStatement().getConnection().close();
				
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//	out.println("</table>");
			out.println("</table>");
		}
		
		if(va.equals("chat")){ //FALTA TERMINAR
			String codremi=req.getParameter("codremi");
			String CodUsuario=req.getParameter("CodUsuario");
			
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
			java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());
			rs=au.MostrarChatRemi(codremi,CodUsuario,Fecha);
			rs1=au.BuscarNombreUsuario(CodUsuario);
			rsp=au.BuscarNombreUsuario(codremi);
			rsl=au.MostrarChatEnviado(codremi,CodUsuario,Fecha);
			String NombreUser="";
			String NombreUser2="";
			
			try {
				
				while (rsp.next()){
					NombreUser2=rsp.getString("nombre");
				}
				rsp.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.println("<table><tr align=right><td><input type=button name=cerrar value=X onclick=cerrardialogo("+codremi+","+CodUsuario+") class='cerrar'/></td></tr>");
			out.println("<tr bgcolor=#D7E6FF align=center><td><b>CHAT CON "+NombreUser2+"</b></td></tr>");
			out.println("<tr><td><a onclick=LimpiarChat("+codremi+","+CodUsuario+") ><b>Limpiar Conversacion</b></a></td></tr>");
			out.println("<tr><td bgcolor=#EFF4FD>REMITENTE</a></td></tr><tr><td><div class='prueba'><table><tr><td >");
			try {
			
				while (rs.next()){
					out.println("<tr><td>"+NombreUser2+" : "+rs.getString("mensaje")+" ("+rs.getString("horaenvio")+") </td></tr>");
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.println("</table></div></td></tr>");
			
			out.println("<tr><td bgcolor=#EFF4FD>ENVIADO</td></tr><tr><td><div class='prueba'><table><tr><td>");
			try {
				
				while (rs1.next()){
					NombreUser=rs1.getString("nombre");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				
				while (rsl.next()){
					out.println("<tr><td>"+NombreUser+" : "+rsl.getString("mensaje")+" ("+rsl.getString("horaenvio")+") </td></tr>");
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.println("</table></div></td></tr>");
			
			out.println("<tr><td><input type=text size=40 id=txtmensaje name=txtmensaje /><input type=button onclick=EnviarDialogo("+CodUsuario+","+codremi+") value=Enviar  /></td></tr>");
			out.println("</table>");
			
			//out.println("<tr><td><iframe src=http://localhost:8080/Saim/servlet/seguridad_controlador.MostrarConv.java ></iframe>");
			//out.println("<tr><td><iframe src=http://localhost:8080/Saim/servlet/seguridad_controlador.AddConv.java ></iframe>");
			//out.println("<tr><td><input type=text size=40 id=txtmensaje name=txtmensaje /><input type=button onclick=AddConv("+CodUsuario+") value=Enviar  /></td></tr>");
			//au.GuardarMensaje(codremi,asunto,contmen, CodUsuario,estado,Fecha,Hora);
		
		}
		
		if(va.equals("GuardarDial")){
			String codremi=req.getParameter("codremi");
			String CodUsuario=req.getParameter("CodUsuario");
			String mensaje=req.getParameter("mensaje");
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
			java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());
			String estado="0"; 
			String orden1="0";
			String orden2="0";
			au.GuardarChat(codremi,CodUsuario,mensaje,estado,Fecha,Hora,orden1,orden2);
			
			rs=au.MostrarChatRemi(codremi,CodUsuario,Fecha);
			rs1=au.BuscarNombreUsuario(CodUsuario);
			rsp=au.BuscarNombreUsuario(codremi);
			rsl=au.MostrarChatEnviado(codremi,CodUsuario,Fecha);
			String NombreUser="";
			String NombreUser2="";
			
			try {
				
				while (rsp.next()){
					NombreUser2=rsp.getString("nombre");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.println("<table><tr align=right><td><input type=button name=cerrar value=X onclick=cerrardialogo("+codremi+","+CodUsuario+") class='cerrar'/></td></tr>");
			out.println("<tr bgcolor=#D7E6FF align=center><td><b>CHAT CON "+NombreUser2+"</b></td></tr>");
			out.println("<tr><td><a onclick=LimpiarChat("+codremi+","+CodUsuario+") ><b>Limpiar Conversacion</b></a></td></tr>");
			out.println("<tr><td bgcolor=#EFF4FD>REMITENTE</td></tr><tr><td><div class='prueba'><table><tr><td>");
			try {
			
				while (rs.next()){
					out.println("<tr><td>"+NombreUser2+" : "+rs.getString("mensaje")+" ("+rs.getString("horaenvio")+")</td></tr>");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.println("</table></div></td></tr>");
			
			out.println("<tr><td bgcolor=#EFF4FD>ENVIADO</td></tr><tr><td><div class='prueba'><table><tr><td>");
			try {
				
				while (rs1.next()){
					NombreUser=rs1.getString("nombre");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				
				while (rsl.next()){
					out.println("<tr><td>"+NombreUser+" : "+rsl.getString("mensaje")+" ("+rsl.getString("horaenvio")+")</td></tr>");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			out.println("</table></div></td></tr>");
			
			out.println("<tr><td><input type=text size=40 id=txtmensaje name=txtmensaje /><input type=button onclick=EnviarDialogo("+CodUsuario+","+codremi+") value=Enviar  /></td></tr>");
			out.println("</table>");
			
		}
		
		
		if(va.equals("cerrarchat")){
			out.println("");

		}
		
		if(va.equals("OpenChat")){
			
			//out.println("TIENE CHATS NUEVOS!");
			String CodUsuario=req.getParameter("CodUsuario");
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());	
			rs=au.BuscarNuevoChat(CodUsuario, Fecha);
			String codremi=null;
			String men;
			String Hora;
			String Nombre=null;
			Object a;
			Object b;
			Vector<String> vector=new Vector(20);
			int cont=0;
			try {
				out.println("<table>");
				while(rs.next()){
					codremi=rs.getString(1);
					men=rs.getString(2);
					Hora=rs.getString(3);
					cont=cont+1;
					//out.println("este es el codremi"+codremi);
					//out.println("este es el mensaje"+men);
					rs1=au.BuscarNombreUsuario(codremi);
					while(rs1.next()){
						Nombre=rs1.getString(1);
					}
					//vector.addElement(Nombre);
					//out.println("valor de a"+vector);
					//out.println("valor de b"+b);
					//out.println("este es el nombre"+Nombre);
				}
				out.println("</table>");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			/*for(int i=0;i>=cont;i++){
				//if(vector.elementAt(i)!=vector.elementAt(i-1)){
					out.println("<tr><td><input type=radio name=chat ></input>"+vector.elementAt(i)+":</td><tr>");
				//}
			}*/
			if(Nombre!=null){
			out.println("<table><tr><td>TIENE CHATS NUEVOS!</tr></td>");
			out.println("<tr><td><input type=radio name=chat onclick=chat("+codremi+","+CodUsuario+")></input>"+Nombre+"</td><tr>");
			}
			out.println("</table>");
		}
		
		if(va.equals("Hchat")){
			String codusuario=req.getParameter("CodUsuario");
			out.println("Seleccione la fecha para ver su Historial de Chat Recibidos");
			int dia=0;
			out.println("<select name=dia id=dia>");
			out.println("<option value=40>---</option>");
			for(int i=0;i<=30;i++){
				dia=dia+1;
				out.println("<option value="+dia+">"+dia+"</option>");
			}	
			out.println("</select>");
			out.println("<select name=mes id=mes>");
			out.println("<option value=40>---</option>");
			out.println("<option value=1>Enero</option>");
			out.println("<option value=2>Febrero</option>");
			out.println("<option value=3>Marzo</option>");
			out.println("<option value=4>Abril</option>");
			out.println("<option value=5>Mayo</option>");
			out.println("<option value=6>Junio</option>");
			out.println("<option value=7>Julio</option>");
			out.println("<option value=8>Agosto</option>");
			out.println("<option value=9>Septiembre</option>");
			out.println("<option value=10>Octubre</option>");
			out.println("<option value=11>Noviembre</option>");
			out.println("<option value=12>Diciembre</option>");	
			out.println("</select>");
			
			out.println("<select name=ano id=ano>");
			out.println("<option value=40>---</option>");
			int ano=2011;
			for(int j=0;j<=30;j++){
				ano=ano+1;
				out.println("<option value="+ano+">"+ano+"</option>");
			}
			out.println("</select>");
			out.println("<input type=button value=Buscar onclick=buscarchatrec('ano',"+codusuario+")></input>");
		}
		
		if(va.equals("OpenChatH")){
			String tanos=req.getParameter("tanos");
			String tdia=req.getParameter("tdia");
			String tmeses=req.getParameter("tmeses");
			String Fecha=tanos+"-"+tmeses+"-"+tdia;
			//out.println("Prueba de : "+Fecha);
			String codusuario=req.getParameter("codusuario");
			String CodRemiChat=null;
			String CodRemiChat2=null;
			rs=au.BuscarRemiHistChatRec(codusuario,Fecha);
			int cont=0;
			try {
				out.println("<table><tr><td>Chat De:</td></tr>");
				while(rs.next()){
					CodRemiChat=rs.getString(1);
					
					rs1=au.BuscarNombreUsuario(CodRemiChat);
					if(CodRemiChat!=null){
						while(rs1.next()){
						out.println("<tr><td><input type=radio onclick=OpenHistChat("+CodRemiChat+","+codusuario+","+tanos+","+tdia+","+tmeses+") />"+rs1.getString(1)+"</td></tr>");
					}
					
						
					
				}
				}
				
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(CodRemiChat==null){
			out.println("<tr><td>NO HAY REGISTROS PARA LA FECHA ESCOGIDA</tr></td>");
			}
			out.println("</table>");
		}
		
		if(va.equals("VistaChatHist")){
			
			String codusuario=req.getParameter("codusuario");
			String CodRemiChat=req.getParameter("CodRemiChat");
			String tanos=req.getParameter("tanos");
			String tdia=req.getParameter("tdia");
			String tmeses=req.getParameter("tmeses");
			String Fecha=tanos+"-"+tmeses+"-"+tdia;
			String NombreRemi=null;
			
			rs=au.BuscarHistChatRec(CodRemiChat, codusuario, Fecha);
			rs1=au.BuscarNombreUsuario(CodRemiChat);
			try {
				while(rs1.next()){
					NombreRemi=rs1.getString(1);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				out.println("<table><tr bgcolor=#D7E6FF><td>Chats Recibido de :"+NombreRemi+" en la Fecha: "+Fecha+"</tr></td>");
				while(rs.next()){
					
					out.println("<tr><td>"+rs.getString(2)+" ("+rs.getString(3)+")</td></tr>");
				}
				out.println("</table>");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		if(va.equals("LimpChat")){
			String codusuario=req.getParameter("CodUsuario");
			String codremi=req.getParameter("codremi");
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());	
			java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());
			int Val=1;
			int orden1=0;
			int orden2=0;
			int ordenremi=0;
			int ordenenv=0;
			int est=5;
			int codu=Integer.parseInt(codusuario);
			int codrem=Integer.parseInt(codremi);
			int codchat;
			rs=au.ConsultarOrden(codusuario, codremi, Fecha, Val, Hora);
			try {
				while(rs.next()){
					
					orden1=rs.getInt(1);
					orden2=rs.getInt(2);
					est=rs.getInt(3);
					codchat=rs.getInt(4);
					if((orden1==0)&&(est==0)){
						ordenremi=1;
					}else{
						if((orden1==codu)&&(est==1)){
							ordenremi=1;
						}else{
							if((orden1==0)&&(est==1)){
								ordenremi=1;
							}else{
								if((orden1!=0)&&(est==1)){
									if((orden2==0)&&(est==0)){
										ordenremi=2;
									}else{
										if((orden2==0)&&(est==1)){
											ordenremi=2;
										}
										
									}
								}
							}
						}
					}
					
					out.println("primera  vuelta <br />");
					out.println("valor orden 1 "+orden1+"<br />");
					out.println("valor ordenremi  "+ordenremi+"<br />");
					out.println("valor de codu 1 "+codu+"<br />");
					out.println("valor de estado 1 "+est+"<br />");
					out.println("valor orden 2 "+orden2+"<br />");
					
					au.LimpiarChat(codusuario, codremi, Fecha,ordenenv,ordenremi,Hora, codchat);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
				Val=2;
				rs1=au.ConsultarOrden(codusuario, codremi, Fecha, Val, Hora);
				try {
					orden1=0;
					orden2=0;
					est=5;
					while(rs1.next()){
						
						orden1=rs1.getInt(1);
						orden2=rs1.getInt(2);
						est=rs1.getInt(3);
						codchat=rs1.getInt(4);
						if((orden1==0)&&(est==0)){
							ordenenv=1;
						}else{
							if((orden1==codu)&&(est==1)){
								ordenenv=1;
							}else{
								if((orden1==0)&&(est==1)){
									ordenenv=1;
								}else{
									if((orden1!=0)&&(est==1)){
										if((orden2==0)&&(est==0)){
											ordenenv=2;
										}else{
											if((orden2==0)&&(est==1)){
												ordenenv=2;
											}
											
										}
									}
								}
							}
						}
						out.println("segunda vuelta <br />");
						out.println("valor orden 1 "+orden1+"<br />");
						out.println("valor ordenenv "+ordenenv+"<br />");
						out.println("valor de codu 1 "+codu+"<br />");
						out.println("valor de estado 1 "+est+"<br />");
						out.println("valor orden 2 "+orden2+"<br />");
						
						au.LimpiarChat(codusuario, codremi, Fecha,ordenenv,ordenremi,Hora, codchat);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			out.println("valor final ordenremi"+ordenremi);
			out.println("valor final ordenenv"+ordenenv);
			
			//out.println("<table><tr><td>coduser"+codusuario+"codremi!"+codremi+"fecha!"+Fecha+"</td></tr></table>");
			
		}
		
		if(va.equals("ElimMens")){
			String CodUsuario=req.getParameter("CodUsuario");
			String CodRemi=req.getParameter("CodRemi");
			String CodMen=req.getParameter("CodMen");
			rs=au.EliminarMens(CodUsuario, CodRemi, CodMen);
		}
		
		if(va.equals("NuevoMens")){
			String CodUsuario=req.getParameter("CodUsuario");
			out.println("<form id=formu onsubmit=GuardarMen() >");
			out.println("<table bgcolor=#E4F0FE width=100% class=styleMensaje><tr><td><b>MENSAJE NUEVO</b><br><br></td></tr>");
			rs=au.BuscarNombreUsuario(CodigoUsuario);
			try {
				while(rs.next()){
				out.println("<tr><td>De :<b>"+rs.getString("nombreuser")+"</b><input type=hidden name=coduser  id=coduser value="+rs.getString("usu_codigo")+"></input></td></tr>");
				}
				out.println("<tr><td>Para :<input type=text name=txtRemi id=txtRemi onkeyup=AutocompletarRemi() size=50></input><input type=hidden name=txtcodremi  id=txtcodremi></input><div id=ResultadoRemi></div></td></tr>");
				out.println("<tr><td><br>Asunto : <input type=text name=txtasunto id=txtasunto size=50 maxlength=100> </input></td></tr>");
				out.println("<tr><td><br><br><textarea cols=50 rows=10 id=contmen name=contmen onkeyup='return maxiLong(this,500)'> </textarea></td></tr>");
				out.println("<tr><td><input type=submit value=Enviar /></td></tr>");
				
				out.println("</table>");
				out.println("</form>");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		
		
		if(va.equals("AutoRemi")){
			String NomRemi=req.getParameter("NomRemi");
			rs=au.listarRemi(NomRemi);
			out.print(rs);
			int Cont=1;
			try {
				int p=0;
				
				out.println("<table>");
				while(rs.next())
				{	
					p=p+1;
					String NombreRemit=rs.getString(1)+" "+rs.getString(2);
					String CodRemi=rs.getString(3);
					out.println("<tr><td><input name='chkRemi' type='radio'  onclick='LlenarRemi("+CodRemi+","+Cont+")'/></td><td>"+NombreRemit+"</td></tr>");
					
				}
				out.println("</table>");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("LlenaRemi")){
			String CodRemi=req.getParameter("CodRemi");
			rs=au.LlenarRemi(CodRemi);
			try {
				while(rs.next()){
					out.print(rs.getString("Nombre_Remi"));
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace(); 
			}
		}
		
		if(va.equals("EnviaMen")){
			String codremi=req.getParameter("codremi");
			String asunto=req.getParameter("asunto");
			String contmen=req.getParameter("contmen");
			String CodUsuario=req.getParameter("codu");
			String estado="0";
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
			java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());
			au.GuardarMensaje(codremi,asunto,contmen,CodUsuario,estado,Fecha,Hora);
			out.print("codremi"+codremi+"asunto"+asunto+"contmen"+contmen+"CodUsuario"+CodUsuario+"estado"+estado+"Fecha"+Fecha+"Hora"+Hora);
		}
		
		
		if(va.equals("Imagen")){
			rs=au.BuscarUsuario(Identificacion);
			try {
				
				if(rs.next()){
					String Nombre=rs.getString(1)+" "+rs.getString(2) ;
					out.print("<table width='100%' border='0' class='style6'><tr><td width='20%'><div>NOMBRE DE USUARIO:</div></td>");
					out.print("<td colspan='2'><div>"+Nombre+"<input name='CodUsuario' type='hidden' id='CodUsuario' value="+rs.getString(4)+"></div></td>");
					out.print("<img name='ok' id='ok' src='C:\\Program Files\\Apache Software Foundation\\Tomcat 6.0\\webapps\\Imagenes-saim\\OSCAR.jpg'>");
					out.print("</tr><tr><td colspan='3'></td></tr><tr><td colspan='2'><input type='hidden' value='' name='idProducto'>Selecciona la Imagen: <input type='file' name='myimage' /><input type='submit'  name='Aceptar' value='Adjuntar' /> </td><td width='34%'><div align='center'></div></td></tr>");
				}
				else{
					out.print("Registro No Encontrado\nIntente Otra Vez.");
				}
				
				rs.getStatement().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		if(va.equals("Ingresar")){
			out.print("<table width='100%' border='1' class='style6'><tr><td><div align='center' class='style11' id='cabecera2'>Buscar Usuario </div></td></tr>");
			out.print("<tr><td><div align='center'>Identificacion:<input name='txtIdentificacion' type='text' id='txtIdentificacion' /><input name='btnBuscar' type='button' id='btnBuscar' value='Buscar' onclick='BuscarUsuario()'></div></td></tr>");
			out.print("<tr><td><div id='formatos'></div></td></tr></table>");
		}
		
		/*if(va.equals("Actualizar")){
			out.print("<table width='100%' border='1' class='style6'><tr><td><div align='center' class='style11' id='cabecera2'>Buscar Usuario </div></td></tr>");
			out.print("<tr><td><div align='center'>Identificacion:<input name='txtIdentificacion' type='text' id='txtIdentificacion' /><input name='btnBuscar' type='button' id='btnBuscar' value='Buscar'></div></td></tr>");
			out.print("<tr><td><div id='formatos'></div></td></tr></table>");
		}*/
		if(va.equals("Hosp")){
			int ContAutorizados=0;
			int ContDisponibles=0;
			try {
				rs=au.BuscarFormatosActivosHosp(CodigoUsuario);			
				rs1=au.BuscarFormatosDisponiblesHosp(CodigoUsuario);
				rsp=au.BuscarFormatosActivosHosp(CodigoUsuario);
				out.print("<table width='100%' class='style6' border='1' ><tr><td colspan='3'><div align='center' class='style11' id='cabecera2'>Formatos de Hospitalizacion </div></td></tr>");
				out.print("<tr><td><div align='center'>Opciones Disponibles</div></td><td width='50%'><div align='center'>Opciones Autorizadas</div></td></tr>");
				out.print("<tr><td width='50%'><table>");
				if(rsp.next()){
					while(rs1.next()){
						out.print("<tr><td><label><input name='chkDisponible' type='checkbox' value="+rs1.getString(1)+" id='chkDisponible' />"+rs1.getString(2)+"</label></td></tr>");
						ContDisponibles=ContDisponibles+1;
					}
					out.print("</table></td><td><table>");
					rs1.getStatement().getConnection().close();
				}
				else{
					rsl=au.TodosFormatos();
					while(rsl.next()){
						out.print("<tr><td><label><input name='chkDisponible' type='checkbox' value="+rsl.getString(1)+" id='chkDisponible' />"+rsl.getString(2)+"</label></td></tr>");
						ContDisponibles=ContDisponibles+1;
					}
					out.print("</table></td><td><table>");
					rsl.getStatement().getConnection().close();
				}
				rsp.getStatement().getConnection().close();				
				while(rs.next()){
					out.print("<tr><td><label><input name='chkAutorizadas' type='checkbox' value="+rs.getString(1)+" id='chkAutorizadas'  />"+rs.getString(2)+"</label></td></tr>");
					ContAutorizados=ContAutorizados+1;
				}
				out.print("</table></td></tr><tr><td><div align='center'><input title='Asignar' name='btnAsignar' type='button' id='btnAsignar' value='&gt;&gt;' onClick='GuardarRelacionFormatos()'><input name='txtConDisponibles' type='hidden' id='txtConDisponibles' value="+ContDisponibles+"></div></td>");
				out.print("<td><div align='center'><input title='Omitir' name='btnOmitir' type='button' id='btnOmitir' value='&lt;&lt;' onClick='OmitirPermisoFormato()'><input name='txtContAutorizadas' type='hidden' id='txtContAutorizadas' value="+ContAutorizados+"></div></td></tr></table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error en PermisosPagina>>valor=Hosp" +e);
				e.printStackTrace();
			}
			
		}
		if(va.equals("Cex")){
			int ContAutorizados=0;
			int ContDisponibles=0;
			try {
				rs=au.BuscarFormatosActivosCE(CodigoUsuario);			
				rs1=au.BuscarFormatosDisponiblesCE(CodigoUsuario);
				rsp=au.BuscarFormatosActivosCE(CodigoUsuario);
				out.print("<table width='100%' class='style6' border='1' ><tr><td colspan='3'><div align='center' class='style11' id='cabecera2'>Formatos de Consulta Externa </div></td></tr>");
				out.print("<tr><td><div align='center'>Opciones Disponibles</div></td><td width='50%'><div align='center'>Opciones Autorizadas</div></td></tr>");
				out.print("<tr><td width='50%'><table>");
				if(rsp.next()){
					while(rs1.next()){
						out.print("<tr><td><label><input name='chkDisponibleCE' type='checkbox' value="+rs1.getString(1)+" id='chkDisponibleCE' />"+rs1.getString(2)+"</label></td></tr>");
						ContDisponibles=ContDisponibles+1;
					}
					out.print("</table></td><td><table>");
					rs1.getStatement().getConnection().close();
				}
				else{
					rsl=au.TodosFormatos();
					while(rsl.next()){
						out.print("<tr><td><label><input name='chkDisponibleCE' type='checkbox' value="+rsl.getString(1)+" id='chkDisponibleCE' />"+rsl.getString(2)+"</label></td></tr>");
						ContDisponibles=ContDisponibles+1;
					}
					out.print("</table></td><td><table>");
					rsl.getStatement().getConnection().close();
				}
				rsp.getStatement().getConnection().close();				
				while(rs.next()){
					out.print("<tr><td><label><input name='chkAutorizadasCE' type='checkbox' value="+rs.getString(1)+" id='chkAutorizadasCE'  />"+rs.getString(2)+"</label></td></tr>");
					ContAutorizados=ContAutorizados+1;
				}
				out.print("</table></td></tr><tr><td><div align='center'><input title='Asignar' name='btnAsignar' type='button' id='btnAsignar' value='&gt;&gt;' onClick='GuardarRelacionFormatosCE()'><input name='txtConDisponiblesCE' type='hidden' id='txtConDisponiblesCE' value="+ContDisponibles+"></div></td>");
				out.print("<td><div align='center'><input title='Omitir' name='btnOmitir' type='button' id='btnOmitir' value='&lt;&lt;' onClick='OmitirPermisoFormatoCE()'><input name='txtContAutorizadasCE' type='hidden' id='txtContAutorizadasCE' value="+ContAutorizados+"></div></td></tr></table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error en PermisosPagina>>valor=Hosp" +e);
				e.printStackTrace();
			}
		}
		if(va.equals("Phic")){
			int ContAutorizados=0;
			int ContDisponibles=0;
			try {
				rs=au.BuscarPermisosActivosHC(CodigoUsuario);			
				rs1=au.BuscarPermisosDisponiblesHC(CodigoUsuario);
				rsp=au.BuscarPermisosActivosHC(CodigoUsuario);
				out.print("<table width='100%' class='style6' border='1' ><tr><td colspan='3'><div align='center' class='style11' id='cabecera2'>Item Historia Clinica </div></td></tr>");
				out.print("<tr><td><div align='center'>Opciones Disponibles</div></td><td width='50%'><div align='center'>Opciones Autorizadas</div></td></tr>");
				out.print("<tr><td width='50%'><table>");
				if(rsp.next()){
					while(rs1.next()){
						out.print("<tr><td><label><input name='chkDisponibleHC' type='checkbox' value="+rs1.getString(1)+" id='chkDisponibleHC' />"+rs1.getString(2)+"</label></td></tr>");
						ContDisponibles=ContDisponibles+1;
					}
					out.print("</table></td><td><table>");
					rs1.getStatement().getConnection().close();
				}
				else{
					rsl=au.TodosPermisosHC();
					while(rsl.next()){
						out.print("<tr><td><label><input name='chkDisponibleHC' type='checkbox' value="+rsl.getString(1)+" id='chkDisponibleHC' />"+rsl.getString(2)+"</label></td></tr>");
						ContDisponibles=ContDisponibles+1;
					}
					out.print("</table></td><td><table>");
					rsl.getStatement().getConnection().close();
				}
				rsp.getStatement().getConnection().close();				
				while(rs.next()){
					out.print("<tr><td><label><input name='chkAutorizadasHC' type='checkbox' value="+rs.getString(1)+" id='chkAutorizadasHC'  />"+rs.getString(2)+"</label></td></tr>");
					ContAutorizados=ContAutorizados+1;
				}
				out.print("</table></td></tr><tr><td><div align='center'><input title='Asignar' name='btnAsignar' type='button' id='btnAsignar' value='&gt;&gt;' onClick='GuardarRelacionPermisoHC()'><input name='txtConDisponiblesHC' type='hidden' id='txtConDisponiblesHC' value="+ContDisponibles+"></div></td>");
				out.print("<td><div align='center'><input title='Omitir' name='btnOmitir' type='button' id='btnOmitir' value='&lt;&lt;' onClick='OmitirPermisoHC()'><input name='txtContAutorizadasHC' type='hidden' id='txtContAutorizadasHC' value="+ContAutorizados+"></div></td></tr></table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error en PermisosPagina>>valor=Hosp" +e);
				e.printStackTrace();
			}
		}
		if(va.equals("Buscar")){
			//int contador=0;
			rs=au.BuscarUsuario(Identificacion);
			//rs1=au.TodosFormatos();		
			try {
				if(rs.next()){
					String Nombre=rs.getString(1)+" "+rs.getString(2) ;
					out.print("<table width='100%' border='1' class='style6'><tr><td width='20%'><div>NOMBRE DE USUARIO:</div></td>");
					out.print("<td colspan='2'><div>"+Nombre+"<input name='txtCodigoUsuario' type='hidden' id='txtCodigoUsuario' value="+rs.getString(3)+"></div></td>");
					out.print("</tr></table>");
					
					out.print("<table width='100%' class='style6'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Seleccione Una Opcion </div></td></tr>");
					out.print("<tr><td width='28%'><label><input name='radiobutton' type='radio' value='radiobutton' id='Hosp' onclick='Radio()' />Ingresar Permisos Formato Hospitalizacion</label></td>");
					out.print("<td width='36%'><label><input name='radiobutton' type='radio' value='radiobutton' id='Cex' onclick='Radio()' />Ingresar Permisos Formato Consulta Externa</label></td>");
					out.print("<td width='36%'><label><input name='radiobutton' type='radio' value='radiobutton' id='Phic' onclick='Radio()' />Permisos Historia Clinica</label></td></tr>");
					out.print("<tr><td colspan='4'><div id='Opcion'></div></td></tr></table>");
					out.print("");
				}
				else{
					out.print("No Se Encontro Registro.");
				}
				rs.getStatement().getConnection().close();
				
				/*while(rs1.next()){
					contador=contador+1;
					out.print("<tr><td colspan='2'><div>"+rs1.getString(2)+"</div></td><td><div align='center'><label><input name='chkvalidar' type='checkbox' id='ca' value='"+rs1.getString(1)+"' /></label></div></td></tr>");
				}
				out.print("<tr><td colspan='3'><div align='center'><input name='btnAsignar' type='button' id='btnAsignar' value='Asignar' onClick='GuardarRelacionFormatos()'><input name='txtContador' type='hidden' id='txtContador' value="+contador+"></div></td></tr></table>");
				rs1.getStatement().getConnection().close();*/
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		if(va.equals("Guardar")){
			au.insertarRelacionFormatos(CodigoUsuario, CodigoFormato);
			//out.print("Formatos Relacionados Con Exito");
		}
		
		if(va.equals("Omitir")){
			au.OmitirPermisoFormatoHosp(CodigoAsignacion);
		}
		
		if(va.equals("GuardarCE")){
			au.insertarRelacionFormatosCE(CodigoUsuario, CodigoFormato);
			//out.print("Formatos Relacionados Con Exito");
		}
		
		if(va.equals("OmitirCE")){
			au.OmitirPermisoFormatoCE(CodigoAsignacion);
		}
		
		if(va.equals("GuardarHC")){
			au.insertarRelacionPermisoHC(CodigoUsuario, CodigoPermiso);
			//out.print("Formatos Relacionados Con Exito");
		}
		
		if(va.equals("OmitirHC")){
			au.OmitirPermisoHC(CodigoAsignacion);
		}
		
		if(va.equals("Menus")){
			rs=au.BuscarPermisosMenu(CodigoUsuario);			
			try {
				out.print("<table width='100%' border='0'>");
				//out.print("<tr></tr>");
				int columna=0;
				while(rs.next()){
					out.print("<tr><td><a href='"+rs.getString(2)+"' >"+rs.getString(1)+"</a></td></tr>");
					columna++;
					/*if(columna==0){
						out.print("</tr>");
						columna=0;
					}*/
				}
				out.print("</table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public String formatMoneda(String valor){		
		String temp2="";String temp1="";
		int ud=1;int logCad = valor.length();		
		for (int i=logCad-1;i>=0;i--){			
			temp2=valor.substring(i,i+1);
			temp2+=temp1;
			if(ud==3){
				if(i!=0){temp1="."+temp2;}else{temp1=temp2;}ud=0;
			}else{temp1=temp2;}
			ud++;
		}
		temp1="$ "+temp1;
		return temp1;
	}
	

}
