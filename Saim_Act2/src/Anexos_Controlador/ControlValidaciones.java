package Anexos_Controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;

import Anexos_metodo.MetodoValidaciones;


public class ControlValidaciones extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest re, HttpServletResponse res)throws ServletException, IOException{
		res.setContentType("text/html;charset=UTF-8");
		MetodoValidaciones na = new MetodoValidaciones();
		String yo = re.getParameter("yo");
		String x = re.getParameter("x");
		String xx = re.getParameter("de");
		String dpto_evento = re.getParameter("dpto_evento");
		String evento = re.getParameter("evento");
		String codigo = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		PrintWriter out = res.getWriter();
		
		if(yo.equals("1")){//Se obtiene el paises  llamados de una funcion ValidacionesFurips.js ajax
			rs = na.SQLPais();
			try {
				while(rs.next()){
					out.println(rs.getString(1)+"_");
				}
				rs.getStatement().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else if(yo.equals("2")){//Se obtiene Departamentos llamados de una funcion ValidacionesFurips.js ajax
			rs = na.SQLDep(x);
			try {
				while(rs.next()){
					out.println(rs.getString(1)+"_");
				}
				rs.getStatement().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else if(yo.equals("3")){//Se obtiene Municipios llamados de una funcion ValidacionesFurips.js ajaxa
			rs1 = na.SQLCodDepa(xx);
			try {
				while(rs1.next()){
					codigo = rs1.getString(1);
				}
				rs = na.SQLMun(codigo);
				rs1.getStatement().close();
				while(rs.next()){
					out.println(rs.getString(1)+"_");
				}
				rs.getStatement().close();
			} catch(SQLException e1){
				e1.printStackTrace();
			}
		}else if(yo.equals("cargaDeptos")){
			rs1 = na.getDeptos();
			try {
				while(rs1.next()){
					out.println(rs1.getString(1)+"_");
				}
				rs1.getStatement().close();
			} catch(SQLException e1){
				e1.printStackTrace();
			}
		}
		
		if(yo.equals("4")){//Se obtiene Eventos llamados de una funcion 1.js ajaxEvento
			out.print("<td><select name='eventonaturaleza' id='eventonaturaleza' ><option value='Seleccione'>Seleccione</option>");
			rs = na.SQLEventoNaturaleza(evento);
			try {
				while(rs.next()){
					out.print("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
				}
				rs.getStatement().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(yo.equals("5")){			
			out.print("<td><select name='cbmun' id='cbmun' ><option value='Seleccione'>Seleccione</option>");
			rs1 = na.SQLCodDepa(dpto_evento);
			try {
				if(rs1.next()){
					codigo = rs1.getString(1);
				}
				rs = na.SQLMun(codigo);
				while(rs.next()){
				out.print("<option value="+rs.getString(2)+">"+rs.getString(1)+"</option>");
				}
				rs.getStatement().close();
				rs1.getStatement().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("</select>");
		}
		
		if(yo.equals("6")){			
			out.print("<td><select name='municipio' id='municipio' ><option value='Seleccione'>Seleccione</option>");
			rs1 = na.SQLCodDepa(dpto_evento);
			try {
				if(rs1.next()){
					codigo = rs1.getString(1);
				}
				rs = na.SQLMun(codigo);
				while(rs.next()){
				out.print("<option value="+rs.getString(2)+">"+rs.getString(1)+"</option>");
				}
				rs.getStatement().close();
				rs1.getStatement().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("</select>");
		}
		
		if(yo.equals("7")){			
			out.print("<td><select name='mun' id='mun' ><option value='Seleccione'>Seleccione</option>");
			rs1 = na.SQLCodDepa(dpto_evento);
			try {
				if(rs1.next()){
					codigo = rs1.getString(1);
				}
				rs = na.SQLMun(codigo);
				while(rs.next()){
				out.print("<option value="+rs.getString(2)+">"+rs.getString(1)+"</option>");
				}
				rs.getStatement().close();
				rs1.getStatement().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("</select>");
		}
		
	}
}
