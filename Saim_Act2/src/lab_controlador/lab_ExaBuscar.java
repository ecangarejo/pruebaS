/**
 * controlador: lab_ExaBuscar se encuentra el proceso para  
 * buscar un examen individual si no se acuerda en que area fue creado.
*/
package lab_controlador;

import hic_metodo.MetodoLaboratoriosHistoria;

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

/**
 * Servlet implementation class lab_ExaBuscar
 */
public class lab_ExaBuscar extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String z=req.getParameter("z");
		String op=req.getParameter("op");
		String Nombre=req.getParameter("Nombre");
		String PrimerApellido=req.getParameter("PrimerApellido");
		String SegundoApellido=req.getParameter("SegundoApellido");
		String CodPac=req.getParameter("CodPac");
		MetodoExamen exa=new MetodoExamen ();
        MetodolabPa mp = new MetodolabPa();
        MetodoLaboratoriosHistoria mlh=new MetodoLaboratoriosHistoria();
        ResultSet rs=null;
        ResultSet rs1=null;
        ResultSet rs2=null;
        ResultSet rs3=null;
        ResultSet rs4=null;
		PrintWriter rt=response.getWriter();
		if(z.equals("1")){
			rt.print("<table><tr><td>");
			rt.print("<input type='text' name='nombre' id='nombre' onkeyup='autocompleta_nombre()' value='' size='50' /></td><td><input name='examenlista' class='boton4' type='button' id='examenlista' value='Realizar' onclick='reforma();' /></td></tr>");
			rt.print("<tr><td><div id='sugerencias'  ></div></td></tr>");
			rt.print("</table>");
		}
		if(z.equals("2")){ 
	           String nombre1 =req.getParameter("nombre");
               
               try {
                   rs = mp.listar(nombre1);
                   String cadena ="";
                   cadena="[";
                   while(rs.next()){                   	
                   	cadena = cadena+"'"+rs.getString(1)+"'";
                   	cadena = cadena +",";	
                   }
                   rs.getStatement().getConnection().close();
                   cadena = cadena+"]";               
                   response.getWriter().write(cadena);
               } catch (SQLException e) {
                   e.getMessage();
               } catch (Exception e) {
					e.printStackTrace();
				}
		}
		if(z.equals("3")){
			  String examen;
				examen=req.getParameter("nombre");
				rs=exa.BuscarExamen(examen);
				PrintWriter re=response.getWriter();
				try {
					if(rs.next()){
					re.print(rs.getString(1)+"/"+rs.getString(2));
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		if(z.equals("4")){
			/**
			 * procedimiento para llenar las coincidencias de la busqueda
			 *  de un paciente ya sea por su nombre,primer apellido o segundo apellido.
			 * */
			
			if(op.equals("1")){				
				rs=exa.ConsultaNombrePapellidoSapellido(Nombre, PrimerApellido, SegundoApellido);
				try {
					rt.print("<table width='100%' border='1' class='style6'><tr><td colspan='3'><div class='style11' id='cabecera2' align='center'>Resultados De La Busqueda </div></td></tr>");
					while(rs.next()){
						rt.print("<tr><td width='25%'>"+rs.getString(1)+"-"+rs.getString(2)+"</td><td width='34%'><a  href='#'onclick='VerLaboratoriosPaciente("+rs.getString(7)+")'>"+rs.getString(5)+" "+rs.getString(4)+" "+rs.getString(3)+"</a></td><td width='41%'>"+rs.getString(6)+"</td></tr>");
					}
					rt.print("</table>");
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(op.equals("2")){
				rs=exa.ConsultaNombrePapellido(Nombre, PrimerApellido);
				try {
					rt.print("<table width='100%' border='1' class='style6'><tr><td colspan='3'><div class='style11' id='cabecera2' align='center'>Resultados De La Busqueda </div></td></tr>");
					while(rs.next()){
						rt.print("<tr><td width='25%'>"+rs.getString(1)+"-"+rs.getString(2)+"</td><td width='34%'><a  href='#'onclick='VerLaboratoriosPaciente("+rs.getString(7)+")'>"+rs.getString(5)+" "+rs.getString(4)+" "+rs.getString(3)+"</a></td><td width='41%'>"+rs.getString(6)+"</td></tr>");
					}
					rt.print("</table>");
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(op.equals("3")){
				rs=exa.ConsultaNombreSapellido(Nombre, SegundoApellido);
				try {
					rt.print("<table width='100%' border='1' class='style6'><tr><td colspan='3'><div class='style11' id='cabecera2' align='center'>Resultados De La Busqueda </div></td></tr>");
					while(rs.next()){
						rt.print("<tr><td width='25%'>"+rs.getString(1)+"-"+rs.getString(2)+"</td><td width='34%'><a  href='#'onclick='VerLaboratoriosPaciente("+rs.getString(7)+")'>"+rs.getString(5)+" "+rs.getString(4)+" "+rs.getString(3)+"</a></td><td width='41%'>"+rs.getString(6)+"</td></tr>");
					}
					rt.print("</table>");
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(op.equals("4")){
				rs=exa.ConsultaPapellidoSapellido(PrimerApellido, SegundoApellido);
				try {
					rt.print("<table width='100%' border='1' class='style6'><tr><td colspan='3'><div class='style11' id='cabecera2' align='center'>Resultados De La Busqueda </div></td></tr>");
					while(rs.next()){
						rt.print("<tr><td width='25%'>"+rs.getString(1)+"-"+rs.getString(2)+"</td><td width='34%'><a  href='#'onclick='VerLaboratoriosPaciente("+rs.getString(7)+")'>"+rs.getString(5)+" "+rs.getString(4)+" "+rs.getString(3)+"</a></td><td width='41%'>"+rs.getString(6)+"</td></tr>");
					}
					rt.print("</table>");
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(op.equals("5")){
				rs=exa.ConsultaNombre(Nombre);
				try {
					rt.print("<table width='100%' border='1' class='style6'><tr><td colspan='3'><div class='style11' id='cabecera2' align='center'>Resultados De La Busqueda </div></td></tr>");
					while(rs.next()){
						rt.print("<tr><td width='25%'>"+rs.getString(1)+"-"+rs.getString(2)+"</td><td width='34%'><a  href='#'onclick='VerLaboratoriosPaciente("+rs.getString(7)+")'>"+rs.getString(5)+" "+rs.getString(4)+" "+rs.getString(3)+"</a></td><td width='41%'>"+rs.getString(6)+"</td></tr>");
					}
					rt.print("</table>");
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(op.equals("6")){
				rs=exa.ConsultaPapellido(PrimerApellido);
				try {
					rt.print("<table width='100%' border='1' class='style6'><tr><td colspan='3'><div class='style11' id='cabecera2' align='center'>Resultados De La Busqueda </div></td></tr>");
					while(rs.next()){
						rt.print("<tr><td width='25%'>"+rs.getString(1)+"-"+rs.getString(2)+"</td><td width='34%'><a  href='#'onclick='VerLaboratoriosPaciente("+rs.getString(7)+")'>"+rs.getString(5)+" "+rs.getString(4)+" "+rs.getString(3)+"</a></td><td width='41%'>"+rs.getString(6)+"</td></tr>");
					}
					rt.print("</table>");
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(op.equals("7")){
				rs=exa.ConsultaSapellido(SegundoApellido);
				try {
					rt.print("<table width='100%' border='1' class='style6'><tr><td colspan='3'><div class='style11' id='cabecera2' align='center'>Resultados De La Busqueda </div></td></tr>");
					while(rs.next()){
						rt.print("<tr><td width='25%'>"+rs.getString(1)+"-"+rs.getString(2)+"</td><td width='34%'><a  href='#'onclick='VerLaboratoriosPaciente("+rs.getString(7)+")'>"+rs.getString(5)+" "+rs.getString(4)+" "+rs.getString(3)+"</a></td><td width='41%'>"+rs.getString(6)+"</td></tr>");
					}
					rt.print("</table>");
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		if(z.equals("5")){
			/**
			 * para llenar los examanes k se ha hecho el paciente en el formato
			 * que salga enseguida se carge la historia clinica
			 */
			rs4=exa.DatosPaciente(CodPac);
			try {
				if(rs4.next()){
					rt.print("<table width='100%' border='1' class='style6'><tr><td colspan='4'><div class='style11' id='cabecera2' align='center' >Datos Del Registro</div></td></tr>");
					rt.print("<tr><td width='10%'><div>Documento</div></td><td width='27%'><div>"+rs4.getString(1)+"-"+rs4.getString(2)+"</div></td><td width='16%'><div>Nombres y Apellidos </div></td><td width='47%'><div>"+rs4.getString(3)+" "+rs4.getString(4)+" "+rs4.getString(5)+"</div></td></tr></table>");
				}
				rs4.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String edad="";
			String genero="";
			rs2=mlh.Busedadygene(CodPac);
			  try {
				if(rs2.next()){
					  edad=rs2.getString(2);
					  genero=rs2.getString(1);
				  }
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			String edad1=edad;
			rs3=mlh.Buscacodge(genero);
			String codge="";
			try {
				if(rs3.next()){
					codge=rs3.getString(1);
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			rs=mlh.ExamenTexto(CodPac);
			rs1=mlh.ExamenRango(CodPac,codge);
			String nombre="";
			String apellidos="";
			ResultSet pa1=null;
			pa1=mlh.Busedadygene(CodPac);
			try {
				if(pa1.next()){
					nombre=pa1.getString(3);
					apellidos=pa1.getString(4);
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			
			 rt.print("<table width='100%' border='1' class='style6'><tr id='cabecera2' align='rigth' class='style11'><td >Fecha y Hora </td><td >Nombre Del Estudio </td></tr>");
			try {
				rt.print("<tr>");
				while(rs.next()){
					String ano=rs.getString(4).substring(0,4);
					String mes=rs.getString(4).substring(5,7);
					String dia=rs.getString(4).substring(8,10); 
					String hora=rs.getString(5).substring(0,2);
					String minuto=rs.getString(5).substring(3,5);
					String segundo=rs.getString(5).substring(6,8);
					rt.print("<tr><td align='left' width='20%'><div>"+rs.getString(4)+"/"+rs.getString(5)+"</div></td><td width='80%' align='left'><div><a  href='#'onclick='Abrir_ventana("+ano+","+mes+","+dia+","+hora+","+minuto+","+segundo+","+1+","+rs.getString(8)+","+rs.getString(9)+","+rs.getString(10)+")'>"+rs.getString(6)+"</a></div></td></tr>");	
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				while(rs1.next()){
					String ano=rs1.getString(4).substring(0,4);
					String mes=rs1.getString(4).substring(5,7);
					String dia=rs1.getString(4).substring(8,10);
					String hora=rs1.getString(5).substring(0,2);
					String minuto=rs1.getString(5).substring(3,5);
					String segundo=rs1.getString(5).substring(6,8);
					rt.print("<tr><td align='left' width='20%'><div>"+rs1.getString(4)+"/"+rs1.getString(5)+"</div></td><td width='80%' align='left'><div><a  href='#'onclick='Abrir_ventana("+ano+","+mes+","+dia+","+hora+","+minuto+","+segundo+","+2+","+rs1.getString(10)+","+rs1.getString(11)+","+rs1.getString(12)+","+rs1.getString(8)+","+rs1.getString(9)+")'>"+rs1.getString(6)+"</a></div></td></tr>");	
				}
				rs.getStatement().getConnection().close();
				rs1.getStatement().getConnection().close();			
				rs2.getStatement().getConnection().close();
				ResultSet rsgrupo=null;
				rsgrupo=mlh.Examen(CodPac);
				ResultSet redad=null;
				ResultSet rgene=null;
				redad=mlh.Buscaedad(CodPac);
				String edad2="";
				String genero1="";
				String codgenero="";
				try {
					if(redad.next()){
						edad2=redad.getString(1);
						genero1=redad.getString(2);
					}
					rgene=mlh.Buscacodge(genero);
					if(rgene.next()){
						codgenero=rgene.getString(1);
					}
				} catch (SQLException ex) {
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
						rt.print("<tr><td align='left' width='20%'><div>"+rsgrupo.getString(4)+"/"+rsgrupo.getString(5)+"</div></td><td width='80%' align='left'><div><a  href='#'onclick='Abrir("+ano+","+mes+","+dia+","+hora+","+minuto+","+segundo+","+rsgrupo.getString(9)+","+rsgrupo.getString(10)+","+codgenero+")'>"+rsgrupo.getString(6)+"</a></div></td></tr>");	
					}
					rt.print("</table>");
					rsgrupo.getStatement().getConnection().close();
					redad.getStatement().getConnection().close();
					rgene.getStatement().getConnection().close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

}
