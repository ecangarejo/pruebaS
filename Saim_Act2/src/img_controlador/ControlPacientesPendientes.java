/**
 * controlador: ControlPacientesPendientes se encuentra el proceso para la 
 * visualizacion de los pacientes que tienen examenes pendientes.
*/

package img_controlador;

import img_logica.MetodoAprobarImg;
import img_logica.MetodoCitasExamen;
import img_logica.MetodoimgPa;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import adm_logica.Conexion;

/**
 * Servlet implementation class lab_Ingrupo
 */
public class ControlPacientesPendientes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");
		ResultSet rs=null;
		ResultSet rs3=null;
		PrintWriter out=res.getWriter();
		String va=req.getParameter("valor");
		String fecha="";
		String horaAs="";
		String fechaAs="";
		String completo="";
		String nombre="";
		String apellido="";
		MetodoAprobarImg mai=new MetodoAprobarImg ();
		MetodoCitasExamen mce = new MetodoCitasExamen();
		String cedula=req.getParameter("cedula");
		String codigo=req.getParameter("codigo");
		String codigoIce=req.getParameter("codigoIce");
		ResultSet rs1=null;
		if(va.equals("1")){
			rs=mai.ObtenerPacientesPendientes();
			
			out.println("<table width='100%'><tr><td colspan='7' align='center' id='cabecera2'><div align='center' class='style11'>EXAMENES POR REALIZAR</span></td></tr><tr><td>&nbsp;</td></tr></table><div class='scrollbox5'><table width='100%' border='1' bordercolor='#1C777B' class='style6'><tr id='cabecera2' class='style11' align='center' ><td width='143'><strong>Fecha de la Cita </strong></td><td width='221'><strong>Nombre Del Paciente </strong></td><td width='114'><strong>Cedula</strong></td><td width='131'><strong>Examenes Pendientes </strong></td><td width='120'><strong>Entidad</strong></td><td width='120'><strong>Asignado Por </strong></td><td width='120'><strong>Anular</strong></td></tr>");		
			try {
				while(rs.next()){
					fechaAs=rs.getString(1);
					horaAs=rs.getString(2);
					fecha=fechaAs+"/"+horaAs;
					nombre=rs.getString(3);
					apellido=rs.getString(4);
					completo=nombre+" "+apellido;
					out.println("<tr align='center'><td>"+fecha+"</td><td><a  href='#'onclick='mostarexamenes("+rs.getString(5)+","+rs.getString(8)+")'>"+completo+"</a></td><td>"+rs.getString(5)+"</td><td>"+rs.getString(7)+"</td><td>"+rs.getString(9)+"</td><td>"+rs.getString(6)+"</td><td><a href='#' onclick='omitir("+rs.getString(8)+")'><strong><em>Omitir</em></strong></a></td>");
				}
				out.println("</table></div>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				System.out.println("Error en Controlador va=1 "+e);
				e.printStackTrace();
			}
		}
		
		if(va.equals("2")){
			String datosClinicos="";
			int contador=0;
			rs=mce.ObtenerExamenesAsignados(cedula,codigoIce);
			rs1=mce.ObtenerExamenesAsignados(cedula,codigoIce);
			try{
				while(rs1.next()){
					nombre=rs1.getString(1);
					apellido=rs1.getString(2);
					datosClinicos=rs1.getString(8);
				}
				completo=nombre+" "+apellido;
				rs1.getStatement().getConnection().close();
			}
			catch (SQLException e) {
				System.out.println("Error en Controlador va=1.1 "+e);
				e.printStackTrace();
			}    
			out.println("<div class='margen1'><table  width='100%' class='style6'><tr><td colspan='8' id='cabecera2' align='center'><span class='style11'>DATOS DEL PACIENTE</span><td><tr><td>&nbsp;</td></tr></tr><tr><td colspan='8'>NOMBRE DEL PACIENTE: <b class='style9'>"+completo+"</b></td></tr><tr align='center'><td colspan='8'>DATOS CLINICOS<br><textarea name='txtdatoClinico' cols='75' rows='4' id='txtdatoClinico' readonly='readonly'>"+datosClinicos+"</textarea></td></tr><tr><td id='cabecera2' class='style11' colspan='8' align='center'>EXAMENES A REALIZAR </td></tr>");
			try{     
				
				while(rs.next()){
					String result=rs.getString(7);     
					out.println("<tr><td colspan='2' align='center'>"+rs.getString(5)+"</td></tr><tr><td colspan='2' align='center'><label><textarea name='txtdescripcion' cols='80' rows='6' id='txtdescripcion' onblur='Actualizar1()' >"+result+"</textarea></label></td></tr> <tr align='center'><td>CONCLUSION <br><label><textarea name='txtDiagnostico' cols='65' id='txtDiagnostico' onblur='Actualizar1()'></textarea></label></td></tr><input name='codExaCita' id='codExaCita' type='hidden' value='"+rs.getString(6)+"'>");
					contador=contador+1;
				}
				out.println("<input name='yo' id='yo' type='hidden' value="+contador+">");  
				out.println("<tr><td colspan='2' align='center'><label><input name='btnIngresar' type='button' class='boton4' id='btnIngresar' value='Ingresar' onclick='finalizar()'></label></td></tr></table></div>");
				rs.getStatement().getConnection().close();
			}
			catch(SQLException e){
				System.out.println("Error en Controlador va=4 "+e);
				e.printStackTrace();
			}
		}
		
		if(va.equals("3")){
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
			java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());
			String Accion="Om";
			String CodUsu=req.getParameter("CodUsu");
			mai.omitirExamen(codigo,Accion);
			mai.InsertarRevisionHistorialImagenes(codigo, CodUsu, Fecha, Hora);
			
		}
		if(va.equals("33")){
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
			java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());
			String Accion="Rv";
			String CodUsu=req.getParameter("CodUsu");
			mai.RevisarExamen(codigo, Accion);
			mai.InsertarRevisionHistorialImagenes(codigo, CodUsu, Fecha, Hora);
		}
		if(va.equals("4")){ 
			
			out.println("<table class='style6' width='441' border='0'><tr><td><input name='txtnombre' type='text' id='txtnombre' value='' size='55' onkeyup='autocompletaPaciente()'  /></td><td><input name='btnBuscar2' class='boton4' type='button' id='btnBuscar2' value='Buscar' onclick='buscar()' /></td></tr><tr><td><div id='sugerencias1'></div></td><td>&nbsp;</td></tr><tr><td width='548'><input name='txtnumdoc' type='hidden' id='txtnumdoc'></td><td width='205'>&nbsp;</td></tr></table>");
		}
		if(va.equals("5")){
			out.println("<table class='style6' width='441' border='0' ><tr><td width='129'>TIPO DE DOCUMENTO</td><td width='138'> <label><select name='txttipodoc' size='1' id='txttipodoc'>");
			
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
			
			
			out.print("</select></label></td><td width='74'><label>NUMERO</label></td><td width='201'><label><input name='txtnumdoc' type='text' id='txtnumdoc'  size='20' /></label></td><td width='201'><input name='btnBuscar' class='boton4' type='button' id='btnBuscar' value='Buscar' onclick='buscar()' /></td></table>");
		}
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");
		ResultSet rs=null;	
		String cad =req.getParameter("codigo");  
		MetodoimgPa img=new MetodoimgPa();	

	 int accion;
     
        accion = Integer.parseInt(req.getParameter("txtAccion"));
if(accion == 26){          

        try {
            rs =img.listarParaAutocompletarPaciente(cad);
            String cadena ="";
            String nombre ="";
            
            cadena="[";
            while(rs.next()){
            	nombre=rs.getString(2)+" "+rs.getString(3);
            	cadena = cadena+"'"+rs.getString(4)+"|"+rs.getString(1)+"|"+nombre+"'";
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
}
