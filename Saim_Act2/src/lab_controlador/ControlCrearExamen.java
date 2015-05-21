/**
 * controlador: ControlCrearExamen se encuentra el proceso para la 
 * creacion de los examenes en todas sus diferentes opciones.
*/
package lab_controlador;

import img_logica.MetodoCrearSubExa;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lab_logica.MetodoCrearExamen;
import lab_logica.MetodoSubarea;

/**
 * Servlet implementation class ControlCrearExamen
 * 
 */
public class ControlCrearExamen extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		MetodoCrearExamen mce=new MetodoCrearExamen();
        ResultSet rs=null;
        ResultSet rs1=null;
        ResultSet rs2=null;
        res.setContentType("text/html;charset=UTF-8");
		String tipo=req.getParameter("tipo");		
		String codAex=req.getParameter("codAex");		
		String codSex=req.getParameter("codSex");		
		String nomb=req.getParameter("nomb");		
		String radio=req.getParameter("val");
		String valIni=req.getParameter("valIni");		
		String valFin=req.getParameter("valFin");		
		String codUni=req.getParameter("codUni");
		String codGen=req.getParameter("codGen");
		String edaIni=req.getParameter("edaIni");
		String edaFin=req.getParameter("edaFin");
		String codCom=req.getParameter("tipo");
		String CodUsu=req.getParameter("CodUsu");
		String codExa=null;
		String CodLabora="";
		String CodPrograma="";	
		String tipo_examen="0";
		MetodoCrearSubExa mcs = new MetodoCrearSubExa ();
		java.util.Date fechaActual = new java.util.Date();
		java.sql.Date fecha = new java.sql.Date(fechaActual.getTime());		
		java.sql.Time hora = new java.sql.Time(fechaActual.getTime());	
		MetodoSubarea sa = new MetodoSubarea();
		String cero="0";
		String blanco="";
		
/////////////////////////////si es tipo texto////////////////////////////////////	
		
		if(radio.equals("5")){
			/////////area//////////////////
			if((codAex!="")&&(codSex=="")){
				codSex="0";
				try {
					mcs.CrearPrograma(cero, cero, blanco, blanco, nomb, cero, cero, cero, cero, cero, fecha, hora, CodUsu);
					rs1=mcs.ObtenerCodigoPrograma(nomb, fecha, hora, CodUsu);
					if(rs1.next()){
						CodPrograma=rs1.getString(1);
					}
					rs1.getStatement().getConnection().close();
				} catch (SQLException e) {
					System.out.print("Error ControlCrearSubExa>>ObtenerCodigoPrograma "+e);
					e.printStackTrace();
				}
				
				try {
					mce.insertarTipoTextoArea(nomb, codAex, codSex, tipo);
					rs2=mce.obtenerCodigoExamenTexto(nomb, tipo_examen, codAex);
					if(rs2.next()){
						CodLabora=rs2.getString(1);
					}
					rs2.getStatement().getConnection().close();
				} catch (SQLException e) {
					System.out.print("Error ControlCrearSubExa>>ObtenerCodigoSubarea "+e);
					e.printStackTrace();
				}
				sa.InsertarProg_Lab(CodLabora, CodPrograma, tipo_examen);
				}else{
						///////////subarea////////////////////
					if((codAex!="")&&(codSex!="")){
						codAex="0";
						mce.insertarTipoTextoArea(nomb, codAex, codSex, tipo);
				
					}
			
				}
				res.sendRedirect("lab_examen.jsp");
			}	
		
		//////SI ES DE TIPO GENERO//////////////////////
		
		if(radio.equals("yosi")){
			String codtipo="";
			if((codAex!="")&&(codSex=="")){
				
				codSex="0";		
				
				String valInih=req.getParameter("valInih");		
				String valFinh=req.getParameter("valFinh");
				
				String valInim=req.getParameter("valInim");		
				String valFinm=req.getParameter("valFinm");
				
				//System.out.print("valor inicial hombre"+valInih);
				//System.out.print("valor final hombre"+valFinh);
				
				//System.out.print("valor inicial mujer"+valInim);
				//System.out.print("valor final mujer"+valFinm);
				try {
					mcs.CrearPrograma(cero, cero, blanco, blanco, nomb, cero, cero, cero, cero, cero, fecha, hora, CodUsu);
					rs1=mcs.ObtenerCodigoPrograma(nomb, fecha, hora, CodUsu);
					if(rs1.next()){
						CodPrograma=rs1.getString(1);
					}
					rs1.getStatement().getConnection().close();
				} catch (SQLException e) {
					System.out.print("Error ControlCrearSubExa>>ObtenerCodigoPrograma "+e);
					e.printStackTrace();
				}			
				mce.insertarTipoTextoArea(nomb, codAex, codSex, tipo);
				rs=mce.obtenerCodigoExamenArea(nomb, codAex);
				try {
					while(rs.next()){
						codExa=rs.getString(1);					
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					System.out.println("ERROR EN CONTROLADOR:_ "+e);
					e.printStackTrace();
				}
				sa.InsertarProg_Lab(codExa, CodPrograma, tipo_examen);
				 String tiporango="GENERO";
				mce.insertipo(tiporango,codExa);
				rs=mce.obtenertipo(codExa);
				try {
					while(rs.next()){
						codtipo=rs.getString(1);					
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					System.out.println("ERROR EN CONTROLADOR:_ "+e);
					e.printStackTrace();
				}
				codGen="2";
				mce.insertarTipoRangoArea(valInih, valFinh, codUni, codGen, codCom, codtipo, edaIni, edaFin);
				codGen="3";
				mce.insertarTipoRangoArea(valInim, valFinm, codUni, codGen, codCom, codtipo, edaIni, edaFin);
				
			}else{
				
				if((codAex!="")&&(codSex!="")){
					codAex="0";
					String valInih=req.getParameter("valInih");		
					String valFinh=req.getParameter("valFinh");
					
					String valInim=req.getParameter("valInim");		
					String valFinm=req.getParameter("valFinm");
					
				//	System.out.print("valor inicial hombre"+valInih);
				//	System.out.print("valor final hombre"+valFinh);
					
				//	System.out.print("valor inicial mujer"+valInim);
				//	System.out.print("valor final mujer"+valFinm);
								
					mce.insertarTipoTextoArea(nomb, codAex, codSex, tipo);
					rs=mce.obtenerCodigoExamenArea(nomb, codAex);
					try {
						while(rs.next()){
							codExa=rs.getString(1);					
						}
						rs.getStatement().getConnection().close();
					} catch (SQLException e) {
						System.out.println("ERROR EN CONTROLADOR:_ "+e);
						e.printStackTrace();
					}
					 String tiporango="GENERO";
					mce.insertipo(tiporango,codExa);
					rs=mce.obtenertipo(codExa);
					try {
						while(rs.next()){
							codtipo=rs.getString(1);					
						}
						rs.getStatement().getConnection().close();
					} catch (SQLException e) {
						System.out.println("ERROR EN CONTROLADOR:_ "+e);
						e.printStackTrace();
					}
					codGen="2";
					mce.insertarTipoRangoArea(valInih, valFinh, codUni, codGen, codCom, codtipo, edaIni, edaFin);
					codGen="3";
					mce.insertarTipoRangoArea(valInim, valFinm, codUni, codGen, codCom, codtipo, edaIni, edaFin);
					}
			
			}
			res.sendRedirect("lab_examen.jsp");
			
		}
	////////////////////////////EDAD y GENERO////////////////	
		if(radio.equals("62")){
			String codtipo="";
			if((codAex!="")&&(codSex=="")){
				
				codSex="0";		
				try {
					mcs.CrearPrograma(cero, cero, blanco, blanco, nomb, cero, cero, cero, cero, cero, fecha, hora, CodUsu);
					rs1=mcs.ObtenerCodigoPrograma(nomb, fecha, hora, CodUsu);
					if(rs1.next()){
						CodPrograma=rs1.getString(1);
					}
					rs1.getStatement().getConnection().close();
				} catch (SQLException e) {
					System.out.print("Error ControlCrearSubExa>>ObtenerCodigoPrograma "+e);
					e.printStackTrace();
				}	
				mce.insertarTipoTextoArea(nomb, codAex, codSex, tipo);
				rs=mce.obtenerCodigoExamenArea(nomb, codAex);
				try {
					while(rs.next()){
						codExa=rs.getString(1);					
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					System.out.println("ERROR EN CONTROLADOR:_ "+e);
					e.printStackTrace();
				}
				sa.InsertarProg_Lab(codExa, CodPrograma, tipo_examen);
				 String tiporango="EDAD";
				mce.insertipo(tiporango,codExa);
				rs=mce.obtenertipo(codExa);
				try {
					while(rs.next()){
						codtipo=rs.getString(1);					
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					System.out.println("ERROR EN CONTROLADOR:_ "+e);
					e.printStackTrace();
				}
				mce.insertarTipoRangoArea(valIni, valFin, codUni, codGen, codCom, codtipo, edaIni, edaFin);
				
			}else{
				if((codAex!="")&&(codSex!="")){
					codAex="0";				
					mce.insertarTipoTextoArea(nomb, codAex, codSex, tipo);
					rs=mce.obtenerCodigoExamenArea(nomb, codAex);
					try {
						while(rs.next()){
							codExa=rs.getString(1);					
						}
						rs.getStatement().getConnection().close();
					} catch (SQLException e) {
						System.out.println("ERROR EN CONTROLADOR:_ "+e);
						e.printStackTrace();
					}
					String tiporango="EDAD";
					mce.insertipo(tiporango,codExa);
					rs=mce.obtenertipo(codExa);
					try {
						while(rs.next()){
							codtipo=rs.getString(1);					
						}
						rs.getStatement().getConnection().close();
					} catch (SQLException e) {
						System.out.println("ERROR EN CONTROLADOR:_ "+e);
						e.printStackTrace();
					}
					mce.insertarTipoRangoArea(valIni, valFin, codUni, codGen, codCom, codtipo, edaIni, edaFin);
				}
			}
			
					
			res.sendRedirect("lab_examen.jsp");
		}	
		
////////////////////////////si es tipo rango EDAD/////////////////////////////////////	
		if(radio.equals("61")){
			String codtipo="";
			if((codAex!="")&&(codSex=="")){
				
				codSex="0";		
				try {
					mcs.CrearPrograma(cero, cero, blanco, blanco, nomb, cero, cero, cero, cero, cero, fecha, hora, CodUsu);
					rs1=mcs.ObtenerCodigoPrograma(nomb, fecha, hora, CodUsu);
					if(rs1.next()){
						CodPrograma=rs1.getString(1);
					}
					rs1.getStatement().getConnection().close();
				} catch (SQLException e) {
					System.out.print("Error ControlCrearSubExa>>ObtenerCodigoPrograma "+e);
					e.printStackTrace();
				}				
				mce.insertarTipoTextoArea(nomb, codAex, codSex, tipo);
				rs=mce.obtenerCodigoExamenArea(nomb, codAex);
				try {
					while(rs.next()){
						codExa=rs.getString(1);					
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					System.out.println("ERROR EN CONTROLADOR:_ "+e);
					e.printStackTrace();
				}
				 String tiporango="EDAD";
				 sa.InsertarProg_Lab(codExa, CodPrograma, tipo_examen);
				mce.insertipo(tiporango,codExa);
				rs=mce.obtenertipo(codExa);
				try {
					while(rs.next()){
						codtipo=rs.getString(1);					
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					System.out.println("ERROR EN CONTROLADOR:_ "+e);
					e.printStackTrace();
				}
				mce.insertarTipoRangoArea(valIni, valFin, codUni, codGen, codCom, codtipo, edaIni, edaFin);
				
			}else{
				if((codAex!="")&&(codSex!="")){
					codAex="0";
					
					mce.insertarTipoTextoArea(nomb, codAex, codSex, tipo);
					rs=mce.obtenerCodigoExamenArea(nomb, codAex);
					try {
						while(rs.next()){
							codExa=rs.getString(1);					
						}
						rs.getStatement().getConnection().close();
					} catch (SQLException e) {
						System.out.println("ERROR EN CONTROLADOR:_ "+e);
						e.printStackTrace();
					}
					String tiporango="EDAD";
					mce.insertipo(tiporango,codExa);
					rs=mce.obtenertipo(codExa);
					try {
						while(rs.next()){
							codtipo=rs.getString(1);					
						}
						rs.getStatement().getConnection().close();
					} catch (SQLException e) {
						System.out.println("ERROR EN CONTROLADOR:_ "+e);
						e.printStackTrace();
					}
					mce.insertarTipoRangoArea(valIni, valFin, codUni, codGen, codCom, codtipo, edaIni, edaFin);
				}
			}
			
					
			res.sendRedirect("lab_examen.jsp");
		}	
		
////////////////////////////si es tipo rango GENERAL/////////////////////////////////////	
		if(radio.equals("6")){
			String codtipo="";
			if((codAex!="")&&(codSex=="")){
				
				codSex="0";		
				try {
					mcs.CrearPrograma(cero, cero, blanco, blanco, nomb, cero, cero, cero, cero, cero, fecha, hora, CodUsu);
					rs1=mcs.ObtenerCodigoPrograma(nomb, fecha, hora, CodUsu);
					if(rs1.next()){
						CodPrograma=rs1.getString(1);
					}
					rs1.getStatement().getConnection().close();
				} catch (SQLException e) {
					System.out.print("Error ControlCrearSubExa>>ObtenerCodigoPrograma "+e);
					e.printStackTrace();
				}				
				mce.insertarTipoTextoArea(nomb, codAex, codSex, tipo);
				rs=mce.obtenerCodigoExamenArea(nomb, codAex);
				try {
					while(rs.next()){
						codExa=rs.getString(1);					
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					System.out.println("ERROR EN CONTROLADOR:_ "+e);
					e.printStackTrace();
				}
				sa.InsertarProg_Lab(codExa, CodPrograma, tipo_examen);
				 String tiporango="GENERAL";
				mce.insertipo(tiporango,codExa);
				rs=mce.obtenertipo(codExa);
				try {
					while(rs.next()){
						codtipo=rs.getString(1);					
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					System.out.println("ERROR EN CONTROLADOR:_ "+e);
					e.printStackTrace();
				}
				mce.insertarTipoRangoArea(valIni, valFin, codUni, codGen, codCom, codtipo, edaIni, edaFin);
				
			}else{
				if((codAex!="")&&(codSex!="")){
					codAex="0";
				
					mce.insertarTipoTextoArea(nomb, codAex, codSex, tipo);
					rs=mce.obtenerCodigoExamenArea(nomb, codAex);
					try {
						while(rs.next()){
							codExa=rs.getString(1);					
						}
						rs.getStatement().getConnection().close();
					} catch (SQLException e) {
						System.out.println("ERROR EN CONTROLADOR:_ "+e);
						e.printStackTrace();
					}
					String tiporango="GENERAL";
					mce.insertipo(tiporango,codExa);
					rs=mce.obtenertipo(codExa);
					try {
						while(rs.next()){
							codtipo=rs.getString(1);					
						}
						rs.getStatement().getConnection().close();
					} catch (SQLException e) {
						System.out.println("ERROR EN CONTROLADOR:_ "+e);
						e.printStackTrace();
					}
					mce.insertarTipoRangoArea(valIni, valFin, codUni, codGen, codCom, codtipo, edaIni, edaFin);
				}
			}
			
					
			res.sendRedirect("lab_examen.jsp");
		}	
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String va = req.getParameter("valor");	
		String area = req.getParameter("area");
		String nom=req.getParameter("unidad");
		String codarea=req.getParameter("codigoarea");
		String nomsub=req.getParameter("nomsubarea");
		String nomArea=req.getParameter("codarea");
		String nomGen=req.getParameter("nomGen");
		PrintWriter out=res.getWriter();
		MetodoCrearExamen mce=new MetodoCrearExamen();
		ResultSet rs=null;
		if(va.equals("1")){
			out.println("<table id='prueba' width='100%' name='prueba'><tr><td colspan='4' align='center' class='style11' id='cabecera2'>SI PERTENECE A GRUPO </td></tr><tr class='style6'><td width='222' align='left'>ESCOJA EL GRUPO </td><td colspan='3' align='left'><label><select name='cmbareaA' id='cmbareaA' onchange='codarea()'><option>SELECCIONE</option></select><b class='style8'>*<b/></label></td></tr><tr><td align='left' class='style6'>ESCRIBA EL NOMBRE DEL EXAMEN </td><td width='205' align='left'><label><input name='txtnomexam' type='text' id='txtnomexam' /><b class='style8'>*</b></label></td><td width='119'>&nbsp;</td><td width='106'>&nbsp;</td></tr><tr><td colspan='4' align='center' class='style11' id='cabecera2'>TIPO RANGO </td></tr><tr><td align='left' class='style6'><input name='radiobutton1' type='radio' value='radiobutton1' id='13' onclick='RadioTipo1()' />General</td><td rowspan='4'>&nbsp;</td><td rowspan='4'>&nbsp;</td></tr><tr><td align='left'class='style6'><input name='radiobutton1' type='radio' value='radiobutton1' id='14' onclick='RadioTipo1()' />Por Genero</td></tr><tr><td align='left' class='style6'><input name='radiobutton1' type='radio' value='radiobutton1' id='15'onclick='RadioTipo1()' />Por Edad</td></tr><tr><td align='left' class='style6'><input name='radiobutton1' type='radio' value='radiobutton1' id='16' onclick='RadioTipo1()' />Por Edad y Sexo</td></tr><tr><td colspan='4' align='center' class='style11' id='cabecera2'>TIPO TEXTO </td></tr><tr><td align='left' class='style6'><input name='radiobutton1' type='radio' value='radiobutton1' id='5' onclick='RadioTipo1()' />Texto</td><td>&nbsp;</td><td>&nbsp;</td></tr></table>");
		}
		if(va.equals("2")){
		out.println("<table id='prueba' name='prueba' width='100%' border='0'><tr><td colspan='5' align='center' class='style11' id='cabecera2'><span class='style11'>SI PERTENECE A SUBGRUPO</span> </td></tr><tr><td width='215' class='style6' align='left'>ESCOJA EL GRUPO </td><td colspan='4' align='left'><label><select name='cmbareaA' onchange='subareas()' id='cmbareaA'><option>SELECCIONE</option></select><span class='style1'><b class='style8'>*</b></span></label></td></tr><tr><td align='left' class='style6'>ESCOJA EL SUBGRUPO </td><td colspan='4' align='left'><label><select name='cmbsubareaS' id='cmbsubareaS'onchange='codsubarea()'><option>SELECCIONE</option></select><span class='style8'><b>*</b> </span></label></td></tr><tr><td align='left' class='style6'>ESCRIBA EL NOMBRE DEL EXAMEN </td><td width='210' align='left'><label><input name='txtnomexam' type='text' id='txtnomexam'><span class='style8'><b>*</b></span></label></td><td width='110'><label></label></td><td width='113'><label></label></td></tr><tr><td colspan='4' align='center' class='style11' id='cabecera2'>TIPO RANGO </td></tr><tr><td align='left' class='style6'><span ><input name='radiobutton1' type='radio' value='radiobutton1' id='13' onclick='RadioTipo1()' />General</span></td><td rowspan='4'>&nbsp;</td><td rowspan='4'>&nbsp;</td></tr><tr><td align='left' class='style6'><span ><input name='radiobutton1' type='radio' value='radiobutton1' id='14' onclick='RadioTipo1()' />Por Genero</span></td></tr><tr><td align='left' class='style6'><span ><input name='radiobutton1' type='radio' value='radiobutton1' id='15'onclick='RadioTipo1()' />Por Edad</span></td></tr><tr><td align='left' class='style6'><input name='radiobutton1' type='radio' value='radiobutton1' id='16' onclick='RadioTipo1()' /><span >Por Edad y Sexo</span></td></tr><tr><td colspan='4' align='center' class='style11' id='cabecera2'>TIPO TEXTO </td></tr><tr><td align='left' class='style6'><input name='radiobutton1' type='radio' value='radiobutton1' id='5' onclick='RadioTipo1()'/>Texto</td><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr></table>");
		}
		if(va.equals("3")){
		rs=mce.obtenerArea() ;
		String v[] = new String[1000];
		try{
			int c=0;
			while(rs.next()){
				v[c]=rs.getString(2);
				out.println(v[c]+"_");				                              
				c++;
			}	
			rs.getStatement().getConnection().close();
		}
		 catch(SQLException e1){
			 System.out.println("ERROR EN CONTROLADOR_: "+e1);
			 e1.getMessage();
		  }
		}		
		if(va.equals("4")){				
			rs=mce.obtenerSubArea(area);
			String v[]=new String [1000];
			try{
				int c=0;
				while(rs.next()){
					v[c]=rs.getString(2);
					out.println(v[c]+"_");				
					c++;
				}
				rs.getStatement().getConnection().close();			
			}
			 catch(SQLException e1){
				 System.out.println("ERROR EN CONTROLADOR_: "+e1);
				 e1.getMessage();
			  }
		}		
		if(va.equals("5")){
			//System.out.println("entro a va = 5");
			out.println("<table id='prueba' name='prueba' width='100%'><tr><td align='center'><input name='btningresar' class='boton4' type='button' id='btningresar' onclick='verificar1()' value='Ingresar' /></td></tr></table>");
			
		}
		  if(va.equals("6")){
			//  System.out.println("entro a va = 6");
			  out.println("<table width='100%'><tr><td width='168' class='style6'>Genero</td><td width='114' class='style6'>Valor Inicial </td><td width='106' class='style6'>Valor Final </td><td width='158' class='style6'>Unidad</td> <td width='140'>&nbsp;</td></tr><tr><td><label><select name='cmbgenero' id='cmbgenero'>  <option>SELECCIONE</option></select></label></td><td><label><input name='txtvalini' type='text' id='txtvalini' size='10' maxlength='10' /></label></td><td><label><input name='txtvalfin' type='text' id='txtvalfin' size='10' maxlength='10' /></label></td><td><label><select name='cmbunidad' id='cmbunidad'><option>SELECCIONE</option></select></label></td><td><label><input name='btncomentario' type='button' class='boton4' id='btncomentario' value='Comentario' /></label></td></tr></table>");
		  }
		if(va.equals("7")){
			rs=mce.obtenerNombreGenero();
			String v[] = new String[1000];
			try{
				int c=0;
				while(rs.next()){
					v[c]=rs.getString(2);
					out.println(v[c]+"_");				
					c++;
				}
				rs.getStatement().getConnection().close();
			}
			 catch(SQLException e1){
				 System.out.println("ERROR_: "+e1);
				 e1.getMessage();
			  }
			out.print("Masculino");
			
		}
		
		if(va.equals("8")){
			rs=mce.obtenerCodigoGenero(nomGen) ;
			String v[] = new String[1000];
			try{
				int c=0;
				while(rs.next()){
					v[c]=rs.getString(1);
					out.println(v[c]+"_");				
					c++;
				}	
				rs.getStatement().getConnection().close();
			}
			 catch(SQLException e1){
				 System.out.println("ERROR_: "+e1);
				 e1.getMessage();
			  }	
		}
		
	
		if(va.equals("9")){
			rs=mce.obtenerUnidad() ;
			String v[] = new String[1000];
			try{
				int c=0;
				while(rs.next()){
					v[c]=rs.getString(2);
					if(v[c].equals("9")){
						for(int i=0;i<v[c].length();i++){
			     		      v[c]=v[c].replace('9','%');
			     		    }
					}
					out.println(v[c]+"_");				
					c++;
				}
				rs.getStatement().getConnection().close();
			}
			 catch(SQLException e1){
				 System.out.println("ERROR_: "+e1);
				 e1.getMessage();
			  }	
		}
		
		if(va.equals("10")){
			//System.out.println("NOMBRE DE LA UNIDAD   "+nom);
			int i;
			for(i=0;i<nom.length();i++){
				nom=nom.replace('9','%');
			    }
			
			rs=mce.obtenerCodigoUnidad(nom) ;
			String v[] = new String[1000];
			try{
				int c=0;
				while(rs.next()){
					v[c]=rs.getString(1);
					out.println(v[c]+"_");				
					c++;
				}	
				rs.getStatement().getConnection().close();
			}
			 catch(SQLException e1){
				 System.out.println("ERROR_: "+e1);
				 e1.getMessage();
			  }	
		}
		
		if(va.equals("11")){
			//System.out.println("entro a va = 11");
			rs=mce.obtenerCodigoArea(nomArea) ;
			String v[] = new String[1000];
			try{
				int c=0;
				while(rs.next()){
					v[c]=rs.getString(1);
					out.println(v[c]+"_");				
					c++;
				}	
				rs.getStatement().getConnection().close();
			}
			 catch(SQLException e1){
				 System.out.println("ERROR_: "+e1);
				 e1.getMessage();
			  }				
		}
		
		if(va.equals("12")){
			rs=mce.obtenerCodigoSubArea(nomsub, codarea) ;
			String v[] = new String[1000];
			try{
				int c=0;
				while(rs.next()){
					v[c]=rs.getString(1);
					out.println(v[c]+"_");	
					c++;
				}	
				rs.getStatement().getConnection().close();
			}
			 catch(SQLException e1){
				 System.out.println("ERROR_: "+e1);
				 e1.getMessage();
			  }				
		}
		if(va.equals("13")){
			//System.out.println("entro a va = 13");
			out.println("<table width='100%' border='0'><tr><td width='118' class='style6'>Valor Inicial </td><td width='109' class='style6'>Valor Final </td><td width='207' class='style6'>Unidad</td><td width='108'>&nbsp;</td></tr><tr><td><label><input name='txtvalini' type='text' id='txtvalini' size='10' maxlength='10' /></label></td><td><label><input name='txtvalfin' type='text' id='txtvalfin' size='10' maxlength='10' /></label></td><td><label><select name='cmbunidad' id='cmbunidad' onchange='unidades()'><option>SELECCIONE</option></select></label></td><td><label><input name='btnguardarNormal' class='boton4' type='button' id='btnguardarNormal' value='GUARDAR' onclick='InsertarGeneral()' /></label></td></tr></table>");
		}
		if(va.equals("14")){
		//	System.out.println("entro a va = 14");
			out.println("<table width='100%' border='0'><tr><td width='168' class='style6'>GENERO</td><td width='114' class='style6'>Valor Inicial </td><td width='106' class='style6'>Valor Final </td><td width='158' class='style6'>Unidad</td> <td width='140'>&nbsp;</td></tr><tr><td class='style6'><label>HOMBRE</label></td><td><label><input name='txtvalinihombre' type='text' id='txtvalinihombre' size='10' maxlength='10' /></label></td><td><label><input name='txtvalfinhombre' type='text' id='txtvalfinhombre' size='10' maxlength='10' /></label></td><td><label><select name='cmbunidad' id='cmbunidad' onchange='unidades()'><option>SELECCIONE</option></select></label></td><td><label><input name='btncomentario' class='boton4' type='button' id='btncomentario' value='GUARDAR' onclick='InsertarGenero()' /></label></td></tr><tr><td class='style6'><label>MUJER</label></td><td><label><input name='txtvalinimujer' type='text' id='txtvalinimujer' size='10' maxlength='10' /></label></td><td><label><input name='txtvalfinmujer' type='text' id='txtvalfinmujer' size='10' maxlength='10' /></label></td></tr></table>");
		}
		if(va.equals("15")){
		//	System.out.println("entro a va = 15");
			out.println("<table width='100%' border='0'><tr><td width='108' class='style6'>Edad Inicial </td><td width='101' class='style6'>Edad Final </td><td width='101' class='style6'>Valor Inicial </td><td width='106' class='style6'>Valor Final </td><td width='124' class='style6'>Unidad</td><td width='140'>&nbsp;</td></tr><tr><td><label><input name='txtedadini' type='text' id='txtedadini' size='10' maxlength='10' /></label></td><td><label><input name='txtedadfin' type='text' id='txtedadfin' size='10' maxlength='10' /></label></td><td><label><input name='txtvalini' type='text' id='txtvalini' size='10' maxlength='10' /></label></td><td><label><input name='txtvalfin' type='text' id='txtvalfin' size='10' maxlength='10' /></label></td><td><label><select name='cmbunidad' id='cmbunidad' onchange='unidades()'><option>SELECCIONE</option></select></label></td><td><label><input name='btncomentario' class='boton4' type='button' id='btncomentario' value='Comentario' onclick='InsertarEdad()' /></label></td></tr></table>");
		}
		if(va.equals("16")){
		//	System.out.println("entro a va = 16");
			out.println("<table width='100%' border='0'><tr><td width='110' class='style6'>Genero</td><td width='76' class='style6'>Edad Inicial </td><td width='69' class='style6'>Edad Final </td><td width='83' class='style6'>Valor Inicial </td><td width='71' class='style6'>Valor Final </td><td width='125' class='style6'>Unidad</td><td width='140'>&nbsp;</td></tr><tr><td><label><select name='cmbgenero' id='cmbgenero' onchange='genero()'>  <option>SELECCIONE</option></select></label></td><td><label><input name='txtedadini' type='text' id='txtedadini' size='10' maxlength='10' /></label></td><td><label><input name='txtedadfin' type='text' id='txtedadfin' size='10' maxlength='10' /></label></td><td><label><input name='txtvalini' type='text' id='txtvalini' size='10' maxlength='10' /></label></td><td><label><input name='txtvalfin' type='text' id='txtvalfin' size='10' maxlength='10' /></label></td><td><label><select name='cmbunidad' id='cmbunidad' onchange='unidades()'><option>SELECCIONE</option></select></label></td><td><label><input name='btncomentario' class='boton4' type='button' id='btncomentario' value='Comentario' onclick='InsertarEdadGenero()' /></label></td></tr></table>");
		}
		
	}
 
}
