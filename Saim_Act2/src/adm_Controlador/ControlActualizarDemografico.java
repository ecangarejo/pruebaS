/**
 * NOMBRE DE LA CLASE: ControlActualizarDemografico
 * AUTOR: Oscar Rolong Schweiger
 * DESCRIPCION: En este Controlador se encuentra lo necesario para hacer la actualizacion
 * 				de los datos demograficos del paciente.
 * 				
 */
package adm_Controlador;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import adm_logica.MetodoActualizarDemografico;


import java.net.URLDecoder;
import java.io.UnsupportedEncodingException;


public class ControlActualizarDemografico extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		/**
		 * se declaran las variables que se van a utilizar y se reciben los datos
		 * por parametros desde el Adm_ActualizarDemografico.jsp
		 */
		res.setContentType("text/html;charset=UTF-8");
		String va = req.getParameter("va");		
		String ced,tipodoc,x,nombrepac = null,papellidos = null,sapellidos = null,y=null;
		String ocupacion = null,municipio = null,entidad = null,afiliacion = null,nivel = null,papellido = null,sapellido = null,nombre = null,genero = null,nacionalidad = null,direccion = null,telfijo = null,telofi = null,telcel = null,empresalab = null,zonares = null,religion = null,estadocv = null,raza = null,estrato = null,email = null,codigopac = null,fechanac = null;
		String dep=null,codocu=null,codmun=null,codentidad=null,longitud=null,latitud=null,barrio=null,codepart=null,codpais=null; 
		MetodoActualizarDemografico mad = new MetodoActualizarDemografico ();
		java.sql.ResultSet rs=null;
		java.sql.ResultSet rs1=null;
		java.sql.ResultSet rs2=null;
		java.sql.ResultSet rs3=null;
		ced = req.getParameter("ced");
		tipodoc=req.getParameter("tipo");
		 System.out.println(ced+" "+tipodoc);
		if(va.equals("0")){
			/**
			 * se obtienen los datos del paciente que se va actualizar
			 * estos se obtienen mediante una consulta MetodoActualizarDemografico/obtenerDatosPaciente
			 * si el resultado es vacio se redirecciona a la misma jsp sino
			 * muestra los datos en pantalla.
			 */
		 try{		 
			 System.out.println("Entro a esta vaina");
			  rs=mad.obtenerDatosPacienteActuaDemogra(ced,tipodoc);
			  if(rs.next()){
				  ocupacion=rs.getString(1);				 
				  municipio=rs.getString(2);				 
				  entidad=rs.getString(3);	
				  tipodoc=rs.getString(4);				 
				  ced=rs.getString(5);				 
				  afiliacion=rs.getString(6);				 
				  nivel=rs.getString(7);				 
				  papellido=rs.getString(8);				  
				  sapellido=rs.getString(9);				  
				  nombre=rs.getString(10);				 
				  genero=rs.getString(11);				
				  nacionalidad=rs.getString(12);				 
				  direccion=rs.getString(13);				
				  telfijo=rs.getString(14);
				  telofi=rs.getString(15);
				  telcel=rs.getString(16);
				  empresalab=rs.getString(17);
				  zonares=rs.getString(18);
				  religion=rs.getString(19);
				  estadocv=rs.getString(20);
				  raza=rs.getString(21);
				  estrato=rs.getString(22);
				  email=rs.getString(23);
				  codigopac=rs.getString(24);
				  fechanac=rs.getString(25);
				  dep=rs.getString(26);
				  codocu=rs.getString(27);
				  codmun=rs.getString(28);
				  codentidad=rs.getString(29); 
				  longitud=rs.getString(30);
				  latitud=rs.getString(31);
				  barrio=rs.getString(32);
				  codepart=rs.getString(33);
				  codpais=rs.getString(34);
				  
				  res.sendRedirect("Adm_ActualizarDemografico.jsp?r=0&ocupacion="+ocupacion+"&municipio="+municipio+"&entidad="+entidad+"&tipodoc="+tipodoc+"&ced="+ced+"&afiliacion="+afiliacion+"&nivel="+nivel+"&papellido="+papellido+"&sapellido="+sapellido+"&nombre="+nombre+"&genero="+genero+"&nacionalidad="+nacionalidad+"&direccion="+java.net.URLEncoder.encode( direccion, "UTF-8")+"&telfijo="+telfijo+"&telofi="+telofi+"&telcel="+telcel+"&empresalab="+empresalab+"&zonares="+zonares+"&religion="+religion+"&estadocv="+estadocv+"&raza="+raza+"&estrato="+estrato+"&email="+email+"&codigopac="+codigopac+"&fechanac="+fechanac+"&dep="+dep+"&codocu="+codocu+"&codmun="+codmun+"&codentidad="+codentidad+"&longitud="+longitud+"&latitud="+latitud+"&bar="+barrio+"&CodDep="+codepart+"&CodPais="+codpais+"");
				  
				  System.out.println("Adm_ActualizarDemografico.jsp?r=0&ocupacion="+ocupacion+"&municipio="+municipio+"&entidad="+entidad+"&tipodoc="+tipodoc+"&ced="+ced+"&afiliacion="+afiliacion+"&nivel="+nivel+"&papellido="+papellido+"&sapellido="+sapellido+"&nombre="+nombre+"&genero="+genero+"&nacionalidad="+nacionalidad+"&direccion="+direccion+"&telfijo="+telfijo+"&telofi="+telofi+"&telcel="+telcel+"&empresalab="+empresalab+"&zonares="+zonares+"&religion="+religion+"&estadocv="+estadocv+"&raza="+raza+"&estrato="+estrato+"&email="+email+"&codigopac="+codigopac+"&fechanac="+fechanac+"&dep="+dep+"&codocu="+codocu+"&codmun="+codmun+"&codentidad="+codentidad+"&longitud="+longitud+"&latitud="+latitud+"&bar="+barrio+"&CodDep="+codepart+"&CodPais="+codpais+"");
			  }
			  else{
				  res.sendRedirect("Adm_ActualizarDemografico.jsp");
			  }
			  rs.getStatement().getConnection().close();
		 }
		 catch(SQLException e1){
			 e1.getMessage();
		 }
		}	 
		 if (va.equals("5")){
			 /**
			  * se obtienen todos los datos a actualizar y se envian al
			  * MetodoActualizarDemografico/Actualizar para su actualizacion.
			  */
			 String codEnt,codpac, tip,ced1, afiliacion1, nivel1, pape, sape, nom, gene,nacionali, dir, tel, telofi1, telcel1, ocu, emp,zona, reli, estadoci, ra, estra, ema, mun_cod, fechanaci, numcontra,dire;
				String NombreCompleto="";
				String DocumentoCompleto="";
			 	codEnt=req.getParameter("codEnt");
				tip=req.getParameter("ti");
				ced1=req.getParameter("ced");
				afiliacion1=req.getParameter("afi");
				nivel1=req.getParameter("ni");
				pape=req.getParameter("pape");				
				sape=req.getParameter("sape");
				nom=req.getParameter("nom");
				gene=req.getParameter("ge");
				nacionali=req.getParameter("naci");
				dir=req.getParameter("dir");				
				tel=req.getParameter("telefi");
				telofi1=req.getParameter("teleofici");
				telcel1=req.getParameter("telecel");
				ocu=req.getParameter("ocu");
				emp=req.getParameter("emp");				
				zona=req.getParameter("zona");
				reli=req.getParameter("re");
				estadoci=req.getParameter("esta");
				ra=req.getParameter("ra");
				estra=req.getParameter("estra");				
				ema=req.getParameter("ema");
				mun_cod=req.getParameter("codmun");
				fechanaci=req.getParameter("fe");
				numcontra=req.getParameter("numco");
				codpac = req.getParameter("codpac");
				NombreCompleto=nom+" "+pape+" "+sape;
				DocumentoCompleto=tip+"-"+ced1;
				longitud=req.getParameter("long");
				latitud=req.getParameter("lati");
				barrio=req.getParameter("bar");
				direccion=req.getParameter("dir");
				//direccion=java.net.URLEncoder.encode( dire, "UTF-8");
				System.out.println("valor del codigo de paciente"+codpac);
				System.out.println("direccion nueva"+" "+direccion);
				if(codEnt!=""){
					rs=mad.obtenerConvenio(codEnt);
					try {
						if(rs.next()){
							codEnt=rs.getString(1);
						}
						rs.getStatement().getConnection().close();
						rs1=mad.ObtenerAdmisionSinFacturar(tip, ced1);
						String CodAdm="";
						String codEntidad=req.getParameter("codEnt");
						while(rs1.next()){
							CodAdm=rs1.getString(1);
							rs2=mad.ObtenerDatosEntidad(codEntidad);
							if(rs2.next()){
								//String CodEntidad= 
								String RazonSocial=rs2.getString(5);
								String Nit=rs2.getString(1);
								String DireccionEntidad=rs2.getString(7);
								String TelefonoEnt=rs2.getString(8);
								String CiudadEnt=rs2.getString(12);
								String CondicionPago="30 Dias";
								rs3=mad.DatosEncabezado(CodAdm);
								
								String nombre_paciente="";String documento="";
								String direccion_p=""; String telefono_p="";String tipo_afiliacion="";
								String fecha_ingreso="";String fecha_egreso="";  String num_autorizacion="";
								if(rs3.next()){
									nombre_paciente=rs3.getString(9);documento=rs3.getString(10);
									direccion_p=rs3.getString(11);telefono_p=rs3.getString(12);
									tipo_afiliacion=rs3.getString(13);fecha_ingreso=rs3.getString(15);
									fecha_egreso=rs3.getString(16);num_autorizacion=rs3.getString(18);
									
									System.out.println("fecha_ingreso"+fecha_ingreso);
								}
								rs3.getStatement().getConnection().close();
								/*mad.CrearEncabezadoFactura(codEntidad, RazonSocial, Nit, DireccionEntidad, TelefonoEnt, CiudadEnt, 
										CondicionPago, nombre_paciente, documento, direccion_p, telefono_p, tipo_afiliacion, estrato, 
										fecha_ingreso, fecha_egreso, CodAdm, num_autorizacion,"2");*/
								//System.out.println();
								mad.ActualizarEncabezadoFactura(codEntidad, RazonSocial, Nit, DireccionEntidad, TelefonoEnt, CiudadEnt, CondicionPago, CodAdm,NombreCompleto,DocumentoCompleto);
								
								/**hacer una consulta con los datos del encabezado anterior
								**consultar cual es la eps nueva con sus datos
								**crear un nuevo encabezado con los datos unidos*/
							}
							rs2.getStatement().getConnection().close();
							
						}
						rs1.getStatement().getConnection().close();
					} catch (SQLException e) {
						System.out.println("Error En El Controlador "+e);
						e.printStackTrace();
					}
					
					mad.Actualizar(tip, ced1, afiliacion1, nivel1, pape, sape, nom, gene, nacionali, direccion, tel, telofi1, telcel1, ocu, emp, zona, reli, estadoci, ra, estra, ema, mun_cod, fechanaci, codEnt,codpac,longitud,latitud,barrio);
										
				}
				
				else{					
					try {
						rs1=mad.ObtenerAdmisionSinFacturar(tip, ced1);
						String CodAdmision="";
						while(rs1.next()){
							CodAdmision=rs1.getString(1);
						}
						mad.ActualizarEncabezadoFacturaDatos(CodAdmision, NombreCompleto, DocumentoCompleto);
						//mad.CrearEncabezadoFactura(cod_eps, razon_social, nit, direccion, telefono, ciudad, condicion_pago, nombre_paciente, documento, direccion_p, telefono_p, tipo_afiliacion, estrato, fecha_ingreso, fecha_egreso, cod_admision, num_autorizacion, cod_usuario_fk, poliza, fecha, hora, tipo, valor, valorletras, anticipos, copago, moderacion, efectivo, estado);
						//mad.ActualizarEncabezadoFactura(codEntidad, RazonSocial, Nit, DireccionEntidad, TelefonoEnt, CiudadEnt, CondicionPago, CodAdm,NombreCompleto,DocumentoCompleto);
						mad.Actualizar(tip, ced1, afiliacion1, nivel1, pape, sape, nom, gene, nacionali, direccion, tel, telofi1, telcel1, ocu, emp, zona, reli, estadoci, ra, estra, ema, mun_cod, fechanaci, numcontra,codpac,longitud,latitud,barrio);	 

						rs1.getStatement().getConnection().close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			 
			 
		 }


		
	}	
	
}
