/**
 * controlador: ControlCrearGrupo se encuentra el proceso para la 
 * creacion los grupos y la actualizacion de los datos del paciente(si es modulo independiente)
*/
package img_controlador;

import img_logica.MetodoCrearGrupo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ControlCrearGrupo
 */
public class ControlCrearGrupo extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String area=req.getParameter("area");
		String va = req.getParameter("valor");
		MetodoCrearGrupo mcg = new MetodoCrearGrupo ();		
		res.setContentType("text/html;charset=UTF-8");
		if(va.equals("1")){
			mcg.CrearGrupo(area);
			res.sendRedirect("img_CrearArea.jsp");
		}
		if(va.equals("5")){			
			String codigo=req.getParameter("codigo");
			String nombres=req.getParameter("nombre");
			String genero=req.getParameter("genero");
			String eps=req.getParameter("eps");
			String telefono=req.getParameter("telefono");
			String direccion=req.getParameter("direccion");
			String email=req.getParameter("email");
			String apellidos=req.getParameter("apellido");
			String nacimiento=req.getParameter("nacimiento");
			String cedulas=req.getParameter("cedula");
			String TipoDoc=req.getParameter("TipoDoc");
			//System.out.println("codigo "+codigo+" nombres "+nombres+" genero "+genero+" eps "+eps+" telefono "+telefono+" direccion "+direccion+" email "+email+" apellidos "+apellidos+" nacimiento "+nacimiento+" cedulas "+cedulas);
			
			if(nacimiento==""){
				//System.out.print("nacimiento "+nacimiento);
				mcg.ActualizarDemograficoVacio(codigo, nombres, genero, eps,telefono,direccion,email,apellidos,nacimiento,cedulas,TipoDoc);
				res.sendRedirect("Imagenologia.jsp");
			}
			else{
				//System.out.print("nacimiento lleno "+nacimiento);
				mcg.ActualizarDemografico(codigo, nombres, genero, eps,telefono,direccion,email,apellidos,nacimiento,cedulas);
				res.sendRedirect("Imagenologia.jsp");
			}
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out=res.getWriter();
		String va = req.getParameter("valor");
		ResultSet rs=null;
		ResultSet rsEco=null;
		ResultSet rsRmc=null;
		ResultSet rs1=null;
		ResultSet rsEco1=null;
		ResultSet rsRmc1=null;
		ResultSet rs2=null;
		ResultSet rsEco2=null;
		ResultSet rsRmc2=null;
		ResultSet rsCat=null;
		ResultSet rsCat1=null;
		ResultSet rsCat2=null;
		MetodoCrearGrupo mcg = new MetodoCrearGrupo ();
		String cedula=req.getParameter("cedula");
		String codigo=req.getParameter("codigo");
		String nombres=req.getParameter("nombre");
		String genero=req.getParameter("genero");
		String eps=req.getParameter("eps");
		String telefono=req.getParameter("telefono");
		String direccion=req.getParameter("direccion");
		String email=req.getParameter("email");
		String apellidos=req.getParameter("apellido");
		String nacimiento=req.getParameter("nacimiento");
		String cedulas=req.getParameter("cedula");
		String TipoDoc=req.getParameter("TipoDoc"); 
		if(va.equals("1")){
			int contador=0;
			rs=mcg.ObtenerExamenesPacienteNuevo(cedula,TipoDoc);	
			rsEco=mcg.ObtenerExamenesEco(cedula, TipoDoc);
			rsRmc=mcg.ObtenerExamenesRmc(cedula, TipoDoc);
			rsCat=mcg.buscarInformesCateterismosRealizados(cedula, TipoDoc);
			rs1=mcg.ObtenerExamenesPacienteNuevo(cedula,TipoDoc);	
			rsEco1=mcg.ObtenerExamenesEco(cedula, TipoDoc);
			rsRmc1=mcg.ObtenerExamenesRmc(cedula, TipoDoc);
			rsCat1=mcg.buscarInformesCateterismosRealizados(cedula, TipoDoc);
			rs2=mcg.ObtenerExamenesPacienteNuevo(cedula,TipoDoc);	
			rsEco2=mcg.ObtenerExamenesEco(cedula, TipoDoc);
			rsRmc2=mcg.ObtenerExamenesRmc(cedula, TipoDoc);
			rsCat2=mcg.buscarInformesCateterismosRealizados(cedula, TipoDoc);
			
			try{
				if((rs1.next())||(rsEco1.next())||(rsRmc1.next())||(rsCat1.next())){
					if (rs2.next()){
						System.out.println("entro a img");
						out.println("<table width='100%' class='style6'><tr><td colspan='3'>NOMBRE DEL PACIENTE: <b class='style9'>"+rs2.getString("NombrePaciente")+"</b> </td></tr><tr id='cabecera2' class='style11' align='center'><td colspan='3'>EXAMENES REALIZADOS </td></tr><tr><td>&nbsp;</td></tr> </table>  <div class='scrollbox5'><table width='100%' border='1' bordercolor='#1C777B'><tr id='cabecera2' class='style11' align='center'><td width='147'>Fecha</td><td width='517'>Nombre Del Examen</td><td width='99' colspan='4'>Aprobado Por </td></tr>");
						
					}else{
						if (rsEco2.next()){
							System.out.println("entro a eco");
							out.println("<table width='100%' class='style6'><tr><td colspan='3'>NOMBRE DEL PACIENTE: <b class='style9'>"+rsEco2.getString("NombrePaciente")+"</b> </td></tr><tr id='cabecera2' class='style11' align='center'><td colspan='3'>EXAMENES REALIZADOS </td></tr><tr><td>&nbsp;</td></tr> </table>  <div class='scrollbox5'><table width='100%' border='1' bordercolor='#1C777B'><tr id='cabecera2' class='style11' align='center'><td width='147'>Fecha</td><td width='517'>Nombre Del Examen</td><td width='99' colspan='4'>Aprobado Por </td></tr>");
						}else{
							if (rsRmc2.next()){
								System.out.println("entro a rmc");
								out.println("<table width='100%' class='style6'><tr><td colspan='3'>NOMBRE DEL PACIENTE: <b class='style9'>"+rsRmc2.getString("NombrePaciente")+"</b> </td></tr><tr id='cabecera2' class='style11' align='center'><td colspan='3'>EXAMENES REALIZADOS </td></tr><tr><td>&nbsp;</td></tr> </table>  <div class='scrollbox5'><table width='100%' border='1' bordercolor='#1C777B'><tr id='cabecera2' class='style11' align='center'><td width='147'>Fecha</td><td width='517'>Nombre Del Examen</td><td width='99' colspan='4'>Aprobado Por </td></tr>");
							}else{
								if (rsCat2.next()){
									System.out.println("entro a cateterismo");
									out.println("<table width='100%' class='style6'><tr><td colspan='3'>NOMBRE DEL PACIENTE: <b class='style9'>"+rsCat2.getString("NombrePaciente")+"</b> </td></tr><tr id='cabecera2' class='style11' align='center'><td colspan='3'>EXAMENES REALIZADOS </td></tr><tr><td>&nbsp;</td></tr> </table>  <div class='scrollbox5'><table width='100%' border='1' bordercolor='#1C777B'><tr id='cabecera2' class='style11' align='center'><td width='147'>Fecha</td><td width='517'>Nombre Del Examen</td><td width='99' colspan='4'>Aprobado Por </td></tr>");
								
								}
							}
						}
					}
					
					
					if (rs.next()){
						
					out.println("<tr class='style6' align='center'><td>"+rs.getString("Fecha_Lectura")+"</td>" +
							"<td><a  href='#'onclick='mostarexamenes("+rs.getString("codigo")+",&quot;"+rs.getString("aprobacion")+"&quot;)'>"+rs.getString("nombre")+"</a></td>" +
									"<td colspan='4'>"+rs.getString("aprobacion")+"</td>" +
											"<td><label><input name='txtcodIce' type='hidden' id='txtcodIce' /></label></td></tr>");
					}
					
					
					
					if (rsEco.next()){
						//System.out.println("entro a eco2");
						out.println("<tr class='style6' align='center'><td>"+rsEco.getString("Fecha_Lectura")+"</td>" +
								"<td><a  href='#'onclick='mostrarInformesCardiologia("+rsEco.getString(2)+")'>"+rsEco.getString(5)+"</a></td>" +
										"<td colspan='4'>"+rsEco.getString(1)+"</td>" +
												"<td><label><input name='txtcodIce' type='hidden' id='txtcodIce' /></label></td></tr>");
						
						
					}
					
                    if (rsCat.next()){
                    	//System.out.println("entro a cateterismo2");
						out.println("<tr class='style6' align='center'><td>"+rsCat.getString(4)+"</td>" +
								"<td><a  href='#'onclick='mostrarInformesHemodinamia("+rsCat.getString(1)+")'>"+rsCat.getString(3)+"</a></td>" +
										"<td colspan='4'>"+rsCat.getString(6)+"</td>" +
												"<td><label><input name='txtcodIce' type='hidden' id='txtcodIce' /></label></td></tr>");
					}
					

					if(rsRmc.next()){
						//System.out.println("entro a rmc2");
						out.println("<tr class='style6' align='center'><td>"+rsRmc.getString("Fecha_Lectura")+"</td>" +
								"<td><a  href='#'onclick='mostrarInformesRmc("+rsRmc.getString(2)+")'>"+rsRmc.getString(5)+"</a></td>" +
										"<td colspan='4'>"+rsRmc.getString(1)+"</td>" +
												"<td><label><input name='txtcodIce' type='hidden' id='txtcodIce' /></label></td></tr>");
					}
					
					contador=0;
					
					while(rs.next()){
						out.println("<tr class='style6' align='center'><td>"+rs.getString("Fecha_Lectura")+"</td>" +
								"<td><a  href='#'onclick='mostarexamenes("+rs.getString("codigo")+",&quot;"+rs.getString("aprobacion")+"&quot;)'>"+rs.getString("nombre")+"</a></td>" +
										"<td colspan='4'>"+rs.getString("aprobacion")+"</td>" +
												"<td><label><input name='txtcodIce' type='hidden' id='txtcodIce' /></label></td></tr>");
						contador=contador+1;
					}
					
					while (rsEco.next()){
						//System.out.println("entro a eco3");
						out.println("<tr class='style6' align='center'><td>"+rsEco.getString("Fecha_Lectura")+"</td>" +
								"<td><a  href='#'onclick='mostrarInformesCardiologia("+rsEco.getString(2)+")'>"+rsEco.getString(5)+"</a></td>" +
										"<td colspan='4'>"+rsEco.getString(1)+"</td>" +
												"<td><label><input name='txtcodIce' type='hidden' id='txtcodIce' /></label></td></tr>");
					}
					
                     contador=0;
					
					while(rsRmc.next()){
						//System.out.println("entro a rmc3");
						out.println("<tr class='style6' align='center'><td>"+rsRmc.getString("Fecha_Lectura")+"</td>" +
								"<td><a  href='#'onclick='mostrarInformesRmc("+rsRmc.getString(2)+")'>"+rsRmc.getString(5)+"</a></td>" +
										"<td colspan='4'>"+rsRmc.getString(1)+"</td>" +
												"<td><label><input name='txtcodIce' type='hidden' id='txtcodIce' /></label></td></tr>");
					contador=contador+1;
					}
					
					
					 contador=0;
						
						while(rsCat.next()){
							//System.out.println("entro a cateterismo3");
							out.println("<tr class='style6' align='center'><td>"+rsCat.getString(4)+"</td>" +
									"<td><a  href='#'onclick='mostrarInformesHemodinamia("+rsCat.getString(1)+")'>"+rsCat.getString(3)+"</a></td>" +
											"<td colspan='4'>"+rsCat.getString(6)+"</td>" +
													"<td><label><input name='txtcodIce' type='hidden' id='txtcodIce' /></label></td></tr>");
						contador=contador+1;
						}
					

				}else{
					out.print("NO SE ENCONTRARON ESTUDIOS RELACIONADOS A ESTE PACIENTE.");
				}
				
				
				
				out.println("</table></div>");
				rs.getStatement().getConnection().close();
				rs1.getStatement().getConnection().close();
				rs2.getStatement().getConnection().close();
				rsEco.getStatement().getConnection().close();
				rsEco1.getStatement().getConnection().close();
				rsEco2.getStatement().getConnection().close();
				rsRmc.getStatement().getConnection().close();
				rsRmc1.getStatement().getConnection().close();
				rsRmc2.getStatement().getConnection().close();
			}
			catch(SQLException e){
				System.out.println("Error en Controlador va=4 "+e);
				e.printStackTrace();
			}
		}
		
		
		if(va.equals("2")){			
			out.println("<table  width='100%'><tr><td align='center'><input class='boton4' name='btngenerar' type='button' id='btngenerar' value='Generar' onclick='generartodo()' /></td>" +
					//"<td align='center'><input class='boton4' name='btngenerarExcel' type='button' id='btngenerarExcel' value='Generar Excel' onclick='GenerarExcel()' /></td>" +
					"</tr></table>");			
		}
		
		
		
		if(va.equals("3")){
			out.println("<table width='100%'  class='style6'><tr><td  align='right'>FECHA INICIAL:</td><td><label>Dia<select name='cmbdiaIni' id='cmbdiaIni'><option value='Dia' selected='selected'>Dia</option><option value='1'>1</option> <option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option><option value='13'>13</option><option value='14'>14</option><option value='15'>15</option><option value='16'>16</option><option value='17'>17</option><option value='18'>18</option><option value='19'>19</option><option value='20'>20</option><option value='21'>21</option><option value='22'>22</option><option value='23'>23</option><option value='24'>24</option><option value='25'>25</option><option value='26'>26</option><option value='27'>27</option><option value='28'>28</option><option value='29'>29</option><option value='30'>30</option><option value='31'>31</option></select>Mes<select name='cmbmesIni' id='cmbmesIni'><option value='Mes' selected='selected'>Mes</option><option value='01'>Enero</option><option value='02'>Febrero</option><option value='03'>Marzo</option><option value='04'>Abril</option><option value='05'>Mayo</option><option value='06'>Junio</option><option value='07'>Julio</option><option value='08'>Agosto</option><option value='09'>Septiembre</option><option value='10'>Octubre</option><option value='11'>Noviembre</option><option value='12'>Diciembre</option></select>A&ntilde;o<input name='txtanioIni' type='text' id='txtanioIni' size='4' maxlength='4'></label></td></tr>");
			out.println("<tr ><td  align='right'>FECHA FINAL:</td><td >Dia<select name='cmbdiaFin' id='cmbdiaFin'><option value='Dia' selected='selected'>Dia</option><option value='1'>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option><option value='13'>13</option><option value='14'>14</option><option value='15'>15</option><option value='16'>16</option><option value='17'>17</option><option value='18'>18</option><option value='19'>19</option><option value='20'>20</option><option value='21'>21</option><option value='22'>22</option><option value='23'>23</option><option value='24'>24</option><option value='25'>25</option><option value='26'>26</option><option value='27'>27</option><option value='28'>28</option><option value='29'>29</option><option value='30'>30</option><option value='31'>31</option></select>Mes<select name='cmbmesFin' id='cmbmesFin'><option value='Mes' selected='selected'>Mes</option><option value='01'>Enero</option><option value='02'>Febrero</option><option value='03'>Marzo</option><option value='04'>Abril</option><option value='05'>Mayo</option><option value='06'>Junio</option><option value='07'>Julio</option><option value='08'>Agosto</option><option value='09'>Septiembre</option><option value='10'>Octubre</option><option value='11'>Noviembre</option><option value='12'>Diciembre</option></select>A&ntilde;o<input name='txtanioFin' type='text' id='txtanioFin' size='4' maxlength='4'></td></tr><tr><td >&nbsp;</td><td align=''><input name='btngenerar' type='button' id='btngenerar' class='boton4' value='Generar' onClick='generarfechas()'></td></tr></table>");
		}
		
		if(va.equals("6")){
			out.println("<table width='100%'  class='style6'><tr><td  align='right'>FECHA INICIAL:</td><td><label>Dia<select name='cmbdiaIni' id='cmbdiaIni'><option value='Dia' selected='selected'>Dia</option><option value='1'>1</option> <option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option><option value='13'>13</option><option value='14'>14</option><option value='15'>15</option><option value='16'>16</option><option value='17'>17</option><option value='18'>18</option><option value='19'>19</option><option value='20'>20</option><option value='21'>21</option><option value='22'>22</option><option value='23'>23</option><option value='24'>24</option><option value='25'>25</option><option value='26'>26</option><option value='27'>27</option><option value='28'>28</option><option value='29'>29</option><option value='30'>30</option><option value='31'>31</option></select>Mes<select name='cmbmesIni' id='cmbmesIni'><option value='Mes' selected='selected'>Mes</option><option value='01'>Enero</option><option value='02'>Febrero</option><option value='03'>Marzo</option><option value='04'>Abril</option><option value='05'>Mayo</option><option value='06'>Junio</option><option value='07'>Julio</option><option value='08'>Agosto</option><option value='09'>Septiembre</option><option value='10'>Octubre</option><option value='11'>Noviembre</option><option value='12'>Diciembre</option></select>A&ntilde;o<input name='txtanioIni' type='text' id='txtanioIni' size='4' maxlength='4'></label></td></tr>");
			out.println("<tr ><td  align='right'>FECHA FINAL:</td><td >Dia<select name='cmbdiaFin' id='cmbdiaFin'><option value='Dia' selected='selected'>Dia</option><option value='1'>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option><option value='13'>13</option><option value='14'>14</option><option value='15'>15</option><option value='16'>16</option><option value='17'>17</option><option value='18'>18</option><option value='19'>19</option><option value='20'>20</option><option value='21'>21</option><option value='22'>22</option><option value='23'>23</option><option value='24'>24</option><option value='25'>25</option><option value='26'>26</option><option value='27'>27</option><option value='28'>28</option><option value='29'>29</option><option value='30'>30</option><option value='31'>31</option></select>Mes<select name='cmbmesFin' id='cmbmesFin'><option value='Mes' selected='selected'>Mes</option><option value='01'>Enero</option><option value='02'>Febrero</option><option value='03'>Marzo</option><option value='04'>Abril</option><option value='05'>Mayo</option><option value='06'>Junio</option><option value='07'>Julio</option><option value='08'>Agosto</option><option value='09'>Septiembre</option><option value='10'>Octubre</option><option value='11'>Noviembre</option><option value='12'>Diciembre</option></select>A&ntilde;o<input name='txtanioFin' type='text' id='txtanioFin' size='4' maxlength='4'></td></tr><tr><td >&nbsp;</td><td align=''><input name='btngenerar' type='button' id='btngenerar' class='boton4' value='Generar' onClick='GenerarFechasEntidad()'></td></tr></table>");
		}
		
		if(va.equals("7")){
			out.println("<table width='100%'  class='style6'><tr><td  align='right'>FECHA INICIAL:</td><td><label>Dia<select name='cmbdiaIni' id='cmbdiaIni'><option value='Dia' selected='selected'>Dia</option><option value='1'>1</option> <option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option><option value='13'>13</option><option value='14'>14</option><option value='15'>15</option><option value='16'>16</option><option value='17'>17</option><option value='18'>18</option><option value='19'>19</option><option value='20'>20</option><option value='21'>21</option><option value='22'>22</option><option value='23'>23</option><option value='24'>24</option><option value='25'>25</option><option value='26'>26</option><option value='27'>27</option><option value='28'>28</option><option value='29'>29</option><option value='30'>30</option><option value='31'>31</option></select>Mes<select name='cmbmesIni' id='cmbmesIni'><option value='Mes' selected='selected'>Mes</option><option value='01'>Enero</option><option value='02'>Febrero</option><option value='03'>Marzo</option><option value='04'>Abril</option><option value='05'>Mayo</option><option value='06'>Junio</option><option value='07'>Julio</option><option value='08'>Agosto</option><option value='09'>Septiembre</option><option value='10'>Octubre</option><option value='11'>Noviembre</option><option value='12'>Diciembre</option></select>A&ntilde;o<input name='txtanioIni' type='text' id='txtanioIni' size='4' maxlength='4'></label></td></tr>");
			out.println("<tr ><td  align='right'>FECHA FINAL:</td><td >Dia<select name='cmbdiaFin' id='cmbdiaFin'><option value='Dia' selected='selected'>Dia</option><option value='1'>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option><option value='13'>13</option><option value='14'>14</option><option value='15'>15</option><option value='16'>16</option><option value='17'>17</option><option value='18'>18</option><option value='19'>19</option><option value='20'>20</option><option value='21'>21</option><option value='22'>22</option><option value='23'>23</option><option value='24'>24</option><option value='25'>25</option><option value='26'>26</option><option value='27'>27</option><option value='28'>28</option><option value='29'>29</option><option value='30'>30</option><option value='31'>31</option></select>Mes<select name='cmbmesFin' id='cmbmesFin'><option value='Mes' selected='selected'>Mes</option><option value='01'>Enero</option><option value='02'>Febrero</option><option value='03'>Marzo</option><option value='04'>Abril</option><option value='05'>Mayo</option><option value='06'>Junio</option><option value='07'>Julio</option><option value='08'>Agosto</option><option value='09'>Septiembre</option><option value='10'>Octubre</option><option value='11'>Noviembre</option><option value='12'>Diciembre</option></select>A&ntilde;o<input name='txtanioFin' type='text' id='txtanioFin' size='4' maxlength='4'></td></tr><tr><td >&nbsp;</td><td align=''><input name='btngenerar' type='button' id='btngenerar' class='boton4' value='Generar' onClick='GenerarFechasMedico()'></td></tr></table>");
		}
		
		if(va.equals("8")){
			out.println("<table width='100%'  class='style6'><tr><td  align='right'>FECHA INICIAL:</td><td><label>Dia<select name='cmbdiaIni' id='cmbdiaIni'><option value='Dia' selected='selected'>Dia</option><option value='1'>1</option> <option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option><option value='13'>13</option><option value='14'>14</option><option value='15'>15</option><option value='16'>16</option><option value='17'>17</option><option value='18'>18</option><option value='19'>19</option><option value='20'>20</option><option value='21'>21</option><option value='22'>22</option><option value='23'>23</option><option value='24'>24</option><option value='25'>25</option><option value='26'>26</option><option value='27'>27</option><option value='28'>28</option><option value='29'>29</option><option value='30'>30</option><option value='31'>31</option></select>Mes<select name='cmbmesIni' id='cmbmesIni'><option value='Mes' selected='selected'>Mes</option><option value='01'>Enero</option><option value='02'>Febrero</option><option value='03'>Marzo</option><option value='04'>Abril</option><option value='05'>Mayo</option><option value='06'>Junio</option><option value='07'>Julio</option><option value='08'>Agosto</option><option value='09'>Septiembre</option><option value='10'>Octubre</option><option value='11'>Noviembre</option><option value='12'>Diciembre</option></select>A&ntilde;o<input name='txtanioIni' type='text' id='txtanioIni' size='4' maxlength='4'></label></td></tr>");
			out.println("<tr ><td  align='right'>FECHA FINAL:</td><td >Dia<select name='cmbdiaFin' id='cmbdiaFin'><option value='Dia' selected='selected'>Dia</option><option value='1'>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option><option value='13'>13</option><option value='14'>14</option><option value='15'>15</option><option value='16'>16</option><option value='17'>17</option><option value='18'>18</option><option value='19'>19</option><option value='20'>20</option><option value='21'>21</option><option value='22'>22</option><option value='23'>23</option><option value='24'>24</option><option value='25'>25</option><option value='26'>26</option><option value='27'>27</option><option value='28'>28</option><option value='29'>29</option><option value='30'>30</option><option value='31'>31</option></select>Mes<select name='cmbmesFin' id='cmbmesFin'><option value='Mes' selected='selected'>Mes</option><option value='01'>Enero</option><option value='02'>Febrero</option><option value='03'>Marzo</option><option value='04'>Abril</option><option value='05'>Mayo</option><option value='06'>Junio</option><option value='07'>Julio</option><option value='08'>Agosto</option><option value='09'>Septiembre</option><option value='10'>Octubre</option><option value='11'>Noviembre</option><option value='12'>Diciembre</option></select>A&ntilde;o<input name='txtanioFin' type='text' id='txtanioFin' size='4' maxlength='4'></td></tr><tr><td >&nbsp;</td><td align=''><input name='btngenerar' type='button' id='btngenerar' class='boton4' value='Generar' onClick='GenerarFechasPyP()'></td></tr></table>");
		}
		
		if(va.equals("9")){
			out.println("<table width='100%'  class='style6'><tr><td  align='right'>FECHA INICIAL:</td><td><label>Dia<select name='cmbdiaIni' id='cmbdiaIni'><option value='Dia' selected='selected'>Dia</option><option value='1'>1</option> <option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option><option value='13'>13</option><option value='14'>14</option><option value='15'>15</option><option value='16'>16</option><option value='17'>17</option><option value='18'>18</option><option value='19'>19</option><option value='20'>20</option><option value='21'>21</option><option value='22'>22</option><option value='23'>23</option><option value='24'>24</option><option value='25'>25</option><option value='26'>26</option><option value='27'>27</option><option value='28'>28</option><option value='29'>29</option><option value='30'>30</option><option value='31'>31</option></select>Mes<select name='cmbmesIni' id='cmbmesIni'><option value='Mes' selected='selected'>Mes</option><option value='01'>Enero</option><option value='02'>Febrero</option><option value='03'>Marzo</option><option value='04'>Abril</option><option value='05'>Mayo</option><option value='06'>Junio</option><option value='07'>Julio</option><option value='08'>Agosto</option><option value='09'>Septiembre</option><option value='10'>Octubre</option><option value='11'>Noviembre</option><option value='12'>Diciembre</option></select>A&ntilde;o<input name='txtanioIni' type='text' id='txtanioIni' size='4' maxlength='4'></label></td></tr>");
			out.println("<tr ><td  align='right'>FECHA FINAL:</td><td >Dia<select name='cmbdiaFin' id='cmbdiaFin'><option value='Dia' selected='selected'>Dia</option><option value='1'>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option><option value='13'>13</option><option value='14'>14</option><option value='15'>15</option><option value='16'>16</option><option value='17'>17</option><option value='18'>18</option><option value='19'>19</option><option value='20'>20</option><option value='21'>21</option><option value='22'>22</option><option value='23'>23</option><option value='24'>24</option><option value='25'>25</option><option value='26'>26</option><option value='27'>27</option><option value='28'>28</option><option value='29'>29</option><option value='30'>30</option><option value='31'>31</option></select>Mes<select name='cmbmesFin' id='cmbmesFin'><option value='Mes' selected='selected'>Mes</option><option value='01'>Enero</option><option value='02'>Febrero</option><option value='03'>Marzo</option><option value='04'>Abril</option><option value='05'>Mayo</option><option value='06'>Junio</option><option value='07'>Julio</option><option value='08'>Agosto</option><option value='09'>Septiembre</option><option value='10'>Octubre</option><option value='11'>Noviembre</option><option value='12'>Diciembre</option></select>A&ntilde;o<input name='txtanioFin' type='text' id='txtanioFin' size='4' maxlength='4'></td></tr><tr><td >&nbsp;</td><td align=''><input name='btngenerar' type='button' id='btngenerar' class='boton4' value='Generar' onClick='GenerarDetallado()'></td></tr></table>");
		}
		
		if(va.equals("10")){
			out.println("<table width='100%'  class='style6'><tr><td  align='right'>SELECCIONE DIA :</td><td><label>Dia<select name='cmbdia' id='cmbdia'><option value='Dia' selected='selected'>Dia</option><option value='1'>1</option> <option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option><option value='13'>13</option><option value='14'>14</option><option value='15'>15</option><option value='16'>16</option><option value='17'>17</option><option value='18'>18</option><option value='19'>19</option><option value='20'>20</option><option value='21'>21</option><option value='22'>22</option><option value='23'>23</option><option value='24'>24</option><option value='25'>25</option><option value='26'>26</option><option value='27'>27</option><option value='28'>28</option><option value='29'>29</option><option value='30'>30</option><option value='31'>31</option></select>Mes<select name='cmbmes' id='cmbmes'><option value='Mes' selected='selected'>Mes</option><option value='01'>Enero</option><option value='02'>Febrero</option><option value='03'>Marzo</option><option value='04'>Abril</option><option value='05'>Mayo</option><option value='06'>Junio</option><option value='07'>Julio</option><option value='08'>Agosto</option><option value='09'>Septiembre</option><option value='10'>Octubre</option><option value='11'>Noviembre</option><option value='12'>Diciembre</option></select>A&ntilde;o<input name='txtanio' type='text' id='txtanio' size='4' maxlength='4'></label></td></tr><tr><td >&nbsp;</td><td align=''><input name='btngenerar' type='button' id='btngenerar' class='boton4' value='Generar' onClick='GenerarDetalladoDia()'></td></tr></table>");
		}
		if(va.equals("11")){
			out.println("<table width='100%'  class='style6'><tr><td  align='right'>FECHA INICIAL:</td><td><label>Dia<select name='cmbdiaIni' id='cmbdiaIni'><option value='Dia' selected='selected'>Dia</option><option value='1'>1</option> <option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option><option value='13'>13</option><option value='14'>14</option><option value='15'>15</option><option value='16'>16</option><option value='17'>17</option><option value='18'>18</option><option value='19'>19</option><option value='20'>20</option><option value='21'>21</option><option value='22'>22</option><option value='23'>23</option><option value='24'>24</option><option value='25'>25</option><option value='26'>26</option><option value='27'>27</option><option value='28'>28</option><option value='29'>29</option><option value='30'>30</option><option value='31'>31</option></select>Mes<select name='cmbmesIni' id='cmbmesIni'><option value='Mes' selected='selected'>Mes</option><option value='01'>Enero</option><option value='02'>Febrero</option><option value='03'>Marzo</option><option value='04'>Abril</option><option value='05'>Mayo</option><option value='06'>Junio</option><option value='07'>Julio</option><option value='08'>Agosto</option><option value='09'>Septiembre</option><option value='10'>Octubre</option><option value='11'>Noviembre</option><option value='12'>Diciembre</option></select>A&ntilde;o<input name='txtanioIni' type='text' id='txtanioIni' size='4' maxlength='4'></label></td></tr>");
			out.println("<tr ><td  align='right'>FECHA FINAL:</td><td >Dia<select name='cmbdiaFin' id='cmbdiaFin'><option value='Dia' selected='selected'>Dia</option><option value='1'>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option><option value='13'>13</option><option value='14'>14</option><option value='15'>15</option><option value='16'>16</option><option value='17'>17</option><option value='18'>18</option><option value='19'>19</option><option value='20'>20</option><option value='21'>21</option><option value='22'>22</option><option value='23'>23</option><option value='24'>24</option><option value='25'>25</option><option value='26'>26</option><option value='27'>27</option><option value='28'>28</option><option value='29'>29</option><option value='30'>30</option><option value='31'>31</option></select>Mes<select name='cmbmesFin' id='cmbmesFin'><option value='Mes' selected='selected'>Mes</option><option value='01'>Enero</option><option value='02'>Febrero</option><option value='03'>Marzo</option><option value='04'>Abril</option><option value='05'>Mayo</option><option value='06'>Junio</option><option value='07'>Julio</option><option value='08'>Agosto</option><option value='09'>Septiembre</option><option value='10'>Octubre</option><option value='11'>Noviembre</option><option value='12'>Diciembre</option></select>A&ntilde;o<input name='txtanioFin' type='text' id='txtanioFin' size='4' maxlength='4'></td></tr><tr><td >&nbsp;</td><td align=''><input name='btngenerar' type='button' id='btngenerar' class='boton4' value='Generar' onClick='GenerarPacientePorExamen()'></td></tr></table>");
		}
		
		if(va.equals("4")){
			rs=mcg.ObtenerPacienteActualizar(cedula,TipoDoc);
			try{
				while(rs.next()){
					String fechaNaci=rs.getString(9);
					if(fechaNaci==null){
						fechaNaci="";
					}
					else{
				    	 String dia,mes,anio=null;
				    	 dia=fechaNaci.substring(8,10);
				    	 mes=fechaNaci.substring(5,7);
				    	 anio=fechaNaci.substring(0,4);
				    	 fechaNaci=dia+"/"+mes+"/"+anio;				    	 
					}
					out.println("<table  width='100%' class='style6'>" +
							"<tr> <td colspan='6' id='cabecera2' align='center'> <span class='style11'>DATOS DEMOGRAFICOS</span> </td></tr>" +
							"<tr> <td>&nbsp;</td></tr>" +
							"<tr> <td align='right'>TIPO DOCUMENTO:</td> <td><select name='tipodoc' size='1' id='tipodoc'><option selected='selected' value="+rs.getString(10)+">"+rs.getString(10)+"</option><option value='CC'>CC</option><option value='TI'>TI</option><option value='RC'>RC</option><option value='Nacido Vivo'>Nacido Vivo</option></select><span class='style8'>*</span></td> </tr>" +
							"<tr> <td align='right'>No DE IDENTIFICACION:</td> <td><input name='txtnumdocu' type='text' id='txtnumdocu' onKeyUp='validarte(form2.txtnumdocu);' maxlength='12' value='"+cedula+"' /><span class='style8'>*</span></td> </tr>" +
							"<tr> <td align='right'>APELLIDOS:</td> <td><input name='txtapellidos' type='text' id='txtapellidos' onKeyUp='this.value=this.value.toUpperCase(),caracter(form2.txtapellidos);' value='"+rs.getString(8)+"' /><span class='style8'>*</span></td> </tr>" +
							"<tr> <td align='right'>NOMBRES:</td> <td><input name='txtnombres' type='text' id='txtnombres' onKeyUp='this.value=this.value.toUpperCase(),caracter(form2.txtnombres);' value='"+rs.getString(2)+"'/><span class='style8'>*</span></td> </tr>" +
							"<tr> <td align='right'>FECHA NACIMIENTO(dd/mm/aaaa):</td> <td><input type='text' name='txtfechanaci' id='txtfechanaci' value='"+fechaNaci+"' onKeyUp='masca(this,'/',patron,true)' maxlength='10'/></td> </tr>" +
							"<tr> <td align='right'>TELEFONO:</td> <td><input name='txtele' type='text' id='txtele' onKeyUp='validarte(form2.txtele);'  maxlength='11' value='"+rs.getString(5)+"' /><span class='style8'>*</span></td> </tr>" +
							"<tr> <td align='right'>DIRECCION:</td> <td><input  name='txtdireccion'  type='text' id='txtdireccion' value='"+rs.getString(6)+"'/><span class='style8'>*</span></td> </tr>" +
							"<tr> <td align='right'>EPS:</td> <td><input name='txteps' type='text' id='txteps'  onKeyUp='this.value=this.value.toUpperCase(),caracter(form2.txteps);' value='"+rs.getString(4)+"' /><span class='style8'>*</span></td> </tr>" +
							"<tr> <td align='right'>E-mail:</td> <td><input name='txtemail' type='text' id='txtemail' value='"+rs.getString(7)+"'  maxlength='100'/></td> </tr>" +
							"<tr> <td align='right'>GENERO:</td> <td><select name='cbgenero' id='cbgenero' ><option selected='selected' value='"+rs.getString(3)+"'>"+rs.getString(3)+"</option><option value='Masculino'>Masculino</option><option value='Femenino'>Femenino</option></select><span class='style8'>*</span></td> </tr>" +
							"<tr> <td><input name='codPac' type='hidden' id='codPac' value='"+rs.getString(1)+"' ></td><td><input type='button' name='btingresar' id='btingresar' value='Ingresar' class='boton4' onClick='img_pacienteActualizar();' /> </td></tr>" +
							"<tr> <td><span class='style8'>Datos Obligatorios*</span></td></tr>" +
							"</table>");
				}
				rs.getStatement().getConnection().close();
			}catch(SQLException e){
				System.out.println("Error en Controlador>>ControlCrearGrupo>> va=4 "+e);
				e.printStackTrace();
			}
		}
		if(va.equals("5")){
			//System.out.print("nacimiento "+nacimiento);
			//if(nacimiento==""){
			mcg.ActualizarDemograficoVacio(codigo, nombres, genero, eps,telefono,direccion,email,apellidos,nacimiento,cedulas,TipoDoc);
			//}
		}
		
		
	}

}
