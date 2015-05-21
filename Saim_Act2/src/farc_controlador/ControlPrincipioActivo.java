package farc_controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.*; //para llamar la librería 


import java.io.PrintWriter;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.io.*;

import javax.servlet.http.HttpSession;


import farc_metodo.MetodoPrincipioActivo;

public class ControlPrincipioActivo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(request, response);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		HttpSession session = req.getSession(true);
		
		String va = req.getParameter("valor");

		
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out=res.getWriter();
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		ResultSet rs3=null;
		ResultSet rsfc=null;
		ResultSet rsef=null;
		
		MetodoPrincipioActivo mpa = new MetodoPrincipioActivo();
		
		
	if(va.equals("30A")){
		
		out.print("<table width='100%' border='0' align='center'>" +
				"<tr>" +
				"<td colspan='4' height='30' valign='top'>" +
					"<div align='center' class='style11' id='cabecera2'>Creacion de Principio Activo</div>" +
				"</td>" +
				"</tr>" +
				"</table>");
		out.print("<table width='100%' border=0' class='style6' ><tr BGCOLOR='#D3D3D3' ><td colspan='4' height='30' valign='top'><i><div align='center'>Descripcion del Principio Activo</div></i></td></tr>");
		out.print("<table width='100%' class='style6'><tr><td><div align='center'>Nombre: <input name='nombre' id='nombre' type='text'  size='20' /></div></td>" +
				"<td><div align='center'><input name='fk' id='fk' type='hidden' type='text' value='0' size='20' /></div></td></tr></table>");
		out.print("<br>");
		out.print("<table width='100%' border='0' class='style6' ><tr><td colspan='4'><div align='center'><input name='btnCrearPrincipio' type='button' id='btnCrearPrincipio' value=' Crear  ' onclick='crear()' /></div></td></tr><tr></tr><tr></tr></table>");
		
			
	}
	

	
	if(va.equals("CAV")){
		String nombre=req.getParameter("nombre");
		String cod_grupoFarmacologico_fk=req.getParameter("cod_grupoFarmacologico_fk");
		try {
			while(rs.next()){
			out.print(rs.getString(1)+"|"+rs.getString(2));
			}
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
	}
	
	
	
	if(va.equals("1")){

		String nombre=req.getParameter("nombre");
		String fk=req.getParameter("fk");
		
		rs = mpa.verificar(nombre);
		String codsustGenerica = "";
		try {
			if(rs.next()){
				codsustGenerica = rs.getString(1);
				out.print("El Principio Activo ya se encuentra registrado");
			}else{
				mpa.CrearPrincipioActivo(nombre, fk);
				out.print("Se registro exitosamente el Principio Activo");
			}
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		
		
		
		
		
	}
	
	

		
	
}
}


