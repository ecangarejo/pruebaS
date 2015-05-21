package ord_controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ord_metodo.MetodoProve;



public class ControlProve extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String va = request.getParameter("valor");
		PrintWriter out=response.getWriter();
		MetodoProve mcp = new MetodoProve();
				
		String nit=request.getParameter("nit");//
		String pais=request.getParameter("pais");//
		String ciudad=request.getParameter("ciudad");//
		String muni=request.getParameter("muni");//
		String telefono=request.getParameter("telefono");//	
		String fax=request.getParameter("fax");	//
		String email=request.getParameter("email");	//
		String contacto=request.getParameter("contacto");	
		String clase=request.getParameter("clase");	//
		String razon=request.getParameter("razon");	//
		String direccion=request.getParameter("direccion");//
		String observacion=request.getParameter("observacion");//
		String nombreTipo=request.getParameter("nombreTipo");//
		String Operacion=request.getParameter("Operacion");//
		String ni=request.getParameter("ni");	//
		String codpp=request.getParameter("codpp");	//
		String formapago=request.getParameter("formapago");
		String cta=request.getParameter("ctad");
		String aecon=request.getParameter("aecon");
		String rfte=request.getParameter("rfte");
		String ica=request.getParameter("ica"); 
		String user=request.getParameter("user");
		String tc=request.getParameter("tc");
		String diaspla=request.getParameter("diaspla");
		String nombrebanco=request.getParameter("nombrebanco");
		String codbanco=request.getParameter("codbanco");
		String telcontacto=request.getParameter("telcontacto");
		String dv=request.getParameter("dv");
		String estado=request.getParameter("estado");
		
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		ResultSet rs3=null;
		ResultSet rs4=null;
		ResultSet rs5=null;
		ResultSet rs6=null;
		
		
		if(va.equals("Proveedor")){			
			
			out.print("<table width='100%' class='style6' ><tr><td ><div align='center' class='style11' id='cabecera2'>Crear Proveedores</div></td></tr>");
			out.print("<tr><td>" +
					"<table class='style6' width='50%'  ><tr><td width='10%' > Razon Social : </td><td width='25%'><input name='txtRazon' type='text' id='txtRazon' size='50' onkeyup='this.value=this.value.toUpperCase();' style='width:100%' /></td>");
					out.print("<td width='10%' > &nbsp; Identificacion : </td><td width='3%' ><select  style='width:50px' name='tc' id='tc' >");
					rs1=mcp.ConsultarTiposDocumentos();
					try {
						while(rs1.next()){
							out.print("<option title='"+rs1.getString(2)+"' value='"+rs1.getString(1)+"'>"+rs1.getString(3)+"</option>");
						}
						rs1.getStatement().getConnection().close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					out.print("</select></td><td width='16%'><input name='txtNit' type='text' id='txtNit' size='40' onchange='validanit()' style='width:100%' /></td><td width='5%'><input type='text' id='dv'  size='3'  readonly='readonly' style='width:100%' /></td>");
					out.print("</tr>");
					out.print("</table>");
				out.print("</td></tr>");
				out.print("<tr><td>" +
							"<table  class='style6' width='50%'>");
							out.print("<td width='6%' >Direccion : </td><td width='17%'><input name='txtDireccion' type='text' id='txtDireccion' size='50' onkeyup='this.value=this.value.toUpperCase();' style='width:100%' /></td><td width='8%' > &nbsp; Telefono :</td><td width='18%'><input name='txtTelefono' type='text' id='txtTelefono' size='40'  style='width:100%' /></td>" +
							"<tr><td width='4%'> &nbsp; Fax :</td><td width='8%'><label><input name='txtFax' type='text' id='txtFax' size='10'  style='width:100%' /></td>");
							out.print("<td width='4%'> &nbsp; E-mail:</td><td width='11%'><input name='txtEmail' type='text' id='txtEmail' size='40' style='width:100%'/></td></tr>");
							out.print("<tr><td width='6%'>&nbsp;Contacto :</td><td width='20%'><label><input name='txtContacto' type='text' id='txtContacto' size='40' onkeyup='this.value=this.value.toUpperCase();' style='width:100%'/></label></td><td width='6%'>Tel. Contacto :</td><td width='5%'><input type='text' id='telconta'  style='width:100%' /></td>");
							out.print("</tr>");
							out.print("</table>");
				out.print("</td></tr>");
				
				out.print("<tr><td>");
				out.print("<table width='50%' class='style6'>");
				out.print("<tr><td  width='6%'> &nbsp; Nacionalidad :</td><td width='6%'><select name='cmbPais' id='cmbPais' onChange='Dpto()' class='rep'><option value='Seleccione'>Seleccione</option>");
				rs1=mcp.ObtenerPais();
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
				out.print("<td width='6%' > &nbsp; Departamento :</td><td width='10%'><label><div id='dpto'><select name='cmbDpto' id='cmbDpto' class='rep' style='width:100%' ><option value='Seleccione'>Seleccione</option>");
				out.print("</select></div></label></td><td width='5%'>&nbsp; Municipio: </td><td width='9%'><label><div id='Muni'><select name='cmbMuni' id='cmbMuni' style='width:100%' class='rep'><option value='Seleccione'>Seleccione</option>");
				out.print("</select></div></label></td></tr>");
				out.print("</table>");
				out.print("</td></tr>");
				
				out.print("<tr><td>");
			//	
							out.print("<table width='50%' class='style6'   >");
							out.print("<tr><td width='6%'>Banco :</td><td width='17%'><input id='txtBanco' size='30' onkeyup='autocompletaBanco()' style='width:100%' /></td><td width='7%'> Forma de Pago : </td><td width='5%'><label><select id='fp' onchange='FormaPago()' class='rep' >");
							
							rs=mcp.BuscarformasdePago();
							try {
								while(rs.next()){
									out.print("<option value='"+rs.getString(1)+"' >"+rs.getString(2)+"</option>");
								}rs.getStatement().getConnection().close();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
			
					
							out.print("</select></label></td>");
							
							out.print("<td width='6%'> Dias de Plazo: </td><td width='3%'><input type='text' id='diap' size='3' style='width:100%' /></td></tr>");
							out.print("<tr><td></td><td><div id='banco'><input type='hidden' id='txtCodBanco' value=0 /></div></td><td></td><td><div id='vfp' ></div></td><td colspan='2'></td></tr>");
							out.print("<tr><td width='5%'> Regimen: </td><td width='8%'><label><select name='cmbclase' id='cmbClase' class='rep' style='width:100%' ><option value='Seleccione'>Seleccione</option><option value='Regimen Comun'>Regimen Comun</option><option value='Regimen Simplificado'>Regimen Simplificado</option><option value='Grandes Contribuyentes'>Grandes Contribuyentes</option></select></label></td>");
							out.print("<td width='5%' > Estado : </td><td width='4%'><select id='estad' class='rep' ><option value='0'>ACTIVO</option><option value='1' >INACTIVO</option></select></td>");
							out.print("</tr>");
							out.print("</table>");
							out.print("</td></tr>");
							out.print("<tr><td>");
							out.print("<table width='50%' class='style6'>");
							out.print("<tr><td width='8%'> Actividad Economica: </td><td width='12%'><input type='text' id='aecon' size='40' onkeyup='autoAecon()' style='width:100%'/></td></tr>");
							out.print("<tr><td><input type='hidden'  id='codaecon' /></td><td><div id='veacon' ></div></td></tr>");
							out.print("<td width='5%'> Observacion :</td><td  width='18%'><label><textarea name='txtDescripcion' id='txtDescripcion' cols='32' rows='2' style='width:100%' ></textarea></label></td>");
							
							
							out.print("<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>");
							out.print("<tr><td colspan='4'><div align='center'><label><input type='button' name='btnIngresarProveedor' id='btnIngresarProveedor' value='     Crear     ' onClick='CrearProveedor()'></label></div></td></tr>");
							out.print("<tr><td colspan='2'>&nbsp;</td></tr></table>");
							out.print("</td></tr>");
			out.print("<table width='100%' class='style6'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Proveedores Creados</div></td></tr>");
			out.print("<table width='100%' border='0' class='style6'><tr><td width='12%'><u><i><div align='left'>NIT</div></i></u></td><td width='88%'><u><i><div align='left'>Razon Social</div></i></u></td></tr>");
			out.print("<table width='100%' border='0' class='style6'>");
			String codigo1=null;
			String codigo2=null;
			rs1=mcp.ObtenerProveedor("0");
								
			try {
				while(rs1.next()){
					codigo2=rs1.getString(2);
					codigo1=rs1.getString(1);
					out.print("<tr><td width='12%'>"+codigo1+"</td>");
					out.print("<td>"+codigo2+"</td></tr>");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			out.print("<tr><td colspan='2'>&nbsp;</td></tr></table>");
		
		}

		
	String NomBanco=request.getParameter("NomBanco");
	if(va.equals("abc")){
	int con=0;
	String CodBanco="";
	String NombrBanco="";
	try {
		out.print("<table>");
		rs=mcp.BuscarBancos(NomBanco);
		while(rs.next()){
			out.print("<tr><td><a href='#' onclick='SeleccionarBanco("+rs.getString(1)+",&quot;"+rs.getString(2)+"&quot;)'>"+rs.getString(2)+"</a></td></tr>");
			NombrBanco=rs.getString(2);
			CodBanco=rs.getString(1);
			con=con+1;
			
		}
		out.print("<tr><td><input type='hidden' id='txtCont' value="+con+" />");
		//if(con==1){
			
					out.print(		"" +
					"<input type='hidden' id='txtNombBanco' value='"+NombrBanco+"' />" +
					"</td></tr>");
		
		out.print("<input type='hidden' id='txtCodBanco' value="+CodBanco+" /></table>");
	//	}
		rs.getStatement().getConnection().close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
			
		if(va.equals("AutoAeco")){
			String texto=request.getParameter("texto");
			rs=mcp.BuscarActividad(texto);
			out.println("<table>");
			
			try {
				while(rs.next()){
					out.println("<tr><td ><a class='rep' href='#' onclick='Aeco("+rs.getString(1)+")' >"+rs.getString(2)+" | "+rs.getString(3)+"</a></td><tr>");
				}rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.println("</table>");
		}

		if(va.equals("visualaeco")){
			
			String codaeco=request.getParameter("codaeco");
			rs=mcp.BuscarDatosActividad(codaeco);
			String cadena="";
			try {
				while(rs.next()){
					cadena=rs.getString(1)+"_"+rs.getString(2)+"_"+rs.getString(3)+"_"+rs.getString(4);				
				}rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.println(cadena);
			System.out.println("valor de la cadena "+cadena);
			
		}
		
		
		if(va.equals("Autorefte")){
			String texto=request.getParameter("texto");
			rs=mcp.BuscarRefte(texto);
			out.println("<table>");
			try {
				while(rs.next()){
					out.println("<tr><td><a class='rep' href='#' onclick='Arfte("+rs.getString(1)+","+rs.getDouble(2)+")'>"+rs.getDouble(2)+" | "+rs.getString(3)+" </a></td></tr>");
				}rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			out.println("</table>");
			
		}
		
		if(va.equals("Autoica")){
			String texto=request.getParameter("texto");
			rs=mcp.Buscarica(texto);
			out.println("<table>");
			try {
				while(rs.next()){
					out.println("<tr><td><a class='rep' href='#' onclick='Aica("+rs.getString(1)+","+rs.getString(2)+")'>"+rs.getString(2)+" | "+rs.getString(3)+" </a></td></tr>");
				}rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			out.println("</table>");
			
		}
		
		if(va.equals("MVerprove")){
			out.print("<table width='100%' class='style6'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Proveedores Creados</div></td></tr>");
			out.print("<tr><td colspan='2'><a href='#' onclick='CargarProve(0)' > Proveedores Activos </a><td><td colspan='2'><a href='#' onclick='CargarProve(1)' >Proveedores Inactivos </a></td></tr>");
		}
		
		
		if(va.equals("MProveedor")){			
			
			String iden=request.getParameter("iden");
			out.print("<table width='100%' border='0' class='style6' border='1' ><tr class='contpre' bgcolor='#E2EAF1'><td width='12%'><u><i><div align='left'>Identificacion</div></i></u></td><td width='70%'><u><i><div align='left'>Razon Social</div></i></u></td><td width='8%'><u><i><div align='left'>Estado</div></i></u></td><td width='10%'><u><i><div align='left'>Anotacion</div></i></u></td></tr>");
			
			
			String codigo1=null;
			String codigo2=null;
			String codigo3=null;
			rs1=mcp.ObtenerProveedor(iden);
								
			try {
				while(rs1.next()){
					codigo3=rs1.getString(3);
					codigo2=rs1.getString(2);
					codigo1=rs1.getString(1);
					System.out.println("valor de tipo"+rs1.getString(5));
					if(rs1.getString(5).equals("NI")){
						out.print("<tr class='rep'><td width='12%'>"+rs1.getString(5)+" "+codigo1+"-"+rs1.getString(6)+"</td>");		
					}else{
						out.print("<tr class='rep'><td width='12%'>"+rs1.getString(5)+" "+codigo1+"</td>");
					}
						
					out.print("<td><a  href='#'onclick='Actualizarp("+codigo3+")'>"+codigo2+"</a></td>");
					if(rs1.getString(4).equals("0")){
						out.print("<td>ACTIVO</td>");
					}else{
						out.print("<td><font color='red' >INACTIVO</font></td>");
					}
					rs3=mcp.VerificarAct(codigo3); //Verifica si el proveedor ha sido actualizado. 
					if(rs3.next()){
						out.print("<td>Actualizado Fecha : "+rs3.getString(1)+"</td>");
					}else{
						out.print("<td>NO ACTUALIZADO</td>");
					}rs3.getStatement().getConnection().close();
					out.print("</tr>");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			out.print("<tr><td colspan='2'>&nbsp;</td></tr></table>");
		
		}
		
		
		if(va.equals("3")){
			  
			 /* Aqui  se verifica si existe un formato con el mismo nombre.
			 */
			String codigo=null;
			rs=mcp.ObtenerCodigoProveedor(nit);
			try {
				if(rs.next()){
					codigo=rs.getString(1);
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			/**
			 * Si No existe Ninguno Con el mismo Nombre Se Procede a Ingresar El Nuevo Formato.
			 */
			
			if(codigo==null){
			
			mcp.CrearProveedor(nit,muni,telefono,fax,email,contacto,clase,razon,direccion,observacion,tc,dv,estado,diaspla);
			rs=mcp.ObtenerCodigoProveedor(nit);
			String cod="";
			
			try {
				if(rs.next()){
					cod=rs.getString(1);
				}
				java.util.Date fechaActual = new java.util.Date();
				java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
				java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());
				//System.out.println("valor de cta "+cta+" valor de aecon "+aecon);
				mcp.GuardarActEconoForma(cod,aecon,formapago,cta,rfte,ica,Fecha,Hora,user); //se ingresa la actividad economica, la rfte, el ica, la forma de pago y la cuenta si es trasnferencia 
				String tdep="";
				String tmuni="";
				rs1=mcp.BuscarMuni(muni);
				if(rs1.next()){
					tdep=rs1.getString(1);
					tmuni=rs1.getString(2);
				}
				mcp.GuardarContTerceros(nit,razon,telefono,Fecha,tdep,tmuni,direccion,cod,telcontacto,codbanco,cta,estado,clase,email,diaspla);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.print("Ingreso Exitoso.");
			
			}else{
				out.println("Ya existe un Proveedor con el mismo NIT Por Favor Intente nuevamente.");
			}
		}
		
		
		if(va.equals("1")){			
			out.print("<select name='cmbDpto' id='cmbDpto' onChange='Muni()' class='rep' style='width:100%'><option value='Seleccione'>Seleccione</option>");
			rs1=mcp.ObtenerDpto(pais);
			try {
				while(rs1.next()){
				out.print("<option value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("</select></label>");
			
		}
		
		if(va.equals("2")){			
			out.print("<select name='cmbMuni' id='cmbMuni' class='rep' style='width:100%' ><option value='Seleccione'>Seleccione</option>");
			rs1=mcp.ObtenerMuni(ciudad);
			try {
				while(rs1.next()){
				out.print("<option value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("</select></label>");
			
		}
		
		
		if(va.equals("Actualizarp")){	
			out.print("<table width='100%' class='style6'><tr><td ><div align='center' class='style11' id='cabecera2'>Modificar Proveedores</div></td></tr>");
		
			
			rs2=mcp.ObtenerProveedord(ni);
			String nmunic="";
			String cdept="";
			String ndept="";
			String cpais="";
			String npais="";
			try {
				if(rs2.next()){
					out.print("<tr><td>");
					out.print("<table class='style6' width='50%' >");
					out.print("<tr><td width='10%'>Razon Social</td><td width='25%'><label><input name='txtRazon' type='text' id='txtRazon' value='"+rs2.getString(8)+"' size='40' onkeyup='this.value=this.value.toUpperCase();' /></label></td><td width='10%'>Identificacion</td>");
					out.print("<td width='3%'><select  style='width:50px' name='tc' id='tc' >");
					out.print("<option value='"+rs2.getString(12)+"' >"+rs2.getString(12)+"</option>");
					rs1=mcp.ConsultarTiposDocumentos();
					try {
						while(rs1.next()){
							out.print("<option title='"+rs1.getString(2)+"' value='"+rs1.getString(1)+"'>"+rs1.getString(3)+"</option>");
						}
						rs1.getStatement().getConnection().close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					out.print("</select></td><td width='16%'><input name='txtNit' type='text' id='txtNit' size='40' onchange='validanit()' value='"+rs2.getString(1)+"' style='width:100%' /></td><td width='5%'><input type='text' id='dv'  size='3'  value='"+rs2.getString(13)+"' readonly='readonly' style='width:100%' /></td>");
					
					out.println("</table>");
					out.println("</tr></td>");
					
					out.print("<tr><td>" +
						"<table  class='style6' width='50%'>");
					out.print("<td width='6%' >Direccion : </td><td width='17%'><input name='txtDireccion' type='text' id='txtDireccion' size='50' value='"+rs2.getString(9)+"' onkeyup='this.value=this.value.toUpperCase();' style='width:100%' /></td><td width='8%' > &nbsp; Telefono :</td><td width='18%'><input name='txtTelefono' type='text' id='txtTelefono'  value='"+rs2.getString(3)+"' size='40'  style='width:100%' /></td>" +
						"<tr><td width='4%'> &nbsp; Fax :</td><td width='8%'><label><input name='txtFax' value='"+rs2.getString(4)+"' type='text' id='txtFax' size='10'  style='width:100%' /></td>");
					out.print("<td width='4%'> &nbsp; E-mail:</td><td width='11%'><input name='txtEmail' value='"+rs2.getString(5)+"' type='text' id='txtEmail' size='40' style='width:100%'/></td></tr>");
					out.print("<tr><td width='6%'>&nbsp;Contacto :</td><td width='20%'><label><input name='txtContacto' type='text' id='txtContacto' size='40' value='"+rs2.getString(6)+"'onkeyup='this.value=this.value.toUpperCase();' style='width:100%'/></label></td><td width='6%'>Tel. Contacto :</td><td width='5%'><input type='text' id='telconta' value='"+rs2.getString(17)+"' style='width:100%' /></td>");
					out.print("</tr>");
					out.print("</table>");
					out.print("</td></tr>");
					
					//obtengo el municipio
					rs1=mcp.ObtenerNMuni(rs2.getString(2));
					try {
						if(rs1.next()){
						nmunic=rs1.getString(1);
						cdept=rs1.getString(2);
						}
						rs1.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					
					//obtengo el departamento
					rs3=mcp.ObtenerNDpto(cdept);
					try {
						if(rs3.next()){
						ndept=rs3.getString(1);
						cpais=rs3.getString(2);
						}
						rs3.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					
					//obtengo el Pais
					rs4=mcp.ObtenerNPais(cpais);
					try {
						if(rs4.next()){
						npais=rs4.getString(1);
						}
						rs4.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					out.print("<tr><td>");
					out.print("<table width='50%' class='style6'>");
					out.print("<tr><td  width='6%'> &nbsp; Nacionalidad :</td><td width='6%'><select name='cmbPais' id='cmbPais' onChange='Dpto()' class='rep'><option value='"+cpais+"'>"+npais+"</option>");
					rs1=mcp.ObtenerPais();
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
					out.print("<td width='6%' > &nbsp; Departamento :</td><td width='10%'><label><div id='dpto'><select name='cmbDpto' id='cmbDpto'onChange='Muni()' class='rep' ><option value='"+cdept+"'>"+ndept+"</option>");
					rs5=mcp.ObtenerDpto(cpais);
					try {
						while(rs5.next()){
						out.print("<option value="+rs5.getString(1)+">"+rs5.getString(2)+"</option>");
						}
						rs5.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					out.print("</select></div></label></td>");
					out.print("<td width='5%'>&nbsp; Municipio: </td><td width='9%'><label><div id='Muni'><select name='cmbMuni' id='cmbMuni' style='width:100%' class='rep'><option value='"+rs2.getString(2)+"'>"+nmunic+"</option>");
					rs6=mcp.ObtenerMuni(cdept);
					try {
						while(rs6.next()){
						out.print("<option value="+rs6.getString(1)+">"+rs6.getString(2)+"</option>");
						}
						rs6.getStatement().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					out.print("</select></div></label></td>");
					out.print("</table>");
					out.print("</td></tr>");
					out.print("<tr><td>");
					
					//Obtener la forma de Pago y la actividad economica //
					rs=mcp.BusActFp(ni);
					String codacteco="";
					String nombeacteco="";
					String codigociiu="";
					String codcre="";
					String val_cre="";
					String cod_ica="";
					String val_ica="";
					String cod_rfte="";
					String val_rfte="";
					String codfp="";
					String descfp="";
					String ctadepago="";
					if(rs.next()){
						codacteco=rs.getString(1);
						nombeacteco=rs.getString(2);
						codigociiu=rs.getString(3);
						codcre=rs.getString(4);
						val_cre=rs.getString(5);
						cod_ica=rs.getString(10);
						val_ica=rs.getString(11);
						cod_rfte=rs.getString(8);
						val_rfte=rs.getString(9);
						codfp=rs.getString(12);
						descfp=rs.getString(6); 
						ctadepago=rs.getString(7);
					}rs.getStatement().getConnection().close();
					//fin forma de Pago y la actividad economica //
					
					//Busqueda de Banco///
					String nombrebank="";
					rs=mcp.BusBanco(rs2.getString(15));
					if(rs.next()){
						nombrebank=rs.getString(2);
					}rs.getStatement().getConnection().close();
					
					//Fin Banco//
									out.print("<table width='50%' class='style6'   >");
									out.print("<tr><td width='6%'>Banco :</td><td width='17%'><input id='txtBanco' size='30' onkeyup='autocompletaBanco()' value='"+nombrebank+"' style='width:100%' /></td><td width='7%'> Forma de Pago : </td><td width='5%'><label><select id='fp' onchange='FormaPago()' class='rep' >");
									out.print("<option value='"+codfp+"' >"+descfp+"</option>");
									rs=mcp.BuscarformasdePago();
									try {
										while(rs.next()){
											out.print("<option value='"+rs.getString(1)+"' >"+rs.getString(2)+"</option>");
										}rs.getStatement().getConnection().close();
									} catch (SQLException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
					
							
									out.print("</select></label></td>");
									
									out.print("<td width='6%'> Dias de Plazo: </td><td width='3%'><input type='text' id='diap' size='3' value='"+rs2.getString(14)+"' style='width:100%' /></td></tr>");
									out.print("<tr><td></td><td><div id='banco'><input type='hidden' id='txtCodBanco' value="+rs2.getString(15)+" /></div></td><td></td><td><div id='vfp' >");
									if(codfp.equals("3")){
										 out.println("Cta. <input type='text' id='cta' size='20' onblur='TempFormaPago()' value='"+ctadepago+"'/>");
									}
									out.print("</div></td><td colspan='2'></td></tr>");
									//out.print("<tr><td width='5%'> Regimen: </td><td width='8%'><label><select name='cmbclase' id='cmbClase' class='rep' style='width:100%' ><option value='Seleccione'>Seleccione</option><option value='Regimen Comun'>Regimen Comun</option><option value='Regimen Simplificado'>Regimen Simplificado</option><option value='Grandes Contribuyentes'>Grandes Contribuyentes</option></select></label></td>");
									out.print("<tr>");
									if(rs2.getString(7).equals("Regimen Comun")){
										out.print("<td>Regimen: </td><td><label><select name='cmbclase' id='cmbClase' class='rep'><option value='"+rs2.getString(7)+"'>"+rs2.getString(7)+"</option><option value='Regimen Simplificado'>Regimen Simplificado</option><option value='Grandes Contribuyentes'>Grandes Contribuyentes</option></select></label></td>");
									}else{if(rs2.getString(7).equals("Regimen Simplificado")){
										out.print("<td> Regimen :</td><td><label><select name='cmbclase' id='cmbClase' class='rep' ><option value='"+rs2.getString(7)+"'>"+rs2.getString(7)+"</option><option value='Regimen Comun'>Regimen Comun</option><option value='Grandes Contribuyentes'>Grandes Contribuyentes</option></select></label></td>");
									}else{out.print("<td>Regimen : </td><td><label><select name='cmbclase' id='cmbClase' class='rep' ><option value='"+rs2.getString(7)+"'>"+rs2.getString(7)+"</option><option value='Regimen Comun'>Regimen Comun</option><option value='Regimen Simplificado'>Regimen Simplificado</option></select></label></td>");
									}
									}
									
									out.print("<td width='5%' > Estado : </td><td width='4%'><select id='estad' class='rep' >");
									if(rs2.getString(18).equals("0")){
										out.print("<option value='0'>ACTIVO</option><option value='1' >INACTIVO</option>");
									}else{
										out.print("<option value='1' >INACTIVO</option><option value='0'>ACTIVO</option>");
									}
									out.print("</select></td>");
									out.print("</tr>");
									out.print("</table>");
									out.print("</td></tr>");
									out.print("<tr><td>");
									out.print("<table width='50%' class='style6'>");
									out.print("<tr><td width='8%'> Actividad Economica: </td><td width='12%'><input type='text' id='aecon' size='40' onkeyup='autoAecon()' value='"+codigociiu+" | "+nombeacteco+"' style='width:100%'/></td></tr>");
									out.print("<tr><td><input type='hidden'  id='codaecon' value='"+codacteco+"' /></td><td><div id='veacon' >");
									if(codacteco.equals("")){
										
									}else{
										System.out.println("entrando a val de actividades economicas ");
										out.print("<table width='100%'><tr class='rep' align='center' bgcolor='#BADBFB' ><td width='14%'><b>Codigo CIIU</b></td><td width='50%'><b>Nombre de la Actividad</b></td><td width='9%'><b>Retefuente</b></td><td width='9%'><b>ICA</b></td><td width='9%'><b>CRE</b></td></tr>" +
										"<tr class='rep' ><td width='14%'>"+codigociiu+"</td><td width='50%'>"+nombeacteco+"</td><td width='9%'><input type='text'id='fte' onkeyup='Retefuente("+codacteco+")' value='"+val_rfte+"' size='4'/></td><td width='9%'><input tye='text' id='ica'  onkeyup='valica("+codacteco+")' value='"+val_ica+"' size='4'/></td><td width='9%'>"+val_cre+"</td></tr>"+
										"<tr><td colspan='2' width='64%'><input type='hidden' id='codfte' value='"+cod_rfte+"'size='8'/><input type='hidden' id='codica'  value='"+cod_ica+"' size='8' /></td><td ><div id='vistafte'></div></td><td ><div id='vistaica'></div></td><td></td></tr></table>");
									}
									out.print("</div></td></tr>");
									out.print("<td width='5%'> Observacion :</td><td  width='18%'><label><textarea name='txtDescripcion' id='txtDescripcion' cols='32' rows='2' style='width:100%' >"+rs2.getString(10)+"</textarea></label></td>");
									out.print("<tr><td colspan='4'><div align='center'><label><input type='button' name='btnIngresarProveedor' id='btnIngresarProveedor' value='     Modificar     ' onClick='ModificarProveedor("+ni+")'></label></div></td></tr>");
									out.print("<tr><td colspan='2'>&nbsp;</td></tr></table>");
									out.print("<input type='hidden' id='codterc'  value='"+rs2.getString(11)+"' />");
					
					/*out.print("<tr><td width='12%'>Nacionalidad</td><td><label><select name='cmbPais' id='cmbPais' onChange='Dpto()'><option value='"+cpais+"'>"+npais+"</option>");
					rs1=mcp.ObtenerPais();
					try {
						while(rs1.next()){
						out.print("<option value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
						}
						rs1.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					out.print("</select></label></td>");
					out.print("<td width='12%'>Departamento</td><td><label><div id='dpto'><select name='cmbDpto' id='cmbDpto' onChange='Muni()'><option value='"+cdept+"'>"+ndept+"</option>");
					rs5=mcp.ObtenerDpto(cpais);
					try {
						while(rs5.next()){
						out.print("<option value="+rs5.getString(1)+">"+rs5.getString(2)+"</option>");
						}
						rs5.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					out.print("</select></div></label></td></tr>");
					out.print("<tr><td width='12%'>Municipio</td><td><label><div id='Muni'><select name='cmbMuni' id='cmbMuni' ><option value='"+rs2.getString(2)+"'>"+nmunic+"</option>");
					rs6=mcp.ObtenerMuni(cdept);
					try {
						while(rs6.next()){
						out.print("<option value="+rs6.getString(1)+">"+rs6.getString(2)+"</option>");
						}
						rs6.getStatement().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					out.print("</select></div></label></td>");
					*/
					/*if(rs2.getString(7).equals("Regimen Comun")){
						out.print("<td>Clase Contribuyente</td><td><label><select name='cmbclase' id='cmbClase' ><option value='"+rs2.getString(7)+"'>"+rs2.getString(7)+"</option><option value='Regimen Simplificado'>Regimen Simplificado</option><option value='Grandes Contribuyentes'>Grandes Contribuyentes</option></select></label></td></tr>");
					}else{if(rs2.getString(7).equals("Regimen Simplificado")){
						out.print("<td>Clase Contribuyente</td><td><label><select name='cmbclase' id='cmbClase' ><option value='"+rs2.getString(7)+"'>"+rs2.getString(7)+"</option><option value='Regimen Comun'>Regimen Comun</option><option value='Grandes Contribuyentes'>Grandes Contribuyentes</option></select></label></td></tr>");
					}else{out.print("<td>Clase Contribuyente</td><td><label><select name='cmbclase' id='cmbClase' ><option value='"+rs2.getString(7)+"'>"+rs2.getString(7)+"</option><option value='Regimen Comun'>Regimen Comun</option><option value='Regimen Simplificado'>Regimen Simplificado</option></select></label></td></tr>");
					}
					}
					out.print("<tr><td width='12%'>Contacto</td><td width='30%'><label><input name='txtContacto' type='text' id='txtContacto' value='"+rs2.getString(6)+"' size='40'/></label></td></tr>");
					
					//Obtener la forma de Pago y la actividad economica //
					rs=mcp.BusActFp(ni);
					String codacteco="";
					String nombeacteco="";
					String codigociiu="";
					String codcre="";
					String val_cre="";
					String cod_ica="";
					String val_ica="";
					String cod_rfte="";
					String val_rfte="";
					String codfp="";
					String descfp="";
					String ctadepago="";
					if(rs.next()){
						codacteco=rs.getString(1);
						nombeacteco=rs.getString(2);
						codigociiu=rs.getString(3);
						codcre=rs.getString(4);
						val_cre=rs.getString(5);
						cod_ica=rs.getString(10);
						val_ica=rs.getString(11);
						cod_rfte=rs.getString(8);
						val_rfte=rs.getString(9);
						codfp=rs.getString(12);
						descfp=rs.getString(6); 
						ctadepago=rs.getString(7);
					}rs.getStatement().getConnection().close();
					
					//fin// 
					out.print("<td>Forma de Pago :</td><td> <label><select id='fp' onchange='FormaPago()'>");
					
					out.print("<option value='"+codfp+"' >"+descfp+"</option>");
					rs=mcp.BuscarformasdePago();
					try {
						while(rs.next()){
							out.print("<option value='"+rs.getString(1)+"' >"+rs.getString(2)+"</option>");
						}rs.getStatement().getConnection().close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					out.print("</select></label></td><td><div id='vfp' >");
					if(codfp.equals("3")){
						 out.println("Cta. <input type='text' id='cta' size='20' onblur='TempFormaPago()' value='"+ctadepago+"'/>");
					}
					out.print("</div></td></tr>");
					
					out.print("<tr><td>Actividad Economica: </td><td><input type='text' id='aecon' size='40' onkeyup='autoAecon()' value='"+codigociiu+" | "+nombeacteco+"' class='rep'/>");
					out.print("</td></tr>");
					out.print("<tr><td><input type='hidden'  id='codaecon' value='"+codacteco+"' /></td>");
					out.print("<td><div id='veacon' >");
					if(codacteco.equals("")){
						
					}else{
						System.out.println("entrando a val de actividades economicas ");
						out.print("<table width='100%'><tr class='rep' align='center' bgcolor='#BADBFB' ><td width='14%'><b>Codigo CIIU</b></td><td width='50%'><b>Nombre de la Actividad</b></td><td width='9%'><b>Retefuente</b></td><td width='9%'><b>ICA</b></td><td width='9%'><b>CRE</b></td></tr>" +
						"<tr class='rep' ><td width='14%'>"+codigociiu+"</td><td width='50%'>"+nombeacteco+"</td><td width='9%'><input type='text'id='fte' onkeyup='Retefuente("+codacteco+")' value='"+val_rfte+"' size='4'/></td><td width='9%'><input tye='text' id='ica'  onkeyup='valica("+codacteco+")' value='"+val_ica+"' size='4'/></td><td width='9%'>"+val_cre+"</td></tr>"+
						"<tr><td colspan='2' width='64%'><input type='hidden' id='codfte' value='"+cod_rfte+"'size='8'/><input type='hidden' id='codica'  value='"+cod_ica+"' size='8' /></td><td ><div id='vistafte'></div></td><td ><div id='vistaica'></div></td><td></td></tr></table>");
					}
					out.print("</div></td></tr>");
					out.print("<tr><td>Observacion</td><td><label><textarea name='txtDescripcion' id='txtDescripcion' cols='32' rows='3' >"+rs2.getString(10)+"</textarea></label></td></tr>");
					out.print("<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>");
					out.print("<tr><td colspan='4'><div align='center'><label><input type='button' name='btnIngresarProveedor' id='btnIngresarProveedor' value='     Modificar     ' onClick='ModificarProveedor("+ni+")'></label></div></td></tr>");
					out.print("<tr><td colspan='2'>&nbsp;</td></tr></table>");
					out.print("<input type='hidden' id='codterc'  value='"+rs2.getString(11)+"' />");*/
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		if(va.equals("MP")){
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
			java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());
			
			String codact=request.getParameter("codact");
			String codfp=request.getParameter("codfp");
			String codica=request.getParameter("codica");
			String codrfte=request.getParameter("codrfte");
			String valrfte=request.getParameter("valrfte");
			String valica=request.getParameter("valica");
			String codctapago=request.getParameter("codctapago");
			String codterc=request.getParameter("codterc");
			  int sw=0;
			 /* Aqui  se verifica si existe un formato con el mismo nombre.
			 */
			String codigo=null;
			
			//el nit es nit
			//el codpp es cod
			
			rs=mcp.ObtenerCodigoProveedor(nit);
			try {
				while(rs.next()){
					if(!rs.getString(1).equals(codpp)){
						sw=1;
					}
					codigo=rs.getString(1);
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
			if(sw==0){
			rs=mcp.DatosProve(codpp);
			String descripcion="";
			try {
				if(rs.next()){
					descripcion=" ENCONTRADO Razon Social='"+rs.getString(8)+"' Identificacion='"+rs.getString(12)+"''"+rs.getString(1)+"'-'"+rs.getString(13)+"' Direccion='"+rs.getString(9)+"' Telefono='"+rs.getString(3)+"' Fax='"+rs.getString(4)+"' Regimen='"+rs.getString(7)+"' banco='"+rs.getString(18)+"' ";
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			mcp.Auditoria(user,descripcion,codpp,Fecha,Hora);
			mcp.ModificarProveedor(nit,muni,telefono,fax,email,contacto,clase,razon,direccion,observacion,codpp, tc, dv, estado, diaspla);
			mcp.ActualizarActEconoForma(codpp, codact, codfp, codctapago, codrfte, codica, Fecha, Hora, user);
			try {
				//System.out.println("valor de razon "+razon);
				mcp.ActualizaContTerceros(nit, razon, telefono, Fecha, ciudad, muni, direccion, codterc,telcontacto,codbanco,cta,estado,clase,email,diaspla);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.print("Ingreso Exitoso.");
			}else{
			out.print("Ya existe un proveedor con el mismo NIT.");	
			}
			
		}
		
		if(va.equals("FormaPago")){
		 String fp=request.getParameter("fp");
		 if(fp.equals("3")){
			 out.println("Cta. <input type='text' id='cta' size='20' onblur='TempFormaPago()'/>");
		 }
		}
		
		
		
	}

}
