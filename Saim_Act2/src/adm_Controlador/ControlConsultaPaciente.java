package adm_Controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.*; //para llamar la librería 


import java.io.PrintWriter;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.io.*;

import javax.servlet.http.HttpSession;

import cont_metodo.MetodoCuentas;

import fact_bean.Tarifas;
import adm_logica.MetodoConsultaPaciente;


public class ControlConsultaPaciente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(request, response);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		HttpSession session = req.getSession(true);
		
		String va = req.getParameter("valor");
		String venc=req.getParameter("venc");
		String lot=req.getParameter("lot");
		
		
		String NombreManual=req.getParameter("NombreManual");
		String cod_ManTarFk=req.getParameter("cod_ManTarFk");
		String NombreTipoTarifa=req.getParameter("NombreTipoTarifa");
		String ClaseServicio=req.getParameter("ClaseServicio");
		String NombreCentroCosto=req.getParameter("NombreCentroCosto");
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
		String estAdm=req.getParameter("estAdm");
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
		
		String mbasec=req.getParameter("mbasec");
		String descch0=req.getParameter("descch0");
		String fechai0=req.getParameter("fechai0");
		String fechaf0=req.getParameter("fechaf0");
		String valor0=req.getParameter("valor0");
		String ct=req.getParameter("ct");
		String y=req.getParameter("y");
		String v=req.getParameter("v");
		String vFI=req.getParameter("vFI");
		String vFF=req.getParameter("vFF");
		
		
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
		
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out=res.getWriter();
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		ResultSet rs3=null;
		ResultSet rsfc=null;
		ResultSet rsef=null;
		MetodoConsultaPaciente mm = new MetodoConsultaPaciente();
		
		String formatomoneda="$###,###,###"; //declarando la variable con un tipo de formato
		DecimalFormat df=new DecimalFormat(formatomoneda); //aplicando el formato a la variable formato_moneda
		
		
		

	//encambezado para consultar listado de paciente registado en adminsiones con estado 0
	if(va.equals("30A")){
		
		out.print("<table width='100%' border='0' align='center'>" +
				"<tr>" +
				"<td colspan='4' height='30' valign='top'>" +
					"<div align='center' class='style11' id='cabecera2'>Consulta</div>" +
				"</td>" +
				"</tr>" +
				"</table>");
		out.print("<table width='100%' border=0' class='style6' ><tr BGCOLOR='#D3D3D3' ><td colspan='4' height='30' valign='top'><i><div align='center'>Criterios de Busqueda</div></i></td></tr>");
		out.print("<table width='100%' class='style6'><tr><td><div align='center'>Fecha Inicial: <input name='FI' id='FI' type='text'size='10'  onKeyup='masca(this,patron,true,0,0,0)' /></div></td><td><div align='center'>Fecha Final: <input name='FF' id='FF' type='text'size='10'  onKeyup='masca(this,patron,true,0,0,0)' /></div></td></tr></table>");
		out.print("<br>");
		out.print("<table width='100%' border='0' class='style6' ><tr><td colspan='4'><div align='center'><input name='btnCrearEntidad' type='button' id='btnCrearEntidad' value='     Consultar Pacientes  ' onclick='consulta()' /></div></td></tr><tr></tr><tr></tr></table>");
		out.print("<table width='100%' border='0' class='style6' ><tr></tr><tr BGCOLOR='#D3D3D3' ><td colspan='4' height='30' valign='top'><i><div align='center'>Resultados de Busqueda</div></i></td></tr><tr></tr></table>");
		out.print("<div id='resultadosf' style='height: 240px; overflow-y: scroll'></div>");
		
		
	}
	
	//Resultado
if(va.equals("30")){
/*	String[] fi = vFI.split("/||");
	System.out.println("fi="+fi[7]+fi[8]+fi[9]+fi[10]+"/"+fi[4]+fi[5]+"/"+fi[1]+fi[2]);*/
	
	String[] fi = vFI.split("-");
	String fi2=fi[2]+"-"+fi[1]+"-"+fi[0];
	System.out.println("fi="+fi[2]+"-"+fi[1]+"-"+fi[0]);
	
	String[] ff = vFF.split("-");
	String ff2=fi[2]+"-"+ff[1]+"-"+ff[0];
	System.out.println("ff="+fi[2]+"-"+ff[1]+"-"+ff[0]);
	
	try {
		rs=mm.Consultafecha(fi2, ff2);
		int t=0;
		out.print("<table width='100%' border='0' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='3%'><i><div align='center'>Item</div></i></td><td width='25%'><i><div align='center'>Nombres</div></i></td><td width='10%'><i><div align='center'>Nº Documento</div></i></td><td width='25%'><i><div align='center'>Pabellon - Ubicacion de Cama</div></i></td><td width='3%'><i><div align='center'>Piso</div></i></td><td width='10%'><i><div align='center'>Fecha de registro</div></i></td><td width='8%'><i><div align='center'>Hora de registro</div></i></td><td width='25%'><i><div align='center'>Registrado Por</div></i></td></tr>	");
		/*if(rs.next()){
			out.print("<table width='100%' border='0' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='3%'><i><div align='center'>Item</div></i></td><td width='25%'><i><div align='center'>Nombres</div></i></td><td width='10%'><i><div align='center'>Nº Documento</div></i></td><td width='25%'><i><div align='center'>Pabellon - Ubicacion de Cama</div></i></td><td width='3%'><i><div align='center'>Piso</div></i></td><td width='10%'><i><div align='center'>Fecha de registro</div></i></td><td width='8%'><i><div align='center'>Hora de registro</div></i></td><td width='25%'><i><div align='center'>Registrado Por</div></i></td></tr>	");*/  
			
		while (rs.next()){
			t=t+1;
			out.print("<tr><td>"+t+"</td><td> "+rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+"</td><td>"+rs.getString(4)+" "+rs.getString(5)+"</td><td>"+rs.getString(7)+" - "+rs.getString(8)+" - "+rs.getString(9)+"</td><td>"+rs.getString(10)+"</td><td>"+rs.getString(11)+"</td><td>"+rs.getString(12)+"</td><td>"+rs.getString(14)+" "+rs.getString(15)+"</td></tr>");
			}
		out.print("</table>");
		/*}else{
			out.print("No se encuentra registro con la fecha consultada");
			}*/
		rs.getStatement().getConnection().close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}}
	}}
	
	


	
	
	
	
	


	
	

	/////////////////////////////


	
	


