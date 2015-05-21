/***********************/
/**
 * controlador: ControlParametros se encuentra la forma en que son 
 * creadas los parametros que se van a usar para la creacion de los horarios
 * de atencion.
 */
package pyp_controlador;



import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pyp_metodo.PYP_MetodoMedicos;
import pyp_metodo.PYP_MetodoParametros;



/**
 * Servlet implementation class ControlParametros
 */
public class PYP_Pruebas extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		res.setContentType("text/html;charset=UTF-8");
		String va = req.getParameter("valor");
		String Dia=req.getParameter("Dia");
		String HoraInicial=req.getParameter("HoraInicial");
		String HoraFinal=req.getParameter("HoraFinal");
		String tiempoCita=req.getParameter("tiempoCita");		
		String FechaActual=req.getParameter("FechaActual");
		/********variables para guardar el horario del medico***********/
		String CodigoMedico=req.getParameter("CodigoMedico");
		String HorasMedico=req.getParameter("HorasMedico");
		String FechaMedico=req.getParameter("FechaMedico");
		String estado="0";
		/*******************/
		PrintWriter out=res.getWriter();
		PYP_MetodoParametros mp = new PYP_MetodoParametros();
		PYP_MetodoMedicos mmd = new PYP_MetodoMedicos();
		ResultSet rs=null;
		ResultSet rs1=null;				
		
		if(va.equals("guardar")){
			mmd.CrearHorarioMedico(HorasMedico, FechaMedico, estado, CodigoMedico);
		}
		
		if(va.equals("1")){
		mp.CrearParametro(Dia, HoraInicial, HoraFinal, tiempoCita);
		out.print("Ingreso Exitoso.");
		}
		
		if(va.equals("horario")){
			rs=mmd.BuscarMedicoTodos();
			out.print("<table width='100%' border='1'><tr><td colspan='2'><div align='center' class='style11' id='cabecera2'>Asignar Horario Medico </div></td></tr><tr><td width='15%'><div>Seleccione Medico </div></td><td><div><select name='cmbMedico' id='cmbMedico' onchange='horario()'><option value='Seleccione' selected='selected'>Seleccione</option>");
			try {
				while(rs.next()){
					String nombre=rs.getString(2);
					String apellidos=rs.getString(3);
					String Completo=nombre+" "+apellidos;
					out.print("<option value="+rs.getString(1)+">"+Completo+"</option>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}			
			out.print("</select></div></td></tr><tr><td colspan='2'><div id='Horario'></div></td></tr></table>");
			//out.print("<label><input name='radiobutton' type='radio' value='radiobutton' onclick='Radio()' id='mes' />Por Mes</label></div></td></tr><tr><td colspan='4'><div id='Horario'></div></td></tr></table>");
			
		}	
		if(va.equals("asignar")){				
			out.print("<table width='100%' border='1'><tr><td width='50%'><div align='center'><label><input name='chkTodos' type='checkbox' id='chkTodos' onClick='checkAll()' />Seleccionar Todos</label></div></td><td width='50%'><div align='center'><input name='btnAsignar' type='button' id='btnAsignar' value='Asignar' onclick='Chkdato()' /></div></td></tr></table>");
			java.util.Calendar fecha = java.util.Calendar.getInstance();
			int cj = 1+fecha.get(java.util.Calendar.MONTH);
			int cj2= fecha.get(java.util.Calendar.YEAR);
			int i=0;
			String horaI="";
			String minutoI="";
			String JornadaI="";
			String horaF="";
			String minutoF="";
			String JornadaF="";
			String Lapso="";
			String p1="";
			String p2="";
			String p3="";
			String p1f="";
			String p2f="";
			String p3f="";
	//Igual al calendario pero x meses
			if(cj==12){
				//si es diciembre
					out.print("<table border='1' width='21%'><tr align='center'  id='cabecera2'>");
					fecha.set(cj2,11,1);

					int dia = fecha.get(fecha.DAY_OF_WEEK);
					
					String v[] = {"D","L","M","M","J","V","S"," ","D","L","M","M","J","V","S"," ","D","L","M","M","J","V","S"," ","D","L","M","M","J","V","S"," ","D","L","M","M","J","V","S"," ","D","L","M","M","J","V","S"};
					String m[][] = new String[70][70];
					out.print("<td width='6%'>HORA</td>");
					for (int z=0; z<35+dia-1; z++){
					  out.print("<td width='6%'>"+v[z]+"</td>");
					}
					out.print("<tr></tr>");
					
					
					/*************nuevo *************/
					rs1=mp.BuscarParametros();
					 try {
						while(rs1.next()){
							horaI=rs1.getString(2).substring(0,2);
							minutoI=rs1.getString(2).substring(3,5);
							JornadaI=rs1.getString(2).substring(6,8);
							
							horaF=rs1.getString(3).substring(0,2);
							minutoF=rs1.getString(3).substring(3,5);
							JornadaF=rs1.getString(3).substring(6,8);
							
							Lapso=rs1.getString(4);
						}
						rs1.getStatement().getConnection().close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					 p1 = horaI;//horaInicial
					 p2 = minutoI;//minutosInicial
					 p3 = JornadaI;//jornada AM-PM Inicial
					
					 p1f=horaF;
					 p2f=minutoF;
					 p3f=JornadaF;
					/*******************fin nuevo****************************/
					
					//validar la Hora de finalización
					int contador=0;
					int cont=0;
					int hi=Integer.parseInt(p1);// hora inicial
					int mi=Integer.parseInt(p2);// min inicial   p3 es tt
				
					int lapso=Integer.parseInt(Lapso);//lapso
			    	int xyz=0; 
			    	
			    	int hf=Integer.parseInt(p1f);// hora final
					int mf=Integer.parseInt(p2f);// min final   p3f es tt					
					//validar las horas
					int sw=0;
					int c=0;
					int cm=0;
					int compara=hi;
					while(sw==0){
						c=c+1;
						compara=compara+1;
						if(compara==hf){sw=1;}
					}
					if(mi<mf){cm=mf-mi;}
					if(mi>mf){cm=mf+mi; c=c-1;}
					int suma=(((c*60)+cm)/lapso);
					//fin de validacion de horas			
					out.print("<td>&nbsp;</td>");
						if(dia!=1){
							for (int k=0; k<=dia-2 ; k++){
								out.print("<td> &nbsp;</td>");
						   }//fin for k
						  }//fin if dia=1
						for (int j=0; j<31; j++){
							out.print("<td>"+String.valueOf(j+1)+"</td>");
							if(j!=0){
								if((j+dia) % 7 == 0){
									 out.print("<td>&nbsp;</td>"); 
								 }
								}
					}
					out.print("<tr></tr>");					
					for (i=0; i<suma; i++){
					 if(dia!=1){
						for (int k=0; k<=dia-2 ; k++){
							m[i][k]="&nbsp;";
					   }//fin for k
					  }//fin if dia=1
					 for (int j=0; j<31+dia-1; j++){	
						  m[i][j+dia-1]="&nbsp;";
					 }//fin for j
					}//fin for i
					//IMPRIMIR MATRIZ
			    	int xy=mi;
					for (i=0; i<suma; i++){
						contador=contador+1;						
					      if(contador==1){ 
						  out.print("<td>"+p1+":"+p2+" "+p3+"</td>"); 
					      }else{
					    	  xy=xy+lapso;
					    	  //Validaciones de las horas
					    	  int resta=xy/60;
					    	  if(xy>59){xy=(xy-(60*resta)); xyz=Integer.parseInt(p1)+resta; p1=String.valueOf(xyz);} //si sobrepasa una hora
					    	  if((p1.equals("13"))&&(p3.equals("AM"))){p1="1"; p3="PM";}
					          if((p1.equals("13"))&&(p3.equals("PM"))){p1="1"; p3="AM";}
					          if(xy==0){
					          out.print("<td>"+p1+":00 "+p3+"</td>");
					          }else{
					    	  out.print("<td>"+p1+":"+xy+" "+p3+"</td>");
					    	  }
					      }	 
					 for (int j=0; j<31+dia-1; j++){							
							if(j!=0){
							if((j) % 7 == 0){
								 out.print("<td bgcolor=#0099FF>&nbsp;</td>"); 
							 }
							}
							cont=cont+1;
							int g=j;
							g=g-1;
							String dato="";
							dato=p1+":"+xy+":"+p3+"|"+cj2+"-"+cj+"-"+g;

								out.print("<td ><input name='chkHorarioMedico' type='checkbox' id='chkHorarioMedico' value="+dato+" />"+m[i][j]+"</td>");

								
						}
						out.print("<tr></tr>");
					}
				    out.print("</tr><input name='txtCont' type='hidden' id='txtCont' value="+cont+" /></table>");
				    
					
				}//fin cj=12
			
			if(cj==1){
				out.print("<table border='1' width='21%'><tr align='center'  id='cabecera2'>");
				fecha.set(cj2,00,1);

				int dia = fecha.get(fecha.DAY_OF_WEEK);
				
				String v[] = {"D","L","M","M","J","V","S"," ","D","L","M","M","J","V","S"," ","D","L","M","M","J","V","S"," ","D","L","M","M","J","V","S"," ","D","L","M","M","J","V","S"," ","D","L","M","M","J","V","S"};
				String m[][] = new String[70][70];
				out.print("<td width='6%'>HORA</td>");
				for (int z=0; z<35+dia-1; z++){
				  out.print("<td width='6%'>"+v[z]+"</td>");
				}
				out.print("<tr></tr>");
				
				
				/*************nuevo *************/
				rs1=mp.BuscarParametros();
				 try {
					while(rs1.next()){
						horaI=rs1.getString(2).substring(0,2);
						minutoI=rs1.getString(2).substring(3,5);
						JornadaI=rs1.getString(2).substring(6,8);
						
						horaF=rs1.getString(3).substring(0,2);
						minutoF=rs1.getString(3).substring(3,5);
						JornadaF=rs1.getString(3).substring(6,8);
						
						Lapso=rs1.getString(4);
					}
					rs1.getStatement().getConnection().close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				 p1 = horaI;//horaInicial
				 p2 = minutoI;//minutosInicial
				 p3 = JornadaI;//jornada AM-PM Inicial
				
				 p1f=horaF;
				 p2f=minutoF;
				 p3f=JornadaF;
				/*******************fin nuevo****************************/
				
				//validar la Hora de finalización
				int contador=0;
				int cont=0;
				int hi=Integer.parseInt(p1);// hora inicial
				int mi=Integer.parseInt(p2);// min inicial   p3 es tt
			
				int lapso=Integer.parseInt(Lapso);//lapso
		    	int xyz=0; 
		    	
		    	int hf=Integer.parseInt(p1f);// hora final
				int mf=Integer.parseInt(p2f);// min final   p3f es tt					
				//validar las horas
				int sw=0;
				int c=0;
				int cm=0;
				int compara=hi;
				while(sw==0){
					c=c+1;
					compara=compara+1;
					if(compara==hf){sw=1;}
				}
				if(mi<mf){cm=mf-mi;}
				if(mi>mf){cm=mf+mi; c=c-1;}
				int suma=(((c*60)+cm)/lapso);
				//fin de validacion de horas			
				out.print("<td>&nbsp;</td>");
					if(dia!=1){
						for (int k=0; k<=dia-2 ; k++){
							out.print("<td> &nbsp;</td>");
					   }//fin for k
					  }//fin if dia=1
					for (int j=0; j<31; j++){
						out.print("<td>"+String.valueOf(j+1)+"</td>");
						if(j!=0){
							if((j+dia) % 7 == 0){
								 out.print("<td>&nbsp;</td>"); 
							 }
							}
				}
				out.print("<tr></tr>");					
				for (i=0; i<suma; i++){
				 if(dia!=1){
					for (int k=0; k<=dia-2 ; k++){
						m[i][k]="&nbsp;";
				   }//fin for k
				  }//fin if dia=1
				 for (int j=0; j<31+dia-1; j++){	
					  m[i][j+dia-1]="&nbsp;";
				 }//fin for j
				}//fin for i
				//IMPRIMIR MATRIZ
		    	int xy=mi;
				for (i=0; i<suma; i++){
					contador=contador+1;						
				      if(contador==1){ 
					  out.print("<td>"+p1+":"+p2+" "+p3+"</td>"); 
				      }else{
				    	  xy=xy+lapso;
				    	  //Validaciones de las horas
				    	  int resta=xy/60;
				    	  if(xy>59){xy=(xy-(60*resta)); xyz=Integer.parseInt(p1)+resta; p1=String.valueOf(xyz);} //si sobrepasa una hora
				    	  if((p1.equals("13"))&&(p3.equals("AM"))){p1="1"; p3="PM";}
				          if((p1.equals("13"))&&(p3.equals("PM"))){p1="1"; p3="AM";}
				          if(xy==0){
				          out.print("<td>"+p1+":00 "+p3+"</td>");
				          }else{
				    	  out.print("<td>"+p1+":"+xy+" "+p3+"</td>");
				    	  }
				      }	 
				 for (int j=0; j<31+dia-1; j++){							
						if(j!=0){
						if((j) % 7 == 0){
							 out.print("<td bgcolor=#0099FF>&nbsp;</td>"); 
						 }
						}
						cont=cont+1;
						int g=j;
						g=g-1;
						String dato="";
						dato=p1+":"+xy+":"+p3+"|"+cj2+"-"+cj+"-"+g;

							out.print("<td ><input name='chkHorarioMedico' type='checkbox' id='chkHorarioMedico' value="+dato+" />"+m[i][j]+"</td>");

							
					}
					out.print("<tr></tr>");
				}
			    out.print("</tr><input name='txtCont' type='hidden' id='txtCont' value="+cont+" /></table>");
			    
				
			}//fin cj=1
		}
		
		if(va.equals("mes")){
			String anio=FechaActual.substring(0,4);
			String mes=FechaActual.substring(5,7);
			String dia=FechaActual.substring(8,10);
		}
		
	}

	
}
