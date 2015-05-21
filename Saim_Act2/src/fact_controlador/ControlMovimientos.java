package fact_controlador;

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
import java.util.regex.Pattern;
//import java.io.*;

//import javax.servlet.http.HttpSession;

import cont_metodo.MetodoCuentas;

//import fact_bean.Tarifas;
import fact_metodo.MetodoMovimientos;


public class ControlMovimientos extends HttpServlet { 
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(request, response);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		//HttpSession session = req.getSession(true);
		
		String va = req.getParameter("valor");
		String venc=req.getParameter("venc");
		String lot=req.getParameter("lot");
		
		
		//String NombreManual=req.getParameter("NombreManual");
		//String cod_ManTarFk=req.getParameter("cod_ManTarFk");
		//String NombreTipoTarifa=req.getParameter("NombreTipoTarifa");
		//String ClaseServicio=req.getParameter("ClaseServicio");
		//String NombreCentroCosto=req.getParameter("NombreCentroCosto");
		String Abreviado=req.getParameter("Abreviado");
		String enca=req.getParameter("enca");
		String lotil=req.getParameter("lotil");
		String nivel=req.getParameter("nivel");
		String texto=req.getParameter("texto");
		String xx=req.getParameter("xx");
		String n=req.getParameter("n");
		String efe=req.getParameter("e");
		String pop=req.getParameter("pop");
		String ep=req.getParameter("ep");
		String mbase0p=req.getParameter("mbase0p");
		String mtarp=req.getParameter("mtarp");
		String mtarpp=req.getParameter("mtarpp");
		String desch0p=req.getParameter("desch0p");
		String critp=req.getParameter("critp");
		String caad=req.getParameter("caad");
		String serv0=req.getParameter("serv0");
		String refhi=req.getParameter("refhi");
		String servich0=req.getParameter("servich0");
		String ref0=req.getParameter("ref0");
		String cse=req.getParameter("cse");
		String subc=req.getParameter("subc");
		String desct0=req.getParameter("desct0");
		String adm01=req.getParameter("adm0");
		String taq=req.getParameter("taq");
		String med=req.getParameter("med");
		//String estAdm=req.getParameter("estAdm");
		String actqx=req.getParameter("actqx");
		String mece=req.getParameter("me");
		String fece=req.getParameter("fe");
		String anti=req.getParameter("anti");
		String modera=req.getParameter("modera");
		String copag=req.getParameter("copag");
		String subt=req.getParameter("subt");
		String estancia=req.getParameter("estancia");
		String encab=req.getParameter("enca");
		if(encab==null){encab="";}
		if(estancia==null){estancia="";}
		if(subt==null){subt="";}
		String subtl=req.getParameter("subtl");
		if(subtl==null){subtl="";}
		if(copag==null){copag="";}
		if(modera==null){modera="";}
		if(anti==null){anti="";}
		if(mece==null){mece="";}
		if(fece==null){fece="";}
		if(med==null){med="";}
		if(actqx==null){actqx="";}
		if(taq==null){taq="";}
		String act=req.getParameter("act");
		if(act==null){act="";}
		if(adm01==null){adm01="";}
		if(servich0==null){servich0="";}
		if(ref0==null){ref0="";}
		if(cse==null){cse="";}
		if(efe==null){efe="";}
		if(subc==null){subc="";}
		if(desct0==null){desct0="";}
		
		if(refhi==null){refhi="";}
		if(serv0==null){serv0="";}
		if(mbase0p==null){mbase0p="";}
		if(mtarpp==null){mtarpp="8";}
		if(mtarp==null){mtarp="";}
		if(desch0p==null){desch0p="";}
		if(critp==null){critp="";}
		if(caad==null){caad="";}
		if(pop==null){pop="";}
		
		//String mbasec=req.getParameter("mbasec");
		//String descch0=req.getParameter("descch0");
		//String fechai0=req.getParameter("fechai0");
		//String fechaf0=req.getParameter("fechaf0");
		//String valor0=req.getParameter("valor0");
		//String ct=req.getParameter("ct");
		//String y=req.getParameter("y");
		//String v=req.getParameter("v");
		
		
		//java.util.Date fechaS = new Date();
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
		
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out=res.getWriter();
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		ResultSet rs3=null;
		ResultSet rs4=null;
		ResultSet rsfc=null;
		ResultSet rsef=null;
		MetodoMovimientos mm = new MetodoMovimientos();
		
		String formatomoneda="$###,###,###"; //declarando la variable con un tipo de formato
		DecimalFormat df=new DecimalFormat(formatomoneda); //aplicando el formato a la variable formato_moneda
		
		
		if(va.equals("30CPF")){
			//La idea es  consultar las facturas
			out.print("<table width='100%' border='0' align='center'><tr><td colspan='4' height='30' valign='top'><div align='center' class='style11' id='cabecera2'>Facturacion x Centros Principales</div></td></tr></table>");
			
			//out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='60%'><i><div align='center'>Paciente</div></i></td><td width='30%'><i><div align='center'>Empresa</div></i></td><td width='10%'><i><div align='center'>Admisi�n</div></i></td></tr>");  //
			out.print("<table width='100%' border='0' class='style6' ><tr BGCOLOR='#D3D3D3' ><td colspan='6' height='30' valign='top'><i><div align='center'>Criterios de Busqueda</div></i></td></tr>");
			out.print("<tr><td  width='20%'><div align='right'>Fecha Inicial:</div></td><td width='10%'><input name='FI' type='text' id='FI' size='10'  onKeyup='masca(this,patron,true,0,0,0)' onblur='vfi()'; /></td><td width='15%'><div align='right'>Fecha Final:</div></td><td width='10%'><input name='FF' type='text' id='FF' size='10' onKeyup='masca(this,patron,true,0,0,0)' onblur='vff()'; /></td>");
			out.print("<td width='5%'><div align='right'>Entidad:</div></td><td width='30%'><select  style='width:269px' name='Ent' id='Ent' ><option value='Seleccione'>Seleccione</option>");
			rs2=mm.Empresas();
			try {
				while(rs2.next()){
				out.print("<option title='"+rs2.getString(2)+"' value="+rs2.getString(1)+">"+rs2.getString(2)+"</option>");
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("</td></tr></tr><tr></tr></table>");
			out.print("<table width='100%' border='0' class='style6' ><tr BGCOLOR='#D3D3D3' ><tr><td  width='30%'><div align='right'>Centros Principales:</div></td><td width='30%'><div align='center'><select  style='width:200px' name='cmbuser' id='cmbuser' ><option value='Seleccione'>Seleccione</option>");
			//rs2=mm.UsuariosFacturadores();
			out.print("<option value='1'>Urgencias</option><option value='2'>Hospitalizacion</option><option value='3'>Ambulatorio</option><option value='4'>Consulta Externa</option></select>");
			out.print("</td><td  width='30%'><div align='lefth'><input name='btnCrearEntidad' type='button' id='btnCrearEntidad' value='     Consultar     ' onclick='CFactCPF()' /></div></td><td width='10%'></td></tr><tr></tr><tr></tr></table>");
			
			out.print("<table width='100%' border='0' class='style6' ><tr></tr><tr BGCOLOR='#D3D3D3' ><td colspan='4' height='30' valign='top'><i><div align='center'>Resultados de Busqueda</div></i></td></tr><tr></tr></table>");
			out.print("<div id='resultadosf' style='height: 240px; overflow-y: scroll'></div>");
			out.print("<table width='100%' border='0' class='style6' ><tr><td><div align='center'><input type='button' value='Generar Reporte' name='btnIMP' onclick='ReporteACPF()' style='margin-right:10px'/></div></td></tr></table>");
		}

		if(va.equals("31CPF")){
			String sql="";
		String vu=req.getParameter("vu");
		String v8=req.getParameter("v8");
		String v9=req.getParameter("v9");
		
		if((v8==null)||(v8.equals(""))||(v9.equals(""))||(v9==null)){v8="";v9="";}
		else{
		String dv8=v8.substring(0, 2);
		String mv8=v8.substring(3, 5);
		String av8=v8.substring(6, 10);
		String fv8=av8+"-"+mv8+"-"+dv8;
		
		String dv9=v9.substring(0, 2);
		String mv9=v9.substring(3, 5);
		String av9=v9.substring(6, 10);
		String fv9=av9+"-"+mv9+"-"+dv9;
		
		String v2=req.getParameter("v2");
		if(v2.equals("Seleccione")){v2="";}else{sql=sql+" AND ef.cod_eps="+v2;}
		
		//sql=sql+" AND ef.fecha BETWEEN '"+fv8+"' AND '"+fv9+"' ";}
		
		
			
			int crs=0;		
			rs2=mm.FactxCentrosP(vu,fv8,fv9,sql);
			try {
				while(rs2.next()){
					if(crs==0){
					out.print("<table width='100%' border='0' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='3%'><i><div align='center'>Item</div></i></td><td width='7%'><i><div align='center'>No. de Factura</div></i></td><td width='15%'><i><div align='center'>Empresa</div></i></td><td width='5%'><i><div align='center'>Fecha</div></i></td><td width='7%'><i><div align='center'>Documento</div></i></td><td width='30%'><i><div align='center'>Nombres</div></i></td></tr>	");  //
					}
				out.print("<tr><td>"+(crs+1)+"</td><td><a  href='#'onclick='ventSelReFact(&quot;"+rs2.getString(1)+"&quot;,&quot;"+rs2.getString(1)+"&quot;,&quot;"+rs2.getString(9)+"&quot;,&quot;"+rs2.getString(10)+"&quot;)'>"+rs2.getString(1)+"</a></td><td>"+rs2.getString(2)+"</td><td>"+rs2.getString(8)+"</td><td>"+rs2.getString(3)+" "+rs2.getString(4)+"</td><td> "+rs2.getString(5)+" "+rs2.getString(6)+" "+rs2.getString(7)+"</td></tr>");
				crs++;
				}
				rs2.getStatement().getConnection().close();
				//sql="AND ef.cod_usuario_fk=cesar";
				//System.out.println(sql);
					
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			if(crs==0){out.print("No hay resultados para sus criterios de busqueda.");}
		}
		
		}
		
		/*
FUNCIONANDO
		if(va.equals("1")){
			
			out.print("<table width='100%' class='style6'><tr><td colspan='5'><div align='center' class='style11' id='cabecera2'>Movimiento en Urgencias </div></td></tr>");
			
			out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='60%'><i><div align='center'>Paciente</div></i></td><td width='30%'><i><div align='center'>Empresa</div></i></td><td width='10%'><i><div align='center'>Admisi�n</div></i></td></tr>");  //
			
			out.print("<tr><td><input type=text id='desc0' size='90' onkeyup='autocompletaPacientes(0,0)' >"); 
			out.print("<input name='desch0' type='hidden' id='desch0'/></td>");
			out.print("<td><input type=text id='eps0' size='45' readonly='readonly' >"); 
			out.print("<input name='epsh0' type='hidden' id='epsh0'/></td>");
			out.print("<td><input type=text id='adm0' size='10' readonly='readonly' ></td></tr>"); 
			
			out.print("<tr><td><div id='sugerencias0' ></div></td></tr></table>");
			out.print("</table>");
			out.print("<tr><td><div id='resul2' ></div></td></tr></table>");
			out.print("<tr><td><div id='resul' ></div></td></tr></table>");
			out.print("<tr><td><div id='resulcrea' ></div></td></tr></table>");
			
		
		}
		
	*/




		if(va.equals("1")){
			
			out.print("<table width='100%' class='style6'><tr><td colspan='5'><div align='center' class='style11' id='cabecera2'>Movimiento en Urgencias </div></td></tr>");
			
			out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='35%'><i><div align='center'>Paciente</div></i></td><td width='25%'><i><div align='center'>Empresa</div></i></td><td width='10%'><i><div align='center'>Admisi�n</div></i></td><td width='10%'><i><div align='center'>Fecha Ingreso</div></i></td><td width='10%'><i><div align='center'>Fecha Egreso</div></i></td><td width='10%'><i><div align='center'>Num. Autorizacion</div></i></td></tr>");  //
			
			out.print("<tr>" +
					"<td><input type=text id='desc0' size='90' onfocus='limpiarmovimientos(0)' onkeyup='autocompletaPacientes(0,0)'  >"); //crear un onfocus aqui y en 1h para limpiar el div y revisar bn ambulatorio urg y hosp
			out.print("<input name='desch0' type='hidden' id='desch0'/></td>");
			
			//out.print("<td><input type=text id='eps0' size='45' readonly='readonly' >"); 
			out.print("<td><div id='depss'><select  name='epsh0' id='epsh0' style='width:100%'  ><option value='Seleccione'>Seleccione</option></select></div>");//style='width:220px' 
			//out.print("<input name='epsh0' type='hidden' id='epsh0'/></td>");
			out.print("<td><input type=text id='adm0' size='10' readonly='readonly' ></td>" +
					"<td><input type=text id='FechaIngreso' size='10' readonly='readonly' ></td>" +
					"<td><input type=text id='FechaEgreso' size='10' readonly='readonly' ></td>" +
					"<td><input type=text id='NumAutoriza' size='10' readonly='readonly' ></td></tr>"); 
			
			out.print("<tr><td><div id='sugerencias0' ></div></td></tr></table>");
			out.print("</table>");
			out.print("<tr><td><div id='resul2' ></div></td></tr></table>");
			out.print("<tr><td><div id='resul' ></div></td></tr></table>");
			out.print("<tr><td><div id='resulcrea' ></div></td></tr></table>");
			
		
		}


/*
FUNCIONANDO
		if(va.equals("1h")){
			
			
			out.print("<table width='100%' class='style6'><tr><td colspan='5'><div align='center' class='style11' id='cabecera2'>Movimiento en Hospitalizaci�n </div></td></tr>");
			
			out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='50%'><i><div align='center'>Paciente</div></i></td><td width='30%'><i><div align='center'>Empresa</div></i></td><td width='10%'><i><div align='center'>Admisi�n</div></i></td><td width='10%'><i><div align='center'>Facturar</div></i></td></tr>");  //
		FUNCIONANDO */	
		/*	out.print("<tr><td><input type=text id='desc0' size='80' onkeyup='autocompletaPacientesh(0,0)' >"); 
			out.print("<input name='desch0' type='hidden' id='desch0'/></td>");
			out.print("<td><input type=text id='eps0' size='45' readonly='readonly' >"); 
			out.print("<input name='epsh0' type='hidden' id='epsh0'/></td>");
			out.print("<td><input type=text id='eps0' size='45' readonly='readonly' ><td>"); 
			out.print("<td><input type=text id='adm0' size='10' readonly='readonly' ></td></tr>"); 
		*/	
		/* FUNCIONANDO	
			out.print("<tr><td><input type=text id='desc0' size='80' onkeyup='autocompletaPacientesh(0,0,0)' >"); 
			out.print("<input name='desch0' type='hidden' id='desch0'/></td>");
			out.print("<td><input type=text id='eps0' size='45' readonly='readonly' >"); 
			out.print("<input name='epsh0' type='hidden' id='epsh0'/></td>");
			out.print("<td><input type=text id='adm0' size='7' readonly='readonly' >"); 
			out.print("<td><input id='iraFact' type=button value='Facturar' disabled='true' ></td></tr>"); 
		
				
			
			out.print("<tr><td><div id='sugerencias0' ></div></td></tr></table>");
			out.print("</table>");
			out.print("<tr><td><div id='resul2' ></div></td></tr></table>");
			out.print("<tr><td><div id='resul' ></div></td></tr></table>");
			out.print("<tr><td><div id='resulcrea' ></div></td></tr></table>");
			
		
		}
		*/

	if(va.equals("1h")){
			
			
			out.print("<table width='100%' class='style6'><tr><td colspan='5'><div align='center' class='style11' id='cabecera2'>Movimiento en Hospitalizaci�n </div></td></tr>");
			
			out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' >" +
					"<td width='35%'><i><div align='center'>Paciente</div></i></td>" +
					"<td width='25%'><i><div align='center'>Empresa</div></i></td>" +
					"<td width='10%'><i><div align='center'>Admisi�n</div></i></td>" +
					"<td width='10%'><i><div align='center'>Fecha Ingreso</div></i></td>" +
					"<td width='10%'><i><div align='center'>Fecha Egreso</div></i></td>" +
					"<td width='10%'><i><div align='center'>Num Autorizacion</div></i></td>" +
					"<td width='10%'><i><div align='center'>Facturar</div></i></td></tr>");  //
			
		/*	out.print("<tr><td><input type=text id='desc0' size='80' onkeyup='autocompletaPacientesh(0,0)' >"); 
			out.print("<input name='desch0' type='hidden' id='desch0'/></td>");
			out.print("<td><input type=text id='eps0' size='45' readonly='readonly' >"); 
			out.print("<input name='epsh0' type='hidden' id='epsh0'/></td>");
			out.print("<td><input type=text id='eps0' size='45' readonly='readonly' ><td>"); 
			out.print("<td><input type=text id='adm0' size='10' readonly='readonly' ></td></tr>"); 
		*/	
			
			out.print("<tr><td><input type=text id='desc0' size='80' onfocus='limpiarmovimientos(1)' onkeyup='autocompletaPacientesh(0,0,0)' >"); 
			out.print("<input name='desch0' type='hidden' id='desch0'/></td>");
		//	out.print("<td><input type=text id='eps0' size='45' readonly='readonly' >"); 
			out.print("<td><div id='depssh'><select  name='epsh0' id='epsh0' style='width:100%'  ><option value='Seleccione'>Seleccione</option></select></div>");//style='width:220px' 
		//	out.print("<input name='epsh0' type='hidden' id='epsh0'/></td>");
			out.print("<td><input type=text id='adm0' size='7' readonly='readonly' >");
			
			out.print("<td><input type=text id='FechaIngreso' size='7' readonly='readonly' >"); 
			out.print("<td><input type=text id='FechaEgreso' size='7' readonly='readonly' >"); 
			out.print("<td><input type=text id='NumAutoriza' size='7' readonly='readonly' >"); 
			
			out.print("<td><input id='iraFact' type=button value='Facturar' disabled='true' ></td></tr>"); 
		
			out.print("<input name='hoa' type='hidden' id='hoa' value='"+0+"' />");
				
			
			out.print("<tr><td><div id='sugerencias0' ></div></td></tr></table>");
			out.print("</table>");
			out.print("<tr><td><div id='resul2' ></div></td></tr></table>");
			out.print("<tr><td><div id='resul' ></div></td></tr></table>");
			out.print("<tr><td><div id='resulcrea' ></div></td></tr></table>");
			
		
		}



if(va.equals("1eps")){	
			String ceps=req.getParameter("ceps");
			String eps=req.getParameter("eps");
	
			//System.out.println("llegamos a la eps"+ceps+" - "+eps);
			out.print("<select  name='epsh0' id='epsh0' style='width:100%'  onChange='CambiardeEntidad("+ceps+",&quot;"+eps+"&quot;)' ><option value='"+ceps+"'>"+eps+"</option>");//style='width:220px' 
			
			rs1=mm.EmpresasDiferentes(ceps);
			//String ManualBase = "";
			try {
				while(rs1.next()){
					out.print("<option value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
					//System.out.println("<option value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("</select>");
		}
		
//		verifico si hay encabezado con esta entidad y si no hay la creo en el controladr guia va 32

		if(va.equals("2eps")){	
			String ceps=req.getParameter("ceps");
			String eps=req.getParameter("eps");
			 
		String codenca="";
		rs = mm.InfoEncaxEntidad(adm01,ceps);
		try {
			if(rs.next()){
				codenca=rs.getString(1);
			}rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if(codenca.equals("")){//Mo hay encabezado hay que crearlo
			
			rs = mm.obtenerInfoEnca(adm01);
			try {
				if(rs.next()){
					rs1=mm.BuscarEntidadNueva(ceps);
					if(rs1.next()){
						 mm.duplicarEncabezado(ceps,
								 eps,
								 rs1.getString(1),
								 rs1.getString(7),
								 rs1.getString(8),
								 rs1.getString(12),
								 rs.getString(8),
								 rs.getString(9),
								 rs.getString(10),
								 rs.getString(11),
								 rs.getString(12),
								 rs.getString(13),
								 rs.getString(14),
								 rs.getString(15),
								 adm01,
								 rs.getString(18),
								 rs.getString(20),
								 rs.getString(23),
								 rs.getString(16));
					}
					rs1.getStatement().getConnection().close();							
				}rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else{
			System.out.println("El codigo del encabezado es: "+codenca);
		}
		}

		

if(va.equals("1heps")){	
			String ceps=req.getParameter("ceps");
			String eps=req.getParameter("eps");
	
			//System.out.println("llegamos a la eps"+ceps+" - "+eps);
			out.print("<select  name='epsh0' id='epsh0' style='width:100%'  onChange='CambiardeEntidadh("+ceps+",&quot;"+eps+"&quot;)' ><option value='"+ceps+"'>"+eps+"</option>");//style='width:220px' 
			
			rs1=mm.EmpresasDiferentes(ceps);
			//String ManualBase = "";
			try {
				while(rs1.next()){
					out.print("<option value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
					//System.out.println("<option value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("</select>");
		}

		

		//	verifico si hay encabezado con esta entidad y si no hay la creo en el controladr guia va 32
			if(va.equals("2heps")){	
				
				String ceps=req.getParameter("ceps");
				String eps=req.getParameter("eps");
				 
			String codenca="";
			rs = mm.InfoEncaxEntidad(adm01,	ceps);
			try {
				if(rs.next()){
					codenca=rs.getString(1);
				}rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.println("Entramos aqui "+codenca+" y no hay");
			if(codenca.equals("")){//Mo hay encabezado hay que crearlo
				
				rs = mm.obtenerInfoEnca(adm01);
				try {
					if(rs.next()){
						rs1=mm.BuscarEntidadNueva(ceps);
						if(rs1.next()){
							 mm.duplicarEncabezado(ceps,
									 eps,
									 rs1.getString(1),
									 rs1.getString(7),
									 rs1.getString(8),
									 rs1.getString(12),
									 rs.getString(8),
									 rs.getString(9),
									 rs.getString(10),
									 rs.getString(11),
									 rs.getString(12),
									 rs.getString(13),
									 rs.getString(14),
									 rs.getString(15),
									 adm01,
									 rs.getString(18),
									 rs.getString(20),
									 rs.getString(23),
									 rs.getString(16));
						}
						rs1.getStatement().getConnection().close();			
					}rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}else{
				System.out.println("El codigo del encabezado es: "+codenca);
			}
			}




	/*if(va.equals("1hA")){
			
			
			out.print("<table width='100%' class='style6'><tr><td colspan='5'><div align='center' class='style11' id='cabecera2'>Movimiento Ambulatorio </div></td></tr>");
			
			out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='50%'><i><div align='center'>Paciente</div></i></td><td width='30%'><i><div align='center'>Empresa</div></i></td><td width='10%'><i><div align='center'>Admisi�n</div></i></td><td width='10%'><i><div align='center'>Facturar</div></i></td></tr>");  //
			
				
			out.print("<tr><td><input type=text id='desc0' size='80' onkeyup='autocompletaPacientesh(0,0,1)' >"); 
			out.print("<input name='desch0' type='hidden' id='desch0'/></td>");
			out.print("<td><input type=text id='eps0' size='45' readonly='readonly' >"); 
			out.print("<input name='epsh0' type='hidden' id='epsh0'/></td>");
			out.print("<td><input type=text id='adm0' size='7' readonly='readonly' >"); 
			out.print("<td><input id='iraFact' type=button value='Facturar' disabled='true' ></td></tr>"); 
		
					
			out.print("<tr><td><div id='sugerencias0' ></div></td></tr></table>");
			out.print("</table>");
			out.print("<tr><td><div id='resul2' ></div></td></tr></table>");
			out.print("<tr><td><div id='resul' ></div></td></tr></table>");
			out.print("<tr><td><div id='resulcrea' ></div></td></tr></table>");
			
		
		}*/
		

if(va.equals("1hA")){
			
			
			out.print("<table width='100%' class='style6'><tr><td colspan='5'><div align='center' class='style11' id='cabecera2'>Movimiento Ambulatorio </div></td></tr>");
			
			out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' >" +
					"<td width='30%'><i><div align='center'>Paciente</div></i></td>" +
					"<td width='20%'><i><div align='center'>Empresa</div></i></td>" +
					"<td width='10%'><i><div align='center'>Admisi�n</div></i></td>" +
					"<td width='10%'><i><div align='center'>Fecha Ingreso</div></i></td>" +
					"<td width='10%'><i><div align='center'>Fecha Egreso</div></i></td>" +
					"<td width='10%'><i><div align='center'>Num Autorizacion</div></i></td>" +
					"<td width='10%'><i><div align='center'>Facturar</div></i></td></tr>");  //
			
				
			out.print("<tr><td><input type=text id='desc0' size='80' onfocus='limpiarmovimientos(1)' onkeyup='autocompletaPacientesh(0,0,1)' >"); 
			out.print("<input name='desch0' type='hidden' id='desch0'/></td>");
			//out.print("<td><input type=text id='eps0' size='45' readonly='readonly' >"); 
			out.print("<td><div id='depssh'><select  name='epsh0' id='epsh0' style='width:100%'  ><option value='Seleccione'>Seleccione</option></select></div>");//style='width:220px' 
			//out.print("<input name='epsh0' type='hidden' id='epsh0'/></td>");
			out.print("<td><input type=text id='adm0' size='7' readonly='readonly' >");
			out.print("<td><input type=text id='FechaIngreso' size='7' readonly='readonly' >"); 
			out.print("<td><input type=text id='FechaEgreso' size='7' readonly='readonly' >"); 
			out.print("<td><input type=text id='NumAutoriza' size='7' readonly='readonly' >"); 
			out.print("<td><input id='iraFact' type=button value='Facturar' disabled='true' ></td></tr>"); 
			
			out.print("<input name='hoa' type='hidden' id='hoa' value='"+1+"' />");
					
			out.print("<tr><td><div id='sugerencias0' ></div></td></tr></table>");
			out.print("</table>");
			out.print("<tr><td><div id='resul2' ></div></td></tr></table>");
			out.print("<tr><td><div id='resul' ></div></td></tr></table>");
			out.print("<tr><td><div id='resulcrea' ></div></td></tr></table>");
			
		
		}
		
		if(va.equals("1ce")){
			
			
			out.print("<table width='100%' class='style6'><tr><td colspan='5'><div align='center' class='style11' id='cabecera2'>Movimiento Diaro </div></td></tr>");
			
			out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='60%'><i><div align='center'>Paciente</div></i></td><td width='30%'><i><div align='center'>Empresa</div></i></td><td width='10%'><i><div align='center'>Admisi�n</div></i></td></tr>");  //
			
			out.print("<tr><td><input type=text id='desc0' size='90' onkeyup='autocompletaPacientesCE(0,0)' >"); 
			out.print("<input name='desch0' type='hidden' id='desch0'/></td>");
			out.print("<td><input type=text id='eps0' size='45' readonly='readonly' >"); 
			out.print("<input name='epsh0' type='hidden' id='epsh0'/></td>");
			out.print("<td><input type=text id='adm0' size='10' readonly='readonly' ></td></tr>"); 
			
			out.print("<tr><td><div id='sugerencias0' ></div></td></tr></table>");
			out.print("</table>");
			out.print("<tr><td><div id='resul2' ></div></td></tr></table>");
			out.print("<tr><td><div id='resul' ></div></td></tr></table>");
			out.print("<tr><td><div id='resulcrea' ></div></td></tr></table>");
			
		
		}
		
		
		if(va.equals("2")){
		String opcion = req.getParameter("opcion");	
		out.print("<table width='100%' border='1' class='style6' ><tr >" +
				"<td width='5%'></td><td width='43%'></td>" +
				"<td width='6%'></td><td width='5%'></td>" +
				"<td width='5%'></td><td width='7%'></td></tr>");
	
		out.print("<input name='lotil' type='hidden' id='lotil' value='"+lot+"' />");
		out.print("<input name='encas' type='hidden' id='encas' value='"+venc+"' />");
		
		rs1=mm.FactEncabezado(venc, lot);
		try {
			while(rs1.next()){
				rs2=mm.FactDetalle(rs1.getString(1));
				out.print("<input name='enca' type='hidden' id='enca' value='"+rs1.getString(1)+"' />");
				try {
					while(rs2.next()){
						if(rs2.getString(2).equals("1")){
							out.print("<td><input type=text id='refdescr' size='5' readonly='readonly' value='"+rs2.getString(3)+"' style='border: 0'></td>");
							out.print("<td><input type=text id='descr' size='93' readonly='readonly' value='"+rs2.getString(4)+"' style='border: 0'></td>");
							out.print("<td>"+rs2.getString(7)+"</td>");	
							out.print("<td>"+rs2.getString(8)+"</td>");
							out.print("<td align='right'>"+mm.formatMoneda(rs2.getString(9))+"</td>");
							if(rs2.getString(10).equals("0")){
								out.print("<td><div align='center'><a  href='#'onclick='EliminarCargue("+rs2.getString(1)+","+venc+","+lot+",&quot;"+opcion+"&quot;)'>Anular</a></div></td></tr>");
							}else{
								out.print("<td><div align='center'><a   disabled='true' href='#'onclick=''>Anular</a></div></td></tr>");
							}
							
						}else{
							out.print("<td><input type=text id='refdescr' size='5' readonly='readonly' value='"+rs2.getString(5)+"'></td>");
							out.print("<td><input type=text id='descr' size='93' readonly='readonly' value='"+rs2.getString(6)+" ("+rs2.getString(4)+")'></td>");
							out.print("<td>"+rs2.getString(7)+"</td>");	
							out.print("<td>"+rs2.getString(8)+"</td>");
							out.print("<td align='right'>"+mm.formatMoneda(rs2.getString(9))+"</td>");
							if(rs2.getString(10).equals("0")){
								out.print("<td><div align='center'><a  href='#'onclick='EliminarCargue("+rs2.getString(1)+","+venc+","+lot+")'>Anular</a></div></td></tr>");
							}else{
								out.print("<td><div align='center'><a   disabled='true' href='#'onclick=''>Anular</a></div></td></tr>");
							}
						}
						
					}
					rs2.getStatement().getConnection().close();
					} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
					}
				////////////////////
			}
			rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
			}
			
		}//fin va=2
		
	
		if(va.equals("2h")){
			
			System.out.println("2h valores de lot"+lot+" venc"+venc);
			String opcion = req.getParameter("opcion");
			int numMov=0;
			out.print("<table width='100%' border='1' class='style6' ><tr ><td width='5%'><i><div align='center'></div></i></td><td width='44%'><i><div align='center'></div></i></td><td width='6%'><i><div align='center'></div></i></td><td width='5%'><i><div align='center'></div></i></td></i></td><td width='5%'><i><div align='center'></div></i></td><td width='6%'><i><div align='center'></div></i></td></tr>");
		
			out.print("<input name='lotil' type='hidden' id='lotil' value='"+lot+"' />");
			out.print("<input name='encas' type='hidden' id='encas' value='"+venc+"' />");
			
			rs1=mm.FactEncabezado(venc, lot);
			try {
				while(rs1.next()){
					rs2=mm.FactDetalle(rs1.getString(1));
					out.print("<input name='enca' type='hidden' id='enca' value='"+rs1.getString(1)+"' />");
					
					try {
						while(rs2.next()){
							numMov++;
							if(rs2.getString(2).equals("1")){
								out.print("<td><input type=text id='refdescr' size='5' readonly='readonly' value='"+rs2.getString(3)+"'></td>");
								out.print("<td><input type=text id='descr' size='93' readonly='readonly' value='"+rs2.getString(4)+"'></td>");
								out.print("<td>"+rs2.getString(7)+"</td>");	
								out.print("<td>"+rs2.getString(8)+"</td>");
								out.print("<td align='right'>"+mm.formatMoneda(rs2.getString(9))+"</td>");
								if(rs2.getString(10).equals("0")){
									out.print("<td><div align='center'><a  href='#'onclick='EliminarCargueh("+rs2.getString(1)+","+venc+","+lot+",&quot;"+opcion+"&quot;)'>Anular</a></div></td></tr>");
								}else{
									out.print("<td><div align='center'><a   disabled='true' href='#'onclick=''>Anular</a></div></td></tr>");
								}							}else{
								out.print("<td><input type=text id='refdescr' size='5' readonly='readonly' value='"+rs2.getString(5)+"'></td>");
								out.print("<td><input type=text id='descr' size='93' readonly='readonly' value='"+rs2.getString(6)+" ("+rs2.getString(4)+")'></td>");
								out.print("<td>"+rs2.getString(7)+"</td>");	
								out.print("<td>"+rs2.getString(8)+"</td>");
								out.print("<td align='right'>"+mm.formatMoneda(rs2.getString(9))+"</td>");
								if(rs2.getString(10).equals("0")){
									out.print("<td><div align='center'><a  href='#'onclick='EliminarCargueh("+rs2.getString(1)+","+venc+","+lot+",&quot;"+opcion+"&quot;)'>Anular</a></div></td></tr>");
								}else{
									out.print("<td><div align='center'><a   disabled='true' href='#'onclick=''>Anular</a></div></td></tr>");
								}							}
							
						}
						rs2.getStatement().getConnection().close();
						} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
						}
					////////////////////
				}
				rs1.getStatement().getConnection().close();
				} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
				}
				out.print("|"+numMov);
				
			}//fin va=2
		
		
		if(va.equals("3")){
			String amb=req.getParameter("amb");
			//La parte de crear tarifa.
			out.print("<table width='100%' class='style6'>" +
					"<tr>" +
					"<td colspan='5'><div align='center' class='style11' id='cabecera2'>Cargar Movimientos</div></td>" +
					"</tr>");
			out.print("<tr>" +
					"<td>Fecha de Movimiento: <input type=text id='fechamo' size='7%' onKeyup='masca(this,patron,true,0,0,0)' value='"+estancia+"' onBlur='Estancias("+enca+",&quot;"+amb+"&quot;);'></td>" +
			        "</tr>" +
			        "</table>");
			out.print("<table width='100%' border='1' >" +
					"<tr BGCOLOR='#D3D3D3' >" +
					//"<td width='8%'><i><div align='center'>No.Ref.1</div></i></td>" +
					"<td colspan='2' ><i><div align='center'>Programa</div></i></td>" +
					"<td width='20%'><i><div align='center'>Servicio</div></i></td>" +
					"<td width='5%'><i><div align='center'>Cantidad</div></i></td>" +
					//"</i></td>" +
					"<td width='10%'><i><div align='center'>Medico</div></i></td>" +
					"<td width='3%'><i><div align='center'>Valor</div></i></td>" +
					"<td width='3%'><i><div align='center'>Acci�n</div></i></td>" +
					"</tr>");  //
			out.print("<tr>" +
					"  <td colspan='2'><input type=text id='desct0' style='width:100%'  onkeyup='autocompletaPYS(0,0)' ><input name='descth0' type='hidden' id='descch0'  /><input name='ref0' type='hidden' id='ref0'  /></td>");
			//out.print("<td><input type=text id='desct0' style='width:100%' onkeyup='autocompletaPYS(0,0)'  ></td>");			
			out.print("<td><div id='servic'><input type='text' id='serv0' style='width:100%' ><input name='servich0' type='hidden' id='servich0' /></div></td>"); 
			out.print("<td><input type=text id='cant0' style='width:100%'  onkeyup='tab(this,0); checknumcantidad(0);' onkeypress='return limita(5,this);'></td>"); 
			out.print("<td><div id='medic0' ><input type='text'  style='width:100%' name='med0' id='med0' /></div></td>"); 
			out.print("<td><div id='vv'><input  type=text id='valorpyp0' style='width:100%' readonly='readonly' value='0'  ></div></td>");
			out.print("<td><div id='carga'  align='center'><a disabled='disabled' id='cargar'>Cargar</a></div></td> " +
					"</tr>" +
					"");
			out.print("<tr>" +
					//"<td><div id='sugerencias3210' ></div></td>" +
					"<td colspan='2'><div id='sugerencias210' ></div></td>" +
					"<td></td>" +
					"<td></td>" +
					"<td><div id='sugerencias432101' ></div></td>" +
					"</tr>" +
					"</table>");
		
		}//fin va=2

		/*
		if(va.equals("3")){
			String amb=req.getParameter("amb");
			//La parte de crear tarifa.
			out.print("<table width='100%' class='style6'>" +
					"<tr>" +
					"<td colspan='5'><div align='center' class='style11' id='cabecera2'>Cargar Movimientos</div></td>" +
					"</tr>");
			out.print("<tr>" +
					"<td>Fecha de Movimiento: <input type=text id='fechamo' size='7%' onKeyup='masca(this,patron,true,0,0,0)' value='"+estancia+"' onBlur='Estancias("+enca+",&quot;"+amb+"&quot;);'></td>" +
			        "</tr>" +
			        "</table>");
			out.print("<table width='100%' border='1' class='style6' id='lineasCreacion'>" +
					"<tr BGCOLOR='#D3D3D3' >" +
					"<td width='8%'><i><div align='center'>No.Ref.1</div></i></td>" +
					"<td width='30%'><i><div align='center'>Programa</div></i></td>" +
					"<td width='20%'><i><div align='center'>Servicio</div></i></td>" +
					"<td width='5%'><i><div align='center'>Cantidad</div></i></td>" +
					//"</i></td>" +
					"<td width='10%'><i><div align='center'>Medico</div></i></td>" +
					"<td width='3%'><i><div align='center'>Valor</div></i></td>" +
					"<td width='3%'><i><div align='center'>Acci�n</div></i></td>" +
					"</tr>");  //
			out.print("<tr>" +
					"  <td><input type=text id='ref0' style='width:100%'  onkeyup='autocompletaRef(0,0)' ></td>");
			out.print("<td><input type=text id='desct0' style='width:100%' onkeyup='autocompletaPYS(0,0)'  >"); 
			out.print("<input name='descth0' type='hidden' id='descch0'  /></td>");			
			out.print("<td><div id='servic' ><input type='text' name='serv0' id='serv0' style='width:100%' ><input name='servich0' type='hidden' id='servich0' /></div></td>"); 
			out.print("<td><input type=text id='cant0' style='width:100%'  onkeyup='tab(this,0); checknumcantidad(0);' onkeypress='return limita(3,this);'></td>"); 
			out.print("<td><div id='medic0' ><input type='text'  style='width:100%' name='med0' id='med0' /></div></td>"); 
			out.print("<td><div id='vv' ><input readonly='readonly' type=text id='valorpyp0' style='width:100%'  ></div></td>");
			out.print("<td><div id='carga'  align='center'><a disabled='disabled' id='cargar'>Cargar</a></div></td> " +
					"</tr>" +
					"");
			out.print("<tr>" +
					"<td><div id='sugerencias3210' ></div></td>" +
					"<td><div id='sugerencias210' ></div></td>" +
					"<td></td>" +
					"<td></td>" +
					"<td><div id='sugerencias432101' ></div></td>" +
					"</tr>" +
					"</table>");
		
		}//fin va=2
		*/
		
		
		if(va.equals("3h")){
			String opcion=req.getParameter("opcion");
			String taq2=req.getParameter("taq2");
			String taq3=req.getParameter("taq3");
			if(taq2.equals(null)){taq2="Seleccione";}
			if(taq3.equals(null)){taq3="Seleccione";}
			if(taq2.equals("undefined")){taq2="Seleccione";}
			if(taq3.equals("undefined")){taq3="Seleccione";}
			
			//La parte de crear tarifa.
				
			out.print("<div width='100%' class='style6'><div align='center' class='style11' id='cabecera2'>Cargar Movimientos</div></div>");
			out.print("<div><div style='float:left;'>Fecha de Movimiento: <input type=text id='fechamo' size='7%' onKeyup='masca(this,patron,true,0,0,0)'  value='"+estancia+"'  onBlur='Estanciash("+enca+",&quot;"+opcion+"&quot;);'></div>");
			rs1=mm.listarFormasActoQx();
			try {
			out.print("<div style='float:left;margin-top:1px;margin-left:20px;'>Tipo de Acto Quir�rgico: <select  style='width:442px' name='taq' id='taq0' onchange='resetPr()'><option value='Seleccione'>Seleccione</option>");
				while(rs1.next()){
				out.print("<option value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("</select></div>");
			out.print("<div id='divaqx' style='position:absolute;margin-left:20px;width=200px;' >Acto Qx: <input type='text' id='pr' class='rout' value='Seleccione' readonly='true' tabindex = '-1' unselectable ='on' onfocus = 'this.blur()' onclick='mostrarActoQx()' onmouseover='rolloverIn()' onmouseout='rolloverOut()'/> <input type='hidden' name='actqx' id='actqx0' value='Seleccione' /><div id='mensajeDetEnca4' class='mensajeDetEnca4' ></div></div>"); 
			out.print("<table width='100%' border='1' class='style6' id='lineasCreacion'><tr BGCOLOR='#D3D3D3' ><td width='8%'><i><div align='center'>No.Ref.4</div></i></td><td width='30%'><i><div align='center'>Programa</div></i></td><td width='20%'><i><div align='center'>Servicio</div></i></td><td width='5%'><i><div align='center'>Cantidad</div></i></td><td width='10%'><i><div align='center'>Medico</div></i></td><td width='3%'><i><div align='center'>Valor</div></i></td><td width='3%'><i><div align='center'>Acci�n</div></i></td></tr>");  //
			out.print("<tr><td><input type=text id='ref0' style='width:100%;' ");
			if(opcion.equals("undefined")){
				out.print(" onkeyup='autocompletaRef(0,0)' ");
			}else{
				if(opcion.equals("amb")){
					out.print(" onkeyup='autocompletaRefMD(0,0,&quot;"+opcion+"&quot;)' ");
				}
			}
			
		//	out.print("onfocus='Estanciash("+enca+",&quot;"+opcion+"&quot;);' >");
			out.print("</td><td><input type=text id='desct0' style='width:100%;' ");
			if(opcion.equals("undefined")){
				out.print(" onkeyup='autocompletaPYS(0,0)' ");
			}else{
				if(opcion.equals("amb")){
					out.print(" onkeyup='autocompletaPYSMD(0,0,&quot;"+opcion+"&quot;)' ");
				}
			}
			
			//out.print("onfocus='Estanciash("+enca+",&quot;"+opcion+"&quot;);' >"); 
			out.print("<input name='descth0' type='hidden' id='descch0'  /></td>");
			out.print("<td><div id='servic' ><select  style='width:220px' name='serv0' id='serv0' ><option value=''></option></select><input name='servich0' type='hidden' id='servich0'  /></div></td>"); 
			out.print("<td><input type=text id='cant0' style='width:100%;' onkeyup='tab(this,0);checknumcantidad(0);' ></td>"); 
			out.print("<td><div id='medic0' ><input type='text'  name='med0' id='med0' /></div></td>"); 
			out.print("<td><div id='vv' ><input readonly='readonly' type=text id='valorpyp0' size='8%'  ></div></td>");
			out.print("<td><div id='carga'  align='center'><a disabled='disabled' id='cargar'>Cargar</a></div></td></tr>");
			out.print("<tr><td><div id='sugerencias3210' ></div></td><td><div id='sugerencias210' ></div></td><td></td><td></td><td><div id='sugerencias432101' ></div></td></tr></table>");
		
		}
		
		if(va.equals("3CE")){
			//La parte de crear tarifa.
			String a1=fece.substring(0, 4);
			String m1=fece.substring(5, 7);
			String d1=fece.substring(8, 10);
			String fecen=d1+"/"+m1+"/"+a1;
			
			out.print("<table width='100%' class='style6'><tr><td colspan='5'><div align='center' class='style11' id='cabecera2'>Cargar Movimientos</div></td></tr>");
			out.print("<td>Fecha de Movimiento: <input type=text id='fechamo' size='7%' value='"+fecen+"' readonly='readonly' ></td>");
			out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='8%'><i><div align='center'>No.Ref.2</div></i></td><td width='30%'><i><div align='center'>Programa</div></i></td><td width='20%'><i><div align='center'>Servicio</div></i></td><td width='5%'><i><div align='center'>Cantidad</div></i></td></i></td><td width='10%'><i><div align='center'>Medico</div></i></td><td width='3%'><i><div align='center'>Valor</div></i></td><td width='3%'><i><div align='center'>Acci�n</div></i></td></tr>");  //
			out.print("<tr><td><input type=text id='ref0' style='width:100%;'  onkeyup='autocompletaRef(0,0)' ></td>");
			out.print("<td><input type=text id='desct0' style='width:100%;' onkeyup='autocompletaPYS(0,0)' >"); 
			out.print("<input name='descth0' type='hidden' id='descch0'  /></td>");
			out.print("<td><div id='servic' ><select  style='width:220px' name='serv0' id='serv0' ><option value=''></option><input name='servich0' type='hidden' id='servich0'  /></div></td>"); 
			out.print("<td><input type=text id='cant0' style='width:100%;'  onkeypress='return limita(3,this);' onkeyup='checknumcantidad(0);'></td>"); 
			out.print("<td><div id='medic0' ><select  style='width:100%' name='med0' id='med0' ></div>"); 
			//out.print("<select  style='width:120px' name='med0' id='med0' ><option value='Seleccione'>Seleccione</option>");
			/*rs1=mm.listarMedce(mece);
			try {
				while(rs1.next()){
				out.print("<option value="+rs1.getString(1)+">"+rs1.getString(2)+" "+rs1.getString(3)+"</option>");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("</td></select>");*/
						
			out.print("<td><div id='vv' ><input readonly='readonly' type=text id='valorpyp0' size='10%'  ></div></td>");
			//out.print("");
			//out.print("<input name='fecec' type='hidden' id='fecec'  value='"+fece+"'/></td>");
			
			out.print("<td><div id='carga'  align='center'><a  href='#'onclick='CargarFactce("+enca+","+mece+","+fece+")'>Cargar</a></div></td>");
			out.print("<tr><td><div id='sugerencias3210' ></div></td><td><div id='sugerencias210' ></div></td><td></td><td></td><td><div id='sugerencias432101' ></div></td></tr></table>");
		
		}//fin va=2
		
if(va.equals("3h1")){
			
			String eps=req.getParameter("eps");
			
			rs1=mm.obtenerManualBase(eps);
			String ManualBase = "";
			try {
				while(rs1.next()){
					ManualBase = rs1.getString(1);
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			String[] detManualBase = ManualBase.split(",");
			if(detManualBase.length>1){
				rs1=mm.listarActoQx(taq,detManualBase[0],detManualBase[1]);
			}else{
				rs1=mm.listarActoQx(taq,detManualBase[0],detManualBase[0]);
			}
			out.print("<ul id='acQx'><li onmouseover='lIn(this)' onmouseout='lOut(this)'  value='Seleccione' onclick='seleccionarli(this)'>Seleccione</li>");
			try {
				while(rs1.next()){
				out.print("<li id='lista' value='"+rs1.getString(1)+"' onmouseover='lIn(this)' onmouseout='lOut(this)' onclick='seleccionarli(this)'>"+rs1.getString(2)+"</li>");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("</ul>");
		}
		
		
		if(va.equals("4")){
			
			String eps=req.getParameter("eps");
			
			//System.out.println(mtarp+" + "+critp+" + "+n+" + "+estancia+" + "+enca+" + "+eps);
			
			out.print("<input name='pop' type='hidden' id='pop'  value='"+mtarp+"'/></td>");
		//	out.print("<td>"+mtarp+"</td>");
			
			if(mtarp.equals("2")){			
				rs=mm.listarS(critp);
				try {
					if(rs.next()){											
						rs1=mm.listarSYP(critp,eps);				
						out.print("<table width='100%' class='style6'><tr><td colspan='5'><div align='center' class='style11' id='cabecera2'>Cargar Movimientos</div></td></tr>");
						out.print("<td>Fecha de Movimiento: <input type=text id='fechamo' size='7%' onKeyup='masca(this,patron,true,0,0,0)' value='"+estancia+"' onBlur='Estancias("+enca+");'></td>");
						out.print("<table width='100%' border='1' class='style6' id='lineasCreacion'><tr BGCOLOR='#D3D3D3' ><td width='8%'><i><div align='center'>No.Ref.3</div></i></td><td width='30%'><i><div align='center'>Programa</div></i></td><td width='20%'><i><div align='center'>Servicio</div></i></td><td width='5%'><i><div align='center'>Cantidad</div></i></td></i></td><td width='10%'><i><div align='center'>Medico</div></i></td><td width='3%'><i><div align='center'>Valor</div></i></td><td width='3%'><i><div align='center'>Acci�n</div></i><input type='checkbox' name='all' id='all' onclick='vcarga()'></td></tr>");  //
						int j=0;
						int cs=0;
						try {
							while(rs1.next()){
								cs++;
								out.print("<div id='j"+j+"'>");
								out.print("<tr><td><input type='hidden' id='ref"+j+"' style='width:100%;'   onfocus='Estancias("+enca+");' value='"+rs.getString(1)+"'></td>");
								out.print("<td><input type=text id='desct"+j+"' style='width:100%' onkeyup='autocompletaPYS(0,0)' onfocus='Estancias("+enca+");' value='"+rs.getString(2)+"'>"); 
								out.print("<input name='descth0' type='hidden' id='descch"+j+"' value="+critp+" /></td>");
								out.print("<td><div id='servic' ><select   style='width:100%' name='serv0' id='serv"+j+"' ><option title='"+rs1.getString(3)+"' value="+rs1.getString(1)+">"+rs1.getString(3)+"</option><input name='servich0' type='hidden' id='servich"+j+"'  value='"+rs1.getString(3)+"'></div></td>"); 
								out.print("<td><input type=text id='cant"+j+"' style='width:100%;' value='1' onkeyup='tab(this,"+j+"); checknumcantidad("+j+");'></td>"); 
								out.print("<td><div id='medic"+j+"' >");
								//out.print("<input type=text name='med0'  size='10%'  id='med"+j+"' style='width:100%;'  onkeyup='autocompletaMed(0,0)' >");
								out.print("<select id='med"+j+"' ><option value='-'>Seleccione</option>");
								rs2=mm.listarMedSelect();
								while(rs2.next()){
									out.print("<option value="+rs2.getString("usu_codigo")+">"+rs2.getString("NombreMedico")+"</option>");
								}
								rs2.getStatement().getConnection().close();
								out.print("</select>");
								out.print("<input name='medh0' type='hidden' id='medh"+j+"' /></td>");
				
			
								out.print("<td><div id='vv' ><input readonly='readonly' type=text id='valorpyp"+j+"' size='10%' value="+rs1.getString(5)+" ></div></td>");
								//out.print("");
								out.print("<td><input type='checkbox' name='checkbox' id='carga"+j+"' value='"+j+"' ></td>");
								//out.print("<td><a  href='#'onclick='CargarFact("+encab+","+j+",2)'>Cargar</a></td>");
								//out.print("<td><div id='carga'  align='center'><a disabled='disabled'>Cargar</a></div></td>");
								out.print("<tr><td><div id='sugerencias3210' ></div></td><td><div id='sugerencias210' ></div></td><td></td><td></td><td><div id='sugerencias432101' ></div></td><td></td><td></td></tr>");
								j++;
							}
							if(cs==0){out.print("<td colspan='5'>Este servicio no posee ningun programa con tarifa vigente!!!  </td>");}
							else{
								out.print("<tr><td></td><td></td><td></td><td></td><td></td><td></td><td><a  href='#'onclick='CargarFactS("+encab+")'>Cargar</a></td></tr>");
							}
							
							rs1.getStatement().getConnection().close();
						} catch (SQLException e) {
							out.print("Error "+e);
							e.printStackTrace();
						}
						out.print("</table>");
				
					}
					rs.getStatement().getConnection().close();
				}catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				out.print("</div>");					
			}else{
				out.print("<select  style='width:220px' name='serv0' id='serv0' ><option value='"+critp+"'>"+n+"</option>");
			}
		}//fin va=4
		
		
if(va.equals("4h")){
		String opcion=req.getParameter("opcion");
			String eps=req.getParameter("eps");
			out.print("<input name='pop' type='hidden' id='pop'  value='"+mtarp+"'/></td>");
			
			String taqp=req.getParameter("taq");
			if(taqp==null){taqp="Seleccione";}
			String taqp3=req.getParameter("taq3");
			if(taqp3==null){taqp3="Seleccione";}
			String aqp=req.getParameter("aq");
			if(aqp==null){aqp="Seleccione";}
			String aqp3=req.getParameter("aq3");
			if(aqp3==null){aqp3="Seleccione";}
			
			if(mtarp.equals("2")){
				
				int j=0;
				int cs=0;				
					rs=mm.listarS(critp);				
				try {
					if(rs.next()){
							rs1=mm.listarSYP(critp,eps);
					out.print("<div width='100%' class='style6'><div align='center' class='style11' id='cabecera2'>Cargar Movimientos</div></div>");
					out.print("<div><div style='float:left;'>Fecha de Movimiento: <input type=text id='fechamo' size='7%' onKeyup='masca(this,patron,true,0,0,0)'  value='"+estancia+"'  onBlur='Estanciash("+enca+");'></div>");
					rs2=mm.listarFormasActoQx();
					try {
					out.print("<div style='float:left;margin-top:1px;margin-left:20px;'>Tipo de Acto Quir�rgico: <select  style='width:442px' name='taq' id='taq0' onchange='resetPr()'><option value='Seleccione'>Seleccione</option>");
						while(rs2.next()){
						out.print("<option value="+rs2.getString(1)+">"+rs2.getString(2)+"</option>");
						}
						rs2.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					out.print("</select></div>");
					out.print("<div id='divaqx' style='position:absolute;margin-left:20px;width=200px;' >Acto Qx: <input type='text' id='pr' class='rout' value='Seleccione' readonly='true' tabindex = '-1' unselectable ='on' onfocus = 'this.blur()' onclick='mostrarActoQx()' onmouseover='rolloverIn()' onmouseout='rolloverOut()'/> <input type='hidden' name='actqx' id='actqx0' value='Seleccione' /><div id='mensajeDetEnca4' class='mensajeDetEnca4' ></div></div>"); 
				//////
				
				
				out.print("<table width='100%' border='1' class='style6' id='lineasCreacion'><tr BGCOLOR='#D3D3D3' ><td width='8%'><i><div align='center'>No.Ref.5</div></i></td><td width='30%'><i><div align='center'>Programa</div></i></td><td width='20%'><i><div align='center'>Servicio</div></i></td><td width='5%'><i><div align='center'>Cantidad</div></i></td></i></td><td width='10%'><i><div align='center'>Medico</div></i></td><td width='3%'><i><div align='center'>Valor</div></i></td><td width='3%' id='accCarg'><i><div align='center'>Acci�n</div></i></td></tr>");  //
				
				String vp="";
				String vpn="";
				try {
				while(rs1.next()){
					
					cs++;	
				out.print("<tr  id='row"+j+"'><td><input type=text id='ref"+j+"' style='width:100%;'  onkeyup='autocompletaRef(0,0)' onfocus='Estanciash("+enca+");' value="+rs.getString(1)+"></td>");
				out.print("<td><input type=text id='desct"+j+"' style='width:100%;' onkeyup='autocompletaPYS(0,0)' onfocus='Estanciash("+enca+");' value='"+rs.getString(2)+"'>");
				out.print("<input name='descth0' type='hidden' id='descch"+j+"' value="+critp+"  /></td>");
				out.print("<td><div id='servic' ><select  style='width:220px' name='serv0' id='serv"+j+"' ><option  title='"+rs1.getString(3)+"'  value="+rs1.getString(1)+">"+rs1.getString(3)+"</option><input name='servich0' type='hidden' id='servich"+j+"' value='"+rs1.getString(3)+"' /></div></td>"); 
				out.print("<td><input type=text id='cant"+j+"' style='width:100%;'  value='1' readonly='true' onkeyup='tab(this,"+j+"); checknumcantidad("+j+");'></td>"); 
				
				out.print("<td><div id='medic"+j+"' ><input type=text name='med0' id='med"+j+"' size='10%'  onkeyup='autocompletaMed(0,0,"+cs+")' >");				
				

				out.print("<input name='medh0' type='hidden' id='medh"+j+cs+"' /></td>");
				out.print("<input name='cse' type='hidden' id='cse"+j+"'  value='"+rs1.getString(8)+"'/></td>");

				if((taq.equals("6"))&&(j>0)){
					vpn=vp;
					out.print("<td><div id='vv' ><input readonly='readonly' type=text id='valorpyp"+j+"' size='10%'  value='"+vpn+"' ></div></td>");
				
				}else{
				vp=rs1.getString(5);
				out.print("<td><div id='vv' ><input readonly='readonly' type=text id='valorpyp"+j+"' size='10%'  value='"+rs1.getString(5)+"' ></div></td>");
				}
				
				out.print("<td id='chkCarg'><input type='checkbox'  name='checkbox' id='cargaCesar"+j+"' value='"+j+"' ></td>");
				out.print("<input name='raqx' type='hidden' id='raqx"+j+"'  value="+rs1.getString(4)+" ></td>");
				out.print("<input name='rmed' type='hidden' id='rmed"+j+"'  value="+rs1.getString(6)+" >");
				out.print("<input name='rporc' type='hidden' id='rporc"+j+"'  value="+rs1.getString(7)+" ></td>");
				
				if(cs==1){
					out.print("<tr id='sug"+j+"'><td><div id='sugerencias3210' ></div></td><td><div id='sugerencias210' ></div></td><td></td><td></td><td><div id='sugerencias43210"+cs+"' ></div></td></tr>");
					//System.out.println("<tr><td><div id='sugerencias3210' ></div></td><td><div id='sugerencias210' ></div></td><td></td><td></td><td><div id='sugerencias43210"+cs+"' ></div></td></tr>");

				}else{
					if(cs>1){
						out.print("<tr id='sug"+j+"'><td></td><td></td><td></td><td></td><td><div id='sugerencias43210"+cs+"' ></div></td></tr>");
						//System.out.println("<tr><td></td><td></td><td></td><td></td><td><div id='sugerencias43210"+cs+"' ></div></td></tr>");

					}
				}
				j++;
				}
				out.print("<tr id='hid'><td><input name='jj' type='hidden' id='jj'  value="+j+" ></td></tr>");
				
				if(cs==0){out.print("<td colspan='5'>Este servicio no posee ningun programa con tarifa vigente!!!  </td>");}
				else{
					out.print("<tr tr='carg'><td></td><td></td><td></td><td></td><td></td><td></td><td><a  href='#'onclick='CargarFactSh("+encab+",&quot;"+opcion+"&quot;)' id='cargar'>Cargar</a></td></tr>");
				}
				
				rs1.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				out.print("</table>|lt2");
				
					}
					rs.getStatement().getConnection().close();
					}catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
				
				
				
			}else{
				if(taqp.equals("Seleccione")||aqp.equals("Seleccione")){
					out.print("<input name='raqx' type='hidden' id='raqx0'  value='"+act+"'/></td>");
					out.print("<input name='rmed' type='hidden' id='rmed0'  value='"+med+"'/></td>");
					out.print("<select  style='width:220px' name='serv0' id='serv0' ><option value='"+critp+"'>"+n+"</option>|lt1");
				}else{
					rs = mm.obtenerEspPrograma(critp,eps);
					String espProg = "";
					int valorPrograma = 0;
					try {
						while(rs.next()){
							espProg = rs.getString(1);
							valorPrograma = Integer.parseInt(rs.getString(2));
						}
						rs.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					if(!espProg.equals("7")){
						out.print("<input name='raqx' type='hidden' id='raqx0'  value='"+act+"'/></td>");
						out.print("<input name='rmed' type='hidden' id='rmed0'  value='"+med+"'/></td>");
						out.print("<select  style='width:220px' name='serv0' id='serv0' ><option value='"+critp+"'>"+n+"</option>|lt1");
					}else{
						int j=0;
						int cs=0;				
							rs=mm.listarPH(critp);				
						try {
							if(rs.next()){
									rs1=mm.listarSYPHemodinamia(eps);
							out.print("<div width='100%' class='style6'><div align='center' class='style11' id='cabecera2'>Cargar Movimientos</div></div>");
							out.print("<div><div style='float:left;'>Fecha de Movimiento: <input type=text id='fechamo' size='7%' onKeyup='masca(this,patron,true,0,0,0)'  value='"+estancia+"'  onBlur='Estanciash("+enca+");'></div>");
							rs2=mm.listarFormasActoQx();
							try {
							out.print("<div style='float:left;margin-top:1px;margin-left:20px;'>Tipo de Acto Quir�rgico: <select  style='width:442px' name='taq' id='taq0' onchange='resetPr()'><option value='Seleccione'>Seleccione</option>");
								while(rs2.next()){
								out.print("<option value="+rs2.getString(1)+">"+rs2.getString(2)+"</option>");
								}
								rs2.getStatement().getConnection().close();
							} catch (SQLException e) {
								out.print("Error "+e);
								e.printStackTrace();
							}
							out.print("</select></div>");
							out.print("<div id='divaqx' style='position:absolute;margin-left:20px;width=200px;' >Acto Qx: <input type='text' id='pr' class='rout' value='Seleccione' readonly='true' tabindex = '-1' unselectable ='on' onfocus = 'this.blur()' onclick='mostrarActoQx()' onmouseover='rolloverIn()' onmouseout='rolloverOut()'/> <input type='hidden' name='actqx' id='actqx0' value='Seleccione' /><div id='mensajeDetEnca4' class='mensajeDetEnca4' ></div></div>"); 						
						
						out.print("<table width='100%' border='1' class='style6' id='lineasCreacion'><tr BGCOLOR='#D3D3D3' ><td width='8%'><i><div align='center'>No.Ref.6</div></i></td><td width='30%'><i><div align='center'>Programa</div></i></td><td width='20%'><i><div align='center'>Servicio</div></i></td><td width='5%'><i><div align='center'>Cantidad</div></i></td></i></td><td width='10%'><i><div align='center'>Medico</div></i></td><td width='3%'><i><div align='center'>Valor</div></i></td><td width='3%' id='accCarg'><i><div align='center'>Acci�n</div></i></td></tr>");  //
						
						//int vp=0;
						//String vpn="";
						try {
						while(rs1.next()){
							cs++;	
							System.out.println("");
						out.print("<tr  id='row"+j+"'><td><input type=text id='ref"+j+"' style='width:100%;'  onkeyup='autocompletaRef(0,0)' onfocus='Estanciash("+enca+");' value="+rs.getString(1)+"></td>");
						out.print("<td><input type=text id='desct"+j+"' style='width:100%;' onkeyup='autocompletaPYS(0,0)' onfocus='Estanciash("+enca+");' value='"+rs.getString(2)+"'>");
						out.print("<input name='descth0' type='hidden' id='descch"+j+"' value="+critp+"  /></td>");
						out.print("<td><div id='servic' ><select  style='width:220px' name='serv0' id='serv"+j+"' ><option  title='"+rs1.getString(3)+"'  value="+rs1.getString(1)+">"+rs1.getString(3)+"</option><input name='servich0' type='hidden' id='servich"+j+"' value='"+rs1.getString(3)+"' /></div></td>"); 
						out.print("<td><input type=text id='cant"+j+"' style='width:100%;'  value='1' readonly='true' onkeyup='tab(this,"+j+"); checknumcantidad("+j+");'></td>"); 
						out.print("<td><div id='medic"+j+"' ><input type=text name='med0' id='med"+j+"' size='10%'  onkeyup='autocompletaMed(0,0,"+cs+")' >");				
						out.print("<input name='medh0' type='hidden' id='medh"+j+cs+"' /></td>");
						out.print("<input name='cse' type='hidden' id='cse"+j+"'  value='"+rs1.getString(8)+"'/></td>");
					    int indice=Integer.parseInt(rs1.getString(7));
						String porcs = "";
						rs2=mm.PorcentajeQx(aqp);
						try {
							if(rs2.next()){
								porcs=rs2.getString(indice);
							}
							rs2.getStatement().getConnection().close();
						} catch (SQLException e) {
							out.print("Error "+e);
							e.printStackTrace();
						}
						if(porcs.equals("")){
							porcs = "100";
						}
						//int vp = Integer.parseInt(porcs);
						out.print("<td><div id='vv' ><input readonly='readonly' type=text id='valorpyp"+j+"' size='10%'  value='"+valorPrograma+"' ></div></td>");	
						out.print("<td id='chkCarg'><input type='checkbox'  name='checkbox' id='cargaCesar"+j+"' value='"+j+"' ></td>");
						out.print("<input name='raqx' type='hidden' id='raqx"+j+"'  value="+rs1.getString(4)+" ></td>");
						out.print("<input name='rmed' type='hidden' id='rmed"+j+"'  value="+rs1.getString(6)+" >");
						out.print("<input name='rporc' type='hidden' id='rporc"+j+"'  value="+rs1.getString(7)+" ></td>");
						if(cs==1){
							out.print("<tr id='sug"+j+"'><td><div id='sugerencias3210' ></div></td><td><div id='sugerencias210' ></div></td><td></td><td></td><td><div id='sugerencias43210"+cs+"' ></div></td></tr>");
						}else{
							if(cs>1){
								out.print("<tr id='sug"+j+"'><td></td><td></td><td></td><td></td><td><div id='sugerencias43210"+cs+"' ></div></td></tr>");
							}
						}
						j++;
						}
						out.print("<tr id='hid'><td><input name='jj' type='hidden' id='jj'  value="+j+" ></td></tr>");
						
						if(cs==0){out.print("<td colspan='5'>Este servicio no posee ningun programa con tarifa vigente!!!  </td>");}
						else{
							out.print("<tr tr='carg'><td></td><td></td><td></td><td></td><td></td><td></td><td><a  href='#'onclick='CargarFactSh("+encab+",&quot;"+opcion+"&quot;)' id='cargar'>Cargar</a></td></tr>");
						}
						
						rs1.getStatement().getConnection().close();
						} catch (SQLException e) {
							out.print("Error "+e);
							e.printStackTrace();
						}
						out.print("</table>|hemodinamia");
						
							}
							rs.getStatement().getConnection().close();
							}catch (SQLException e) {
								out.print("Error "+e);
								e.printStackTrace();
							}
					}
				}
			}
			
		}//fin va=4
		
		if(va.equals("4a")){
		//	hay q modificar esta consulta poara  traer el valor de los programas
			String pp="";
			String pp1="";
			String pp2="";
			rs=mm.listarP(critp);
			try {
				if(rs.next()){
				pp=rs.getString(1);
				pp1=rs.getString(2);
				pp2=rs.getString(3);				
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			out.print("<input name='cse' type='hidden' id='cse'  value='"+pp1+"'/></td>");
			out.print("<input name='refhi' type='hidden' id='refhi'  value='"+pp+"'/></td>");
			out.print("<input name='subc' type='hidden' id='subc'  value='"+pp2+"'/></td>");
			
			    rs1=mm.listarSYPV(critp,ep);
				String vx="";
				try {
					if(rs1.next()){
					vx=rs1.getString(1);
					}
					rs1.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				System.out.print("cesaeaeea: "+caad);
				out.print("<input readonly='readonly' type=text id='valorpyp0' size='10%'  value='"+vx+"'><input name='servich0' type='hidden' id='servich0' value='"+caad+"' />");
				//out.print(vx);
		}//fin va=4
		
		
		if(va.equals("4ah")){
			//	hay q modificar esta consulta poara  traer el valor de los programas
				String pp="";
				String pp1="";
				String pp2="";
				String ra="";
				String me="";
				rs=mm.listarP(critp);
				try {
					if(rs.next()){
						//System.out.println(rs.getString(1));
					pp=rs.getString(1);//codigo de referencia
					pp1=rs.getString(2);//descripcion clase de servicio
					pp2=rs.getString(3);//subcentro de costo
					ra=rs.getString(4);//acto quirurgico
					me=rs.getString(5);//medico
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				
				
				if(mtarp.equals("1")){
					out.print("<input name='cse0' type='hidden' id='cse0'  value='"+pp1+"'/></td>");
					
				}
				
				out.print("<input name='raqx' type='hidden' id='raqx0'  value='"+ra+"'/></td>");
				out.print("<input name='rmed' type='hidden' id='rmed0'  value='"+me+"'/></td>");
				
				
				out.print("<input name='refhi' type='hidden' id='refhi'  value='"+pp+"'/></td>");
				out.print("<input name='subc' type='hidden' id='subc'  value='"+pp2+"'/></td>");

				    rs1=mm.listarSYPV(critp,ep);
					String vx="";
					try {
						if(rs1.next()){
						vx=rs1.getString(1);
						}
						rs1.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					out.print("<input readonly='readonly' type=text id='valorpyp0' size='10%'  value='"+vx+"'><input name='servich0' type='hidden' id='servich0' value='"+caad+"' />");

			}//fin va=4
		
		
		
		if(va.equals("5")){
			
			
			out.print("<td><input type=text name='med0' id='med0' size='10%'  onkeyup='autocompletaMed(0,0,1)' >");
			out.print("<input name='medh0' type='hidden' id='medh01' /></td>");
			
			/*	out.print("<select  style='width:120px' name='med0' id='med0' ><option value='Seleccione'>Seleccione</option>");
				rs1=mm.listarMed();
				try {
					while(rs1.next()){
					out.print("<option value="+rs1.getString(1)+">"+rs1.getString(2)+" "+rs1.getString(3)+"</option>");
					}
					rs1.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				out.print("</td></select>"); */
		}//fin va=5
		
if(va.equals("51")){
			
			String opcion=req.getParameter("opcion");
			String esti="";
			String estf="";
			
			String cs="0";
			if(opcion.equals("undefined")){
				rs1=mm.EstanciaUrg(adm01);
				
				try {
					if(rs1.next()){
						esti=rs1.getString(1);
						estf=rs1.getString(2);
						cs="1";
					}
					rs1.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
			}
			
			

			if(cs.equals("0")||opcion.equals("amb")){
				if(opcion.equals("amb")){
					rs2=mm.EstanciaAmb(adm01);
				}else{
					if(opcion.equals("undefined")){
						rs2=mm.EstanciaUrg2(adm01);
					}
				}
				
				try {
					if(rs2.next()){
						esti=rs2.getString(1);
					}
					rs2.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				
				estf=fechacjmysql;
			}
			if(estf==null){
				estf=fechacjmysql;
			}
			
			String dias=efe.substring(0, 2);
			String meses=efe.substring(3, 5);
			String anos=efe.substring(6, 10);
			String efes=anos+"-"+meses+"-"+dias;
			
			SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
			Date fecd = null;
			Date feci = null;
			Date fecf = null;
			
			System.out.println("fecha escrita" + efe);
			System.out.println("estancia inicial" +esti);
			System.out.println("estancia final" +estf);
			
			try {
				fecd= formatoDelTexto.parse(efes);
				feci= formatoDelTexto.parse(esti);
				fecf= formatoDelTexto.parse(estf);
			} catch (ParseException ex) {
			ex.printStackTrace();
			}
			
			String a1=esti.substring(0, 4);
			String m1=esti.substring(5, 7);
			String d1=esti.substring(8, 10);
			String e1=d1+"/"+m1+"/"+a1;
			
			String a2=estf.substring(0, 4);
			String m2=estf.substring(5, 7);
			String d2=estf.substring(8, 10);
			String e2=d2+"/"+m2+"/"+a2;
			
			int sw=0;
			if (fecd.compareTo(feci) < 0){
				sw=1;
			}
			if (fecd.compareTo(fecf) > 0){
				sw=1;
			}
			if (sw==1){
				out.print("Fecha por fuera del rango de estancia del paciente!!! \n");
				out.print("\nFecha Ingreso:    "+e1);
			//	if(opcion.equals("")){
					out.print("\nFecha Egreso:     "+e2);
			//	}
				
			}else{out.print("1");}
			
	}//fin va=5
			



		if(va.equals("51h")){
			String opcion=req.getParameter("opcion");
			String esti="";
			String estf="";
			String cs="0";
			if(opcion.equals("undefined")){
				rs1=mm.EstanciaHosp(adm01);
				try {
					if(rs1.next()){
						esti=rs1.getString(1);
						estf=rs1.getString(2);
						cs="1";
			//		out.print("<option value="+rs1.getString(1)+">"+rs1.getString(2)+" "+rs1.getString(3)+"</option>");
					}
					rs1.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
			}

				
			if(cs.equals("0")||opcion.equals("amb")){
				if(opcion.equals("amb")){
					rs2=mm.EstanciaAmb(adm01);
				}else{
					if(opcion.equals("undefined")){
						rs2=mm.EstanciaUrg2(adm01);
					}
				}
				try {
					if(rs2.next()){
						esti=rs2.getString(1);
					}
					rs2.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				
				estf=fechacjmysql;
			}
			if(estf==null){
				estf=fechacjmysql;
			}
			
			String dias=efe.substring(0, 2);
			String meses=efe.substring(3, 5);
			String anos=efe.substring(6, 10);
			String efes=anos+"-"+meses+"-"+dias;
			
			SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
			Date fecd = null;
			Date feci = null;
			Date fecf = null;
	
			try {
				fecd= formatoDelTexto.parse(efes);
				feci= formatoDelTexto.parse(esti);
				fecf= formatoDelTexto.parse(estf);
			} catch (ParseException ex) {
			ex.printStackTrace();
			}
			
			String a1=esti.substring(0, 4);
			String m1=esti.substring(5, 7);
			String d1=esti.substring(8, 10);
			String e1=d1+"/"+m1+"/"+a1;
			
			String a2=estf.substring(0, 4);
			String m2=estf.substring(5, 7);
			String d2=estf.substring(8, 10);
			String e2=d2+"/"+m2+"/"+a2;
			
			int sw=0;
			if (fecd.compareTo(feci) < 0){
				sw=1;
			}
			if (fecd.compareTo(fecf) > 0){
				sw=1;
			}
			if (sw==1){
				out.print("Fecha por fuera del rango de estancia del paciente!!! \n");
				out.print("\nFecha Ingreso:    "+e1);
				out.print("\nFecha Egreso:     "+e2);
			}else{out.print("1");}
			
	}//fin va=5
		
		if(va.equals("52")){
			out.print("<td><div id='carga' align='center'><a disabled='disabled'>Cargar</a></div></td>");
		}
		
		if(va.equals("53")){
			String opcion = req.getParameter("opcion");
			out.print("<td><div id='carga' align='center'><a  href='#'onclick='CargarFact("+enca+",0,&quot;"+opcion+"&quot;)' id='cargar'>Cargar</a></div></td>");
		}
		
		if(va.equals("53h")){
			String opcion = req.getParameter("opcion");
			out.print("<td><div id='carga' align='center'><a  href='#'onclick='CargarFacth("+enca+",&quot;"+opcion+"&quot;)' id='cargar'>Cargar</a></div></td>");
		}
		
		
		if(va.equals("6")){
			
			
			String di=mtarpp.substring(0, 2);
			String me=mtarpp.substring(3, 5);
			String a=mtarpp.substring(6, 10);
			String f=a+"-"+me+"-"+di;	
			
			
			Calendar calendario = Calendar.getInstance();
			//	Calendar calendario = new GregorianCalendar();
				int hora, minutos, segundos;
				hora =calendario.get(Calendar.HOUR_OF_DAY);
				minutos = calendario.get(Calendar.MINUTE);
				segundos = calendario.get(Calendar.SECOND);
				String hra= hora+":"+minutos+":"+segundos;
				
				
				String dias=fechacj.substring(0, 2);
				String meses=fechacj.substring(3, 5);
				String anos=fechacj.substring(6, 10);
				String fec=anos+"-"+meses+"-"+dias;
				rs3=mm.ObtenerCodigoAdmision(lot);
				String CodAdm="";
				String EncaNue="";
				String TipoFac="";
				try {
					if(rs3.next()){
						CodAdm=rs3.getString(1);
						TipoFac=rs3.getString(2);
						rs4=mm.obtenerInfoEncaActivo(CodAdm);
						if(rs4.next()){
							EncaNue=rs4.getString(1);
						}
						rs4.getStatement().getConnection().close();					
					}
					rs3.getStatement().getConnection().close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(TipoFac.equals("1")){
					//cargue de urgencia.
					if(pop.equals("1")){
						mm.CargarDetalle(fec,hra,pop,serv0,refhi,mbase0p,null,null,cse,f,desch0p,critp,Abreviado,EncaNue,venc,subc,null,"0");
					}else{
						mm.CargarDetalle(fec,hra,pop,serv0,refhi,mbase0p,nivel,xx,cse,f,desch0p,critp,Abreviado,EncaNue,venc,subc,null,"0");
					}	
				}else{
					//cargue hospitalizacion y ambulatorio
					if(pop.equals("1")){
						mm.CargarDetalle(fec,hra,pop,serv0,refhi,mbase0p,null,null,cse,f,desch0p,critp,Abreviado,lot,venc,subc,null,"0");
					}else{
						mm.CargarDetalle(fec,hra,pop,serv0,refhi,mbase0p,nivel,xx,cse,f,desch0p,critp,Abreviado,lot,venc,subc,null,"0");
					}	
				}
				
		}//fin va=6
		
		if(va.equals("6h")){
			//System.out.println("prueba");
			String movh=req.getParameter("movh");
			movh=movh.replace("|", "_");
			//movh=movh.replace("/", "-");
			movh = movh.substring(1,movh.length()-1);
			String V[] = movh.split("_");
			String rporc=req.getParameter("rporc");
			//System.out.println(movh);		   
			   Calendar calendario = Calendar.getInstance();
				//	Calendar calendario = new GregorianCalendar();
					int hora, minutos, segundos;
					hora =calendario.get(Calendar.HOUR_OF_DAY);
					minutos = calendario.get(Calendar.MINUTE);
					segundos = calendario.get(Calendar.SECOND);
					String hra= hora+":"+minutos+":"+segundos;
					
					
					String dias=fechacj.substring(0, 2);
					String meses=fechacj.substring(3, 5);
					String anos=fechacj.substring(6, 10);
					String fec=anos+"-"+meses+"-"+dias;
			   
					//System.out.println("//////////////////////////"); 
		    for(int i=0; i<V.length; i=i+17){
		    	
		    	pop = V[i+0];
		    	//System.out.println(pop);
				serv0 = V[i+1];
				//System.out.println(serv0);
				if(V[i+2].equals("na")){
					refhi = "";
				}else{
					refhi = V[i+2];
				}
				//System.out.println(serv0);
				
				mtarpp = V[i+3];
				//System.out.println(mtarpp);
				
				
				nivel = V[i+4];
				//System.out.println(nivel);
				texto = V[i+5];
				//System.out.println(texto);
				xx = V[i+6];
				//System.out.println(xx);
				mbase0p = V[i+7];
				//System.out.println(mbase0p);
				desch0p = V[i+8];
				//System.out.println(desch0p);
				venc = V[i+9];
				if(venc.equals("null")){
					venc="";
				}
				//System.out.println(venc);
				critp = V[i+10];
				//System.out.println(critp);
				lot = V[i+11];
				//System.out.println(lot);
				Abreviado = V[i+12];
				//System.out.println(Abreviado);
				cse = V[i+13];
				//System.out.println(cse);
				subc = V[i+14];
				//System.out.println(subc);
				
				actqx = V[i+15];
				if(actqx.equals("null")){
					actqx="";
				}
				//System.out.println(actqx);
				rporc = V[i+16];
				
				
				
				//System.out.println("//////////////////////////");

		    
		   
			    
			//System.out.print(movh);
			
			//if(rporc==null){rporc="";}
			//	ajax.send("valor=6&mtarp="+e+"&nivel="+ref0+"&texto="+descth0+"&xx="+desct0+"&mbase0p="+servich0+"&desch0p="+cant0+"&venc="+med0+"&critp="+valorpyp0+"&valor="+enca+"&abreviado="+txtCodusuario);
				//System.out.println("fecha: "+fec);
				String di=mtarpp.substring(0, 2);
				String me=mtarpp.substring(3, 5);
				String a=mtarpp.substring(6, 10);
				String f=a+"-"+me+"-"+di;	
				//System.out.println("fecha: "+mtarpp+" di: "+di+" me: "+me+" a: "+a+" f: "+f );
				
				
				
				
					//System.out.print("actooo: "+actqx);
				//	actqx
					//System.out.print("columna: "+rporc+" actoqx: "+actqx+"  " );
					//si rporc=null no hacer nada d esto y colocar el valor
					String critpn="";
					String porcs="";
					
					if((rporc==null)||(rporc.equals("null"))){
						critpn=critp;
					}else{
					
						int indice=Integer.parseInt(rporc);
						rs2=mm.PorcentajeQx(actqx);
						try {
							if(rs2.next()){
								porcs=rs2.getString(indice);
							}
							rs2.getStatement().getConnection().close();
						} catch (SQLException e) {
							out.print("Error "+e);
							e.printStackTrace();
						}
						if(porcs.equals("")){
							porcs = "100";
						}
						
						int critpe=Math.round(Integer.parseInt(critp)*(Float.parseFloat(porcs)/100));
						critpn=String.valueOf(critpe);
					}//else rporc==null
					
					if(actqx==null){actqx="";}
				if(pop.equals("1")){
				mm.CargarDetalle(fec,hra,pop,serv0,refhi,mbase0p,null,null,cse,f,desch0p,critpn,Abreviado,lot,venc,subc,actqx,"0");
				///caragar 2 programas mas para hemodinamia
				////fin 2 programas mas para hemodinamia			
				}else{
				mm.CargarDetalle(fec,hra,pop,serv0,refhi,mbase0p,nivel,xx,cse,f,desch0p,critpn,Abreviado,lot,venc,subc,actqx,porcs);
				}	//
		    }		
			}//fin va=6h
		
		
		
/*FUNCIONANDO			
		if(va.equals("7")){
			//System.out.println(enca+"-"+venc+"-"+lot+"prueba");
			int numFact=0;
			rs = mm.obtenerNumFact(venc);
			try {
				while(rs.next()){
					numFact++;
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			if(numFact>0){
				rs = mm.movSinFacturar(venc);
				int numMov=0;
				try {
					while(rs.next()){
						numMov++;
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				
				if(numMov==1){
					out.print("uno");
				}else{
					if(numMov>1){
						mm.AnularMov(enca);
						out.print("Movimiento Anulado Exitosamente!!!");
					}
				}
			}else{
				mm.AnularMov(enca);
				out.print("Movimiento Anulado Exitosamente!!!");
			}
				
		}//fin va=7



FUNCIONANDO */




if(va.equals("7")){
			//System.out.println(enca+"-"+venc+"-"+lot+"prueba");
			int numFact=0;
			rs = mm.obtenerNumFact(venc);
			try {
				while(rs.next()){
					numFact++;
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			if(numFact>0){
				rs = mm.movSinFacturar(venc,lot);
				int numMov=0;
				try {
					while(rs.next()){
						numMov++;
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				
				if(numMov==1){
					out.print("uno");
				}else{
					if(numMov>1){
						mm.AnularMov(enca);
						out.print("Movimiento Anulado Exitosamente!!!");
					}
				}
			}else{
				mm.AnularMov(enca);
				out.print("Movimiento Anulado Exitosamente!!!");
			}
				
		}//fin va=7


		
		if(va.equals("7a")){
			//System.out.println(enca+"por");
			mm.AnularMov(enca);
			mm.ActualizarAdmision(venc);
			out.print("Movimiento Anulado Exitosamente!!!");
			
			
		}//fin va=7

		
	if(va.equals("8")){
			
			
			out.print("<table width='100%' class='style6'><tr><td colspan='5'><div align='center' class='style11' id='cabecera2'>Facturaci�n en Urgencias </div></td></tr>");
			
			out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' >" +
					"<td width='35%'><i><div align='center'>Paciente</div></i></td>" +
					"<td width='25%'><i><div align='center'>Empresa</div></i></td>" +
					"<td width='10%'><i><div align='center'>Admisi�n</div></i></td>" +
					"<td width='10%'><i><div align='center'>Fecha Ingreso</div></i></td>" +
					"<td width='10%'><i><div align='center'>Fecha Egreso</div></i></td>" +
					"<td width='10%'><i><div align='center'>Num Autorizacion</div></i></td>" +
					"</tr>");  //
			
			out.print("<tr><td><input type=text id='desc0' size='90' onkeyup='autocompletaPacientes(0,0)' >"); 
			out.print("<input name='desch0' type='hidden' id='desch0'/></td>");
			out.print("<td><input type=text id='eps0' size='45' readonly='readonly' >"); 
			out.print("<input name='epsh0' type='hidden' id='epsh0'/></td>");
			out.print("<td><input type=text id='adm0' size='10' readonly='readonly' ></td>");
			out.print("<td><input type=text id='FechaIngreso' size='10' readonly='readonly' ></td>");
			out.print("<td><input type=text id='FechaEgreso' size='10' readonly='readonly' ></td>");
			out.print("<td><input type=text id='NumAutoriza' size='10' readonly='readonly' ></td>");
			out.print("</tr>"); 
			
			out.print("<tr><td><div id='sugerencias0' ></div></td></tr></table>");
			out.print("</table>");
			out.print("<tr><td><div id='resul2' ></div></td></tr></table>");
			out.print("<tr><td><div id='resul' ></div></td></tr></table>");
			out.print("<tr><td><div id='resulcrea' ></div></td></tr></table>");
		
		}
		
/*	
	if(va.equals("8h")){
		
		
		out.print("<table width='100%' class='style6'><tr><td colspan='5'><div align='center' class='style11' id='cabecera2'>Facturaci�n en Hospitalizaci�n </div></td></tr>");
		
		out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='60%'><i><div align='center'>Paciente</div></i></td><td width='30%'><i><div align='center'>Empresa</div></i></td><td width='10%'><i><div align='center'>Admisi�n</div></i></td></tr>");  //
		
		out.print("<tr><td><input type=text id='desc0' size='90' onkeyup='autocompletaPacientes(0,0)' >"); 
		out.print("<input name='desch0' type='hidden' id='desch0'/></td>");
		out.print("<td><input type=text id='eps0' size='45' readonly='readonly' >"); 
		out.print("<input name='epsh0' type='hidden' id='epsh0'/></td>");
		out.print("<td><input type=text id='adm0' size='10' readonly='readonly' ></td></tr>"); 
		
		out.print("<tr><td><div id='sugerencias0' ></div></td></tr></table>");
		out.print("</table>");
		//out.print("<table><tr></td></tr>");
		//out.print("<tr><td></td></tr></table>");
		out.print("<div id='resultodos' ></div>");
		out.print("<div id='BM' ></div>");
		//out.print("<tr><td><div id='resul2' ></div></td></tr></table>");
		out.print("<table width='100%' id='conResul'><tr><td><div id='resul' ></div></td></tr></table>");
		//out.print("<tr><td><div id='resulcrea' ></div></td></tr></table>");
	
	}
	
	
	if(va.equals("8hA")){
		
		
		out.print("<table width='100%' class='style6'><tr><td colspan='5'><div align='center' class='style11' id='cabecera2'>Facturaci�n Ambulatoria </div></td></tr>");
		
		out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='60%'><i><div align='center'>Paciente</div></i></td><td width='30%'><i><div align='center'>Empresa</div></i></td><td width='10%'><i><div align='center'>Admisi�n</div></i></td></tr>");  //
		
		out.print("<tr><td><input type=text id='desc0' size='90' onkeyup='autocompletaPacientes(0,0)' >"); 
		out.print("<input name='desch0' type='hidden' id='desch0'/></td>");
		out.print("<td><input type=text id='eps0' size='45' readonly='readonly' >"); 
		out.print("<input name='epsh0' type='hidden' id='epsh0'/></td>");
		out.print("<td><input type=text id='adm0' size='10' readonly='readonly' ></td></tr>"); 
		
		out.print("<tr><td><div id='sugerencias0' ></div></td></tr></table>");
		out.print("</table>");
		//out.print("<table><tr></td></tr>");
		//out.print("<tr><td></td></tr></table>");
		out.print("<div id='resultodos' ></div>");
		out.print("<div id='BM' ></div>");
		//out.print("<tr><td><div id='resul2' ></div></td></tr></table>");
		out.print("<table width='100%' id='conResul'><tr><td><div id='resul' ></div></td></tr></table>");
		//out.print("<tr><td><div id='resulcrea' ></div></td></tr></table>");
	
	}
	*/
	
	
if(va.equals("8h")){
		
		
		out.print("<table width='100%' class='style6'><tr><td colspan='5'><div align='center' class='style11' id='cabecera2'>Facturaci�n en Hospitalizaci�n </div></td></tr>");
		
		out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' >" +
				"<td width='35%'><i><div align='center'>Paciente</div></i></td>" +
				"<td width='25%'><i><div align='center'>Empresa</div></i></td>" +
				"<td width='10%'><i><div align='center'>Admisi�n</div></i></td>" +
				"<td width='10%'><i><div align='center'>Fecha Ingreso</div></i></td>" +
				"<td width='10%'><i><div align='center'>Fecha Egreso</div></i></td>" +
				"<td width='10%'><i><div align='center'>Num Autorizacion</div></i></td>" +
				"</tr>");  //
		
		out.print("<tr><td><input type=text id='desc0' size='90' onkeyup='autocompletaPacientes(0,0,0)' >"); 
		out.print("<input name='desch0' type='hidden' id='desch0'/></td>");
		out.print("<td><input type=text id='eps0' size='45' readonly='readonly' >"); 
		out.print("<input name='epsh0' type='hidden' id='epsh0'/></td>");
		out.print("<td><input type=text id='adm0' size='10' readonly='readonly' ></td>");
		out.print("<td><input type=text id='FechaIngreso' size='10' readonly='readonly' ></td>");
		out.print("<td><input type=text id='FechaEgreso' size='10' readonly='readonly' ></td>");
		out.print("<td><input type=text id='NumAutoriza' size='10' readonly='readonly' ></td>");
		out.print("</tr>"); 
		
		out.print("<tr><td><div id='sugerencias0' ></div></td></tr></table>");
		out.print("</table>");
		//out.print("<table><tr></td></tr>");
		//out.print("<tr><td></td></tr></table>");
		out.print("<div id='resultodos' ></div>");
		out.print("<div id='BM' ></div>");
		//out.print("<tr><td><div id='resul2' ></div></td></tr></table>");
		out.print("<table width='100%' id='conResul'><tr><td><div id='resul' ></div></td></tr></table>");
		//out.print("<tr><td><div id='resulcrea' ></div></td></tr></table>");
	
	}
	
	
	if(va.equals("8hA")){
		
		
		out.print("<table width='100%' class='style6'><tr><td colspan='5'><div align='center' class='style11' id='cabecera2'>Facturaci�n Ambulatoria </div></td></tr>");
		
		out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' >" +
				"<td width='60%'><i><div align='center'>Paciente</div></i></td>" +
				"<td width='30%'><i><div align='center'>Empresa</div></i></td>");
		out.print("<td width='10%'><i><div align='center'>Admisi�n</div></i></td>");
		out.print("<td width='10%'><i><div align='center'>Fecha Ingreso</div></i></td>");
		out.print("<td width='10%'><i><div align='center'>Fecha Egreso</div></i></td>");
		out.print("<td width='10%'><i><div align='center'>Num Autorizacion</div></i></td>");
		out.print("</tr>");  //
		
		out.print("<tr><td><input type=text id='desc0' size='90' onkeyup='autocompletaPacientes(0,0,1)' >"); 
		out.print("<input name='desch0' type='hidden' id='desch0'/></td>");
		out.print("<td><input type=text id='eps0' size='45' readonly='readonly' >"); 
		out.print("<input name='epsh0' type='hidden' id='epsh0'/></td>");
		out.print("<td><input type=text id='adm0' size='10' readonly='readonly' ></td>");
		out.print("<td><input type=text id='FechaIngreso' size='10' readonly='readonly' ></td>");
		out.print("<td><input type=text id='FechaEgreso' size='10' readonly='readonly' ></td>");
		out.print("<td><input type=text id='NumAutoriza' size='10' readonly='readonly' ></td>");
		out.print("</tr>"); 
		
		out.print("<tr><td><div id='sugerencias0' ></div></td></tr></table>");
		out.print("</table>");
		//out.print("<table><tr></td></tr>");
		//out.print("<tr><td></td></tr></table>");
		out.print("<div id='resultodos' ></div>");
		out.print("<div id='BM' ></div>");
		//out.print("<tr><td><div id='resul2' ></div></td></tr></table>");
		out.print("<table width='100%' id='conResul'><tr><td><div id='resul' ></div></td></tr></table>");
		//out.print("<tr><td><div id='resulcrea' ></div></td></tr></table>");
	
	}
	
	if(va.equals("8ce")){
		
		
		out.print("<table width='100%' class='style6'><tr><td colspan='5'><div align='center' class='style11' id='cabecera2'>Facturaci�n en Consulta Externa </div></td></tr>");
		
		out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='60%'><i><div align='center'>Paciente</div></i></td><td width='30%'><i><div align='center'>Empresa</div></i></td><td width='10%'><i><div align='center'>Admisi�n</div></i></td></tr>");  //
		
		out.print("<tr><td><input type=text id='desc0' size='90' onkeyup='autocompletaPacientes(0,0)' >"); 
		out.print("<input name='desch0' type='hidden' id='desch0'/></td>");
		out.print("<td><input type=text id='eps0' size='45' readonly='readonly' >"); 
		out.print("<input name='epsh0' type='hidden' id='epsh0'/></td>");
		out.print("<td><input type=text id='adm0' size='10' readonly='readonly' ></td></tr>"); 
		
		out.print("<tr><td><div id='sugerencias0' ></div></td></tr></table>");
		out.print("</table>");
		out.print("<tr><td><div id='resul2' ></div></td></tr></table>");
		out.print("<tr><td><div id='resul' ></div></td></tr></table>");
		out.print("<tr><td><div id='resulcrea' ></div></td></tr></table>");
	
	}
	
/*
FUNCIONANDO 
	
	if(va.equals("9")){
		
		out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='100%' align='center'>Movimientos Cargados</td></tr></table>");  //
		out.print("<table width='100%' border='0' class='style6' ><tr ><td width='14.28%'><i><div align='center'>Todos: <input onclick='autoChecked(this,&quot;TODOS&quot;,&quot;"+venc+"&quot;,&quot;"+enca+"&quot;,&quot;"+lot+"&quot;)' type='checkbox' name='checkbox'  id='chkAll' value='ffff' ></div></i></td>" +
				"<td width='14.28%'><i><div align='center'></div></i></td>" +
				"<td width='16%'><i><div align='center'></div></i></td>" +
				"<td width='14.28%'><i><div align='center'></div></i></td>" +
				"<td width='14.28%'><i><div align='center'></div></i></td>" +
				"<td width='14.28%'><i><div align='center'></div></i></td>" +
				"<td width='14.28%'><i><div align='center'></div></i></td>" +
				"</tr></table>");
		out.print("<div id='titulo'><table id='movimientos' width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3'><td width='5%'><i>Codigo</div></td><td width='44%'><i>Descripci�n</i></td><td width='6%'><i>Fecha</i></td><td width='5%'><i>Cantidad</i></td></i></td><td width='5%'><i>Valor</i></td><td width='6%'><i>Seleccione</i></td></tr></table></div>");
		out.print("<div id='resulM' >");
		out.print("<table id='movimientos' width='100%' border='1' class='style6' ><tr ><td width='5%'><i><div align='center'></div></i></td><td width='44%'><i><div align='center'></div></i></td><td width='6%'><i><div align='center'></div></i></td><td width='5%'><i><div align='center'></div></i></td></i></td><td width='5%'><i><div align='center'></div></i></td><td width='6%'><i><div align='center'></div></i></td></tr>");
		
				rs2=mm.movSinFacturar(venc);
				//out.print("<input name='enca' type='hidden' id='enca' value='"+rs1.getString(1)+"' />");
				int lineas=0;
				try {
					
					while(rs2.next()){
						
					
							out.print("<tr><td>"+rs2.getString(4)+"</td>");
							out.print("<td>"+rs2.getString(5)+"</td>");
							out.print("<td>"+rs2.getString(8)+"</td>");	
							out.print("<td>"+rs2.getString(9)+"</td>");
							out.print("<td>"+rs2.getString(10)+"</td>");
							out.print("	<td><input type='checkbox' name='checkbox' onclick='desmarcarEnca(&quot;"+venc+"&quot;,&quot;"+enca+"&quot;,&quot;"+lot+"&quot;)' id='check"+lineas+"' value='"+rs2.getString(4)+"|"+rs2.getString(12)+"|"+rs2.getString(13)+"' ></td></tr>");
							lineas++;
			
						
					}
					rs2.getStatement().getConnection().close();
					} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
					}
				////////////////////
			
					/*	out.print("<table width='100%'  class='style6' border='0'>");
		out.print("<tr><td  width='50%'><label><input name='radiobutton' type='radio' value='radiobutton' id='10' onclick='Radios("+venc+","+lot+","+encab+")' />La Entidad</label></td>");
		out.print("<td width='40%'><label><input name='radiobutton' type='radio' value='radiobutton' id='11' onclick='Radios("+venc+","+lot+","+encab+")' />El Usuario</label></td>");
		out.print("<td width='10%'><label><input name='radiobutton' type='radio' value='radiobutton' id='12' onclick='Radios("+venc+","+lot+","+encab+")' />Compartido</label></td></tr>");
		//out.print("<tr><td colspan='5'><div id='Opcion'></div></td></tr>");*/
	/*	
		out.print("</table><input type='hidden' value='"+lineas+"' id='numLineas'/>");
		out.print("</div>");
		rs = mm.encaSinFacturar(venc);
	
				out.print("<div id='encabezados' class='encabezados'>");
				out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='100%' align='center'>Facturas por generar</td></tr></table>");  
				int numEnca=0;
				try {
					rs.next();	
					numEnca++;
					while(rs.next()){
						out.print("<input type='radio' id='rad"+numEnca+"' name='enca' value='"+rs.getString(1)+"' onclick='moverMovA(this,&quot;"+venc+"&quot;,&quot;"+lot+"&quot;)'/><a href='#' onclick='verFact(&quot;"+rs.getString(1)+"&quot;,&quot;"+venc+"&quot;,&quot;"+lot+"&quot;,0)'>Factura "+numEnca+"</a>");
						//System.out.print("<input type='radio' id='rad"+numEnca+"' name='enca' value='"+rs.getString(1)+"' onclick='moverMovA(this)'/><a href='#' onclick='verFact("+rs.getString(1)+")'>Factura 002</a>");

						numEnca++;
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
				}
				out.print("<input type='hidden' value='"+numEnca+"' id='numEnca'/>");
				out.print("</div>");
			
		
		rs = mm.encaFacturados(venc);
		
				out.print("<div id='encaFact' class='encabezados'>");
				out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='100%' align='center'>Facturas generadas</td></tr></table>");  
				
				try {
					while(rs.next()){
						out.print("<a href='#' onclick='CFacturas(&quot;"+rs.getString(2)+"&quot;)'>Factura "+rs.getString(2)+"</a>");
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
				}
				out.print("<input type='hidden' value='"+numEnca+"' id='numEnca'/>");
				out.print("</div>");
		
		
		
	
	}//fin va=2

	FUNCIONANDO */




if(va.equals("9")){
		
	System.out.println("entrando a 9");
		out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='100%' align='center'>Movimientos Cargados</td></tr></table>");  //
		out.print("<table width='100%' border='0' class='style6' ><tr ><td width='14.28%'><i><div align='center'>Todos: <input onclick='autoChecked(this,&quot;TODOS&quot;,&quot;"+venc+"&quot;,&quot;"+enca+"&quot;,&quot;"+lot+"&quot;)' type='checkbox' name='checkbox'  id='chkAll' value='ffff' ></div></i></td>" +
				"<td width='14.28%'><i><div align='center'></div></i></td>" +
				"<td width='16%'><i><div align='center'></div></i></td>" +
				"<td width='14.28%'><i><div align='center'></div></i></td>" +
				"<td width='14.28%'><i><div align='center'></div></i></td>" +
				"<td width='14.28%'><i><div align='center'></div></i></td>" +
				"<td width='14.28%'><i><div align='center'></div></i></td>" +
				"</tr></table>");
		out.print("<div id='titulo'><table id='movimientos' width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3'><td width='5%'><i>Codigo</div></td><td width='44%'><i>Descripci�n</i></td><td width='6%'><i>Fecha</i></td><td width='5%'><i>Cantidad</i></td></i></td><td width='5%'><i>Valor</i></td><td width='6%'><i>Seleccione</i></td></tr></table></div>");
		out.print("<div id='resulM' >");
		out.print("<table id='movimientos' width='100%' border='1' class='style6' ><tr ><td width='5%'><i><div align='center'></div></i></td><td width='44%'><i><div align='center'></div></i></td><td width='6%'><i><div align='center'></div></i></td><td width='5%'><i><div align='center'></div></i></td></i></td><td width='5%'><i><div align='center'></div></i></td><td width='6%'><i><div align='center'></div></i></td></tr>");
		
				rs2=mm.movSinFacturar(venc,lot);
				//out.print("<input name='enca' type='hidden' id='enca' value='"+rs1.getString(1)+"' />");
				int lineas=0;
				try {
					
					while(rs2.next()){
						
					
							out.print("<tr><td>"+rs2.getString(4)+"</td>");
							out.print("<td>"+rs2.getString(5)+"</td>");
							out.print("<td>"+rs2.getString(8)+"</td>");	
							out.print("<td>"+rs2.getString(9)+"</td>");
							out.print("<td>"+rs2.getString(10)+"</td>");
							out.print("	<td><input type='checkbox' name='checkbox' onclick='desmarcarEnca(&quot;"+venc+"&quot;,&quot;"+enca+"&quot;,&quot;"+lot+"&quot;)' id='check"+lineas+"' value='"+rs2.getString(4)+"|"+rs2.getString(12)+"|"+rs2.getString(13)+"' ></td></tr>");
							lineas++;
			
						
					}
					rs2.getStatement().getConnection().close();
					} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
					}
				////////////////////
			
					/*	out.print("<table width='100%'  class='style6' border='0'>");
		out.print("<tr><td  width='50%'><label><input name='radiobutton' type='radio' value='radiobutton' id='10' onclick='Radios("+venc+","+lot+","+encab+")' />La Entidad</label></td>");
		out.print("<td width='40%'><label><input name='radiobutton' type='radio' value='radiobutton' id='11' onclick='Radios("+venc+","+lot+","+encab+")' />El Usuario</label></td>");
		out.print("<td width='10%'><label><input name='radiobutton' type='radio' value='radiobutton' id='12' onclick='Radios("+venc+","+lot+","+encab+")' />Compartido</label></td></tr>");
		//out.print("<tr><td colspan='5'><div id='Opcion'></div></td></tr>");*/
		
		out.print("</table><input type='hidden' value='"+lineas+"' id='numLineas'/>");
		out.print("</div>");
		rs = mm.encaSinFacturar(venc,lot); 
		//System.out.println("xxxxxxxxxxxxxxxxxxxxx1: "+venc+" - "+lot);
		
				out.print("<div id='encabezados' class='encabezados'>");
				out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='100%' align='center'>Facturas por generar</td></tr></table>");  
				int numEnca=1;
				try {
					//rs.next();	
					//numEnca++;
					while(rs.next()){
						out.print("<input type='radio' id='rad"+numEnca+"' name='enca' value='"+rs.getString(1)+"' onclick='moverMovA(this,&quot;"+venc+"&quot;,&quot;"+lot+"&quot;)'/><a href='#' onclick='verFact(&quot;"+rs.getString(1)+"&quot;,&quot;"+venc+"&quot;,&quot;"+lot+"&quot;,0)'>Factura "+numEnca+"</a>");
						//System.out.print("<input type='radio' id='rad"+numEnca+"' name='enca' value='"+rs.getString(1)+"' onclick='moverMovA(this)'/><a href='#' onclick='verFact("+rs.getString(1)+")'>Factura 002</a>");

						numEnca++;
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
				}
				out.print("<input type='hidden' value='"+numEnca+"' id='numEnca'/>");
				out.print("</div>");
			
		
		rs = mm.encaFacturados(venc,lot);
		
				out.print("<div id='encaFact' class='encabezados'>");
				out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='100%' align='center'>Facturas generadas</td></tr></table>");  
				
				try {
					while(rs.next()){
						out.print("<a href='#' onclick='CFacturas(&quot;"+rs.getString(2)+"&quot;)'>Factura "+rs.getString(2)+"</a>");
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
				}
				out.print("<input type='hidden' value='"+numEnca+"' id='numEnca'/>");
				out.print("</div>");
		
		
		
	
	}//fin va=2


	
	if(va.equals("9.1")){
		String amb = req.getParameter("amb");
		out.print("<table width='100%'  class='style6' border='0'>");
		out.print("<tr><td  width='50%'><label><input name='radiobutton' type='radio' value='radiobutton' id='10' onclick='Radios("+venc+","+lot+","+encab+",&quot;resul&quot;,0,1,&quot;"+amb+"&quot;)' />La Entidad</label></td>");
		out.print("<td width='40%'><label><input name='radiobutton' type='radio' value='radiobutton' id='11' onclick='Radios("+venc+","+lot+","+encab+",&quot;resul&quot;,0,1,&quot;"+amb+"&quot;)' />El Usuario</label></td>");
		out.print("<td width='10%'><label><input name='radiobutton' type='radio' value='radiobutton' id='12' onclick='Radios("+venc+","+lot+","+encab+",&quot;resul&quot;,0,1,&quot;"+amb+"&quot;)' />Compartido</label></td></tr>");
		//out.print("<tr><td colspan='5'><div id='Opcion'></div></td></tr>");
		out.print("</table>");
	}//fin va=2
	
	
	
/*	
if(va.equals("10")){
	String amb = req.getParameter("amb");
	String tipoFact = req.getParameter("tipoFact");
	String opcion = req.getParameter("opcion");
	//System.out.println(venc+" "+lot+" "+enca+" jkgasdihbasdkljbalskj");
	rs3=mm.ObtenerCodigoAdmision(enca);
	String CodAdm="";
	String EncaNue="";
	String TipoFac="";
	try {
		if(rs3.next()){
			System.out.println("		ENTRA EN LA VALIDACION Y BUSCA LA ADMISION");
			CodAdm=rs3.getString(1);
			TipoFac=rs3.getString(2);
			rs4=mm.obtenerInfoEncaActivo(CodAdm);
			if(rs4.next()){
				EncaNue=rs4.getString(1);
				System.out.println("ENCUENTRA EN ENCABEZADO ACTIVOS= "+EncaNue+" TIPOFACT= "+TipoFac);
			}
			rs4.getStatement().getConnection().close();					
		}
		rs3.getStatement().getConnection().close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

		int toty=0;
		String tots="";
		String cants="";
		if(TipoFac.equals("1")){/*por urgencias*/
	  
	/*F rs1=mm.Valor(venc,lot,EncaNue); }else{rs1=mm.Valor(venc,lot,enca);}
		
		
		try {
			while(rs1.next()){
			tots=rs1.getString(1);	
			cants=rs1.getString(2);	
			toty=toty+(Integer.parseInt(tots)*Integer.parseInt(cants));
			}
			rs1.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		
		String tot=df.format(toty);
		String totys=toty+"";
		out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='20%'><i><div align='center'>Forma de Pago</div></i></td><td width='10%'><i><div align='center'>Valor</div></i></td><td width='5%'><i><div align='center'></div></i></td><td width='20%'><i><div align='center'>Pagos a la Cuenta</div></i></td><td width='10%'><i><div align='center'>Valor</div></i></td></tr>");  //
		//out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='60%'><i><div align='center'>Paciente</div></i></td><td width='30%'><i><div align='center'>Empresa</div></i></td><td width='10%'><i><div align='center'>Admisi�n</div></i></td></tr>");  //
		out.print("<tr><td><input type=text id='desc0' style='width:100%;' value='Credito Empresa'  ></td>"); 
		out.print("<td><input type=text id='Empresa' style='width:100%;' value='"+tot+"'  readonly='readonly' ></td>"); 
		out.print("<td><input type=text id='desc0' style='width:100%;' style='background-color:#D3D3D3;' readonly='readonly'  ></td>");
		out.print("<td><input type=text id='desc0' style='width:100%;' value='Anticipos'  ></td>");
		out.print("<td><input type=text id='Anticipos' style='width:100%;' value='0' readonly='readonly' ></td>");
		out.print("<tr><td><input type=text id='desc0' style='width:100%;' value='Efectivo' ></td>"); 
		out.print("<td><input type=text id='Efectivo' style='width:100%;' value='0' readonly='readonly' ></td>");
		out.print("<td><input type=text id='desc0' style='width:100%;' style='background-color:#D3D3D3;' readonly='readonly' ></td>");
		out.print("<td><input type=text id='desc0' style='width:100%;' value='Descuento' ></td>");
		out.print("<td><input type=text id='Moderacion' style='width:100%;' value='0' readonly='readonly'></td>");
		out.print("<tr><td><input type=text id='desc0' style='width:100%;' value='Total' ></td>");
		out.print("<td><input type=text id='Total' style='width:100%;' value='"+tot+"' readonly='readonly'></td>");
		out.print("<td><input type=text id='desc0' style='width:100%;' style='background-color:#D3D3D3;' readonly='readonly' ></td>");
		out.print("<td><input type=text id='desc0' style='width:100%;' value='Copago' ></td>");
		out.print("<td><input type=text id='Copago' style='width:100%;' value='0' readonly='readonly'></td>");
		out.print("</table><table width='100%' border='0' class='style6' ><tr></tr><tr></tr><tr></tr><tr></tr><tr><div align='center'>Total Movimientos: "+tot+"</div></tr><tr></tr><tr><td colspan='4'  width='10%'><div align='center'><input type='button' name='btnPre' id='btnPre' value='Generar Prefactura' onClick='Pref("+venc+","+EncaNue+")'></div></td><td colspan='4'  width='10%'><div align='center'>");
		//if(estAdm.equals("1")){
		/********************************************************************************/
	/*f	rs3=mm.ObtenerCodigoAdmision(enca);
		//String CodAdm="";
		//String EncaNue="";
		//String TipoFac="";
		try {
			if(rs3.next()){
				System.out.println("		ENTRA EN LA VALIDACION Y BUSCA LA ADMISION");
				CodAdm=rs3.getString(1);
				TipoFac=rs3.getString(2);
				rs4=mm.obtenerInfoEncaActivo(CodAdm);
				if(rs4.next()){
					EncaNue=rs4.getString(1);
					System.out.println("ENCUENTRA EN ENCABEZADO ACTIVOS= "+EncaNue+" TIPOFACT= "+TipoFac);
				}
				rs4.getStatement().getConnection().close();					
			}
			rs3.getStatement().getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(TipoFac.equals("1")){
			///si es urgencia
			if(opcion.equals("0")){
				out.print("<input type='button' name='btnFact' id='btnFact' value='Generar Factura' onClick='Fact("+venc+","+lot+","+EncaNue+","+tipoFact+",&quot;"+amb+"&quot;)'></div></td></tr></table>");
			}else{
				if(opcion.equals("1")){
					out.print("<input type='button' name='btnFact' id='btnFact' value='Generar Factura' onClick='Fact("+venc+","+lot+","+EncaNue+","+tipoFact+")'></div></td>");
					out.print("<td colspan='4'  width='10%'><div align='center'><input type='button' name='btnCerrar' id='btnCerrar' value='Cerrar' onClick='cerrarVentDetEnca("+venc+","+EncaNue+","+lot+")'></div></td></tr></table>");
				}
			}
			//}else{
				//out.print("<input type='button' name='btnFact' id='btnFact' disabled='disabled' value='Generar Factura' onClick='Fact("+venc+","+lot+","+encab+")'></div></td></tr></table>");
			//}
			//out.print("</table><table width='100%' border='0' class='style6' ><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr><td colspan='4'  width='10%'><div align='center'><input type='button' name='btnPre' id='btnPre' value='ctura' onClick='Pref("+venc+")'></div></td><td colspan='4'  width='10%'><div align='center'><input type='button' name='btnFact' id='btnFact' value='Generar Factura' onClick='Fact("+venc+")'></div></td></tr></table>");
			//System.out.println(venc+","+lot+","+totys+","+subtl+","+enca);
			mm.Valor(venc, lot, totys, subtl,EncaNue);

			
		}else{
			/// si es hospitalizacion o ambulatorio
			if(opcion.equals("0")){
				out.print("<input type='button' name='btnFact' id='btnFact' value='Generar Factura' onClick='Fact("+venc+","+lot+","+enca+","+tipoFact+",&quot;"+amb+"&quot;)'></div></td></tr></table>");
			}else{
				if(opcion.equals("1")){
					out.print("<input type='button' name='btnFact' id='btnFact' value='Generar Factura' onClick='Fact("+venc+","+lot+","+enca+","+tipoFact+")'></div></td>");
					out.print("<td colspan='4'  width='10%'><div align='center'><input type='button' name='btnCerrar' id='btnCerrar' value='Cerrar' onClick='cerrarVentDetEnca("+venc+","+enca+","+lot+")'></div></td></tr></table>");
				}
			}
			//}else{
				//out.print("<input type='button' name='btnFact' id='btnFact' disabled='disabled' value='Generar Factura' onClick='Fact("+venc+","+lot+","+encab+")'></div></td></tr></table>");
			//}
			//out.print("</table><table width='100%' border='0' class='style6' ><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr><td colspan='4'  width='10%'><div align='center'><input type='button' name='btnPre' id='btnPre' value='Generar Prefactura' onClick='Pref("+venc+")'></div></td><td colspan='4'  width='10%'><div align='center'><input type='button' name='btnFact' id='btnFact' value='Generar Factura' onClick='Fact("+venc+")'></div></td></tr></table>");
			//System.out.println(venc+","+lot+","+totys+","+subtl+","+enca);
			mm.Valor(venc, lot, totys, subtl,enca);

		}		
		/**********************************************************************************/	
		
/*	}//fin va=2
	
*/
	
	
	if(va.equals("10")){
		String amb = req.getParameter("amb");
		String tipoFact = req.getParameter("tipoFact");
		String opcion = req.getParameter("opcion");
		System.out.println("jkgasdihbasdkljbalskj"+venc+" "+lot+" "+enca+" jkgasdihbasdkljbalskj");
			int toty=0;
			String tots="";
			String cants="";
			rs1=mm.Valor(venc,lot,enca);
			try {
				while(rs1.next()){
				tots=rs1.getString(1);	
				cants=rs1.getString(2);	
				toty=toty+(Integer.parseInt(tots)*Integer.parseInt(cants));
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			String tot=df.format(toty);
			String totys=toty+"";
			out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='20%'><i><div align='center'>Forma de Pago</div></i></td><td width='10%'><i><div align='center'>Valor</div></i></td><td width='5%'><i><div align='center'></div></i></td><td width='20%'><i><div align='center'>Pagos a la Cuenta</div></i></td><td width='10%'><i><div align='center'>Valor</div></i></td></tr>");  //
			//out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='60%'><i><div align='center'>Paciente</div></i></td><td width='30%'><i><div align='center'>Empresa</div></i></td><td width='10%'><i><div align='center'>Admisi�n</div></i></td></tr>");  //
			out.print("<tr><td><input type=text id='desc0' style='width:100%;' value='Credito Empresa'  ></td>"); 
			out.print("<td><input type=text id='Empresa' style='width:100%;' value='"+tot+"'  readonly='readonly' ></td>"); 
			out.print("<td><input type=text id='desc0' style='width:100%;' style='background-color:#D3D3D3;' readonly='readonly'  ></td>");
			out.print("<td><input type=text id='desc0' style='width:100%;' value='Anticipos'  ></td>");
			out.print("<td><input type=text id='Anticipos' style='width:100%;' value='0' readonly='readonly' ></td>");
			out.print("<tr><td><input type=text id='desc0' style='width:100%;' value='Efectivo' ></td>"); 
			out.print("<td><input type=text id='Efectivo' style='width:100%;' value='0' readonly='readonly' ></td>");
			out.print("<td><input type=text id='desc0' style='width:100%;' style='background-color:#D3D3D3;' readonly='readonly' ></td>");
			out.print("<td><input type=text id='desc0' style='width:100%;' value='Descuento' ></td>");
			out.print("<td><input type=text id='Moderacion' style='width:100%;' value='0' readonly='readonly'></td>");
			out.print("<tr><td><input type=text id='desc0' style='width:100%;' value='Total' ></td>");
			out.print("<td><input type=text id='Total' style='width:100%;' value='"+tot+"' readonly='readonly'></td>");
			out.print("<td><input type=text id='desc0' style='width:100%;' style='background-color:#D3D3D3;' readonly='readonly' ></td>");
			out.print("<td><input type=text id='desc0' style='width:100%;' value='Copago' ></td>");
			out.print("<td><input type=text id='Copago' style='width:100%;' value='0' readonly='readonly'></td>");
			out.print("</table><table width='100%' border='0' class='style6' ><tr></tr><tr></tr><tr></tr><tr></tr><tr><div align='center'>Total Movimientos: "+tot+"</div></tr><tr></tr><tr><td colspan='4'  width='10%'><div align='center'><input type='button' name='btnPre' id='btnPre' value='Generar Prefactura' onClick='Pref("+venc+","+enca+")'></div></td><td colspan='4'  width='10%'><div align='center'>");
			//if(estAdm.equals("1")){
			if(opcion.equals("0")){
				out.print("<input type='button' name='btnFact' id='btnFact' value='Generar Factura' onClick='Fact("+venc+","+lot+","+enca+","+tipoFact+",&quot;"+amb+"&quot;)'></div></td></tr></table>");
			}else{
				if(opcion.equals("1")){
					out.print("<input type='button' name='btnFact' id='btnFact' value='Generar Factura' onClick='Fact("+venc+","+lot+","+enca+","+tipoFact+")'></div></td>");
					out.print("<td colspan='4'  width='10%'><div align='center'><input type='button' name='btnCerrar' id='btnCerrar' value='Cerrar' onClick='cerrarVentDetEnca("+venc+","+enca+","+lot+")'></div></td></tr></table>");
				}
			}
			//}else{
				//out.print("<input type='button' name='btnFact' id='btnFact' disabled='disabled' value='Generar Factura' onClick='Fact("+venc+","+lot+","+encab+")'></div></td></tr></table>");
			//}
			//out.print("</table><table width='100%' border='0' class='style6' ><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr><td colspan='4'  width='10%'><div align='center'><input type='button' name='btnPre' id='btnPre' value='Generar Prefactura' onClick='Pref("+venc+")'></div></td><td colspan='4'  width='10%'><div align='center'><input type='button' name='btnFact' id='btnFact' value='Generar Factura' onClick='Fact("+venc+")'></div></td></tr></table>");
			//System.out.println(venc+","+lot+","+totys+","+subtl+","+enca);
			mm.Valor(venc, lot, totys, subtl,enca);
			
		}//fin va=2
	
	if(va.equals("11")){
		String amb = req.getParameter("amb");
		String tipoFact = req.getParameter("tipoFact");
		//System.out.println(venc+" "+lot+" "+enca);
		String opcion = req.getParameter("opcion");
		int toty=0;
		String tots="";
		String cants="";
		/**********************************/
		rs3=mm.ObtenerCodigoAdmision(enca);
		String CodAdm="";
		String EncaNue="";
		String TipoFac="";
		try {
			if(rs3.next()){
				//System.out.println("		ENTRA EN LA VALIDACION Y BUSCA LA ADMISION");
				CodAdm=rs3.getString(1);
				TipoFac=rs3.getString(2);
				rs4=mm.obtenerInfoEncaActivo(CodAdm);
				if(rs4.next()){
					EncaNue=rs4.getString(1);
					System.out.println("ENCUENTRA EN ENCABEZADO ACTIVOS= "+EncaNue+" TIPOFACT= "+TipoFac);
				}
				rs4.getStatement().getConnection().close();					
			}
			rs3.getStatement().getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/**********************************/
		if(TipoFac.equals("1")){/*por urgencias*/ rs1=mm.Valor(venc,lot,EncaNue); }else{rs1=mm.Valor(venc,lot,enca);}
		//rs1=mm.Valor(venc,lot,enca);
		try {
			while(rs1.next()){
			tots=rs1.getString(1);	
			cants=rs1.getString(2);	
			toty=toty+(Integer.parseInt(tots)*Integer.parseInt(cants));
			}
			rs1.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		
		String tot=df.format(toty);
		String totys=toty+"";
		out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='20%'><i><div align='center'>Forma de Pago</div></i></td><td width='10%'><i><div align='center'>Valor</div></i></td><td width='5%'><i><div align='center'></div></i></td><td width='20%'><i><div align='center'>Pagos a la Cuenta</div></i></td><td width='10%'><i><div align='center'>Valor</div></i></td></tr>");  //
		//out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='60%'><i><div align='center'>Paciente</div></i></td><td width='30%'><i><div align='center'>Empresa</div></i></td><td width='10%'><i><div align='center'>Admisi�n</div></i></td></tr>");  //
		out.print("<tr><td><input type=text id='desc0' style='width:100%;' value='Credito Empresa'  ></td>"); 
		out.print("<td><input type=text id='Empresa' style='width:100%;' value='0'  readonly='readonly' ></td>"); 
		out.print("<td><input type=text id='desc0' style='width:100%;' style='background-color:#D3D3D3;' readonly='readonly'  ></td>");
		out.print("<td><input type=text id='desc0' style='width:100%;' value='Anticipos' ></td>");
		out.print("<td><input type=text id='Anticipos' style='width:100%;' value='0' readonly='readonly' ></td>");
		out.print("<tr><td><input type=text id='desc0' style='width:100%;' value='Efectivo' ></td>"); 
		out.print("<td><input type=text id='Efectivo' style='width:100%;' value='"+tot+"' readonly='readonly'></td>");
		out.print("<td><input type=text id='desc0' style='width:100%;' style='background-color:#D3D3D3;' readonly='readonly' ></td>");
		out.print("<td><input type=text id='desc0' style='width:100%;' value='Descuento' ></td>");
		out.print("<td><input type=text id='Moderacion' style='width:100%;' value='0' readonly='readonly'></td>");
		out.print("<tr><td><input type=text id='desc0' style='width:100%;' value='Total' ></td>");
		out.print("<td><input type=text id='Total' style='width:100%;' value='"+tot+"' readonly='readonly'></td>");
		out.print("<td><input type=text id='desc0' style='width:100%;' style='background-color:#D3D3D3;' readonly='readonly' ></td>");
		out.print("<td><input type=text id='desc0' style='width:100%;' value='Copago' ></td>");
		out.print("<td><input type=text id='Copago' style='width:100%;' value='0'readonly='readonly'></td>");
		//out.print("</table><table width='100%' border='0' class='style6' ><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr><td colspan='4'  width='10%'><div align='center'><input type='button' name='btnPre' id='btnPre' value='Generar Prefactura' onClick='Pref("+venc+")'></div></td><td colspan='4'  width='10%'><div align='center'><input type='button' name='btnFact' id='btnFact' value='Generar Factura' onClick='Fact("+venc+")'></div></td></tr></table>");
		out.print("</table><table width='100%' border='0' class='style6' ><tr></tr><tr></tr><tr></tr><tr></tr><tr><div align='center'>Total Movimientos: "+tot+"</div></tr><tr></tr><tr><td colspan='4'  width='10%'><div align='center'><input type='button' name='btnPre' id='btnPre' value='Generar Prefactura' onClick='Pref("+venc+","+EncaNue+")'></div></td><td colspan='4'  width='10%'><div align='center'>");
		/********************************************************************************/
		rs3=mm.ObtenerCodigoAdmision(enca);
	//	String CodAdm="";
		//String EncaNue="";
		//String TipoFac="";
		try {
			if(rs3.next()){
				CodAdm=rs3.getString(1);
				TipoFac=rs3.getString(2);
				rs4=mm.obtenerInfoEncaActivo(CodAdm);
				if(rs4.next()){
					EncaNue=rs4.getString(1);
				}
				rs4.getStatement().getConnection().close();					
			}
			rs3.getStatement().getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(TipoFac.equals("1")){
			///si es urgencia
			if(opcion.equals("0")){
				out.print("<input type='button' name='btnFact' id='btnFact' value='Generar Factura' onClick='Fact("+venc+","+lot+","+EncaNue+","+tipoFact+",&quot;"+amb+"&quot;)'></div></td></tr></table>");
			}else{
				if(opcion.equals("1")){
					out.print("<input type='button' name='btnFact' id='btnFact' value='Generar Factura' onClick='Fact("+venc+","+lot+","+EncaNue+","+tipoFact+")'></div></td>");
					out.print("<td colspan='4'  width='10%'><div align='center'><input type='button' name='btnCerrar' id='btnCerrar' value='Cerrar' onClick='cerrarVentDetEnca("+venc+","+EncaNue+","+lot+")'></div></td></tr></table>");
				}
			}
			//}else{
				//out.print("<input type='button' name='btnFact' id='btnFact' disabled='disabled' value='Generar Factura' onClick='Fact("+venc+","+lot+","+encab+")'></div></td></tr></table>");
			//}
			//out.print("</table><table width='100%' border='0' class='style6' ><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr><td colspan='4'  width='10%'><div align='center'><input type='button' name='btnPre' id='btnPre' value='Generar Prefactura' onClick='Pref("+venc+")'></div></td><td colspan='4'  width='10%'><div align='center'><input type='button' name='btnFact' id='btnFact' value='Generar Factura' onClick='Fact("+venc+")'></div></td></tr></table>");
			//System.out.println(venc+","+lot+","+totys+","+subtl+","+enca);
			mm.Valor(venc, lot, totys, subtl,EncaNue);

			
		}else{
			/// si es hospitalizacion o ambulatorio
			if(opcion.equals("0")){
				out.print("<input type='button' name='btnFact' id='btnFact' value='Generar Factura' onClick='Fact("+venc+","+lot+","+enca+","+tipoFact+",&quot;"+amb+"&quot;)'></div></td></tr></table>");
			}else{
				if(opcion.equals("1")){
					out.print("<input type='button' name='btnFact' id='btnFact' value='Generar Factura' onClick='Fact("+venc+","+lot+","+enca+","+tipoFact+")'></div></td>");
					out.print("<td colspan='4'  width='10%'><div align='center'><input type='button' name='btnCerrar' id='btnCerrar' value='Cerrar' onClick='cerrarVentDetEnca("+venc+","+enca+","+lot+")'></div></td></tr></table>");
				}
			}
			//}else{
				//out.print("<input type='button' name='btnFact' id='btnFact' disabled='disabled' value='Generar Factura' onClick='Fact("+venc+","+lot+","+encab+")'></div></td></tr></table>");
			//}
			//out.print("</table><table width='100%' border='0' class='style6' ><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr><td colspan='4'  width='10%'><div align='center'><input type='button' name='btnPre' id='btnPre' value='Generar Prefactura' onClick='Pref("+venc+")'></div></td><td colspan='4'  width='10%'><div align='center'><input type='button' name='btnFact' id='btnFact' value='Generar Factura' onClick='Fact("+venc+")'></div></td></tr></table>");
			//System.out.println(venc+","+lot+","+totys+","+subtl+","+enca);
			mm.Valor(venc, lot, totys, subtl,enca);

		}		
		/**********************************************************************************/	

		
		/*if(opcion.equals("0")){
			out.print("<input type='button' name='btnFact' id='btnFact' value='Generar Factura' onClick='Fact("+venc+","+lot+","+enca+","+tipoFact+",&quot;"+amb+"&quot;)'></div></td></tr></table>");
		}else{
			if(opcion.equals("1")){
				out.print("<input type='button' name='btnFact' id='btnFact' value='Generar Factura' onClick='Fact("+venc+","+lot+","+enca+","+tipoFact+")'></div></td>");
				out.print("<td colspan='4'  width='10%'><div align='center'><input type='button' name='btnCerrar' id='btnCerrar' value='Cerrar' onClick='cerrarVentDetEnca("+venc+","+enca+","+lot+")'></div></td></tr></table>");
			}
		}		
		mm.Valor(venc, lot, totys, subtl,enca);*/
	}//fin va=2
	
	
	
	
	if(va.equals("12")){
		String amb = req.getParameter("amb");
		String tipoFact = req.getParameter("tipoFact");
		//System.out.println(venc+" "+lot+" "+enca);
		String opcion = req.getParameter("opcion");
		int toty=0;
		String tots="";
		String cants="";
		int antic=0;
		int mod=0;
		int cop=0;
		int valore=0;
		/**********************************/
		rs3=mm.ObtenerCodigoAdmision(enca);
		String CodAdm="";
		String EncaNue="";
		String TipoFac="";
		try {
			if(rs3.next()){
				//System.out.println("		ENTRA EN LA VALIDACION Y BUSCA LA ADMISION");
				CodAdm=rs3.getString(1);
				TipoFac=rs3.getString(2);
				rs4=mm.obtenerInfoEncaActivo(CodAdm);
				if(rs4.next()){
					EncaNue=rs4.getString(1);
					System.out.println("ENCUENTRA EN ENCABEZADO ACTIVOS= "+EncaNue+" TIPOFACT= "+TipoFac);
				}
				rs4.getStatement().getConnection().close();					
			}
			rs3.getStatement().getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/**********************************/
		if(TipoFac.equals("1")){/*por urgencias*/ rs1=mm.Valor(venc,lot,EncaNue); }else{rs1=mm.Valor(venc,lot,enca);}

		//rs1=mm.Valor(venc,lot,enca);
		try {
			while(rs1.next()){
			tots=rs1.getString(1);	
			cants=rs1.getString(2);	
			antic=Integer.parseInt(rs1.getString(3));	
			mod=Integer.parseInt(rs1.getString(4));	
			cop=Integer.parseInt(rs1.getString(5));	
			valore=Integer.parseInt(rs1.getString(6));	
			toty=toty+(Integer.parseInt(tots)*Integer.parseInt(cants));
			}
			rs1.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		
		String tot=df.format(toty);
		String antif=df.format(antic);
		String modf=df.format(mod);
		String copf=df.format(cop);
		String valoref=df.format(valore);
		if(valore==0)valoref=tot;
		String valoress=valore+"";
		out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='20%'><i><div align='center'>Forma de Pago</div></i></td><td width='10%'><i><div align='center'>Valor</div></i></td><td width='5%'><i><div align='center'></div></i></td><td width='20%'><i><div align='center'>Pagos a la Cuenta</div></i></td><td width='10%'><i><div align='center'>Valor</div></i></td></tr>");  //
		//out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='60%'><i><div align='center'>Paciente</div></i></td><td width='30%'><i><div align='center'>Empresa</div></i></td><td width='10%'><i><div align='center'>Admisi�n</div></i></td></tr>");  //
		out.print("<tr><td><input type=text id='desc0' style='width:100%;' value='Credito Empresa'  ></td>"); 
		out.print("<td><input type=text id='Compartida1' style='width:100%;' onkeyup='checknumg(1)' onblur='Empre("+toty+","+venc+","+lot+")' value='"+valoref+"' ></td>"); 
		out.print("<td><input type=text id='desc0' style='width:100%;' style='background-color:#D3D3D3;' readonly='readonly'  ></td>");
		out.print("<td><input type=text id='Anticipos' style='width:100%;' value='Anticipos' ></td>");
		out.print("<td><input type=text id='Compartida2' style='width:100%;' value='"+antif+"'  onkeyup='Efect(this,"+toty+","+venc+","+lot+","+EncaNue+","+opcion+","+tipoFact+")' ></td>");
		out.print("<tr><td><input type=text id='desc0' style='width:100%;' value='Efectivo' ></td>"); 
		out.print("<td><input type=text id='Compartida3' style='width:100%;'  onkeyup='checknumg(3)' readonly='readonly'   value='"+copf+"'></td>");
		out.print("<td><input type=text id='desc0' style='width:100%;' style='background-color:#D3D3D3;' readonly='readonly' ></td>");
		out.print("<td><input type=text id='desc0' style='width:100%;' value='Descuento' ></td>");
		out.print("<td><input type=text id='Compartida4' style='width:100%;' onkeyup='Efect(this,"+toty+","+venc+","+lot+","+EncaNue+","+opcion+","+tipoFact+")' value='"+modf+"'></td>");
		out.print("<tr><td><input type=text id='desc0' style='width:100%;' value='Subtotal' ></td>");
		out.print("<td><input type=text id='Compartida5' style='width:100%;' value='"+valoref+"' readonly='readonly'></td>");
		out.print("<td><input type=text id='desc0' style='width:100%;' style='background-color:#D3D3D3;' readonly='readonly' ></td>");
		out.print("<td><input type=text id='desc0' style='width:100%;' value='Copago' ></td>");
		out.print("<td><input type=text id='Compartida6' style='width:100%;'  value='"+copf+"'  onkeyup='Efect(this,"+toty+","+venc+","+lot+","+EncaNue+","+opcion+","+tipoFact+")'></td>");
		out.print("</table><table width='100%' border='0' class='style6' ><tr></tr><tr></tr><tr></tr><tr></tr><tr><div align='center'>Total Movimientos: "+tot+"</div></tr><tr></tr><tr><td colspan='4'  width='10%'><div align='center'><input type='button' name='btnPre' id='btnPre' value='Generar Prefactura' onClick='Pref("+venc+","+EncaNue+")'></div></td><td colspan='4'  width='10%'><div align='center'>");
		
		/********************************************************************************/
		rs3=mm.ObtenerCodigoAdmision(enca);
		//String CodAdm="";
		//String EncaNue="";
		//String TipoFac="";
		try {
			if(rs3.next()){
				CodAdm=rs3.getString(1);
				TipoFac=rs3.getString(2);
				rs4=mm.obtenerInfoEncaActivo(CodAdm);
				if(rs4.next()){
					EncaNue=rs4.getString(1);
				}
				rs4.getStatement().getConnection().close();					
			}
			rs3.getStatement().getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(TipoFac.equals("1")){
			///si es urgencia
			if(opcion.equals("0")){
				out.print("<input type='button' name='btnFact' id='btnFact' value='Generar Factura' onClick='Fact("+venc+","+lot+","+EncaNue+","+tipoFact+",&quot;"+amb+"&quot;)'></div></td></tr></table>");
			}else{
				if(opcion.equals("1")){
					out.print("<input type='button' name='btnFact' id='btnFact' value='Generar Factura' onClick='Fact("+venc+","+lot+","+EncaNue+","+tipoFact+")'></div></td>");
					out.print("<td colspan='4'  width='10%'><div align='center'><input type='button' name='btnCerrar' id='btnCerrar' value='Cerrar' onClick='cerrarVentDetEnca("+venc+","+EncaNue+","+lot+")'></div></td></tr></table>");
				}
			}
			//}else{
				//out.print("<input type='button' name='btnFact' id='btnFact' disabled='disabled' value='Generar Factura' onClick='Fact("+venc+","+lot+","+encab+")'></div></td></tr></table>");
			//}
			//out.print("</table><table width='100%' border='0' class='style6' ><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr><td colspan='4'  width='10%'><div align='center'><input type='button' name='btnPre' id='btnPre' value='Generar Prefactura' onClick='Pref("+venc+")'></div></td><td colspan='4'  width='10%'><div align='center'><input type='button' name='btnFact' id='btnFact' value='Generar Factura' onClick='Fact("+venc+")'></div></td></tr></table>");
			//System.out.println(venc+","+lot+","+totys+","+subtl+","+enca);
			mm.Valor(venc, lot, valoress, subtl,EncaNue);

			
		}else{
			/// si es hospitalizacion o ambulatorio
			if(opcion.equals("0")){
				out.print("<input type='button' name='btnFact' id='btnFact' value='Generar Factura' onClick='Fact("+venc+","+lot+","+enca+","+tipoFact+",&quot;"+amb+"&quot;)'></div></td></tr></table>");
			}else{
				if(opcion.equals("1")){
					out.print("<input type='button' name='btnFact' id='btnFact' value='Generar Factura' onClick='Fact("+venc+","+lot+","+enca+","+tipoFact+")'></div></td>");
					out.print("<td colspan='4'  width='10%'><div align='center'><input type='button' name='btnCerrar' id='btnCerrar' value='Cerrar' onClick='cerrarVentDetEnca("+venc+","+enca+","+lot+")'></div></td></tr></table>");
				}
			}
			//}else{
				//out.print("<input type='button' name='btnFact' id='btnFact' disabled='disabled' value='Generar Factura' onClick='Fact("+venc+","+lot+","+encab+")'></div></td></tr></table>");
			//}
			//out.print("</table><table width='100%' border='0' class='style6' ><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr><td colspan='4'  width='10%'><div align='center'><input type='button' name='btnPre' id='btnPre' value='Generar Prefactura' onClick='Pref("+venc+")'></div></td><td colspan='4'  width='10%'><div align='center'><input type='button' name='btnFact' id='btnFact' value='Generar Factura' onClick='Fact("+venc+")'></div></td></tr></table>");
			//System.out.println(venc+","+lot+","+totys+","+subtl+","+enca);
			mm.Valor(venc, lot, valoress, subtl,enca);

		}		
		/**********************************************************************************/	

		
		
		/*if(opcion.equals("0")){
			out.print("<input type='button' name='btnFact' id='btnFact' value='Generar Factura' onClick='Fact("+venc+","+lot+","+enca+","+tipoFact+",&quot;"+amb+"&quot;)'></div></td></tr></table>");
		}else{
			if(opcion.equals("1")){
				out.print("<input type='button' name='btnFact' id='btnFact' value='Generar Factura' onClick='Fact("+venc+","+lot+","+enca+","+tipoFact+")'></div></td>");
				out.print("<td colspan='4'  width='10%'><div align='center'><input type='button' name='btnCerrar' id='btnCerrar' value='Cerrar' onClick='cerrarVentDetEnca("+venc+","+enca+","+lot+")'></div></td></tr></table>");
			}
		}	
	mm.Valor(venc, lot, valoress, subtl,enca);*/
	}//fin va=2
	

	if(va.equals("VAF")){
		//dx ingreso ConsultarDx
		String CodAdm=req.getParameter("CodAdm");
		
		try {
			rs=mm.ConsultarDx(CodAdm);
			if(rs.next()){}else{out.print("El Paciente No Tiene Diagnostico de Ingreso.");}
			rs.getStatement().getConnection().close();
			
			rs=mm.Dxegreso(CodAdm);
			if(rs.next()){}else{out.print("El Paciente No Tiene Diagnostico de Egreso.");}
			rs.getStatement().getConnection().close();
			
			rs=mm.Destinop(CodAdm);
			if(rs.next()){}else{out.print("El Paciente No Tiene Estado de la Salida Diligenciado.");}
			rs.getStatement().getConnection().close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//dx egreso Dxegreso
		
		//destino pac Destinop
		
	}
	
	if(va.equals("22")){
		String efectivo = req.getParameter("efectivo");
		String anticipos = req.getParameter("anticipos");
		String moderacion = req.getParameter("moderacion");
				
		mm.Copago(venc,lot,copag,enca);
		mm.Efectivo(venc, lot, efectivo,enca);
		mm.Anticipo(venc,lot,anticipos,enca);
		mm.Modera(venc,lot,moderacion,enca);
		mm.Valor(venc, lot, subt, subtl,enca);
	}
	
	if(va.equals("23")){
		int toty=0;
		String tots="";
		String cants="";
		rs3=mm.ObtenerCodigoAdmision(enca);
		String CodAdm="";
		String EncaNue="";
		String TipoFac="";
		try {
			if(rs3.next()){
				CodAdm=rs3.getString(1);
				TipoFac=rs3.getString(2);
				rs4=mm.obtenerInfoEncaActivo(CodAdm);
				if(rs4.next()){
					EncaNue=rs4.getString(1);
				}
				rs4.getStatement().getConnection().close();					
			}
			rs3.getStatement().getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		if(TipoFac.equals("1")){/*por urgencias*/ rs1=mm.Valor(venc,lot,EncaNue); }else{rs1=mm.Valor(venc,lot,enca);}
		//rs1=mm.Valor(venc,lot,enca);
		try {
			while(rs1.next()){
			tots=rs1.getString(1);	
			cants=rs1.getString(2);	
			toty=toty+(Integer.parseInt(tots)*Integer.parseInt(cants));
			}
			rs1.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		
		String EstAdm ="";
		rs1=mm.estadoAdmision(venc);
		try {
			while(rs1.next()){
				EstAdm=rs1.getString(1);
			}
			rs1.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		
		out.print("<input name='epsh0' type='hidden' id='vh' value='"+toty+"'/></td>|"+EstAdm);
		
		//String tot=df.format(toty);
		}
	
	
	
	
	if(va.equals("16")){
		
		out.print("<table width='100%' class='style6'><tr><td colspan='5'><div align='center' class='style11' id='cabecera2'>Generar Nueva Tarifa </div></td></tr>");
				
		out.print("<table width='100%' class='style6' border='0' ><tr><td  width='10%'>Manual Tarifario: </td><td  width='20%'><label><select  style='width:220px' name='mtarn' id='mtarn' ><option value='Seleccione'>Seleccione</option>");
	//	rs1=mct.BuscarManualTarifario();
		try {
			while(rs1.next()){
			out.print("<option value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
			}
			rs1.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		out.print("</td></select>");
			
		out.print("<td width='8%'>Fecha Inicial:</td><td width='8%'><input type=text id='fechai0' size='7%'  onKeyup='masca(this,patron,true,0,0,0)'></td>"); 
		out.print("<td width='8%'>Fecha Final:</td><td width='8%'><input type=text id='fechaf0' size='7%'  onKeyup='masca(this,patron,true,0,0,0)'></td>"); 
		out.print("<td width='8%'>Incremento:</td><td width='5%'><input type=text id='valor0' size='2%' onkeyup='checknumt(0)' >%</td>");
		out.print("</table>");
	
		//////////////////////////////////////////Criterios//////////////////////////////////////
		
		out.print("<table width='100%' class='style6'><tr><td colspan='5'><div align='center' class='style11' id='cabecera2'>Criterios Base</div></td></tr>");
		
		out.print("<table width='100%' class='style6' border='0' ><tr><td  width='10%'>Manual Tarifario: </td><td  width='20%'><label><select  style='width:220px' name='mtara' id='mtara' ><option value='Seleccione'>Seleccione</option>");
	//	rs1=mct.BuscarManualTarifario();
		try {
			while(rs1.next()){
			out.print("<option value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
			}
			rs1.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		out.print("</select>");
		
		out.print("<td width='8%'>Factor de Redondeo:</td><td width='5%'><label><select   name='factor' id='factor' ><option value='Seleccione'>Seleccione</option><option value='100'>100</option><option value='10'>10</option><option value='1'>1</option>");
		
		out.print("</table>");
		out.print("<table width='100%' class='style6' border='0' ><tr>");
		out.print("<td colspan='4'  width='10%'><div align='center'><input type='button' name='btnConsultarPq' id='btnConsultarPq' value='Generar' onClick='GenerarT()'></div></td></tr></table>");
		out.print("<table width='100%' class='style6' border='0' >");
		out.print("<tr><td colspan='5'><div id='creacion'></div></td></tr></table>");
	}
	
	
	if(va.equals("17")){
	//	mct.GenerarTarifas(mtarp, fechai0, fechaf0, valor0, descch0, mbasec);
		out.print("El proceso se ha generado satisfactoriamente!!!");
	}
	
	if(va.equals("18")){
		String tipoFact = req.getParameter("tipoFact");
		String amb = req.getParameter("amb");
		try {
			rsef=mm.VerificarEncabezadoFacturado(enca); 
			if(rsef.next()){
				System.out.print("Ya existe una factura con este codigo de encabezado "+enca+" Usuario= "+Abreviado);
			}else{
				System.out.print("Creando la factura del encabezado "+enca);
				if(tipoFact.equals("1")){
					mm.actualizarMovUrg(enca);
				}
		
				String nf="";
				rs2=mm.NumFacturas();
		
				if(rs2.next()){
					nf=rs2.getString(1);
				}
				rs2.getStatement().getConnection().close();
		
				int l=nf.length();
				String sincc=nf.substring(2,l);
				int z=Integer.parseInt(sincc)+1;
				int lz=String.valueOf(z).length();
				String zc=String.valueOf(z);
		
				for(int i=lz; i<l-2; i++ ){//l es la longitud -2 x las CC
					zc="0"+zc;
					//System.out.println("zc: "+zc);
				}
		
				zc="CC"+zc;
		
				mm.ActualizarNumFacturas(zc);// ACTUALIZA EL CONSECUTIVO EN LA TABLA fact_numeros_fact
				mm.AsignarNumFact(fechacjmysql,encab,zc);//INGRESA EL CONSECUTIVO EN LA TABLA FACT_NUMERADAS
				rs2=mm.BuscarCodFactNumerada(fechacjmysql,encab,zc);
				String codfactNum="";
				while(rs2.next()){
					codfactNum=rs2.getString(1);
					mm.recordEstadoFact(codfactNum, "0", fechacjmysql, Abreviado);
				}
				rs2.getStatement().getConnection().close();

		
				Calendar calendario = Calendar.getInstance();
				//	Calendar calendario = new GregorianCalendar();
				int hora, minutos, segundos;
				hora =calendario.get(Calendar.HOUR_OF_DAY);
				minutos = calendario.get(Calendar.MINUTE);
				segundos = calendario.get(Calendar.SECOND);
				String hra= hora+":"+minutos+":"+segundos;
		
				mm.ActualizarEncabezado(fechacjmysql, hra, Abreviado ,encab );
			
			
				rs = mm.encaSinFacturar(venc,lot);
				//System.out.println("xxxxxxxxxxxxxxxxxxxxx2: "+venc+" - "+lot);
				int numMov=0;
				try {
					while(rs.next()){
						numMov++;
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
			
				rs = mm.primerEncaAdmision(venc,lot);
				String enca0="";
				try {
					while(rs.next()){
						enca0=rs.getString(1);
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
			
				rs = mm.movxEncabezados(enca0);
				int movEnca0=0;
				try {
					while(rs.next()){
						movEnca0++;
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				//System.out.println("ENTRA EN TIPO FACT 0");
				//System.out.println("ENTRA EN TIPO FACT 1");
				String cod_cuenta_fk="";
				String estado="0";
				String fecha_factura="";
				String fecha_insercion="";
				String hora_insercion="";
				String iva="0";
				String numero_factura="";
				String observacion="-";
				String precio_factura="";
				String ret_ica="-";
				String tipo="1";
			
				MetodoCuentas mc= new MetodoCuentas();						
				try {
					rsfc=mc.BuscarDatosParaFactura(encab);
					if(rsfc.next()){
						cod_cuenta_fk=rsfc.getString(9);
						fecha_factura=rsfc.getString(4);
						fecha_insercion=rsfc.getString(7);
						hora_insercion=rsfc.getString(8);
						numero_factura=rsfc.getString(2);
						precio_factura=rsfc.getString(3);
					
					}
					rsfc.getStatement().getConnection().close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				mc.CrearFactura(cod_cuenta_fk, estado, fecha_factura,
						fecha_insercion, hora_insercion, iva, numero_factura, 
						observacion, precio_factura, ret_ica, tipo, 
						Abreviado);
				try {
					rsfc=mc.DatosFacturaDetalle(numero_factura);
					if(rsfc.next()){					
						mc.CrearDetalleFactura(rsfc.getString(1), "0", fecha_factura, "1", 
								precio_factura, "-", "-", fecha_insercion, 
								hora_insercion, rsfc.getString(8), "CERO PESOS");
					
						mc.CrearPlazoCartera(numero_factura, precio_factura,rsfc.getString(1));
					}
					rsfc.getStatement().getConnection().close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
				//System.out.println(numMov+" "+movEnca0+" "+enca+" "+enca0+"tipo fact "+tipoFact);
			
				//Actualizar Auditoria de Facturas
				rs = mm.ValorFact(encab);
				String val="";
				try {
					while(rs.next()){
						val=rs.getString(1);
					}
					rs.getStatement().getConnection().close();				
					rs1=mm.ServicioAdmision(venc);
					if(rs1.next()){
						out.print(rs1.getString(1));
					}
					rs1.getStatement().getConnection().close();				
				} catch (SQLException e) { 
					out.print("Error "+e);
					e.printStackTrace();
				}
			
				mm.ActualizarAuditoriadeConvenios(lot,val);
				//fin de la actualizacion de auditoria
			
				if(amb.equals("undefined")){
					if(tipoFact.equals("0")){
						if(numMov==1&&movEnca0==0){
							mm.anularEncabezado(enca0);
							mm.ActualizarAdmision(venc);
						
						}
					}else{
						if(tipoFact.equals("1")){
							/*java.util.Date fechaActual = new java.util.Date();
						java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
						java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());*/
							mm.ActualizarAdmision(venc);
						
						}
					}
				}else{
					if(amb.equals("amb")){
						System.out.println("ENTRA EN TIPO FACT 2");
						mm.ActualizarAmbulatorio(venc);
					}
				
				}
			}
			rsef.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
	}

	
	if(va.equals("30")){
		//La idea es  consultar las facturas
		out.print("<table width='100%' border='0' align='center'><tr><td colspan='4' height='30' valign='top'><div align='center' class='style11' id='cabecera2'>Consulta de Facturas</div></td></tr></table>");
		
		//out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='60%'><i><div align='center'>Paciente</div></i></td><td width='30%'><i><div align='center'>Empresa</div></i></td><td width='10%'><i><div align='center'>Admisi�n</div></i></td></tr>");  //
		out.print("<table width='100%' border=0' class='style6' ><tr BGCOLOR='#D3D3D3' ><td colspan='4' height='30' valign='top'><i><div align='center'>Criterios de Busqueda</div></i></td></tr>");
		out.print("<tr></tr><tr><td width='15%'><div align='right'>Factura No:</div></td><td width='30%'><input name='txtFact' type='text' id='txtFact' size='39' maxlength='100' /></td>");
		out.print("<td width='15%'><div align='right'>Entidad:</div></td><td width='40%'><select  style='width:269px' name='Ent' id='Ent' ><option value='Seleccione'>Seleccione</option>");
		rs2=mm.Empresas();
		try {
			while(rs2.next()){
			out.print("<option title='"+rs2.getString(2)+"' value="+rs2.getString(1)+">"+rs2.getString(2)+"</option>");
			}
			rs2.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		out.print("</td></tr>");
					
		out.print("<tr><td><div align='right'>Tipo Doc:</div></td><td width='40%'><select  style='width:50px' name='tdoc' id='tdoc' >");
		rs2=mm.TipoDoc();
		try {
			while(rs2.next()){
			out.print("<option value="+rs2.getString(1)+">"+rs2.getString(2)+"</option>");
			}
			rs2.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		out.print("</select>");
		
		out.print(" Numero:<input name='txtDoc' type='text' id='txtDoc'  size='22' maxlength='50' /></td><td><div align='right'>Nombres:</div></td><td><input name='txtNom' type='text' id='txtNom' size='39' maxlength='100'/ ></td></tr>");
		out.print("<tr><td><div align='right'>Primer Apellido:</div></td><td><input name='txtPa' type='text' id='txtPa'  size='39' maxlength='50' /></td><td><div align='right'>Segundo Apellido:</div></td><td><input name='txtSa' type='text' id='txtSa'  size='39' maxlength='100'/ ></td></tr>");
		out.print("<tr><td><div align='right'>Fecha Inicial:</div></td><td><input name='FI' type='text' id='FI' size='10'  onKeyup='masca(this,patron,true,0,0,0)' onblur='vfi()'; /></td><td><div align='right'>Fecha Final:</div></td><td><input name='FF' type='text' id='FF' size='10' onKeyup='masca(this,patron,true,0,0,0)' onblur='vff()'; /></td></tr><tr></tr></table>");
		
		//out.print("<tr><td><div align='right'>Primer Apellido:</div></td><td><input name='txtCodigoPrestador' type='text' id='txtCodPrestador' size='39' maxlength='50' onkeydown='A(this, event)'/><span class='style8'>*</span></td><td><div align='right'>Segundo Apellido:</div></td><td><select name='listEstado' id='listEstado' style='width: 266px;' onkeydown='A(this, event)'><option value='Activo' selected='selected'>Activo</option><option value='Inactivo'>Inactivo</option></select><span class='style8'>*</span></td></tr></table>");
		out.print("<table width='100%' border='0' class='style6' ><tr><td colspan='4'><div align='center'><input name='btnCrearEntidad' type='button' id='btnCrearEntidad' value='     Consultar Facturas    ' onclick='CFact()' /></div></td></tr><tr></tr><tr></tr></table>");
		out.print("<table width='100%' border='0' class='style6' ><tr></tr><tr BGCOLOR='#D3D3D3' ><td colspan='4' height='30' valign='top'><i><div align='center'>Resultados de Busqueda</div></i></td></tr><tr></tr></table>");
		out.print("<div id='resultadosf' style='height: 240px; overflow-y: scroll'></div>");
		
		
	}
	
	
	if(va.equals("30A")){
		//La idea es  consultar las facturas
		out.print("<table width='100%' border='0' align='center'><tr><td colspan='4' height='30' valign='top'><div align='center' class='style11' id='cabecera2'>Auditoria de Facturas</div></td></tr></table>");
		
		//out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='60%'><i><div align='center'>Paciente</div></i></td><td width='30%'><i><div align='center'>Empresa</div></i></td><td width='10%'><i><div align='center'>Admisi�n</div></i></td></tr>");  //
		out.print("<table width='100%' border=0' class='style6' ><tr BGCOLOR='#D3D3D3' ><td colspan='4' height='30' valign='top'><i><div align='center'>Criterios de Busqueda</div></i></td></tr>");
		out.print("<tr></tr><tr><td width='15%'><div align='right'>Factura No:</div></td><td width='30%'><input name='txtFact' type='text' id='txtFact' size='39' maxlength='100' /></td>");
		out.print("<td width='15%'><div align='right'>Entidad:</div></td><td width='40%'><select  style='width:269px' name='Ent' id='Ent' ><option value='Seleccione'>Seleccione</option>");
		rs2=mm.Empresas();
		try {
			while(rs2.next()){
			out.print("<option title='"+rs2.getString(2)+"' value="+rs2.getString(1)+">"+rs2.getString(2)+"</option>");
			}
			rs2.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		out.print("</td></tr>");
					
		out.print("<tr><td><div align='right'>Tipo Doc:</div></td><td width='40%'><select  style='width:50px' name='tdoc' id='tdoc' >");
		rs2=mm.TipoDoc();
		try {
			while(rs2.next()){
			out.print("<option value="+rs2.getString(1)+">"+rs2.getString(2)+"</option>");
			}
			rs2.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		out.print("</select>");
		
		out.print(" Numero:<input name='txtDoc' type='text' id='txtDoc'  size='22' maxlength='50' /></td><td><div align='right'>Nombres:</div></td><td><input name='txtNom' type='text' id='txtNom' size='39' maxlength='100'/ ></td></tr>");
		out.print("<tr><td><div align='right'>Primer Apellido:</div></td><td><input name='txtPa' type='text' id='txtPa'  size='39' maxlength='50' /></td><td><div align='right'>Segundo Apellido:</div></td><td><input name='txtSa' type='text' id='txtSa'  size='39' maxlength='100'/ ></td></tr>");
		out.print("<tr><td><div align='right'>Fecha Inicial:</div></td><td><input name='FI' type='text' id='FI' size='10'  onKeyup='masca(this,patron,true,0,0,0)' onblur='vfi()'; /></td><td><div align='right'>Fecha Final:</div></td><td><input name='FF' type='text' id='FF' size='10' onKeyup='masca(this,patron,true,0,0,0)' onblur='vff()'; /></td></tr><tr></tr>");
		out.print("<tr><td><div align='right'>Usuario:</div></td><td width='40%'><select  style='width:200px' name='cmbuser' id='cmbuser' ><option value='Seleccione'>Seleccione</option>");
		rs2=mm.UsuariosFacturadores();
		try {
			while(rs2.next()){
			out.print("<option value="+rs2.getString(2)+">"+rs2.getString(1)+"</option>");
			}
			rs2.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		out.print("</select></table>");
		
		//out.print("<tr><td><div align='right'>Primer Apellido:</div></td><td><input name='txtCodigoPrestador' type='text' id='txtCodPrestador' size='39' maxlength='50' onkeydown='A(this, event)'/><span class='style8'>*</span></td><td><div align='right'>Segundo Apellido:</div></td><td><select name='listEstado' id='listEstado' style='width: 266px;' onkeydown='A(this, event)'><option value='Activo' selected='selected'>Activo</option><option value='Inactivo'>Inactivo</option></select><span class='style8'>*</span></td></tr></table>");
		out.print("<table width='100%' border='0' class='style6' ><tr><td colspan='4'><div align='center'><input name='btnCrearEntidad' type='button' id='btnCrearEntidad' value='     Consultar Facturas    ' onclick='CFactA()' /></div></td></tr><tr></tr><tr></tr></table>");
		out.print("<table width='100%' border='0' class='style6' ><tr></tr><tr BGCOLOR='#D3D3D3' ><td colspan='4' height='30' valign='top'><i><div align='center'>Resultados de Busqueda</div></i></td></tr><tr></tr></table>");
		out.print("<div id='resultadosf' style='height: 240px; overflow-y: scroll'></div>");
		
		
	}
	
	
	if(va.equals("31")){
		String sql="";
		String v1=req.getParameter("v1");
		if((v1==null)||(v1.equals(""))){v1="";}else{sql=sql+" AND n.consecutivo='"+v1+"'";}
		String v2=req.getParameter("v2");
		if(v2.equals("Seleccione")){v2="";}else{sql=sql+" AND ef.cod_eps="+v2;}
		String v3=req.getParameter("v3");
		if(v3==null){v3="";}
		String v4=req.getParameter("v4");
		if((v4==null)||(v4.equals(""))){v4="";}else{sql=sql+" AND p.tipo_documento='"+v3+"' AND p.numero_documento="+v4;}
		String v5=req.getParameter("v5");
		if((v5==null)||(v5.equals(""))){v5="";}else{sql=sql+" AND p.nombre like '"+v5+"%'";}
		String v6=req.getParameter("v6");
		if((v6==null)||(v6.equals(""))){v6="";}else{sql=sql+" AND p.primer_apellido like '"+v6+"%'";}
		String v7=req.getParameter("v7");
		if((v7==null)||(v7.equals(""))){v7="";}else{sql=sql+" AND p.segundo_apellido like '"+v7+"%'";}
		String v8=req.getParameter("v8");
		String v9=req.getParameter("v9");
		
		if((v8==null)||(v8.equals(""))||(v9.equals(""))||(v9==null)){v8="";v9="";}
		else{
		String dv8=v8.substring(0, 2);
		String mv8=v8.substring(3, 5);
		String av8=v8.substring(6, 10);
		String fv8=av8+"-"+mv8+"-"+dv8;
		
		String dv9=v9.substring(0, 2);
		String mv9=v9.substring(3, 5);
		String av9=v9.substring(6, 10);
		String fv9=av9+"-"+mv9+"-"+dv9;
		
		sql=sql+" AND ef.fecha BETWEEN '"+fv8+"' AND '"+fv9+"' ";}
		
		int crs=0;		
		rs2=mm.GeneraSQL(sql);
		try {
			while(rs2.next()){
				if(crs==0){
				out.print("<table width='100%' border='0' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='7%'><i><div align='center'>No. de Factura</div></i></td><td width='15%'><i><div align='center'>Empresa</div></i></td><td width='5%'><i><div align='center'>Fecha</div></i></td><td width='7%'><i><div align='center'>Documento</div></i></td><td width='30%'><i><div align='center'>Nombres</div></i></td></tr>	");  //
				}
			out.print("<tr><td><a  href='#'onclick='ventSelReFact(&quot;"+rs2.getString(1)+"&quot;,&quot;"+rs2.getString(1)+"&quot;,&quot;"+rs2.getString(9)+"&quot;,&quot;"+rs2.getString(10)+"&quot;)'>"+rs2.getString(1)+"</a></td><td>"+rs2.getString(2)+"</td><td>"+rs2.getString(8)+"</td><td>"+rs2.getString(3)+" "+rs2.getString(4)+"</td><td> "+rs2.getString(5)+" "+rs2.getString(6)+" "+rs2.getString(7)+"</td></tr>");
			crs++;
			}
			rs2.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		if(crs==0){out.print("No hay resultados para sus criterios de busqueda.");}
	}
	
	
	
	
	
	if(va.equals("31A")){
		String sql="";
	String vu=req.getParameter("vu");
	if((vu==null)||(vu.equals("Seleccione"))){vu="";}else{sql=sql+" AND ef.cod_usuario_fk="+vu+"";}
		String v1=req.getParameter("v1");
		if((v1==null)||(v1.equals(""))){v1="";}else{sql=sql+" AND n.consecutivo='"+v1+"'";}
		String v2=req.getParameter("v2");
		if(v2.equals("Seleccione")){v2="";}else{sql=sql+" AND ef.cod_eps="+v2;}
		String v3=req.getParameter("v3");
		if(v3==null){v3="";}
		String v4=req.getParameter("v4");
		if((v4==null)||(v4.equals(""))){v4="";}else{sql=sql+" AND p.tipo_documento='"+v3+"' AND p.numero_documento="+v4;}
		String v5=req.getParameter("v5");
		if((v5==null)||(v5.equals(""))){v5="";}else{sql=sql+" AND p.nombre like '"+v5+"%'";}
		String v6=req.getParameter("v6");
		if((v6==null)||(v6.equals(""))){v6="";}else{sql=sql+" AND p.primer_apellido like '"+v6+"%'";}
		String v7=req.getParameter("v7");
		if((v7==null)||(v7.equals(""))){v7="";}else{sql=sql+" AND p.segundo_apellido like '"+v7+"%'";}
		String v8=req.getParameter("v8");
		String v9=req.getParameter("v9");
		
		if((v8==null)||(v8.equals(""))||(v9.equals(""))||(v9==null)){v8="";v9="";}
		else{
		String dv8=v8.substring(0, 2);
		String mv8=v8.substring(3, 5);
		String av8=v8.substring(6, 10);
		String fv8=av8+"-"+mv8+"-"+dv8;
		
		String dv9=v9.substring(0, 2);
		String mv9=v9.substring(3, 5);
		String av9=v9.substring(6, 10);
		String fv9=av9+"-"+mv9+"-"+dv9;
		
		sql=sql+" AND ef.fecha BETWEEN '"+fv8+"' AND '"+fv9+"' ";}
		
		int crs=0;		
		rs2=mm.GeneraSQL(sql);
		try {
			while(rs2.next()){
				if(crs==0){
				out.print("<table width='100%' border='0' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='3%'><i><div align='center'>Item</div></i></td><td width='7%'><i><div align='center'>No. de Factura</div></i></td><td width='15%'><i><div align='center'>Empresa</div></i></td><td width='5%'><i><div align='center'>Fecha</div></i></td><td width='7%'><i><div align='center'>Documento</div></i></td><td width='30%'><i><div align='center'>Nombres</div></i></td></tr>	");  //
				}
			out.print("<tr><td>"+(crs+1)+"</td><td><a  href='#'onclick='ventSelReFact(&quot;"+rs2.getString(1)+"&quot;,&quot;"+rs2.getString(1)+"&quot;,&quot;"+rs2.getString(9)+"&quot;,&quot;"+rs2.getString(10)+"&quot;)'>"+rs2.getString(1)+"</a></td><td>"+rs2.getString(2)+"</td><td>"+rs2.getString(8)+"</td><td>"+rs2.getString(3)+" "+rs2.getString(4)+"</td><td> "+rs2.getString(5)+" "+rs2.getString(6)+" "+rs2.getString(7)+"</td></tr>");
			crs++;
			}
			rs2.getStatement().getConnection().close();
			//sql="AND ef.cod_usuario_fk=cesar";
			System.out.println(sql);
			out.print("</table><table width='100%' border='0' class='style6' ><tr><td><div align='center'><input type='button' value='Generar Reporte' name='btnIMP' onclick='ReporteA()' style='margin-right:10px'/></div></td></tr></table>");
			
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		if(crs==0){out.print("No hay resultados para sus criterios de busqueda.");}
	}
	
	
	
	
	
       ///////////////////////

if(va.equals("autoinv")){
		
		try {
			//System.out.print("ESSSTOO."+xx);
			rs =mm.listarPacientes(texto);
			String cadena ="";
			cadena="[";
			while(rs.next()){
				cadena = cadena+"'"+rs.getString(2)+"|"+rs.getString(3)+"|"+rs.getString(4)+"|"+rs.getString(5)+"|"+rs.getString(1)+"|"+rs.getString(6)+"|"+rs.getString("fecha_ingreso")+"|"+rs.getString("fecha_egreso")+"|"+rs.getString("num_autorizacion")+"'";
            	cadena = cadena +",";	
			}
			cadena = cadena+"]";
			//System.out.print("ESSSTOO."+cadena);
			res.getWriter().write(cadena);
			rs.getStatement().getConnection().close();
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
		}
	}
	
	
	if(va.equals("autoinvFH")){
		
		try {
			//System.out.print("ESSSTOO."+xx);
			rs =mm.listarPacientesh(texto,"0");
			String cadena ="";
			cadena="[";
			while(rs.next()){
				cadena = cadena+"'"+rs.getString(2)+"|"+rs.getString(3)+"|"+rs.getString(4)+"|"+rs.getString(5)+"|"+rs.getString(1)+"|"+rs.getString(6)+"|"+rs.getString("fecha_ingreso")+"|"+rs.getString("fecha_egreso")+"|"+rs.getString("num_autorizacion")+"'";
				cadena = cadena +",";	
			}
			cadena = cadena+"]";
			//System.out.print("ESSSTOO."+cadena);
			res.getWriter().write(cadena);
			rs.getStatement().getConnection().close();
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
		}
	}
	
	if(va.equals("autoinvFHa")){
		
		try {
			//System.out.print("ESSSTOO."+xx);
			rs =mm.listarPacientesh(texto,"1");
			String cadena ="";
			cadena="[";
			while(rs.next()){
				cadena = cadena+"'"+rs.getString(2)+"|"+rs.getString(3)+"|"+rs.getString(4)+"|"+rs.getString(5)+"|"+rs.getString(1)+"|"+rs.getString(6)+"|"+rs.getString("fecha_ingreso")+"|"+rs.getString("fecha_egreso")+"|"+rs.getString("num_autorizacion")+"'";
				cadena = cadena +",";	
			}
			cadena = cadena+"]";
			//System.out.print("ESSSTOO."+cadena);
			res.getWriter().write(cadena);
			rs.getStatement().getConnection().close();
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
		}
	}
	
	
if(va.equals("autoinvCE")){
		
		try {
			//System.out.print("ESSSTOO."+xx);
			rs =mm.listarPacientesCE(texto);
			String cadena ="";
			cadena="[";
			while(rs.next()){
				cadena = cadena+"'"+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+"|"+rs.getString(5)+"|"+rs.getString(6)+"|"+rs.getString(7)+"|"+rs.getString(1)+"|"+rs.getString(8)+"|"+rs.getString(9)+"'";
            	cadena = cadena +",";	
			}
			cadena = cadena+"]";
			//System.out.print("ESSSTOO."+cadena);
			res.getWriter().write(cadena);
			rs.getStatement().getConnection().close();
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
		}
	}
	
	
if(va.equals("autoinvh")){
	
	try {
		//System.out.print("ESSSTOO."+xx);
		rs =mm.listarPacientesh(texto,"0");
		String cadena ="";
		cadena="[";
		while(rs.next()){
		//	cadena = cadena+"'"+rs.getString(2)+"|"+rs.getString(3)+"|"+rs.getString(4)+"|"+rs.getString(5)+"|"+rs.getString(1)+"|"+rs.getString(6)+"'";
     		cadena = cadena+"'"+rs.getString(2)+"|"+rs.getString(3)+"|"+rs.getString(4)+"|"+rs.getString(5)+"|"+rs.getString(1)+"|"+rs.getString(6)+"|"+rs.getString("fecha_ingreso")+"|"+rs.getString("fecha_egreso")+"|"+rs.getString("num_autorizacion")+"'";
        	cadena = cadena +",";	
		}
		cadena = cadena+"]";
		//System.out.print("ESSSTOO."+cadena);
		res.getWriter().write(cadena);
		rs.getStatement().getConnection().close();
	} catch (Exception e) {
		e.getMessage();
		e.printStackTrace(); 
	}
}


if(va.equals("autoinvha")){
	 
	try {
		//System.out.print("ESSSTOO."+xx);
		rs =mm.listarPacientesh(texto,"1");
		String cadena ="";
		cadena="[";
		while(rs.next()){
		//	cadena = cadena+"'"+rs.getString(2)+"|"+rs.getString(3)+"|"+rs.getString(4)+"|"+rs.getString(5)+"|"+rs.getString(1)+"|"+rs.getString(6)+"'";
     		cadena = cadena+"'"+rs.getString(2)+"|"+rs.getString(3)+"|"+rs.getString(4)+"|"+rs.getString(5)+"|"+rs.getString(1)+"|"+rs.getString(6)+"|"+rs.getString("fecha_ingreso")+"|"+rs.getString("fecha_egreso")+"|"+rs.getString("num_autorizacion")+"'";
        	cadena = cadena +",";	
		}
		cadena = cadena+"]";
		//System.out.print("ESSSTOO."+cadena);
		res.getWriter().write(cadena);
		rs.getStatement().getConnection().close();
	} catch (Exception e) {
		e.getMessage();
		e.printStackTrace();
	}
}



	if(va.equals("automed")){
		//revisar este metodo
		
		//para el autocompletar medico
		
		//rvisar los rs con la concatenacion.
		try {
			//System.out.print("ESSSTOO."+xx);
			rs =mm.listarMed(texto);
			String cadena ="";
			cadena="[";
			while(rs.next()){
				cadena = cadena+"'"+rs.getString(2)+" "+rs.getString(3)+"|"+rs.getString(4)+"|"+rs.getString(1)+"'";
            	cadena = cadena +",";	
			}
			cadena = cadena+"]";
			//System.out.print("ESSSTOO."+cadena);
			res.getWriter().write(cadena);
			rs.getStatement().getConnection().close();
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
		}
	}
	
	if(va.equals("autoinv3")){
		
		try {
			rs =mm.listarPYS(texto,xx);
			String cadena ="";
			cadena="[";
			while(rs.next()){
				cadena = cadena+"'"+rs.getString(5)+"|"+rs.getString(4)+"|"+rs.getString(1)+"|"+rs.getString(2)+"|"+rs.getString(3)+"|"+rs.getString(6)+"|"+rs.getString(7)+"|"+rs.getString(8)+"|"+rs.getString(9)+"|"+rs.getString(10)+"|"+rs.getString(11)+"'";
            	cadena = cadena +",";	
			}
			cadena = cadena+"]";
			//System.out.print("ESSSTOO."+cadena);
			res.getWriter().write(cadena);
			rs.getStatement().getConnection().close();
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
		}
	}
	
	
	if(va.equals("autoinv4")){
		
		try {

			rs =mm.listarRef(texto,xx);
			String cadena ="";
			cadena="[";
			while(rs.next()){
				cadena = cadena+"'"+rs.getString(4)+"|"+rs.getString(5)+"|"+rs.getString(1)+"|"+rs.getString(2)+"|"+rs.getString(3)+"|"+rs.getString(6)+"|"+rs.getString(7)+"|"+rs.getString(8)+"|"+rs.getString(9)+"|"+rs.getString(10)+"|"+rs.getString(11)+"'";
            	cadena = cadena +",";	
            	//System.out.print("cadena."+cadena);
			}
			cadena = cadena+"]";
			//System.out.print("ESSSTOO."+cadena);
			res.getWriter().write(cadena);
			rs.getStatement().getConnection().close();
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
		}
	}


/* FUNCIONANDO 
	
	if(va.equals("32")){
		String codAdmision = req.getParameter("codAdmision");
		String movimientos = req.getParameter("movimientos");
		String encabezado = req.getParameter("enca");
		
		//System.out.println(movimientos);
		//System.out.println("1. "+movimientos);
		
		String[] detMov = movimientos.split("_");
		int cantDetMov=0;
		String detMovServ = "";
		for(int i=0;i<detMov.length;i++){
			//System.out.println("1.1. "+detMov[i]);
			String[] temp =  detMov[i].split(",");
			//System.out.println("2. "+temp[0]);
			//System.out.println("3. "+temp[1]);
			rs = mm.obtenerDetMovServicios(temp[0],codAdmision,temp[1]);
			try {
				while(rs.next()){
					detMovServ+=rs.getString(1)+"-";
					cantDetMov++;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		
		
		//System.out.println("2. "+detMovServ);
		detMovServ = detMovServ.substring(0,detMovServ.length()-1);
		
		rs = mm.movSinFacturar(codAdmision);
		int movSinFact=0;
		try {
			while(rs.next()){
				movSinFact++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		detMov = detMovServ.split("-");
		String eps="";
		String razonSocial="";
		String nit="";
		String dir="";
		String tel="";
		String ciudad="";
		String condPago="";
		String paciente="";
		String documento="";
		String dirP="";
		String telP="";
		String tipoAfi="";
		String estrato="";
		String fechaIngreso="";
		String numAutorizacion="";
		String codUsuario="";
		String poliza="";
		String tipo="";
		String fechaEgreso="";
		
		rs = mm.obtenerInfoEnca(codAdmision);
		try {
			if(rs.next()){
				 eps=rs.getString(2);
				 razonSocial=rs.getString(3);
				 nit=rs.getString(4);
				 dir=rs.getString(5);
				 tel=rs.getString(6);
				 ciudad=rs.getString(7);
				 condPago=rs.getString(8);
				 paciente=rs.getString(9);
				 documento=rs.getString(10);
				 dirP=rs.getString(11);
				 telP=rs.getString(12);
				 tipoAfi=rs.getString(13);
				 estrato=rs.getString(14);
				 fechaIngreso=rs.getString(15);
				 fechaEgreso=rs.getString(16);
				 numAutorizacion=rs.getString(18);
				 poliza=rs.getString(20);
				 tipo=rs.getString(23);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mm.duplicarEncabezado(eps, razonSocial, nit, dir, tel, ciudad, condPago, paciente, documento, dirP, telP, tipoAfi, estrato, fechaIngreso, codAdmision, numAutorizacion, poliza, tipo,fechaEgreso);
		rs = mm.obtenerInfoEnca(codAdmision);
		String encaNuevo="";
		try {
			if(rs.next()){
				 encaNuevo=rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		encabezado = encaNuevo;
		
		for(int i=0;i<detMov.length;i++){
			mm.actualizarMovimiento(detMov[i], encabezado);
		}
		out.print("<table width='100%'  class='style6' border='0'>");
		out.print("<tr><td  width='50%'><label><input name='radiobutton' type='radio' value='radiobutton' id='10' onclick='Radios("+codAdmision+","+eps+","+encabezado+",&quot;resul&quot;,0,0)' />La Entidad</label></td>");
		out.print("<td width='40%'><label><input name='radiobutton' type='radio' value='radiobutton' id='11' onclick='Radios("+codAdmision+","+eps+","+encabezado+",&quot;resul&quot;,0,0)' />El Usuario</label></td>");
		out.print("<td width='10%'><label><input name='radiobutton' type='radio' value='radiobutton' id='12' onclick='Radios("+codAdmision+","+eps+","+encabezado+",&quot;resul&quot;,0,0)' />Compartido</label></td></tr>");
		out.print("<tr><td colspan='5'><div id='Opcion'></div></td></tr>");
		
	}
	
*/



if(va.equals("32")){
		System.out.println("32");
		String codAdmision = req.getParameter("codAdmision");
		String movimientos = req.getParameter("movimientos");
		String encabezado = req.getParameter("enca");
		System.out.println("codAdmision"+codAdmision+" encabezado"+encabezado);
		//System.out.println(movimientos);
		//System.out.println("1. "+movimientos);
		
		String[] detMov = movimientos.split("_");
		int cantDetMov=0;
		String detMovServ = "";
		for(int i=0;i<detMov.length;i++){
			//System.out.println("1.1. "+detMov[i]);
			String[] temp =  detMov[i].split(",");
			//System.out.println("2. "+temp[0]);
			//System.out.println("3. "+temp[1]);
			rs = mm.obtenerDetMovServicios(temp[0],codAdmision,temp[1]);
			try {
				while(rs.next()){
					detMovServ+=rs.getString(1)+"-";
					cantDetMov++;
				}rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		
		
		//System.out.println("2. "+detMovServ);
		detMovServ = detMovServ.substring(0,detMovServ.length()-1);
		
		rs = mm.movSinFacturar(codAdmision,lot);
		int movSinFact=0;
		try {
			while(rs.next()){
				movSinFact++;
			}rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		detMov = detMovServ.split("-");
		String eps="";
		String razonSocial="";
		String nit="";
		String dir="";
		String tel="";
		String ciudad="";
		String condPago="";
		String paciente="";
		String documento="";
		String dirP="";
		String telP="";
		String tipoAfi="";
		String estrato="";
		String fechaIngreso="";
		String numAutorizacion="";
		//String codUsuario="";
		String poliza="";
		String tipo="";
		String fechaEgreso="";
		
	
		//rs = mm.obtenerInfoEnca2(codAdmision,encabezado);
		rs = mm.obtenerInfoEnca(codAdmision);
		
		try {
			if(rs.next()){
				 eps=rs.getString(2);
				 razonSocial=rs.getString(3);
				 nit=rs.getString(4);
				 dir=rs.getString(5);
				 tel=rs.getString(6);
				 ciudad=rs.getString(7);
				 condPago=rs.getString(8);
				 paciente=rs.getString(9);
				 documento=rs.getString(10);
				 dirP=rs.getString(11);
				 telP=rs.getString(12);
				 tipoAfi=rs.getString(13);
				 estrato=rs.getString(14);
				 fechaIngreso=rs.getString(15);
				 fechaEgreso=rs.getString(16);
				 numAutorizacion=rs.getString(18);
				 poliza=rs.getString(20);
				 tipo=rs.getString(23);
			}
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("oooooooooooooooooooooooooooooooooo "+eps);
		mm.duplicarEncabezado(eps,
				razonSocial,
				nit,
				dir,
				tel,
				ciudad,
				condPago,
				paciente,
				documento,
				dirP,
				telP,
				tipoAfi,
				estrato,
				fechaIngreso,
				codAdmision,
				numAutorizacion,
				poliza,
				tipo,
				fechaEgreso);
		rs = mm.obtenerInfoEncaDuplica(codAdmision,eps);
		String encaNuevo="";
		try {
			if(rs.next()){
				 encaNuevo=rs.getString(1);
				 System.out.println("valor de la consulta "+rs.getString(1));
			}rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Valor de Encabezado Nuevo "+encaNuevo);
		encabezado = encaNuevo;
		
		for(int i=0;i<detMov.length;i++){
			//System.out.println("oooooooooooooooooooooooooooooooooo "+encabezado);
			mm.actualizarMovimiento(detMov[i], encabezado);
		}
		
		//System.out.println("tipotipotipotipotipotipotipotipotipotipotipotipo "+tipo+" - "+codAdmision+" - "+eps+" - "+encabezado);
		if(tipo.equals("2")){//Aqui manda la seleccion de hospiatalizacion empresa, usuario,compartido                                
		out.print("<table width='100%'  class='style6' border='0'>");
		out.print("<tr><td  width='50%'><label><input name='radiobutton' type='radio' value='radiobutton' id='10' onclick='Radios("+codAdmision+","+eps+","+encabezado+",&quot;resul&quot;,0,0)' />La Entidad</label></td>");
		out.print("<td width='40%'><label><input name='radiobutton' type='radio' value='radiobutton' id='11' onclick='Radios("+codAdmision+","+eps+","+encabezado+",&quot;resul&quot;,0,0)' />El Usuario</label></td>");
		out.print("<td width='10%'><label><input name='radiobutton' type='radio' value='radiobutton' id='12' onclick='Radios("+codAdmision+","+eps+","+encabezado+",&quot;resul&quot;,0,0)' />Compartido</label></td></tr>");
		out.print("<tr><td colspan='5'><div id='Opcion'></div></td></tr>");
		out.print("<tr><td colspan='5'><div id='resul'></div></td></tr>");
		}
		
		if(tipo.equals("3")){//Aqui manda la seleccion de ambulatorio empresa, usuario,compartido
			out.print("<table width='100%'  class='style6' border='0'>");
			out.print("<tr><td  width='50%'><label><input name='radiobutton' type='radio' value='radiobutton' id='10' onclick='Radios("+codAdmision+","+eps+","+encabezado+",&quot;resul&quot;,0,2)' />La Entidad</label></td>");
			out.print("<td width='40%'><label><input name='radiobutton' type='radio' value='radiobutton' id='11' onclick='Radios("+codAdmision+","+eps+","+encabezado+",&quot;resul&quot;,0,2)' />El Usuario</label></td>");
			out.print("<td width='10%'><label><input name='radiobutton' type='radio' value='radiobutton' id='12' onclick='Radios("+codAdmision+","+eps+","+encabezado+",&quot;resul&quot;,0,2)' />Compartido</label></td></tr>");
			out.print("<tr><td colspan='5'><div id='Opcion'></div></td></tr>");
			out.print("<tr><td colspan='5'><div id='resul'></div></td></tr>");
		}
		
		if(tipo.equals("4")){//Aqui manda la seleccion de ambulatorio empresa, usuario,compartido
			out.print("<table width='100%'  class='style6' border='0'>");
			out.print("<tr><td  width='50%'><label><input name='radiobutton' type='radio' value='radiobutton' id='10' onclick='Radios("+codAdmision+","+eps+","+encabezado+",&quot;resul&quot;,0,2)' />La Entidad</label></td>");
			out.print("<td width='40%'><label><input name='radiobutton' type='radio' value='radiobutton' id='11' onclick='Radios("+codAdmision+","+eps+","+encabezado+",&quot;resul&quot;,0,2)' />El Usuario</label></td>");
			out.print("<td width='10%'><label><input name='radiobutton' type='radio' value='radiobutton' id='12' onclick='Radios("+codAdmision+","+eps+","+encabezado+",&quot;resul&quot;,0,2)' />Compartido</label></td></tr>");
			out.print("<tr><td colspan='5'><div id='Opcion'></div></td></tr>");
			out.print("<tr><td colspan='5'><div id='resul'></div></td></tr>");
		}
		
	}








/* FUNCIONANDO 	
	if(va.equals("33")){
		
		String movimientos = req.getParameter("movimientos");
		String encabezado = req.getParameter("enca");
		lot = req.getParameter("lot");
		
		//System.out.println("mov "+movimientos);
		if(movimientos!=""){
			String[] detMov = movimientos.split("_");
			
			
			String detMovServ = "";
			for(int i=0;i<detMov.length;i++){
				String[] temp = detMov[i].split(",");
				rs = mm.obtenerDetMovServicios(temp[0],venc,temp[1]);
				try {
					while(rs.next()){
						detMovServ+=rs.getString(1)+"-";
						
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
			
			detMovServ = detMovServ.substring(0,detMovServ.length()-1);
			String[] detMov2 = detMovServ.split("-");
			
			for(int i=0;i<detMov2.length;i++){
				mm.actualizarMovimiento(detMov2[i], encabezado);
			}
		}
		rs = mm.encaSinFacturar(venc);
		int encaSinFacturar=0;
		try {
			while(rs.next()){
				encaSinFacturar++;
			}
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		
		rs=mm.movSinFacturar(venc);
		int movSinFacturar=0;
		try {
			while(rs.next()){
				movSinFacturar++;
			}
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		
		//System.out.println(encaSinFacturar+" "+movSinFacturar);
		if(encaSinFacturar>0||movSinFacturar>0){
			out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='100%' align='center'>Movimientos Cargados</td></tr></table>");  //
			out.print("<table width='100%' border='0' class='style6' ><tr ><td width='14.28%'><i><div align='center'>Todos: <input onclick='autoChecked(this,&quot;TODOS&quot;,&quot;"+venc+"&quot;,&quot;"+enca+"&quot;,&quot;"+lot+"&quot;)' type='checkbox' name='checkbox'  id='chkAll' value='ffff' ></div></i></td>" +
					"<td width='14.28%'><i><div align='center'></div></i></td>" +
					"<td width='16%'><i><div align='center'></div></i></td>" +
					"<td width='14.28%'><i><div align='center'></div></i></td>" +
					"<td width='14.28%'><i><div align='center'></div></i></td>" +
					"<td width='14.28%'><i><div align='center'></div></i></td>" +
					"<td width='14.28%'><i><div align='center'></div></i></td>" +
					"</tr></table>");
			out.print("<div id='titulo'><table id='movimientos' width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3'><td width='5%'><i>Codigo</div></td><td width='44%'><i>Descripci�n</i></td><td width='6%'><i>Fecha</i></td><td width='5%'><i>Cantidad</i></td></i></td><td width='5%'><i>Valor</i></td><td width='6%'><i>Seleccione</i></td></tr></table></div>");		out.print("<div id='resulM' >");
			out.print("<table id='movimientos' width='100%' border='1' class='style6' ><tr ><td width='5%'><i><div align='center'></div></i></td><td width='44%'><i><div align='center'></div></i></td><td width='6%'><i><div align='center'></div></i></td><td width='5%'><i><div align='center'></div></i></td></i></td><td width='5%'><i><div align='center'></div></i></td><td width='6%'><i><div align='center'></div></i></td></tr>");
			
					rs2=mm.movSinFacturar(venc);
					//out.print("<input name='enca' type='hidden' id='enca' value='"+rs1.getString(1)+"' />");
					int lineas=0;
					try {
						while(rs2.next()){
							
						
								out.print("<tr><td>"+rs2.getString(4)+"</td>");
								out.print("<td>"+rs2.getString(5)+"</td>");
								out.print("<td>"+rs2.getString(8)+"</td>");	
								out.print("<td>"+rs2.getString(9)+"</td>");
								out.print("<td>"+rs2.getString(10)+"</td>");
								out.print("	<td><input type='checkbox' name='checkbox' onclick='desmarcarEnca(&quot;"+venc+"&quot;,&quot;"+enca+"&quot;,&quot;"+lot+"&quot;)' id='check"+lineas+"' value='"+rs2.getString(4)+"|"+rs2.getString(12)+"|"+rs2.getString(13)+"' ></td></tr>");
								lineas++;
				
							
						}
						rs2.getStatement().getConnection().close();
						} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
						}
					////////////////////
				
						/*	out.print("<table width='100%'  class='style6' border='0'>");
			out.print("<tr><td  width='50%'><label><input name='radiobutton' type='radio' value='radiobutton' id='10' onclick='Radios("+venc+","+lot+","+encab+")' />La Entidad</label></td>");
			out.print("<td width='40%'><label><input name='radiobutton' type='radio' value='radiobutton' id='11' onclick='Radios("+venc+","+lot+","+encab+")' />El Usuario</label></td>");
			out.print("<td width='10%'><label><input name='radiobutton' type='radio' value='radiobutton' id='12' onclick='Radios("+venc+","+lot+","+encab+")' />Compartido</label></td></tr>");
			//out.print("<tr><td colspan='5'><div id='Opcion'></div></td></tr>");*/
	/*		
			out.print("</table><input type='hidden' value='"+lineas+"' id='numLineas'/>");
			out.print("</div>");
			rs = mm.encaSinFacturar(venc);
			
			out.print("<div id='encabezados' class='encabezados'>");
			out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='100%' align='center'>Facturas por generar</td></tr></table>");  
			int numEnca=0;
			
			try {
				rs.next();	
				numEnca++;
				while(rs.next()){
					out.print("<input type='radio' id='rad"+numEnca+"' name='enca' value='"+rs.getString(1)+"' onclick='moverMovA(this,&quot;"+venc+"&quot;,&quot;"+lot+"&quot;)'/><a href='#' onclick='verFact(&quot;"+rs.getString(1)+"&quot;,&quot;"+venc+"&quot;,&quot;"+lot+"&quot;,0)'>Factura "+numEnca+"</a>");
					//System.out.print("<input type='radio' id='rad"+numEnca+"' name='enca' value='"+rs.getString(1)+"' onclick='moverMovA(this)'/><a href='#' onclick='verFact("+rs.getString(1)+")'>Factura 002</a>");
	
					numEnca++;
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
			}
			out.print("<input type='hidden' value='"+numEnca+"' id='numEnca'/>");
			out.print("</div>");
		
	
			rs = mm.encaFacturados(venc);
	
			out.print("<div id='encaFact' class='encabezados'>");
			out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='100%' align='center'>Facturas generadas</td></tr></table>");  
			
			try {
				while(rs.next()){
					out.print("<a href='#' onclick='CFacturas(&quot;"+rs.getString(2)+"&quot;)'>Factura "+rs.getString(2)+"</a>");
					//System.out.print("<a href='#' onclick='verFact(&quot;"+rs.getString(2)+"&quot;)'>Factura "+rs.getString(2)+"</a>");
	
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
			}
			out.print("<input type='hidden' value='"+numEnca+"' id='numEnca'/>");
			out.print("</div>");
		}else{
			out.print("actualizar");
		}

		
	
	}
		
	
FUNCIONANDO */		



if(va.equals("33")){
		
		String movimientos = req.getParameter("movimientos");
		String encabezado = req.getParameter("enca");
		lot = req.getParameter("lot");
		
		//System.out.println("movimientos "+movimientos);
		//System.out.println("encabezado "+encabezado);
		//System.out.println("lot "+lot);
		
		if(movimientos!=""){
			String[] detMov = movimientos.split("_");
			//System.out.println("ESTE ES EL VECTOR Q SE REVIENTA!!!!detMov "+detMov);
			
			
			String detMovServ = "";
			for(int i=0;i<detMov.length;i++){
				String[] temp = detMov[i].split(",");
				rs = mm.obtenerDetMovServicios(temp[0],venc,temp[1]);
				try {
					while(rs.next()){
						detMovServ+=rs.getString(1)+"-";
						
					}rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
			//System.out.println("AAAAAQQQQUUUUUIIIIII!!!!detMovServ "+detMovServ);
			detMovServ = detMovServ.substring(0,detMovServ.length()-1);
			//System.out.println("rrrrrrrrrrrrrrrrrII!!!!detMovServ "+detMovServ);
			String[] detMov2 = detMovServ.split("-");
			
			for(int i=0;i<detMov2.length;i++){
				mm.actualizarMovimiento(detMov2[i], encabezado);
			}
		}
		rs = mm.encaSinFacturar(venc,lot);
		//System.out.println("xxxxxxxxxxxxxxxxxxxxx3: "+venc+" - "+lot);
		int encaSinFacturar=0;
		try {
			while(rs.next()){
				encaSinFacturar++;
			}
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		
		rs=mm.movSinFacturar(venc,lot);
		int movSinFacturar=0;
		try {
			while(rs.next()){
				movSinFacturar++;
			}
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		
		//System.out.println(encaSinFacturar+" "+movSinFacturar);
		if(encaSinFacturar>0||movSinFacturar>0){
			out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='100%' align='center'>Movimientos Cargados</td></tr></table>");  //
			out.print("<table width='100%' border='0' class='style6' ><tr ><td width='14.28%'><i><div align='center'>Todos: <input onclick='autoChecked(this,&quot;TODOS&quot;,&quot;"+venc+"&quot;,&quot;"+enca+"&quot;,&quot;"+lot+"&quot;)' type='checkbox' name='checkbox'  id='chkAll' value='ffff' ></div></i></td>" +
					"<td width='14.28%'><i><div align='center'></div></i></td>" +
					"<td width='16%'><i><div align='center'></div></i></td>" +
					"<td width='14.28%'><i><div align='center'></div></i></td>" +
					"<td width='14.28%'><i><div align='center'></div></i></td>" +
					"<td width='14.28%'><i><div align='center'></div></i></td>" +
					"<td width='14.28%'><i><div align='center'></div></i></td>" +
					"</tr></table>");
			out.print("<div id='titulo'><table id='movimientos' width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3'><td width='5%'><i>Codigo</div></td><td width='44%'><i>Descripci�n</i></td><td width='6%'><i>Fecha</i></td><td width='5%'><i>Cantidad</i></td></i></td><td width='5%'><i>Valor</i></td><td width='6%'><i>Seleccione</i></td></tr></table></div>");		out.print("<div id='resulM' >");
			out.print("<table id='movimientos' width='100%' border='1' class='style6' ><tr ><td width='5%'><i><div align='center'></div></i></td><td width='44%'><i><div align='center'></div></i></td><td width='6%'><i><div align='center'></div></i></td><td width='5%'><i><div align='center'></div></i></td></i></td><td width='5%'><i><div align='center'></div></i></td><td width='6%'><i><div align='center'></div></i></td></tr>");
			
					rs2=mm.movSinFacturar(venc,lot);
					//out.print("<input name='enca' type='hidden' id='enca' value='"+rs1.getString(1)+"' />");
					int lineas=0;
					try {
						while(rs2.next()){
							
						
								out.print("<tr><td>"+rs2.getString(4)+"</td>");
								out.print("<td>"+rs2.getString(5)+"</td>");
								out.print("<td>"+rs2.getString(8)+"</td>");	
								out.print("<td>"+rs2.getString(9)+"</td>");
								out.print("<td>"+rs2.getString(10)+"</td>");
								out.print("	<td><input type='checkbox' name='checkbox' onclick='desmarcarEnca(&quot;"+venc+"&quot;,&quot;"+enca+"&quot;,&quot;"+lot+"&quot;)' id='check"+lineas+"' value='"+rs2.getString(4)+"|"+rs2.getString(12)+"|"+rs2.getString(13)+"' ></td></tr>");
								lineas++;
				
							
						}
						rs2.getStatement().getConnection().close();
						} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
						}
					////////////////////
				
						/*	out.print("<table width='100%'  class='style6' border='0'>");
			out.print("<tr><td  width='50%'><label><input name='radiobutton' type='radio' value='radiobutton' id='10' onclick='Radios("+venc+","+lot+","+encab+")' />La Entidad</label></td>");
			out.print("<td width='40%'><label><input name='radiobutton' type='radio' value='radiobutton' id='11' onclick='Radios("+venc+","+lot+","+encab+")' />El Usuario</label></td>");
			out.print("<td width='10%'><label><input name='radiobutton' type='radio' value='radiobutton' id='12' onclick='Radios("+venc+","+lot+","+encab+")' />Compartido</label></td></tr>");
			//out.print("<tr><td colspan='5'><div id='Opcion'></div></td></tr>");*/
			
			out.print("</table><input type='hidden' value='"+lineas+"' id='numLineas'/>");
			out.print("</div>");
			rs = mm.encaSinFacturar(venc,lot);
			//System.out.println("xxxxxxxxxxxxxxxxxxxxx4: "+venc+" - "+lot);
			
			out.print("<div id='encabezados' class='encabezados'>");
			out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='100%' align='center'>Facturas por generar</td></tr></table>");  
			int numEnca=1;
			
			try {
				//rs.next();	
				//numEnca++;
				while(rs.next()){
					out.print("<input type='radio' id='rad"+numEnca+"' name='enca' value='"+rs.getString(1)+"' onclick='moverMovA(this,&quot;"+venc+"&quot;,&quot;"+lot+"&quot;)'/><a href='#' onclick='verFact(&quot;"+rs.getString(1)+"&quot;,&quot;"+venc+"&quot;,&quot;"+lot+"&quot;,0)'>Factura "+numEnca+"</a>");
					//System.out.print("<input type='radio' id='rad"+numEnca+"' name='enca' value='"+rs.getString(1)+"' onclick='moverMovA(this)'/><a href='#' onclick='verFact("+rs.getString(1)+")'>Factura 002</a>");
	
					numEnca++;
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
			}
			out.print("<input type='hidden' value='"+numEnca+"' id='numEnca'/>");
			out.print("</div>");
		
	
			rs = mm.encaFacturados(venc,lot);
	
			out.print("<div id='encaFact' class='encabezados'>");
			out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='100%' align='center'>Facturas generadas</td></tr></table>");  
			
			try {
				while(rs.next()){
					out.print("<a href='#' onclick='CFacturas(&quot;"+rs.getString(2)+"&quot;)'>Factura "+rs.getString(2)+"</a>");
					//System.out.print("<a href='#' onclick='verFact(&quot;"+rs.getString(2)+"&quot;)'>Factura "+rs.getString(2)+"</a>");
	
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
			}
			out.print("<input type='hidden' value='"+numEnca+"' id='numEnca'/>");
			out.print("</div>");
		}else{
			out.print("actualizar");
		}

		
	
	}





	
	
	
	////////////////////
if(va.equals("34")){
	//System.out.println(enca+" "+venc+" "+lot);	
	rs = mm.primerEncaAdmision(venc,lot);	
	String  enca0 = "";
	try {
		while(rs.next()){
			enca0 = rs.getString(1);
		}
		rs.getStatement().getConnection().close();
	} catch (SQLException e) {
		out.print("Error "+e);
		e.printStackTrace();
	}
	
		
	String opcion = req.getParameter("opcion");
	out.print("<div><table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='100%' align='center'>Movimientos Cargados...</td></tr></table>");  //
	//out.print("<table width='100%' border='0' class='style6' ><tr ><td width='14.28%'><i><div align='center'>Todos: <input onclick='autoChecked(this,&quot;TODOS&quot;)' type='checkbox' name='checkbox'  id='chkAll' value='ffff' ></div></i></td><td width='14.28%'><i><div align='center'>POS: <input type='checkbox' name='checkbox' id='chkPos' value='ffff' ></div></i></td><td width='16%'><i><div align='center'>No POS: <input type='checkbox' name='checkbox' id='chkNPos' value='ffff' ></div></i></td><td width='14.28%'><i><div align='center'>Materiales: <input type='checkbox' onclick='autoChecked(this,&quot;MATERIALES&quot;)' name='checkbox' id='chkMat' value='ffff' ></div></i></td></i></td><td width='14.28%'><i><div align='center'>Laboratorios: <input onclick='autoChecked(this,&quot;LABORATORIO&quot;)' type='checkbox' name='checkbox' id='chkLab' value='ffff' ></div></i></td><td width='14.28%'><i><div align='center'>Imagenologia: <input onclick='autoChecked(this,&quot;IMAGENOLOGIA&quot;)' type='checkbox' name='checkbox' id='chkImg' value='ffff' ></div></i></td><td width='14.28%'><i><div align='center'>Corte 30 d�as: <input onclick='autoCheckedCorte(this)' type='checkbox' name='checkbox' id='dddd' value='ffff' ></div></i></td></tr></table>");
	out.print("<div id='resulM' >");
	out.print("<table id='movimientos' width='100%' border='1' class='style6' ><tr ><td width='5%'><i><div align='center'></div></i></td><td width='44%'><i><div align='center'></div></i></td><td width='6%'><i><div align='center'></div></i></td><td width='5%'><i><div align='center'></div></i></td></i></td><td width='5%'><i><div align='center'></div></i></td><td width='6%'><i><div align='center'></div></i></td></tr>");
	
			rs2=mm.movxEncabezados(enca);
			//out.print("<input name='enca' type='hidden' id='enca' value='"+rs1.getString(1)+"' />");
			int lineas=0;
			try {
				while(rs2.next()){
					
				
						out.print("<tr><td>"+rs2.getString(4)+"</td>");
						out.print("<td>"+rs2.getString(5)+"</td>");
						out.print("<td>"+rs2.getString(8)+"</td>");	
						out.print("<td>"+rs2.getString(9)+"</td>");
						out.print("<td>"+rs2.getString(10)+"</td>");
						out.print("<td><a href='#' onclick='anularMovFact(&quot;"+rs2.getString(4)+"&quot;,&quot;"+enca0+"&quot;,&quot;"+enca+"&quot;,&quot;"+venc+"&quot;,&quot;"+lot+"&quot;,&quot;"+rs2.getString(13)+"&quot;)'>Anular</a></td></tr>");
						lineas++;
		
					
				}
				rs2.getStatement().getConnection().close();
				} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
				}
			
	
	out.print("</table></div></div>");
	//System.out.print("<div><table width='100%' class='style6' ><tr><td colspan='6'  width='10%'><div align='center'><input type='button' name='btnCerrar' id='btnCerrar' value='Cerrar' onClick='cerrarVentDetEnca(&quot;"+enca+"&quot;,&quot;"+venc+"&quot;)'></div></td></tr></table></div>");

	if(opcion.equals("0")){
		out.print("<div><table width='100%' class='style6' ><tr><td colspan='6'  width='10%'><div align='center'><input type='button' name='btnGFactura' id='btnGFactura' value='Facturar' onClick='GF(&quot;"+venc+"&quot;,&quot;"+enca+"&quot;,&quot;"+lot+"&quot;)'><input type='button' name='btnCerrar' id='btnCerrar' value='Cerrar' onClick='cerrarVentDetEnca("+venc+","+enca+","+lot+")'></div></td></tr></table></div>");
	}else{
		if(opcion.equals("1")){
			out.print("<div><table width='100%' class='style6' ><tr><td colspan='6'  width='10%'><div align='center'><input type='button' name='btnCerrar' id='btnCerrar' value='Cerrar' onClick='cerrarVentDetEnca("+venc+","+enca+","+lot+")'></div></td></tr></table></div>");
		}
	}
	
	
	
	//rs = mm.encaSinFacturar(venc);

	

	}
  //////////////////
	
	
	if(va.equals("35")){
		
		out.print("<table width='100%'  class='style6' border='0'>");
		out.print("<tr><td  width='50%'><label><input name='radiobutton' type='radio' value='radiobutton' id='10' onclick='Radios("+venc+","+lot+","+enca+",&quot;resul2&quot;,1,0)' />La Entidad</label></td>");
		out.print("<td width='40%'><label><input name='radiobutton' type='radio' value='radiobutton' id='11' onclick='Radios("+venc+","+lot+","+enca+",&quot;resul2&quot;,1,0)' />El Usuario</label></td>");
		out.print("<td width='10%'><label><input name='radiobutton' type='radio' value='radiobutton' id='12' onclick='Radios("+venc+","+lot+","+enca+",&quot;resul2&quot;,1,0)' />Compartido</label></td></tr>");
		out.print("<tr><td colspan='5'><div id='Opcion'></div></td></tr>");
		out.print("<table width='100%'><tr><td><div id='resul2' ></div></td></tr></table>");
	}
	
	///////
	
	if(va.equals("36")){
		String codMov = req.getParameter("codMov");
		String enca0 = req.getParameter("enca0");
		//System.out.println(codMov);
		String detMovServ = "";
		String[] detCodMov = codMov.split(","); 
		rs = mm.obtenerDetMovServicios(detCodMov[0],venc,detCodMov[1]);
		try {
			while(rs.next()){
				detMovServ+=rs.getString(1)+"-";
				//System.out.println(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		detMovServ = detMovServ.substring(0,detMovServ.length()-1);
		
		//System.out.println(detMovServ);
		String[] vecdetMovServ = detMovServ.split("-");
		for (int i=0;i<vecdetMovServ.length;i++){
			mm.anularMovFact(vecdetMovServ[i], enca0);
		}
		
		
		rs2=mm.movxEncabezados(enca);
		//out.print("<input name='enca' type='hidden' id='enca' value='"+rs1.getString(1)+"' />");
		int lineas=0;
		try {
			while(rs2.next()){
				lineas++;	
			}
			rs2.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		
		if(lineas==0){
			mm.anularEncabezado(enca);
			out.print("anulado");
		}
		
		
	}
	/////
	if(va.equals("dtPac")){
		System.out.println("dtpac enca"+enca);
		rs=mm.datPac(enca);
		String dat="";
		try {
			while(rs.next()){
				dat = rs.getString(1)+"|"+rs.getString(2)+"|"+rs.getString(3)+"|"+rs.getString("fecha_ingreso")+"|"+rs.getString("fecha_egreso")+"|"+rs.getString("num_autorizacion");
			}
			rs.getStatement().getConnection().close();
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
		}
		out.print(dat);
		System.out.println("dat:::::: "+dat);
	}
	
	if(va.equals("0")){
		
		out.print("<table width='100%'  class='style6' border='0'><tr>");
		out.print("<td colspan='5'><div align='center' class='style11' id='cabecera2'>Seleccione Una Opcion </div></td></tr>");
		out.print("<tr><td  width='25%'><label><input name='radiobutton' type='radio' value='radiobutton' id='sfe' onclick='OpcionEnvioFact()' />Facturas para Enviar</label></td>");
		out.print("<td width='25%'><label><input name='radiobutton' type='radio' value='radiobutton' id='cfe' onclick='OpcionEnvioFact()' />Consultar facturas enviadas</label></td>");
		out.print("<td width='25%'><label><input name='radiobutton' type='radio' value='radiobutton' id='sfea' onclick='OpcionEnvioFact()' />Facturas para Auditoria Concurrente</label></td>");
		out.print("<td width='25%'><label><input name='radiobutton' type='radio' value='radiobutton' id='cfea' onclick='OpcionEnvioFact()' />Consultar facturas enviadas a Auditoria</label></td></tr>");
		out.print("<tr><td colspan='5'><div id='Opcion'></div></td></tr>");
		out.print("</table>");
		
	}
	
	
	if(va.equals("00")){
		
		out.print("<table width='100%'  class='style6' border='0'><tr>");
		out.print("<td colspan='5'><div align='center' class='style11' id='cabecera2'>Seleccione Una Opcion </div></td></tr>");
		out.print("<tr><td  width='50%'><label><input name='radiobutton' type='radio' value='radiobutton' id='sfr' onclick='OpcionEnvioFact()' />Seleccionar Facturas a Radicar</label></td>");
		out.print("<td width='50%'><label><input name='radiobutton' type='radio' value='radiobutton' id='cfr' onclick='OpcionEnvioFact()' />Consultar facturas Radicadas</label></td></tr>");
		out.print("<tr><td colspan='5'><div id='Opcion'></div></td></tr>");
		out.print("</table>");
		
	}
	
	if(va.equals("00D")){
		
		out.print("<table width='100%'  class='style6' border='0'><tr>");
		out.print("<td colspan='5'><div align='center' class='style11' id='cabecera2'>Seleccione Una Opcion </div></td></tr>");
		out.print("<tr><td  width='50%'><label><input name='radiobutton' type='radio' value='radiobutton' id='sfrd' onclick='OpcionEnvioFact()' />Seleccionar Facturas a Devolver</label></td>");
		out.print("<td width='50%'><label><input name='radiobutton' type='radio' value='radiobutton' id='cfrd' onclick='OpcionEnvioFact()' />Listado de Facturas Devueltas</label></td></tr>");
		out.print("<tr><td colspan='5'><div id='Opcion'></div></td></tr>");
		out.print("</table>");
		
	}

	
	if(va.equals("00I")){
		
		out.print("<table width='100%'  class='style6' border='0'><tr>");
		out.print("<td colspan='5'><div align='center' class='style11' id='cabecera2'>Seleccione Una Opcion </div></td></tr>");
		out.print("<tr><td  width='50%'><label><input name='radiobutton' type='radio' value='radiobutton' id='sfri' onclick='OpcionEnvioFact()' />Seleccionar Facturas a Radicar Internamente</label></td>");
		out.print("<td width='50%'><label><input name='radiobutton' type='radio' value='radiobutton' id='cfri' onclick='OpcionEnvioFact()' />Consultar facturas Radicadas Internamente</label></td></tr>");
		out.print("<tr><td colspan='5'><div id='Opcion'></div></td></tr>");
		out.print("</table>");
		
	}
	
	if(va.equals("sfe")){
		//System.out.println("La idea es  consultar las facturas");
		out.print("<table width='100%' border='0' align='center'><tr><td colspan='4' height='30' valign='top'><div align='center' class='style11' id='cabecera2'>Consulta de Facturas</div></td></tr></table>");
		
		//out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='60%'><i><div align='center'>Paciente</div></i></td><td width='30%'><i><div align='center'>Empresa</div></i></td><td width='10%'><i><div align='center'>Admisi�n</div></i></td></tr>");  //
		out.print("<table width='100%' border=0' class='style6' ><tr BGCOLOR='#D3D3D3' ><td colspan='4' height='30' valign='top'><i><div align='center'>Criterios de Busqueda</div></i></td></tr>");
		out.print("<tr></tr><tr><td width='15%'><div align='right'>Factura No:</div></td><td width='30%'><input name='txtFact' type='text' id='txtFact' size='39' maxlength='100' /></td>");
		out.print("<td width='15%'><div align='right'>Entidad:</div></td><td width='40%'><select  style='width:269px' name='Ent' id='Ent' ><option value='Seleccione'>Seleccione</option>");
		rs2=mm.Empresas();
		try {
			while(rs2.next()){
			out.print("<option title='"+rs2.getString(2)+"' value="+rs2.getString(1)+">"+rs2.getString(2)+"</option>");
			}
			rs2.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		out.print("</td></tr>");
					
		out.print("<tr><td><div align='right'>Tipo Doc:</div></td><td width='40%'><select  style='width:50px' name='tdoc' id='tdoc' >");
		rs2=mm.TipoDoc();
		try {
			while(rs2.next()){
			out.print("<option value="+rs2.getString(1)+">"+rs2.getString(2)+"</option>");
			}
			rs2.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		out.print("</select>");
		
		out.print(" Numero:<input name='txtDoc' type='text' id='txtDoc'  size='22' maxlength='50' /></td><td><div align='right'>Nombres:</div></td><td><input name='txtNom' type='text' id='txtNom' size='39' maxlength='100'/ ></td></tr>");
		out.print("<tr><td><div align='right'>Primer Apellido:</div></td><td><input name='txtPa' type='text' id='txtPa'  size='39' maxlength='50' /></td><td><div align='right'>Segundo Apellido:</div></td><td><input name='txtSa' type='text' id='txtSa'  size='39' maxlength='100'/ ></td></tr>");
		out.print("<tr><td><div align='right'>Fecha Inicial:</div></td><td><input name='FI' type='text' id='FI' size='10'  onKeyup='masca(this,patron,true,0,0,0)' onblur='vfi()'; /></td><td><div align='right'>Fecha Final:</div></td><td><input name='FF' type='text' id='FF' size='10' onKeyup='masca(this,patron,true,0,0,0)' onblur='vff()'; /></td></tr><tr></tr></table>");
		
		//out.print("<tr><td><div align='right'>Primer Apellido:</div></td><td><input name='txtCodigoPrestador' type='text' id='txtCodPrestador' size='39' maxlength='50' onkeydown='A(this, event)'/><span class='style8'>*</span></td><td><div align='right'>Segundo Apellido:</div></td><td><select name='listEstado' id='listEstado' style='width: 266px;' onkeydown='A(this, event)'><option value='Activo' selected='selected'>Activo</option><option value='Inactivo'>Inactivo</option></select><span class='style8'>*</span></td></tr></table>");
		out.print("<table width='100%' border='0' class='style6' ><tr><td colspan='4'><div align='center'><input name='btnCrearEntidad' type='button' id='btnCrearEntidad' value='     Consultar Facturas    ' onclick='BuscarFactPEnviar(0)' /></div></td></tr><tr></tr><tr></tr></table>");
		out.print("<table width='100%' border='0' class='style6' ><tr></tr><tr BGCOLOR='#D3D3D3' ><td colspan='4' height='30' valign='top'><i><div align='center'>Resultados de Busqueda</div></i></td></tr><tr></tr></table>");
		out.print("<div id='resultadosf' style='height: 200px; overflow-y: scroll'></div>");
		out.print("<div id='btnEnvFact' align='center'></div>");	
	}
	
	
	if(va.equals("sfr")){
		//System.out.println("La idea es  consultar las facturas");
		out.print("<table width='100%' border='0' align='center'><tr><td colspan='4' height='30' valign='top'><div align='center' class='style11' id='cabecera2'>Consulta de Facturas</div></td></tr></table>");
		
		//out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='60%'><i><div align='center'>Paciente</div></i></td><td width='30%'><i><div align='center'>Empresa</div></i></td><td width='10%'><i><div align='center'>Admisi�n</div></i></td></tr>");  //
		out.print("<table width='100%' border=0' class='style6' ><tr BGCOLOR='#D3D3D3' ><td colspan='4' height='30' valign='top'><i><div align='center'>Criterios de Busqueda</div></i></td></tr>");
		out.print("<tr></tr><tr><td width='15%'><div align='right'>Factura No:</div></td><td width='30%'><input name='txtFact' type='text' id='txtFact' size='39' maxlength='100' /></td>");
		out.print("<td width='15%'><div align='right'>Entidad:</div></td><td width='40%'><select  style='width:269px' name='Ent' id='Ent' ><option value='Seleccione'>Seleccione</option>");
		rs2=mm.Empresas();
		try {
			while(rs2.next()){
			out.print("<option title='"+rs2.getString(2)+"' value="+rs2.getString(1)+">"+rs2.getString(2)+"</option>");
			}
			rs2.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		out.print("</td></tr>");
					
		out.print("<tr><td><div align='right'>Tipo Doc:</div></td><td width='40%'><select  style='width:50px' name='tdoc' id='tdoc' >");
		rs2=mm.TipoDoc();
		try {
			while(rs2.next()){
			out.print("<option value="+rs2.getString(1)+">"+rs2.getString(2)+"</option>");
			}
			rs2.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		out.print("</select>");
		
		out.print(" Numero:<input name='txtDoc' type='text' id='txtDoc'  size='22' maxlength='50' /></td><td><div align='right'>Nombres:</div></td><td><input name='txtNom' type='text' id='txtNom' size='39' maxlength='100'/ ></td></tr>");
		out.print("<tr><td><div align='right'>Primer Apellido:</div></td><td><input name='txtPa' type='text' id='txtPa'  size='39' maxlength='50' /></td><td><div align='right'>Segundo Apellido:</div></td><td><input name='txtSa' type='text' id='txtSa'  size='39' maxlength='100'/ ></td></tr>");
		out.print("<tr><td><div align='right'>Fecha Inicial:</div></td><td><input name='FI' type='text' id='FI' size='10'  onKeyup='masca(this,patron,true,0,0,0)' onblur='vfi()'; /></td><td><div align='right'>Fecha Final:</div></td><td><input name='FF' type='text' id='FF' size='10' onKeyup='masca(this,patron,true,0,0,0)' onblur='vff()'; /></td></tr><tr></tr>");
		out.print("<tr><td><div align='right'>Cuenta de Cobro:</div></td><td><input name='CDC' type='text' id='CDC' size='15'   /></td><td></td><td></td></tr><tr></tr></table>");
		
		//out.print("<tr><td><div align='right'>Primer Apellido:</div></td><td><input name='txtCodigoPrestador' type='text' id='txtCodPrestador' size='39' maxlength='50' onkeydown='A(this, event)'/><span class='style8'>*</span></td><td><div align='right'>Segundo Apellido:</div></td><td><select name='listEstado' id='listEstado' style='width: 266px;' onkeydown='A(this, event)'><option value='Activo' selected='selected'>Activo</option><option value='Inactivo'>Inactivo</option></select><span class='style8'>*</span></td></tr></table>");
		out.print("<table width='100%' border='0' class='style6' ><tr><td colspan='4'><div align='center'><input name='btnCrearEntidad' type='button' id='btnCrearEntidad' value='     Consultar Facturas    ' onclick='BuscarFactPRadicar(0)' /></div></td></tr><tr></tr><tr></tr></table>");
		out.print("<table width='100%' border='0' class='style6' ><tr></tr><tr BGCOLOR='#D3D3D3' ><td colspan='4' height='30' valign='top'><i><div align='center'>Resultados de Busqueda</div></i></td></tr><tr></tr></table>");
		out.print("<div id='resultadosf' style='height: 200px; overflow-y: scroll'></div>");
		out.print("<div id='btnEnvFact' align='center'></div>");	
	}
	
	
	
	if(va.equals("sfea")){
		//System.out.println("La idea es  consultar las facturas");
		out.print("<table width='100%' border='0' align='center'><tr><td colspan='4' height='30' valign='top'><div align='center' class='style11' id='cabecera2'>Consulta de Facturas para enviar a Auditoria</div></td></tr></table>");
		
		//out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='60%'><i><div align='center'>Paciente</div></i></td><td width='30%'><i><div align='center'>Empresa</div></i></td><td width='10%'><i><div align='center'>Admisi�n</div></i></td></tr>");  //
		out.print("<table width='100%' border=0' class='style6' ><tr BGCOLOR='#D3D3D3' ><td colspan='4' height='30' valign='top'><i><div align='center'>Criterios de Busqueda</div></i></td></tr>");
		out.print("<tr></tr><tr><td width='15%'><div align='right'>Factura No:</div></td><td width='30%'><input name='txtFact' type='text' id='txtFact' size='39' maxlength='100' /></td>");
		out.print("<td width='15%'><div align='right'>Entidad:</div></td><td width='40%'><select  style='width:269px' name='Ent' id='Ent' ><option value='Seleccione'>Seleccione</option>");
		rs2=mm.Empresas();
		try {
			while(rs2.next()){
			out.print("<option title='"+rs2.getString(2)+"' value="+rs2.getString(1)+">"+rs2.getString(2)+"</option>");
			}
			rs2.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		out.print("</td></tr>");
					
		out.print("<tr><td><div align='right'>Tipo Doc:</div></td><td width='40%'><select  style='width:50px' name='tdoc' id='tdoc' >");
		rs2=mm.TipoDoc();
		try {
			while(rs2.next()){
			out.print("<option value="+rs2.getString(1)+">"+rs2.getString(2)+"</option>");
			}
			rs2.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		out.print("</select>");
		
		out.print(" Numero:<input name='txtDoc' type='text' id='txtDoc'  size='22' maxlength='50' /></td><td><div align='right'>Nombres:</div></td><td><input name='txtNom' type='text' id='txtNom' size='39' maxlength='100'/ ></td></tr>");
		out.print("<tr><td><div align='right'>Primer Apellido:</div></td><td><input name='txtPa' type='text' id='txtPa'  size='39' maxlength='50' /></td><td><div align='right'>Segundo Apellido:</div></td><td><input name='txtSa' type='text' id='txtSa'  size='39' maxlength='100'/ ></td></tr>");
		out.print("<tr><td><div align='right'>Fecha Inicial:</div></td><td><input name='FI' type='text' id='FI' size='10'  onKeyup='masca(this,patron,true,0,0,0)' onblur='vfi()'; /></td><td><div align='right'>Fecha Final:</div></td><td><input name='FF' type='text' id='FF' size='10' onKeyup='masca(this,patron,true,0,0,0)' onblur='vff()'; /></td></tr><tr></tr></table>");
		
		//out.print("<tr><td><div align='right'>Primer Apellido:</div></td><td><input name='txtCodigoPrestador' type='text' id='txtCodPrestador' size='39' maxlength='50' onkeydown='A(this, event)'/><span class='style8'>*</span></td><td><div align='right'>Segundo Apellido:</div></td><td><select name='listEstado' id='listEstado' style='width: 266px;' onkeydown='A(this, event)'><option value='Activo' selected='selected'>Activo</option><option value='Inactivo'>Inactivo</option></select><span class='style8'>*</span></td></tr></table>");
		out.print("<table width='100%' border='0' class='style6' ><tr><td colspan='4'><div align='center'><input name='btnCrearEntidad' type='button' id='btnCrearEntidad' value='     Consultar Facturas    ' onclick='BuscarFactPEnviar(1)' /></div></td></tr><tr></tr><tr></tr></table>");
		out.print("<table width='100%' border='0' class='style6' ><tr></tr><tr BGCOLOR='#D3D3D3' ><td colspan='4' height='30' valign='top'><i><div align='center'>Resultados de Busqueda</div></i></td></tr><tr></tr></table>");
		out.print("<div id='resultadosf' style='height: 200px; overflow-y: scroll'></div>");
		out.print("<div id='btnEnvFact' align='center'></div>");	
	}
	
	if(va.equals("sfri")){
		//System.out.println("La idea es  consultar las facturas");
		out.print("<table width='100%' border='0' align='center'><tr><td colspan='4' height='30' valign='top'><div align='center' class='style11' id='cabecera2'>Consulta de Facturas</div></td></tr></table>");
		
		//out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='60%'><i><div align='center'>Paciente</div></i></td><td width='30%'><i><div align='center'>Empresa</div></i></td><td width='10%'><i><div align='center'>Admisi�n</div></i></td></tr>");  //
		out.print("<table width='100%' border=0' class='style6' ><tr BGCOLOR='#D3D3D3' ><td colspan='4' height='30' valign='top'><i><div align='center'>Criterios de Busqueda</div></i></td></tr>");
		out.print("<tr></tr><tr><td width='15%'><div align='right'>Factura No:</div></td><td width='30%'><input name='txtFact' type='text' id='txtFact' size='39' maxlength='100' /></td>");
		out.print("<td width='15%'><div align='right'>Entidad:</div></td><td width='40%'><select  style='width:269px' name='Ent' id='Ent' ><option value='Seleccione'>Seleccione</option>");
		rs2=mm.Empresas();
		try {
			while(rs2.next()){
			out.print("<option title='"+rs2.getString(2)+"' value="+rs2.getString(1)+">"+rs2.getString(2)+"</option>");
			}
			rs2.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		out.print("</td></tr>");
					
		out.print("<tr><td><div align='right'>Tipo Doc:</div></td><td width='40%'><select  style='width:50px' name='tdoc' id='tdoc' >");
		rs2=mm.TipoDoc();
		try {
			while(rs2.next()){
			out.print("<option value="+rs2.getString(1)+">"+rs2.getString(2)+"</option>");
			}
			rs2.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		out.print("</select>");
		
		out.print(" Numero:<input name='txtDoc' type='text' id='txtDoc'  size='22' maxlength='50' /></td><td><div align='right'>Nombres:</div></td><td><input name='txtNom' type='text' id='txtNom' size='39' maxlength='100'/ ></td></tr>");
		out.print("<tr><td><div align='right'>Primer Apellido:</div></td><td><input name='txtPa' type='text' id='txtPa'  size='39' maxlength='50' /></td><td><div align='right'>Segundo Apellido:</div></td><td><input name='txtSa' type='text' id='txtSa'  size='39' maxlength='100'/ ></td></tr>");
		out.print("<tr><td><div align='right'>Fecha Inicial:</div></td><td><input name='FI' type='text' id='FI' size='10'  onKeyup='masca(this,patron,true,0,0,0)' onblur='vfi()'; /></td><td><div align='right'>Fecha Final:</div></td><td><input name='FF' type='text' id='FF' size='10' onKeyup='masca(this,patron,true,0,0,0)' onblur='vff()'; /></td></tr><tr></tr>");
		out.print("<tr><td><div align='right'>Cuenta de Cobro:</div></td><td><input name='CDC' type='text' id='CDC' size='15'   /></td><td></td><td></td></tr><tr></tr></table>");
		
		//out.print("<tr><td><div align='right'>Primer Apellido:</div></td><td><input name='txtCodigoPrestador' type='text' id='txtCodPrestador' size='39' maxlength='50' onkeydown='A(this, event)'/><span class='style8'>*</span></td><td><div align='right'>Segundo Apellido:</div></td><td><select name='listEstado' id='listEstado' style='width: 266px;' onkeydown='A(this, event)'><option value='Activo' selected='selected'>Activo</option><option value='Inactivo'>Inactivo</option></select><span class='style8'>*</span></td></tr></table>");
		out.print("<table width='100%' border='0' class='style6' ><tr><td colspan='4'><div align='center'><input name='btnCrearEntidad' type='button' id='btnCrearEntidad' value='     Consultar Facturas    ' onclick='BuscarFactPRadicar(1)' /></div></td></tr><tr></tr><tr></tr></table>");
		out.print("<table width='100%' border='0' class='style6' ><tr></tr><tr BGCOLOR='#D3D3D3' ><td colspan='4' height='30' valign='top'><i><div align='center'>Resultados de Busqueda</div></i></td></tr><tr></tr></table>");
		out.print("<div id='resultadosf' style='height: 200px; overflow-y: scroll'></div>");
		out.print("<div id='btnEnvFact' align='center'></div>");	
	}
	
	if(va.equals("cfe")){
		String opcion = req.getParameter("opcion");
		//System.out.println("La idea es  consultar las facturas");
		out.print("<table width='100%' border='0' align='center'><tr><td colspan='4' height='30' valign='top'><div align='center' class='style11' id='cabecera2'>Consulta de Cuentas de Cobro</div></td></tr></table>");
		
		//out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='60%'><i><div align='center'>Paciente</div></i></td><td width='30%'><i><div align='center'>Empresa</div></i></td><td width='10%'><i><div align='center'>Admisi�n</div></i></td></tr>");  //
		out.print("<table width='100%' border=0' class='style6' ><tr BGCOLOR='#D3D3D3' ><td colspan='4' height='30' valign='top'><i><div align='center'>Criterios de Busqueda</div></i></td></tr>");
		out.print("<tr></tr><tr><td width='15%'><div align='right'>Cuenta de Cobro No:</div></td><td width='30%'><input name='txtCdC' type='text' id='txtCdC' size='39' maxlength='100' /></td></tr>");
		out.print("<tr></tr><tr><td width='15%'><div align='right'>Factura No:</div></td><td width='30%'><input name='txtFact' type='text' id='txtFact' size='39' maxlength='100' /></td>");
		out.print("<td width='15%'><div align='right'>Entidad:</div></td><td width='40%'><select  style='width:269px' name='Ent' id='Ent' ><option value='Seleccione'>Seleccione</option>");
		rs2=mm.Empresas();
		try {
			while(rs2.next()){
			out.print("<option title='"+rs2.getString(2)+"' value="+rs2.getString(1)+">"+rs2.getString(2)+"</option>");
			}
			rs2.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		out.print("</td></tr>");
					
		out.print("<tr><td><div align='right'>Tipo Doc:</div></td><td width='40%'><select  style='width:50px' name='tdoc' id='tdoc' >");
		rs2=mm.TipoDoc();
		try {
			while(rs2.next()){
			out.print("<option value="+rs2.getString(1)+">"+rs2.getString(2)+"</option>");
			}
			rs2.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		out.print("</select>");
		
		out.print(" Numero:<input name='txtDoc' type='text' id='txtDoc'  size='22' maxlength='50' /></td><td><div align='right'>Nombres:</div></td><td><input name='txtNom' type='text' id='txtNom' size='39' maxlength='100'/ ></td></tr>");
		out.print("<tr><td><div align='right'>Primer Apellido:</div></td><td><input name='txtPa' type='text' id='txtPa'  size='39' maxlength='50' /></td><td><div align='right'>Segundo Apellido:</div></td><td><input name='txtSa' type='text' id='txtSa'  size='39' maxlength='100'/ ></td></tr>");
		out.print("<tr><td><div align='right'>Fecha Inicial:</div></td><td><input name='FI' type='text' id='FI' size='10'  onKeyup='masca(this,patron,true,0,0,0)' onblur='vfi()'; /></td><td><div align='right'>Fecha Final:</div></td><td><input name='FF' type='text' id='FF' size='10' onKeyup='masca(this,patron,true,0,0,0)' onblur='vff()'; /></td></tr><tr></tr></table>");
		
		//out.print("<tr><td><div align='right'>Primer Apellido:</div></td><td><input name='txtCodigoPrestador' type='text' id='txtCodPrestador' size='39' maxlength='50' onkeydown='A(this, event)'/><span class='style8'>*</span></td><td><div align='right'>Segundo Apellido:</div></td><td><select name='listEstado' id='listEstado' style='width: 266px;' onkeydown='A(this, event)'><option value='Activo' selected='selected'>Activo</option><option value='Inactivo'>Inactivo</option></select><span class='style8'>*</span></td></tr></table>");
		out.print("<table width='100%' border='0' class='style6' ><tr><td colspan='4'><div align='center'><input name='btnCrearEntidad' type='button' id='btnCrearEntidad' value='     Consultar     ' onclick='BuscarCuentaDeCobro(&quot;"+opcion+"&quot;)' /></div></td></tr><tr></tr><tr></tr></table>");
		out.print("<table width='100%' border='0' class='style6' ><tr></tr><tr BGCOLOR='#D3D3D3' ><td colspan='4' height='30' valign='top'><i><div align='center'>Resultados de Busqueda</div></i></td></tr><tr></tr></table>");
		out.print("<div id='resultadosf' style='height: 200px; overflow-y: scroll'></div>");
			
	}
	
	
	if(va.equals("cfr")){
		String opcion = req.getParameter("opcion");
		//System.out.println("La idea es  consultar las facturas");
		out.print("<table width='100%' border='0' align='center'><tr><td colspan='4' height='30' valign='top'><div align='center' class='style11' id='cabecera2'>Consulta de Cuentas Radicadas</div></td></tr></table>");
		
		//out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='60%'><i><div align='center'>Paciente</div></i></td><td width='30%'><i><div align='center'>Empresa</div></i></td><td width='10%'><i><div align='center'>Admisi�n</div></i></td></tr>");  //
		out.print("<table width='100%' border=0' class='style6' ><tr BGCOLOR='#D3D3D3' ><td colspan='4' height='30' valign='top'><i><div align='center'>Criterios de Busqueda</div></i></td></tr>");
		//out.print("<tr></tr><tr><td width='15%'><div align='right'>Cuenta de Cobro No:</div></td><td width='30%'><input name='txtCdC' type='text' id='txtCdC' size='39' maxlength='100' /></td></tr>");
		out.print("<tr></tr><tr><td width='15%'><div align='right'>Factura No:</div></td><td width='30%'><input name='txtFact' type='text' id='txtFact' size='39' maxlength='100' /></td>");
		out.print("<td width='15%'><div align='right'>Entidad:</div></td><td width='40%'><select  style='width:269px' name='Ent' id='Ent' ><option value='Seleccione'>Seleccione</option>");
		rs2=mm.Empresas();
		try {
			while(rs2.next()){
			out.print("<option title='"+rs2.getString(2)+"' value="+rs2.getString(1)+">"+rs2.getString(2)+"</option>");
			}
			rs2.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		out.print("</td></tr>");
					
		out.print("<tr><td><div align='right'>Tipo Doc:</div></td><td width='40%'><select  style='width:50px' name='tdoc' id='tdoc' >");
		rs2=mm.TipoDoc();
		try {
			while(rs2.next()){
			out.print("<option value="+rs2.getString(1)+">"+rs2.getString(2)+"</option>");
			}
			rs2.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		out.print("</select>");
		
		out.print(" Numero:<input name='txtDoc' type='text' id='txtDoc'  size='22' maxlength='50' /></td><td><div align='right'>Nombres:</div></td><td><input name='txtNom' type='text' id='txtNom' size='39' maxlength='100'/ ></td></tr>");
		out.print("<tr><td><div align='right'>Primer Apellido:</div></td><td><input name='txtPa' type='text' id='txtPa'  size='39' maxlength='50' /></td><td><div align='right'>Segundo Apellido:</div></td><td><input name='txtSa' type='text' id='txtSa'  size='39' maxlength='100'/ ></td></tr>");
		out.print("<tr><td><div align='right'>Fecha Inicial:</div></td><td><input name='FI' type='text' id='FI' size='10'  onKeyup='masca(this,patron,true,0,0,0)' onblur='vfi()'; /></td><td><div align='right'>Fecha Final:</div></td><td><input name='FF' type='text' id='FF' size='10' onKeyup='masca(this,patron,true,0,0,0)' onblur='vff()'; /></td></tr><tr></tr>");
		out.print("<tr><td><div align='right'>Numero de Radicaci�n:</div></td><td><input name='CDC' type='text' id='CDC' size='15'   /></td><td></td><td></td></tr><tr></tr></table>");
		
		//out.print("<tr><td><div align='right'>Primer Apellido:</div></td><td><input name='txtCodigoPrestador' type='text' id='txtCodPrestador' size='39' maxlength='50' onkeydown='A(this, event)'/><span class='style8'>*</span></td><td><div align='right'>Segundo Apellido:</div></td><td><select name='listEstado' id='listEstado' style='width: 266px;' onkeydown='A(this, event)'><option value='Activo' selected='selected'>Activo</option><option value='Inactivo'>Inactivo</option></select><span class='style8'>*</span></td></tr></table>");
		out.print("<table width='100%' border='0' class='style6' ><tr><td colspan='4'><div align='center'><input name='btnCrearEntidad' type='button' id='btnCrearEntidad' value='     Consultar     ' onclick='BuscarCuentaDeRadicado(&quot;"+opcion+"&quot;,0)' /></div></td></tr><tr></tr><tr></tr></table>");
		out.print("<table width='100%' border='0' class='style6' ><tr></tr><tr BGCOLOR='#D3D3D3' ><td colspan='4' height='30' valign='top'><i><div align='center'>Resultados de Busqueda</div></i></td></tr><tr></tr></table>");
		out.print("<div id='resultadosf' style='height: 200px; overflow-y: scroll'></div>");
			
	}
	
	

	
	if(va.equals("cfea")){
		String opcion = req.getParameter("opcion");
		//System.out.println("La idea es  consultar las facturas");
		out.print("<table width='100%' border='0' align='center'><tr><td colspan='4' height='30' valign='top'><div align='center' class='style11' id='cabecera2'>Consulta de Relaci�n de Envios a Auditoria</div></td></tr></table>");
		
		//out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='60%'><i><div align='center'>Paciente</div></i></td><td width='30%'><i><div align='center'>Empresa</div></i></td><td width='10%'><i><div align='center'>Admisi�n</div></i></td></tr>");  //
		out.print("<table width='100%' border=0' class='style6' ><tr BGCOLOR='#D3D3D3' ><td colspan='4' height='30' valign='top'><i><div align='center'>Criterios de Busqueda</div></i></td></tr>");
		//out.print("<tr></tr><tr><td width='15%'><div align='right'>Cuenta de Cobro No:</div></td><td width='30%'><input name='txtCdC' type='text' id='txtCdC' size='39' maxlength='100' /></td></tr>");
		out.print("<tr></tr><tr><td width='15%'><div align='right'>Factura No:</div></td><td width='30%'><input name='txtFact' type='text' id='txtFact' size='39' maxlength='100' /></td>");
		out.print("<td width='15%'><div align='right'>Entidad:</div></td><td width='40%'><select  style='width:269px' name='Ent' id='Ent' ><option value='Seleccione'>Seleccione</option>");
		rs2=mm.Empresas();
		try {
			while(rs2.next()){
			out.print("<option title='"+rs2.getString(2)+"' value="+rs2.getString(1)+">"+rs2.getString(2)+"</option>");
			}
			rs2.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		out.print("</td></tr>");
					
		out.print("<tr><td><div align='right'>Tipo Doc:</div></td><td width='40%'><select  style='width:50px' name='tdoc' id='tdoc' >");
		rs2=mm.TipoDoc();
		try {
			while(rs2.next()){
			out.print("<option value="+rs2.getString(1)+">"+rs2.getString(2)+"</option>");
			}
			rs2.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		out.print("</select>");
		
		out.print(" Numero:<input name='txtDoc' type='text' id='txtDoc'  size='22' maxlength='50' /></td><td><div align='right'>Nombres:</div></td><td><input name='txtNom' type='text' id='txtNom' size='39' maxlength='100'/ ></td></tr>");
		out.print("<tr><td><div align='right'>Primer Apellido:</div></td><td><input name='txtPa' type='text' id='txtPa'  size='39' maxlength='50' /></td><td><div align='right'>Segundo Apellido:</div></td><td><input name='txtSa' type='text' id='txtSa'  size='39' maxlength='100'/ ></td></tr>");
		out.print("<tr><td><div align='right'>Fecha Inicial:</div></td><td><input name='FI' type='text' id='FI' size='10'  onKeyup='masca(this,patron,true,0,0,0)' onblur='vfi()'; /></td><td><div align='right'>Fecha Final:</div></td><td><input name='FF' type='text' id='FF' size='10' onKeyup='masca(this,patron,true,0,0,0)' onblur='vff()'; /></td></tr><tr></tr>");
		out.print("<tr><td><div align='right'>Numero de Relaci�n:</div></td><td><input name='CDC' type='text' id='CDC' size='15'   /></td><td></td><td></td></tr><tr></tr></table>");
		
		//out.print("<tr><td><div align='right'>Primer Apellido:</div></td><td><input name='txtCodigoPrestador' type='text' id='txtCodPrestador' size='39' maxlength='50' onkeydown='A(this, event)'/><span class='style8'>*</span></td><td><div align='right'>Segundo Apellido:</div></td><td><select name='listEstado' id='listEstado' style='width: 266px;' onkeydown='A(this, event)'><option value='Activo' selected='selected'>Activo</option><option value='Inactivo'>Inactivo</option></select><span class='style8'>*</span></td></tr></table>");
		out.print("<table width='100%' border='0' class='style6' ><tr><td colspan='4'><div align='center'><input name='btnCrearEntidad' type='button' id='btnCrearEntidad' value='     Consultar     ' onclick='BuscarCuentaDeRadicado(&quot;"+opcion+"&quot;,1)' /></div></td></tr><tr></tr><tr></tr></table>");
		out.print("<table width='100%' border='0' class='style6' ><tr></tr><tr BGCOLOR='#D3D3D3' ><td colspan='4' height='30' valign='top'><i><div align='center'>Resultados de Busqueda</div></i></td></tr><tr></tr></table>");
		out.print("<div id='resultadosf' style='height: 200px; overflow-y: scroll'></div>");
			
	}
	
	
	if(va.equals("cfri")){
		String opcion = req.getParameter("opcion");
		//System.out.println("La idea es  consultar las facturas");
		out.print("<table width='100%' border='0' align='center'><tr><td colspan='4' height='30' valign='top'><div align='center' class='style11' id='cabecera2'>Consulta de Cuentas Radicadas Internas</div></td></tr></table>");
		
		//out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='60%'><i><div align='center'>Paciente</div></i></td><td width='30%'><i><div align='center'>Empresa</div></i></td><td width='10%'><i><div align='center'>Admisi�n</div></i></td></tr>");  //
		out.print("<table width='100%' border=0' class='style6' ><tr BGCOLOR='#D3D3D3' ><td colspan='4' height='30' valign='top'><i><div align='center'>Criterios de Busqueda</div></i></td></tr>");
		//out.print("<tr></tr><tr><td width='15%'><div align='right'>Cuenta de Cobro No:</div></td><td width='30%'><input name='txtCdC' type='text' id='txtCdC' size='39' maxlength='100' /></td></tr>");
		out.print("<tr></tr><tr><td width='15%'><div align='right'>Factura No:</div></td><td width='30%'><input name='txtFact' type='text' id='txtFact' size='39' maxlength='100' /></td>");
		out.print("<td width='15%'><div align='right'>Entidad:</div></td><td width='40%'><select  style='width:269px' name='Ent' id='Ent' ><option value='Seleccione'>Seleccione</option>");
		rs2=mm.Empresas();
		try {
			while(rs2.next()){
			out.print("<option title='"+rs2.getString(2)+"' value="+rs2.getString(1)+">"+rs2.getString(2)+"</option>");
			}
			rs2.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		out.print("</td></tr>");
					
		out.print("<tr><td><div align='right'>Tipo Doc:</div></td><td width='40%'><select  style='width:50px' name='tdoc' id='tdoc' >");
		rs2=mm.TipoDoc();
		try {
			while(rs2.next()){
			out.print("<option value="+rs2.getString(1)+">"+rs2.getString(2)+"</option>");
			}
			rs2.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		out.print("</select>");
		
		out.print(" Numero:<input name='txtDoc' type='text' id='txtDoc'  size='22' maxlength='50' /></td><td><div align='right'>Nombres:</div></td><td><input name='txtNom' type='text' id='txtNom' size='39' maxlength='100'/ ></td></tr>");
		out.print("<tr><td><div align='right'>Primer Apellido:</div></td><td><input name='txtPa' type='text' id='txtPa'  size='39' maxlength='50' /></td><td><div align='right'>Segundo Apellido:</div></td><td><input name='txtSa' type='text' id='txtSa'  size='39' maxlength='100'/ ></td></tr>");
		out.print("<tr><td><div align='right'>Fecha Inicial:</div></td><td><input name='FI' type='text' id='FI' size='10'  onKeyup='masca(this,patron,true,0,0,0)' onblur='vfi()'; /></td><td><div align='right'>Fecha Final:</div></td><td><input name='FF' type='text' id='FF' size='10' onKeyup='masca(this,patron,true,0,0,0)' onblur='vff()'; /></td></tr><tr></tr>");
		out.print("<tr><td><div align='right'>Numero de Radicaci�n Interno:</div></td><td><input name='CDC' type='text' id='CDC' size='15'   /></td><td></td><td></td></tr><tr></tr></table>");
		
		//out.print("<tr><td><div align='right'>Primer Apellido:</div></td><td><input name='txtCodigoPrestador' type='text' id='txtCodPrestador' size='39' maxlength='50' onkeydown='A(this, event)'/><span class='style8'>*</span></td><td><div align='right'>Segundo Apellido:</div></td><td><select name='listEstado' id='listEstado' style='width: 266px;' onkeydown='A(this, event)'><option value='Activo' selected='selected'>Activo</option><option value='Inactivo'>Inactivo</option></select><span class='style8'>*</span></td></tr></table>");
		out.print("<table width='100%' border='0' class='style6' ><tr><td colspan='4'><div align='center'><input name='btnCrearEntidad' type='button' id='btnCrearEntidad' value='     Consultar     ' onclick='BuscarCuentaDeRadicado(&quot;"+opcion+"&quot;,2)' /></div></td></tr><tr></tr><tr></tr></table>");
		out.print("<table width='100%' border='0' class='style6' ><tr></tr><tr BGCOLOR='#D3D3D3' ><td colspan='4' height='30' valign='top'><i><div align='center'>Resultados de Busqueda</div></i></td></tr><tr></tr></table>");
		out.print("<div id='resultadosf' style='height: 200px; overflow-y: scroll'></div>");
			
	}
	
	


	if(va.equals("showFact")){	
		
		String sql="";
		String ea=req.getParameter("ea");
		System.out.println("valor de ea "+ea);
		String v1=req.getParameter("v1");
		if((v1==null)||(v1.equals(""))){v1="";}else{sql=sql+" AND fn.consecutivo='"+v1+"'";}
		String v2=req.getParameter("v2");
		if(v2.equals("Seleccione")){v2="";}else{sql=sql+" AND ae.ent_nit="+v2;}
		String v3=req.getParameter("v3");
		if(v3==null){v3="";}
		String v4=req.getParameter("v4");
		if((v4==null)||(v4.equals(""))){v4="";}else{sql=sql+" AND p.tipo_documento='"+v3+"' AND p.numero_documento="+v4;}
		String v5=req.getParameter("v5");
		if((v5==null)||(v5.equals(""))){v5="";}else{sql=sql+" AND p.nombre like '%"+v5+"%'";}
		String v6=req.getParameter("v6");
		if((v6==null)||(v6.equals(""))){v6="";}else{sql=sql+" AND p.primer_apellido='"+v6+"'";}
		String v7=req.getParameter("v7");
		if((v7==null)||(v7.equals(""))){v7="";}else{sql=sql+" AND p.segundo_apellido='"+v7+"'";}
		String v8=req.getParameter("v8");
		String v9=req.getParameter("v9");
		
		if((v8==null)||(v8.equals(""))||(v9.equals(""))||(v9==null)){v8="";v9="";}
		else{
		String dv8=v8.substring(0, 2);
		String mv8=v8.substring(3, 5);
		String av8=v8.substring(6, 10);
		String fv8=av8+"-"+mv8+"-"+dv8;
		
		String dv9=v9.substring(0, 2);
		String mv9=v9.substring(3, 5);
		String av9=v9.substring(6, 10);
		String fv9=av9+"-"+mv9+"-"+dv9;
		
		sql=sql+" AND fn.fecha BETWEEN '"+fv8+"' AND '"+fv9+"' ";}
		
		int crs=0;		
		rs2=mm.GeneraSQLSelFact(sql,ea);
		try {
			while(rs2.next()){
				if(crs==0){
					out.print("<table id='listFact' width='100%' border='0' class='style6'  style='font-size: 13px'>" +
							  	"<tr BGCOLOR='#D3D3D3'  style='font-weight: bold'>" +
							  		"<td width='5%' align='center'><i>Item</i></td>" +
							  		"<td width='11%' align='center'><i>No Factura</i></td>" +
							  		"<td width='24%' align='center'><i>Nombres y Apellidos</i></td>" +
							  		"<td width='11%' align='center'><i>Identificacion</i></td>" +
							  		"<td width='8%' align='center'><i>F.Ingreso</i></td>" +
							  		"<td width='8%' align='center'><i>F.Egreso</i></td>" +
							  		"<td width='10%' align='center'><i>Valor</i></td>" +
							  		"<td width='8%' align='center'><i>Valor Nota credito</i></td>" +
							  		"<td width='10%' align='center'><i>Valor Total Factura</i></td>" +
							  		"<td width='10%' align='center'><i>Municipio</i></td>" +
							  		"<td width='12%' align='center'><i>Entidad</i></td>" +
							  		"<td width='1%' align='center'></td>" +
							  	"</tr>");
				}
				if(rs2.getString(10).equals("0")){
					//String conce=rs2.getString(2);
					/*System.out.println("consecutivo "+conce);
					rs3=mm.BuscarNotasCredito2(conce);*/
					long NOTAC=0;
					/*while(rs3.next()){
						NOTAC=rs3.getLong(1);
					}rs3.getStatement().getConnection().close();*/
					NOTAC=rs2.getLong(11);
					long vf=rs2.getLong(7);
					long totalfact=(vf-NOTAC);
					String totalf=Long.toString(totalfact);
					System.out.println("valor de ea"+ea+" valor notac "+NOTAC);
					//if(totalfact!=0){
					out.print("<tr>" +
					  	"<td width='5%' align='center'>"+(crs+1)+"</td>" +
					  	"<td width='11%' align='left'>"+rs2.getString(2)+"</td>" +
					  	"<td width='24%' align='left'>"+rs2.getString(3)+"</td>" +
					  	"<td width='11%' align='left'>"+rs2.getString(4)+"</td>" +
					  	"<td width='8%' align='left'>"+rs2.getString(5)+"</td>" +
					  	"<td width='8%' align='left'>"+rs2.getString(6)+"</td>" +
					  	"<td width='10%' align='left' >"+rs2.getString(7)+"</td>" +
						"<td width='8%' align='center' id='NC"+crs+"'>"+NOTAC+"</td>" +
						"<td width='10%' align='left' id='vEF"+crs+"'>"+totalf+"</td>" +
					  	"<td width='10%' align='center'>"+rs2.getString(9)+"</td>" +
					  	"<td width='12%' align='center' id='ent"+crs+"'>"+rs2.getString(8)+"</td>" );
					  	if(totalfact!=0){
					  	out.print("<td width='1%' align='center'><input id='chkEF"+crs+"' type='checkbox' value='"+rs2.getString(1)+"'/></td>");
					  	}else{
					  		out.print("<td width='1%' align='center'><input id='chkEF"+crs+"' type='checkbox' value='"+rs2.getString(1)+"' disabled='true'/></td>");
					  	}
					  out.print("</tr>");
					
				/*	}else{
						System.out.println("factura en cero"+rs2.getString(2));
					}*/
				}
				if(ea.equals("0")){
				if(rs2.getString(10).equals("8")){
					//String conce=rs2.getString(2);
					/*rs3=mm.BuscarNotasCredito2(conce);
					System.out.println("consecutivoo "+conce);*/
					long NOTAC=0;
					/*while(rs3.next()){
						NOTAC=rs3.getLong(1);
					}rs3.getStatement().getConnection().close();*/
					NOTAC=rs2.getLong(11);
					System.out.println("valor notac 8 "+NOTAC);
					long vf=rs2.getLong(7);
					long totalfact=(vf-NOTAC);
					String totalf=Long.toString(totalfact);
					//if(totalfact!=0){
					out.print("<tr>" +
					  	"<td width='5%' align='center'>"+(crs+1)+"</td>" + 
					  	"<td width='11%' align='left' ><FONT COLOR='#FF3333'>"+rs2.getString(2)+"</td>" +
					  	"<td width='24%' align='left'>"+rs2.getString(3)+"</td>" +
					  	"<td width='11%' align='left'>"+rs2.getString(4)+"</td>" +
					  	"<td width='8%' align='left'>"+rs2.getString(5)+"</td>" +
					  	"<td width='8%' align='left'>"+rs2.getString(6)+"</td>" +
					  	"<td width='10%' align='left' >"+rs2.getString(7)+"</td>" +
						"<td width='8%' align='center' id='NC"+crs+"'>"+NOTAC+"</td>" +
						"<td width='10%' align='left' id='vEF"+crs+"'>"+totalf+"</td>" +
					  	"<td width='10%' align='center'>"+rs2.getString(9)+"</td>" +
					  	"<td width='12%' align='center' id='ent"+crs+"'>"+rs2.getString(8)+"</td>");
					if(totalfact!=0){
					  	out.print("<td width='1%' align='center'><input id='chkEF"+crs+"' type='checkbox' value='"+rs2.getString(1)+"'/></td>" );
					}else{
						out.print("<td width='1%' align='center'><input id='chkEF"+crs+"' type='checkbox' value='"+rs2.getString(1)+"' disabled='true'/></td>");
					}
					 out.print("</tr>");
					//}
				/*	}else{
						System.out.println("factura en cero"+rs2.getString(2));
					}*/
				}
				}
				
			crs++;
			}
			out.print("</table>");
			rs2.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		if(crs==0){
			out.print("No hay resultados para sus criterios de busqueda.");
		}
	}
	
	
if(va.equals("showFactR")){	
		
		String ri=req.getParameter("ri");
		System.out.println("Entramos a seleccionar facturasRRR: "+ri);
		String sql="";
		String v1=req.getParameter("v1");
		if((v1==null)||(v1.equals(""))){v1="";}else{sql=sql+" AND fn.consecutivo='"+v1+"'";}
		String v2=req.getParameter("v2");
		if(v2.equals("Seleccione")){v2="";}else{sql=sql+" AND ae.ent_nit="+v2;}
		String v3=req.getParameter("v3");
		if(v3==null){v3="";}
		String v4=req.getParameter("v4");
		if((v4==null)||(v4.equals(""))){v4="";}else{sql=sql+" AND p.tipo_documento='"+v3+"' AND p.numero_documento="+v4;}
		String v5=req.getParameter("v5");
		if((v5==null)||(v5.equals(""))){v5="";}else{sql=sql+" AND p.nombre like '%"+v5+"%'";}
		String v6=req.getParameter("v6");
		if((v6==null)||(v6.equals(""))){v6="";}else{sql=sql+" AND p.primer_apellido='"+v6+"'";}
		String v7=req.getParameter("v7");
		if((v7==null)||(v7.equals(""))){v7="";}else{sql=sql+" AND p.segundo_apellido='"+v7+"'";}
		String v10=req.getParameter("v10");
		if(ri.equals("0")){
			System.out.println("v10 "+v10);
		if((v10==null)||(v10.equals(""))){v10="";}else{sql=sql+" AND fe.consEnvio='"+v10+"' ";}
		
		}
		if(ri.equals("1")){
		if((v10==null)||(v10.equals(""))){v10="";}else{sql=sql+" AND fe.consAudita='"+v10+"'";}
		}
		
		
		String v8=req.getParameter("v8");
		String v9=req.getParameter("v9");
		if((v8==null)||(v8.equals(""))||(v9.equals(""))||(v9==null)){v8="";v9="";}
		else{
		String dv8=v8.substring(0, 2);
		String mv8=v8.substring(3, 5);
		String av8=v8.substring(6, 10);
		String fv8=av8+"-"+mv8+"-"+dv8;
		
		String dv9=v9.substring(0, 2);
		String mv9=v9.substring(3, 5);
		String av9=v9.substring(6, 10);
		String fv9=av9+"-"+mv9+"-"+dv9;
		
		sql=sql+" AND fn.fecha BETWEEN '"+fv8+"' AND '"+fv9+"' ";}
		
		int crs=0;		
		rs2=mm.GeneraSQLSelFactR(sql,ri);
		try {
			while(rs2.next()){
				if(crs==0){
					out.print("<table id='listFact' width='100%' border='0' class='style6'  style='font-size: 13px'>" +
							  	"<tr BGCOLOR='#D3D3D3'  style='font-weight: bold'>" +
							  		"<td width='3%' align='center'><i>Item</i></td>" +
							  		"<td width='9%' align='center'><i>No Factura</i></td>" +
							  		"<td width='24%' align='center'><i>Nombres y Apellidos</i></td>" +
							  		"<td width='9%' align='center'><i>Identificacion</i></td>" +
							  		"<td width='8%' align='center'><i>F.Ingreso</i></td>" +
							  		"<td width='8%' align='center'><i>F.Egreso</i></td>" +
							  		"<td width='10%' align='center'><i>Valor</i></td>" +
							  		"<td width='8%' align='center'><i>Valor Nota credito</i></td>" +
							  		"<td width='10%' align='center'><i>Valor Total Factura</i></td>" +
							  		"<td width='10%' align='center'><i>Cta. de Cobro</i></td>" +
							  		"<td width='15%' align='center'><i>Entidad</i></td>" +
							  		"<td width='8%' align='center'><i>Fecha Radicado </i><input type='text' id='fradt'  size='7' onKeyup='masca(this,patron,true,0,0,0,event)' onBlur='repitefecha()'/></td>" +
							  		"<td width='1%' align='center'><input type='checkbox' id='radicatodo' onclick='todoscheck()' /></td>" +
							  	"</tr>");
				}
				if(ri.equals("0")){

					String av8=rs2.getString(11).substring(0, 4);
					String mv8=rs2.getString(11).substring(5, 7);
					String dv8=rs2.getString(11).substring(8, 10);
					String fnf=dv8+"/"+mv8+"/"+av8;
					//String conce=rs2.getString(2);
				//	rs3=mm.BuscarNotasCredito2(conce);
					long NOTAC=0;
					/*while(rs3.next()){
						NOTAC=rs3.getLong(1);
					}rs3.getStatement().getConnection().close();*/
					NOTAC=rs2.getLong(12);
					System.out.println("valor nota en radicado"+NOTAC);
					long vf=rs2.getLong(7);
					long totalfact=(vf-NOTAC);
					String totalf=Long.toString(totalfact);
					
					out.print("<tr>" +
					  	"<td width='3%' align='center'>"+(crs+1)+"</td>" +
					  	"<td width='9%' align='left'>"+rs2.getString(2)+"</td>" +
					  	"<td width='24%' align='left'>"+rs2.getString(3)+"</td>" +
					  	"<td width='9%' align='left'>"+rs2.getString(4)+"</td>" +
					  	"<td width='8%' align='left'>"+rs2.getString(5)+"</td>" +
					  	"<td width='8%' align='left'>"+rs2.getString(6)+"</td>" +
					  	"<td width='10%' align='left' >"+rs2.getString(7)+"</td>" +
					  	"<td width='8%' align='center' id='NC"+crs+"'>"+NOTAC+"</td>" +
						"<td width='10%' align='left' id='vEF"+crs+"'>"+totalf+"</td>" +
					  	"<td width='10%' align='center'>"+rs2.getString(9)+"</td>" +
					  	"<td width='15%' align='center' id='ent"+crs+"'>"+rs2.getString(8)+"</td>" +
					  	"<td width='8%' align='center' ><input name='frad' type='text' id='frad"+crs+"'  size='8' onKeyup='masca(this,patron,true,0,0,0)' /><input type='hidden' id='ffr"+crs+"' value='"+fnf+"' /></td>");
						if(totalfact!=0){
					  	out.print("<td width='1%' align='center'><input name='radiorad' id='chkEF"+crs+"' type='checkbox' value='"+rs2.getString(1)+"'/></td>");
						}else{
							out.print("<td width='1%' align='center'><input name='radiorad' id='chkEF"+crs+"' type='checkbox' value='"+rs2.getString(1)+"' disabled='true' /></td>");
						}
						out.print("</tr>");
						
					
				}
			
				if(ri.equals("1")){

					String av8=rs2.getString(11).substring(0, 4);
					String mv8=rs2.getString(11).substring(5, 7);
					String dv8=rs2.getString(11).substring(8, 10);
					String fnf=dv8+"/"+mv8+"/"+av8;
								
					String ddv8=fechacjmysql.substring(0, 4);
					String dmv8=fechacjmysql.substring(5, 7);
					String dav8=fechacjmysql.substring(8, 10);
					String dfv8=dav8+"/"+dmv8+"/"+ddv8;

					//String conce=rs2.getString(2);
					//rs3=mm.BuscarNotasCredito2(conce);
					long NOTAC=0;
					/*while(rs3.next()){
						NOTAC=rs3.getLong(1);
					}rs3.getStatement().getConnection().close();*/
					NOTAC=rs2.getLong(12);
					System.out.println("valor nota en radicado"+NOTAC);
					long vf=rs2.getLong(7);
					long totalfact=(vf-NOTAC);
					String totalf=Long.toString(totalfact);
				
					out.print("<tr>" +
							  	"<td width='3%' align='center'>"+(crs+1)+"</td>" +
							  	"<td width='9%' align='left'>"+rs2.getString(2)+"</td>" +
							  	"<td width='24%' align='left'>"+rs2.getString(3)+"</td>" +
							  	"<td width='9%' align='left'>"+rs2.getString(4)+"</td>" +
							  	"<td width='8%' align='left'>"+rs2.getString(5)+"</td>" +
							  	"<td width='8%' align='left'>"+rs2.getString(6)+"</td>" +
							  	"<td width='10%' align='left' >"+rs2.getString(7)+"</td>" +
							  	"<td width='8%' align='center' id='NC"+crs+"'>"+NOTAC+"</td>" +
								"<td width='10%' align='left' id='vEF"+crs+"'>"+totalf+"</td>" +
							  	"<td width='10%' align='center'>"+rs2.getString(9)+"</td>" +
							  	"<td width='15%' align='center' id='ent"+crs+"'>"+rs2.getString(8)+"</td>" +
							  	"<td width='8%' align='center' ><input name='frad' type='text' id='frad"+crs+"'  size='8' onKeyup='masca(this,patron,true,0,0,0)' value='"+dfv8+"' /><input type='hidden' id='ffr"+crs+"' value='"+fnf+"' /></td>");
							 	if(totalfact!=0){  	
							  	out.print("<td width='1%' align='center'><input id='chkEF"+crs+"' type='checkbox' value='"+rs2.getString(1)+"'/></td>");
							 	}else{
							 	out.print("<td width='1%' align='center'><input id='chkEF"+crs+"' type='checkbox' value='"+rs2.getString(1)+"' disabled='true' /></td>");
							 	}
							 	out.print("</tr>");
					
						
				}
			crs++;
			}
			out.print("</table>");
			rs2.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		if(crs==0){
			out.print("No hay resultados para sus criterios de busqueda.");
		}
	}

	
	
	if(va.equals("envFact")){
		String ea=req.getParameter("ea");
		String FactParaEnviar=req.getParameter("facturas");
		String totalFacturas=req.getParameter("vFacturas");
		String totalLetrasFacturas=req.getParameter("vlFacturas");
		String usuario=req.getParameter("usuario");
		String valornc=req.getParameter("NC");
		
		Calendar calendario = Calendar.getInstance();
		int dia0, mes0, anio0;
		dia0 =calendario.get(Calendar.DAY_OF_MONTH);
		mes0 = calendario.get(Calendar.MONTH)+1;
		anio0 = calendario.get(Calendar.YEAR);
		String fecha= anio0+"-"+mes0+"-"+dia0;
		
		if(ea.equals("0")){
		mm.asignarCuentaCobroFact(totalFacturas, totalLetrasFacturas, usuario, fecha,ea);
		
		rs=mm.obtenerConsecutivoCC(ea);
		String consecutivoCC="";
		try {
			while(rs.next()){
				consecutivoCC=rs.getString(1);
			}
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		out.print(consecutivoCC);
		String[] detFactParaEnviar = FactParaEnviar.split("_");
		String[] detvalornc=valornc.split("_");
		for (int i=0;i<detFactParaEnviar.length;i++){
			mm.asignarCCAFact(consecutivoCC, detFactParaEnviar[i],detvalornc[i]);
			mm.actualizarEstFact(detFactParaEnviar[i]);
			mm.recordEstadoFact(detFactParaEnviar[i], "1", fecha,usuario);
		}
		}//fin ea=0
		
		if(ea.equals("1")){
			mm.asignarCuentaCobroFact(totalFacturas, totalLetrasFacturas, usuario, fecha,ea);
			
			rs=mm.obtenerConsecutivoCC(ea);
			String consecutivoCC="";
			try {
				while(rs.next()){
					consecutivoCC=rs.getString(1);
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print(consecutivoCC);
			String[] detFactParaEnviar = FactParaEnviar.split("_");
			String[] detvalornc=valornc.split("_");
			for (int i=0;i<detFactParaEnviar.length;i++){
				mm.asignarCRAFact(consecutivoCC, detFactParaEnviar[i],fechacjmysql,"1",detvalornc[i]);
				mm.actualizarEstFactR(detFactParaEnviar[i],"1");
				mm.recordEstadoFact(detFactParaEnviar[i], "6", fecha,usuario);
			}
			}//fin ea=1
		
	}
	
	
	if(va.equals("radFact")){
		String frad=req.getParameter("frad");
		String ri=req.getParameter("ri");
		//System.out.print(frad+" nueva "+fradn);
		
		String FactParaEnviar=req.getParameter("facturas");
		String totalFacturas=req.getParameter("vFacturas");
		String totalLetrasFacturas=req.getParameter("vlFacturas");
		String usuario=req.getParameter("usuario");
		String valornc=req.getParameter("NC");
		
		Calendar calendario = Calendar.getInstance();
		int dia0, mes0, anio0;
		dia0 =calendario.get(Calendar.DAY_OF_MONTH);
		mes0 = calendario.get(Calendar.MONTH)+1;
		anio0 = calendario.get(Calendar.YEAR);
		String fecha= anio0+"-"+mes0+"-"+dia0;
		
		mm.asignarCuentaRad(totalFacturas, totalLetrasFacturas, usuario, fecha, ri);
		
		rs=mm.obtenerConsecutivoCR(ri);
		String consecutivoCC="";
		try {
			while(rs.next()){
				consecutivoCC=rs.getString(1);
			}
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		out.print(consecutivoCC);
		String[] detFactParaEnviar = FactParaEnviar.split("_");
		String[] Vfrad = frad.split("_");
		String detvalornc[]=valornc.split("_");
		for (int i=0;i<detFactParaEnviar.length;i++){
			
			String dv8=Vfrad[i].substring(0, 2);
			String mv8=Vfrad[i].substring(3, 5);
			String av8=Vfrad[i].substring(6, 10);
			String fradn=av8+"-"+mv8+"-"+dv8;
			if(ri.equals("0")){	
			mm.asignarCRAFact(consecutivoCC, detFactParaEnviar[i],fradn,"0",detvalornc[i]);
			mm.actualizarEstFactR(detFactParaEnviar[i],"0");
			mm.recordEstadoFact(detFactParaEnviar[i], "2", fecha,usuario);
			
			rs=mm.obtenerConsecutivoFactNum(detFactParaEnviar[i]);
			String consefact="";
			try {
				if(rs.next()){
					consefact=rs.getString(1);
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			mm.actualizarEstFactRadicadaenContabilidad(consefact);
			mm.actualizarFechaRadicacionCarteraPlazo(consefact, fradn);
			
			
			}
			if(ri.equals("1")){	
			mm.asignarCRAFact(consecutivoCC, detFactParaEnviar[i],fradn,"2",detvalornc[i]);
			mm.actualizarEstFactR(detFactParaEnviar[i],"2");
			mm.recordEstadoFact(detFactParaEnviar[i], "8", fecha,usuario);
			}
			
		}
		
	}
	
	
	
	if(va.equals("showCC")){	
		String opcion = req.getParameter("opcion");
		System.out.println(opcion);
		String sql="";
		String v11=req.getParameter("v11");
		if((v11==null)||(v11.equals(""))){v11="";}else{sql=sql+" AND ffe.consEnvio='"+v11.substring(6,v11.length())+"'";}
		
		String v1=req.getParameter("v1");
		if((v1==null)||(v1.equals(""))){v1="";}else{sql=sql+" AND fn.consecutivo='"+v1+"'";}
		String v2=req.getParameter("v2");
		if(v2.equals("Seleccione")){v2="";}else{sql=sql+" AND ae.ent_nit="+v2;}
		String v3=req.getParameter("v3");
		if(v3==null){v3="";}
		String v4=req.getParameter("v4");
		if((v4==null)||(v4.equals(""))){v4="";}else{sql=sql+" AND ap.tipo_documento='"+v3+"' AND ap.numero_documento="+v4;}
		String v5=req.getParameter("v5");
		if((v5==null)||(v5.equals(""))){v5="";}else{sql=sql+" AND ap.nombre LIKE '%"+v5+"%'";}
		String v6=req.getParameter("v6");
		if((v6==null)||(v6.equals(""))){v6="";}else{sql=sql+" AND ap.primer_apellido='"+v6+"'";}
		String v7=req.getParameter("v7");
		if((v7==null)||(v7.equals(""))){v7="";}else{sql=sql+" AND ap.segundo_apellido='"+v7+"'";}
		String v8=req.getParameter("v8");
		String v9=req.getParameter("v9");
		
		if((v8==null)||(v8.equals(""))||(v9.equals(""))||(v9==null)){v8="";v9="";}
		else{
		String dv8=v8.substring(0, 2);
		String mv8=v8.substring(3, 5);
		String av8=v8.substring(6, 10);
		String fv8=av8+"-"+mv8+"-"+dv8;
		
		String dv9=v9.substring(0, 2);
		String mv9=v9.substring(3, 5);
		String av9=v9.substring(6, 10);
		String fv9=av9+"-"+mv9+"-"+dv9;
		
		sql=sql+" AND ffe.fecha BETWEEN '"+fv8+"' AND '"+fv9+"' ";}
		 
		
		int crs=0;		
		rs2=mm.GeneraSQLCtaFact(sql);
		try {
			while(rs2.next()){
				if(crs==0){
					out.print("<table id='listFact' width='100%' border='0' class='style6'  style='font-size: 13px'>" +
							  	"<tr BGCOLOR='#D3D3D3'  style='font-weight: bold'>"+
							  		"<td width='3%' align='center'><i>Item</i></td>"+
							  		"<td width='12%' align='center'><i>No Cuenta de Cobro</i></td>"+
							  		"<td width='10%' align='center'><i>Valor CTA</i></td>"+
							  		"<td width='12%' align='center'><i>Fecha de envio</i></td>"+
							  		"<td width='37%' align='center'><i>Entidad</i></td>"+
							  		"<td width='26%' align='center'><i>Usuario</i></td>"+
							  	"</tr>");
				}
				
			out.print("<tr> " +
						  "<td width='3%' align='center'>"+(crs+1)+"</td>" +
						  "<td width='12%' align='left'><a href='#' onclick='mostrarDetCTA(&quot;"+rs2.getString(6)+"&quot;,&quot;"+opcion+"&quot;)'>"+rs2.getString(1)+"</a></td>" +
						  "<td width='10%' align='right'>"+mm.formatMoneda(rs2.getString(2))+"</td>" +
						  "<td width='12%' align='center'>"+rs2.getString(3)+"</td>" +
						  "<td width='37%' align='left'>"+rs2.getString(4)+"</td>" +
						  "<td width='26%' align='left'>"+rs2.getString(5)+"</td>" + 
					  "</tr>");
			crs++;
			
			}
			out.print("</table>");

			rs2.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		if(crs==0){
			out.print("No hay resultados para sus criterios de busqueda.");
		}
	}
	
	
	
	if(va.equals("showCR")){	
		String opcion = req.getParameter("opcion");
		String ra = req.getParameter("ra");
		System.out.println(opcion);
		String sql="";
		//String v11=req.getParameter("v11");
		//if((v11==null)||(v11.equals(""))){v11="";}else{sql=sql+" AND ffe.consEnvio='"+v11.substring(6,v11.length())+"'";}
		
		String v1=req.getParameter("v1");
		if((v1==null)||(v1.equals(""))){v1="";}else{sql=sql+" AND fn.consecutivo='"+v1+"'";}
		String v2=req.getParameter("v2");
		if(v2.equals("Seleccione")){v2="";}else{sql=sql+" AND ae.ent_nit="+v2;}
		String v3=req.getParameter("v3");
		if(v3==null){v3="";}
		String v4=req.getParameter("v4");
		if((v4==null)||(v4.equals(""))){v4="";}else{sql=sql+" AND ap.tipo_documento='"+v3+"' AND ap.numero_documento="+v4;}
		String v5=req.getParameter("v5");
		if((v5==null)||(v5.equals(""))){v5="";}else{sql=sql+" AND ap.nombre LIKE '%"+v5+"%'";}
		String v6=req.getParameter("v6");
		if((v6==null)||(v6.equals(""))){v6="";}else{sql=sql+" AND ap.primer_apellido='"+v6+"'";}
		String v7=req.getParameter("v7");
		if((v7==null)||(v7.equals(""))){v7="";}else{sql=sql+" AND ap.segundo_apellido='"+v7+"'";}
		String v10=req.getParameter("v10");
		if(ra.equals("0")){
		if((v10==null)||(v10.equals(""))){v10="";}else{sql=sql+" AND ffe.consRadicado='"+v10+"'";}
		}
		if(ra.equals("1")){
			if((v10==null)||(v10.equals(""))){v10="";}else{sql=sql+" AND ffe.consAudita='"+v10+"'";}
		}
		if(ra.equals("2")){
			if((v10==null)||(v10.equals(""))){v10="";}else{sql=sql+" AND ffe.consRadicado='"+v10+"'";}
		}
		
		String v8=req.getParameter("v8");
		String v9=req.getParameter("v9");
		
		if((v8==null)||(v8.equals(""))||(v9.equals(""))||(v9==null)){v8="";v9="";}
		else{
		String dv8=v8.substring(0, 2);
		String mv8=v8.substring(3, 5);
		String av8=v8.substring(6, 10);
		String fv8=av8+"-"+mv8+"-"+dv8;
		
		String dv9=v9.substring(0, 2);
		String mv9=v9.substring(3, 5);
		String av9=v9.substring(6, 10);
		String fv9=av9+"-"+mv9+"-"+dv9;
		
		sql=sql+" AND ffe.fecha BETWEEN '"+fv8+"' AND '"+fv9+"' ";}
		 
		
		int crs=0;		
		rs2=mm.GeneraSQLCtaFactR(sql,ra);
		try {
			while(rs2.next()){
				if(ra.equals("0")){
				if(crs==0){
					out.print("<table id='listFact' width='100%' border='0' class='style6'  style='font-size: 13px'>" +
							  	"<tr BGCOLOR='#D3D3D3'  style='font-weight: bold'>"+
							  		"<td width='3%' align='center'><i>Item</i></td>"+
							  		"<td width='12%' align='center'><i>No Radicacion</i></td>"+
							  		"<td width='10%' align='center'><i>Valor CTA</i></td>"+
							  		"<td width='12%' align='center'><i>Fecha de envio</i></td>"+
							  		"<td width='37%' align='center'><i>Entidad</i></td>"+
							  		"<td width='26%' align='center'><i>Usuario</i></td>"+
							  	"</tr>");
				}
				
			out.print("<tr> " +
						  "<td width='3%' align='center'>"+(crs+1)+"</td>" +
						  "<td width='12%' align='left'><a href='#' onclick='mostrarDetRAD(&quot;"+rs2.getString(6)+"&quot;,&quot;"+opcion+"&quot;)'>"+rs2.getString(1)+"</a></td>" +
						  "<td width='10%' align='right'>"+mm.formatMoneda(rs2.getString(2))+"</td>" +
						  "<td width='12%' align='center'>"+rs2.getString(3)+"</td>" +
						  "<td width='37%' align='left'>"+rs2.getString(4)+"</td>" +
						  "<td width='26%' align='left'>"+rs2.getString(5)+"</td>" + 
					  "</tr>");
			crs++;
			
			}
				if(ra.equals("1")){
					if(crs==0){
						out.print("<table id='listFact'  width='100%' border='0' class='style6'  style='font-size: 13px'>" +
								  	"<tr BGCOLOR='#D3D3D3'  style='font-weight: bold'>"+
								  		"<td width='3%' align='center'><i>Item</i></td>"+
								  		"<td width='12%' align='center'><i>No Relacion</i></td>"+
								  		"<td width='10%' align='center'><i>Valor CTA</i></td>"+
								  		"<td width='12%' align='center'><i>Fecha de envio</i></td>"+
								  		"<td width='37%' align='center'><i>Entidad</i></td>"+
								  		"<td width='26%' align='center'><i>Usuario</i></td>"+
								  	"</tr>");
					}
					
				out.print("<tr> " +
							  "<td width='3%' align='center'>"+(crs+1)+"</td>" +
							  "<td width='12%' align='left'><a href='#' onclick='mostrarDetCTAA(&quot;"+rs2.getString(6)+"&quot;,&quot;"+opcion+"&quot;)'>"+rs2.getString(1)+"</a></td>" +
							  "<td width='10%' align='right'>"+mm.formatMoneda(rs2.getString(2))+"</td>" +
							  "<td width='12%' align='center'>"+rs2.getString(3)+"</td>" +
							  "<td width='37%' align='left'>"+rs2.getString(4)+"</td>" +
							  "<td width='26%' align='left'>"+rs2.getString(5)+"</td>" + 
						  "</tr>");
				crs++;
				
				}
				
				if(ra.equals("2")){
					if(crs==0){
						out.print("<table id='listFact'  width='100%' border='0' class='style6'  style='font-size: 13px'>" +
								  	"<tr BGCOLOR='#D3D3D3'  style='font-weight: bold'>"+
								  		"<td width='3%' align='center'><i>Item</i></td>"+
								  		"<td width='12%' align='center'><i>No Relacion</i></td>"+
								  		"<td width='10%' align='center'><i>Valor CTA</i></td>"+
								  		"<td width='12%' align='center'><i>Fecha de envio</i></td>"+
								  		"<td width='37%' align='center'><i>Entidad</i></td>"+
								  		"<td width='26%' align='center'><i>Usuario</i></td>"+
								  	"</tr>");
					}
					
				out.print("<tr> " +
							  "<td width='3%' align='center'>"+(crs+1)+"</td>" +
							  "<td width='12%' align='left'><a href='#' onclick='mostrarDetRADI(&quot;"+rs2.getString(6)+"&quot;,&quot;"+opcion+"&quot;)'>"+rs2.getString(1)+"</a></td>" +
							  "<td width='10%' align='right'>"+mm.formatMoneda(rs2.getString(2))+"</td>" +
							  "<td width='12%' align='center'>"+rs2.getString(3)+"</td>" +
							  "<td width='37%' align='left'>"+rs2.getString(4)+"</td>" +
							  "<td width='26%' align='left'>"+rs2.getString(5)+"</td>" + 
						  "</tr>");
				crs++;
				
				}
				
				
			}
			out.print("</table>");

			rs2.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		if(crs==0){
			out.print("No hay resultados para sus criterios de busqueda.");
		}
	}
	
	
	
	if(va.equals("detCTA")){	
		String opcion = req.getParameter("opcion");
		String codCTA = req.getParameter("consCTA");
		
		out.print("<table  width='100%'>");
		out.print("<tr BGCOLOR='#D3D3D3'  style='font-weight: bold'>");
		out.print("<td  align='CENTER' colspan='3'>INFORMACION CUENTA DE COBRO</td>");
		out.print("</tr>");
		out.print("<tr></tr>");
		out.print("<tr></tr>");
		
		rs = mm.datCTA(codCTA);
		try {
			while(rs.next()){
				out.print("<tr><td><b>No. de cuenta:  </b>"+rs.getString(1)+"</td></tr>");
				out.print("<tr><td><br /></td></tr>");
				out.print("<tr><td><b>Valor CTA:  </b>"+mm.formatMoneda(rs.getString(2))+"</td><td><b>Fecha de envio:  </b>"+rs.getString(3)+" </td><td><b>Usuario:  </b>"+rs.getString(4)+" </td></tr>");
			}
				
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		
		out.print("</table>");
		out.print("<br />");

		out.print("<table  width='100%'>");
		out.print("<tr BGCOLOR='#D3D3D3'  style='font-weight: bold'>");
		out.print("<td  align='CENTER' colspan='10'>CONSOLIDADO DE FACTURAS</td>");
		out.print("</tr>");
		out.print("<tr></tr><tr></tr>");
		
		out.print("</table>" +
				  "<div  style='overflow-y: scroll; height: 280px;'>" +
				  "<table width='100%'>");
		out.print("<tr BGCOLOR='#D3D3D3' style='font-weight: bold'>" +
				  "<td width='5%' align='center'>Item</td> " +
				  "<td width='11%' align='center'>No Factura</td>" +
				  "<td width='27%' align='center'>Nombres y Apellidos</td>" +
				  "<td width='11%' align='center'>Identificacion</td>" +
				  "<td width='7%' align='center'>F.Ingreso</td>" +
				  "<td width='7%' align='center'>F.Egreso</td>" +
				  "<td width='7%' align='center'>Valor</td>" +
				  "<td width='10%' align='center'>Municipio</td>");
		if(!opcion.equals("contabilidad")){
			out.print("<td width='15%' align='center'>Entidad</td>");
		}else{
			out.print("<td width='14%' align='center'>Entidad</td>");
			out.print("<td width='1%' align='center'></td>");
		}
		out.print("</tr>");
		
		rs = mm.consolidadoCTA(codCTA);
		try {
			int crs=0;
			while(rs.next()){
				out.print("<tr>"+
						  "<td width='5%' align='center'>"+(crs+1)+"</td>" +
						  "<td width='11%' align='left'>"+rs.getString(1)+"</td>" +
						  "<td width='27%' align='left'>"+rs.getString(2)+"</td>" +
						  "<td width='11%' align='left'>"+rs.getString(3)+"</td>" +
						  "<td width='7%' align='center'>"+rs.getString(4)+"</td>" +
						  "<td width='7%' align='center'>"+rs.getString(5)+"</td>" +
						  "<td width='7%' align='right'>"+mm.formatMoneda(rs.getString(6))+"</td>" +
						  "<td width='10%' align='left'>"+rs.getString(7)+"</td>");
				
				if(!opcion.equals("contabilidad")){
					out.print("<td width='15%' align='left'>"+rs.getString(8)+"</td>");
				}else{
					out.print("<td width='14%' align='left'>"+rs.getString(8)+"</td>");
					out.print("<td width='1%' align='left'><input type='checkbox'/></td>");
				}
				out.print("</tr>");
				crs++;
			}
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		
		
		out.print("</table></div>");
		out.print("<br>");
		out.print("<table width='100%'><tr><td align='center'>");
		out.print("<input type='button' value='Cerrar' name='btnC' onclick='cerrarVentdetCTA(&quot;"+opcion+"&quot;)' style='margin-right:10px '/>");
		if(!opcion.equals("contabilidad")){
			out.print("<input type='button' value='Imprimir' name='btnIMP' onclick='Imprime()' style='margin-right:10px'/>");
		}else{
			out.print("<input type='button' value='" +
					"" +
					" Todo' name='btnRT' onclick='' style='margin-right:10px'/>");
			out.print("<input type='button' value='Radicar Seleccionado' name='btnRS' onclick='' style='margin-right:10px' />");
		}
		out.print("</td></tr></table>");
		
	}
	
	
	if(va.equals("detRAD")){	
		String opcion = req.getParameter("opcion");
		String codCTA = req.getParameter("consCTA");
		
		out.print("<table  width='100%'>");
		out.print("<tr BGCOLOR='#D3D3D3'  style='font-weight: bold'>");
		out.print("<td  align='CENTER' colspan='3'>INFORMACION RADICACION</td>");
		out.print("</tr>");
		out.print("<tr></tr>");
		out.print("<tr></tr>");
		
		rs = mm.datRAD(codCTA);
		try {
			while(rs.next()){
				out.print("<tr><td><b>No. de cuenta:  </b>"+rs.getString(1)+"</td></tr>");
				out.print("<tr><td><br /></td></tr>");
				out.print("<tr><td><b>Valor CTA:  </b>"+mm.formatMoneda(rs.getString(2))+"</td><td><b>Fecha de envio:  </b>"+rs.getString(3)+" </td><td><b>Usuario:  </b>"+rs.getString(4)+" </td></tr>");
			}
				
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		
		out.print("</table>");
		out.print("<br />");

		out.print("<table  width='100%'>");
		out.print("<tr BGCOLOR='#D3D3D3'  style='font-weight: bold'>");
		out.print("<td  align='CENTER' colspan='10'>CONSOLIDADO DE FACTURAS</td>");
		out.print("</tr>");
		out.print("<tr></tr><tr></tr>");
		
		out.print("</table>" +
				  "<div  style='overflow-y: scroll; height: 280px;'>" +
				  "<table width='100%'>");
		out.print("<tr BGCOLOR='#D3D3D3' style='font-weight: bold'>" +
				  "<td width='5%' align='center'>Item</td> " +
				  "<td width='11%' align='center'>No Factura</td>" +
				  "<td width='27%' align='center'>Nombres y Apellidos</td>" +
				  "<td width='11%' align='center'>Identificacion</td>" +
				  "<td width='7%' align='center'>F.Ingreso</td>" +
				  "<td width='7%' align='center'>F.Egreso</td>" +
				  "<td width='7%' align='center'>Valor</td>" +
				  "<td width='10%' align='center'>Fecha Rad</td>");
		if(!opcion.equals("contabilidad")){
			out.print("<td width='15%' align='center'>Entidad</td>");
		}else{
			out.print("<td width='14%' align='center'>Entidad</td>");
			out.print("<td width='1%' align='center'></td>");
		}
		out.print("</tr>");
		
		rs = mm.consolidadoRAD(codCTA);
		try {
			int crs=0;
			while(rs.next()){
				String a1=rs.getString(7).substring(0, 4);
				String m1=rs.getString(7).substring(5, 7);
				String d1=rs.getString(7).substring(8, 10);
				String e1=d1+"/"+m1+"/"+a1;
				
				out.print("<tr>"+
						  "<td width='5%' align='center'>"+(crs+1)+"</td>" +
						  "<td width='11%' align='left'>"+rs.getString(1)+"</td>" +
						  "<td width='27%' align='left'>"+rs.getString(2)+"</td>" +
						  "<td width='11%' align='left'>"+rs.getString(3)+"</td>" +
						  "<td width='7%' align='center'>"+rs.getString(4)+"</td>" +
						  "<td width='7%' align='center'>"+rs.getString(5)+"</td>" +
						  "<td width='7%' align='right'>"+mm.formatMoneda(rs.getString(6))+"</td>" +
						  "<td width='10%' align='left'>"+e1+"</td>");
				
				if(!opcion.equals("contabilidad")){
					out.print("<td width='15%' align='left'>"+rs.getString(8)+"</td>");
				}else{
					out.print("<td width='14%' align='left'>"+rs.getString(8)+"</td>");
					out.print("<td width='1%' align='left'><input type='checkbox'/></td>");
				}
				out.print("</tr>");
				crs++;
			}
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		
		
		out.print("</table></div>");
		out.print("<br>");
		out.print("<table width='100%'><tr><td align='center'>");
		out.print("<input type='button' value='Cerrar' name='btnC' onclick='cerrarVentdetRAD(&quot;"+opcion+"&quot;)' style='margin-right:10px '/>");
		if(!opcion.equals("contabilidad")){
			out.print("<input type='button' value='Imprimir' name='btnIMP' onclick='Imprime()' style='margin-right:10px'/>");
		}else{
			out.print("<input type='button' value='Radicar Todo' name='btnRT' onclick='' style='margin-right:10px'/>");
			out.print("<input type='button' value='Radicar Seleccionado' name='btnRS' onclick='' style='margin-right:10px' />");
		}
		out.print("</td></tr></table>");
		
	}


	


	if(va.equals("mfact")){
		String opcion = req.getParameter("opcion");
		//System.out.println("La idea es  consultar las facturas");
		out.print("<table width='100%' border='0' align='center'><tr><td colspan='4' height='30' valign='top'><div align='center' class='style11' id='cabecera2'>Consulta de Cuentas de Cobro</div></td></tr></table>");
		
		//out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='60%'><i><div align='center'>Paciente</div></i></td><td width='30%'><i><div align='center'>Empresa</div></i></td><td width='10%'><i><div align='center'>Admisi�n</div></i></td></tr>");  //
		out.print("<table width='100%' border=0' class='style6' ><tr BGCOLOR='#D3D3D3' ><td colspan='4' height='30' valign='top'><i><div align='center'>Criterios de Busqueda</div></i></td></tr>");
		//out.print("<tr></tr><tr><td width='15%'><div align='right'>Cuenta de Cobro No:</div></td><td width='30%'><input name='txtCdC' type='text' id='txtCdC' size='39' maxlength='100' /></td></tr>");
		out.print("<tr></tr><tr><td width='15%'><div align='right'>Factura No:</div></td><td width='30%'><input name='txtFact' type='text' id='txtFact' size='39' maxlength='100' /></td>");
		out.print("<td width='15%'><div align='right'>Entidad:</div></td><td width='40%'><select  style='width:269px' name='Ent' id='Ent' ><option value='Seleccione'>Seleccione</option>");
		rs2=mm.Empresas();
		try {
			while(rs2.next()){
			out.print("<option title='"+rs2.getString(2)+"' value="+rs2.getString(1)+">"+rs2.getString(2)+"</option>");
			}
			rs2.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		out.print("</td></tr>");
					
		out.print("<tr><td><div align='right'>Fecha Inicial:</div></td><td><input name='FI' type='text' id='FI' size='10'  onKeyup='masca(this,patron,true,0,0,0)' onblur='vfi()'; /></td><td><div align='right'>Fecha Final:</div></td><td><input name='FF' type='text' id='FF' size='10' onKeyup='masca(this,patron,true,0,0,0)' onblur='vff()'; /></td></tr><tr></tr></table>");
		
		out.print("<table width='100%' border='0' class='style6' ><tr><td colspan='4'><div align='center'><input name='btnCrearEntidad' type='button' id='btnCrearEntidad' value='     Consultar     ' onclick='ConsultarEstadoFactura(&quot;"+opcion+"&quot;)' /></div></td></tr><tr></tr><tr></tr></table>");
		out.print("<table width='100%' border='0' class='style6' ><tr></tr><tr BGCOLOR='#D3D3D3' ><td colspan='4' height='30' valign='top'><i><div align='center'>Resultados de Busqueda</div></i></td></tr><tr></tr></table>");
		out.print("<div id='resultadosf' style='height: 280px; overflow-y: scroll'></div>");
			
	}
	if(va.equals("showEF")){	
		System.out.println("showEF");
	//	String opcion = req.getParameter("opcion");
		//CTA0001000018
		String sql="";
		//String v11=req.getParameter("v11");
		//if((v11==null)||(v11.equals(""))){v11="";}else{sql=sql+" AND ffe.consEnvio='"+v11.substring(6,v11.length())+"'";}
		
		String v1=req.getParameter("v1");
		if((v1==null)||(v1.equals(""))){v1="";}else{sql=sql+" AND fn.consecutivo='"+v1+"'";}
		String v2=req.getParameter("v2");
		if(v2.equals("Seleccione")){v2="";}else{sql=sql+" AND fef.cod_eps="+v2;}
		
		String v8=req.getParameter("v8");
		String v9=req.getParameter("v9");
		
		if((v8==null)||(v8.equals(""))||(v9.equals(""))||(v9==null)){v8="";v9="";}
		else{
		String dv8=v8.substring(0, 2);
		String mv8=v8.substring(3, 5);
		String av8=v8.substring(6, 10);
		String fv8=av8+"-"+mv8+"-"+dv8;
		
		String dv9=v9.substring(0, 2);
		String mv9=v9.substring(3, 5);
		String av9=v9.substring(6, 10);
		String fv9=av9+"-"+mv9+"-"+dv9;
		
		sql=sql+" AND fn.fecha BETWEEN '"+fv8+"' AND '"+fv9+"' ";}
		 
		
		int crs=0;		
		try {
			rs3=mm.obtenerEstFact(sql);
					while(rs3.next()){
						if(crs==0){
							out.print("<table  width='100%'> " +
									  "<tr BGCOLOR='#D3D3D3' style='font-weight: bold'> " +
									  "<td width='5%' align='center'>Item</td>" +
									  "<td width='12%' align='center'>No Factura</td>" +
									  "<td width='11%' align='center'>Valor</td>" +
									  "<td width='8%' align='center'>F.Creacio</td>" +
									  "<td width='8%' align='center'>F.Estado</td>" +
									  "<td width='15%' align='center'>Estado</td>" +
									  "<td width='28%' align='center'>Usuario</td>" +
									  "<td width='13%' align='center'></td></tr>");
						}
						
						out.print("<tr>" +
							     "<td width='5%' align='center'>"+(crs+1)+"</td>" +
							     "<td width='12%' align='center'>"+rs3.getString(1)+"</td>" +
							     "<td width='11%' align='right'>"+mm.formatMoneda(rs3.getString(2))+"</td>" +
							     "<td width='8%' align='center'>"+rs3.getString(3)+"</td>" +
							     "<td width='8%' align='center'>"+rs3.getString(4)+"</td>" +
							     "<td width='15%' align='left'>"+rs3.getString(5)+"</td>" +
							     "<td width='28%' align='left'>"+rs3.getString(6)+"</td>" +
							     "<td width='13%' align='center'><a href='#' onclick='mostrarDetMovFact(&quot;"+rs3.getString(1)+"&quot;,&quot;&quot)'>Ver detalles</a></td>" +
							  "</tr>");
						
						crs++;
					}rs3.getStatement().getConnection().close();								
						
			out.print("</table>");
			//rs2.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		if(crs==0){
			out.print("No hay resultados para sus criterios de busqueda.");
		}
	}
	
	/*if(va.equals("showEF--")){	
		System.out.println("showEF");
	//	String opcion = req.getParameter("opcion");
		//CTA0001000018
		String sql="";
		//String v11=req.getParameter("v11");
		//if((v11==null)||(v11.equals(""))){v11="";}else{sql=sql+" AND ffe.consEnvio='"+v11.substring(6,v11.length())+"'";}
		
		String v1=req.getParameter("v1");
		if((v1==null)||(v1.equals(""))){v1="";}else{sql=sql+" AND fn.consecutivo='"+v1+"'";}
		String v2=req.getParameter("v2");
		if(v2.equals("Seleccione")){v2="";}else{sql=sql+" AND fef.cod_eps="+v2;}
		
		String v8=req.getParameter("v8");
		String v9=req.getParameter("v9");
		
		if((v8==null)||(v8.equals(""))||(v9.equals(""))||(v9==null)){v8="";v9="";}
		else{
		String dv8=v8.substring(0, 2);
		String mv8=v8.substring(3, 5);
		String av8=v8.substring(6, 10);
		String fv8=av8+"-"+mv8+"-"+dv8;
		
		String dv9=v9.substring(0, 2);
		String mv9=v9.substring(3, 5);
		String av9=v9.substring(6, 10);
		String fv9=av9+"-"+mv9+"-"+dv9;
		
		sql=sql+" AND fn.fecha BETWEEN '"+fv8+"' AND '"+fv9+"' ";}
		 
		
		int crs=0;		
		rs2=mm.obtenerEstFactu(sql);
		try {
			while(rs2.next()){
				System.out.println("Whileeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
				if((rs2.getString(4).equals("FACTURADA"))){
					//System.out.println("IFFFF    FACTURADA");
					if(crs==0){
						out.print("<table  width='100%'> " +
								  "<tr BGCOLOR='#D3D3D3' style='font-weight: bold'> " +
								  "<td width='5%' align='center'>Item</td>" +
								  "<td width='12%' align='center'>No Factura</td>" +
								  "<td width='11%' align='center'>Valor</td>" +
								  "<td width='8%' align='center'>F.Creacio</td>" +
								  "<td width='8%' align='center'>F.Estado</td>" +
								  "<td width='15%' align='center'>Estado</td>" +
								  "<td width='28%' align='center'>Usuario</td>" +
								  "<td width='13%' align='center'></td></tr>");
					}
					
				out.print("<tr>" +
						     "<td width='5%' align='center'>"+(crs+1)+"</td>" +
						     "<td width='12%' align='center'>"+rs2.getString(1)+"</td>" +
						     "<td width='11%' align='right'>"+mm.formatMoneda(rs2.getString(2))+"</td>" +
						     "<td width='8%' align='center'>"+rs2.getString(3)+"</td>" +
						     "<td width='8%' align='center'> </td>" +
						     "<td width='15%' align='left'>"+rs2.getString(4)+"</td>" +
						     "<td width='28%' align='left'>"+rs2.getString(5)+"</td>" +
						     "<td width='13%' align='center'><a href='#' onclick='mostrarDetMovFact(&quot;"+rs2.getString(1)+"&quot;,&quot;&quot)'>Ver detalles</a></td>" +
						  "</tr>");
				crs++;
				}else{
					//System.out.println("SINOSINOSINO    FACTURADA LIMIT1");
					String sql2=sql+" ORDER BY  fmf.codigo DESC LIMIT 1 ";
					rs3=mm.obtenerEstFact(sql2);
					while(rs3.next()){
						if(crs==0){
							out.print("<table  width='100%'> " +
									  "<tr BGCOLOR='#D3D3D3' style='font-weight: bold'> " +
									  "<td width='5%' align='center'>Item</td>" +
									  "<td width='12%' align='center'>No Factura</td>" +
									  "<td width='11%' align='center'>Valor</td>" +
									  "<td width='8%' align='center'>F.Creacio</td>" +
									  "<td width='8%' align='center'>F.Estado</td>" +
									  "<td width='15%' align='center'>Estado</td>" +
									  "<td width='28%' align='center'>Usuario</td>" +
									  "<td width='13%' align='center'></td></tr>");
						}
						
						out.print("<tr>" +
							     "<td width='5%' align='center'>"+(crs+1)+"</td>" +
							     "<td width='12%' align='center'>"+rs3.getString(1)+"</td>" +
							     "<td width='11%' align='right'>"+mm.formatMoneda(rs3.getString(2))+"</td>" +
							     "<td width='8%' align='center'>"+rs3.getString(3)+"</td>" +
							     "<td width='8%' align='center'>"+rs3.getString(4)+"</td>" +
							     "<td width='15%' align='left'>"+rs3.getString(5)+"</td>" +
							     "<td width='28%' align='left'>"+rs3.getString(6)+"</td>" +
							     "<td width='13%' align='center'><a href='#' onclick='mostrarDetMovFact(&quot;"+rs3.getString(1)+"&quot;,&quot;&quot)'>Ver detalles</a></td>" +
							  "</tr>");
						
						crs++;
					}rs3.getStatement().getConnection().close();
					
				}
				
			
			}
			
			out.print("</table>");

			rs2.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		if(crs==0){
			out.print("No hay resultados para sus criterios de busqueda.");
		}
	}*/
	
	

	
	if(va.equals("showDMF")){	
		String consFact = req.getParameter("consFact");
		
		out.print("<table  width='100%'> " +
				  "<tr BGCOLOR='#D3D3D3' style='font-weight: bold'> " +
				  "<td align='center'>DETALLE MOVIMIENTO FACTURA</td></tr></table></br>");
		out.print("<div style='height:150px;'>");
		int crs=0;	
		rs2=mm.obtenerDetMovFact(consFact);
	    	String validacion="nada";
		try {
			while(rs2.next()){
						if(crs==0){
							out.print("<table  width='100%' > " +
									  "<tr BGCOLOR='#D3D3D3' style='font-weight: bold'> " +
									  "<td width='5%' align='center'>Item</td>" +
									  "<td width='12%' align='center'>No Factura</td>" +
									  "<td width='11%' align='center'>Valor</td>" +
									  "<td width='10%' align='center'>F.Creacio</td>" +
									  "<td width='10%' align='center'>F.Estado</td>" +
									  "<td width='18%' align='center'>Estado</td>" +
									  "<td width='37%' align='center'>Usuario</td></tr>");
							if(rs2.getString(5).equals("FACTURADA")){
								
							}else{
									rs=mm.BuscarFact(consFact);
									while(rs.next()){
										out.print("<tr>" +
											     "<td width='5%' align='center'>"+(crs+1)+"</td>" +
											     "<td width='12%' align='center'>"+rs.getString(1)+"</td>" +
											     "<td width='11%' align='right'>"+mm.formatMoneda(rs.getString(2))+"</td>" +
											     "<td width='10%' align='center'>"+rs.getString(3)+"</td>" +
											     "<td width='10%' align='center'>"+rs.getString(4)+"</td>" +
											     "<td width='18%' align='left'>"+rs.getString(5)+"</td>" +
											     "<td width='37%' align='left'>"+rs.getString(6)+"</td>" +
											  "</tr>");
									}rs.getStatement().getConnection().close();
							}
						}
					System.out.println("INICIO "+validacion);
					System.out.println("Valor del campo 5 "+rs2.getString(5));
					if(validacion.equals("nada")){	
					out.print("<tr>" +
							     "<td width='5%' align='center'>"+(crs+1)+"</td>" +
							     "<td width='12%' align='center'>"+rs2.getString(1)+"</td>" +
							     "<td width='11%' align='right'>"+mm.formatMoneda(rs2.getString(2))+"</td>" +
							     "<td width='10%' align='center'>"+rs2.getString(3)+"</td>" +
							     "<td width='10%' align='center'>"+rs2.getString(4)+"</td>" +
							     "<td width='18%' align='left'>"+rs2.getString(5)+"</td>" +
							     "<td width='35%' align='left'>"+rs2.getString(6)+"</td>" +
							  "</tr>");
					}else{
						out.print("<tr>" +
							     "<td width='5%' align='center'>"+(crs+1)+"</td>" +
							     "<td width='12%' align='center'>"+rs2.getString(1)+"</td>" +
							     "<td width='11%' align='right'>"+mm.formatMoneda(rs2.getString(2))+"</td>" +
							     "<td width='10%' align='center'>"+rs2.getString(3)+"</td>" +
							     "<td width='10%' align='center'>"+rs2.getString(4)+"</td>");
								
								 if(rs2.getString(5).equals("DEVUELTA")){
									 if(validacion.equals("ENVIADA")){
										 validacion="FACTURADA";
									 }else{
										 if(validacion.equals("RADICADA")){
											 validacion="ENVIADA";
										 }else{
											 if(validacion.equals("EN AUDITORIA")){
												 validacion="FACTURADA";
											 }else{
												 if(validacion.equals("Radicada Int")){
													 validacion="EN AUDITORIA";
												 }else{
													 if(validacion.equals("REENVIO")){
														 validacion="Radicada Int";
													 }
												 }
											 }
										 }
									 }
							     out.println("<td width='18%' align='left'>"+rs2.getString(5)+" A ESTADO ("+validacion+")</td>");
								 }else{
									 out.println("<td width='18%' align='left'>"+rs2.getString(5)+"</td>");
								 }
							    
							     out.println("<td width='35%' align='left'>"+rs2.getString(6)+"</td>" +
							  "</tr>");
					}
					validacion=rs2.getString(5);
					System.out.println("Final "+validacion);
					crs++;
			
			}//Fin While 
			
			out.print("</table>");
			out.print("</div>");
			out.print("<table  width='100%'><tr><td align='center'><input type='button' value='Cerrar' onclick='cerrarVentdetMOV(&quot;&quot;)' /></td></tr></table>");
			rs2.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		if(crs==0){
			out.print("No hay resultados para sus criterios de busqueda.");
		}
	}

	
	if(va.equals("admsinfact")){
		String opcion = req.getParameter("opcion");
		//System.out.println("La idea es  consultar las facturas");
		out.print("<table width='100%' border='0' align='center'><tr><td colspan='4' height='30' valign='top'><div align='center' class='style11' id='cabecera2'>Consulta Admisiones sin Facturar</div></td></tr></table>");
		
		//out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='60%'><i><div align='center'>Paciente</div></i></td><td width='30%'><i><div align='center'>Empresa</div></i></td><td width='10%'><i><div align='center'>Admisi�n</div></i></td></tr>");  //
		out.print("<table width='100%' border=0' class='style6' ><tr BGCOLOR='#D3D3D3' ><td colspan='4' height='30' valign='top'><i><div align='center'>Criterios de Busqueda</div></i></td></tr>");
		out.print("<tr></tr><tr><td width='15%'></td><td width='30%'></td></tr>");
		out.print("<tr></tr><tr>");
		out.print("<td><div align='right'>Entidad:</div></td><td width='40%'><select  style='width:269px' name='Ent' id='Ent' ><option value='Seleccione'>Seleccione</option>");
		rs2=mm.Empresas();
		try {
			while(rs2.next()){
			out.print("<option title='"+rs2.getString(2)+"' value="+rs2.getString(1)+">"+rs2.getString(2)+"</option>");
			}
			rs2.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		out.print("</td></tr>");
					
		out.print("<tr><td><div align='right'>Fecha Inicial:</div></td><td><input name='FI' type='text' id='FI' size='10'  onKeyup='masca(this,patron,true,0,0,0)' onblur='vfi()'; /></td><td><div align='right'>Fecha Final:</div></td><td><input name='FF' type='text' id='FF' size='10' onKeyup='masca(this,patron,true,0,0,0)' onblur='vff()'; /></td></tr><tr></tr></table>");
		
		out.print("<table width='100%' border='0' class='style6' ><tr><td colspan='4'><div align='center'><input name='btnCrearEntidad' type='button' id='btnCrearEntidad' value='     Consultar     ' onclick='ConsultarAdmSnFacturar(&quot;"+opcion+"&quot;)' /></div></td></tr><tr></tr><tr></tr></table>");
		out.print("<table width='100%' border='0' class='style6' ><tr></tr><tr BGCOLOR='#D3D3D3' ><td colspan='4' height='30' valign='top'><i><div align='center'>Resultados de Busqueda</div></i></td></tr><tr></tr></table>");
		out.print("<div id='resultadosf' style='height: 280px; overflow-y: scroll'></div>");
			
	}
	
if(va.equals("autoinv3a")){
		
		try {
			//System.out.print("ESSSTOO."+xx);
			rs =mm.listarPYSU(texto,xx);
			String cadena ="";
			cadena="[";
			while(rs.next()){
				cadena = cadena+"'"+rs.getString(5)+"|"+rs.getString(4)+"|"+rs.getString(1)+"|"+rs.getString(2)+"|"+rs.getString(3)+"|"+rs.getString(6)+"|"+rs.getString(7)+"|"+rs.getString(8)+"|"+rs.getString(9)+"|"+rs.getString(10)+"|"+rs.getString(11)+"'";
            	cadena = cadena +",";	
			}
			cadena = cadena+"]";
			//System.out.print("ESSSTOO."+cadena);
			res.getWriter().write(cadena);
			rs.getStatement().getConnection().close();
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
		}
	}

if(va.equals("VR1")){
	
	//System.out.println("CESAR: "+adm01+" -ff- "+xx+" -ff- "+lot  );
	
	out.print("<table width='100%' class='style6'><tr><td colspan='5'><div align='center' class='style11' id='cabecera2'>Validacion de RIPS</div></td></tr>");
	
	//out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='50%'><i><div align='center'>Empresa</div></i></td><td width='29%'><i><div align='center'>Tipo de Convenio</div></i></td><td width='7%'><i><div align='center'>Fecha Inicial</div></i></td><td width='7%'><i><div align='center'>Fecha Final</div></i></td><td width='7%'><i><div align='center'>Acci�n</div></i></td></tr>");  //
	
	
	int sw1=0;
	int sw2=0;
	int sw3=0;
	rs1=mm.Dxingreso(adm01);
	rs2=mm.Dxegreso(adm01);
	rs3=mm.Destinop(adm01);
	
	try {
		out.print("<table width='100%' border='0' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='100%' colspan='5'><i><div align='center'>Diagnosticos de Ingreso</div></i></td></tr>");  //
		out.print("<tr><td width='20%'><i><u>Codigo</u></i></td><td width='20%'><i><u>Descripcion</u></i></td><td width='20%'></td><td width='20%'></td><td width='20%'></td></tr>");
		while(rs1.next()){
			sw1=1;
			out.print("<tr><td>"+rs1.getString(3)+"</td><td colspan='4'>"+rs1.getString(2)+"</td></tr>");
		}rs1.getStatement().getConnection().close();
		//out.print("</table>");
		out.print("<tr></tr>");
		
		if(sw1==0){
			out.print("<tr><td><input type='text' id='txtCodDiagnostico' readonly='readonly' /></td><td colspan='2'><input type='text' id='txtNomDiagnos' onKeyup='autocompletarCIE10();' size='50' /></td><td><input type='button' value='Ingresar'  onclick='IngresaDxie("+adm01+","+xx+","+lot+",1)' /></td></tr>");
			out.print("<tr><td></td><td colspan='2'><div id='SugeDiagnostico'></div></td></tr>");
		}
		
		
	//	out.print("<table width='100%' border='0' class='style6' >
		out.print("<tr BGCOLOR='#D3D3D3' ><td width='100%' colspan='5'><i><div align='center'>Diagnosticos de Egreso</div></i></td></tr>");  //
		out.print("<tr><td><i><u>Codigo</u></i></td><td><i><u>Descripcion</u></i></td></tr>");
		while(rs2.next()){
			sw2=1;
			out.print("<tr><td>"+rs2.getString(3)+"</td><td colspan='4'>"+rs2.getString(2)+"</td></tr>");
		}rs2.getStatement().getConnection().close();
		out.print("<tr></tr>");
		
		if(sw2==0){
			out.print("<tr><td><input type='text' id='txtCodDiagnosticoEgreso' readonly='readonly' /></td><td colspan='2'><input type='text' id='txtNomDiagnosRelaEgreso' onKeyup='autocompletarCIE10Egreso();' size='50' /></td><td><input type='button' value='Ingresar'  onclick='IngresaDxie("+adm01+","+xx+","+lot+",2)' /></td></tr>");
			out.print("<tr><td></td><td colspan='2'><div id='SugeDiagnosticoEgreso'></div></td></tr>");
		}
		
		
	//	out.print("<table width='100%' border='0' class='style6' >
		out.print("<tr BGCOLOR='#D3D3D3' ><td width='100%' colspan='5'><i><div align='center'>Destino del Paciente</div></i></td></tr>");  //
		out.print("<tr><td><i><u>Destino del paciente</u></i></td><td><i><u>Estado a la salida</u></i></td><td><i><u>Finalidad de la consulta</u></i></td><td><i><u>Causa Externa</u></i></td><td><i><u>Tipo de Diagnostico</u></i></td></tr>");
		while(rs3.next()){
			sw3=1;
			out.print("<tr><td>"+rs3.getString(1)+"</td><td>"+rs3.getString(2)+"</td><td>"+rs3.getString(3)+" - "+rs3.getString(4)+"</td><td>"+rs3.getString(5)+" - "+rs3.getString(6)+"</td><td>"+rs3.getString(7)+"</td></tr>");
		}rs3.getStatement().getConnection().close();
		
		if(sw3==0){
			out.print("<tr><td><select  style='width:100%' id='dp' >");
			out.print("<option title='Seleccione' value='Seleccione'>Seleccione</option>");
			out.print("<option title='SALIDA DE URGENCIA' value='SALIDA DE URGENCIA'>SALIDA DE URGENCIA</option>");
			out.print("<option title='SALIDA DE HOSPITALIZACION' value='SALIDA DE HOSPITALIZACION'>SALIDA DE HOSPITALIZACION</option>");
			out.print("<option title='SALIDA AMBULATORIA' value='SALIDA AMBULATORIA'>SALIDA AMBULATORIA</option>");
			out.print("<option title='SALIDA DE CONSULTA EXTERNA' value='SALIDA DE CONSULTA EXTERNA'>SALIDA DE CONSULTA EXTERNA</option>");
			out.print("</select></td>");
			
			out.print("<td><select  style='width:100%' id='es' >");
			out.print("<option title='VIVO' value='VIVO'>VIVO</option>");
			out.print("<option title='MUERTO' value='MUERTO'>MUERTO</option>");
			out.print("</select></td>");
			
			out.print("<td><select  style='width:100%' id='fc' >");
			rs = mm.finconsulta();
			while(rs.next()){
				out.print("<option title='"+rs.getString(2)+"' value='"+rs.getString(3)+"'>"+rs.getString(2)+"</option>");
			}
			out.print("</select></td>");
			rs.getStatement().close();
			
			out.print("<td><select  style='width:100%' id='ce' >");
			rs = mm.causaext();
			while(rs.next()){
				out.print("<option title='"+rs.getString(2)+"' value='"+rs.getString(3)+"'>"+rs.getString(2)+"</option>");
			}
			out.print("</select></td>");
			rs.getStatement().close();
			
			out.print("<td><select  style='width:100%' id='td' >");
			out.print("<option title='Impresion Diagnostica' value='1'>Impresion Diagnostica</option>");
			out.print("<option title='Confirmado Nuevo' value='2'>Confirmado Nuevo</option>");
			out.print("<option title='Confirmado Repetido' value='3'>Confirmado Repetido</option>");
			out.print("</select></td>");
			out.print("</tr>");
			
			
			out.print("<tr></tr><tr></tr><tr><td colspan='5'><div align='center'><input type='button' value='Ingresar'  onclick='IngresaDestino("+adm01+","+xx+","+lot+")' /></div></td></tr>");
			
		}
		
		out.print("</table>");
	} catch (SQLException e) {
		out.print("Error "+e);
		e.printStackTrace();
	}
	
	
	
	/*
	out.print("<td><select  style='width:100%'  name='Ent' id='Ent' ><option value='Seleccione'>Seleccione</option>");
	rs2=mm.EmpresasC();
	try {
		while(rs2.next()){
		out.print("<option title='"+rs2.getString(8)+"' value="+rs2.getString(6)+">"+rs2.getString(8)+"</option>");
		}
		rs2.getStatement().close();
	} catch (SQLException e) {
		out.print("Error "+e);
		e.printStackTrace();
	}
	out.print("</td>");
	*/

	//adm01
}

if(va.equals("VR2")){
	
	Calendar calendario = Calendar.getInstance();
	//	Calendar calendario = new GregorianCalendar();
	int hora, minutos, segundos;
	hora =calendario.get(Calendar.HOUR_OF_DAY);
	minutos = calendario.get(Calendar.MINUTE);
	segundos = calendario.get(Calendar.SECOND);
	String hra= hora+":"+minutos+":"+segundos;		
	
	//String t="0";
	if(nivel.equals("1")){
		//t="1";
		}
	if(nivel.equals("2")){
	//	t="2";
		}
	
	rs = mm.CodigoCIE(lotil);
	try {
		if(rs.next()){
			mm.InsertarDxi(lotil,lot,hra,fechacjmysql,adm01,xx,rs.getString(1),nivel);
		}
		rs.getStatement().getConnection().close();
	} catch (SQLException e) {
		out.print("Error "+e);
		e.printStackTrace();
	}	
}

if(va.equals("VR3")){
	
	Calendar calendario = Calendar.getInstance();
	//	Calendar calendario = new GregorianCalendar();
	int hora, minutos, segundos;
	hora =calendario.get(Calendar.HOUR_OF_DAY);
	minutos = calendario.get(Calendar.MINUTE);
	segundos = calendario.get(Calendar.SECOND);
	String hra= hora+":"+minutos+":"+segundos;		
	
	mm.InsertarDestino(lotil,lot,hra,fechacjmysql,adm01,xx,nivel,Abreviado,texto,n);
}

	if(va.equals("autoinv4a")){
		
		try {

			rs =mm.listarRefU(texto,xx);
			String cadena ="";
			cadena="[";
			while(rs.next()){
				cadena = cadena+"'"+rs.getString(4)+"|"+rs.getString(5)+"|"+rs.getString(1)+"|"+rs.getString(2)+"|"+rs.getString(3)+"|"+rs.getString(6)+"|"+rs.getString(7)+"|"+rs.getString(8)+"|"+rs.getString(9)+"|"+rs.getString(10)+"|"+rs.getString(11)+"'";
            	cadena = cadena +",";	
            	//System.out.print("cadena."+cadena);
			}
			cadena = cadena+"]";
			//System.out.print("ESSSTOO."+cadena);
			res.getWriter().write(cadena);
			rs.getStatement().getConnection().close();
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
		}
	}
	
	
	if(va.equals("showADMsnFact")){	
		//String opcion = req.getParameter("opcion");
		//CTA0001000018
		String sql="";
		
		String v2=req.getParameter("v2");
		if(v2.equals("Seleccione")){v2="";}else{sql=sql+" AND fef.cod_eps="+v2;}
		
		String v8=req.getParameter("v8");
		String v9=req.getParameter("v9");
		
		if((v8==null)||(v8.equals(""))||(v9.equals(""))||(v9==null)){
			v8="";v9="";
		}
		else{
			String dv8=v8.substring(0, 2);
			String mv8=v8.substring(3, 5);
			String av8=v8.substring(6, 10);
			String fv8=av8+"-"+mv8+"-"+dv8;
			
			String dv9=v9.substring(0, 2);
			String mv9=v9.substring(3, 5);
			String av9=v9.substring(6, 10);
			String fv9=av9+"-"+mv9+"-"+dv9;
			sql=sql+" AND adm.fecha_registro BETWEEN '"+fv8+"' AND '"+fv9+"' ";
		}
		sql+=" GROUP BY adm.adm_numero_ingreso";		
		//int valorAdmFact = 0;
		int crs=0;		
		rs2=mm.obtenerAdmSinFact(sql);
		try {
			while(rs2.next()){
				if(crs==0){
					out.print("<table width=100%>" +
							  "<tr BGCOLOR='#D3D3D3' style='font-weight: bold'> " +
							  "<td width='8%' align='center'> Admision </td> " +
							  "<td width='29%' align='center'>Paciente</td> " +
							  "<td width='12%' align='center'>Identificacion</td> " +
							  "<td width='19%' align='center'>Entidad</td> " +
							  "<td width='9%' align='center'>F.Ingreso</td> " +
							  "<td width='9%' align='center'>F.Egreso</td> " +
							  "<td width='14%' align='center'>Saldo por Facturar</td>");
				}
				
				

				out.print("<tr> " +
									  "<td align='center'>"+rs2.getString(1)+"</td> " +
									  "<td align='left'>"+rs2.getString(2)+" "+rs2.getString(3)+" "+rs2.getString(4)+"</td> " +
									  "<td align='left'>"+rs2.getString(5)+" "+rs2.getString(6)+"</td> " +
									  "<td align='left'>"+rs2.getString(7)+"</td> " +
									  "<td align='center'>"+rs2.getString(8)+"</td> " +
									  "<td align='center'>"+rs2.getString(9)+"</td> " +
									  "<td align='right'>"+mm.formatMoneda(""+(rs2.getInt(10)))+" " +
									  "</tr>");
			crs++;
			
			}
			
			out.print("</table>");

			rs2.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		if(crs==0){
			out.print("No hay resultados para sus criterios de busqueda.");
		}
	}
	
	
	if(va.equals("EliminarTM")){
	   mm.EliminarCargues(enca);
	  // out.print("No hay resultados para sus criterios de busqueda.");
		//System.out.print(enca);
	}
	
if(va.equals("ac01")){
		
		out.print("<table width='100%'  class='style6' border='0'><tr>");
		out.print("<td colspan='5'><div align='center' class='style11' id='cabecera2'>Seleccione Una Opcion </div></td></tr>");
		out.print("<tr><td  width='50%'><label><input name='radiobutton' type='radio' value='radiobutton' id='vc' onclick='OpcionConvenio()' />Vencimiento de Covenios</label></td>");
		out.print("<td width='50%'><label><input name='radiobutton' type='radio' value='radiobutton' id='tc' onclick='OpcionConvenio()' />Topes de Convenios</label></td></tr>");
		out.print("<tr><td colspan='5'><div id='Opcion'></div></td></tr>");
		out.print("</table>");
		out.print("<table width='100%'  class='style6' border='0'><tr>");
		out.print("<tr><td colspan='5'><div id='resultadoa'></div></td></tr></table>");
	}
	
	if(va.equals("vc")){
		
		int crs=0;		
		rs2=mm.GenerarConveniosxff();
		try {
			while(rs2.next()){
				if(crs==0){
					out.print("<table id='listFact' width='100%' border='1' class='style6'  style='font-size: 13px'>" +
							  	"<tr BGCOLOR='#D3D3D3'  style='font-weight: bold'>" +
							  		"<td width='5%' align='center'><i>Item</i></td>" +
							  		"<td width='11%' align='center'><i>Contrato</i></td>" +
							  		"<td width='24%' align='center'><i>Entidad</i></td>" +
							  		"<td width='11%' align='center'><i>Inicia</i></td>" +
							  		"<td width='8%' align='center'><i>Finaliza</i></td>" +
							  		"<td width='8%' align='center'><i>Tarifas</i></td>" +
							  		"<td width='8%' align='center'><i>Valor</i></td>" +
							  		"<td width='10%' align='center'><i>Autoriza</i></td>" +
							  		"<td width='10%' align='center'><i>Observacion</i></td>" +
							   		"</tr>");
				}
			  	
				SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
				Date fecd = null;
				Date fecf = null;
				try {
				fecd= formatoDelTexto.parse(fechacjmysql);
				fecf= formatoDelTexto.parse(rs2.getString(5));
				} catch (ParseException ex) {
				  ex.printStackTrace();
				}
				if (fecd.compareTo(fecf) < 0){		
					out.print("<tr>" +
					  	"<td width='5%' align='center'>"+(crs+1)+"</td>" +
					  	"<td width='11%' align='left'>"+rs2.getString(2)+"</td>" +
					  	"<td width='24%' align='left'>"+rs2.getString(3)+"</td>" +
					  	"<td width='11%' align='right'>"+rs2.getString(4)+"</td>" +
					  	"<td width='8%' align='right'>"+rs2.getString(5)+"</td>");
					rs=mm.ManualesConvenio(rs2.getString("cod_entidad"));
					out.print("<td width='8%' align='right'><table border='1' class='style6'  style='font-size: 13px'>");
					while(rs.next()){
						out.print("<tr><td width='8%' align='right'>"+rs.getString("descripcion")+"</td></tr>");	
					}
					out.print("</table></td>");
					rs.getStatement().getConnection().close();
					  					  	
					  	out.print("<td width='8%' align='right'>"+rs2.getString(6)+"</td>" +
					  	"<td width='10%' align='right' id='vEF"+crs+"'>"+rs2.getString(7)+"</td>" +
					  	"<td width='10%' align='center'>"+rs2.getString(8)+"</td>" +
					    "</tr>");
			  	}else{
			  		out.print("<tr>" +
						  	"<td width='5%' align='center'>"+(crs+1)+"</td>" +
						  	"<td width='11%' align='left'>"+rs2.getString(2)+"</td>" +
						  	"<td width='24%' align='left'>"+rs2.getString(3)+"</td>" +
						  	"<td width='11%' align='right' >"+rs2.getString(4)+"</td>" +
						  	"<td width='8%' align='right'><FONT COLOR='#FF0000'>"+rs2.getString(5)+"</td>");
			  		rs=mm.ManualesConvenio(rs2.getString("cod_entidad"));
					out.print("<td width='8%' align='right'><table border='1' class='style6'  style='font-size: 13px'>");
					while(rs.next()){
						out.print("<tr><td width='8%' align='right'>"+rs.getString("descripcion")+"</td></tr>");	
					}
					out.print("</table></td>");
					rs.getStatement().getConnection().close();
						  	out.print("<td width='8%' align='right'>"+rs2.getString(6)+"</td>" +
						  	"<td width='10%' align='right' id='vEF"+crs+"'>"+rs2.getString(7)+"</td>" +
						  	"<td width='10%' align='center'>"+rs2.getString(8)+"</td>" +
						    "</tr>");
			  		
			  	}
			  	
			crs++;
			}
			out.print("</table>");
			rs2.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		if(crs==0){
			out.print("No hay resultados para sus criterios de busqueda.");
		}	
	}
	
	
//////////////////NUEVA FACTURACION/////////////////////
	if(va.equals("100")){
		String tipoFact = req.getParameter("tipoFact");
		//String amb = req.getParameter("amb");
		
		try {
			
			rsef=mm.VerificarEncabezadoFacturado(enca);
			if(rsef.next()){
				System.out.print("Ya existe una factura con este codigo de encabezado "+enca+" Usuario= "+Abreviado);
			}else{
				//TIPOS DE FACTURA
				//tipoFact=0 hospitalizacion
				//tipoFact=1 urgencia
				//tipoFact=2 Consulta Externa y Ambulatorio
				//tipoFact=3 capitado??
				
				//1. Los moviemientos en estado 2 pasan a 1
					//System.out.print("Creando la factura del encabezado "+enca);
					if(tipoFact.equals("1")){
						mm.actualizarMovUrg(enca);
					}
					
					
					int cont=0;
					rs2=mm.NumFacturas();
					while(rs2.next()){
						cont=cont+1;
						//nf=rs2.getString(1);
					}
					rs2.getStatement().getConnection().close();
					
				//2. Consultamos ultimo consecutivo de numeradas
					String nf="";
					String Prefijo="";
					rs2=mm.NumFacturas();
					if(rs2.next()){
						nf=rs2.getString("consecutivo");
						Prefijo=rs2.getString("sigla_consecutivo");
					}
					rs2.getStatement().getConnection().close();
		
				//3. Aumentamos el consecutivo de numeradas
					//int l=nf.length();
					//String sincc=nf.substring(2,l);
					int z=Integer.parseInt(nf)+1;
					//int lz=String.valueOf(z).length();
					String zc=String.valueOf(z);
			
					/*for(int i=lz; i<l-2; i++ ){//l es la longitud -2 x las CC
						zc="0"+zc;	//System.out.println("zc: "+zc);
					}
					zc="CC"+zc;*/
					
					mm.ActualizarNumFacturas(zc);
					
				//4. Ingresamos a facturas numeradas
					zc=Prefijo+""+zc;
					mm.AsignarNumFact(fechacjmysql,encab,zc);
					
				//5.Ingresamos en fact_movfacturas
					rs2=mm.BuscarCodFactNumerada(fechacjmysql,encab,zc);
					String codfactNum="";
					if(rs2.next()){
						codfactNum=rs2.getString(1);
						mm.recordEstadoFact(codfactNum, "0", fechacjmysql, Abreviado);
					}
					rs2.getStatement().getConnection().close();
					
					
					Calendar calendario = Calendar.getInstance();
					//	Calendar calendario = new GregorianCalendar();
					int hora, minutos, segundos;
					hora =calendario.get(Calendar.HOUR_OF_DAY);
					minutos = calendario.get(Calendar.MINUTE);
					segundos = calendario.get(Calendar.SECOND);
					String hra= hora+":"+minutos+":"+segundos;
					
				//6.Actualizamos el encabezado y lo ponemos facturado
					mm.ActualizarEncabezado(fechacjmysql, hra, Abreviado ,encab );
				//6.1 Actualizamos los movimientos de hospitalizacion a 1 de este encabezado	
					if((tipoFact.equals("0"))||(tipoFact.equals("2"))){
						mm.actualizarMovUrg(encab);
					}
				//7.Creamos la factura en cont_factura 
					String cod_cuenta_fk="";
					String estado="0";
					String fecha_factura="";
					String fecha_insercion="";
					String hora_insercion="";
					String iva="0";
					String numero_factura="";
					String observacion="-";
					String precio_factura="";
					String ret_ica="-";
					String tipo="1";
				
					MetodoCuentas mc= new MetodoCuentas();						
					try {
						rsfc=mc.BuscarDatosParaFactura(encab);
						if(rsfc.next()){
							cod_cuenta_fk=rsfc.getString(9);
							fecha_factura=rsfc.getString(4);
							fecha_insercion=rsfc.getString(7);
							hora_insercion=rsfc.getString(8);
							numero_factura=rsfc.getString(2);
							precio_factura=rsfc.getString(3);
						}
						rsfc.getStatement().getConnection().close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					mc.CrearFactura(cod_cuenta_fk, estado, fecha_factura, fecha_insercion, hora_insercion, iva, numero_factura, observacion, precio_factura, ret_ica, tipo, Abreviado);
					
				
				//8.Creamos la factura en cont_detalle_factura y en cont_cartera_plazo
					try {
						rsfc=mc.DatosFacturaDetalle(numero_factura);
						if(rsfc.next()){					
							mc.CrearDetalleFactura(rsfc.getString(1), "0", fecha_factura, "1", precio_factura, "-", "-", fecha_insercion, hora_insercion, rsfc.getString(8), "CERO PESOS");
							mc.CrearPlazoCartera(numero_factura, precio_factura,rsfc.getString(1));
						}
						rsfc.getStatement().getConnection().close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
					
				//9.Actualizamos auditoria de convenios o auditoria de facturas
					String nit="0";
					rs = mm.ValorFact(encab);
					String val="";
					try {
						while(rs.next()){
							val=rs.getString(1);
							nit=rs.getString(2);
						}
						rs.getStatement().getConnection().close();				
						rs1=mm.ServicioAdmision(venc);
						if(rs1.next()){
							out.print(rs1.getString(1));
						}
						rs1.getStatement().getConnection().close();				
					} catch (SQLException e) { 
						out.print("Error "+e);
						e.printStackTrace();
					}
					mm.ActualizarAuditoriadeConvenios(lot,val);
					
				
				//10.Consultamos si existe otro encabezado de esta misma admisionsin facturar	
					rs = mm.encaSinFacturar2(venc); 
					int numMov=0;
					try {
						while(rs.next()){
							numMov++;
						} 
						rs.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
				//11. Consultamos el codigo del primer encabezado y si tiene movimientos en estado 0 o 2	
					rs = mm.primerEncaAdmision(venc,lot);
					String enca0="";
					try {
						while(rs.next()){
							enca0=rs.getString(1);
						}
						rs.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					System.out.print("Primer encabezado activo "+enca0+" admision "+venc);
					rs = mm.movxEncabezados(enca0);
					int movEnca0=0;
					try {
						while(rs.next()){
							movEnca0++;
						}
						rs.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					} 
						
					System.out.print("Primer tipoFact "+tipoFact+" numMov "+numMov+" movEnca0 "+movEnca0);
					//hay q revisar q ponga el primer encabezado de esa eps y lo ponga en 3
					
					if((tipoFact.equals("0"))||(tipoFact.equals("2"))){//FACTURAS DE HOSPITALIZACION O AMBULATORIA
						if(numMov==1&&movEnca0==0){
							mm.ActualizarEncabezadoaeliminar(enca0);//mm.anularEncabezado(enca0);
							mm.ActualizarAdmision(venc);			
						}else{
							
							//10.Consultamos si existe otro encabezado de esta misma admisionsin facturar	
							rs = mm.encaSinFacturar3(venc,lot); 
							int numMov2=0;
							try {
								while(rs.next()){
									numMov2++;
								} 
								rs.getStatement().getConnection().close();
							} catch (SQLException e) {
								out.print("Error "+e);
								e.printStackTrace();
							}
							System.out.print("PrimerSegundo tipoFact "+tipoFact+" numMov2 "+numMov2+" movEnca0 "+movEnca0);
						if(numMov2==1&&movEnca0==0){
							mm.ActualizarEncabezadoaeliminar(enca0);//mm.anularEncabezado(enca0);
							//Aqui no actualizo la admision solo el encabezado inicial
						}
						}
					}else{
						if(tipoFact.equals("1")){//FACTURAS DE URGENCIAS
							if(numMov==0){
								mm.ActualizarAdmision(venc);
							}
						}
					}



	/***Cargar los costos de todos los medicamentos***/
					rs2=mm.ConsultarProgramasValores(enca);
					double costot=0;
					double valort=0;
					while(rs2.next()){//ME TRAIGO LOS CODIGOS DE LOS PROGRAMAS MEDICAMENTOS E INSUMOS
						rs3=mm.ConsultarProgramasCostos(venc,rs2.getString(1));
						double costop=0;
						String xxx="0";
						if(rs3.next()){//ME TRAIGO EL COSTO PROMEDIO DE CADA PROGRAMA
							//costop=costop+Double.parseDouble(rs3.getString(3));//
							if(rs3.getString(3)==null){xxx="0";}
							//if(rs3.getString(3).equals(null)){xxx="0";}
							costop=costop+Double.parseDouble(xxx);//
						}
						rs3.getStatement().getConnection().close();
						
						costot=costot+costop;
						valort=valort+Double.parseDouble(rs2.getString(3));
					}
					rs2.getStatement().getConnection().close();
					
					mm.actualizarCostosdeDispensaciont(enca,String.valueOf(valort),String.valueOf(costot)); 
					/***FIN Cargar los costos de todos los medicamentos***/
					
					/***Creacion del documento contable***/
					String conso="";
					String cons="";
					String consn="";
					rs = mm.ConsecutivosdeCuentas("10");
					try {
						if(rs.next()){
							//out.print(rs.getString(1)+"|"+rs.getString(2));
							if(rs.getString(2).length()==1){cons=("00000000"+rs.getString(2));}
							if(rs.getString(2).length()==2){cons=("0000000"+rs.getString(2));}
							if(rs.getString(2).length()==3){cons=("000000"+rs.getString(2));}
							if(rs.getString(2).length()==4){cons=("00000"+rs.getString(2));}
							if(rs.getString(2).length()==5){cons=("0000"+rs.getString(2));}
							if(rs.getString(2).length()==6){cons=("000"+rs.getString(2));}
							if(rs.getString(2).length()==7){cons=("00"+rs.getString(2));}
							if(rs.getString(2).length()==8){cons=("0"+rs.getString(2));}
							if(rs.getString(2).length()==9){cons=rs.getString(2);}
							consn=rs.getString(1)+""+cons;
							conso=rs.getString(2);
						}
						rs.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					
					 mm.CrearDocumento(annio,mes,"010",consn,fechacjmysql,"FACTURA DE VENTA "+zc,"0","0","0","",Abreviado,fechacjmysql,hra);
					
					String docu="";
					rs = mm.ConsecutivoUDocumento(consn);
					try {
						if(rs.next()){
							docu=rs.getString(1);
						}
						rs.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}	

					int ctan=Integer.parseInt(conso);
					conso=String.valueOf(ctan);
					mm.ActualizarConsecutivo(conso,"10");

					int doc2=Integer.parseInt(docu);
					String doc=String.valueOf(doc2);
					
					//BUSCAMOS LA CUENTA DE INGRESO 13050505 
					rs3=mm.ConsultarcuentaIngresoClientes();
					String ctai="0";
					String nctai="";
					if(rs3.next()){//ME TRAIGO EL COSTO PROMEDIO DE CADA PROGRAMA
						ctai=rs3.getString(1);//
						nctai=rs3.getString(2);//
					}
					rs3.getStatement().getConnection().close();
					
															
					//ME TRAIGO EL <CODIGO DE LA TABLA TERCERO CON ESTE NIT
					rs3=mm.ConsultarTercerosconNIT(nit);
					String ctat="0";
					if(rs3.next()){
						ctat=rs3.getString(1);//
					}
					rs3.getStatement().getConnection().close();
					
					//Hay q modificar el centro y subcentro y el tercero
					String sc="0";
					String cs="0";
					String cc="0";
					String scc="0";
					if(tipoFact.equals("1")){	sc="1"; 	cs="1";   cc="1"; scc="1";  }//Factura de Urgencias
					if(tipoFact.equals("0")){	sc="1"; 	cs="2";   cc="2"; scc="2"; 	}//Factura de Hospitalizacion
					if(tipoFact.equals("2")){	sc="1"; 	cs="15";  cc="3"; scc="15"; 	}//Factura de Ambulatorio y COnsulta Externa
					if(tipoFact.equals("3")){	sc="1"; 	cs="15";  cc="3"; scc="15"; 	}//Factura Capitado
									
					
					//1. Creo el detalle de ingreso 13050505
					mm.CrearDetalleDocumento(doc,ctai,sc,cs,ctat,nctai,"0",numero_factura,precio_factura,"0","0","FAC");//1ra linea
					
			        Double vtd=0.0;
					String progc="";
					String valortc="";
					String nombrec="";
					String clases="";
					rs2=mm.ConsultarProgramasValores(enca);
					while(rs2.next()){//ME TRAIGO LOS CODIGOS DE LOS PROGRAMAS MEDICAMENTOS E INSUMOS
						progc=rs2.getString(1);
						valortc=rs2.getString(3);
						nombrec=rs2.getString(4);
						clases=rs2.getString(5);
												
						//Aqui buscamos el programa la cuenta
						rs3=mm.Consultarcuentasdeprogramas(cc,scc,progc);
						String cuentapp="0";
						String cuentappc="0";
						String cuentappg="0";
						if(rs3.next()){//ME TRAIGO EL COSTO PROMEDIO DE CADA PROGRAMA
							cuentapp=rs3.getString(1);//
							cuentappc=rs3.getString(2);//
							cuentappg=rs3.getString(3);//
						}
						rs3.getStatement().getConnection().close();
						
						if(cuentapp.equals("")){cuentapp="0";}
						
						mm.CrearDetalleDocumento(doc,cuentapp,sc,cs,ctat,nombrec,"0",numero_factura,"0",valortc,"0","FAC");//demas lineas
						
						if((clases.equals("MEDICAMENTOS"))||(clases.equals("MATERIALES"))){
							
							//Consulto el costo del programa
							rs3=mm.ConsultarProgramasCostos(venc,progc);
							double costop=0;
							String xxx="0";
							if(rs3.next()){//ME TRAIGO EL COSTO PROMEDIO DE CADA PROGRAMA
								xxx=rs3.getString(3);//
								if(rs3.getString(3)==null){xxx="0";}
								costop=Double.parseDouble(xxx);//
							}
							rs3.getStatement().getConnection().close();
							
							String cps=String.valueOf(costop);
							
							mm.CrearDetalleDocumento(doc,cuentappc,sc,cs,ctat,nombrec,"0",numero_factura,cps,"0","0","FAC");//tercera linea
							mm.CrearDetalleDocumento(doc,cuentappg,sc,cs,ctat,nombrec,"0",numero_factura,"0",cps,"0","FAC");//cuarta linea
							vtd=vtd+Double.parseDouble(cps);
						}
						
						vtd=vtd+Double.parseDouble(valortc);
					}
					rs2.getStatement().getConnection().close();
						
					
					//BUSCAMOS SI HAY QUE HACER DETALLE DE COPAGO Y MODERACION
					rs3=mm.Consultarcopagoymoderacionodescuento(enca);
					String cop="0";
					String mod="0";
					if(rs3.next()){//ME TRAIGO EL COSTO PROMEDIO DE CADA PROGRAMA
						cop=rs3.getString(1);//
						mod=rs3.getString(2);//
					}
					rs3.getStatement().getConnection().close();
					
					if(!cop.equals("0")){
					//BUSCAMOS LA CUENTA DE COPAGO 42050505 
					rs3=mm.ConsultarcuentaCopago();
					String ctacop="0";
					String nctacop="";
					if(rs3.next()){//ME TRAIGO EL COSTO PROMEDIO DE CADA PROGRAMA
						ctacop=rs3.getString(1);//
						nctacop=rs3.getString(2);//
					}
					rs3.getStatement().getConnection().close();
					
					mm.CrearDetalleDocumento(doc,ctacop,sc,cs,ctat,nctacop,"0",numero_factura,cop,"0","0","FAC");//linea de copago
					}//fin si tiene copago	
					
					if(!mod.equals("0")){
						//BUSCAMOS LA CUENTA DE COPAGO 42050505 
						rs3=mm.ConsultarcuentaModeracion();
						String ctacop="0";
						String nctacop="";
						if(rs3.next()){//ME TRAIGO EL COSTO PROMEDIO DE CADA PROGRAMA
							ctacop=rs3.getString(1);//
							nctacop=rs3.getString(2);//
						}
						rs3.getStatement().getConnection().close();
						
						mm.CrearDetalleDocumento(doc,ctacop,sc,cs,ctat,nctacop,"0",numero_factura,cop,"0","0","FAC");//linea de copago
					}//fin si tiene descuento o moderacion	
						
					
					mm.ActualizarDocumentoDebitoCredito(doc, String.valueOf(vtd), String.valueOf(vtd));
					
					
					
				
					
					
					
					/***FINCreacion del documento contable***/




			}
			rsef.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
	}
	
	//////////////////FIN NUEVA FACTURACION/////////////////////
	
	
	if(va.equals("tc")){
		
		int crs=0;		
		rs2=mm.GenerarConveniosxTope();
		try {
			while(rs2.next()){
				if(crs==0){
					out.print("<table id='listFact' width='100%' border='1' class='style6'  style='font-size: 13px'>" +
							  	"<tr BGCOLOR='#D3D3D3'  style='font-weight: bold'>" +
							  		"<td width='5%' align='center'><i>Item</i></td>" +
							  		"<td width='11%' align='center'><i>Contrato</i></td>" +
							  		"<td width='24%' align='center'><i>Entidad</i></td>" +
							  		"<td width='11%' align='center'><i>Inicia</i></td>" +
							  		"<td width='8%' align='center'><i>Finaliza</i></td>" +
							  		"<td width='8%' align='center'><i>Tarifas</i></td>"+
							  		"<td width='8%' align='center'><i>Valor</i></td>" +
							  		"<td width='10%' align='center'><i>Tope</i></td>" +
							  		"<td width='10%' align='center'><i>Consumido</i></td>" +
							  		"<td width='10%' align='center'><i>Diferencia</i></td>" +
							   		"</tr>");
				}
			  	String vt=rs2.getString(7);
			  	int vrt=0;
			  	String tope="";
			  	if(vt.equals("0")){tope=" No Aplica"; vrt=0;}
			  	if(vt.equals("1")){tope="  5 Millones"; vrt=5000000;}
			  	if(vt.equals("2")){tope=" 10 Millones"; vrt=10000000;}
			  	if(vt.equals("3")){tope=" 20 Millones"; vrt=20000000;}
			  	if(vt.equals("4")){tope=" 50 Millones"; vrt=50000000;}
			  	if(vt.equals("5")){tope="100 Millones"; vrt=100000000;}
			  	
			  	String scartera ="0";
			  	String ccartera ="0";
			  	///////////Consumido desde cartera//////////////////
			  	try {
					rs =mm.ConsumidoCartera(rs2.getString(4),rs2.getString(5),rs2.getString(10));
					while(rs.next()){
						scartera = rs.getString(1);
					}
					rs.getStatement().getConnection().close();
				} catch (Exception e) {
					e.getMessage();
					e.printStackTrace();
				}
				///////////Consumido desde cartera//////////////////
				long lcc=Long.parseLong(rs2.getString(6))-Long.parseLong(scartera);
			  //	System.out.println("CCARTERA: "+ccartera);
			  	
				ccartera = String.valueOf(lcc);
				
			  	if(Long.parseLong(ccartera)>vrt){
			  		if(Long.parseLong(ccartera)>0){
					out.print("<tr>" +
					  	"<td width='5%' align='center'>"+(crs+1)+"</td>" +
					  	"<td width='11%' align='left'>"+rs2.getString(2)+"</td>" +
					  	"<td width='34%' align='left'>"+rs2.getString(3)+"</td>" +
					  	"<td width='11%' align='right'>"+rs2.getString(4)+"</td>" +
					  	"<td width='8%' align='right'>"+rs2.getString(5)+"</td>" +
					  	"<td width='8%' align='right'>"+rs2.getString(6)+"</td>" );
					rs=mm.ManualesConvenio(rs2.getString("ent_nit"));
					out.print("<td width='8%' align='right'><table border='1' class='style6'  style='font-size: 13px'>");
					while(rs.next()){
						out.print("<tr><td width='8%' align='right'>"+rs.getString("descripcion")+"</td></tr>");	
					}
					out.print("</table></td>");
					rs.getStatement().getConnection().close();
					out.print("<td width='8%' align='right'>"+tope+"</td>" +
					  	"<td width='10%' align='right' id='vEF"+crs+"'>"+scartera+"</td>" +
					  	"<td width='10%' align='center'>"+ccartera+"</td>" +
					    "</tr>");
			  		}else{
			  			out.print("<tr bgcolor='#FF0000'>" +
							  	"<td width='5%' align='center'>"+(crs+1)+"</td>" +
							  	"<td width='11%' align='left'>"+rs2.getString(2)+"</td>" +
							  	"<td width='34%' align='left'>"+rs2.getString(3)+"</td>" +
							  	"<td width='11%' align='right'>"+rs2.getString(4)+"</td>" +
							  	"<td width='8%' align='right'>"+rs2.getString(5)+"</td>" +
							  	"<td width='8%' align='right'>"+rs2.getString(6)+"</td>" );
			  			rs=mm.ManualesConvenio(rs2.getString("ent_nit"));
						out.print("<td width='8%' align='right'><table border='1' class='style6'  style='font-size: 13px'>");
						while(rs.next()){
							out.print("<tr><td width='8%' align='right'>"+rs.getString("descripcion")+"</td></tr>");	
						}
						out.print("</table></td>");
						rs.getStatement().getConnection().close();
						out.print("<td width='8%' align='right'>"+tope+"</td>" +
							  	"<td width='10%' align='right' id='vEF"+crs+"'>"+scartera+"</td>" +
							  	"<td width='10%' align='center'>"+ccartera+"</td>" +
							    "</tr>");
			  		}
			  	}else{
			  		if(Long.parseLong(ccartera)>0){
			  		out.print("<tr>" +
						  	"<td width='5%' align='center'><FONT COLOR='#FF0000'>"+(crs+1)+"</td>" +
						  	"<td width='11%' align='left'><FONT COLOR='#FF0000'>"+rs2.getString(2)+"</td>" +
						  	"<td width='34%' align='left'><FONT COLOR='#FF0000'>"+rs2.getString(3)+"</td>" +
						  	"<td width='11%' align='right' ><FONT COLOR='#FF0000'>"+rs2.getString(4)+"</td>" +
						  	"<td width='8%' align='right'><FONT COLOR='#FF0000'>"+rs2.getString(5)+"</td>" +
						  	"<td width='8%' align='right'><FONT COLOR='#FF0000'>"+rs2.getString(6)+"</td>" +
						  	"<td width='8%' align='right'><FONT COLOR='#FF0000'>"+tope+"</td>" +
						  	"<td width='10%' align='right' id='vEF"+crs+"'><FONT COLOR='#FF0000'>"+scartera+"</td>" +
						  	"<td width='10%' align='center'><FONT COLOR='#FF0000'>"+ccartera+"</td>" +
						    "</tr>");
			  		}else{
			  			out.print("<tr bgcolor='#FF0000'>" +
							  	"<td width='5%' align='center'>"+(crs+1)+"</td>" +
							  	"<td width='11%' align='left'><FONT COLOR='#FFFFFF'>"+rs2.getString(2)+"</td>" +
							  	"<td width='34%' align='left'><FONT COLOR='#FFFFFF'>"+rs2.getString(3)+"</td>" +
							  	"<td width='11%' align='right' ><FONT COLOR='#FFFFFF'>"+rs2.getString(4)+"</td>" +
							  	"<td width='8%' align='right'><FONT COLOR='#FFFFFF'>"+rs2.getString(5)+"</td>" +
							  	"<td width='8%' align='right'><FONT COLOR='#FFFFFF'>"+rs2.getString(6)+"</td>" +
							  	"<td width='8%' align='right'><FONT COLOR='#FFFFFF'>"+tope+"</td>" +
							  	"<td width='10%' align='right' id='vEF"+crs+"'><FONT COLOR='#FFFFFF'>"+scartera+"</td>" +
							  	"<td width='10%' align='center'><FONT COLOR='#FFFFFF'>"+ccartera+"</td>" +
							    "</tr>");
			  		}
			  		
			  	}
			  	
			crs++;
			}
			out.print("</table>");
			rs2.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		if(crs==0){
			out.print("No hay resultados para sus criterios de busqueda.");
		}	
	}
	
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


	//////////////////////////FACTURACION CAPITADOS////////////////
	
	if(va.equals("1C")){
		
		
		out.print("<table width='100%' class='style6'><tr><td colspan='5'><div align='center' class='style11' id='cabecera2'>Facturaci?n Capitado</div></td></tr>");
		
		out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='50%'><i><div align='center'>Empresa</div></i></td><td width='29%'><i><div align='center'>Tipo de Convenio</div></i></td><td width='7%'><i><div align='center'>Fecha Inicial</div></i></td><td width='7%'><i><div align='center'>Fecha Final</div></i></td><td width='7%'><i><div align='center'>Acci?n</div></i></td></tr>");  //
		
		out.print("<td><select  style='width:100%'  name='Ent' id='Ent' ><option value='Seleccione'>Seleccione</option>");
		rs2=mm.EmpresasC();
		try {
			while(rs2.next()){
			out.print("<option title='"+rs2.getString(8)+"' value="+rs2.getString(6)+">"+rs2.getString(8)+"</option>");
			}
			rs2.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		out.print("</td>");
		
		
		out.print("<td width='1%' align='center'>Urg<input id='urg' type='checkbox' value='cccc'/>");
		out.print("  Hosp<input id='hos' type='checkbox' value='cccc'/>");
		out.print("  Ambu<input id='amb' type='checkbox' value='cccc'/>");
		out.print("  Consul.Externa<input id='cex' type='checkbox' value='cccc'/>");
		out.print("  P&P<input id='pyp' type='checkbox' value='cccc'/></td>");
		
		out.print("<td><input name='FI' type='text' id='FI' style='width:100%' onKeyup='masca(this,patron,true,0,0,0)' onblur='vfi()'; /></td><td><input name='FF' type='text' id='FF'  style='width:100%'  onKeyup='masca(this,patron,true,0,0,0)' onblur='vff()'; /></td>");
		out.print("<td><div align='center'><input name='btnCrearEntidad' type='button' id='btnCrearEntidad' value='     Consultar     ' onclick='ConsultarPctesCapitados()' /></div></td>");
		out.print("<tr><td colspan='5'><div align='center' id='Resultados'></div></td></tr>");
		out.print("<tr><td colspan='5'><div align='center' id='ResultadosC2'></div></td></tr>");
	}

	
if(va.equals("2C")){
	String encau="";
	String admiu="";
	String sql="";
	out.print("<table width='100%' class='style6'><tr><td colspan='5'><div align='center' class='style11' id='cabecera2'>Resultados de la Busqueda</div></td></tr>");
	out.print("<table width='100%' border='0' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='6%'><i><div align='center'>Item</div></i></td><td width='50%'><i><div align='center'>Nombre del paciente</div></i></td><td width='22%'><i><div align='center'>Fecha de atencion</div></i></td><td width='22%'><i><div align='center'>Valor de la atencion</div></i></td></tr>");  //
	int cc=0;
	int tc=0;
	
	String fi=req.getParameter("fi");
	String ff=req.getParameter("ff");
	String eps=req.getParameter("eps");
	String urg=req.getParameter("urg");
	String hos=req.getParameter("hos");
	String amb=req.getParameter("amb");
	String cex=req.getParameter("cex");
	String pyp=req.getParameter("pyp");
	
	String dv8=fi.substring(0, 2);
	String mv8=fi.substring(3, 5);
	String av8=fi.substring(6, 10);
	String finew=av8+"-"+mv8+"-"+dv8;
	
	String dv9=ff.substring(0, 2);
	String mv9=ff.substring(3, 5);
	String av9=ff.substring(6, 10);
	String ffnew=av9+"-"+mv9+"-"+dv9;
			
	sql=sql+" AND fe.cod_eps="+eps;
	sql=sql+" AND a.fecha_registro BETWEEN '"+finew+"' AND '"+ffnew+"'";
	
	int swtc=0;
	
	if((urg.equals("1"))&&(hos.equals("0"))&&(amb.equals("0"))&&(cex.equals("0"))&&(pyp.equals("0"))){ sql=sql+" AND pcc.urg=1 AND fe.tipo=1 "; swtc=1;}
	if((urg.equals("0"))&&(hos.equals("1"))&&(amb.equals("0"))&&(cex.equals("0"))&&(pyp.equals("0"))){ sql=sql+" AND pcc.hos=1 AND fe.tipo=2 "; swtc=1;}
	if((urg.equals("0"))&&(hos.equals("0"))&&(amb.equals("1"))&&(cex.equals("0"))&&(pyp.equals("0"))){ sql=sql+" AND pcc.amb=1 AND fe.tipo=3 "; swtc=1;}
	if((urg.equals("0"))&&(hos.equals("0"))&&(amb.equals("0"))&&(cex.equals("1"))&&(pyp.equals("0"))){ sql=sql+" AND pcc.cex=1 AND fe.tipo=4 "; swtc=1;}
	if((urg.equals("0"))&&(hos.equals("0"))&&(amb.equals("0"))&&(cex.equals("0"))&&(pyp.equals("1"))){ sql=sql+" AND pcc.pyp=1 AND fe.tipo=4 "; swtc=1;}
	
	if(swtc==0){
		sql=sql+" AND ( ";
		
		if(urg.equals("1")){ sql=sql+" pcc.urg=1 ";
			if(hos.equals("1")){ sql=sql+" OR pcc.hos=1 ";}
			if(amb.equals("1")){ sql=sql+" OR pcc.amb=1 ";}
			if(cex.equals("1")){ sql=sql+" OR pcc.cex=1 ";}
			if(pyp.equals("1")){ sql=sql+" OR pcc.pyp=1 ";}
		}else{
			if(hos.equals("1")){ sql=sql+" pcc.hos=1 ";
				if(amb.equals("1")){ sql=sql+" OR pcc.amb=1 ";}
				if(cex.equals("1")){ sql=sql+" OR pcc.cex=1 ";}
				if(pyp.equals("1")){ sql=sql+" OR pcc.pyp=1 ";}
			}else{
				if(amb.equals("1")){ sql=sql+" pcc.amb=1 ";
					if(cex.equals("1")){ sql=sql+" OR pcc.cex=1 ";}
					if(pyp.equals("1")){ sql=sql+" OR pcc.pyp=1 ";}
				}else{
					if(cex.equals("1")){ sql=sql+" pcc.cex=1 ";
						if(pyp.equals("1")){ sql=sql+" OR pcc.pyp=1 ";}
					}
				}
			}
		}
					
		sql=sql+" ) AND (";
		
		
		if(urg.equals("1")){ sql=sql+" fe.tipo=1";
			if(hos.equals("1")){ sql=sql+" OR fe.tipo=2 ";}
			if(amb.equals("1")){ sql=sql+" OR fe.tipo=3 ";}
			if(cex.equals("1")){ sql=sql+" OR fe.tipo=4 ";}
			if(pyp.equals("1")){ sql=sql+" OR fe.tipo=4 ";}
		}else{
			if(hos.equals("1")){ sql=sql+" fe.tipo=2 ";
				if(amb.equals("1")){ sql=sql+" OR fe.tipo=3 ";}
				if(cex.equals("1")){ sql=sql+" OR fe.tipo=4 ";}
				if(pyp.equals("1")){ sql=sql+" OR fe.tipo=4 ";}
			}else{
				if(amb.equals("1")){ sql=sql+" fe.tipo=3 ";
					if(cex.equals("1")){ sql=sql+" OR fe.tipo=4 ";}
					if(pyp.equals("1")){ sql=sql+" OR fe.tipo=4 ";}
				}else{
					if(cex.equals("1")){ sql=sql+" fe.tipo=4 ";
						if(pyp.equals("1")){ sql=sql+" OR fe.tipo=4 ";}
					}
				}
			}
		}
					
		sql=sql+" ) ";
	}
	
	String sqlnew="";
	sqlnew=sqlnew+sql;
	
	rs2=mm.BuscarPacientesC(sql);
	try {
		while(rs2.next()){
			cc++;
			out.print("<tr onMouseOver=this.style.background='D3D3D3' onmouseout=this.style.background='white'  >");
			out.print("<td><div align='center'>"+cc+"</div></td>");
			out.print("<td><div align='left'>"+rs2.getString(2)+"</div></td>");
			out.print("<td><div align='center'>"+rs2.getString(7)+"</div></td>");	
			rs3=mm.BuscarPacientesCSinCargue(rs2.getString(6));
			if(rs3.next()){
				out.print("<td><div align='right' >"+formatMoneda(rs3.getString(1))+"</div></td>");
				tc=tc+Integer.parseInt(rs3.getString(1));
			}else{
				out.print("<td><FONT COLOR='#FF0000'><div align='right'>0</div></td>");
			}
			rs3.getStatement().getConnection().close();
			
			out.print("</tr>");	
			
			encau=rs2.getString(6);
			admiu=rs2.getString(1);
		}
		rs2.getStatement().getConnection().close();
	} catch (SQLException e) {
		out.print("Error "+e);
		e.printStackTrace();
	}

	out.print("<input type=hidden id='vtc'  value="+tc+"></td>");
	out.print("<input type=hidden id='encau'  value="+encau+"></td>");
	out.print("<input type=hidden id='admiu'  value="+admiu+"></td>");
	
	out.print("<tr></tr><tr></tr><tr><td colspan='3'><div align='right' style='font: bold 15px '>TOTAL:</div></td><td><div align='right' style='font: bold 15px '>"+formatMoneda(String.valueOf(tc))+"</div></td></tr>");
	out.print("<tr><td><textarea id='sql1' style='visibility:hidden'>"+sqlnew+"</textarea></td></tr>");
	}


	if(va.equals("2C2")){
		if(!xx.equals("0")){
		String vtc=req.getParameter("vtc");
		String fi=req.getParameter("fi");
		String ff=req.getParameter("ff");
		String eps=req.getParameter("eps");
		String tipoFact=req.getParameter("tipoFact");
		
		String vc="";
		String nc="";
		rs2=mm.Convenios(eps);
		try {
			if(rs2.next()){
				nc=rs2.getString(2);
				vc=rs2.getString(3);
			}
			rs2.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		
		String cfc="";
		rs2=mm.ConceptoCapitado();
		try {
			if(rs2.next()){
				cfc=rs2.getString(1);
			}
			rs2.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		
		out.print("<table width='100%' border='0' class='style6' ><tr><td width='100%'></td></tr>");  
		out.print("<table width='100%' border='0' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='10%'><i><div align='center'>Valor Consumido de la capita</div></i></td><td width='10%'><i><div align='center'>Valor del Convenio / Valor de la Factura</div></i></td><td width='10%'><i><div align='center'>Diferencia</div></i></td><td width='70%'><i><div align='center'>Concepto de la Factura</div></i></td></tr>");  //
		
		
		out.print("<tr><td><input type=text id='valor0' value='"+vtc+"' readonly='readonly' style='width:100%' ></td>");
		out.print("<td><input type=text id='valor1' value='"+vc+"'  onkeyup='checknumcapita(1,"+vtc+")' style='width:100%'></td>");
		int vgop=Integer.parseInt(vc)-Integer.parseInt(vtc);
		if(vgop<0){
			out.print("<td><div id='diferencia'><FONT COLOR='#FF0000'>"+formatMoneda(String.valueOf(vgop))+"</div></td>");
		}else{
			out.print("<td>"+formatMoneda(String.valueOf(vgop))+"</td>");
		}
		
		out.print("<td><input type=text id='cfce' value='"+cfc+" correspondientes al periodo de "+fi+" hasta el "+ff+" seg?n contrato No. "+nc+" '  onkeyup='fffff(1)' style='width:100%'></td></tr>");
		
		out.print("<tr></tr><tr></tr><tr><td  colspan='4'><div align='center'><input name='btnCrearEntidad' type='button' value='     Facturar     '  onclick='FactC("+eps+","+tipoFact+")' /></div></td>");
		}
	}
	
	

	
if(va.equals("3C")){
		
	String hsql=req.getParameter("hsql");
	String encau=req.getParameter("encau");
	String admiu=req.getParameter("admiu");
	String fi=req.getParameter("fi");
	String ff=req.getParameter("ff");
	String cfc=req.getParameter("cfc");
	String vfc=req.getParameter("vfc");
	String usuario=req.getParameter("usuario");
	String eps=req.getParameter("eps");
	String tipoFact=req.getParameter("tipoFact");
	
		String dv8=fi.substring(0, 2);
		String mv8=fi.substring(3, 5);
		String av8=fi.substring(6, 10);
		String finew=av8+"-"+mv8+"-"+dv8;
		
		String dv9=ff.substring(0, 2);
		String mv9=ff.substring(3, 5);
		String av9=ff.substring(6, 10);
		String ffnew=av9+"-"+mv9+"-"+dv9;
	  
	System.out.println("Codigo del encabezado a copiar el encabezado para la capita: "+encau);	
	rs = mm.obtenerInfoEncaEnca(encau);
	try {
		if(rs.next()){//CREO EL ENCABEZADO DE LA FACTURA CAPITADA
			 mm.duplicarEncabezado(rs.getString(2),
					 rs.getString(3),
					 rs.getString(4),
					 rs.getString(5),
					 rs.getString(6),
					 rs.getString(7),
					 rs.getString(8),
					 rs.getString(3),
					 rs.getString(4),
					 rs.getString(5),
					 rs.getString(6),
					 "Cotizante",
					 "0",
					 finew,
					 rs.getString(17),
					 "",
					 "",
					 "5",
					 ffnew);	
		}
		rs.getStatement().getConnection().close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	 Calendar calendario = Calendar.getInstance();
			int hora, minutos, segundos;
			hora =calendario.get(Calendar.HOUR_OF_DAY);
			minutos = calendario.get(Calendar.MINUTE);
			segundos = calendario.get(Calendar.SECOND);
			String hra= hora+":"+minutos+":"+segundos;
			
				
	rs = mm.obtenerInfoEncaDuplica(admiu,eps);//BUSCAMOS EL ULTIMO ENCABEZADO Y CREAMOS UN DETALLE
	String encaNuevo="";
	try {
		if(rs.next()){
			 encaNuevo=rs.getString(1);
			 System.out.println("Valor encanuevo"+rs.getString(1));
			 mm.CargarDetalle(finew,hra,"1","0","",cfc,null,null,"Servicios",finew,"1",vfc,usuario,encaNuevo,"","0","","0");
		}
		rs.getStatement().getConnection().close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   
	mm.Valor(venc, eps, vfc, subtl,encaNuevo);//Actualizo el valor del encabezado
	

	///////////////FACTURAMOS EL ENCABEZADO NUEVO////////////////
	encab=encaNuevo;
	Abreviado=usuario;
	System.out.println("Valor encanuevo para la factura"+encab+" Y el usuario es: "+Abreviado);
	try{
	//2. Consultamos ultimo consecutivo de numeradas
	String nf="";
	String sf="";
	rs2=mm.NumFacturasMultiples(tipoFact);
	if(rs2.next()){
		System.out.println(rs2.getString("consecutivo"));
		nf=rs2.getString(1);
		sf=rs2.getString(2);
	}
	rs2.getStatement().getConnection().close();
	
	//3. Aumentamos el consecutivo de numeradas
	int z=Integer.parseInt(nf)+1;
	String zc=String.valueOf(z);

	mm.ActualizarNumFacturasMultiples(zc,tipoFact);
	
	zc=sf+zc;
		
	
	//4. Ingresamos a facturas numeradas
	mm.AsignarNumFact(fechacjmysql,encab,zc);
	
	//5.Ingresamos en fact_movfacturas
	rs2=mm.BuscarCodFactNumerada(fechacjmysql,encab,zc);
	String codfactNum="";
	if(rs2.next()){
		codfactNum=rs2.getString(1);
		mm.recordEstadoFact(codfactNum, "0", fechacjmysql, Abreviado);
	}
	rs2.getStatement().getConnection().close();
	
	//6.Actualizamos el encabezado y lo ponemos facturado
	mm.ActualizarEncabezado(fechacjmysql, hra, Abreviado ,encab );
	//6.1 Actualizamos los movimientos de hospitalizacion a 1 de este encabezado	
	mm.actualizarMovUrg(encab);
	
	

	//Creamos la Agrupacion de encabezados a la factura capitadada
	rs = mm.obtenerPacientesCapita(hsql);//BUSCAMOS TODOS LOS ENCABEZADOS
	try {
		while(rs.next()){
			mm.InsertarAgrupacionCapitada(codfactNum,rs.getString(1),fechacjmysql,hra,"1");//1 xq es capitado, el valor 0 x defecto es agrupacion
		}
		rs.getStatement().getConnection().close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

		
	//ACTUALIZAMOS LOS ENCABEZADOS Y LOS ACTUALIZAMOS LAS ADMISIONES COMO FACTURADOS
	mm.ActualizarEncayAdmisionesCapitadas(hsql);
	
	
	//7.Creamos la factura en cont_factura 
	String cod_cuenta_fk="";
	String estado="0";
	String fecha_factura="";
	String fecha_insercion="";
	String hora_insercion="";
	String iva="0";
	String numero_factura="";
	String observacion="-";
	String precio_factura="";
	String ret_ica="-";
	String tipo="1";

	MetodoCuentas mc= new MetodoCuentas();						
	try {
		rsfc=mc.BuscarDatosParaFactura(encab);
		if(rsfc.next()){
			cod_cuenta_fk=rsfc.getString(9);
			fecha_factura=rsfc.getString(4);
			fecha_insercion=rsfc.getString(7);
			hora_insercion=rsfc.getString(8);
			numero_factura=rsfc.getString(2);
			precio_factura=rsfc.getString(3);
		}
		rsfc.getStatement().getConnection().close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	mc.CrearFactura(cod_cuenta_fk, estado, fecha_factura, fecha_insercion, hora_insercion, iva, numero_factura, observacion, precio_factura, ret_ica, tipo, Abreviado);

	//8.Creamos la factura en cont_detalle_factura y en cont_cartera_plazo
	try {
		rsfc=mc.DatosFacturaDetalle(numero_factura);
		if(rsfc.next()){					
			mc.CrearDetalleFactura(rsfc.getString(1), "0", fecha_factura, "1", precio_factura, "-", "-", fecha_insercion, hora_insercion, rsfc.getString(8), "CERO PESOS");
			mc.CrearPlazoCartera(numero_factura, precio_factura,rsfc.getString(1));
		}
		rsfc.getStatement().getConnection().close();
	} catch (SQLException e1) {
		e1.printStackTrace();
	}
	
	
	//9.Actualizamos auditoria de convenios o auditoria de facturas
	rs = mm.ValorFact(encab);
	String val="";
	try {
		while(rs.next()){
			val=rs.getString(1);
		}
		rs.getStatement().getConnection().close();						
	} catch (SQLException e) { 
		out.print("Error "+e);
		e.printStackTrace();
	}
	mm.ActualizarAuditoriadeConvenios(eps,val);
			
	out.print(encab);//Mandamos el encabezado para imprimir el reporte
	
	
	/***FALTA HACER EL MOVIMIENTO CONTABLE**/
	
	} catch (SQLException e) {
	out.print("Error "+e);
	e.printStackTrace();
	}
	}

	//////////////////////////FIN FACTURACION CAPITADOS////////////////
	
	
	////////////////////////////PACIENTES CAPITADOS/////////////////////7
	
	if(va.equals("1PC")){
		
		out.print("<table width='100%' class='style6'><tr><td colspan='5'><div align='center' class='style11' id='cabecera2'>Subir Pacientes Capitados</div></td></tr>");
		out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' >");
		out.print("<td ><input type='file' id='archivo' name='archivo' style='width:469px' />");
		out.print("<td ><div align='center'><input name='btnCrearEntidad' type='button' value='     Subir     '  onclick='Subir()' /></div></td>");
	
	}
	
	
	////////////////////FIN PACIENTES CAPITADOS////////////////////////////////////////////////



////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	

	

	//////////////////////////FACTURACION AGRUPADA////////////////
	
	
	if(va.equals("1A")){
		
		
		out.print("<table width='100%' class='style6'><tr><td colspan='5'><div align='center' class='style11' id='cabecera2'>Facturaci�n Agrupada</div></td></tr>");
		
		out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='50%'><i><div align='center'>Empresa</div></i></td><td width='29%'><i><div align='center'>Tipo de Ingreso</div></i></td><td width='7%'><i><div align='center'>Fecha Inicial</div></i></td><td width='7%'><i><div align='center'>Fecha Final</div></i></td><td width='7%'><i><div align='center'>Acci�n</div></i></td></tr>");  //
		
		out.print("<td><select  style='width:100%'  name='Ent' id='Ent' ><option value='Seleccione'>Seleccione</option>");
		rs2=mm.EmpresasA();
		try {
			while(rs2.next()){
			out.print("<option title='"+rs2.getString(6)+"' value="+rs2.getString(4)+">"+rs2.getString(6)+"</option>");
			}
			rs2.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		out.print("</td>");
		
		
		out.print("<td width='1%' align='center'>Urg<input id='urg' type='checkbox' value='cccc'/>");
		out.print("  Hosp<input id='hos' type='checkbox' value='cccc'/>");
		out.print("  Ambu<input id='amb' type='checkbox' value='cccc'/>");
		out.print("  Consul.Externa<input id='cex' type='checkbox' value='cccc'/>");
		out.print("  P&P<input id='pyp' type='checkbox' value='cccc'/></td>");
		
		out.print("<td><input name='FI' type='text' id='FI' style='width:100%' onKeyup='masca(this,patron,true,0,0,0)' onblur='vfi()'; /></td><td><input name='FF' type='text' id='FF'  style='width:100%'  onKeyup='masca(this,patron,true,0,0,0)' onblur='vff()'; /></td>");
		out.print("<td><div align='center'><input name='btnCrearEntidad' type='button' id='btnCrearEntidad' value='     Consultar     ' onclick='ConsultarPctesAgrupados()' /></div></td>");
		out.print("<tr><td colspan='5'><div align='center' id='Resultados'></div></td></tr>");
		out.print("<tr><td colspan='5'><div align='center' id='ResultadosC2'></div></td></tr>");
	}
	
	
		if(va.equals("2A")){
		String encau="";
		String admiu="";
		String sql="";
		out.print("<table width='100%' class='style6'><tr><td colspan='5'><div align='center' class='style11' id='cabecera2'>Resultados de la Busqueda</div></td></tr>");
		out.print("<table width='100%' border='0' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='5%'><i><div align='center'>Item <input type='checkbox' checked='true' name='checkbox'  id='chkAll' checked='true'  value='ffff' onclick='CheckedoNo(this,&quot;TODOS&quot;)' /></div></i></td><td width='25%'><i><div align='center'>Nombre del paciente</div></i></td><td width='40%'><i><div align='center'>Servicio Prestado</div></i></td><td width='10%'><i><div align='center'>Fecha Atencion</div></i></td><td width='10%'><i><div align='center'>Copago</div></i></td><td width='10%'><i><div align='center'>Valor Atencion</div></i></td></tr>");  //
		
		int cc=0;
		int tc=0;
		int tcopago=0;
		
		String fi=req.getParameter("fi");
		String ff=req.getParameter("ff");
		String eps=req.getParameter("eps");
		String urg=req.getParameter("urg");
		String hos=req.getParameter("hos");
		String amb=req.getParameter("amb");
		String cex=req.getParameter("cex");
		String pyp=req.getParameter("pyp");
		
		String dv8=fi.substring(0, 2);
		String mv8=fi.substring(3, 5);
		String av8=fi.substring(6, 10);
		String finew=av8+"-"+mv8+"-"+dv8;
		
		String dv9=ff.substring(0, 2);
		String mv9=ff.substring(3, 5);
		String av9=ff.substring(6, 10);
		String ffnew=av9+"-"+mv9+"-"+dv9;
				
		sql=sql+" AND fe.cod_eps="+eps;
		sql=sql+" AND a.fecha_registro BETWEEN '"+finew+"' AND '"+ffnew+"' ";
		
		int swtc=0;
		
		if((urg.equals("1"))&&(hos.equals("0"))&&(amb.equals("0"))&&(cex.equals("0"))&&(pyp.equals("0"))){ sql=sql+" AND fe.tipo=1 "; swtc=0;}
		if((urg.equals("0"))&&(hos.equals("1"))&&(amb.equals("0"))&&(cex.equals("0"))&&(pyp.equals("0"))){ sql=sql+" AND fe.tipo=2 "; swtc=0;}
		if((urg.equals("0"))&&(hos.equals("0"))&&(amb.equals("1"))&&(cex.equals("0"))&&(pyp.equals("0"))){ sql=sql+" AND fe.tipo=3 "; swtc=0;}
		if((urg.equals("0"))&&(hos.equals("0"))&&(amb.equals("0"))&&(cex.equals("1"))&&(pyp.equals("0"))){ sql=sql+" AND fe.tipo=4 "; swtc=0;}
		if((urg.equals("0"))&&(hos.equals("0"))&&(amb.equals("0"))&&(cex.equals("0"))&&(pyp.equals("1"))){ sql=sql+" AND fe.tipo=7 "; swtc=0;}
		
		
		System.out.println("ESTE ES SQL: "+sql);
				
		String sqlnew="";
		sqlnew=sqlnew+sql;
		sql=sql+" GROUP BY fe.cod_enc_factura"; 
		
		if(swtc==1){
			rs2=mm.BuscarPacientesACE(sql);
			}else{
			rs2=mm.BuscarPacientesAOT(sql);	
			}
		
		try {
			while(rs2.next()){         
				cc++;
				out.print("<tr onMouseOver=this.style.background='D3D3D3' onmouseout=this.style.background='white'  >");
				out.print("<td><div align='center'><input id='check"+cc+"' type='checkbox' checked='true' onclick='Seleccionacheck(this)' value='"+rs2.getString(6)+"'/>" +cc+"</div></td>");
				out.print("<td><div align='left'>"+rs2.getString(2)+"</div></td>");
				out.print("<td><div align='left'>"+rs2.getString(8)+"</div></td>");	
				out.print("<td><div align='center'>"+rs2.getString(7)+"</div></td>");
				out.print("<td><div align='right'>"+formatMoneda(rs2.getString(10))+"</div></td>");
				out.print("<td><div align='right'>"+formatMoneda(rs2.getString(9))+"</div></td>");
				out.print("</tr>");	
				tc=tc+Integer.parseInt(rs2.getString(9));
				tcopago=tcopago+Integer.parseInt(rs2.getString(10));
				encau=rs2.getString(6);
				System.out.println("Encabezado "+cc+": "+encau);
				
				admiu=rs2.getString(1);
			}
			rs2.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		
		//  ya el check tiene el encabezado como value hay q hacer el facturar q recorra escoja cada encabezado lo cambie a facturado y cambie la admision cambie dinamicamente el subtotal, total y copago usando los div y genere la factura
		
		int vtmc=tc-tcopago;

		out.print("<input type=hidden id='cc'  value="+cc+"></td>");
		out.print("<input type=hidden id='vtc'  value="+vtmc+"></td>");
		out.print("<input type=hidden id='encau'  value="+encau+"></td>");
		out.print("<input type=hidden id='admiu'  value="+admiu+"></td>");
		out.print("<input type=hidden id='copago'  value="+tcopago+"></td>");
		out.print("<tr></tr><tr></tr><tr><td colspan='5'><div align='right' style='font: bold 13px '>SubTotal:</div></td><td><div align='right' style='font: bold 15px ' id='subtpc' >"+formatMoneda(String.valueOf(tc))+"</div></td></tr>");
		out.print("<tr></tr><tr></tr><tr><td colspan='5'><div align='right' style='font: bold 13px '>Copago:</div></td><td><div align='right' style='font: bold 15px '  id='copagpc' >"+formatMoneda(String.valueOf(tcopago))+"</div></td></tr>");
		out.print("<tr></tr><tr></tr><tr><td colspan='5'><div align='right' style='font: bold 15px '>TOTAL:</div></td><td><div align='right' style='font: bold 15px '  id='totalpc' >"+formatMoneda(String.valueOf(vtmc))+"</div></td></tr>");
		out.print("<tr><td><textarea id='sql1' style='visibility:hidden'>"+sqlnew+"</textarea></td></tr>");
	}
	
		if(va.equals("4A")){
		
		int totales=0;
		int copagos=0;
		
		StringTokenizer ele5;  
		ele5 = new StringTokenizer(nivel,"|"); 
		while(ele5.hasMoreTokens()){ //BUSCAMOS TODOS LOS ENCABEZADOS
			String encabezado2=ele5.nextToken();
			//System.out.println("nextToken2: "+encabezado2+" CrearDetalleDocumento");
		
			rs2=mm.Consultarcopagoytotal(encabezado2);
			try {
				if(rs2.next()){
					totales=totales+Integer.parseInt(rs2.getString(1));
					copagos=copagos+Integer.parseInt(rs2.getString(2));
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
		}//fin while
		
				
		out.print(totales+"|"+copagos+"|"+(totales-copagos));
		}

	
	if(va.equals("2A2")){
		if(!xx.equals("0")){
		//String fi=req.getParameter("fi");
		//String ff=req.getParameter("ff");
		String eps=req.getParameter("eps");
		String tipoFact=req.getParameter("tipoFact");
				
		
		String cfc="";
		rs2=mm.ConceptoAgrupado();
		try {
			if(rs2.next()){
				cfc=rs2.getString(1);
			}
			rs2.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		
		out.print("<table width='100%' border='0' class='style6' ><tr><td width='100%'></td></tr>");  //
		out.print("<table width='100%' border='0' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='100%'><i><div align='center'>Concepto de la Factura</div></i></td></tr>");  //
			
			
		out.print("<td><input type=text id='cfce' value='"+cfc+"'   style='width:100%'></td></tr>");
		
		out.print("<tr></tr><tr></tr><tr></tr><tr></tr><tr><td  colspan='4'><div align='center'><input name='btnCrearEntidad' type='button' id='botonagrupado' value='  Generar  Factura    '  onclick='FactA("+eps+","+tipoFact+")' /></div></td>");
		}
	}
	
	
	
	if(va.equals("3A")){
				
		//String hsql=req.getParameter("hsql");
		String encau=req.getParameter("encau");
		String admiu=req.getParameter("admiu");
		String fi=req.getParameter("fi");
		String ff=req.getParameter("ff");
		String usuario=req.getParameter("usuario");
		String eps=req.getParameter("eps");
		String cfc=req.getParameter("cfc");	
		String vfc=req.getParameter("vfc");
		String tipoFact=req.getParameter("tipoFact");
		
		
		String dv8=fi.substring(0, 2);
		String mv8=fi.substring(3, 5);
		String av8=fi.substring(6, 10);
		String finew=av8+"-"+mv8+"-"+dv8;
		
		String dv9=ff.substring(0, 2);
		String mv9=ff.substring(3, 5);
		String av9=ff.substring(6, 10);
		String ffnew=av9+"-"+mv9+"-"+dv9;
		  
		//System.out.println("Codigo del encabezado a copiar el encabezado para la capita: "+encau);	
		rs = mm.obtenerInfoEncaEnca(encau);
		try {
			if(rs.next()){//CREO EL ENCABEZADO DE LA FACTURA CAPITADA
				 mm.duplicarEncabezado(rs.getString(2),						 
						 rs.getString(3),
						 rs.getString(4),
						 rs.getString(5),
						 rs.getString(6),
						 rs.getString(7),
						 rs.getString(8),
						 rs.getString(3),
						 rs.getString(4),
						 rs.getString(5),
						 rs.getString(6),
						 "Cotizante",
						 "0",
						 finew,
						 rs.getString(17),
						 "",
						 "",
						 "6",
						 ffnew);	
			}
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Calendar calendario = Calendar.getInstance();
		//	Calendar calendario = new GregorianCalendar();
		int hora, minutos, segundos;
		hora =calendario.get(Calendar.HOUR_OF_DAY);
		minutos = calendario.get(Calendar.MINUTE);
		segundos = calendario.get(Calendar.SECOND);
		String hra= hora+":"+minutos+":"+segundos;		
		
		rs = mm.obtenerInfoEncaDuplica(admiu,eps);//BUSCAMOS EL ULTIMO ENCABEZADO Y CREAMOS UN DETALLE
		String encaNuevo="";
		try {
			if(rs.next()){
				 encaNuevo=rs.getString(1);
				 //System.out.println("Valor encanuevo"+rs.getString(1));
				 mm.CargarDetalle(finew,hra,"1","0","",cfc,null,null,"Servicios",finew,"1",vfc,usuario,encaNuevo,"","0","","0");	 															                    
			}
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
		mm.Valor(venc, eps, vfc, subtl,encaNuevo);//Actualizo el valor del encabezado vfc,copag
		mm.Copago(venc, eps, copag,encaNuevo);//Actualizo el copago del encabezado
		mm.Efectivo(venc, eps, copag,encaNuevo);//Actualizo el efectivo del encabezado
		
		
		///////////////FACTURAMOS EL ENCABEZADO NUEVO////////////////
		
		
	
		/***SUPER METODO CONSECUTIVOS DE FACTURAS MULTIPLES***/
		encab=encaNuevo;
		Abreviado=usuario;
		System.out.println("Valor encanuevo para la factura"+encab+" Y el usuario es: "+Abreviado);
		try{
		//2. Consultamos ultimo consecutivo de numeradas
		String nf="";
		String sf="";
		rs2=mm.NumFacturasMultiples(tipoFact);
		if(rs2.next()){
			nf=rs2.getString(1);
			sf=rs2.getString(2);
		}
		rs2.getStatement().getConnection().close();
		
				
		//3. Aumentamos el consecutivo de numeradas
		int z=Integer.parseInt(nf)+1;
		String zc=String.valueOf(z);

		mm.ActualizarNumFacturasMultiples(zc,tipoFact);
		
		zc=sf+zc;
		
		//4. Ingresamos a facturas numeradas
		mm.AsignarNumFact(fechacjmysql,encab,zc);
	
		
		
		//5.Ingresamos en fact_movfacturas
		rs2=mm.BuscarCodFactNumerada(fechacjmysql,encab,zc);
		String codfactNum="";
		if(rs2.next()){
			codfactNum=rs2.getString(1);
			mm.recordEstadoFact(codfactNum, "0", fechacjmysql, Abreviado);
		}
		rs2.getStatement().getConnection().close();
		
		//6.Actualizamos el encabezado y lo ponemos facturado
		mm.ActualizarEncabezado(fechacjmysql, hra, Abreviado ,encab );
		//6.1 Actualizamos los movimientos de hospitalizacion a 1 de este encabezado	
		mm.actualizarMovUrg(encab);
		
	
		//Creamos la Agrupacion de encabezados a la factura agrupada
		String textodc=texto;
		//String textodcn=texto;
		StringTokenizer ele;  
		ele = new StringTokenizer(texto,"|"); 
		while(ele.hasMoreTokens()){ //BUSCAMOS TODOS LOS ENCABEZADOS
			String encabezado=ele.nextToken();
			System.out.println("nextToken: "+encabezado+" InsertarAgrupacionCapitada");
			
			mm.InsertarAgrupacionCapitada(codfactNum,encabezado,fechacjmysql,hra,"0");// 0 x defecto es agrupacion
			
			//System.out.println("nextToken: "+encabezado+" ActualizarEncayAdmisionesAgrupadas");
			
			//ACTUALIZAMOS LOS ENCABEZADOS Y LOS ACTUALIZAMOS LAS ADMISIONES COMO FACTURADOS
			mm.ActualizarEncayAdmisionesAgrupadas(encabezado);
			
		}
						
		
		
		//7.Creamos la factura en cont_factura 
		String cod_cuenta_fk="";
		String estado="0";
		String fecha_factura="";
		String fecha_insercion="";
		String hora_insercion="";
		String iva="0";
		String numero_factura="";
		String observacion="-";
		String precio_factura="";
		String ret_ica="-";
		String tipo="1";
		
		MetodoCuentas mc= new MetodoCuentas();						
		try {
			rsfc=mc.BuscarDatosParaFactura(encab);
			if(rsfc.next()){
				cod_cuenta_fk=rsfc.getString(9);
				fecha_factura=rsfc.getString(4);
				fecha_insercion=rsfc.getString(7);
				hora_insercion=rsfc.getString(8);
				numero_factura=rsfc.getString(2);
				precio_factura=rsfc.getString(3);
			}
			rsfc.getStatement().getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		mc.CrearFactura(cod_cuenta_fk, estado, fecha_factura, fecha_insercion, hora_insercion, iva, numero_factura, observacion, precio_factura, ret_ica, tipo, Abreviado);

		//8.Creamos la factura en cont_detalle_factura y en cont_cartera_plazo
		try {
			rsfc=mc.DatosFacturaDetalle(numero_factura);
			if(rsfc.next()){					
				mc.CrearDetalleFactura(rsfc.getString(1), "0", fecha_factura, "1", precio_factura, "-", "-", fecha_insercion, hora_insercion, rsfc.getString(8), "CERO PESOS");
				mc.CrearPlazoCartera(numero_factura, precio_factura,rsfc.getString(1));
			}
			rsfc.getStatement().getConnection().close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		//9.Actualizamos auditoria de convenios o auditoria de facturas
		String nit="0";
		rs = mm.ValorFact(encab);
		String val="";
		try {
			while(rs.next()){
				val=rs.getString(1);
				nit=rs.getString(2);
			}
			rs.getStatement().getConnection().close();						
		} catch (SQLException e) { 
			out.print("Error "+e);
			e.printStackTrace();
		}
		mm.ActualizarAuditoriadeConvenios(eps,val);
					
		out.print(encab);//Mandamos el encabezado para imprimir el reporte
				
	
		/*
		
	///Cargar los costos de todos los medicamentos
					rs2=mm.ConsultarProgramasValores(enca);
					double costot=0;
					double valort=0;
					while(rs2.next()){//ME TRAIGO LOS CODIGOS DE LOS PROGRAMAS MEDICAMENTOS E INSUMOS
						rs3=mm.ConsultarProgramasCostos(venc,rs2.getString(1));
						double costop=0;
						String xxx="0";
						if(rs3.next()){//ME TRAIGO EL COSTO PROMEDIO DE CADA PROGRAMA
							//costop=costop+Double.parseDouble(rs3.getString(3));//
							if(rs3.getString(3)==null){xxx="0";}
							//if(rs3.getString(3).equals(null)){xxx="0";}
							costop=costop+Double.parseDouble(xxx);//
						}
						rs3.getStatement().getConnection().close();
						
						costot=costot+costop;//costo de los medicamentos dispensados
						valort=valort+Double.parseDouble(rs2.getString(3));//valor facturado de los medicamentos dispensados
					}
					rs2.getStatement().getConnection().close();
					
					mm.actualizarCostosdeDispensaciont(enca,String.valueOf(valort),String.valueOf(costot)); 
					///FIN Cargar los costos de todos los medicamentos**
		*/
		
		
		
					String conso="";
					String cons="";
					String consn="";
					rs = mm.ConsecutivosdeCuentas("10");
					try {
						if(rs.next()){
							//out.print(rs.getString(1)+"|"+rs.getString(2));
							if(rs.getString(2).length()==1){cons=("00000000"+rs.getString(2));}
							if(rs.getString(2).length()==2){cons=("0000000"+rs.getString(2));}
							if(rs.getString(2).length()==3){cons=("000000"+rs.getString(2));}
							if(rs.getString(2).length()==4){cons=("00000"+rs.getString(2));}
							if(rs.getString(2).length()==5){cons=("0000"+rs.getString(2));}
							if(rs.getString(2).length()==6){cons=("000"+rs.getString(2));}
							if(rs.getString(2).length()==7){cons=("00"+rs.getString(2));}
							if(rs.getString(2).length()==8){cons=("0"+rs.getString(2));}
							if(rs.getString(2).length()==9){cons=rs.getString(2);}
							consn=rs.getString(1)+""+cons;
							conso=rs.getString(2);
						}
						rs.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					
					
					int vtdocu=Integer.parseInt(vfc)+Integer.parseInt(copag);
					mm.CrearDocumento(annio,mes,"010",consn,fechacjmysql,"FACTURA DE VENTA "+zc,String.valueOf(vtdocu),String.valueOf(vtdocu),"0","",Abreviado,fechacjmysql,hra);
					
					String docu="";
					rs = mm.ConsecutivoUDocumento(consn);
					try {
						if(rs.next()){
							docu=rs.getString(1);
							//out.print("<input type=hidden id='docuh'  value="+rs.getString(1)+"></td>");
						}
						rs.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}	

					int ctan=Integer.parseInt(conso)+1;
					conso=String.valueOf(ctan);
					mm.ActualizarConsecutivo(conso,"10");

					int doc2=Integer.parseInt(docu);
					String doc=String.valueOf(doc2);
					
					//BUSCAMOS LA CUENTA DE INGRESO 13050505 
					rs3=mm.ConsultarcuentaIngresoClientes();
					String ctai="0";
					String nctai="";
					if(rs3.next()){//ME TRAIGO EL CODIGO DE LA CUENTA DE INGRESO
						ctai=rs3.getString(1);//
						nctai=rs3.getString(2);//
					}
					rs3.getStatement().getConnection().close();
					
															
					//ME TRAIGO EL <CODIGO DE LA TABLA TERCERO CON ESTE NIT
					rs3=mm.ConsultarTercerosconNIT(nit);
					String ctat="0";
					if(rs3.next()){
						ctat=rs3.getString(1);//
					}
					rs3.getStatement().getConnection().close();
				
					//Hay q modificar el centro y subcentro y el tercero
					String sc="0";
					String cs="0";
					//String cc="0";
					//String scc="0";
					
					if(tipoFact.equals("1")){	sc="1"; 	cs="1";   /*cc="1"; scc="1";*/  }//Factura de Urgencias
					if(tipoFact.equals("0")){	sc="2"; 	cs="2";   /*cc="2"; scc="2"; */	}//Factura de Hospitalizacion
					if(tipoFact.equals("2")){	sc="4"; 	cs="15";  /*cc="3"; scc="15"; */	}//Factura de Ambulatorio y COnsulta Externa
					if(tipoFact.equals("7")){	sc="4"; 	cs="17";  /*cc="5"; scc="17"; */	}//Factura P&P
									
					
					//1. Creo el detalle de ingreso 13050505
					mm.CrearDetalleDocumento(doc,ctai,sc,cs,ctat,nctai,"0",numero_factura,precio_factura,"0","0","FAC");//1ra linea
					
									
					
					
					//Actualizamos todos los estados de los encabezados a 1 para saber cual generar la agrupacion de cuentas para el detalle de documento
					StringTokenizer ele2;  
					System.out.println("qqqqqqqqqqqqqqqqqqqqqqqq "+textodc);
					ele2 = new StringTokenizer(textodc,"|"); 
					while(ele2.hasMoreTokens()){ //BUSCAMOS TODOS LOS ENCABEZADOS
						String encabezado2=ele2.nextToken(); 
						System.out.println("nextToken2: "+encabezado2+" CrearDetalleDocumento");
						mm.ActualizarMovimientosAgrupados(encabezado2,encab);
					}
					
					
					rs3=mm.ConsultarCuentasAgrupadas(fi,ff,eps,encab);
						while(rs3.next()){//
							//cop=rs3.getString(1);//
							mm.CrearDetalleDocumento(doc,rs3.getString(1),rs3.getString(2),rs3.getString(3),rs3.getString(4),rs3.getString(5),"0",numero_factura,"0",rs3.getString(6),"0","FAC");//demas lineas
						}
					rs3.getStatement().getConnection().close();
				
					
					
					if(!copag.equals("0")){
						//BUSCAMOS LA CUENTA DE COPAGO 42050505 
						rs3=mm.ConsultarcuentaCopago();
						String ctacop="0";
						String nctacop="";
						if(rs3.next()){//ME TRAIGO EL COSTO PROMEDIO DE CADA PROGRAMA
							ctacop=rs3.getString(1);//
							nctacop=rs3.getString(2);//
						}
						rs3.getStatement().getConnection().close();
						
					mm.CrearDetalleDocumento(doc,ctacop,sc,cs,ctat,nctacop,"0",numero_factura,copag,"0","0","FAC");//linea de copago
					}
			/*		
				
					Double vtd=0.0;
					String progc="";
					String valortc="";
					String nombrec="";
					String clases="";
					rs2=mm.ConsultarProgramasValores(encabezado2);
					while(rs2.next()){//ME TRAIGO LOS CODIGOS DE LOS PROGRAMAS MEDICAMENTOS E INSUMOS
						progc=rs2.getString(1);
						valortc=rs2.getString(3);
						nombrec=rs2.getString(4);
						clases=rs2.getString(5);
												
						//Aqui buscamos el programa la cuenta
						rs3=mm.Consultarcuentasdeprogramas(cc,scc,progc);
						String cuentapp="0";
						String cuentappc="0";
						String cuentappg="0";
						if(rs3.next()){//ME TRAIGO EL COSTO PROMEDIO DE CADA PROGRAMA
							cuentapp=rs3.getString(1);//
							cuentappc=rs3.getString(2);//
							cuentappg=rs3.getString(3);//
						}
						rs3.getStatement().getConnection().close();
						
						if(cuentapp.equals("")){cuentapp="0";}
						
						mm.CrearDetalleDocumento(doc,cuentapp,sc,cs,ctat,nombrec,"0",numero_factura,"0",valortc,"0","FAC");//demas lineas
						
						/**HAY Q ARREGLAR LO DE LOS MEDICAMENTOS***/
						/*if((clases.equals("MEDICAMENTOS"))||(clases.equals("MATERIALES"))){
							
							//Consulto el costo del programa
							rs3=mm.ConsultarProgramasCostos(venc,progc);
							double costop=0;
							String xxx="0";
							if(rs3.next()){//ME TRAIGO EL COSTO PROMEDIO DE CADA PROGRAMA
								xxx=rs3.getString(3);//
								if(rs3.getString(3)==null){xxx="0";}
								costop=Double.parseDouble(xxx);//
							}
							rs3.getStatement().getConnection().close();
							
							String cps=String.valueOf(costop);
							
							mm.CrearDetalleDocumento(doc,cuentappc,sc,cs,ctat,nombrec,"0",numero_factura,cps,"0","0","FAC");//tercera linea
							mm.CrearDetalleDocumento(doc,cuentappg,sc,cs,ctat,nombrec,"0",numero_factura,"0",cps,"0","FAC");//cuarta linea
							vtd=vtd+Double.parseDouble(cps);
						}*/
						
				/*		vtd=vtd+Double.parseDouble(valortc);
					}
					rs2.getStatement().getConnection().close();
						
					
					//BUSCAMOS SI HAY QUE HACER DETALLE DE COPAGO Y MODERACION
					rs3=mm.Consultarcopagoymoderacionodescuento(enca);
					String cop="0";
					String mod="0";
					if(rs3.next()){//ME TRAIGO EL COSTO PROMEDIO DE CADA PROGRAMA
						cop=rs3.getString(1);//
						mod=rs3.getString(2);//
					}
					rs3.getStatement().getConnection().close();
					
					if(!cop.equals("0")){
					//BUSCAMOS LA CUENTA DE COPAGO 42050505 
					rs3=mm.Consultarcuentasxnumero("11051040");
					String ctacop="0";
					String nctacop="";
					if(rs3.next()){//ME TRAIGO EL COSTO PROMEDIO DE CADA PROGRAMA
						ctacop=rs3.getString(1);//
						nctacop=rs3.getString(2);//
					}
					rs3.getStatement().getConnection().close();
					
					mm.CrearDetalleDocumento(doc,ctacop,sc,cs,ctat,nctacop,"0",numero_factura,cop,"0","0","FAC");//linea de copago
					}//fin si tiene copago	
					
					if(!mod.equals("0")){
						//BUSCAMOS LA CUENTA DE COPAGO 42050505 
						rs3=mm.Consultarcuentasxnumero("42503005");
						String ctacop="0";
						String nctacop="";
						if(rs3.next()){//ME TRAIGO EL COSTO PROMEDIO DE CADA PROGRAMA
							ctacop=rs3.getString(1);//
							nctacop=rs3.getString(2);//
						}
						rs3.getStatement().getConnection().close();
						
						mm.CrearDetalleDocumento(doc,ctacop,sc,cs,ctat,nctacop,"0",numero_factura,cop,"0","0","FAC");//linea de copago
					}//fin si tiene descuento o moderacion	
						
					
					mm.ActualizarDocumentoDebitoCredito(doc, String.valueOf(vtd), String.valueOf(vtd));
		
		*/
		
		
		} catch (SQLException e) {
		out.print("Error "+e);
		e.printStackTrace();
		}
		
		
	}
	
	









	

/////////////////////////////
}
	
	


	///CONVERTIR DE NUMERO A LETRAS
	
	 private final String[] UNIDADES = {"", "un ", "dos ", "tres ", "cuatro ", "cinco ", "seis ", "siete ", "ocho ", "nueve "};
	    private final String[] DECENAS = {"diez ", "once ", "doce ", "trece ", "catorce ", "quince ", "dieciseis ",
	        "diecisiete ", "dieciocho ", "diecinueve", "veinte ", "treinta ", "cuarenta ",
	        "cincuenta ", "sesenta ", "setenta ", "ochenta ", "noventa "};
	    private final String[] CENTENAS = {"", "ciento ", "doscientos ", "trecientos ", "cuatrocientos ", "quinientos ", "seiscientos ",
	        "setecientos ", "ochocientos ", "novecientos "};

	   /*public Numero_a_Letra() {
	   }*/

	    public String Convertir(String numero) {
	        String literal = "";
	        String parte_decimal;    
	        //si el numero utiliza (.) en lugar de (,) -> se reemplaza
	        numero = numero.replace(".", ",");
	        //si el numero no tiene parte decimal, se le agrega ,00
	        if(numero.indexOf(",")==-1){
	            numero = numero + "";
	        }
	        //se valida formato de entrada -> 0,00 y 999 999 999,00
	        if (Pattern.matches("\\d{1,9}", numero)) {
	            //se divide el numero 0000000,00 -> entero y decimal
	            String Num[] = numero.split(",");            
	            //de da formato al numero decimal
	            parte_decimal =  "  Pesos";
	            //se convierte el numero a literal
	            if (Integer.parseInt(Num[0]) == 0) {//si el valor es cero
	                literal = "cero ";
	            } else if (Integer.parseInt(Num[0]) > 999999) {//si es millon
	                literal = getMillones(Num[0]);
	            } else if (Integer.parseInt(Num[0]) > 999) {//si es miles
	                literal = getMiles(Num[0]);
	            } else if (Integer.parseInt(Num[0]) > 99) {//si es centena
	                literal = getCentenas(Num[0]);
	            } else if (Integer.parseInt(Num[0]) > 9) {//si es decena
	                literal = getDecenas(Num[0]);
	            } else {//sino unidades -> 9
	                literal = getUnidades(Num[0]);
	            }
	            //devuelve el resultado en mayusculas o minusculas
	           // if (mayusculas) {
	                return (literal + parte_decimal).toUpperCase();
	           // } else {
	               // return (literal + parte_decimal);
	            //}
	        } else {//error, no se puede convertir
	            return literal = null;
	        }
	    }

	    /* funciones para convertir los numeros a literales */

	    private String getUnidades(String numero) {// 1 - 9
	        //si tuviera algun 0 antes se lo quita -> 09 = 9 o 009=9
	        String num = numero.substring(numero.length() - 1);
	        return UNIDADES[Integer.parseInt(num)];
	    }

	    private String getDecenas(String num) {// 99                        
	        int n = Integer.parseInt(num);
	        if (n < 10) {//para casos como -> 01 - 09
	            return getUnidades(num);
	        } else if (n > 19) {//para 20...99
	            String u = getUnidades(num);
	            if (u.equals("")) { //para 20,30,40,50,60,70,80,90
	                return DECENAS[Integer.parseInt(num.substring(0, 1)) + 8];
	            } else {
	                return DECENAS[Integer.parseInt(num.substring(0, 1)) + 8] + "y " + u;
	            }
	        } else {//numeros entre 11 y 19
	            return DECENAS[n - 10];
	        }
	    }

	    private String getCentenas(String num) {// 999 o 099
	        if( Integer.parseInt(num)>99 ){//es centena
	            if (Integer.parseInt(num) == 100) {//caso especial
	                return " cien ";
	            } else {
	                 return CENTENAS[Integer.parseInt(num.substring(0, 1))] + getDecenas(num.substring(1));
	            } 
	        }else{//por Ej. 099 
	            //se quita el 0 antes de convertir a decenas
	            return getDecenas(Integer.parseInt(num)+"");            
	        }        
	    }

	    private String getMiles(String numero) {// 999 999
	        //obtiene las centenas
	        String c = numero.substring(numero.length() - 3);
	        //obtiene los miles
	        String m = numero.substring(0, numero.length() - 3);
	        String n="";
	        //se comprueba que miles tenga valor entero
	        if (Integer.parseInt(m) > 0) {
	            n = getCentenas(m);           
	            return n + "mil " + getCentenas(c);
	        } else {
	            return "" + getCentenas(c);
	        }

	    }

	    private String getMillones(String numero) { //000 000 000        
	        //se obtiene los miles
	        String miles = numero.substring(numero.length() - 6);
	        //se obtiene los millones
	        String millon = numero.substring(0, numero.length() - 6);
	        String n = "";
	        if(millon.length()>1){
	            n = getCentenas(millon) + "millones ";
	        }else{
	            n = getUnidades(millon) + "millon ";
	        }
	        return n + getMiles(miles);        
	    }
		

	    /////FIN CONVERTIR DE NUMERO A LETRAS 
	


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

