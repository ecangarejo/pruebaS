/**
 * controlador: INSERTAR_GRUPO se encuentra el proceso para  
 * la insercion de los datos de los resultados de los examenes en grupo.
*/
package lab_controlador;

import hic_metodo.MetodoVerFormatos;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lab_logica.MetodoResultado;
import lab_logica.MetodolabPa;

/**
 * Servlet implementation class INSERTAR_GRUPO
 */
public class INSERTAR_GRUPO extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		ResultSet rs=null;ResultSet rs1=null;
		MetodoResultado resu= new MetodoResultado();
		String CodigoSubarea=request.getParameter("CodigoSubarea");
		String resultado=request.getParameter("resultado");
		String hora=request.getParameter("hora");
		String codigo=request.getParameter("cod");
		String fecha=request.getParameter("fecha");
		String exa=request.getParameter("exa");
		//String tipo=request.getParameter("tipo");
		String usu=request.getParameter("usu");
		String cedula=request.getParameter("cedula");
		String cont=request.getParameter("cont");
		String CodAsignacion=request.getParameter("CodAsignacion");
		String TipoEstudio="-1";
		String estado="-1";
		PrintWriter out=response.getWriter();
		MetodoVerFormatos mvf = new MetodoVerFormatos();		
		MetodolabPa pa=new MetodolabPa();
		ResultSet usuario=null;
		String CodigoOrd="";
		String TipoEstudioOrd="";
		
		
		//System.out.println("CodigoSubarea="+CodigoSubarea+"&resultado="+Resul+"&cod="+cod+"&fecha="+fecha+"&hora="+hora+"&exa="+codexa+"&tipo="+codtipoexa+"&usu="+usu+"&cedula="+cedula+"&cont="+cont)
		//System.out.println("CodigoSubarea="+CodigoSubarea+"&resultado="+Resul+"&cod="+codigo+"&fecha="+fecha+"&hora="+hora+"&exa="+codexa+"&tipo="+codtipoexa+"&usu="+usu+"&cedula="+cedula+"&cont="+cont);
		if(cont.equals("1")){
			String NombreUsuario="NO ASIGNADO";
			String TipoPyP="NO APLICA";
			resu.RelacionPacienteLaboratorios(CodigoSubarea, TipoEstudio, codigo, hora, fecha,estado,NombreUsuario,TipoPyP);
			try {
				rs1=resu.BuscarIgual(hora,fecha);
				if(rs1.next()){
					CodigoOrd=rs1.getString(1);
					TipoEstudioOrd=rs1.getString(2);
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}
		try {
			rs=resu.BuscarIgual(hora,fecha);
			if(rs.next()){
				CodigoOrd=rs.getString(1);
				TipoEstudioOrd=rs.getString(2);
			}
			rs.getStatement().getConnection().close();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
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
		try {
			int p;
			for(p=0;p<resultado.length();p++){
				resultado=resultado.replace('@','+');
		        }
			if((CodAsignacion!=null)){
			resu.insertar(codigo, exa, fecha, hora,usu,resultado,cedula,CodAsignacion);
			mvf.EstadoDeAsignacion(CodAsignacion);
			}
			if(TipoEstudioOrd.equals("-1")){
				resu.insertar(codigo, exa, fecha, hora,usu,resultado,cedula,CodigoOrd);
			}
			else{
				if(CodAsignacion==null){
					try {
						rs=resu.BuscarIgual(hora,fecha);
						if(rs.next()){
							CodigoOrd=rs.getString(1);
							TipoEstudioOrd=rs.getString(2);
						}
						rs.getStatement().getConnection().close();
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					resu.insertar(codigo, exa, fecha, hora,usu,resultado,cedula,CodigoOrd);
				}
			}
		} catch (Exception e) {
			out.print("Error al insertar verifique sus datos"+e);
		}
	}
}