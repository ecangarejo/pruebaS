package cont_controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cont_metodo.MetodoReportesFact;

public class ControlReportesFact extends  HttpServlet{
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String va = req.getParameter("valor");
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out;
		out=res.getWriter();
		MetodoReportesFact au=new MetodoReportesFact();
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		ResultSet rs3=null;
		ResultSet rs4=null;
		ResultSet rsp=null;
		ResultSet rsl=null;
		ResultSet rsa=null;
		
		
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
			String tnota=req.getParameter("tnota");
			//int vali=0;
			rs1=au.empresa();
			String nombre="";
			try {
				if(rs1.next()){
					nombre=rs1.getString(2);
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String Namereport="";
			rs1=au.BuscarTipoNota(tnota);
			try {
				if(rs1.next()){
					Namereport=rs1.getString(2);
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(Namereport.equals("")){
				Namereport=" NOTA CREDITO, GLOSAS Y GLOSAS CONCURRENTE ";
			}
			rs=au.reportenc(Fechai, Fechaf,ftent,tnota);
			long ValorNota=0;
			long ValorFact=0;
			System.out.println("valor de tnota"+tnota);
			
			
			out.println("<table width='80%' align='center'>"); 
			out.println("<tr class='TituloRep'  font='bold' ><td ><br><br> REPORTE DE "+Namereport+" "+nombre+" - Informe comprendido del  "+Fechai+" al "+Fechaf+"   <br><br></td></tr>");
			out.println("<tr><td><table border=1 >");
					out.println("<tr class='TituloRep' bgcolor='#97C2E9' align='center' font='bold' border='1' ><td>No.</td><td><br>Empresa </td><td><br>Fecha de Inserci�n</td><td><br>Usuario</td><td><br>No. Factura </td><td><br>Fecha Emision</td><td>Valor Factura</td><td><br>Valor Nota</td><td><br>Obervacion</td><td><br>Descripcion</td></tr> ");
					
					try {
						while(rs.next()){
							out.println("<tr class='rep'><td>"+rs.getString(1)+"</td>");
							out.println("<td>"+rs.getString(2)+"</td>");
							out.println("<td>"+rs.getString(3)+"</td>");
							out.println("<td>"+rs.getString(4)+"</td>");
							out.println("<td>"+rs.getString(5)+"</td>");
							out.println("<td>"+rs.getString("fecha_factura")+"</td>");
							out.println("<td>"+formatMoneda(rs.getInt(9)+"")+"</td>");
							out.println("<td >"+formatMoneda(rs.getInt(6)+"")+"</td>");
							ValorNota=ValorNota+rs.getLong(6);
							out.println("<td align='left'>"+rs.getString(7)+"</td>");
							out.println("<td>"+rs.getString(8)+"</td>");
							
							
							//System.out.println("ValorFact="+ValorFact+" Factura="+rs.getString(5)+" valor factura= "+rs.getString(9));
							out.println("</tr>");
						}
						rs.getStatement().getConnection().close();
				
						rs=au.reportencconsolidado(Fechai,Fechaf,ftent,tnota);
						while(rs.next()){
							ValorFact=ValorFact+rs.getLong(9);
						}
						rs.getStatement().getConnection().close();
				
						
					
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				//	out.println("</table>");
					out.println("</td></tr></table>");
					out.println("<tr class='TituloRep' ><td>VALOR TOTAL DE "+Namereport+" : "+formatMoneda(ValorNota+"")+" VALOR TOTAL DE FACTURAS : "+formatMoneda(ValorFact+"")+"</td></tr>");
					out.println("</table>");
			
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
			rs1=au.empresa();
			String nombre="";
			try {
				if(rs1.next()){
					nombre=rs1.getString(2);
				}rs1.getStatement().getConnection().close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			rs=au.reportefact(Fechai, Fechaf,ftent);
			int estado; 
			long valorfact=0;
			out.println("<table width='80%' >");
			out.println("<tr class='TituloRep' ><td ><br><br> REPORTE DE FACTURAS "+nombre+" - Informe comprendido del  "+Fechai+" al "+Fechaf+"   <br><br></td></tr>");
			//out.println("<p class='TituloRep'></p>");
			out.println("<tr><td><table border=1>");
					out.println("<tr class='TituloRep'  bgcolor='#97C2E9' align='center' font='bold' border='1' ><td><br>Consecutivo </td><td><br>Razon Social</td><td><br>Fecha de emision</td><td><br>valor</td><td><br>Valor Factura</td><td><br>Valor Usuario</td><td><br>Valor Total</td><td><br>Estado</td><td><br>Fecha de ingreso</td><td><br>Fecha de Egreso</td><td><br>Documento </td><td><br>Nombre Paciente </td><td><br> Facturado Por</td></tr> ");
					
					try {
						while(rs.next()){
							out.println("<tr class='rep' ><td>"+rs.getString(1)+"</td>");
							out.println("<td align='left'>"+rs.getString(2)+"</td>");
							
							estado=rs.getInt(8);
							if(estado==5){
								out.println("<td >"+rs.getString(3)+"</td>");
								out.println("<td>"+formatMoneda("0")+"</td>");
								out.println("<td>"+formatMoneda("0")+"</td>");
								out.println("<td>"+formatMoneda("0")+"</td>");
								out.println("<td>"+formatMoneda("0")+"</td>");
								out.println("<td>ANULADA</td>");
							}else{
								out.println("<td >"+rs.getString(3)+"</td>");
								out.println("<td>"+formatMoneda(rs.getString(15))+"</td>");
								out.println("<td>"+formatMoneda(rs.getString(15))+"</td>");
								long ValorFact=rs.getLong(15)-rs.getLong("valorUsuario");
								out.println("<td>"+formatMoneda(rs.getLong("valorUsuario")+"")+"</td>");
								out.println("<td>"+formatMoneda(ValorFact+"")+"</td>");
								out.println("<td>ACTIVA</td>");
								valorfact=valorfact+rs.getLong(7);
							}
							//out.println("<td>"+rs.getString(8)+"</td>");
							out.println("<td>"+rs.getString(9)+"</td>");
							out.println("<td>"+rs.getString(10)+"</td>");
							out.println("<td align='left'>"+rs.getString("documento")+"</td>");
							out.println("<td align='left'>"+rs.getString(11)+"</td>");
							out.println("<td align='left'>"+rs.getString(12)+"</td></tr>");
						}
						rs.getStatement().getConnection().close();
						
					
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				//	out.println("</table>");
					out.println("</td></tr></table>" );
					
					String vfact=Long.toString(valorfact);
					out.println("<tr class='TituloRep' ><td > Valor Total Facturado Activas : "+formatMoneda(vfact)+"</td></tr>");
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
			System.out.println("Valor de ttp "+ttp);
			String Codanul;
			int vali=0;
			if(ttp.equals("FACTURAS")){
				vali=1;
			}else{
				if(ttp.equals("RECIBOS DE CAJA")){
					vali=2;
				}else{
					if(ttp.equals("NOTAS CREDITOS")){
						vali=3;
					}
				}
			}
			rs1=au.empresa();
			String nombre="";
			try {
				if(rs1.next()){
					nombre=rs1.getString(2);
				}rs1.getStatement().getConnection().close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//System.out.println(ftent);
			if(vali==1){
			rs=au.reportefactanu(Fechai,Fechaf,ftent);
			
			out.println("<table width='80%' >");
			out.println("<tr class='TituloRep'><td><br><br> REPORTE DE "+ttp+" ANULADAS "+nombre+" - Informe del "+Fechai+" al "+Fechaf+"<br><br></td>");
			out.println("<tr><td><table border=1>");
					out.println("<tr  class='TituloRep'  bgcolor='#97C2E9' align='center' font='bold'><td><br>Consecutivo </td><td><br>Razon Social</td><td><br>fecha de emision</td><td><br>valor</td><td><br>Valor Factura</td><td><br>Valor Usuario</td><td><br>Valor Total</td><td><br>Estado</td><td><br>Nombre Paciente </td><td><br>Factura Generada Por</td><td>Anulada Por:</td></tr> ");
					long valorfact=0;
					try {
						while(rs.next()){
							out.println("<tr  class='rep' ><td>"+rs.getString(1)+"</td>");
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
							valorfact=valorfact+rs.getLong(7);
							rs1=au.BuscarNombreUsuario(Codanul);
							while(rs1.next()){
								out.println("<td>"+rs1.getString(1)+"</td></tr>");
							}rs1.getStatement().getConnection().close();
						}
						rs.getStatement().getConnection().close();
						
					
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				//	out.println("</table>");
					out.println("</table>");
			out.println("</td></tr>");
			String vfact=Long.toString(valorfact);
			out.println("<tr class='TituloRep' ><td> Valor Total en Facturas Anuladas : "+formatMoneda(vfact)+"</td></tr>");
			out.println("</table>");
			
			}else{
				if (vali==2){
					rsp=au.reportercAnu(Fechai,Fechaf,ftent);
					out.println("<table width='80%' >");
					out.println("<tr class='TituloRep'><td><br><br> REPORTE DE "+ttp+" ANULADAS "+nombre+" - Informe del "+Fechai+" al "+Fechaf+"<br><br></td>");
					out.println("<tr><td><table border=1>");
							out.println("<tr class='TituloRep'  bgcolor='#97C2E9' align='center' font='bold' ><br><td><br>Cod RC </td><td><br>Empresa </td><td><br>Concepto</td><td><br>Usuario</td><td><br>Fecha de Insercion</td><td><br>Fecha de Pago</td><td><br>Observacion</td><td><br>Valor Total RC</td></tr> ");
							
							try {
								while(rsp.next()){
									out.println("<tr class='rep'><td>"+rsp.getString(1)+"</td>");
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
					out.println("</td></tr></table>");
				}else{
					if(vali==3){
						rs1=au.reportencAnu(Fechai,Fechaf,ftent);
						long ValorNota=0;
						long ValorFact=0;
						//out.println("Valor de fent -->"+ftent);
						out.println("<table width='80%' >");
						out.println("<tr class='TituloRep'><td><br><br> REPORTE DE "+ttp+" ANULADAS "+nombre+" - Informe del "+Fechai+" al "+Fechaf+"<br><br></td>");
						out.println("<tr><td><table border=1>");
								out.println("<tr class='TituloRep'  bgcolor='#97C2E9' align='center' font='bold'><td>No.</td><td><br>Empresa </td><td><br>Fecha de Inserci�n</td><td><br>Usuario</td><td><br>No. Factura </td><td><br>Valor Nota</td><td><br>Obervacion</td><td><br>Descripcion</td><td>Valor Factura</td></tr> ");
								try {
									while(rs1.next()){
										out.println("<tr class='rep'><td>"+rs1.getString(1)+"</td>");
										out.println("<td>"+rs1.getString(2)+"</td>");
										out.println("<td>"+rs1.getString(3)+"</td>");
										out.println("<td>"+rs1.getString(4)+"</td>");
										out.println("<td>"+rs1.getString(5)+"</td>");
										out.println("<td>"+formatMoneda(rs1.getInt(6)+"")+"</td>");
										ValorNota=ValorNota+rs1.getLong(6);
										out.println("<td>"+rs1.getString(7)+"</td>");
										out.println("<td>"+rs1.getString(8)+"</td>");
										out.println("<td>"+formatMoneda(rs1.getInt(9)+"")+"</td>");
										//System.out.println("ValorFact="+ValorFact+" Factura="+rs.getString(5)+" valor factura= "+rs.getString(9));
										out.println("</tr>");
									}
									rs1.getStatement().getConnection().close();
									rs=au.reportencAnuConsolidado(Fechai, Fechaf, ftent);
									while(rs.next()){
										ValorFact=ValorFact+rs.getLong(9);
									}rs.getStatement().getConnection().close();
									
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							out.println("</table>");
					out.println("<tr  class='TituloRep' ><td>Valor Total de Nota "+formatMoneda(ValorNota+""));
					out.println(" Valor Total de Facturas"+formatMoneda(ValorFact+""));
					out.println("</td></tr>");
					out.println("</td></tr></table>");
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
					}rs1.getStatement().getConnection().close();
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
			String tipo=req.getParameter("tipo");
			String Fechai=tanos+"-"+tmeses+"-"+tdia;
			String Fechaf=ftanos+"-"+ftmeses+"-"+ftdia; 
			rs=au.reportefactrad(Fechai, Fechaf,ftent,tipo);
			rs1=au.empresa();
			String nombre="";
			try {
				if(rs1.next()){
					nombre=rs1.getString(2);
				}rs1.getStatement().getConnection().close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//int estado;
			String dtipo="";
			if(tipo.equals("40")){
				dtipo="RADICADAS Y RADICADAS INTERNAMENTE";
			}else{
				if(tipo.equals("1")){
					dtipo="RADICADAS ";
				}else{
					if(tipo.equals("2")){
						dtipo="RADICADAS INTERNAMENTE";
					}
				}
			}
			out.println("<table width='80%' >");
			out.println("<tr class='TituloRep' ><td><br><br> REPORTE DE FACTURAS "+dtipo+" "+nombre+" - Informe del "+Fechai+" al "+Fechaf+"<br><br></td>");
			out.println("<tr><td><table border=1>");
			out.println("<tr class='rep' bgcolor='#97C2E9' align='center' font='bold'><td><br>Consecutivo Radicacion</td><td><br>Nombre de Entidad</td><td><br>Consecutivo</td><td><br>Usuario</td><td><br>Paciente</td><td><br>Documento</td><td><br>Fecha de ingreso</td><td><br>Fecha de Egreso</td><td><br>Fecha de Emision</td><td><br>Fecha de Envio</td><td><br>Fecha de Insercion En Sistema</td><td><br> Fecha de Radicacion</td><td><br>Valor Factura </td><td><br><br>Valor NC</tr> ");
			long Valornc=0;
			long Valorfact=0;
			try {
				while(rs.next()){
					out.println("<tr class='rep'><td>");
					if(rs.getString(21).equals("1")){
						out.println("RAD0"+rs.getString(1));
					}else{
						out.println("RADI0"+rs.getString(1));
					}
					out.println("</td>");
					out.println("<td>"+rs.getString(16)+"</td>");
					out.println("<td>"+rs.getString(2)+"</td>");						
					out.println("<td>"+rs.getString(5)+" "+rs.getString(6)+"</td>");
					out.println("<td>"+rs.getString(7)+" "+rs.getString(8)+" "+rs.getString(9)+"</td>");
					out.println("<td>"+rs.getString(10)+" "+rs.getString(11)+"</td>");
					out.println("<td>"+rs.getString(13)+"</td>");
					out.println("<td>"+rs.getString(14)+"</td>");
					out.println("<td>"+rs.getString(19)+"</td>");
					out.println("<td>"+rs.getString(4)+"</td>");
					out.println("<td>"+rs.getString(12)+"</td>");
					out.println("<td>"+rs.getString(18)+"</td>");					
					out.println("<td>"+formatMoneda(rs.getString(15))+"</td>");
					out.println("<td>"+formatMoneda(rs.getString(20))+"</td></tr>");
					Valornc=Valornc+rs.getLong(20);
							
				}
				rs.getStatement().getConnection().close();
				
			
				if(tipo.equals("40")){
				rs=au.reportefactradConsolidado(Fechai, Fechaf,ftent,"1");
					while(rs.next()){
						Valorfact=Valorfact+rs.getLong(2);
					}rs.getStatement().getConnection().close();
				
					rs=au.reportefactradConsolidado(Fechai, Fechaf,ftent,"1");
					while(rs.next()){
						Valorfact=Valorfact+rs.getLong(2);
					}rs.getStatement().getConnection().close();
				}else{
					if(tipo.equals("1")){
						rs=au.reportefactradConsolidado(Fechai, Fechaf,ftent,"1");
						while(rs.next()){
							Valorfact=Valorfact+rs.getLong(2);
						}rs.getStatement().getConnection().close();
					}else{
						rs=au.reportefactradConsolidado(Fechai, Fechaf,ftent,"1");
						while(rs.next()){
							Valorfact=Valorfact+rs.getLong(2);
						}rs.getStatement().getConnection().close();
					}
				}
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//	out.println("</table>");
			out.println("</table>");
			
			out.println("</tr></td>");
			long TotalRadicado=Valorfact-Valornc;
			out.println("<tr class='TituloRep' ><td>Valor en Notas Credito = "+formatMoneda(Valornc+"")+" Valor en Factura = "+formatMoneda(Valorfact+"")+" Total Radicado ="+formatMoneda(TotalRadicado+"")+"</td></tr>");
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
			out.println("<p class=contpre><br><br><b>REPORTE DE RECIBOS DE CAJA - Informe del "+Fechai+" al "+Fechaf+"</b><br><br></p>");
			out.println("<tr><br><b><td><br>Cod RC </td><td>Empresa </td><td>Concepto</td><td><br>Usuario</td><td><br>Fecha de Insercion</td><td><br>Fecha de Pago</td><td>Observacion</td><td><br>Valor Total RC</td></tr> ");
			
			try {
				while(rs.next()){
					out.println("<tr class='rep'><td>"+rs.getString(1)+"</td>");
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
