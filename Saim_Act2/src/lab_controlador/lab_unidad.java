/**
 * controlador: lab_unidad se encuentra el proceso para  
 * llenar las tablas emergentesde realizar examenes.
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

import lab_logica.MetodoResultado;

/**
 * Servlet implementation class lab_unidad
 */
public class lab_unidad extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
	    String exa=request.getParameter("examen");
	    String ced=request.getParameter("ced");
	    String docu=request.getParameter("docu");
	    String valor=request.getParameter("valor");
	    String area=request.getParameter("area");
	    String gene=request.getParameter("gene");
	    String edad=request.getParameter("edad");
	    MetodoResultado re=new MetodoResultado();
	    ResultSet rs=null;
	    ResultSet rs1=null;
	    ResultSet rs2=null;
	    rs1=re.BuscarTipo(exa,area);
	    if(valor.equals("1")||valor.equals("3")){
	    	try {
				if(rs1.next()){
					 String tipo=rs1.getString(1);
					 if(tipo.equals("GENERO")){
						 
					 rs=re.Resultados(rs1.getString(2),exa,area,gene);
					 }
					 if(tipo.equals("GENERAL")){
						 rs=re.Resultados1(rs1.getString(2),exa,area); 
					 }
					 if(tipo.equals("EDAD")){
						 
						 rs2=re.BuscEdad(exa);
						int edad1= Integer.parseInt(edad);
						
						 
						 while(rs2.next()){
							 int edadini=Integer.parseInt(rs2.getString(1));
								int edadfi=Integer.parseInt(rs2.getString(2));
							 if((edad1>=edadini)&&(edad1<=edadfi)){
								 String edadinicial= String.valueOf(edadini);
								 String edadfinal= String.valueOf(edadfi);
								 rs=re.Resulatdo3(rs1.getString(2),exa, area, edadinicial, edadfinal);
							 }
						 }
						 rs2.getStatement().getConnection().close();
					 }
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    PrintWriter out=response.getWriter();
		
		out.print("<br>");		 
		try {
			
			while(rs.next()){
				out.print(" <table width='434' border='0'><tr>");
				out.print("<td>UNIDAD</td>");
			out.print("<td><input name='uni' type='text' id='uni' value='"+rs.getString(3)+"' readonly='' ></td><td>RANGO</td><td><input name='ran' type='text' id='ran' value='"+rs.getString(1)+"-"+rs.getString(2)+ "'readonly='' ></td></tr><td>OBSERVACION</td><td><input name='comen' type='text' id='comen' value='"+rs.getString(4)+"'readonly='' ></td><td>GENERO</td><td><input name='gene' type='text' id='gene' value='"+rs.getString(5)+"'readonly='' ></td></tr></table>");
			}
			rs.getStatement().getConnection().close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    
	    
	}

}
