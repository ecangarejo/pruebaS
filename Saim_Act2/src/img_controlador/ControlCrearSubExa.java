/**
 * controlador: ControlCrearSubExa se encuentra el proceso para la 
 * creacion examenes y la ctualizacion de su patron normal.
*/

package img_controlador;

import img_logica.MetodoCrearSubExa;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ControlCrearSubExa
 * 
 **/
public class ControlCrearSubExa extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		MetodoCrearSubExa mcs = new MetodoCrearSubExa ();
		java.util.Date fechaActual = new java.util.Date();
		java.sql.Date fecha = new java.sql.Date(fechaActual.getTime());		
		java.sql.Time hora = new java.sql.Time(fechaActual.getTime());		
		ResultSet rs=null;
		ResultSet rs1=null;
		String cod_gruFk=req.getParameter("codGrupo");
		String nombre=req.getParameter("nomExam");
		String numeroExamen=req.getParameter("codExam");
		String patron=req.getParameter("patron");
		String CodUsu=req.getParameter("CodUsu");
		String CodImagen="";
		String CodPrograma="";		
		try {
			mcs.InsertExaSub(cod_gruFk, nombre, numeroExamen,patron);
			rs=mcs.ObtenerCodigoImagen(cod_gruFk, numeroExamen, nombre);
			if(rs.next()){
				CodImagen=rs.getString(1);
			}
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			System.out.print("Error ControlCrearSubExa>>ObtenerCodigoImagen "+e);
			e.printStackTrace();
		}
		
		String cero="0";
		String blanco="";
		
		try {
			mcs.CrearPrograma(cero, cero, blanco, blanco, nombre, cero, cero, cero, cero, cero, fecha, hora, CodUsu);
			rs1=mcs.ObtenerCodigoPrograma(nombre, fecha, hora, CodUsu);
			if(rs1.next()){
				CodPrograma=rs1.getString(1);
			}
			rs1.getStatement().getConnection().close();
		} catch (SQLException e) {
			System.out.print("Error ControlCrearSubExa>>ObtenerCodigoPrograma "+e);
			e.printStackTrace();
		}
		
		mcs.Insertprog_imag(CodImagen, CodPrograma);
		
		res.sendRedirect("img_ExamenSub.jsp");

	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		ResultSet rs=null;
		MetodoCrearSubExa mcs = new MetodoCrearSubExa ();
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out=res.getWriter();
		String va = req.getParameter("valor");
		String codigoExa=req.getParameter("codigoExa");
		String patroNormal=req.getParameter("patronNormal");
		
		if(va.equals("1")){  
			rs=mcs.ObtenerArea();  
			out.println("<table class='style6' width='100%' ><tr><td colspan='2' id='cabecera2' class='style11'  align='center' ><span>CREAR SUB-GRUPO</span></td></tr>" +
					"<tr><td>&nbsp;</td></tr><tr><td align='center'>ESCOJA EL GRUPO<label><select name='cmbarea' size='1' id='cmbarea' ><option selected='selected' value='SELECCIONE' >SELECCIONE</option>");  
			try {   
				while(rs.next()){       
					out.println("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");	        				  
				}
			out.println("</select><span class='style8'>*</span></label></td></tr><tr><td>&nbsp;</td></td><tr><td align='center'>NOMBRE DEL EXAMEN<br><label><textarea name='txtExamen' cols='75' rows='3' id='txtExamen' ></textarea><span class='style8'>*</span></label></td></tr><tr><td>&nbsp;</td></td><tr><td align='center'>PATRON NORMAL<br><textarea name='txtpatron' cols='75' rows='6' id='txtpatron' ></textarea><span class='style8'> *</span></td></tr><tr><td align='center'>CODIGO DEL EXAMEN  <label><input name='txtCodExam' type='text' id='txtCodExam' size='30' maxlength='75'><span class='style8'>*</span></label></td></tr><tr><td colspan='2' align='center'><label><input name='btncrear' class='boton4' type='button' id='btncrear' value='Ingresar'  onClick='IngresarExaSub()' ></label></td></tr><tr><td colspan='2'><span class='style8'>Datos Requeridos * </span></td></tr></table>");	
			rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				System.out.println("Error en Controlador va=1 "+e);
				e.printStackTrace();  
			}			  
		}
		
		if(va.equals("2")){
			String patronNormal="";
			rs=mcs.ObtenerDatosExamen(codigoExa);
			try {
				if(rs.next()){
					patronNormal=rs.getString(1);					
				}
			out.println(patronNormal);
			rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				System.out.println("Error en Controlador va=2 "+e);
				e.printStackTrace();
			}	
		}
		
		if(va.equals("3")){	
			mcs.ActualizarPatronNormal(codigoExa,patroNormal);
		}

	}

}
