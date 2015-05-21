/**
 * controlador: IngExamen se encuentra el proceso para  
 * la insercion de los datos de los resultados de los examenes.
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
import lab_logica.MetodolabPa;
import lab_logica.MetodoResultado;

/**
 * Servlet implementation class IngExamen
 */
public class IngExamen extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String exa=request.getParameter("examen");
		String ced=request.getParameter("ced");
		String tipo=request.getParameter("tipo");
		String des=request.getParameter("des");
		String fecha=request.getParameter("fecha");
		String hora=request.getParameter("hora");
		String valor=request.getParameter("valor");
		String usu=request.getParameter("usu");
		String  z=request.getParameter("z");
		String  cedula=request.getParameter("cedula");
		String TipoEstudio="-1";
		String estado="-1";
		String CodigoOrd="";
		String TipoEstudioOrd="";
		MetodoExamen me=new MetodoExamen();
		MetodolabPa pa=new MetodolabPa();
		MetodoResultado re=new MetodoResultado();
		PrintWriter out=response.getWriter();
		
		ResultSet usuario=null;
		
		usuario=pa.buscarUsu(usu);
		
		try {
			if(usuario.next()){
				usu=usuario.getString(1)+" "+usuario.getString(2);
			}
			usuario.getStatement().getConnection().close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		ResultSet rs3=null;
		
		rs1=pa.SQLlabPac(ced, tipo);
		String codexamen="";
		String codpac="";
		
		
		if(z.equals("0")){
			if(valor.equals("1")||valor.equals("3")){				
				rs=me.CodExamenIndividual(exa);
			}
			if(valor.equals("2")){
				rs=me.CodExamenGrupo(exa);
			}
			
			try {
			if(rs.next()){
				codexamen=rs.getString(1);
				}			
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		}
		
/////////////////////////////////////////////////////		
		
		if(z.equals("1")){
			if(valor.equals("1")||valor.equals("3")){
				rs3=me.CodExamenInd(exa);
			}
			if(valor.equals("2")){
				rs3=me.CodExamenGrup(exa);
			}
			try {
				if(rs3.next()){
					codexamen=rs3.getString(1);
				}
				rs3.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		try {
			if(rs1.next()){
				codpac=rs1.getString(3);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			
				int i;
				for(i=0;i<des.length();i++){
					  des=des.replace('@','+');
			          
			        }
				//if(CodigoSubarea!=""){
					try {
						rs=re.BuscarIgual(hora,fecha);
						if(rs.next()){
							//System.out.print("No Ingreso");
						}
						else{
							String NombreUsuario="NO ASIGNADO";
							String TipoPyP="No Aplica";
							re.RelacionPacienteLaboratorios(codexamen, TipoEstudio, codpac, hora, fecha,estado,NombreUsuario,TipoPyP);
							rs2=re.BuscarIgual(hora,fecha);
							if(rs2.next()){
								CodigoOrd=rs2.getString(1);
								TipoEstudioOrd=rs2.getString(2);
							}
						}
						rs2.getStatement().getConnection().close();
						rs.getStatement().getConnection().close();
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				//}
					if(TipoEstudioOrd!="-1"){
						re.insertar(codpac, codexamen, fecha, hora,usu,des,ced,CodigoOrd);
						//mvf.EstadoDeAsignacion(CodAsignacion);
					}
					else{
						CodigoOrd="0";
						re.insertar(codpac, codexamen, fecha, hora,usu,des,ced,CodigoOrd);
						//mvf.EstadoDeAsignacion(CodAsignacion);
					}
				//re.insertar(codpac, codexamen, fecha, hora,usu,des,ced);
				out.print("Inserccion Exitosa");
		
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			out.print("Error al insertar verifique sus datos");
		}
		try {
			
			rs1.getStatement().getConnection().close();
			//rs2.getStatement().getConnection().close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
