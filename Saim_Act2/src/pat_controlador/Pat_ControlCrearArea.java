
/**
 * controlador:ControlCrearArea se encuentra la forma en que son 
 * creadas las areas las cuales posteriormente se le asigna un formato.
 */
package pat_controlador;


import pat_metodo.MetodoCrearArea;

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
public class Pat_ControlCrearArea extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	/**
	 * autocompletar de las pregunta, a medida que se va escribiendo las diferentes opciones
	 * similares, trae consigo el nombre y el codigo.
	 */
		ResultSet rs=null;	
		String cad =req.getParameter("codigo");  
		MetodoCrearArea mca = new MetodoCrearArea ();

	 int accion;
     
        accion = Integer.parseInt(req.getParameter("txtAccion"));
if(accion == 26){          

        try {
            rs =mca.listarParaAutocompletarPregunta(cad);
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
		String NomArea = req.getParameter("NomArea");
		String CodPregunta = req.getParameter("CodPregunta");
		String codRelacion=req.getParameter("codRelacion");
		String CodigoArea=req.getParameter("CodigoArea");
		PrintWriter out=res.getWriter();
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		ResultSet rs3=null;
		String CodRelacion=null;
		MetodoCrearArea mca = new MetodoCrearArea();
		
		if(va.equals("Ingresar")){
			/**
			 * Aqui se llena el formulario apenas se carga  hic_crearArea.jsp
			 */
			out.println("<table width='100%' class='style6' ><tr><td colspan='5' id='cabecera2'><span class='style11'><div align='center'>CREAR AREA </div> </span></td></tr><tr><td colspan='4'><span >NOMBRE DEL AREA </span></td><td width='35'>&nbsp;</td></tr><tr><td colspan='5'><input name='txtNomArea' type='text'  id='txtNomArea' onkeyup='this.value=this.value.toUpperCase();' size='70' />  <span class='style8'>*</span></td><BR /></tr><tr><td colspan='2' >SELECCIONE LAS PREGUNTAS DEL AREA </td><td width='98' align='center'>&nbsp;</td><td colspan='2' align='center'>&nbsp;</td></tr><tr><td width='427' align='center' ><input name='txtPregunta' type='text' id='txtPregunta' size='70' onkeyup='autocompletaPregunta()' /></td><td width='125' align='center' class='style3'><input name='btnAsignar' class='boton4' type='button' id='btnAsignar' onclick='AsignarPregunta()' value='Asignar'  align='middle'/></td><td align='center'><label><span ><input name='btnFinalizar' class='boton4' type='button' id='btnFinalizar' value='Finalizar' onclick='FinalizarArea()' /></span></label></td><td colspan='2' align='center'>&nbsp;</td></tr><tr><td align='center'><div id='sugerencias'></div></td><td align='center' class='style3'><input name='txtCodPregunta' type='hidden' id='txtCodPregunta' size='10' /></td><td align='center'>&nbsp;</td><td colspan='2' align='center'>&nbsp;</td></tr><tr><td><div id='preguntas'></div></td><td colspan='4' align='center' ><span class='style8'>Datos Requeridos *</span></td></tr></table>");
		}
		
		if(va.equals("Actualizar")){
			/**
			 * Aqui se llena el formulario para actualizar el area
			 */
			rs=mca.ObtenerAreas();
			out.println("<table width='100%' border='1' class='style6' ><tr><td colspan='2' id='cabecera2'><span class='style11'><div align='center'>ACTUALIZAR AREA </div></span></td></tr>");
			out.println("<tr><td width='171'><span >NOMBRE DEL AREA </span></td><td width='792'><select name='cmbNombreArea' id='cmbNombreArea' onchange='VerPreguntas()'><option value='Seleccione'>Seleccione</option>");
			try {
				while(rs.next()){
				out.println("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			out.println("</select></td></tr><tr><td colspan='2'><div id='preguntas'></div></td></tr></table>");
			
		}
		if(va.equals("cambio")){
			
			rs=mca.ObtenerPreguntas();
			out.println("<table width='100%' border='0' class='style6'><tr><td width='24%'><span >ADICIONAR PREGUNTAS </span> </td><td colspan='2'><select name='cmbPreguntas' id='cmbPreguntas'><option value='Seleccione' selected='selected'>Seleccione</option>");
			try {
				while(rs.next()){
				out.println("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			out.println("</select><input name='btnAdicionar' type='button' id='btnAdicionar' value='     Adicionar     ' onclick='ActualizarFormato()'/></td></tr></table>");
			out.println("<table width='100%' border='0' class='style6'><tr id='cabecera2' class='style11'><td width='48%'><div align='center'>Nombre Pregunta </div></td><td width='28%'><div align='center'>Tipo Pregunta </div></td><td width='24%'><div align='center'>Accion</div></td></tr>");
			rs3=mca.ObtenerPreguntasTipoRespuestas(CodigoArea);
			try {
				while(rs3.next()){
				out.println("<tr><td>"+rs3.getString(1)+"</td><td>"+rs3.getString(2)+"</td><td><a href='#' onclick='omitir("+rs3.getString(3)+")'><strong><em>Omitir</em></strong></a></td></tr>");
				}
				rs3.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			out.println("</table>");
		}
		if(va.equals("actu")){
			rs=mca.ObtenerPreguntas();
			out.println("<table width='100%' border='0' class='style6'><tr><td width='24%'><span >ADICIONAR PREGUNTAS </span> </td><td colspan='2'><select name='cmbPreguntas' id='cmbPreguntas'><option value='Seleccione' selected='selected'>Seleccione</option>");
			try {
				while(rs.next()){
				out.println("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			out.println("</select><input name='btnAdicionar' type='button' id='btnAdicionar' value='     Adicionar     ' onclick='ActualizarFormato()'/></td></tr></table>");
			
			rs1=mca.ObtenerRelacionAreaPregunta(CodPregunta, CodigoArea);
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
				 * si el resultado de la relacion viene vacio se ingresa la relacion de la pregunta con el area
				 */
				mca.RelacionAreaPregunta(CodigoArea, CodPregunta);
				rs3=mca.ObtenerPreguntasTipoRespuestas(CodigoArea);
				/**
					 * se llenan las preguntas que tiene el area.
					 */
				out.println("<table width='100%' border='0' class='style6'><tr id='cabecera2' class='style11'><td width='48%'><div align='center'>Nombre Pregunta </div></td><td width='28%'><div align='center'>Tipo Pregunta </div></td><td width='24%'><div align='center'>Accion</div></td></tr>");
				try {
					while(rs3.next()){
					out.println("<tr><td>"+rs3.getString(1)+"</td><td>"+rs3.getString(2)+"</td><td><a href='#' onclick='omitir("+rs3.getString(3)+")'><strong><em>Omitir</em></strong></a></td></tr>");
					}
					rs3.getStatement().getConnection().close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				out.println("</table>");
				
			}
			else{
				out.println("Ya Existe Una Relacion De Esta Area Con Esta Pregunta.");
				out.println("Por Favor Intente Otra Vez.");
			}
		}
		
		if(va.equals("1")){
			/**
			 * Aqui  se verifica si existe una area con el mismo nombre.
			 */
			String CodArea=null;
			
			rs=mca.ObtenerCodigoArea(NomArea);
			try {
				if(rs.next()){
					CodArea=rs.getString(1);
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			/**
			 * Si No existe Ninguna Con Al mismo Nombre Se Procede a Ingresar La Nueva Area.
			 */
			if(CodArea==null){
				mca.CrearArea(NomArea);
			}
			/**
			 * Ya Creada El Area Se Procede a Asignar Las Preguntas Que van Hacer Parte De Esta
			 * pero primero se comprueba si ya la pregunta no ha sido asignada al area.
			 */
			rs2=mca.ObtenerCodigoArea(NomArea);
			try{
				if(rs2.next()){
					CodArea=rs2.getString(1);
				}
				rs2.getStatement().getConnection().close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			rs1=mca.ObtenerRelacionAreaPregunta(CodPregunta, CodArea);
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
				 * si el resultado de la relacion viene vacio se ingresa la relacion de la pregunta con el area
				 */
				mca.RelacionAreaPregunta(CodArea, CodPregunta);
				rs3=mca.ObtenerPreguntasTipoRespuestas(CodArea);
				try {
					/**
					 * se llenan las preguntas que tiene el area.
					 */
					out.print("<table border='1' class='style6' width='100%'><tr align='center' class='style11' id='cabecera2'><td width='255' >Preguntas</td><td width='175' align='center' >Tipo Respuesta </td><td width='117' align='center' >Eliminar</td></tr>");
					while(rs3.next()){
					out.print("<tr><td>"+rs3.getString(1)+"</td><td>"+rs3.getString(2)+"</td><td><a href='#' onclick='omitir("+rs3.getString(3)+")'><strong><em>Omitir</em></strong></a></td></tr>");	
					}
					out.print("</table>");
					rs3.getStatement().getConnection().close();
				} catch (SQLException e) {
					System.out.println("Error Resulset 3 "+e);
					e.printStackTrace();
				}
				
			}
			else{
				out.println("Ya Existe Una Relacion De Esta Area Con Esta Pregunta.");
				out.println("Por Favor Intente Otra Vez.");
			}
			
		}
		if(va.equals("4")){
			String CodArea=null;
			rs=mca.ObtenerCodigoArea(NomArea);
			try {
				if(rs.next()){
					CodArea=rs.getString(1);
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			rs3=mca.ObtenerPreguntasTipoRespuestas(CodArea);
			try {
				/**
				 * se llenan las preguntas que tiene el area.
				 */
				out.print("<table border='1' class='style6' width='100%'><tr align='center' class='style11' id='cabecera2'><td width='255' >Preguntas</td><td width='175' align='center' >Tipo Respuesta </td><td width='117' align='center' >Eliminar</td></tr>");
				while(rs3.next()){
				out.print("<tr><td>"+rs3.getString(1)+"</td><td>"+rs3.getString(2)+"</td><td><a href='#' onclick='omitir("+rs3.getString(3)+")'><strong><em>Omitir</em></strong></a></td></tr>");	
				}
				out.print("</table>");
				rs3.getStatement().getConnection().close();
			} catch (SQLException e) {
				System.out.println("Error Resulset 3 "+e);
				e.printStackTrace();
			}
		}
		if(va.equals("3")){
			mca.OmitirAsignacion(codRelacion);
		}
		
	}
}
