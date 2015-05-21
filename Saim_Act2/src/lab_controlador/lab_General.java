/**
 * controlador: lab_General se encuentra el proceso para  
 * buscar los rangos de las unidades.
*/
package lab_controlador;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lab_logica.MetodoResultado;
import lab_logica.MetodolabPa;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.tree.*;
import java.net.*;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


/**
 * Servlet implementation class lab_General
 */
public class lab_General extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int accion;
		response.setContentType("text/html;charset=UTF-8");
        accion = Integer.parseInt(request.getParameter("txtAccion"));

	if(accion == 26){
     
           String cad =request.getParameter("codigo");
           String tipo =request.getParameter("tipo");
         MetodolabPa mp = new MetodolabPa();
   
        
         
           ResultSet rs=null;
           try {
               rs = mp.listarParaAutocompletarControl(cad,tipo);
               String cadena ="";
               String nombre ="";
               cadena="[";
               while(rs.next()){
               	nombre=rs.getString(2)+" "+rs.getString(3);
               	cadena = cadena+"'"+rs.getString(1)+"|"+nombre+"'";
               	cadena = cadena +",";	
               }
              
               cadena = cadena+"]";
               rs.getStatement().getConnection().close();
           //  System.out.println(cadena);
               response.getWriter().write(cadena);
           } catch (SQLException e) {
               e.getMessage();
           } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

       }
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String z=request.getParameter("z");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter rt=response.getWriter();
		if(z.equals("1")){
			rt.print("<table><tr><td>");
			rt.print("Tipo Documento: <select name='cbtipo' size='1' id='cbtipo'><option selected='selected'>CC</option><option>TI</option><option>RC</option><option>Nacido Vivo</option></td><td>Numero: <input type='text' name='cedula' id='cedula' onkeyup='validarcom_General(form1,this,event),autocompleta()' value='' /></td></tr></table>");
		}
		
		if(z.equals("2")){

	
		}
	}
	

}

