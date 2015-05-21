package farc_controlador;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;
import java.text.*;
import java.awt.*;
import java.util.StringTokenizer;  //para dividir por token |

import java.io.*;
import java.sql.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import farc_metodo.MetodoCrearArticulo;
import farc_metodo.MetodoEntradas;
import farc_metodo.MetodoReporte;

import farc_metodo.MetodoExistencias;
import farc_metodo.MetodoEntradas;

/**
 * Servlet implementation class ControlExistencias
 */
public class ControlExistencias extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		MetodoEntradas ment = new MetodoEntradas();
		String user=request.getParameter("user");
		//OutputStream outi =response.getOutputStream(); 
		//OutputStream oux = response.getOutputStream();
		
		MetodoExistencias me = new MetodoExistencias();
		
		String va = request.getParameter("valor");
		String texto=request.getParameter("texto");
		
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		ResultSet rs3=null;
		
		

		
	/*	String bo = request.getParameter("bo");
		//out.print("Bo: "+bo);
		if(bo==null){bo="0";}
		//out.print("Bo "+bo);
		
		
		String cont = request.getParameter("cont");
		String texto=request.getParameter("texto");
		String xx=request.getParameter("xx");
		String TipoM = request.getParameter("TipoM");
		
		
		
		ResultSet rs1=null;
		ResultSet rs2=null;
		ResultSet rs3=null;
		ResultSet rs4=null;
		ResultSet rs5=null;
		
		
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
		String fecha=dia+"/"+mes+"/"+annio;
				
		if(cont==null){cont="0";}
		int conta = Integer.parseInt(cont);
		if (conta>9){conta=9;}
		
		String datos2 ="";
		datos2 = request.getParameter("datos2");
		if(datos2==null){datos2="";}
		if(conta==1){datos2="";}
		
		String V[] = new String[150];
		String MI[][] = new String[11][11];
		String M[][] = new String[11][11];
		
		*/
		
/*
		//
		
		//String M2[][] = request.getParameter("m2"); //= request.getParameter("m2");
	*/
		

		if(va.equals("ConOrdenProduccion")){
			String idia=request.getParameter("idia");
			String imes=request.getParameter("imes");
			String iano=request.getParameter("iano");
			String Fdia=request.getParameter("Fdia");
			String Fmes=request.getParameter("Fmes");
			String Fano=request.getParameter("Fano");
			
			String Fechai=iano+"-"+imes+"-"+idia;
			String Fechaf=Fano+"-"+Fmes+"-"+Fdia;
			rs=me.BuscarOrdenesProducciones(Fechai,Fechaf);
			out.print("<table class='style6'><tr align='center' width='100%'><td >&nbsp;<i><u> Consecutivo</i></u> &nbsp;</td><td >&nbsp;<i><u> Fecha </i></u>&nbsp; &nbsp;</td><td >&nbsp; &nbsp;<i><u> Resposable </i></u> &nbsp; &nbsp;</td><td >&nbsp; <i><u> Auxiliar </i></u>&nbsp; &nbsp;</td><td >&nbsp; &nbsp;<i><u> Verificado &nbsp;</i></u></td><td>&nbsp; <i><u>Ver Orden </i></u>&nbsp;</td><td>&nbsp; &nbsp; <i><u> Ver Etiqueta </i></u> &nbsp; &nbsp;</td><td>&nbsp; <i><u>Ver No. de Dosis Adecuar </i></u>&nbsp;</td><td>&nbsp; <i><u>Ver No. de Dosis Por Volumen </i></u>&nbsp;</td></tr>");
			try {
				while(rs.next()){
					out.print("<tr><td align='center' width='10%' >&nbsp; AB0"+rs.getString(1)+"&nbsp;</td><td align='center' width='10%'>"+rs.getString(2)+"</td><td align='center' width='15%'>"+rs.getString(3)+"</td><td align='center' width='15%'>"+rs.getString(4)+"</td><td width='15%'>"+rs.getString(5)+"</td><td align='center'  width='8%' ><input type='radio' name='radiobutton' onclick='BuscarOp("+rs.getString(1)+")' /></td><td align='center' width='8%'><input type='radio' name='radiobutton' onclick='BuscarEt("+rs.getString(1)+")' /></td><td width='8%' align='center'><input type='radio' name='radiobutton' onclick='BuscarDosisAdecuar("+rs.getString(1)+")' /></td><td width='20%' align='center'><input type='radio' name='radiobutton' onclick='BuscarDosisporVolumen("+rs.getString(1)+")' /></td></tr>");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			out.print("</table>");
		}
		
		
	if(va.equals("repcod")){
			
			String codop=request.getParameter("codop");
			System.out.println("codop "+codop);
			rs=me.BuscarOrdenP(codop);
			String CodMedProd="";
			String CodMed="";
			double Frecuencia=1;
			String VolumenFinal="";
			double DosisAdecuar;
			double CantAdecuar;
			double Dosis;
			double concentracion;
			double TDosis=0;
			int i=0;
			int j=0;
			out.print("<table class='contpre'><tr  bgcolor='#BBDEEC'><td>Nombre Medicamento</td><td>Concentracion</td><td>Diluyente</td><td>Total de Cant Adecuar</td></tr>");
			try {
				while(rs.next()){
					CodMedProd=rs.getString(2);
					CodMed=rs.getString(3);
					out.print("<tr border='1'><td> "+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td><td>"+rs.getString(6)+"</td>");
					rs1=me.Buscarfrec(codop,CodMedProd,CodMed);
					//System.out.println("rs -->"+i);
					//out.print("<tr><b><td><br>Orden <br></td><td><br>No de Dosis Adecuar <br></td><td><br>Volumen Final <br></td></b></tr>");
					TDosis=0; 
					System.out.println("Cuando i es "+i+"Medicamento es : "+CodMed);
					while(rs1.next()){
						Frecuencia=rs1.getInt(3);
						DosisAdecuar=24/Frecuencia;
						Dosis=rs1.getDouble(6);
						concentracion=rs1.getDouble(7);
						CantAdecuar=((Dosis*DosisAdecuar)/concentracion);
						//VolumenFinal=rs1.getString(4);
						//System.out.println("rs1 -->"+j);
					//	out.print("<tr><td>"+rs1.getString(5)+"</td><td>"+DosisAdecuar+"</td><td>"+VolumenFinal+"</td></tr>");
						TDosis=CantAdecuar+TDosis;
						//System.out.println("Medicamento "+CodMed);
						
						
						System.out.println("Cant a adecuar : "+CantAdecuar);
						//System.out.println("Total de Dosis"+big);
						j=j+1;
					}
					rs1.getStatement().getConnection().close();
					String val = TDosis+"";  
					BigDecimal big = new BigDecimal(val);  
					big = big.setScale(2, RoundingMode.HALF_UP); 
					out.print("<td >"+big+"</td></b></tr>");
					i=i+1;
					
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.println("</table>");
		}



		if(va.equals("repvol")){
			String codop=request.getParameter("codop");
			String tipo=request.getParameter("tipo");
			String a="";
			String b="";
			double Frecuencia=1;
			String VolumenFinal="0";
			double DosisAdecuar;
			double TDosis=0;
			String vector;
			int i=0;
			int j=0;
			String val="0";
			try {
				out.print("<table class='contpre'>");
				if(tipo.equals("1")){
					out.print("<tr border='1'><td colspan='4'><b><br> JERINGAS 1 mL</b></td></tr>");
					a="1";
				}else{
					if(tipo.equals("2")){
						out.print("<tr border='1'><td colspan='4'><b><br> JERINGAS 2 mL</b></td></tr>");
						a="1";
						b="2";
					}else{
						if(tipo.equals("3")){
							out.print("<tr border='1'><td colspan='4'><b><br> JERINGAS 3 mL</b></td></tr>");
							a="2";
							b="3";
						}else{
							if(tipo.equals("4")){
								out.print("<tr border='1'><td colspan='4'><b><br> JERINGAS 5 mL</b></td></tr>");
								a="3";
								b="5";
							}else{
								if(tipo.equals("5")){
									out.print("<tr border='1'><td colspan='4'><b><br> JERINGAS 10 mL</b></td></tr>");
									a="5";
									b="10";
								}else{
									if(tipo.equals("6")){
										out.print("<tr border='1'><td colspan='4'><b><br> JERINGAS 20 mL</b></td></tr>");
										a="10";
										b="20";
									}else{
										if(tipo.equals("7")){
											out.print("<tr border='1'><td colspan='4'><b><br> BOLSA DE 100 mL</b></td></tr>");
											a="100";
											b="150";
										}else{
											if(tipo.equals("8")){
												out.print("<tr border='1'><td colspan='4'><b><br>BOLSA DE 250 mL</b></td></tr>");
												a="250";
												b="270";
											}else{
												if(tipo.equals("9")){
													out.print("<tr border='1'><td colspan='4'><b><br> BOLSA DE 500 mL</b></td></tr>");
													a="500";
													
												}
											}
										}
									}
								}
							}
						}
					}
				}
				rs=me.BuscarOrdenPV(codop,tipo,a,b);
				out.print("<tr bgcolor='#BBDEEC'><b><td><br>Orden <br></td><td><br>Medicamento<br></td><td><br>No de Dosis Adecuar <br></td><td><br>Volumen Final <br></td></b></tr>");
				while(rs.next()){
					Frecuencia=rs.getInt(5);
					DosisAdecuar=24/Frecuencia;
					VolumenFinal=rs.getString(6);
						//out.print("<tr border='1'><td colspan='4'><b><br> JERINGAS 1 mL</b></td></tr>");
						i++;
						out.print("<tr><td>"+rs.getString(7)+"</td><td>"+rs.getString(4)+"</td><td>"+DosisAdecuar+"</td><td>"+VolumenFinal+"</td></tr>");
						TDosis=DosisAdecuar+TDosis;
						//out.print("<tr border='1'><b><td colspan='2'>Total de Dosis a Adecuar</td><td>"+TDosis+"</td></b></tr>");
				}
				
				if(i==0){
					out.print("<tr><td> No hay Resultados</td></tr>");
				}else{
					out.print("<tr border='1'><b><td colspan='2'>Total de Dosis a Adecuar</td><td>"+TDosis+"</td></b></tr>");
				}
				
				rs.getStatement().getConnection().close();		
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.println("</table>");
		}
		
		if(va.equals("Existencias")){	
			out.print("<table width='100%' class='style6'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Relación de Existencias</div></td></tr>");
			out.print("<tr><td><label><input name='radiobutton' type='radio' value='radiobutton' id='General' onClick='Seleccione()' />Reporte General</label></td>");
			out.print("<td><label><input name='radiobutton' type='radio' value='radiobutton' id='Agrupado' onClick='Seleccione()' />Reporte Agrupado</label></td>");
			out.print("<td><label><input name='radiobutton' type='radio' value='radiobutton' id='Medicamento' onClick='Seleccione()' />Por Producto</label></td></tr>");	
			//out.print("<option value='todas'>TODAS LAS BODEGAS</option>");
		} 


		
		if(va.equals("General")){	
			out.print("<table width='100%' class='style6'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Relación de Existencias</div></td></tr>");
			
			out.print("<tr><td><label><input name='radiobutton' type='radio' value='radiobutton' id='General' onClick='Seleccione()' checked />Reporte General</label></td>");
			out.print("<td><label><input name='radiobutton' type='radio' value='radiobutton' id='Agrupado' onClick='Seleccione()' />Reporte Agrupado</label></td>");
			out.print("<td><label><input name='radiobutton' type='radio' value='radiobutton' id='Medicamento' onClick='Seleccione()' />Por Producto</label></td></tr>");	
			
			out.print("<table width='100%' class='style6'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Reporte General</div></td></tr>");
			
			out.print("<tr><td width='12%'>Seleccione Bodega</td><td><label><select name='cmbBodega' id='cmbBodega'><option value='Seleccione'>Seleccione</option>");
			out.print("<option value='todas'>TODAS LAS BODEGAS</option>");
			rs=me.ObtenerBodegas(user);
			try {
				while(rs.next()){
				out.print("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("</select></label></td></tr>");	
			
			out.print("<td colspan='4'><label><input type='button' name='btnConsultarE' id='btnConsultarE' value='     CONSULTAR     ' onClick='ConsultarEG()'></label></td>");
						
		}  


				   
		if(va.equals("Agrupado")){	
			out.print("<table width='100%' class='style6'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Relación de Existencias</div></td></tr>");
			
			out.print("<tr><td><label><input name='radiobutton' type='radio' value='radiobutton' id='General' onClick='Seleccione()' />Reporte General</label></td>");
			out.print("<td><label><input name='radiobutton' type='radio' value='radiobutton' id='Agrupado' onClick='Seleccione()' checked />Reporte Agrupado</label></td>");
			out.print("<td><label><input name='radiobutton' type='radio' value='radiobutton' id='Medicamento' onClick='Seleccione()' />Por Producto</label></td></tr>");	
			
			out.print("<table width='100%' class='style6'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Reporte Agrupado</div></td></tr>");
			
			out.print("<tr><td width='12%'>Seleccione Bodega</td><td><label><select name='cmbBodega' id='cmbBodega'><option value='Seleccione'>Seleccione</option>");
			out.print("<option value='todas'>TODAS LAS BODEGAS</option>");
			rs=me.ObtenerBodegas(user);
			try { 
				while(rs.next()){
				out.print("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
				} 
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("</select></label></td>");	
			 
			out.print("<td width='12%'>Agrupadas por:</td><td><label><select name='cmbAgru' id='cmbAgru' ><option value='Seleccione'>Seleccione</option>");
			out.print("<option value='1'>GRUPO AL QUE PERTENECE</option>");
			out.print("<option value='2'>CLASIFICACIÓN</option>");
			out.print("<option value='3'>MEDICAMENTOS DE CONTROL</option>");
			out.print("<option value='4'>FORMA FARMACEUTICA</option>");
	    		     
			out.print("</select></label></td></tr>");
			out.print("<tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr><td colspan='4'><div align='center'><label><input type='button' name='btnConsultarE' id='btnConsultarE' value='     CONSULTAR     ' onClick='ConsultarE()'></label></div></td></tr>");
						   
		}
    
		
		if(va.equals("Medicamento")){	
			out.print("<table width='100%' class='style6'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Relación de Existencias</div></td></tr>");
			
			out.print("<tr><td><label><input name='radiobutton' type='radio' value='radiobutton' id='General' onClick='Seleccione()' />Reporte General</label></td>");
			out.print("<td><label><input name='radiobutton' type='radio' value='radiobutton' id='Agrupado' onClick='Seleccione()' />Reporte Agrupado</label></td>");
			out.print("<td><label><input name='radiobutton' type='radio' value='radiobutton' id='Medicamento' onClick='Seleccione()' checked />Por Producto</label></td></tr>");	
			
			out.print("<table width='100%' class='style6'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Reporte Por Medicamento</div></td></tr>");
			
			out.print("<table width='100%' border='0' class='style6'><tr><td width='30%'><u><i><div align='left'>Producto</div></i></u></td><td width='70%'><u><i><div align='left'>Accion</div></i></u></td></tr>");
			//out.print("<table width='100%' border='1' class='style6'>");
			
		
			//Hacer Autocompletar d productos
			out.print("<tr><td><input name='txtTipoMe0' type='text' id='txtTipoMe0' onkeyup='autocompletaMArticulo(0,0,1)' size='39' />");//identificador Div+identificador campo
			out.print("<input name='txtTipoMeH0' type='hidden' id='txtTipoMeH0'/></td>");//identificador Div+identificador campo
			out.print("<td colspan='4'><div><label><input type='button' name='btnMP' id='btnMP' value='Consultar' onClick='MP()' disabled='disabled'></label></div></td></tr>");
			out.print("<tr><td><div id='sugerencias10'></div></td></tr>");
			out.print("<tr><td colspan='2'>&nbsp;</td></tr></table>");
						
		} 
		
		if(va.equals("Consumo")){	
			out.print("<table width='100%' class='style6'><tr><td colspan='9'><div align='center' class='style11' id='cabecera2'>Reportes</div></td></tr>");
			out.print("<tr><td><label><input name='radiobutton' type='radio' value='radiobutton' id='GeneralConsumo' onClick='Seleccione()' />Reporte de Consumo</label></td>");
			out.print("<td><label><input name='radiobutton' type='radio' value='radiobutton' id='Dispensacion' onClick='Seleccione()' />Reporte Dispensacion</label></td>");
			out.print("<td><label><input name='radiobutton' type='radio' value='radiobutton' id='Movimiento' onClick='Seleccione()' />Reporte tipo de Movimiento</label></td>");	
			out.print("<td><label><input name='radiobutton' type='radio' value='radiobutton' id='Producto' onClick='Seleccione()' />Reporte por Producto</label></td>");
			out.print("<td><label><input name='radiobutton' type='radio' value='radiobutton' id='Ordenes' onClick='Seleccione()' />Reporte de Ordenes</label></td>" +
					"<td><label><input name='radiobutton' type='radio' value='radiobutton' id='MedDx' onClick='Seleccione()' />Reporte Medicamentos y Diagnosticos</label></td>" +
					"<td><label><input name='radiobutton' type='radio' value='radiobutton' id='RepSal' onClick='Seleccione()' />Reporte De Salidas</label></td>" +
					"<td><label><input name='radiobutton' type='radio' value='radiobutton' id='InfSis' onClick='Seleccione()' />Informacion Para Sismed Compras</label></td>" +
					"<td><label><input name='radiobutton' type='radio' value='radiobutton' id='InfSisV' onClick='Seleccione()' />Informacion Para Sismed Ventas</label></td>" +
					"</tr>");
			//out.print("<option value='todas'>TODAS LAS BODEGAS</option>");
		} 
		if(va.equals("InfSis")){
			out.print("<table width='100%' class='style6'><tr><td colspan='9'><div align='center' class='style11' id='cabecera2'>Reportes</div></td></tr>");
			out.print("<tr><td><label><input name='radiobutton' type='radio' value='radiobutton' id='GeneralConsumo' onClick='Seleccione()' />Reporte de Consumo</label></td>");
			out.print("<td><label><input name='radiobutton' type='radio' value='radiobutton' id='Dispensacion' onClick='Seleccione()' />Reporte Dispensacion</label></td>");
			out.print("<td><label><input name='radiobutton' type='radio' value='radiobutton' id='Movimiento' onClick='Seleccione()' />Reporte tipo de Movimiento</label></td>");	
			out.print("<td><label><input name='radiobutton' type='radio' value='radiobutton' id='Producto' onClick='Seleccione()' />Reporte por Producto</label></td>");
			out.print("<td><label><input name='radiobutton' type='radio' value='radiobutton' id='Ordenes' onClick='Seleccione()' />Reporte de Ordenes</label></td>" +
					"<td><label><input name='radiobutton' type='radio' value='radiobutton' id='MedDx' onClick='Seleccione()'  />Reporte Medicamentos y Diagnosticos</label></td>" +
					"<td><label><input name='radiobutton' type='radio' value='radiobutton' id='RepSal' onClick='Seleccione()'  />Reporte De Salidas</label></td>" +
					"<td><label><input name='radiobutton' type='radio' value='radiobutton' id='InfSis' onClick='Seleccione()' />Informacion Para Sismed Compras</label></td>" +
					"<td><label><input name='radiobutton' type='radio' value='radiobutton' id='InfSisV' onClick='Seleccione()' />Informacion Para Sismed Ventas</label></td>" +
					"</tr></table>");
			out.print("<table width='100%' class='style6'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Informacion Para Sismed Compras</div></td></tr></table>");

			out.print("<table border='0' cellspacing='0' cellpadding='0'><tr> " +
					"<td>Fecha Inicial <input name='txtFechaInicial' type='text' id='txtFechaInicial' onkeyup='masca(this,&quot;/&quot;,patron,true)' maxlength='10' /></td> " +
					"<td>Fecha Final <input name='txtFechaFinal' type='text' id='txtFechaFinal' onkeyup='masca(this,&quot;/&quot;,patron,true)' maxlength='10'  /> " +
					"<input name='btnBuscarPac' type='button' id='btnBuscarPac' value='Buscar' onclick='BuscarInfSis()' /></td></tr></table>");
		}
		
		if(va.equals("InfSisV")){
			out.print("<table width='100%' class='style6'><tr><td colspan='9'><div align='center' class='style11' id='cabecera2'>Reportes</div></td></tr>");
			out.print("<tr><td><label><input name='radiobutton' type='radio' value='radiobutton' id='GeneralConsumo' onClick='Seleccione()' />Reporte de Consumo</label></td>");
			out.print("<td><label><input name='radiobutton' type='radio' value='radiobutton' id='Dispensacion' onClick='Seleccione()' />Reporte Dispensacion</label></td>");
			out.print("<td><label><input name='radiobutton' type='radio' value='radiobutton' id='Movimiento' onClick='Seleccione()' />Reporte tipo de Movimiento</label></td>");	
			out.print("<td><label><input name='radiobutton' type='radio' value='radiobutton' id='Producto' onClick='Seleccione()' />Reporte por Producto</label></td>");
			out.print("<td><label><input name='radiobutton' type='radio' value='radiobutton' id='Ordenes' onClick='Seleccione()' />Reporte de Ordenes</label></td>" +
					"<td><label><input name='radiobutton' type='radio' value='radiobutton' id='MedDx' onClick='Seleccione()'  />Reporte Medicamentos y Diagnosticos</label></td>" +
					"<td><label><input name='radiobutton' type='radio' value='radiobutton' id='RepSal' onClick='Seleccione()'  />Reporte De Salidas</label></td>" +
					"<td><label><input name='radiobutton' type='radio' value='radiobutton' id='InfSis' onClick='Seleccione()' />Informacion Para Sismed Compras</label></td>" +
					"<td><label><input name='radiobutton' type='radio' value='radiobutton' id='InfSisV' onClick='Seleccione()' />Informacion Para Sismed Ventas</label></td>" +
					"</tr></table>");
			out.print("<table width='100%' class='style6'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Informacion Para Sismed Ventas</div></td></tr></table>");

			out.print("<table border='0' cellspacing='0' cellpadding='0'><tr> " +
					"<td>Fecha Inicial <input name='txtFechaInicial' type='text' id='txtFechaInicial' onkeyup='masca(this,&quot;/&quot;,patron,true)' maxlength='10' /></td> " +
					"<td>Fecha Final <input name='txtFechaFinal' type='text' id='txtFechaFinal' onkeyup='masca(this,&quot;/&quot;,patron,true)' maxlength='10'  /> " +
					"<input name='btnBuscarPac' type='button' id='btnBuscarPac' value='Buscar' onclick='BuscarInfSisV()' /></td></tr></table>");
		}
		
		if(va.equals("BInfSisV")){
			MetodoReporte mer = new MetodoReporte();
			String FechaInicial=request.getParameter("FechaInicial");
			String FechaFinal=request.getParameter("FechaFinal");
			Integer i=0;	
			long suma=0;
			rs=mer.BuscarPeriodoV(FechaInicial, FechaFinal);			
			try {
				out.print("<table width='100%' border='1' cellspacing='0' cellpadding='0' ><tr><td><div align='center' class='style11' id='cabecera2'>DATOS PARA REPORTE SISMED VENTAS</div></td></tr></table>");
				out.print("<table border='1' cellspacing='0' cellpadding='0' width='100%'>" +
					"<tr  BGCOLOR='#CCCCCC' align='center'> " +
					"<td>Item</td> " + 
					"<td>Fecha</td> " + 
					"<td>Medicamento</td> " + 
					"<td>Cod Cum</td> " +  
					"<td>Precio Minimo</td> " + 
					"<td>Precio Maximo</td> " +  
					"<td>Cantidad</td> " + 
					"<td>Valor Total</td> " + 
					"<td>Factura Vlr Minimo</td> " +
					"<td>Factura Vlr Maximo</td> " +
					"</tr>");
				while(rs.next()){	
					int Cantidad=0;
					int VlrMin=0;
					int Resul=0;
					String FacturaMN="";
					String FacturaMX="";
					i=i+1;
					//String[] arrayColores = rs.getString("fecha").split("-");
					out.print("<tr>" +
							"<td>"+i+"</td> " +
							"<td>"+rs.getString("Mes")+"</td>" +
							"<td>"+rs.getString("Medicamento")+"</td> " +
							"<td>"+rs.getString("CUM")+"</td>" );
					rs1=mer.BuscarfactVMN(rs.getString("Mes"), rs.getString("cod_programa"));
					if(rs1.next()){
						VlrMin=rs1.getInt("valor");
						FacturaMN=rs1.getString("consecutivo");
						out.print("<td>"+rs1.getString("valor")+"</td>" );
					}
					rs1.getStatement().getConnection().close();
					
					rs1=mer.BuscarfactVMX(rs.getString("Mes"), rs.getString("cod_programa"));
					if(rs1.next()){
						FacturaMX=rs1.getString("consecutivo");
						out.print("<td>"+rs1.getString("valor")+"</td>" );
					}
					rs1.getStatement().getConnection().close();
					
					rs1=mer.BuscarfactVCantidad(rs.getString("Mes"), rs.getString("cod_programa"));
					if(rs1.next()){
						Cantidad=rs1.getInt("Cantidad");
						out.print("<td>"+rs1.getString("Cantidad")+"</td>" );
					}
					rs1.getStatement().getConnection().close();
					Resul=Cantidad * VlrMin;
					//out.print("<td>"+rs.getString("Precio_Maximo")+"</td>" );
					//out.print("<td>"+rs.getString("Cantidad")+"</td> " );
					out.print("<td>"+Resul+"</td>" );
					out.print("<td>"+FacturaMN+"</td>");
					out.print("<td>"+FacturaMX+"</td>");
					//"");
					//suma=suma+Integer.parseInt(rs.getString(7));
					/*rs1=mer.BuscarfactV(rs.getString("Precio_Minimo"), rs.getString("cod_programa"),FechaInicial, FechaFinal);
					if(rs1.next()){
						out.print("<td>"+rs1.getString("consecutivo")+"</td>");
					}else{
						out.print("<td>N/A"+rs.getString("Precio_Maximo")+"-"+rs.getString("cod_programa")+"-"+rs.getString("cod_enc_factura")+"</td>");
					}
					rs1.getStatement().close();
					rs1=mer.BuscarfactV(rs.getString("Precio_Maximo"), rs.getString("cod_programa"),FechaInicial, FechaFinal);
					if(rs1.next()){
						out.print("<td>"+rs1.getString("consecutivo")+"</td></tr>");
					}else{
						out.print("<td>N/A"+rs.getString("Precio_Maximo")+"-"+rs.getString("cod_programa")+"-"+rs.getString("cod_enc_factura")+"</td>");
					}
					rs1.getStatement().close();*/
				}
				//out.print("<tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td>"+suma+"</td><td></td><td></td></tr>");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				out.print("Error: "+e);
				e.printStackTrace();
			}
			out.print("</table>");
		}
		
		if(va.equals("BInfSis")){
			MetodoReporte mer = new MetodoReporte();
			String FechaInicial=request.getParameter("FechaInicial");
			String FechaFinal=request.getParameter("FechaFinal");
			Integer i=0;	
			long suma=0;
			rs=mer.BuscarPeriodo(FechaInicial, FechaFinal);			
			try {
				out.print("<table width='100%' border='1' cellspacing='0' cellpadding='0' ><tr><td><div align='center' class='style11' id='cabecera2'>DATOS PARA REPORTE SISMED COMPRAS </div></td></tr></table>");
				out.print("<table border='1' cellspacing='0' cellpadding='0' width='100%'>" +
					"<tr  BGCOLOR='#CCCCCC' align='center'> " +
					"<td>Item</td> " + 
					"<td>Fecha</td> " + 
					"<td>Medicamento</td> " + 
					"<td>Cod Cum</td> " +  
					"<td>Precio Minimo</td> " + 
					"<td>Precio Maximo</td> " +  
					"<td>Cantidad</td> " + 
					"<td>Valor Total</td> " + 
					"<td>Factura Vlr Minimo</td> " +
					"<td>Factura Vlr Maximo</td> " +
					"</tr>");
				while(rs.next()){					
					i=i+1;
					//String[] arrayColores = rs.getString("fecha").split("-");
					out.print("<tr>" +
							"<td>"+i+"</td> " +
							"<td>"+rs.getString("fecha")+"</td>" +
							"<td>"+rs.getString("Medicamento")+"</td> " +
							"<td>"+rs.getString("cod_cups")+"</td>" +
							"<td>"+rs.getString("Precio_Minimo")+"</td>" +
							"<td>"+rs.getString("Precio_Maximo")+"</td>" +
							"<td>"+rs.getString("cantidad")+"</td> " +
							"<td>"+rs.getString("valorTotal")+"</td>" +
					"");
					suma=suma+Integer.parseInt(rs.getString(7));
					rs1=mer.Buscarfact(rs.getString("Precio_Minimo"), rs.getString("CodMedica"));
					if(rs1.next()){
						out.print("<td>"+rs1.getString("numero_factura")+"</td>");
					}else{
						out.print("<td>N/A</td>");
					}
					rs1.getStatement().close();
					rs1=mer.Buscarfact(rs.getString("Precio_Maximo"), rs.getString("CodMedica"));
					if(rs1.next()){
						out.print("<td>"+rs1.getString("numero_factura")+"</td></tr>");
					}else{
						out.print("<td>N/A</td>");
					}
					rs1.getStatement().close();
				}
				out.print("<tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td>"+suma+"</td><td></td><td></td></tr>");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.print("</table>");
		}

		if(va.equals("MedDx")){
			out.print("<table width='100%' class='style6'><tr><td colspan='8'><div align='center' class='style11' id='cabecera2'>Reportes</div></td></tr>");
			out.print("<tr><td><label><input name='radiobutton' type='radio' value='radiobutton' id='GeneralConsumo' onClick='Seleccione()' />Reporte de Consumo</label></td>");
			out.print("<td><label><input name='radiobutton' type='radio' value='radiobutton' id='Dispensacion' onClick='Seleccione()' />Reporte Dispensacion</label></td>");
			out.print("<td><label><input name='radiobutton' type='radio' value='radiobutton' id='Movimiento' onClick='Seleccione()' />Reporte tipo de Movimiento</label></td>");	
			out.print("<td><label><input name='radiobutton' type='radio' value='radiobutton' id='Producto' onClick='Seleccione()' />Reporte por Producto</label></td>");
			out.print("<td><label><input name='radiobutton' type='radio' value='radiobutton' id='Ordenes' onClick='Seleccione()' />Reporte de Ordenes</label></td>" +
					"<td><label><input name='radiobutton' type='radio' value='radiobutton' id='MedDx' onClick='Seleccione()' checked />Reporte Medicamentos y Diagnosticos</label></td>" +
					"<td><label><input name='radiobutton' type='radio' value='radiobutton' id='RepSal' onClick='Seleccione()' />Reporte De Salidas</label></td>" +
					"</tr></table>");
			out.print("<table width='100%' class='style6'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Reporte Medicamentos y Diagnosticos</div></td></tr></table>");

			out.print("<table border='0' cellspacing='0' cellpadding='0'><tr> " +
					"<td>Fecha Inicial <input name='txtFechaInicial' type='text' id='txtFechaInicial' onkeyup='masca(this,&quot;/&quot;,patron,true)' maxlength='10' /></td> " +
					"<td>Fecha Final <input name='txtFechaFinal' type='text' id='txtFechaFinal' onkeyup='masca(this,&quot;/&quot;,patron,true)' maxlength='10'  /> " +
					"<input name='btnBuscarPac' type='button' id='btnBuscarPac' value='Buscar' onclick='BuscarMedDx()' /></td></tr></table>");
		}
		if(va.equals("BMedDx")){
			String FechaInicial=request.getParameter("FechaInicial");
			String FechaFinal=request.getParameter("FechaFinal");			
			try {
				out.print("<table width='100%' border='1' cellspacing='0' cellpadding='0' ><tr id='cabecera2' class='style11' align='center'><td colspan='5'>REPORTE MEDICAMENTOS Y DIAGNOSTICOS ENTRE  "+FechaInicial+" Y "+FechaFinal+"</td></tr>");
				out.print("<tr><td>Fecha</td><td>Nombre Medicamento</td><td>Cantidad</td><td>Diagnosticos Ingreso</td><td>Diagnosticos Egresos</td></tr>");
				rs=me.BuscarMedicamentosDx(FechaInicial, FechaFinal);
				while(rs.next()){
					
					out.println("<tr>");
					out.println("<td>"+rs.getString("fecha_realizacion")+"</td>");
					out.println("<td>"+rs.getString("nombre_programa")+"</td>");
					out.println("<td>"+rs.getString("cantidad")+"</td>");					
					rs1=me.BuscarDxI(rs.getString("cod_admision"));
					if(rs1.next()){
						out.println("<td>"+rs1.getString(1)+"</td>");
					}
					rs1.getStatement().getConnection().close();
					
					rs1=me.BuscarDxE(rs.getString("cod_admision"));
					if(rs1.next()){
						out.println("<td>"+rs1.getString(1)+"</td>");
					}
					rs1.getStatement().getConnection().close();					
					out.println("</tr>");
				}
				out.println("</table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("BRepSal")){
			String FechaInicial=request.getParameter("FechaInicial");
			String FechaFinal=request.getParameter("FechaFinal");			
			try {
				out.print("<table width='100%' border='1' cellspacing='0' cellpadding='0' ><tr id='cabecera2' class='style11' align='center'><td colspan='4'>REPORTE SALIDAS DE MEDICAMENTOS E INSUMOS  ENTRE  "+FechaInicial+" Y "+FechaFinal+"</td></tr>");
				out.print("<tr><td>Tipo Insumo</td><td>Nombre Medicamento</td><td>Cantidad</td><td>Bodega Destino</td></tr>");
				rs=me.ReporteSalidasFarmacia(FechaInicial, FechaFinal);
				while(rs.next()){					
					out.println("<tr>");
					out.println("<td>"+rs.getString("tipo")+"</td>");
					out.println("<td>"+rs.getString("nombre")+"</td>");
					out.println("<td>"+rs.getString("CANTIDAD")+"</td>");					
					out.println("<td>"+rs.getString("BodegaD")+"</td>");			
									
					out.println("</tr>");
				}
				out.println("</table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		if(va.equals("RepSal")){
			out.print("<table width='100%' class='style6'><tr><td colspan='8'><div align='center' class='style11' id='cabecera2'>Reportes</div></td></tr>");
			out.print("<tr><td><label><input name='radiobutton' type='radio' value='radiobutton' id='GeneralConsumo' onClick='Seleccione()' />Reporte de Consumo</label></td>");
			out.print("<td><label><input name='radiobutton' type='radio' value='radiobutton' id='Dispensacion' onClick='Seleccione()' />Reporte Dispensacion</label></td>");
			out.print("<td><label><input name='radiobutton' type='radio' value='radiobutton' id='Movimiento' onClick='Seleccione()' />Reporte tipo de Movimiento</label></td>");	
			out.print("<td><label><input name='radiobutton' type='radio' value='radiobutton' id='Producto' onClick='Seleccione()' />Reporte por Producto</label></td>");
			out.print("<td><label><input name='radiobutton' type='radio' value='radiobutton' id='Ordenes' onClick='Seleccione()' />Reporte de Ordenes</label></td>" +
					"<td><label><input name='radiobutton' type='radio' value='radiobutton' id='MedDx' onClick='Seleccione()' />Reporte Medicamentos y Diagnosticos</label></td>" +
					"<td><label><input name='radiobutton' type='radio' value='radiobutton' id='RepSal' onClick='Seleccione()' checked />Reporte De Salidas</label></td>" +
					"</tr></table>");
			out.print("<table width='100%' class='style6'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Reporte De Salidas</div></td></tr></table>");
			
			out.print("<table border='0' cellspacing='0' cellpadding='0'><tr> " +
					"<td>Fecha Inicial <input name='txtFechaInicial' type='text' id='txtFechaInicial' onkeyup='masca(this,&quot;/&quot;,patron,true)' maxlength='10' /></td> " +
					"<td>Fecha Final <input name='txtFechaFinal' type='text' id='txtFechaFinal' onkeyup='masca(this,&quot;/&quot;,patron,true)' maxlength='10'  /> " +
					"<input name='btnBuscarPac' type='button' id='btnBuscarPac' value='Buscar' onclick='BuscarRepSal()' /></td></tr></table>");

		}
		

		if(va.equals("GeneralConsumo")){	
			out.print("<table width='100%' class='style6'><tr><td colspan='6'><div align='center' class='style11' id='cabecera2'>Relación de Consumo</div></td></tr>");
			
			out.print("<tr><td><label><input name='radiobutton' type='radio' value='radiobutton' id='GeneralConsumo' onClick='Seleccione()' checked />Reporte Consumo</label></td>");
			out.print("<td><label><input name='radiobutton' type='radio' value='radiobutton' id='Dispensacion' onClick='Seleccione()' />Reporte Dispensacion</label></td>");
			out.print("<td><label><input name='radiobutton' type='radio' value='radiobutton' id='Movimiento' onClick='Seleccione()' />Reporte por Tipo de Movimiento</label></td>");	
			out.print("<td><label><input name='radiobutton' type='radio' value='radiobutton' id='Producto' onClick='Seleccione()' />Reporte por Producto</label></td>");
			out.print("<td><label><input name='radiobutton' type='radio' value='radiobutton' id='Ordenes' onClick='Seleccione()' />Reporte de Ordenes</label></td></tr>");
			out.print("<table width='100%' class='style6'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Reporte General</div></td></tr>");
			
			out.print("<tr><td width='12%'>Seleccione Bodega</td><td><label><select name='cmbBodega' id='cmbBodega'><option value='Seleccione'>Seleccione</option>");
			out.print("<option value='todas'>TODAS LAS BODEGAS</option>");
			rs=me.ObtenerBodegas(user);
			try {
				while(rs.next()){
				out.print("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("</select></label></td><td>Tipo de Producto: </td><td><select name='cmdproducto' id='cmdproducto'>" +
					"<option value='Seleccione'>Seleccione</option>" +
					"<option value='4'>TODOS LOS MEDICAMENTOS Y DISPOSITIVOS </option>"+
					"<option value='1'>MEDICAMENTOS </option>" +
					"<option value='3'>MEDICAMENTOS DE CONTROL </option>"+
					"<option value='2'>DISPOSITIVOS </option>" +
					"</select></td></tr><tr><td> Fecha Inicial : </td><td>");
										int dia=0;
										out.println("<select name=Idia id=Idia>");
										out.println("<option value=40>---</option>");
										for(int i=0;i<=30;i++){
										dia=dia+1;
										out.println("<option value="+dia+">"+dia+"</option>");
										}
										
										out.println("</select>");
										out.println("<select name=Imes id=Imes>");
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
										
										out.println("<select name=Iano id=Iano>");
										out.println("<option value=40>---</option>");
										int ano=2008;
										for(int k=0;k<=30;k++){
											ano=ano+1;
											out.println("<option value="+ano+">"+ano+"</option>");
										}
										out.println("</select></td>");	
										out.println("<td>Fecha Final : </td><td>");
										int fdia=0;
										out.println("<select name=Fdia id=Fdia>");
										out.println("<option value=40>---</option>");
										for(int i=0;i<=30;i++){
										fdia=fdia+1;
										out.println("<option value="+fdia+">"+fdia+"</option>");
										}
										
										out.println("</select>");
										out.println("<select name=Fmes id=Fmes>");
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
										
										
										out.println("<select name=Fano id=Fano>");
										out.println("<option value=40>---</option>");
										int fano=2008;
										for(int k=0;k<=30;k++){
											fano=fano+1;
											out.println("<option value="+fano+">"+fano+"</option>");
										}
										out.println("</select></td></tr>");
			
			out.print("<td colspan='4'><label><input type='button' name='btnConsultarE' id='btnConsultarE' value='  Consultar  ' onClick='ConsultarCons()'></label></td>");
						
		}  



		if(va.equals("Dispensacion")){	
			out.print("<table width='100%' class='style6'><tr><td colspan='6'><div align='center' class='style11' id='cabecera2'>Relación de Dispensacion</div></td></tr>");
			
			out.print("<tr><td><label><input name='radiobutton' type='radio' value='radiobutton' id='GeneralConsumo' onClick='Seleccione()' />Reporte Consumo</label></td>");
			out.print("<td><label><input name='radiobutton' type='radio' value='radiobutton' id='Dispensacion' onClick='Seleccione()' checked />Reporte Dispensacion</label></td>");
			out.print("<td><label><input name='radiobutton' type='radio' value='radiobutton' id='Movimiento' onClick='Seleccione()' />Reporte Por Tipo de Movimiento</label></td>");	
			out.print("<td><label><input name='radiobutton' type='radio' value='radiobutton' id='Producto' onClick='Seleccione()' />Reporte por Producto</label></td>");
			out.print("<td><label><input name='radiobutton' type='radio' value='radiobutton' id='Ordenes' onClick='Seleccione()' />Reporte de Ordenes</label></td></tr>");
			out.print("<table width='100%' class='style6'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Reporte por Dispensacion a Pacientes</div></td></tr>");
			
			out.print("<tr><td width='12%'>Seleccione Bodega</td><td><label><select name='cmbBodega' id='cmbBodega'><option value='Seleccione'>Seleccione</option>");
			out.print("<option value='todas'>TODAS LAS BODEGAS</option>");
			rs=me.ObtenerBodegas(user);
			try { 
				while(rs.next()){
				out.print("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
				} 
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("</select></label></td>");	
			 
			out.print("</tr>");
			out.print("<tr><td> Fecha Inicial : </td><td>");
			int dia=0;
			out.println("<select name=Idia id=Idia>");
			out.println("<option value=40>---</option>");
			for(int i=0;i<=30;i++){
			dia=dia+1;
			out.println("<option value="+dia+">"+dia+"</option>");
			}
			
			out.println("</select>");
			out.println("<select name=Imes id=Imes>");
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
			
			out.println("<select name=Iano id=Iano>");
			out.println("<option value=40>---</option>");
			int ano=2008;
			for(int k=0;k<=30;k++){
				ano=ano+1;
				out.println("<option value="+ano+">"+ano+"</option>");
			}
			out.println("</select></td>");	
			out.println("<td>Fecha Final : </td><td>");
			int fdia=0;
			out.println("<select name=Fdia id=Fdia>");
			out.println("<option value=40>---</option>");
			for(int i=0;i<=30;i++){
			fdia=fdia+1;
			out.println("<option value="+fdia+">"+fdia+"</option>");
			}
			
			out.println("</select>");
			out.println("<select name=Fmes id=Fmes>");
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
			
			
			out.println("<select name=Fano id=Fano>");
			out.println("<option value=40>---</option>");
			int fano=2008;
			for(int k=0;k<=30;k++){
				fano=fano+1;
				out.println("<option value="+fano+">"+fano+"</option>");
			}
			out.println("</select></td></tr>");
			out.print("<td colspan='4'><div align='center'><label><input type='button' name='btnConsultarE' id='btnConsultarE' value='     CONSULTAR     ' onClick='ConsultarDisp()'></label></div></td></tr>");
						   
		}
		

		if(va.equals("Movimiento")){	
			out.print("<table width='100%' class='style6'><tr><td colspan='6'><div align='center' class='style11' id='cabecera2'>Relación de Movimientos</div></td></tr>");
			
			out.print("<tr><td><label><input name='radiobutton' type='radio' value='radiobutton' id='GeneralConsumo' onClick='Seleccione()' />Reporte Consumo</label></td>");
			out.print("<td><label><input name='radiobutton' type='radio' value='radiobutton' id='Dispensacion' onClick='Seleccione()' />Reporte Dispensacion</label></td>");
			out.print("<td><label><input name='radiobutton' type='radio' value='radiobutton' id='Movimiento' onClick='Seleccione()'checked  />Reporte por Tipo deMovimiento</label></td>");	
			out.print("<td><label><input name='radiobutton' type='radio' value='radiobutton' id='Producto' onClick='Seleccione()' />Reporte por Producto</label></td>");
			out.print("<td><label><input name='radiobutton' type='radio' value='radiobutton' id='Ordenes' onClick='Seleccione()' />Reporte de Ordenes</label></td></tr>");
			out.print("<table width='100%' class='style6'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Reporte por Tipo de Movimiento</div></td></tr>");
			
			out.print("<tr><td width='12%'>Seleccione Bodega</td><td><label><select name='cmbBodega' id='cmbBodega'><option value='Seleccione'>Seleccione</option>");
			out.print("<option value='todas'>TODAS LAS BODEGAS</option>");
			rs=me.ObtenerBodegas(user);
			try { 
				while(rs.next()){
				out.print("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
				} 
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("</select></label></td>");	
			 
			out.print("<td>Seleccione Tipo de Movimiento:  </td><td><select name='cmdmov' id='cmdmov'> <option value='Seleccione'>Seleccione</option>" +
					"<option value='1'>TODOS</option>" +
					"<option value='2'>ENTRADAS</option>" +
					"<option value='4'>SALIDAS</option>" +
					"<option value='3'>TRASLADOS</option></select></td></tr>");
			out.print("<tr><td> Fecha Inicial : </td><td>");
			int dia=0;
			out.println("<select name=Idia id=Idia>");
			out.println("<option value=40>---</option>");
			for(int i=0;i<=30;i++){
			dia=dia+1;
			out.println("<option value="+dia+">"+dia+"</option>");
			}
			
			out.println("</select>");
			out.println("<select name=Imes id=Imes>");
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
			
			out.println("<select name=Iano id=Iano>");
			out.println("<option value=40>---</option>");
			int ano=2008;
			for(int k=0;k<=30;k++){
				ano=ano+1;
				out.println("<option value="+ano+">"+ano+"</option>");
			}
			out.println("</select></td>");	
			out.println("<td>Fecha Final : </td><td>");
			int fdia=0;
			out.println("<select name=Fdia id=Fdia>");
			out.println("<option value=40>---</option>");
			for(int i=0;i<=30;i++){
			fdia=fdia+1;
			out.println("<option value="+fdia+">"+fdia+"</option>");
			}
			
			out.println("</select>");
			out.println("<select name=Fmes id=Fmes>");
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
			
			
			out.println("<select name=Fano id=Fano>");
			out.println("<option value=40>---</option>");
			int fano=2008;
			for(int k=0;k<=30;k++){
				fano=fano+1;
				out.println("<option value="+fano+">"+fano+"</option>");
			}
			out.println("</select></td></tr>");
			out.print("<td colspan='4'><div align='center'><label><input type='button' name='btnConsultarE' id='btnConsultarE' value='     CONSULTAR     ' onClick='ConsultarMov()'></label></div></td></tr>");
						   
		}



	if(va.equals("RepConGen")){
			String tano=request.getParameter("tano");
			String tmeses=request.getParameter("tmeses");
			String tdia=request.getParameter("tdia");
			String ftano=request.getParameter("ftano");
			String ftmeses=request.getParameter("ftmeses");
			String ftdia=request.getParameter("ftdia");
			//String ftent=request.getParameter("ftent");
			String Fechai=tano+"-"+tmeses+"-"+tdia;
			String Fechaf=ftano+"-"+ftmeses+"-"+ftdia; 
			
			rs=me.BuscarEmpresa();
			String nombreE="";
			try {
				if(rs.next()){
					nombreE=rs.getString(2);
				}rs.getStatement().getConnection().close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			out.println("<table  class='contpre' width='90%' >");
			out.println("<tr><td colspan='14'><br><b>"+nombreE+" - Informe del "+Fechai+" al "+Fechaf+"</b></td></tr>");
			
			
			rs=me.BusCodMedConsumo(Fechai,Fechaf);
			String codmed= null;
			try {
				out.println("<tr><td colspan='14'><br><b>TODAS LAS BODEGAS - TODOS LOS MEDICAMENTOS Y DISPOSITIVOS</b></td></tr>");
				out.println("<tr bgcolor='#CCE6FD'><b><td><br>Nombre del Producto</td><td><br>Lote</td><td><br>Identificacion Pac.</td><td><br>Nombre Paciente</td><td><br>Cantidad Formulada</td><td><br>Cantidad Dispensada</td><td><br>Formulada Por </td><td><br>Profesion</td><td><br>Registro Medico</td><td><br>Fecha de Formulacion</td><td><br>Hora de Formulacion</td><td><br>Fecha de Dispensacion </td><td><br>Hora de Dispensacion </td></b></tr> ");
				while(rs.next()){
					codmed=rs.getString(1);
					System.out.println("valor de codmed"+codmed);
					rs1=me.BusDetMedConsumo(codmed,Fechai,Fechaf);
					int formulado=0;
					int dispensado=0;
					int form;
					int disp;
					int cont=0;
					int promd;
					int promf;
					while(rs1.next()){
						out.println("<tr class='rep'><td>"+rs1.getString(1)+"</td>");//medicamento
						out.println("<td>"+rs1.getString(4)+"</td>");//lote
						out.println("<td>"+rs1.getString(7)+"</td>");//Identificacion Paciente
						out.println("<td>"+rs1.getString(6)+"</td>");//paciente
						out.println("<td>"+rs1.getString(2)+"</td>");//cantidad formulada
						out.println("<td>"+rs1.getString(3)+"</td>");//cantidad disp
						out.println("<td>"+rs1.getString(5)+"</td>");//formulada por 
						out.println("<td>"+rs1.getString(11)+"</td>");//profesion
						out.println("<td>"+rs1.getString(12)+"</td>");//registro medico
						out.println("<td>"+rs1.getString(9)+"</td>");//fecha formulacion
						out.println("<td>"+rs1.getString(13)+"</td>");//hora form
						out.println("<td>"+rs1.getString(10)+"</td>");//fecha dispen 
						out.println("<td>"+rs1.getString(14)+"</td>");//hora disp
						out.println("</tr>");
						form=rs1.getInt(2);
						disp=rs1.getInt(3);
						formulado=form+formulado;
						dispensado=disp+dispensado;
						cont=cont+1;
					}
					rs1.getStatement().getConnection().close();
					if(cont!=0){
					promf=(formulado/cont);
					promd=(dispensado/cont);
					out.println("<tr bgcolor='#F1FAFC' ><td colspan='7' ><hr><b>Sumatoria Formulado : "+formulado+"&nbsp; Promedio Formulado : "+promf+"</b><hr></td>");
					out.println("<td colspan='7' ><hr><b> Sumatoria Dispensado :&nbsp;"+dispensado+"&nbsp; Promedio Dispensado :&nbsp;"+promd+"</b><hr></td></tr>");
					}else{
						
						//out.println("<tr><td><font color='red'>No hay datos para los parametros Dados "+codmed+"</font></td></tr>");
					}
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//out.println("<tr><td align='center'><input type='button' name='btnGenPDF' value='Generar PDF' onClick='verReporteGeneral("+tano+","+tmeses+","+tdia+","+ftano+","+ftmeses+","+ftdia+")' /></td></tr>'");
			out.println("</table>");
			System.out.println("Terminando RepConGen ");
		}
		
		
		if(va.equals("RepTipoMovT")){
			String tano=request.getParameter("tano");
			String tmeses=request.getParameter("tmeses");
			String tdia=request.getParameter("tdia");
			String ftano=request.getParameter("ftano");
			String ftmeses=request.getParameter("ftmeses");
			String ftdia=request.getParameter("ftdia");
			//String ftent=request.getParameter("ftent");
			String Fechai=tano+"-"+tmeses+"-"+tdia;
			String Fechaf=ftano+"-"+ftmeses+"-"+ftdia; 
			String mov=request.getParameter("mov");
			String bodega=request.getParameter("bodega");
			rs=me.BuscarEmpresa();
			String nombreE="";
			try {
				if(rs.next()){
					nombreE=rs.getString(2);
				}rs.getStatement().getConnection().close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			System.out.println("valor de bodega"+bodega);
			out.println("<table  class='contpre' width='90%' >");
			out.println("<tr><td colspan='11'><br><b>"+nombreE+" - Informe del "+Fechai+" al "+Fechaf+"</b></td></tr>");
					out.println("<tr><td colspan='11' ><br>");
					if(bodega.equals("todas")){
						out.println("<b>TODAS LAS BODEGAS - TODOS LOS MOVIMIENTOS</b>");
					}else{
						rs2=me.ObtenerBodegaso(bodega);
						try {
							if(rs2.first()){
								out.println("<br><b>"+rs2.getString(2)+" - TODOS LOS MOVIMIENTOS</b>");
							}
							rs2.getStatement().getConnection().close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
			out.println("</td></tr>");
			rs=me.BuscarMovFarmacia(Fechai,Fechaf,bodega);
			String codmed= null;
			try {
				
				out.println("<tr bgcolor='#CCE6FD'><b><td><br>Consecutivo</td><td><br>Producto</td><td><br>Lote</td><td><br>Cantidad</td><td><br>Usuario</td><td><br>Descripcion</td><td><br>Bodega</td><td><br>Soporte</td><td><br>Concepto</td><td><br>Fecha de Registro</td><td><br>Hora de Registro</td></b></tr> ");
				while(rs.next()){
					
						out.println("<tr class='rep'><td>");
								if(rs.getString(2).equals("0")){
									out.println(rs.getString(1));
								}else{
									out.println(rs.getString(2));//consecutivo
								}
								out.println("</td>");
						out.println("<td>"+rs.getString(9)+"</td>");//medicamento
						out.println("<td>"+rs.getString(10)+"</td>");//lote
						if(rs.getString(14).equals("1")){
							out.println("<td>"+rs.getString(6)+"</td>");
						}else{
								out.println("<td>"+rs.getString(5)+"</td>");
							
						}
						//cantidad
						
						out.println("<td>"+rs.getString(4)+"</td>");//usuario
						out.println("<td>"+rs.getString(7)+"</td>");//descrip
						if(rs.getString(17).equals("13")){
							rs3=me.BuscarBodega(rs.getString(1));
							if(rs3.next()){
								out.println("<td>"+rs.getString(8)+" --> "+rs3.getString(1)+" </td>");//bodega
							}rs3.getStatement().getConnection().close();
						}else{
							out.println("<td>"+rs.getString(8)+"</td>");//bodega
						}
						out.println("<td>"+rs.getString(18)+"</td>");//soporte
						out.println("<td>"+rs.getString(16)+"</td>");////concepto
						out.println("<td>"+rs.getString(11)+"</td>");//fecha dispen 	
						out.println("<td>"+rs.getString(15)+"</td>");//Hora disp
				}
				rs.getStatement().getConnection().close();
				rs3=me.BuscarMovDev(Fechai, Fechaf, bodega);
				while(rs3.next()){
					out.println("<tr class='rep' ><td>DEV"+rs3.getString(1)+"</td>");
					out.println("<td>"+rs3.getString(5)+"</td>");
					out.println("<td>"+rs3.getString(6)+"</td>");
					out.println("<td>"+rs3.getString(2)+"</td>");
					out.println("<td>"+rs3.getString(4)+"</td>");
					out.println("<td>"+rs3.getString(3)+"</td>");
					out.println("<td>"+rs3.getString(11)+"</td>");
					out.println("<td>"+rs3.getString(16)+"</td>");
					out.println("<td>"+rs3.getString(15)+"</td>");
					out.println("<td>"+rs3.getString(7)+"</td>");
					out.println("<td>"+rs3.getString(14)+"</td></tr>");
					
				}
				rs3.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//out.println("<tr><td align='center'><input type='button' name='btnGenPDF' value='Generar PDF' onClick='verReporteGeneral("+tano+","+tmeses+","+tdia+","+ftano+","+ftmeses+","+ftdia+")' /></td></tr>'");
			out.println("</table>");
		}

		


		if(va.equals("RepTipoEnt_Tras")){
			String tano=request.getParameter("tano");
			String tmeses=request.getParameter("tmeses");
			String tdia=request.getParameter("tdia");
			String ftano=request.getParameter("ftano");
			String ftmeses=request.getParameter("ftmeses");
			String ftdia=request.getParameter("ftdia");
			//String ftent=request.getParameter("ftent");
			String Fechai=tano+"-"+tmeses+"-"+tdia;
			String Fechaf=ftano+"-"+ftmeses+"-"+ftdia; 
			String mov=request.getParameter("mov");
			String bodega=request.getParameter("bodega");
			
			rs=me.BuscarEmpresa();
			String nombreE="";
			try {
				if(rs.next()){
					nombreE=rs.getString(2);
				}rs.getStatement().getConnection().close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			System.out.println("valor de bodega"+bodega);
			out.println("<table  class='contpre' width='90%' >");
			out.println("<tr><td colspan='11'><br><b>"+nombreE+" - Informe del "+Fechai+" al "+Fechaf+"</b></td></tr>");
			out.println("<tr><td colspan='11'>");
					if((bodega.equals("todas"))&&(mov.equals("2"))){
						out.println("<br><b>TODAS LAS BODEGAS - MOVIMIENTOS DE ENTRADA</b>");
					}else{
						if((bodega.equals("todas"))&&(mov.equals("3"))){
							out.println("<br><b>TODAS LAS BODEGAS - MOVIMIENTOS DE TRASLADOS</b>");
						}else{
							if((bodega!="todas")&&(mov.equals("2"))){
							rs2=me.ObtenerBodegaso(bodega);
							try {
								if(rs2.first()){
								out.println("<br><b>BODEGA : "+rs2.getString(2)+" - MOVIMIEMNTOS DE ENTRADA</b>");
								}
								rs2.getStatement().getConnection().close();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}else{
							if((bodega!="todas")&&(mov.equals("3"))){
								rs3=me.ObtenerBodegaso(bodega);
								try {
									if(rs3.first()){
									out.println("<br><b>BODEGA : "+rs3.getString(2)+" - MOVIMIENTOS DE TRASLADO</b>");
									}
									rs3.getStatement().getConnection().close();
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}
						}
					}
			out.println("</td></tr>");
			rs=me.BuscarMovEnt_Tras(Fechai,Fechaf,bodega,mov);
			String codmed= null;
			try {
				//out.println("<br><b>TODAS LAS BODEGAS - TODOS LOS MEDICAMENTOS Y DISPOSITIVOS</b>");
				out.println("<tr bgcolor='#CCE6FD'><b><td><br>Consecutivo</td><td><br>Producto</td><td><br>Lote</td><td><br>Cantidad</td><td><br>Usuario</td><td><br>Descripcion</td><td><br>Bodega</td><td><br>Soporte</td><td><br>Concepto</td><td><br>Fecha de Registro</td><td><br>Hora de Registro</td></b></tr> ");
				while(rs.next()){
					
						out.println("<tr class='rep'><td>");
								if(rs.getString(2).equals("0")){
									out.println(rs.getString(1));
								}else{
									out.println(rs.getString(2));//consecutivo
								}
								out.println("</td>");
						out.println("<td>"+rs.getString(9)+"</td>");//medicamento
						out.println("<td>"+rs.getString(10)+"</td>");//lote
						if(rs.getString(14).equals("1")){
							out.println("<td>"+rs.getString(6)+"</td>");
						}else{
								out.println("<td>"+rs.getString(5)+"</td>");
							
						}
						//cantidad
						
						out.println("<td>"+rs.getString(4)+"</td>");//paciente
						out.println("<td>"+rs.getString(7)+"</td>");//cantidad formulada
						if(rs.getString(18).equals("13")){//tipo de movimiento traslado
							rs3=me.BuscarBodega(rs.getString(17));
							System.out.println(" Valor de codigo de inventario "+rs.getString(17));
							if(rs3.next()){
							out.println("<td>"+rs.getString(8)+" --> "+rs3.getString(1)+" </td>");
							}rs3.getStatement().getConnection().close();
						}else{
							out.println("<td>"+rs.getString(8)+"</td>");// bodega
						}
						out.println("<td>"+rs.getString(19)+"</td>");// # soporte
						out.println("<td>"+rs.getString(16)+"</td>");//concepto
						out.println("<td>"+rs.getString(11)+"</td>");//fecha 
						out.println("<td>"+rs.getString(15)+"</td></tr>");//hora 	
				}
				rs.getStatement().getConnection().close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//out.println("<tr><td align='center'><input type='button' name='btnGenPDF' value='Generar PDF' onClick='verReporteGeneral("+tano+","+tmeses+","+tdia+","+ftano+","+ftmeses+","+ftdia+")' /></td></tr>'");
			out.println("</table>");
		}

				
		



if(va.equals("RepEntProd")){
			String tano=request.getParameter("tano");
			String tmeses=request.getParameter("tmeses");
			String tdia=request.getParameter("tdia");
			String ftano=request.getParameter("ftano");
			String ftmeses=request.getParameter("ftmeses");
			String ftdia=request.getParameter("ftdia");
			//String ftent=request.getParameter("ftent");
			String Fechai=tano+"-"+tmeses+"-"+tdia;
			String Fechaf=ftano+"-"+ftmeses+"-"+ftdia; 
			String cm=request.getParameter("cm");
			String bodega=request.getParameter("bodega");
			
			rs=me.BuscarEmpresa();
			String nombreE="";
			try {
				if(rs.next()){
					nombreE=rs.getString(2);
				}rs.getStatement().getConnection().close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			System.out.println("valor de bodega"+bodega);
			out.println("<table  class='contpre' width='90%' >");
			out.println("<tr><td colspan='11'><br><b>"+nombreE+" - Informe del "+Fechai+" al "+Fechaf+"</b></td></tr>");
			out.println("<tr><td colspan='11'>");
			if(bodega.equals("todas")){
				out.println("<br><b>TODAS LAS BODEGAS </b>");
			}else{				
						rs3=me.ObtenerBodegaso(bodega);
						try {
							if(rs3.first()){
							out.println("<br><b>BODEGA : "+rs3.getString(2)+"</b>");
							}
							rs3.getStatement().getConnection().close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
			
			out.println("</td></tr>");
			
			rs=me.BuscarEntProd(Fechai,Fechaf,bodega,cm);
			String codmed= null;
			try {
				//out.println("<br><b>TODAS LAS BODEGAS - TODOS LOS MEDICAMENTOS Y DISPOSITIVOS</b>");
				out.println("<tr bgcolor='#CCE6FD'><b><td><br>Consecutivo</td><td><br>Producto</td><td><br>Lote</td><td><br>Cantidad</td><td><br>Usuario</td><td><br>Descripcion</td><td><br>Bodega</td><td><br>Soporte</td><td><br>Concepto</td><td><br>Fecha de Registro</td><td><br>Hora de Registro</td></b></tr> ");
				while(rs.next()){
					
						out.println("<tr class='rep' ><td>");
								if(rs.getString(2).equals("0")){
									out.println(rs.getString(1));
								}else{
									out.println(rs.getString(2));//consecutivo
								}
								out.println("</td>");
						out.println("<td>"+rs.getString(9)+"</td>");//medicamento
						out.println("<td>"+rs.getString(10)+"</td>");//lote
						if(rs.getString(14).equals("1")){
							out.println("<td>"+rs.getString(6)+"</td>");
						}else{
								out.println("<td>"+rs.getString(5)+"</td>");
							
						}
						//cantidad
						
						out.println("<td>"+rs.getString(4)+"</td>");//paciente
						out.println("<td>"+rs.getString(7)+"</td>");//cantidad formulada
						out.println("<td>"+rs.getString(8)+"</td>");//cantidad disp
						out.println("<td>"+rs.getString(16)+"</td>");//soporte
						out.println("<td>"+rs.getString(3)+"</td>");//concepto
						out.println("<td>"+rs.getString(11)+"</td>");//fecha dispen 
						out.println("<td>"+rs.getString(15)+"</td>");//hora dispen
						out.println("</tr>");
				}
				rs.getStatement().getConnection().close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//out.println("<tr><td align='center'><input type='button' name='btnGenPDF' value='Generar PDF' onClick='verReporteGeneral("+tano+","+tmeses+","+tdia+","+ftano+","+ftmeses+","+ftdia+")' /></td></tr>'");
			out.println("</table>");
		}


		
		
		if(va.equals("RepSalProd")){
			String tano=request.getParameter("tano");
			String tmeses=request.getParameter("tmeses");
			String tdia=request.getParameter("tdia");
			String ftano=request.getParameter("ftano");
			String ftmeses=request.getParameter("ftmeses");
			String ftdia=request.getParameter("ftdia");
			//String ftent=request.getParameter("ftent");
			String Fechai=tano+"-"+tmeses+"-"+tdia;
			String Fechaf=ftano+"-"+ftmeses+"-"+ftdia; 
			String cm=request.getParameter("cm");
			String bodega=request.getParameter("bodega");
			
			rs=me.BuscarEmpresa();
			String nombreE="";
			try {
				if(rs.next()){
					nombreE=rs.getString(2);
				}rs.getStatement().getConnection().close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			System.out.println("valor de bodega"+bodega);
			out.println("<table  class='contpre' width='90%' >");
			out.println("<tr><td colspan='11'><br><b>"+nombreE+" - Informe del "+Fechai+" al "+Fechaf+"</b></td></tr>");
			out.println("<tr><td colspan='11'> ");
			if(bodega.equals("todas")){
				out.println("<br><b>TODAS LAS BODEGAS </b>");
			}else{				
						rs3=me.ObtenerBodegaso(bodega);
						try {
							if(rs3.first()){
							out.println("<br><b>BODEGA : "+rs3.getString(2)+"</b>");
							}
							rs3.getStatement().getConnection().close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
			
			out.println("</td></tr>");
			
			rs=me.BuscarSalProd(Fechai,Fechaf,bodega,cm);
			String codmed= null;
			try {
				//out.println("<br><b>TODAS LAS BODEGAS - TODOS LOS MEDICAMENTOS Y DISPOSITIVOS</b>");
				out.println("<tr bgcolor='#CCE6FD'><b><td><br>Consecutivo</td><td><br>Producto</td><td><br>Lote</td><td><br>Cantidad</td><td><br>Usuario</td><td><br>Descripcion</td><td><br>Bodega</td><td><br>Soporte</td><td><br>Concepto</td><td><br>Fecha de Registro</td><td><br>Hora de Registro</td></b></tr> ");
				while(rs.next()){
					
						out.println("<tr class='rep'><td>");
								if(rs.getString(2).equals("0")){
									out.println(rs.getString(1));
								}else{
									out.println(rs.getString(2));//consecutivo
								}
								out.println("</td>");
						out.println("<td>"+rs.getString(9)+"</td>");//medicamento
						out.println("<td>"+rs.getString(10)+"</td>");//lote
						if(rs.getString(14).equals("1")){
							out.println("<td>"+rs.getString(6)+"</td>");
						}else{
								out.println("<td>"+rs.getString(5)+"</td>");
							
						}
						//cantidad
						
						out.println("<td>"+rs.getString(4)+"</td>");//paciente
						out.println("<td>"+rs.getString(7)+"</td>");//cantidad formulada
						out.println("<td>"+rs.getString(8)+"</td>");//cantidad disp
						out.println("<td>"+rs.getString(16)+"</td>");//soporte
						out.println("<td>"+rs.getString(3)+"</td>");//concepto
						out.println("<td>"+rs.getString(11)+"</td>");//fecha dispen 
						out.println("<td>"+rs.getString(15)+"</td>");//hora dispe
				}
				rs.getStatement().getConnection().close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//out.println("<tr><td align='center'><input type='button' name='btnGenPDF' value='Generar PDF' onClick='verReporteGeneral("+tano+","+tmeses+","+tdia+","+ftano+","+ftmeses+","+ftdia+")' /></td></tr>'");
			out.println("</table>");
		}


		

if(va.equals("RepTrasProd")){
			String tano=request.getParameter("tano");
			String tmeses=request.getParameter("tmeses");
			String tdia=request.getParameter("tdia");
			String ftano=request.getParameter("ftano");
			String ftmeses=request.getParameter("ftmeses");
			String ftdia=request.getParameter("ftdia");
			//String ftent=request.getParameter("ftent");
			String Fechai=tano+"-"+tmeses+"-"+tdia;
			String Fechaf=ftano+"-"+ftmeses+"-"+ftdia; 
			String cm=request.getParameter("cm");
			String bodega=request.getParameter("bodega");
			
			rs=me.BuscarEmpresa();
			String nombreE="";
			try {
				if(rs.next()){
					nombreE=rs.getString(2);
				}rs.getStatement().getConnection().close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			System.out.println("valor de bodega"+bodega);
			out.println("<table  class='contpre' width='90%' >");
			out.println("<tr><td colspan='11'><br><b>"+nombreE+" - Informe del "+Fechai+" al "+Fechaf+"</b></td></tr>");
			out.println("<tr><td colspan='11' >");
			if(bodega.equals("todas")){
				out.println("<br><b>TODAS LAS BODEGAS </b>");
			}else{				
						rs3=me.ObtenerBodegaso(bodega);
						try {
							if(rs3.first()){
							out.println("<br><b>BODEGA : "+rs3.getString(2)+"</b>");
							}
							rs3.getStatement().getConnection().close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
			
			out.println("</td></tr>");
			
			rs=me.BuscarTrasProd(Fechai,Fechaf,bodega,cm);
			String codmed= null;
			try {
				//out.println("<br><b>TODAS LAS BODEGAS - TODOS LOS MEDICAMENTOS Y DISPOSITIVOS</b>");
				out.println("<tr bgcolor='#CCE6FD'><b><td><br>Consecutivo</td><td><br>Producto</td><td><br>Lote</td><td><br>Cantidad</td><td><br>Usuario</td><td><br>Descripcion</td><td><br>Bodega</td><td><br>Soporte</td><td><br>Concepto</td><td><br>Fecha de Registro</td><td><br>Hora de Registro</td></b></tr> ");
				while(rs.next()){
					
						out.println("<tr class='rep' ><td>");
								if(rs.getString(2).equals("0")){
									out.println(rs.getString(1));
								}else{
									out.println(rs.getString(2));//consecutivo
								}
								out.println("</td>");
						out.println("<td>"+rs.getString(9)+"</td>");//medicamento
						out.println("<td>"+rs.getString(10)+"</td>");//lote
						if(rs.getString(14).equals("1")){
							out.println("<td>"+rs.getString(6)+"</td>");
						}else{
								out.println("<td>"+rs.getString(5)+"</td>");
							
						}
						//cantidad
						
						out.println("<td>"+rs.getString(4)+"</td>");//paciente
						out.println("<td>"+rs.getString(7)+"</td>");//cantidad formulada
						out.println("<td>"+rs.getString(8)+"</td>");//cantidad disp
						out.println("<td>"+rs.getString(16)+"</td>");//soporte
						out.println("<td>"+rs.getString(3)+"</td>");//concepto
						out.println("<td>"+rs.getString(11)+"</td>");//fecha 
						out.println("<td>"+rs.getString(15)+"</td");//hora
				}
				rs.getStatement().getConnection().close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//out.println("<tr><td align='center'><input type='button' name='btnGenPDF' value='Generar PDF' onClick='verReporteGeneral("+tano+","+tmeses+","+tdia+","+ftano+","+ftmeses+","+ftdia+")' /></td></tr>'");
			out.println("</table>");
		}





	if(va.equals("RepDevProd")){
			String tano=request.getParameter("tano");
			String tmeses=request.getParameter("tmeses");
			String tdia=request.getParameter("tdia");
			String ftano=request.getParameter("ftano");
			String ftmeses=request.getParameter("ftmeses");
			String ftdia=request.getParameter("ftdia");
			//String ftent=request.getParameter("ftent");
			String Fechai=tano+"-"+tmeses+"-"+tdia;
			String Fechaf=ftano+"-"+ftmeses+"-"+ftdia; 
			String cm=request.getParameter("cm");
			String bodega=request.getParameter("bodega");
			
			rs=me.BuscarEmpresa();
			String nombreE="";
			try {
				if(rs.next()){
					nombreE=rs.getString(2);
				}rs.getStatement().getConnection().close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			System.out.println("valor de bodega"+bodega);
			out.println("<table  class='contpre' width='90%' >");
			out.println("<tr><td colspan='11'><br><b>"+nombreE+" - Informe del "+Fechai+" al "+Fechaf+"</b></td></tr>");
			out.println("<tr><td colspan='11'>");
			if(bodega.equals("todas")){
				out.println("<br><b>TODAS LAS BODEGAS </b>");
			}else{				
						rs3=me.ObtenerBodegaso(bodega);
						try {
							if(rs3.first()){
							out.println("<br><b>BODEGA : "+rs3.getString(2)+"</b>");
							}
							rs3.getStatement().getConnection().close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
			
			out.println("</td></tr>");
			
			rs=me.BuscarDevProd(Fechai,Fechaf,bodega,cm);
			String codmed= null;
			try {
				//out.println("<br><b>TODAS LAS BODEGAS - TODOS LOS MEDICAMENTOS Y DISPOSITIVOS</b>");
				out.println("<tr bgcolor='#CCE6FD'><b><td><br>Consecutivo</td><td><br>Producto</td><td><br>Lote</td><td><br>Cantidad</td><td><br>Usuario</td><td><br>Descripcion</td><td><br>Bodega</td><td><br>Soporte</td><td><br>Concepto</td><td><br>Fecha de Registro</td><td><br>Hora de Registro</td></b></tr> ");
				while(rs.next()){
					
					out.println("<tr class='rep'><td>DEV"+rs.getString(1)+"</td>");
					out.println("<td>"+rs.getString(5)+"</td>");
					out.println("<td>"+rs.getString(6)+"</td>");
					out.println("<td>"+rs.getString(2)+"</td>");
					out.println("<td>"+rs.getString(4)+"</td>");
					out.println("<td>"+rs.getString(3)+"</td>");
					out.println("<td>"+rs.getString(11)+"</td>");
					out.println("<td>"+rs.getString(15)+"</td>");//soporte
					out.println("<td>"+rs.getString(3)+"</td>");//concepto
					out.println("<td>"+rs.getString(7)+"</td>");//fecha dev
					out.println("<td>"+rs.getString(14)+"</td>");// hora dev
					out.println("</tr>");
				}
				rs.getStatement().getConnection().close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//out.println("<tr><td align='center'><input type='button' name='btnGenPDF' value='Generar PDF' onClick='verReporteGeneral("+tano+","+tmeses+","+tdia+","+ftano+","+ftmeses+","+ftdia+")' /></td></tr>'");
			out.println("</table>");
		}
			
		
		
		
		
		


if(va.equals("RepTipoMovSal")){
			String tano=request.getParameter("tano");
			String tmeses=request.getParameter("tmeses");
			String tdia=request.getParameter("tdia");
			String ftano=request.getParameter("ftano");
			String ftmeses=request.getParameter("ftmeses");
			String ftdia=request.getParameter("ftdia");
			//String ftent=request.getParameter("ftent");
			String Fechai=tano+"-"+tmeses+"-"+tdia;
			String Fechaf=ftano+"-"+ftmeses+"-"+ftdia; 
			String mov=request.getParameter("mov");
			String bodega=request.getParameter("bodega");
			
			rs=me.BuscarEmpresa();
			String nombreE="";
			try {
				if(rs.next()){
					nombreE=rs.getString(2);
				}rs.getStatement().getConnection().close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			//System.out.println("valor de bodega"+bodega);
			out.println("<table  class='contpre' width='90%' >");
			out.println("<tr><td colspan='11'><br><b>"+nombreE+" - Informe del "+Fechai+" al "+Fechaf+"</b></td></tr>");
			out.println("<tr><td colspan='11' >");
			if((bodega.equals("todas"))&&(mov.equals("4"))){
				out.println("<br><b>TODAS LAS BODEGAS - MOVIMIENTOS DE SALIDA</b>");
			}else{
				if((bodega!="todas")&&(mov.equals("4"))){
					rs2=me.ObtenerBodegaso(bodega);
					try {
						if(rs2.first()){
						out.println("<br><b>BODEGA : "+rs2.getString(2)+" - MOVIMIENTOS DE SALIDA</b>");
						}
						rs2.getStatement().getConnection().close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				
				}
			}
			out.println("</td></tr>");
			rs=me.BuscarMovSal(Fechai,Fechaf,bodega,mov);
			String codmed= null;
			try {
				//out.println("<br><b>TODAS LAS BODEGAS - TODOS LOS MEDICAMENTOS Y DISPOSITIVOS</b>");
				out.println("<tr bgcolor='#CCE6FD'><b><td><br>Consecutivo</td><td><br>Producto</td><td><br>Lote</td><td><br>Cantidad</td><td><br>Usuario</td><td><br>Descripcion</td><td><br>Bodega</td><td><br>Soporte</td><td><br>Concepto</td><td><br>Fecha de Registro</td><td><br> Hora de Registro</td></b></tr> ");
				while(rs.next()){
					
						out.println("<tr class='rep' ><td>");
								if(rs.getString(2).equals("0")){
									out.println(rs.getString(1));
								}else{
									out.println(rs.getString(2));//consecutivo
								}
								out.println("</td>");
						out.println("<td>"+rs.getString(9)+"</td>");//medicamento
						out.println("<td>"+rs.getString(10)+"</td>");//lote
						if(rs.getString(14).equals("1")){
							out.println("<td>"+rs.getString(6)+"</td>");
						}else{
								out.println("<td>"+rs.getString(5)+"</td>");
							
						}
						//cantidad
						
						out.println("<td>"+rs.getString(4)+"</td>");//paciente
						out.println("<td>"+rs.getString(7)+"</td>");//cantidad formulada
						out.println("<td>"+rs.getString(8)+"</td>");//cantidad disp
						out.println("<td>"+rs.getString(16)+"</td>");//soporte
						out.println("<td>"+rs.getString(3)+"</td>");//concepto
						out.println("<td>"+rs.getString(11)+"</td>");//fecha de registro
						out.println("<td>"+rs.getString(15)+"</td></tr>");//hora de registro
				}
				rs.getStatement().getConnection().close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//out.println("<tr><td align='center'><input type='button' name='btnGenPDF' value='Generar PDF' onClick='verReporteGeneral("+tano+","+tmeses+","+tdia+","+ftano+","+ftmeses+","+ftdia+")' /></td></tr>'");
			out.println("</table>");
		}


		


if(va.equals("RepDispPac")){
			String tano=request.getParameter("tano");
			String tmeses=request.getParameter("tmeses");
			String tdia=request.getParameter("tdia");
			String ftano=request.getParameter("ftano");
			String ftmeses=request.getParameter("ftmeses");
			String ftdia=request.getParameter("ftdia");
			//String ftent=request.getParameter("ftent");
			String Fechai=tano+"-"+tmeses+"-"+tdia;
			String Fechaf=ftano+"-"+ftmeses+"-"+ftdia; 
			String bodega="nada";
			String rep=request.getParameter("rep");
			System.out.println("rep"+rep);
			int sql=1;
			if(rep.equals("3")){
				sql=3;
			}
			System.out.println("sql"+sql);
			String med=request.getParameter("cm");
			rs=me.BuscarEmpresa();
			String nombreE="";
			try {
				if(rs.next()){
					nombreE=rs.getString(2);
				}rs.getStatement().getConnection().close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			System.out.println("valor de bodega"+bodega);
			out.println("<table  class='contpre' width='100%' >");
			out.println("<tr><td colspan='16' align='center' ><br><b>"+nombreE+" - Informe del "+Fechai+" al "+Fechaf+"</b></td></tr>");
			rs=me.BusRepDisPac(Fechai,Fechaf,sql,bodega,med);
			//String codmed= null;
			try {
				out.println("<tr><td colspan='16' align='center'><b>TODAS LAS BODEGAS</b></td></tr>");
					//out.println("<p align='left'><br><b>TODAS LAS BODEGAS </b></p>");
			
				out.println("<tr bgcolor='#CCE6FD'><b><td><br>No. Formula</td><td><br>Producto</td><td><br>Lote</td><td><br>DocumentPac.</td><td><br>Nombre del Paciente</td><td><br>Cant Form</td><td><br>Cant Disp </td><td><br>Cant Dev</td><td><br>Dispensado de</td><td><br>Dirigido a </td><td><br>Fecha Disp</td><td><br>Hora Disp</td><td><br>Usuario Disp</td><td><br>Fecha Dev </td><td><br>Hora Dev</td><td><br>Usuario Dev</td></b></tr> ");
				while(rs.next()){
					
						out.println("<tr class='rep'><td>"+rs.getString(7)+"</td>");//formula
						out.println("<td>"+rs.getString(12)+" "+rs.getString(13)+"</td>");//Nombre medicamento(falta la forma farmaceutica)
						out.println("<td>"+rs.getString(14)+"</td>");//lote
						out.println("<td>"+rs.getString(4)+"-"+rs.getString(5)+"</td>");//doc paciente
						out.println("<td>"+rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+"</td>");//nombre paciente
						out.println("<td>"+rs.getString(16)+"</td>");//cantidad form
						out.println("<td>"+rs.getString(17)+"</td>");//cantidad disp 
						out.println("<td>"+rs.getString(18)+"</td>");//cantidad dev
						out.println("<td>"+rs.getString(19)+"</td>");//dispensado de
						out.println("<td>"+rs.getString(11)+"</td>");//dirigido a 
						out.println("<td>"+rs.getString(8)+"</td>");//fecha de disp
						out.println("<td>"+rs.getString(9)+"</td>");//hora de disp
						rs1=me.BusUsuario(rs.getString(22));
						if(rs1.next()){
							out.println("<td>"+rs1.getString(1)+"</td>");//usuario disp
						}else{
							out.println("<td></td>");
						}rs1.getStatement().getConnection().close();
						out.println("<td>"+rs.getString(20)+"</td>");//fecha de dev
						out.println("<td>"+rs.getString(21)+"</td>");//Hora de dev
						rs1=me.BusUsuario(rs.getString(23));
						if(rs1.next()){
							out.println("<td>"+rs1.getString(1)+"</td>");//usuario dev
						}else{
							out.println("<td></td>");
						}
						rs1.getStatement().getConnection().close();
					}
					
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(sql==1){
			//SI SIRVE PERO HAY QUE MODIFICAR EL JASPER PARA QUE SE VEA MAS ESTETICO out.println("<tr><td align='center'><input type='button' name='btnGenPDF' value='Generar PDF' onClick='verRepDisG("+tano+","+tmeses+","+tdia+","+ftano+","+ftmeses+","+ftdia+")' /></td></tr>'");
			}
			out.println("</table>");
		}


		if(va.equals("RepDispPacB")){
			String tano=request.getParameter("tano");
			String tmeses=request.getParameter("tmeses");
			String tdia=request.getParameter("tdia");
			String ftano=request.getParameter("ftano");
			String ftmeses=request.getParameter("ftmeses");
			String ftdia=request.getParameter("ftdia");
			//String ftent=request.getParameter("ftent");
			String Fechai=tano+"-"+tmeses+"-"+tdia;
			String Fechaf=ftano+"-"+ftmeses+"-"+ftdia; 
			String bodega=request.getParameter("bodega");
			String rep=request.getParameter("rep");
			String med=request.getParameter("cm");
			int sql=2;
			if(rep.equals("4")){
				sql=4;
			}
			rs=me.BuscarEmpresa();
			String nombreE="";
			try {
				if(rs.next()){
					nombreE=rs.getString(2);
				}rs.getStatement().getConnection().close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			System.out.println("valor de bodega"+bodega);
			out.println("<table  class='contpre' width='100%' >");
			out.println("<tr><td colspan='16' align='center' ><br><b>"+nombreE+" - Informe del "+Fechai+" al "+Fechaf+"</b></td></tr>");
			
			
			rs=me.BusRepDisPac(Fechai,Fechaf,sql,bodega,med);
			String codmed= null;
			try {
				out.println("<tr><td colspan='16' align='center'>");
				rs2=me.ObtenerBodegaso(bodega);
				if(rs2.first()){
				out.println("<br><b>BODEGA: "+rs2.getString(2)+"</b>");
				}
				rs2.getStatement().getConnection().close();
				out.println("</td></tr>");
				out.println("<tr bgcolor='#CCE6FD'><b><td><br>No. Formula</td><td><br>Producto</td><td><br>Lote</td><td><br>DocumentPac.</td><td><br>Nombre del Paciente</td><td><br>Cant Form</td><td><br>Cant Disp </td><td><br>Cant Dev</td><td><br>Dispensado de</td><td><br>Dirigido a </td><td><br>Fecha Disp</td><td><br>Hora Disp</td><td><br>Usuario Disp</td><td><br>Fecha Dev </td><td><br>Hora Dev</td><td><br>Usuario Dev</td></b></tr> ");
				while(rs.next()){

						out.println("<tr class='rep'><td>"+rs.getString(7)+"</td>");//formula
						out.println("<td>"+rs.getString(12)+" "+rs.getString(13)+"</td>");//Nombre medicamento(falta la forma farmaceutica)
						out.println("<td>"+rs.getString(14)+"</td>");//lote
						out.println("<td>"+rs.getString(4)+"-"+rs.getString(5)+"</td>");//doc paciente
						out.println("<td>"+rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+"</td>");//nombre paciente
						out.println("<td>"+rs.getString(16)+"</td>");//cantidad form
						out.println("<td>"+rs.getString(17)+"</td>");//cantidad disp 
						out.println("<td>"+rs.getString(18)+"</td>");//cantidad dev
						out.println("<td>"+rs.getString(19)+"</td>");//dispensado de
						out.println("<td>"+rs.getString(11)+"</td>");//dirigido a 
						out.println("<td>"+rs.getString(8)+"</td>");//Fecha de disp
						out.println("<td>"+rs.getString(9)+"</td>");//hora de disp
						rs1=me.BusUsuario(rs.getString(22));
						if(rs1.next()){
							out.println("<td>"+rs1.getString(1)+"</td>");//usuario disp
						}else{
							out.println("<td></td>");
						}rs1.getStatement().getConnection().close();
						out.println("<td>"+rs.getString(20)+"</td>");//fecha de dev
						out.println("<td>"+rs.getString(21)+"</td>");//Hora de dev
						rs1=me.BusUsuario(rs.getString(23));
						if(rs1.next()){
							out.println("<td>"+rs1.getString(1)+"</td>");//usuario dev
						}else{
							out.println("<td></td>");
						}
						rs1.getStatement().getConnection().close();
						out.println("</tr>");
					}
					
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(sql==2){
			// SI FUNCIONA out.println("<tr><td align='center'><input type='button' name='btnGenPDF' value='Generar PDF' onClick='verRepDisGB("+tano+","+tmeses+","+tdia+","+ftano+","+ftmeses+","+ftdia+","+bodega+")' /></td></tr>'");
			}
			out.println("</table>");
		}
		
		



	if(va.equals("RepConGenMedCont")){
			String tano=request.getParameter("tano");
			String tmeses=request.getParameter("tmeses");
			String tdia=request.getParameter("tdia");
			String ftano=request.getParameter("ftano");
			String ftmeses=request.getParameter("ftmeses");
			String ftdia=request.getParameter("ftdia");
			//String ftent=request.getParameter("ftent");
			String Fechai=tano+"-"+tmeses+"-"+tdia;
			String Fechaf=ftano+"-"+ftmeses+"-"+ftdia; 
			
			
			
			rs=me.BuscarEmpresa();
			String nombreE="";
			try {
				if(rs.next()){
					nombreE=rs.getString(2);
				}rs.getStatement().getConnection().close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		
			out.println("<table  class='contpre' width='90%' >");
			out.println("<tr><td colspan='14' align='center'><br><b>"+nombreE+" - Informe del "+Fechai+" al "+Fechaf+"</b></td></tr>");
			
			
			rs=me.BusCodMedConsumoMedCont(Fechai,Fechaf);
			String codmed= null;
			try {
				out.println("<tr><td colspan='14' align='center'>");
				out.println("<br><b>TODAS LAS BODEGAS - MEDICAMENTOS DE CONTROL</b>");
				out.println("<td></tr>");
				out.println("<tr bgcolor='#CCE6FD'><b><td><br>Nombre del Producto</td><td><br>Lote</td><td><br>Identificacion Pac.</td><td><br>Nombre Paciente</td><td><br>Cantidad Formulada</td><td><br>Cantidad Dispensada</td><td><br>Formulada Por </td><td><br>Profesion</td><td><br>Registro Medico</td><td><br>Fecha de Formulacion</td><td><br>Hora de Formulacion</td><td><br>Fecha de Dispensacion </td><td><br>Hora de Dispensacion</td></b></tr> ");
				while(rs.next()){
					codmed=rs.getString(1);
					rs1=me.BusDetMedConsumoMedCont(codmed,Fechai,Fechaf);
					int formulado=0;
					int dispensado=0;
					int form;
					int disp;
					int cont=0;
					int promd;
					int promf;
					while(rs1.next()){
						out.println("<tr><td>"+rs1.getString(1)+"</td>");//medicamento
						out.println("<td>"+rs1.getString(4)+"</td>");//lote
						out.println("<td>"+rs1.getString(7)+"</td>");//Identificacion Paciente
						out.println("<td>"+rs1.getString(6)+"</td>");//paciente
						out.println("<td>"+rs1.getString(2)+"</td>");//cantidad formulada
						out.println("<td>"+rs1.getString(3)+"</td>");//cantidad disp
						out.println("<td>"+rs1.getString(5)+"</td>");//formulada por 
						out.println("<td>"+rs1.getString(11)+"</td>");//profesion
						out.println("<td>"+rs1.getString(12)+"</td>");//registro medico
						out.println("<td>"+rs1.getString(9)+"</td>");//fecha formulacion
						out.println("<td>"+rs1.getString(13)+"</td>");//hora formulacion
						out.println("<td>"+rs1.getString(10)+"</td><");//fecha dispen 
						out.println("<td>"+rs1.getString(14)+"</td>");
						out.println("</tr>");
						form=rs1.getInt(2);
						disp=rs1.getInt(3);
						formulado=form+formulado;
						dispensado=disp+dispensado;
						cont=cont+1;
					}
					rs1.getStatement().getConnection().close();
					if(cont!=0){
					promf=(formulado/cont);
					promd=(dispensado/cont);
					out.println("<tr bgcolor='#F1FAFC' ><td colspan='7' ><hr><b>Sumatoria Formulado : "+formulado+"&nbsp; Promedio Formulado : "+promf+"</b><hr></td>");
					out.println("<td colspan='7' ><hr><b> Sumatoria Dispensado :&nbsp;"+dispensado+"&nbsp; Promedio Dispensado :&nbsp;"+promd+"</b><hr></td></tr>");
					}else{
					//out.println("<tr><td><font color='red'>No hay datos para los parametros Dados</font></td></tr>");
					}
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//out.println("<tr><td align='center'><input type='button' name='btnGenPDF' value='Generar PDF' onClick='verReporteMedControl("+tano+","+tmeses+","+tdia+","+ftano+","+ftmeses+","+ftdia+")' /></td></tr>'");
			out.println("</table>");
		}





if(va.equals("RepConGenMedContBod")){
			String tano=request.getParameter("tano");
			String tmeses=request.getParameter("tmeses");
			String tdia=request.getParameter("tdia");
			String ftano=request.getParameter("ftano");
			String ftmeses=request.getParameter("ftmeses");
			String ftdia=request.getParameter("ftdia");
			//String ftent=request.getParameter("ftent");
			String Fechai=tano+"-"+tmeses+"-"+tdia;
			String Fechaf=ftano+"-"+ftmeses+"-"+ftdia; 
			String bodega=request.getParameter("bodega");
			
			
			rs=me.BuscarEmpresa();
			String nombreE="";
			try {
				if(rs.next()){
					nombreE=rs.getString(2);
				}rs.getStatement().getConnection().close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			System.out.println("valor de bodega"+bodega);
			out.println("<table  class='contpre' width='90%' >");
			out.println("<tr><td colspan='14'><br><b>"+nombreE+" - Informe del "+Fechai+" al "+Fechaf+"</b></td></tr>");
			
			
			rs=me.BusCodMedConsumoMedContB(bodega,Fechai,Fechaf);
			String codmed= null;
			try {
				out.println("<tr><td colspan='14' >");
				rs2=me.ObtenerBodegaso(bodega);
				if(rs2.next()){
				out.println("<br><b>"+rs2.getString(2)+" - MEDICAMENTOS DE CONTROL</b>");
				}
				out.println("</td></tr>");
				out.println("<tr bgcolor='#CCE6FD'><b><td><br>Nombre del Producto</td><td><br>Lote</td><td><br>Identificacion Pac.</td><td><br>Nombre Paciente</td><td><br>Cantidad Formulada</td><td><br>Cantidad Dispensada</td><td><br>Formulada Por </td><td><br>Profesion</td><td><br>Registro Medico</td><td><br>Fecha de Formulacion</td><td><br>Hora de Formulacion</td><td><br>Fecha de Dispensacion </td><td><br>Hora de Dispensacion</td></b></tr> ");
				while(rs.next()){
					codmed=rs.getString(1);
					rs1=me.BusDetMedConsumoMedContB(codmed,bodega,Fechai,Fechaf);
					int formulado=0;
					int dispensado=0;
					int form;
					int disp;
					int cont=0;
					int promd;
					int promf;
					while(rs1.next()){
						out.println("<tr><td>"+rs1.getString(1)+"</td>");//medicamento
						out.println("<td>"+rs1.getString(4)+"</td>");//lote
						out.println("<td>"+rs1.getString(7)+"</td>");//Identificacion Paciente
						out.println("<td>"+rs1.getString(6)+"</td>");//paciente
						out.println("<td>"+rs1.getString(2)+"</td>");//cantidad formulada
						out.println("<td>"+rs1.getString(3)+"</td>");//cantidad disp
						out.println("<td>"+rs1.getString(5)+"</td>");//formulada por 
						out.println("<td>"+rs1.getString(11)+"</td>");//profesion
						out.println("<td>"+rs1.getString(12)+"</td>");//registro medico
						out.println("<td>"+rs1.getString(9)+"</td>");//fecha formulacion
						out.println("<td>"+rs1.getString(13)+"</td>");
						out.println("<td>"+rs1.getString(10)+"</td>");//fecha dispen 
						out.println("<td>"+rs1.getString(14)+"</td>");
						out.println("</tr>");
						form=rs1.getInt(2);
						disp=rs1.getInt(3);
						formulado=form+formulado;
						dispensado=disp+dispensado;
						cont=cont+1;
					}
					rs1.getStatement().getConnection().close();
					if(cont!=0){
					promf=(formulado/cont);
					promd=(dispensado/cont);
					out.println("<tr bgcolor='#F1FAFC' ><td colspan='7' ><hr><b>Sumatoria Formulado : "+formulado+"&nbsp; Promedio Formulado : "+promf+"</b><hr></td>");
					out.println("<td colspan='7' ><hr><b> Sumatoria Dispensado :&nbsp;"+dispensado+"&nbsp; Promedio Dispensado :&nbsp;"+promd+"</b><hr></td></tr>");
					}else{
					//out.println("<tr><td><font color='red'>No hay datos para los parametros Dados</font></td></tr>");
					}
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//out.println("<tr><td align='center'><input type='button' name='btnGenPDF' value='Generar PDF' onClick=' verReporteMedControlB("+tano+","+tmeses+","+tdia+","+ftano+","+ftmeses+","+ftdia+","+bodega+")' /></td></tr>'");
			out.println("</table>");
		}
		
		
		
		
		


if(va.equals("RepConGenProd1")){
			String tano=request.getParameter("tano");
			String tmeses=request.getParameter("tmeses");
			String tdia=request.getParameter("tdia");
			String ftano=request.getParameter("ftano");
			String ftmeses=request.getParameter("ftmeses");
			String ftdia=request.getParameter("ftdia");
			//String ftent=request.getParameter("ftent");
			String Fechai=tano+"-"+tmeses+"-"+tdia;
			String Fechaf=ftano+"-"+ftmeses+"-"+ftdia; 
			String producto=request.getParameter("producto");
			rs=me.BuscarEmpresa();
			String nombreE="";
			try {
				if(rs.next()){
					nombreE=rs.getString(2);
				}rs.getStatement().getConnection().close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
			out.println("<table  class='contpre' width='90%' >");
			out.println("<tr><td colspan='14'><br><b>"+nombreE+" - Informe del "+Fechai+" al "+Fechaf+"</b></td></tr>");
			
			out.println("<tr><td colspan='14' >");
			rs=me.BusCodMedConsMed(producto,Fechai,Fechaf);
			String codmed= null;
			try {
				if(producto.equals("1")){
				out.println("<br><b>TODAS LAS BODEGAS - MEDICAMENTOS</b>");
				}else{
					if(producto.equals("2")){
						out.println("<br><b>TODAS LAS BODEGAS - DISPOSITIVOS</b>");
					}
				}
			out.println("</td></tr>");
				out.println("<tr bgcolor='#CCE6FD'><b><td><br>Nombre del Producto</td><td><br>Lote</td><td><br>Identificacion Pac.</td><td><br>Nombre Paciente</td><td><br>Cantidad Formulada</td><td><br>Cantidad Dispensada</td><td><br>Formulada Por </td><td><br>Profesion</td><td><br>Registro Medico</td><td><br>Fecha de Formulacion</td><td>Hora de Formulacion</td><td><br>Fecha de Dispensacion </td><td>Hora de Dispensacion</td></b></tr> ");
				while(rs.next()){
					codmed=rs.getString(1);
					rs1=me.BusDetMedConsumo(codmed,Fechai,Fechaf);
					int formulado=0;
					int dispensado=0;
					int form;
					int disp;
					int cont=0;
					int promd;
					int promf;
					while(rs1.next()){
						out.println("<tr><td>"+rs1.getString(1)+"</td>");//medicamento
						out.println("<td>"+rs1.getString(4)+"</td>");//lote
						out.println("<td>"+rs1.getString(7)+"</td>");//Identificacion Paciente
						out.println("<td>"+rs1.getString(6)+"</td>");//paciente
						out.println("<td>"+rs1.getString(2)+"</td>");//cantidad formulada
						out.println("<td>"+rs1.getString(3)+"</td>");//cantidad disp
						out.println("<td>"+rs1.getString(5)+"</td>");//formulada por 
						out.println("<td>"+rs1.getString(11)+"</td>");//profesion
						out.println("<td>"+rs1.getString(12)+"</td>");//registro medico
						out.println("<td>"+rs1.getString(9)+"</td>");//fecha formulacion
						out.println("<td>"+rs1.getString(13)+"</td>");
						out.println("<td>"+rs1.getString(10)+"</td>");//fecha dispen 
						out.println("<td>"+rs1.getString(14)+"</td>");
						out.println("</tr>");
						form=rs1.getInt(2);
						disp=rs1.getInt(3);
						formulado=form+formulado;
						dispensado=disp+dispensado;
						cont=cont+1;
					}
					rs1.getStatement().getConnection().close();
					if(cont!=0){
					promf=(formulado/cont);
					promd=(dispensado/cont);
					out.println("<tr bgcolor='#F1FAFC' ><td colspan='7' ><hr><b>Sumatoria Formulado : "+formulado+"&nbsp; Promedio Formulado : "+promf+"</b><hr></td>");
					out.println("<td colspan='7' ><hr><b> Sumatoria Dispensado :&nbsp;"+dispensado+"&nbsp; Promedio Dispensado :&nbsp;"+promd+"</b><hr></td></tr>");
					}else{
					//out.println("<tr><td colspan='14'><font color='red'>No hay datos para los parametros Dados</font></td></tr>");
					}
				
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//out.println("<tr><td align='center'><input type='button' name='btnGenPDF' value='Generar PDF' onClick='verReporteMedGen("+producto+","+tano+","+tmeses+","+tdia+","+ftano+","+ftmeses+","+ftdia+")' /></td></tr>'");
			out.println("</table>");
		}




		
if(va.equals("RepConGenBod")){
			String tano=request.getParameter("tano");
			String tmeses=request.getParameter("tmeses");
			String tdia=request.getParameter("tdia");
			String ftano=request.getParameter("ftano");
			String ftmeses=request.getParameter("ftmeses");
			String ftdia=request.getParameter("ftdia");
			String bodega=request.getParameter("bodega");
			//String ftent=request.getParameter("ftent");
			String Fechai=tano+"-"+tmeses+"-"+tdia;
			String Fechaf=ftano+"-"+ftmeses+"-"+ftdia; 
			
			rs=me.BuscarEmpresa();
			String nombreE="";
			try {
				if(rs.next()){
					nombreE=rs.getString(2);
				}rs.getStatement().getConnection().close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			System.out.println("valor de bodega"+bodega);
			out.println("<table  class='contpre' width='90%' >");
			out.println("<tr><td colspan='11'><br><b>"+nombreE+" - Informe del "+Fechai+" al "+Fechaf+"</b></td></tr>");
			out.println("<tr><td colspan='11'>");
			
			rs=me.BusCodMedConsumoBod(bodega,Fechai,Fechaf);
			String codmed= null;
			try {
				rs2=me.ObtenerBodegaso(bodega);
				if(rs2.first()){
				out.println("<br><b>BODEGA : "+rs2.getString(2)+"- TODOS LOS MEDICAMENTOS Y DISPOSITIVOS</b>");
				}
			out.println("</td></tr>");
				rs2.getStatement().getConnection().close();
				out.println("<tr bgcolor='#CCE6FD'><b><td><br>Nombre del Producto</td><td><br>Lote</td><td><br>Identificacion Pac.</td><td><br>Nombre Paciente</td><td><br>Cantidad Formulada</td><td><br>Cantidad Dispensada</td><td><br>Formulada Por </td><td><br>Profesion</td><td><br>Registro Medico</td><td><br>Fecha de Formulacion</td><td><br>Fecha de Dispensacion </td></b></tr> ");
				while(rs.next()){
					codmed=rs.getString(1);
					rs1=me.BusDetMedConsumoBod(codmed,Fechai,Fechaf,bodega);
					int formulado=0;
					int dispensado=0;
					int form;
					int disp;
					int cont=0;
					int promd;
					int promf;
					while(rs1.next()){
						out.println("<tr class='rep' ><td>"+rs1.getString(1)+"</td>");//medicamento
						out.println("<td>"+rs1.getString(4)+"</td>");//lote
						out.println("<td>"+rs1.getString(7)+"</td>");//Identificacion Paciente
						out.println("<td>"+rs1.getString(6)+"</td>");//paciente
						out.println("<td>"+rs1.getString(2)+"</td>");//cantidad formulada
						out.println("<td>"+rs1.getString(3)+"</td>");//cantidad disp
						out.println("<td>"+rs1.getString(5)+"</td>");//formulada por 
						out.println("<td>"+rs1.getString(11)+"</td>");//profesion
						out.println("<td>"+rs1.getString(12)+"</td>");//registro medico
						out.println("<td>"+rs1.getString(9)+"</td>");//fecha formulacion
						out.println("<td>"+rs1.getString(10)+"</td></tr>");//fecha dispen 
						form=rs1.getInt(2);
						disp=rs1.getInt(3);
						formulado=form+formulado;
						dispensado=disp+dispensado;
						cont=cont+1;
					}
					rs1.getStatement().getConnection().close();
					if(cont!=0){
					promf=(formulado/cont);
					promd=(dispensado/cont);
					out.println("<tr bgcolor='#F1FAFC' ><td colspan='5' ><hr><b>Sumatoria Formulado : "+formulado+"&nbsp; Promedio Formulado : "+promf+"</b><hr></td>");
					out.println("<td colspan='6' ><hr><b> Sumatoria Dispensado :&nbsp;"+dispensado+"&nbsp; Promedio Dispensado :&nbsp;"+promd+"</b><hr></td></tr>");
					}else{
					out.println("<tr><td><font color='red'>No hay datos para los parametros Dados</font></td></tr>");
					}
				
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//ESTE SI FUNCIONA PERO HAY Q MODIFICAR EL REPORTE PARA QUE SE VEA MAS PRESENTABLE out.println("<tr><td align='center'><input type='button' name='btnGenPDF' value='Generar PDF' onClick='verReporteGeneralB("+tano+","+tmeses+","+tdia+","+ftano+","+ftmeses+","+ftdia+","+bodega+")' /></td></tr>'");
			out.println("</table>");
		}

		
		if(va.equals("RepConGenBodMed")){
			String tano=request.getParameter("tano");
			String tmeses=request.getParameter("tmeses");
			String tdia=request.getParameter("tdia");
			String ftano=request.getParameter("ftano");
			String ftmeses=request.getParameter("ftmeses");
			String ftdia=request.getParameter("ftdia");
			String bodega=request.getParameter("bodega");
			String producto=request.getParameter("producto");
			//String ftent=request.getParameter("ftent");
			String Fechai=tano+"-"+tmeses+"-"+tdia;
			String Fechaf=ftano+"-"+ftmeses+"-"+ftdia; 
			
			
			
			out.println("<table  class=contrep width='80%'>");
			out.println("<p class=contpre><br><br><b>Informe del "+Fechai+" al "+Fechaf+"</b><br><br></p>");
			
			
			rs=me.BusCodMedConsumoBodMed(bodega,producto,Fechai,Fechaf);
			String codmed= null;
			try {
				rs2=me.ObtenerBodegaso(bodega);
				if(rs2.first()){
					if(producto.equals("1")){
						out.println("<br><b>BODEGA : "+rs2.getString(2)+"- MEDICAMENTOS </b>");
					}else{
						if(producto.equals("2")){
							out.println("<br><b>BODEGA : "+rs2.getString(2)+"- DISPOSITIVOS</b>");
						}
					}
				}
				rs2.getStatement().getConnection().close();
				out.println("<tr bgcolor='#CCE6FD'><b><td><br>Nombre del Producto</td><td><br>Lote</td><td><br>Identificacion Pac.</td><td><br>Nombre Paciente</td><td><br>Cantidad Formulada</td><td><br>Cantidad Dispensada</td><td><br>Formulada Por </td><td><br>Profesion</td><td><br>Registro Medico</td><td><br>Fecha de Formulacion</td><td><br>Fecha de Dispensacion </td></b></tr> ");
				while(rs.next()){
					codmed=rs.getString(1);
					System.out.println("codmed"+codmed);
					rs1=me.BusDetMedConsumoBodMed(codmed,Fechai,Fechaf,bodega,producto);
					int formulado=0;
					int dispensado=0;
					int form;
					int disp;
					int cont=0;
					int promd;
					int promf;
					while(rs1.next()){
						out.println("<tr><td>"+rs1.getString(1)+"</td>");//medicamento
						out.println("<td>"+rs1.getString(4)+"</td>");//lote
						out.println("<td>"+rs1.getString(7)+"</td>");//Identificacion Paciente
						out.println("<td>"+rs1.getString(6)+"</td>");//paciente
						out.println("<td>"+rs1.getString(2)+"</td>");//cantidad formulada
						out.println("<td>"+rs1.getString(3)+"</td>");//cantidad disp
						out.println("<td>"+rs1.getString(5)+"</td>");//formulada por 
						out.println("<td>"+rs1.getString(11)+"</td>");//profesion
						out.println("<td>"+rs1.getString(12)+"</td>");//registro medico
						out.println("<td>"+rs1.getString(9)+"</td>");//fecha formulacion
						out.println("<td>"+rs1.getString(10)+"</td></tr>");//fecha dispen 
						form=rs1.getInt(2);
						disp=rs1.getInt(3);
						formulado=form+formulado;
						dispensado=disp+dispensado;
						cont=cont+1;
						System.out.println("cont"+cont);
					}
					rs1.getStatement().getConnection().close();
					System.out.println("="+formulado+"/"+cont);
					if(cont!=0){
					promf=(formulado/cont);
					promd=(dispensado/cont);
					out.println("<tr bgcolor='#F1FAFC' ><td colspan='5' ><hr><b>Sumatoria Formulado : "+formulado+"&nbsp; Promedio Formulado : "+promf+"</b><hr></td>");
					out.println("<td colspan='6' ><hr><b> Sumatoria Dispensado :&nbsp;"+dispensado+"&nbsp; Promedio Dispensado :&nbsp;"+promd+"</b><hr></td></tr>");
					}else{
						//out.println("<tr><td><font color='red'>No hay datos para los parametros Dados</font></td></tr>");
					}
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//out.println("<tr><td align='center'><input type='button' name='btnGenPDF' value='Generar PDF' onClick='verReporteMedGenB("+tano+","+tmeses+","+tdia+","+ftano+","+ftmeses+","+ftdia+","+bodega+","+producto+")' /></td></tr>'");
			out.println("</table>");
		}



if(va.equals("Producto")){	
			out.print("<table width='100%' class='style6'><tr><td colspan='6'><div align='center' class='style11' id='cabecera2'>Relación de Productos</div></td></tr>");
			
			out.print("<tr><td><label><input name='radiobutton' type='radio' value='radiobutton' id='GeneralConsumo' onClick='Seleccione()' />Reporte Consumo</label></td>");
			out.print("<td><label><input name='radiobutton' type='radio' value='radiobutton' id='Dispensacion' onClick='Seleccione()' />Reporte Dispensacion</label></td>");
			out.print("<td><label><input name='radiobutton' type='radio' value='radiobutton' id='Movimiento' onClick='Seleccione()' />Reporte Por Tipo de Movimiento</label></td>");	
			out.print("<td><label><input name='radiobutton' type='radio' value='radiobutton' id='Producto' onClick='Seleccione()' checked />Reporte Por Producto</label></td>");
			out.print("<td><label><input name='radiobutton' type='radio' value='radiobutton' id='Ordenes' onClick='Seleccione()' />Reporte de Ordenes</label></td></tr>");
			
			out.print("<table width='100%' class='style6'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Reporte Por Producto</div></td></tr>");
			
			out.print("<table width='100%' border='0' class='style6'><tr><td width='30%'><u><i><div align='left'>Producto</div></i></u></td>");
			//out.print("<table width='100%' border='1' class='style6'>");
			out.print("<td><input name='txtTipoMe0' type='text' id='txtTipoMe0' onkeyup='autocompletaMArticulo(0,0,1)' size='39' />");//identificador Div+identificador campo
			out.print("<input name='txtTipoMeH0' type='hidden' id='txtTipoMeH0'/></td><td>Tipo de Reporte : <select name='treporte' id='treporte'><option value='Seleccione'>Seleccione</option>" +
					"<option value='1'>CONSUMO PROMEDIO</>" +
					"<option value='2'>DISPENSACION POR PACIENTES</>" +
					"<option value='3'>ENTRADAS </> " +
					"<option value='4'>SALIDAS</>" +
					"<option value='5'>TRASLADOS</>" +
					"<option value='6'>DEVOLUCIONES </></select></td></tr>");//identificador Div+identificador campo
			out.print("<tr><td></td><td><div id='sugerencias10'></div></td></tr>");
			out.print("<tr><td width='12%'>Seleccione Bodega</td><td><label><select name='cmbBodega' id='cmbBodega'><option value='Seleccione'>Seleccione</option>");
			out.print("<option value='todas'>TODAS LAS BODEGAS</option>");
			rs=me.ObtenerBodegas(user);
			try { 
				while(rs.next()){
				out.print("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
				} 
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("</select></label></td>");	
			 
			out.print("</tr>");
			out.print("<tr><td> Fecha Inicial : </td><td>");
			int dia=0;
			out.println("<select name=Idia id=Idia>");
			out.println("<option value=40>---</option>");
			for(int i=0;i<=30;i++){
			dia=dia+1;
			out.println("<option value="+dia+">"+dia+"</option>");
			}
			
			out.println("</select>");
			out.println("<select name=Imes id=Imes>");
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
			
			out.println("<select name=Iano id=Iano>");
			out.println("<option value=40>---</option>");
			int ano=2008;
			for(int k=0;k<=30;k++){
				ano=ano+1;
				out.println("<option value="+ano+">"+ano+"</option>");
			}
			out.println("</select></td>");	
			out.println("<td>Fecha Final : ");
			int fdia=0;
			out.println("<select name=Fdia id=Fdia>");
			out.println("<option value=40>---</option>");
			for(int i=0;i<=30;i++){
			fdia=fdia+1;
			out.println("<option value="+fdia+">"+fdia+"</option>");
			}
			
			out.println("</select>");
			out.println("<select name=Fmes id=Fmes>");
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
			
			
			out.println("<select name=Fano id=Fano>");
			out.println("<option value=40>---</option>");
			int fano=2008;
			for(int k=0;k<=30;k++){
				fano=fano+1;
				out.println("<option value="+fano+">"+fano+"</option>");
			}
			out.println("</select></td></tr>");
			
		
			//Hacer Autocompletar d productos
			
			out.print("<td colspan='4'><div><label><input type='button' name='btnMP' id='btnMP' value='Consultar' onClick='ConsultarProd()' disabled='disabled'></label></div></td></tr>");
			
			out.print("<tr><td colspan='2'>&nbsp;</td></tr></table>");
						
		}  
		
		if(va.equals("Ordenes")){	
			out.print("<table width='100%' class='style6'><tr><td colspan='6'><div align='center' class='style11' id='cabecera2'>Relación de Ordenes</div></td></tr>");
			
			out.print("<tr><td><label><input name='radiobutton' type='radio' value='radiobutton' id='GeneralConsumo' onClick='Seleccione()' />Reporte Consumo</label></td>");
			out.print("<td><label><input name='radiobutton' type='radio' value='radiobutton' id='Dispensacion' onClick='Seleccione()' />Reporte Dispensacion</label></td>");
			out.print("<td><label><input name='radiobutton' type='radio' value='radiobutton' id='Movimiento' onClick='Seleccione()' />Reporte Por Tipo de Movimiento</label></td>");	
			out.print("<td><label><input name='radiobutton' type='radio' value='radiobutton' id='Producto' onClick='Seleccione()' />Reporte Por Producto</label></td>");
			out.print("<td><label><input name='radiobutton' type='radio' value='radiobutton' id='Ordenes' onClick='Seleccione()' checked />Reporte de Ordenes</label></td></tr>");
			
			out.print("<table width='100%' class='style6'><tr><td colspan='6'><div align='center' class='style11' id='cabecera2'>Reporte De Ordenes</div></td></tr>");
			
			
			//out.print("<table width='100%' border='1' class='style6'>");
			
			out.print("<tr><td>Tipo de Unidad : <select name='tunidad' id='tunidad'><option value='Seleccione'>Seleccione</option>" +
					"<option value='todas'>TODAS</>" );
				rs1=me.BuscarPabellones();
				try {
					while(rs1.next()){
						out.println("<option value='"+rs1.getString(1)+"'>"+rs1.getString(2)+"</option>");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				out.println("</select></td>");
			
			out.print("<td> Fecha Inicial : </td><td>");
			int dia=0;
			out.println("<select name=Idia id=Idia>");
			out.println("<option value=40>---</option>");
			for(int i=0;i<=30;i++){
			dia=dia+1;
			out.println("<option value="+dia+">"+dia+"</option>");
			}
			
			out.println("</select>");
			out.println("<select name=Imes id=Imes>");
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
			
			out.println("<select name=Iano id=Iano>");
			out.println("<option value=40>---</option>");
			int ano=2008;
			for(int k=0;k<=30;k++){
				ano=ano+1;
				out.println("<option value="+ano+">"+ano+"</option>");
			}
			out.println("</select></td>");	
			out.println("<td>Fecha Final : </td><td>");
			int fdia=0;
			out.println("<select name=Fdia id=Fdia>");
			out.println("<option value=40>---</option>");
			for(int i=0;i<=30;i++){
			fdia=fdia+1;
			out.println("<option value="+fdia+">"+fdia+"</option>");
			}
			
			out.println("</select>");
			out.println("<select name=Fmes id=Fmes>");
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
			
			
			out.println("<select name=Fano id=Fano>");
			out.println("<option value=40>---</option>");
			int fano=2008;
			for(int k=0;k<=30;k++){
				fano=fano+1;
				out.println("<option value="+fano+">"+fano+"</option>");
			}
			out.println("</select></td></tr>");
			
		
			//Hacer Autocompletar d productos
			
			out.print("<td colspan='4'><div><label><input type='button' name='btnMP' id='btnMP' value='Consultar' onClick='ConsultarOrd()' ></label></div></td></tr>");
			
			out.print("<tr><td colspan='2'>&nbsp;</td></tr></table>");
						
		}  
		
		
		

if(va.equals("RepConsProd")){
			String tano=request.getParameter("tano");
			String tmeses=request.getParameter("tmeses");
			String tdia=request.getParameter("tdia");
			String ftano=request.getParameter("ftano");
			String ftmeses=request.getParameter("ftmeses");
			String ftdia=request.getParameter("ftdia");
			//String ftent=request.getParameter("ftent");
			String Fechai=tano+"-"+tmeses+"-"+tdia;
			String Fechaf=ftano+"-"+ftmeses+"-"+ftdia; 
			String cm=request.getParameter("cm");
			System.out.println("valor de cm"+cm);
			String codmed= null;
			rs=me.BuscarEmpresa();
			String nombreE="";
			try {
				if(rs.next()){
					nombreE=rs.getString(2);
				}rs.getStatement().getConnection().close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			out.println("<table  class='contpre' width='90%' >");
			out.println("<tr><td colspan='14'><br><b>"+nombreE+" - Informe del "+Fechai+" al "+Fechaf+"</b></td></tr>");
			try {
				
				out.println("<tr><td  colspan='14'><br><b>TODAS LAS BODEGAS </b></td></tr>");
				out.println("<tr bgcolor='#CCE6FD'><b><td><br>No. Formulacion</td><td><br>Nombre del Producto</td><td><br>Lote</td><td><br>Identificacion Pac.</td><td><br>Nombre Paciente</td><td><br>Cantidad Formulada</td><td><br>Cantidad Dispensada</td><td><br>Formulada Por </td><td><br>Profesion</td><td><br>Registro Medico</td><td><br>Fecha de Formulacion</td><td><br>Hora de Formulacion </td><td><br>Fecha de Dispensacion </td><td><br>Hora de Dispensacion </td></b></tr> ");
					rs=me.BusDetMedConsumoP(cm,Fechai,Fechaf);
					int formulado=0;
					int dispensado=0;
					int form;
					int disp;
					int cont=0;
					int promd;
					int promf;
					while(rs.next()){
						out.println("<tr class='rep'><td>"+rs.getString(13)+"</td>");//codformulacion
						out.println("<td>"+rs.getString(1)+"</td>");//medicamento
						out.println("<td>"+rs.getString(4)+"</td>");//lote
						out.println("<td>"+rs.getString(7)+"</td>");//Identificacion Paciente
						out.println("<td>"+rs.getString(6)+"</td>");//paciente
						out.println("<td>"+rs.getString(2)+"</td>");//cantidad formulada
						out.println("<td>"+rs.getString(3)+"</td>");//cantidad disp
						out.println("<td>"+rs.getString(5)+"</td>");//formulada por 
						out.println("<td>"+rs.getString(11)+"</td>");//profesion
						out.println("<td>"+rs.getString(12)+"</td>");//registro medico
						out.println("<td>"+rs.getString(9)+"</td>");//fecha formulacion
						out.println("<td>"+rs.getString(14)+"</td>");//hora formulacion
						out.println("<td>"+rs.getString(10)+"</td>");//fecha dispen 
						out.println("<td>"+rs.getString(15)+"</td>");//hora de disp
						form=rs.getInt(2);
						disp=rs.getInt(3);
						formulado=form+formulado;
						dispensado=disp+dispensado;
						cont=cont+1;
					}
					rs.getStatement().getConnection().close();
					if(cont!=0){
					promf=(formulado/cont);
					promd=(dispensado/cont);
					out.println("<tr bgcolor='#F1FAFC' ><td colspan='7' ><hr><b>Sumatoria Formulado : "+formulado+"&nbsp; Promedio Formulado : "+promf+"</b><hr></td>");
					out.println("<td colspan='7' ><hr><b> Sumatoria Dispensado :&nbsp;"+dispensado+"&nbsp; Promedio Dispensado :&nbsp;"+promd+"</b><hr></td></tr>");
					}else{
							out.println("<tr><td colspan='14'><font color='red'>No hay datos para los parametros Dados</font></td></tr>");
					}
					
					rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//out.println("<tr><td align='center'><input type='button' name='btnGenPDF' value='Generar PDF' onClick='' /></td></tr>'");
			out.println("</table>");
		}



		
		
		if(va.equals("RepOrdenes")){
			String tano=request.getParameter("tano");
			String tmeses=request.getParameter("tmeses");
			String tdia=request.getParameter("tdia");
			String ftano=request.getParameter("ftano");
			String ftmeses=request.getParameter("ftmeses");
			String ftdia=request.getParameter("ftdia");
			String tunidad=request.getParameter("tunidad");
			//String ftent=request.getParameter("ftent");
			String Fechai=tano+"-"+tmeses+"-"+tdia;
			String Fechaf=ftano+"-"+ftmeses+"-"+ftdia; 
		
			
			out.println("<table  class=contrep width='80%'>");
			out.println("<p class=contpre><br><br><b>Informe del "+Fechai+" al "+Fechaf+"</b><br><br></p>");
			if(tunidad.equals("todas")){
				out.println("<tr><td>TODAS LAS AREAS</td></tr>");
			}else{
				rs=me.ObtenerPabellones(tunidad);
				try {
					if(rs.next()){
						out.println("<tr><td>"+rs.getString(2)+"</td></tr>");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			int contF=0;
			int contD=0;
			int contFT=0;
			int val;
			rs1=me.BuscarOrdenes(Fechai,Fechaf,tunidad);
			out.println("<tr bgcolor='#CCE6FD'><b><td>No. Formulacion</td><td><br>Documento del Paciente</td><td><br>Nombre del Paciente</td><td><br>Estado</td><td><br>Area</td><td><br>SubArea</td><td><br>Nombre del Formulante</td><td>Profesion</td><td><br>Documento </td><td><br>Fecha </td></b></tr>");
			try {
				while(rs1.next()){
					out.println("<tr><td>"+rs1.getString(1)+"</td>");
					out.println("<td>"+rs1.getString(2)+"</td>");
					out.println("<td>"+rs1.getString(3)+"</td>");
					val=rs1.getInt(4);
					System.out.println("val "+val);
					if(val==1){
						out.println("<td>Dispensado</td>");
						contD=contD+1;
					}else{
						out.println("<td></td>");
						contF=contF+1;
					}
					contFT=contFT+1;
					out.println("<td>"+rs1.getString(5)+"</td>");
					out.println("<td>"+rs1.getString(6)+"</td>");
					out.println("<td>"+rs1.getString(7)+"</td>");
					out.println("<td>"+rs1.getString(8)+"</td>");
					out.println("<td>"+rs1.getString(9)+"</td>");
					out.println("<td>"+rs1.getString(10)+"</td></tr>");
					
				}
				out.println("<tr><td colspan='3'>Total de Ordenes Formuladas :"+contFT+"</td><td colspan='3'>Total de Ordenes Formuladas No Dispensadas :"+contF+"</td><td colspan='4'>Total de Ordenes Formuladas Dispensadas :"+contD+"</td></tr>");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			out.println("</table>");
		}
		
		
		if(va.equals("RepConsProdBod")){
			String tano=request.getParameter("tano");
			String tmeses=request.getParameter("tmeses");
			String tdia=request.getParameter("tdia");
			String ftano=request.getParameter("ftano");
			String ftmeses=request.getParameter("ftmeses");
			String ftdia=request.getParameter("ftdia");
			String bodega=request.getParameter("bodega");
			//String ftent=request.getParameter("ftent");
			String Fechai=tano+"-"+tmeses+"-"+tdia;
			String Fechaf=ftano+"-"+ftmeses+"-"+ftdia; 
			String cm=request.getParameter("cm");
			System.out.println("valor de cm"+cm);
			String codmed= null;
			out.println("<table  class=contrep width='80%'>");
			out.println("<p class=contpre><br><br><b>Informe del "+Fechai+" al "+Fechaf+"</b><br><br></p>");
			try {
				
				out.println("<tr><td><br><b>TODAS LAS BODEGAS </b></td></tr>");
				out.println("<tr bgcolor='#CCE6FD'><b><td>No. Formulacion</td><td><br>Nombre del Producto</td><td><br>Lote</td><td><br>Identificacion Pac.</td><td><br>Nombre Paciente</td><td><br>Cantidad Formulada</td><td><br>Cantidad Dispensada</td><td><br>Formulada Por </td><td><br>Profesion</td><td><br>Registro Medico</td><td><br>Fecha de Formulacion</td><td><br>Fecha de Dispensacion </td></b></tr> ");
					rs=me.BusDetMedConsumoPBod(cm,bodega,Fechai,Fechaf);
					int formulado=0;
					int dispensado=0;
					int form;
					int disp;
					int cont=0;
					int promd;
					int promf;
					while(rs.next()){
						out.println("<tr><td>"+rs.getString(13)+"</td>");//codigo de formulacion
						out.println("<td>"+rs.getString(1)+"</td>");//medicamento
						out.println("<td>"+rs.getString(4)+"</td>");//lote
						out.println("<td>"+rs.getString(7)+"</td>");//Identificacion Paciente
						out.println("<td>"+rs.getString(6)+"</td>");//paciente
						out.println("<td>"+rs.getString(2)+"</td>");//cantidad formulada
						out.println("<td>"+rs.getString(3)+"</td>");//cantidad disp
						out.println("<td>"+rs.getString(5)+"</td>");//formulada por 
						out.println("<td>"+rs.getString(11)+"</td>");//profesion
						out.println("<td>"+rs.getString(12)+"</td>");//registro medico
						out.println("<td>"+rs.getString(9)+"</td>");//fecha formulacion
						out.println("<td>"+rs.getString(10)+"</td></tr>");//fecha dispen 
						form=rs.getInt(2);
						disp=rs.getInt(3);
						formulado=form+formulado;
						dispensado=disp+dispensado;
						cont=cont+1;
					}
					if(cont!=0){
					promf=(formulado/cont);
					promd=(dispensado/cont);
					
					out.println("<tr bgcolor='#F1FAFC' ><td colspan='6' ><hr><b>Sumatoria Formulado : "+formulado+"&nbsp; Promedio Formulado : "+promf+"</b><hr></td>");
					out.println("<td colspan='6' ><hr><b> Sumatoria Dispensado :&nbsp;"+dispensado+"&nbsp; Promedio Dispensado :&nbsp;"+promd+"</b><hr></td></tr>");
					}else{
						out.println("<tr><td><font color='red'>No hay datos para los parametros Dados</font></td></tr>");
					}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.println("<tr><td align='center'><input type='button' name='btnGenPDF' value='Generar PDF' onClick='' /></td></tr>'");
			out.println("</table>");
		}
		

		if(va.equals("MedOrden")){
			
			out.print("<table width='100%' class='style6'><tr><td colspan='8'><div align='center' class='style11' id='cabecera2'>Habilitar Medicamento Para que sea Ordenado</div></td></tr>");
			out.print("<tr><td><table width='100%' border='0' class='style6'><tr><td ><u><i><div align='left'>Producto :</div></i></u></td>");
			out.print("<td><input name='txtTipoMe0' type='text' id='txtTipoMe0' onkeyup='autocompletaMArticulo(0,0,1)' size='39' /><td>Concentracion : </td><td ><label><input name='txtCon' type='text' id='txtCon' size='40' onkeyup='checknum()' /></label></td><td> Estabilidad (Dias) :</td>");
			out.print("<td><select id='Estabilidad'><option value='0'>0</option>");
			int j=1;
			for (j=1;j<101;j++){
				out.print("<option value='"+j+"'>"+j+"</option>");	
				}	
			out.print("</select></td>");
			out.print("<td><input name='txtTipoMeH0' type='hidden' id='txtTipoMeH0'/></td>");
			out.print("<tr><td></td><td><div id='sugerencias10'></div></td></tr>");
			out.print("<tr><td>Diluyente :</td><td>" +
					"<select id='diluy'><option value='Seleccione'>Seleccione</option>" +
					"<option value='SSN'>SSN</option>" +
					"<option value='API'>API</option>" +
					"<option value='N/A'>N/A</option>"+
					"</select></td><td>Codigo Interno : </td><td><input name='txtCodI' id='txtCodI' size='40' /></td><td>Via de Administracion : </td><td>");
			out.print("<select id='VAdm'><option value='Seleccione'>Seleccione</option>");
			rs=me.BuscarViaAdmon();
			try {
				while(rs.next()){
					out.print("<option value='"+rs.getString(1)+"' title='"+rs.getString(3)+"' >"+rs.getString(1)+"</option>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			out.print("</select></td></tr><tr><td>Volumen de Reconstitucion :</td><td><input name='txtVR' id='txtVR' size='20' onkeyup='checknumvr()' /></td>");
			out.print("<td> Grupo Farmacologico : </td>");
			rs=me.BuscarGrupoFarmacologico();
			out.print("<td width='20%'><select id='grupof'><option value='Seleccione'>Seleccione</option>");
			try {
				while (rs.next()){
					out.print("<option value='"+rs.getString(1)+"'>"+rs.getString(2)+"</option>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.print("</select></td>");
			out.print("<tr><td>Numero de Constantes de Volumen a Asignar :</td>");
			out.print("<td><select id='Nconst'><option value='0'>0</option>");
			int i=1;
			for (i=1;i<101;i++){
				out.print("<option value='"+i+"'>"+i+"</option>");	
				}
			out.print("</select>");
			out.print("</td><td colspan='4'><div align='left'><label><input type='button' name='btnMP' id='btnMP' value=' Relacionar Constantes de Volumen ' onClick='HabMed()' disabled='disabled'></label></div></td></tr>");
			out.print("<tr><td colspan='2'>&nbsp;</td></tr></table>");
					
		}
		
		
	if(va.equals("Creados")){

out.print("<table width='100%' class='style6'>");
	rs=me.BuscarMFaltCte(); 
	int j=1;
	try {
		if(rs.next()){
			
			while(rs.next()){
				j++;
			}
			out.print("<tr><td align='rigth'><a href='#' onclick='VerMedSinConstantes()'> <b>Medicamentos sin Constantes asignadas <font color='red'>"+j+"</font><b></a></td></tr>");
		}
	}catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
			
	out.print("<tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Medicamentos Habilitados Para Ordenes de Produccion</div></td></tr>");
	out.print("<table border='0' class='style6'><tr><td  ><u><i><div align='left'>Codigo Interno</div></i></u></td><td><u><i><div align='left'>Nombre del Producto &nbsp;</div></i></u></td><td><u><i><div align='left'>&nbsp;Concentracion</div></i></u></td><td><u><i><div align='left'>&nbsp;Diluyente</div></i></u></td><td><u><i><div align='left'>&nbsp;VR</div></i></u></td><td><u><i><div align='left'>&nbsp;Grupo Farmacologico</div></i></u></td><td><u><i><div align='left'>&nbsp;Cte. de Volumen</div></i></u></td><td><u><i><div align='left'>&nbsp;Descripcion</div></i></u></td><td><u><i><div align='left'>&nbsp;Estabilidad (Dias)</div></i></u></td><td><u><i><div align='left'>&nbsp;Via Admon</div></i></u></td><td><u><i><div align='left'>&nbsp;Modificar</div></i></u></td></tr>");
	rs=me.BuscarMedHabilitado();
	String CodMedH="";	
	String CodCteV="";
	try {
		while(rs.next()){
			CodMedH=rs.getString(11);
			CodCteV=rs.getString(12);
			out.print("<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td align='center'>"+rs.getString(3)+"</td><td align='center'>"+rs.getString(4)+"</td><td align='rigth'>"+rs.getString(5)+"</td><td align='center'>"+rs.getString(6)+"</td><td align='center'>"+rs.getString(7)+"</td><td align='center'>"+rs.getString(8)+"</td><td align='center'>"+rs.getString(9)+"</td><td align='center'>"+rs.getString(10)+"</td><td>&nbsp;<input type='radio'  name='radiobutton'  onclick='ModMedH("+CodMedH+","+CodCteV+")' /></td></tr>");
		System.out.print("CodMEDh : "+CodMedH);
		System.out.print("cODcTEv : "+CodCteV);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	out.print("</table>");
	
	}


	if(va.equals("VerMSC")){
		rs=me.BuscarMFaltCte(); 
		out.print("<table width='100%' class='style6'>");
		out.print("<tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>MEDICAMENTOS HABILITADOS SIN CONSTANTE DE VOLUMEN ASIGNADA</div></td></tr>");
		out.print("<table border='0' class='style6'><tr><td  ><u><i><div align='left'>Codigo Interno</div></i></u></td><td><u><i><div align='left'>Nombre del Producto &nbsp;</div></i></u></td><td><u><i><div align='left'>&nbsp;Concentracion</div></i></u></td><td><u><i><div align='left'>&nbsp;Diluyente</div></i></u></td><td><u><i><div align='left'>&nbsp;VR</div></i></u></td><td><u><i><div align='left'>&nbsp;Grupo Farmacologico</div></i></u></td><td><u><i><div align='left'>&nbsp;Estabilidad (Dias)</div></i></u></td><td><u><i><div align='left'>&nbsp;Via Admon</div></i></u></td><td><u><i><div align='left'>&nbsp;Cte. de Volumen</div></i></u></td><td><u><i><div align='left'>&nbsp;Descripcion</div></i></u></td><td><u><i><div align='left'>&nbsp;</div></i></u></td></tr>");
		try {
			while(rs.next()){
				out.print("<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td align='center'>"+rs.getString(3)+"</td><td align='center'>"+rs.getString(4)+"</td><td align='rigth'>"+rs.getString(5)+"</td><td align='center'>"+rs.getString(6)+"</td><td align='center'>"+rs.getString(7)+"</td><td align='center'>"+rs.getString(8)+"</td><td><input type=text id='CteV' size='8'/></td>");
				out.print("<td ><select id='Desci'><option value='Seleccione'>Seleccione</option>");
				out.print("<option value='N/A' title='NO APLICA'>N/A</option>");
				out.print("<option value='BOLSA' title='BOLSA'>BOLSA</option>");
				out.print("<option value='JERINGA PRELLENADA' title='JERINGA PRELLENADA'>JP</option>");
				out.print("</select></td>");
				out.print("<td>&nbsp;<input type='button' name='btn' id='btn' title='Adicionar Cte de Volumen' value=' + ' onClick='AgreCteV("+rs.getString(9)+")' ></td></tr>");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.print("</table>");
		System.out.println("VerMSC");
	}

	if(va.equals("ModMedH")){			
	String CodCteV=request.getParameter("CodCteV");
	String CodmedH=request.getParameter("CodmedH");
	System.out.println("Controlador MoMedH");
	rs=me.ObtenerMedH(CodmedH,CodCteV);
	out.print("<table width='100%' class='style6'><tr><td colspan='11'><div align='center' class='style11' id='cabecera2'>Modificar Medicamento Habilitado</div></td></tr>"); 
	out.print("<tr><td><u><i><div align='left'>Codigo Interno</div></i></u></td><td><u><i><div align='left'>Nombre del Producto &nbsp;</div></i></u></td><td><u><i><div align='left'>&nbsp;Concentracion</div></i></u></td><td><u><i><div align='left'>&nbsp;Diluyente</div></i></u></td><td><u><i><div align='left'>&nbsp;VR</div></i></u></td><td><u><i><div align='left'>&nbsp;Grupo Farmacologico</div></i></u></td><td><u><i><div align='left'>&nbsp;Cte. de Volumen</div></i></u></td><td><u><i><div align='left'>&nbsp;Descripcion</div></i></u></td><td><u><i><div align='left'>&nbsp;Estabilidad (Dias)</div></i></u></td><td><u><i><div align='left'>&nbsp;Via Admon</div></i></u></td></tr>");
	try {
		while(rs.next()){
			out.print("<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td align='center'>"+rs.getString(3)+"</td><td align='center'>"+rs.getString(4)+"</td><td align='rigth'>"+rs.getString(5)+"</td><td align='left'>"+rs.getString(6)+"</td><td align='center'>"+rs.getString(7)+"</td><td align='center'>"+rs.getString(8)+"</td><td align='center'>"+rs.getString(9)+"</td><td align='center'>"+rs.getString(10)+"</td>");
			out.print("<tr><td><input name='txtCodI' id='txtCodI' size='10' value='"+rs.getString(1)+"' /></td><td><input name='txtTipoMe0' type='text' id='txtTipoMe0' onkeyup='autocompletaMArticulo(0,0,1)' size='20' value='"+rs.getString(2)+"' /></td><td ><label><input name='txtCon' type='text' id='txtCon' size='10' value='"+rs.getString(3)+"' onkeyup='checknum()' /></label></td><td>" +
			"<select id='diluy'><option value='"+rs.getString(4)+"'>"+rs.getString(4)+"</option>" +
			"<option value='SSN'>SSN</option>" +
			"<option value='API'>API</option>" +
			"<option value='N/A'>N/A</option>"+
			"</select></td><td><input name='txtVR' id='txtVR' size='8' value='"+rs.getString(5)+"' onkeyup='checknumvr()' /></td>");
			
			rs1=me.BuscarGrupoFarmacologico();
			out.print("<td width='10%'><select id='grupof' ><option value='"+rs.getString(13)+"'>"+rs.getString(6)+"</option>");
			try {
				while (rs1.next()){
					out.print("<option value='"+rs1.getString(1)+"'>"+rs1.getString(2)+"</option>");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.print("</select></td>");
			rs2=me.BuscarCteV(CodCteV);
			while(rs2.next()){
				out.print("<td ><label><input type='text' size='10' id='Val' value='"+rs2.getString(4)+"' /><label></td>");
				out.print("<td><select id='Desc'><option value='"+rs2.getString(3)+"'>"+rs2.getString(3)+"</option>");
				out.print("<option value='N/A'>N/A</option>");
				out.print("<option value='BOLSA'>BOLSA</option>");
				out.print("<option value='JERINGA PRELLENADA'>JERINGA PRELLENADA</option>");
				out.print("</select></td>");
			}
			rs2.getStatement().getConnection().close();
			
			out.print("<td><input name='Estabilidad' id='Estabilidad' size='10' value='"+rs.getString(9)+"' onkeyup='checknumest()' /></td>");
			out.print("<td><select id='VAdm'><option value='"+rs.getString(10)+"'>"+rs.getString(10)+"</option>");
			rs2=me.BuscarViaAdmon();
			try {
				while(rs2.next()){
					out.print("<option value='"+rs2.getString(1)+"' title='"+rs2.getString(3)+"' >"+rs2.getString(1)+"</option>");
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			out.print("</select></option>");
			out.print("<td><input name='txtTipoMeH0' type='hidden' id='txtTipoMeH0' value='"+rs.getString(14)+"' /></td>");
			out.print("<tr><td></td><td><div id='sugerencias10'></div></td></tr>");
		
		}
		rs.getStatement().getConnection().close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	//out.print("<tr><td colspan='4'><div align='left'><label><br><br><input type='button' name='btnIngresarGFarmacologico' id='btnIngresarGFarmacologico' value='     Modificar Medicamentos de Produccion   ' onClick='ModGFarmacologico()'></label></div></td></tr>");

out.print("<tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Agregar Cte de Volumen</div></td></tr>");
	out.print("<tr><td>Cte de Volumen: </td><td><lable></label><input type=text id='CteV' size='8'/></td><td> Descripcion :</td>");
	
	out.print("<td ><select id='Desci'><option value='Seleccione'>Seleccione</option>");
	out.print("<option value='N/A' title='NO APLICA'>N/A</option>");
	out.print("<option value='BOLSA' title='BOLSA'>BOLSA</option>");
	out.print("<option value='JERINGA PRELLENADA' title='JERINGA PRELLENADA'>JP</option>");
	out.print("</select></td>");
	
	out.print("<td colspan='2'><div align='left'><label><input type='button' name='btn' id='btn' title='Adicionar Cte de Volumen' value=' + ' onClick='AgreCteV("+CodmedH+")' ></label></div></td></tr>");
	
	out.print("</td></tr><tr><td colspan='12'><div align='left'><label><input type='button' name='btnMP' id='btnMP' value='  Modificar ' onClick='ModHabMed("+CodmedH+","+CodCteV+")' ></label></div></td></tr>");
	out.print("</table>");
	}

	if(va.equals("ModHabMed")){
	
	String cm=request.getParameter("cm");
	String concen=request.getParameter("concen");
	String diluy=request.getParameter("diluy");
	String grupof=request.getParameter("grupof");
	String codi=request.getParameter("codi");
	String VR=request.getParameter("VR");
	String VAdm=request.getParameter("VAdm");
	String Est=request.getParameter("Est");
	String CodmedH=request.getParameter("CodmedH");
	String CodCteV=request.getParameter("CodCteV");
	String Val=request.getParameter("Val");
	String Desc=request.getParameter("Desc");
	me.ModMedh(cm,concen,diluy,grupof,codi,VR,VAdm,Est,CodmedH);
	me.ModCteV(CodCteV, Val, Desc);
	out.print("Modificacion Exitosa !!");
	}

	if(va.equals("GuarCteV")){
		String valor=request.getParameter("cte");
		String desc=request.getParameter("desc");
		String codigo=request.getParameter("codmed");
		me.CrearConstVol(codigo, valor, desc);
		out.print("Constante Adicionada con exito !!");
	}

		if(va.equals("HabMed")){
			String cm=request.getParameter("cm");
			String Nconst=request.getParameter("Nconst");
			String grupof=request.getParameter("grupof");
			String concen=request.getParameter("concen");
			String diluy=request.getParameter("diluy");
			String codi=request.getParameter("codi");
			String VR=request.getParameter("VR");
			String VAdm=request.getParameter("VAdm");
			String Est=request.getParameter("Est");
			String usuario=request.getParameter("user");
			System.out.println("DESAGRACIADA ESTABILIDA"+Est);
			int i=0;
			System.out.println("Nconst"+Nconst);
			int entero=Integer.parseInt(Nconst);
			System.out.println("entero"+entero);
			rs=me.ObtenerMedOrden(cm);
			String codigo="";
			try {
				while(rs.next()){
					codigo=rs.getString(1);
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
						
			System.out.println("codigo "+codigo);
			if(codigo.equals("")){
				System.out.println("PRIMERA VALIDACION CUANDO CODIGO ES IGUAL A BLANCO");
				me.CrearMedOrdenP(cm,grupof,concen,diluy,codi,VR,Est,VAdm);
				out.print("<table class='style6'><tr width='100%'><td colspan='8'><div align='center' class='style11' id='cabecera2'>Asignacion de Constante de Volumen</div></td></tr>");
				out.print("<tr><td>Valor</td><td>Descripcion</td></tr>");
				for(i=0;i<entero;i++){
					out.print("<tr width='60%'><td width='15%'><label><input type='text' size=10 id='Val"+i+"' /><label></td>");
					out.print("<td width='15%'><select id='Desc"+i+"'><option value='Seleccione'>Seleccione</option>");
					out.print("<option value='N/A'>N/A</option>");
					out.print("<option value='BOLSA'>BOLSA</option>");
					out.print("<option value='JERINGA PRELLENADA'>JERINGA PRELLENADA</option>");
					out.print("</select></td></tr>");
					//out.print("entrando al para valor de i "+i);
				}
				rs=me.ObtenerMedOrdenP(cm,grupof,concen,diluy,codi,VR,VAdm,Est);
				try {
					while(rs.next()){
						codigo=rs.getString(1);
						System.out.println("CODI"+codigo);
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				out.print("<tr><td><br><br><input type='button' id='btnFinalH' value='  Finalizar Habilitacion ' onclick='FinHab("+entero+","+codigo+")'></td></tr>");
			}else{
				/*rs=me.BuscarMedCtaVolumen(codigo);  
				String codigo2="";
				
				try {
					while(rs.next()){
						codigo2=rs.getString(1);
						System.out.println(" Val 2 codigo2"+codigo2);
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(codigo2.equals("")){
					
					me.CrearMedOrdenP(cm,grupof,concen,diluy,codi,VR,VAdm,Est);
					out.print("<table class='style6'><tr width='100%'><td colspan='8'><div align='center' class='style11' id='cabecera2'>Asignacion de Constante de Volumen</div></td></tr>");
					out.print("<tr><td>Valor</td><td>Descripcion</td></tr>");
					for(i=0;i<entero;i++){
						out.print("<tr width='60%'><td width='15%'><label><input type='text' size=10 id='Val"+i+"' /><label></td>");
						out.print("<td width='15%'><select id='Desc"+i+"'><option value='Seleccione'>Seleccione</option>");
						out.print("<option value='N/A'>N/A</option>");
						out.print("<option value='BOLSA'>BOLSA</option>");
						out.print("<option value='JERINGA PRELLENADA'>JERINGA PRELLENADA</option>");
						out.print("</select></td>");
						//out.print("entrando al para valor de i "+i);
					}
					rs=me.ObtenerMedOrdenP(cm,grupof,concen,diluy,codi,VR,VAdm,Est);
					try {
						while(rs.next()){
							codigo=rs.getString(1);
						}
						rs.getStatement().getConnection().close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					out.print("<tr><td><br><br><input type='button' id='btnFinalH' value='  Finalizar Habilitacion ' onclick='FinHab("+entero+","+codigo+")'></td></tr>");
					
				}else{ */
			 out.println("<tr><td align='center'><H1><font color='red'>NO PUEDE HABILITAR ESE MEDICAMENTO PORQUE YA EXISTE UNO EN SISTEMA, SI DESEA MODIFIQUE EL MEDICAMENTO </font></td><tr>");
			/* }*/
			}
			
			out.print("</table>");
		}
		
		if(va.equals("GMedOrd")){
			String codigo=request.getParameter("codigo");
			String valor=request.getParameter("valor1");
			String desc=request.getParameter("desc");
			rs=me.ObtenerConstVol(codigo,valor,desc);
			System.out.println("codigo "+codigo);
			System.out.println("valor "+valor);
			System.out.println("desc "+desc);
			String codCons="";
			try {
				while(rs.next()){
					codCons=rs.getString(1);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//int entero=Integer.parseInt(Nconst);
			if(codCons.equals("")){
				me.CrearConstVol(codigo,valor,desc);
				out.print("Creado exitosamente");
			}else{
				out.print("No se creo la constante porque ya existe");
			}
			
			
		}

		
		if(va.equals("Vencimiento")){	
			out.print("<table width='100%' class='style6'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Relación de Productos Vencidos o por Vencer</div></td></tr>");
			out.print("<tr><td><label><input name='radiobuttonv' type='radio' value='radiobuttonv' id='xvence' onClick='Seleccionev()' />Productos por Vencer</label></td>");
			out.print("<td><label><input name='radiobuttonv' type='radio' value='radiobuttonv' id='vence' onClick='Seleccionev()' />Productos Vencidos</label></td></tr>");
			//out.print("<option value='todas'>TODAS LAS BODEGAS</option>");    					
		} 
		 
		if(va.equals("xvence")){	
			out.print("<table width='100%' class='style6'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Relación de Productos Vencidos o por Vencer</div></td></tr>");
			
			out.print("<tr><td><label><input name='radiobuttonv' type='radio' value='radiobuttonv' id='xvence' onClick='Seleccionev()' />Productos por Vencer</label></td>");
			out.print("<td><label><input name='radiobuttonv' type='radio' value='radiobuttonv' id='vence' onClick='Seleccionev()' />Productos Vencidos</label></td></tr>");
			
			out.print("<table width='100%' class='style6'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Productos por Vencer</div></td></tr>");
			
			out.print("<tr><td width='12%'>Seleccione Bodega</td><td><label><select name='cmbBodegav' id='cmbBodegav'><option value='Seleccione'>Seleccione</option>");
			out.print("<option value='todas'>TODAS LAS BODEGAS</option>");
			rs=me.ObtenerBodegas(user);
			try {
				while(rs.next()){
				out.print("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("</select></label></td></tr>");	
			 
			out.print("<td colspan='4'><label><input type='button' name='btnConsultarE' id='btnConsultarE' value='     CONSULTAR     ' onClick='Consultarxv()'></label></td>");
						
		}  
		
		if(va.equals("vence")){	
			out.print("<table width='100%' class='style6'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Relación de Productos Vencidos o por Vencer</div></td></tr>");
			
			out.print("<tr><td><label><input name='radiobuttonv' type='radio' value='radiobuttonv' id='xvence' onClick='Seleccionev()' />Productos por Vencer</label></td>");
			out.print("<td><label><input name='radiobuttonv' type='radio' value='radiobuttonv' id='vence' onClick='Seleccionev()' />Productos Vencidos</label></td></tr>");
			
			out.print("<table width='100%' class='style6'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Productos Vencidos</div></td></tr>");
			
			out.print("<tr><td width='12%'>Seleccione Bodega</td><td><label><select name='cmbBodegav' id='cmbBodegav'><option value='Seleccione'>Seleccione</option>");
			out.print("<option value='todas'>TODAS LAS BODEGAS</option>");
			rs=me.ObtenerBodegas(user);
			try {
				while(rs.next()){
				out.print("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("</select></label></td></tr>");	
			
			out.print("<td colspan='4'><label><input type='button' name='btnConsultarE' id='btnConsultarE' value='     CONSULTAR     ' onClick='Consultarv()'></label></td>");
						
		}  
		
		
		if(va.equals("autoart")){
			try {
				rs =ment.listarArticulos(texto);
				String cadena ="";
				cadena="[";
				while(rs.next()){
					cadena = cadena+"'"+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5)+" "+rs.getString(6)+"|"+rs.getString(1)+"|"+rs.getString(7)+"'";
                	cadena = cadena +",";	
				}
				cadena = cadena+"]";
				response.getWriter().write(cadena);
				rs.getStatement().getConnection().close();
			} catch (Exception e) {
				e.getMessage();
				e.printStackTrace();
			}
		}
		
		
		
	/*	if(va.equals("1")){		
			
			String cb = request.getParameter("cb");
			String ca = request.getParameter("ca");
			int n=-1;
		 	String sql="";
			if(cb.equals("todas")){
			sql="select m.nombre, m.concentracion, i.lote, i.vencimiento, i.cantidad, i.vunitario, iv.valor, i.vtotal, e.nombre as empresa, e.nit, e.logo from medicamentos m, farc_inventario i, farc_iva iv, empresa e, farc_bodegas b where m.codigo=i.cod_medFK and iv.codigo=i.cod_ivaFK and i.cod_bodegaFk='"+n+"' and i.cod_bodegaFk=b.codigo order by m.nombre";
			}else{
			sql="select m.nombre, m.concentracion, i.lote, i.vencimiento, i.cantidad, i.vunitario, iv.valor, i.vtotal, e.nombre as empresa, e.nit, e.logo from medicamentos m, farc_inventario i, farc_iva iv, empresa e, farc_bodegas b where m.codigo=i.cod_medFK and iv.codigo=i.cod_ivaFK and i.cod_bodegaFk='"+cb+"' and i.cod_bodegaFk=b.codigo order by m.nombre";
			}
			out.print(sql);
			out.print(bode);
		}*/
		
	/*	if(va.equals("Traslados2")){	
			out.print("<table width='100%' class='style6'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Salida de Articulos</div></td></tr>");
			out.print("<tr><td width='12%'>Bodega de Origen</td><td><label><select name='cmbBodegao' id='cmbBodegao' onchange='vbodegad()'>");
			rs=mt.ObtenerBodegaso(bo);
			try {
				while(rs.next()){
				out.print("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			rs=mt.ObtenerBodegasd(bo);
			try {
				while(rs.next()){
				out.print("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("</select></label></td>");	
			
			out.print("<td width='12%'>Bodega de Destino</td><td><label><select name='cmbBodegad' id='cmbBodegad' onfocus='vbodega()' ><option value='Seleccione'>Seleccione</option>");
			rs=mt.ObtenerBodegasd(bo);
			//rs=mt.ObtenerBodegas();
			try {
				while(rs.next()){
				out.print("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("</select></label></td></tr>");
			
			out.print("<tr><td>Numero de Soporte</td><td><label><input name='txtFactura' type='text' id='txtFactura' size='40'/></td>");
			out.print("<td>Fecha</td><td width='45%'><label><input name='txtFecha' type='text' id='txtFecha' value='"+fecha+"' readonly='' size='40' /></label></td></tr>");
			out.print("<tr><td>Concepto</td><td><label><input name='txtConcepto' type='text' id='txtConcepto' size='40'/></td></tr>");
					
			out.print("<table width='100%' class='style6'><tr><td colspan='2'><div align='center' class='style11' id='cabecera2'>Generar Movimientos</div></td></tr>");
					
			out.print("<table width='100%' border='1' class='style6'><tr><td width='28%'><div align='center'>Articulo</div></td><td width='9%'><div align='center'>Lote</div></td><td width='9%'><div align='center'>Codigo Invima</div></td><td width='8%'><div align='center'>Vence</div></td><td width='8%'><div align='center'>Existencias</div></td><td width='10%'><div align='center'>Vlr Unitario</div></td><td width='20%'><div align='center'>Proveedor</div></td><td width='8%'><div align='center'>Cantidad </div></td></tr>");
			
			out.print("<td><input name='txtTipoMe0' type='text' id='txtTipoMe0' onfocus='vbodega()' onkeyup='autocompletaInventarioT(0,0)' size='39' />");//identificador Div+identificador campo
			out.print("<input name='txtTipoMeH0' type='hidden' id='txtTipoMeH0'/></td>");//identificador Div+identificador campo
			out.print("<td><label><input name='txtLote0' type='text' id='txtLote0' readonly='' size='8'/></td>");
			out.print("<td><label><input name='txtInvima0' type='text' id='txtInvima0' readonly='' size='8'/></td>");
			out.print("<td><label><input name='txtVence0' type='text' id='txtVence0' readonly='' size='7'/></td>");
			out.print("<td><label><input name='txtCantidad0' type='text' id='txtCantidad0' readonly='' size='7'/></td>");
			out.print("<td><label><input name='txtVunitario0' type='text' id='txtVunitario0'  readonly='' size='10'/></td>");
			out.print("<td width='4%'><label><input name='txtProvee0' type='text' id='txtProvee0' readonly='' size='26'/>");
			out.print("<input name='txtInv0' type='hidden' id='txtInv0' readonly='' size='26'/>");
			out.print("<td><label><input name='txtCa0' type='text' id='txtCa0' onkeyup='checknum3(0)' onBlur='Repetirt("+cont+",0,this.form)'  size='7'/></td>");//value='"+vt+"'   
			
			out.print("<tr><td><div id='sugerencias10'></div></td><td></td><td></td><td><div id='sugerencias20'></div></td></tr>");
			
		
			out.print("</table>"); 
				out.print("<div id='repetir'></div>");
			//out.print("<tr><td colspan='4'>&nbsp;</td></tr>");
			
			//rs = me.ObtenerMovimiento(TipoM);
		}*/
	////////////////////////////////////////////////////////////////////////////////////////////////////////	
	/*	if(va.equals("EntradasRepetidas")){		
			//out.print("contadores "+conta);
			
			String a0 = request.getParameter("a0");
			char s ;
			char s2='@';
			String ini="";
			String fin="";
			for(int q=0; q<a0.length();q++){
				s=a0.charAt(q);
				if(s==s2){//out.println("encontrado en "+q+" es "+s);
				ini=ini+"%";}else{
				ini=ini+s;
				}
			}
							
			String a1 = request.getParameter("a1");
			String a2 = request.getParameter("a2");
			String a3 = request.getParameter("a3");
			String a4 = request.getParameter("a4");
			String a5 = request.getParameter("a5");
			String a6 = request.getParameter("a6");
			String a7 = request.getParameter("a7");
			String a8 = request.getParameter("a8");
			String a9 = request.getParameter("a9");
			
			datos2=datos2+ini;datos2=datos2+"|";
			datos2=datos2+a1;datos2=datos2+"|";
			datos2=datos2+a2;datos2=datos2+"|";
			datos2=datos2+a3;datos2=datos2+"|";
			datos2=datos2+a4;datos2=datos2+"|";
			datos2=datos2+a5;datos2=datos2+"|";
			datos2=datos2+a6;datos2=datos2+"|";
			datos2=datos2+a7;datos2=datos2+"|";
			datos2=datos2+a8;datos2=datos2+"|";
			datos2=datos2+a9;datos2=datos2+"|";
			
			//llena los datos que provienen de repetir
			int i1=0;   
			StringTokenizer elementos;  
			elementos = new StringTokenizer(datos2,"|"); 
			   
			   while(elementos.hasMoreTokens()){ 
				   V[i1] = elementos.nextToken();   
				   i1++;
			   }
			   
			   int in=0;
			   for(int z=0; z<conta; z++){
				   for(int zz=0; zz<=9; zz++){
					   MI[z][zz]=V[in];
					   in++;
				   }
			   }
			
			//out.print("Dtossss  "+datos2);
			
			int ind=0;
			out.print("<table width='100%' border='1' class='style6'>");
			for(int i=0;i<conta;i++){
				//out.print("esta es i: "+i);
				int j=i+1;
				//out.print("JJJJJ"+j);
				String art="txtTipoMe"+j;
				String artH="txtTipoMeH"+j;
				String lot="txtLote"+j;
				String inv="txtInvima"+j;
				String vence="txtVence"+j;
				String can="txtCantidad"+j;
				String vau="txtVunitario"+j;
				String pro="txtProvee"+j;
				String inventa="txtInv"+j;
				String ca="txtCa"+j;
				String txtM="txtM"+j;
				String sug1="sugerencias1"+j;
				
				for(int z=0; z<=9; z++){
				ind=z+(j*10);
				M[i][z]=V[ind];
				//out.print("fila "+(i)+" col"+z+" : "+M[i][z]);
				}
				
			//	String sug2="sugerencias2"+j;
			//	out.print("txtM0 "+datos2+"  txtMJ: "+j);
			//	out.print("conta es igual a "+conta);
				
			
				
				
				if((i==(conta-1)&&(i!=8))){
					out.print("<td><input name='"+art+"' type='text' id='"+art+"' onkeyup='autocompletaInventarioT("+j+","+j+")'  size='39' />");//identificador Div+identificador campo
					out.print("<input name='"+artH+"' type='hidden' id='"+artH+"' /></td>");//identificador Div+identificador campo
					out.print("<td><label><input name='"+lot+"' type='text' id='"+lot+"'  readonly='' size='8'/></td>");
					out.print("<td><label><input name='"+inv+"' type='text' id='"+inv+"'  readonly='' size='8'/></td>");
					out.print("<td><label><input name='"+vence+"' type='text' id='"+vence+"'  readonly='' size='7'/></td>");
					out.print("<td><label><input name='"+can+"' type='text' id='"+can+"'  readonly='' size='7'/></td>");
					out.print("<td><label><input name='"+vau+"' type='text' id='"+vau+"'  readonly='' size='10'/></td>");
					out.print("<td width='4%'><label><input name='"+pro+"' type='text' id='"+pro+"' readonly='' size='26'/>");
					out.print("<input name='"+inventa+"' type='hidden' id='"+inventa+"'  readonly='' size='26'/>");
					out.print("<td><label><input name='"+ca+"' type='text' id='"+ca+"' onfocus='anterior("+j+")' onkeyup='checknum3("+j+")' onBlur='Repetirt("+cont+","+j+",this.form)'  size='7'/></td>");//value='"+vt+"'   
					out.print("<input name='"+txtM+"' type='hidden' id='"+txtM+"' value='"+datos2+"' /></td>");//value='"+datos2+"'  identificador Div+identificador campo
					
					out.print("<tr><td><div id="+sug1+"></div></td><td></td><td></td><td><div id='sugerencias20'></div></td></tr>");			
				}
				
				if((conta>1)&&(i<(conta-1))){
					out.print("<td><input name='"+art+"' type='text' id='"+art+"' value='"+M[i][0]+"' onkeyup='autocompletaInventarioT("+j+","+j+")'  size='39' />");//identificador Div+identificador campo
					out.print("<input name='"+artH+"' type='hidden' id='"+artH+"' value='"+M[i][1]+"'/></td>");//identificador Div+identificador campo
					out.print("<td><label><input name='"+lot+"' type='text' id='"+lot+"' value='"+M[i][2]+"' readonly='' size='8'/></td>");
					out.print("<td><label><input name='"+inv+"' type='text' id='"+inv+"' value='"+M[i][3]+"' readonly='' size='8'/></td>");
					out.print("<td><label><input name='"+vence+"' type='text' id='"+vence+"' value='"+M[i][4]+"' readonly='' size='7'/></td>");
					out.print("<td><label><input name='"+can+"' type='text' id='"+can+"' value='"+M[i][5]+"' readonly='' size='7'/></td>");
					out.print("<td><label><input name='"+vau+"' type='text' id='"+vau+"' value='"+M[i][6]+"' readonly='' size='10'/></td>");
					out.print("<td width='4%'><label><input name='"+pro+"' type='text' value='"+M[i][7]+"'id='"+pro+"' readonly='' size='26'/>");
					out.print("<input name='"+inventa+"' type='hidden' id='"+inventa+"' value='"+M[i][8]+"' readonly='' size='26'/>");
					out.print("<td><label><input name='"+ca+"' type='text' id='"+ca+"' onfocus='anterior("+j+")' onkeyup='checknum3("+j+")' onBlur='Repetirt("+cont+","+j+",this.form)'  value='"+M[i][9]+"' size='7'/></td>");//value='"+vt+"'   
					out.print("<input name='"+txtM+"' type='hidden' id='"+txtM+"' value='"+datos2+"' /></td>");//value='"+datos2+"'  identificador Div+identificador campo
					
					out.print("<tr><td><div id="+sug1+"></div></td><td></td><td></td><td><div id='sugerencias20'></div></td></tr>");
				}
				
				if(i==8){
					for(int ss=0; ss<=9; ss++){ if (M[i][ss]==null){M[i][ss]="";}}
					out.print("<td><input name='"+art+"' type='text' id='"+art+"' value='"+M[i][0]+"' onkeyup='autocompletaInventarioT("+j+","+j+")'  size='39' />");//identificador Div+identificador campo
					out.print("<input name='"+artH+"' type='hidden' id='"+artH+"' value='"+M[i][1]+"'/></td>");//identificador Div+identificador campo
					out.print("<td><label><input name='"+lot+"' type='text' id='"+lot+"' value='"+M[i][2]+"' readonly='' size='8'/></td>");
					out.print("<td><label><input name='"+inv+"' type='text' id='"+inv+"' value='"+M[i][3]+"' readonly='' size='8'/></td>");
					out.print("<td><label><input name='"+vence+"' type='text' id='"+vence+"' value='"+M[i][4]+"' readonly='' size='7'/></td>");
					out.print("<td><label><input name='"+can+"' type='text' id='"+can+"' value='"+M[i][5]+"' readonly='' size='7'/></td>");
					out.print("<td><label><input name='"+vau+"' type='text' id='"+vau+"' value='"+M[i][6]+"' readonly='' size='10'/></td>");
					out.print("<td width='4%'><label><input name='"+pro+"' type='text' value='"+M[i][7]+"'id='"+pro+"' readonly='' size='26'/>");
					out.print("<input name='"+inventa+"' type='hidden' id='"+inventa+"' value='"+M[i][8]+"' readonly='' size='26'/>");
					out.print("<td><label><input name='"+ca+"' type='text' id='"+ca+"' onfocus='anterior("+j+")' onkeyup='checknum3("+j+")' onBlur='Repetirt("+cont+","+j+",this.form)'  value='"+M[i][9]+"' size='7'/></td>");//value='"+vt+"'   
					out.print("<input name='"+txtM+"' type='hidden' id='"+txtM+"' value='"+datos2+"' /></td>");//value='"+datos2+"'  identificador Div+identificador campo
					
					out.print("<tr><td><div id="+sug1+"></div></td><td></td><td></td><td><div id='sugerencias20'></div></td></tr>");
				}
			}//fin del if desde 0 a contador
		}// fin entradas repetidas
*/
		//////////////////////////////////////////////////////////////
		
/*		if(va.equals("Ingreso")){	
		
		Calendar calendario = Calendar.getInstance();
		int hora, minutos, segundos;
		hora =calendario.get(Calendar.HOUR_OF_DAY);
		minutos = calendario.get(Calendar.MINUTE);
		segundos = calendario.get(Calendar.SECOND);
		String hra= hora+":"+minutos+":"+segundos;
		
		String ing = request.getParameter("ing");
		String nE = request.getParameter("nE");
		String bodo = request.getParameter("bodegao");
		String bodd = request.getParameter("bodegad");
		String fact = request.getParameter("factura");
		String conc = request.getParameter("concepto");
		String user = request.getParameter("user");
		String fech = request.getParameter("fechas");
		String dias=fech.substring(0, 2);
		String meses=fech.substring(3, 5);
		String anos=fech.substring(6, 10);
		String fec=anos+"-"+meses+"-"+dias;
	
		StringTokenizer elementos;  
		elementos = new StringTokenizer(ing,"|"); 
		int i2=0;
		while(elementos.hasMoreTokens()){ 
		  V[i2] = elementos.nextToken();   
		  i2++;
		}
		//out.print("I2longitud: "+i2);
	//	for(int f=0;f<i2;f++){
	//		out.print(" V["+f+"]: "+V[f]);
	//	}
		int fin=i2/10;
		//int r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13;
		//out.print("vector: "+fin);
		if(i2>0){
			//esta seccion ingresa el movimiento
			int cant=0;	
			for(int z3=0; z3<fin; z3++){
				cant=cant+Integer.parseInt(V[z3*10+9]);
			}
			String cants=String.valueOf(cant);
			String inventario=null;
			//out.print(" la cantidad total es : "+cants);
			
			String t="";
			rs3=mt.ObtenerCodTraslado();
			try {
				while(rs3.next()){
				t=rs3.getString(1);//esta variable es el cod del movimiento
				}
				rs3.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			//out.print(" El codigo del mov es : "+t);
			mt.CrearMovimientos(bodo, cants, t, fec, hra, user, fact, conc);
			
			/////////////////////////////////////////////////
			//rescatar el ultimo movimiento
			rs1=mt.ObtenerUMovimiento(fec,hra);
			String movi="";
			try {
				while(rs1.next()){
				movi=rs1.getString(1);//esta variable es el cod del movimiento
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			/////
			 for(int z5=0; z5<fin; z5++){
				// out.print(" el movi : "+movi);
				// out.print(" el v[z5*10+8] : "+V[z5*10+8]);
				mt.CrearDetalle(movi,V[z5*10+8]);	
			 }		 
		
			 
			 for(int z2=0; z2<fin; z2++){
			 int exis=Integer.parseInt(V[z2*10+5]);
			 int cantid=Integer.parseInt(V[z2*10+9]);
			 int to=exis-cantid;
			 String paso=String.valueOf(to);
			 //out.print(" Lo q queda la "+z2+1+" es: "+paso);
			 mt.CrearSalidas(V[z2*10+8],paso);	
			 }	 
			 
			 for(int z2=0; z2<fin; z2++){
				 int cantid=Integer.parseInt(V[z2*10+9]);
				 
				 String tt="";
					rs3=mt.ObtenerIva(V[z2*10+8]);
					try {
						while(rs3.next()){
						tt=rs3.getString(1);//esta variable es el cod del movimiento
						}
						rs3.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}

					String vi="";
					rs5=mt.ObtenerVIva(tt);
					try {
						while(rs5.next()){
						vi=rs5.getString(1);//esta variable es el cod del movimiento
						}
						rs5.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					
					String pp="";
					rs4=mt.ObtenerPro(V[z2*10+8]);
					try {
						while(rs4.next()){
						pp=rs4.getString(1);//esta variable es el cod del movimiento
						}
						rs4.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					
					int ic=Integer.parseInt(V[z2*10+9]);
					int ivu=Integer.parseInt(V[z2*10+6]);
					int ivi=Integer.parseInt(vi);
					
					//out.print("Error "+ic+" : "+ivu+" : "+ivi);
				    float vato = ((ic*ivu)*((ivi/100f)+1));
				    //out.print(" vato "+vato);
				    int vatofi = Math.round(vato*100)/100 ;
				    //out.print(" vatofi "+vatofi);
				    String vatofis=String.valueOf(vatofi);
		//out.print("Esto es lo q mando"+movi+" : "+V[z2*10+1]+" : "+V[z2*10+4]+" : "+V[z2*10+2]+" : "+V[z2*10+9]+" : "+V[z2*10+6]+" xxx: "+vatofi+" : "+tt+" : "+bodd+" : "+fec+" : "+hra+" : "+pp+" : "+V[z2*10+3]);
		// movimiento, articulo, vence,  lote,  cantidad,  vunitario,  vtotal,  iva,  bodega,  fecha,  hra,  proveedor, invima
			
				 mt.CrearEntradasT(movi,V[z2*10+1],V[z2*10+4],V[z2*10+2],V[z2*10+9],V[z2*10+6],vatofis,tt,bodd,fec,hra,pp,V[z2*10+3]);
				 }	
			 
			 	 out.print(movi);
		//out.print("Ingreso Exitoso.");
			}
			//response.sendRedirect("nombrejsp.jsp?vatofis="+ing+"&f="+meses);
		 
		}
*/		
		/////////////////////////////////////////////////////////////
			
	/*	if(va.equals("autoinv")){
			
			try {
				//System.out.print("ESSSTOO."+xx);
				rs =mt.listarArticulos(texto,xx);
				String cadena ="";
				cadena="[";
				while(rs.next()){
					cadena = cadena+"'"+rs.getString(2)+" "+rs.getString(3)+"|"+rs.getString(1)+"|"+rs.getString(4)+"|"+rs.getString(5)+"|"+rs.getString(6)+"|"+rs.getString(7)+"|"+rs.getString(8)+"|"+rs.getString(9)+"|"+rs.getString(10)+"'";
                	cadena = cadena +",";	
				}
				cadena = cadena+"]";
				//System.out.print("ESSSTOO."+cadena);
				response.getWriter().write(cadena);
				rs.getStatement().getConnection().close();
			} catch (Exception e) {
				e.getMessage();
				e.printStackTrace();
			}
		}
		*/
		
		
	}

}
