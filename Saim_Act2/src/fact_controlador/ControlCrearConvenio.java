package fact_controlador;

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

import fact_metodo.MetodoCrearConvenio;
import fact_metodo.MetodoCrearModalidad;
import fact_metodo.MetodoCrearNivelComplejidad;
import fact_metodo.MetodoCrearManualTarifario;
import fact_metodo.MetodoCrearEntidad;

/**
 * Servlet implementation class ControlCrearConvenio
 */
public class ControlCrearConvenio extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String va = req.getParameter("valor");
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		
		//numContrato, valor, fechaI, fechaF, nivComplejidad, codTarifa1, codTarifa2, autoriza, codEntidad
		//String numContrato, String valor, String fechaI, String fechaF, String nivComplejidad, String codTarifa1, String codTarifa2, String autoriza, String codEntidad
		String numContrato = req.getParameter("numContrato");
		String valor = req.getParameter("valorConv");
		String fechaI = req.getParameter("fechaI");
		String fechaF = req.getParameter("fechaF");
		String modalidad = req.getParameter("modalidad");
		String nivComplejidad = req.getParameter("nivComplejidad");
		String codTarifa1 = req.getParameter("codTarifa1");
		String codTarifa2 = req.getParameter("codTarifa2");
		String autoriza = req.getParameter("autoriza");
		String codEntidad = req.getParameter("codEntidad");
		String obs = req.getParameter("obs");
		String Modalidad_Pago=req.getParameter("Modalidad_Pago");
		String tope = req.getParameter("tope");
		String estc = req.getParameter("estc");
		String cod = req.getParameter("cod");//Codigo del convenio
		String user = req.getParameter("user");
		
		
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
		
		MetodoCrearConvenio mce = new MetodoCrearConvenio();
		MetodoCrearModalidad mod = new MetodoCrearModalidad();
		MetodoCrearNivelComplejidad nc = new MetodoCrearNivelComplejidad();
		MetodoCrearManualTarifario mtar = new MetodoCrearManualTarifario();
		MetodoCrearEntidad ent = new MetodoCrearEntidad();
		
		
		
		if(va.equals("CrearRb")){
			out.print("<div class='separator'></div>");
			out.print("<div >" +
						"<a class='alignText sizeName floatLeft'>N&uacute;mero de Contrato:</a>" +
						"<div class='widthDivLeftRigth floatLeft'>" +
							"<input name='NumContrato' type='text' maxlength='50' id='NumContrato' class='widthField'/>" +
							//"<a class='style8'>*</a>" +
						"</div>" +
					    "<div class='withDivCenter floatLeft'></div>" +
					    "<a class='alignText sizeName floatLeft'>Valor:</a>" +
					    "<div class='widthDivLeftRigth floatLeft'>" +
					    	"<input name='Valor' type='text' id='Valor' onkeypress='return soloNumero(event)' maxlength='50' class='widthField'/>" +
					    	//"<a class='style8'>*</a>" +
					    "</div> " +
					  "</div>");
			out.print("<div class='separator'></div>");
			out.print("<div >" +
					 	"<a class='alignText sizeName floatLeft'>Fecha Inicial:</a>" +
					 	"<div class='widthDivLeftRigth floatLeft'>" +
					 		"<input name='FechaI' type='text' id='FechaI' maxlength='10' onkeypress='masca(this, patron, true)' class='widthFieldDate'/>" +
					 		//"<a class='style8'>*</a>" +
					 	"</div>" +
					 	"<div class='withDivCenter floatLeft'></div>" +
					 	"<a class='alignText sizeName floatLeft'>Fecha Final:</a>" +
					 	"<div class='widthDivLeftRigth floatLeft'>" +
					 		"<input name='FechaF' type='text' id='FechaF' onkeypress='masca(this, patron, true)' maxlength='10' class='widthFieldDate'/>" +
					 		//"<a class='style8'>*</a>"+
					 	"</div> " +
			  		  "</div>");
			out.print("<div class='separator'></div>");
			out.print("<div >" +
					 	"<a class='alignText sizeName floatLeft'>Modalidad de Pago:</a>" +
					 	"<div class='widthDivLeftRigth floatLeft'>");
						
			ResultSet r = null;
			try{
				r = mod.getAll();
				int cont = 1;
				while(r.next()){
					out.print("<label style='display:inline; cursor: pointer;' class='widthCheck'><input name='checkboxModalidad' type='checkbox' value='"+r.getString(1)+"' />"+r.getString(2)+"</label>");					
					cont++;
				}
				r.getStatement().getConnection().close();
			}catch (Exception e) {
				e.printStackTrace();
			}
			out.print("</div> " +
					  "<div class='withDivCenter floatLeft'></div>" +
					  "<a class='alignText sizeName floatLeft'>Nivel de Complejidad:</a>" +
					  "<div class='widthDivLeftRigth floatLeft'>" +
					  		"<select name='listNivelC' class='widthField ' id='listNivelC' onkeydown='A(this, event)'><option value='0'>Seleccione...</option>");
			ResultSet rs = null;
			try{
				rs = nc.getAll();
				while(rs.next()){
					out.print("<option value='"+rs.getString(1)+"'>"+rs.getString(2)+"</option>");
				}
				rs.getStatement().getConnection().close();
			}catch (Exception e) {
				e.printStackTrace();
			}
			out.print("</select>"+//<a class='style8'>*</a> " +
					  "</div> " +
			  		 "</div>");
			out.print("<div class='separator'></div>");	
			out.print("<div >" +
						"<a class='alignText sizeName floatLeft'>Seleccionar tarifa:</a>" +
						"<div class='widthDivLeftRigth floatLeft'>" +
							"<select name='listTarifa1' id='listTarifa1' class='widthField'><option value='0'>Seleccione...</option>");
			ResultSet rs1 = null;
			try{
				rs1 = mtar.getAll();
				while(rs1.next()){
					out.print("<option value='"+rs1.getString(1)+"'>"+rs1.getString(2)+"</option>");
				}
				rs1.getStatement().getConnection().close();
			}catch (Exception e) {
				e.printStackTrace();
			}
			out.print("</select>" +
					 "</div>" +
					 "<div class='withDivCenter floatLeft'>" +
							"<input type='button' value='>>' class='boton4' onclick='asignarTarifa()' title='Asignar tarifa'/>" +
					 "</div>" +
					 "<a class='alignText sizeName floatLeft'>Tarifas Asignadas:</a>" +
					 "<div class='widthDivLeftRigth floatLeft'>" +
							"<select name='listTarifa2' multiple='multiple' id='listTarifa2' class='widthField' onkeyup='eliminarTarifa(this, event)'></select>" +
					 "</div>" +
				"</div>");
			out.print("<div class='separator'></div>");
			
			
			out.print("<div> " +
						"<a class='alignText sizeName floatLeft'>Autorizado Por:</a>" +
						"<div class='widthDivLeftRigth floatLeft'>" +
					  		"<input name='Autoriza' type='text' id='Autoriza' maxlength='50' class='widthField' onkeydown='A(this, event)'/>" +
					  		//"<span class='style8'>*</span></td><td  align='center'>" +
					  	"</div>"+
					  	"<div class='withDivCenter floatLeft'></div>" +
				  		"<a class='alignText sizeName floatLeft'>Entidad:</a>" +
				  		"<div class='widthDivLeftRigth floatLeft'>" +
				  			"<select name='listEntidad' id='listEntidad'  onkeydown='A(this, event)' class='widthField'><option value='0'>Seleccione...</option>");
			ResultSet rs2 = null;
			try{
				rs2 = ent.BuscarTodasEntidad();
				while(rs2.next()){
					out.print("<option title='"+rs2.getString(2)+"' value='"+rs2.getString(1)+"'>"+rs2.getString(2)+"</option>");
				}
				rs2.getStatement().getConnection().close();
			}catch (Exception e) {
				e.printStackTrace();
			}
			out.print("</select>" +
					  //"<span class='style8'>*</span>"+
					  "</div>" +
					  "</div>");
			
			out.print("<div class='separator'></div>");
			out.print("<div>" +
						"<a class='alignText sizeName floatLeft'>Observaci&oacute;n:</a>" +
						"<div class='widthDivLeftRigth floatLeft'>" +
							"<textarea id='Observacion' name='Observacion' cols='41' rows='3' onkeyup='return maximaLongitud(this,300)' style='font-family: Trebuchet MS;' class='widthField'></textarea>" +
						"</div>" +
						"<div class='withDivCenter floatLeft'></div>" +
						"<a class='alignText sizeName floatLeft'>Condicion Pago:</a>" +
						"<div class='widthDivLeftRigth floatLeft'>" +
							"<input name='Modalidad_Pago' type='text' id='Modalidad_Pago' maxlength='50' onkeydown='A(this, event)' class='widthField'/>" +
							//"<span class='style8'>*</span>" +
						"</div>" +
					  "</div>");
			out.print("<div class='separator'></div>");
			out.print("<div>" +
						"<a class='alignText sizeName floatLeft'>Estado:</a>" +
						"<div class='widthDivLeftRigth floatLeft'>" +
						"<select name='ec' id='ec'  onkeydown='A(this, event)' class='widthField'><option value='0'>Activo</option><option value='1'>Inactivo</option></select></div>" +
						"<div class='withDivCenter floatLeft'></div>" +
						"<a class='alignText sizeName floatLeft'>Tope:</a>" +
						"<div class='widthDivLeftRigth floatLeft'>" +
							"<select name='tope' id='tope'  onkeydown='A(this, event)' class='widthField'><option value='0'>&nbsp;No Aplica</option><option value='1'>&nbsp;&nbsp;&nbsp;&nbsp;5 Millones</option><option value='2'>&nbsp;&nbsp;10 Millones</option><option value='3'>&nbsp;&nbsp;20 Millones</option><option value='4'>&nbsp;&nbsp;50 Millones</option><option value='5'>100 Millones</option></select>" +
							//"<span class='style8'>*</span>" +
						"</div>" +
					  "</div>");
			out.print("<div class='separatorFinal'></div>");
			out.print("<table width='100%' border='0'>");
			//out.print("<tr><td colspan='5'><span class='style8' style='font-size: 10pt;'>* Campos Requeridos</span></td></tr>");
			out.print("<div align='center'><input name='btnCrearConvenio' type='button' id='btnCrearConvenio' value='Crear Convenio' onclick='Ingresar()' class='boton4'/><input name='BorrarForm' type='button' id='BorrarForm' value='Borrar Campos' onclick='Borrar()' class='boton4'/></div>");
			out.close();
			
			
			
		}else if(va.equals("ActualizarRb")){
			ResultSet rs3 = null;
			rs3 = mce.getAll();
			out.print("<a class='alignText floatLeft'>Seleccione el Convenio:</a><div class='floatLeft'><select name='lista' id='lista' onchange='Buscar()'><option value='Seleccione'>Seleccione</option>");
			try {
				while(rs3.next()){
					out.print("<option value="+rs3.getString(1)+">"+rs3.getString(3)+"-"+rs3.getString(2)+"</option>");
				}
				out.print("</select></div>");
				out.print("<div class='separator'></div>");
				out.print("<div id='actConvenio'></div>");
				out.print("<div id='objeto'></div>");
				rs3.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.close();
			
			
			
			
		}else if(va.equals("Ingresar")){
			String r =  mce.getCodByNumEntidad(codEntidad);
			if(r.equals("")){
				String fI = "", fF = "";
				fI += fechaI.substring(6, 10)+"-"+fechaI.substring(3, 5)+"-"+fechaI.substring(0, 2);
				fF += fechaF.substring(6, 10)+"-"+fechaF.substring(3, 5)+"-"+fechaF.substring(0, 2);
				if(mce.Crear(numContrato, valor, fI, fF, nivComplejidad,autoriza, codEntidad, obs, modalidad,Modalidad_Pago,user)){
					String codConvenio = mce.obtenerCodConvenio();
					String[] tarifas = codTarifa1.split(",");
					for (int i=0;i<tarifas.length;i++){
						mce.agregarTarifaAConvenio(codConvenio, tarifas[i]);
					}
					
					mce.AuditaConvenio(codConvenio,codEntidad,fF,valor,tope,"0");
					
					out.print("Ingreso exitoso");
				}else{
					out.print("Error al ingresar los datos");
				}
			}else{
				out.print("Esta empresa ya tiene convenio activo");
			}
			out.close();
			

			
			
		}else if(va.equals("ver")){

			ResultSet rs4 = null;
			rs4 = mce.getByCod(cod);
			try {
				if(rs4.next()){
					out.print("<div class='separator'></div>");
					out.print("<div >" +
								"<a class='alignText sizeName floatLeft'>N&uacute;mero de Contrato:</a>" +
								"<div class='widthDivLeftRigth floatLeft'>" +
									"<input name='NumContrato' type='text' maxlength='50' id='NumContrato' class='widthField' value='"+rs4.getString(1)+"' />" +
									//"<a class='style8'>*</a>" +
								"</div>" +
							    "<div class='withDivCenter floatLeft'></div>" +
							    "<a class='alignText sizeName floatLeft'>Valor:</a>" +
							    "<div class='widthDivLeftRigth floatLeft'>" +
							    	"<input name='Valor' type='text' id='Valor' value='"+rs4.getString(2)+"' maxlength='50' class='widthField'/>" +
							    	//"<a class='style8'>*</a>" +
							    "</div> " +
							  "</div>");
					out.print("<div class='separator'></div>");
					
					String fI = "", fF = "";
					fI += rs4.getString(3).substring(8, 10)+"/"+rs4.getString(3).substring(5, 7)+"/"+rs4.getString(3).substring(0, 4);
					fF += rs4.getString(4).substring(8, 10)+"/"+rs4.getString(4).substring(5, 7)+"/"+rs4.getString(4).substring(0, 4);
					
					out.print("<div >" +
						 	"<a class='alignText sizeName floatLeft'>Fecha Inicial:</a>" +
						 	"<div class='widthDivLeftRigth floatLeft'>" +
						 		"<input name='FechaI' type='text' id='FechaI' maxlength='10' onkeypress='masca(this, patron, true)' class='widthFieldDate' value='"+fI+"'  disabled='disabled'/>" +
						 		//"<a class='style8'>*</a>" +
						 	"</div>" +
						 	"<div class='withDivCenter floatLeft'></div>" +
						 	"<a class='alignText sizeName floatLeft'>Fecha Final:</a>" +
						 	"<div class='widthDivLeftRigth floatLeft'>" +
						 		"<input name='FechaF' type='text' id='FechaF' onkeypress='masca(this, patron, true)' maxlength='10' class='widthFieldDate' value='"+fF+"'/>" +
						 		//"<a class='style8'>*</a>"+
						 	"</div> " +
				  		  "</div>");
					out.print("<div class='separator'></div>");
				
					out.print("<div >" +
						 	"<a class='alignText sizeName floatLeft'>Modalidad de Pago:</a>" +
						 	"<div class='widthDivLeftRigth floatLeft'>");
							
					ResultSet r = null;
					r = mod.getAll();
					int cont = 1;
					ResultSet rAux = mce.getModalidadesByCod(cod);
					while(r.next()){
						String sAux = "";
						while (rAux.next()) {
							if(rAux.getString(1).equals(r.getString(1))){
								sAux = " checked='checked' ";
							}
						}
							out.print("<label style='display:inline; cursor: pointer;' class='widthCheck'><input name='checkboxModalidad' type='checkbox' value='"+r.getString(1)+"'"+sAux+" />"+r.getString(2)+"</label>");					
							cont++;
							rAux.beforeFirst();
						}
						rAux.getStatement().getConnection().close();
						r.getStatement().getConnection().close();
					
					out.print("</div> " +
							  "<div class='withDivCenter floatLeft'></div>" +
							  "<a class='alignText sizeName floatLeft'>Nivel de Complejidad:</a>" +
							  "<div class='widthDivLeftRigth floatLeft'>" +
							  		"<select name='listNivelC' class='widthField ' id='listNivelC' onkeydown='A(this, event)'><option value='0'>Seleccione...</option>");
					ResultSet rs = null;
					try{
						ResultSet rs5 = null;
						rs5 = nc.getAll();
						while(rs5.next()){
							if(rs4.getString(5).equals(rs5.getString(1))){
								out.print("<option value='"+rs5.getString(1)+"' selected='selected'>"+rs5.getString(2)+"</option>");
							}else{
								out.print("<option value='"+rs5.getString(1)+"'>"+rs5.getString(2)+"</option>");
							}
						}
						rs5.getStatement().getConnection().close();
					}catch (Exception e) {
						e.printStackTrace();
					}
					out.print("</select>"+//<a class='style8'>*</a> " +
							  "</div> " +
					  		 "</div>");
					out.print("<div class='separator'></div>");						
					
					
						out.print("<div >" +
								"<a class='alignText sizeName floatLeft'>Seleccionar tarifa:</a>" +
								"<div class='widthDivLeftRigth floatLeft'>" +
									"<select name='listTarifa1' id='listTarifa1' class='widthField'><option value='0'>Seleccione...</option>");
					ResultSet rs1 = null;
					try{
						rs1 = mtar.getAll();
						while(rs1.next()){
							out.print("<option value='"+rs1.getString(1)+"'>"+rs1.getString(2)+"</option>");
						}
						rs1.getStatement().getConnection().close();
					}catch (Exception e) {
						e.printStackTrace();
					}
					out.print("</select>" +
							 "</div>" +
							 "<div class='withDivCenter floatLeft'>" +
									"<input type='button' value='>>' class='boton4' onclick='asignarTarifa()' title='Asignar tarifa'/>" +
							 "</div>" +
							 "<a class='alignText sizeName floatLeft'>Tarifas Asignadas:</a>" +
							 "<div class='widthDivLeftRigth floatLeft'>" +
									"<select name='listTarifa2' multiple='multiple' id='listTarifa2' class='widthField' onkeyup='eliminarTarifa(this, event)'>");
					try{
						ResultSet rs7 = null;
						rs7 = mce.obtenerTarifasConvenio(cod);
						while(rs7.next()){
							out.print("<option value='"+rs7.getString(1)+"'>"+rs7.getString(2)+"</option>");
						}
						rs7.getStatement().getConnection().close();
					}catch (Exception e) {
						e.printStackTrace();
					}
					out.print("</select>" +
							 "</div>" +
						"</div>");
					out.print("<div class='separator'></div>");
					
					out.print("<div> " +
							"<a class='alignText sizeName floatLeft'>Autorizado Por:</a>" +
							"<div class='widthDivLeftRigth floatLeft'>" +
						  		"<input name='Autoriza' type='text' id='Autoriza' maxlength='50' class='widthField' onkeydown='B(this, event)' value='"+rs4.getString(8)+"' />" +
						  		//"<span class='style8'>*</span></td><td  align='center'>" +
						  	"</div>"+
						  	"<div class='withDivCenter floatLeft'></div>" +
					  		"<a class='alignText sizeName floatLeft'>Entidad:</a>" +
					  		"<div class='widthDivLeftRigth floatLeft'>" +
					  			"<select name='listEntidad' id='listEntidad'  onkeydown='A(this, event)' class='widthField' disabled='disabled'><option value='0'>Seleccione...</option>");
									
					try{
						ResultSet rs7 = null;
						rs7 = ent.BuscarTodasEntidad();
						while(rs7.next()){
							if(rs4.getString(7).equals(rs7.getString(1))){
								out.print("<option value='"+rs7.getString(1)+"' selected='selected'>"+rs7.getString(2)+"</option>");
							}else{
								out.print("<option value='"+rs7.getString(1)+"'>"+rs7.getString(2)+"</option>");
							}
						}
						rs7.getStatement().getConnection().close();
					}catch (Exception e) {
						e.printStackTrace();
					}
					out.print("</select>" +
							  //"<span class='style8'>*</span>"+
							  "</div>" +
							  "</div>");
					out.print("<div class='separator'></div>");
					String obser = "";
					if(rs4.getString(8) != null){obser = rs4.getString(8);}
					out.print("<div>" +
							"<a class='alignText sizeName floatLeft'>Observaci&oacute;n:</a>" +
							"<div class='widthDivLeftRigth floatLeft'>" +
								"<textarea id='Observacion' name='Observacion' cols='41' rows='3' onkeyup='return maximaLongitud(this,300)' style='font-family: Trebuchet MS;' class='widthField'>"+obser+"</textarea>" +
							"</div>" +
							"<div class='withDivCenter floatLeft'></div>" +
							"<a class='alignText sizeName floatLeft'>Condicion Pago:</a>" +
							"<div class='widthDivLeftRigth floatLeft'>" +
								"<input name='Modalidad_Pago' type='text' id='Modalidad_Pago' maxlength='50' onkeydown='A(this, event)' class='widthField' value='"+rs4.getString(9)+"'/>" +
								//"<span class='style8'>*</span>" +
							"</div>" +
						  "</div>");
					
					out.print("<div class='separator'></div>");
					try{
					ResultSet rs8 = null;
					rs8 = mce.obtenerAuditaConvenio(cod);
					String topeletras="";
					if(rs8.next()){
					if(rs8.getString(3).equals("0")){topeletras="No Aplica";}
					if(rs8.getString(3).equals("1")){topeletras="&nbsp;&nbsp;&nbsp;&nbsp;5 Millones";}
					if(rs8.getString(3).equals("2")){topeletras="&nbsp;&nbsp;10 Millones";}
					if(rs8.getString(3).equals("3")){topeletras="&nbsp;&nbsp;20 Millones";}
					if(rs8.getString(3).equals("4")){topeletras="&nbsp;&nbsp;50 Millones";}
					if(rs8.getString(3).equals("5")){topeletras="100 Millones";}
					//traerme el estado del convenio de l a tabla convenio y escribirlo
					//y hacer todo el proceso de ingresarlo y ademas de actualizarlo
					try{
						ResultSet rs9 = null;
						rs9 = mce.obtenerEstadoConvenio(cod);
						String ec="";String vec="";
						String nec="";String vnec="";
						if(rs9.next()){
							if(rs9.getString(1).equals("0")){ec="Activo";nec="Inactivo";vec="0";vnec="1";}
							if(rs9.getString(1).equals("1")){ec="Inactivo";nec="Activo";vec="1";vnec="0";}
					out.print("<div>" +
								"<a class='alignText sizeName floatLeft'>Estado:</a>" +
								"<div class='widthDivLeftRigth floatLeft'>" +
								"<select name='ec' id='ec'  onkeydown='A(this, event)' class='widthField'><option value='"+vec+"'>"+ec+"</option><option value='"+vnec+"'>"+nec+"</option></select></div>" +
								"<div class='withDivCenter floatLeft'></div>" +
								"<a class='alignText sizeName floatLeft'>Tope:</a>" +
								"<div class='widthDivLeftRigth floatLeft'>" +
									"<select name='tope' id='tope'  onkeydown='A(this, event)' class='widthField'><option value='"+rs8.getString(3)+"'>"+topeletras+"</option><option value='0'>&nbsp;No Aplica</option><option value='1'>&nbsp;&nbsp;&nbsp;&nbsp;5 Millones</option><option value='2'>&nbsp;&nbsp;10 Millones</option><option value='3'>&nbsp;&nbsp;20 Millones</option><option value='4'>&nbsp;&nbsp;50 Millones</option><option value='5'>100 Millones</option></select>" +
									//"<span class='style8'>*</span>" +
								"</div>" +
							  "</div>");
						}
						rs9.getStatement().getConnection().close();
					}catch (Exception e) {
						e.printStackTrace();
					}
					
					}
					rs8.getStatement().getConnection().close();
					}catch (Exception e) {
						e.printStackTrace();
					}
					
					out.print("<div class='separatorFinal'></div>");
					
					out.print("<div align='center'><input name='Actualiza' type='button' id='Actualiza' value='Actualizar Convenio' onclick='Actualizar()' class='boton4' /></div>");//<input name='BorrarForm' type='button' id='BorrarForm' value='     Borrar Campos     ' onclick='Borrar()' />
				}
				rs4.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				out.print(e.getMessage());
				e.printStackTrace();
			}
			out.close();
			
			
			
			
		}else if(va.equals("Actualizar")){
			String fI = "", fF = "";
			fI += fechaI.substring(6, 10)+"-"+fechaI.substring(3, 5)+"-"+fechaI.substring(0, 2);
			fF += fechaF.substring(6, 10)+"-"+fechaF.substring(3, 5)+"-"+fechaF.substring(0, 2);
			if(mce.Actualizar(numContrato, valor, fI, fF, nivComplejidad, autoriza, codEntidad, obs, cod, modalidad,Modalidad_Pago,estc,user,fechacjmysql,hra,tope)){
				mce.borrarTarifasConvenio(cod);
				String[] tarifas = codTarifa1.split(",");
				for (int i=0;i<tarifas.length;i++){
					mce.agregarTarifaAConvenio(cod, tarifas[i]);
				}
				mce.UpdateAuditaConvenio(cod, fF, valor, tope);
				out.print("Convenio actualizado exitosamente");
			}else{
				out.print("Error al actualizar el convenio");
			}
			out.close();
		}
	}

}