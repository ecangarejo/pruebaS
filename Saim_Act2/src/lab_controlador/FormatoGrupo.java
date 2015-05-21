/**
 * controlador: FormatoGrupo se encuentra el proceso para  
 * la consulta de los examenes por los pacientes.
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
 * Servlet implementation class FormatoGrupo
 */
public class FormatoGrupo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		ResultSet rs=null;
		PrintWriter out=response.getWriter();		
		String tipo=request.getParameter("tipo");
		String cedula=request.getParameter("cedula");
		String subarea=request.getParameter("subarea");
		String area=request.getParameter("area");
		MetodoResultado exa=new MetodoResultado();
		rs=exa.ResulGrupo(tipo,cedula,subarea);		
		ResultSet redad=null;
		ResultSet rgene=null;
		redad=exa.Buscaedad(tipo, cedula);
		String edad="";
		String genero="";
		String codgenero="";
		try {
			if(redad.next()){
				edad=redad.getString(1);
				genero=redad.getString(2);
			}
			redad.getStatement().getConnection().close();	
			MetodoExamen ge=new MetodoExamen();
			rgene=ge.Buscacodge(genero);
			if(rgene.next()){
				codgenero=rgene.getString(1);
			}
			rgene.getStatement().getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		out.print("<table width='500' border='1' ><tr><td colspan='2'aling='center'><div align='center'>EXAMENES REALIZADOS</div></td></tr><tr><td>NOMBRE DE EXAMEN</td><td>FECHA</td></tr>");
		try {
		
			while(rs.next()){
				
				String ano=rs.getString(1).substring(0,4);
				String mes=rs.getString(1).substring(5,7);
				String dia=rs.getString(1).substring(8,10);				
				
				String hora=rs.getString(2).substring(0,2);
				String minuto=rs.getString(2).substring(3,5);
				String segundo=rs.getString(2).substring(6,8);
				
				out.print("<tr><td><a  href='#'onclick='Abrir("+ano+","+mes+","+dia+","+hora+","+minuto+","+segundo+","+rs.getString(6)+","+rs.getString(7)+","+edad+","+codgenero+")'>"+rs.getString(3)+"</a></td><td>"+rs.getString(1)+"/"+rs.getString(2)+"</td></tr>");	
			}
			out.print("</table>");
			rs.getStatement().getConnection().close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
	}

}
