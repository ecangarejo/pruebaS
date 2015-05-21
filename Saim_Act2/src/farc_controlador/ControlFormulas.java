package farc_controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.text.*;
import java.util.StringTokenizer;  //para dividir por token |

import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import lab_logica.MetodolabPa;

import farc_metodo.MetodoCrearArticulo;
import farc_metodo.MetodoSalidas;

/**
 * Servlet implementation class ControlFormulas
 */
public class ControlFormulas extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		
		MetodoSalidas ms = new MetodoSalidas();
		
		String va = request.getParameter("valor");
		String formula = request.getParameter("formula");
		String codm = request.getParameter("codm");
		//String codmed = request.getParameter("codm");
		String bode = request.getParameter("bode");
		String inv = request.getParameter("inv");
		String cf = request.getParameter("cf");
		String ca = request.getParameter("ca");
		String D = request.getParameter("D");
		String N = request.getParameter("N");
		String detalle = request.getParameter("detalle");
		if(detalle==null){detalle="";}
		String datos = request.getParameter("datos");
		if(datos==null){datos="";}
		String diss = request.getParameter("diss");
		if(diss==null){diss="";}
		String sw1 = request.getParameter("sw1");
		if(sw1==null){sw1="0";}
		String movi = request.getParameter("movi");
		if(movi==null){movi="0";}
		String usu = request.getParameter("usu");
		if(usu==null){usu="";}
		String Ne = request.getParameter("Ne");
		if(Ne==null){Ne="";}
		String cdf = request.getParameter("cdf");
		if(cdf==null){cdf="";}
		String cfo = request.getParameter("cfo");
		if(cfo==null){cfo="";}
		String cdisp = request.getParameter("cdisp");
		if(cdisp==null){cdisp="";}
		String invd = request.getParameter("invd");
		if(invd==null){invd="";}
		String cantd = request.getParameter("cantd");
		if(cantd==null){cantd="";}
		String concep = request.getParameter("concep");
		if(concep==null){concep="";}
		String codpa = request.getParameter("codpa");
		if(codpa==null){codpa="";}
		String admp = request.getParameter("admp");
		if(admp==null){admp="";}
		String cp = request.getParameter("cp");
		if(cp==null){cp="";}
		String codmovdis = request.getParameter("codmovdis");
		if(codmovdis==null){codmovdis="";}
		
		//if(conta==1){datos2="";}
		
		
		
		
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		ResultSet rs3=null;
		ResultSet rs4=null;
		ResultSet rs5=null;
		ResultSet rs6=null;
		
		String V[] = new String[250];
		String VV[] = new String[250];
		String M[][] = new String[50][5];
		String MV[][] = new String[2][5];

		//out.print("<input name='txtM' type='hidden' id='txtM' value='"+datos+"' /></td>");//value='"+datos2+"'  identificador Div+identificador campo
		
		
		if(va.equals("Formulas")){	
			
			//out.print("<table width='100%' class='style6'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Formulas Pendientes por Entregar</div></td></tr>");
			out.print("<table width='100%' border='1' class='style12'><tr><td width='5%'><div align='center'></div></td><td width='11%'><div align='center'></div></td><td width='27%'><div align='center'></div></td><td width='31%'><div align='center'></div></td><td width='6%'><div align='center'></div></td><td width='19%'><div align='center'></div></td></tr>");
			
			rs=ms.ObtenerFormula();
			try {
				while(rs.next()){
				 out.print(" <tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+" "+rs.getString(3)+"</td><td><a  href='#'onclick='entregar("+rs.getString(1)+","+sw1+","+rs.getString(12)+","+rs.getString(13)+")'>"+rs.getString(4)+" "+rs.getString(5)+" "+rs.getString(6)+"</a></td><td>"+rs.getString(7)+" - "+rs.getString(8)+"</td><td>"+rs.getString(9)+"</td><td>"+rs.getString(10)+" "+rs.getString(11)+"</td></tr>");//value="+CodAsignacion+"
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
				
		}
		if(va.equals("Medicamentos1")){	
			rs1=ms.Dispensacion(formula);			
			try {
				while(rs1.next()){
					if(rs1.getInt(1)>0){
						sw1="1";
					}
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("<input name='txtsw' type='hidden' id='txtsw' value='"+sw1+"' /></td>");//value='"+datos2+"'  identificador Div+identificador campo
			out.print("<table width='100%' border='1' class='style12'><tr><td width='5%'><div align='center'></div></td><td width='11%'><div align='center'></div></td><td width='27%'><div align='center'></div></td><td width='31%'><div align='center'></div></td><td width='6%'><div align='center'></div></td><td width='19%'><div align='center'></div></td></tr>");
			
		rs=ms.ObtenerFormulae(formula);
		try {
			while(rs.next()){
				out.print(" <tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+" "+rs.getString(3)+"</td><td><a  href='#'onclick='entregar("+rs.getString(1)+","+sw1+","+admp+","+cp+")'>"+rs.getString(4)+" "+rs.getString(5)+" "+rs.getString(6)+"</a></td><td>"+rs.getString(7)+" - "+rs.getString(8)+"</td><td>"+rs.getString(9)+"</td><td>"+rs.getString(10)+" "+rs.getString(11)+"</td></tr>");//value="+CodAsignacion+"
			//	out.print(" <tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+" "+rs.getString(3)+"</td><td>"+rs.getString(4)+" "+rs.getString(5)+" "+rs.getString(6)+"</td><td>"+rs.getString(7)+" - "+rs.getString(8)+"</td><td>"+rs.getString(9)+"</td><td>"+rs.getString(10)+" "+rs.getString(11)+"</td></tr>");//value="+CodAsignacion+"
			}
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		
		out.print("<table width='100%' border='1' class='style12'><tr><td width='100%'><div align='center'></div></td></tr>");
		//out.print("moviiiiiisss: "+movis);out.print("sw1iiiiii: "+sw1);out.print("formuiiiiii: "+formula);
		out.print("<tr><td><label><div align='center'>Seleccione El tipo de Movimiento que desea Realizar. <select name='cmbMovi' id='cmbMovi' onChange='Movi("+formula+","+sw1+","+movi+","+admp+","+cp+")' ><option value='Seleccione'>Seleccione</option>");
		rs1=ms.listarMovimientoss();
		try {
			while(rs1.next()){
				out.print("<option value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
			}
			rs1.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		out.print("</select></div></label></td>");
		
		}
		
		
		if(va.equals("Medicamentos")){	
			
				rs1=ms.Dispensacion(formula);			
				try {
					while(rs1.next()){
						if(rs1.getInt(1)>0){
							sw1="1";
						}
					}
					rs1.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				out.print("<input name='txtsw' type='hidden' id='txtsw' value='"+sw1+"' /></td>");//value='"+datos2+"'  identificador Div+identificador campo
				
				out.print("<table width='100%' border='1' class='style12'><tr><td width='5%'><div align='center'></div></td><td width='11%'><div align='center'></div></td><td width='27%'><div align='center'></div></td><td width='31%'><div align='center'></div></td><td width='6%'><div align='center'></div></td><td width='19%'><div align='center'></div></td></tr>");
				
			rs=ms.ObtenerFormulae(formula);
			try {
				while(rs.next()){
					out.print(" <tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+" "+rs.getString(3)+"</td><td><a  href='#'onclick='entregar("+rs.getString(1)+","+sw1+","+admp+","+cp+")'>"+rs.getString(4)+" "+rs.getString(5)+" "+rs.getString(6)+"</a></td><td>"+rs.getString(7)+" - "+rs.getString(8)+"</td><td>"+rs.getString(9)+"</td><td>"+rs.getString(10)+" "+rs.getString(11)+"</td></tr>");//value="+CodAsignacion+"
					//out.print(" <tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+" "+rs.getString(3)+"</td><td>"+rs.getString(4)+" "+rs.getString(5)+" "+rs.getString(6)+"</td><td>"+rs.getString(7)+" - "+rs.getString(8)+"</td><td>"+rs.getString(9)+"</td><td>"+rs.getString(10)+" "+rs.getString(11)+"</td></tr>");//value="+CodAsignacion+"
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			out.print("<table width='100%' border='1' class='style12'><tr><td width='100%'><div align='center'></div></td></tr>");
			
			//out.print("moviiiiii: "+movi);out.print("sw1iiiiii: "+sw1);out.print("formuiiiiii: "+formula);
			
			out.print("<tr><td><label><div align='center'>Tipo de Movimiento a Realizar. <select name='cmbMovi' id='cmbMovi' >");
			rs1=ms.listarMovimientossc(movi);
			try {
				while(rs1.next()){
					out.print("<option value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("</select></div></label></td>");
						
			out.print("<table width='100%' class='style6'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Medicamentos en la Formula</div></td></tr>");
			out.print("<table width='100%' border='1' class='style6'><tr><td width='38%'><div align='center'>Medicamento</div></td><td width='26%'><div align='center'>Dosis</div></td><td width='7%'><div align='center'>Cantidad</div></td><td width='7%'><div align='center'>Entregadas</div></td><td width='7%'><div align='center'>Pendientes</div></td><td width='8%'><div align='center'>Omitir</div></td><td width='7%'><div align='center'>Eliminar</div></td></tr>");
	
			//out.print("<table width='100%' class='style6'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Formulas Pendientes por Entregar</div></td></tr>");
			out.print("<table width='100%' border='1' class='style6'><tr><td width='38%'><div align='center'></div></td><td width='26%'><div align='center'></div></td><td width='7%'><div align='center'></div></td><td width='7%'><div align='center'></div></td><td width='7%'><div align='center'></div></td><td width='8%'><div align='center'></div></td><td width='7%'><div align='center'></div></td></tr>");
			rs=ms.ObtenerMedicamento(formula);
			
			try {
				while(rs.next()){
					//System.out.print("rs3: "+rs.getString(3));
					//System.out.print(" rs4: "+rs.getString(4));
					
					int dis=Integer.parseInt(rs.getString(3))-Integer.parseInt(rs.getString(4));
					if ((dis!=0)&&(!rs.getString(8).equals("3"))&&(!rs.getString(8).equals("2"))){
					out.print(" <tr><td><a  href='#'onclick='Dispensa("+rs.getString(5)+","+formula+","+rs.getString(3)+","+rs.getString(6)+","+rs.getString(4)+","+sw1+","+admp+","+cp+")'>"+rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(10)+" "+rs.getString(11)+"</a></td><td>"+rs.getString(7)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>"+dis+"</td>"); 	
					if(rs.getInt(4)==0){
					 out.print("<td><a  href='#'onclick='Noexiste("+rs.getString(6)+","+formula+","+sw1+","+movi+")'>Existencias</a></td>");
					}else{
					 out.print("<td>&nbsp</td>");	
					}
					out.print("<td><a  href='#'onclick='Nodispensa("+rs.getString(6)+","+formula+","+sw1+","+movi+")'>Eliminar</a></td></tr>");
					}else{
					out.print(" <tr><td>"+rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(10)+" "+rs.getString(11)+"</td><td>"+rs.getString(7)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>"+dis+"</td><td>&nbsp</td><td>&nbsp</td>"); 																																															
					}
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
	
		}
		
		
		if(va.equals("Dispensacion")){	
			//out.print("codmed "+codm);
			//out.print("<table width='100%' border='1' class='style6'><tr><td width='55%'><div align='center'>Bodega</div></td></tr>");
			out.print("<table width='100%' border='1' class='style6'><tr><td width='15%'><div align='center'>Bodega</div></td><td width='70%'><div align='center'>Medicamentos</div></td><td width='5%'><div align='center'>Cantidad</div></td><td width='10%'><div align='center'>Acción</div></td></tr>");
			int sw=0;
			rs2=ms.ObtenerBodegaM(codm,usu);
			int cb=0;
			String bb="";
			//System.out.println("codm: "+codm+" user: "+usu);
			try {
				if(rs2.next()){
					out.print("<tr><td><label><select name='cmbBodega' id='cmbBodega' onChange='Lotes("+codm+","+formula+","+cf+","+detalle+","+diss+","+sw1+","+admp+","+cp+")' >");
					rs1=ms.ObtenerBodegaM(codm,usu);
					
					try {
						while(rs1.next()){
							out.print("<option value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
							if(cb==0){
							bb=rs1.getString(1);
							}
							cb++;
						}
						rs1.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					out.print("</select></label></td>");
				}else{out.print("<tr><td colspan='2'>No hay existencias del medicamento en sus bodegas asignadas!!!</td></tr>"); sw=1;}
				rs2.getStatement().getConnection().close();
				///////////////////////////////////////

				
			
				/////////////////////////////////////////
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			//////////////////////////
			if(sw==0){
			int ce=0;
			String ex="";
			String in="";
			out.print("<td><label><select name='cmbLotes' id='cmbLotes' onChange='ValidaBodega("+codm+","+formula+","+cf+","+detalle+","+diss+","+sw1+","+admp+","+cp+")'>");
			rs2=ms.ObtenerMedicamentosL(codm,bb);
			try {
				while(rs2.next()){
					out.print("<option value="+rs2.getString(1)+">"+rs2.getString(2)+"  "+rs2.getString(3)+"   "+rs2.getString(7)+"       Lote: "+rs2.getString(4)+"  Existencias: "+rs2.getString(5)+"  Vence: "+rs2.getString(6)+"</option>");
					if(ce==0){
						ex=rs2.getString(5);
						in=rs2.getString(1);
						}
						ce++;
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace(); 
			}
			out.print("</select></label></td>");
			//out.print("<td><input name='txtcant' type='text' id='txtcant' size='5' onFocus='validamed()' onkeyup='checknumsdisp()' /></td>");//identificador Div+identificador campo
			
			//out.print("<td><input type='button' name='btnDispensar' id='btnDispensar' value='Dispensar' onClick='Enviar()'></td></tr>");	
			out.print("<td><input name='txtcant' type='text' id='txtcant' size='5' onfocus='validamed()' onkeyup='checknumsdisp()' onChange='exis("+ex+","+cf+")' /></td>");//  onBlur='cantidades("+codm+","+bode+")' identificador Div+identificador campo
			
			out.print("<td><input type='button' name='btnDispensar' id='btnDispensar' value='Dispensar' onClick='Dispensar("+in+","+codm+","+formula+","+cf+","+detalle+","+diss+","+sw1+","+admp+","+cp+")'></td></tr>");		
			}
			
			//////////////	
		}
		
		if(va.equals("Dispensacion2")){	
			//out.print("codmed "+codm);
			//out.print("<table width='100%' border='1' class='style6'><tr><td width='55%'><div align='center'></div></td><td width='10%'><div align='center'></div></td><td width='10%'><div align='center'></div></td><td width='10%'><div align='center'></div></td><td width='13%'><div align='center'></div></td></tr>");
			out.print("<table width='100%' border='1' class='style6'><tr><td width='15%'><div align='center'>Bodega</div></td><td width='70%'><div align='center'>Medicamentos</div></td><td width='5%'><div align='center'>Cantidad</div></td><td width='10%'><div align='center'>Acción</div></td></tr>");
			out.print("<tr><td><label><select name='cmbBodega' id='cmbBodega' onChange='Lotes("+codm+","+formula+","+cf+","+detalle+","+diss+","+sw1+","+admp+","+cp+")' >");
			rs1=ms.ObtenerBodegac(bode);
			try {
				while(rs1.next()){
					//out.print("<option value='jukennnn'>jukeennn</option>");
					out.print("<option value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			rs3=ms.ObtenerBodegasc(bode,codm);
			try {
				while(rs3.next()){
					//out.print("<option value=cesar>cesarrrrr</option>");
					out.print("<option value="+rs3.getString(1)+">"+rs3.getString(2)+"</option>");
				}
				rs3.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			out.print("</select></label></td>");
			
			out.print("<td><label><select name='cmbLotes' id='cmbLotes' onChange='ValidaBodega("+codm+","+formula+","+cf+","+detalle+","+diss+","+sw1+","+admp+","+cp+")'><option value='Seleccione'>Seleccione</option>");
			rs2=ms.ObtenerMedicamentosL(codm,bode);
			try {
				while(rs2.next()){
					out.print("<option value="+rs2.getString(1)+">"+rs2.getString(2)+"  "+rs2.getString(3)+"  Lote: "+rs2.getString(4)+"  Existencias: "+rs2.getString(5)+"  Vence: "+rs2.getString(6)+"</option>");
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("</select></label></td>");
			out.print("<td><input name='txtcant' type='text' id='txtcant' size='5' onFocus='validamed()' onkeyup='checknumsdisp()' /></td>");//identificador Div+identificador campo
			
			out.print("<td><input type='button' name='btnDispensar' id='btnDispensar' value='Dispensar' onClick='Enviar()'></td></tr>");		

		}
			
		
		if(va.equals("Dispensacion3")){	
			
			System.out.print("detalle "+detalle);
			//out.print("<table width='100%' border='1' class='style6'><tr><td width='55%'><div align='center'></div></td><td width='10%'><div align='center'></div></td><td width='10%'><div align='center'></div></td><td width='10%'><div align='center'></div></td><td width='13%'><div align='center'></div></td></tr>");
			out.print("<table width='100%' border='1' class='style6'><tr><td width='15%'><div align='center'>Bodega</div></td><td width='70%'><div align='center'>Medicamentos</div></td><td width='5%'><div align='center'>Cantidad</div></td><td width='10%'><div align='center'>Acción</div></td></tr>");
			out.print("<tr><td><label><select name='cmbBodega' id='cmbBodega' onChange='Lotes("+codm+","+formula+","+cf+","+detalle+","+diss+","+sw1+","+admp+","+cp+")' >");
			rs1=ms.ObtenerBodegac(bode);
			try {
				while(rs1.next()){
					out.print("<option value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			rs3=ms.ObtenerBodegasc(bode,codm);
			try {
				while(rs3.next()){
					out.print("<option value="+rs3.getString(1)+">"+rs3.getString(2)+"</option>");
				}
				rs3.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			out.print("</select></label></td>");
			
			out.print("<td><label><select name='cmbLotes' id='cmbLotes' onChange='ValidaBodega("+codm+","+formula+","+cf+","+detalle+","+diss+","+sw1+","+admp+","+cp+")'>");
			rs2=ms.ObtenerMedicamentosLc(codm,bode,inv);
			String c="";
			try {
				while(rs2.next()){
					c=rs2.getString(5);
					out.print("<option value="+rs2.getString(1)+">"+rs2.getString(2)+"  "+rs2.getString(3)+"  Lote: "+rs2.getString(4)+"  Existencias: "+rs2.getString(5)+"</option>");
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			rs4=ms.ObtenerMedicamentosLsc(codm,bode,inv);
			try {
				while(rs4.next()){
					out.print("<option value="+rs4.getString(1)+">"+rs4.getString(2)+"  "+rs4.getString(3)+"  Lote: "+rs4.getString(4)+"  Existencias: "+rs4.getString(5)+"  Vence: "+rs4.getString(6)+"</option>");
				}
				rs4.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("</select></label></td>");
			out.print("<td><input name='txtcant' type='text' id='txtcant' size='5' onfocus='validamed()' onkeyup='checknumsdisp()' onChange='exis("+c+","+cf+")' /></td>");//  onBlur='cantidades("+codm+","+bode+")' identificador Div+identificador campo
			
			out.print("<td><input type='button' name='btnDispensar' id='btnDispensar' value='Dispensar' onClick='Dispensar("+inv+","+codm+","+formula+","+cf+","+detalle+","+diss+","+sw1+","+admp+","+cp+")'></td></tr>");		
		
		}
		
		if(va.equals("ValidaBodegaC")){ 
			rs4=ms.ValidaBodega(bode,usu);
			int sw=0;
			try {
				if(rs4.next()){
					sw=1;
				}
				rs4.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print(sw);
		}
		
		
		
		
		if(va.equals("Eliminare")){ 
			 ms.ActualizaEstadosD(Ne,"2");
		}
		
		if(va.equals("Eliminaren")){ 
			 ms.ActualizaEstadosD(Ne,"3");
		}
		
		
		if(va.equals("Actualizarf")){
						
			if((Integer.parseInt(diss)+Integer.parseInt(ca))>Integer.parseInt(cf)){
				out.print("Dispensar "+ca+" Sobrepasa la cantidad formulada!!!");
			}else{
			String cadb="";
			rs2=ms.Consultare(inv);
			try {
				while(rs2.next()){
					cadb=rs2.getString(1);
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			System.out.println("inv:"+inv+" cadb:"+cadb+"  ca:"+ca);
			int saca = Integer.parseInt(cadb)-Integer.parseInt(ca);
			String can=String.valueOf(saca);
			
			ms.Dispensari(inv,can);
		
			rs1=ms.Consultard(detalle);
			
			String caddb="";
			try {
				while(rs1.next()){
					caddb=rs1.getString(1);
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			int mete = Integer.parseInt(caddb)+Integer.parseInt(ca);
			String cad=String.valueOf(mete);
			
			ms.Dispensarf(detalle,cad);
			
			
			rs4=ms.ObtenerUDispensa(detalle);
			String ud="";
			try {
				while(rs4.next()){
					ud=rs4.getString(1);
				}
				rs4.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			
			
			Calendar c = new GregorianCalendar();
			String dia = Integer.toString(c.get(Calendar.DATE));
			String mes = Integer.toString(c.get(Calendar.MONTH)+1);
			String annio = Integer.toString(c.get(Calendar.YEAR));
			String fec=annio+"-"+mes+"-"+dia;
			
			Calendar calendario = Calendar.getInstance();
			//	Calendar calendario = new GregorianCalendar();
				int hora, minutos, segundos;
				hora =calendario.get(Calendar.HOUR_OF_DAY);
				minutos = calendario.get(Calendar.MINUTE);
				segundos = calendario.get(Calendar.SECOND);
				String hra= hora+":"+minutos+":"+segundos;
			
				
				
				
				///////////////////////////INSERTAMOS O ACTUALIZAMOS LA DISPENSACION DEL PERFIL FARMACOTERAPEUTICO//////////////////////////
				rs2=ms.ConsultarTipoMed(codm);
				String tdm="";
				try {
					if(rs2.next()){
						tdm=rs2.getString(1);
					}
					rs2.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				
				if(tdm.equals("1")){
					System.out.print("Es medicamento");


				rs2=ms.ConsultarDetallePerfil(detalle);
				String cdp="";
				try {
					if(rs2.next()){
						cdp=rs2.getString(1);
					}
					rs2.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				
				rs2=ms.ConsultarDispensacionPerfil(cdp);
				String cdd="";
				try {
					if(rs2.next()){
						cdd=rs2.getString(1);
					}
					rs2.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				
				if(cdd.equals("")){
					ms.InsertarDispensacionPerfil(cdp,cad,"0",fec,hra,usu);
				}else{
					ms.ActualizarDispensacionPerfil(cad,fec,hra,usu,cdp);
				}
				}
				/////////////////////////FINNNN    INSERTAMOS O ACTUALIZAMOS LA DISPENSACION DEL PERFIL FARMACOTERAPEUTICO//////////////////////////
					
				
			
			rs3=ms.ObtenerIdentificador(formula);
			String iden="";
			try {
				while(rs3.next()){
					iden=rs3.getString(1);
				}
				rs3.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			if(iden==""){iden="1";
			ms.CrearIdentificadorm(formula);
			}
						
			
			
			/****AQUI SE HACE EL CARGUE AUTOMATICO A FACTURACION ****/
			String enca="";
			rs1=ms.ConsultarEncabezado(admp);
			try {
				if(rs1.next()){
					enca=rs1.getString(1);
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			String Prog="";
			rs2=ms.ConsultarPrograma(codm);
			try {
				if(rs2.next()){
					Prog=rs2.getString(1);
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			String rProg="";
			String dProg="";
			String csProg="";
			String scProg="";
			rs2=ms.DetallePrograma(Prog);
			try {
				if(rs2.next()){
					rProg=rs2.getString(1);
					dProg=rs2.getString(2);
					csProg=rs2.getString(3);
					scProg=rs2.getString(4);
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			int sw=0;
			String vProg="";
			rs1=ms.ValorPrograma(cp,Prog);
			try {
				if(rs1.next()){
					vProg=rs1.getString(1);
					sw=1;
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			System.out.println("Encabezado: "+enca);
			System.out.println("Medicamento: "+codm);
			System.out.println("Programa: "+Prog);
			System.out.println("ValorPrograma: "+vProg);
			
			/*if(sw==1){
			ms.CargarDetalle(fec,hra,"1",Prog,rProg,dProg,null,null,csProg,fec,ca,vProg,usu,enca,"",scProg,null,"0");
			}else{
			ms.CargarDetalleSintarifa(Prog);
			}*/
			
			/*****HASTA AQUI QUEDA EL CARGUE AUTOMATICO****/
			
			ms.CrearMovDis(ud, ca, movi, fec, hra, usu, formula, inv, bode, iden, enca);
			

			/***COSTO DE DISPENSACION****/
			String vunitario="";
			String cod_iva="";
			String lote="";
				
			rs2=ms.Consultare(inv);
			try {
				if(rs2.next()){
					vunitario=rs2.getString(2);
					cod_iva=rs2.getString(3);
					lote=rs2.getString(4);
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			Double vtotal=Integer.parseInt(ca)*Double.parseDouble(vunitario);
			if(cod_iva.equals("2")){vtotal=vtotal*1.16; vunitario=String.valueOf(Double.parseDouble(vunitario)*1.16);}
			
			String vt=String.valueOf(vtotal);
						
			String codprog="";
			rs5=ms.ConsultarProgramaMedicamento(codm);
			try {
				if(rs5.next()){
					codprog=rs5.getString(1);
				}
				rs5.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			ms.InsertarCostos(admp,inv,fec,hra,ca,vunitario,vt,codm,lote,codprog);
			/***FIN COSTO DE DISPENSACION****/

			}//else de sobrepasan la formulacion
			
			
			rs5=ms.EstadosD(formula);
			try {
				while(rs5.next()){
				 ms.ActualizaEstadosD(rs5.getString(1),"1");
				}
				rs5.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			

		}
		
		if(va.equals("Mostrarb")){
			out.print("<center><table><tr><td colspan='2'><div align='center'><label><input type='button' name='btnCrearInv' id='btnCrearInv' value='Lista de Pacientes' onClick='Formulas()'></label></div></td>");
			if (!sw1.equals("0")){
			 out.print("<td colspan='2'><div align='center'><label><input type='button' name='btnCrearInv' id='btnCrearInv' value='Generar la Entrega' onClick='DispensarMov("+formula+","+admp+","+cp+")'></label></div></td></tr>");
			}
			out.print("</table></center>");
		}
		
		if(va.equals("Mostrarbd")){
			if (sw1.equals("0")){
			 out.print("<tr><td colspan='2'><div align='center'><label><input type='button' name='btnCrearInv' id='btnCrearInv' value='Lista de Pacientes' onClick='Formulas()'></label></div></td></tr>");
			}
		}
		
					
		if(va.equals("Movimientos")){
		
			
			rs4=ms.ObtenerIdentificador(formula);
			String idenm="";
			try {
				while(rs4.next()){
					idenm=rs4.getString(1);
				}
				rs4.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			
			
			rs=ms.ConsultarMov(idenm,formula);
			int tc=0;
			String tp="";
			String us="";
			String co="";
			try {
				while(rs.next()){
					tc=tc+rs.getInt(1);
					tp=rs.getString(2);
					us=rs.getString(3);	
					co=rs.getString(4);	
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			
			Calendar c = new GregorianCalendar();
			String dia = Integer.toString(c.get(Calendar.DATE));
			String mes = Integer.toString(c.get(Calendar.MONTH)+1);
			String annio = Integer.toString(c.get(Calendar.YEAR));
			String fec=annio+"-"+mes+"-"+dia;
			
			Calendar calendario = Calendar.getInstance();
			//	Calendar calendario = new GregorianCalendar();
			int hora, minutos, segundos;
			hora =calendario.get(Calendar.HOUR_OF_DAY);
			minutos = calendario.get(Calendar.MINUTE);
			segundos = calendario.get(Calendar.SECOND);
			String hra= hora+":"+minutos+":"+segundos;
			
			String tcs= String.valueOf(tc);
			//System.out.print("usu: "+us);
			
			rs1=ms.ObtenerConsecutivo("4");
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
			ms.ActualizaConsecutivo("4",cns);
			 
	
			ms.LlenarMov(tcs,tp,fec,hra,us,formula,co,cons);
	
				
			/*****************************/			
			
			//System.out.print("codadmision: "+admp);
			//System.out.print("codpaciente: "+cp);
			
			rs6=ms.ConsultarEncabezado(admp,cp);
			String ce="";
			try {
				if(rs6.next()){
					ce=rs6.getString(1);
				}
				rs6.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			//ce es mi codigo de encabezado de la factura para 
			//hacer el cargue desde la dispensacion
			
			//revisar el cargue manual porque el codigo d eps esta malo en saludcoop no es 64 sino 352
			
		
			
			
			
			/**********************************	*/	
			
			
			
			///545456456456/////
			String umov="";
			rs2=ms.ObtenerUMovimiento(fec,hra);
			try {
				while(rs2.next()){
					umov=rs2.getString(1);
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			//System.out.print("umov: "+umov);
			rs1=ms.ConsultarMov(idenm,formula);
			try {
				while(rs1.next()){
					ms.LlenarDMov(umov,rs1.getString(5),rs1.getString(6),rs1.getString(1));	
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			
			int pidenm=0;
			if(idenm==""){pidenm=1;
			}else{
				pidenm=Integer.parseInt(idenm)+1;
			}
			
			ms.Actualizaid(formula,String.valueOf(pidenm));//pasar el valor ya consultado +1
			
			rs5=ms.EstadosF(formula);
			int sw=0;
				try {
					while(rs5.next()){
					sw=1;	
					}
					rs5.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				
				if(sw==0){ms.ActualizaEstadosF(formula);}
				out.print(umov);	
		}
		
		
		
		
		if(va.equals("Devolucion")){
			out.print("<table width='100%' class='style6'><tr><td colspan='2'><div align='center' class='style11' id='cabecera2'>Devolución con Cargo a Paciente</div></td></tr>");
			out.print("<table width='100%' border='0' class='style6'><tr><td width='12%'>Tipo de Documento: </td><td width='15%'><label><select name='cmbD' id='cmbD'><option value='Seleccione'>Seleccione</option>");
			rs1=ms.listarDocumentos();
			try {
				while(rs1.next()){
					out.print("<option value="+rs1.getString(1)+">"+rs1.getString(1)+"</option>");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("</select></div></label></td>");
			out.print("<td width='14%'>Numero de Documento: </td><td width='18%'><label><input name='txtDocumento' type='text' id='txtDocumento' size='20' onkeyup='checknums()'/></label></td>");
			out.print("<td colspan='2'><div align='left'><label><input type='button' name='btnBuscar' id='btnBuscar' value='Consultar' onClick='BuscarP()'></label></div></td></tr>");
			out.print("</table>");
			out.print("<tr><td><div id='Admisiones'></div></td></tr>");	
		}
		
		
		
		if(va.equals("Devolucion2")){
			//out.print("<table width='100%' class='style6'><tr><td colspan='2'><div align='center' class='style11' id='cabecera2'>Devolución con Cargo a Paciente</div></td></tr>");
			out.print("<table width='100%' border='0' class='style6'><tr><td>");
			
			String codp="";
			rs2=ms.ConsultarPaciente(D,N);
			try {
				while(rs2.next()){
					codp=rs2.getString(4);
					out.print("<div align='center'>Paciente: "+rs2.getString(1)+" "+rs2.getString(2)+" "+rs2.getString(3)+"</div></td>");
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			//out.print("<tr>&bnsp</tr>");
			String adm="";
			rs3=ms.ConsultarAdmisiones(codp);
			try {
				while(rs3.next()){
				adm=rs3.getString(1);
				out.print("<td><div align='center'>Número de Admisión: "+rs3.getString(1)+"</div></td><td><div align='center'>  Fecha del Ingreso: "+rs3.getString(2)+"</div></td></tr>");
				}
				rs3.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			//out.print("<table width='100%' class='style6'><tr><td colspan='2'><div align='center' class='style11' id='cabecera2'>Formulaciones del Paciente</div></td></tr>");
			out.print("<table width='100%' class='style6'><tr><td colspan='6'><div align='center' class='style11' id='cabecera2'>Medicamentos dispensados al Paciente</div></td></tr>");
			
		
					//out.print("<tr><td><a  href='#'onclick='medica("+rs4.getString(1)+","+codp+")'>Formula: "+rs4.getString(1)+"</a></td><td>Medico: "+rs4.getString(4)+" "+rs4.getString(5)+"</td></tr>");
				/////////////////////////////////////
					
					rs=ms.ObtenerMedicamentoCJxformula(adm);
					int con=0;
					try {
						while(rs.next()){
							con++;
							int dis=Integer.parseInt(rs.getString(3))-Integer.parseInt(rs.getString(4));
							
							///Buscar la cantidad devuelta si tiene
							String devol="";
							rs1=ms.ConsultarDevoluciones(rs.getString(9), rs.getString(14));
							try {
								while(rs1.next()){
									devol=rs1.getString(1);
									if(devol==null){devol="";}
									System.out.println("devol: "+devol);
								}
								rs1.getStatement().getConnection().close();
							} catch (SQLException e) {
								out.print("Error "+e);
								e.printStackTrace();
							}
							
							//System.out.println(rs.getString(4)+"  -----  "+ rs.getString(17));
							if(rs.getString(4).equals(devol)){
							out.print("<tr><td><a  href='#'onclick='Dispensada()'><FONT COLOR=red>"+rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(10)+" "+rs.getString(11)+ "</FONT></a></td><td>Lote:  "+rs.getString(12)+" / Vence:"+rs.getString(17)+"</td><td>Cantidad Dispensada:   "+rs.getString(4)+"</td><td>Fecha Disp:  "+rs.getString(15)+" / "+rs.getString(16)+"</td><td>Formula:    "+rs.getString(18)+"</a></td><td>Medico:    "+rs.getString(21)+" "+rs.getString(22)+"</td></tr>");
							}else{
							out.print("<tr><td><a  href='#'onclick='devolmedica("+rs.getString(6)+","+rs.getString(13)+","+rs.getString(3)+","+rs.getString(9)+","+rs.getString(23)+","+rs.getString(14)+")'>"+rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(10)+" "+rs.getString(11)+ "</a></td><td>Lote:  "+rs.getString(12)+" / Vence: "+rs.getString(17)+"</td><td>Cantidad Dispensada:   "+rs.getString(4)+"</td><td>Fecha Disp:  "+rs.getString(15)+" / "+rs.getString(16)+"</td><td>Formula:    "+rs.getString(18)+"</a></td><td>Medico:    "+rs.getString(21)+" "+rs.getString(22)+"</td></tr>");
							}
							//out.print(" <tr><td><a  href='#'onclick='devolmed(&quot;"+rs.getString(6)+"&quot;");
							
						//	out.print(" <tr><td><a  href='#'onclick='devolmed("+rs.getString(6)+","+rs.getString(3)+","+rs.getString(9)+","+codpa+")'>"+rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(10)+" "+rs.getString(11)+"</a></td><td>Formula:    "+rs4.getString(1)+"</a></td><td>Medico:    "+rs4.getString(4)+" "+rs4.getString(5)+"</td>"); 
					}
						rs.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}	
					////////////////////////////////////////
				
			out.print("<tr><td colspan='6'><div id='Medicas'></div></td></tr>");				
		}	
		
	/*	if(va.equals("Devolucion2")){
			//out.print("<table width='100%' class='style6'><tr><td colspan='2'><div align='center' class='style11' id='cabecera2'>Devolución con Cargo a Paciente</div></td></tr>");
			out.print("<table width='100%' border='0' class='style6'><tr><td>");
			
			String codp="";
			rs2=ms.ConsultarPaciente(D,N);
			try {
				while(rs2.next()){
					codp=rs2.getString(4);
					out.print("<div align='center'>Paciente: "+rs2.getString(1)+" "+rs2.getString(2)+" "+rs2.getString(3)+"</div></td>");
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			//out.print("<tr>&bnsp</tr>");
			String adm="";
			rs3=ms.ConsultarAdmisiones(codp);
			try {
				while(rs3.next()){
				adm=rs3.getString(1);
				out.print("<td><div align='center'>Número de Admisión: "+rs3.getString(1)+"</div></td><td><div align='center'>  Fecha del Ingreso: "+rs3.getString(2)+"</div></td></tr>");
				}
				rs3.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			//out.print("<table width='100%' class='style6'><tr><td colspan='2'><div align='center' class='style11' id='cabecera2'>Formulaciones del Paciente</div></td></tr>");
			out.print("<table width='100%' class='style6'><tr><td colspan='6'><div align='center' class='style11' id='cabecera2'>Medicamentos dispensados al Paciente</div></td></tr>");
			
			rs4=ms.ConsultarFormulas(adm);
			try {
				while(rs4.next()){
					//out.print("<tr><td><a  href='#'onclick='medica("+rs4.getString(1)+","+codp+")'>Formula: "+rs4.getString(1)+"</a></td><td>Medico: "+rs4.getString(4)+" "+rs4.getString(5)+"</td></tr>");
				/////////////////////////////////////
					rs=ms.ObtenerMedicamentoCJ(rs4.getString(1));
					int con=0;
					try {
						while(rs.next()){
							con++;
							int dis=Integer.parseInt(rs.getString(3))-Integer.parseInt(rs.getString(4));
							
							///Buscar la cantidad devuelta si tiene
							String devol="";
							rs1=ms.ConsultarDevoluciones(rs.getString(9), rs.getString(14));
							try {
								while(rs1.next()){
									devol=rs1.getString(1);
									if(devol==null){devol="";}
									System.out.println("devol: "+devol);
								}
								rs1.getStatement().getConnection().close();
							} catch (SQLException e) {
								out.print("Error "+e);
								e.printStackTrace();
							}
							
							//System.out.println(rs.getString(4)+"  -----  "+ rs.getString(17));
							if(rs.getString(4).equals(devol)){
							out.print("<tr><td><a  href='#'onclick='Dispensada()'><FONT COLOR=red>"+rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(10)+" "+rs.getString(11)+ "</FONT></a></td><td>Lote:  "+rs.getString(12)+" / Vence:"+rs.getString(17)+"</td><td>Cantidad Dispensada:   "+rs.getString(4)+"</td><td>Fecha Disp:  "+rs.getString(15)+" / "+rs.getString(16)+"</td><td>Formula:    "+rs4.getString(1)+"</a></td><td>Medico:    "+rs4.getString(4)+" "+rs4.getString(5)+"</td></tr>");
							}else{
							out.print("<tr><td><a  href='#'onclick='devolmedica("+rs.getString(6)+","+rs.getString(13)+","+rs.getString(3)+","+rs.getString(9)+","+rs4.getString(6)+","+rs.getString(14)+")'>"+rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(10)+" "+rs.getString(11)+ "</a></td><td>Lote:  "+rs.getString(12)+" / Vence: "+rs.getString(17)+"</td><td>Cantidad Dispensada:   "+rs.getString(4)+"</td><td>Fecha Disp:  "+rs.getString(15)+" / "+rs.getString(16)+"</td><td>Formula:    "+rs4.getString(1)+"</a></td><td>Medico:    "+rs4.getString(4)+" "+rs4.getString(5)+"</td></tr>");
							}
							//out.print(" <tr><td><a  href='#'onclick='devolmed(&quot;"+rs.getString(6)+"&quot;");
							
						//	out.print(" <tr><td><a  href='#'onclick='devolmed("+rs.getString(6)+","+rs.getString(3)+","+rs.getString(9)+","+codpa+")'>"+rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(10)+" "+rs.getString(11)+"</a></td><td>Formula:    "+rs4.getString(1)+"</a></td><td>Medico:    "+rs4.getString(4)+" "+rs4.getString(5)+"</td>"); 
					}
						rs.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}	
					////////////////////////////////////////
					out.print("<tr BGCOLOR='#D3D3D3'></tr>");
				}
				rs4.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("<tr><td colspan='6'><div id='Medicas'></div></td></tr>");				
		}	*/
		
		
	/*	if(va.equals("Mostrarmed")){
			out.print("<table width='100%' class='style6'><tr><td colspan='2'><div align='center' class='style11' id='cabecera2'>Medicamentos en la Formula</div></td></tr>");
			int sw=0;
			out.println("<tr><td>Seleccione el Lote del medicamento	: </td></tr>");
			rs2=ms.ObtenerMedicamentosD(cdf);
			try {
				while(rs2.next()){
					out.print(" <tr><td><a  href='#'onclick='devolmedica("+cdf+","+rs2.getString(1)+","+cfo+","+cdisp+","+codpa+","+rs2.getString(8)+")'>"+rs2.getString(3)+" "+rs2.getString(4)+" "+rs2.getString(6)+" "+rs2.getString(7)+"   Lote:  "+rs2.getString(5)+" Cantidad Dispensada:  "+rs2.getString(2)+"</a></td>"); 
					sw=1;
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			if(sw==0){out.println("<tr></tr><tr><td>No se dispensó ninguna unidad del medicamento!!! </td></tr>");}
		}*/
		
		
		if(va.equals("Mostrarmed2")){
			//out.print("<table width='100%' border='0' class='style6'><tr><td>");
			out.print("<table width='100%' class='style6'><tr><td colspan='6'><div align='center' class='style11' id='cabecera2'>Devolución de Medicamentos</div></td></tr>");
			//out.print("<table width='100%' border='1' class='style6'><tr><td width='38%'><div align='center'></div></td><td width='26%'><div align='center'></div></td><td width='7%'><div align='center'></div></td><td width='7%'><div align='center'></div></td><td width='7%'><div align='center'></div></td><td width='8%'><div align='center'></div></td><td width='7%'><div align='center'></div></td></tr>");
			rs1=ms.ObtenerMedicamentosD2(codmovdis);
			String dispen=""; 
			try {
				if(rs1.next()){
					//devolmed(cdf,cfo,cdisp);
					out.print("<td><div align='center' class='style6' ><a  href='#'onclick='devolmed("+cdf+","+cfo+","+cdisp+","+codpa+")'>"+rs1.getString(3)+" "+rs1.getString(4)+" "+rs1.getString(8)+" "+rs1.getString(9)+  "</a>     Lote: "+rs1.getString(5)+"</div></td>");
					dispen=rs1.getString(2);
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
					
			out.print("<table width='100%' border='1' class='style6'><tr BGCOLOR='#BDBDBD'><td width='14%'><div align='center'>Formuladas</div></td><td width='14%'><div align='center'>Dispensadas</div></td><td width='14%'><div align='center'>Devueltas</div></td><td width='14%'><div align='center'>Devolver</div></td><td width='30%'><div align='center'>Causa de devolución</div></td><td width='14%'><div align='center'>Acción</div></td></tr>");
			//out.print("<table width='100%' border='1' class='style6'><tr><td width='38%'><div align='center'></div></td><td width='26%'><div align='center'></div></td><td width='7%'><div align='center'></div></td><td width='7%'><div align='center'></div></td><td width='7%'><div align='center'></div></td><td width='8%'><div align='center'></div></td><td width='7%'><div align='center'></div></td></tr>");
			out.print("<td><div align='center'>"+cfo+"</div></td>"); 	
			out.print("<td><div align='center'>"+dispen+"</div></td>"); 	
         	//out.print("</select></label></td>");
         		//	int NN=Integer.parseInt(N);
         			int con=0;
         		//	int devo[]= new int[500];
         			rs2=ms.ObtenerDevoluciones(cdisp, inv, codmovdis);
					int dev=0;
					String devinv="0";
					try {
						while(rs2.next()){
							con++;
							dev=dev+rs2.getInt(1);
							System.out.print("esto es dev: "+rs2.getString(1));
					//		devo[con]=rs2.getInt(1);
							devinv=rs2.getString(2);
						}
						rs2.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}	
					//out.print("<td><div align='center'>"+devo[NN]+"</div></td>");
					out.print("<td><div align='center'>"+dev+"</div></td>");
					if(Integer.parseInt(dispen)==dev){
					out.print("<td><label><input name='txtDevolver' type='text' id='txtDevolver' readonly='' value='0' size='20' /></label></td>");	
					}else{
					out.print("<td><label><input name='txtDevolver' type='text' id='txtDevolver' size='20' onkeyup='checknumsdevo()'/></label></td>");
					}
					out.print("<td><select  style='width:100%' id='con'><option value='Seleccione'>Seleccione</option>");	
					out.print("<option value='ALERGIA MEDICAMENTOSA'>ALERGIA MEDICAMENTOSA</option>");
					out.print("<option value='ALTA MEDICA'>ALTA MEDICA</option>");
					out.print("<option value='MODIFICACION DE DOSIS'>MODIFICACION DE DOSIS</option>");
					out.print("<option value='CAMBIO VIA DE ADMINISTRACION'>CAMBIO VIA DE ADMINISTRACION</option>");
					out.print("<option value='DOSIS INCORRECTA'>DOSIS INCORRECTA</option>");
					out.print("<option value='INTERACCION'>INTERACCION</option>");
					out.print("<option value='INTERVENCION QUIRURGICA'>INTERVENCION QUIRURGICA</option>");
					out.print("<option value='MEDICAMENTO INCORRECTO'>MEDICAMENTO INCORRECTO</option>");
					out.print("<option value='MEDICAMENTO OMITIDO'>MEDICAMENTO OMITIDO</option>");
					out.print("<option value='MODIFICACION DEL TRATAMIENTO'>MODIFICACION DEL TRATAMIENTO</option>");
					out.print("<option value='MUERTE'>MUERTE</option>");
					out.print("<option value='NAUSEAS'>NAUSEAS</option>");
					out.print("<option value='PACIENTE EN AYUNA'>PACIENTE EN AYUNA</option>");
					out.print("<option value='PACIENTE REALIZANDO PRUEBAS'>PACIENTE REALIZANDO PRUEBAS</option>");
					out.print("<option value='REACCION ADVERSA A MEDICAMENTO'>REACCION ADVERSA A MEDICAMENTO</option>");
					out.print("<option value='TRASLADO A OTRA ENTIDAD'>TRASLADO A OTRA ENTIDAD</option>");
					out.print("<option value='VIA INCORRECTA'>VIA INCORRECTA</option>");
					out.print("<option value='VOMITO'>VOMITO</option></td>");
					
					out.print("<td><div align='center'><a  href='#'onclick='devol("+cdisp+","+inv+","+dev+","+dispen+","+cdf+","+cfo+","+codpa+","+codmovdis+")'>Devolver</a></div></td>");
		}
		

		
		
		

		
		
		
		
		
		
		
		
		

		
		if(va.equals("Devototal")){
			
			Calendar c = new GregorianCalendar();
			String dia = Integer.toString(c.get(Calendar.DATE));
			String mes = Integer.toString(c.get(Calendar.MONTH)+1);
			String annio = Integer.toString(c.get(Calendar.YEAR));
			String fec=annio+"-"+mes+"-"+dia;
			
			System.out.println("La fechhaaa: "+fec);
			
			Calendar calendario = Calendar.getInstance();
			int hora, minutos, segundos;
			hora =calendario.get(Calendar.HOUR_OF_DAY);
			minutos = calendario.get(Calendar.MINUTE);
			segundos = calendario.get(Calendar.SECOND);
			String hra= hora+":"+minutos+":"+segundos;
			 
			
			
			rs2=ms.CantidadEnInventario(invd);
				String CantIni="";
				try {
					if(rs2.next()){
						CantIni=rs2.getString(1);//esta variable es el cod del movimiento
					}
					rs2.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				
			
				
				int cantdef=Integer.parseInt(CantIni)+Integer.parseInt(cantd);
				String cantdefs = String.valueOf(cantdef);
				
						
		 ms.Devolucion(invd, cantdefs);
		 ms.LlenarDevolucion(cdisp,cantd,usu,fec,hra,invd,concep,codpa,codmovdis); 
		 rs1=ms.ObtenerUDevolucion(fec,hra);
			String devo="";
			try {
				while(rs1.next()){
				devo=rs1.getString(1);//esta variable es el cod del movimiento
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			out.print(devo);
			
			
	/****RESTAMOS EL COSTO DE LA DISPENSACION*****/
		//	System.out.println("XXX: "+codmovdis);
			rs1=ms.ObtenerDevolucionesalCosto(codmovdis);
			String invc="";
			String disc="";
			String medc="";
			String admc="";
			String devc="";
			String lotc="";
			int cantacons=0;
			try {
				if(rs1.next()){
					invc=rs1.getString(1);
					disc=rs1.getString(2);
					medc=rs1.getString(3);
					admc=rs1.getString(4);
					devc=rs1.getString(5);
					lotc=rs1.getString(6);
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			if((devc==null)||(devc.equals(null))){devc="0";}
			
			cantacons=Integer.parseInt(disc)-Integer.parseInt(devc);
			
			//cantidad dispensada
			
			rs1=ms.ObtenerCostosdeDispensacion(admc,invc,medc,lotc,String.valueOf(cantacons));
			String codigon="";
			String canta="";
			String cunit="";
			try {
				if(rs1.next()){
					codigon=rs1.getString(1);
					canta=rs1.getString(2);
					cunit=rs1.getString(3);
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			int ctn=Integer.parseInt(canta)-Integer.parseInt(cantd);
			double cttn=Double.parseDouble(cunit)*ctn;
			ms.ActualizarCostos(String.valueOf(ctn),String.valueOf(cttn),codigon);
			
			/****FIN RESTAMOS EL COSTO DE LA DISPENSACION*****/
			

			///////////////////////////INSERTAMOS O ACTUALIZAMOS LA DISPENSACION DEL PERFIL FARMACOTERAPEUTICO//////////////////////////
			rs2=ms.ConsultarDetalleFormulacion(cdisp);
			String cdeta="";
			try {
				if(rs2.next()){
					cdeta=rs2.getString(1);
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			rs2=ms.ConsultarDetallePerfil(cdeta);
			String cdp="";
			try {
				if(rs2.next()){
					cdp=rs2.getString(1);
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			rs2=ms.ConsultarDispensacionPerfil(cdp);
			String cdd="";
			try {
				if(rs2.next()){
					cdd=rs2.getString(1);
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			ms.ActualizarDispensacionPerfilD(cantd,fec,hra,usu,cdp);
		
			/////////////////////////FINNNN    INSERTAMOS O ACTUALIZAMOS LA DISPENSACION DEL PERFIL FARMACOTERAPEUTICO//////////////////////////
			
			
			
			
			
			
			/********SE ELIMINA AUTOMATICAMENTE EL CARGUE DE FACTURACION*******/
			
			rs2=ms.ObtenerEncabezado(codmovdis);
			String encaf="";
			String fecf="";
			String hraf="";
			try {
				if(rs2.next()){
					fecf=rs2.getString(1);
					hraf=rs2.getString(2);
					encaf=rs2.getString(3);
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			 rs1=ms.ObtenerCantidadCargado(encaf,fecf,hraf);
				String cafc="";
				try {
					if(rs1.next()){
						cafc=rs1.getString(1);//
					}
					rs1.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
			
				int cafcn=Integer.parseInt(cafc)-Integer.parseInt(cantd);
				cafc=String.valueOf(cafcn); 
				
				/*if(cafcn<1){
					ms.EliminaCargue(encaf,fecf,hraf);
				}else{
					ms.ActualizaCargue(encaf,fecf,hraf,cafc);
				}*/
				
			//la cantidad a devolver   cantd
		//	+"&codmovdis="+codmovdis
			
			
			
		  
		}	
						
		//////////////
		
		
	}

}

/*
 
CAUSAS DE DEVOLUCION DE MEDICAMENTOS

ALTA MEDICA
DOSIS INCORRECTA
MEDICAMENTO INCORRECTO
PACIENTE EN AYUNA
ALERGIA MEDICAMENTOSA
INTERACCION
MEDICAMENTO OMITIDO
REACCION ADVERSA A MEDICAMENTO
CAMBIO DE DOSIS
INTERVENCION QUIRURGICA
NAUSEAS
VOMITO
CAMBIO VIA DE ADMINISTRACION
MUERTE
PACIENTE REALIZANDO PRUEBAS
VIA INCORRECTA

*/





