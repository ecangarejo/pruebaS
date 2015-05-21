package seguridad_controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import seguridad_logica.MetodoCrearPermisos;

public class ControlCrearPermisos extends HttpServlet {

	/**
	 * Control Para Crear los permisos de las jsp de manera grafica.
	 */
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out=res.getWriter();		
		MetodoCrearPermisos mcp= new MetodoCrearPermisos (); 
		
		String va=req.getParameter("valor");
		String UrlMenu=req.getParameter("UrlMenu");
		String NombreMenu=req.getParameter("NombreMenu");
		String CodigoMenuFk=req.getParameter("CodigoMenuFk");
		String CodigoOpMenuFk=req.getParameter("CodigoOpMenuFk");
		String UrlOpcionesMenu=req.getParameter("UrlOpcionesMenu");
		String NombreOpcionesMenu=req.getParameter("NombreOpcionesMenu");
		String NombreOpcionDisponible=req.getParameter("NombreOpcionDisponible");
		
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		
		if(va.equals("1")){
			out.print("<table width='100%' border='1'><tr><td colspan='5'><div align='center' class='style11' id='cabecera2' >Crear Menu</div></td></tr>");
			out.print("<tr><td width='11%'>Nombre Menu </td><td width='27%'><input name='txtNombreMenu' type='text' id='txtNombreMenu' size='40' onKeyUp='this.value=this.value.toUpperCase();' /></td><td width='7%'>Url </td><td width='27%'><label><input name='txtUrlMenu' type='text' id='txtUrlMenu' size='40' /></label></td><td width='28%'><input name='btnCrearMenu' type='button' id='btnCrearMenu' value='     Crear     ' onclick='CrearMenu()' /></td></tr>");
			out.print("<tr><td colspan='5'><div align='center' class='style11' id='cabecera2' >Listado de Menu. </div></td></tr>");
			out.print("<tr><td colspan='3'><div align='center'>NOMBRE MENU </div></td><td colspan='2'><div align='center'>URL</div></td></tr>");
			rs=mcp.BuscarMenus();
			try {
				while(rs.next()){
				out.print("<tr><td colspan='3'>"+rs.getString(1)+"</td><td colspan='2'>"+rs.getString(2)+"</td></tr>");
				}
				out.print("</table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error: "+e);
				e.printStackTrace();
			}
		}
		if(va.equals("1.1")){
			String verificar="";
			rs=mcp.BuscarNombreMenuRepetido(NombreMenu);
			try {
				if(rs.next()){
					verificar=rs.getString(1);
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error: "+e);
				e.printStackTrace();
			}
			if(verificar==""){
				mcp.CrearMenu(NombreMenu, UrlMenu);
			}
			if(verificar!=""){
				out.print("El Nombre De Este Menu Ya Existe!\n Intente Otravez.");
			}
			
		}
		
		if(va.equals("2")){
			out.print("<table width='100%' border='1'><tr><td colspan='5'><div align='center' class='style11' id='cabecera2' >Crear Opciones de Menu </div></td></tr>");
			out.print("<tr><td width='15%'>Seleccione el  Menu </td><td colspan='4'><select name='cmbMenu' id='cmbMenu' onChange='VerOpcionesMenu()'><option value='Seleccione' selected='selected'>Seleccione</option>");
			rs=mcp.BuscarMenus();
			try {
				while(rs.next()){
					out.print(" <option value="+rs.getString(3)+">"+rs.getString(1)+"</option>");
				}
				out.print("</select></td></tr><tr><td colspan='5'><div id='OpMenu'></div></td></tr></table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error: "+e);
				e.printStackTrace();
			}
		}
		if(va.equals("2.1")){
			String Menu="";
			rs=mcp.BuscarOpcionesMenus(CodigoMenuFk);
			rs1=mcp.BuscarOpcionesMenus(CodigoMenuFk);
			out.print("<table width='100%' border='1'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2' >Datos Para  Opciones de Menu </div></td></tr>");
			out.print("<tr><td width='12%'>Nombre Opcion: </td><td colspan='2'><label><input name='txtNombreOpcion' type='text' id='txtNombreOpcion' size='40' onKeyUp='this.value=this.value.toUpperCase();' ></label></td><td width='58%'><input name='btnCrearOpMenu' type='button' id='btnCrearOpMenu' value='     Crear     ' onClick='CrearOpMenu()'></td></tr>");
			try {
				if(rs.next()){
					Menu=rs.getString(2);
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error: "+e);
				e.printStackTrace();
			}
			out.print("<tr><td colspan='4'><div align='center'> Opciones Del Menu: "+Menu+" </div></td></tr>");
			
			try {
				while(rs1.next()){
				out.print("<tr><td colspan='4'>"+rs1.getString(1)+"</td></tr>");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error: "+e);
				e.printStackTrace();
			}
		}
		if(va.equals("2.2")){
			String verificar="";
			rs=mcp.BuscarNombreOpcionMenuRepetido(NombreOpcionesMenu, CodigoMenuFk);
			try {
				if(rs.next()){
					verificar=rs.getString(1);
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error: "+e);
				e.printStackTrace();
			}
			if(verificar==""){
				mcp.CrearOpcionesMenu(NombreOpcionesMenu, CodigoMenuFk);
			}
			if(verificar!=""){
				out.print("La Relacion de la Opcion de Menu Ya existe!\n Intente Otravez.");
			}
		}
		
		if(va.equals("3")){
			out.print("<table width='100%' border='1'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2' >Crear Opciones Disponibles </div></td></tr>");
			out.print("<tr><td width='15%'>Seleccione el  Menu </td><td width='21%'><select name='cmbMenu' id='cmbMenu' onChange='CambioOpcionesMenu()'><option value='Seleccione' selected='selected'>Seleccione</option>");
			rs=mcp.BuscarMenus();
			try {
				while(rs.next()){
					out.print("<option value="+rs.getString(3)+">"+rs.getString(1)+"</option>");
				}
				out.print("</select></td><td width='17%'>Seleccione Opcion Menu </td><td><div id='OpcionesMenu'><select name='cmbOpMenu' id='cmbOpMenu'><option value='Seleccione'>Seleccione</option></select></div></td></tr>");
				out.print("<tr><td colspan='4'><div id='OpDisponibles'></div></td></tr></table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error: "+e);
				e.printStackTrace();
			}
		}
		if(va.equals("3.1")){
			rs=mcp.BuscarOpcionesMenus(CodigoMenuFk);
			out.print("<div id='OpcionesMenu'><select name='cmbOpMenu' id='cmbOpMenu' onchange='VerOpcionesDisponibles()'><option value='Seleccione'>Seleccione</option>");
			try {
				while(rs.next()){
					out.print("<option value="+rs.getString(3)+">"+rs.getString(1)+"</option>");
				}
				out.print("</select></div>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error: "+e);
				e.printStackTrace();
			}
		}
		
		if(va.equals("3.2")){
			String Menu="";
			String OpcionMenu="";
			rs=mcp.BuscarOpcionesMenus(CodigoMenuFk);
			rs1=mcp.BuscarOpcionesDisponibles(CodigoOpMenuFk);
			rs2=mcp.BuscarOpcionesDisponibles(CodigoOpMenuFk);
			out.print("<table width='100%' border='1'><tr><td colspan='5'><div align='center' class='style11' id='cabecera2' >Datos Opciones Disponibles </div></td></tr>");
			out.print(" <tr><td width='17%'>Nombre Opcion Disponible </td><td width='30%'><input name='txtOpcionDisponible' type='text' id='txtOpcionDisponible' size='40'></td><td width='15%'>Url Opcion Disponible </td><td width='28%'><input name='txtUrlOpDisponible' type='text' id='txtUrlOpDisponible' size='40'></td><td width='10%'><input name='btnCrearOpDisponible' type='button' id='btnCrearOpDisponible' value='     Crear     ' onClick='CrearOpDisponible()'></td></tr>");
			try {
				if(rs.next()){
					Menu=rs.getString(2);	
					
				}
				rs.getStatement().getConnection().close();
				if(rs1.next()){
					OpcionMenu=rs1.getString(4);
				}
				rs1.getStatement().getConnection().close();
				
				out.print("<tr><td colspan='5'><div align='center'>Opciones Disponibles de: "+Menu+" >> "+OpcionMenu+"</div></td></tr>");
				out.print("<tr><td colspan='2'><div align='center'>Opciones Disponibles</div></td><td colspan='3'><div align='center'>Url</div></td></tr>");
				while(rs2.next()){
				out.print("<tr><td colspan='2'>"+rs2.getString(1)+"</td><td colspan='3'>"+rs2.getString(2)+"</td></tr>");
				}
				out.print("</table>");
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(va.equals("3.3")){
			rs=mcp.BuscarNombreOpcionDisponiblesRepetido(NombreOpcionDisponible, CodigoOpMenuFk);
			String verificar="";
			try {
				if(rs.next()){
					verificar=rs.getString(1);
				}
				rs.getStatement().getConnection().close();
				if(verificar==""){
					//creamos la insercion.					
					mcp.CrearOpcionesDisponibles(NombreOpcionDisponible, UrlOpcionesMenu, CodigoOpMenuFk);
				}
				if(verificar!=""){
					out.print("Esta Opcion Ya Existe.\nIntente Otravez.");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}

}
