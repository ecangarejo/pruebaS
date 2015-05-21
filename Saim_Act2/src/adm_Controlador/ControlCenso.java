/**
 * Controlador:ControlCenso
 * Muestra el censo del los pacientes admitidos en la clinica
 * Desarrollado:yosimar valega
 */

package adm_Controlador;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import adm_bean.Censo;
import adm_logica.MetodoConsultarCenso;
import agm_metodo.MetodoAsignarCita;

import java.util.Calendar;

/**
 * Servlet implementation class ControlCenso
 */
public class ControlCenso extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * llamamos al procedimiento obtenerCenso/MetodoConsultarCenso para realizar la consulta de los pacientes q tienen admision activa estado=0
	 * Dentro del mientras que creamos un objeto de tipo censo donde introducimos los resultado del select
	 * Se crea un vector y se llena con los datos regogidos  en el bean Censo
	 * Se crea una session listacenso y se redirecciona al la jsp censo donde se mostrara los datos.
	 * 
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String va = req.getParameter("valor");
		MetodoConsultarCenso cc = new MetodoConsultarCenso();
		ResultSet rs=null;
		ResultSet rs1=null;
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out=res.getWriter();
		
		if(va.equals("PO")){
			/*
			out.println("<table width='100%' border='1' cellspacing='0' cellpadding='0'><tr><th width='9%' align='left' scope='col'>SERVICIO</th><th width='48%' scope='col' align='left'><select name='cmbServicios' id='cmbServicios' style='width:100%' >");
			out.println("<option value='Seleccione'>Seleccione</option>");
			rs=cc.UnidadesHospitalarias();
			try {
				while(rs.next()){
					out.println("<option value="+rs.getString("codsubarea")+">"+rs.getString("Ubicacion")+"</option>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		

			out.println("</select></th><th width='12%' scope='col' align='left'>FECHA INICIAL </th><th width='9%' scope='col' align='left'><input name='txtFechaInical' type='text' id='txtFechaInical' size='12' maxlength='10' onKeyup='masca(this,patron,true,0,0,0,event)' /></th><th width='11%' scope='col' align='left'>FECHA FINAL</th><th width='11%' scope='col' align='left'><input name='txtFechaFinal' type='text' id='txtFechaFinal' size='12' maxlength='10' onKeyup='masca(this,patron,true,0,0,0,event)' />    </th></tr>");

			out.println("<tr><td>&nbsp;</td><td colspan='4'><input name='btngenerar' type='button' id='btngenerar' class='boton4' value='Generar' onClick='GenerarPorcentaje()'></td><td>&nbsp;</td></tr></table>");
			*/
			out.println("<table width='100%'  class='style6'><tr><td  align='right'>FECHA INICIAL:</td><td><label>Dia<select name='cmbdiaIni' id='cmbdiaIni'><option value='Dia' selected='selected'>Dia</option><option value='1'>1</option> <option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option><option value='13'>13</option><option value='14'>14</option><option value='15'>15</option><option value='16'>16</option><option value='17'>17</option><option value='18'>18</option><option value='19'>19</option><option value='20'>20</option><option value='21'>21</option><option value='22'>22</option><option value='23'>23</option><option value='24'>24</option><option value='25'>25</option><option value='26'>26</option><option value='27'>27</option><option value='28'>28</option><option value='29'>29</option><option value='30'>30</option><option value='31'>31</option></select>Mes<select name='cmbmesIni' id='cmbmesIni'><option value='Mes' selected='selected'>Mes</option><option value='01'>Enero</option><option value='02'>Febrero</option><option value='03'>Marzo</option><option value='04'>Abril</option><option value='05'>Mayo</option><option value='06'>Junio</option><option value='07'>Julio</option><option value='08'>Agosto</option><option value='09'>Septiembre</option><option value='10'>Octubre</option><option value='11'>Noviembre</option><option value='12'>Diciembre</option></select>A&ntilde;o<input name='txtanioIni' type='text' id='txtanioIni' size='4' maxlength='4'></label></td></tr>");
			out.println("<tr ><td  align='right'>FECHA FINAL:</td><td >Dia<select name='cmbdiaFin' id='cmbdiaFin'><option value='Dia' selected='selected'>Dia</option><option value='1'>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option><option value='13'>13</option><option value='14'>14</option><option value='15'>15</option><option value='16'>16</option><option value='17'>17</option><option value='18'>18</option><option value='19'>19</option><option value='20'>20</option><option value='21'>21</option><option value='22'>22</option><option value='23'>23</option><option value='24'>24</option><option value='25'>25</option><option value='26'>26</option><option value='27'>27</option><option value='28'>28</option><option value='29'>29</option><option value='30'>30</option><option value='31'>31</option></select>Mes<select name='cmbmesFin' id='cmbmesFin'><option value='Mes' selected='selected'>Mes</option><option value='01'>Enero</option><option value='02'>Febrero</option><option value='03'>Marzo</option><option value='04'>Abril</option><option value='05'>Mayo</option><option value='06'>Junio</option><option value='07'>Julio</option><option value='08'>Agosto</option><option value='09'>Septiembre</option><option value='10'>Octubre</option><option value='11'>Noviembre</option><option value='12'>Diciembre</option></select>A&ntilde;o<input name='txtanioFin' type='text' id='txtanioFin' size='4' maxlength='4'></td></tr><tr><td >&nbsp;</td><td align=''><input name='btngenerar' type='button' id='btngenerar' class='boton4' value='Generar' onClick='GenerarPromedio()'></td></tr></table>");

		}
		
		if(va.equals("POR")){
			try {
				String txtFechaInical=req.getParameter("txtFechaInical"); 
				String txtFechaFinal=req.getParameter("txtFechaFinal"); 
				String cmbServicios=req.getParameter("cmbServicios"); 
			
				//1. se buscan todas las camas del servicio.
				//rs=cc.BuscarCamasUnidad(cmbServicios);			
				//while(rs.next()){
					//String CodCama=rs.getString("cen_numero_cama");
					//2. se buscar los movimientos de esa cama dentro del rango de fechas seleccionado.
					
				//}
				out.print("<table width='100%' border='1' cellspacing='0' cellpadding='0' ><tr><td>Nombre Unidad</td><td>Cantidad Pacientes</td><td>Sumatoria Dias </td><td>Promedio</td></tr>");
				rs1=cc.UnidadesHospitalarias();
				while(rs1.next()){
					out.print("<tr><td>"+rs1.getString("Ubicacion")+"</td>");
					String CantidadPacientes="";
					rs=cc.CantidadPacientes(rs1.getString("codsubarea"), txtFechaInical, txtFechaFinal);
					if(rs.next()){
						CantidadPacientes=rs.getString(1);
					}
					rs.getStatement().getConnection().close();
					out.print("<td>"+CantidadPacientes+"</td>");
					String SumatoriaDias="";
					rs=cc.SumatoriaDias(rs1.getString("codsubarea"), txtFechaInical, txtFechaFinal);
					if(rs.next()){
						SumatoriaDias=rs.getString("Diferencia2");						
					}
					rs.getStatement().getConnection().close();
					out.print("<td>"+SumatoriaDias+"</td>");
					
					long Promedio=0;
					Promedio=(Long.parseLong(SumatoriaDias))/(Long.parseLong(CantidadPacientes));
					out.print("<td>"+Promedio+"</td></tr>");
				}
				rs1.getStatement().getConnection().close(); 	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("1")){
			out.println("<table width='100%'  class='style6'><tr><td  align='right'>FECHA INICIAL:</td><td><label>Dia<select name='cmbdiaIni' id='cmbdiaIni'><option value='Dia' selected='selected'>Dia</option><option value='1'>1</option> <option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option><option value='13'>13</option><option value='14'>14</option><option value='15'>15</option><option value='16'>16</option><option value='17'>17</option><option value='18'>18</option><option value='19'>19</option><option value='20'>20</option><option value='21'>21</option><option value='22'>22</option><option value='23'>23</option><option value='24'>24</option><option value='25'>25</option><option value='26'>26</option><option value='27'>27</option><option value='28'>28</option><option value='29'>29</option><option value='30'>30</option><option value='31'>31</option></select>Mes<select name='cmbmesIni' id='cmbmesIni'><option value='Mes' selected='selected'>Mes</option><option value='01'>Enero</option><option value='02'>Febrero</option><option value='03'>Marzo</option><option value='04'>Abril</option><option value='05'>Mayo</option><option value='06'>Junio</option><option value='07'>Julio</option><option value='08'>Agosto</option><option value='09'>Septiembre</option><option value='10'>Octubre</option><option value='11'>Noviembre</option><option value='12'>Diciembre</option></select>A&ntilde;o<input name='txtanioIni' type='text' id='txtanioIni' size='4' maxlength='4'></label></td></tr>");
			out.println("<tr ><td  align='right'>FECHA FINAL:</td><td >Dia<select name='cmbdiaFin' id='cmbdiaFin'><option value='Dia' selected='selected'>Dia</option><option value='1'>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option><option value='13'>13</option><option value='14'>14</option><option value='15'>15</option><option value='16'>16</option><option value='17'>17</option><option value='18'>18</option><option value='19'>19</option><option value='20'>20</option><option value='21'>21</option><option value='22'>22</option><option value='23'>23</option><option value='24'>24</option><option value='25'>25</option><option value='26'>26</option><option value='27'>27</option><option value='28'>28</option><option value='29'>29</option><option value='30'>30</option><option value='31'>31</option></select>Mes<select name='cmbmesFin' id='cmbmesFin'><option value='Mes' selected='selected'>Mes</option><option value='01'>Enero</option><option value='02'>Febrero</option><option value='03'>Marzo</option><option value='04'>Abril</option><option value='05'>Mayo</option><option value='06'>Junio</option><option value='07'>Julio</option><option value='08'>Agosto</option><option value='09'>Septiembre</option><option value='10'>Octubre</option><option value='11'>Noviembre</option><option value='12'>Diciembre</option></select>A&ntilde;o<input name='txtanioFin' type='text' id='txtanioFin' size='4' maxlength='4'></td></tr><tr><td >&nbsp;</td><td align=''><input name='btngenerar' type='button' id='btngenerar' class='boton4' value='Generar' onClick='GenerarReporteEntidad()'></td></tr></table>");
		}
		if(va.equals("2")){
			out.println("<table width='100%'  class='style6'><tr><td  align='right'>FECHA INICIAL:</td><td><label>Dia<select name='cmbdiaIni' id='cmbdiaIni'><option value='Dia' selected='selected'>Dia</option><option value='1'>1</option> <option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option><option value='13'>13</option><option value='14'>14</option><option value='15'>15</option><option value='16'>16</option><option value='17'>17</option><option value='18'>18</option><option value='19'>19</option><option value='20'>20</option><option value='21'>21</option><option value='22'>22</option><option value='23'>23</option><option value='24'>24</option><option value='25'>25</option><option value='26'>26</option><option value='27'>27</option><option value='28'>28</option><option value='29'>29</option><option value='30'>30</option><option value='31'>31</option></select>Mes<select name='cmbmesIni' id='cmbmesIni'><option value='Mes' selected='selected'>Mes</option><option value='01'>Enero</option><option value='02'>Febrero</option><option value='03'>Marzo</option><option value='04'>Abril</option><option value='05'>Mayo</option><option value='06'>Junio</option><option value='07'>Julio</option><option value='08'>Agosto</option><option value='09'>Septiembre</option><option value='10'>Octubre</option><option value='11'>Noviembre</option><option value='12'>Diciembre</option></select>A&ntilde;o<input name='txtanioIni' type='text' id='txtanioIni' size='4' maxlength='4'></label></td></tr>");
			out.println("<tr ><td  align='right'>FECHA FINAL:</td><td >Dia<select name='cmbdiaFin' id='cmbdiaFin'><option value='Dia' selected='selected'>Dia</option><option value='1'>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option><option value='13'>13</option><option value='14'>14</option><option value='15'>15</option><option value='16'>16</option><option value='17'>17</option><option value='18'>18</option><option value='19'>19</option><option value='20'>20</option><option value='21'>21</option><option value='22'>22</option><option value='23'>23</option><option value='24'>24</option><option value='25'>25</option><option value='26'>26</option><option value='27'>27</option><option value='28'>28</option><option value='29'>29</option><option value='30'>30</option><option value='31'>31</option></select>Mes<select name='cmbmesFin' id='cmbmesFin'><option value='Mes' selected='selected'>Mes</option><option value='01'>Enero</option><option value='02'>Febrero</option><option value='03'>Marzo</option><option value='04'>Abril</option><option value='05'>Mayo</option><option value='06'>Junio</option><option value='07'>Julio</option><option value='08'>Agosto</option><option value='09'>Septiembre</option><option value='10'>Octubre</option><option value='11'>Noviembre</option><option value='12'>Diciembre</option></select>A&ntilde;o<input name='txtanioFin' type='text' id='txtanioFin' size='4' maxlength='4'></td></tr><tr><td >&nbsp;</td><td align=''><input name='btngenerar' type='button' id='btngenerar' class='boton4' value='Generar' onClick='GenerarReportePatologias()'></td></tr></table>");
		}
		if(va.equals("3")){
			out.println("<table width='100%'  class='style6'><tr><td  align='right'>FECHA INICIAL:</td><td><label>Dia<select name='cmbdiaIni' id='cmbdiaIni'><option value='Dia' selected='selected'>Dia</option><option value='1'>1</option> <option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option><option value='13'>13</option><option value='14'>14</option><option value='15'>15</option><option value='16'>16</option><option value='17'>17</option><option value='18'>18</option><option value='19'>19</option><option value='20'>20</option><option value='21'>21</option><option value='22'>22</option><option value='23'>23</option><option value='24'>24</option><option value='25'>25</option><option value='26'>26</option><option value='27'>27</option><option value='28'>28</option><option value='29'>29</option><option value='30'>30</option><option value='31'>31</option></select>Mes<select name='cmbmesIni' id='cmbmesIni'><option value='Mes' selected='selected'>Mes</option><option value='01'>Enero</option><option value='02'>Febrero</option><option value='03'>Marzo</option><option value='04'>Abril</option><option value='05'>Mayo</option><option value='06'>Junio</option><option value='07'>Julio</option><option value='08'>Agosto</option><option value='09'>Septiembre</option><option value='10'>Octubre</option><option value='11'>Noviembre</option><option value='12'>Diciembre</option></select>A&ntilde;o<input name='txtanioIni' type='text' id='txtanioIni' size='4' maxlength='4'></label></td></tr>");
			out.println("<tr ><td  align='right'>FECHA FINAL:</td><td >Dia<select name='cmbdiaFin' id='cmbdiaFin'><option value='Dia' selected='selected'>Dia</option><option value='1'>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option><option value='13'>13</option><option value='14'>14</option><option value='15'>15</option><option value='16'>16</option><option value='17'>17</option><option value='18'>18</option><option value='19'>19</option><option value='20'>20</option><option value='21'>21</option><option value='22'>22</option><option value='23'>23</option><option value='24'>24</option><option value='25'>25</option><option value='26'>26</option><option value='27'>27</option><option value='28'>28</option><option value='29'>29</option><option value='30'>30</option><option value='31'>31</option></select>Mes<select name='cmbmesFin' id='cmbmesFin'><option value='Mes' selected='selected'>Mes</option><option value='01'>Enero</option><option value='02'>Febrero</option><option value='03'>Marzo</option><option value='04'>Abril</option><option value='05'>Mayo</option><option value='06'>Junio</option><option value='07'>Julio</option><option value='08'>Agosto</option><option value='09'>Septiembre</option><option value='10'>Octubre</option><option value='11'>Noviembre</option><option value='12'>Diciembre</option></select>A&ntilde;o<input name='txtanioFin' type='text' id='txtanioFin' size='4' maxlength='4'></td></tr><tr><td >&nbsp;</td><td align=''><input name='btngenerar' type='button' id='btngenerar' class='boton4' value='Generar' onClick='GenerarReportesUsuario()'></td></tr></table>");
		}
		if(va.equals("5")){
			out.println("<table width='100%'  class='style6'><tr><td><label> SALIDA DE URGENCIA <input name='radiobutton1' type='radio' value='SALIDA DE URGENCIA' id='1' ></label></td><td><label> SALIDA DE HOSPITALIZACION <input name='radiobutton1' type='radio' value='SALIDA DE HOSPITALIZACION' id='1' ></label></td></tr><tr><td>SELECCIONE ENTIDAD:</td><td><select name='cmbEntidad' id='cmbEntidad'><option value='Seleccione' selected='selected'>Seleccione</option>");
			rs=cc.ObtenerEntidadesConvenio();
			try {
				while(rs.next()){
					out.print("<option value="+rs.getString(2)+">"+rs.getString(1)+"</option>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			out.println("</select></td></tr><tr><td>FECHA INICIAL:</td><td><label>Dia<select name='cmbdiaIni' id='cmbdiaIni'><option value='Dia' selected='selected'>Dia</option><option value='1'>1</option> <option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option><option value='13'>13</option><option value='14'>14</option><option value='15'>15</option><option value='16'>16</option><option value='17'>17</option><option value='18'>18</option><option value='19'>19</option><option value='20'>20</option><option value='21'>21</option><option value='22'>22</option><option value='23'>23</option><option value='24'>24</option><option value='25'>25</option><option value='26'>26</option><option value='27'>27</option><option value='28'>28</option><option value='29'>29</option><option value='30'>30</option><option value='31'>31</option></select>Mes<select name='cmbmesIni' id='cmbmesIni'><option value='Mes' selected='selected'>Mes</option><option value='01'>Enero</option><option value='02'>Febrero</option><option value='03'>Marzo</option><option value='04'>Abril</option><option value='05'>Mayo</option><option value='06'>Junio</option><option value='07'>Julio</option><option value='08'>Agosto</option><option value='09'>Septiembre</option><option value='10'>Octubre</option><option value='11'>Noviembre</option><option value='12'>Diciembre</option></select>A&ntilde;o<input name='txtanioIni' type='text' id='txtanioIni' size='4' maxlength='4'></label></td></tr>");
			out.println("<tr ><td>FECHA FINAL:</td><td >Dia<select name='cmbdiaFin' id='cmbdiaFin'><option value='Dia' selected='selected'>Dia</option><option value='1'>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option><option value='13'>13</option><option value='14'>14</option><option value='15'>15</option><option value='16'>16</option><option value='17'>17</option><option value='18'>18</option><option value='19'>19</option><option value='20'>20</option><option value='21'>21</option><option value='22'>22</option><option value='23'>23</option><option value='24'>24</option><option value='25'>25</option><option value='26'>26</option><option value='27'>27</option><option value='28'>28</option><option value='29'>29</option><option value='30'>30</option><option value='31'>31</option></select>Mes<select name='cmbmesFin' id='cmbmesFin'><option value='Mes' selected='selected'>Mes</option><option value='01'>Enero</option><option value='02'>Febrero</option><option value='03'>Marzo</option><option value='04'>Abril</option><option value='05'>Mayo</option><option value='06'>Junio</option><option value='07'>Julio</option><option value='08'>Agosto</option><option value='09'>Septiembre</option><option value='10'>Octubre</option><option value='11'>Noviembre</option><option value='12'>Diciembre</option></select>A&ntilde;o<input name='txtanioFin' type='text' id='txtanioFin' size='4' maxlength='4'></td></tr><tr><td >&nbsp;</td><td align=''><input name='btngenerar' type='button' id='btngenerar' class='boton4' value='Generar' onClick='GenerarReportesSalidas()'></td></tr></table>");
		}
		
		if(va.equals("6")){
			out.println("<table width='100%'  class='style6'><tr><td></td><td></td></tr><tr><td>SELECCIONE ENTIDAD:</td><td><select name='cmbEntidad' id='cmbEntidad'><option value='Seleccione' selected='selected'>Seleccione</option>");
			rs=cc.ObtenerServicios();
			try {
				while(rs.next()){
					out.print("<option title='"+rs.getString(2)+"' value='"+rs.getString(2)+"'>"+rs.getString(2)+"</option>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			out.println("</select></td></tr><tr><td></td><td></td></tr>");
			out.println("<tr ><td></td><td ></td></tr><tr><td >&nbsp;</td><td align=''><input name='btngenerar' type='button' id='btngenerar' class='boton4' value='Generar' onClick='GenerarTrasladosActivos()'></td></tr></table>");
		}
		
		if(va.equals("gral")){
			out.print("<table border='1' width='100%' ><tr><td><div id='cabecera2' class='style11' align='center'>CENSO GENERAL </div></td></tr></table><table width='100%' border='1'><tr class='style6'><td><div align='center'><input name='btnGenerar' type='button' class='boton4' id='btnGenerar' value='     Generar     ' onClick='impresion()'></div></td></tr></table>");
		}
		
		if(va.equals("pser")){			
			out.print("<table border='1' width='100%' ><tr><td><div id='cabecera2' class='style11' align='center'>CENSO POR SERVICIO </div></td></tr></table><table width='100%' border='1'><tr class='style6'><td width='8%'><label>SERVICIO</label></td><td width='43%'><select name='cmbServicio' class='style3' id='cmbServicio'><option value='Seleccione' selected='selected'>Seleccione</option>");
			rs=cc.ObtenerServicios();
			try {
				while(rs.next()){
					out.print("<option value='"+rs.getString(2)+"'>"+rs.getString(2)+"</option>");
				}
				out.print("</select></td><td width='49%'><div align='center'><input name='btnGenerar' type='button' class='boton4' id='btnGenerar' value='     Generar     ' onClick='CensoServicio()'></div></td></tr></table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
		}
		
		if(va.equals("pent")){
			out.print("<table border='1' width='100%' ><tr><td><div id='cabecera2' class='style11' align='center'>CENSO POR ENTIDAD </div></td></tr></table><table width='100%' border='1'><tr class='style6'><td width='8%'><label>ENTIDAD</label></td><td width='53%'><select name='cmbEntidad' class='style3' id='cmbEntidad'><option value='Seleccione' selected='selected'>Seleccione</option>");
			try {
				rs=cc.ObtenerEntidades();
				while(rs.next()){
					out.print("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
				}
				out.print("</select></td><td width='39%'><div align='center'><input name='btnGenerar' type='button' class='boton4' id='btnGenerar' value='     Generar     ' onClick='CensoEntidad()'></div></td></tr></table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
		}
		
		MetodoAsignarCita mac = new MetodoAsignarCita();
		
		if(va.equals("RCE")){			
		//con entidad
			try {
				String FechaInicial=req.getParameter("fechaIni");
				String FechaFinal=req.getParameter("fechaFin");
				String TipoSalida=req.getParameter("TipoSalida");
				String CodEps=req.getParameter("CodEps");
				rs=cc.ObtenerPacientesSalidaEntidad(FechaInicial, FechaFinal, TipoSalida, CodEps);
				int coc=0;
				out.print("<table width='100%' border='1' cellspacing='0' style='font-size:9px'>");
				out.print("<tr><td colspan='12'>Reporte de "+TipoSalida+" del "+FechaInicial+" al "+FechaFinal+"</td></tr>");
				out.print("<tr><td>N�</td><td>Nombre Paciente</td><td>Documento</td><td>Edad</td><td>Entidad</td><td>Afiliacion</td><td>Nivel</td><td>Ingreso</td><td>Egreso</td><td>Dx Ingreso</td><td>Dx Egreso</td><td>Estado</td></tr>");
				while(rs.next()){
					coc=coc+1;
					out.print("<tr><td>"+coc+"</td><td>"+rs.getString("Paciente")+"</td><td>"+rs.getString("Documento")+"</td><td>"+rs.getString("edad")+"</td><td>"+rs.getString("Entidad")+"</td><td>"+rs.getString("tipo_afiliacion")+"</td><td>"+rs.getString("nivel_cotizante")+"</td><td>"+rs.getString("Ingreso")+"</td><td>"+rs.getString("Egreso")+"</td>");
					
					rs1=mac.BuscarDiagnosticoCex(rs.getString("adm_numero_ingreso"));
					if(rs1.next()){
						out.print("<td>"+rs1.getString("dx")+"</td>");
					}else{
						out.print("<td>-</td>");
					}
					rs1.getStatement().getConnection().close();
					
					rs1=mac.BuscarDiagnosticoEgreso(rs.getString("adm_numero_ingreso"));
					if(rs1.next()){
						out.print("<td>"+rs1.getString("dx")+"</td>");
					}else{
						out.print("<td>-</td>");
					}
					rs1.getStatement().getConnection().close();
					
					out.print("<td>"+rs.getString("Estado")+"</td>");
					
					out.print("</tr>");
				}
				out.print("</table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		
		if(va.equals("RSE")){
			//sin entidad
			try {
				String FechaInicial=req.getParameter("fechaIni");
				String FechaFinal=req.getParameter("fechaFin");
				String TipoSalida=req.getParameter("TipoSalida");
				rs=cc.ObtenerPacientesSalida(FechaInicial, FechaFinal, TipoSalida);
				int coc=0;
				out.print("<table width='100%' border='1' cellspacing='0' style='font-size:9px'>");
				out.print("<tr><td colspan='12'>Reporte de "+TipoSalida+" del "+FechaInicial+" al "+FechaFinal+"</td></tr>");
				out.print("<tr><td>N�</td><td>Nombre Paciente</td><td>Documento</td><td>Edad</td><td>Entidad</td><td>Afiliacion</td><td>Nivel</td><td>Ingreso</td><td>Egreso</td><td>Dx Ingreso</td><td>Dx Egreso</td><td>Estado</td></tr>");
				while(rs.next()){
					coc=coc+1;
					out.print("<tr><td>"+coc+"</td><td>"+rs.getString("Paciente")+"</td><td>"+rs.getString("Documento")+"</td><td>"+rs.getString("edad")+"</td><td>"+rs.getString("Entidad")+"</td><td>"+rs.getString("tipo_afiliacion")+"</td><td>"+rs.getString("nivel_cotizante")+"</td><td>"+rs.getString("Ingreso")+"</td><td>"+rs.getString("Egreso")+"</td>");
					
					rs1=mac.BuscarDiagnosticoCex(rs.getString("adm_numero_ingreso"));
					if(rs1.next()){
						out.print("<td>"+rs1.getString("dx")+"</td>");
					}else{
						out.print("<td>-</td>");
					}
					rs1.getStatement().getConnection().close();
					
					rs1=mac.BuscarDiagnosticoEgreso(rs.getString("adm_numero_ingreso"));
					if(rs1.next()){
						out.print("<td>"+rs1.getString("dx")+"</td>");
					}else{
						out.print("<td>-</td>");
					}
					rs1.getStatement().getConnection().close();
					
					out.print("<td>"+rs.getString("Estado")+"</td>");
					
					out.print("</tr>");
				}
				out.print("</table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*HttpSession session = request.getSession(true);
		response.setContentType("text/html;charset=UTF-8");	
		ResultSet rs = null;
		MetodoConsultarCenso cc = new MetodoConsultarCenso();
		rs = cc.obtenerCenso();
		ResultSet rs1 = null;
		Vector l = new Vector();
		
		
		String nombre, cama, codigopacientek, edad;
		
		try{
			int numero = 0;
			Date fechan;
			while(rs.next()){
				Censo c = new Censo();				
				codigopacientek = rs.getString(7);
				fechan = (Date)rs.getDate(8);
				Calendar cal = new GregorianCalendar();
				cal.setTime(fechan); 				
				numero = cc.calcularEdad(cal);
				cama = rs.getString("codigocama");
				nombre = rs.getString(4)+" "+rs.getString(5);				
				
				c.setCama(cama);
				c.setPaciente(nombre);
				c.setDiagnostico(".");
				c.setSexo(rs.getString(3));
				c.setEdad(numero);
				c.setSemanac(rs.getString(2));
				c.setEps(rs.getString(6));
				c.setMedico(".");
				c.setFechaentrada(rs.getString(10));
				c.setDias(rs.getString(11));
				c.setObservacione(rs.getString(9));	
				c.setPabellon(rs.getString(12));
				c.setUbicacion(rs.getString(13));
				c.setCodigo(rs.getString(7));
				l.add(c);
			}
			rs.getStatement().getConnection().close();
			
			session.setAttribute("listacenso", l);
			response.sendRedirect("censo.jsp");
			
			
		}
		catch(Exception ex){
			ex.getMessage();
		}*/
		
	}

	

}
