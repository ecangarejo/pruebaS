package Anexos_Controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Anexos_metodo.MetodoAnexoNo1;

import java.util.GregorianCalendar;
import java.util.Calendar;

public class ControlAnexoNo1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ControlAnexoNo1() {
		super();
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		String tp = req.getParameter("TipoDocumento");
		String num = req.getParameter("NumeroDocumento");
		String va = req.getParameter("valor");

		ResultSet rs = null;
		MetodoAnexoNo1 metodos = new MetodoAnexoNo1();
		ResultSet busqueda_paciente = null;
		ResultSet consultas = null;
		PrintWriter out = res.getWriter();
		String x = req.getParameter("x");
		String tipodocumentopaciente = req.getParameter("TipoDocumento");
		String numeroDocumento = req.getParameter("NumeroDocumento");
		
		String codpaciente = req.getParameter("codpaciente");
		String codusuario = req.getParameter("codusuario");
		String inconsistencia = req.getParameter("tipoinconsistencia");
		String cobertura = req.getParameter("cobertura");
		String papellido = req.getParameter("primerapellido");
		String sapellido = req.getParameter("segundoapellido");
		String pnombre = req.getParameter("primernombre");
		String snombre = req.getParameter("segundonombre");
		String tipodocumento = req.getParameter("tipodocumento");
		String numdocumento = req.getParameter("numerodocumento");
		String fecnacimiento = req.getParameter("fechanacimiento");
		String observacion = req.getParameter("observacion");
			
		Calendar c = new GregorianCalendar();
		String dia = Integer.toString(c.get(Calendar.DATE));
		String mes = Integer.toString(c.get(Calendar.MONTH));
		String annio = Integer.toString(c.get(Calendar.YEAR));
		int m=Integer.parseInt(mes)+1;
		mes=String.valueOf(m);
		int d=Integer.parseInt(dia);
		if(d<10){dia="0"+d;}
		if(m<10){mes="0"+m;}
		String fecha=annio+"-"+mes+"-"+dia; //para la base de dtaos
		//String fechacj=dia+"/"+mes+"/"+annio;
		
		
		
		Calendar calendario = Calendar.getInstance();
		//Calendar calendario = new GregorianCalendar();
			int hora, minutos, segundos;
			hora =calendario.get(Calendar.HOUR_OF_DAY);
			minutos = calendario.get(Calendar.MINUTE);
			segundos = calendario.get(Calendar.SECOND);
			String hra= hora+":"+minutos+":"+segundos;
		
		
		//Muestra el Paciente
		if (va.equals("1")) {
			busqueda_paciente = metodos.BuscarPaciente(tipodocumentopaciente,numeroDocumento);
			String NombreCompleto = "";
			rs = metodos.BuscarPaciente(tp, num);
			int verif = 0;
			String codpac = "0";
			try {
			   if (rs.next()) {
					codpac = rs.getString(1);
					verif = 1;
					if (busqueda_paciente.next()) {
						out.print("1|"); // existe paciente
						out.print(busqueda_paciente.getString(6) + "|");
						out.print("<table width='100%'><tr><td><div align='center' class='style11' id='cabecera2' >DATOS DEL USUARIO (como aparece en la base de datos) </div></td></tr></table>"
										+ "<table border='0'> "
										+ "<tr>"
										+ "<td width='18%' align='center'>" + busqueda_paciente.getString(4)+ "<input type='hidden' name='paciente' id='paciente' value="+busqueda_paciente.getString(16)+" /> </td>"
										+ "<td width='13%' align='center'>" + busqueda_paciente.getString(5)+ "</td>"
										+ "<td width='18%' align='center' colspan='2'>"+busqueda_paciente.getString(3) + "</td>" 
										+ "</tr>"
										+ "<tr>" 
										+ "<td width='18%' align='center'>Primer Apellido </td>"
										+ "<td width='13%' align='center'>Segundo Apellido </td> "
										+ "<td width='18%' align='center' colspan='2'> Nombres </td> "
										+ "</tr>"
										+ "<tr>" 
										+ "<td width='18%'>Tipo de Identificaci&oacute;n: </td>"
										+ "<td width='13%' align='left'>"+ busqueda_paciente.getString(1)+ "</td>"
										+ "<td width='18%'>N&uacute;mero de documento de identificaci&oacute;n: </td> "
										+ "<td width='13%' align='left'>"+ busqueda_paciente.getString(2)+ "</td>"
										+"</tr>"
										+ "<tr>" 
										+ "<td width='18%'>Fecha nacimiento: </td>"
										+ "<td width='13%' align='left'>"+ busqueda_paciente.getString(7)+ "</td>" 
										+ "</tr>" 
										+ "<tr>" 
										+ "<td width='18%'>Direcci&oacute;n de Residencia Habitual: </td>"
										+ "<td width='13%' align='left'>"+ busqueda_paciente.getString(9)+ "</td>"
										+ "<td width='18%'>Telefono: </td> "
										+ "<td width='13%' align='left'>"+ busqueda_paciente.getString(11)+ "</td>"
										+"</tr>"
										+ "<tr>" 
										+ "<td width='18%'>Departamento: </td>"
										+ "<td width='13%' align='left'>"+ busqueda_paciente.getString(13)+ "</td>"
										+ "<td width='18%'>Municipio: </td> "
										+ "<td width='13%' align='left'>"+ busqueda_paciente.getString(12)+ "</td>"
										+"</tr>"
										+ "<tr>" 
										+ "<td width='18%'>Cobertura en salud </td>"
										+"<td width='13%'><select name='cobertura' id='cobertura' class='styletxt' style='width: 155px'>");
								
							if(busqueda_paciente.getString(15)=="Contributivo"){
								out.print("<option value='Regimen Contributivo'>Regimen Contributivo</option>"
										+"<option value='Regimen Subsidiado - total'>Regimen Subsidiado - total</option>"
										+"<option value='Regimen Subsidiado - parcial'>Regimen Subsidiado - parcial</option>"
										+"<option value='Poblacion pobre no Asegurada con SISBEN'>Poblacion pobre no Asegurada con SISBEN</option>"
										+"<option value='Poblacion Pobre no asegurada sin SISBEN'>Poblacion Pobre no asegurada sin SISBEN</option>"
										+"<option value='Desplazado'>Desplazado</option>"
										+"<option value='Plan adicional de salud'>Plan adicional de salud</option>"
										+"</select> <span class='style8'> *</span> </td>"
										+ "</tr>" 
										+ "</table>");
							}else if (busqueda_paciente.getString(15)=="Subsidiado") {
								out.print("<option value='Regimen Subsidiado - total'>Regimen Subsidiado - total</option>"
										+ "<option value='Regimen Contributivo'>Regimen Contributivo</option>"
										+"<option value='Regimen Subsidiado - parcial'>Regimen Subsidiado - parcial</option>"
										+"<option value='Poblacion pobre no Asegurada con SISBEN'>Poblacion pobre no Asegurada con SISBEN</option>"
										+"<option value='Poblacion Pobre no asegurada sin SISBEN'>Poblacion Pobre no asegurada sin SISBEN</option>"
										+"<option value='Desplazado'>Desplazado</option>"
										+"<option value='Plan adicional de salud'>Plan adicional de salud</option>"
										+"</select> <span class='style8'> *</span> </td>"
										+ "</tr>" 
										+ "</table>");
							}else{
								out.print("<option value='Regimen Contributivo'>Regimen Contributivo</option>"
										+"<option value='Regimen Subsidiado - total'>Regimen Subsidiado - total</option>"
										+"<option value='Regimen Subsidiado - parcial'>Regimen Subsidiado - parcial</option>"
										+"<option value='Poblacion pobre no Asegurada con SISBEN'>Poblacion pobre no Asegurada con SISBEN</option>"
										+"<option value='Poblacion Pobre no asegurada sin SISBEN'>Poblacion Pobre no asegurada sin SISBEN</option>"
										+"<option value='Desplazado'>Desplazado</option>"
										+"<option value='Plan adicional de salud'>Plan adicional de salud</option>"
										+"</select> <span class='style8'> *</span> </td>"
										+ "</tr>" 
										+ "</table>");
							}
					}
					busqueda_paciente.getStatement().getConnection().close();
				} else {
					out.print(verif + "|" + codpac);
				}
				rs.getStatement().close();;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(va.equals("2")){
			metodos.InsertarAnexoNo1(codpaciente, codusuario, fecha, hra, inconsistencia, cobertura, papellido, sapellido, pnombre, snombre, tipodocumento, numdocumento, fecnacimiento, observacion);
		}
		
		//Mostrar todos los radicados
		if (va.equals("3")) {
			rs=metodos.consultaranexos(tp,num);
			try {
				out.print("<table width='100%' border='1'><tr><td colspan='6'><div align='center' class='style11' id='cabecera2'>Anexos Tecnicos No 1 </div></td></tr>");
				while(rs.next()){
					out.print("<td><a href='#' onclick='Reporte("+rs.getString(1)+")'>"+rs.getString(2)+" "+rs.getString(3)+"</a></td></tr>");
				}
				out.print("</table>");
				rs.getStatement().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}