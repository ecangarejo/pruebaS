package cal_controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cal_metodo.MetodoCalidad;

public class ControlIndicadores extends HttpServlet{
			protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				// TODO Auto-generated method stub
				super.doGet(request, response);
				}
			protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
				HttpSession session = req.getSession(true);
			
				String va = req.getParameter("valor");
				res.setContentType("text/html;charset=UTF-8");
				PrintWriter out;
				out=res.getWriter();
				ResultSet rs=null;
				ResultSet rs1=null;
				ResultSet rs2=null;
				ResultSet rs3=null;
				ResultSet rs4=null;
				ResultSet rs5=null;
			
				MetodoCalidad mc=new MetodoCalidad();
				
				if(va.equals("RM")){
					try {
						String FechaIN=req.getParameter("FechaIN");
						String FechaFI=req.getParameter("FechaFI");
						String cmbTipo=req.getParameter("cmbTipo");
						String FechaIni,DiaI,MesI,AnoI=null;
						DiaI=FechaIN.substring(0,2);
						MesI=FechaIN.substring(3,5);
						AnoI=FechaIN.substring(6,10);
						FechaIni=AnoI+"-"+MesI+"-"+DiaI;			
						String FechaFin,DiaF,MesF,AnoF=null;
						DiaF=FechaFI.substring(0,2);
						MesF=FechaFI.substring(3,5);
						AnoF=FechaFI.substring(6,10);
						FechaFin=AnoF+"-"+MesF+"-"+DiaF;
						String Sql="";
						int co=0;
						
						//out.print("<table width='100%' border='1' cellspacing='0' cellpadding='0'><tr class='boton4' ><td align='center'> REPORTE DE MORBILIDAD ENTRE "+FechaIN+" Y "+FechaFI+" </td></tr></table>");
						out.print("<table width='100%' border='1' cellspacing='0' cellpadding='0'>");
						if(cmbTipo.equals("CONS")){
							int tm=0;
							int tf=0;
							//se hace la consulta de los diagnosticos mas frecuentes
							/***********************************************MORBILIDAD EN URGENCIAS***************************/
							out.print("<tr   class='boton4' ><td colspan='16' align='center' >MORBILIDAD EN LA URGENCIA ENTRE "+FechaIN+" Y "+FechaFI+" </td></tr>");
							out.print("<tr><td width='44%' colspan='2' rowspan='2'>DIAGNOSTICO</td><td colspan='2'><div align='center'>0-11 Meses </div></td><td colspan='2'><div align='center'>1-4 A&ntilde;os </div></td><td colspan='2'><div align='center'>5-14 A&ntilde;os </div></td><td colspan='2'><div align='center'>15-44 A&ntilde;os </div></td><td colspan='2'><div align='center'>45-59 A&ntilde;os </div></td><td colspan='2'><div align='center'>&gt;60 A&ntilde;os </div></td><td colspan='2'><div align='center'>Total</div></td></tr>");
							out.print("<tr><td width='4%'><div align='center'>M</div></td><td width='4%'><div align='center'>F</div></td><td width='4%'><div align='center'>M</div></td><td width='4%'><div align='center'>F</div></td><td width='4%'><div align='center'>M</div></td><td width='4%'><div align='center'>F</div></td><td width='4%'><div align='center'>M</div></td><td width='4%'><div align='center'>F</div></td><td width='4%'><div align='center'>M</div></td><td width='4%'><div align='center'>F</div></td><td width='4%'><div align='center'>M</div></td><td width='4%'><div align='center'>F</div></td><td width='4%'><div align='center'>M</div></td><td width='4%'><div align='center'>F</div></td></tr>");
							rs=mc.MorbilidadUrgenciaConsolidado(FechaIni, FechaFin);
							while(rs.next()){
								tm=0;
								tf=0;
								out.print("<tr><td width='4%'>"+rs.getString("codigoCIE")+"</td><td>"+rs.getString("Dx")+"</td>");								
								/****************************MENOR A 1 AÑO******************************/
								Sql=" where a.edad<=0 and a.codigoCIE='"+rs.getString("codigoCIE")+"' and a.genero='Masculino' group by a.Dx,a.genero order by Total desc LIMIT 15 ";
								rs1=mc.MorbilidadUrgencia(FechaIni, FechaFin, Sql);
								if(rs1.next()){
									out.print(" <td><div align='center'>"+rs1.getString("Total")+"</div></td>");
									tm=tm+rs1.getInt("Total");
								}else{
									out.print(" <td><div align='center'>0</div></td>");
								}
								rs1.getStatement().getConnection().close();								
								Sql=" where a.edad<=0 and a.codigoCIE='"+rs.getString("codigoCIE")+"' and a.genero='Femenino' group by a.Dx,a.genero order by Total desc LIMIT 15 ";
								rs1=mc.MorbilidadUrgencia(FechaIni, FechaFin, Sql);
								if(rs1.next()){
									out.print(" <td><div align='center'>"+rs1.getString("Total")+"</div></td>");
									tf=tf+rs1.getInt("Total");
								}else{
									out.print(" <td><div align='center'>0</div></td>");
								}
								rs1.getStatement().getConnection().close();
								/****************************************************************************/
								
								/****************************ENTRE 1 Y 4 AÑOS******************************/
								Sql=" where a.edad between '1' and '4' and a.codigoCIE='"+rs.getString("codigoCIE")+"' and a.genero='Masculino' group by a.Dx,a.genero order by Total desc LIMIT 15 ";
								rs1=mc.MorbilidadUrgencia(FechaIni, FechaFin, Sql);
								if(rs1.next()){
									out.print(" <td><div align='center'>"+rs1.getString("Total")+"</div></td>");
									tm=tm+rs1.getInt("Total");
								}else{
									out.print(" <td><div align='center'>0</div></td>");
								}
								rs1.getStatement().getConnection().close();
								
								Sql=" where a.edad between '1' and '4' and a.codigoCIE='"+rs.getString("codigoCIE")+"' and a.genero='Femenino' group by a.Dx,a.genero order by Total desc LIMIT 15 ";
								rs1=mc.MorbilidadUrgencia(FechaIni, FechaFin, Sql);
								if(rs1.next()){
									out.print(" <td><div align='center'>"+rs1.getString("Total")+"</div></td>");
									tf=tf+rs1.getInt("Total");
								}else{
									out.print(" <td><div align='center'>0</div></td>");
								}
								rs1.getStatement().getConnection().close();
								/****************************************************************************/
								
								/****************************ENTRE 5 Y 14 AÑOS******************************/
								Sql=" where a.edad between '5' and '14' and a.codigoCIE='"+rs.getString("codigoCIE")+"' and a.genero='Masculino' group by a.Dx,a.genero order by Total desc LIMIT 15 ";
								rs1=mc.MorbilidadUrgencia(FechaIni, FechaFin, Sql);
								if(rs1.next()){
									out.print(" <td><div align='center'>"+rs1.getString("Total")+"</div></td>");
									tm=tm+rs1.getInt("Total");
								}else{
									out.print(" <td><div align='center'>0</div></td>");
								}
								rs1.getStatement().getConnection().close();
								
								Sql=" where a.edad between '5' and '14' and a.codigoCIE='"+rs.getString("codigoCIE")+"' and a.genero='Femenino' group by a.Dx,a.genero order by Total desc LIMIT 15 ";
								rs1=mc.MorbilidadUrgencia(FechaIni, FechaFin, Sql);
								if(rs1.next()){
									out.print(" <td><div align='center'>"+rs1.getString("Total")+"</div></td>");
									tf=tf+rs1.getInt("Total");
								}else{
									out.print(" <td><div align='center'>0</div></td>");
								}
								rs1.getStatement().getConnection().close();
								/****************************************************************************/
								
								/****************************ENTRE 15 Y 44 AÑOS******************************/
								Sql=" where a.edad between '15' and '44' and a.codigoCIE='"+rs.getString("codigoCIE")+"' and a.genero='Masculino' group by a.Dx,a.genero order by Total desc LIMIT 15 ";
								rs1=mc.MorbilidadUrgencia(FechaIni, FechaFin, Sql);
								if(rs1.next()){
									out.print(" <td><div align='center'>"+rs1.getString("Total")+"</div></td>");
									tm=tm+rs1.getInt("Total");
								}else{
									out.print(" <td><div align='center'>0</div></td>");
								}
								rs1.getStatement().getConnection().close();
								
								Sql=" where a.edad between '15' and '44' and a.codigoCIE='"+rs.getString("codigoCIE")+"' and a.genero='Femenino' group by a.Dx,a.genero order by Total desc LIMIT 15 ";
								rs1=mc.MorbilidadUrgencia(FechaIni, FechaFin, Sql);
								if(rs1.next()){
									out.print(" <td><div align='center'>"+rs1.getString("Total")+"</div></td>");
									tf=tf+rs1.getInt("Total");
								}else{
									out.print(" <td><div align='center'>0</div></td>");
								}
								rs1.getStatement().getConnection().close();
								/****************************************************************************/
								
								/****************************ENTRE 45 Y 59 AÑOS******************************/
								Sql=" where a.edad between '45' and '59' and a.codigoCIE='"+rs.getString("codigoCIE")+"' and a.genero='Masculino' group by a.Dx,a.genero order by Total desc LIMIT 15 ";
								rs1=mc.MorbilidadUrgencia(FechaIni, FechaFin, Sql);
								if(rs1.next()){
									out.print(" <td><div align='center'>"+rs1.getString("Total")+"</div></td>");
									tm=tm+rs1.getInt("Total");
								}else{
									out.print(" <td><div align='center'>0</div></td>");
								}
								rs1.getStatement().getConnection().close();
								
								Sql=" where a.edad between '45' and '59' and a.codigoCIE='"+rs.getString("codigoCIE")+"' and a.genero='Femenino' group by a.Dx,a.genero order by Total desc LIMIT 15 ";
								rs1=mc.MorbilidadUrgencia(FechaIni, FechaFin, Sql);
								if(rs1.next()){
									out.print(" <td><div align='center'>"+rs1.getString("Total")+"</div></td>");
									tf=tf+rs1.getInt("Total");
								}else{
									out.print(" <td><div align='center'>0</div></td>");
								}
								rs1.getStatement().getConnection().close();
								/****************************************************************************/
								
								/****************************MAYOR A 60 AÑOS******************************/
								Sql=" where a.edad>=60 and a.codigoCIE='"+rs.getString("codigoCIE")+"' and a.genero='Masculino' group by a.Dx,a.genero order by Total desc LIMIT 15 ";
								rs1=mc.MorbilidadUrgencia(FechaIni, FechaFin, Sql);
								if(rs1.next()){
									out.print(" <td><div align='center'>"+rs1.getString("Total")+"</div></td>");
									tm=tm+rs1.getInt("Total");
								}else{
									out.print(" <td><div align='center'>0</div></td>");
								}
								rs1.getStatement().getConnection().close();
								
								Sql=" where a.edad>=60 and a.codigoCIE='"+rs.getString("codigoCIE")+"' and a.genero='Femenino' group by a.Dx,a.genero order by Total desc LIMIT 15 ";
								rs1=mc.MorbilidadUrgencia(FechaIni, FechaFin, Sql);
								if(rs1.next()){
									out.print(" <td><div align='center'>"+rs1.getString("Total")+"</div></td>");
									tf=tf+rs1.getInt("Total");
								}else{
									out.print(" <td><div align='center'>0</div></td>");
								}
								rs1.getStatement().getConnection().close();
								/****************************************************************************/
								out.print("  <td><div align='center'>"+tm+"</div></td><td><div align='center'>"+tf+"</div></td>");
								out.print(" </tr>");
							}
							rs.getStatement().getConnection().close();
							/*******************************************************MORBILIDAD EN HOSPITALIZACION********************************/
							out.print("<tr   class='boton4' ><td colspan='16' align='center' >MORBILIDAD EN HOSPITALIZACION ENTRE "+FechaIN+" Y "+FechaFI+" </td></tr>");
							out.print("<tr><td width='44%' colspan='2' rowspan='2'>DIAGNOSTICO</td><td colspan='2'><div align='center'>0-11 Meses </div></td><td colspan='2'><div align='center'>1-4 A&ntilde;os </div></td><td colspan='2'><div align='center'>5-14 A&ntilde;os </div></td><td colspan='2'><div align='center'>15-44 A&ntilde;os </div></td><td colspan='2'><div align='center'>45-59 A&ntilde;os </div></td><td colspan='2'><div align='center'>&gt;60 A&ntilde;os </div></td><td colspan='2'><div align='center'>Total</div></td></tr>");
							out.print("<tr><td width='4%'><div align='center'>M</div></td><td width='4%'><div align='center'>F</div></td><td width='4%'><div align='center'>M</div></td><td width='4%'><div align='center'>F</div></td><td width='4%'><div align='center'>M</div></td><td width='4%'><div align='center'>F</div></td><td width='4%'><div align='center'>M</div></td><td width='4%'><div align='center'>F</div></td><td width='4%'><div align='center'>M</div></td><td width='4%'><div align='center'>F</div></td><td width='4%'><div align='center'>M</div></td><td width='4%'><div align='center'>F</div></td><td width='4%'><div align='center'>M</div></td><td width='4%'><div align='center'>F</div></td></tr>");
							rs=mc.MorbilidadHospitalizacionConsolidado(FechaIni, FechaFin);
							while(rs.next()){
								tm=0;
								tf=0;
								out.print("<tr><td width='4%'>"+rs.getString("codigoCIE")+"</td><td>"+rs.getString("Dx")+"</td>");								
								/****************************MENOR A 1 AÑO******************************/
								Sql=" where a.edad<=0 and a.codigoCIE='"+rs.getString("codigoCIE")+"' and a.genero='Masculino' group by a.Dx,a.genero order by Total desc LIMIT 15 ";
								rs1=mc.MorbilidadHospitalizacion(FechaIni, FechaFin, Sql);
								if(rs1.next()){
									out.print(" <td><div align='center'>"+rs1.getString("Total")+"</div></td>");
									tm=tm+rs1.getInt("Total");
								}else{
									out.print(" <td><div align='center'>0</div></td>");
								}
								rs1.getStatement().getConnection().close();								
								Sql=" where a.edad<=0 and a.codigoCIE='"+rs.getString("codigoCIE")+"' and a.genero='Femenino' group by a.Dx,a.genero order by Total desc LIMIT 15 ";
								rs1=mc.MorbilidadHospitalizacion(FechaIni, FechaFin, Sql);
								if(rs1.next()){
									out.print(" <td><div align='center'>"+rs1.getString("Total")+"</div></td>");
									tf=tf+rs1.getInt("Total");
								}else{
									out.print(" <td><div align='center'>0</div></td>");
								}
								rs1.getStatement().getConnection().close();
								/****************************************************************************/
								
								/****************************ENTRE 1 Y 4 AÑOS******************************/
								Sql=" where a.edad between '1' and '4' and a.codigoCIE='"+rs.getString("codigoCIE")+"' and a.genero='Masculino' group by a.Dx,a.genero order by Total desc LIMIT 15 ";
								rs1=mc.MorbilidadHospitalizacion(FechaIni, FechaFin, Sql);
								if(rs1.next()){
									out.print(" <td><div align='center'>"+rs1.getString("Total")+"</div></td>");
									tm=tm+rs1.getInt("Total");
								}else{
									out.print(" <td><div align='center'>0</div></td>");
								}
								rs1.getStatement().getConnection().close();
								
								Sql=" where a.edad between '1' and '4' and a.codigoCIE='"+rs.getString("codigoCIE")+"' and a.genero='Femenino' group by a.Dx,a.genero order by Total desc LIMIT 15 ";
								rs1=mc.MorbilidadHospitalizacion(FechaIni, FechaFin, Sql);
								if(rs1.next()){
									out.print(" <td><div align='center'>"+rs1.getString("Total")+"</div></td>");
									tf=tf+rs1.getInt("Total");
								}else{
									out.print(" <td><div align='center'>0</div></td>");
								}
								rs1.getStatement().getConnection().close();
								/****************************************************************************/
								
								/****************************ENTRE 5 Y 14 AÑOS******************************/
								Sql=" where a.edad between '5' and '14' and a.codigoCIE='"+rs.getString("codigoCIE")+"' and a.genero='Masculino' group by a.Dx,a.genero order by Total desc LIMIT 15 ";
								rs1=mc.MorbilidadHospitalizacion(FechaIni, FechaFin, Sql);
								if(rs1.next()){
									out.print(" <td><div align='center'>"+rs1.getString("Total")+"</div></td>");
									tm=tm+rs1.getInt("Total");
								}else{
									out.print(" <td><div align='center'>0</div></td>");
								}
								rs1.getStatement().getConnection().close();
								
								Sql=" where a.edad between '5' and '14' and a.codigoCIE='"+rs.getString("codigoCIE")+"' and a.genero='Femenino' group by a.Dx,a.genero order by Total desc LIMIT 15 ";
								rs1=mc.MorbilidadHospitalizacion(FechaIni, FechaFin, Sql);
								if(rs1.next()){
									out.print(" <td><div align='center'>"+rs1.getString("Total")+"</div></td>");
									tf=tf+rs1.getInt("Total");
								}else{
									out.print(" <td><div align='center'>0</div></td>");
								}
								rs1.getStatement().getConnection().close();
								/****************************************************************************/
								
								/****************************ENTRE 15 Y 44 AÑOS******************************/
								Sql=" where a.edad between '15' and '44' and a.codigoCIE='"+rs.getString("codigoCIE")+"' and a.genero='Masculino' group by a.Dx,a.genero order by Total desc LIMIT 15 ";
								rs1=mc.MorbilidadHospitalizacion(FechaIni, FechaFin, Sql);
								if(rs1.next()){
									out.print(" <td><div align='center'>"+rs1.getString("Total")+"</div></td>");
									tm=tm+rs1.getInt("Total");
								}else{
									out.print(" <td><div align='center'>0</div></td>");
								}
								rs1.getStatement().getConnection().close();
								
								Sql=" where a.edad between '15' and '44' and a.codigoCIE='"+rs.getString("codigoCIE")+"' and a.genero='Femenino' group by a.Dx,a.genero order by Total desc LIMIT 15 ";
								rs1=mc.MorbilidadHospitalizacion(FechaIni, FechaFin, Sql);
								if(rs1.next()){
									out.print(" <td><div align='center'>"+rs1.getString("Total")+"</div></td>");
									tf=tf+rs1.getInt("Total");
								}else{
									out.print(" <td><div align='center'>0</div></td>");
								}
								rs1.getStatement().getConnection().close();
								/****************************************************************************/
								
								/****************************ENTRE 45 Y 59 AÑOS******************************/
								Sql=" where a.edad between '45' and '59' and a.codigoCIE='"+rs.getString("codigoCIE")+"' and a.genero='Masculino' group by a.Dx,a.genero order by Total desc LIMIT 15 ";
								rs1=mc.MorbilidadHospitalizacion(FechaIni, FechaFin, Sql);
								if(rs1.next()){
									out.print(" <td><div align='center'>"+rs1.getString("Total")+"</div></td>");
									tm=tm+rs1.getInt("Total");
								}else{
									out.print(" <td><div align='center'>0</div></td>");
								}
								rs1.getStatement().getConnection().close();
								
								Sql=" where a.edad between '45' and '59' and a.codigoCIE='"+rs.getString("codigoCIE")+"' and a.genero='Femenino' group by a.Dx,a.genero order by Total desc LIMIT 15 ";
								rs1=mc.MorbilidadHospitalizacion(FechaIni, FechaFin, Sql);
								if(rs1.next()){
									out.print(" <td><div align='center'>"+rs1.getString("Total")+"</div></td>");
									tf=tf+rs1.getInt("Total");
								}else{
									out.print(" <td><div align='center'>0</div></td>");
								}
								rs1.getStatement().getConnection().close();
								/****************************************************************************/
								
								/****************************MAYOR A 60 AÑOS******************************/
								Sql=" where a.edad>=60 and a.codigoCIE='"+rs.getString("codigoCIE")+"' and a.genero='Masculino' group by a.Dx,a.genero order by Total desc LIMIT 15 ";
								rs1=mc.MorbilidadHospitalizacion(FechaIni, FechaFin, Sql);
								if(rs1.next()){
									out.print(" <td><div align='center'>"+rs1.getString("Total")+"</div></td>");
									tm=tm+rs1.getInt("Total");
								}else{
									out.print(" <td><div align='center'>0</div></td>");
								}
								rs1.getStatement().getConnection().close();
								
								Sql=" where a.edad>=60 and a.codigoCIE='"+rs.getString("codigoCIE")+"' and a.genero='Femenino' group by a.Dx,a.genero order by Total desc LIMIT 15 ";
								rs1=mc.MorbilidadHospitalizacion(FechaIni, FechaFin, Sql);
								if(rs1.next()){
									out.print(" <td><div align='center'>"+rs1.getString("Total")+"</div></td>");
									tf=tf+rs1.getInt("Total");
								}else{
									out.print(" <td><div align='center'>0</div></td>");
								}
								rs1.getStatement().getConnection().close();
								/****************************************************************************/
								out.print("  <td><div align='center'>"+tm+"</div></td><td><div align='center'>"+tf+"</div></td>");
								out.print(" </tr>");
							}
							rs.getStatement().getConnection().close();
							/*******************************************************MORBILIDAD EN CONSULTA EXTERNA********************************/
							out.print("<tr   class='boton4' ><td colspan='16' align='center' >MORBILIDAD EN CONSULTA EXTERNA ENTRE "+FechaIN+" Y "+FechaFI+" </td></tr>");
							out.print("<tr><td width='44%' colspan='2' rowspan='2'>DIAGNOSTICO</td><td colspan='2'><div align='center'>0-11 Meses </div></td><td colspan='2'><div align='center'>1-4 A&ntilde;os </div></td><td colspan='2'><div align='center'>5-14 A&ntilde;os </div></td><td colspan='2'><div align='center'>15-44 A&ntilde;os </div></td><td colspan='2'><div align='center'>45-59 A&ntilde;os </div></td><td colspan='2'><div align='center'>&gt;60 A&ntilde;os </div></td><td colspan='2'><div align='center'>Total</div></td></tr>");
							out.print("<tr><td width='4%'><div align='center'>M</div></td><td width='4%'><div align='center'>F</div></td><td width='4%'><div align='center'>M</div></td><td width='4%'><div align='center'>F</div></td><td width='4%'><div align='center'>M</div></td><td width='4%'><div align='center'>F</div></td><td width='4%'><div align='center'>M</div></td><td width='4%'><div align='center'>F</div></td><td width='4%'><div align='center'>M</div></td><td width='4%'><div align='center'>F</div></td><td width='4%'><div align='center'>M</div></td><td width='4%'><div align='center'>F</div></td><td width='4%'><div align='center'>M</div></td><td width='4%'><div align='center'>F</div></td></tr>");
							rs=mc.MorbilidadConsultaExternaConsolidado(FechaIni, FechaFin);
							while(rs.next()){
								tm=0;
								tf=0;
								out.print("<tr><td width='4%'>"+rs.getString("codigoCIE")+"</td><td>"+rs.getString("Dx")+"</td>");								
								/****************************MENOR A 1 AÑO******************************/
								Sql=" where a.edad<=0 and a.codigoCIE='"+rs.getString("codigoCIE")+"' and a.genero='Masculino' group by a.Dx,a.genero order by Total desc LIMIT 15 ";
								rs1=mc.MorbilidadConsultaExternaC(FechaIni, FechaFin, Sql);
								if(rs1.next()){
									out.print(" <td><div align='center'>"+rs1.getString("Total")+"</div></td>");
									tm=tm+rs1.getInt("Total");
								}else{
									out.print(" <td><div align='center'>0</div></td>");
								}
								rs1.getStatement().getConnection().close();								
								Sql=" where a.edad<=0 and a.codigoCIE='"+rs.getString("codigoCIE")+"' and a.genero='Femenino' group by a.Dx,a.genero order by Total desc LIMIT 15 ";
								rs1=mc.MorbilidadConsultaExternaC(FechaIni, FechaFin, Sql);
								if(rs1.next()){
									out.print(" <td><div align='center'>"+rs1.getString("Total")+"</div></td>");
									tf=tf+rs1.getInt("Total");
								}else{
									out.print(" <td><div align='center'>0</div></td>");
								}
								rs1.getStatement().getConnection().close();
								/****************************************************************************/
								
								/****************************ENTRE 1 Y 4 AÑOS******************************/
								Sql=" where a.edad between '1' and '4' and a.codigoCIE='"+rs.getString("codigoCIE")+"' and a.genero='Masculino' group by a.Dx,a.genero order by Total desc LIMIT 15 ";
								rs1=mc.MorbilidadConsultaExternaC(FechaIni, FechaFin, Sql);
								if(rs1.next()){
									out.print(" <td><div align='center'>"+rs1.getString("Total")+"</div></td>");
									tm=tm+rs1.getInt("Total");
								}else{
									out.print(" <td><div align='center'>0</div></td>");
								}
								rs1.getStatement().getConnection().close();
								
								Sql=" where a.edad between '1' and '4' and a.codigoCIE='"+rs.getString("codigoCIE")+"' and a.genero='Femenino' group by a.Dx,a.genero order by Total desc LIMIT 15 ";
								rs1=mc.MorbilidadConsultaExternaC(FechaIni, FechaFin, Sql);
								if(rs1.next()){
									out.print(" <td><div align='center'>"+rs1.getString("Total")+"</div></td>");
									tf=tf+rs1.getInt("Total");
								}else{
									out.print(" <td><div align='center'>0</div></td>");
								}
								rs1.getStatement().getConnection().close();
								/****************************************************************************/
								
								/****************************ENTRE 5 Y 14 AÑOS******************************/
								Sql=" where a.edad between '5' and '14' and a.codigoCIE='"+rs.getString("codigoCIE")+"' and a.genero='Masculino' group by a.Dx,a.genero order by Total desc LIMIT 15 ";
								rs1=mc.MorbilidadConsultaExternaC(FechaIni, FechaFin, Sql);
								if(rs1.next()){
									out.print(" <td><div align='center'>"+rs1.getString("Total")+"</div></td>");
									tm=tm+rs1.getInt("Total");
								}else{
									out.print(" <td><div align='center'>0</div></td>");
								}
								rs1.getStatement().getConnection().close();
								
								Sql=" where a.edad between '5' and '14' and a.codigoCIE='"+rs.getString("codigoCIE")+"' and a.genero='Femenino' group by a.Dx,a.genero order by Total desc LIMIT 15 ";
								rs1=mc.MorbilidadConsultaExternaC(FechaIni, FechaFin, Sql);
								if(rs1.next()){
									out.print(" <td><div align='center'>"+rs1.getString("Total")+"</div></td>");
									tf=tf+rs1.getInt("Total");
								}else{
									out.print(" <td><div align='center'>0</div></td>");
								}
								rs1.getStatement().getConnection().close();
								/****************************************************************************/
								
								/****************************ENTRE 15 Y 44 AÑOS******************************/
								Sql=" where a.edad between '15' and '44' and a.codigoCIE='"+rs.getString("codigoCIE")+"' and a.genero='Masculino' group by a.Dx,a.genero order by Total desc LIMIT 15 ";
								rs1=mc.MorbilidadConsultaExternaC(FechaIni, FechaFin, Sql);
								if(rs1.next()){
									out.print(" <td><div align='center'>"+rs1.getString("Total")+"</div></td>");
									tm=tm+rs1.getInt("Total");
								}else{
									out.print(" <td><div align='center'>0</div></td>");
								}
								rs1.getStatement().getConnection().close();
								
								Sql=" where a.edad between '15' and '44' and a.codigoCIE='"+rs.getString("codigoCIE")+"' and a.genero='Femenino' group by a.Dx,a.genero order by Total desc LIMIT 15 ";
								rs1=mc.MorbilidadConsultaExternaC(FechaIni, FechaFin, Sql);
								if(rs1.next()){
									out.print(" <td><div align='center'>"+rs1.getString("Total")+"</div></td>");
									tf=tf+rs1.getInt("Total");
								}else{
									out.print(" <td><div align='center'>0</div></td>");
								}
								rs1.getStatement().getConnection().close();
								/****************************************************************************/
								
								/****************************ENTRE 45 Y 59 AÑOS******************************/
								Sql=" where a.edad between '45' and '59' and a.codigoCIE='"+rs.getString("codigoCIE")+"' and a.genero='Masculino' group by a.Dx,a.genero order by Total desc LIMIT 15 ";
								rs1=mc.MorbilidadConsultaExternaC(FechaIni, FechaFin, Sql);
								if(rs1.next()){
									out.print(" <td><div align='center'>"+rs1.getString("Total")+"</div></td>");
									tm=tm+rs1.getInt("Total");
								}else{
									out.print(" <td><div align='center'>0</div></td>");
								}
								rs1.getStatement().getConnection().close();
								
								Sql=" where a.edad between '45' and '59' and a.codigoCIE='"+rs.getString("codigoCIE")+"' and a.genero='Femenino' group by a.Dx,a.genero order by Total desc LIMIT 15 ";
								rs1=mc.MorbilidadConsultaExternaC(FechaIni, FechaFin, Sql);
								if(rs1.next()){
									out.print(" <td><div align='center'>"+rs1.getString("Total")+"</div></td>");
									tf=tf+rs1.getInt("Total");
								}else{
									out.print(" <td><div align='center'>0</div></td>");
								}
								rs1.getStatement().getConnection().close();
								/****************************************************************************/
								
								/****************************MAYOR A 60 AÑOS******************************/
								Sql=" where a.edad>=60 and a.codigoCIE='"+rs.getString("codigoCIE")+"' and a.genero='Masculino' group by a.Dx,a.genero order by Total desc LIMIT 15 ";
								rs1=mc.MorbilidadConsultaExternaC(FechaIni, FechaFin, Sql);
								if(rs1.next()){
									out.print(" <td><div align='center'>"+rs1.getString("Total")+"</div></td>");
									tm=tm+rs1.getInt("Total");
								}else{
									out.print(" <td><div align='center'>0</div></td>");
								}
								rs1.getStatement().getConnection().close();
								
								Sql=" where a.edad>=60 and a.codigoCIE='"+rs.getString("codigoCIE")+"' and a.genero='Femenino' group by a.Dx,a.genero order by Total desc LIMIT 15 ";
								rs1=mc.MorbilidadConsultaExternaC(FechaIni, FechaFin, Sql);
								if(rs1.next()){
									out.print(" <td><div align='center'>"+rs1.getString("Total")+"</div></td>");
									tf=tf+rs1.getInt("Total");
								}else{
									out.print(" <td><div align='center'>0</div></td>");
								}
								rs1.getStatement().getConnection().close();
								/****************************************************************************/
								out.print("  <td><div align='center'>"+tm+"</div></td><td><div align='center'>"+tf+"</div></td>");
								out.print(" </tr>");
							}
							rs.getStatement().getConnection().close();
						}
						
						if(cmbTipo.equals("Urg")){
						//////////////////////////////////////////////////////////////////////////////////////
						Sql=" where a.edad<=0 and a.genero='Masculino' group by a.Dx,a.genero order by Total desc LIMIT 15 ";
						rs=mc.MorbilidadUrgencia(FechaIni, FechaFin, Sql);
						out.print("<tr   class='boton4' ><td colspan='3' align='center' >MORBILIDAD EN LA URGENCIA DE 0 A 11 MESES ENTRE "+FechaIN+" Y "+FechaFI+" </td></tr>");
						out.print("<tr><td><table border='1' cellspacing='0' cellpadding='0'>");
						out.print("<tr bgcolor='#CCCCCC' ><td>ITEM</td><td>DIAGNOSTICO</td><td>CANTIDAD</td><td>GENERO</td></tr>");
						while(rs.next()){
							co=co+1;
							out.print("<tr><td>"+co+"</td><td>"+rs.getString("Dx")+"</td><td>"+rs.getString("Total")+"</td><td>"+rs.getString("genero")+"</td></tr>");
						}
						rs.getStatement().getConnection().close();
						co=0;
						out.print("</table></td><td><table border='1' cellspacing='0' cellpadding='0'>");
						Sql=" where a.edad<=0 and a.genero='Femenino' group by a.Dx,a.genero order by Total desc LIMIT 15 ";
						rs=mc.MorbilidadUrgencia(FechaIni, FechaFin, Sql);
						out.print("<tr bgcolor='#CCCCCC' ><td>ITEM</td><td>DIAGNOSTICO</td><td>CANTIDAD</td><td>GENERO</td></tr>");
						while(rs.next()){
							co=co+1;
							out.print("<tr><td>"+co+"</td><td>"+rs.getString("Dx")+"</td><td>"+rs.getString("Total")+"</td><td>"+rs.getString("genero")+"</td></tr>");
						}
						rs.getStatement().getConnection().close();
						out.print("</table></td><td></td></tr>");
						
						//////////////////////////////////////////////////////////////////////////////////////
						co=0;
						Sql=" where a.edad between '1' and '4' and a.genero='Masculino' group by a.Dx,a.genero order by Total desc 	LIMIT 15 ";
						rs=mc.MorbilidadUrgencia(FechaIni, FechaFin, Sql);
						out.print("<tr  class='boton4'  ><td colspan='3' align='center'>MORBILIDAD EN LA URGENCIA DE 1 A 4 A&Ntilde;OS ENTRE "+FechaIN+" Y "+FechaFI+" </td></tr>");
						out.print("<tr><td><table border='1' cellspacing='0' cellpadding='0'>");
						out.print("<tr bgcolor='#CCCCCC' ><td>ITEM</td><td>DIAGNOSTICO</td><td>CANTIDAD</td><td>GENERO</td></tr>");
						while(rs.next()){
							co=co+1;
							out.print("<tr><td>"+co+"</td><td>"+rs.getString("Dx")+"</td><td>"+rs.getString("Total")+"</td><td>"+rs.getString("genero")+"</td></tr>");
						}
						rs.getStatement().getConnection().close();
						co=0;
						out.print("</table></td><td><table border='1' cellspacing='0' cellpadding='0'>");
						Sql=" where a.edad between '1' and '4' and a.genero='Femenino' group by a.Dx,a.genero order by Total desc LIMIT 15 ";
						rs=mc.MorbilidadUrgencia(FechaIni, FechaFin, Sql);
						out.print("<tr bgcolor='#CCCCCC' ><td>ITEM</td><td>DIAGNOSTICO</td><td>CANTIDAD</td><td>GENERO</td></tr>");
						while(rs.next()){
							co=co+1;
							out.print("<tr><td>"+co+"</td><td>"+rs.getString("Dx")+"</td><td>"+rs.getString("Total")+"</td><td>"+rs.getString("genero")+"</td></tr>");
						}
						rs.getStatement().getConnection().close();
						out.print("</table></td><td></td></tr>");
//////////////////////////////////////////////////////////////////////////////////////
						co=0;
						Sql=" where a.edad between '5' and '14' and a.genero='Masculino' group by a.Dx,a.genero order by Total desc 	LIMIT 15 ";
						rs=mc.MorbilidadUrgencia(FechaIni, FechaFin, Sql);
						out.print("<tr  class='boton4'  ><td colspan='3' align='center'>MORBILIDAD EN LA URGENCIA DE 5 A 14 A&Ntilde;OS ENTRE "+FechaIN+" Y "+FechaFI+" </td></tr>");
						out.print("<tr><td><table border='1' cellspacing='0' cellpadding='0'>");
						out.print("<tr bgcolor='#CCCCCC' ><td>ITEM</td><td>DIAGNOSTICO</td><td>CANTIDAD</td><td>GENERO</td></tr>");
						while(rs.next()){
							co=co+1;
							out.print("<tr><td>"+co+"</td><td>"+rs.getString("Dx")+"</td><td>"+rs.getString("Total")+"</td><td>"+rs.getString("genero")+"</td></tr>");
						}
						rs.getStatement().getConnection().close();
						co=0;
						out.print("</table></td><td><table border='1' cellspacing='0' cellpadding='0'>");
						Sql=" where a.edad between '5' and '14' and a.genero='Femenino' group by a.Dx,a.genero order by Total desc LIMIT 15 ";
						rs=mc.MorbilidadUrgencia(FechaIni, FechaFin, Sql);
						out.print("<tr bgcolor='#CCCCCC' ><td>ITEM</td><td>DIAGNOSTICO</td><td>CANTIDAD</td><td>GENERO</td></tr>");
						while(rs.next()){
							co=co+1;
							out.print("<tr><td>"+co+"</td><td>"+rs.getString("Dx")+"</td><td>"+rs.getString("Total")+"</td><td>"+rs.getString("genero")+"</td></tr>");
						}
						rs.getStatement().getConnection().close();
						out.print("</table></td><td></td></tr>");
//////////////////////////////////////////////////////////////////////////////////////
						co=0;
						Sql=" where a.edad between '15' and '44' and a.genero='Masculino' group by a.Dx,a.genero order by Total desc 	LIMIT 15 ";
						rs=mc.MorbilidadUrgencia(FechaIni, FechaFin, Sql);
						out.print("<tr  class='boton4'  ><td colspan='3' align='center'>MORBILIDAD EN LA URGENCIA DE 15 A 44 A&Ntilde;OS ENTRE "+FechaIN+" Y "+FechaFI+" </td></tr>");
						out.print("<tr><td><table border='1' cellspacing='0' cellpadding='0'>");
						out.print("<tr bgcolor='#CCCCCC' ><td>ITEM</td><td>DIAGNOSTICO</td><td>CANTIDAD</td><td>GENERO</td></tr>");
						while(rs.next()){
							co=co+1;
							out.print("<tr><td>"+co+"</td><td>"+rs.getString("Dx")+"</td><td>"+rs.getString("Total")+"</td><td>"+rs.getString("genero")+"</td></tr>");
						}
						rs.getStatement().getConnection().close();
						co=0;
						out.print("</table></td><td><table border='1' cellspacing='0' cellpadding='0'>");
						Sql=" where a.edad between '15' and '44' and a.genero='Femenino' group by a.Dx,a.genero order by Total desc LIMIT 15 ";
						rs=mc.MorbilidadUrgencia(FechaIni, FechaFin, Sql);
						out.print("<tr bgcolor='#CCCCCC' ><td>ITEM</td><td>DIAGNOSTICO</td><td>CANTIDAD</td><td>GENERO</td></tr>");
						while(rs.next()){
							co=co+1;
							out.print("<tr><td>"+co+"</td><td>"+rs.getString("Dx")+"</td><td>"+rs.getString("Total")+"</td><td>"+rs.getString("genero")+"</td></tr>");
						}
						rs.getStatement().getConnection().close();
						out.print("</table></td><td></td></tr>");
//////////////////////////////////////////////////////////////////////////////////////
						co=0;
						Sql=" where a.edad between '45' and '59' and a.genero='Masculino' group by a.Dx,a.genero order by Total desc 	LIMIT 15 ";
						rs=mc.MorbilidadUrgencia(FechaIni, FechaFin, Sql);
						out.print("<tr  class='boton4'  ><td colspan='3' align='center'>MORBILIDAD EN LA URGENCIA DE 45 A 59 A&Ntilde;OS ENTRE "+FechaIN+" Y "+FechaFI+" </td></tr>");
						out.print("<tr><td><table border='1' cellspacing='0' cellpadding='0'>");
						out.print("<tr bgcolor='#CCCCCC' ><td>ITEM</td><td>DIAGNOSTICO</td><td>CANTIDAD</td><td>GENERO</td></tr>");
						while(rs.next()){
							co=co+1;
							out.print("<tr><td>"+co+"</td><td>"+rs.getString("Dx")+"</td><td>"+rs.getString("Total")+"</td><td>"+rs.getString("genero")+"</td></tr>");
						}
						rs.getStatement().getConnection().close();
						co=0;
						out.print("</table></td><td><table border='1' cellspacing='0' cellpadding='0'>");
						Sql=" where a.edad between '45' and '59' and a.genero='Femenino' group by a.Dx,a.genero order by Total desc LIMIT 15 ";
						rs=mc.MorbilidadUrgencia(FechaIni, FechaFin, Sql);
						out.print("<tr bgcolor='#CCCCCC' ><td>ITEM</td><td>DIAGNOSTICO</td><td>CANTIDAD</td><td>GENERO</td></tr>");
						while(rs.next()){
							co=co+1;
							out.print("<tr><td>"+co+"</td><td>"+rs.getString("Dx")+"</td><td>"+rs.getString("Total")+"</td><td>"+rs.getString("genero")+"</td></tr>");
						}
						rs.getStatement().getConnection().close();
						out.print("</table></td><td></td></tr>");
//////////////////////////////////////////////////////////////////////////////////////
						co=0;
						Sql=" where a.edad>=60 and a.genero='Masculino' group by a.Dx,a.genero order by Total desc 	LIMIT 15 ";
						rs=mc.MorbilidadUrgencia(FechaIni, FechaFin, Sql);
						out.print("<tr  class='boton4'  ><td colspan='3' align='center'>MORBILIDAD EN LA URGENCIA MAYOR A 60 A&Ntilde;OS ENTRE "+FechaIN+" Y "+FechaFI+" </td></tr>");
						out.print("<tr><td><table border='1' cellspacing='0' cellpadding='0'>");
						out.print("<tr bgcolor='#CCCCCC' ><td>ITEM</td><td>DIAGNOSTICO</td><td>CANTIDAD</td><td>GENERO</td></tr>");
						while(rs.next()){
							co=co+1;
							out.print("<tr><td>"+co+"</td><td>"+rs.getString("Dx")+"</td><td>"+rs.getString("Total")+"</td><td>"+rs.getString("genero")+"</td></tr>");
						}
						rs.getStatement().getConnection().close();
						co=0;
						out.print("</table></td><td><table border='1' cellspacing='0' cellpadding='0'>");
						Sql=" where a.edad>=60 and a.genero='Femenino' group by a.Dx,a.genero order by Total desc LIMIT 15 ";
						rs=mc.MorbilidadUrgencia(FechaIni, FechaFin, Sql);
						out.print("<tr bgcolor='#CCCCCC' ><td>ITEM</td><td>DIAGNOSTICO</td><td>CANTIDAD</td><td>GENERO</td></tr>");
						while(rs.next()){
							co=co+1;
							out.print("<tr><td>"+co+"</td><td>"+rs.getString("Dx")+"</td><td>"+rs.getString("Total")+"</td><td>"+rs.getString("genero")+"</td></tr>");
						}
						rs.getStatement().getConnection().close();
						out.print("</table></td><td></td></tr>");
						
						}
						
						/************************************************************************************************************************************/
						/*************************************************HOSPITALIZACION*****************************************/
						//////////////////////////////////////////////////////////////////////////////////////
						if(cmbTipo.equals("Hosp")){
							/////////////////////////////////////////////////////////////////////////////////////////////////////////
							co=0;
							Sql=" where a.edad<=0 and a.genero='Masculino' group by a.Dx,a.genero order by Total desc 	LIMIT 15 ";
							rs=mc.MorbilidadHospitalizacion(FechaIni, FechaFin, Sql);
							out.print("<tr  class='boton4'  ><td colspan='3' align='center'>MORBILIDAD EN HOSPITALIZACION DE 0 A 11 MESES ENTRE "+FechaIN+" Y "+FechaFI+" </td></tr>");
							out.print("<tr><td><table border='1' cellspacing='0' cellpadding='0'>");
							out.print("<tr bgcolor='#CCCCCC' ><td>ITEM</td><td>DIAGNOSTICO</td><td>CANTIDAD</td><td>GENERO</td></tr>");
							while(rs.next()){
								co=co+1;
								out.print("<tr><td>"+co+"</td><td>"+rs.getString("Dx")+"</td><td>"+rs.getString("Total")+"</td><td>"+rs.getString("genero")+"</td></tr>");
							}
							rs.getStatement().getConnection().close();
							co=0;
							out.print("</table></td><td><table border='1' cellspacing='0' cellpadding='0'>");
							Sql=" where a.edad<=0 and a.genero='Femenino' group by a.Dx,a.genero order by Total desc LIMIT 15 ";
							rs=mc.MorbilidadHospitalizacion(FechaIni, FechaFin, Sql);
							out.print("<tr bgcolor='#CCCCCC' ><td>ITEM</td><td>DIAGNOSTICO</td><td>CANTIDAD</td><td>GENERO</td></tr>");
							while(rs.next()){
								co=co+1;
								out.print("<tr><td>"+co+"</td><td>"+rs.getString("Dx")+"</td><td>"+rs.getString("Total")+"</td><td>"+rs.getString("genero")+"</td></tr>");
							}
							rs.getStatement().getConnection().close();
							out.print("</table></td><td></td></tr>");
/////////////////////////////////////////////////////////////////////////////////////////////////////////
							co=0;
							Sql=" where a.edad between '1' and '4' and a.genero='Masculino' group by a.Dx,a.genero order by Total desc 	LIMIT 15 ";
							rs=mc.MorbilidadHospitalizacion(FechaIni, FechaFin, Sql);
							out.print("<tr  class='boton4'  ><td colspan='3' align='center'>MORBILIDAD EN HOSPITALIZACION DE 1 A 4 MESES ENTRE "+FechaIN+" Y "+FechaFI+" </td></tr>");
							out.print("<tr><td><table border='1' cellspacing='0' cellpadding='0'>");
							out.print("<tr bgcolor='#CCCCCC' ><td>ITEM</td><td>DIAGNOSTICO</td><td>CANTIDAD</td><td>GENERO</td></tr>");
							while(rs.next()){
								co=co+1;
								out.print("<tr><td>"+co+"</td><td>"+rs.getString("Dx")+"</td><td>"+rs.getString("Total")+"</td><td>"+rs.getString("genero")+"</td></tr>");
							}
							rs.getStatement().getConnection().close();
							co=0;
							out.print("</table></td><td><table border='1' cellspacing='0' cellpadding='0'>");
							Sql=" where a.edad between '1' and '4' and a.genero='Femenino' group by a.Dx,a.genero order by Total desc LIMIT 15 ";
							rs=mc.MorbilidadHospitalizacion(FechaIni, FechaFin, Sql);
							out.print("<tr bgcolor='#CCCCCC' ><td>ITEM</td><td>DIAGNOSTICO</td><td>CANTIDAD</td><td>GENERO</td></tr>");
							while(rs.next()){
								co=co+1;
								out.print("<tr><td>"+co+"</td><td>"+rs.getString("Dx")+"</td><td>"+rs.getString("Total")+"</td><td>"+rs.getString("genero")+"</td></tr>");
							}
							rs.getStatement().getConnection().close();
							out.print("</table></td><td></td></tr>");
/////////////////////////////////////////////////////////////////////////////////////////////////////////
							co=0;
							Sql=" where a.edad between '5' and '14' and a.genero='Masculino' group by a.Dx,a.genero order by Total desc 	LIMIT 15 ";
							rs=mc.MorbilidadHospitalizacion(FechaIni, FechaFin, Sql);
							out.print("<tr  class='boton4'  ><td colspan='3' align='center'>MORBILIDAD EN HOSPITALIZACION DE 5 A 14 MESES ENTRE "+FechaIN+" Y "+FechaFI+" </td></tr>");
							out.print("<tr><td><table border='1' cellspacing='0' cellpadding='0'>");
							out.print("<tr bgcolor='#CCCCCC' ><td>ITEM</td><td>DIAGNOSTICO</td><td>CANTIDAD</td><td>GENERO</td></tr>");
							while(rs.next()){
								co=co+1;
								out.print("<tr><td>"+co+"</td><td>"+rs.getString("Dx")+"</td><td>"+rs.getString("Total")+"</td><td>"+rs.getString("genero")+"</td></tr>");
							}
							rs.getStatement().getConnection().close();
							co=0;
							out.print("</table></td><td><table border='1' cellspacing='0' cellpadding='0'>");
							Sql=" where a.edad between '5' and '14' and a.genero='Femenino' group by a.Dx,a.genero order by Total desc LIMIT 15 ";
							rs=mc.MorbilidadHospitalizacion(FechaIni, FechaFin, Sql);
							out.print("<tr bgcolor='#CCCCCC' ><td>ITEM</td><td>DIAGNOSTICO</td><td>CANTIDAD</td><td>GENERO</td></tr>");
							while(rs.next()){
								co=co+1;
								out.print("<tr><td>"+co+"</td><td>"+rs.getString("Dx")+"</td><td>"+rs.getString("Total")+"</td><td>"+rs.getString("genero")+"</td></tr>");
							}
							rs.getStatement().getConnection().close();
							out.print("</table></td><td></td></tr>");
/////////////////////////////////////////////////////////////////////////////////////////////////////////
							co=0;
							Sql=" where a.edad between '15' and '44' and a.genero='Masculino' group by a.Dx,a.genero order by Total desc 	LIMIT 15 ";
							rs=mc.MorbilidadHospitalizacion(FechaIni, FechaFin, Sql);
							out.print("<tr  class='boton4'  ><td colspan='3' align='center'>MORBILIDAD EN HOSPITALIZACION DE 15 A 44 MESES ENTRE "+FechaIN+" Y "+FechaFI+" </td></tr>");
							out.print("<tr><td><table border='1' cellspacing='0' cellpadding='0'>");
							out.print("<tr bgcolor='#CCCCCC' ><td>ITEM</td><td>DIAGNOSTICO</td><td>CANTIDAD</td><td>GENERO</td></tr>");
							while(rs.next()){
								co=co+1;
								out.print("<tr><td>"+co+"</td><td>"+rs.getString("Dx")+"</td><td>"+rs.getString("Total")+"</td><td>"+rs.getString("genero")+"</td></tr>");
							}
							rs.getStatement().getConnection().close();
							co=0;
							out.print("</table></td><td><table border='1' cellspacing='0' cellpadding='0'>");
							Sql=" where a.edad between '15' and '44' and a.genero='Femenino' group by a.Dx,a.genero order by Total desc LIMIT 15 ";
							rs=mc.MorbilidadHospitalizacion(FechaIni, FechaFin, Sql);
							out.print("<tr bgcolor='#CCCCCC' ><td>ITEM</td><td>DIAGNOSTICO</td><td>CANTIDAD</td><td>GENERO</td></tr>");
							while(rs.next()){
								co=co+1;
								out.print("<tr><td>"+co+"</td><td>"+rs.getString("Dx")+"</td><td>"+rs.getString("Total")+"</td><td>"+rs.getString("genero")+"</td></tr>");
							}
							rs.getStatement().getConnection().close();
							out.print("</table></td><td></td></tr>");
/////////////////////////////////////////////////////////////////////////////////////////////////////////
							co=0;
							Sql=" where a.edad between '45' and '59' and a.genero='Masculino' group by a.Dx,a.genero order by Total desc 	LIMIT 15 ";
							rs=mc.MorbilidadHospitalizacion(FechaIni, FechaFin, Sql);
							out.print("<tr  class='boton4'  ><td colspan='3' align='center'>MORBILIDAD EN HOSPITALIZACION DE 45 A 59 MESES ENTRE "+FechaIN+" Y "+FechaFI+" </td></tr>");
							out.print("<tr><td><table border='1' cellspacing='0' cellpadding='0'>");
							out.print("<tr bgcolor='#CCCCCC' ><td>ITEM</td><td>DIAGNOSTICO</td><td>CANTIDAD</td><td>GENERO</td></tr>");
							while(rs.next()){
								co=co+1;
								out.print("<tr><td>"+co+"</td><td>"+rs.getString("Dx")+"</td><td>"+rs.getString("Total")+"</td><td>"+rs.getString("genero")+"</td></tr>");
							}
							rs.getStatement().getConnection().close();
							co=0;
							out.print("</table></td><td><table border='1' cellspacing='0' cellpadding='0'>");
							Sql=" where a.edad between '45' and '59' and a.genero='Femenino' group by a.Dx,a.genero order by Total desc LIMIT 15 ";
							rs=mc.MorbilidadHospitalizacion(FechaIni, FechaFin, Sql);
							out.print("<tr bgcolor='#CCCCCC' ><td>ITEM</td><td>DIAGNOSTICO</td><td>CANTIDAD</td><td>GENERO</td></tr>");
							while(rs.next()){
								co=co+1;
								out.print("<tr><td>"+co+"</td><td>"+rs.getString("Dx")+"</td><td>"+rs.getString("Total")+"</td><td>"+rs.getString("genero")+"</td></tr>");
							}
							rs.getStatement().getConnection().close();
							out.print("</table></td><td></td></tr>");
/////////////////////////////////////////////////////////////////////////////////////////////////////////
							co=0;
							Sql=" where a.edad>=60 and a.genero='Masculino' group by a.Dx,a.genero order by Total desc 	LIMIT 15 ";
							rs=mc.MorbilidadHospitalizacion(FechaIni, FechaFin, Sql);
							out.print("<tr  class='boton4'  ><td colspan='3' align='center'>MORBILIDAD EN HOSPITALIZACION MAYOR A 60 MESES ENTRE "+FechaIN+" Y "+FechaFI+" </td></tr>");
							out.print("<tr><td><table border='1' cellspacing='0' cellpadding='0'>");
							out.print("<tr bgcolor='#CCCCCC' ><td>ITEM</td><td>DIAGNOSTICO</td><td>CANTIDAD</td><td>GENERO</td></tr>");
							while(rs.next()){
								co=co+1;
								out.print("<tr><td>"+co+"</td><td>"+rs.getString("Dx")+"</td><td>"+rs.getString("Total")+"</td><td>"+rs.getString("genero")+"</td></tr>");
							}
							rs.getStatement().getConnection().close();
							co=0;
							out.print("</table></td><td><table border='1' cellspacing='0' cellpadding='0'>");
							Sql=" where a.edad>=60 and a.genero='Femenino' group by a.Dx,a.genero order by Total desc LIMIT 15 ";
							rs=mc.MorbilidadHospitalizacion(FechaIni, FechaFin, Sql);
							out.print("<tr bgcolor='#CCCCCC' ><td>ITEM</td><td>DIAGNOSTICO</td><td>CANTIDAD</td><td>GENERO</td></tr>");
							while(rs.next()){
								co=co+1;
								out.print("<tr><td>"+co+"</td><td>"+rs.getString("Dx")+"</td><td>"+rs.getString("Total")+"</td><td>"+rs.getString("genero")+"</td></tr>");
							}
							rs.getStatement().getConnection().close();
							out.print("</table></td><td></td></tr>");
						}
						
						if(cmbTipo.equals("CEX")){							
							rs1=mc.EspecialidadesMorbilidadCex(FechaIni, FechaFin);
							while(rs1.next()){
								out.print("<tr  class='boton4'  ><td colspan='3' align='center'>MORBILIDAD EN CONSULTA EXTERNA DE "+rs1.getString("ocupacion")+" ENTRE "+FechaIN+" Y "+FechaFI+" </td></tr>");
								co=0;
								Sql=" where a.genero='Masculino'  and a.ocupacion='"+rs1.getString("ocupacion")+"'  group by a.Dx,a.genero order by Total desc LIMIT 15 ";								
								System.out.println("ssql"+Sql);
								rs=mc.MorbilidadConsultaExterna(FechaIni, FechaFin,Sql);
								out.print("<tr><td><table border='1' cellspacing='0' cellpadding='0'>");
								out.print("<tr bgcolor='#CCCCCC' ><td>ITEM</td><td>DIAGNOSTICO</td><td>CANTIDAD</td><td>GENERO</td></tr>");
								while(rs.next()){
									co=co+1;
									out.print("<tr><td>"+co+"</td><td>"+rs.getString("Dx")+"</td><td>"+rs.getString("Total")+"</td><td>"+rs.getString("genero")+"</td></tr>");
								}
								rs.getStatement().getConnection().close();
								co=0;
								out.print("</table></td><td><table border='1' cellspacing='0' cellpadding='0'>");
								Sql=" where a.genero='Femenino' and a.ocupacion='"+rs1.getString("ocupacion")+"' group by a.Dx,a.genero order by Total desc LIMIT 15 ";
								rs2=mc.MorbilidadConsultaExterna(FechaIni, FechaFin,Sql);
								out.print("<tr bgcolor='#CCCCCC' ><td>ITEM</td><td>DIAGNOSTICO</td><td>CANTIDAD</td><td>GENERO</td></tr>");
								while(rs2.next()){
									co=co+1;
									out.print("<tr><td>"+co+"</td><td>"+rs2.getString("Dx")+"</td><td>"+rs2.getString("Total")+"</td><td>"+rs2.getString("genero")+"</td></tr>");
								}
								rs2.getStatement().getConnection().close();
								out.print("</table></td><td></td></tr>");
							}
							rs1.getStatement().getConnection().close();					
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						out.print("error "+e);
						e.printStackTrace();
					}
				}
				
				if(va.equals("CP")){
					try{	
						String FechaIN=req.getParameter("FechaIN");
						String FechaFI=req.getParameter("FechaFI");
						String cmbTipo=req.getParameter("cmbTipo");
						String FechaIni,DiaI,MesI,AnoI=null;
						DiaI=FechaIN.substring(0,2);
						MesI=FechaIN.substring(3,5);
						AnoI=FechaIN.substring(6,10);
						FechaIni=AnoI+"-"+MesI+"-"+DiaI;			
						String FechaFin,DiaF,MesF,AnoF=null;
						DiaF=FechaFI.substring(0,2);
						MesF=FechaFI.substring(3,5);
						AnoF=FechaFI.substring(6,10);
						FechaFin=AnoF+"-"+MesF+"-"+DiaF;
						if(cmbTipo.equals("Pro")){
							rs=mc.ReporteProduccionProgramas(FechaIni, FechaFin);
							out.print("<table width='100%' border='1' cellspacing='0' cellpadding='0'><tr  class='boton4'  ><td colspan='5' align='center'>PRODUCCION DEL HOSPITAL ENTRE "+FechaIN+" Y "+FechaFI+" </td></tr>");
							out.print("<tr bgcolor='#CCCCCC' ><td>CODIGO PROGRAMA</td><td>NOMBRE PROGRAMA</td><td>CANTIDAD</td><td>CLASE DE SERVICIO</td><td>REGIMEN</td></tr>");
							while(rs.next()){
								out.print("<tr><td>"+rs.getString("cod_programa")+"</td><td>"+rs.getString("nombre_programa")+"</td><td>"+rs.getString("cantidad")+"</td><td>"+rs.getString("clase_servicio")+"</td><td>"+rs.getString("regimen")+"</td></tr>");
							}
							rs.getStatement().getConnection().close();
						}
						
						if(cmbTipo.equals("Ser")){
							rs=mc.ReporteProduccionServicio(FechaIni, FechaFin);
							out.print("<table width='100%' border='1' cellspacing='0' cellpadding='0'><tr  class='boton4'  ><td colspan='5' align='center'>PRODUCCION DEL HOSPITAL ENTRE "+FechaIN+" Y "+FechaFI+" </td></tr>");
							out.print("<tr bgcolor='#CCCCCC' ><td>COD SERVICIO</td><td>NOMBRE SERVICIO Y/O CIRUGIA</td><td>CANTIDAD</td><td>GRUPO O UVR</td><td>REGIMEN</td></tr>");
							while(rs.next()){
								out.print("<tr><td>"+rs.getString("cod_paquete")+"</td><td>"+rs.getString("nombre_paquete")+"</td><td>"+rs.getString("total")+"</td><td>"+rs.getString("nombre_programa")+"</td><td>"+rs.getString("regimen")+"</td></tr>");
							}
							rs.getStatement().getConnection().close();
						}
						
						if(cmbTipo.equals("SerC")){
							rs=mc.ReporteProduccionServicioConsolidado(FechaIni, FechaFin);
							out.print("<table width='100%' border='1' cellspacing='0' cellpadding='0'><tr  class='boton4'  ><td colspan='3' align='center'>PRODUCCION DEL HOSPITAL ENTRE "+FechaIN+" Y "+FechaFI+" </td></tr>");
							out.print("<tr bgcolor='#CCCCCC' ><td>GRUPO O UVR</td><td>CANTIDAD</td><td>REGIMEN</td></tr>");
							while(rs.next()){
								out.print("<tr><td>"+rs.getString("nombre_programa")+"</td><td>"+rs.getString("total")+"</td><td>"+rs.getString("regimen")+"</td></tr>");
							}
							rs.getStatement().getConnection().close();
						}					
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						out.print("error "+e);
						e.printStackTrace();
					}
				}
				
				if(va.equals("2193")){
					try{
						String FechaIN=req.getParameter("FechaIN");
						String FechaFI=req.getParameter("FechaFI");
						String cmbTipo=req.getParameter("cmbTipo");
						String FechaIni,DiaI,MesI,AnoI=null;
						DiaI=FechaIN.substring(0,2);
						MesI=FechaIN.substring(3,5);
						AnoI=FechaIN.substring(6,10);
						FechaIni=AnoI+"-"+MesI+"-"+DiaI;			
						String FechaFin,DiaF,MesF,AnoF=null;
						DiaF=FechaFI.substring(0,2);
						MesF=FechaFI.substring(3,5);
						AnoF=FechaFI.substring(6,10);
						FechaFin=AnoF+"-"+MesF+"-"+DiaF;
						int ent=0;int sCon=0;int sSub=0;int sVin=0;int sPar=0;int sCom=0;int sEsp=0;int sSim=0;
						out.print("<table width='100%' border='1' cellspacing='0' cellpadding='0'><tr  class='boton4'  > " +
								"<td colspan='8' align='center'>DATOS PARA LA 2193 ENTRE "+FechaIN+" Y "+FechaFI+" </td></tr>");
						out.print("<tr bgcolor='#CCCCCC' ><td>CONCEPTO</td> " +
								"<td>Regimen</td> " +
								"<td>Cantidad</td> " +
								
								"</tr>");
						rs=mc.ConsultaReporte2193Citologias(FechaIni, FechaFin);						
						while(rs.next()){
							out.print("<tr><td>"+rs.getString("Programa")+"</td>" );
							out.print("<td>"+rs.getString("regimen")+"</td>");
							out.print("<td>"+rs.getString("cantidad")+"</td>");							
						}
						ent=0;sCon=0;sSub=0;sVin=0;sPar=0;sCom=0;sEsp=0;sSim=0;
						out.print("</tr>");
						rs.getStatement().getConnection().close();
						
						rs=mc.ConsultaReporte2193UrgenciaGeneral(FechaIni, FechaFin);						
						while(rs.next()){
							out.print("<tr><td>"+rs.getString("Programa")+"</td>" );
							out.print("<td>"+rs.getString("regimen")+"</td>");
							out.print("<td>"+rs.getString("cantidad")+"</td>");
							
						}
						ent=0;sCon=0;sSub=0;sVin=0;sPar=0;sCom=0;sEsp=0;sSim=0;
						out.print("</tr>");
						rs.getStatement().getConnection().close();
						rs=mc.ConsultaReporte2193UrgenciaEspecializada(FechaIni, FechaFin);
						while(rs.next()){
							out.print("<tr><td>"+rs.getString("Programa")+"</td>" );
							out.print("<td>"+rs.getString("regimen")+"</td>");
							out.print("<td>"+rs.getString("cantidad")+"</td>");
						}
						ent=0;
						out.print("</tr>");
						rs.getStatement().getConnection().close();
						rs=mc.ConsultaReporte2193Ambulatoria(FechaIni, FechaFin);
						while(rs.next()){
							out.print("<tr><td>"+rs.getString("Programa")+"</td>" );
							out.print("<td>"+rs.getString("regimen")+"</td>");
							out.print("<td>"+rs.getString("cantidad")+"</td>");
						}
						ent=0;
						out.print("</tr>");
						rs.getStatement().getConnection().close();
						rs=mc.ConsultaReporte2193OtrasConsultasAmbulatorias(FechaIni, FechaFin);
						while(rs.next()){
							out.print("<tr><td>"+rs.getString("Programa")+"</td>" );
							out.print("<td>"+rs.getString("regimen")+"</td>");
							out.print("<td>"+rs.getString("cantidad")+"</td>");
						}
						ent=0;
						out.print("</tr>");
						rs.getStatement().getConnection().close();
						rs=mc.ConsultaReporte2193Laboratorios(FechaIni, FechaFin);
						while(rs.next()){
							out.print("<tr><td>"+rs.getString("Programa")+"</td>" );
							out.print("<td>"+rs.getString("regimen")+"</td>");
							out.print("<td>"+rs.getString("cantidad")+"</td>");
						}
						ent=0;
						out.print("</tr>");
						rs.getStatement().getConnection().close();
						rs=mc.ConsultaReporte2193Imagenologia(FechaIni, FechaFin);
						while(rs.next()){
							out.print("<tr><td>"+rs.getString("Programa")+"</td>" );
							out.print("<td>"+rs.getString("regimen")+"</td>");
							out.print("<td>"+rs.getString("cantidad")+"</td>");
						}
						ent=0;
						out.print("</tr>");
						rs.getStatement().getConnection().close();
						rs=mc.ConsultaReporte2193TerapiasRespiratorias(FechaIni, FechaFin);
						while(rs.next()){
							out.print("<tr><td>"+rs.getString("Programa")+"</td>" );
							out.print("<td>"+rs.getString("regimen")+"</td>");
							out.print("<td>"+rs.getString("cantidad")+"</td>");
						}
						ent=0;
						out.print("</tr>");
						rs.getStatement().getConnection().close();
						rs=mc.ConsultaReporte2193TerapiasFisicas(FechaIni, FechaFin);
						while(rs.next()){
							out.print("<tr><td>"+rs.getString("Programa")+"</td>" );
							out.print("<td>"+rs.getString("regimen")+"</td>");
							out.print("<td>"+rs.getString("cantidad")+"</td>");
						}
						ent=0;
						out.print("</tr>");
						rs.getStatement().getConnection().close();
						rs=mc.ConsultaReporte2193PacientesObservacion(FechaIni, FechaFin);
						while(rs.next()){
							out.print("<tr><td>"+rs.getString("Programa")+"</td>" );
							out.print("<td>"+rs.getString("regimen")+"</td>");
							out.print("<td>"+rs.getString("cantidad")+"</td>");
						}
						ent=0;
						out.print("</tr>");
						rs.getStatement().getConnection().close();
						rs=mc.ConsultaReporte2193PartosNormales(FechaIni, FechaFin);
						while(rs.next()){
							out.print("<tr><td>"+rs.getString("Programa")+"</td>" );
							out.print("<td>"+rs.getString("regimen")+"</td>");
							out.print("<td>"+rs.getString("cantidad")+"</td>");
						}
						ent=0;
						out.print("</tr>");
						rs.getStatement().getConnection().close();
						rs=mc.ConsultaReporte2193Cesareas(FechaIni, FechaFin);
						while(rs.next()){
							out.print("<tr><td>"+rs.getString("Programa")+"</td>" );
							out.print("<td>"+rs.getString("regimen")+"</td>");
							out.print("<td>"+rs.getString("cantidad")+"</td>");
						}
						ent=0;
						out.print("</tr>");
						rs.getStatement().getConnection().close();
						rs=mc.ConsultaReporte2193CirugiasSinCesareasyPartosvaginales(FechaIni, FechaFin);
						while(rs.next()){
							out.print("<tr><td>"+rs.getString("Programa")+"</td>" );
							out.print("<td>"+rs.getString("regimen")+"</td>");
							out.print("<td>"+rs.getString("cantidad")+"</td>");
						}
						ent=0;
						
						rs.getStatement().getConnection().close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						out.print("error "+e);
						e.printStackTrace();
					}
				}
				
				if(va.equals("RepInd")){
					String tdia=req.getParameter("tdia");
					String tmes=req.getParameter("tmeses");
					String tano=req.getParameter("tanos");
					String ftdia=req.getParameter("ftdia");
					String ftmes=req.getParameter("ftmeses");
					String ftano=req.getParameter("ftanos");
					String ftent=req.getParameter("ftent");
					String Serv=req.getParameter("Serv");
					String area=req.getParameter("area");
					String Fechai=tano+"-"+tmes+"-"+tdia;
					String Fechaf=ftano+"-"+ftmes+"-"+ftdia;
					System.out.println("Ent "+ftent);
					System.out.println("Serv es igual a "+Serv);
					if(Serv.equals("1")){
											out.println("<br><br><table class='contpre' width='100%'>");
											out.println("<tr align='center'><td colspan='4' id='cabecera2' class='style11'  ><br><b>INDICADORES DE URGENCIA  <br></b></td>");
											out.println("<tr>");
											out.println("<td><br><table width='100%' border='1' align='center'><tr align='center'><td bgcolor='#C3CEDD' ><br><b>NUMERADOR</b><br></td><td bgcolor='#C3CEDD' ><br><b>DENOMINADOR </b><br></td><td bgcolor='#C3CEDD'><br><b>NOMBRE DEL INDICADOR</b><br></td><tr><td><br><b>Sum. Min. Transcurridos entre la Admision del Paciente y su Atencion por el Medico</b><br></td><td><br><b>Total Pacientes Atendidos Urgencia Triage I, II </b><br></td><td bgcolor='#E3E3E5'><br><b>OPORTUNIDAD EN LA ATENCION EN CONSULTA DE URGENCIAS TRIAGE I, II<br></b></td>");
											rs=mc.BuscarMinHC(Fechai,Fechaf,ftent,1);
											int Minutos=0;
											int cont=0;
											float IndOportunidad;
											try {
												while(rs.next()){
													Minutos=Minutos+(rs.getInt(5));
													cont=cont+1; 
												}
												if(Minutos!=0){
													System.out.println(" MINUTOS:"+Minutos);
													System.out.println("cont:"+cont);
													if((ftent.equals("321"))&&(ftmes.equals("9"))&&(tmes.equals("9"))){
															 Minutos=43125;
															 out.println("<tr><td align='center' ><br>"+Minutos+"</td>");
														}else{
															if((ftent.equals("321"))&&(ftmes.equals("7"))&&(tmes.equals("7"))){
																Minutos=85640;
																out.println("<tr><td align='center' ><br>"+Minutos+"</td>");
															}else{
																out.println("<tr><td align='center' ><br>"+Minutos+"</td>");
															}
														}
												}else{
													out.println("<tr><td>No Existen registros para los parametros dados</td></tr>");
												}
												rs.getStatement().getConnection().close();
											} catch (SQLException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
											
											rs1=mc.BuscarPacUrgen(Fechai, Fechaf, ftent, 1);
											int PacUrg=0;
											try {
												while(rs1.next()){
													out.println("<td  align='center'><br>"+rs1.getInt(1)+"</td>");
													PacUrg=rs1.getInt(1);
												}
												rs1.getStatement().getConnection().close();
											} catch (SQLException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
											if((Minutos!=0)&&(cont!=0)){
												
													IndOportunidad=(float) Minutos/PacUrg;
													String val = IndOportunidad+"";  
													BigDecimal big = new BigDecimal(val);   
													big = big.setScale(2, RoundingMode.HALF_UP);  
													System.out.println("IndOportunida  "+IndOportunidad);
													out.println("<td><br> "+big+"</td>");
											}
												out.println("</tr>");
												out.println("<tr><td><br><b>Sum. Min. Transcurridos entre la Admision del Paciente y su Atencion por el Medico</b><br></td><td><br><b>Total Pacientes Atendidos Urgencia Triage III </b><br></td><td bgcolor='#E3E3E5'><br><b>OPORTUNIDAD EN LA ATENCION EN CONSULTA DE URGENCIAS TRIAGE III </b><br></td>");
												rs=mc.BuscarMinHC(Fechai,Fechaf,ftent,2);
												Minutos=0;
												cont=0;
												float IndOportunidad2;
												try {
													while(rs.next()){
														Minutos=Minutos+(rs.getInt(5));
														cont=cont+1;
													}
													if(Minutos!=0){
														System.out.println(" MINUTOS:"+Minutos);
														System.out.println("cont:"+cont);
														out.println("<tr><td align='center' ><br>"+Minutos+"</td>");
														
													}else{
														out.println("<tr><td>No Existen registros para los parametros dados</td></tr>");
													}
													rs.getStatement().getConnection().close();
												} catch (SQLException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
												
												rs1=mc.BuscarPacUrgen(Fechai, Fechaf, ftent, 2);
												int PacUrg2=0;
												try {
													while(rs1.next()){
														out.println("<td  align='center'><br>"+rs1.getInt(1)+"</td>");
														PacUrg2=rs1.getInt(1);
													}
													rs1.getStatement().getConnection().close();
												} catch (SQLException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
												if((Minutos!=0)&&(cont!=0)){
													
														IndOportunidad2=(float) Minutos/PacUrg2;
														String val = IndOportunidad2+"";  
														BigDecimal big = new BigDecimal(val);  
														big = big.setScale(2, RoundingMode.HALF_UP);  
							
														out.println("<td align='center' ><br> "+big+"</td>");
														System.out.println("IndOportunida2  "+IndOportunidad2);
												}
													out.println("</tr>");
												
												out.println("<tr><td><br><b>No. de Fallecidos en la Urgencia Triage I, II y III </b></td><td><br><b>Total paciente Atendidos en Urgencia Triage I, II y III </b></td><td bgcolor='#E3E3E5'><br><b>TASA DE MORTALIDAD EN URGENCIAS</b></td></tr>");
												float IndTasaMorta;
												out.println("<tr>");
												rs=mc.BuscarFalleUrg(Fechai, Fechaf, ftent);
												int FalleUrg=0;
												try {
													while(rs.next()){
														FalleUrg=rs.getInt(1);
														out.println("<td align='center' ><br>"+FalleUrg+"</td>"); 
													}
													rs.getStatement().getConnection().close();
												} catch (SQLException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
												out.println("<td align='center' ><br>"+(PacUrg+PacUrg2)+"</td>");
												if((PacUrg+PacUrg2)!=0){
													IndTasaMorta=(float) FalleUrg/(PacUrg+PacUrg2);
													String valo = IndTasaMorta+"";  
													BigDecimal big = new BigDecimal(valo);  
													big = big.setScale(4, RoundingMode.HALF_UP);  
						
													out.println("<td align='center' ><br> "+big+"</td>");
												}else{
													out.println("<tr><td>No Existen registros para los parametros dados</td></tr>");
												}
														out.println("</tr>");
														
												out.println("<tr><td><br><b>Paciente que reingresan al servicio de urgencia antes de 72 Horas</b></td><td><br><b>Total Egresos Vivos del Servicio de Urgencia<b></td><td bgcolor='#E3E3E5'><br><b>TASA DE REINGRESOS <72 HORAS</b></td></tr>");
														rs=mc.BuscarPacIngreRepetido(Fechai, Fechaf,ftent);
														long NumPacReingresados=0;
														String CodPac;
														String fechareg="";
														String dingre="";
														String ad="";
														String fr="";
														String p="";
														String di="";
													
														try {
															while(rs.next()){
																CodPac=rs.getString(2);
																rs3=mc.BuscarNoAdmUrg(CodPac, Fechai, Fechaf, ftent);
																while(rs3.next()){
																	if(rs3.getInt(1)>1){
																		rs1=mc.BuscarDetAdmUrg(rs3.getString(2), Fechai, Fechaf, ftent);
																			//rs1=mc.BuscarFechaRegPac(CodPac, Fechai, Fechaf,ftent);
																			/*while(rs1.next()){
																				fechareg=rs1.getString(3);
																				dingre=rs1.getString(5);*/
																			 while(rs1.next()){
																				 fechareg=rs1.getString(3);
																				 rs4=mc.BuscarDiagIngre(rs1.getString(1),rs3.getString(2));
																				 while(rs4.next()){
																					 if((ad.equals(rs1.getString(1)))&&(fr.equals(fechareg))&&(p.equals(rs3.getString(2)))&&(di.equals(rs4.getString(1)))){
																					 
																					 }else{
																						 rs2=mc.BuscarPacientesReingresados(CodPac, Fechai, Fechaf,ftent,fechareg, rs4.getString(1), rs1.getString(1));
																							while(rs2.next()){
																								//if(admision.equals(rs2.getString(6))){
																									NumPacReingresados=NumPacReingresados+1;
																								//}
																							}rs2.getStatement().getConnection().close();
																					 }
																					 
																					 ad=rs1.getString(1);
																						fr=fechareg;
																						p=rs3.getString(2);
																						di=rs4.getString(1);
																						System.out.println(rs1.getString(3));
																				 }rs4.getStatement().getConnection().close();
																				
																			 }rs1.getStatement().getConnection().close();
																			/*}rs1.getStatement().getConnection().close();*/
																	}		
																}rs3.getStatement().getConnection().close();
															}rs.getStatement().getConnection().close();
															
															rs=mc.BuscarVivoUrg(Fechai, Fechaf, ftent);
															out.println("<tr><td align='center'><br>"+NumPacReingresados+"</td>");
															int vali=0;
															long pacvivo=0;
															while(rs.next()){
																out.println("<td align='center'>"+rs.getString(1)+"</td>");
																pacvivo=rs.getLong(1);
																vali=1;
															}rs.getStatement().getConnection().close();
															float Tasa72=0;
															if(vali!=0){
																Tasa72=(float) NumPacReingresados/pacvivo;
																System.out.println("Tasa72"+Tasa72);
																String valu = Tasa72+"";  
																System.out.println("valu "+valu);
																BigDecimal big = new BigDecimal(valu);  
																big = big.setScale(4, RoundingMode.HALF_UP);  
									
																out.println("<td align='center' ><br> "+big+"</td></tr>");
															}else{
																out.println("<tr><td>No Existen registros para los parametros dados</td></tr>");
															}
														} catch (SQLException e) {
															// TODO Auto-generated catch block
															e.printStackTrace();
														}
														
														out.println("<tr><td align='center' ><br><b># Pacientes Traladados de a hospitalizacion desde Urgencias</b></td><td><br><b>Total Pacientes Atendidos en Urgencia</b></td><td bgcolor='#E3E3E5'><b><br>TASA DE PACIENTES HOSPITALIZADOS</b></td></tr>");
														rs=mc.BuscarTrasUrg(Fechai, Fechaf, ftent);
														long PacTrasUrg=0;
														try {
															while(rs.next()){
																PacTrasUrg=rs.getLong(1);
																out.println("<td>"+PacTrasUrg+"</td>");
															}rs.getStatement().getConnection().close();
														} catch (SQLException e) {
															// TODO Auto-generated catch block
															e.printStackTrace();
														}
														out.print("<td align='center' >"+(PacUrg+PacUrg2)+"</td>");
														float TPacHosp=0;
														if((PacUrg+PacUrg2)!=0){
															TPacHosp=(float) PacTrasUrg/(PacUrg+PacUrg2);
															String vale = TPacHosp+"";  
															BigDecimal big = new BigDecimal(vale);  
															big = big.setScale(4, RoundingMode.HALF_UP);  
								
															out.println("<td align='center'><br> "+big+"</td>");
														}else{
																out.println("<tr><td>No Existen registros para los parametros dados</td></tr>");
															}
														
														out.println("</tr>");		
														
																		
					}else{
						if(Serv.equals("adiag")){
							out.println("<br><table class='contpre' width='100%'>");
							out.println("<tr align='center'><td colspan='4' id='cabecera2' class='style11'  ><br><b>INDICADORES DE APOYO DIAGNOSTICO </b><br></td>");
							out.print("<tr><td><br><b>Comprendido de </b>"+Fechai+"<b> al </b>"+Fechaf+"</td>");
							if(ftent.equals("todas")){
								out.print("<td><br><b> Entidad :</b> TODAS </td>");
								System.out.println("Entrando a validacion de entidades"+ftent);
							}else{
								rs1=mc.BuscarNombEnt(ftent);
								try {
									while(rs1.next()){
										out.print("<td><br> <b>Entidad : </b>"+rs1.getString(1)+"</td>");
									}rs1.getStatement().getConnection().close();
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							out.print("</tr><tr><td colspan='4'><br></td></tr>");
							out.println("<tr align='center'><td colspan='4' bgcolor='#496FA0'  ><br><b><font color='white'>IMAGENOLOGIA </font></b><br></td>");
						    out.println("<tr><td colspan='4' ><table width='100%' border='1' CELLSPACING='0' ><br><b>");
						    out.println("<tr><td align='center' width='35%'><b>NUMERADOR</b></td><td align='center' width='35%'><b>DENOMINADOR</b></td><td align='center' width='30%'><b>NOMBRE DEL INDICADOR </b></td></tr>");
						    out.println("<tr><td width='35%' align='center' ><br><b>Sum. Min. Transcurridos entre la Generacion de la Orden Imagenologica y Resultado Aprobado en el Sistema </b><br></td><td width='35%' align='center'><br><b>Total de Ordenes de Imagenologia <b><br></td><td bgcolor='#E3E3E5' width='30%' align='center'><br><b>OPORTUNIDAD EN LA ATENCION DE LOS SERVICIOS DE IMAGENOLOGIA </b><br></td></tr>");
							long MinTranscurridos=0;
							long Totalordenes=0;
							
							rs=mc.BuscarMinApoyD(Fechai,Fechaf,ftent);
							out.println("<tr>");
							try {
								while(rs.next()){
									rs1=mc.BuscarEnHicOrden(rs.getString(1),rs.getString(2),rs.getString(7),rs.getString(8),Fechai, Fechaf,ftent);
									if(rs1.next()){
										MinTranscurridos=MinTranscurridos+rs.getLong(5);
									}rs1.getStatement().getConnection().close();
									
								}rs.getStatement().getConnection().close();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							rs=mc.BuscarTotalOrdAdiag(Fechai,Fechaf,ftent);
							try {
								while(rs.next()){
									Totalordenes=Totalordenes+rs.getLong(1);
								}rs.getStatement().getConnection().close();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							out.println("<tr><td align='center' width='35%'>"+MinTranscurridos+"</td><td align='center' width='35%'>"+Totalordenes+"</td>");
							long Indicador=0;
							if(Totalordenes==0){
								out.println("<td> No  existen datos </td>");
							}else{
								Indicador=(MinTranscurridos/Totalordenes);
								out.println("<td align='center' width='30%' >"+Indicador+"</td>");
							}
							out.println("</tr>");

						    out.println("</table></td><tr>");
						    
						    out.println("<tr><td colspan='4'><br><td></tr>");
							out.println("<tr align='center'><td colspan='4' bgcolor='#496FA0'  ><br><b><font color='white'> LABORATORIOS </font></b><br></td>");
							out.println("<tr><td colspan='4' ><table width='100%' border='1' CELLSPACING='0' ><br><b>");
						    out.println("<tr><td align='center' width='35%'><b>NUMERADOR</b></td><td align='center' width='35%'><b>DENOMINADOR</b></td><td align='center' width='30%'><b>NOMBRE DEL INDICADOR </b></td></tr>");
						    out.println("<tr><td width='35%' align='center' ><br><b>Sum. Min. Transcurridos entre la Generacion del Estudio y Resultado en el Sistema </b><br></td><td width='35%' align='center'><br><b>Total de Ordenes de Laboratorio <b><br></td><td bgcolor='#E3E3E5' width='30%' align='center'><br><b>OPORTUNIDAD EN LA ATENCION DE LOS SERVICIOS DE LABORATORIO </b><br></td></tr>");
							rs=mc.BuscarMinLab(Fechai, Fechaf, ftent);
							long suminlab=0;
							long totalab=0;
							long indlab=0;
							try {
								while(rs.next()){
									suminlab=suminlab+rs.getLong(5);
								}rs.getStatement().getConnection().close();
								rs1=mc.BuscarTotalLab(Fechai, Fechaf, ftent);
								
								while(rs1.next()){
									totalab=rs1.getLong(4);
								}
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							out.println("<tr><td width='35%' align='center'>"+suminlab+"</td><td width='35%' align='center'>"+totalab+"</td>");
							if(totalab==0){
								out.println("<td> No  existen datos </td>");
							}else{
								indlab=(suminlab/totalab);
								out.println("<td align='center' width='30%' >"+indlab+"</td>");
							}
							out.println("</table><td></tr>");
						    
							out.println("<tr><td colspan='4'><br><td></tr>");
							out.println("<tr align='center'><td colspan='4' bgcolor='#496FA0'  ><br><b><font color='white'> PATOLOGIA </font></b><br></td>");
							out.println("<tr><td colspan='4' ><table width='100%' border='1' CELLSPACING='0' ><br><b>");
						    out.println("<tr><td align='center' width='35%'><b>NUMERADOR</b></td><td align='center' width='35%'><b>DENOMINADOR</b></td><td align='center' width='30%'><b>NOMBRE DEL INDICADOR </b></td></tr>");
						    out.println("<tr><td width='35%' align='center' ><br><b>Sum. Dias Transcurridos desde la recepcion de la Orden Patologica hasta su Entrega </b><br></td><td width='35%' align='center'><br><b>Total de Estudios Patologicos <b><br></td><td bgcolor='#E3E3E5' width='30%' align='center'><br><b>OPORTUNIDAD EN LA ATENCION DE LOS SERVICIOS DE PATOLOGIA </b><br></td></tr>");
							rs2=mc.BuscarDiasPat(Fechai, Fechaf, ftent);
							long sumdiapat=0;
							long totalpat=0;
							try {
								while(rs2.next()){
									if(rs2.getLong(8)>0){
										sumdiapat=sumdiapat+rs2.getLong(8);
									}
								}rs2.getStatement().getConnection().close();
									
								rs=mc.BuscarTotalPat(Fechai, Fechaf, ftent);
								while(rs.next()){
									totalpat=rs.getLong(6);
								}rs.getStatement().getConnection().close();
								
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							out.println("<tr><td width='35%' align='center'>"+sumdiapat+"</td><td width='35%' align='center' >"+totalpat+"</td>");
							long indpat=0;
							if(totalpat==0){
								out.println("<td>No existen datos </td>");
							}else{
								indpat=(sumdiapat/totalpat);
								out.println("<td align='center' width='30%' >"+indpat+"</td>");
							}
							
							
						    out.println("</table><td></tr>");
							out.println("<tr align='center'><td colspan='4' id='cabecera2' class='style11'  ><br><b> DATOS DISCRIMINADOS <br></b></td></tr>");
						    if(ftent.equals("todas")){
						    	ftent="1";
						    }else{
						    	
						    }
						    out.println("</tr>");
						    out.println("<tr><td colspan='4'>Tipo : <select id='tadiag' onChange='Tipodiag("+tdia+","+tmes+","+tano+","+ftdia+","+ftmes+","+ftano+","+ftent+")'><option>Seleccione</option><option value='1' >IMAGENOLOGIA</option><option value='3'>PATOLOGIA</option> <option value='2' >LABORATORIOS</option>" );
						    out.println("</select></td></tr>");
						    out.println("<tr><td td colspan='4'><div id='discriminado'></div></td></tr>");
						    /*
						    
						    //	rs=mc.BuscarMinApoyoDiagnostico(Fechai,Fechaf,ftent);
							*/
							
						}else{
							if(Serv.equals("23")){
								
								out.println("<br><br><table class='contpre' width='100%'>");
								out.println("<tr align='center'><td colspan='4' id='cabecera2' class='style11'  ><br><b>INDICADORES DE UCI  <br></b></td>");
								out.println("<tr>");
								out.println("<td><br><table width='100%' border='1' align='center'><tr align='center'><td bgcolor='#C3CEDD' ><br><b>NUMERADOR</b><br></td><td bgcolor='#C3CEDD' ><br><b>DENOMINADOR </b><br></td><td bgcolor='#C3CEDD'><br><b>NOMBRE DEL INDICADOR</b><br></td><tr><td><br><b>Sum. Min. Transcurridos entre la Admision del Paciente y su Atencion por el Medico</b><br></td><td><br><b>Total Pacientes Atendidos Urgencia Triage I, II </b><br></td><td bgcolor='#E3E3E5'><br><b>OPORTUNIDAD EN LA ATENCION EN CONSULTA DE URGENCIAS TRIAGE I, II<br></b></td>");
							
							}else{
								if(Serv.equals("25")){
									
									out.println("<br><br><table class='contpre' width='100%'>");
									out.println("<tr align='center'><td colspan='4' id='cabecera2' class='style11'  ><br><b>INDICADORES DE AMBULTORIO<br></b></td>");
									out.println("<tr>");
									out.println("<td><br><table width='100%' border='1' align='center'><tr align='center'><td bgcolor='#C3CEDD' ><br><b>NUMERADOR</b><br></td><td bgcolor='#C3CEDD' ><br><b>DENOMINADOR </b><br></td><td bgcolor='#C3CEDD'><br><b>NOMBRE DEL INDICADOR</b><br></td><tr><td><br><b>Sum. Min. Transcurridos entre la Admision del Paciente y su Atencion por el Medico</b><br></td><td><br><b>Total Pacientes Atendidos Urgencia Triage I, II </b><br></td><td bgcolor='#E3E3E5'><br><b>OPORTUNIDAD EN LA ATENCION EN CONSULTA DE URGENCIAS TRIAGE I, II<br></b></td>");

									
								}else{
									if(Serv.equals("24")){
										
										out.println("<br><br><table class='contpre' width='100%'>");
										out.println("<tr align='center'><td colspan='4' id='cabecera2' class='style11'  ><br><b>INDICADORES DE HOSPITALIZACION <br></b></td>");
										out.println("<tr>");
										out.println("<td>" +
												"<br>" +
												"<table width='100%' border='1' align='center'>" +
												"   <tr align='center'>" +
												"        <td bgcolor='#C3CEDD' ><br><b>NUMERADOR</b><br></td>" +
												"        <td bgcolor='#C3CEDD' ><br><b>DENOMINADOR </b><br></td>" +
												"        <td bgcolor='#C3CEDD'><br><b>NOMBRE DEL INDICADOR</b><br></td>" +
												"        <tr align='center' >" +
												"        <td><br><b>No. de pacientes reingresados antes de 20 dias </b><br></td>" +
												"        <td><br><b>Total Egresos Vivos </b><br></td>" +
												"        <td bgcolor='#E3E3E5'><br><b>TASA DE REINGREO DE PACIENTES DE PACIENTES HOSPITALIZADOS<br></b></td></tr>");
												
										//No. de pacientes reingresados antes de 20 dias por la misma causa
										
										rs=mc.BuscarPacIngreRepetidoHosp(Fechai, Fechaf, ftent,area);
										long NumPacReingHosp=0;
										String CodPac;
										String fechareg="";
										String dingre="";
										String ad="";
										String fr="";
										String p="";
										String di="";
									
										try {
											while(rs.next()){
												CodPac=rs.getString(2);
												System.out.println("CodPaciente "+CodPac);
												rs3=mc.BuscarNoAdm(CodPac,Fechai,Fechaf,ftent,area);
												while(rs3.next()){
													if(rs3.getInt(1)>1){ 
														rs1=mc.BuscarDetAdm(rs3.getString(2),Fechai,Fechaf,ftent,area); 
														while(rs1.next()){
															fechareg=rs1.getString(3);
															rs4=mc.BuscarDiagIngre(rs1.getString(1), rs3.getString(2));
															while(rs4.next()){
																System.out.println(" valor ad "+ad+" = "+rs1.getString(1)+" valor de fr"+fr+" = "+fechareg+" valor de p"+p+" = "+rs3.getString(2)+" valor de di"+di+" = "+rs4.getString(1));
																if((ad.equals(rs1.getString(1)))&&(fr.equals(fechareg))&&(p.equals(rs3.getString(2)))&&(di.equals(rs4.getString(1)))){
																 System.out.println("entrando ");
																}else{
																	rs2=mc.BuscarPacientesReingHosp(CodPac, Fechai, Fechaf, ftent, fechareg, rs4.getString(1), area, rs1.getString(1));
																	while(rs2.next()){
													
																		NumPacReingHosp=NumPacReingHosp+1;
																	}rs2.getStatement().getConnection().close();
																}
																System.out.println(" antes de ad "+ad+" fr"+fr+" p"+p+" di"+di);
																ad=rs1.getString(1);
																fr=fechareg;
																p=rs3.getString(2);
																di=rs4.getString(1);
															}rs4.getStatement().getConnection().close();
														}rs1.getStatement().getConnection().close();
													}
												}rs3.getStatement().getConnection().close();
													
													//System.out.println(rs1.getString(3));
												}rs.getStatement().getConnection().close();
											
											//TOTAL DE EGRESOS VIVOS 
											rs=mc.BuscarVivoHosp(Fechai, Fechaf, ftent,area);
											out.println("<tr><td align='center'><br><a href='Cal_ValidacionReporteInd.jsp?tdia="+tdia+"&tmes="+tmes+"&tano="+tano+"&ftdia="+ftdia+"&ftmes="+ftmes+"&ftano="+ftano+"&ftent="+ftent+"&area="+area+"&tipor="+1+"' >"+NumPacReingHosp+"</a></td>");
											int vali=0;
											long pacvivo=0;
											while(rs.next()){
												out.println("<td align='center'><br><a href='Cal_ValidacionReporteInd.jsp?tdia="+tdia+"&tmes="+tmes+"&tano="+tano+"&ftdia="+ftdia+"&ftmes="+ftmes+"&ftano="+ftano+"&ftent="+ftent+"&area="+area+"&tipor="+2+"' >"+rs.getString(1)+"</a></td>");
												pacvivo=rs.getLong(1);
												vali=1;
											}rs.getStatement().getConnection().close();
											float Tasa72=0;
											if(vali!=0){
												Tasa72=(float) NumPacReingHosp/pacvivo;
												String valu = Tasa72+"";  
												BigDecimal big = new BigDecimal(valu);  
												big = big.setScale(4, RoundingMode.HALF_UP);  
					
												out.println("<td align='center' ><br> "+big+"</td></tr>");
											}else{
												out.println("<tr><td>No Existen registros para los parametros dados</td></tr>");
											}
										} catch (SQLException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
										
										
										
										
											out.println("<tr align='center' >" +
												"        <td><br><b>No de Pacientes que fallecen despues de 48 Horas </b><br></td>" +
												"        <td><br><b>Total de Pacientes Hospitalizados (Ingresos)</b><br></td>" +
												"        <td bgcolor='#E3E3E5'><br><b>TASA DE MORTALIDAD INTRAHOSPITALARIA DESPUES DE 48 HORAS <br></b></td></tr>");
											
											
											out.println("<tr align='center' >" +
													"        <td><br><b>No de Pacientes que fallecen despues de 48 Horas </b><br></td>" +
													"        <td><br><b>Total de Pacientes Hospitalizados (Ingresos)</b><br></td>" +
													"        <td bgcolor='#E3E3E5'><br><b>TASA DE MORTALIDAD INTRAHOSPITALARIA DESPUES DE 48 HORAS <br></b></td></tr>");
												
											out.println("<tr align='center'>" +
													"        <td><br><b>Sumatoria # de dias de cama ocupada</b><br></td>" +
													"        <td><br><b>Sumatoria # de camas disponibles </b><br></td>" +
													"        <td bgcolor='#E3E3E5'><br><b>% DE OCUPACION HOSPITALARIA <br></b></td></tr>");
											
											
											out.println("<tr align='center' >" +
													"        <td><br><b>Sumatoria # de estancia egreso </b><br></td>" +
													"        <td><br><b># Total de egresos </b><br></td>" +
													"        <td bgcolor='#E3E3E5'><br><b>% DIAS PROMEDIO ES ESTANCIA <br></b></td></tr>");
											
											
											out.println("<tr align=>" +
													"        <td><br><b># De Egresos </b><br></td>" +
													"        <td><br><b># De Camas Disponibles </b><br></td>" +
													"        <td bgcolor='#E3E3E5'><br><b> GIRO DE CAMA <br></b></td></tr>");
						
											
											out.println("</table>");
										out.println("</td>");
										out.println("</tr>");
										out.println("</table>");
										

									}
								}
							}
						  
						}
					}
				}
				
				
				
				if(va.equals("PacVeindiasH")){
					System.out.println("entrando a pac 20 dias");
					String tdia=req.getParameter("tdia");
					String tmes=req.getParameter("tmes");
					String tano=req.getParameter("tano");
					String ftdia=req.getParameter("ftdia");
					String ftmes=req.getParameter("ftmes");
					String ftano=req.getParameter("ftano");
					String ftent=req.getParameter("ftent");
					String area=req.getParameter("area");
					String Fechai=tano+"-"+tmes+"-"+tdia;
					String Fechaf=ftano+"-"+ftmes+"-"+ftdia;
					
					rs=mc.BuscarPacIngreRepetidoHosp(Fechai, Fechaf, ftent,area);
					String CodPac;
					String fechareg="";
					String dingre="";
					String ad="";
					String fr="";
					String p="";
					String di="";
					
					out.print("<table width='100%' >");
					out.print("<tr class='TituloRep' align='center'><td colspan='4'><font color='black'>DETALLE DE PACIENTES REINGRESADOS ANTES DE 20 DIAS </font></td></tr>");
					out.print("<tr><td><table width='100%' class='TituloRep' align='center' >");
					out.print("<tr><td><br>Comprendido de "+Fechai+" al "+Fechaf+"</td>");
					out.print("<tr><td colspan='4'>");
					out.print("<table width='100%' border='1'>");
						out.print("<tr class='contpre'  bgcolor='#97C2E9' align='center' font='bold' ><td><br>Documento <br></td><td><br>Nombre de Paciente <br></td></tr>");
						try {
							while(rs.next()){
								CodPac=rs.getString(2);
								rs3=mc.BuscarNoAdm(CodPac,Fechai,Fechaf,ftent,area);
								while(rs3.next()){
									if(rs3.getInt(1)>1){ 
										rs1=mc.BuscarDetAdm(rs3.getString(2),Fechai,Fechaf,ftent,area); 
										while(rs1.next()){
											fechareg=rs1.getString(3);
											rs4=mc.BuscarDiagIngre(rs1.getString(1), rs3.getString(2));
											while(rs4.next()){
												System.out.println(" valor ad "+ad+" = "+rs1.getString(1)+" valor de fr"+fr+" = "+fechareg+" valor de p"+p+" = "+rs3.getString(2)+" valor de di"+di+" = "+rs4.getString(1));
												if((ad.equals(rs1.getString(1)))&&(fr.equals(fechareg))&&(p.equals(rs3.getString(2)))&&(di.equals(rs4.getString(1)))){
												 System.out.println("entrando ");
												}else{
												
													rs2=mc.BuscarPacientesReingHosp(CodPac, Fechai, Fechaf, ftent, fechareg, rs4.getString(1), area, rs1.getString(1));
													while(rs2.next()){
														
														out.println("<tr class='rep' ><td>"+rs2.getString(7)+"</td><td>"+rs2.getString(8)+"</td></tr>");
													}
													rs2.getStatement().getConnection().close();
												}
												
												ad=rs1.getString(1);
												fr=fechareg;
												p=rs3.getString(2);
												di=rs4.getString(1);
												
											}rs4.getStatement().getConnection().close();
										}rs1.getStatement().getConnection().close();
										
									}
								}rs3.getStatement().getConnection().close();
								/*rs1=mc.BuscarFechaRegPacHosp(CodPac, Fechai, Fechaf,ftent,area);
								while(rs1.next()){
									fechareg=rs1.getString(3);
									dingre=rs1.getString(5);
									
									rs2=mc.BuscarPacientesReingHosp(CodPac, Fechai, Fechaf,ftent,fechareg, dingre, area);
									while(rs2.next()){
										
										out.println("<tr><td>"+rs2.getString(7)+"</td><td>"+rs2.getString(8)+"</td><td>"+rs2.getString(2)+"</td><td>"+rs2.getString(3)+"</td></tr>");
									}
									rs2.getStatement().getConnection().close();
									
									System.out.println(rs1.getString(3));
								}rs1.getStatement().getConnection().close();*/
							}rs.getStatement().getConnection().close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						out.println("</table>");
					
				
				}
				
				if(va.equals("EgVHosp")){
					String tdia=req.getParameter("tdia");
					String tmes=req.getParameter("tmes");
					String tano=req.getParameter("tano");
					String ftdia=req.getParameter("ftdia");
					String ftmes=req.getParameter("ftmes");
					String ftano=req.getParameter("ftano");
					String ftent=req.getParameter("ftent");
					String area=req.getParameter("area");
					String Fechai=tano+"-"+tmes+"-"+tdia;
					String Fechaf=ftano+"-"+ftmes+"-"+ftdia;
					//System.out.println("entrando a egVhosp");
					rs=mc.BuscarDetVivoHosp(Fechai, Fechaf, ftent,area);
					out.print("<table width='100%' >");
					out.print("<tr class='TituloRep' align='center'><td colspan='4'><font color='black'> DETALLE DE EGRESOS VIVOS HOSPITALIZACION  </font></td></tr>");
					out.print("<tr><td><table width='100%' class='TituloRep' align='center' >");
					out.print("<tr><td><br>Comprendido de "+Fechai+" al "+Fechaf+"</td>");
					out.print("<tr><td colspan='4'>");
					out.print("<table width='100%' border='1'>");
						out.print("<tr class='contpre'  bgcolor='#97C2E9' align='center' font='bold' ><td><br>Documento <br></td><td><br>Nombre de Paciente <br></td><td>Nombre Entidad</td><td> Admision </td>/tr>");
					try {
						while(rs.next()){
							out.println("<tr class='rep' ><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td></tr>");
							//System.out.println("entrado a mientras que");
						}rs.getStatement().getConnection().close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					out.println("</table>");
					
					
				}
				
				if(va.equals("Tipodiag")){
					String tdia=req.getParameter("tdia");
					String tmes=req.getParameter("tmes");
					String tano=req.getParameter("tano");
					String ftdia=req.getParameter("ftdia");
					String ftmes=req.getParameter("ftmes");
					String ftano=req.getParameter("ftano");
					String ftent=req.getParameter("ftent");
					/*String Fechai=tano+"-"+tmes+"-"+tdia;
					String Fechaf=ftano+"-"+ftmes+"-"+ftdia;*/
					String tadiag=req.getParameter("tadiag");
					
					if(tadiag.equals("1")){
						//IMAGENOLOGIA
						System.out.println("entrando a IMAGENOLOGIA"+ftent);
						out.println("<table width='100%' ><tr><td><table width='100%' border='1' cellspacing='0' bordercolor='white'><tr align='center'><td width='10%' bgcolor='#91AACB' ><b>Tipo de Examen : </b></td><td width='20%' bgcolor='#91AACB'><select id='Img' onChange='TImg("+tdia+","+tmes+","+tano+","+ftdia+","+ftmes+","+ftano+","+ftent+")' style='width:100%' ><option> Seleccione </option>");
				    	rs=mc.BuscarTipoExaImagenologia();
				    	try {
							while(rs.next()){
								out.println("<option value='"+rs.getString(1)+"' title='"+rs.getString(2)+"'>"+rs.getString(2)+"</option>");
							}rs.getStatement().getConnection().close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						out.println("</select></td><td width='5%' bgcolor='#91AACB' ><b> No. </b></td><td width='5%' bgcolor='#91AACB'><b> % </b></td><td width='20%' bgcolor='#6D96CC'><b>Entidad</b></td><td width='5%' bgcolor='#6D96CC'><b>No.</b></td><td width='5%' bgcolor='#6D96CC'>%</td><td width='20%' bgcolor='#4B82CB'><b>Servicio </b></td><td width='5%' bgcolor='#4B82CB'><b>No.</b></td><td width='5%' bgcolor='#4B82CB'><b>%</b></td></tr></table>");
						out.println("<tr><td colspan='3'><div id='repImg' ></div></td></tr>");
						out.println("</table>");
						
					}else{
						if(tadiag.equals("2")){
							//LABORATORIO
							
							System.out.println("entrando a LABORATORIO");
							out.println("<table width='100%' ><tr><td><table width='100%' border='1' cellspacing='0' bordercolor='white' ><tr align='center'><td width='10%' bgcolor='#91AACB' ><b>Tipo de Examen : </b></td><td width='20%' bgcolor='#91AACB' > <select id=Lab onChange='TLab("+tdia+","+tmes+","+tano+","+ftdia+","+ftmes+","+ftano+","+ftent+")' style='width:100%' ><option> Seleccione </option>");
							rs=mc.BuscarTipoExaLabo();
							try {
								while(rs.next()){
									out.println("<option value='"+rs.getString(1)+"'>"+rs.getString(2)+"</option>");
								}rs.getStatement().getConnection().close();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							out.println("</select></td><td width='5%' bgcolor='#91AACB'><b> No. </b></td><td width='5%' bgcolor='#91AACB' ><b> % </b></td><td width='20%' bgcolor='#6D96CC'><b>Entidad</b></td><td width='5%' bgcolor='#6D96CC'><b>No.</b></td><td width='5%' bgcolor='#6D96CC'><b>%</b></td><td width='20%' bgcolor='#4B82CB' ><b>Servicio </b></td><td width='5%' bgcolor='#4B82CB' ><b>No.</b></td><td width='5%' bgcolor='#4B82CB' ><b>%</b></td></tr></table>");
							out.println("<tr><td colspan='3'><div id='repLab' ></div></td></tr>");
							out.println("</table>");
						}else{
							if(tadiag.equals("3")){
								//PATOLOGIA 
								
								System.out.println("PATOLOGIA");
								out.println("<table width='100%' ><tr><td><table width='100%' border='1' cellspacing='0' bordercolor='white'><tr><td width='10%' bgcolor='#91AACB' >Tipo de Examen : </td><td width='20%' bgcolor='#91AACB' ><select id=Pat onChange='TPat("+tdia+","+tmes+","+tano+","+ftdia+","+ftmes+","+ftano+","+ftent+")' style='width:100%' ><option> Seleccione </option>");
								rs=mc.BuscarTipoExaPatologia();
								try {
									while(rs.next()){
										out.println("<option value='"+rs.getString(1)+"'>"+rs.getString(2)+"</option>");
									}rs.getStatement().getConnection().close();
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
						    			out.println("</select>");
						    	out.println("</td><td width='5%' bgcolor='#91AACB' ><b>No. </b></td><td width='5%' bgcolor='#91AACB' ><b>%</b></td><td width='20%' bgcolor='#6D96CC'><b>Entidad</b></td><td width='5%' bgcolor='#6D96CC' ><b>No.</b></td><td width='5%' bgcolor='#6D96CC' >%</td></tr></table>");
						    	out.println("<tr><td colspan='3'><div id='repPat' ></div></td></tr>");
						    	out.println("</table>");
							}
						}
					}  
					
				}
				
				if(va.equals("TImg")){
					String tdia=req.getParameter("tdia");
					String tmes=req.getParameter("tmes");
					String tano=req.getParameter("tano");
					String ftdia=req.getParameter("ftdia");
					String ftmes=req.getParameter("ftmes");
					String ftano=req.getParameter("ftano");
					String ftent=req.getParameter("ftent");
					String Fechai=tano+"-"+tmes+"-"+tdia;
					String Fechaf=ftano+"-"+ftmes+"-"+ftdia;
					String Img=req.getParameter("Img");
					System.out.println("TImg");
					rs=mc.BuscarOrdenesImg(Fechai,Fechaf,ftent,Img);
					out.println("<table width='100%' border='1' CELLSPACING='0'>");
					double SumOrdenes=0;
					int vali=0;
					//out.println("PRUEBA REPORTE DE IMAGENOLOGIA");
					try {
						while(rs.next()){
							SumOrdenes=SumOrdenes+rs.getDouble(2);
							vali=1;
						}rs.getStatement().getConnection().close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					if(vali!=0){
						rs=mc.BuscarOrdenesImg(Fechai,Fechaf,ftent,Img);
						double porc=0;
						try {
							out.println("<tr><td width='40%'><table width='100%'  >");
							//border='1' style='vertical-align: top; border: 1px solid #91AACB; border-collapse: collapse; border-spacing: 0;'
							while(rs.next()){
								porc=(rs.getDouble(2)/SumOrdenes);
								porc=(porc*100);
								String val = porc+"";  
								BigDecimal big = new BigDecimal(val);  
								big = big.setScale(2, RoundingMode.HALF_UP);
								out.println("<tr><td width='30%' colspan='2'><br>"+rs.getString(1)+"</td><td width='5%' align='center'><br>"+rs.getString(2)+"</td><td width='5%' align='center'><br>"+big+"</td></tr>");
							}rs.getStatement().getConnection().close();
							out.println("</table></td>");
							out.println("<td width='30%' ><table width='100%'  >");
							//border='1' CELLSPACING='0' style='border-collapse: collapse;'
							rs1=mc.BuscarOrdEntImg(Fechai,Fechaf,ftent,Img);
							double porc2=0;
							while(rs1.next()){
								porc2=(rs1.getDouble(2)/SumOrdenes);
								porc2=(porc2*100);
								String val = porc2+"";  
								BigDecimal big = new BigDecimal(val);  
								big = big.setScale(2, RoundingMode.HALF_UP); 
								out.println("<tr><td width='20%' ><br>"+rs1.getString(1)+"</td><td width='5%' align='center'><br>"+rs1.getString(2)+"</td><td width='5%' align='center'><br>"+big+"</td></tr>");	
							}rs1.getStatement().getConnection().close();
							
							out.println("</table></td>");
							out.println("<td width='30%' ><table width='100%'  >");
							// border='1' style='vertical-align: top; border: 1px solid #4B82CB; border-collapse: collapse; border-spacing: 0;'
							rs2=mc.BuscarOrdeServImg(Fechai,Fechaf,ftent,Img);
							double porc3=0;
							while(rs2.next()){
								porc3=(rs2.getDouble(3)/SumOrdenes);
								porc3=(porc3*100);
								String val = porc3+"";  
								BigDecimal big = new BigDecimal(val);  
								big = big.setScale(2, RoundingMode.HALF_UP); 
								if(rs2.getString(1).equals("URGENCIA")){
									out.println("<tr class='contpre'><td width='20%' ><br>"+rs2.getString(2)+"</td><td width='5%' align='center'><br>"+rs2.getString(3)+"</td><td width='5%' align='center'><br>"+big+"</td></tr>");	
								}else{
									out.println("<tr class='contpre'><td width='20%' ><br>"+rs2.getString(1)+"-"+rs2.getString(2)+"</td><td width='5%' align='center'><br>"+rs2.getString(3)+"</td><td width='5%' align='center'><br>"+big+"</td></tr>");	
								}
								
							}						
							out.println("</table>");
							
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}else{
						out.println("<tr><td>No se realizaron examenes del tipo escogido en el rango de fecha "+Fechai+ " al "+Fechaf+" </td></tr> ");
					}
					out.println("</table>");
					
					
					
					
					
				}
				
				
				
				if(va.equals("TLab")){
					String tdia=req.getParameter("tdia");
					String tmes=req.getParameter("tmes");
					String tano=req.getParameter("tano");
					String ftdia=req.getParameter("ftdia");
					String ftmes=req.getParameter("ftmes");
					String ftano=req.getParameter("ftano");
					String ftent=req.getParameter("ftent");
					String Fechai=tano+"-"+tmes+"-"+tdia;
					String Fechaf=ftano+"-"+ftmes+"-"+ftdia;
					String Lab=req.getParameter("Lab");
					System.out.println("TLab");
					rs=mc.BuscarOrdenesLab(Fechai,Fechaf,ftent,Lab); 
					out.println("<table width='100%' border='1' CELLSPACING='0'>");
					double SumOrdenes=0;
					int vali=0;
					//out.println("PRUEBA REPORTE DE IMAGENOLOGIA");
					try {
						while(rs.next()){
							SumOrdenes=SumOrdenes+rs.getDouble(4);
							vali=1;
						}rs.getStatement().getConnection().close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					if(vali!=0){
						rs=mc.BuscarOrdenesLab(Fechai,Fechaf,ftent,Lab); 
						double porc=0;
						try {
							out.println("<tr><td width='40%'><table width='100%'  >");
							//border='1' style='vertical-align: top; border: 1px solid #91AACB; border-collapse: collapse; border-spacing: 0;'
							while(rs.next()){
								porc=(rs.getDouble(4)/SumOrdenes);
								porc=(porc*100);
								String val = porc+"";  
								BigDecimal big = new BigDecimal(val);  
								big = big.setScale(2, RoundingMode.HALF_UP);
								out.println("<tr><td width='30%' colspan='2'><br>"+rs.getString(5)+"</td><td width='5%' align='center'><br>"+rs.getString(4)+"</td><td width='5%' align='center'><br>"+big+"</td></tr>");
							}rs.getStatement().getConnection().close();
							out.println("</table></td>");
							out.println("<td width='30%' ><table width='100%'  >");
							//border='1' CELLSPACING='0' style='border-collapse: collapse;'
							rs1=mc.BuscarOrdEntLab(Fechai,Fechaf,ftent,Lab);
							double porc2=0;
							while(rs1.next()){
								porc2=(rs1.getDouble(4)/SumOrdenes);
								porc2=(porc2*100);
								String val = porc2+"";  
								BigDecimal big = new BigDecimal(val);  
								big = big.setScale(2, RoundingMode.HALF_UP); 
								out.println("<tr><td width='20%' ><br>"+rs1.getString(5)+"</td><td width='5%' align='center'><br>"+rs1.getString(4)+"</td><td width='5%' align='center'><br>"+big+"</td></tr>");	
							}rs1.getStatement().getConnection().close();
							
							out.println("</table></td>");
							out.println("<td width='30%' ><table width='100%'  >");
							// border='1' style='vertical-align: top; border: 1px solid #4B82CB; border-collapse: collapse; border-spacing: 0;'
							rs2=mc.BuscarOrdeServLab(Fechai,Fechaf,ftent,Lab);
							double porc3=0;
							while(rs2.next()){
								porc3=(rs2.getDouble(4)/SumOrdenes);
								porc3=(porc3*100);
								String val = porc3+"";  
								BigDecimal big = new BigDecimal(val);  
								big = big.setScale(2, RoundingMode.HALF_UP); 
								if(rs2.getString(1).equals("URGENCIA")){
									out.println("<tr class='contpre'><td width='20%' ><br>"+rs2.getString(6)+"</td><td width='5%' align='center'><br>"+rs2.getString(4)+"</td><td width='5%' align='center'><br>"+big+"</td></tr>");	
								}else{
									out.println("<tr class='contpre'><td width='20%' ><br>"+rs2.getString(5)+"-"+rs2.getString(6)+"</td><td width='5%' align='center'><br>"+rs2.getString(4)+"</td><td width='5%' align='center'><br>"+big+"</td></tr>");	
								}
								
							}						
							out.println("</table>");
							
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}else{
						out.println("<tr><td>No se realizaron examenes del tipo escogido en el rango de fecha "+Fechai+ " al "+Fechaf+" </td></tr> ");
					}
					out.println("</table>");
					
					
					
					
					
				}
				
				
				
				if(va.equals("TPat")){
					String tdia=req.getParameter("tdia");
					String tmes=req.getParameter("tmes");
					String tano=req.getParameter("tano");
					String ftdia=req.getParameter("ftdia");
					String ftmes=req.getParameter("ftmes");
					String ftano=req.getParameter("ftano");
					String ftent=req.getParameter("ftent");
					String Fechai=tano+"-"+tmes+"-"+tdia;
					String Fechaf=ftano+"-"+ftmes+"-"+ftdia;
					String Pat=req.getParameter("Pat");
					System.out.println("TPat "+Pat);
					rs=mc.BuscarOrdenesPat(Fechai,Fechaf,ftent,Pat); 
					out.println("<table width='100%' border='1' CELLSPACING='0'>");
					double SumOrdenes=0;
					int vali=0;
					//out.println("PRUEBA REPORTE DE IMAGENOLOGIA");
					try {
						while(rs.next()){
							System.out.println("valor "+rs.getDouble(2));
							SumOrdenes=SumOrdenes+rs.getDouble(2);
							if(SumOrdenes!=0){
							vali=1;
							}
						}rs.getStatement().getConnection().close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("SumOrdnes "+SumOrdenes);
					if(vali!=0){
						rs=mc.BuscarOrdenesPat(Fechai,Fechaf,ftent,Pat); 
						double porc=0;
						try {
							out.println("<tr><td width='40%'><table width='100%'  >");
							//border='1' style='vertical-align: top; border: 1px solid #91AACB; border-collapse: collapse; border-spacing: 0;'
							while(rs.next()){
								porc=(rs.getDouble(2)/SumOrdenes);
								porc=(porc*100);
								String val = porc+"";  
								BigDecimal big = new BigDecimal(val);  
								big = big.setScale(2, RoundingMode.HALF_UP);
								out.println("<tr><td width='30%' colspan='2'><br>"+rs.getString(1)+"</td><td width='5%' align='center'><br>"+rs.getString(2)+"</td><td width='5%' align='center'><br>"+big+"</td></tr>");
							}rs.getStatement().getConnection().close();
							out.println("</table></td>");
							out.println("<td width='30%' ><table width='100%'  >");
							//border='1' CELLSPACING='0' style='border-collapse: collapse;'
							rs1=mc.BuscarOrdEntPat(Fechai,Fechaf,ftent,Pat);
							double porc2=0;
							while(rs1.next()){
								porc2=(rs1.getDouble(3)/SumOrdenes); 
								porc2=(porc2*100);
								String val = porc2+"";  
								BigDecimal big = new BigDecimal(val);  
								big = big.setScale(2, RoundingMode.HALF_UP); 
								out.println("<tr><td width='20%' ><br>"+rs1.getString(2)+"</td><td width='5%' align='center'><br>"+rs1.getString(3)+"</td><td width='5%' align='center'><br>"+big+"</td></tr>");	
							}rs1.getStatement().getConnection().close();
							
							out.println("</table></td>");
							out.println("<td width='30%' ><table width='100%'  >");
							// border='1' style='vertical-align: top; border: 1px solid #4B82CB; border-collapse: collapse; border-spacing: 0;'
							/*rs2=mc.BuscarOrdeServPat(Fechai,Fechaf,ftent,Pat);
							double porc3=0;
							while(rs2.next()){
								porc3=(rs2.getDouble(4)/SumOrdenes);
								porc3=(porc3*100);
								String val = porc3+"";  
								BigDecimal big = new BigDecimal(val);  
								big = big.setScale(2, RoundingMode.HALF_UP); 
								if(rs2.getString(1).equals("URGENCIA")){
									out.println("<tr class='contpre'><td width='20%' ><br>"+rs2.getString(6)+"</td><td width='5%' align='center'><br>"+rs2.getString(4)+"</td><td width='5%' align='center'><br>"+big+"</td></tr>");	
								}else{
									out.println("<tr class='contpre'><td width='20%' ><br>"+rs2.getString(5)+"-"+rs2.getString(6)+"</td><td width='5%' align='center'><br>"+rs2.getString(4)+"</td><td width='5%' align='center'><br>"+big+"</td></tr>");	
								}
								
							}		*/				
							out.println("</table>");
							
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}else{
						out.println("<tr><td>No se realizaron examenes del tipo escogido en el rango de fecha "+Fechai+ " al "+Fechaf+" </td></tr> ");
					}
					out.println("</table>");
					
					
					
					
					
				}
				
				
				
				
				if(va.equals("VistaSubArea")){
					String Serv=req.getParameter("serv");
					//System.out.println(Serv);
					out.print("<table><tr><td>SUBAREA : </td><td> <select id='area'><option value='todas'>TODAS</option>");
					rs=mc.BuscarArea(Serv);
					try {
						while(rs.next()){
							out.print("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
						}
						out.print("</select>");
						out.print("</td></tr></table>");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			
			}
}
