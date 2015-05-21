/**
 * Controlador:Admision
 * Realiza la Admision del los Pacientes si es normalmente o si es por una urgencia vital:Actualiza la cama la pone como estado ocupado y libera cama si cambia de una cama reservada por otra
 * Desarrollado:yosimar valega
 */
package adm_Controlador;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;

import adm_logica.MetodoAdmision;
import adm_logica.MetodoHistoricoCama;
import adm_logica.MetodoPaciente;
import adm_logica.MetodoTraslado;
import adm_logica.MetodoUrgenciaVital;


public class ControlAdmision extends HttpServlet{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**Obtenemos los datos de la fucncion Admisiones de la js Validaciones para realizar la admision
	 *llamamos al procedimeinto insertar/MetodoAdmision para ingresar la admision
	 *Actualizamos la cama con Actualizar/MetodoAdmision
	 *Actualizamos la Urgencia Vital ActualizarUrgencia/MetodoUrgenciaVital
	 *Redireccionamos a ControlHistoricoCama la ingresar la tabla historico-cama.
	 *liberamos cama reservada si hay!!! con liberar/MetodoAdmision
	 *
	 */
	
	public void doGet(HttpServletRequest re, HttpServletResponse res)throws ServletException, IOException{
		res.setContentType("text/html;charset=UTF-8");
		String fecha = re.getParameter("fe");
		String hora = re.getParameter("ho");
		String usuario=re.getParameter("usuario");
		String codcama=re.getParameter("num");		
		
		res.sendRedirect("ControlHistoricoCama?cod="+codcama+"&fe="+fecha+"&ho="+hora+"&usuario="+usuario);	
	
	}
	
	public void doPost(HttpServletRequest re, HttpServletResponse res)throws ServletException, IOException{
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out=res.getWriter();		
		MetodoAdmision ba=new MetodoAdmision();	
		String va=re.getParameter("valor");
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		ResultSet rs3=null;
		ResultSet rs4=null;
		ResultSet rsc=null;
		ResultSet rs5=null;
		/*******************************************CREACION NUEVA ADMISION***********************************/
		String TipoDocumento = re.getParameter("TipoDocumento");
		String NumeroDocumento = re.getParameter("NumeroDocumento");
		if(va.equals("NAD")){		
			try {
				rs=ba.ConsultarPacienteAdmision(TipoDocumento, NumeroDocumento);
				if(rs.next()){
					rs1=ba.ConsultarPacienteAdmisionActiva(rs.getString("pac_codigo_paciente"));
					if(rs1.next()){
						//paciente tiene admision activa.
					}else{
						//se muestra para llenar la admision.
					}
					rs1.getStatement().getConnection().close();
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		
		
		/***************************************FIN CREACION NUEVA ADMISION***********************************/
		
		String codcam=re.getParameter("cod");		
		String nacido=re.getParameter("codna");		
		String ced = re.getParameter("cedu");		
		String tipo=re.getParameter("tipo");
		String numeroautorizacion = re.getParameter("nume");		
		String urgencia = re.getParameter("id");		
		String medioauto = re.getParameter("medio");
		String fecha = re.getParameter("fe");
		String autopor = re.getParameter("auto");
		String hora = re.getParameter("ho");
		String registradopor = re.getParameter("regis");
		String remitidode = re.getParameter("remi");
		String estadoa = re.getParameter("esta");
		String semanac = re.getParameter("se");
		String obs = re.getParameter("ob");		
		String codcama=re.getParameter("num");		
		String contacto=re.getParameter("con");
		String documento=re.getParameter("ane");	
		String usuarioIni=re.getParameter("usuario");
		String dir=re.getParameter("dire");
		String longitud=re.getParameter("long");
		String latitud=re.getParameter("lat");
		String barrio=re.getParameter("bar");
		String codmunicipio=re.getParameter("muni");
	
		String ViaIng="";
		String aurg="";
		String ahosp="";
				
		MetodoUrgenciaVital uv=new MetodoUrgenciaVital();
	
		
		String cod_eps = "", razon_social = "",
		 nit = "", direccion = "",
		telefono = "",ciudad = "",nombre_paciente = "",documentoP = "",
		direccion_p = "",telefono_p = "",
		tipo_afiliacion = "",estrato = "",fecha_ingreso = "",
		num_autorizacion = "",
      	poliza = null;
	
		if(va.equals("1")){
			if(urgencia==null){
				urgencia="0";
				ba.insertar(contacto,codcama,ced,numeroautorizacion, medioauto,autopor,registradopor,fecha,hora,estadoa,semanac,documento,obs,remitidode,nacido,urgencia,tipo);
			}else{
				ba.insertar(contacto,codcama,ced,numeroautorizacion, medioauto,autopor,registradopor,fecha,hora,estadoa,semanac,documento,obs,remitidode,nacido,urgencia,tipo);
			}
		
			if(codcam.equals("")){
			}else{
				ba.liberar(codcam);
			}
			/*********************************************/
			MetodoHistoricoCama hi=new MetodoHistoricoCama();
			MetodoAdmision ad=new MetodoAdmision();		
			String usuarioFin="-1";			
			String fechaFinal="0000-00-00";
			String horaFinal="00:00:00";
			String FechaRegistro,iniin,medin,finin=null;
			iniin=fecha.substring(0,2);			
			medin=fecha.substring(3,5);
			finin=fecha.substring(6,10);
			FechaRegistro=finin+"-"+medin+"-"+iniin;
			/*****************************************************************/
			
			String CodAdmision="";
			String Servicio="";
			String Ubicacion="";
			try {
				rs4=ba.BuscarCodigoCola(ced);
				if(rs4.next()){
					ba.ActualizarColaAdmision(rs4.getString(1));
				}
				rs4.getStatement().getConnection().close();
				
				rs4 = ba.obtenerCodigoPaciente(ced,tipo);			
				String codpac=null;
				if (rs4.next()){codpac = rs4.getString(1);}else{codpac="";}	
				rs4.getStatement().getConnection().close();
				
				rs=ba.obtenerCodigoAdmision(FechaRegistro, hora,codpac);
				if(rs.next()){
					CodAdmision=rs.getString(1);					
					ba.Actualizar(codcama);
					uv.ActualizarUrgencia(urgencia);
					out.print(CodAdmision);		
					MetodoTraslado ta= new MetodoTraslado();
					java.util.Date fechaActualInm = new java.util.Date();
					java.sql.Date FechaRegis = new java.sql.Date(fechaActualInm.getTime());		
					java.sql.Time HoraRegis = new java.sql.Time(fechaActualInm.getTime());
					String UsuarioIngresa=re.getParameter("CodUsuario");
					String NomMedico=re.getParameter("NomMedico");
					String CodMedTra=re.getParameter("CodMedTra");
					rs3=ta.BuscarMedicoTratante(CodAdmision);
					try {
						if(rs3.next()){
							//ya tiene un medico tratante activo
							//se hace un update al resistro que ya tiene
							ta.ActualizarMedicoTratante(rs3.getString(1));
							ta.IngresarMedicoTratante(CodMedTra, NomMedico, CodAdmision,FechaRegis, HoraRegis, UsuarioIngresa);
							//se crea registro nuevo
						}else{
							ta.IngresarMedicoTratante(CodMedTra, NomMedico, CodAdmision,FechaRegis, HoraRegis, UsuarioIngresa);
						}
						rs3.getStatement().getConnection().close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
			/******************************************************************/		
					rs2=ad.obtenerCodigo(codcama);
					String cen_num="";
					
					try{						
						while(rs2.next()){
							cen_num=rs2.getString(1);	
							//Servicio=rs2.getString(2);
							//Ubicacion=rs2.getString(3);
						}
						rs2.getStatement().getConnection().close();
					}catch(Exception ex){
						
					}				
					hi.insertar(cen_num, FechaRegistro, fechaFinal, CodAdmision,hora,usuarioIni,horaFinal,usuarioFin);
					/*********************************************************************/
					
				}else{
					out.print("Error al Ingresar.\n Intente Otra Vez.");
				}
				rs.getStatement().getConnection().close();
				rs1=ba.ConsultarViaIngreso(CodAdmision); 
				if(rs1.next()){
					Servicio=rs1.getString(1);
					Ubicacion=rs1.getString(2);
					//ViaIng=rs1.getString(1);
					System.out.println("Servicio "+Servicio+" Ubicacion "+Ubicacion);
					if(Servicio.equals("1")){
						ViaIng="1";
						aurg="1";
						ba.ActualizarAdmisionUrg(aurg, CodAdmision);
					}else{
						//ViaIng="2";
						if((Ubicacion.equals("CONSULTA EXTERNA"))&&(Servicio.equals("4"))){
							ViaIng="4";
						}else{
							if((!Ubicacion.equals("CONSULTA EXTERNA"))&&(Servicio.equals("4"))){
								ViaIng="3";
							}
							if((Servicio.equals("2"))||(Servicio.equals("3"))){
								ViaIng="2";
							}
						}
						
						ahosp="1";
						ba.ActualizarAdmisionHosp(ahosp, CodAdmision);
					}
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				System.out.print("Error en rs "+e);
				e.printStackTrace();
			}
			
			
			
			try {
				rsc=ba.InsertarEncabezado(CodAdmision);
				if(rsc.next()){
					cod_eps=rsc.getString(1);
	        		razon_social=rsc.getString(2);
	        		nit=rsc.getString(3); 
	        		direccion=rsc.getString(4);
					telefono=rsc.getString(5);
					ciudad=rsc.getString(6);
					nombre_paciente=rsc.getString(7);
					documentoP=rsc.getString(8);
					direccion_p=rsc.getString(9);
					telefono_p=rsc.getString(10);
					tipo_afiliacion=rsc.getString(11);
					estrato=rsc.getString(12);
					fecha_ingreso=rsc.getString(13);
					num_autorizacion=rsc.getString(14);
					poliza=rsc.getString(15);
					
					ba.InserEmcabe(cod_eps, razon_social, nit, direccion, telefono, ciudad, nombre_paciente, documentoP, direccion_p, telefono_p, tipo_afiliacion, estrato, fecha_ingreso, CodAdmision, num_autorizacion,poliza,ViaIng);
				}else{
					out.print("No Se Inserto El Encabezado.");
				}
				
				rsc.getStatement().getConnection().close();
			} catch (SQLException e) {
				System.out.println("Error en rsc.InsertarEncabezado "+e);
				out.println("Error en rsc.InsertarEncabezado "+e);
				e.printStackTrace();
			}
			
		}
		
		if(va.equals("2")){
			 		 
				 MetodoPaciente pc= new MetodoPaciente();
			
			   	pc.ActualizarDireccionAdmision(dir, latitud, longitud, tipo, ced, barrio,codmunicipio);		
		      					         		   
			 	  
			
	      	
			
	    }
	}
}	
	
	
