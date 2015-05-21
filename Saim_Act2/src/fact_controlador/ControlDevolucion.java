package fact_controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fact_metodo.MetodoDevolucion;


public class ControlDevolucion  extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(request, response);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		HttpSession session = req.getSession(true);
		
		String va = req.getParameter("valor");
		String venc=req.getParameter("venc");
		
		
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
		MetodoDevolucion md = new MetodoDevolucion();
		
		String formatomoneda="$###,###,###"; //declarando la variable con un tipo de formato
		DecimalFormat df=new DecimalFormat(formatomoneda); //aplicando el formato a la variable formato_moneda
		
		
		
		//INICIO PROCESO DE DEVOLUCION 
		
		
		if(va.equals("00D")){
			
			out.print("<table width='100%'  class='style6' border='0'><tr>");
			out.print("<td colspan='5'><div align='center' class='style11' id='cabecera2'>Seleccione Una Opcion </div></td></tr>");
			out.print("<tr>"
					+ "<td><label><input name='radiobutton' type='radio' value='radiobutton' id='sfrd' onclick='OpcionEnvioFact()' />Seleccionar Facturas a Devolver</label></td>");
			out.print("<td><label><input name='radiobutton' type='radio' value='radiobutton' id='cfrd' onclick='OpcionEnvioFact()' />Listado de Facturas Devueltas</label></td>"
					+"<td><label><input name='radiobutton' type='radio' value='radiobutton' id='dfra' onclick='OpcionEnvioFact()' />Devolver Facturas Radicadas a Facturada</label></td>" 
					+"</tr> "
					+ "");
			out.print("<tr><td colspan='5'><div id='Opcion'></div></td></tr>");
			out.print("</table>");
			
		}
		
		if(va.equals("dfra")){
			out.print("<table width='100%'  class='style6' border='0'><tr>");
			out.print("<td colspan='5'>"
					+ "<div align='center' class='style11' id='cabecera2'>DEVOLVER FACTURAS A ESTADO DE EMISION</div>"
					+ "</td></tr>");
			out.print("<tr>"
					+ "<td><label>Numero de Factura: <input type='text' id='txtNumFactura' /></label></td>");
			out.print("<td><label>Motivo:<select id='cmbMotivoDev'><option value='Seleccione'>Seleccione</option> ");
			rs=md.BuscarMotivo();
			try {
				while(rs.next()){
				out.print("<option value="+rs.getString("codigo")+" >"+rs.getString("descripcion")+"</option>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.print("</select></label></td>"
					+"<td><label><input type='button' value='     Devolver     ' onclick='DevolverFacturaRadEmi()'></label></td>" 
					+"</tr> "
					+ "");
			out.print("<tr><td colspan='5'><div id='Opcion2'></div></td></tr>");
			out.print("</table>");
		}
		if(va.equals("DFRE")){
			try {
				String numeroFactura=req.getParameter("numeroFactura");
				String Motivo=req.getParameter("Motivo");
				String txtCodusuario=req.getParameter("txtCodusuario");
				String NombreMotivo=req.getParameter("NombreMotivo");
				String CodNumerada="";
				String CodRadicacion="";
				String CodEnvio="";
				String ValorNuevoCtaRadicacion="";
				String ValorNuevoCtaEnvio="";
				String ValorLetrasCtaRadicacion="";
				String ValorLetrasCtaEnvio="";
				//obtener codigo de fact_numerada
				rs=md.BuscarCodigoNumerada(numeroFactura);			
				if(rs.next()){
					CodNumerada=rs.getString("cod_fact_numerada");					
					//consultar 
					rs1=md.BuscarDetalleFacturaradicada(CodNumerada);
					if(rs1.next()){
						CodRadicacion=rs1.getString("consRadicado");
						ValorNuevoCtaRadicacion=rs1.getString("NuevoValorRadicacion");
						ValorLetrasCtaRadicacion=Convertir(ValorNuevoCtaRadicacion);
						//1. fact_facturas_radicadas:eliminar registro, obtener codigo de radicacion
						md.EliminarFacturaCuentaRadicacion(CodNumerada);
						//2. fact_factradicadas: actualizar el valor Y valor en letras del codigo obtenido.						
						md.ActualizarDatosCtaRadicacion(CodRadicacion, ValorNuevoCtaRadicacion, ValorLetrasCtaRadicacion);
						/**************************************************************************************/
						rs2=md.BuscarDetalleFacturaEnvio(CodNumerada);
						if(rs2.next()){
							CodEnvio=rs2.getString("consEnvio");
							ValorNuevoCtaEnvio=rs2.getString("NuevoValorEnvio");
							ValorLetrasCtaEnvio=Convertir(ValorNuevoCtaEnvio);
							//3. fact_facturas_enviadas: eliminar registro de la factura, obtener numero de envio.
							md.EliminarFacturaCuentaEnvio(CodNumerada);
							//4. fact_factenviadas: actualizar el valor Y valor en letras del codigo obtenido.							
							md.ActualizarDatosCtaEnvio(CodEnvio, ValorNuevoCtaEnvio, ValorLetrasCtaEnvio);
							//5. fact_factdev: ingresar y obtener el codigo principal
							String ValorFactura=rs2.getString("ValorFactura");
							String ValorNc=rs2.getString("valornc");
							Calendar calendario = Calendar.getInstance();
							int dia0, mes0, anio0;
							dia0 =calendario.get(Calendar.DAY_OF_MONTH);
							mes0 = calendario.get(Calendar.MONTH)+1;
							anio0 = calendario.get(Calendar.YEAR);
							String fecha= anio0+"-"+mes0+"-"+dia0;
							md.CrearDevolucion(ValorFactura, Convertir(ValorFactura), txtCodusuario, fecha, Motivo);
							//5.1 consultar el codigo de la devolucion
							rs3=md.ConsultarCodigoDevolucion(ValorFactura, Convertir(ValorFactura), txtCodusuario, fecha, Motivo);
							if(rs3.next()){
								//6. fact_facturas_devueltas: ingresar registro, codigo de devolucion 
								md.asignarCDevFact(rs3.getString("consDev"), CodNumerada, fecha, ValorNc);
								md.recordEstadoFactDEV(CodNumerada, "4", fecha, txtCodusuario, NombreMotivo, rs3.getString("consDev"));
								//7. FACT_NUMERADAS: SE CAMBIA EL ESTADO A 0 FACTURADA
								md.ActualizarEstadoFactura(CodNumerada);
								out.print("Factura "+numeroFactura+" devuelta exitosamente.");
							}else{
								System.out.print("no se creo el encabezado de devolucion");
							}
						}else{
							out.print("La factura "+numeroFactura+" no existe en ninguna cuenta de envio.");
						}
						rs2.getStatement().getConnection().close();
						/*************************************************************************************/
					}else{
						out.print("La factura "+numeroFactura+" no existe en ninguna cuenta de radicacion.");
					}
					rs1.getStatement().getConnection().close();
				}else{
					out.print("La factura "+numeroFactura+" no existe.");
				}
				
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
				if(va.equals("cfrd")){
					String opcion = req.getParameter("opcion");
					System.out.println("Valor de opcion en cfrd "+opcion);
					//System.out.println("La idea es  consultar las facturas");
					out.print("<table width='100%' border='0' align='center'><tr><td colspan='4' height='30' valign='top'><div align='center' class='style11' id='cabecera2'>Consulta de Facturas Devueltas </div></td></tr></table>");
					
					//out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='60%'><i><div align='center'>Paciente</div></i></td><td width='30%'><i><div align='center'>Empresa</div></i></td><td width='10%'><i><div align='center'>Admisi�n</div></i></td></tr>");  //
					out.print("<table width='100%' border=0' class='style6' ><tr BGCOLOR='#D3D3D3' ><td colspan='4' height='30' valign='top'><i><div align='center'>Criterios de Busqueda</div></i></td></tr>");
					//out.print("<tr></tr><tr><td width='15%'><div align='right'>Cuenta de Cobro No:</div></td><td width='30%'><input name='txtCdC' type='text' id='txtCdC' size='39' maxlength='100' /></td></tr>");
					out.print("<tr></tr><tr><td width='15%'><div align='right'>Factura No:</div></td><td width='30%'><input name='txtFact' type='text' id='txtFact' size='39' maxlength='100' /></td>");
					out.print("<td width='15%'><div align='right'>Entidad:</div></td><td width='40%'><select  style='width:269px' name='Ent' id='Ent' ><option value='Seleccione'>Seleccione</option>");
					rs2=md.Empresas();
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
					rs2=md.TipoDoc();
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
					out.print("<tr><td><div align='right'>Numero de Devolucion:</div></td><td><input name='CDC' type='text' id='CDC' size='15'   /></td><td></td><td></td></tr><tr></tr></table>");
					
					//out.print("<tr><td><div align='right'>Primer Apellido:</div></td><td><input name='txtCodigoPrestador' type='text' id='txtCodPrestador' size='39' maxlength='50' onkeydown='A(this, event)'/><span class='style8'>*</span></td><td><div align='right'>Segundo Apellido:</div></td><td><select name='listEstado' id='listEstado' style='width: 266px;' onkeydown='A(this, event)'><option value='Activo' selected='selected'>Activo</option><option value='Inactivo'>Inactivo</option></select><span class='style8'>*</span></td></tr></table>");
					out.print("<table width='100%' border='0' class='style6' ><tr><td colspan='4'><div align='center'><input name='btnCrearEntidad' type='button' id='btnCrearEntidad' value='     Consultar     ' onclick='BuscarCuentaDevolucion(1)' /></div></td></tr><tr></tr><tr></tr></table>");
					out.print("<table width='100%' border='0' class='style6' ><tr></tr><tr BGCOLOR='#D3D3D3' ><td colspan='4' height='30' valign='top'><i><div align='center'>Resultados de Busqueda</div></i></td></tr><tr></tr></table>");
					out.print("<div id='resultadosf' style='height: 200px; overflow-y: scroll'></div>");
						
				}
				
				
				if(va.equals("sfrd")){
					
					out.print("<table width='100%' border='0' align='center'><tr><td colspan='4' height='30' valign='top'><div align='center' class='style11' id='cabecera2'>Consulta de Facturas a Devolver</div></td></tr></table>");
					
					//out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='60%'><i><div align='center'>Paciente</div></i></td><td width='30%'><i><div align='center'>Empresa</div></i></td><td width='10%'><i><div align='center'>Admisi�n</div></i></td></tr>");  //
					out.print("<table width='100%' border=0' class='style6' ><tr BGCOLOR='#D3D3D3' ><td colspan='4' height='30' valign='top'><i><div align='center'>Criterios de Busqueda</div></i></td></tr>");
					out.print("<tr></tr><tr><td width='15%'><div align='right'>Factura No:</div></td><td width='30%'><input name='txtFact' type='text' id='txtFact' size='39' maxlength='100' /></td>");
					out.print("<td width='15%'><div align='right'>Entidad:</div></td><td width='40%'><select  style='width:269px' name='Ent' id='Ent' ><option value='Seleccione'>Seleccione</option>");
					rs2=md.Empresas();
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
					rs2=md.TipoDoc();
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
					System.out.println("entrando a sfrd donde se muestar el formulario para escoger porque tipo de dato voy a consultar");
					out.print(" Numero:<input name='txtDoc' type='text' id='txtDoc'  size='22' maxlength='50' /></td><td><div align='right'>Nombres:</div></td><td><input name='txtNom' type='text' id='txtNom' size='39' maxlength='100'/ ></td></tr>");
					out.print("<tr><td><div align='right'>Primer Apellido:</div></td><td><input name='txtPa' type='text' id='txtPa'  size='39' maxlength='50' /></td><td><div align='right'>Segundo Apellido:</div></td><td><input name='txtSa' type='text' id='txtSa'  size='39' maxlength='100'/ ></td></tr>");
					out.print("<tr><td><div align='right'>Fecha Inicial:</div></td><td><input name='FI' type='text' id='FI' size='10'  onKeyup='masca(this,patron,true,0,0,0)' onblur='vfi()'; /></td><td><div align='right'>Fecha Final:</div></td><td><input name='FF' type='text' id='FF' size='10' onKeyup='masca(this,patron,true,0,0,0)' onblur='vff()'; /></td></tr><tr></tr>");
					out.print("<tr><td><div align='right'>Cuenta de Cobro:</div></td><td><input name='CDC' type='text' id='CDC' size='15'   /></td><td></td><td></td></tr><tr></tr></table>");
					
					//out.print("<tr><td><div align='right'>Primer Apellido:</div></td><td><input name='txtCodigoPrestador' type='text' id='txtCodPrestador' size='39' maxlength='50' onkeydown='A(this, event)'/><span class='style8'>*</span></td><td><div align='right'>Segundo Apellido:</div></td><td><select name='listEstado' id='listEstado' style='width: 266px;' onkeydown='A(this, event)'><option value='Activo' selected='selected'>Activo</option><option value='Inactivo'>Inactivo</option></select><span class='style8'>*</span></td></tr></table>");
					out.print("<table width='100%' border='0' class='style6' ><tr><td colspan='4'><div align='center'><input name='btnCrearEntidad' type='button' id='btnCrearEntidad' value='     Consultar Facturas    'onclick='BuscarFactDev(1)' /></div></td></tr><tr></tr><tr></tr></table>");
					out.print("<table width='100%' border='0' class='style6' ><tr></tr><tr BGCOLOR='#D3D3D3' ><td colspan='4' height='30' valign='top'><i><div align='center'>Resultados de Busqueda</div></i></td></tr><tr></tr></table>");
					out.print("<div id='resultadosf' style='height: 200px; overflow-y: scroll'></div>");
					out.print("<div id='btnEnvFact' align='center'></div>");	
				}

		
				if(va.equals("showFactD")){	
					String ri=req.getParameter("ri");
				
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
					//System.out.println(v10);
					int verif=0;
					String [] detv10= v10.split("CTA0000");
					for(int i=0;i<detv10.length;i++){
						if(detv10[i].equals("")){
							
						}else{
						sql=sql+"  AND ffe.consEnvio='"+detv10[i]+"' ";
						verif=1;
						}
						
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
					//System.out.println("se utiliza la consulta factest168 para listar las factura que se encuentran en este estado");
					int crs=0;		
					if(verif==1){
						rs2=md.Fact2Est168(sql);
					}else{
					rs2=md.FactEst168(sql);
					}
					try {
						while(rs2.next()){
							if(crs==0){
								out.print("<table width='50%'>");
								out.print("<tr><td colspan='12'> Motivo : <select name='motivo' id='motivo' style='width:80%'  ><option value='seleccione'>Seleccione</option>");
								rs3=md.BuscarMotivo(); 
								while(rs3.next()){
									out.print("<option value='"+rs3.getString(1)+"' >"+rs3.getString(2)+"</option>");
								}rs3.getStatement().getConnection().close();
								out.print("</select></td></tr></table>");
								out.print("<table id='listFact' width='100%' border='0' class='style6'  style='font-size: 13px'>" +
										"<tr BGCOLOR='#D3D3D3'  style='font-weight: bold'>" +
										  		"<td width='3%' align='center'><i>Item</i></td>" +
										  		"<td width='9%' align='center'><i>No Factura</i></td>" +
										  		"<td width='20%' align='center'><i>Nombres y Apellidos</i></td>" +
										  		"<td width='9%' align='center'><i>Identificacion</i></td>" +
										  		"<td width='8%' align='center'><i>F.Ingreso</i></td>" +
										  		"<td width='8%' align='center'><i>F.Egreso</i></td>" +
										  		"<td width='10%' align='center'><i>Valor</i></td>" +
										  		"<td width='8%' align='center'><i>Valor Nota credito</i></td>" +
										  		"<td width='10%' align='center'><i>Valor Total Factura</i></td>" +
										  		"<td width='15%' align='center'><i>Entidad</i></td>" +
										  		"<td width='8%' align='center'><i>Estado</i></td>" +
										  		"<td width='8%' align='center'><i>Observaciones</i></td>" +
										  		"<td width='1%' align='center'></td>" +
										  	"</tr>");
							}
							
							if(ri.equals("1")){
								
								String Conce=rs2.getString(2);
								String Est=rs2.getString(10);
								rs3=md.BuscarNotasCredito(rs2.getString(1),Est); 
								long NOTAC=0;
								while(rs3.next()){
									NOTAC=rs3.getLong(1);
								}rs3.getStatement().getConnection().close();
								String notac=Long.toString(NOTAC);
								long vf=rs2.getLong(7);
								long totalfact=(vf-NOTAC);
								String totalf=Long.toString(totalfact);
								out.print("<tr>" +
										  	"<td width='3%' align='center'>"+(crs+1)+"</td>" +
										  	"<td width='9%' align='left'>"+rs2.getString(2)+"</td>" +
										  	"<td width='20%' align='left'>"+rs2.getString(3)+"</td>" +
										  	"<td width='9%' align='left'>"+rs2.getString(4)+"</td>" +
										  	"<td width='8%' align='left'>"+rs2.getString(5)+"</td>" +
										  	"<td width='8%' align='left'>"+rs2.getString(6)+"</td>" +
										  	"<td width='10%' align='left'>"+rs2.getString(7)+"</td>" +
										  	"<td width='8%' align='left' id='NC"+crs+"'>"+NOTAC+"</td>" +
											"<td width='10%' align='left' id='vEF"+crs+"'>"+totalf+"</td>" +
										  	"<td width='15%' align='center' id='ent"+crs+"'>"+rs2.getString(8)+"</td>" +
										  	"<td width='8%' align='center'> "); 
											if(rs2.getString(10).equals("1")){
												out.print(" Enviada </td>");
											}else{
												if(rs2.getString(10).equals("6")){
													out.print("En Proceso Auditoria Interna </td> ");
												}else{
													if(rs2.getString(10).equals("8")){
														out.print(" Radicada Internamente </td>");
													}else{
														if(rs2.getString(10).equals("2")){
															out.print(" Radicada </td>");
														}
													}
												}
											}
										  out.print("<td width='8%' align='center' ><input name='frad' type='text' id='frad"+crs+"'  size='20'  /></td>" +
										 			  	
										  	"<td width='1%' align='center'><input id='chkEF"+crs+"' type='checkbox' value='"+rs2.getString(1)+"' /></td>" +
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

		
					if(va.equals("devFact")){
						String frad=req.getParameter("frad");
						String motivo=req.getParameter("frad");
						String ri=req.getParameter("ri");
						String motivo2=req.getParameter("motivo");
						//System.out.print(frad+" nueva "+fradn);
						//System.out.println("Motivo"+motivo);
						String FactParaEnviar=req.getParameter("facturas");
						//System.out.println("FactParaEnviar "+FactParaEnviar);
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
						
						md.asignarCuentaDev(totalFacturas, totalLetrasFacturas, usuario, fecha, motivo2, ri);
						
						rs=md.obtenerConsecutivoCR("2");
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
						//String[] Vfrad = frad.split("_");
						String[] detvalornc=valornc.split("_");
						String[] detMotivo=motivo.split("_");
						for (int i=0;i<detFactParaEnviar.length;i++){
							
						
							rs2=md.BuscarEstadoFact(detFactParaEnviar[i]);
							int EstFact=0;
							try {
								while(rs2.next()){
									EstFact=rs2.getInt(1);
								}rs2.getStatement().getConnection().close();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							String Est=Integer.toString(EstFact);
							if(ri.equals("1")){	
									
								if((EstFact==1)||(EstFact==6)){
										md.asignarCDevFact(consecutivoCC, detFactParaEnviar[i],fecha,detvalornc[i]); //ingresa datos en la tabla de fact_factdev
										md.actualizarEstFactR(detFactParaEnviar[i],"3");
										if(motivo.equals("0")){
										md.recordEstadoFactDEV(detFactParaEnviar[i], "4", fecha,usuario,"",consecutivoCC);
										}else{
										md.recordEstadoFactDEV(detFactParaEnviar[i], "4", fecha,usuario,detMotivo[i],consecutivoCC);
										}
										if(EstFact==1){
												rs2=md.BuscarCtaE(detFactParaEnviar[i]);
												try {
													java.util.Date fechaActual = new java.util.Date();
													java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
													java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());
													while(rs2.next()){
														md.registroEliminacion(detFactParaEnviar[i],rs2.getString(2),usuario,Fecha,Hora,"fact_facturas_enviadas");
														md.EliminarRegCta(detFactParaEnviar[i],rs2.getString(2),"1");
														rs3=md.BuscarValoresCtaCobro(rs2.getString(2),"1");
														long valorfact=0;
														long notasc=0;
														int valii=0;
														int vali=0;
															while(rs3.next()){
																
																valorfact=valorfact+(rs3.getLong(1));
																//System.out.println("Valor de fact"+valorfact);
																String fact=rs3.getString(3);
																rs4=md.BuscarNotasCredito(rs3.getString(2),Est);
																valii=1;
																while(rs4.next()){
																	notasc=notasc+(rs4.getLong(1));
																	vali=1;
																	
																}rs4.getStatement().getConnection().close();
																
															}rs3.getStatement().getConnection().close();
															if(valii==1){
																//System.out.println("Valor de Fact antes de"+valorfact);
																//System.out.println("Valor de Notas c "+notasc);
																valorfact=valorfact-notasc;
																//System.out.println("Cuando es valii valor fact es "+valorfact);
																String Vfact=Long.toString(valorfact);
																String Let=Convertir(Vfact);
																System.out.println(Let);
																md.ActualizarCtaCobro(rs2.getString(2),valorfact,Let,"1");
															}
													}rs2.getStatement().getConnection().close();
													
												} catch (SQLException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}	
										}else{
											if(EstFact==6){
												rs2=md.BuscarCtaA(detFactParaEnviar[i]);
												try {
													java.util.Date fechaActual = new java.util.Date();
													java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
													java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());
													while(rs2.next()){
														md.registroEliminacion(detFactParaEnviar[i],rs2.getString(2),usuario,Fecha,Hora,"fact_facturas_auditadas");
														md.EliminarRegCta(detFactParaEnviar[i],rs2.getString(2),"2");
														rs3=md.BuscarValoresCtaCobro(rs2.getString(2),"2");
														long valorfact=0;
														long notasc=0;
														int valii=0;
														int vali=0;
															while(rs3.next()){
																valorfact=valorfact+(rs3.getLong(1));
																String fact=rs3.getString(3);
																rs4=md.BuscarNotasCredito(rs3.getString(2),Est);
																valii=1;
																while(rs4.next()){
																	notasc=notasc+(rs4.getLong(1));
																	vali=1;
																	
																}rs4.getStatement().getConnection().close();
																
															}rs3.getStatement().getConnection().close();
															if(valii==1){
																//System.out.println("Valor de Fact antes de"+valorfact);
																//System.out.println("Valor de Notas c "+notasc);
																valorfact=valorfact-notasc;
																//System.out.println("Cuando es valii valor fact es "+valorfact);
																String Vfact=Long.toString(valorfact);
																String Let=Convertir(Vfact);
																System.out.println(Let);
																md.ActualizarCtaCobro(rs2.getString(2),valorfact,Let,"2");
															}
													}rs2.getStatement().getConnection().close();
													
												} catch (SQLException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}	
												
											}
										}
								}else{
										if(EstFact==8){
											md.asignarCDevFact(consecutivoCC, detFactParaEnviar[i],fecha,detvalornc[i]); //ingresa datos en la tabla de fact_factdev
											md.actualizarEstFactR(detFactParaEnviar[i],"2");
											if(motivo.equals("0")){
											md.recordEstadoFactDEV(detFactParaEnviar[i], "4", fecha,usuario,"",consecutivoCC);
											}else{
											md.recordEstadoFactDEV(detFactParaEnviar[i], "4", fecha,usuario,detMotivo[i],consecutivoCC);
											}
											rs2=md.BuscarCtaA(detFactParaEnviar[i]);
											try {
												java.util.Date fechaActual = new java.util.Date();
												java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
												java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());
												while(rs2.next()){
													md.registroEliminacion(detFactParaEnviar[i],rs2.getString(2),usuario,Fecha,Hora,"fact_facturas_radicadasi");
													md.EliminarRegCta(detFactParaEnviar[i],rs2.getString(2),"3");
													rs3=md.BuscarValoresCtaCobro(rs2.getString(2),"3");
													long valorfact=0;
													long notasc=0;
													int valii=0;
													int vali=0;
														while(rs3.next()){
															valorfact=valorfact+(rs3.getLong(1));
															String fact=rs3.getString(3);
															rs4=md.BuscarNotasCredito(rs3.getString(2),Est);
															valii=1;
															while(rs4.next()){
																notasc=notasc+(rs4.getLong(1));
																vali=1;
																
															}rs4.getStatement().getConnection().close();
															
														}rs3.getStatement().getConnection().close();
														if(valii==1){
															//System.out.println("Valor de Fact antes de"+valorfact);
															//System.out.println("Valor de Notas c "+notasc);
															valorfact=valorfact-notasc;
															//System.out.println("Cuando es valii valor fact es "+valorfact);
															String Vfact=Long.toString(valorfact);
															String Let=Convertir(Vfact);
															System.out.println(Let);
															md.ActualizarCtaCobro(rs2.getString(2),valorfact,Let,"3");
														}
												}rs2.getStatement().getConnection().close();
												
											} catch (SQLException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}	
											
										}
									
								}
						}
					}
				}
		

		
				if(va.equals("showCuentaDev")){	
						
						String ri = req.getParameter("ri");
						System.out.println("showCuentaDev"+1);
						String sql="";
						
						
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
						
						System.out.println(v10);
						String [] detv10= v10.split("DEV0000");
						for(int i=0;i<detv10.length;i++){
							if(detv10[i].equals("")){
								
							}else{
							sql=sql+"  AND ffd.consDev='"+detv10[i]+"' ";
							
							}
							
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
						
						sql=sql+" AND ffd.fecha BETWEEN '"+fv8+"' AND '"+fv9+"' ";}
						 
						
						int crs=0;		
						rs2=md.GeneraSQLCtaFactDev(sql,ri); 
						try {
							while(rs2.next()){
								if(ri.equals("1")){
								if(crs==0){
									out.print("<table id='listFact' width='100%' border='0' class='style6'  style='font-size: 13px'>" +
											  	"<tr BGCOLOR='#D3D3D3'  style='font-weight: bold'>"+
											  		"<td width='3%' align='center'><i>Item</i></td>"+
											  		"<td width='8%' align='center'><i>Consecutivo</i></td>"+
											  		"<td width='10%' align='center'><i>Valor Dev</i></td>"+
											  		"<td width='10%' align='center'><i>Fecha de Devolucion</i></td>"+
											  		"<td width='30%' align='center'><i>Entidad</i></td>"+
											  		"<td width='22%' align='center'><i>Usuario</i></td>"+
											  		"<td width='40%' align='center'><i>Motivo</i></td>"+
											  	"</tr>");
								}
								
							out.print("<tr> " +
										  "<td width='3%' align='center'>"+(crs+1)+"</td>" +
										  "<td width='8%' align='left'><a href='#' onclick='mostrarDetDev(&quot;"+rs2.getString(7)+"&quot;,0)'>"+rs2.getString(1)+"</a></td>" +
										  "<td width='10%' align='right'>"+md.formatMoneda(rs2.getString(2))+"</td>" +
										  "<td width='10%' align='center'>"+rs2.getString(3)+"</td>" +
										  "<td width='30%' align='left'>"+rs2.getString(4)+"</td>" +
										  "<td width='22%' align='left'>"+rs2.getString(5)+"</td>" +
										  "<td width='40%' align='left'>"+rs2.getString(6)+"</td>" + 
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
		

				if(va.equals("reportedev")){
					String cons=req.getParameter("cons");
					
					//int vali=0;
					
					rs=md.reportedev(cons);
					rs1=md.reportedev(cons);
					
					//out.println("Valor de fent -->"+ftent);
					out.println("<table border=1 class=contpre>"); 
					out.println("<tr><b><td>No. Factura </td><td><br>Empresa </td><td><br>Nombre Paciente </td><td><br>Valor</td><td><br>Motivo de Devolucion</td><td><br>Fecha de Devolucion</td><td><br>Usuario</td></tr> ");
					int co=0;
					int cont=0;
					String t="";
					try {
						while(rs.next()){
							
							out.println("<tr><td>"+rs.getString(2)+"</td>");
							out.println("<td>"+rs.getString(4)+"</td>");
							out.println("<td>"+rs.getString(5)+"</td>");
							out.println("<td>"+formatMoneda(rs.getInt(3)+"")+"</td>");
							if (co==0){
								while(rs1.next()){
									cont++;
								}
								rs1.getStatement().getConnection().close();
								out.println("<td rowspan="+cont+">"+rs.getString(6)+"</td>");
							co=1;}	
							out.println("<td>"+rs.getString(8)+"</td>");
							out.println("<td>"+rs.getString(7)+"</td>");
							System.out.println(cont);
						}
					
						
						rs.getStatement().getConnection().close();
						
					
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//		out.println("</table>");
					
					//out.println("Valor Total de Nota "+formatMoneda(ValorNota+""));
					//out.println("Valor Total de Facturas"+formatMoneda(ValorFact+""));
					out.println("</tr></table>");
					
				}

	///FINAL DEL PROCESO DE DEVOLUCION 
		
	}
	
	
	/////////////
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
