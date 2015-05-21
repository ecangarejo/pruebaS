package fact_controlador;

import fact_metodo.MetodoMovimientos;
import fact_metodo.MetodoRips;

import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JFileChooser;

import cont_metodo.MetodoCuentas;

public class ControlRips extends HttpServlet {

	/**
	 * Se guarda y consulta todo lo relacionado con una admision.
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String va = req.getParameter("valor");
		//System.out.println("Esto es va: "+va);
		//if(va==null){va=}
		MetodoCuentas mc= new MetodoCuentas();	
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out=res.getWriter();

		String fi=req.getParameter("fi");
		String ff=req.getParameter("ff");
		String fsl=req.getParameter("fsl");
		String datos=req.getParameter("datos");
		
		String hora=req.getParameter("hora");
		String fecha=req.getParameter("fecha");
		
		String NomPac = req.getParameter("NomPac");
		
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		ResultSet rs3=null;
		ResultSet rs4=null;	
		ResultSet rs5=null;
		ResultSet rs6=null;
		ResultSet rs7=null;
		ResultSet rs8=null;
		
		MetodoRips mr=new MetodoRips();
		
		java.util.Date fechaS = new Date();
		Calendar c = new GregorianCalendar();
		String dia = Integer.toString(c.get(Calendar.DATE));
		String mes = Integer.toString(c.get(Calendar.MONTH));
		String annio = Integer.toString(c.get(Calendar.YEAR));
		int m=Integer.parseInt(mes)+1;
		mes=String.valueOf(m);
		int d=Integer.parseInt(dia);
		if(d<10){dia="0"+d;}
		if(m<10){mes="0"+m;}
		
		String fechacjmysql=annio+"-"+mes+"-"+dia;
		String fechacj=dia+"/"+mes+"/"+annio;
		
		
		Calendar calendario = Calendar.getInstance();
		//	Calendar calendario = new GregorianCalendar();
			int h, minutos, segundos;
			h =calendario.get(Calendar.HOUR_OF_DAY);
			minutos = calendario.get(Calendar.MINUTE);
			segundos = calendario.get(Calendar.SECOND);
			String hra= h+":"+minutos+":"+segundos;
		
		//System.out.print(va);
		
	if(va.equals("1")){			
		//out.print("<table width='100%' class='style6'  border='0'><tr><input type='file' name='fichero' id='fichero'></tr></table>");
			out.print("<table width='100%' class='style6'  border='0'><tr><td colspan='6'><div align='center' class='style11' id='cabecera2'>Generar Archivos RIPS</div></td></tr>");
			
			out.print("<tr><td >Entidad:</td><td ><label><select name='cmbE' id='cmbE' style='width:380px'><option value='Seleccione'>Seleccione</option>");
			rs=mr.Empresas();
			try {
				while(rs.next()){
				out.print("<option title='"+rs.getString(1)+"' value="+rs.getString(2)+">"+rs.getString(1)+"</option>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("</select></label></td>");
			out.print("<td>Unidad de destino: </td><td><label><select name='ruta' id='ruta' style='width:300px'><option value='c'>C</option><option value='d'>D</option><option value='e'>E</option>");
			out.print("</select></label></td><td>Tipo Rips</td><td><select id='cmbTipoRips' onchange='CambTipoRips()'><option value='Seleccione'>Seleccione</option><option value='CuentaC'>Cuenta de Cobro</option><option value='BusGen'>Busqueda General</option></select></td></tr></table>");
			//out.print("<td width='5%'></td><td width='10%'>Ruta destino: </td><td width='20%'><label><div id=ruta><input name='txtNit' type='text' id='txtNit' size='40' readonly='readonly'/></div></label></td><td width='5%'><input name='btnCrearConvenio' type='button' id='btnCrearConvenio' value='Seleccione' onclick='Ruta()' /></td><tr></table>");
			
			out.print("<table width='100%' border='0' ><tr><td id='RipsCam'></td></tr></table>");
			
			out.print("<table width='100%' class='style6'  border='0'><tr><td colspan='6'><div align='center' class='style11' id='cabecera2'>Facturas</div></td></tr>");
			
			
			out.print("<table width='100%' class='style6' border='0'><tr><td>Facturas Disponibles</td><td whidth='10%'></td><td width='5%'><input name='btnCrearConvenio' type='button' id='btnCrearConvenio' value='>>' onclick='MoverS()' /></td><td>Facturas Seleccionadas</td><td></td></tr>");
			out.print("<tr><td colspan='2'><label><div id='DFD'><select name='FD' multiple  id='FD' style='width:380px; height: 400px'></div></label></td><div align='right'><td><input name='btnCrearConvenio' type='button' id='btnCrearConvenio' value='<<' onclick='MoverD()' /></td></div><td colspan='2'><label><div id='DFS'><select name='FS' multiple id='FS' style='width:380px; height: 400px'></div></label></td></tr>");
	
			out.print("<table width='100%' class='style6'  border='0'><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr><td width='5%'><div align='center'><input name='btnCrearConvenio' type='button' id='btnCrearConvenio' value='Exportar RIPS' onclick='Exportar()' /></div></td></tr></table>");
			
			/*	*/	//out.print("<tr><td colspan='4'><div align='center'><input name='btnCrearEntidad' type='button' id='btnCrearEntidad' value='     Exportar RIPS     ' onclick='CrearEntidad()' /></div></td></tr></table>");
	
	}
		
		
		if(va.equals("2")){	
		
		/*	File f = null;
			 JFileChooser selector = new JFileChooser(".");
			 selector.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
			 try
			 {
				 int value = selector.showOpenDialog(selector.getParent());
			   //int value = selector.showOpenDialog(null);
			   if( value == JFileChooser.APPROVE_OPTION)
			   {
			     f = selector.getSelectedFile();
			     //System.out.print("FFFF: "+f);
			     //String cj="/";
			     String fff=f+"/cesar.txt";
			     out.print("<input name='txtNit' type='text' id='txtNit' size='40' value='"+f+"' readonly='readonly'/>");
			   }
			   }
			 catch(Exception e)
			 {
			   e.printStackTrace();
			 }		*/
			
			
		} 
		
		
		if(va.equals("3")){	
			String eps=req.getParameter("eps");
			
			
			//System.out.print("irleth: "+eps+"  "+fi+"  "+ff);
			
			String a1=fi.substring(0, 2);
			String m1=fi.substring(3, 5);
			String d1=fi.substring(6, 10);
			String fin=d1+"-"+m1+"-"+a1;
						
			String a2=ff.substring(0, 2);
			String m2=ff.substring(3, 5);
			String d2=ff.substring(6, 10);
			String ffn=d2+"-"+m2+"-"+a2;
			//System.out.print("cesar: "+eps+"  "+fin+"  "+ffn);
			
			out.print("<select name='FD' multiple  id='FD' style='width:380px;  height: 400px'>");
		rs=mr.Facturas(eps,fin,ffn);
		try {
			while(rs.next()){
		//	out.print("<option title='"+rs.getString(1)+"' value="+rs.getString(2)+">"+rs.getString(1)+"</option>");
			out.print("<option value='"+rs.getString(1)+"'>"+rs.getString(2)+"</option>");
			}
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		out.print("</select>");
		}
		
		if(va.equals("C3")){	
			//String eps=req.getParameter("eps");
			String NumCtaCobro=req.getParameter("NumCtaCobro");
			
			String NumCTA=NumCtaCobro.substring(6,NumCtaCobro.length());
			out.print("<select name='FD' multiple  id='FD' style='width:380px;  height: 400px'>");
			rs=mr.FacturasNumCta(NumCTA);
			try {
				while(rs.next()){
					out.print("<option value='"+rs.getString(1)+"'>"+rs.getString(2)+"</option>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("</select>");
		}
		
		
		
		if(va.equals("4")){						
			out.print("<select name='FS' multiple  id='FS' style='width:380px;  height: 400px'>");
			out.print("<option value='"+fi+"'>"+ff+"</option>");
		}
		
		
		
		if(va.equals("5")){	
			String AR=req.getParameter("AR");
			System.out.println("AR: "+AR);
			String rut=req.getParameter("rut");
			String nom=req.getParameter("nom");
			System.out.println("rut: "+rut);
			System.out.println("nom: "+nom);
			
			
			 int cah=0;
		 	 int cam=0;
		 	 int can=0;
		 	 int cap=0;
		 	 int cat=0;
		 	 int cau=0;
		 	 int cus=0;
		  	 int cac=0;
		  	 int cad=0;
		  	 int caf=0;
		 	
		  	/*String dias=fi.substring(0, 2);
			String meses=fi.substring(3, 5);
			String anos=fi.substring(6, 10);
			fi=anos+"-"+meses+"-"+dias;
			System.out.println("fi: "+fi);
			
			String diasf=ff.substring(0, 2);
			String mesesf=ff.substring(3, 5);
			String anosf=ff.substring(6, 10);
			ff=anosf+"-"+mesesf+"-"+diasf;
			System.out.println("fi: "+fi);*/
			 
			//Numero de facturas y las facturas
			String V[] = new String[200];
			int i1=0;
			 StringTokenizer elementos;  
			 System.out.println("datos: "+datos);
			   elementos = new StringTokenizer(datos,"|"); 
			   
			   System.out.println("elementos: "+elementos);
			   while(elementos.hasMoreTokens()){ 
				   V[i1] = elementos.nextToken();  
				   System.out.println("V[i1]: "+V[i1]);
				   i1++;
			   }
					   
			   System.out.println("Antes del JFileChooser::::::::::::::::::::::::::: ");
				
			// JFileChooser selector = new JFileChooser(".");
			  System.out.println("Luego  del JFileChooser: ");
				
			// try
			// {
			 	 String f=rut+"\\";
			 	 
			 	 System.out.println("Asignamos a f la ruta: "+f);
					
			 	 
			 	rs=mr.Prestador();
			 	String prestador="";
			 	 System.out.println("Conseguimos el codigo del prestador: ");
					
				try {
					while(rs.next()){
						prestador=rs.getString(1);
						System.out.println("prestador: "+prestador);
						
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				
				String Tdig=null;
				
				
				/***ARCHIVO CONSULTA CON PROBLEMAS EN EL CICLO **/	
				/*** Archivo de Consultas***/	
			/*	
				if(AR.equals("1")){		
				for(int i=0; i<i1; i++){//for de la cantidad de facturas
					rs2=mr.DAC(V[i]);
					if(rs2.next()){
						Tdig=rs2.getString(1);
						rs=mr.AC1(fi,ff,V[i],Tdig);
				  try {
						 while(rs.next()){
							 if(cac>0){out.print("\r\n");}
							 String ce=rs.getString(9);
							 String ce2=rs.getString(8);
							 String cen="0";
							 if (ce.length()<2){ce=cen+ce;}
							 if (ce2.length()<2){ce2=cen+ce2;}
							// outputAC.println(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6)+","+rs.getString(7)+","+rs.getString(8)+","+rs.getString(9)+","+rs.getString(10)+","+rs.getString(11)+","+rs.getString(12)+","+rs.getString(13)+","+rs.getString(14)+","+rs.getString(15)+","+rs.getString(16)+","+rs.getString(17));
							if(rs.getString(18).equals("1")){
							 out.print(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6)+","+rs.getString(7)+","+ce2+","+ce+","+rs.getString(10)+","+rs.getString(11)+","+rs.getString(12)+","+rs.getString(13)+","+rs.getString(14)+","+rs.getString(15)+","+rs.getString(16)+","+rs.getString(17));
							 cac++;
							}else{
								long cant=rs.getInt(18);
								long total=rs.getLong(17);
								for(i=1;i<=cant;i++){
								
								out.print(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6)+","+rs.getString(7)+","+ce2+","+ce+","+rs.getString(10)+","+rs.getString(11)+","+rs.getString(12)+","+rs.getString(13)+","+rs.getString(14)+","+rs.getString(15)+","+rs.getString(16)+","+(total/cant));
								//out.print("<br>"); 
								System.out.println("cant"+cant);
								System.out.println("valor de i"+i);
								if(((cant-i))!=0){
								out.print("\r\n");
								}
								cac++;
								}
							}
							
						}
						rs.getStatement().getConnection().close();
					 } catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					 }
					
					}else{
					
					rs=mr.AC(fi,ff,V[i]);
				  try {
						 while(rs.next()){
							 if(cac>0){out.print("\r\n");}
							 String ce=rs.getString(9);
							 String ce2=rs.getString(8);
							 String cen="0";
							 if (ce.length()<2){ce=cen+ce;}
							 if (ce2.length()<2){ce2=cen+ce2;}
							// outputAC.println(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6)+","+rs.getString(7)+","+rs.getString(8)+","+rs.getString(9)+","+rs.getString(10)+","+rs.getString(11)+","+rs.getString(12)+","+rs.getString(13)+","+rs.getString(14)+","+rs.getString(15)+","+rs.getString(16)+","+rs.getString(17));
							 if(rs.getString(18).equals("1")){
							 out.print(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6)+","+rs.getString(7)+","+ce2+","+ce+","+rs.getString(10)+","+rs.getString(11)+","+rs.getString(12)+","+rs.getString(13)+","+rs.getString(14)+","+rs.getString(15)+","+rs.getString(16)+","+rs.getString(17));
							 cac++;
							 }else{
									long cant=rs.getInt(18);
									long total=rs.getLong(17);
									for(i=1;i<=cant;i++){
									out.print(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6)+","+rs.getString(7)+","+ce2+","+ce+","+rs.getString(10)+","+rs.getString(11)+","+rs.getString(12)+","+rs.getString(13)+","+rs.getString(14)+","+rs.getString(15)+","+rs.getString(16)+","+(total/cant));
									System.out.println("cant"+cant);
									System.out.println("valor de i"+i);
									if(((cant-i))!=0){
									out.print("\r\n");
									}
									cac++;
									}
							 }
						 }
						rs.getStatement().getConnection().close();
					 } catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					 }

					}	
				 }
				}
				*/
				
				
				/*** Archivo de Consultas FUNCIONA ***/	
			
				System.out.println("AARARARRARARARAARARAR "+AR);
				
				if(AR.equals("1")){	
					System.out.println("llegamos al ac i1 "+i1);
				for(int i=0; i<i1; i++){//for de la cantidad de facturas
					System.out.println("llegamos al ac v[i] "+V[i]);
					
					rs2=mr.DAC(V[i]);
				 try {
				
					 if(rs2.next()){
						Tdig=rs2.getString(1);
						System.out.println("llegamos al ac"+V[i]+" no se q es esto "+Tdig);
						
						rs=mr.AC1(fi,ff,V[i],Tdig);
						System.out.println("Se entra al metodo ACl ");
						
				 // try {
						 while(rs.next()){
							 if(cac>0){out.print("\r\n");}
							 String ce=rs.getString(9);
							 String ce2=rs.getString(8);
							 String cen="0";
							 if (ce.length()<2){ce=cen+ce;}
							 if (ce2.length()<2){ce2=cen+ce2;}
							// outputAC.println(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6)+","+rs.getString(7)+","+rs.getString(8)+","+rs.getString(9)+","+rs.getString(10)+","+rs.getString(11)+","+rs.getString(12)+","+rs.getString(13)+","+rs.getString(14)+","+rs.getString(15)+","+rs.getString(16)+","+rs.getString(17));
							//EL Q ESTABA out.print(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6)+","+rs.getString(7)+","+ce2+","+ce+","+rs.getString(10)+","+rs.getString(11)+","+rs.getString(12)+","+rs.getString(13)+","+rs.getString(14)+","+rs.getString(15)+","+rs.getString(16)+","+rs.getString(17));
							
							 if(rs.getInt(18)>1){
								 int vari=0;
								 	do{
								 		if(vari>0){ out.print("\r\n");}
									 out.print(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6)+","+rs.getString(7)+","+ce2+","+ce+","+rs.getString(10)+","+rs.getString(11)+","+rs.getString(12)+","+rs.getString(13)+","+rs.getString(14)+","+rs.getString(15)+","+rs.getString(16)+","+rs.getString(15));
									 vari=vari+1;
								 	}while(vari<rs.getInt(18));
								}else{
							  out.print(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6)+","+rs.getString(7)+","+ce2+","+ce+","+rs.getString(10)+","+rs.getString(11)+","+rs.getString(12)+","+rs.getString(13)+","+rs.getString(14)+","+rs.getString(15)+","+rs.getString(16)+","+rs.getString(17));	 
							 }

							System.out.println("Esto trajo la consulta AC1 "+rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6)+","+rs.getString(7)+","+ce2+","+ce+","+rs.getString(10)+","+rs.getString(11)+","+rs.getString(12)+","+rs.getString(13)+","+rs.getString(14)+","+rs.getString(15)+","+rs.getString(16)+","+rs.getString(17));
							cac++;
						}
						rs.getStatement().getConnection().close();
					// } catch (SQLException e) {
					///	out.print("Error "+e);
					//	e.printStackTrace();
					// }
					 System.out.println("AC1 dio  "+cac);
						
					}else{
					
					rs=mr.AC(fi,ff,V[i]);
					System.out.println("Se entra al metodo AC solo ");
					
				//  try {
						 while(rs.next()){
							 if(cac>0){out.print("\r\n");}
							 String ce=rs.getString(9);
							 String ce2=rs.getString(8);
							 String cen="0";
							 if (ce.length()<2){ce=cen+ce;}
							 if (ce2.length()<2){ce2=cen+ce2;}
							// outputAC.println(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6)+","+rs.getString(7)+","+rs.getString(8)+","+rs.getString(9)+","+rs.getString(10)+","+rs.getString(11)+","+rs.getString(12)+","+rs.getString(13)+","+rs.getString(14)+","+rs.getString(15)+","+rs.getString(16)+","+rs.getString(17));
							//EL QUE ESTABA out.print(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6)+","+rs.getString(7)+","+ce2+","+ce+","+rs.getString(10)+","+rs.getString(11)+","+rs.getString(12)+","+rs.getString(13)+","+rs.getString(14)+","+rs.getString(15)+","+rs.getString(16)+","+rs.getString(17));
							
							 if(rs.getInt(18)>1){
								 	int vari=0;
								 	do{
								 		if(vari>0){ out.print("\r\n");}
									 out.print(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6)+","+rs.getString(7)+","+ce2+","+ce+","+rs.getString(10)+","+rs.getString(11)+","+rs.getString(12)+","+rs.getString(13)+","+rs.getString(14)+","+rs.getString(15)+","+rs.getString(16)+","+rs.getString(15));
									 vari=vari+1;
								 	}while(vari<rs.getInt(18));
								}else{
							  out.print(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6)+","+rs.getString(7)+","+ce2+","+ce+","+rs.getString(10)+","+rs.getString(11)+","+rs.getString(12)+","+rs.getString(13)+","+rs.getString(14)+","+rs.getString(15)+","+rs.getString(16)+","+rs.getString(17));	 
							 }
							
							System.out.println("Esto trajo la consulta AC "+rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6)+","+rs.getString(7)+","+ce2+","+ce+","+rs.getString(10)+","+rs.getString(11)+","+rs.getString(12)+","+rs.getString(13)+","+rs.getString(14)+","+rs.getString(15)+","+rs.getString(16)+","+rs.getString(17));
							cac++;
						}
						rs.getStatement().getConnection().close();
				//	 } catch (SQLException e) {
				//		out.print("Error "+e);
				//		e.printStackTrace();
				//	 }

					}System.out.println("AC dio  "+cac);
					
				 } catch (SQLException e) {
						out.print("Error CESARJULIO "+e);
						e.printStackTrace();
					 }
				
				}
				}
				
				
				
				/*** Archivo de Descripcion Agrupada***/
			 	 
			 	/*** Archivo de Transacciones***/
				if(AR.equals("3")){	
					System.out.println("ENTRAMOS A TRANSACCIONES AR= "+AR);
				for(int i=0; i<i1; i++){//for de la cantidad de facturas	
						 rs=mr.AF(fi,ff,V[i]);
					  try {
							 while(rs.next()){
								 if(caf>0){out.print("\r\n");}
								out.print(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6)+","+rs.getString(7)+","+rs.getString(8)+","+rs.getString(9)+","+rs.getString(10)+","+rs.getString(11)+","+rs.getString(12)+","+rs.getString(13)+","+rs.getString(14)+","+rs.getString(15)+","+rs.getString(16)+","+rs.getString(17));
								System.out.println("Esto trajo la consulta Transacciones "+rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6)+","+rs.getString(7)+","+rs.getString(8)+","+rs.getString(9)+","+rs.getString(10)+","+rs.getString(11)+","+rs.getString(12)+","+rs.getString(13)+","+rs.getString(14)+","+rs.getString(15)+","+rs.getString(16)+","+rs.getString(17));
								
								caf++;
							}
							rs.getStatement().getConnection().close();
						 } catch (SQLException e) {
							out.print("Error "+e);
							e.printStackTrace();
						 }
						}
					}
				
				
				/*** Archivo de Medicamentos***/
				if(AR.equals("4")){
					System.out.println("ENTRAMOS A MEDICAMENTOS AR= "+AR);
					
				for(int i=0; i<i1; i++){//for de la cantidad de facturas	
					rs=mr.AM(fi,ff,V[i]);
					  try {
							 while(rs.next()){
								 if(cam>0){out.print("\r\n");}
								out.print(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6)+","+rs.getString(7)+","+rs.getString(8)+","+rs.getString(9)+","+rs.getString(10)+","+rs.getString(11)+","+rs.getString(12)+","+rs.getString(13)+","+rs.getString(14));
								//System.out.println(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6)+","+rs.getString(7)+","+rs.getString(8)+","+rs.getString(9)+","+rs.getString(10)+","+rs.getString(11)+","+rs.getString(12)+","+rs.getString(13)+","+rs.getString(14));
								System.out.println("Esto trajo la consulta Transacciones "+rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6)+","+rs.getString(7)+","+rs.getString(8)+","+rs.getString(9)+","+rs.getString(10)+","+rs.getString(11)+","+rs.getString(12)+","+rs.getString(13)+","+rs.getString(14));
								cam++;
							}
							rs.getStatement().getConnection().close();
						 } catch (SQLException e) {
							out.print("Error "+e);
							e.printStackTrace();
						 }
						}
					}
				
				/*** Archivo de Hospitalizacion***/
				if(AR.equals("5")){		
					System.out.println("ENTRAMOS A HOSPITALIZACION AR= "+AR);
					
				for(int i=0; i<i1; i++){//for de la cantidad de facturas	
					rs=mr.AH(fi,ff,V[i]);
					  try {
							 while(rs.next()){
								 
								 String[] diagEgreso = rs.getString(11).split("_");														
									String de1="",de2="",de3="",de4="";
									if(diagEgreso.length==1){
										de1=diagEgreso[0];
									}else{
										if(diagEgreso.length==2){
											de1=diagEgreso[0];
											de2=diagEgreso[1];
										}else{
											if(diagEgreso.length==3){
												de1=diagEgreso[0];
												de2=diagEgreso[1];
												de3=diagEgreso[2];
											}else{
												if(diagEgreso.length==4){
													de1=diagEgreso[0];
													de2=diagEgreso[1];
													de3=diagEgreso[2];
													de4=diagEgreso[3];
												}
											}
										}
									}
									//System.out.println("Aqui vamos");
									 if(cah>0){out.print("\r\n");}
									out.print(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6)+","+rs.getString(7)+","+rs.getString(8)+","+rs.getString(9)+","+rs.getString(10)+","+de1+","+de2+","+de3+","+de4+","+rs.getString(12)+","+rs.getString(13)+","+rs.getString(14)+","+rs.getString(15)+","+rs.getString(16));
									System.out.println("Esto trajo la consulta HOSPITAIZAAS "+rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6)+","+rs.getString(7)+","+rs.getString(8)+","+rs.getString(9)+","+rs.getString(10)+","+de1+","+de2+","+de3+","+de4+","+rs.getString(12)+","+rs.getString(13)+","+rs.getString(14)+","+rs.getString(15)+","+rs.getString(16));
									cah++;
							}
							rs.getStatement().getConnection().close();
						 } catch (SQLException e) {
							out.print("Error "+e);
							e.printStackTrace();
						 }
						}
					}
				
	
				/*** Archivo de Recien Nacidos***/
				if(AR.equals("6")){
					System.out.println("ENTRAMOS A RECIEN NACIDOS AR= "+AR);
					
					//System.out.println("Aqui vamos6666");
				for(int i=0; i<i1; i++){//for de la cantidad de facturas	
					rs=mr.AN(fi,ff,V[i]);
					  try {
							 while(rs.next()){
								if(can>0){out.print("\r\n");}
								out.print(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6)+","+rs.getString(7)+","+rs.getString(8)+","+rs.getString(9)+","+rs.getString(10)+","+rs.getString(11)+","+rs.getString(12)+","+rs.getString(13)+","+rs.getString(14));
								System.out.println("Esto trajo la consulta HOSPITAIZAAS "+rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6)+","+rs.getString(7)+","+rs.getString(8)+","+rs.getString(9)+","+rs.getString(10)+","+rs.getString(11)+","+rs.getString(12)+","+rs.getString(13)+","+rs.getString(14));
								
								can++;
							}
							rs.getStatement().getConnection().close();
						 } catch (SQLException e) {
							out.print("Error "+e);
							e.printStackTrace();
						 }
						}
					}
			 	
				 

				/*** Archivo de Procedimientos***/
				if(AR.equals("7")){
					System.out.println("ENTRAMOS A PROCEDIMIENTIOS AR= "+AR);
					
					//System.out.println("Aqui vamos777777");
					for(int i=0; i<i1; i++){//for de la cantidad de facturas
					rs2=mr.DAP(V[i]);
					System.out.println("ENTRAMOS DAP AR= "+AR);
					
					try{
						if(rs2.next()){
							Tdig=rs2.getString(1);
							rs=mr.AP(fi,ff,V[i],Tdig);
							try {
								while(rs.next()){
									if(cap>0){out.print("\r\n");}
									out.print(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6)+","+rs.getString(7)+","+rs.getString(8)+","+rs.getString(9)+","+rs.getString(10)+","+rs.getString(11)+","+rs.getString(12)+","+rs.getString(13)+","+rs.getString(14)+","+rs.getString(15));
									System.out.println("Esto trajo la consulta AP1 "+rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6)+","+rs.getString(7)+","+rs.getString(8)+","+rs.getString(9)+","+rs.getString(10)+","+rs.getString(11)+","+rs.getString(12)+","+rs.getString(13)+","+rs.getString(14)+","+rs.getString(15));
									
									cap++;
								}
								rs.getStatement().getConnection().close();
								
								rs1=mr.AP2(fi,ff,V[i]);
								while(rs1.next()){
									if(cap>0){out.print("\r\n");}
									out.print(rs1.getString(1)+","+rs1.getString(2)+","+rs1.getString(3)+","+rs1.getString(4)+","+rs1.getString(5)+","+rs1.getString(6)+","+rs1.getString(7)+","+rs1.getString(8)+","+rs1.getString(9)+","+rs1.getString(10)+","+rs1.getString(11)+","+rs1.getString(12)+","+rs1.getString(13)+","+rs1.getString(14)+","+rs1.getString(15));
									System.out.println("Esto trajo la consulta AP2 "+rs1.getString(1)+","+rs1.getString(2)+","+rs1.getString(3)+","+rs1.getString(4)+","+rs1.getString(5)+","+rs1.getString(6)+","+rs1.getString(7)+","+rs1.getString(8)+","+rs1.getString(9)+","+rs1.getString(10)+","+rs1.getString(11)+","+rs1.getString(12)+","+rs1.getString(13)+","+rs1.getString(14)+","+rs1.getString(15));
									
									cap++;
								}
								
								rs1.getStatement().getConnection().close();
							} catch (SQLException e) {
								out.print("Error "+e);
								e.printStackTrace();
							}
						}
						else{	
							rs=mr.AP1(fi,ff,V[i]);	
							System.out.println("ENTRAMOS A AP1 AR= "+AR);
							
							try {
								while(rs.next()){
									if(cap>0){out.print("\r\n");}
									out.print(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6)+","+rs.getString(7)+","+rs.getString(8)+","+rs.getString(9)+","+rs.getString(10)+","+rs.getString(11)+","+rs.getString(12)+","+rs.getString(13)+","+rs.getString(14)+","+rs.getString(15));
									System.out.println("Esto trajo la consulta AP1 "+rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6)+","+rs.getString(7)+","+rs.getString(8)+","+rs.getString(9)+","+rs.getString(10)+","+rs.getString(11)+","+rs.getString(12)+","+rs.getString(13)+","+rs.getString(14)+","+rs.getString(15));
									
									cap++;
								}
								rs.getStatement().getConnection().close();
							 
								rs1=mr.AP2(fi,ff,V[i]);
								while(rs1.next()){
									if(cap>0){out.print("\r\n");}
									out.print(rs1.getString(1)+","+rs1.getString(2)+","+rs1.getString(3)+","+rs1.getString(4)+","+rs1.getString(5)+","+rs1.getString(6)+","+rs1.getString(7)+","+rs1.getString(8)+","+rs1.getString(9)+","+rs1.getString(10)+","+rs1.getString(11)+","+rs1.getString(12)+","+rs1.getString(13)+","+rs1.getString(14)+","+rs1.getString(15));
									System.out.println("Esto trajo la consulta AP2 "+rs1.getString(1)+","+rs1.getString(2)+","+rs1.getString(3)+","+rs1.getString(4)+","+rs1.getString(5)+","+rs1.getString(6)+","+rs1.getString(7)+","+rs1.getString(8)+","+rs1.getString(9)+","+rs1.getString(10)+","+rs1.getString(11)+","+rs1.getString(12)+","+rs1.getString(13)+","+rs1.getString(14)+","+rs1.getString(15));
									cap++;
								}
								
								rs1.getStatement().getConnection().close();
							} catch (SQLException e) {
								out.print("Error "+e);
								e.printStackTrace();
							}
						}
					 } catch (SQLException e) {
							out.print("Error CESARJULIO2 "+e);
							e.printStackTrace();
						 }
					}
				}
			 	
				
				/*** Archivo de Otros Servicios***/	
				if(AR.equals("8")){	
					System.out.println("ENTRAMOS A AT OTROS SERV AR= "+AR);
					
					//System.out.println("Aqui vamos88888888");
					for(int i=0; i<i1; i++){//for de la cantidad de facturas	
					rs=mr.AT(fi,ff,V[i]);
					  try {
							 while(rs.next()){
								 if(cat>0){out.print("\r\n");}
								 System.out.println("AT");
								 out.print(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+",1,"+rs.getString(7)+","+rs.getString(8)+","+rs.getString(9)+","+rs.getString(10)+","+rs.getString(11));
								 //donde va el uno que indica el tipo de servicio iba antes +rs.getString(6)+
								 cat++;
								 System.out.println("Esto trajo la consulta OTROS SERVICIOS "+rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+",1,"+rs.getString(7)+","+rs.getString(8)+","+rs.getString(9)+","+rs.getString(10)+","+rs.getString(11));
							}
							rs.getStatement().getConnection().close();
						 } catch (SQLException e) {
							//out.print("Error "+e);
							e.printStackTrace();
						 }
						}
					}
				
				
				/*** Archivo de Urgencias***/
				if(AR.equals("9")){	//System.out.println("Aqui vamos9999999999");
					System.out.println("ENTRAMOS A URGENCIAS AR= "+AR);
					
				for(int i=0; i<i1; i++){//for de la cantidad de facturas	
					rs=mr.AU(fi,ff,V[i]);
					  try {
							 while(rs.next()){
								 if(cau>0){out.print("\r\n");}
								 System.out.println("AU");
								 out.print(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6)+","+rs.getString(7)+","+rs.getString(8)+","+rs.getString(9)+","+rs.getString(10)+","+rs.getString(11)+","+rs.getString(12)+","+rs.getString(13)+","+rs.getString(14)+","+rs.getString(15)+","+rs.getString(16)+","+rs.getString(17));	
								 System.out.println("Esto trajo la consulta URGENCIAS "+rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6)+","+rs.getString(7)+","+rs.getString(8)+","+rs.getString(9)+","+rs.getString(10)+","+rs.getString(11)+","+rs.getString(12)+","+rs.getString(13)+","+rs.getString(14)+","+rs.getString(15)+","+rs.getString(16)+","+rs.getString(17));
								 cau++;
							}
							rs.getStatement().getConnection().close();
						 } catch (SQLException e) {
							out.println("Error "+e);
							e.printStackTrace();
						 }
						}
					}
			

				/*** Archivo de Usuarios***/
			if(AR.equals("10")){//System.out.println("Aqui vamos100000");	
					int icj=0;
					String CJ[] = new String[1000];
				for(int i=0; i<i1; i++){//for de la cantidad de facturas
					String CodEnt=req.getParameter("CodEnt");
					 rs=mr.US(fi,ff,CodEnt,V[i]);
					  try {
							 while(rs.next()){
								 String[] pnombre = rs.getString(7).split(" ");
									String[] edad = rs.getString(8).split("-");
									
									int swp=0;
									String cons=rs.getString(1)+""+rs.getString(2);
									int lon=CJ.length;
									
									for(int z=0;z<lon;z++){
										if(cons.equals(CJ[z])){
											swp=1;
										}
									}
																
									if(swp==0){									
									CJ[icj]= rs.getString(1)+""+rs.getString(2);
									icj++;
																		
									 if(cus>0){out.print("\r\n");}
									if(pnombre.length>1){
										 System.out.println("US1");
										 out.print(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6)+","+pnombre[0]+","+pnombre[1]+","+edad[0]+","+edad[1]+","+rs.getString(9)+",");
										 if(rs.getString(10).equals("8")){
											 out.print("0"+rs.getString(10)+",");
											 if((rs.getString(11)).equals("1")){
												 out.print("00"+rs.getString(11)+",");
											 }else{
												 out.print(rs.getString(11)+",");
											 }
										 }else{
											 out.print(rs.getString(10)+","+rs.getString(11)+",");
										 }
										 if((rs.getString(12)).equals("")){
											 out.print("U");
										 }else{
											 out.print(rs.getString(12));
										 }
									}else{
										 System.out.println("US2");
										 out.print(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6)+","+pnombre[0]+",,"+edad[0]+","+edad[1]+","+rs.getString(9)+",");
										 	if(rs.getString(10).equals("8")){
												 out.print("0"+rs.getString(10)+",");
												 if((rs.getString(11)).equals("1")){
													 out.print("00"+rs.getString(11)+",");
												 }else{
													 out.print(rs.getString(11)+",");
												 }
										 	}else{
										 		out.print(rs.getString(10)+","+rs.getString(11)+",");
										 	}
										 	if((rs.getString(12)).equals("")){
										 		out.print("U");
										 	}else{
										 		out.print(rs.getString(12));
										 	}
									}
									cus++;
									}//fin swp
							}
							rs.getStatement().getConnection().close();
						 } catch (SQLException e) {
							out.print("Error "+e);
							e.printStackTrace();
						 }
						}
					}
				
			 	
				/*** Archivo de Control***/
				if(AR.equals("11")){//System.out.println("Aqui vamos100000");	
					 System.out.println("CT");
					 out.print(prestador+","+fechacj+",");
				}
			
			 	 
			 	 
			
				 
			     /*** Archivo de Control***/
			/* 	 f=f+"CT"+nom+".txt";
			 	 PrintWriter output= new PrintWriter(new FileWriter(f));
			 	 if(cac!=0)
			 	 output.println(prestador+","+fechacj+",AC"+nom+","+cac);
			 	 if(cad!=0)
			 	 output.println(prestador+","+fechacj+",AD"+nom+","+cad);
			 	 if(caf!=0)
			 	 output.println(prestador+","+fechacj+",AF"+nom+","+caf);
			 	 if(cah!=0)
			 	 output.println(prestador+","+fechacj+",AH"+nom+","+cah);
			 	 if(cam!=0)
			 	 output.println(prestador+","+fechacj+",AM"+nom+","+cam);
			 	 if(can!=0)
			 	 output.println(prestador+","+fechacj+",AN"+nom+","+can);
			 	 if(cap!=0)
			 	 output.println(prestador+","+fechacj+",AP"+nom+","+cap);
			 	 if(cat!=0)
			 	 output.println(prestador+","+fechacj+",AT"+nom+","+cat);
			 	 if(cau!=0)
			 	 output.println(prestador+","+fechacj+",AU"+nom+","+cau);
			 	 if(cus!=0)
			 	 output.println(prestador+","+fechacj+",US"+nom+","+cus);	
			 	 output.close();
			 	 
			*/ 	
			 	/*** FIN ARCHIVOS RIPS ***/ 
			
			// }
		//	 catch(Exception e)
		//	 {
		//	   e.printStackTrace();
		//	 }			
		}
		
		
		


		
		
		
		
		/**************************RIPS AGRUPADOS*******************************/
		
		if(va.equals("1A")){			
			//out.print("<table width='100%' class='style6'  border='0'><tr><input type='file' name='fichero' id='fichero'></tr></table>");
				out.print("<table width='100%' class='style6'  border='0'><tr><td colspan='6'><div align='center' class='style11' id='cabecera2'>Generar Archivos RIPS de facturas Agrupadas</div></td></tr>");
				
				out.print("<tr><td width='10%'>Entidad:</td><td width='40%'><label><select name='cmbE' id='cmbE' style='width:380px'><option value='Seleccione'>Seleccione</option>");
				rs=mr.Empresas();
				try {
					while(rs.next()){
					out.print("<option title='"+rs.getString(1)+"' value="+rs.getString(2)+">"+rs.getString(1)+"</option>");
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				out.print("</select></label></td>");
				out.print("<td width='10%'>Unidad de destino: </td><td width='40%'><label><select name='ruta' id='ruta' style='width:380px'><option value='c'>C</option><option value='d'>D</option><option value='e'>E</option>");
				out.print("</select></label></td>");
				//out.print("<td width='5%'></td><td width='10%'>Ruta destino: </td><td width='20%'><label><div id=ruta><input name='txtNit' type='text' id='txtNit' size='40' readonly='readonly'/></div></label></td><td width='5%'><input name='btnCrearConvenio' type='button' id='btnCrearConvenio' value='Seleccione' onclick='Ruta()' /></td><tr></table>");
				
				out.print("<table width='100%' class='style6'  border='0'><tr><td width='7%'>Fecha Inicial:</td><td width='15%'><label><input name='FI' type='text' id='FI' size='10'  onKeyup='masca(this,patron,true,0,0,0)' /></label></td><td width='7%'>Fecha Final:</td><td width='15%' ><label><input name='FF' type='text' id='FF' size='10' onKeyup='masca(this,patron,true,0,0,0)'  onBlur='BuscarFA()' /></label></td><td width='5%'>Nombre:</td><td width='15%'><label><input name='txtNombre' type='text' id='txtNombre' size='10'  onKeyup='max()' /></label></td><td width='5%'><input name='btnCrearConvenio' type='button' id='btnCrearConvenio' value='Consultar' onclick='BuscarFA()' /></td></tr></table>");
				
				out.print("<table width='100%' class='style6'  border='0'><tr><td colspan='6'><div align='center' class='style11' id='cabecera2'>Facturas</div></td></tr>");
				
				
				out.print("<table width='100%' class='style6' border='0'><tr><td>Facturas Disponibles</td><td whidth='10%'></td><td width='5%'><input name='btnCrearConvenio' type='button' id='btnCrearConvenio' value='>>' onclick='MoverS()' /></td><td>Facturas Seleccionadas</td><td></td></tr>");
				out.print("<tr><td colspan='2'><label><div id='DFD'><select name='FD' multiple  id='FD' style='width:380px; height: 400px'></div></label></td><div align='right'><td><input name='btnCrearConvenio' type='button' id='btnCrearConvenio' value='<<' onclick='MoverD()' /></td></div><td colspan='2'><label><div id='DFS'><select name='FS' multiple id='FS' style='width:380px; height: 400px'></div></label></td></tr>");
		
				out.print("<table width='100%' class='style6'  border='0'><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr><td width='5%'><div align='center'><input name='btnCrearConvenio' type='button' id='btnCrearConvenio' value='Exportar RIPS' onclick='ExportarA()' /></div></td></tr></table>");
				
				/*	*/	//out.print("<tr><td colspan='4'><div align='center'><input name='btnCrearEntidad' type='button' id='btnCrearEntidad' value='     Exportar RIPS     ' onclick='CrearEntidad()' /></div></td></tr></table>");
		
		}
			
		if(va.equals("3A")){	
			String eps=req.getParameter("eps");
			
			
			//System.out.print("irleth: "+eps+"  "+fi+"  "+ff);
			
			String a1=fi.substring(0, 2);
			String m1=fi.substring(3, 5);
			String d1=fi.substring(6, 10);
			String fin=d1+"-"+m1+"-"+a1;
						
			String a2=ff.substring(0, 2);
			String m2=ff.substring(3, 5);
			String d2=ff.substring(6, 10);
			String ffn=d2+"-"+m2+"-"+a2;
			//System.out.print("cesar: "+eps+"  "+fin+"  "+ffn);
			
			out.print("<select name='FD' multiple  id='FD' style='width:380px;  height: 400px'>");
		rs=mr.FacturasA(eps,fin,ffn);
		try {
			while(rs.next()){
		//	out.print("<option title='"+rs.getString(1)+"' value="+rs.getString(2)+">"+rs.getString(1)+"</option>");
			out.print("<option value='"+rs.getString(1)+"'>"+rs.getString(2)+"</option>");
			}
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		out.print("</select>");
		}
		
		
		
		
		if(va.equals("5A")){	
			String ARA=req.getParameter("ARA");
			//System.out.println("AR: "+AR);
			String rut=req.getParameter("rut");
			String nom=req.getParameter("nom");
		    
			 int cah=0;
		 	 int cam=0;
		 	 int can=0;
		 	 int cap=0;
		 	 int cat=0;
		 	 int cau=0;
		 	 int cus=0;
		  	 int cac=0;
		  	 int cad=0;
		  	 int caf=0;
		 	
		  	String dias=fi.substring(0, 2);
			String meses=fi.substring(3, 5);
			String anos=fi.substring(6, 10);
			fi=anos+"-"+meses+"-"+dias;
			
			String diasf=ff.substring(0, 2);
			String mesesf=ff.substring(3, 5);
			String anosf=ff.substring(6, 10);
			ff=anosf+"-"+mesesf+"-"+diasf;
		  	 
			//Numero de facturas y las facturas
			String V[] = new String[200];
			int i1=0;
			 StringTokenizer elementos;  
			// System.out.println("datos: "+datos);
			   elementos = new StringTokenizer(datos,"|"); 
			 //  System.out.println("elementos: "+elementos);
			   while(elementos.hasMoreTokens()){ 
				   V[i1] = elementos.nextToken();  
				   System.out.println("V[i1]: "+V[i1]);
				   i1++;
			   }
					   
					
			 JFileChooser selector = new JFileChooser(".");
			 try
			 {
			 	 String f=rut+"\\";
			 	 
			 	 
			 	rs=mr.Prestador();
			 	String prestador="";
				try {
					while(rs.next()){
						prestador=rs.getString(1);
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				
				String Tdig=null;
		
					
				/*** Archivo de Consultas FUNCIONA ***/	
				if(ARA.equals("1")){		
				for(int i=0; i<i1; i++){//for de la cantidad de facturas
					rs2=mr.DAC(V[i]);
					if(rs2.next()){
						Tdig=rs2.getString(1);
						rs=mr.AC1(fi,ff,V[i],Tdig);
				  try {
						 while(rs.next()){
							 if(cac>0){out.print("\r\n");}
							 String ce=rs.getString(9);
							 String ce2=rs.getString(8);
							 String cen="0";
							 if (ce.length()<2){ce=cen+ce;}
							 if (ce2.length()<2){ce2=cen+ce2;}
							// outputAC.println(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6)+","+rs.getString(7)+","+rs.getString(8)+","+rs.getString(9)+","+rs.getString(10)+","+rs.getString(11)+","+rs.getString(12)+","+rs.getString(13)+","+rs.getString(14)+","+rs.getString(15)+","+rs.getString(16)+","+rs.getString(17));
							out.print(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6)+","+rs.getString(7)+","+ce2+","+ce+","+rs.getString(10)+","+rs.getString(11)+","+rs.getString(12)+","+rs.getString(13)+","+rs.getString(14)+","+rs.getString(15)+","+rs.getString(16)+","+rs.getString(17));
							cac++;
						}
						rs.getStatement().getConnection().close();
					 } catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					 }
					
					}else{
					
					rs=mr.AC(fi,ff,V[i]);
				  try {
						 while(rs.next()){
							 if(cac>0){out.print("\r\n");}
							 String ce=rs.getString(9);
							 String ce2=rs.getString(8);
							 String cen="0";
							 if (ce.length()<2){ce=cen+ce;}
							 if (ce2.length()<2){ce2=cen+ce2;}
							// outputAC.println(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6)+","+rs.getString(7)+","+rs.getString(8)+","+rs.getString(9)+","+rs.getString(10)+","+rs.getString(11)+","+rs.getString(12)+","+rs.getString(13)+","+rs.getString(14)+","+rs.getString(15)+","+rs.getString(16)+","+rs.getString(17));
							out.print(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6)+","+rs.getString(7)+","+ce2+","+ce+","+rs.getString(10)+","+rs.getString(11)+","+rs.getString(12)+","+rs.getString(13)+","+rs.getString(14)+","+rs.getString(15)+","+rs.getString(16)+","+rs.getString(17));
							cac++;
						}
						rs.getStatement().getConnection().close();
					 } catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					 }

					}	
				
				}
				}//FIN ARA=1
		}
		catch(Exception e)
		{
		 e.printStackTrace();
		}			
	}//FIN VA =5A


















/***********************************GLOSAS****************************************/
		String v1=req.getParameter("v1");
		String v2=req.getParameter("v2");
		String v3=req.getParameter("v3");
		String v4=req.getParameter("v4");
		String v5=req.getParameter("v5");
		String v6=req.getParameter("v6");
		String v7=req.getParameter("v7");
		String v8=req.getParameter("v8");
		String v9=req.getParameter("v9");
		String v10=req.getParameter("v10");
		String v11=req.getParameter("v11");
		String v12=req.getParameter("v12");
		String usu=req.getParameter("usu");
		
		MetodoMovimientos mm=new MetodoMovimientos();
		if(va.equals("ag")){
			//La idea es  consultar las facturas
			out.print("<table width='100%' border='0' align='center'><tr><td colspan='4' height='30' valign='top'><div align='center' class='style11' id='cabecera2'>Consulta de Facturas a Notificar</div></td></tr></table>");
			
			//out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='60%'><i><div align='center'>Paciente</div></i></td><td width='30%'><i><div align='center'>Empresa</div></i></td><td width='10%'><i><div align='center'>Admisi�n</div></i></td></tr>");  //
			out.print("<table width='100%' border=0' class='style6' ><tr BGCOLOR='#D3D3D3' ><td colspan='4' height='30' valign='top'><i><div align='center'>Criterios de Busqueda</div></i></td></tr>");
			out.print("<tr></tr><tr><td width='15%'><div align='right'>Factura No:</div></td><td width='30%'><input name='txtFact' type='text' id='txtFact' size='39' maxlength='100' /></td>");
			out.print("<td width='15%'><div align='right'>Entidad:</div></td><td width='40%'><select  style='width:269px' name='Ent' id='Ent' ><option value='Seleccione'>Seleccione</option>");
			rs2=mm.Empresas();
			try {
				while(rs2.next()){
				out.print("<option title='"+rs2.getString(2)+"' value="+rs2.getString(1)+">"+rs2.getString(2)+"</option>");
				}
				rs2.getStatement().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("</td></tr>");
						
			out.print("<tr><td><div align='right'>Tipo Doc:</div></td><td width='40%'><select  style='width:50px' name='tdoc' id='tdoc' >");
			rs2=mm.TipoDoc();
			try {
				while(rs2.next()){
				out.print("<option value="+rs2.getString(1)+">"+rs2.getString(2)+"</option>");
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("</select>");
			
			out.print(" Numero:<input name='txtDoc' type='text' id='txtDoc'  size='22' maxlength='50' /></td><td><div align='right'>Nombres:</div></td><td><input name='txtNom' type='text' id='txtNom' size='39' maxlength='100'/ ></td></tr>");
			out.print("<tr><td><div align='right'>Primer Apellido:</div></td><td><input name='txtPa' type='text' id='txtPa'  size='39' maxlength='50' /></td><td><div align='right'>Segundo Apellido:</div></td><td><input name='txtSa' type='text' id='txtSa'  size='39' maxlength='100'/ ></td></tr>");
			out.print("<tr><td><div align='right'>Fecha Inicial:</div></td><td><input name='FI' type='text' id='FI' size='10'  onKeyup='masca(this,patron,true,0,0,0,event)' onblur='vfi()'; /></td><td><div align='right'>Fecha Final:</div></td><td><input name='FF' type='text' id='FF' size='10' onKeyup='masca(this,patron,true,0,0,0,event)' onblur='vff()'; /></td></tr><tr></tr></table>");
			
			//out.print("<tr><td><div align='right'>Primer Apellido:</div></td><td><input name='txtCodigoPrestador' type='text' id='txtCodPrestador' size='39' maxlength='50' onkeydown='A(this, event)'/><span class='style8'>*</span></td><td><div align='right'>Segundo Apellido:</div></td><td><select name='listEstado' id='listEstado' style='width: 266px;' onkeydown='A(this, event)'><option value='Activo' selected='selected'>Activo</option><option value='Inactivo'>Inactivo</option></select><span class='style8'>*</span></td></tr></table>");
			out.print("<table width='100%' border='0' class='style6' ><tr><td colspan='4'><div align='center'><input name='btnCrearEntidad' type='button' id='btnCrearEntidad' value='     Consultar Facturas    ' onclick='BuscarFactGlosas()' /></div></td></tr><tr></tr><tr></tr></table>");
			out.print("<table width='100%' border='0' class='style6' ><tr></tr><tr BGCOLOR='#D3D3D3' ><td colspan='4' height='30' valign='top'><i><div align='center'>Resultados de Busqueda</div></i></td></tr><tr></tr></table>");
			out.print("<div id='resultadosf' style='height: 240px; overflow-y: scroll'></div>");
		}
		
		if(va.equals("showGlosas")){
			String sql="";
			//String v1=req.getParameter("v1");
			if((v1==null)||(v1.equals(""))){v1="";}else{sql=sql+" AND n.consecutivo='"+v1+"'";}
			//String v2=req.getParameter("v2");
			if(v2.equals("Seleccione")){v2="";}else{sql=sql+" AND ef.cod_eps="+v2;}
			//String v3=req.getParameter("v3");
			if(v3==null){v3="";}
			//String v4=req.getParameter("v4");
			if((v4==null)||(v4.equals(""))){v4="";}else{sql=sql+" AND p.tipo_documento='"+v3+"' AND p.numero_documento="+v4;}
			//String v5=req.getParameter("v5");
			if((v5==null)||(v5.equals(""))){v5="";}else{sql=sql+" AND p.nombre like '"+v5+"%'";}
			//String v6=req.getParameter("v6");
			if((v6==null)||(v6.equals(""))){v6="";}else{sql=sql+" AND p.primer_apellido like '"+v6+"%'";}
			//String v7=req.getParameter("v7");
			if((v7==null)||(v7.equals(""))){v7="";}else{sql=sql+" AND p.segundo_apellido like '"+v7+"%'";}
			//String v8=req.getParameter("v8");
			//String v9=req.getParameter("v9");
			
			if((v8==null)||(v8.equals(""))||(v9.equals(""))||(v9==null)){v8="";v9="";}
			else{
			String dv8=v8.substring(0, 2);
			String mv8=v8.substring(3, 5);
			String av8=v8.substring(6, 10);
			String fv8=av8+"-"+mv8+"-"+dv8;
			
			String dv9=v9.substring(0, 2);
			String mv9=v9.substring(3, 5);
			String av9=v9.substring(6, 10);
			String fv9=av9+"-"+mv9+"-"+dv9;
			
			sql=sql+" AND ef.fecha BETWEEN '"+fv8+"' AND '"+fv9+"' ";}
			
			int crs=0;		
			rs2=mr.GeneraSQL(sql);
			try {
				while(rs2.next()){
					if(crs==0){
					out.print("<table width='100%' border='0' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='7%'><i><div align='center'>No. de Factura</div></i></td><td width='15%'><i><div align='center'>Empresa</div></i></td><td width='5%'><i><div align='center'>Fecha</div></i></td><td width='7%'><i><div align='center'>Documento</div></i></td><td width='30%'><i><div align='center'>Nombres</div></i></td></tr>	");  //
					}
				out.print("<tr><td><a  href='#'onclick='Notificar(&quot;"+rs2.getString(10)+"&quot;,&quot;"+rs2.getString(1)+"&quot;,&quot;"+rs2.getString(11)+"&quot;)'>"+rs2.getString(1)+"</a></td><td>"+rs2.getString(2)+"</td><td>"+rs2.getString(8)+"</td><td>"+rs2.getString(3)+" "+rs2.getString(4)+"</td><td> "+rs2.getString(5)+" "+rs2.getString(6)+" "+rs2.getString(7)+"</td></tr>");
				crs++;
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			if(crs==0){out.print("No hay resultados para sus criterios de busqueda.");}
		}
		
		
		if(va.equals("agf")){
			//La idea es  consultar las facturas
			
			out.print("<table width='100%' border='0' align='center'><tr><td colspan='4' valign='top'><div align='center' class='style11' id='cabecera2'>Notificar Glosas</div></td></tr></table>");
	
			out.print("<table width='100%' border='0' class='style6' ><tr></tr><tr BGCOLOR='#D3D3D3' ><td colspan='4' valign='top'><i><div align='center'>Factura: "+v2+"</div></i></td></tr><tr></tr></table>");
			out.print("<table width='100%' border='0' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='3%'><i><div align='center'>Item</div></i></td><td width='10%'><i><div align='center'>Cod. Programa</div></i></td><td width='50%'><i><div align='center'>Nombre Programa/Servicio</div></i></td><td width='11%'><i><div align='center'>Clase Servicio</div></i></td><td width='5%'><i><div align='center'>Cantidad</div></i></td><td width='7%'><i><div align='center'>Valor Unitario</div></i></td><td width='7%'><i><div align='center'>Valor Total</div></i></td><td width='7%'><i><div align='center'>Valor Glosado</div></i></td></tr>	");  //
			
			int it=0;	
			String cs="";
			String ns="";
			int vt=0;
			String vtg="0";
			
			rs2=mr.DetalleFactura(v1);
			try {
				while(rs2.next()){
					int sw=0;
					vtg="0";
					it++;
					if(rs2.getString(5)==null){cs="";}else{cs=" - "+rs2.getString(5);}
					if(rs2.getString(6)==null){ns="";}else{ns=" - "+rs2.getString(6);}
					vt=Integer.parseInt(rs2.getString(9))*Integer.parseInt(rs2.getString(8));
					
					
					
					rs3=mr.DetalleFacturaGlosa(rs2.getString(1));
					if(rs3.next()){
						vtg=rs3.getString(1);
						sw=1;
						out.print("<tr><td>"+it+"</td><td><font color='grey'>"+rs2.getString(3)+""+cs+"</font></td><td><a  href='#'onclick='RevisarNotificacion(&quot;"+v1+"&quot;,&quot;"+v2+"&quot;,&quot;"+v3+"&quot;,&quot;"+rs2.getString(1)+"&quot;)'><font color='grey'>"+rs2.getString(4)+""+ns+"</font></a></td><td><font color='grey'>"+rs2.getString(7)+"</font></td><td><font color='grey'>"+rs2.getString(8)+"</font></td><td><font color='grey'>"+rs2.getString(9)+"</font></td><td><font color='grey'>"+vt+"</font></td><td><font color='grey'>"+vtg+"</font></td></tr>");
					}
					
					if(sw==0){
					out.print("<tr><td>"+it+"</td><td>"+rs2.getString(3)+""+cs+"</td><td><a  href='#'onclick='NotificarCargue(&quot;"+v1+"&quot;,&quot;"+v2+"&quot;,&quot;"+v3+"&quot;,&quot;"+rs2.getString(1)+"&quot;)'>"+rs2.getString(4)+""+ns+"</a></td><td>"+rs2.getString(7)+"</td><td>"+rs2.getString(8)+"</td><td>"+rs2.getString(9)+"</td><td>"+vt+"</td><td>"+vtg+"</td></tr>");
					}
				
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			out.print("</table>");
			//out.print("<div id='resultadosf' style='height: 240px; overflow-y: scroll'></div>");
			
		}
		
		
		if(va.equals("AplicarGlosa2")){
					
			out.print("<table width='100%' border='0' align='center'><tr><td colspan='4' valign='top'><div align='center' class='style11' id='cabecera2'>Motivos de Glosa</div></td></tr></table>");
	
			out.print("<table width='100%' border='0' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='10%'><i><div align='center'>Cod. Programa</div></i></td><td width='50%'><i><div align='center'>Nombre Programa/Servicio</div></i></td><td width='22%'><i><div align='center'>Clase Servicio</div></i></td><td width='5%'><i><div align='center'>Cantidad</div></i></td><td width='10%'><i><div align='center'>Valor Total</div></i></td></tr>	");  //
			String cs="";
			String ns="";
			Integer cant=0;
			rs2=mr.DetalleFacturaD(v4);
			try {
				if(rs2.next()){
					if(rs2.getString(5)==null){cs="";}else{cs=" - "+rs2.getString(5);}
					if(rs2.getString(6)==null){ns="";}else{ns=" - "+rs2.getString(6);}
					cant=Integer.parseInt(rs2.getString(9))*Integer.parseInt(rs2.getString(8));
					out.print("<tr><td>"+rs2.getString(3)+""+cs+"</td><td>"+rs2.getString(4)+""+ns+"</td><td>"+rs2.getString(7)+"</td><td>"+rs2.getString(8)+"</td><td>"+cant+"</td></tr>");
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			out.print("</table>");
			
			out.print("<table width='100%' border='0' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='40%'><i><div align='center'>Concepto General de la Glosa</div></i></td><td width='40%'><i><div align='center'>Concepto especifico de la Glosa</div></i></td><td width='10%'><i><div align='center'>Valor glosado</div></i></td><td width='10%'><i><div align='center'>Diferencia</div></i></td></tr>	");  //
			
			out.print("<tr><td><select  style='width:100%' id='cgg' onchange='comboge()' ><option value='Seleccione'>Seleccione</option>");
			rs2=mr.GlosasConceptoG();
			try {
				while(rs2.next()){
				out.print("<option title='"+rs2.getString(3)+"' value="+rs2.getString(1)+">"+rs2.getString(2)+"</option>");
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("</select></td>");
			
			
			out.print("<td><div id='cmbcge'><select  style='width:100%' id='cge' ><option value='Seleccione'>Seleccione</option>");
			out.print("</select></div></td>");
			out.print("<td><input type='text' id='vaplica' style='width:95%' value='0' onkeyup='cvg("+cant+")'/></td>");
			out.print("<td><input type='text' id='vtota' value='"+cant+"' style='width:95%'/></td>");
			
			out.print("<tr><td colspan=4><div align='center'>Observacion</div></td></tr>");
			out.print("<tr><td colspan=4><div align='center'><textarea id='txtDescripcion' cols='58' rows='3'></textarea></div></td></tr>");
			
			out.print("<tr><td colspan=4><div align='center'><input type='button' id='NGlosa' value='     Notificar    ' onclick='AplicaGxC(&quot;"+v1+"&quot;,&quot;"+v2+"&quot;,&quot;"+v3+"&quot;,&quot;"+v4+"&quot;,&quot;"+cant+"&quot;)' /></div></td></tr>");
			
			
		}
		
		
		
		if(va.equals("cge")){
			
			out.print("<td width='40%'><div id='cmbcge'><select  style='width:100%' id='cge' ><option value='Seleccione'>Seleccione</option>");
			rs2=mr.GlosasConceptoE(v2);
			try {
				while(rs2.next()){
				out.print("<option value="+rs2.getString(1)+">"+rs2.getString(2)+"</option>");
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("</select></div></td>");
			
		}
			
		
		if(va.equals("ig")){
			System.out.println("v1: "+v1+" v2: "+v2+" v3: "+v3+" v4: "+v4+ "v5: "+v5+" v6: "+v6+" v7: "+v7+" v8: "+v8+" v9: "+v9+" v10: "+v10+" v11: "+v11+" v12: "+v12);
			
			String codg="";
			int sw=0;
			rs2=mr.ConsultarCodGlosa(v3);
			try {
				if(rs2.next()){
					codg=rs2.getString(1);
					sw=1;
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			if(sw==0){
				mr.InsertarGlosa(v3,v1);
				rs2=mr.ConsultarCodGlosa(v3);
				try {
					if(rs2.next()){
						codg=rs2.getString(1);
					}
					rs2.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
			}
			
			mr.InsertarDetalleGlosa(codg,v4,v5,v6,v7,v8,v9,v10,v11,"9",v12,fechacjmysql,hra);
		}		
		
		if(va.equals("RevisarGlosa2")){
			
			out.print("<table width='100%' border='0' align='center'><tr><td colspan='4' valign='top'><div align='center' class='style11' id='cabecera2'>Motivos de Glosa</div></td></tr></table>");
	
			out.print("<table width='100%' border='0' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='10%'><i><div align='center'>Cod. Programa</div></i></td><td width='50%'><i><div align='center'>Nombre Programa/Servicio</div></i></td><td width='22%'><i><div align='center'>Clase Servicio</div></i></td><td width='5%'><i><div align='center'>Cantidad</div></i></td><td width='10%'><i><div align='center'>Valor Unitario</div></i></td></tr>	");  //
			String cs="";
			String ns="";
			Integer cant=0;
			rs2=mr.DetalleFacturaD(v4);
			try {
				if(rs2.next()){
					if(rs2.getString(5)==null){cs="";}else{cs=" - "+rs2.getString(5);}
					if(rs2.getString(6)==null){ns="";}else{ns=" - "+rs2.getString(6);}
					cant=Integer.parseInt(rs2.getString(9))*Integer.parseInt(rs2.getString(8));
					out.print("<tr><td>"+rs2.getString(3)+""+cs+"</td><td>"+rs2.getString(4)+""+ns+"</td><td>"+rs2.getString(7)+"</td><td>"+rs2.getString(8)+"</td><td>"+cant+"</td></tr>");
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			out.print("</table>");
		
			out.print("<table width='100%' border='0' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='40%'><i><div align='center'>Concepto General de la Glosa</div></i></td><td width='40%'><i><div align='center'>Concepto especifico de la Glosa</div></i></td><td width='10%'><i><div align='center'>Valor glosado</div></i></td><td width='10%'><i><div align='center'>Diferencia</div></i></td></tr>	");  //
			
			rs2=mr.GlosaRevision(v4);
			try {
				if(rs2.next()){
				out.print("<tr><td><select  style='width:100%' id='cgg'  disabled='true' >");
				out.print("<option value='0'>"+rs2.getString(1)+"</option>");
				out.print("</select></td>");
				
				out.print("<td><select  style='width:100%' id='cge'  disabled='true'>");
				out.print("<option value='0'>"+rs2.getString(2)+"</option>");
				out.print("</select></td>");
				
				out.print("<td><input type='text' id='vaplica' style='width:95%' value='"+rs2.getString(4)+"'  disabled='true'/></td>");
				int cantd=Integer.parseInt(rs2.getString(3))-Integer.parseInt(rs2.getString(4));
				out.print("<td><input type='text' id='vtota' value='"+cantd+"' style='width:95%'  disabled='true'/></td>");

				out.print("<tr><td colspan=4><div align='center'>Observacion</div></td></tr>");
				out.print("<tr><td colspan=4><div align='center'><textarea id='txtDescripcion' cols='58' rows='3'  disabled='true'>"+rs2.getString(5)+"</textarea></div></td></tr>");
				
				if(rs2.getString(6).equals("9")){
					out.print("<tr><td colspan=4><div align='center'><input type='button' id='NGlosa' value='     Anular Notificacion    ' onclick='EliminarNotificacion(&quot;"+v1+"&quot;,&quot;"+v2+"&quot;,&quot;"+v3+"&quot;,&quot;"+v4+"&quot;)' /></div></td></tr>");
				}else{
					String estadon="";
					if(rs2.getString(6).equals("0")){estadon="Notificacion Enviada a Auditoria";}
					if(rs2.getString(6).equals("1")){estadon="Notificacion Subsanada";}
					if(rs2.getString(6).equals("2")){estadon="Notificacion Aplicada";}
					if(rs2.getString(6).equals("3")){estadon="Notificacion Rechazada";}
					if(rs2.getString(6).equals("5")){estadon="Notificacion Conciliada";}
					out.print("<tr><td colspan=4><div align='center'><label><font color='red'>"+estadon+"</font></label></div></td></tr>");
				}
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
		}
		
		
		
		if(va.equals("EliminarNotificacion")){
			
			mr.AnularNotificacion(v1,usu,fechacjmysql,hra);
		}
			
		
		if(va.equals("cg")){
			//La idea es  consultar las facturas
			out.print("<table width='100%' border='0' align='center'><tr><td colspan='4' height='30' valign='top'><div align='center' class='style11' id='cabecera2'>Facturas en proceso de Glosas</div></td></tr></table>");
			
			//out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='60%'><i><div align='center'>Paciente</div></i></td><td width='30%'><i><div align='center'>Empresa</div></i></td><td width='10%'><i><div align='center'>Admisi�n</div></i></td></tr>");  //
			out.print("<table width='100%' border=0' class='style6' ><tr BGCOLOR='#D3D3D3' ><td colspan='4' height='30' valign='top'><i><div align='center'>Criterios de Busqueda</div></i></td></tr>");
			out.print("<tr></tr><tr><td width='15%'><div align='right'>Factura No:</div></td><td width='30%'><input name='txtFact' type='text' id='txtFact' size='39' maxlength='100' /></td>");
			out.print("<td width='15%'><div align='right'>Entidad:</div></td><td width='40%'><select  style='width:269px' name='Ent' id='Ent' ><option value='Seleccione'>Seleccione</option>");
			rs2=mm.Empresas();
			try {
				while(rs2.next()){
				out.print("<option title='"+rs2.getString(2)+"' value="+rs2.getString(1)+">"+rs2.getString(2)+"</option>");
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("</td></tr>");
						
			out.print("<tr><td><div align='right'>Tipo Doc:</div></td><td width='40%'><select  style='width:50px' name='tdoc' id='tdoc' >");
			rs2=mm.TipoDoc();
			try {
				while(rs2.next()){
				out.print("<option value="+rs2.getString(1)+">"+rs2.getString(2)+"</option>");
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("</select>");
			
			out.print(" Numero:<input name='txtDoc' type='text' id='txtDoc'  size='22' maxlength='50' /></td><td><div align='right'>Nombres:</div></td><td><input name='txtNom' type='text' id='txtNom' size='39' maxlength='100'/ ></td></tr>");
			out.print("<tr><td><div align='right'>Primer Apellido:</div></td><td><input name='txtPa' type='text' id='txtPa'  size='39' maxlength='50' /></td><td><div align='right'>Segundo Apellido:</div></td><td><input name='txtSa' type='text' id='txtSa'  size='39' maxlength='100'/ ></td></tr>");
			out.print("<tr><td><div align='right'>Fecha Inicial:</div></td><td><input name='FI' type='text' id='FI' size='10'  onKeyup='masca(this,patron,true,0,0,0,event)' onblur='vfi()'; /></td><td><div align='right'>Fecha Final:</div></td><td><input name='FF' type='text' id='FF' size='10' onKeyup='masca(this,patron,true,0,0,0,event)' onblur='vff()'; /></td></tr><tr></tr></table>");
			
			//out.print("<tr><td><div align='right'>Primer Apellido:</div></td><td><input name='txtCodigoPrestador' type='text' id='txtCodPrestador' size='39' maxlength='50' onkeydown='A(this, event)'/><span class='style8'>*</span></td><td><div align='right'>Segundo Apellido:</div></td><td><select name='listEstado' id='listEstado' style='width: 266px;' onkeydown='A(this, event)'><option value='Activo' selected='selected'>Activo</option><option value='Inactivo'>Inactivo</option></select><span class='style8'>*</span></td></tr></table>");
			out.print("<table width='100%' border='0' class='style6' ><tr><td colspan='4'><div align='center'><input name='btnCrearEntidad' type='button' id='btnCrearEntidad' value='     Consultar Facturas Notificadas   ' onclick='BuscarFactNotificadas()' /></div></td></tr><tr></tr><tr></tr></table>");
			out.print("<table width='100%' border='0' class='style6' ><tr></tr><tr BGCOLOR='#D3D3D3' ><td colspan='4' height='30' valign='top'><i><div align='center'>Resultados de Busqueda</div></i></td></tr><tr></tr></table>");
			out.print("<div id='resultadosf' style='height: 240px; overflow-y: scroll'></div>");
			out.print("<table width='100%' border='0' class='style6' ><tr><td><div id='xxxx' align='center'><input type='button' id='revisar'  value='     Revisar notificacion   ' onclick='ResponderNotificacion()' /></div></td></tr></table>");
		}
		
		
		if(va.equals("cgr")){
			//La idea es  consultar las facturas
			out.print("<table width='100%' border='0' align='center'><tr><td colspan='4' height='30' valign='top'><div align='center' class='style11' id='cabecera2'>Facturas en proceso de Glosas</div></td></tr></table>");
			
			//out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='60%'><i><div align='center'>Paciente</div></i></td><td width='30%'><i><div align='center'>Empresa</div></i></td><td width='10%'><i><div align='center'>Admisi�n</div></i></td></tr>");  //
			out.print("<table width='100%' border=0' class='style6' ><tr BGCOLOR='#D3D3D3' ><td colspan='4' height='30' valign='top'><i><div align='center'>Criterios de Busqueda</div></i></td></tr>");
			out.print("<tr></tr><tr><td width='15%'><div align='right'>Factura No:</div></td><td width='30%'><input name='txtFact' type='text' id='txtFact' size='39' maxlength='100' /></td>");
			out.print("<td width='15%'><div align='right'>Entidad:</div></td><td width='40%'><select  style='width:269px' name='Ent' id='Ent' ><option value='Seleccione'>Seleccione</option>");
			rs2=mm.Empresas();
			try {
				while(rs2.next()){
				out.print("<option title='"+rs2.getString(2)+"' value="+rs2.getString(1)+">"+rs2.getString(2)+"</option>");
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("</td></tr>");
						
			out.print("<tr><td><div align='right'>Tipo Doc:</div></td><td width='40%'><select  style='width:50px' name='tdoc' id='tdoc' >");
			rs2=mm.TipoDoc();
			try {
				while(rs2.next()){
				out.print("<option value="+rs2.getString(1)+">"+rs2.getString(2)+"</option>");
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("</select>");
			
			out.print(" Numero:<input name='txtDoc' type='text' id='txtDoc'  size='22' maxlength='50' /></td><td><div align='right'>Nombres:</div></td><td><input name='txtNom' type='text' id='txtNom' size='39' maxlength='100'/ ></td></tr>");
			out.print("<tr><td><div align='right'>Primer Apellido:</div></td><td><input name='txtPa' type='text' id='txtPa'  size='39' maxlength='50' /></td><td><div align='right'>Segundo Apellido:</div></td><td><input name='txtSa' type='text' id='txtSa'  size='39' maxlength='100'/ ></td></tr>");
			out.print("<tr><td><div align='right'>Fecha Inicial:</div></td><td><input name='FI' type='text' id='FI' size='10'  onKeyup='masca(this,patron,true,0,0,0,event)' onblur='vfi()'; /></td><td><div align='right'>Fecha Final:</div></td><td><input name='FF' type='text' id='FF' size='10' onKeyup='masca(this,patron,true,0,0,0,event)' onblur='vff()'; /></td></tr><tr></tr></table>");
			
			//out.print("<tr><td><div align='right'>Primer Apellido:</div></td><td><input name='txtCodigoPrestador' type='text' id='txtCodPrestador' size='39' maxlength='50' onkeydown='A(this, event)'/><span class='style8'>*</span></td><td><div align='right'>Segundo Apellido:</div></td><td><select name='listEstado' id='listEstado' style='width: 266px;' onkeydown='A(this, event)'><option value='Activo' selected='selected'>Activo</option><option value='Inactivo'>Inactivo</option></select><span class='style8'>*</span></td></tr></table>");
			out.print("<table width='100%' border='0' class='style6' ><tr><td colspan='4'><div align='center'><input name='btnCrearEntidad' type='button' id='btnCrearEntidad' value='     Consultar Facturas   ' onclick='BuscarFactNotificadasr()' /></div></td></tr><tr></tr><tr></tr></table>");
			out.print("<table width='100%' border='0' class='style6' ><tr></tr><tr BGCOLOR='#D3D3D3' ><td colspan='4' height='30' valign='top'><i><div align='center'>Resultados de Busqueda</div></i></td></tr><tr></tr></table>");
			out.print("<div id='resultadosf' style='height: 240px; overflow-y: scroll'></div>");
			out.print("<table width='100%' border='0' class='style6' ><tr><td><div id='xxxx' align='center'><input type='button' id='revisar'  value='     Generar Reporte   ' onclick='ReportedeGlosa(2)' /></div></td></tr></table>");
		}
		
		
		if(va.equals("cgf")){
			//La idea es  consultar las facturas
			out.print("<table width='100%' border='0' align='center'><tr><td colspan='4' height='30' valign='top'><div align='center' class='style11' id='cabecera2'>Facturas en proceso de Glosas</div></td></tr></table>");
			
			//out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='60%'><i><div align='center'>Paciente</div></i></td><td width='30%'><i><div align='center'>Empresa</div></i></td><td width='10%'><i><div align='center'>Admisi�n</div></i></td></tr>");  //
			out.print("<table width='100%' border=0' class='style6' ><tr BGCOLOR='#D3D3D3' ><td colspan='4' height='30' valign='top'><i><div align='center'>Criterios de Busqueda</div></i></td></tr>");
			out.print("<tr></tr><tr><td width='15%'><div align='right'>Factura No:</div></td><td width='30%'><input name='txtFact' type='text' id='txtFact' size='39' maxlength='100' /></td>");
			out.print("<td width='15%'><div align='right'>Entidad:</div></td><td width='40%'><select  style='width:269px' name='Ent' id='Ent' ><option value='Seleccione'>Seleccione</option>");
			rs2=mm.Empresas();
			try {
				while(rs2.next()){
				out.print("<option title='"+rs2.getString(2)+"' value="+rs2.getString(1)+">"+rs2.getString(2)+"</option>");
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("</td></tr>");
						
			out.print("<tr><td><div align='right'>Tipo Doc:</div></td><td width='40%'><select  style='width:50px' name='tdoc' id='tdoc' >");
			rs2=mm.TipoDoc();
			try {
				while(rs2.next()){
				out.print("<option value="+rs2.getString(1)+">"+rs2.getString(2)+"</option>");
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("</select>");
			
			out.print(" Numero:<input name='txtDoc' type='text' id='txtDoc'  size='22' maxlength='50' /></td><td><div align='right'>Nombres:</div></td><td><input name='txtNom' type='text' id='txtNom' size='39' maxlength='100'/ ></td></tr>");
			out.print("<tr><td><div align='right'>Primer Apellido:</div></td><td><input name='txtPa' type='text' id='txtPa'  size='39' maxlength='50' /></td><td><div align='right'>Segundo Apellido:</div></td><td><input name='txtSa' type='text' id='txtSa'  size='39' maxlength='100'/ ></td></tr>");
			out.print("<tr><td><div align='right'>Fecha Inicial:</div></td><td><input name='FI' type='text' id='FI' size='10'  onKeyup='masca(this,patron,true,0,0,0,event)' onblur='vfi()'; /></td><td><div align='right'>Fecha Final:</div></td><td><input name='FF' type='text' id='FF' size='10' onKeyup='masca(this,patron,true,0,0,0,event)' onblur='vff()'; /></td></tr><tr></tr></table>");
			
			//out.print("<tr><td><div align='right'>Primer Apellido:</div></td><td><input name='txtCodigoPrestador' type='text' id='txtCodPrestador' size='39' maxlength='50' onkeydown='A(this, event)'/><span class='style8'>*</span></td><td><div align='right'>Segundo Apellido:</div></td><td><select name='listEstado' id='listEstado' style='width: 266px;' onkeydown='A(this, event)'><option value='Activo' selected='selected'>Activo</option><option value='Inactivo'>Inactivo</option></select><span class='style8'>*</span></td></tr></table>");
			out.print("<table width='100%' border='0' class='style6' ><tr><td colspan='4'><div align='center'><input name='btnCrearEntidad' type='button' id='btnCrearEntidad' value='     Consultar Facturas Respondidas  ' onclick='BuscarFactNotificadasf()' /></div></td></tr><tr></tr><tr></tr></table>");
			out.print("<table width='100%' border='0' class='style6' ><tr></tr><tr BGCOLOR='#D3D3D3' ><td colspan='4' height='30' valign='top'><i><div align='center'>Resultados de Busqueda</div></i></td></tr><tr></tr></table>");
			out.print("<div id='resultadosf' style='height: 240px; overflow-y: scroll'></div>");
			out.print("<table width='100%' border='0' class='style6' ><tr><td><div id='xxxx' align='center'><input type='button' id='revisar'  value='     Finalizar Proceso de Glosas   ' onclick='FProcesoG()' /></div></td></tr></table>");
		}
		
		if(va.equals("cgre")){
			//La idea es  consultar las facturas
			out.print("<table width='100%' border='0' align='center'><tr><td colspan='4' height='30' valign='top'><div align='center' class='style11' id='cabecera2'>Consultar Reporte de Glosas</div></td></tr></table>");
			
			//out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='60%'><i><div align='center'>Paciente</div></i></td><td width='30%'><i><div align='center'>Empresa</div></i></td><td width='10%'><i><div align='center'>Admisi�n</div></i></td></tr>");  //
			out.print("<table width='100%' border=0' class='style6' ><tr BGCOLOR='#D3D3D3' ><td colspan='4' height='30' valign='top'><i><div align='center'>Criterios de Busqueda</div></i></td></tr>");
			out.print("<tr></tr><tr><td width='15%'><div align='right'>Reporte No:</div></td><td width='30%'><input name='txtFact' type='text' id='txtFact' size='39' maxlength='100' /></td>");
			out.print("<td width='15%'></td><td width='40%'></td></tr>");
			out.print("<tr><td><div align='right'>Fecha Inicial:</div></td><td><input name='FI' type='text' id='FI' size='10'  onKeyup='masca(this,patron,true,0,0,0,event)' onblur='vfi()'; /></td><td><div align='right'>Fecha Final:</div></td><td><input name='FF' type='text' id='FF' size='10' onKeyup='masca(this,patron,true,0,0,0,event)' onblur='vff()'; /></td></tr><tr></tr></table>");
			
			//out.print("<tr><td><div align='right'>Primer Apellido:</div></td><td><input name='txtCodigoPrestador' type='text' id='txtCodPrestador' size='39' maxlength='50' onkeydown='A(this, event)'/><span class='style8'>*</span></td><td><div align='right'>Segundo Apellido:</div></td><td><select name='listEstado' id='listEstado' style='width: 266px;' onkeydown='A(this, event)'><option value='Activo' selected='selected'>Activo</option><option value='Inactivo'>Inactivo</option></select><span class='style8'>*</span></td></tr></table>");
			out.print("<table width='100%' border='0' class='style6' ><tr><td colspan='4'><div align='center'><input name='btnCrearEntidad' type='button' id='btnCrearEntidad' value='     Consultar Reportes   ' onclick='BuscarReportes()' /></div></td></tr><tr></tr><tr></tr></table>");
			out.print("<table width='100%' border='0' class='style6' ><tr></tr><tr BGCOLOR='#D3D3D3' ><td colspan='4' height='30' valign='top'><i><div align='center'>Resultados de Busqueda</div></i></td></tr><tr></tr></table>");
			out.print("<div id='resultadosf' style='height: 240px; overflow-y: scroll'></div>");
		}
		
		if(va.equals("cgren")){
			//La idea es  consultar las facturas
			out.print("<table width='100%' border='0' align='center'><tr><td colspan='4' height='30' valign='top'><div align='center' class='style11' id='cabecera2'>Consultar Reporte de Notificaciones</div></td></tr></table>");
			
			//out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='60%'><i><div align='center'>Paciente</div></i></td><td width='30%'><i><div align='center'>Empresa</div></i></td><td width='10%'><i><div align='center'>Admisi�n</div></i></td></tr>");  //
			out.print("<table width='100%' border=0' class='style6' ><tr BGCOLOR='#D3D3D3' ><td colspan='4' height='30' valign='top'><i><div align='center'>Criterios de Busqueda</div></i></td></tr>");
			out.print("<tr></tr><tr><td width='15%'><div align='right'>Reporte No:</div></td><td width='30%'><input name='txtFact' type='text' id='txtFact' size='39' maxlength='100' /></td>");
			out.print("<td width='15%'></td><td width='40%'></td></tr>");
			out.print("<tr><td><div align='right'>Fecha Inicial:</div></td><td><input name='FI' type='text' id='FI' size='10'  onKeyup='masca(this,patron,true,0,0,0,event)' onblur='vfi()'; /></td><td><div align='right'>Fecha Final:</div></td><td><input name='FF' type='text' id='FF' size='10' onKeyup='masca(this,patron,true,0,0,0,event)' onblur='vff()'; /></td></tr><tr></tr></table>");
			
			//out.print("<tr><td><div align='right'>Primer Apellido:</div></td><td><input name='txtCodigoPrestador' type='text' id='txtCodPrestador' size='39' maxlength='50' onkeydown='A(this, event)'/><span class='style8'>*</span></td><td><div align='right'>Segundo Apellido:</div></td><td><select name='listEstado' id='listEstado' style='width: 266px;' onkeydown='A(this, event)'><option value='Activo' selected='selected'>Activo</option><option value='Inactivo'>Inactivo</option></select><span class='style8'>*</span></td></tr></table>");
			out.print("<table width='100%' border='0' class='style6' ><tr><td colspan='4'><div align='center'><input name='btnCrearEntidad' type='button' id='btnCrearEntidad' value='     Consultar Reportes   ' onclick='BuscarReportesn()' /></div></td></tr><tr></tr><tr></tr></table>");
			out.print("<table width='100%' border='0' class='style6' ><tr></tr><tr BGCOLOR='#D3D3D3' ><td colspan='4' height='30' valign='top'><i><div align='center'>Resultados de Busqueda</div></i></td></tr><tr></tr></table>");
			out.print("<div id='resultadosf' style='height: 240px; overflow-y: scroll'></div>");
		}
		
		
		if(va.equals("cgpc")){
			//La idea es  consultar las facturas
			out.print("<table width='100%' border='0' align='center'><tr><td colspan='4' height='30' valign='top'><div align='center' class='style11' id='cabecera2'>Consultar Facturas para Conciliar</div></td></tr></table>");
			
			//out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='60%'><i><div align='center'>Paciente</div></i></td><td width='30%'><i><div align='center'>Empresa</div></i></td><td width='10%'><i><div align='center'>Admisi�n</div></i></td></tr>");  //
			out.print("<table width='100%' border=0' class='style6' ><tr BGCOLOR='#D3D3D3' ><td colspan='4' height='30' valign='top'><i><div align='center'>Criterios de Busqueda</div></i></td></tr>");
			out.print("<tr></tr><tr><td width='15%'><div align='right'>Factura No:</div></td><td width='30%'><input name='txtFact' type='text' id='txtFact' size='39' maxlength='100' /></td>");
			out.print("<td width='15%'><div align='right'>Entidad:</div></td><td width='40%'><select  style='width:269px' name='Ent' id='Ent' ><option value='Seleccione'>Seleccione</option>");
			rs2=mm.Empresas();
			try {
				while(rs2.next()){
				out.print("<option title='"+rs2.getString(2)+"' value="+rs2.getString(1)+">"+rs2.getString(2)+"</option>");
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("</td></tr>");
						
			out.print("<tr><td><div align='right'>Tipo Doc:</div></td><td width='40%'><select  style='width:50px' name='tdoc' id='tdoc' >");
			rs2=mm.TipoDoc();
			try {
				while(rs2.next()){
				out.print("<option value="+rs2.getString(1)+">"+rs2.getString(2)+"</option>");
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("</select>");
			
			out.print(" Numero:<input name='txtDoc' type='text' id='txtDoc'  size='22' maxlength='50' /></td><td><div align='right'>Nombres:</div></td><td><input name='txtNom' type='text' id='txtNom' size='39' maxlength='100'/ ></td></tr>");
			out.print("<tr><td><div align='right'>Primer Apellido:</div></td><td><input name='txtPa' type='text' id='txtPa'  size='39' maxlength='50' /></td><td><div align='right'>Segundo Apellido:</div></td><td><input name='txtSa' type='text' id='txtSa'  size='39' maxlength='100'/ ></td></tr>");
			out.print("<tr><td><div align='right'>Fecha Inicial:</div></td><td><input name='FI' type='text' id='FI' size='10'  onKeyup='masca(this,patron,true,0,0,0,event)' onblur='vfi()'; /></td><td><div align='right'>Fecha Final:</div></td><td><input name='FF' type='text' id='FF' size='10' onKeyup='masca(this,patron,true,0,0,0,event)' onblur='vff()'; /></td></tr><tr></tr></table>");
			
			//out.print("<tr><td><div align='right'>Primer Apellido:</div></td><td><input name='txtCodigoPrestador' type='text' id='txtCodPrestador' size='39' maxlength='50' onkeydown='A(this, event)'/><span class='style8'>*</span></td><td><div align='right'>Segundo Apellido:</div></td><td><select name='listEstado' id='listEstado' style='width: 266px;' onkeydown='A(this, event)'><option value='Activo' selected='selected'>Activo</option><option value='Inactivo'>Inactivo</option></select><span class='style8'>*</span></td></tr></table>");
			out.print("<table width='100%' border='0' class='style6' ><tr><td colspan='4'><div align='center'><input name='btnCrearEntidad' type='button' id='btnCrearEntidad' value='     Consultar Facturas Respondidas  ' onclick='BuscarFactNotificadasPC()' /></div></td></tr><tr></tr><tr></tr></table>");
			out.print("<table width='100%' border='0' class='style6' ><tr></tr><tr BGCOLOR='#D3D3D3' ><td colspan='4' height='30' valign='top'><i><div align='center'>Resultados de Busqueda</div></i></td></tr><tr></tr></table>");
			out.print("<div id='resultadosf' style='height: 240px; overflow-y: scroll'></div>");
			out.print("<table width='100%' border='0' class='style6' ><tr><td><div id='xxxx' align='center'><input type='button' id='revisar'  value='     Iniciar Proceso de Conciliacion  ' onclick='FProcesoCG()' /></div></td></tr></table>");
		}
		
		if(va.equals("cgrc")){
			//La idea es  consultar las facturas
			out.print("<table width='100%' border='0' align='center'><tr><td colspan='4' height='30' valign='top'><div align='center' class='style11' id='cabecera2'>Consultar Reporte de Conciliaciones</div></td></tr></table>");
			
			//out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='60%'><i><div align='center'>Paciente</div></i></td><td width='30%'><i><div align='center'>Empresa</div></i></td><td width='10%'><i><div align='center'>Admisi�n</div></i></td></tr>");  //
			out.print("<table width='100%' border=0' class='style6' ><tr BGCOLOR='#D3D3D3' ><td colspan='4' height='30' valign='top'><i><div align='center'>Criterios de Busqueda</div></i></td></tr>");
			out.print("<tr></tr><tr><td width='15%'><div align='right'>Reporte No:</div></td><td width='30%'><input name='txtFact' type='text' id='txtFact' size='39' maxlength='100' /></td>");
			out.print("<td width='15%'></td><td width='40%'></td></tr>");
			out.print("<tr><td><div align='right'>Fecha Inicial:</div></td><td><input name='FI' type='text' id='FI' size='10'  onKeyup='masca(this,patron,true,0,0,0,event)' onblur='vfi()'; /></td><td><div align='right'>Fecha Final:</div></td><td><input name='FF' type='text' id='FF' size='10' onKeyup='masca(this,patron,true,0,0,0,event)' onblur='vff()'; /></td></tr><tr></tr></table>");
			
			//out.print("<tr><td><div align='right'>Primer Apellido:</div></td><td><input name='txtCodigoPrestador' type='text' id='txtCodPrestador' size='39' maxlength='50' onkeydown='A(this, event)'/><span class='style8'>*</span></td><td><div align='right'>Segundo Apellido:</div></td><td><select name='listEstado' id='listEstado' style='width: 266px;' onkeydown='A(this, event)'><option value='Activo' selected='selected'>Activo</option><option value='Inactivo'>Inactivo</option></select><span class='style8'>*</span></td></tr></table>");
			out.print("<table width='100%' border='0' class='style6' ><tr><td colspan='4'><div align='center'><input name='btnCrearEntidad' type='button' id='btnCrearEntidad' value='     Consultar Reportes   ' onclick='BuscarReportesc()' /></div></td></tr><tr></tr><tr></tr></table>");
			out.print("<table width='100%' border='0' class='style6' ><tr></tr><tr BGCOLOR='#D3D3D3' ><td colspan='4' height='30' valign='top'><i><div align='center'>Resultados de Busqueda</div></i></td></tr><tr></tr></table>");
			out.print("<div id='resultadosf' style='height: 240px; overflow-y: scroll'></div>");
		}


		if(va.equals("cgrn")){
			//La idea es  consultar las facturas
			out.print("<table width='100%' border='0' align='center'><tr><td colspan='4' height='30' valign='top'><div align='center' class='style11' id='cabecera2'>Facturas en proceso de Glosas</div></td></tr></table>");
			
			//out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='60%'><i><div align='center'>Paciente</div></i></td><td width='30%'><i><div align='center'>Empresa</div></i></td><td width='10%'><i><div align='center'>Admisi�n</div></i></td></tr>");  //
			out.print("<table width='100%' border=0' class='style6' ><tr BGCOLOR='#D3D3D3' ><td colspan='4' height='30' valign='top'><i><div align='center'>Criterios de Busqueda</div></i></td></tr>");
			out.print("<tr></tr><tr><td width='15%'><div align='right'>Factura No:</div></td><td width='30%'><input name='txtFact' type='text' id='txtFact' size='39' maxlength='100' /></td>");
			out.print("<td width='15%'><div align='right'>Entidad:</div></td><td width='40%'><select  style='width:269px' name='Ent' id='Ent' ><option value='Seleccione'>Seleccione</option>");
			rs2=mm.Empresas();
			try {
				while(rs2.next()){
				out.print("<option title='"+rs2.getString(2)+"' value="+rs2.getString(1)+">"+rs2.getString(2)+"</option>");
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("</td></tr>");
						
			out.print("<tr><td><div align='right'>Tipo Doc:</div></td><td width='40%'><select  style='width:50px' name='tdoc' id='tdoc' >");
			rs2=mm.TipoDoc();
			try {
				while(rs2.next()){
				out.print("<option value="+rs2.getString(1)+">"+rs2.getString(2)+"</option>");
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("</select>");
			
			out.print(" Numero:<input name='txtDoc' type='text' id='txtDoc'  size='22' maxlength='50' /></td><td><div align='right'>Nombres:</div></td><td><input name='txtNom' type='text' id='txtNom' size='39' maxlength='100'/ ></td></tr>");
			out.print("<tr><td><div align='right'>Primer Apellido:</div></td><td><input name='txtPa' type='text' id='txtPa'  size='39' maxlength='50' /></td><td><div align='right'>Segundo Apellido:</div></td><td><input name='txtSa' type='text' id='txtSa'  size='39' maxlength='100'/ ></td></tr>");
			out.print("<tr><td><div align='right'>Fecha Inicial:</div></td><td><input name='FI' type='text' id='FI' size='10'  onKeyup='masca(this,patron,true,0,0,0,event)' onblur='vfi()'; /></td><td><div align='right'>Fecha Final:</div></td><td><input name='FF' type='text' id='FF' size='10' onKeyup='masca(this,patron,true,0,0,0,event)' onblur='vff()'; /></td></tr><tr></tr></table>");
			
			//out.print("<tr><td><div align='right'>Primer Apellido:</div></td><td><input name='txtCodigoPrestador' type='text' id='txtCodPrestador' size='39' maxlength='50' onkeydown='A(this, event)'/><span class='style8'>*</span></td><td><div align='right'>Segundo Apellido:</div></td><td><select name='listEstado' id='listEstado' style='width: 266px;' onkeydown='A(this, event)'><option value='Activo' selected='selected'>Activo</option><option value='Inactivo'>Inactivo</option></select><span class='style8'>*</span></td></tr></table>");
			out.print("<table width='100%' border='0' class='style6' ><tr><td colspan='4'><div align='center'><input name='btnCrearEntidad' type='button' id='btnCrearEntidad' value='     Consultar Facturas   ' onclick='BuscarFactNotificadasrn()' /></div></td></tr><tr></tr><tr></tr></table>");
			out.print("<table width='100%' border='0' class='style6' ><tr></tr><tr BGCOLOR='#D3D3D3' ><td colspan='4' height='30' valign='top'><i><div align='center'>Resultados de Busqueda</div></i></td></tr><tr></tr></table>");
			out.print("<div id='resultadosf' style='height: 240px; overflow-y: scroll'></div>");
			out.print("<table width='100%' border='0' class='style6' ><tr><td><div id='xxxx' align='center'><input type='button' id='revisar'  value='     Generar Reporte Notificacion   ' onclick='ReportedeNotificacion()' /></div></td></tr></table>");
		}
		
		if(va.equals("showGlosasNotificadas")){
			String sql="";
			//String v1=req.getParameter("v1");
			if((v1==null)||(v1.equals(""))){v1="";}else{sql=sql+" AND n.consecutivo='"+v1+"'";}
			//String v2=req.getParameter("v2");
			if(v2.equals("Seleccione")){v2="";}else{sql=sql+" AND ef.cod_eps="+v2;}
			//String v3=req.getParameter("v3");
			if(v3==null){v3="";}
			//String v4=req.getParameter("v4");
			if((v4==null)||(v4.equals(""))){v4="";}else{sql=sql+" AND p.tipo_documento='"+v3+"' AND p.numero_documento="+v4;}
			//String v5=req.getParameter("v5");
			if((v5==null)||(v5.equals(""))){v5="";}else{sql=sql+" AND p.nombre like '"+v5+"%'";}
			//String v6=req.getParameter("v6");
			if((v6==null)||(v6.equals(""))){v6="";}else{sql=sql+" AND p.primer_apellido like '"+v6+"%'";}
			//String v7=req.getParameter("v7");
			if((v7==null)||(v7.equals(""))){v7="";}else{sql=sql+" AND p.segundo_apellido like '"+v7+"%'";}
			//String v8=req.getParameter("v8");
			//String v9=req.getParameter("v9");
			
			if((v8==null)||(v8.equals(""))||(v9.equals(""))||(v9==null)){v8="";v9="";}
			else{
			String dv8=v8.substring(0, 2);
			String mv8=v8.substring(3, 5);
			String av8=v8.substring(6, 10);
			String fv8=av8+"-"+mv8+"-"+dv8;
			
			String dv9=v9.substring(0, 2);
			String mv9=v9.substring(3, 5);
			String av9=v9.substring(6, 10);
			String fv9=av9+"-"+mv9+"-"+dv9;
			
			sql=sql+" AND ef.fecha BETWEEN '"+fv8+"' AND '"+fv9+"' ";}
			
			System.out.println("Buscar Notifocaciones: "+sql);
			int crs=0;		
			rs2=mr.ConsultarNotificaciones(sql);
			try {
				while(rs2.next()){
					if(crs==0){
					out.print("<table width='100%' border='0' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='10%'><i><div align='center'>No. de Factura</div></i></td><td width='25%'><i><div align='center'>Empresa</div></i></td><td width='10%'><i><div align='center'>Fecha</div></i></td><td width='10%'><i><div align='center'>Documento</div></i></td><td width='40%'><i><div align='center'>Nombres</div></i></td><td width='5%'><i><div align='center'>Seleccionar<input type='checkbox' name='all' id='all' onclick='todos()'></div></i></td></tr>	");  //
					}
				out.print("<tr><td>"+rs2.getString(1)+"</td><td>"+rs2.getString(2)+"</td><td>"+rs2.getString(8)+"</td><td>"+rs2.getString(3)+" "+rs2.getString(4)+"</td><td> "+rs2.getString(5)+" "+rs2.getString(6)+" "+rs2.getString(7)+"</td><td><div align='center'><input type='checkbox' name='checkbox' id='carga' value='"+rs2.getString(10)+"' ></div></td></tr>");
				crs++;
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			if(crs==0){out.print("No hay resultados para sus criterios de busqueda.");}
		}
		
		
		
		if(va.equals("showGlosasNotificadasr")){
			String sql="";
			//String v1=req.getParameter("v1");
			if((v1==null)||(v1.equals(""))){v1="";}else{sql=sql+" AND n.consecutivo='"+v1+"'";}
			//String v2=req.getParameter("v2");
			if(v2.equals("Seleccione")){v2="";}else{sql=sql+" AND ef.cod_eps="+v2;}
			//String v3=req.getParameter("v3");
			if(v3==null){v3="";}
			//String v4=req.getParameter("v4");
			if((v4==null)||(v4.equals(""))){v4="";}else{sql=sql+" AND p.tipo_documento='"+v3+"' AND p.numero_documento="+v4;}
			//String v5=req.getParameter("v5");
			if((v5==null)||(v5.equals(""))){v5="";}else{sql=sql+" AND p.nombre like '"+v5+"%'";}
			//String v6=req.getParameter("v6");
			if((v6==null)||(v6.equals(""))){v6="";}else{sql=sql+" AND p.primer_apellido like '"+v6+"%'";}
			//String v7=req.getParameter("v7");
			if((v7==null)||(v7.equals(""))){v7="";}else{sql=sql+" AND p.segundo_apellido like '"+v7+"%'";}
			//String v8=req.getParameter("v8");
			//String v9=req.getParameter("v9");
			
			if((v8==null)||(v8.equals(""))||(v9.equals(""))||(v9==null)){v8="";v9="";}
			else{
			String dv8=v8.substring(0, 2);
			String mv8=v8.substring(3, 5);
			String av8=v8.substring(6, 10);
			String fv8=av8+"-"+mv8+"-"+dv8;
			
			String dv9=v9.substring(0, 2);
			String mv9=v9.substring(3, 5);
			String av9=v9.substring(6, 10);
			String fv9=av9+"-"+mv9+"-"+dv9;
			
			sql=sql+" AND ef.fecha BETWEEN '"+fv8+"' AND '"+fv9+"' ";}
			
			System.out.println("Buscar Notifocaciones: "+sql);
			int crs=0;		
			rs2=mr.ConsultarNotificacionesr(sql);
			try {
				while(rs2.next()){
					if(crs==0){
					out.print("<table width='100%' border='0' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='10%'><i><div align='center'>No. de Factura</div></i></td><td width='25%'><i><div align='center'>Empresa</div></i></td><td width='10%'><i><div align='center'>Fecha</div></i></td><td width='10%'><i><div align='center'>Documento</div></i></td><td width='40%'><i><div align='center'>Nombres</div></i></td><td width='5%'><i><div align='center'>Seleccionar<input type='checkbox' name='all' id='all' onclick='todosr()'></div></i></td></tr>	");  //
					}
				out.print("<tr><td>"+rs2.getString(1)+"</td><td>"+rs2.getString(2)+"</td><td>"+rs2.getString(8)+"</td><td>"+rs2.getString(3)+" "+rs2.getString(4)+"</td><td> "+rs2.getString(5)+" "+rs2.getString(6)+" "+rs2.getString(7)+"</td><td><div align='center'><input type='checkbox' name='checkg' id='carga' value='"+rs2.getString(10)+"' ></div></td></tr>");
				crs++;
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			if(crs==0){out.print("No hay resultados para sus criterios de busqueda.");}
		}
		
		if(va.equals("showGlosasNotificadasf")){
			String sql="";
			//String v1=req.getParameter("v1");
			if((v1==null)||(v1.equals(""))){v1="";}else{sql=sql+" AND n.consecutivo='"+v1+"'";}
			//String v2=req.getParameter("v2");
			if(v2.equals("Seleccione")){v2="";}else{sql=sql+" AND ef.cod_eps="+v2;}
			//String v3=req.getParameter("v3");
			if(v3==null){v3="";}
			//String v4=req.getParameter("v4");
			if((v4==null)||(v4.equals(""))){v4="";}else{sql=sql+" AND p.tipo_documento='"+v3+"' AND p.numero_documento="+v4;}
			//String v5=req.getParameter("v5");
			if((v5==null)||(v5.equals(""))){v5="";}else{sql=sql+" AND p.nombre like '"+v5+"%'";}
			//String v6=req.getParameter("v6");
			if((v6==null)||(v6.equals(""))){v6="";}else{sql=sql+" AND p.primer_apellido like '"+v6+"%'";}
			//String v7=req.getParameter("v7");
			if((v7==null)||(v7.equals(""))){v7="";}else{sql=sql+" AND p.segundo_apellido like '"+v7+"%'";}
			//String v8=req.getParameter("v8");
			//String v9=req.getParameter("v9");
			
			if((v8==null)||(v8.equals(""))||(v9.equals(""))||(v9==null)){v8="";v9="";}
			else{
			String dv8=v8.substring(0, 2);
			String mv8=v8.substring(3, 5);
			String av8=v8.substring(6, 10);
			String fv8=av8+"-"+mv8+"-"+dv8;
			
			String dv9=v9.substring(0, 2);
			String mv9=v9.substring(3, 5);
			String av9=v9.substring(6, 10);
			String fv9=av9+"-"+mv9+"-"+dv9;
			
			sql=sql+" AND ef.fecha BETWEEN '"+fv8+"' AND '"+fv9+"' ";}
			
			System.out.println("Buscar Notifocaciones: "+sql);
			int crs=0;		
			rs2=mr.ConsultarNotificacionesf(sql);
			try {
				while(rs2.next()){
					if(crs==0){
					out.print("<table width='100%' border='0' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='8%'><i><div align='center'>No. de Factura</div></i></td><td width='20%'><i><div align='center'>Empresa</div></i></td><td width='7%'><i><div align='center'>Fecha</div></i></td><td width='10%'><i><div align='center'>Documento</div></i></td><td width='20%'><i><div align='center'>Nombres</div></i></td><td width='8%'><i><div align='center'>Valor Factura</div></i></td><td width='8%'><i><div align='center'>Valor Glosado</div></i></td><td width='8%'><i><div align='center'>Valor Aceptado</div></i></td><td width='8%'><i><div align='center'>Valor a Aplicar</div></i></td><td width='8%'><div align='center'><input type='checkbox' name='all' id='all' onclick='todos()'></div></td></tr>	");  //
					}
				out.print("<tr><td>"+rs2.getString(1)+"</td><td>"+rs2.getString(2)+"</td><td>"+rs2.getString(8)+"</td><td>"+rs2.getString(3)+" "+rs2.getString(4)+"</td><td> "+rs2.getString(5)+" "+rs2.getString(6)+" "+rs2.getString(7)+"</td><td>"+rs2.getString(13)+"</td><td>"+rs2.getString(14)+"</td><td>"+rs2.getString(15)+"</td><td><input type='text' id='vaplica"+crs+"'  value='"+rs2.getString(15)+"' style='width:95%'/></td><td><div align='center'><input type='checkbox' name='checkbox' id='"+crs+"' value='"+rs2.getString(12)+"' ></div></td></tr>");
				crs++;
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			if(crs==0){out.print("No hay resultados para sus criterios de busqueda.");}
		}
		
		
		
		if(va.equals("showGlosasReportes")){
			String sql="";
			//String v1=req.getParameter("v1");
			if((v1==null)||(v1.equals(""))){v1="";}else{sql=sql+" AND codigo='"+v1+"'";}
			//SELECT * FROM fact_glosasenviadas WHERE codigo!=0 AND codigo=7 AND fecha
			if((v8==null)||(v8.equals(""))||(v9.equals(""))||(v9==null)){v8="";v9="";}
			else{
			String dv8=v8.substring(0, 2);
			String mv8=v8.substring(3, 5);
			String av8=v8.substring(6, 10);
			String fv8=av8+"-"+mv8+"-"+dv8;
			
			String dv9=v9.substring(0, 2);
			String mv9=v9.substring(3, 5);
			String av9=v9.substring(6, 10);
			String fv9=av9+"-"+mv9+"-"+dv9;
			
			sql=sql+" AND fecha BETWEEN '"+fv8+"' AND '"+fv9+"' ";}
			
			System.out.println("Buscar Notifocaciones: "+sql);
			int crs=0;		
			rs2=mr.ConsultarReportesG(sql);
			try {
				while(rs2.next()){
					if(crs==0){
					out.print("<table width='100%' border='0' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='10%'><i><div align='center'>No. de Reporte</div></i></td><td width='10%'><i><div align='center'>Fecha</div></i></td><td width='80%'><i><div align='center'>Empresa</div></i></td></tr>	");  //
					}
				out.print("<tr onclick='ImprimirGlosareport("+rs2.getString(1)+")'><td>"+rs2.getString(1)+"</td><td>"+rs2.getString(2)+"</td><td>"+rs2.getString(4)+"</td></tr>");
				crs++;
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			if(crs==0){out.print("No hay resultados para sus criterios de busqueda.");}
		}
		
		
		if(va.equals("showGlosasReportesn")){
			String sql="";
			//String v1=req.getParameter("v1");
			if((v1==null)||(v1.equals(""))){v1="";}else{sql=sql+" AND codigo='"+v1+"'";}
			//SELECT * FROM fact_glosasenviadas WHERE codigo!=0 AND codigo=7 AND fecha
			if((v8==null)||(v8.equals(""))||(v9.equals(""))||(v9==null)){v8="";v9="";}
			else{
			String dv8=v8.substring(0, 2);
			String mv8=v8.substring(3, 5);
			String av8=v8.substring(6, 10);
			String fv8=av8+"-"+mv8+"-"+dv8;
			
			String dv9=v9.substring(0, 2);
			String mv9=v9.substring(3, 5);
			String av9=v9.substring(6, 10);
			String fv9=av9+"-"+mv9+"-"+dv9;
			
			sql=sql+" AND fecha BETWEEN '"+fv8+"' AND '"+fv9+"' ";}
			
			System.out.println("Buscar Notifocaciones: "+sql);
			int crs=0;		
			rs2=mr.ConsultarReportesGN(sql);
			try {
				while(rs2.next()){
					if(crs==0){
					out.print("<table width='100%' border='0' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='25%'><i><div align='center'>No. de Reporte</div></i></td><td width='25%'><i><div align='center'>Fecha</div></i></td><td width='25%'><i><div align='center'>Valor Facturado</div></i></td><td width='25%'><i><div align='center'>Valor Glosado</div></i></td></tr>	");  //
					}
				out.print("<tr onclick='ImprimirGlosareportN("+rs2.getString(1)+")'><td>"+rs2.getString(1)+"</td><td>"+rs2.getString(2)+"</td><td>"+rs2.getString(4)+"</td><td>"+rs2.getString(5)+"</td></tr>");
				crs++;
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			if(crs==0){out.print("No hay resultados para sus criterios de busqueda.");}
		}
		
		
		if(va.equals("showGlosasNotificadasPC")){
			String sql="";
			//String v1=req.getParameter("v1");
			if((v1==null)||(v1.equals(""))){v1="";}else{sql=sql+" AND n.consecutivo='"+v1+"'";}
			//String v2=req.getParameter("v2");
			if(v2.equals("Seleccione")){v2="";}else{sql=sql+" AND ef.cod_eps="+v2;}
			//String v3=req.getParameter("v3");
			if(v3==null){v3="";}
			//String v4=req.getParameter("v4");
			if((v4==null)||(v4.equals(""))){v4="";}else{sql=sql+" AND p.tipo_documento='"+v3+"' AND p.numero_documento="+v4;}
			//String v5=req.getParameter("v5");
			if((v5==null)||(v5.equals(""))){v5="";}else{sql=sql+" AND p.nombre like '"+v5+"%'";}
			//String v6=req.getParameter("v6");
			if((v6==null)||(v6.equals(""))){v6="";}else{sql=sql+" AND p.primer_apellido like '"+v6+"%'";}
			//String v7=req.getParameter("v7");
			if((v7==null)||(v7.equals(""))){v7="";}else{sql=sql+" AND p.segundo_apellido like '"+v7+"%'";}
			//String v8=req.getParameter("v8");
			//String v9=req.getParameter("v9");
			
			if((v8==null)||(v8.equals(""))||(v9.equals(""))||(v9==null)){v8="";v9="";}
			else{
			String dv8=v8.substring(0, 2);
			String mv8=v8.substring(3, 5);
			String av8=v8.substring(6, 10);
			String fv8=av8+"-"+mv8+"-"+dv8;
			
			String dv9=v9.substring(0, 2);
			String mv9=v9.substring(3, 5);
			String av9=v9.substring(6, 10);
			String fv9=av9+"-"+mv9+"-"+dv9;
			
			sql=sql+" AND ef.fecha BETWEEN '"+fv8+"' AND '"+fv9+"' ";}
			
			System.out.println("Buscar Notifocaciones: "+sql);
			int crs=0;		
			rs2=mr.ConsultarNotificacionesPC(sql);
			try {
				while(rs2.next()){
					crs++;
					if(crs==1){
					out.print("<table width='100%' border='0' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='8%'><i><div align='center'>No. de Factura</div></i></td><td width='20%'><i><div align='center'>Empresa</div></i></td><td width='7%'><i><div align='center'>Fecha</div></i></td><td width='10%'><i><div align='center'>Documento</div></i></td><td width='20%'><i><div align='center'>Nombres</div></i></td><td width='8%'><i><div align='center'>Valor Factura</div></i></td><td width='8%'><i><div align='center'>Valor Glosado</div></i></td><td width='8%'><i><div align='center'>Valor a Aplicar</div></i></td><td width='8%'><i><div align='center'>Porcentaje %</div></i></td><td width='8%'><div align='center'><input type='checkbox' name='all' id='all' onclick='todos()'></div></td></tr>	");  //
					}
				out.print("<tr><td>"+rs2.getString(1)+"</td><td>"+rs2.getString(2)+"</td><td>"+rs2.getString(8)+"</td><td>"+rs2.getString(3)+" "+rs2.getString(4)+"</td><td> "+rs2.getString(5)+" "+rs2.getString(6)+" "+rs2.getString(7)+"</td><td>"+rs2.getString(13)+"<input type='hidden' id='vfact"+crs+"' value='"+rs2.getString(14)+"'/></td><td>"+rs2.getString(14)+"</td><td><input type='text' id='vaplica"+crs+"' value='0' style='width:95%'/></td><td><input type='text' id='vaplicaporcentaje"+crs+"' value='0' style='width:95%' onkeyup='porcg(this,"+crs+");'/></td><td><div align='center'><input type='checkbox' name='checkbox' id='carga"+crs+"' value='"+rs2.getString(12)+"' ></div></td></tr>");
				
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			if(crs==0){out.print("No hay resultados para sus criterios de busqueda.");}
		}
		
		
		if(va.equals("showGlosasNotificadasrn")){
			String sql="";
			//String v1=req.getParameter("v1");
			if((v1==null)||(v1.equals(""))){v1="";}else{sql=sql+" AND n.consecutivo='"+v1+"'";}
			//String v2=req.getParameter("v2");
			if(v2.equals("Seleccione")){v2="";}else{sql=sql+" AND ef.cod_eps="+v2;}
			//String v3=req.getParameter("v3");
			if(v3==null){v3="";}
			//String v4=req.getParameter("v4");
			if((v4==null)||(v4.equals(""))){v4="";}else{sql=sql+" AND p.tipo_documento='"+v3+"' AND p.numero_documento="+v4;}
			//String v5=req.getParameter("v5");
			if((v5==null)||(v5.equals(""))){v5="";}else{sql=sql+" AND p.nombre like '"+v5+"%'";}
			//String v6=req.getParameter("v6");
			if((v6==null)||(v6.equals(""))){v6="";}else{sql=sql+" AND p.primer_apellido like '"+v6+"%'";}
			//String v7=req.getParameter("v7");
			if((v7==null)||(v7.equals(""))){v7="";}else{sql=sql+" AND p.segundo_apellido like '"+v7+"%'";}
			//String v8=req.getParameter("v8");
			//String v9=req.getParameter("v9");
			
			if((v8==null)||(v8.equals(""))||(v9.equals(""))||(v9==null)){v8="";v9="";}
			else{
			String dv8=v8.substring(0, 2);
			String mv8=v8.substring(3, 5);
			String av8=v8.substring(6, 10);
			String fv8=av8+"-"+mv8+"-"+dv8;
			
			String dv9=v9.substring(0, 2);
			String mv9=v9.substring(3, 5);
			String av9=v9.substring(6, 10);
			String fv9=av9+"-"+mv9+"-"+dv9;
			
			sql=sql+" AND ef.fecha BETWEEN '"+fv8+"' AND '"+fv9+"' ";}
			
			System.out.println("Buscar Notifocaciones: "+sql);
			int crs=0;		
			rs2=mr.ConsultarNotificacionesrn(sql);
			try {
				while(rs2.next()){
					if(crs==0){
					out.print("<table width='100%' border='0' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='10%'><i><div align='center'>No. de Factura</div></i></td><td width='25%'><i><div align='center'>Empresa</div></i></td><td width='10%'><i><div align='center'>Fecha</div></i></td><td width='10%'><i><div align='center'>Documento</div></i></td><td width='40%'><i><div align='center'>Nombres</div></i></td><td width='5%'><i><div align='center'>Seleccionar<input type='checkbox' name='all' id='all' onclick='todosr()'></div></i></td></tr>	");  //
					}
				out.print("<tr><td>"+rs2.getString(1)+"</td><td>"+rs2.getString(2)+"</td><td>"+rs2.getString(8)+"</td><td>"+rs2.getString(3)+" "+rs2.getString(4)+"</td><td> "+rs2.getString(5)+" "+rs2.getString(6)+" "+rs2.getString(7)+"</td><td><div align='center'><input type='checkbox' name='checkg' id='carga' value='"+rs2.getString(12)+"' ></div></td></tr>");
				crs++;
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			if(crs==0){out.print("No hay resultados para sus criterios de busqueda.");}
		}
		


	if(va.equals("showGlosasReportesc")){
			String sql="";
			//String v1=req.getParameter("v1");
			if((v1==null)||(v1.equals(""))){v1="";}else{sql=sql+" AND codigo='"+v1+"'";}
			//SELECT * FROM fact_glosasenviadas WHERE codigo!=0 AND codigo=7 AND fecha
			if((v8==null)||(v8.equals(""))||(v9.equals(""))||(v9==null)){v8="";v9="";}
			else{
			String dv8=v8.substring(0, 2);
			String mv8=v8.substring(3, 5);
			String av8=v8.substring(6, 10);
			String fv8=av8+"-"+mv8+"-"+dv8;
			
			String dv9=v9.substring(0, 2);
			String mv9=v9.substring(3, 5);
			String av9=v9.substring(6, 10);
			String fv9=av9+"-"+mv9+"-"+dv9;
			
			sql=sql+" AND fecha BETWEEN '"+fv8+"' AND '"+fv9+"' ";}
			
			System.out.println("Buscar Notifocaciones: "+sql);
			int crs=0;		
			rs2=mr.ConsultarReportesGC(sql);
			try {
				while(rs2.next()){
					if(crs==0){
					out.print("<table width='100%' border='0' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='25%'><i><div align='center'>No. de Reporte</div></i></td><td width='25%'><i><div align='center'>Fecha</div></i></td><td width='25%'><i><div align='center'>Valor Facturado</div></i></td><td width='25%'><i><div align='center'>Valor Glosado</div></i></td></tr>	");  //
					}
				out.print("<tr onclick='ImprimirGlosareportC("+rs2.getString(1)+")'><td>"+rs2.getString(1)+"</td><td>"+rs2.getString(2)+"</td><td>"+rs2.getString(4)+"</td><td>"+rs2.getString(5)+"</td></tr>");
				crs++;
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			if(crs==0){out.print("No hay resultados para sus criterios de busqueda.");}
		}
		


		if(va.equals("cg2")){
			//out.print("<table width='100%' border='0' align='center'><tr><td colspan='4' valign='top'><div align='center' class='style11' id='cabecera2'>Notificar Glosas</div></td></tr></table>");
			out.print("<table width='100%' border='0'><tr></tr><tr><td  width='92%'></td><td  width='8%'  BGCOLOR='#808080' ><i><div align='center'><a  href='#'onclick='ReportedeGlosa(1)'><font color='white'>Generar Reporte</font></a></div></i></td></tr></table>");
				
			int it=0;	
			String cs="";
			String ns="";
			int vt=0;
			String vtg="0";
			
			System.out.println("v1: "+v1);
			///////////////////////////
			//String V[] = new String[200];
			int i1=0;
			StringTokenizer elementos;  
			System.out.println("datos: "+v1);
			elementos = new StringTokenizer(v1,"|"); 
			System.out.println("elementos: "+elementos);
			while(elementos.hasMoreTokens()){ 
				//V[i1] = elementos.nextToken();  
				//System.out.println("V["+i1+"]: "+V[i1]);
				//i1++;
				String x=elementos.nextToken(); 
				///////////////////////////
				String estadon="";
				String color="";
				String title="";
				int cd=0;
				rs2=mr.DetalleFactura(x);
				try {
					while(rs2.next()){
						
						if(cd==0){
							out.print("<table width='100%' border='0'><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr><td  width='20%'  BGCOLOR='#808080' ><i><div align='left'><font color='white'>Factura: "+rs2.getString(10)+"</font><input type='checkbox' name='checkg' id='facturag' value='"+x+"' checked='true'></div></i></td><td  width='80%'></td></tr><tr></tr></table>");
							out.print("<table width='100%' border='0' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='3%'><i><div align='center'>Item</div></i></td><td width='7%'><i><div align='center'>Cod. Programa</div></i></td><td width='50%'><i><div align='center'>Nombre Programa/Servicio</div></i></td><td width='11%'><i><div align='center'>Clase Servicio</div></i></td><td width='5%'><i><div align='center'>Cantidad</div></i></td><td width='7%'><i><div align='center'>Valor Unitario</div></i></td><td width='7%'><i><div align='center'>Valor Total</div></i></td><td width='7%'><i><div align='center'>Valor Glosado</div></i></td><td width='3%'><i><div align='center'>Estado</div></i></td></tr>	");  //
						}
						
						int sw=0;
						vtg="0";
						
						if(rs2.getString(5)==null){cs="";}else{cs=" - "+rs2.getString(5);}
						if(rs2.getString(6)==null){ns="";}else{ns=" - "+rs2.getString(6);}
						vt=Integer.parseInt(rs2.getString(9))*Integer.parseInt(rs2.getString(8));
						
						/*
						*/
						
						rs3=mr.DetalleFacturaGlosa(rs2.getString(1));
						if(rs3.next()){
							it++;
							vtg=rs3.getString(1);
							sw=1;
							if(rs3.getString(2).equals("0")){estadon="P"; color="red"; 	title="Pendiente";
							out.print("<tr name='tdd' onclick='Revisatd(this)'><td>"+it+"</td><td>"+rs2.getString(3)+""+cs+"</td><td><a  href='#'onclick='RevisarNotificacion2(&quot;"+v1+"&quot;,&quot;"+rs2.getString(1)+"&quot;)'>"+rs2.getString(4)+""+ns+"</a></td><td>"+rs2.getString(7)+"</td><td>"+rs2.getString(8)+"</td><td>"+rs2.getString(9)+"</td><td>"+vt+"</td><td>"+vtg+"</td><td><font color='"+color+"' title='"+title+"'>"+estadon+"</font></td></tr>");
							}
							if(rs3.getString(2).equals("1")){estadon="S"; color="green";title="Subsanada";
							out.print("<tr name='tdd' onclick='Revisatd(this)'><td>"+it+"</td><td><font color='gray'>"+rs2.getString(3)+""+cs+"</font></td><td><a  href='#'onclick='RevisarNotificacion2(&quot;"+v1+"&quot;,&quot;"+rs2.getString(1)+"&quot;)'><font color='gray'>"+rs2.getString(4)+""+ns+"</font></a></td><td><font color='gray'>"+rs2.getString(7)+"</font></td><td><font color='gray'>"+rs2.getString(8)+"</font></td><td><font color='gray'>"+rs2.getString(9)+"</font></td><td><font color='gray'>"+vt+"</font></td><td><font color='gray'>"+vtg+"</font></td><td><font color='"+color+"' title='"+title+"'>"+estadon+"</font></td></tr>");
							}
							if(rs3.getString(2).equals("2")){estadon="A"; color="green"; title="Aceptada";
							out.print("<tr name='tdd' onclick='Revisatd(this)'><td>"+it+"</td><td><font color='gray'>"+rs2.getString(3)+""+cs+"</font></td><td><a  href='#'onclick='RevisarNotificacion2(&quot;"+v1+"&quot;,&quot;"+rs2.getString(1)+"&quot;)'><font color='gray'>"+rs2.getString(4)+""+ns+"</font></a></td><td><font color='gray'>"+rs2.getString(7)+"</font></td><td><font color='gray'>"+rs2.getString(8)+"</font></td><td><font color='gray'>"+rs2.getString(9)+"</font></td><td><font color='gray'>"+vt+"</font></td><td><font color='gray'>"+vtg+"</font></td><td><font color='"+color+"' title='"+title+"'>"+estadon+"</font></td></tr>");
							}
							if(rs3.getString(2).equals("3")){estadon="R"; color="green";title="Rechazada";
							out.print("<tr name='tdd' onclick='Revisatd(this)'><td>"+it+"</td><td><font color='gray'>"+rs2.getString(3)+""+cs+"</font></td><td><a  href='#'onclick='RevisarNotificacion2(&quot;"+v1+"&quot;,&quot;"+rs2.getString(1)+"&quot;)'><font color='gray'>"+rs2.getString(4)+""+ns+"</font></a></td><td><font color='gray'>"+rs2.getString(7)+"</font></td><td><font color='gray'>"+rs2.getString(8)+"</font></td><td><font color='gray'>"+rs2.getString(9)+"</font></td><td><font color='gray'>"+vt+"</font></td><td><font color='gray'>"+vtg+"</font></td><td><font color='"+color+"' title='"+title+"'>"+estadon+"</font></td></tr>");
							}
							if(rs3.getString(2).equals("5")){estadon="C"; color="green";title="Conciliada";
							out.print("<tr name='tdd' onclick='Revisatd(this)'><td>"+it+"</td><td><font color='gray'>"+rs2.getString(3)+""+cs+"</font></td><td><a  href='#'onclick='RevisarNotificacion2(&quot;"+v1+"&quot;,&quot;"+rs2.getString(1)+"&quot;)'><font color='gray'>"+rs2.getString(4)+""+ns+"</font></a></td><td><font color='gray'>"+rs2.getString(7)+"</font></td><td><font color='gray'>"+rs2.getString(8)+"</font></td><td><font color='gray'>"+rs2.getString(9)+"</font></td><td><font color='gray'>"+vt+"</font></td><td><font color='gray'>"+vtg+"</font></td><td><font color='"+color+"' title='"+title+"'>"+estadon+"</font></td></tr>");
							}
							
						}rs3.getStatement().getConnection().close();
						/*if(sw==0){
						out.print("<tr><td>"+it+"</td><td>"+rs2.getString(3)+""+cs+"</td><td><a  href='#'onclick='NotificarCargue(&quot;"+v1+"&quot;,&quot;"+v2+"&quot;,&quot;"+v3+"&quot;,&quot;"+rs2.getString(1)+"&quot;)'>"+rs2.getString(4)+""+ns+"</a></td><td>"+rs2.getString(7)+"</td><td>"+rs2.getString(8)+"</td><td>"+rs2.getString(9)+"</td><td>"+vt+"</td><td>"+vtg+"</td></tr>");
						}*/
						cd++;
					}
					rs2.getStatement().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
			}
			out.print("</table>");
		}
		
		
		if(va.equals("cgf2")){
				
			int it=0;	
			String cs="";
			String ns="";
			int vt=0;
			String vtg="0";
			
			StringTokenizer elementosval;

			elementosval = new StringTokenizer(v3,"|"); 
			String V[] = new String[200];
			int i2=0;
			while(elementosval.hasMoreTokens()){ 
			  V[i2] = elementosval.nextToken();  
			  System.out.println("V[i2]: "+V[i2]);
			  i2++;
			}
			
			
			System.out.println("v1: "+v1);
			///////////////////////////
			//String V[] = new String[200];
			int i1=0;
			StringTokenizer elementos;  
			System.out.println("datos: "+v1);
			elementos = new StringTokenizer(v1,"|"); 
			System.out.println("elementoghjkgks: "+elementos);
			while(elementos.hasMoreTokens()){ 
				//V[i1] = elementos.nextToken();  
				//System.out.println("V["+i1+"]: "+V[i1]);
				//i1++;
				String x=elementos.nextToken(); 
				///////////////////////////
						
				rs2=mr.ConsultarValoresaCerrar(x);
				try {
					while(rs2.next()){
						System.out.println("xxxxxx: "+rs2.getString(4));
						
						if(Integer.parseInt(V[i1])<=rs2.getInt(3)){
						//if(!rs2.getString(4).equals("0")){
							
							
							
							System.out.println("elffffffffffff: "+elementos);
							mr.FinalizarConciliacion(x);//coloca en estado 3 la glosa como terminada
 
							//rs2.getString(1) consecutivo
							//rs2.getString(2) valor factura
							String Valor_Glosa=V[i1];//rs2.getString(4);
							String Observacion=rs2.getString("Concepto");
							//v2 usuario
							//fechacjmysql
							//fechacjmysql
							//hra
							//Debo colocar estado 3 en glosas
							//*********************************************
							//if(tipo.equals("1")){
								/*
								1. buscar si la factura esta en cont_facturas
								2. sino esta crear la factura
								3. verificar el saldo de la factura, valor original-pagos o notas c
								4. si el saldo de la factura es menor a la notac mande un alerta que diga que no se puede hacer la nota c
								5. sino que las haga.
								*/
				/*			String Deducido="";
							String ValorFactura="";
							String CodFactura="";
							String CodDetalleFactura="";
								try {		
									rs1=mc.DatosFacturaDetalle(rs2.getString(1));
									if(rs1.next()){
										//si esta la factura en cont_facturas
										//se verifica el saldo de la factura
										ValorFactura=rs1.getString("precio_factura");
										CodFactura=rs1.getString("codigo");
										rs=mc.BuscarDetalleFactura(rs1.getString(1));
										if(rs.next()){
											Deducido=rs.getString("Deducido");
										}
										rs.getStatement().getConnection().close();
									}else{
										//no esta la factura creada en cont_facturas
										// se buscan los datos a ingresar
										// se inserta en cont_facturas,cont_detalle_factura y en cont_cartera_plazo
									}
									rs1.getStatement().getConnection().close();
									long TotalaDeducir=0;
									TotalaDeducir=Long.parseLong(Deducido)+Long.parseLong(Valor_Glosa);
									long Diferencia=Long.parseLong(ValorFactura)-TotalaDeducir;
									System.out.println("Deducido "+Deducido+" Valor_Glosa="+Valor_Glosa+" TotalaDeducir="+TotalaDeducir+" ValorFactura="+ValorFactura+" Diferencia="+Diferencia);
									if(TotalaDeducir>Long.parseLong(ValorFactura)){
										//no se puede deducir
									}else{
										//se hace la deduccion
										mc.InsertarDetalleFactura(CodFactura, Valor_Glosa, fechacjmysql, "1", Diferencia+"", "-", Observacion, fechacjmysql, hra, v2);
										rs1=mc.BuscarDetalleFacturaFechaHora(fechacjmysql+"", hra+"",CodFactura);
										if(rs1.next()){
											CodDetalleFactura=rs1.getString(1);
											//String TipoPago=req.getParameter("TipoPago");
											String ret_fuente="0";
											String cod_centro_costo="0";
											mc.CrearComplementoFactura(CodFactura, CodDetalleFactura, cod_centro_costo, ret_fuente, "1");

										}
										rs1.getStatement().getConnection().close();
										/*****************************************************************/															
									
										//*********************************************************************/
										// se sube la nota credito o la glosa a contabilidad
										//1. se verifica el consecutivo de la nota credito;								
										//se busca el consecutivo de el recibo de caja
				/*						String conso="";
										String cons="";
										String consn="";
										rs = mm.ConsecutivosdeCuentas("130");
										//try {
											if(rs.next()){
												//out.print(rs.getString(1)+"|"+rs.getString(2));
												if(rs.getString(2).length()==1){cons=("00000000"+rs.getString(2));}
												if(rs.getString(2).length()==2){cons=("0000000"+rs.getString(2));}
												if(rs.getString(2).length()==3){cons=("000000"+rs.getString(2));}
												if(rs.getString(2).length()==4){cons=("00000"+rs.getString(2));}
												if(rs.getString(2).length()==5){cons=("0000"+rs.getString(2));}
												if(rs.getString(2).length()==6){cons=("000"+rs.getString(2));}
												if(rs.getString(2).length()==7){cons=("00"+rs.getString(2));}
												if(rs.getString(2).length()==8){cons=("0"+rs.getString(2));}
												if(rs.getString(2).length()==9){cons=rs.getString(2);}
												consn=rs.getString(1)+""+cons;
												conso=rs.getString(2);
											}
											rs.getStatement().getConnection().close();
										//2. se crea el documento en la tabla cont_documentos;									
										mm.CrearDocumento(annio,mes,"130",consn,fechacjmysql,"Nota Credito "+Observacion,Valor_Glosa,Valor_Glosa,"0","",v2,fechacjmysql,hra+"");
										//3. se crea el detalle del documento;
										//3.1 se obtiene el codigo del documento creado
										String Coddocu="";
										rs = mm.ConsecutivoUDocumento(consn);if(rs.next()){Coddocu=rs.getString(1);}rs.getStatement().getConnection().close();
										//3.2 se obtiene el codigo de la entidad prestadora;
										rs3=mm.ConsultarcuentaIngresoClientes();
										String ctacliente="0";String nctacliente="";
										if(rs3.next()){	ctacliente=rs3.getString(1);nctacliente=rs3.getString(2);}rs3.getStatement().getConnection().close();
										//3.3 se crea el detalle de credito a la entidad
										mm.CrearDetalleDocumento(Coddocu,ctacliente,"1","2","2",nctacliente,"0","Nota Credito "+CodDetalleFactura,"0",Valor_Glosa,"0","NTC");
										//3.4 se obtiene el numero de la cuenta de la nota credito, para crear el detalle debito del documento
										String TipoPago=req.getParameter("TipoPago");
										String ctaNc="0";String nctaNc="";
										rs3=mm.ConsultarCuentaNc(TipoPago);
										if(rs3.next()){ctaNc=rs3.getString(1);nctaNc=rs3.getString(3);rs3.getStatement().getConnection().close();}
										mm.CrearDetalleDocumento(Coddocu,ctaNc,"1","2","2",nctaNc,"0","Nota Credito "+CodDetalleFactura,Valor_Glosa,"0","0","NTC");
										//3.5 se actualiza el consecutivo del documento
										int ctan=Integer.parseInt(conso)+1;
										conso=String.valueOf(ctan);
										mm.ActualizarConsecutivo(conso,"130");
										//*********************************************************************/
										/*******************************************************************/								
									}																		
								//	out.print("CodFactura "+CodFactura+" CantidadInicial "+CantidadInicial+" cantidad "+cantidad+" CodEstadoCuenta "+CodEstadoCuenta+" restante "+restante+" CodDetalleFactura "+CodDetalleFactura);
						/*		} catch (SQLException e) {
									out.print(e);
									e.printStackTrace();
								}*/
						//	}
							
							
							//**********************************************
			//			}
										
						
					}
					rs2.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				i1++;
			}
	
		}
		
		
		if(va.equals("RevisarGlosa3")){
			
			out.print("<table width='100%' border='0' align='center'><tr><td colspan='4' valign='top'><div align='center' class='style11' id='cabecera2'>Motivos de Glosa</div></td></tr></table>");
		
			out.print("<table width='100%' border='0' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='40%'><i><div align='center'>Concepto General de la Glosa</div></i></td><td width='40%'><i><div align='center'>Concepto especifico de la Glosa</div></i></td><td width='10%'><i><div align='center'>Valor glosado</div></i></td><td width='10%'><i><div align='center'>Diferencia</div></i></td></tr>	");  //
			
			rs2=mr.GlosaRevision(v4);
			try {
				if(rs2.next()){
				out.print("<tr><td><select  style='width:100%' id='cgg' >");
				out.print("<option title='"+rs2.getString(1)+"' value='0'>"+rs2.getString(1)+"</option>");
				out.print("</select></td>");
				
				out.print("<td><select  style='width:100%' id='cge' >");
				out.print("<option title='"+rs2.getString(2)+"'  value='0'>"+rs2.getString(2)+"</option>");
				out.print("</select></td>");
				
				out.print("<td><input type='text' id='vaplica' style='width:95%' value='"+rs2.getString(4)+"'  readonly='readonly'/></td>");
				int cantd=Integer.parseInt(rs2.getString(3))-Integer.parseInt(rs2.getString(4));
				out.print("<td><input type='text' id='vtota' value='"+cantd+"' style='width:95%' readonly='readonly' /></td>");

				out.print("<tr><td colspan=4><div align='center'>Observacion: <input type='text' id='obser' value='"+rs2.getString(5)+"' style='width:93%' readonly='readonly' /></div></td></tr>");
				//out.print("<tr><td colspan=4><div align='center'><textarea id='txtDescripcion' cols='58' rows='3'  readonly='readonly'>"+rs2.getString(5)+"</textarea></div></td></tr>");
				out.print("</table>");
						
				out.print("<table width='100%' border='0' align='center'><tr><td colspan='4' valign='top'><div align='center' class='style11' id='cabecera2'>Responder Glosas</div></td></tr>");
				
				if(rs2.getString(6).equals("0")){
					
					out.print("<tr><td width='10%'>Respuesta: </td><td width='10%'><select id='rge' onChange='vconcilia(this)' style='width:93%'><option value='1'>Subsanar</option><option value='2'>Aplicar</option><option value='3'>Rechazar</option><option value='5'>Conciliar</option></select></td>");
					out.print("<td  width='10%' rowspan='2'>Justificacion: </td><td  width='70%' rowspan='2'><textarea id='arearespon' cols='110' rows='2' style='vertical-align:text-top;'></textarea></td></tr>");
					//out.print("<td  width='80%' height='50' valign='bottom'>Justificacion: </td></tr>");
					out.print("<tr><td  width='10%'><div id='vc'>Valor conciliado: </div></td><td  width='10%'><div id='vcn'><input type='text' id='valorconcilia' value='0' style='width:93%' onkeyup='verificaconci(this)'/></div></td></tr>");
					
					out.print("<tr><td colspan=4><div align='center'><input type='button' id='NGlosa' value='     Responder    ' onclick='ResponderGlosas(rge,&quot;"+v1+"&quot;,&quot;"+v4+"&quot;,&quot;"+rs2.getString(7)+"&quot;)' /></div></td></tr>");
				}else{
					String estadon="";
					if(rs2.getString(6).equals("1")){estadon="Notificacion Subsanada";}
					if(rs2.getString(6).equals("2")){estadon="Notificacion Aplicada";}
					if(rs2.getString(6).equals("3")){estadon="Notificacion Rechazada";}
					if(rs2.getString(6).equals("5")){estadon="Notificacion Conciliada";}
					out.print("<tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr><td colspan=4><div align='center'><label><font color='red'>"+estadon+"</font></label><a href='#'onclick='EliminarRespuesta(&quot;"+v4+"&quot;,&quot;"+v1+"&quot;)'>-Eliminar Respuesta</a></div></td></tr>");
				}
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
		}
		
		if(va.equals("EliminarRespuesta")){
		mr.AnularRespuesta(v1,usu,fechacjmysql,hra);
		}
			
		if(va.equals("Responder")){
			//System.out.println(v1+" - "+v2+" - "+v3+" - "+v4+" - "+v5+" - "+v6);
			mr.ResponderGlosadetalle(v6,v4,v5,v3,v2,fechacjmysql,hra,v7);
			
			int swg=0;
			rs2=mr.Detallesactivos(v7);
			try {
				if(rs2.next()){
					swg=1;
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			if(swg==0){
				mr.FinalizarGlosa(fechacjmysql,hra,v7);
			}
	
		}
		
		
		if(va.equals("GenerarReporteG")){
			out.print("<table width='100%' border='0' align='center'><tr><td colspan='4' valign='top'><div align='center' class='style11' id='cabecera2'>Generar reporte de Glosa</div></td></tr></table>");
			out.print("<table width='100%' border='0'><tr></tr><tr><td  width='90%'></td><td  width='10%'  BGCOLOR='#808080' ><i><div align='center'><a href='#'onclick='ResponderNotificacionNueva(&quot;"+v1+"&quot;)'><font color='white'>Revisar Notificacion</font></a></div></i></td></tr><tr></tr></table>");
			
			out.print("<table width='100%' border='0' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='10%'><i><div align='center'>Factura</div></i></td><td width='40%'><i><div align='center'>Entidad</div></i></td><td width='10%'><i><div align='center'>Valor</div></i></td><td width='35%'><i><div align='center'>Paciente</div></i></td><td><i><div align='center'>Seleccionar</div></i></td></tr>	");  //
			
			StringTokenizer elementos;  
			//System.out.println("datos: "+v1);
			elementos = new StringTokenizer(v1,"|"); 
			//System.out.println("elementos: "+elementos);
			
			while(elementos.hasMoreTokens()){ 
				String x=elementos.nextToken(); 
							
				rs2=mr.Glosasxdetalle(x);
				try {
					if(rs2.next()){
						if(rs2.getString(2).equals("0")){
							out.print("<tr title='Factura en proceso de conciliacion'><td><font color='grey'>"+rs2.getString(4)+"</font></td><td><font color='grey'>"+rs2.getString(6)+"</font></td><td><font color='grey'>"+rs2.getString(7)+"</font></td><td><font color='grey'>"+rs2.getString(8)+"</font></td><td><input type='checkbox' name='checkgnfalse' id='reportgfalse' value='0' disabled='disabled' ></td></tr>");	
						}else{
							out.print("<tr><td>"+rs2.getString(4)+"</td><td>"+rs2.getString(6)+"</td><td>"+rs2.getString(7)+"</td><td>"+rs2.getString(8)+"</td><td><input type='checkbox' name='checkgn' id='"+rs2.getString(6)+"' value='"+rs2.getString(1)+"' checked='true'></td></tr>");	
						}
					}
					rs2.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
	
			}//fin while
		}
		
		
		if(va.equals("GenerarReporteN")){
			
			
			//valorfacturado,valorglosado
			 mr.InsertarFormatoGlosaNotificada(fechacjmysql,hra,v2);
			 String codgn="";;
				rs2=mr.ConsultarCodFormatoGlosaN(fechacjmysql,hra,v2);
				try {
					if(rs2.next()){
						codgn=rs2.getString(1);
					}
					rs2.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				
			int ce=0;	
			int tfactura=0;
			int tglosado=0;
			StringTokenizer elementos;  
			//System.out.println("datos: "+v1);
			elementos = new StringTokenizer(v1,"|"); 
			System.out.println("elementos finales o encabezados: "+v1);
			System.out.println("elementos: "+elementos);
			
			
			
			while(elementos.hasMoreTokens()){ 
				String x=elementos.nextToken(); 
				System.out.println("CJJJ: "+x);
				ce=0;
				rs2=mr.ConsultarGlosaNumerada(x);
				try {
					while(rs2.next()){
						
						System.out.println("While Glosas: "+rs2.getString(1)+" y: "+rs2.getString(2));
						//rs2.getString(1)//cod glosa
						//rs2.getString(2)//cod fact_numerada
						if(ce==0){
							mr.EnviarNotificacion(rs2.getString(1));
							tfactura=tfactura+Integer.parseInt(rs2.getString(5));
						}
						
						tglosado=tglosado+Integer.parseInt(rs2.getString(7));
						mr.ActualizaDetalleNotifica(rs2.getString(1));
						mr.InsertarFormatoGlosaNotificadaDetalle(codgn,rs2.getString(1),rs2.getString(3),rs2.getString(5),rs2.getString(6),rs2.getString(7));
					
						ce++;
					}
					rs2.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
					
			mr.ActualizaFormatoGlosaNotificada(String.valueOf(tfactura),String.valueOf(tglosado),codgn);
			out.print(codgn);
						
			}
		
		}
		
		
		if(va.equals("ImprimirReporteG")){
			mr.InsertarFormatoGlosa(fechacjmysql,hra,v3,v2);
			String codge="";;
			rs2=mr.ConsultarCodFormatoGlosa(fechacjmysql,hra,v3,v2);
			try {
				if(rs2.next()){
					codge=rs2.getString(1);
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
						
			StringTokenizer elementos;  
			StringTokenizer elementos2;  
			elementos = new StringTokenizer(v1,"|"); 
			System.out.println("elementos finales o encabezados: "+v1);
			int ce=0;
			String valora="";
			String resa="";
			int tfactura=0;
			int tglosado=0;
			int taceptado=0;
			String cons="";
			
			while(elementos.hasMoreTokens()){ 
				int swacepta=0;
				int txfactura=0;
				int tglosadoxf=0;
				String motivog="";
				String nit="";
				String consecutivo="";
				String encabezado="";
				String valorg="";
				String v="";
				String x=elementos.nextToken(); 
				ce=0;
				System.out.println("x: "+x+" ce: "+ce);
				
				rs2=mr.ConsultarGlosaNumerada(x);
				try {
					while(rs2.next()){
						nit=rs2.getString(11);
						consecutivo=rs2.getString(3);
						valorg=rs2.getString(7);
						encabezado=rs2.getString(13);
						System.out.println("While Glosas: "+rs2.getString(1)+" y: "+rs2.getString(2));
						System.out.println("nit: "+rs2.getString(11));
						System.out.println("consecutivo : "+rs2.getString(3));
						System.out.println("det encabezado : "+rs2.getString(12));
						System.out.println("encabezado : "+rs2.getString(13));
						
						
						//rs2.getString(1)//cod glosa
						//rs2.getString(2)//cod fact_numerada
						if(ce==0){
							mr.EnviarGlosa(fechacjmysql,hra,v2,rs2.getString(1));
							mr.ActualizaGlosaNumerada(rs2.getString(2));
							mr.MovimientoGlosa(rs2.getString(2),"3",fechacjmysql,v2);	
							tfactura=tfactura+Integer.parseInt(rs2.getString(5));
							txfactura=txfactura+Integer.parseInt(rs2.getString(5));
						}
						
						
						//aqui puedo verificar los estados. si esta aceptada todos los cargues de la factura 
						//debo generar la Nota credito automaticamente y cambio el estado de numeradas a 9
						//y en fact_glosas un estado 3 q sea finalizada todo su proceso de glosa q me impida volver a generar glosa
						if(rs2.getString(8).equals("1")){valora="0"; 				resa="Subsanada - ";	swacepta=1;}
						if(rs2.getString(8).equals("2")){valora=rs2.getString(9); 	resa="Aceptada -";  v=v+rs2.getString(12)+"|"; }
						if(rs2.getString(8).equals("3")){valora="0"; 				resa="Rechazada -";		swacepta=1;}
						if(rs2.getString(8).equals("5")){													
						valora=String.valueOf(Integer.parseInt(rs2.getString(7))-Integer.parseInt(rs2.getString(9)));	resa="Conciliada -";
						swacepta=1;
						}
						
						
						tglosadoxf=tglosadoxf+Integer.parseInt(rs2.getString(7));
						tglosado=tglosado+Integer.parseInt(rs2.getString(7));
						taceptado=taceptado+Integer.parseInt(valora);
						motivog=motivog+rs2.getString(10);
						mr.InsertarFormatoGlosaDetalle(codge,rs2.getString(1),rs2.getString(3),resa+" "+rs2.getString(4),rs2.getString(5),rs2.getString(6),rs2.getString(7),valora,rs2.getString(12));
						
						cons=rs2.getString(3);//Consecutivo de factura
					ce++;
					}
					
					rs2.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				
				if(swacepta==0){//Si todos los mov glosados fueron aceptados generamos la NC automaticamente
				
					mr.FinalizarConciliacion(x);//coloca en estado 3 la glosa como terminada

				//cons consecutivo
				//String.valueOf(txfactura)
				//String.valueOf(tglosadoxf)
				//v2 usuario
				//fechacjmysql
				//fechacjmysql
				//hra
				//motivog
					//Debo colocar estado 3 en glosas
					String Deducido="";
					String ValorFactura="";
					String CodFactura="";
					String CodDetalleFactura="";
						try {		
							rs1=mc.DatosFacturaDetalle(cons);
							if(rs1.next()){
								//si esta la factura en cont_facturas
								//se verifica el saldo de la factura
								ValorFactura=rs1.getString("precio_factura");
								CodFactura=rs1.getString("codigo");
								rs=mc.BuscarDetalleFactura(rs1.getString(1));
								if(rs.next()){
									Deducido=rs.getString("Deducido");
								}
								rs.getStatement().getConnection().close();
							}else{
								//no esta la factura creada en cont_facturas
								// se buscan los datos a ingresar
								// se inserta en cont_facturas,cont_detalle_factura y en cont_cartera_plazo
							}
							rs1.getStatement().getConnection().close();
							long TotalaDeducir=0;
							TotalaDeducir=Long.parseLong(Deducido)+Long.parseLong(tglosadoxf+"");
							long Diferencia=Long.parseLong(ValorFactura)-TotalaDeducir;
							System.out.println("Deducido "+Deducido+" Valor_Glosa="+tglosadoxf+" TotalaDeducir="+TotalaDeducir+" ValorFactura="+ValorFactura+" Diferencia="+Diferencia);
							if(TotalaDeducir>Long.parseLong(ValorFactura)){
								//no se puede deducir
							}else{
								//se hace la deduccion
				/*				mc.InsertarDetalleFactura(CodFactura, tglosadoxf+"", fechacjmysql, "1", Diferencia+"", "-", motivog, fechacjmysql, hra, v2);
								rs1=mc.BuscarDetalleFacturaFechaHora(fechacjmysql+"", hra+"",CodFactura);
								if(rs1.next()){
									CodDetalleFactura=rs1.getString(1);
									//String TipoPago=req.getParameter("TipoPago");
									String ret_fuente="0";
									String cod_centro_costo="0";
									mc.CrearComplementoFactura(CodFactura, CodDetalleFactura, cod_centro_costo, ret_fuente, "1");

								}
								rs1.getStatement().getConnection().close();
								/*****************************************************************/															
							
								
								/***no se puede grnerar nota credito***/
								
								//*********************************************************************/
								// se sube la nota credito o la glosa a contabilidad
								//1. se verifica el consecutivo de la nota credito;								
								//se busca el consecutivo de el recibo de caja
					/*			String conso="";
								String cons1="";
								String consn="";
								rs = mm.ConsecutivosdeCuentas("130");
								//try {
									if(rs.next()){
										//out.print(rs.getString(1)+"|"+rs.getString(2));
										if(rs.getString(2).length()==1){cons1=("00000000"+rs.getString(2));}
										if(rs.getString(2).length()==2){cons1=("0000000"+rs.getString(2));}
										if(rs.getString(2).length()==3){cons1=("000000"+rs.getString(2));}
										if(rs.getString(2).length()==4){cons1=("00000"+rs.getString(2));}
										if(rs.getString(2).length()==5){cons1=("0000"+rs.getString(2));}
										if(rs.getString(2).length()==6){cons1=("000"+rs.getString(2));}
										if(rs.getString(2).length()==7){cons1=("00"+rs.getString(2));}
										if(rs.getString(2).length()==8){cons1=("0"+rs.getString(2));}
										if(rs.getString(2).length()==9){cons1=rs.getString(2);}
										consn=rs.getString(1)+""+cons1;
										conso=rs.getString(2);
									}
									rs.getStatement().getConnection().close();
								//2. se crea el documento en la tabla cont_documentos;									
								mm.CrearDocumento(annio,mes,"130",consn,fechacjmysql,"Nota Credito "+motivog,tglosadoxf+"",tglosadoxf+"","0","",v2,fechacjmysql,hra+"");
								//3. se crea el detalle del documento;
								//3.1 se obtiene el codigo del documento creado
								String Coddocu="";
								rs = mm.ConsecutivoUDocumento(consn);if(rs.next()){Coddocu=rs.getString(1);}rs.getStatement().getConnection().close();
								//3.2 se obtiene el codigo de la entidad prestadora;
								rs3=mm.ConsultarcuentaIngresoClientes();
								String ctacliente="0";String nctacliente="";
								if(rs3.next()){	ctacliente=rs3.getString(1);nctacliente=rs3.getString(2);}rs3.getStatement().getConnection().close();
								//ME TRAIGO EL <CODIGO DE LA TABLA TERCERO CON ESTE NIT
								rs3=mm.ConsultarTercerosconNIT(nit);
								String ctat="0";
								if(rs3.next()){
									ctat=rs3.getString(1);//
								}
								rs3.getStatement().getConnection().close();
								
								
								
								
								
								//3.3 se crea el detalle de credito a la entidad
								mm.CrearDetalleDocumento(Coddocu,ctacliente,"1","2",ctat,nctacliente,"0",consecutivo,"0",tglosadoxf+"","0","NTC");
								//3.4 se obtiene el numero de la cuenta de la nota credito, para crear el detalle debito del documento
								String TipoPago=req.getParameter("TipoPago");
								String ctaNc="0";String nctaNc="";
								rs3=mm.ConsultarCuentaNc(TipoPago);
								if(rs3.next()){ctaNc=rs3.getString(1);nctaNc=rs3.getString(3);rs3.getStatement().getConnection().close();}
							//	mm.CrearDetalleDocumento(Coddocu,ctaNc,"1","2",ctat,nctaNc,"0","Nota Credito "+CodDetalleFactura,tglosadoxf+"","0","0","NTC");
								System.out.println("v: "+v);
							
								elementos2 = new StringTokenizer(v,"|");
								
								
								while(elementos2.hasMoreTokens()){
									String xy=elementos2.nextToken();
									System.out.println("xy: "+xy);
									
									String sc="0";
									String cs="0";
									String cc="0";
									String scc="0";
									rs3=mm.ConsultarEncabezado(encabezado);		
									String tipoFact="1";
									if(rs3.next()){
										//tipoFact=rs3.getString(23);//
										tipoFact=rs3.getString("tipo");	
										System.out.println("tipo : "+tipoFact);
									}
									rs3.getStatement().getConnection().close();
							
									if(tipoFact.equals("1")){	sc="1"; 	cs="1";   cc="1"; scc="1";  }//Factura de Urgencias
									if(tipoFact.equals("2")){	sc="1"; 	cs="2";   cc="2"; scc="2"; 	}//Factura de Hospitalizacion
									if(tipoFact.equals("3")){	sc="1"; 	cs="15";  cc="3"; scc="15"; 	}//Factura de Ambulatorio 
									if(tipoFact.equals("4")){	sc="1"; 	cs="15";  cc="3"; scc="15"; 	}//Factura COnsulta Externa
									if(tipoFact.equals("5")){	sc="1"; 	cs="15";  cc="3"; scc="15"; 	}//Factura Capitado
									if(tipoFact.equals("6")){	sc="1"; 	cs="15";  cc="3"; scc="15"; 	}//Factura Agrupado
									
									System.out.println("sc : "+sc +" ,cs: "+cs+" ,cc: "+cc+" ,scc: "+scc);
									Double vtd=0.0;
									String progc="";
									String valortc="";
									String nombrec="";
									String clases="";
									String vg="";
									rs1=mm.ConsultarValoresg(xy);
									while(rs1.next()){
										vg=rs1.getString(1);
									}
									
									
									rs2=mm.ConsultarProgramasValoresd(xy);
									while(rs2.next()){//ME TRAIGO LOS CODIGOS DE LOS PROGRAMAS MEDICAMENTOS E INSUMOS
										progc=rs2.getString(1);
										valortc=rs2.getString(3);
										nombrec=rs2.getString(4);
										clases=rs2.getString(5);
										
										//Aqui buscamos el programa la cuenta
										rs3=mm.Consultarcuentasdeprogramas(cc,scc,progc);
										String cuentapp="0";
										String cuentappc="0";
										String cuentappg="0";
										if(rs3.next()){//ME TRAIGO EL COSTO PROMEDIO DE CADA PROGRAMA
											cuentapp=rs3.getString(1);//
											cuentappc=rs3.getString(2);//
											cuentappg=rs3.getString(3);//
										}
										rs3.getStatement().getConnection().close();
										
										if(cuentapp.equals("")){cuentapp="0";}
										
										mm.CrearDetalleDocumento(Coddocu,cuentapp,sc,cs,ctat,nombrec,"0",consecutivo,vg,"0","0","NTC");//demas lineas
										
										if((clases.equals("MEDICAMENTOS"))||(clases.equals("MATERIALES"))){
											double costop=0;
											//Consulto el costo del programa
										/*	rs3=mm.ConsultarProgramasCostos(CodAdm,progc);
											
											String xxx="0";
											if(rs3.next()){//ME TRAIGO EL COSTO PROMEDIO DE CADA PROGRAMA
												xxx=rs3.getString(3);//
												if(rs3.getString(3)==null){xxx="0";}
												costop=Double.parseDouble(xxx);//
											}
											rs3.getStatement().getConnection().close();
											*/
						/*					String cps=String.valueOf(costop);
											
											mm.CrearDetalleDocumento(Coddocu,cuentappc,sc,cs,ctat,nombrec,"0",consecutivo,"0",cps,"0","NTC");//tercera linea
											mm.CrearDetalleDocumento(Coddocu,cuentappg,sc,cs,ctat,nombrec,"0",consecutivo,cps,"0","0","NTC");//cuarta linea
										//	vtd=vtd+Double.parseDouble(cps);
										}
												
									//	vtd=vtd+Double.parseDouble(valortc);
									}
									rs2.getStatement().getConnection().close();
						
								}

									
								//3.5 se actualiza el consecutivo del documento
								int ctan=Integer.parseInt(conso)+1;
								conso=String.valueOf(ctan);
								mm.ActualizarConsecutivo(conso,"130");*/
								//*********************************************************************/
								/*******************************************************************/								
							}																		
						//	out.print("CodFactura "+CodFactura+" CantidadInicial "+CantidadInicial+" cantidad "+cantidad+" CodEstadoCuenta "+CodEstadoCuenta+" restante "+restante+" CodDetalleFactura "+CodDetalleFactura);
						
						} catch (SQLException e) {
							out.print(e);
							e.printStackTrace();
						}
				}
				
			}
			
			mr.ActualizaFormatoGlosa(String.valueOf(tfactura),String.valueOf(tglosado),String.valueOf(taceptado),codge);
			out.print(codge);
		}
		
		
		
		
		
		
		
		if(va.equals("GenerarConciliacion")){
			
			
			//valorfacturado,valorglosado,valoraplicado
			 mr.InsertarFormatoGlosaConciliada(fechacjmysql,hra,v2);
			 String codgc="";;
				rs2=mr.ConsultarCodFormatoGlosaC(fechacjmysql,hra,v2);
				try {
					if(rs2.next()){
						codgc=rs2.getString(1);
					}
					rs2.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
			
				int tfactura=0;
				int tglosado=0;
				int taplicado=0;
			 
			StringTokenizer elementos; 
			StringTokenizer elementosval;
			//System.out.println("datos: "+v1);
			elementos = new StringTokenizer(v1,"|"); 
			elementosval = new StringTokenizer(v3,"|"); 
			System.out.println("elementos finales o encabezados: "+v1);
						
			String V[] = new String[200];
			int i1=0;
			while(elementosval.hasMoreTokens()){ 
			  V[i1] = elementosval.nextToken();  
			  System.out.println("V[i1]: "+V[i1]);
			  i1++;
			}
			
			int i2=0;
			while(elementos.hasMoreTokens()){ 
				String x=elementos.nextToken(); 
				
				rs2=mr.ConsultarValoresaFinalizar(x);
				try {
				if(rs2.next()){
					System.out.println("if Glosas: "+rs2.getString(1)+" y: "+rs2.getString(2)+" y: "+rs2.getString(3)+" V^* "+V[i2]);
					//mr.FinalizarConciliacion(x);//coloca en estado 3 la glosa como terminada
					mr.FinalizarConciliacionydetalle(x,fechacjmysql,hra,v2);
					mr.FinalizarConciliaciondetalleglosa(x,v2,rs2.getString(3),fechacjmysql,hra);
					
					tfactura=tfactura+Integer.parseInt(rs2.getString(2));
					tglosado=tglosado+Integer.parseInt(rs2.getString(3));
					taplicado=taplicado+Integer.parseInt(V[i2]);
					
					mr.ActualizaDetalleConciliacion(x);//coloca en estado 6 los detalles como conciliados general
																
					mr.InsertarFormatoGlosaConciliadaDetalle(codgc,x,rs2.getString(1),rs2.getString(2),rs2.getString(3),V[i2]);
				
					//rs2.getString(1) consecutivo
					//rs2.getString(2) valor factura
					//V[i2] valor glosa
					//v2 usuario
					//fechacjmysql
					//fechacjmysql
					//hra
					//rs2.getString(4) 
					String Deducido="";
					String ValorFactura="";
					String CodFactura="";
					String CodDetalleFactura="";
						try {		
							rs1=mc.DatosFacturaDetalle(rs2.getString(1));
							if(rs1.next()){
								//si esta la factura en cont_facturas
								//se verifica el saldo de la factura
								ValorFactura=rs1.getString("precio_factura");
								CodFactura=rs1.getString("codigo");
								rs=mc.BuscarDetalleFactura(rs1.getString(1));
								if(rs.next()){
									Deducido=rs.getString("Deducido");
								}
								rs.getStatement().getConnection().close();
							}else{
								//no esta la factura creada en cont_facturas
								// se buscan los datos a ingresar
								// se inserta en cont_facturas,cont_detalle_factura y en cont_cartera_plazo
							}
							rs1.getStatement().getConnection().close();
							long TotalaDeducir=0;
							TotalaDeducir=Long.parseLong(Deducido)+Long.parseLong(V[i2]+"");
							long Diferencia=Long.parseLong(ValorFactura)-TotalaDeducir;
							System.out.println("Deducido "+Deducido+" Valor_Glosa="+V[i2]+" TotalaDeducir="+TotalaDeducir+" ValorFactura="+ValorFactura+" Diferencia="+Diferencia);
							if(TotalaDeducir>Long.parseLong(ValorFactura)){
								//no se puede deducir
							}else{
								//se hace la deduccion
			/*					mc.InsertarDetalleFactura(CodFactura, V[i2]+"", fechacjmysql, "1", Diferencia+"", "-", rs2.getString(4), fechacjmysql, hra, v2);
								rs1=mc.BuscarDetalleFacturaFechaHora(fechacjmysql+"", hra+"",CodFactura);
								if(rs1.next()){
									CodDetalleFactura=rs1.getString(1);
									//String TipoPago=req.getParameter("TipoPago");
									String ret_fuente="0";
									String cod_centro_costo="0";
									mc.CrearComplementoFactura(CodFactura, CodDetalleFactura, cod_centro_costo, ret_fuente, "1");
								}
								rs1.getStatement().getConnection().close();
								/*****************************************************************/															
							
								//*********************************************************************/
								// se sube la nota credito o la glosa a contabilidad
								//1. se verifica el consecutivo de la nota credito;								
								//se busca el consecutivo de el recibo de caja
				/*				String conso="";
								String cons1="";
								String consn="";
								rs = mm.ConsecutivosdeCuentas("130");
								//try {
									if(rs.next()){
										//out.print(rs.getString(1)+"|"+rs.getString(2));
										if(rs.getString(2).length()==1){cons1=("00000000"+rs.getString(2));}
										if(rs.getString(2).length()==2){cons1=("0000000"+rs.getString(2));}
										if(rs.getString(2).length()==3){cons1=("000000"+rs.getString(2));}
										if(rs.getString(2).length()==4){cons1=("00000"+rs.getString(2));}
										if(rs.getString(2).length()==5){cons1=("0000"+rs.getString(2));}
										if(rs.getString(2).length()==6){cons1=("000"+rs.getString(2));}
										if(rs.getString(2).length()==7){cons1=("00"+rs.getString(2));}
										if(rs.getString(2).length()==8){cons1=("0"+rs.getString(2));}
										if(rs.getString(2).length()==9){cons1=rs.getString(2);}
										consn=rs.getString(1)+""+cons1;
										conso=rs.getString(2);
									}
									rs.getStatement().getConnection().close();
								//2. se crea el documento en la tabla cont_documentos;									
								mm.CrearDocumento(annio,mes,"130",consn,fechacjmysql,"Nota Credito "+rs2.getString(4),V[i2]+"",V[i2]+"","0","",v2,fechacjmysql,hra+"");
								//3. se crea el detalle del documento;
								//3.1 se obtiene el codigo del documento creado
								String Coddocu="";
								rs = mm.ConsecutivoUDocumento(consn);if(rs.next()){Coddocu=rs.getString(1);}rs.getStatement().getConnection().close();
								//3.2 se obtiene el codigo de la entidad prestadora;
								rs3=mm.ConsultarcuentaIngresoClientes();
								String ctacliente="0";String nctacliente="";
								if(rs3.next()){	ctacliente=rs3.getString(1);nctacliente=rs3.getString(2);}rs3.getStatement().getConnection().close();
								//3.3 se crea el detalle de credito a la entidad
								mm.CrearDetalleDocumento(Coddocu,ctacliente,"1","2","2",nctacliente,"0","Nota Credito "+CodDetalleFactura,"0",V[i2]+"","0","NTC");
								//3.4 se obtiene el numero de la cuenta de la nota credito, para crear el detalle debito del documento
								String TipoPago=req.getParameter("TipoPago");
								String ctaNc="0";String nctaNc="";
								rs3=mm.ConsultarCuentaNc(TipoPago);
								if(rs3.next()){ctaNc=rs3.getString(1);nctaNc=rs3.getString(3);rs3.getStatement().getConnection().close();}
								mm.CrearDetalleDocumento(Coddocu,ctaNc,"1","2","2",nctaNc,"0","Nota Credito "+CodDetalleFactura,V[i2]+"","0","0","NTC");
								//3.5 se actualiza el consecutivo del documento
								int ctan=Integer.parseInt(conso)+1;
								conso=String.valueOf(ctan);
								mm.ActualizarConsecutivo(conso,"130");*/
								//*********************************************************************/
								/*******************************************************************/								
							}																		
						//	out.print("CodFactura "+CodFactura+" CantidadInicial "+CantidadInicial+" cantidad "+cantidad+" CodEstadoCuenta "+CodEstadoCuenta+" restante "+restante+" CodDetalleFactura "+CodDetalleFactura);
						} catch (SQLException e) {
							out.print(e);
							e.printStackTrace();
						}
				}
				rs2.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				i2++;
			}
			mr.ActualizaFormatoGlosaConciliada(String.valueOf(tfactura),String.valueOf(tglosado),String.valueOf(taplicado),codgc);
			out.print(codgc);
		}
	}
}	