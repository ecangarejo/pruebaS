package fact_controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fact_metodo.MetodoCrearNivelComplejidad;

/**
 * Servlet implementation class ControlCrearNivelComplejidad
 */
public class ControlCrearNivelComplejidad extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String va = req.getParameter("valor");
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		MetodoCrearNivelComplejidad mce = new MetodoCrearNivelComplejidad();
		
		String desc = req.getParameter("desc");
		String cod = req.getParameter("cod");
		ResultSet rs = null;
		ResultSet rs1 = null;
		
		
		if(va.equals("CrearRb")){
			out.print("<table width='421' border='0'><tr><td width='88'><div align='right'>Descripci&oacute;n:</div></td><td width='323'><input name='Descripcion' type='text' id='Descripcion' size='45' maxlength='100' onkeydown='A(this, event)' /></td></tr>");
			out.print("<tr><td colspan='2'><div align='right'><input name='Ingresa' type='button' id='Ingresa' value='   Ingresar   ' onclick='Ingresar()' /></div></td></tr></table>");
			out.close();
		}else if(va.equals("ActualizarRb")){
			rs = mce.getAll();
			out.print("<table width='540' border='0'>");
			out.print("<tr><td width='206'><div align='right'>Seleccione el Nivel de Complejidad:</div></td><td width='318'><select name='lista' id='lista' onchange='Buscar()'><option value='Seleccione'>Seleccione</option>");
			try {
				while(rs.next()){
					out.print("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
				}
				out.print("</select></td></tr>");
				out.print("<tr><td colspan='2'><div id='objeto'></div></td></tr></table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.close();
		}else if(va.equals("Ingresar")){
			if(mce.Crear(desc)){
				out.print("Ingreso exitoso");
			}else{
				out.print("Error al ingresar los datos");
			}
			out.close();
		}else if(va.equals("ver")){
			rs1 = mce.getByCod(cod);
			try {
				if(rs1.next()){
					out.print("<table width='421' border='0'><tr><td width='88'><div align='right'>Descripci&oacute;n:</div></td><td width='323'><input name='Descripcion' type='text' id='Descripcion' value='"+rs1.getString(1)+"' size='45' maxlength='100' onkeydown='B(this, event)' /></td></tr>");
					out.print("<tr><td colspan='2'><div align='right'><input name='Actualiza' type='button' id='Actualiza' value='   Actualizar   ' onclick='Actualizar()' /></div></td></tr></table>");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				out.print(e.getMessage());
				e.printStackTrace();
			}
			out.close();
		}else if(va.equals("Actualizar")){
			if(mce.Actualizar(desc, cod)){
				out.print("Nivel de complejidad actualizado exitosamente");
			}else{
				out.print("Error al actualizar el nivel de complejidad");
			}
			out.close();
		}
	}

}
