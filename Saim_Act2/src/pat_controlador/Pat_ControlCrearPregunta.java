/**
 * controlador: ControlCrearPregunta se encuentra la forma en que son 
 * creadas las preguntas las cuales posteriormete se les asigna un area para completar el formato.
 */
package pat_controlador;


import pat_metodo.MetodoCrearPregunta;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class ControlCrearPregunta
 */
public class Pat_ControlCrearPregunta extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		/**
		 * autocompletar de los diferentes tipo de respuesta, tienen como parametro
		 * el nombre del tipo de respuesta.
		 */
		ResultSet rs=null;	
		String cad =req.getParameter("codigo");  
		MetodoCrearPregunta mcp = new MetodoCrearPregunta ();

	 int accion;
     
        accion = Integer.parseInt(req.getParameter("txtAccion"));
if(accion == 26){          

        try {
            rs =mcp.listarParaAutocompletarTipoRespuesta(cad);
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
		PrintWriter out=res.getWriter();
		ResultSet rs=null;	
		ResultSet rs1=null;
		MetodoCrearPregunta mcp= new MetodoCrearPregunta();
		String nombrePregunta=req.getParameter("NombrePregunta");
		String codigo_tiporespuesta_fk=req.getParameter("CodTipoRespuesta");
		String PatronNormal=req.getParameter("PatronNormal");
		String UnidadPregunta=req.getParameter("UnidadPregunta");
		String TipoRequerimiento=req.getParameter("TipoRequerimiento");
		String codigo_pregunta_fk="";
		if(va.equals("0")){
			/**
			 * Aqui se llena el formulario apenas se carga  hic_crearPregunta.jsp
			 */
			out.print("<table width='100%' class='style6' ><tr><td colspan='3' id='cabecera2'><span class='style11'><div align='center'>CREAR PREGUNTA</div> </span></td></tr>" +
					"<tr><td colspan='3'><span >NOMBRE DE LA PREGUNTA </span></td></tr>" +
					"<tr><td colspan='3'><input name='txtNomPregunta' type='text'  id='txtNomPregunta' onkeyup='this.value=this.value.toUpperCase();' size='70' />  <span class='style8'>*</span></td><BR /></tr>" +
					"<tr><td colspan='3' >RESPUESTA AUTOMATICA </td></tr>" +
					"<tr><td colspan='3' ><textarea name='txtPatronNormal' cols='55' rows='5'  id='txtPatronNormal' onkeyup='this.value=this.value.toUpperCase();' type='text' ></textarea></td></tr>" +
					"<tr><td colspan='3' >UNIDAD DE LA PREGUNTA </td></tr>" +
					"<tr><td colspan='3' ><input name='txtUnidadPregunta' type='text' id='txtUnidadPregunta' size='70' maxlength='10'  /><span class='style8'></span></td></tr>" +
					"<tr><td >TIPO DE RESPUESTA </td><td colspan='2' >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label><input name='chkRequerido' type='hidden' id='chkRequerido' value='1' /></label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label><input name='chkEpicrisis' type='hidden' id='chkEpicrisis' value='2' /></label></td></tr>" +
					"<tr><td width='446' ><input name='txtTipoRespuesta' type='text' id='txtTipoRespuesta' size='70' onkeyup='autocompletaTipoRespuesta()' /><span class='style8'> *</span></td><td width='347' align='center' ><input name='btnCrearCerrada' type='button' class='boton4' id='btnCrearCerrada' onclick='IngresarPregunta()' value='Crear'  align='middle'/></td><td width='277' align='center'><input name='txtCodTipoRespuesta' type='hidden' id='txtCodTipoRespuesta' size='10' /></td></tr>" +
					"<tr><td align='center'><div id='sugerencias'></div></td><td align='center' class='style3'><span class='style8'>Datos Requeridos * </span></td><td align='center'>&nbsp;</td></tr></table>");
		}		
		if(va.equals("1")){
			/**
			 * se busca si el nombre de la pregunta ya existe en la base de datos.
			 */
			String CodPregunta=null;
			try {
				rs1=mcp.ObtenerCodigoPregunta(nombrePregunta);
				if(rs1.next()){
					CodPregunta=rs1.getString(1);
				}
				rs1.getStatement().getConnection().close();
			} catch (Exception e1) {
				System.out.println("ERROR en el ResultSet 1 "+e1);
				e1.printStackTrace();
			}
			if(CodPregunta==null){
			mcp.CrearPregunta(nombrePregunta,PatronNormal,UnidadPregunta,TipoRequerimiento);
			try {
				rs=mcp.ObtenerCodigoPregunta(nombrePregunta);
				if(rs.next()){
					codigo_pregunta_fk=rs.getString(1);
				}
				rs.getStatement().getConnection().close();
				mcp.RelacionPreguntaTipoRespuesta(codigo_tiporespuesta_fk, codigo_pregunta_fk);
			} catch (Exception e) {
				e.printStackTrace();
			}
			}
			else{
				out.print("Ya Existe una Pregunta Con Este Nombre");
				out.print("Por Favor Intente Con Otro Nombre");
			}
		}
	}
}
