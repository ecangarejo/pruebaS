package aiepi_Controlador;

/*IMPORTO EL METODO Y LIBRERIAS Y CLASES GENERALES Y REQUERIDAS*/
import aiepi_metodo.MetodoConfiguracion; 

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Date;
import java.text.SimpleDateFormat;

import com.sun.org.apache.xml.internal.serialize.Printer;

import fact_bean.Entidad;

/*CREO LA CLASE ControlConfiguracion QUE HEREDA DE HttpServlet*/
public class ControlConfiguracion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/*INSTANCIO EN LA VARIABLE mc LA CLASE MetodoConfiguracion*/
	MetodoConfiguracion mc = new MetodoConfiguracion();
	
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
	}
		
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");
		
		String opcion = req.getParameter("op");
		
		PrintWriter out = res.getWriter();
		
		                                             boolean presentaDiag = false;
		
		//verificacion de datos del paciente
		if(opcion.equals("1")){
			String tdoc = req.getParameter("tdoc");
			String ndoc = req.getParameter("ndoc");
			String codUsuario = req.getParameter("codUsuario");
						
			//codigo paciente
			ResultSet rs = mc.verificarPaciente(ndoc, tdoc);
			String codPac = "";
			int anio = 0;
			int meses = 0;
			int dias = 0;
			try{
				while(rs.next()){
					codPac = rs.getString(1);
					anio = rs.getInt(2);
					meses = rs.getInt(3);
					dias = rs.getInt(4);
				}
				rs.getStatement().close();
			}catch(SQLException e1){
				e1.getMessage();
			}
			
			java.util.Calendar hoy = java.util.Calendar.getInstance();
			int anioActual = hoy.get(java.util.Calendar.YEAR);
			int mesActual = hoy.get(java.util.Calendar.MONTH)+1;
			int diaActual = hoy.get(java.util.Calendar.DATE);
			
			int horaAct = hoy.get(java.util.Calendar.HOUR_OF_DAY);
			int minutoActual = hoy.get(java.util.Calendar.MINUTE);
			int segundoActual = hoy.get(java.util.Calendar.SECOND);
			
			
			String fechaActual = anioActual+"-"+mesActual+"-"+diaActual;
			String horaActual = horaAct+":"+minutoActual+":"+segundoActual;
			
			rs = mc.obtenerCodAiepi();
			String codAiepi = "";
			try{
				while(rs.next()){
					codAiepi = rs.getString(1);
				}
				rs.getStatement().close();
			}catch(SQLException e1){
				e1.getMessage();
			}
			if (codAiepi.equals("")){
				codAiepi = "ai_1";
			}else{
				String[] codAiepiDeT = codAiepi.split("_");
				int consecutivo = Integer.parseInt(codAiepiDeT[1]);
				consecutivo++;
				codAiepi = codAiepiDeT[0]+"_"+consecutivo;
			}
			
			
			
			
			
			//codigo de la primera area
			String tipo_aiepi="";

			if(anio==-1){
				anio=0;
			}
			if(meses<0){
				meses+=12;
			}
			
			
			if(anio==0&&meses==0&&dias==0){
				tipo_aiepi="tipo_aiepi_1";
			}else{
				if(anio==0){
					if((meses>=0&&meses<=1)||(meses==0&&dias>=1)){
						tipo_aiepi="tipo_aiepi_2";
					}else{
						tipo_aiepi="tipo_aiepi_3";
					}
				}else{
					if(anio<=5){
						if(anio==5&&meses==0&&dias==0){
							tipo_aiepi="tipo_aiepi_3";
						}else{
							if(anio<5){
								tipo_aiepi="tipo_aiepi_3";
							}
						}
					}
					
				}				
			}
			rs = mc.Area(tipo_aiepi);
			String codArea = "";
			try{
				while(rs.next()){
					codArea += rs.getString(1)+"%";
				}
				rs.getStatement().close();
			}catch(SQLException e1){
				e1.getMessage();
			}
			
			if(codArea.length()>0){
				codArea = codArea.substring(0,codArea.length()-1);
			}
			
			
			if(codPac!=""){
				if (anio>=0&&anio<=5){
					rs = mc.obtenerAiepiXFecha(codPac, fechaActual);
					String tieneAiepi = "";
					try{
						while(rs.next()){
							tieneAiepi += rs.getString(1);
						}
						rs.getStatement().close();
					}catch(SQLException e1){
						e1.getMessage();
					}
					//if(tieneAiepi.equals("")){
						out.print("pe-"+codArea+"-"+tipo_aiepi+"-"+anio+"-"+meses+"-"+dias+"-"+codAiepi+"-"+codPac+"-"+fechaActual);
						mc.guardarAiepi(codAiepi, fechaActual, horaActual, tipo_aiepi, codPac, codUsuario);
					//}else{
						//out.print("AI");
					//}

				}else{
					out.print("na");
				}	
			}else{
				out.print("ne");
			}
			
		}
		
		
		//preguntas
		if(opcion.equals("2")){
			boolean nuevoGrupo = true;
			 
			String area = req.getParameter("codArea");
			String anio = req.getParameter("anio");
			String meses = req.getParameter("meses");
			String dias = req.getParameter("dias");
			
			//System.out.println(anio+" "+meses+" "+dias);
			
			ResultSet rs = mc.preguntas(area);
			
			String preguntas = "";
			String tipoRespuesta = "";
			try{
				while(rs.next()){
					preguntas += rs.getString(1)+"-"+rs.getString(2)+"å";
					tipoRespuesta += rs.getString(3)+"å";
				}
				rs.getStatement().close();
			}catch(SQLException e1){
				e1.getMessage();
			}
			preguntas = preguntas.substring(0, preguntas.length()-1);
			tipoRespuesta = tipoRespuesta.substring(0,tipoRespuesta.length()-1);
			
			String[] grupoPreguntas = preguntas.split("å");
			String[] grupoTipoRespuesta = tipoRespuesta.split("å");
			
			for (int i=0;i<grupoPreguntas.length;i++){
				String[] pregunta = grupoPreguntas[i].split("-");
				//obtener el tipo de respuesta de una pregunta, utilizado para diseño
				
				String tipoResp = grupoTipoRespuesta[i];
				
				//obtener las respuestas para una pregunta
				if (tipoResp.equals("Si/No")){
					rs = mc.preguntaRespuestas(pregunta[0],"");
				}else{
					rs = mc.preguntaRespuestas(pregunta[0],pregunta[0]);
				}
				String respuestas = "";
				//System.out.println(rs.toString());
				try{
					while(rs.next()){
						
						respuestas += rs.getString(1)+"&"+rs.getString(2)+",";
						
					}
					rs.getStatement().close();
				}catch(SQLException e1){
					e1.getMessage();
				}
				
				if(respuestas.length()>0){
					respuestas = respuestas.substring(0,respuestas.length()-1);
				}

				String[] grupoResp = respuestas.split(",");
				
				//obtener si la preguntas contiene preguntas que dependan de ella
				rs = mc.existeDependencia(pregunta[0]);
				int existeDependecia = 0;
				String respDepen = "";
				//System.out.println(rs.toString());
				try{
					while(rs.next()){
						existeDependecia++;
						respDepen = rs.getString(1);
					}
					rs.getStatement().close();
				}catch(SQLException e1){
					e1.getMessage();
				}
				
				
				
				if (tipoResp.equals("Si/No")){
					if (nuevoGrupo){
						//out.print("<div>");
							out.print("<div class='encSN'>");
								out.print("<label class='descSN' >Si</label>");
								out.print("<label>No</label>");
							out.print("</div>");
						//out.print("</div>");
						nuevoGrupo = false;
					}
					
					out.print("<div class='pregunta' id='"+pregunta[0]+"'>");
						out.print("<label class='pregunta'>"+pregunta[1]+"</label>");
							for(int j=0;j<grupoResp.length;j++){
								
								String[] resp = grupoResp[j].split("&");
								out.print("<div>");
								//System.out.println(respDepen+"->"+resp[0]);
								if (existeDependecia>0&&resp[0].equals(respDepen)){
									out.print("<input type='radio' name='"+pregunta[1]+"' value='"+pregunta[0]+"-"+resp[0]+"' onclick='cargarPregDepend(this)' />");
								}else{
									out.print("<input type='radio' name='"+pregunta[1]+"' value='"+pregunta[0]+"-"+resp[0]+"' onclick='ocultarPregDepend(this)'/>");
								}
								out.print("</div>");
							}
							
					out.print("</div>");			
				}else{
					if (tipoResp.equals("Seleccion")){
						nuevoGrupo = true;
						out.print("<div class='pregunta' id='"+pregunta[0]+"'>");
						out.print("<label class='pregunta'>"+pregunta[1]+"</label>");
						out.print("<div class='select'>");
						if (existeDependecia>0){
							out.print("<select class='seleccion' name="+pregunta[0]+" onchange='cargarPregDepend(this)' >");
						}else{
							out.print("<select class='seleccion' name="+pregunta[0]+">");
						}
							out.print("<option value='"+pregunta[0]+"-' selected='selected'>Seleccione</option>");
	
						for(int j=0;j<grupoResp.length;j++){
							String[] resp = grupoResp[j].split("&");
							out.print("<option value='"+pregunta[0]+"-"+resp[0]+"'>"+resp[1]+"</option>"); 
						}
						out.print("</select>"); 
						out.print("</div>");
						out.print("</div>");						
					}else{
						if (tipoResp.equals("Abierta(Texto corto)")){
							
							nuevoGrupo = true;
							out.print("<div class='pregunta' id='"+pregunta[0]+"'>");
							out.print("<label class='pregunta'>"+pregunta[1]+"</label>");
							out.print("<div>");
							out.print("<input class='text' type='text' name='"+pregunta[0]+"' onkeyup='soloNumero(this)'/>");
							out.print("</div>");
							out.print("</div>");						
						}else{
							if (tipoResp.equals("Encabezado")){
								nuevoGrupo = true;
								out.print("<div class='pregunta' >");
								out.print("<label class='preguntaEnc'>"+pregunta[1]+"</label>");
								out.print("</div>");						
							}else{
								if (tipoResp.equals("Abierta(Texto largo)")){
									nuevoGrupo = true;
									
									out.print("<div class='pregunta' id='"+pregunta[0]+"'>");
									out.print("<label class='pregunta'>"+pregunta[1]+"</label>");
									out.print("<div>");
									out.print("<input class='textl' type='text' name='"+pregunta[0]+"'/>");
									out.print("</div>");
									out.print("</div>");						
								}
							}
							
						}
					}
				
				}
				
			}
			out.print("<input id='boton' onclick='obtenerDatosRespuesta(&quot;"+area+"&quot;,this)' name='nombre' value='Enviar' type='button'>");
			nuevoGrupo = true;
			
			
			

		}
		
		if(opcion.equals("3")){
			boolean nuevoGrupo = true;
			 
			String codPregunta = req.getParameter("codPreg");
			String codResp = req.getParameter("codResp");

			ResultSet rs = mc.ObtenerPreguntaDependencia(codPregunta,codResp);
			
			String preguntas = "";
			try{
				while(rs.next()){
					preguntas += rs.getString(1)+"-"+rs.getString(2)+",";
				}
				rs.getStatement().close();
			}catch(SQLException e1){
				e1.getMessage();
			}
			
			//System.out.println(preguntas);
			if(preguntas.length()>0){
				preguntas = preguntas.substring(0, preguntas.length()-1);
	
				
				String[] grupoPreguntas = preguntas.split(",");
				
				for (int i=0;i<grupoPreguntas.length;i++){
					String[] pregunta = grupoPreguntas[i].split("-");
					//obtener el tipo de respuesta de una pregunta, utilizado para diseño
					rs = mc.preguntaTipoResp(pregunta[0]);
					String tipoResp = "";
					try{
						while(rs.next()){
							tipoResp = rs.getString(1);
						}
						rs.getStatement().close();
					}catch(SQLException e1){
						e1.getMessage();
					}
					
					//obtener las respuestas para una pregunta
					if (tipoResp.equals("Si/No")){
						rs = mc.preguntaRespuestas(pregunta[0],"");
					}else{
						rs = mc.preguntaRespuestas(pregunta[0],pregunta[0]);
					}
					
					
					String respuestas = "";
					try{
						while(rs.next()){
							respuestas += rs.getString(1)+"&"+rs.getString(2)+",";
						}
						rs.getStatement().close();
					}catch(SQLException e1){
						e1.getMessage();
					}
					respuestas = respuestas.substring(0,respuestas.length()-1);
	
					String[] grupoResp = respuestas.split(",");
					
					//obtener si la preguntas contiene preguntas que dependan de ella
					rs = mc.existeDependencia(pregunta[0]);
					int existeDependecia = 0;
					try{
						while(rs.next()){
							existeDependecia = rs.getInt(1);
						}
						rs.getStatement().close();
					}catch(SQLException e1){
						e1.getMessage();
					}
					
					
					
					if (tipoResp.equals("Si/No")){
						if (nuevoGrupo){
							//out.print("<div>");
							out.print("<div class='encSN'>");
								out.print("<label class='descSN2' >Si</label>");
								out.print("<label>No</label>");
							out.print("</div>");
							//out.print("</div>");
							nuevoGrupo = false;
						}
						
						out.print("<div class='pregunta2' id='"+pregunta[0]+"'>");
						out.print("<label class='pregunta2'>"+pregunta[1]+"</label>");
						for(int j=0;j<grupoResp.length;j++){
							String[] resp = grupoResp[j].split("&");
							out.print("<div>");
							if (existeDependecia>0){
								out.print("<input type='radio' name='"+pregunta[1]+"' value='"+pregunta[0]+"-"+resp[0]+"' onclick='cargarPregDepend(this)' />");
							}else{
								out.print("<input type='radio' name='"+pregunta[1]+"' value='"+pregunta[0]+"-"+resp[0]+"'/>");
							}
							out.print("</div>");
						}
						
						out.print("</div>");			
					}else{
						if (tipoResp.equals("Seleccion")){
							nuevoGrupo = true;
							out.print("<div  class='pregunta2' id='"+pregunta[0]+"'>");
							out.print("<label class='pregunta'>"+pregunta[1]+"</label>");
							out.print("<div>");
							if (existeDependecia>0){
								out.print("<select class='seleccion2' name="+pregunta[0]+" onchange='cargarPregDepend(this)' >");
							}else{
								out.print("<select class='seleccion2' name="+pregunta[0]+">");
							}
								out.print("<option selected='selected'>Seleccione</option>");
		
							for(int j=0;j<grupoResp.length;j++){
								String[] resp = grupoResp[j].split("&");
								out.print("<option value='"+pregunta[0]+"-"+resp[0]+"'>"+resp[1]+"</option>"); 
							}
							out.print("</select>"); 
							out.print("</div>");
							out.print("</div>");
						}else{
							if (tipoResp.equals("Abierta")){
								nuevoGrupo = true;
								out.print("<div  class='pregunta2' id='"+pregunta[0]+"'>");
								out.print("<label class='pregunta'>"+pregunta[1]+"</label>");
								out.print("<div>");
								out.print("<input class='text' type='text' name='"+pregunta[0]+"'/>");
								out.print("</div>");
								out.print("</div>");						
							}
						}
					}
	
				}
				nuevoGrupo = true;
				out.print("<div style='visibility:hidden'>");
				out.print("</div>");
			}
		}
		
		
		if (opcion.equals("4")){
			int cantDiagUsuario = 0;//numero de diagnosticos positivos del usuario
			String respArea = "0";
			String respuestas = req.getParameter("resp");
			String codArea = req.getParameter("codArea");
			String tipoAiepi = req.getParameter("tipoAiepi");
			String codAiepi = req.getParameter("codAiepi");
			String respDiagnostico ="";//las respuestas que dan con el diagnostico
			String codDiag = "";
			
			//System.out.println(tipoAiepi);
			respuestas = respuestas.substring(0,respuestas.length()-1);
			
			
			//obteniendo los diagnosticos de un area
			ResultSet rs = mc.obtenerDiagnsticoArea(codArea,tipoAiepi);
			String diagnosticos = "";
			try{
				while(rs.next()){
					diagnosticos += rs.getString(1)+"&"+rs.getString(2)+"&"+rs.getInt(3)+"%";
				}
				rs.getStatement().close();
			}catch(SQLException e1){
				e1.getMessage();
			}
			
			if (diagnosticos.length()>0){//verificar si el area presenta diagnosticos o es de solo insercion en la base de datos
				diagnosticos = diagnosticos.substring(0,diagnosticos.length()-1);
				
				
				
				String[] vectDiagnosticos = diagnosticos.split("%");
				int cantDiagposibles = vectDiagnosticos.length; //numero posibles de daignosticos del area 
				
				String diagUsuarios = "";//almacenar los diagnosticos de un usuario
				
				for (int i=0;i<vectDiagnosticos.length;i++){
					String[] vectDetDiag = vectDiagnosticos[i].split("&");
					
					//obtener el tipo de condicion de las respuestas del diagnostico
					rs = mc.obtenerTipoCondDiag(vectDetDiag[0]);
					String tipoCondDiag = "";
					try{
						while(rs.next()){
							tipoCondDiag += rs.getString(1);
						}
						rs.getStatement().close();
					}catch(SQLException e1){
						e1.getMessage();
					}
	
					//obtener las preguntas y respuestas para la condicion de un diagnostico 
					rs = mc.obtenerCondDiag(vectDetDiag[0]);
					String PregResp = ""; //codigo de las preguntas y respuestas de una condicion
					try{
						while(rs.next()){
							PregResp += rs.getString(1)+"-"+rs.getString(2)+",";
						}
						rs.getStatement().close();
					}catch(SQLException e1){
						e1.getMessage();
					}
					PregResp = PregResp.substring(0,PregResp.length()-1);
	
					
					//comparacion de las repuestas con la condicion del diagnostico
					int cont = 0;
					String[] vectRespUsuario = respuestas.split(",");
					String[] vectRespDiag = PregResp.split(",");
					int numCond = vectRespDiag.length;
					for (int j=0;j<vectRespUsuario.length;j++){
						String[] vectDetRespUsuario = vectRespUsuario[j].split("-");
						for (int k = 0;k<vectRespDiag.length;k++){
							String[] vectDetRespDiag = vectRespDiag[k].split("-");
							
							if ((vectDetRespUsuario[0].contentEquals(vectDetRespDiag[0]))&&(vectDetRespUsuario[1].contentEquals(vectDetRespDiag[1]))){
								cont++;
								respDiagnostico += vectDetRespUsuario[0]+"-"+vectDetRespUsuario[1]+",";
								k = vectRespDiag.length;
								
							}
							
						}
						
						
						
						
					}
					
					
					if (tipoCondDiag.equals("cond_1")){
						if(cont>0){
							diagUsuarios += vectDetDiag[1]+"|"+vectDetDiag[2]+"&";
							codDiag += vectDetDiag[0]+"&";
							cantDiagUsuario++;
						}else{
							//
						}
					}else{
						if (tipoCondDiag.equals("cond_3")){
							if(cont>=2){
								diagUsuarios += vectDetDiag[1]+"|"+vectDetDiag[2]+"&";
								codDiag += vectDetDiag[0]+"&";
								cantDiagUsuario++;
							}else{
								//
							}
						}else{
							if(cont==numCond){
								diagUsuarios += vectDetDiag[1]+"|"+vectDetDiag[2]+"&";
								codDiag += vectDetDiag[0]+"&";
								cantDiagUsuario++;
							}
						}
					}
				}
				
				if(respDiagnostico.length()>0){
					respDiagnostico = respDiagnostico.substring(0,respDiagnostico.length()-1);
				}
				
				if(codDiag.length()>0){
					codDiag = codDiag.substring(0,codDiag.length()-1);
				}
				
				
				if(cantDiagUsuario==cantDiagposibles){
					diagUsuarios = diagUsuarios.substring(0,diagUsuarios.length()-1);					
					respArea = "1";
					presentaDiag=true;
				}else{
					if(cantDiagUsuario>0){
						diagUsuarios = diagUsuarios.substring(0,diagUsuarios.length()-1);
						respArea = "1";
						presentaDiag=true;
					}
				}	
				rs = mc.obtenerAreas(tipoAiepi,codArea,respArea);
				String areas = "";
				try{
					while(rs.next()){
						areas += rs.getString(1)+"-";
					}
					
					rs.getStatement().close();
				}catch(SQLException e1){
					e1.getMessage();
				}
				if(areas.length()>0){
					areas = areas.substring(0,areas.length()-1);
				}
				
				//guardar respuestas del aiepi
				String[] detRespuesta = respuestas.split(",");
				for (int ab = 0;ab<detRespuesta.length;ab++){
					String[] detResp = detRespuesta[ab].split("-");
					mc.guardarRespAiepi(codAiepi, codArea, detResp[0], detResp[1]);
				}
				
				//guardar los diagnosticos
				if(codDiag.length()>0){
					String[] detDiag = codDiag.split("&");
					for(int ac=0;ac<detDiag.length;ac++){
						mc.guardarDiagAiepi(codAiepi, codArea, detDiag[ac]);
						
						String[] detRespDiagnostico = respDiagnostico.split(",");
						for (int ad=0;ad<detRespDiagnostico.length;ad++){
						 	String[] detResp2 = detRespDiagnostico[ad].split("-");
						 	
							mc.guardarRespDiagAiepi(codAiepi, detDiag[ac], detResp2[0], detResp2[1]);
						}
					}
				}
					
				
				
				
				
				out.print(diagUsuarios+"="+areas);
				
					
					
				
			}
			
		}
		
		//
		if (opcion.equals("5")){
			String codArea = req.getParameter("codArea");		
			ResultSet rs = mc.datosAreas(codArea);
			
			String contenido = "";
			String descArea = "";
			String posicion = "";
			String tipo_resp = "";
			String resp_positiva = "";
			
			try{
				while(rs.next()){
					posicion = rs.getString(1)+"%";
					descArea = rs.getString(2);
					tipo_resp = rs.getString(3); 
					resp_positiva = rs.getString(4); 
				}
				rs.getStatement().close();
			}catch(SQLException e1){
				e1.getMessage();
			}
			if(!tipo_resp.equals("N/A")){
				if (tipo_resp.equals("Si/No")){
					int pos = 0;
					String[] sn = {"Si","No"};
					
					contenido += "<div class='encabezadoArea'>";
					contenido += descArea;
					rs = mc.areasRespuestas(codArea);
					try{
						while(rs.next()){
							if (resp_positiva.equals(rs.getString(1))){
								contenido += "<label>"+sn[pos]+"</label><input id ='rd"+sn[pos]+codArea+"' type='radio' name='"+codArea+"' value='"+codArea+"-"+rs.getString(1)+"' onclick='preguntas(&quot;"+codArea+"&quot;,this)'/>";
							}else{
								contenido += "<label>"+sn[pos]+"</label><input id ='rd"+sn[pos]+codArea+"' type='radio' name='"+codArea+"' value='"+codArea+"-"+rs.getString(1)+"' onclick='ocultarPreguntas(&quot;"+codArea+"&quot;)'/>";

							}
							pos++;
						}
						rs.getStatement().close();
					}catch(SQLException e1){
						e1.getMessage();
					}
					contenido += "</div>";
				}
			}else{
				contenido = "<div class='encabezadoArea'>"+descArea+"</div>";
			}
			out.print(posicion+contenido+"%"+tipo_resp);
			

			
		}
		
		if (opcion.equals("6")){
			String codPac = req.getParameter("codPac");
			String codAiepi = req.getParameter("codAiepi");
			String fecha = req.getParameter("fecha");
			
			
			
			ResultSet rs = mc.obtenerDiagPaciente2(codPac, codAiepi);
			int diagUsuario = 0;
			try{
				while(rs.next()){
					diagUsuario++;
				}
			}catch(SQLException e1){
				e1.getMessage();
			}
			if(diagUsuario==0){
				System.out.println("no tiene diag");
				mc.guardarDiagAiepi(codAiepi, "a0", "diag_0");
			}
			
			String Paciente = "";
			String Identificacion = "";
			String Genero = "";
			String NomEntidad = "";
			int anio = 0;
			int mes = 0;
			int dia = 0;
			rs = mc.obtenerDatosPaciente(codPac);
			try{
				while(rs.next()){
					Paciente = rs.getString(1);
					Identificacion = rs.getString(2);
					Genero = rs.getString(3);
					NomEntidad = rs.getString(4);
					anio = rs.getInt(5);
					mes = rs.getInt(6);
					dia = rs.getInt(7);
				}
				rs.getStatement().close();
			}catch(SQLException e1){
				e1.getMessage();
			}
			
			String edad = "";
			if(anio==0){
				    if(mes==0){
				        if(dia==0){
				           edad = "Acabado de nacer";
				        }else{
				        	edad = dia+" día(s)";
				        }
				    }else{
				    	if(dia==0){
				    		edad = mes+" mes(es)";
					    }else{
					    	edad = mes+" mes(es) y "+dia+" día(s)";
					    }
				    }      				    
			}else{
				if(mes==0){
				    if(dia==0){
				        edad = anio+" año(s)";
				    }else{
				    	edad=anio+" año(s) y "+dia+" día(s)";
				    }
				}else{
					edad=anio+" año(s) y "+mes+" mes(es)";
				}
			}
			out.print("<input type='text' id='oculto' class=''>");
			out.print("<div class='datos'>");
			out.print("<div class='Linea'>");
			out.print("<label class='datEnc'>NOMBRE:</label><p id='nomUsu'>"+Paciente+"</p>");
			out.print("<label class='datEnc'>DOCUMENTO:</label><p id='docUsu'>"+Identificacion+"</p>");
			out.print("</div>");
			out.print("<div class='Linea'>");
			out.print("<label class='datEnc'>SEXO:</label><p id='sexUsu'>"+Genero+"</p>");
			out.print("<label class='datEnc'>EDAD:</label><p id='edadUsu' value='"+edad+"'>"+edad+"</p>");
			out.print("</div>");
			out.print("<div class='Linea'>");
			out.print("<label class='datEnc'>ENTIDAD:</label><p id='entUsu'>"+NomEntidad+"</p>");
			out.print("</div>");
			out.print("</div>");
			out.print("<label class='encDiag'>El paciente presenta los siguientes diagnosticos:</label>");
			out.print("<div class='grupoDiag' style='overflow: auto'>");
			
			String descDiag = "";
			String codDiag = "";
			String plan = "";
			rs = mc.obtenerDiagPaciente(codPac, codAiepi);
			
			try{
				while(rs.next()){
					out.print("<div  class='detDiag'>");
					out.print("<p class='descDiag'>"+rs.getString(1)+"</p>");
					out.print("<label class='encDiag2'>debido a:</label>");
										
					ResultSet rs2 = mc.obtenerRespDiagPaciente(rs.getString(2), codAiepi);
					try{
						while(rs2.next()){
							out.print("<p class='descPreg'>"+rs2.getString(1)+"</p>");
						}
						rs2.getStatement().close();
					}catch(SQLException e1){
						e1.getMessage();
					}
										
					out.print("<label class='encDiag2'>Planes a seguir:</label>");

					out.print("<p class='descPlan'>"+rs.getString(4)+"</p>");
											
					out.print("</div>");

					
					
				}
				rs.getStatement().close();
			}catch(SQLException e1){
				e1.getMessage();
			}
			
					
			out.print("</div>");
			out.print("<div class='observaciones'>");
			out.print("<div><label class='encDiag'>Observaciones medicas:</label></div>");
			out.print("<textarea id='observaciones'></textarea>");
			out.print("</div>");
			out.print("<div class='botones'><input type='button' id='imprimir' value='Imprimir' onclick='impReporte(&quot;"+codPac+"&quot;"+","+"&quot;"+fecha+"&quot)' /><input type='button' id='finalizar' value='Finalizar' onclick='finalizarReporte()' /></div>");
		
		
	}
		
	if (opcion.equals("7")){
		String codAiepi = req.getParameter("codAiepi");
		String edad = req.getParameter("edad");
		String observaciones = req.getParameter("observaciones");
		mc.guardarObservaciones(codAiepi, edad, observaciones);
	}
	
	if (opcion.equals("8")){
		String nDoc = req.getParameter("ndoc");
		String tDoc = req.getParameter("tdoc");
		String nombre="";
		String genero="";
		String codPac="";
		ResultSet rs = mc.obtenerDatosPacAiepi(tDoc, nDoc);
		try{
			while(rs.next()){
				nombre = rs.getString(1);
				genero = rs.getString(2);
				codPac = rs.getString(3);
			}
			rs.getStatement().close();
		}catch(SQLException e1){
			e1.getMessage();
		}
		if(nombre.equals("")&&genero.equals("")&&codPac.equals("")){
			out.print("na_");
		}else{
			///
			String Paciente = "";
			String Identificacion = "";
			String Genero = "";
			String NomEntidad = "";
			int anio = 0;
			int mes = 0;
			int dia = 0;
			rs = mc.obtenerDatosPaciente(codPac);
			try{
				while(rs.next()){
					Paciente = rs.getString(1);
					Identificacion = rs.getString(2);
					Genero = rs.getString(3);
					NomEntidad = rs.getString(4);
					anio = rs.getInt(5);
					mes = rs.getInt(6);
					dia = rs.getInt(7);
				}
				rs.getStatement().close();
			}catch(SQLException e1){
				e1.getMessage();
			}
			String edad = "";
			if(anio==0){
				    if(mes==0){
				        if(dia==0){
				           edad = "Acabado de nacer";
				        }else{edad = dia+" día(s)";}
				    }else{
				    	if(dia==0){
				    		edad = mes+" mes(es)";
					    }else{
					    	edad = mes+" mes(es) y "+dia+" día(s)";
					    }
				    }      				    
			}else{
				if(mes==0){
				    if(dia==0){
				        edad = anio+" año(s)";
				    }else{
				    	edad=anio+" año(s) y "+dia+" día(s)";
				    }
				}else{
					edad=anio+" año(s) y "+mes+" mes(es)";
				}
			}
				
				////
			out.print("pe_");
			out.print("<div class='datos2'>");
			out.print("<div class='Linea'>");
			out.print("<label class='datEnc'>NOMBRE:</label><p id='nomUsu'>"+Paciente+"</p>");
			out.print("<label class='datEnc'>DOCUMENTO:</label><p id='docUsu'>"+Identificacion+"</p>");
			out.print("</div>");
			out.print("<div class='Linea'>");
			out.print("<label class='datEnc'>SEXO:</label><p id='sexUsu'>"+genero+"</p>");
			out.print("<label class='datEnc'>EDAD:</label><p id='edadUsu' value=''>"+edad+"</p>");
			out.print("</div>");
			out.print("<div class='Linea'>");
			out.print("<label class='datEnc'>ENTIDAD:</label><p id='entUsu'>"+NomEntidad+"</p>");
			out.print("</div>");
			out.print("</div>");
			out.print("<div  class='encTabla'>");
			out.print("<div class='tbTAI'>Tipo AIEPI realizado</div>");
			out.print("<div class='tbFecha'>Fecha</div>");
			out.print("<div class='tbReal'>Realizado por</div>");
			out.print("<div class='tbRes'></div>");
			out.print("</div>");
			
			out.print("<div  class='tbResultados'>");
			
			rs = mc.obtenerAiepiPac(codPac);
			try{
				while(rs.next()){
					out.print("<div class='tbTAI'>"+rs.getString(1)+"</div>");
					out.print("<div class='tbFecha'>"+rs.getString(3)+"</div>");
					out.print("<div class='tbReal'>"+rs.getString(2)+"</div>");
					out.print("<div class='tbRes'><a href='#' onclick='Abrir(&quot;"+codPac+"&quot;,&quot;"+rs.getString(3)+"&quot;);'>Ver resultados</a></div>");
				}
				rs.getStatement().close();
			}catch(SQLException e1){
				e1.getMessage();
			}

			out.print("</div>");
			
		}
	}
	
	
}
}