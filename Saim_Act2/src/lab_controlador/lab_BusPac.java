/**
 * controlador: lab_BusPac se encuentra el proceso para  
 * cuando ya una vez se encuantre el paciente,cuando se le asigne un examen
 * ya sea tipo individual o en grupo llene las opciones dependiendo de la
 * opcion escogida.
*/
package lab_controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import adm_logica.Conexion;
import lab_logica.MetodoExamen;
import lab_logica.MetodoResultado;
import lab_logica.MetodoSubarea;

/**
 * Servlet implementation class lab_BusPac
 */
public class lab_BusPac extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String z=request.getParameter("z");
		MetodoExamen exa=new MetodoExamen();
		MetodoSubarea me=new MetodoSubarea();
		ResultSet rs=null;
		ResultSet bus=null;
		ResultSet ge=null;
		ResultSet pa=null;		
		ResultSet rs1=null;
		ResultSet rs2=null;
		ResultSet rs3=null;
		PrintWriter rt=response.getWriter();
		if(z.equals("1")){
			rt.print("<table class='style6'><tr><td> ");
			rt.print("Tipo Documento: <select name='cbtipo' size='1' id='cbtipo'>");
					//"<option selected='selected'>CC</option>" +
					//"<option>TI</option>" +
					//"<option>RC</option>" +
					//"<option>Nacido Vivo</option>" +
			try{
				Conexion con2;
				try {
					java.sql.Statement st3 = null;  
					con2 = new Conexion();
					st3 = con2.conn.createStatement();
					rs3=st3.executeQuery("SELECT sigla,descripcion FROM adm_tipodocumento");
					while(rs3.next()){
						rt.print("<option value="+rs3.getString(1)+">"+rs3.getString(1)+"</option>");
					}
					rs3.getStatement().getConnection().close();
					st3.close();
					con2.cerrar();
				}catch(SQLException e){
							  
					System.out.println(e);
					rt.println("no se pudo realizar la conexion!<br/>"); 
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			rt.print("</td><td>Numero: <input type='text' name='cedula' id='cedula' onkeyup='validarcom(form1,this,event),autocompleta()' value='' /></td></tr></table>");
					
			   //ResultSet rs3=null;
			  
			   
		}
		if(z.equals("4")){
			rt.println("<table><tr><td>");
			rt.print("Tipo Documento: <select name='cbti' size='1' id='cbti'><option selected='selected'>CC</option><option>TI</option><option>RC</option><option>Nacido Vivo</option></td><td>Numero: <input type='text' name='ced' id='ced' onkeyup='validarcom(form1,this,event)' value='' /></td></tr></table>");
		}
		
		if(z.equals("3")){
			rt.println("<table class='style6'><tr><td>");
			rt.print("Nombre: <input type='text' name='nombre' id='nombre' onkeyup='autocompleta_nombre(),validarcom_nombre(form1,this,event)' value='' size='50' maxlength='100' /></td><td><input type='hidden' name='cedula' id='cedula' onkeyup='' /><input type='hidden' name='tipodoce' id='tipodoce' onkeyup='' /></td></tr></table>");
		}
		
		if(z.equals("113")){
			rt.println("<table class='style6'><tr><td>");
			rt.print("Nombre: <input type='text' name='nombre' id='nombre' onkeyup='autocompleta_nombre(),validarcom_nombre1(form1,this,event)' value='' size='50' maxlength='100' /></td><td><input type='hidden' name='cedula' id='cedula' onkeyup='' /><input type='hidden' name='tipodoce' id='tipodoce' onkeyup='' /></td></tr></table>");
		}
		
		if(z.equals("5")){
			
		}
		
		if(z.equals("6")){
			
			rs=me.SQLArea();
			rt.println("<table class='style6'>");
			rt.print("<tr><td>Tipo Documento: <select name='tipo' size='1' id='tipo'><option selected='selected'>CC</option><option>TI</option><option>RC</option><option>Nacido Vivo</option></td><td>Numero: <input type='text' name='cedulapac' id='cedulapac'/></td></tr>");
		    rt.print("</tr></table>Elija Area: <select name='cbarea' size='1' id='cbarea' onchange='buscarGrupo()'>");
		    try {
		    	rt.print("<option selected='selected'>SELECCIONE...</option>");
				while(rs.next()){
					rt.print("<option>"+rs.getString(1)+"</option>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				    e.printStackTrace();
			}
		}
		
		
		if(z.equals("7")){
           String area=request.getParameter("area");			
           //ResultSet rs=null;
           rs=exa.MetodoSubarea(area);
           PrintWriter out=response.getWriter();
           out.print("<b clas='style'>Elija Subarea:</b> <select name='subare' size='1' id='subare'>");
			try {
				out.print("<option selected>SELECCIONE...</option>");
				while(rs.next()){
					out.println("<option>"+rs.getString(1)+"</option>");
				}
				out.print("</select>&nbsp&nbsp<input type='button' class='boton4' name='guardar' value='Buscar...' onClick='ReportesGrupo();' />");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(z.equals("8")){
			rt.println("<table class='style6'>");
			rt.print("<tr><td>Tipo Documento: <select name='cbtipo' size='1' id='cbtipo'>");
					//"<option selected='selected'>CC</option>" +
					//"<option>TI</option>" +
					//"<option>RC</option>" +
					//"<option>Nacido Vivo</option>" +
					try{
						Conexion con2;
						try {
							java.sql.Statement st3 = null;  
							con2 = new Conexion();
							st3 = con2.conn.createStatement();
							rs3=st3.executeQuery("SELECT sigla,descripcion FROM adm_tipodocumento");
							while(rs3.next()){
								rt.print("<option value="+rs3.getString(1)+">"+rs3.getString(1)+"</option>");
							}
							rs3.getStatement().getConnection().close();
							st3.close();
							con2.cerrar();
						}catch(SQLException e){
									  
							System.out.println(e);
							rt.println("no se pudo realizar la conexion!<br/>"); 
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					rt.print("</td><td>Numero: <input type='text' name='cedula' id='cedula' onkeyup='valid(this,event),autocompleta();' /></td></tr><tr><td></td><td><div id='sugerencias' ></div></td></tr></table>");
		}
		if(z.equals("9")){   
			
			String ced=request.getParameter("ced");
			String tipo=request.getParameter("tipo");
			String examen=request.getParameter("exa");
			String tipoexa="";
			String genero="";
			String edad="";
			String nombre="";
			String apellidos="";
			String codge="";
			String exam="";
			int edad1=0;
			
			bus=exa.Busedadygene(ced, tipo);
			pa=exa.Busedadygene(ced, tipo);
			
			
			try{
				if(pa.next()){
					nombre=pa.getString(3);
					apellidos=pa.getString(4);
				}
			
			if(bus.next()){
				genero=bus.getString(1);
				edad=bus.getString(2);
			}
			bus.getStatement().getConnection().close();
			
			ge=exa.Buscacodge(genero);
			
			if(ge.next()){
				codge=ge.getString(1);
			}
			 edad1= Integer.parseInt(edad);
			 ge.getStatement().getConnection().close();
             }catch(Exception ex){
				
			}
		     rt.print("<br>");
		     
		     String valor = examen;
		     StringTokenizer tk = new StringTokenizer(valor, "|"); // Cambia aquí el separador
		     try{
		    	 while(tk.hasMoreElements()){
		    		 exam=tk.nextToken();
		    		 rs=exa.Bustip(exam);
		    		 try {
		    			 if(rs.next()){
		    				 tipoexa=rs.getString(2);
		    			 }
		    			 rs.getStatement().getConnection().close();
		    		 } catch (SQLException e) {
		    			 // TODO Auto-generated catch block
		    			 e.printStackTrace();
		    		 }
		    		 try {
		    			 if(tipoexa.equals("1")){
		    				 ResultSet co=null;
		    				 ResultSet co2=null;
			
		    				 co=exa.Examenom(exam, tipo, ced, codge, edad1);
		    				 co2=exa.Examenom(exam, tipo, ced, codge, edad1);
			 
		    				 rt.println("<div class='scrollbox2'><table class='style6' width='100%' border='3'><tr><td></td></tr><tr align='center'>");
		    				 rt.println("<td></td>"); 
		    				 while(co.next()){
		    					 rt.print("<td bgcolor='#dbe5f1'><div class='style6' >"+co.getString(4)+"</div><div  class='style6'>"+co.getString(5)+"</div></td>"); 
		    				 }
		    				 rt.print("</tr>"); 
		    				 rt.println("<tr align='center'><td>"+exam+"</td>");                      
		    				 while(co2.next()){
		    					 rt.print("<td ><div >"+co2.getString(7)+"</div></td>");
		    				 }
		
		    				 co.getStatement().getConnection().close();
		    				 co2.getStatement().getConnection().close();
		    				 rt.println("</tr></table></div><br><br>");
		    			 }
		    		 }catch (SQLException e) {
		    			 e.printStackTrace();
		    		 }  
		    		 try {
		    			 if(tipoexa.equals("0")){  
		    				 ResultSet co1=null;
		    				 ResultSet co5=null;
		    				 co1=exa.ExamenTexto1(ced,tipo,exam);
		    				 co5=exa.ExamenTexto1(ced,tipo,exam);			    
			   
		    				 rt.println("<div class='scrollbox2'><table class='style6' width='100%' border='3'><tr align='center'>");	
		    				 rt.println("<td></td>"); 
		    				 while(co1.next()){
		    					 rt.print("<td bgcolor='#dbe5f1'><div align='center' class='style6'>"+co1.getString(1)+"</div><div align='center' class='style6'>"+co1.getString(2)+"</div></td>");
		    				 }
		    				 rt.print("</tr>");
		    				 rt.println("<tr align='center'><td>"+exam+"</td>");
		    				 co1.getStatement().getConnection().close();
		    				 while(co5.next()){
		    					 rt.print("<td >"+co5.getString(3)+"</td>");
		    				 }		    				 
		    				 co5.getStatement().getConnection().close();
		    				 rt.println("</tr></table></div><br> <br>");
		    			 }
		    		 }catch (SQLException e) {
		    			 e.printStackTrace();
		    		 }  
		    	 }
		    	 pa.getStatement().getConnection().close();
		    	 }catch (SQLException e) {
		    	 e.printStackTrace();
		     } 
		}
		
		if(z.equals("2")){
			String cedula=request.getParameter("ced");
			String tipo=request.getParameter("ti");
			String nombre1="";
			String edad="";
			String genero="";
			rs2=exa.Busedadygene(cedula, tipo);
			
			try {
				if(rs2.next()){
					edad=rs2.getString(2);
					genero=rs2.getString(1);
				}
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			int edad1=Integer.parseInt(edad);
			rs3=exa.Buscacodge(genero);
			String codge="";
			try {
				if(rs3.next()){
					codge=rs3.getString(1);
				}
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			rs=exa.ExamenTexto(cedula, tipo);
			rs1=exa.ExamenRango(cedula, tipo,edad1,codge);
			
			////nuevo///////////
			String nombre="";
			String apellidos="";
			
			ResultSet pa1=null;
			pa1=exa.Busedadygene(cedula, tipo);
			try {
				if(pa1.next()){
					nombre=pa1.getString(3);
					apellidos=pa1.getString(4);
				}
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			rt.print("<table class='style6' width='100%' >");
			rt.print("<tr ><td ><div align='center'>PACIENTE: <b class='style9'>"+nombre+" "+apellidos+"</b></div></td><td>EDAD: "+edad+" Anos </td></tr>");
			rt.print("</table>");
			rt.print("<br>");
			rt.print("<table  class='style6' width='100%' ><tr><td colspan='6' align='center' id='cabecera2' class='style11'><div  >EXAMENES REALIZADOS </div></td></tr></table>   <div   class='scrollbox1'><table  width='100%' class='style6'> <tr  id='cabecera2' class='style11' align='center'><td colspan='2' width='100px'><div >FECHA y HORA</div></td><td colspan='2' ><div >NOMBRE DE EXAMEN</div></td><td ><div  >RESULTADO</div></td><td ><div> RANGO</div></td></tr>");
			try {
				rt.print("<tr>");
				while(rs.next()){
					String ano=rs.getString(4).substring(0,4);
					String mes=rs.getString(4).substring(5,7);
					String dia=rs.getString(4).substring(8,10);
					String hora=rs.getString(5).substring(0,2);
					String minuto=rs.getString(5).substring(3,5);
					String segundo=rs.getString(5).substring(6,8);					
					rt.print("<tr><td colspan='2'><div align='left'width='100px'>"+rs.getString(4)+"/"+rs.getString(5)+"</div></td><td colspan='2'><div align='left' ><a  href='#'onclick='Abrir_ventana("+ano+","+mes+","+dia+","+hora+","+minuto+","+segundo+","+1+","+rs.getString(8)+","+rs.getString(9)+","+rs.getString(10)+")'>"+rs.getString(6)+"</a></div></td><td><div align=left' >"+rs.getString(7)+"</div></td></tr>");	
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			rt.println("<br><br>");			
			try {
				while(rs1.next()){
					String ano=rs1.getString(4).substring(0,4);
					String mes=rs1.getString(4).substring(5,7);
					String dia=rs1.getString(4).substring(8,10);
					String hora=rs1.getString(5).substring(0,2);
					String minuto=rs1.getString(5).substring(3,5);
					String segundo=rs1.getString(5).substring(6,8);
					rt.print("<tr><td colspan='2'><div align='left' width='100px' >"+rs1.getString(4)+"/"+rs1.getString(5)+"</div></td><td colspan='2'><div align='left' ><a  href='#'onclick='Abrir_ventana("+ano+","+mes+","+dia+","+hora+","+minuto+","+segundo+","+2+","+rs1.getString(10)+","+rs1.getString(11)+","+rs1.getString(12)+","+rs1.getString(8)+","+rs1.getString(9)+")'>"+rs1.getString(6)+"</a></div></td><td><div align='left' >"+rs1.getString(7)+"</div></td><td><div align='center' >"+rs1.getString(8)+"-"+rs1.getString(9)+"</div></td></tr>");	
				}
				rs1.getStatement().getConnection().close();
				rs.getStatement().getConnection().close();
				rs2.getStatement().getConnection().close();
				///////////nuevo//////////////////////
				ResultSet rsgrupo=null;
				ResultSet rsgrupo1=null;
				PrintWriter out=response.getWriter();
				
				rsgrupo=exa.Examen(cedula,tipo);
				MetodoResultado exa1=new MetodoResultado();				
				ResultSet redad=null;
				ResultSet rgene=null;
				redad=exa1.Buscaedad(tipo, cedula);
				String edad2="";
				String genero1="";
				String codgenero="";
				try {
					if(redad.next()){
						edad2=redad.getString(1);
						genero1=redad.getString(2);
					}
					
					rgene=exa.Buscacodge(genero);
					if(rgene.next()){
						codgenero=rgene.getString(1);
					}
					
				} catch (SQLException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
				try {
				
					while(rsgrupo.next()){
						
						
						String ano=rsgrupo.getString(4).substring(0,4);
						String mes=rsgrupo.getString(4).substring(5,7);
						String dia=rsgrupo.getString(4).substring(8,10);
						String hora=rsgrupo.getString(5).substring(0,2);
						String minuto=rsgrupo.getString(5).substring(3,5);
						String segundo=rsgrupo.getString(5).substring(6,8);
						//System.out.println("Entro codgeneroooo lab_BusPac.java "+codgenero);
						out.print("<tr><td colspan='2' width='100px'><div align='left' >"+rsgrupo.getString(4)+"/"+rsgrupo.getString(5)+"</div></td><td colspan='2'><div align='left' ><a  href='#'onclick='Abrir("+ano+","+mes+","+dia+","+hora+","+minuto+","+segundo+","+rsgrupo.getString(9)+","+rsgrupo.getString(10)+","+codgenero+")'>"+rsgrupo.getString(6)+"</a></div></td></tr>");	
					}
				
				 
					out.print("</table>");

					rsgrupo.getStatement().getConnection().close();
					redad.getStatement().getConnection().close();
					rgene.getStatement().getConnection().close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			rt.println("</table><div>");
			
		}
		if(z.equals("10")){
			String tipo,cedula;
			tipo=request.getParameter("tipo");
			cedula=request.getParameter("cedula");
			rs=exa.busexamenes_hechos(tipo,cedula);
			try {
				String cadena ="";
				cadena="[";
				while(rs.next()){
					cadena = cadena+"'"+rs.getString(1)+"'";
					cadena = cadena +",";
				}
				cadena = cadena+"]";
				rs.getStatement().getConnection().close();
				response.getWriter().write(cadena);
			} catch (Exception e) {
			}
		}
		
		if(z.equals("11")){
			String cedula=request.getParameter("ced");
			String tipo=request.getParameter("ti");
			String anio="";
			String mes="";
			String dia="";
			String edad="";
			String NombreCompleto="";
			
			rs=exa.TodosExamenes(cedula,tipo);	
			rs1=exa.TodosExamenes(cedula,tipo);
			rt.print("<table class='style6' width='100%' >");
			try {
				if(rs1.next()){
					NombreCompleto=rs1.getString(1)+" "+rs1.getString(2)+" "+rs1.getString(4);
					edad=rs1.getString(6);
				}
			rt.print("<tr ><td ><div align='center'>PACIENTE: <b class='style9'>"+NombreCompleto+"</b></div></td><td>EDAD: "+edad+" Anos </td></tr>");
			rt.print("</table>");
			rs1.getStatement().getConnection().close();
			rt.print("<table  class='style6' width='100%' ><tr><td colspan='6' align='center' id='cabecera2' class='style11'><div  >EXAMENES REALIZADOS </div></td></tr></table><table  width='100%' class='style6'> <tr  id='cabecera2' class='style11' align='center'><td width='100'><div >FECHA</div></td><td ><div >NOMBRE DE PACIENTE</div>  <div> </div></td></tr>");
				while(rs.next()){
					anio=rs.getString(3).substring(0,4);
					mes=rs.getString(3).substring(5,7);
					dia=rs.getString(3).substring(8,10);
					NombreCompleto=rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(4);
					rt.print("<tr><td><div align='left'width='100px'>"+rs.getString(3)+"</div></td><td><div align='left' ><a  href='#'onclick='Abrir("+anio+","+mes+","+dia+","+rs.getString(5)+")'>"+NombreCompleto+"</a></div></td></tr>");	
				}
				rt.print("</table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if(z.equals("13")){
			String cedula=request.getParameter("ced");
			String tipo=request.getParameter("ti");
			String anio="";
			String mes="";
			String dia="";
			String edad="";
			String NombreCompleto="";
			
			rs=exa.TodosExamenes(cedula,tipo);	
			rs1=exa.TodosExamenes(cedula,tipo);
			rt.print("<table class='style6' width='100%' >");
			try {
				if(rs1.next()){
					NombreCompleto=rs1.getString(1)+" "+rs1.getString(2)+" "+rs1.getString(4);
					edad=rs1.getString(6);
				}
			rt.print("<tr ><td ><div align='center'>PACIENTE: <b class='style9'>"+NombreCompleto+"</b></div></td><td>EDAD: "+edad+" Anos </td></tr>");
			rt.print("</table>");
			rs1.getStatement().getConnection().close();
			rt.print("<table  class='style6' width='100%' ><tr><td colspan='6' align='center' id='cabecera2' class='style11'><div  >EXAMENES REALIZADOS </div></td></tr></table><table  width='100%' class='style6'> <tr  id='cabecera2' class='style11' align='center'><td width='100'><div >FECHA</div></td><td ><div >NOMBRE DE PACIENTE</div>  <div> </div></td></tr>");
				while(rs.next()){
					anio=rs.getString(3).substring(0,4);
					mes=rs.getString(3).substring(5,7);
					dia=rs.getString(3).substring(8,10);
					NombreCompleto=rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(4);
					rt.print("<tr><td><div align='left'width='100px'>"+rs.getString(3)+"</div></td><td><div align='left' ><a  href='#'onclick='Abrir("+anio+","+mes+","+dia+","+rs.getString(5)+")'>"+NombreCompleto+"</a></div></td></tr>");	
				}
				rt.print("</table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
