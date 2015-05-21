package cont_controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cont_metodo.MetodoNomina;

/**
 * Servlet implementation class ControlNomina
 */
public class ControlNomina extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		
		String va = request.getParameter("valor");
		String usu = request.getParameter("usu");
		String texto = request.getParameter("texto");
		String nom = request.getParameter("nom");
		String sig = request.getParameter("sig");
		String tc = request.getParameter("tc");
		String dod = request.getParameter("dod");
		String code = request.getParameter("code");
		String dbn1 = request.getParameter("dbn1");
		String dbn2 = request.getParameter("dbn2");
		String dbn3 = request.getParameter("dbn3");
		String dbn4 = request.getParameter("dbn4");
		String dbn5 = request.getParameter("dbn5");
		String dbn6 = request.getParameter("dbn6");
		String dbn7 = request.getParameter("dbn7");
		String dbn8 = request.getParameter("dbn8");
		String dbn9 = request.getParameter("dbn9");
		String dbn10 = request.getParameter("dbn10");
		String dbn11 = request.getParameter("dbn11");
		String dbn12 = request.getParameter("dbn12");
		String dbn13 = request.getParameter("dbn13");
		String dbn14 = request.getParameter("dbn14");
		String dbn15 = request.getParameter("dbn15");
		String dbn16 = request.getParameter("dbn16");
		String dbn17 = request.getParameter("dbn17");
		String dbn18 = request.getParameter("dbn18");
		String dbn19 = request.getParameter("dbn19");
		String dbn20 = request.getParameter("dbn20");
		String dbn21 = request.getParameter("dbn21");
		String dbn22 = request.getParameter("dbn22");
		String dbn23 = request.getParameter("dbn23");
		String dbn24 = request.getParameter("dbn24");
		String dbn25 = request.getParameter("dbn25");
		String dbn26 = request.getParameter("dbn26");
		String dbn27 = request.getParameter("dbn27");
		String dbn28 = request.getParameter("dbn28");
		String dbn29 = request.getParameter("dbn29");
		String dbn30 = request.getParameter("dbn30");
		String dbn31 = request.getParameter("dbn31");
		String dbn32 = request.getParameter("dbn32");
		String dbn33 = request.getParameter("dbn33");
		String dbn34 = request.getParameter("dbn34");
		String dbn35 = request.getParameter("dbn35");
		String dbn36 = request.getParameter("dbn36");
		String dbn37 = request.getParameter("dbn37");
		String dbn38 = request.getParameter("dbn38");
		String dbn39 = request.getParameter("dbn39");
		String indice = request.getParameter("indice");
		String disp = request.getParameter("disp");
		String tdn = request.getParameter("tdn");
		String ndn = request.getParameter("ndn");
		String pan = request.getParameter("pan");
		String pnn = request.getParameter("pnn");

		String anio = request.getParameter("anio");
		String nit = request.getParameter("nit");
		
		PrintWriter out=response.getWriter();
	
		MetodoNomina mn = new MetodoNomina();
		
		
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		
		//java.util.Date fechaS = new Date();
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
		
		Calendar calendario = Calendar.getInstance();
		//	Calendar calendario = new GregorianCalendar();
		int hora, minutos, segundos;
		hora =calendario.get(Calendar.HOUR_OF_DAY);
		minutos = calendario.get(Calendar.MINUTE);
		segundos = calendario.get(Calendar.SECOND);
		String hra= hora+":"+minutos+":"+segundos;
		
		
		if(va.equals("1")){		
			out.print("<table width='100%' class='style6'><tr><td colspan='2'><div align='center' class='style11' id='cabecera2'>Datos Basicos de Nomina</div></td></tr>");
			
			int ct=0;
			rs = mn.ConsultarNomina();
			try {
				if(rs.next()){
					ct++;
					out.print("<tr><td width='22%'>Año</td><td><label><input name='anio' type='text' id='anio' size='35'  value='"+rs.getString(2)+"' onkeyup='validaannio(this,"+annio+")' /></label></td></tr>");
					out.print("<tr><td width='22%'>Salario minimo diario</td><td><label><input name='smd' type='text' id='dbn1' size='35' value='"+rs.getString(3)+"' onkeyup='checknumt(1)'/></label></td></tr>");
					out.print("<tr><td width='22%'>Auxilio de transporte diario diurno</td><td><label><input name='smd' type='text' id='dbn2' size='35' value='"+rs.getString(4)+"' onkeyup='checknumt(2)' /></label></td></tr>");
					out.print("<tr><td width='22%'>Auxilio de transporte diario nocturno</td><td><label><input name='smd' type='text' id='dbn3' size='35'  value='"+rs.getString(5)+"' onkeyup='checknumt(3)' /></label></td></tr>");
					out.print("<tr><td width='22%'>Aporte de pensión</td><td><label><input name='smd' type='text' id='dbn4' size='35' value='"+rs.getString(6)+"' onkeyup='checknumt(4)' /></label></td></tr>");
					out.print("<tr><td width='22%'>Aporte de salud</td><td><label><input name='smd' type='text' id='dbn5' size='35'  value='"+rs.getString(7)+"' onkeyup='checknumt(5)'/></label></td></tr>");
					out.print("<tr><td width='22%'>Aporte F.S.P</td><td><label><input name='smd' type='text' id='dbn6' size='35'  value='"+rs.getString(8)+"' onkeyup='checknumt(6)'/></label></td></tr>");
					out.print("<tr><td width='22%'>Aporte A.R.P</td><td><label><input name='smd' type='text' id='dbn7' size='35'  value='"+rs.getString(9)+"' onkeyup='checknumt(7)'/></label></td></tr>");
					out.print("<tr><td width='22%'>Fecha Ultima Actualización</td><td><label><input name='smd' type='text' id='fua' size='35'  readonly='readonly' value='"+rs.getString(10)+"' /></label></td></tr>");
					out.print("<tr><td width='22%'>Hora Ultima Actualización</td><td><label><input name='smd' type='text' id='hua' size='35'  readonly='readonly' value='"+rs.getString(11)+"' /></label></td></tr>");
					
					rs1 = mn.ConsultarUsuario(rs.getString(12));
					if(rs1.next()){
					out.print("<tr><td width='22%'>Usuario Ultima Actualización</td><td><label><input name='smd' type='text' id='uua' size='35'  value='"+rs1.getString(1)+" "+rs1.getString(2)+"'  readonly='readonly' /></label></td></tr>");
					}rs1.getStatement().getConnection().close();
					
					out.print("<tr></tr><tr></tr><tr></tr><tr><td></td><td><div align='left'><label><input type='button' name='btnCrearIva' id='btnCrearIva' value='Actualizar' onClick='actualizardbn()'></label></div></td></tr>");		
							
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			if(ct==0){
				out.print("<tr><td width='22%'>Año</td><td><label><input name='anio' type='text' id='anio' size='35' onkeyup='validaannio(this,"+annio+")'  /></label></td></tr>");
				out.print("<tr><td width='22%'>Salario minimo diario</td><td><label><input name='smd' type='text' id='dbn1' size='35'  onkeyup='checknumt(1)' /></label></td></tr>");
				out.print("<tr><td width='22%'>Auxilio de transporte diario diurno</td><td><label><input name='dbn1' type='text' id='dbn2' size='35' onkeyup='checknumt(2)'/></label></td></tr>");
				out.print("<tr><td width='22%'>Auxilio de transporte diario nocturno</td><td><label><input name='smd' type='text' id='dbn3' size='35'  onkeyup='checknumt(3)'/></label></td></tr>");
				out.print("<tr><td width='22%'>Aporte de pensión</td><td><label><input name='smd' type='text' id='dbn4' size='35' onkeyup='checknumt(4)' /></label></td></tr>");
				out.print("<tr><td width='22%'>Aporte de salud</td><td><label><input name='smd' type='text' id='dbn5' size='35' onkeyup='checknumt(5)' /></label></td></tr>");
				out.print("<tr><td width='22%'>Aporte F.S.P</td><td><label><input name='smd' type='text' id='dbn6' size='35'  onkeyup='checknumt(6)'/></label></td></tr>");
				out.print("<tr><td width='22%'>Aporte A.R.P</td><td><label><input name='smd' type='text' id='dbn7' size='35' onkeyup='checknumt(7)' /></label></td></tr>");
				
				out.print("<tr></tr><tr></tr><tr></tr><tr><td></td><td><div align='left'><label><input type='button' name='btnCrearIva' id='btnCrearIva' value='Crear' onClick='crardbn()'></label></div></td></tr>");		
				
			}
		}
		
		if(va.equals("1.1")){
		    mn.CrearDatosBasicos(anio,dbn1,dbn2,dbn3,dbn4,dbn5,dbn6,dbn7,fechacjmysql,hra,usu);
		}
		
		if(va.equals("1.2")){
			mn.ActualizarEstadoDatosBasicos();
		    mn.CrearDatosBasicos(anio,dbn1,dbn2,dbn3,dbn4,dbn5,dbn6,dbn7,fechacjmysql,hra,usu);
		}
		
		
		if(va.equals("2")){	
			out.print("<table width='100%' class='style6'><tr><td colspan='2'><div align='center' class='style11' id='cabecera2'>Datos Basicos de Nomina</div></td></tr>");
				
			out.print("<tr><td width='12%'>Descripción</td><td><label><input name='txtNombreIva' type='text' id='desc' onkeyup='this.value=this.value.toUpperCase();' size='48' /></label></td></tr>");
		
			
			out.print("<tr><td  width='12%'>Tipo de concepto: </td><td><select  style='width:235px' name='tc' id='tc' ><option value='Seleccione'>Seleccione</option>");
			rs = mn.ConsultarTiposdeConcepto();
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
			
			
			
			
			//out.print("<tr><td width='12%'>Valor</td><td><label><input name='txtValor' type='text' id='txtValor' size='48' /> %</label></td></tr>");
		//	out.print("<tr><td>Descripcion</td><td><label><textarea name='txtDescripcion' id='txtDescripcion' cols='38' rows='3'></textarea></label></td></tr>");
			out.print("<tr><td></td><td><div align='left'><label><input type='button' name='btnCrearIva' id='btnCrearIva' value='Crear' onClick='Crearc()'></label></div></td></tr>");		
			out.print("<table width='100%' class='style6'><tr><td colspan='2'><div align='center' class='style11' id='cabecera2'>Conceptos de Nomina Creados</div></td></tr>");
			out.print("<table width='100%' border='0' class='style6'><tr><td width='12%'><u><i><div align='left'>Descripcion</div></i></u></td><td width='88%'><u><i><div align='left'>Tipo</div></i></u></td></tr>");
			out.print("<table width='100%' border='0' class='style6'>");
			
			
			rs1=mn.ConsultarConceptos();
			try {
				while(rs1.next()){
					
					out.print("<tr><td width='12%'>"+rs1.getString(2)+"</td><td>"+rs1.getString(7)+"</td></tr>");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			out.print("<tr><td colspan='2'>&nbsp;</td></tr></table>");
		
		}
		
		if(va.equals("2.1")){
		    mn.CrearConceptos(nom,tc,usu,fechacjmysql,hra);
		}
		
		
		if(va.equals("3")){	
			out.print("<table width='100%' class='style6'><tr><td colspan='2'><div align='center' class='style11' id='cabecera2'>Subconceptos de Nomina</div></td></tr>");
			
			out.print("<tr><td  width='12%'>Concepto: </td><td><select  style='width:323px' name='tc' id='tc' ><option value='Seleccione'>Seleccione</option>");
			rs = mn.ConsultarConceptos();
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
			out.print("<tr><td width='12%'>Descripcion</td><td><label><input name='txtNombreIva' type='text' id='desc' onkeyup='this.value=this.value.toUpperCase();' size='48' /></label></td></tr>");
			out.print("<tr><td width='12%'>Sigla</td><td><label><input name='txtValor' type='text' id='sigla'  onkeyup='this.value=this.value.toUpperCase();' size='48' /></label></td></tr>");
			out.print("<tr><td  width='12%'>Clase </td><td><select  style='width:323px' name='dbn1' id='dbn1' ><option value='0'>Novedad</option><option value='1'>Fijo</option></select>");
			out.print("<tr><td  width='12%'>Modalidad </td><td><select  style='width:323px' name='dbn2' id='dbn2' ><option value='Seleccione'>Seleccione</option>");
			rs = mn.ConsultarModalidad();
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
			out.print("<tr><td width='12%'>% de Distrubución</td><td><label><input name='txtValor' type='text' id='dbn3' size='48' /></label></td></tr>");
			out.print("<tr><td width='12%'>Tope maximo</td><td><label><input name='txtValor' type='text' id='dbn4' size='48' /></label></td></tr>");
			out.print("<tr><td width='12%'>Orden en Planilla</td><td><label><input name='txtValor' type='text' id='dbn5' size='48' /></label></td></tr>");
			out.print("<tr><td width='12%'>Afecta prest. sociales</td><td><label><input type='checkbox' id='dbn6' onclick='vcarga()'></label></td></tr>");
			out.print("<tr><td width='12%'>Afecta vacaciones</td><td><label><input type='checkbox' id='dbn7' onclick='vcarga()'></label></td></tr>");
			out.print("<tr><td width='12%'>Afecta seguridad social</td><td><label><input type='checkbox' id='dbn8' onclick='vcarga()'></label></td></tr>");
					
			//out.print("<tr><td>Descripcion</td><td><label><textarea name='txtDescripcion' id='txtDescripcion' cols='38' rows='3'></textarea></label></td></tr>");
			out.print("<tr><td></td><td><div align='left'><label><input type='button' name='btnCrearIva' id='btnCrearIva' value='Crear' onClick='Crearsubc()'></label></div></td></tr>");		
			out.print("<table width='100%' class='style6'><tr><td colspan='2'><div align='center' class='style11' id='cabecera2'>Subconceptos creados</div></td></tr>");
			out.print("<table width='100%' border='0' class='style6'><tr><td width='20%'><u><i><div align='left'>Descripción</div></i></u></td><td width='7%'><u><i><div align='left'>Sigla</div></i></u></td><td width='17%'><u><i><div align='left'>Concepto al que pertenece</div></i></u></td><td width='7%'><u><i><div align='left'>Clase</div></i></u></td><td width='7%'><u><i><div align='left'>Modalidad</div></i></u></td><td width='7%'><u><i><div align='left'>% Distrib</div></i></u></td><td width='7%'><u><i><div align='left'>Tope</div></i></u></td><td width='7%'><u><i><div align='left'>Orden</div></i></u></td><td width='7%'><u><i><div align='left'>Afecta Prest Soc</div></i></u></td><td width='7%'><u><i><div align='left'>Afect. Vacac</div></i></u></td><td width='7%'><u><i><div align='left'>Afecta Seg Soc</div></i></u></td></tr>");
			//out.print("<table width='100%' border='0' class='style6'>");
			int i=0;
			String x="";
			String y="";
			String z="";
			String w="";
			rs1=mn.ConsultarSubconceptos();
			try {
				while(rs1.next()){
					if(rs1.getString(5).equals("0")){x="Novedad";}else{x="Fijo";}
					if(rs1.getString(10).equals("0")){y="No";}else{y="Si";}
					if(rs1.getString(11).equals("0")){z="No";}else{z="Si";}
					if(rs1.getString(12).equals("0")){w="No";}else{w="Si";}
					if((i%2)==0){
						out.print("<tr bgcolor='#E0DDDD'><td>"+rs1.getString(2)+"</td><td>"+rs1.getString(4)+"</td><td>"+rs1.getString(16)+"</td><td>"+x+"</td><td>"+rs1.getString(17)+"</td><td>"+rs1.getString(7)+"</td><td>"+rs1.getString(8)+"</td><td>"+rs1.getString(9)+"</td><td>"+y+"</td><td>"+z+"</td><td>"+w+"</td></tr>");
						}else{
						out.print("<tr><td>"+rs1.getString(2)+"</td><td>"+rs1.getString(4)+"</td><td>"+rs1.getString(16)+"</td><td>"+x+"</td><td>"+rs1.getString(17)+"</td><td>"+rs1.getString(7)+"</td><td>"+rs1.getString(8)+"</td><td>"+rs1.getString(9)+"</td><td>"+y+"</td><td>"+z+"</td><td>"+w+"</td></tr>");
						}
						i++;
					}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			out.print("<tr><td colspan='2'>&nbsp;</td></tr></table>");
			
		}
		
		
		if(va.equals("3.1")){
			    mn.CrearSubconceptos(nom,tc,sig,dbn1,dbn2,dbn3,dbn4,dbn5,dbn6,dbn7,dbn8,usu,fechacjmysql,hra);
		}
		
		
		
		
		if(va.equals("4")){	
	
			out.print("<table width='100%' class='style6'><tr><td colspan='2'><div align='center' class='style11' id='cabecera2'>Tipos de Provisiones</div></td></tr>");
			
				
			out.print("<tr><td width='12%'>Tipo de provision</td><td><label><input name='txtNombreIva' type='text' id='desc' onkeyup='this.value=this.value.toUpperCase();' size='48' /></label></td></tr>");
			out.print("<tr><td width='12%'>Aplica auxilio de transporte</td><td><label><input type='checkbox' id='sigla' onclick='vcarga()'></label></td></tr>");
			//out.print("<tr><td>Descripcion</td><td><label><textarea name='txtDescripcion' id='txtDescripcion' cols='38' rows='3'></textarea></label></td></tr>");
			out.print("<tr><td></td><td><div align='left'><label><input type='button' name='btnCrearIva' id='btnCrearIva' value='Crear' onClick='Creartp()'></label></div></td></tr>");		
			out.print("<table width='100%' class='style6'><tr><td colspan='2'><div align='center' class='style11' id='cabecera2'>Tipos de Provisiones creadas</div></td></tr>");
			out.print("<table width='100%' border='0' class='style6'><tr><td width='30%'><u><i><div align='left'>Descripción</div></i></u></td><td width='70%'><u><i><div align='left'>Aplica auxilio de transporte</div></i></u></td></tr>");
			out.print("<table width='100%' border='0' class='style6'>");
			
			int i=0;
			rs1=mn.ConsultarTiposdeProvisiones();
			try {
				while(rs1.next()){
					String x="";
					//System.out.println("i%2: "+i%2+" i/2: "+i/2);
					if(rs1.getString(3).equals("1")){x="X";}
					if((i%2)==0){
					out.print("<tr bgcolor='#E0DDDD' ><td width='30%'>"+rs1.getString(2)+"</td><td><div align='left'>"+x+"</div></td></tr>");
					}else{
					out.print("<tr><td width='30%'>"+rs1.getString(2)+"</td><td><div align='left'>"+x+"</div></td></tr>");
					}
					i++;
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			out.print("<tr><td colspan='2'>&nbsp;</td></tr></table>");
			
		}
		
		if(va.equals("4.1")){
		    mn.CrearProvisiones(nom,sig);
		}
		
		
		if(va.equals("5")){	
			
			out.print("<table width='100%' class='style6'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Fondos y/o EPS</div></td></tr>");
			
				
			out.print("<tr><td width='12%'>Nombre</td><td><label><input name='txtNombreIva' type='text' id='dbn1' onkeyup='this.value=this.value.toUpperCase();' size='48' /></label></td>");
			out.print("<td width='12%'>Identificacion</td><td><label><select  style='width:50px' name='tc' id='tc' >");
			rs1=mn.ConsultarTiposDocumentos();
			try {
				while(rs1.next()){
					out.print("<option title='"+rs1.getString(2)+"' value='"+rs1.getString(1)+"'>"+rs1.getString(3)+"</option>");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			out.print("</label>");
			out.print("<label><input name='txtNombreIva' type='text' id='nit' size='20' onchange='validanit()' /></label> - <label><input type='text' id='dv'  size='3'  readonly='readonly' /></label></td></tr>");
			out.print("<tr><td width='12%'>% Pension</td><td><label><input name='txtNombreIva' type='text' id='dbn2' onkeyup='this.value=this.value.toUpperCase();' size='48' /></label></td>");
			out.print("<td width='12%'>% Salud</td><td><label><input name='txtNombreIva' type='text' id='dbn3' onkeyup='this.value=this.value.toUpperCase();' size='48' /></label></td></tr>");
			out.print("<tr><td width='12%'>% F.S.P</td><td><label><input name='txtNombreIva' type='text' id='dbn4' onkeyup='this.value=this.value.toUpperCase();' size='48' /></label></td>");
			out.print("<td width='12%'>% A.R.P</td><td><label><input name='txtNombreIva' type='text' id='dbn5' onkeyup='this.value=this.value.toUpperCase();' size='48' /></label></td></tr>");
			out.print("<tr><td width='12%'>% Caja de Compensacion</td><td><label><input name='txtNombreIva' type='text' id='dbn6' onkeyup='this.value=this.value.toUpperCase();' size='48' /></label></td>");
			out.print("<td width='12%'>% I.C.B.F</td><td><label><input name='txtNombreIva' type='text' id='dbn7' onkeyup='this.value=this.value.toUpperCase();' size='48' /></label></td></tr>");
			out.print("<tr><td width='12%'>% SENA</td><td><label><input name='txtNombreIva' type='text' id='dbn8' onkeyup='this.value=this.value.toUpperCase();' size='48' /></label></td>");
			out.print("<td width='12%'>Cuenta</td><td><label><input name='txtNombreIva' type='text' id='dbn9' onkeyup='this.value=this.value.toUpperCase();' size='48' /></label></td></tr>");
			
			out.print("<tr><td></td><td><div align='left'><label><input type='button' name='btnCrearIva' id='btnCrearIva' value='Crear' onClick='Crearfeps()'></label></div></td></tr>");		
			out.print("<table width='100%' class='style6'><tr><td colspan='2'><div align='center' class='style11' id='cabecera2'>Fondos y/o EPS creados</div></td></tr>");
			out.print("<table width='100%' border='0' class='style6'><tr><td width='30%'><u><i><div align='left'>Nombre</div></i></u></td><td width='15%'><u><i><div align='left'>Identificacion</div></i></u></td><td width='7%'><u><i><div align='left'>% Pension</div></i></u></td><td width='7%'><u><i><div align='left'>% Salud</div></i></u></td><td width='7%'><u><i><div align='left'>% F.S.P</div></i></u></td><td width='7%'><u><i><div align='left'>% A.R.P</div></i></u></td><td width='7%'><u><i><div align='left'>% Caja Comp.</div></i></u></td><td width='7%'><u><i><div align='left'>% I.C.B.F</div></i></u></td><td width='7%'><u><i><div align='left'>% SENA</div></i></u></td><td width='13%'><u><i><div align='left'>Cuenta</div></i></u></td></tr>");
			//out.print("<table width='100%' border='0' class='style6'>");
			
			int i=0;
			rs1=mn.ConsultarFondosYEPS();
			try {
				while(rs1.next()){
				if((i%2)==0){
					out.print("<tr  bgcolor='#E0DDDD' ><td>"+rs1.getString(2)+"</td><td>"+rs1.getString(18)+" "+rs1.getString(4)+" - "+rs1.getString(5)+"</td><td>"+rs1.getString(6)+"</td><td>"+rs1.getString(7)+"</td><td>"+rs1.getString(8)+"</td><td>"+rs1.getString(9)+"</td><td>"+rs1.getString(10)+"</td><td>"+rs1.getString(11)+"</td><td>"+rs1.getString(12)+"</td><td>"+rs1.getString(13)+"</td></tr>");
					}else{
					out.print("<tr><td>"+rs1.getString(2)+"</td><td>"+rs1.getString(18)+" "+rs1.getString(4)+" - "+rs1.getString(5)+"</td><td>"+rs1.getString(6)+"</td><td>"+rs1.getString(7)+"</td><td>"+rs1.getString(8)+"</td><td>"+rs1.getString(9)+"</td><td>"+rs1.getString(10)+"</td><td>"+rs1.getString(11)+"</td><td>"+rs1.getString(12)+"</td><td>"+rs1.getString(13)+"</td></tr>");
					}
					i++;
				
				
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			out.print("<tr></tr></table>");
			
		}
		
		
		if(va.equals("5.1")){	
			 mn.CrearFondosYEPS(dbn1,tc,dbn10,dbn11,dbn2,dbn3,dbn4,dbn5,dbn6,dbn7,dbn8,dbn9,usu,fechacjmysql,hra);
		}
		
		if(va.equals("5.2")){	
			////PARA ACTUALIZAR O ELIMINAR FONDOS
			  
		}
		
		if(va.equals("6")){		
			out.print("<table width='100%' class='style6'><tr><td colspan='2'><div align='center' class='style11' id='cabecera2'>Parametros de liquidacion</div></td></tr>");
			
			int ct=0;
			rs = mn.ConsultarPliquidacion();
			try {
				if(rs.next()){
					ct++;
						
					
					out.print("<tr><td width='22%'>Año</td><td><label><input name='anio' type='text' id='anio' size='35' onkeyup='validaannio(this,"+annio+")'  value='"+rs.getString(2)+"'/></label></td></tr>");
					out.print("<tr><td width='22%'>Dias cesantias</td><td><label><input name='smd' type='text' id='dbn1' size='35'  onkeyup='checknumt(1)'  value='"+rs.getString(3)+"'/></label></td></tr>");
					out.print("<tr><td width='22%'>Dias primas</td><td><label><input name='smd' type='text' id='dbn2' size='35'  onkeyup='checknumt(2)'  value='"+rs.getString(4)+"'/></label></td></tr>");
					out.print("<tr><td width='22%'>Dias vacaciones</td><td><label><input name='smd' type='text' id='dbn3' size='35' onkeyup='checknumt(3)'  value='"+rs.getString(5)+"'/></label></td></tr>");
					out.print("<tr><td width='22%'>Dias indemnizacion por primer año</td><td><label><input name='smd' type='text' id='dbn4' size='35' onkeyup='checknumt(4)'  value='"+rs.getString(6)+"'/></label></td></tr>");
					out.print("<tr><td width='22%'>Dias indemnizacion por años adicionales</td><td><label><input name='smd' type='text' id='dbn5' size='35' onkeyup='checknumt(5)'  value='"+rs.getString(7)+"'/></label></td></tr>");
					out.print("<tr><td width='22%'>Dias indemnizacion por primer año >= 10</td><td><label><input name='smd' type='text' id='dbn6' size='35' onkeyup='checknumt(6)' value='"+rs.getString(8)+"' /></label></td></tr>");
					out.print("<tr><td width='22%'>Dias indemnizacion por años adicionales >= 10</td><td><label><input name='smd' type='text' id='dbn7' size='35' onkeyup='checknumt(7)'  value='"+rs.getString(9)+"'/></label></td></tr>");
					out.print("<tr><td width='22%'>% Intereses cesantias</td><td><label><input name='smd' type='text' id='dbn8' size='35' onkeyup='checknumt(8)'  value='"+rs.getString(10)+"'/></label></td></tr>");
					out.print("<tr><td width='22%'>% Tope descuento prestamo</td><td><label><input name='smd' type='text' id='dbn9' size='35' onkeyup='checknumt(9)'  value='"+rs.getString(11)+"'/></label></td></tr>");
					out.print("<tr><td width='22%'>% Provision primas</td><td><label><input name='smd' type='text' id='dbn10' size='35' onkeyup='checknumt(10)'  value='"+rs.getString(12)+"'/></label></td></tr>");
					out.print("<tr><td width='22%'>% Provision vacaciones</td><td><label><input name='smd' type='text' id='dbn11' size='35' onkeyup='checknumt(11)'  value='"+rs.getString(13)+"'/></label></td></tr>");
					out.print("<tr><td width='22%'>% Provision cesantias</td><td><label><input name='smd' type='text' id='dbn12' size='35' onkeyup='checknumt(12)'  value='"+rs.getString(14)+"'/></label></td></tr>");
					out.print("<tr><td width='22%'>% Provision interes cesantias</td><td><label><input name='smd' type='text' id='dbn13' size='35' onkeyup='checknumt(13)'  value='"+rs.getString(15)+"'/></label></td></tr>");
					out.print("<tr><td width='22%'>Fecha Ultima Actualización</td><td><label><input name='smd' type='text' id='fua' size='35'  readonly='readonly' value='"+rs.getString(17)+"' /></label></td></tr>");
					out.print("<tr><td width='22%'>Hora Ultima Actualización</td><td><label><input name='smd' type='text' id='hua' size='35'  readonly='readonly' value='"+rs.getString(18)+"' /></label></td></tr>");
					
					
					
					rs1 = mn.ConsultarUsuario(rs.getString(16));
					if(rs1.next()){
					out.print("<tr><td width='22%'>Usuario Ultima Actualización</td><td><label><input name='smd' type='text' id='uua' size='35'  value='"+rs1.getString(1)+" "+rs1.getString(2)+"'  readonly='readonly' /></label></td></tr>");
					}rs1.getStatement().getConnection().close();
					
					out.print("<tr></tr><tr></tr><tr></tr><tr><td></td><td><div align='left'><label><input type='button' name='btnCrearIva' id='btnCrearIva' value='Actualizar' onClick='actualizarliquidacion()'></label></div></td></tr>");		
						
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			if(ct==0){
				out.print("<tr><td width='22%'>Año</td><td><label><input name='anio' type='text' id='anio' size='35' onkeyup='validaannio(this,"+annio+")' /></label></td></tr>");
				out.print("<tr><td width='22%'>Dias cesantias</td><td><label><input name='smd' type='text' id='dbn1' size='35'  onkeyup='checknumt(1)'/></label></td></tr>");
				out.print("<tr><td width='22%'>Dias primas</td><td><label><input name='smd' type='text' id='dbn2' size='35'  onkeyup='checknumt(2)'/></label></td></tr>");
				out.print("<tr><td width='22%'>Dias vacaciones</td><td><label><input name='smd' type='text' id='dbn3' size='35' onkeyup='checknumt(3)' /></label></td></tr>");
				out.print("<tr><td width='22%'>Dias indemnizacion por primer año</td><td><label><input name='smd' type='text' id='dbn4' size='35' onkeyup='checknumt(4)' /></label></td></tr>");
				out.print("<tr><td width='22%'>Dias indemnizacion por años adicionales</td><td><label><input name='smd' type='text' id='dbn5' size='35' onkeyup='checknumt(5)' /></label></td></tr>");
				out.print("<tr><td width='22%'>Dias indemnizacion por primer año >= 10</td><td><label><input name='smd' type='text' id='dbn6' size='35' onkeyup='checknumt(6)' /></label></td></tr>");
				out.print("<tr><td width='22%'>Dias indemnizacion por años adicionales >= 10</td><td><label><input name='smd' type='text' id='dbn7' size='35' onkeyup='checknumt(7)' /></label></td></tr>");
				out.print("<tr><td width='22%'>% Intereses cesantias</td><td><label><input name='smd' type='text' id='dbn8' size='35' onkeyup='checknumt(8)' /></label></td></tr>");
				out.print("<tr><td width='22%'>% Tope descuento prestamo</td><td><label><input name='smd' type='text' id='dbn9' size='35' onkeyup='checknumt(9)' /></label></td></tr>");
				out.print("<tr><td width='22%'>% Provision primas</td><td><label><input name='smd' type='text' id='dbn10' size='35' onkeyup='checknumt(10)' /></label></td></tr>");
				out.print("<tr><td width='22%'>% Provision vacaciones</td><td><label><input name='smd' type='text' id='dbn11' size='35' onkeyup='checknumt(11)' /></label></td></tr>");
				out.print("<tr><td width='22%'>% Provision cesantias</td><td><label><input name='smd' type='text' id='dbn12' size='35' onkeyup='checknumt(12)' /></label></td></tr>");
				out.print("<tr><td width='22%'>% Provision interes cesantias</td><td><label><input name='smd' type='text' id='dbn13' size='35' onkeyup='checknumt(13)' /></label></td></tr>");
			
				out.print("<tr></tr><tr></tr><tr></tr><tr><td></td><td><div align='left'><label><input type='button' name='btnCrearIva' id='btnCrearIva' value='Crear' onClick='crarliqidacion()'></label></div></td></tr>");		
				
			}
		}
		
		if(va.equals("6.1")){
		    mn.CrearPliquidacion(anio,dbn1,dbn2,dbn3,dbn4,dbn5,dbn6,dbn7,dbn8,dbn9,dbn10,dbn11,dbn12,dbn13,usu,fechacjmysql,hra);
		}
		
		if(va.equals("6.2")){
			mn.ActualizarEstadoPliquidacion();
			mn.CrearPliquidacion(anio,dbn1,dbn2,dbn3,dbn4,dbn5,dbn6,dbn7,dbn8,dbn9,dbn10,dbn11,dbn12,dbn13,usu,fechacjmysql,hra);
		}
		
		
		if(va.equals("7")){	
			
			out.print("<table width='100%' class='style6'>");
			out.print("<tr bgcolor='#E0DDDD'><td colspan='4'><div align='center'>Empleados - Informacion Personal</div></td></tr>");
			
			out.print("<tr><td width='12%'>Identificacion</td><td><label><select  style='width:50px' name='tc' id='tc' >");
			rs1=mn.ConsultarTiposDocumentos();
			try {
				while(rs1.next()){
					out.print("<option title='"+rs1.getString(2)+"' value='"+rs1.getString(1)+"'>"+rs1.getString(3)+"</option>");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			out.print("</label>");
			out.print("<label><input name='txtNombreIva' type='text' id='nit' size='20' onchange='validanit()' /><font color='red'> *</label></td><td></td></tr>");
			out.print("<tr><td width='12%'>Nombre</td><td   colspan='3'><label><input name='txtNombreIva' type='text' id='dbn1' onkeyup='this.value=this.value.toUpperCase();' size='161' /></label></td></tr>");
					
			out.print("<tr><td width='12%'>Primer Apellido</td><td><label><input name='txtNombreIva' type='text' id='dbn2' onkeyup='this.value=this.value.toUpperCase();' size='48' /><font color='red'> *</label></td>");
			out.print("<td width='12%'>Segundo Apellido</td><td><label><input name='txtNombreIva' type='text' id='dbn3' onkeyup='this.value=this.value.toUpperCase();' size='48' /></label></td></tr>");
			out.print("<tr><td width='12%'>Primer Nombre</td><td><label><input name='txtNombreIva' type='text' id='dbn4' onkeyup='this.value=this.value.toUpperCase();' size='48' /><font color='red'> *</label></td>");
			out.print("<td width='12%'>Segundo Nombre</td><td><label><input name='txtNombreIva' type='text' id='dbn5' onkeyup='this.value=this.value.toUpperCase();' size='48' /></label></td></tr>");
			out.print("<tr><td width='12%'>Direccion</td><td><label><input name='txtNombreIva' type='text' id='dbn6' onkeyup='this.value=this.value.toUpperCase();' size='48' /></label></td>");
			out.print("<td width='12%'>Telefono</td><td><label><input name='txtNombreIva' type='text' id='dbn7' onkeyup='this.value=this.value.toUpperCase();' size='48' /></label></td></tr>");
			
			//out.print("<tr><td width='12%'>Centro de costo</td><td><label><input name='txtNombreIva' type='text' id='dbn8' onkeyup='this.value=this.value.toUpperCase();' size='48' /></label></td>");
			
			out.print("<tr><td width='12%'>Centro de costo</td><td><label><select  style='width:323px' id='dbn8' onchange='subcentros()'><option value='Seleccione'>Seleccione</option>");
			rs1=mn.ConsultarCentrosdeCosto();
			try {
				while(rs1.next()){
					out.print("<option title='"+rs1.getString(2)+"' value='"+rs1.getString(1)+"'>"+rs1.getString(2)+"</option>");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			out.print("</label>");
			
			out.print("<td width='12%'>Subcentro de costo </td><td><div id='cco'><label><select  style='width:323px' id='dbn9' ><option value='Seleccione'>Seleccione</option></selet></label></div></td></tr>");
			out.print("<tr><td width='12%'>Estado civil</td><td><label><input name='txtNombreIva' type='text' id='dbn10' onkeyup='this.value=this.value.toUpperCase();' size='48' /></label></td>");
			out.print("<td width='12%'>Fecha de Nacimiento</td><td><label><input name='txtNombreIva' type='text' id='dbn11' onKeyup='masca(this,patron,true,0,0,0)' size='48' /></label></td></tr>");
			out.print("<tr><td width='12%'>Sexo</td><td><label><input name='txtNombreIva' type='text' id='dbn12' onkeyup='this.value=this.value.toUpperCase();' size='48' /></label></td>");
			//out.print("<td width='12%'>Estado</td><td><label><input name='txtNombreIva' type='text' id='dbn13' onkeyup='this.value=this.value.toUpperCase();' size='48' /></label></td></tr>");
			out.print("<td width='12%'>Estado</td><td><label><select  style='width:323px' id='dbn13' ><option value='0'>Activo</option><option value='1'>Inactivo</option>");
				 
			out.print("<tr bgcolor='#E0DDDD'><td colspan='4'><div align='center'>Informacion Laboral</div></td></tr>");
			//out.print("<table width='100%' class='style6'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Informacion Laboral</div></td></tr>");
			
			out.print("<tr><td width='12%'>Cargo</td><td><label><input name='txtNombreIva' type='text' id='dbn14' onkeyup='this.value=this.value.toUpperCase();' size='48' /></label></td>");
			
			//out.print("<td width='12%'>Clase de empleado</td><td><label><input name='txtNombreIva' type='text' id='dbn15' onkeyup='this.value=this.value.toUpperCase();' size='48' /></label></td></tr>");
			out.print("<td width='12%'>Clase de empleado</td><td><label><select  style='width:323px' id='dbn15' ><option value='Seleccione'>Seleccione</option>");
			rs1=mn.ConsultarClaseEmpleado();
			try {
				while(rs1.next()){
					out.print("<option title='"+rs1.getString(2)+"' value='"+rs1.getString(1)+"'>"+rs1.getString(2)+"</option>");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			out.print("</label></select></td></tr>");
			
			//out.print("<tr><td width='12%'></td><td><label><input name='txtNombreIva' type='text' id='dbn16' onkeyup='this.value=this.value.toUpperCase();' size='48' /></label></td>");	
			out.print("<tr><td width='12%'>Contrato</td><td><label><select  style='width:323px' id='dbn16' ><option value='Seleccione'>Seleccione</option>");
			rs1=mn.ConsultarTiposdeContrato();
			try {
				while(rs1.next()){
					out.print("<option title='"+rs1.getString(2)+"' value='"+rs1.getString(1)+"'>"+rs1.getString(2)+"</option>");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			out.print("</label></select></td>");
			
			out.print("<td width='12%'>Fecha Inicial</td><td><label><input name='txtNombreIva' type='text' id='dbn17' onKeyup='masca(this,patron,true,0,0,0)' size='48' /></label></td></tr>");
			//out.print("<tr><td width='12%'>Tipo de Cuenta</td><td><label><input name='txtNombreIva' type='text' id='dbn18' onkeyup='this.value=this.value.toUpperCase();' size='48' /></label></td>");
			out.print("<tr><td width='12%'>Tipo de Cuenta</td><td><label><select  style='width:323px' id='dbn18' ><option value='Seleccione'>Seleccione</option>");
			rs1=mn.ConsultarTiposdeCuenta();
			try {
				while(rs1.next()){
					out.print("<option title='"+rs1.getString(2)+"' value='"+rs1.getString(1)+"'>"+rs1.getString(2)+"</option>");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			out.print("</label></select></td>");
			
			out.print("<td width='12%'>Numero de Cuenta</td><td><label><input name='txtNombreIva' type='text' id='dbn19' onkeyup='this.value=this.value.toUpperCase();' size='48' /></label></td></tr>");
			//out.print("<tr><td width='12%'>Forma de Pago</td><td><label><input name='txtNombreIva' type='text' id='dbn8' onkeyup='this.value=this.value.toUpperCase();' size='48' /></label></td>");
			out.print("<tr><td width='12%'>Forma de Pago</td><td><label><select  style='width:323px' id='dbn20' ><option value='Seleccione'>Seleccione</option>");
			rs1=mn.ConsultarFormasdePago();
			try {
				while(rs1.next()){
					out.print("<option title='"+rs1.getString(2)+"' value='"+rs1.getString(1)+"'>"+rs1.getString(2)+"</option>");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			out.print("</label></select></td>");
			out.print("<td width='12%'>Sueldo Diario</td><td><label><input name='txtNombreIva' type='text' id='dbn21' onkeyup='this.value=this.value.toUpperCase();' size='48' /></label></td></tr>");
			out.print("<tr><td width='12%'>Fecha de Aumento</td><td><label><input name='txtNombreIva' type='text' id='dbn22' onKeyup='masca(this,patron,true,0,0,0)' size='48' /></label></td>");
			out.print("<td width='12%'>Fecha de retiro</td><td><label><input name='txtNombreIva' type='text' id='dbn23' onKeyup='masca(this,patron,true,0,0,0)' size='48' /></label></td></tr>");
				
			out.print("<tr bgcolor='#E0DDDD'><td colspan='4'><div align='center'>Informacion Salud</div></td></tr>");
			
			out.print("<tr><td width='12%'>A.R.P</td><td><label><input name='txtNombreIva' type='text' id='dbn24' onkeyup='this.value=this.value.toUpperCase();' size='48' /></label></td>");
			out.print("<td width='12%'>E.P.S</td><td><label><input name='txtNombreIva' type='text' id='dbn25' onkeyup='this.value=this.value.toUpperCase();' size='48' /></label></td></tr>");
			out.print("<tr><td width='12%'>Caja Compensacion</td><td><label><input name='txtNombreIva' type='text' id='dbn26' onkeyup='this.value=this.value.toUpperCase();' size='48' /></label></td>");
			out.print("<td width='12%'>Fondo Cesantias</td><td><label><input name='txtNombreIva' type='text' id='dbn27' onkeyup='this.value=this.value.toUpperCase();' size='48' /></label></td></tr>");
			out.print("<tr><td width='12%'>Pension</td><td><label><input name='txtNombreIva' type='text' id='dbn28' onkeyup='this.value=this.value.toUpperCase();' size='48' /></label></td>");
			out.print("<td width='12%'>Pension Voluntaria</td><td><label><input name='txtNombreIva' type='text' id='dbn29' onkeyup='this.value=this.value.toUpperCase();' size='48' /></label></td></tr>");
			out.print("<tr><td width='12%'>I.C.B.F</td><td><label><input name='txtNombreIva' type='text' id='dbn30' onkeyup='this.value=this.value.toUpperCase();' size='48' /></label></td>");
			out.print("<td width='12%'>SENA</td><td><label><input name='txtNombreIva' type='text' id='dbn31' onkeyup='this.value=this.value.toUpperCase();' size='48' /></label></td></tr>");
			out.print("<tr><td width='12%'>Aplica auxilio de transporte</td><td><label><input type='checkbox' id='dbn32' ></label></td></tr>");
						
			out.print("<tr bgcolor='#E0DDDD'><td colspan='4'><div align='center'>Informacion Adicional</div></td></tr>");
				
			//out.print("<tr><td width='12%'>Escolaridad</td><td><label><input name='txtNombreIva' type='text' id='dbn2' onkeyup='this.value=this.value.toUpperCase();' size='48' /></label></td>");
			out.print("<tr><td width='12%'>Escolaridad</td><td><label><select  style='width:323px' id='dbn33' ><option value='Seleccione'>Seleccione</option>");
			rs1=mn.ConsultarEscolaridad();
			try {
				while(rs1.next()){
					out.print("<option title='"+rs1.getString(2)+"' value='"+rs1.getString(1)+"'>"+rs1.getString(2)+"</option>");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			out.print("</label></select></td>");
			
			//out.print("<td width='12%'>Grupo Sanguineo</td><td><label><input name='txtNombreIva' type='text' id='dbn3' onkeyup='this.value=this.value.toUpperCase();' size='48' /></label></td></tr>");
			out.print("<td width='12%'>Grupo Sanguineo</td><td><label><select  style='width:323px' id='dbn34' ><option value='Seleccione'>Seleccione</option>");
			rs1=mn.ConsultarGrupoSAnguineo();
			try {
				while(rs1.next()){
					out.print("<option title='"+rs1.getString(2)+"' value='"+rs1.getString(1)+"'>"+rs1.getString(3)+"</option>");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			out.print("</label></select></td></tr>");
			out.print("<tr><td width='12%'>Talla Camisa</td><td><label><input name='txtNombreIva' type='text' id='dbn35' onkeyup='this.value=this.value.toUpperCase();' size='48' /></label></td>");
			out.print("<td width='12%'>Talla Pantalon</td><td><label><input name='txtNombreIva' type='text' id='dbn36' onkeyup='this.value=this.value.toUpperCase();' size='48' /></label></td></tr>");
			out.print("<tr><td width='12%'>Talla Zapatos</td><td><label><input name='txtNombreIva' type='text' id='dbn37' onkeyup='this.value=this.value.toUpperCase();' size='48' /></label></td>");
			out.print("<td width='12%'>E-Mail</td><td><label><input name='txtNombreIva' type='text' id='dbn38'  size='48' /></label></td></tr>");
			out.print("<tr><td width='12%'>No. Hijos</td><td><label><input name='txtNombreIva' type='text' id='dbn39' size='48' /></label></td>");
		
			//out.print("<tr><td></td><td><div align='left'><label><input type='button' name='btnCrearIva' id='btnCrearIva' value='Actualizar' onClick='Crearfepsrrrrr()'></label></div></td><td><div align='left'><label><input type='button' name='btnCrearIva' id='btnCrearIva' value='<<' onClick='Crearfepsrrrrr()'></label></div></td><td><div align='left'><label><input type='button' name='btnCrearIva' id='btnCrearIva' value='>>' onClick='Crearfepsrrrrr()'></label></div></td></tr>");		
			out.print("<tr><td></td><td><div align='left'><label><input type='button' name='btnCrearIva' id='btnCrearIva' value='Crear' onClick='CrearEmpleado()'></label></div></td></tr>");		
			
	
		}
		
		if(va.equals("7.2")){	
			
			out.print("<td><label><select  style='width:323px' id='dbn9' ><option value='Seleccione'>Seleccione</option>");
			rs1=mn.ConsultarSubCentrosdeCosto(dbn8);
			try {
				while(rs1.next()){
					out.print("<option title='"+rs1.getString(2)+"' value='"+rs1.getString(1)+"'>"+rs1.getString(2)+"</option>");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			out.print("</label></td></tr>");
			
			}
		
		if(va.equals("7.3")){	
			System.out.println("dbn11dbn11dbn11dbn11dbn11: "+dbn11);
			String dv8=dbn11.substring(0, 2);
			String mv8=dbn11.substring(3, 5);
			String av8=dbn11.substring(6, 10);
			String fv=av8+"-"+mv8+"-"+dv8;
			mn.CrearEmpleados(tc,nit,dbn2,dbn3,dbn4,dbn5,dbn6,dbn7,dbn8,dbn9,dbn10,dbn12,fv,dbn13,usu,fechacjmysql,hra);
			
			String code2="";
			rs1=mn.ConsultarCodEmpleado(usu,fechacjmysql,hra);
			try {
				while(rs1.next()){
					code2=rs1.getString(1);
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			dv8=dbn17.substring(0, 2);
			mv8=dbn17.substring(3, 5);
			av8=dbn17.substring(6, 10);
			String fv17=av8+"-"+mv8+"-"+dv8;
			
			dv8=dbn22.substring(0, 2);
			mv8=dbn22.substring(3, 5);
			av8=dbn22.substring(6, 10);
			String fv22=av8+"-"+mv8+"-"+dv8;
			
			dv8=dbn23.substring(0, 2);
			mv8=dbn23.substring(3, 5);
			av8=dbn23.substring(6, 10);
			String fv23=av8+"-"+mv8+"-"+dv8;
			
			mn.CrearEmpleados2(code2,dbn14,dbn15,dbn16,fv17,dbn18,dbn19,dbn20,dbn21,fv22,fv23);
			mn.CrearEmpleados3(code2,dbn24,dbn25,dbn26,dbn27,dbn28,dbn29,dbn30,dbn31,dbn32);
			mn.CrearEmpleados4(code2,dbn33,dbn34,dbn35,dbn36,dbn37,dbn38,dbn39);
			
		}
		
		
		if(va.equals("7.1")){	
			
			String codemple="";
			int indicea=0;
			int indicep=0;
			
			//System.out.println("indice "+indice);
			
			if(indice==null){
				indice="0"; 
			}
			
			
			int ce=0;
			String datose="";
			rs1=mn.ConsultarCodEmpleados();
			try {
				while(rs1.next()){
					datose=datose+rs1.getString(1)+"|";
					//System.out.println("Empleados "+rs1.getString(1));
					ce++;
					//System.out.println("CECECECE "+ce);	
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
			String V[] = new String[ce];
			int i1=0;
			StringTokenizer ele;  
			ele = new StringTokenizer(datose,"|"); 
			
			while(ele.hasMoreTokens()){ 
			   V[i1] = ele.nextToken();   
			  // System.out.println("V["+i1+"]:"+V[i1]);
			   i1++;
			}
			//System.out.println("i1 "+i1);


			
			String sq="";
			String codab="";
			if(disp==null){disp="0";}
			
			if(!disp.equals("0")){
				
				if(disp.equals("1")){sq="WHERE num_doc='"+ndn+"'";}
				if(disp.equals("2")){sq="WHERE primer_apellido LIKE '"+pan+"%'";}
				if(disp.equals("3")){sq="WHERE primer_nombre LIKE '"+pnn+"%'";}
				
				rs1=mn.ConsultarCodEmpleados(sq);
				try {
					if(rs1.next()){
						//rs1.getString(1)
						//System.out.println("Empleados "+rs1.getString(1));
						//aqui tengo el codigo luego lo busco en el vector saco el indice y listo
						for(int i=0;i<i1;i++){
							if(rs1.getString(1).equals(V[i]) ){
								indice=String.valueOf(i);
								//System.out.println("V["+i+"]: "+V[i]+"  rs1: "+rs1.getString(1)+" indice: "+indice);
								
							}
						}
						
					}
					rs1.getStatement().getConnection().close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			//System.out.println("tdn "+tdn+" ndn "+ndn+" pan "+pan+" pnn "+pnn+" disp "+disp);
			



			
			if(indice.equals("0")){
				indicea=0;
			}else{
				indicea=Integer.parseInt(indice)-1;
			}
			
			if(Integer.parseInt(indice)<i1-1){
				indicep=Integer.parseInt(indice)+1;
			}else{
				indicep=Integer.parseInt(indice);
			}
			
			
			 
			out.print("<table width='100%' class='style6'>");
			out.print("<tr bgcolor='#E0DDDD'><td colspan='4'><div align='center'>Empleados - Informacion Personal</div></td></tr>");
			
			
			codemple=V[Integer.parseInt(indice)];
			
			 
			 
			 rs=mn.ConsultarEInformacion(codemple);
				try {
					while(rs.next()){
						//rs.getString(1)
						out.print("<input type='hidden' id='ce1' value='"+rs.getString(1)+"'/>");
						////////////////////////////////////////////////
						out.print("<tr><td width='12%'>Identificacion</td><td><label><select  style='width:50px' name='tc' id='tc'  onChange='ActualizaEmpleados(this)' >");
						rs1=mn.ConsultarTiposDocumentos(rs.getString(2));
						try {
							while(rs1.next()){
								out.print("<option title='"+rs1.getString(2)+"' value='"+rs1.getString(1)+"'>"+rs1.getString(3)+"</option>");
							}
							rs1.getStatement().getConnection().close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
						
						out.print("</label>");
						out.print("<label><input name='txtNombreIva' type='text' id='nit' size='20' onchange='validanit()' value='"+rs.getString(3)+"'  onBlur='ActualizaEmpleados(this)' /><font color='red'> *</label><label title='Buscar por Documento'><img src='Imagenes/lupa.jpg' onClick='CEmpleadosy(1)' style='cursor:pointer;' /></lable></td><td></td></tr>");
						out.print("<tr><td width='12%'>Nombre</td><td   colspan='3'><label><input  type='text' id='dbn1' onkeyup='this.value=this.value.toUpperCase();' size='161' value='"+rs.getString(6)+" "+rs.getString(7)+" "+rs.getString(4)+" "+rs.getString(5)+"'  readonly='readonly'   /></label></td></tr>");
								
						out.print("<tr><td width='12%'>Primer Apellido</td><td><label><input  type='text' id='dbn2' onkeyup='this.value=this.value.toUpperCase();' size='48' value='"+rs.getString(4)+"'  onBlur='ActualizaEmpleados(this)' /><font color='red'> *</label><label title='Buscar por Apellido'><img src='Imagenes/lupa.jpg' onClick='CEmpleadosy(2)' style='cursor:pointer;' /></lable></td>");
						out.print("<td width='12%'>Segundo Apellido</td><td><label><input  type='text' id='dbn3' onkeyup='this.value=this.value.toUpperCase();' size='48' value='"+rs.getString(5)+"' onBlur='ActualizaEmpleados(this)' /></label></td></tr>");
						out.print("<tr><td width='12%'>Primer Nombre</td><td><label><input  type='text' id='dbn4' onkeyup='this.value=this.value.toUpperCase();' size='48' value='"+rs.getString(6)+"' onBlur='ActualizaEmpleados(this)' /><font color='red'> *</label><label title='Buscar por Nombre'><img src='Imagenes/lupa.jpg' onClick='CEmpleadosy(3)' style='cursor:pointer;' /></lable></td>");
						out.print("<td width='12%'>Segundo Nombre</td><td><label><input  type='text' id='dbn5' onkeyup='this.value=this.value.toUpperCase();' size='48' value='"+rs.getString(7)+"'  onBlur='ActualizaEmpleados(this)' /></label></td></tr>");
						out.print("<tr><td width='12%'>Direccion</td><td><label><input  type='text' id='dbn6' onkeyup='this.value=this.value.toUpperCase();' size='48' value='"+rs.getString(8)+"'  onBlur='ActualizaEmpleados(this)' /></label></td>");
						out.print("<td width='12%'>Telefono</td><td><label><input  type='text' id='dbn7' onkeyup='this.value=this.value.toUpperCase();' size='48' value='"+rs.getString(9)+"' onBlur='ActualizaEmpleados(this)' /></label></td></tr>");
						
						//out.print("<tr><td width='12%'>Centro de costo</td><td><label><input name='txtNombreIva' type='text' id='dbn8' onkeyup='this.value=this.value.toUpperCase();' size='48' /></label></td>");
						
						out.print("<tr><td width='12%'>Centro de costo</td><td><label><select  style='width:323px' id='dbn8' onchange='subcentros2(), ActualizaEmpleados(this)'>");
						rs1=mn.ConsultarCentrosdeCosto(rs.getString(10));
						try {
							while(rs1.next()){
								out.print("<option title='"+rs1.getString(2)+"' value='"+rs1.getString(1)+"'>"+rs1.getString(2)+"</option>");
							}
							rs1.getStatement().getConnection().close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
						out.print("</label>");
						
						out.print("<td width='12%'>Subcentro de costo </td><td><div id='cco'><label><select  style='width:323px' id='dbn9'  onChange='ActualizaEmpleados(this)' >");
						
						rs1=mn.ConsultarSubCentrosdeCosto(rs.getString(10),rs.getString(11));
						try {
							while(rs1.next()){
								out.print("<option title='"+rs1.getString(2)+"' value='"+rs1.getString(1)+"'>"+rs1.getString(2)+"</option>");
							}
							rs1.getStatement().getConnection().close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
						out.print("</label></select></div></td></tr>");
						
						
						out.print("<tr><td width='12%'>Estado civil</td><td><label><input  type='text' id='dbn10' onkeyup='this.value=this.value.toUpperCase();' size='48'  value='"+rs.getString(12)+"' onBlur='ActualizaEmpleados(this)' /></label></td>");
						out.print("<td width='12%'>Fecha de Nacimiento</td><td><label><input  type='text' id='dbn11' onKeyup='masca(this,patron,true,0,0,0)' size='48'  value='"+rs.getString(14)+"' onBlur='ActualizaEmpleados(this)' /></label></td></tr>");
						out.print("<tr><td width='12%'>Sexo</td><td><label><input  type='text' id='dbn12' onkeyup='this.value=this.value.toUpperCase();' size='48'  value='"+rs.getString(13)+"' onBlur='ActualizaEmpleados(this)'  /></label></td>");
						//out.print("<td width='12%'>Estado</td><td><label><input name='txtNombreIva' type='text' id='dbn13' onkeyup='this.value=this.value.toUpperCase();' size='48' /></label></td></tr>");
						if(rs.getString(15).equals("0")){ 
						out.print("<td width='12%'>Estado</td><td><label><select  style='width:323px' id='dbn13'  onChange='ActualizaEmpleados(this)' ><option value='0'>Activo</option><option value='1'>Inactivo</option>");
						}else{
						out.print("<td width='12%'>Estado</td><td><label><select  style='width:323px' id='dbn13'  onChange='ActualizaEmpleados(this)' ><option value='1'>Inactivo</option><option value='0'>Activo</option>");	
						}
						
						///////////////////////////////////////////////
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			
				rs=mn.ConsultarEInformacionLaboral(codemple);
				try {
					while(rs.next()){
						out.print("<input type='hidden' id='ce2' value='"+rs.getString(1)+"'/>");
						////////////////////////////////////////////////////////////
						out.print("<tr bgcolor='#E0DDDD'><td colspan='4'><div align='center'>Informacion Laboral</div></td></tr>");
						//out.print("<table width='100%' class='style6'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Informacion Laboral</div></td></tr>");
						
						out.print("<tr><td width='12%'>Cargo</td><td><label><input  type='text' id='dbn14' onkeyup='this.value=this.value.toUpperCase();' size='48'  value='"+rs.getString(3)+"'  onBlur='ActualizaEmpleados(this)' /></label></td>");
						
						//out.print("<td width='12%'>Clase de empleado</td><td><label><input name='txtNombreIva' type='text' id='dbn15' onkeyup='this.value=this.value.toUpperCase();' size='48' /></label></td></tr>");
						out.print("<td width='12%'>Clase de empleado</td><td><label><select  style='width:323px' id='dbn15'  onChange='ActualizaEmpleados(this)' >");
						rs1=mn.ConsultarClaseEmpleado(rs.getString(4));
						try {
							while(rs1.next()){
								out.print("<option title='"+rs1.getString(2)+"' value='"+rs1.getString(1)+"'>"+rs1.getString(2)+"</option>");
							}
							rs1.getStatement().getConnection().close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
						out.print("</label></select></td></tr>");
						
						//out.print("<tr><td width='12%'></td><td><label><input name='txtNombreIva' type='text' id='dbn16' onkeyup='this.value=this.value.toUpperCase();' size='48' /></label></td>");	
						out.print("<tr><td width='12%'>Contrato</td><td><label><select  style='width:323px' id='dbn16'  onChange='ActualizaEmpleados(this)' >");
						rs1=mn.ConsultarTiposdeContrato(rs.getString(5));
						try {
							while(rs1.next()){
								out.print("<option title='"+rs1.getString(2)+"' value='"+rs1.getString(1)+"'>"+rs1.getString(2)+"</option>");
							}
							rs1.getStatement().getConnection().close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
						out.print("</label></select></td>");
						
						out.print("<td width='12%'>Fecha Inicial</td><td><label><input name='txtNombreIva' type='text' id='dbn17' onKeyup='masca(this,patron,true,0,0,0)' size='48'  value='"+rs.getString(6)+"'  onBlur='ActualizaEmpleados(this)' /></label></td></tr>");
						//out.print("<tr><td width='12%'>Tipo de Cuenta</td><td><label><input name='txtNombreIva' type='text' id='dbn18' onkeyup='this.value=this.value.toUpperCase();' size='48' /></label></td>");
						out.print("<tr><td width='12%'>Tipo de Cuenta</td><td><label><select  style='width:323px' id='dbn18'  onChange='ActualizaEmpleados(this)' >");
						rs1=mn.ConsultarTiposdeCuenta(rs.getString(7));
						try {
							while(rs1.next()){
								out.print("<option title='"+rs1.getString(2)+"' value='"+rs1.getString(1)+"'>"+rs1.getString(2)+"</option>");
							}
							rs1.getStatement().getConnection().close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
						out.print("</label></select></td>");
						
						out.print("<td width='12%'>Numero de Cuenta</td><td><label><input name='txtNombreIva' type='text' id='dbn19'  onBlur='ActualizaEmpleados(this)'  size='48'  value='"+rs.getString(8)+"'  /></label></td></tr>");
						//out.print("<tr><td width='12%'>Forma de Pago</td><td><label><input name='txtNombreIva' type='text' id='dbn8' onkeyup='this.value=this.value.toUpperCase();' size='48' /></label></td>");
						out.print("<tr><td width='12%'>Forma de Pago</td><td><label><select  style='width:323px' id='dbn20'  onChange='ActualizaEmpleados(this)' >");
						rs1=mn.ConsultarFormasdePago(rs.getString(9));
						try {
							while(rs1.next()){
								out.print("<option title='"+rs1.getString(2)+"' value='"+rs1.getString(1)+"'>"+rs1.getString(2)+"</option>");
							}
							rs1.getStatement().getConnection().close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
						out.print("</label></select></td>");
						out.print("<td width='12%'>Sueldo Diario</td><td><label><input name='txtNombreIva' type='text' id='dbn21'  onBlur='ActualizaEmpleados(this)'  size='48' value='"+rs.getString(10)+"' /></label></td></tr>");
						out.print("<tr><td width='12%'>Fecha de Aumento</td><td><label><input name='txtNombreIva' type='text' id='dbn22' onKeyup='masca(this,patron,true,0,0,0)'  onBlur='ActualizaEmpleados(this)'  size='48' value='"+rs.getString(11)+"' /></label></td>");
						out.print("<td width='12%'>Fecha de retiro</td><td><label><input name='txtNombreIva' type='text' id='dbn23' onKeyup='masca(this,patron,true,0,0,0)'  onBlur='ActualizaEmpleados(this)'  size='48' value='"+rs.getString(12)+"' /></label></td></tr>");
				
						////////////////////////////////////////////////////////////
					
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			
							
				rs=mn.ConsultarEInformacionSalud(codemple);
				try {
					while(rs.next()){
						out.print("<input type='hidden' id='ce3' value='"+rs.getString(1)+"'/>");
						//////////////////////////////////////////////////////////////
						out.print("<tr bgcolor='#E0DDDD'><td colspan='4'><div align='center'>Informacion Salud</div></td></tr>");
						
						out.print("<tr><td width='12%'>A.R.P</td><td><label><input name='txtNombreIva' type='text' id='dbn24' onkeyup='this.value=this.value.toUpperCase();' size='48'  value='"+rs.getString(3)+"'  onBlur='ActualizaEmpleados(this)' /></label></td>");
						out.print("<td width='12%'>E.P.S</td><td><label><input name='txtNombreIva' type='text' id='dbn25' onkeyup='this.value=this.value.toUpperCase();' size='48'  value='"+rs.getString(4)+"'  onBlur='ActualizaEmpleados(this)' /></label></td></tr>");
						out.print("<tr><td width='12%'>Caja Compensacion</td><td><label><input name='txtNombreIva' type='text' id='dbn26' onkeyup='this.value=this.value.toUpperCase();' size='48' value='"+rs.getString(5)+"'  onBlur='ActualizaEmpleados(this)'  /></label></td>");
						out.print("<td width='12%'>Fondo Cesantias</td><td><label><input name='txtNombreIva' type='text' id='dbn27' onkeyup='this.value=this.value.toUpperCase();' size='48' value='"+rs.getString(6)+"'  onBlur='ActualizaEmpleados(this)'  /></label></td></tr>");
						out.print("<tr><td width='12%'>Pension</td><td><label><input name='txtNombreIva' type='text' id='dbn28' onkeyup='this.value=this.value.toUpperCase();' size='48' value='"+rs.getString(7)+"'  onBlur='ActualizaEmpleados(this)'  /></label></td>");
						out.print("<td width='12%'>Pension Voluntaria</td><td><label><input name='txtNombreIva' type='text' id='dbn29' onkeyup='this.value=this.value.toUpperCase();' size='48'  value='"+rs.getString(8)+"'  onBlur='ActualizaEmpleados(this)'  /></label></td></tr>");
						out.print("<tr><td width='12%'>I.C.B.F</td><td><label><input name='txtNombreIva' type='text' id='dbn30' onkeyup='this.value=this.value.toUpperCase();' size='48'  value='"+rs.getString(9)+"' onBlur='ActualizaEmpleados(this)'  /></label></td>");
						out.print("<td width='12%'>SENA</td><td><label><input name='txtNombreIva' type='text' id='dbn31' onkeyup='this.value=this.value.toUpperCase();' size='48'  value='"+rs.getString(10)+"'  onBlur='ActualizaEmpleados(this)'  /></label></td></tr>");
						
						if(rs.getString(11).equals("1")){
						out.print("<tr><td width='12%'>Aplica auxilio de transporte</td><td><label><input type='checkbox' id='dbn32' checked='true'  onClick='ActualizaEmpleados(this)' ></label></td></tr>");
						}else{
						out.print("<tr><td width='12%'>Aplica auxilio de transporte</td><td><label><input type='checkbox' id='dbn32'  onClick='ActualizaEmpleados(this)' ></label></td></tr>");
						}
						
						/////////////////////////////////////////////////////////////
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				
				rs=mn.ConsultarEInformacionAdicional(codemple);
				try {
					while(rs.next()){
						out.print("<input type='hidden' id='ce4' value='"+rs.getString(1)+"'/>");
						/////////////////////////////////////////////////////////////////////
						out.print("<tr bgcolor='#E0DDDD'><td colspan='4'><div align='center'>Informacion Adicional</div></td></tr>");
						
						//out.print("<tr><td width='12%'>Escolaridad</td><td><label><input name='txtNombreIva' type='text' id='dbn2' onkeyup='this.value=this.value.toUpperCase();' size='48' /></label></td>");
						out.print("<tr><td width='12%'>Escolaridad</td><td><label><select  style='width:323px' id='dbn33'  onChange='ActualizaEmpleados(this)' >");
						rs1=mn.ConsultarEscolaridad(rs.getString(3));
						try {
							while(rs1.next()){
								out.print("<option title='"+rs1.getString(2)+"' value='"+rs1.getString(1)+"'>"+rs1.getString(2)+"</option>");
							}
							rs1.getStatement().getConnection().close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
						out.print("</label></select></td>");
						
						//out.print("<td width='12%'>Grupo Sanguineo</td><td><label><input name='txtNombreIva' type='text' id='dbn3' onkeyup='this.value=this.value.toUpperCase();' size='48' /></label></td></tr>");
						out.print("<td width='12%'>Grupo Sanguineo</td><td><label><select  style='width:323px' id='dbn34'  onChange='ActualizaEmpleados(this)' >");
						rs1=mn.ConsultarGrupoSAnguineo(rs.getString(4));
						try {
							while(rs1.next()){
								out.print("<option title='"+rs1.getString(2)+"' value='"+rs1.getString(1)+"'>"+rs1.getString(3)+"</option>");
							}
							rs1.getStatement().getConnection().close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
						out.print("</label></select></td></tr>");
						out.print("<tr><td width='12%'>Talla Camisa</td><td><label><input name='txtNombreIva' type='text' id='dbn35' onkeyup='this.value=this.value.toUpperCase();' size='48'   value='"+rs.getString(5)+"'  onBlur='ActualizaEmpleados(this)' /></label></td>");
						out.print("<td width='12%'>Talla Pantalon</td><td><label><input name='txtNombreIva' type='text' id='dbn36'  size='48'   value='"+rs.getString(6)+"'  onBlur='ActualizaEmpleados(this)' /></label></td></tr>");
						out.print("<tr><td width='12%'>Talla Zapatos</td><td><label><input name='txtNombreIva' type='text' id='dbn37'  size='48'   value='"+rs.getString(7)+"'  onBlur='ActualizaEmpleados(this)' /></label></td>");
						out.print("<td width='12%'>E-Mail</td><td><label><input name='txtNombreIva' type='text' id='dbn38'  size='48'   value='"+rs.getString(8)+"'  onBlur='ActualizaEmpleados(this)' /></label></td></tr>");
						out.print("<tr><td width='12%'>No. Hijos</td><td><label><input name='txtNombreIva' type='text' id='dbn39' size='48'   value='"+rs.getString(9)+"'  onBlur='ActualizaEmpleados(this)' /></label></td>");

						/////////////////////////////////////////////////////////////////////
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				
				
				
			/*	hay q  hacer lo de la actualizacion apenas abandone cada campo
				*/
		
			out.print("<tr></tr><tr></tr><tr></tr><tr></tr></table>");
			out.print("<table  width='100%' class='style6'><tr><td><div align='center'><label><input type='button' id='btnCrearIva' value='<<|'  onClick='CEmpleadosi(0)'><input type='button' id='btnCrearIva' value='<<'  onClick='CEmpleadosi("+indicea+")'>&nbsp&nbsp&nbsp&nbsp"+(Integer.parseInt(indice)+1)+" de "+i1+"&nbsp&nbsp&nbsp&nbsp<input type='button' id='btnCrearIva' value='>>' onClick='CEmpleadosi("+indicep+")'><input type='button' id='btnCrearIva' value='|>>'  onClick='CEmpleadosi("+(i1-1)+")'></label></div></td></tr></table>");		
			
	}
		

		if(va.equals("7.4")){	
			
			out.print("<td><label><select  style='width:323px' id='dbn9'  onChange='ActualizaEmpleados(this)' ><option value='Seleccione'>Seleccione</option>");
			rs1=mn.ConsultarSubCentrosdeCosto(dbn8);
			try {
				while(rs1.next()){
					out.print("<option title='"+rs1.getString(2)+"' value='"+rs1.getString(1)+"'>"+rs1.getString(2)+"</option>");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			out.print("</label></td></tr>");
			
		}
		
		if(va.equals("7.5")){	
	
			String sql="";
			String sql2="";
			//Informacion Personal
			if(dbn1.equals("tc")){		sql=" UPDATE cont_nempleados SET  tipo_doc='"+dbn2+"' WHERE codigo='"+dbn3+"' ";}
			if(dbn1.equals("nit")){		sql=" UPDATE cont_nempleados SET  num_doc='"+dbn2+"' WHERE codigo='"+dbn3+"' ";}
			if(dbn1.equals("dbn2")){	sql=" UPDATE cont_nempleados SET  primer_apellido='"+dbn2+"' WHERE codigo='"+dbn3+"' ";}
			if(dbn1.equals("dbn3")){	sql=" UPDATE cont_nempleados SET  segundo_apellido='"+dbn2+"' WHERE codigo='"+dbn3+"' ";}
			if(dbn1.equals("dbn4")){	sql=" UPDATE cont_nempleados SET  primer_nombre='"+dbn2+"' WHERE codigo='"+dbn3+"' ";}
			if(dbn1.equals("dbn5")){	sql=" UPDATE cont_nempleados SET  segundo_nombre='"+dbn2+"' WHERE codigo='"+dbn3+"' ";}
			if(dbn1.equals("dbn6")){	sql=" UPDATE cont_nempleados SET  direccion='"+dbn2+"' WHERE codigo='"+dbn3+"' ";}
			if(dbn1.equals("dbn7")){	sql=" UPDATE cont_nempleados SET  telefono='"+dbn2+"' WHERE codigo='"+dbn3+"' ";}
			if(dbn1.equals("dbn8")){	sql=" UPDATE cont_nempleados SET  centrocosto_fk='"+dbn2+"' WHERE codigo='"+dbn3+"' ";}
			if(dbn1.equals("dbn9")){	sql=" UPDATE cont_nempleados SET  subcentro_fk='"+dbn2+"' WHERE codigo='"+dbn3+"' ";}
			if(dbn1.equals("dbn10")){	sql=" UPDATE cont_nempleados SET  estadocivil_fk='"+dbn2+"' WHERE codigo='"+dbn3+"' ";}
			if(dbn1.equals("dbn11")){	sql=" UPDATE cont_nempleados SET  sexo='"+dbn2+"' WHERE codigo='"+dbn3+"' ";}
			if(dbn1.equals("dbn12")){	sql=" UPDATE cont_nempleados SET  fecha_nacimiento='"+dbn2+"' WHERE codigo='"+dbn3+"' ";}
			if(dbn1.equals("dbn13")){	sql=" UPDATE cont_nempleados SET  estado='"+dbn2+"' WHERE codigo='"+dbn3+"' ";}
			
			//Informacion Laboral
			if(dbn1.equals("dbn14")){	sql=" UPDATE cont_nemp_infolaboral SET  cargo='"+dbn2+"' WHERE codigo='"+dbn4+"' ";}
			if(dbn1.equals("dbn15")){	sql=" UPDATE cont_nemp_infolaboral SET  clase_empleadofk='"+dbn2+"' WHERE codigo='"+dbn4+"' ";}
			if(dbn1.equals("dbn16")){	sql=" UPDATE cont_nemp_infolaboral SET  contratofk='"+dbn2+"' WHERE codigo='"+dbn4+"' ";}
			if(dbn1.equals("dbn17")){	sql=" UPDATE cont_nemp_infolaboral SET  fechainicial='"+dbn2+"' WHERE codigo='"+dbn4+"' ";}
			if(dbn1.equals("dbn18")){	sql=" UPDATE cont_nemp_infolaboral SET  tipo_cuentafk='"+dbn2+"' WHERE codigo='"+dbn4+"' ";}
			if(dbn1.equals("dbn19")){	sql=" UPDATE cont_nemp_infolaboral SET  numero_cuenta='"+dbn2+"' WHERE codigo='"+dbn4+"' ";}
			if(dbn1.equals("dbn20")){	sql=" UPDATE cont_nemp_infolaboral SET  formadepago_fk='"+dbn2+"' WHERE codigo='"+dbn4+"' ";}
			if(dbn1.equals("dbn21")){	sql=" UPDATE cont_nemp_infolaboral SET  sueldo_diario='"+dbn2+"' WHERE codigo='"+dbn4+"' ";}
			if(dbn1.equals("dbn22")){	sql=" UPDATE cont_nemp_infolaboral SET  fecha_aumento='"+dbn2+"' WHERE codigo='"+dbn4+"' ";}
			if(dbn1.equals("dbn23")){	sql=" UPDATE cont_nemp_infolaboral SET  fecha_retiro='"+dbn2+"' WHERE codigo='"+dbn4+"' ";}
			
			//Infomacion de Salud
			if(dbn1.equals("dbn24")){	sql=" UPDATE cont_nemp_infosalud SET  cod_arpfk='"+dbn2+"' WHERE codigo='"+dbn5+"' ";}
			if(dbn1.equals("dbn25")){	sql=" UPDATE cont_nemp_infosalud SET  cod_epsfk='"+dbn2+"' WHERE codigo='"+dbn5+"' ";}
			if(dbn1.equals("dbn26")){	sql=" UPDATE cont_nemp_infosalud SET  cod_cajacompensacionfk='"+dbn2+"' WHERE codigo='"+dbn5+"' ";}
			if(dbn1.equals("dbn27")){	sql=" UPDATE cont_nemp_infosalud SET  cod_fondocesantiasfk='"+dbn2+"' WHERE codigo='"+dbn5+"' ";}
			if(dbn1.equals("dbn28")){	sql=" UPDATE cont_nemp_infosalud SET  cod_pensionfk='"+dbn2+"' WHERE codigo='"+dbn5+"' ";}
			if(dbn1.equals("dbn29")){	sql=" UPDATE cont_nemp_infosalud SET  cod_pensionvoluntariafk='"+dbn2+"' WHERE codigo='"+dbn5+"' ";}
			if(dbn1.equals("dbn30")){	sql=" UPDATE cont_nemp_infosalud SET  cod_icbffk='"+dbn2+"' WHERE codigo='"+dbn5+"' ";}
			if(dbn1.equals("dbn31")){	sql=" UPDATE cont_nemp_infosalud SET  cod_senafk='"+dbn2+"' WHERE codigo='"+dbn5+"' ";}
			if(dbn1.equals("dbn32")){	sql=" UPDATE cont_nemp_infosalud SET  auxilio='"+dbn2+"' WHERE codigo='"+dbn5+"' ";}
			
			
			//Informacion Adicional
			if(dbn1.equals("dbn33")){	sql=" UPDATE cont_nemp_infoadicional SET  escolaridadfk='"+dbn2+"' WHERE codigo='"+dbn6+"' ";}
			if(dbn1.equals("dbn34")){	sql=" UPDATE cont_nemp_infoadicional SET  gruposanguineofk='"+dbn2+"' WHERE codigo='"+dbn6+"' ";}
			if(dbn1.equals("dbn35")){	sql=" UPDATE cont_nemp_infoadicional SET  tallacamisa='"+dbn2+"' WHERE codigo='"+dbn6+"' ";}
			if(dbn1.equals("dbn36")){	sql=" UPDATE cont_nemp_infoadicional SET  tallapantalon='"+dbn2+"' WHERE codigo='"+dbn6+"' ";}
			if(dbn1.equals("dbn37")){	sql=" UPDATE cont_nemp_infoadicional SET  tallazapatos='"+dbn2+"' WHERE codigo='"+dbn6+"' ";}
			if(dbn1.equals("dbn38")){	sql=" UPDATE cont_nemp_infoadicional SET  email='"+dbn2+"' WHERE codigo='"+dbn6+"' ";}
			if(dbn1.equals("dbn39")){	sql=" UPDATE cont_nemp_infoadicional SET  hijos='"+dbn2+"' WHERE codigo='"+dbn6+"' ";}
			
			mn.ActualizarEmpleado(sql);
			
			sql2=" UPDATE cont_nempleados SET  usuario='"+usu+"' , fecha='"+fechacjmysql+"' , hora='"+hra+"' WHERE codigo='"+dbn3+"' ";
			mn.ActualizarEmpleado(sql2);
			
		}
		
		if(va.equals("8")){	
			
			if(dbn1.equals("0")){
			out.print("<table width='100%' class='style6'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Movimientos de Novedades</div></td></tr>");
			}
			if(dbn1.equals("1")){
			out.print("<table width='100%' class='style6'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Movimientos Fijos</div></td></tr>");
			}
				
			
			out.print("<tr><td width='12%'>Periodo</td><td><label><select  style='width:400px' name='tc' id='tc' onChange='Periodo()' >");
			
			String x="";
			rs1=mn.ConsultarPeriodosNomina();
			try {
				while(rs1.next()){
					out.print("<option value='"+rs1.getString(1)+"'>"+rs1.getString(2)+"</option>");
					if(x.equals("")){
					 x=rs1.getString(4);
					}
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			//out.print("</label>");
			out.print("<td width='12%'>Fecha</td><td><label><input name='txtNombreIva' type='text' id='dbn1' value='"+x+"' size='48' readonly='readonly' /></label></td></tr>");
			// 
			
			out.print("<tr><td width='12%'>Empleado</td><td><label><input name='txtNombreIva' type='text' id='desc0' onfocus='limpiarmovimientos(0)' onkeyup='autocompletaEmpleado(0,0,"+dbn1+")'  size='61' /></label><input type='hidden' id='code'/><input type='hidden' id='sal'/></td>");
			out.print("<td width='12%'>Identificacion</td><td><label><input name='txtNombreIva' type='text' id='dbn3'  size='48' readonly='readonly'/></label></td></tr>");
			out.print("<tr><td></td><td><div id='sugerencias10' ></div></td></tr>");
			out.print("<tr></tr><tr></tr><tr></tr><tr></tr>");
			//out.print("<tr><td width='12%'>% F.S.P</td><td><label><input name='txtNombreIva' type='text' id='dbn4' onkeyup='this.value=this.value.toUpperCase();' size='48' /></label></td>");
			//out.print("<td width='12%'>% A.R.P</td><td><label><input name='txtNombreIva' type='text' id='dbn5' onkeyup='this.value=this.value.toUpperCase();' size='48' /></label></td></tr>");
			//out.print("<tr><td width='12%'>% Caja de Compensacion</td><td><label><input name='txtNombreIva' type='text' id='dbn6' onkeyup='this.value=this.value.toUpperCase();' size='48' /></label></td>");
	
		}
		
		if(va.equals("8.1")){	
			
			rs1=mn.ConsultarPeriodosNomina(tc);
			try {
				if(rs1.next()){
					out.print(rs1.getString(4));
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(va.equals("autoinv")){
			
			try {
				//System.out.print("ESSSTOO."+xx);
				rs =mn.ConsultarEmpleados(texto);
				String cadena ="";
				cadena="[";
				while(rs.next()){
					cadena = cadena+"'"+rs.getString(1)+"|"+rs.getString(6)+" "+rs.getString(7)+" "+rs.getString(4)+" "+rs.getString(5)+"|"+rs.getString(19)+" "+rs.getString(3)+"|"+rs.getString(20)+"'";
	            	cadena = cadena +",";	
				}
				cadena = cadena+"]";
				//System.out.print("ESSSTOO."+cadena);
				response.getWriter().write(cadena);
				rs.getStatement().getConnection().close();
			} catch (Exception e) {
				e.getMessage();
				e.printStackTrace();
			}
		}
		
		
		if(va.equals("9")){	
			
			String at="";
			String sd="";
			String dtr="";
			
			int x=0;
			String codm="";
			rs1=mn.ConsultarMovimientos(tc,code);//CONSULTAMOS SI HAY MOVIMIENTOS
			try {
				if(rs1.next()){
					codm=rs1.getString(1);
					x++;
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			if(x!=0){
				
				int y=0;
				rs1=mn.ConsultarDetalleMovimientos(codm);//SI HAY MOVIMIENTOS CONSULTAMOS CUANTOS DETALLES HAY
				try {
					if(rs1.next()){
						//out.print(rs1.getString(4));
						y=rs1.getInt(1);
					}
					rs1.getStatement().getConnection().close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				
				int z=0;
				rs1=mn.ConsultarSubconceptosNomina();//CONSULTAMOS CUANTOS SUBCONCEPTOS HAY
				try {
					if(rs1.next()){
						//out.print(rs1.getString(4));
						z=rs1.getInt(1);
					}
					rs1.getStatement().getConnection().close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				System.out.println("YYYYYYYYY: "+y+" ZZZZZZZZZZZZZZZ"+z);
				
				
				if(y==z){//MOSTRAMOS TODOS LOS SUBCONCEPTOS CREADOS DEL PACIENTE YA QUE SON IGUALES A MOVIMIENTOS
				int cj=0;	
			
			
			if(dbn1.equals("0")){
			out.print("<table width='100%' ><tr  bgcolor='#A4A3A3' ><td colspan='4'><b><div align='lefth' >DEVENGADOS</div></b></td></tr></table>");
			out.print("<table width='100%' border='0' class='style6'><tr   bgcolor='#E0DDDD' ><td width='60%'><u><i><div align='left'>Descripcion</div></i></u></td><td width='10%'><u><i><div align='center'>Modalidad</div></i></u></td><td width='10%'><u><i><div align='center'>Cantidad</div></i></u></td><td width='10%'><u><i><div align='center'>Valor total</div></i></u></td><td width='10%'><u><i><div align='center'>Estado</div></i></u></td></tr>");
		
			
			rs1=mn.ConsultarDevengados(codm,dbn1);
				try {
				while(rs1.next()){
					float vt=Float.parseFloat(rs1.getString(3))*Float.parseFloat(rs1.getString(4));
					out.print("<tr><td width='12%'><label><input type='text' id='dbn3' style='width:100%' value='"+rs1.getString(8)+"' readonly='readonly' /></label></td>");
					out.print("<td width='12%'><label><input type='text' id='dbn3' style='width:100%' value='"+rs1.getString(18)+"' readonly='readonly' /></label></td>");
					if(rs1.getString(17).equals("1")){
						System.out.println("1111111111111");
						out.print("<td width='12%'><label><input type='text' id='valor0"+cj+"' style='width:100%' value='"+rs1.getString(3)+"' onkeyup='checknum("+cj+")' onblur='vtotal("+cj+","+rs1.getString(11)+","+rs1.getString(1)+",1,"+codm+",2)' /></label></td>");
						out.print("<td width='12%'><label><input type='text' id='vt"+cj+"' style='width:100%' value='"+rs1.getString(4)+"' readonly='readonly' /></label></td>");
					}
					if(rs1.getString(17).equals("2")){
						System.out.println("222222222222");
						out.print("<td width='12%'><label><input type='text' id='valor0"+cj+"' style='width:100%' value='"+rs1.getString(3)+"' onkeyup='checknum("+cj+")' onblur='vtotal("+cj+","+rs1.getString(11)+","+rs1.getString(1)+",2,"+codm+",2)' /></label></td>");
					out.print("<td width='12%'><label><input type='text' id='vt"+cj+"' style='width:100%' value='"+vt+"' readonly='readonly' /></label></td>");
					}
					if(rs1.getString(17).equals("3")){
						System.out.println("3333333333333");
						out.print("<td width='12%'><label><input type='text' id='valor0"+cj+"' style='width:100%' value='"+rs1.getString(3)+"' onkeyup='checknum("+cj+")' onblur='vtotal("+cj+","+rs1.getString(11)+","+rs1.getString(1)+",3,"+codm+",2)' /></label></td>");
					out.print("<td width='12%'><label><input type='text' id='vt"+cj+"' style='width:100%' value='"+vt+"' readonly='readonly' /></label></td>");
					}
					out.print("<td width='12%'><label><input type='text' id='dbn3' style='width:100%' value='S'  readonly='readonly'/></label></td></tr>");
					cj++;
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			}//fin dbn1=0
			
			
			if(dbn1.equals("1")){
				
				////BUSCAMOS EL VALOR DEL AUXILIO DE TRANSPORTE
				
				rs1=mn.ConsultarAuxilioTransporte();//CONSULTAMOS CUANTOS SUBCONCEPTOS HAY
				try {
					if(rs1.next()){
						//out.print(rs1.getString(4));
						at=rs1.getString(1);
					}
					rs1.getStatement().getConnection().close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			////BUSCAMOS EL SUELDO DIARIO
				
				rs1=mn.ConsultarSueldoDiario(code);//CONSULTAMOS CUANTOS SUBCONCEPTOS HAY
				try {
					if(rs1.next()){
						//out.print(rs1.getString(4));
						sd=rs1.getString(1);
					}
					rs1.getStatement().getConnection().close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				////BUSCAMOS LOS DIAS TRABAJADOS
				
				rs1=mn.ConsultarDiastrabajados(codm);//CONSULTAMOS CUANTOS SUBCONCEPTOS HAY
				try {
					if(rs1.next()){
						//out.print(rs1.getString(4));
						dtr=rs1.getString(1);
					}
					rs1.getStatement().getConnection().close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				
				out.print("<table width='100%' ><tr ><td colspan='4'>Nro de Dias trabajados: <input type='text' id='valor050' style='width:5%'  value='"+dtr+"' onkeyup='checknum(50),dt("+at+","+sd+","+codm+")' /></label></td></tr></table>");
			//	apenas escriban el homblur debe calcular los valores fijos y listo
				out.print("<table width='100%' ><tr  bgcolor='#A4A3A3' ><td colspan='4'><b><div align='lefth' >DEVENGADOS</div></b></td></tr></table>");
				out.print("<table width='100%' border='0' class='style6'><tr   bgcolor='#E0DDDD' ><td width='60%'><u><i><div align='left'>Descripcion</div></i></u></td><td width='10%'><u><i><div align='center'>Modalidad</div></i></u></td><td width='10%'><u><i><div align='center'>Cantidad</div></i></u></td><td width='10%'><u><i><div align='center'>Valor total</div></i></u></td><td width='10%'><u><i><div align='center'>Estado</div></i></u></td></tr>");
			
				
				rs1=mn.ConsultarDevengados(codm,dbn1);
					try {
					while(rs1.next()){
						//int vt=Integer.parseInt(rs1.getString(3))*Integer.parseInt(rs1.getString(4));
						out.print("<tr><td width='12%'><label><input type='text' id='dbn3' style='width:100%' value='"+rs1.getString(8)+"' readonly='readonly' /></label><input type='hidden' id='codm"+cj+"' value='"+rs1.getString(1)+"'/></td>");
						out.print("<td width='12%'><label><input type='text' id='dbn3' style='width:100%' value='"+rs1.getString(18)+"' readonly='readonly' /></label></td>");
						out.print("<td width='12%'><label><input type='text' id='valor0"+cj+"' style='width:100%' value='"+rs1.getString(3)+"'    /></label></td>");
						out.print("<td width='12%'><label><input type='text' id='vt"+cj+"' style='width:100%' value='"+rs1.getString(4)+"' readonly='readonly' /></label></td>");
						out.print("<td width='12%'><label><input type='text' id='dbn3' style='width:100%' value='S'  readonly='readonly'/></label></td></tr>");
						cj++;
					}
					rs1.getStatement().getConnection().close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				}//fin dbn1=0
			
			
			out.print("<tr></tr><tr></tr><tr></tr><tr></tr>");
			out.print("</table>");
			
			
			if(dbn1.equals("0")){
			out.print("<table width='100%' ><tr  bgcolor='#A4A3A3' ><td colspan='4'><b><div align='lefth' >DEDUCCIONES</div></b></td></tr></table>");
			out.print("<table width='100%' border='0' class='style6'><tr   bgcolor='#E0DDDD' ><td width='60%'><u><i><div align='left'>Descripcion</div></i></u></td><td width='10%'><u><i><div align='center'>Modalidad</div></i></u></td><td width='10%'><u><i><div align='center'>Cantidad</div></i></u></td><td width='10%'><u><i><div align='center'>Valor total</div></i></u></td><td width='10%'><u><i><div align='center'>Estado</div></i></u></td></tr>");
			

			rs1=mn.ConsultarDeducciones(codm,dbn1);
			try {
				while(rs1.next()){
					float vt=Float.parseFloat(rs1.getString(3))*Float.parseFloat(rs1.getString(4));
					out.print("<tr><td width='12%'><label><input type='text' id='dbn3' style='width:100%' value='"+rs1.getString(8)+"' readonly='readonly' /></label></td>");
					out.print("<td width='12%'><label><input type='text' id='dbn3' style='width:100%' value='"+rs1.getString(18)+"' readonly='readonly' /></label></td>");
					if(rs1.getString(17).equals("1")){
						System.out.println("1111111111111");
						out.print("<td width='12%'><label><input type='text' id='valor0"+cj+"' style='width:100%' value='"+rs1.getString(3)+"' onkeyup='checknum("+cj+")' onblur='vtotal("+cj+","+rs1.getString(11)+","+rs1.getString(1)+",1,"+codm+",1)' /></label></td>");
						out.print("<td width='12%'><label><input type='text' id='vt"+cj+"' style='width:100%' value='"+rs1.getString(4)+"' readonly='readonly' /></label></td>");
					}
					if(rs1.getString(17).equals("2")){
						System.out.println("222222222222");
						out.print("<td width='12%'><label><input type='text' id='valor0"+cj+"' style='width:100%' value='"+rs1.getString(3)+"' onkeyup='checknum("+cj+")' onblur='vtotal("+cj+","+rs1.getString(11)+","+rs1.getString(1)+",2,"+codm+",1)' /></label></td>");
					out.print("<td width='12%'><label><input type='text' id='vt"+cj+"' style='width:100%' value='"+vt+"' readonly='readonly' /></label></td>");
					}
					if(rs1.getString(17).equals("3")){
						System.out.println("3333333333333");
						out.print("<td width='12%'><label><input type='text' id='valor0"+cj+"' style='width:100%' value='"+rs1.getString(3)+"' onkeyup='checknum("+cj+")' onblur='vtotal("+cj+","+rs1.getString(11)+","+rs1.getString(1)+",3,"+codm+",1)' /></label></td>");
					out.print("<td width='12%'><label><input type='text' id='vt"+cj+"' style='width:100%' value='"+vt+"' readonly='readonly' /></label></td>");
					}
					
					
					out.print("<td width='12%'><label><input type='text' id='dbn3' style='width:100%' value='S' readonly='readonly' /></label></td></tr>");
					cj++;
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			out.print("</table>");
			
			}//fin dbn1==0
			
			if(dbn1.equals("1")){
				out.print("<table width='100%' ><tr  bgcolor='#A4A3A3' ><td colspan='4'><b><div align='lefth' >DEDUCCIONES</div></b></td></tr></table>");
				out.print("<table width='100%' border='0' class='style6'><tr   bgcolor='#E0DDDD' ><td width='60%'><u><i><div align='left'>Descripcion</div></i></u></td><td width='10%'><u><i><div align='center'>Modalidad</div></i></u></td><td width='10%'><u><i><div align='center'>Cantidad</div></i></u></td><td width='10%'><u><i><div align='center'>Valor total</div></i></u></td><td width='10%'><u><i><div align='center'>Estado</div></i></u></td></tr>");
				

				rs1=mn.ConsultarDeducciones(codm,dbn1);
				try {
					while(rs1.next()){
						//int vt=Integer.parseInt(rs1.getString(3))*Integer.parseInt(rs1.getString(4));
						out.print("<tr><td width='12%'><label><input type='text' id='dbn3' style='width:100%' value='"+rs1.getString(8)+"' readonly='readonly' /></label></td>");
						out.print("<td width='12%'><label><input type='text' id='dbn3' style='width:100%' value='"+rs1.getString(18)+"' readonly='readonly' /></label></td>");
						out.print("<td width='12%'><label><input type='text' id='valor0"+cj+"' style='width:100%' value='"+rs1.getString(3)+"'   /></label></td>");
						out.print("<td width='12%'><label><input type='text' id='vt"+cj+"' style='width:100%'  value='"+rs1.getString(4)+"'  readonly='readonly'/></label></td>");
						out.print("<td width='12%'><label><input type='text' id='dbn3' style='width:100%' value='S' readonly='readonly' /></label></td></tr>");
						cj++;
					}
					rs1.getStatement().getConnection().close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				out.print("</table>");
				
				
				
			//	out.print("<table width='100%' ><tr ></tr><tr ></tr><tr ></tr><tr><td colspan='4'><div align='center'> <input type='button' name='btnCrearIva' id='btnCrearIva' value='Actualizar' onClick='ActualizaFijo("+at+","+sd+","+codm+")'>   </div></label></td></tr></table>");
			
				
				}//fin dbn1==0
			
      /***   falta actualizar y mostrar ya cree**/
				}else{///y==z //hay que actualizar
					int w=0;
					rs1=mn.ConsultarMovimientosSubconceptos(codm);//CONSULTAMOS LOS SUBCONSECTOS QUE FALTAN EN MOVIEMIENTOS
					try {
						while(rs1.next()){
							//codm=rs1.getString(1);
							System.out.println(w+". Actualizamos los subconceptos que faltan al detalle "+rs1.getString(1));
							w++;
							mn.CrearDetalleMovimiento(codm,"0","0",rs1.getString(1));
						}
						rs1.getStatement().getConnection().close();
					} catch (SQLException e) {
						e.printStackTrace();
					}				
					out.print("1");//Volvemos a refrescar la pagina
				}//fin sino iguales mov y subconceptos
			}else{//fin x!=0	//hay que ingresar todo los movimientos
				mn.CrearMovimiento(tc,code,fechacjmysql,hra,usu);
				codm="";
				rs1=mn.ConsultarMovimientos(tc,code);//CONSULTAMOS SI HAY MOVIMIENTOS
				try {
					if(rs1.next()){
						codm=rs1.getString(1);
					}
					rs1.getStatement().getConnection().close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				
				rs1=mn.ConsultarCodigosSubconceptos();//CONSULTAMOS SI HAY MOVIMIENTOS
				try {
					while(rs1.next()){
						System.out.println("Insertamos los subconceptos al detalle 1 a 1 "+rs1.getString(1));
						mn.CrearDetalleMovimiento(codm,"0","0",rs1.getString(1));
					}
					rs1.getStatement().getConnection().close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				out.print("1");//Volvemos a refrescar la pagina
			}//sino x!=0
		}
		
		
		if(va.equals("9.1")){
		    mn.ActualizarNdetallemovimientos(tc,dbn2,dbn3,dbn1);
	
		    rs1=mn.ConsultarSumMovimientos(sig,"0",dod);//CONSULTAMOS LA SUMA DE LOS MOVIMIENTOS
			try {
				if(rs1.next()){
					System.out.println("EEEEEEE "+rs1.getString(1));
					mn.ActualizarNTotalesNovedades(rs1.getString(1),sig,dod);//Actualizamos las novedades devengadas y deducibles
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		    
		}
		 
		
		if(va.equals("9.2")){
			
			 mn.ActualizarNdetallemovimientos(dbn1,dbn1,dbn1,dbn3);
			 mn.ActualizarNdetallemovimientos(dbn2,dbn2,dbn2,dbn4);
			 //dbn5 dias trabajaados
			
			// float syp=0;
			 
			 rs1=mn.ConsultarSumMovimientos(sig,"1","2");//CONSULTAMOS LA SUMA DE LOS MOVIMIENTOS AQUI PONGO POR DEFECTO 2 Q SON DEVENGADOS FIJOS
				try {//SI HACE FALTA UN DEDUCIBLE FIJO HAY QUE DESARROLLAR DESDE EL PRINCIPIO
					if(rs1.next()){
						mn.ActualizarNTotalesFijas(rs1.getString(1),sig,"2",dbn5);//Actualizamos las novedades devengadas y deducibles
						//syp=Float.parseFloat(rs1.getString(1))-Float.parseFloat(dbn2);
					}
					rs1.getStatement().getConnection().close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			 
				 float tdev=0;
				 rs1=mn.ConsultarTotalDevengados(sig);//CONSULTAMOS LA SUMA DE LOS MOVIMIENTOS AQUI PONGO POR DEFECTO 2 Q SON DEVENGADOS FIJOS
					try {//SI HACE FALTA UN DEDUCIBLE FIJO HAY QUE DESARROLLAR DESDE EL PRINCIPIO
						if(rs1.next()){
							tdev=Float.parseFloat(rs1.getString(1));
						}
						rs1.getStatement().getConnection().close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
					double imp=(tdev-Float.parseFloat(dbn2))*0.04;//Hay q verificar si este 4% es dinamico y parametrizable
					
					mn.ActualizarNTotalesFijas(String.valueOf(imp*2),sig,"1",dbn5);//Actualizamos las novedades devengadas y deducibles
					
				out.print(imp);
			}
		
		if(va.equals("10")){	
			
			out.print("<table width='100%' class='style6'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Preliquidacion de Nomina</div></td></tr>");
			
			out.print("<tr><td width='12%'>Periodo</td><td><label><select  style='width:400px' name='tc' id='tc' onChange='Periodo()' >");
			
			String x="";
			rs1=mn.ConsultarPeriodosNomina();
			try {
				while(rs1.next()){
					out.print("<option value='"+rs1.getString(1)+"'>"+rs1.getString(2)+"</option>");
					if(x.equals("")){
					 x=rs1.getString(4);
					}
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			//out.print("</label>");
			out.print("<td width='12%'>Fecha</td><td><label><input name='txtNombreIva' type='text' id='dbn1' value='"+x+"' size='48' readonly='readonly' /></label></td></tr>");
			// 
			
			
			out.print("<tr><td width='12%'>Centro de costo</td><td><label><select  style='width:323px' id='dbn8' onchange='subcentrosnomina()'><option value='Seleccione'>Seleccione</option>");
			rs1=mn.ConsultarCentrosdeCosto();
			try {
				while(rs1.next()){
					out.print("<option title='"+rs1.getString(2)+"' value='"+rs1.getString(1)+"'>"+rs1.getString(2)+"</option>");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			out.print("</label>");
			
			out.print("<td width='12%'>Subcentro de costo </td><td><div id='cco'><label><select  style='width:323px' id='dbn9' ><option value='Seleccione'>Seleccione</option></selet></label></div></td></tr>");
			
			
		//	out.print("<tr><td width='12%'>Empleado</td><td><label><input name='txtNombreIva' type='text' id='desc0' onfocus='limpiarmovimientos(0)' onkeyup='autocompletaEmpleado(0,0,"+dbn1+")'  size='61' /></label><input type='hidden' id='code'/><input type='hidden' id='sal'/></td>");
		//	out.print("<td width='12%'>Identificacion</td><td><label><input name='txtNombreIva' type='text' id='dbn3'  size='48' readonly='readonly'/></label></td></tr>");
		//	out.print("<tr><td></td><td><div id='sugerencias10' ></div></td></tr>");
			out.print("<tr></tr><tr></tr><tr></tr><tr></tr>");
	
		}
		
		if(va.equals("10.1")){	
			
			out.print("<td><label><select  style='width:323px' id='dbn9' onchange='Pacientesxcentro()' ><option value='Seleccione'>Seleccione</option>");
			rs1=mn.ConsultarSubCentrosdeCosto(dbn8);
			try {
				while(rs1.next()){
					out.print("<option title='"+rs1.getString(2)+"' value='"+rs1.getString(1)+"'>"+rs1.getString(2)+"</option>");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			out.print("</label></td></tr>");
			
			}
		
		if(va.equals("10.2")){	
		//out.print("<table width='100%' ><tr  bgcolor='#A4A3A3' ><td colspan='4'><b><div align='lefth' >DEVENGADOS</div></b></td></tr></table>");
		
		out.print("<table width='100%' border='0' class='style6'><tr   bgcolor='#A4A3A3' ><td width='38%' colspan='2'></td><td width='24%'  colspan='3'><div align='center'>DEVENGADOS</div></td><td width='24%'  colspan='3' ><div align='center'>DEDUCCIONES</div></td><td width='10%'  colspan='3' ><div align='center'>Totales</div></td></tr>");
		out.print("<tr   bgcolor='#A4A3A3' ><td width='8%'><u><i><div align='left'>Identificacion</div></i></u></td><td width='30%'><u><i><div align='center'>Nombre</div></i></u></td><td width='8%'><u><i><div align='center'>Fijos</div></i></u></td><td width='8%'><u><i><div align='center'>Novedades</div></i></u></td><td width='8%'><u><i><div align='center'>Total</div></i></u></td><td width='8%'><u><i><div align='center'>Fijos</div></i></u></td><td width='8%'><u><i><div align='center'>Novedades</div></i></u></td><td width='8%'><u><i><div align='center'>Total</div></i></u></td><td width='10%'><u><i><div align='center'>Neto</div></i></u></td></tr>");
		
		
		int cj=0;
		rs1=mn.ConsultarEmpleadosxCentroNovedades(dbn8,dbn9,tc);
			try {
			while(rs1.next()){
				float vtfndev=0;
				float vtfnded=0;
				float neto=0;
				if((cj%2)==0){out.print("<tr >");	}else{out.print("<tr  bgcolor='#E0DDDD'>");}	
				
				out.print("<td ><label>"+rs1.getString(2)+"</label></td>");
				out.print("<td ><label>"+rs1.getString(5)+" "+rs1.getString(6)+" "+rs1.getString(3)+" "+rs1.getString(4)+"</label></td>");
				out.print("<td ><label>"+rs1.getString(7)+"</label></td>");
				out.print("<td ><label>"+rs1.getString(8)+"</label></td>");
				vtfndev=Float.parseFloat(rs1.getString(7))+Float.parseFloat(rs1.getString(8));
				out.print("<td ><label>"+vtfndev+"</label></td>");
				out.print("<td ><label>"+rs1.getString(9)+"</label></td>");
				out.print("<td ><label>"+rs1.getString(10)+"</label></td>");
				vtfnded=Float.parseFloat(rs1.getString(9))+Float.parseFloat(rs1.getString(10));
				out.print("<td ><label>"+vtfnded+"</label></td>");
				neto=vtfndev-vtfnded;
				out.print("<td ><label>"+neto+"</label></td></tr>");
				cj++;
			}
			rs1.getStatement().getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		out.print("<table width='100%' ><tr ></tr><tr ></tr><tr ></tr><tr><td colspan='4'><div align='center'> <input type='button' name='btnCrearIva' id='btnCrearIva' value='Liquidar' onClick='LiquidaN("+dbn8+","+dbn9+","+tc+")'>   </div></label></td></tr></table>");
		
		}
		
		if(va.equals("10.3")){
			
			String at="";
			rs1=mn.ConsultarAuxilioTransporte();//CONSULTAMOS CUANTOS SUBCONCEPTOS HAY
			try {
				if(rs1.next()){
					at=rs1.getString(1);
					//System.out.println("aatatatattat "+at);
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			rs1=mn.ConsultarEmpleadosParaProvisiones(dbn8,dbn9,tc);
			try {
			while(rs1.next()){
				
				int sd=0;
				rs2=mn.ConsultarSueldoDiario(rs1.getString(2));//CONSULTAMOS CUANTOS SUBCONCEPTOS HAY
				try {
					if(rs2.next()){
						//out.print(rs1.getString(4));
						sd=rs2.getInt(1);
					}
					rs2.getStatement().getConnection().close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				System.out.println("sdsdsdsdsd "+sd);
				
				float auxt=Float.parseFloat(at)*Integer.parseInt(rs1.getString(7));
							
				double salud=((Float.parseFloat(rs1.getString(3))+Float.parseFloat(rs1.getString(4)))-auxt)*0.085;
				double pension=((Float.parseFloat(rs1.getString(3))+Float.parseFloat(rs1.getString(4)))-auxt)*0.12;
				double cesantias=((Float.parseFloat(rs1.getString(3))+Float.parseFloat(rs1.getString(4)))-auxt)*0.0833333;
				double intcesantias=cesantias*0.12;
				double primas=((Float.parseFloat(rs1.getString(3))+Float.parseFloat(rs1.getString(4)))-auxt)*0.0833333;
				double vacaciones=(sd/2)*0.0417;
				double cajacomp=((Float.parseFloat(rs1.getString(3))+Float.parseFloat(rs1.getString(4)))-auxt)*0.04;
				double icbf=((Float.parseFloat(rs1.getString(3))+Float.parseFloat(rs1.getString(4)))-auxt)*0.03;
				double sena=((Float.parseFloat(rs1.getString(3))+Float.parseFloat(rs1.getString(4)))-auxt)*0.02;
				double arp=((Float.parseFloat(rs1.getString(3))+Float.parseFloat(rs1.getString(4)))-auxt)*0.0696;
				
				
				mn.CrearProvision(rs1.getString(1),tc,rs1.getString(2),String.valueOf(salud),String.valueOf(pension),String.valueOf(cesantias),String.valueOf(intcesantias),String.valueOf(primas),String.valueOf(vacaciones),String.valueOf(cajacomp),String.valueOf(icbf),String.valueOf(sena),String.valueOf(arp)); 
					
				
			}
			rs1.getStatement().getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
		
		if(va.equals("11")){
			out.print("<table width='100%' class='style6'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Interfaz Contable de Nomina</div></td></tr>");
			
			out.print("<tr><td width='12%'>Centro de costo</td><td><label><select  style='width:323px' id='dbn8' onchange='subcentrosinomina()'><option value='Seleccione'>Seleccione</option>");
			rs1=mn.ConsultarCentrosdeCosto();
			try {
				while(rs1.next()){
					out.print("<option title='"+rs1.getString(2)+"' value='"+rs1.getString(1)+"'>"+rs1.getString(2)+"</option>");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			out.print("</label>");
			
			out.print("<td width='12%'>Subcentro de costo </td><td><div id='cco'><label><select  style='width:323px' id='dbn9' ><option value='Seleccione'>Seleccione</option></selet></label></div></td></tr>");
		}
		
		if(va.equals("11.1")){	
			
			out.print("<td><label><select  style='width:323px' id='dbn9' onchange='InterfazNomina()' ><option value='Seleccione'>Seleccione</option>");
			rs1=mn.ConsultarSubCentrosdeCosto(dbn8);
			try {
				while(rs1.next()){
					out.print("<option title='"+rs1.getString(2)+"' value='"+rs1.getString(1)+"'>"+rs1.getString(2)+"</option>");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			out.print("</label></td></tr>");
		}
		
		if(va.equals("11.2")){	
			
		out.print("<table width='100%' ><tr  bgcolor='#A4A3A3' ><td colspan='3'><b><div align='lefth' >DEVENGADOS</div></b></td></tr></table>");
		out.print("<table width='100%' border='0' class='style6'><tr   bgcolor='#E0DDDD' ><td width='40%'><u><i><div align='left'>Subconcepto</div></i></u></td><td width='20%'><u><i><div align='center'>Cuenta</div></i></u></td><td width='40%'><u><i><div align='center'>Nombre de la Cuenta</div></i></u></td></tr>");
		
		int u=0;
		rs1=mn.ConsultarSubConceptosNomina("2");
		try {
			while(rs1.next()){
				
				out.print("<tr><td><label><input type='text' id='sub"+u+"' style='width:100%' value='"+rs1.getString(2)+"' readonly='readonly' /><input type='hidden' id='hsub"+u+"' value='"+rs1.getString(1)+"'/></label></td>");
				
				int rr=0;
				rs2=mn.ConsultarInterfazdeNomina(rs1.getString(1),dbn8,dbn9);
				try {
					if(rs2.next()){
						out.print("<td><label><input type='text' id='cta"+u+"' style='width:100%' value='"+rs2.getString(2)+"' onkeyup='autocompletarCuentas("+u+",1)'  /></label></td>");
						out.print("<td><label><input type='text' id='ncta"+u+"' style='width:100%' value='"+rs2.getString(3)+"' readonly='readonly' /></label></td>");
						rr=1;
					}
					rs2.getStatement().getConnection().close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				if(rr==0){
					out.print("<td><label><input type='text' id='cta"+u+"' style='width:100%'  onkeyup='autocompletarCuentas("+u+",1)'  /></label></td>");
					out.print("<td><label><input type='text' id='ncta"+u+"' style='width:100%' readonly='readonly'/></label></td>");
				}
				
				out.print("</tr>");
				out.print("<tr><td></td><td><div name='CuentasN' id='Cuentas"+u+"' ></div></td><td></td></tr>");
				
				u++;
			}
			rs1.getStatement().getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		out.print("<tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr></table>");
		out.print("<table width='100%' ><tr  bgcolor='#A4A3A3' ><td colspan='3'><b><div align='lefth' >DEDUCCIONES</div></b></td></tr></table>");
		out.print("<table width='100%' border='0' class='style6'><tr   bgcolor='#E0DDDD' ><td width='40%'><u><i><div align='left'>Subconcepto</div></i></u></td><td width='20%'><u><i><div align='center'>Cuenta</div></i></u></td><td width='40%'><u><i><div align='center'>Nombre de la Cuenta</div></i></u></td></tr>");
		
		rs1=mn.ConsultarSubConceptosNomina("1");
		try {
			while(rs1.next()){
				
				out.print("<tr><td><label><input type='text' id='sub"+u+"' style='width:100%' value='"+rs1.getString(2)+"'   readonly='readonly' /><input type='hidden' id='hsub"+u+"' value='"+rs1.getString(1)+"'/></label></td>");
				
				int rr=0;
				rs2=mn.ConsultarInterfazdeNomina(rs1.getString(1),dbn8,dbn9);
				try {
					if(rs2.next()){
						out.print("<td><label><input type='text' id='cta"+u+"' style='width:100%' value='"+rs2.getString(2)+"' onkeyup='autocompletarCuentas("+u+",1)'  /></label></td>");
						out.print("<td><label><input type='text' id='ncta"+u+"' style='width:100%' value='"+rs2.getString(3)+"' readonly='readonly' /></label></td>");
						rr=1;
					}
					rs2.getStatement().getConnection().close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				if(rr==0){
					out.print("<td><label><input type='text' id='cta"+u+"' style='width:100%'  onkeyup='autocompletarCuentas("+u+",1)'  /></label></td>");
					out.print("<td><label><input type='text' id='ncta"+u+"' style='width:100%' readonly='readonly'/></label></td>");
				}
				
				out.print("</tr>");
				out.print("<tr><td></td><td><div name='CuentasN' id='Cuentas"+u+"' ></div></td><td></td></tr>");
				
				u++;
			}
			rs1.getStatement().getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		out.print("</table>");
		}
		
		if(va.equals("autoinv2")){
			
			try {
				rs =mn.NConsultarCuentasContables(texto);
				//System.out.print("ESSSTOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO."+texto);
				String cadena ="";
				cadena="[";
				while(rs.next()){
					cadena = cadena+"'"+rs.getString(1)+"|"+rs.getString(2)+"|"+rs.getString(3)+"'";
					cadena = cadena +",";	
				}
				cadena = cadena+"]";
				//System.out.print("ESSSTOO."+cadena);
				response.getWriter().write(cadena);
				rs.getStatement().getConnection().close();
			} catch (Exception e) {
				e.getMessage();
				e.printStackTrace();
			}
		}
		
		if(va.equals("11.3")){
			System.out.println(dbn8+" - "+dbn9+" - "+dbn10+" - "+dbn11+" - "+fechacjmysql+" - "+hra+" - "+usu);
			
			String cin="";
			rs1=mn.ConsultarInterfazN(dbn8,dbn9,dbn10);
			try {
				while(rs1.next()){
					cin=rs1.getString(1);
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			if(cin.equals("")){
			mn.CrearInterfazN(dbn8,dbn9,dbn10,dbn11,fechacjmysql,hra,usu);
			}else{
			mn.ActualizarInterfazN(cin,dbn11,fechacjmysql,hra,usu);
			}
		}
		
		
		if(va.equals("12")){
			out.print("<table width='100%' class='style6'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Interfaz Contable de Provisiones de Nomina</div></td></tr>");
			
			out.print("<tr><td width='12%'>Centro de costo</td><td><label><select  style='width:323px' id='dbn8' onchange='subcentrosinomina2()'><option value='Seleccione'>Seleccione</option>");
			rs1=mn.ConsultarCentrosdeCosto();
			try {
				while(rs1.next()){
					out.print("<option title='"+rs1.getString(2)+"' value='"+rs1.getString(1)+"'>"+rs1.getString(2)+"</option>");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			out.print("</label>");
			
			out.print("<td width='12%'>Subcentro de costo </td><td><div id='cco'><label><select  style='width:323px' id='dbn9' ><option value='Seleccione'>Seleccione</option></selet></label></div></td></tr>");
		}
		
		if(va.equals("12.1")){	
			
			out.print("<td><label><select  style='width:323px' id='dbn9' onchange='InterfazProvisiones()' ><option value='Seleccione'>Seleccione</option>");
			rs1=mn.ConsultarSubCentrosdeCosto(dbn8);
			try {
				while(rs1.next()){
					out.print("<option title='"+rs1.getString(2)+"' value='"+rs1.getString(1)+"'>"+rs1.getString(2)+"</option>");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			out.print("</label></td></tr>");
		}
		
		if(va.equals("12.2")){	
			
			//out.print("<table width='100%' ><tr  bgcolor='#A4A3A3' ><td colspan='3'><b><div align='lefth' >DEVENGADOS</div></b></td></tr></table>");
			out.print("<table width='100%' border='0' class='style6'><tr   bgcolor='#E0DDDD' ><td width='30%'><u><i><div align='left'>Descripcion</div></i></u></td><td width='10%'><u><i><div align='center'>Cuenta de Provision</div></i></u></td><td width='25%'><u><i><div align='center'>Nombre de la Cuenta</div></i></u></td><td width='10%'><u><i><div align='center'>Cuenta de Provision</div></i></u></td><td width='25%'><u><i><div align='center'>Nombre de la Cuenta</div></i></u></td></tr>");
			
			int u=0;
			rs1=mn.ConsultarProvisionesNomina();
			try {
				while(rs1.next()){
					
					out.print("<tr><td><label><input type='text' id='sub"+u+"' style='width:100%' value='"+rs1.getString(2)+"'  readonly='readonly' /><input type='hidden' id='hsub"+u+"' value='"+rs1.getString(1)+"'/></label></td>");
					
					int rr=0;
					rs2=mn.ConsultarInterfazdeProvisiones(rs1.getString(1),dbn8,dbn9);
					try {
						if(rs2.next()){
							out.print("<td><label><input type='text' id='cta"+u+"' style='width:100%' value='"+rs2.getString(2)+"' onkeyup='autocompletarCuentas("+u+",2)' /></label></td>");
							out.print("<td><label><input type='text' id='ncta"+u+"' style='width:100%' value='"+rs2.getString(3)+"' readonly='readonly' /></label></td>");
							out.print("<td><label><input type='text' id='ctag"+u+"' style='width:100%' value='"+rs2.getString(4)+"' onkeyup='autocompletarCuentas("+u+",3)' /></label></td>");
							out.print("<td><label><input type='text' id='nctag"+u+"' style='width:100%' value='"+rs2.getString(5)+"' readonly='readonly' /></label></td>");
							rr=1;
						}
						rs2.getStatement().getConnection().close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
					if(rr==0){
						out.print("<td><label><input type='text' id='cta"+u+"' style='width:100%'  onkeyup='autocompletarCuentas("+u+",2)' /></label></td>");
						out.print("<td><label><input type='text' id='ncta"+u+"' style='width:100%' readonly='readonly'/></label></td>");
						out.print("<td><label><input type='text' id='ctag"+u+"' style='width:100%'  onkeyup='autocompletarCuentas("+u+",3)' /></label></td>");
						out.print("<td><label><input type='text' id='nctag"+u+"' style='width:100%' readonly='readonly'/></label></td>");
					}
					
					out.print("</tr>");
					out.print("<tr><td></td><td><div name='CuentasN' id='Cuentas"+u+"' ></div></td><td></td><td><div name='CuentasN' id='CuentasG"+u+"' ></div></td><td></td></tr>");
					
					u++;
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			out.print("</table>");
			
			}
		
		
		if(va.equals("12.3")){
			System.out.println(dbn8+" - "+dbn9+" - "+dbn10+" - "+dbn11+" - "+fechacjmysql+" - "+hra+" - "+usu);
			
			String cin="";
			rs1=mn.ConsultarInterfazP(dbn8,dbn9,dbn10);
			try {
				while(rs1.next()){
					cin=rs1.getString(1);
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			if(cin.equals("")){
			mn.CrearInterfazP(indice,dbn8,dbn9,dbn10,dbn11,fechacjmysql,hra,usu);
			}else{
			mn.ActualizarInterfazP(indice,cin,dbn11,fechacjmysql,hra,usu);
			}
		}
		
		
		if(va.equals("13")){
			out.print("<table width='100%' class='style6'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Interfaz Contable de Facturacion</div></td></tr>");
			
			out.print("<tr><td width='12%'>Centro de costo</td><td><label><select  style='width:323px' id='dbn8' onchange='subcentrosinomina3()'><option value='Seleccione'>Seleccione</option>");
			rs1=mn.ConsultarCentrosdeCosto();
			try {
				while(rs1.next()){
					out.print("<option title='"+rs1.getString(2)+"' value='"+rs1.getString(1)+"'>"+rs1.getString(2)+"</option>");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			out.print("</label>");
			
			out.print("<td width='12%'>Subcentro de costo </td><td><div id='cco'><label><select  style='width:323px' id='dbn9' ><option value='Seleccione'>Seleccione</option></selet></label></div></td></tr>");
		}
		
		if(va.equals("13.1")){	
			
			out.print("<td><label><select  style='width:323px' id='dbn9' onchange='InterfazFacturacion()' ><option value='Seleccione'>Seleccione</option>");
			rs1=mn.ConsultarSubCentrosdeCosto(dbn8);
			try {
				while(rs1.next()){
					out.print("<option title='"+rs1.getString(2)+"' value='"+rs1.getString(1)+"'>"+rs1.getString(2)+"</option>");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			out.print("</label></td></tr>");
		}
		
		
		if(va.equals("13.2")){	
			System.out.println("dbn10dbn10dbn10dbn10dbn10 "+dbn10);
			if((dbn10==null)||(dbn10.equals("1"))){ dbn10="A"; }//cambiar a A
			if(dbn10.equals("2")){ dbn10="B"; }
			if(dbn10.equals("3")){ dbn10="C"; }
			if(dbn10.equals("4")){ dbn10="D"; }
			if(dbn10.equals("5")){ dbn10="E"; }
			if(dbn10.equals("6")){ dbn10="F"; }
			if(dbn10.equals("7")){ dbn10="G"; }
			if(dbn10.equals("8")){ dbn10="H"; }
			if(dbn10.equals("9")){ dbn10="I"; }
			if(dbn10.equals("10")){ dbn10="J"; }
			if(dbn10.equals("11")){ dbn10="K"; }
			if(dbn10.equals("12")){ dbn10="L"; }
			if(dbn10.equals("13")){ dbn10="M"; }
			if(dbn10.equals("14")){ dbn10="N"; }
			if(dbn10.equals("15")){ dbn10="O"; }
			if(dbn10.equals("16")){ dbn10="P"; }
			if(dbn10.equals("17")){ dbn10="Q"; }
			if(dbn10.equals("18")){ dbn10="R"; }
			if(dbn10.equals("19")){ dbn10="S"; }
			if(dbn10.equals("20")){ dbn10="T"; }
			if(dbn10.equals("21")){ dbn10="U"; }
			if(dbn10.equals("22")){ dbn10="V"; }
			if(dbn10.equals("23")){ dbn10="W"; }
			if(dbn10.equals("24")){ dbn10="X"; }
			if(dbn10.equals("25")){ dbn10="Y"; }
			if(dbn10.equals("26")){ dbn10="Z"; }
			
			System.out.println("dbn10dbn10dbn10dbn10dbn10 "+dbn10);
			out.print("<table width='100%' border='0' class='style6'><tr><td><div align='center'>-<label onCLick='A(1)'> A </label>-<label onCLick='A(2)'> B </label>-<label onCLick='A(3)'> C </label>-<label onCLick='A(4)'> D </label>-<label onCLick='A(5)'> E </label>-<label onCLick='A(6)'> F </label>-<label onCLick='A(7)'> G </label>-<label onCLick='A(8)'> H </label>-<label onCLick='A(9)'> I </label>-<label onCLick='A(10)'> J </label>-<label onCLick='A(11)'> K </label>-<label onCLick='A(12)'> L </label>-<label onCLick='A(13)'> M </label>-<label onCLick='A(14)'> N </label>-<label onCLick='A(15)'> O </label>-<label onCLick='A(16)'> P </label>-<label onCLick='A(17)'> Q </label>-<label onCLick='A(18)'> R </label>-<label onCLick='A(19)'> S </label>-<label onCLick='A(20)'> T </label>-<label onCLick='A(21)'> U </label>-<label onCLick='A(22)'> V </label>-<label onCLick='A(23)'> W </label>-<label onCLick='A(24)'> X </label>-<label onCLick='A(25)'> Y </label>-<label onCLick='A(26)'> Z </label>-</div></td></tr>");
			out.print("<table width='100%' border='0' class='style6'><tr   bgcolor='#E0DDDD' ><td width='60%'><u><i><div align='left'>Programa</div></i></u></td><td width='10%'><u><i><div align='center'>Cuenta de Ingreso</div></i></u></td><td width='10%'><u><i><div align='center'>Cuenta de Costo</div></i></u></td><td width='10%'><u><i><div align='center'>Cuenta de Inventario</div></i></u></td></tr>");
			
			int u=0;
			rs1=mn.ConsultarProgramasdeFacturacion(dbn10);
			try {
				while(rs1.next()){
					
					out.print("<tr><td><label><input type='text' id='sub"+u+"' style='width:100%' value='"+rs1.getString(2)+"' readonly='readonly' /><input type='hidden' id='hsub"+u+"' value='"+rs1.getString(1)+"'/></label></td>");
					
					/*int rr=0;
					rs2=mn.ConsultarInterfazdeFacturacion(rs1.getString(1),dbn8,dbn9);
					try {
						if(rs2.next()){
							out.print("<td><label title='"+rs2.getString(3)+"'><input type='text' id='cta"+u+"' style='width:100%' value='"+rs2.getString(2)+"' onkeyup='autocompletarCuentas("+u+",4,1)' /></label></td>");
							out.print("<td><label title='"+rs2.getString(5)+"'><input type='text' id='ctac"+u+"' style='width:100%' value='"+rs2.getString(4)+"' onkeyup='autocompletarCuentas("+u+",4,2)' /></label></td>");
							out.print("<td><label title='"+rs2.getString(7)+"'><input type='text' id='ctag"+u+"' style='width:100%' value='"+rs2.getString(6)+"' onkeyup='autocompletarCuentas("+u+",4,3)' /></label></td>");
							
							//out.print("<td><label><input type='text' id='ncta"+u+"' style='width:100%' value='"+rs2.getString(3)+"' readonly='readonly' /></label></td>");
							rr=1;
						}
						rs2.getStatement().getConnection().close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
					if(rr==0){
						out.print("<td><label><input type='text' id='cta"+u+"' style='width:100%'  onkeyup='autocompletarCuentas("+u+",4,1)'  /></label></td>");
						out.print("<td><label><input type='text' id='ctac"+u+"' style='width:100%'  onkeyup='autocompletarCuentas("+u+",4,2)'  /></label></td>");
						out.print("<td><label><input type='text' id='ctag"+u+"' style='width:100%'  onkeyup='autocompletarCuentas("+u+",4,3)'  /></label></td>");
						
						//out.print("<td><label><input type='text' id='ncta"+u+"' style='width:100%' readonly='readonly'/></label></td>");
					}
					*/
					
					int rr=0;
					rs2=mn.ConsultarInterfazdeFacturacion1(rs1.getString(1),dbn8,dbn9);
					try {
						if(rs2.next()){
							out.print("<td><label title='"+rs2.getString(3)+"'><input type='text' id='cta"+u+"' style='width:100%' value='"+rs2.getString(2)+"' onkeyup='autocompletarCuentas("+u+",4,1)' /></label></td>");
							rr=1;
						}
						rs2.getStatement().getConnection().close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					if(rr==0){
						out.print("<td><label><input type='text' id='cta"+u+"' style='width:100%'  onkeyup='autocompletarCuentas("+u+",4,1)'  /></label></td>");
					}
					
					int rr2=0;
					rs2=mn.ConsultarInterfazdeFacturacion2(rs1.getString(1),dbn8,dbn9);
					try {
						if(rs2.next()){
							out.print("<td><label title='"+rs2.getString(3)+"'><input type='text' id='ctac"+u+"' style='width:100%' value='"+rs2.getString(2)+"' onkeyup='autocompletarCuentas("+u+",4,2)' /></label></td>");
							rr2=1;
						}
						rs2.getStatement().getConnection().close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					if(rr2==0){
						out.print("<td><label><input type='text' id='ctac"+u+"' style='width:100%'  onkeyup='autocompletarCuentas("+u+",4,2)'  /></label></td>");
					}
					
					int rr3=0;
					rs2=mn.ConsultarInterfazdeFacturacion3(rs1.getString(1),dbn8,dbn9);
					try {
						if(rs2.next()){
							out.print("<td><label title='"+rs2.getString(3)+"'><input type='text' id='ctag"+u+"' style='width:100%' value='"+rs2.getString(2)+"' onkeyup='autocompletarCuentas("+u+",4,3)' /></label></td>");
							rr3=1;
						}
						rs2.getStatement().getConnection().close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					if(rr3==0){
						out.print("<td><label><input type='text' id='ctag"+u+"' style='width:100%'  onkeyup='autocompletarCuentas("+u+",4,3)'  /></label></td>");
					}
					
					
					
					out.print("</tr>");
					out.print("<tr><td></td><td><div  id='Cuentas"+u+"' ></div></td><td><div  id='Cuentasc"+u+"' ></div></td><td><div  id='Cuentasg"+u+"' ></div></td></tr>");
					
					u++;
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			out.print("</table>");
			}
		
		
		if(va.equals("13.3")){
			System.out.println(dbn8+" - "+dbn9+" - "+dbn10+" - "+dbn11+" - "+fechacjmysql+" - "+hra+" - "+usu);
			
			String cin="";
			rs1=mn.ConsultarInterfazF(dbn8,dbn9,dbn10);
			try {
				while(rs1.next()){
					cin=rs1.getString(1);
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			if(cin.equals("")){
			mn.CrearInterfazF(dbn8,dbn9,dbn10,dbn11,fechacjmysql,hra,usu,dbn12);
			}else{
			mn.ActualizarInterfazF(cin,dbn11,fechacjmysql,hra,usu,dbn12);
			}
		}
		


				
		if(va.equals("15")){		
			out.print("<table width='100%' class='style6'><tr><td colspan='2'><div align='center' class='style11' id='cabecera2'>Parametros Iniciales de Contabilidad</div></td></tr>");
			
			int ct=0;
			rs = mn.ConsultarDatosiniciales();
			try {
				if(rs.next()){
					ct++;
					out.print("<tr><td width='22%'>Cuenta de Clientes</td><td><label><input type='text' id='cta1' size='35'  value='"+rs.getString(3)+"' onkeyup='autocompletarCuentas(1,1,0,1)'  /><input type='text' id='ncta1' style='width:40%' value='"+rs.getString(4)+"' readonly='readonly' /><input type='hidden' id='hcta1' value='"+rs.getString(2)+"'/></label></td></tr>");
					out.print("<tr><td></td><td><div name='CuentasN' id='Cuentas1' ></div></td><td></td></tr>");
					out.print("<tr><td width='22%'>Cuenta de Ingresos</td><td><label><input type='text' id='cta2' size='35' value='"+rs.getString(6)+"' onkeyup='autocompletarCuentas(2,1,0,1)' /><input type='text' id='ncta2' style='width:40%' value='"+rs.getString(7)+"' readonly='readonly' /><input type='hidden' id='hcta2' value='"+rs.getString(5)+"'/></label></td></tr>");
					out.print("<tr><td></td><td><div name='CuentasN' id='Cuentas2' ></div></td><td></td></tr>");
					out.print("<tr><td width='22%'>Cuenta de Copago</td><td><label><input type='text' id='cta3'' size='35' value='"+rs.getString(9)+"' onkeyup='autocompletarCuentas(3,1,0,1)' /><input type='text' id='ncta3' style='width:40%' value='"+rs.getString(10)+"' readonly='readonly' /><input type='hidden' id='hcta3' value='"+rs.getString(8)+"'/></label></td></tr>");
					out.print("<tr><td></td><td><div name='CuentasN' id='Cuentas3' ></div></td><td></td></tr>");
					out.print("<tr><td width='22%'>Cuenta de Cuota Moderadora</td><td><label><input id='cta4'  size='35'  value='"+rs.getString(12)+"'onkeyup='autocompletarCuentas(4,1,0,1)' /><input type='text' id='ncta4' style='width:40%' value='"+rs.getString(13)+"' readonly='readonly' /><input type='hidden' id='hcta4' value='"+rs.getString(11)+"'/></label></td></tr>");
					out.print("<tr><td></td><td><div name='CuentasN' id='Cuentas4' ></div></td><td></td></tr>");
					out.print("<tr><td></td><td></td><tr>");
					out.print("<tr><td></td><td></td><tr>");
					out.print("<tr><td></td><td></td><tr>");
					out.print("<tr><td width='22%'>Fecha Ultima Actualización</td><td><label>"+rs.getString(14)+"</label></td></tr>");
					out.print("<tr><td width='22%'>Hora Ultima Actualización</td><td><label>"+rs.getString(15)+"</label></td></tr>");
					
					rs1 = mn.ConsultarUsuario(rs.getString(16));
					if(rs1.next()){
					out.print("<tr><td width='22%'>Usuario Ultima Actualización</td><td><label>"+rs1.getString(1)+" "+rs1.getString(2)+"</label></td></tr>");
					}rs1.getStatement().getConnection().close();
					
					out.print("<tr></tr><tr></tr><tr></tr><tr><td></td><td><div align='left'><label><input type='button' name='btnCrearIva' id='btnCrearIva' value='Actualizar' onClick='actualizardbi()'></label></div></td></tr>");		
							
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			if(ct==0){
				out.print("<tr><td width='22%'>Cuenta de Clientes</td><td><label><input  type='text' id='cta1' size='35'  onkeyup='autocompletarCuentas(1,1,0,1)' /></label><label><input type='text' id='ncta1' style='width:40%'  readonly='readonly' /><input type='hidden' id='hcta1' /></label></td></tr>");
				out.print("<tr><td></td><td><div name='CuentasN' id='Cuentas1' ></div></td><td></td></tr>");
				out.print("<tr><td width='22%'>Cuenta de Ingresos</td><td><label><input type='text' id='cta2' size='35'  onkeyup='autocompletarCuentas(2,1,0,1)'/><input type='text' id='ncta2' style='width:40%' readonly='readonly' /><input type='hidden' id='hcta2' /></label></td></tr>");
				out.print("<tr><td></td><td><div name='CuentasN' id='Cuentas2' ></div></td><td></td></tr>");
				out.print("<tr><td width='22%'>Cuenta de Copago</td><td><label><input  type='text' id='cta3' size='35'  onkeyup='autocompletarCuentas(3,1,0,1)' /><input type='text' id='ncta3' style='width:40%' readonly='readonly' /><input type='hidden' id='hcta3' /></label></td></tr>");
				out.print("<tr><td></td><td><div name='CuentasN' id='Cuentas3' ></div></td><td></td></tr>");
				out.print("<tr><td width='22%'>Cuenta de Cuota Moderadora</td><td><label><input type='text' id='cta4' size='35'  onkeyup='autocompletarCuentas(4,1,0,1)' /><input type='text' id='ncta4' style='width:40%' readonly='readonly' /><input type='hidden' id='hcta4' /></label></td></tr>");
				out.print("<tr><td></td><td><div name='CuentasN' id='Cuentas4' ></div></td><td></td></tr>");
				
				out.print("<tr></tr><tr></tr><tr></tr><tr><td></td><td><div align='left'><label><input type='button' name='btnCrearIva' id='btnCrearIva' value='Crear' onClick='crardbi()'></label></div></td></tr>");		
				
			}
		}


		
		if(va.equals("15.1")){
		    mn.CrearDatosBasicosI(dbn8,anio,dbn4,dbn9,dbn1,dbn5,dbn10,dbn2,dbn6,dbn11,dbn3,dbn7,fechacjmysql,hra,usu);
		}
		
		if(va.equals("15.2")){
			mn.ActualizarEstadoDatosBasicosI();
			mn.CrearDatosBasicosI(dbn8,anio,dbn4,dbn9,dbn1,dbn5,dbn10,dbn2,dbn6,dbn11,dbn3,dbn7,fechacjmysql,hra,usu);
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
////////////////////////////////		
	}//Fin doPost
}//Fin class ControlNomina
