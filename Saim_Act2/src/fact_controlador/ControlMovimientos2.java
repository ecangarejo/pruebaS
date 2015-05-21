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
import java.io.*;

import javax.servlet.http.HttpSession;

import cont_metodo.MetodoCuentas;

import fact_bean.Tarifas;
import fact_metodo.MetodoMovimientos;


public class ControlMovimientos2 extends HttpServlet { 
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
		ResultSet rs4=null;
		ResultSet rsfc=null;
		ResultSet rsef=null;
		MetodoMovimientos mm = new MetodoMovimientos();
		
		String formatomoneda="$###,###,###"; //declarando la variable con un tipo de formato
		DecimalFormat df=new DecimalFormat(formatomoneda); //aplicando el formato a la variable formato_moneda
		
		if(va.equals("PA")){
			out.print("<table width='100%' border='0' align='center'><tr><td colspan='4' height='30' valign='top'><div align='center' class='style11' id='cabecera2'>Consulta de Pacientes Agrupados</div></td></tr></table>");
			//out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='60%'><i><div align='center'>Paciente</div></i></td><td width='30%'><i><div align='center'>Empresa</div></i></td><td width='10%'><i><div align='center'>Admisión</div></i></td></tr>");  //
			out.print("<table width='100%' border=0' class='style6' ><tr BGCOLOR='#D3D3D3' ><td colspan='4' height='30' valign='top'><i><div align='center'>Criterios de Busqueda</div></i></td></tr>");
			out.print("<tr></tr><tr><td width='15%'><div align='right'>Factura No:</div></td><td width='30%'><input name='txtFact' type='text' id='txtFact' size='39' maxlength='100' /></td>");
			out.print("<td width='15%'><div align='right'>Entidad:</div></td><td width='40%'><select  style='width:269px' name='Ent' id='Ent' ><option value='Seleccione'>Seleccione</option>");
			rs2=mm.Empresas();
			try {
				while(rs2.next()){
				out.print("<option title='"+rs2.getString(2)+"' value="+rs2.getString(1)+">"+rs2.getString(2)+"</option>");
				}
				rs2.getStatement().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("</td></tr>");
			out.print("<tr><td><div align='right'>Fecha Inicial:</div></td><td><input name='FI' type='text' id='FI' size='10'  onKeyup='masca(this,patron,true,0,0,0)' onblur='vfi()'; /></td><td><div align='right'>Fecha Final:</div></td><td><input name='FF' type='text' id='FF' size='10' onKeyup='masca(this,patron,true,0,0,0)' onblur='vff()'; /></td></tr><tr></tr></table>");
			//out.print("<tr><td><div align='right'>Primer Apellido:</div></td><td><input name='txtCodigoPrestador' type='text' id='txtCodPrestador' size='39' maxlength='50' onkeydown='A(this, event)'/><span class='style8'>*</span></td><td><div align='right'>Segundo Apellido:</div></td><td><select name='listEstado' id='listEstado' style='width: 266px;' onkeydown='A(this, event)'><option value='Activo' selected='selected'>Activo</option><option value='Inactivo'>Inactivo</option></select><span class='style8'>*</span></td></tr></table>");
			out.print("<table width='100%' border='0' class='style6' ><tr><td colspan='4'><div align='center'><input name='btnCrearEntidad' type='button' id='btnCrearEntidad' value='     Consultar Pacientes    ' onclick='CFactAgrupados()' /></div></td></tr><tr></tr><tr></tr></table>");
			out.print("<table width='100%' border='0' class='style6' ><tr></tr><tr BGCOLOR='#D3D3D3' ><td colspan='4' height='30' valign='top'><i><div align='center'>Resultados de Busqueda</div></i></td></tr><tr></tr></table>");
			out.print("<div id='resultadosf' style='height: 240px; overflow-y: scroll'></div>");
		}
		
		
		
		if(va.equals("PA1")){
			String sql="";
			String v1=req.getParameter("v1");
			if((v1==null)||(v1.equals(""))){v1="";}else{sql=sql+" AND n.consecutivo='"+v1+"'";}
			String v2=req.getParameter("v2");
			if(v2.equals("Seleccione")){v2="";}else{sql=sql+" AND ef.cod_eps="+v2;}
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
			rs2=mm.GeneraSQLAgrupado(sql);
			try {
				while(rs2.next()){
					if(crs==0){
					out.print("<table width='100%' border='0' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='7%'><i><div align='center'>No. de Factura</div></i></td><td width='15%'><i><div align='center'>Empresa</div></i></td><td width='5%'><i><div align='center'>Fecha</div></i></td></tr>	");  //
					}
				out.print("<tr><td><a  href='#'onclick='PacientesAgrupados(&quot;"+rs2.getString(4)+"&quot;)'>"+rs2.getString(1)+"</a></td><td>"+rs2.getString(2)+"</td><td>"+rs2.getString(3)+"</td></tr>");
				crs++;
				}
				rs2.getStatement().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			if(crs==0){out.print("No hay resultados para sus criterios de busqueda.");}
		}
		
		
	}
}