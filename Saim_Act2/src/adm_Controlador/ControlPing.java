/**
 * Control:ControlPing
 * Se ingresa los datos de los pacientes a atender y se muestra las eps existentes en la base de datos
 * Desarrollado:yosimar valega
 */

package adm_Controlador;
import img_logica.MetodoCitasExamen;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import adm_logica.Conexion;
import adm_logica.MetodoAdmision;
import adm_logica.MetodoPreingreso;
import agm_metodo.MetodoAsignarCita;

import java.sql.*;
import java.util.*;

public class ControlPing extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Se toman los datos para el ingreso de los pacientes a la tabla preingreso de la jsp preingreso/Validaciones
	 * Se ingresa los datos a la base de datos insertar/MetodoPreingreso
	 */
	
	public void doGet(HttpServletRequest re, HttpServletResponse res)throws ServletException, IOException{
		String ced, nom, pape,sape,eps,fecha,hora,tipodoc,estado,genero,FechaNaci;
		estado="0";
	    eps = new String();
		ced = re.getParameter("ced");
		tipodoc=re.getParameter("ti");
		nom = re.getParameter("nom");
		pape = re.getParameter("pape");
		sape = re.getParameter("sape");
		eps = re.getParameter("ep");
		genero=re.getParameter("genero");
		FechaNaci=re.getParameter("FechaNaci");
		/*****************************************/
		String fechapre,ini,med,fin=null;
		ini=FechaNaci.substring(0,2);
		med=FechaNaci.substring(3,5);
		fin=FechaNaci.substring(6,10);
		fechapre=fin+"-"+med+"-"+ini;
		/*****************************************/
		res.setContentType("text/html;charset=UTF-8");		
		/**Elimina los caracteres de ENTER y retorno o salto de linea 
		*si se necesitan se complementa \r \t*/	
		MetodoPreingreso ba = new MetodoPreingreso();		
		fecha =re.getParameter("fe");
		hora =re.getParameter("ho");		
		//ba.insertar(ced, nom, pape,sape,eps,fecha,hora,tipodoc,estado,genero,fechapre);
	}
	
	/**
	 * Se hace una consulta sobre las eps existentes en la base de datos 
	 * Se introducen en el vector v[c]
	 * Este controlador por el void es llamados de la 1.js
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out=res.getWriter();
		ResultSet rs4=null;
		ResultSet rs2=null;
		ResultSet rs5=null;
		ResultSet rs6=null;
		ResultSet rs7=null;
		ResultSet rss=null;
		ResultSet rss1=null;
		String TipoDocumento=req.getParameter("TipoDocumento");
		String NumeroDocumento=req.getParameter("NumeroDocumento");
		String CodPac = req.getParameter("CodPac");
		String CodUsu = req.getParameter("CodUsu");
		String tipo = req.getParameter("Servicio");
		String va = req.getParameter("va");
		MetodoAsignarCita mac = new MetodoAsignarCita();
		MetodoCitasExamen mce = new MetodoCitasExamen();
		//String eps = new String();
		MetodoPreingreso ba = new MetodoPreingreso();
		//ResultSet rs=null;
		if(va.equals("p1")){
			rss=mac.BuscarPaciente(TipoDocumento, NumeroDocumento);
			try {
				if(rss.next()){
					out.print("<table border='0' width='100%' ><tr><td colspan='6'><div id='cabecera2' class='style11' align='center'>INGRESO AMBULATORIO </div></td></tr>");
					out.print("<tr><td width='9%' class='style12'>Nombre Paciente<input name='txtCodPac' type='hidden' id='txtCodPac' value="+rss.getString(1)+"></td><td width='34%'>"+rss.getString(2)+" "+rss.getString(3)+" "+rss.getString(4)+"</td><td width='5%' class='style12'>Entidad</td><td width='28%'>"+rss.getString(5)+"</td>");
					
					/*out.print("<td width='6%'><span class='style12'>Servicio</span></td> <td width='18%' class='style12'><select name='cmbServicio' id='cmbServicio'><option value='Seleccione' selected='selected'>Seleccione</option>");

					rss1=ba.ObtenerServicios();
					while(rss1.next()){
					out.print("<option value="+rss1.getString(1)+">"+rss1.getString(2)+"</option>");
					}
					rss1.getStatement().getConnection().close();*/

					out.print("</select></td></tr><tr><td colspan='6' class='style12' align='center'>");
					out.print("<input name='btnGuardar' type='button' class='boton4' id='btnGuardar' value='     Asignar     ' onclick='GuardarAmbulatorio()'></td></tr></table>");
					
				}else{
					out.print("<table width='100%' class='style6'><tr><td colspan='4' id='cabecera2'><div align='center' class='style11'> DATOS DEMOGRAFICOS </div></td></tr>");

					out.print("<tr><td width='18%'><span class='Estilo8'>Primer Apellido</span></td><td width='26%'><label><input name='txtpapellido' type='text' id='txtpapellido' onkeyup='this.value=this.value.toUpperCase();'  maxlength='20'  /><span class='style8'>*</span></label></td><td width='16%'><span class='Estilo8'>Segundo Apellido</span></td><td width='40'><label><input name='txtsapellido' type='text' id='txtsapellido' onkeyup='this.value=this.value.toUpperCase();'   maxlength='20'  /></label></td></tr>");

					out.print("<tr><td><span class='Estilo8'>Nombres</span></td><td><label><input name='txtnombre' type='text' id='txtnombre' onkeyup='this.value=this.value.toUpperCase();'  /><span class='style8'>*</span></label></td><td><span class='Estilo8'>Fecha Nacimiento(ddmmaaaa)</span></td><td><label><input type='text' name='txtfechanaci' id='txtfechanaci'   onkeyup='masca(this,&quot;/&quot;,patron,true)' maxlength='10'/><span class='style8'>*</span></label></td></tr>");
					out.print("<tr><td>Estado Civil</td><td> <select id='cmbEstadoCivil'><option value='Seleccione' selected='' >Seleccione</option><option value='Soltero(a)'>Soltero(a)</option><option value='Casado(a)'>Casado(a)</option><option value='Viudo(a)'>Viudo(a)</option></select></td></tr>");
					out.print("<tr class='Estilo8'><td>Tipo Afiliacion</td><td><select id='cmbTipoAfiliacion'><option value='Seleccione' selected='' >Seleccione</option><option value='Beneficiario'>Beneficiario</option><option value='Cotizante'>Cotizante</option><option value='Particular'>Particular</option></select><span class='style8'>*</span></td><td>Nivel Cotizante</td><td><select id='cmbNivelCotizante'><option value='Seleccione' selected='' >Seleccione</option><option value='I'>I</option><option value='II'>II</option><option value='III'>III</option></select><span class='style8'>*</span></td></tr>");
					
					out.print("<tr><td><span class='Estilo8'>Departamento</span></td><td><select name='cbdep' size='1' id='cbdep' onChange='CargarMunicipio()'><option selected='selected'>Seleccione</option>");
					rs2=mac.Departamentos();
					while(rs2.next()){
						out.print("<option value="+rs2.getString(1)+" >"+rs2.getString(2)+"</option>");
					}
					rs2.getStatement().getConnection().close();
					out.print("</select><span class='style8'>*</span></td><td><span class='Estilo8'>Municipio</span></td><td><label><div id='Municipio'><select name='cbmun' size='1' id='cbmun' ><option value='Seleccione' selected='selected'>Seleccione</option></select></div><span class='style5'>*</span></label></td></tr>");
					 
					out.print("<tr><td><span class='Estilo8'>Genero</span></td><td><label><select name='select4' id='cmbGenero' ><option selected='selected' value='Seleccione'>Seleccione</option><option value='Masculino' >Masculino</option><option value='Femenino' >Femenino</option></select><span class='style8'>*</span></label></td><td>E-mail</td><td><span class='style8'><input type='text' name='txtEmail' id='txtEmail' />*</span></td></tr>");
					  
					out.print("<tr><td><span class='Estilo8'>Telefono </span></td><td><input name='txtcelular' type='text' id='txtcelular'   maxlength='20'/><input name='txtTelFijo' type='text' id='txtTelFijo'   maxlength='20'/><input name='txtTelOfi' type='text' id='txtTelOfi'   maxlength='20'/></td><td><span class='Estilo8'>Direccion</span></td><td><label><input name='txtdire' type='text' id='txtdire' maxlength='50'/></label></td></tr>");
					  
					out.print("<tr><td><span class='Estilo8'>Nombre Entidad </span></td><td colspan='3'><label></label><label><input name='cbeps' type='text' id='cbeps' size='80' maxlength='300' onkeyup='autocompleta_nombre()' /><span class='style8'>*</span></label></td></tr>");
					  
					out.print("<tr><td>&nbsp;</td><td colspan='3'><div id='sugerencias'></div></td></tr>");
					  
					out.print("<tr><td colspan='2' class='style8'>Campos Requeridos* </td><td>&nbsp;</td> <td>&nbsp;</td></tr><tr><td>&nbsp;</td><td><label><input name='txtcodmun' type='hidden' id='txtcodmun' /><input name='txtcodentidad' type='hidden' id='txtcodentidad'  /> </label></td><td><label><input type='button' name='btingresar' class='boton4' id='btingresar' value='     Ingresar    ' onclick='GuardarDemograficoCERP()' /></label></td></tr></table>");

				}
				rss.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			}
		
		if(va.equals("p2")){
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
			java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());	
			try{
				rss=mac.BuscarPaciente(TipoDocumento, NumeroDocumento);
				if(rss.next()){
					ba.insertar(NumeroDocumento, rss.getString(2), rss.getString(3),rss.getString(4),rss.getString(5),Fecha,Hora,TipoDocumento,"0",rss.getString(6),rss.getString(7));
					System.out.println(NumeroDocumento+"->"+rss.getString(2)+"->"+rss.getString(3)+"->"+rss.getString(4)+"->"+rss.getString(5)+"->"+Fecha+"->"+Hora+"->"+TipoDocumento+"->"+"0"+"->"+rss.getString(6)+"->"+rss.getString(7));
					out.print("ingreso exitoso");
				}
				rss.getStatement().getConnection().close();							
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			/*****************************************************************/
		}
	/*	rs=ba.SQLEntidad();
		String v[] = new String[1000];
		char ee=((char)13);
		try{
			while(eps.charAt(0)==ee){
				
				 eps=eps.substring(1, eps.length());
			}
		while(eps.charAt(0)=='\n'){
			
			 eps=eps.substring(1, eps.length());
		}
		
		}catch(Exception e){
			}
		
		try {
			int c=0;
			while(rs.next()){				
				v[c]=rs.getString(1);				
				out.println(v[c]+"_");
				c++;
			}
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {			
			e.printStackTrace();
		} */
		
		
		
	}
	
}
