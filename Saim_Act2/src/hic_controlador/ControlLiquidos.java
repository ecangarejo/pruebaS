package hic_controlador;



import fact_metodo.MetodoSeguimientoAdmision;
import hic_metodo.MetodoIngresarResultados;
import hic_metodo.MetodoLaboratoriosHistoria;
import hic_metodo.MetodoVerFormatos;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Vector;
import java.util.regex.Pattern;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;
/**
 * Servlet implementation class ControlLiquidos
 */
public class ControlLiquidos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControlLiquidos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		/**
		 * autocompletar de los formatos, salen todas las coincidencias de los formatos
		 * existentes en la base de datos.
		 */
			res.setContentType("text/html;charset=UTF-8");
			ResultSet rs=null;	
			String cad =req.getParameter("codigo");  
			String CodigoUsuario=req.getParameter("CodigoUsuario");
			MetodoVerFormatos mvf = new MetodoVerFormatos ();

		 int accion;
	     
	        accion = Integer.parseInt(req.getParameter("txtAccion"));
	if(accion == 26){          

	        try {
	            rs =mvf.listarParaAutocompletarFormato(cad,CodigoUsuario);
	            String cadena ="";
	            String nombre ="";
	            cadena="[";
	            while(rs.next()){
	            	nombre=rs.getString(2);
	            	cadena = cadena+"'"+nombre+"|"+rs.getString(1)+"'";
	            	cadena = cadena +",";	
	            }
	            cadena = cadena+"]";
	            res.getWriter().write(cadena);
	            rs.getStatement().getConnection().close();
	        } catch (Exception e) {
	            e.getMessage();
				e.printStackTrace();
				}

	    }
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String CodFormato=req.getParameter("CodFormato");
		String CodArea=req.getParameter("CodArea");
		String CodPac=req.getParameter("CodPac");
		String CodAdm=req.getParameter("CodAdm");
		String CodUsuario=req.getParameter("CodUsuario");
		String HoraFormato=req.getParameter("HoraFormato");
		String FechaFormato=req.getParameter("FechaFormato");
		String CodMedicamento=req.getParameter("CodMedicamento");
		String va=req.getParameter("valor");
		MetodoVerFormatos mvf = new MetodoVerFormatos ();
		MetodoLaboratoriosHistoria mlh=new MetodoLaboratoriosHistoria();
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		ResultSet rs3=null;
		ResultSet rs4=null;
		ResultSet rs5=null;
		ResultSet rs6=null;
	
		
		String codPac=req.getParameter("codPac");
		String codAdm=req.getParameter("codAdm");
		String observacion=req.getParameter("observacion");
		String DetOrden=req.getParameter("DetOrden");
		String Codusuario=req.getParameter("usuario");
		String codCama=req.getParameter("codCama");
		String codArea=req.getParameter("codArea");
		String codSubarea=req.getParameter("codSubarea");
		MetodoSeguimientoAdmision msa =new MetodoSeguimientoAdmision();
		//////////////////////////////////////////////
		String codFormulacion_fk=req.getParameter("codFormulacion_fk");
		String codigoMed=req.getParameter("codigoMed");
		String cantidad=req.getParameter("cantidad");
		String dosificacion=req.getParameter("dosificacion");
		//////////////////////////////////////////////
		String codDetFormulacion_fk="";
		//req.getParameter("codDetFormulacion_fk");
		//////////////////////////////////////////////
		String VigeTto=req.getParameter("VigeTto");
		
		int contador=0;
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out=res.getWriter();
		
		
		Calendar c = new GregorianCalendar();
		String diac = Integer.toString(c.get(Calendar.DATE));
		String mesc = Integer.toString(c.get(Calendar.MONTH));
		String annio = Integer.toString(c.get(Calendar.YEAR));
		int m=Integer.parseInt(mesc)+1;
		mesc=String.valueOf(m);
		int d=Integer.parseInt(diac);
		if(d<10){diac="0"+d;}
		if(m<10){mesc="0"+m;}
		
		String fechacjmysql=annio+"-"+mesc+"-"+diac; //para la base de datos

		Calendar calendario = Calendar.getInstance();
		int hora;
		int minutos, segundos;
		hora =calendario.get(Calendar.HOUR_OF_DAY);
		minutos = calendario.get(Calendar.MINUTE);
		segundos = calendario.get(Calendar.SECOND);
		String hracjmysql= hora+":"+minutos+":"+segundos; 
		
		
		/*************************************************************************************************************************/
		String NombreMedicamento=req.getParameter("NombreMedicamento");
		//String FormaFarmaceutica=req.getParameter("FormaFarmaceutica");
		String CodFormaFarmaceutica=req.getParameter("CodFormaFarmaceutica");
		String Concentracion=req.getParameter("Concentracion");
		String UnidadMed=req.getParameter("Unidad");
		String ViaAdm=req.getParameter("ViaAdm");
		String LapsoMed=req.getParameter("LapsoMed");
		String CantidadMedicamento=req.getParameter("CantidadMedicamento");
		String CodPacK=req.getParameter("CodPacK");
		String CodAdmK=req.getParameter("CodAdmK");
		String CodUsuK=req.getParameter("CodUsuK");
		String CodCamaK=req.getParameter("CodCamaK");
		String CodAreaK=req.getParameter("CodAreaK");
		String CodSubareaK=req.getParameter("CodSubareaK");
		String FrecuenciaK=req.getParameter("FrecuenciaK");
		String DosisK=req.getParameter("DosisK");
		String NomUnidad=req.getParameter("NomUnidad");
		String PesoK=req.getParameter("PesoK");
		String TallaK=req.getParameter("TallaK");
		String ObserInfu=req.getParameter("ObserInfu");
		
		//variables entrada salidas
		
		String nom_text=req.getParameter("nom_text");
		String contenido_text=req.getParameter("contenido_text");
		ResultSet busqueda_ent_sal = null;
		ResultSet busqueda_sv = null;
		boolean insercciones_realizadas_con_exito = false;
		
		if(va.equals("guardarEntSal")){
			String FechaKardex=req.getParameter("FechaKardex");
			
			//busqueda_ent_sal = mvf.buscarEntSal(nom_text, FechaKardex, codAdm);
			
			
try {
				
				if (busqueda_ent_sal.next()) {
					/* insercciones_realizadas_con_exito=mvf.modificarHicEntSal(
							    nom_text,
							    contenido_text,
							    Codusuario,
								fechacjmysql,
								hracjmysql, 
								codAdm,
								FechaKardex
								);*/

				} else {
					 /*insercciones_realizadas_con_exito=mvf.insertarHicEntSal (
							    nom_text,
							    contenido_text,
							    codPac,
							    Codusuario,
								fechacjmysql,
								hracjmysql, 
								codAdm,
								"1", 
								FechaKardex
								);*/
				}
				busqueda_ent_sal.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
			 if(insercciones_realizadas_con_exito == true){
			      out.print("1;insercion exitosa");		  
				  }else{
				  out.print("0;Hubo un error al ingresar los datos al sistema!");
				  }
			
		}
		

		if(va.equals("guardarDesocultarSv")){
			
			System.out.println("guardardesocultar");	
			String FechaKardex=req.getParameter("FechaKardex");
			String nom_desocultar=req.getParameter("desocultar");
			
			busqueda_sv = mvf.buscarDesocultarSv(nom_desocultar, FechaKardex, codAdm);
			
			
try {
				
				if (busqueda_sv.next()) {
					System.out.println("ya se encuentra ingresado el sv desocultar");	

				} else {
					 insercciones_realizadas_con_exito=mvf.insertarDesocultarSv (
							    nom_desocultar,
							    codPac,
							    Codusuario,
								fechacjmysql,
								hracjmysql, 
								codAdm,
								FechaKardex,
								"1"
								
								);
				}
				busqueda_sv.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
			 if(insercciones_realizadas_con_exito == true){
			      out.print("1;insercion exitosa");		  
				  }else{
				  out.print("0;Hubo un error al ingresar los datos al sistema!");
				  }
			
		}
		

		
		if(va.equals("guardarHc")){
			String FechaKardex=req.getParameter("FechaKardex");
			
			busqueda_sv = mvf.buscarSv(nom_text, FechaKardex, codAdm);
			
			
try {
				
				if (busqueda_sv.next()) {
					 insercciones_realizadas_con_exito=mvf.modificarSv(
							    nom_text,
							    contenido_text,
							    Codusuario,
								fechacjmysql,
								hracjmysql, 
								codAdm,
								FechaKardex
								);

				} else {
					 insercciones_realizadas_con_exito=mvf.insertarSv (
							    nom_text,
							    contenido_text,
							    codPac,
							    Codusuario,
								fechacjmysql,
								hracjmysql, 
								codAdm,
								FechaKardex,
								"1"
								
								);
				}
				busqueda_sv.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
			 if(insercciones_realizadas_con_exito == true){
			      out.print("1;insercion exitosa");		  
				  }else{
				  out.print("0;Hubo un error al ingresar los datos al sistema!");
				  }
			
		}
		
		
		
		if(va.equals("VerEntSal")){
			//if(va.equals("8")){		
				
				try {
					java.util.Date fechaActual = new java.util.Date();
					java.sql.Date fecha = new java.sql.Date(fechaActual.getTime());	
					
					///////////////////
					 Calendar c1 = GregorianCalendar.getInstance();
				        System.out.println("Fecha actual: " + c1.getTime().toLocaleString());
				        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				        sdf = new SimpleDateFormat("yyyy-MM-dd");
				        c1.add(Calendar.DATE, 1);
					/////////////////
					String Pes="";
					String Tal="";
					//out.print("<table><tr><td></td></tr></table>");
					rs2=mvf.ObtenerPesoTalla(CodAdmK);
					if(rs2.next()){
						Pes=rs2.getString(3);
						Tal=rs2.getString(4);
						
						//out.print("<table><tr><td>Peso <input name='txtPeso' size='5' type='text' onkeyup='SoloNumeros(form1.txtPeso)' value='"+Pes+"' /> (En Kg. EJ:56)</td> " +
						//		"<td>Talla <input name='txtTalla' size='5' type='text' onkeyup='SoloNumeros(form1.txtTalla)' value='"+Tal+"'  /> (En Mts. EJ:1.68)</td><td></tr></table>");
					}else{
					//	out.print("<table><tr><td>Peso <input name='txtPeso' size='5' type='text' onkeyup='SoloNumeros(form1.txtPeso)' /> (En Kg. EJ:56)</td> " +
						//		"<td>Talla <input name='txtTalla' size='5' type='text' onkeyup='SoloNumeros(form1.txtTalla)'  /> (En Mts. EJ:1.68)</td><td><input name='btnCrear' type='button' class='boton4' id='btnCrear' value='Crear' onClick='CrearPT()'></td></tr></table>");
					}
					rs2.getStatement().getConnection().close();
					
					/////////////////////////////////////////////////////
					out.print("<table width='100%' border='0' >");
					out.print("<tr><input type='hidden' id='txtPerfil' value='0' ><td id='DetalleKardex'>");
					
					java.util.Calendar hoy = java.util.Calendar.getInstance();
					java.util.Date primerDia= hoy.getTime();
					int diasMes[]= new int[12];
					diasMes[0] = 31;diasMes[1] = 28;diasMes[2] = 31;diasMes[3] = 30;diasMes[4] = 31;diasMes[5] = 30;
					diasMes[6] = 31;diasMes[7] = 31;diasMes[8] = 30;diasMes[9] = 31;diasMes[10] = 30;diasMes[11] = 31;

					int dia;int anio;int columna;int nDias;int diaInicial;int i;int mes;
					String NombreMes = "";
					anio=hoy.get(java.util.Calendar.YEAR);
					dia=hoy.get(java.util.Calendar.DATE);
					mes=hoy.get(java.util.Calendar.MONTH);
					mes=mes+1;
					if(mes==1){NombreMes="Enero";}if(mes==2){NombreMes="Febrero";}if(mes==3){NombreMes="Marzo";}
					if(mes==4){NombreMes="Abril";}if(mes==5){NombreMes="Mayo";}if(mes==6){NombreMes="Junio";}
					if(mes==7){NombreMes="Julio";}if(mes==8){NombreMes="Agosto";}if(mes==9){NombreMes="Septiembre";}
					if(mes==10){NombreMes="Octubre";}if(mes==11){NombreMes="Noviembre";}if(mes==12){NombreMes="Diciembre";}

					if(((anio%4==0) && (anio%100!=0))||(anio%400==0)) 
					{
						diasMes[1] = 29; //y si lo es var�o el n�mero de d�as del mes de febrero en el array
					}
					nDias=diasMes[hoy.get(java.util.Calendar.MONTH)];		
					primerDia.setDate(1);
					diaInicial=primerDia.getDay();
					
					out.print("<table width='100%' border='1'>");
					out.print("<tr><th colspan=41 align=center>"+NombreMes+" Del "+anio+" "); 		
					out.print("<tr bgcolor='#CCCCCC'>");
					out.print("<th align=center>D");out.print("<th align=center>L");out.print("<th align=center>M");
					out.print("<th align=center>M");out.print("<th align=center>J");out.print("<th align=center>V");
					out.print("<th align=center>S");out.print("<th align=center>D");out.print("<th align=center>L");
					out.print("<th align=center>M");out.print("<th align=center>M");out.print("<th align=center>J");
					out.print("<th align=center>V");out.print("<th align=center>S");out.print("<th align=center>D");
					out.print("<th align=center>L");out.print("<th align=center>M");out.print("<th align=center>M");
					out.print("<th align=center>J");out.print("<th align=center>V");out.print("<th align=center>S");
					out.print("<th align=center>D");out.print("<th align=center>L");out.print("<th align=center>M");
					out.print("<th align=center>M");out.print("<th align=center>J");out.print("<th align=center>V");
					out.print("<th align=center>S");out.print("<th align=center>D");out.print("<th align=center>L");
					out.print("<th align=center>M");out.print("<th align=center>M");out.print("<th align=center>J");
					out.print("<th align=center>V");out.print("<th align=center>S");out.print("<th align=center>D");
					out.print("<th align=center>L");
					out.print("</tr>");
					out.print("<tr></tr>");
					columna=0;
					for(i=0;i<diaInicial;i++) 
					{
						out.print("<td align=center><font size+=4>.");
						columna++;
						out.print("</font>");
					}
					int a=0;
					for(i=1;i<=nDias;i++) 
					{
						a=a+1;
						out.print("<td align=center>");				
						if(i==dia) 
						{
							String valueF=anio+"-"+mes+"-"+i;
							out.print("<b>"+i+"</b>");
							out.print("<font color=\"#ff0000\" size+=7><b>");
							out.print("<input name='radiobutto' type='radio' value='radiobutto' checked='true' id='f' onclick='RadioForLiq(&quot;"+valueF+"&quot;,"+CodAdmK+")' />");
							out.print("</b></font>"); 
						}else{
							String valueF=anio+"-"+mes+"-"+i;
							out.print(i);
							out.print("<input name='radiobutto' type='radio' value='radiobutto' id='f' onclick='RadioForLiq(&quot;"+valueF+"&quot;,"+CodAdmK+")' />");
						}
					}
					out.print("</table>");
					out.print("<table width='100%' border='1' ><tr><td width='4%' ><input type='hidden' id='txtFechaActuKard' value="+fecha+" ><input type='hidden' id='txtAdmisionCKR' value="+CodAdmK+" ></td></tr></table>");

					try {
						String FechaKardex=req.getParameter("FechaKardex");
						String FechaKardex2=req.getParameter("FechaKardex");
						String CodAdmK1=req.getParameter("CodAdmK");
						
						rs2=mvf.ObtenerCodigoFormulacionKardesSeleccionado(FechaKardex+"", CodAdmK1,"2");
						String CodFormK="";
						String CodDetalleFormulacionK="";
						if(rs2.next()){
							CodFormK=rs2.getString(1);
							
							
							
						//carga las horas enfermeria
							out.print("<table width='100%'><tr><td><div id='CarguesDiaKardex'><input type='hidden' id='txtFechaKardexActivo' value='"+FechaKardex+"'> ");
							out.print("<table width='100%' border='0' ><tr><td width='30%'><div id='DatosEmerg'>-</div></td>" +
									"<td width='100%'><table width='100%' border='0' ><tr><td bgcolor='#E6E6E6' colspan='17' align='center'>"+fecha+"</td><td colspan='7' bgcolor='#E6E6E6' align='center'>"+sdf.format(c1.getTime())+"</td></tr><tr></td>" +
							"<td width='2.8%'>07</td>" +"<td width='2.8%'>08</td>" +"<td width='2.8%'>09</td>" +
							"<td width='2.8%'>10</td>" +"<td width='2.8%'>11</td>" +"<td width='2.8%'>12</td>" +"<td width='2.8%'>13</td>" +
							"<td width='2.8%'>T</td>" +"<td width='2.8%'>14</td>" +"<td width='2.8%'>15</td>" +"<td width='2.8%'>16</td>" +"<td width='2.8%'>17</td>" +
							"<td width='2.8%'>18</td>" +"<td width='2.8%'>19</td>" +"<td width='2.8%'>T</td>" +"<td width='2.8%'>20</td>" +"<td width='2.8%'>21</td>" +
							"<td width='2.8%'>22</td>" +"<td width='2.8%'>23</td>" +"<td width='2.8%'>00</td>" +"<td width='2.8%'>01</td>" +"<td width='2.8%'>02</td>" +
							"<td width='2.8%'>03</td>" +"<td width='2.8%'>04</td>" +"<td width='2.8%'>05</td>" +"<td width='2.8%'>06</td>" +"<td width='2.8%'>T</td>" +													
							"</tr></table></td></tr>" +
							
							// liquidos salidas precargado todos los dias 
							"<tr bgcolor='#FA5858'> <td width='100%'><div id='salidas'>SALIDAS</div></td>" +
							"<td>" +
							"<input id='nom_drenaje' type='text' style='border: 1px solid #585858'/>"+
							"<input type='button' id='aparecer_drenajes' value='Agregar Drenaje' onclick='actualizar_ult_drenaje()' /></td>"+
							"</tr>"+	
							//orina
							"<tr> <td width='30%'><div id='orina'>ORINA</div></td>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='orn7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(orn7,tsal7,7)'; onblur='prueba(orn7)'/></td>" 
							+"<td  width='2.8%'><input id='orn8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(orn8,tsal8,8)'; onblur='prueba(orn8)'/></td>" 
							+"<td  width='2.8%'><input id='orn9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(orn9,tsal9,9)'; onblur='prueba(orn9)'/></td>" 
							+"<td  width='2.8%'><input id='orn10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(orn10,tsal10,10)'; onblur='prueba(orn10)'/></td>" 
							+"<td  width='2.8%'><input id='orn11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(orn11,tsal11,11)'; onblur='prueba(orn11)'/></td>" 
							+"<td  width='2.8%'><input id='orn12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(orn12,tsal12,12)'; onblur='prueba(orn12)'/></td>"
							+"<td  width='2.8%'><input id='orn13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(orn13,tsal13,13)'; onblur='prueba(orn13)'/></td>" 
							+"<td  width='2.8%'><input id='ornt1' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='orn14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(orn14,tsal14,14)'; onblur='prueba(orn14)'/></td>" 
							+"<td  width='2.8%'><input id='orn15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(orn15,tsal15,15)'; onblur='prueba(orn15)'/></td>" 
							+"<td  width='2.8%'><input id='orn16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(orn16,tsal16,16)'; onblur='prueba(orn16)'/></td>" 
							+"<td  width='2.8%'><input id='orn17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(orn17,tsal17,17)'; onblur='prueba(orn17)'/></td>" 
							+"<td  width='2.8%'><input id='orn18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(orn18,tsal18,18)'; onblur='prueba(orn18)'/></td>" 
							+"<td  width='2.8%'><input id='orn19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(orn19,tsal19,19)'; onblur='prueba(orn19)'/></td>" 
							+"<td  width='2.8%'><input id='ornt2' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='orn20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(orn20,tsal20,20)'; onblur='prueba(orn20)'/></td>" 
							+"<td  width='2.8%'><input id='orn21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(orn21,tsal21,21)'; onblur='prueba(orn21)'/></td>" 
							+"<td  width='2.8%'><input id='orn22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(orn22,tsal22,22)'; onblur='prueba(orn22)'/></td>" 
							+"<td  width='2.8%'><input id='orn23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(orn23,tsal23,23)'; onblur='prueba(orn23)'/></td>" 
							+"<td  width='2.8%'><input id='orn00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(orn00,tsal00,00)'; onblur='prueba(orn00)'/></td>" 
							+"<td  width='2.8%'><input id='orn01' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(orn01,tsal01,01)'; onblur='prueba(orn01)'/></td>" 
							+"<td  width='2.8%'><input id='orn02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(orn02,tsal02,02)'; onblur='prueba(orn02)'/></td>" 
							+"<td  width='2.8%'><input id='orn03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(orn03,tsal03,03)'; onblur='prueba(orn03)'/></td>" 
							+"<td  width='2.8%'><input id='orn04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(orn04,tsal04,04)'; onblur='prueba(orn04)'/></td>" 
							+"<td  width='2.8%'><input id='orn05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(orn05,tsal05,05)'; onblur='prueba(orn05)'/></td>" 
							+"<td  width='2.8%'><input id='orn06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(orn06,tsal06',06)'; onblur='prueba(orn06)'/></td>"													
							+"<td  width='2.8%'><input id='ornt3' type='text' size='1' disabled='disabled'/></td>" 
							+"</tr></table></td></tr>" +		
							//sondas
							"<tr> <td width='30%'><div id='sondas'>SONDAS</div>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='sondsal7' type='text' style='border: 1px solid #585858' size='1'  onkeyup='soloNumeros(sondsal7,tsal7,7)'; onblur='prueba(sondsal7)'/></td>" 
							+"<td  width='2.8%'><input id='sondsal8' type='text' style='border: 1px solid #585858' size='1'  onkeyup='soloNumeros(sondsal8,tsal8,8)'; onblur='prueba(sondsal8)'/></td>" 
							+"<td  width='2.8%'><input id='sondsal9' type='text' style='border: 1px solid #585858' size='1'  onkeyup='soloNumeros(sondsal9,tsal9,9)'; onblur='prueba(sondsal9)'/></td>" 
							+"<td  width='2.8%'><input id='sondsal10' type='text' style='border: 1px solid #585858' size='1'  onkeyup='soloNumeros(sondsal10,tsal10,10)'; onblur='prueba(sondsal10)'/></td>" 
							+"<td  width='2.8%'><input id='sondsal11' type='text' style='border: 1px solid #585858' size='1'  onkeyup='soloNumeros(sondsal11,tsal11,11)'; onblur='prueba(sondsal11)'/></td>" 
							+"<td  width='2.8%'><input id='sondsal12' type='text' style='border: 1px solid #585858' size='1'  onkeyup='soloNumeros(sondsal12,tsal12,12)'; onblur='prueba(sondsal12)'/></td>"
							+"<td  width='2.8%'><input id='sondsal13' type='text' style='border: 1px solid #585858' size='1'  onkeyup='soloNumeros(sondsal13,tsal13,13)'; onblur='prueba(sondsal13)'/></td>" 
							+"<td  width='2.8%'><input id='sondsalt1' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='sondsal14' type='text' style='border: 1px solid #585858' size='1'  onkeyup='soloNumeros(sondsal14,tsal14,14)'; onblur='prueba(sondsal14)'/></td>" 
							+"<td  width='2.8%'><input id='sondsal15' type='text' style='border: 1px solid #585858' size='1'  onkeyup='soloNumeros(sondsal15,tsal15,15)'; onblur='prueba(sondsal15)'/></td>" 
							+"<td  width='2.8%'><input id='sondsal16' type='text' style='border: 1px solid #585858' size='1'  onkeyup='soloNumeros(sondsal16,tsal16,16)'; onblur='prueba(sondsal16)'/></td>" 
							+"<td  width='2.8%'><input id='sondsal17' type='text' style='border: 1px solid #585858' size='1'  onkeyup='soloNumeros(sondsal17,tsal17,17)'; onblur='prueba(sondsal17)'/></td>" 
							+"<td  width='2.8%'><input id='sondsal18' type='text' style='border: 1px solid #585858' size='1'  onkeyup='soloNumeros(sondsal18,tsal18,18)'; onblur='prueba(sondsal18)'/></td>" 
							+"<td  width='2.8%'><input id='sondsal19' type='text' style='border: 1px solid #585858' size='1'  onkeyup='soloNumeros(sondsal19,tsal19,19)'; onblur='prueba(sondsal19)'/></td>" 
							+"<td  width='2.8%'><input id='sondsalt2' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='sondsal20' type='text' style='border: 1px solid #585858' size='1'  onkeyup='soloNumeros(sondsal20,tsal20,20)'; onblur='prueba(sondsal20)'/></td>" 
							+"<td  width='2.8%'><input id='sondsal21' type='text' style='border: 1px solid #585858' size='1'  onkeyup='soloNumeros(sondsal21,tsal21,21)'; onblur='prueba(sondsal21)'/></td>" 
							+"<td  width='2.8%'><input id='sondsal22' type='text' style='border: 1px solid #585858' size='1'  onkeyup='soloNumeros(sondsal22,tsal22,22)'; onblur='prueba(sondsal22)'/></td>" 
							+"<td  width='2.8%'><input id='sondsal23' type='text' style='border: 1px solid #585858' size='1'  onkeyup='soloNumeros(sondsal23,tsal23,23)'; onblur='prueba(sondsal23)'/></td>" 
							+"<td  width='2.8%'><input id='sondsal00' type='text' style='border: 1px solid #585858' size='1'  onkeyup='soloNumeros(sondsal00,tsal00,00)'; onblur='prueba(sondsal00)'/></td>" 
							+"<td  width='2.8%'><input id='sondsal01' type='text' style='border: 1px solid #585858' size='1'  onkeyup='soloNumeros(sondsal01,tsal01,01)'; onblur='prueba(sondsal01)'/></td>" 
							+"<td  width='2.8%'><input id='sondsal02' type='text' style='border: 1px solid #585858' size='1'  onkeyup='soloNumeros(sondsal02,tsal02,02)'; onblur='prueba(sondsal02)'/></td>" 
							+"<td  width='2.8%'><input id='sondsal03' type='text' style='border: 1px solid #585858' size='1'  onkeyup='soloNumeros(sondsal03,tsal03,03)'; onblur='prueba(sondsal03)'/></td>" 
							+"<td  width='2.8%'><input id='sondsal04' type='text' style='border: 1px solid #585858' size='1'  onkeyup='soloNumeros(sondsal04,tsal04,04)'; onblur='prueba(sondsal04)'/></td>" 
							+"<td  width='2.8%'><input id='sondsal05' type='text' style='border: 1px solid #585858' size='1'  onkeyup='soloNumeros(sondsal05,tsal05,05)'; onblur='prueba(sondsal05)'/></td>" 
							+"<td  width='2.8%'><input id='sondsal06' type='text' style='border: 1px solid #585858' size='1'  onkeyup='soloNumeros(sondsal06,tsal06,06)'; onblur='prueba(sondsal06)'/></td>"													
							+"<td  width='2.8%'><input id='sondsalt3' type='text' size='1' disabled='disabled'/></td>" 
							+"</tr></table>"+
							"</td></tr>"+
							//vomito
							"<tr> <td width='30%'><div id='vomito'>VOMITO</div>" +
						    "<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='vom7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(vom7,tsal7,7)'; onblur='prueba(vom7)'/></td>" 
							+"<td  width='2.8%'><input id='vom8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(vom8,tsal8,8)'; onblur='prueba(vom8)'/></td>" 
							+"<td  width='2.8%'><input id='vom9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(vom9,tsal9,9)'; onblur='prueba(vom9)'/></td>" 
							+"<td width='2.8%'><input id='vom10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(vom10,tsal10,10)'; onblur='prueba(vom10)'/></td>" 
							+"<td  width='2.8%'><input id='vom11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(vom11,tsal11,11)'; onblur='prueba(vom11)'/></td>" 
							+"<td  width='2.8%'><input id='vom12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(vom12,tsal12,12)'; onblur='prueba(vom12)'/></td>"
							+"<td  width='2.8%'><input id='vom13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(vom13,tsal13,13)'; onblur='prueba(vom13)'/></td>" 
							+"<td  width='2.8%'><input id='vomt1' type='text' size='1' disabled='disabled'/></td>" 
							+"<td width='2.8%'><input id='vom14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(vom14,tsal14,14)'; onblur='prueba(vom14)'/></td>" 
							+"<td  width='2.8%'><input id='vom15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(vom15,tsal15,15)'; onblur='prueba(vom15)'/></td>" 
							+"<td  width='2.8%'><input id='vom16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(vom16,tsal16,16)'; onblur='prueba(vom16)'/></td>" 
							+"<td  width='2.8%'><input id='vom17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(vom17,tsal17,17)'; onblur='prueba(vom17)'/></td>" 
							+"<td width='2.8%'><input id='vom18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(vom18,tsal18,18)'; onblur='prueba(vom18)'/></td>" 
							+"<td  width='2.8%'><input id='vom19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(vom19,tsal19,19)'; onblur='prueba(vom19)'/></td>" 
							+"<td  width='2.8%'><input id='vomt2' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='vom20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(vom20,tsal20,20)'; onblur='prueba(vom20)'/></td>" 
							+"<td  width='2.8%'><input id='vom21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(vom21,tsal21,21)'; onblur='prueba(vom21)'/></td>" 
							+"<td width='2.8%'><input id='vom22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(vom22,tsal22,22)'; onblur='prueba(vom22)'/></td>" 
							+"<td  width='2.8%'><input id='vom23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(vom23,tsal23,23)'; onblur='prueba(vom23)'/></td>" 
							+"<td  width='2.8%'><input id='vom00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(vom00,tsal00,00)'; onblur='prueba(vom00)'/></td>" 
							+"<td  width='2.8%'><input id='vom01' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(vom01,tsal01,01)'; onblur='prueba(vom01)'/></td>" 
							+"<td  width='2.8%'><input id='vom02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(vom02,tsal02,02)'; onblur='prueba(vom02)'/></td>" 
							+"<td width='2.8%'><input id='vom03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(vom03,tsal03,03)'; onblur='prueba(vom03)'/></td>" 
							+"<td  width='2.8%'><input id='vom04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(vom04,tsal04,04)'; onblur='prueba(vom04)'/></td>" 
							+"<td  width='2.8%'><input id='vom05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(vom05,tsal05,05)'; onblur='prueba(vom05)'/></td>" 
							+"<td  width='2.8%'><input id='vom06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(vom06,tsal06,06)'; onblur='prueba(vom06)'/></td>"													
							+"<td  width='2.8%'><input id='vomt3' type='text' size='1' disabled='disabled'/></td>" 
							+"</tr></table>"+
						    "</td></tr>"+
						    //deposicion
							"<tr> <td width='30%'><div id='deposicion'>DEPOSICI\u00D3N</div>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='dep7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(dep7,tsal7,7)'; onblur='prueba(dep7)'/></td>" 
							+"<td  width='2.8%'><input id='dep8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(dep8,tsal8,8)'; onblur='prueba(dep8)'/></td>" 
							+"<td  width='2.8%'><input id='dep9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(dep9,tsal9,9)'; onblur='prueba(dep9)'/></td>" 
							+"<td width='2.8%'><input id='dep10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(dep10,tsal10,10)'; onblur='prueba(dep10)'/></td>" 
							+"<td  width='2.8%'><input id='dep11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(dep11,tsal11,11)'; onblur='prueba(dep11)'/></td>" 
							+"<td  width='2.8%'><input id='dep12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(dep12,tsal12,12)'; onblur='prueba(dep12)'/></td>"
							+"<td  width='2.8%'><input id='dep13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(dep13,tsal13,13)'; onblur='prueba(dep13)'/></td>" 
							+"<td  width='2.8%'><input id='dept1' type='text' size='1' disabled='disabled'/></td>" 
							+"<td width='2.8%'><input id='dep14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(dep14,tsal14,14)'; onblur='prueba(dep14)'/></td>" 
							+"<td  width='2.8%'><input id='dep15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(dep15,tsal15,15)'; onblur='prueba(dep15)'/></td>" 
							+"<td  width='2.8%'><input id='dep16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(dep16,tsal16,16)'; onblur='prueba(dep16)'/></td>" 
							+"<td  width='2.8%'><input id='dep17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(dep17,tsal17,17)'; onblur='prueba(dep17,)'/></td>" 
							+"<td width='2.8%'><input id='dep18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(dep18,tsal18,18)'; onblur='prueba(dep18)'/></td>" 
							+"<td  width='2.8%'><input id='dep19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(dep19,tsal19,19)'; onblur='prueba(dep19)'/></td>" 
							+"<td  width='2.8%'><input id='dept2' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='dep20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(dep20,tsal20,20)'; onblur='prueba(dep20)'/></td>" 
							+"<td  width='2.8%'><input id='dep21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(dep21,tsal21,21)'; onblur='prueba(dep21)'/></td>" 
							+"<td width='2.8%'><input id='dep22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(dep22,tsal22,22)'; onblur='prueba(dep22)'/></td>" 
							+"<td  width='2.8%'><input id='dep23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(dep23,tsal23,23)'; onblur='prueba(dep23)'/></td>" 
							+"<td  width='2.8%'><input id='dep00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(dep00,tsal00,00)'; onblur='prueba(dep00)'/></td>" 
							+"<td  width='2.8%'><input id='dep01' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(dep01,tsal01,01)'; onblur='prueba(dep01)'/></td>" 
							+"<td  width='2.8%'><input id='dep02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(dep02,tsal02,02)'; onblur='prueba(dep02)'/></td>" 
							+"<td width='2.8%'><input id='dep03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(dep03,tsal03,03)'; onblur='prueba(dep03)'/></td>" 
							+"<td  width='2.8%'><input id='dep04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(dep04,tsal04,04)'; onblur='prueba(dep04)'/></td>" 
							+"<td  width='2.8%'><input id='dep05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(dep05,tsal05,05)'; onblur='prueba(dep05)'/></td>" 
							+"<td  width='2.8%'><input id='dep06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(dep06,tsal06,06)'; onblur='prueba(dep06)'/></td>"													
							+"<td  width='2.8%'><input id='dept3' type='text' size='1' disabled='disabled'/></td>" 
							+"</tr></table>"+
							"</td></tr>"+    
							//drenaje1
							"<tr id='drenaje1' style='display: none'> <td  width='30%'><div id='drena1'>drenaje1</div>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='drenaje1_7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje1_7,tsal7,7)'; onblur='prueba(drenaje1_7)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje1_8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje1_8,tsal8,8)'; onblur='prueba(drenaje1_8)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje1_9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje1_9,tsal9,9)'; onblur='prueba(drenaje1_9)'/></td>" 
							+"<td width='2.8%'><input id='drenaje1_10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje1_10,tsal10,10)'; onblur='prueba(drenaje1_10)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje1_11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje1_11,tsal11,11)'; onblur='prueba(drenaje1_11)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje1_12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje1_12,tsal12,12)'; onblur='prueba(drenaje1_12)'/></td>"
							+"<td  width='2.8%'><input id='drenaje1_13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje1_13,tsal13,13)'; onblur='prueba(drenaje1_13)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje1_t1' type='text' size='1' disabled='disabled'/></td>" 
							+"<td width='2.8%'><input id='drenaje1_14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje1_14,tsal14,14)'; onblur='prueba(drenaje1_14)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje1_15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje1_15,tsal15,15)'; onblur='prueba(drenaje1_15)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje1_16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje1_16,tsal16,16)'; onblur='prueba(drenaje1_16)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje1_17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje1_17,tsal17,17)'; onblur='prueba(drenaje1_17,)'/></td>" 
							+"<td width='2.8%'><input id='drenaje1_18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje1_18,tsal18,18)'; onblur='prueba(drenaje1_18)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje1_19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje1_19,tsal19,19)'; onblur='prueba(drenaje1_19)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje1_t2' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='drenaje1_20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje1_20,tsal20,20)'; onblur='prueba(drenaje1_20)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje1_21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje1_21,tsal21,21)'; onblur='prueba(drenaje1_21)'/></td>" 
							+"<td width='2.8%'><input id='drenaje1_22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje1_22,tsal22,22)'; onblur='prueba(drenaje1_22)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje1_23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje1_23,tsal23,23)'; onblur='prueba(drenaje1_23)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje1_00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje1_00,tsal00,00)'; onblur='prueba(drenaje1_00)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje1_01' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje1_01,tsal01,01)'; onblur='prueba(drenaje1_01)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje1_02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje1_02,tsal02,02)'; onblur='prueba(drenaje1_02)'/></td>" 
							+"<td width='2.8%'><input id='drenaje1_03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje1_03,tsal03,03)'; onblur='prueba(drenaje1_03)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje1_04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje1_04,tsal04,04)'; onblur='prueba(drenaje1_04)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje1_05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje1_05,tsal05,05)'; onblur='prueba(drenaje1_05)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje1_06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje1_06,tsal06,06)'; onblur='prueba(drenaje1_06)'/></td>"													
							+"<td  width='2.8%'><input id='drenaje1_t3' type='text' size='1' disabled='disabled'/></td>" 
							+"</tr></table>"+
							"</td></tr>"+ 
							
							//drenaje2
							"<tr id='drenaje2' style='display: none'> <td  width='30%'><div id='drena2'>drenaje2</div>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='drenaje2_7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje2_7,tsal7,7)'; onblur='prueba(drenaje2_7)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje2_8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje2_8,tsal8,8)'; onblur='prueba(drenaje2_8)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje2_9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje2_9,tsal9,9)'; onblur='prueba(drenaje2_9)'/></td>" 
							+"<td width='2.8%'><input id='drenaje2_10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje2_10,tsal10,10)'; onblur='prueba(drenaje2_10)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje2_11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje2_11,tsal11,11)'; onblur='prueba(drenaje2_11)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje2_12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje2_12,tsal12,12)'; onblur='prueba(drenaje2_12)'/></td>"
							+"<td  width='2.8%'><input id='drenaje2_13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje2_13,tsal13,13)'; onblur='prueba(drenaje2_13)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje2_t1' type='text' size='1' disabled='disabled'/></td>" 
							+"<td width='2.8%'><input id='drenaje2_14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje2_14,tsal14,14)'; onblur='prueba(drenaje2_14)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje2_15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje2_15,tsal15,15)'; onblur='prueba(drenaje2_15)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje2_16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje2_16,tsal16,16)'; onblur='prueba(drenaje2_16)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje2_17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje2_17,tsal17,17)'; onblur='prueba(drenaje2_17,)'/></td>" 
							+"<td width='2.8%'><input id='drenaje2_18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje2_18,tsal18,18)'; onblur='prueba(drenaje2_18)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje2_19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje2_19,tsal19,19)'; onblur='prueba(drenaje2_19)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje2_t2' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='drenaje2_20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje2_20,tsal20,20)'; onblur='prueba(drenaje2_20)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje2_21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje2_21,tsal21,21)'; onblur='prueba(drenaje2_21)'/></td>" 
							+"<td width='2.8%'><input id='drenaje2_22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje2_22,tsal22,22)'; onblur='prueba(drenaje2_22)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje2_23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje2_23,tsal23,23)'; onblur='prueba(drenaje2_23)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje2_00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje2_00,tsal00,00)'; onblur='prueba(drenaje2_00)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje2_01' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje2_01,tsal01,01)'; onblur='prueba(drenaje2_01)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje2_02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje2_02,tsal02,02)'; onblur='prueba(drenaje2_02)'/></td>" 
							+"<td width='2.8%'><input id='drenaje2_03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje2_03,tsal03,03)'; onblur='prueba(drenaje2_03)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje2_04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje2_04,tsal04,04)'; onblur='prueba(drenaje2_04)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje2_05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje2_05,tsal05,05)'; onblur='prueba(drenaje2_05)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje2_06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje2_06,tsal06,06)'; onblur='prueba(drenaje2_06)'/></td>"													
							+"<td  width='2.8%'><input id='drenaje2_t3' type='text' size='1' disabled='disabled'/></td>" 
							+"</tr></table>"+
							"</td></tr>"+ 
							
							//drenaje3
							"<tr id='drenaje3' style='display: none'> <td  width='30%'><div id='drena3'>drenaje3</div>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='drenaje3_7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje3_7,tsal7,7)'; onblur='prueba(drenaje3_7)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje3_8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje3_8,tsal8,8)'; onblur='prueba(drenaje3_8)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje3_9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje3_9,tsal9,9)'; onblur='prueba(drenaje3_9)'/></td>" 
							+"<td width='2.8%'><input id='drenaje3_10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje3_10,tsal10,10)'; onblur='prueba(drenaje3_10)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje3_11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje3_11,tsal11,11)'; onblur='prueba(drenaje3_11)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje3_12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje3_12,tsal12,12)'; onblur='prueba(drenaje3_12)'/></td>"
							+"<td  width='2.8%'><input id='drenaje3_13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje3_13,tsal13,13)'; onblur='prueba(drenaje3_13)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje3_t1' type='text' size='1' disabled='disabled'/></td>" 
							+"<td width='2.8%'><input id='drenaje3_14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje3_14,tsal14,14)'; onblur='prueba(drenaje3_14)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje3_15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje3_15,tsal15,15)'; onblur='prueba(drenaje3_15)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje3_16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje3_16,tsal16,16)'; onblur='prueba(drenaje3_16)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje3_17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje3_17,tsal17,17)'; onblur='prueba(drenaje3_17,)'/></td>" 
							+"<td width='2.8%'><input id='drenaje3_18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje3_18,tsal18,18)'; onblur='prueba(drenaje3_18)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje3_19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje3_19,tsal19,19)'; onblur='prueba(drenaje3_19)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje3_t2' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='drenaje3_20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje3_20,tsal20,20)'; onblur='prueba(drenaje3_20)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje3_21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje3_21,tsal21,21)'; onblur='prueba(drenaje3_21)'/></td>" 
							+"<td width='2.8%'><input id='drenaje3_22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje3_22,tsal22,22)'; onblur='prueba(drenaje3_22)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje3_23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje3_23,tsal23,23)'; onblur='prueba(drenaje3_23)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje3_00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje3_00,tsal00,00)'; onblur='prueba(drenaje3_00)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje3_01' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje3_01,tsal01,01)'; onblur='prueba(drenaje3_01)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje3_02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje3_02,tsal02,02)'; onblur='prueba(drenaje3_02)'/></td>" 
							+"<td width='2.8%'><input id='drenaje3_03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje3_03,tsal03,03)'; onblur='prueba(drenaje3_03)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje3_04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje3_04,tsal04,04)'; onblur='prueba(drenaje3_04)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje3_05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje3_05,tsal05,05)'; onblur='prueba(drenaje3_05)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje3_06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje3_06,tsal06,06)'; onblur='prueba(drenaje3_06)'/></td>"													
							+"<td  width='2.8%'><input id='drenaje3_t3' type='text' size='1' disabled='disabled'/></td>" 
							+"</tr></table>"+
							"</td></tr>"+ 
							
							//drenaje4
							"<tr id='drenaje4' style='display: none'> <td width='30%'><div id='drena4'>drenaje4</div>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='drenaje4_7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje4_7,tsal7,7)'; onblur='prueba(drenaje4_7)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje4_8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje4_8,tsal8,8)'; onblur='prueba(drenaje4_8)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje4_9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje4_9,tsal9,9)'; onblur='prueba(drenaje4_9)'/></td>" 
							+"<td width='2.8%'><input id='drenaje4_10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje4_10,tsal10,10)'; onblur='prueba(drenaje4_10)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje4_11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje4_11,tsal11,11)'; onblur='prueba(drenaje4_11)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje4_12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje4_12,tsal12,12)'; onblur='prueba(drenaje4_12)'/></td>"
							+"<td  width='2.8%'><input id='drenaje4_13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje4_13,tsal13,13)'; onblur='prueba(drenaje4_13)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje4_t1' type='text' size='1' disabled='disabled'/></td>" 
							+"<td width='2.8%'><input id='drenaje4_14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje4_14,tsal14,14)'; onblur='prueba(drenaje4_14)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje4_15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje4_15,tsal15,15)'; onblur='prueba(drenaje4_15)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje4_16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje4_16,tsal16,16)'; onblur='prueba(drenaje4_16)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje4_17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje4_17,tsal17,17)'; onblur='prueba(drenaje4_17,)'/></td>" 
							+"<td width='2.8%'><input id='drenaje4_18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje4_18,tsal18,18)'; onblur='prueba(drenaje4_18)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje4_19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje4_19,tsal19,19)'; onblur='prueba(drenaje4_19)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje4_t2' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='drenaje4_20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje4_20,tsal20,20)'; onblur='prueba(drenaje4_20)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje4_21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje4_21,tsal21,21)'; onblur='prueba(drenaje4_21)'/></td>" 
							+"<td width='2.8%'><input id='drenaje4_22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje4_22,tsal22,22)'; onblur='prueba(drenaje4_22)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje4_23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje4_23,tsal23,23)'; onblur='prueba(drenaje4_23)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje4_00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje4_00,tsal00,00)'; onblur='prueba(drenaje4_00)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje4_01' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje4_01,tsal01,01)'; onblur='prueba(drenaje4_01)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje4_02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje4_02,tsal02,02)'; onblur='prueba(drenaje4_02)'/></td>" 
							+"<td width='2.8%'><input id='drenaje4_03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje4_03,tsal03,03)'; onblur='prueba(drenaje4_03)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje4_04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje4_04,tsal04,04)'; onblur='prueba(drenaje4_04)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje4_05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje4_05,tsal05,05)'; onblur='prueba(drenaje4_05)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje4_06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje4_06,tsal06,06)'; onblur='prueba(drenaje4_06)'/></td>"													
							+"<td  width='2.8%'><input id='drenaje4_t3' type='text' size='1' disabled='disabled'/></td>" 
							+"</tr></table>"+
							"</td></tr>"+ 
							
							//drenaje5
							"<tr id='drenaje5' style='display: none'> <td  width='30%'><div id='drena5'>drenaje5</div>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='drenaje5_7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje5_7,tsal7,7)'; onblur='prueba(drenaje5_7)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje5_8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje5_8,tsal8,8)'; onblur='prueba(drenaje5_8)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje5_9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje5_9,tsal9,9)'; onblur='prueba(drenaje5_9)'/></td>" 
							+"<td width='2.8%'><input id='drenaje5_10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje5_10,tsal10,10)'; onblur='prueba(drenaje5_10)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje5_11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje5_11,tsal11,11)'; onblur='prueba(drenaje5_11)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje5_12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje5_12,tsal12,12)'; onblur='prueba(drenaje5_12)'/></td>"
							+"<td  width='2.8%'><input id='drenaje5_13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje5_13,tsal13,13)'; onblur='prueba(drenaje5_13)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje5_t1' type='text' size='1' disabled='disabled'/></td>" 
							+"<td width='2.8%'><input id='drenaje5_14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje5_14,tsal14,14)'; onblur='prueba(drenaje5_14)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje5_15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje5_15,tsal15,15)'; onblur='prueba(drenaje5_15)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje5_16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje5_16,tsal16,16)'; onblur='prueba(drenaje5_16)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje5_17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje5_17,tsal17,17)'; onblur='prueba(drenaje5_17,)'/></td>" 
							+"<td width='2.8%'><input id='drenaje5_18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje5_18,tsal18,18)'; onblur='prueba(drenaje5_18)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje5_19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje5_19,tsal19,19)'; onblur='prueba(drenaje5_19)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje5_t2' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='drenaje5_20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje5_20,tsal20,20)'; onblur='prueba(drenaje5_20)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje5_21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje5_21,tsal21,21)'; onblur='prueba(drenaje5_21)'/></td>" 
							+"<td width='2.8%'><input id='drenaje5_22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje5_22,tsal22,22)'; onblur='prueba(drenaje5_22)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje5_23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje5_23,tsal23,23)'; onblur='prueba(drenaje5_23)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje5_00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje5_00,tsal00,00)'; onblur='prueba(drenaje5_00)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje5_01' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje5_01,tsal01,01)'; onblur='prueba(drenaje5_01)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje5_02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje5_02,tsal02,02)'; onblur='prueba(drenaje5_02)'/></td>" 
							+"<td width='2.8%'><input id='drenaje5_03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje5_03,tsal03,03)'; onblur='prueba(drenaje5_03)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje5_04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje5_04,tsal04,04)'; onblur='prueba(drenaje5_04)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje5_05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje5_05,tsal05,05)'; onblur='prueba(drenaje5_05)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje5_06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje5_06,tsal06,06)'; onblur='prueba(drenaje5_06)'/></td>"													
							+"<td  width='2.8%'><input id='drenaje5_t3' type='text' size='1' disabled='disabled'/></td>" 
							+"</tr></table>"+
							"</td></tr>"+ 
							
							//drenaje6
							"<tr id='drenaje6' style='display: none'> <td  width='30%'><div id='drena6'>drenaje6</div>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='drenaje6_7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje6_7,tsal7,7)'; onblur='prueba(drenaje6_7)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje6_8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje6_8,tsal8,8)'; onblur='prueba(drenaje6_8)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje6_9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje6_9,tsal9,9)'; onblur='prueba(drenaje6_9)'/></td>" 
							+"<td width='2.8%'><input id='drenaje6_10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje6_10,tsal10,10)'; onblur='prueba(drenaje6_10)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje6_11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje6_11,tsal11,11)'; onblur='prueba(drenaje6_11)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje6_12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje6_12,tsal12,12)'; onblur='prueba(drenaje6_12)'/></td>"
							+"<td  width='2.8%'><input id='drenaje6_13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje6_13,tsal13,13)'; onblur='prueba(drenaje6_13)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje6_t1' type='text' size='1' disabled='disabled'/></td>" 
							+"<td width='2.8%'><input id='drenaje6_14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje6_14,tsal14,14)'; onblur='prueba(drenaje6_14)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje6_15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje6_15,tsal15,15)'; onblur='prueba(drenaje6_15)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje6_16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje6_16,tsal16,16)'; onblur='prueba(drenaje6_16)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje6_17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje6_17,tsal17,17)'; onblur='prueba(drenaje6_17,)'/></td>" 
							+"<td width='2.8%'><input id='drenaje6_18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje6_18,tsal18,18)'; o'nblur='prueba(drenaje6_18)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje6_19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje6_19,tsal19,19)'; onblur='prueba(drenaje6_19)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje6_t2' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='drenaje6_20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje6_20,tsal20,20)'; onblur='prueba(drenaje6_20)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje6_21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje6_21,tsal21,21)'; onblur='prueba(drenaje6_21)'/></td>" 
							+"<td width='2.8%'><input id='drenaje6_22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje6_22,tsal22,22)'; onblur='prueba(drenaje6_22)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje6_23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje6_23,tsal23,23)'; onblur='prueba(drenaje6_23)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje6_00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje6_00,tsal00,00)'; onblur='prueba(drenaje6_00)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje6_01' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje6_01,tsal01,01)'; onblur='prueba(drenaje6_01)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje6_02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje6_02,tsal02,02)'; onblur='prueba(drenaje6_02)'/></td>" 
							+"<td width='2.8%'><input id='drenaje6_03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje6_03,tsal03,03)'; onblur='prueba(drenaje6_03)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje6_04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje6_04,tsal04,04)'; onblur='prueba(drenaje6_04)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje6_05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje6_05,tsal05,05)'; onblur='prueba(drenaje6_05)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje6_06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje6_06,tsal06,06)'; onblur='prueba(drenaje6_06)'/></td>"													
							+"<td  width='2.8%'><input id='drenaje6_t3' type='text' size='1' disabled='disabled'/></td>" 
							+"</tr></table>"+
							"</td></tr>"+ 
							
							//drenaje7
							"<tr id='drenaje7' style='display: none'> <td width='30%'><div id='drena7'>drenaje7</div>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='drenaje7_7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje7_7,tsal7,7)'; onblur='prueba(drenaje7_7)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje7_8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje7_8,tsal8,8)'; onblur='prueba(drenaje7_8)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje7_9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje7_9,tsal9,9)'; onblur='prueba(drenaje7_9)'/></td>" 
							+"<td width='2.8%'><input id='drenaje7_10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje7_10,tsal10,10)'; onblur='prueba(drenaje7_10)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje7_11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje7_11,tsal11,11)'; onblur='prueba(drenaje7_11)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje7_12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje7_12,tsal12,12)'; onblur='prueba(drenaje7_12)'/></td>"
							+"<td  width='2.8%'><input id='drenaje7_13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje7_13,tsal13,13)'; onblur='prueba(drenaje7_13)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje7_t1' type='text' size='1' disabled='disabled'/></td>" 
							+"<td width='2.8%'><input id='drenaje7_14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje7_14,tsal14,14)'; onblur='prueba(drenaje7_14)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje7_15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje7_15,tsal15,15)'; onblur='prueba(drenaje7_15)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje7_16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje7_16,tsal16,16)'; onblur='prueba(drenaje7_16)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje7_17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje7_17,tsal17,17)'; onblur='prueba(drenaje7_17,)'/></td>" 
							+"<td width='2.8%'><input id='drenaje7_18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje7_18,tsal18,18)'; onblur='prueba(drenaje7_18)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje7_19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje7_19,tsal19,19)'; onblur='prueba(drenaje7_19)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje7_t2' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='drenaje7_20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje7_20,tsal20,20)'; onblur='prueba(drenaje7_20)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje7_21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje7_21,tsal21,21)'; onblur='prueba(drenaje7_21)'/></td>" 
							+"<td width='2.8%'><input id='drenaje7_22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje7_22,tsal22,22)'; onblur='prueba(drenaje7_22)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje7_23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje7_23,tsal23,23)'; onblur='prueba(drenaje7_23)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje7_00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje7_00,tsal00,00)'; onblur='prueba(drenaje7_00)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje7_01' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje7_01,tsal01,01)'; onblur='prueba(drenaje7_01)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje7_02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje7_02,tsal02,02)'; onblur='prueba(drenaje7_02)'/></td>" 
							+"<td width='2.8%'><input id='drenaje7_03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje7_03,tsal03,03)'; onblur='prueba(drenaje7_03)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje7_04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje7_04,tsal04,04)'; onblur='prueba(drenaje7_04)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje7_05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje7_05,tsal05,05)'; onblur='prueba(drenaje7_05)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje7_06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje7_06,tsal06,06)'; onblur='prueba(drenaje7_06)'/></td>"													
							+"<td  width='2.8%'><input id='drenaje7_t3' type='text' size='1' disabled='disabled'/></td>" 
							+"</tr></table>"+
							"</td></tr>"+ 
							
							//drenaje8
							"<tr id='drenaje8' style='display: none'> <td width='30%'><div id='drena8'>drenaje8</div>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='drenaje8_7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje8_7,tsal7,7)'; onblur='prueba(drenaje8_7)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje8_8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje8_8,tsal8,8)'; onblur='prueba(drenaje8_8)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje8_9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje8_9,tsal9,9)'; onblur='prueba(drenaje8_9)'/></td>" 
							+"<td width='2.8%'><input id='drenaje8_10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje8_10,tsal10,10)'; onblur='prueba(drenaje8_10)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje8_11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje8_11,tsal11,11)'; onblur='prueba(drenaje8_11)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje8_12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje8_12,tsal12,12)'; onblur='prueba(drenaje8_12)'/></td>"
							+"<td  width='2.8%'><input id='drenaje8_13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje8_13,tsal13,13)'; onblur='prueba(drenaje8_13)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje8_t1' type='text' size='1' disabled='disabled'/></td>" 
							+"<td width='2.8%'><input id='drenaje8_14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje8_14,tsal14,14)'; onblur='prueba(drenaje8_14)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje8_15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje8_15,tsal15,15)'; onblur='prueba(drenaje8_15)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje8_16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje8_16,tsal16,16)'; onblur='prueba(drenaje8_16)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje8_17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje8_17,tsal17,17)'; onblur='prueba(drenaje8_17,)'/></td>" 
							+"<td width='2.8%'><input id='drenaje8_18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje8_18,tsal18,18)'; onblur='prueba(drenaje8_18)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje8_19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje8_19,tsal19,19)'; onblur='prueba(drenaje8_19)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje8_t2' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='drenaje8_20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje8_20,tsal20,20)'; onblur='prueba(drenaje8_20)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje8_21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje8_21,tsal21,21)'; onblur='prueba(drenaje8_21)'/></td>" 
							+"<td width='2.8%'><input id='drenaje8_22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje8_22,tsal22,22)'; onblur='prueba(drenaje8_22)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje8_23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje8_23,tsal23,23)'; onblur='prueba(drenaje8_23)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje8_00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje8_00,tsal00,00)'; onblur='prueba(drenaje8_00)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje8_01' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje8_01,tsal01,01)'; onblur='prueba(drenaje8_01)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje8_02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje8_02,tsal02,02)'; onblur='prueba(drenaje8_02)'/></td>" 
							+"<td width='2.8%'><input id='drenaje8_03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje8_03,tsal03,03)'; onblur='prueba(drenaje8_03)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje8_04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje8_04,tsal04,04)'; onblur='prueba(drenaje8_04)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje8_05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje8_05,tsal05,05)'; onblur='prueba(drenaje8_05)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje8_06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje8_06,tsal06,06)'; onblur='prueba(drenaje8_06)'/></td>"													
							+"<td  width='2.8%'><input id='drenaje8_t3' type='text' size='1' disabled='disabled'/></td>" 
							+"</tr></table>"+
							"</td></tr>"+ 
							
							//drenaje9
							"<tr id='drenaje9' style='display: none'> <td width='30%'><div id='drena9'>drenaje9</div>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='drenaje9_7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje9_7,tsal7,7)'; onblur='prueba(drenaje9_7)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje9_8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje9_8,tsal8,8)'; onblur='prueba(drenaje9_8)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje9_9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje9_9,tsal9,9)'; onblur='prueba(drenaje9_9)'/></td>" 
							+"<td width='2.8%'><input id='drenaje9_10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje9_10,tsal10,10)'; onblur='prueba(drenaje9_10)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje9_11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje9_11,tsal11,11)'; onblur='prueba(drenaje9_11)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje9_12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje9_12,tsal12,12)'; onblur='prueba(drenaje9_12)'/></td>"
							+"<td  width='2.8%'><input id='drenaje9_13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje9_13,tsal13,13)'; onblur='prueba(drenaje9_13)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje9_t1' type='text' size='1' disabled='disabled'/></td>" 
							+"<td width='2.8%'><input id='drenaje9_14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje9_14,tsal14,14)'; onblur='prueba(drenaje9_14)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje9_15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje9_15,tsal15,15)'; onblur='prueba(drenaje9_15)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje9_16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje9_16,tsal16,16)'; onblur='prueba(drenaje9_16)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje9_17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje9_17,tsal17,17)'; onblur='prueba(drenaje9_17,)'/></td>" 
							+"<td width='2.8%'><input id='drenaje9_18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje9_18,tsal18,18)'; onblur='prueba(drenaje9_18)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje9_19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje9_19,tsal19,19)'; onblur='prueba(drenaje9_19)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje9_t2' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='drenaje9_20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje9_20,tsal20,20)'; onblur='prueba(drenaje9_20)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje9_21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje9_21,tsal21,21)'; onblur='prueba(drenaje9_21)'/></td>" 
							+"<td width='2.8%'><input id='drenaje9_22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje9_22,tsal22,22)'; onblur='prueba(drenaje9_22)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje9_23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje9_23,tsal23,23)'; onblur='prueba(drenaje9_23)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje9_00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje9_00,tsal00,00)'; onblur='prueba(drenaje9_00)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje9_01' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje9_01,tsal01,01)'; onblur='prueba(drenaje9_01)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje9_02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje9_02,tsal02,02)'; onblur='prueba(drenaje9_02)'/></td>" 
							+"<td width='2.8%'><input id='drenaje9_03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje9_03,tsal03,03)'; onblur='prueba(drenaje9_03)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje9_04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje9_04,tsal04,04)'; onblur='prueba(drenaje9_04)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje9_05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje9_05,tsal05,05)'; onblur='prueba(drenaje9_05)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje9_06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje9_06,tsal06,06)'; onblur='prueba(drenaje9_06)'/></td>"													
							+"<td  width='2.8%'><input id='drenaje9_t3' type='text' size='1' disabled='disabled'/></td>" 
							+"</tr></table>"+
							"</td></tr>"+ 
							
							//drenaje10
							"<tr id='drenaje10' style='display: none'> <td width='30%'><div id='drena10'>drenaje10</div>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='drenaje10_7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje10_7,tsal7,7)'; onblur='prueba(drenaje10_7)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje10_8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje10_8,tsal8,8)'; onblur='prueba(drenaje10_8)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje10_9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje10_9,tsal9,9)'; onblur='prueba(drenaje10_9)'/></td>" 
							+"<td width='2.8%'><input id='drenaje10_10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje10_10,tsal10,10)'; onblur='prueba(drenaje10_10)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje10_11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje10_11,tsal11,11)'; onblur='prueba(drenaje10_11)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje10_12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje10_12,tsal12,12)'; onblur='prueba(drenaje10_12)'/></td>"
							+"<td  width='2.8%'><input id='drenaje10_13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje10_13,tsal13,13)'; onblur='prueba(drenaje10_13)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje10_t1' type='text' size='1' disabled='disabled'/></td>" 
							+"<td width='2.8%'><input id='drenaje10_14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje10_14,tsal14,14)'; onblur='prueba(drenaje10_14)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje10_15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje10_15,tsal15,15)'; onblur='prueba(drenaje10_15)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje10_16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje10_16,tsal16,16)'; onblur='prueba(drenaje10_16)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje10_17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje10_17,tsal17,17)'; onblur='prueba(drenaje10_17,)'/></td>" 
							+"<td width='2.8%'><input id='drenaje10_18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje10_18,tsal18,18)'; onblur='prueba(drenaje10_18)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje10_19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje10_19,tsal19,19)'; onblur='prueba(drenaje10_19)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje10_t2' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='drenaje10_20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje10_20,tsal20,20)'; onblur='prueba(drenaje10_20)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje10_21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje10_21,tsal21,21)'; onblur='prueba(drenaje10_21)'/></td>" 
							+"<td width='2.8%'><input id='drenaje10_22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje10_22,tsal22,22)'; onblur='prueba(drenaje10_22)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje10_23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje10_23,tsal23,23)'; onblur='prueba(drenaje10_23)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje10_00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje10_00,tsal00,00)'; onblur='prueba(drenaje10_00)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje10_01' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje10_01,tsal01,01)'; onblur='prueba(drenaje10_01)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje10_02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje10_02,tsal02,02)'; onblur='prueba(drenaje10_02)'/></td>" 
							+"<td width='2.8%'><input id='drenaje10_03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje10_03,tsal03,03)'; onblur='prueba(drenaje10_03)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje10_04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje10_04,tsal04,04)'; onblur='prueba(drenaje10_04)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje10_05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje10_05,tsal05,05)'; onblur='prueba(drenaje10_05)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje10_06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje10_06,tsal06,06)'; onblur='prueba(drenaje10_06)'/></td>"													
							+"<td  width='2.8%'><input id='drenaje10_t3' type='text' size='1' disabled='disabled'/></td>" 
							+"</tr></table>"+
							"</td></tr>"+ 
							
							
							//total salidas 
							"<tr bgcolor='#FA5858'> <td width='30%'><div id='tsal'>TOTAL SALIDAS</div></td>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='tsal7' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tsal8' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tsal9' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tsal10' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tsal11' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tsal12' type='text' size='1' disabled='disabled'/></td>"
							+"<td  width='2.8%'><input id='tsal13' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tsalt1' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tsal14' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tsal15' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tsal16' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tsal17' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tsal18' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tsal19' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tsalt2' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tsal20' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tsal21' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tsal22' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tsal23' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tsal00' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tsal01' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tsal02' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tsal03' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tsal04' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tsal05' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tsal06' type='text' size='1' disabled='disabled'/></td>"													
							+"<td  width='2.8%'><input id='tsalt3' type='text' size='1' disabled='disabled'/></td>" 
							+"</tr></table></td></tr>" +
							
							
					        //fin liquidos salidas precargado todos los dias 
							
							
							// liquidos entradas precargado todos los dias 
							"<tr  bgcolor='#2E9AFE'> <td width='100%'><div id='entradas'>ENTRADAS</div></td>" +
							"<td>" +
							"<input id='nom_sonda' type='text' style='border: 1px solid #585858'/>"+
							"<input type='button' id='aparecer_sondas' value='Agregar Sonda' onclick='actualizar_ult_sonda()' /></td>"+
							"</tr>"+	
							//sonda
							"<tr > <td width='30%'><div id='sonda'>SONDA</div></td>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='sonent7' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='sonent8' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='sonent9' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='sonent10' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='sonent11' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='sonent12' type='text' size='1' disabled='disabled'/></td>"
							+"<td  width='2.8%'><input id='sonent13' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='sonentt1' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='sonent14' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='sonent15' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='sonent16' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='sonent17' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='sonent18' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='sonent19' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='sonentt2' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='sonent20' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='sonent21' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='sonent22' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='sonent23' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='sonent00' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='sonent01' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='sonent02' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='sonent03' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='sonent04' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='sonent05' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='sonent06' type='text' size='1' disabled='disabled'/></td>"													
							+"<td  width='2.8%'><input id='sonentt3' type='text' size='1' disabled='disabled'/></td>" 
							+"</tr></table></td></tr>" +	
							
							
							//sonda1
							"<tr id='sonda1' style='display: none'> <td width='30%'><div id='sond1'>sonda1</div>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='sonda1_7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda1_7,tent7,7)'; onblur='prueba(sonda1_7)'/></td>" 
							+"<td  width='2.8%'><input id='sonda1_8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda1_8,tent8,8)'; onblur='prueba(sonda1_8)'/></td>" 
							+"<td  width='2.8%'><input id='sonda1_9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda1_9,tent9,9)'; onblur='prueba(sonda1_9)'/></td>" 
							+"<td width='2.8%'><input id='sonda1_10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda1_10,tent10,10)'; onblur='prueba(sonda1_10)'/></td>" 
							+"<td  width='2.8%'><input id='sonda1_11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda1_11,tent11,11)'; onblur='prueba(sonda1_11)'/></td>" 
							+"<td  width='2.8%'><input id='sonda1_12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda1_12,tent12,12)'; onblur='prueba(sonda1_12)'/></td>"
							+"<td  width='2.8%'><input id='sonda1_13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda1_13,tent13,13)'; onblur='prueba(sonda1_13)'/></td>" 
							+"<td  width='2.8%'><input id='sonda1_t1' type='text' size='1' disabled='disabled'/></td>" 
							+"<td width='2.8%'><input id='sonda1_14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda1_14,tent14,14)'; onblur='prueba(sonda1_14)'/></td>" 
							+"<td  width='2.8%'><input id='sonda1_15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda1_15,tent15,15)'; onblur='prueba(sonda1_15)'/></td>" 
							+"<td  width='2.8%'><input id='sonda1_16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda1_16,tent16,16)'; onblur='prueba(sonda1_16)'/></td>" 
							+"<td  width='2.8%'><input id='sonda1_17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda1_17,tent17,17)'; onblur='prueba(sonda1_17,)'/></td>" 
							+"<td width='2.8%'><input id='sonda1_18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda1_18,tent18,18)'; onblur='prueba(sonda1_18)'/></td>" 
							+"<td  width='2.8%'><input id='sonda1_19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda1_19,tent19,19)'; onblur='prueba(sonda1_19)'/></td>" 
							+"<td  width='2.8%'><input id='sonda1_t2' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='sonda1_20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda1_20,tent20,20)'; onblur='prueba(sonda1_20)'/></td>" 
							+"<td  width='2.8%'><input id='sonda1_21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda1_21,tent21,21)'; onblur='prueba(sonda1_21)'/></td>" 
							+"<td width='2.8%'><input id='sonda1_22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda1_22,tent22,22)'; onblur='prueba(sonda1_22)'/></td>" 
							+"<td  width='2.8%'><input id='sonda1_23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda1_23,tent23,23)'; onblur='prueba(sonda1_23)'/></td>" 
							+"<td  width='2.8%'><input id='sonda1_00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda1_00,tent00,00)'; onblur='prueba(sonda1_00)'/></td>" 
							+"<td  width='2.8%'><input id='sonda1_01' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda1_01,tent01,01)'; onblur='prueba(sonda1_01)'/></td>" 
							+"<td  width='2.8%'><input id='sonda1_02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda1_02,tent02,02)'; onblur='prueba(sonda1_02)'/></td>" 
							+"<td width='2.8%'><input id='sonda1_03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda1_03,tent03,03)'; onblur='prueba(sonda1_03)'/></td>" 
							+"<td  width='2.8%'><input id='sonda1_04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda1_04,tent04,04)'; onblur='prueba(sonda1_04)'/></td>" 
							+"<td  width='2.8%'><input id='sonda1_05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda1_05,tent05,05)'; onblur='prueba(sonda1_05)'/></td>" 
							+"<td  width='2.8%'><input id='sonda1_06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda1_06,tent06,06)'; onblur='prueba(sonda1_06)'/></td>"													
							+"<td  width='2.8%'><input id='sonda1_t3' type='text' size='1' disabled='disabled'/></td>" 
							+"</tr></table>"+
							"</td></tr>"+ 
							
							//sonda2
							"<tr id='sonda2' style='display: none'> <td width='30%'><div id='sond2'>sonda2</div>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='sonda2_7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda2_7,tent7,7)'; onblur='prueba(sonda2_7)'/></td>" 
							+"<td  width='2.8%'><input id='sonda2_8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda2_8,tent8,8)'; onblur='prueba(sonda2_8)'/></td>" 
							+"<td  width='2.8%'><input id='sonda2_9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda2_9,tent9,9)'; onblur='prueba(sonda2_9)'/></td>" 
							+"<td width='2.8%'><input id='sonda2_10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda2_10,tent10,10)'; onblur='prueba(sonda2_10)'/></td>" 
							+"<td  width='2.8%'><input id='sonda2_11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda2_11,tent11,11)'; onblur='prueba(sonda2_11)'/></td>" 
							+"<td  width='2.8%'><input id='sonda2_12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda2_12,tent12,12)'; onblur='prueba(sonda2_12)'/></td>"
							+"<td  width='2.8%'><input id='sonda2_13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda2_13,tent13,13)'; onblur='prueba(sonda2_13)'/></td>" 
							+"<td  width='2.8%'><input id='sonda2_t1' type='text' size='1' disabled='disabled'/></td>" 
							+"<td width='2.8%'><input id='sonda2_14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda2_14,tent14,14)'; onblur='prueba(sonda2_14)'/></td>" 
							+"<td  width='2.8%'><input id='sonda2_15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda2_15,tent15,15)'; onblur='prueba(sonda2_15)'/></td>" 
							+"<td  width='2.8%'><input id='sonda2_16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda2_16,tent16,16)'; onblur='prueba(sonda2_16)'/></td>" 
							+"<td  width='2.8%'><input id='sonda2_17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda2_17,tent17,17)'; onblur='prueba(sonda2_17,)'/></td>" 
							+"<td width='2.8%'><input id='sonda2_18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda2_18,tent18,18)'; onblur='prueba(sonda2_18)'/></td>" 
							+"<td  width='2.8%'><input id='sonda2_19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda2_19,tent19,19)'; onblur='prueba(sonda2_19)'/></td>" 
							+"<td  width='2.8%'><input id='sonda2_t2' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='sonda2_20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda2_20,tent20,20)'; onblur='prueba(sonda2_20)'/></td>" 
							+"<td  width='2.8%'><input id='sonda2_21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda2_21,tent21,21)'; onblur='prueba(sonda2_21)'/></td>" 
							+"<td width='2.8%'><input id='sonda2_22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda2_22,tent22,22)'; onblur='prueba(sonda2_22)'/></td>" 
							+"<td  width='2.8%'><input id='sonda2_23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda2_23,tent23,23)'; onblur='prueba(sonda2_23)'/></td>" 
							+"<td  width='2.8%'><input id='sonda2_00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda2_00,tent00,00)'; onblur='prueba(sonda2_00)'/></td>" 
							+"<td  width='2.8%'><input id='sonda2_01' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda2_01,tent01,01)'; onblur='prueba(sonda2_01)'/></td>" 
							+"<td  width='2.8%'><input id='sonda2_02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda2_02,tent02,02)'; onblur='prueba(sonda2_02)'/></td>" 
							+"<td width='2.8%'><input id='sonda2_03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda2_03,tent03,03)'; onblur='prueba(sonda2_03)'/></td>" 
							+"<td  width='2.8%'><input id='sonda2_04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda2_04,tent04,04)'; onblur='prueba(sonda2_04)'/></td>" 
							+"<td  width='2.8%'><input id='sonda2_05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda2_05,tent05,05)'; onblur='prueba(sonda2_05)'/></td>" 
							+"<td  width='2.8%'><input id='sonda2_06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda2_06,tent06,06)'; onblur='prueba(sonda2_06)'/></td>"													
							+"<td  width='2.8%'><input id='sonda2_t3' type='text' size='1' disabled='disabled'/></td>" 
							+"</tr></table>"+
							"</td></tr>"+ 
							
							//sonda3
							"<tr id='sonda3' style='display: none'> <td width='30%'><div id='sond3'>sonda3</div>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='sonda3_7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda3_7,tent7,7)'; onblur='prueba(sonda3_7)'/></td>" 
							+"<td  width='2.8%'><input id='sonda3_8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda3_8,tent8,8)'; onblur='prueba(sonda3_8)'/></td>" 
							+"<td  width='2.8%'><input id='sonda3_9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda3_9,tent9,9)'; onblur='prueba(sonda3_9)'/></td>" 
							+"<td width='2.8%'><input id='sonda3_10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda3_10,tent10,10)'; onblur='prueba(sonda3_10)'/></td>" 
							+"<td  width='2.8%'><input id='sonda3_11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda3_11,tent11,11)'; onblur='prueba(sonda3_11)'/></td>" 
							+"<td  width='2.8%'><input id='sonda3_12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda3_12,tent12,12)'; onblur='prueba(sonda3_12)'/></td>"
							+"<td  width='2.8%'><input id='sonda3_13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda3_13,tent13,13)'; onblur='prueba(sonda3_13)'/></td>" 
							+"<td  width='2.8%'><input id='sonda3_t1' type='text' size='1' disabled='disabled'/></td>" 
							+"<td width='2.8%'><input id='sonda3_14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda3_14,tent14,14)'; onblur='prueba(sonda3_14)'/></td>" 
							+"<td  width='2.8%'><input id='sonda3_15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda3_15,tent15,15)'; onblur='prueba(sonda3_15)'/></td>" 
							+"<td  width='2.8%'><input id='sonda3_16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda3_16,tent16,16)'; onblur='prueba(sonda3_16)'/></td>" 
							+"<td  width='2.8%'><input id='sonda3_17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda3_17,tent17,17)'; onblur='prueba(sonda3_17,)'/></td>" 
							+"<td width='2.8%'><input id='sonda3_18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda3_18,tent18,18)'; onblur='prueba(sonda3_18)'/></td>" 
							+"<td  width='2.8%'><input id='sonda3_19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda3_19,tent19,19)'; onblur='prueba(sonda3_19)'/></td>" 
							+"<td  width='2.8%'><input id='sonda3_t2' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='sonda3_20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda3_20,tent20,20)'; onblur='prueba(sonda3_20)'/></td>" 
							+"<td  width='2.8%'><input id='sonda3_21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda3_21,tent21,21)'; onblur='prueba(sonda3_21)'/></td>" 
							+"<td width='2.8%'><input id='sonda3_22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda3_22,tent22,22)'; onblur='prueba(sonda3_22)'/></td>" 
							+"<td  width='2.8%'><input id='sonda3_23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda3_23,tent23,23)'; onblur='prueba(sonda3_23)'/></td>" 
							+"<td  width='2.8%'><input id='sonda3_00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda3_00,tent00,00)'; onblur='prueba(sonda3_00)'/></td>" 
							+"<td  width='2.8%'><input id='sonda3_01' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda3_01,tent01,01)'; onblur='prueba(sonda3_01)'/></td>" 
							+"<td  width='2.8%'><input id='sonda3_02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda3_02,tent02,02)'; onblur='prueba(sonda3_02)'/></td>" 
							+"<td width='2.8%'><input id='sonda3_03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda3_03,tent03,03)'; onblur='prueba(sonda3_03)'/></td>" 
							+"<td  width='2.8%'><input id='sonda3_04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda3_04,tent04,04)'; onblur='prueba(sonda3_04)'/></td>" 
							+"<td  width='2.8%'><input id='sonda3_05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda3_05,tent05,05)'; onblur='prueba(sonda3_05)'/></td>" 
							+"<td  width='2.8%'><input id='sonda3_06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda3_06,tent06,06)'; onblur='prueba(sonda3_06)'/></td>"													
							+"<td  width='2.8%'><input id='sonda3_t3' type='text' size='1' disabled='disabled'/></td>" 
							+"</tr></table>"+
							"</td></tr>"+ 
							
							//sonda4
							"<tr id='sonda4' style='display: none'> <td width='30%'><div id='sond4'>sonda4</div>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='sonda4_7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda4_7,tent7,7)'; onblur='prueba(sonda4_7)'/></td>" 
							+"<td  width='2.8%'><input id='sonda4_8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda4_8,tent8,8)'; onblur='prueba(sonda4_8)'/></td>" 
							+"<td  width='2.8%'><input id='sonda4_9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda4_9,tent9,9)'; onblur='prueba(sonda4_9)'/></td>" 
							+"<td width='2.8%'><input id='sonda4_10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda4_10,tent10,10)'; onblur='prueba(sonda4_10)'/></td>" 
							+"<td  width='2.8%'><input id='sonda4_11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda4_11,tent11,11)'; onblur='prueba(sonda4_11)'/></td>" 
							+"<td  width='2.8%'><input id='sonda4_12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda4_12,tent12,12)'; onblur='prueba(sonda4_12)'/></td>"
							+"<td  width='2.8%'><input id='sonda4_13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda4_13,tent13,13)'; onblur='prueba(sonda4_13)'/></td>" 
							+"<td  width='2.8%'><input id='sonda4_t1' type='text' size='1' disabled='disabled'/></td>" 
							+"<td width='2.8%'><input id='sonda4_14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda4_14,tent14,14)'; onblur='prueba(sonda4_14)'/></td>" 
							+"<td  width='2.8%'><input id='sonda4_15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda4_15,tent15,15)'; onblur='prueba(sonda4_15)'/></td>" 
							+"<td  width='2.8%'><input id='sonda4_16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda4_16,tent16,16)'; onblur='prueba(sonda4_16)'/></td>" 
							+"<td  width='2.8%'><input id='sonda4_17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda4_17,tent17,17)'; onblur='prueba(sonda4_17,)'/></td>" 
							+"<td width='2.8%'><input id='sonda4_18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda4_18,tent18,18)'; onblur='prueba(sonda4_18)'/></td>" 
							+"<td  width='2.8%'><input id='sonda4_19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda4_19,tent19,19)'; onblur='prueba(sonda4_19)'/></td>" 
							+"<td  width='2.8%'><input id='sonda4_t2' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='sonda4_20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda4_20,tent20,20)'; onblur='prueba(sonda4_20)'/></td>" 
							+"<td  width='2.8%'><input id='sonda4_21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda4_21,tent21,21)'; onblur='prueba(sonda4_21)'/></td>" 
							+"<td width='2.8%'><input id='sonda4_22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda4_22,tent22,22)'; onblur='prueba(sonda4_22)'/></td>" 
							+"<td  width='2.8%'><input id='sonda4_23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda4_23,tent23,23)'; onblur='prueba(sonda4_23)'/></td>" 
							+"<td  width='2.8%'><input id='sonda4_00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda4_00,tent00,00)'; onblur='prueba(sonda4_00)'/></td>" 
							+"<td  width='2.8%'><input id='sonda4_01' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda4_01,tent01,01)'; onblur='prueba(sonda4_01)'/></td>" 
							+"<td  width='2.8%'><input id='sonda4_02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda4_02,tent02,02)'; onblur='prueba(sonda4_02)'/></td>" 
							+"<td width='2.8%'><input id='sonda4_03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda4_03,tent03,03)'; onblur='prueba(sonda4_03)'/></td>" 
							+"<td  width='2.8%'><input id='sonda4_04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda4_04,tent04,04)'; onblur='prueba(sonda4_04)'/></td>" 
							+"<td  width='2.8%'><input id='sonda4_05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda4_05,tent05,05)'; onblur='prueba(sonda4_05)'/></td>" 
							+"<td  width='2.8%'><input id='sonda4_06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda4_06,tent06,06)'; onblur='prueba(sonda4_06)'/></td>"													
							+"<td  width='2.8%'><input id='sonda4_t3' type='text' size='1' disabled='disabled'/></td>" 
							+"</tr></table>"+
							"</td></tr>"+ 
							
							//sonda5
							"<tr id='sonda5' style='display: none'> <td width='30%'><div id='sond5'>sonda5</div>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='sonda5_7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda5_7,tent7,7)'; onblur='prueba(sonda5_7)'/></td>" 
							+"<td  width='2.8%'><input id='sonda5_8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda5_8,tent8,8)'; onblur='prueba(sonda5_8)'/></td>" 
							+"<td  width='2.8%'><input id='sonda5_9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda5_9,tent9,9)'; onblur='prueba(sonda5_9)'/></td>" 
							+"<td width='2.8%'><input id='sonda5_10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda5_10,tent10,10)'; onblur='prueba(sonda5_10)'/></td>" 
							+"<td  width='2.8%'><input id='sonda5_11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda5_11,tent11,11)'; onblur='prueba(sonda5_11)'/></td>" 
							+"<td  width='2.8%'><input id='sonda5_12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda5_12,tent12,12)'; onblur='prueba(sonda5_12)'/></td>"
							+"<td  width='2.8%'><input id='sonda5_13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda5_13,tent13,13)'; onblur='prueba(sonda5_13)'/></td>" 
							+"<td  width='2.8%'><input id='sonda5_t1' type='text' size='1' disabled='disabled'/></td>" 
							+"<td width='2.8%'><input id='sonda5_14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda5_14,tent14,14)'; onblur='prueba(sonda5_14)'/></td>" 
							+"<td  width='2.8%'><input id='sonda5_15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda5_15,tent15,15)'; onblur='prueba(sonda5_15)'/></td>" 
							+"<td  width='2.8%'><input id='sonda5_16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda5_16,tent16,16)'; onblur='prueba(sonda5_16)'/></td>" 
							+"<td  width='2.8%'><input id='sonda5_17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda5_17,tent17,17)'; onblur='prueba(sonda5_17,)'/></td>" 
							+"<td width='2.8%'><input id='sonda5_18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda5_18,tent18,18)'; onblur='prueba(sonda5_18)'/></td>" 
							+"<td  width='2.8%'><input id='sonda5_19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda5_19,tent19,19)'; onblur='prueba(sonda5_19)'/></td>" 
							+"<td  width='2.8%'><input id='sonda5_t2' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='sonda5_20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda5_20,tent20,20)'; onblur='prueba(sonda5_20)'/></td>" 
							+"<td  width='2.8%'><input id='sonda5_21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda5_21,tent21,21)'; onblur='prueba(sonda5_21)'/></td>" 
							+"<td width='2.8%'><input id='sonda5_22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda5_22,tent22,22)'; onblur='prueba(sonda5_22)'/></td>" 
							+"<td  width='2.8%'><input id='sonda5_23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda5_23,tent23,23)'; onblur='prueba(sonda5_23)'/></td>" 
							+"<td  width='2.8%'><input id='sonda5_00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda5_00,tent00,00)'; onblur='prueba(sonda5_00)'/></td>" 
							+"<td  width='2.8%'><input id='sonda5_01' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda5_01,tent01,01)'; onblur='prueba(sonda5_01)'/></td>" 
							+"<td  width='2.8%'><input id='sonda5_02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda5_02,tent02,02)'; onblur='prueba(sonda5_02)'/></td>" 
							+"<td width='2.8%'><input id='sonda5_03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda5_03,tent03,03)'; onblur='prueba(sonda5_03)'/></td>" 
							+"<td  width='2.8%'><input id='sonda5_04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda5_04,tent04,04)'; onblur='prueba(sonda5_04)'/></td>" 
							+"<td  width='2.8%'><input id='sonda5_05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda5_05,tent05,05)'; onblur='prueba(sonda5_05)'/></td>" 
							+"<td  width='2.8%'><input id='sonda5_06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda5_06,tent06,06)'; onblur='prueba(sonda5_06)'/></td>"													
							+"<td  width='2.8%'><input id='sonda5_t3' type='text' size='1' disabled='disabled'/></td>" 
							+"</tr></table>"+
							"</td></tr>"+ 
							
							//sonda6
							"<tr id='sonda6' style='display: none'> <td width='30%'><div id='sond6'>sonda6</div>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='sonda6_7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda6_7,tent7,7)'; onblur='prueba(sonda6_7)'/></td>" 
							+"<td  width='2.8%'><input id='sonda6_8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda6_8,tent8,8)'; onblur='prueba(sonda6_8)'/></td>" 
							+"<td  width='2.8%'><input id='sonda6_9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda6_9,tent9,9)'; onblur='prueba(sonda6_9)'/></td>" 
							+"<td width='2.8%'><input id='sonda6_10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda6_10,tent10,10)'; onblur='prueba(sonda6_10)'/></td>" 
							+"<td  width='2.8%'><input id='sonda6_11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda6_11,tent11,11)'; onblur='prueba(sonda6_11)'/></td>" 
							+"<td  width='2.8%'><input id='sonda6_12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda6_12,tent12,12)'; onblur='prueba(sonda6_12)'/></td>"
							+"<td  width='2.8%'><input id='sonda6_13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda6_13,tent13,13)'; onblur='prueba(sonda6_13)'/></td>" 
							+"<td  width='2.8%'><input id='sonda6_t1' type='text' size='1' disabled='disabled'/></td>" 
							+"<td width='2.8%'><input id='sonda6_14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda6_14,tent14,14)'; onblur='prueba(sonda6_14)'/></td>" 
							+"<td  width='2.8%'><input id='sonda6_15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda6_15,tent15,15)'; onblur='prueba(sonda6_15)'/></td>" 
							+"<td  width='2.8%'><input id='sonda6_16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda6_16,tent16,16)'; onblur='prueba(sonda6_16)'/></td>" 
							+"<td  width='2.8%'><input id='sonda6_17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda6_17,tent17,17)'; onblur='prueba(sonda6_17,)'/></td>" 
							+"<td width='2.8%'><input id='sonda6_18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda6_18,tent18,18)'; onblur='prueba(sonda6_18)'/></td>" 
							+"<td  width='2.8%'><input id='sonda6_19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda6_19,tent19,19)'; onblur='prueba(sonda6_19)'/></td>" 
							+"<td  width='2.8%'><input id='sonda6_t2' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='sonda6_20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda6_20,tent20,20)'; onblur='prueba(sonda6_20)'/></td>" 
							+"<td  width='2.8%'><input id='sonda6_21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda6_21,tent21,21)'; onblur='prueba(sonda6_21)'/></td>" 
							+"<td width='2.8%'><input id='sonda6_22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda6_22,tent22,22)'; onblur='prueba(sonda6_22)'/></td>" 
							+"<td  width='2.8%'><input id='sonda6_23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda6_23,tent23,23)'; onblur='prueba(sonda6_23)'/></td>" 
							+"<td  width='2.8%'><input id='sonda6_00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda6_00,tent00,00)'; onblur='prueba(sonda6_00)'/></td>" 
							+"<td  width='2.8%'><input id='sonda6_01' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda6_01,tent01,01)'; onblur='prueba(sonda6_01)'/></td>" 
							+"<td  width='2.8%'><input id='sonda6_02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda6_02,tent02,02)'; onblur='prueba(sonda6_02)'/></td>" 
							+"<td width='2.8%'><input id='sonda6_03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda6_03,tent03,03)'; onblur='prueba(sonda6_03)'/></td>" 
							+"<td  width='2.8%'><input id='sonda6_04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda6_04,tent04,04)'; onblur='prueba(sonda6_04)'/></td>" 
							+"<td  width='2.8%'><input id='sonda6_05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda6_05,tent05,05)'; onblur='prueba(sonda6_05)'/></td>" 
							+"<td  width='2.8%'><input id='sonda6_06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda6_06,tent06,06)'; onblur='prueba(sonda6_06)'/></td>"													
							+"<td  width='2.8%'><input id='sonda6_t3' type='text' size='1' disabled='disabled'/></td>" 
							+"</tr></table>"+
							"</td></tr>"+ 
							
							//sonda7
							"<tr id='sonda7' style='display: none'> <td width='30%'><div id='sond7'>sonda7</div>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='sonda7_7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda7_7,tent7,7)'; onblur='prueba(sonda7_7)'/></td>" 
							+"<td  width='2.8%'><input id='sonda7_8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda7_8,tent8,8)'; onblur='prueba(sonda7_8)'/></td>" 
							+"<td  width='2.8%'><input id='sonda7_9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda7_9,tent9,9)'; onblur='prueba(sonda7_9)'/></td>" 
							+"<td width='2.8%'><input id='sonda7_10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda7_10,tent10,10)'; onblur='prueba(sonda7_10)'/></td>" 
							+"<td  width='2.8%'><input id='sonda7_11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda7_11,tent11,11)'; onblur='prueba(sonda7_11)'/></td>" 
							+"<td  width='2.8%'><input id='sonda7_12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda7_12,tent12,12)'; onblur='prueba(sonda7_12)'/></td>"
							+"<td  width='2.8%'><input id='sonda7_13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda7_13,tent13,13)'; onblur='prueba(sonda7_13)'/></td>" 
							+"<td  width='2.8%'><input id='sonda7_t1' type='text' size='1' disabled='disabled'/></td>" 
							+"<td width='2.8%'><input id='sonda7_14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda7_14,tent14,14)'; onblur='prueba(sonda7_14)'/></td>" 
							+"<td  width='2.8%'><input id='sonda7_15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda7_15,tent15,15)'; onblur='prueba(sonda7_15)'/></td>" 
							+"<td  width='2.8%'><input id='sonda7_16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda7_16,tent16,16)'; onblur='prueba(sonda7_16)'/></td>" 
							+"<td  width='2.8%'><input id='sonda7_17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda7_17,tent17,17)'; onblur='prueba(sonda7_17,)'/></td>" 
							+"<td width='2.8%'><input id='sonda7_18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda7_18,tent18,18)'; onblur='prueba(sonda7_18)'/></td>" 
							+"<td  width='2.8%'><input id='sonda7_19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda7_19,tent19,19)'; onblur='prueba(sonda7_19)'/></td>" 
							+"<td  width='2.8%'><input id='sonda7_t2' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='sonda7_20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda7_20,tent20,20)'; onblur='prueba(sonda7_20)'/></td>" 
							+"<td  width='2.8%'><input id='sonda7_21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda7_21,tent21,21)'; onblur='prueba(sonda7_21)'/></td>" 
							+"<td width='2.8%'><input id='sonda7_22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda7_22,tent22,22)'; onblur='prueba(sonda7_22)'/></td>" 
							+"<td  width='2.8%'><input id='sonda7_23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda7_23,tent23,23)'; onblur='prueba(sonda7_23)'/></td>" 
							+"<td  width='2.8%'><input id='sonda7_00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda7_00,tent00,00)'; onblur='prueba(sonda7_00)'/></td>" 
							+"<td  width='2.8%'><input id='sonda7_01' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda7_01,tent01,01)'; onblur='prueba(sonda7_01)'/></td>" 
							+"<td  width='2.8%'><input id='sonda7_02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda7_02,tent02,02)'; onblur='prueba(sonda7_02)'/></td>" 
							+"<td width='2.8%'><input id='sonda7_03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda7_03,tent03,03)'; onblur='prueba(sonda7_03)'/></td>" 
							+"<td  width='2.8%'><input id='sonda7_04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda7_04,tent04,04)'; onblur='prueba(sonda7_04)'/></td>" 
							+"<td  width='2.8%'><input id='sonda7_05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda7_05,tent05,05)'; onblur='prueba(sonda7_05)'/></td>" 
							+"<td  width='2.8%'><input id='sonda7_06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda7_06,tent06,06)'; onblur='prueba(sonda7_06)'/></td>"													
							+"<td  width='2.8%'><input id='sonda7_t3' type='text' size='1' disabled='disabled'/></td>" 
							+"</tr></table>"+
							"</td></tr>"+ 
							
							//sonda8
							"<tr id='sonda8' style='display: none'> <td width='30%'><div id='sond8'>sonda8</div>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='sonda8_7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda8_7,tent7,7)'; onblur='prueba(sonda8_7)'/></td>" 
							+"<td  width='2.8%'><input id='sonda8_8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda8_8,tent8,8)'; onblur='prueba(sonda8_8)'/></td>" 
							+"<td  width='2.8%'><input id='sonda8_9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda8_9,tent9,9)'; onblur='prueba(sonda8_9)'/></td>" 
							+"<td width='2.8%'><input id='sonda8_10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda8_10,tent10,10)'; onblur='prueba(sonda8_10)'/></td>" 
							+"<td  width='2.8%'><input id='sonda8_11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda8_11,tent11,11)'; onblur='prueba(sonda8_11)'/></td>" 
							+"<td  width='2.8%'><input id='sonda8_12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda8_12,tent12,12)'; onblur='prueba(sonda8_12)'/></td>"
							+"<td  width='2.8%'><input id='sonda8_13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda8_13,tent13,13)'; onblur='prueba(sonda8_13)'/></td>" 
							+"<td  width='2.8%'><input id='sonda8_t1' type='text' size='1' disabled='disabled'/></td>" 
							+"<td width='2.8%'><input id='sonda8_14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda8_14,tent14,14)'; onblur='prueba(sonda8_14)'/></td>" 
							+"<td  width='2.8%'><input id='sonda8_15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda8_15,tent15,15)'; onblur='prueba(sonda8_15)'/></td>" 
							+"<td  width='2.8%'><input id='sonda8_16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda8_16,tent16,16)'; onblur='prueba(sonda8_16)'/></td>" 
							+"<td  width='2.8%'><input id='sonda8_17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda8_17,tent17,17)'; onblur='prueba(sonda8_17,)'/></td>" 
							+"<td width='2.8%'><input id='sonda8_18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda8_18,tent18,18)'; onblur='prueba(sonda8_18)'/></td>" 
							+"<td  width='2.8%'><input id='sonda8_19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda8_19,tent19,19)'; onblur='prueba(sonda8_19)'/></td>" 
							+"<td  width='2.8%'><input id='sonda8_t2' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='sonda8_20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda8_20,tent20,20)'; onblur='prueba(sonda8_20)'/></td>" 
							+"<td  width='2.8%'><input id='sonda8_21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda8_21,tent21,21)'; onblur='prueba(sonda8_21)'/></td>" 
							+"<td width='2.8%'><input id='sonda8_22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda8_22,tent22,22)'; onblur='prueba(sonda8_22)'/></td>" 
							+"<td  width='2.8%'><input id='sonda8_23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda8_23,tent23,23)'; onblur='prueba(sonda8_23)'/></td>" 
							+"<td  width='2.8%'><input id='sonda8_00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda8_00,tent00,00)'; onblur='prueba(sonda8_00)'/></td>" 
							+"<td  width='2.8%'><input id='sonda8_01' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda8_01,tent01,01)'; onblur='prueba(sonda8_01)'/></td>" 
							+"<td  width='2.8%'><input id='sonda8_02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda8_02,tent02,02)'; onblur='prueba(sonda8_02)'/></td>" 
							+"<td width='2.8%'><input id='sonda8_03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda8_03,tent03,03)'; onblur='prueba(sonda8_03)'/></td>" 
							+"<td  width='2.8%'><input id='sonda8_04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda8_04,tent04,04)'; onblur='prueba(sonda8_04)'/></td>" 
							+"<td  width='2.8%'><input id='sonda8_05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda8_05,tent05,05)'; onblur='prueba(sonda8_05)'/></td>" 
							+"<td  width='2.8%'><input id='sonda8_06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda8_06,tent06,06)'; onblur='prueba(sonda8_06)'/></td>"													
							+"<td  width='2.8%'><input id='sonda8_t3' type='text' size='1' disabled='disabled'/></td>" 
							+"</tr></table>"+
							"</td></tr>"+ 
							
							//sonda9
							"<tr id='sonda9' style='display: none'> <td width='30%'><div id='sond9'>sonda9</div>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='sonda9_7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda9_7,tent7,7)'; onblur='prueba(sonda9_7)'/></td>" 
							+"<td  width='2.8%'><input id='sonda9_8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda9_8,tent8,8)'; onblur='prueba(sonda9_8)'/></td>" 
							+"<td  width='2.8%'><input id='sonda9_9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda9_9,tent9,9)'; onblur='prueba(sonda9_9)'/></td>" 
							+"<td width='2.8%'><input id='sonda9_10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda9_10,tent10,10)'; onblur='prueba(sonda9_10)'/></td>" 
							+"<td  width='2.8%'><input id='sonda9_11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda9_11,tent11,11)'; onblur='prueba(sonda9_11)'/></td>" 
							+"<td  width='2.8%'><input id='sonda9_12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda9_12,tent12,12)'; onblur='prueba(sonda9_12)'/></td>"
							+"<td  width='2.8%'><input id='sonda9_13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda9_13,tent13,13)'; onblur='prueba(sonda9_13)'/></td>" 
							+"<td  width='2.8%'><input id='sonda9_t1' type='text' size='1' disabled='disabled'/></td>" 
							+"<td width='2.8%'><input id='sonda9_14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda9_14,tent14,14)'; onblur='prueba(sonda9_14)'/></td>" 
							+"<td  width='2.8%'><input id='sonda9_15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda9_15,tent15,15)'; onblur='prueba(sonda9_15)'/></td>" 
							+"<td  width='2.8%'><input id='sonda9_16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda9_16,tent16,16)'; onblur='prueba(sonda9_16)'/></td>" 
							+"<td  width='2.8%'><input id='sonda9_17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda9_17,tent17,17)'; onblur='prueba(sonda9_17,)'/></td>" 
							+"<td width='2.8%'><input id='sonda9_18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda9_18,tent18,18)'; onblur='prueba(sonda9_18)'/></td>" 
							+"<td  width='2.8%'><input id='sonda9_19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda9_19,tent19,19)'; onblur='prueba(sonda9_19)'/></td>" 
							+"<td  width='2.8%'><input id='sonda9_t2' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='sonda9_20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda9_20,tent20,20)'; onblur='prueba(sonda9_20)'/></td>" 
							+"<td  width='2.8%'><input id='sonda9_21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda9_21,tent21,21)'; onblur='prueba(sonda9_21)'/></td>" 
							+"<td width='2.8%'><input id='sonda9_22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda9_22,tent22,22)'; onblur='prueba(sonda9_22)'/></td>" 
							+"<td  width='2.8%'><input id='sonda9_23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda9_23,tent23,23)'; onblur='prueba(sonda9_23)'/></td>" 
							+"<td  width='2.8%'><input id='sonda9_00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda9_00,tent00,00)'; onblur='prueba(sonda9_00)'/></td>" 
							+"<td  width='2.8%'><input id='sonda9_01' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda9_01,tent01,01)'; onblur='prueba(sonda9_01)'/></td>" 
							+"<td  width='2.8%'><input id='sonda9_02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda9_02,tent02,02)'; onblur='prueba(sonda9_02)'/></td>" 
							+"<td width='2.8%'><input id='sonda9_03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda9_03,tent03,03)'; onblur='prueba(sonda9_03)'/></td>" 
							+"<td  width='2.8%'><input id='sonda9_04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda9_04,tent04,04)'; onblur='prueba(sonda9_04)'/></td>" 
							+"<td  width='2.8%'><input id='sonda9_05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda9_05,tent05,05)'; onblur='prueba(sonda9_05)'/></td>" 
							+"<td  width='2.8%'><input id='sonda9_06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda9_06,tent06,06)'; onblur='prueba(sonda9_06)'/></td>"													
							+"<td  width='2.8%'><input id='sonda9_t3' type='text' size='1' disabled='disabled'/></td>" 
							+"</tr></table>"+
							"</td></tr>"+ 
							
							//sonda10
							"<tr id='sonda10' style='display: none'> <td width='30%'><div id='sond10'>sonda10</div>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='sonda10_7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda10_7,tent7,7)'; onblur='prueba(sonda10_7)'/></td>" 
							+"<td  width='2.8%'><input id='sonda10_8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda10_8,tent8,8)'; onblur='prueba(sonda10_8)'/></td>" 
							+"<td  width='2.8%'><input id='sonda10_9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda10_9,tent9,9)'; onblur='prueba(sonda10_9)'/></td>" 
							+"<td width='2.8%'><input id='sonda10_10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda10_10,tent10,10)'; onblur='prueba(sonda10_10)'/></td>" 
							+"<td  width='2.8%'><input id='sonda10_11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda10_11,tent11,11)'; onblur='prueba(sonda10_11)'/></td>" 
							+"<td  width='2.8%'><input id='sonda10_12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda10_12,tent12,12)'; onblur='prueba(sonda10_12)'/></td>"
							+"<td  width='2.8%'><input id='sonda10_13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda10_13,tent13,13)'; onblur='prueba(sonda10_13)'/></td>" 
							+"<td  width='2.8%'><input id='sonda10_t1' type='text' size='1' disabled='disabled'/></td>" 
							+"<td width='2.8%'><input id='sonda10_14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda10_14,tent14,14)'; onblur='prueba(sonda10_14)'/></td>" 
							+"<td  width='2.8%'><input id='sonda10_15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda10_15,tent15,15)'; onblur='prueba(sonda10_15)'/></td>" 
							+"<td  width='2.8%'><input id='sonda10_16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda10_16,tent16,16)'; onblur='prueba(sonda10_16)'/></td>" 
							+"<td  width='2.8%'><input id='sonda10_17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda10_17,tent17,17)'; onblur='prueba(sonda10_17,)'/></td>" 
							+"<td width='2.8%'><input id='sonda10_18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda10_18,tent18,18)'; onblur='prueba(sonda10_18)'/></td>" 
							+"<td  width='2.8%'><input id='sonda10_19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda10_19,tent19,19)'; onblur='prueba(sonda10_19)'/></td>" 
							+"<td  width='2.8%'><input id='sonda10_t2' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='sonda10_20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda10_20,tent20,20)'; onblur='prueba(sonda10_20)'/></td>" 
							+"<td  width='2.8%'><input id='sonda10_21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda10_21,tent21,21)'; onblur='prueba(sonda10_21)'/></td>" 
							+"<td width='2.8%'><input id='sonda10_22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda10_22,tent22,22)'; onblur='prueba(sonda10_22)'/></td>" 
							+"<td  width='2.8%'><input id='sonda10_23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda10_23,tent23,23)'; onblur='prueba(sonda10_23)'/></td>" 
							+"<td  width='2.8%'><input id='sonda10_00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda10_00,tent00,00)'; onblur='prueba(sonda10_00)'/></td>" 
							+"<td  width='2.8%'><input id='sonda10_01' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda10_01,tent01,01)'; onblur='prueba(sonda10_01)'/></td>" 
							+"<td  width='2.8%'><input id='sonda10_02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda10_02,tent02,02)'; onblur='prueba(sonda10_02)'/></td>" 
							+"<td width='2.8%'><input id='sonda10_03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda10_03,tent03,03)'; onblur='prueba(sonda10_03)'/></td>" 
							+"<td  width='2.8%'><input id='sonda10_04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda10_04,tent04,04)'; onblur='prueba(sonda10_04)'/></td>" 
							+"<td  width='2.8%'><input id='sonda10_05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda10_05,tent05,05)'; onblur='prueba(sonda10_05)'/></td>" 
							+"<td  width='2.8%'><input id='sonda10_06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda10_06,tent06,06)'; onblur='prueba(sonda10_06)'/></td>"													
							+"<td  width='2.8%'><input id='sonda10_t3' type='text' size='1' disabled='disabled'/></td>" 
							+"</tr></table>"+
							"</td></tr>"+ 
							
							
							//sANGRE
							"<tr> <td width='30%'><div id='sangre'>SANGRE</div>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='sang7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sang7,tent7,7)'; onblur='prueba(sang7)'/></td>" 
							+"<td  width='2.8%'><input id='sang8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sang8,tent8,8)'; onblur='prueba(sang8)'/></td>" 
							+"<td  width='2.8%'><input id='sang9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sang9,tent9,9)'; onblur='prueba(sang9)'/></td>" 
							+"<td width='2.8%'><input id='sang10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sang10,tent10,10)'; onblur='prueba(sang10)'/></td>" 
							+"<td  width='2.8%'><input id='sang11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sang11,tent11,11)'; onblur='prueba(sang11)'/></td>" 
							+"<td  width='2.8%'><input id='sang12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sang12,tent12,12)'; onblur='prueba(sang12)'/></td>"
							+"<td  width='2.8%'><input id='sang13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sang13,tent13,13)'; onblur='prueba(sang13)'/></td>" 
							+"<td  width='2.8%'><input id='sangt1' type='text' size='1' disabled='disabled'/></td>" 
							+"<td width='2.8%'><input id='sang14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sang14,tent14,14)'; onblur='prueba(sang14)'/></td>" 
							+"<td  width='2.8%'><input id='sang15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sang15,tent15,15)'; onblur='prueba(sang15)'/></td>" 
							+"<td  width='2.8%'><input id='sang16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sang16,tent16,16)'; onblur='prueba(sang16)'/></td>" 
							+"<td  width='2.8%'><input id='sang17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sang17,tent17,17)'; onblur='prueba(sang17)'/></td>" 
							+"<td width='2.8%'><input id='sang18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sang18,tent18,18)'; onblur='prueba(sang18)'/></td>" 
							+"<td  width='2.8%'><input id='sang19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sang19,tent19,19)'; onblur='prueba(sang19)'/></td>" 
							+"<td  width='2.8%'><input id='sangt2' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='sang20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sang20,tent20,20)'; onblur='prueba(sang20)'/></td>" 
							+"<td  width='2.8%'><input id='sang21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sang21,tent21,21)'; onblur='prueba(sang21)'/></td>" 
							+"<td width='2.8%'><input id='sang22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sang22,tent22,22)'; onblur='prueba(sang22)'/></td>" 
							+"<td  width='2.8%'><input id='sang23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sang23,tent23,23)'; onblur='prueba(sang23)'/></td>" 
							+"<td  width='2.8%'><input id='sang00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sang00,tent00,00)'; onblur='prueba(sang00)'/></td>" 
							+"<td  width='2.8%'><input id='sang01' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sang01,tent01,01)'; onblur='prueba(sang01)'/></td>" 
							+"<td  width='2.8%'><input id='sang02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sang02,tent02,02)'; onblur='prueba(sang02)'/></td>" 
							+"<td width='2.8%'><input id='sang03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sang03,tent03,03)'; onblur='prueba(sang03)'/></td>" 
							+"<td  width='2.8%'><input id='sang04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sang04,tent04,04)'; onblur='prueba(sang04)'/></td>" 
							+"<td  width='2.8%'><input id='sang05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sang05,tent05,05)'; onblur='prueba(sang05)'/></td>" 
							+"<td  width='2.8%'><input id='sang06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sang06,tent06,06)'; onblur='prueba(sang06)'/></td>"													
							+"<td  width='2.8%'><input id='sangt3' type='text' size='1' disabled='disabled'/></td>" 
							+"</tr></table>"+
							"</td></tr>"+
							//PLASMA
						    "<tr> <td width='30%'><div id='plasma'>PLASMA</div>" +
						    "<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='plasm7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(plasm7,tent7,7)'; onblur='prueba(plasm7)'/></td>" 
							+"<td  width='2.8%'><input id='plasm8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(plasm8,tent8,8)'; onblur='prueba(plasm8)'/></td>" 
							+"<td  width='2.8%'><input id='plasm9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(plasm9,tent9,9)'; onblur='prueba(plasm9)'/></td>" 
							+"<td width='2.8%'><input id='plasm10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(plasm10,tent10,10)'; onblur='prueba(plasm10)'/></td>" 
							+"<td  width='2.8%'><input id='plasm11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(plasm11,tent11,11)'; onblur='prueba(plasm11)'/></td>" 
							+"<td  width='2.8%'><input id='plasm12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(plasm12,tent12,12)'; onblur='prueba(plasm12)'/></td>"
							+"<td  width='2.8%'><input id='plasm13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(plasm13,tent13,13)'; onblur='prueba(plasm13)'/></td>" 
							+"<td  width='2.8%'><input id='plasmt1' type='text' size='1' disabled='disabled'/></td>" 
							+"<td width='2.8%'><input id='plasm14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(plasm14,tent14,14)'; onblur='prueba(plasm14)'/></td>" 
							+"<td  width='2.8%'><input id='plasm15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(plasm15,tent15,15)'; onblur='prueba(plasm15)'/></td>" 
							+"<td  width='2.8%'><input id='plasm16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(plasm16,tent16,16)'; onblur='prueba(plasm16)'/></td>" 
							+"<td  width='2.8%'><input id='plasm17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(plasm17,tent17,17)'; onblur='prueba(plasm17)'/></td>" 
							+"<td width='2.8%'><input id='plasm18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(plasm18,tent18,18)'; onblur='prueba(plasm18)'/></td>" 
							+"<td  width='2.8%'><input id='plasm19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(plasm19,tent19,19)'; onblur='prueba(plasm19)'/></td>" 
							+"<td  width='2.8%'><input id='plasmt2' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='plasm20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(plasm20,tent20,20)'; onblur='prueba(plasm20)'/></td>" 
							+"<td  width='2.8%'><input id='plasm21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(plasm21,tent21,21)'; onblur='prueba(plasm21)'/></td>" 
							+"<td width='2.8%'><input id='plasm22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(plasm22,tent22,22)'; onblur='prueba(plasm22)'/></td>" 
							+"<td  width='2.8%'><input id='plasm23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(plasm23,tent23,23)'; onblur='prueba(plasm23)'/></td>" 
							+"<td  width='2.8%'><input id='plasm00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(plasm00,tent00,00)'; onblur='prueba(plasm00)'/></td>" 
							+"<td  width='2.8%'><input id='plasm01' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(plasm01,tent01,01)'; onblur='prueba(plasm01)'/></td>" 
							+"<td  width='2.8%'><input id='plasm02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(plasm02,tent02,02)'; onblur='prueba(plasm02)'/></td>" 
							+"<td width='2.8%'><input id='plasm03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(plasm03,tent03,03)'; onblur='prueba(plasm03)'/></td>" 
							+"<td  width='2.8%'><input id='plasm04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(plasm04,tent04,04)'; onblur='prueba(plasm04)'/></td>" 
							+"<td  width='2.8%'><input id='plasm05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(plasm05,tent05,05)'; onblur='prueba(plasm05)'/></td>" 
							+"<td  width='2.8%'><input id='plasm06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(plasm06,tent06,06)'; onblur='prueba(plasm06)'/></td>"													
							+"<td  width='2.8%'><input id='plasmt3' type='text' size='1' disabled='disabled'/></td>" 
							+"</tr></table>"+
						    "</td></tr>"+
						    //COLOIDES
							"<tr> <td width='30%'><div id='coloides'>COLOIDES</div>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='col7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(col7,tent7,7)'; onblur='prueba(col7)'/></td>" 
							+"<td  width='2.8%'><input id='col8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(col8,tent8,8)'; onblur='prueba(col8)'/></td>" 
							+"<td  width='2.8%'><input id='col9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(col9,tent9,9)'; onblur='prueba(col9)'/></td>" 
							+"<td width='2.8%'><input id='col10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(col10,tent10,10)'; onblur='prueba(col10)'/></td>" 
							+"<td  width='2.8%'><input id='col11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(col11,tent11,11)'; onblur='prueba(col11)'/></td>" 
							+"<td  width='2.8%'><input id='col12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(col12,tent12,12)'; onblur='prueba(col12)'/></td>"
							+"<td  width='2.8%'><input id='col13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(col13,tent13,13)'; onblur='prueba(col13)'/></td>" 
							+"<td  width='2.8%'><input id='colt1' type='text' size='1' disabled='disabled'/></td>" 
							+"<td width='2.8%'><input id='col14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(col14,tent14,14)'; onblur='prueba(col14)'/></td>" 
							+"<td  width='2.8%'><input id='col15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(col15,tent15,15)'; onblur='prueba(col15)'/></td>" 
							+"<td  width='2.8%'><input id='col16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(col16,tent16,16)'; onblur='prueba(col16)'/></td>" 
							+"<td  width='2.8%'><input id='col17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(col17,tent17,17)'; onblur='prueba(col17)'/></td>" 
							+"<td width='2.8%'><input id='col18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(col18,tent18,18)'; onblur='prueba(col18)'/></td>" 
							+"<td  width='2.8%'><input id='col19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(col19,tent19,19)'; onblur='prueba(col19)'/></td>" 
							+"<td  width='2.8%'><input id='colt2' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='col20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(col20,tent20,20)'; onblur='prueba(col20)'/></td>" 
							+"<td  width='2.8%'><input id='col21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(col21,tent21,21)'; onblur='prueba(col21)'/></td>" 
							+"<td width='2.8%'><input id='col22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(col22,tent22,22)'; onblur='prueba(col22)'/></td>" 
							+"<td  width='2.8%'><input id='col23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(col23,tent23,23)'; onblur='prueba(col23)'/></td>" 
							+"<td  width='2.8%'><input id='col00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(col00,tent00,00)'; onblur='prueba(col00)'/></td>" 
							+"<td  width='2.8%'><input id='col01' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(col01,tent01,01)'; onblur='prueba(col01)'/></td>" 
							+"<td  width='2.8%'><input id='col02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(col02,tent02,02)'; onblur='prueba(col02)'/></td>" 
							+"<td width='2.8%'><input id='col03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(col03,tent03,03)'; onblur='prueba(col03)'/></td>" 
							+"<td  width='2.8%'><input id='col04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(col04,tent04,04)'; onblur='prueba(col04)'/></td>" 
							+"<td  width='2.8%'><input id='col05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(col05,tent05,05)'; onblur='prueba(col05)'/></td>" 
							+"<td  width='2.8%'><input id='col06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(col06,tent06,06)'; onblur='prueba(col06)'/></td>"													
							+"<td  width='2.8%'><input id='colt3' type='text' size='1' disabled='disabled'/></td>" 
							+"</tr></table>"+
							"</td></tr>"+       
							
							
							//total entradas 
							"<tr  bgcolor='#2E9AFE'> <td width='30%'><div id='tent'>TOTAL ENTRADAS</div></td>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='tent7' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tent8' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tent9' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tent10' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tent11' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tent12' type='text' size='1' disabled='disabled'/></td>"
							+"<td  width='2.8%'><input id='tent13' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tentt1' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tent14' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tent15' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tent16' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tent17' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tent18' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tent19' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tentt2' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tent20' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tent21' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tent22' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tent23' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tent00' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tent01' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tent02' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tent03' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tent04' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tent05' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tent06' type='text' size='1' disabled='disabled'/></td>"													
							+"<td  width='2.8%'><input id='tentt3' type='text' size='1' disabled='disabled'/></td>" 
							+"</tr></table></td></tr>" +
							
							
					        //fin liquidos entradas precargado todos los dias 
							
							
							"</table>");
							

							out.print("<table width='100%' border='0'>");
							rs=mvf.DetalledeFormulacionK(CodFormK);
							
							out.print("</table>");
							out.print("</div></td></tr></table>");
							rs.getStatement().getConnection().close();
						}else{
							out.print("<div id='CarguesDiaKardex'></div>");
							out.print("<div id='DosisKardex'></div>");
							
						}
						rs2.getStatement().getConnection().close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
		}		

		
		
		//entradas y salidas todos los dias
		
		if(va.equals("CamLiq")){
			String FechaKardex=req.getParameter("FechaKardex");	
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date fecha = new java.sql.Date(fechaActual.getTime());	
			
			
			
			///////////////////
			 Calendar c1 = GregorianCalendar.getInstance();
		        System.out.println("Fecha actual: " + c1.getTime().toLocaleString());
		        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		        sdf = new SimpleDateFormat("yyyy-MM-dd");
		        c1.add(Calendar.DATE, 1);
			String Perfil=req.getParameter("Perfil");
					
			
			
				
					out.print("<table width='100%' border='0'><tr><td><div id='CarguesDiaKardex'><input type='hidden' id='txtFechaKardexActivo' value='"+FechaKardex+"'>");
					out.print("<table width='100%' border='0'><tr><td width='30%'><div id='DatosEmerg'>-</div></td>" +
							"<td width='70%'><table width='100%' border='1' ><tr><td bgcolor='#E6E6E6' colspan='17' align='center'>"+fecha+"</td><td colspan='7' bgcolor='#E6E6E6' align='center'>"+sdf.format(c1.getTime())+"</td></tr><tr></td>" +
							"<td width='2.8%'>07</td>" +"<td width='2.8%'>08</td>" +"<td width='2.8%'>09</td>" +
							"<td width='2.8%'>10</td>" +"<td width='2.8%'>11</td>" +"<td width='2.8%'>12</td>" +"<td width='2.8%'>13</td>" +
							"<td width='2.8%'>14</td>" +"<td width='2.8%'>15</td>" +"<td width='2.8%'>16</td>" +"<td width='2.8%'>17</td>" +
							"<td width='2.8%'>18</td>" +"<td width='2.8%'>19</td>" +"<td width='2.8%'>20</td>" +"<td width='2.8%'>21</td>" +
							"<td width='2.8%'>22</td>" +"<td width='2.8%'>23</td>" +"<td width='2.8%'>00</td>" +"<td width='2.8%'>01</td>" +"<td width='2.8%'>02</td>" +
							"<td width='2.8%'>03</td>" +"<td width='2.8%'>04</td>" +"<td width='2.8%'>05</td>" +"<td width='2.8%'>06</td>" +													
							"</tr></table></td></tr>"  +
							// liquidos salidas precargado todos los dias 
							"<tr bgcolor='#FA5858'> <td width='100%'><div id='salidas'>SALIDAS</div></td>" +
							"<td>" +
							"<input id='nom_drenaje' type='text' style='border: 1px solid #585858'/>"+
							"<input type='button' id='aparecer_drenajes' value='Agregar Drenaje' onclick='actualizar_ult_drenaje()' /></td>"+
							"</tr>"+	
							//orina
							"<tr> <td width='30%'><div id='orina'>ORINA</div></td>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='orn7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(orn7,tsal7,7)'; onblur='prueba(orn7)'/></td>" 
							+"<td  width='2.8%'><input id='orn8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(orn8,tsal8,8)'; onblur='prueba(orn8)'/></td>" 
							+"<td  width='2.8%'><input id='orn9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(orn9,tsal9,9)'; onblur='prueba(orn9)'/></td>" 
							+"<td  width='2.8%'><input id='orn10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(orn10,tsal10,10)'; onblur='prueba(orn10)'/></td>" 
							+"<td  width='2.8%'><input id='orn11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(orn11,tsal11,11)'; onblur='prueba(orn11)'/></td>" 
							+"<td  width='2.8%'><input id='orn12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(orn12,tsal12,12)'; onblur='prueba(orn12)'/></td>"
							+"<td  width='2.8%'><input id='orn13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(orn13,tsal13,13)'; onblur='prueba(orn13)'/></td>" 
							+"<td  width='2.8%'><input id='ornt1' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='orn14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(orn14,tsal14,14)'; onblur='prueba(orn14)'/></td>" 
							+"<td  width='2.8%'><input id='orn15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(orn15,tsal15,15)'; onblur='prueba(orn15)'/></td>" 
							+"<td  width='2.8%'><input id='orn16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(orn16,tsal16,16)'; onblur='prueba(orn16)'/></td>" 
							+"<td  width='2.8%'><input id='orn17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(orn17,tsal17,17)'; onblur='prueba(orn17)'/></td>" 
							+"<td  width='2.8%'><input id='orn18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(orn18,tsal18,18)'; onblur='prueba(orn18)'/></td>" 
							+"<td  width='2.8%'><input id='orn19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(orn19,tsal19,19)'; onblur='prueba(orn19)'/></td>" 
							+"<td  width='2.8%'><input id='ornt2' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='orn20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(orn20,tsal20,20)'; onblur='prueba(orn20)'/></td>" 
							+"<td  width='2.8%'><input id='orn21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(orn21,tsal21,21)'; onblur='prueba(orn21)'/></td>" 
							+"<td  width='2.8%'><input id='orn22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(orn22,tsal22,22)'; onblur='prueba(orn22)'/></td>" 
							+"<td  width='2.8%'><input id='orn23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(orn23,tsal23,23)'; onblur='prueba(orn23)'/></td>" 
							+"<td  width='2.8%'><input id='orn00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(orn00,tsal00,00)'; onblur='prueba(orn00)'/></td>" 
							+"<td  width='2.8%'><input id='orn01' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(orn01,tsal01,01)'; onblur='prueba(orn01)'/></td>" 
							+"<td  width='2.8%'><input id='orn02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(orn02,tsal02,02)'; onblur='prueba(orn02)'/></td>" 
							+"<td  width='2.8%'><input id='orn03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(orn03,tsal03,03)'; onblur='prueba(orn03)'/></td>" 
							+"<td  width='2.8%'><input id='orn04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(orn04,tsal04,04)'; onblur='prueba(orn04)'/></td>" 
							+"<td  width='2.8%'><input id='orn05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(orn05,tsal05,05)'; onblur='prueba(orn05)'/></td>" 
							+"<td  width='2.8%'><input id='orn06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(orn06,tsal06',06)'; onblur='prueba(orn06)'/></td>"													
							+"<td  width='2.8%'><input id='ornt3' type='text' size='1' disabled='disabled'/></td>" 
							+"</tr></table></td></tr>" +		
							//sondas
							"<tr> <td width='30%'><div id='sondas'>SONDAS</div>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='sondsal7' type='text' style='border: 1px solid #585858' size='1'  onkeyup='soloNumeros(sondsal7,tsal7,7)'; onblur='prueba(sondsal7)'/></td>" 
							+"<td  width='2.8%'><input id='sondsal8' type='text' style='border: 1px solid #585858' size='1'  onkeyup='soloNumeros(sondsal8,tsal8,8)'; onblur='prueba(sondsal8)'/></td>" 
							+"<td  width='2.8%'><input id='sondsal9' type='text' style='border: 1px solid #585858' size='1'  onkeyup='soloNumeros(sondsal9,tsal9,9)'; onblur='prueba(sondsal9)'/></td>" 
							+"<td  width='2.8%'><input id='sondsal10' type='text' style='border: 1px solid #585858' size='1'  onkeyup='soloNumeros(sondsal10,tsal10,10)'; onblur='prueba(sondsal10)'/></td>" 
							+"<td  width='2.8%'><input id='sondsal11' type='text' style='border: 1px solid #585858' size='1'  onkeyup='soloNumeros(sondsal11,tsal11,11)'; onblur='prueba(sondsal11)'/></td>" 
							+"<td  width='2.8%'><input id='sondsal12' type='text' style='border: 1px solid #585858' size='1'  onkeyup='soloNumeros(sondsal12,tsal12,12)'; onblur='prueba(sondsal12)'/></td>"
							+"<td  width='2.8%'><input id='sondsal13' type='text' style='border: 1px solid #585858' size='1'  onkeyup='soloNumeros(sondsal13,tsal13,13)'; onblur='prueba(sondsal13)'/></td>" 
							+"<td  width='2.8%'><input id='sondsalt1' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='sondsal14' type='text' style='border: 1px solid #585858' size='1'  onkeyup='soloNumeros(sondsal14,tsal14,14)'; onblur='prueba(sondsal14)'/></td>" 
							+"<td  width='2.8%'><input id='sondsal15' type='text' style='border: 1px solid #585858' size='1'  onkeyup='soloNumeros(sondsal15,tsal15,15)'; onblur='prueba(sondsal15)'/></td>" 
							+"<td  width='2.8%'><input id='sondsal16' type='text' style='border: 1px solid #585858' size='1'  onkeyup='soloNumeros(sondsal16,tsal16,16)'; onblur='prueba(sondsal16)'/></td>" 
							+"<td  width='2.8%'><input id='sondsal17' type='text' style='border: 1px solid #585858' size='1'  onkeyup='soloNumeros(sondsal17,tsal17,17)'; onblur='prueba(sondsal17)'/></td>" 
							+"<td  width='2.8%'><input id='sondsal18' type='text' style='border: 1px solid #585858' size='1'  onkeyup='soloNumeros(sondsal18,tsal18,18)'; onblur='prueba(sondsal18)'/></td>" 
							+"<td  width='2.8%'><input id='sondsal19' type='text' style='border: 1px solid #585858' size='1'  onkeyup='soloNumeros(sondsal19,tsal19,19)'; onblur='prueba(sondsal19)'/></td>" 
							+"<td  width='2.8%'><input id='sondsalt2' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='sondsal20' type='text' style='border: 1px solid #585858' size='1'  onkeyup='soloNumeros(sondsal20,tsal20,20)'; onblur='prueba(sondsal20)'/></td>" 
							+"<td  width='2.8%'><input id='sondsal21' type='text' style='border: 1px solid #585858' size='1'  onkeyup='soloNumeros(sondsal21,tsal21,21)'; onblur='prueba(sondsal21)'/></td>" 
							+"<td  width='2.8%'><input id='sondsal22' type='text' style='border: 1px solid #585858' size='1'  onkeyup='soloNumeros(sondsal22,tsal22,22)'; onblur='prueba(sondsal22)'/></td>" 
							+"<td  width='2.8%'><input id='sondsal23' type='text' style='border: 1px solid #585858' size='1'  onkeyup='soloNumeros(sondsal23,tsal23,23)'; onblur='prueba(sondsal23)'/></td>" 
							+"<td  width='2.8%'><input id='sondsal00' type='text' style='border: 1px solid #585858' size='1'  onkeyup='soloNumeros(sondsal00,tsal00,00)'; onblur='prueba(sondsal00)'/></td>" 
							+"<td  width='2.8%'><input id='sondsal01' type='text' style='border: 1px solid #585858' size='1'  onkeyup='soloNumeros(sondsal01,tsal01,01)'; onblur='prueba(sondsal01)'/></td>" 
							+"<td  width='2.8%'><input id='sondsal02' type='text' style='border: 1px solid #585858' size='1'  onkeyup='soloNumeros(sondsal02,tsal02,02)'; onblur='prueba(sondsal02)'/></td>" 
							+"<td  width='2.8%'><input id='sondsal03' type='text' style='border: 1px solid #585858' size='1'  onkeyup='soloNumeros(sondsal03,tsal03,03)'; onblur='prueba(sondsal03)'/></td>" 
							+"<td  width='2.8%'><input id='sondsal04' type='text' style='border: 1px solid #585858' size='1'  onkeyup='soloNumeros(sondsal04,tsal04,04)'; onblur='prueba(sondsal04)'/></td>" 
							+"<td  width='2.8%'><input id='sondsal05' type='text' style='border: 1px solid #585858' size='1'  onkeyup='soloNumeros(sondsal05,tsal05,05)'; onblur='prueba(sondsal05)'/></td>" 
							+"<td  width='2.8%'><input id='sondsal06' type='text' style='border: 1px solid #585858' size='1'  onkeyup='soloNumeros(sondsal06,tsal06,06)'; onblur='prueba(sondsal06)'/></td>"													
							+"<td  width='2.8%'><input id='sondsalt3' type='text' size='1' disabled='disabled'/></td>" 
							+"</tr></table>"+
							"</td></tr>"+
							//vomito
							"<tr> <td width='30%'><div id='vomito'>VOMITO</div>" +
						    "<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='vom7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(vom7,tsal7,7)'; onblur='prueba(vom7)'/></td>" 
							+"<td  width='2.8%'><input id='vom8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(vom8,tsal8,8)'; onblur='prueba(vom8)'/></td>" 
							+"<td  width='2.8%'><input id='vom9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(vom9,tsal9,9)'; onblur='prueba(vom9)'/></td>" 
							+"<td width='2.8%'><input id='vom10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(vom10,tsal10,10)'; onblur='prueba(vom10)'/></td>" 
							+"<td  width='2.8%'><input id='vom11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(vom11,tsal11,11)'; onblur='prueba(vom11)'/></td>" 
							+"<td  width='2.8%'><input id='vom12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(vom12,tsal12,12)'; onblur='prueba(vom12)'/></td>"
							+"<td  width='2.8%'><input id='vom13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(vom13,tsal13,13)'; onblur='prueba(vom13)'/></td>" 
							+"<td  width='2.8%'><input id='vomt1' type='text' size='1' disabled='disabled'/></td>" 
							+"<td width='2.8%'><input id='vom14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(vom14,tsal14,14)'; onblur='prueba(vom14)'/></td>" 
							+"<td  width='2.8%'><input id='vom15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(vom15,tsal15,15)'; onblur='prueba(vom15)'/></td>" 
							+"<td  width='2.8%'><input id='vom16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(vom16,tsal16,16)'; onblur='prueba(vom16)'/></td>" 
							+"<td  width='2.8%'><input id='vom17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(vom17,tsal17,17)'; onblur='prueba(vom17)'/></td>" 
							+"<td width='2.8%'><input id='vom18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(vom18,tsal18,18)'; onblur='prueba(vom18)'/></td>" 
							+"<td  width='2.8%'><input id='vom19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(vom19,tsal19,19)'; onblur='prueba(vom19)'/></td>" 
							+"<td  width='2.8%'><input id='vomt2' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='vom20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(vom20,tsal20,20)'; onblur='prueba(vom20)'/></td>" 
							+"<td  width='2.8%'><input id='vom21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(vom21,tsal21,21)'; onblur='prueba(vom21)'/></td>" 
							+"<td width='2.8%'><input id='vom22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(vom22,tsal22,22)'; onblur='prueba(vom22)'/></td>" 
							+"<td  width='2.8%'><input id='vom23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(vom23,tsal23,23)'; onblur='prueba(vom23)'/></td>" 
							+"<td  width='2.8%'><input id='vom00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(vom00,tsal00,00)'; onblur='prueba(vom00)'/></td>" 
							+"<td  width='2.8%'><input id='vom01' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(vom01,tsal01,01)'; onblur='prueba(vom01)'/></td>" 
							+"<td  width='2.8%'><input id='vom02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(vom02,tsal02,02)'; onblur='prueba(vom02)'/></td>" 
							+"<td width='2.8%'><input id='vom03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(vom03,tsal03,03)'; onblur='prueba(vom03)'/></td>" 
							+"<td  width='2.8%'><input id='vom04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(vom04,tsal04,04)'; onblur='prueba(vom04)'/></td>" 
							+"<td  width='2.8%'><input id='vom05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(vom05,tsal05,05)'; onblur='prueba(vom05)'/></td>" 
							+"<td  width='2.8%'><input id='vom06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(vom06,tsal06,06)'; onblur='prueba(vom06)'/></td>"													
							+"<td  width='2.8%'><input id='vomt3' type='text' size='1' disabled='disabled'/></td>" 
							+"</tr></table>"+
						    "</td></tr>"+
						    //deposicion
							"<tr> <td width='30%'><div id='deposicion'>DEPOSICI\u00D3N</div>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='dep7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(dep7,tsal7,7)'; onblur='prueba(dep7)'/></td>" 
							+"<td  width='2.8%'><input id='dep8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(dep8,tsal8,8)'; onblur='prueba(dep8)'/></td>" 
							+"<td  width='2.8%'><input id='dep9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(dep9,tsal9,9)'; onblur='prueba(dep9)'/></td>" 
							+"<td width='2.8%'><input id='dep10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(dep10,tsal10,10)'; onblur='prueba(dep10)'/></td>" 
							+"<td  width='2.8%'><input id='dep11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(dep11,tsal11,11)'; onblur='prueba(dep11)'/></td>" 
							+"<td  width='2.8%'><input id='dep12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(dep12,tsal12,12)'; onblur='prueba(dep12)'/></td>"
							+"<td  width='2.8%'><input id='dep13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(dep13,tsal13,13)'; onblur='prueba(dep13)'/></td>" 
							+"<td  width='2.8%'><input id='dept1' type='text' size='1' disabled='disabled'/></td>" 
							+"<td width='2.8%'><input id='dep14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(dep14,tsal14,14)'; onblur='prueba(dep14)'/></td>" 
							+"<td  width='2.8%'><input id='dep15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(dep15,tsal15,15)'; onblur='prueba(dep15)'/></td>" 
							+"<td  width='2.8%'><input id='dep16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(dep16,tsal16,16)'; onblur='prueba(dep16)'/></td>" 
							+"<td  width='2.8%'><input id='dep17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(dep17,tsal17,17)'; onblur='prueba(dep17,)'/></td>" 
							+"<td width='2.8%'><input id='dep18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(dep18,tsal18,18)'; onblur='prueba(dep18)'/></td>" 
							+"<td  width='2.8%'><input id='dep19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(dep19,tsal19,19)'; onblur='prueba(dep19)'/></td>" 
							+"<td  width='2.8%'><input id='dept2' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='dep20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(dep20,tsal20,20)'; onblur='prueba(dep20)'/></td>" 
							+"<td  width='2.8%'><input id='dep21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(dep21,tsal21,21)'; onblur='prueba(dep21)'/></td>" 
							+"<td width='2.8%'><input id='dep22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(dep22,tsal22,22)'; onblur='prueba(dep22)'/></td>" 
							+"<td  width='2.8%'><input id='dep23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(dep23,tsal23,23)'; onblur='prueba(dep23)'/></td>" 
							+"<td  width='2.8%'><input id='dep00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(dep00,tsal00,00)'; onblur='prueba(dep00)'/></td>" 
							+"<td  width='2.8%'><input id='dep01' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(dep01,tsal01,01)'; onblur='prueba(dep01)'/></td>" 
							+"<td  width='2.8%'><input id='dep02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(dep02,tsal02,02)'; onblur='prueba(dep02)'/></td>" 
							+"<td width='2.8%'><input id='dep03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(dep03,tsal03,03)'; onblur='prueba(dep03)'/></td>" 
							+"<td  width='2.8%'><input id='dep04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(dep04,tsal04,04)'; onblur='prueba(dep04)'/></td>" 
							+"<td  width='2.8%'><input id='dep05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(dep05,tsal05,05)'; onblur='prueba(dep05)'/></td>" 
							+"<td  width='2.8%'><input id='dep06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(dep06,tsal06,06)'; onblur='prueba(dep06)'/></td>"													
							+"<td  width='2.8%'><input id='dept3' type='text' size='1' disabled='disabled'/></td>" 
							+"</tr></table>"+
							"</td></tr>"+    
							//drenaje1
							"<tr id='drenaje1' style='display: none'> <td  width='30%'><div id='drena1'>drenaje1</div>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='drenaje1_7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje1_7,tsal7,7)'; onblur='prueba(drenaje1_7)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje1_8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje1_8,tsal8,8)'; onblur='prueba(drenaje1_8)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje1_9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje1_9,tsal9,9)'; onblur='prueba(drenaje1_9)'/></td>" 
							+"<td width='2.8%'><input id='drenaje1_10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje1_10,tsal10,10)'; onblur='prueba(drenaje1_10)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje1_11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje1_11,tsal11,11)'; onblur='prueba(drenaje1_11)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje1_12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje1_12,tsal12,12)'; onblur='prueba(drenaje1_12)'/></td>"
							+"<td  width='2.8%'><input id='drenaje1_13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje1_13,tsal13,13)'; onblur='prueba(drenaje1_13)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje1_t1' type='text' size='1' disabled='disabled'/></td>" 
							+"<td width='2.8%'><input id='drenaje1_14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje1_14,tsal14,14)'; onblur='prueba(drenaje1_14)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje1_15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje1_15,tsal15,15)'; onblur='prueba(drenaje1_15)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje1_16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje1_16,tsal16,16)'; onblur='prueba(drenaje1_16)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje1_17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje1_17,tsal17,17)'; onblur='prueba(drenaje1_17,)'/></td>" 
							+"<td width='2.8%'><input id='drenaje1_18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje1_18,tsal18,18)'; onblur='prueba(drenaje1_18)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje1_19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje1_19,tsal19,19)'; onblur='prueba(drenaje1_19)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje1_t2' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='drenaje1_20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje1_20,tsal20,20)'; onblur='prueba(drenaje1_20)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje1_21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje1_21,tsal21,21)'; onblur='prueba(drenaje1_21)'/></td>" 
							+"<td width='2.8%'><input id='drenaje1_22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje1_22,tsal22,22)'; onblur='prueba(drenaje1_22)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje1_23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje1_23,tsal23,23)'; onblur='prueba(drenaje1_23)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje1_00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje1_00,tsal00,00)'; onblur='prueba(drenaje1_00)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje1_01' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje1_01,tsal01,01)'; onblur='prueba(drenaje1_01)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje1_02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje1_02,tsal02,02)'; onblur='prueba(drenaje1_02)'/></td>" 
							+"<td width='2.8%'><input id='drenaje1_03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje1_03,tsal03,03)'; onblur='prueba(drenaje1_03)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje1_04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje1_04,tsal04,04)'; onblur='prueba(drenaje1_04)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje1_05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje1_05,tsal05,05)'; onblur='prueba(drenaje1_05)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje1_06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje1_06,tsal06,06)'; onblur='prueba(drenaje1_06)'/></td>"													
							+"<td  width='2.8%'><input id='drenaje1_t3' type='text' size='1' disabled='disabled'/></td>" 
							+"</tr></table>"+
							"</td></tr>"+ 
							
							//drenaje2
							"<tr id='drenaje2' style='display: none'> <td  width='30%'><div id='drena2'>drenaje2</div>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='drenaje2_7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje2_7,tsal7,7)'; onblur='prueba(drenaje2_7)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje2_8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje2_8,tsal8,8)'; onblur='prueba(drenaje2_8)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje2_9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje2_9,tsal9,9)'; onblur='prueba(drenaje2_9)'/></td>" 
							+"<td width='2.8%'><input id='drenaje2_10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje2_10,tsal10,10)'; onblur='prueba(drenaje2_10)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje2_11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje2_11,tsal11,11)'; onblur='prueba(drenaje2_11)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje2_12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje2_12,tsal12,12)'; onblur='prueba(drenaje2_12)'/></td>"
							+"<td  width='2.8%'><input id='drenaje2_13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje2_13,tsal13,13)'; onblur='prueba(drenaje2_13)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje2_t1' type='text' size='1' disabled='disabled'/></td>" 
							+"<td width='2.8%'><input id='drenaje2_14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje2_14,tsal14,14)'; onblur='prueba(drenaje2_14)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje2_15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje2_15,tsal15,15)'; onblur='prueba(drenaje2_15)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje2_16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje2_16,tsal16,16)'; onblur='prueba(drenaje2_16)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje2_17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje2_17,tsal17,17)'; onblur='prueba(drenaje2_17,)'/></td>" 
							+"<td width='2.8%'><input id='drenaje2_18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje2_18,tsal18,18)'; onblur='prueba(drenaje2_18)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje2_19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje2_19,tsal19,19)'; onblur='prueba(drenaje2_19)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje2_t2' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='drenaje2_20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje2_20,tsal20,20)'; onblur='prueba(drenaje2_20)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje2_21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje2_21,tsal21,21)'; onblur='prueba(drenaje2_21)'/></td>" 
							+"<td width='2.8%'><input id='drenaje2_22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje2_22,tsal22,22)'; onblur='prueba(drenaje2_22)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje2_23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje2_23,tsal23,23)'; onblur='prueba(drenaje2_23)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje2_00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje2_00,tsal00,00)'; onblur='prueba(drenaje2_00)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje2_01' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje2_01,tsal01,01)'; onblur='prueba(drenaje2_01)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje2_02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje2_02,tsal02,02)'; onblur='prueba(drenaje2_02)'/></td>" 
							+"<td width='2.8%'><input id='drenaje2_03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje2_03,tsal03,03)'; onblur='prueba(drenaje2_03)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje2_04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje2_04,tsal04,04)'; onblur='prueba(drenaje2_04)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje2_05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje2_05,tsal05,05)'; onblur='prueba(drenaje2_05)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje2_06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje2_06,tsal06,06)'; onblur='prueba(drenaje2_06)'/></td>"													
							+"<td  width='2.8%'><input id='drenaje2_t3' type='text' size='1' disabled='disabled'/></td>" 
							+"</tr></table>"+
							"</td></tr>"+ 
							
							//drenaje3
							"<tr id='drenaje3' style='display: none'> <td  width='30%'><div id='drena3'>drenaje3</div>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='drenaje3_7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje3_7,tsal7,7)'; onblur='prueba(drenaje3_7)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje3_8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje3_8,tsal8,8)'; onblur='prueba(drenaje3_8)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje3_9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje3_9,tsal9,9)'; onblur='prueba(drenaje3_9)'/></td>" 
							+"<td width='2.8%'><input id='drenaje3_10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje3_10,tsal10,10)'; onblur='prueba(drenaje3_10)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje3_11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje3_11,tsal11,11)'; onblur='prueba(drenaje3_11)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje3_12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje3_12,tsal12,12)'; onblur='prueba(drenaje3_12)'/></td>"
							+"<td  width='2.8%'><input id='drenaje3_13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje3_13,tsal13,13)'; onblur='prueba(drenaje3_13)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje3_t1' type='text' size='1' disabled='disabled'/></td>" 
							+"<td width='2.8%'><input id='drenaje3_14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje3_14,tsal14,14)'; onblur='prueba(drenaje3_14)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje3_15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje3_15,tsal15,15)'; onblur='prueba(drenaje3_15)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje3_16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje3_16,tsal16,16)'; onblur='prueba(drenaje3_16)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje3_17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje3_17,tsal17,17)'; onblur='prueba(drenaje3_17,)'/></td>" 
							+"<td width='2.8%'><input id='drenaje3_18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje3_18,tsal18,18)'; onblur='prueba(drenaje3_18)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje3_19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje3_19,tsal19,19)'; onblur='prueba(drenaje3_19)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje3_t2' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='drenaje3_20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje3_20,tsal20,20)'; onblur='prueba(drenaje3_20)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje3_21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje3_21,tsal21,21)'; onblur='prueba(drenaje3_21)'/></td>" 
							+"<td width='2.8%'><input id='drenaje3_22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje3_22,tsal22,22)'; onblur='prueba(drenaje3_22)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje3_23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje3_23,tsal23,23)'; onblur='prueba(drenaje3_23)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje3_00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje3_00,tsal00,00)'; onblur='prueba(drenaje3_00)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje3_01' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje3_01,tsal01,01)'; onblur='prueba(drenaje3_01)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje3_02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje3_02,tsal02,02)'; onblur='prueba(drenaje3_02)'/></td>" 
							+"<td width='2.8%'><input id='drenaje3_03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje3_03,tsal03,03)'; onblur='prueba(drenaje3_03)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje3_04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje3_04,tsal04,04)'; onblur='prueba(drenaje3_04)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje3_05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje3_05,tsal05,05)'; onblur='prueba(drenaje3_05)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje3_06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje3_06,tsal06,06)'; onblur='prueba(drenaje3_06)'/></td>"													
							+"<td  width='2.8%'><input id='drenaje3_t3' type='text' size='1' disabled='disabled'/></td>" 
							+"</tr></table>"+
							"</td></tr>"+ 
							
							//drenaje4
							"<tr id='drenaje4' style='display: none'> <td width='30%'><div id='drena4'>drenaje4</div>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='drenaje4_7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje4_7,tsal7,7)'; onblur='prueba(drenaje4_7)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje4_8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje4_8,tsal8,8)'; onblur='prueba(drenaje4_8)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje4_9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje4_9,tsal9,9)'; onblur='prueba(drenaje4_9)'/></td>" 
							+"<td width='2.8%'><input id='drenaje4_10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje4_10,tsal10,10)'; onblur='prueba(drenaje4_10)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje4_11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje4_11,tsal11,11)'; onblur='prueba(drenaje4_11)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje4_12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje4_12,tsal12,12)'; onblur='prueba(drenaje4_12)'/></td>"
							+"<td  width='2.8%'><input id='drenaje4_13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje4_13,tsal13,13)'; onblur='prueba(drenaje4_13)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje4_t1' type='text' size='1' disabled='disabled'/></td>" 
							+"<td width='2.8%'><input id='drenaje4_14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje4_14,tsal14,14)'; onblur='prueba(drenaje4_14)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje4_15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje4_15,tsal15,15)'; onblur='prueba(drenaje4_15)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje4_16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje4_16,tsal16,16)'; onblur='prueba(drenaje4_16)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje4_17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje4_17,tsal17,17)'; onblur='prueba(drenaje4_17,)'/></td>" 
							+"<td width='2.8%'><input id='drenaje4_18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje4_18,tsal18,18)'; onblur='prueba(drenaje4_18)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje4_19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje4_19,tsal19,19)'; onblur='prueba(drenaje4_19)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje4_t2' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='drenaje4_20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje4_20,tsal20,20)'; onblur='prueba(drenaje4_20)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje4_21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje4_21,tsal21,21)'; onblur='prueba(drenaje4_21)'/></td>" 
							+"<td width='2.8%'><input id='drenaje4_22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje4_22,tsal22,22)'; onblur='prueba(drenaje4_22)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje4_23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje4_23,tsal23,23)'; onblur='prueba(drenaje4_23)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje4_00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje4_00,tsal00,00)'; onblur='prueba(drenaje4_00)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje4_01' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje4_01,tsal01,01)'; onblur='prueba(drenaje4_01)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje4_02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje4_02,tsal02,02)'; onblur='prueba(drenaje4_02)'/></td>" 
							+"<td width='2.8%'><input id='drenaje4_03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje4_03,tsal03,03)'; onblur='prueba(drenaje4_03)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje4_04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje4_04,tsal04,04)'; onblur='prueba(drenaje4_04)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje4_05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje4_05,tsal05,05)'; onblur='prueba(drenaje4_05)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje4_06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje4_06,tsal06,06)'; onblur='prueba(drenaje4_06)'/></td>"													
							+"<td  width='2.8%'><input id='drenaje4_t3' type='text' size='1' disabled='disabled'/></td>" 
							+"</tr></table>"+
							"</td></tr>"+ 
							
							//drenaje5
							"<tr id='drenaje5' style='display: none'> <td  width='30%'><div id='drena5'>drenaje5</div>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='drenaje5_7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje5_7,tsal7,7)'; onblur='prueba(drenaje5_7)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje5_8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje5_8,tsal8,8)'; onblur='prueba(drenaje5_8)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje5_9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje5_9,tsal9,9)'; onblur='prueba(drenaje5_9)'/></td>" 
							+"<td width='2.8%'><input id='drenaje5_10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje5_10,tsal10,10)'; onblur='prueba(drenaje5_10)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje5_11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje5_11,tsal11,11)'; onblur='prueba(drenaje5_11)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje5_12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje5_12,tsal12,12)'; onblur='prueba(drenaje5_12)'/></td>"
							+"<td  width='2.8%'><input id='drenaje5_13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje5_13,tsal13,13)'; onblur='prueba(drenaje5_13)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje5_t1' type='text' size='1' disabled='disabled'/></td>" 
							+"<td width='2.8%'><input id='drenaje5_14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje5_14,tsal14,14)'; onblur='prueba(drenaje5_14)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje5_15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje5_15,tsal15,15)'; onblur='prueba(drenaje5_15)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje5_16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje5_16,tsal16,16)'; onblur='prueba(drenaje5_16)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje5_17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje5_17,tsal17,17)'; onblur='prueba(drenaje5_17,)'/></td>" 
							+"<td width='2.8%'><input id='drenaje5_18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje5_18,tsal18,18)'; onblur='prueba(drenaje5_18)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje5_19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje5_19,tsal19,19)'; onblur='prueba(drenaje5_19)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje5_t2' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='drenaje5_20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje5_20,tsal20,20)'; onblur='prueba(drenaje5_20)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje5_21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje5_21,tsal21,21)'; onblur='prueba(drenaje5_21)'/></td>" 
							+"<td width='2.8%'><input id='drenaje5_22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje5_22,tsal22,22)'; onblur='prueba(drenaje5_22)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje5_23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje5_23,tsal23,23)'; onblur='prueba(drenaje5_23)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje5_00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje5_00,tsal00,00)'; onblur='prueba(drenaje5_00)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje5_01' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje5_01,tsal01,01)'; onblur='prueba(drenaje5_01)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje5_02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje5_02,tsal02,02)'; onblur='prueba(drenaje5_02)'/></td>" 
							+"<td width='2.8%'><input id='drenaje5_03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje5_03,tsal03,03)'; onblur='prueba(drenaje5_03)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje5_04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje5_04,tsal04,04)'; onblur='prueba(drenaje5_04)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje5_05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje5_05,tsal05,05)'; onblur='prueba(drenaje5_05)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje5_06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje5_06,tsal06,06)'; onblur='prueba(drenaje5_06)'/></td>"													
							+"<td  width='2.8%'><input id='drenaje5_t3' type='text' size='1' disabled='disabled'/></td>" 
							+"</tr></table>"+
							"</td></tr>"+ 
							
							//drenaje6
							"<tr id='drenaje6' style='display: none'> <td  width='30%'><div id='drena6'>drenaje6</div>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='drenaje6_7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje6_7,tsal7,7)'; onblur='prueba(drenaje6_7)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje6_8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje6_8,tsal8,8)'; onblur='prueba(drenaje6_8)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje6_9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje6_9,tsal9,9)'; onblur='prueba(drenaje6_9)'/></td>" 
							+"<td width='2.8%'><input id='drenaje6_10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje6_10,tsal10,10)'; onblur='prueba(drenaje6_10)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje6_11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje6_11,tsal11,11)'; onblur='prueba(drenaje6_11)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje6_12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje6_12,tsal12,12)'; onblur='prueba(drenaje6_12)'/></td>"
							+"<td  width='2.8%'><input id='drenaje6_13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje6_13,tsal13,13)'; onblur='prueba(drenaje6_13)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje6_t1' type='text' size='1' disabled='disabled'/></td>" 
							+"<td width='2.8%'><input id='drenaje6_14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje6_14,tsal14,14)'; onblur='prueba(drenaje6_14)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje6_15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje6_15,tsal15,15)'; onblur='prueba(drenaje6_15)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje6_16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje6_16,tsal16,16)'; onblur='prueba(drenaje6_16)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje6_17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje6_17,tsal17,17)'; onblur='prueba(drenaje6_17,)'/></td>" 
							+"<td width='2.8%'><input id='drenaje6_18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje6_18,tsal18,18)'; o'nblur='prueba(drenaje6_18)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje6_19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje6_19,tsal19,19)'; onblur='prueba(drenaje6_19)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje6_t2' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='drenaje6_20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje6_20,tsal20,20)'; onblur='prueba(drenaje6_20)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje6_21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje6_21,tsal21,21)'; onblur='prueba(drenaje6_21)'/></td>" 
							+"<td width='2.8%'><input id='drenaje6_22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje6_22,tsal22,22)'; onblur='prueba(drenaje6_22)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje6_23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje6_23,tsal23,23)'; onblur='prueba(drenaje6_23)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje6_00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje6_00,tsal00,00)'; onblur='prueba(drenaje6_00)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje6_01' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje6_01,tsal01,01)'; onblur='prueba(drenaje6_01)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje6_02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje6_02,tsal02,02)'; onblur='prueba(drenaje6_02)'/></td>" 
							+"<td width='2.8%'><input id='drenaje6_03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje6_03,tsal03,03)'; onblur='prueba(drenaje6_03)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje6_04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje6_04,tsal04,04)'; onblur='prueba(drenaje6_04)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje6_05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje6_05,tsal05,05)'; onblur='prueba(drenaje6_05)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje6_06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje6_06,tsal06,06)'; onblur='prueba(drenaje6_06)'/></td>"													
							+"<td  width='2.8%'><input id='drenaje6_t3' type='text' size='1' disabled='disabled'/></td>" 
							+"</tr></table>"+
							"</td></tr>"+ 
							
							//drenaje7
							"<tr id='drenaje7' style='display: none'> <td width='30%'><div id='drena7'>drenaje7</div>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='drenaje7_7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje7_7,tsal7,7)'; onblur='prueba(drenaje7_7)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje7_8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje7_8,tsal8,8)'; onblur='prueba(drenaje7_8)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje7_9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje7_9,tsal9,9)'; onblur='prueba(drenaje7_9)'/></td>" 
							+"<td width='2.8%'><input id='drenaje7_10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje7_10,tsal10,10)'; onblur='prueba(drenaje7_10)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje7_11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje7_11,tsal11,11)'; onblur='prueba(drenaje7_11)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje7_12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje7_12,tsal12,12)'; onblur='prueba(drenaje7_12)'/></td>"
							+"<td  width='2.8%'><input id='drenaje7_13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje7_13,tsal13,13)'; onblur='prueba(drenaje7_13)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje7_t1' type='text' size='1' disabled='disabled'/></td>" 
							+"<td width='2.8%'><input id='drenaje7_14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje7_14,tsal14,14)'; onblur='prueba(drenaje7_14)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje7_15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje7_15,tsal15,15)'; onblur='prueba(drenaje7_15)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje7_16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje7_16,tsal16,16)'; onblur='prueba(drenaje7_16)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje7_17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje7_17,tsal17,17)'; onblur='prueba(drenaje7_17,)'/></td>" 
							+"<td width='2.8%'><input id='drenaje7_18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje7_18,tsal18,18)'; onblur='prueba(drenaje7_18)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje7_19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje7_19,tsal19,19)'; onblur='prueba(drenaje7_19)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje7_t2' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='drenaje7_20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje7_20,tsal20,20)'; onblur='prueba(drenaje7_20)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje7_21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje7_21,tsal21,21)'; onblur='prueba(drenaje7_21)'/></td>" 
							+"<td width='2.8%'><input id='drenaje7_22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje7_22,tsal22,22)'; onblur='prueba(drenaje7_22)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje7_23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje7_23,tsal23,23)'; onblur='prueba(drenaje7_23)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje7_00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje7_00,tsal00,00)'; onblur='prueba(drenaje7_00)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje7_01' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje7_01,tsal01,01)'; onblur='prueba(drenaje7_01)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje7_02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje7_02,tsal02,02)'; onblur='prueba(drenaje7_02)'/></td>" 
							+"<td width='2.8%'><input id='drenaje7_03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje7_03,tsal03,03)'; onblur='prueba(drenaje7_03)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje7_04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje7_04,tsal04,04)'; onblur='prueba(drenaje7_04)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje7_05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje7_05,tsal05,05)'; onblur='prueba(drenaje7_05)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje7_06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje7_06,tsal06,06)'; onblur='prueba(drenaje7_06)'/></td>"													
							+"<td  width='2.8%'><input id='drenaje7_t3' type='text' size='1' disabled='disabled'/></td>" 
							+"</tr></table>"+
							"</td></tr>"+ 
							
							//drenaje8
							"<tr id='drenaje8' style='display: none'> <td width='30%'><div id='drena8'>drenaje8</div>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='drenaje8_7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje8_7,tsal7,7)'; onblur='prueba(drenaje8_7)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje8_8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje8_8,tsal8,8)'; onblur='prueba(drenaje8_8)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje8_9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje8_9,tsal9,9)'; onblur='prueba(drenaje8_9)'/></td>" 
							+"<td width='2.8%'><input id='drenaje8_10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje8_10,tsal10,10)'; onblur='prueba(drenaje8_10)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje8_11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje8_11,tsal11,11)'; onblur='prueba(drenaje8_11)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje8_12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje8_12,tsal12,12)'; onblur='prueba(drenaje8_12)'/></td>"
							+"<td  width='2.8%'><input id='drenaje8_13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje8_13,tsal13,13)'; onblur='prueba(drenaje8_13)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje8_t1' type='text' size='1' disabled='disabled'/></td>" 
							+"<td width='2.8%'><input id='drenaje8_14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje8_14,tsal14,14)'; onblur='prueba(drenaje8_14)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje8_15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje8_15,tsal15,15)'; onblur='prueba(drenaje8_15)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje8_16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje8_16,tsal16,16)'; onblur='prueba(drenaje8_16)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje8_17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje8_17,tsal17,17)'; onblur='prueba(drenaje8_17,)'/></td>" 
							+"<td width='2.8%'><input id='drenaje8_18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje8_18,tsal18,18)'; onblur='prueba(drenaje8_18)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje8_19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje8_19,tsal19,19)'; onblur='prueba(drenaje8_19)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje8_t2' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='drenaje8_20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje8_20,tsal20,20)'; onblur='prueba(drenaje8_20)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje8_21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje8_21,tsal21,21)'; onblur='prueba(drenaje8_21)'/></td>" 
							+"<td width='2.8%'><input id='drenaje8_22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje8_22,tsal22,22)'; onblur='prueba(drenaje8_22)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje8_23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje8_23,tsal23,23)'; onblur='prueba(drenaje8_23)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje8_00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje8_00,tsal00,00)'; onblur='prueba(drenaje8_00)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje8_01' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje8_01,tsal01,01)'; onblur='prueba(drenaje8_01)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje8_02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje8_02,tsal02,02)'; onblur='prueba(drenaje8_02)'/></td>" 
							+"<td width='2.8%'><input id='drenaje8_03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje8_03,tsal03,03)'; onblur='prueba(drenaje8_03)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje8_04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje8_04,tsal04,04)'; onblur='prueba(drenaje8_04)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje8_05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje8_05,tsal05,05)'; onblur='prueba(drenaje8_05)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje8_06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje8_06,tsal06,06)'; onblur='prueba(drenaje8_06)'/></td>"													
							+"<td  width='2.8%'><input id='drenaje8_t3' type='text' size='1' disabled='disabled'/></td>" 
							+"</tr></table>"+
							"</td></tr>"+ 
							
							//drenaje9
							"<tr id='drenaje9' style='display: none'> <td width='30%'><div id='drena9'>drenaje9</div>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='drenaje9_7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje9_7,tsal7,7)'; onblur='prueba(drenaje9_7)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje9_8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje9_8,tsal8,8)'; onblur='prueba(drenaje9_8)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje9_9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje9_9,tsal9,9)'; onblur='prueba(drenaje9_9)'/></td>" 
							+"<td width='2.8%'><input id='drenaje9_10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje9_10,tsal10,10)'; onblur='prueba(drenaje9_10)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje9_11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje9_11,tsal11,11)'; onblur='prueba(drenaje9_11)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje9_12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje9_12,tsal12,12)'; onblur='prueba(drenaje9_12)'/></td>"
							+"<td  width='2.8%'><input id='drenaje9_13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje9_13,tsal13,13)'; onblur='prueba(drenaje9_13)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje9_t1' type='text' size='1' disabled='disabled'/></td>" 
							+"<td width='2.8%'><input id='drenaje9_14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje9_14,tsal14,14)'; onblur='prueba(drenaje9_14)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje9_15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje9_15,tsal15,15)'; onblur='prueba(drenaje9_15)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje9_16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje9_16,tsal16,16)'; onblur='prueba(drenaje9_16)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje9_17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje9_17,tsal17,17)'; onblur='prueba(drenaje9_17,)'/></td>" 
							+"<td width='2.8%'><input id='drenaje9_18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje9_18,tsal18,18)'; onblur='prueba(drenaje9_18)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje9_19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje9_19,tsal19,19)'; onblur='prueba(drenaje9_19)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje9_t2' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='drenaje9_20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje9_20,tsal20,20)'; onblur='prueba(drenaje9_20)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje9_21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje9_21,tsal21,21)'; onblur='prueba(drenaje9_21)'/></td>" 
							+"<td width='2.8%'><input id='drenaje9_22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje9_22,tsal22,22)'; onblur='prueba(drenaje9_22)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje9_23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje9_23,tsal23,23)'; onblur='prueba(drenaje9_23)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje9_00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje9_00,tsal00,00)'; onblur='prueba(drenaje9_00)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje9_01' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje9_01,tsal01,01)'; onblur='prueba(drenaje9_01)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje9_02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje9_02,tsal02,02)'; onblur='prueba(drenaje9_02)'/></td>" 
							+"<td width='2.8%'><input id='drenaje9_03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje9_03,tsal03,03)'; onblur='prueba(drenaje9_03)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje9_04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje9_04,tsal04,04)'; onblur='prueba(drenaje9_04)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje9_05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje9_05,tsal05,05)'; onblur='prueba(drenaje9_05)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje9_06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje9_06,tsal06,06)'; onblur='prueba(drenaje9_06)'/></td>"													
							+"<td  width='2.8%'><input id='drenaje9_t3' type='text' size='1' disabled='disabled'/></td>" 
							+"</tr></table>"+
							"</td></tr>"+ 
							
							//drenaje10
							"<tr id='drenaje10' style='display: none'> <td width='30%'><div id='drena10'>drenaje10</div>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='drenaje10_7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje10_7,tsal7,7)'; onblur='prueba(drenaje10_7)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje10_8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje10_8,tsal8,8)'; onblur='prueba(drenaje10_8)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje10_9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje10_9,tsal9,9)'; onblur='prueba(drenaje10_9)'/></td>" 
							+"<td width='2.8%'><input id='drenaje10_10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje10_10,tsal10,10)'; onblur='prueba(drenaje10_10)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje10_11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje10_11,tsal11,11)'; onblur='prueba(drenaje10_11)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje10_12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje10_12,tsal12,12)'; onblur='prueba(drenaje10_12)'/></td>"
							+"<td  width='2.8%'><input id='drenaje10_13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje10_13,tsal13,13)'; onblur='prueba(drenaje10_13)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje10_t1' type='text' size='1' disabled='disabled'/></td>" 
							+"<td width='2.8%'><input id='drenaje10_14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje10_14,tsal14,14)'; onblur='prueba(drenaje10_14)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje10_15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje10_15,tsal15,15)'; onblur='prueba(drenaje10_15)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje10_16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje10_16,tsal16,16)'; onblur='prueba(drenaje10_16)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje10_17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje10_17,tsal17,17)'; onblur='prueba(drenaje10_17,)'/></td>" 
							+"<td width='2.8%'><input id='drenaje10_18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje10_18,tsal18,18)'; onblur='prueba(drenaje10_18)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje10_19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje10_19,tsal19,19)'; onblur='prueba(drenaje10_19)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje10_t2' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='drenaje10_20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje10_20,tsal20,20)'; onblur='prueba(drenaje10_20)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje10_21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje10_21,tsal21,21)'; onblur='prueba(drenaje10_21)'/></td>" 
							+"<td width='2.8%'><input id='drenaje10_22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje10_22,tsal22,22)'; onblur='prueba(drenaje10_22)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje10_23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje10_23,tsal23,23)'; onblur='prueba(drenaje10_23)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje10_00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje10_00,tsal00,00)'; onblur='prueba(drenaje10_00)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje10_01' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje10_01,tsal01,01)'; onblur='prueba(drenaje10_01)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje10_02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje10_02,tsal02,02)'; onblur='prueba(drenaje10_02)'/></td>" 
							+"<td width='2.8%'><input id='drenaje10_03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje10_03,tsal03,03)'; onblur='prueba(drenaje10_03)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje10_04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje10_04,tsal04,04)'; onblur='prueba(drenaje10_04)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje10_05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje10_05,tsal05,05)'; onblur='prueba(drenaje10_05)'/></td>" 
							+"<td  width='2.8%'><input id='drenaje10_06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(drenaje10_06,tsal06,06)'; onblur='prueba(drenaje10_06)'/></td>"													
							+"<td  width='2.8%'><input id='drenaje10_t3' type='text' size='1' disabled='disabled'/></td>" 
							+"</tr></table>"+
							"</td></tr>"+ 
							
							
							//total salidas 
							"<tr bgcolor='#FA5858'> <td width='30%'><div id='tsal'>TOTAL SALIDAS</div></td>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='tsal7' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tsal8' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tsal9' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tsal10' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tsal11' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tsal12' type='text' size='1' disabled='disabled'/></td>"
							+"<td  width='2.8%'><input id='tsal13' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tsalt1' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tsal14' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tsal15' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tsal16' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tsal17' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tsal18' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tsal19' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tsalt2' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tsal20' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tsal21' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tsal22' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tsal23' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tsal00' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tsal01' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tsal02' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tsal03' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tsal04' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tsal05' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tsal06' type='text' size='1' disabled='disabled'/></td>"													
							+"<td  width='2.8%'><input id='tsalt3' type='text' size='1' disabled='disabled'/></td>" 
							+"</tr></table></td></tr>" +
							
							
					        //fin liquidos salidas precargado todos los dias 
							
							
							// liquidos entradas precargado todos los dias 
							"<tr  bgcolor='#2E9AFE'> <td width='100%'><div id='entradas'>ENTRADAS</div></td>" +
							"<td>" +
							"<input id='nom_sonda' type='text' style='border: 1px solid #585858'/>"+
							"<input type='button' id='aparecer_sondas' value='Agregar Sonda' onclick='actualizar_ult_sonda()' /></td>"+
							"</tr>"+	
							//sonda
							"<tr > <td width='30%'><div id='sonda'>SONDA</div></td>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='sonent7' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='sonent8' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='sonent9' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='sonent10' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='sonent11' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='sonent12' type='text' size='1' disabled='disabled'/></td>"
							+"<td  width='2.8%'><input id='sonent13' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='sonentt1' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='sonent14' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='sonent15' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='sonent16' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='sonent17' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='sonent18' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='sonent19' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='sonentt2' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='sonent20' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='sonent21' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='sonent22' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='sonent23' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='sonent00' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='sonent01' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='sonent02' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='sonent03' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='sonent04' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='sonent05' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='sonent06' type='text' size='1' disabled='disabled'/></td>"													
							+"<td  width='2.8%'><input id='sonentt3' type='text' size='1' disabled='disabled'/></td>" 
							+"</tr></table></td></tr>" +	
							
							
							//sonda1
							"<tr id='sonda1' style='display: none'> <td width='30%'><div id='sond1'>sonda1</div>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='sonda1_7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda1_7,tent7,7)'; onblur='prueba(sonda1_7)'/></td>" 
							+"<td  width='2.8%'><input id='sonda1_8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda1_8,tent8,8)'; onblur='prueba(sonda1_8)'/></td>" 
							+"<td  width='2.8%'><input id='sonda1_9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda1_9,tent9,9)'; onblur='prueba(sonda1_9)'/></td>" 
							+"<td width='2.8%'><input id='sonda1_10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda1_10,tent10,10)'; onblur='prueba(sonda1_10)'/></td>" 
							+"<td  width='2.8%'><input id='sonda1_11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda1_11,tent11,11)'; onblur='prueba(sonda1_11)'/></td>" 
							+"<td  width='2.8%'><input id='sonda1_12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda1_12,tent12,12)'; onblur='prueba(sonda1_12)'/></td>"
							+"<td  width='2.8%'><input id='sonda1_13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda1_13,tent13,13)'; onblur='prueba(sonda1_13)'/></td>" 
							+"<td  width='2.8%'><input id='sonda1_t1' type='text' size='1' disabled='disabled'/></td>" 
							+"<td width='2.8%'><input id='sonda1_14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda1_14,tent14,14)'; onblur='prueba(sonda1_14)'/></td>" 
							+"<td  width='2.8%'><input id='sonda1_15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda1_15,tent15,15)'; onblur='prueba(sonda1_15)'/></td>" 
							+"<td  width='2.8%'><input id='sonda1_16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda1_16,tent16,16)'; onblur='prueba(sonda1_16)'/></td>" 
							+"<td  width='2.8%'><input id='sonda1_17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda1_17,tent17,17)'; onblur='prueba(sonda1_17,)'/></td>" 
							+"<td width='2.8%'><input id='sonda1_18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda1_18,tent18,18)'; onblur='prueba(sonda1_18)'/></td>" 
							+"<td  width='2.8%'><input id='sonda1_19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda1_19,tent19,19)'; onblur='prueba(sonda1_19)'/></td>" 
							+"<td  width='2.8%'><input id='sonda1_t2' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='sonda1_20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda1_20,tent20,20)'; onblur='prueba(sonda1_20)'/></td>" 
							+"<td  width='2.8%'><input id='sonda1_21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda1_21,tent21,21)'; onblur='prueba(sonda1_21)'/></td>" 
							+"<td width='2.8%'><input id='sonda1_22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda1_22,tent22,22)'; onblur='prueba(sonda1_22)'/></td>" 
							+"<td  width='2.8%'><input id='sonda1_23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda1_23,tent23,23)'; onblur='prueba(sonda1_23)'/></td>" 
							+"<td  width='2.8%'><input id='sonda1_00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda1_00,tent00,00)'; onblur='prueba(sonda1_00)'/></td>" 
							+"<td  width='2.8%'><input id='sonda1_01' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda1_01,tent01,01)'; onblur='prueba(sonda1_01)'/></td>" 
							+"<td  width='2.8%'><input id='sonda1_02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda1_02,tent02,02)'; onblur='prueba(sonda1_02)'/></td>" 
							+"<td width='2.8%'><input id='sonda1_03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda1_03,tent03,03)'; onblur='prueba(sonda1_03)'/></td>" 
							+"<td  width='2.8%'><input id='sonda1_04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda1_04,tent04,04)'; onblur='prueba(sonda1_04)'/></td>" 
							+"<td  width='2.8%'><input id='sonda1_05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda1_05,tent05,05)'; onblur='prueba(sonda1_05)'/></td>" 
							+"<td  width='2.8%'><input id='sonda1_06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda1_06,tent06,06)'; onblur='prueba(sonda1_06)'/></td>"													
							+"<td  width='2.8%'><input id='sonda1_t3' type='text' size='1' disabled='disabled'/></td>" 
							+"</tr></table>"+
							"</td></tr>"+ 
							
							//sonda2
							"<tr id='sonda2' style='display: none'> <td width='30%'><div id='sond2'>sonda2</div>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='sonda2_7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda2_7,tent7,7)'; onblur='prueba(sonda2_7)'/></td>" 
							+"<td  width='2.8%'><input id='sonda2_8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda2_8,tent8,8)'; onblur='prueba(sonda2_8)'/></td>" 
							+"<td  width='2.8%'><input id='sonda2_9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda2_9,tent9,9)'; onblur='prueba(sonda2_9)'/></td>" 
							+"<td width='2.8%'><input id='sonda2_10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda2_10,tent10,10)'; onblur='prueba(sonda2_10)'/></td>" 
							+"<td  width='2.8%'><input id='sonda2_11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda2_11,tent11,11)'; onblur='prueba(sonda2_11)'/></td>" 
							+"<td  width='2.8%'><input id='sonda2_12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda2_12,tent12,12)'; onblur='prueba(sonda2_12)'/></td>"
							+"<td  width='2.8%'><input id='sonda2_13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda2_13,tent13,13)'; onblur='prueba(sonda2_13)'/></td>" 
							+"<td  width='2.8%'><input id='sonda2_t1' type='text' size='1' disabled='disabled'/></td>" 
							+"<td width='2.8%'><input id='sonda2_14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda2_14,tent14,14)'; onblur='prueba(sonda2_14)'/></td>" 
							+"<td  width='2.8%'><input id='sonda2_15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda2_15,tent15,15)'; onblur='prueba(sonda2_15)'/></td>" 
							+"<td  width='2.8%'><input id='sonda2_16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda2_16,tent16,16)'; onblur='prueba(sonda2_16)'/></td>" 
							+"<td  width='2.8%'><input id='sonda2_17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda2_17,tent17,17)'; onblur='prueba(sonda2_17,)'/></td>" 
							+"<td width='2.8%'><input id='sonda2_18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda2_18,tent18,18)'; onblur='prueba(sonda2_18)'/></td>" 
							+"<td  width='2.8%'><input id='sonda2_19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda2_19,tent19,19)'; onblur='prueba(sonda2_19)'/></td>" 
							+"<td  width='2.8%'><input id='sonda2_t2' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='sonda2_20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda2_20,tent20,20)'; onblur='prueba(sonda2_20)'/></td>" 
							+"<td  width='2.8%'><input id='sonda2_21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda2_21,tent21,21)'; onblur='prueba(sonda2_21)'/></td>" 
							+"<td width='2.8%'><input id='sonda2_22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda2_22,tent22,22)'; onblur='prueba(sonda2_22)'/></td>" 
							+"<td  width='2.8%'><input id='sonda2_23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda2_23,tent23,23)'; onblur='prueba(sonda2_23)'/></td>" 
							+"<td  width='2.8%'><input id='sonda2_00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda2_00,tent00,00)'; onblur='prueba(sonda2_00)'/></td>" 
							+"<td  width='2.8%'><input id='sonda2_01' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda2_01,tent01,01)'; onblur='prueba(sonda2_01)'/></td>" 
							+"<td  width='2.8%'><input id='sonda2_02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda2_02,tent02,02)'; onblur='prueba(sonda2_02)'/></td>" 
							+"<td width='2.8%'><input id='sonda2_03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda2_03,tent03,03)'; onblur='prueba(sonda2_03)'/></td>" 
							+"<td  width='2.8%'><input id='sonda2_04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda2_04,tent04,04)'; onblur='prueba(sonda2_04)'/></td>" 
							+"<td  width='2.8%'><input id='sonda2_05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda2_05,tent05,05)'; onblur='prueba(sonda2_05)'/></td>" 
							+"<td  width='2.8%'><input id='sonda2_06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda2_06,tent06,06)'; onblur='prueba(sonda2_06)'/></td>"													
							+"<td  width='2.8%'><input id='sonda2_t3' type='text' size='1' disabled='disabled'/></td>" 
							+"</tr></table>"+
							"</td></tr>"+ 
							
							//sonda3
							"<tr id='sonda3' style='display: none'> <td width='30%'><div id='sond3'>sonda3</div>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='sonda3_7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda3_7,tent7,7)'; onblur='prueba(sonda3_7)'/></td>" 
							+"<td  width='2.8%'><input id='sonda3_8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda3_8,tent8,8)'; onblur='prueba(sonda3_8)'/></td>" 
							+"<td  width='2.8%'><input id='sonda3_9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda3_9,tent9,9)'; onblur='prueba(sonda3_9)'/></td>" 
							+"<td width='2.8%'><input id='sonda3_10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda3_10,tent10,10)'; onblur='prueba(sonda3_10)'/></td>" 
							+"<td  width='2.8%'><input id='sonda3_11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda3_11,tent11,11)'; onblur='prueba(sonda3_11)'/></td>" 
							+"<td  width='2.8%'><input id='sonda3_12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda3_12,tent12,12)'; onblur='prueba(sonda3_12)'/></td>"
							+"<td  width='2.8%'><input id='sonda3_13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda3_13,tent13,13)'; onblur='prueba(sonda3_13)'/></td>" 
							+"<td  width='2.8%'><input id='sonda3_t1' type='text' size='1' disabled='disabled'/></td>" 
							+"<td width='2.8%'><input id='sonda3_14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda3_14,tent14,14)'; onblur='prueba(sonda3_14)'/></td>" 
							+"<td  width='2.8%'><input id='sonda3_15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda3_15,tent15,15)'; onblur='prueba(sonda3_15)'/></td>" 
							+"<td  width='2.8%'><input id='sonda3_16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda3_16,tent16,16)'; onblur='prueba(sonda3_16)'/></td>" 
							+"<td  width='2.8%'><input id='sonda3_17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda3_17,tent17,17)'; onblur='prueba(sonda3_17,)'/></td>" 
							+"<td width='2.8%'><input id='sonda3_18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda3_18,tent18,18)'; onblur='prueba(sonda3_18)'/></td>" 
							+"<td  width='2.8%'><input id='sonda3_19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda3_19,tent19,19)'; onblur='prueba(sonda3_19)'/></td>" 
							+"<td  width='2.8%'><input id='sonda3_t2' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='sonda3_20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda3_20,tent20,20)'; onblur='prueba(sonda3_20)'/></td>" 
							+"<td  width='2.8%'><input id='sonda3_21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda3_21,tent21,21)'; onblur='prueba(sonda3_21)'/></td>" 
							+"<td width='2.8%'><input id='sonda3_22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda3_22,tent22,22)'; onblur='prueba(sonda3_22)'/></td>" 
							+"<td  width='2.8%'><input id='sonda3_23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda3_23,tent23,23)'; onblur='prueba(sonda3_23)'/></td>" 
							+"<td  width='2.8%'><input id='sonda3_00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda3_00,tent00,00)'; onblur='prueba(sonda3_00)'/></td>" 
							+"<td  width='2.8%'><input id='sonda3_01' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda3_01,tent01,01)'; onblur='prueba(sonda3_01)'/></td>" 
							+"<td  width='2.8%'><input id='sonda3_02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda3_02,tent02,02)'; onblur='prueba(sonda3_02)'/></td>" 
							+"<td width='2.8%'><input id='sonda3_03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda3_03,tent03,03)'; onblur='prueba(sonda3_03)'/></td>" 
							+"<td  width='2.8%'><input id='sonda3_04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda3_04,tent04,04)'; onblur='prueba(sonda3_04)'/></td>" 
							+"<td  width='2.8%'><input id='sonda3_05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda3_05,tent05,05)'; onblur='prueba(sonda3_05)'/></td>" 
							+"<td  width='2.8%'><input id='sonda3_06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda3_06,tent06,06)'; onblur='prueba(sonda3_06)'/></td>"													
							+"<td  width='2.8%'><input id='sonda3_t3' type='text' size='1' disabled='disabled'/></td>" 
							+"</tr></table>"+
							"</td></tr>"+ 
							
							//sonda4
							"<tr id='sonda4' style='display: none'> <td width='30%'><div id='sond4'>sonda4</div>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='sonda4_7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda4_7,tent7,7)'; onblur='prueba(sonda4_7)'/></td>" 
							+"<td  width='2.8%'><input id='sonda4_8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda4_8,tent8,8)'; onblur='prueba(sonda4_8)'/></td>" 
							+"<td  width='2.8%'><input id='sonda4_9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda4_9,tent9,9)'; onblur='prueba(sonda4_9)'/></td>" 
							+"<td width='2.8%'><input id='sonda4_10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda4_10,tent10,10)'; onblur='prueba(sonda4_10)'/></td>" 
							+"<td  width='2.8%'><input id='sonda4_11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda4_11,tent11,11)'; onblur='prueba(sonda4_11)'/></td>" 
							+"<td  width='2.8%'><input id='sonda4_12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda4_12,tent12,12)'; onblur='prueba(sonda4_12)'/></td>"
							+"<td  width='2.8%'><input id='sonda4_13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda4_13,tent13,13)'; onblur='prueba(sonda4_13)'/></td>" 
							+"<td  width='2.8%'><input id='sonda4_t1' type='text' size='1' disabled='disabled'/></td>" 
							+"<td width='2.8%'><input id='sonda4_14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda4_14,tent14,14)'; onblur='prueba(sonda4_14)'/></td>" 
							+"<td  width='2.8%'><input id='sonda4_15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda4_15,tent15,15)'; onblur='prueba(sonda4_15)'/></td>" 
							+"<td  width='2.8%'><input id='sonda4_16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda4_16,tent16,16)'; onblur='prueba(sonda4_16)'/></td>" 
							+"<td  width='2.8%'><input id='sonda4_17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda4_17,tent17,17)'; onblur='prueba(sonda4_17,)'/></td>" 
							+"<td width='2.8%'><input id='sonda4_18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda4_18,tent18,18)'; onblur='prueba(sonda4_18)'/></td>" 
							+"<td  width='2.8%'><input id='sonda4_19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda4_19,tent19,19)'; onblur='prueba(sonda4_19)'/></td>" 
							+"<td  width='2.8%'><input id='sonda4_t2' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='sonda4_20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda4_20,tent20,20)'; onblur='prueba(sonda4_20)'/></td>" 
							+"<td  width='2.8%'><input id='sonda4_21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda4_21,tent21,21)'; onblur='prueba(sonda4_21)'/></td>" 
							+"<td width='2.8%'><input id='sonda4_22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda4_22,tent22,22)'; onblur='prueba(sonda4_22)'/></td>" 
							+"<td  width='2.8%'><input id='sonda4_23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda4_23,tent23,23)'; onblur='prueba(sonda4_23)'/></td>" 
							+"<td  width='2.8%'><input id='sonda4_00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda4_00,tent00,00)'; onblur='prueba(sonda4_00)'/></td>" 
							+"<td  width='2.8%'><input id='sonda4_01' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda4_01,tent01,01)'; onblur='prueba(sonda4_01)'/></td>" 
							+"<td  width='2.8%'><input id='sonda4_02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda4_02,tent02,02)'; onblur='prueba(sonda4_02)'/></td>" 
							+"<td width='2.8%'><input id='sonda4_03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda4_03,tent03,03)'; onblur='prueba(sonda4_03)'/></td>" 
							+"<td  width='2.8%'><input id='sonda4_04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda4_04,tent04,04)'; onblur='prueba(sonda4_04)'/></td>" 
							+"<td  width='2.8%'><input id='sonda4_05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda4_05,tent05,05)'; onblur='prueba(sonda4_05)'/></td>" 
							+"<td  width='2.8%'><input id='sonda4_06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda4_06,tent06,06)'; onblur='prueba(sonda4_06)'/></td>"													
							+"<td  width='2.8%'><input id='sonda4_t3' type='text' size='1' disabled='disabled'/></td>" 
							+"</tr></table>"+
							"</td></tr>"+ 
							
							//sonda5
							"<tr id='sonda5' style='display: none'> <td width='30%'><div id='sond5'>sonda5</div>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='sonda5_7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda5_7,tent7,7)'; onblur='prueba(sonda5_7)'/></td>" 
							+"<td  width='2.8%'><input id='sonda5_8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda5_8,tent8,8)'; onblur='prueba(sonda5_8)'/></td>" 
							+"<td  width='2.8%'><input id='sonda5_9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda5_9,tent9,9)'; onblur='prueba(sonda5_9)'/></td>" 
							+"<td width='2.8%'><input id='sonda5_10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda5_10,tent10,10)'; onblur='prueba(sonda5_10)'/></td>" 
							+"<td  width='2.8%'><input id='sonda5_11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda5_11,tent11,11)'; onblur='prueba(sonda5_11)'/></td>" 
							+"<td  width='2.8%'><input id='sonda5_12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda5_12,tent12,12)'; onblur='prueba(sonda5_12)'/></td>"
							+"<td  width='2.8%'><input id='sonda5_13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda5_13,tent13,13)'; onblur='prueba(sonda5_13)'/></td>" 
							+"<td  width='2.8%'><input id='sonda5_t1' type='text' size='1' disabled='disabled'/></td>" 
							+"<td width='2.8%'><input id='sonda5_14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda5_14,tent14,14)'; onblur='prueba(sonda5_14)'/></td>" 
							+"<td  width='2.8%'><input id='sonda5_15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda5_15,tent15,15)'; onblur='prueba(sonda5_15)'/></td>" 
							+"<td  width='2.8%'><input id='sonda5_16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda5_16,tent16,16)'; onblur='prueba(sonda5_16)'/></td>" 
							+"<td  width='2.8%'><input id='sonda5_17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda5_17,tent17,17)'; onblur='prueba(sonda5_17,)'/></td>" 
							+"<td width='2.8%'><input id='sonda5_18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda5_18,tent18,18)'; onblur='prueba(sonda5_18)'/></td>" 
							+"<td  width='2.8%'><input id='sonda5_19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda5_19,tent19,19)'; onblur='prueba(sonda5_19)'/></td>" 
							+"<td  width='2.8%'><input id='sonda5_t2' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='sonda5_20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda5_20,tent20,20)'; onblur='prueba(sonda5_20)'/></td>" 
							+"<td  width='2.8%'><input id='sonda5_21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda5_21,tent21,21)'; onblur='prueba(sonda5_21)'/></td>" 
							+"<td width='2.8%'><input id='sonda5_22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda5_22,tent22,22)'; onblur='prueba(sonda5_22)'/></td>" 
							+"<td  width='2.8%'><input id='sonda5_23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda5_23,tent23,23)'; onblur='prueba(sonda5_23)'/></td>" 
							+"<td  width='2.8%'><input id='sonda5_00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda5_00,tent00,00)'; onblur='prueba(sonda5_00)'/></td>" 
							+"<td  width='2.8%'><input id='sonda5_01' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda5_01,tent01,01)'; onblur='prueba(sonda5_01)'/></td>" 
							+"<td  width='2.8%'><input id='sonda5_02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda5_02,tent02,02)'; onblur='prueba(sonda5_02)'/></td>" 
							+"<td width='2.8%'><input id='sonda5_03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda5_03,tent03,03)'; onblur='prueba(sonda5_03)'/></td>" 
							+"<td  width='2.8%'><input id='sonda5_04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda5_04,tent04,04)'; onblur='prueba(sonda5_04)'/></td>" 
							+"<td  width='2.8%'><input id='sonda5_05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda5_05,tent05,05)'; onblur='prueba(sonda5_05)'/></td>" 
							+"<td  width='2.8%'><input id='sonda5_06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda5_06,tent06,06)'; onblur='prueba(sonda5_06)'/></td>"													
							+"<td  width='2.8%'><input id='sonda5_t3' type='text' size='1' disabled='disabled'/></td>" 
							+"</tr></table>"+
							"</td></tr>"+ 
							
							//sonda6
							"<tr id='sonda6' style='display: none'> <td width='30%'><div id='sond6'>sonda6</div>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='sonda6_7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda6_7,tent7,7)'; onblur='prueba(sonda6_7)'/></td>" 
							+"<td  width='2.8%'><input id='sonda6_8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda6_8,tent8,8)'; onblur='prueba(sonda6_8)'/></td>" 
							+"<td  width='2.8%'><input id='sonda6_9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda6_9,tent9,9)'; onblur='prueba(sonda6_9)'/></td>" 
							+"<td width='2.8%'><input id='sonda6_10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda6_10,tent10,10)'; onblur='prueba(sonda6_10)'/></td>" 
							+"<td  width='2.8%'><input id='sonda6_11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda6_11,tent11,11)'; onblur='prueba(sonda6_11)'/></td>" 
							+"<td  width='2.8%'><input id='sonda6_12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda6_12,tent12,12)'; onblur='prueba(sonda6_12)'/></td>"
							+"<td  width='2.8%'><input id='sonda6_13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda6_13,tent13,13)'; onblur='prueba(sonda6_13)'/></td>" 
							+"<td  width='2.8%'><input id='sonda6_t1' type='text' size='1' disabled='disabled'/></td>" 
							+"<td width='2.8%'><input id='sonda6_14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda6_14,tent14,14)'; onblur='prueba(sonda6_14)'/></td>" 
							+"<td  width='2.8%'><input id='sonda6_15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda6_15,tent15,15)'; onblur='prueba(sonda6_15)'/></td>" 
							+"<td  width='2.8%'><input id='sonda6_16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda6_16,tent16,16)'; onblur='prueba(sonda6_16)'/></td>" 
							+"<td  width='2.8%'><input id='sonda6_17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda6_17,tent17,17)'; onblur='prueba(sonda6_17,)'/></td>" 
							+"<td width='2.8%'><input id='sonda6_18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda6_18,tent18,18)'; onblur='prueba(sonda6_18)'/></td>" 
							+"<td  width='2.8%'><input id='sonda6_19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda6_19,tent19,19)'; onblur='prueba(sonda6_19)'/></td>" 
							+"<td  width='2.8%'><input id='sonda6_t2' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='sonda6_20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda6_20,tent20,20)'; onblur='prueba(sonda6_20)'/></td>" 
							+"<td  width='2.8%'><input id='sonda6_21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda6_21,tent21,21)'; onblur='prueba(sonda6_21)'/></td>" 
							+"<td width='2.8%'><input id='sonda6_22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda6_22,tent22,22)'; onblur='prueba(sonda6_22)'/></td>" 
							+"<td  width='2.8%'><input id='sonda6_23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda6_23,tent23,23)'; onblur='prueba(sonda6_23)'/></td>" 
							+"<td  width='2.8%'><input id='sonda6_00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda6_00,tent00,00)'; onblur='prueba(sonda6_00)'/></td>" 
							+"<td  width='2.8%'><input id='sonda6_01' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda6_01,tent01,01)'; onblur='prueba(sonda6_01)'/></td>" 
							+"<td  width='2.8%'><input id='sonda6_02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda6_02,tent02,02)'; onblur='prueba(sonda6_02)'/></td>" 
							+"<td width='2.8%'><input id='sonda6_03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda6_03,tent03,03)'; onblur='prueba(sonda6_03)'/></td>" 
							+"<td  width='2.8%'><input id='sonda6_04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda6_04,tent04,04)'; onblur='prueba(sonda6_04)'/></td>" 
							+"<td  width='2.8%'><input id='sonda6_05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda6_05,tent05,05)'; onblur='prueba(sonda6_05)'/></td>" 
							+"<td  width='2.8%'><input id='sonda6_06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda6_06,tent06,06)'; onblur='prueba(sonda6_06)'/></td>"													
							+"<td  width='2.8%'><input id='sonda6_t3' type='text' size='1' disabled='disabled'/></td>" 
							+"</tr></table>"+
							"</td></tr>"+ 
							
							//sonda7
							"<tr id='sonda7' style='display: none'> <td width='30%'><div id='sond7'>sonda7</div>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='sonda7_7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda7_7,tent7,7)'; onblur='prueba(sonda7_7)'/></td>" 
							+"<td  width='2.8%'><input id='sonda7_8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda7_8,tent8,8)'; onblur='prueba(sonda7_8)'/></td>" 
							+"<td  width='2.8%'><input id='sonda7_9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda7_9,tent9,9)'; onblur='prueba(sonda7_9)'/></td>" 
							+"<td width='2.8%'><input id='sonda7_10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda7_10,tent10,10)'; onblur='prueba(sonda7_10)'/></td>" 
							+"<td  width='2.8%'><input id='sonda7_11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda7_11,tent11,11)'; onblur='prueba(sonda7_11)'/></td>" 
							+"<td  width='2.8%'><input id='sonda7_12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda7_12,tent12,12)'; onblur='prueba(sonda7_12)'/></td>"
							+"<td  width='2.8%'><input id='sonda7_13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda7_13,tent13,13)'; onblur='prueba(sonda7_13)'/></td>" 
							+"<td  width='2.8%'><input id='sonda7_t1' type='text' size='1' disabled='disabled'/></td>" 
							+"<td width='2.8%'><input id='sonda7_14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda7_14,tent14,14)'; onblur='prueba(sonda7_14)'/></td>" 
							+"<td  width='2.8%'><input id='sonda7_15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda7_15,tent15,15)'; onblur='prueba(sonda7_15)'/></td>" 
							+"<td  width='2.8%'><input id='sonda7_16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda7_16,tent16,16)'; onblur='prueba(sonda7_16)'/></td>" 
							+"<td  width='2.8%'><input id='sonda7_17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda7_17,tent17,17)'; onblur='prueba(sonda7_17,)'/></td>" 
							+"<td width='2.8%'><input id='sonda7_18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda7_18,tent18,18)'; onblur='prueba(sonda7_18)'/></td>" 
							+"<td  width='2.8%'><input id='sonda7_19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda7_19,tent19,19)'; onblur='prueba(sonda7_19)'/></td>" 
							+"<td  width='2.8%'><input id='sonda7_t2' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='sonda7_20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda7_20,tent20,20)'; onblur='prueba(sonda7_20)'/></td>" 
							+"<td  width='2.8%'><input id='sonda7_21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda7_21,tent21,21)'; onblur='prueba(sonda7_21)'/></td>" 
							+"<td width='2.8%'><input id='sonda7_22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda7_22,tent22,22)'; onblur='prueba(sonda7_22)'/></td>" 
							+"<td  width='2.8%'><input id='sonda7_23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda7_23,tent23,23)'; onblur='prueba(sonda7_23)'/></td>" 
							+"<td  width='2.8%'><input id='sonda7_00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda7_00,tent00,00)'; onblur='prueba(sonda7_00)'/></td>" 
							+"<td  width='2.8%'><input id='sonda7_01' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda7_01,tent01,01)'; onblur='prueba(sonda7_01)'/></td>" 
							+"<td  width='2.8%'><input id='sonda7_02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda7_02,tent02,02)'; onblur='prueba(sonda7_02)'/></td>" 
							+"<td width='2.8%'><input id='sonda7_03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda7_03,tent03,03)'; onblur='prueba(sonda7_03)'/></td>" 
							+"<td  width='2.8%'><input id='sonda7_04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda7_04,tent04,04)'; onblur='prueba(sonda7_04)'/></td>" 
							+"<td  width='2.8%'><input id='sonda7_05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda7_05,tent05,05)'; onblur='prueba(sonda7_05)'/></td>" 
							+"<td  width='2.8%'><input id='sonda7_06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda7_06,tent06,06)'; onblur='prueba(sonda7_06)'/></td>"													
							+"<td  width='2.8%'><input id='sonda7_t3' type='text' size='1' disabled='disabled'/></td>" 
							+"</tr></table>"+
							"</td></tr>"+ 
							
							//sonda8
							"<tr id='sonda8' style='display: none'> <td width='30%'><div id='sond8'>sonda8</div>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='sonda8_7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda8_7,tent7,7)'; onblur='prueba(sonda8_7)'/></td>" 
							+"<td  width='2.8%'><input id='sonda8_8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda8_8,tent8,8)'; onblur='prueba(sonda8_8)'/></td>" 
							+"<td  width='2.8%'><input id='sonda8_9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda8_9,tent9,9)'; onblur='prueba(sonda8_9)'/></td>" 
							+"<td width='2.8%'><input id='sonda8_10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda8_10,tent10,10)'; onblur='prueba(sonda8_10)'/></td>" 
							+"<td  width='2.8%'><input id='sonda8_11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda8_11,tent11,11)'; onblur='prueba(sonda8_11)'/></td>" 
							+"<td  width='2.8%'><input id='sonda8_12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda8_12,tent12,12)'; onblur='prueba(sonda8_12)'/></td>"
							+"<td  width='2.8%'><input id='sonda8_13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda8_13,tent13,13)'; onblur='prueba(sonda8_13)'/></td>" 
							+"<td  width='2.8%'><input id='sonda8_t1' type='text' size='1' disabled='disabled'/></td>" 
							+"<td width='2.8%'><input id='sonda8_14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda8_14,tent14,14)'; onblur='prueba(sonda8_14)'/></td>" 
							+"<td  width='2.8%'><input id='sonda8_15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda8_15,tent15,15)'; onblur='prueba(sonda8_15)'/></td>" 
							+"<td  width='2.8%'><input id='sonda8_16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda8_16,tent16,16)'; onblur='prueba(sonda8_16)'/></td>" 
							+"<td  width='2.8%'><input id='sonda8_17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda8_17,tent17,17)'; onblur='prueba(sonda8_17,)'/></td>" 
							+"<td width='2.8%'><input id='sonda8_18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda8_18,tent18,18)'; onblur='prueba(sonda8_18)'/></td>" 
							+"<td  width='2.8%'><input id='sonda8_19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda8_19,tent19,19)'; onblur='prueba(sonda8_19)'/></td>" 
							+"<td  width='2.8%'><input id='sonda8_t2' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='sonda8_20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda8_20,tent20,20)'; onblur='prueba(sonda8_20)'/></td>" 
							+"<td  width='2.8%'><input id='sonda8_21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda8_21,tent21,21)'; onblur='prueba(sonda8_21)'/></td>" 
							+"<td width='2.8%'><input id='sonda8_22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda8_22,tent22,22)'; onblur='prueba(sonda8_22)'/></td>" 
							+"<td  width='2.8%'><input id='sonda8_23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda8_23,tent23,23)'; onblur='prueba(sonda8_23)'/></td>" 
							+"<td  width='2.8%'><input id='sonda8_00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda8_00,tent00,00)'; onblur='prueba(sonda8_00)'/></td>" 
							+"<td  width='2.8%'><input id='sonda8_01' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda8_01,tent01,01)'; onblur='prueba(sonda8_01)'/></td>" 
							+"<td  width='2.8%'><input id='sonda8_02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda8_02,tent02,02)'; onblur='prueba(sonda8_02)'/></td>" 
							+"<td width='2.8%'><input id='sonda8_03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda8_03,tent03,03)'; onblur='prueba(sonda8_03)'/></td>" 
							+"<td  width='2.8%'><input id='sonda8_04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda8_04,tent04,04)'; onblur='prueba(sonda8_04)'/></td>" 
							+"<td  width='2.8%'><input id='sonda8_05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda8_05,tent05,05)'; onblur='prueba(sonda8_05)'/></td>" 
							+"<td  width='2.8%'><input id='sonda8_06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda8_06,tent06,06)'; onblur='prueba(sonda8_06)'/></td>"													
							+"<td  width='2.8%'><input id='sonda8_t3' type='text' size='1' disabled='disabled'/></td>" 
							+"</tr></table>"+
							"</td></tr>"+ 
							
							//sonda9
							"<tr id='sonda9' style='display: none'> <td width='30%'><div id='sond9'>sonda9</div>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='sonda9_7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda9_7,tent7,7)'; onblur='prueba(sonda9_7)'/></td>" 
							+"<td  width='2.8%'><input id='sonda9_8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda9_8,tent8,8)'; onblur='prueba(sonda9_8)'/></td>" 
							+"<td  width='2.8%'><input id='sonda9_9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda9_9,tent9,9)'; onblur='prueba(sonda9_9)'/></td>" 
							+"<td width='2.8%'><input id='sonda9_10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda9_10,tent10,10)'; onblur='prueba(sonda9_10)'/></td>" 
							+"<td  width='2.8%'><input id='sonda9_11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda9_11,tent11,11)'; onblur='prueba(sonda9_11)'/></td>" 
							+"<td  width='2.8%'><input id='sonda9_12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda9_12,tent12,12)'; onblur='prueba(sonda9_12)'/></td>"
							+"<td  width='2.8%'><input id='sonda9_13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda9_13,tent13,13)'; onblur='prueba(sonda9_13)'/></td>" 
							+"<td  width='2.8%'><input id='sonda9_t1' type='text' size='1' disabled='disabled'/></td>" 
							+"<td width='2.8%'><input id='sonda9_14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda9_14,tent14,14)'; onblur='prueba(sonda9_14)'/></td>" 
							+"<td  width='2.8%'><input id='sonda9_15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda9_15,tent15,15)'; onblur='prueba(sonda9_15)'/></td>" 
							+"<td  width='2.8%'><input id='sonda9_16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda9_16,tent16,16)'; onblur='prueba(sonda9_16)'/></td>" 
							+"<td  width='2.8%'><input id='sonda9_17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda9_17,tent17,17)'; onblur='prueba(sonda9_17,)'/></td>" 
							+"<td width='2.8%'><input id='sonda9_18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda9_18,tent18,18)'; onblur='prueba(sonda9_18)'/></td>" 
							+"<td  width='2.8%'><input id='sonda9_19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda9_19,tent19,19)'; onblur='prueba(sonda9_19)'/></td>" 
							+"<td  width='2.8%'><input id='sonda9_t2' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='sonda9_20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda9_20,tent20,20)'; onblur='prueba(sonda9_20)'/></td>" 
							+"<td  width='2.8%'><input id='sonda9_21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda9_21,tent21,21)'; onblur='prueba(sonda9_21)'/></td>" 
							+"<td width='2.8%'><input id='sonda9_22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda9_22,tent22,22)'; onblur='prueba(sonda9_22)'/></td>" 
							+"<td  width='2.8%'><input id='sonda9_23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda9_23,tent23,23)'; onblur='prueba(sonda9_23)'/></td>" 
							+"<td  width='2.8%'><input id='sonda9_00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda9_00,tent00,00)'; onblur='prueba(sonda9_00)'/></td>" 
							+"<td  width='2.8%'><input id='sonda9_01' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda9_01,tent01,01)'; onblur='prueba(sonda9_01)'/></td>" 
							+"<td  width='2.8%'><input id='sonda9_02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda9_02,tent02,02)'; onblur='prueba(sonda9_02)'/></td>" 
							+"<td width='2.8%'><input id='sonda9_03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda9_03,tent03,03)'; onblur='prueba(sonda9_03)'/></td>" 
							+"<td  width='2.8%'><input id='sonda9_04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda9_04,tent04,04)'; onblur='prueba(sonda9_04)'/></td>" 
							+"<td  width='2.8%'><input id='sonda9_05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda9_05,tent05,05)'; onblur='prueba(sonda9_05)'/></td>" 
							+"<td  width='2.8%'><input id='sonda9_06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda9_06,tent06,06)'; onblur='prueba(sonda9_06)'/></td>"													
							+"<td  width='2.8%'><input id='sonda9_t3' type='text' size='1' disabled='disabled'/></td>" 
							+"</tr></table>"+
							"</td></tr>"+ 
							
							//sonda10
							"<tr id='sonda10' style='display: none'> <td width='30%'><div id='sond10'>sonda10</div>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='sonda10_7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda10_7,tent7,7)'; onblur='prueba(sonda10_7)'/></td>" 
							+"<td  width='2.8%'><input id='sonda10_8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda10_8,tent8,8)'; onblur='prueba(sonda10_8)'/></td>" 
							+"<td  width='2.8%'><input id='sonda10_9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda10_9,tent9,9)'; onblur='prueba(sonda10_9)'/></td>" 
							+"<td width='2.8%'><input id='sonda10_10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda10_10,tent10,10)'; onblur='prueba(sonda10_10)'/></td>" 
							+"<td  width='2.8%'><input id='sonda10_11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda10_11,tent11,11)'; onblur='prueba(sonda10_11)'/></td>" 
							+"<td  width='2.8%'><input id='sonda10_12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda10_12,tent12,12)'; onblur='prueba(sonda10_12)'/></td>"
							+"<td  width='2.8%'><input id='sonda10_13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda10_13,tent13,13)'; onblur='prueba(sonda10_13)'/></td>" 
							+"<td  width='2.8%'><input id='sonda10_t1' type='text' size='1' disabled='disabled'/></td>" 
							+"<td width='2.8%'><input id='sonda10_14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda10_14,tent14,14)'; onblur='prueba(sonda10_14)'/></td>" 
							+"<td  width='2.8%'><input id='sonda10_15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda10_15,tent15,15)'; onblur='prueba(sonda10_15)'/></td>" 
							+"<td  width='2.8%'><input id='sonda10_16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda10_16,tent16,16)'; onblur='prueba(sonda10_16)'/></td>" 
							+"<td  width='2.8%'><input id='sonda10_17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda10_17,tent17,17)'; onblur='prueba(sonda10_17,)'/></td>" 
							+"<td width='2.8%'><input id='sonda10_18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda10_18,tent18,18)'; onblur='prueba(sonda10_18)'/></td>" 
							+"<td  width='2.8%'><input id='sonda10_19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda10_19,tent19,19)'; onblur='prueba(sonda10_19)'/></td>" 
							+"<td  width='2.8%'><input id='sonda10_t2' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='sonda10_20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda10_20,tent20,20)'; onblur='prueba(sonda10_20)'/></td>" 
							+"<td  width='2.8%'><input id='sonda10_21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda10_21,tent21,21)'; onblur='prueba(sonda10_21)'/></td>" 
							+"<td width='2.8%'><input id='sonda10_22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda10_22,tent22,22)'; onblur='prueba(sonda10_22)'/></td>" 
							+"<td  width='2.8%'><input id='sonda10_23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda10_23,tent23,23)'; onblur='prueba(sonda10_23)'/></td>" 
							+"<td  width='2.8%'><input id='sonda10_00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda10_00,tent00,00)'; onblur='prueba(sonda10_00)'/></td>" 
							+"<td  width='2.8%'><input id='sonda10_01' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda10_01,tent01,01)'; onblur='prueba(sonda10_01)'/></td>" 
							+"<td  width='2.8%'><input id='sonda10_02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda10_02,tent02,02)'; onblur='prueba(sonda10_02)'/></td>" 
							+"<td width='2.8%'><input id='sonda10_03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda10_03,tent03,03)'; onblur='prueba(sonda10_03)'/></td>" 
							+"<td  width='2.8%'><input id='sonda10_04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda10_04,tent04,04)'; onblur='prueba(sonda10_04)'/></td>" 
							+"<td  width='2.8%'><input id='sonda10_05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda10_05,tent05,05)'; onblur='prueba(sonda10_05)'/></td>" 
							+"<td  width='2.8%'><input id='sonda10_06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sonda10_06,tent06,06)'; onblur='prueba(sonda10_06)'/></td>"													
							+"<td  width='2.8%'><input id='sonda10_t3' type='text' size='1' disabled='disabled'/></td>" 
							+"</tr></table>"+
							"</td></tr>"+ 
							
							
							//sANGRE
							"<tr> <td width='30%'><div id='sangre'>SANGRE</div>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='sang7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sang7,tent7,7)'; onblur='prueba(sang7)'/></td>" 
							+"<td  width='2.8%'><input id='sang8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sang8,tent8,8)'; onblur='prueba(sang8)'/></td>" 
							+"<td  width='2.8%'><input id='sang9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sang9,tent9,9)'; onblur='prueba(sang9)'/></td>" 
							+"<td width='2.8%'><input id='sang10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sang10,tent10,10)'; onblur='prueba(sang10)'/></td>" 
							+"<td  width='2.8%'><input id='sang11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sang11,tent11,11)'; onblur='prueba(sang11)'/></td>" 
							+"<td  width='2.8%'><input id='sang12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sang12,tent12,12)'; onblur='prueba(sang12)'/></td>"
							+"<td  width='2.8%'><input id='sang13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sang13,tent13,13)'; onblur='prueba(sang13)'/></td>" 
							+"<td  width='2.8%'><input id='sangt1' type='text' size='1' disabled='disabled'/></td>" 
							+"<td width='2.8%'><input id='sang14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sang14,tent14,14)'; onblur='prueba(sang14)'/></td>" 
							+"<td  width='2.8%'><input id='sang15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sang15,tent15,15)'; onblur='prueba(sang15)'/></td>" 
							+"<td  width='2.8%'><input id='sang16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sang16,tent16,16)'; onblur='prueba(sang16)'/></td>" 
							+"<td  width='2.8%'><input id='sang17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sang17,tent17,17)'; onblur='prueba(sang17)'/></td>" 
							+"<td width='2.8%'><input id='sang18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sang18,tent18,18)'; onblur='prueba(sang18)'/></td>" 
							+"<td  width='2.8%'><input id='sang19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sang19,tent19,19)'; onblur='prueba(sang19)'/></td>" 
							+"<td  width='2.8%'><input id='sangt2' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='sang20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sang20,tent20,20)'; onblur='prueba(sang20)'/></td>" 
							+"<td  width='2.8%'><input id='sang21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sang21,tent21,21)'; onblur='prueba(sang21)'/></td>" 
							+"<td width='2.8%'><input id='sang22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sang22,tent22,22)'; onblur='prueba(sang22)'/></td>" 
							+"<td  width='2.8%'><input id='sang23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sang23,tent23,23)'; onblur='prueba(sang23)'/></td>" 
							+"<td  width='2.8%'><input id='sang00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sang00,tent00,00)'; onblur='prueba(sang00)'/></td>" 
							+"<td  width='2.8%'><input id='sang01' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sang01,tent01,01)'; onblur='prueba(sang01)'/></td>" 
							+"<td  width='2.8%'><input id='sang02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sang02,tent02,02)'; onblur='prueba(sang02)'/></td>" 
							+"<td width='2.8%'><input id='sang03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sang03,tent03,03)'; onblur='prueba(sang03)'/></td>" 
							+"<td  width='2.8%'><input id='sang04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sang04,tent04,04)'; onblur='prueba(sang04)'/></td>" 
							+"<td  width='2.8%'><input id='sang05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sang05,tent05,05)'; onblur='prueba(sang05)'/></td>" 
							+"<td  width='2.8%'><input id='sang06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(sang06,tent06,06)'; onblur='prueba(sang06)'/></td>"													
							+"<td  width='2.8%'><input id='sangt3' type='text' size='1' disabled='disabled'/></td>" 
							+"</tr></table>"+
							"</td></tr>"+
							//PLASMA
						    "<tr> <td width='30%'><div id='plasma'>PLASMA</div>" +
						    "<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='plasm7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(plasm7,tent7,7)'; onblur='prueba(plasm7)'/></td>" 
							+"<td  width='2.8%'><input id='plasm8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(plasm8,tent8,8)'; onblur='prueba(plasm8)'/></td>" 
							+"<td  width='2.8%'><input id='plasm9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(plasm9,tent9,9)'; onblur='prueba(plasm9)'/></td>" 
							+"<td width='2.8%'><input id='plasm10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(plasm10,tent10,10)'; onblur='prueba(plasm10)'/></td>" 
							+"<td  width='2.8%'><input id='plasm11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(plasm11,tent11,11)'; onblur='prueba(plasm11)'/></td>" 
							+"<td  width='2.8%'><input id='plasm12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(plasm12,tent12,12)'; onblur='prueba(plasm12)'/></td>"
							+"<td  width='2.8%'><input id='plasm13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(plasm13,tent13,13)'; onblur='prueba(plasm13)'/></td>" 
							+"<td  width='2.8%'><input id='plasmt1' type='text' size='1' disabled='disabled'/></td>" 
							+"<td width='2.8%'><input id='plasm14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(plasm14,tent14,14)'; onblur='prueba(plasm14)'/></td>" 
							+"<td  width='2.8%'><input id='plasm15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(plasm15,tent15,15)'; onblur='prueba(plasm15)'/></td>" 
							+"<td  width='2.8%'><input id='plasm16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(plasm16,tent16,16)'; onblur='prueba(plasm16)'/></td>" 
							+"<td  width='2.8%'><input id='plasm17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(plasm17,tent17,17)'; onblur='prueba(plasm17)'/></td>" 
							+"<td width='2.8%'><input id='plasm18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(plasm18,tent18,18)'; onblur='prueba(plasm18)'/></td>" 
							+"<td  width='2.8%'><input id='plasm19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(plasm19,tent19,19)'; onblur='prueba(plasm19)'/></td>" 
							+"<td  width='2.8%'><input id='plasmt2' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='plasm20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(plasm20,tent20,20)'; onblur='prueba(plasm20)'/></td>" 
							+"<td  width='2.8%'><input id='plasm21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(plasm21,tent21,21)'; onblur='prueba(plasm21)'/></td>" 
							+"<td width='2.8%'><input id='plasm22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(plasm22,tent22,22)'; onblur='prueba(plasm22)'/></td>" 
							+"<td  width='2.8%'><input id='plasm23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(plasm23,tent23,23)'; onblur='prueba(plasm23)'/></td>" 
							+"<td  width='2.8%'><input id='plasm00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(plasm00,tent00,00)'; onblur='prueba(plasm00)'/></td>" 
							+"<td  width='2.8%'><input id='plasm01' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(plasm01,tent01,01)'; onblur='prueba(plasm01)'/></td>" 
							+"<td  width='2.8%'><input id='plasm02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(plasm02,tent02,02)'; onblur='prueba(plasm02)'/></td>" 
							+"<td width='2.8%'><input id='plasm03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(plasm03,tent03,03)'; onblur='prueba(plasm03)'/></td>" 
							+"<td  width='2.8%'><input id='plasm04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(plasm04,tent04,04)'; onblur='prueba(plasm04)'/></td>" 
							+"<td  width='2.8%'><input id='plasm05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(plasm05,tent05,05)'; onblur='prueba(plasm05)'/></td>" 
							+"<td  width='2.8%'><input id='plasm06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(plasm06,tent06,06)'; onblur='prueba(plasm06)'/></td>"													
							+"<td  width='2.8%'><input id='plasmt3' type='text' size='1' disabled='disabled'/></td>" 
							+"</tr></table>"+
						    "</td></tr>"+
						    //COLOIDES
							"<tr> <td width='30%'><div id='coloides'>COLOIDES</div>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='col7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(col7,tent7,7)'; onblur='prueba(col7)'/></td>" 
							+"<td  width='2.8%'><input id='col8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(col8,tent8,8)'; onblur='prueba(col8)'/></td>" 
							+"<td  width='2.8%'><input id='col9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(col9,tent9,9)'; onblur='prueba(col9)'/></td>" 
							+"<td width='2.8%'><input id='col10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(col10,tent10,10)'; onblur='prueba(col10)'/></td>" 
							+"<td  width='2.8%'><input id='col11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(col11,tent11,11)'; onblur='prueba(col11)'/></td>" 
							+"<td  width='2.8%'><input id='col12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(col12,tent12,12)'; onblur='prueba(col12)'/></td>"
							+"<td  width='2.8%'><input id='col13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(col13,tent13,13)'; onblur='prueba(col13)'/></td>" 
							+"<td  width='2.8%'><input id='colt1' type='text' size='1' disabled='disabled'/></td>" 
							+"<td width='2.8%'><input id='col14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(col14,tent14,14)'; onblur='prueba(col14)'/></td>" 
							+"<td  width='2.8%'><input id='col15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(col15,tent15,15)'; onblur='prueba(col15)'/></td>" 
							+"<td  width='2.8%'><input id='col16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(col16,tent16,16)'; onblur='prueba(col16)'/></td>" 
							+"<td  width='2.8%'><input id='col17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(col17,tent17,17)'; onblur='prueba(col17)'/></td>" 
							+"<td width='2.8%'><input id='col18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(col18,tent18,18)'; onblur='prueba(col18)'/></td>" 
							+"<td  width='2.8%'><input id='col19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(col19,tent19,19)'; onblur='prueba(col19)'/></td>" 
							+"<td  width='2.8%'><input id='colt2' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='col20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(col20,tent20,20)'; onblur='prueba(col20)'/></td>" 
							+"<td  width='2.8%'><input id='col21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(col21,tent21,21)'; onblur='prueba(col21)'/></td>" 
							+"<td width='2.8%'><input id='col22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(col22,tent22,22)'; onblur='prueba(col22)'/></td>" 
							+"<td  width='2.8%'><input id='col23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(col23,tent23,23)'; onblur='prueba(col23)'/></td>" 
							+"<td  width='2.8%'><input id='col00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(col00,tent00,00)'; onblur='prueba(col00)'/></td>" 
							+"<td  width='2.8%'><input id='col01' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(col01,tent01,01)'; onblur='prueba(col01)'/></td>" 
							+"<td  width='2.8%'><input id='col02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(col02,tent02,02)'; onblur='prueba(col02)'/></td>" 
							+"<td width='2.8%'><input id='col03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(col03,tent03,03)'; onblur='prueba(col03)'/></td>" 
							+"<td  width='2.8%'><input id='col04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(col04,tent04,04)'; onblur='prueba(col04)'/></td>" 
							+"<td  width='2.8%'><input id='col05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(col05,tent05,05)'; onblur='prueba(col05)'/></td>" 
							+"<td  width='2.8%'><input id='col06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumeros(col06,tent06,06)'; onblur='prueba(col06)'/></td>"													
							+"<td  width='2.8%'><input id='colt3' type='text' size='1' disabled='disabled'/></td>" 
							+"</tr></table>"+
							"</td></tr>"+       
							
							
							//total entradas 
							"<tr  bgcolor='#2E9AFE'> <td width='30%'><div id='tent'>TOTAL ENTRADAS</div></td>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='tent7' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tent8' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tent9' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tent10' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tent11' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tent12' type='text' size='1' disabled='disabled'/></td>"
							+"<td  width='2.8%'><input id='tent13' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tentt1' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tent14' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tent15' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tent16' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tent17' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tent18' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tent19' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tentt2' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tent20' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tent21' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tent22' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tent23' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tent00' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tent01' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tent02' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tent03' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tent04' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tent05' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tent06' type='text' size='1' disabled='disabled'/></td>"													
							+"<td  width='2.8%'><input id='tentt3' type='text' size='1' disabled='disabled'/></td>" 
							+"</tr></table></td></tr>" +
							
							
					        //fin liquidos entradas precargado todos los dias 
					      
							
							
					        "</table>");
				
					
			
		}
		
		
		//fin entradas y salidas todos los dias
		/*
		if(va.equals("pre")){
			String FechaKardex=req.getParameter("FechaKardex");
			String CodAdmK1=req.getParameter("CodAdmK");
			Vector <String> contenidoInformeGeneral = mvf.buscarEntSal(FechaKardex, CodAdmK1);
			String contenido ="";
			int i =0;
			for(i=0; i<contenidoInformeGeneral.size();i++ ){
				contenido +=contenidoInformeGeneral.elementAt(i)+"&";
				
			}
			System.out.println("cont"+contenido);
			out.print(contenido);
		}*/
		
		
		if(va.equals("mostrarsv")){
			String FechaKardex=req.getParameter("FechaKardex");
			String CodAdmK1=req.getParameter("CodAdmK");
			Vector <String> contenidoSv = mvf.buscarSv(FechaKardex, CodAdmK1);
			String contenido ="";
			int i =0;
			for(i=0; i<contenidoSv.size();i++ ){
				contenido +=contenidoSv.elementAt(i)+"&";
				
			}
			System.out.println("cont"+contenido);
			out.print(contenido);
		}
		
	/*	if(va.equals("buscarDrenaje")){
			String FechaKardex=req.getParameter("FechaKardex");
			String CodAdmK1=req.getParameter("CodAdmK");
			Vector <String> contenidoDrenaje = mvf.buscarDrenaje(FechaKardex, CodAdmK1);
			String contenido ="";
			int i =0;
			for(i=0; i<contenidoDrenaje.size();i++ ){
				contenido +=contenidoDrenaje.elementAt(i)+"&";
				
			}
			System.out.println("buscar drenaje "+contenido);
			out.print(contenido);
		}
		
		
		if(va.equals("buscarSonda")){
			String FechaKardex=req.getParameter("FechaKardex");
			String CodAdmK1=req.getParameter("CodAdmK");
			Vector <String> contenidoSonda = mvf.buscarSonda (FechaKardex, CodAdmK1);
			String contenido ="";
			int i =0;
			for(i=0; i<contenidoSonda.size();i++ ){
				contenido +=contenidoSonda.elementAt(i)+"&";
				
			}
			System.out.println("buscar sonda "+contenido);
			out.print(contenido);
		}*/
		
		
		if(va.equals("buscarDesocultar")){
			String FechaKardex=req.getParameter("FechaKardex");
			String CodAdmK1=req.getParameter("CodAdmK");
			Vector <String> contenidoDesocultar = mvf.buscarTodosDesocultar(FechaKardex, CodAdmK1);
			String contenido ="";
			int i =0;
			for(i=0; i<contenidoDesocultar.size();i++ ){
				contenido +=contenidoDesocultar.elementAt(i)+"&";
				
			}
			System.out.println("buscar desocultar "+contenido);
			out.print(contenido);
		}
		
		/*
	if(va.equals("guardarDrenaje")){
		String FechaKardex=req.getParameter("FechaKardex");
		String conte_div=req.getParameter("conte_div");
    
    insercciones_realizadas_con_exito=mvf.insertarDrenaje (
		    nom_text,
		    conte_div,
		    codPac,
		    Codusuario,
			fechacjmysql,
			hracjmysql, 
			codAdm,
			FechaKardex,
			"1"
			
			);

		}
	
	
	if(va.equals("guardarSonda")){
		String FechaKardex=req.getParameter("FechaKardex");
		String conte_div=req.getParameter("conte_div");
    
    insercciones_realizadas_con_exito=mvf.insertarSonda (
		    nom_text,
		    conte_div,
		    codPac,
		    Codusuario,
			fechacjmysql,
			hracjmysql, 
			codAdm,
			FechaKardex,
			"1"
			
			);

		}
	
	if(va.equals("actUltDrena")){
		String FechaKardex=req.getParameter("FechaKardex");
		String adm = req.getParameter("adm");
	    
	     String y;
		y = mvf.ultimo_drenaje(adm, FechaKardex);
			 out.print(y);
	}
		
	
	if(va.equals("actUltSonda")){
		String FechaKardex=req.getParameter("FechaKardex");
		String adm = req.getParameter("adm");
	    
	     String y;
		y = mvf.ultima_sonda(adm, FechaKardex);
			//String [] cad = y.split("&");
			//aparecer = cad[0];
		   // nomId = cad[1];
		   
		
		    out.print(y);
	}*/
	
	
		
	}//fin post

}//fin clase
