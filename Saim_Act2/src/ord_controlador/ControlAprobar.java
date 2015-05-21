package ord_controlador;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ord_metodo.MetodoAprobar;
import java.sql.ResultSet;
import java.sql.SQLException;



public class ControlAprobar extends HttpServlet{
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String va = req.getParameter("valor");
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out;
		out=res.getWriter();
		MetodoAprobar ma=new MetodoAprobar();
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		ResultSet rs3=null;
		ResultSet rs4=null;
		ResultSet rsp=null;
		ResultSet rsl=null;
		ResultSet rsa=null;
		String user=req.getParameter("user");
		
		if(va.equals("ListaPendxA")){
		/***Muestra las Ordenes de Compra pendiente por aprobar **/
		int i=1;
		out.println("<table width='100%'>");
		out.println("<tr class='contpre' align='center' bgcolor='#E6ECF2'><td></td><td>No. Orden</td><td>Fecha de Emision</td><td>Fecha de Entrega</td><td>Proveedor</td><td>Concepto</td><td>Valor Total</td><td>Usuario</td><td colspan='4'></td></tr>");
		rs=ma.BusOrdxAprob();
			try {
				while(rs.next()){
					out.println("<tr class='rep' align='center'><td>"+i+"</td><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(6)+"</td><td>"+rs.getString(5)+"</td><td>"+FormatMoneda(rs.getString(7))+"</td>");
					rs1=ma.BuscarUser(rs.getString(4));
					if(rs1.next()){
					out.println("<td>"+rs1.getString(1)+"</td><td><a href='#' onclick='VerO("+rs.getString(8)+")' >Ver</a></td><td><a href='#' onclick='AprobO("+rs.getString(8)+")'>Aprobar </a></td><td><a href='#' onclick='RechO("+rs.getString(8)+")' >Rechazar</a></td><td><a href='#' onclick='ModiO("+rs.getString(8)+")'>Modificar</a></td></tr>");
					}rs1.getStatement().getConnection().close();
					i=i+1;
				}rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.println("<tr><td colspan='12' align='center'><div id='vistaprob'></div></td></tr>");
			out.println("</table>");
		
		}
		
		if(va.equals("VDetO")){
			/***Vista del detalle de la orden de compra en ventana emergente ****/
			String codoc=req.getParameter("codigo");//Codigo de la orden de compra
			out.println("<table width='100%'>");
			rs=ma.BuscarDetalleOc(codoc);
			try {
				if(rs.next()){
					out.println("<tr class='contpre'>");
						out.println("<td>");
						out.println("<b>No. Orden:</b>");
						out.println("</td>");
						
						out.println("<td>");
						out.println(rs.getString(17));//Consecutivo Orden
						out.println("</td>");
						
						out.println("<td>");
						out.println("<b>Proveedor:</b>");
						out.println("</td>");
						
						out.println("<td>");
						out.println(rs.getString(7));//Nombre Prove
						out.println("</td>"); 
						
						out.println("<td>");
						out.println("<b>Telefono:</b>");
						out.println("</td>");
						
						out.println("<td>");
						out.println(rs.getString(8));//Telefono Prove
						out.println("</td>");
						
						out.println("<td>");
						out.println("<b>Forma de Pago:</b>");
						out.println("</td>");
						
						out.println("<td>");
						out.println(rs.getString(18));//Forma de Pago
						out.println("</td>");
						
						out.println("<td>");
						out.println("<b>Fecha de Entrega:</b>");
						out.println("</td>");
						
						out.println("<td>");
						out.println(rs.getString(5));//Fecha de Entrega 
						out.println("</td>");
						
						
						
					out.println("</tr>");
					out.println("<tr class='contpre' >");
						
						out.println("<td>");
						out.println("<b>Sucursal:</b>");
						out.println("</td>");
						rs1=ma.BusSuc(rs.getString(2));
						if(rs1.next()){
							out.println("<td>");
							out.println(rs1.getString(2));//Sucursal
							out.println("</td>");
						}rs1.getStatement().getConnection().close();
						
						out.println("<td>");
						out.println("<b>C. Costo:</b>");
						out.println("</td>");
						
						rs1=ma.BusCentro(rs.getString(1));
						
						if(rs1.next()){
							out.println("<td>");
							out.println(rs1.getString(2));//centro
							out.println("</td>");
						}rs1.getStatement().getConnection().close();
						
						out.println("<td>");
						out.println("<b>Subc. Costo:</b>");
						out.println("</td>");
						
						rs1=ma.BusSubcen(rs.getString(3));
						if(rs1.next()){
							out.println("<td colspan='2'>");
							out.println(rs1.getString(2));//subcentro
							out.println("</td>");
						}rs1.getStatement().getConnection().close();
						
						out.println("<td>");
						out.println("<b>Concepto:</b>");
						out.println("</td>");
						
						out.println("<td colspan='2'>");
						out.println(rs.getString(4));//Concepto
						out.println("</td>");
						
					out.println("</tr>");
				
					
					int i=1;
					rs2=ma.BuscarDetalleOc(codoc);
					out.println("<tr ><td colspan='10'>");
						out.println("<table width='100%' border=1 cellspacing='0' >");
							out.println("<tr class='contpre' bgcolor='#E6ECF2' align='center'><td></td><td>Cod. Ref.</td><td>Nombre</td><td>Cant</td><td>Precio Unitario</td><td>Descuento</td><td>IVA</td><td>Total</td></tr>");
							while(rs2.next()){
								out.println("<tr class='rep' ><td>"+i+"</td><td>"+rs2.getString(9)+"</td><td>"+rs2.getString(10)+"</td><td>"+rs2.getString(11)+"</td><td>"+FormatMoneda(rs2.getString(13))+"</td><td>"+rs2.getString(12)+"</td><td>"+rs2.getString(14)+"</td><td>"+FormatMoneda(rs2.getString(16))+"</td></tr>");
								i=i+1;
							}rs2.getStatement().getConnection().close();
							out.println("<tr><td colspan='7' align='right' class='rep'> Valor Total:</td><td>"+FormatMoneda(rs.getString(19))+"</td></tr>");
						out.println("</table>");
					out.println("</td>");
					out.println("</tr>");
					
				}rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			out.println("</table>");
		}
		
		
		if(va.equals("Aprob1")){
			String codoc=req.getParameter("codigo");
			rs=ma.BusOC(codoc);
			out.println("<table width='100%'>");
			try {
				if(rs.next()){
					out.println("<tr><td><br></td></tr>");
				out.println("<tr class='contpre' ><td>OBSERVACION APROBACION ORDEN No."+rs.getString(10)+" Proveedor: "+rs.getString(18)+" Valor Total: "+FormatMoneda(rs.getString(16))+" </td></tr>");
				out.println("<tr><td><textarea id='oap' cols='80' rows='4' class='rep'></textarea></td></tr>");
				out.println("<tr><td><input type='button' value='Aceptar' onclick='GAprob("+codoc+")'</td></tr>");
				}rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.println("</table>");
		}
		
		
		
		if(va.equals("Gaprob")){
			String codoc=req.getParameter("codigo");
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
			java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());
			String concepto=req.getParameter("obs");
			try {
				ma.AprobOrden(codoc,Fecha,Hora,user,concepto);
				out.println("0");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				out.println("1");
				e.printStackTrace();
			}
		}
		
		if(va.equals("Rech1")){
			
			String codoc=req.getParameter("codigo");
			rs=ma.BusOC(codoc);
			out.println("<table width='100%'>");
			try {
				if(rs.next()){
					out.println("<tr><td><br></td></tr>");
				out.println("<tr class='contpre'><td>OBSERVACION DE RECHAZO ORDEN No."+rs.getString(10)+" Proveedor: "+rs.getString(18)+" Valor Total: "+FormatMoneda(rs.getString(16))+" </td></tr>");
				out.println("<tr><td><textarea id='orec' cols='80' rows='4' class='rep'></textarea></td></tr>");
				out.println("<tr><td><input type='button' value='Aceptar' onclick='GRech("+codoc+")'</td></tr>");
				}rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.println("</table>");
			
		}
		
		if(va.equals("Grech")){
			String codoc=req.getParameter("codigo");
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
			java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());
			String concepto=req.getParameter("obs");
			try {
				ma.RechOrden(codoc,Fecha,Hora,user,concepto);
				out.println("0");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				out.println("1");
				e.printStackTrace();
			}
		}
		
		if(va.equals("ModOC")){
			String codoc=req.getParameter("codigo");
			rs=ma.BuscarDetalleOc(codoc);
			out.println("<table width='100%'>");
			out.println("<tr bgcolor='#DBE2E9' align='center'><td>ENCABEZADO</td></tr>");
			out.println("<tr><td><br></td></tr>");
			try {
				if(rs.next()){
					out.println("<tr class='contpre'><td>");
						out.println("<table width='100%'>");
						out.println("<td>");
						out.println("<b>No. Orden:</b>");
						out.println("</td>");
						
						out.println("<td>");
						out.println(rs.getString(17));//Consecutivo Orden
						out.println("</td>");
						
						out.println("<td>");
						out.println("<b>Proveedor:</b>");
						out.println("</td>");
						
						out.println("<td>");
						out.println(rs.getString(7));//Nombre Prove
						out.println("</td>"); 
						
						out.println("<td>");
						out.println("<b>Telefono:</b>");
						out.println("</td>");
						
						out.println("<td>");
						out.println(rs.getString(8));//Telefono Prove
						out.println("</td>");
						
						out.println("<td>");
						out.println("<b>Forma de Pago:</b>");
						out.println("</td>");
						
						out.println("<td>");
						out.println(rs.getString(18));//Forma de Pago
						out.println("</td>");
						
						out.println("<td>");
						out.println("<b>Fecha de Entrega:</b>");
						out.println("</td>");
						
						out.println("<td>");
						out.println(rs.getString(5));//Fecha de Entrega 
						out.println("</td>");
						
						
						
					out.println("</tr>");
					out.println("<tr class='contpre' >");
						
						out.println("<td>");
						out.println("<b>Sucursal:</b>");
						out.println("</td>");
						rs1=ma.BusSuc(rs.getString(2));
						if(rs1.next()){
							out.println("<td>");
							out.println(rs1.getString(2));//Sucursal
							out.println("</td>");
						}rs1.getStatement().getConnection().close();
						
						out.println("<td>");
						out.println("<b>C. Costo:</b>");
						out.println("</td>");
						
						rs1=ma.BusCentro(rs.getString(1));
						
						if(rs1.next()){
							out.println("<td>");
							out.println(rs1.getString(2));//centro
							out.println("</td>");
						}rs1.getStatement().getConnection().close();
						
						out.println("<td>");
						out.println("<b>Subc. Costo:</b>");
						out.println("</td>");
						
						rs1=ma.BusSubcen(rs.getString(3));
						if(rs1.next()){
							out.println("<td colspan='2'>");
							out.println(rs1.getString(2));//subcentro
							out.println("</td>");
						}rs1.getStatement().getConnection().close();
						
						out.println("<td>");
						out.println("<b>Concepto:</b>");
						out.println("</td>");
						
						out.println("<td colspan='2'>");
						out.println(rs.getString(4));//Concepto
						out.println("</td>");
						
						out.println("</tr>");
						out.println("</table>");
					out.println("</td></tr>");
					out.println("<tr><td><br></td></tr>");
					out.println("<tr bgcolor='#DBE2E9' align='center'><td>DETALLE</td></tr>");
					out.println("<tr>");
					out.println("<td>");
						out.println("<table border=1 cellspacing='0' width='100%'>");
							out.println("<tr class='contpre' bgcolor='#E6ECF2' align='center'><td></td><td>Cod. Ref.</td><td>Nombre</td><td>Cant</td><td>Precio Unitario</td><td>Descuento</td><td>IVA</td><td>Total</td><td>Eliminar</td></tr>");
							rs2=ma.BuscarDetalleOc(codoc);
							int i=1;
							while(rs2.next()){
								out.println("<tr class='rep' align='center' ><td>"+i+"</td><td>"+rs2.getString(9)+"</td><td>"+rs2.getString(10)+"</td><td>"+rs2.getString(11)+"</td><td>"+FormatMoneda(rs2.getString(13))+"</td><td>"+rs2.getString(12)+"</td><td>"+rs2.getString(14)+"</td><td>"+FormatMoneda(rs2.getString(16))+"</td><td><input type='radio' name='radiobutton' id='elim"+i+"' onclick='ElimO("+codoc+","+rs.getString(20)+")' /></td></tr>");
								i=i+1;
							}rs2.getStatement().getConnection().close();
							out.println("</table>");
					out.println("</td>");
					out.println("</tr>");
					out.println("<tr><td><br></td></tr>");
					out.println("<tr>");
					out.println("<td>");
						out.println("<table>");
							out.println("<tr bgcolor='#BADBFB' class='rep' align='center'><td width='15%'>Cod Ref.</td><td width='40%'>Descripcion</td><td width='5%'>Cantidad</td><td width='10%'>Precio Unitario</td><td width='5%'>Descuento % </td><td width='5%'>Subtotal</td><td width='5%'>IVA</td><td width='15%'>Valor Total</td><td></td></tr>");
							out.println("<tr class='rep' ><td><input type='text' onkeyup='BuscarProducto("+rs.getString(21)+",1)' class='rep' id='codref'  size='15' style='width:100%'/></td>" +
									"<td><input type='text' class='rep' onkeyup='BuscarProducto("+rs.getString(21)+",2)' class='rep' id='descrip' size='80' style='width:100%'/></td><td><input type='text' class='rep' id='cant' size='5' style='width:100%' onkeyup='checknu()' onblur='CalcularTotal(1)' /></td>" +
									"<td><input type='text' class='rep'size='8' id='punit' style='width:100%' disabled/></td>"+
									"<td><input type='text' class='rep' size='5' id='desc' style='width:100%' value='0' onblur='CalcularTotal(2)' /></td>" +
									"<td><input type='text' class='rep' size='8' id='subtotal' style='width:100%' disabled/></td>"+
									"<td><input type='text' class='rep' onkeyup='BuscarIva()' size='5' id='iva' style='width:100%' /></td>" +
									"<td><input type='text' class='rep' size='15' id='ptotal' style='width:100%' disabled/></td><td><input type='button' id='Oa' value='+' onclick='AdProd("+codoc+","+rs.getString(21)+")' title='Agregar' /></td></tr>");
							
							out.println("<tr><td><div id='vcodref'></div><input type='hidden' id='codprodu' /><input type='hidden' id='codtarifa' /></td>"+
										"<td><div id='vproduct' ></div></td><td colspan='4'> </td><td colspan='2'><div id='vistiva' ></div><input type='hidden' id='codiiva' /></td>");
							out.println("</tr>");
						out.println("</table>");
					out.println("</td>");
					out.println("</tr>");
				}rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.println("</table>");
		
		}
		
		
		if(va.equals("Adprod")){
			String codoc=req.getParameter("codigo");
			String codprove=req.getParameter("codprove");
			String codprod=req.getParameter("codprod");
			String codref=req.getParameter("codref");
			String nombre=req.getParameter("nombre");
			String cant=req.getParameter("cant");
			String punit=req.getParameter("punit");
			String subtotal=req.getParameter("subtotal");
			String iva=req.getParameter("iva");
			String ptotal=req.getParameter("ptotal");
			String codiiva=req.getParameter("codiiva");
			String codtarifa=req.getParameter("codtarifa");
			String desc=req.getParameter("desc");
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
			java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());
			
			try {
				ma.AdicionarProducto(codoc,user,codprove,codprod,codref,nombre,cant,punit,subtotal,iva,ptotal,codiiva,codtarifa,desc,Fecha,Hora);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		if(va.equals("ElimO")){
			String codoc=req.getParameter("codigo");
			String coddet=req.getParameter("coddet");
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
			java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());
			String concep=req.getParameter("concept");
			try {
				ma.EliminarDetOC(codoc,coddet,Fecha,Hora,user,concep);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
	}

	
	
	
	
	
	/***********************************************/
	public String FormatMoneda(String valor){		
		String temp2="";String temp1="";
		int ud=1;int logCad = valor.length();		
		for (int i=logCad-1;i>=0;i--){			
			temp2=valor.substring(i,i+1);
			//ystem.out.println("temp2 -"+temp2);
			
			//System.out.println("temp1 -"+temp1);
			//System.out.println("TEMP22 -"+temp2);
			if((temp1.equals("."))||(temp1.equals("0"))){
				//System.out.println("eNTRANDO!!!"+temp1);
			}
			if(temp2.equals(".")){
				ud=0;
				temp2+=temp1;
				temp1=temp2;
			}else{
				temp2+=temp1;
				if(ud==3){
					if(i!=0){
						temp1="."+temp2;
						}else{
						temp1=temp2;
						}ud=0;
				}else{
					temp1=temp2;
					}
			}
				ud++;
		}
		temp1="$ "+temp1;
		return temp1;
	}
	
	
	
	
	
	/**************************************/
}
