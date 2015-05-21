package ord_controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ord_metodo.MetodoDescOC;


public class ControlDescOC  extends HttpServlet{
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String va = req.getParameter("valor");
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out;
		out=res.getWriter();
		MetodoDescOC md=new MetodoDescOC();
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		ResultSet rs3=null;
		ResultSet rs4=null;
		ResultSet rsp=null;
		ResultSet rsl=null;
		ResultSet rsa=null;
		String user=req.getParameter("user");
		java.util.Date fechaS = new Date();
		Calendar c = new GregorianCalendar();
		String diaa = Integer.toString(c.get(Calendar.DATE));
		String mess = Integer.toString(c.get(Calendar.MONTH));
		String annio = Integer.toString(c.get(Calendar.YEAR));
		
		if(va.equals("VerDescOC")){
			//Interfaz para escogencia del tipo de Descargue
			out.println(" <table width='80%'> ");
			out.println("<tr> ");
			out.println("<td> Tipo de descargue : </td> ");
			out.println("<td> "+
				"<select id='tid' style='width:80%' onchange='Tdescargue()' > "+
					" <option value='0' > ------ </option> "+
					" <option value='1' > MANUAL </option> "+
					" <option value='2' > PRECARGADO </option> "+
					" </select> "+
					"</td> "+
					"</tr>	"+			
					"</table> ");
		}
		
		if(va.equals("DManual")){
			
			out.println("<table>");
			out.println("<tr><td>Seleccione el proveedor : </td><td><input type='text' id='prove' onkeyup='AutoProveOrden()' class='rep' size='30'/></td><td>Digite Factura :</td><td><input type='text' id='nofactura' class='rep' size='20' /></td><td>Autorizado Por:</td><td><input type='text' id='Resp' size='25' class='rep'/></td>");
			out.println("<td colspan='2' align='center'><input type='button' id='BtnBuscar'  onclick='BtnBuscar()' value='Buscar' /></td></tr>");
			out.println("<tr><td><input type='hidden' id='codprove' /></td><td><div id='vprov' ></div></td></tr>");
			out.println("</table>");
			
		}
		
		if(va.equals("Tipos")){
			
			String codprove=req.getParameter("codprove");
			String nofactura=req.getParameter("nofact");
			System.out.println(nofactura);
			String Resp=req.getParameter("resp");
			rs=md.BusFact(codprove, nofactura);
			String verif="";
			String consecOC="";
			try {
				if(rs.next()){
					consecOC=rs.getString(10);
					verif="1";
				}rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(verif.equals("")){
			out.println("<table width='100%'>");
			rs1=md.BusProveOrd(codprove);
			try {
				if(rs1.next()){
					out.println("<tr><td><b>Nombre del Proveedor :</b></td><td>"+rs1.getString(3)+"</td><td><b>Direccion:</b></td><td>"+rs1.getString(6)+"</td><td><b>Telefono:</b></td><td>"+rs1.getString(7)+"</td></tr>");
				}rs1.getStatement().getConnection().close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			out.println("<tr><td><b>Seleccione un Tipo de Producto:</b></td><td><select id='tip' onchange='tip()' class='rep'>"+
						"<option value='no' >---</option>");
			rs=md.BuscarTipos();
			try {
				while(rs.next()){
					if((rs.getString(2).equals("SERVICIOS"))||(rs.getString(2).equals("OTROS"))){ //este condicional es provisional mientras se hace la interfaz de lo demas
					out.println("<option value='"+rs.getString(1)+"'>"+rs.getString(2)+"</option>");
					}
				}rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.println("</select>");
			out.println("<input type='hidden' id='cprove' value='"+codprove+"' /><input type='hidden'  id='nfact' value='"+nofactura+"' /><input type='hidden' id='resp' value='"+Resp+"' /></td></tr>");
			out.println("</table>");
			}else{
				out.println(verif+"_"+consecOC);
			}
		}
		
		
		if(va.equals("IManual")){
			/*Interfaz de cargue manual  de servicios, productos y medicamentos **/
			String tip=req.getParameter("tip");
			String codprove=req.getParameter("codprove");
			String NoFactura=req.getParameter("Nofactura");
			String resp=req.getParameter("resp");
			int verif=0;
			rs=md.BusDescTipo(tip);
			try {
				if(rs.next()){
					if((rs.getString(2).contains("SERVICIO"))||(rs.getString(2).contains("OTRO"))){
						verif=1;//Cuando los productos son de tipo servicio u otros, estos productos no se deben llevar a inventario, no son contables. 
						//System.out.println("prueba "+rs.getString(2));
					}else{
						if(rs.getString(2).contains("MEDIC")){
							verif=2;//Cuando los productos son de tipo medicamentos o dispositivos medicos, es decir de tipo 1 y 2 segun la tabla ord_tipoproducto
						}else{
							 verif=3;//Productos de otra topologia que pueden ser contados(Equipos de oficina). 
						}
					}
					
				}rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(verif==1){
				//Para servicios y otros, cosas q no se puedan contar 
				
				out.println("<table width='100%' >");
				rs1=md.BusProveOrd(codprove);
				try {
					if(rs1.next()){
						out.println("<tr><td><b>Nombre del Proveedor :</b></td><td>"+rs1.getString(3)+"</td><td><b>Direccion:</b></td><td>"+rs1.getString(6)+"</td><td><b>Telefono:</b></td><td>"+rs1.getString(7)+"</td></tr>");
					}rs1.getStatement().getConnection().close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				out.println("<tr><td>Autorizado por : </td><td>"+resp+"</td><td>No. de Cta de Cobro ó Factura </td><td>"+NoFactura+"</td><td>Observacion: </td><td><input type='text' id='obs' style='width:100%' /></td></tr>");
				out.println("<tr >");
				out.println("<td colspan='6'>");
				out.println("<table>");
						out.println("<tr><td>Sucursal : </td><td><select id='suc' onchange='suc()' class='rep' ><option>---</option>");
						rs2=md.BuscarSucursal();
						try {
							while(rs2.next()){
								out.println("<option value='"+rs2.getString(1)+"'>"+rs2.getString(2)+"</option>");
								
							}rs2.getStatement().getConnection().close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						out.println("</select>");
						out.println("<input type='hidden' id='codsuc' /><input type='hidden' id='codsubc' /></td>");
						out.println("<td><div id='centrocosto' ></div><input type='hidden' id='codcc' /><input type='hidden' id='codsucycc' /></td>");
						out.println("<td><div id='succosto' ></div><input type='hidden' id='codcentrosubc' /></td>");
				out.println("</tr>");		
				out.println("</table>");
				
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td colspan='6' align='center' class='contpre'><br>");
					
					out.println("<table width='100%' >");
						out.println("<tr>");
							out.println("<td>");
								out.println("Cod. Referencia");
							out.println("</td>");
							out.println("<td>");
								out.println("Descripcion");
							out.println("</td>");
							out.println("<td>");
								out.println("Valor");
							out.println("</td>");
							out.println("<td>");
								out.println("Descuento");
							out.println("</td>");
							out.println("<td>");
								out.println("IVA");
							out.println("</td>");
							out.println("<td>");
								out.println("Total");
							out.println("</td>");
							out.println("<td>");
							out.println("</td>");
						out.println("</tr>");//7
						out.println("<tr>");
							out.println("<td>");
								out.println("<input type='text' id='codref' style='width:100%' onkeyup='AutoProdu("+tip+",1,"+codprove+")' class='rep'/>");
							out.println("</td>");
							out.println("<td>");
								out.println("<input type='text' id='descrip' style='width:100%' onkeyup='AutoProdu("+tip+",2,"+codprove+")'  class='rep'/>");
							out.println("</td>");
							out.println("<td>");
								out.println("<input type='text' id='valor' style='width:100%'  onblur='checknum(1)' class='rep' />");
							out.println("</td>");
							out.println("<td>");
								out.println("<input type='text' id='desc' style='width:100%'  onblur='checknum(2)' value='0'  class='rep'/>");
							out.println("</td>");
							out.println("<td>");
							out.println("<input type='text' id='viva' style='width:100%' onkeyup='BuscarIva()' class='rep'  />");
							out.println("</td>");
							out.println("<td>");
							out.println("<input type='text' id='total' style='width:100%' class='rep' disabled /><input type='hidden' id='verif' /><input type='hidden' id='resp' value='"+resp+"' />");
							out.println("</td>");
							out.println("<td>");
								out.println("<input type='button' id='btnmanu' onclick='btnmanual("+tip+","+codprove+",1)' value='+' class='rep' /><input type='hidden' id='nofact' value='"+NoFactura+"' ");
							out.println("</td>");
						out.println("</tr>");
						
						out.println("<tr><td><div id='vcref'></div><input type='hidden' id='cprod' /></td>"+
						"<td><div id='vprod' ></div></td><td colspan='2'> </td><td ><div id='veriva' ></div></td><td><input type='hidden' id='civa' /></td></tr>");
						//out.println("<tr><td colspan='7' align='center'><br>DETALLE  DE CARGUE</td></tr>");
						out.println("<tr><td colspan='7'><div id='detcar'></div> </td></tr> ");
						out.println("</table>");
				out.println("</td>");
				out.println("</tr>");
				out.println("</table>");
				
			}else{
				if(verif==2){
					
				}else{
					if(verif==3){
						
					}
				}
			}
			
		}
		
		
	if(va.equals("GCargue")){
		String tip=req.getParameter("tip");
		String codprove=req.getParameter("codprove");
		String e=req.getParameter("e");
		String cprod=req.getParameter("cprod");
		String civa=req.getParameter("civa");
		String codref=req.getParameter("codref");
		String nofact=req.getParameter("nofact");
		String ndescrip=req.getParameter("ndescrip");
		String precio=req.getParameter("precio");
		String desc=req.getParameter("desc");
		String viva=req.getParameter("viva");
		String total=req.getParameter("total");
		String vali=req.getParameter("verif");//verificacion si el codigo es de un producto o una cuenta
		String resp=req.getParameter("resp");
		
		java.util.Date fechaActual = new java.util.Date();
		java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
		java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());
		
		String codigo=req.getParameter("codigo");//codigo oc para cuando e!=1
		
		System.out.println("valor de e"+e+" valor de tip "+tip);
		int verif=0;
		rs=md.BusDescTipo(tip);
	
			try {
				if(rs.next()){
					if((rs.getString(2).contains("SERVICIO"))||(rs.getString(2).contains("OTRO"))){
						verif=1;//Cuando los productos son de tipo servicio u otros, estos productos no se deben llevar a inventario, no son contables. 
						//System.out.println("prueba "+rs.getString(2));
					}else{
						if(rs.getString(2).contains("MEDIC")){
							verif=2;//Cuando los productos son de tipo medicamentos o dispositivos medicos, es decir de tipo 1 y 2 segun la tabla ord_tipoproducto
						}else{
							 verif=3;//Productos de otra topologia que pueden ser contados(Equipos de oficina). 
						}
					}
					
				}rs.getStatement().getConnection().close();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			
			System.out.println("valor de verif"+verif);
		if(e.equals("1")){//primera vez que se graba, por lo tanto hay que hacer el encabezado en ord_orden_compra
			try {
				
				System.out.println("valor de verif"+verif);
				if(verif==1){
					System.out.println("valor de vali"+2);
					if(vali.equals("2")){
						md.CrearProducto(cprod,Fecha,Hora,user);
						rs=md.BuscarCodProd(cprod,Fecha,Hora,user);
						if(rs.next()){
							cprod=rs.getString(1);
						}rs.getStatement().getConnection().close();
					}
					
					md.HacerEnca(tip,codprove,cprod,civa,codref,nofact,ndescrip,precio,desc,viva,total,user,Fecha,Hora);
					rs=md.BusCodigo(tip,codprove,cprod,civa,codref,nofact,ndescrip,precio,desc,viva,total,user,Fecha,Hora);
					String codigooc="";
					if(rs.next()){
						codigooc=rs.getString(1);
					}rs.getStatement().getConnection().close();
					
					out.println("<table width='100%' >");
					rs1=md.BusProveOrd(codprove);
					try {
						if(rs1.next()){
							out.println("<tr><td><b>Nombre del Proveedor :</b></td><td>"+rs1.getString(3)+"</td><td><b>Direccion:</b></td><td>"+rs1.getString(6)+"</td><td><b>Telefono:</b></td><td>"+rs1.getString(7)+"</td></tr>");
						}rs1.getStatement().getConnection().close();
					} catch (SQLException e4) {
						// TODO Auto-generated catch block
						e4.printStackTrace();
					}
					out.println("<tr><td>Autorizado por : </td><td>"+resp+"</td><td>No. de Cta de Cobro ó Factura </td><td>"+nofact+"</td><td>Observacion: </td><td><input type='text' id='obs' style='width:100%' /></td></tr>");
					out.println("<tr >");
					out.println("<td colspan='6'>");
					out.println("<table>");
							out.println("<tr><td>Sucursal : </td><td><select id='suc' onchange='suc()' class='rep' ><option>---</option>");
							rs2=md.BuscarSucursal();
							try {
								while(rs2.next()){
									out.println("<option value='"+rs2.getString(1)+"'>"+rs2.getString(2)+"</option>");
									
								}rs2.getStatement().getConnection().close();
							} catch (SQLException e3) {
								// TODO Auto-generated catch block
								e3.printStackTrace();
							}
							out.println("</select>");
							out.println("<input type='hidden' id='codsuc' /><input type='hidden' id='codsubc' /></td>");
							out.println("<td><div id='centrocosto' ></div><input type='hidden' id='codcc' /><input type='hidden' id='codsucycc' /></td>");
							out.println("<td><div id='succosto' ></div><input type='hidden' id='codcentrosubc' /></td>");
					out.println("</tr>");		
					out.println("</table>");
					
					out.println("</td>");
					out.println("</tr>");
					
					out.println("<tr >");
					out.println("<td colspan='6' align='center' class='contpre'><br>");
						
						out.println("<table width='100%' >");
							out.println("<tr>");
								out.println("<td>");
									out.println("Cod. Referencia");
								out.println("</td>");
								out.println("<td>");
									out.println("Descripcion");
								out.println("</td>");
								out.println("<td>");
									out.println("Valor");
								out.println("</td>");
								out.println("<td>");
									out.println("Descuento");
								out.println("</td>");
								out.println("<td>");
									out.println("IVA");
								out.println("</td>");
								out.println("<td>");
									out.println("Total");
								out.println("</td>");
								out.println("<td>");
								out.println("</td>");
							out.println("</tr>");//7
							out.println("<tr>");
								out.println("<td>");
									out.println("<input type='text' id='codref' style='width:100%' onkeyup='AutoProdu("+tip+",1,"+codprove+")' class='rep'/>");
								out.println("</td>");
								out.println("<td>");
									out.println("<input type='text' id='descrip' style='width:100%' onkeyup='AutoProdu("+tip+",2,"+codprove+")'  class='rep'/>");
								out.println("</td>");
								out.println("<td>");
									out.println("<input type='text' id='valor' style='width:100%'  onblur='checknum(1)' class='rep' />");
								out.println("</td>");
								out.println("<td>");
									out.println("<input type='text' id='desc' style='width:100%'  onblur='checknum(2)' value='0'  class='rep'/>");
								out.println("</td>");
								out.println("<td>");
								out.println("<input type='text' id='viva' style='width:100%' onkeyup='BuscarIva()' class='rep'  />");
								out.println("</td>");
								out.println("<td>");
								out.println("<input type='text' id='total' style='width:100%' class='rep' disabled /><input type='hidden' id='verif' value='"+vali+"' /><input type='hidden' id='resp' value='"+resp+"' />");
								out.println("</td>");
								out.println("<td>");
									out.println("<input type='button' id='btnmanu' onclick='btnmanual("+tip+","+codprove+",2)' value='+' class='rep' /><input type='hidden' id='nofact' value='"+nofact+"' ");
								out.println("</td>");
							out.println("</tr>");
							
							out.println("<tr><td><div id='vcref'></div><input type='hidden' id='cprod' /></td>"+
							"<td><div id='vprod' ></div></td><td colspan='2'> </td><td ><div id='veriva' ></div></td><td><input type='hidden' id='civa' /></td></tr>");
							
							out.println("<tr><td colspan='7'><div id='detcar' width='100%'>");
									out.println("<table width='100%' >");
									out.println("<tr><td colspan='7' align='center'><br>DETALLE  DE CARGUE</td></tr>");
									out.println("<tr>");
									out.println("<td>");
										out.println("Cod. Referencia");
									out.println("</td>");
									out.println("<td>");
										out.println("Descripcion");
									out.println("</td>");
									out.println("<td>");
										out.println("Valor");
									out.println("</td>");
									out.println("<td>");
										out.println("Descuento");
									out.println("</td>");
									out.println("<td>");
										out.println("IVA");
									out.println("</td>");
									out.println("<td>");
										out.println("Total");
									out.println("</td>");
									out.println("<td>Eliminar");
									out.println("</td>");
								out.println("</tr>");//7
								rs1=md.BuscarDetalle(codigooc);
								int i=1;
								while(rs1.next()){
									out.println("<tr>");
									out.println("<td>");
										out.println(rs1.getString(5));
									out.println("</td>");
									out.println("<td>");
										out.println(rs1.getString(6));
									out.println("</td>");
									out.println("<td>");
										out.println(rs1.getString(8));
									out.println("</td>");
									out.println("<td>");
										out.println(rs1.getString(9));
									out.println("</td>");
									out.println("<td>");
										out.println(rs1.getString(11));
									out.println("</td>");
									out.println("<td>");
										out.println(rs1.getString(12));
									out.println("</td>");
									out.println("<td><input  name='rbutton' type='radio'  id='Elim'  onclick='RElim("+i+","+rs1.getString(1)+")' title='Eliminar' />");
									out.println("<input type='hidden' value='"+codigooc+"'  id='codigo' /></td>");
									
									i=i+1;
								}rs1.getStatement().getConnection().close();
								out.println("<tr><td colspan='7' align='center'><input type='button' id='Term' value='Terminar' onclick='Terminar("+codigooc+")' /></td></tr>");
									out.println("</table>");
							out.println("</div></td></tr> ");
							out.println("</table>");
					out.println("</td>");
					out.println("</tr>");
					out.println("</table>");
					
				}else{
					if(verif==2){
						
					}else{
						if(verif==3){
							
						}
					}
					
				}
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else{
			if(verif==1){
				System.out.println("valor de vali"+2);
				if(vali.equals("2")){
					md.CrearProducto(cprod,Fecha,Hora,user);
					rs=md.BuscarCodProd(cprod,Fecha,Hora,user);
					try {
						if(rs.next()){
							cprod=rs.getString(1);
						}rs.getStatement().getConnection().close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				try {
					md.GuardarDetalle(tip,codprove,cprod,civa,codref,nofact,ndescrip,precio,desc,viva,total,user,Fecha,Hora,codigo);
					
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				out.println("<table width='100%' >");
				rs1=md.BusProveOrd(codprove);
				try {
					if(rs1.next()){
						out.println("<tr><td><b>Nombre del Proveedor :</b></td><td>"+rs1.getString(3)+"</td><td><b>Direccion:</b></td><td>"+rs1.getString(6)+"</td><td><b>Telefono:</b></td><td>"+rs1.getString(7)+"</td></tr>");
					}rs1.getStatement().getConnection().close();
				} catch (SQLException e4) {
					// TODO Auto-generated catch block
					e4.printStackTrace();
				}
				out.println("<tr><td>Autorizado por : </td><td>"+resp+"</td><td>No. de Cta de Cobro ó Factura </td><td>"+nofact+"</td><td>Observacion: </td><td><input type='text' id='obs' style='width:100%' /></td></tr>");
				out.println("<tr >");
				out.println("<td colspan='6'>");
				out.println("<table>");
						out.println("<tr><td>Sucursal : </td><td><select id='suc' onchange='suc()' class='rep' ><option>---</option>");
						rs2=md.BuscarSucursal();
						try {
							while(rs2.next()){
								out.println("<option value='"+rs2.getString(1)+"'>"+rs2.getString(2)+"</option>");
								
							}rs2.getStatement().getConnection().close();
						} catch (SQLException e3) {
							// TODO Auto-generated catch block
							e3.printStackTrace();
						}
						out.println("</select>");
						out.println("<input type='hidden' id='codsuc' /><input type='hidden' id='codsubc' /></td>");
						out.println("<td><div id='centrocosto' ></div><input type='hidden' id='codcc' /><input type='hidden' id='codsucycc' /></td>");
						out.println("<td><div id='succosto' ></div><input type='hidden' id='codcentrosubc' /></td>");
				out.println("</tr>");		
				out.println("</table>");
				
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr >");
				out.println("<td colspan='6' align='center' class='contpre'><br>");
					
					out.println("<table width='100%' >");
						out.println("<tr>");
							out.println("<td>");
								out.println("Cod. Referencia");
							out.println("</td>");
							out.println("<td>");
								out.println("Descripcion");
							out.println("</td>");
							out.println("<td>");
								out.println("Valor");
							out.println("</td>");
							out.println("<td>");
								out.println("Descuento");
							out.println("</td>");
							out.println("<td>");
								out.println("IVA");
							out.println("</td>");
							out.println("<td>");
								out.println("Total");
							out.println("</td>");
							out.println("<td>");
							out.println("</td>");
						out.println("</tr>");//7
						out.println("<tr>");
							out.println("<td>");
								out.println("<input type='text' id='codref' style='width:100%' onkeyup='AutoProdu("+tip+",1,"+codprove+")' class='rep'/>");
							out.println("</td>");
							out.println("<td>");
								out.println("<input type='text' id='descrip' style='width:100%' onkeyup='AutoProdu("+tip+",2,"+codprove+")'  class='rep'/>");
							out.println("</td>");
							out.println("<td>");
								out.println("<input type='text' id='valor' style='width:100%'  onblur='checknum(1)' class='rep' />");
							out.println("</td>");
							out.println("<td>");
								out.println("<input type='text' id='desc' style='width:100%'  onblur='checknum(2)' value='0'  class='rep'/>");
							out.println("</td>");
							out.println("<td>");
							out.println("<input type='text' id='viva' style='width:100%' onkeyup='BuscarIva()' class='rep'  />");
							out.println("</td>");
							out.println("<td>");
							out.println("<input type='text' id='total' style='width:100%' class='rep' disabled /><input type='hidden' id='verif' /><input type='hidden' id='resp' value='"+resp+"' />");
							out.println("</td>");
							out.println("<td>");
								out.println("<input type='button' id='btnmanu' onclick='btnmanual("+tip+","+codprove+",2)' value='+' class='rep' /><input type='hidden' id='nofact' value='"+nofact+"' ");
							out.println("</td>");
						out.println("</tr>");
						
						out.println("<tr><td><div id='vcref'></div><input type='hidden' id='cprod' /></td>"+
						"<td><div id='vprod' ></div></td><td colspan='2'> </td><td ><div id='veriva' ></div></td><td><input type='hidden' id='civa' /></td></tr>");
						
						out.println("<tr><td colspan='7'><div id='detcar' width='100%'>");
								out.println("<table width='100%'>");
								out.println("<tr><td colspan='7' align='center'><br>DETALLE  DE CARGUE</td></tr>");
								out.println("<tr>");
								out.println("<td>");
									out.println("Cod. Referencia");
								out.println("</td>");
								out.println("<td>");
									out.println("Descripcion");
								out.println("</td>");
								out.println("<td>");
									out.println("Valor");
								out.println("</td>");
								out.println("<td>");
									out.println("Descuento");
								out.println("</td>");
								out.println("<td>");
									out.println("IVA");
								out.println("</td>");
								out.println("<td>");
									out.println("Total");
								out.println("</td>");
								out.println("<td>Eliminar");
								out.println("</td>");
							out.println("</tr>");//7
							rs1=md.BuscarDetalle(codigo);
							int i=1;
							try {
								while(rs1.next()){
									out.println("<tr>");
									out.println("<td>");
										out.println(rs1.getString(5));
									out.println("</td>");
									out.println("<td>");
										out.println(rs1.getString(6));
									out.println("</td>");
									out.println("<td>");
										out.println(rs1.getString(8));
									out.println("</td>");
									out.println("<td>");
										out.println(rs1.getString(9));
									out.println("</td>");
									out.println("<td>");
										out.println(rs1.getString(11));
									out.println("</td>");
									out.println("<td>");
										out.println(rs1.getString(12));
									out.println("</td>");
									out.println("<td><input  name='rbutton' type='radio'  id='Elim'  onclick='RElim("+i+","+rs1.getString(1)+")' title='Eliminar' />");
									out.println("<input type='hidden' value='"+rs1.getString(1)+"' id='cdet"+i+"'><input type='hidden' value='"+codigo+"'  id='codigo' /></td>");
									
									i=i+1;
								}rs1.getStatement().getConnection().close();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							out.println("<tr><td colspan='7' align='center'><input type='button' id='Term' value='Terminar' onclick='Terminar("+codigo+")' /></td></tr>");
								out.println("</table>");
						out.println("</div></td></tr> ");
						out.println("</table>");
				out.println("</td>");
				out.println("</tr>");
				out.println("</table>");
				
			}else{
				if(verif==2){
					
				}else{
					if(verif==3){
						
					}
				}
			}
		}
		
		
	}
		
		if(va.equals("BusquedaProve")){
			 //System.out.println("BusquedaProve");
				String texto=req.getParameter("texto");
				rs=md.BuscarProve(texto);
				out.println("<table>");
				try {
					while(rs.next()){
						out.println("<tr><td><a class='rep' href='#' onclick='SelProv("+rs.getString(1)+")' >"+rs.getString(2)+" | "+rs.getString(3)+"</a></td></tr>");
					}rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				out.println("</table>");
		}
		
		
		if(va.equals("Prov")){
			
			String cod=req.getParameter("cod");
			//System.out.println("prov"+cod);
			rs=md.BusProveOrd(cod);
			try {
				if(rs.next()){
					//System.out.println(" verificacion ");
					out.println(rs.getString(1)+"_"+rs.getString(3));
				}rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		
		
		if(va.equals("VProd")){
			String tip=req.getParameter("tip");
			String texto=req.getParameter("texto");
			String codprove=req.getParameter("codprove");
			rs=md.BusProducto(tip,texto,codprove);
			int i=1;
			out.println("<table>");
			try {
				while(rs.next()){
					rs1=md.VerifTarifa(codprove,rs.getString(1));
					int val=0;
					while(rs1.next()){
					
						out.println("<tr><td><a href='#' onclick='SelProd("+rs.getString(1)+","+i+",1)' />"+rs.getString(2)+" | "+rs.getString(3)+" - Valor "+rs1.getString(4)+"</a><input type='hidden' id='cref"+i+"' value='"+rs.getString(2)+"' /><input type='hidden' id='nom"+i+"' value='"+rs.getString(3)+"' /><input type='hidden' id='valor"+i+"' value='"+rs1.getString(4)+"' /><input type='hidden' id='vaiva"+i+"' value='"+rs1.getString(10)+"' /><input type='hidden' id='civa"+i+"' value='"+rs1.getString(9)+"' /></td></tr>");
					 val=1;
					 i=i+1;
					}rs1.getStatement().getConnection().close();
					if(val==0){
						out.println("<tr><td><a href='#' onclick='SelProd("+rs.getString(1)+","+i+",1)' />"+rs.getString(2)+" | "+rs.getString(3)+" - Valor $0</a><input type='hidden' id='cref"+i+"' value='"+rs.getString(2)+"' /><input type='hidden' id='nom"+i+"' value='"+rs.getString(3)+"' /><input type='hidden' id='valor"+i+"' value=0 /><input type='hidden' id='vaiva"+i+"' value=0 /><input type='hidden' id='civa"+i+"' value=1 /></td></tr>");
						i=i+1;
					}
					
				}rs.getStatement().getConnection().close();
				rs1=md.BuscarCta(texto);
				while(rs1.next()){
					out.println("<tr><td><a href='#' onclick='SelProd("+rs1.getString(1)+","+i+",2)'>"+rs1.getString(2)+" | "+rs1.getString(3)+"</a><input type='hidden' id='cref"+i+"' value='"+rs1.getString(2)+"' /><input type='hidden' id='nom"+i+"' value='"+rs1.getString(3)+"' /><input type='hidden' id='valor"+i+"' value=0 /><input type='hidden' id='vaiva"+i+"' value=0 /><input type='hidden' id='civa"+i+"' value=1 /></td></tr>");
					i=i+1;
				}rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			out.println("</table>");
		}
		if(va.equals("DPrecargado")){
			//Interfaz para los parametros de busquedapara el descargue de una orden aprobada en SAIM 
			
			out.println("<table width='40%'>");
			out.println("<tr><td colspan='4' ><br></td></tr>");
			out.println("<tr align='center' ><td  colspan='4' >DESCARGUE DE ORDEN  DE COMPRA</td></tr>");
			out.println("<tr><td colspan='4' ><br></td></tr>");
			out.println("<tr><td width='20%'>Digite Orden</td><td colspan='3' width='80%'><input type='text' id='numoc' size='15' class='rep' onkeyup='BuscOrdenA();Auna()' style='width:100%'/></td></tr>");
			out.println("<tr><td><input type='hidden' id='codoc' /></td><td colspan='3' width='80%'><div id='ord' ></div></td></tr>");
			out.println("<tr><td width='20%' >No. Factura </td><td width='25%'><input type='text' id='fact' onblur='Verifact()' class='rep' style='width:100%' /></td><td width='20%' align='right'>Dias de Plazo:</td><td width='25%'><input type='text' id='dp' class='rep' style='width:100%' onkeyup='num1()' /></td></tr>");
			out.println("<tr><td width='20%'>Fecha de Descargue </td><td width='26%'><input type='text' size='2' id='dia' onkeyup='dia()' class='rep' style='width:90%' /> / </td>");
			rs=md.BuscarMeses();
			out.println("<td width='26%'><select id='mes' class='rep' style='width:90%'><option value='0'>---</option>");
			try {
				while(rs.next()){
					out.println("<option value='"+rs.getString(1)+"' >"+rs.getString(2)+"</option>");
				}rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.println("</select> / </td><td width='26%'><input type='text' id='ano' onkeyup='Ano(2)' class='rep' style='width:100%' /></td></tr>");
			
			out.println("</table>");
			
		}
		
		if(va.equals("OCP")){
			//aqui me muestra los productos asociados a la orden escogida, se debe digitar la cantidad que es recibida recibida
			String codoc=req.getParameter("codoc");
			String fecha=req.getParameter("fecha");
			String nofactura=req.getParameter("nofact");
			String dp=req.getParameter("dp");
			out.println("<table width='100%'>");
			out.println("<tr><td colspan='10'> <br> </td></tr>");
			rs1=md.BuscarOCA(codoc);
			try {
				if(rs1.next()){
					rs2=md.BuscarProveedor(rs1.getString(15));
					if(rs2.next()){
						out.println("<tr>" +
									"<td class='contpre' bgcolor='#E6E7E7'  width='8%'>No. Orden : </td>" +
									"<td class='rep' width='12%'>"+rs1.getString(10)+"</td>" +
									"<td class='contpre' bgcolor='#E6E7E7'  width='8%'>No. Factura : </td>" +
									"<td class='rep' width='12%' >"+nofactura+"</td>" +
									"<td class='contpre' bgcolor='#E6E7E7'  width='8%' >Fecha de Entrega:</td>"+
									"<td class='rep' width='12%' >"+rs1.getString(2)+"</td>"+
									"<td class='contpre' bgcolor='#E6E7E7'  width='8%'>Fecha de Recibo:</td>"+
									"<td class='rep' width='12%' >"+fecha+"</td>"+
									"<td class='contpre'  bgcolor='#E6E7E7'  width='8%' >Dias Plazo :</td>" +
									"<td class='rep' width='12%' >"+dp+"</td>" +
									"</tr>");
						
						out.println("<tr>" +
								"<td class='contpre'  width='8%' bgcolor='#E6E7E7'>Nit:</td>" +
								"<td class='rep' width='12%' >"+rs2.getString(2)+"</td>"+
								"<td class='contpre'  width='8%' bgcolor='#E6E7E7'>Proveedor:</td>" +
								"<td class='rep' width='12%'>"+rs2.getString(9)+"</td>" +
								"<td class='contpre'  width='8%' bgcolor='#E6E7E7'>Direccion:</td>" +
								"<td class='rep' width='12%'>"+rs2.getString(10)+"</td>" +
								"<td class='contpre'  width='8%' bgcolor='#E6E7E7'>Telefono:</td>" +
								"<td class='rep' width='12%'>"+rs2.getString(4)+"</td>" +
								"<td class='contpre'  width='8%' bgcolor='#E6E7E7'>Email:</td>" +
								"<td class='rep' width='12%'>"+rs2.getString(6)+"</td>" +
								"</tr>");
						out.println("<tr>");
						
					}rs2.getStatement().getConnection().close();
					
					
					rs2=md.BSucursal(rs1.getString(4));
					if(rs2.next()){
						out.println("<td class='contpre' bgcolor='#E6E7E7' >Sucursal :</td>");
						out.println("<td class='rep' >"+rs2.getString(2)+"</td>");
					}rs2.getStatement().getConnection().close();
					
					rs2=md.Bcentrocosto(rs1.getString(5));
					if(rs2.next()){
						out.println("<td class='contpre' bgcolor='#E6E7E7'>Centro de Costo :</td>");
						out.println("<td class='rep'>"+rs2.getString(2)+"</td>");
					}rs2.getStatement().getConnection().close();
					
					rs2=md.Bsubcc(rs1.getString(6));
					if(rs2.next()){
						out.println("<td class='contpre' bgcolor='#E6E7E7'>SubCentro Costo:</td>");
						out.println("<td class='rep'>"+rs2.getString(2)+"</td>");
						
					}rs2.getStatement().getConnection().close();
					out.println("</tr>");
					
				}rs1.getStatement().getConnection().close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			out.println("<tr><td colspan='10'> <br> <br> </td></tr>");
			out.println("<tr><td colspan='10'> ");
				out.println("<table width='100%' border='1' cellspacing=0 align='center'>");
				int i=1;
				int tp=0;
					out.println("<tr class='contpre' bgcolor='#E8EFF4' align='center' ><td>ITEM</td><td>PRECIO UNITARIO</td><td>DESCUENTO</td><td>IVA</td><td>CANT SOLICITADA</td><td>CANT DESCARGADA</td><td>CANT RECIBIDA</td><td></td></tr>");
					rs=md.BuscarDetOC(codoc);
					
					try {
						while(rs.next()){
							out.println("<tr class='rep'><td>"+rs.getString(1)+"-"+rs.getString(2)+"</td><td align='center'>"+rs.getString(4)+"</td><td align='center'>"+rs.getString(5)+"</td><td align='center'>"+rs.getString(7)+"</td><td align='center'>"+rs.getString(3)+"</td><td>"+rs.getString(10)+"</td><td><input type='txt' id='cantrec"+i+"' onkeyup='valcant("+i+","+rs.getString(3)+","+rs.getString(10)+")' style='width:80%' value=0  class='rep' font='color:blue'/></td><td align='center'><a href='#' onclick='Omitir("+i+")' ><div id='act"+i+"' >OMITIR</div></a><input type='hidden' id='codprod"+i+"' value='"+rs.getString(8)+"' /><input type='hidden' id='coddet"+i+"' value='"+rs.getString(9)+"'/></td></tr>");
							//out.println("<tr><td colspan='6'><div id='actrec"+i+"' ></div></td></tr>");
							i=i+1;
							rs1=md.BusTipoProd(rs.getString(8));
							if(rs1.next()){
								if((rs1.getString(1).equals("1"))||(rs1.getString(2).equals("2"))){
									tp=tp+0;;
								}else{
									tp=1+tp;
								}
							}rs1.getStatement().getConnection().close();
						}rs.getStatement().getConnection().close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				out.println("</table>");
			out.println("</td></tr>");
			out.println("<input type='hidden' id='nofact' value='"+nofactura+"' />");
			out.println("<input type='hidden' id='fecha' value='"+fecha+"' />");
			if(i!=1){
			out.println("<tr><td colspan='10' align='center' ><input type='button' onclick='TestBodega("+i+","+codoc+","+dp+","+tp+")' value='Guardar' /> </td></tr>");
			System.out.println("entrando a guardar "+fecha);
			}
			out.println("</table>");
		}
		
		
		if(va.equals("DivOC")){
			//Me muestra las ordenes a descargar. 
			String texto=req.getParameter("texto");
			rs=md.BusOCA(texto);
			
			out.println("<table >");
			int i=1;
			try {
				while(rs.next()){
					rs1=md.BuscarProveedor(rs.getString(15));
					if(rs1.next()){
					out.println("<tr><td><a href='#' onclick='Selord("+rs.getString(1)+","+i+")' >"+rs.getString(10)+" - "+rs1.getString(2)+" | "+rs1.getString(9)+" - "+rs.getString(3)+"</a><input type='hidden' id='consec"+i+"' value='"+rs.getString(10)+"' /><input type='hidden' id='nomprov"+i+"' value='"+rs1.getString(9)+"' /></td></tr>");
					}rs1.getStatement().getConnection().close();
					i=i+1;
				}rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.println("</table>");
		}
		
		if(va.equals("Auna")){
		int vali=0;
		String texto=req.getParameter("texto");
		String consec="";
		String cod="";
		String nomprov="";
		rs=md.BusOCA(texto);
		   		try {
		   			while(rs.next()){
		   				consec=rs.getString(10);
		   			cod=rs.getString(1);
		   			rs1=md.BuscarProveedor(rs.getString(15));
			   			if(rs1.next()){
			   				nomprov=rs1.getString(9);
			   			}rs1.getStatement().getConnection().close();
		   				vali=1+vali;
		   			}rs.getStatement().getConnection().close();
		   		} catch (SQLException e) {
		   			// TODO Auto-generated catch block
		   			e.printStackTrace();
		   		}
		   	out.print(vali+"_"+consec+"_"+cod+"_"+nomprov);
		}
		
		if(va.equals("Verifact")){
			String fact=req.getParameter("fact");
			String codoc=req.getParameter("codoc");
			int verif=0;
			rs=md.Buscarfact(fact,codoc);
			try {
				if(rs.next()){
					verif=1;
				}else{
					verif=2;
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			out.print(verif);
		}
		
		
		if(va.equals("TestBodega")){
			String productos=req.getParameter("productos");
			String cantidades=req.getParameter("cantidades");
			String tp=req.getParameter("tp");
			String BodFarmacia=req.getParameter("BodFarmacia");
			String nofactura=req.getParameter("nofactura");
			String i=req.getParameter("i");
			String codoc=req.getParameter("codoc");
			String fecha=req.getParameter("fecha");
			String dp=req.getParameter("dp");
			String coddets=req.getParameter("coddets");
			String detprod[]=productos.split("_");
			String detcant[]=cantidades.split("_");
			String detcodet[]=coddets.split("_");
			int ii=Integer.parseInt(i);
			int s=1;
			System.out.println(" productos "+productos+" valor de ii"+ii);
			System.out.println(" coddets "+coddets+" valor de ii"+ii);
			int  verif=0;
			int a=0;
			int b=0;
			 rs4=md.VerificarTipos(codoc);
			 try {
				while(rs4.next()){
					 if((rs4.getString(1).equals("1"))||(rs4.getString(1).equals("2"))){
						 a=1;
					 }else{
						 if(rs4.getInt(1)>2){
							 b=1;
						 }
					 }
				 }rs4.getStatement().getConnection().close();
				 if((a==1)&&(b==1)){
					 verif=3;//3: med, disp med y productos 
				 }else{
					 if(a==1){
						 	verif=1;//1: solo medicmantos y dispositivos,
					 }else{
						 if(b==1){
							 verif=2;//2: solo productos 
						 }
					 }
				 }
				
				 
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//System.out.println("valor de verif "+verif );
			out.println("<table align='center'>");
			if((verif==1)||(verif==3)){
			out.println("<tr class='TituloRep' align='center'><td colspan='7' >NOTA : Todos los productos de tipo <b>MEDICAMENTO</b> ó <b>DISPOSITIVO MEDICO</b> seran llevado a la <b> BODEGA DE RECEPCION </b> correspondiente al modulo de <b>SERVICIO FARMACEUTICO </b> </td></tr>");
			}
			out.println("<tr><td><br></td></tr>");
			out.println("<tr align='center' class='contpre' bgcolor='#E0E5EB' ><td>Item</td><td>Nombre Producto</td><td>Cant Solicitada</td><td>Cant Recibida</td><td>Cant Descargada</td><td>Fecha de Vencimiento</td><td>Lote</td></tr>");
			for(int j=0;j<ii-1;j++){				
				try {
					//System.out.println("val j"+j);
					rs1=md.BusProdenOC(detcodet[j]);
					if(rs1.next()){
						rs=md.BusTipoProd(rs1.getString(4));
						if(rs.next()){
							if((rs.getString(1).equals("1"))||(rs.getString(1).equals("2"))){
								
								out.println("<tr clas='rep'><td colspan='7'><input type='hidden' id='fvenc"+s+"' value=0 /><input type='hidden' id='lot"+s+"'  value=0 /><input type='hidden' id='vercant"+s+"' value='"+detcant[j]+"' /></td></tr>");
							}else{
								out.println("<tr align='center' clas='rep' class='rep'><td>"+s+"</td><td>"+rs1.getString(5)+" - "+rs1.getString(6)+"</td><td>"+rs1.getString(7)+"</td>");
								if(detcant[j].equals("OMITIDO")){
									out.println("<td><font color='red'>"+detcant[j]+"</font></td><td>"+rs1.getString(18)+"</td>");
								}else{
									out.println("<td>"+detcant[j]+"</td><td>"+rs1.getString(18)+"</td>");
								}
								if(detcant[j].equals("OMITIDO")){
									out.println("<td><input type='text' id='fvenc"+s+"' onKeyup='masca(this,patron,true,"+diaa+","+mess+","+annio+")' onblur='checkfecha("+s+")' style='width:100%' disabled /></td><td><input type='text' id='lot"+s+"'  title='lot"+s+"' style='width:100%' disabled /><input type='hidden' id='vercant"+s+"' value='"+detcant[j]+"' /></td></tr>");
								}else{
									out.println("<td><input type='text' id='fvenc"+s+"' onKeyup='masca(this,patron,true,"+diaa+","+mess+","+annio+")' onblur='checkfecha("+s+")' style='width:100%'/></td><td><input type='text' id='lot"+s+"'  title='lot"+s+"' style='width:100%' /><input type='hidden' id='vercant"+s+"' value='"+detcant[j]+"' /></td></tr>");
								}
								
								
							}
						}rs.getStatement().getConnection().close();
						s=s+1;
					}rs1.getStatement().getConnection().close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}//FIN PARA 
			out.println("<input type='hidden' id='productos' value='"+productos+"' />");
			out.println("<input type='hidden' id='cantidades' value='"+cantidades+"' />");
			out.println("<input type='hidden' id='nofactura' value='"+nofactura+"' />");
			out.println("<input type='hidden' id='coddets' value='"+coddets+"' />");
			out.println("<input type='hidden' id='fecha' value='"+fecha+"' />");
			out.println("<tr align='center' class='rep'><td colspan='4' >Digite Bodega : <select id='BodInv' onchange='SelBodI("+i+","+codoc+","+dp+","+tp+","+s+","+BodFarmacia+","+verif+")' class='rep'>");
			
			//"+i+","+codoc+","+fecha+","+dp+","+productos+","+cantidades+","+BodFarmacia+","+tp+","+nofactura+","+coddets+","+s+"
			out.println("<option value='0' >---</option>");
			rs1=md.BusBodInventario();
			try {
				while(rs1.next()){
					out.println("<option value='"+rs1.getString(1)+"'>"+rs1.getString(2)+"</option>");
					
				}rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.println("</select></td>");
			out.println("<td colspan='3'><input type='button' value='Continuar' onclick='ContinuarDesc("+i+","+codoc+","+dp+","+tp+","+s+","+BodFarmacia+","+verif+")' /></td>");
			
			out.println("</tr>");
			
			out.println("</table>");
		}
		
		
		if(va.equals("GpreOC")){
			
			String i=req.getParameter("i");
			String codoc=req.getParameter("codoc");
			String fecha=req.getParameter("fecha");
			String dp=req.getParameter("dp");//dias de pago
			String productos=req.getParameter("productos");
			String cantidades=req.getParameter("cantidades");
			String BodFarmacia=req.getParameter("BodFarmacia");
			String BodProducto=req.getParameter("BodProducto");
			String tp=req.getParameter("tp");//tipo
			String coddets=req.getParameter("coddets");
			String fechasp=req.getParameter("fechasp");//fechas de venc de producto
			String lotesp=req.getParameter("lotesp"); // lotes correspondiente a los productos 
			String invimas=req.getParameter("invimas");//invimas correspondiente a los medicamentos y dispositos medicos 
			String lotes=req.getParameter("lotes");// lotes de med y disp med
			String fechas=req.getParameter("fechas");//fechas de med y disp med
			int ii=Integer.parseInt(i);
			String detprod[]=productos.split("_");
			String detcant[]=cantidades.split("_");
			String detcoddet[]=coddets.split("_");
			String detlotes[]=lotes.split("_");
			String detfechas[]=fechas.split("_");
			String detlotesp[]=lotesp.split("_");
			String detinvimas[]=invimas.split("_");
			String detfechasp[]=fechasp.split("_");
			String nofactura=req.getParameter("nofactura");
			
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
			java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());
			
			long sumiva=0;
			
			/*Buscar Datos que necesitamos de la orden*/
			String suc_ccosto="";
			String cen_succ="";
			String codprove="";
			rs=md.BuscarOCA(codoc);
			String concept="";
			String consecOC="";
			int may=0;
			int men=0;
			for(int h=0;h<ii-1;h++){
				rs2=md.BusDetOC(detcoddet[h]);
				try {
					if(rs2.next()){	
					if((rs2.getString(19).equals("1"))||(rs2.getString(19).equals("2"))){
						if(rs2.getInt(1)>2){
							 if(detcant[h].equals("OMITIDO")){
								 may=may;//solo med y dispositivos
							 }else{
								 may=may+1;
							 }
						 }else{
							 if(detcant[h].equals("OMITIDO")){
								 men=men;
							 }else{
								 men=men+1;
							 }
							
						 }
					}
						
					}rs2.getStatement().getConnection().close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
			
			try {
				if(rs.next()){
					suc_ccosto=rs.getString(7);
					cen_succ=rs.getString(8);
					codprove=rs.getString(15);
					concept=rs.getString(3);
					consecOC=rs.getString(10);
				}rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String codterc="";
			String codcuentaprove="";
			rs1=md.BuscarTercero(codprove);
			try {
				if(rs1.next()){
					codterc=rs1.getString(3);
					codcuentaprove=rs1.getString(4);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			
			String codmov="";
			String sigla="";
			String consecEnt="";
			long consEnt=0;
			int verif=0;//1: solo medicmantos y dispositivos, 2: solo productos , 3: med, disp med y productos 
			String siglaI="";
			String consecEntI="";
			long consEntI=0;
			rs3=md.BMovimiento("COMPRAS");//busca tipo de movimiento para farmacia
			try {
				if(rs3.next()){
				
					codmov=rs3.getString(1);
					int a=0;
					int b=0;
					 rs4=md.VerificarTipos(codoc);
					 
						while(rs4.next()){
							 if((rs4.getString(1).equals("1"))||(rs4.getString(1).equals("2"))){
								 a=1;
							 }else{
								 if(rs4.getInt(1)>2){
									 b=1;
								 }
							 }
						 }rs4.getStatement().getConnection().close();
						 if((a==1)&&(b==1)){
							 verif=3;//3: med, disp med y productos 
						 }else{
							 if(a==1){
								 	verif=1;//1: solo medicmantos y dispositivos,
							 }else{
								 if(b==1){
									 verif=2;//2: solo productos 
								 }
							 }
						 }
					
					if(verif==1){	
						if(may>0){
							rs=md.BConsecutivo(rs3.getString(3)); //Consecutivo de movimientos Farmacia
							if(rs.next()){
								consEnt=rs.getLong(3);
								long consEntt=consEnt+1;
								md.ActConsecutivo(rs3.getString(3),consEntt);//Incremento en consecutivos farmacia 
								sigla=rs.getString(4);
								String s = String.valueOf(consEnt);
								consecEnt=sigla+s;
								
							}rs.getStatement().getConnection().close();
						}
					
					}else{
						if(verif==2){
							
							if(men>0){
								rs=md.BConsecInv("1");//Consecutivo de inventario productos
								if(rs.next()){
									consEntI=rs.getLong(3);
									consEntI=consEntI+1;
									md.ActConsecInv(rs3.getString(3),consEntI);//Incremento en consecutivos de inventario productos
									siglaI=rs.getString(4);
									String s = String.valueOf(consEntI);
									consecEntI=siglaI+s;
								}rs.getStatement().getConnection().close();
							}
						}else{
							if(verif==3){
								System.out.println("valor de men"+men);
								if(may>0){
									rs=md.BConsecutivo(rs3.getString(3)); //Consecutivo de movimientos Farmacia
									if(rs.next()){
										consEnt=rs.getLong(3);
										long consEntt=consEnt+1;
										md.ActConsecutivo(rs3.getString(3),consEntt);//Incremento en consecutivos farmacia 
										sigla=rs.getString(4);
										String s = String.valueOf(consEnt);
										consecEnt=sigla+s;
										
									}rs.getStatement().getConnection().close();
								}
								
								System.out.println("valor de may"+may);
								if(men>0){
									rs=md.BConsecInv("1");//Consecutivo inventario productos
									if(rs.next()){
										consEntI=rs.getLong(3);
										consEntI=consEntI+1;
										md.ActConsecInv(rs3.getString(3),consEntI);//Incremento en consecutivos de inventario productos
										siglaI=rs.getString(4);
										String s = String.valueOf(consEntI);
										consecEntI=siglaI+s;
									}rs.getStatement().getConnection().close();
								}
							}
						}
					}
				}rs3.getStatement().getConnection().close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			long sum=0;
			long sumtotal=0;
			double mult=0;
			double sumcantf=0;
			double sumcantp=0;
			for(int f=0;f<ii-1;f++){
				rs2=md.BusDetOC(detcoddet[f]);
				try {
					if(rs2.next()){
						String cant=detcant[f];
						if(detcant[f].equals("OMITIDO")){
						
						}else{
							
							double numero=Double.valueOf(cant).doubleValue();
							if((rs2.getString(19).equals("1"))||(rs2.getString(19).equals("2"))){
								sumcantf=numero+sumcantf;//Total de cantidades para farc_movimientos
							}else{
								sumcantp=numero+sumcantp;//Total de cantidades para inv_movimientos
							}
							
							double desc=rs2.getDouble(9);
							double unit=rs2.getDouble(8);
							 mult=(numero*unit);
							double opedesc=((mult*desc)/100);
							mult=mult-opedesc;				
						}
					}rs2.getStatement().getConnection().close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				sum= (new Double(mult)).longValue();
				sumtotal=sum+sumtotal;
			}
			 
			
			String nsoporte="ORD. COMPRA "+consecOC+" No. Factura "+nofactura;
			for(int j=0;j<ii-1;j++){
				System.out.println("valor de j"+j);
				System.out.println("valor de ii"+ii);
				System.out.println(" detalle "+detcoddet[j]);
				rs2=md.BusDetOC(detcoddet[j]);
				try {
					if(rs2.next()){
						rs=md.BusTipoProd(rs2.getString(4));
						if(rs.next()){
							if((rs.getString(1).equals("1"))||(rs.getString(1).equals("2"))){
								//PARA MEDICAMENTOS Y DISPOSITIVOS MEDICOS
								System.out.println("entrando a primer condicional ");
								/**
								 * Metodo para ingresar a farc_movimientos farc_detallemov, 
								 * luego a farc_inventario,  farc_actrecep y ord_seguimiento, ord_contpredoc
								 */
								if(detcant[j].equals("OMITIDO")){
								}else{
									md.GuardarInvenFarm(detcant[j],codmov,Fecha,Hora,user,nsoporte,concept,consecEnt,rs2.getString(4),detlotes[j],detinvimas[j],detfechas[j],rs2.getString(11),rs2.getLong(7),rs2.getLong(8),rs2.getLong(9),BodFarmacia,codprove,consecOC,nofactura,codoc,sumcantf);
									
									/**
									 * Buscar si esta creado el registro en ord_contpredoc, para proceder con ese identificador a crear 
									 *  en cont_predoc, no sin antes colocar el detalle de la orden como predecargado o descargado segun sea el caso
									 */
									rs3=md.BusOrdcontpre(nofactura,codoc);
									if(rs3.next()){
										String codctaprod="";
										rs4=md.BuscarCtaProd(rs2.getString(4));
										if(rs4.next()){
											codctaprod=rs4.getString(9);
										}rs4.getStatement().getConnection().close();
										md.ActdetOrdenCompra(detcoddet[j],detcant[j],rs2.getLong(7),rs2.getLong(18),user,Fecha,Hora);//coloca la orden predescargada o descargada
										md.CrearCont_Predoc(codterc,suc_ccosto,cen_succ,codoc,nofactura,codcuentaprove,codctaprod,rs2.getString(4),Fecha,Hora,fecha,rs3.getString(1),user,detcant[j],rs2.getLong(7),rs2.getLong(8),rs2.getLong(9),rs2.getLong(11),rs2.getString(17),concept,codprove,sumtotal);
									}rs3.getStatement().getConnection().close();
								}
							}else{
								//PARA OTRA CLASE DE PRODUCTOS
								if(detcant[j].equals("OMITIDO")){
								}else{
									/**
									 * Metodo para ingresar en la tabla inv_producto, inv_pmovimiento, inv_detpmov, ord_seguimiento, ord_contpredoc */
									md.GuardarInvenProducto(detcant[j],"3",Fecha,Hora,user,nsoporte,concept,consecEntI,rs2.getString(4),detlotesp[j],detfechasp[j],rs2.getLong(7),rs2.getLong(8),rs2.getLong(9),rs2.getLong(11),BodProducto,codprove,consecOC,nofactura,codoc,rs2.getString(17),sumcantp);
									/**
									 * Buscar si esta creado el registro en ord_contpredoc, para proceder con ese identificador a crear 
									 *  en cont_predoc, no sin antes colocar el detalle de la orden como predecargado o descargado segun sea el caso
									 */
									rs3=md.BusOrdcontpre(nofactura,codoc);
									if(rs3.next()){
										String codctaprod="";
										rs4=md.BuscarCtaProd(rs2.getString(4));
										if(rs4.next()){
											codctaprod=rs4.getString(9);
										}rs4.getStatement().getConnection().close(); 
										md.ActdetOrdenCompra(detcoddet[j],detcant[j],rs2.getLong(7),rs2.getLong(18),user,Fecha,Hora);//coloca la orden predescargada o descargada
										md.CrearCont_Predoc(codterc,suc_ccosto,cen_succ,codoc,nofactura,codcuentaprove,codctaprod,rs2.getString(4),Fecha,Hora,fecha,rs3.getString(1),user,detcant[j],rs2.getLong(7),rs2.getLong(8),rs2.getLong(9),rs2.getLong(11),rs2.getString(17),concept,codprove,sumtotal);
									}rs3.getStatement().getConnection().close();
								}
								
							}
						}rs.getStatement().getConnection().close();
						
							
					}rs2.getStatement().getConnection().close();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			System.out.println(verif+"_"+consecEnt+"_"+consecEntI);
			out.println(verif+"_"+consecEnt+"_"+consecEntI);
			
		}
		
		if(va.equals("1")){
			//System.out.println("ENTRANDO A 1");
			String productos=req.getParameter("productos");
			String cantidades=req.getParameter("cantidades");
			String i=req.getParameter("i");
			String codoc=req.getParameter("codoc");
			String fecha=req.getParameter("fecha");
			String dp=req.getParameter("dp");
			String tp=req.getParameter("tp");
			String BodFarmacia=req.getParameter("BodFarmacia");
			String nofactura=req.getParameter("nofactura");
			String coddets=req.getParameter("coddets");
			String BProducto=req.getParameter("Bproducto");
			String lotesp=req.getParameter("lotesp");
			String fechasp=req.getParameter("fechasp");
			
			String detprod[]=productos.split("_");
			String detcodet[]=coddets.split("_");
			String detcant[]=cantidades.split("_");
			int ii=Integer.parseInt(i);
			System.out.println("Valor de fecha de descargue"+fecha+" valor de BodProducto"+BProducto);
			out.println("<input type='hidden' id='productos' value='"+productos+"' />");
			out.println("<input type='hidden' id='cantidades' value='"+cantidades+"' />");
			out.println("<input type='hidden' id='nofactura' value='"+nofactura+"' />");
			out.println("<input type='hidden' id='coddets' value='"+coddets+"' />");
			out.println("<input type='hidden' id='i' value='"+i+"' />");
			out.println("<input type='hidden' id='codoc' value='"+codoc+"' />");
			out.println("<input type='hidden' id='fecha' value='"+fecha+"' />");//fecha de descargue 
			out.println("<input type='hidden' id='dp' value='"+dp+"' />");
			out.println("<input type='hidden' id='tp' value='"+tp+"' />");
			out.println("<input type='hidden' id='BProducto' value='"+BProducto+"' />");
			out.println("<input type='hidden' id='lotesp' value='"+lotesp+"' />");
			out.println("<input type='hidden' id='fechasp' value='"+fechasp+"' />");
			out.println("<input type='hidden' id='BodFarmacia' value='"+BodFarmacia+"' />");
			out.println("<table width='100%' border=1 cellspacing=0 >");
			out.println("<tr align='center'  class='contpre'><td>No.</td><td>Medicamento / Dispositivo Medico </td><td>Cant Solicitada</td><td>Cant Recibida</td><td>Cant Descargada</td><td>Fecha de Vencimiento</td><td>Lote</td><td>Cod Invima </td></tr>");
			int h=1;
			int item=1;
			for(int j=0;j<ii-1;j++){
				rs=md.BusProdenOC(detcodet[j]);
				try {
					if(rs.next()){
						
						rs1=md.BusTipoProd(rs.getString(4));
						if(rs1.next()){
							if((rs1.getString(1).equals("1"))||(rs1.getString(1).equals("2"))){
								out.println("<tr class='rep'><td>"+item+"</td><td>"+rs.getString(5)+" - "+rs.getString(6)+"</td><td>"+rs.getString(7)+"</td>");
								if(detcant[j].equals("OMITIDO")){
									out.println("<td><font color='red'>"+detcant[j]+"</font></td><td>"+rs.getString(18)+"</td><td><input type='text' id='fvenc"+h+"' onKeyup='masca(this,patron,true,"+diaa+","+mess+","+annio+")' onblur='checkfecha("+h+")' style='width:100%' class='rep' disabled /></td><td><input type='text' id='lot"+h+"' style='width:100%' class='rep'  disabled /></td><td><input type='text' id='invima"+h+"' title='invima"+h+"' onblur='verifinv("+j+","+ii+","+h+")' style='width:100%' class='rep' disabled/><input type='hidden' id='vercantt"+h+"' value='"+detcant[j]+"' /></td></tr>");
								}else{
								out.println("<td>"+detcant[j]+"</td><td>"+rs.getString(18)+"</td><td><input type='text' id='fvenc"+h+"' onKeyup='masca(this,patron,true,"+diaa+","+mess+","+annio+")' onblur='checkfecha("+h+")' style='width:100%' class='rep' /></td><td><input type='text' id='lot"+h+"' style='width:100%' class='rep' /></td><td><input type='text' id='invima"+h+"' title='invima"+h+"' onblur='verifinv("+j+","+ii+","+h+")' style='width:100%' class='rep'/><input type='hidden' id='vercantt"+h+"' value='"+detcant[j]+"' /></td></tr>");
								}
								item=item+1;
							}else{
								out.println("<tr><td colspan='8'><input type='hidden' id='fvenc"+h+"' value=0 /><input type='hidden' id='lot"+h+"'  value=0 /><input type='hidden' id='invima"+h+"'  value=0 /><input type='hidden' id='vercantt"+h+"' value='0' /></td></tr>");
							}
						}rs1.getStatement().getConnection().close();
					}rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				h=h+1;
			}
			out.println("<tr><td colspan='8'><div id='boton'></div></td></tr>");
			out.println("</table>");
			
			
		}
		
		if(va.equals("MovFarmacia")){
			String consecFarm=req.getParameter("consecFarm");
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());	
			rs=md.BusCodmov(1,consecFarm,Fecha);
			try {
				if(rs.next()){
					out.println(rs.getString(1));
				}rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		if(va.equals("MovProducto")){
			String consecInv=req.getParameter("consecInv");
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());	
			rs=md.BusCodmov(2,consecInv,Fecha);
			try {
				if(rs.next()){
					out.println(rs.getString(1));
				}rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("Oclic")){
			String h=req.getParameter("h");
			String i=req.getParameter("i");
			String codoc=req.getParameter("codoc");
			String fecha=req.getParameter("fecha");
			String dp=req.getParameter("dp");
			String productos=req.getParameter("productos");
			String cantidades=req.getParameter("cantidades");
			String BodFarmacia=req.getParameter("BodFarmacia");
			String BodProducto=req.getParameter("BodProducto");
			String tp=req.getParameter("tp");
			String nofactura=req.getParameter("nofactura");
			//String user=req.getParameter("user");
			String coddets=req.getParameter("coddets");
			String fechasp=req.getParameter("fechasp");
			String lotesp=req.getParameter("lotesp");
			String fechas=req.getParameter("fechas");
			String lotes=req.getParameter("lotes");
			String invimas=req.getParameter("invimas");
			
			int hh = Integer.parseInt(h);
			hh=hh+1;
			out.println("<table width='100%' >");
			out.println("<tr><td>");
			out.println("<input type='hidden' id='h' value='"+hh+"' ");
			out.println("<input type='hidden' id='i' value='"+i+"' />");
			out.println("<input type='hidden' id='codoc' value='"+codoc+"' />");
			out.println("<input type='hidden' id='fecha' value='"+fecha+"' />");
			out.println("<input type='hidden' id='dp' value='"+dp+"' />");
			out.println("<input type='hidden' id='productos' value='"+productos+"' />");
			out.println("<input type='hidden' id='cantidades' value='"+cantidades+"' />");
			out.println("<input type='hidden' id='BodFarmacia' value='"+BodFarmacia+"' />");
			out.println("<input type='hidden' id='BodProducto' value='"+BodProducto+"' />");
			out.println("<input type='hidden' id='tp' value='"+tp+"' />");
			out.println("<input type='hidden' id='nofactura' value='"+nofactura+"' />");
			out.println("<input type='hidden' id='coddets' value='"+coddets+"' />");
			out.println("<input type='hidden' id='fechasp' value='"+fechasp+"' />");
			out.println("<input type='hidden' id='lotesp' value='"+lotesp+"' />");
			out.println("<input type='hidden' id='fechas' value='"+fechas+"' />");
			out.println("<input type='hidden' id='lotes' value='"+lotes+"' />");
			out.println("<input type='hidden' id='invimas' value='"+invimas+"' />");
			out.println("<tr></td>");
			out.println("<tr><td align='center' ><input type='button' value='Continuar1' onclick='pp()' /> </td></tr>");
			out.println("</table>");
		}
		
		
		if(va.equals("VerifOrden")){
			String codoc=req.getParameter("codoc");
			rs=md.VerifOrden(codoc);//Verifica si la orden tiene descargues
			
			int verif=0;
			try {
				if(rs.next()){
					verif=1;
				}rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			out.println(verif);
		}
		
		if(va.equals("CambEstO")){
			String codoc=req.getParameter("codoc");
			String esto=req.getParameter("esto");
			String textoobs=req.getParameter("textoobs");
			String descrip=req.getParameter("descrip");
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
			java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());
		//	System.out.println("Valor de codoc "+codoc+" dat "+dato+"textoobs "+textoobs);
			
			try {
				md.CambEstO(codoc,esto,descrip,Fecha,Hora,user);
				out.println("Orden Cambiada de estado");
			} catch (SQLException e) {
				out.println("Ups, ha ocurrido un error ");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		if(va.equals("ElimCargue")){
			String coddet=req.getParameter("coddet");
			String codigo=req.getParameter("codigo");
			md.EliminarCargue(coddet);
			
			out.println("<table width='100%'>");
			out.println("<tr><td colspan='7' align='center'><br>DETALLE  DE CARGUE</td></tr>");
			out.println("<tr>");
			out.println("<td>");
				out.println("Cod. Referencia");
			out.println("</td>");
			out.println("<td>");
				out.println("Descripcion");
			out.println("</td>");
			out.println("<td>");
				out.println("Valor");
			out.println("</td>");
			out.println("<td>");
				out.println("Descuento");
			out.println("</td>");
			out.println("<td>");
				out.println("IVA");
			out.println("</td>");
			out.println("<td>");
				out.println("Total");
			out.println("</td>");
			out.println("<td>Eliminar");
			out.println("</td>");
		out.println("</tr>");//7
		rs1=md.BuscarDetalle(codigo);
		int i=1;
		
			try {
				while(rs1.next()){
					out.println("<tr>");
					out.println("<td>");
						out.println(rs1.getString(5));
					out.println("</td>");
					out.println("<td>");
						out.println(rs1.getString(6));
					out.println("</td>");
					out.println("<td>");
						out.println(rs1.getString(8));
					out.println("</td>");
					out.println("<td>");
						out.println(rs1.getString(9));
					out.println("</td>");
					out.println("<td>");
						out.println(rs1.getString(11));
					out.println("</td>");
					out.println("<td>");
						out.println(rs1.getString(12));
					out.println("</td>");
					out.println("<td><input  name='rbutton' type='radio'  id='Elim"+i+"'  onclick='RElim("+i+","+rs1.getString(1)+")' title='Eliminar' />");
					out.println("<input type='hidden' value='"+rs1.getString(1)+"' id='cdet"+i+"'><input type='hidden' value='"+codigo+"'  id='codigo' /></td></tr>");
					
					i=i+1;
				
				}rs1.getStatement().getConnection().close();
				out.println("<tr><td colspan='7' align='center'><input type='button' id='Term' value='Terminar' onclick='Terminar("+codigo+")' /></td></tr>");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}
		
		if(va.equals("Terminar")){
			String codigo=req.getParameter("codigo");
			String resp=req.getParameter("resp");
			String nofact=req.getParameter("nofact");
			String codcsuc=req.getParameter("codsuc");
			String codsubc=req.getParameter("codsubc");
			String codcc=req.getParameter("codcc");
			String codsucycc=req.getParameter("codsucycc");
			String codcentrosubc=req.getParameter("codcentrosubc");
			String obs=req.getParameter("obs");
			String texto="COM";//sigla del documento
			long consec=0;
			String cons="";
			String consn="";
			rs=md.BuscarConsecDoc(texto);
			try {
				if(rs.next()){
					consec=rs.getLong(4);
					
						//out.print(rs.getString(1)+"|"+rs.getString(2));
						if(rs.getString(4).length()==1){cons=("00000000"+rs.getString(4));}
						if(rs.getString(4).length()==2){cons=("0000000"+rs.getString(4));}
						if(rs.getString(4).length()==3){cons=("000000"+rs.getString(4));}
						if(rs.getString(4).length()==4){cons=("00000"+rs.getString(4));}
						if(rs.getString(4).length()==5){cons=("0000"+rs.getString(4));}
						if(rs.getString(4).length()==6){cons=("000"+rs.getString(4));}
						if(rs.getString(4).length()==7){cons=("00"+rs.getString(4));}
						if(rs.getString(4).length()==8){cons=("0"+rs.getString(4));}
						if(rs.getString(4).length()==9){cons=rs.getString(4);}
						consn=rs.getString(3)+""+cons;
						
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}		
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
			java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());	
			
			java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("yyyy/MM/dd");
			String temp=sdf.format(Fecha);
			System.out.println("temp"+temp);
			
			String dettemp[]=temp.split("/");
			String anno=dettemp[0];
			String perio=dettemp[1];
			rs=md.BuscarDetOC(codigo);
			String codprove="";
			try {
				if(rs.next()){
					codprove=rs.getString(25);//cod proveedor
				}rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String codterc="";
			rs=md.BuscarTercero(codprove);
			try {
				if(rs.next()){
					codterc=rs.getString(3);
				}rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("valor de codtercero"+codterc+" valor de codprove "+codprove);
			md.EncabeDocumento(anno,perio,Fecha,nofact,resp,codterc,user,Hora,consn);
			
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
			
			md.ActualizarConsecutivo((consec+1),"110");
			
			rs=md.BuscarDatosProve(codprove);
			String regimen="";
        	String depprove="";
        	String depemp="";
        	String codcuentaprove="";
        	try {
				if(rs.next()){
					regimen=rs.getString(1);
					depprove=rs.getString(2);
					depemp=rs.getString(3);
					codcuentaprove=rs.getString(4);
					
				}rs.getStatement().getConnection().close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			rs=md.BuscAgruDetOc(codigo);
			
			long Sumtotal=0;
			long totaliva=0;
			long totalrfte=0;
			long totalica=0;
			double descuento=0;
			double iva=0;
			double rfte=0;
			double ica=0;
			double subtotal=0;
			double uvt=0;
			long valordeb=0;
			double vrfte=0;
			double vica=0;
			String codctaiva="";
			String codctaica="";
			String codctarfte="";
			
			try {
				while(rs.next()){
					descuento=((rs.getDouble(2)*rs.getDouble(3))/100);
					
					subtotal=(rs.getDouble(2)-descuento);
					
					valordeb=(new Double(subtotal)).longValue();
					System.out.println("Cod Cuenta "+rs.getString(6)+" valor descuento"+descuento+" valor subtotal "+subtotal+" valordeb"+valordeb);
					md.DetDocumento(codterc,doc,codsucycc,codcentrosubc,rs.getString(6),valordeb,0,"COMP",obs);
					iva=((subtotal*rs.getDouble(4))/100);
					totaliva=((new Double(iva)).longValue())+totaliva;
					Sumtotal=((new Double(subtotal)).longValue())+Sumtotal;
					vrfte=rs.getDouble(10);
					vica=rs.getDouble(7);
					uvt=((rs.getLong(8))*(rs.getLong(9)));
					codctaiva=rs.getString(11);
					codctaica=rs.getString(13);
					codctarfte=rs.getString(12);
	        		
				}rs.getStatement().getConnection().close();
				
				if((Sumtotal>uvt)||(Sumtotal==uvt)&&(!regimen.equals("Grandes Contribuyentes"))){
						rfte=((Sumtotal*vrfte)/100);				
					if(depprove.equals(depemp)){
						ica=((Sumtotal*vica)*100);
					}
        		}
				
				totalica=((new Double(ica)).longValue());
				totalrfte=((new Double(rfte)).longValue());
				long totalprove=((Sumtotal+totaliva)-(totalica+totalrfte));
				md.DetDocumento(codterc,doc,codsucycc,codcentrosubc,codctaiva,totaliva,0,"COMP",obs);
				md.DetDocumento(codterc,doc,codsucycc,codcentrosubc,codctaica,0,totalica,"COMP",obs);
				md.DetDocumento(codterc,doc,codsucycc,codcentrosubc,codctarfte,0,totalrfte,"COMP",obs);
				md.DetDocumento(codterc,doc,codsucycc,codcentrosubc,codcuentaprove,0,totalprove,"COMP",obs);
				
				long totaldebito=Sumtotal+totaliva;
				long totalcredito=totalprove+totalica+totalrfte;
				md.ActEncabezado(doc,totaldebito,totalcredito);
				out.println(doc);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
	}
	
	
	
}
