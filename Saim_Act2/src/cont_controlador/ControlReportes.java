package cont_controlador;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Image;

import cont_metodo.MetodoProveedor;
import cont_metodo.MetodoReportes;

public class ControlReportes extends HttpServlet{
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 
	}
	
	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String va = req.getParameter("valor");
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out;
		out=res.getWriter();
		MetodoReportes mpr= new MetodoReportes();
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		ResultSet rs3=null;
		ResultSet rs4=null;
		
		
		if(va.equals("cpraux")){
			out.print("<table width='100%' border='0'  >");
			out.print("<tr><td colspan='8' id='cabecera2' class='style11' align='center'>REPORTES DE AUXILIARES </td> </tr>");
			out.print("<tr><td>Seleccione el tipo de informe a consultar : <select id='tipoCpraux' onChange='TipoCpraux()'><option>---</option><option value='1'> GENERAL </option><option value='2'> POR CUENTA Y POR TERCERO </option><option value='3'>POR SUCURSAL Y CUENTA </option><option value='4'>POR SUCURSAL  - CENTRO DE COSTO Y CUENTA </option></td></tr>");
			out.print("<tr><td><div id='menucpraux' ></div></td></tr>");
			
		}
		
		if(va.equals("menucpraux")){
			String TipoCpraux=req.getParameter("TipoCpraux");
			
			if(TipoCpraux.equals("1")){
						/***REPORTE DE AUXILIAR DE CONTABILIDAD GENERAL***/
						out.print("<table width='100%' border='0'  >");
						out.print("<tr><td colspan='6' id='cabecera2' class='style11' align='center'>REPORTE AUXILIAR DE CONTABILIDAD GENERAL</td> </tr>");
						//out.print("<tr><td>Digite los siguientes Campos </td>");
						out.print("<tr><td><div ><b>A&ntilde;o: </b> &nbsp; &nbsp; <input name='AC' type='text' id='AC' size='3' onKeyup='valAnoPeriodo(this,patronp,true,1)'  />&nbsp; &nbsp;  <b> Periodo: &nbsp;  &nbsp; &nbsp; </b><input name='MC' type='text' id='MC' size='2' onKeyup='valAnoPeriodo(this,patronp,true,0)' /> &nbsp; <b> al </b> &nbsp; <input name='MC2' type='text' id='MC2' size='2' onKeyup='valAnoPeriodo(this,patronp,true,0)' /> &nbsp; &nbsp; &nbsp; <b> Tipo de Documento: </b> &nbsp; <select id='TDOC'> <option value='todas'>TODAS</option>");
						rs=mpr.BuscarTDoc();
						try {
							while(rs.next()){
								out.print("<option value='"+rs.getString(1)+"' title='"+rs.getString(2)+"' >"+rs.getString(3)+"</option>");
							}rs.getStatement().getConnection().close();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						out.print("</select></td></tr>"+
									"<tr><td > <br>  <b> Rango de Cuentas: </b> <br></td> </tr>");
						out.print("<tr><td><input type='text'   id='cta0' onkeyup='autocompletaCta8(0,0)' onBlur='limpcta(0)'	size='10' value='TODAS'/> ");
						out.print("<input type=hidden id='ctah0' ><input type=hidden id='ctahh0' >");
						out.print("&nbsp; &nbsp; al &nbsp; &nbsp;<input type='text'   id='RCC0' onkeyup='autocompletaCta9(0,0)' onBlur='limpcta(0)'	size='10' value='TODAS' />");
						out.print("</td></tr>");
						out.print("<tr><td width='30%'><div id='dcta0'  width='30%'/></td><td width='30%' align='left'><div id='dctaa0'  width='30%' /></td></tr>");
								/*	"<tr><td><select id='RC1' style='width:350px' ><option value='Seleccione'>Seleccione</><option value='todas'>Todas</option> ");
						rs=mpr.BuscarCuentas3();
						try {
							while(rs.next()){
								out.print("<option value='"+rs.getString(1)+"' title='"+rs.getString(2)+"'>"+rs.getString(1)+" "+rs.getString(2)+"</option>");
							}
							rs.getStatement().getConnection().close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						out.print("</select>");
						out.print("&nbsp; &nbsp; al &nbsp; &nbsp;<select id='RC2' style='width:350px'><option value='Seleccione'>Seleccione</> <option value='todas'>Todas</option> ");
						rs=mpr.BuscarCuentas3();
						try {
							while(rs.next()){
								out.print("<option value='"+rs.getString(1)+"' title='"+rs.getString(2)+"'>"+rs.getString(1)+" "+rs.getString(2)+"</option>");
							}
							rs.getStatement().getConnection().close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						out.print("</select></div></td>");*/
						out.print("<td colspan='2' align='center'><input type='button' onclick='ConsultarRepAuxiliarGeneral()' id='Bot0' value='Consultar Reporte '></td></tr></table>");
			}else{
				if(TipoCpraux.equals("2")){
					/***REPORTE DE AUXILIAR DE CONTABILIDAD POR CUENTA Y TERCERO***/
					out.print("<table width='100%' border='0'  >");
					out.print("<tr><td colspan='6' id='cabecera2' class='style11' align='center'>REPORTE AUXILIAR DE CONTABILIDAD POR CUENTA Y TERCERO</td> </tr>");
					//out.print("<tr><td>Digite los siguientes Campos </td>");
					out.print("<tr><td><div ><b>A&ntilde;o: </b> &nbsp; <input name='AC' type='text' id='AC' size='3' onKeyup='valAnoPeriodo(this,patronp,true,1)'  />&nbsp; &nbsp;  <b> Periodo: &nbsp;  &nbsp; </b><input name='MC' type='text' id='MC' size='2' onKeyup='valAnoPeriodo(this,patronp,true,0)' /> &nbsp; <b> al </b> &nbsp; <input name='MC2' type='text' id='MC2' size='2' onKeyup='valAnoPeriodo(this,patronp,true,0)' /> &nbsp; &nbsp; <b> Tipo de Documento: </b> &nbsp; <select id='TDOC'> <option value='todas'>TODAS</option>");
					rs=mpr.BuscarTDoc();
					try {
						while(rs.next()){
							out.print("<option value='"+rs.getString(1)+"' title='"+rs.getString(2)+"' >"+rs.getString(3)+"</option>");
						}
						rs.getStatement().getConnection().close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					out.print("</select>&nbsp; <b> Tipo de Tercero: </b> <input type=text id='ter0'  onkeyup='autocompletarTercero1(0,0)' onBlur='limpiater(0)' value='TODAS' size='10'/> ");
					out.print("</div></td></tr><input type=hidden id='terh0' value='TODAS'><tr><td><div id='dter0' ></div></td></tr>"+
								"<tr><td > <br>  <b> Rango de Cuentas: </b> <br></td> </tr>"+
								"<tr><td>");
					out.print("<input type='text'   id='cta0' onkeyup='autocompletaCta8(0,0)' onBlur='limpcta(0)'	size='10' value='TODAS'/> ");
					out.print("<input type=hidden id='ctah0' ><input type=hidden id='ctahh0' >");
					out.print("&nbsp; &nbsp; al &nbsp; &nbsp;<input type='text'   id='RCC0' onkeyup='autocompletaCta9(0,0)' onBlur='limpcta(0)'	size='10' value='TODAS' />");
					out.print("</td></tr>");
					out.print("<tr><td width='30%'><div id='dcta0'  width='30%'/></td><td width='30%' align='left'><div id='dctaa0'  width='30%' /></td></tr>");
					/*rs=mpr.BuscarTercero(); 
					try {
						while(rs.next()){
							out.print("<option value='"+rs.getString(1)+"' title='"+rs.getString(3)+"' >"+rs.getString(2)+"</option>");
						}
						out.print("</select></td></tr>");
						rs.getStatement().getConnection().close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
							out.print("<tr><td > <br>  <b> Rango de Cuentas: </b> <br></td> </tr>"+
								"<tr><td><select id='RC1' style='width:350px' ><option value='Seleccione'>Seleccione</><option value='todas'>Todas</option> ");
					rs=mpr.BuscarCuentas3();
					try {
						while(rs.next()){
							out.print("<option value='"+rs.getString(1)+"' title='"+rs.getString(2)+"'>"+rs.getString(1)+" "+rs.getString(2)+"</option>");
						}
						rs.getStatement().getConnection().close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					out.print("</select>");
					out.print("&nbsp; &nbsp; al &nbsp; &nbsp;<select id='RC2' style='width:350px'><option value='Seleccione'>Seleccione</> <option value='todas'>Todas</option> ");
					rs=mpr.BuscarCuentas3();
					try {
						while(rs.next()){
							out.print("<option value='"+rs.getString(1)+"' title='"+rs.getString(2)+"'>"+rs.getString(1)+" "+rs.getString(2)+"</option>");
						}
						rs.getStatement().getConnection().close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					out.print("</select></div></td>");*/
					out.print("<td colspan='2' align='center'><input type='button' onclick='ConsultarRepAuxiliarCuentaTercero()' id='Bot0' value='Consultar Reporte '></td></tr></table>");
					
				}else{
					if(TipoCpraux.equals("3")){
						
						/***REPORTE DE AUXILIAR DE CONTABILIDAD POR SUCURSAL Y CUENTA ***/
						out.print("<table width='100%' border='0'  >");
						out.print("<tr><td colspan='6' id='cabecera2' class='style11' align='center'>REPORTE AUXILIAR DE CONTABILIDAD POR SUCURSAL Y CUENTA </td> </tr>");
						//out.print("<tr><td>Digite los siguientes Campos </td>");
						out.print("<tr><td><b> Tipo de Documento: </b> &nbsp; <select id='TDOC'> <option value='todas'>TODAS</option>");
						rs=mpr.BuscarTDoc();
						try {
							while(rs.next()){
								out.print("<option value='"+rs.getString(1)+"' title='"+rs.getString(2)+"' >"+rs.getString(3)+"</option>");
							}rs.getStatement().getConnection().close();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						out.print("</select> &nbsp; &nbsp;  <b>Tipo de Sucursal </b>: &nbsp; &nbsp; <select id='Suc'><option value='todas'>TODAS</option>");
						rs=mpr.BuscarSucursal();
						try {
							while(rs.next()){
								out.print("<option value='"+rs.getString(1)+"' >"+rs.getString(2)+"</option>");
							}rs.getStatement().getConnection().close();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						out.print("</select></td></tr>");
						out.print("<tr><td><br><div ><b>A&ntilde;o: </b> &nbsp; &nbsp; <input name='AC' type='text' id='AC' size='3' onKeyup='valAnoPeriodo(this,patronp,true,1)'  />&nbsp; &nbsp;  <b> Periodo: &nbsp;  &nbsp; &nbsp; </b><input name='MC' type='text' id='MC' size='2' onKeyup='valAnoPeriodo(this,patronp,true,0)' /> &nbsp; <b> al </b> &nbsp; <input name='MC2' type='text' id='MC2' size='2' onKeyup='valAnoPeriodo(this,patronp,true,0)' /> ");
						out.print("<tr><td > <br>  <b> Rango de Cuentas: </b> <br></td> </tr>");
						out.print("<tr><td><input type='text'   id='cta0' onkeyup='autocompletaCta8(0,0)' onBlur='limpcta(0)'	size='10' value='TODAS'/> ");
						out.print("<input type=hidden id='ctah0' ><input type=hidden id='ctahh0' >");
						out.print("&nbsp; &nbsp; al &nbsp; &nbsp;<input type='text'   id='RCC0' onkeyup='autocompletaCta9(0,0)' onBlur='limpcta(0)'	size='10' value='TODAS' />");
						out.print("</td></tr>");
						out.print("<tr><td width='30%'><div id='dcta0'  width='30%'/></td><td width='30%' align='left'><div id='dctaa0'  width='30%' /></td></tr>");
								/*	"<tr><td><select id='RC1' style='width:350px' ><option value='Seleccione'>Seleccione</><option value='todas'>Todas</option> ");
						rs=mpr.BuscarCuentas3();
						try {
							while(rs.next()){
								out.print("<option value='"+rs.getString(1)+"' title='"+rs.getString(2)+"'>"+rs.getString(1)+" "+rs.getString(2)+"</option>");
							}
							rs.getStatement().getConnection().close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						out.print("</select>");
						out.print("&nbsp; &nbsp; al &nbsp; &nbsp;<select id='RC2' style='width:350px'><option value='Seleccione'>Seleccione</> <option value='todas'>Todas</option> ");
						rs=mpr.BuscarCuentas3();
						try {
							while(rs.next()){
								out.print("<option value='"+rs.getString(1)+"' title='"+rs.getString(2)+"'>"+rs.getString(1)+" "+rs.getString(2)+"</option>");
							}
							rs.getStatement().getConnection().close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						out.print("</select></div></td>");*/
						out.print("<td colspan='2' align='center'><input type='button' onclick='ConsultarRepAuxiliarSucursalyCcosto("+1+")' id='Bot0' value='Consultar Reporte '></td></tr></table>");
						
					}else{
						if(TipoCpraux.equals("4")){
							
							/***REPORTE DE AUXILIAR DE CONTABILIDAD POR SUCURSAL - CCOSTO Y CUENTA **/
							out.print("<table width='100%' border='0'  >");
							out.print("<tr><td colspan='6' id='cabecera2' class='style11' align='center'>REPORTE AUXILIAR DE CONTABILIDAD POR SUCURSAL - CENTRO DE COSTO Y CUENTA  </td> </tr>");
							//out.print("<tr><td>Digite los siguientes Campos </td>");
							out.print("<tr><td><b> Tipo de Documento: </b> &nbsp; <select id='TDOC'> <option value='todas'>TODAS</option>");
							rs=mpr.BuscarTDoc();
							try {
								while(rs.next()){
									out.print("<option value='"+rs.getString(1)+"' title='"+rs.getString(2)+"' >"+rs.getString(3)+"</option>");
								}rs.getStatement().getConnection().close();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							out.print("</select> &nbsp; &nbsp;  <b>Tipo de Sucursal </b>: &nbsp; &nbsp; <select id='Suc'><option value='todas'>TODAS</option>");
							rs=mpr.BuscarSucursal();
							try {
								while(rs.next()){
									out.print("<option value='"+rs.getString(1)+"' >"+rs.getString(2)+"</option>");
								}rs.getStatement().getConnection().close();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							out.print("</select> &nbsp; &nbsp; <b> Tipo de Centro de Costo : </b> &nbsp; &nbsp; <select id='ccosto' ><option value='todas'>TODAS</option>");
							rs=mpr.BuscarCentroCosto();
							try {
								while(rs.next()){
									out.print("<option value='"+rs.getString(1)+"' >"+rs.getString(2)+"</option>");
								}
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							out.print("</select></td></tr>");
							out.print("<tr><td><br><div ><b>A&ntilde;o: </b> &nbsp; &nbsp; <input name='AC' type='text' id='AC' size='3' onKeyup='valAnoPeriodo(this,patronp,true,1)'  />&nbsp; &nbsp;  <b> Periodo: &nbsp;  &nbsp; &nbsp; </b><input name='MC' type='text' id='MC' size='2' onKeyup='valAnoPeriodo(this,patronp,true,0)' /> &nbsp; <b> al </b> &nbsp; <input name='MC2' type='text' id='MC2' size='2' onKeyup='valAnoPeriodo(this,patronp,true,0)' /> ");
							out.print("<tr><td > <br>  <b> Rango de Cuentas: </b> <br></td> </tr>");
							out.print("<tr><td><input type='text'   id='cta0' onkeyup='autocompletaCta8(0,0)' onBlur='limpcta(0)'	size='10' value='TODAS'/> ");
							out.print("<input type=hidden id='ctah0' ><input type=hidden id='ctahh0' >");
							out.print("&nbsp; &nbsp; al &nbsp; &nbsp;<input type='text'   id='RCC0' onkeyup='autocompletaCta9(0,0)' onBlur='limpcta(0)'	size='10' value='TODAS' />");
							out.print("</td></tr>");
							out.print("<tr><td width='30%'><div id='dcta0'  width='30%'/></td><td width='30%' align='left'><div id='dctaa0'  width='30%' /></td></tr>");
									/*	"<tr><td><select id='RC1' style='width:350px' ><option value='Seleccione'>Seleccione</><option value='todas'>Todas</option> ");
							rs=mpr.BuscarCuentas3();
							try {
								while(rs.next()){
									out.print("<option value='"+rs.getString(1)+"' title='"+rs.getString(2)+"'>"+rs.getString(1)+" "+rs.getString(2)+"</option>");
								}
								rs.getStatement().getConnection().close();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							out.print("</select>");
							out.print("&nbsp; &nbsp; al &nbsp; &nbsp;<select id='RC2' style='width:350px'><option value='Seleccione'>Seleccione</> <option value='todas'>Todas</option> ");
							rs=mpr.BuscarCuentas3();
							try {
								while(rs.next()){
									out.print("<option value='"+rs.getString(1)+"' title='"+rs.getString(2)+"'>"+rs.getString(1)+" "+rs.getString(2)+"</option>");
								}
								rs.getStatement().getConnection().close();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							out.print("</select></div></td>");*/
							out.print("<td colspan='2' align='center'><input type='button' onclick='ConsultarRepAuxiliarSucursalyCcosto("+2+")' id='Bot0' value='Consultar Reporte '></td></tr></table>");
							
							
						}
					}
				}
			}
		
			}
		
		
		
		
		
		if(va.equals("autoinv1")){//Seleccionar Cuentas
			String texto=req.getParameter("texto");
			String opc=req.getParameter("opc");
			System.out.println("Valor de OPC "+opc);
			try {	
				if(opc.equals("1")){
					rs =mpr.BuscarCuentas4(texto);
					String cadena ="";
					cadena="[";
					while(rs.next()){
						
						cadena = cadena+"'"+rs.getString(1)+"|"+rs.getString(2)+"|"+rs.getString(3)+"'";
		            	cadena = cadena +",";	
					}
					cadena = cadena+"]";
					res.getWriter().write(cadena);
					
				}else{
					
					rs =mpr.BuscarCuentas5(texto);
					String cadena ="";
					cadena="[";
					System.out.println("entrando al si ");
					if((opc.equals("2"))||(opc.equals("3"))){
							while(rs.next()){
								String val=rs.getString(4);
								int vali=Integer.parseInt(val);
								//System.out.println("valor de opc"+opc);
								if(opc.equals("2")){
										if((vali==1)||(vali==2)||(vali==3)){
											
										//System.out.println("entrando a vali consulta"+vali);
										cadena = cadena+"'"+rs.getString(1)+"|"+rs.getString(2)+"|"+rs.getString(3)+"'";
						            	cadena = cadena +",";	
										}
								}else{
									if(opc.equals("3")){
										if((vali==4)||(vali==5)||(vali==6)||(vali==7)){
											cadena = cadena+"'"+rs.getString(1)+"|"+rs.getString(2)+"|"+rs.getString(3)+"'";
							            	cadena = cadena +",";
										}
									}
									
								}
							}
					}else{
						if(opc.equals("4")){
							rs1=mpr.BuscarCuentasAux(texto);
							while(rs1.next()){
								cadena = cadena+"'"+rs1.getString(1)+"|"+rs1.getString(2)+"|"+rs1.getString(3)+"'";
				            	cadena = cadena +",";
							}
							rs1.getStatement().getConnection().close();
						}
					}
					cadena = cadena+"]";
					res.getWriter().write(cadena);
					
					
				}
				rs.getStatement().getConnection().close();
				
				} catch (Exception e) {
					e.getMessage();
					e.printStackTrace();
				}
			}
		
		
	
		
		
		if(va.equals("cprbp")){
			out.print("<table width='100%' border='0'  >");
			out.print("<tr><td colspan='8' id='cabecera2' class='style11' align='center'>REPORTES DE BALANCE DE PRUEBA </td> </tr>");
			out.print("<tr><td>Seleccione el tipo de informe a consultar : <select id='tipoCprbp' onChange='TipoCprbp()'><option>---</option><option value='1'> GENERAL </option><option value='2'> POR CUENTA Y POR TERCERO </option><option value='3'>POR SUCURSAL</option><option value='4'>POR CENTRO DE COSTO</option><option value='5'>POR SUCURSAL Y CENTRO DE COSTO</option></select></td></tr>");
			out.print("<tr><td><div id='menucprbp' ></div></td></tr>");
			
		}
		if(va.equals("menucprbp")){
			String TipoCprbp=req.getParameter("TipoCprbp");
			/***REPORTE DE BALANCE GENERAL DE PRUEBA***/	
			java.util.Calendar hoy = java.util.Calendar.getInstance();
			int anio=hoy.get(java.util.Calendar.YEAR);
			int dia=hoy.get(java.util.Calendar.DATE);
			int mes=hoy.get(java.util.Calendar.MONTH);
			String mess="";
			mes=mes+1;
			if(mes==1){mess="01";}
			if(mes==2){mess="02";}
			if(mes==3){mess="03";}
			if(mes==4){mess="04";}
			if(mes==5){mess="05";}
			if(mes==6){mess="06";}
			if(mes==7){mess="07";}
			if(mes==8){mess="08";}
			if(mes==9){mess="09";}
			if(mes==10){mess="10";}
			if(mes==11){mess="11";}
			if(mes==12){mess="12";}

			if(TipoCprbp.equals("1")){
					out.print("<table width='100%' border='0'  >");
					out.print("<tr><td colspan='6' id='cabecera2' class='style11' align='center'>BALANCE DE PRUEBA GENERAL</td> </tr>");
					//out.print("<tr><td>Digite los siguientes Campos </td>");
					out.print("<tr><td><div ><b>A&ntilde;o: </b> &nbsp; &nbsp; <input name='AC' type='text' id='AC' size='3' onKeyup='valAnoPeriodo(this,patronp,true,1)'  />&nbsp; &nbsp;  <b> Periodo: &nbsp;  &nbsp; &nbsp; </b><input name='MC' type='text' id='MC' size='2' onKeyup='valAnoPeriodo(this,patronp,true,0)' /> </td></tr>"+
								"<tr><td > <br>  <b> Rango de Cuentas: </b> <br></td> </tr>"+
								"<tr><td>");
					/*<select id='RC1' style='width:350px' ><option value='Seleccione'>Seleccione</><option value='todas'>Todas</option> ");
					rs=mpr.BuscarCuentas2();
					try {
						while(rs.next()){
							out.print("<option value='"+rs.getString(1)+"' title='"+rs.getString(2)+"'>"+rs.getString(1)+" "+rs.getString(2)+"</option>");
						}
						rs.getStatement().getConnection().close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					out.print("</select>");*/
					out.print("<input type='text'   id='cta0' onkeyup='autocompletaCta2(0,0)' onBlur='limpcta(0)'	size='10' value='TODAS'/> ");
					out.print("<input type=hidden id='ctah0' ><input type=hidden id='ctahh0' >");
					out.print("&nbsp; &nbsp; al &nbsp; &nbsp;<input type='text'   id='RCC0' onkeyup='autocompletaCta3(0,0)' onBlur='limpcta(0)'	size='10' value='TODAS' />");
	
					out.print("</select></div></td></tr>");
					out.print("<tr><td width='30%'><div id='dcta0'  width='30%'/></td><td width='30%' align='left'><div id='dctaa0'  width='30%' /></td></tr>");
					out.print("<tr ><td><table id='balance'><tr><td><br><b> Definir Nivel: </b><br></td></tr>");
					rs=mpr.BuscarNivel();
					int cont=0;
					try {
						while(rs.next()){
							cont=cont+1;
							out.print("<tr><td>Nivel "+rs.getString(1)+"<input id='chkEF"+cont+"' type='checkbox' value='"+rs.getString(1)+"' /></td></tr>");
						}rs.getStatement().getConnection().close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					out.print("</table>");
					
					
					out.print("<tr><td colspan='2' align='center'><input id='Bot0' type='button' onclick='ConsultarRepBalanceGeneral()' value='Consultar Reporte '></td></tr></table>");
			}else{
				if(TipoCprbp.equals("2")){
					/***REPORTE DE BALANCE DE PRUEBA POR CUENTA Y POR TERCERO***/
					out.print("<table width='100%' border='0'  >");
					out.print("<tr><td colspan='6' id='cabecera2' class='style11' align='center'>REPORTE DE BALANCE DE PRUEBA POR CUENTA Y POR TERCERO</td> </tr>");
					//out.print("<tr><td>Digite los siguientes Campos </td>");
					out.print("<tr><td><div ><b>A&ntilde;o: </b> &nbsp; &nbsp; <input name='AC' type='text' id='AC' size='3' onKeyup='valAnoPeriodo(this,patronp,true,1)'  />&nbsp; &nbsp;  <b> Periodo: &nbsp;  &nbsp; &nbsp; </b><input name='MC' type='text' id='MC' size='2' onKeyup='valAnoPeriodo(this,patronp,true,0)' /> &nbsp; &nbsp; <b> Tipo de Tercero: </b> &nbsp; <input type=text id='ter0'  onkeyup='autocompletarTercero1(0,0)' onBlur='limpiater(0)' value='TODAS'> ");
					out.print("<input type=hidden id='terh0' value='TODAS'>");
					out.print("</td></tr><tr><td><div id='dter0' ></div></td></tr>"+
								"<tr><td > <br>  <b> Rango de Cuentas: </b> <br></td> </tr>"+
								"<tr><td>");
					out.print("<input type='text'   id='cta0' onkeyup='autocompletaCta8(0,0)' onBlur='limpcta(0)'	size='10' value='TODAS'/> ");
					out.print("<input type=hidden id='ctah0' ><input type=hidden id='ctahh0' >");
					out.print("&nbsp; &nbsp; al &nbsp; &nbsp;<input type='text'   id='RCC0' onkeyup='autocompletaCta9(0,0)' onBlur='limpcta(0)'	size='10' value='TODAS' />");
					out.print("</td></tr>");
					out.print("<tr><td width='30%'><div id='dcta0'  width='30%'/></td><td width='30%' align='left'><div id='dctaa0'  width='30%' /></td></tr>");
					/*	rs=mpr.BuscarCuentas3();
					try {
						while(rs.next()){
							out.print("<option value='"+rs.getString(1)+"' title='"+rs.getString(2)+"'>"+rs.getString(1)+" "+rs.getString(2)+"</option>");
						}
						rs.getStatement().getConnection().close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					out.print("</select>");
					out.print("&nbsp; &nbsp; al &nbsp; &nbsp;<select id='RC2' style='width:350px'><option value='Seleccione'>Seleccione</> <option value='todas'>Todas</option> ");
					rs=mpr.BuscarCuentas3();
					try {
						while(rs.next()){
							out.print("<option value='"+rs.getString(1)+"' title='"+rs.getString(2)+"'>"+rs.getString(1)+" "+rs.getString(2)+"</option>");
						}
						rs.getStatement().getConnection().close(); 
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}*/
					out.print("</select></div></td>");
					out.print("<td colspan='2' align='center'><input type='button' onclick='ConsBalanceCuentaTercero()'  id='Bot0' value='Consultar Reporte '></td></tr></table>");
					
				}else{
					if(TipoCprbp.equals("3")){
						///REPORTE DE PRUEBA GENERAL POR SUCURSAL /////
						out.print("<table width='100%' border='0'  >");
						out.print("<tr><td colspan='6' id='cabecera2' class='style11' align='center'>REPORTE DE BALANCE DE PRUEBA POR SUCURSAL</td> </tr>");
						out.print("<tr><td><div ><b>A&ntilde;o: </b> &nbsp; &nbsp; <input name='AC' type='text' id='AC' size='3' onKeyup='valAnoPeriodo(this,patronp,true,1)'  />&nbsp; &nbsp;  <b> Periodo: &nbsp;  &nbsp; &nbsp; </b><input name='MC' type='text' id='MC' size='2' onKeyup='valAnoPeriodo(this,patronp,true,0)' /> &nbsp; &nbsp; <b>Tipo Sucursal:</b> &nbsp;<select id='Suc' ><option  value='todas'>TODAS</option>");
								rs=mpr.BuscarSucursal();
								try {
									while(rs.next()){
										out.print("<option value='"+rs.getString(1)+"' >"+rs.getString(2)+"</option>");
									}rs.getStatement().getConnection().close();
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								out.print("</select></td></tr>"+
								"<tr><td > <br>  <b> Rango de Cuentas: </b> <br></td> </tr>"+
								"<tr><td>");
					/*<select id='RC1' style='width:350px' ><option value='Seleccione'>Seleccione</><option value='todas'>Todas</option> ");
					rs=mpr.BuscarCuentas2();
					try {
						while(rs.next()){
							out.print("<option value='"+rs.getString(1)+"' title='"+rs.getString(2)+"'>"+rs.getString(1)+" "+rs.getString(2)+"</option>");
						}
						rs.getStatement().getConnection().close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					out.print("</select>");*/
					out.print("<input type='text'   id='cta0' onkeyup='autocompletaCta2(0,0)' onBlur='limpcta(0)'	size='10' value='TODAS'/> ");
					out.print("<input type=hidden id='ctah0' ><input type=hidden id='ctahh0' >");
					out.print("&nbsp; &nbsp; al &nbsp; &nbsp;<input type='text'   id='RCC0' onkeyup='autocompletaCta3(0,0)' onBlur='limpcta(0)'	size='10' value='TODAS' />");
	
					out.print("</select></div></td></tr>");
					out.print("<tr><td width='30%'><div id='dcta0'  width='30%'/></td><td width='30%' align='left'><div id='dctaa0'  width='30%' /></td></tr>");
					out.print("<tr ><td><table id='balance'><tr><td><br><b> Definir Nivel: </b><br></td></tr>");
					rs=mpr.BuscarNivel();
					int cont=0;
					try {
						while(rs.next()){
							cont=cont+1;
							out.print("<tr><td>Nivel "+rs.getString(1)+"<input id='chkEF"+cont+"' type='checkbox' value='"+rs.getString(1)+"' /></td></tr>");
						}rs.getStatement().getConnection().close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					out.print("</table>");
					
					
					out.print("<tr><td colspan='2' align='center'><input id='Bot0' type='button' onclick='ConsultarRepBalancePruebaSucursal()' value='Consultar Reporte '></td></tr></table>");
					}else{
						if(TipoCprbp.equals("4")){
							out.print("<table width='100%' border='0'  >");
							out.print("<tr><td colspan='6' id='cabecera2' class='style11' align='center'>REPORTE DE BALANCE DE PRUEBA POR CENTRO DE COSTO</td> </tr>");
							out.print("<tr><td><div ><b>A&ntilde;o: </b> &nbsp; &nbsp; <input name='AC' type='text' id='AC' size='3' onKeyup='valAnoPeriodo(this,patronp,true,1)'  />&nbsp; &nbsp;  <b> Periodo: &nbsp;  &nbsp; &nbsp; </b><input name='MC' type='text' id='MC' size='2' onKeyup='valAnoPeriodo(this,patronp,true,0)' /> &nbsp; &nbsp; <b>Tipo de Centro de Costo:</b> &nbsp;<select id='ccosto' ><option  value='todas'>TODAS</option>");
							rs=mpr.BuscarCentroCosto();
							try {
								while(rs.next()){
									out.print("<option value='"+rs.getString(1)+"' >"+rs.getString(2)+"</option>");
								}rs.getStatement().getConnection().close();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							out.print("</select></td></tr>"+
							"<tr><td > <br>  <b> Rango de Cuentas: </b> <br></td> </tr>"+
							"<tr><td>");
				
							out.print("<input type='text'   id='cta0' onkeyup='autocompletaCta2(0,0)' onBlur='limpcta(0)'	size='10' value='TODAS'/> ");
							out.print("<input type=hidden id='ctah0' ><input type=hidden id='ctahh0' >");
							out.print("&nbsp; &nbsp; al &nbsp; &nbsp;<input type='text'   id='RCC0' onkeyup='autocompletaCta3(0,0)' onBlur='limpcta(0)'	size='10' value='TODAS' />");
			
							out.print("</select></div></td></tr>");
							out.print("<tr><td width='30%'><div id='dcta0'  width='30%'/></td><td width='30%' align='left'><div id='dctaa0'  width='30%' /></td></tr>");
							out.print("<tr ><td><table id='balance'><tr><td><br><b> Definir Nivel: </b><br></td></tr>");
							rs=mpr.BuscarNivel();
								int cont=0;
								try {
									while(rs.next()){
										cont=cont+1;
										out.print("<tr><td>Nivel "+rs.getString(1)+"<input id='chkEF"+cont+"' type='checkbox' value='"+rs.getString(1)+"' /></td></tr>");
									}rs.getStatement().getConnection().close();
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								out.print("</table>");
								
								
								out.print("<tr><td colspan='2' align='center'><input id='Bot0' type='button' onclick='ConsultarRepBalancePruebaCentroCosto()' value='Consultar Reporte '></td></tr></table>");

						
						}else{
							if(TipoCprbp.equals("5")){
								out.print("<table width='100%' border='0'  >");
								out.print("<tr><td colspan='6' id='cabecera2' class='style11' align='center'>REPORTE DE BALANCE DE PRUEBA POR SUCURSAL Y CENTRO DE COSTOS</td> </tr>");
								out.print("<tr><td><div ><b>A&ntilde;o: </b> &nbsp; &nbsp; <input name='AC' type='text' id='AC' size='3' onKeyup='valAnoPeriodo(this,patronp,true,1)'  />&nbsp; &nbsp;  <b> Periodo: &nbsp;  &nbsp; &nbsp; </b><input name='MC' type='text' id='MC' size='2' onKeyup='valAnoPeriodo(this,patronp,true,0)' /> &nbsp;");
								
								out.print("<tr><td > <br>  <b> Rango de Cuentas: </b> <br></td> </tr>"+
								"<tr><td>");
					
								out.print("<input type='text'   id='cta0' onkeyup='autocompletaCta2(0,0)' onBlur='limpcta(0)'	size='10' value='TODAS'/> ");
								out.print("<input type=hidden id='ctah0' ><input type=hidden id='ctahh0' >");
								out.print("&nbsp; &nbsp; al &nbsp; &nbsp;<input type='text'   id='RCC0' onkeyup='autocompletaCta3(0,0)' onBlur='limpcta(0)'	size='10' value='TODAS' />");
								out.print("</select></div></td></tr>");
								out.print("<tr><td width='30%'><div id='dcta0'  width='30%'/></td><td width='30%' align='left'><div id='dctaa0'  width='30%' /></td></tr>");
								out.print("<tr><td><br><br> &nbsp; <b>Tipo de Centro de Costo:</b> &nbsp;<select id='ccosto' ><option  value='todas'>TODAS</option>");
								rs=mpr.BuscarCentroCosto();
								try {
									while(rs.next()){
										out.print("<option value='"+rs.getString(1)+"' >"+rs.getString(2)+"</option>");
									}
									rs.getStatement().getConnection().close();
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								out.print("</select>");
								out.print("&nbsp; &nbsp; <b>Tipo Sucursal:</b> &nbsp;<select id='Suc' ><option  value='todas'>TODAS</option>");
								rs=mpr.BuscarSucursal();
								try {
									while(rs.next()){
										out.print("<option value='"+rs.getString(1)+"' >"+rs.getString(2)+"</option>");
									}rs.getStatement().getConnection().close();
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								out.print("</select></td></tr>");
								out.print("<tr ><td><table id='balance'><tr><td><br><b> Definir Nivel: </b><br></td></tr>");
								rs=mpr.BuscarNivel();
									int cont=0;
									try {
										while(rs.next()){
											cont=cont+1;
											out.print("<tr><td>Nivel "+rs.getString(1)+"<input id='chkEF"+cont+"' type='checkbox' value='"+rs.getString(1)+"' /></td></tr>");
										}rs.getStatement().getConnection().close();
									} catch (SQLException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									out.print("</table>");
									
									
									out.print("<tr><td colspan='2' align='center'><input id='Bot0' type='button' onclick='ConsultarRepBalancePruebaSucCentroCosto()' value='Consultar Reporte '></td></tr></table>");
							
							
							}
						}
					}
				}
			}
			
		}
		
		if(va.equals("cprbg")){
			
			out.print("<table width='100%' border='0'  >");
			out.print("<tr><td colspan='8' id='cabecera2' class='style11' align='center'> BALANCE GENERAL </td> </tr>");
			//out.print("<tr><td>Digite los siguientes Campos </td>");
			out.print("<tr><td><div ><b>A&ntilde;o: </b> &nbsp; &nbsp; <input name='AC' type='text' id='AC' size='3' onKeyup='valAnoPeriodo(this,patronp,true,1)'  />&nbsp; &nbsp;  <b> Periodo: &nbsp;  &nbsp; &nbsp; </b><input name='MC' type='text' id='MC' size='2' onKeyup='valAnoPeriodo(this,patronp,true,0)' /> </td></tr>"+
						"<tr><td > <br>  <b> Rango de Cuentas: </b> <br></td> </tr>"+
						"<tr><td>");
			/*<select id='RC1' style='width:350px' ><option value='Seleccione'>Seleccione</><option value='todas'>Todas</option> ");
			rs=mpr.BuscarCuentas2();
			try {
				while(rs.next()){
					out.print("<option value='"+rs.getString(1)+"' title='"+rs.getString(2)+"'>"+rs.getString(1)+" "+rs.getString(2)+"</option>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.print("</select>");*/
			out.print("<input type='text'   id='cta0' name='cta0' onkeyup='autocompletaCta4(0,0)' onBlur='limpcta(0)'	size='10' value='TODAS'/> ");
			out.print("<input type=hidden id='ctah0' ><input type=hidden id='ctahh0' >");
			out.print("&nbsp; &nbsp; al &nbsp; &nbsp;<input type='text'   id='RCC0' onkeyup='autocompletaCta5(0,0)' onBlur='limpcta(0)'	size='10' value='TODAS' />");

			out.print("</select></div></td></tr>");
			out.print("<tr><td width='30%'><div id='dcta0'  width='30%'/></td><td width='30%' align='left'><div id='dctaa0'  width='30%' /></td></tr>");
			out.print("<tr ><td><table id='balance'><tr><td><br><b> Definir Nivel: </b><br></td></tr>");
			rs=mpr.BuscarNivel();
			int cont=0;
			try {
				while(rs.next()){
					cont=cont+1;
					out.print("<tr><td>Nivel "+rs.getString(1)+"<input id='chkEF"+cont+"' type='checkbox' value='"+rs.getString(1)+"' /></td></tr>");
				}rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.print("</table>");
			
			
			out.print("<tr><td colspan='2' align='center'><input id='Bot0' type='button' onclick='ConsultarBalanceGeneral()' value='Consultar Reporte '></td></tr></table>");
		}
		
		if(va.equals("cprpg")){
			out.print("<table width='100%' border='0'  >");
			out.print("<tr><td colspan='8' id='cabecera2' class='style11' align='center'>REPORTES P Y G </td> </tr>");
			out.print("<tr><td>Seleccione el tipo de informe de Estado de Resultados : <select id='tpg' onChange='Tpg()'><option>---</option><option value='1'> MES </option><option value='2'> ACUMULADO </option><option value='3'> MES Y ACUMULADO</option></td></tr>");
			out.print("<tr><td><div id='menupg' ></div></td></tr>");
		}
		
		
		if(va.equals("cprdia")){
			out.print("<table width='100%' border='0'  >");
			out.print("<tr><td colspan='8' id='cabecera2' class='style11' align='center'>COMPROBANTE DIARIO GENERAL</td> </tr>");
			out.print("<tr><td><br><div ><b>A&ntilde;o: </b> &nbsp; &nbsp; <input name='AC' type='text' id='AC' size='3' onKeyup='valAnoPeriodo(this,patronp,true,1)'  />&nbsp; &nbsp;  <b> Mes : &nbsp;  &nbsp;  </b><input name='MC' type='text' id='MC' size='2' onKeyup='valAnoPeriodo(this,patronp,true,0)' /> &nbsp;<b> Dia </b><select id='rdia1'><option value='-' >--</option> ");
			for(int i=1;i<32;i++){
				out.println("<option value="+i+">"+i+"</option>");
			}
			out.println("</select>");
			out.println(" <b>al</b> &nbsp; <select id='rdia2'><option value='-' >--</option>");
			for(int i=1;i<32;i++){
				out.println("<option value="+i+">"+i+"</option>");
			}
			out.println("</select>");
			out.print("&nbsp; &nbsp;  <b>Seleccione Tipo de Documento : <b><select id='tdoc' ><option value='todos'> TODOS</option>");
		    rs=mpr.BuscarTDoc();
		    try {
				while(rs.next()){
					out.println("<option value='"+rs.getString(1)+"'>"+rs.getString(2)+"</option>");
				}rs.getStatement().getConnection().close();
				out.println("</select></td>");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.print("<tr><td colspan='2' align='center'><input id='Bot0' type='button' onclick='ConsultarDiario()' value='Consultar Reporte'></td></tr></table>");
			
			
		}
		
		if(va.equals("menupg")){
			///REPORTE P Y G - UTILIZAN LOS MISMOS FILTROS UTILIZO LA VARIABLE PG PARA ELEGIR AL REPORTE YA SEA POR MES, ACUMULADO O REP DE MES Y ACUMULADO
			String pg=req.getParameter("pg");
			out.print("<table>");
			out.print("<tr><td><div ><b>A&ntilde;o: </b> &nbsp; &nbsp; <input name='AC' type='text' id='AC' size='3' onKeyup='valAnoPeriodo(this,patronp,true,1)'  />&nbsp; &nbsp;  <b> Periodo: &nbsp;  &nbsp; &nbsp; </b><input name='MC' type='text' id='MC' size='2' onKeyup='valAnoPeriodo(this,patronp,true,0)' /> </td></tr>"+
					"<tr><td > <br>  <b> Rango de Cuentas: </b> <br></td> </tr>"+
					"<tr><td>");
		/*<select id='RC1' style='width:350px' ><option value='Seleccione'>Seleccione</><option value='todas'>Todas</option> ");
		rs=mpr.BuscarCuentas2();
		try {
			while(rs.next()){
				out.print("<option value='"+rs.getString(1)+"' title='"+rs.getString(2)+"'>"+rs.getString(1)+" "+rs.getString(2)+"</option>");
			}
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.print("</select>");*/
		out.print("<input type='text'   id='cta0' onkeyup='autocompletaCta6(0,0)' onBlur='limpcta(0)'	size='10' value='TODAS'/> ");
		out.print("<input type=hidden id='ctah0' ><input type=hidden id='ctahh0' >");
		out.print("&nbsp; &nbsp; al &nbsp; &nbsp;<input type='text'   id='RCC0' onkeyup='autocompletaCta7(0,0)' onBlur='limpcta(0)'	size='10' value='TODAS' />");

		out.print("</select></div></td></tr>");
		out.print("<tr><td width='30%'><div id='dcta0'  width='30%'/></td><td width='30%' align='left'><div id='dctaa0'  width='30%' /></td></tr>");
		out.print("<tr ><td><table id='nivelespg'><tr><td><br><b> Definir Nivel: </b><br></td></tr>");
		rs=mpr.BuscarNivel();
		int cont=0;
		try {
			while(rs.next()){
				cont=cont+1;
				out.print("<tr><td>Nivel "+rs.getString(1)+"<input id='chkEF"+cont+"' type='checkbox' value='"+rs.getString(1)+"' /></td></tr>");
			}rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.print("</table>");
		
		
		out.print("<tr><td colspan='2' align='center'><input id='Bot0' type='button' onclick='ConsultarPG("+pg+")' value='Consultar Reporte '></td></tr></table>");
			
		}
		
		if(va.equals("ReportePG")){
			String pg=req.getParameter("pg");
			String RC1=req.getParameter("RC1");
			String RC2=req.getParameter("RC2");
			String MC=req.getParameter("MC");
			String MC2=req.getParameter("MC2");
			String AC=req.getParameter("AC");
			
			if(RC2.equals("TODAS")){
				
			}else{
				/*int rc11=Integer.parseInt(RC1);
				int rc22=Integer.parseInt(RC2);
				if(RC1.equals(RC2)){
				}else{*/
				RC2=RC2+"9";
				/*}*/
			}
			System.out.println(" valor de RC2"+RC2);
			String nivelSeleccionado=req.getParameter("nivelSeleccionado");
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
			java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());
			String[] detnivelSeleccionado = nivelSeleccionado.split("_");
			
			String valorNivel [] = new String[6];
			int cont=0;
			for (int i = 0; i < detnivelSeleccionado.length; i++) {
				if(detnivelSeleccionado[i].equals("1")){
					valorNivel[i+1]="1";
				}else{
					if(detnivelSeleccionado[i].equals("2")){
						valorNivel[i+1]="2";
					}else{
						if(detnivelSeleccionado[i].equals("3")){
							valorNivel[i+1]="4";
						}else{
							if(detnivelSeleccionado[i].equals("4")){
								valorNivel[i+1]="6";
							}else{
								if(detnivelSeleccionado[i].equals("5")){						
								valorNivel[i+1]="8";
								}
							}
						}
				}
				
			}
				cont=cont+1;
			}
			
			String cuenta="";
			String nombre="";
			long saldo=0;
			if(pg.equals("1")){
					out.print("<br><br><table width='80%' >");
					rs=mpr.BuscarEmpresa();
					try {
						while(rs.next()){
						out.println("<tr class='contpre'><td align='center' colspan='3'>"+rs.getString(1)+"</td></tr>");
						out.println("<tr class='contpre'><td align='center' colspan='3'>"+rs.getString(2)+"<br></td></tr>");
						
						}rs.getStatement().getConnection().close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					out.print("<tr><td><br><br></td>");
					out.print("<tr class='contpre'><td  align='center' colspan='3'> ESTADO DE P Y G MENSUAL DEL PERIODO "+MC+" DEL A�O "+AC+" </td></tr>");
					out.print("<tr class='contpre'><td></td><td  align='left' > Fecha de Generacion: "+Fecha+"</td><td  align='center'> Hora de Generacion: "+Hora+"</td></tr>");
					out.print("<tr><td colspan='3'><hr></td>");
					out.print("<tr class='rep' ><b><td >&nbsp; CUENTA </td><td >&nbsp; NOMBRE DE LA CUENTA &nbsp;</td><td >&nbsp; SALDO CUENTA &nbsp;</td></b></tr>");
					out.print("<tr><td colspan='3'><hr></td>");
					
					if(cont>0){
						//String nivel=valorNivel[1];
						rs=mpr.BuscarDatosBalanceG1(RC1, RC2, AC, MC, "1","nada","nada");
						try {
							while(rs.next()){
								cuenta=rs.getString(1);
								rs1=mpr.BuscarCuenta(cuenta);
								while(rs1.next()){
									nombre=rs1.getString(3);
								out.print("<tr class='rep'><td align='left' ><b>"+cuenta+"</b></td><td  align='left'><b>"+rs1.getString(3)+"</b></td><td ></td></tr>");
								}
								rs1.getStatement().getConnection().close();
								if(cont==1){
										String nivel=valorNivel[1];
										if(nivel.contains("1")){
											
										}else{
											rs2=mpr.BuscarDatosBalanceG1(RC1, RC2, AC, MC, nivel,"nada","nada");
											while(rs2.next()){
												String cuenta1=rs2.getString(1);
												rs3=mpr.BuscarCuenta(cuenta1);
												while(rs3.next()){
													if(cuenta.equals(rs2.getString(9))){
														System.out.println(" naturaleza"+rs2.getString(8));
														if(rs2.getString(8).equals("Debito")){
															
															saldo=((rs2.getLong(5))-(rs2.getLong(6)));
															String sal=Long.toString(saldo);
														//System.out.println("valor de cuenta "+cuenta+"valor de valor 9 de la consulta "+rs2.getString(9));
														out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs3.getString(3)+"</td><td align='right' >"+FormatMoneda(sal)+"</td></tr>");
														}else{
															saldo=((rs2.getLong(6))-(rs2.getLong(5)));
															String sal=Long.toString(saldo);
														//System.out.println("valor de cuenta "+cuenta+"valor de valor 9 de la consulta "+rs2.getString(9));
														out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs3.getString(3)+"</td><td align='right' >"+FormatMoneda(sal)+"</td></tr>");
														}
														}
												}
												rs3.getStatement().getConnection().close();
											}
											rs2.getStatement().getConnection().close();
										}
										
										
									}else{
										if(cont==2){
											String nivel1=valorNivel[1];
											String nivel2=valorNivel[2];
											if(nivel1.contains("1")){
												rs1=mpr.BuscarDatosBalanceG1(RC1, RC2, AC, MC, nivel2,"nada","nada");
												while(rs1.next()){
													String cuenta1=rs1.getString(1);
													rs2=mpr.BuscarCuenta(cuenta1);
													while(rs2.next()){
														if(cuenta.equals(rs1.getString(9))){
															System.out.println(" naturaleza"+rs1.getString(8));
															if(rs1.getString(8).equals("Debito")){
																saldo=((rs1.getLong(5))-(rs1.getLong(6)));
																String sal=Long.toString(saldo);
																//System.out.println("valor de debito "+rs2.getLong(5)+" valor de credito"+rs2.getLong(6)+" valor de la diferencia"+sal);
																//String()
																//System.out.println("valor de cuenta "+cuenta+"valor de valor 9 de la consulta "+rs2.getString(9));
															out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right' >"+FormatMoneda(sal)+"</td></tr>");
															}else{
																System.out.println((rs1.getLong(6))+"-"+(rs1.getLong(5)));
																saldo=((rs1.getLong(6))-(rs1.getLong(5)));
																String sal=Long.toString(saldo);
																//System.out.println("valor de credito "+rs2.getLong(6)+" valor de debito"+rs2.getLong(5)+" valor de la diferencia"+sal);
															//	System.out.println("valor de cuenta "+cuenta+"valor de valor 9 de la consulta "+rs2.getString(9));
																out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right' >"+FormatMoneda(sal)+"</td></tr>");
															}
														}
													}rs2.getStatement().getConnection().close();
												}rs1.getStatement().getConnection().close();
											}else{
												
												rs1=mpr.BuscarDatosBalanceG2(RC1, RC2, AC, MC, nivel1, nivel2,"nada","nada");
												while(rs1.next()){
													String cuenta1=rs1.getString(1);
													rs2=mpr.BuscarCuenta(cuenta1);
													while(rs2.next()){
														if(cuenta.equals(rs1.getString(9))){
															System.out.println(" naturaleza"+rs1.getString(8));
															if(rs1.getString(8).equals("Debito")){
																saldo=((rs1.getLong(5))-(rs1.getLong(6)));
																String sal=Long.toString(saldo);
															//System.out.println("valor de debito "+rs1.getLong(5)+" valor de credito"+rs1.getLong(6)+" valor de la diferencia"+sal);
															//System.out.println("valor de cuenta "+cuenta+"valor de valor 9 de la consulta "+rs2.getString(9));
															out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right' >"+FormatMoneda(sal)+"</td></tr>");
														}else{
															saldo=((rs1.getLong(6))-(rs1.getLong(5)));
															String sal=Long.toString(saldo);
															//System.out.println("valor de credito "+rs1.getLong(6)+" valor de debito"+rs1.getLong(5)+" valor de la diferencia"+sal);
															//System.out.println("valor de cuenta "+cuenta+"valor de valor 9 de la consulta "+rs2.getString(9));
															out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right' >"+FormatMoneda(sal)+"</td></tr>");
														}
													}
													}rs2.getStatement().getConnection().close();
												}rs1.getStatement().getConnection().close();
											}
										}else{
											if(cont==3){
																						
												String nivel1=valorNivel[1];
												String nivel2=valorNivel[2];
												String nivel3=valorNivel[3];
												if(nivel1.contains("1")){
													rs1=mpr.BuscarDatosBalanceG2(RC1, RC2, AC, MC, nivel2,nivel3,"nada","nada");
													while(rs1.next()){
														String cuenta1=rs1.getString(1);
														rs2=mpr.BuscarCuenta(cuenta1);
														while(rs2.next()){
															if(cuenta.equals(rs1.getString(9))){
																System.out.println(" naturaleza"+rs1.getString(8));
																if(rs1.getString(8).equals("Debito")){
																	saldo=((rs1.getLong(5))-(rs1.getLong(6)));
																	String sal=Long.toString(saldo);
																//System.out.println("valor de cuenta "+cuenta+"valor de valor 9 de la consulta "+rs2.getString(9));
																out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right' >"+FormatMoneda(sal)+"</td></tr>");
																}else{
																	saldo=((rs1.getLong(6))-(rs1.getLong(5)));
																	String sal=Long.toString(saldo);
																	out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right' >"+FormatMoneda(sal)+"</td></tr>");
																}
																}
														}rs2.getStatement().getConnection().close();
													}rs1.getStatement().getConnection().close();
												}else{
													
													rs1=mpr.BuscarDatosBalanceG3(RC1, RC2, AC, MC, nivel1, nivel2,nivel3,"nada","nada");
													while(rs1.next()){
														String cuenta1=rs1.getString(1);
														rs2=mpr.BuscarCuenta(cuenta1);
														while(rs2.next()){
															if(cuenta.equals(rs1.getString(9))){
																System.out.println(" naturaleza"+rs1.getString(8));
																if(rs1.getString(8).equals("Debito")){
																	saldo=((rs1.getLong(5))-(rs1.getLong(6)));
																	String sal=Long.toString(saldo);
																//System.out.println("valor de cuenta "+cuenta+"valor de valor 9 de la consulta "+rs2.getString(9));
																out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right' >"+FormatMoneda(sal)+"</td></tr>");
																}else{
																	saldo=((rs1.getLong(6))-(rs1.getLong(5)));
																	String sal=Long.toString(saldo);
																	out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right' >"+FormatMoneda(sal)+"</td></tr>");
																}
																}
														}rs2.getStatement().getConnection().close();
													}rs1.getStatement().getConnection().close();
												}
											
											}else{
												if(cont==4){
													
													String nivel1=valorNivel[1];
													String nivel2=valorNivel[2];
													String nivel3=valorNivel[3];
													String nivel4=valorNivel[4];
													if(nivel1.contains("1")){
														rs1=mpr.BuscarDatosBalanceG3(RC1, RC2, AC, MC, nivel2,nivel3,nivel4,"nada","nada");
														while(rs1.next()){
															String cuenta1=rs1.getString(1);
															rs2=mpr.BuscarCuenta(cuenta1);
															while(rs2.next()){
																if(cuenta.equals(rs1.getString(9))){
																	System.out.println(" naturaleza"+rs1.getString(8));
																	if(rs1.getString(8).equals("Debito")){
																		saldo=((rs1.getLong(5))-(rs1.getLong(6)));
																		String sal=Long.toString(saldo);
																	//System.out.println("valor de cuenta "+cuenta+"valor de valor 9 de la consulta "+rs2.getString(9));
																	out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right' >"+FormatMoneda(sal)+"</td></tr>");
																	}else{
																		saldo=((rs1.getLong(6))-(rs1.getLong(5)));
																		String sal=Long.toString(saldo);
																		out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right' >"+FormatMoneda(sal)+"</td></tr>");
																	}
																	}
															}rs2.getStatement().getConnection().close();
														}rs1.getStatement().getConnection().close();
													}else{
														
														rs1=mpr.BuscarDatosBalanceG4(RC1, RC2, AC, MC, nivel1, nivel2,nivel3,nivel4,"nada","nada");
														while(rs1.next()){
															String cuenta1=rs1.getString(1);
															rs2=mpr.BuscarCuenta(cuenta1);
															while(rs2.next()){
																if(cuenta.equals(rs1.getString(9))){
																	System.out.println(" naturaleza"+rs1.getString(8));
																	if(rs1.getString(8).equals("Debito")){
																		saldo=((rs1.getLong(5))-(rs1.getLong(6)));
																		String sal=Long.toString(saldo);
																	//System.out.println("valor de cuenta "+cuenta+"valor de valor 9 de la consulta "+rs2.getString(9));
																	out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right' >"+FormatMoneda(sal)+"</td></tr>");
																	}else{
																		saldo=((rs1.getLong(6))-(rs1.getLong(5)));
																		String sal=Long.toString(saldo);
																		out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right' >"+FormatMoneda(sal)+"</td></tr>");
																	}
																	}
															}rs2.getStatement().getConnection().close();
														}rs1.getStatement().getConnection().close();
													}
													
												}else{
													if(cont==5){
														
														String nivel1=valorNivel[1];
														String nivel2=valorNivel[2];
														String nivel3=valorNivel[3];
														String nivel4=valorNivel[4];
														String nivel5=valorNivel[5];
														if(nivel1.contains("1")){
															rs1=mpr.BuscarDatosBalanceG4(RC1, RC2, AC, MC, nivel2,nivel3,nivel4, nivel5,"nada","nada");
															while(rs1.next()){
																String cuenta1=rs1.getString(1);
																rs2=mpr.BuscarCuenta(cuenta1);
																while(rs2.next()){
																	if(cuenta.equals(rs1.getString(9))){
																		System.out.println(" naturaleza"+rs1.getString(8));
																		if(rs1.getString(8).equals("Debito")){
																			saldo=((rs1.getLong(5))-(rs1.getLong(6)));
																			String sal=Long.toString(saldo);
																		//System.out.println("valor de cuenta "+cuenta+"valor de valor 9 de la consulta "+rs2.getString(9));
																		out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right' >"+FormatMoneda(sal)+"</td></tr>");
																		}else{
																			saldo=((rs1.getLong(6))-(rs1.getLong(5)));
																			String sal=Long.toString(saldo);
																			out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right' >"+FormatMoneda(sal)+"</td></tr>");
																		}
																		}
																}rs2.getStatement().getConnection().close();
															}rs1.getStatement().getConnection().close();
														}else{}
															
													}	
												}
											}
										}
									}
								
								long stotal=0;
								String st="";
								if(rs.getString(8).equals("Debito")){
									System.out.println("Totalizacion "+(rs.getLong(5))+"-"+(rs.getLong(6)));
									stotal=((rs.getLong(5))-(rs.getLong(6)));
									st=Long.toString(stotal);
								}else{
									System.out.println("Totalizacion "+(rs.getLong(5))+"-"+(rs.getLong(6)));
									stotal=((rs.getLong(6))-(rs.getLong(5)));
									st=Long.toString(stotal);
								}
							out.print("<tr class='rep'><td></td><td align='right' > </td><td align='right' ><b> TOTAL "+nombre+" &nbsp; &nbsp; </b> <u><b> "+FormatMoneda(st)+"</b></u></td></tr>");
							
								
							}
							rs.getStatement().getConnection().close();
							rs=mpr.BuscarDatosBalanceG1("4","79", AC, MC, "1","nada","nada");
							long parcialCta567=0;
							long Cta4=0;
							long Cta567=0;
							while(rs.next()){
								if(rs.getString(9).equals("4")){
									if(rs.getString(8).equals("Debito")){
										Cta4=((rs.getLong(5))-(rs.getLong(6)));
									}else{
										Cta4=((rs.getLong(6))-(rs.getLong(5)));
									}
								}else{
									
									if(rs.getString(8).equals("Debito")){
										parcialCta567=((rs.getLong(5))-(rs.getLong(6)));
									}else{
										parcialCta567=((rs.getLong(6))-(rs.getLong(5)));
									}
									System.out.println("parcial Cta567"+parcialCta567);
									Cta567=Cta567+parcialCta567;
								}
							}
							rs.getStatement().getConnection().close();
							long Util=(Cta4-Cta567);
							String TotalUtil=Long.toString(Util);
							out.print("<tr class='contpre' ><td></td><td align='left'><br><b>UTILIDAD O PERDIDA DEL EJERCICIO </b></td><td align='right'><br> <font style='text-decoration: overline underline;'>"+FormatMoneda(TotalUtil)+"</font></td></tr>");
							out.print("<tr class='rep'><td colspan='3' align='center'><br><br><br><br><br><br>&nbsp; &nbsp;<font style='text-decoration: overline;'> &nbsp; &nbsp; &nbsp; &nbsp; GERENTE GENERAL &nbsp; &nbsp; &nbsp; &nbsp;</font> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; <font style='text-decoration: overline;'> &nbsp; &nbsp; &nbsp; &nbsp; REVISOR FISCAL &nbsp; &nbsp; &nbsp; &nbsp;</font></td></tr>");
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					
					
			}else{
				if(pg.equals("2")){
					out.print("<br><br><table width='80%'>");
					rs=mpr.BuscarEmpresa();
					try {
						while(rs.next()){
						out.println("<tr class='contpre'><td align='center' colspan='3'>"+rs.getString(1)+"</td></tr>");
						out.println("<tr class='contpre'><td align='center' colspan='3'>"+rs.getString(2)+"<br></td></tr>");
						
						}rs.getStatement().getConnection().close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					out.println("<tr><td><br><br></td></tr>");
					out.print("<tr class='contpre'><td  align='center' colspan='3'> ESTADO DE P y G ACUMULADO DEL PERIODO "+MC+" DEL A�O "+AC+" </td></tr>");
					out.print("<tr class='contpre'><td></td><td  align='left' > Fecha de Generacion: "+Fecha+"</td><td  align='center'> Hora de Generacion: "+Hora+"</td></tr>");
					out.print("<tr><td colspan='3'><hr></td>");
					out.print("<tr class='rep' ><b><td >&nbsp; CUENTA </td><td >&nbsp; NOMBRE DE LA CUENTA &nbsp;</td><td>&nbsp; SALDO CUENTA &nbsp;</td></b></tr>");
					out.print("<tr><td colspan='3'><hr></td>");
					
					if(cont>0){
						//String nivel=valorNivel[1];
						rs=mpr.BuscarDatosBalanceG1(RC1, RC2, AC, MC, "1","nada","nada");
						try {
							while(rs.next()){
								cuenta=rs.getString(1);
								rs1=mpr.BuscarCuenta(cuenta);
								while(rs1.next()){
									nombre=rs1.getString(3);
								out.print("<tr class='rep'><td align='left' ><b>"+cuenta+"</b></td><td  align='left'><b>"+rs1.getString(3)+"</b></td><td ></td></tr>");
								}
								rs1.getStatement().getConnection().close();
								if(cont==1){
										String nivel=valorNivel[1];
										if(nivel.contains("1")){
											
										}else{
											rs2=mpr.BuscarDatosBalanceG1(RC1, RC2, AC, MC, nivel,"nada","nada");
											while(rs2.next()){
												String cuenta1=rs2.getString(1);
												rs3=mpr.BuscarCuenta(cuenta1);
												while(rs3.next()){
													if(cuenta.equals(rs2.getString(9))){
														//System.out.println("valor de cuenta "+cuenta+"valor de valor 9 de la consulta "+rs2.getString(9));
														out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs3.getString(3)+"</td><td align='right' >"+FormatMoneda(rs2.getString(7))+"</td></tr>");
													}
												}
												rs3.getStatement().getConnection().close();
											}
											rs2.getStatement().getConnection().close();
										}
										
										
									}else{
										if(cont==2){
											String nivel1=valorNivel[1];
											String nivel2=valorNivel[2];
											if(nivel1.contains("1")){
												rs1=mpr.BuscarDatosBalanceG1(RC1, RC2, AC, MC, nivel2,"nada","nada");
												while(rs1.next()){
													String cuenta1=rs1.getString(1);
													rs2=mpr.BuscarCuenta(cuenta1);
													while(rs2.next()){
														if(cuenta.equals(rs1.getString(9))){
															//System.out.println("valor de cuenta "+cuenta+"valor de valor 9 de la consulta "+rs2.getString(9));
															out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right' >"+FormatMoneda(rs1.getString(7))+"</td></tr>");
														}
													}rs2.getStatement().getConnection().close();
												}rs1.getStatement().getConnection().close();
											}else{
												
												rs1=mpr.BuscarDatosBalanceG2(RC1, RC2, AC, MC, nivel1, nivel2,"nada","nada");
												while(rs1.next()){
													String cuenta1=rs1.getString(1);
													rs2=mpr.BuscarCuenta(cuenta1);
													while(rs2.next()){
														if(cuenta.equals(rs1.getString(9))){
															//System.out.println("valor de cuenta "+cuenta+"valor de valor 9 de la consulta "+rs2.getString(9));
															out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right' >"+FormatMoneda(rs1.getString(7))+"</td></tr>");
														}
													}rs2.getStatement().getConnection().close();
												}rs1.getStatement().getConnection().close();
											}
										}else{
											if(cont==3){
																						
												String nivel1=valorNivel[1];
												String nivel2=valorNivel[2];
												String nivel3=valorNivel[3];
												if(nivel1.contains("1")){
													rs1=mpr.BuscarDatosBalanceG2(RC1, RC2, AC, MC, nivel2,nivel3,"nada","nada");
													while(rs1.next()){
														String cuenta1=rs1.getString(1);
														rs2=mpr.BuscarCuenta(cuenta1);
														while(rs2.next()){
															if(cuenta.equals(rs1.getString(9))){
															//	System.out.println("valor de cuenta "+cuenta+"valor de valor 9 de la consulta "+rs2.getString(9));
																out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right' >"+FormatMoneda(rs1.getString(7))+"</td></tr>");
															}
														}rs2.getStatement().getConnection().close();
													}rs1.getStatement().getConnection().close();
												}else{
													
													rs1=mpr.BuscarDatosBalanceG3(RC1, RC2, AC, MC, nivel1, nivel2,nivel3,"nada","nada");
													while(rs1.next()){
														String cuenta1=rs1.getString(1);
														rs2=mpr.BuscarCuenta(cuenta1);
														while(rs2.next()){
															if(cuenta.equals(rs1.getString(9))){
																System.out.println("valor de cuenta "+cuenta+"valor de valor 9 de la consulta "+rs2.getString(9));
																out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right' >"+FormatMoneda(rs1.getString(7))+"</td></tr>");
															}
														}rs2.getStatement().getConnection().close();
													}rs1.getStatement().getConnection().close();
												}
											
											}else{
												if(cont==4){
													
													String nivel1=valorNivel[1];
													String nivel2=valorNivel[2];
													String nivel3=valorNivel[3];
													String nivel4=valorNivel[4];
													if(nivel1.contains("1")){
														rs1=mpr.BuscarDatosBalanceG3(RC1, RC2, AC, MC, nivel2,nivel3,nivel4,"nada","nada");
														while(rs1.next()){
															String cuenta1=rs1.getString(1);
															rs2=mpr.BuscarCuenta(cuenta1);
															while(rs2.next()){
																if(cuenta.equals(rs1.getString(9))){
																	//System.out.println("valor de cuenta "+cuenta+"valor de valor 9 de la consulta "+rs2.getString(9));
																	out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right' >"+FormatMoneda(rs1.getString(7))+"</td></tr>");
																}
															}rs2.getStatement().getConnection().close();
														}rs1.getStatement().getConnection().close();
													}else{
														
														rs1=mpr.BuscarDatosBalanceG4(RC1, RC2, AC, MC, nivel1, nivel2,nivel3,nivel4,"nada","nada");
														while(rs1.next()){
															String cuenta1=rs1.getString(1);
															rs2=mpr.BuscarCuenta(cuenta1);
															while(rs2.next()){
																if(cuenta.equals(rs1.getString(9))){
																	//System.out.println("valor de cuenta "+cuenta+"valor de valor 9 de la consulta "+rs2.getString(9));
																	out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right' >"+FormatMoneda(rs1.getString(7))+"</td></tr>");
																}
															}rs2.getStatement().getConnection().close();
														}rs1.getStatement().getConnection().close();
													}
													
												}else{
													if(cont==5){
														
														String nivel1=valorNivel[1];
														String nivel2=valorNivel[2];
														String nivel3=valorNivel[3];
														String nivel4=valorNivel[4];
														String nivel5=valorNivel[5];
														if(nivel1.contains("1")){
															rs1=mpr.BuscarDatosBalanceG4(RC1, RC2, AC, MC, nivel2,nivel3,nivel4, nivel5,"nada","nada");
															while(rs1.next()){
																String cuenta1=rs1.getString(1);
																rs2=mpr.BuscarCuenta(cuenta1);
																while(rs2.next()){
																	if(cuenta.equals(rs1.getString(9))){
																		System.out.println("valor de cuenta "+cuenta+"valor de valor 9 de la consulta "+rs2.getString(9));
																		out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right' >"+FormatMoneda(rs1.getString(7))+"</td></tr>");
																	}
																}rs2.getStatement().getConnection().close();
															}rs1.getStatement().getConnection().close();
														}else{}
															
													}	
												}
											}
										}
									}
							out.print("<tr class='rep'><td></td><td align='right' > </td><td align='right' ><b> TOTAL "+nombre+" &nbsp; &nbsp; </b> <u><b> "+FormatMoneda(rs.getString(7))+"</b></u></td></tr>");
							
								
							}
							rs.getStatement().getConnection().close();
							rs=mpr.BuscarDatosBalanceG1("4","79", AC, MC, "1","nada","nada");
							long Cta4=0;
							long Cta567=0;
							while(rs.next()){
								if(rs.getString(9).equals("4")){
									Cta4=rs.getLong(7);
								}else{
									Cta567=Cta567+rs.getLong(7);
									System.out.println("Valor de cta567"+Cta567);
								}
							}
							rs.getStatement().getConnection().close();
							System.out.println("Valor Cta4"+Cta4);
							long Util=(Cta4-Cta567);
							System.out.println(Util);
							String TotalUtil=Long.toString(Util);
							out.print("<tr class='contpre' ><td></td><td align='left'><br><b>UTILIDAD O PERDIDA DEL EJERCICIO </b></td><td align='right'><br> <font style='text-decoration: overline underline;'>"+FormatMoneda(TotalUtil)+"</font></td></tr>");
							out.print("<tr class='rep'><td colspan='3' align='center'><br><br><br><br><br><br>&nbsp; &nbsp;<font style='text-decoration: overline;'> &nbsp; &nbsp; &nbsp; &nbsp; GERENTE GENERAL &nbsp; &nbsp; &nbsp; &nbsp;</font> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; <font style='text-decoration: overline;'> &nbsp; &nbsp; &nbsp; &nbsp; REVISOR FISCAL &nbsp; &nbsp; &nbsp; &nbsp;</font></td></tr>");
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				}else{
					if(pg.equals("3")){
						
						out.print("<br><br><table width='80%' >");
						rs=mpr.BuscarEmpresa();
						try {
							while(rs.next()){
							out.println("<tr class='contpre'><td align='center' colspan='6'>"+rs.getString(1)+"</td></tr>");
							out.println("<tr class='contpre'><td align='center' colspan='6'>"+rs.getString(2)+"<br></td></tr>");
							
							}rs.getStatement().getConnection().close();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						out.print("<tr><td><br><br></td></tr>");
						out.print("<tr class='contpre'><td  align='center' colspan='4'> ESTADO DE P Y G MENSUAL Y ACUMULADO DE DEL PERIODO "+MC+" DEL A�O "+AC+" </td></tr>");
						out.print("<tr class='contpre'><td></td><td  align='left' > Fecha de Generacion: "+Fecha+"</td><td></td><td  align='left'> Hora de Generacion: "+Hora+"</td><td align></td></tr>");
						out.print("<tr><td colspan='4'><hr></td>");
						out.print("<tr class='rep' ><b><td >&nbsp; CUENTA </td><td >&nbsp; NOMBRE DE LA CUENTA &nbsp;</td><td>&nbsp; MES &nbsp;</td><td>&nbsp; ACUMULADO &nbsp;</td></b></tr>");
						out.print("<tr><td colspan='4'><hr></td>");
						
						if(cont>0){
							//String nivel=valorNivel[1];
							rs=mpr.BuscarDatosBalanceG1(RC1, RC2, AC, MC, "1","nada","nada");
							try {
								while(rs.next()){
									cuenta=rs.getString(1);
									rs1=mpr.BuscarCuenta(cuenta);
									while(rs1.next()){
										nombre=rs1.getString(3);
									out.print("<tr class='rep'><td align='left' ><b>"+cuenta+"</b></td><td  align='left'><b>"+rs1.getString(3)+"</b></td><td ></td></tr>");
									}
									rs1.getStatement().getConnection().close();
									if(cont==1){
											String nivel=valorNivel[1];
											if(nivel.contains("1")){
												
											}else{
												rs2=mpr.BuscarDatosBalanceG1(RC1, RC2, AC, MC, nivel,"nada","nada");
												while(rs2.next()){
													String cuenta1=rs2.getString(1);
													rs3=mpr.BuscarCuenta(cuenta1);
													while(rs3.next()){
														if(cuenta.equals(rs2.getString(9))){
															System.out.println(" naturaleza"+rs2.getString(8));
															if(rs2.getString(8).equals("Debito")){
																
																saldo=((rs2.getLong(5))-(rs2.getLong(6)));
																String sal=Long.toString(saldo);
															//System.out.println("valor de cuenta "+cuenta+"valor de valor 9 de la consulta "+rs2.getString(9));
															out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs3.getString(3)+"</td><td align='right' >"+FormatMoneda(sal)+"</td><td align='right' >"+FormatMoneda(rs2.getString(7))+"</td></tr>");
															}else{
																saldo=((rs2.getLong(6))-(rs2.getLong(5)));
																String sal=Long.toString(saldo);
															//System.out.println("valor de cuenta "+cuenta+"valor de valor 9 de la consulta "+rs2.getString(9));
															out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs3.getString(3)+"</td><td align='right' >"+FormatMoneda(sal)+"</td><td align='right' >"+FormatMoneda(rs2.getString(7))+"</td></tr>");
															}
															}
													}
													rs3.getStatement().getConnection().close();
												}
												rs2.getStatement().getConnection().close();
											}
											
											
										}else{
											if(cont==2){
												String nivel1=valorNivel[1];
												String nivel2=valorNivel[2];
												if(nivel1.contains("1")){
													rs1=mpr.BuscarDatosBalanceG1(RC1, RC2, AC, MC, nivel2,"nada","nada");
													while(rs1.next()){
														String cuenta1=rs1.getString(1);
														rs2=mpr.BuscarCuenta(cuenta1);
														while(rs2.next()){
															if(cuenta.equals(rs1.getString(9))){
																System.out.println(" naturaleza"+rs1.getString(8));
																if(rs1.getString(8).equals("Debito")){
																	saldo=((rs1.getLong(5))-(rs1.getLong(6)));
																	String sal=Long.toString(saldo);
																	//System.out.println("valor de debito "+rs2.getLong(5)+" valor de credito"+rs2.getLong(6)+" valor de la diferencia"+sal);
																	//String()
																	//System.out.println("valor de cuenta "+cuenta+"valor de valor 9 de la consulta "+rs2.getString(9));
																out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right' >"+FormatMoneda(sal)+"</td><td align='right' >"+FormatMoneda(rs1.getString(7))+"</td></tr>");
																}else{
																	System.out.println((rs1.getLong(6))+"-"+(rs1.getLong(5)));
																	saldo=((rs1.getLong(6))-(rs1.getLong(5)));
																	String sal=Long.toString(saldo);
																	//System.out.println("valor de credito "+rs2.getLong(6)+" valor de debito"+rs2.getLong(5)+" valor de la diferencia"+sal);
																//	System.out.println("valor de cuenta "+cuenta+"valor de valor 9 de la consulta "+rs2.getString(9));
																	out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right' >"+FormatMoneda(sal)+"</td><td align='right' >"+FormatMoneda(rs1.getString(7))+"</td></tr>");
																}
															}
														}rs2.getStatement().getConnection().close();
													}rs1.getStatement().getConnection().close();
												}else{
													
													rs1=mpr.BuscarDatosBalanceG2(RC1, RC2, AC, MC, nivel1, nivel2,"nada","nada");
													while(rs1.next()){
														String cuenta1=rs1.getString(1);
														rs2=mpr.BuscarCuenta(cuenta1);
														while(rs2.next()){
															if(cuenta.equals(rs1.getString(9))){
																System.out.println(" naturaleza"+rs1.getString(8));
																if(rs1.getString(8).equals("Debito")){
																	saldo=((rs1.getLong(5))-(rs1.getLong(6)));
																	String sal=Long.toString(saldo);
																//System.out.println("valor de debito "+rs1.getLong(5)+" valor de credito"+rs1.getLong(6)+" valor de la diferencia"+sal);
																//System.out.println("valor de cuenta "+cuenta+"valor de valor 9 de la consulta "+rs2.getString(9));
																out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right' >"+FormatMoneda(sal)+"</td><td align='right' >"+FormatMoneda(rs1.getString(7))+"</td></tr>");
															}else{
																saldo=((rs1.getLong(6))-(rs1.getLong(5)));
																String sal=Long.toString(saldo);
																//System.out.println("valor de credito "+rs1.getLong(6)+" valor de debito"+rs1.getLong(5)+" valor de la diferencia"+sal);
																//System.out.println("valor de cuenta "+cuenta+"valor de valor 9 de la consulta "+rs2.getString(9));
																out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right' >"+FormatMoneda(sal)+"</td><td align='right' >"+FormatMoneda(rs1.getString(7))+"</td></tr>");
															}
														}
														}rs2.getStatement().getConnection().close();
													}rs1.getStatement().getConnection().close();
												}
											}else{
												if(cont==3){
																							
													String nivel1=valorNivel[1];
													String nivel2=valorNivel[2];
													String nivel3=valorNivel[3];
													if(nivel1.contains("1")){
														rs1=mpr.BuscarDatosBalanceG2(RC1, RC2, AC, MC, nivel2,nivel3,"nada","nada");
														while(rs1.next()){
															String cuenta1=rs1.getString(1);
															rs2=mpr.BuscarCuenta(cuenta1);
															while(rs2.next()){
																if(cuenta.equals(rs1.getString(9))){
																	System.out.println(" naturaleza"+rs1.getString(8));
																	if(rs1.getString(8).equals("Debito")){
																		saldo=((rs1.getLong(5))-(rs1.getLong(6)));
																		String sal=Long.toString(saldo);
																	//System.out.println("valor de cuenta "+cuenta+"valor de valor 9 de la consulta "+rs2.getString(9));
																	out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right' >"+FormatMoneda(sal)+"</td><td align='right' >"+FormatMoneda(rs1.getString(7))+"</td></tr>");
																	}else{
																		saldo=((rs1.getLong(6))-(rs1.getLong(5)));
																		String sal=Long.toString(saldo);
																		out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right' >"+FormatMoneda(sal)+"</td><td align='right' >"+FormatMoneda(rs1.getString(7))+"</td></tr>");
																	}
																	}
															}rs2.getStatement().getConnection().close();
														}rs1.getStatement().getConnection().close();
													}else{
														
														rs1=mpr.BuscarDatosBalanceG3(RC1, RC2, AC, MC, nivel1, nivel2,nivel3,"nada","nada");
														while(rs1.next()){
															String cuenta1=rs1.getString(1);
															rs2=mpr.BuscarCuenta(cuenta1);
															while(rs2.next()){
																if(cuenta.equals(rs1.getString(9))){
																	System.out.println(" naturaleza"+rs1.getString(8));
																	if(rs1.getString(8).equals("Debito")){
																		saldo=((rs1.getLong(5))-(rs1.getLong(6)));
																		String sal=Long.toString(saldo);
																	//System.out.println("valor de cuenta "+cuenta+"valor de valor 9 de la consulta "+rs2.getString(9));
																	out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right' >"+FormatMoneda(sal)+"</td><td align='right' >"+FormatMoneda(rs1.getString(7))+"</td></tr>");
																	}else{
																		saldo=((rs1.getLong(6))-(rs1.getLong(5)));
																		String sal=Long.toString(saldo);
																		out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right' >"+FormatMoneda(sal)+"</td><td align='right' >"+FormatMoneda(rs1.getString(7))+"</td></tr>");
																	}
																	}
															}rs2.getStatement().getConnection().close();
														}rs1.getStatement().getConnection().close();
													}
												
												}else{
													if(cont==4){
														
														String nivel1=valorNivel[1];
														String nivel2=valorNivel[2];
														String nivel3=valorNivel[3];
														String nivel4=valorNivel[4];
														if(nivel1.contains("1")){
															rs1=mpr.BuscarDatosBalanceG3(RC1, RC2, AC, MC, nivel2,nivel3,nivel4,"nada","nada");
															while(rs1.next()){
																String cuenta1=rs1.getString(1);
																rs2=mpr.BuscarCuenta(cuenta1);
																while(rs2.next()){
																	if(cuenta.equals(rs1.getString(9))){
																		System.out.println(" naturaleza"+rs1.getString(8));
																		if(rs1.getString(8).equals("Debito")){
																			saldo=((rs1.getLong(5))-(rs1.getLong(6)));
																			String sal=Long.toString(saldo);
																		//System.out.println("valor de cuenta "+cuenta+"valor de valor 9 de la consulta "+rs2.getString(9));
																		out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right' >"+FormatMoneda(sal)+"</td><td align='right' >"+FormatMoneda(rs1.getString(7))+"</td></tr>");
																		}else{
																			saldo=((rs1.getLong(6))-(rs1.getLong(5)));
																			String sal=Long.toString(saldo);
																			out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right' >"+FormatMoneda(sal)+"</td><td align='right' >"+FormatMoneda(rs1.getString(7))+"</td></tr>");
																		}
																		}
																}rs2.getStatement().getConnection().close();
															}rs1.getStatement().getConnection().close();
														}else{
															
															rs1=mpr.BuscarDatosBalanceG4(RC1, RC2, AC, MC, nivel1, nivel2,nivel3,nivel4,"nada","nada");
															while(rs1.next()){
																String cuenta1=rs1.getString(1);
																rs2=mpr.BuscarCuenta(cuenta1);
																while(rs2.next()){
																	if(cuenta.equals(rs1.getString(9))){
																		System.out.println(" naturaleza"+rs1.getString(8));
																		if(rs1.getString(8).equals("Debito")){
																			saldo=((rs1.getLong(5))-(rs1.getLong(6)));
																			String sal=Long.toString(saldo);
																		//System.out.println("valor de cuenta "+cuenta+"valor de valor 9 de la consulta "+rs2.getString(9));
																		out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right' >"+FormatMoneda(sal)+"</td><td align='right' >"+FormatMoneda(rs1.getString(7))+"</td></tr>");
																		}else{
																			saldo=((rs1.getLong(6))-(rs1.getLong(5)));
																			String sal=Long.toString(saldo);
																			out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right' >"+FormatMoneda(sal)+"</td><td align='right' >"+FormatMoneda(rs1.getString(7))+"</td></tr>");
																		}
																		}
																}rs2.getStatement().getConnection().close();
															}rs1.getStatement().getConnection().close();
														}
														
													}else{
														if(cont==5){
															
															String nivel1=valorNivel[1];
															String nivel2=valorNivel[2];
															String nivel3=valorNivel[3];
															String nivel4=valorNivel[4];
															String nivel5=valorNivel[5];
															if(nivel1.contains("1")){
																rs1=mpr.BuscarDatosBalanceG4(RC1, RC2, AC, MC, nivel2,nivel3,nivel4, nivel5,"nada","nada");
																while(rs1.next()){
																	String cuenta1=rs1.getString(1);
																	rs2=mpr.BuscarCuenta(cuenta1);
																	while(rs2.next()){
																		if(cuenta.equals(rs1.getString(9))){
																			System.out.println(" naturaleza"+rs1.getString(8));
																			if(rs1.getString(8).equals("Debito")){
																				saldo=((rs1.getLong(5))-(rs1.getLong(6)));
																				String sal=Long.toString(saldo);
																			//System.out.println("valor de cuenta "+cuenta+"valor de valor 9 de la consulta "+rs2.getString(9));
																			out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right' >"+FormatMoneda(sal)+"</td><td align='right' >"+FormatMoneda(rs1.getString(7))+"</td></tr>");
																			}else{
																				saldo=((rs1.getLong(6))-(rs1.getLong(5)));
																				String sal=Long.toString(saldo);
																				out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right' >"+FormatMoneda(sal)+"</td><td align='right' >"+FormatMoneda(rs1.getString(7))+"</td></tr>");
																			}
																			}
																	}rs2.getStatement().getConnection().close();
																}rs1.getStatement().getConnection().close();
															}else{}
																
														}	
													}
												}
											}
										}
									
									long stotal=0;
									String st="";
									if(rs.getString(8).equals("Debito")){
										System.out.println("Totalizacion "+(rs.getLong(5))+"-"+(rs.getLong(6)));
										stotal=((rs.getLong(5))-(rs.getLong(6)));
										st=Long.toString(stotal);
									}else{
										System.out.println("Totalizacion "+(rs.getLong(5))+"-"+(rs.getLong(6)));
										stotal=((rs.getLong(6))-(rs.getLong(5)));
										st=Long.toString(stotal);
									}
								out.print("<tr class='rep'><td></td><td align='right' > </td><td align='right' ><b> TOTAL "+nombre+" &nbsp; &nbsp; </b> <u><b> "+FormatMoneda(st)+"</b></u></td><td align='right' > <u><b>"+FormatMoneda(rs.getString(7))+" </u><b></td></tr>");
								
									
								}
								rs.getStatement().getConnection().close();
								rs=mpr.BuscarDatosBalanceG1("4","79", AC, MC, "1","nada","nada");
								long parcialCta567=0;
								long Cta4=0;
								long Cta567=0;
								long Cta4A=0;
								long Cta567A=0;
								while(rs.next()){
									if(rs.getString(9).equals("4")){
										if(rs.getString(8).equals("Debito")){
											Cta4=((rs.getLong(5))-(rs.getLong(6)));
										}else{
											Cta4=((rs.getLong(6))-(rs.getLong(5)));
										}
										Cta4A=rs.getLong(7);
									}else{
										
										if(rs.getString(8).equals("Debito")){
											parcialCta567=((rs.getLong(5))-(rs.getLong(6)));
										}else{
											parcialCta567=((rs.getLong(6))-(rs.getLong(5)));
										}
										System.out.println("parcial Cta567"+parcialCta567);
										Cta567=Cta567+parcialCta567;
										Cta567A=Cta567A+rs.getLong(7);
									}
								}
								rs.getStatement().getConnection().close();
								long Util=(Cta4-Cta567);
								long UtilA=(Cta4A-Cta567A);
								String TotalUtil=Long.toString(Util);
								String TotalUtilA=Long.toString(UtilA);
								out.print("<tr class='contpre' ><td></td><td align='left'><br><b>UTILIDAD O PERDIDA DEL EJERCICIO </b></td><td align='right'><br> <font style='text-decoration: overline underline;'>"+FormatMoneda(TotalUtil)+"</font></td><td align='right'><br> <font style='text-decoration: overline underline;'>"+FormatMoneda(TotalUtilA)+"</font></td></tr>");
								out.print("<tr class='rep'><td colspan='4' align='center'><br><br><br><br><br><br>&nbsp; &nbsp;<font style='text-decoration: overline;'> &nbsp; &nbsp; &nbsp; &nbsp; GERENTE GENERAL &nbsp; &nbsp; &nbsp; &nbsp;</font> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; <font style='text-decoration: overline;'> &nbsp; &nbsp; &nbsp; &nbsp; REVISOR FISCAL &nbsp; &nbsp; &nbsp; &nbsp;</font></td></tr>");
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						
					}//CIERRE DE PG3
						
				}
			}
			
	}
			
			
		
		
		if(va.equals("RepAuxiliarCT")){
			String RC1=req.getParameter("RC1");
			String RC2=req.getParameter("RC2");
			String MC=req.getParameter("MC");
			String MC2=req.getParameter("MC2");
			String AC=req.getParameter("AC");
			String TDOC=req.getParameter("TDOC");
			String TERC=req.getParameter("TERC"); 
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
			java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());
			out.print("<br><br><table width='80%'>");
			rs=mpr.BuscarEmpresa();
			try {
				while(rs.next()){
				out.print("<tr class='contpre'><td align='center' colspan='11'>"+rs.getString(1)+"</td></tr>");
				out.print("<tr class='contpre'><td align='center' colspan='11'>"+rs.getString(2)+"<br></td></tr>");
				
				}rs.getStatement().getConnection().close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			out.print("<tr class='contpre'><td colspan='7' align='left'>AUXILIAR DE CONTABILIDAD POR CUENTA Y TERCERO</td><td colspan='2' align='right'>Fecha de Generacion:</td><td colspan='2'>"+Fecha+"</td></tr>");
			out.print("<tr class='contpre'><td colspan='7' align='left'>CORRESPONDIENTE A PERIODO "+MC+" AL "+MC2+" DEL A�O "+AC+"</td><td colspan='2' align='rigth'>&nbsp; Hora de Generacion:</td><td colspan='2'>"+Hora+"</td></tr>");
			out.print("<tr><td colspan='11'><hr></td></tr>");
			out.print("<tr class='contpre' ><br><br><b><td>&nbsp; FECHA &nbsp;</td><td>&nbsp; TIPO DOC. &nbsp;</td><td>&nbsp; SUCURSAL &nbsp;</td><td>&nbsp; C. COSTO &nbsp;</td><td>&nbsp; SUB. C. COSTO &nbsp;</td><td>&nbsp; TERCERO &nbsp;</td><td>&nbsp; DESCRIPCION DEL DOCUMENTO &nbsp;</td><td>&nbsp; DOC. REF &nbsp;</td><td>&nbsp; DEBITOS &nbsp;</td><td>&nbsp; CREDITOS &nbsp;</td><td>&nbsp; PARCIAL &nbsp;</td></b></tr>");
			out.print("<tr><td colspan='11'><hr></td></tr>");
			//rs=mpr.BuscarSaldoCuenT(RC1,RC2,AC,MC,TERC,TDOC,MC2);
			long sumdeb=0;
			long sumcre=0;
			long parcial=0;
			long sa=0;
			float cont2=0;
			int cont=0;
			String  cuenta="";
			rs2=mpr.BuscarCuentaTerc(RC1, RC2, AC, MC, TERC, TDOC,  MC2);
			try {
			while(rs2.next()){
				out.print("<tr class='contpre'><b><td >CUENTA:</td><td>"+rs2.getString(2)+"</td><td>"+rs2.getString(3)+"</td></b></tr>");
				cuenta=rs2.getString(1);
				System.out.println("Valor de cuenta en rs2"+cuenta);
				rs=mpr.BuscarSaldoCuenT(cuenta,AC,MC,TERC,TDOC,MC2);
				while(rs.next()){
					System.out.println("Valor de cuenta en rs "+cuenta);
					String tercero=rs.getString(4);
					int vali=0;
					rs1=mpr.BuscarCuentaAuxDetTer(cuenta,AC,MC, MC2,TDOC,tercero);
					if(rs1.next()){
						vali=1;
						System.out.println("valor de tercero "+tercero+" valor de cuenta "+cuenta+" valores de la validacion");
					}rs1.getStatement().getConnection().close();
					System.out.println("valor de vali"+vali);
					if(vali!=0){
					out.print("<tr class='contpre'><td colspan='2'><br>TERCERO :</td><td colspan='2'><br>"+rs.getString(5)+"</td><td colspan='3'><br>"+rs.getString(6)+"</td><td colspan='2' align='right'><br>SALDO ANTERIOR </td><td colspan='2' align='right'><br>"+FormatMoneda(rs.getString(7))+"</td></tr>");
					// cuenta=rs.getString(1);
					}
					int p=0;
					rs1=mpr.BuscarCuentaAuxDetTer(cuenta,AC,MC, MC2,TDOC,tercero);
					int verif=0;
					while(rs1.next()){
								sa=rs.getLong(7);
								String val=rs1.getString(11);
								System.out.println(" val "+val);
					
								if(val.equals("Debito")){
									if(p==0){
									parcial=(sa+(rs1.getLong(9)));
									p=1;
									}else{
									parcial=(parcial+(rs1.getLong(9)));
									}
									System.out.println("valor p"+p);
									//System.out.println("saldo anterior "+sa);
									System.out.println("Resultado de la suma con debito"+parcial);
									parcial=(parcial-(rs1.getLong(10)));
									
									System.out.println("Resultado de la resta con credito "+parcial);
								}else{
									if(p==0){
										parcial=(sa+(rs1.getLong(10)));
										p=1;
										}else{
										parcial=(parcial+(rs1.getLong(10)));
										}
									//System.out.println("Resultado de la suma con credito"+parcial);
									parcial=(parcial-(rs1.getLong(9)));
									//System.out.println("Resultado de la resta con debito "+parcial);
								}
								
								sumdeb=((rs1.getLong(9))+sumdeb);
								sumcre=((rs1.getLong(10))+sumcre);
						//System.out.print("VALOR  DE PARCIAL EN LONG "+parcial);
						String parcial2=Long.toString(parcial);
					//	System.out.print("VALOR  DE PARCIAL EN String "+parcial2);
						
						out.print("<tr class='rep'><td>"+rs1.getString(1)+"</td><td>"+rs1.getString(2)+"</td><td>"+rs1.getString(3)+"</td><td>"+rs1.getString(4)+"</td><td>"+rs1.getString(5)+"</td><td>"+rs1.getString(6)+"</td><td>"+rs1.getString(7)+"</td><td>"+rs1.getString(8)+"</td><td align='right'>"+FormatMoneda(rs1.getString(9))+"</td><td align='right'>"+FormatMoneda(rs1.getString(10))+"</td><td align='right'>"+FormatMoneda(parcial2)+"</td></tr>");
						verif=1;
					}
					rs1.getStatement().getConnection().close();
					String sumdeb1=Long.toString(sumdeb);
					String sumcre1=Long.toString(sumcre);
					
					if(vali!=0){
						out.print("<tr><td colspan='8'></td><td colspan='3'> <hr></td>");
						out.print("<tr class='rep' ><b><td colspan='8' align='right'> TOTAL TERCERO</td><td colspan='1' align='right'> "+FormatMoneda(sumdeb1)+" </td><td colspan='1' align='right'> "+FormatMoneda(sumcre1)+" </td><td colspan='1' align='right'> "+FormatMoneda(rs.getString(8))+" </td></b></tr>");
						}
					
			}
				
				rs1=mpr.BuscarSaldosAcum(cuenta,MC,MC2,AC);
				while(rs1.next()){
				out.print("<tr class='rep' ><b><td colspan='8' align='right'> TOTAL CUENTA </td><td colspan='1' align='right'> "+FormatMoneda(rs1.getString(4))+" </td><td colspan='1' align='right'> "+FormatMoneda(rs1.getString(5))+"</td><td colspan='1' align='right'> "+FormatMoneda(rs1.getString(6))+"</td></b></tr>");
				}	
				rs1.getStatement().getConnection().close();
			}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.print("</table>");
			
		}
		
		if(va.equals("RepBalanceTercero")){
			String RC1=req.getParameter("RC1");
			String RC2=req.getParameter("RC2");
			String MC=req.getParameter("MC");
			String AC=req.getParameter("AC");
			String TERC=req.getParameter("TERC"); 
			
			System.out.println("Valor de tercero"+TERC);
			
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
			java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());
			out.print("<br><br><table width='80%'>");
			rs=mpr.BuscarEmpresa();
			try {
				while(rs.next()){
				out.println("<tr class='contpre'><td align='center' colspan='6'>"+rs.getString(1)+"</td></tr>");
				out.println("<tr class='contpre'><td align='center' colspan='6'>"+rs.getString(2)+"<br></td></tr>");
				
				}rs.getStatement().getConnection().close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			rs=mpr.BuscarSaldos(RC1, RC2, MC, AC);	
			out.print("<tr class='contpre'><td colspan='3' align='left'>BALANCE DE PRUEBA GENERAL POR CUENTA Y POR TERCERO</td><td colspan='1' align='right'>Fecha de Generacion:</td><td colspan='2'>"+Fecha+"</td></tr>");
			out.print("<tr class='contpre'><td colspan='3' align='left'>CORRESPONDIENTE AL PERIODO "+MC+" DEL A�O "+AC+"</td><td colspan='1' align='rigth'>Hora de Generacion:</td><td colspan='2'>"+Hora+"</td></tr>");
			out.print("<tr><td colspan='11'><hr></td></tr>");
			out.print("<tr class='contpre' ><br><br><b><td>&nbsp; CUENTA &nbsp;</td><td>&nbsp; NOMBRE DE LA CUENTA &nbsp;</td><td>&nbsp; SALDO ANTERIOR &nbsp;</td><td>&nbsp; DEBITO &nbsp;</td><td>&nbsp; CREDITO &nbsp;</td><td>&nbsp; SALDO &nbsp;</td></b></tr>");
			out.print("<tr><td colspan='11'><hr></td></tr>");
			try {
				while(rs.next()){
					
					String cuenta=rs.getString(1);
					rs1=mpr.BuscarCuentaTercero(cuenta,MC,AC,TERC);
					int val=0;
					//System.out.println("valor de val antes de entrar");
					while(rs1.next()){
						if(val==0){
						out.print("<tr class='rep'><b><td>"+rs.getString(2)+"</td><td >"+rs.getString(3)+"</td><td align='right'>"+FormatMoneda(rs.getString(4))+"</td><td align='right'>"+FormatMoneda(rs.getString(6))+"</td><td align='right'>"+FormatMoneda(rs.getString(7))+"</td><td align='right'>"+FormatMoneda(rs.getString(5))+"</td></b></tr>");
						val=1;
						//System.out.print("entrando");
						}
						out.println("<tr class='rep'><td>"+rs1.getString(1)+"</td><td>"+rs1.getString(2)+"</td><td align='right'>"+FormatMoneda(rs1.getString(3))+"</td><td align='right'>"+FormatMoneda(rs1.getString(4))+"</td><td align='right'>"+FormatMoneda(rs1.getString(5))+"</td><td align='right'>"+FormatMoneda(rs1.getString(6))+"</td></tr>");
						//System.out.print("valor de val dentro del ciclo buscar cuenta tercero "+val);
						
					}
					//System.out.println("valor depues del ciclo "+val);
					rs1.getStatement().getConnection().close();
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.print("</table>");
			
			
		}
		
		if(va.equals("ReporteBalanceGeneral")){
			String RC1=req.getParameter("RC1");
			String RC2=req.getParameter("RC2");
			String MC=req.getParameter("MC");
			String AC=req.getParameter("AC");
			if(RC2.equals("TODAS")){
				
			}else{
				/*int rc11=Integer.parseInt(RC1);
				int rc22=Integer.parseInt(RC2);
				if(RC1.equals(RC2)){
				}else{*/
				RC2=RC2+"9";
				/*}*/
			}
			System.out.println(" valor de RC2"+RC2);
			String nivelSeleccionado=req.getParameter("nivelSeleccionado");
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
			java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());
			String[] detnivelSeleccionado = nivelSeleccionado.split("_");
			
			out.print("<br><table width='80%' >");
			rs=mpr.BuscarEmpresa();
			try {
				while(rs.next()){
				out.println("<tr class='contpre'><td align='center' colspan='6'>"+rs.getString(1)+"</td></tr>");
				out.println("<tr class='contpre'><td align='center' colspan='6'>"+rs.getString(2)+"<br></td></tr>");
				
				}rs.getStatement().getConnection().close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			out.print("<tr class='contpre'><td></td><td  align='left' colspan='1'> BALANCE GENERAL &nbsp; &nbsp;</td><td align='left' colspan='3'>Fecha :</td><td colspan='2' >"+Fecha+"</td></tr>");
			out.print("<tr class='contpre'><td></td><td  align='left' colspan='1'>CORRESPONDIENTE AL PERIODO "+MC+" DEL A�O "+AC+" &nbsp; &nbsp;<br></td><td  align='left' colspan='3'>Hora    :<br></td><td colspan='2'>"+Hora+"<br></td></tr>");
			out.print("<tr><td colspan='6'><hr></td>");
			out.print("<tr class='rep' ><b><td colspan='1'>CUENTA </td><td colspan='3' >&nbsp; NOMBRE DE LA CUENTA &nbsp;</td><td colspan='2'>&nbsp; SALDO CUENTA &nbsp;</td></b></tr>");
			out.print("<tr><td colspan='6'><hr></td>");
			String cuenta="";
			String valorNivel [] = new String[6];
			int cont=0;
			for (int i = 0; i < detnivelSeleccionado.length; i++) {
				if(detnivelSeleccionado[i].equals("1")){
					valorNivel[i+1]="1";
				}else{
					if(detnivelSeleccionado[i].equals("2")){
						valorNivel[i+1]="2";
					}else{
						if(detnivelSeleccionado[i].equals("3")){
							valorNivel[i+1]="4";
						}else{
							if(detnivelSeleccionado[i].equals("4")){
								valorNivel[i+1]="6";
							}else{
								if(detnivelSeleccionado[i].equals("5")){						
								valorNivel[i+1]="8";
								}
							}
						}
				}
				
			}
				cont=cont+1;
			}
			long Pasivo=0;
			long Patrimonio=0;
			long TotalPP=0;
			String valp="0";
			String valpat="0";
			String nombre="";
			String TotalPaPa="0";
			if(cont>0){
				//String nivel=valorNivel[1];
				rs=mpr.BuscarDatosBalanceG1(RC1, RC2, AC, MC, "1","nada","nada");
				try {
					while(rs.next()){
						cuenta=rs.getString(1);
						rs1=mpr.BuscarCuenta(cuenta);
						while(rs1.next()){
							nombre=rs1.getString(3);
						out.print("<tr class='rep'><td align='left' colspan='1' ><b>"+cuenta+"</b></td><td colspan='3' align='left'><b>"+rs1.getString(3)+"</b></td><td colspan='2'></td></tr>");
						}
						rs1.getStatement().getConnection().close();
						if(cont==1){
								String nivel=valorNivel[1];
								if(nivel.contains("1")){
									
								}else{
									rs2=mpr.BuscarDatosBalanceG1(RC1, RC2, AC, MC, nivel,"nada","nada");
									while(rs2.next()){
										String cuenta1=rs2.getString(1);
										rs3=mpr.BuscarCuenta(cuenta1);
										while(rs3.next()){
											if(cuenta.equals(rs2.getString(9))){
												System.out.println("valor de cuenta "+cuenta+"valor de valor 9 de la consulta "+rs2.getString(9));
												out.print("<tr class='rep'><td align='left' colspan='1'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' colspan='3'> &nbsp;"+rs3.getString(3)+"</td><td align='center' colspan='2'>  "+FormatMoneda(rs2.getString(7))+"</td></tr>");
											}
										}
										rs3.getStatement().getConnection().close();
									}
									rs2.getStatement().getConnection().close();
								}
								
								
							}else{
								if(cont==2){
									String nivel1=valorNivel[1];
									String nivel2=valorNivel[2];
									if(nivel1.contains("1")){
										rs1=mpr.BuscarDatosBalanceG1(RC1, RC2, AC, MC, nivel2,"nada","nada");
										while(rs1.next()){
											String cuenta1=rs1.getString(1);
											rs2=mpr.BuscarCuenta(cuenta1);
											while(rs2.next()){
												if(cuenta.equals(rs1.getString(9))){
													System.out.println("valor de cuenta "+cuenta+"valor de valor 9 de la consulta "+rs2.getString(9));
													out.print("<tr class='rep'><td align='left' colspan='1'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' colspan='3'> &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='center' colspan='2'>"+FormatMoneda(rs1.getString(7))+"</td></tr>");
												}
											}rs2.getStatement().getConnection().close();
										}rs1.getStatement().getConnection().close();
									}else{
										
										rs1=mpr.BuscarDatosBalanceG2(RC1, RC2, AC, MC, nivel1, nivel2,"nada","nada");
										while(rs1.next()){
											String cuenta1=rs1.getString(1);
											rs2=mpr.BuscarCuenta(cuenta1);
											while(rs2.next()){
												if(cuenta.equals(rs1.getString(9))){
													System.out.println("valor de cuenta "+cuenta+"valor de valor 9 de la consulta "+rs2.getString(9));
													out.print("<tr class='rep'><td align='left' colspan='1'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' colspan='3'> &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='center' colspan='2'>"+FormatMoneda(rs1.getString(7))+"</td></tr>");
												}
											}rs2.getStatement().getConnection().close();
										}rs1.getStatement().getConnection().close();
									}
								}else{
									if(cont==3){
																				
										String nivel1=valorNivel[1];
										String nivel2=valorNivel[2];
										String nivel3=valorNivel[3];
										if(nivel1.contains("1")){
											rs1=mpr.BuscarDatosBalanceG2(RC1, RC2, AC, MC, nivel2,nivel3,"nada","nada");
											while(rs1.next()){
												String cuenta1=rs1.getString(1);
												rs2=mpr.BuscarCuenta(cuenta1);
												while(rs2.next()){
													if(cuenta.equals(rs1.getString(9))){
														System.out.println("valor de cuenta "+cuenta+"valor de valor 9 de la consulta "+rs2.getString(9));
														out.print("<tr class='rep'><td align='left' colspan='1'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' colspan='3'> &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='center' colspan='2'>"+FormatMoneda(rs1.getString(7))+"</td></tr>");
													}
												}rs2.getStatement().getConnection().close();
											}rs1.getStatement().getConnection().close();
										}else{
											
											rs1=mpr.BuscarDatosBalanceG3(RC1, RC2, AC, MC, nivel1, nivel2,nivel3,"nada","nada");
											while(rs1.next()){
												String cuenta1=rs1.getString(1);
												rs2=mpr.BuscarCuenta(cuenta1);
												while(rs2.next()){
													if(cuenta.equals(rs1.getString(9))){
														System.out.println("valor de cuenta "+cuenta+"valor de valor 9 de la consulta "+rs2.getString(9));
														out.print("<tr class='rep'><td align='left' colspan='1'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' colspan='3'> &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='center' colspan='2'>"+FormatMoneda(rs1.getString(7))+"</td></tr>");
													}
												}rs2.getStatement().getConnection().close();
											}rs1.getStatement().getConnection().close();
										}
									
									}else{
										if(cont==4){
											
											String nivel1=valorNivel[1];
											String nivel2=valorNivel[2];
											String nivel3=valorNivel[3];
											String nivel4=valorNivel[4];
											if(nivel1.contains("1")){
												rs1=mpr.BuscarDatosBalanceG3(RC1, RC2, AC, MC, nivel2,nivel3,nivel4,"nada","nada");
												while(rs1.next()){
													String cuenta1=rs1.getString(1);
													rs2=mpr.BuscarCuenta(cuenta1);
													while(rs2.next()){
														if(cuenta.equals(rs1.getString(9))){
															System.out.println("valor de cuenta "+cuenta+"valor de valor 9 de la consulta "+rs2.getString(9));
															out.print("<tr class='rep'><td align='left' colspan='1'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' colspan='3'> &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='center' colspan='2'>"+FormatMoneda(rs1.getString(7))+"</td></tr>");
														}
													}rs2.getStatement().getConnection().close();
												}rs1.getStatement().getConnection().close();
											}else{
												
												rs1=mpr.BuscarDatosBalanceG4(RC1, RC2, AC, MC, nivel1, nivel2,nivel3,nivel4,"nada","nada");
												while(rs1.next()){
													String cuenta1=rs1.getString(1);
													rs2=mpr.BuscarCuenta(cuenta1);
													while(rs2.next()){
														if(cuenta.equals(rs1.getString(9))){
															System.out.println("valor de cuenta "+cuenta+"valor de valor 9 de la consulta "+rs2.getString(9));
															out.print("<tr class='rep'><td align='left' colspan='1'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' colspan='3'> &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='center' colspan='2'>"+FormatMoneda(rs1.getString(7))+"</td></tr>");
														}
													}rs2.getStatement().getConnection().close();
												}rs1.getStatement().getConnection().close();
											}
											
										}else{
											if(cont==5){
												
												String nivel1=valorNivel[1];
												String nivel2=valorNivel[2];
												String nivel3=valorNivel[3];
												String nivel4=valorNivel[4];
												String nivel5=valorNivel[5];
												if(nivel1.contains("1")){
													rs1=mpr.BuscarDatosBalanceG4(RC1, RC2, AC, MC, nivel2,nivel3,nivel4, nivel5,"nada","nada");
													while(rs1.next()){
														String cuenta1=rs1.getString(1);
														rs2=mpr.BuscarCuenta(cuenta1);
														while(rs2.next()){
															if(cuenta.equals(rs1.getString(9))){
																System.out.println("valor de cuenta "+cuenta+"valor de valor 9 de la consulta "+rs2.getString(9));
																out.print("<tr class='rep'><td align='left' colspan='1'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' colspan='3'> &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='center' colspan='2'>"+FormatMoneda(rs1.getString(7))+"</td></tr>");
															}
														}rs2.getStatement().getConnection().close();
													}rs1.getStatement().getConnection().close();
												}else{}
													
											}	
										}
									}
								}
							}
					out.print("<tr class='rep'><td align='right' colspan='4'> <b>TOTAL "+nombre+" </b></td><td colspan='2' align='center'><u><b> "+FormatMoneda(rs.getString(7))+"</b></u></td></tr>");
					
						
					}
					rs.getStatement().getConnection().close();
					rs=mpr.BuscarDatosBalanceG1("1","39", AC, MC, "1","nada","nada");
					while(rs.next()){
						if(rs.getString(9).equals("2")){
							valp=rs.getString(7);
							Pasivo=Long.parseLong(valp);	
						}else{
							if(rs.getString(9).equals("3")){
								valpat=rs.getString(7);
								Patrimonio=Long.parseLong(valpat);
							}
						}
						System.out.println("valor de la cuenta "+cuenta);
						System.out.println("valor  de valp"+valp);
						System.out.println("valor de  Pasivo"+Pasivo);
						TotalPP=(Pasivo+Patrimonio);
						TotalPaPa=Long.toString(TotalPP);
					}
					rs.getStatement().getConnection().close();
					rs=mpr.BuscarDatosBalanceG1("4","79", AC, MC, "1", "nada","nada");
					long Cta4=0;
					long Cta567=0;
					while(rs.next()){
						if(rs.getString(9).equals("4")){
							Cta4=rs.getLong(7);
						}else{
							Cta567=Cta567+rs.getLong(7);
						}
					}
					rs.getStatement().getConnection().close();
					long Util=(Cta4-Cta567);
					String TotalUtil=Long.toString(Util);
					out.print("<tr class='contpre' ><td colspan='1'></td><td align='left' colspan='3'><br><b>UTILIDAD O PERDIDA DEL EJERCICIO </b></td><td align='center' colspan='2'><br>"+FormatMoneda(TotalUtil)+"</td></tr>");
					out.print("<tr class='contpre' ><td colspan='1'></td><td align='left' colspan='3'><b>TOTAL PATRIMONIO  </b></td><td align='center' colspan='2'>"+FormatMoneda(valpat)+"</td></tr>");
					out.print("<tr class='contpre' ><td colspan='1'></td><td align='left' colspan='3'><b>TOTAL PASIVO Y PATRIMONIO </b></td><td align='center'  colspan='2' >"+FormatMoneda(TotalPaPa)+"</td></tr>");
					out.print("<tr class='contpre' align='center'><td colspan='6' ><br><br><br><br><br><br>&nbsp; &nbsp;<font style='text-decoration: overline;'> &nbsp; &nbsp; &nbsp; &nbsp; GERENTE GENERAL &nbsp; &nbsp; &nbsp; &nbsp;</font> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; <font style='text-decoration: overline;'> &nbsp; &nbsp; &nbsp; &nbsp; REVISOR FISCAL &nbsp; &nbsp; &nbsp; &nbsp;</font></td></tr>");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
		}
		
		
		if(va.equals("ReporteBalancePruebaSucursal")){
			String RC1=req.getParameter("RC1");
			String RC2=req.getParameter("RC2");
			String MC=req.getParameter("MC");
			String AC=req.getParameter("AC");
			String Suc=req.getParameter("Suc");
			if(RC2.equals("TODAS")){
				
			}else{
				/*int rc11=Integer.parseInt(RC1);
				int rc22=Integer.parseInt(RC2);
				if(RC1.equals(RC2)){
				}else{*/
				RC2=RC2+"9";
				/*}*/
			}
			System.out.println(" valor de RC2"+RC2);
			String nivelSeleccionado=req.getParameter("nivelSeleccionado");
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
			java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());
			String[] detnivelSeleccionado = nivelSeleccionado.split("_");
			
			out.print("<br><br><table width='80%' >");
			rs=mpr.BuscarEmpresa();
			try {
				while(rs.next()){
				out.println("<tr class='contpre'><td align='center' colspan='6'>"+rs.getString(1)+"</td></tr>");
				out.println("<tr class='contpre'><td align='center' colspan='6'>"+rs.getString(2)+"<br></td></tr>");
				
				}rs.getStatement().getConnection().close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			out.print("<tr class='contpre'><td></td><td  align='left' > BALANCE DE PRUEBA POR SUCURSAL </td><td align='right'>Fecha :</td><td align='right' colspan='2'>"+Fecha+"</td></tr>");
			out.print("<tr class='contpre'><td></td><td  align='left' >CORRESPONDIENTE AL PERIODO "+MC+" DEL A�O "+AC+" </td><td  align='right'>Hora :</td><td colspan='2' align='right'>"+Hora+"</td></tr>");
			out.print("<tr><td colspan='6'><hr></td>");
			out.print("<tr class='rep' ><b><td >&nbsp; CUENTA &nbsp;</td><td>&nbsp; NOMBRE DE LA CUENTA &nbsp;</td><td>&nbsp; SALDO ANTERIOR &nbsp;</td><td>&nbsp; DEBITOS &nbsp;</td><td>&nbsp; CREDITOS &nbsp;</td><td>&nbsp; SALDO NUEVO &nbsp;</td></b></tr>");
			out.print("<tr><td colspan='6'><hr></td>");
			String cuenta="";
			String valorNivel [] = new String[6];
			int cont=0;
			for (int i = 0; i < detnivelSeleccionado.length; i++) {
				if(detnivelSeleccionado[i].equals("1")){
					valorNivel[i+1]="1";
				}else{
					if(detnivelSeleccionado[i].equals("2")){
						valorNivel[i+1]="2";
					}else{
						if(detnivelSeleccionado[i].equals("3")){
							valorNivel[i+1]="4";
						}else{
							if(detnivelSeleccionado[i].equals("4")){
								valorNivel[i+1]="6";
							}else{
								if(detnivelSeleccionado[i].equals("5")){						
								valorNivel[i+1]="8";
								}
							}
						}
				}
				
			}
				cont=cont+1;
			}
		
			long PreSumaDebitoC1=0;
			long PreSumaCreditoC1=0;
			long PreSumaDebitoC2=0;
			long PreSumaCreditoC2=0;
			long PreSumaSAD=0;
			long PreSumaSAC=0;
			if(cont>0){
				//String nivel=valorNivel[1];
				rs4=mpr.BuscarNombreSuc(Suc);
				try {
					while(rs4.next()){
						out.print("<tr class='contpre'><td><br> <b>SUCURSAL No: </b> </td><td><br>"+rs4.getString(2)+"</td><tr>");
						Suc=rs4.getString(1);
						PreSumaDebitoC1=0;
						PreSumaCreditoC1=0;
						PreSumaDebitoC2=0;
						PreSumaCreditoC2=0;
						PreSumaSAD=0;
						PreSumaSAC=0;
								System.out.println("Valor de cont "+cont);
								if(cont==1){
										String nivel=valorNivel[1];
										System.out.println("valor de nivel"+nivel);
										rs=mpr.BuscarDatosBalanceG1(RC1,RC2,AC,MC,nivel,Suc,"nada");								
										while(rs.next()){
											cuenta=rs.getString(1);
												if(cuenta.equals("")){
												
												}else{
													rs1=mpr.BuscarCuenta(cuenta);
													while(rs1.next()){
														out.println("<tr align='left' class='rep'><td>"+cuenta+"</td><td>"+rs1.getString(3)+"</td><td align='right'>"+FormatMoneda(rs.getString(4))+" &nbsp;</td><td align='right'>"+FormatMoneda(rs.getString(5))+" &nbsp;</td><td align='right'>"+FormatMoneda(rs.getString(6))+" &nbsp;</td><td align='right'>"+FormatMoneda(rs.getString(7))+"</td></tr>");
														String Naturaleza=rs.getString(8);
															if(Naturaleza.equals("Debito")){
																	String vald=rs.getString(5);
																	long vald1=Long.parseLong(vald); 
																	PreSumaDebitoC1=(vald1+PreSumaDebitoC1);
																	String valc=rs.getString(6);
																	long valc1=Long.parseLong(valc);
																	PreSumaCreditoC1=(valc1+PreSumaCreditoC1);
																	String valsa=rs.getString(4);
																	long valsad=Long.parseLong(valsa);
																	PreSumaSAD=(valsad+PreSumaSAD);
															}else{
																	String valdd=rs.getString(5);
																	long vald2=Long.parseLong(valdd);
																	PreSumaDebitoC2=(vald2+PreSumaDebitoC2);
																	String valcc=rs.getString(6);
																	long valc2=Long.parseLong(valcc);
																	PreSumaCreditoC2=(valc2+PreSumaCreditoC2);
																	String valsa2=rs.getString(4);
																	long valsac=Long.parseLong(valsa2);
																	PreSumaSAC=(valsac+PreSumaSAC);
														
															}
													}rs1.getStatement().getConnection().close();
												}
										}rs.getStatement().getConnection().close();
										long dif1=PreSumaDebitoC1-PreSumaCreditoC1;
										long dif2=0;
										if(PreSumaDebitoC2<PreSumaCreditoC2){
											dif2=(-(PreSumaDebitoC2-PreSumaCreditoC2));
										}else{
											dif2=(PreSumaDebitoC2-PreSumaCreditoC2);
										}
										
										/*if((RC1.equals("todas"))&&(RC2.equals("todas"))){
											sql=("SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, cdasc.saldo_anterior,SUM(cdasc.debito),SUM(cdasc.credito),cdasc.saldo_nuevo, cc.NaturalezaCuenta, e.* FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc,empresa e WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"' GROUP BY cuenta  ORDER BY cuenta ASC ");
										}else{
											sql=("SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel+"))as cuenta,cc.CodigoCuenta, cc.NombreCuenta, cdasc.saldo_anterior,SUM(cdasc.debito),SUM(cdasc.credito),cdasc.saldo_nuevo, cc.NaturalezaCuenta, e.* FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, empresa e WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'and cc.codigo between '"+RC1+"' and '"+RC2+"' GROUP BY cuenta  ORDER BY cuenta ASC");
										}*/
										int ident=0;
										String PreSumaSAD1=Long.toString(PreSumaSAD);
										String PreSumaSAC1=Long.toString(PreSumaSAC);
										String PreSumaDebitoC11=Long.toString(PreSumaDebitoC1);
										String PreSumaCreditoC11=Long.toString(PreSumaCreditoC1);
										String dif11=Long.toString(dif1);
										String dif22=Long.toString(dif2);
										String PreSumaDebitoC22=Long.toString(PreSumaDebitoC2);
										String PreSumaCreditoC22=Long.toString(PreSumaCreditoC2);
										long DSA=(PreSumaSAD-PreSumaSAC);
										long DD=(PreSumaSAD-PreSumaSAC);
										long DC=(PreSumaCreditoC1-PreSumaCreditoC2);
										long DDIF=(dif1-dif2);
										String DSA1=Long.toString(DSA);
										String DD1=Long.toString(DD);
										String DC1=Long.toString(DC);
										String DDIF1=Long.toString(DDIF);
										if(nivel.equals("1")){
											out.print("<tr><td colspan='6'> <br><br> </td></tr>");
											out.print("<tr align='rigth' class='rep'><td colspan='2'class='contpre' ><b>TOTAL DEBITOS</b></td><td align='right'>"+FormatMoneda(PreSumaSAD1)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaDebitoC11)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaCreditoC11)+" &nbsp;</td><td align='right'>"+FormatMoneda(dif11)+" </td></tr>");
											out.print("<tr align='rigth' class='rep'><td colspan='2' class='contpre' ><b>TOTAL CREDITOS</b></td><td align='right'>"+FormatMoneda(PreSumaSAC1)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaDebitoC22)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaCreditoC22)+" &nbsp;</td><td align='right'>"+FormatMoneda(dif22)+" </td></tr>");
											out.print("<tr align='rigth' class='rep'><td colspan='2' class='contpre'><b>DIFERENCIA</b></td><td align='right'>"+FormatMoneda(DSA1)+" &nbsp;</td><td align='right'>"+FormatMoneda(DD1)+" &nbsp;</td><td align='right'>"+FormatMoneda(DC1)+" &nbsp;</td><td align='right'>"+FormatMoneda(DDIF1)+"</td></tr>");
											ident=1;
										}
										
											//out.println("<tr align='left'><td><p align='left'><input type='button' value=' Generar PDF ' onclick='verrep1("+RC1+","+RC2+","+AC+","+MC+","+nivel+","+PreSumaSAD+","+PreSumaDebitoC1+","+PreSumaCreditoC1+","+PreSumaDebitoC2+","+PreSumaCreditoC2+","+PreSumaSAC+","+dif1+","+dif2+","+ident+","+Fecha+","+Hora+")'/></p></td></tr>");
									
								}else{
									if(cont==2){
										
										String nivel1=valorNivel[1];
										String nivel2=valorNivel[2];
										rs=mpr.BuscarDatosBalanceG2(RC1,RC2,AC,MC,nivel1,nivel2,Suc,"nada");
										
											while(rs.next()){
												cuenta=rs.getString(1);
												if(cuenta.equals("")){
													
												}else{
													System.out.println("entrando a validacion en cont 2");
													rs1=mpr.BuscarCuenta(cuenta);
													while(rs1.next()){
														out.println("<tr align='left' class='rep'><td>"+cuenta+"</td><td>"+rs1.getString(3)+"</td><td align='right'>"+FormatMoneda(rs.getString(4))+" &nbsp;</td><td align='right'>"+FormatMoneda(rs.getString(5))+" &nbsp;</td><td align='right'>"+FormatMoneda(rs.getString(6))+" &nbsp;</td><td align='right'>"+FormatMoneda(rs.getString(7))+"</td></tr>");
													}
												}rs1.getStatement().getConnection().close();
											}rs.getStatement().getConnection().close();
											if(nivel1.equals("1")){
												rs=mpr.BuscarDatosBalanceG1(RC1,RC2,AC,MC,nivel1,Suc,"nada");
												while(rs.next()){
													cuenta=rs.getString(1);
													if(cuenta.equals("")){ 
														
													}else{
														String Naturaleza=rs.getString(8);
														if(Naturaleza.equals("Debito")){
															String vald=rs.getString(5);
															long vald1=Long.parseLong(vald); 
															PreSumaDebitoC1=(vald1+PreSumaDebitoC1);
															String valc=rs.getString(6);
															long valc1=Long.parseLong(valc);
															PreSumaCreditoC1=(valc1+PreSumaCreditoC1);
															String valsa=rs.getString(4);
															long valsad=Long.parseLong(valsa);
															PreSumaSAD=(valsad+PreSumaSAD);
																
														}else{
															String valdd=rs.getString(5);
															long vald2=Long.parseLong(valdd);
															PreSumaDebitoC2=(vald2+PreSumaDebitoC2);
															String valcc=rs.getString(6);
															long valc2=Long.parseLong(valcc);
															PreSumaCreditoC2=(valc2+PreSumaCreditoC2);
															String valsa2=rs.getString(4);
															long valsac=Long.parseLong(valsa2);
															PreSumaSAC=(valsac+PreSumaSAC);
														}
													}
													
												}rs.getStatement().getConnection().close();
											
												long dif1=PreSumaDebitoC1-PreSumaCreditoC1;
												long dif2=0;
												if(PreSumaDebitoC2<PreSumaCreditoC2){
													dif2=(-(PreSumaDebitoC2-PreSumaCreditoC2));
												}else{
													dif2=(PreSumaDebitoC2-PreSumaCreditoC2);
												}
											
												String PreSumaSAD1=Long.toString(PreSumaSAD);
												String PreSumaSAC1=Long.toString(PreSumaSAC);
												String PreSumaDebitoC11=Long.toString(PreSumaDebitoC1);
												String PreSumaCreditoC11=Long.toString(PreSumaCreditoC1);
												String dif11=Long.toString(dif1);
												String dif22=Long.toString(dif2);
												String PreSumaDebitoC22=Long.toString(PreSumaDebitoC2);
												String PreSumaCreditoC22=Long.toString(PreSumaCreditoC2);
												long DSA=(PreSumaSAD-PreSumaSAC);
												long DD=(PreSumaSAD-PreSumaSAC);
												long DC=(PreSumaCreditoC1-PreSumaCreditoC2);
												long DDIF=(dif1-dif2);
												String DSA1=Long.toString(DSA);
												String DD1=Long.toString(DD);
												String DC1=Long.toString(DC);
												String DDIF1=Long.toString(DDIF);
												out.print("<tr><td colspan='6'> <br><br> </td></tr>");
												out.print("<tr align='rigth' class='rep'><td colspan='2'class='contpre' ><b>TOTAL DEBITOS</b></td><td align='right'>"+FormatMoneda(PreSumaSAD1)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaDebitoC11)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaCreditoC11)+" &nbsp;</td><td align='right'>"+FormatMoneda(dif11)+"</td></tr>");
												out.print("<tr align='rigth' class='rep'><td colspan='2' class='contpre' ><b>TOTAL CREDITOS</b></td><td align='right'>"+FormatMoneda(PreSumaSAC1)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaDebitoC22)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaCreditoC22)+" &nbsp;</td><td align='right'>"+FormatMoneda(dif22)+" </td></tr>");
												out.print("<tr align='rigth' class='rep'><td colspan='2' class='contpre'><b>DIFERENCIA</b></td><td align='right'>"+FormatMoneda(DSA1)+" &nbsp;</td><td align='right'>"+FormatMoneda(DD1)+" &nbsp;</td><td align='right'>"+FormatMoneda(DC1)+" &nbsp;</td><td align='right'>"+FormatMoneda(DDIF1)+"</td></tr>");
												//out.println("<p align='left'><input type='button' value=' Generar PDF '/></p>");
											}

										
									}else{
										if(cont==3){
											
											String nivel1=valorNivel[1];
											String nivel2=valorNivel[2];
											String nivel3=valorNivel[3];
											rs=mpr.BuscarDatosBalanceG3(RC1,RC2,AC,MC,nivel1,nivel2,nivel3,Suc,"nada");
											
												while(rs.next()){
													cuenta=rs.getString(1);
													if(cuenta.equals("")){
														
													}else{
														rs1=mpr.BuscarCuenta(cuenta);
														while(rs1.next()){
															out.println("<tr align='left' class='rep'><td>"+cuenta+"</td><td>"+rs1.getString(3)+"</td><td align='right'>"+FormatMoneda(rs.getString(4))+" &nbsp;</td><td align='right'>"+FormatMoneda(rs.getString(5))+" &nbsp;</td><td align='right'>"+FormatMoneda(rs.getString(6))+" &nbsp;</td><td align='right'>"+FormatMoneda(rs.getString(7))+"</td>");
														}
														rs1.getStatement().getConnection().close();
													}
												}
												rs.getStatement().getConnection().close();
												if(nivel1.equals("1")){
													rs=mpr.BuscarDatosBalanceG1(RC1,RC2,AC,MC,nivel1,Suc,"nada");
													while(rs.next()){
														cuenta=rs.getString(1);
														if(cuenta.equals("")){ 
															
														}else{
																String Naturaleza=rs.getString(8);
																if(Naturaleza.equals("Debito")){
																	String vald=rs.getString(5);
																	long vald1=Long.parseLong(vald); 
																	PreSumaDebitoC1=(vald1+PreSumaDebitoC1);
																	String valc=rs.getString(6);
																	long valc1=Long.parseLong(valc);
																	PreSumaCreditoC1=(valc1+PreSumaCreditoC1);
																	String valsa=rs.getString(4);
																	long valsad=Long.parseLong(valsa);
																	PreSumaSAD=(valsad+PreSumaSAD);		
																}else{
																	String valdd=rs.getString(5);
																	long vald2=Long.parseLong(valdd);
																	PreSumaDebitoC2=(vald2+PreSumaDebitoC2);
																	String valcc=rs.getString(6);
																	long valc2=Long.parseLong(valcc);
																	PreSumaCreditoC2=(valc2+PreSumaCreditoC2);
																	String valsa2=rs.getString(4);
																	long valsac=Long.parseLong(valsa2);
																	PreSumaSAC=(valsac+PreSumaSAC);
																}
														}
															
													} rs.getStatement().getConnection().close();
												
													long dif1=PreSumaDebitoC1-PreSumaCreditoC1;
													long dif2=0;
													if(PreSumaDebitoC2<PreSumaCreditoC2){
														dif2=(-(PreSumaDebitoC2-PreSumaCreditoC2));
													}else{
														dif2=(PreSumaDebitoC2-PreSumaCreditoC2);
													}
													String PreSumaSAD1=Long.toString(PreSumaSAD);
													String PreSumaSAC1=Long.toString(PreSumaSAC);
													String PreSumaDebitoC11=Long.toString(PreSumaDebitoC1);
													String PreSumaCreditoC11=Long.toString(PreSumaCreditoC1);
													String dif11=Long.toString(dif1);
													String dif22=Long.toString(dif2);
													String PreSumaDebitoC22=Long.toString(PreSumaDebitoC2);
													String PreSumaCreditoC22=Long.toString(PreSumaCreditoC2);
													long DSA=(PreSumaSAD-PreSumaSAC);
													long DD=(PreSumaSAD-PreSumaSAC);
													long DC=(PreSumaCreditoC1-PreSumaCreditoC2);
													long DDIF=(dif1-dif2);
													String DSA1=Long.toString(DSA);
													String DD1=Long.toString(DD);
													String DC1=Long.toString(DC);
													String DDIF1=Long.toString(DDIF);
													out.print("<tr><td colspan='6'> <br><br> </td></tr>");
													out.print("<tr align='rigth' class='rep'><td colspan='2'class='contpre' ><b>TOTAL DEBITOS</b></td><td align='right'>"+FormatMoneda(PreSumaSAD1)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaDebitoC11)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaCreditoC11)+" &nbsp;</td><td align='right'>"+FormatMoneda(dif11)+" </td></tr>");
													out.print("<tr align='rigth' class='rep'><td colspan='2' class='contpre' ><b>TOTAL CREDITOS</b></td><td align='right'>"+FormatMoneda(PreSumaSAC1)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaDebitoC22)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaCreditoC22)+" &nbsp;</td><td align='right'>"+FormatMoneda(dif22)+" </td></tr>");
													out.print("<tr align='rigth' class='rep'><td colspan='2' class='contpre'><b>DIFERENCIA</b></td><td align='right'>"+FormatMoneda(DSA1)+" &nbsp;</td><td align='right'>"+FormatMoneda(DD1)+" &nbsp;</td><td align='right'>"+FormatMoneda(DC1)+" &nbsp;</td><td align='right'>"+FormatMoneda(DDIF1)+" </td></tr>");
													//out.println("<p align='left'><input type='button' value=' Generar PDF '/></p>");
												}
													
										}else{
											if(cont==4){
												
												String nivel1=valorNivel[1];
												String nivel2=valorNivel[2];
												String nivel3=valorNivel[3];
												String nivel4=valorNivel[4];
												rs=mpr.BuscarDatosBalanceG4(RC1,RC2,AC,MC,nivel1,nivel2,nivel3,nivel4,Suc,"nada");
												
													while(rs.next()){
														cuenta=rs.getString(1);
														if(cuenta.equals("")){
															
														}else{
															rs1=mpr.BuscarCuenta(cuenta);
															while(rs1.next()){
																out.println("<tr align='left' class='rep'><td>"+cuenta+"</td><td>"+rs1.getString(3)+"</td><td align='right'>"+FormatMoneda(rs.getString(4))+" &nbsp;</td><td align='right'>"+FormatMoneda(rs.getString(5))+" &nbsp;</td><td align='right'>"+FormatMoneda(rs.getString(6))+" &nbsp;</td><td align='right'>"+FormatMoneda(rs.getString(7))+"</td>");
															}
															rs1.getStatement().getConnection().close();
														}
													}
													rs.getStatement().getConnection().close();
													if(nivel1.equals("1")){
														rs=mpr.BuscarDatosBalanceG1(RC1,RC2,AC,MC,nivel1,Suc,"nada");
													
														while(rs.next()){
															cuenta=rs.getString(1);
															if(cuenta.equals("")){ 
																
															}else{
																String Naturaleza=rs.getString(8);
																if(Naturaleza.equals("Debito")){
																	String vald=rs.getString(5);
																	long vald1=Long.parseLong(vald); 
																	PreSumaDebitoC1=(vald1+PreSumaDebitoC1);
																	String valc=rs.getString(6);
																	long valc1=Long.parseLong(valc);
																	PreSumaCreditoC1=(valc1+PreSumaCreditoC1);
																	String valsa=rs.getString(4);
																	long valsad=Long.parseLong(valsa);
																	PreSumaSAD=(valsad+PreSumaSAD);
																		
																}else{
																	String valdd=rs.getString(5);
																	long vald2=Long.parseLong(valdd);
																	PreSumaDebitoC2=(vald2+PreSumaDebitoC2);
																	String valcc=rs.getString(6);
																	long valc2=Long.parseLong(valcc);
																	PreSumaCreditoC2=(valc2+PreSumaCreditoC2);
																	String valsa2=rs.getString(4);
																	long valsac=Long.parseLong(valsa2);
																	PreSumaSAC=(valsac+PreSumaSAC);
																}
															}
															
														}rs.getStatement().getConnection().close();
													
														long dif1=PreSumaDebitoC1-PreSumaCreditoC1;
														long dif2=0;
														if(PreSumaDebitoC2<PreSumaCreditoC2){
														 dif2=(-(PreSumaDebitoC2-PreSumaCreditoC2));
														}else{
															dif2=(PreSumaDebitoC2-PreSumaCreditoC2);
														}
														String PreSumaSAD1=Long.toString(PreSumaSAD);
														String PreSumaSAC1=Long.toString(PreSumaSAC);
														String PreSumaDebitoC11=Long.toString(PreSumaDebitoC1);
														String PreSumaCreditoC11=Long.toString(PreSumaCreditoC1);
														String dif11=Long.toString(dif1);
														String dif22=Long.toString(dif2);
														String PreSumaDebitoC22=Long.toString(PreSumaDebitoC2);
														String PreSumaCreditoC22=Long.toString(PreSumaCreditoC2);
														long DSA=(PreSumaSAD-PreSumaSAC);
														long DD=(PreSumaSAD-PreSumaSAC);
														long DC=(PreSumaCreditoC1-PreSumaCreditoC2);
														long DDIF=(dif1-dif2);
														String DSA1=Long.toString(DSA);
														String DD1=Long.toString(DD);
														String DC1=Long.toString(DC);
														String DDIF1=Long.toString(DDIF);
														out.print("<tr><td colspan='6'> <br><br> </td></tr>");
														out.print("<tr align='rigth' class='rep'><td colspan='2'class='contpre' ><b>TOTAL DEBITOS</b></td><td align='right'>"+FormatMoneda(PreSumaSAD1)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaDebitoC11)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaCreditoC11)+" &nbsp;</td><td align='right'>"+FormatMoneda(dif11)+" </td></tr>");
														out.print("<tr align='rigth' class='rep'><td colspan='2' class='contpre' ><b>TOTAL CREDITOS</b></td><td align='right'>"+FormatMoneda(PreSumaSAC1)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaDebitoC22)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaCreditoC22)+" &nbsp;</td><td align='right'>"+FormatMoneda(dif22)+" </td></tr>");
														out.print("<tr align='rigth' class='rep'><td colspan='2' class='contpre'><b>DIFERENCIA</b></td><td align='right'>"+FormatMoneda(DSA1)+" &nbsp;</td><td align='right'>"+FormatMoneda(DD1)+" &nbsp;</td><td align='right'>"+FormatMoneda(DC1)+" &nbsp;</td><td align='right'>"+FormatMoneda(DDIF1)+"</td></tr>");
														//out.println("<p align='left'><input type='button' value=' Generar PDF '/></p>");
													
													}
												
											}else{
												if(cont==5){
													System.out.println("entrando a nivel 5");
													String nivel1=valorNivel[1];
													String nivel2=valorNivel[2];
													String nivel3=valorNivel[3];
													String nivel4=valorNivel[4];
													String nivel5=valorNivel[5];
													rs=mpr.BuscarDatosBalanceG5(RC1,RC2,AC,MC,nivel1,nivel2,nivel3,nivel4,nivel5,Suc,"nada");
													
														while(rs.next()){
															cuenta=rs.getString(1);
															if(cuenta.equals("")){
																
															}else{
																rs1=mpr.BuscarCuenta(cuenta);
																while(rs1.next()){
																	out.println("<tr align='left' class='rep'><td>"+cuenta+"</td><td>"+rs1.getString(3)+"</td><td align='right'>"+FormatMoneda(rs.getString(4))+" &nbsp;</td><td align='right'>"+FormatMoneda(rs.getString(5))+" &nbsp;</td><td align='right'>"+FormatMoneda(rs.getString(6))+" &nbsp;</td><td align='right'>"+FormatMoneda(rs.getString(7))+"</td>");
																}
																rs1.getStatement().getConnection().close();
															}
														}
														rs.getStatement().getConnection().close();
														if(nivel1.equals("1")){
															System.out.println("entrando a validacion");
															rs=mpr.BuscarDatosBalanceG1(RC1,RC2,AC,MC,nivel1,Suc,"nada");
															while(rs.next()){
																cuenta=rs.getString(1);
																if(cuenta.equals("")){
																	
																}else{
																	String Naturaleza=rs.getString(8);
																	if(Naturaleza.equals("Debito")){
																		String vald=rs.getString(5);
																		long vald1=Long.parseLong(vald); 
																		PreSumaDebitoC1=(vald1+PreSumaDebitoC1);
																		String valc=rs.getString(6);
																		long valc1=Long.parseLong(valc);
																		PreSumaCreditoC1=(valc1+PreSumaCreditoC1);
																		String valsa=rs.getString(4);
																		long valsad=Long.parseLong(valsa);
																		PreSumaSAD=(valsad+PreSumaSAD);		
																	}else{
																		String valdd=rs.getString(5);
																		long vald2=Long.parseLong(valdd);
																		PreSumaDebitoC2=(vald2+PreSumaDebitoC2);
																		String valcc=rs.getString(6);
																		long valc2=Long.parseLong(valcc);
																		PreSumaCreditoC2=(valc2+PreSumaCreditoC2);
																		String valsa2=rs.getString(4);
																		long valsac=Long.parseLong(valsa2);
																		PreSumaSAC=(valsac+PreSumaSAC);
																	}
																}
																
															}rs.getStatement().getConnection().close();
															long dif1=0;
															dif1=PreSumaDebitoC1-PreSumaCreditoC1;
															long dif2=0;
															if(PreSumaDebitoC2<PreSumaCreditoC2){
															 dif2=(-(PreSumaDebitoC2-PreSumaCreditoC2));
															}else{
																dif2=(PreSumaDebitoC2-PreSumaCreditoC2);
															}
															String PreSumaSAD1=Long.toString(PreSumaSAD);
															String PreSumaSAC1=Long.toString(PreSumaSAC);
															String PreSumaDebitoC11=Long.toString(PreSumaDebitoC1);
															String PreSumaCreditoC11=Long.toString(PreSumaCreditoC1);
															String dif11=Long.toString(dif1);
															String dif22=Long.toString(dif2);
															String PreSumaDebitoC22=Long.toString(PreSumaDebitoC2);
															String PreSumaCreditoC22=Long.toString(PreSumaCreditoC2);
															long DSA=(PreSumaSAD-PreSumaSAC);
															long DD=(PreSumaSAD-PreSumaSAC);
															long DC=(PreSumaCreditoC1-PreSumaCreditoC2);
															long DDIF=(dif1-dif2);
															String DSA1=Long.toString(DSA);
															String DD1=Long.toString(DD);
															String DC1=Long.toString(DC);
															String DDIF1=Long.toString(DDIF);
															out.print("<tr align='rigth' class='rep'><td colspan='2'class='contpre' ><b>TOTAL DEBITOS</b></td><td align='right'>"+FormatMoneda(PreSumaSAD1)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaDebitoC11)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaCreditoC11)+" &nbsp;</td><td align='right'>"+FormatMoneda(dif11)+" </td></tr>");
															out.print("<tr align='rigth' class='rep'><td colspan='2' class='contpre' ><b>TOTAL CREDITOS</b></td><td align='right'>"+FormatMoneda(PreSumaSAC1)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaDebitoC22)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaCreditoC22)+" &nbsp;</td><td align='right'>"+FormatMoneda(dif22)+" </td></tr>");
															out.print("<tr align='rigth' class='rep'><td colspan='2' class='contpre'><b>DIFERENCIA</b></td><td align='right'>"+FormatMoneda(DSA1)+" &nbsp;</td><td align='right'>"+FormatMoneda(DD1)+" &nbsp;</td><td align='right'>"+FormatMoneda(DC1)+" &nbsp;</td><td align='right'>"+FormatMoneda(DDIF1)+" </td></tr>");
															//out.println("<p align='left'><input type='button' value=' Generar PDF '/></p>");
															
														}
													
													}
												}
											}
										}
									}
								}rs4.getStatement().getConnection().close();
				
						
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
			
			
		if(va.equals("ReporteBalancePruebaCcosto")){
			String RC1=req.getParameter("RC1");
			String RC2=req.getParameter("RC2");
			String MC=req.getParameter("MC");
			String AC=req.getParameter("AC");
			String ccosto=req.getParameter("ccosto");
			if(RC2.equals("TODAS")){
				
			}else{
				/*int rc11=Integer.parseInt(RC1);
				int rc22=Integer.parseInt(RC2);
				if(RC1.equals(RC2)){
				}else{*/
				RC2=RC2+"9";
				/*}*/
			}
			System.out.println(" valor de RC2"+RC2);
			String nivelSeleccionado=req.getParameter("nivelSeleccionado");
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
			java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());
			String[] detnivelSeleccionado = nivelSeleccionado.split("_");
			
			out.print("<br><br><table width='80%' >");
			rs=mpr.BuscarEmpresa();
			try {
				while(rs.next()){
				out.println("<tr class='contpre'><td align='center' colspan='6'>"+rs.getString(1)+"</td></tr>");
				out.println("<tr class='contpre'><td align='center' colspan='6'>"+rs.getString(2)+"<br></td></tr>");
				
				}rs.getStatement().getConnection().close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			out.print("<tr class='contpre'><td></td><td  align='left' > BALANCE DE PRUEBA POR CENTRO DE COSTO &nbsp; &nbsp;</td><td align='left'>Fecha :</td><td colspan='2' align='right' >"+Fecha+"</td></tr>");
			out.print("<tr class='contpre'><td></td><td  align='left' >CORRESPONDIENTE AL PERIODO "+MC+" DEL A�O "+AC+" &nbsp; </td><td  align='left'>Hora : </td><td colspan='2' align='right'>"+Hora+"</td></tr>");
			out.print("<tr><td colspan='6'><hr></td>");
			out.print("<tr class='rep' ><b><td >&nbsp; CUENTA &nbsp;</td><td>&nbsp; NOMBRE DE LA CUENTA &nbsp;</td><td>&nbsp; SALDO ANTERIOR &nbsp;</td><td>&nbsp; DEBITOS &nbsp;</td><td>&nbsp; CREDITOS &nbsp;</td><td>&nbsp; SALDO NUEVO &nbsp;</td></b></tr>");
			out.print("<tr><td colspan='6'><hr></td>");
			String cuenta="";
			String valorNivel [] = new String[6];
			int cont=0;
			for (int i = 0; i < detnivelSeleccionado.length; i++) {
				if(detnivelSeleccionado[i].equals("1")){
					valorNivel[i+1]="1";
				}else{
					if(detnivelSeleccionado[i].equals("2")){
						valorNivel[i+1]="2";
					}else{
						if(detnivelSeleccionado[i].equals("3")){
							valorNivel[i+1]="4";
						}else{
							if(detnivelSeleccionado[i].equals("4")){
								valorNivel[i+1]="6";
							}else{
								if(detnivelSeleccionado[i].equals("5")){						
								valorNivel[i+1]="8";
								}
							}
						}
				}
				
			}
				cont=cont+1;
			}
		
			long PreSumaDebitoC1=0;
			long PreSumaCreditoC1=0;
			long PreSumaDebitoC2=0;
			long PreSumaCreditoC2=0;
			long PreSumaSAD=0;
			long PreSumaSAC=0;
			if(cont>0){
				//String nivel=valorNivel[1];
				rs4=mpr.BuscarNombreCentroCosto(ccosto);
				try {
					while(rs4.next()){
						out.print("<tr class='contpre'><td><br> <b>CENTRO DE COSTO No: </b> </td><td><br>"+rs4.getString(2)+"</td><tr>");
						ccosto=rs4.getString(1);
						PreSumaDebitoC1=0;
						PreSumaCreditoC1=0;
						PreSumaDebitoC2=0;
						PreSumaCreditoC2=0;
						PreSumaSAD=0;
						PreSumaSAC=0;
								System.out.println("Valor de cont "+cont);
								if(cont==1){
										String nivel=valorNivel[1];
										System.out.println("valor de nivel"+nivel);
										rs=mpr.BuscarDatosBalanceG1(RC1,RC2,AC,MC,nivel,"nada",ccosto);								
										while(rs.next()){
											cuenta=rs.getString(1);
												if(cuenta.equals("")){
												
												}else{
													rs1=mpr.BuscarCuenta(cuenta);
													while(rs1.next()){
														out.println("<tr align='left' class='rep'><td>"+cuenta+"</td><td>"+rs1.getString(3)+"</td><td align='right'>"+FormatMoneda(rs.getString(4))+" &nbsp;</td><td align='right'>"+FormatMoneda(rs.getString(5))+" &nbsp;</td><td align='right'>"+FormatMoneda(rs.getString(6))+" &nbsp;</td><td align='right'>"+FormatMoneda(rs.getString(7))+"</td></tr>");
														String Naturaleza=rs.getString(8);
															if(Naturaleza.equals("Debito")){
																	String vald=rs.getString(5);
																	long vald1=Long.parseLong(vald); 
																	PreSumaDebitoC1=(vald1+PreSumaDebitoC1);
																	String valc=rs.getString(6);
																	long valc1=Long.parseLong(valc);
																	PreSumaCreditoC1=(valc1+PreSumaCreditoC1);
																	String valsa=rs.getString(4);
																	long valsad=Long.parseLong(valsa);
																	PreSumaSAD=(valsad+PreSumaSAD);
															}else{
																	String valdd=rs.getString(5);
																	long vald2=Long.parseLong(valdd);
																	PreSumaDebitoC2=(vald2+PreSumaDebitoC2);
																	String valcc=rs.getString(6);
																	long valc2=Long.parseLong(valcc);
																	PreSumaCreditoC2=(valc2+PreSumaCreditoC2);
																	String valsa2=rs.getString(4);
																	long valsac=Long.parseLong(valsa2);
																	PreSumaSAC=(valsac+PreSumaSAC);
														
															}
													}rs1.getStatement().getConnection().close();
												}
										}rs.getStatement().getConnection().close();
										long dif1=PreSumaDebitoC1-PreSumaCreditoC1;
										long dif2=0;
										if(PreSumaDebitoC2<PreSumaCreditoC2){
											dif2=(-(PreSumaDebitoC2-PreSumaCreditoC2));
										}else{
											dif2=(PreSumaDebitoC2-PreSumaCreditoC2);
										}
										
										/*if((RC1.equals("todas"))&&(RC2.equals("todas"))){
											sql=("SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, cdasc.saldo_anterior,SUM(cdasc.debito),SUM(cdasc.credito),cdasc.saldo_nuevo, cc.NaturalezaCuenta, e.* FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc,empresa e WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"' GROUP BY cuenta  ORDER BY cuenta ASC ");
										}else{
											sql=("SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel+"))as cuenta,cc.CodigoCuenta, cc.NombreCuenta, cdasc.saldo_anterior,SUM(cdasc.debito),SUM(cdasc.credito),cdasc.saldo_nuevo, cc.NaturalezaCuenta, e.* FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, empresa e WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'and cc.codigo between '"+RC1+"' and '"+RC2+"' GROUP BY cuenta  ORDER BY cuenta ASC");
										}*/
										int ident=0;
										String PreSumaSAD1=Long.toString(PreSumaSAD);
										String PreSumaSAC1=Long.toString(PreSumaSAC);
										String PreSumaDebitoC11=Long.toString(PreSumaDebitoC1);
										String PreSumaCreditoC11=Long.toString(PreSumaCreditoC1);
										String dif11=Long.toString(dif1);
										String dif22=Long.toString(dif2);
										String PreSumaDebitoC22=Long.toString(PreSumaDebitoC2);
										String PreSumaCreditoC22=Long.toString(PreSumaCreditoC2);
										long DSA=(PreSumaSAD-PreSumaSAC);
										long DD=(PreSumaSAD-PreSumaSAC);
										long DC=(PreSumaCreditoC1-PreSumaCreditoC2);
										long DDIF=(dif1-dif2);
										String DSA1=Long.toString(DSA);
										String DD1=Long.toString(DD);
										String DC1=Long.toString(DC);
										String DDIF1=Long.toString(DDIF);
										if(nivel.equals("1")){
											out.print("<tr><td colspan='6'> <br><br> </td></tr>");
											out.print("<tr align='rigth' class='rep'><td colspan='2'class='contpre' ><b>TOTAL DEBITOS</b></td><td align='right'>"+FormatMoneda(PreSumaSAD1)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaDebitoC11)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaCreditoC11)+" &nbsp;</td><td align='right'>"+FormatMoneda(dif11)+" </td></tr>");
											out.print("<tr align='rigth' class='rep'><td colspan='2' class='contpre' ><b>TOTAL CREDITOS</b></td><td align='right'>"+FormatMoneda(PreSumaSAC1)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaDebitoC22)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaCreditoC22)+" &nbsp;</td><td align='right'>"+FormatMoneda(dif22)+" </td></tr>");
											out.print("<tr align='rigth' class='rep'><td colspan='2' class='contpre'><b>DIFERENCIA</b></td><td align='right'>"+FormatMoneda(DSA1)+" &nbsp;</td><td align='right'>"+FormatMoneda(DD1)+" &nbsp;</td><td align='right'>"+FormatMoneda(DC1)+" &nbsp;</td><td align='right'>"+FormatMoneda(DDIF1)+"</td></tr>");
											ident=1;
										}
										
											//out.println("<tr align='left'><td><p align='left'><input type='button' value=' Generar PDF ' onclick='verrep1("+RC1+","+RC2+","+AC+","+MC+","+nivel+","+PreSumaSAD+","+PreSumaDebitoC1+","+PreSumaCreditoC1+","+PreSumaDebitoC2+","+PreSumaCreditoC2+","+PreSumaSAC+","+dif1+","+dif2+","+ident+","+Fecha+","+Hora+")'/></p></td></tr>");
									
								}else{
									if(cont==2){
										
										String nivel1=valorNivel[1];
										String nivel2=valorNivel[2];
										rs=mpr.BuscarDatosBalanceG2(RC1,RC2,AC,MC,nivel1,nivel2,"nada",ccosto);
										
											while(rs.next()){
												cuenta=rs.getString(1);
												if(cuenta.equals("")){
													
												}else{
													System.out.println("entrando a validacion en cont 2");
													rs1=mpr.BuscarCuenta(cuenta);
													while(rs1.next()){
														out.println("<tr align='left' class='rep'><td>"+cuenta+"</td><td>"+rs1.getString(3)+"</td><td align='right'>"+FormatMoneda(rs.getString(4))+" &nbsp;</td><td align='right'>"+FormatMoneda(rs.getString(5))+" &nbsp;</td><td align='right'>"+FormatMoneda(rs.getString(6))+" &nbsp;</td><td align='right'>"+FormatMoneda(rs.getString(7))+"</td></tr>");
													}
												}rs1.getStatement().getConnection().close();
											}rs.getStatement().getConnection().close();
											if(nivel1.equals("1")){
												rs=mpr.BuscarDatosBalanceG1(RC1,RC2,AC,MC,nivel1,"nada",ccosto);
												while(rs.next()){
													cuenta=rs.getString(1);
													if(cuenta.equals("")){ 
														
													}else{
														String Naturaleza=rs.getString(8);
														if(Naturaleza.equals("Debito")){
															String vald=rs.getString(5);
															long vald1=Long.parseLong(vald); 
															PreSumaDebitoC1=(vald1+PreSumaDebitoC1);
															String valc=rs.getString(6);
															long valc1=Long.parseLong(valc);
															PreSumaCreditoC1=(valc1+PreSumaCreditoC1);
															String valsa=rs.getString(4);
															long valsad=Long.parseLong(valsa);
															PreSumaSAD=(valsad+PreSumaSAD);
																
														}else{
															String valdd=rs.getString(5);
															long vald2=Long.parseLong(valdd);
															PreSumaDebitoC2=(vald2+PreSumaDebitoC2);
															String valcc=rs.getString(6);
															long valc2=Long.parseLong(valcc);
															PreSumaCreditoC2=(valc2+PreSumaCreditoC2);
															String valsa2=rs.getString(4);
															long valsac=Long.parseLong(valsa2);
															PreSumaSAC=(valsac+PreSumaSAC);
														}
													}
													
												}rs.getStatement().getConnection().close();
											
												long dif1=PreSumaDebitoC1-PreSumaCreditoC1;
												long dif2=0;
												if(PreSumaDebitoC2<PreSumaCreditoC2){
													dif2=(-(PreSumaDebitoC2-PreSumaCreditoC2));
												}else{
													dif2=(PreSumaDebitoC2-PreSumaCreditoC2);
												}
											
												String PreSumaSAD1=Long.toString(PreSumaSAD);
												String PreSumaSAC1=Long.toString(PreSumaSAC);
												String PreSumaDebitoC11=Long.toString(PreSumaDebitoC1);
												String PreSumaCreditoC11=Long.toString(PreSumaCreditoC1);
												String dif11=Long.toString(dif1);
												String dif22=Long.toString(dif2);
												String PreSumaDebitoC22=Long.toString(PreSumaDebitoC2);
												String PreSumaCreditoC22=Long.toString(PreSumaCreditoC2);
												long DSA=(PreSumaSAD-PreSumaSAC);
												long DD=(PreSumaSAD-PreSumaSAC);
												long DC=(PreSumaCreditoC1-PreSumaCreditoC2);
												long DDIF=(dif1-dif2);
												String DSA1=Long.toString(DSA);
												String DD1=Long.toString(DD);
												String DC1=Long.toString(DC);
												String DDIF1=Long.toString(DDIF);
												out.print("<tr><td colspan='6'> <br><br> </td></tr>");
												out.print("<tr align='rigth' class='rep'><td colspan='2'class='contpre' ><b>TOTAL DEBITOS</b></td><td align='right'>"+FormatMoneda(PreSumaSAD1)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaDebitoC11)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaCreditoC11)+" &nbsp;</td><td align='right'>"+FormatMoneda(dif11)+"</td></tr>");
												out.print("<tr align='rigth' class='rep'><td colspan='2' class='contpre' ><b>TOTAL CREDITOS</b></td><td align='right'>"+FormatMoneda(PreSumaSAC1)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaDebitoC22)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaCreditoC22)+" &nbsp;</td><td align='right'>"+FormatMoneda(dif22)+" </td></tr>");
												out.print("<tr align='rigth' class='rep'><td colspan='2' class='contpre'><b>DIFERENCIA</b></td><td align='right'>"+FormatMoneda(DSA1)+" &nbsp;</td><td align='right'>"+FormatMoneda(DD1)+" &nbsp;</td><td align='right'>"+FormatMoneda(DC1)+" &nbsp;</td><td align='right'>"+FormatMoneda(DDIF1)+"</td></tr>");
												//out.println("<p align='left'><input type='button' value=' Generar PDF '/></p>");
											}

										
									}else{
										if(cont==3){
											
											String nivel1=valorNivel[1];
											String nivel2=valorNivel[2];
											String nivel3=valorNivel[3];
											rs=mpr.BuscarDatosBalanceG3(RC1,RC2,AC,MC,nivel1,nivel2,nivel3,"nada",ccosto);
											
												while(rs.next()){
													cuenta=rs.getString(1);
													if(cuenta.equals("")){
														
													}else{
														rs1=mpr.BuscarCuenta(cuenta);
														while(rs1.next()){
															out.println("<tr align='left' class='rep'><td>"+cuenta+"</td><td>"+rs1.getString(3)+"</td><td align='right'>"+FormatMoneda(rs.getString(4))+" &nbsp;</td><td align='right'>"+FormatMoneda(rs.getString(5))+" &nbsp;</td><td align='right'>"+FormatMoneda(rs.getString(6))+" &nbsp;</td><td align='right'>"+FormatMoneda(rs.getString(7))+"</td>");
														}
														rs1.getStatement().getConnection().close();
													}
												}
												rs.getStatement().getConnection().close();
												if(nivel1.equals("1")){
													rs=mpr.BuscarDatosBalanceG1(RC1,RC2,AC,MC,nivel1,"nada",ccosto);
													while(rs.next()){
														cuenta=rs.getString(1);
														if(cuenta.equals("")){ 
															
														}else{
																String Naturaleza=rs.getString(8);
																if(Naturaleza.equals("Debito")){
																	String vald=rs.getString(5);
																	long vald1=Long.parseLong(vald); 
																	PreSumaDebitoC1=(vald1+PreSumaDebitoC1);
																	String valc=rs.getString(6);
																	long valc1=Long.parseLong(valc);
																	PreSumaCreditoC1=(valc1+PreSumaCreditoC1);
																	String valsa=rs.getString(4);
																	long valsad=Long.parseLong(valsa);
																	PreSumaSAD=(valsad+PreSumaSAD);		
																}else{
																	String valdd=rs.getString(5);
																	long vald2=Long.parseLong(valdd);
																	PreSumaDebitoC2=(vald2+PreSumaDebitoC2);
																	String valcc=rs.getString(6);
																	long valc2=Long.parseLong(valcc);
																	PreSumaCreditoC2=(valc2+PreSumaCreditoC2);
																	String valsa2=rs.getString(4);
																	long valsac=Long.parseLong(valsa2);
																	PreSumaSAC=(valsac+PreSumaSAC);
																}
														}
															
													} rs.getStatement().getConnection().close();
												
													long dif1=PreSumaDebitoC1-PreSumaCreditoC1;
													long dif2=0;
													if(PreSumaDebitoC2<PreSumaCreditoC2){
														dif2=(-(PreSumaDebitoC2-PreSumaCreditoC2));
													}else{
														dif2=(PreSumaDebitoC2-PreSumaCreditoC2);
													}
													String PreSumaSAD1=Long.toString(PreSumaSAD);
													String PreSumaSAC1=Long.toString(PreSumaSAC);
													String PreSumaDebitoC11=Long.toString(PreSumaDebitoC1);
													String PreSumaCreditoC11=Long.toString(PreSumaCreditoC1);
													String dif11=Long.toString(dif1);
													String dif22=Long.toString(dif2);
													String PreSumaDebitoC22=Long.toString(PreSumaDebitoC2);
													String PreSumaCreditoC22=Long.toString(PreSumaCreditoC2);
													long DSA=(PreSumaSAD-PreSumaSAC);
													long DD=(PreSumaSAD-PreSumaSAC);
													long DC=(PreSumaCreditoC1-PreSumaCreditoC2);
													long DDIF=(dif1-dif2);
													String DSA1=Long.toString(DSA);
													String DD1=Long.toString(DD);
													String DC1=Long.toString(DC);
													String DDIF1=Long.toString(DDIF);
													out.print("<tr><td colspan='6'> <br><br> </td></tr>");
													out.print("<tr align='rigth' class='rep'><td colspan='2'class='contpre' ><b>TOTAL DEBITOS</b></td><td align='right'>"+FormatMoneda(PreSumaSAD1)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaDebitoC11)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaCreditoC11)+" &nbsp;</td><td align='right'>"+FormatMoneda(dif11)+" </td></tr>");
													out.print("<tr align='rigth' class='rep'><td colspan='2' class='contpre' ><b>TOTAL CREDITOS</b></td><td align='right'>"+FormatMoneda(PreSumaSAC1)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaDebitoC22)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaCreditoC22)+" &nbsp;</td><td align='right'>"+FormatMoneda(dif22)+" </td></tr>");
													out.print("<tr align='rigth' class='rep'><td colspan='2' class='contpre'><b>DIFERENCIA</b></td><td align='right'>"+FormatMoneda(DSA1)+" &nbsp;</td><td align='right'>"+FormatMoneda(DD1)+" &nbsp;</td><td align='right'>"+FormatMoneda(DC1)+" &nbsp;</td><td align='right'>"+FormatMoneda(DDIF1)+" </td></tr>");
													//out.println("<p align='left'><input type='button' value=' Generar PDF '/></p>");
												}
													
										}else{
											if(cont==4){
												
												String nivel1=valorNivel[1];
												String nivel2=valorNivel[2];
												String nivel3=valorNivel[3];
												String nivel4=valorNivel[4];
												rs=mpr.BuscarDatosBalanceG4(RC1,RC2,AC,MC,nivel1,nivel2,nivel3,nivel4,"nada",ccosto);
												
													while(rs.next()){
														cuenta=rs.getString(1);
														if(cuenta.equals("")){
															
														}else{
															rs1=mpr.BuscarCuenta(cuenta);
															while(rs1.next()){
																out.println("<tr align='left' class='rep'><td>"+cuenta+"</td><td>"+rs1.getString(3)+"</td><td align='right'>"+FormatMoneda(rs.getString(4))+" &nbsp;</td><td align='right'>"+FormatMoneda(rs.getString(5))+" &nbsp;</td><td align='right'>"+FormatMoneda(rs.getString(6))+" &nbsp;</td><td align='right'>"+FormatMoneda(rs.getString(7))+"</td>");
															}
															rs1.getStatement().getConnection().close();
														}
													}
													rs.getStatement().getConnection().close();
													if(nivel1.equals("1")){
														rs=mpr.BuscarDatosBalanceG1(RC1,RC2,AC,MC,nivel1,"nada",ccosto);
													
														while(rs.next()){
															cuenta=rs.getString(1);
															if(cuenta.equals("")){ 
																
															}else{
																String Naturaleza=rs.getString(8);
																if(Naturaleza.equals("Debito")){
																	String vald=rs.getString(5);
																	long vald1=Long.parseLong(vald); 
																	PreSumaDebitoC1=(vald1+PreSumaDebitoC1);
																	String valc=rs.getString(6);
																	long valc1=Long.parseLong(valc);
																	PreSumaCreditoC1=(valc1+PreSumaCreditoC1);
																	String valsa=rs.getString(4);
																	long valsad=Long.parseLong(valsa);
																	PreSumaSAD=(valsad+PreSumaSAD);
																		
																}else{
																	String valdd=rs.getString(5);
																	long vald2=Long.parseLong(valdd);
																	PreSumaDebitoC2=(vald2+PreSumaDebitoC2);
																	String valcc=rs.getString(6);
																	long valc2=Long.parseLong(valcc);
																	PreSumaCreditoC2=(valc2+PreSumaCreditoC2);
																	String valsa2=rs.getString(4);
																	long valsac=Long.parseLong(valsa2);
																	PreSumaSAC=(valsac+PreSumaSAC);
																}
															}
															
														}rs.getStatement().getConnection().close();
													
														long dif1=PreSumaDebitoC1-PreSumaCreditoC1;
														long dif2=0;
														if(PreSumaDebitoC2<PreSumaCreditoC2){
														 dif2=(-(PreSumaDebitoC2-PreSumaCreditoC2));
														}else{
															dif2=(PreSumaDebitoC2-PreSumaCreditoC2);
														}
														String PreSumaSAD1=Long.toString(PreSumaSAD);
														String PreSumaSAC1=Long.toString(PreSumaSAC);
														String PreSumaDebitoC11=Long.toString(PreSumaDebitoC1);
														String PreSumaCreditoC11=Long.toString(PreSumaCreditoC1);
														String dif11=Long.toString(dif1);
														String dif22=Long.toString(dif2);
														String PreSumaDebitoC22=Long.toString(PreSumaDebitoC2);
														String PreSumaCreditoC22=Long.toString(PreSumaCreditoC2);
														long DSA=(PreSumaSAD-PreSumaSAC);
														long DD=(PreSumaSAD-PreSumaSAC);
														long DC=(PreSumaCreditoC1-PreSumaCreditoC2);
														long DDIF=(dif1-dif2);
														String DSA1=Long.toString(DSA);
														String DD1=Long.toString(DD);
														String DC1=Long.toString(DC);
														String DDIF1=Long.toString(DDIF);
														out.print("<tr><td colspan='6'> <br><br> </td></tr>");
														out.print("<tr align='rigth' class='rep'><td colspan='2'class='contpre' ><b>TOTAL DEBITOS</b></td><td align='right'>"+FormatMoneda(PreSumaSAD1)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaDebitoC11)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaCreditoC11)+" &nbsp;</td><td align='right'>"+FormatMoneda(dif11)+" </td></tr>");
														out.print("<tr align='rigth' class='rep'><td colspan='2' class='contpre' ><b>TOTAL CREDITOS</b></td><td align='right'>"+FormatMoneda(PreSumaSAC1)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaDebitoC22)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaCreditoC22)+" &nbsp;</td><td align='right'>"+FormatMoneda(dif22)+" </td></tr>");
														out.print("<tr align='rigth' class='rep'><td colspan='2' class='contpre'><b>DIFERENCIA</b></td><td align='right'>"+FormatMoneda(DSA1)+" &nbsp;</td><td align='right'>"+FormatMoneda(DD1)+" &nbsp;</td><td align='right'>"+FormatMoneda(DC1)+" &nbsp;</td><td align='right'>"+FormatMoneda(DDIF1)+"</td></tr>");
														//out.println("<p align='left'><input type='button' value=' Generar PDF '/></p>");
													
													}
												
											}else{
												if(cont==5){
													System.out.println("entrando a nivel 5");
													String nivel1=valorNivel[1];
													String nivel2=valorNivel[2];
													String nivel3=valorNivel[3];
													String nivel4=valorNivel[4];
													String nivel5=valorNivel[5];
													rs=mpr.BuscarDatosBalanceG5(RC1,RC2,AC,MC,nivel1,nivel2,nivel3,nivel4,nivel5,"nada",ccosto);
													
														while(rs.next()){
															cuenta=rs.getString(1);
															if(cuenta.equals("")){
																
															}else{
																rs1=mpr.BuscarCuenta(cuenta);
																while(rs1.next()){
																	out.println("<tr align='left' class='rep'><td>"+cuenta+"</td><td>"+rs1.getString(3)+"</td><td align='right'>"+FormatMoneda(rs.getString(4))+" &nbsp;</td><td align='right'>"+FormatMoneda(rs.getString(5))+" &nbsp;</td><td align='right'>"+FormatMoneda(rs.getString(6))+" &nbsp;</td><td align='right'>"+FormatMoneda(rs.getString(7))+"</td>");
																}
																rs1.getStatement().getConnection().close();
															}
														}
														rs.getStatement().getConnection().close();
														if(nivel1.equals("1")){
															System.out.println("entrando a validacion");
															rs=mpr.BuscarDatosBalanceG1(RC1,RC2,AC,MC,nivel1,"nada",ccosto);
															while(rs.next()){
																cuenta=rs.getString(1);
																if(cuenta.equals("")){
																	
																}else{
																	String Naturaleza=rs.getString(8);
																	if(Naturaleza.equals("Debito")){
																		String vald=rs.getString(5);
																		long vald1=Long.parseLong(vald); 
																		PreSumaDebitoC1=(vald1+PreSumaDebitoC1);
																		String valc=rs.getString(6);
																		long valc1=Long.parseLong(valc);
																		PreSumaCreditoC1=(valc1+PreSumaCreditoC1);
																		String valsa=rs.getString(4);
																		long valsad=Long.parseLong(valsa);
																		PreSumaSAD=(valsad+PreSumaSAD);		
																	}else{
																		String valdd=rs.getString(5);
																		long vald2=Long.parseLong(valdd);
																		PreSumaDebitoC2=(vald2+PreSumaDebitoC2);
																		String valcc=rs.getString(6);
																		long valc2=Long.parseLong(valcc);
																		PreSumaCreditoC2=(valc2+PreSumaCreditoC2);
																		String valsa2=rs.getString(4);
																		long valsac=Long.parseLong(valsa2);
																		PreSumaSAC=(valsac+PreSumaSAC);
																	}
																}
																
															}rs.getStatement().getConnection().close();
															long dif1=0;
															dif1=PreSumaDebitoC1-PreSumaCreditoC1;
															long dif2=0;
															if(PreSumaDebitoC2<PreSumaCreditoC2){
															 dif2=(-(PreSumaDebitoC2-PreSumaCreditoC2));
															}else{
																dif2=(PreSumaDebitoC2-PreSumaCreditoC2);
															}
															String PreSumaSAD1=Long.toString(PreSumaSAD);
															String PreSumaSAC1=Long.toString(PreSumaSAC);
															String PreSumaDebitoC11=Long.toString(PreSumaDebitoC1);
															String PreSumaCreditoC11=Long.toString(PreSumaCreditoC1);
															String dif11=Long.toString(dif1);
															String dif22=Long.toString(dif2);
															String PreSumaDebitoC22=Long.toString(PreSumaDebitoC2);
															String PreSumaCreditoC22=Long.toString(PreSumaCreditoC2);
															long DSA=(PreSumaSAD-PreSumaSAC);
															long DD=(PreSumaSAD-PreSumaSAC);
															long DC=(PreSumaCreditoC1-PreSumaCreditoC2);
															long DDIF=(dif1-dif2);
															String DSA1=Long.toString(DSA);
															String DD1=Long.toString(DD);
															String DC1=Long.toString(DC);
															String DDIF1=Long.toString(DDIF);
															out.print("<tr align='rigth' class='rep'><td colspan='2'class='contpre' ><b>TOTAL DEBITOS</b></td><td align='right'>"+FormatMoneda(PreSumaSAD1)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaDebitoC11)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaCreditoC11)+" &nbsp;</td><td align='right'>"+FormatMoneda(dif11)+" </td></tr>");
															out.print("<tr align='rigth' class='rep'><td colspan='2' class='contpre' ><b>TOTAL CREDITOS</b></td><td align='right'>"+FormatMoneda(PreSumaSAC1)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaDebitoC22)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaCreditoC22)+" &nbsp;</td><td align='right'>"+FormatMoneda(dif22)+" </td></tr>");
															out.print("<tr align='rigth' class='rep'><td colspan='2' class='contpre'><b>DIFERENCIA</b></td><td align='right'>"+FormatMoneda(DSA1)+" &nbsp;</td><td align='right'>"+FormatMoneda(DD1)+" &nbsp;</td><td align='right'>"+FormatMoneda(DC1)+" &nbsp;</td><td align='right'>"+FormatMoneda(DDIF1)+" </td></tr>");
															//out.println("<p align='left'><input type='button' value=' Generar PDF '/></p>");
															
														}
													
													}
												}
											}
										}
									}
								}rs4.getStatement().getConnection().close();
				
						
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
			
		
		if(va.equals("ReporteBalancePruebaSucCcosto")){
			String RC1=req.getParameter("RC1");
			String RC2=req.getParameter("RC2");
			String MC=req.getParameter("MC");
			String AC=req.getParameter("AC");
			String Suc=req.getParameter("Suc");
			String ccosto=req.getParameter("ccosto");
			if(RC2.equals("TODAS")){
				
			}else{
				/*int rc11=Integer.parseInt(RC1);
				int rc22=Integer.parseInt(RC2);
				if(RC1.equals(RC2)){
				}else{*/
				RC2=RC2+"9";
				/*}*/
			}
			System.out.println(" valor de RC2"+RC2);
			String nivelSeleccionado=req.getParameter("nivelSeleccionado");
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
			java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());
			String[] detnivelSeleccionado = nivelSeleccionado.split("_");
			
			out.print("<br><br><table width='80%' >");
			
			rs=mpr.BuscarEmpresa();
			try {
				while(rs.next()){
				out.println("<tr class='contpre'><td align='center' colspan='6'>"+rs.getString(1)+"</td></tr>");
				out.println("<tr class='contpre'><td align='center' colspan='6'>"+rs.getString(2)+"<br></td></tr>");
				
				}rs.getStatement().getConnection().close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			out.print("<tr class='contpre'><td></td><td  align='left' > BALANCE DE PRUEBA POR SUCURSAL Y CENTRO DE COSTO &nbsp; &nbsp;</td><td align='left'>Fecha de Generacion:</td><td >"+Fecha+"</td></tr>");
			out.print("<tr class='contpre'><td></td><td  align='left' >CORRESPONDIENTE AL PERIODO "+MC+" DEL A�O "+AC+" &nbsp; &nbsp;</td><td  align='left'>Hora de Generacion:</td><td colspan='2'>"+Hora+"</td></tr>");
			out.print("<tr><td colspan='6'><hr></td>");
			out.print("<tr class='rep' ><b><td >&nbsp; CUENTA &nbsp;</td><td>&nbsp; NOMBRE DE LA CUENTA &nbsp;</td><td>&nbsp; SALDO ANTERIOR &nbsp;</td><td>&nbsp; DEBITOS &nbsp;</td><td>&nbsp; CREDITOS &nbsp;</td><td>&nbsp; SALDO NUEVO &nbsp;</td></b></tr>");
			out.print("<tr><td colspan='6'><hr></td>");
			String cuenta="";
			String valorNivel [] = new String[6];
			int cont=0;
			for (int i = 0; i < detnivelSeleccionado.length; i++) {
				if(detnivelSeleccionado[i].equals("1")){
					valorNivel[i+1]="1";
				}else{
					if(detnivelSeleccionado[i].equals("2")){
						valorNivel[i+1]="2";
					}else{
						if(detnivelSeleccionado[i].equals("3")){
							valorNivel[i+1]="4";
						}else{
							if(detnivelSeleccionado[i].equals("4")){
								valorNivel[i+1]="6";
							}else{
								if(detnivelSeleccionado[i].equals("5")){						
								valorNivel[i+1]="8";
								}
							}
						}
				}
				
			}
				cont=cont+1;
			}
		
			long PreSumaDebitoC1=0;
			long PreSumaCreditoC1=0;
			long PreSumaDebitoC2=0;
			long PreSumaCreditoC2=0;
			long PreSumaSAD=0;
			long PreSumaSAC=0;
			if(cont>0){
				//String nivel=valorNivel[1];
				rs4=mpr.BuscarNombreSuc(Suc);
				try {
					while(rs4.next()){
						out.print("<tr class='contpre'><td><br> <b>SUCURSAL No: </b> </td><td><br>"+rs4.getString(2)+"</td><tr>");
						rs3=mpr.BuscarNombreCentroCosto(ccosto);
						Suc=rs4.getString(1);
						while(rs3.next()){
							out.print("<tr class='contpre'><td><br> <b>CENTRO DE COSTO No: </b> </td><td><br>"+rs3.getString(2)+"</td><tr>");
						String centcost=rs3.getString(1);
						
						PreSumaDebitoC1=0;
						PreSumaCreditoC1=0;
						PreSumaDebitoC2=0;
						PreSumaCreditoC2=0;
						PreSumaSAD=0;
						PreSumaSAC=0;
								System.out.println("Valor de cont "+cont);
								if(cont==1){
										String nivel=valorNivel[1];
										System.out.println("valor de nivel"+nivel);
										rs=mpr.BuscarDatosBalanceG1(RC1,RC2,AC,MC,nivel,Suc,centcost);								
										while(rs.next()){
											cuenta=rs.getString(1);
												if(cuenta.equals("")){
												
												}else{
													rs1=mpr.BuscarCuenta(cuenta);
													while(rs1.next()){
														out.println("<tr align='left' class='rep'><td>"+cuenta+"</td><td>"+rs1.getString(3)+"</td><td align='right'>"+FormatMoneda(rs.getString(4))+" &nbsp;</td><td align='right'>"+FormatMoneda(rs.getString(5))+" &nbsp;</td><td align='right'>"+FormatMoneda(rs.getString(6))+" &nbsp;</td><td align='right'>"+FormatMoneda(rs.getString(7))+"</td></tr>");
														String Naturaleza=rs.getString(8);
															if(Naturaleza.equals("Debito")){
																	String vald=rs.getString(5);
																	long vald1=Long.parseLong(vald); 
																	PreSumaDebitoC1=(vald1+PreSumaDebitoC1);
																	String valc=rs.getString(6);
																	long valc1=Long.parseLong(valc);
																	PreSumaCreditoC1=(valc1+PreSumaCreditoC1);
																	String valsa=rs.getString(4);
																	long valsad=Long.parseLong(valsa);
																	PreSumaSAD=(valsad+PreSumaSAD);
															}else{
																	String valdd=rs.getString(5);
																	long vald2=Long.parseLong(valdd);
																	PreSumaDebitoC2=(vald2+PreSumaDebitoC2);
																	String valcc=rs.getString(6);
																	long valc2=Long.parseLong(valcc);
																	PreSumaCreditoC2=(valc2+PreSumaCreditoC2);
																	String valsa2=rs.getString(4);
																	long valsac=Long.parseLong(valsa2);
																	PreSumaSAC=(valsac+PreSumaSAC);
														
															}
													}rs1.getStatement().getConnection().close();
												}
										}rs.getStatement().getConnection().close();
										long dif1=PreSumaDebitoC1-PreSumaCreditoC1;
										long dif2=0;
										if(PreSumaDebitoC2<PreSumaCreditoC2){
											dif2=(-(PreSumaDebitoC2-PreSumaCreditoC2));
										}else{
											dif2=(PreSumaDebitoC2-PreSumaCreditoC2);
										}
										
										/*if((RC1.equals("todas"))&&(RC2.equals("todas"))){
											sql=("SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, cdasc.saldo_anterior,SUM(cdasc.debito),SUM(cdasc.credito),cdasc.saldo_nuevo, cc.NaturalezaCuenta, e.* FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc,empresa e WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"' GROUP BY cuenta  ORDER BY cuenta ASC ");
										}else{
											sql=("SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel+"))as cuenta,cc.CodigoCuenta, cc.NombreCuenta, cdasc.saldo_anterior,SUM(cdasc.debito),SUM(cdasc.credito),cdasc.saldo_nuevo, cc.NaturalezaCuenta, e.* FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, empresa e WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'and cc.codigo between '"+RC1+"' and '"+RC2+"' GROUP BY cuenta  ORDER BY cuenta ASC");
										}*/
										int ident=0;
										String PreSumaSAD1=Long.toString(PreSumaSAD);
										String PreSumaSAC1=Long.toString(PreSumaSAC);
										String PreSumaDebitoC11=Long.toString(PreSumaDebitoC1);
										String PreSumaCreditoC11=Long.toString(PreSumaCreditoC1);
										String dif11=Long.toString(dif1);
										String dif22=Long.toString(dif2);
										String PreSumaDebitoC22=Long.toString(PreSumaDebitoC2);
										String PreSumaCreditoC22=Long.toString(PreSumaCreditoC2);
										long DSA=(PreSumaSAD-PreSumaSAC);
										long DD=(PreSumaSAD-PreSumaSAC);
										long DC=(PreSumaCreditoC1-PreSumaCreditoC2);
										long DDIF=(dif1-dif2);
										String DSA1=Long.toString(DSA);
										String DD1=Long.toString(DD);
										String DC1=Long.toString(DC);
										String DDIF1=Long.toString(DDIF);
										if(nivel.equals("1")){
											out.print("<tr><td colspan='6'> <br><br> </td></tr>");
											out.print("<tr align='rigth' class='rep'><td colspan='2'class='contpre' ><b>TOTAL DEBITOS</b></td><td align='right'>"+FormatMoneda(PreSumaSAD1)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaDebitoC11)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaCreditoC11)+" &nbsp;</td><td align='right'>"+FormatMoneda(dif11)+" </td></tr>");
											out.print("<tr align='rigth' class='rep'><td colspan='2' class='contpre' ><b>TOTAL CREDITOS</b></td><td align='right'>"+FormatMoneda(PreSumaSAC1)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaDebitoC22)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaCreditoC22)+" &nbsp;</td><td align='right'>"+FormatMoneda(dif22)+" </td></tr>");
											out.print("<tr align='rigth' class='rep'><td colspan='2' class='contpre'><b>DIFERENCIA</b></td><td align='right'>"+FormatMoneda(DSA1)+" &nbsp;</td><td align='right'>"+FormatMoneda(DD1)+" &nbsp;</td><td align='right'>"+FormatMoneda(DC1)+" &nbsp;</td><td align='right'>"+FormatMoneda(DDIF1)+"</td></tr>");
											ident=1;
										}
										
											//out.println("<tr align='left'><td><p align='left'><input type='button' value=' Generar PDF ' onclick='verrep1("+RC1+","+RC2+","+AC+","+MC+","+nivel+","+PreSumaSAD+","+PreSumaDebitoC1+","+PreSumaCreditoC1+","+PreSumaDebitoC2+","+PreSumaCreditoC2+","+PreSumaSAC+","+dif1+","+dif2+","+ident+","+Fecha+","+Hora+")'/></p></td></tr>");
									
								}else{
									if(cont==2){
										
										String nivel1=valorNivel[1];
										String nivel2=valorNivel[2];
										rs=mpr.BuscarDatosBalanceG2(RC1,RC2,AC,MC,nivel1,nivel2,Suc,centcost);
										
											while(rs.next()){
												cuenta=rs.getString(1);
												if(cuenta.equals("")){
													
												}else{
													System.out.println("entrando a validacion en cont 2");
													rs1=mpr.BuscarCuenta(cuenta);
													while(rs1.next()){
														out.println("<tr align='left' class='rep'><td>"+cuenta+"</td><td>"+rs1.getString(3)+"</td><td align='right'>"+FormatMoneda(rs.getString(4))+" &nbsp;</td><td align='right'>"+FormatMoneda(rs.getString(5))+" &nbsp;</td><td align='right'>"+FormatMoneda(rs.getString(6))+" &nbsp;</td><td align='right'>"+FormatMoneda(rs.getString(7))+"</td></tr>");
													}
												}rs1.getStatement().getConnection().close();
											}rs.getStatement().getConnection().close();
											if(nivel1.equals("1")){
												rs=mpr.BuscarDatosBalanceG1(RC1,RC2,AC,MC,nivel1,Suc,centcost);
												while(rs.next()){
													cuenta=rs.getString(1);
													if(cuenta.equals("")){ 
														
													}else{
														String Naturaleza=rs.getString(8);
														if(Naturaleza.equals("Debito")){
															String vald=rs.getString(5);
															long vald1=Long.parseLong(vald); 
															PreSumaDebitoC1=(vald1+PreSumaDebitoC1);
															String valc=rs.getString(6);
															long valc1=Long.parseLong(valc);
															PreSumaCreditoC1=(valc1+PreSumaCreditoC1);
															String valsa=rs.getString(4);
															long valsad=Long.parseLong(valsa);
															PreSumaSAD=(valsad+PreSumaSAD);
																
														}else{
															String valdd=rs.getString(5);
															long vald2=Long.parseLong(valdd);
															PreSumaDebitoC2=(vald2+PreSumaDebitoC2);
															String valcc=rs.getString(6);
															long valc2=Long.parseLong(valcc);
															PreSumaCreditoC2=(valc2+PreSumaCreditoC2);
															String valsa2=rs.getString(4);
															long valsac=Long.parseLong(valsa2);
															PreSumaSAC=(valsac+PreSumaSAC);
														}
													}
													
												}rs.getStatement().getConnection().close();
											
												long dif1=PreSumaDebitoC1-PreSumaCreditoC1;
												long dif2=0;
												if(PreSumaDebitoC2<PreSumaCreditoC2){
													dif2=(-(PreSumaDebitoC2-PreSumaCreditoC2));
												}else{
													dif2=(PreSumaDebitoC2-PreSumaCreditoC2);
												}
											
												String PreSumaSAD1=Long.toString(PreSumaSAD);
												String PreSumaSAC1=Long.toString(PreSumaSAC);
												String PreSumaDebitoC11=Long.toString(PreSumaDebitoC1);
												String PreSumaCreditoC11=Long.toString(PreSumaCreditoC1);
												String dif11=Long.toString(dif1);
												String dif22=Long.toString(dif2);
												String PreSumaDebitoC22=Long.toString(PreSumaDebitoC2);
												String PreSumaCreditoC22=Long.toString(PreSumaCreditoC2);
												long DSA=(PreSumaSAD-PreSumaSAC);
												long DD=(PreSumaSAD-PreSumaSAC);
												long DC=(PreSumaCreditoC1-PreSumaCreditoC2);
												long DDIF=(dif1-dif2);
												String DSA1=Long.toString(DSA);
												String DD1=Long.toString(DD);
												String DC1=Long.toString(DC);
												String DDIF1=Long.toString(DDIF);
												out.print("<tr><td colspan='6'> <br><br> </td></tr>");
												out.print("<tr align='rigth' class='rep'><td colspan='2'class='contpre' ><b>TOTAL DEBITOS</b></td><td align='right'>"+FormatMoneda(PreSumaSAD1)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaDebitoC11)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaCreditoC11)+" &nbsp;</td><td align='right'>"+FormatMoneda(dif11)+"</td></tr>");
												out.print("<tr align='rigth' class='rep'><td colspan='2' class='contpre' ><b>TOTAL CREDITOS</b></td><td align='right'>"+FormatMoneda(PreSumaSAC1)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaDebitoC22)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaCreditoC22)+" &nbsp;</td><td align='right'>"+FormatMoneda(dif22)+" </td></tr>");
												out.print("<tr align='rigth' class='rep'><td colspan='2' class='contpre'><b>DIFERENCIA</b></td><td align='right'>"+FormatMoneda(DSA1)+" &nbsp;</td><td align='right'>"+FormatMoneda(DD1)+" &nbsp;</td><td align='right'>"+FormatMoneda(DC1)+" &nbsp;</td><td align='right'>"+FormatMoneda(DDIF1)+"</td></tr>");
												//out.println("<p align='left'><input type='button' value=' Generar PDF '/></p>");
											}

										
									}else{
										if(cont==3){
											
											String nivel1=valorNivel[1];
											String nivel2=valorNivel[2];
											String nivel3=valorNivel[3];
											rs=mpr.BuscarDatosBalanceG3(RC1,RC2,AC,MC,nivel1,nivel2,nivel3,Suc,centcost);
											
												while(rs.next()){
													cuenta=rs.getString(1);
													if(cuenta.equals("")){
														
													}else{
														rs1=mpr.BuscarCuenta(cuenta);
														while(rs1.next()){
															out.println("<tr align='left' class='rep'><td>"+cuenta+"</td><td>"+rs1.getString(3)+"</td><td align='right'>"+FormatMoneda(rs.getString(4))+" &nbsp;</td><td align='right'>"+FormatMoneda(rs.getString(5))+" &nbsp;</td><td align='right'>"+FormatMoneda(rs.getString(6))+" &nbsp;</td><td align='right'>"+FormatMoneda(rs.getString(7))+"</td>");
														}
														rs1.getStatement().getConnection().close();
													}
												}
												rs.getStatement().getConnection().close();
												if(nivel1.equals("1")){
													rs=mpr.BuscarDatosBalanceG1(RC1,RC2,AC,MC,nivel1,Suc,centcost);
													while(rs.next()){
														cuenta=rs.getString(1);
														if(cuenta.equals("")){ 
															
														}else{
																String Naturaleza=rs.getString(8);
																if(Naturaleza.equals("Debito")){
																	String vald=rs.getString(5);
																	long vald1=Long.parseLong(vald); 
																	PreSumaDebitoC1=(vald1+PreSumaDebitoC1);
																	String valc=rs.getString(6);
																	long valc1=Long.parseLong(valc);
																	PreSumaCreditoC1=(valc1+PreSumaCreditoC1);
																	String valsa=rs.getString(4);
																	long valsad=Long.parseLong(valsa);
																	PreSumaSAD=(valsad+PreSumaSAD);		
																}else{
																	String valdd=rs.getString(5);
																	long vald2=Long.parseLong(valdd);
																	PreSumaDebitoC2=(vald2+PreSumaDebitoC2);
																	String valcc=rs.getString(6);
																	long valc2=Long.parseLong(valcc);
																	PreSumaCreditoC2=(valc2+PreSumaCreditoC2);
																	String valsa2=rs.getString(4);
																	long valsac=Long.parseLong(valsa2);
																	PreSumaSAC=(valsac+PreSumaSAC);
																}
														}
															
													} rs.getStatement().getConnection().close();
												
													long dif1=PreSumaDebitoC1-PreSumaCreditoC1;
													long dif2=0;
													if(PreSumaDebitoC2<PreSumaCreditoC2){
														dif2=(-(PreSumaDebitoC2-PreSumaCreditoC2));
													}else{
														dif2=(PreSumaDebitoC2-PreSumaCreditoC2);
													}
													String PreSumaSAD1=Long.toString(PreSumaSAD);
													String PreSumaSAC1=Long.toString(PreSumaSAC);
													String PreSumaDebitoC11=Long.toString(PreSumaDebitoC1);
													String PreSumaCreditoC11=Long.toString(PreSumaCreditoC1);
													String dif11=Long.toString(dif1);
													String dif22=Long.toString(dif2);
													String PreSumaDebitoC22=Long.toString(PreSumaDebitoC2);
													String PreSumaCreditoC22=Long.toString(PreSumaCreditoC2);
													long DSA=(PreSumaSAD-PreSumaSAC);
													long DD=(PreSumaSAD-PreSumaSAC);
													long DC=(PreSumaCreditoC1-PreSumaCreditoC2);
													long DDIF=(dif1-dif2);
													String DSA1=Long.toString(DSA);
													String DD1=Long.toString(DD);
													String DC1=Long.toString(DC);
													String DDIF1=Long.toString(DDIF);
													out.print("<tr><td colspan='6'> <br><br> </td></tr>");
													out.print("<tr align='rigth' class='rep'><td colspan='2'class='contpre' ><b>TOTAL DEBITOS</b></td><td align='right'>"+FormatMoneda(PreSumaSAD1)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaDebitoC11)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaCreditoC11)+" &nbsp;</td><td align='right'>"+FormatMoneda(dif11)+" </td></tr>");
													out.print("<tr align='rigth' class='rep'><td colspan='2' class='contpre' ><b>TOTAL CREDITOS</b></td><td align='right'>"+FormatMoneda(PreSumaSAC1)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaDebitoC22)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaCreditoC22)+" &nbsp;</td><td align='right'>"+FormatMoneda(dif22)+" </td></tr>");
													out.print("<tr align='rigth' class='rep'><td colspan='2' class='contpre'><b>DIFERENCIA</b></td><td align='right'>"+FormatMoneda(DSA1)+" &nbsp;</td><td align='right'>"+FormatMoneda(DD1)+" &nbsp;</td><td align='right'>"+FormatMoneda(DC1)+" &nbsp;</td><td align='right'>"+FormatMoneda(DDIF1)+" </td></tr>");
													//out.println("<p align='left'><input type='button' value=' Generar PDF '/></p>");
												}
													
										}else{
											if(cont==4){
												
												String nivel1=valorNivel[1];
												String nivel2=valorNivel[2];
												String nivel3=valorNivel[3];
												String nivel4=valorNivel[4];
												rs=mpr.BuscarDatosBalanceG4(RC1,RC2,AC,MC,nivel1,nivel2,nivel3,nivel4,Suc,centcost);
												
													while(rs.next()){
														cuenta=rs.getString(1);
														if(cuenta.equals("")){
															
														}else{
															rs1=mpr.BuscarCuenta(cuenta);
															while(rs1.next()){
																out.println("<tr align='left' class='rep'><td>"+cuenta+"</td><td>"+rs1.getString(3)+"</td><td align='right'>"+FormatMoneda(rs.getString(4))+" &nbsp;</td><td align='right'>"+FormatMoneda(rs.getString(5))+" &nbsp;</td><td align='right'>"+FormatMoneda(rs.getString(6))+" &nbsp;</td><td align='right'>"+FormatMoneda(rs.getString(7))+"</td>");
															}
															rs1.getStatement().getConnection().close();
														}
													}
													rs.getStatement().getConnection().close();
													if(nivel1.equals("1")){
														rs=mpr.BuscarDatosBalanceG1(RC1,RC2,AC,MC,nivel1,Suc,centcost);
													
														while(rs.next()){
															cuenta=rs.getString(1);
															if(cuenta.equals("")){ 
																
															}else{
																String Naturaleza=rs.getString(8);
																if(Naturaleza.equals("Debito")){
																	String vald=rs.getString(5);
																	long vald1=Long.parseLong(vald); 
																	PreSumaDebitoC1=(vald1+PreSumaDebitoC1);
																	String valc=rs.getString(6);
																	long valc1=Long.parseLong(valc);
																	PreSumaCreditoC1=(valc1+PreSumaCreditoC1);
																	String valsa=rs.getString(4);
																	long valsad=Long.parseLong(valsa);
																	PreSumaSAD=(valsad+PreSumaSAD);
																		
																}else{
																	String valdd=rs.getString(5);
																	long vald2=Long.parseLong(valdd);
																	PreSumaDebitoC2=(vald2+PreSumaDebitoC2);
																	String valcc=rs.getString(6);
																	long valc2=Long.parseLong(valcc);
																	PreSumaCreditoC2=(valc2+PreSumaCreditoC2);
																	String valsa2=rs.getString(4);
																	long valsac=Long.parseLong(valsa2);
																	PreSumaSAC=(valsac+PreSumaSAC);
																}
															}
															
														}rs.getStatement().getConnection().close();
													
														long dif1=PreSumaDebitoC1-PreSumaCreditoC1;
														long dif2=0;
														if(PreSumaDebitoC2<PreSumaCreditoC2){
														 dif2=(-(PreSumaDebitoC2-PreSumaCreditoC2));
														}else{
															dif2=(PreSumaDebitoC2-PreSumaCreditoC2);
														}
														String PreSumaSAD1=Long.toString(PreSumaSAD);
														String PreSumaSAC1=Long.toString(PreSumaSAC);
														String PreSumaDebitoC11=Long.toString(PreSumaDebitoC1);
														String PreSumaCreditoC11=Long.toString(PreSumaCreditoC1);
														String dif11=Long.toString(dif1);
														String dif22=Long.toString(dif2);
														String PreSumaDebitoC22=Long.toString(PreSumaDebitoC2);
														String PreSumaCreditoC22=Long.toString(PreSumaCreditoC2);
														long DSA=(PreSumaSAD-PreSumaSAC);
														long DD=(PreSumaSAD-PreSumaSAC);
														long DC=(PreSumaCreditoC1-PreSumaCreditoC2);
														long DDIF=(dif1-dif2);
														String DSA1=Long.toString(DSA);
														String DD1=Long.toString(DD);
														String DC1=Long.toString(DC);
														String DDIF1=Long.toString(DDIF);
														out.print("<tr><td colspan='6'> <br><br> </td></tr>");
														out.print("<tr align='rigth' class='rep'><td colspan='2'class='contpre' ><b>TOTAL DEBITOS</b></td><td align='right'>"+FormatMoneda(PreSumaSAD1)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaDebitoC11)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaCreditoC11)+" &nbsp;</td><td align='right'>"+FormatMoneda(dif11)+" </td></tr>");
														out.print("<tr align='rigth' class='rep'><td colspan='2' class='contpre' ><b>TOTAL CREDITOS</b></td><td align='right'>"+FormatMoneda(PreSumaSAC1)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaDebitoC22)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaCreditoC22)+" &nbsp;</td><td align='right'>"+FormatMoneda(dif22)+" </td></tr>");
														out.print("<tr align='rigth' class='rep'><td colspan='2' class='contpre'><b>DIFERENCIA</b></td><td align='right'>"+FormatMoneda(DSA1)+" &nbsp;</td><td align='right'>"+FormatMoneda(DD1)+" &nbsp;</td><td align='right'>"+FormatMoneda(DC1)+" &nbsp;</td><td align='right'>"+FormatMoneda(DDIF1)+"</td></tr>");
														//out.println("<p align='left'><input type='button' value=' Generar PDF '/></p>");
													
													}
												
											}else{
												if(cont==5){
													System.out.println("entrando a nivel 5");
													String nivel1=valorNivel[1];
													String nivel2=valorNivel[2];
													String nivel3=valorNivel[3];
													String nivel4=valorNivel[4];
													String nivel5=valorNivel[5];
													rs=mpr.BuscarDatosBalanceG5(RC1,RC2,AC,MC,nivel1,nivel2,nivel3,nivel4,nivel5,Suc,centcost);
													
														while(rs.next()){
															cuenta=rs.getString(1);
															if(cuenta.equals("")){
																
															}else{
																rs1=mpr.BuscarCuenta(cuenta);
																while(rs1.next()){
																	out.println("<tr align='left' class='rep'><td>"+cuenta+"</td><td>"+rs1.getString(3)+"</td><td align='right'>"+FormatMoneda(rs.getString(4))+" &nbsp;</td><td align='right'>"+FormatMoneda(rs.getString(5))+" &nbsp;</td><td align='right'>"+FormatMoneda(rs.getString(6))+" &nbsp;</td><td align='right'>"+FormatMoneda(rs.getString(7))+"</td>");
																}
																rs1.getStatement().getConnection().close();
															}
														}
														rs.getStatement().getConnection().close();
														if(nivel1.equals("1")){
															System.out.println("entrando a validacion");
															rs=mpr.BuscarDatosBalanceG1(RC1,RC2,AC,MC,nivel1,Suc,centcost);
															while(rs.next()){
																cuenta=rs.getString(1);
																if(cuenta.equals("")){
																	
																}else{
																	String Naturaleza=rs.getString(8);
																	if(Naturaleza.equals("Debito")){
																		String vald=rs.getString(5);
																		long vald1=Long.parseLong(vald); 
																		PreSumaDebitoC1=(vald1+PreSumaDebitoC1);
																		String valc=rs.getString(6);
																		long valc1=Long.parseLong(valc);
																		PreSumaCreditoC1=(valc1+PreSumaCreditoC1);
																		String valsa=rs.getString(4);
																		long valsad=Long.parseLong(valsa);
																		PreSumaSAD=(valsad+PreSumaSAD);		
																	}else{
																		String valdd=rs.getString(5);
																		long vald2=Long.parseLong(valdd);
																		PreSumaDebitoC2=(vald2+PreSumaDebitoC2);
																		String valcc=rs.getString(6);
																		long valc2=Long.parseLong(valcc);
																		PreSumaCreditoC2=(valc2+PreSumaCreditoC2);
																		String valsa2=rs.getString(4);
																		long valsac=Long.parseLong(valsa2);
																		PreSumaSAC=(valsac+PreSumaSAC);
																	}
																}
																
															}rs.getStatement().getConnection().close();
															long dif1=0;
															dif1=PreSumaDebitoC1-PreSumaCreditoC1;
															long dif2=0;
															if(PreSumaDebitoC2<PreSumaCreditoC2){
															 dif2=(-(PreSumaDebitoC2-PreSumaCreditoC2));
															}else{
																dif2=(PreSumaDebitoC2-PreSumaCreditoC2);
															}
															String PreSumaSAD1=Long.toString(PreSumaSAD);
															String PreSumaSAC1=Long.toString(PreSumaSAC);
															String PreSumaDebitoC11=Long.toString(PreSumaDebitoC1);
															String PreSumaCreditoC11=Long.toString(PreSumaCreditoC1);
															String dif11=Long.toString(dif1);
															String dif22=Long.toString(dif2);
															String PreSumaDebitoC22=Long.toString(PreSumaDebitoC2);
															String PreSumaCreditoC22=Long.toString(PreSumaCreditoC2);
															long DSA=(PreSumaSAD-PreSumaSAC);
															long DD=(PreSumaSAD-PreSumaSAC);
															long DC=(PreSumaCreditoC1-PreSumaCreditoC2);
															long DDIF=(dif1-dif2);
															String DSA1=Long.toString(DSA);
															String DD1=Long.toString(DD);
															String DC1=Long.toString(DC);
															String DDIF1=Long.toString(DDIF);
															out.print("<tr align='rigth' class='rep'><td colspan='2'class='contpre' ><b>TOTAL DEBITOS</b></td><td align='right'>"+FormatMoneda(PreSumaSAD1)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaDebitoC11)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaCreditoC11)+" &nbsp;</td><td align='right'>"+FormatMoneda(dif11)+" </td></tr>");
															out.print("<tr align='rigth' class='rep'><td colspan='2' class='contpre' ><b>TOTAL CREDITOS</b></td><td align='right'>"+FormatMoneda(PreSumaSAC1)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaDebitoC22)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaCreditoC22)+" &nbsp;</td><td align='right'>"+FormatMoneda(dif22)+" </td></tr>");
															out.print("<tr align='rigth' class='rep'><td colspan='2' class='contpre'><b>DIFERENCIA</b></td><td align='right'>"+FormatMoneda(DSA1)+" &nbsp;</td><td align='right'>"+FormatMoneda(DD1)+" &nbsp;</td><td align='right'>"+FormatMoneda(DC1)+" &nbsp;</td><td align='right'>"+FormatMoneda(DDIF1)+" </td></tr>");
															//out.println("<p align='left'><input type='button' value=' Generar PDF '/></p>");
															
														}
													
													}
												}
											}
										}
									}
								}rs3.getStatement().getConnection().close();
								}rs4.getStatement().getConnection().close();
				
						
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		
		
		if(va.equals("RepBalanceGeneral")){
			String RC1=req.getParameter("RC1");
			String RC2=req.getParameter("RC2");
			String MC=req.getParameter("MC");
			String AC=req.getParameter("AC");
			if(RC2.equals("TODAS")){
				
			}else{
				/*int rc11=Integer.parseInt(RC1);
				int rc22=Integer.parseInt(RC2);
				if(RC1.equals(RC2)){
				}else{*/
				RC2=RC2+"9";
				/*}*/
			}
			System.out.println(" valor de RC2"+RC2);
			String nivelSeleccionado=req.getParameter("nivelSeleccionado");
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
			java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());
			System.out.println("NivelSeleccionado"+nivelSeleccionado);
			String sql="";
			String[] detnivelSeleccionado = nivelSeleccionado.split("_");
			out.print("<br><br><table width='80%'>");
			rs=mpr.BuscarEmpresa();
			try {
				while(rs.next()){
				out.println("<tr class='contpre'><td align='center' colspan='6'>"+rs.getString(1)+"</td></tr>");
				out.println("<tr class='contpre'><td align='center' colspan='6'>"+rs.getString(2)+"<br></td></tr>");
				
				}rs.getStatement().getConnection().close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			out.print("<tr class='contpre'><td colspan='3' align='left'>BALANCE DE PRUEBA GENERAL</td><td colspan='2' align='left'>Fecha de Generacion:</td><td colspan='1'>"+Fecha+"</td></tr>");
			out.print("<tr class='contpre'><td colspan='3' align='left'>CORRESPONDIENTE AL PERIODO "+MC+" DEL A�O "+AC+"</td><td colspan='2' align='left'>Hora de Generacion:</td><td colspan='1'>"+Hora+"</td></tr>");
			out.print("<tr><td colspan='6'><hr></td>");
			out.print("<tr class='rep' ><b><td >&nbsp; CUENTA &nbsp;</td><td>&nbsp; NOMBRE DE LA CUENTA &nbsp;</td><td>&nbsp; SALDO ANTERIOR &nbsp;</td><td>&nbsp; DEBITOS &nbsp;</td><td>&nbsp; CREDITOS &nbsp;</td><td>&nbsp; SALDO NUEVO &nbsp;</td></b></tr>");
			out.print("<tr><td colspan='6'><hr></td>");
			String cuenta="";
			String valorNivel [] = new String[6];
			int cont=0;
			for (int i = 0; i < detnivelSeleccionado.length; i++) {
				if(detnivelSeleccionado[i].equals("1")){
					valorNivel[i+1]="1";
				}else{
					if(detnivelSeleccionado[i].equals("2")){
						valorNivel[i+1]="2";
					}else{
						if(detnivelSeleccionado[i].equals("3")){
							valorNivel[i+1]="4";
						}else{
							if(detnivelSeleccionado[i].equals("4")){
								valorNivel[i+1]="6";
							}else{
								if(detnivelSeleccionado[i].equals("5")){						
								valorNivel[i+1]="8";
								}
							}
						}
				}
				
			}
				cont=cont+1;
			}
			long PreSumaDebitoC1=0;
			long PreSumaCreditoC1=0;
			long PreSumaDebitoC2=0;
			long PreSumaCreditoC2=0;
			long PreSumaSAD=0;
			long PreSumaSAC=0;
		
			if(cont>0){
				System.out.println("Valor de cont "+cont);
				if(cont==1){
				String nivel=valorNivel[1];
				System.out.println("valor de nivel"+nivel);
					rs=mpr.BuscarDatosBalanceG1(RC1,RC2,AC,MC,nivel,"nada","nada");
					try {
						
						while(rs.next()){
							cuenta=rs.getString(1);
							if(cuenta.equals("")){
								
							}else{
								rs1=mpr.BuscarCuenta(cuenta);
								while(rs1.next()){
									out.println("<tr align='left' class='rep'><td>"+cuenta+"</td><td>"+rs1.getString(3)+"</td><td align='right'>"+FormatMoneda(rs.getString(4))+" &nbsp;</td><td align='right'>"+FormatMoneda(rs.getString(5))+" &nbsp;</td><td align='right'>"+FormatMoneda(rs.getString(6))+" &nbsp;</td><td align='right'>"+FormatMoneda(rs.getString(7))+"</td></tr>");
									String Naturaleza=rs.getString(8);
									if(Naturaleza.equals("Debito")){
											String vald=rs.getString(5);
											long vald1=Long.parseLong(vald); 
											PreSumaDebitoC1=(vald1+PreSumaDebitoC1);
											String valc=rs.getString(6);
											long valc1=Long.parseLong(valc);
											PreSumaCreditoC1=(valc1+PreSumaCreditoC1);
											String valsa=rs.getString(4);
											long valsad=Long.parseLong(valsa);
											PreSumaSAD=(valsad+PreSumaSAD);
											
									}else{
										String valdd=rs.getString(5);
										long vald2=Long.parseLong(valdd);
										PreSumaDebitoC2=(vald2+PreSumaDebitoC2);
										String valcc=rs.getString(6);
										long valc2=Long.parseLong(valcc);
										PreSumaCreditoC2=(valc2+PreSumaCreditoC2);
										String valsa2=rs.getString(4);
										long valsac=Long.parseLong(valsa2);
										PreSumaSAC=(valsac+PreSumaSAC);
										
									}
								}
								rs1.getStatement().getConnection().close();
							}
						}
						long dif1=PreSumaDebitoC1-PreSumaCreditoC1;
						long dif2=0;
						if(PreSumaDebitoC2<PreSumaCreditoC2){
						 dif2=(-(PreSumaDebitoC2-PreSumaCreditoC2));
						}else{
							dif2=(PreSumaDebitoC2-PreSumaCreditoC2);
						}
						
						/*if((RC1.equals("todas"))&&(RC2.equals("todas"))){
							sql=("SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, cdasc.saldo_anterior,SUM(cdasc.debito),SUM(cdasc.credito),cdasc.saldo_nuevo, cc.NaturalezaCuenta, e.* FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc,empresa e WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"' GROUP BY cuenta  ORDER BY cuenta ASC ");
						}else{
							sql=("SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel+"))as cuenta,cc.CodigoCuenta, cc.NombreCuenta, cdasc.saldo_anterior,SUM(cdasc.debito),SUM(cdasc.credito),cdasc.saldo_nuevo, cc.NaturalezaCuenta, e.* FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, empresa e WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'and cc.codigo between '"+RC1+"' and '"+RC2+"' GROUP BY cuenta  ORDER BY cuenta ASC");
						}*/
						int ident=0;
						String PreSumaSAD1=Long.toString(PreSumaSAD);
						String PreSumaSAC1=Long.toString(PreSumaSAC);
						String PreSumaDebitoC11=Long.toString(PreSumaDebitoC1);
						String PreSumaCreditoC11=Long.toString(PreSumaCreditoC1);
						String dif11=Long.toString(dif1);
						String dif22=Long.toString(dif2);
						String PreSumaDebitoC22=Long.toString(PreSumaDebitoC2);
						String PreSumaCreditoC22=Long.toString(PreSumaCreditoC2);
						long DSA=(PreSumaSAD-PreSumaSAC);
						long DD=(PreSumaSAD-PreSumaSAC);
						long DC=(PreSumaCreditoC1-PreSumaCreditoC2);
						long DDIF=(dif1-dif2);
						String DSA1=Long.toString(DSA);
						String DD1=Long.toString(DD);
						String DC1=Long.toString(DC);
						String DDIF1=Long.toString(DDIF);
						if(nivel.equals("1")){
						out.print("<tr><td colspan='6'> <br><br> </td></tr>");
						out.print("<tr align='rigth' class='rep'><td colspan='2'class='contpre' ><b>TOTAL DEBITOS</b></td><td align='right'>"+FormatMoneda(PreSumaSAD1)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaDebitoC11)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaCreditoC11)+" &nbsp;</td><td align='right'>"+FormatMoneda(dif11)+" </td></tr>");
						out.print("<tr align='rigth' class='rep'><td colspan='2' class='contpre' ><b>TOTAL CREDITOS</b></td><td align='right'>"+FormatMoneda(PreSumaSAC1)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaDebitoC22)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaCreditoC22)+" &nbsp;</td><td align='right'>"+FormatMoneda(dif22)+" </td></tr>");
						out.print("<tr align='rigth' class='rep'><td colspan='2' class='contpre'><b>DIFERENCIA</b></td><td align='right'>"+FormatMoneda(DSA1)+" &nbsp;</td><td align='right'>"+FormatMoneda(DD1)+" &nbsp;</td><td align='right'>"+FormatMoneda(DC1)+" &nbsp;</td><td align='right'>"+FormatMoneda(DDIF1)+"</td></tr>");
						ident=1;
						}
						
							//out.println("<tr align='left'><td><p align='left'><input type='button' value=' Generar PDF ' onclick='verrep1("+RC1+","+RC2+","+AC+","+MC+","+nivel+","+PreSumaSAD+","+PreSumaDebitoC1+","+PreSumaCreditoC1+","+PreSumaDebitoC2+","+PreSumaCreditoC2+","+PreSumaSAC+","+dif1+","+dif2+","+ident+","+Fecha+","+Hora+")'/></p></td></tr>");
						
						rs.getStatement().getConnection().close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					if(cont==2){
						
						String nivel1=valorNivel[1];
						String nivel2=valorNivel[2];
						rs=mpr.BuscarDatosBalanceG2(RC1,RC2,AC,MC,nivel1,nivel2,"nada","nada");
						try {
							while(rs.next()){
								cuenta=rs.getString(1);
								if(cuenta.equals("")){
									
								}else{
									rs1=mpr.BuscarCuenta(cuenta);
									while(rs1.next()){
										out.println("<tr align='left' class='rep'><td>"+cuenta+"</td><td>"+rs1.getString(3)+"</td><td align='right'>"+FormatMoneda(rs.getString(4))+" &nbsp;</td><td align='right'>"+FormatMoneda(rs.getString(5))+" &nbsp;</td><td align='right'>"+FormatMoneda(rs.getString(6))+" &nbsp;</td><td align='right'>"+FormatMoneda(rs.getString(7))+"</td></tr>");
									}
									rs1.getStatement().getConnection().close();
								}
							}
							rs.getStatement().getConnection().close();
							System.out.print("nivel1="+nivel1);
							if(nivel1.equals("1")){
								System.out.print("nivel1=");
							rs=mpr.BuscarDatosBalanceG1(RC1,RC2,AC,MC,nivel1,"nada","nada");
							while(rs.next()){
								cuenta=rs.getString(1);
								if(cuenta.equals("")){ }else{
										String Naturaleza=rs.getString(8);
										if(Naturaleza.equals("Debito")){
											String vald=rs.getString(5);
											long vald1=Long.parseLong(vald); 
											PreSumaDebitoC1=(vald1+PreSumaDebitoC1);
											String valc=rs.getString(6);
											long valc1=Long.parseLong(valc);
											PreSumaCreditoC1=(valc1+PreSumaCreditoC1);
											String valsa=rs.getString(4);
											long valsad=Long.parseLong(valsa);
											PreSumaSAD=(valsad+PreSumaSAD);
												
										}else{
											String valdd=rs.getString(5);
											long vald2=Long.parseLong(valdd);
											PreSumaDebitoC2=(vald2+PreSumaDebitoC2);
											String valcc=rs.getString(6);
											long valc2=Long.parseLong(valcc);
											PreSumaCreditoC2=(valc2+PreSumaCreditoC2);
											String valsa2=rs.getString(4);
											long valsac=Long.parseLong(valsa2);
											PreSumaSAC=(valsac+PreSumaSAC);
										}
									}
									
								}
							
							long dif1=PreSumaDebitoC1-PreSumaCreditoC1;
							long dif2=0;
							if(PreSumaDebitoC2<PreSumaCreditoC2){
							 dif2=(-(PreSumaDebitoC2-PreSumaCreditoC2));
							}else{
								dif2=(PreSumaDebitoC2-PreSumaCreditoC2);
							}
							
							String PreSumaSAD1=Long.toString(PreSumaSAD);
							String PreSumaSAC1=Long.toString(PreSumaSAC);
							String PreSumaDebitoC11=Long.toString(PreSumaDebitoC1);
							String PreSumaCreditoC11=Long.toString(PreSumaCreditoC1);
							String dif11=Long.toString(dif1);
							String dif22=Long.toString(dif2);
							String PreSumaDebitoC22=Long.toString(PreSumaDebitoC2);
							String PreSumaCreditoC22=Long.toString(PreSumaCreditoC2);
							long DSA=(PreSumaSAD-PreSumaSAC);
							long DD=(PreSumaSAD-PreSumaSAC);
							long DC=(PreSumaCreditoC1-PreSumaCreditoC2);
							long DDIF=(dif1-dif2);
							String DSA1=Long.toString(DSA);
							String DD1=Long.toString(DD);
							String DC1=Long.toString(DC);
							String DDIF1=Long.toString(DDIF);
							out.print("<tr><td colspan='6'> <br><br> </td></tr>");
							out.print("<tr align='rigth' class='rep'><td colspan='2'class='contpre' ><b>TOTAL DEBITOS</b></td><td align='right'>"+FormatMoneda(PreSumaSAD1)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaDebitoC11)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaCreditoC11)+" &nbsp;</td><td align='right'>"+FormatMoneda(dif11)+"</td></tr>");
							out.print("<tr align='rigth' class='rep'><td colspan='2' class='contpre' ><b>TOTAL CREDITOS</b></td><td align='right'>"+FormatMoneda(PreSumaSAC1)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaDebitoC22)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaCreditoC22)+" &nbsp;</td><td align='right'>"+FormatMoneda(dif22)+" </td></tr>");
							out.print("<tr align='rigth' class='rep'><td colspan='2' class='contpre'><b>DIFERENCIA</b></td><td align='right'>"+FormatMoneda(DSA1)+" &nbsp;</td><td align='right'>"+FormatMoneda(DD1)+" &nbsp;</td><td align='right'>"+FormatMoneda(DC1)+" &nbsp;</td><td align='right'>"+FormatMoneda(DDIF1)+"</td></tr>");
							//out.println("<p align='left'><input type='button' value=' Generar PDF '/></p>");
							}
							rs.getStatement().getConnection().close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else{
						if(cont==3){
							
							String nivel1=valorNivel[1];
							String nivel2=valorNivel[2];
							String nivel3=valorNivel[3];
							rs=mpr.BuscarDatosBalanceG3(RC1,RC2,AC,MC,nivel1,nivel2,nivel3,"nada","nada");
							try {
								while(rs.next()){
									cuenta=rs.getString(1);
									if(cuenta.equals("")){
										
									}else{
										rs1=mpr.BuscarCuenta(cuenta);
										while(rs1.next()){
											out.println("<tr align='left' class='rep'><td>"+cuenta+"</td><td>"+rs1.getString(3)+"</td><td align='right'>"+FormatMoneda(rs.getString(4))+" &nbsp;</td><td align='right'>"+FormatMoneda(rs.getString(5))+" &nbsp;</td><td align='right'>"+FormatMoneda(rs.getString(6))+" &nbsp;</td><td align='right'>"+FormatMoneda(rs.getString(7))+"</td>");
										}
										rs1.getStatement().getConnection().close();
									}
								}
								rs.getStatement().getConnection().close();
								if(nivel1.equals("1")){
								rs=mpr.BuscarDatosBalanceG1(RC1,RC2,AC,MC,nivel1,"nada","nada");
								while(rs.next()){
									cuenta=rs.getString(1);
									if(cuenta.equals("")){ }else{
											String Naturaleza=rs.getString(8);
											if(Naturaleza.equals("Debito")){
												String vald=rs.getString(5);
												long vald1=Long.parseLong(vald); 
												PreSumaDebitoC1=(vald1+PreSumaDebitoC1);
												String valc=rs.getString(6);
												long valc1=Long.parseLong(valc);
												PreSumaCreditoC1=(valc1+PreSumaCreditoC1);
												String valsa=rs.getString(4);
												long valsad=Long.parseLong(valsa);
												PreSumaSAD=(valsad+PreSumaSAD);		
											}else{
												String valdd=rs.getString(5);
												long vald2=Long.parseLong(valdd);
												PreSumaDebitoC2=(vald2+PreSumaDebitoC2);
												String valcc=rs.getString(6);
												long valc2=Long.parseLong(valcc);
												PreSumaCreditoC2=(valc2+PreSumaCreditoC2);
												String valsa2=rs.getString(4);
												long valsac=Long.parseLong(valsa2);
												PreSumaSAC=(valsac+PreSumaSAC);
											}
										}
										
									}
								
								long dif1=PreSumaDebitoC1-PreSumaCreditoC1;
								long dif2=0;
								if(PreSumaDebitoC2<PreSumaCreditoC2){
								 dif2=(-(PreSumaDebitoC2-PreSumaCreditoC2));
								}else{
									dif2=(PreSumaDebitoC2-PreSumaCreditoC2);
								}
								String PreSumaSAD1=Long.toString(PreSumaSAD);
								String PreSumaSAC1=Long.toString(PreSumaSAC);
								String PreSumaDebitoC11=Long.toString(PreSumaDebitoC1);
								String PreSumaCreditoC11=Long.toString(PreSumaCreditoC1);
								String dif11=Long.toString(dif1);
								String dif22=Long.toString(dif2);
								String PreSumaDebitoC22=Long.toString(PreSumaDebitoC2);
								String PreSumaCreditoC22=Long.toString(PreSumaCreditoC2);
								long DSA=(PreSumaSAD-PreSumaSAC);
								long DD=(PreSumaSAD-PreSumaSAC);
								long DC=(PreSumaCreditoC1-PreSumaCreditoC2);
								long DDIF=(dif1-dif2);
								String DSA1=Long.toString(DSA);
								String DD1=Long.toString(DD);
								String DC1=Long.toString(DC);
								String DDIF1=Long.toString(DDIF);
								out.print("<tr><td colspan='6'> <br><br> </td></tr>");
								out.print("<tr align='rigth' class='rep'><td colspan='2'class='contpre' ><b>TOTAL DEBITOS</b></td><td align='right'>"+FormatMoneda(PreSumaSAD1)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaDebitoC11)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaCreditoC11)+" &nbsp;</td><td align='right'>"+FormatMoneda(dif11)+" </td></tr>");
								out.print("<tr align='rigth' class='rep'><td colspan='2' class='contpre' ><b>TOTAL CREDITOS</b></td><td align='right'>"+FormatMoneda(PreSumaSAC1)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaDebitoC22)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaCreditoC22)+" &nbsp;</td><td align='right'>"+FormatMoneda(dif22)+" </td></tr>");
								out.print("<tr align='rigth' class='rep'><td colspan='2' class='contpre'><b>DIFERENCIA</b></td><td align='right'>"+FormatMoneda(DSA1)+" &nbsp;</td><td align='right'>"+FormatMoneda(DD1)+" &nbsp;</td><td align='right'>"+FormatMoneda(DC1)+" &nbsp;</td><td align='right'>"+FormatMoneda(DDIF1)+" </td></tr>");
								//out.println("<p align='left'><input type='button' value=' Generar PDF '/></p>");
								}
									rs.getStatement().getConnection().close();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}else{
							if(cont==4){
								
								String nivel1=valorNivel[1];
								String nivel2=valorNivel[2];
								String nivel3=valorNivel[3];
								String nivel4=valorNivel[4];
								rs=mpr.BuscarDatosBalanceG4(RC1,RC2,AC,MC,nivel1,nivel2,nivel3,nivel4,"nada","nada");
								try {
									while(rs.next()){
										cuenta=rs.getString(1);
										if(cuenta.equals("")){
											
										}else{
											rs1=mpr.BuscarCuenta(cuenta);
											while(rs1.next()){
												out.println("<tr align='left' class='rep'><td>"+cuenta+"</td><td>"+rs1.getString(3)+"</td><td align='right'>"+FormatMoneda(rs.getString(4))+" &nbsp;</td><td align='right'>"+FormatMoneda(rs.getString(5))+" &nbsp;</td><td align='right'>"+FormatMoneda(rs.getString(6))+" &nbsp;</td><td align='right'>"+FormatMoneda(rs.getString(7))+"</td>");
											}
											rs1.getStatement().getConnection().close();
										}
									}
									rs.getStatement().getConnection().close();
									if(nivel1.equals("1")){
									rs=mpr.BuscarDatosBalanceG1(RC1,RC2,AC,MC,nivel1,"nada","nada");
									
									while(rs.next()){
										cuenta=rs.getString(1);
										if(cuenta.equals("")){ }else{
												String Naturaleza=rs.getString(8);
												if(Naturaleza.equals("Debito")){
													String vald=rs.getString(5);
													long vald1=Long.parseLong(vald); 
													PreSumaDebitoC1=(vald1+PreSumaDebitoC1);
													String valc=rs.getString(6);
													long valc1=Long.parseLong(valc);
													PreSumaCreditoC1=(valc1+PreSumaCreditoC1);
													String valsa=rs.getString(4);
													long valsad=Long.parseLong(valsa);
													PreSumaSAD=(valsad+PreSumaSAD);
														
												}else{
													String valdd=rs.getString(5);
													long vald2=Long.parseLong(valdd);
													PreSumaDebitoC2=(vald2+PreSumaDebitoC2);
													String valcc=rs.getString(6);
													long valc2=Long.parseLong(valcc);
													PreSumaCreditoC2=(valc2+PreSumaCreditoC2);
													String valsa2=rs.getString(4);
													long valsac=Long.parseLong(valsa2);
													PreSumaSAC=(valsac+PreSumaSAC);
												}
											}
											
										}
									
									long dif1=PreSumaDebitoC1-PreSumaCreditoC1;
									long dif2=0;
									if(PreSumaDebitoC2<PreSumaCreditoC2){
									 dif2=(-(PreSumaDebitoC2-PreSumaCreditoC2));
									}else{
										dif2=(PreSumaDebitoC2-PreSumaCreditoC2);
									}
									String PreSumaSAD1=Long.toString(PreSumaSAD);
									String PreSumaSAC1=Long.toString(PreSumaSAC);
									String PreSumaDebitoC11=Long.toString(PreSumaDebitoC1);
									String PreSumaCreditoC11=Long.toString(PreSumaCreditoC1);
									String dif11=Long.toString(dif1);
									String dif22=Long.toString(dif2);
									String PreSumaDebitoC22=Long.toString(PreSumaDebitoC2);
									String PreSumaCreditoC22=Long.toString(PreSumaCreditoC2);
									long DSA=(PreSumaSAD-PreSumaSAC);
									long DD=(PreSumaSAD-PreSumaSAC);
									long DC=(PreSumaCreditoC1-PreSumaCreditoC2);
									long DDIF=(dif1-dif2);
									String DSA1=Long.toString(DSA);
									String DD1=Long.toString(DD);
									String DC1=Long.toString(DC);
									String DDIF1=Long.toString(DDIF);
									out.print("<tr><td colspan='6'> <br><br> </td></tr>");
									out.print("<tr align='rigth' class='rep'><td colspan='2'class='contpre' ><b>TOTAL DEBITOS</b></td><td align='right'>"+FormatMoneda(PreSumaSAD1)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaDebitoC11)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaCreditoC11)+" &nbsp;</td><td align='right'>"+FormatMoneda(dif11)+" </td></tr>");
									out.print("<tr align='rigth' class='rep'><td colspan='2' class='contpre' ><b>TOTAL CREDITOS</b></td><td align='right'>"+FormatMoneda(PreSumaSAC1)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaDebitoC22)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaCreditoC22)+" &nbsp;</td><td align='right'>"+FormatMoneda(dif22)+" </td></tr>");
									out.print("<tr align='rigth' class='rep'><td colspan='2' class='contpre'><b>DIFERENCIA</b></td><td align='right'>"+FormatMoneda(DSA1)+" &nbsp;</td><td align='right'>"+FormatMoneda(DD1)+" &nbsp;</td><td align='right'>"+FormatMoneda(DC1)+" &nbsp;</td><td align='right'>"+FormatMoneda(DDIF1)+"</td></tr>");
									//out.println("<p align='left'><input type='button' value=' Generar PDF '/></p>");
									rs.getStatement().getConnection().close();
									}
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}else{
								if(cont==5){
									System.out.println("entrando a nivel 5");
									String nivel1=valorNivel[1];
									String nivel2=valorNivel[2];
									String nivel3=valorNivel[3];
									String nivel4=valorNivel[4];
									String nivel5=valorNivel[5];
									rs=mpr.BuscarDatosBalanceG5(RC1,RC2,AC,MC,nivel1,nivel2,nivel3,nivel4,nivel5,"nada","nada");
									try {
										while(rs.next()){
											cuenta=rs.getString(1);
											if(cuenta.equals("")){
												
											}else{
												rs1=mpr.BuscarCuenta(cuenta);
												while(rs1.next()){
													out.println("<tr align='left' class='rep'><td>"+cuenta+"</td><td>"+rs1.getString(3)+"</td><td align='right'>"+FormatMoneda(rs.getString(4))+" &nbsp;</td><td align='right'>"+FormatMoneda(rs.getString(5))+" &nbsp;</td><td align='right'>"+FormatMoneda(rs.getString(6))+" &nbsp;</td><td align='right'>"+FormatMoneda(rs.getString(7))+"</td>");
												}
												rs1.getStatement().getConnection().close();
											}
										}
										
										rs.getStatement().getConnection().close();
										if(nivel1.equals("1")){
										rs=mpr.BuscarDatosBalanceG1(RC1,RC2,AC,MC,nivel1,"nada","nada");
										while(rs.next()){
											cuenta=rs.getString(1);
											if(cuenta.equals("")){ }else{
													String Naturaleza=rs.getString(8);
													if(Naturaleza.equals("Debito")){
														String vald=rs.getString(5);
														long vald1=Long.parseLong(vald); 
														PreSumaDebitoC1=(vald1+PreSumaDebitoC1);
														String valc=rs.getString(6);
														long valc1=Long.parseLong(valc);
														PreSumaCreditoC1=(valc1+PreSumaCreditoC1);
														String valsa=rs.getString(4);
														long valsad=Long.parseLong(valsa);
														PreSumaSAD=(valsad+PreSumaSAD);
															
													}else{
														String valdd=rs.getString(5);
														long vald2=Long.parseLong(valdd);
														PreSumaDebitoC2=(vald2+PreSumaDebitoC2);
														String valcc=rs.getString(6);
														long valc2=Long.parseLong(valcc);
														PreSumaCreditoC2=(valc2+PreSumaCreditoC2);
														String valsa2=rs.getString(4);
														long valsac=Long.parseLong(valsa2);
														PreSumaSAC=(valsac+PreSumaSAC);
													}
												}
												
											}
										
										long dif1=PreSumaDebitoC1-PreSumaCreditoC1;
										long dif2=0;
										if(PreSumaDebitoC2<PreSumaCreditoC2){
										 dif2=(-(PreSumaDebitoC2-PreSumaCreditoC2));
										}else{
											dif2=(PreSumaDebitoC2-PreSumaCreditoC2);
										}
										String PreSumaSAD1=Long.toString(PreSumaSAD);
										String PreSumaSAC1=Long.toString(PreSumaSAC);
										String PreSumaDebitoC11=Long.toString(PreSumaDebitoC1);
										String PreSumaCreditoC11=Long.toString(PreSumaCreditoC1);
										String dif11=Long.toString(dif1);
										String dif22=Long.toString(dif2);
										String PreSumaDebitoC22=Long.toString(PreSumaDebitoC2);
										String PreSumaCreditoC22=Long.toString(PreSumaCreditoC2);
										long DSA=(PreSumaSAD-PreSumaSAC);
										long DD=(PreSumaSAD-PreSumaSAC);
										long DC=(PreSumaCreditoC1-PreSumaCreditoC2);
										long DDIF=(dif1-dif2);
										String DSA1=Long.toString(DSA);
										String DD1=Long.toString(DD);
										String DC1=Long.toString(DC);
										String DDIF1=Long.toString(DDIF);
										out.print("<tr align='rigth' class='rep'><td colspan='2'class='contpre' ><b>TOTAL DEBITOS</b></td><td align='right'>"+FormatMoneda(PreSumaSAD1)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaDebitoC11)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaCreditoC11)+" &nbsp;</td><td align='right'>"+FormatMoneda(dif11)+" </td></tr>");
										out.print("<tr align='rigth' class='rep'><td colspan='2' class='contpre' ><b>TOTAL CREDITOS</b></td><td align='right'>"+FormatMoneda(PreSumaSAC1)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaDebitoC22)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaCreditoC22)+" &nbsp;</td><td align='right'>"+FormatMoneda(dif22)+" </td></tr>");
										out.print("<tr align='rigth' class='rep'><td colspan='2' class='contpre'><b>DIFERENCIA</b></td><td align='right'>"+FormatMoneda(DSA1)+" &nbsp;</td><td align='right'>"+FormatMoneda(DD1)+" &nbsp;</td><td align='right'>"+FormatMoneda(DC1)+" &nbsp;</td><td align='right'>"+FormatMoneda(DDIF1)+" </td></tr>");
										//out.println("<p align='left'><input type='button' value=' Generar PDF '/></p>");
										rs.getStatement().getConnection().close();
										}
									} catch (SQLException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							}
						}
					}
				}
			}
			
			/*for (int i = 0; i < detnivelSeleccionado.length; i++) {
				System.out.println("entrando al para"+detnivelSeleccionado[i]);
				if(detnivelSeleccionado[i].equals("1")){
					rs=mpr.BuscarDatosBalance(RC1,RC2,AC,MC,1);
					try {
						while(rs.next()){
						cuenta=rs.getString(1);
						System.out.println(cuenta);
						rs1=mpr.BuscarCuenta(cuenta);
							if(rs1.next()){
								out.print("<tr><td>"+cuenta+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td><td>"+rs.getString(6)+"</td><td>"+rs.getString(7)+"</td>");
							}
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}*/
			System.out.println("cadena de consulta"+sql);
			
			
		}
		
		
		if(va.equals("RepAuxiliarGeneral")){
			
			String RC1=req.getParameter("RC1");
			String RC2=req.getParameter("RC2");
			String MC=req.getParameter("MC");
			String AC=req.getParameter("AC");
			String TDOC=req.getParameter("TDOC");
			String MC2=req.getParameter("MC2");
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
			java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());
			out.print("<br><br><table width='80%'>");
			rs=mpr.BuscarEmpresa();
			try {
				while(rs.next()){
				out.println("<tr class='contpre'><td align='center' colspan='11'>"+rs.getString(1)+"</td></tr>");
				out.println("<tr class='contpre'><td align='center' colspan='11'>"+rs.getString(2)+"<br></td></tr>");
				
				}rs.getStatement().getConnection().close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			out.print("<tr class='contpre'><td colspan='7' align='left'>AUXILIAR DE CONTABILIDAD GENERAL</td><td colspan='2' align='right'>Fecha de Generacion:</td><td colspan='2'>"+Fecha+"</td></tr>");
			out.print("<tr class='contpre'><td colspan='7' align='left'>CORRESPONDIENTE A PERIODO "+MC+" AL "+MC2+" DEL A�O "+AC+"</td><td colspan='2' align='rigth'>&nbsp; Hora de Generacion:</td><td colspan='2'>"+Hora+"</td></tr>");
			out.print("<tr><td colspan='11'><hr></td></tr>");
			out.print("<tr class='contpre' ><br><br><b><td>&nbsp; FECHA &nbsp;</td><td>&nbsp; TIPO DOC. &nbsp;</td><td>&nbsp; SUCURSAL &nbsp;</td><td>&nbsp; C. COSTO &nbsp;</td><td>&nbsp; SUB. C. COSTO &nbsp;</td><td>&nbsp; TERCERO &nbsp;</td><td>&nbsp; DESCRIPCION DEL DOCUMENTO &nbsp;</td><td>&nbsp; DOC. REF &nbsp;</td><td>&nbsp; DEBITOS &nbsp;</td><td>&nbsp; CREDITOS &nbsp;</td><td>&nbsp; PARCIAL &nbsp;</td></b></tr>");
			out.print("<tr><td colspan='11'><hr></td></tr>");
			rs=mpr.BuscarSaldoAnt(RC1,RC2,AC,MC,MC2,TDOC); 
			String cuenta="";
			long sa=0;
			long parcial=0;
			long sumdeb=0;
			long sumcre=0;
			try {
				while(rs.next()){
					System.out.println(rs.getString(1));
					out.println("<tr><td colspan='11'><br></td></tr>");
					out.print("<tr class='contpre' ><b><td colspan='2' align='left'>CUENTA : </td><td colspan='2' align='left'>"+rs.getString(2)+"</td><td colspan='2' align='center'>"+rs.getString(3)+"</td><td colspan='2' align='right'>SALDO ANTERIOR :</td><td colspan='4' align='right'>"+FormatMoneda(rs.getString(4))+"</td></b><tr>");
					out.println("<tr><td colspan='11'><br></td></tr>");
					cuenta=rs.getString(1);
					rs1=mpr.BuscarCuentaAuxDet(cuenta,AC,MC,MC2,TDOC);
					sa=rs.getLong(4);
					sumdeb=0;
					sumcre=0;
					int p=0;
					while(rs1.next()){
						String val=rs1.getString(11);
						System.out.println(" val "+val);
						if(val.equals("Debito")){
							if(p==0){
								parcial=(sa+(rs1.getLong(9)));
								p=1;
								}else{
								parcial=(parcial+(rs1.getLong(9)));
								}
								System.out.println("valor p"+p);
							System.out.println("Resultado de la suma con debito"+parcial);
							parcial=(parcial-(rs1.getLong(10)));
							System.out.println("Resultado de la resta con credito "+parcial);
						}else{
							if(p==0){
								parcial=(sa+(rs1.getLong(10)));
								p=1;
								}else{
								parcial=(parcial+(rs1.getLong(10)));
								}
							System.out.println("Resultado de la suma con credito"+parcial);
							parcial=(parcial-(rs1.getLong(9)));
							System.out.println("Resultado de la resta con debito "+parcial);
						}
						
						sumdeb=((rs1.getLong(9))+sumdeb);
						sumcre=((rs1.getLong(10))+sumcre);
						System.out.print("VALOR  DE PARCIAL EN LONG "+parcial);
						String parcial2=Long.toString(parcial);
						System.out.print("VALOR  DE PARCIAL EN String "+parcial2);
						
						out.print("<tr class='rep'><td>"+rs1.getString(1)+"</td><td>"+rs1.getString(2)+"</td><td>"+rs1.getString(3)+"</td><td>"+rs1.getString(4)+"</td><td>"+rs1.getString(5)+"</td><td>"+rs1.getString(6)+"</td><td>"+rs1.getString(7)+"</td><td>"+rs1.getString(8)+"</td><td>"+FormatMoneda(rs1.getString(9))+"</td><td>"+FormatMoneda(rs1.getString(10))+"</td><td>"+FormatMoneda(parcial2)+"</td></tr>");
						
					}
					rs1.getStatement().getConnection().close();
					String sumdeb1=Long.toString(sumdeb);
					String sumcre1=Long.toString(sumcre);
					if((sumdeb!=0)||(sumcre!=0)){
						out.print("<tr><td colspan='8'></td><td colspan='3'> <hr></td>");
						out.print("<tr class='rep' ><b><td colspan='8'></td><td colspan='1' align='center'> "+FormatMoneda(sumdeb1)+" </td><td colspan='1' align='center'> "+FormatMoneda(sumcre1)+" </td><td colspan='1' align='center'> "+FormatMoneda(rs.getString(5))+" </td></b></tr>");
					}
				}rs.getStatement().getConnection().close();
				
				out.print("</table>");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
		
		
		
		if(va.equals("RepAuxiliarSucyCcosto")){
			System.out.println("RepAuxiliarSucyCosto");
			String RC1=req.getParameter("RC1");
			String RC2=req.getParameter("RC2");
			String MC=req.getParameter("MC");
			String AC=req.getParameter("AC");
			String TDOC=req.getParameter("TDOC");
			String MC2=req.getParameter("MC2");
			String Suc=req.getParameter("Suc");
			String tiporepor=req.getParameter("tiporepor");
			String ccosto=req.getParameter("ccosto");
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
			java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());
			out.print("<table>");
			if(tiporepor.equals("1")){
							System.out.println("entrando a opcion 1");
							System.out.println("Valor de Suc "+Suc+" Valor de ccosto "+ccosto);
								out.print("<br><br><table width='80%'>");
								rs=mpr.BuscarEmpresa();
								try {
									while(rs.next()){
									out.println("<tr class='contpre'><td align='center' colspan='11'>"+rs.getString(1)+"</td></tr>");
									out.println("<tr class='contpre'><td align='center' colspan='11'>"+rs.getString(2)+"<br></td></tr>");
									
									}rs.getStatement().getConnection().close();
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								out.print("<tr class='contpre'><td colspan='7' align='left'>AUXILIAR DE CONTABILIDAD POR SUCURSAL Y CUENTA </td><td colspan='2' align='right'>Fecha de Generacion:</td><td colspan='2'>"+Fecha+"</td></tr>");
								out.print("<tr class='contpre'><td colspan='7' align='left'>CORRESPONDIENTE A PERIODO "+MC+" AL "+MC2+" DEL A�O "+AC+"</td><td colspan='2' align='rigth'>&nbsp; Hora de Generacion:</td><td colspan='2'>"+Hora+"</td></tr>");
								out.print("<tr><td colspan='11'><hr></td></tr>");
								out.print("<tr class='contpre' ><br><br><b><td>&nbsp; FECHA &nbsp;</td><td>&nbsp; TIPO DOC. &nbsp;</td><td>&nbsp; SUCURSAL &nbsp;</td><td>&nbsp; C. COSTO &nbsp;</td><td>&nbsp; SUB. C. COSTO &nbsp;</td><td>&nbsp; TERCERO &nbsp;</td><td>&nbsp; DESCRIPCION DEL DOCUMENTO &nbsp;</td><td>&nbsp; DOC. REF &nbsp;</td><td>&nbsp; DEBITOS &nbsp;</td><td>&nbsp; CREDITOS &nbsp;</td><td>&nbsp; PARCIAL &nbsp;</td></b></tr>");
								out.print("<tr><td colspan='11'><hr></td></tr>");
								rs4=mpr.BuscarNombreSuc(Suc);
								try {
								while(rs4.next()){
									out.print("<tr class='contpre' ><td colspan='3'> SUCURSAL No. : </td><td colspan='5'>"+rs4.getString(2)+"</td></tr>");
									Suc=rs4.getString(1);
								
										rs=mpr.BuscarSaldoAntSucCost(RC1, RC2, AC, MC, MC2, TDOC, Suc, ccosto); 
										String cuenta="";
										long sa=0;
										long parcial=0;
										long sumdeb=0;
										long sumcre=0;
									
											while(rs.next()){
												//System.out.println(rs.getString(1));
												cuenta=rs.getString(1);
												rs1=mpr.BuscarCuentaAuxDetSucyCcosto(cuenta,AC,MC,MC2,TDOC,Suc,ccosto);
												int vali=0;
												
												if(rs1.next()){
													vali=1;
												}rs1.getStatement().getConnection().close();
												if(vali!=0){
												out.println("<tr><td colspan='11'><br></td></tr>");
												out.print("<tr class='contpre' ><b><td colspan='2' align='left'><br>CUENTA : </td><td colspan='2' align='left'><br>"+rs.getString(2)+"</td><td colspan='2' align='center'><br>"+rs.getString(3)+"</td><td colspan='2' align='right'><br>SALDO ANTERIOR :</td><td colspan='4' align='right'><br>"+FormatMoneda(rs.getString(4))+"</td></b><tr>");
												out.println("<tr><td colspan='11'><br></td></tr>");
												}
												rs1=mpr.BuscarCuentaAuxDetSucyCcosto(cuenta,AC,MC,MC2,TDOC,Suc,ccosto);
												sa=rs.getLong(4);
												sumdeb=0;
												sumcre=0;
												int p=0;
												while(rs1.next()){
													String val=rs1.getString(11);
												//	System.out.println(" val "+val);
													if(val.equals("Debito")){
														if(p==0){
															parcial=(sa+(rs1.getLong(9)));
															p=1;
															}else{
															parcial=(parcial+(rs1.getLong(9)));
															}
															System.out.println("valor p"+p);
														//System.out.println("Resultado de la suma con debito"+parcial);
														parcial=(parcial-(rs1.getLong(10)));
														//System.out.println("Resultado de la resta con credito "+parcial);
													}else{
														if(p==0){
															parcial=(sa+(rs1.getLong(10)));
															p=1;
															}else{
															parcial=(parcial+(rs1.getLong(10)));
															}
														//System.out.println("Resultado de la suma con credito"+parcial);
														parcial=(parcial-(rs1.getLong(9)));
														//System.out.println("Resultado de la resta con debito "+parcial);
													}
													
													sumdeb=((rs1.getLong(9))+sumdeb);
													sumcre=((rs1.getLong(10))+sumcre);
													//System.out.print("VALOR  DE PARCIAL EN LONG "+parcial);
													String parcial2=Long.toString(parcial);
												//	System.out.print("VALOR  DE PARCIAL EN String "+parcial2);
													
													out.print("<tr class='rep'><td>"+rs1.getString(1)+"</td><td>"+rs1.getString(2)+"</td><td>"+rs1.getString(3)+"</td><td>"+rs1.getString(4)+"</td><td>"+rs1.getString(5)+"</td><td>"+rs1.getString(6)+"</td><td>"+rs1.getString(7)+"</td><td>"+rs1.getString(8)+"</td><td>"+FormatMoneda(rs1.getString(9))+"</td><td>"+FormatMoneda(rs1.getString(10))+"</td><td>"+FormatMoneda(parcial2)+"</td></tr>");
													
												}
												rs1.getStatement().getConnection().close();
												String sumdeb1=Long.toString(sumdeb);
												String sumcre1=Long.toString(sumcre);
												if((sumdeb!=0)||(sumcre!=0)){
													if(vali!=0){
													out.print("<tr><td colspan='8'></td><td colspan='3'> <hr></td>");
													out.print("<tr class='rep' ><b><td colspan='8'></td><td colspan='1' align='center'> "+FormatMoneda(sumdeb1)+" </td><td colspan='1' align='center'> "+FormatMoneda(sumcre1)+" </td><td colspan='1' align='center'> "+FormatMoneda(rs.getString(5))+" </td></b></tr>");
												}
												}
											}rs.getStatement().getConnection().close();
											
											
								}rs4.getStatement().getConnection().close();
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
									
			}else{
				
				out.print("<br><br><table width='80%'>");
				rs=mpr.BuscarEmpresa();
				try {
					while(rs.next()){
					out.println("<tr class='contpre'><td align='center' colspan='11'>"+rs.getString(1)+"</td></tr>");
					out.println("<tr class='contpre'><td align='center' colspan='11'>"+rs.getString(2)+"<br></td></tr>");
					
					}rs.getStatement().getConnection().close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				out.print("<tr class='contpre'><td colspan='7' align='left'>AUXILIAR DE CONTABILIDAD POR SUCURSAL - CENTRO DE COSTO Y CUENTA  </td><td colspan='2' align='right'>Fecha de Generacion:</td><td colspan='2'>"+Fecha+"</td></tr>");
				out.print("<tr class='contpre'><td colspan='7' align='left'>CORRESPONDIENTE A PERIODO "+MC+" AL "+MC2+" DEL A�O "+AC+"</td><td colspan='2' align='rigth'>&nbsp; Hora de Generacion:</td><td colspan='2'>"+Hora+"</td></tr>");
				out.print("<tr><td colspan='11'><hr></td></tr>");
				out.print("<tr class='contpre' ><br><br><b><td>&nbsp; FECHA &nbsp;</td><td>&nbsp; TIPO DOC. &nbsp;</td><td>&nbsp; SUCURSAL &nbsp;</td><td>&nbsp; C. COSTO &nbsp;</td><td>&nbsp; SUB. C. COSTO &nbsp;</td><td>&nbsp; TERCERO &nbsp;</td><td>&nbsp; DESCRIPCION DEL DOCUMENTO &nbsp;</td><td>&nbsp; DOC. REF &nbsp;</td><td>&nbsp; DEBITOS &nbsp;</td><td>&nbsp; CREDITOS &nbsp;</td><td>&nbsp; PARCIAL &nbsp;</td></b></tr>");
				out.print("<tr><td colspan='11'><hr></td></tr>");
				rs4=mpr.BuscarNombreSuc(Suc);
				
				try {
				while(rs4.next()){
					out.print("<tr class='contpre' ><td> SUCURSAL No. : </td><td>"+rs4.getString(2)+"</td></tr>");
					Suc=rs4.getString(1);
					String centrocosto="";
					System.out.println("valor de ccosto"+ccosto+" final");
					rs3=mpr.BuscarNombreCentroCosto(ccosto);
					while(rs3.next()){
						System.out.println("entrando a buscar centro de costo ");
						int vali=0;
						centrocosto=rs3.getString(1);
					
						
						
						
									rs=mpr.BuscarSaldoAntSucCost(RC1, RC2, AC, MC, MC2, TDOC, Suc, centrocosto); 
									String cuenta="";
									long sa=0;
									long parcial=0;
									long sumdeb=0;
									long sumcre=0;
								
										while(rs.next()){
											System.out.println("cuenta "+rs.getString(1));
											cuenta=rs.getString(1);
											rs1=mpr.BuscarCuentaAuxDetSucyCcosto(cuenta,AC,MC,MC2,TDOC,Suc,centrocosto);
											int vali2=0;
											if(rs1.next()){
												vali2=1;
											}
											if(vali2!=0){
											out.print("<tr class='contpre' ><td colspan='4'><br> CENTRO DE COSTO  No. : </td><td colspan='3'><br>"+rs3.getString(2)+"</td></tr>");
											out.println("<tr><td colspan='11'><br></td></tr>");
											out.print("<tr class='contpre' ><b><td colspan='2' align='left'><br>CUENTA : </td><td colspan='2' align='left'><br>"+rs.getString(2)+"</td><td colspan='2' align='center'><br>"+rs.getString(3)+"</td><td colspan='2' align='right'><br>SALDO ANTERIOR :</td><td colspan='4' align='right'><br>"+FormatMoneda(rs.getString(4))+"</td></b><tr>");
											out.println("<tr><td colspan='11'><br></td></tr>");
											}
											rs1=mpr.BuscarCuentaAuxDetSucyCcosto(cuenta,AC,MC,MC2,TDOC,Suc,centrocosto);
											sa=rs.getLong(4);
											sumdeb=0;
											sumcre=0;
											int p=0;
											while(rs1.next()){
												String val=rs1.getString(11);
												System.out.println(" val "+val);
												if(val.equals("Debito")){
													if(p==0){
														parcial=(sa+(rs1.getLong(9)));
														p=1;
														}else{
														parcial=(parcial+(rs1.getLong(9)));
														}
														System.out.println("valor p"+p);
													System.out.println("Resultado de la suma con debito"+parcial);
													parcial=(parcial-(rs1.getLong(10)));
													System.out.println("Resultado de la resta con credito "+parcial);
												}else{
													if(p==0){
														parcial=(sa+(rs1.getLong(10)));
														p=1;
														}else{
														parcial=(parcial+(rs1.getLong(10)));
														}
													System.out.println("Resultado de la suma con credito"+parcial);
													parcial=(parcial-(rs1.getLong(9)));
													System.out.println("Resultado de la resta con debito "+parcial);
												}
												
												sumdeb=((rs1.getLong(9))+sumdeb);
												sumcre=((rs1.getLong(10))+sumcre);
												System.out.print("VALOR  DE PARCIAL EN LONG "+parcial);
												String parcial2=Long.toString(parcial);
												System.out.print("VALOR  DE PARCIAL EN String "+parcial2);
												
												out.print("<tr class='rep'><td>"+rs1.getString(1)+"</td><td>"+rs1.getString(2)+"</td><td>"+rs1.getString(3)+"</td><td>"+rs1.getString(4)+"</td><td>"+rs1.getString(5)+"</td><td>"+rs1.getString(6)+"</td><td>"+rs1.getString(7)+"</td><td>"+rs1.getString(8)+"</td><td>"+FormatMoneda(rs1.getString(9))+"</td><td>"+FormatMoneda(rs1.getString(10))+"</td><td>"+FormatMoneda(parcial2)+"</td></tr>");
												
											}
											rs1.getStatement().getConnection().close();
											String sumdeb1=Long.toString(sumdeb);
											String sumcre1=Long.toString(sumcre);
											if((sumdeb!=0)||(sumcre!=0)){
												out.print("<tr><td colspan='8'></td><td colspan='3'> <hr></td>");
												out.print("<tr class='rep' ><b><td colspan='8'></td><td colspan='1' align='center'> "+FormatMoneda(sumdeb1)+" </td><td colspan='1' align='center'> "+FormatMoneda(sumcre1)+" </td><td colspan='1' align='center'> "+FormatMoneda(rs.getString(5))+" </td></b></tr>");
											}
										}rs.getStatement().getConnection().close();
										
										
										
					}rs3.getStatement().getConnection().close();
										
				}rs4.getStatement().getConnection().close();
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
			out.print("</table>");
			
			
	}//fin RepAuxiliarSucyCcosto

		
		
	if(va.equals("CompDiario")){
		String AC=req.getParameter("AC");
		String MC=req.getParameter("MC");
		String RDiaU=req.getParameter("RDiaU");
		String RDiaD=req.getParameter("RDiaD");
		String TDOC=req.getParameter("TDOC");
		String Fechai=AC+"-"+MC+"-"+RDiaU;
		String Fechaf=AC+"-"+MC+"-"+RDiaD;
		java.util.Date fechaActual = new java.util.Date();
		java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
		java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());
		
		out.println("<table  width='90%' align='center' >");
		rs=mpr.BuscarEmpresa();
		try {
			while(rs.next()){
				/*PENDIENTE AVERIGUAR COMO MUESTRA LA  IMAGEN DE BD*/
				out.println("<tr align='left' class='contpre'><td colspan='2'>"+rs.getString(1)+"</td><td colspan='2' align='right'>FECHA : "+Fecha+"</td></tr>");
				out.println("<tr align='left' class='contpre'><td colspan='2'>"+rs.getString(2)+"</td><td colspan='2' align='right'>HORA  : "+Hora+"</td></tr>");
				out.println("<tr align='left' class='contpre'><td colspan='2'>COMPROBANTE DE DIARIO GENERAL <br></td><td colspan='2' align='right'>CORRESPONDIENTE A "+Fechai+" al "+Fechaf+"<br></td></tr>");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.print("<tr><td colspan='4'><hr></td></tr>");
		out.println("<tr class='rep'><td><b>&nbsp; <br>CUENTA &nbsp; </b></td><td><b>&nbsp; <br>NOMBRE CUENTA &nbsp; </b></td><td><b>&nbsp; <br>DEBITOS &nbsp; </b></td><td><b> &nbsp; <br>CREDITOS &nbsp; </b></td></tr>");
		out.print("<tr><td colspan='4'><hr></td></tr>");
		rs=mpr.AgrupadoPorFecha(Fechai,Fechaf,TDOC);
		long SumTdeb=0;
		long SumTcred=0;
		try {
			while(rs.next()){
				out.println("<tr class='contpre'  align='left'><td colspan='4'><br><b>FECHA : </b>"+rs.getString(1)+"</td></tr>");
				rs1=mpr.AgrupadoPorTdoc(rs.getString(1),TDOC);
				while(rs1.next()){
					out.println("<tr class='contpre' align='left'><td colspan='4'><br><b>TIPO No. : </b>"+rs1.getString(1)+" - "+rs1.getString(2)+"<br></td></tr>");
					rs2=mpr.AgrupadoPorCuenta(rs1.getString(1),rs.getString(1));
					long SumDeb=0;
					long SumCred=0;
					while(rs2.next()){
						out.println("<tr class='rep'><td>"+rs2.getString(1)+"</td><td align='left'>"+rs2.getString(2)+"</td><td>"+rs2.getString(3)+"</td><td>"+rs2.getString(4)+"</td></tr>");
						SumDeb=rs2.getLong(3)+SumDeb;
						SumCred=rs2.getLong(4)+SumCred;
					}rs2.getStatement().getConnection().close();
					out.println("<tr class='contpre'><td colspan='2' align='right'><b>TOTAL TIPO DOCUMENTO : </b></td><td>"+SumDeb+"</td><td>"+SumCred+"</td></tr>");
				}
				out.println("<tr class='contpre' ><td colspan='2' align='right'><b> TOTAL DIA: </b></td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td></tr>");
				SumTdeb=rs.getLong(2)+SumTdeb;
				SumTcred=rs.getLong(3)+SumTcred;
				
			}rs.getStatement().getConnection().close();
			out.println("<tr><td><br><br></td></tr>");
			out.println("<tr class='contpre'><td colspan='2' align='right' ><b>TOTAL GENERAL : </b></td><td>"+SumTdeb+"</td><td>"+SumTcred+"</td></tr>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		out.println("</table>");
		
		
	}
		
		
		
		
	}
			
		
	
	
	
	
	
/********************************************/
	public String FormatMoneda(String valor){		
		String temp2="";String temp1="";String temp3="";
		int ud=1;int logCad = valor.length();		
		int j=0;
		for (int i=logCad-1;i>=0;i--){			
			temp2=valor.substring(i,i+1);
			temp2+=temp1;
			//System.out.println("valor de i en fuera de condional "+i);
			if(ud==3){
				if(i!=0){
					if(valor.contains("-")){
						int resul=(logCad%2);
						//System.out.println("resul "+resul);
						if((logCad%2)==0){
							temp1="."+temp2;
						}else{
						//	System.out.println("Valor de i en condicional contains"+i);
							if(i==1){
								temp1=temp2;
								//System.out.println(" prueba");
								
							}else{
								temp1="."+temp2;
							}
							
						}
					/*if(temp2.contains("-")){
						temp1=temp2;*/
									
					}else{
						temp1="."+temp2;
					}
					//System.out.println("valor de i en condicional "+i);
					/*if(temp2.contains("-")){
						temp1=temp2;
					}
					System.out.println("valor de temp1 "+temp1);
					System.out.println("valor de temp2 "+temp2);
					System.out.println("valor de i en condicional "+i);
					temp1="."+temp2;*/
				}else{
					temp1=temp2;
					
					}
				ud=0;
			}else{
				
				temp1=temp2;
				//j=1;
				}
			//System.out.println("valor de temp1 "+temp1);
			//System.out.println("valor de temp2 "+temp2);
			ud++;
		}
		temp1=temp1+",00";
		return temp1;
	}
	
	/****************************/



	
}
