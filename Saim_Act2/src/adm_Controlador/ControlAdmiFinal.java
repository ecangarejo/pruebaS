/**
 * NOMBRE DE LA CLASE: ControlAdmiFinal
 * AUTOR: Oscar Rolong Schweiger
 * DESCRIPCION: En este Controlador se encuentra lo necesario para 
 * 				finalizar la admision, actualiza la admision,la cama y el historico de la cama 				
 */
package adm_Controlador;

import hic_metodo.MetodoIngresarResultados;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import adm_logica.MetodoAdmision;
import adm_logica.MetodoFinalizarAdmision;
import adm_logica.MetodoHistoricoCama;
import adm_logica.MetodoTraslado;
import pend_metodo.metodoTraslados;


/**
 * Servlet implementation class ControlAdmiFinal
 */
public class ControlAdmiFinal extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	/**
	 * se finaliza la admision haciendo una actualizacion en  
	 * la admision mediante MetodoFinalizarAdmision/ActualizarAdmision
	 * la cama mediante MetodoFinalizarAdmision/ActualizarCama
	 * el historico mediante MetodoFinalizarAdmision/ActualizarHistoria
	 */
		response.setContentType("text/html;charset=UTF-8");
		MetodoFinalizarAdmision fa = new MetodoFinalizarAdmision ();		
		String numingreso="";
		String numcama="";
		String fecha="";
		String hora="";
		String usuario="";
		hora=request.getParameter("hora");
		usuario=request.getParameter("usuario");
		numingreso=request.getParameter("numingreso");
		numcama=request.getParameter("numcama");
		fecha=request.getParameter("fecha");
		java.util.Date fechaActualInm = new java.util.Date();
		java.sql.Date FechaServidor = new java.sql.Date(fechaActualInm.getTime());		
		//java.sql.Time HoraInm = new java.sql.Time(fechaActualInm.getTime());
		fa.ActualizarAdmision(numingreso);
		fa.ActualizarCama(numcama);
		fa.ActualizarHistoria(numcama, numingreso, fecha,hora,usuario);
		fa.ActualizarEncabezadoFechaEgreso(FechaServidor, numingreso);
		response.sendRedirect("Final_Admision.jsp");
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out=res.getWriter();
		
		MetodoFinalizarAdmision fa = new MetodoFinalizarAdmision ();	
		MetodoIngresarResultados mir = new MetodoIngresarResultados ();
		MetodoTraslado ta= new MetodoTraslado();
		metodoTraslados pendtras= new metodoTraslados();
		String numingreso="";
		String numcama="";
		String fecha="";
		String hora="";
		String usuario="";
		String va=req.getParameter("valor");
		String CodDiagnostico=req.getParameter("CodDiagnostico");
		String CodPac=req.getParameter("CodPac");
		hora=req.getParameter("hora");
		usuario=req.getParameter("usuario");
		numingreso=req.getParameter("numingreso");
		numcama=req.getParameter("numcama");
		fecha=req.getParameter("fecha");
		/**********************************************/
		String CodDiagnosticoEgreso=req.getParameter("CodDiagnosticoEgreso");
		String finalidadcons=req.getParameter("finalidadcons");
		String causaexterna=req.getParameter("causaexterna");
		String tipdiag=req.getParameter("tipdiag");
		String EstSalida=req.getParameter("EstSalida");
		String TipoTras=req.getParameter("TipoTras");
		/**
		 * SALIDA DE URGENCIA
		 * TRASLADO
		 * SALIDA DE HOSPITALIZACION
		 * /
		/*******************************************/
		ResultSet rs8=null;
		if(va.equals("1")){
			java.util.Date fechaActualInm = new java.util.Date();
			java.sql.Date FechaServidor = new java.sql.Date(fechaActualInm.getTime());		
			//java.sql.Time HoraInm = new java.sql.Time(fechaActualInm.getTime());
			System.out.println("finalidadcons="+finalidadcons+" causaexterna="+causaexterna+" tipdiag="+tipdiag+" EstSalida="+EstSalida+" TipoTras="+TipoTras);
			/**se actualiza cuando se le da de alta**/
			if((TipoTras.equals("SALIDA DE URGENCIA"))||(TipoTras.equals("SALIDA DE HOSPITALIZACION"))){
				fa.ActualizarAdmision(numingreso);
				fa.ActualizarCama(numcama);
				fa.ActualizarHistoria(numcama, numingreso, fecha,hora,usuario);
				fa.ActualizarEncabezadoFechaEgreso(FechaServidor, numingreso);
			}
			/****************************************/
			
			//System.out.println("ENTROOOOOOOOOOOOOO");
			String CodDiag_fk="";
			String CodDia_eg="";
			
			try {
				rs8=mir.ObtenerCodigoDiagnostico(CodDiagnostico);
				if(rs8.next()){	CodDiag_fk=rs8.getString(1);}
				rs8.getStatement().getConnection().close();
				
				rs8=mir.ObtenerCodigoDiagnostico(CodDiagnosticoEgreso);
				if(rs8.next()){	CodDia_eg=rs8.getString(1);}
				rs8.getStatement().getConnection().close();
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			/***se tiene que validar si es salida de urgencia, salida de hospitalizacion o es para traslado***/
			if(TipoTras.equals("SALIDA DE URGENCIA")){
				mir.IngresarEstadoSalidaDestino("SALIDA DE URGENCIA", EstSalida, usuario, hora, fecha, numingreso, CodPac, tipdiag, causaexterna, finalidadcons);
				mir.IngresarDiagnosticosEgreso(CodDiagnosticoEgreso, "EG", usuario, hora, fecha, numingreso, CodPac,CodDia_eg);
			}
			if(TipoTras.equals("SALIDA DE HOSPITALIZACION")){
				mir.IngresarEstadoSalidaDestino("SALIDA DE HOSPITALIZACION", EstSalida, usuario, hora, fecha, numingreso, CodPac, tipdiag, causaexterna, finalidadcons);
				mir.IngresarDiagnosticosEgreso(CodDiagnosticoEgreso, "EGH", usuario, hora, fecha, numingreso, CodPac,CodDia_eg);
			}
			if(TipoTras.equals("TRASLADO")){
				mir.IngresarEstadoSalidaDestino("HOSPITALIZACION", EstSalida, usuario, hora, fecha, numingreso, CodPac, tipdiag, causaexterna, finalidadcons);
				mir.IngresarDiagnosticosEgreso(CodDiagnosticoEgreso, "EG", usuario, hora, fecha, numingreso, CodPac,CodDia_eg);
			}
			
			/**************************************************************************************************/
			mir.IngresarDiagnosticoIngreso(null, CodDiagnostico, usuario, hora, fecha, numingreso, CodPac,CodDiag_fk);
			/*** si es diagnostico de egreso de urgencia y/o ambulatorio se coloca EG, si es salida de hospitalixzacion se le pone EGH****/
			
			
			
		}
		
		
		
		if(va.equals("1AH")){			
			java.util.Date fechaActualInm = new java.util.Date();
			java.sql.Date FechaServidor = new java.sql.Date(fechaActualInm.getTime());		
			java.sql.Time HoraInm = new java.sql.Time(fechaActualInm.getTime());
			MetodoAdmision ba=new MetodoAdmision();
			MetodoHistoricoCama hi=new MetodoHistoricoCama();
			String NomUsuario=req.getParameter("NomUsuario");
			String CamaNueva=req.getParameter("CamaNueva");
			ResultSet rs=null;
			ResultSet rs1=null;
			/************************************************************************/
			/******************SE CIERRA LA ADMISION AMBULATORIA*********************/
			
			//out.print("numcama="+numcama+" CamaNueva="+CamaNueva+" numingreso="+numingreso);
			fa.ActualizarCama(numcama);
			ba.ActualizarCama(CamaNueva);
			ba.AsignarCamaAdmision(numingreso, CamaNueva);
			ba.ActualizarEncabezado(numingreso);
			//fa.ActualizarHistoria(CamaNueva, numingreso, fecha,hora,usuario);
			
			String CodDiag_fk="";
			try {				
				rs8=mir.ObtenerCodigoDiagnostico(CodDiagnostico);
				//se modifica el traslado pendiente
				rs1 =pendtras.ObtenerPendTransPac(numingreso);
				if (rs1.next()){
					pendtras.actualizarTraslado(numingreso, "1");
				}
				rs1.getStatement().getConnection().close();
				if(rs8.next()){
					CodDiag_fk=rs8.getString(1);
				}
				rs8.getStatement().getConnection().close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			
			mir.IngresarDiagnosticoIngreso(null, CodDiagnostico, usuario, hora, fecha, numingreso, CodPac,CodDiag_fk);
			mir.IngresarDiagnosticosEgreso(CodDiagnostico, "EG", usuario, hora, fecha, numingreso, CodPac,CodDiag_fk);
		/*
			//SE CREA LA NUEVA ADMISION/
			try {
				//CONSULTAR LA ADMISION ANTERIOR
				rs=ba.Datos_Admision(numingreso);
				if(rs.next()){
					//SE CREA LA NUEVA ADMISION
					ba.CrearNuevaAdmision(rs.getString(2), rs.getString(3), rs.getString(4),NomUsuario, FechaServidor, HoraInm, rs.getString(8), "2", rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), CamaNueva, CodPac, rs.getString(16), "0", "0", "0", "0", "1");
				}
				rs.getStatement().getConnection().close();
				rs1=ba.obtenerCodigoAdmision(FechaServidor+"", HoraInm+"");
				String CodAdmision="";
				if(rs1.next()){
					CodAdmision=rs1.getString(1);					
					ba.ActualizarCama(CamaNueva);
					out.print(CodAdmision);		
					ta.IngresarMedicoTratante("479", "CLINICA DE LA COSTA", CodAdmision,FechaServidor, HoraInm, usuario);
					hi.insertar(CamaNueva, FechaServidor+"", "0000-00-00", CodAdmision,HoraInm+"",usuario,"00:00:00","-1");
				}
				rs1.getStatement().getConnection().close();
				
				//SE CREA EL EMCABEZADO DE LA NUEVA ADMISION
				ResultSet rsc=null;
				String cod_eps = "", razon_social = "",
				 nit = "", direccion = "",
				telefono = "",ciudad = "",nombre_paciente = "",documentoP = "",
				direccion_p = "",telefono_p = "",
				tipo_afiliacion = "",estrato = "",fecha_ingreso = "",
				num_autorizacion = "",
				poliza = null;
				
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
					
					ba.InserEmcabe(cod_eps, razon_social, nit, direccion, telefono, ciudad, nombre_paciente, documentoP, direccion_p, telefono_p, tipo_afiliacion, estrato, fecha_ingreso, CodAdmision, num_autorizacion,poliza,"2");
				}else{
					out.print("No Se Inserto El Encabezado.");
				}				
				rsc.getStatement().getConnection().close();				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			/*************************************************************************/
		}
	}
}
