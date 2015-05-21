package seguridad_controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import seguridad_logica.Metodo_Usuario; 

/**
 * Servlet implementation class Seguridad_login
 */
public class Seguridad_login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usuario=request.getParameter("usu");
		String contrasena=request.getParameter("id");
		String va=request.getParameter("accion");
		String codu="";		
		ResultSet rs=null;
		ResultSet rs2=null;
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		HttpSession session = request.getSession(true);
		String Profesion="";
		if(va.equals("1")){
			Metodo_Usuario us=new Metodo_Usuario();
			rs=us.obtenerUsuario(usuario, contrasena);
			us.CambiarEstadoOn(usuario,contrasena);
			String UsuHc="0";
			String UsuCx="0";
			String UsuAd="0";
			try {
				if(rs.next()){
					//hacer que cuando el medico tiene ambos perfiles salga de donde quiere arrancar
					java.util.Date Fecha = new java.util.Date();
					java.sql.Date Fecha_Insercion = new java.sql.Date(Fecha.getTime());		
					java.sql.Time Hora_Insercion = new java.sql.Time(Fecha.getTime());
					codu=rs.getString(1);
					us.IngresarLogUsuario(codu, Fecha_Insercion+"", Hora_Insercion+"", "INGRESO AL SISTEMA ");
					rs2=us.ObtenerPantallaPrincipalHc(codu);
					if(rs2.next()){
						UsuHc="1";
					}
					rs2.getStatement().getConnection().close();
					
					rs2=us.ObtenerPantallaPrincipalCex(codu);
					if(rs2.next()){
						UsuCx="1";
					}
					rs2.getStatement().getConnection().close();
					/*Profesion=rs.getString(2);
					if(Profesion.equals("Administracion")||
							Profesion.equals("Bacteriologa")||
							Profesion.equals("Bacteriologo")||
							Profesion.equals("Radiologo")||
							Profesion.equals("Tec. Radialogo")||
							Profesion.equals("Patologa")||
							Profesion.equals("Patologo")){
						
						session = request.getSession(false);
						session.setAttribute("codusuario", codu);
						out.print("menu.jsp");
						
					}else{
						session = request.getSession(false);
						session.setAttribute("codusuario", codu);
						out.print("agm_ListaMedico.jsp");
					}*/	
					if((UsuHc.equals("1"))&&(UsuCx.equals("1"))){
						//muestra los dos entornos
						out.print("hccx");
					}
					if((UsuHc.equals("1"))&&(UsuCx.equals("0"))){
						//muestra entorno de hospitalizacion y administracion
						out.print("hosadm");
					}
					if((UsuHc.equals("0"))&&(UsuCx.equals("1"))){
						//muestra entorno de consulta externa y administracion
						out.print("cexadm");
					}
					if((UsuHc.equals("0"))&&(UsuCx.equals("0"))){
						//se va directo a menu principal
						out.print("menu");
					}
					session = request.getSession(false);
					session.setAttribute("codusuario", codu);
				}else{
					out.println("<p style='color:#FF0000'>"+"Usuario y/o Contrasena no Coinciden/Existen.\n Por Favor Verifique Sus Datos."+"</p>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);		
		String va=request.getParameter("accion");
		String codusu=request.getParameter("CodUsu");
		ResultSet rs3=null;
		Metodo_Usuario us=new Metodo_Usuario();
		if(va.equals("cerrar")){
			try{ 
				rs3=us.CambiarEstadoOff(codusu);
				
				java.util.Date Fecha = new java.util.Date();
				java.sql.Date Fecha_Insercion = new java.sql.Date(Fecha.getTime());		
				java.sql.Time Hora_Insercion = new java.sql.Time(Fecha.getTime());
				us.IngresarLogUsuario(codusu, Fecha_Insercion+"", Hora_Insercion+"", "SALIDA DEL SISTEMA ");
				session.invalidate();			
				response.sendRedirect("Seg_login.jsp");				
			}catch(Exception e){
				e.getMessage();
			}
		}
	}
}