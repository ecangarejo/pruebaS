package aiepi_Controlador;

/*IMPORTA EL METODO Y LIBRERIAS Y CLASES GENERALES Y REQUERIDAS*/
import aiepi_metodo.MetodoConfiguracionPyP; 
import hic_metodo.MetodoMultiplePacientes;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pyp_metodo.Consultas_pyp;

/*CREO LA CLASE ControlConfiguracion QUE HEREDA DE HttpServlet*/
public class ControlConfiguracionPP extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/*INSTANCIO EN LA VARIABLE mc LA CLASE MetodoConfiguracion*/
	MetodoConfiguracionPyP mc = new MetodoConfiguracionPyP();
	Consultas_pyp CPP = new Consultas_pyp();
	MetodoMultiplePacientes mmp = new MetodoMultiplePacientes();	
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
	}
	
	@SuppressWarnings("resource")
	protected void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		String opcion = req.getParameter("op");
		String Evento = "onmouseover='cambiar(this)'"+"onmouseout='cambiar2(this)'";
		ResultSet rs = null;
		ResultSet rst = null;
		ResultSet rsi = null;
		ResultSet rso = null;
		ResultSet rsa = null;
		ResultSet rsb = null;
		String Barra = "";
		if(opcion.equals("1")){
			String tdoc = req.getParameter("tdoc");
			String ndoc = req.getParameter("ndoc");
			rst=CPP.revisarCedula(ndoc);
			rs=CPP.verificarGestante(ndoc, tdoc);
			try {
				while(rst.next()){
					if(rst.getInt(1)<1){
						if(rs.next()){
							if(rs.getString(8).equals("Masculino")){
								out.print("");
								out.print("<br/><label align='center'><FONT FACE='arial' SIZE=5 COLOR=red>No se puede realizar reporte a este paciente, genero igual a "+rs.getString(8)+"</FONT></label>");
							}else{
								out.print("<br/><table width='100%' class='style6'>");
								out.print("<tr><td colspan='7' id='cabecera2'><div align='center' class='style11'>DATOS GESTANTES PARA ENCUENSTA AIEPI GESTANTES</div></td></tr>");
								out.print("<tr><td>&nbsp;</td></tr>");
								out.print("<tr>");
								out.print("<td align='center'><input type='radio'name='opcionP'value='ecografia'onclick='VerificarOpcionParto()'/> Fecha de parto por Ecografia</td>");
								out.print("<td align='center'><input type='radio'name='opcionP'value='menstruacion'onclick='VerificarOpcionParto()'/> Fecha de parto por Menstruación</td>");
								out.print("</tr>");
								out.print("</table><br/>");
								out.print("<hr width='90%' size='2' class='style6'/>");
								out.print("<br/><div id='resultadoE'></div>");
							}
						}else{
							out.print("<br/><center><label id='est'><font face='arial'size='5'color='red'>EL paciente de cedula: "+ndoc+", no existe en la base de datos!</font></label></center>");
						}
					}else if(rst.getInt(1)>=1){
						out.print(ndoc);
					}
				}
				
				rs.getStatement().close();
				rst.getStatement().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(opcion.equals("0parto")){
			String opParto = req.getParameter("parto");
			System.out.println(opParto);
			if(opParto.equals("menstruacion")){
				out.print("<table width='100%'class='style6'><tr>");
				out.print("<td align='center'width='20%'>Fecha de la ultima ecografia:</td>");
				out.print("<td align='center'>Fecha de la ultima menstruaci&oacute;n:</td>");
				out.print("<td align='center'></td>");
				out.print("<td align='center'>Fecha Probable de Parto:</td></tr>");
				out.print("<tr><td align='center'width='20%'><input type='text'id='TxtFechaE'onfocus='Calendario(this)'readonly></td>");
				out.print("<td align='center'><input type='text'id='TxtFechaGes'onmouseover='Calendario(this)'onkeypress='Enter2()'readonly></td>");
				out.print("<td align='center'><input type='button'class='linkmenu' value='Calcular Fecha Probable de Parto' onclick='CalcularParto()'></td>");
				out.print("<td align='center'><input type='text'name='TxtFechaP'id='TxtFechaP'readonly></td>");
				out.print("</tr></table><br/>");
			}else if(opParto.equals("ecografia")){
				out.print("<table width='100%'class='style6'border='0'><tr>");
				out.print("<td align='center'width='20%'>Fecha de la ultima ecografia:</td>");
				out.print("<td align='center'>Tiempo de gestaciòn por ecografia:</td>");
				out.print("<td align='center'>Calcular fecha de parto</td>");
				out.print("<td align='center'width='20%'>Fecha Probable de Parto por ecografia:</td></tr>");
				////////////////////////////////////////////////////////////////////
				out.print("<tr><td align='center'><input type='text'id='TxtFechaE'onfocus='Calendario(this)'readonly></td>");
				out.print("<td align='center'>Nùmero de semanas:&nbsp;<input type='text'id='TxtSem'size='5'onkeyup='numSem(this)'onkeypress='Enter3()'></td>");
				out.print("<td align='center'><input type='button'class='linkmenu'value='Calcular Fecha de Parto'onclick='CalcularPartoEco()'></td>");
				out.print("<td align='center'><input type='text'id='TxtFechaP'readonly></td></tr>");
				out.print("</tr></table>");
				out.print("<input type='hidden'id='TxtFechaGes'readonly></br>");
			}
		}
		
		if(opcion.equals("2")){
			String Trim = req.getParameter("trim");
			String nDoc = req.getParameter("ndoc");
			String tDoc = req.getParameter("tdoc");
			String CodPac = "";
			rs=CPP.devCodPac(nDoc, tDoc);
			if(Trim.equals("Trimestre 1")){
				Barra = "PRIMER TRIMESTRE";
			}else if(Trim.equals("Trimestre 2")){
				Barra = "SEGUNDO TRIMESTRE";
			}else if(Trim.equals("Trimestre 3")){
				Barra = "TERCER TRIMESTRE";
			}
			try {
				while(rs.next()){
					CodPac = rs.getString(1);
					System.out.println(CodPac);
					out.print("<div id='page'><input type='hidden'id='TxtCodPac'value='"+CodPac+"'readonly>");
					out.print("<table width='100%' class='style6'align='center'>"+
							"<tr><td colspan='7' id='cabecera2'><div align='center' class='style11'>"+Barra+" (Informacion Personal)</div></td></tr>"+
							"<tr><td align='left'colspan='4'> Nivel Educativo </td></tr>"+
							"<tr><td align='center'><span class='Estilo8'>Analfabeto:</span></td>"+
							"<td>" +
							"<label>" +
							"<div id='capa1'>"+
							"Si <input type='radio'name='RadAn1'value='Si'id='1a'onclick='desRadBut()'class='rbSi'/>"+
							"No <input type='radio'name='RadAn1'value='No'id='2a'/class='rbNo'><span class='style8'>*</span></div></label></td>" +
							"<td align='center'><span class='Estilo8'>Tecnico o Tecnologo:</span></td>" +
							"<td>" +
							"<label>"+
				      		"<div id='capa2'>"+
							"Si <input type='radio'name='RadBac1' value='Si' id='3a'class='rbSi'/>"+
							"No <input type='radio'name='RadBac1' value='No' id='4a'class='rbNo'/>"+
				      		"<span class='style8'>*</span></div></label></td></tr>");
					out.print("<tr><td align='center'><span class='Estilo8'>Primaria Completa:</span></td>"+
							"<td>" +
							"<label>" +
							"<div id='capa3'>"+
							"Si <input type='radio'name='RadPri1' value='Si' id='5a'class='rbSi'/>"+
							"No <input type='radio'name='RadPri1' value='No' id='6a'class='rbNo'/><span class='style8'>*</span></div></label></td>" +
							"<td align='center'><span class='Estilo8'>Educacion no Formal:</span></td>" +					
							"<td>" +
							"<label>"+
				      		"<div id='capa4'>"+
							"Si <input type='radio' name='RadNof1' value='Si' id='7a'class='rbSi'/>"+
							"No <input type='radio' name='RadNof1' value='No' id='8a'class='rbNo'/>"+
				      		"<span class='style8'>*</span></div></label></td></tr>");
					out.print("<tr><td align='center'><span class='Estilo8'>Secundaria Completa:</span></td>"+
							"<td>" +
							"<label>" +
							"<div id='capa5'>"+
							"Si <input type='radio'name='RadSec1' value='Si' id='1b'class='rbSi'/>"+
							"No <input type='radio'name='RadSec1' value='No' id='2b'class='rbNo'/><span class='style8'>*</span></label></td>" +
							"</div>"+
							"<td align='center'><span class='Estilo8'>Universitario:</span></td>" +
							"<td>"+
							"<label>"+
				      		"<div id='capa6'>"+
							"Si <input type='radio' name='RadUni1' value='Si' id='3b'class='rbSi'/>"+
							"No <input type='radio' name='RadUni1' value='No' id='4b'class='rbNo'/>"+
				      		"<span class='style8'>*</span></label></div></td></tr>"+
				      		"</table>");
					out.print("<br/><hr width='90%' size='2' align='center' class='style6'/>"+
							"<table width='100%' class='style6'>"+
							"<tr><td align='left'colspan='4'> Convivientes </td></tr>"+
							"<tr><td colspan='4'><center><label><span class='Estilo8'>Solo:</span>"+
							"<input type='checkbox'id='CheckSol'name='CheckSolo' value='Si'onclick='marcar(this)'/></label></center></td></tr>"+
							"</table>");
					out.print("<table width='100%' class='style6'>"+
							"<tr>"+
							"<td align='right'><span class='Estilo8'>Hijos:</span></td>"+
							"<td align='center'>"+
							"<label>"+
							"<div id='capa7'>"+
							"Si <input type='radio' name='RadCon1' value='Si'id='1'onclick='RbChec(this.name,sel1);Solo()'class='rbconv rbConSi'/>"+
							"No <input type='radio' name='RadCon1' value='No'id='2'onclick='RbChec(this.name,sel1);Solo()'class='rbConNo'/>"+
							"&nbsp;&nbsp;&nbsp;&nbsp;Nº <select name='sel1' id='select1'class='selconv'>"+
							"<option selected='selected' value='1'>1</option>"+
							"<option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option></select>"+
							"<span class='style8'>*</span>"+
							"</div>"+
							"</label>"+
							"</td>"+
							"<td align='right'><span class='Estilo8'>Nietos:</span></td>"+
							"<td align='center'>"+
							"<label>"+
							"<div id='capa8'>"+
							"Si <input type='radio'name='RadCon2'value='Si' id='3' onclick='RbChec(this.name,select2);Solo()'class='rbconv rbConSi'/>"+
							"No <input type='radio'name='RadCon2'value='No' id='4' onclick='RbChec(this.name,select2);Solo()'class='rbConNo'/>"+
							"&nbsp;&nbsp;&nbsp;&nbsp;Nº <select name='select2'id='select2'class='selconv'>"+
							"<option selected='selected' value='1'>1</option>"+
							"<option value='2' >2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option></select>"+
							"<span class='style8'>*</span>"+
							"</div>"+
							"</label>"+
							"</td>"+
							"</tr>");
					out.print("<tr>"+
							"<td align='right'><span class='Estilo8'>Padres:</span></td>"+
							"<td align='center'>"+
							"<label>"+
							"<div id='capa9'>"+
							"Si <input type='radio' name='RadCon3' value='Si' id='5' onclick='RbChec(this.name,select3);Solo()'class='rbconv rbConSi'/>"+
							"No <input type='radio' name='RadCon3' value='No' id='6' onclick='RbChec(this.name,select3);Solo()'class='rbConNo'/>"+					
							"&nbsp;&nbsp;&nbsp;&nbsp;Nº <select name='select3'id='select3'class='selconv'>"+
							"<option selected='selected'value='1'>1</option>"+
							"<option value='2'>2</option><option value='3'>3</option><option value='4'>4</option></select>"+
							"<span class='style8'>*</span>"+
							"</div>"+
							"</label>"+
							"</td>"+
							"<td align='right'><span class='Estilo8'>Conyugue:</span></td>"+
							"<td align='center'>"+
							"<label>"+
							"<div id='capa10'>"+
							"Si <input type='radio'name='RadCon4'value='Si' id='7' onclick='RbChec(this.name,select4);Solo()'class='rbconv rbConSi'/>"+
							"No <input type='radio'name='RadCon4'value='No' id='8' onclick='RbChec(this.name,select4);Solo()'class='rbConNo'/>"+					
							"&nbsp;&nbsp;&nbsp;&nbsp;Nº <select name='select4'id='select4'class='selconv'>"+
							"<option selected='selected'value='1'class='selconv'>1</option>"+
							"<option value='2'>2</option><option value='3'>3</option><option value='4'>4</option></select>"+
							"<span class='style8'>*</span>"+
							"</div>"+
							"</label>"+
							"</td>"+
							"</tr>"+
							"</table><br/>");
					out.print("<div align='center'><input type='button'class='boton4' value='Guardar' onclick='GuardarGestante1()'></div></div>");
				}
				rs.getStatement().close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(opcion.equals("3")){
			String CedPaciente = req.getParameter("txtcedpac");
			rs=CPP.revisarCedula(CedPaciente);
			try {
				while(rs.next()){
					if(rs.getInt(1)<1){
						CedPaciente = req.getParameter("txtcedpac");
						String fechaCon = req.getParameter("txtfecmen");
						String tipoGes = req.getParameter("tipoGes");
						String numHijo = req.getParameter("selhijo");
						String numPadre= req.getParameter("selpadre");
						String numNieto= req.getParameter("selnieto");
						String numConyugue= req.getParameter("selcon");
						String FechaEnc= req.getParameter("fechaEn");
						String FechaParto= req.getParameter("txtfechaP");
						String Analfabeta= req.getParameter("rbana");
						String primCompleta= req.getParameter("rbpri");
						String secCompleta= req.getParameter("rbsec");
						String bacCompleto= req.getParameter("rbbac");
						String noFormal= req.getParameter("rbedf");
						String Universitario= req.getParameter("rbuni");
						String convSolo= req.getParameter("chsolo");
						String convHijos= req.getParameter("rbhijo");
						String convPadres= req.getParameter("rbpadre");
						String convNietos= req.getParameter("rbnieto");
						String convConyugue= req.getParameter("rbcon");				
						String TrimAc= req.getParameter("trim");
						String TipoDoc= req.getParameter("tipdoc");
						String CodPac= req.getParameter("codpac");
						String CodUser= req.getParameter("coduser");
						String fecEco= req.getParameter("fecEco");
						CPP.guardarEncGestante1(numHijo, numPadre, numNieto, numConyugue, FechaEnc, 
								FechaParto, Analfabeta, primCompleta, TrimAc, secCompleta, bacCompleto, 
								noFormal, Universitario, convSolo, convHijos, convPadres, convNietos, 
								convConyugue, CedPaciente,fechaCon,tipoGes,CodPac,TipoDoc,CodUser,fecEco);
					}else if(rs.getInt(1)>=1){
						String cedPac = "";
						rs=CPP.devCedula(CedPaciente);
						try{
							while(rs.next()){
								cedPac = rs.getString(1);
								out.print(cedPac);
							}
							rs.getStatement().close();
						}catch(SQLException e1){
							e1.getMessage();
						}
					}
				}
				rs.getStatement().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(opcion.equals("4")){
			String Nombre="",EstadoC="",Edad="",TipoAfi="",Nivel="",Dir="",Men="",Parto="",CodPac="",CodAdm="",Fecha="",Cod="",TipG="";
			String tced = req.getParameter("tced");
			String nced = req.getParameter("nced");
			String fecha = req.getParameter("fecha");
			rs=CPP.cargarDatos(tced, nced);
			try{
				while(rs.next()){
					System.out.println(rs.getString(1));
					Nombre = rs.getString(1);
					EstadoC = rs.getString(2);
					Edad = rs.getString(3);
					TipoAfi = rs.getString(4);
					Nivel = rs.getString(5);
					Dir = rs.getString(6);
					Men = rs.getString(7);
					Parto = rs.getString(8);
					CodPac = rs.getString(9);
					CodAdm = rs.getString(10);
					Fecha = rs.getString(11);
					Cod = rs.getString(12);
					TipG = rs.getString(13);
					out.print("<table width='100%' class='style6'>");
					out.print("<tr><td>Nombre Completo:<br/>");
					out.print("&nbsp;&nbsp;<input type='text' style='border:0px' id='TxtNomPac' value='"+Nombre+"'size='30' readonly>");
					out.print("</td></tr>");
					out.print("<tr><td>Edad de la Paciente:<br/>");
					out.print("&nbsp;&nbsp;<input type='text' style='border:0px' id='TxtEdad' value='"+Edad+"' readonly>");
					out.print("</td></tr>");
					out.print("<tr><td>Fecha Posible de Parto:<br/>");
					out.print("&nbsp;&nbsp;<input type='text' style='border:0px' id='FecPar' value='"+Parto+"' readonly>");
					out.print("</td></tr>");
					rsb=CPP.DateDif(Cod, fecha);
					if(rsb.next()){
						int Valor = rsb.getInt(1);
						int Semanas = (int) Math.floor(Valor/7);
						int Dias = Valor%7;
						out.print("<tr><td>Tiempo de Gestaci&oacuten por "+TipG+":<br/>");
						out.print("&nbsp;&nbsp;<input type='text'id='tipGes'style='border:0px'id='TxtDir'value='"+Semanas+" Semanas con "+Dias+" dias'readonly>");
					}
					rsb.getStatement().close();
					out.print("</td></tr>");
					out.print("<tr><td>Tipo Afiliaci&oacuten: ");
					out.print("&nbsp;&nbsp;<input type='text' style='border:0px' id='TxtTipAfi' value='"+TipoAfi+"' readonly>");
					out.print("</td></tr>");
					out.print("<tr><td>Nivel Cotizante: ");
					out.print("&nbsp;&nbsp;<input type='text' style='border:0px' id='TxtNivelC' value='"+Nivel+"' readonly>");
					out.print("</td></tr>");
					out.print("<tr><td>Estado Civil:</br>");
					out.print("&nbsp;&nbsp;<input type='text' style='border:0px' id='TxtEstCiv' value='"+EstadoC+"' readonly>");
					out.print("</td></tr>");
					out.print("<tr><td>Direcci&oacuten:<br/>");
					out.print("&nbsp;&nbsp;<input type='text' style='border:0px' id='TxtDir' value='"+Dir+"'size='35' readonly>");
					out.print("</td></tr>");
					out.print("<tr><td>");
					out.print("<input type='hidden' id='TxtFechaM' value='"+Men+"' readonly>");
					out.print("</td></tr>");
					out.print("<tr><td>");
					out.print("<input type='hidden' id='TxtCodPac' value='"+CodPac+"' readonly>");
					out.print("</td></tr>");
					out.print("<tr><td>");
					out.print("<input type='hidden' id='TxtCodAdm' value='"+CodAdm+"' readonly>");
					out.print("</td></tr>");
					out.print("<tr><td>");
					out.print("<input type='hidden' id='TxtFecha' value='"+Fecha+"' readonly>");
					out.print("</td></tr>");
					out.print("<tr><td>");
					out.print("<input type='hidden' id='TxtCodInt' value='"+Cod+"' readonly>");
					out.print("</td></tr>");
					rsb=CPP.DateDif2(CodPac);
					if(rsb.next()){
						int Valor = rsb.getInt(1);
						out.print("<tr><td>");
						out.print("<input type='hidden' id='TxtDias' value='"+Valor+"' readonly>");
						out.print("</td></tr>");
					}
					rsb.getStatement().close();
					out.print("</table><br/>");
				}
				rs.getStatement().close();
			}catch(SQLException e1){
				e1.getMessage();
			}
		}
		if(opcion.equals("5")){
			String etapa = req.getParameter("etapa");
			String numTrim = req.getParameter("trim");
			String numT = "";
			if(numTrim.equals("Primer Trimestre")){
				numT = "1";
			}else if(numTrim.equals("Segundo Trimestre")){
				numT = "2";
			}else{
				numT = "3";
			}
			try {
				////////////////////////////////////////
				//Cabecera A
				////////////////////////////////////////
				out.print("<table width='100%' class='style6' bgcolor='#D3F0FF' border='0'><tr><td width='45%'>");
				out.print("&nbsp;&nbsp;<input type='radio' name='Exp' id='RaExp1' onclick='abrirCabezeras()'checked>");//abrirCabezeras()
				out.print("A-HISTORIA REPRODUCTIVA</td><td width='55%'align='center'>N°</td></tr></table><br/>");
				out.print("<div id='contenido1'><table border='0'width='100%'>");
				rs=CPP.cargarPreguntas(etapa);
				while(rs.next()){
					if(rs.getString(2).equals("r2_seleccion")){
						String CodPreg=rs.getString(4);
						String Preg=rs.getString(1);
						int Cont = 0;
						out.print("<tr><td align='center'width='25%'><label value='"+CodPreg+"'id='La1'>"+Preg+":&nbsp;</label></td>");
						out.print("<td><label onclick='abrirdiv(SelMul0)'class='style6 simulalink'><span class='style8'>*</span>Seleccione...</label>" +
								"&nbsp;&nbsp;<span style='display:none'id='SelA"+Cont+"'>(x)</span>" +
								"<div id='SelMul0'style='display:none'>");
						rst=CPP.cargarRespuestas(etapa);
						out.print("<table border='0'width='80%'id='tab1'>");
						while(rst.next()){
							String Det=rst.getString(3);
							String Resp=rst.getString(1);
							String CodResp=rst.getString(4);
							if(rst.getString(2).equals("r2_seleccion")){
								Cont++;
								out.print("<tr><td>" +
											"<input type='radio'id='rbA"+Cont+"'name='radioA'value='"+Det+"'class='"+CodResp+"'onclick='abrirRB(SelMul0);SumarA();ColorRB(SelA0)'/>" +
											"<label id='TrbA"+Cont+"'>"+Resp+"</label></td>");
								out.print("<td width='35%'><input type='text'name='NumTxt'id='TxtrbA"+Cont+"'onchange='GuardarA1()'onkeyup='numMenor(this)'disabled='true'onblur='CajaRaTe("+Cont+")'/></td></tr>");
							}
						}
						out.print("</td></tr></table>");
						rst.getStatement().close();
						out.print("</div></td>");
						out.print("<tr><td>&nbsp;</td></tr>");
					}
					out.print("</table>");
					out.print("<table border='0'width='100%'>");
					if(rs.getString(2).equals("r3_multi_seleccion1")){
						String CodPreg=rs.getString(4);
						String Preg=rs.getString(1);
						int Cont = 0;
						out.print("<tr><td align='center'width='25%'><label value='"+CodPreg+"'class='clasA1'id='La2'>"+Preg+":&nbsp;</label></td>");
						out.print("<td><label onclick='abrirdiv(SelMul)'class='style6 simulalink'><span class='style8'>*</span>Seleccione...</label><div id='SelMul'style='display:none'>");
						rst=CPP.cargarRespuestas(etapa);
						out.print("<table border='0'width='80%'id='tab2'>");
						while(rst.next()){
							Cont++;
							String Det=rst.getString(3);
							String Resp=rst.getString(1);
							String CodResp=rst.getString(4);
							if(rst.getString(2).equals("r3_multi_seleccionNul")){
								out.print("<tr><td><input type='checkbox'id='RbA"+Cont+"'name='checkA'value='"+Det+"'onclick='SumarA();Nulipara(RbA"+Cont+");GuardarAN(this.name)'class='"+CodResp+"'/><label id='TRbA"+Cont+"'>"+Resp+"</label></td>");
								out.print("<td><input type='hidden'name='NumTxt'id='TxtRbA"+Cont+"'disabled='true'class='NumTxt'/></td></tr>");
							}
							if(rst.getString(2).equals("r3_multi_seleccion1")){
								if(CodResp.equals("A-par2")){
									out.print("<tr "+Evento+"><td><input type='checkbox'id='RbA"+Cont+"'name='checkA2'value='"+Det+"'onclick='validarCT1("+Cont+");SumarA();abrirRB(SelMul)'class='"+CodResp+"'/><label id='TRbA"+Cont+"'>"+Resp+"</label></td>");
									out.print("<td width='35%'><input type='text'name='checkA2'id='TxtRbA"+Cont+"'onkeyup='soloNum1(this)'disabled='true'class='NumTxt nulipara'onblur='validarChTe("+Cont+")'onchange='GuardarA2(this.name)'/>");
									out.print("<span style='display:none'id='xrbS"+Cont+"'>(x)</span></td></tr>");
								}
								if(CodResp.equals("A-par3")){
									out.print("<tr "+Evento+"><td><input type='checkbox'id='RbA"+Cont+"'name='checkA3'value='"+Det+"'onclick='validarCT1("+Cont+");SumarA();abrirRB(SelMul)'class='"+CodResp+"'/><label id='TRbA"+Cont+"'>"+Resp+"</label></td>");
									out.print("<td width='35%'><input type='text'name='checkA3'id='TxtRbA"+Cont+"'onkeyup='soloNum2(this)'disabled='true'class='NumTxt nulipara'onblur='validarChTe("+Cont+")'onchange='GuardarA2(this.name)'/>");
									out.print("<span style='display:none'id='xrbS"+Cont+"'>(x)</span></td></tr>");
								}
								if(CodResp.equals("A-par4")){
									out.print("<tr "+Evento+"><td><input type='checkbox'id='RbA"+Cont+"'name='checkA4'value='"+Det+"'onclick='validarCT1("+Cont+");SumarA();abrirRB(SelMul)'class='"+CodResp+"'/><label id='TRbA"+Cont+"'>"+Resp+"</label></td>");
									out.print("<td width='35%'><input type='text'name='checkA4'id='TxtRbA"+Cont+"'onkeyup='soloNum3(this)'disabled='true'class='NumTxt nulipara'onblur='validarChTe("+Cont+")'onchange='GuardarA2(this.name)'/>");
									out.print("<span style='display:none'id='xrbS"+Cont+"'>(x)</span></td></tr>");
								}
							}
						}
						out.print("</table>");
						rst.getStatement().close();
						out.print("</div></td>");
						out.print("<tr><td>&nbsp;</td></tr>");
					}
					if(rs.getString(2).equals("r3_multi_seleccion2")){
						String CodPreg=rs.getString(4);
						String Preg=rs.getString(1);
						int Cont = 0;
						out.print("<tr><td align='center'width='25%'><label value='"+CodPreg+"'class='clasA1'id='La3'>"+Preg+":&nbsp;</label></td>");
						out.print("<td><label onclick='abrirdiv(SelMul2)'class='style6 simulalink'><span class='style8'>*</span>Seleccione...</label><div id='SelMul2'style='display:none'>");
						rst=CPP.cargarRespuestas(etapa);
						out.print("<table border='0'width='80%'id='tab3'>");
						while(rst.next()){
							Cont++;
							String Det=rst.getString(3);
							String Resp=rst.getString(1);
							String CodResp=rst.getString(4);
							if(rst.getString(2).equals("r3_multi_seleccion2")){
								out.print("<tr "+Evento+"><td><input type='checkbox'id='ChA"+Cont+"'name='checkA5'value='"+Det+"'onclick='SumarA();validarCT2("+Cont+")'class='"+CodResp+"'/><label id='TChA"+Cont+"'>"+Resp+"</label></td>");
								out.print("<td width='35%'><input type='text'name='checkA5'id='TxtChA"+Cont+"'onkeyup='numMenor(this)'disabled='true'class='NumTxt'onblur='validarChTe2("+Cont+")'onchange='GuardarA3(this.name)'/></td></tr>");
							}
						}
						out.print("</table>");
						rst.getStatement().close();
						out.print("</div></td>");
						out.print("<tr><td>&nbsp;</td></tr>");
						out.print("<tr><td align='center'colspan='3'><hr width='90%' size='2' class='style6'/></td></tr></table>");
						out.print("<table border='0'width='100%'><tr><td align='right'width='25%'>Subtotal Trimestre: </td>");
						out.print("<td>&nbsp;&nbsp;&nbsp;<input type='text'style='border:0px'id='TxtSubTotal'readonly></td><tr/>");
						out.print("<tr><td>&nbsp;</td></tr>");
					}				
				}				
				rs.getStatement().close();
				out.print("</tr></table></div>");
				////////////////////////////////////////				
				//Cabecera B
				////////////////////////////////////////
				rs=CPP.cargarPreguntas(etapa);
				while(rs.next()){
					int Cont = 0;
					out.print("<table width='100%' class='style6' bgcolor='#D3F0FF' border='0'><tr><td width='50%'>");
					out.print("&nbsp;&nbsp;<input type='radio' name='Exp' id='RaExp2' onclick='abrirCabezeras()'>");//abrirCabezeras();
					out.print("B-CONDICIONES ASOCIADAS</td><td align='center'>"+numTrim+"</td></tr></table><br/>");
					out.print("<div id='contenido2'style='display:none'><table width='100%' border='0'id='tabla2'>");
					while(rs.next()){
						Cont++;
						String CodPreg=rs.getString(4);
						String Det=rs.getString(3);
						String Preg=rs.getString(1);
						if(rs.getString(2).equals("r3_multi_seleccion3")){
							out.print("<tr "+Evento+"><td width='20%'>&nbsp;</td>");
							out.print("<td width='30%'><label value='"+CodPreg+"'class='clasB'>"+Preg+":&nbsp;</label></td>");
							out.print("<td align='center'><input type='checkbox'name='checkB'id='CbB"+Cont+"'value='"+Det+"'onclick='SumarPuntajesB("+Cont+")'></td></tr>");
						}
					}
					out.print("<tr><td>&nbsp;</td></tr>");
					out.print("<tr><td align='center'colspan='3'><hr width='90%' size='2' class='style6'/></td></tr>");
					out.print("<tr><td align='center'><label>Subtotal Trimestre:</label></td>");
					out.print("<td><input type='text'id='TxtSubTotal2'style='border:0px'size='3'readonly></td><tr/>");
					out.print("<tr><td>&nbsp;</td></tr>");
				}
				rs.getStatement().close();
				out.print("</tr></table></div>");
				////////////////////////////////////////
				//Cabecera C
				////////////////////////////////////////
				rs=CPP.cargarPreguntas(etapa);
				while(rs.next()){
					out.print("<table width='100%' class='style6' bgcolor='#D3F0FF' border='0'><tr><td width='50%'>");
					out.print("&nbsp;&nbsp;<input type='radio' name='Exp' id='RaExp3' onclick='abrirCabezeras()'>");//abrirCabezeras();
					out.print("C-EMBARAZO ACTUAL</td><td align='center'>"+numTrim+"</td></tr></table><br/>");
					out.print("<div id='contenido3'style='display:none'><table width='100%' border='0'id='tabla3'>");
					int Cont=0;
					while(rs.next()){
						Cont++;
						String Preg=rs.getString(1);
						String Det=rs.getString(3);
						String CodPreg=rs.getString(4);
						if(rs.getString(2).equals("r3_multi_seleccion4")){
							out.print("<tr "+Evento+"><td width='20%'>&nbsp;</td>");
							out.print("<td width='30%'><label value='"+CodPreg+"'class='clasC'>"+Preg+":&nbsp;</label></td>");
							out.print("<td align='center'><input type='checkbox'name='checkC'id='CbC"+Cont+"'value='"+Det+"'onclick='SumarPuntajesC("+Cont+")'></td></tr>");
						}
					}
					out.print("<tr><td>&nbsp;</td></tr>");
					out.print("<tr><td align='center'colspan='3'><hr width='90%' size='2' class='style6'/></td></tr>");
					out.print("<tr><td align='center'><label>Subtotal Trimestre:</label></td>");
					out.print("<td><input type='text'style='border:0px'id='TxtSubTotal3'size='3'readonly></td><tr/>");
					out.print("<tr><td>&nbsp;</td></tr>");					
				}
				rs.getStatement().close();
				out.print("</tr></table></div>");				
				////////////////////////////////////////
				//Cabecera D
				////////////////////////////////////////				
				rso=CPP.cargarPreguntas2(etapa);
				rsa=CPP.cargarRespuestas2(etapa);
				out.print("<table width='100%' class='style6' bgcolor='#D3F0FF' border='0'><tr><td>");
				out.print("&nbsp;&nbsp;<input type='radio'name='Exp'id='RaExp4'onclick='abrirCabezeras()'>");//abrirCabezeras();
				out.print("D-RIESGO BIOPSICOSOCIAL</td></tr></table><br/>");
				out.print("<div id='contenido4'style='display:none'><table width='100%'border='0'id='tabla4'align='center'>");				
				int Cont=0;
				while((rso.next())&&(rsa.next())){
					Cont++;
					String Resp=rsa.getString(1);
					String CodResp=rsa.getString(4);
					String Preg=rso.getString(1);
					String CodPreg=rso.getString(4);
					out.print("<tr "+Evento+"><td class='style6'>&nbsp;&nbsp;<label id='"+CodPreg+"'class='clasD1'>"+Preg+"</label></td>");
					out.print("<td align='center'><label id='"+CodResp+"'class='clasD2'>"+Resp+"</label></td>");
					out.print("<td align='center'>Ausente<input type='radio'id='RbAus"+Cont+"'name='Rb"+Cont+"'value='Ausente'class='ClasAus'onclick='SumarPunRBD();ColorRB(xRbInt"+Cont+")'>");
					out.print("Intenso<input type='radio'id='RbInt"+Cont+"'name='Rb"+Cont+"'value='Intenso'onclick='SumarPunRBD();ColorRB(xRbInt"+Cont+")'class='ClasInt'><span style='display:none'id='xRbInt"+Cont+"'>(x)</span></td></tr>");
				}
				out.print("<tr><td>&nbsp;</td></tr>");
				out.print("<tr><td align='center'>Subtotal Trimestre:<input type='text'id='TxtSubTotal4'size='1'class='inputcentrado'style='border:0px'readonly></td></tr>");
				out.print("</td></tr></table>");
				rso.getStatement().close();
				rsa.getStatement().close();	
				////////////////////////////////////////
				//Items
				////////////////////////////////////////				
				rs=CPP.cargarPreguntas3(etapa);
				rsa=CPP.cargarRespuestas3(etapa);
				rsi=CPP.cargarItems();
				out.print("<hr width='90%' size='2' class='style6'/>");
				out.print("<table width='100%'border='0'>");
				if((rs.next())&&(rsa.next())){
					String Preg = rs.getString(1);
					String CodPreg=rs.getString(4);
					String Resp = rsa.getString(1);
					String CodResp = rsa.getString(4);
					out.print("<tr><td class='style6'>&nbsp;&nbsp;<label id='"+CodPreg+"'class='clasD3'>"+Preg+"</label><br/><br/><p></td>");
					out.print("<td align='center'><label id='"+CodResp+"'class='clasD4'>"+Resp+"</label><br/><br/><p></td><td><table border='0'width='100%'><tr>");						
					int Can = 0;
					while(rsi.next()){
						Can++;
						if(rsi.getString(2).equals("r2_seleccion1")){
							String Item = rsi.getString(1);
							String CodItem = rsi.getString(3);
							out.print("<td align='center'><label id='"+CodItem+"'class='clasD5'>"+Item+"</label></td>");
							out.print("<td><select id='sel"+Can+"'name='selectB'onchange='SumarPuntajesDD(sel"+Can+")'class='selB'>");//"+Can+"
							out.print("<option value='0'>Casi Siempre</option>");
							out.print("<option value='0'>Aveces</option>");
							out.print("<option value='5'>Nunca</option>");
							out.print("</select></td></tr>");
						}
					}
					out.print("</table>");
				}				
				out.print("</td></tr>");
				out.print("<tr><td align='center'>Subtotal Trimestre:</td><td><input type='text'id='T5'size='1'class='inputcentrado'style='border:0px'readonly>" +
						"<input type='hidden'id='T6'size='1'readonly></td></tr>");
				out.print("<tr><td>&nbsp;</td></tr>");
				out.print("</table><br/></div>");
				////////////////////////////////////////
				//Tabla para resultados
				////////////////////////////////////////
				out.print("<table width='100%' border='0'><tr><td id='cabecera1'>");
				out.print("<div align='center' class='style6'>VALORACI&Oacute;N DEL RIESGO BIOPSICOSOCIAL</div></td></tr>");
				out.print("<tr><td>&nbsp;</td></tr></table><table bordercolor='#0B3861'border='1'align='center'width='95%'><tr><td><table align='center'width='95%'>");
				out.print("<tr><td>&nbsp;</td></tr>");
				out.print("<tr><td align='center'>1 Trimestre  Bajo < 3</td><td align='center'>2 Trimestre  Bajo < 3</td><td align='center'>3 Trimestre  Bajo < 3</td></tr>");
				out.print("<tr><td>&nbsp;</td><td></td><td></td></tr>");
				out.print("<td align='center'>A + B + C + D = Alto > 3<br/>Total: <input type='text'id='Tot1'size='1'style='border:0px'class='inputcentrado'readonly></td>");
				out.print("<td align='center'>B + C + D = Alto > 3<br/>Total: <input type='text'id='Tot2'size='1'style='border:0px'class='inputcentrado'readonly></td>");
				out.print("<td align='center'>B + C + D = Alto > 3<br/>Total: <input type='text'id='Tot3'size='1'style='border:0px'class='inputcentrado'readonly></td>");
				out.print("<tr><td>&nbsp;</td><td></td><td></td></tr>");
				out.print("</table></td></tr></table>");
				out.print("<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type='button'id='CalTotal'value='Calcular Total'onclick='sumaTotal("+numT+")'>");
				out.print("<input type='hidden'id='Estado'size='1'value='1' readonly>");
				rsa.getStatement().close();
				rs.getStatement().close();
				rsi.getStatement().close();
				rst.getStatement().close();
			} catch (SQLException e) {
				e.getMessage();
			}
		}	
		if(opcion.equals("6")){
			String etapa = req.getParameter("etapa");
			try{
				////////////////////////////////////////
				//Cabecera 1
				////////////////////////////////////////
				rs=CPP.cargarPreguntas(etapa);
				out.print("<table width='100%' class='style6' bgcolor='#D3F0FF' border='0'><tr><td>");//
				out.print("&nbsp;&nbsp;<input type='radio'name='Exp'id='RaExp1'onclick='abrirCabezeras2()'checked>");
				out.print("COMPLICACIONES ANTERIORES</td></tr></table><br/>");
				out.print("<div id='contenido1'>");
				out.print("<table width='80%' border='0'id='t1'align='center'>");
				int Cont = 0;
				while(rs.next()){
					Cont++;
					if(rs.getString(2).equals("r6_seleccion1")){
						String Preg=rs.getString(1);
						String CodPreg=rs.getString(4);
						out.print("<tr><td align='center'width='8%'><label id='"+CodPreg+"'class='clasA1'>"+Preg+":&nbsp;</label></td>");
						out.print("<td width='7%'align='right'>Si <input type='radio'name='ComAnt"+Cont+"'value='Si'id='RadSi"+Cont+"'onclick='ComAnt("+Cont+");ColorRB(xRadNo"+Cont+")'>&nbsp;&nbsp;");
						out.print("No <input type='radio'name='ComAnt"+Cont+"'value='No'id='RadNo"+Cont+"'onclick='ComAnt("+Cont+");ColorRB(xRadNo"+Cont+")'></td>");
						out.print("<td width='2%'><span style='display:none'id='xRadNo"+Cont+"'>(x)</span></td>");
						out.print("<td width='20%'id='Td1'>Cual: <input type='text'id='Txt"+Cont+"'class='text1'disabled></td></tr>");
					}
					if(rs.getString(2).equals("r5_texto")){
						String Preg=rs.getString(1);
						String CodPreg=rs.getString(4);
						out.print("<tr><td align='right'colspan='2'><label id='"+CodPreg+"'class='clasA1'>"+Preg+":&nbsp;</label></td>");
						out.print("<td align='left'colspan='2'>&nbsp;<input type='text'id='spinner'name='value'size='5'value='0'readonly/></td></tr>");
					}
				}
				out.print("<tr><td>&nbsp;</td></tr>");
				out.print("</table></div>");
				////////////////////////////////////////
				//Cabecera 2
				////////////////////////////////////////
				out.print("<table width='100%'class='style6'bgcolor='#D3F0FF'border='0' id='rtui88'><tr><td>");
				out.print("&nbsp;&nbsp;<input type='radio' name='Exp' id='RaExp2' onclick='abrirCabezeras2()'>");
				out.print("TAMIZAJE CLINICO Y DE LABORATORIO ANTERIOR</td></tr></table><br/>");
				out.print("<div id='contenido2'style='display:none'>");
				rs.getStatement().close();
				rs=CPP.cargarPreguntas(etapa);
				out.print("<table width='100%'border='0'id='tab2'align='center'><tr>");
				int Cont1 = 0;
				while(rs.next()){
					Cont1++;
					String Preg = rs.getString(1);
					String CodPreg = rs.getString(4);
					if(rs.getString(2).equals("r1_seleccion2")){
						out.print("<td align='center'><label id='"+CodPreg+"'class='clasB1'>"+Preg+":&nbsp;</label>");
						out.print("Si <input type='radio'name='TamCli"+Cont1+"'value='Si'id='rbS"+Cont1+"'onclick='ColorRB(xrbS"+Cont1+")'class='rbTC'>");
						out.print("No <input type='radio'name='TamCli"+Cont1+"'value='No'id='rbN"+Cont1+"'onclick='ColorRB(xrbS"+Cont1+")'class='rbTC'>");
						out.print("<span style='display:none'id='xrbS"+Cont1+"'>(x)</span></td>");
					}
				}
				out.print("</tr></table>");
				out.print("<hr width='80%' size='2' class='style6'/>");
				out.print("<table width='100%'border='0'id='t3'align='center'><tr>");
				rs.getStatement().close();
				rsi=CPP.cargarItems();
				rs=CPP.cargarPreguntas(etapa);
				while(rs.next()){
					String Preg = rs.getString(1);
					String CodPreg = rs.getString(4);
					if(rs.getString(2).equals("r1_seleccion")){
						out.print("<td align='center'><label id='"+CodPreg+"'class='clasB5'>"+Preg+":&nbsp;</label>");
						out.print("<select id='selectA'class='clasB4'>");						
						while(rsi.next()){
							String Item = rsi.getString(1);
							if(rsi.getString(2).equals("r2_seleccionSan")){
								out.print("<option value='"+Item+"'>"+Item+"</option>");
							}
						}
						out.print("</select></td>");
					}
					if(rs.getString(2).equals("r1_seleccion1")){
						out.print("<td align='center'><label id='"+CodPreg+"'class='clasB5'>"+Preg+":&nbsp;</label>");
						out.print("<select id='selectB'class='clasB4'>");
						out.print("<option value='Negativo'>Negativo</option>");
						out.print("<option value='Positivo'>Positivo</option>");
						out.print("</select></td>");
					}
				}
				out.print("</tr></table><br/>");
				rs.getStatement().close();
				rsi.getStatement().close();
				rs=CPP.cargarPreguntas(etapa);
				out.print("<table align='center'border='0'width='100%'><tr><td align='right'><table width='90%'border='0'>");
				out.print("<tr><td>&nbsp;</td><td align='center'>Si</td><td align='center'>No</td><td></td><td align='center'>Comentarios</td></tr>");
				while(rs.next()){
					Cont1++;
					String Preg = rs.getString(1);
					String CodPreg = rs.getString(4);
					if(rs.getString(2).equals("r5_texto1")){
						out.print("<tr><td><label id='"+CodPreg+"'class='clasB2'>"+Preg+":&nbsp;</label></td>");
						out.print("<td align='center'>&nbsp;<input type='radio'value='Si'name='resp1"+Cont1+"'class='radSN'onclick='ColorRB(Xresp1"+Cont1+")'>&nbsp;</td>");
						out.print("<td align='center'>&nbsp;<input type='radio'value='No'name='resp1"+Cont1+"'class='radSN'onclick='ColorRB(Xresp1"+Cont1+")'>&nbsp;</td>");
						out.print("<td><span style='display:none'id='Xresp1"+Cont1+"'>(x)</span></td>");
						out.print("<td align='center'><input type='text'class='clasB3'id='Txt"+Cont1+"'onkeyup='ColorRB(STxt"+Cont1+")'size='17'></td></tr>");
						//out.print("");
					}
				}
				out.print("</table></td>");
				rs.getStatement().close();
				rs=CPP.cargarPreguntas(etapa);
				out.print("<td><table width='90%'align='center'border='0'>");
				out.print("<tr><td>&nbsp;</td><td align='center'>Si</td><td align='center'>No</td><td></td><td align='center'>Comentarios</td></tr>");
				while(rs.next()){
					Cont1++;
					String Preg = rs.getString(1);
					String CodPreg = rs.getString(4);
					if(rs.getString(2).equals("r5_texto2")){
						out.print("<tr><td><label id='"+CodPreg+"'class='clasB2'>"+Preg+":&nbsp;</label></td>");
						out.print("<td align='center'>&nbsp;<input type='radio'value='Si'name='resp2"+Cont1+"'class='radSN'onclick='ColorRB(Xresp2"+Cont1+")'>&nbsp;</td>");
						out.print("<td align='center'>&nbsp;<input type='radio'value='No'name='resp2"+Cont1+"'class='radSN'onclick='ColorRB(Xresp2"+Cont1+")'>&nbsp;</td>");
						out.print("<td><span style='display:none'id='Xresp2"+Cont1+"'>(x)</span></td>");
						out.print("<td align='center'><input type='text'class='clasB3'id='Txt"+Cont1+"'onkeyup='ColorRB(STxt"+Cont1+")'size='17'></td></tr>");
						//out.print("");
					}
				}
				rs.getStatement().close();				
				out.print("</table></td></tr>");
				out.print("<tr><td>&nbsp;</td></tr>");
				out.print("</table></div>");
				////////////////////////////////////////
				//Cabecera 3
				////////////////////////////////////////
				out.print("<table width='100%'class='style6'bgcolor='#D3F0FF'border='0'><tr><td>");
				out.print("&nbsp;&nbsp;<input type='radio' name='Exp' id='RaExp3' onclick='abrirCabezeras2()'>");
				out.print("EXAMEN FISICO DE LA PRIMERA CONSULTA</td></tr></table><br/>");
				out.print("<div id='contenido3'style='display:none'>");
				rs=CPP.cargarPreguntas(etapa);
				int ContEx = 0;
				out.print("<table width='100%'border='0'><tr>");
				while(rs.next()){
					ContEx++;
					String Preg = rs.getString(1);
					String CodPreg = rs.getString(4);
					if(rs.getString(2).equals("r4_numerica2")){
						out.print("<td align='center'><label id='"+CodPreg+"'class='clasC1'>"+Preg+":&nbsp;</label>" +
								"<input type='text'id='Txt3"+ContEx+"'onkeyUp='soloNum(this);ColorRB(STxt3"+ContEx+")'size='10'class='ExFis'>" +
								"<span style='display:none'id='STxt3"+ContEx+"'>(x)</span></td>");
					}
					if(rs.getString(2).equals("r4_numerica1")){
						out.print("<td align='center'><label id='"+CodPreg+"'class='clasC1'>"+Preg+":&nbsp;</label>" +
								"<input type='text'id='Txt3"+ContEx+"'onkeyUp='numMenor(this);ColorRB(STxt3"+ContEx+")'size='10'class='ExFis'>" +
								"<span style='display:none'id='STxt3"+ContEx+"'>(x)</span></td>");						
					}
					if(rs.getString(2).equals("r4_numerica3")){
						out.print("<td align='center'><label id='"+CodPreg+"'class='clasC1'>"+Preg+":&nbsp;</label>" +
								"<input type='text'id='Txt3"+ContEx+"'onkeyUp='numTA(this);ColorRB(STxt3"+ContEx+")'size='10'class='ExFis'>" +
								"<span style='display:none'id='STxt3"+ContEx+"'>(x)</span></td>");						
					}
				}
				rs.getStatement().close();
				out.print("</tr></table><br/>");
				out.print("<table width='90%'border='0'id='tabla3'align='center'>");
				out.print("<tr><td>&nbsp;</td>");
				out.print("<td>&nbsp;</td>");
				rsi=CPP.cargarItems();
				while(rsi.next()){
					String Item=rsi.getString(1);
					String CodItem=rsi.getString(3);
					if(rsi.getString(2).equals("r5_seleccionRad")){
						out.print("<td align='center'><label id='"+CodItem+"'>"+Item+"</label></td>");						
					}
				}
				out.print("</tr>");
				rsi.getStatement().close();
				rs=CPP.cargarPreguntas(etapa);
				while(rs.next()){
					ContEx++;
					String Preg = rs.getString(1);
					String CodPreg = rs.getString(4);
					if(rs.getString(2).equals("r6_seleccion2")){
						out.print("<tr "+Evento+"><td width='15%'>&nbsp;&nbsp;<label id='"+CodPreg+"'class='clasC2'>"+Preg+":</label></td>");
						out.print("<td width='3%'align='center'><span style='display:none'id='SRadN"+ContEx+"'>(x)</span></td>");
						out.print("<td width='8%'align='center'><input type='radio'name='RadExa"+ContEx+"'id='RadN"+ContEx+"'value='Normal'class='clasCR2'onclick='ColorRB(SRadN"+ContEx+");ComExaFis("+ContEx+")'></td>");
						out.print("<td width='10%'align='center'><input type='radio'name='RadExa"+ContEx+"'id='RadP"+ContEx+"'value='Patologico'class='clasCR2'onclick='ColorRB(SRadN"+ContEx+");ComExaFis("+ContEx+")'></td>");
						out.print("<td align='center'><input type='text'id='TxtCab3"+ContEx+"'size='80'class='detC'disabled></td></tr>");
					}
				}
				rs.getStatement().close();
				out.print("<tr><td>&nbsp;</td></tr>");
				out.print("</table>");
				out.print("</div>");
				out.print("<input type='hidden'id='Estado'size='1'value='2'readonly>");
			} catch (SQLException e){
				e.getMessage();
			}
			////////////////////////////////////////
			//Fin opcion 6
			////////////////////////////////////////
		}
		if(opcion.equals("7")){
			String etapa = req.getParameter("etapa");
			String numTrim = req.getParameter("trim");
			String CodUsuario = req.getParameter("codigo");
			String fecha = req.getParameter("fecA");
			String CodPac = req.getParameter("codpac");//
			String CodPacI = req.getParameter("codpacI");
			try {
				////////////////////////////////////////
				//Cabecera 1
				////////////////////////////////////////
				out.print("<table width='100%' class='style6' bgcolor='#D3F0FF' border='0'><tr><td>");//
				out.print("&nbsp;&nbsp;<input type='radio' name='Exp' id='RaExp1'onclick='abrirCabezeras2()'checked>" +
						"EVOLUCIÓN DEL EMBARAZO</td></tr></table><br/>");				
				out.print("<div id='contenido1'>");
				out.print("<table width='80%' border='0'id='tabla1'align='center'>");
				rs=CPP.cargarPreguntas(etapa);
				int Cont = 0;
				out.print("<tr><td align='center'colspan='4'><label class='style6'>"+numTrim+"</label></td></tr>");
				out.print("<tr><td>&nbsp;</td></tr>");
				out.print("<tr><td><table align='center'border='0'width='100%'>");
				while(rs.next()){
					Cont++;
					String Preg = rs.getString(1);
					String CodPreg = rs.getString(4);
					if(rs.getString(2).equals("r7_fechacon")){
						out.print("<tr "+Evento+"><td>&nbsp;</td><td><label id='"+CodPreg+"'class='clasEV'>"+Preg+":&nbsp;</label></td>");
						out.print("<td><input type='text'id='Txt"+Cont+"'value='"+fecha+"'class='evol'readonly></td></tr>");
					}
					if(rs.getString(2).equals("r5_altura1")){
						out.print("<tr "+Evento+"><td>&nbsp;</td><td width='46%'><label id='"+CodPreg+"'class='clasEV'>"+Preg+":&nbsp;</label></td>");
						out.print("<td width='54%'><input type='text'id='Txt"+Cont+"'onkeyUp='numAlturaU(this);ColorRB(STxt"+Cont+")'class='evol'><span style='display:none'id='STxt"+Cont+"'>(x)</span></td></tr>");
					}
					if(rs.getString(2).equals("r5_peso1")){
						out.print("<tr "+Evento+"><td>&nbsp;</td><td width='46%'><label id='"+CodPreg+"'class='clasEV'>"+Preg+":&nbsp;</label></td>");
						out.print("<td width='54%'><input type='text'id='Txt"+Cont+"'onkeyUp='numPesoKg(this);ColorRB(STxt"+Cont+")'class='evol'><span style='display:none'id='STxt"+Cont+"'>(x)</span></td></tr>");
					}
					if(rs.getString(2).equals("r5_texto4")){
						out.print("<tr "+Evento+"><td>&nbsp;</td><td><label id='"+CodPreg+"'class='clasEV'>"+Preg+":&nbsp;</label></td>");
						out.print("<td><input type='text'id='Txt"+Cont+"'class='evol'onkeyUp='ColorRB(STxt"+Cont+")'><span style='display:none'id='STxt"+Cont+"'>(x)</span></td></tr>");//
					}
					if(rs.getString(2).equals("r5_gestacion1")){
						rsb=CPP.DateDif(CodPacI, fecha);
						if(rsb.next()){
							int Valor = rsb.getInt(1);
							int Semanas = (int) Math.floor(Valor/7);
							int Dias = Valor%7;
							out.print("<tr "+Evento+"><td>&nbsp;</td><td><label id='"+CodPreg+"'class='clasEV'>"+Preg+":&nbsp;</label></td>");
							out.print("<td><input type='text'class='evol'id='Txt"+Cont+"'value='"+Semanas+" Semanas con "+Dias+" dias'readonly></td></tr>");
						}
						rsb.getStatement().close();
					}
				}
				out.print("</table></td>");
				rs.getStatement().close();
				out.print("<td>&nbsp;</td>");
				rs=CPP.cargarPreguntas(etapa);
				out.print("<td><table align='center'border='0'width='100%'>");
				while(rs.next()){
					Cont++;
					String Preg = rs.getString(1);
					String CodPreg = rs.getString(4);
					if(rs.getString(2).equals("r5_texto5")){
						out.print("<tr "+Evento+"><td>&nbsp;</td><td><label id='"+CodPreg+"'class='clasEV'>"+Preg+":&nbsp;</label></td>");
						out.print("<td><input type='text'id='Txt"+Cont+"'class='evol'onkeyUp='ColorRB(STxt"+Cont+")'><span style='display:none'id='STxt"+Cont+"'>(x)</span></td></tr>");
					}
					if(rs.getString(2).equals("r7_fecha2")){
						out.print("<tr "+Evento+"><td>&nbsp;</td><td><label id='"+CodPreg+"'class='clasEV'>"+Preg+":&nbsp;</label></td>");
						out.print("<td><input type='text'id='Txt"+Cont+"'class='evol'onblur='mascara(this)'onchange='ColorRB2(STxt"+Cont+")'size='11'><span style='display:none'id='STxt"+Cont+"'>(x)</span></td></tr>");
					}
					if(rs.getString(2).equals("r5_selitem")){
						out.print("<tr "+Evento+"><td>&nbsp;</td><td><label id='"+CodPreg+"'class='clasEV'>"+Preg+":&nbsp;</label></td>");
						out.print("<td><input type='text'id='Txt"+Cont+"'class='evol'onkeyUp='ColorRB(STxt"+Cont+")'><span style='display:none'id='STxt"+Cont+"'>(x)</span></td></tr>");
					}
				}
				rs.getStatement().close();
				out.print("</table></td></tr></table><br/>");
				out.print("<hr width='70%' size='2' class='style6'/>");
				out.print("<table width='40%' border='0'id='tabla2'align='center'>");
				rs=CPP.cargarPreguntas(etapa);
				out.print("<tr><td>&nbsp;</td><td>&nbsp;Si</td><td>No</td>");
				while(rs.next()){
					Cont++;
					String Preg = rs.getString(1);
					String CodPreg = rs.getString(4);
					if(rs.getString(2).equals("r1_seleccion3")){
						out.print("<tr "+Evento+"><td width='60%'>&nbsp;&nbsp<label id='"+CodPreg+"'class='RadEvo'>"+Preg+":</label></td>");
						out.print("<td width='10%'><input type='radio'name='RadEvo"+Cont+"'id='RadS"+Cont+"'value='Si'onclick='ColorRB(SRadN"+Cont+")'></td>");
						out.print("<td width='10%'><input type='radio'name='RadEvo"+Cont+"'id='RadN"+Cont+"'value='No'onclick='ColorRB(SRadN"+Cont+")'></td>");
						out.print("<td width='10%'><span style='display:none'id='SRadN"+Cont+"'>(x)</span></td></tr>");
					}
				}
				out.print("<tr><td>&nbsp;</td></tr></table></div>");
				rs.getStatement().close();
				////////////////////////////////////////
				//Cabecera 2
				////////////////////////////////////////
				out.print("<table width='100%' class='style6' bgcolor='#D3F0FF' border='0'><tr><td>");
				out.print("&nbsp;&nbsp;<input type='radio' name='Exp' id='RaExp2'onclick='abrirCabezeras2()'" +
						">ECOGRAFIA</td></tr></table><br/>");				
				out.print("<div id='contenido2'style='display:none'>");
				rs=CPP.cargarPreguntas(etapa);
				out.print("<table width='80%' border='0'id='tabla1'align='center'>");
				while(rs.next()){
					Cont++;
					String Preg = rs.getString(1);
					String CodPreg = rs.getString(4);
					if(rs.getString(2).equals("r7_fecha3")){
						rsb=CPP.devEcografia(CodPacI);
						if(rsb.next()){
							String eco = rsb.getString(1);
							out.print("<tr><td width='20%'>&nbsp;</td><td><label id='"+CodPreg+"'>"+Preg+":</label></td>");
							out.print("<td width='50%'><input type='text'id='Txt"+Cont+"'size='30'value='"+eco+"'onchange='ColorRB(STxt"+Cont+")'class='evol2'readonly></td></tr>");
						}
						rsb.getStatement().close();
					}
					if(rs.getString(2).equals("r5_peso2")){
						out.print("<tr><td width='20%'>&nbsp;</td><td><label id='"+CodPreg+"'>"+Preg+":</label></td>");
						out.print("<td width='50%'><input type='text'id='Txt"+Cont+"'onkeyUp='numPesoKg(this);ColorRB(STxt"+Cont+")'size='30'class='evol2'><span style='display:none'id='STxt"+Cont+"'>(x)</span></td></tr>");
					}
					if(rs.getString(2).equals("r5_texto6")){
						out.print("<tr><td width='20%'>&nbsp;</td><td><label id='"+CodPreg+"'>"+Preg+":</label></td>");
						out.print("<td width='50%'><input type='text'id='Txt"+Cont+"'size='30'class='evol2'onkeyUp='ColorRB(STxt"+Cont+")'><span style='display:none'id='STxt"+Cont+"'>(x)</span></td></tr>");
					}
					if(rs.getString(2).equals("r5_gestacion2")){
						out.print("<tr><td>&nbsp;</td><td><label id='"+CodPreg+"'>"+Preg+":</label></td>");
						out.print("<td><input type='text'name='ges2'id='Txt"+Cont+"'size='30'class='evol2'onchange='ColorRB2(STxt"+Cont+")'readonly><span style='display:none'id='STxt"+Cont+"'>(x)</span></td></tr>");
					}
					if(rs.getString(2).equals("r5_atencion")){
						rsb=CPP.devCodUser(CodUsuario);
						if(rsb.next()){
							String Nom = rsb.getString(1);
							out.print("<tr><td width='20%'>&nbsp;</td><td><label id='"+CodPreg+"'>"+Preg+":</label></td>");
							out.print("<td width='50%'><input type='text'id='Txt"+Cont+"'size='30'value='"+Nom+"'readonly></td></tr>");
						}
						rsb.getStatement().close();
					}
				}
				rs.getStatement().close();
				out.print("<tr><td>&nbsp;</td></tr></table></div>");
				////////////////////////////////////////
				//Cabecera 3
				////////////////////////////////////////
				out.print("<table width='100%' class='style6' bgcolor='#D3F0FF' border='0'><tr><td>");//
				out.print("&nbsp;&nbsp;<input type='radio' name='Exp' id='RaExp3'onclick='abrirCabezeras2()'" +
						">FORMULARIOS</td></tr></table><br/>");
				out.print("<div id='contenido3'style='display:none'>");				
				out.print("<table align='center'id='table1'width='79%'border='0'>");
				rs=CPP.cargarPreguntas(etapa);
				rst=CPP.NumControles(CodPac);
				int Con = 0;
				while(rs.next()){
					String Preg = rs.getString(1);
					String CodPreg = rs.getString(4);
					if(rs.getString(2).equals("r7_fechaform")){
						out.print("<tr><td align='right'><label id='"+CodPreg+"'class='style6'>"+Preg+":&nbsp;</label></td>");
						out.print("<td><input type='text'id='Txt"+Cont+"'value='"+fecha+"'size='8'style='border:0px'class='textf'readonly></td>");
					}
					if(rs.getString(2).equals("r4_numerica3")){
						out.print("<td align='right'><label id='"+CodPreg+"'class='style6'>"+Preg+":&nbsp;</label></td>");
						if(rst.next()){
							int resC = rst.getInt(1);
							int con = resC+1;
							out.print("<td><input type='text'id='Txt"+Cont+"'value='"+con+"'size='3'style='border:0px'class='textf'readonly></td></tr>");//textf
						}
						out.print("<tr><td>&nbsp;</td></tr>");
					}
				}
				out.print("</table>");
				rst.getStatement().close();
				rs.getStatement().close();
				rs=CPP.cargarPreguntas(etapa);
				out.print("<table align='center'width='75%'border='0'><tr>");
				while(rs.next()){
					String Preg = rs.getString(1);
					String CodPreg = rs.getString(4);
					if(rs.getString(2).equals("r5_texto7")){
						Con++;
						out.print("<td><table width='45%'border='0'>");
						out.print("<tr><td><label id='"+CodPreg+"'>"+Preg+":</label></td></tr>");
						out.print("<tr><td align='left'><textarea cols=40 rows=5 id='tab"+Con+"'" +
								"class='textf'></textarea></td></tr></table></td>");
					}
				}
				rs.getStatement().close();
				out.print("</tr></table></div>");
				out.print("<input type='hidden'id='Estado'size='1'value='3'readonly>");
			} catch(SQLException e){
				e.getMessage();
			}
			////////////////////////////////////////
			//Fin opcion 7
			////////////////////////////////////////
		}
		if(opcion.equals("8")){
			String etapa = req.getParameter("etapa");
			String numTrim = req.getParameter("trim");
			int Cont = 0;
			try {
				out.print("<table width='100%' class='style6' bgcolor='#D3F0FF' border='0'><tr><td>");
				out.print("&nbsp;&nbsp;<input type='radio' name='Exp' id='RaExp1' onclick='abrirCabezeras2()'checked>");
				out.print("EXAMENES DE LABORATORIO</td></tr></table><br/>");
				out.print("<div id='contenido1'>");
				out.print("<table width='80%' border='0'id='tabla1'align='center'>");
				rs=CPP.cargarPreguntas(etapa);
				out.print("<tr><td width='30%'align='center'><label class='style6'>Laboratorios</label></td><td align='center'colspan='2'>" +
						"<label class='style6'>"+numTrim+"</label></td></tr>");
				out.print("<tr><td width='30%'>&nbsp;&nbsp;</td>");
				out.print("<td width='35%'align='center'>Fecha</td>");
				out.print("<td width='35%'align='center'>Resultados</td></tr>");
				out.print("</table>");
				out.print("<table width='80%'border='0'id='tabla1'align='center'>");
				while(rs.next()){
					String Preg = rs.getString(1);
					String CodPreg = rs.getString(4);
					if(rs.getString(2).equals("r8_fechatex")){
						Cont++;
						out.print("<tr "+Evento+"><td width='30%'>&nbsp;&nbsp;<label id='"+CodPreg+"'class='label'>"+Preg+":</label></td>");
						out.print("<td width='35%'align='center'><input type='text'id='TxtF"+Cont+"'onfocus='CalendarioNew(this)'class='clasF'" +
								"onchange='ExLab("+Cont+")'readonly><span id='STxtF"+Cont+"'class='style8 simulalink'onclick='BorExLab("+Cont+")'>*</span></td>");
						out.print("<td width='35%'align='center'><input type='text'id='TxtR"+Cont+"'class='clasR'disabled='true'></td></tr>");
					}
				}
				out.print("<tr><td>&nbsp;</td></tr></table></div>");
				rs.getStatement().close();
				rsi=CPP.cargarSecciones();
				out.print("<table width='100%' class='style6' bgcolor='#D3F0FF' border='0'><tr><td>");
				out.print("&nbsp;&nbsp;<input type='radio' name='Exp' id='RaExp2' onclick='abrirCabezeras2()'>");
				out.print("CONDUCTAS A SEGUIR</td></tr></table><br/>");
				out.print("<div id='contenido2'style='display:none'>");
				out.print("<table width='80%' border='0'id='tabla1'align='center'>");
				while(rsi.next()){
					String Sec = rsi.getString(1);
					if(rsi.getString(2).equals("r3_multi_seleccion")){
						out.print("<tr><td><label class='style6 simulalink labSec'onclick='abrirdiv(d1)'>"+Sec+"</label>:</td></tr>");
						out.print("<tr><td>&nbsp;</td></tr>");
						out.print("<tr><td><div id='d1'><table width='90%'border='0'align='center'>");
						rs=CPP.cargarConductas(etapa);
						while(rs.next()){
							String Cond = rs.getString(1);
							String CodCond = rs.getString(2);
							if(rs.getString(3).equals("r7_fecha")){
								out.print("<tr "+Evento+"><td>&nbsp;&nbsp;&nbsp;&nbsp;<label id='"+CodCond+"'class='labCond'>"+Cond+":</label></td>");
								out.print("<td align='center'><input type='text'id='TxtFec'onfocus='Calendario2(this)'readonly></td></tr>");
							}
							if(rs.getString(3).equals("r3_multi_seleccion")){
								out.print("<tr "+Evento+"><td>&nbsp;&nbsp;&nbsp;&nbsp;<label id='"+CodCond+"'class='labCond'>"+Cond+":</label></td>");
								out.print("<td width='35%'align='center'><input type='checkbox'value='Si'/></tr>");
							}
						}
						out.print("<tr><td>&nbsp;</td></tr>");
						out.print("</table></div></td></tr>");
						rs.getStatement().close();
					}
					if(rsi.getString(2).equals("r3_multi_seleccion1")){
						out.print("<tr><td><label class='style6 simulalink labSec'onclick='abrirdiv(d2)'>"+Sec+"</label>:</td></tr>");
						out.print("<tr><td>&nbsp;</td></tr>");
						out.print("<tr><td><div id='d2'style='display:none'><table width='90%'border='0'align='center'>");
						rs=CPP.cargarConductas(etapa);
						while(rs.next()){
							String Cond = rs.getString(1);
							String CodCond = rs.getString(2);
							if(rs.getString(3).equals("r3_multi_seleccion1")){
								out.print("<tr "+Evento+"><td>&nbsp;&nbsp;&nbsp;&nbsp;<label id='"+CodCond+"'class='labCond'>"+Cond+"</label>:</td>");
								out.print("<td width='35%'align='center'><input type='checkbox'value='Si'/></tr>");
							}
						}
						out.print("<tr><td>&nbsp;</td></tr>");
						out.print("</table></div></td></tr>");
						rs.getStatement().close();
					}
					if(rsi.getString(2).equals("r1_seleccion")){
						out.print("<tr><td><label class='style6 simulalink labSec'onclick='abrirdiv(d3)'>"+Sec+"</label>:</td></tr>");
						out.print("<tr><td><div id='d3'style='display:none'><table width='90%'border='0'align='center'>");
						out.print("<tr><td>&nbsp;</td></tr>");
						rs=CPP.cargarConductas(etapa);
						while(rs.next()){
							String Cond = rs.getString(1);
							String CodCond = rs.getString(2);
							if(rs.getString(3).equals("r1_seleccion")){
								out.print("<tr "+Evento+"><td>&nbsp;&nbsp;&nbsp;&nbsp;<label class='labCond'>"+Cond+"</label>:</td>");
								out.print("<td width='35%'align='center'><input id='"+CodCond+"'name='rem'type='radio'value='"+Cond+"'checked/></tr>");
							}
						}
						out.print("<tr><td>&nbsp;</td></tr>");
						out.print("</table></div></td></tr>");
						rs.getStatement().close();
					}
				}
				out.print("</table></div>");
				out.print("<input type='hidden'id='Estado'size='1'value='4'readonly>");
				rsi.getStatement().close();
			}catch(SQLException e){
				e.getMessage();
			}
		}
		
		if(opcion.equals("reportes")){
			out.print("<table width='100%' class='style6' bgcolor='#D3F0FF' border='0'><tr><td>");
			out.print("&nbsp;&nbsp;HISTORIAL Y REPORTES</td></tr></table><br/>");
			out.print("<div id='repor'>");
			out.print("<table width='80%' border='0'id='tabla1'align='center'>");
			out.print("<tr><td>");
			out.print("<input value='infPer'name='rep'type='radio'/>");
			out.print("<label>Información de la gestante</label>");
			out.print("</td></tr>");
			out.print("<tr><td>&nbsp;</tr></td>");
			int Cont = 0;
			while(Cont<4){
				Cont++;
				out.print("<tr><td>");
				out.print("<input value='etapa"+Cont+"'name='rep'type='radio'/>");
				out.print("<label>Reporte de la etapa 2-"+Cont+"</label>");
				out.print("</td></tr>");
				out.print("<tr><td>&nbsp;</tr></td>");
			}
			out.print("<tr><td>");
			out.print("<input type='button'id='ButReporte'value='Generar Reporte'onclick='GenReportes()'>");
			out.print("</td></tr></div>");
			out.print("<input type='hidden'id='Estado'size='1'value='5'readonly>");
		}
		
		////////////////////////////////////////
		//Guardar A
		if(opcion.equals("9")){
			String tabla = "aiepi_gestante_21_a";
			String codPac = req.getParameter("codPac");
			String cedPac = req.getParameter("cedPac");
			String Etapa = req.getParameter("etapa");
			String area = req.getParameter("area");
			String fecha = req.getParameter("fecha");
			String trim = req.getParameter("trim");
			String codPreg = req.getParameter("codPreg");
			String codResp = req.getParameter("codResp");
			String Resp = req.getParameter("Resp");
			String Det = req.getParameter("Det");
			String codUser = req.getParameter("codUser");
			String Codigo = req.getParameter("codInt");
			try {
				rst=CPP.SinDuplicado(tabla, codPac, codPreg);
				if(rst.next()){
					int Num = rst.getInt(1);
					if(Num==0){
						CPP.Etapa21_AreaA(codPac, cedPac, Etapa, area, fecha, 
						trim, codPreg, codResp, Resp, Det, codUser, Codigo);
					}else if(Num>0){
						CPP.ActualizarA1(codPac, Det, codPreg, codResp, Resp, codUser);
					}
				}
				rst.getStatement().close();
			} catch (SQLException e) {
				e.getMessage();
			}
		}
		
		////////////////////////////////////////
		//Guardar A1
		if(opcion.equals("9.1")){
			String tabla = "aiepi_gestante_21_a";
			String codPac = req.getParameter("codPac");
			String cedPac = req.getParameter("cedPac");
			String Etapa = req.getParameter("etapa");
			String area = req.getParameter("area");
			String fecha = req.getParameter("fecha");
			String trim = req.getParameter("trim");
			String codPreg = req.getParameter("codPreg");
			String codResp = req.getParameter("codResp");
			String Resp = req.getParameter("Resp");
			String Det = req.getParameter("Det");
			String codUser = req.getParameter("codUser");
			String Codigo = req.getParameter("codInt");
			try {
				rst=CPP.SinDuplicado2(tabla, codPac, codResp);
				if(rst.next()){
					int Num = rst.getInt(1);
					if(Num==0){
						CPP.Etapa21_AreaA(codPac, cedPac, Etapa, area, fecha, 
						trim, codPreg, codResp, Resp, Det, codUser, Codigo);
					}else{
						CPP.EliminarA1(codPac, codPreg, codResp);
					}
				}
				rst.getStatement().close();
			} catch (SQLException e) {
				e.getMessage();
			}
		}
		
		////////////////////////////////////////
		//Guardar A2
		if(opcion.equals("9.2")){
			String tabla = "aiepi_gestante_21_a";
			String codPac = req.getParameter("codPac");
			String cedPac = req.getParameter("cedPac");
			String Etapa = req.getParameter("etapa");
			String area = req.getParameter("area");
			String fecha = req.getParameter("fecha");
			String trim = req.getParameter("trim");
			String codPreg = req.getParameter("codPreg");
			String codResp = req.getParameter("codResp");
			String Resp = req.getParameter("Resp");
			String Det = req.getParameter("Det");
			String codUser = req.getParameter("codUser");
			String Codigo = req.getParameter("codInt");
			try {
				rst=CPP.SinDuplicado2(tabla, codPac, codResp);
				if(rst.next()){
					int Num = rst.getInt(1);
					if(Num==0){
						CPP.Etapa21_AreaA(codPac, cedPac, Etapa, area, fecha, 
						trim, codPreg, codResp, Resp, Det, codUser, Codigo);
					}else{
						CPP.EliminarA1(codPac, codPreg, codResp);
					}
				}
				rst.getStatement().close();
			} catch (SQLException e) {
				e.getMessage();
			}
		}
		
		////////////////////////////////////////
		//Guardar A3
		if(opcion.equals("9.3")){
			String tabla = "aiepi_gestante_21_a";
			String codPac = req.getParameter("codPac");
			String cedPac = req.getParameter("cedPac");
			String Etapa = req.getParameter("etapa");
			String area = req.getParameter("area");
			String fecha = req.getParameter("fecha");
			String trim = req.getParameter("trim");
			String codPreg = req.getParameter("codPreg");
			String codResp = req.getParameter("codResp");
			String Resp = req.getParameter("Resp");
			String Det = req.getParameter("Det");
			String codUser = req.getParameter("codUser");
			String Codigo = req.getParameter("codInt");
			try {
				rst=CPP.SinDuplicado2(tabla, codPac, codResp);
				if(rst.next()){
					int Num = rst.getInt(1);
					if(Num==0){
						CPP.Etapa21_AreaA(codPac, cedPac, Etapa, area, fecha, 
						trim, codPreg, codResp, Resp, Det, codUser, Codigo);
					}else{
						CPP.EliminarA1(codPac, codPreg, codResp);
					}
				}
				rst.getStatement().close();
			} catch (SQLException e) {
				e.getMessage();
			}
		}
		
		if(opcion.equals("10")){
			String Fecha = req.getParameter("fececo");
			try {
				rst=CPP.DifDias(Fecha);
				if(rst.next()){
					int FecEco = rst.getInt(1);
					out.print(FecEco);
				}
				rst.getStatement().close();
			} catch (SQLException e) {
				e.getMessage();
			}
		}
		
		if(opcion.equals("11")){
			String Fecha = req.getParameter("fecM");
			try {
				rst=CPP.DifDias(Fecha);
				if(rst.next()){
					int Dias = rst.getInt(1);
					out.print(Dias);
				}
				rst.getStatement().close();
			} catch (SQLException e) {
				e.getMessage();
			}
		}
		
		if(opcion.equals("12")){
			String Fecha = req.getParameter("fecpar");
			try {
				rst=CPP.FechaParto(Fecha);
				if(rst.next()){
					String FecPar = rst.getString(1);
					out.print(FecPar);
				}
				rst.getStatement().close();
			} catch (SQLException e) {
				e.getMessage();
			}
		}
		
		if(opcion.equals("auc")){
			try{
				rs=CPP.cargarRemision();
				while(rs.next()){
					String rem = rs.getString(1);
					out.print(rem+",");
				}
			}catch(SQLException e){
				e.getMessage();
			}
		}
		
		if(opcion.equals("13")){
			String Etapa = "Etapa 2-1";
			String Codigo = req.getParameter("codInt");
			String codPac = req.getParameter("codPac");
			String cedPac = req.getParameter("cedPac");
			String area = req.getParameter("area");
			String fecha = req.getParameter("fecha");
			String trim = req.getParameter("trim");
			String codPreg = req.getParameter("codPreg");
			String Resp = req.getParameter("Resp");
			String codUser = req.getParameter("codUser");
			////////////////////////////////////////
			//Seccion B
			////////////////////////////////////////
			String[] CodPre = codPreg.split(",");
			String[] resp = Resp.split(",");
	        for(int i = 0; i < CodPre.length; i++){
	        	CPP.Etapa21_AreaB(codPac, cedPac, Etapa, area, fecha, 
	        	trim, CodPre[i], resp[i], codUser, Codigo);
	        }
		}
		
		if(opcion.equals("14")){
			String Etapa = "Etapa 2-1";
			String Codigo = req.getParameter("codInt");
			String codPac = req.getParameter("codPac");
			String cedPac = req.getParameter("cedPac");
			String area = req.getParameter("area");
			String fecha = req.getParameter("fecha");
			String trim = req.getParameter("trim");
			String codPreg = req.getParameter("codPreg");
			String Resp = req.getParameter("Resp");
			String codUser = req.getParameter("codUser");
			////////////////////////////////////////
			//Seccion C
			////////////////////////////////////////
			String[] CodPre = codPreg.split(",");
			String[] resp = Resp.split(",");
	        for(int i = 0; i < CodPre.length; i++){
	        	CPP.Etapa21_AreaC(codPac, cedPac, Etapa, area, fecha, 
	        	trim, CodPre[i], resp[i], codUser, Codigo);
	        }
		}
		
		if(opcion.equals("15")){
			String Etapa = "Etapa 2-1";
			String Codigo = req.getParameter("codInt");
			String codPac = req.getParameter("codPac");
			String cedPac = req.getParameter("cedPac");
			String area = req.getParameter("area");
			String fecha = req.getParameter("fecha");
			String trim = req.getParameter("trim");
			String codPreg1 = req.getParameter("codPreg1");
			String codPreg2 = req.getParameter("codPreg2");
			String codResp1 = req.getParameter("codResp1");
			String codResp2 = req.getParameter("codResp2");
			String RespR = req.getParameter("RespR");
			String RespS = req.getParameter("RespS");
			String codItem = req.getParameter("codItem");
			String codUser = req.getParameter("codUser");
			String cItem = "Ausente/Intenso";
			////////////////////////////////////////
			//Seccion D
			////////////////////////////////////////
			String[] CodPre1 = codPreg1.split(",");
			String[] CodPre2 = codPreg2.split(",");
	        String[] CodRes1 = codResp1.split(",");
	        String[] CodRes2 = codResp2.split(",");
	        String[] resR = RespR.split(",");
	        String[] resS = RespS.split(",");
	        String[] CodItem = codItem.split(",");
	        	        
	        for (int i = 0; i < CodPre1.length; i++) {
	        	CPP.Etapa21_AreaD(codPac, cedPac, Etapa, area, fecha, 
	        	trim, CodPre1[i], CodRes1[i], resR[i], cItem, codUser, Codigo);
	        }
	        for (int j = 0; j < resS.length; j++) {
	        	CPP.Etapa21_AreaD(codPac, cedPac, Etapa, area, fecha, 
	        	trim, CodPre2[0], CodRes2[0], resS[j], CodItem[j], codUser, Codigo);
	        }
		}
		
		if(opcion.equals("16")){
			String Etapa = "Etapa 2-2";
			String Codigo = req.getParameter("codInt");
			String codPac = req.getParameter("codPac");
			String cedPac = req.getParameter("cedPac");
			String area = req.getParameter("area");
			String fecha = req.getParameter("fecha");
			String trim = req.getParameter("trim");
			String codPreg = req.getParameter("codPreg");
			String Resp = req.getParameter("Resp");
			String detResp = req.getParameter("detResp");
			String codUser = req.getParameter("codUser");
			////////////////////////////////////////
			//Seccion 22-1
			////////////////////////////////////////
			String[] CodPre = codPreg.split(",");
			String[] resp = Resp.split(",");
			String[] Dresp = detResp.split(",");
	        for(int i = 0; i < Dresp.length; i++){
	        	CPP.Etapa22_Area1(codPac, cedPac, Etapa, area, fecha, 
	        	trim, CodPre[i], resp[i], Dresp[i], codUser, Codigo);
	        }
		}
		
		if(opcion.equals("17")){
			String Comen = "Sin detalles";
			String Etapa = "Etapa 2-2";
			String Codigo = req.getParameter("codInt");
			String codPac = req.getParameter("codPac");
			String cedPac = req.getParameter("cedPac");
			String area = req.getParameter("area");
			String fecha = req.getParameter("fecha");
			String trim = req.getParameter("trim");
			String codPreg = req.getParameter("codPreg");
			String codPreg2 = req.getParameter("codPreg2");
			String codPreg3 = req.getParameter("codPreg3");
			String Resp = req.getParameter("Resp");
			String Resp2 = req.getParameter("Resp2");
			String Resp3 = req.getParameter("Resp3");
			String Com = req.getParameter("com");
			String codUser = req.getParameter("codUser");
			////////////////////////////////////////
			//Seccion 22-2
			////////////////////////////////////////
			String[] CodPre = codPreg.split(",");
			String[] CodPre2 = codPreg2.split(",");
			String[] CodPre3 = codPreg3.split(",");
			String[] resp = Resp.split(",");
			String[] resp2 = Resp2.split(",");
			String[] resp3 = Resp3.split(",");
			String[] com = Com.split(",");
	        for(int i = 0; i < CodPre.length; i++){
	        	CPP.Etapa22_Area2(codPac, cedPac, Etapa, area, fecha, 
	        	trim, CodPre[i], resp[i], Comen, codUser, Codigo);
	        }
	        for(int i = 0; i < CodPre2.length; i++){
	        	CPP.Etapa22_Area2(codPac, cedPac, Etapa, area, fecha, 
	        	trim, CodPre2[i], resp2[i], Comen, codUser, Codigo);
	        }
	        for(int i = 0; i < CodPre3.length; i++){
	        	CPP.Etapa22_Area2(codPac, cedPac, Etapa, area, fecha, 
	        	trim, CodPre3[i], resp3[i], com[i], codUser, Codigo);
	        }
		}
		
		if(opcion.equals("18")){
			String det = "Null";
			String Etapa = "Etapa 2-2";
			String Codigo = req.getParameter("codInt");
			String codPac = req.getParameter("codPac");
			String cedPac = req.getParameter("cedPac");
			String area = req.getParameter("area");
			String fecha = req.getParameter("fecha");
			String trim = req.getParameter("trim");
			String codPreg = req.getParameter("codPreg");
			String Resp = req.getParameter("Resp");
			String codPreg2 = req.getParameter("codPreg2");
			String Resp2 = req.getParameter("Resp2");
			String Det = req.getParameter("Det");
			String codUser = req.getParameter("codUser");
			////////////////////////////////////////
			//Seccion 22-3
			////////////////////////////////////////
			String[] CodPre = codPreg.split(",");
			String[] resp = Resp.split(",");
			String[] CodPre2 = codPreg2.split(",");
			String[] resp2 = Resp2.split(",");
			String[] Detalle = Det.split(",");
	        for(int i = 0; i < CodPre.length; i++){
	        	CPP.Etapa22_Area3(codPac, cedPac, Etapa, area, fecha, 
	        	trim, CodPre[i], resp[i], det, codUser, Codigo);
	        }
	        for(int i = 0; i < CodPre2.length; i++){
	        	CPP.Etapa22_Area3(codPac, cedPac, Etapa, area, fecha, 
	        	trim, CodPre2[i], resp2[i], Detalle[i], codUser, Codigo);
	        }
		}
		
		if(opcion.equals("19")){
			String Etapa = "Etapa 2-3";
			String Codigo = req.getParameter("codInt");
			String codPac = req.getParameter("codPac");
			String cedPac = req.getParameter("cedPac");
			String area = req.getParameter("area");
			String fecha = req.getParameter("fecha");
			String trim = req.getParameter("trim");
			String codPreg = req.getParameter("codPreg1");
			String Resp = req.getParameter("Resp1");
			String codPreg2 = req.getParameter("codPreg2");
			String Resp2 = req.getParameter("Resp2");
			String codUser = req.getParameter("codUser");
			////////////////////////////////////////
			//Seccion 23-1
			////////////////////////////////////////
			String[] CodPre = codPreg.split(",");
			String[] resp = Resp.split(",");
			String[] CodPre2 = codPreg2.split(",");
			String[] resp2 = Resp2.split(",");
			
	        for(int i = 0; i < CodPre.length; i++){
	        	CPP.Etapa23_Area1(codPac, cedPac, Etapa, area, fecha, 
	        	trim, CodPre[i], resp[i], codUser, Codigo);
	        }
	        for(int i = 0; i < CodPre2.length; i++){
	        	CPP.Etapa23_Area1(codPac, cedPac, Etapa, area, fecha, 
	        	trim, CodPre2[i], resp2[i], codUser, Codigo);
	        }
		}
		
		if(opcion.equals("20")){
			String Etapa = "Etapa 2-3";
			String Codigo = req.getParameter("codInt");
			String codPac = req.getParameter("codPac");
			String cedPac = req.getParameter("cedPac");
			String area = req.getParameter("area");
			String fecha = req.getParameter("fecha");
			String trim = req.getParameter("trim");
			String codPreg = req.getParameter("codPreg");
			String Resp = req.getParameter("Resp");
			String codUser = req.getParameter("codUser");
			////////////////////////////////////////
			//Seccion 23-2
			////////////////////////////////////////
			String[] CodPre = codPreg.split(",");
			String[] resp = Resp.split(",");
			
	        for(int i = 0; i < CodPre.length; i++){
	        	CPP.Etapa23_Area2(codPac, cedPac, Etapa, area, fecha, 
	        	trim, CodPre[i], resp[i], codUser, Codigo);
	        }
		}
		
		if(opcion.equals("21")){
			String Etapa = "Etapa 2-3";
			String Codigo = req.getParameter("codInt");
			String codPac = req.getParameter("codPac");
			String cedPac = req.getParameter("cedPac");
			String area = req.getParameter("area");
			String fecha = req.getParameter("fecha");
			String trim = req.getParameter("trim");
			String codPreg = req.getParameter("codPreg");
			String Resp = req.getParameter("Resp");
			String codUser = req.getParameter("codUser");
			////////////////////////////////////////
			//Seccion 23-3
			////////////////////////////////////////
			String[] CodPre = codPreg.split(",");
			String[] resp = Resp.split(",");
			
	        for(int i = 0; i < CodPre.length; i++){
	        	CPP.Etapa23_Area3(codPac, cedPac, Etapa, area, fecha, 
	        	trim, CodPre[i], resp[i], codUser, Codigo);
	        }
		}
		
		if(opcion.equals("22")){
			String Etapa = "Etapa 2-4";
			String Codigo = req.getParameter("codInt");
			String codPac = req.getParameter("codPac");
			String cedPac = req.getParameter("cedPac");
			String area = req.getParameter("area");
			String fecha = req.getParameter("fecha");
			String trim = req.getParameter("trim");
			String codPreg = req.getParameter("codPreg");
			String fecLab = req.getParameter("fecLab");
			String Resp = req.getParameter("Resp");
			String codUser = req.getParameter("codUser");
			////////////////////////////////////////
			//Seccion 24-1
			////////////////////////////////////////
			String[] CodPre = codPreg.split(",");
			String[] fecL = fecLab.split(",");
			String[] resp = Resp.split(",");
			
	        for(int i = 0; i < CodPre.length; i++){
	        	CPP.Etapa24_Area1(codPac, cedPac, Etapa, area, fecha, 
	        	trim, CodPre[i], fecL[i], resp[i], codUser, Codigo);
	        }
		}
		
		if(opcion.equals("23")){
			String Etapa = "Etapa 2-4";
			String Codigo = req.getParameter("codInt");
			String codPac = req.getParameter("codPac");
			String cedPac = req.getParameter("cedPac");
			String area = req.getParameter("area");
			String fecha = req.getParameter("fecha");
			String trim = req.getParameter("trim");
			String Seccion = req.getParameter("secciones");
			String codPreg1 = req.getParameter("codPreg1");
			String codPreg2 = req.getParameter("codPreg2");
			String codPreg3 = req.getParameter("codPreg3");
			String Resp1 = req.getParameter("Resp1");
			String Resp2 = req.getParameter("Resp2");
			String Resp3 = req.getParameter("Resp3");
			String codUser = req.getParameter("codUser");
			////////////////////////////////////////
			//Seccion 24-2
			////////////////////////////////////////
			String[] Secciones = Seccion.split(",");
			String[] CodPre1 = codPreg1.split(",");
			String[] CodPre2 = codPreg2.split(",");
			String[] CodPre3 = codPreg3.split(",");
			String[] resp1 = Resp1.split(",");
			String[] resp2 = Resp2.split(",");
			String[] resp3 = Resp3.split(",");
			
	        for(int i = 0; i < CodPre1.length; i++){
	        	CPP.Etapa24_Area2(codPac, cedPac, Etapa, area, fecha, 
	        	trim, Secciones[0], CodPre1[i], resp1[i], codUser, Codigo);
	        }
	        for(int i = 0; i < CodPre2.length; i++){
	        	CPP.Etapa24_Area2(codPac, cedPac, Etapa, area, fecha, 
	        	trim, Secciones[1], CodPre2[i], resp2[i], codUser, Codigo);
	        }
	        for(int i = 0; i < CodPre3.length; i++){
	        	CPP.Etapa24_Area2(codPac, cedPac, Etapa, area, fecha, 
	        	trim, Secciones[2], CodPre3[i], resp3[i], codUser, Codigo);
	        }
		}
		
		if(opcion.equals("24")){
			String dias = req.getParameter("Dias");
			String fecha = req.getParameter("fecha");
			try {
				rs=CPP.FechaPartoEco(fecha,dias);
				if(rs.next()){
					String FecPar = rs.getString(1);
					out.print(FecPar);
				}
				rs.getStatement().close();
			} catch (SQLException e) {
				e.getMessage();
			}
		}
		
		if(opcion.equals("25")){
			String fecha = req.getParameter("fecha");
			try {
				rs=CPP.RestFec(fecha);
				if(rs.next()){
					String FecCon = rs.getString(1);
					out.print(FecCon);
				}
				rs.getStatement().close();
			} catch (SQLException e) {
				e.getMessage();
			}
		}
		
		if(opcion.equals("menu")){
			String codigo = req.getParameter("codigo");
			try {
				out.print("<table width='100%'border='0'cellspacing='0'><tr><td width='15%'><div id='MenuVertical'><div id='button'><ul>");
				rs=CPP.VerificarPermisosHC(codigo);
				if(rs.next()){
					rst=CPP.VerificarPermisosHC(codigo);
					while(rst.next()){
						if(rst.getString(1).equals("1")){
							//Permiso de Antecedentes
							out.print("<li><a href='#' onclick='Antecedentes()'>Antecedentes</a></li>");
						}
						if(rst.getString(1).equals("6")){
							//Permiso de Imagenologia
							out.print("<li><a href='#' onclick='MostrarImagenologia()'>Imagenologia</a></li>");
						}
						if(rst.getString(1).equals("8")){
							//Permiso de Atenciones Anteriores
							out.print("<li><a href='#' onclick='MostrarAtenciones()'>Atenciones Anteriores</a></li>");
						}
					}
					//Volver a las preguntas de la encuesta
					out.print("<li><a href='#' onclick='CargarPreguntas()'>Encuesta</a></li>");
					rst.getStatement().close();
				}else{
					out.print("NO TIENE NINGUN PERMISO AUTORIZADO.");
				}
				rs.getStatement().close();
				out.print("</ul>");
				out.print("</div></div></td></tr></table>");
			} catch (SQLException e) {
				System.out.println("Error en ControlMultiplepaciente valor = menu "+e);
				e.printStackTrace();
			}
		}
		
	}
}
















