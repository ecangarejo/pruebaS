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

import ord_metodo.MetodoProductos;



public class ControlProductos extends HttpServlet{
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String va = req.getParameter("valor");
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out;
		out=res.getWriter();
		MetodoProductos mp=new MetodoProductos();
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		ResultSet rs3=null;
		ResultSet rs4=null;
		ResultSet rs5=null;
		ResultSet rs6=null;
		ResultSet rs7=null;
		ResultSet rs8=null;
		ResultSet rsp=null;
		ResultSet rsl=null;
		ResultSet rsa=null;
		String user=req.getParameter("user");
		java.util.Date fechaS = new Date();
		Calendar c = new GregorianCalendar();
		String diaa = Integer.toString(c.get(Calendar.DATE));
		String mess = Integer.toString(c.get(Calendar.MONTH));
		String annio = Integer.toString(c.get(Calendar.YEAR));
		String ocodprove=req.getParameter("codprove");
		String ocodref=req.getParameter("codref");
		String onombre=req.getParameter("nombre");
		String ocodprodu=req.getParameter("codprodu");
		String ocant=req.getParameter("cant");
		String opunit=req.getParameter("punit");
		String osubtotal=req.getParameter("subtotal");
		String oiva=req.getParameter("iva");
		String optotal=req.getParameter("ptotal");
		String ocodiiva=req.getParameter("codiiva");
		String ocodtarifa=req.getParameter("codtarifa");
		String odesc=req.getParameter("desc");
		String ouser=req.getParameter("user");
		String ofechaentrega=req.getParameter("fechaentrega");
		String oconcep=req.getParameter("concep");
		String osuc=req.getParameter("suc");
		String occosto=req.getParameter("ccosto");
		String osubcc=req.getParameter("subcc");
		String ocodsuccosto=req.getParameter("codsuccosto");
		String ocodcensubcc=req.getParameter("codcensubcc");
		String oformapago=req.getParameter("formapago");
		
	if(va.equals("GuardarG")){
		
		String nombreg=req.getParameter("nameg");
		String estg=req.getParameter("estg");
		java.util.Date fechaActual = new java.util.Date();
		java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
		java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());
		int val=0;
		rs=mp.BuscarGrupo(nombreg);
		try {
			if(rs.next()){
				//out.println("YA EXISTE UN GRUPO CON ESTE NOMBRE  ");
				val=1;
			}else{
				mp.GuardarGrupo(nombreg, estg, user, Fecha, Hora); //Guarda registro en la tabla ord_grupo
				System.out.println("GRUPO CREADO EXITOSAMENTE, RECUERDE COMUNICARSE CON EL DPTO DE CONTABILIDAD PARA QUE SEA ASIGANADA UNA CUENTA AUXILIAR");
			val=2;
			}
			
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.println(val);
		System.out.println(val);
	}
	
	
	if(va.equals("MostrarG")){
		out.println("<table width='100%'>");
		out.println("<td colspan='4' id='cabecera2' class='style11' ><div align='center'>GRUPOS CREADOS</div></td>");
		out.println("<tr ><td><b>Nombre de Grupo </b></td><td> <b>No. Cuenta Asociada </b></td></tr>");
		 rs=mp.MostrarGrupos();
		 try {
			while (rs.next()){
				 out.println("<tr class='rep' ><td>"+rs.getString(1)+"</td>");
				 if(rs.getString(2).equals("0")){
					
					 out.println("<td>No existe una cuenta asignada a este grupo</td> ");
				 }else{
					 out.println("<td>"+rs.getString(3)+"</td>");
				 }
				 out.println("</tr>");
			 }rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}//finMostrarG
	
	if(va.equals("GuardarTP")){
		
		/*Los tipos de productos se guardan en la misma tabla de grupos (farc_grupos) de medicamentos, nunca se podran modificar los
		  grupos existentes como lo son Medicamentos y Dispositivos Medicos, se realiza esta opcion para que se 
		  pueda clasificar los productos denominados como otros(pc, baldosas, servicio de mtto, entre otros )
		*/
		String nametp=req.getParameter("nameTP");
		java.util.Date fechaActual = new java.util.Date();
		java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
		java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());
		rs=mp.BuscarTp(nametp);
		int vali=0;
		try {
			if(rs.next()){
				vali=1;
				//out.println("YA EXISTE UN TIPO DE PRODUCTO CON ESTE NOMBRE");
			}else{
				mp.GuardarTipoP(nametp,user,Fecha,Hora);
				vali=2;
				//out.println("Tipo de Producto "+nametp+" creado exitosamente ");
			}rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.println(vali);
		
	}
	
	
	if(va.equals("MostrarT")){
		out.println("<table width='100%'>");
		out.println("<td colspan='4' id='cabecera2' class='style11' ><div align='center'>TIPOS DE PRODUCTO CREADOS</div></td>");
		out.println("<tr ><td><b>Nombre </b></td></tr>");
		 rs=mp.MostrarTipos();
		 try {
			while (rs.next()){
				 out.println("<tr class='rep' ><td>"+rs.getString(1)+"</td>");
				
				 out.println("</tr>");
			 }rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}
	
	
	if(va.equals("GuardarP")){
		
		String nameP=req.getParameter("nameP");
		String codref=req.getParameter("codref");
		String tipop=req.getParameter("tprod");
		java.util.Date fechaActual = new java.util.Date();
		java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
		java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());
		System.out.println("user "+user);
		rs=mp.BuscarProducto(nameP);
		int v=0;
		try {
			if(rs.next()){
				v=1;
				//Ya existe un producto con este nombre 
			}else{
				/*Guardo los registros de producto, pero ojo solo los que sean de tipo
				 diferente a medicamento o insumos medicos(dispositivos medicos
				 * */
				mp.GuardarProducto(codref,nameP,Fecha,Hora,user,tipop);
				v=2;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.println(v);
	}
	
	
	if(va.equals("Agrupo")){
	
		out.println("<table width='100%'>");
		out.println("<tr><td id='cabecera2' class='style11' colspan='5' align='center' ><br>ASIGNACIONES  DE GRUPO A UN PRODUCTO </td>");
		out.println("<tr>");
			out.println("<td>");
				out.println("PRODUCTO :");
				out.println("<input type='text' id='prod' onkeyup='autocompletaProd(1,0)' size='25' />");
			out.println("</td>");
			out.println("<td>");
				out.println(" GRUPO :");
			out.println("</td>");
			out.println("<td>");
			 	rs=mp.BuscarGrupo();
			 	out.println("<select id='bgrupo' ><option value='---' >---</option> ");
			 	try {
					while(rs.next()){
					   out.println("<option value='"+rs.getString(1)+"' >"+rs.getString(2)+" | "+rs.getString(4)+"</option>");
					}rs.getStatement().getConnection().close();
				out.println("</select>");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			out.println("</td>");
			out.println("<td>");
			out.println("<input type='button' id='asig' onclick='GAsignacion()' value='Asignar'/>");
			out.println("</td>");
		out.println("</tr>");
		out.println("<tr>");
			out.println("<td>");
			out.println("<div id='vp' ></div><div id='desc' ></div></td><td><input type='hidden' id='codpro' />");
			out.println("</td>");
		out.println("</tr>");
		out.println("</table>");
	}
	
	if(va.equals("Aprovee")){
		out.println("<table width='100%'>");
		out.println("<tr><td id='cabecera2' class='style11' colspan='5' align='center' ><br>ASIGNACIONES  DE PROVEEDOR A UN PRODUCTO </td></tr>");
		out.println("<tr><td> PROVEEDOR : <input type='text' id='provee' onkeyup='autocompletaProvee()' size='30'/></td>");
		out.println("<td> PRODUCTO : <input type='text' id='prod' onkeyup='autocompletaProd(2,0)' size='30'/></td>");
		out.println("<td>");
		out.println("<input type='button' id='asig' onclick='GAsigProvee()' value='Asignar'/>");
		out.println("</td>");
		out.println("</tr>");
		out.println("<tr>");
			out.println("<td>");
			out.println("<div id='vistapro' > </div><div id='dpro'> </div><input type='hidden' id='cprove' /> ");
			out.println("</td>");
			out.println("<td>");
			out.println("<div id='vp' ></div><div id='desc' ></div><input type='hidden' id='codpro' />");
			out.println("</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td></td>");
		out.println("</tr>");
		out.println("</table>");
		
	}
	
	if(va.equals("AutoProd")){
	
		String texto=req.getParameter("texto");
		String ident=req.getParameter("ident");
		String prove=req.getParameter("cprovee");
		System.out.println("Valor de provee "+prove);
		String nombre="";
		rs=mp.BuscarProd(texto, ident,prove);
		out.println("<table>");
		try {
			while(rs.next()){
				nombre=rs.getString(3);
				out.println("<tr><td><a class='rep' href='#' onclick=Producto("+rs.getString(1)+")>"+rs.getString(3)+" | "+rs.getString(2)+" | "+rs.getString(4)+"</a></td></tr>");
			}rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.println("</table>");
		//"+rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+"
	}
	
	if(va.equals("AutoProvee")){
		
		String texto=req.getParameter("texto");
		System.out.println("Entrando a AutoProvee "+texto);
		String ident=req.getParameter("ident");
		String nombre="";
		rs=mp.BuscarProvee(texto);
		out.println("<table>");
		try {
			while(rs.next()){
				nombre=rs.getString(3);
				out.println("<tr><td><a class='rep' href='#' onclick=Proveedor("+rs.getString(1)+")>"+rs.getString(3)+" | "+rs.getString(2)+"</a></td></tr>");
			}rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.println("</table>");
		//"+rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+"
	}
	
	if(va.equals("visualprove")){
		String codprove=req.getParameter("codprove");
		System.out.println("valor de codprove "+codprove);
		rs=mp.BuscarProveedor(codprove);
		String cadena="";
		try {
			while(rs.next()){
				cadena=rs.getString(1)+"_"+rs.getString(2)+"_"+rs.getString(3);				
			}rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.println(cadena);
		System.out.println("valor de la cadena "+cadena);
	}
	
	
	if(va.equals("visual")){
		String codprod=req.getParameter("codprod");
		System.out.println("valor de codprod"+codprod);
		rs=mp.BuscarP(codprod);
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
	if(va.equals("SaveAsigG")){
		
		String codprod=req.getParameter("codprod");
		String codgrupo=req.getParameter("codgrupo");
		java.util.Date fechaActual = new java.util.Date();
		java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
		java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());
		rs=mp.BuscarProductoenGrupo(codprod);
		int vali=0;
		try {
			if(rs.next()){
				vali=1;
			}else{
				mp.GuardarAsigGrupo(codprod,codgrupo,user,Fecha,Hora);
				vali=2;
			}
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.println(vali);
	}
	
	if(va.equals("SaveAsigProve")){
		
		String cprovee=req.getParameter("cprove");
		String cproducto=req.getParameter("cproducto");
		java.util.Date fechaActual = new java.util.Date();
		java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
		java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());
		
		rs=mp.BuscarproductoenProve(cproducto,cprovee);
		int vale=0;
		try {
			if(rs.next()){
				vale=1;
			}else{
				mp.GuardarAsigProve(cproducto,cprovee,user,Fecha,Hora);
				vale=2;
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		out.println(vale);
		
	}
	
	if(va.equals("Atarifa")){
		
		out.println("<table width='100%'>");
		out.println("<tr><td id='cabecera2' class='style11' colspan='5' align='center' ><br>ASIGNACION DE TARIFA </td></tr>");
		out.println("<tr><td>Seleccione Proveedor : <select id='lprove' onchange='RadioTarifa()'><option value='---' >---</option>");
		rs=mp.BuscarProveedores();
		try {
			while(rs.next()){
				out.println("<option value='"+rs.getString(1)+"' >"+rs.getString(3)+" | "+rs.getString(2)+"</option>");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		out.println("</select></td></tr>");
		out.println("<tr><td width='80%'><div id='vistradios' ><br></div></td></tr>");
		out.println("<tr><td width='80%'><div id='vistproduc' ><br></div></td></tr>");
		out.println("</table>");
		
		
	}
	
	if(va.equals("RadioTarifa")){
		String provee=req.getParameter("provee");
		out.println("<br>");
		out.println("<br>");
		out.println("<table align='center' width='100%'>");
		out.println("<tr><td id='cabecera2' class='style11' colspan='3' align='center' ><br>OPCIONES</td></tr>");
		out.println("<tr>");
		out.println("<td align='center' width='20%'><label><input name='radiobuttonu' type='radio' value='6' id='ProductosT'  onclick='RadioT("+provee+")'  >Ver Productos Tarifados</label></td>");
		out.println("<td align='center' width='20%'><label><input name='radiobuttonu' type='radio' value='6' id='Lprod'  onclick='RadioT("+provee+")'  >Productos por Tarifar</label></td>");
		out.println("<td align='center' width='20%'><label><input name='radiobuttonu' type='radio' value='6' id='PTarifar'  onclick='RadioT("+provee+")'  >Asignar tarifa por Producto</label></td>");
		out.println("</tr>");
		out.println("</table>");
	}
	
	if(va.equals("ProductosT")){
		String provee=req.getParameter("cprove");
		rs=mp.BuscarListaProductos(provee);//Busca el lista de producto segun el rpoveedor, va a la tabla ord_prove_prod
		out.println("<br><br>");
		out.println("<table border='1'>");
		out.println("<tr class='contpre'><td>Cod. Referencia</td><td>Nombre de Producto</td><td>Tipo de Producto</td><td>Cuenta</td><td>Valor Neto</td><td>IVA</td><td></td></tr>");
		int vali=0;
		try {
			while(rs.next()){
			rs1=mp.BuscarTarifas(rs.getString(2),provee);					
				while(rs1.next()){
					
						//out.println("<tr><td>prueba 2 </td></tr>");
					out.println("<tr class='rep'><td><br>"+rs.getString(3)+"</td><td><br>"+rs.getString(4)+"</td><td><br>"+rs.getString(5)+"</td><td><br>"+rs.getString(7)+"</td><td align='center'><br>"+FormatMoneda(rs1.getString(4))+"</td><td align='center'><br>"+rs1.getString(9)+"</td><td><br><a href='#' onclick='ElimTarifa("+rs.getString(1)+","+rs.getString(2)+","+rs1.getString(1)+")' >Eliminar</a></td></tr>");	
					}
				
				
				rs1.getStatement().getConnection().close();
			}rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.println("</table>");
		
	}
	
	if(va.equals("PTarifar")){
		String cprovee=req.getParameter("cprove");
		System.out.println("valor de provee en PTarifar "+cprovee);
		out.println("<br>");
		out.println("<br>");
		out.println("<table>");
		out.println("<tr><td>Seleccione Producto : <input type='text' id='prod' onkeyup='autocompletaProd(3,"+cprovee+")' size='40'/></td><td>&nbsp; &nbsp; Digite Tarifa: <input type='text' onkeyup='checknu()' id='tarifa' size='10' /> &nbsp; &nbsp;<td>Iva: <input type='text' id='iv' onkeyup='checkiva(1,1)' size='3'/></td> <td> <input type='button' id='asigt'  value='Asignar' onclick='AsT("+cprovee+")' /></td></tr>");
		out.println("<tr><td><div id='vp' ></div><div id='desc' ></div><input type='hidden' id='codpro' /></td><td></td><td><div id='vistiva'></div><input type='hidden' id='codiva' /></td></tr>");
		out.println("</table>");
	}
	if(va.equals("Vistiva")){
		String texto=req.getParameter("texto");
		String i=req.getParameter("i");
		String iden=req.getParameter("iden");
		rs=mp.BuscarIva(texto);
		out.println("<table>");
		try {
			while(rs.next()){
				out.println("<tr><td><a class='rep' href='#' onclick='Asiva("+rs.getString(2)+","+iden+","+rs.getString(1)+","+i+")' >"+rs.getString(2)+" - "+rs.getString(3)+"</a></td></tr>");
			}rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.println("</table>");
		
	}
	
	
	if(va.equals("SaveTProd")){
		String cprodu=req.getParameter("cprodu");
		String tarif=req.getParameter("tarif");
		String cprove=req.getParameter("cprove");
		String civa=req.getParameter("civa");
		java.util.Date fechaActual = new java.util.Date();
		java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
		java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());
		try {
			mp.GuardarTarifas(cprove,cprodu,user,Fecha,Hora,tarif,civa);  
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	if(va.equals("Lprod")){
		String cprove=req.getParameter("cprove");
		rs=mp.BuscarListaProductos(cprove);//Busca el lista de producto segun el rpoveedor, va a la tabla ord_prove_prod
		out.println("<br><br>");
		out.println("<table frame='above'>");
		out.println("<tr class='contpre'><td>Cod. Referencia</td><td>Nombre de Producto</td><td>Tipo de Producto</td><td>Cuenta</td><td>Valor Neto</td><td>Iva</td><td></td></tr>");
		int i=0;
		try {
			while(rs.next()){
			rs1=mp.BuscarTarifas(rs.getString(2),cprove);	
				if(rs1.next()){ 
					
				}else{
					i=i+1;
					out.println("<tr class='rep'><td><br>"+rs.getString(3)+"</td><td><br>"+rs.getString(4)+"</td><td><br>"+rs.getString(5)+"</td><td><br>"+rs.getString(7)+"</td><td><br><input type='text' id='tarifa"+i+"' size='8' onkeyup='checknu()' /></td><td><br><input type='text' id='iv"+i+"' size='4' onkeyup='checkiva(2,"+i+")' /> </td><td><br><a href='#' onclick='AsigTarifa("+rs.getString(1)+","+rs.getString(2)+","+i+")' id='asignt"+i+"' >Asignar</a></td></tr>");	
					out.println("<tr><td colspan='5'></td><td><div id='vistiva"+i+"'></div><input type='hidden' id='codiva"+i+"' /></td></tr> ");
				}
				rs1.getStatement().getConnection().close();
			}rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.println("</table>");
	}
	
	if(va.equals("SaveTarifa")){
		String cprove=req.getParameter("cprove");
		String cprodu=req.getParameter("cprodu");
		String tarifa=req.getParameter("tarif");
		String civa=req.getParameter("civa");
		java.util.Date fechaActual = new java.util.Date();
		java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
		java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());
		rs=mp.BuscarTarifas(cprodu,cprove);
		try {
			if(rs.next()){
				
			}else{
				mp.GuardarTarifas(cprove,cprodu,user,Fecha,Hora,tarifa,civa);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	if(va.equals("ElimTarifa")){
		
		String cprove=req.getParameter("cprove");
		String cprodu=req.getParameter("cprodu");
		String codtarifa=req.getParameter("codtarifa");
		java.util.Date fechaActual = new java.util.Date();
		java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
		java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());
		
		try {
			mp.BorrarTarifa(codtarifa,cprove,cprodu,Fecha,Hora,user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	if(va.equals("BusquedaProve")){
		String texto=req.getParameter("texto");
		rs=mp.BuscarProveparaOrdenCompra(texto);
		out.println("<table>");
		try {
			while(rs.next()){
				out.println("<tr><td><a class='rep' href='#' onclick='ProvOrd("+rs.getString(1)+")' >"+rs.getString(2)+" | "+rs.getString(3)+"</a></td></tr>");
			}rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		out.println("</table>");
	}
	
	if(va.equals("Orden")){
		String codprove=req.getParameter("codprove");
		rs=mp.BuscarProveOrdenCompra(codprove);
		out.println("<table width='100%'>");
		out.println("<tr>"+
			" <td colspan='4' id='cabecera2' class='style11' ><div align='center'>ORDEN DE COMPRA</div></td> "+
		    "</tr>");
		try {
			while(rs.next()){
			out.println("<tr>");
			out.println("<td width='10%'>");
			out.println("Proveedor");
			out.println("</td>");
			out.println("<td class='rep'>");
			out.println(rs.getString(3));
			out.println("</td>");
			out.println("<td width='20%'>");
			out.println("Actividad Economica : ");
			out.println("</td>");
			out.println("<td class='rep' >");
			out.println(rs.getString(4)+" | "+rs.getString(5));
			out.println("</td>");
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td width='20%'>");
			out.println("Direccion : ");
			out.println("</td>");
			out.println("<td class='rep'>");
			out.println(rs.getString(6));
			out.println("</td>");
			out.println("<td width='20%'>");
			out.println("Telefono : ");
			out.println("</td>");
			out.println("<td class='rep' >");
			out.println(rs.getString(7));
			out.println("</td>");
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td width='20%'>");
			out.println("Forma de Pago: ");
			out.println("</td>");
			rs1=mp.BuscarFormaPago();
			out.println("<td class='rep'>");
			out.println("<select id='fpago'  class='rep' ><option value='"+rs.getString(11)+"' >"+rs.getString(12)+"</option>");
			while(rs1.next()){
				out.println("<option value='"+rs1.getString(1)+"'>"+rs1.getString(2)+"</option>");				
			}rs1.getStatement().getConnection().close();
			out.println("</select>");
			out.println("</td>");
			out.println("<td width='20%'>");
			out.println("Fecha de Entrega :");
			out.println("</td>");
			out.println("<td>");
			out.println("<input type='text' id='fentre' size='10' onKeyup='masca(this,patron,true,"+diaa+","+mess+","+annio+")' onblur='checkfecha()'/>");
			out.println("</td>");
			out.println("</tr>");
			
			if(rs.getString(11).equals("3")){
				out.println("<tr><td width='15%'>Cta. para Transferencias: </td><td class='rep'>"+rs.getString(13)+"</td><td colspan='2'></td></tr>");
			}
			out.println("<tr>");
			out.println("<td>");
			out.println("Sucursal : </td><td><select id='suc' onchange='suc()' class='rep' ><option>---</option>");
			rs1=mp.BuscarSucursal();
			while(rs1.next()){
				out.println("<option value='"+rs1.getString(1)+"'>"+rs1.getString(2)+"</option>");
			}
			out.println("</select>");
			out.println("<input type='hidden' id='codsuc' /><input type='hidden' id='codsubc' /></td>");
			out.println("<td><div id='centrocosto' ></div><input type='hidden' id='codcc' /><input type='hidden' id='codsucycc' /></td>");
			out.println("<td><div id='succosto' ></div><input type='hidden' id='codcentrosubc' /></td>");
			
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td width='20%'>");
			out.println("Concepto : ");
			out.println("</td>");
			out.println("<td colspan='3'>");
			out.println("<textarea id='concepto' cols='72' rows='3' class='rep'></textarea>");
			out.println("</td>");
			
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td colspan='4' ><div align='center'><hr> CARGUE DE ORDEN <hr></div></td> ");
			out.println("</td>");
			out.println("</tr>");
			
			out.println("<tr><td colspan='4' width='100%'>");
			out.println("<table width='100%' frame='above'>");
			out.println("<tr bgcolor='#BADBFB' class='rep' align='center'><td width='15%'>Cod Ref.</td><td width='40%'>Descripcion</td><td width='5%'>Cantidad</td><td width='10%'>Precio Unitario</td><td width='5%'>Descuento % </td><td width='5%'>Subtotal</td><td width='5%'>IVA</td><td width='15%'>Valor Total</td><td></td></tr>");
			out.println("<tr class='rep' ><td><input type='text' onkeyup='BuscarProducto("+rs.getString(1)+",1)' class='rep' id='codref'  size='15' style='width:100%'/></td>" +
					"<td><input type='text' class='rep' onkeyup='BuscarProducto("+rs.getString(1)+",2)' class='rep' id='descrip' size='80' style='width:100%'/></td><td><input type='text' class='rep' id='cant' size='5' style='width:100%' onkeyup='checknu()' onblur='CalcularTotal(1)' /></td>" +
					"<td><input type='text' class='rep'size='8' id='punit' style='width:100%' disabled/></td>"+
					"<td><input type='text' class='rep' size='5' id='desc' style='width:100%' value='0' onblur='CalcularTotal(2)' /></td>" +
					"<td><input type='text' class='rep' size='8' id='subtotal' style='width:100%' disabled/></td>"+
					"<td><input type='text' class='rep' onkeyup='BuscarIva()' size='5' id='iva' style='width:100%' /></td>" +
					"<td><input type='text' class='rep' size='15' id='ptotal' style='width:100%' disabled/></td><td><input type='button' id='Oa' value='+' onclick='AsigOrden("+codprove+")' /></td></tr>");
			
			out.println("<tr><td><div id='vcodref'></div><input type='hidden' id='codprodu' /><input type='hidden' id='codtarifa' /></td>"+
						"<td><div id='vproduct' ></div></td><td colspan='4'> </td><td colspan='2'><div id='vistiva' ></div><input type='hidden' id='codiiva' /></td>");
			out.println("</tr>");
			
			
			out.println("</table>");
			out.println("</td></tr>");
			out.println("<tr><td colspan='4' ><div align='center' id='detalleo'><input type='hidden' id='verif' /></div></td><tr>");
			out.println("</table>");
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	if(va.equals("ValiO")){
		String codprove=req.getParameter("cod");
		rs=mp.BusOrdExist(codprove);
		try {
			if(rs.next()){
				out.println("1");
			}else{
				out.println("0");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	if(va.equals("O")){
		String cod=req.getParameter("cod");
		rs=mp.BuscarProveOrdenCompra(cod);
		try {
			if(rs.next()){
				out.println(rs.getString(1)+"_"+rs.getString(3));
			}rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	if(va.equals("Bp")){
		String codprove=req.getParameter("codprove");
		String texto=req.getParameter("texto");
		String ident=req.getParameter("iden");
		String xx=req.getParameter("xx");
		rs=mp.BuscarProd(texto, "4", codprove);
		out.println("<table>");
		
		try {
			while(rs.next()){
				out.println("<tr><td><a class='rep' href='#' onclick='Bprod("+codprove+","+rs.getString(6)+","+ident+","+xx+")' >"+rs.getString(2)+" - "+rs.getString(3)+" Valor Unit. "+rs.getString(7)+" Iva "+rs.getString(9)+"</a></td></tr>");
			}rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		out.println("</table>");
		
	}
	
	if(va.equals("Bproduc")){
		
		
		String codprove=req.getParameter("codprove");
		String codtarifa=req.getParameter("codtarifa");
		//System.out.println("Entrando a Bproduc "+codprove+" - "+codtarifa);
		rs=mp.BuscarTarifa(codtarifa);
		try {
			if(rs.next()){
				out.println(rs.getString(1)+"_"+rs.getString(2)+"_"+rs.getString(3)+"_"+rs.getString(4)+"_"+rs.getString(6)+"_"+rs.getString(7)+"_"+rs.getString(8)+"_"+rs.getString(9));
				//System.out.println(rs.getString(1)+"_"+rs.getString(2)+"_"+rs.getString(3)+"_"+rs.getString(4)+"_"+rs.getString(6)+"_"+rs.getString(7)+"_"+rs.getString(8)+"_"+rs.getString(9));
			}rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	if(va.equals("Vistcentrocosto")){
		
		String suc=req.getParameter("suc");
		rs=mp.BuscarCentroCosto(suc);
		out.println("Centro de Costo: <select id='ccosto' class='rep' onchange='ccosto("+suc+")' ><option value='---'>---</option>");
		try {
			while(rs.next()){
				out.println("<option value='"+rs.getString(1)+"'>"+rs.getString(2)+"</option>");
			}rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		out.println("</select>");
	}
	
	if(va.equals("Vistsubcosto")){
		String ccosto=req.getParameter("ccosto"); 
		String suc=req.getParameter("suc");
		rs=mp.Buscarsubcentro(ccosto,suc);
		out.println("Subcentro de Costo : <select id='subccosto' onchange='ccysc("+ccosto+","+suc+")' class='rep'><option value='---' >---</option>");
		
		try {
			while(rs.next()){
				out.println("<option value='"+rs.getString(1)+"' >"+rs.getString(2)+"</option>");	
			}rs.getStatement().getConnection().close();
			out.println("</select>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	if(va.equals("ccysc")){
		
		String codcosto=req.getParameter("codcosto");
		String subcc=req.getParameter("subcc");
		String suc=req.getParameter("suc");
		
		rs=mp.Buscarccysc(codcosto,subcc,suc);
		try {
			if(rs.next()){
				out.println(rs.getString(1)+"_"+rs.getString(2));
			
			}rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	if(va.equals("Biva")){
		String texto=req.getParameter("texto");
		
		rs=mp.Biva(texto);
		out.println("<table>");
		try {
			while(rs.next()){
				out.println("<tr><td><a class='rep' href='#' onclick='Biv("+rs.getString(1)+","+rs.getString(2)+")' >"+rs.getString(2)+" - "+rs.getString(3)+"</a></td></tr>");
			}rs.getStatement().getConnection().close();
			
			out.println("</table>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	if(va.equals("DetOrden")){
		java.util.Date fechaActual = new java.util.Date();
		java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
		java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());
		System.out.println("ENTRANDO A detorden");
		int v=1;
				try {
					rs1=mp.BuscarCodPreOrden(ocodprove, ofechaentrega,osuc,occosto,osubcc,ocodsuccosto,ocodcensubcc,oconcep,oformapago, ouser);
					if(rs1.next()){
						v=0;
					}else{
					mp.GuardarPreOrden(ocodprove, ofechaentrega,osuc,occosto,osubcc,ocodsuccosto,ocodcensubcc,oconcep,oformapago, ouser);
					}rs1.getStatement().getConnection().close();  
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} 
		rs=mp.BuscarCodPreOrden(ocodprove, ofechaentrega,osuc,occosto,osubcc,ocodsuccosto,ocodcensubcc,oconcep,oformapago, ouser);
		String codordencompra="";
				try {
					if(rs.next()){
						codordencompra=rs.getString(1);
					}rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(optotal);
				if(v==0){
					out.println("0");
				}else{
					out.println(codordencompra);
				}
		 
	}
	
	if(va.equals("DetOrdenD")){
		java.util.Date fechaActual = new java.util.Date();
		java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
		java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());
		String codordencompra=req.getParameter("codorden");
		System.out.println(" DetOrdenD "+codordencompra+" nombre del producto "+onombre);
		if(ofechaentrega.equals("")){
			ofechaentrega="0000-00-00";
		}
		mp.ActuaEncOrden(codordencompra,ofechaentrega,osuc,occosto,osubcc,ocodsuccosto,ocodcensubcc,oconcep,oformapago,ocodprove);
		try {
			mp.GuardarDetorden(codordencompra,ocodprove,ocodprodu,ocodref,onombre, ocant, opunit, osubtotal, oiva, optotal, ocodiiva,ocodtarifa , odesc, Fecha, Hora, ouser  );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int i=1;
		rs=mp.BuscarDetOrden(codordencompra);
		out.println("<table width='100%'>");
		out.println("<tr bgcolor='#BADBFB' class='rep' align='center'><td width='15%'>Cod Ref.</td><td width='40%'>Descripcion</td><td width='5%'>Cantidad</td><td width='10%'>Precio Unitario</td><td width='5%'>Descuento % </td><td width='5%'>Subtotal</td><td width='5%'>IVA</td><td width='9%'>Valor Total</td><td>Modificar</td><td>Eliminar</td></tr>");
	
			try {
				while(rs.next()){
					out.println("<tr class='rep' ><div id=''detalle"+i+"' >");
					out.println("<td>"+rs.getString(5)+"</td><td>"+rs.getString(6)+"</td><td>"+rs.getString(7)+"</td><td>"+rs.getString(8)+"</td><td>"+rs.getString(9)+"</td><td>"+rs.getString(10)+"</td><td>"+rs.getString(11)+"</td><td>"+rs.getString(12)+"</td><td><input  name='radiobuttonn' type='radio'  id='Modif'  onclick='RadioDet("+i+")' title='Modificar' /></td><td><input  name='radiobuttonn' type='radio' id='Elim'  onclick='RadioDet("+i+")' title='Eliminar' /><input type='hidden' value='"+rs.getString(1)+"'  id='cdet"+i+"' /></td>");
					out.println("</div></tr>");
					i=i+1;
				}rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		out.println("<input type='hidden' value='"+codordencompra+"' id='codorden' />");
		out.println("<tr><td colspan='10'><input type='button' onclick='FinalizarOr("+codordencompra+")' value='Guardar Orden' /> </td></tr>");
		out.println("</table>");
		
	}
	
	if(va.equals("OO")){
		String codprove=req.getParameter("cod");
		rs=mp.BuscarPreOrdenes(codprove, user);
		out.println("<table width='100%'>");
		out.println("<tr class='contpre' bgcolor='#BADBFB' align='center'><td>Codigo Preorden</td><td>Ultima fecha de Cargue</td><td>Concepto</td></tr> ");
		try {
			while(rs.next()){
				out.println("<tr class='rep'><td  align='center' ><a class='rep' href='#' onclick='VerPreOrden("+codprove+","+rs.getString(1)+")'>"+rs.getString(1)+"</td><td  align='center' >"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td></a></tr>");
			}rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.println("</table>");
	}
		
	if(va.equals("OO.1")){//Ver preorden 
		String codprove=req.getParameter("codprove");
		String codpreorden=req.getParameter("codpreorden");
		System.out.println("OO.1");
		int i=1;
		rs=mp.BuscarProveOrdenCompra(codprove);
		rs3=mp.BusDatOrden(codpreorden);
		out.println("<table width='100%'>");
		out.println("<tr>"+
			" <td colspan='4' id='cabecera2' class='style11' ><div align='center'>ORDEN DE COMPRA</div></td> "+
		    "</tr>");
		try {
			if(rs.next()){
			out.println("<tr>");
			out.println("<td width='10%'>");
			out.println("Proveedor");
			out.println("</td>");
			out.println("<td class='rep'>");
			out.println(rs.getString(3));
			out.println("</td>");
			out.println("<td width='20%'>");
			out.println("Actividad Economica : ");
			out.println("</td>");
			out.println("<td class='rep' >");
			out.println(rs.getString(4)+" | "+rs.getString(5));
			out.println("</td>");
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td width='20%'>");
			out.println("Direccion : ");
			out.println("</td>");
			out.println("<td class='rep'>");
			out.println(rs.getString(6));
			out.println("</td>");
			out.println("<td width='20%'>");
			out.println("Telefono : ");
			out.println("</td>");
			out.println("<td class='rep' >");
			out.println(rs.getString(7));
			out.println("</td>");
			out.println("</tr>");
			
			out.println("<tr>");
			
			if(rs3.next()){
				out.println("<td width='20%'>");
				out.println("Forma de Pago: ");
				out.println("</td>");
				rs1=mp.BuscarFormaPago();
				out.println("<td class='rep'>");
						out.println("<select id='fpago'  class='rep' ><option value='"+rs3.getString(14)+"' >"+rs3.getString(18)+"</option>");
						while(rs1.next()){
							out.println("<option value='"+rs1.getString(1)+"'>"+rs1.getString(2)+"</option>");				
						}rs1.getStatement().getConnection().close();
						out.println("</select>");
						out.println("</td>");
						out.println("<td width='20%'>");
						out.println("Fecha de Entrega :");
						out.println("</td>");
						out.println("<td>");
						out.println(rs3.getString(2));	
						out.println("<input type='hidden' id='fentre' value='"+rs3.getString(2)+"'");
						out.println("</td>");
						out.println("</tr>");
						
						if(rs.getString(11).equals("3")){
							out.println("<tr><td width='15%'>Cta. para Transferencias: </td><td class='rep'>"+rs.getString(13)+"</td><td colspan='2'></td></tr>");
						}
						out.println("<tr>");
						out.println("<td>");
						if(rs3.getString(4).equals("0")){
							out.println("Sucursal : </td><td><select id='suc' onchange='suc()' class='rep' ><option>---</option>");
						}else{
							rs4=mp.BusSuc(rs3.getString(4));
							if(rs4.next()){
								out.println("Sucursal : </td><td><select id='suc' onchange='suc()' class='rep' ><option value='"+rs4.getString(1)+"'>"+rs4.getString(2)+"</option>");
							}rs4.getStatement().getConnection().close();
						}
						rs1=mp.BuscarSucursal();
						while(rs1.next()){
							out.println("<option value='"+rs1.getString(1)+"'>"+rs1.getString(2)+"</option>");
						}rs1.getStatement().getConnection().close();
						out.println("</select>");
						out.println("<input type='hidden' id='codsuc' value='"+rs3.getString(4)+"'/><input type='hidden' id='codsubc' value='"+rs3.getString(6)+"' /></td>");
						out.println("<td><div id='centrocosto' >");
						if(rs3.getInt(5)!=0){
							rs4=mp.BuscarCentroCosto(rs3.getString(4));
							rs5=mp.Buscc(rs3.getString(5));//Busco el nombre de centro de costo
							if(rs5.next()){
							out.println("Centro de Costo: <select id='ccosto' class='rep' onchange='ccosto("+rs3.getString(4)+")' ><option value='"+rs5.getString(1)+"'>"+rs5.getString(2)+"</option>");
							}rs5.getStatement().getConnection().close();
							try {
								while(rs4.next()){
									out.println("<option value='"+rs4.getString(1)+"'>"+rs4.getString(2)+"</option>");
								}rs4.getStatement().getConnection().close();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							out.println("</select>");
						}
						out.println("</div><input type='hidden' id='codcc'  value='"+rs3.getString(5)+"'/><input type='hidden' id='codsucycc' value='"+rs3.getString(7)+"' /></td>");
						out.println("<td><div id='succosto' >");
						if(rs3.getInt(6)!=0){
							
							rs4=mp.Buscarsubcentro(rs3.getString(5),rs3.getString(4));
							rs5=mp.Bussubc(rs3.getString(6));
							if(rs5.next()){
								out.println("Subcentro de Costo : <select id='subccosto' onchange='ccysc("+rs3.getString(5)+","+rs3.getString(4)+")' class='rep'><option value='"+rs5.getString(1)+"' >"+rs5.getString(2)+"</option>");
							}rs5.getStatement().getConnection().close();
							try {
								while(rs4.next()){
									out.println("<option value='"+rs4.getString(1)+"' >"+rs4.getString(2)+"</option>");	
								}rs4.getStatement().getConnection().close();
								out.println("</select>");
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
						out.println("</div><input type='hidden' id='codcentrosubc' value='"+rs3.getString(8)+"' /></td>");
						
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td width='20%'>");
						out.println("Concepto : ");
						out.println("</td>");
						out.println("<td colspan='3'>");
						out.println("<textarea id='concepto' cols='72' rows='3' class='rep' >"+rs3.getString(3)+"</textarea>");
						out.println("</td>");
			}rs3.getStatement().getConnection().close();
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td colspan='4' ><div align='center'><hr> CARGUE DE ORDEN <hr></div></td> ");
			out.println("</td>");
			out.println("</tr>");
			
			out.println("<tr><td colspan='4' width='100%'>");
			out.println("<table width='100%' frame='above'>");
			out.println("<tr bgcolor='#BADBFB' class='rep' align='center'><td width='15%'>Cod Ref.</td><td width='40%'>Descripcion</td><td width='5%'>Cantidad</td><td width='10%'>Precio Unitario</td><td width='5%'>Descuento % </td><td width='5%'>Subtotal</td><td width='5%'>IVA</td><td width='15%'>Valor Total</td><td></td></tr>");
			out.println("<tr class='rep' ><td><input type='text' onkeyup='BuscarProducto("+rs.getString(1)+",1)' class='rep' id='codref'  size='15' style='width:100%'/></td>" +
					"<td><input type='text' class='rep' onkeyup='BuscarProducto("+rs.getString(1)+",2)' class='rep' id='descrip' size='80' style='width:100%'/></td><td><input type='text' class='rep' id='cant' size='5' style='width:100%' onkeyup='checknu()' onblur='CalcularTotal(1)' /></td>" +
					"<td><input type='text' class='rep'size='8' id='punit' style='width:100%' disabled/></td>"+
					"<td><input type='text' class='rep' size='5' id='desc' style='width:100%' value='0' onblur='CalcularTotal(2)' /></td>" +
					"<td><input type='text' class='rep' size='8' id='subtotal' style='width:100%' disabled/></td>"+
					"<td><input type='text' class='rep' onkeyup='BuscarIva()' size='5' id='iva' style='width:100%' /></td>" +
					"<td><input type='text' class='rep' size='15' id='ptotal' style='width:100%' disabled/></td><td><input type='button' id='Oa' value='+' onclick='AsigOrden("+codprove+")' /></td></tr>");
			
			out.println("<tr><td><div id='vcodref'></div><input type='hidden' id='codprodu' /><input type='hidden' id='codtarifa' /></td>"+
						"<td><div id='vproduct' ></div></td><td colspan='4'> </td><td colspan='2'><div id='vistiva' ></div><input type='hidden' id='codiiva' /></td>");
			out.println("</tr>");
			
			
			out.println("</table>");
			out.println("</td></tr>");
			out.println("<tr><td colspan='4' ><div align='center' id='detalleo'>");
					
					rs2=mp.BuscarDetOrden(codpreorden);
					out.println("<table width='100%'>");
					out.println("<tr bgcolor='#BADBFB' class='rep' align='center'><td width='15%'>Cod Ref.</td><td width='40%'>Descripcion</td><td width='5%'>Cantidad</td><td width='10%'>Precio Unitario</td><td width='5%'>Descuento % </td><td width='5%'>Subtotal</td><td width='5%'>IVA</td><td width='9%'>Valor Total</td><td width='3%'>Modificar</td><td width='3%'>Eliminar</td></tr>");
				
						try {
							while(rs2.next()){
								out.println("<tr class='rep' ><div id='detalle"+i+"' >");
								out.println("<td>"+rs2.getString(5)+"</td><td>"+rs2.getString(6)+"</td><td>"+rs2.getString(7)+"</td><td>"+rs2.getString(8)+"</td><td>"+rs2.getString(9)+"</td><td>"+rs2.getString(10)+"</td><td>"+rs2.getString(11)+"</td><td>"+rs2.getString(12)+"</td><td><input  name='radiobuttonn' type='radio'  id='Modif'  onclick='RadioDet("+i+")' title='Modificar' /></td><td><input  name='radiobuttonn' type='radio' id='Elim'  onclick='RadioDet("+i+")' title='Eliminar' /><input type='hidden' value='"+rs2.getString(1)+"'  id='cdet"+i+"' /></td>");
								//System.out.println("<td>"+rs2.getString(5)+"</td><td>"+rs2.getString(6)+"</td><td>"+rs2.getString(7)+"</td><td>"+rs2.getString(8)+"</td><td>"+rs2.getString(9)+"</td><td>"+rs2.getString(10)+"</td><td>"+rs2.getString(11)+"</td><td>"+rs2.getString(12)+"</td><td><input  name='radiobuttonn' type='radio'  id='Modif'  onclick='RadioDet("+i+")' title='Modificar' /></td><td><input  name='radiobuttonn' type='radio' id='Elim'  onclick='RadioDet("+i+")' title='Eliminar' /><input type='hidden' value='"+rs2.getString(1)+"'  id='cdet"+i+"' /></td>");
								out.println("</div></tr>");
								i=i+1;
							}rs2.getStatement().getConnection().close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			
			out.println("</div></td><tr>");
			
			if(i>1){
				out.println("<tr><td colspan='10'><input type='button' onclick='FinalizarOr("+codpreorden+")' value='Guardar Orden' /> </td></tr>");
			}
			out.println("</table>");
			
			}rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	if(va.equals("Elim")){
		System.out.println("Entrando a elim");
		java.util.Date fechaActual = new java.util.Date();
		java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
		java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());
		String coddetord=req.getParameter("codord");
		String ident=req.getParameter("i");
		try {
			mp.EliminarDetOrden(coddetord, user,Fecha,Hora);
			rs=mp.BuscCodOrden(coddetord);
			if(rs.next()){
				out.print(rs.getString(1));
			}
			//out.println("Eliminacion exitosa");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
	}
	
	if(va.equals("Modif")){
		String coddetord=req.getParameter("codord");
		rs=mp.BuscCodOrden(coddetord);
		try {
			if(rs.next()){
				out.println("<table width='100%' frame='above'>");
				out.println("<tr><td colspan='10'>Modificacion</td></tr>");
				out.println("<tr bgcolor='#BADBFB' class='rep' align='center'><td width='15%'>Cod Ref.</td><td width='40%'>Descripcion</td><td width='5%'>Cantidad</td><td width='10%'>Precio Unitario</td><td width='5%'>Descuento % </td><td width='5%'>Subtotal</td><td width='5%'>IVA</td><td width='9%'>Valor Total</td><td width='3%'></td><td width='3%'></td></tr>");
				out.println("<tr class='rep' ><td><input type='text' onkeyup='BuscarProduc("+rs.getString(4)+",1)' class='rep' id='codreff'  size='15' style='width:100%' value='"+rs.getString(6)+"'/></td>" +
						"<td><input type='text' class='rep' onkeyup='BuscarProduc("+rs.getString(4)+",2)' class='rep' id='descripp' size='80' style='width:100%' value='"+rs.getString(7)+"'/></td><td><input type='text' class='rep' id='cantt' size='5' style='width:100%' onkeyup='checknu()' onblur='CalcularTotall(1)' value='"+rs.getString(8)+"' /></td>" +
						"<td><input type='text' class='rep'size='8' id='punitt' style='width:100%' value='"+rs.getString(9)+"' disabled/></td>"+
						"<td><input type='text' class='rep' size='5' id='descc' style='width:100%' value='0' onblur='CalcularTotall(2)' value='"+rs.getString(10)+"' /></td>" +
						"<td><input type='text' class='rep' size='8' id='subtotall' style='width:100%' value='"+rs.getString(11)+"' disabled/></td>"+
						"<td><input type='text' class='rep' onkeyup='BuscarIvaa()' size='5' id='ivaa' style='width:100%' value='"+rs.getString(12)+"' /></td>" +
						"<td><input type='text' class='rep' size='15' id='ptotall' style='width:100%' value='"+rs.getString(13)+"' disabled/></td><td><input type='button' id='Oaa' value='Actualizar' onclick='ActDetOrd("+coddetord+","+rs.getString(3)+")'</td><td><input type='button' value='Cancelar' onclick='ActLista("+rs.getString(1)+")'</td></tr>");
				rs1=mp.BusCodTarifa(rs.getString(9),rs.getString(4),rs.getString(5)); //Busco el codigo de tarifa que fue utilizado 
				String ctarifa="";
				int sw=0;
				while((rs1.next())&&(sw==0)){
					if(rs1.getString(8).equals("")){
						sw=1;
						ctarifa=rs1.getString(1);
					}else{
						ctarifa=rs1.getString(1);
					}
				}rs1.getStatement().getConnection().close();
				out.println("<tr><td><div id='vcodreff'></div><input type='hidden' id='codproduu' value='"+rs.getString(5)+"'/><input type='hidden' id='codtarifaa' value='"+ctarifa+"'/></td>"+
							"<td><div id='vproductt' ></div></td><td colspan='4'> </td><td colspan='2'><div id='vistivaa' ></div><input type='hidden' id='codiivaa' value='"+rs.getString(18)+"' /></td>");
				out.println("</tr>");
				
				
				out.println("</table>");
			}rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	if(va.equals("ActLista")){
		System.out.println("ActLista");
		String codordencompra=req.getParameter("codorden");
		int i=1;
		//System.out.println("valor de codorden"+codordencompra+" para ver si salta");
		rs=mp.BuscarDetOrden(codordencompra);
		out.println("<table width=100% >");
		out.println("<tr bgcolor='#BADBFB' class='rep' align='center'><td width='15%'>Cod Ref.</td><td width='40%'>Descripcion</td><td width='5%'>Cantidad</td><td width='10%'>Precio Unitario</td><td width='5%'>Descuento % </td><td width='5%'>Subtotal</td><td width='5%'>IVA</td><td width='9%'>Valor Total</td><td>Modificar</td><td>Eliminar</td></tr>");
	
			try {
				while(rs.next()){
					out.println("<tr class='rep' ><div id='detalle"+i+"' >");
					
					out.println("<td>"+rs.getString(5)+"</td><td>"+rs.getString(6)+"</td><td>"+rs.getString(7)+"</td><td>"+rs.getString(8)+"</td><td>"+rs.getString(9)+"</td><td>"+rs.getString(10)+"</td><td>"+rs.getString(11)+"</td><td>"+rs.getString(12)+"</td><td><input  name='radiobuttonn' type='radio'  id='Modif'  onclick='RadioDet("+i+")' title='Modificar' /></td><td><input  name='radiobuttonn' type='radio' id='Elim'  onclick='RadioDet("+i+")' title='Eliminar' /><input type='hidden' value='"+rs.getString(1)+"'  id='cdet"+i+"' /></td>");
					
					out.println("</div></tr>");
					i=i+1;
				}rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		out.println("<input type='hidden' value='"+codordencompra+"' id='codorden' />");
		out.println("<tr><td colspan='10'><input type='button' onclick='FinalizarOr("+codordencompra+")' value='Guardar Orden' /> </td></tr>");
		out.println("</table>");
	}
	
	if(va.equals("ActDetOrd")){
		String coddetord=req.getParameter("coddetorden");
		String codorden=req.getParameter("codorden");
		java.util.Date fechaActual = new java.util.Date();
		java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
		java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());
		try {
			mp.ActDetOrden(coddetord,codorden, ocodref,onombre,ocodprodu,ocant,opunit,osubtotal,oiva,optotal,ocodiiva,ocodtarifa,odesc,user,Fecha,Hora);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	if(va.equals("EmitOrd")){
		String codord=req.getParameter("codord");
		java.util.Date fechaActual = new java.util.Date();
		java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
		java.sql.Time Hora = new java.sql.Time(fechaActual.getTime()); 
		try {
			mp.GuardarOrden(codord,Fecha,Hora,oconcep,user);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
   }
	
	
	/************************************/
	
	
	public String FormatMoneda(String valor){		
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
	
	
	/**************************************/
	
	
}
