package hic_controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eco_Cardiologia.MetodoTransesofagico;

import java.util.Calendar;
import java.util.GregorianCalendar;
import hic_metodo.MetodoOrdPat;
/**
 * Servlet implementation class ControlOrdPat
 */
public class ControlOrdPat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControlOrdPat() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter(); // respuesta
		String va = request.getParameter("valor");
		MetodoOrdPat metodos = new MetodoOrdPat();
		
		//hora y fecha
		Calendar c = new GregorianCalendar();
		String dia = Integer.toString(c.get(Calendar.DATE));
		String mes = Integer.toString(c.get(Calendar.MONTH));
		String annio = Integer.toString(c.get(Calendar.YEAR));
		int m=Integer.parseInt(mes)+1;
		mes=String.valueOf(m);
		int d=Integer.parseInt(dia);
		if(d<10){dia="0"+d;}
		if(m<10){mes="0"+m;}
		
		String fechacjmysql=annio+"-"+mes+"-"+dia; //para la base de datos

		Calendar calendario = Calendar.getInstance();
		int hora;
		int minutos, segundos;
		hora =calendario.get(Calendar.HOUR_OF_DAY);
		minutos = calendario.get(Calendar.MINUTE);
		segundos = calendario.get(Calendar.SECOND);
		String hracjmysql= hora+":"+minutos+":"+segundos; 
		//fin hora y fecha
		
		if(va.equals("conUlt")){
			//buscar ultimo codigo en la tabla ordenpat_encabezado
			 String codOrdPat = ""+metodos.codigoActualOrdPat();
			 out.print(codOrdPat);	
			 System.out.println("ultimo codigo: "+codOrdPat);
		}
		
if(va.equals("OrdPat")){//guardar informe
			
			String codigoPaciente =  request.getParameter("CodPac");
			String ubicacionPac =  request.getParameter("ubicacionPac");
			String codUsuario =  request.getParameter("codusu");
			String codAdm=request.getParameter("CodAdm");
			String [] observacionesPat =  request.getParameter("observaciones").split("&");
			String [] tipoProcPat =  request.getParameter("tipoProc").split("&");
			boolean insercciones_realizadas_con_exito = false;
			
			System.out.println("datos orden patologia "+codigoPaciente+" "+
					codUsuario+" "+
					codAdm+" "+
					"obs "+observacionesPat.length +" "+
					"proc "+tipoProcPat.length+" "
					);
			//insertar encabezado orden patologia
			insercciones_realizadas_con_exito=metodos.insertarEncabezadoOrdPat(
					fechacjmysql,
					hracjmysql, 
					codUsuario, 
					codigoPaciente, 
					codAdm,
					ubicacionPac,
					"1"
					);
			
			
			//buscar ultimo codigo en la tabla ordenpat_encabezado
			String codOrdPat = ""+metodos.codigoActualOrdPat();
			
			//insertar idOrdPat en ordenpat_observaciones
			insercciones_realizadas_con_exito= metodos.asociarObs(codOrdPat);
			
			//insertar obs en ordenpat_observaciones
			insercciones_realizadas_con_exito=metodos.actualizarObs(codOrdPat, observacionesPat);
			
			//insertar idOrdPat enordenpat_tipo_procedimiento
			insercciones_realizadas_con_exito= metodos.asociarTipoProc(codOrdPat);
			
			//insertar tipo procedimiento en ordenpat_tipo_procedimiento
			insercciones_realizadas_con_exito=metodos.actualizarTipoProc(codOrdPat, tipoProcPat);
			
		
			
			      out.print(codOrdPat);		  
				
			
		}//fin guardar informe
		
		
		//buscar informes por paciente
		/*if (va.equals("3")){
			try {
			busqueda_informe = metodos.buscarInformesDeEcotransRealizados(tipodocumentopaciente, numeroDocumento);
			
			if (busqueda_informe.next()) {
				out.print("<table width='100%' border='1'>" +
						"<tr>" +
						"<td colspan='3'>" +
						"<div align='center' class='style11'id='cabecera2'>" +
						"INFORMES ENCONTRADOS " +
						"</div>" +
						"</td>" +
						"</tr>");
				
				out.print("<tr>" +
						"<td width='13%'>" +
						"Nombre del Paciente:" +
						"</td>" +
						"<td width='87%'>"
						+ busqueda_informe.getString(1) + "" +
						"</td>" +
						"</tr>");
				
				out.print("<tr>" +
						"<td colspan='2'>" +
						"Hora y Fecha Del Informe" +
						"</td>" +
						"</tr>");
				
				do{
					   out.print("<tr>" +
					   "<td colspan='3'>" +
					   "<a href='#' " +
					   "onclick='mostrarInformesEcotrans(" +
					   busqueda_informe.getString(2)+")'>"+
					   busqueda_informe.getString(3) 
					   +"</a>" +
					   "</td>" +
					   "</tr>");

				}while (busqueda_informe.next());
				out.print("</table>");
				
			}else{
				out.print("El Documento: " + tipodocumentopaciente + "-"
						+ numeroDocumento + " No Posee Registros.");
			}
			busqueda_informe.getStatement().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

			
			
		}*/
		
		
		
	}

}
