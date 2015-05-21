/**
 * controlador: ControlPacientesLaboratorios se encuentra la forma en que se 
 * llenan las plantillas de los laboratorios que se le asignaron al paciente
 * para la posterior insercion de los resultados.
 *  
*/
package hic_controlador;


import hic_metodo.MetodoPacienteLaboratorio;
import hic_metodo.MetodoVerFormatos;

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

public class ControlPacientesLaboratorios  extends HttpServlet  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out=res.getWriter();
		String va=req.getParameter("valor");
		ResultSet rs=null;
		ResultSet rs1=null;
		MetodoExamen exa=new MetodoExamen();
		//MetodoResultado re=new MetodoResultado();
		ResultSet tr=null;
		ResultSet rsg=null;
		ResultSet rsgene=null;
		ResultSet rsedad=null;
		ResultSet rsNomExa=null;
		String CodPac=req.getParameter("CodPac");
		String CodExa=req.getParameter("CodExa");
		String Fecha=req.getParameter("Fecha");
		String Hora=req.getParameter("Hora");
		String Resultado=req.getParameter("Resultado");
		String Usuario=req.getParameter("Usuario");
		String CodAsignacion=req.getParameter("CodAsignacion");
		String CodTipoExa=req.getParameter("CodTipoExa");
		String cedula=req.getParameter("cedula");
		String nombre="";
		String edad=req.getParameter("edad");
		String sexo=req.getParameter("sexo");
		String TipoExamen=req.getParameter("TipoExamen");
		String CodEstudio=req.getParameter("CodEstudio");
		MetodoPacienteLaboratorio mpl = new MetodoPacienteLaboratorio ();
		MetodoVerFormatos mvf = new MetodoVerFormatos ();
		MetodoResultado mre = new MetodoResultado ();
		
		if(va.equals("0")){
			String NombreCompleto="";
			String Cantidad="";
			String Fechas="";
			rs=mpl.ObtenerPacPendiente();
			out.print("<table width='100%' class='style6' ><tr id='cabecera2' class='style11' align='center'><td colspan='2'>PACIENTES PENDIENTES</td></tr><tr><td>&nbsp;</td></tr><tr class='style11' id='cabecera2' align='center'><td >Nombre Paciente </td><td >Laboratorios Pendientes </td></tr>");
			try {
				while(rs.next()){
					NombreCompleto=rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4);
					Cantidad=rs.getString(5);
					Fechas=rs.getString(7)+" / "+rs.getString(6);
					out.print("  <tr><td><a  href='#'onclick='RealizarExamenes("+rs.getString(8)+")'>"+NombreCompleto+"</a></td><td>"+Cantidad+"</td></tr>");
				}
				out.print("</table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(va.equals("1")){
			int cont=0;
			String NomPaciente="";
			String Genero="";
			String TipoDoc="";
			String NumDoc="";
			String Edad="";
			String Documento="";
			String NombreExamen="";
			rs=mvf.ObtenerPaciente(CodPac);
			out.print("<table width='100%'  class='style6'><tr><td colspan='4' id='cabecera2'><div align='center' class='style11'>DATOS DEL PACIENTE </div></td></tr>");
			try {
				if(rs.next()){
					NomPaciente=rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3);
					Genero=rs.getString(4);
					TipoDoc=rs.getString(5);
					NumDoc=rs.getString(6);
					Edad=rs.getString(7);
					Documento=TipoDoc+" "+NumDoc;
					out.print("<tr><td width='97' >N&ordm; Documento </td><td width='113' ><p class='style9'>"+Documento+"</p></td><td width='121' >Nombre Completo </td><td width='414' ><p class='style9'>"+NomPaciente+"</p></td></tr><tr><td>Edad</td><td><p class='style9'>"+Edad+"</p></td><td>Sexo</td><td><p class='style9'>"+Genero+"</p></td></tr>");
				}
				out.print("<input name='txtnumdoc' id='txtnumdoc' type='hidden' value="+rs.getString(6)+"></table>");
				rs1=mpl.EstudiosPorPaciente(CodPac);
				out.print("<table width='100%' class='style6'><tr id='cabecera2' class='style11' align='center'><td >Examenes Pendientes<input name='cod' type='hidden' id='cod'  value='"+CodPac+"' /></td>  <td width='206'>Fecha De La Peticion </td></tr></table><div class='scrollbox3'><table width='100%' class='style6'>");
				while(rs1.next()){
					String Sexo="";
					NombreExamen=rs1.getString(2);
					if(rs1.getString(5).equals("Femenino")){
						Sexo="1";
					}
					if(rs1.getString(5).equals("Masculino")){
						Sexo="2";
					}
					String FechaPeticion=rs1.getString(7)+" "+rs1.getString(8);
					out.print("<tr><td><a  href='#'onclick='Realizar("+rs1.getString(1)+","+rs1.getString(3)+","+rs1.getString(4)+","+Sexo+","+rs1.getString(6)+")'>"+NombreExamen+"</a><input name='NomExamen' type='hidden' id='NomExamen'  value='"+rs1.getString(2)+"' /></td><td width='175'>"+FechaPeticion+"</td><td><a  href='#'onclick='omitir("+rs1.getString(4)+")'>Omitir</a></td></tr>");
					cont=cont+1;
				}
				out.print("<tr><td colspan='2'><input name='cont' type='hidden' id='cont'  value='"+cont+"' /></td></tr></table></div><div id='formato'></div>");
				rs1.getStatement().getConnection().close();
				rs.getStatement().getConnection().close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			out.print("</table>");
		}
		
		
		if(va.equals("2")){
			/**
			 * se proceden a llenar los formatos de  los laboratorios a realizar
			 * llenandose de las diferentes formas que estan como ejemplo
			 * si el tipo de examen es =0 entonces se busca en la base de datos de examen y se pone el formato
			 * si el tipo de examen es =1 entonces se busca en la base de datos de examen y se pone el formato
			 * si el tipo de examen es =2 entonces se busca en el subarea ya que es de tipo grupo y se procede a llenar el formato con todos
			 * los sub examenes que le pertenecen a este. 
			 */
			if(TipoExamen.equals("0")){
				rs=mvf.ObtenerExamenesIndividualTexto(CodEstudio);
				try {
					out.print("<table width='100%' class='style6'  border='2' align='center'><tr id='cabecera2' class='style11' align='center' ><td width='48%'>EXAMEN</td><td width='52%' >RESULTADO</td></tr>");
					if(rs.next()){
						out.print(" <tr><td>"+rs.getString(2)+"</td><td><textarea name='exagrupo' cols='40' rows='3' id='exagrupo'></textarea><input name='codexa' type='hidden' id='codexa'  value="+rs.getString(1)+" /><input name='codtipoexa' type='hidden' id='codtipoexa'  value="+rs.getString(3)+" /><input name='CodAsignacion' type='hidden' id='CodAsignacion'  value="+CodAsignacion+" /></td></tr>");
					}
					out.print("<tr><td colspan='2'><div align='center'><input name='btnExamTexto' class='boton4' type='button' id='btnExamTexto' value='     Realizar     ' onclick='ExamenTexto()' /></div></td></tr></table>");
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(TipoExamen.equals("1")){
				rs=mvf.ObtenerExamenesIndividualRango(CodEstudio);
				try {
					out.print("<table  border='2' class='style6' align='center' width='100%'><tr id='cabecera2' class='style11' align='center'><td width='50%'>EXAMEN</td><td width='20%' >RESULTADO</td><td width='15%'>UNIDAD</td><td width='15%'>RANGO</td></tr>");
					if(rs.next()){
						out.print("<tr><td>"+rs.getString(2)+"</td><td><input name='exagrupo' type='text' id='exagrupo' /></td><td>"+rs.getString(6)+"</td><td>"+rs.getString(4)+"--"+rs.getString(5)+"</td><td><input name='codtipoexa' type='hidden' id='codtipoexa'  value="+rs.getString(3)+"><input name='codexa' type='hidden' id='codexa'  value="+rs.getString(1)+"><input name='CodAsignacion' type='hidden' id='CodAsignacion'  value="+CodAsignacion+" /></td></tr>");
					}
					out.print("<tr><td colspan='5'><div align='center'><input name='btnExamenRango' class='boton4' type='button' id='btnExamenRango' value='     Realizar     ' onclick='ExamenRango()' /></div></td></tr></table>");
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////			
			if(TipoExamen.equals("2")){
				
				try {
					rsNomExa=mvf.ObtenerNombreSubarea(CodEstudio);
					if(rsNomExa.next()){
					nombre=rsNomExa.getString(1);	
					}
					rsNomExa.getStatement().getConnection().close();
				} catch (Exception e2) {

					e2.printStackTrace();
				}
			
				if(sexo.equals("Masculino")){
					sexo="Masculino";
				}
				if(sexo.equals("Femenino")){
					sexo="Femenino";
				}
				tr=exa.Buscacodge(sexo);
				String codge="";
				try {
					if(tr.next()){
						codge=tr.getString(1);
					}
					tr.getStatement().getConnection().close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				rsg=exa.BuscaSexo(nombre,codge);		
			    rs1=exa.ExameGrupo(nombre);
			    rs=exa.ExameGrup(nombre);
			    rsg=exa.BuscaSexo(nombre,codge);
			    rsgene=exa.ExameGrupoRango(nombre);
			    rsg=exa.BuscaSexo(nombre,codge);
		       
		       
		       out.print("<table  align='center' width='100%'><input name='CodAsignacion' type='hidden' id='CodAsignacion'  value="+CodAsignacion+" />");
		       out.print("<tr id='cabecera2'><td colspan='14'><div align='center'><span class='style11'>"+nombre+"</span></div></td></table>");
		       out.print("<table  border='2' class='style6' width='100%'></tr><tr align='center' id='cabecera2' class='style11'><td>EXAMEN</td><td >OBSERVACION</td><td>UNIDAD</td><td>RANGO</td></tr>");
				
				try {
				int y = 0;
						String yu="";
						String edadinicial="";
						String edadfinal="";
						int edadini = 0;
						int edadfi = 0;
						while(rs.next()){
							int edad1=Integer.parseInt(edad);
							edadini=Integer.parseInt(rs.getString(3));
							edadfi=Integer.parseInt(rs.getString(4));
							 if((edad1>edadini)&&(edad1<edadfi)){
									 edadinicial= String.valueOf(edadini);
									 edadfinal= String.valueOf(edadfi);
									 } 
						}
						 rs.getStatement().getConnection().close();
						 rsedad=exa.ExameGrupoEdad(nombre, edadinicial, edadfinal);
						while((rs1.next())){
							yu=rs1.getString(2);
							if(yu.equals("0")){
							out.print("<tr><td>"+rs1.getString(1)+"</td>");
							out.print("<td><textarea name='exagrupo' ></textarea></td></tr>");
							out.print("<input name='codtipoexa' type='hidden' id='codtipoexa'  value="+rs1.getString(2)+">");
							out.print("<input name='codexa' type='hidden' id='codexa'  value="+rs1.getString(4)+">");
							y=y+1;	
							}
							}
						rs1.getStatement().getConnection().close();
										 while(rsedad.next()){
										    out.print("<tr><td>"+rsedad.getString(1)+"</td>");
										    out.print("<td><input name='exagrupo' type='text' id='exagrupo' /></td>");
										    out.print("<td>"+rsedad.getString(6)+"</td><td>"+rsedad.getString(5)+"--"+rsedad.getString(7)+"</td>");
										    out.print("<input name='codtipoexa' type='hidden' id='codtipoexa'  value="+rsedad.getString(2)+">");
										    out.print("<input name='codexa' type='hidden' id='codexa'  value="+rsedad.getString(4)+">");
											
												y=y+1;
										}
										 rsedad.getStatement().getConnection().close();
										 while(rsg.next()){
											 out.print("<tr><td>"+rsg.getString(1)+"</td>");
											    out.print("<td><input name='exagrupo' type='text' id='exagrupo' /></td>");
											    out.print("<td>"+rsg.getString(6)+"</td><td>"+rsg.getString(5)+"--"+rsg.getString(7)+"</td>");
											    out.print("<input name='codtipoexa' type='hidden' id='codtipoexa'  value="+rsg.getString(2)+">");
											    out.print("<input name='codexa' type='hidden' id='codexa'  value="+rsg.getString(4)+">");
												y=y+1;
										 }
										 rsg.getStatement().getConnection().close();
										 while(rsgene.next()){
											 out.print("<tr><td>"+rsgene.getString(1)+"</td>");
											    out.print("<td><input name='exagrupo' type='text' id='exagrupo' /></td>");
											    out.print("<td>"+rsgene.getString(6)+"</td><td>"+rsgene.getString(5)+"--"+rsgene.getString(7)+"</td>");
											    out.print("<input name='codtipoexa' type='hidden' id='codtipoexa'  value="+rsgene.getString(2)+">");
											    out.print("<input name='codexa' type='hidden' id='codexa'  value="+rsgene.getString(4)+">");
												y=y+1;
										 }
										 rsgene.getStatement().getConnection().close();
										 
											out.print("</tr>");
									
					out.print("</table>");
					out.print("<table  border='0' align='center'><tr><td><input name='exa' type='button' id='exa' value='Guardar' class='boton4'  onclick='insertar_grupo("+y+");'/></td></tr></table>");
			    
					 
					
					 
					 
					
				} catch (SQLException e) {
					System.out.println(""+e);
					e.printStackTrace();
				}
			}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////			
			
		}
		if(va.equals("3")){
			/**
			 * guardar los tipo rango individual
			 */
			String NomUsuario="";
			String CodResult="";
			rs1=mvf.ObtenerNombreUsuario(Usuario);
			try {
				if(rs1.next()){
				NomUsuario=rs1.getString(1)+" "+rs1.getString(2);	
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			//System.out.println("rango individual CodPac "+CodPac+" CodExa "+CodExa+" Fecha "+Fecha+" Hora "+Hora+" NomUsuario "+NomUsuario+" Resultado "+Resultado+" cedula "+cedula+" CodAsignacion "+CodAsignacion);
			mre.insertar(CodPac, CodExa, Fecha, Hora, NomUsuario,Resultado,cedula,CodAsignacion);
			rs=mre.CodResult(CodExa, Hora, Fecha, CodPac);
			try {
				if(rs.next()){
					CodResult=rs.getString(1);
				}
				rs.getStatement().getConnection().close();
				//mre.insertarRango(CodResult, Resultado);
				mvf.EstadoDeAsignacion(CodAsignacion);
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(va.equals("4")){
			/**
			 * guardar los tipo texto individual
			 */
			String CodResult="";
			String NomUsuario="";
			rs1=mvf.ObtenerNombreUsuario(Usuario);
			try {
				if(rs1.next()){
				NomUsuario=rs1.getString(1)+" "+rs1.getString(2);	
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			mre.insertar(CodPac, CodExa, Fecha, Hora, NomUsuario,Resultado,cedula,CodAsignacion);
			rs=mre.CodResult(CodExa, Hora, Fecha, CodPac);
			try {
				if(rs.next()){
					CodResult=rs.getString(1);
				}
				rs.getStatement().getConnection().close();
				mvf.EstadoDeAsignacion(CodAsignacion);
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(va.equals("5")){
			/**
			 * guarda el examen en grupo
			 */
			String NomUsuario="";
			rs1=mvf.ObtenerNombreUsuario(Usuario);
			try {
				if(rs1.next()){
				NomUsuario=rs1.getString(1)+" "+rs1.getString(2);	
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			if(CodTipoExa.equals("1")){
				/**
				 * guardar los tipo rango individual
				 */
				String CodResult="";
				mre.insertar(CodPac, CodExa, Fecha, Hora, NomUsuario,Resultado,cedula,CodAsignacion);
				rs=mre.CodResult(CodExa, Hora, Fecha, CodPac);
				try {
					if(rs.next()){
						CodResult=rs.getString(1);
					}
					mvf.EstadoDeAsignacion(CodAsignacion);
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(CodTipoExa.equals("0")){
				/**
				 * guardar los tipo texto individual
				 */
				String CodResult="";
				mre.insertar(CodPac, CodExa, Fecha, Hora, NomUsuario,Resultado,cedula,CodAsignacion);
				rs=mre.CodResult(CodExa, Hora, Fecha, CodPac);
				try {
					if(rs.next()){
						CodResult=rs.getString(1);
					}
					mvf.EstadoDeAsignacion(CodAsignacion);
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
		if(va.equals("6")){
			mpl.EliminarAsignacion(CodAsignacion);
		}
		if(va.equals("7")){
			rs=mpl.ObtenerListaPacientesDia(Fecha);
			try {
				out.print("<table width='100%' border='1'><tr><td colspan='4'><div align='center'>Llamadas Recibidas En La Fecha "+Fecha+"</div></td></tr>");
				out.print("<tr><td width='52%'><div align='center'>Nombre</div></td><td width='16%'><div align='center'>Telefono 1</div> </td><td width='16%'><div align='center'>Telefono 2</div> </td><td width='16%'><div align='center'>Telefono 3</div> </td></tr>");
				while(rs.next()){
					out.print("<tr><td><div>"+rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+"</div></td><td><div>"+rs.getString(4)+"</div></td><td><div>"+rs.getString(5)+"</div></td><td><div>"+rs.getString(6)+"</div></td></tr>");
				}
				out.print("<tr><td colspan='4'><div align='center'><input type='button' name='imprimir' value='Imprimir' onclick='window.print();'></div></td></tr></table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
