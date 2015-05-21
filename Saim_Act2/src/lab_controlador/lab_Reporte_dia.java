/**
 * controlador: lab_Reporte_dia se encuentra el proceso para  
 * buscar examenes realizados en los ultimos dos dias de un paciente.
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
import lab_logica.MetodoExamen;
import lab_logica.MetodoResultado;

/**
 * Servlet implementation class lab_BusPac
 */
public class lab_Reporte_dia extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String z=request.getParameter("z");
		PrintWriter rt=response.getWriter();
		MetodoExamen exa=new MetodoExamen();
		MetodoResultado resu=new MetodoResultado();
		ResultSet rsgrupo=null;
		ResultSet redad=null;
		ResultSet rgene=null;
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		ResultSet rs3=null;
		ResultSet pa1=null;
		
		if(z.equals("2")){
			String cedula=request.getParameter("ced");
			String tipo=request.getParameter("ti");
			//String nombre1="";			
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
			
			rs=exa.Fecha_Texto(cedula, tipo);
			rs1=exa.Fecha_Rango(cedula, tipo,edad1,codge);
			
			////nuevo///////////
			String nombre="";
			String apellidos="";
			
			
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
			 rt.print("<table width='100%' border='0' style='border:1px solid #0066FF; color:#000099;' >");
		     rt.print("<tr class='style6'><td ><div align='center' >PACIENTE: <b class='style9'>"+nombre+" "+apellidos+"</b></div></td><td>EDAD: "+edad+" Anos </td></tr>");
		     rt.print("</table>");
		     rt.print("<br>");
		     rt.print("<div align='center'><a  href='#' onclick=todos_reporte("+cedula+",'"+tipo+"')>Ver Todos</a></div>"); 
		     rt.print("<table  class='style6' width='100%' ><tr><td colspan='6' align='center' id='cabecera2' class='style11'><div  >EXAMENES REALIZADOS </div></td></tr></table>   <div   class='scrollbox1'><table  width='100%' class='style6'> <tr  id='cabecera2' class='style11' align='center'><td colspan='2' width='100px'><div align='center'>FECHA y HORA</div></td><td colspan='2' ><div >NOMBRE DE EXAMEN</div></td><td ><div  >RESULTADO</div></td><td ><div> RANGO</div></td></tr>");
			try {
				rt.print("<tr>");
				while(rs.next()){
					String ano=rs.getString(4).substring(0,4); 
					String mes=rs.getString(4).substring(5,7);
					String dia=rs.getString(4).substring(8,10);
					String hora=rs.getString(5).substring(0,2);
					String minuto=rs.getString(5).substring(3,5);
					String segundo=rs.getString(5).substring(6,8);
					rt.print("<tr><td colspan='2'><div align='center' >"+rs.getString(4)+"/"+rs.getString(5)+"</div></td><td colspan='2'><div align='center' ><a  href='#'onclick='Abrir_ventana("+ano+","+mes+","+dia+","+hora+","+minuto+","+segundo+","+1+","+rs.getString(8)+","+rs.getString(9)+","+rs.getString(10)+")'>"+rs.getString(6)+"</a></div></td><td><div align='center' >"+rs.getString(7)+"</div></td></tr>");	
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
					rt.print("<tr><td colspan='2'><div align='center' >"+rs1.getString(4)+"/"+rs1.getString(5)+"</div></td><td colspan='2'><div align='center' ><a  href='#'onclick='Abrir_ventana("+ano+","+mes+","+dia+","+hora+","+minuto+","+segundo+","+2+","+rs1.getString(10)+","+rs1.getString(11)+","+rs1.getString(12)+","+rs1.getString(8)+","+rs1.getString(9)+")'>"+rs1.getString(6)+"</a></div></td><td><div align='center' >"+rs1.getString(7)+"</div></td><td><div align='center' >"+rs1.getString(8)+"-"+rs1.getString(9)+"</div></td></tr>");	
				}
				rs1.getStatement().getConnection().close();
				rs.getStatement().getConnection().close();
				rs2.getStatement().getConnection().close();
				///////////nuevo//////////////////////				
				
				PrintWriter out=response.getWriter();
				rsgrupo=exa.Fecha_anterior(cedula,tipo);
				
				redad=resu.Buscaedad(tipo, cedula);
				//String edad2="";
				//String genero1="";
				String codgenero="";
				try {
					if(redad.next()){
						redad.getString(1);
						redad.getString(2);
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
						//System.out.println("Entro codgeneroooo lab_Reporte_Dia.java "+codgenero);
						out.print("<tr><td colspan='2'><div align='center' >"+rsgrupo.getString(4)+"/"+rsgrupo.getString(5)+"</div></td><td colspan='2'><div align='center' ><a  href='#'onclick='Abrir("+ano+","+mes+","+dia+","+hora+","+minuto+","+segundo+","+rsgrupo.getString(9)+","+rsgrupo.getString(10)+","+codgenero+")'>"+rsgrupo.getString(6)+"</a></div></td></tr>");	
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
			rt.println("</table>");
		}
	}
}
