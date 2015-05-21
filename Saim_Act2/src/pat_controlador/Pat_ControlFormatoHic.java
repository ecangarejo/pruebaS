
package pat_controlador;


import pat_metodo.MetodoFormatoHic;

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
public class Pat_ControlFormatoHic extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	/**
	 *autocompletar de todas las areas,para posteriormente escojerla.
	 */
		
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
		PrintWriter out=res.getWriter();
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		ResultSet rs3=null;
		String CodRelacion=null;
		MetodoFormatoHic mfh = new MetodoFormatoHic ();
		
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
		
	}
}
