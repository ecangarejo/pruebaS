package cont_controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cont_metodo.MetodoProduc;



public class ControlProduc  extends HttpServlet{
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String va = req.getParameter("valor");
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out;
		out=res.getWriter();
		MetodoProduc mp=new MetodoProduc();
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		ResultSet rs3=null;
		ResultSet rs4=null;
		ResultSet rsp=null;
		ResultSet rsl=null;
		ResultSet rsa=null;
		String user=req.getParameter("user");
		
		if(va.equals("gp")){
			out.println("<table width='90%'>");
			out.println("<tr><td  id='cabecera2' class='style11' colspan='3'><div align='center'>GRUPOS POR PARAMETRIZAR</div></td></tr>");
			rs=mp.BuscarGrupoporParametrizar();
			int vali=0;
			int ind=0;
			try {
				while(rs.next()){
					if(vali==0){
						out.println("<tr width='50%'><td>Nombre del Grupo </td> <td width='30%'>Cuenta </td></tr>");
						vali=1;
					}
					out.println("<tr><td width='30%'>"+rs.getString(2)+"</td><td><input type='text'   id='aux"+ind+"' onkeyup='autocompletaCtaAux("+ind+",1,0)' size='15' /></td><td><input type='button' id='Boto' onclick='GuardarCta("+ind+","+rs.getString(1)+")' value='Guardar' /></td>");
					out.print("<tr><td width='30%'><input type='hidden' id='auxcod"+ind+"' /></td><td width='30%'><div id='daux"+1+ind+"'  width='30%'/></td></tr>");
					ind++;
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(vali==0){
				out.println("No existen grupos pendientes por parametrizar ");
			}
			
		}
		
		if(va.equals("gm")){
			out.println("<table width='90%'>");
			out.println("<tr><td  id='cabecera2' class='style11' colspan='3'><div align='center'>CAMBIAR CUENTAS DE GRUPOS</div></td></tr>");
			out.println("<tr><td>Grupo: </td><td><input type='text' class='rep' style='width:80%' size='15' onkeyup='BusGrup()' id='grup' /></td><td><div id='vistacuentag' ></div></td></tr>");
			out.println("<tr><td></td><td><div id='vgrupo' ></div></td></tr>");
			out.println("<tr><td colspan='3'><a href='#' onclick='VerLista(2)' >Ver Lista de Grupos Parametrizados </a></td></tr>");
			out.println("</table>");
		}
		
		if(va.equals("busaux")){
			String texto=req.getParameter("texto");
			String ind=req.getParameter("ind");
			String tipo=req.getParameter("tipo");
			String numcuenta=req.getParameter("numcuenta");
			rs1=mp.BuscarAux(texto,tipo,numcuenta);
			System.out.println("ind"+ind+" tipo "+tipo+" numcuenta "+numcuenta);
			out.println("<table>");
			int verif=0;
			try {
				
					while(rs1.next()){
						out.println("<tr><td><a class='rep' href='#' onclick='AsignacionCtaAux("+ind+","+rs1.getString(2)+","+rs1.getString(1)+")' >"+rs1.getString(2)+" | "+rs1.getString(3)+"</a></td></tr>");	
						verif=1;
					}rs1.getStatement().getConnection().close();
					
					if(verif==0){
						if(tipo.equals("3")){
							out.println("<tr><td>El producto que esta tratando de parametrizar tiene como cuenta Mayor "+numcuenta+" solo le apareceran en su busqueda cuentas derivadas de esta cuenta </td></tr>");
						}else{
							out.println("<tr><td>No existen registros </td></tr>");
						}
					}
				
			out.println("</table>");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			
		}
		
		if(va.equals("guardarcta")){
			String ctaaux=req.getParameter("ctaaux");
			String codcta=req.getParameter("codcta");
			String codgrupo=req.getParameter("codgrupo");
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
			java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());
			
			try {
				mp.GuardarCtaGrupo(ctaaux,codcta,codgrupo,Fecha,Hora,user);
				out.println("CUENTA ASIGNADA CORRECTAMENTE ");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				out.println("NO SE LOGRO ASIGNAR CORRECTAMENTE LA CTA ");
				e.printStackTrace();
			}			
		}
		
		if(va.equals("guardarctaprod")){
			String ctaaux=req.getParameter("ctaaux");
			String codcta=req.getParameter("codcta");
			String codgrupo=req.getParameter("codpro");
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
			java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());
			
			try {
				mp.GuardarCtaProd(ctaaux,codcta,codgrupo,Fecha,Hora,user);
				out.println("CUENTA ASIGNADA CORRECTAMENTE ");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				out.println("NO SE LOGRO ASIGNAR CORRECTAMENTE LA CTA ");
				e.printStackTrace();
			}
			
			
		}
		
		if (va.equals("pp")){//parametrizacion de producto
			out.println("<table width='90%'>");
			out.println("<tr><td  id='cabecera2' class='style11' colspan='3'><div align='center'>PRODUCTOS POR PARAMETRIZAR</div></td></tr>");
			rs=mp.BuscarProductosxParametrizar();
			int vali=0;
			int ind=0;
			try {
				while(rs.next()){
					if(vali==0){
						out.println("<tr width='50%'><td>Nombre del Producto</td> <td width='30%'>Cuenta </td></tr>");
						vali=1;
					}
					rs1=mp.BuscarCtaGrupo(rs.getString(1));
					if(rs1.next()){
						out.println("<tr><td width='30%'>"+rs.getString(2)+" - "+rs.getString(3)+"</td><td><input type='text'   id='aux"+ind+"' onkeyup='autocompletaCtaAux("+ind+",3,"+rs1.getString(4)+")' size='15' /></td><td><input type='button' id='Boto' onclick='GuardarCtaProd("+ind+","+rs.getString(1)+")' value='Guardar' /></td>");
						out.print("<tr><td width='30%'><input type='hidden' id='auxcod"+ind+"' /></td><td width='30%'><div id='daux"+1+ind+"'  width='30%'/></td></tr>");
					}rs1.getStatement().getConnection().close();
					ind++;
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(vali==0){
				out.println("No existen grupos pendientes por parametrizar ");
			}
			
		}
		
		if(va.equals("pm")){//Modificar parametrizacion de producto
			int ind=0;
			out.println("<table width='90%'>");
			out.println("<tr><td  id='cabecera2' class='style11' colspan='3'><div align='center'>CAMBIO DE CUENTAS DE PRODUCTOS </div></td></tr>");
			out.println("<tr><td>Producto: </td><td><input type='text' id='produc' onkeyup='BusProduc()' style='width:100%' size='15' class='rep' /></td><td><div id='vistacuentap'></div></td></tr>");
			out.println("<tr><td></td><td><div id='vprod'> </div></td><td><input type='hidden' id='codpro' /></td></tr>");
			out.println("<tr><td colspan='3'><a href='#' onclick='VerLista(1)' >Ver Lista de Productos Parametrizados </a></td></tr>");
			
		}
		
		if(va.equals("Lista")){
			String tipo=req.getParameter("tipo");
			if(tipo.equals("1")){
				rs=mp.BusProdPara();
				out.println("<table align='center' width='90%' >");
				out.println("<tr class='contpre' ><td colspan='6' align='center'><br><b>LISTADO DE PRODUCTOS PARAMETRIZADOS </b></td></tr>");
				out.println("<tr><td colspan='6'></td></tr>");
				out.println("<tr class='contpre' bgcolor='#C6D9E5' align='center' ><td>CTA GRUPO</td><td>GRUPO </td><td>REF</td><td>NOMBRE PRODUCTO</td><td>TIPO DE PRODUCTO</td><td>CTA DE PRODUCTO</td></tr>");
				try {
					while(rs.next()){
						out.println("<tr class='rep' ><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(5)+"</td><td>"+rs.getString(6)+"</td><td>"+rs.getString(7)+"</td><td>"+rs.getString(3)+"-"+rs.getString(4)+"</td></tr>");
					}rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				out.println("</table>");
				
			}else{
				rs=mp.BusGrupPara();
				out.println("<table align='center' width='90%' >");
				out.println("<tr class='contpre'><td colspan='2' align='center'><br><b>LISTADO DE GRUPOS PARAMETRIZADOS </b></td></tr>");
				out.println("<tr class='contpre' bgcolor='#C6D9E5' ><td>CTA GRUPO</td><td>GRUPO </td>");
				try {
					while(rs.next()){
						out.println("<tr class='rep'><td>"+rs.getString(2)+" - "+rs.getString(3)+"</td><td>"+rs.getString(1)+"</td></tr>");
					}rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				out.println("</table>");
				
			}
		}
		if(va.equals("BProd")){
			String texto=req.getParameter("texto");
			rs=mp.BProd(texto);
			out.println("<table>");
			try {
				while(rs.next()){
					out.println("<tr><td><a href='#' onclick='SelProd("+rs.getString(1)+","+rs.getString(10)+")' >"+rs.getString(2)+" - "+rs.getString(3)+" - "+rs.getString(10)+"</a></td></tr>");
				}rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			out.println("</table>");
			
		}
		
		if(va.equals("BGrup")){
			String texto=req.getParameter("texto");
			rs=mp.BGrup(texto);
			out.println("<table>");
			try {
				while(rs.next()){
					out.println("<tr><td><a href='#' onclick='SelGrup("+rs.getString(1)+")' >"+rs.getString(2)+"</a></td></tr>");
					
				}rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("txt")){
			String codprod=req.getParameter("codprod");
			rs1=mp.BusNombreProducto(codprod);
			String datos="";
			try {
				if(rs1.next()){
					datos=rs1.getString(2)+"_"+rs1.getString(3);
				}rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			out.print(datos);
		}
		
		if(va.equals("txtg")){
			String codgrup=req.getParameter("codgrup");
			rs=mp.BusGrupo(codgrup);
			String datos="";
			try {
				if(rs.next()){
					datos=rs.getString(2);
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			out.print(datos);
			
		}
		
		if(va.equals("VistaModif")){
			String codprod=req.getParameter("codprod");
			String cuenta=req.getParameter("cuenta");
			
			rs=mp.BGrupodeProd(codprod);
			out.println("<table>");
			try {	
				if(rs.next()){
					out.println("<tr><td> Cuenta Actual : "+cuenta+" </td><td> Nueva Cuenta : <input type='text' id='ctaprod' onkeyup='AutoCompleCta("+rs.getString(4)+","+codprod+")' /></td></tr>");
					out.println("<tr><td></td><td><div id='cuentas' ></div></td></tr>");
				}rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.println("</table>");
			
		}
		
		
		if(va.equals("VistaModig")){
			String codgru=req.getParameter("codgrup");
			rs=mp.BCuentaGrupo(codgru);
			out.println("<table>");
			try {	
				if(rs.next()){
					out.println("<tr><td> Cuenta Actual : <font class='rep' > "+rs.getString(4)+" - "+rs.getString(9)+" </font></td><td> Nueva Cuenta : <input type='text' id='ctagrup' onkeyup='AutoCompleCtaG("+codgru+")' /></td></tr>");
					out.println("<tr><td><div id='ListaP'> </div></td><td><div id='cuentasg' ></div></td></tr>");
				}rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.println("</table>");
			
		}
		
		if(va.equals("VerCtas")){
			String ctagrup=req.getParameter("ctagrup");
			String codprod=req.getParameter("codprod");
			String texto=req.getParameter("texto");
			rs=mp.BuscarCtasProducto(ctagrup,codprod,texto);
			out.println("<table>");
			try {
				while(rs.next()){
					out.println("<tr><td><a href='#'  onclick='ActCuenta("+rs.getString(1)+","+codprod+")' >"+rs.getString(2)+" - "+rs.getString(3)+"</a></td></tr>");
				}rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.println("</table>");
		}
		
		if(va.equals("VerCtasG")){
			String codgrup=req.getParameter("codgrup");
			String texto=req.getParameter("texto");
			int vali=0;// este validador me indica si el grupo ya tiene productos asociados con ctas asociadas
			rs1=mp.BuscarProdAsocGrupo(codgrup);
			rs=mp.BuscarAux(texto,"1","0");
			out.println("<table>");
			try {
				if(rs1.next()){
					vali=1;
				}rs1.getStatement().getConnection().close();
				while(rs.next()){
					out.println("<tr class='rep' ><td><a href='#'  onclick='ActCuentaG("+rs.getString(1)+","+vali+","+codgrup+")' >"+rs.getString(2)+" - "+rs.getString(3)+"</a></td></tr>");
				}rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.println("</table>");
		}
		
		
		if(va.equals("AsigCtaGP")){
			String codgrup=req.getParameter("codgrup");
			String codcuenta=req.getParameter("codcuenta");
			String codctaAnt="";
			rs=mp.BusGrupo(codgrup);
			rs2=mp.BusCta(codcuenta);
					
			String ctaaux="";
			int i=1;
			
			try {
				if(rs.next()){
					codctaAnt=rs.getString(3);
				}rs.getStatement().getConnection().close();
				
				if(rs2.next()){
					ctaaux=rs2.getString(2);
				}rs2.getStatement().getConnection().close();
								
				out.println("<table>");
				
				out.println("<tr><td colspan='3' ><br></td></tr>");
				out.println("<tr><td class='contpre' bgcolor='#DAE5EE' colspan='3' >PRODUCTO</td></tr>");
				rs3=mp.BusProdxParaG(codgrup);
				while(rs3.next()){
					out.println("<tr class='rep' ><td>"+rs3.getString(2)+" - "+rs3.getString(3)+"</td><td>"+rs3.getString(10)+" - "+rs3.getString(11)+"</td><td><input type='text' id='ctaP"+i+"' size='15' onkeyup='AutoCompCtaGP("+i+","+ctaaux+")' /></td></tr>");
					out.println("<tr><td colspan='2'><input type='hidden' id='codcta"+i+"' /><input type='hidden' value='"+rs3.getString(1)+"' id='codpro"+i+"' /></td><td><div id='cc"+i+"' > </div></td></tr>");
					i=i+1;
				}rs3.getStatement().getConnection().close();
				
				if(i!=1){
					out.println("<tr><td colspan='3' align='center'> <input type='button'  value='Guardar' onclick='GuardarActG("+i+","+codgrup+","+codcuenta+","+ctaaux+")' /></td></tr>");
				}
				
				out.println("</table>");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			
		}
		if(va.equals("AsigCtaG")){
			
			String codgru=req.getParameter("codgrup");
			String codcuenta=req.getParameter("codcuenta");
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
			java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());
			rs2=mp.BusCta(codcuenta);
			String ctaaux="";
			try {
				if(rs2.next()){
					ctaaux=rs2.getString(2);
					
				}rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				mp.GuardarCtaGrupo(ctaaux, codcuenta, codgru, Fecha, Hora, user);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		
		if(va.equals("Visualtxt")){
			String texto=req.getParameter("texto");
			String ctagrup=req.getParameter("ctaaux");
			String ident=req.getParameter("ident");
			rs=mp.BuscarCtasProducto(ctagrup,"",texto);
			out.println("<table>");
			try {
				while(rs.next()){
					out.println("<tr><td><a href='#'  onclick='ACtaP("+rs.getString(1)+","+rs.getString(2)+","+ident+")' >"+rs.getString(2)+" - "+rs.getString(3)+"</a></td></tr>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.println("</table>");
		}
		
		
		if(va.equals("GuardarActG")){
			String cuentas=req.getParameter("cuentas");
			String productos=req.getParameter("productos");
			String i=req.getParameter("i");
			String codgrup=req.getParameter("codgrup");
			String codcta=req.getParameter("codcta");
			String ctaaux=req.getParameter("ctaaux");
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
			java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());
			
			String detprod []=productos.split("_");
			String detctas []=cuentas.split("_");
			String pend="";
			String prodpend="";
			int ii=Integer.parseInt(i);
			
			try {
				mp.GuardarCtaGrupo(ctaaux, codcta, codgrup, Fecha, Hora, user); 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int k=1;
			try {
				for(int j=0;j<ii;j++){
					if(detctas[j].equals("")){
						pend=detprod[j];
						prodpend=pend+"_"+prodpend;
						k=k+1;
					}else{
						mp.GuardarCtaProd2(detctas[j], detprod[j], Fecha, Hora, user); 
					}
				}
				if(prodpend.equals("")){
					out.print("1");
				}else{
					String descrip="";
					String detprodpend []=prodpend.split("_");
					String texto="";
					String prod="";
					//System.out.println("valor de k"+k);
					//System.out.println("valor de prodpend"+prodpend);
					
					out.println("<table>");
					out.println("<tr><td colspan='3' ><br></td></tr>");
					out.println("<tr><td class='contpre' bgcolor='#DAE5EE' colspan='3' >PRODUCTO</td></tr>");
					int m=1;
					for(int n=0;n<k-1;n++){
						System.out.println("valor de n"+n);
						System.out.println(" valor de prod"+detprodpend[n]+"fff");
						prod=detprodpend[n];
						
						if(prod.equals("")){	
						}else{
						
							rs4=mp.BusNombreProducto(prod);
							if(rs4.next()){
								texto=rs4.getString(2)+" - "+rs4.getString(3)+" | "+texto;
								out.println("<tr class='rep' ><td>"+rs4.getString(2)+" - "+rs4.getString(3)+"</td><td>"+rs4.getString(10)+" - "+rs4.getString(11)+"</td><td><input type='text' id='ctaP"+m+"' size='15' onkeyup='AutoCompCtaGP("+m+","+ctaaux+")' /></td></tr>");
								out.println("<tr><td colspan='2'><input type='hidden' id='codcta"+m+"' /><input type='hidden' value='"+rs4.getString(1)+"' id='codpro"+m+"' /></td><td><div id='cc"+m+"' > </div></td></tr>");
							}rs4.getStatement().getConnection().close();
							System.out.println("cuando n="+n+" texto "+texto);	
							m=m+1;
						}
						prod="";
					}
					if(m!=1){
						out.println("<tr><td colspan='3' align='center'> <input type='button'  value='Guardar' onclick='GuardarActG("+m+","+codgrup+","+codcta+","+ctaaux+")' /></td></tr>");
					}
					out.println("</table>");
					descrip="Le informamos que el proceso de cambio de cuenta de GRUPO los productos "+texto+" quedaron pendiente por asignarle una cuenta valida, por favor asigne las cuentas correspondientes a estos productos ";
					mp.EnviarMensaje("74",user,"PARAMETRIZACION PRODUCTOS",Fecha, Hora,descrip);

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		
		if(va.equals("GuardarActP")){
			String codcuenta=req.getParameter("codcuenta");
			String codprod=req.getParameter("codprod");
			String nombrecta_nva="";
			String codigocuenta_nva="";
			String nombrecta_ant="";
			String codigocuenta_ant="";
			String codcuenta_ant="";
			
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
			java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());
			
			rs=mp.BusCta(codcuenta);
			rs1=mp.BusNombreProducto(codprod);
			try {
				if(rs.next()){
					nombrecta_nva=rs.getString(3);
					codigocuenta_nva=rs.getString(2);
				}rs.getStatement().getConnection().close();
				
				if(rs1.next()){
					codcuenta_ant=rs1.getString(9);
					codigocuenta_ant=rs1.getString(10);
					nombrecta_ant=rs1.getString(11);
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				System.out.println(" valor de user "+user);
				mp.ActCuenta(codprod, codcuenta_ant, codigocuenta_ant, nombrecta_ant, codcuenta, codigocuenta_nva, nombrecta_nva, Fecha, Hora,user);
				out.println("Cambio Realizado !! ");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				out.println(" Ups, se produjo un error ["+e+"]");
				e.printStackTrace();
			
			}
			
		}
		
		if(va.equals("proveg")){
			out.println("<table width='100%'>");
			out.println("<tr><td>Buscar Cuenta </td><td><input type='text' id='cta'  onkeyup='AutoCta()' /></td><td><input type='button' value='Asignar' onclick='AsigG()' /></td></tr>");
			out.println("<tr><td></td><td><div id='vcta' ></div><input type='hidden' id='ccta' /></td></tr>");
		}
		
		if(va.equals("proveind")){
			
			out.println("<table width='100%' >");
			out.println("<tr><td>Seleccione Proveedor </td><td><select id='prove'>");
			rs=mp.BuscarProveedores();
			try {
				while(rs.next()){
					out.println("<option value='"+rs.getString(3)+"' >"+rs.getString(1)+" - "+rs.getString(2)+"</option>");
				}rs.getStatement().getConnection().close();
				out.println("</select>");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.println("</td><td>Seleccion Cta: </td><td><input type='text' id='cta' onkeyup='AutoCta()' /></td><td><input type='button' value='Asignar' onclick='AsigI()'/> </td></tr>");
			out.println("<tr><td colspan='3'></td><td><div id='vcta' ></div></td><td><input type='hidden' id='ccta' /></td></tr>");
		}
		
		if(va.equals("ProvexCta")){
			out.println("<table width='100%'>");
			out.println("<tr><td  id='cabecera2' class='style11' ><div align='center'>PROVEEDORES POR PARAMETRIZAR</div></td>");
			rs=mp.BusProvexCta();
			try {
				while(rs.next()){
					out.println("<tr><td>"+rs.getString(1)+" - "+rs.getString(2)+"</td></tr>");
				}rs.getStatement().getConnection().close();
				out.println("</table>");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("AutoCta")){
			String texto=req.getParameter("texto");
			out.println("<table width='100%' >");
			rs=mp.BuscarAux(texto,"2","");
			try {
				while(rs.next()){
				 out.println("<tr class='rep'><td><a href='#' onclick='AsigCta("+rs.getString(1)+","+rs.getString(2)+")' >"+rs.getString(2)+" | "+rs.getString(3)+"</a></td></tr>");
				}rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.println("</table>");
			
		}
		
		if(va.equals("AsigCtaProveG")){
			String codcta=req.getParameter("codcta");
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
			java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());
			try {
				mp.AsigCtaProveG(codcta,Fecha,Hora,user);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		if(va.equals("AsigCtaProveI")){
			System.out.println("AsigCtaProveI");
			String codcta=req.getParameter("codcta");
			String codprove=req.getParameter("codprove");
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
			java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());
			
			
			try {
				mp.AsigCtaProveI(codcta,Fecha,Hora,user,codprove);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		if(va.equals("pimp")){
			out.println("<table width='100%'>");
			out.println("<tr><td><br></td></tr>");
			out.println("<tr><td  id='cabecera2' class='style11' ><div align='center'>PARAMETRIZACION DE IMPUESTOS</div></td>");
			out.println("<tr>");
			out.println("<td>");
				out.println("<table width='100%'>");
					out.println("<tr>");
						out.println("<td>");//IVA
							out.println("<table width='100%'>");
							out.println("<tr>");
							out.println("<td colspan='4' bgcolor='#E7E9EB' align='center'><b>IVA</b></td></tr>");
							out.println("<tr align='center'><td >Valor</td><td>Descripcion</td><td>Cod Cuenta</td><td>Nombre Cuenta</td></tr>");
							int i=1;
							rs=mp.BuscarIva();
							try {
								while(rs.next()){
									out.println("<tr><td><input type='text' value='"+rs.getString(2)+"' id='valiva"+i+"' class='rep' style='width:100%' onblur='Valivalor(1,"+i+","+rs.getString(1)+")'/></td><td><input type='text' value='"+rs.getString(3)+"' id='diva"+i+"' class='rep' style='width:100%' /><input type='hidden' id='codiva"+i+"' value='"+rs.getString(1)+"' /></td>");
									rs1=mp.BusCta(rs.getString(4));
									if(rs1.next()){
										out.println("<td><input type='text' id='ccta"+i+"' value='"+rs1.getString(2)+"' class='rep' style='width:100%' onkeyup='Acuenta(1,"+i+")' /></td><td><input type='text' id='dcta"+i+"' value='"+rs1.getString(3)+"' class='rep' style='width:100%' disabled /><input type='hidden' id='codcta"+i+"' value='"+rs1.getString(1)+"'  class='rep' /></td>");
									}else{
										out.println("<td><input type='text' id='ccta"+i+"' value='NO ASIGNADA' class='rep' style='width:100%' onkeyup='Acuenta(1,"+i+")' /></td><td><input type='text' id='dcta"+i+"' value='NO ASIGNADA'  class='rep' style='width:100%' disabled /><input type='hidden' id='codcta"+i+"' value='NO'/></td>");
									}rs1.getStatement().getConnection().close();
									out.println("</tr>");
									out.println("<tr><td colspan='2'></td><td colspan='2' ><div id='vctaiva"+i+"' ></div></td></tr>");
									
									i=i+1;
									}rs.getStatement().getConnection().close();
								
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							out.println("<tr>");
							out.println("<td align='center' colspan='2'>");
							out.println("<input type='button' value='Nuevo' id='bn1' onclick='NuevoImpuesto(1)' />");
							out.println("</td>");
							out.println("<td align='center' colspan='2' >");
							out.println("<input type='button' value='Modificar' onclick='ModImpuesto(1,"+i+")' />");
							out.println("</td>");
							out.println("</tr>");
							out.println("<tr>");
							out.println("<td colspan='4' ><div id='nvoiva' ></div></td>");
							out.println("</tr>");
							out.println("</table>");
						out.println("</td>");
						out.println("</tr>");//FINAL IVA
						out.println("<tr>");
						out.println("<td>");//Retefuente
						
							out.println("<table width='100%'>");
							out.println("<td colspan='6' bgcolor='#E7E9EB' align='center'><b>RETEFUENTE</b></td></tr>");
							out.println("<tr align='center'><td >Valor</td><td>Base</td><td>UVT</td><td>Descripcion</td><td>Cod Cuenta</td><td>Nombre Cuenta</td></tr>");
							int j=1;
							rs=mp.BuscarRfte();
							try {
								while(rs.next()){
									out.println("<tr><td><input type='text' id='valrfte"+j+"' value='"+rs.getString(2)+"' class='rep'  style='width:100%' onblur='Valivalor(2,"+j+","+rs.getString(1)+")' /></td><td><input type='text' id='brfte"+j+"' value='"+rs.getString(4)+"' class='rep' style='width:100%' /></td><td><input type='text' id='urfte"+j+"' value='"+rs.getString(5)+"' class='rep' style='width:100%' /></td><td><input type='text' id='drfte"+j+"' value='"+rs.getString(3)+"' class='rep' style='width:100%' /><input type='hidden' id='codrfte"+j+"' value='"+rs.getString(1)+"' /></td>");
									rs1=mp.BusCta(rs.getString(6));
									if(rs1.next()){
										out.println("<td><input type='text' id='ctarfte"+j+"' value='"+rs1.getString(2)+"' class='rep' style='width:100%' onkeyup='Acuenta(2,"+j+")' /></td><td><input type='text' id='dcrfte"+j+"' value='"+rs1.getString(3)+"' class='rep' style='width:100%' disabled /><input type='hidden' id='codctarfte"+j+"' value='"+rs1.getString(1)+"'  class='rep' style='width:100%'/></td>");
									}else{
										out.println("<td><input type='text' id='ctarfte"+j+"' value='NO ASIGNADA' class='rep' style='width:100%' onkeyup='Acuenta(2,"+j+")'  /></td><td><input type='text' id='dcrfte"+j+"' value='NO ASIGNADA'  class='rep' style='width:100%' disabled  /><input type='hidden' id='codctarfte"+j+"' value='NO' style='width:100%'/></td>");
									}rs1.getStatement().getConnection().close();
									j=j+1;
									out.println("</tr>");
									out.println("<tr><td colspan='3'></td><td colspan='3' ><div id='vctarfte"+j+"' ></div></td></tr>");
								}rs.getStatement().getConnection().close();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							out.println("<tr>");
							out.println("<td align='center' colspan='3'>");
							out.println("<input type='button' value='Nuevo' id='bn2' onclick='NuevoImpuesto(2)' />");
							out.println("</td>");
							out.println("<td align='center' colspan='3' >");
							out.println("<input type='button' value='Modificar' onclick='ModImpuesto(2,"+j+")' />");
							out.println("</td>");
							out.println("</tr>");
							out.println("<tr>");
							out.println("<td colspan='6'><div id='nvrfte' ></div></td>");
							out.println("</tr>");
							out.println("</table>");
							
						out.println("</td>");
						out.println("</tr>");//Final rfte
						out.println("<tr>");
						out.println("<td>");//ICA
							out.println("<table width='100%'>");
							out.println("<tr>");
							out.println("<td colspan='4' bgcolor='#E7E9EB' align='center'><b>ICA</b></td></tr>");
							out.println("<tr align='center'><td >Valor</td><td>Descripcion</td><td>Cod Cuenta</td><td>Nombre Cuenta</td></tr>");
							rs=mp.BuscarIca();
							int k=1;
							try {
								while(rs.next()){
									out.println("<tr><td><input type='text' value='"+rs.getString(2)+"' id='valica"+k+"' class='rep' style='width:100%'  onblur='Valivalor(3,"+k+","+rs.getString(1)+")' /></td><td><input type='text' value='"+rs.getString(3)+"' id='dica"+k+"' class='rep' style='width:100%' /><input type='hidden' id='codica"+k+"' value='"+rs.getString(1)+"' /></td>");
									rs1=mp.BusCta(rs.getString(4));
									if(rs1.next()){
										out.println("<td><input type='text' id='ctaica"+k+"' value='"+rs1.getString(2)+"' class='rep' style='width:100%' onkeyup='Acuenta(3,"+k+")' /></td><td><input type='text' id='dctaica"+k+"' value='"+rs1.getString(3)+"' class='rep' style='width:100%' disabled /><input type='hidden' id='codctaica"+k+"' value='"+rs1.getString(1)+"'  class='rep' /></td>");
									}else{
										out.println("<td><input type='text' id='ctaica"+k+"' value='NO ASIGNADA' class='rep' style='width:100%' onkeyup='Acuenta(3,"+k+")' /></td><td><input type='text' id='dctaica"+k+"' value='NO ASIGNADA'  class='rep' style='width:100%' disabled/><input type='hidden' id='codctaica"+k+"' value='NO'/></td>");
									}rs1.getStatement().getConnection().close();
									out.println("</tr>");
									out.println("<tr><td colspan='2'></td><td colspan='2' ><div id='vctaica"+k+"' ></div></td></tr>");
									
									k=k+1;
								}rs.getStatement().getConnection().close();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							out.println("<tr>");
							out.println("<td align='center' colspan='2'>");
							out.println("<input type='button' value='Nuevo' id='bn3' onclick='NuevoImpuesto(3)' />");
							out.println("</td>");
							out.println("<td align='center' colspan='2' >");
							out.println("<input type='button' value='Modificar' onclick='ModImpuesto(3,"+k+")' />");
							out.println("</td>");
							out.println("</tr>");
							out.println("<tr>");
							out.println("<td colspan='4' ><div id='nvoica' ></div></td>");
							out.println("</tr>");
							out.println("</table>");
						out.println("</td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td>");//CRE
							out.println("<table width='100%'>");
							out.println("<tr>");
							out.println("<td colspan='4' bgcolor='#E7E9EB' align='center'><b>CRE</b></td></tr>");
							out.println("<tr align='center'><td >Valor</td><td>Descripcion</td><td>Cod Cuenta</td><td>Nombre Cuenta</td></tr>");
							rs=mp.BuscarCre();
							int h=1;
							try {
								while(rs.next()){
									out.println("<tr><td><input type='text' value='"+rs.getString(2)+"' id='valcre"+h+"' class='rep' style='width:100%' onblur='Valivalor(4,"+h+","+rs.getString(1)+")' /></td><td><input type='text' value='"+rs.getString(3)+"' id='dcre"+h+"' class='rep' style='width:100%' /><input type='hidden' id='codcre"+h+"' value='"+rs.getString(1)+"' /></td>");
									rs1=mp.BusCta(rs.getString(4));
									if(rs1.next()){
										out.println("<td><input type='text' id='ctacre"+h+"' value='"+rs1.getString(2)+"' class='rep' style='width:100%' onkeyup='Acuenta(4,"+h+")' /></td><td><input type='text' id='dctacre"+h+"' value='"+rs1.getString(3)+"' class='rep' style='width:100%' disabled /><input type='hidden' id='codctacre"+h+"' value='"+rs1.getString(1)+"'  class='rep' /></td>");
									}else{
										out.println("<td><input type='text' id='ctacre"+h+"' value='NO ASIGNADA' class='rep' style='width:100%' onkeyup='Acuenta(4,"+h+")' /></td><td><input type='text' id='dctacre"+h+"' value='NO ASIGNADA'  class='rep' style='width:100%' disabled /><input type='hidden' id='codctacre"+h+"' value='NO'/></td>");
									}rs1.getStatement().getConnection().close();
									out.println("</tr>");
									out.println("<tr><td colspan='2'></td><td colspan='2' ><div id='vctacre"+h+"' ></div></td></tr>");
									h=h+1;
								}rs.getStatement().getConnection().close();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							out.println("<tr>");
							out.println("<td align='center' colspan='2'>");
							out.println("<input type='button' value='Nuevo' id='bn4' onclick='NuevoImpuesto(4)' />");
							out.println("</td>");
							out.println("<td align='center' colspan='2' >");
							out.println("<input type='button' value='Modificar' onclick='ModImpuesto(4,"+h+")' />");
							out.println("</td>");
							out.println("</tr>");
							out.println("<tr>");
							out.println("<td colspan='4'><div id='nvocre' ></div></td>");
							out.println("</tr>");
							out.println("</table>");
						
						out.println("</td>");
					out.println("</tr>");
				out.println("</table>");
			out.println("</td>");
			out.println("</tr>");
			out.println("</table>");
		}
		
		if(va.equals("Valivalor")){
			String Tipo=req.getParameter("Tip");
			String Ind=req.getParameter("Ind");
			String dato=req.getParameter("dato");
			String desc=req.getParameter("desc");
			String reg=req.getParameter("reg");
			rs=mp.BuscarValores(Tipo,dato,reg);
			try {
				if(rs.next()){
					out.println("1_Este valor "+dato+" ya existe dentro de la tabla de "+desc);
				}else{
					out.println("0_");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		
		if(va.equals("ModImp")){
			String Tipo=req.getParameter("Tipo");
			String datos_cant=req.getParameter("datos_cant");
			String datos_desc=req.getParameter("datos_desc");
			String datos_codcta=req.getParameter("datos_codcta");
			String datos_uvt=req.getParameter("datos_uvt");
			String datos_base=req.getParameter("datos_base");
			String Ind=req.getParameter("Ind");
			String tabla=req.getParameter("tabla");
			String datos_codreg=req.getParameter("datos_codreg");
			
			String cant[]=datos_cant.split("_");
			String desc[]=datos_desc.split("_");
			String codcta[]=datos_codcta.split("_");
			String uvt[]=datos_uvt.split("_");
			String base[]=datos_base.split("_");
			String codreg[]=datos_codreg.split("_");
			
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
			java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());
			int ii=Integer.parseInt(Ind);
			System.out.println("valor de ii"+ii);
			for(int i=0;i<ii-1;i++){
				System.out.println("Tipo"+Tipo);
				if(Tipo.equals("2")){
					try {
						mp.ActImpuestos(codreg[i],cant[i],desc[i],codcta[i],uvt[i],base[i], user,Fecha,Hora,Tipo,tabla);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					try {
						System.out.println("valor de i"+i);
						mp.ActImpuestos(codreg[i],cant[i],desc[i],codcta[i],"","", user,Fecha,Hora,Tipo,tabla);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			
			}
		}
		
		if(va.equals("VCtas")){
			String datos=req.getParameter("datos");
			String cc=req.getParameter("cc");
			String vista=req.getParameter("vista");
			String texto=req.getParameter("texto");
			String Ind=req.getParameter("Ind");
			String descp=req.getParameter("descp");
			//System.out.println("Vista "+vista);
			rs=mp.BuscarAux(datos, "2", "");
			out.println("<table>");
			int i=1;
			try {
				while(rs.next()){
					out.println("<tr><td><a href='#' onclick='AsCtaImp("+i+","+rs.getString(1)+","+Ind+")' >"+rs.getString(2)+" | "+rs.getString(3)+"</a><input type='hidden' id='nombcta"+i+"' value='"+rs.getString(3)+"' /><input type='hidden' id='numcta"+i+"' value='"+rs.getString(2)+"' /></td></tr>");
					i=i+1;
				}rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		out.println("<tr><td><input type='hidden' value='"+cc+"' id='cc' /><input type='hidden' value='"+vista+"' id='vist' /><input type='hidden' value='"+descp+"' id='descp' /><input type='hidden' value='"+texto+"' id='texto' /> </td></tr>");	
		out.println("</table>");
			
		}
		//,"+rs.getString(1)+","+cc+","+vista+","+Ind+","+descp+","+texto+"
		
		if(va.equals("NvoImp")){
			String  Tipo=req.getParameter("Tipo");
			if(Tipo.equals("2")){
				out.println("<table width='100%' >");
				out.println("<tr align='center'><td>Valor</td><td>Base</td><td>UVT</td><td>Descripcion</td><td>Cod Cuenta</td><td>Nombre Cuenta</td></tr>");
				out.println("<tr><td><input type='text' id='nval' style='width:100%' class='rep' /></td><td><input style='width:100%' class='rep' type='text' id='bas' /></td><td><input type='text' id='uv' style='width:100%' class='rep' /></td><td><input type='text' id='de' title='de' style='width:100%' class='rep' /></td><td><input type='text' id='ncta' onkeyup='ncta()' style='width:100%' class='rep' /></td><td><input type='text' id='decta' style='width:100%' class='rep' disabled /></td></tr>");
				out.println("<tr><td colspan='4'></td><td colspan='2'><div id='nvistacta'></div></td></tr>");
				out.println("<tr><td colspan='6' ><input type='hidden' id='nuevocodcta' /><input type='button' value='Guardar' onclick='Gimp("+Tipo+")' /></td></tr>");
				out.println("</table>");
			}else{
				out.println("<table width='100%' >");
				out.println("<tr align='center'><td>Valor</td><td>Descripcion</td><td>Cod Cuenta</td><td>Nombre Cuenta</td></tr>");
				out.println("<tr><td><input type='text' id='nval' style='width:100%' class='rep' /></td><td><input type='text' id='de' style='width:100%' class='rep' /></td><td><input type='text' id='ncta' onkeyup='ncta()' style='width:100%' class='rep' /></td><td><input type='text' id='decta' style='width:100%' class='rep' disabled /></td></tr>");
				out.println("<tr><td colspan='2'></td><td colspan='2'><div id='nvistacta'></div></td></tr>");
				out.println("<tr><td colspan='4' ><input type='hidden' id='nuevocodcta' /><input type='button' value='Guardar' onclick='Gimp("+Tipo+")' /></td></tr>");
				out.println("</table>");
			}
		}
		
		if(va.equals("Vistancta")){
			String texto=req.getParameter("texto");
			rs=mp.BuscarAux(texto, "2", "");
			out.println("<table>");
			int i=1;
			try {
				while(rs.next()){
					out.println("<tr><td><a href='#' onclick='nasicta("+i+","+rs.getString(1)+")' >"+rs.getString(2)+" | "+rs.getString(3)+"</td><input type='hidden' id='ncodcta"+i+"' value='"+rs.getString(2)+"' /><input type='hidden' id='nnombre"+i+"' value='"+rs.getString(3)+"' /></tr>");
					i=i+1;
				}rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			out.println("</table>");
		}
		
		if(va.equals("GImpu")){
			String cant=req.getParameter("cant");
			String descrip=req.getParameter("descrip");
			String codcta=req.getParameter("codcta");
			String base=req.getParameter("base");
			String uvt=req.getParameter("uvt");
			String Tipo=req.getParameter("Tipo");
			mp.GuardarImpuestos(Tipo,cant,descrip,codcta,base,uvt,user);
		}
		

}
}
