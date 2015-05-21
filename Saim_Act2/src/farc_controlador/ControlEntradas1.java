package farc_controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.*;

import farc_metodo.MetodoEntradas;
import farc_metodo.MetodoEntradas1;

/**
 * Servlet implementation class ControlDocumentos
 */
public class ControlEntradas1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
	}


		
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		res.setContentType("text/html;charset=UTF-8");
		HttpSession session = req.getSession(true);
		PrintWriter out=res.getWriter();
		MetodoEntradas1 md = new MetodoEntradas1();
		//MetodoEntradas me = new MetodoEntradas();
		

		///////Variables/////////////////////
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		ResultSet rs3=null;
		ResultSet rs4=null;
		String va = req.getParameter("valor");
		String texto=req.getParameter("texto");
		String Ndatos=req.getParameter("Ndatos");
		String docu=req.getParameter("docu");
		String user=req.getParameter("user");
	
		String id=req.getParameter("id");
		String ls=req.getParameter("l");


		
		int l=0;
		if(ls==null){
			ls="0";	
		}else{
		  l=Integer.parseInt(ls);
		}
		
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
		
		
		Calendar calendario = Calendar.getInstance();
		//	Calendar calendario = new GregorianCalendar();
			int hora, minutos, segundos;
			hora =calendario.get(Calendar.HOUR_OF_DAY);
			minutos = calendario.get(Calendar.MINUTE);
			segundos = calendario.get(Calendar.SECOND);
			
			String hora1 =Integer.toString(c.get(Calendar.HOUR_OF_DAY));
			String minutos1 = Integer.toString(c.get(Calendar.MINUTE)); 
			String segundos1 = Integer.toString(c.get(Calendar.SECOND));
			
			String hra= hora+":"+minutos+":"+segundos;
		
		
		String fechacjmysql=annio+"-"+mes+"-"+dia;
		String fechacj=dia+"/"+mes+"/"+annio;
		String fecha=dia+"/"+mes+"/"+annio;
		String horas1=hora1+":"+minutos1+":"+segundos1;
		



	

	
	if(va.equals("50")){
		String user1=req.getParameter("user1");
		out.print("<table width='100%' id='enca' border='0' class='style6'><tr><td colspan='5'><div align='center' class='style11' id='cabecera2'>Acta de Recepcion / Entrada de Articulos </div></td></tr>");
		out.print("<tr><td>Bodega : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select  width='100%' name='cmbBodega' id='cmbBodega'><option value='Seleccione'>Seleccione</option>");
		rs=md.ObtenerBodegas(user1);
		try {
			while(rs.next()){
				out.print("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
			}
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
			}
		out.print("</select></td>");	
		
		out.print("<td>Proveedor : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select width='100%' name='cmbProveedor' id='cmbProveedor'><option value='Seleccione'>Seleccione</option>");
		rs1=md.ObtenerProveedor();
		try {
			while(rs1.next()){
				out.print("<option value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
			}
			rs1.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
			
		out.print("</select></td>");
		
		out.print("<td>Tipo de Movimiento : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select  width='100%' name='cmbTipoM' id='cmbTipoM'  ><option value='Seleccione'>Seleccione</option>");
		rs3=md.listarMovimientose();
		try {
			while(rs3.next()){
				out.print("<option value="+rs3.getString(1)+">"+rs3.getString(2)+"</option>");
			}
			rs3.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		out.print("</select></td></tr>");
		
		out.print("<tr><td>Nº de Factura : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name='txtFactura' type='text' id='txtFactura' size='20'/></td><td>Nº de Orden : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name='txtOrden' type='text' id='txtOrden' size='20'/></td><td>Cumplimiento en Tiempo de entrega:  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label><select name='cmbTiemE' type='text' id='cmbTiemE'><option value='Seleccione'>Seleccion</option>" +
				"<option value='SI'>SI</option>" +
				"<option value='NO'>NO</option></select></td></tr>");
		out.print("<tr><td>Recibe : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name='txtRecibe' type='text' id='txtRecibe' size='20'/></td><td>Entrego : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name='txtEntrego' type='text' id='txtEntrego' size='20'/></td><td>Verifico : &nbsp;&nbsp;&nbsp;&nbsp;<input name='txtVerifico' type='text' id='txtVerifico' size='20' /></td>" );
		out.print("<tr><td><input   type='hidden' name='txtFecha' type='text' id='txtFecha' value='"+fecha+"' readonly='' size='10' /></td><td><input  type='hidden' name='txtHora'  type='text' id='txtHora' value='"+horas1+"' onKeyup=''  size='10' /></td></tr>");
		
		
		out.print("<br>");
		
		
		out.print("<table width='100%' class='style6'><tr><td colspan='2'><div align='center' class='style11' id='cabecera2'>Generar Movimientos</div></td></tr></table>");
			
	
		
out.print("<table id='asientoprincipal' width='100%'   border='0'  ><tr><td><div id=asientosc>");
		
		
		out.print("<table id='asiento' width='100%'   border='0'  ><tr BGCOLOR='#D3D3D3' ><td width='4%'><i><div align='center'>ITEM</div></i></td><td width='15%'><i><div align='center'>Articulo</div></i></td><td width='15%'><i><div align='center'>Laboratorio</div></i></td><td width='5%'><i><div align='center'>Lote</div></i></td><td width='5%'><i><div align='center'>Codigo Invima</div></i></td><td width='5%'><i><div align='center'>Vence</div></i></td><td width='3%'><i><div align='center'>Cant. Solicita</div></i></td><td width='3%'><i><div align='center'>Cant. Recibe</div></i></td><td width='3%'><i><div align='center'>EMBA</div></i></td><td width='3%'><i><div align='center'>EMPA</div></i></td><td width='3%'><i><div align='center'>Envase</div></i></td><td width='3%'><i><div align='center'>T.ºC</div></i></td><td width='10%'><i><div align='center'>Env. Primario</div></i></td><td width='5%'><i><div align='center'>Nº CUM</div></i></td><td width='3%'><i><div align='center'>Cumple</div></i></td><td width='3%'><i><div align='center'>Dona</div></i></td><td width='3%'><i><div align='center'>IVA</div></i></td><td width='10%'><i><div align='center'>Vlr Unitario</div></i></td><td width='20%'><i><div align='center'>Vlr Total</div></i></td></tr>");  //

		out.print("<tr>");
		out.print("<td><div id='lit'>1</div></td>");

				
		out.print("<td><input type=text id='cta0' style='width:100%' Onkeyup='Completa(this,dteri,event,&quot;ControlEntradas1&quot;,&quot;51&quot;,2,1)'>");
		out.print("<input type=hidden id='hcta0' style='width:100%' ></td>");
		
		out.print("<td><select id='lab0' style='width:100%'title='lab0'><option value='Seleccione'>Seleccione</option>");
		
		rs3=md.listarLaboratorios();
		try {
			while(rs3.next()){
				out.print("<option value="+rs3.getString(1)+">"+rs3.getString(2)+"</option>");
			}
			rs3.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		out.print("</select></td>");

		
		out.print("<td><input type=text id='lote0' style='width:100%' title='lote0' onFocus='checkart(0)' onkeyup='checkcara(0)'></td>");
		
		out.print("<td><input type=text id='txtInvima0' style='width:100%' title='txtInvima0' onkeyup='checkcaras(0)'></td>");
		
		out.print("<td><input type=text id='txtVence0' style='width:100%' title='txtVence0' onKeyup='masca(this,patron,true,"+dia+","+mes+","+annio+")' onBlur='checkfecha(0),compare_dates(0,this,"+dia+","+mes+","+annio+")'></td>");
		
		out.print("<td><input type=text id='txtCantSol0' style='width:100%' title='txtCantSol0' onkeyup='checknum(0)'></td>");
		
		out.print("<td><input type=text id='txtCantidad0' style='width:100%' title='txtCantidad0' onkeyup='checknum(0)'></td>");
		
		out.print("<td><select id='cmbEMB0' style='width:100%'title='cmbEMB0'><option value='Seleccione'> </option>");
		
		rs3=md.listarEmbalaje();
		try {
			while(rs3.next()){
				out.print("<option value="+rs3.getString(1)+">"+rs3.getString(2)+"</option>");
			}
			rs3.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		out.print("</select></td>");
		
	out.print("<td><select id='cmbEMP0' style='width:100%'title='cmbEMP0'><option value='Seleccione'> </option>");
		rs3=md.listarEmpaque();
	try {
		while(rs3.next()){
			out.print("<option  value="+rs3.getString(1)+">"+rs3.getString(2)+"</option>");
		}
		rs3.getStatement().getConnection().close();
	} catch (SQLException e) {
		out.print("Error "+e);
		e.printStackTrace();
	}
	out.print("</select></td>");
		
	out.print("<td><select id='cmbEMV0' style='width:100%'title='cmbEMV0'><option value='Seleccione'> </option>");
	rs3=md.listarEnvase();
	try {
		while(rs3.next()){
			out.print("<option  value="+rs3.getString(1)+">"+rs3.getString(2)+"</option>");
		}
		rs3.getStatement().getConnection().close();
	} catch (SQLException e) {
		out.print("Error "+e);
		e.printStackTrace();
	}
	out.print("</select></td>");
		
		
		out.print("<td><input title='txtT0' type='text' id='txtT0'  style='width:100%' value='N/A'></td>");
		
		out.print("<td><select id='cmbENVP0' style='width:100%'title='cmbENVP0'><option value='Seleccione'> </option>");
		rs3=md.listarEnvasePrimario();
		try {
			while(rs3.next()){
				out.print("<option  name="+rs3.getString(2)+" value="+rs3.getString(1)+">"+rs3.getString(2)+"</option>");
			}
			rs3.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		out.print("</select></td>");
		
		
		out.print("<td><input type=text style='width:100%' title='txtCUM0' id='txtCUM0' ></td>");
				
		//out.print("<td><input align='center' type='checkbox' title='cumple0' name='cumple0' id='cumple0' size='2'></td>");
		
		//out.print("<td><input align='center' type='checkbox' title='dona0' name='dona0'  id='dona0' size='2'></td>");
		
		out.print("<td><select title='cumple0' name='cumple0' id='cumple0' style='width:100%'>" +
				"<option value=''></option>" +
				"<option value='1'>SI</option>" +
				"<option value='0'>NO</option></select></td>");
		
		out.print("<td><select title='dona0' name='dona0'  id='dona0' style='width:100%'>" +
				"<option value=''></option>" +
				"<option value='1'>SI</option>" +
				"<option value='0'>NO</option></select></td>");
		
		out.print("<td><input type=text id='ter0' style='width:100%' onBlur='Repetir(0)'  Onkeyup='Completa(this,dterf,event,&quot;ControlEntradas1&quot;,&quot;52&quot;,2,2)'>");
		out.print("<input type=hidden id='hter0' style='width:100%' ></td>");
		
		out.print("<td><input title='txtVunitario0' type='text' onFocus='checkiva(0)' onkeyup='checknum2(0)' onBlur='Repetir(0)' id='txtVunitario0' style='width:100%' ></td>");
		
		out.print("<td><input type='text' id='txtTotal0' style='width:100%' readonly='' onBlur='newfile(1);'></td>");
			
		out.print("</tr>"); 
		
			
		out.print("<tr>");
		out.print("<td></td><td><div id='dteri'></div></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td><div  id='dterf'></div></td><td></td><td></td>");
		out.print("</tr>");
		
		
		
		
		out.print("</table>");
		out.print("</table>");
		
	
		
		
		out.print("<div id='repetir'><div id='boton'  align='center'><input type='button' name='btnD' id='btnD' value='    Ingresar    '  onClick='Causar()'></div></div>");
		
		
	
	}//50	
	
	if(va.equals("51")){//
	String cadena ="";
	cadena="[";

	rs = md.listarArticulos(texto);
	try {
	while(rs.next()){
		
		cadena = cadena+"'"+rs.getString(1)+"|"+rs.getString(2)+"|"+rs.getString(3)+"|"+rs.getString(4)+"|"+rs.getString(5)+"|"+rs.getString(6)+"'";
		cadena = cadena +",";

	} 
	rs.getStatement().getConnection().close();
	} catch (SQLException e) {
	out.print("Error "+e);
	e.printStackTrace();
	}
	cadena = cadena+"]";
	res.getWriter().write(cadena);
	//out.println(cadena); 
	
	//System.out.println("buffer: "+cadena);
	
	/////////////
	}
	

	
	if(va.equals("52")){//Para crear cheques consulta terceros
	///////////////////
	String cadena ="";
	cadena="[";
	rs =md.listarIvas(texto);
	//rs = md.CompletaTerceros(texto);
	try {
	while(rs.next()){
	cadena = cadena+"'"+rs.getString(1)+"|"+rs.getString(2)+"|"+rs.getString(3)+"'";
	    	cadena = cadena +",";
	    	//cadena = cadena+"<li onclick='confirmarCta(this.innerHTML)'>"+rs.getString(1)+"|"+rs.getString(2)+"|"+rs.getString(3)+"</li>";
	    	  
	} 
	rs.getStatement().close();
	} catch (SQLException e) {
	out.print("Error "+e);
	e.printStackTrace();
	}
	cadena = cadena+"]";
	res.getWriter().write(cadena);
	//out.println(cadena); 
	
	System.out.println("buffer: "+cadena);
	
	/////////////
	}
	
	

	
	if(va.equals("53")){
		System.out.println("Datos: "+Ndatos);
		
		String tipoM=req.getParameter("tipoM");
		String fechass = req.getParameter("fechass");
		String dias=fechass.substring(0, 2);
		String meses=fechass.substring(3, 5);
		String anos=fechass.substring(6, 10);
		String fec=anos+"-"+meses+"-"+dias;
		String factura = req.getParameter("factura");
		String cont=req.getParameter("cont");
		String max=req.getParameter("max");
		String bodega=req.getParameter("bodega");
		String hras=req.getParameter("hora");
		String proveedor=req.getParameter("proveedor");
		String orden=req.getParameter("orden");
		String recibe=req.getParameter("recibe");
		String entrego=req.getParameter("entrego");
		String tiempoE=req.getParameter("tiempoE");
		String verifico=req.getParameter("verifico");
		
					
		rs1=md.ObtenerConsecutivo("1");
		String cons="";
		int cn=0;
		try {
			if(rs1.next()){
			cons=rs1.getString(1)+""+rs1.getString(2);
			cn=Integer.parseInt(rs1.getString(2))+1;
			}
			rs1.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		
		String cns=String.valueOf(cn);
		md.ActualizaConsecutivo("1",cns);
		
		md.CrearMovimientos(cont, tipoM, fec, hra, user, factura, cons);
		
		
		//rescatar el ultimo movimiento
		rs1=md.ObtenerUMovimiento(fec, hra);
		String movi="";
		try {
			while(rs1.next()){
			movi=rs1.getString(1);
			}
			rs1.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		
		out.print(movi);

		StringTokenizer ele;  
		ele = new StringTokenizer(Ndatos,"|"); 
			
		while(ele.hasMoreTokens()){ 
	
		String x=ele.nextToken();
		System.out.println(x+" / ");
		
		StringTokenizer ele2 = new StringTokenizer(x,","); 
		while(ele2.hasMoreTokens()){
			
		String cta=ele2.nextToken();
		String lab=ele2.nextToken();
		String lote=ele2.nextToken();
		String Invima=ele2.nextToken();
		String Vence=ele2.nextToken();
		String CantSol=ele2.nextToken();
		String Cantidad=ele2.nextToken();
		String EMB=ele2.nextToken();
		String EMP=ele2.nextToken();
		String EMV=ele2.nextToken();
		String T=ele2.nextToken();
		String ENVP=ele2.nextToken();
		String CUM=ele2.nextToken();
		String cumple=ele2.nextToken();
		String dona=ele2.nextToken();
		String hter=ele2.nextToken();
		String Vunitario=ele2.nextToken();
		String Total=ele2.nextToken();
			
		String diass=Vence.substring(0, 2);
		String mesess=Vence.substring(3, 5);
		String anoss=Vence.substring(6, 10);
		String venc=anoss+"-"+mesess+"-"+diass;
		
		
		
		md.CrearEntradas(movi, cta, venc, lote, Cantidad, Vunitario, Total, hter, bodega, fec, hras, proveedor, Invima);
		
		
		
		///obtener ultimo inv
		 rs2=md.ObtenerUEntradas(fec,hras);
			String in="";
			try {
				while(rs2.next()){
				in=rs2.getString(1);
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
		////crear detalle mov
						int cp=(Integer.parseInt(CantSol)-Integer.parseInt(Cantidad));
						String cps=String.valueOf(cp);
						System.out.println(cps);
						
						double cm=(Integer.parseInt(Cantidad)*0.1)+1;
						String cms=String.valueOf(cm);
						System.out.println(cps);
						
					/*
						String cumplex="";
						
						if(cumple=="on"){
							 cumplex="1";
						}else{
							 cumplex="0";
						}
						
						String donax="";		
						if(dona=="on"){
							donax="1";
						}else{
							donax="0";
						}	
						*/
						
		md.CrearActa(in,orden,factura,CantSol,Cantidad,cps,EMB,EMP,EMV,T,ENVP,recibe,entrego,"10%+1","ALEATORIO",cms,cumple,tiempoE,verifico,dona,fec,hras,lab,CUM);
		
		md.CrearDetalle(movi,in,bodega,Cantidad);	

		//out.print(movi);
		
		}
		
	
		}
		
		//res.sendRedirect("farc_ReporteEntradas1.jsp?movi="+movi);
		//res.sendRedirect("farc_ReporteActaRecepcion1.jsp?movi="+movi);
		}
	
	
	
	
	
	}
}

// Fin doPost
	
	

	







 

