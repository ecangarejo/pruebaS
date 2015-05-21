/**
 * controlador: ControlExamenImag se encuentra el proceso para la 
 * busqueda del paciente para asignarle una cita. 
*/


package img_controlador;

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



/**
 * Servlet implementation class ControlExamen
 */
public class ControlExamenImag extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String va=request.getParameter("valor");
		//String nombre,codigo;
		String cedula=request.getParameter("cedula");
		String tipodoc=request.getParameter("tipo");
		String op=request.getParameter("op");
		ResultSet rs1=null;	
		MetodoimgPa impa=new MetodoimgPa();

		
		if(va.equals("1")){
			rs1=impa.SQLimgPac(cedula, tipodoc);
			try {
				if(rs1.next()){
					//nombre=rs1.getString(1)+" "+rs1.getString(2);
					//codigo=rs1.getString(3);
					if(op.equals("1")){
						response.sendRedirect("img_buscarEspecifico.jsp?numero="+cedula+"&TipoDoc="+tipodoc);
					}
					
					if(op.equals("2")){
						response.sendRedirect("img_asignar.jsp?numero="+cedula+"&TipoDoc="+tipodoc);

					}
					
				}else{
					response.sendRedirect("adm_IngresarDemografico.jsp");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				System.out.println("Error En ControlExamenImag get va=1 "+e);
				e.printStackTrace();
			}
		}
	 }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ResultSet rs=null;
		ResultSet rs1=null;
		String nombre="";
		String apellido="";
		String completo="";
		String codPac_fk="";
		//String codigo="";
		String va=request.getParameter("valor");
		String cedula=request.getParameter("cedula");
		String tipodoc=request.getParameter("tipo");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		MetodoCitasExamen mce = new MetodoCitasExamen();
		MetodoimgPa impa=new MetodoimgPa();
		if(va.equals("0")){
			rs1=impa.SQLimgPac(cedula, tipodoc);
			try {
				if(rs1.next()){
					nombre=rs1.getString(1)+" "+rs1.getString(2);
					//codigo=rs1.getString(3);
				}else{
					response.sendRedirect("img_paciente.jsp?id="+cedula+"&TipoDocumento="+tipodoc);
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				System.out.println(e);
				e.printStackTrace();
			}
		}
		
		if(va.equals("1")){
		 String cedu=request.getParameter("ced");
		 String tipo=request.getParameter("tipo");
		 String apellidos=request.getParameter("ape");
		 String nombres=request.getParameter("nom");
		 String fecha=request.getParameter("fecha");
		 String tele=request.getParameter("telefono");
		 String dire=request.getParameter("dire");
		 String email=request.getParameter("email");
		 String genero=request.getParameter("gene");
		 String eps=request.getParameter("eps");
		 String CodUsu=request.getParameter("CodUsu");
		 String horaIngreso=request.getParameter("horaIngreso");
		 String fechaIngreso=request.getParameter("fechaIngreso");
		 String edad="0";
		// String NombreCompleto="";
		 MetodoimgPa ingresopac=new MetodoimgPa();
		 rs=ingresopac.NombreUsuario(CodUsu);
		 try {
			if(rs.next()){
				//String nombres1=rs.getString(1);
				//String apellido1=rs.getString(2);
				//NombreCompleto=nombres1+" "+apellido1;
				ingresopac.insertar(nombres,genero,cedu,eps,edad,tele,dire,email,apellidos,tipo,fecha,CodUsu,fechaIngreso,horaIngreso); 
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
					
		}
		
		if(va.equals("2")){
			if(cedula!=""){
			rs=mce.ObtenerArea();
			rs1=mce.ObtenerCodigoPaciente(cedula,tipodoc);
			try{
				while(rs1.next()){
					codPac_fk=rs1.getString(1);
					nombre=rs1.getString(2);
					apellido=rs1.getString(3);
				}
				completo=nombre+" "+apellido;
				rs1.getStatement().getConnection().close();
			}
			catch (SQLException e) {
				System.out.println("Error en Controlador va=1.1 "+e);   
				e.printStackTrace();  
			}         
			  
			out.println("<table width='100%' class='style6'><tr><td colspan='2' id='cabecera2'>&nbsp;</td></tr><tr><td colspan='2' align='center'>NOMBRE Y APELLIDOS: <b class='style9'>"+completo+"</b></td></tr><tr><td>DATOS CLINICOS <br><label><textarea name='txtdatosClinicos' cols='75' rows='4' id='txtdatosClinicos' ></textarea></label></td></tr><tr><td>SELECCIONE GRUPO<label><label><select name='cmbgrupos' id='cmbgrupos' onchange='VerExamenes()'><option selected='selected'>SELECCIONE</option>");
			try { 
				while(rs.next()){
					out.println("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");					
				}
			out.println("</select><input name='txtcedula' type='hidden' id='txtcedula' value='"+codPac_fk+"' /></label></label><label></label></td></tr><tr><td><label><input name='chkportatil' type='checkbox' id='chkportatil' value='(PORTATIL)' />PORTATIL</label></td><td>&nbsp;</td></tr></table>");	
			rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				System.out.println("Error en Controlador va=2 "+e);  
				e.printStackTrace();
			}
				}
			else{
				//System.out.println("no se encontro nada");
			}
			}
		  
		if(va.equals("3")){	
			if(cedula!=""){
				//rs=mce.ObtenerArea();
				rs1=mce.ObtenerCodigoPaciente(cedula,tipodoc);
				try{
					while(rs1.next()){  
						codPac_fk=rs1.getString(1);
						nombre=rs1.getString(2);
						apellido=rs1.getString(3);
					}
					completo=nombre+" "+apellido;
					rs1.getStatement().getConnection().close();
				}
				catch (SQLException e) {
					System.out.println("Error en Controlador va=1.1 "+e);
					e.printStackTrace();
				}    
				
				out.println("<table width='100%'  class='style6'>" +
						"<tr><td colspan='2' id='cabecera2'>&nbsp;</td>" +  
						"</tr><tr><td colspan='2' align='center'>NOMBRE Y APELLIDOS:<b class='style9'> " +
						""+completo+"</b></td></tr><tr><td colspan='2'>DATOS CLINICOS " +
						"<br><textarea name='txtdatosClinicos' cols='75' rows='4' id='txtdatosClinicos'></textarea></td>" +
						"</tr><tr><td colspan='2'>NOMBRE DEL ESTUDIO " +
						"<label><label><input name='txtnomexam' type='text' id='txtnomexam' size='85' onkeyup='autocompleta_nombre()' /><input name='txtcedula' type='hidden' id='txtcedula' value='"+codPac_fk+"' /></label></label><label><input name='btnAsignar' class='boton4' type='button' id='btnAsignar' value='Asignar' onclick='enviar()' /><input name='txtcodexamen' type='hidden' id='txtcodexamen' /></label></td></tr>" +
						"<tr><td><input name='chkPortatil' type='checkbox' id='chkportatil' value='(PORTATIL)'/>PORTATIL</td><td><div style='margin-left:0px' id='sugerencias1'></div></td></tr><tr><td height='24'>&nbsp;</td><td><input name='btnFinalizar' class='boton4' type='button' id='btnFinalizar' value='Finalizar' onclick='finalizar()' /></td></tr>" +
						"<tr><td colspan='4'><div id='resultado1' style='display:none' ></div></td></tr></table>");
				//System.out.println("entro va = 2");
					}
				else{
					//System.out.println("no se encontro nada");
				}
		}
		}
}