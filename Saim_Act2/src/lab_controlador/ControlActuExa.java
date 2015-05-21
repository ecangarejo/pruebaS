/**
 * controlador: ControlActuExa se encuentra el proceso para la 
 * actualizacion de los examenes que son de tipo rango.
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

import lab_logica.MetodoActExa;

/**
 * Servlet implementation class ControlActuExa
 */
public class ControlActuExa extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		MetodoActExa mae = new MetodoActExa ();
		String codUni=req.getParameter("codUni");
		String codGen=req.getParameter("codGen");
		String valIni=req.getParameter("valIni");
		String valFin=req.getParameter("valFin");
		String codExa=req.getParameter("codExa");
		//mae.ActualizarExamen(codUni, codGen, valIni, valFin, codExa);
		res.sendRedirect("lab_actu_examen.jsp");
		
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");
		MetodoActExa mae = new MetodoActExa ();
		String codUnidad=req.getParameter("codUni");
		String codGenero=req.getParameter("codGen");
		String valIni=req.getParameter("valIni");
		String valFin=req.getParameter("valFin");
		String codExa=req.getParameter("codExa");
		String CodigoRango=req.getParameter("CodigoRango");
		String va = req.getParameter("valor");
		String entra=req.getParameter("entra");
		String area = req.getParameter("area");
		String nomArea=req.getParameter("codarea");
		String nomsub=req.getParameter("nomsubarea");
		String codsuba=req.getParameter("codSuba");
		String nombre=req.getParameter("nomexa");
		//String Uni=req.getParameter("Uni");
		String nomGen=req.getParameter("nomGen");
		String nomUni=req.getParameter("nomUni");
		PrintWriter out=res.getWriter();
		ResultSet rs=null;
		
		if(va.equals("0")){
			rs=mae.obtenerExamenRango(codsuba);
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
				// System.out.println("ERROR EN CONTROLADOR_0: "+e1);
			  }
		}
		
		if(va.equals("1")){
			out.println("<table id='prueba' name='prueba' width='100%' class='style6'><tr><td colspan='4' align='center' class='style11' id='cabecera2'><span class='style1'>SI PERTENECE A GRUPO </span></td></tr><tr><td width='222'>ESCOJA EL GRUPO </td><td colspan='3'><label><select name='cmbareaA' id='cmbareaA' onchange='codareaGru ()'><option>SELECCIONE</option></select><span class='style8'>*</span></label></td></tr><tr><td>ESCOJA EL EXAMEN </td><td colspan='3'><label><select name='cmbexagruAc'   id='cmbexagruAc' onchange='mostrarrango()' ><option>SELECCIONE</option></select><span class='style8'>*</span></label></td></tr></table>");
		}
		if(va.equals("2")){
			
			out.println("<table id='prueba' class='style6' name='prueba' width='100%'><tr><td colspan='5' align='center' id='cabecera2' class='style11'>SI PERTENECE A SUBGRUPO </td></tr><tr><td width='215'>ESCOJA EL GRUPO </td><td colspan='4'><label><select name='cmbareaA' onchange='codarea()' id='cmbareaA'><option>SELECCIONE</option></select><span class='style8'>*</span></label></td></tr><tr><td>ESCOJA EL SUBGRUPO </td><td colspan='4'><label><select name='cmbsubareaS' id='cmbsubareaS'onchange='codsubarea()'><option>SELECCIONE</option></select><span class='style8'>*</span></label></td></tr><tr><td>ESCOJA EL EXAMEN </td><td colspan='4'><label><select name='cmbexamenAc' id='cmbexamenAc' onchange='codexamen()'><option>SELECCIONE</option></select><span class='style8'>*</span></label></td></tr></table>");
		}
		if(va.equals("3")){
			rs=mae.obtenerArea() ;
			String v[] = new String[1000];
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
				// System.out.println("ERROR EN CONTROLADOR_3: "+e1);
			  }
			}
		
		if(va.equals("4")){				
			rs=mae.obtenerSubArea(area);
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
				// System.out.println("ERROR EN CONTROLADOR_4: "+e1);
			  }
		}
		
		if(va.equals("5")){
			rs=mae.obtenerCodigoExamen(codsuba, nombre);
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
				// System.out.println("ERROR EN CONTROLADOR_5: "+e1);
				 e1.getMessage();
			  }
				
		}
		
		if(va.equals("6")){
			int con=0;
			String ValorInicial="";
			String ValorFinal="";
			String CodigoUnidad="";
			String NombreUnidad="";
			rs=mae.obtenerDatosExamen(codsuba, nombre);	
			out.print("<table id='prueba' name='prueba' width='100%' class='style6'><tr><td colspan='7' align='center' id='cabecera2'><span class='style2'>"+nombre+"</span></td></tr>");
			try{
				
				while(rs.next()){
					con=con+1;
					ValorInicial=rs.getString(4);
					ValorFinal=rs.getString(5);
					CodigoUnidad=rs.getString(7);
					NombreUnidad=rs.getString(3);
					out.print("<tr><td width='129'>GENERO</td><td width='173'><label><select name='cmbGeneroACt' id='cmbGeneroACt' onchange='obtenerCodGenero()'><option value="+rs.getString(8)+">"+rs.getString(6)+"</option></select></label></td><td width='117'>VALOR INICIAL</td><td width='181'><input name='txtvaliniAct' type='text' id='txtvaliniAct' size='12' maxlength='12' value="+rs.getString(4)+" /><span class='style8'>*</span></td><td width='174'>VALOR FINAL</td><td width='175'><input name='txtvalfinAct' type='text' id='txtvalfinAct' size='12' maxlength='12' value="+rs.getString(5)+" /><span class='style8'>*<input name='txtCodigoRango' type='hidden' id='txtCodigoRango' value="+rs.getString(9)+" /></span></td></tr>");
					
				}
				
				
				
				rs.getStatement().getConnection().close();
			}			
			catch(SQLException e1){
				// System.out.println("ERROR EN CONTROLADOR_6: "+e1);
				 e1.getMessage();
			}	
			out.print("<tr><td>UNIDAD</td><td><label><select name='cmbunidadAct' id='cmbunidadAct' onchange='obtenerCodUnidad()' ><option value="+CodigoUnidad+">"+NombreUnidad+"</option></select><span class='style8'>*</span></label></td><td>&nbsp;</td><td><label></label></td><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td colspan='7' align='center'><input name='txtContador' type='hidden' id='txtContador' value="+con+" /><input type='button' class='boton4' name='btningresar1' value='Actualizar' onclick='Actualizar()' /></td></tr></table>");
		}	
		
		if(va.equals("7")){
			rs=mae.obtenerDatosExamen(codsuba, nombre);
			String codunidad="";
			String codgenero="";
			String nomuni="";
			String nomgen="";
			
			try{
				while(rs.next()){
					nomuni=rs.getString(3);
					nomgen=rs.getString(6);
					codunidad=rs.getString(7);
					codgenero=rs.getString(8);
					if(nomuni.equals("ñ")){
						for(int i=0;i<nomuni.length();i++){
							nomuni=nomuni.replace('ñ','%');
			     		    }
					}
					out.println(codunidad+"_"+codgenero+"_"+nomuni+"_"+nomgen);
				}	
				
				rs.getStatement().getConnection().close();

			}			
			catch(SQLException e1){
				// System.out.println("ERROR EN CONTROLADOR_7: "+e1);
				 e1.getMessage();
			}			
		}
		
		
		if(va.equals("8")){
			//System.out.println("ENTRO A VA=8 CON NOMBRE DE LA UNIDAD= "+nomUni);
			rs=mae.obtenerCodigoUnidad(nomUni);
			String codUni = "";
			try{
				while(rs.next()){
					codUni=rs.getString(1);
					out.println(codUni+"_");
				}	
				rs.getStatement().getConnection().close();
			}
			 catch(SQLException e1){
				// System.out.println("ERROR EN CONTROLADOR_8: "+e1);
				 e1.getMessage();
			  }				
		}
		
		if(va.equals("9")){
			
			rs=mae.obtenerUnidad() ;
			String v[] = new String[1000];
			try{
				int c=0;
				while(rs.next()){
					v[c]=rs.getString(2);
					if(v[c].equals("ñ")){
						for(int i=0;i<v[c].length();i++){
			     		      v[c]=v[c].replace('ñ','%');
			     		    }
					}
					out.println(v[c]+"_");
					c++;
				}
				rs.getStatement().getConnection().close();
			}
			 catch(SQLException e1){
				// System.out.println("ERROR EN CONTROLADOR_9: "+e1);
				 e1.getMessage();
			  }
		}
		if (va.equals("10")){
				rs=mae.obtenerNombreGenero();
			String v[] = new String[1000];
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
				// System.out.println("ERROR EN CONTROLADOR_10: "+e1);
				 e1.getMessage();
			  }			 
		}
		if(va.equals("11")){
			rs=mae.obtenerCodigoArea(nomArea) ;
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
				// System.out.println("ERROR EN CONTROLADOR_11: "+e1);
				 e1.getMessage();
			  }				
		}
		
		if(va.equals("12")){
			rs=mae.obtenerCodigoSubArea(nomsub, nomArea) ;
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
				// System.out.println("ERROR EN CONTROLADOR_12: "+e1);
				 e1.getMessage();
			  }				
		}
		
		if(va.equals("13")){
			rs=mae.obtenerCodigoGenero(nomGen);
			String codGen = "";
			try{
				while(rs.next()){
					codGen=rs.getString(1);
					out.println(codGen+"_");
				}	
				rs.getStatement().getConnection().close();
			}
			 catch(SQLException e1){
				// System.out.println("ERROR EN CONTROLADOR_13: "+e1);
				 e1.getMessage();
			  }				
		}
		
		if(va.equals("14")){
			rs=mae.obtenerExamenGru(area);
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
				// System.out.println("ERROR EN CONTROLADOR_14: "+e1);
				 e1.getMessage();
			  }
			
		}
		/*if(valor.equals("16")){
			out.println("<table id='prueba' name='prueba' width='570' border='0'><tr><td colspan='5' align='center' style='background-color:#3282C2'><span class='style2'>RANGO</span></td></tr><tr><td width='129'>VALOR INICIAL</td><td width='90'><label><input name='txtvaliniAct' type='text' id='txtvaliniAct' size='12' maxlength='12' /><span class='style1'>*</span></label></td><td width='121'>VALOR FINAL </td><td width='95'><input name='txtvalfinAct' type='text' id='txtvalfinAct' size='12' maxlength='12' /><span class='style1'>*</span></td></tr>" );
			out.println("<tr><td>UNIDAD</td><td><label><select name='cmbunidadAct' id='cmbunidadAct' onchange='obtenerCodUnidad()' ><option>SELECCIONE</option></select><span class='style1'>*</span></label></td><td>GENERO</td><td><label><select name='cmbGeneroACt' id='cmbGeneroACt' onchange='obtenerCodGenero()'><option>SELECCIONE</option></select></label></td></tr><tr><td colspan='5' align='center'><input type='button' name='btningresar1' value='Actualizar' onclick='verificar()' /></td></tr></table>");
			
		}*/
		if(va.equals("15")){
			rs=mae.obtenerCodigoExamenGru(nomArea, nombre);
			String codigoExa = "";
			String codunidad="";
			String codgenero="";
			String nomuni="";
			String nomgen="";
			String valorIni="";
			String valorFin="";
			String CodigoRangoIndi="";
			
			
			try{
				while(rs.next()){
					codigoExa=rs.getString(1);
					codunidad=rs.getString(7);
					codgenero=rs.getString(8);
					nomuni=rs.getString(3);
					nomgen=rs.getString(6);
					CodigoRangoIndi=rs.getString(9);
					valorIni=rs.getString(4);
					valorFin=rs.getString(5);
					out.println(codigoExa+"_"+codunidad+"_"+codgenero+"_"+nomuni+"_"+nomgen+"_"+valorIni+"_"+valorFin+"_"+CodigoRangoIndi);				
				}	
				rs.getStatement().getConnection().close();
			}

			 catch(SQLException e1){
				// System.out.println("ERROR EN CONTROLADOR_15: "+e1);
				 e1.getMessage();
			  }
				
		}
		if(va.equals("16")){											
					out.println("<table id='prueba' name='prueba' width='100%' class='style6'><tr><td colspan='5' align='center' id='cabecera2'><span class='style2'>RANGO</span></td></tr><tr><td width='129'>VALOR INICIAL</td><td width='90'><label><input name='txtvaliniAct' type='text' id='txtvaliniAct' size='12' maxlength='12' /><input name='txtContador' type='hidden' id='txtContador' value=1 /><input name='txtCodigoRango' type='hidden' id='txtCodigoRango' /><span class='style8'>*</span></label></td><td width='121'>VALOR FINAL </td><td width='95'><input name='txtvalfinAct' type='text' id='txtvalfinAct' size='12' maxlength='12' /><span class='style8'>*</span></td></tr><tr><td>UNIDAD</td><td><label><select name='cmbunidadAct' id='cmbunidadAct' onchange='obtenerCodUnidad()' ><option>SELECCIONE</option></select><span class='style8'>*</span></label></td><td>GENERO</td><td><label><select name='cmbGeneroACt' id='cmbGeneroACt' onchange='obtenerCodGenero()'><option>SELECCIONE</option></select></label></td></tr><tr><td colspan='5' align='center'><input class='boton4' type='button' name='btningresar1' value='Actualizar' onclick='Actualizar()' /></td></tr></table>");
						
		}
		if(va.equals("17")){
			//mae.ActualizarExamen(codUnidad, codGenero, valIni, valFin, codExa);
			//mae.ActualizarExamen(codUnidad, codGenero, valIni, valFin, CodigoRango);
			if(entra.equals("1")){
				mae.ActualizarExamenVarios(codUnidad, codGenero, valIni, valFin, CodigoRango);
			}
			if(entra.equals("2")){
				mae.ActualizarExamenCompl(codUnidad, codGenero, valIni, valFin, CodigoRango);
			}
		}
					
	}

}
