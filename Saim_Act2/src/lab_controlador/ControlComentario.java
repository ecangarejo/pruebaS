/**
 * controlador: ControlComentario se encuentra el proceso para la 
 * creacion de los comentarios.
*/
package lab_controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lab_logica.MetodoComentario;


public class ControlComentario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		MetodoComentario mc = new MetodoComentario();
		String normalini = req.getParameter("normalini");	
		String normalfin = req.getParameter("normalfin");
		//
		String bajoini = req.getParameter("bajoini");	
		String bajofin = req.getParameter("bajofin");
		//
		String altoini = req.getParameter("altoini");	
		String altofin = req.getParameter("altofin");
		//
		String bcritini = req.getParameter("bcritini");	
		String bcritfin = req.getParameter("bcritfin");
		//
		String acritini = req.getParameter("acritini");	
		String acritfin = req.getParameter("acritfin");
		//	
		String codExafk = req.getParameter("codExafk");
		
		mc.insertarComentario(normalini, normalfin, bajoini, bajofin, altoini, altofin, bcritini, bcritfin, acritini, acritfin, codExafk);
		res.sendRedirect("lab_comentario.jsp");
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		MetodoComentario mc = new MetodoComentario();
		String va = req.getParameter("valor");	
		String nomArea=req.getParameter("codarea");
		String nomsub=req.getParameter("nomsubarea");
		String area = req.getParameter("area");
		String codsuba=req.getParameter("codSuba");
		String nombre=req.getParameter("nomexa");
		String codGru=req.getParameter("codGru");
		String nomExa=req.getParameter("nomExa");
		String codExa=req.getParameter("codExa");
		res.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out=res.getWriter();
		ResultSet rs=null;
		if(va.equals("1")){
			out.println("<table id='prueba' name='prueba' width='480' border='0'><tr><td colspan='4' align='center' style='background-color:#3282C2'><span class='style2'>Pertenece a Grupo</span></td></tr><tr><td width='202'>Escoja el Grupo</td><td width='268' colspan='3'><label><select name='cmbareaA' id='cmbareaA' onchange='codareaGru ()'><option>SELECCIONE</option></select><span class='style1'>*</span></label></td></tr><tr><td>Escoja el Examen</td><td colspan='3'><label><select name='cmbexagruAc' id='cmbexagruAc' onchange='codexamengru()' ><option>SELECCIONE</option></select><span class='style1'>*</span></label></td></tr></table>");
		}
		if(va.equals("2")){
		out.println("<table id='prueba' name='prueba' width='480' border='0'><tr><td colspan='5' align='center' style='background-color:#3282C2'><span class='style2'>Pertenece a Subgrupo </td></tr><tr><td width='200'>Escoja el Grupo </td><td width='270' colspan='4'><label><select name='cmbareaA' onchange='codarea()' id='cmbareaA'><option>SELECCIONE</option></select><span class='style1'>*</span></label></td></tr><tr><td>Escoja el Subgrupo </td><td colspan='4'><label><select name='cmbsubareaS' id='cmbsubareaS'onchange='codsubarea()'><option>SELECCIONE</option></select><span class='style1'>*</span></label></td></tr><tr><td>Escoja el Examen </td><td colspan='4'><label><select name='cmbexamenAc' id='cmbexamenAc' onchange='codexamen()'><option>SELECCIONE</option></select><span class='style1'>*</span></label></td></tr></table>");
		}
		if(va.equals("3")){
			rs=mc.obtenerArea() ;
			String v[] = new String[1000];
			try{
				int c=0;
				while(rs.next()){
					v[c]=rs.getString(1);
					out.println(v[c]+"_");				
					c++;
					
				}	
				rs.getStatement().getConnection().close();
			}
			 catch(SQLException e1){
				 System.out.println("ERROR EN CONTROLADOR_3: "+e1);
			  }
			}
		if(va.equals("4")){
			rs=mc.obtenerCodigoArea(nomArea) ;
			String v[] = new String[1000];
			try{
				int c=0;
				while(rs.next()){
					v[c]=rs.getString(1);
					out.println(v[c]+"_");				
					c++;
				}	
				rs.getStatement().getConnection().close();
			}
			 catch(SQLException e1){
				 System.out.println("ERROR EN CONTROLADOR_4: "+e1);
				 e1.getMessage();
			  }				
		}
		if(va.equals("5")){
			rs=mc.obtenerExamenGru(area);
			String v[] = new String[1000];
			try{
				int c=0;
				while(rs.next()){
					v[c]=rs.getString(4);
					out.println(v[c]+"_");				
					c++;
				}
				rs.getStatement().getConnection().close();
			}
			 catch(SQLException e1){
				 System.out.println("ERROR EN CONTROLADOR_5: "+e1);
				 e1.getMessage();
			  }
			
		}
		if(va.equals("6")){	
			rs=mc.obtenerSubArea(area);
			String v[]=new String [1000];
			try{
				int c=0;
				while(rs.next()){
					v[c]=rs.getString(2);
					out.println(v[c]+"_");				
					c++;
				}
				rs.getStatement().getConnection().close();			
			}
			 catch(SQLException e1){
				 System.out.println("ERROR EN CONTROLADOR_6: "+e1);
			  }
		}
		if(va.equals("7")){
			rs=mc.obtenerCodigoSubArea(nomsub, nomArea) ;
			String v[] = new String[1000];
			try{
				int c=0;
				while(rs.next()){
					v[c]=rs.getString(1);
					out.println(v[c]+"_");	
					c++;
				}	
				rs.getStatement().getConnection().close();
			}
			 catch(SQLException e1){
				 System.out.println("ERROR EN CONTROLADOR_7: "+e1);
				 e1.getMessage();
			  }				
		}
		if(va.equals("8")){
			rs=mc.obtenerExamenRango(codsuba);
			String v[] = new String[1000];
			try{
				int c=0;
				while(rs.next()){
					v[c]=rs.getString(6);
					out.println(v[c]+"_");				
					c++;
				}	
				rs.getStatement().getConnection().close();
			}
			 catch(SQLException e1){
				 System.out.println("ERROR EN CONTROLADOR_8: "+e1);
			  }
		}
		if(va.equals("9")){
			rs=mc.obtenerCodigoExamen(codsuba, nombre);
			String v[] = new String[1000];
			try{
				int c=0;
				while(rs.next()){
					v[c]=rs.getString(1);
					out.println(v[c]+"_");				
					c++;
				}	
				rs.getStatement().getConnection().close();
			}

			 catch(SQLException e1){
				 System.out.println("ERROR EN CONTROLADOR_9: "+e1);
				 e1.getMessage();
			  }
				
		}
		
		if(va.equals("10")){
			rs=mc.obtenerCodigoExaGrupo(codGru, nomExa);
			String codigo = "";
			try{
				
				while(rs.next()){
					codigo=rs.getString(1);
					out.println(codigo+"_");				
					
				}	
				rs.getStatement().getConnection().close();
			}

			 catch(SQLException e1){
				 System.out.println("ERROR EN CONTROLADOR_10: "+e1);
				 e1.getMessage();
			  }
		}
		if(va.equals("11")){
			out.println("<table width='480' border='0'><tr><td colspan='2'>Valor Referencia Inicial </td><td width='63'><input name='txtvalini' type='text' id='txtvalini' size='10' readonly='' /></td><td colspan='4'>Valor Referencia Final </td><td width='74' align='rigth'><input name='txtvalfin' type='text' id='txtvalfin' size='10' readonly='' /></td></tr><tr><td colspan='8' bgcolor='3282C2'>&nbsp;</td></tr><tr><td width='93'>Normal</td><td width='60'>Entre</td><td><label><input name='txtnormalini' type='text' id='txtnormalini' size='10' /></label></td><td width='19'>&nbsp;</td><td width='20'>&nbsp;</td><td width='58' align='right'>Y</td><td width='59'>&nbsp;</td><td align='rigth'><label><input name='txtnormalfin' type='text' id='txtnormalfin' size='10' /></label></td></tr><tr><td>Bajo</td><td>Entre</td><td><label><input name='txtbajoini' type='text' id='txtbajoini' size='10' /></label></td><td>&nbsp;</td><td>&nbsp;</td><td align='right'>Y</td><td>&nbsp;</td><td align='rigth'><label><input name='txtbajofin' type='text' id='txtbajofin' size='10' /></label></td></tr><tr><td>Alto</td><td>Entre</td><td><label><input name='txtaltoini' type='text' id='txtaltoini' size='10' /></label></td><td>&nbsp;</td><td>&nbsp;</td><td align='right'>Y</td><td>&nbsp;</td><td align='rigth'><label><input name='txtaltofin' type='text' id='txtaltofin' size='10' /></label></td></tr><tr><td>Bajo Critico</td><td>Entre</td><td><label><input name='txtbcritini' type='text' id='txtbcritini' size='10' /></label></td><td>&nbsp;</td><td>&nbsp;</td><td align='right'>Y</td><td>&nbsp;</td><td align='rigth'><label><input name='txtbcritfin' type='text' id='txtbcritfin' size='10' /></label></td></tr><tr><td>Alto Critico </td><td>Entre</td><td><label><input name='txtacritini' type='text' id='txtacritini' size='10' /></label></td><td>&nbsp;</td><td>&nbsp;</td><td align='right'>Y</td><td>&nbsp;</td><td align='rigth'><label><input name='txtacritfin' type='text' id='txtacritfin' size='10' /></label></td></tr><tr><td colspan='2'>&nbsp;</td><td colspan='5' align='center'><label><input name='btnInserComen' type='button' id='btnInserComen' value='Ingresar' onclick='insertarComentario()'></label></td><td align='rigth'>&nbsp;</td></tr></table>");
		}
		
		if(va.equals("12")){
			rs=mc.obtenerValoresExamen(codExa);			
			String valorIni="";
			String valorFin="";
			String normalini="";
			String normalfin="";
			String bajoini="";
			String bajofin="";
			String altoini="";
			String altofin="";
			String bcritini="";
			String bcritfin="";
			String acritini="";
			String acritfin="";
			try{
				while(rs.next()){
					
					valorIni=rs.getString(1);
					valorFin=rs.getString(2);
					normalini=rs.getString(3);
					normalfin=rs.getString(4);
					bajoini=rs.getString(5);
					bajofin=rs.getString(6);
					altoini=rs.getString(7);
					altofin=rs.getString(8);
					bcritini=rs.getString(9);
					bcritfin=rs.getString(10);
					acritini=rs.getString(11);
					acritfin=rs.getString(12);
					
					out.println(valorIni+"_"+valorFin+"_"+normalini+"_"+normalfin+"_"+bajoini+"_"+bajofin+"_"+altoini+"_"+altofin+"_"+bcritini+"_"+bcritfin+"_"+acritini+"_"+acritfin);				
				}	
				rs.getStatement().getConnection().close();
			}

			 catch(SQLException e1){
				 System.out.println("ERROR EN CONTROLADOR_12: "+e1);
				 e1.getMessage();
			  }
				
		}
	}

}
