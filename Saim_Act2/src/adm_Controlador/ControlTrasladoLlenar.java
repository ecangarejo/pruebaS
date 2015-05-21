/**
 * NOMBRE DE LA CLASE: ControlTrasladoLlenar
 * AUTOR: Oscar Rolong Schweiger
 * DESCRIPCION: En este Controlador se encuentra lo necesario
 * 				para realizar traslados. 				
 */
package adm_Controlador;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import adm_logica.MetodoHistoricoCama;
import adm_logica.MetodoTraslado;
import pend_metodo.metodoTraslados;

import java.sql.*;


public class ControlTrasladoLlenar extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
		res.setContentType("text/html;charset=UTF-8");		
		MetodoTraslado ta= new MetodoTraslado();	
		metodoTraslados pendtras = new metodoTraslados();
		String va = req.getParameter("va");	
		String x=req.getParameter("x");		
		String xa=req.getParameter("xa");		
		String y=req.getParameter("y");		
		String ya=req.getParameter("ya");		
		String cama=req.getParameter("cama");
		String cedula1=req.getParameter("cedula");	
		String CamaNueva=req.getParameter("CamaNueva");
		String TipoDocumento=req.getParameter("TipoDocumento");
		String NumeroDocumento=req.getParameter("NumeroDocumento");
		String CodArea=req.getParameter("CodArea");
		String CodSubarea=req.getParameter("CodSubarea");
		ResultSet rs=null;	
		ResultSet rs1=null;
		ResultSet rs2=null;
		ResultSet rs3=null;
		PrintWriter out=res.getWriter();	
		/*****************************VA>8*********************************************/
		if(va.equals("8")){
			
			try {
				rs2=ta.BuscarTrasladoUrgHosp(TipoDocumento, NumeroDocumento);
				if(rs2.next()){
					out.print("Paciente Ya Esta En Hospitalizacion");
				}else{					
					rs=ta.BuscarPaciente(TipoDocumento, NumeroDocumento);
					if(rs.next()){
						rs3=ta.ValidarSalidaUrgencia(rs.getString(3));
						if(rs3.next()){
							out.print("<table width='100%' border='0'><tr><td width='13%'>Nombre Paciente<input name='txtCodAdm' type='hidden' id='txtCodAdm' value="+rs.getString(3)+" ><input name='txtCodPac' type='hidden' id='txtCodPac' value="+rs.getString(5)+"><input name='txtCodCama' type='hidden' id='txtCodCama' value="+rs.getString(4)+"></td><td width='39%'>"+rs.getString(1)+"</td><td width='7%'>Ubicacion</td><td width='41%'>"+rs.getString(2)+"</td></tr>");
							out.print("<tr><td colspan='4'><div id='cabecera2' class='style11' align='left'>TRASLADAR A:</div></td></tr></table>");
							out.print("<table width='100%' border='0'><tr><td width='12%'>Area</td> <td width='17%'><select name='cmbArea' id='cmbArea' onChange='CargarSubarea()'><option value='Seleccione'>Seleccione</option>");

							rs1=ta.obtenerArea();
							while(rs1.next()){
								out.print("<option title='"+rs1.getString(1)+"' value="+rs1.getString(2)+">"+rs1.getString(1)+"</option>");
							}
							rs1.getStatement().getConnection().close();

							out.print("</select></td><td width='10%'>Subarea</td><td width='24%'><div id='Subarea'><select name='cmbSubarea' id='cmbSubarea'><option value='Seleccione'>Seleccione</option></select></div></td><td width='9%'>Cama</td><td width='28%'><div id='CamaSub'><select name='cmbCama' id='cmbCama'><option value='Seleccione'>Seleccione</option></select></div></td><td></td><td></td></tr>");
							out.print("<tr><td>N� Autorizacion </td><td><input name='txtNumAutoriza' type='text' id='txtNumAutoriza'></td><td>Autorizado Por </td><td><input name='txtAutorizadoPor' type='text' id='txtAutorizadoPor'></td><td>Medico Tratante</td><td><select id='cmbMedicoTratante' style='width:100%'><option value='Seleccione' selected=''>Seleccione</option>");
							rs1=ta.ListarEspecialistas();
							while(rs1.next()){
								out.print("<option title='"+rs1.getString(2)+"' value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
							}
							rs1.getStatement().getConnection().close();

							out.print("</select></td><td>Observacion</td><td><textarea name='txtObservacion' cols='40' rows='4' id='txtObservacion'></textarea></td></tr>");
							out.print("<tr><td colspan='6'><div align='center'><input name='btnTrasladar' type='button' id='btnTrasladar' value='     Trasladar     ' onclick='TrasladarUrgHosp()' ></div></td></tr></table>");
						}else{
							out.print("Al Paciente No Le Han Dado La Alta De Urgencia.\nEL Medico En Turno Debe Ingresar El Destino Del Paciente.");
						}
						rs3.getStatement().getConnection().close();
					}
					rs.getStatement().getConnection().close();
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(va.equals("8.1")){
			rs=ta.obtenerSubAreaCodigo(CodArea);
			try {
				out.print("<select name='cmbSubarea' id='cmbSubarea' onchange='ObtenerCama()'><option value='Seleccione'>Seleccione</option>");
				while(rs.next()){
					out.print("<option value="+rs.getString(2)+">"+rs.getString(1)+"</option>");
				}
				out.print("</select>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		if(va.equals("8.2")){
			rs=ta.obtenerCamaSubarea(CodSubarea);
			try {
				out.print("<select name='cmbCama' id='cmbCama' ><option value='Seleccione'>Seleccione</option>");
				while(rs.next()){
					out.print("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
				}
				out.print("</select>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		if(va.equals("9")){
			MetodoHistoricoCama hc=new MetodoHistoricoCama();
			String horaFinal="00:00:00";
			String fechaincioNue="0000-00-00";
			String usuarioFin="-1";
			String horaIni=req.getParameter("hora");
			String FechaFinal=req.getParameter("fecha");
			String CodCamaVieja=req.getParameter("CodCamaVieja");
			String CodCamaNueva=req.getParameter("CodCamaNueva");
			String CodAdm=req.getParameter("CodAdm");
			String usuario=req.getParameter("usuario");
			String Observacion=req.getParameter("Observacion");
			String NumAutorizacion=req.getParameter("NumAutorizacion");
			String NombreAutoriza=req.getParameter("NombreAutoriza");
			String Fecha=req.getParameter("Fecha");
			String Hora=req.getParameter("Hora");
			/*************************************************************************/
		
					ta.ActualizarLibreCodigo(CodCamaVieja);
					ta.ActualizarOcupadaCodigo(CodCamaNueva);
					//ta.obtenerCodCama(camaAc);
					ta.ActualizarAdmision(CodCamaVieja, CodCamaNueva, CodAdm);
					hc.ActualizarHistoriaTraslado(CodCamaVieja, CodAdm, Fecha,Hora,usuario);
					
					hc.insertarTrasUrg(CodCamaNueva, Fecha, fechaincioNue, CodAdm,Hora,usuario,horaFinal,usuarioFin);
					ta.insertarTrasUrgHosp(Fecha, Hora, CodAdm, usuario, CodCamaVieja, CodCamaNueva, Observacion, NumAutorizacion, NombreAutoriza);
					
					out.print("Traslado Exitoso.");
			
			
			/**************************************************************************/
		}
		
		if(va.equals("tr")){
			MetodoHistoricoCama hc=new MetodoHistoricoCama();
			//MetodoTraslado ta= new MetodoTraslado();
			//String camaOc=req.getParameter("camaOc");//cama a ocupar			
			//String camaAc=req.getParameter("camaAc");//cama actual (la q se va a cambiar por camaOc) 		
			String codigoNueva=req.getParameter("codigoNueva");		
			String codigoVieja=req.getParameter("codigoVieja");		
			String codigoAdmision=req.getParameter("codigo");
			String usuarioIni=req.getParameter("usuario");	
			//String FechaFinal=req.getParameter("fecha");	
			String horaFinal="00:00:00";
			String fechaincioNue="0000-00-00";
			String usuarioFin="-1";
			//String horaIni=req.getParameter("hora");
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date FechaFinal = new java.sql.Date(fechaActual.getTime());		
			java.sql.Time horaIni = new java.sql.Time(fechaActual.getTime());	

			/*la consulta dede de ser con el parametro de camaOc*/		
			try {
				System.out.println("Cama_Anterior "+codigoVieja+" Cama_Nueva "+codigoNueva);
				rs=ta.BuscarCamaOcupada(codigoNueva);
				if(rs.next()){
					if(rs.getString(1).equals("3")){
						out.print("La Cama Esta Ocupada.\n Seleccione Otra.");
					}
					
					if(rs.getString(1).equals("1")){
						
						System.out.println("codigoVieja "+codigoVieja+" codigoNueva "+codigoNueva);
						
						ta.ActualizarLibre(codigoVieja);
						ta.ActualizarOcupada(codigoNueva);
						//ta.obtenerCodCama(camaAc);
						ta.ActualizarAdmision(codigoVieja, codigoNueva, codigoAdmision);
						hc.ActualizarHistoria(codigoVieja, codigoAdmision, FechaFinal,horaIni,usuarioIni);
						hc.insertar2(codigoNueva, FechaFinal, fechaincioNue, codigoAdmision,horaIni,usuarioIni,horaFinal,usuarioFin);
						out.print("");
						
						//se modifica el traslado pendiente
						rs1 =pendtras.ObtenerPendTransPac(codigoAdmision);
						if (rs1.next()){
							pendtras.actualizarTraslado(codigoAdmision, "1");
						}
						
						
						//res.sendRedirect("Adm_traslado.jsp");
					}
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		if(va.equals("Imt")){
			java.util.Date fechaActualInm = new java.util.Date();
			java.sql.Date FechaRegis = new java.sql.Date(fechaActualInm.getTime());		
			java.sql.Time HoraRegis = new java.sql.Time(fechaActualInm.getTime());
			String CodAdm=req.getParameter("CodAdm");
			String UsuarioIngresa=req.getParameter("UsuarioIngresa");
			String NomMedico=req.getParameter("NomMedico");
			String CodMedTra=req.getParameter("CodMedTra");
			rs=ta.BuscarMedicoTratante(CodAdm);
			try {
				if(rs.next()){
					//ya tiene un medico tratante activo
					//se hace un update al resistro que ya tiene
					ta.ActualizarMedicoTratante(rs.getString(1));
					ta.IngresarMedicoTratante(CodMedTra, NomMedico, CodAdm,FechaRegis, HoraRegis, UsuarioIngresa);
					//se crea registro nuevo
				}else{
					ta.IngresarMedicoTratante(CodMedTra, NomMedico, CodAdm,FechaRegis, HoraRegis, UsuarioIngresa);
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		if(va.equals("9.1")){
			MetodoHistoricoCama hc=new MetodoHistoricoCama();
			String horaFinal="00:00:00";
			String fechaincioNue="0000/00/00";
			String usuarioFin="-1";
			String horaIni=req.getParameter("hora");
			String FechaFinal=req.getParameter("fecha");
			String CodCamaVieja=req.getParameter("CodCamaVieja");
			String CodCamaNueva=req.getParameter("CodCamaNueva");
			String CodAdm=req.getParameter("CodAdm");
			String usuario=req.getParameter("usuario");
			String Observacion=req.getParameter("Observacion");
			String NumAutorizacion=req.getParameter("NumAutorizacion");
			String NombreAutoriza=req.getParameter("NombreAutoriza");
			String Fecha=req.getParameter("Fecha");
			String Hora=req.getParameter("Hora");
			/*************************************************************************/
			
			/*try {
				if(rs.next()){*/
					ta.ActualizarLibreCodigo(CodCamaVieja);
					ta.ActualizarOcupadaCodigo(CodCamaNueva);
					//ta.obtenerCodCama(camaAc);
					ta.ActualizarAdmision(CodCamaVieja, CodCamaNueva, CodAdm);
					hc.ActualizarHistoriaTraslado(CodCamaVieja, CodAdm, Fecha,Hora,usuario);
					
					hc.insertarTrasUrg(CodCamaNueva, Fecha, fechaincioNue, CodAdm,Hora,usuario,horaFinal,usuarioFin);
					ta.insertarTrasUrgHosp(Fecha, Hora, CodAdm, usuario, CodCamaVieja, CodCamaNueva, Observacion, NumAutorizacion, NombreAutoriza);
					ta.ActualizarEstadoFactu(CodAdm);
					ta.ActualizarEncabezado(CodAdm);
					out.print("1");
					
					//se modifica el traslado pendiente
					rs1 =pendtras.ObtenerPendTransPac(CodAdm);
					try{
					
					if (rs1.next()){
						pendtras.actualizarTraslado(CodAdm, "1");
					}
					
					rs1.getStatement().getConnection().close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					
				/*	out.print("1");
					
					
				}else{
					//out.print("Al Paciente No Le Han Dado La Alta De Urgencia.\nEL Medico En Turno Debe De Dar La Salida");
					out.print("2");
				}
				rs.getStatement().getConnection().close();*/
			/*} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
			/**************************************************************************/
		}
		/****************************************************************************/
		if(va.equals("6")){
			/**
			 * se obtiene la subarea mediante MetodoTraslado/obtenerSubArea
			 * que lleva como parametro el nombre del area.
			 */
			rs= ta.obtenerSubArea(xa);		
			String s[] = new String[1000];
			try{
				int k=0;
				while(rs.next()){						
					s[k]=rs.getString(1);
					out.print(s[k]+"_");
					k++;								
				}
				rs.getStatement().getConnection().close();
			}		
			catch(SQLException e1){
				e1.getMessage();
			}
		}
		
		if(va.equals("7")){
			/**
			 * se obtiene la cama mediante la consulta MetodoTraslado/obtenerCamaActualizar
			 * que lleva como parametro el subarea y solo escoje las que estan disponibles
			 * 
			 */
			rs= ta.obtenerCamaActualizar(ya);		
			//String s[] = new String[1000];
			try{
				//int k=0;
				out.print("<select name='cmbcamatr' id='cmbcamatr' ><option value='Seleccione'>Seleccione</option>");
				while(rs.next()){
					//s[k]=rs.getString(1);
					out.print("<option value="+rs.getString(2)+">"+rs.getString(1)+"</option>");
					//k++;								
				}	
				out.print("</select>");
				rs.getStatement().getConnection().close();
			}		
			catch(SQLException e1){
				e1.getMessage();
			}	
		}	
			
		if (va.equals("1")){
			/**
			 * se obtiene las areas mediante MetodoTraslado/obtenerArea
			 * 
			 */
			rs= ta.obtenerArea1();
			String v[] = new String[1000];
			try{
				int c=0;
				while(rs.next()){
					v[c]=rs.getString(1);
					out.print(v[c]+"_");
					c++;
				}
				rs.getStatement().getConnection().close();
			}
			catch(SQLException e1){
				e1.getMessage();
			}			 
		}
		
		if(va.equals("2")){
			/**
			 * se obtiene la subarea mediante MetodoTraslado/obtenerSubArea
			 * que lleva como parametro el nombre del area.
			 */
			rs= ta.obtenerSubArea(x);		
			String s[] = new String[1000];
			try{
				int k=0;
				while(rs.next()){
					s[k]=rs.getString(1);
					out.print(s[k]+"_");
					k++;								
				}
				rs.getStatement().getConnection().close();
			}		
			catch(SQLException e1){
				e1.getMessage();
			}
		}
		
		if(va.equals("3")){
			/**
			 * se obtiene la cama mediante la consulta MetodoTraslado/obtenerCama
			 * que lleva como parametro el subarea y solo escoje las que estan ocupadas
			 * 
			 */
			 rs= ta.obtenerCama(y);		
			// String s[] = new String[1000];
				try{
					//int k=0;
					out.print("<select name='cmbcama' id='cmbcama' onchange='VerificarPaciente()' ><option value='Seleccione' selected='selected'>Seleccione</option>");
					while(rs.next()){
						//s[k]=rs.getString(1);
						out.print("<option value="+rs.getString(2)+" >"+rs.getString(1)+"</option>");
						//k++;								
					}
					out.print("</select>");
					rs.getStatement().getConnection().close();
				}		
				 catch(SQLException e1){
					 e1.getMessage();
					 }	
		}
		if (va.equals("4")){
			/**
			 * se obtienen los datos del pacientes mediante
			 * MetodoTraslado/obtenerPaciente y lleva como parametro el nombre de la cama
			 */
			 rs= ta.obtenerPaciente(cama);
			 String nombre=null;
			 String cedula=null;
			 String apellido=null;
			 String codigoVieja=null;
				try{				
					while(rs.next()){
						cedula=rs.getString(1);
						nombre=rs.getString(2);
						apellido=rs.getString(3);
						
					    //alert(direccion.length);
					  /*  for(int i=0;i<apellido.length();i++){
					    	apellido=apellido.replace('�','N');
					      //alert(direccion);
					    }*/
						codigoVieja=rs.getString(4);
						out.print(nombre+"_"+apellido+"_"+cedula+"_"+rs.getString(5));
					}
					rs.getStatement().getConnection().close();
				}
				 catch(SQLException e1){
					 e1.getMessage();
					 }
				 }
		
		
		if(va.equals("0")){
			/**
			 * se obtiene el codigo de la admision a actualizar mediante
			 * MetodoTraslado/obtenerCodigoAdmision que lleva como parametro
			 * la cedula del paciente y el codigo de la cama
			 */
			rs=ta.obtenerCodigoAdmision(cedula1,CamaNueva);
			String admision=null;
			try{
				while (rs.next()){
					admision=rs.getString(1);
					CamaNueva=rs.getString(2);
					out.print(admision+"_"+CamaNueva);
				
					
				}
				rs.getStatement().getConnection().close();
			}
			 catch(SQLException e1){
				 e1.getMessage();
			 }		  
		}
		
		
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
	/**
	 * se obtienen los datos desde Adm_traslado.jsp
	 * para hacer el traslado de la cama se hace una actualizacion en
	 * historico cama que pone la fecha final de la cama que estaba ocupada
	 * y pone la fecha inicial de la cama del nuevo traslado esto se hace mediante
	 * MetodoHistoricoCama/ActualizarHistoria y MetodoHistoricoCama/insertar
	 * y se actualiza la admision se  pone el codigo de la cama nueva en la mediante
	 * MetodoTraslado/ActualizarAdmision lleva como parametro el codigo de la cama vieja 
	 * el codigo de la cama nueva y el codigo de la admision.
	 * tambien se actualiza la cama que estaba ocupada se cambia su estado a libre mediante
	 * MetodoTraslado/ActualizarLibre
	 * y se actualiza la cama a ocupar cambiado su estado a ocupada mediante 
	 * MetodoTraslado/ActualizarOcupada
	 */
		
		MetodoHistoricoCama hc=new MetodoHistoricoCama();
		MetodoTraslado ta= new MetodoTraslado();
		String camaOc=req.getParameter("camaOc");			
		String camaAc=req.getParameter("camaAc");		
		String codigoNueva=req.getParameter("codigoNueva");		
		String codigoVieja=req.getParameter("codigoVieja");		
		String codigoAdmision=req.getParameter("codigo");
		String usuarioIni=req.getParameter("usuario");	
		String FechaFinal=req.getParameter("fecha");	
		String horaFinal="00:00:00";
		String fechaincioNue="0000/00/00";
		String usuarioFin="-1";
		String horaIni=req.getParameter("hora");
		ta.ActualizarLibre(camaAc);
		ta.ActualizarOcupada(camaOc);
		ta.obtenerCodCama(camaAc);
		ta.ActualizarAdmision(codigoVieja, codigoNueva, codigoAdmision);
		///hc.ActualizarHistoria(codigoVieja, codigoAdmision, FechaFinal,horaIni,usuarioIni);
		
		//hc.insertar(codigoNueva, FechaFinal, fechaincioNue, codigoAdmision,horaIni,usuarioIni,horaFinal,usuarioFin);
		res.sendRedirect("Adm_traslado.jsp");
		
	}//fin del doGet
}
