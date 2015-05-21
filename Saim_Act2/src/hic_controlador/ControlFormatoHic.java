
package hic_controlador;


import hic_metodo.MetodoFormatoHic;
import hic_metodo.MetodoVerFormatos;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class Controimg_area
 */
public class ControlFormatoHic extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	/**
	 *autocompletar de todas las areas,para posteriormente escojerla.
	 */
		res.setContentType("text/html;charset=UTF-8");
		ResultSet rs=null;
		String cad =req.getParameter("codigo");
		MetodoFormatoHic mfh = new MetodoFormatoHic ();

	 int accion;
     
        accion = Integer.parseInt(req.getParameter("txtAccion"));
if(accion == 26){

        try {
            rs =mfh.listarParaAutocompletarArea(cad);
            String cadena ="";
            String nombre ="";
            cadena="[";
            while(rs.next()){
            	nombre=rs.getString(2);
            	cadena = cadena+"'"+nombre+"|"+rs.getString(1)+"'";
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
		String va = req.getParameter("valor");
		String NomFormato = req.getParameter("NomFormato");
		String CodArea = req.getParameter("CodArea");
		String codRelacion = req.getParameter("codRelacion");
		String CodigoFormato=req.getParameter("CodigoFormato"); 
		String CodPac=req.getParameter("CodPac");
		String CodAdm=req.getParameter("CodAdm");
		String usuario=req.getParameter("usuario");
		String CodInsumo=req.getParameter("CodInsumo");
		String CodigoMed=req.getParameter("CodigoMed");
		String Cantidad=req.getParameter("Cantidad");
		String Observacion=req.getParameter("Observacion");
		String DetOrden=req.getParameter("DetOrden");
		String codFormulacion_fk=req.getParameter("codFormulacion_fk");
		res.setContentType("text/html;charset=UTF-8");
		String hora=req.getParameter("hora");
		String fecha=req.getParameter("fecha");	
		PrintWriter out=res.getWriter();
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		ResultSet rs3=null;
		String CodRelacion=null;
		MetodoFormatoHic mfh = new MetodoFormatoHic ();
		MetodoVerFormatos mvf = new MetodoVerFormatos ();
		
		if(va.equals("diagnosticos")){
			out.print("<table width='100%' class='style6' border='0'><tr><td colspan='4' align='left' id='cabecera2' class='style11'><div align='center'>Tipo De Reporte</div></td></tr><tr><td width='245' align='left'>REPORTE GENERAL </td><td width='129' align='left'><label><input name='radio' type='radio' value='2' id='Diageneral'  onclick='Radio()'></label></td>");
			out.print("<td width='177'><label>REPORTE POR FECHAS </label></td><td width='406'><input name='radio' type='radio' value='3' id='Diafecha' onclick='Radio()' /></td></tr><tr><td colspan='4' align='left'><div id='OpcRepo'></div></td></tr></table>");
		}
		
		if(va.equals("medicamentos")){
			out.print("<table width='100%' class='style6' border='0'><tr><td colspan='4' align='left' id='cabecera2' class='style11'><div align='center'>Tipo De Reporte</div></td></tr><tr><td width='245' align='left'>REPORTE GENERAL </td><td width='129' align='left'><label><input name='radio' type='radio' value='2' id='Medigeneral'  onclick='Radio()'></label></td>");
			out.print("<td width='177'><label>REPORTE POR FECHAS </label></td><td width='406'><input name='radio' type='radio' value='3' id='Medifecha' onclick='Radio()' /></td></tr><tr><td colspan='4' align='left'><div id='OpcRepo'></div></td></tr></table>");
		}
		
		if(va.equals("cirugias")){
			out.print("<table width='100%' class='style6' border='0'><tr><td colspan='4' align='left' id='cabecera2' class='style11'><div align='center'>Tipo De Reporte</div></td></tr><tr><td width='245' align='left'>REPORTE GENERAL </td><td width='129' align='left'><label><input name='radio' type='radio' value='2' id='Cirugeneral'  onclick='Radio()'></label></td>");
			out.print("<td width='177'><label>REPORTE POR FECHAS </label></td><td width='406'><input name='radio' type='radio' value='3' id='Cirufecha' onclick='Radio()' /></td></tr><tr><td colspan='4' align='left'><div id='OpcRepo'></div></td></tr></table>");
		}
		/***************************************************/
		if(va.equals("Diageneral")){
			out.print("<table  width='100%'><tr><td align='center'><input class='boton4' name='btngenerar' type='button' id='btngenerar' value='Generar' onclick='generartodoDiagnostico()' /></td></tr></table>");
		}
		if(va.equals("Diafecha")){
			out.print("<table width='100%'  class='style6'><tr><td  align='right'>FECHA INICIAL:</td><td><label>Dia<select name='cmbdiaIni' id='cmbdiaIni'><option value='Dia' selected='selected'>Dia</option><option value='1'>1</option> <option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option><option value='13'>13</option><option value='14'>14</option><option value='15'>15</option><option value='16'>16</option><option value='17'>17</option><option value='18'>18</option><option value='19'>19</option><option value='20'>20</option><option value='21'>21</option><option value='22'>22</option><option value='23'>23</option><option value='24'>24</option><option value='25'>25</option><option value='26'>26</option><option value='27'>27</option><option value='28'>28</option><option value='29'>29</option><option value='30'>30</option><option value='31'>31</option></select>Mes<select name='cmbmesIni' id='cmbmesIni'><option value='Mes' selected='selected'>Mes</option><option value='01'>Enero</option><option value='02'>Febrero</option><option value='03'>Marzo</option><option value='04'>Abril</option><option value='05'>Mayo</option><option value='06'>Junio</option><option value='07'>Julio</option><option value='08'>Agosto</option><option value='09'>Septiembre</option><option value='10'>Octubre</option><option value='11'>Noviembre</option><option value='12'>Diciembre</option></select>A&ntilde;o<input name='txtanioIni' type='text' id='txtanioIni' size='4' maxlength='4'></label></td></tr>");
			out.print("<tr ><td  align='right'>FECHA FINAL:</td><td >Dia<select name='cmbdiaFin' id='cmbdiaFin'><option value='Dia' selected='selected'>Dia</option><option value='1'>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option><option value='13'>13</option><option value='14'>14</option><option value='15'>15</option><option value='16'>16</option><option value='17'>17</option><option value='18'>18</option><option value='19'>19</option><option value='20'>20</option><option value='21'>21</option><option value='22'>22</option><option value='23'>23</option><option value='24'>24</option><option value='25'>25</option><option value='26'>26</option><option value='27'>27</option><option value='28'>28</option><option value='29'>29</option><option value='30'>30</option><option value='31'>31</option></select>Mes<select name='cmbmesFin' id='cmbmesFin'><option value='Mes' selected='selected'>Mes</option><option value='01'>Enero</option><option value='02'>Febrero</option><option value='03'>Marzo</option><option value='04'>Abril</option><option value='05'>Mayo</option><option value='06'>Junio</option><option value='07'>Julio</option><option value='08'>Agosto</option><option value='09'>Septiembre</option><option value='10'>Octubre</option><option value='11'>Noviembre</option><option value='12'>Diciembre</option></select>A&ntilde;o<input name='txtanioFin' type='text' id='txtanioFin' size='4' maxlength='4'></td></tr><tr><td >&nbsp;</td><td align=''><input name='btngenerar' type='button' id='btngenerar' class='boton4' value='Generar' onClick='generarfechasDiagnostico()'></td></tr></table>");
		}
		
		if(va.equals("Medigeneral")){
			out.print("<table  width='100%'><tr><td align='center'><input class='boton4' name='btngenerar' type='button' id='btngenerar' value='Generar' onclick='generartodoMedicamentos()' /></td></tr></table>");
		}
		if(va.equals("Medifecha")){
			out.print("<table width='100%'  class='style6'><tr><td  align='right'>FECHA INICIAL:</td><td><label>Dia<select name='cmbdiaIni' id='cmbdiaIni'><option value='Dia' selected='selected'>Dia</option><option value='1'>1</option> <option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option><option value='13'>13</option><option value='14'>14</option><option value='15'>15</option><option value='16'>16</option><option value='17'>17</option><option value='18'>18</option><option value='19'>19</option><option value='20'>20</option><option value='21'>21</option><option value='22'>22</option><option value='23'>23</option><option value='24'>24</option><option value='25'>25</option><option value='26'>26</option><option value='27'>27</option><option value='28'>28</option><option value='29'>29</option><option value='30'>30</option><option value='31'>31</option></select>Mes<select name='cmbmesIni' id='cmbmesIni'><option value='Mes' selected='selected'>Mes</option><option value='01'>Enero</option><option value='02'>Febrero</option><option value='03'>Marzo</option><option value='04'>Abril</option><option value='05'>Mayo</option><option value='06'>Junio</option><option value='07'>Julio</option><option value='08'>Agosto</option><option value='09'>Septiembre</option><option value='10'>Octubre</option><option value='11'>Noviembre</option><option value='12'>Diciembre</option></select>A&ntilde;o<input name='txtanioIni' type='text' id='txtanioIni' size='4' maxlength='4'></label></td></tr>");
			out.print("<tr ><td  align='right'>FECHA FINAL:</td><td >Dia<select name='cmbdiaFin' id='cmbdiaFin'><option value='Dia' selected='selected'>Dia</option><option value='1'>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option><option value='13'>13</option><option value='14'>14</option><option value='15'>15</option><option value='16'>16</option><option value='17'>17</option><option value='18'>18</option><option value='19'>19</option><option value='20'>20</option><option value='21'>21</option><option value='22'>22</option><option value='23'>23</option><option value='24'>24</option><option value='25'>25</option><option value='26'>26</option><option value='27'>27</option><option value='28'>28</option><option value='29'>29</option><option value='30'>30</option><option value='31'>31</option></select>Mes<select name='cmbmesFin' id='cmbmesFin'><option value='Mes' selected='selected'>Mes</option><option value='01'>Enero</option><option value='02'>Febrero</option><option value='03'>Marzo</option><option value='04'>Abril</option><option value='05'>Mayo</option><option value='06'>Junio</option><option value='07'>Julio</option><option value='08'>Agosto</option><option value='09'>Septiembre</option><option value='10'>Octubre</option><option value='11'>Noviembre</option><option value='12'>Diciembre</option></select>A&ntilde;o<input name='txtanioFin' type='text' id='txtanioFin' size='4' maxlength='4'></td></tr><tr><td >&nbsp;</td><td align=''><input name='btngenerar' type='button' id='btngenerar' class='boton4' value='Generar' onClick='generarfechasMedicamentos()'></td></tr></table>");
		}
		
		if(va.equals("Cirugeneral")){
			out.print("<table  width='100%'><tr><td align='center'><input class='boton4' name='btngenerar' type='button' id='btngenerar' value='Generar' onclick='generartodoCirugia()' /></td></tr></table>");
		}
		if(va.equals("Cirufecha")){
			out.print("<table width='100%'  class='style6'><tr><td  align='right'>FECHA INICIAL:</td><td><label>Dia<select name='cmbdiaIni' id='cmbdiaIni'><option value='Dia' selected='selected'>Dia</option><option value='1'>1</option> <option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option><option value='13'>13</option><option value='14'>14</option><option value='15'>15</option><option value='16'>16</option><option value='17'>17</option><option value='18'>18</option><option value='19'>19</option><option value='20'>20</option><option value='21'>21</option><option value='22'>22</option><option value='23'>23</option><option value='24'>24</option><option value='25'>25</option><option value='26'>26</option><option value='27'>27</option><option value='28'>28</option><option value='29'>29</option><option value='30'>30</option><option value='31'>31</option></select>Mes<select name='cmbmesIni' id='cmbmesIni'><option value='Mes' selected='selected'>Mes</option><option value='01'>Enero</option><option value='02'>Febrero</option><option value='03'>Marzo</option><option value='04'>Abril</option><option value='05'>Mayo</option><option value='06'>Junio</option><option value='07'>Julio</option><option value='08'>Agosto</option><option value='09'>Septiembre</option><option value='10'>Octubre</option><option value='11'>Noviembre</option><option value='12'>Diciembre</option></select>A&ntilde;o<input name='txtanioIni' type='text' id='txtanioIni' size='4' maxlength='4'></label></td></tr>");
			out.print("<tr ><td  align='right'>FECHA FINAL:</td><td >Dia<select name='cmbdiaFin' id='cmbdiaFin'><option value='Dia' selected='selected'>Dia</option><option value='1'>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option><option value='13'>13</option><option value='14'>14</option><option value='15'>15</option><option value='16'>16</option><option value='17'>17</option><option value='18'>18</option><option value='19'>19</option><option value='20'>20</option><option value='21'>21</option><option value='22'>22</option><option value='23'>23</option><option value='24'>24</option><option value='25'>25</option><option value='26'>26</option><option value='27'>27</option><option value='28'>28</option><option value='29'>29</option><option value='30'>30</option><option value='31'>31</option></select>Mes<select name='cmbmesFin' id='cmbmesFin'><option value='Mes' selected='selected'>Mes</option><option value='01'>Enero</option><option value='02'>Febrero</option><option value='03'>Marzo</option><option value='04'>Abril</option><option value='05'>Mayo</option><option value='06'>Junio</option><option value='07'>Julio</option><option value='08'>Agosto</option><option value='09'>Septiembre</option><option value='10'>Octubre</option><option value='11'>Noviembre</option><option value='12'>Diciembre</option></select>A&ntilde;o<input name='txtanioFin' type='text' id='txtanioFin' size='4' maxlength='4'></td></tr><tr><td >&nbsp;</td><td align=''><input name='btngenerar' type='button' id='btngenerar' class='boton4' value='Generar' onClick='generarfechasCirugia()'></td></tr></table>");
		}
		/***************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************/
		
		if(va.equals("Ingresar")){
			/** 
			 */
			out.println("<table width='100%' class='style6'  ><tr><td colspan='5' id='cabecera2'><span class='style11'><div align='center'>CREAR FORMATO </div></span></td></tr><tr><td colspan='4'><span >NOMBRE DEL FORMATO </span></td><td width='35'>&nbsp;</td></tr><tr><td colspan='5'><input name='txtNomFormato' type='text'  id='txtNomFormato' onkeyup='this.value=this.value.toUpperCase();' size='70' />  <span class='style8'>*</span></td><BR /></tr><tr><td colspan='2' >SELECCIONE LAS AREAS QUE TIENE EL FORMATO </td><td width='98' align='center'>&nbsp;</td><td colspan='2' align='center'>&nbsp;</td></tr><tr><td width='427' align='center' ><input name='txtArea' type='text' id='txtArea' size='70' onkeyup='autocompletaArea()' /></td><td width='125' align='center' ><input name='btnAsignar' class='boton4' type='button' id='btnAsignar' onclick='AsignarArea()' value='Asignar'  align='middle'/></td><td align='center'><label><span class='style3'><input name='btnFinalizar' type='button' id='btnFinalizar' class='boton4' value='Finalizar' onclick='FinalizarFormato()' /></span></label></td><td colspan='2' align='center'>&nbsp;</td></tr><tr><td align='center'><div id='sugerencias'></div></td><td align='center' ><input name='txtCodArea' type='hidden' id='txtCodArea' size='10' /></td><td align='center'>&nbsp;</td><td colspan='2' align='center'>&nbsp;</td></tr><tr><td align='center' ><div id='areas'></div></td><td colspan='4' align='center' ><span class='style8'>Datos Requeridos *</span></td></tr></table>");
		}
		if(va.equals("Actualizar")){
			/** 
			 */
			out.println("<table width='100%' border='1' class='style6' ><tr><td colspan='2' id='cabecera2'><span class='style11'><div align='center'>ACTUALIZAR FORMATO </div></span></td></tr>");
			out.println("<tr><td width='194'><span >NOMBRE DEL FORMATO </span></td><td width='765'><select name='cmbFormato' id='cmbFormato'  onchange='VerAreas()'><option value='Seleccione' selected='selected'>Seleccione</option>");
			rs=mfh.ObtenerFormatos();
			try {
				while(rs.next()){
				out.println("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
				}
				rs.getStatement().getConnection().close();
				out.println("</select></td></tr><tr><td colspan='2'><div id='areas'></div></td></tr></table>");
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		if(va.equals("NOACTIVO")){
			out.println("<p class=style67>USTED NO TIENE ESTA OPCION ACTIVADA PORQUE TIENE FORMATOS ABIERTOS</p>");
			out.println("<p  class=style66><u><i><a href=menu.jsp>CERRAR FORMATO</a></i></u></p>");
		}
		if(va.equals("cambio")){
			out.println("<table width='100%' border='0'><tr><td width='17%'>ADICIONAR AREA </td><td width='83%' colspan='2'><select name='cmbAreas' id='cmbAreas'  onChange=''><option value='Seleccione' selected='selected'>Seleccione</option>");
			rs=mfh.ObtenerAreas();
			try {
				while(rs.next()){
				out.println("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
				}
				out.println("</select><input name='btnAdicionar' type='button' id='btnAdicionar' value='     Adicionar     ' onClick='ActualizarFormato()' /></td></tr></table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			rs1=mfh.ObtenerAreasFormato(CodigoFormato);
			out.println("<table width='100%' border='0'><tr id='cabecera2' class='style11'><td width='48%'><div align='center'>Nombre Area </div></td><td><div align='center'>Accion</div></td></tr>");
			try {
				while(rs1.next()){
					out.println("<tr><td><div align='center'>"+rs1.getString(1)+"</div></td><td><div align='center'><a href='#' onclick='omitir("+rs1.getString(2)+")'><strong><em>Omitir</em></strong></a></div></td></tr>");

				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
			out.println("</table>");
		}
		
		if(va.equals("actu")){
			out.println("<table width='100%' border='0'><tr><td width='17%'>ADICIONAR AREA </td><td width='83%' colspan='2'><select name='cmbAreas' id='cmbAreas'  onChange=''><option value='Seleccione' selected='selected'>Seleccione</option>");
			rs=mfh.ObtenerAreas();
			try {
				while(rs.next()){
				out.println("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
				}
				out.println("</select><input name='btnAdicionar' type='button' id='btnAdicionar' value='     Adicionar     ' onClick='ActualizarFormato()' /></td></tr></table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
			
			rs1=mfh.ObtenerRelacionAreaFormato(CodArea, CodigoFormato);
			try {
				if(rs1.next()){
					CodRelacion=rs1.getString(1);
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(CodRelacion==null){
				/**
				 * si el codigo de la relacion es null se procede a insertar la relacion del area con el formato
				 */
				mfh.RelacionAreaFormato(CodigoFormato, CodArea);
				rs3=mfh.ObtenerAreasFormato(CodigoFormato);
				out.println("<table width='100%' border='0'><tr id='cabecera2' class='style11'><td width='48%'><div align='center'>Nombre Area </div></td><td><div align='center'>Accion</div></td></tr>");
				try {
					while(rs3.next()){
						out.println("<tr><td><div align='center'>"+rs3.getString(1)+"</div></td><td><div align='center'><a href='#' onclick='omitir("+rs3.getString(2)+")'><strong><em>Omitir</em></strong></a></div></td></tr>");
					}
					rs3.getStatement().getConnection().close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				out.println("</table>");
			}
			else{
				out.println("Ya Existe Una Relacion De Esta Area Con Este Formato.");
				out.println("Por Favor Intente Otra Vez.");
			}
		}
		
		if(va.equals("1")){
			/**
			 * Aqui  se verifica si existe un formato con el mismo nombre.
			 */
			String CodFormato=null;
			
			rs=mfh.ObtenerCodigoFormato(NomFormato);
			try {
				if(rs.next()){
					CodFormato=rs.getString(1);
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			/**
			 * Si No existe Ninguno Con el mismo Nombre Se Procede a Ingresar El Nuevo Formato.
			 */
			if(CodFormato==null){
				
				mfh.CrearFormato(NomFormato);
			}
			/**
			 * Ya Creada El Formato Se Procede a Asignar Las Areas Que van Hacer Parte De Esta
			 * pero primero se comprueba si ya El Area no ha sido asignada al Mismo Formato.
			 */
			rs2=mfh.ObtenerCodigoFormato(NomFormato);
			try{
				if(rs2.next()){
					CodFormato=rs2.getString(1);
				}
				rs2.getStatement().getConnection().close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			rs1=mfh.ObtenerRelacionAreaFormato(CodArea, CodFormato);
			try {
				if(rs1.next()){
					CodRelacion=rs1.getString(1);
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(CodRelacion==null){
				/**
				 * si el codigo de la relacion es null se procede a insertar la relacion del area con el formato
				 */
				mfh.RelacionAreaFormato(CodFormato, CodArea);
				rs3=mfh.ObtenerAreasFormato(CodFormato);
				try {
					/**
					 * se muestran las areas que tiene el formato.
					 */
					out.print("<table border='1' class='style6' width='100%'><tr id='cabecera2'  align='center' class='style11'><td width='70%'>Areas</td><td width='30%' align='center' >Eliminar</td></tr>");
					while(rs3.next()){
						out.print("<tr><td>"+rs3.getString(1)+"</td><td><a href='#' onclick='omitir("+rs3.getString(2)+")'><strong><em>Omitir</em></strong></a></td></tr>");
					}
					out.print("</table>");
					rs3.getStatement().getConnection().close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			else{
				out.println("Ya Existe Una Relacion De Esta Area Con Este Formato.");
				out.println("Por Favor Intente Otra Vez.");
			}
			
		}
		
		if(va.equals("3")){
			mfh.OmitirAsignacion(codRelacion);
		}
		if(va.equals("4")){
			String CodFormato=null;
			rs=mfh.ObtenerCodigoFormato(NomFormato);
			try {
				if(rs.next()){
					CodFormato=rs.getString(1);
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			rs3=mfh.ObtenerAreasFormato(CodFormato);
			try {
				/**
				 * se muestran las areas que tiene el formato.
				 */
				out.print("<table border='1' class='style6' width='100%'><tr id='cabecera2'  align='center' class='style11'><td width='70%'>Areas</td><td width='30%' align='center' >Eliminar</td></tr>");
				while(rs3.next()){
					out.print("<tr><td>"+rs3.getString(1)+"</td><td><a href='#' onclick='omitir("+rs3.getString(2)+")'><strong><em>Omitir</em></strong></a></td></tr>");
				}
				out.print("</table>");
				rs3.getStatement().getConnection().close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		
		/*****************************OPCION DE MEDICAMENTOS EN HISTORIA CLINICA**********************/
		if(va.equals("5")){
			out.print("<table border='1' width='100%' ><tr><td><div id='cabecera2' class='style11' align='center'>MEDICAMENTOS</div></td></tr></table>");
			out.print("<table width='100%' border='1'><tr class='style6'>");			
			out.print("<td><label><input name='rbMedicamentos' type='radio' value='radiobutton' id='MA' onClick='RadioHisMedi()'>Medicamentos Activos</label></td>");			
			out.print("<td><label><input name='rbMedicamentos' type='radio' value='radiobutton' id='HM' onClick='RadioHisMedi()'>Historial Medicamentos</label></td>");
			out.print("</tr><tr><td colspan='2'><div id='CambioMedicamentos'></div></td></tr></table>");
		}
		
		if(va.equals("IDP")){			
			try {
				String CodDetForm=req.getParameter("CodDetForm");
				String CantidadDP=req.getParameter("CantidadDP");
				int Sumatoria=0;
				rs=mfh.ObtenerCantidadMedicamento(CodDetForm);
				if(rs.next()){
					Sumatoria=(rs.getInt(8))+(Integer.parseInt(CantidadDP));
					mfh.ActualizarDetalleDispensacion(Sumatoria+"", CodDetForm);
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("MA")){
			/*********MEDICAMENTOS ACTIVOS***********/
			try {
				rs=mfh.ObtenerMedicamentosActivos(CodPac, CodAdm);
				out.print("<table width='100%' border='1'><tr><td colspan='6'><div id='cabecera2' class='style11' align='center'>MEDICAMENTOS ACTIVOS</div></td></tr>");
				out.print("<tr class='style12'><td width='25%'><div align='center'>Nombre Medicamento</div></td><td width='25%'><div align='center'>Dosis</div></td><td width='5%'><div align='center'>Cantidad</div></td><td td width='35%'>Dispensar</td><td width='5%'>Unidades Suministradas</td><td width='5%'>Faltan por Suministrar</td></tr>");
				while(rs.next()){
					String sep="";
					String sep1="";
					//String sep3="";
					sep=rs.getString(2).replace(" ", "_");					
					int h=sep.split("_").length;
					String[] d=sep.split("_");		     	
					for(int g=0; g<h-1; g=g+1)
					{ 						
						 sep1=d[1];
						 //sep3=d[3];
					}
					if(sep1.equals("0.25")){
						sep1="1";
					}
					if(sep1.equals("0.5")){
						sep1="1";
					}
					if(sep1.equals("")){
						sep1="1";
					}
					int val1 =Integer.parseInt(sep1);
					int val2 =rs.getInt(6); 
					int Divs=(val2)/(val1);					
					out.print("<tr><td>"+rs.getString(1)+"</td>" +
							"<td>"+rs.getString(2)+"</td>" +
							"<td>"+rs.getString(4)+"</td><td> ");
					int ko=0;
					int sa=rs.getInt(5);
					int Divis=(sa)/(val1);
					
					for(int zz=-1;zz<Divis-1;zz=zz+1){
						ko=ko+1;
						out.println("Dosis:"+ko+" <input  type='checkbox' name='checkbox' checked=true disabled=true  id='Chk"+ko+"' >");
					}
					
					for(int z=-1;z<Divs-1; z=z+1){
						ko=ko+1;
											
						out.println("Dosis:"+ko+" <input  type='checkbox' name='checkbox'  id='Chk"+ko+"' onclick='IngresarDispensacion("+rs.getString(7)+","+val1+")' >");
					}				
					out.print("</td><td>"+rs.getString(5)+"</td>" +
							"<td>"+rs.getString(6)+"</td>" +
					"</tr>");
				}
				out.print("</table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("HM")){
			/*********HISTORICO MEDICAMENTOS*********/
			
			try {
				rs=mfh.ObtenerMedicamentosHistorico(CodPac);
				out.print("<table width='100%' border='1'><tr><td colspan='4'><div id='cabecera2' class='style11' align='center'>HISTORICO DE MEDICAMENTOS</div></td></tr>");
				out.print(" <tr class='style12'><td width='16%'><div align='center'>Fecha De Medicacion</div></td><td width='31%'><div align='center'>Nombre Medicamento</div></td><td width='26%'><div align='center'>Dosis</div></td><td width='27%'><div align='center'>Responsable</div></td></tr>");
				while(rs.next()){
					out.print("<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td></tr>");
				}
				out.print("</table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		
		
		if(va.equals("IM")){
			try {
				rs=mvf.ObtenerFormulaPendUsu(CodAdm, usuario);
				if(rs.next()){
					
					out.print("<table width='100%' border='1'><tr><td><div align='center' style='font-size:large; color:#FF0000 ' >Tiene Una Formula Sin Guardar.Debe Guardarla Antes de Formular Otra.</div></td></tr></table>");
					out.print("<table width='100%' border='1'>");					
					out.print("<tr><td colspan='2'><div align='center' id='cabecera2' class='style11'><span>Detalle Orden</span></div></td></tr>");
					out.print("<tr><td width='10%'><div><p><p>Detalle Orden<p></div></td><td width='90%'><textarea name='txtDetOrden' cols='50' rows='4' id='txtDetOrden' readonly='' >"+rs.getString(2)+"</textarea></td></tr>");
					out.print("</table>");					
					codFormulacion_fk=rs.getString(1);
					out.print("<table width='100%' border='1'><tr><td colspan='5'><div align='center' id='cabecera2' class='style11'><span>Detalle Formulaci&oacute;n</span></div></td></tr>");
					out.print("<tr><td width='42%'><div align='center'>Insumo</div></td><td width='27%'><div align='center'>Dosificaci&oacute;n</div></td><td width='21%'><div align='center'>Observaci&oacute;n</div></td><td width='10%'><div align='center'>Cantidad</div><input name='txtCodFormulacion' type='hidden' id='txtCodFormulacion' value="+codFormulacion_fk+"  /></td></tr>");
					
					rs2=mvf.DetalledeFormulacion(codFormulacion_fk);
					while(rs2.next()){
						out.print("<tr><td>"+rs2.getString(1)+"</td><td>"+rs2.getString(2)+"</td><td>"+rs2.getString(3)+"</td><td>"+rs2.getString(4)+"</td></tr>");
					}
					out.print("</table>");
					out.print("</div>");
					///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					out.print("<table width='100%' border='1' ><tr><td><div align='center'><input name='btnFinalizar' type='button' id='btnFinalizar' value='Finalizar' onclick='FinalFormulacion()' ></div></td></tr></table>");
					out.print("</div>");
					rs2.getStatement().getConnection().close();
					
				}
				else{
					String Pes="";
					String Tal="";
					rs2=mvf.ObtenerPesoTalla(CodAdm);
					if(rs2.next()){
						Pes=rs2.getString(3);
						Tal=rs2.getString(4);
					}
					rs2.getStatement().getConnection().close();
					out.print("<table width='100%' border='1'>");					
					out.print("<tr><td colspan='2'><div align='center' id='cabecera2' class='style11'><span>Formulacion Medicamentos e Insumos</span></div><input name='txtTipo' type='hidden' id='txtTipo' value='1' /></td></tr>");
					out.print("<tr><td width='10%'><div><p><p>Detalle Orden<p></div></td><td width='90%'><textarea name='txtDetOrden' cols='50' rows='4' id='txtDetOrden' onkeyup='ActualizaDetalleOrden()' ></textarea>" +
							"Peso <input name='txtPeso' type='text' onkeyup='SoloNumeros(form1.txtPeso)' value='"+Pes+"' /> (En Kg. EJ:56)   " +
							"Talla <input name='txtTalla' type='text' onkeyup='SoloNumeros(form1.txtTalla)' value='"+Tal+"'  /> (En Mts. EJ:1.68) " +
							"</td></tr>");
					out.print("<tr><td width='11%'>Medicamento</td><td width='89%'><input name='txtMedicamento' type='text' id='txtMedicamento' size='70' onKeyUp='autocompletarMedicamentoFormula()' /></td></tr>");
					out.print("<tr><td><input name='txtCodigoMed' type='hidden' id='txtCodigoMed' size='18' /></td><td><div id='SugerenciaMedFormula'></div></td></tr>");
					out.print("</table>");
					///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					out.print("<div id='ConteForm'>");
					out.print("<table width='100%' >");
					out.print("<tr><td><div id='DetAdministra'></div></td></tr>");
					out.print("</table>");
					///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					out.print("<div id='DetFormula'>");
					out.print("<table width='100%' border='1'><tr><td colspan='5'><div align='center' id='cabecera2' class='style11'><span>Detalle Formulaci&oacute;n</span></div></td></tr>");
					out.print("<tr><td width='42%'><div align='center'>Medicamento</div></td><td width='27%'><div align='center'>Dosificaci&oacute;n</div></td><td width='15%'><div align='center'>Observaci&oacute;n</div></td><td width='10%'><div align='center'>Cantidad</div><input name='txtCodFormulacion' type='hidden' id='txtCodFormulacion'  /></td><td width='6%'><div align='center'>Accion</div></td></tr>");
					out.print("<tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>");
					out.print("</table>");
					out.print("</div>");
					///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					//out.print("<table width='100%' border='1' ><tr><td><div align='center'><input name='btnFinalizar' type='button' id='btnFinalizar' value='Finalizar'></div></td></tr></table>");
					out.print("</div>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		if(va.equals("CamInsu")){
			out.print("<table width='100%' border='1'><tr><td colspan='7'><label><input name='cmbCantidad' type='hidden' id='cmbCantidad' /></label><input name='cmbLapso' type='hidden' id='cmbLapso' /> <input name='txtCantDosis' type='hidden' id='txtCantDosis' size='10' maxlength='10'  /><input name='Unidad' type='hidden' id='Unidad' size='10' maxlength='10'  /><input name='cmbViaAdmi' type='hidden' id='cmbViaAdmi' />Cantidad&nbsp;&nbsp;&nbsp;&nbsp;</td>");
			out.print("<td width='714'><input name='txtCantidad' type='text' id='txtCantidad' size='10' maxlength='10'  /></td><td width='207'><input name='btnAsignar' type='button' id='btnAsignar' value='Asignar' onclick='AsignarInsumo()' /></td></tr>");
			out.print("<tr><td width='131'>Observacion</td><td colspan='8'><textarea name='txtObservacion' cols='40' rows='3' id='txtObservacion' ></textarea></td></tr>");
			out.print("</table>");
		}
	
	}
}
