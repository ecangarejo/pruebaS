package fact_controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.*;
import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fact_bean.Tarifas;
import fact_metodo.MetodoCrearTarifas;

public class ControlCrearTarifas extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String va = req.getParameter("valor");
		String texto=req.getParameter("texto");
		String xx=req.getParameter("xx");
		ResultSet rs=null;
		res.setContentType("text/html;charset=UTF-8");
		MetodoCrearTarifas mct = new MetodoCrearTarifas();
		
		if(va.equals("autoinv")){
			
			try {
				rs =mct.listarArticulos(texto,xx);
				String cadena ="";
				cadena="[";
				while(rs.next()){
					cadena = cadena+"'"+rs.getString(2)+"|"+rs.getString(1)+"|"+rs.getString(3)+"|"+rs.getString(4)+"|"+rs.getString(5)+"|"+rs.getString(6)+"'";
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
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		HttpSession session = req.getSession(true);
		
		String va = req.getParameter("valor");
		String NombreManual=req.getParameter("NombreManual");
		String cod_ManTarFk=req.getParameter("cod_ManTarFk");
		String NombreTipoTarifa=req.getParameter("NombreTipoTarifa");
		String ClaseServicio=req.getParameter("ClaseServicio");
		String NombreCentroCosto=req.getParameter("NombreCentroCosto");
		String Abreviado=req.getParameter("Abreviado");
		String nivel=req.getParameter("nivel");
		String texto=req.getParameter("texto");
		String xx=req.getParameter("xx");
		String mbase0p=req.getParameter("mbase0p");
		String mtarp=req.getParameter("mtarp");
		String desch0p=req.getParameter("desch0p");
		String critp=req.getParameter("critp");
		if(mbase0p==null){mbase0p="";}
		if(mtarp==null){mtarp="";}
		if(mtarp.equals("Seleccione")){mtarp="";}
		if(desch0p==null){desch0p="";}
		if(critp==null){critp="";}
		
		String mbasec=req.getParameter("mbasec");
		String descch0=req.getParameter("descch0");
		String fechai0=req.getParameter("fechai0");
		String fechaf0=req.getParameter("fechaf0");
		String valor0=req.getParameter("valor0");
		String ct=req.getParameter("ct");
		String y=req.getParameter("y");
		String v=req.getParameter("v");
		
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out=res.getWriter();
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		ResultSet rs3=null;
		MetodoCrearTarifas mct = new MetodoCrearTarifas();
		
		if(va.equals("0")){
			out.print("<div align='center' class='style11' id='cabecera2'>Seleccione Una Opcion </div></td></tr>");
			out.print("<div>");
			out.print("<label class='floatLeft' style='width:33%;text-align:center;'><input name='radiobutton' type='radio' value='radiobutton' id='1' onclick='Radios()' />Consultar/Crear Tarifas</label></td>");
			out.print("<label class='floatLeft' style='width:33%;text-align:center;'><input name='radiobutton' type='radio' value='radiobutton' id='6' onclick='Radios()' />Incremento Automatico de Tarifas</label>");
			out.print("<label class='floatLeft' style='width:33%;text-align:center;'><input name='radiobutton' type='radio' value='radiobutton' id='6d' onclick='Radios()' />Decremento Automatico de Tarifas</label>");
			out.print("</div>");
			out.print("<div class='separator'></div>");
		}

		
		if(va.equals("1")){
			out.print("<div align='center' class='style11' id='cabecera2'><a title='Muestra los programas tarifados pertenecientes\nal manual tarifario'>Consultar tarifa</a></div>");
			out.print("<div class='separator'></div>");
			out.print("<fieldset class='fieldsetForm centerOp1000px' >");
			out.print("<legend class='legendForm'>CRITERIOS</legend>");
			out.print("<div class='divForm'>");
			out.print("<label class='etiquetas'>Manual Tarifario:</label>");
			out.print("<select id='manualT' ><option value='0'>Seleccione</option>");
			rs1=mct.BuscarManualTarifario();
			try {
				while(rs1.next()){
				out.print("<option value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("</select>");
			out.print("<label style='width:245px;text-align:right;'>Codigo o Descripcion programa:</label>");
			out.print("<input type='text' class='widthField' id='descrPT' />");
			out.print("<input id='Buscar' type='button' value='Buscar' onClick='cargarProgramasManualTarifario()'>");
			out.print("</div>");
			out.print("</fieldset>");
			out.print("<div class='separator7'></div>");
			out.print("<div id='DivPRT' class='centerOp1000px' style='height:500px;overflow-y:scroll;'></div>");//programas asignados al manual tarifario
		}

		
		
		
		if(va.equals("2")){
			Vector l = new Vector();
			
			String sql=" select t.cod_tarifa, t.manual_tarifario, mt.descripcion, t.programa, p.descripcion, t.fecha_ini, t.fecha_fin, t.valor from fact_tarifas t, fact_manuales_tarifarios mt, fact_programas p where  t.manual_tarifario=mt.cod_manual_tarifario and t.programa=p.cod_programa ";
			if((!mtarp.equals("Seleccione"))&&(!mtarp.equals(""))){
				sql=sql+" and t.manual_tarifario='"+mtarp+"'";
			}
			if(!desch0p.equals("")){
				sql=sql+" and t.programa='"+desch0p+"'";
			}
			if(!critp.equals("")){
				sql=sql+" and p.descripcion like '%"+critp+"%'";
			}
				
			sql=sql+" order by p.descripcion ";
			
			String dias="";
			String meses="";
			String anos="";
			String dias2="";
			String meses2="";
			String anos2="";
			int con=0;
			
			String fechai="";
			String fechaf="";
						
			rs =mct.ObtenerProgramas(sql);
			try {
				while(rs.next()){
					con++;
					Tarifas c = new Tarifas();
				
					anos=rs.getString(6).substring(0, 4);
					meses=rs.getString(6).substring(5, 7);
					dias=rs.getString(6).substring(8, 10);
					fechai=dias+"/"+meses+"/"+anos;
					
					anos2=rs.getString(7).substring(0, 4);
					meses2=rs.getString(7).substring(5, 7);
					dias2=rs.getString(7).substring(8, 10);
					fechaf=dias2+"/"+meses2+"/"+anos2;
					
					c.setTarifa(rs.getString(1));
					c.setManual(rs.getString(2));
					c.setManualv(rs.getString(3));
					c.setPrograma(rs.getString(5));
					c.setProgramac(rs.getString(4));
					c.setFechaI(fechai);
					c.setFechaF(fechaf);
					c.setValor(rs.getString(8));
					l.add(c);
					
					
					out.print(rs.getString(1)+"|");
					out.print(rs.getString(2)+"|");
					out.print(rs.getString(3)+"|");
					out.print(rs.getString(5)+"|");
					out.print(rs.getString(4)+"|");
					out.print(fechai+"|");
					out.print(fechaf+"|");
					out.print(rs.getString(8)+"|");
				}
				
				rs.getStatement().getConnection().close();	
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
		}
		
		
		if(va.equals("3")){
			String d=fechai0.substring(0, 2);
			String m=fechai0.substring(3, 5);
			String a=fechai0.substring(6, 10);
			String f=a+"-"+m+"-"+d;
			String df=fechaf0.substring(0, 2);
			String mf=fechaf0.substring(3, 5);
			String af=fechaf0.substring(6, 10);
			String ff=af+"-"+mf+"-"+df;
			//validar q no se pueda ingresar 2 programas a un mismo manual tarifario
			
			int sw=0;
			rs1=mct.ObtenerTarifaR(mbasec, descch0);
			try {
			if(rs1.next()){
			sw=1;
			}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			if(sw==0){
			mct.CrearTarifa(mbasec, descch0, f, ff, valor0);
			out.print("Ingreso exitoso!!!");
			}else{
			out.print("Ya existe una tarifa para este Programa!!!");	
			}
		}
		
		
		/////////////
		
	if(va.equals("4")){
			
			String sql="";
						
			if(y.equals("1")){
				sql="update fact_tarifas set manual_tarifario='"+mbasec+"' where cod_tarifa='"+ct+"'";
			}
			if(y.equals("2")){
				sql="update fact_programas set nivel_complejidad='"+valor0+"' where cod_programa='"+v+"'";
			}
			if(y.equals("3")){
				String d=fechai0.substring(0, 2);
				String m=fechai0.substring(3, 5);
				String a=fechai0.substring(6, 10);
				String f=a+"-"+m+"-"+d;
				sql="update fact_tarifas set fecha_ini='"+f+"' where cod_tarifa='"+ct+"'";
			}
			if(y.equals("4")){
				String df=fechaf0.substring(0, 2);
				String mf=fechaf0.substring(3, 5);
				String af=fechaf0.substring(6, 10);
				String ff=af+"-"+mf+"-"+df;
				sql="update fact_tarifas set fecha_fin='"+ff+"' where cod_tarifa='"+ct+"'";
			}
			if(y.equals("5")){
				sql="update fact_tarifas set valor='"+valor0+"' where cod_tarifa='"+ct+"'";
			}
			
			/////////////////////
			
			int sw=0;
			rs1=mct.ObtenerTarifaR(mbasec, descch0);
			try {
			if(rs1.next()){
			sw=1;
			}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			if(sw==0){
			mct.ModificarTExistente(sql);
			}else{
			out.print("Este programa ya esta tarifado en este manual!!!");	
			}
		}
		
		
	if(va.equals("5")){
			System.out.println(ct);
			mct.EliminarTarifa(ct);
		}

	
	if(va.equals("6")){
		out.print("<table width='100%' class='style6'><tr><td colspan='5'><div align='center' class='style11' id='cabecera2'>Generar Nueva Tarifa </div></td></tr>");
		out.print("<table width='100%' class='style6' border='0' ><tr><td  width='10%'>Manual Tarifario: </td><td  width='20%'><label><select  style='width:220px' name='mtarn' id='mtarn' ><option value='Seleccione'>Seleccione</option>");
		rs1=mct.BuscarManualTarifario();
		try {
			while(rs1.next()){
			out.print("<option value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
			}
			rs1.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		out.print("</td></select>");
			
		out.print("<td width='8%'>Fecha Final:</td><td width='8%'><input type=text id='fechai0' size='7%'  onKeyup='masca(this,patron,true,0,0,0)'></td>"); 
		out.print("<td width='8%'>Fecha Inicial:</td><td width='8%'><input type=text id='fechaf0' size='7%'  onKeyup='masca(this,patron,true,0,0,0)'></td>"); 
		out.print("<td width='8%'>Incremento:</td><td width='5%'><input type=text id='valor0' size='2%' onkeyup='checknumt(0)' >%</td>");
		out.print("</table>");
	
		//////////////////////////////////////////Criterios//////////////////////////////////////
		
		out.print("<table width='100%' class='style6'><tr><td colspan='5'><div align='center' class='style11' id='cabecera2'>Criterios Base</div></td></tr>");
		
		out.print("<table width='100%' class='style6' border='0' ><tr><td  width='10%'>Manual Tarifario: </td><td  width='20%'><label><select  style='width:220px' name='mtara' id='mtara' ><option value='Seleccione'>Seleccione</option>");
		rs1=mct.BuscarManualTarifario();
		try {
			while(rs1.next()){
			out.print("<option value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
			}
			rs1.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		out.print("</select>");
		
		out.print("<td width='8%'>Factor de Redondeo:</td><td width='5%'><label><select   name='factor' id='factor' ><option value='Seleccione'>Seleccione</option><option value='100'>100</option><option value='10'>10</option><option value='1'>1</option>");
		
		out.print("</table>");
		out.print("<table width='100%' class='style6' border='0' ><tr>");
		out.print("<td colspan='4'  width='10%'><div align='center'><input type='button' name='btnConsultarPq' id='btnConsultarPq' value='Generar' onClick='GenerarT()'></div></td></tr></table>");
		out.print("<table width='100%' class='style6' border='0' >");
		out.print("<tr><td colspan='5'><div id='creacion'></div></td></tr></table>");
	}
	
	
	if(va.equals("6d")){
		
		out.print("<table width='100%' class='style6'><tr><td colspan='5'><div align='center' class='style11' id='cabecera2'>Generar Nueva Tarifa </div></td></tr>");
				
		out.print("<table width='100%' class='style6' border='0' ><tr><td  width='10%'>Manual Tarifario: </td><td  width='20%'><label><select  style='width:220px' name='mtarn' id='mtarn' ><option value='Seleccione'>Seleccione</option>");
		rs1=mct.BuscarManualTarifario();
		try {
			while(rs1.next()){
			out.print("<option value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
			}
			rs1.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		out.print("</td></select>");
			
		out.print("<td width='8%'>Fecha Final:</td><td width='8%'><input type=text id='fechai0' size='7%'  onKeyup='masca(this,patron,true,0,0,0)'></td>"); 
		out.print("<td width='8%'>Fecha Inicial:</td><td width='8%'><input type=text id='fechaf0' size='7%'  onKeyup='masca(this,patron,true,0,0,0)'></td>"); 
		out.print("<td width='8%'>Decremento:</td><td width='5%'><input type=text id='valor0' size='2%' onkeyup='checknumt(0)' >%</td>");
		out.print("</table>");
	
		//////////////////////////////////////////Criterios//////////////////////////////////////
		
		out.print("<table width='100%' class='style6'><tr><td colspan='5'><div align='center' class='style11' id='cabecera2'>Criterios Base</div></td></tr>");
		
		out.print("<table width='100%' class='style6' border='0' ><tr><td  width='10%'>Manual Tarifario: </td><td  width='20%'><label><select  style='width:220px' name='mtara' id='mtara' ><option value='Seleccione'>Seleccione</option>");
		rs1=mct.BuscarManualTarifario();
		try {
			while(rs1.next()){
			out.print("<option value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
			}
			rs1.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		out.print("</select>");
		
		out.print("<td width='8%'>Factor de Redondeo:</td><td width='5%'><label><select   name='factor' id='factor' ><option value='Seleccione'>Seleccione</option><option value='100'>100</option><option value='10'>10</option><option value='1'>1</option>");
		
		out.print("</table>");
		out.print("<table width='100%' class='style6' border='0' ><tr>");
		out.print("<td colspan='4'  width='10%'><div align='center'><input type='button' name='btnConsultarPq' id='btnConsultarPq' value='Generar' onClick='GenerarTd()'></div></td></tr></table>");
		out.print("<table width='100%' class='style6' border='0' >");
		out.print("<tr><td colspan='5'><div id='creacion'></div></td></tr></table>");
	}
	
	if(va.equals("7")){
		mct.GenerarTarifas(mtarp, fechai0, fechaf0, valor0, descch0, mbasec);
		out.print("El proceso se ha generado satisfactoriamente!!!");
	}
	
	if(va.equals("7d")){
		mct.GenerarTarifasd(mtarp, fechai0, fechaf0, valor0, descch0, mbasec);
		out.print("El proceso se ha generado satisfactoriamente!!!");
	}
	
       ///////////////////////
		if(va.equals("autoinv")){
			
			try {
				rs =mct.listarArticulos(texto,xx);
				String cadena ="";
				cadena="[";
				while(rs.next()){
					cadena = cadena+"'"+rs.getString(2)+"|"+rs.getString(1)+"|"+rs.getString(3)+"|"+rs.getString(4)+"|"+rs.getString(5)+"|"+rs.getString(6)+"'";
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
		
		
		if(va.equals("autoinv2")){
			
			try {
				rs =mct.listarArticulos2(texto);
				String cadena ="";
				cadena="[";
				while(rs.next()){
					cadena = cadena+"'"+rs.getString(2)+"|"+rs.getString(1)+"|"+rs.getString(3)+"|"+rs.getString(4)+"|"+rs.getString(5)+"|"+rs.getString(6)+"'";
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
		
		if(va.equals("criterios")){
			String mTarifa=req.getParameter("mTarifa");
			String especialidad = "0>Seleccione<";
			try {
				rs =mct.obtenerEspecialidad(mTarifa);
				while(rs.next()){
					especialidad += rs.getString(1)+">"+rs.getString(2)+"<";
				}
				rs.getStatement().getConnection().close();
			} catch (Exception e) {
				e.getMessage();
				e.printStackTrace();
			}
			
			String nivelC = "0>Seleccione<";
			try {
				rs =mct.obtenerNivelC(mTarifa);
				while(rs.next()){
					nivelC += rs.getString(1)+">"+rs.getString(2)+"<";
				}
				rs.getStatement().getConnection().close();
			} catch (Exception e) {
				e.getMessage();
				e.printStackTrace();
			}
			
			String claseC = "0>Seleccione<";
			try {
				rs =mct.obtenerClaseServicio(mTarifa);
				while(rs.next()){
					claseC += rs.getString(1)+">"+rs.getString(2)+"<";
				}
				rs.getStatement().getConnection().close();
			} catch (Exception e) {
				e.getMessage();
				e.printStackTrace();
			}
			
			String subcentroC = "0>Seleccione<";
			try {
				rs =mct.obtenerSubcentroCosto(mTarifa);
				while(rs.next()){
					subcentroC += rs.getString(1)+">"+rs.getString(2)+"<";
				}
				rs.getStatement().getConnection().close();
			} catch (Exception e) {
				e.getMessage();
				e.printStackTrace();
			}
			out.print(nivelC+":"+especialidad+":"+claseC+":"+subcentroC);
	}
		
		
		if(va.equals("progManualTarifario")){
			String mtarifario=req.getParameter("mtarifario");
			String descripcion=req.getParameter("desc");
			out.print("<table width='100%' border='1px' id='tablaProgManualTarifario'>");
			out.print("<tr bgcolor='#d3d3d3'>");
			out.print("<td align='center' width='7%'>Cod. Ref.</td>");
			out.print("<td align='center' width='65%'>Descripcion</td>");
			out.print("<td align='center' width='8%'>Fecha Inicial</td>");
			out.print("<td align='center' width='7%'>Fecha Final</td>");
			out.print("<td align='center' width='7%'>Valor</td>");
			out.print("<td align='center' width='6%'></td>");
			out.print("</tr>");
			try {
				int linea = 0;
					rs =mct.obtenerProgManualTarifario(mtarifario,descripcion);
					
					while(rs.next()){
					out.print("<tr>");
					out.print("<td align='center' >"+rs.getString(1)+"</td>");
					out.print("<td >"+rs.getString(2)+"<input id='codT"+linea+"' type='hidden' value='"+rs.getString(6)+"'/></td>");
					String[] fechaI = rs.getString(3).split("-");
					String[] fechaF = rs.getString(4).split("-");
					out.print("<td ><input type='text' style='width: 100%;border: 0' disabled='disabled' value='"+fechaI[2]+"/"+fechaI[1]+"/"+fechaI[0]+"'></td>");
					out.print("<td ><input type='text' style='width: 100%;border: 0' disabled='disabled' value='"+fechaF[2]+"/"+fechaF[1]+"/"+fechaF[0]+"'/></td>");
					out.print("<td align='right'><input type='text' style='width: 100%;border: 0;text-align:right;' disabled='disabled' value='"+mct.formatMoneda(rs.getString(5))+"'/></td>");
					out.print("<td align='center'><a  href='#' onclick='EliminarTarifa("+linea+",this)'>Eliminar</a></td>");
					out.print("</tr>");
					
					linea++;
					}
					rs.getStatement().getConnection().close();
				
				rs1 =mct.obtenerProgManualBase(mtarifario,descripcion);
				while(rs1.next()){
					out.print("<tr>");
					out.print("<td align='center' id='ref"+linea+"'>"+rs1.getString(1)+"</td>");
					out.print("<td id='desc"+linea+"'>"+rs1.getString(2)+"<input id='prog"+linea+"' type='hidden' value='"+rs1.getString(3)+"'/></td>");
					out.print("<td ><input id='FI"+linea+"' type='text' style='width: 100%;border: 0' onKeyup='masca(this,patron,true,0,0,0)'/></td>");
					out.print("<td ><input id='FF"+linea+"' type='text' style='width: 100%;border: 0' onKeyup='masca(this,patron,true,0,0,0)'/></td>");
					out.print("<td ><input id='V"+linea+"' type='text' style='width: 100%;border: 0'/></td>");
					out.print("<td align='center'><a  href='#' onclick='asignarTarifa("+linea+",this)'>Asignar</a></td>");
					out.print("</tr>");
					linea++;
				}
				rs1.getStatement().getConnection().close();
			} catch (Exception e) {
				e.getMessage();
				e.printStackTrace();
			}
			
			out.print("</table>");
		}
		
		if(va.equals("crearTarifa")){
			String FechaIni=req.getParameter("FechaIni");
			String FechaFin=req.getParameter("FechaFin");
			String Valor=req.getParameter("Valor");
			String manualT=req.getParameter("manualT");
			String codProg=req.getParameter("codProg");
			
			String[] detFechaIni = FechaIni.split("/");
			String[] detFechaFin = FechaFin.split("/");
			mct.CrearTarifa(manualT, codProg, detFechaIni[2]+"-"+detFechaIni[1]+"-"+detFechaIni[0], detFechaFin[2]+"-"+detFechaFin[1]+"-"+detFechaFin[0], Valor);
			
			try {
				rs =mct.obtenerCodUltTarif();
				while(rs.next()){
					out.print(rs.getString(1));
				}
				rs.getStatement().getConnection().close();
			} catch (Exception e) {
				e.getMessage();
				e.printStackTrace();
			}
			
			
		}
		
	}

}
