package hic_controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.*; //para llamar la librer�a

import java.io.PrintWriter;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.io.*;

import javax.servlet.http.HttpSession;

//import fact_metodo.MetodoMovimientos;
import hic_metodo.MetodoCargue;
/**
 * Servlet implementation class ControlCargue
 */
public class ControlCargue extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(true);
		
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out=resp.getWriter();
		
		MetodoCargue mc = new MetodoCargue();
		
		String va = req.getParameter("valor");
		String codp=req.getParameter("codp");
		String nomp=req.getParameter("nomp");
		String codr=req.getParameter("codr");
		String clases=req.getParameter("clases");
		String fechamo=req.getParameter("fechamo");
		String texto=req.getParameter("texto");
		String xx=req.getParameter("xx");
		String taqx=req.getParameter("taqx");
		String aq=req.getParameter("aq");
		String adm=req.getParameter("adm");
		String enca=req.getParameter("enca");
		String ent=req.getParameter("ent");
		String pop=req.getParameter("pop");
		String user=req.getParameter("user");
		String valorp=req.getParameter("valorp");
		String scc=req.getParameter("scc");
		String ccx=req.getParameter("ccx");
		String hi=req.getParameter("h0");
		String hf=req.getParameter("h1");
		String anes=req.getParameter("anes");
		String ayuda=req.getParameter("ayuda");
		String instru=req.getParameter("instru");
		String tejido=req.getParameter("tejido");
		String DQX=req.getParameter("DQX");
		String tdx=req.getParameter("tdx");
		String cdpre=req.getParameter("cdpre");
		String ndpre=req.getParameter("ndpre");
		String cdpost=req.getParameter("cdpost");
		String ndpost=req.getParameter("ndpost");
		String cx=req.getParameter("cx");
		String cx2=req.getParameter("cx2");
		String ane=req.getParameter("ane");
		String vayuda=req.getParameter("vayuda");
		
		
		
		
		
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		ResultSet rs3=null;
		
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
		
		String fechacjmysql=annio+"-"+mes+"-"+dia;
		String fechacj=dia+"/"+mes+"/"+annio;
		
		Calendar calendario = Calendar.getInstance();
		//	Calendar calendario = new GregorianCalendar();
			int hora, minutos, segundos;
			hora =calendario.get(Calendar.HOUR_OF_DAY);
			minutos = calendario.get(Calendar.MINUTE);
			segundos = calendario.get(Calendar.SECOND);
			String hra= hora+":"+minutos+":"+segundos;
		
		
		
		if(va.equals("1")){
			
				//<tr  BGCOLOR='#D3D3D3'><td colspan='6' >
			
			
			out.print("<table width='100%' class='style6'><tr><td><div align='center' class='style11' id='cabecera2'>Descripcion Quirurgica </div></td></tr>");
			out.print("<tr></tr><tr  BGCOLOR='#D3D3D3'><td colspan='4' ><div align='center'><a href='#' onclick='DP("+adm+")'>DATOS PRINCIPALES</div></td></tr>");
			
			
			out.print("<table width='100%' border='1' class='style6'> ");
			rs=mc.listarDescripciones(adm,user);
			int sw=0;
			try {
				if(rs.next()){////////////////////
				sw=1;
				
				/***Datos principales**/
				out.print("<tr><td><div id='dDP'></div></td></tr>");
				out.print("<input type='hidden' id='DQX' value='"+rs.getString(1)+"' />");
				out.print("<input type='hidden' id='ayuda' value='"+rs.getString(2)+"' />");
				out.print("<input type='hidden' id='frdqx' value='"+rs.getString(7)+"' />");
				
				//out.print("<tr><td></td><td></td><td></td><td></td><td></td><td></td></tr>");
				
				/***Fin Datos principales**/
				
				//out.print("</table>");	
				
				
				out.print("<table width='100%' border='1' class='style6'> ");
				out.print("<tr></tr><tr></tr><tr  BGCOLOR='#D3D3D3'><td colspan='4' ><div align='center'><a href='#' onclick='DG("+adm+")'>DIAGNOSTICOS</div></td></tr>");
				
				/***Diagnosticos **/
				out.print("<tr><td><div id='dDG'></div></td></tr>");
				
				/***Fin Diagnosticos **/
				
				
				
				
				out.print("<tr></tr><tr></tr><tr  BGCOLOR='#D3D3D3'><td colspan='4' ><div align='center'><a href='#' onclick='PQ("+adm+","+enca+")'>PROCEDIMIENTO(S) QUIRURGICO(S) Y PERSONAL QUE INTERVIENE: </div></td></tr>");
				/***PROCEDIMIENTO **/
				out.print("<tr><td td colspan='4' ><div id='dPQ'></div></td></tr>");
				/***Fin PROCEDIMIENTO **/
				
				
				
				/////////////////////////////////////////////////////////////
				
				
				out.print("<tr></tr><tr></tr><tr  BGCOLOR='#D3D3D3'><td colspan='4' ><div align='center'><a href='#' onclick='HD("+adm+","+enca+")'>HALLAZGOS / DESCRIPCION QUIRURGICA </div></td></tr>");
				/***Hallazgos **/
				out.print("<tr><td td colspan='4' ><div id='dHD'></div></td></tr>");
				
				/***Fin Hallazgos **/
			
					
				out.print("</table> ");
				
								
				}////////////////////
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			
			if(sw==0){
				out.print("<tr><td>Fecha Procedimiento:</td><td><input type=text id='fechamo' size='7%' onKeyup='masca(this,patron,true,&quot;"+dia+"&quot;,&quot;"+mes+"&quot;,&quot;"+annio+"&quot;)' value='"+fechacj+"' ></td><td>Hora Inicio: (hh:mm AM/PM)</td><td><input type='text' id='h0' size='30' onKeyup='hor(this,patron,true)' onBlur='vhor(this,0)' /></td><td>Hora Fin: (hh:mm AM/PM)</td><td><input  type='text' id='h1' size='30'   onKeyup='hor(this,patron,true)' onBlur='vhor(this,1)' ></td></tr>");
				out.print("<tr><td>Tipo de Anestesia: </td><td><select  style='width:150px' id='anes' ><option value='Seleccione'>Seleccione</option>" );
				rs1=mc.listarTipoAnestesia();
				try {
					while(rs1.next()){
						out.print("<option  title='"+rs1.getString(2)+"'  value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
					}
					rs1.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				out.print("</select></td>");	
				out.print("<td>Instrumentador(a):</td><td><input name='txtOrden' type='text' id='instru' size='30'></td><td>Tejido enviado a Patologia:</td><td><input type='text' id='tejido' value='N/A' size='30'/></td></tr>");
				out.print("</table>");
				out.print("<table width='100%' border='1' class='style6'> ");
				//out.print("<tr><td>Tejido enviado a Patologia: <input name='txtOrden' type='text' id='tejido' size='150' value='N/A'/></td></tr>");
				out.print("<tr><td><div id=dx align='center'><input type='button' id='btnCrearEntidad' value='Diagnosticos' onclick='Dx("+enca+")'/></div></td></tr></table> ");
			}
			
			int s=0;
			rs1=mc.listarDescripcionesGuardadas(adm);
			try {
				while(rs1.next()){
					if(s==0){
						out.print("<table width='100%' border='0' class='style6'> ");	
						out.print("<tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr><td colspan='4' ><div align='center' class='style11' id='cabecera2'>DESCRIPCIONES QUIRURGICAS ANTERIORES</div></td></tr>");	
					}
					s++;
					out.print("<tr><td><a href='#' onclick='MDQA("+rs1.getString(1)+")'>"+rs1.getString(2)+" - "+rs1.getString(3)+" "+rs1.getString(4)+" "+rs1.getString(5)+"</td></tr>");
				}
				out.print("</table> ");
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			
			
		}
			
		if(va.equals("1.1")){
						
			String di=fechamo.substring(0, 2);
			String me=fechamo.substring(3, 5);
			String a=fechamo.substring(6, 10);
			String f=a+"-"+me+"-"+di;	
			
			mc.CrearDescripcionQx(instru,anes,hi,hf,f,tejido,adm,enca,codp,user,fechacjmysql,hra);
		}
			
		if(va.equals("1.2")){
			if(tdx.equals("1")){
				mc.InsertarDx(cdpre,ndpre,"0",DQX);
			}
			if(tdx.equals("2")){
				mc.InsertarDx(cdpost,ndpost,"1",DQX);
			}
		}
		
			
		if(va.equals("1.3")){
		//	System.out.println("LLEGAMOS AL 1.3 "+tdx);
			if(tdx.equals("1")){
				/////////Validar si hay un usuario con identificador 0////////
				int swcx=0;
				rs1=mc.CxsinAsignar(DQX);
				try {
					if(rs1.next()){
					 swcx=1;
					}
					rs1.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				
				if(swcx==0){mc.InsertarCx(cdpre,ndpre,"0",DQX);}else{
				
				int swcx2=0;
				rs1=mc.Cx2sinAsignar(DQX);
				try {
					if(rs1.next()){
					 swcx2=1;
					}
					rs1.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				
				if(swcx2==0){mc.InsertarCx(cdpre,ndpre,"1",DQX);}
				}
			}
			if(tdx.equals("2")){
				
				int swcx3=0;
				rs1=mc.Cx3sinAsignar(DQX);
				try {
					if(rs1.next()){
					 swcx3=1;
					}
					rs1.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				
				if(swcx3==0){mc.InsertarCx(cdpost,ndpost,"2",DQX);}
			}
		}
		
		if(va.equals("0.1")){
			out.print("<table width='100%' border='1' class='style6'> ");
			//out.print("<tr><td>Fecha Procedimiento:</td><td><input type='hidden' id='DQX' value='cesar' /><input type=text id='fechamo' size='7%'   value='aaaaaaaaa'   disabled='true' ></td><td>Hora Inicio:</td><td><input type='text' id='h0' size='30' onKeyup='hor(this,patron,true)' onBlur='vhor(this,0)'  value='dddddddd'   disabled='true' /></td><td>Hora Fin:</td><td><input  type='text' id='h1' size='30'   onKeyup='hor(this,patron,true)' onBlur='vhor(this,1)'  value='rrrrrrrrrrrrr'  disabled='true' ></td></tr>");
			
			
			rs=mc.listarDescripciones(adm,user);
			int sw=0;
			try {
				if(rs.next()){//////
					//out.print("<option  title='"+rs1.getString(2)+"'  value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
					out.print("<tr><td>Fecha Procedimiento:</td><td><input type='hidden' id='DQX' value='"+rs.getString(1)+"' /><input type=text id='fechamo' size='7%' onKeyup='masca(this,patron,true,&quot;"+dia+"&quot;,&quot;"+mes+"&quot;,&quot;"+annio+"&quot;)'  value='"+rs.getString(7)+"'   disabled='true' ></td><td>Hora Inicio: (hh:mm AM/PM)</td><td><input type='text' id='h0' size='30' onKeyup='hor(this,patron,true)' onBlur='vhor(this,0)'  value='"+rs.getString(5)+"'   disabled='true' /></td><td>Hora Fin: (hh:mm AM/PM)</td><td><input  type='text' id='h1' size='30'   onKeyup='hor(this,patron,true)' onBlur='vhor(this,1)'  value='"+rs.getString(6)+"'  disabled='true' ></td></tr>");
					out.print("<tr><td>Tipo de Anestesia: </td><td><select  style='width:150px' id='anes'  disabled='true' >" );
					rs1=mc.listarTipoAnestesia(rs.getString(4));
					try {
						if(rs1.next()){
							out.print("<option  title='"+rs1.getString(2)+"'  value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
						}
						rs1.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					out.print("</select></td>");	
					out.print("<td>Instrumentador(a):</td><td><input name='txtOrden' type='text' id='instru' size='30' value='"+rs.getString(3)+"'  disabled='true' ></td><td>Tejido enviado a Patologia:</td><td><input type='text' id='tejido' size='30'  value='"+rs.getString(8)+"' disabled='true' /></td></tr>");
					out.print("</table>");
				/*	out.print("<table width='100%' border='1' class='style6'> ");
					out.print("<tr><td>Tejido enviado a Patologia: <input name='txtOrden' type='text' id='tejido' size='150'  value='"+rs.getString(8)+"'  disabled='true' /></td></tr>");
					out.print("</table>");	
				*/	}////////////////////
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			
		}
		
		
		
		if(va.equals("0.2")){
			
		out.print("<table width='100%' border='1' class='style6'> ");	
		out.print("<tr><td>Dx Preoperatorio:</td><td><input type='hidden' id='txtCodDiagnosticoRela1' /><input type='text' id='txtNomDiagnosRela1' size='50' onkeyup='autocompletarCIE10DiagRela1()'  /><input type='button' id='btnCrearEntidad' value='>>' onclick='ADx(1,"+adm+","+enca+")'/></td><td>Dx Postoperatorio:</td><td><input type='hidden' id='txtCodDiagnosticoRela2' /><input name='txtOrden' type='text' id='txtNomDiagnosRela2' size='50' onkeyup='autocompletarCIE10DiagRela2()' /><input type='button' id='btnCrearEntidad' value='>>' onclick='ADx(2,"+adm+","+enca+")'/></td></tr>");
		out.print("<tr><td></td><td><div id='SugeDiagnosticoRela1'></div></td><td></td><td><div id='SugeDiagnosticoRela2'></div></td></tr>");
		
		rs=mc.listarDescripciones(adm,user);
		int sw=0;
		try {
			if(rs.next()){//////
				
			
				int swpre=0;
				out.print("<tr><td colspan='2' ><div align='lefth'>");
				rs1=mc.listarDx(rs.getString(1),"0");
				try {
					while(rs1.next()){
						swpre++;
						out.print("<a href='#' onclick='EDX("+rs1.getString(1)+",&quot;"+rs1.getString(3)+"&quot;,1,"+adm+","+enca+")'>"+swpre+". "+rs1.getString(3)+" "+rs1.getString(2)+"<BR>");
				
					}
					rs1.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				out.print("</div></td>");
		
				int swpost=0;
				out.print("<td colspan='2' ><div align='lefth'>");
				rs1=mc.listarDx(rs.getString(1),"1");
				try {
					while(rs1.next()){
						swpost++;
						out.print("<a href='#' onclick='EDX("+rs1.getString(1)+",&quot;"+rs1.getString(3)+"&quot;,2,"+adm+","+enca+")'>"+swpost+". "+rs1.getString(3)+" "+rs1.getString(2)+"<BR>");
				
					}
					rs1.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				out.print("</div></td></tr>");
		
			}////////////////////
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		}
		
		if(va.equals("0.3")){
		//	out.print("<table width='100%'  border='0' class='style6'>");
			rs=mc.listarDescripciones(adm,user);
			int sw=0;
			try {
				if(rs.next()){//////					
			/**Aqui deben venir los procedimientos cargados de este usuario**/
					out.print("<table width='100%'  border='0' class='style6'>");
						int a=1;
						rs1=mc.listarProcedimientosconenca(DQX,rs.getString(12));
						try {
							while(rs1.next()){
								
								out.print("<tr><td title='Cargado el " +rs1.getString(6)+" a las "+rs1.getString(7)+"'> <a href='#' onclick='EPROC("+rs1.getString(1)+",&quot;"+rs1.getString(5)+"&quot;,"+adm+","+enca+")'>  "+a+"). &nbsp;&nbsp;&nbsp;"+rs1.getString(4)+" -- "+rs1.getString(5)+"</td></tr>");
								
								a++;
							}
							rs1.getStatement().getConnection().close();
						} catch (SQLException e) {
							out.print("Error "+e);
							e.printStackTrace();
						}
						out.print("</table>");
					
					
						
					/////Cirujanos y Anestesiologos///////////////
						out.print("<table width='100%'  border='1' class='style6'>");
						out.print("<tr  BGCOLOR='#D3D3D3'><td colspan='4' ><div align='center'>Cirujanos/Anestesiologos</div></td></tr>");
						out.print("<tr><td>Cirujano(s):</td>");
						rs1=mc.listarUsuario(user);
						try {
						out.print("<td><select  style='width:350px' name='ccx' id='ccx' >");
							if(rs1.next()){
							out.print("<option  title='"+rs1.getString(1)+"  "+rs1.getString(2)+"' value="+rs1.getString(3)+">"+rs1.getString(1)+" "+rs1.getString(2)+"</option>");
							}
							rs1.getStatement().getConnection().close();
						} catch (SQLException e) {
							out.print("Error "+e);
							e.printStackTrace();
						}
						
						rs1=mc.listarNoUsuario(user);
						try {
						while(rs1.next()){
							out.print("<option  title='"+rs1.getString(2)+"  "+rs1.getString(1)+"' value="+rs1.getString(3)+">"+rs1.getString(2)+" "+rs1.getString(1)+"</option>");
							}
						rs1.getStatement().getConnection().close();
						} catch (SQLException e) {
							out.print("Error "+e);
							e.printStackTrace();
						}
						
						out.print("</select>");
						
						out.print("<input type='button' id='btnCrearEntidad' value='>>' onclick='ACx(1,"+adm+","+enca+")'/></td>");
						
						out.print("<td>Anestesiologo(s):</td>");
						rs1=mc.listarUsuarios();
						try {
						out.print("<td><select  style='width:350px' name='can' id='can' ><option value='Seleccione'>Seleccione</option>");
							while(rs1.next()){
							out.print("<option  title='"+rs1.getString(2)+"  "+rs1.getString(1)+"' value="+rs1.getString(3)+">"+rs1.getString(2)+" "+rs1.getString(1)+"</option>");
							}
							rs1.getStatement().getConnection().close();
						} catch (SQLException e) {
							out.print("Error "+e);
							e.printStackTrace();
						}
						out.print("</select>");
						out.print("<input type='button' id='btnCrearEntidad' value='>>' onclick='ACx(2,"+adm+","+enca+")'/></td></tr>");
						
						//////////////MOSTRAR CIRUJANOS Y ANESTESIOLOGOS >CARGADOS//////////////
						int swcx=0;
						String tcx="";
						out.print("<tr><td colspan='2' ><div align='lefth'>");
						rs1=mc.listarCxAnes(rs.getString(1),"0","1");
						try {
							while(rs1.next()){
								swcx++;
								if(rs1.getString(4).equals("0")){tcx="Cirujano Principal";}
								if(rs1.getString(4).equals("1")){tcx="Cirujano Ayudante";}
								out.print("<a href='#' onclick='ECX("+rs1.getString(1)+",&quot;"+rs1.getString(3)+"&quot;,1,"+adm+","+enca+")'>"+swcx+". "+rs1.getString(3)+" --"+tcx+"-- <BR>");
								
							}
							rs1.getStatement().getConnection().close();
						} catch (SQLException e) {
							out.print("Error "+e);
							e.printStackTrace();
						}
						out.print("</div></td>");
						
						int swane=0;
						out.print("<td colspan='2' ><div align='lefth'>");
						rs1=mc.listarCxAnes(rs.getString(1),"2");
						try {
							while(rs1.next()){
								swane++;
								out.print("<a href='#' onclick='ECX("+rs1.getString(1)+",&quot;"+rs1.getString(3)+"&quot;,2,"+adm+","+enca+")'>"+swane+". "+rs1.getString(3)+"<BR>");
								
							}
							rs1.getStatement().getConnection().close();
						} catch (SQLException e) {
							out.print("Error "+e);
							e.printStackTrace();
						}
						out.print("</div></td></tr></table>");
						
						
						
			
			out.print("<table width='100%'  border='0' class='style6'>");
			out.print("<tr BGCOLOR='#D3D3D3' ><td width='8%'><i><div align='center'>Ref.</div></i></td><td width='30%'><i><div align='center'>Descripcion</div></i></td><td width='25%'><i><div align='center'>Tipo de Acto Qx</div></i></td><td width='25%'><i><div align='center'>Acto Qx</div></i></td></tr>");
			
			out.print("<input name='pop' type='hidden' id='pop'/></td>");
			out.print("<input name='clases' type='hidden' id='clases'/></td>");
			out.print("<input name='valorp' type='hidden' id='valorp'/></td>");
			out.print("<input name='scc' type='hidden' id='scc'/></td>");
			out.print("<input name='ra' type='hidden' id='ra'/></td>");
			out.print("<tr><td><input type=text id='ref0' style='width:100%;' onfocus='validamedicos("+DQX+")' onkeyup='autocompletaRef(0,0)'  ></td>");
			out.print("<td><input type=text id='desct0' style='width:100%;' onfocus='validamedicos("+DQX+")' onkeyup='autocompletaPYS(0,0)' ></td>"); 
			out.print("<input name='descth0' type='hidden' id='descth0'  /></td>");
			//out.print("<td><input type=text id='fechamo' size='7%' onKeyup='masca(this,patron,true,0,0,0)' value='"+fechacj+"' onBlur='Estancias(a,&quot;a&quot;);'></td>");
			rs1=mc.listarFormasActoQx();
			try {
			out.print("<td><select  style='width:350px' id='taq' onchange='actoqx()'><option value='Seleccione'>Seleccione</option>");
				while(rs1.next()){
				out.print("<option title='"+rs1.getString(2)+"' value="+rs1.getString(1)+" >"+rs1.getString(2)+"</option>");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("</select></td>");
			
			out.print("<td><div id='divaqx'><select  style='width:350px'  id='aq' ><option value='Seleccione'>Seleccione</option></div></td>"); 
			//out.print("<td><a  href='#'onclick='Cargar("+adm+","+enca+")' >Cargar</a></td></tr>");
			out.print("<tr><td><div id='sugerencias3210' ></div></td><td><div id='sugerencias210' ></div></td><td></td><td></td></tr></table>");
			
			
			
			//out.print("<td><a  href='#'onclick='Cargar("+adm+","+enca+")' >Cargar</a></td></tr>");
			
			out.print("<tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>");
			out.print("<tr><td colspan='4'><div align='center'><input type='button' id='btnCrearEntidad' value='     Cargar     ' onclick='Cargarpop("+adm+","+enca+")' /></div></td></tr>");
			
		
				}////////////////////fin del ciclo de  la dx no tal
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
		
		}












		if(va.equals("0.4")){
		out.print("<table width='100%'  border='0' class='style6'>");
		out.print("<tr><td>Hallazgos:</td><td><textarea id='hallazgos' cols='100' rows='5' size='50' ></textarea></td></tr>");
		out.print("<tr><td>Descripcion Quirurgica:</td><td><textarea id='descripcion' cols='100' rows='5' size='500' ></textarea></td></tr>");
		out.print("</table>");	
		

		out.print("<table width='100%' border='0' class='style6'> ");	
		out.print("<tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>");
		out.print("<tr><td colspan='2'><div align='center'><input type='button' id='btnCrearEntidad' value='     Guardar Descripcion    ' onclick='GuardarDQx("+adm+","+enca+")' /></div></td></tr></table>");
		out.print("</table> ");
		
		
		}
		
	
		if(va.equals("2")){
		
		rs1=mc.listarActoQx(taqx);
		try {
		out.print("<td><select  style='width:350px' name='aq' id='aq' ><option value='Seleccione'>Seleccione</option>");
			while(rs1.next()){
			out.print("<option  title='"+rs1.getString(2)+"' value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
			}
			rs1.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		out.print("</select></td>");
		}
		
		
		if(va.equals("2.1")){//Cx Principales
			rs1=mc.CxAnesinAsignar(DQX,"0");
			try {
				if(rs1.next()){
					out.print(rs1.getString(1));
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
		}
			
		if(va.equals("2.2")){//Cx Secundarios
			rs1=mc.CxAnesinAsignar(DQX,"1");
			try {
				if(rs1.next()){
					out.print(rs1.getString(1));
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
		}
		
		if(va.equals("2.3")){//Cx Secundarios
			rs1=mc.CxAnesinAsignar(DQX,"2");
			try {
				if(rs1.next()){
					out.print(rs1.getString(1));
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
		}
		
		if(va.equals("3")){
					
			String cpdqx="";
			//Cargamos el paquete en hic_procedimientosDqx
			mc.CargarProcedimientoDQx(pop,codp,nomp,DQX,hra);
			//buscamos el codigo que acabamos de cargar
			rs1=mc.ProcedimientoDQx(codp,DQX,hra);
			try {
				if(rs1.next()){
					cpdqx=rs1.getString(1);
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			
			String dias=fechacj.substring(0, 2);
			String meses=fechacj.substring(3, 5);
			String anos=fechacj.substring(6, 10);
			String fec=anos+"-"+meses+"-"+dias;
			
			String f=fechamo;
			
			
			
			String ncx="";
			rs1=mc.CxAne(cx);
			try {
				if(rs1.next()){
					ncx=rs1.getString(1);
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			String pcx="";
			String pan="";
			String pcx2="";
			int sw=0;
			rs1=mc.ConsultarPorcentajePro(codp);
			try {
				if(rs1.next()){
					sw=1;
					pcx=rs1.getString(1);
					pan=rs1.getString(2);
					pcx2=rs1.getString(3);
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			if(sw==1){
			int vtpcx=Math.round(Integer.parseInt(valorp)*(Float.parseFloat(pcx)/100));
			String vtpscx =String.valueOf(vtpcx);
			mc.CargarHonorariosDQx(cpdqx,cx,vtpscx,pcx);
			mc.AtualizarCxAne(cx);
			
			int vtpan=Math.round(Integer.parseInt(valorp)*(Float.parseFloat(pan)/100));
			String vtpsan =String.valueOf(vtpan);
			mc.CargarHonorariosDQx(cpdqx,ane,vtpsan,pan);
			mc.AtualizarCxAne(ane);
			
			if(!cx2.equals("0")){
			int vtpcx2=Math.round(Integer.parseInt(valorp)*(Float.parseFloat(pcx2)/100));
			String vtpscx2 =String.valueOf(vtpcx2);
			mc.CargarHonorariosDQx(cpdqx,cx2,vtpscx2,pcx2);
			mc.AtualizarCxAne(cx2);
			}
			
			}else{
			//Inserto en la misma tabla hic_porc_descripcionqx el cod del programa sin porcentajes para q la jefe se los asigne
				int swe=0;
				rs1=mc.ConsultarPorcentajeProCreado(codp);
				try {
					if(rs1.next()){
						swe=1;
					}
					rs1.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				if(swe==0){
				mc.InsertarPorcentajePro(codp);
				}
				
				mc.CargarHonorariosDQx(cpdqx,cx,"0","Consultar con Facturacion");
				mc.AtualizarCxAne(cx);
				mc.CargarHonorariosDQx(cpdqx,ane,"0","Consultar con Facturacion");
				mc.AtualizarCxAne(ane);
				if(!cx2.equals("0")){
				mc.CargarHonorariosDQx(cpdqx,cx2,"0","Consultar con Facturacion");
				mc.AtualizarCxAne(cx2);
				}
			}
			
			mc.CargarDetalle(fec,hra,pop,codp,codr,nomp,null,null,clases,f,"1",valorp,user,enca,ncx,scc,aq,"0","1",DQX);				
		}
		
		if(va.equals("4")){
			String cpdqx="";
			//Cargamos el paquete en hic_procedimientosDqx
			mc.CargarProcedimientoDQx(pop,codp,nomp,DQX,hra);
			//buscamos el codigo que acabamos de cargar
			rs1=mc.ProcedimientoDQx(codp,DQX,hra);
			try {
				if(rs1.next()){
					cpdqx=rs1.getString(1);
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
				
			
			String dias=fechacj.substring(0, 2);
			String meses=fechacj.substring(3, 5);
			String anos=fechacj.substring(6, 10);
			String fec=anos+"-"+meses+"-"+dias;
			
			String f=fechamo;
		
			String ncx="";
			String ncx2="";
			String nane="";
			
			rs2=mc.listarSYP(codp,ent);
			try {
				while(rs2.next()){
					System.out.println("SERVICIOS: 1: "+rs2.getString(1)+" 2:"+rs2.getString(2)+" 3:"+rs2.getString(3)+" 4:"+rs2.getString(4)+" 5:"+rs2.getString(5)+" 6:"+rs2.getString(6)+" 7:"+rs2.getString(7)+" 8:"+rs2.getString(8));
					/////////////////////////////////////////
					String porcs="";
					int indice=rs2.getInt(7);
					rs1=mc.PorcentajeQx(aq);
					try {
						if(rs1.next()){
							System.out.println("ACTO QX: 1:"+rs1.getString(1)+" 2:"+rs1.getString(2)+" 3:"+rs1.getString(3)+" 4:"+rs1.getString(4)+" 5:"+rs1.getString(5)+" 6:"+rs1.getString(6));
							
							porcs=rs1.getString(indice);
						}
						rs1.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					
					if(porcs.equals("")){
						porcs = "100";
					}
					
					int vtp=Math.round(Integer.parseInt(rs2.getString(5))*(Float.parseFloat(porcs)/100));
					String vtps =String.valueOf(vtp);
					
					System.out.println("porcs"+porcs);
					System.out.println("vtp"+vtp);
					///////////////////////////////////
					
					
					
					if(rs2.getInt(7)==1){
						rs1=mc.CxAne(cx);
						try {
							if(rs1.next()){
								ncx=rs1.getString(1);
							}
							rs1.getStatement().getConnection().close();
						} catch (SQLException e) {
							out.print("Error "+e);
							e.printStackTrace();
						}
						
						mc.CargarDetalle(fec,hra,pop,rs2.getString(1),rs2.getString(2),rs2.getString(3),codr,nomp,rs2.getString(8),f,"1",vtps,user,enca,ncx,scc,aq,porcs,"1",DQX);
						mc.CargarHonorariosDQx(cpdqx,cx,vtps,porcs);
						mc.AtualizarCxAne(cx);
					}
					if(rs2.getInt(7)==2){
						rs1=mc.CxAne(ane);
						try {
							if(rs1.next()){
								nane=rs1.getString(1);
							}
							rs1.getStatement().getConnection().close();
						} catch (SQLException e) {
							out.print("Error "+e);
							e.printStackTrace();
						}
						mc.CargarDetalle(fec,hra,pop,rs2.getString(1),rs2.getString(2),rs2.getString(3),codr,nomp,rs2.getString(8),f,"1",vtps,user,enca,nane,scc,aq,porcs,"1",DQX);
						mc.CargarHonorariosDQx(cpdqx,ane,vtps,porcs);
						mc.AtualizarCxAne(ane);
					}
					if(rs2.getInt(7)==3){
						if(!cx2.equals("0")){
						rs1=mc.CxAne(cx2);
						try {
							if(rs1.next()){
								ncx2=rs1.getString(1);
							}
							rs1.getStatement().getConnection().close();
						} catch (SQLException e) {
							out.print("Error "+e);
							e.printStackTrace();
						}
						mc.CargarHonorariosDQx(cpdqx,cx2,vtps,porcs);
						mc.AtualizarCxAne(cx2);
						}
						mc.CargarDetalle(fec,hra,pop,rs2.getString(1),rs2.getString(2),rs2.getString(3),codr,nomp,rs2.getString(8),f,"1",vtps,user,enca,ncx2,scc,aq,porcs,"1",DQX);
						
						
					}
					if(rs2.getInt(7)==4){
						mc.CargarDetalle(fec,hra,pop,rs2.getString(1),rs2.getString(2),rs2.getString(3),codr,nomp,rs2.getString(8),f,"1",vtps,user,enca,"",scc,aq,porcs,"1",DQX);
					}
					if(rs2.getInt(7)==5){
						mc.CargarDetalle(fec,hra,pop,rs2.getString(1),rs2.getString(2),rs2.getString(3),codr,nomp,rs2.getString(8),f,"1",vtps,user,enca,"",scc,aq,porcs,"1",DQX);
					}
					if(rs2.getInt(7)==6){
						mc.CargarDetalle(fec,hra,pop,rs2.getString(1),rs2.getString(2),rs2.getString(3),codr,nomp,rs2.getString(8),f,"1",vtps,user,enca,"",scc,aq,porcs,"1",DQX);
					}
					
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
					
		}
		
		
		if(va.equals("5")){
			String hallazgos = req.getParameter("hallazgos");
			String descripcion = req.getParameter("descripcion");
			//Verifico el dx pre
			int swdpre=0;
			rs1=mc.listarDx(DQX,"0");
			try {
				if(rs1.next()){
					swdpre=1;			
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			//Verifico el dx post
			int swdpos=0;
			rs1=mc.listarDx(DQX,"1");
			try {
				if(rs1.next()){
					swdpos=1;			
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			//Verifico al menos 1 procedimiento
			int p=0;
			rs1=mc.listarProcedimientos(DQX);
			try {
				if(rs1.next()){
					p=1;
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			
			if(swdpre==0){
				out.print("1");
			}
			if(swdpos==0){
				out.print("2");
			}
			if(p==0){
				out.print("3");
			}
			//System.out.print("ESSSTOO."+swdpre+" -- "+swdpos+" -- "+p);
			if((swdpre==1)&&(swdpos==1)&&(p==1)){
				mc.FinalizarDescripcionQx(hallazgos,descripcion,fechacjmysql,hra,DQX);
				out.print("0");
			}
			
		}

		
		
	if(va.equals("6")){
		///////////////////
		int swcx=0;
		String tcx="";
		rs1=mc.listarCxAnes(DQX,"0","1");
		try {
			if(rs1.next()){
				swcx++;
			}
			rs1.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}

		
		int swane=0;
			rs1=mc.listarCxAnes(DQX,"2");
		try {
			while(rs1.next()){
				swane++;
			}
			rs1.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		
		if((swcx==0)&&(swane==0)){
			out.print(0);
		}else{
			if(swcx==0){
				out.print(1);
			}else{
				if(swane==0){
					out.print(2);
				}else{
					out.print(3);
				}
			}
		}
	/*	*/
		
		////////////////
	}	
		

	if(va.equals("7")){
		mc.EliminarMedico(ccx);
	}
	
	if(va.equals("8")){
		mc.EliminarDiagnostico(ccx);
	}
	
		if(va.equals("9")){
		mc.EliminarProcedimiento(ccx);
	}
	
		if(va.equals("autoinv3")){
			
			try {
			//	System.out.print("ESSSTOO."+xx);
				rs =mc.listarPYS(texto,xx);
				String cadena ="";
				cadena="[";
				while(rs.next()){
					cadena = cadena+"'"+rs.getString(5)+"|"+rs.getString(4)+"|"+rs.getString(1)+"|"+rs.getString(2)+"|"+rs.getString(3)+"|"+rs.getString(6)+"|"+rs.getString(7)+"|"+rs.getString(8)+"|"+rs.getString(9)+"|"+rs.getString(10)+"|"+rs.getString(11)+"|"+rs.getString(12)+"'";
	            	cadena = cadena +",";	
				}
				cadena = cadena+"]";
				//System.out.print("ESSSTOO."+cadena);
				resp.getWriter().write(cadena);
				rs.getStatement().getConnection().close();
			} catch (Exception e) {
				e.getMessage();
				e.printStackTrace();
			}
		}
		
		if(va.equals("autoinv4")){
			
			try {

				rs =mc.listarRef(texto,xx);
				String cadena ="";
				cadena="[";
				while(rs.next()){
					cadena = cadena+"'"+rs.getString(4)+"|"+rs.getString(5)+"|"+rs.getString(1)+"|"+rs.getString(2)+"|"+rs.getString(3)+"|"+rs.getString(6)+"|"+rs.getString(7)+"|"+rs.getString(8)+"|"+rs.getString(9)+"|"+rs.getString(10)+"|"+rs.getString(11)+"|"+rs.getString(12)+"'";
	            	cadena = cadena +",";	
	            	//System.out.print("cadena."+cadena);
				}
				cadena = cadena+"]";
				//System.out.print("ESSSTOO."+cadena);
				resp.getWriter().write(cadena);
				rs.getStatement().getConnection().close();
			} catch (Exception e) {
				e.getMessage();
				e.printStackTrace();
			}
		}
		
		
		
		
		
	}

}
