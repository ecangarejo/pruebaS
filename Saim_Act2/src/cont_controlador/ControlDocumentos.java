package cont_controlador;

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

import cont_metodo.MetodoDocumentos;

/**
 * Servlet implementation class ControlDocumentos
 */
public class ControlDocumentos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		HttpSession session = req.getSession(true);
		PrintWriter out=res.getWriter();
		MetodoDocumentos md = new MetodoDocumentos();

		///////Variables/////////////////////
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		ResultSet rs3=null;
		String va = req.getParameter("valor");
		String texto=req.getParameter("texto");
		String Ndatos=req.getParameter("Ndatos");
		String docu=req.getParameter("docu");
		String user=req.getParameter("user");
		String ccme=req.getParameter("ccme");
		String le=req.getParameter("le");
		String li=req.getParameter("ls");
		String vdebito=req.getParameter("vdebito");
		String vcredito=req.getParameter("vcredito");
		String pc=req.getParameter("pc");
		String ac=req.getParameter("ac");
		String pco=req.getParameter("pco");
		String aco=req.getParameter("aco");
		String p=req.getParameter("p");
		String b=req.getParameter("b");
		String v=req.getParameter("v");
		String pd=req.getParameter("pd");
		String su=req.getParameter("su");
		String cos=req.getParameter("cos");
		String td=req.getParameter("td");
		String nd=req.getParameter("nd");
		String fd=req.getParameter("fd");
		String dd=req.getParameter("dd");
		String ter=req.getParameter("ter");
		String tern=req.getParameter("tern");
		String sd=req.getParameter("sd");
		String sdn=req.getParameter("sdn");
		String ccd=req.getParameter("ccd");
		String ccdn=req.getParameter("ccdn");
		String scd=req.getParameter("scd");
		String scdn=req.getParameter("scdn");
		String bd=req.getParameter("bd");
		String cc=req.getParameter("cc");
		String id=req.getParameter("id");
		String ls=req.getParameter("l");
		String cheque=req.getParameter("cheque");
		String conc=req.getParameter("conc");
		
		String NumOrden=req.getParameter("NumOrden");
		String factu=req.getParameter("factu");

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
			String hra= hora+":"+minutos+":"+segundos;
		
		
		String fechacjmysql=annio+"-"+mes+"-"+dia;
		String fechacj=dia+"/"+mes+"/"+annio;
		
		if(va.equals("1")){	//Selecciona el Periodo
			out.print("<table width='100%'  border='0' class='style6'><tr><td colspan='5'><div align='center' class='style11' id='cabecera2'>Seleccione Periodo</div></td></tr>");
			//out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='60%'><i><div align='center'>Paciente</div></i></td><td width='30%'><i><div align='center'>Empresa</div></i></td><td width='10%'><i><div align='center'>Admisi�n</div></i></td></tr>");  //
			out.print("<table width='100%' border='0' class='style6' ><tr><td><div align='center'>A&ntilde;o: <input name='AC' type='text' id='AC' size='2' onKeyup='periodocontable(this,patronp,true,1,"+ccme+")'  />  Mes: <input name='MC' type='text' id='MC' size='1' onKeyup='periodocontable(this,patronp,true,0,"+ccme+")' /></div></td></tr>"); 
		}//fin equals 1
		
		if(va.equals("2")){ //Verifica que el periodo exista o este habilitado
			rs = md.ConsultarPeriodo(pc, ac);
			String sw="00";
			try {
				if(rs.next()){
					sw=rs.getString(1)+"|"+rs.getString(2);
				}	
				rs.getStatement().getConnection().close();
			out.print(sw);
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
		}//fin equals 2
		
		if(va.equals("3")){
			//out.print("<table width='100%'  border='1' class='style6'><tr><td colspan='5'><div align='center' class='style11' id='cabecera2'>Periodo</div></td></tr>");
			out.print("<table width='100%' border='0' class='style6' ><tr BGCOLOR='#D3D3D3'><td  colspan='3' ><div align='center'>Periodo: "+ac+" - "+pc+"</div></td></tr>"); 
			
			if(b.equals("1")){
				out.print("<tr><td  width='50%'><div align='center'><label><input name='radiobutton' type='radio' value='radiobutton' id='10' onclick='RadioDoc(4,&quot;"+pc+"&quot;,"+ac+","+p+","+b+","+ccme+")' />Crear Documento</label></div></td>");
				out.print("<td width='50%'><div align='center'><label><input name='radiobutton' type='radio' value='radiobutton' id='12' onclick='RadioDoc(1,&quot;"+pc+"&quot;,"+ac+","+p+","+b+","+ccme+")' />Consultar Documento</label></div></td></tr></table>");
			}else{
				out.print("<tr><td  width='50%'><div align='center'><label><input name='radiobutton' type='radio' value='radiobutton' id='10' onclick='RadioDoc(0,&quot;"+pc+"&quot;,"+ac+","+p+","+b+","+ccme+")' />Crear Documento</label></div></td>");
				out.print("<td width='50%'><div align='center'><label><input name='radiobutton' type='radio' value='radiobutton' id='12' onclick='RadioDoc(1,&quot;"+pc+"&quot;,"+ac+","+p+","+b+","+ccme+")' />Consultar Documento</label></div></td></tr></table>");
			}
		}//fin equals 3
			
		if(va.equals("3m")){
			//out.print("<table width='100%'  border='1' class='style6'><tr><td colspan='5'><div align='center' class='style11' id='cabecera2'>Periodo</div></td></tr>");
			out.print("<table width='100%' border='0' class='style6' ><tr BGCOLOR='#D3D3D3'><td  colspan='3' ><div align='center'>Periodo: "+ac+" - "+pc+"</div></td></tr>"); 
			
			if(b.equals("0")){
				out.print("<tr><td  width='50%'><div align='center'><label><input name='radiobutton' type='radio' value='radiobutton' id='10' onclick='RadioDoc(2,&quot;"+pc+"&quot;,"+ac+","+p+","+b+","+ccme+")' />Modificar Documento</label></div></td>");
				out.print("<td width='50%'><div align='center'><label><input name='radiobutton' type='radio' value='radiobutton' id='12' onclick='RadioDoc(3,&quot;"+pc+"&quot;,"+ac+","+p+","+b+","+ccme+")' />Eliminar Documento</label></div></td></tr></table>");
			}
		}//fin equals 3m
		
		if(va.equals("4")){
			out.print("<table width='100%' border='0' class='style6' ><tr BGCOLOR='#D3D3D3'><td   colspan='3'><div align='center'>Periodo: "+ac+" - "+pc+"</div></td></tr>"); 
			if(b.equals("1")){//Periodo Bloqueado
				if(ccme.equals("1")){
				out.print("<tr><td  width='50%'><div align='center'><label><input name='radiobutton' type='radio' value='radiobutton' id='10' onclick='RadioDoc(4,&quot;"+pc+"&quot;,"+ac+","+p+","+b+","+ccme+")' />Crear Documento</label></div></td>");
				out.print("<td width='50%'><div align='center'><label><input name='radiobutton' type='radio' value='radiobutton' id='12' onclick='RadioDoc(1,&quot;"+pc+"&quot;,"+ac+","+p+","+b+","+ccme+")' />Consultar Documento</label></div></td></tr></table>");
				}
				//out.print("<table width='100%' border='0' class='style6'><tr><td colspan='3'><div align='center' class='style11' id='cabecera2'>Consultar Documento</div></td></tr>");
				///AQUI DEBERIA PEDIR EL NUMERO DE DOCUMENTO Y CONSULTAR DOCUMENTOS
			}else{//Periodo desbloqueado
				if(ccme.equals("1")){
				out.print("<tr><td  width='33%'><div align='center'><label><input name='radiobutton' type='radio' value='radiobutton' id='10' onclick='RadioDoc(0,&quot;"+pc+"&quot;,"+ac+","+p+","+b+","+ccme+")' />Crear Documento</label></div></td>");
				out.print("<td width='33%'><div align='center'><label><input name='radiobutton' type='radio' value='radiobutton' id='12' onclick='RadioDoc(1,&quot;"+pc+"&quot;,"+ac+","+p+","+b+","+ccme+")' />Consultar Documento</label></div></td></tr></table>");
				}
				if(ccme.equals("2")){
				out.print("<tr><td  width='50%'><div align='center'><label><input name='radiobutton' type='radio' value='radiobutton' id='10' onclick='RadioDoc(2,&quot;"+pc+"&quot;,"+ac+","+p+","+b+","+ccme+")' />Modificar Documento</label></div></td>");
				out.print("<td width='50%'><div align='center'><label><input name='radiobutton' type='radio' value='radiobutton' id='12' onclick='RadioDoc(3,&quot;"+pc+"&quot;,"+ac+","+p+","+b+","+ccme+")' />Eliminar Documento</label></div></td></tr></table>");
				}
				
				if(v.equals("0")){//Para crear documento
				out.print("<table width='100%' id='enca' border='0' class='style6'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Crear Documento</div></td></tr>");
				out.print("<tr><td  width='29%'>Tipo Documento: <select  style='width:45px' name='tipoDocC' id='tipoDocC' onChange='TipoDoc(0)'><option value='Seleccione'>Seleccione</option>");
				rs = md.ConsultarTiposDocumentos();
				try {
					while(rs.next()){
						out.print("<option value="+rs.getString(1)+">"+rs.getString(1)+"</option>");
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				out.print("</select>");
				out.print("<select   style='width:202px' name='tipoDocN' id='tipoDocN' onChange='TipoDoc(1)' ><option value='Seleccione'>Seleccione</option>");
				rs = md.ConsultarTiposDocumentos();
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
				out.print("<td  width='23%'>Fecha: &nbsp;&nbsp;&nbsp;&nbsp;<input type=text id='fd' size='10' onKeyup='masca(this,patron,true,0,0,0,&quot;"+pc+"&quot;,"+ac+")' onBlur='vfd()'></td>");
				
				out.print("<td  width='25%'>Plantilla: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select   style='width:217px' name='Plantilla' id='Plantilla' onChange='fpcont()' ><option value='0'>Seleccione</option>");
				rs = md.ConsultarPlantillas();
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
				
				out.print("<td><span id='divtercero' ></span></td></tr>");//div nuevo para montar terceros
				out.print("<tr><td><div id='pcont' ></div></td>");//div nuevo para montar sucursal
				out.print("<td><div id='Centrosc' ></div></td>");//div nuevo para montar ccosto
				out.print("<td><div id='SCentrosc' ></div></td>");//div nuevo para montar Subccosto
				out.print("<td><div id='VBase' ></div></td></tr>");//div nuevo para montar Valor Base
				out.print("<tr><td>Nro. Documento: <input type=text id='numdoc' size='36' ></td>");
				out.print("<td colspan='2' >Detalle: &nbsp;<input type=text id='detdoc' size='82' ></td>");
				
				out.print("<td id='tdboton'><input type='button' value='  Crear  ' onClick='CrearDoc(&quot;"+pc+"&quot;,"+ac+")' ></td></tr></table>");
				}//if v=0 	
				
				if(v.equals("2")){//Para MODIFICAR  documentos
					out.print("<table width='100%' border='0' class='style6'><tr><td colspan='6'><div align='center' class='style11' id='cabecera2'>Modificar Documento</div></td></tr>");
					out.print("<tr><td width='16%'><div align='Right'>Numero de Documento</div></td><td><input type=text id='numdc' size='45' ></td>"); 
					out.print("<td width='16%'><div align='Right'>Detalle:</div></td><td><input type=text id='anodc' size='45'  ></td>"); 
					//out.print("<td width='16%'><div align='Right'>Tipo de Documento</div></td><td><input type=text id='perdc' size='45'  ></td></tr>"); 
					
					////////////////////
					out.print("<td width='16%'><div align='Right'>Tipo Documento:</div></td><td><select  style='width:45px' name='tipoDocC' id='tipoDocC' onChange='TipoDocM(0)'><option value='Seleccione'>Seleccione</option>");
					rs = md.ConsultarTiposDocumentos();
					try {
						while(rs.next()){
							out.print("<option value="+rs.getString(1)+">"+rs.getString(1)+"</option>");
						}
						rs.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					out.print("</select>");
					out.print("<select   style='width:202px' name='tipoDocN' id='tipoDocN' onChange='TipoDocM(1)' ><option value='Seleccione'>Seleccione</option>");
					rs = md.ConsultarTiposDocumentos();
					try {
						while(rs.next()){
							out.print("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
						}
						rs.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					out.print("</select></td></tr>");
					/////////////////
					
					out.print("<tr><tr></tr><tr></tr><tr></tr><tr></tr><td colspan='6'><div align='center'><input type='button' value='  Consultar  ' onClick='ConsultarDoc(2,"+ac+",&quot;"+pc+"&quot;)' ></div></td></tr>");
					out.print("<tr><td colspan='6'><div id='consul' ></div></td></tr></table>");
									
				}//if v=2
				
				if(v.equals("3")){//Para Eliminar  documentos
					out.print("<table width='100%' border='0' class='style6'><tr><td colspan='6'><div align='center' class='style11' id='cabecera2'>Eliminar Documento</div></td></tr>");
					out.print("<tr><td width='16%'><div align='Right'>Numero de Documento</div></td><td><input type=text id='numdc' size='45' ></td>"); 
					out.print("<td width='16%'><div align='Right'>ACJo</div></td><td><input type=text id='anodc' size='45' ></td>"); 
					out.print("<td width='16%'><div align='Right'>Periodo</div></td><td><input type=text id='perdc' size='45'  ></td></tr>"); 
					out.print("<tr><tr></tr><tr></tr><tr></tr><tr></tr><td colspan='6'><div align='center'><input type='button' value='  Consultar  ' onClick='ConsultarDoc(3,"+ac+",&quot;"+pc+"&quot;)' ></div></td></tr>");
					out.print("<tr><td colspan='6'><div id='consul' ></div></td></tr></table>");
				}//if v=3
				
				
			}//fin del else b=1 es decir desbloqueado
			/// if v=1 essta afuera porque debe estar habilitado aunq el periodo este bloqueado
			if(v.equals("1")){//Para CONSULTAR  documento
				out.print("<table width='100%' border='0' class='style6'><tr><td colspan='6'><div align='center' class='style11' id='cabecera2'>Consultar Documento</div></td></tr>");
				out.print("<tr><td width='16%'><div align='Right'>Numero de Documento</div></td><td><input type=text id='numdc' size='45' ></td>"); 
				out.print("<td width='16%'><div align='Right'>ACJo</div></td><td><input type=text id='anodc' size='45' ></td>"); 
				out.print("<td width='16%'><div align='Right'>Periodo</div></td><td><input type=text id='perdc' size='45'  ></td></tr>"); 
				out.print("<tr><tr></tr><tr></tr><tr></tr><tr></tr><td colspan='6'><div align='center'><input type='button' value='  Consultar  ' onClick='ConsultarDoc(1,"+ac+",&quot;"+pc+"&quot;)' ></div></td></tr>");
				out.print("<tr><td colspan='6'><div id='consul' ></div></td></tr></table>");
			}//if v=1
			
			//out.print("<tr><td><div id='Cabecera' ></div></td></tr>");
			out.print("<tr><td><div id='Detalle' ></div></td></tr></table>");
			out.print("<tr><td><div id='Cabecera' ></div></td></tr></table>");
			
			//out.print("<table width='100%' border='0' class='style6' ><tr><td><div align='center'>sdfdfsvtha<input name='MC' type='text' id='MC' size='1' onKeyup='periodocontable(this,patronp,true,0)' />  A&ntilde;o: <input name='AC' type='text' id='AC' size='2' onKeyup='periodocontable(this,patronp,true,1)'  /></div></td></tr>"); 
		

		}//fin equals 4
			
		if(va.equals("5")){ ////Se verifica la plantilla y se montan los otros campos
			rs = md.ConsultarDetallePlantillas(pd);
			String sw="00";
			try {
				if(rs.next()){
					sw=rs.getString(1)+"|"+rs.getString(2);
				}	
				rs.getStatement().getConnection().close();
			//out.print(sw);
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			

			if(!pd.equals("0")){
				
			////Sucursales	
			out.print("<tr><td>Sucursal: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select  style='width:45px' name='sucuc' id='sucuc' onChange='vsucu(0)'><option value='0'>Seleccione</option>");
			rs = md.Sucursales();
			try {
				while(rs.next()){
					out.print("<option value="+rs.getString(1)+">"+rs.getString(1)+"</option>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("</select>");
			out.print("<select  style='width:200px' name='sucun' id='sucun' onChange='vsucu(1)'><option value='0'>Seleccione</option>");
			rs = md.Sucursales();
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
			}
			
			
		}//fin equals 5
	
	
	if(va.equals("6")){ ////Se verifica la plantilla y se montan los otros campos
	////Centros de Costo
		if(cos.equals("0")){
		out.print("<td>C.Costo: <select  style='width:50px' id='ccostoc' onChange='vccosto(0)'><option value='0'>Seleccione</option>");
		rs = md.CentrosdeCosto(su);
		try {
			while(rs.next()){
				out.print("<option value="+rs.getString(3)+">"+rs.getString(1)+"</option>");
			}
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		out.print("</select>");
		out.print("<select  style='width:170px' id='ccoston' onChange='vccosto(1)'><option value='0'>Seleccione</option>");
		rs = md.CentrosdeCosto(su);
		try {
			while(rs.next()){
				out.print("<option value="+rs.getString(3)+">"+rs.getString(2)+"</option>");
			}
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		out.print("</select></td>");
		}
	////SubCentros de Costo
		if(cos.equals("1")){
			out.print("<td>SubC.Costo: <select  style='width:50px' id='sccostoc' onChange='vsccosto(0)'><option value='0'>Seleccione</option>");
			rs = md.SubCentrosdeCosto(su);
			try {
				while(rs.next()){
					out.print("<option value="+rs.getString(3)+">"+rs.getString(1)+"</option>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("</select>");
			out.print("<select  style='width:170px' id='sccoston' onChange='vsccosto(1)'><option value='0'>Seleccione</option>");
			rs = md.SubCentrosdeCosto(su);
			try {
				while(rs.next()){
					out.print("<option value="+rs.getString(3)+">"+rs.getString(2)+"</option>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("</select></td>");
			}
	}//fin equals 6
	
	
	if(va.equals("7")){ ////Se monta el campo de valor base
		out.print("Base: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type=text id='base' size='31' onkeyup='vnb(0,4)' >");
	}//fin equals 7	

	
	if(va.equals("8")){ ////Se monta el campo de terceros
		out.print("<td  width='23%'>Tercero: <select  style='width:100px' id='tercero' onChange='vter(0)'><option value='0'>Seleccione</option>");
		rs = md.Terceros();
		try {
			while(rs.next()){
				out.print("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
			}
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		out.print("</select>");
		out.print("<select  style='width:120px' id='terceron' onChange='vter(1)'><option value='0'>Seleccione</option>");
		rs = md.Terceros();
		try {
			while(rs.next()){
				out.print("<option value="+rs.getString(1)+">"+rs.getString(3)+"</option>");
			}
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		out.print("</select></td>");
	}//fin equals 8	
	
	
	if(va.equals("9")){ ////Conseguir el consecutivo para armar el numero
		rs = md.ConsecutivosdeCuentas(cc);
		try {
			if(rs.next()){
				out.print(rs.getString(1)+"|"+rs.getString(2));
			}
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}		
		int aa=Integer.parseInt(annio)-2000;
		//out.print("|"+aa+"|"+mes);
	}//fin equals 9
	
	
	
	
	if(va.equals("10")){ ////Se inserta el encabezado en la db del documento y se genera el detalle
	String doc="";
	if(bd.equals(null)){bd="";}
	if(bd.equals("undefined")){bd="";}
	String dv8=fd.substring(0, 2);
	String mv8=fd.substring(3, 5);
	String av8=fd.substring(6, 10);
	String fdn=av8+"-"+mv8+"-"+dv8;


	/*VALIDACION  DE CONSECUTIVO */
	rs=md.VerifcacionConsecutivo(nd);
	System.out.println("Valor de nd"+nd);
	String verificacion="0";
	try {
		if(rs.next()){
			verificacion="1";
			
		}rs.getStatement().getConnection().close();
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	long consec=0;
	String ndd="";
	if(verificacion.equals("1")){
		rs=md.VerifConttipoDoc(td);
		try {
			if(rs.next()){
				
				consec=rs.getLong(4);
				System.out.println("valor de consec "+consec);
				if(rs.getString(4).length()==1){ndd=("00000000"+rs.getString(4));}
				if(rs.getString(4).length()==2){ndd=("0000000"+rs.getString(4));}
				if(rs.getString(4).length()==3){ndd=("000000"+rs.getString(4));}
				if(rs.getString(4).length()==4){ndd=("00000"+rs.getString(4));}
				if(rs.getString(4).length()==5){ndd=("0000"+rs.getString(4));}
				if(rs.getString(4).length()==6){ndd=("000"+rs.getString(4));}
				if(rs.getString(4).length()==7){ndd=("00"+rs.getString(4));}
				if(rs.getString(4).length()==8){ndd=("0"+rs.getString(4));}
				if(rs.getString(4).length()==9){ndd=rs.getString(4);}
				nd=rs.getString(3)+""+ndd;
				System.out.println("entrando a validacion nd "+nd);
			}rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*FIN VALIDACION */
		
	md.CrearDocumento(ac, pc, td, nd, fdn, dd, "0", "0", pd, bd, user, fechacjmysql, hra);
		
	
	rs = md.ConsecutivoUDocumento(nd);
	try {
		if(rs.next()){
			doc=rs.getString(1);
			out.print("<input type=hidden id='docuh'  value="+rs.getString(1)+"></td>");
		}
		rs.getStatement().getConnection().close();
	} catch (SQLException e) {
		out.print("Error "+e);
		e.printStackTrace();
	}	
	
	
	String cta="";
	rs = md.ConsecutivosdeCuentas(td);
	try {
		if(rs.next()){
			cta=rs.getString(2);
		}
		rs.getStatement().getConnection().close();
	} catch (SQLException e) {
		out.print("Error "+e);
		e.printStackTrace();
	}	
	int ctan=Integer.parseInt(cta)+1;
	cta=String.valueOf(ctan);
	md.ActualizarConsecutivo(cta,td);
                                                                                                                                                                                                                                                            	
	/***  DETALLE DE DOCUMENTOS  ****////LA PARTE MAS IMPORTANTE
	
	if(pd.equals("0")){
	
	out.print("<table width='100%' border='0' class='style6'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Detalle del Documento</div></td></tr></table>");
	out.print("<table width='100%' border='0' class='style6' ><tr><td  colspan='12' ><div id='linea'>");
	out.print("<table width='100%' border='0' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='2%'><i><div align='center'>ITEM</div></i></td><td width='10%'><i><div align='center'>CUENTA</div></i></td><td width='6%'><i><div align='center'>SUCURSAL</div></i></td><td width='6%'><i><div align='center'>C.COSTO</div></i></td><td width='6%'><i><div align='center'>SUBC.COSTO</div></i></td><td width='9%'><i><div align='center'>TERCERO</div></i></td><td width='19%'><i><div align='center'>DESCRIPCION</div></i></td><td width='7%'><i><div align='center'>BASE</div></i></td><td width='7%'><i><div align='center'>DOC.REF</div></i></td><td width='8%'><i><div align='center'>DEBITO</div></i></td><td width='8%'><i><div align='center'>CREDITO</div></i></td><td width='5%'><i><div align='center'>DIFERIDO</div></i></td><td width='4%'><i><div align='center'>ACCION</div></i></td></tr>");  //
	out.print("</tr><td><div align='center'>1</div></td>");
	out.print("<td><input type=text id='cta0' style='width: 100%' onkeyup='autocompletaCta(0,0)' onChange='limpiacta(0)'>");
	out.print("<input type=hidden id='ctah0'></td>");
	out.print("<td><select  style='width:100%' id='suc0'  onChange='lcco(0)'><option value='0'>Seleccione</option>");
	rs = md.Sucursales();
	try {
		while(rs.next()){
			out.print("<option title='"+rs.getString(2)+"' value="+rs.getString(1)+">"+rs.getString(1)+"</option>");
		}
		rs.getStatement().getConnection().close();
	} catch (SQLException e) {
		out.print("Error "+e);
		e.printStackTrace();
	}
	out.print("</select></td>");
	out.print("<td><div id='ncco0'><select  style='width:100%' id='cco0'><option value='0'>Seleccione</option>");
	out.print("</select></div></td>");
	out.print("<td><div id='nsco0'><select  style='width:100%' id='scc0'><option value='0'>Seleccione</option>");
	out.print("</select></div></td>");
	out.print("<td><input type=text id='ter0' style='width: 100%' onkeyup='autocompletarTercero(0,0)' onBlur='limpiater(0)'>");
	out.print("<input type=hidden id='terh0'></td>");
	out.print("<td><input type=text id='des0' style='width: 100%' ></td>");
	out.print("<td><input type=text id='bas0' style='width: 100%' onkeyup='vnb(0,1)' value='0'></td>");
	out.print("<td><input type=text id='ref0' style='width: 100%' ></td>");
	out.print("<td><input type=text id='deb0' style='width: 100%' onkeyup='vnb(0,2);exluye(0,1)'   value='0'></td>");
	out.print("<td><input type=text id='cre0' style='width: 100%' onkeyup='vnb(0,3);exluye(0,2)'  value='0'></td>");
	out.print("<td><input type=text id='dif0' style='width: 100%' disabled='true'  value='N/A'></td>");
	out.print("<td><a  href='#'onclick='Nuevalinea(0,"+doc+","+ac+",&quot;"+pc+"&quot;)'><div align='center'>Crear</div></a></td></tr>");
	out.print("<tr><td></td><td><div id='dcta0'></div></td><td></td><td></td><td></td><td><div id='dter0' ></div></td><td></td><td></td><td></td><td></td><td></td><td></td></tr></table>");
	
	out.print("</div></td></tr></table>");
	
	}else{///  si escoge alguna plantilla
		
		
		
		
		out.print("<table width='100%' border='0' class='style6'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Detalle del Documento</div></td></tr></table>");
		out.print("<table width='100%' border='0' class='style6' ><tr><td  colspan='12' ><div id='linea'>");
		out.print("<table width='100%' border='0' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='2%'><i><div align='center'>ITEM</div></i></td><td width='10%'><i><div align='center'>CUENTA</div></i></td><td width='6%'><i><div align='center'>SUCURSAL</div></i></td><td width='6%'><i><div align='center'>C.COSTO</div></i></td><td width='6%'><i><div align='center'>SUBC.COSTO</div></i></td><td width='9%'><i><div align='center'>TERCERO</div></i></td><td width='19%'><i><div align='center'>DESCRIPCION</div></i></td><td width='7%'><i><div align='center'>BASE</div></i></td><td width='7%'><i><div align='center'>DOC.REF</div></i></td><td width='8%'><i><div align='center'>DEBITO</div></i></td><td width='8%'><i><div align='center'>CREDITO</div></i></td><td width='5%'><i><div align='center'>DIFERIDO</div></i></td><td width='4%'><i><div align='center'>ACCION</div></i></td></tr>");  //
		
	
		int in=0;
		double dt=0;
		double ct=0;
		
		rs = md.DetallePlantillas(pd);
		try {
			while(rs.next()){
				//Integer.parseInt(rs.getString(1));

		out.print("</tr><td><div align='center'>"+(in+1)+"</div></td>");
		out.print("<td><input type=text id='cta"+in+"' style='width: 100%' value='"+rs.getString(2)+"' readonly='readonly'>");
		out.print("<input type=hidden id='ctah"+in+"' value='"+rs.getString(1)+"' ></td>");
		out.print("<td><select  style='width:100%' id='suc"+in+"'  ><option value='"+sd+"'>"+sdn+"</option>");
		out.print("</select></td>");
		out.print("<td><select  style='width:100%' id='cco"+in+"'><option value='"+ccd+"'>"+ccdn+"</option>");
		out.print("</select></td>");
		out.print("<td><select  style='width:100%' id='scc"+in+"'><option value='"+scd+"'>"+scdn+"</option>");
		out.print("</select></td>");
		out.print("<td><input type=text id='ter"+in+"' style='width: 100%' value='"+tern+"' readonly='readonly'> ");
		out.print("<input type=hidden id='terh"+in+"' value='"+ter+"' readonly='readonly'></td>");
		out.print("<td><input type=text id='des"+in+"' style='width: 100%' value='"+dd+"' readonly='readonly'></td>");
		///AQUI DEBE EJECUTARSE LA ESTANDARIZACION DE LAS FORMULAS AHORA IRA MANUAL///////////////
		double vcd=0;
		double vcc=0;
		int vcb=0;
		
		
		
		if(in==0){vcd=Integer.parseInt(bd);}
		if(in==1){vcb=Integer.parseInt(bd);vcc=(Integer.parseInt(bd)*0.10);}
		if(in==2){vcc=(Integer.parseInt(bd) - (Integer.parseInt(bd)*0.10));}
		out.print("<td><input type=text id='bas"+in+"' style='width: 100%' value='"+vcb+"' readonly='readonly'></td>");
		out.print("<td><input type=text id='ref"+in+"' style='width: 100%' readonly='readonly'></td>");
		out.print("<td><input type=text id='deb"+in+"' style='width: 100%' value='"+vcd+"' readonly='readonly'></td>");
		out.print("<td><input type=text id='cre"+in+"' style='width: 100%' value='"+vcc+"' readonly='readonly'></td>");
		out.print("<td><input type=text id='dif"+in+"' style='width: 100%' disabled='true'  value='N/A'></td>");
		out.print("<td></td></tr>");
		out.print("<tr><td></td><td><div id='dcta"+in+"'></div></td><td></td><td></td><td></td><td><div id='dter"+in+"' ></div></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>");
		
		dt=dt+vcd;
		//System.out.println("dti: "+dt);
		ct=ct+vcc; 
		//System.out.println("cti: "+ct);
		
		
		
		
	//	Double.valueOf(JTF_PrecioProd.getText().toString()) - Double.valueOf(JTF_DescuentoProd.getText())) 
		
		
	
		in++;
		}
			out.print("</table></div></td></tr></table>");
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		
		out.print("<input type=hidden id='ddt'  value="+dt+"></td>");
		out.print("<input type=hidden id='dct'  value="+ct+"></td>");
		out.print("<input type=hidden id='lipla'  value="+(in-1)+"></td>");
	}//fin de la plantilla

	}//fin equals 10
		
		
	if(va.equals("11")){ ////Se inserta nueva linea del detalle
		//System.out.println("ESTE ES VALOR IGUAL A 11 ac"+ac);
		String V[] = new String[3200];
		//String M[][] = new String[11][11];
		String M[][] = new String[200][16];
	
		
		
		int i1=0;
		StringTokenizer ele;  
		ele = new StringTokenizer(Ndatos,"|"); 
		
		while(ele.hasMoreTokens()){ 
		   V[i1] = ele.nextToken();   
		 //  System.out.println("V["+i1+"]:"+V[i1]);
		   i1++;
		}
		   
		  // for(int z=0; z<i1; z++){
		//	   out.print(" V["+z+"]="+V[z]+"\n"); 
		//   }
		int in=0;
		for(int z=0; z<=l; z++){
		  for(int zz=0; zz<16; zz++){
			//  System.out.println("in:"+in+" M["+z+"]["+zz+"]="+V[in]);
			M[z][zz]=V[in];
			in++;
		  }
		}
		
		/***  DETALLE DE DOCUMENTOS  ****////LA PARTE MAS IMPORTANTE
		
		int ln=l+1;
		out.print("<table width='100%' border='0' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='2%'><i><div align='center'>ITEM</div></i></td><td width='10%'><i><div align='center'>CUENTA</div></i></td><td width='6%'><i><div align='center'>SUCURSAL</div></i></td><td width='6%'><i><div align='center'>C.COSTO</div></i></td><td width='6%'><i><div align='center'>SUBC.COSTO</div></i></td><td width='9%'><i><div align='center'>TERCERO</div></i></td><td width='19%'><i><div align='center'>DESCRIPCION</div></i></td><td width='7%'><i><div align='center'>BASE</div></i></td><td width='7%'><i><div align='center'>DOC.REF</div></i></td><td width='8%'><i><div align='center'>DEBITO</div></i></td><td width='8%'><i><div align='center'>CREDITO</div></i></td><td width='8%'><i><div align='center'>DIFERIDO</div></i></td><td width='4%'><i><div align='center'>ACCION</div></i></td></tr>");  //
		for(int i=0;i<=l;i++){///este es el pfor para las lineas creadas
			
		///
			out.print("</tr><td><div align='center'>"+(i+1)+"</div></td>");
			out.print("<td><input type=text id='cta"+i+"' style='width: 100%' onkeyup='autocompletaCta("+i+","+i+")' onBlur='limpiacta("+i+")' value='"+M[i][0]+"'>");
			out.print("<input type=hidden id='ctah"+i+"'  value='"+M[i][1]+"'></td>");
			out.print("<td><select  style='width:100%' id='suc"+i+"'  onChange='lcco("+i+")'><option value='"+M[i][2]+"'>"+M[i][3]+"</option>");
			rs = md.SucursalesDiferentes(M[i][2]);
			try {
				while(rs.next()){
					out.print("<option title='"+rs.getString(2)+"' value="+rs.getString(1)+">"+rs.getString(1)+"</option>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("</select></td>");
			out.print("<td><div id='ncco"+i+"'><select  style='width:100%' id='cco"+i+"'><option value='"+M[i][4]+"'>"+M[i][5]+"</option>");
			out.print("</select></div></td>");
			out.print("<td><div id='nsco"+i+"'><select  style='width:100%' id='scc"+i+"'><option value='"+M[i][6]+"'>"+M[i][7]+"</option>");
			out.print("</select></div></td>");
			out.print("<td><input type=text id='ter"+i+"' style='width: 100%' onkeyup='autocompletarTercero("+i+","+i+")' onBlur='limpiater("+i+")' value='"+M[i][8]+"'>");
			out.print("<input type=hidden id='terh"+i+"' value='"+M[i][9]+"'></td>");
			out.print("<td><input type=text id='des"+i+"' style='width: 100%' value='"+M[i][10]+"'></td>");
			out.print("<td><input type=text id='bas"+i+"' style='width: 100%' value='"+M[i][11]+"' onkeyup='vnb("+i+",1)' ></td>");
			out.print("<td><input type=text id='ref"+i+"' style='width: 100%' value='"+M[i][12]+"'></td>");
			out.print("<td><input type=text id='deb"+i+"' style='width: 100%' value='"+M[i][13]+"' onkeyup='vnb("+i+",2);exluye("+i+",1)' value='0'></td>");
			out.print("<td><input type=text id='cre"+i+"' style='width: 100%' value='"+M[i][14]+"' onkeyup='vnb("+i+",3);exluye("+i+",2)' value='0'></td>");
			out.print("<td><input type=text id='dif"+i+"' style='width: 100%' disabled='true'   value='"+M[i][15]+"'></td>");
			out.print("<td></td></tr>");
			out.print("<tr><td></td><td><div id='dcta"+i+"'></div></td><td></td><td></td><td></td><td><div id='dter"+i+"' ></div></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>");
		}
		/////////creacion del nuevo////////////////
		
		out.print("</tr><td><div align='center'>"+(ln+1)+"</div></td>");
		out.print("<td><input type=text id='cta"+ln+"' style='width: 100%' onkeyup='autocompletaCta("+ln+","+ln+")' onBlur='limpiacta("+ln+")'>");
		out.print("<input type=hidden id='ctah"+ln+"'></td>");
		out.print("<td><select  style='width:100%' id='suc"+ln+"'  onChange='lcco("+ln+")'><option value='0'>Seleccione</option>");
		rs = md.Sucursales();
		try {
			while(rs.next()){
				out.print("<option title='"+rs.getString(2)+"' value="+rs.getString(1)+">"+rs.getString(1)+"</option>");
			}
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		out.print("</select></td>");
		out.print("<td><div id='ncco"+ln+"'><select  style='width:100%' id='cco"+ln+"'><option value='0'>Seleccione</option>");
		out.print("</select></div></td>");
		out.print("<td><div id='nsco"+ln+"'><select  style='width:100%' id='scc"+ln+"'><option value='0'>Seleccione</option>");
		out.print("</select></div></td>");
		out.print("<td><input type=text id='ter"+ln+"' style='width: 100%' onkeyup='autocompletarTercero("+ln+","+ln+")' onBlur='limpiater("+ln+")'>");
		out.print("<input type=hidden id='terh"+ln+"'></td>");
		out.print("<td><input type=text id='des"+ln+"' style='width: 100%' ></td>");
		out.print("<td><input type=text id='bas"+ln+"' style='width: 100%' onkeyup='vnb("+ln+",1)' value='0'></td>");
		out.print("<td><input type=text id='ref"+ln+"' style='width: 100%' ></td>");
		out.print("<td><input type=text id='deb"+ln+"' style='width: 100%' onkeyup='vnb("+ln+",2);exluye("+ln+",1)' value='0'></td>");
		out.print("<td><input type=text id='cre"+ln+"' style='width: 100%' onkeyup='vnb("+ln+",3);exluye("+ln+",2)' value='0'></td>");
		out.print("<td><input type=text id='dif"+ln+"' style='width: 100%' disabled='true'   value='N/A'></td>");
		out.print("<td><a  href='#'onclick='Nuevalinea("+ln+","+docu+","+ac+",&quot;"+pc+"&quot;)'><div align='center'>Crear</div></a></td></tr>");
		out.print("<tr><td></td><td><div id='dcta"+ln+"'></div></td><td></td><td></td><td></td><td><div id='dter"+ln+"' ></div></td><td></td><td></td><td></td><td></td><td></td><td></td></tr></table>");
		out.print("</div></td></tr></table>");
		
		//out.print("</table>");
	}
		
	
	if(va.equals("12")){ ////Centro de costo de la sucursal
		out.print("<td><div id='ncco'><select  style='width:100%' id='cco"+id+"' onChange='lsco("+id+")'><option value='0'>Seleccione</option>");
		rs = md.CentrosdeCosto(su);
		try {
			while(rs.next()){
				out.print("<option title='"+rs.getString(2)+"' value="+rs.getString(3)+">"+rs.getString(1)+"</option>");
			}
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		out.print("</select></div></td>");
	}
	
	
	if(va.equals("13")){ ////SubCentro de costo de la sucursal
	out.print("<td><select  style='width:100%' id='scc"+id+"'><option value='0'>Seleccione</option>");
	rs = md.SubCentrosdeCosto(su);
	try {
		while(rs.next()){
			out.print("<option title='"+rs.getString(2)+"' value="+rs.getString(3)+">"+rs.getString(1)+"</option>");
		}
		rs.getStatement().getConnection().close();
	} catch (SQLException e) {
		out.print("Error "+e);
		e.printStackTrace();
	}
	out.print("</select></td>");
	}
	
	
	if(va.equals("14")){ ////Tabla de datos generales de debito, credito y diferencia
		//out.print("<table width='100%'  border='0' class='style6'><tr><td colspan='5'><div align='center' class='style11' id='cabecera2'>Datos Generales</div></td></tr>");
		out.print("<table width='100%' border='0' class='style6' ><tr BGCOLOR='#D3D3D3'><td   colspan='4'><div align='center'>Datos Generales</div></td></tr>"); 
		//out.print("<tr><td  width='50%'><div align='lefth'><label><input name='radiobutton' type='radio' value='radiobutton' id='10' onclick='RadioDoc(2,&quot;"+pc+"&quot;,"+ac+","+p+","+b+")' />Crear Documento</label></div></td>");
		//out.print("<td width='50%'><div align='lefth'><label><input name='radiobutton' type='radio' value='radiobutton' id='12' onclick='RadioDoc(1,&quot;"+pc+"&quot;,"+ac+","+p+","+b+")' />Consultar Documento</label></div></td></tr></table>");
		out.print("<tr><td width='30%'></td><td width='30%'></td><td width='20%'><div align='lefth'>DEBITOS:</div></td><td  width='20%'><div align='rigth'><input type=text id='debitos' style='width: 100%' value='"+vdebito+"' ></div></td></tr>");//value='"+FormatoMoneda(vdebito+"")+"'
		out.print("<tr><td></td><td></td><td><div align='lefth'>CREDITOS:</div></td><td><div align='rigth'><input type=text id='debitos' style='width: 100%' value='"+vcredito+"' ></div></td></tr>");//value='"+FormatoMoneda(vcredito+"")+"'
		double diferencia=Double.parseDouble(vdebito)-Double.parseDouble(vcredito);
		//System.out.println("Diferencia"+diferencia);
		if(diferencia==0){
		out.print("<tr><td></td><td></td><td><div align='lefth'>DIFERENCIA:</div></td><td><div align='rigth'><input type=text id='debitos' style='width: 100%' value='"+diferencia+"' ></div></td></tr></table>"); //value='"+FormatoMoneda(diferencia+"")+"'
		}else{
		out.print("<tr><td></td><td></td><td><div align='lefth'>DIFERENCIA:</div></td><td><div align='rigth'><input type=text id='debitos' class='style8' style='width: 100%' value='"+diferencia+"' ></div></td></tr></table>");//value='"+FormatoMoneda(diferencia+"")+"'
		}
		
		out.print("<table width='100%' border='0' ><tr><td colspan='3'></td></tr>");
		out.print("<tr><td width='45%'><div align='right'><input type='button' value='  Guardar  ' onClick='GuardarDetalle(&quot;"+docu+"&quot;,"+li+","+vdebito+","+vcredito+","+ac+",&quot;"+pc+"&quot;)' ></div></td>");
		out.print("<td width='10%'></td>");
		out.print("<td width='45%'><div align='left'><input type='button' value='  Cancelar  ' onClick='EliminarDetalle(&quot;"+docu+"&quot;,"+li+")'></div></td></tr></table>");
	/**/	
	}
	
	
	if(va.equals("15")){ ////INgresa el detalle a la base de datos
		
		String V[] = new String[3200];
		String M[][] = new String[200][16];

		int i1=0;
		StringTokenizer ele;  
		ele = new StringTokenizer(Ndatos,"|"); 
		
		while(ele.hasMoreTokens()){ 
		   V[i1] = ele.nextToken();   
		 //  System.out.println("15V["+i1+"]:"+V[i1]);
		   i1++;
		}

		int in=0;
		for(int z=0; z<=l; z++){
		  for(int zz=0; zz<16; zz++){
			//  System.out.println("in:"+in+" M["+z+"]["+zz+"]="+V[in]);
			M[z][zz]=V[in];
			in++;
		  }
		}
		
		/***  INGRESAR A LA DB DETALLE DE DOCUMENTOS  ****////LA PARTE MAS IMPORTANTE
	
		int ln=l+1;
		for(int i=0;i<=l;i++){///este es el for para crear cada linea del detalle en la db
		//////Crea el detalle	
		md.CrearDetalleDocumento(docu,M[i][1],M[i][4],M[i][6],M[i][9],M[i][10],M[i][11],M[i][12],M[i][13],M[i][14],"0","CNT");
		
		
		/***  Consultar y crear el Acumulado sucursal centro de costo *****////////////
		
		rs = md.CentrosySubcentrosdeCosto(M[i][6]);
		String vcco="";
		String vscc="";
		try {
			while(rs.next()){
				vcco=rs.getString(1);
				vscc=rs.getString(2);
			}
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		
		///Verificar si la relacion a�o,cta,succntro y centrosubcentro existe
		String codasc=""; 
		rs = md.Acumulado_Sucursal_Centrocco(M[i][4],M[i][6],M[i][1],ac);
		int sw=0;
		try {
			if(rs.next()){
				sw=1;
				codasc=rs.getString(1);
			}
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		
		if(sw==0){//Si la relacion en Acumulado_sucursal_centrodecosto no existe
			md.CrearAcumulado_Sucursal_Centrocco(M[i][4],M[i][6],M[i][1],ac);//Se crea la nueva relacion y se procede a crear los periodos
			rs = md.Acumulado_Sucursal_Centrocco(M[i][4],M[i][6],M[i][1],ac);//Nos traemos el codigo de la relacion q acabamos de crear
			try {
				if(rs.next()){
					codasc=rs.getString(1);
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			int acaa=(Integer.parseInt(ac)-1);
			String acaas=String.valueOf(acaa);
			String snp13="0";
			rs = md.Acumulado_Sucursal_Centrocco(M[i][4],M[i][6],M[i][1],acaas);//Consltamos si existe esa relacion en el a�o anterior para buscar en el detalle el saldo final del periodo 13
			int sw2=0;
			try {
				if(rs.next()){
					sw2=1;
					////////////////////
					rs1 = md.Detalle_Acumulado_Sucursal_CentroccoP13(rs.getString(1));//Consultamos el periodo 13 del a�o anterior y nos traemos el saldo nuevo
					try {
						if(rs1.next()){
							snp13=rs1.getString(1);
						}
						rs1.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}	
					///////////////////////
					
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			String pp="0";
			String dp="0";
			String cp="0";
			String snp01="";
			for(int j=1; j<=13; j++){
				if(j<10){pp="0"+j;}else{pp=String.valueOf(j);}
				if(pc.equals(pp)){dp=M[i][13]; cp=M[i][14];}//si periodo=j hay q cambiar el valor del debito y credito ysegun la naturaleza ejecutar la formula)
				else{dp="0"; cp="0";}
				
				String natucta=M[i][0].substring(0, 1);//selecciona el inicio del nombre de la cuenta para saber la naturaleza
				if(natucta.equals("1")||natucta.equals("5")||natucta.equals("6")||natucta.equals("7")||natucta.equals("8")){
					long snp01int=(Long.parseLong(snp13)+Long.parseLong(dp)-Long.parseLong(cp));
					System.out.println("ESTE ES EL RESUKTADO DE LA OPERACION DEBITO snp01int"+snp01int);
					snp01=String.valueOf(snp01int);             
				}
				if(natucta.equals("2")||natucta.equals("3")||natucta.equals("4")||natucta.equals("9")){
					long snp01int=(Long.parseLong(snp13)-Long.parseLong(dp)+Long.parseLong(cp));
					System.out.println("ESTE ES EL RESUKTADO DE LA OPERACION CREDITO snp01int"+snp01int);
					snp01=String.valueOf(snp01int);   
				}
				
				md.CrearDetalle_Acumulado_Sucursal_Centrocco(codasc,pp,snp13,dp,cp,snp01);//crea el detalle x periodo del detalle_acumulado_sucursal_centrocco
				snp13=snp01;//Al saldo anterior le asigno el saldo nuevo para el proximo periodo
		 
			}//fin for j
			
		}///fin sw==0
		else{//si la relacion si existe solo tocaria actualizar
			
			String sac="0";
			String sdc="0";
			String scc="0";
			String snc="0";
			
			rs = md.Detalle_Acumulado_Sucursal_CentroccoPeriodo(codasc,pc);//Consultamos el periodo 13 del a�o anterior y nos traemos el saldo nuevo
			try {
				if(rs.next()){
					sac=rs.getString(1);
					sdc=rs.getString(2);
					scc=rs.getString(3);
					snc=rs.getString(4);
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}	
			sdc=String.valueOf(Long.parseLong(sdc)+Long.parseLong(M[i][13]));
			scc=String.valueOf(Long.parseLong(scc)+Long.parseLong(M[i][14]));
		
			String natucta=M[i][0].substring(0, 1);//selecciona el inicio del nombre de la cuenta para saber la naturaleza
			if(natucta.equals("1")||natucta.equals("5")||natucta.equals("6")||natucta.equals("7")||natucta.equals("8")){
				long sncint=(Long.parseLong(sac)+Long.parseLong(sdc)-Long.parseLong(scc));
				System.out.println("ESTE ES EL RESUKTADO DE LA OPERACION DEBITO sncint"+sncint);
				snc=String.valueOf(sncint);             
			}
			if(natucta.equals("2")||natucta.equals("3")||natucta.equals("4")||natucta.equals("9")){
				long sncint=(Long.parseLong(sac)-Long.parseLong(sdc)+Long.parseLong(scc));
				System.out.println("ESTE ES EL RESUKTADO DE LA OPERACION CREDITO sncint"+sncint);
				snc=String.valueOf(sncint);   
			}
			
			md.ActualizarDetalle_Acumulado_Sucursal_Centrocco(sac,sdc,scc,snc,codasc,pc);//Actualiza el detalle con los valores del debito y credito y el saldo nuevo
			
			String pp2="";
			int inicio=Integer.parseInt(pc)+1;
			String sac2="0";
			String sdc2="0";
			String scc2="0";
			String snc2="0";
			
			for (int x=inicio; x<14; x++){// for que me ayuda a actualizar los registros de todos los periodos hacia abajo
				if(x<10){pp2="0"+x;}else{pp2=String.valueOf(x);}
				rs = md.Detalle_Acumulado_Sucursal_CentroccoPeriodo(codasc,pp2);//Consultamos el periodo 13 del a�o anterior y nos traemos el saldo nuevo
				try {
					if(rs.next()){
						//sac2=rs.getString(1);
						sdc2=rs.getString(2);
						scc2=rs.getString(3);
						//snc2=rs.getString(4);
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}	
				
				String natucta2=M[i][0].substring(0, 1);//selecciona el inicio del nombre de la cuenta para saber la naturaleza
				if(natucta2.equals("1")||natucta2.equals("5")||natucta2.equals("6")||natucta2.equals("7")||natucta2.equals("8")){
					snc2=String.valueOf(Long.parseLong(snc)+Long.parseLong(sdc2)-Long.parseLong(scc2));             
				}
				if(natucta2.equals("2")||natucta2.equals("3")||natucta2.equals("4")||natucta2.equals("9")){
					snc2=String.valueOf(Long.parseLong(snc)-Long.parseLong(sdc2)+Long.parseLong(scc2));   
				}
				
				md.ActualizarDetalle_Acumulado_Sucursal_Centrocco(snc,sdc2,scc2,snc2,codasc,pp2);//Actualiza el detalle hacia abajo con el saldo anterior
			snc=snc2;
			}//fin for <14
				
		}//fin del else la relacion de acumulados sucursalcentrossubcentros si existe
		
		
		
		
		
		/****EMPIEZA EL ACUMULADO TERCERO***/
		
		
		
		
		///Verificar si la relacion tercero,cta,a�o,succntro y centrosubcentro existe
		String codat=""; 
		rs = md.Acumulado_Terceros(M[i][9],M[i][1],ac,M[i][4],M[i][6]);
		int swt=0;
		try {
			if(rs.next()){
				swt=1;
				codat=rs.getString(1);
			}
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		
		if(swt==0){//Si la relacion en Acumuladotercero no existe
			md.CrearAcumulado_Terceros(M[i][9],M[i][1],ac,M[i][4],M[i][6]);//Se crea la nueva relacion y se procede a crear los periodos
			rs = md.Acumulado_Terceros(M[i][9],M[i][1],ac,M[i][4],M[i][6]);//Nos traemos el codigo de la relacion q acabamos de crear
			try {
				if(rs.next()){
					codat=rs.getString(1);
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			int acaa=(Integer.parseInt(ac)-1);
			String acaas=String.valueOf(acaa);
			String snp13="0";
			rs = md.Acumulado_Terceros(M[i][9],M[i][1],acaas,M[i][4],M[i][6]);//Consltamos si existe esa relacion en el a�o anterior para buscar en el detalle el saldo final del periodo 13
			int sw2=0;
			try {
				if(rs.next()){
					sw2=1;
					////////////////////
					rs1 = md.Detalle_Acumulado_TerceroP13(rs.getString(1));//Consultamos el periodo 13 del a�o anterior y nos traemos el saldo nuevo
					try {
						if(rs1.next()){
							snp13=rs1.getString(1);
						}
						rs1.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}	
					///////////////////////
					
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			String pp="0";
			String dp="0";
			String cp="0";
			String snp01="";
			for(int j=1; j<=13; j++){
				if(j<10){pp="0"+j;}else{pp=String.valueOf(j);}
				if(pc.equals(pp)){dp=M[i][13]; cp=M[i][14];}//si periodo=j hay q cambiar el valor del debito y credito ysegun la naturaleza ejecutar la formula)
				else{dp="0"; cp="0";}
				
				String natucta=M[i][0].substring(0, 1);//selecciona el inicio del nombre de la cuenta para saber la naturaleza
				if(natucta.equals("1")||natucta.equals("5")||natucta.equals("6")||natucta.equals("7")||natucta.equals("8")){
					long snp01int=(Long.parseLong(snp13)+Long.parseLong(dp)-Long.parseLong(cp));
					System.out.println("ESTE ES EL RESUKTADO DE LA OPERACION DEBITO snp01int"+snp01int);
					snp01=String.valueOf(snp01int);             
				}
				if(natucta.equals("2")||natucta.equals("3")||natucta.equals("4")||natucta.equals("9")){
					long snp01int=(Long.parseLong(snp13)-Long.parseLong(dp)+Long.parseLong(cp));
					System.out.println("ESTE ES EL RESUKTADO DE LA OPERACION CREDITO snp01int"+snp01int);
					snp01=String.valueOf(snp01int);   
				}
				
				md.CrearDetalle_Acumulado_Tercero(codat,pp,snp13,dp,cp,snp01);//crea el detalle x periodo del detalle_acumulado_tercero
				snp13=snp01;//Al saldo anterior le asigno el saldo nuevo para el proximo periodo
		 
			}//fin for j
			
		}///fin swt==0
		else{//si la relacion si existe solo tocaria actualizar el tercero
			
			String sac="0";
			String sdc="0";
			String scc="0";
			String snc="0";
			
			rs = md.Detalle_Acumulado_Tercero(codat,pc);//Consultamos el periodo 13 del a�o anterior y nos traemos el saldo nuevo
			try {
				if(rs.next()){
					sac=rs.getString(1);
					sdc=rs.getString(2);
					scc=rs.getString(3);
					snc=rs.getString(4);
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}	
			sdc=String.valueOf(Long.parseLong(sdc)+Long.parseLong(M[i][13]));
			scc=String.valueOf(Long.parseLong(scc)+Long.parseLong(M[i][14]));
		
			String natucta=M[i][0].substring(0, 1);//selecciona el inicio del nombre de la cuenta para saber la naturaleza
			if(natucta.equals("1")||natucta.equals("5")||natucta.equals("6")||natucta.equals("7")||natucta.equals("8")){
				long sncint=(Long.parseLong(sac)+Long.parseLong(sdc)-Long.parseLong(scc));
				//System.out.println("ESTE ES EL RESUKTADO DE LA OPERACION DEBITO sncint"+sncint);
				snc=String.valueOf(sncint);             
			}
			if(natucta.equals("2")||natucta.equals("3")||natucta.equals("4")||natucta.equals("9")){
				long sncint=(Long.parseLong(sac)-Long.parseLong(sdc)+Long.parseLong(scc));
				//System.out.println("ESTE ES EL RESUKTADO DE LA OPERACION CREDITO sncint"+sncint);
				snc=String.valueOf(sncint);   
			}
			
			md.ActualizarDetalle_Acumulado_Tercero(sac,sdc,scc,snc,codat,pc);//Actualiza el detalle con los valores del debito y credito y el saldo nuevo
			
			String pp2="";
			int inicio=Integer.parseInt(pc)+1;
			String sac2="0";
			String sdc2="0";
			String scc2="0";
			String snc2="0";
			
			for (int x=inicio; x<14; x++){// for que me ayuda a actualizar los registros de todos los periodos hacia abajo
				if(x<10){pp2="0"+x;}else{pp2=String.valueOf(x);}
				rs = md.Detalle_Acumulado_Tercero(codat,pp2);//Consultamos el periodo 13 del a�o anterior y nos traemos el saldo nuevo
				try {
					if(rs.next()){
						//sac2=rs.getString(1);
						sdc2=rs.getString(2);
						scc2=rs.getString(3);
						//snc2=rs.getString(4);
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}	
				
				String natucta2=M[i][0].substring(0, 1);//selecciona el inicio del nombre de la cuenta para saber la naturaleza
				if(natucta2.equals("1")||natucta2.equals("5")||natucta2.equals("6")||natucta2.equals("7")||natucta2.equals("8")){
					snc2=String.valueOf(Long.parseLong(snc)+Long.parseLong(sdc2)-Long.parseLong(scc2));             
				}
				if(natucta2.equals("2")||natucta2.equals("3")||natucta2.equals("4")||natucta2.equals("9")){
					snc2=String.valueOf(Long.parseLong(snc)-Long.parseLong(sdc2)+Long.parseLong(scc2));   
				}
				
				md.ActualizarDetalle_Acumulado_Tercero(snc,sdc2,scc2,snc2,codat,pp2);//Actualiza el detalle hacia abajo con el saldo anterior
			snc=snc2;
			}//fin for <14
				
		}//fin del else la relacion de acumulados sucursalcentrossubcentros si existe
		
	
	
		}//fin del for para crear cada linea
		
		/////Actualiza el valor total del documento
		md.ActualizarDocumentoDebitoCredito(docu, vdebito, vcredito);
		
		String tdche="";
		rs = md.ConsultarTipoDocumento(docu);
		try {
			if(rs.next()){
			tdche=rs.getString(1)+"|"+rs.getString(2)+"|"+rs.getString(3)+"|"+rs.getString(4);
			}
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		
		out.print(tdche);
	}
	
	
	if(va.equals("16")){ ////INgresa el detalle a la base de datos
			
		md.CrearDetalleDocumento(docu,"0","0","0","0","ELIMINADO","0","0","0","0","0","CNT");
		//deberia haber un estado en documento para eliminarlo
		out.print("Documeno Eliminado!!!!");
	}
	
	
	if(va.equals("17")){ ////Consultar documentos
		//  System.out.println("17171717");
		String sql="";
		if((docu!=null)&&(!docu.equals(""))){sql=sql+" AND numero_documento='"+docu+"'";}
		if((ac!=null)&&(!ac.equals(""))){sql=sql+" AND anio='"+ac+"'";}
		if((pc!=null)&&(!pc.equals(""))){sql=sql+" AND periodo='"+pc+"'";}
		//out.print("<table width='100%' border='1' ><tr><td colspan='4'></td></tr>");
		out.print("<table width='100%' border='0' class='style6'><tr><td colspan='6'><div align='center' class='style11' id='cabecera2'>Resultados de la Busqueda</div></td></tr>");
		out.print("<table width='100%' border='0' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='10%'><i><div align='center'>ACJo</div></i></td><td width='10%'><i><div align='center'>Periodo</div></i></td><td width='20%'><i><div align='center'>Nro. Documento</div></i></td><td width='60%'><i><div align='center'>Descripcion</div></i></td></tr>");  //
		int sw=0;
		rs = md.ConsultarDocumentos(sql);
		try {
			while(rs.next()){
			sw=1;
			out.print("<tr><td><div align='center'>"+rs.getString(2)+"</div></td><td><div align='center'>"+rs.getString(3)+"</div></td><td><div align='center'><a  href='#'onclick='ImprimirDocumento2("+rs.getString(1)+")'>"+rs.getString(5)+"</div></td><td>"+rs.getString(7)+"</td>");
			
			}
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		if(sw==0){//No trajo nada la consulta
			out.print("<td colspan='4'>No existen resultados para sus parametros de busqueda!!!</td>");
		}
		
		out.print("</tr></table></table>");
	
	}
	
	if(va.equals("18")){ ////Consultar documentos a modificar
		//  System.out.println("17171717");
		String sql="";
		if((docu!=null)&&(!docu.equals(""))){sql=sql+" AND numero_documento='"+docu+"'";}
			
		if((aco!=null)&&(!aco.equals(""))){sql=sql+" AND detalle LIKE '%"+aco+"%'";
		}

		if((pco!=null)&&(!pco.equals(""))){sql=sql+" AND tipo_documento_fk='"+pco+"'";}

		if((ac!=null)&&(!ac.equals(""))){sql=sql+" AND anio='"+ac+"'";}
		if((pc!=null)&&(!pc.equals(""))){sql=sql+" AND periodo='"+pc+"'";}
		//out.print("<table width='100%' border='1' ><tr><td colspan='4'></td></tr>");
		out.print("<table width='100%' border='0' class='style6'><tr><td colspan='6'><div align='center' class='style11' id='cabecera2'>Resultados de la Busqueda</div></td></tr>");
		out.print("<table width='100%' border='0' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='10%'><i><div align='center'>ACJo</div></i></td><td width='10%'><i><div align='center'>Periodo</div></i></td><td width='20%'><i><div align='center'>Nro. Documento</div></i></td><td width='60%'><i><div align='center'>Descripcion</div></i></td></tr>");  //
		int sw=0;
		rs = md.ConsultarDocumentosaModificar(sql);
		try {
			while(rs.next()){
			sw=1;
			out.print("<tr><td><div align='center'>"+rs.getString(2)+"</div></td><td><div align='center'>"+rs.getString(3)+"</div></td><td><div align='center'><a  href='#' onclick='ModificarDocumento("+rs.getString(1)+",&quot;"+ac+"&quot;,&quot;"+pc+"&quot;)'>"+rs.getString(5)+"</div></td><td>"+rs.getString(7)+"</td></tr>");
			//out.print("<tr><td><div align='center'>"+rs.getString(2)+"</div></td><td><div align='center'>"+rs.getString(3)+"</div></td><td><div align='center'><a  href='#' onclick='ModificarDocumento("+rs.getString(1)+","+ac+",&quot;"+pc+"&quot;)'>"+rs.getString(5)+"</div></td><td>"+rs.getString(7)+"</td></tr>");
			
			}
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		if(sw==0){//No trajo nada la consulta
			out.print("<td colspan='4'>No existen resultados para sus parametros de busqueda!!!</td>");
		}
		
		out.print("</tr></table></table>");
	
	}
	
	if(va.equals("19")){ //Traer los datos del documento a modificar
	
	out.print("<table width='100%' border='0' class='style6'><tr><td colspan='6'><div align='center' class='style11' id='cabecera2'>Descripcion del Documento</div></td></tr>");
			
		
	out.print("<table width='100%' border='0' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='2%'><i><div align='center'>ITEM</div></i></td><td width='10%'><i><div align='center'>CUENTA</div></i></td><td width='6%'><i><div align='center'>SUCURSAL</div></i></td><td width='6%'><i><div align='center'>C.COSTO</div></i></td><td width='6%'><i><div align='center'>SUBC.COSTO</div></i></td><td width='9%'><i><div align='center'>TERCERO</div></i></td><td width='19%'><i><div align='center'>DESCRIPCION</div></i></td><td width='7%'><i><div align='center'>BASE</div></i></td><td width='7%'><i><div align='center'>DOC.REF</div></i></td><td width='8%'><i><div align='center'>DEBITO</div></i></td><td width='8%'><i><div align='center'>CREDITO</div></i></td><td width='8%'><i><div align='center'>DIFERIDO</div></i></td><td width='4%'><i><div align='center'>ACCION</div></i></td></tr>");  //

	rs = md.ConsultarDocumentosyDetalles(docu);
	int i=0;
	String dife="";
	
	try {
		while(rs.next()){
		////////////////////////////hay un input hidden idden+i q en 0 viene en la consulta, en 2 se elimina y en 4 es nueva linea
			out.print("<tr><td><div align='center'>"+(i+1)+"</div><input type=hidden id='cdh"+i+"'  value='"+rs.getString(9)+"'><input type=hidden id='ideen"+i+"'  value='0'></td>");
			out.print("<td><input type=text id='cta"+i+"' style='width: 100%' onkeyup='autocompletaCta("+i+","+i+")' onBlur='limpiacta("+i+")' value='"+rs.getString(12)+"'>");
			out.print("<input type=hidden id='ctah"+i+"'  value='"+rs.getString(11)+"'></td>");
			out.print("<td><select  style='width:100%' id='suc"+i+"'  onChange='lcco("+i+")'><option value='"+rs.getString(13)+"'>"+rs.getString(13)+"</option>");
			rs1 = md.SucursalesDiferentes(rs.getString(14));
			try {
				while(rs1.next()){
					out.print("<option title='"+rs1.getString(2)+"' value="+rs1.getString(1)+">"+rs1.getString(1)+"</option>");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("</select></td>");
			out.print("<td><div id='ncco"+i+"'><select  style='width:100%' id='cco"+i+"'><option value='"+rs.getString(13)+"'>"+rs.getString(15)+"</option>");
			out.print("</select></div></td>");
			out.print("<td><div id='nsco"+i+"'><select  style='width:100%' id='scc"+i+"'><option value='"+rs.getString(16)+"'>"+rs.getString(17)+"</option>");
			out.print("</select></div></td>");
			out.print("<td><input type=text id='ter"+i+"' style='width: 100%' onkeyup='autocompletarTercero("+i+","+i+")' onBlur='limpiater("+i+")' value='"+rs.getString(19)+"'>");
			out.print("<input type=hidden id='terh"+i+"' value='"+rs.getString(18)+"'></td>");
			out.print("<td><input type=text id='des"+i+"' style='width: 100%' value='"+rs.getString(20)+"'></td>");
			out.print("<td><input type=text id='bas"+i+"' style='width: 100%' value='"+rs.getString(21)+"' onkeyup='vnb("+i+",1)' ></td>");
			out.print("<td><input type=text id='ref"+i+"' style='width: 100%' value='"+rs.getString(22)+"'></td>");
			out.print("<td><input type=text id='deb"+i+"' style='width: 100%' value='"+rs.getString(23)+"' onkeyup='vnb("+i+",2);exluye("+i+",1)' value='0'></td>");
			out.print("<td><input type=text id='cre"+i+"' style='width: 100%' value='"+rs.getString(24)+"' onkeyup='vnb("+i+",3);exluye("+i+",2)' value='0'></td>");
			if(rs.getString(25).equals("0")){dife="N/A";}else{dife=rs.getString(25);}
			out.print("<td><input type=text id='dif"+i+"' style='width: 100%' disabled='true'   value='"+dife+"'></td>");
			out.print("<td><a  href='#'onclick='EliminarLinea("+docu+",&quot;"+ac+"&quot;,&quot;"+pc+"&quot;,"+(i+1)+",1)'><div align='center'>Eliminar</div></a></td></tr></tr>");
			out.print("<tr><td></td><td><div id='dcta"+i+"'></div></td><td></td><td></td><td></td><td><div id='dter"+i+"' ></div></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>");
			
			//out.print("<td><a  href='#'onclick='Nuevalinea(0,"+doc+","+ac+",&quot;"+pc+"&quot;)'><div align='center'>Crear</div></a></td></tr>");
			
			
			i++;
			//////////////////////////////	
		}
		rs.getStatement().getConnection().close();
	} catch (SQLException e) {
		out.print("Error "+e);
		e.printStackTrace();
	}
	
	/////////creacion del nuevo////////////////
		
	out.print("<tr><td><div align='center'>"+(i+1)+"</div><input type=hidden id='cdh"+i+"'   value='0'><input type=hidden id='ideen"+i+"'  value='4'></td>");
	out.print("<td><input type=text id='cta"+i+"' style='width: 100%' onkeyup='autocompletaCta("+i+","+i+")' onBlur='limpiacta("+i+")' >");
	out.print("<input type=hidden id='ctah"+i+"' ></td>");
	out.print("<td><select  style='width:100%' id='suc"+i+"'  onChange='lcco("+i+")'><option value='0'>Seleccione</option>");
	rs = md.Sucursales();
	try {
		while(rs.next()){
			out.print("<option title='"+rs.getString(2)+"' value="+rs.getString(1)+">"+rs.getString(1)+"</option>");
		}
		rs.getStatement().getConnection().close();
	} catch (SQLException e) {
		out.print("Error "+e);
		e.printStackTrace();
	}
	out.print("</select></td>");
	out.print("<td><div id='ncco"+i+"'><select  style='width:100%' id='cco"+i+"'><option value='0'>Seleccione</option>");
	out.print("</select></div></td>");
	out.print("<td><div id='nsco"+i+"'><select  style='width:100%' id='scc"+i+"'><option value='0'>Seleccione</option>");
	out.print("</select></div></td>");
	out.print("<td><input type=text id='ter"+i+"' style='width: 100%' onkeyup='autocompletarTercero("+i+","+i+")' onBlur='limpiater("+i+")' >");
	out.print("<input type=hidden id='terh"+i+"' ></td>");
	out.print("<td><input type=text id='des"+i+"' style='width: 100%' ></td>");
	out.print("<td><input type=text id='bas"+i+"' style='width: 100%' onkeyup='vnb("+i+",1)'  value='0'></td>");
	out.print("<td><input type=text id='ref"+i+"' style='width: 100%' ></td>");
	out.print("<td><input type=text id='deb"+i+"' style='width: 100%'  onkeyup='vnb("+i+",2);exluye("+i+",1)' value='0'></td>");
	out.print("<td><input type=text id='cre"+i+"' style='width: 100%'  onkeyup='vnb("+i+",3);exluye("+i+",2)' value='0'></td>");
	out.print("<td><input type=text id='dif"+i+"' style='width: 100%' disabled='true'   value='N/A' ></td>");
	out.print("<td><a  href='#'onclick='EliminarLinea("+docu+",&quot;"+ac+"&quot;,&quot;"+pc+"&quot;,"+(i+1)+",2)'><div align='center'>Crear</div></a></td></tr></tr>");
	out.print("<tr><td></td><td><div id='dcta"+i+"'></div></td><td></td><td></td><td></td><td><div id='dter"+i+"' ></div></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>");


		////////////////
		
		
		
	out.print("<input type=hidden id='totale'  value='"+(i-1)+"'>");
	out.print("</table><table width='100%' border='0' ><tr><td></td></tr>");
	out.print("<tr><td></td></tr><tr><td></td></tr><tr><td width='45%'><div align='center'><input type='button' value='  Modificar  ' onClick='ModificarDetalle(&quot;"+docu+"&quot;,"+(i-1)+",&quot;"+ac+"&quot;,&quot;"+pc+"&quot;)' ></div></td></tr></table>");



}
	
	
	if(va.equals("19a")){ //Traer los datos del documento a modificar
		
		String V[] = new String[3600];
		String M[][] = new String[200][18];

		int i1=0;
		StringTokenizer ele;  
		ele = new StringTokenizer(Ndatos,"|"); 
		
		while(ele.hasMoreTokens()){ 
		   V[i1] = ele.nextToken();   
		   //System.out.println("15V["+i1+"]:"+V[i1]);
		   i1++;
		}

		int in=0;
		for(int z=0; z<=l; z++){
		  for(int zz=0; zz<18; zz++){
		//	  System.out.println("in:"+in+" M["+z+"]["+zz+"]="+V[in]);
			M[z][zz]=V[in];
			
			in++;
		  }
		}
			
		out.print("<table width='100%' border='0' class='style6'><tr><td colspan='6'><div align='center' class='style11' id='cabecera2'>Descripcion del Documento</div></td></tr>");
				
			
		out.print("<table width='100%' border='0' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='2%'><i><div align='center'>ITEM</div></i></td><td width='10%'><i><div align='center'>CUENTA</div></i></td><td width='6%'><i><div align='center'>SUCURSAL</div></i></td><td width='6%'><i><div align='center'>C.COSTO</div></i></td><td width='6%'><i><div align='center'>SUBC.COSTO</div></i></td><td width='9%'><i><div align='center'>TERCERO</div></i></td><td width='19%'><i><div align='center'>DESCRIPCION</div></i></td><td width='7%'><i><div align='center'>BASE</div></i></td><td width='7%'><i><div align='center'>DOC.REF</div></i></td><td width='8%'><i><div align='center'>DEBITO</div></i></td><td width='8%'><i><div align='center'>CREDITO</div></i></td><td width='8%'><i><div align='center'>DIFERIDO</div></i></td><td width='4%'><i><div align='center'>ACCION</div></i></td></tr>");  //

		

		String dife="";
			
			////////////////////////////hay un input hidden idden+i q en 0 viene en la consulta, en 2 se elimina y en 4 es nueva linea
		int ln=l+1;
		//out.print("<table width='100%' border='0' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='2%'><i><div align='center'>ITEM</div></i></td><td width='10%'><i><div align='center'>CUENTA</div></i></td><td width='6%'><i><div align='center'>SUCURSAL</div></i></td><td width='6%'><i><div align='center'>C.COSTO</div></i></td><td width='6%'><i><div align='center'>SUBC.COSTO</div></i></td><td width='9%'><i><div align='center'>TERCERO</div></i></td><td width='19%'><i><div align='center'>DESCRIPCION</div></i></td><td width='7%'><i><div align='center'>BASE</div></i></td><td width='7%'><i><div align='center'>DOC.REF</div></i></td><td width='8%'><i><div align='center'>DEBITO</div></i></td><td width='8%'><i><div align='center'>CREDITO</div></i></td><td width='8%'><i><div align='center'>DIFERIDO</div></i></td><td width='4%'><i><div align='center'>ACCION</div></i></td></tr>");  //
		for(int i=0;i<=l;i++){///este es el pfor para las lineas creadas
			
							
				if(M[i][16].equals("2")){
					out.println("<tr BGCOLOR='#D3D3D3'><td><div align='center'><FONT COLOR=red>"+(i+1)+"</FONT></div><input type=hidden id='cdh"+i+"'  value='"+M[i][17]+"'><input type=hidden id='ideen"+i+"'  value='"+M[i][16]+"'></td>");
					out.print("<td><input type=text id='cta"+i+"' style='width: 100%' onkeyup='autocompletaCta("+i+","+i+")' onBlur='limpiacta("+i+")' value='"+M[i][0]+"' disabled='true' >");
					out.print("<input type=hidden id='ctah"+i+"'  value='"+M[i][1]+"'></td>");
					out.print("<td><select  style='width:100%' id='suc"+i+"'  onChange='lcco("+i+")' disabled='true' ><option value='"+M[i][2]+"'>"+M[i][3]+"</option>");
					rs1 = md.SucursalesDiferentes(M[i][2]);
					try {
						while(rs1.next()){
							out.print("<option title='"+rs1.getString(2)+"' value="+rs1.getString(1)+">"+rs1.getString(1)+"</option>");
						}
						rs1.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					out.print("</select></td>");
					out.print("<td><div id='ncco"+i+"'><select  style='width:100%' id='cco"+i+"' disabled='true' ><option value='"+M[i][4]+"'>"+M[i][5]+"</option>");
					out.print("</select></div></td>");
					out.print("<td><div id='nsco"+i+"'><select  style='width:100%' id='scc"+i+"' disabled='true' ><option value='"+M[i][6]+"'>"+M[i][7]+"</option>");
					out.print("</select></div></td>");
					out.print("<td><input type=text id='ter"+i+"' style='width: 100%' onkeyup='autocompletarTercero("+i+","+i+")' onBlur='limpiater("+i+")' value='"+M[i][8]+"' disabled='true' >");
					out.print("<input type=hidden id='terh"+i+"' value='"+M[i][9]+"' ></td>");
					out.print("<td><input type=text id='des"+i+"' style='width: 100%' value='"+M[i][10]+"' disabled='true' ></td>");
					out.print("<td><input type=text id='bas"+i+"' style='width: 100%' value='"+M[i][11]+"' onkeyup='vnb("+i+",1)' disabled='true' ></td>");
					out.print("<td><input type=text id='ref"+i+"' style='width: 100%' value='"+M[i][12]+"' disabled='true' ></td>");
					out.print("<td><input type=text id='deb"+i+"' style='width: 100%' value='0' onkeyup='vnb("+i+",2);exluye("+i+",1)' disabled='true'  ></td>");
					out.print("<td><input type=text id='cre"+i+"' style='width: 100%' value='0' onkeyup='vnb("+i+",3);exluye("+i+",2)' disabled='true' ></td>");
					if(M[i][15].equals("0")){dife="N/A";}else{dife=M[i][15];}
					out.print("<td><input type=text id='dif"+i+"' style='width: 100%' disabled='true'   value='"+dife+"'></td>");
					out.print("<td><div align='center'><FONT COLOR=red>Eliminado</FONT></div></td></tr></tr>");		
				}else{
					out.print("<tr><td><div align='center'>"+(i+1)+"</div><input type=hidden id='cdh"+i+"'  value='"+M[i][17]+"'><input type=hidden id='ideen"+i+"'  value='"+M[i][16]+"'></td>");	
					out.print("<td><input type=text id='cta"+i+"' style='width: 100%' onkeyup='autocompletaCta("+i+","+i+")' onBlur='limpiacta("+i+")' value='"+M[i][0]+"'  >");
					out.print("<input type=hidden id='ctah"+i+"'  value='"+M[i][1]+"'></td>");
					out.print("<td><select  style='width:100%' id='suc"+i+"'  onChange='lcco("+i+")'  ><option value='"+M[i][2]+"'>"+M[i][3]+"</option>");
					rs1 = md.SucursalesDiferentes(M[i][2]);
					try {
						while(rs1.next()){
							out.print("<option title='"+rs1.getString(2)+"' value="+rs1.getString(1)+">"+rs1.getString(1)+"</option>");
						}
						rs1.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					out.print("</select></td>");
					out.print("<td><div id='ncco"+i+"'><select  style='width:100%' id='cco"+i+"'  ><option value='"+M[i][4]+"'>"+M[i][5]+"</option>");
					out.print("</select></div></td>");
					out.print("<td><div id='nsco"+i+"'><select  style='width:100%' id='scc"+i+"'  ><option value='"+M[i][6]+"'>"+M[i][7]+"</option>");
					out.print("</select></div></td>");
					out.print("<td><input type=text id='ter"+i+"' style='width: 100%' onkeyup='autocompletarTercero("+i+","+i+")' onBlur='limpiater("+i+")' value='"+M[i][8]+"'  >");
					out.print("<input type=hidden id='terh"+i+"' value='"+M[i][9]+"' ></td>");
					out.print("<td><input type=text id='des"+i+"' style='width: 100%' value='"+M[i][10]+"'  ></td>");
					out.print("<td><input type=text id='bas"+i+"' style='width: 100%' value='"+M[i][11]+"' onkeyup='vnb("+i+",1)'  ></td>");
					out.print("<td><input type=text id='ref"+i+"' style='width: 100%' value='"+M[i][12]+"'  ></td>");
					out.print("<td><input type=text id='deb"+i+"' style='width: 100%' value='"+M[i][13]+"' onkeyup='vnb("+i+",2);exluye("+i+",1)' value='0'></td>");
					out.print("<td><input type=text id='cre"+i+"' style='width: 100%' value='"+M[i][14]+"' onkeyup='vnb("+i+",3);exluye("+i+",2)' value='0'></td>");
					if(M[i][15].equals("0")){dife="N/A";}else{dife=M[i][15];}
					out.print("<td><input type=text id='dif"+i+"' style='width: 100%'    value='"+dife+"'></td>");
					out.print("<td><a  href='#'onclick='EliminarLinea("+docu+",&quot;"+ac+"&quot;,&quot;"+pc+"&quot;,"+(i+1)+",1)'><div align='center'>Eliminar</div></a></td></tr></tr>");
		
				}

				out.print("<tr><td></td><td><div id='dcta"+i+"'></div></td><td></td><td></td><td></td><td><div id='dter"+i+"' ></div></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>");
				
		}//fin del for
		
/////////creacion del nuevo////////////////
		
		
		out.print("<tr><td><div align='center'>"+(ln+1)+"</div><input type=hidden id='cdh"+ln+"'   value='0'><input type=hidden id='ideen"+ln+"'  value='4'></td>");
		out.print("<td><input type=text id='cta"+ln+"' style='width: 100%' onkeyup='autocompletaCta("+ln+","+ln+")' onBlur='limpiacta("+ln+")' >");
		out.print("<input type=hidden id='ctah"+ln+"' ></td>");
		out.print("<td><select  style='width:100%' id='suc"+ln+"'  onChange='lcco("+ln+")'><option value='0'>Seleccione</option>");
		rs = md.Sucursales();
		try {
			while(rs.next()){
				out.print("<option title='"+rs.getString(2)+"' value="+rs.getString(1)+">"+rs.getString(1)+"</option>");
			}
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		out.print("</select></td>");
		out.print("<td><div id='ncco"+ln+"'><select  style='width:100%' id='cco"+ln+"'><option value='0'>Seleccione</option>");
		out.print("</select></div></td>");
		out.print("<td><div id='nsco"+ln+"'><select  style='width:100%' id='scc"+ln+"'><option value='0'>Seleccione</option>");
		out.print("</select></div></td>");
		out.print("<td><input type=text id='ter"+ln+"' style='width: 100%' onkeyup='autocompletarTercero("+ln+","+ln+")' onBlur='limpiater("+ln+")' >");
		out.print("<input type=hidden id='terh"+ln+"' ></td>");
		out.print("<td><input type=text id='des"+ln+"' style='width: 100%' ></td>");
		out.print("<td><input type=text id='bas"+ln+"' style='width: 100%' onkeyup='vnb("+ln+",1)'  value='0'></td>");
		out.print("<td><input type=text id='ref"+ln+"' style='width: 100%' ></td>");
		out.print("<td><input type=text id='deb"+ln+"' style='width: 100%'  onkeyup='vnb("+ln+",2);exluye("+ln+",1)' value='0'></td>");
		out.print("<td><input type=text id='cre"+ln+"' style='width: 100%'  onkeyup='vnb("+ln+",3);exluye("+ln+",2)' value='0'></td>");
		out.print("<td><input type=text id='dif"+ln+"' style='width: 100%' disabled='true'   value='N/A' ></td>");
		out.print("<td><a  href='#'onclick='EliminarLinea("+docu+",&quot;"+ac+"&quot;,&quot;"+pc+"&quot;,"+(ln+1)+",2)'><div align='center'>Crear</div></a></td></tr></tr>");
		out.print("<tr><td></td><td><div id='dcta"+ln+"'></div></td><td></td><td></td><td></td><td><div id='dter"+ln+"' ></div></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>");


			////////////////
				//////////////////////////////	
		
	//System.out.println("lnlnlnlnlnlnln: "+ln);
		out.print("<input type=hidden id='totale'  value='"+(ln-1)+"'>");
		out.print("</table><table width='100%' border='0' ><tr><td></td></tr>");
		out.print("<tr><td></td></tr><tr><td></td></tr><tr><td width='45%'><div align='center'><input type='button' value='  Modificar  ' onClick='ModificarDetalle(&quot;"+docu+"&quot;,"+(ln-1)+",&quot;"+ac+"&quot;,&quot;"+pc+"&quot;)' ></div></td></tr></table>");

	}
	
	
	if(va.equals("20")){ ////Consultar documentos a Eliminar
		//  System.out.println("17171717");
		String sql="";
		if((docu!=null)&&(!docu.equals(""))){sql=sql+" AND numero_documento='"+docu+"'";}
		if((ac!=null)&&(!ac.equals(""))){sql=sql+" AND anio='"+ac+"'";}
		if((pc!=null)&&(!pc.equals(""))){sql=sql+" AND periodo='"+pc+"'";}
		//out.print("<table width='100%' border='1' ><tr><td colspan='4'></td></tr>");
		out.print("<table width='100%' border='0' class='style6'><tr><td colspan='6'><div align='center' class='style11' id='cabecera2'>Resultados de la Busqueda</div></td></tr>");
		out.print("<table width='100%' border='0' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='10%'><i><div align='center'>ACJo</div></i></td><td width='10%'><i><div align='center'>Periodo</div></i></td><td width='20%'><i><div align='center'>Nro. Documento</div></i></td><td width='60%'><i><div align='center'>Descripcion</div></i></td></tr>");  //
		int sw=0;
		rs = md.ConsultarDocumentos(sql);
		try {
			while(rs.next()){
			sw=1;
			out.print("<tr><td><div align='center'>"+rs.getString(2)+"</div></td><td><div align='center'>"+rs.getString(3)+"</div></td><td><div align='center'><a  href='#'onclick='EliminarDocumento("+rs.getString(1)+","+ac+",&quot;"+pc+"&quot;)'>"+rs.getString(5)+"</div></td><td>"+rs.getString(7)+"</td>");
			
			}
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		if(sw==0){//No trajo nada la consulta
			out.print("<td colspan='4'>No existen resultados para sus parametros de busqueda!!!</td>");
		}
		
		out.print("</tr></table></table>");
	
	}
	
	
	/////////////////////////
	
	if(va.equals("21")){ ////INgresa el detalle a la base de datos
		//System.out.println("LLegamos a modificar");
		
		
		
	//	listo primero recorrrer para actualizar todo y ademas periodo x periodo hacia abajo
		
		
		/////////Realizar la copia de cont_documento a cont_movdocumentos/////////
		rs = md.ConsultarDocumento(docu);
		try {
			if(rs.next()){
				md.CrearMoviemientoDocumento(docu,rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14),rs.getString(15));
			}
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		
		/////////Realizar la copia de cont_detalle_documento a cont_movdetalle_documentos
		String codMovDocumento="";
		rs = md.ConsultarCodMovDocumento(docu);///Consulto el cod de la tabla cont_movdocumentos
		try {
			if(rs.next()){
				codMovDocumento=rs.getString(1);
			}
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		
		
		
		rs = md.ConsultarDetalleDocumento(docu);
		try {
			while(rs.next()){
				md.CrearMoviemientoDetalleDocumento(codMovDocumento,rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13));			
				//md.(docu,rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10));
			}
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		
		///////////////////////////////////////////////////////////////////
			
		String V[] = new String[3600];
		String M[][] = new String[200][18];

		int i1=0;
		StringTokenizer ele;  
		ele = new StringTokenizer(Ndatos,"|"); 
		
		while(ele.hasMoreTokens()){ 
		 V[i1] = ele.nextToken();   
		 //System.out.println("15V["+i1+"]:"+V[i1]);
		 i1++;
		}

		int in=0;
		for(int z=0; z<=l; z++){
		  for(int zz=0; zz<18; zz++){
			//  System.out.println("in:"+in+" M["+z+"]["+zz+"]="+V[in]);
			M[z][zz]=V[in];
			in++;
		  }
		}
			

			


	
		
		
		/***  INGRESAR A LA DB LA MODIFICACION DEL  DOCUMENTOS  Y  DETALLE DE DOCUMENTOS  ****/
	
		md.ActualizarDocumentoDebitoCredito(docu, vdebito, vcredito);
		
		int ln=l+1;
		for(int i=0;i<=l;i++){///este es el for para crear cada linea del detalle en la db
		//////Actualizar el detalle, Eliminarlo o crearlo
			
		/***cdh M[i][17]
			ideen M[i][16]     /// 0 actualizo     2 elimino     4 inserto con el cod del documento
			cta M[i][0]
			ctah  M[i][1]
			suc   value='"+M[i][2]+"'>"+M[i][3]
			ncco  value='"+M[i][4]+"'>"+M[i][5] 
			nsco   value='"+M[i][6]+"'>"+M[i][7] 
			ter M[i][8]
			terh M[i][9]
			des M[i][10]
			bas M[i][11]
			ref M[i][12]
			deb M[i][13]
			cre M[i][14]
			dife M[i][15]**/
			
			
			
	/***  Consultar y crear el Acumulado sucursal centro de costo *****////////////
			
			rs = md.CentrosySubcentrosdeCosto(M[i][6]);//Consulto centros de costo y subcentro de costo
			String vcco="";
			String vscc="";
			try {
				while(rs.next()){
					vcco=rs.getString(1);
					vscc=rs.getString(2);
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			///Verificar si la relacion a�o,cta,succentro y centrosubcentro existe
			String codasc=""; 
			rs = md.Acumulado_Sucursal_Centrocco(M[i][4],M[i][6],M[i][1],ac);
			int sw=0;
			try {
				if(rs.next()){
					sw=1;
					codasc=rs.getString(1);
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}	
			System.out.println("");
			System.out.println("DOCDH: "+M[i][17]+"RELACION"+M[i][4]+"-"+M[i][6]+"-"+M[i][1]+"-"+ac);
			
			
			///Verificar si la relacion tercero,cta,a�o,succntro y centrosubcentro existe
			String codat=""; 
			rs = md.Acumulado_Terceros(M[i][9],M[i][1],ac,M[i][4],M[i][6]);
			int swt=0;
			try {
				if(rs.next()){
					swt=1;
					codat=rs.getString(1);
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
/*****/	if(M[i][16].equals("0")){//1.Actualizo detalle existente
			System.out.println(M[i][16]+"Si es 1 la relacion existe y hace algo sino pailas"+sw);
			
//////////////////////////////////////////////////PRIMERO DESHACEMOS EL ACUMULADO RESTANDO LOS VALORES ORIGINALES/////////////////////////////////////
			String debo="";
			String creo="";
			String ctao="";
			String scoo="";
			String csoo="";
			String tero="";
			String annoo="";
			rs1 = md.ConsultarDetalleDocumentoDebitoyCredito(M[i][17]);//Obtengo el debito y credito original del detalle a actualizar 
			try {
				if(rs1.next()){
					debo=rs1.getString(1);//debito
					creo=rs1.getString(2);//credito
					ctao=rs1.getString(3);//me traigo el codigo de la cta original
					scoo=rs1.getString(4);//sc
					csoo=rs1.getString(5);//cs
					tero=rs1.getString(6);//ter
					annoo=rs1.getString(7);//a�o
					
					//System.out.println("VALORES ORIGINALES"+debo+"-"+creo);
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}	
			
			
			////////////////////////////PRIMERO RESTAMOS LOS VALORES ORIGINALES///////////////////////////////////////
			String codaso=""; 
			rs = md.Acumulado_Sucursal_Centrocco(scoo,csoo,ctao,annoo);
			try {
				if(rs.next()){
					codaso=rs.getString(1);
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}	
			String natuo="";
			rs1 = md.ConsultarNombreCta(ctao);//Obtengo nombre de la cuenta para sacar su nauraleza
			try {
				if(rs1.next()){
					natuo=rs1.getString(1);
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			//System.out.println("NATUOOO: "+natuo);
			String sac="0";
			String sdc="0";
			String scc="0";
			String snc="0";
			
			rs = md.Detalle_Acumulado_Sucursal_CentroccoPeriodo(codaso,pc);//Consultamos todos los datos del periodo a actualizar
			try {
				if(rs.next()){
					sac=rs.getString(1);
					sdc=rs.getString(2);
					scc=rs.getString(3);
					snc=rs.getString(4);
					//System.out.println("VALORES DEL ACUMULADO: "+sac+"-"+sdc+"-"+scc+"-"+snc);
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}	
			sdc=String.valueOf(Long.parseLong(sdc)-Long.parseLong(debo));
			scc=String.valueOf(Long.parseLong(scc)-Long.parseLong(creo));
		
			String natucta=natuo.substring(0, 1);//selecciona el inicio del nombre de la cuenta para saber la naturaleza
			if(natucta.equals("1")||natucta.equals("5")||natucta.equals("6")||natucta.equals("7")||natucta.equals("8")){
				long sncint=(Long.parseLong(sac)+Long.parseLong(sdc)-Long.parseLong(scc));
			//	System.out.println("ESTE ES EL RESUKTADO DE LA OPERACION DEBITO sncint"+sncint);
				snc=String.valueOf(sncint);             
			}
			if(natucta.equals("2")||natucta.equals("3")||natucta.equals("4")||natucta.equals("9")){
				long sncint=(Long.parseLong(sac)-Long.parseLong(sdc)+Long.parseLong(scc));
			//	System.out.println("ESTE ES EL RESUKTADO DE LA OPERACION CREDITO sncint"+sncint);
				snc=String.valueOf(sncint);   
			}
			
			md.ActualizarDetalle_Acumulado_Sucursal_Centrocco(sac,sdc,scc,snc,codaso,pc);//Actualiza el detalle con los valores del debito y credito y el saldo nuevo

			//System.out.println("Aqui se restan los valores originales sa"+sac+" sd"+sdc+" sc"+scc+" sn"+snc);
			String pp2="";
			int inicio=Integer.parseInt(pc)+1;
			String sac2="0";
			String sdc2="0";
			String scc2="0";
			String snc2="0";
			
			for (int x=inicio; x<14; x++){// for que me ayuda a actualizar los registros de todos los periodos hacia abajo
				if(x<10){pp2="0"+x;}else{pp2=String.valueOf(x);}
				rs = md.Detalle_Acumulado_Sucursal_CentroccoPeriodo(codaso,pp2);//Consultamos el periodo 13 del a�o anterior y nos traemos el saldo nuevo
				try {
					if(rs.next()){
						//sac2=rs.getString(1);
						sdc2=rs.getString(2);
						scc2=rs.getString(3);
						//snc2=rs.getString(4);
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}	
				
				String natucta2=M[i][0].substring(0, 1);//selecciona el inicio del nombre de la cuenta para saber la naturaleza
				if(natucta2.equals("1")||natucta2.equals("5")||natucta2.equals("6")||natucta2.equals("7")||natucta2.equals("8")){
					snc2=String.valueOf(Long.parseLong(snc)+Long.parseLong(sdc2)-Long.parseLong(scc2));             
				}
				if(natucta2.equals("2")||natucta2.equals("3")||natucta2.equals("4")||natucta2.equals("9")){
					snc2=String.valueOf(Long.parseLong(snc)-Long.parseLong(sdc2)+Long.parseLong(scc2));   
				}
				
				md.ActualizarDetalle_Acumulado_Sucursal_Centrocco(snc,sdc2,scc2,snc2,codaso,pp2);//Actualiza el detalle hacia abajo con el saldo anterior
			snc=snc2;
			}//fin for <14
			
			
			
			
			if(sw==1){//si la relacion si existe 
							
				//////////////////////////////////////////////////SEGUNDO SUMAMOS LOS NUEVOS VALORES AL ACUMULADO//////////////////////////////////////////
				String sacn="0";
				String sdcn="0";
				String sccn="0";
				String sncn="0";
				
				rs = md.Detalle_Acumulado_Sucursal_CentroccoPeriodo(codasc,pc);//Consultamos todos los datos del periodo a actualizar
				try {
					if(rs.next()){
						sacn=rs.getString(1);
						sdcn=rs.getString(2);
						sccn=rs.getString(3);
						sncn=rs.getString(4);
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}	
				sdcn=String.valueOf(Long.parseLong(sdcn)+Long.parseLong(M[i][13]));
				sccn=String.valueOf(Long.parseLong(sccn)+Long.parseLong(M[i][14]));
			
				
				String natuctan=M[i][0].substring(0, 1);//selecciona el inicio del nombre de la cuenta para saber la naturaleza
				if(natuctan.equals("1")||natuctan.equals("5")||natuctan.equals("6")||natuctan.equals("7")||natuctan.equals("8")){
					long sncint=(Long.parseLong(sacn)+Long.parseLong(sdcn)-Long.parseLong(sccn));
				//	System.out.println("ESTE ES EL RESUKTADO DE LA OPERACION DEBITO sncint"+sncint);
					sncn=String.valueOf(sncint);             
				}
				if(natuctan.equals("2")||natuctan.equals("3")||natuctan.equals("4")||natuctan.equals("9")){
					long sncint=(Long.parseLong(sacn)-Long.parseLong(sdcn)+Long.parseLong(sccn));
				//	System.out.println("ESTE ES EL RESUKTADO DE LA OPERACION CREDITO sncint"+sncint);
					sncn=String.valueOf(sncint);   
				}
				
				md.ActualizarDetalle_Acumulado_Sucursal_Centrocco(sacn,sdcn,sccn,sncn,codasc,pc);//Actualiza el detalle con los valores del debito y credito y el saldo nuevo
				//System.out.println("Aqui se SUMAN los valores nuevos sa"+sacn+" sd"+sdcn+" sc"+sccn+" sn"+sncn);
				String pp2n="";
				int inicion=Integer.parseInt(pc)+1;
				String sac2n="0";
				String sdc2n="0";
				String scc2n="0";
				String snc2n="0";
				
				for (int x=inicion; x<14; x++){// for que me ayuda a actualizar los registros de todos los periodos hacia abajo
					if(x<10){pp2n="0"+x;}else{pp2n=String.valueOf(x);}
					rs = md.Detalle_Acumulado_Sucursal_CentroccoPeriodo(codasc,pp2n);//Consultamos el periodo 13 del a�o anterior y nos traemos el saldo nuevo
					try {
						if(rs.next()){
							//sac2=rs.getString(1);
							sdc2n=rs.getString(2);
							scc2n=rs.getString(3);
							//snc2=rs.getString(4);
						}
						rs.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}	
					
					String natucta2=M[i][0].substring(0, 1);//selecciona el inicio del nombre de la cuenta para saber la naturaleza
					if(natucta2.equals("1")||natucta2.equals("5")||natucta2.equals("6")||natucta2.equals("7")||natucta2.equals("8")){
						snc2n=String.valueOf(Long.parseLong(sncn)+Long.parseLong(sdc2n)-Long.parseLong(scc2n));             
					}
					if(natucta2.equals("2")||natucta2.equals("3")||natucta2.equals("4")||natucta2.equals("9")){
						snc2n=String.valueOf(Long.parseLong(sncn)-Long.parseLong(sdc2n)+Long.parseLong(scc2n));   
					}
					
					md.ActualizarDetalle_Acumulado_Sucursal_Centrocco(sncn,sdc2n,scc2n,snc2n,codasc,pp2n);//Actualiza el detalle hacia abajo con el saldo anterior
				sncn=snc2n;
				}//fin for <14
				
			}//FIN si la relacion si existe
			else{//Si la relacion no existe es nueva
				
				md.CrearAcumulado_Sucursal_Centrocco(M[i][4],M[i][6],M[i][1],ac);//Se crea la nueva relacion y se procede a crear los periodos
				rs = md.Acumulado_Sucursal_Centrocco(M[i][4],M[i][6],M[i][1],ac);//Nos traemos el codigo de la relacion q acabamos de crear
				try {
					if(rs.next()){
						codasc=rs.getString(1);
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				
				int acaa=(Integer.parseInt(ac)-1);
				String acaas=String.valueOf(acaa);
				String snp13="0";
				rs = md.Acumulado_Sucursal_Centrocco(M[i][4],M[i][6],M[i][1],acaas);//Consltamos si existe esa relacion en el a�o anterior para buscar en el detalle el saldo final del periodo 13
				int sw2=0;
				try {
					if(rs.next()){
						sw2=1;
						////////////////////
						rs = md.Detalle_Acumulado_Sucursal_CentroccoP13(rs.getString(1));//Consultamos el periodo 13 del a�o anterior y nos traemos el saldo nuevo
						try {
							if(rs.next()){
								snp13=rs.getString(1);
							}
							rs.getStatement().getConnection().close();
						} catch (SQLException e) {
							out.print("Error "+e);
							e.printStackTrace();
						}	
						///////////////////////
						
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				
				String pp="0";
				String dp="0";
				String cp="0";
				String snp01="";
				for(int j=1; j<=13; j++){
					if(j<10){pp="0"+j;}else{pp=String.valueOf(j);}
					if(pc.equals(pp)){dp=M[i][13]; cp=M[i][14];}//si periodo=j hay q cambiar el valor del debito y credito ysegun la naturaleza ejecutar la formula)
					else{dp="0"; cp="0";}
					
					natucta=M[i][0].substring(0, 1);//selecciona el inicio del nombre de la cuenta para saber la naturaleza
					if(natucta.equals("1")||natucta.equals("5")||natucta.equals("6")||natucta.equals("7")||natucta.equals("8")){
						long snp01int=(Long.parseLong(snp13)+Long.parseLong(dp)-Long.parseLong(cp));
						//System.out.println("ESTE ES EL RESUKTADO DE LA OPERACION DEBITO snp01int"+snp01int);
						snp01=String.valueOf(snp01int);             
					}
					if(natucta.equals("2")||natucta.equals("3")||natucta.equals("4")||natucta.equals("9")){
						long snp01int=(Long.parseLong(snp13)-Long.parseLong(dp)+Long.parseLong(cp));
						//System.out.println("ESTE ES EL RESUKTADO DE LA OPERACION CREDITO snp01int"+snp01int);
						snp01=String.valueOf(snp01int);   
					}
					
					md.CrearDetalle_Acumulado_Sucursal_Centrocco(codasc,pp,snp13,dp,cp,snp01);//crea el detalle x periodo del detalle_acumulado_sucursal_centrocco
					snp13=snp01;//Al saldo anterior le asigno el saldo nuevo para el proximo periodo
			 
				}//fin for j
	
			}///fin si la relacion no existe es nueva
			
					
			
			/*****ACUMULADO TERCEROS****///
			
			 debo="";
			 creo="";
			 ctao="";
			 scoo="";
			 csoo="";
			 tero="";
			 annoo="";
			rs1 = md.ConsultarDetalleDocumentoDebitoyCredito(M[i][17]);//Obtengo el debito y credito original del detalle a eliminar
			try {
				if(rs1.next()){
					debo=rs1.getString(1);//debito
					creo=rs1.getString(2);//credito
					ctao=rs1.getString(3);//me traigo el codigo de la cta original
					scoo=rs1.getString(4);//sc
					csoo=rs1.getString(5);//cs
					tero=rs1.getString(6);//ter
					System.out.println("tero: "+tero);
					annoo=rs1.getString(7);//a�o
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}	
			
			String codato=""; 
			
			rs = md.Acumulado_Terceros(tero,ctao,annoo,scoo,csoo);
			try {
				if(rs.next()){
					codato=rs.getString(1);
					System.out.println("codato codigo del acumulado tercero: "+codato);
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}	
			
			 natuo="";
			
			rs1 = md.ConsultarNombreCta(ctao);//Obtengo nombre de la cuenta para sacar su nauraleza
			try {
				if(rs1.next()){
					natuo=rs1.getString(1);
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			System.out.println("NATUOOO: "+natuo);
			
							
			 sac="0";
			 sdc="0";
			 scc="0";
			 snc="0";
			
			rs = md.Detalle_Acumulado_Tercero(codato,pc);//Consultamos todos los datos del periodo a actualizar
			try {
				if(rs.next()){
					sac=rs.getString(1);
					sdc=rs.getString(2);
					scc=rs.getString(3);
					snc=rs.getString(4);
					System.out.println(codato+"-->codato Valores del acumulado tercero sa al que se le van a restar"+sac+" sd"+sdc+" sc"+scc+" sn"+snc);
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}	
			sdc=String.valueOf(Long.parseLong(sdc)-Long.parseLong(debo));
			scc=String.valueOf(Long.parseLong(scc)-Long.parseLong(creo));
		
			 natucta=M[i][0].substring(0, 1);//selecciona el inicio del nombre de la cuenta para saber la naturaleza
			if(natucta.equals("1")||natucta.equals("5")||natucta.equals("6")||natucta.equals("7")||natucta.equals("8")){
				long sncint=(Long.parseLong(sac)+Long.parseLong(sdc)-Long.parseLong(scc));
				//System.out.println("ESTE ES EL RESUKTADO DE LA OPERACION DEBITO sncint"+sncint);
				snc=String.valueOf(sncint);             
			}
			if(natucta.equals("2")||natucta.equals("3")||natucta.equals("4")||natucta.equals("9")){
				long sncint=(Long.parseLong(sac)-Long.parseLong(sdc)+Long.parseLong(scc));
				//System.out.println("ESTE ES EL RESUKTADO DE LA OPERACION CREDITO sncint"+sncint);
				snc=String.valueOf(sncint);   
			}
			
			md.ActualizarDetalle_Acumulado_Tercero(sac,sdc,scc,snc,codato,pc);//Actualiza el detalle con los valores del debito y credito y el saldo nuevo
			System.out.println("Se le restan el debito y credito original y quedan sa"+sac+" sd"+sdc+" sc"+scc+" sn"+snc);
			 pp2="";
			 inicio=Integer.parseInt(pc)+1;
			 sac2="0";
			 sdc2="0";
			 scc2="0";
			 snc2="0";
			
			for (int x=inicio; x<14; x++){// for que me ayuda a actualizar los registros de todos los periodos hacia abajo
				if(x<10){pp2="0"+x;}else{pp2=String.valueOf(x);}
				rs = md.Detalle_Acumulado_Tercero(codato,pp2);//Consultamos el periodo 13 del a�o anterior y nos traemos el saldo nuevo
				try {
					if(rs.next()){
						//sac2=rs.getString(1);
						sdc2=rs.getString(2);
						scc2=rs.getString(3);
						//snc2=rs.getString(4);
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}	
				
				String natucta2=M[i][0].substring(0, 1);//selecciona el inicio del nombre de la cuenta para saber la naturaleza
				if(natucta2.equals("1")||natucta2.equals("5")||natucta2.equals("6")||natucta2.equals("7")||natucta2.equals("8")){
					snc2=String.valueOf(Long.parseLong(snc)+Long.parseLong(sdc2)-Long.parseLong(scc2));             
				}
				if(natucta2.equals("2")||natucta2.equals("3")||natucta2.equals("4")||natucta2.equals("9")){
					snc2=String.valueOf(Long.parseLong(snc)-Long.parseLong(sdc2)+Long.parseLong(scc2));   
				}
				
				md.ActualizarDetalle_Acumulado_Tercero(snc,sdc2,scc2,snc2,codato,pp2);//Actualiza el detalle hacia abajo con el saldo anterior
			snc=snc2;
			}//fin for <14
			
			
			if(swt==1){//si la relacion si existe solo tocaria actualizar el tercero
				//System.out.println("ENTRAMOS A TERCEROS LA RELACION SI EXIXTE");
				//////////////////////////////////////////////////PRIMERO DESHACEMOS EL ACUMULADO RESTANDO LOS VALORES ORIGINALES/////////////////////////////////////
			
				
					
				
				//////////////////////////////////////////////////SEGUNDO SUMAMOS LOS NUEVOS VALORES AL ACUMULADO TERCERO//////////////////////////////////////////
				
				String sacn="0";
				String sdcn="0";
				String sccn="0";
				String sncn="0";
				
				rs = md.Detalle_Acumulado_Tercero(codat,pc);//Consultamos todos los datos del periodo a actualizar
				try {
					if(rs.next()){
						sacn=rs.getString(1);
						sdcn=rs.getString(2);
						sccn=rs.getString(3);
						sncn=rs.getString(4);
						System.out.println(codat+"-->codat  Valores del acumulado tercero al que se le van a sumar sa"+sacn+" sd"+sdcn+" sc"+sccn+" sn"+sncn);
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}	
				sdcn=String.valueOf(Long.parseLong(sdcn)+Long.parseLong(M[i][13]));
				sccn=String.valueOf(Long.parseLong(sccn)+Long.parseLong(M[i][14]));
				
				String natucta3=M[i][0].substring(0, 1);//selecciona el inicio del nombre de la cuenta para saber la naturaleza
				if(natucta3.equals("1")||natucta3.equals("5")||natucta3.equals("6")||natucta3.equals("7")||natucta3.equals("8")){
					long sncint=(Long.parseLong(sacn)+Long.parseLong(sdcn)-Long.parseLong(sccn));
					//System.out.println("ESTE ES EL RESUKTADO DE LA OPERACION DEBITO sncint"+sncint);
					sncn=String.valueOf(sncint);             
				}
				if(natucta3.equals("2")||natucta3.equals("3")||natucta3.equals("4")||natucta3.equals("9")){
					long sncint=(Long.parseLong(sacn)-Long.parseLong(sdcn)+Long.parseLong(sccn));
					//System.out.println("ESTE ES EL RESUKTADO DE LA OPERACION CREDITO sncint"+sncint);
					sncn=String.valueOf(sncint);   
				}
				
				md.ActualizarDetalle_Acumulado_Tercero(sacn,sdcn,sccn,sncn,codat,pc);//Actualiza el detalle con los valores del debito y credito y el saldo nuevo
				System.out.println("Se le suman el debito y credito de la matriz sa"+sacn+" sd"+sdcn+" sc"+sccn+" sn"+sncn);
				String pp2n="";
				int inicion=Integer.parseInt(pc)+1;
				String sac2n="0";
				String sdc2n="0";
				String scc2n="0";
				String snc2n="0";
				
				for (int x=inicion; x<14; x++){// for que me ayuda a actualizar los registros de todos los periodos hacia abajo
					if(x<10){pp2n="0"+x;}else{pp2n=String.valueOf(x);}
					rs = md.Detalle_Acumulado_Tercero(codat,pp2n);//Consultamos el periodo 13 del a�o anterior y nos traemos el saldo nuevo
					try {
						if(rs.next()){
							//sac2=rs.getString(1);
							sdc2n=rs.getString(2);
							scc2n=rs.getString(3);
							//snc2=rs.getString(4);
						}
						rs.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}	
					
					String natucta2=M[i][0].substring(0, 1);//selecciona el inicio del nombre de la cuenta para saber la naturaleza
					if(natucta2.equals("1")||natucta2.equals("5")||natucta2.equals("6")||natucta2.equals("7")||natucta2.equals("8")){
						snc2n=String.valueOf(Long.parseLong(sncn)+Long.parseLong(sdc2n)-Long.parseLong(scc2n));             
					}
					if(natucta2.equals("2")||natucta2.equals("3")||natucta2.equals("4")||natucta2.equals("9")){
						snc2n=String.valueOf(Long.parseLong(sncn)-Long.parseLong(sdc2n)+Long.parseLong(scc2n));   
					}
					
					md.ActualizarDetalle_Acumulado_Tercero(sncn,sdc2n,scc2n,snc2n,codat,pp2n);//Actualiza el detalle hacia abajo con el saldo anterior
				snc=snc2n;
				}//fin for <14

			}//fin del else la relacion de acumulados terceros si existe
			else{//Si la relacion no existe es nueva
				
				
				md.CrearAcumulado_Terceros(M[i][9],M[i][1],ac,M[i][4],M[i][6]);//Se crea la nueva relacion y se procede a crear los periodos
				rs = md.Acumulado_Terceros(M[i][9],M[i][1],ac,M[i][4],M[i][6]);//Nos traemos el codigo de la relacion q acabamos de crear
				try {
					if(rs.next()){
						codat=rs.getString(1);
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
			
				int acaa=(Integer.parseInt(ac)-1);
				String acaas=String.valueOf(acaa);
				String snp13="0";
				rs = md.Acumulado_Terceros(M[i][9],M[i][1],acaas,M[i][4],M[i][6]);//Consltamos si existe esa relacion en el a�o anterior para buscar en el detalle el saldo final del periodo 13
				int sw2=0;
				try {
					if(rs.next()){
						sw2=1;
						////////////////////
						rs1 = md.Detalle_Acumulado_TerceroP13(rs.getString(1));//Consultamos el periodo 13 del a�o anterior y nos traemos el saldo nuevo
						try {
							if(rs1.next()){
								snp13=rs1.getString(1);
							}
							rs1.getStatement().getConnection().close();
						} catch (SQLException e) {
							out.print("Error "+e);
							e.printStackTrace();
						}	
						///////////////////////
						
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				String pp="0";
				String dp="0";
				String cp="0";
				String snp01="";
				for(int j=1; j<=13; j++){
					if(j<10){pp="0"+j;}else{pp=String.valueOf(j);}
					if(pc.equals(pp)){dp=M[i][13]; cp=M[i][14];}//si periodo=j hay q cambiar el valor del debito y credito ysegun la naturaleza ejecutar la formula)
					else{dp="0"; cp="0";}
					
					natucta=M[i][0].substring(0, 1);//selecciona el inicio del nombre de la cuenta para saber la naturaleza
					if(natucta.equals("1")||natucta.equals("5")||natucta.equals("6")||natucta.equals("7")||natucta.equals("8")){
						long snp01int=(Long.parseLong(snp13)+Long.parseLong(dp)-Long.parseLong(cp));
						System.out.println("ESTE ES EL RESUKTADO DE LA OPERACION DEBITO snp01int"+snp01int);
						snp01=String.valueOf(snp01int);             
					}
					if(natucta.equals("2")||natucta.equals("3")||natucta.equals("4")||natucta.equals("9")){
						long snp01int=(Long.parseLong(snp13)-Long.parseLong(dp)+Long.parseLong(cp));
						System.out.println("ESTE ES EL RESUKTADO DE LA OPERACION CREDITO snp01int"+snp01int);
						snp01=String.valueOf(snp01int);   
					}
					
					md.CrearDetalle_Acumulado_Tercero(codat,pp,snp13,dp,cp,snp01);//crea el detalle x periodo del detalle_acumulado_tercero
					snp13=snp01;//Al saldo anterior le asigno el saldo nuevo para el proximo periodo
			 
				}//fin for j

			}///fin si la relacion no existe es nueva


			md.ActualizarDetalle_Documento(M[i][1],M[i][4],M[i][6],M[i][9],M[i][10],M[i][11],M[i][12],M[i][13],M[i][14],"0","CNT",M[i][17]);
					
		}//1.FIN Actualizo detalle existente
		
		
		



		
/*****/	if(M[i][16].equals("2")){//2.Elimino detalle existente
			if(!M[i][17].equals("0")){//Consulto si estaba creado antes porque no puedo eliminar uno propio q intentaba crear
				
				if(sw==1){//si la relacion si existe
				
					String debo="";
					String creo="";
					rs1 = md.ConsultarDetalleDocumentoDebitoyCredito(M[i][17]);//Obtengo el debito y credito original del detalle a eliminar
					try {
						if(rs1.next()){
							debo=rs1.getString(1);
							creo=rs1.getString(2);
							
						}
						rs1.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}	
					
					
					String sac="0";
					String sdc="0";
					String scc="0";
					String snc="0";
					
					rs = md.Detalle_Acumulado_Sucursal_CentroccoPeriodo(codasc,pc);//Consultamos todos los datos del periodo a actualizar
					try {
						if(rs.next()){
							sac=rs.getString(1);
							sdc=rs.getString(2);
							scc=rs.getString(3);
							snc=rs.getString(4);
						}
						rs.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}	
					sdc=String.valueOf(Long.parseLong(sdc)-Long.parseLong(debo));
					scc=String.valueOf(Long.parseLong(scc)-Long.parseLong(creo));
				
					String natucta=M[i][0].substring(0, 1);//selecciona el inicio del nombre de la cuenta para saber la naturaleza
					if(natucta.equals("1")||natucta.equals("5")||natucta.equals("6")||natucta.equals("7")||natucta.equals("8")){
						long sncint=(Long.parseLong(sac)+Long.parseLong(sdc)-Long.parseLong(scc));
					//	System.out.println("ESTE ES EL RESUKTADO DE LA OPERACION DEBITO sncint"+sncint);
						snc=String.valueOf(sncint);             
					}
					if(natucta.equals("2")||natucta.equals("3")||natucta.equals("4")||natucta.equals("9")){
						long sncint=(Long.parseLong(sac)-Long.parseLong(sdc)+Long.parseLong(scc));
					//	System.out.println("ESTE ES EL RESUKTADO DE LA OPERACION CREDITO sncint"+sncint);
						snc=String.valueOf(sncint);   
					}
					
					md.ActualizarDetalle_Acumulado_Sucursal_Centrocco(sac,sdc,scc,snc,codasc,pc);//Actualiza el detalle con los valores del debito y credito y el saldo nuevo
					
					String pp2="";
					int inicio=Integer.parseInt(pc)+1;
					String sac2="0";
					String sdc2="0";
					String scc2="0";
					String snc2="0";
					
					for (int x=inicio; x<14; x++){// for que me ayuda a actualizar los registros de todos los periodos hacia abajo
						if(x<10){pp2="0"+x;}else{pp2=String.valueOf(x);}
						rs = md.Detalle_Acumulado_Sucursal_CentroccoPeriodo(codasc,pp2);//Consultamos el periodo 13 del a�o anterior y nos traemos el saldo nuevo
						try {
							if(rs.next()){
								//sac2=rs.getString(1);
								sdc2=rs.getString(2);
								scc2=rs.getString(3);
								//snc2=rs.getString(4);
							}
							rs.getStatement().getConnection().close();
						} catch (SQLException e) {
							out.print("Error "+e);
							e.printStackTrace();
						}	
						
						String natucta2=M[i][0].substring(0, 1);//selecciona el inicio del nombre de la cuenta para saber la naturaleza
						if(natucta2.equals("1")||natucta2.equals("5")||natucta2.equals("6")||natucta2.equals("7")||natucta2.equals("8")){
							snc2=String.valueOf(Long.parseLong(snc)+Long.parseLong(sdc2)-Long.parseLong(scc2));             
						}
						if(natucta2.equals("2")||natucta2.equals("3")||natucta2.equals("4")||natucta2.equals("9")){
							snc2=String.valueOf(Long.parseLong(snc)-Long.parseLong(sdc2)+Long.parseLong(scc2));   
						}
						
						md.ActualizarDetalle_Acumulado_Sucursal_Centrocco(snc,sdc2,scc2,snc2,codasc,pp2);//Actualiza el detalle hacia abajo con el saldo anterior
					snc=snc2;
					}//fin for <14
				
				}//FIN si la relacion si existe
				
				/******  ACUMULADO TERCERO ********/
				
				
				
				if(swt==1){//si la relacion si existe solo tocaria actualizar el tercero
					
					
					//////////////////////////////////////////////////PRIMERO DESHACEMOS EL ACUMULADO RESTANDO LOS VALORES ORIGINALES/////////////////////////////////////
					String debo="";
					String creo="";
					rs1 = md.ConsultarDetalleDocumentoDebitoyCredito(M[i][17]);//Obtengo el debito y credito original del detalle a eliminar
					try {
						if(rs1.next()){
							debo=rs1.getString(1);
							creo=rs1.getString(2);
							
						}
						rs1.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}	
					
									
					String sac="0";
					String sdc="0";
					String scc="0";
					String snc="0";
				
					rs = md.Detalle_Acumulado_Tercero(codat,pc);//Consultamos todos los datos del periodo a actualizar
					try {
						if(rs.next()){
							sac=rs.getString(1);
							sdc=rs.getString(2);
							scc=rs.getString(3);
							snc=rs.getString(4);
						}
						rs.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}	
					sdc=String.valueOf(Long.parseLong(sdc)-Long.parseLong(debo));
					scc=String.valueOf(Long.parseLong(scc)-Long.parseLong(creo));
				
					String natucta=M[i][0].substring(0, 1);//selecciona el inicio del nombre de la cuenta para saber la naturaleza
					if(natucta.equals("1")||natucta.equals("5")||natucta.equals("6")||natucta.equals("7")||natucta.equals("8")){
						long sncint=(Long.parseLong(sac)+Long.parseLong(sdc)-Long.parseLong(scc));
						//System.out.println("ESTE ES EL RESUKTADO DE LA OPERACION DEBITO sncint"+sncint);
						snc=String.valueOf(sncint);             
					}
					if(natucta.equals("2")||natucta.equals("3")||natucta.equals("4")||natucta.equals("9")){
						long sncint=(Long.parseLong(sac)-Long.parseLong(sdc)+Long.parseLong(scc));
						//System.out.println("ESTE ES EL RESUKTADO DE LA OPERACION CREDITO sncint"+sncint);
						snc=String.valueOf(sncint);   
					}
					
					md.ActualizarDetalle_Acumulado_Tercero(sac,sdc,scc,snc,codat,pc);//Actualiza el detalle con los valores del debito y credito y el saldo nuevo
				
					String pp2="";
					int inicio=Integer.parseInt(pc)+1;
					String sac2="0";
					String sdc2="0";
					String scc2="0";
					String snc2="0";
					
					for (int x=inicio; x<14; x++){// for que me ayuda a actualizar los registros de todos los periodos hacia abajo
						if(x<10){pp2="0"+x;}else{pp2=String.valueOf(x);}
						rs = md.Detalle_Acumulado_Tercero(codat,pp2);//Consultamos el periodo 13 del a�o anterior y nos traemos el saldo nuevo
						try {
							if(rs.next()){
								//sac2=rs.getString(1);
								sdc2=rs.getString(2);
								scc2=rs.getString(3);
								//snc2=rs.getString(4);
							}
							rs.getStatement().getConnection().close();
						} catch (SQLException e) {
							out.print("Error "+e);
							e.printStackTrace();
						}	
						
						String natucta2=M[i][0].substring(0, 1);//selecciona el inicio del nombre de la cuenta para saber la naturaleza
						if(natucta2.equals("1")||natucta2.equals("5")||natucta2.equals("6")||natucta2.equals("7")||natucta2.equals("8")){
							snc2=String.valueOf(Long.parseLong(snc)+Long.parseLong(sdc2)-Long.parseLong(scc2));             
						}
						if(natucta2.equals("2")||natucta2.equals("3")||natucta2.equals("4")||natucta2.equals("9")){
							snc2=String.valueOf(Long.parseLong(snc)-Long.parseLong(sdc2)+Long.parseLong(scc2));   
						}
						
						md.ActualizarDetalle_Acumulado_Tercero(snc,sdc2,scc2,snc2,codat,pp2);//Actualiza el detalle hacia abajo con el saldo anterior
					snc=snc2;
					}//fin for <14
								
				}//FIN si la relacion tercero si existe
				
			}//FIN Consulto si estaba creado antes porque no puedo eliminar uno propio q intentaba crear
			
			md.EliminarDetalle_Documento(M[i][17]);//Elimino el Detalle del Documento como tal
			
		}//2.FIN Elimino detalle existente
		
		



				
/****/	if(M[i][16].equals("4")){//3.Creo detalle con el cod del documento
		md.CrearDetalleDocumento(docu,M[i][1],M[i][4],M[i][6],M[i][9],M[i][10],M[i][11],M[i][12],M[i][13],M[i][14],"0","CNT");
		
			if(sw==0){//Si la relacion en Acumulado_sucursal_centrodecosto no existe
			md.CrearAcumulado_Sucursal_Centrocco(M[i][4],M[i][6],M[i][1],ac);//Se crea la nueva relacion y se procede a crear los periodos
			rs = md.Acumulado_Sucursal_Centrocco(M[i][4],M[i][6],M[i][1],ac);//Nos traemos el codigo de la relacion q acabamos de crear
			try {
				if(rs.next()){
					codasc=rs.getString(1);
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			int acaa=(Integer.parseInt(ac)-1);
			String acaas=String.valueOf(acaa);
			String snp13="0";
			rs = md.Acumulado_Sucursal_Centrocco(M[i][4],M[i][6],M[i][1],acaas);//Consltamos si existe esa relacion en el a�o anterior para buscar en el detalle el saldo final del periodo 13
			int sw2=0;
			try {
				if(rs.next()){
					sw2=1;
					////////////////////
					rs = md.Detalle_Acumulado_Sucursal_CentroccoP13(rs.getString(1));//Consultamos el periodo 13 del a�o anterior y nos traemos el saldo nuevo
					try {
						if(rs.next()){
							snp13=rs.getString(1);
						}
						rs.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}	
					///////////////////////
					
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			String pp="0";
			String dp="0";
			String cp="0";
			String snp01="";
			for(int j=1; j<=13; j++){
				if(j<10){pp="0"+j;}else{pp=String.valueOf(j);}
				if(pc.equals(pp)){dp=M[i][13]; cp=M[i][14];}//si periodo=j hay q cambiar el valor del debito y credito ysegun la naturaleza ejecutar la formula)
				else{dp="0"; cp="0";}
				
				String natucta=M[i][0].substring(0, 1);//selecciona el inicio del nombre de la cuenta para saber la naturaleza
				if(natucta.equals("1")||natucta.equals("5")||natucta.equals("6")||natucta.equals("7")||natucta.equals("8")){
					long snp01int=(Long.parseLong(snp13)+Long.parseLong(dp)-Long.parseLong(cp));
					//System.out.println("ESTE ES EL RESUKTADO DE LA OPERACION DEBITO snp01int"+snp01int);
					snp01=String.valueOf(snp01int);             
				}
				if(natucta.equals("2")||natucta.equals("3")||natucta.equals("4")||natucta.equals("9")){
					long snp01int=(Long.parseLong(snp13)-Long.parseLong(dp)+Long.parseLong(cp));
					//System.out.println("ESTE ES EL RESUKTADO DE LA OPERACION CREDITO snp01int"+snp01int);
					snp01=String.valueOf(snp01int);   
				}
				
				md.CrearDetalle_Acumulado_Sucursal_Centrocco(codasc,pp,snp13,dp,cp,snp01);//crea el detalle x periodo del detalle_acumulado_sucursal_centrocco
				snp13=snp01;//Al saldo anterior le asigno el saldo nuevo para el proximo periodo
		 
			}//fin for j
			
		}///fin sw==0
		else{//si la relacion si existe solo tocaria actualizar
			
			String sac="0";
			String sdc="0";
			String scc="0";
			String snc="0";
			
			rs = md.Detalle_Acumulado_Sucursal_CentroccoPeriodo(codasc,pc);//Consultamos todos los datos del periodo a actualizar
			try {
				if(rs.next()){
					sac=rs.getString(1);
					sdc=rs.getString(2);
					scc=rs.getString(3);
					snc=rs.getString(4);
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}	
			sdc=String.valueOf(Long.parseLong(sdc)+Long.parseLong(M[i][13]));
			scc=String.valueOf(Long.parseLong(scc)+Long.parseLong(M[i][14]));
		
			String natucta=M[i][0].substring(0, 1);//selecciona el inicio del nombre de la cuenta para saber la naturaleza
			if(natucta.equals("1")||natucta.equals("5")||natucta.equals("6")||natucta.equals("7")||natucta.equals("8")){
				long sncint=(Long.parseLong(sac)+Long.parseLong(sdc)-Long.parseLong(scc));
			//	System.out.println("ESTE ES EL RESUKTADO DE LA OPERACION DEBITO sncint"+sncint);
				snc=String.valueOf(sncint);             
			}
			if(natucta.equals("2")||natucta.equals("3")||natucta.equals("4")||natucta.equals("9")){
				long sncint=(Long.parseLong(sac)-Long.parseLong(sdc)+Long.parseLong(scc));
			//	System.out.println("ESTE ES EL RESUKTADO DE LA OPERACION CREDITO sncint"+sncint);
				snc=String.valueOf(sncint);   
			}
			
			md.ActualizarDetalle_Acumulado_Sucursal_Centrocco(sac,sdc,scc,snc,codasc,pc);//Actualiza el detalle con los valores del debito y credito y el saldo nuevo
			
			String pp2="";
			int inicio=Integer.parseInt(pc)+1;
			String sac2="0";
			String sdc2="0";
			String scc2="0";
			String snc2="0";
			
			for (int x=inicio; x<14; x++){// for que me ayuda a actualizar los registros de todos los periodos hacia abajo
				if(x<10){pp2="0"+x;}else{pp2=String.valueOf(x);}
				rs = md.Detalle_Acumulado_Sucursal_CentroccoPeriodo(codasc,pp2);//Consultamos el periodo 13 del a�o anterior y nos traemos el saldo nuevo
				try {
					if(rs.next()){
						//sac2=rs.getString(1);
						sdc2=rs.getString(2);
						scc2=rs.getString(3);
						//snc2=rs.getString(4);
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}	
				
				String natucta2=M[i][0].substring(0, 1);//selecciona el inicio del nombre de la cuenta para saber la naturaleza
				if(natucta2.equals("1")||natucta2.equals("5")||natucta2.equals("6")||natucta2.equals("7")||natucta2.equals("8")){
					snc2=String.valueOf(Long.parseLong(snc)+Long.parseLong(sdc2)-Long.parseLong(scc2));             
				}
				if(natucta2.equals("2")||natucta2.equals("3")||natucta2.equals("4")||natucta2.equals("9")){
					snc2=String.valueOf(Long.parseLong(snc)-Long.parseLong(sdc2)+Long.parseLong(scc2));   
				}
				
				md.ActualizarDetalle_Acumulado_Sucursal_Centrocco(snc,sdc2,scc2,snc2,codasc,pp2);//Actualiza el detalle hacia abajo con el saldo anterior
			snc=snc2;
			}//fin for <14
				
		}//fin del else la relacion si existe
			
			
			
			/*****ACUMULADO TERCERO*****///
			if(swt==0){//Si la relacion en Acumuladotercero no existe
				md.CrearAcumulado_Terceros(M[i][9],M[i][1],ac,M[i][4],M[i][6]);//Se crea la nueva relacion y se procede a crear los periodos
				rs = md.Acumulado_Terceros(M[i][9],M[i][1],ac,M[i][4],M[i][6]);//Nos traemos el codigo de la relacion q acabamos de crear
				try {
					if(rs.next()){
						codat=rs.getString(1);
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
			
				int acaa=(Integer.parseInt(ac)-1);
				String acaas=String.valueOf(acaa);
				String snp13="0";
				rs = md.Acumulado_Terceros(M[i][9],M[i][1],acaas,M[i][4],M[i][6]);//Consltamos si existe esa relacion en el a�o anterior para buscar en el detalle el saldo final del periodo 13
				int sw2=0;
				try {
					if(rs.next()){
						sw2=1;
						////////////////////
						rs1 = md.Detalle_Acumulado_TerceroP13(rs.getString(1));//Consultamos el periodo 13 del a�o anterior y nos traemos el saldo nuevo
						try {
							if(rs1.next()){
								snp13=rs1.getString(1);
							}
							rs1.getStatement().getConnection().close();
						} catch (SQLException e) {
							out.print("Error "+e);
							e.printStackTrace();
						}	
						///////////////////////
						
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				String pp="0";
				String dp="0";
				String cp="0";
				String snp01="";
				for(int j=1; j<=13; j++){
					if(j<10){pp="0"+j;}else{pp=String.valueOf(j);}
					if(pc.equals(pp)){dp=M[i][13]; cp=M[i][14];}//si periodo=j hay q cambiar el valor del debito y credito ysegun la naturaleza ejecutar la formula)
					else{dp="0"; cp="0";}
					
					String natucta=M[i][0].substring(0, 1);//selecciona el inicio del nombre de la cuenta para saber la naturaleza
					if(natucta.equals("1")||natucta.equals("5")||natucta.equals("6")||natucta.equals("7")||natucta.equals("8")){
						long snp01int=(Long.parseLong(snp13)+Long.parseLong(dp)-Long.parseLong(cp));
						System.out.println("ESTE ES EL RESUKTADO DE LA OPERACION DEBITO snp01int"+snp01int);
						snp01=String.valueOf(snp01int);             
					}
					if(natucta.equals("2")||natucta.equals("3")||natucta.equals("4")||natucta.equals("9")){
						long snp01int=(Long.parseLong(snp13)-Long.parseLong(dp)+Long.parseLong(cp));
						System.out.println("ESTE ES EL RESUKTADO DE LA OPERACION CREDITO snp01int"+snp01int);
						snp01=String.valueOf(snp01int);   
					}
					
					md.CrearDetalle_Acumulado_Tercero(codat,pp,snp13,dp,cp,snp01);//crea el detalle x periodo del detalle_acumulado_tercero
					snp13=snp01;//Al saldo anterior le asigno el saldo nuevo para el proximo periodo
			 
				}//fin for j
				
			}///fin swt==0
			else{//si la relacion si existe solo tocaria actualizar el tercero
				
				String sac="0";
				String sdc="0";
				String scc="0";
				String snc="0";
				
				rs = md.Detalle_Acumulado_Tercero(codat,pc);//Consultamos el periodo 13 del a�o anterior y nos traemos el saldo nuevo
				try {
					if(rs.next()){
						sac=rs.getString(1);
						sdc=rs.getString(2);
						scc=rs.getString(3);
						snc=rs.getString(4);
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}	
				sdc=String.valueOf(Long.parseLong(sdc)+Long.parseLong(M[i][13]));
				scc=String.valueOf(Long.parseLong(scc)+Long.parseLong(M[i][14]));
			
				String natucta=M[i][0].substring(0, 1);//selecciona el inicio del nombre de la cuenta para saber la naturaleza
				if(natucta.equals("1")||natucta.equals("5")||natucta.equals("6")||natucta.equals("7")||natucta.equals("8")){
					long sncint=(Long.parseLong(sac)+Long.parseLong(sdc)-Long.parseLong(scc));
					//System.out.println("ESTE ES EL RESUKTADO DE LA OPERACION DEBITO sncint"+sncint);
					snc=String.valueOf(sncint);             
				}
				if(natucta.equals("2")||natucta.equals("3")||natucta.equals("4")||natucta.equals("9")){
					long sncint=(Long.parseLong(sac)-Long.parseLong(sdc)+Long.parseLong(scc));
					//System.out.println("ESTE ES EL RESUKTADO DE LA OPERACION CREDITO sncint"+sncint);
					snc=String.valueOf(sncint);   
				}
				
				md.ActualizarDetalle_Acumulado_Tercero(sac,sdc,scc,snc,codat,pc);//Actualiza el detalle con los valores del debito y credito y el saldo nuevo
				
				String pp2="";
				int inicio=Integer.parseInt(pc)+1;
				String sac2="0";
				String sdc2="0";
				String scc2="0";
				String snc2="0";
				
				for (int x=inicio; x<14; x++){// for que me ayuda a actualizar los registros de todos los periodos hacia abajo
					if(x<10){pp2="0"+x;}else{pp2=String.valueOf(x);}
					rs = md.Detalle_Acumulado_Tercero(codat,pp2);//Consultamos el periodo 13 del a�o anterior y nos traemos el saldo nuevo
					try {
						if(rs.next()){
							//sac2=rs.getString(1);
							sdc2=rs.getString(2);
							scc2=rs.getString(3);
							//snc2=rs.getString(4);
						}
						rs.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}	
					
					String natucta2=M[i][0].substring(0, 1);//selecciona el inicio del nombre de la cuenta para saber la naturaleza
					if(natucta2.equals("1")||natucta2.equals("5")||natucta2.equals("6")||natucta2.equals("7")||natucta2.equals("8")){
						snc2=String.valueOf(Long.parseLong(snc)+Long.parseLong(sdc2)-Long.parseLong(scc2));             
					}
					if(natucta2.equals("2")||natucta2.equals("3")||natucta2.equals("4")||natucta2.equals("9")){
						snc2=String.valueOf(Long.parseLong(snc)-Long.parseLong(sdc2)+Long.parseLong(scc2));   
					}
					
					md.ActualizarDetalle_Acumulado_Tercero(snc,sdc2,scc2,snc2,codat,pp2);//Actualiza el detalle hacia abajo con el saldo anterior
				snc=snc2;
				}//fin for <14
					
			}//fin del else la relacion de acumulados terceros si existe
			
		}//3.FIN Creo detalle con el cod del documento
			
	}//fin for para crear las lineas del detalle
		
	
		out.println("La Modificaci�n del Documento ha sido Exitosa!!!");
	}
	////////////////////////
	
	
	
	
	/*****La parte de modificar el documento y actualizar los acumulados******/
	
	
	if(va.equals("22")){ ////Eliminar documento
		
		//	listo primero recorrrer para actualizar todo y ademas periodo x periodo hacia abajo
				
		/////////Realizar la copia de cont_documento a cont_movdocumentos/////////
		rs = md.ConsultarDocumento(docu);
		try {
			if(rs.next()){
				md.CrearMoviemientoDocumento(docu,rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),"5",rs.getString(13),rs.getString(14),rs.getString(15));
			}
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		
		/////////Realizar la copia de cont_detalle_documento a cont_movdetalle_documentos
		String codMovDocumento="";
		rs = md.ConsultarCodMovDocumento(docu);///Consulto el cod de la tabla cont_movdocumentos
		try {
			if(rs.next()){
				codMovDocumento=rs.getString(1);
			}
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		
		/***
		coddetalle  rs.getString(1)
		coddoc  rs.getString(2)
		ctah  rs.getString(3)
		succco   rs.getString(4)
		cscoo  rs.getString(5)
		terh rs.getString(6)
		des rs.getString(7)
		bas rs.getString(8)
		ref rs.getString(9)
		deb rs.getString(10)
		cre rs.getString(11)
		dife rs.getString(12)
		orig rs.getString(13)**/
		
		rs = md.ConsultarDetalleDocumento(docu);
		try {
			while(rs.next()){//MQ de los detalles de los Documentos
				md.CrearMoviemientoDetalleDocumento(codMovDocumento,rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13));			
			
				///Verificar si la relacion a�o,cta,succentro y centrosubcentro existe
				String codasc=""; 
				rs1 = md.Acumulado_Sucursal_Centrocco(rs.getString(4),rs.getString(5),rs.getString(3),ac);
				int sw=0;
				try {
					if(rs1.next()){
						sw=1;
						codasc=rs1.getString(1);
					}
					rs1.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}	
			
				///Verificar si la relacion tercero,cta,a�o,succntro y centrosubcentro existe
				String codat=""; 
				rs1 = md.Acumulado_Terceros(rs.getString(6),rs.getString(3),ac,rs.getString(4),rs.getString(5));
				int swt=0;
				try {
					if(rs1.next()){
						swt=1;
						codat=rs1.getString(1);
					}
					rs1.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				
				///////////////////////////////////////////////////////////////////
				String debo="";
				String creo="";
				rs1 = md.ConsultarDetalleDocumentoDebitoyCredito(rs.getString(1));//Obtengo el debito y credito original del detalle a eliminar
				try {
					if(rs1.next()){
						debo=rs1.getString(1);
						creo=rs1.getString(2);
					}
					rs1.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}	
				
				String sac="0";
				String sdc="0";
				String scc="0";
				String snc="0";
				
				rs1 = md.Detalle_Acumulado_Sucursal_CentroccoPeriodo(codasc,pc);//Consultamos todos los datos del periodo a actualizar
				try {
					if(rs1.next()){
						sac=rs1.getString(1);
						sdc=rs1.getString(2);
						scc=rs1.getString(3);
						snc=rs1.getString(4);
					}
					rs1.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}	
				sdc=String.valueOf(Long.parseLong(sdc)-Long.parseLong(debo));
				scc=String.valueOf(Long.parseLong(scc)-Long.parseLong(creo));
				
				String nomcta="";
				rs1 = md.ConsultarNombreCta(rs.getString(3));//Consultamos todos los datos del periodo a actualizar
				try {
					if(rs1.next()){
						nomcta=rs1.getString(1);
					}
					rs1.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}	
				
				
				String natucta=nomcta.substring(0, 1);//selecciona el inicio del nombre de la cuenta para saber la naturaleza
				if(natucta.equals("1")||natucta.equals("5")||natucta.equals("6")||natucta.equals("7")||natucta.equals("8")){
					long sncint=(Long.parseLong(sac)+Long.parseLong(sdc)-Long.parseLong(scc));
				//	System.out.println("ESTE ES EL RESUKTADO DE LA OPERACION DEBITO sncint"+sncint);
					snc=String.valueOf(sncint);             
				}
				if(natucta.equals("2")||natucta.equals("3")||natucta.equals("4")||natucta.equals("9")){
					long sncint=(Long.parseLong(sac)-Long.parseLong(sdc)+Long.parseLong(scc));
				//	System.out.println("ESTE ES EL RESUKTADO DE LA OPERACION CREDITO sncint"+sncint);
					snc=String.valueOf(sncint);   
				}
				
				md.ActualizarDetalle_Acumulado_Sucursal_Centrocco(sac,sdc,scc,snc,codasc,pc);//Actualiza el detalle con los valores del debito y credito y el saldo nuevo
				
				String pp2="";
				int inicio=Integer.parseInt(pc)+1;
				String sac2="0";
				String sdc2="0";
				String scc2="0";
				String snc2="0";
				
				for (int x=inicio; x<14; x++){// for que me ayuda a actualizar los registros de todos los periodos hacia abajo
					if(x<10){pp2="0"+x;}else{pp2=String.valueOf(x);}
					rs1 = md.Detalle_Acumulado_Sucursal_CentroccoPeriodo(codasc,pp2);//Consultamos el periodo 13 del a�o anterior y nos traemos el saldo nuevo
					try {
						if(rs1.next()){
							//sac2=rs.getString(1);
							sdc2=rs1.getString(2);
							scc2=rs1.getString(3);
							//snc2=rs.getString(4);
						}
						rs1.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}	
					
					String natucta2=natucta;//selecciona el inicio del nombre de la cuenta para saber la naturaleza
					if(natucta2.equals("1")||natucta2.equals("5")||natucta2.equals("6")||natucta2.equals("7")||natucta2.equals("8")){
						snc2=String.valueOf(Long.parseLong(snc)+Long.parseLong(sdc2)-Long.parseLong(scc2));             
					}
					if(natucta2.equals("2")||natucta2.equals("3")||natucta2.equals("4")||natucta2.equals("9")){
						snc2=String.valueOf(Long.parseLong(snc)-Long.parseLong(sdc2)+Long.parseLong(scc2));   
					}
					
					md.ActualizarDetalle_Acumulado_Sucursal_Centrocco(snc,sdc2,scc2,snc2,codasc,pp2);//Actualiza el detalle hacia abajo con el saldo anterior
				snc=snc2;
				}//fin for <14
				
				
				
				/*****Ahora Terceros*****/
				
				////////////////////////////PRIMERO DESHACEMOS EL ACUMULADO RESTANDO LOS VALORES ORIGINALES/////////////////////////////////////

								
				String sacn="0";
				String sdcn="0";
				String sccn="0";
				String sncn="0";
				
				rs1 = md.Detalle_Acumulado_Tercero(codat,pc);//Consultamos todos los datos del periodo a actualizar
				try {
					if(rs1.next()){
						sacn=rs1.getString(1);
						sdcn=rs1.getString(2);
						sccn=rs1.getString(3);
						sncn=rs1.getString(4);
					}
					rs1.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}	
				sdcn=String.valueOf(Long.parseLong(sdcn)-Long.parseLong(debo));
				sccn=String.valueOf(Long.parseLong(sccn)-Long.parseLong(creo));
			
			//	natucta=natucta;//selecciona el inicio del nombre de la cuenta para saber la naturaleza
				if(natucta.equals("1")||natucta.equals("5")||natucta.equals("6")||natucta.equals("7")||natucta.equals("8")){
					long sncint=(Long.parseLong(sacn)+Long.parseLong(sdcn)-Long.parseLong(sccn));
					//System.out.println("ESTE ES EL RESUKTADO DE LA OPERACION DEBITO sncint"+sncint);
					sncn=String.valueOf(sncint);             
				}
				if(natucta.equals("2")||natucta.equals("3")||natucta.equals("4")||natucta.equals("9")){
					long sncint=(Long.parseLong(sacn)-Long.parseLong(sdcn)+Long.parseLong(sccn));
					//System.out.println("ESTE ES EL RESUKTADO DE LA OPERACION CREDITO sncint"+sncint);
					sncn=String.valueOf(sncint);   
				}
				
				md.ActualizarDetalle_Acumulado_Tercero(sacn,sdcn,sccn,sncn,codat,pc);//Actualiza el detalle con los valores del debito y credito y el saldo nuevo
				
				String pp2n="";
				int inicion=Integer.parseInt(pc)+1;
				String sac2n="0";
				String sdc2n="0";
				String scc2n="0";
				String snc2n="0";
				
				for (int x=inicion; x<14; x++){// for que me ayuda a actualizar los registros de todos los periodos hacia abajo
					if(x<10){pp2n="0"+x;}else{pp2n=String.valueOf(x);}
					rs1 = md.Detalle_Acumulado_Tercero(codat,pp2n);//Consultamos el periodo 13 del a�o anterior y nos traemos el saldo nuevo
					try {
						if(rs1.next()){
							//sac2=rs.getString(1);
							sdc2n=rs1.getString(2);
							scc2n=rs1.getString(3);
							//snc2=rs.getString(4);
						}
						rs1.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}	
					
					String natucta2=natucta;//selecciona el inicio del nombre de la cuenta para saber la naturaleza
					if(natucta2.equals("1")||natucta2.equals("5")||natucta2.equals("6")||natucta2.equals("7")||natucta2.equals("8")){
						snc2n=String.valueOf(Long.parseLong(snc)+Long.parseLong(sdc2n)-Long.parseLong(scc2n));             
					}
					if(natucta2.equals("2")||natucta2.equals("3")||natucta2.equals("4")||natucta2.equals("9")){
						snc2n=String.valueOf(Long.parseLong(snc)-Long.parseLong(sdc2n)+Long.parseLong(scc2n));   
					}
					
					md.ActualizarDetalle_Acumulado_Tercero(snc,sdc2n,scc2n,snc2n,codat,pp2n);//Actualiza el detalle hacia abajo con el saldo anterior
				snc=snc2n;
				}//fin for <14
					

				md.EliminarDetalle_Documento(rs.getString(1));//Elimino el detalle del Documento

			}//Fin del MQ de los detalles de los Documentos
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		
		md.EliminarDocumento(docu);//Elimino el Documento
	
			
		out.println("El Documento ha sido Eliminado Exitosamente!!!");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/***** 
	 * 

	falta la modificacion de a�o en a�o en este 
	y el proceso anual de actualizacion de saldos
				
	*****/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*******************************************CHEQUES*********************************************************************/
	
	
	if(va.equals("23p")){ //Verifica que el periodo exista o este habilitado
		System.out.println(annio+"-"+mes);
		rs = md.ConsultarPeriodo(mes, annio);
		String sw="00";
		try {
			if(rs.next()){
				sw=rs.getString(1)+"|"+rs.getString(2);
			}	
			rs.getStatement().getConnection().close();
		out.print(sw);
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
	}//fin equals 2
	
	
	if(va.equals("23")){//Para crear cheques
		//EGR000000001
		out.print("<table width='100%' id='enca' border='0' class='style6'><tr><td colspan='3'><div align='center' class='style11' id='cabecera2'>EGRESOS POR CHEQUE</div></td></tr>");
		out.print("<tr><td colspan='3'><div align='center' ><a  href='#'onclick='CCheque()'>Consultar Cheque</a></div></td></tr>");
		out.print("<tr><td  colspan='1'><div align='left'>Fecha: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type=text id='fd' size='6' readonly='readonly' value='"+fechacj+"'></div></td>");
		out.print("<td  colspan='2'><div align='right'>Egreso No: &nbsp;<input type=text id='numdoc' size='12'  readonly='readonly' ></div></td></tr>");
		out.print("<tr><td  width='33%'></td><td  width=33%'></td><td  width='33%'></td></tr>");
		//out.print("<tr><td  width='12%'>Fecha: &nbsp;&nbsp;<input type=text id='fd' size='6' readonly='readonly' value='"+fechacj+"'></td>");
		out.print("<tr><td>Sucursal: &nbsp;<select  style='width:45px' name='sucuc' id='sucuc' onChange='vsucu(0)'><option value='0'>Seleccione</option>");
		rs = md.Sucursales();
		try {
			while(rs.next()){
				out.print("<option value="+rs.getString(1)+">"+rs.getString(1)+"</option>");
			}
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		out.print("</select>");
		out.print("<select  style='width:180px' name='sucun' id='sucun' onChange='vsucu(1)'><option value='0'>Seleccione</option>");
		rs = md.Sucursales();
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

		out.print("<td><div id='Centrosc' >C.Costo: <select  style='width:50px' id='ccostoc' onChange='vccosto(0)'><option value='0'>Seleccione</option></select><select  style='width:170px' id='ccoston' onChange='vccosto(1)'><option value='0'>Seleccione</option></select></div></td>");//div nuevo para montar ccosto
		out.print("<td><div id='SCentrosc' >SubC.Costo: <select  style='width:50px' id='sccostoc' onChange='vsccosto(0)'><option value='0'>Seleccione</option></select><select  style='width:170px' id='sccoston' onChange='vsccosto(1)'><option value='0'>Seleccione</option></select></div></td></tr>");//div nuevo para montar ccosto
		
		out.print("<tr><td>Banco: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select  style='width:45px' name='ban' id='ban' onChange='vbanco(0)'><option value='0'>Seleccione</option>");
		rs = md.Bancos();
		try {
			while(rs.next()){
				out.print("<option value="+rs.getString(3)+">"+rs.getString(1)+"</option>");
			}
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		out.print("</select>");
		out.print("<select  style='width:180px' name='bann' id='bann' onChange='vbanco(1)'><option value='0'>Seleccione</option>");
		rs = md.Bancos();
		try {
			while(rs.next()){
				out.print("<option value="+rs.getString(3)+">"+rs.getString(2)+"</option>");
			}
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		out.print("</select></td>");
		
		out.print("<td >Girado: &nbsp;&nbsp;<select  style='width:70px' id='tercero' onChange='vter(0)'><option value='0'>Seleccione</option>");
		rs = md.Terceros();
		try {
			while(rs.next()){
				out.print("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
			}
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		out.print("</select>");
		out.print("<select  style='width:151px' id='terceron' onChange='vter(1)'><option value='0'>Seleccione</option>");
		rs = md.Terceros();
		try {
			while(rs.next()){
				out.print("<option value="+rs.getString(1)+">"+rs.getString(3)+"</option>");
			}
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		out.print("</select></td>");
		//validar solo numeros
		out.print("<td>Valor: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type=text id='valor0' size='31' value='0' onkeyup='format(this)'></td></tr>");//vnb(0,5)
		out.print("<tr><td>Cheque:&nbsp;&nbsp;&nbsp;&nbsp;<input type=text id='cheq' size='32' ></td>");
		
		out.print("<td colspan='2'>Concepto:<select  style='width:565px' id='concepto' onChange='vter(0)'><option value='0'>Seleccione</option>");
		rs = md.ConceptordeEgreso();
		try {
			while(rs.next()){
				out.print("<option title="+rs.getString(1)+" value="+rs.getString(3)+">"+rs.getString(2)+"</option>");
			}
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		out.print("</select></tr>");
		
		
		out.print("<tr><td  colspan='3'>Detalle:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type=text id='detalle' size='142' ></td></tr>");
		
		out.print("<tr><td colspan='3'><div align='center' id='gcheque'><input type='button'  value='  Guardar  ' onClick='GuardarCheque()' ></div></td></tr></table>");
		//out.print("<tr><td id='tdboton'><input type='button' value='  Consultar  ' onClick='GuardarCheque(&quot;"+pc+"&quot;,"+ac+")' ></td></tr></table>");
		out.print("<table width='100%'  border='0' ><tr><td colspan='3'><div align='center' id='Cargando'></div></td></tr>");
			
	}//if v=0 	
	
	if(va.equals("23C")){//Para crear cheques
		//EGR000000001
		out.print("<table width='100%' id='enca' border='0' class='style6'><tr><td colspan='3'><div align='center' class='style11' id='cabecera2'>EGRESOS POR CHEQUE</div></td></tr>");
		out.print("<tr><td colspan='3'><div align='center' ><a  href='#'onclick='CCheque()'>Consultar Cheque</a></div></td></tr>");
		out.print("<tr><td  colspan='1'><div align='left'><font color='silver'>Fecha: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font><input type=text id='fd' size='6' value='0' DISABLED></div></td>");
		out.print("<td  colspan='2'><div align='right'>Egreso No: &nbsp;<input type=text id='numdoc' size='12'  ></div></td></tr>");
		out.print("<tr><td  width='33%'></td><td  width=33%'></td><td  width='33%'></td></tr>");
		//out.print("<tr><td  width='12%'>Fecha: &nbsp;&nbsp;<input type=text id='fd' size='6' readonly='readonly' value='"+fechacj+"'></td>");
		out.print("<tr><td><font color='silver'>Sucursal: &nbsp;</font><select  style='width:45px' name='sucuc' id='sucuc' DISABLED><option value='0'>Seleccione</option>");
		out.print("</select>");
		out.print("<select  style='width:180px' name='sucun' id='sucun' DISABLED><option value='0'>Seleccione</option>");
		out.print("</select></td>");
		
		
		out.print("<td><div id='Centrosc' ><font color='silver'>C.Costo: </font><select  style='width:50px' id='ccostoc' DISABLED><option value='0'>Seleccione</option></select><select  style='width:170px' id='ccoston' DISABLED><option value='0'>Seleccione</option></select></div></td>");//div nuevo para montar ccosto
		out.print("<td><div id='SCentrosc' ><font color='silver'>SubC.Costo: </font><select  style='width:50px' id='sccostoc' DISABLED><option value='0'>Seleccione</option></select><select  style='width:170px' id='sccoston' DISABLED><option value='0'>Seleccione</option></select></div></td></tr>");//div nuevo para montar ccosto
		
		out.print("<tr><td><font color='silver'>Banco: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font><select  style='width:45px' name='ban' id='ban' DISABLED><option value='0'>Seleccione</option>");
		out.print("</select>");
		out.print("<select  style='width:180px' name='bann' id='bann' DISABLED><option value='0'>Seleccione</option>");
		out.print("</select></td>");
		
		out.print("<td >Girado: &nbsp;&nbsp;<select  style='width:70px' id='tercero' onChange='vter(0)'><option value='0'>Seleccione</option>");
		rs = md.Terceros();
		try {
			while(rs.next()){
				out.print("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
			}
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		out.print("</select>");
		out.print("<select  style='width:151px' id='terceron' onChange='vter(1)'><option value='0'>Seleccione</option>");
		rs = md.Terceros();
		try {
			while(rs.next()){
				out.print("<option value="+rs.getString(1)+">"+rs.getString(3)+"</option>");
			}
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		out.print("</select></td>");
		//validar solo numeros
		out.print("<td><font color='silver'>Valor: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font><input type=text id='valor0' size='31' value='0' DISABLED></td></tr>");//vnb(0,5)
		out.print("<tr><td>Cheque:&nbsp;&nbsp;&nbsp;&nbsp;<input type=text id='cheq' size='32' ></td>");
		
		out.print("<td colspan='2'><font color='silver'>Concepto:</font><select  style='width:565px' id='concepto' DISABLED><option value='0'>Seleccione</option>");
		out.print("</select></tr>");
		
		
		out.print("<tr><td  colspan='3'><font color='silver'>Detalle:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font><input type=text id='detalle' size='142' value='0' DISABLED></td></tr>");
		
		out.print("<tr><td colspan='3'><div align='center' id='gcheque'><input type='button'  value='  Consultar  ' onClick='ConsultarCheque()' ></div></td></tr></table>");
		//out.print("<tr><td id='tdboton'><input type='button' value='  Consultar  ' onClick='GuardarCheque(&quot;"+pc+"&quot;,"+ac+")' ></td></tr></table>");
		out.print("<table width='100%'  border='0' ><tr><td colspan='3'><div align='center' id='Cargando'></div></td></tr>");
			
	}//if v=0 	
	
	if(va.equals("24")){//Para crear documento cheque
		
		String conso="";
		String cons="";
		String consn="";
		rs = md.ConsecutivosdeCuentas("140");
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
		md.CrearDocumento(annio,mes,"140",consn,fechacjmysql,dd,vdebito,vdebito,"0","",user,fechacjmysql,hra);
		
		String doc="";
		rs = md.ConsecutivoUDocumento(consn);
		try {
			if(rs.next()){
				doc=rs.getString(1);
				//out.print("<input type=hidden id='docuh'  value="+rs.getString(1)+"></td>");
			}
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}	
		
		int ctan=Integer.parseInt(conso)+1;
		conso=String.valueOf(ctan);
		md.ActualizarConsecutivo(conso,"140");
		
		
		md.CrearDetalleDocumento(doc,conc,ccd,scd,ter,dd,"0",cheque,vdebito,"0","0","CHE");/// con el codigo del concepto
		md.CrearDetalleDocumento(doc,bd,ccd,scd,ter,dd,"0",cheque,"0",vdebito,"0","CHE");/// con el codigo del banco
		
	//	1111111111111111111111
		
		/////////////////////ACUMULADO SUCURSAL////////////////////
		
		//Eliminarestomd.CrearDetalleDocumento(docu,M[i][1],M[i][4],M[i][6],M[i][9],M[i][10],M[i][11],M[i][12],M[i][13],M[i][14],"0","CNT");
				
		String ctav=conc;// la primera vez esta cuenta
		String vnd=vdebito;
		String vnc="0";
		String vncta="";
		for(int i=0;i<2;i++){//for de los dos detalles
				
		/***  Consultar y crear el Acumulado sucursal centro de costo *****////////////
		
		rs = md.CentrosySubcentrosdeCosto(scd);
		String vcco="";
		String vscc="";
		try {
			while(rs.next()){
				vcco=rs.getString(1);
				vscc=rs.getString(2);
			}
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		
		///Verificar si la relacion a�o,cta,succntro y centrosubcentro existe
		String codasc=""; 
		rs = md.Acumulado_Sucursal_Centrocco(ccd,scd,ctav,annio);
		int sw=0;
		try {
			if(rs.next()){
				sw=1;
				codasc=rs.getString(1);
			}
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		
		rs1 = md.ConsultarNombreCta(ctav);//Consultamos el periodo 13 del a�o anterior y nos traemos el saldo nuevo
		try {
			if(rs1.next()){
				vncta=rs1.getString(1);
				//System.out.println("vncta111: "+vncta);
			}
			rs1.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}	
		
		if(sw==0){//Si la relacion en Acumulado_sucursal_centrodecosto no existe
			md.CrearAcumulado_Sucursal_Centrocco(ccd,scd,ctav,annio);//Se crea la nueva relacion y se procede a crear los periodos
			rs = md.Acumulado_Sucursal_Centrocco(ccd,scd,ctav,annio);//Nos traemos el codigo de la relacion q acabamos de crear
			try {
				if(rs.next()){
					codasc=rs.getString(1);
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			int acaa=(Integer.parseInt(annio)-1);
			String acaas=String.valueOf(acaa);
			String snp13="0";
			rs = md.Acumulado_Sucursal_Centrocco(ccd,scd,ctav,acaas);//Consltamos si existe esa relacion en el a�o anterior para buscar en el detalle el saldo final del periodo 13
			int sw2=0;
			try {
				if(rs.next()){
					sw2=1;
					////////////////////
					rs1 = md.Detalle_Acumulado_Sucursal_CentroccoP13(rs.getString(1));//Consultamos el periodo 13 del a�o anterior y nos traemos el saldo nuevo
					try {
						if(rs1.next()){
							snp13=rs1.getString(1);
						}
						rs1.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}	
					///////////////////////
					
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
	

			
			//System.out.println("vncta_: "+vncta);
			String pp="0";
			String dp="0";
			String cp="0";
			String snp01="";
			for(int j=1; j<=13; j++){
				if(j<10){pp="0"+j;}else{pp=String.valueOf(j);}
				if(mes.equals(pp)){dp=vnd; cp=vnc;}//si periodo=j hay q cambiar el valor del debito y credito ysegun la naturaleza ejecutar la formula)
				else{dp="0"; cp="0";}
				
				String natucta=vncta.substring(0, 1);//selecciona el inicio del nombre de la cuenta para saber la naturaleza
				if(natucta.equals("1")||natucta.equals("5")||natucta.equals("6")||natucta.equals("7")||natucta.equals("8")){
					long snp01int=(Long.parseLong(snp13)+Long.parseLong(dp)-Long.parseLong(cp));
					System.out.println("ESTE ES EL RESUKTADO DE LA OPERACION DEBITO snp01int"+snp01int);
					snp01=String.valueOf(snp01int);             
				}
				if(natucta.equals("2")||natucta.equals("3")||natucta.equals("4")||natucta.equals("9")){
					long snp01int=(Long.parseLong(snp13)-Long.parseLong(dp)+Long.parseLong(cp));
					System.out.println("ESTE ES EL RESUKTADO DE LA OPERACION CREDITO snp01int"+snp01int);
					snp01=String.valueOf(snp01int);   
				}
				
				md.CrearDetalle_Acumulado_Sucursal_Centrocco(codasc,pp,snp13,dp,cp,snp01);//crea el detalle x periodo del detalle_acumulado_sucursal_centrocco
				snp13=snp01;//Al saldo anterior le asigno el saldo nuevo para el proximo periodo
		 
			}//fin for j
			
		}///fin sw==0
		else{//si la relacion si existe solo tocaria actualizar
			
			String sac="0";
			String sdc="0";
			String scc="0";
			String snc="0";
			
			rs = md.Detalle_Acumulado_Sucursal_CentroccoPeriodo(codasc,mes);//Consultamos el periodo 13 del a�o anterior y nos traemos el saldo nuevo
			try {
				if(rs.next()){
					sac=rs.getString(1);
					sdc=rs.getString(2);
					scc=rs.getString(3);
					snc=rs.getString(4);
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}	
			
				
			sdc=String.valueOf(Long.parseLong(sdc)+Long.parseLong(vnd));
			scc=String.valueOf(Long.parseLong(scc)+Long.parseLong(vnc));
			//System.out.println("vnctallll_: "+vncta);
			String natucta=vncta.substring(0, 1);//selecciona el inicio del nombre de la cuenta para saber la naturaleza
			if(natucta.equals("1")||natucta.equals("5")||natucta.equals("6")||natucta.equals("7")||natucta.equals("8")){
				long sncint=(Long.parseLong(sac)+Long.parseLong(sdc)-Long.parseLong(scc));
				System.out.println("ESTE ES EL RESUKTADO DE LA OPERACION DEBITO sncint"+sncint);
				snc=String.valueOf(sncint);             
			}
			if(natucta.equals("2")||natucta.equals("3")||natucta.equals("4")||natucta.equals("9")){
				long sncint=(Long.parseLong(sac)-Long.parseLong(sdc)+Long.parseLong(scc));
				System.out.println("ESTE ES EL RESUKTADO DE LA OPERACION CREDITO sncint"+sncint);
				snc=String.valueOf(sncint);   
			}
			
			md.ActualizarDetalle_Acumulado_Sucursal_Centrocco(sac,sdc,scc,snc,codasc,mes);//Actualiza el detalle con los valores del debito y credito y el saldo nuevo
			
			String pp2="";
			int inicio=Integer.parseInt(mes)+1;
			String sac2="0";
			String sdc2="0";
			String scc2="0";
			String snc2="0";
			
			for (int x=inicio; x<14; x++){// for que me ayuda a actualizar los registros de todos los periodos hacia abajo
				if(x<10){pp2="0"+x;}else{pp2=String.valueOf(x);}
				rs = md.Detalle_Acumulado_Sucursal_CentroccoPeriodo(codasc,pp2);//Consultamos el periodo 13 del a�o anterior y nos traemos el saldo nuevo
				try {
					if(rs.next()){
						//sac2=rs.getString(1);
						sdc2=rs.getString(2);
						scc2=rs.getString(3);
						//snc2=rs.getString(4);
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}	
				
				String natucta2=vncta.substring(0, 1);//selecciona el inicio del nombre de la cuenta para saber la naturaleza
				if(natucta2.equals("1")||natucta2.equals("5")||natucta2.equals("6")||natucta2.equals("7")||natucta2.equals("8")){
					snc2=String.valueOf(Long.parseLong(snc)+Long.parseLong(sdc2)-Long.parseLong(scc2));             
				}
				if(natucta2.equals("2")||natucta2.equals("3")||natucta2.equals("4")||natucta2.equals("9")){
					snc2=String.valueOf(Long.parseLong(snc)-Long.parseLong(sdc2)+Long.parseLong(scc2));   
				}
				
				md.ActualizarDetalle_Acumulado_Sucursal_Centrocco(snc,sdc2,scc2,snc2,codasc,pp2);//Actualiza el detalle hacia abajo con el saldo anterior
			snc=snc2;
			}//fin for <14
				
		}//fin del else la relacion de acumulados sucursalcentrossubcentros si existe
	
		//////////////////////FIN ACUMULADO SUCURSAL/////////////////
		//////////////////////ACUMULADO TERCERO/////////////////
		
	/****EMPIEZA EL ACUMULADO TERCERO***/
	
		
		///Verificar si la relacion tercero,cta,a�o,succntro y centrosubcentro existe
		String codat=""; 
		rs = md.Acumulado_Terceros(ter,ctav,annio,ccd,scd);
		int swt=0;
		try {
			if(rs.next()){
				swt=1;
				codat=rs.getString(1);
			}
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		
		
		/*rs1 = md.ConsultarNombreCta(ctav);//Consultamos el periodo 13 del a�o anterior y nos traemos el saldo nuevo
		try {
			if(rs1.next()){
				vncta=rs1.getString(1);
				System.out.println("vncta2222: "+vncta);
			}
			rs1.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}	
		*/
		if(swt==0){//Si la relacion en Acumuladotercero no existe
			md.CrearAcumulado_Terceros(ter,ctav,annio,ccd,scd);//Se crea la nueva relacion y se procede a crear los periodos
			rs = md.Acumulado_Terceros(ter,ctav,annio,ccd,scd);//Nos traemos el codigo de la relacion q acabamos de crear
			try {
				if(rs.next()){
					codat=rs.getString(1);
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			int acaa=(Integer.parseInt(annio)-1);
			String acaas=String.valueOf(acaa);
			String snp13="0";
			rs = md.Acumulado_Terceros(ter,ctav,acaas,ccd,scd);//Consltamos si existe esa relacion en el a�o anterior para buscar en el detalle el saldo final del periodo 13
			int sw2=0;
			try {
				if(rs.next()){
					sw2=1;
					////////////////////
					rs1 = md.Detalle_Acumulado_TerceroP13(rs.getString(1));//Consultamos el periodo 13 del a�o anterior y nos traemos el saldo nuevo
					try {
						if(rs1.next()){
							snp13=rs1.getString(1);
						}
						rs1.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}	
					///////////////////////
					
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			String pp="0";
			String dp="0";
			String cp="0";
			String snp01="";
			
			for(int j=1; j<=13; j++){
				if(j<10){pp="0"+j;}else{pp=String.valueOf(j);}
				if(mes.equals(pp)){dp=vnd; cp=vnc;}//si periodo=j hay q cambiar el valor del debito y credito ysegun la naturaleza ejecutar la formula)
				else{dp="0"; cp="0";}
				
				String natucta=vncta.substring(0, 1);//selecciona el inicio del nombre de la cuenta para saber la naturaleza
				if(natucta.equals("1")||natucta.equals("5")||natucta.equals("6")||natucta.equals("7")||natucta.equals("8")){
					long snp01int=(Long.parseLong(snp13)+Long.parseLong(dp)-Long.parseLong(cp));
					System.out.println("ESTE ES EL RESUKTADO DE LA OPERACION DEBITO snp01int"+snp01int);
					snp01=String.valueOf(snp01int);             
				}
				if(natucta.equals("2")||natucta.equals("3")||natucta.equals("4")||natucta.equals("9")){
					long snp01int=(Long.parseLong(snp13)-Long.parseLong(dp)+Long.parseLong(cp));
					System.out.println("ESTE ES EL RESUKTADO DE LA OPERACION CREDITO snp01int"+snp01int);
					snp01=String.valueOf(snp01int);   
				}
				
				md.CrearDetalle_Acumulado_Tercero(codat,pp,snp13,dp,cp,snp01);//crea el detalle x periodo del detalle_acumulado_tercero
				snp13=snp01;//Al saldo anterior le asigno el saldo nuevo para el proximo periodo
		 
			}//fin for j
			
		}///fin swt==0
		else{//si la relacion si existe solo tocaria actualizar el tercero
			
			String sac="0";
			String sdc="0";
			String scc="0";
			String snc="0";
	
			rs = md.Detalle_Acumulado_Tercero(codat,mes);//Consultamos el periodo 13 del a�o anterior y nos traemos el saldo nuevo
			try {
				if(rs.next()){
					sac=rs.getString(1);
					sdc=rs.getString(2);
					scc=rs.getString(3);
					snc=rs.getString(4);
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}	
			sdc=String.valueOf(Long.parseLong(sdc)+Long.parseLong(vnd));
			scc=String.valueOf(Long.parseLong(scc)+Long.parseLong(vnc));
		
			String natucta=vncta.substring(0, 1);//selecciona el inicio del nombre de la cuenta para saber la naturaleza
			if(natucta.equals("1")||natucta.equals("5")||natucta.equals("6")||natucta.equals("7")||natucta.equals("8")){
				long sncint=(Long.parseLong(sac)+Long.parseLong(sdc)-Long.parseLong(scc));
				//System.out.println("ESTE ES EL RESUKTADO DE LA OPERACION DEBITO sncint"+sncint);
				snc=String.valueOf(sncint);             
			}
			if(natucta.equals("2")||natucta.equals("3")||natucta.equals("4")||natucta.equals("9")){
				long sncint=(Long.parseLong(sac)-Long.parseLong(sdc)+Long.parseLong(scc));
				//System.out.println("ESTE ES EL RESUKTADO DE LA OPERACION CREDITO sncint"+sncint);
				snc=String.valueOf(sncint);   
			}
			
			md.ActualizarDetalle_Acumulado_Tercero(sac,sdc,scc,snc,codat,mes);//Actualiza el detalle con los valores del debito y credito y el saldo nuevo
			
			String pp2="";
			int inicio=Integer.parseInt(mes)+1;
			String sac2="0";
			String sdc2="0";
			String scc2="0";
			String snc2="0";
			
			for (int x=inicio; x<14; x++){// for que me ayuda a actualizar los registros de todos los periodos hacia abajo
				if(x<10){pp2="0"+x;}else{pp2=String.valueOf(x);}
				rs = md.Detalle_Acumulado_Tercero(codat,pp2);//Consultamos el periodo 13 del a�o anterior y nos traemos el saldo nuevo
				try {
					if(rs.next()){
						//sac2=rs.getString(1);
						sdc2=rs.getString(2);
						scc2=rs.getString(3);
						//snc2=rs.getString(4);
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}	
				
				String natucta2=vncta.substring(0, 1);//selecciona el inicio del nombre de la cuenta para saber la naturaleza
				if(natucta2.equals("1")||natucta2.equals("5")||natucta2.equals("6")||natucta2.equals("7")||natucta2.equals("8")){
					snc2=String.valueOf(Long.parseLong(snc)+Long.parseLong(sdc2)-Long.parseLong(scc2));             
				}
				if(natucta2.equals("2")||natucta2.equals("3")||natucta2.equals("4")||natucta2.equals("9")){
					snc2=String.valueOf(Long.parseLong(snc)-Long.parseLong(sdc2)+Long.parseLong(scc2));   
				}
				
				md.ActualizarDetalle_Acumulado_Tercero(snc,sdc2,scc2,snc2,codat,pp2);//Actualiza el detalle hacia abajo con el saldo anterior
			snc=snc2;
			}//fin for <14
				
		}//fin del else la relacion de acumulados sucursalcentrossubcentros si existe
		////////////////////FIN ACUMULADO TERCERO///////////////
		ctav=bd;
		vnd="0";
		vnc=vdebito;
		}//fin del for de 2 el del banco y el del egreso
		
			
		
	out.println(consn);
	}
	
	
	
	if(va.equals("25")){//Para 
		String sql="";
		if(docu.equals("")){docu="";}else{sql=sql+" d.numero_documento="+docu;}
		if(ter.equals("Seleccione")){ter="";}else{sql=sql+" AND t.codigo="+ter;}
		if(cheque.equals("")){cheque="";}else{sql=sql+" AND dd.documento_referencia="+cheque;}
		
		out.print("<table width='100%' class='style6'><tr><td colspan='6'><div align='center' class='style11' id='cabecera2'>RESULTADOS DE LA BUSQUEDA</div></td></tr>");
		out.print("<tr BGCOLOR='#BDBDBD'><td width='12%'><div align='center'>DOCUMENTO</div></td><td width='12%'><div align='center'>CHEQUE</div></td><td width='12%'><div align='center'>FECHA</div></td><td width='30%'><div align='center'>DETALLE</div></td><td width='30%'><div align='center'>TERCERO</div></td><td width='4%'><div align='center'>ACCION</div></td></tr>");
		
		rs = md.ConsultarCheque(sql);//Consultamos el periodo 13 del a�o anterior y nos traemos el saldo nuevo
		try {
			while(rs.next()){
				out.print("<tr><td><label><input name='txtDevolver' type='text' id='nd' readonly='' value='"+rs.getString(1)+"' style='width:100%' /></label></td>");	
				out.print("<td><label><input name='txtDevolver' type='text' id='dr' readonly='' value='"+rs.getString(2)+"' style='width:100%'/></label></td>");	
				out.print("<td><label><input name='txtDevolver' type='text' id='fd' readonly='' value='"+rs.getString(3)+"' style='width:100%' /></label></td>");	
				out.print("<td><label><input name='txtDevolver' type='text' id='dd' readonly='' value='"+rs.getString(4)+"' style='width:100%' /></label></td>");	
				out.print("<td><label><input name='txtDevolver' type='text' id='rs' readonly='' value='"+rs.getString(5)+"' style='width:100%' /></label></td>");	
				out.print("<td><a  href='#'onclick='ConsultarCheque2(&quot;"+rs.getString(1)+"&quot;,&quot;"+rs.getString(3)+"&quot;,"+rs.getString(6)+")'>Imprimir</a></td></tr>");		
			}
			
			
			out.print("</table>");
			
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}	
	
	}
	
	if(va.equals("26")){//Para c
	String L=Convertir(v);
	out.print(L);
	}
	


	if(va.equals("26y")){//Para reporte de egreso
		String doc=req.getParameter("doc");
		String tip=req.getParameter("tip");
	
		String consecutivo="";
		String valordebito="";
		String fdche="";
		String tipo="";
		rs=md.ConsultarDocumento(doc);
		try {
			if(rs.next()){
				consecutivo=rs.getString(5);
				valordebito=rs.getString(8);
				fdche=rs.getString(6);
				tipo=rs.getString(4);
			}rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("valor "+consecutivo);
		String L="";
		if(tip.equals("1")){
			L=Convertir(valordebito);
			out.print(L+"_"+consecutivo+"_"+fdche+"_"+tipo);
		}else{
			if(tip.equals("0")){
				L=Convertir(v);
				out.print(L+"_"+consecutivo);
			}
		}
	}

///////////////////////////ACTUALIZAR SALDOS///////////////////////////////
	
	if(va.equals("30")){	//Selecciona el Periodos
		out.print("<table width='100%'  border='0' class='style6'><tr><td colspan='5'><div align='center' class='style11' id='cabecera2'>Seleccione el Periodo a iniciar</div></td></tr>");
		//out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='60%'><i><div align='center'>Paciente</div></i></td><td width='30%'><i><div align='center'>Empresa</div></i></td><td width='10%'><i><div align='center'>Admisi�n</div></i></td></tr>");  //
		out.print("<table width='100%' border='0' class='style6' ><tr><td><div align='center'>A&ntilde;o: <input name='AC' type='text' id='AC' size='2' onKeyup='periodocontablen(this,patronp,true,1)'  />  Mes: <input name='MC' type='text' id='MC' size='1' onKeyup='periodocontablen(this,patronp,true,0)' /></div></td></tr>"); 
	}//fin equals 30
	
	
	if(va.equals("31")){	
		out.print("<table width='100%' border='0' class='style6' ><tr BGCOLOR='#D3D3D3'><td  colspan='3' ><div align='center'>Actualizar Saldos desde: "+pc+" de "+ac+" hasta 13 de "+ac+"  <input type='button' value='  ACTUALIZAR  ' onClick='PS2(&quot;"+ac+"&quot;,"+pc+")' ></div></td></tr>"); 
		//out.print("<tr><td id='tdboton'><input type='button' value='  Consultar  ' onClick='GuardarCheque(&quot;"+pc+"&quot;,"+ac+")' ></td></tr></table>");
		
	}//fin equals 31
	

	if(va.equals("32")){	
		
////////////////ACTUALIZAMOS A CERO TODOS LOS ACUMULADOS DESDE EL PERIODO HASTA ESE A�O///////////////
		
		md.AcumuladosSCCaCero(ac,pc);//Acumulado sucursal centro de costo a cero
		md.AcumuladosTaCero(ac,pc);//Acumulado terceros a cero
		
		//RECORRO TODAS LAS COMBINACIONES CON SALDOS EN EL PERIODO 13 Y LAS CREO CON CEROS Y SALDOS ANTERIORES Y NUEVOS 
		int acaapb=(Integer.parseInt(ac)-1);
		String acaapbn=String.valueOf(acaapb);
		System.out.println("INICIAMOS");
		rs1 = md.AcumuladosSconsaldo(acaapbn);
		try {
			while(rs1.next()){
				///VERIFICAMOS SI LA RELACION YA EXISTE EN ESTE A�O
				System.out.println("INICIAL SE VERIFICA SI EXISTE: "+rs1.getString(2)+" - "+rs1.getString(3)+" - "+rs1.getString(4)+" - "+ac);
				rs2 = md.Acumulado_Sucursal_Centrocco(rs1.getString(2),rs1.getString(3),rs1.getString(4),ac);
				int sws=0;
				String pn="";
				try {
					if(rs2.next()){
						System.out.println("SI EXISTE");
						sws=1;
						//////////////////CONSULTAMOS SI LOS DETALLES EXISTEN/////////
						for(int j=1; j<=13; j++){
						int swsp=0;
						if(j<10){pn="0"+j;}else{pn=String.valueOf(j);}
						System.out.println("SEGUNDO SE VERIFICA SI EXISTEN LOS DETALLES: "+rs2.getString(1)+" - "+pn);
						
						rs3 = md.Detalle_Acumulado_Sucursal_CentroccoPeriodoAnterior(rs2.getString(1),pn);//Consultamos el periodo 13 del a�o anterior y nos traemos el saldo nuevo
						try {
							if(rs3.next()){
								System.out.println("SI EXISTE EL DETALLE "+rs3.getString(1)+" valor:"+rs1.getString(12));
								swsp=1;
								md.ActualizarDetalle_Acumulado_Sucursal_Centrocco(rs1.getString(12),"0","0",rs1.getString(12),rs2.getString(1),pn);//Actualiza el detalle hacia abajo con el saldo anterior
							//	System.out.println("Valor saldo p13: "+rs1.getString(12));
							}
							rs3.getStatement().getConnection().close();
						} catch (SQLException e) {
							out.print("Error "+e);
							e.printStackTrace();
						}	
						//////////////////CREAMOS LOS DETALLES/////////
						if(swsp==0){
							System.out.println("NO EXISTE EL DETALLE "+rs1.getString(12));
						md.CrearDetalle_Acumulado_Sucursal_Centrocco(rs2.getString(1),pn,rs1.getString(12),"0","0",rs1.getString(12));//crea el detalle x periodo del detalle_acumulado_sucursal_centrocco
						}
						}//fin for j
						/////////////////
					}
					rs2.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				if(sws==0){
					System.out.println("NO EXISTE");
					md.CrearAcumulado_Sucursal_Centrocco(rs1.getString(2),rs1.getString(3),rs1.getString(4),ac);//Se crea la nueva relacion y se procede a crear los periodos
					
					rs2 = md.Acumulado_Sucursal_Centrocco(rs1.getString(2),rs1.getString(3),rs1.getString(4),ac);
					try {
						if(rs2.next()){
							//////////////////CREAMOS LOS DETALLES/////////
							for(int j=1; j<=13; j++){
								if(j<10){pn="0"+j;}else{pn=String.valueOf(j);}
								md.CrearDetalle_Acumulado_Sucursal_Centrocco(rs2.getString(1),pn,rs1.getString(12),"0","0",rs1.getString(12));//crea el detalle x periodo del detalle_acumulado_sucursal_centrocco
							}//fin for j
							/////////////////
						}
						rs2.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
				}
				
				
			////////////////////////
			}
			rs1.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		
		
		///////INICIAMOS CON LOS TERCEROS
		
		rs1 = md.AcumuladosTconsaldo(acaapbn);
		try {
			while(rs1.next()){
				///VERIFICAMOS SI LA RELACION YA EXISTE EN ESTE A�O
				//System.out.println("INICIAL SE VERIFICA SI EXISTE: "+rs1.getString(2)+" - "+rs1.getString(3)+" - "+ac+" - "+rs1.getString(5)+" -"+rs1.getString(6));
				
				rs2 = md.Acumulado_Terceros(rs1.getString(2),rs1.getString(3),ac,rs1.getString(5),rs1.getString(6));//Consltamos si existe esa relacion en el a�o anterior para buscar en el detalle el saldo final del periodo 13
				int sws=0;
				String pn="";
				try {
					if(rs2.next()){
						//System.out.println("SI EXISTE");
						sws=1;
						//////////////////CONSULTAMOS SI LOS DETALLES EXISTEN/////////
						for(int j=1; j<=13; j++){
						int swsp=0;
						if(j<10){pn="0"+j;}else{pn=String.valueOf(j);}
						//System.out.println("SEGUNDO SE VERIFICA SI EXISTEN LOS DETALLES: "+rs2.getString(1)+" - "+pn);
						
						rs3 = md.Detalle_Acumulado_TerceroPeriodoAnterior(rs2.getString(1),pn);//Consultamos el periodo 13 del a�o anterior y nos traemos el saldo nuevo
						try {
							if(rs3.next()){
								//System.out.println("SI EXISTE EL DETALLE "+rs3.getString(1)+" valor:"+rs1.getString(13));
								swsp=1;
								md.ActualizarDetalle_Acumulado_Tercero(rs1.getString(13),"0","0",rs1.getString(13),rs2.getString(1),pn);//Actualiza el detalle hacia abajo con el saldo anterior
								//System.out.println("Valor saldo p13: "+snp13);
							}
							rs3.getStatement().getConnection().close();
						} catch (SQLException e) {
							out.print("Error "+e);
							e.printStackTrace();
						}	
						//////////////////CREAMOS LOS DETALLES/////////
						if(swsp==0){
							//System.out.println("NO EXISTE EL DETALLE "+rs1.getString(12));
						md.CrearDetalle_Acumulado_Tercero(rs2.getString(1),pn,rs1.getString(13),"0","0",rs1.getString(13));//crea el detalle x periodo del detalle_acumulado_tercero
						}
						}//fin for j
						/////////////////
					}
					rs2.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				if(sws==0){
					//System.out.println("NO EXISTE");
					md.CrearAcumulado_Terceros(rs1.getString(2),rs1.getString(3),ac,rs1.getString(5),rs1.getString(6));//Se crea la nueva relacion y se procede a crear los periodos
					
					rs2 = md.Acumulado_Terceros(rs1.getString(2),rs1.getString(3),ac,rs1.getString(5),rs1.getString(6));//Consltamos si existe esa relacion en el a�o anterior para buscar en el detalle el saldo final del periodo 13
					try {
						if(rs2.next()){
							//////////////////CREAMOS LOS DETALLES/////////
							for(int j=1; j<=13; j++){
								if(j<10){pn="0"+j;}else{pn=String.valueOf(j);}
								md.CrearDetalle_Acumulado_Tercero(rs2.getString(1),pn,rs1.getString(13),"0","0",rs1.getString(13));//crea el detalle x periodo del detalle_acumulado_tercero
							}//fin for j
							/////////////////
						}
						rs2.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
				}
				
				
			////////////////////////
			}
			rs1.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		
		
		
		
		
/////////////////////ACUMULADO SUCURSAL////////////////////
		out.print("<table width='100%' border='0' class='style6' >");
		rs = md.ConsultarDocyDet(ac,pc);
		//String vcco="";
		try {
			while(rs.next()){
				//AQUI DEBO DE HACER TODO EL PROCESO DE ACUMULADOS
					
			////  Consultar y crear el Acumulado sucursal centro de costo /////////
				rs1 = md.CentrosySubcentrosdeCosto(rs.getString(5));
				String vcco="";
				String vscc="";
				try {
					while(rs1.next()){
						vcco=rs1.getString(1);
						vscc=rs1.getString(2);
					//	System.out.println("CentrosySubcentrosdeCosto: "+vcco+" - "+vscc);
					}
					rs1.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				
				///Verificar si la relacion a�o,cta,succntro y centrosubcentro existe
				String codasc=""; 
				rs1 = md.Acumulado_Sucursal_Centrocco(rs.getString(4),rs.getString(5),rs.getString(3),rs.getString(1));
				int sw=0;
				try {
					if(rs1.next()){
						sw=1;
						codasc=rs1.getString(1);
					}
					rs1.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				
				if(sw==0){//Si la relacion en Acumulado_sucursal_centrodecosto no existe
					md.CrearAcumulado_Sucursal_Centrocco(rs.getString(4),rs.getString(5),rs.getString(3),rs.getString(1));//Se crea la nueva relacion y se procede a crear los periodos
					rs1 = md.Acumulado_Sucursal_Centrocco(rs.getString(4),rs.getString(5),rs.getString(3),rs.getString(1));//Nos traemos el codigo de la relacion q acabamos de crear
					try {
						if(rs1.next()){
							codasc=rs1.getString(1);
						}
						rs1.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					
						int acaa=(Integer.parseInt(rs.getString(1))-1);
						String acaas=String.valueOf(acaa);
						String snp13="0";
						rs2 = md.Acumulado_Sucursal_Centrocco(rs.getString(4),rs.getString(5),rs.getString(3),acaas);//Consltamos si existe esa relacion en el a�o anterior para buscar en el detalle el saldo final del periodo 13
						//System.out.println("................."+rs.getString(4)+"...."+rs.getString(5)+"...."+rs.getString(3)+"...."+acaas);
						try {
							if(rs2.next()){
								////////////////////
								rs1 = md.Detalle_Acumulado_Sucursal_CentroccoP13(rs2.getString(1));//Consultamos el periodo 13 del a�o anterior y nos traemos el saldo nuevo
								try {
									if(rs1.next()){
										snp13=rs1.getString(1);
										//System.out.println("Valor saldo p13: "+snp13);
									}
									rs1.getStatement().getConnection().close();
								} catch (SQLException e) {
									out.print("Error "+e);
									e.printStackTrace();
								}	
								///////////////////////
								
							}
							rs2.getStatement().getConnection().close();
						} catch (SQLException e) {
							out.print("Error "+e);
							e.printStackTrace();
						}

					String pp="0";
					String dp="0";
					String cp="0";
					String snp01="";
				 	for(int j=1; j<=13; j++){
						if(j<10){pp="0"+j;}else{pp=String.valueOf(j);}
						if(rs.getString(2).equals(pp)){dp=rs.getString(7); cp=rs.getString(8);}//si periodo=j hay q cambiar el valor del debito y credito ysegun la naturaleza ejecutar la formula)
						else{dp="0"; cp="0";}
						
						String natucta=rs.getString(9).substring(0, 1);//selecciona el inicio del nombre de la cuenta para saber la naturaleza
						if(natucta.equals("1")||natucta.equals("5")||natucta.equals("6")||natucta.equals("7")||natucta.equals("8")){
							long snp01int=(Long.parseLong(snp13)+Long.parseLong(dp)-Long.parseLong(cp));
							System.out.println("ESTE ES EL RESUKTADO DE LA OPERACION DEBITO snp01int"+snp01int);
							snp01=String.valueOf(snp01int);             
						}
						if(natucta.equals("2")||natucta.equals("3")||natucta.equals("4")||natucta.equals("9")){
							long snp01int=(Long.parseLong(snp13)-Long.parseLong(dp)+Long.parseLong(cp));
							System.out.println("ESTE ES EL RESUKTADO DE LA OPERACION CREDITO snp01int"+snp01int);
							snp01=String.valueOf(snp01int);   
						}
						System.out.println("creamos el detalle "+codasc+"-"+pp+"-"+snp13+"-"+dp+"-"+cp+"-"+snp01);
						md.CrearDetalle_Acumulado_Sucursal_Centrocco(codasc,pp,snp13,dp,cp,snp01);//crea el detalle x periodo del detalle_acumulado_sucursal_centrocco
						snp13=snp01;//Al saldo anterior le asigno el saldo nuevo para el proximo periodo
				 	}//fin for j
						
				}///fin sw==0
				else{//si la relacion si existe solo tocaria actualizar
				String snp13="0";
					//System.out.println("PERIODOOO: "+pc);
					if(pc.equals("1")){
						
					int acaa=(Integer.parseInt(rs.getString(1))-1);
					String acaas=String.valueOf(acaa);
					
					//System.out.println("Relacion p13: "+rs.getString(4)+"-"+rs.getString(5)+"-"+rs.getString(3)+"-"+acaas);
					rs2 = md.Acumulado_Sucursal_Centrocco(rs.getString(4),rs.getString(5),rs.getString(3),acaas);//Consltamos si existe esa relacion en el a�o anterior para buscar en el detalle el saldo final del periodo 13
					//System.out.println("................."+rs.getString(4)+"...."+rs.getString(5)+"...."+rs.getString(3)+"...."+acaas);
					try {
						if(rs2.next()){
							////////////////////
							rs1 = md.Detalle_Acumulado_Sucursal_CentroccoP13(rs2.getString(1));//Consultamos el periodo 13 del a�o anterior y nos traemos el saldo nuevo
							try {
								if(rs1.next()){
									snp13=rs1.getString(1);
								//	System.out.println("Valor saldo p13: "+snp13);
								}
								rs1.getStatement().getConnection().close();
							} catch (SQLException e) {
								out.print("Error "+e);
								e.printStackTrace();
							}	
							///////////////////////
							
						}
						rs2.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					
				}else{
			//System.out.println("ENTRAMOS AL SINO OSEA QUE SI EXISTE LA RELACIONNNNNNNNN");
						String pnsa="";
						if((Integer.parseInt(rs.getString(2))-1)<10){pnsa="0"+String.valueOf(Integer.parseInt(rs.getString(2))-1);}else{pnsa=String.valueOf(Integer.parseInt(rs.getString(2))-1);}			
						rs1 = md.Detalle_Acumulado_Sucursal_CentroccoPeriodo(codasc,pnsa);//
						System.out.println("ERRORRRRRRRRRRRRRRRR: "+codasc+"-"+pnsa);
						try {
							if(rs1.next()){
								snp13=rs1.getString(4);//Saldo anterior
								System.out.println("SALDO ANTERIOR DEL PERIODO: "+pc+"-1: "+snp13);
							}
							rs1.getStatement().getConnection().close();
						} catch (SQLException e) {
							out.print("Error "+e);
							e.printStackTrace();
						}	
					}
					
				
					int swd=0;
					
					String sac=snp13;
					String sdc="0";
					String scc="0";
					String snc="0";
						
					rs1 = md.Detalle_Acumulado_Sucursal_CentroccoPeriodo(codasc,rs.getString(2));//
					try {
						if(rs1.next()){
							swd=1;
						//	sac=rs1.getString(1);//Saldo anterior
							sdc=rs1.getString(2);//Debito
							scc=rs1.getString(3);//Credito
							snc=rs1.getString(4);//Saldo nuevo
						}
						rs1.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}	
									
												
					//Aqui antes sumaba pero en el proceso debo reemplazar el debito o el credito
					sdc=String.valueOf(Long.parseLong(sdc)+Long.parseLong(rs.getString(7)));
					scc=String.valueOf(Long.parseLong(scc)+Long.parseLong(rs.getString(8)));
				
					String natucta=rs.getString(9).substring(0, 1);//selecciona el inicio del nombre de la cuenta para saber la naturaleza
					if(natucta.equals("1")||natucta.equals("5")||natucta.equals("6")||natucta.equals("7")||natucta.equals("8")){
						long sncint=(Long.parseLong(sac)+Long.parseLong(sdc)-Long.parseLong(scc));
						System.out.println("ESTE ES EL RESUKTADO DE LA OPERACION DEBITO sncint"+sncint);
						snc=String.valueOf(sncint);             
					}
					if(natucta.equals("2")||natucta.equals("3")||natucta.equals("4")||natucta.equals("9")){
						long sncint=(Long.parseLong(sac)-Long.parseLong(sdc)+Long.parseLong(scc));
						System.out.println("ESTE ES EL RESUKTADO DE LA OPERACION CREDITO sncint"+sncint);
						snc=String.valueOf(sncint);   
					}
					
					System.out.println("swdswdswdswdswdswdswdswdswdswdswd: "+swd);
					
					if(swd==1){
						System.out.println("1111111111Actualizamos el detalle "+sac+"-"+sdc+"-"+scc+"-"+snc+"-"+codasc+"-"+rs.getString(2));
						
						md.ActualizarDetalle_Acumulado_Sucursal_Centrocco(sac,sdc,scc,snc,codasc,rs.getString(2));//Actualiza el detalle con los valores del debito y credito y el saldo nuevo
					}else{//este sino es por si existe el acumulado pero no el detalle
						md.CrearDetalle_Acumulado_Sucursal_Centrocco(codasc,rs.getString(2),sac,sdc,scc,snc);//crea el detalle x periodo del detalle_acumulado_sucursal_centrocco
					}
					
					String pp2="";
					int inicio=Integer.parseInt(rs.getString(2))+1;
					String sac2="0";
					String sdc2="0";
					String scc2="0";
					String snc2="0";
					
					for (int x=inicio; x<14; x++){// for que me ayuda a actualizar los registros de todos los periodos hacia abajo
						System.out.println("CICLO REPETITIVO: "+x);
						if(x<10){pp2="0"+x;}else{pp2=String.valueOf(x);}
						rs1 = md.Detalle_Acumulado_Sucursal_CentroccoPeriodo(codasc,pp2);//Consultamos el periodo 13 del a�o anterior y nos traemos el saldo nuevo
						try {
							if(rs1.next()){
								//sac2=rs.getString(1);
								sdc2=rs1.getString(2);
								scc2=rs1.getString(3);
								//snc2=rs.getString(4);
							}
							rs1.getStatement().getConnection().close();
						} catch (SQLException e) {
							out.print("Error "+e);
							e.printStackTrace();
						}	
						
						String natucta2=rs.getString(9).substring(0, 1);//selecciona el inicio del nombre de la cuenta para saber la naturaleza
						if(natucta2.equals("1")||natucta2.equals("5")||natucta2.equals("6")||natucta2.equals("7")||natucta2.equals("8")){
							snc2=String.valueOf(Long.parseLong(snc)+Long.parseLong(sdc2)-Long.parseLong(scc2));             
						}
						if(natucta2.equals("2")||natucta2.equals("3")||natucta2.equals("4")||natucta2.equals("9")){
							snc2=String.valueOf(Long.parseLong(snc)-Long.parseLong(sdc2)+Long.parseLong(scc2));   
						}
						
						if(swd==1){
							System.out.println("222222Actualizamos el detalle "+snc+"-"+sdc2+"-"+scc2+"-"+snc2+"-"+codasc+"-"+pp2);
							
							md.ActualizarDetalle_Acumulado_Sucursal_Centrocco(snc,sdc2,scc2,snc2,codasc,pp2);//Actualiza el detalle hacia abajo con el saldo anterior
						}else{//este sino es por si existe el acumulado pero no el detalle
							System.out.println("SINOSINOSINMPOSIMSMSOICICLO REPETITIVO: "+x);
							md.CrearDetalle_Acumulado_Sucursal_Centrocco(codasc,pp2,snc,sdc2,scc2,snc2);//crea el detalle x periodo del detalle_acumulado_sucursal_centrocco
						}
						
					 snc=snc2;
					}//fin for <14
			}//fin del else la relacion de acumulados sucursalcentrossubcentros si existe

/////////////////////FIN ACUMULADO SUCURSAL////////////////////
		/*	*/		
//////EMPIEZA EL ACUMULADO TERCERO////////////				
				
				///Verificar si la relacion tercero,cta,a�o,succntro y centrosubcentro existe
				String codat=""; 
				rs1 = md.Acumulado_Terceros(rs.getString(6),rs.getString(3),rs.getString(1),rs.getString(4),rs.getString(5));
				int swt=0;
				try {
					if(rs1.next()){
						swt=1;
						codat=rs1.getString(1);
					}
					rs1.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				
				if(swt==0){//Si la relacion en Acumuladotercero no existe
				md.CrearAcumulado_Terceros(rs.getString(6),rs.getString(3),rs.getString(1),rs.getString(4),rs.getString(5));//Se crea la nueva relacion y se procede a crear los periodos
				rs1 = md.Acumulado_Terceros(rs.getString(6),rs.getString(3),rs.getString(1),rs.getString(4),rs.getString(5));//Nos traemos el codigo de la relacion q acabamos de crear
					try {
						if(rs1.next()){
							codat=rs1.getString(1);
						}
						rs1.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					
					int acaa=(Integer.parseInt(rs.getString(1))-1);
					String acaas=String.valueOf(acaa);
					String snp13="0";
					rs2 = md.Acumulado_Terceros(rs.getString(6),rs.getString(3),rs.getString(1),rs.getString(4),rs.getString(5));//Consltamos si existe esa relacion en el a�o anterior para buscar en el detalle el saldo final del periodo 13
					//int sw2=0;
					try {
						if(rs2.next()){
							//sw2=1;////////////////////
							rs1 = md.Detalle_Acumulado_TerceroP13(rs2.getString(1));//Consultamos el periodo 13 del a�o anterior y nos traemos el saldo nuevo
							try {
								if(rs1.next()){
									snp13=rs1.getString(1);
								}
								rs1.getStatement().getConnection().close();
							} catch (SQLException e) {
								out.print("Error "+e);
								e.printStackTrace();
							}	
							///////////////////////
							
						}
						rs2.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
				
					String pp="0";
					String dp="0";
					String cp="0";
					String snp01="";
					for(int j=1; j<=13; j++){
						if(j<10){pp="0"+j;}else{pp=String.valueOf(j);}
						if(rs.getString(2).equals(pp)){dp=rs.getString(7); cp=rs.getString(8);}//si periodo=j hay q cambiar el valor del debito y credito ysegun la naturaleza ejecutar la formula)
						else{dp="0"; cp="0";}
						
						String natucta=rs.getString(9).substring(0, 1);//selecciona el inicio del nombre de la cuenta para saber la naturaleza
						if(natucta.equals("1")||natucta.equals("5")||natucta.equals("6")||natucta.equals("7")||natucta.equals("8")){
							long snp01int=(Long.parseLong(snp13)+Long.parseLong(dp)-Long.parseLong(cp));
							System.out.println("ESTE ES EL RESUKTADO DE LA OPERACION DEBITO snp01int"+snp01int);
							snp01=String.valueOf(snp01int);             
						}
						if(natucta.equals("2")||natucta.equals("3")||natucta.equals("4")||natucta.equals("9")){
							long snp01int=(Long.parseLong(snp13)-Long.parseLong(dp)+Long.parseLong(cp));
							System.out.println("ESTE ES EL RESUKTADO DE LA OPERACION CREDITO snp01int"+snp01int);
							snp01=String.valueOf(snp01int);   
						}
						
						md.CrearDetalle_Acumulado_Tercero(codat,pp,snp13,dp,cp,snp01);//crea el detalle x periodo del detalle_acumulado_tercero
						snp13=snp01;//Al saldo anterior le asigno el saldo nuevo para el proximo periodo
				 
		 			}//fin for j	
				}///fin swt==0
				else{//si la relacion si existe solo tocaria actualizar el tercero
		
					String snp13="0";
					
					if(pc.equals("1")){
					
						int acaa=(Integer.parseInt(rs.getString(1))-1);
						String acaas=String.valueOf(acaa);
						
						rs2 = md.Acumulado_Terceros(rs.getString(6),rs.getString(3),rs.getString(1),rs.getString(4),rs.getString(5));//Consltamos si existe esa relacion en el a�o anterior para buscar en el detalle el saldo final del periodo 13
						try {
							if(rs2.next()){
								////////////////////
								rs1 = md.Detalle_Acumulado_TerceroP13(rs2.getString(1));//Consultamos el periodo 13 del a�o anterior y nos traemos el saldo nuevo
								try {
									if(rs1.next()){
										snp13=rs1.getString(1);
									}
									rs1.getStatement().getConnection().close();
								} catch (SQLException e) {
									out.print("Error "+e);
									e.printStackTrace();
								}	
								///////////////////////
							
							}
							rs2.getStatement().getConnection().close();
						} catch (SQLException e) {
							out.print("Error "+e);
							e.printStackTrace();
						}
					}else{
						String pnsa="";
						if((Integer.parseInt(rs.getString(2))-1)<10){pnsa="0"+String.valueOf(Integer.parseInt(rs.getString(2))-1);}else{pnsa=String.valueOf(Integer.parseInt(rs.getString(2))-1);}			
												
						rs1 = md.Detalle_Acumulado_Tercero(codat,pnsa);//
						try {
							if(rs1.next()){
								snp13=rs1.getString(4);//Saldo anterior
								//System.out.println("SALDO ANTERIOR DEL PERIODO: "+pc+"-1: "+snp13);
							}
							rs1.getStatement().getConnection().close();
						} catch (SQLException e) {
							out.print("Error "+e);
							e.printStackTrace();
						}	
						
					}	
					
					/*HAY QUE VERIFICAR SI NO EXISTEN LOS DETALLES Y DEBE SER CAPAZ DE CREARLOS  URGENTE
					
					LUEGO HACER LO MISMO CON LOS TERCEROS
					
					REVISAR DESCRIPCION QXk                                      
					*/
				
					int swdt=0;
					
					String sac=snp13;
					String sdc="0";
					String scc="0";
					String snc="0";
					
					rs1 = md.Detalle_Acumulado_Tercero(codat,rs.getString(2));//Consultamos el periodo 13 del a�o anterior y nos traemos el saldo nuevo
					try {
						if(rs1.next()){
							swdt=1;
							//	sac=rs1.getString(1);//Saldo anterior
							sdc=rs1.getString(2);//Debito
							scc=rs1.getString(3);//Credito
							snc=rs1.getString(4);//Saldo nuevo
						}
						rs1.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}	
					
					sdc=String.valueOf(Long.parseLong(sdc)+Long.parseLong(rs.getString(7)));
					scc=String.valueOf(Long.parseLong(scc)+Long.parseLong(rs.getString(8)));
				
					String natucta=rs.getString(9).substring(0, 1);//selecciona el inicio del nombre de la cuenta para saber la naturaleza
					if(natucta.equals("1")||natucta.equals("5")||natucta.equals("6")||natucta.equals("7")||natucta.equals("8")){
						long sncint=(Long.parseLong(sac)+Long.parseLong(sdc)-Long.parseLong(scc));
						//System.out.println("ESTE ES EL RESUKTADO DE LA OPERACION DEBITO sncint"+sncint);
						snc=String.valueOf(sncint);             
					}
					if(natucta.equals("2")||natucta.equals("3")||natucta.equals("4")||natucta.equals("9")){
						long sncint=(Long.parseLong(sac)-Long.parseLong(sdc)+Long.parseLong(scc));
						//System.out.println("ESTE ES EL RESUKTADO DE LA OPERACION CREDITO sncint"+sncint);
						snc=String.valueOf(sncint);   
					}
					
					if(swdt==1){
						md.ActualizarDetalle_Acumulado_Tercero(sac,sdc,scc,snc,codat,rs.getString(2));//Actualiza el detalle con los valores del debito y credito y el saldo nuevo
					}else{//este sino es por si existe el acumulado pero no el detalle
						md.CrearDetalle_Acumulado_Tercero(codat,rs.getString(2),sac,sdc,scc,snc);//crea el detalle x periodo del detalle_acumulado_tercero
					}
					
					
					
					
					
					
					String pp2="";
					int inicio=Integer.parseInt(rs.getString(2))+1;
					String sac2="0";
					String sdc2="0";
					String scc2="0";
					String snc2="0";
					
					for (int x=inicio; x<14; x++){// for que me ayuda a actualizar los registros de todos los periodos hacia abajo
						if(x<10){pp2="0"+x;}else{pp2=String.valueOf(x);}
						rs1 = md.Detalle_Acumulado_Tercero(codat,pp2);//Consultamos el periodo 13 del a�o anterior y nos traemos el saldo nuevo
						try {
							if(rs1.next()){
								//sac2=rs.getString(1);
								sdc2=rs1.getString(2);
								scc2=rs1.getString(3);
								//snc2=rs.getString(4);
							}
							rs1.getStatement().getConnection().close();
						} catch (SQLException e) {
							out.print("Error "+e);
							e.printStackTrace();
						}	
						
						String natucta2=rs.getString(9).substring(0, 1);//selecciona el inicio del nombre de la cuenta para saber la naturaleza
						if(natucta2.equals("1")||natucta2.equals("5")||natucta2.equals("6")||natucta2.equals("7")||natucta2.equals("8")){
							snc2=String.valueOf(Long.parseLong(snc)+Long.parseLong(sdc2)-Long.parseLong(scc2));             
						}
						if(natucta2.equals("2")||natucta2.equals("3")||natucta2.equals("4")||natucta2.equals("9")){
							snc2=String.valueOf(Long.parseLong(snc)-Long.parseLong(sdc2)+Long.parseLong(scc2));   
						}
						
						md.ActualizarDetalle_Acumulado_Tercero(snc,sdc2,scc2,snc2,codat,pp2);//Actualiza el detalle hacia abajo con el saldo anterior
						
						if(swdt==1){
							md.ActualizarDetalle_Acumulado_Tercero(snc,sdc2,scc2,snc2,codat,pp2);//Actualiza el detalle hacia abajo con el saldo anterior
						}else{//este sino es por si existe el acumulado pero no el detalle
							md.CrearDetalle_Acumulado_Tercero(codat,pp2,snc,sdc2,scc2,snc2);//crea el detalle x periodo del detalle_acumulado_tercero
						}
					
						snc=snc2;
					}//fin for <14
													
				}//fin del else la relacion de acumulados terceros si existe
					
			}
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		out.print("</table>");
		
		
		
	/*	int ln=l+1;
						
		
			}//fin del for para crear cada linea
			//FIN DEL FOR PARA CREAR CADA LINEA AQUI DEBE TERMINAR MI WHILE
			
			/////Actualiza el valor total del documento
			md.ActualizarDocumentoDebitoCredito(docu, vdebito, vcredito);
			
		
		}*/
		
		
		
	}//fin equals 32
	
///////////////////////////FIN ACTUALIZAR SALDOS///////////////////////////////
	
	
	
	
//	primero  hay que solucionar lo de hemodinamia con la jefe o del 25%
	
	
	
	
	
	
	
	
	////////////////////////////AUTOINVENTARIOS/////////////////////////////////////////
	
	
	if(va.equals("autoinv1")){//Seleccionar Cuentas
	try {
			rs =md.Cuentas(texto);
			String cadena ="";
			cadena="[";
			while(rs.next()){
				System.out.println(rs.getString(1)+"|"+rs.getString(2)+"|"+rs.getString(3));
				cadena = cadena+"'"+rs.getString(1)+"|"+rs.getString(2)+"|"+rs.getString(3)+"'";
            	cadena = cadena +",";	
			}
			cadena = cadena+"]";
			res.getWriter().write(cadena);
			System.out.println("CADENA: "+cadena);
			rs.getStatement().getConnection().close();
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
		}
	}
	
	if(va.equals("autoinv2")){//Seleccionar Cuentas
		try {
				rs =md.Terceros(texto);
				
				String cadena ="";
				cadena="[";
				while(rs.next()){
					
					cadena = cadena+"'"+rs.getString(1)+"|"+rs.getString(2)+"|"+rs.getString(3)+"'";
	            	cadena = cadena +",";	
				}
				cadena = cadena+"]";
				res.getWriter().write(cadena);
				rs.getStatement().getConnection().close();
			} catch (Exception e) {
				e.getMessage();
				e.printStackTrace();
			}
		}
	
	
///////////////////////////////CAUSACION///////////////////////////////
	
	if(va.equals("50")){//Para causar facturas
		//EGR000000001
	
		
		//System.out.println("CESAR");
		
		out.print("<table width='100%' id='enca' border='0' class='style6'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>CAUSACION DE FACTURAS</div></td></tr>");
		out.print("<tr><td  colspan='1'><div align='left'>Fecha: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type=text id='fd' size='8' readonly='readonly' value='"+fechacj+"'></div></td></tr>");
		out.print("<tr><td  width='25%'></td><td  width=25%'></td><td  width='25%'></td><td  width='25%'></td></tr>");
		out.print("<tr><td>Sucursal: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select  style='width:100px' name='sucuc' id='sucuc' onChange='vsucu(0)'><option value='0'>Seleccione</option>");
		rs = md.Sucursales();
		try {
			while(rs.next()){
				out.print("<option value="+rs.getString(1)+">"+rs.getString(1)+"</option>");
			}
			rs.getStatement().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		out.print("</select>");
		out.print("<select  style='width:200px' name='sucun' id='sucun' onChange='vsucu(1)'><option value='0'>Seleccione</option>");
		rs = md.Sucursales();
		try {
			while(rs.next()){
				out.print("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
			}
			rs.getStatement().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		out.print("</select></td>");

		out.print("<td><div id='Centrosc' >C.Costo: <select  style='width:50px' id='ccostoc' onChange='vccosto(0)'><option value='0'>Seleccione</option></select><select  style='width:170px' id='ccoston' onChange='vccosto(1)'><option value='0'>Seleccione</option></select></div></td>");//div nuevo para montar ccosto
		out.print("<td><div id='SCentrosc' >SubC.Costo: <select  style='width:50px' id='sccostoc' onChange='vsccosto(0)'><option value='0'>Seleccione</option></select><select  style='width:170px' id='sccoston' onChange='vsccosto(1)'><option value='0'>Seleccione</option></select></div></td></tr>");//div nuevo para montar ccosto
	
		
		
		out.print("<tr><td >Tercero: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp<select  style='width:100px' id='tercero' onChange='vter(0),orden()'><option value='0'>Seleccione</option>");
		rs = md.Terceros();
		try {
			while(rs.next()){
				out.print("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
			}
			rs.getStatement().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		out.print("</select>");
		out.print("<select  style='width:200px' id='terceron' onChange='vter(1),orden()'><option value='0'>Seleccione</option>");
		rs = md.Terceros();
		try {
			while(rs.next()){
				out.print("<option value="+rs.getString(1)+">"+rs.getString(3)+"</option>");
			}
			rs.getStatement().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		out.print("</select></td>");
		
		out.print("<td>Factura: <input type=text id='factu' size='31' Onkeyup='referencia(this)'></td>");//vnb(0,5)
		out.print("<td  width='23%'>Vence: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type=text id='fd' size='10' onKeyup='masca2(this,patron,true,0,0,0)' onBlur='vfd()'></td>" +
				  "<td> <div id='NoOrden'>Orden de Compra: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select  id='Norden' ><option value='0'>Seleccione</option></select></div></td></tr>");
		out.print("<tr><td colspan='4'>Descripcion: <input type=text id='desccausa' size='200'></td></tr>");//vnb(0,5)
		out.print("</table>");
		
		
		out.print("<table id='asientoprincipal' width='100%'   border='0'  ><tr><td><div id=asientosc>");
		
		
		out.print("<table id='asiento' width='100%'   border='0'  ><tr BGCOLOR='#D3D3D3' ><td width='4%'><i><div align='center'>ITEM</div></i></td><td width='20%'><i><div align='center'>CUENTA</div></i></td><td width='30%'><i><div align='center'>TERCERO</div></i></td><td width='20%'><i><div align='center'>DOC.REF</div></i></td><td width='13%'><i><div align='center'>DEBITO</div></i></td><td width='13%'><i><div align='center'>CREDITO</div></i></td></tr>");  //

		out.print("<tr>");
		out.print("<td><div id='lit'>1</div></td>");
		out.print("<td><input type=text id='cta0' style='width:100%' Onkeyup='Completa(this,dteri,event,&quot;ControlDocumentos&quot;,&quot;51&quot;,2,1)'>");
		out.print("<input type=hidden id='hcta0' style='width:100%' ></td>");
		
		out.print("<td><input type=text id='ter0' style='width:100%' Onkeyup='Completa(this,dterf,event,&quot;ControlDocumentos&quot;,&quot;52&quot;,2,2)'>");
		out.print("<input type=hidden id='hter0' style='width:100%' ></td>");
		
		out.print("<td><input type=text id='doc0' style='width:100%' title='doc0'></td>");
		out.print("<td><input type=text id='deb0' style='width:100%' value='0'  ></td>");
		out.print("<td><input type=text id='cre0' style='width:97%' value='0' onBlur='newfile(1);'></td>");
		out.print("</tr>"); 
		
		out.print("<tr>");
		out.print("<td></td><td><div id='dteri'></div></td><td><div id='dterf'></div></td><td></td><td></td><td></td>");
		out.print("</tr>");
		//out.print("<tr><td><div id='more'>CESAR JULIO CHEVEL ENAMORADO..<input type=text id='deb1' style='width:100%'></div></td></tr>");
		
		
		out.print("</table>");
		out.print("</div></td></tr></table>");
		
		out.print("<table width='100%'><tr><td><div align='Right'>Total Debito: <input type='text'  id='ttd' value='0' readonly=readonly  ></div></td></tr>");
		out.print("<tr><td><div align='Right'>Total Credito: <input type='text'  id='ttc' value='0' readonly=readonly ></div></td></tr>");
		out.print("<tr><td><div align='Right'>Diferencia: <input type='text'  id='ttdi' value='0' readonly=readonly ></div></td></tr>");
		
		out.print("<tr><td><div align='center'><input type='button'  id='botoncausa' value='  Guardar  ' disabled='disabled' onClick='Causar()' ></div></td></tr></table>");
		
			
		
		
		
	//	out.print("<div id='more2'></div>");
		
		
		/*	
		out.print("<tr><td>Froma de pago:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select style='width:200px' id='fp' onChange='formapago()'><option value='0'>Cheque</option><option value='1'>Efectivo</option></select></td>");
		out.print("<td>Banco:&nbsp;&nbsp;&nbsp;&nbsp;<select  style='width:45px' name='ban' id='ban' onChange='vbanco(0)'><option value='0'>Seleccione</option>");
		rs = md.Bancos();
		try {
			while(rs.next()){
				out.print("<option value="+rs.getString(3)+">"+rs.getString(1)+"</option>");
			}
			rs.getStatement().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		out.print("</select>");
		out.print("<select  style='width:180px' name='bann' id='bann' onChange='vbanco(1)'><option value='0'>Seleccione</option>");
		rs = md.Bancos();
		try {
			while(rs.next()){
				out.print("<option value="+rs.getString(3)+">"+rs.getString(2)+"</option>");
			}
			rs.getStatement().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		out.print("</select></td>");
		out.print("<td>Valor: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type=text id='valor0' size='31' value='0' onkeyup='format(this)'></td></tr>");//vnb(0,5)
		
		out.print("<tr><td>Cheque:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type=text id='cheq' size='32' ></td>");
		
		
		out.print("<tr></tr>");	out.print("<tr></tr>");	out.print("<tr></tr>");	out.print("<tr></tr>");
		out.print("<tr></tr>"); out.print("<tr></tr>");	out.print("<tr></tr>");	out.print("<tr></tr>");
		out.print("<tr></tr>"); out.print("<tr></tr>");	out.print("<tr></tr>");	out.print("<tr></tr>");
		out.print("<tr></tr>"); out.print("<tr></tr>");	out.print("<tr></tr>");	out.print("<tr></tr>");
		out.print("<tr></tr>"); out.print("<tr></tr>");	out.print("<tr></tr>");	out.print("<tr></tr>");
		out.print("<tr></tr>"); out.print("<tr></tr>");	out.print("<tr></tr>"); out.print("<tr></tr>");
		out.print("<tr></tr>"); out.print("<tr></tr>");	out.print("<tr></tr>");	out.print("<tr></tr>");
		out.print("<tr></tr>"); out.print("<tr></tr>");	out.print("<tr></tr>");	out.print("<tr></tr>");
		out.print("<tr></tr>"); out.print("<tr></tr>");	out.print("<tr></tr>");	out.print("<tr></tr>");
		out.print("<tr></tr>"); out.print("<tr></tr>");	out.print("<tr></tr>"); out.print("<tr></tr>");
		out.print("<tr></tr>"); out.print("<tr></tr>");	out.print("<tr></tr>"); out.print("<tr></tr>");
		//out.print("<tr><td></td></tr>");
			
		
		
		out.print("<td >Girado: &nbsp;&nbsp;<select  style='width:70px' id='tercero' onChange='vter(0)'><option value='0'>Seleccione</option>");
		rs = md.Terceros();
		try {
			while(rs.next()){
				out.print("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
			}
			rs.getStatement().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		out.print("</select>");
		out.print("<select  style='width:151px' id='terceron' onChange='vter(1)'><option value='0'>Seleccione</option>");
		rs = md.Terceros();
		try {
			while(rs.next()){
				out.print("<option value="+rs.getString(1)+">"+rs.getString(3)+"</option>");
			}
			rs.getStatement().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		out.print("</select></td>");
		//validar solo numeros
		
		out.print("<td colspan='2'>Concepto:<select  style='width:565px' id='concepto' onChange='vter(0)'><option value='0'>Seleccione</option>");
		rs = md.ConceptordeEgreso();
		try {
			while(rs.next()){
				out.print("<option title="+rs.getString(1)+" value="+rs.getString(3)+">"+rs.getString(2)+"</option>");
			}
			rs.getStatement().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		out.print("</select></tr>");
		
		
		out.print("<tr><td  colspan='3'>Detalle:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type=text id='detalle' size='142' ></td></tr>");
		
		out.print("<tr><td colspan='3'><div align='center' id='gcheque'><input type='button'  value='  Guardar  ' onClick='GuardarCheque()' ></div></td></tr></table>");
		//out.print("<tr><td id='tdboton'><input type='button' value='  Consultar  ' onClick='GuardarCheque(&quot;"+pc+"&quot;,"+ac+")' ></td></tr></table>");
		out.print("<table width='100%'  border='0' ><tr><td colspan='3'><div align='center' id='Cargando'></div></td></tr>");
	*/		
	}//50	
	
	
	if(va.equals("53")){
		System.out.println("Datos: "+Ndatos);
		
		
		
		String sc=req.getParameter("sc");
		String cs=req.getParameter("cs");
		///////////////////////////
		
		String conso="";
		String cons="";
		String consn="";
		rs = md.ConsecutivosdeCuentas("710");
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
			rs.getStatement().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}		
		
		
		md.CrearDocumento(annio,mes,"710",consn,fechacjmysql,dd,vdebito,vcredito,"0","",user,fechacjmysql,hra);
		
		int ctan=Integer.parseInt(conso)+1;
		conso=String.valueOf(ctan);
		md.ActualizarConsecutivo(conso,"710");
		
		String doc="";
		rs = md.ConsecutivoUDocumento(consn);
		try {
			if(rs.next()){
				doc=rs.getString(1);
				//out.print("<input type=hidden id='docuh'  value="+rs.getString(1)+"></td>");
			}
			rs.getStatement().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}	
		
		
		
		
		//int i1=0;
		StringTokenizer ele;  
		ele = new StringTokenizer(Ndatos,"|"); 
		//String V[] = new String[3600];
		
		while(ele.hasMoreTokens()){ 
		  // V[i1] = ele.nextToken();  
			String x=ele.nextToken();
			System.out.println(x+" / ");
			
			StringTokenizer ele2 = new StringTokenizer(x,","); 
			while(ele2.hasMoreTokens()){
				//System.out.println("Firme: "+ele2.nextToken());
				
				String ccta=ele2.nextToken();
				String cter=ele2.nextToken();
				String desc=ele2.nextToken();
				String vdeb=ele2.nextToken();
				String vcre=ele2.nextToken();
				
				md.CrearDetalleDocumento(doc,ccta,sc,cs,cter,dd,"0",desc,vdeb,vcre,"0","CAU");/// con el codigo del concepto
	
			}
			
		 //  System.out.println("V["+i1+"]:"+V[i1]);
		 //  i1++;
		}
		md.OrdenCausacion(NumOrden, doc);
		out.print(consn);
		//////////////////////////
	
	}
	

	
	
	
	
	if(va.equals("51")){//Para crear cheques consulta cuentas
	///////////////////
	String cadena ="";
	cadena="[";
	rs = md.CuentasCausa(texto);
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
	

	
	if(va.equals("52")){//Para crear cheques consulta terceros
		///////////////////
		String cadena ="";
		cadena="[";
		rs = md.CompletaTerceros(texto);
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
	
	if(va.equals("54")){
		rs=md.OrdenTerceros(ter);
		try {
			out.print("Orden de Compra: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select  style='width:100px' id='Norden' onChange='detorden()'><option value='0'>Seleccione</option>");
			while(rs.next()){
				out.print("<option value='"+rs.getString("id_orden")+"'>"+rs.getString("id_orden")+"</option>");
			}
			out.print("</select>");
			rs.getStatement().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	if(va.equals("55")){
		int ii=1;
		int deb=0;
		int cre=0;
		String naturaleza="";
		System.out.println("&NumOrden="+NumOrden+"&ter="+ter+"&factu="+factu+"&tern="+tern);
		rs=md.DetOrden(NumOrden);
		try {
			out.print("<table id='asiento' width='100%'   border='0'  ><tr BGCOLOR='#D3D3D3' ><td width='4%'><i><div align='center'>ITEM</div></i></td><td width='20%'><i><div align='center'>CUENTA</div></i></td><td width='30%'><i><div align='center'>TERCERO</div></i></td><td width='20%'><i><div align='center'>DOC.REF</div></i></td><td width='13%'><i><div align='center'>DEBITO</div></i></td><td width='13%'><i><div align='center'>CREDITO</div></i></td></tr>");  //

			while(rs.next()){
					out.print("<tr>");
					out.print("<td><label><div >"+ii+"</div></label></td>");
					if(rs.getString("subgrupo").equals("0")){
						rs1=md.CuentaGrupo(rs.getString("grupo"));
						if(rs1.next()){
							naturaleza=rs1.getString("NaturalezaCuenta");
							out.print("<td><input type=text id='cta"+ii+"' style='width:100%' Onkeyup='Completa(this,dter1,event,&quot;ControlDocumentos&quot;,&quot;51&quot;,2,1)' value='"+rs1.getString("cuenta")+"'> ");
							out.print("<input type=hidden id='hcta"+ii+"' style='width:100%' value='"+rs1.getString("cod_cuenta")+"' title='"+rs1.getString("cc.NombreCuenta")+"'></td>");
						}
						rs1.getStatement().close();
					}else{
						rs1=md.CuentaSubGrupo(rs.getString("subgrupo"));
						if(rs1.next()){
							naturaleza=rs1.getString("NaturalezaCuenta");
							out.print("<td><input type=text id='cta"+ii+"'  style='width:100%' Onkeyup='Completa(this,dter1,event,&quot;ControlDocumentos&quot;,&quot;51&quot;,2,1)' value='"+rs1.getString("Cuenta")+"'> ");
							out.print("<input type=hidden id='hcta"+ii+"' style='width:100%' value='"+rs1.getString("Cod_Cuenta")+"'></td>");
						}
						rs1.getStatement().close();
					}
					
						out.print("<td><input type=text id='ter"+ii+"' style='width:100%' Onkeyup='Completa(this,dter0,event,&quot;ControlDocumentos&quot;,&quot;52&quot;,2,2)' value='"+tern+"'> ");
						out.print("<input type=hidden id='hter"+ii+"' style='width:100%' value='"+ter+"'></td>");
					
						out.print("<td><input type=text id='doc"+ii+"' style='width:100%' title='doc0' value='"+factu+"'></td>");
						if(naturaleza.equals("Debito")){
							out.print("<td><input type=text id='deb"+ii+"' style='width:100%' value='"+(Integer.parseInt(rs.getString("det.subtotal"))+Integer.parseInt(rs.getString("det.iva")))+"' title='deb"+ii+"'></td>");
							out.print("<td><input type=text id='cre"+ii+"' style='width:97%' value='0' ></td>");
							deb=deb+((Integer.parseInt(rs.getString("det.subtotal"))+Integer.parseInt(rs.getString("det.iva"))));
						}else{
							out.print("<td><input type=text id='deb"+ii+"' style='width:100%' value='0'  ></td>");
							out.print("<td><input type=text id='cre"+ii+"' style='width:97%' value='"+(Integer.parseInt(rs.getString("det.subtotal"))+Integer.parseInt(rs.getString("det.iva")))+"' ></td>");
							cre=cre+((Integer.parseInt(rs.getString("det.subtotal"))+Integer.parseInt(rs.getString("det.iva"))));
						}
						
						
					out.print("</tr>");
					out.print("<tr>");
					out.print("<td></td><td><div id='dcta"+ii+"'></div></td><td><div id='dter"+ii+"'></div></td><td></td><td></td><td></td>");
					out.print("</tr>");
					ii++;
			}//fin while
			
			System.out.println("deb: "+deb+" cre: "+cre);
			
			out.print("<tr>");
			out.print("<td><div id='lit'>"+ii+"</div></td>");
			out.print("<td><input type=text id='cta0' style='width:100%' Onkeyup='Completa(this,dteri,event,&quot;ControlDocumentos&quot;,&quot;51&quot;,2,1)'>");
			out.print("<input type=hidden id='hcta0' style='width:100%' ></td>");
			
			out.print("<td><input type=text id='ter0' style='width:100%' Onkeyup='Completa(this,dter0,event,&quot;ControlDocumentos&quot;,&quot;52&quot;,2,2)' value='"+tern+"'> ");
			out.print("<input type=hidden id='hter0' style='width:100%' value='"+ter+"'></td>");
			
			out.print("<td><input type=text id='doc0' style='width:100%' title='doc0' value='"+factu+"'></td>");
			
			if(deb > cre){
				out.print("<td><input type=text id='deb0' style='width:100%' value='0'  ></td>");
				out.print("<td><input type=text id='cre0' style='width:97%' value='"+(deb-cre)+"' onBlur='newfile("+ii+");'></td>");
			}else{
				out.print("<td><input type=text id='deb0' style='width:100%' value='"+(cre-deb)+"'  ></td>");
				out.print("<td><input type=text id='cre0' style='width:97%' value='0' onBlur='newfile("+ii+");'></td>");
			}
			out.print("</tr>"); 
			
			out.print("<tr>");
			out.print("<td></td><td><div id='dteri'></div></td><td><div id='dterf'></div></td><td></td><td></td><td></td>");
			out.print("</tr>");
			
			out.print("</table>");
		
			rs.getStatement().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	
	
	}// Fin doPost
	
	
	public String FormatoMoneda(String valor){		
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
	
	
	/////////////////////////CONVERTIR NUMERO EN LETRAS/////////////////
	
	//public class Numero_a_Letra {

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

	
	
	
}// Fin class Control Documentos
