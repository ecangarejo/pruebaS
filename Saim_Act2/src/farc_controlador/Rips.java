package farc_controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.util.*;
import java.text.*;
import java.util.StringTokenizer;  //para dividir por token |

import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.io.FileNotFoundException;
import java.io.PrintStream;


import farc_metodo.MetodoEntradas;

/**
 * Servlet implementation class Rips
 */
public class Rips extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		MetodoEntradas me = new MetodoEntradas();
		
		String va = request.getParameter("valor");
		String datos2 ="";
		String texto=request.getParameter("texto");
		String cont = request.getParameter("cont");
		String nmov = request.getParameter("nmov");
		String tmov = request.getParameter("tmov");
		String canul = request.getParameter("canul");
		String R = request.getParameter("R");
		String sw = request.getParameter("sw");
		String fechas = request.getParameter("fechas");
		String documento = request.getParameter("documento");
		//String M2[][] = request.getParameter("m2"); //= request.getParameter("m2");
			
		PrintWriter out=response.getWriter();

		java.util.Date fechaS = new Date();
		Calendar c = new GregorianCalendar();
		String dia = Integer.toString(c.get(Calendar.DATE));
		String mes = Integer.toString(c.get(Calendar.MONTH));
		String annio = Integer.toString(c.get(Calendar.YEAR));
		int m=Integer.parseInt(mes)+1;
		mes=String.valueOf(m);
		int d=Integer.parseInt(dia);
		if(d<10){dia="0"+d;}
		if(m<10){mes="0"+m;}
		
		if(cont==null){cont="0";}
		int conta = Integer.parseInt(cont);
		if (conta>9){conta=9;}
		
		datos2 = request.getParameter("datos2");
		if(datos2==null){datos2="";}
		if(conta==1){datos2="";}
		
	
		String M[][] = new String[11][11];
		String MI[][] = new String[11][11];
		String V[] = new String[150];
		
		String fecha=dia+"/"+mes+"/"+annio;
		//****************************************
		//Este es el llamado para la mascara de fechas
		//onKeyup='masca(this,patron,true)'
			
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		ResultSet rs3=null;
	
		
		String sFichero = "C:\\Clientes.txt";
		File fichero = new File(sFichero);
		
		if(va.equals("Ripss")){	
		
			try{
				  BufferedWriter bw = new BufferedWriter(new FileWriter(fichero));
			
			   	  // Escribimos 10 filas
				  for (int x=0;x<10;x++)
				 	bw.write("Fila numero " + x + ",mas decena"+x+10+"\n");

				  // Hay que cerrar el fichero
				  bw.close();
			/*	  
				  File f=new File("C:\\prueeee.txt");
					FileWriter fw= new FileWriter(f,false);
					fw.write("texto");
					fw.close();
					*/
				
					//Abrimos el archivo del cual extraeremos los datos
				//	BufferedReader in = new BufferedReader(new FileReader(“C:\\Clientes.txt”));
					//Creamos el archive destino
					//BufferedWriter outt = new BufferedWriter(new FileWriter("C:\\rrrrrrrrrr.txt"));
				//	outt.write("texto");
				//	outt.close();
				  
				} catch (IOException ioe){
				ioe.printStackTrace();
				}
			
				
				out.print("<table width='100%' class='style6'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Anulación de Entradas</div></td></tr>");
			/*out.print("<tr><td>Movimiento Número</td><td><label><input name='txtMov' type='text' id='txtMov' size='40'/><td>Fecha</td><td><label><input name='txtVence0' type='text' id='txtVence0' onKeyup='mascas(this,patron,true,"+dia+","+mes+","+annio+")' onBlur='checkfecha(0)' size='35' /> dd/mm/aaaa</label></td></tr>");
			out.print("<tr><td>No. Doc. Soporte</td><td><label><input name='txtDoc' type='text' id='txtDoc' size='40'/><td>Realizado por</td><td><label><input name='txtR' type='text' id='txtR' size='35' /></label></td></tr>");
			out.print("<tr><td width='13%'>Tipo de Movimiento</td><td width='45%'><label><select name='cmbTipoM' id='cmbTipoM'  ><option value='Seleccione'>Seleccione</option>");
			rs3=me.listarMovimientose();
			try {
				while(rs3.next()){
				out.print("<option value="+rs3.getString(1)+">"+rs3.getString(2)+"</option>");
				//out.print("a"+rs3.getString(1)+" b"+rs3.getString(2)); 
				}
				rs3.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("</select></label></td></tr>");
			out.print("<tr></tr><tr><td colspan='4'><div align='center'><label><input type='button' name='btnConsultarE' id='btnConsultarE' value='     CONSULTAR     ' onClick='ConsultarEA()'></label></div></td></tr></table>");
			out.print("<tr><td><div id='mova'></div></td></tr>");

			*/
		}
		
		
		if(va.equals("AnularE")){	
			out.print("<table width='100%' class='style6'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Anulación de Entradas</div></td></tr>");
			out.print("<tr><td>Movimiento Número</td><td><label><input name='txtMov' type='text' id='txtMov' size='40'/><td>Fecha</td><td><label><input name='txtVence0' type='text' id='txtVence0' onKeyup='mascas(this,patron,true,"+dia+","+mes+","+annio+")' onBlur='checkfecha(0)' size='35' /> dd/mm/aaaa</label></td></tr>");
			out.print("<tr><td>No. Doc. Soporte</td><td><label><input name='txtDoc' type='text' id='txtDoc' size='40'/><td>Realizado por</td><td><label><input name='txtR' type='text' id='txtR' size='35' /></label></td></tr>");
			out.print("<tr><td width='13%'>Tipo de Movimiento</td><td width='45%'><label><select name='cmbTipoM' id='cmbTipoM'  ><option value='Seleccione'>Seleccione</option>");
			rs3=me.listarMovimientose();
			try {
				while(rs3.next()){
				out.print("<option value="+rs3.getString(1)+">"+rs3.getString(2)+"</option>");
				//out.print("a"+rs3.getString(1)+" b"+rs3.getString(2)); 
				}
				rs3.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("</select></label></td></tr>");
			out.print("<tr></tr><tr><td colspan='4'><div align='center'><label><input type='button' name='btnConsultarE' id='btnConsultarE' value='     CONSULTAR     ' onClick='ConsultarEA()'></label></div></td></tr></table>");
			out.print("<tr><td><div id='mova'></div></td></tr>");

			
		}
		////////////////////////////////////
		
		if(va.equals("AnularE2")){	
			out.print("<table width='100%' class='style6'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Resultados de la Busqueda</div></td></tr>");
			String sql="select m.codigo,m.fecha,m.nsoporte,t.descripcion,m.concepto,u.usuario from farc_movimientos m,seg_usuario u, farc_tipomovimiento t where m.cod_usuarioFk=u.usu_codigo and m.tipo_mvtoFk=t.codigo and t.cod_movFk='1'";
			if(!nmov.equals("")){
				sql=sql+" and m.codigo='"+nmov+"'";
			}
			if(!fechas.equals("")){
				sql=sql+" and m.fecha='"+fechas+"'";
			}
			if(!documento.equals("")){
				sql=sql+" and m.nsoporte='"+documento+"'";
			}
			if(!R.equals("")){
				sql=sql+" and u.usuario='"+R+"'";
			}
			if(!tmov.equals("Seleccione")){
				sql=sql+" and m.tipo_mvtoFk='"+tmov+"'";
			}
			//out.print("nmov "+nmov+" fechas "+fechas+" documento "+documento+" R "+R+" tmov "+tmov);
			//out.print(sql);
			out.print("<table width='100%' border='1' class='style6'><tr><td width='7%'><div align='center'>Mov. No</div></td><td width='9%'><div align='center'>Fecha</div></td><td width='12%'><div align='center'>Doc. Soporte</div></td><td width='23%'><div align='center'>Movimiento</div></td><td width=30%'><div align='center'>Concepto</div></td><td width='15%'><div align='center'>Realizado por</div></td><td width='4%'><div align='center'>Ver</div></td></tr>");
			rs =me.ObtenerMoviA(sql);
			String color="'#FF6699'";
			try {
				while(rs.next()){
					out.print("<tr onMouseOver=this.style.background='D3D3D3' onmouseout=this.style.background='white'  ><td onclick='anular("+rs.getString(1)+")'>"+rs.getString(1)+"</td>");
					out.print("<td onclick='anular("+rs.getString(1)+")' >"+rs.getString(2)+"</td>");//onMouseOver='
					out.print("<td onclick='anular("+rs.getString(1)+")'>"+rs.getString(3)+"</td>");
					out.print("<td onclick='anular("+rs.getString(1)+")'>"+rs.getString(4)+"</td>");
					out.print("<td onclick='anular("+rs.getString(1)+")'>"+rs.getString(5)+"</td>");
					out.print("<td onclick='anular("+rs.getString(1)+")'>"+rs.getString(6)+"</td>");
					out.print("<td><a  href='#'onclick='veranular("+rs.getString(1)+")'>VER</a></td>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
		}
		
		
		if(va.equals("AnularE3")){	
			sw="0";
			rs1 =me.ObtenerInvAnular(nmov);
			try {
				while(rs1.next()){
					///////////////////////
					rs2 =me.ObtenerMovAnular(nmov,rs1.getString(1));
					try {
						while(rs2.next()){
							sw="1";							
						}
						rs2.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					////////disp/////////////
					
					rs3 =me.ObtenerDispAnular(rs1.getString(1));
					try {
						while(rs3.next()){
							sw="1";							
						}
						rs3.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					////////////////////////
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("<tr><td><label><input name='txtSw' type='hidden' id='txtSw' size='133%' value='"+sw+"'/></tr>");
			
		}
		//////////////////////////////////////
		if(va.equals("AnularE4")){	
			
			if(sw.equals("0")){
				out.print("<table width='100%' class='style6'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Entrada Seleccionada</div></td></tr>");
				String sql="select m.codigo,m.fecha,m.nsoporte,t.descripcion,m.concepto,u.usuario,m.tipo_mvtoFk from farc_movimientos m,seg_usuario u, farc_tipomovimiento t where m.cod_usuarioFk=u.usu_codigo and m.tipo_mvtoFk=t.codigo and t.cod_movFk='1' and m.codigo='"+nmov+"'";
				
				out.print("<table width='100%' border='1' class='style6'><tr><td width='7%'><div align='center'>Mov. No</div></td><td width='9%'><div align='center'>Fecha</div></td><td width='12%'><div align='center'>Doc. Soporte</div></td><td width='23%'><div align='center'>Movimiento</div></td><td width=30%'><div align='center'>Concepto</div></td><td width='15%'><div align='center'>Realizado por</div></td><td width='4%'><div align='center'>Ver</div></td></tr>");
				rs =me.ObtenerMoviA(sql);
				String tipomov="";
				try {
					while(rs.next()){
						out.print("<tr><td>"+rs.getString(1)+"</td>");
						out.print("<td>"+rs.getString(2)+"</td>");
						out.print("<td>"+rs.getString(3)+"</td>");
						out.print("<td>"+rs.getString(4)+"</td>");
						out.print("<td>"+rs.getString(5)+"</td>");
						out.print("<td>"+rs.getString(6)+"</td>");
						tipomov=rs.getString(7);
						out.print("<td><a  href='#'onclick='veranular("+rs.getString(1)+")'>VER</a></td>");
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				out.print("</table><table width='100%' border='1' class='style6'>");
				out.print("<tr><td width='15%'>Concepto de Anulación</td><td td width='85%'><label><input name='txtCanul' type='text' id='txtCanul' size='133%'/></tr>");
				out.print("</table><table width='100%' border='0' class='style6'>");
				out.print("<tr></tr><tr><td colspan='4'><div align='center'><label><input type='button' name='btnAnularE' id='btnAnularE' value='     ANULAR     ' onClick='AnularEA("+nmov+","+tipomov+")'></label></div></td></tr></table>");
			}
			///hacer el if del sw
		}
		/////////////////////////////////////////
		
		if(va.equals("AnularE5")){	
		Calendar calendario = Calendar.getInstance();
		//	Calendar calendario = new GregorianCalendar();
			int hora, minutos, segundos;
			hora =calendario.get(Calendar.HOUR_OF_DAY);
			minutos = calendario.get(Calendar.MINUTE);
			segundos = calendario.get(Calendar.SECOND);
			String hra= hora+":"+minutos+":"+segundos;
			String user = request.getParameter("user");
			
			String dias=fecha.substring(0, 2);
			String meses=fecha.substring(3, 5);
			String anos=fecha.substring(6, 10);
			String fec=anos+"-"+meses+"-"+dias;
			
			//Creamos el movimiento de anulacion
			me.CrearAnulacion(fec,hra,user,canul,tmov,nmov);
			//Consultamos el codigo de la anulacion
			rs=me.ObtenerAnulacion(fec, hra);
			String coda="";
			try {
				if(rs.next()){
				 coda=rs.getString(1);
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			//Creamos los detalles de movimientos de la anulacion
			rs1=me.ObtenerDetalleAnulacion(nmov);
			try {
				while(rs1.next()){
					me.CrearDetalleAnulacion(coda,rs1.getString(1),rs1.getString(2),rs1.getString(3),rs1.getString(4),rs1.getString(5),rs1.getString(6),rs1.getString(7),rs1.getString(8),rs1.getString(9),rs1.getString(10));
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			me.Anulardmov(nmov);
			me.Anularinv(nmov);
			me.Anularmov(nmov);
		
		}
		///////////////////////
	}

}
