/**
 * controlador: ControlActuResultado se encuentra el proceso para la 
 * actualizacion de los examenes tipo individuales que fueron ingresados pero que no han sido
 * aprobados.
*/
package lab_controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import adm_logica.Conexion;

import lab_logica.MetodoActuResultadoIndividual;

/**
 * Servlet implementation class ControlActuResultadoIndividual
 */
public class ControlActuResultadoIndividual extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {


	}



	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");
		ResultSet rs=null;
		ResultSet rs3=null;
		PrintWriter out=res.getWriter();
		String va = req.getParameter("valor");
		String cedula=req.getParameter("cedula");
		String codGrupo=req.getParameter("codGrupo");		
		String codSubgrupo=req.getParameter("codSub");
		String numero=req.getParameter("numero");
		String codsub=req.getParameter("codsub");
		String fecha=req.getParameter("fecha");
		String horas=req.getParameter("horas");
		String resultado=req.getParameter("resultado");
		String tipo=req.getParameter("tipo");
		String codResultado=req.getParameter("codResultado");
		MetodoActuResultadoIndividual mari = new MetodoActuResultadoIndividual();   
		 
		if(va.equals("1")){ 
			rs=mari.mostrarGrupo();
			out.println("<table  class='style6'width='100%' ><tr><td colspan='4' align='center' id='cabecera2'><div class='style11'>ACTUALIZAR RESULTADOS DE EXAMENES </div></td></tr><tr><td>&nbsp;</td></tr><tr ><td>DOCUMENTO</td><td><select name='cbafiliacion' size='1' id='cbafiliacion'>");
			
			/********************************************************************/		
			try {
				Conexion con2;
				java.sql.Statement st3 = null;  
				con2 = new Conexion();
				st3 = con2.conn.createStatement();
				rs3=st3.executeQuery("SELECT sigla,descripcion FROM adm_tipodocumento");
				while(rs3.next()){
					out.print("<option value="+rs3.getString(1)+">"+rs3.getString(1)+"</option>");
				}
				rs3.getStatement().getConnection().close();
				st3.close();
				con2.cerrar();
			}catch(SQLException e){
						  
				System.out.println(e);
				out.println("no se pudo realizar la conexion!<br/>"); 
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/********************************************************************/
			
			out.println("</select></td><td>CEDULA</td><td><label><input name='txtnumdoc' type='text' id='txtnumdoc'  onkeyup='autocompleta_realizar();' /></label></td><td>&nbsp;</td></tr><tr><td>GRUPO</td><td><label><select name='cmbGrupo' id='cmbGrupo' onChange='ajaxLlenarExamenes()'><option selected='selected'>SELECCIONE</option>");
			try {
				while(rs.next()){
					out.println("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");					
				}
			out.println("</select></label></td><td></td><td><div id='sugerenciasRe'></div></td></tr><tr><td>EXAMENES</td><td><div id='option'><label><select name='select'><option>SELECCIONE</option></select></label></div></td><td><label><input name='btnBuscar' class='boton4' type='button' id='btnBuscar' value='Buscar' onclick='BuscarExam()' /></label></td></tr></table>");	
			rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				//System.out.println("Error en Controlador va=1 "+e);
				e.printStackTrace();
			}			
		}
		
		if(va.equals("2")){
			rs=mari.mostrarExamenes(codGrupo);
			out.println("<table width='200' border='0'><tr><td><label><select name='cmbSubgrupo' id='cmbSubgrupo'><option selected='selected'>SELECCIONE</option>");
			try {
				while(rs.next()){
					out.println("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");					
				}
				rs.getStatement().getConnection().close();
			out.println("</select></label></td></tr></table>");	
			} catch (SQLException e) {
				//System.out.println("Error en Controlador va=2 "+e);
				e.printStackTrace();
			}
			
		}
		
		if(va.equals("3")){
			rs=mari.mostrarExamenEncontrados(cedula, codSubgrupo,tipo);
			out.println("<table width='100%'><tr><td colspan='4'align='center' id='cabecera2'><div class='style11'>EXAMENES REALIZADOS</div></td></tr><tr><td>&nbsp;</td></tr><tr align='center' class='style6' bgcolor='#dbe5f1'><td width='232'>Fecha</td><td width='188'>Nombre Del Examen</td><td width='114'>Cedula</td><td width='241'>Nombre Del Paciente </td></tr>");
			try {
				
				while(rs.next()){
					
					String ano=rs.getString(4).substring(0,4);
					String mes=rs.getString(4).substring(5,7);
					String dia=rs.getString(4).substring(8,10);		
					String hora=rs.getString(5).substring(0,2);
					String minuto=rs.getString(5).substring(3,5);
					String segundo=rs.getString(5).substring(6,8);
					
					out.print("<tr class='style6'><td>"+rs.getString(4)+"/"+rs.getString(5)+"</td><td><a  href='#'onclick='mostarexamenes("+ano+","+mes+","+dia+","+hora+","+minuto+","+segundo+","+rs.getString(11)+","+rs.getString(14)+")'>"+rs.getString(6)+"</a></td><td>"+rs.getString(3)+"</td><td>"+rs.getString(2)+" "+rs.getString(1)+"</td></tr>");
				}
				rs.getStatement().getConnection().close();
				out.print("</table>");
			} catch (SQLException e) {
				//System.out.println("Error en Controlador va=3 "+e);
			}
		}
		
		if(va.equals("4")){
			int contador=0;
			rs=mari.verExamenes(numero, codsub, fecha, horas);
			out.println("<table width='100%' class='style6'  ><tr><td colspan='4'aling='center' id='cabecera2'><div class='style11'>EXAMENES REALIZADOS</div></td></tr><tr><td>&nbsp;</td></tr><tr><td width='164'>NOMBRE DE EXAMEN</td><td width='107'>RESULTADO</td><td width='168'>VALOR REFERENCIA </td><td width='118'>UNIDADES</td></tr>");
				try {				
				while(rs.next()){						
					out.print("<tr><td><a  href='#'onclick=''>"+rs.getString(6)+"</a></td><td aling='center'><label><input name='txtResultado' type='text' id='txtResultado' value='"+rs.getString(10)+"' /></label></td><td aling='center'>"+rs.getString(8)+"-"+rs.getString(9)+"</td><td aling='center'>"+rs.getString(7)+"</td></tr><input name='codResultado' id='codResultado' type='hidden' value='"+rs.getString(12)+"'><input name='tipoExam' id='tipoExam' type='hidden' value='"+rs.getString(13)+"'>");
					contador=contador+1;
				}
				rs.getStatement().getConnection().close();
				out.println("<input name='yo' id='yo' type='hidden' value="+contador+">");
				out.print("<tr><td colspan='4' align='center'><label><input name='btnaprobar' class='boton4' type='button' id='btnaprobar' value='Actualizar' onclick='Actualizar()'></label></td></tr></table>");
				
			} catch (SQLException e) {
				//System.out.println("ERROR EN VA=2 "+e);
			}
		}
		
		if(va.equals("5")){
			//System.out.println("codigoResultado = "+codResultado+" Tipo = "+tipo+" resultado Actualizar = "+resultado);
			//if(tipo.equals("1")){
				mari.ActualizarTipoRango(resultado, codResultado);
			//}
		//	if(tipo.equals("0")){
			//	mari.ActualizarTipoTexto(resultado, codResultado);
			//}
			
		}

	}

}
