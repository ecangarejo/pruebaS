/**
 * controlador: ControlIngresarResultados se encuentra la forma en que son 
 * ingresados los resultados de los formatos segun el codigo del resultado
 * que a su vez los tiene asignado un paciente.
 */
package hic_controlador;


import hic_metodo.MetodoIngresarResultados;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import adm_logica.MetodoFinalizarAdmision;
import adm_logica.MetodoPaciente;



/**
 * Servlet implementation class ControlIngresarResultados
 */
public class ControlIngresarResultados extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
	}

   protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		String va = req.getParameter("valor");
		String Resul=req.getParameter("Resul");
		String CodResul=req.getParameter("CodResul");
		/////////////////////////////////////////////////////////
		String CodPac=req.getParameter("CodPac");
		String hora=req.getParameter("hora");
		String fecha=req.getParameter("fecha");
		String usuario=req.getParameter("usuario");
		String CodAdm=req.getParameter("CodAdm");
		String CodDiagnostico=req.getParameter("CodDiagnostico");			
		/////////////////////////////////////////////////////////
		String CodDiagnosticoRela1=req.getParameter("CodDiagnosticoRela1");
		String CodDiagnosticoRela2=req.getParameter("CodDiagnosticoRela2");
		String CodDiagnosticoEgreso=req.getParameter("CodDiagnosticoEgreso");
		String TipoDiagRel1=req.getParameter("TipoDiagRel1");
		String TipoDiagRel2=req.getParameter("TipoDiagRel2");
		String TipoDiagEG=req.getParameter("TipoDiagEG");		
		/////////////////////////////////////////////////////////
		String DestinoPaciente=req.getParameter("DestinoPaciente");
		String EstadoSalida=req.getParameter("EstadoSalida");
		String fin_cons=req.getParameter("fin_cons");
		String cau_ext=req.getParameter("cau_ext");
		String tip_diag=req.getParameter("tip_diag");
		/////////////////////////////////////////////////////////
		MetodoIngresarResultados mir =new MetodoIngresarResultados();
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out=res.getWriter();
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		ResultSet rs3=null;
		ResultSet rs4=null;
		ResultSet rs5=null;
		ResultSet rs6=null;
		ResultSet rs7=null;
		ResultSet rs8=null;
		ResultSet rsxx=null;
		/////////////////////////////////////////////////////////
			
		if(va.equals("1")){
			String Resultado="";
			if(CodDiagnostico==null){
				Resultado=Resul;
				mir.ActualizarResultados(Resultado, CodResul);
			}
			if(CodDiagnostico!=null){
				try {
					Resultado=Resul+"-"+CodDiagnostico;
					mir.ActualizarResultados(Resultado, CodResul);
					
					String CodDiag_fk="";
					rs8=mir.ObtenerCodigoDiagnostico(CodDiagnostico);
					if(rs8.next()){
						CodDiag_fk=rs8.getString(1);
					}
					rs8.getStatement().getConnection().close();
					
					mir.IngresarDiagnosticoIngreso(CodResul, CodDiagnostico, usuario, hora, fecha, CodAdm, CodPac, CodDiag_fk);
					rs7=mir.ObtenerDiagIngreso(CodAdm);
					out.print("<table>");
					while(rs7.next()){
						out.print("<tr><td>"+rs7.getString(1)+"</td></tr>");
					}
					out.print("</table>");
					rs7.getStatement().getConnection().close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//out.print("Ingreso Exitosa.");
				
			}
		}
		if(va.equals("1.1")){
			String Resultado="";
			if(CodDiagnostico==null){
				Resultado=Resul;
				mir.ActualizarResultados(Resultado, CodResul);
			}
			if(CodDiagnostico!=null){
				try {
					Resultado=Resul+"-"+CodDiagnostico;
					mir.ActualizarResultados(Resultado, CodResul);
					//mir.IngresarDiagnosticoIngreso(CodResul, CodDiagnostico, usuario, hora, fecha, CodAdm, CodPac);
					rs7=mir.ObtenerResultado(CodResul);
					//out.print("<table>");
					while(rs7.next()){
						out.print(rs7.getString(1));
					}
					//out.print("</table>");
					rs7.getStatement().getConnection().close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//out.print("Ingreso Exitosa.");
				
			}
		}
		
		if(va.equals("2")){
			try {
				String CodDiag_fk="";
				rs8=mir.ObtenerCodigoDiagnostico(CodDiagnosticoEgreso);
				if(rs8.next()){
					CodDiag_fk=rs8.getString(1);
				}
				rs8.getStatement().getConnection().close();
			
				if(TipoDiagEG.equals("EG")){
					//rs=mir.ObtenerDiagEgreso(CodAdm, TipoDiagEG);
					//if(rs.next()){					
				//	//	out.print("Ya Existe Un Diagnostico de Egreso Para Esta Admision.");
					//}
					//else{
						mir.IngresarDiagnosticosEgreso(CodDiagnosticoEgreso, TipoDiagEG, usuario, hora, fecha, CodAdm, CodPac, CodDiag_fk);
				//	}
					//rs.getStatement().getConnection().close();			
				}
				if(TipoDiagEG.equals("EGH")){
				//	rs=mir.ObtenerDiagEgreso(CodAdm, TipoDiagEG);
					//if(rs.next()){					
					//	out.print("Ya Existe Un Diagnostico de Egreso Para Esta Admision.");
					//}
					//else{
						mir.IngresarDiagnosticosEgreso(CodDiagnosticoEgreso, TipoDiagEG, usuario, hora, fecha, CodAdm, CodPac, CodDiag_fk);
					//}
				//	rs.getStatement().getConnection().close();			
				}
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
		}
		if(va.equals("3")){
			
			try {				
				
				String CodDiag_fk="";
				rs8=mir.ObtenerCodigoDiagnostico(CodDiagnosticoRela1);
				if(rs8.next()){
					CodDiag_fk=rs8.getString(1);
				}
				rs8.getStatement().getConnection().close();
				
				if(TipoDiagRel1.equals("RE1")){
					rs1=mir.ObtenerDiagRE1(CodAdm, TipoDiagRel1);
					if(rs1.next()){
						out.print("Ya Existe Un Diagnostico Relacionado 1 Para Esta Admision.");
					}
					else{
						mir.IngresarDiagnosticosEgreso(CodDiagnosticoRela1, TipoDiagRel1, usuario, hora, fecha, CodAdm, CodPac,CodDiag_fk);
					}
					rs1.getStatement().getConnection().close();					
				}
				if(TipoDiagRel1.equals("RE1H")){
					rs1=mir.ObtenerDiagRE1(CodAdm, TipoDiagRel1);
					if(rs1.next()){
						out.print("Ya Existe Un Diagnostico Relacionado 1 Para Esta Admision.");
					}
					else{
						mir.IngresarDiagnosticosEgreso(CodDiagnosticoRela1, TipoDiagRel1, usuario, hora, fecha, CodAdm, CodPac,CodDiag_fk);
					}
					rs1.getStatement().getConnection().close();					
				}
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
		}
		
		if(va.equals("4")){
			try {
				String CodDiag_fk="";
				rs8=mir.ObtenerCodigoDiagnostico(CodDiagnosticoRela2);
				if(rs8.next()){
					CodDiag_fk=rs8.getString(1);
				}
				rs8.getStatement().getConnection().close();
				if(TipoDiagRel2.equals("RE2")){
					rs2=mir.ObtenerDiagRE2(CodAdm, TipoDiagRel2);
					if(rs2.next()){
						out.print("Ya Existe Un Diagnostico Relacionado 2 Para Esta Admision.");
					}
					else{
						mir.IngresarDiagnosticosEgreso(CodDiagnosticoRela2, TipoDiagRel2, usuario, hora, fecha, CodAdm, CodPac,CodDiag_fk);
					}
					rs2.getStatement().getConnection().close();
				}
				
				if(TipoDiagRel2.equals("RE2H")){
					rs2=mir.ObtenerDiagRE2(CodAdm, TipoDiagRel2);
					if(rs2.next()){
						out.print("Ya Existe Un Diagnostico Relacionado 2 Para Esta Admision.");
					}
					else{
						mir.IngresarDiagnosticosEgreso(CodDiagnosticoRela2, TipoDiagRel2, usuario, hora, fecha, CodAdm, CodPac,CodDiag_fk);
					}
					rs2.getStatement().getConnection().close();
				}
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
		}
		
		if(va.equals("5")){
			//String Pendientes="";
			try {
			/***********************************/
			/**
			 * AQUI SE PONE LA VALIDACION DE LA SALIDA DEL PACIENTE Y LIBERAR LA CAMA
			 */
				if(DestinoPaciente.equals("2")){
					//EMPIEZA LA VALIDACION.
					MetodoPaciente mp=new MetodoPaciente();
					String validacion="";
					try {						
						String DiagnosticoIngreso="";
						String CieIngreso="";							
						String DiagnosticoEgreso="";
						String CieEgreso="";						
						rs1=mp.datosPacienteAlta(CodAdm, CodPac);
						if(rs1.next()){
							//valida que el paciente tenga formatos pendientes.
							validacion=rs1.getString(1);
							if(validacion!=""){
								rsxx=mp.datosPacienteAlta(CodAdm, CodPac);
								String cadena ="";
								out.println("EL PACIENTE TIENE FORMATOS PENDIENTES.");
								out.println("RELACIONADOS DE LA SIGUIENTE FORMA:");
								out.println(" ");
								while(rsxx.next()){
									cadena=rsxx.getString(1);
									out.println(cadena);
								}
								rsxx.getStatement().getConnection().close();
								//valida que el paciente tenga los diagnosticos de ingreso y egreso.
								rs3=mp.BuscarDiagnosticoIngresoAdmisiones(CodAdm);
								if(rs3.next()){
									DiagnosticoIngreso=rs3.getString(1);
									CieIngreso=rs3.getString(2);
								}
								else{
									out.println(" ");
									out.println("NO TIENE DIAGNOSTICO DE INGRESO.");
								}
								rs3.getStatement().getConnection().close();
								
								rs4=mp.BuscarDiagnosticoEgresoAdmisiones(CodAdm);
								if(rs4.next()){	
									DiagnosticoEgreso=rs4.getString(1);
									CieEgreso=rs4.getString(2);
								}
								else{		
									out.println(" ");
									out.println("NO TIENE DIAGNOSTICO DE EGRESO.");
								}
								rs4.getStatement().getConnection().close();
							}								
						}
						else{
							rs5=mp.BuscarDiagnosticoIngresoAdmisiones(CodAdm);								
							if(rs5.next()){
								DiagnosticoIngreso=rs5.getString(1);
								CieIngreso=rs5.getString(2);					
							}
							else{
								out.println(" ");
								out.println("NO TIENE DIAGNOSTICO DE INGRESO.");
							}
							rs5.getStatement().getConnection().close();
								
							rs6=mp.BuscarDiagnosticoEgresoAdmisiones(CodAdm);
							if(rs6.next()){
								DiagnosticoEgreso=rs6.getString(1);
								CieEgreso=rs6.getString(2);
							}
							else{	
								out.println(" ");
								out.println("NO TIENE DIAGNOSTICO DE EGRESO.");
							}
							rs6.getStatement().getConnection().close();
								
							if((CieEgreso!="")&&(CieIngreso!="")){
								String numcama="";
								rs=mp.CodigoPaciente(CodPac);
								if(rs.next()){
									numcama=rs.getString(11);
								}
								rs.getStatement().getConnection().close();
								DestinoPaciente="REMISION A OTRA IPS";
								if(EstadoSalida.equals("1")){EstadoSalida="VIVO";}
								if(EstadoSalida.equals("2")){EstadoSalida="MUERTO";}
								
								mir.IngresarEstadoSalidaDestino(DestinoPaciente, EstadoSalida, usuario, hora, fecha, CodAdm, CodPac,tip_diag,cau_ext,fin_cons);
								
								MetodoFinalizarAdmision fa = new MetodoFinalizarAdmision ();		
																	
								fa.ActualizarAdmision(CodAdm);
								fa.ActualizarCama(numcama);
								fa.ActualizarHistoria(numcama, CodAdm, fecha,hora,usuario);
								out.print("");
							}
						}
						rs1.getStatement().getConnection().close();		
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				if(DestinoPaciente.equals("2.1")){
					//EMPIEZA LA VALIDACION.
					MetodoPaciente mp=new MetodoPaciente();
					String validacion="";
					try {
						String DiagnosticoIngreso="";
						String CieIngreso="";							
						String DiagnosticoEgreso="";
						String CieEgreso="";						
						rs1=mp.datosPacienteAlta(CodAdm, CodPac);
						if(rs1.next()){
							//valida que el paciente tenga formatos pendientes.
							validacion=rs1.getString(1);
							if(validacion!=""){
								rsxx=mp.datosPacienteAlta(CodAdm, CodPac);
								String cadena ="";
								out.println("EL PACIENTE TIENE FORMATOS PENDIENTES.");
								out.println("RELACIONADOS DE LA SIGUIENTE FORMA:");
								out.println(" ");
								while(rsxx.next()){
									cadena=rsxx.getString(1);
									out.println(cadena);
								}
								rsxx.getStatement().getConnection().close();								
								//valida que el paciente tenga los diagnosticos de ingreso y egreso.
								rs3=mp.BuscarDiagnosticoIngresoAdmisiones(CodAdm);
								if(rs3.next()){
									DiagnosticoIngreso=rs3.getString(1);
									CieIngreso=rs3.getString(2);
								}
								else{
									out.println(" ");
									out.println("NO TIENE DIAGNOSTICO DE INGRESO.");
								}
								rs3.getStatement().getConnection().close();
								
								rs4=mp.BuscarDiagnosticoEgresoAdmisionesH(CodAdm);
								if(rs4.next()){	
									DiagnosticoEgreso=rs4.getString(1);
									CieEgreso=rs4.getString(2);
								}
								else{		
									out.println(" ");
									out.println("NO TIENE DIAGNOSTICO DE EGRESO.");
								}
								rs4.getStatement().getConnection().close();
							}								
						}
						else{
							rs5=mp.BuscarDiagnosticoIngresoAdmisiones(CodAdm);								
							if(rs5.next()){
								DiagnosticoIngreso=rs5.getString(1);
								CieIngreso=rs5.getString(2);					
							}
							else{
								out.println(" ");
								out.println("NO TIENE DIAGNOSTICO DE INGRESO.");
							}
							rs5.getStatement().getConnection().close();
								
							rs6=mp.BuscarDiagnosticoEgresoAdmisionesH(CodAdm);
							if(rs6.next()){
								DiagnosticoEgreso=rs6.getString(1);
								CieEgreso=rs6.getString(2);
							}
							else{
								out.println(" ");
								out.println("NO TIENE DIAGNOSTICO DE EGRESO.");
							}
							rs6.getStatement().getConnection().close();
								
							if((CieEgreso!="")&&(CieIngreso!="")){
								String numcama="";
								rs=mp.CodigoPaciente(CodPac);
								if(rs.next()){
									numcama=rs.getString(11);
								}
								rs.getStatement().getConnection().close();
								DestinoPaciente="REMISION A OTRA IPS";
								if(EstadoSalida.equals("1")){EstadoSalida="VIVO";}
								if(EstadoSalida.equals("2")){EstadoSalida="MUERTO";}
								mir.IngresarEstadoSalidaDestino(DestinoPaciente, EstadoSalida, usuario, hora, fecha, CodAdm, CodPac,tip_diag,cau_ext,fin_cons);
								
								MetodoFinalizarAdmision fa = new MetodoFinalizarAdmision ();		
																	
								fa.ActualizarAdmision(CodAdm);
								fa.ActualizarCama(numcama);
								fa.ActualizarHistoria(numcama, CodAdm, fecha,hora,usuario);
								out.print("");
							}
						}
						rs1.getStatement().getConnection().close();		
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				
				
				
				if(DestinoPaciente.equals("3")){
					/*DestinoPaciente="HOSPITALIZACION";
					if(EstadoSalida.equals("1")){EstadoSalida="VIVO";}
					if(EstadoSalida.equals("2")){EstadoSalida="MUERTO";}
					mir.IngresarEstadoSalidaDestino(DestinoPaciente, EstadoSalida, usuario, hora, fecha, CodAdm, CodPac);*/
					//EMPIEZA LA VALIDACION.
					MetodoPaciente mp=new MetodoPaciente();
					String validacion="";
					try {
						String DiagnosticoIngreso="";
						String CieIngreso="";							
						String DiagnosticoEgreso="";
						String CieEgreso="";						
						rs1=mp.datosPacienteAlta(CodAdm, CodPac);
						if(rs1.next()){
							//valida que el paciente tenga formatos pendientes.
							validacion=rs1.getString(1);
							if(validacion!=""){
								rsxx=mp.datosPacienteAlta(CodAdm, CodPac);
								String cadena ="";
								out.println("EL PACIENTE TIENE FORMATOS PENDIENTES.");
								out.println("RELACIONADOS DE LA SIGUIENTE FORMA:");
								out.println(" ");
								while(rsxx.next()){
									cadena=rsxx.getString(1);
									out.println(cadena);
								}
								rsxx.getStatement().getConnection().close();								
								//valida que el paciente tenga los diagnosticos de ingreso y egreso.
								rs3=mp.BuscarDiagnosticoIngresoAdmisiones(CodAdm);
								if(rs3.next()){
									DiagnosticoIngreso=rs3.getString(1);
									CieIngreso=rs3.getString(2);
								}
								else{
									out.println(" ");
									out.println("NO TIENE DIAGNOSTICO DE INGRESO.");
								}
								rs3.getStatement().getConnection().close();
								
								rs4=mp.BuscarDiagnosticoEgresoAdmisiones(CodAdm);
								if(rs4.next()){	
									DiagnosticoEgreso=rs4.getString(1);
									CieEgreso=rs4.getString(2);
								}
								else{		
									out.println(" ");
									out.println("NO TIENE DIAGNOSTICO DE EGRESO.");
								}
								rs4.getStatement().getConnection().close();
							}
								
						}
						else{
							rs5=mp.BuscarDiagnosticoIngresoAdmisiones(CodAdm);								
							if(rs5.next()){
								DiagnosticoIngreso=rs5.getString(1);
								CieIngreso=rs5.getString(2);					
							}
							else{
								out.println(" ");
								out.println("NO TIENE DIAGNOSTICO DE INGRESO.");
							}
							rs5.getStatement().getConnection().close();
								
							rs6=mp.BuscarDiagnosticoEgresoAdmisiones(CodAdm);
							if(rs6.next()){
								DiagnosticoEgreso=rs6.getString(1);
								CieEgreso=rs6.getString(2);
							}
							else{		
								out.println(" ");
								out.println("NO TIENE DIAGNOSTICO DE EGRESO.");
							}
							rs6.getStatement().getConnection().close();
								
							if((CieEgreso!="")&&(CieIngreso!="")){
								String numcama="";
								rs=mp.CodigoPaciente(CodPac);
								if(rs.next()){
									numcama=rs.getString(11);
								}
								rs.getStatement().getConnection().close();
								DestinoPaciente="HOSPITALIZACION";
								if(EstadoSalida.equals("1")){EstadoSalida="VIVO";}
								if(EstadoSalida.equals("2")){EstadoSalida="MUERTO";}
								mir.IngresarEstadoSalidaDestino(DestinoPaciente, EstadoSalida, usuario, hora, fecha, CodAdm, CodPac,tip_diag,cau_ext,fin_cons);
								
								//MetodoFinalizarAdmision fa = new MetodoFinalizarAdmision ();		
																	
								/*fa.ActualizarAdmision(CodAdm);
								fa.ActualizarCama(numcama);
								fa.ActualizarHistoria(numcama, CodAdm, fecha,hora,usuario);*/
								//res.sendRedirect("menuadm.jsp");
								out.print("");
							}
						}
						rs1.getStatement().getConnection().close();		
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				if(DestinoPaciente.equals("1")){
					//EMPIEZA LA VALIDACION.
					MetodoPaciente mp=new MetodoPaciente();
					String validacion="";
					try {
						String DiagnosticoIngreso="";
						String CieIngreso="";							
						String DiagnosticoEgreso="";
						String CieEgreso="";						
						rs1=mp.datosPacienteAlta(CodAdm, CodPac);
						if(rs1.next()){
							//valida que el paciente tenga formatos pendientes.
							validacion=rs1.getString(1);
							if(validacion!=""){
								rsxx=mp.datosPacienteAlta(CodAdm, CodPac);
								String cadena ="";
								out.println("EL PACIENTE TIENE FORMATOS PENDIENTES.");
								out.println("RELACIONADOS DE LA SIGUIENTE FORMA:");
								out.println(" ");
								while(rsxx.next()){
									cadena=rsxx.getString(1);
									out.println(cadena);
								}
								rsxx.getStatement().getConnection().close();
								//valida que el paciente tenga los diagnosticos de ingreso y egreso.
								rs3=mp.BuscarDiagnosticoIngresoAdmisiones(CodAdm);
								if(rs3.next()){
									DiagnosticoIngreso=rs3.getString(1);
									CieIngreso=rs3.getString(2);
								}
								else{
									out.println(" ");
									out.println("NO TIENE DIAGNOSTICO DE INGRESO.");
								}
								rs3.getStatement().getConnection().close();
								
								rs4=mp.BuscarDiagnosticoEgresoAdmisiones(CodAdm);
								if(rs4.next()){	
									DiagnosticoEgreso=rs4.getString(1);
									CieEgreso=rs4.getString(2);
								}
								else{			
									out.println(" ");
									out.println("NO TIENE DIAGNOSTICO DE EGRESO.");
								}
								rs4.getStatement().getConnection().close();
							}
								
						}
						else{
							rs5=mp.BuscarDiagnosticoIngresoAdmisiones(CodAdm);								
							if(rs5.next()){
								DiagnosticoIngreso=rs5.getString(1);
								CieIngreso=rs5.getString(2);					
							}
							else{
								out.println(" ");
								out.println("NO TIENE DIAGNOSTICO DE INGRESO.");
							}
							rs5.getStatement().getConnection().close();
								
							rs6=mp.BuscarDiagnosticoEgresoAdmisiones(CodAdm);
							if(rs6.next()){
								DiagnosticoEgreso=rs6.getString(1);
								CieEgreso=rs6.getString(2);
							}
							else{		
								out.println(" ");
								out.println("NO TIENE DIAGNOSTICO DE EGRESO.");
							}
							rs6.getStatement().getConnection().close();
								
							if((CieEgreso!="")&&(CieIngreso!="")){
								String numcama="";
								rs=mp.CodigoPaciente(CodPac);
								if(rs.next()){
									numcama=rs.getString(11);
								}
								rs.getStatement().getConnection().close();
								DestinoPaciente="SALIDA DE URGENCIA";
								if(EstadoSalida.equals("1")){EstadoSalida="VIVO";}
								if(EstadoSalida.equals("2")){EstadoSalida="MUERTO";}
								mir.IngresarEstadoSalidaDestino(DestinoPaciente, EstadoSalida, usuario, hora, fecha, CodAdm, CodPac,tip_diag,cau_ext,fin_cons);
								mir.ActualizarEncabezadoFechaEgreso(fecha, CodAdm);
								MetodoFinalizarAdmision fa = new MetodoFinalizarAdmision ();		
																	
								fa.ActualizarAdmision(CodAdm);
								fa.ActualizarCama(numcama);
								fa.ActualizarHistoria(numcama, CodAdm, fecha,hora,usuario);
								//res.sendRedirect("menuadm.jsp");
								out.print("");
							}
						}
						rs1.getStatement().getConnection().close();		
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				if(DestinoPaciente.equals("1.1")){
					//EMPIEZA LA VALIDACION.
					MetodoPaciente mp=new MetodoPaciente();
					String validacion="";
					try {
						String DiagnosticoIngreso="";
						String CieIngreso="";							
						String DiagnosticoEgreso="";
						String CieEgreso="";						
						rs1=mp.datosPacienteAlta(CodAdm, CodPac);
						if(rs1.next()){
							//valida que el paciente tenga formatos pendientes.
							validacion=rs1.getString(1);
							if(validacion!=""){
								rsxx=mp.datosPacienteAlta(CodAdm, CodPac);
								String cadena ="";
								out.println("EL PACIENTE TIENE FORMATOS PENDIENTES.");
								out.println("RELACIONADOS DE LA SIGUIENTE FORMA:");
								out.println(" ");
								while(rsxx.next()){
									cadena=rsxx.getString(1);
									out.println(cadena);
								}
								rsxx.getStatement().getConnection().close();								
								//valida que el paciente tenga los diagnosticos de ingreso y egreso.
								rs3=mp.BuscarDiagnosticoIngresoAdmisiones(CodAdm);
								if(rs3.next()){
									DiagnosticoIngreso=rs3.getString(1);
									CieIngreso=rs3.getString(2);
								}
								else{
									out.println(" ");
									out.println("NO TIENE DIAGNOSTICO DE INGRESO.");
								}
								rs3.getStatement().getConnection().close();
								
								rs4=mp.BuscarDiagnosticoEgresoAdmisionesH(CodAdm);
								if(rs4.next()){	
									DiagnosticoEgreso=rs4.getString(1);
									CieEgreso=rs4.getString(2);
								}
								else{	
									out.println(" ");
									out.println("NO TIENE DIAGNOSTICO DE EGRESO.");
								}
								rs4.getStatement().getConnection().close();
							}								
						}
						else{
							rs5=mp.BuscarDiagnosticoIngresoAdmisiones(CodAdm);								
							if(rs5.next()){
								DiagnosticoIngreso=rs5.getString(1);
								CieIngreso=rs5.getString(2);					
							}
							else{
								out.println(" ");
								out.println("NO TIENE DIAGNOSTICO DE INGRESO.");
							}
							rs5.getStatement().getConnection().close();
								
							rs6=mp.BuscarDiagnosticoEgresoAdmisionesH(CodAdm);
							if(rs6.next()){
								DiagnosticoEgreso=rs6.getString(1);
								CieEgreso=rs6.getString(2);
							}
							else{	
								out.println(" ");
								out.println("NO TIENE DIAGNOSTICO DE EGRESO.");
							}
							rs6.getStatement().getConnection().close();
								
							if((CieEgreso!="")&&(CieIngreso!="")){
								String numcama="";
								rs=mp.CodigoPaciente(CodPac);
								if(rs.next()){
									numcama=rs.getString(11);
								}
								rs.getStatement().getConnection().close();
								DestinoPaciente="SALIDA DE HOSPITALIZACION";
								if(EstadoSalida.equals("1")){EstadoSalida="VIVO";}
								if(EstadoSalida.equals("2")){EstadoSalida="MUERTO";}
								mir.IngresarEstadoSalidaDestino(DestinoPaciente, EstadoSalida, usuario, hora, fecha, CodAdm, CodPac,tip_diag,cau_ext,fin_cons);
								//mir.ActualizarEncabezadoFechaEgreso(fecha, CodAdm);
								/*MetodoFinalizarAdmision fa = new MetodoFinalizarAdmision ();		
																	
								fa.ActualizarAdmision(CodAdm);
								fa.ActualizarCama(numcama);
								fa.ActualizarHistoria(numcama, CodAdm, fecha,hora,usuario);*/
								out.print("");
							}
						}
						rs1.getStatement().getConnection().close();		
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			/***********************************/
			}catch(Exception e){
				out.print("Error "+e);
				e.printStackTrace();
			}
		}
		
	}
}
