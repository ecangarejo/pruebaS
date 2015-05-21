/**
 * controlador: ControlFormato1 se encuentra el proceso para  
 * generar la plantilla de los examenes para su posterior insercion
*/
package lab_controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lab_logica.MetodoExamen;
import lab_logica.MetodoResultado;

/**
 * Servlet implementation class ControlFormato1
 */
public class ControlFormato1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombre=request.getParameter("examen");
		response.setContentType("text/html;charset=UTF-8");
		String edad=request.getParameter("edad");
		String sexo=request.getParameter("sexo");
		MetodoExamen exa=new MetodoExamen();
		MetodoResultado re=new MetodoResultado();
		ResultSet tr=null;

		tr=exa.Buscacodge(sexo);
		String codge="";
		try {
			if(tr.next()){
				codge=tr.getString(1);
			}
			tr.getStatement().getConnection().close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ResultSet rsg=null;
		 rsg=exa.BuscaSexo(nombre,codge);		
		ResultSet rs1=null;
		ResultSet rs=null;
		ResultSet rsgene=null;	
		ResultSet rsedad=null;
	    rs1=exa.ExameGrupo(nombre);
	    rs=exa.ExameGrup(nombre);
	    rsg=exa.BuscaSexo(nombre,codge);
	    rsgene=exa.ExameGrupoRango(nombre);
	    rsg=exa.BuscaSexo(nombre,codge);
        PrintWriter out=response.getWriter();
       
       out.print("<table  border='2' align='center' class='style6'>");
       out.print("<tr bgcolor='#0033FF'><td colspan='14' id='cabecera2' ><div align='center'class='style11'><strong>"+nombre+" </strong></div></td>");
       out.print("</tr><tr class='style6' align='center'><td>EXAMEN</td><td >OBSERVACION</td><td>UNIDAD</td><td>RANGO</td></tr>");
       try {
		int y = 0;
				String CodigoSubarea="";
				String yu="";
				String edadinicial="";
				String edadfinal="";
				int edadini = 0;
				int edadfi = 0;				
				while(rs.next()){
					CodigoSubarea=rs.getString(5);
					int edad1=Integer.parseInt(edad);
					edadini=Integer.parseInt(rs.getString(3));
					edadfi=Integer.parseInt(rs.getString(4));
					 if((edad1>edadini)&&(edad1<edadfi)){
							 edadinicial= String.valueOf(edadini);
							 edadfinal= String.valueOf(edadfi);
							 } 
				}
				 rsedad=exa.ExameGrupoEdad(nombre, edadinicial, edadfinal);
				while((rs1.next())){
					CodigoSubarea=rs1.getString(5);
					yu=rs1.getString(2);
					if(yu.equals("0")){
						out.print("<tr class='style6' align='center'><td>"+rs1.getString(1)+"</td>");
						
					out.print("<td><textarea name='exagrupo'></textarea></td></tr>");
					out.print("<input name='codtipoexa' type='hidden' id='codtipoexa'  value="+rs1.getString(2)+">");
					out.print("<input name='codexa' type='hidden' id='codexa'  value="+rs1.getString(4)+">");
					y=y+1;	
					}
					}			
								
								 while(rsedad.next()){
								    out.print("<tr><td>"+rsedad.getString(1)+"</td>");
								    out.print("<td><input name='exagrupo' type='text' id='exagrupo' /></td>");
								    out.print("<td>"+rsedad.getString(6)+"</td><td>"+rsedad.getString(5)+"--"+rsedad.getString(7)+"</td>");
								    out.print("<input name='codtipoexa' type='hidden' id='codtipoexa'  value="+rsedad.getString(2)+">");
								    out.print("<input name='codexa' type='hidden' id='codexa'  value="+rsedad.getString(4)+">");
									
										y=y+1;
								}
								 while(rsg.next()){
									 out.print("<tr><td>"+rsg.getString(1)+"</td>");
									    out.print("<td><input name='exagrupo' type='text' id='exagrupo' /></td>");
									    out.print("<td>"+rsg.getString(6)+"</td><td>"+rsg.getString(5)+"--"+rsg.getString(7)+"</td>");
									    out.print("<input name='codtipoexa' type='hidden' id='codtipoexa'  value="+rsg.getString(2)+">");
									    out.print("<input name='codexa' type='hidden' id='codexa'  value="+rsg.getString(4)+">");
										
											y=y+1;
								 }
								 while(rsgene.next()){
									 out.print("<tr><td>"+rsgene.getString(1)+"</td>");
									    out.print("<td><input name='exagrupo' type='text' id='exagrupo' /></td>");
									    out.print("<td>"+rsgene.getString(6)+"</td><td>"+rsgene.getString(5)+"--"+rsgene.getString(7)+"</td>");
									    out.print("<input name='codtipoexa' type='hidden' id='codtipoexa'  value="+rsgene.getString(2)+">");
									    out.print("<input name='codexa' type='hidden' id='codexa'  value="+rsgene.getString(4)+">");
										
											y=y+1;
								 }
							  
									out.print("</tr>");
						
			out.print("<tr><td align='center' colspan='4'><input name='CodigoSubarea' type='hidden' id='CodigoSubarea' value="+CodigoSubarea+"><input name='exa' type='button' class='boton4' id='exa' value='Guardar'  onclick='insertar_grupo2("+y+");'/></td></tr></table>");
			 rs1.getStatement().getConnection().close();
			 rs.getStatement().getConnection().close();
			 rsgene.getStatement().getConnection().close();
			 rsg.getStatement().getConnection().close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

}
