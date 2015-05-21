package fact_controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

import fact_metodo.MetodoProgramas;
import fact_metodo.MetodoMovimientos;


public class ControlProgramas extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String va = req.getParameter("valor");
		
		String mbase=req.getParameter("mbase");
		String comp=req.getParameter("comp");
		String cref=req.getParameter("cref");
		String cups=req.getParameter("cups");
		String desc=req.getParameter("desc");
		String espe=req.getParameter("espe");
		String serv=req.getParameter("serv");
		String subc=req.getParameter("subc");
		String rips=req.getParameter("rips");
		String prn=req.getParameter("prn");
		String paq=req.getParameter("paq");
		String pme=req.getParameter("pme");
		String prog=req.getParameter("prog");
		//System.out.print("Primero prn: "+prn);
		String modifica=req.getParameter("modifica");
		String valida=req.getParameter("valida");
		String y=req.getParameter("y");
		String v=req.getParameter("v");
		String texto=req.getParameter("texto");
		String xx=req.getParameter("xx");
		String pp=req.getParameter("pp");
		String codp=req.getParameter("codp");
		String adm01=req.getParameter("adm0");
		String subt=req.getParameter("subt");
		
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out=res.getWriter();
		
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		ResultSet rs3=null;
		ResultSet rs4=null;
		ResultSet rs5=null;
		ResultSet rs6=null;
		ResultSet rs7=null;
		
			
		MetodoMovimientos mm = new MetodoMovimientos();
		MetodoProgramas mp = new MetodoProgramas();
		
		if(va.equals("1")){
			out.print("<table width='100%' class='style6'><tr><td colspan='5'><div align='center' class='style11' id='cabecera2'>Criterios de busqueda </div></td></tr>");
			out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='8%'><i><div align='center'>Manual Base</div></i></td><td width='8%'><i><div align='center'>Complejidad</div></i></td><td width='7%'><i><div align='center'>Cod. de Ref.</div></i></td><td width='6%'><i><div align='center'>Cod. CUPS</div></i></td><td width='23%'><i><div align='center'>Descripci�n</div></i></td><td width='9%'><i><div align='center'>Especialidad</div></i></td><td width='9%'><i><div align='center'>Clase Serv.</div></i></td><td width='9%'><i><div align='center'>SubC. Costo</div></i></td><td width='9%'><i><div align='center'>Archivo RIPS</div></i></td><td width='6%'><i><div align='center'>RN</div></i></td><td width='6%'><i><div align='center'>Req. Act.Qx</div></i></td><td width='6%'><i><div align='center'>Req. Medico</div></i></td></tr>");  //
			out.print("<tr><td><label><select  style='width:100%;' name='mbase' id='mbase' ><option value='Seleccione'>Seleccione</option>");
			rs1=mp.ObtenerMbase();
			try {
				while(rs1.next()){
				out.print("<option title="+rs1.getString(2)+" value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			out.print("<td><label><select  style='width:100%;' name='comp' id='comp'><option value='Seleccione'>Seleccione</option>");
			rs2=mp.ObtenerNivel();
			try {
				while(rs2.next()){
				out.print("<option title="+rs2.getString(2)+" value="+rs2.getString(1)+">"+rs2.getString(2)+"</option>");
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			//out.print("<td><input type=text id='mbase' size='8' ></td>" );
			//out.print("<td><input type=text id='comp' size='8' ></td>" );
			out.print("<td><input type=text id='cref' size='8' ></td>" );
			out.print("<td><input type=text id='cups' size='8' ></td>" );
			out.print("<td><input type=text id='desc' size='35'></td>"); 
			
			out.print("<td><label><select style='width:100%;' name='espe' id='espe' ><option value='Seleccione'>Seleccione</option>");
			rs3=mp.ObtenerEspecialidad();
			try {
				while(rs3.next()){
				out.print("<option title="+rs3.getString(2)+" value="+rs3.getString(1)+">"+rs3.getString(2)+"</option>");
				}
				rs3.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			out.print("<td><label><select  style='width:100%;' name='serv' id='serv' ><option value='Seleccione'>Seleccione</option>");
			rs4=mp.ObtenerServicio();
			try {
				while(rs4.next()){
				out.print("<option title="+rs4.getString(2)+" value="+rs4.getString(1)+">"+rs4.getString(2)+"</option>");
				}
				rs4.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			out.print("<td><label><select  style='width:100%;' name='subc' id='subc' ><option value='Seleccione'>Seleccione</option>");
			rs5=mp.ObtenerSubcentro();
			try {
				while(rs5.next()){
				out.print("<option title="+rs5.getString(2)+" value="+rs5.getString(1)+">"+rs5.getString(2)+"</option>");
				}
				rs5.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			out.print("<td><label><select  style='width:100%;' name='rips' id='rips' ><option value='Seleccione'>Seleccione</option>");
			rs6=mp.ObtenerRips();
			try {
				while(rs6.next()){
				out.print("<option title="+rs6.getString(2)+" value="+rs6.getString(1)+">"+rs6.getString(2)+"</option>");
				}
				rs6.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("<td><div align='center'><input type='checkbox' name='rn' id='rnn' value='checkbox' /></div></td>");
			out.print("<td><div align='center'><input type='checkbox' name='aq' id='aqn' value='checkbox' /></div></td>");
			out.print("<td><div align='center'><input type='checkbox' name='med' id='medn' value='checkbox' /></div></td>");
			//out.print("<td align='center'><label><input name='chkvalidar' type='checkbox' id='chkrn' /></label></td>");
			out.print("</table><table width='100%' border='0'><tr><td>&nbsp</td></tr><tr><td colspan='4'><div align='right'><input type='button' name='btnConsultarP' id='btnConsultarP' value='     Consultar     ' onClick='ConsultarP()'></div></td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td><td colspan='4'><div align='left'><input type='button' name='btnIngresarP' id='btnIngresarP' value='        Crear        ' onClick='CrearP()'></div></td></tr>");
			
		}
		
		if(va.equals("2")){
		//out.print("No hay servicios");
		out.print("<table width='100%' class='style6'><tr><td colspan='5'><div align='center' class='style11' id='cabecera2'>Criterios de busqueda </div></td></tr>");
		out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='8%'><i><div align='left'>Manual Base</div></i></td><td width='8%'><i><div align='left'>Complejidad</div></i></td><td width='7%'><i><div align='left'>Cod. de Ref.</div></i></td><td width='23%'><i><div align='left'>Descripci�n</div></i></td></i></td><td width='9%'><i><div align='left'>SubC. Costo</div></i></td></tr>");  //
		out.print("<tr><td><label><select  style='width:100%;' name='mbase' id='mbase' ><option value='Seleccione'>Seleccione</option>");
		rs1=mp.ObtenerMbase();
		try {
			while(rs1.next()){
			out.print("<option title="+rs1.getString(2)+" value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
			}
			rs1.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		
		out.print("<td><label><select  style='width:100%;' name='comp' id='comp'><option value='Seleccione'>Seleccione</option>");
		rs2=mp.ObtenerNivel();
		try {
			while(rs2.next()){
			out.print("<option title="+rs2.getString(2)+" value="+rs2.getString(1)+">"+rs2.getString(2)+"</option>");
			}
			rs2.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		
		//out.print("<td><input type=text id='mbase' size='8' ></td>" );
		//out.print("<td><input type=text id='comp' size='8' ></td>" );
		out.print("<td><input type=text id='cref' size='15' ></td>" );
		out.print("<td><input type=text id='desc' size='62'></td>"); 
		
		out.print("<td><label><select  style='width:100%;' name='subc' id='subc' ><option value='Seleccione'>Seleccione</option>");
		rs5=mp.ObtenerSubcentro();
		try {
			while(rs5.next()){
			out.print("<option title="+rs5.getString(2)+" value="+rs5.getString(1)+">"+rs5.getString(2)+"</option>");
			}
			rs5.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		
		//out.print("<td align='center'><label><input name='chkvalidar' type='checkbox' id='chkrn' /></label></td>");
		out.print("</table><table width='100%' border='0'><tr><td>&nbsp</td></tr><tr><td colspan='4'><div align='right'><input type='button' name='btnConsultarP' id='btnConsultarP' value='     Consultar     ' onClick='ConsultarPq()'></div></td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td><td colspan='4'><div align='left'><input type='button' name='btnIngresarP' id='btnIngresarP' value='        Crear        ' onClick='CrearPq()'></div></td></tr>");
	
		}
		
		
		
		
		if(va.equals("3")){
			//out.print("333333333333333");			
				out.print("<table width='100%' class='style6'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Resultados de la Busqueda</div></td></tr>");
				String sql="select p.cod_programa,fmb.cod_manual_base,fmb.descripcion,fnc.cod_niv_compl,fnc.descripcion,p.cod_referencia,p.cod_cups,p.descripcion,fe.cod_especialidad,fe.descripcion,fcs.cod_clase_servicio,fcs.descripcion,fsc.cod_subcentro_costo,fsc.descripcion, fr.cod_rip,fr.descripcion,p.rn, p.actoqx, p.medico from fact_programas p, fact_manuales_base fmb, fact_niveles_complejidad fnc, fact_especialidades fe, fact_clases_servicio fcs, fact_subcentros_costo fsc, fact_rips fr where p.manual_base!=0 and nivel_complejidad!=0 and especialidad!=0 and clase_servicio!=0 and subcentro_costo!=0 and p.manual_base=fmb.cod_manual_base and fnc.cod_niv_compl=p.nivel_complejidad and fe.cod_especialidad=p.especialidad and fcs.cod_clase_servicio=p.clase_servicio and fsc.cod_subcentro_costo=p.subcentro_costo and fr.cod_rip=p.archivo_rip ";
				
				if(!mbase.equals("Seleccione")){
					sql=sql+" and fmb.cod_manual_base='"+mbase+"'";
				}
				if(!comp.equals("Seleccione")){
					sql=sql+" and fnc.cod_niv_compl='"+comp+"'";
				}
				if(!cref.equals("")){
					sql=sql+" and p.cod_referencia like '%"+cref+"%'";
				}
				if(!cups.equals("")){
					sql=sql+" and p.cod_cups like '%"+cups+"%'";
				}
				if(!desc.equals("")){
					sql=sql+" and p.descripcion like '%"+desc+"%'"; 
				}
				if(!espe.equals("Seleccione")){
					sql=sql+" and fe.cod_especialidad='"+espe+"'";
				}
				if(!serv.equals("Seleccione")){
					sql=sql+" and fcs.cod_clase_servicio='"+serv+"'";
				}
				if(!subc.equals("Seleccione")){
					sql=sql+" and fsc.cod_subcentro_costo='"+subc+"'";
				}
				if(!rips.equals("Seleccione")){
					sql=sql+" and fr.cod_rip='"+rips+"'";
				}
				if(!prn.equals("0")){
					sql=sql+" and p.rn='"+prn+"'";
				}
				if(!paq.equals("0")){
					sql=sql+" and p.actoqx='"+paq+"'";
				}
				if(!pme.equals("0")){
					sql=sql+" and p.medico='"+pme+"'";
				}
				//out.print("nmov "+nmov+" fechas "+fechas+" documento "+documento+" R "+R+" tmov "+tmov);
				//out.print(sql);
				//out.print("<table width='100%' border='1' class='style6'><tr><td width='7%'><div align='center'>Mov. No</div></td><td width='9%'><div align='center'>Fecha</div></td><td width='12%'><div align='center'>Doc. Soporte</div></td><td width='23%'><div align='center'>Movimiento</div></td><td width=30%'><div align='center'>Concepto</div></td><td width='15%'><div align='center'>Realizado por</div></td><td width='4%'><div align='center'>Ver</div></td></tr>");
				System.out.println(sql);
				
				out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='5%'><i><div align='left'>Manual Base</div></i></td><td width='8%'><i><div align='left'>Complejidad</div></i></td><td width='4%'><i><div align='left'>Cod. de Ref.</div></i></td><td width='4%'><i><div align='left'>Cod. CUPS</div></i></td><td width='25%'><i><div align='left'>Descripci�n</div></i></td><td width='8%'><i><div align='left'>Especialidad</div></i></td><td width='11%'><i><div align='left'>Clase de Servicio</div></i></td><td width='9%'><i><div align='left'>Sub Centro Costo</div></i></td><td width='11%'><i><div align='left'>Archivo RIPS</div></i></td><td width='7%'><i><div align='left'>RN</div></i></td><td width='7%'><i><div align='left'>Req. Act.Qx</div></i></td><td width='7%'><i><div align='left'>Req. Medico</div></i></td><td width='5%'><i><div align='center'><input type='checkbox' title='Seleccionar Todos' name='all' id='all' onclick='checkAllca();' /></div></i></td></tr>");            
				rs =mp.ObtenerProgramas(sql);
				String color="'#FF6699'";
				int contador=0;
				try {
					while(rs.next()){
						//out.print("<tr onMouseOver=this.style.background='D3D3D3' onmouseout=this.style.background='white'  >");
						out.print("<tr>");
						out.print("<td>"+rs.getString(3)+"</td>");
						out.print("<td>"+rs.getString(5)+"</td>");
						out.print("<td>"+rs.getString(6)+"</td>");
						out.print("<td>"+rs.getString(7)+"</td>");
						out.print("<td><input title="+rs.getString(8)+" type=text id='descr' size='35' readonly='readonly' value='"+rs.getString(8)+"'></td>");
						out.print("<td>"+rs.getString(10)+"</td>");
						out.print("<td>"+rs.getString(12)+"</td>");
						out.print("<td>"+rs.getString(14)+"</td>");
						out.print("<td>"+rs.getString(16)+"</td>");
						if(rs.getString(17).equals("0")){out.print("<td><div align='center'><input type='checkbox' name='rn' id='rn' value='checkbox' /></div></td>");}
						if(rs.getString(17).equals("1")){out.print("<td><div align='center'><input type='checkbox' name='rn' id='rn' value='checkbox' CHECKED/></div></td>");}
						if(rs.getString(18).equals("0")){out.print("<td><div align='center'><input type='checkbox' name='aq' id='aq' value='checkbox' /></div></td>");}
						if(rs.getString(18).equals("1")){out.print("<td><div align='center'><input type='checkbox' name='aq' id='aq' value='checkbox' CHECKED/></div></td>");}
						if(rs.getString(19).equals("0")){out.print("<td><div align='center'><input type='checkbox' name='med' id='med' value='checkbox' /></div></td>");}
						if(rs.getString(19).equals("1")){out.print("<td><div align='center'><input type='checkbox' name='med' id='med' value='checkbox' CHECKED/></div></td>");}
						
						out.print("<td align='center'><label><input name='chkvalidar' type='checkbox' id='ca' value='"+rs.getString(1)+"' /></label></td>");
						contador=contador+1;
						//out.print("<td><a  href='#'onclick='veranular("+rs.getString(1)+")'>VER</a></td>");

					/*	out.print("<td><input type=text id='mbase' size='8' ></td>" );
						out.print("<td><input type=text id='comp' size='8' ></td>" );
						out.print("<td><input type=text id='cref' size='8' ></td>" );
						out.print("<td><input type=text id='cups' size='8' ></td>" );
						out.print("<td><input type=text id='desc' size='35'></td>");
						out.print("<td><input type=text id='espe' size='8' ></td>" );
						out.print("<td><input type=text id='serv' size='8' ></td>" );
						out.print("<td><input type=text id='subc' size='8' ></td>" );
						out.print("<td><input type=text id='rips' size='8'></td>");
					*/	
						
						//out.print("<td onclick='anular("+rs.getString(1)+")'>"+rs.getString(6)+"</td>");
					}rs.getStatement().getConnection().close();
					
				////ingresar nuevo en la consulta/////////////////////		
					out.print("<tr><td><label><select  style='width:100%;' name='mbase' id='mbase' ><option value='Seleccione'>Seleccione</option>");
					rs1=mp.ObtenerMbase();
					try {
						while(rs1.next()){
						out.print("<option title="+rs1.getString(2)+" value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
						}
						rs1.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					
					out.print("<td><label><select  style='width:100%;' name='comp' id='comp'><option value='Seleccione'>Seleccione</option>");
					rs2=mp.ObtenerNivel();
					try {
						while(rs2.next()){
						out.print("<option title="+rs2.getString(2)+" value="+rs2.getString(1)+">"+rs2.getString(2)+"</option>");
						}
						rs2.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					
					//out.print("<td><input type=text id='mbase' size='8' ></td>" );
					//out.print("<td><input type=text id='comp' size='8' ></td>" );
					out.print("<td><input type=text id='cref' size='3' ></td>" );
					out.print("<td><input type=text id='cups' size='3' ></td>" );
					out.print("<td><input type=text id='desc' size='35'></td>"); 
					
					out.print("<td><label><select style='width:100%;' name='espe' id='espe' ><option value='Seleccione'>Seleccione</option>");
					rs3=mp.ObtenerEspecialidad();
					try {
						while(rs3.next()){
						out.print("<option title="+rs3.getString(2)+" value="+rs3.getString(1)+">"+rs3.getString(2)+"</option>");
						}
						rs3.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					
					out.print("<td><label><select  style='width:100%;' name='serv' id='serv' ><option value='Seleccione'>Seleccione</option>");
					rs4=mp.ObtenerServicio();
					try {
						while(rs4.next()){
						out.print("<option title="+rs4.getString(2)+" value="+rs4.getString(1)+">"+rs4.getString(2)+"</option>");
						}
						rs4.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					
					out.print("<td><label><select  style='width:100%;' name='subc' id='subc' ><option value='Seleccione'>Seleccione</option>");
					rs5=mp.ObtenerSubcentro();
					try {
						while(rs5.next()){
						out.print("<option title="+rs5.getString(2)+" value="+rs5.getString(1)+">"+rs5.getString(2)+"</option>");
						}
						rs5.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					
					out.print("<td><label><select  style='width:100%;' name='rips' id='rips' ><option value='Seleccione'>Seleccione</option>");
					rs6=mp.ObtenerRips();
					try {
						while(rs6.next()){
						out.print("<option title="+rs6.getString(2)+" value="+rs6.getString(1)+">"+rs6.getString(2)+"</option>");
						}
						rs6.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					//out.print("<td><label><select  style='width:100%;' name='rn' id='rn' ><option value='Seleccione'>Seleccione</option><option value='0'>No</option><option value='1'>Si</option>");
					out.print("<td><div align='center'><input type='checkbox' name='rnn' id='rnn'  /></div></td>");
					out.print("<td><div align='center'><input type='checkbox' name='aqn' id='aqn'  /></div></td>");
					out.print("<td><div align='center'><input type='checkbox' name='medn' id='medn'  /></div></td>");
					
					out.print("<td><div align='center'><a  href='#'onclick='CrearP()'>Crear</a></div></td>");
				/**///	out.print("<td><a  href='#'onclick='veranular("+rs.getString(1)+")'>Crear</a></td>");

					//out.print("</table><table width='100%' border='0'><tr><td>&nbsp</td></tr><tr><td colspan='4'><div align='right'><input type='button' name='btnConsultarP' id='btnConsultarP' value='     Consultar     ' onClick='ConsultarP()'></div></td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td><td colspan='4'><div align='left'><input type='button' name='btnIngresarP' id='btnIngresarP' value='        Crear        ' onClick='CrearP()'></div></td></tr>");
					
			////fin de ingresar nuevo en la consulta//////////////
					
					out.print("</table><tr><td colspan='4'><div align='center'><label><input type='button' name='btnIngresarArticulo' id='btnIngresarArticulo' value='     Modificar     ' onClick='ModificarP()'></label></div></td></tr>");
					out.println("<input name='yo' id='yo' type='hidden' value="+contador+">");
					
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
		
		
		}
		
		
		
		
		if(va.equals("4")){
			
			String sql2="select p.cod_programa,fmb.cod_manual_base,fmb.descripcion,fnc.cod_niv_compl,fnc.descripcion,p.cod_referencia,p.cod_cups,p.descripcion,fe.cod_especialidad,fe.descripcion,fcs.cod_clase_servicio,fcs.descripcion,fsc.cod_subcentro_costo,fsc.descripcion, fr.cod_rip,fr.descripcion,p.rn, p.actoqx, p.medico from fact_programas p, fact_manuales_base fmb, fact_niveles_complejidad fnc, fact_especialidades fe, fact_clases_servicio fcs, fact_subcentros_costo fsc, fact_rips fr where p.manual_base=fmb.cod_manual_base and fnc.cod_niv_compl=p.nivel_complejidad and fe.cod_especialidad=p.especialidad and fcs.cod_clase_servicio=p.clase_servicio and fsc.cod_subcentro_costo=p.subcentro_costo and fr.cod_rip=p.archivo_rip and (";
			
			String V[] = new String[50];
			StringTokenizer elementos;  
			elementos = new StringTokenizer(modifica,"|"); 
			   int i1=0;
			   while(elementos.hasMoreTokens()){ 
				  // and (p.cod_programa=1 or p.cod_programa=16)
				   if(i1==0){
					sql2=sql2+"p.cod_programa='"+elementos.nextToken()+"'";
				   }else{
					sql2=sql2+"or p.cod_programa='"+elementos.nextToken()+"'";
				   }
				  // V[i1] = elementos.nextToken();   
				   i1++;
			   }
			   sql2=sql2+")";
			/*   for(int z=0; z<i1; z++){
				   out.print(" V["+z+"]="+V[z]+"\n"); 
			   }
			*/   
			 //  out.print("SQL= "+sql2);
	    			   
			   
		out.print("<table width='100%' class='style6'><tr><td colspan='5'><div align='center' class='style11' id='cabecera2'>Programas </div></td></tr>");
		out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='8%'><i><div align='left'>Manual Base</div></i></td><td width='8%'><i><div align='left'>Complejidad</div></i></td><td width='7%'><i><div align='left'>Cod. de Ref.</div></i></td><td width='6%'><i><div align='left'>Cod. CUPS</div></i></td><td width='23%'><i><div align='left'>Descripci�n</div></i></td><td width='9%'><i><div align='left'>Especialidad</div></i></td><td width='9%'><i><div align='left'>Clase Serv.</div></i></td><td width='9%'><i><div align='left'>SubC. Costo</div></i></td><td width='9%'><i><div align='left'>Archivo RIPS</div></i></td><td width='6%'><i><div align='left'>RN</div></i></td><td width='6%'><i><div align='left'>Req. Act.Qx</div></i></td><td width='6%'><i><div align='left'>Req. Medico</div></i></td></tr>");  //
		
		//out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='8%'><i><div align='left'>Manual Base</div></i></td><td width='8%'><i><div align='left'>Complejidad</div></i></td><td width='7%'><i><div align='left'>Cod. de Ref.</div></i></td><td width='6%'><i><div align='left'>Cod. CUPS</div></i></td><td width='24%'><i><div align='left'>Descripci�n</div></i></td><td width='9%'><i><div align='left'>Especialidad</div></i></td><td width='10%'><i><div align='left'>Clase Serv.</div></i></td><td width='10%'><i><div align='left'>SubC. Costo</div></i></td><td width='9%'><i><div align='left'>Archivo RIPS</div></i></td><td width='2%'><i><div align='left'>RN</div></i></td></tr>");  //
		//out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='8%'><i><div align='left'>Manual Base</div></i></td><td width='8%'><i><div align='left'>Complejidad</div></i></td><td width='9%'><i><div align='left'>Cod. de Ref.</div></i></td><td width='9%'><i><div align='left'>Cod. CUPS</div></i></td><td width='25%'><i><div align='left'>Descripci�n</div></i></td><td width='9%'><i><div align='left'>Especialidad</div></i></td><td width='11%'><i><div align='left'>Clase de Servicio</div></i></td><td width='11%'><i><div align='left'>Sub Centro Costo</div></i></td><td width='11%'><i><div align='left'>Archivo RIPS</div></i></td></tr>");
		
		//out.print("<table width='100%' border='1' class='style6'><tr                                                    ><td width='8%'><i><div align='left'></div></i></td><td width='8%'><i><div align='left'></div></i></td><td width='9%'><i><div align='left'></div></i></td><td width='9%'><i><div align='left'></div></i></td><td width='25%'><i><div align='left'></div></i></td><td width='9%'><i><div align='left'></div></i></td><td width='11%'><i><div align='left'></div></i></td><td width='11%'><i><div align='left'></div></i></td><td width='11%'><i><div align='left'></div></i></td></tr>");
		
		rs=mp.ConsultarProgramas(sql2);
		try {
			while(rs.next()){
									
				out.print("<tr><td><label><select  style='width:100%;' name='mbase' id='mbase"+rs.getString(1)+"' onChange='ModificarPExistente("+rs.getString(1)+",1)'><option value='"+rs.getString(2)+"'>"+rs.getString(3)+"</option>");
				rs1=mp.ObtenerMbase(rs.getString(2));
				try {
					while(rs1.next()){
					out.print("<option title="+rs1.getString(2)+" value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
					}
					rs1.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				
				out.print("<td><label><select  style='width:100%;' name='comp' id='comp"+rs.getString(1)+"' onChange='ModificarPExistente("+rs.getString(1)+",2)'><option value='"+rs.getString(4)+"'>"+rs.getString(5)+"</option>");
				rs2=mp.ObtenerNivel(rs.getString(4));
				try {
					while(rs2.next()){
					out.print("<option title="+rs2.getString(2)+" value="+rs2.getString(1)+">"+rs2.getString(2)+"</option>");
					}
					rs2.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				
				out.print("<td><input type=text id='cref"+rs.getString(1)+"' size='8'  value='"+rs.getString(6)+"' onBlur='ModificarPExistente("+rs.getString(1)+",3)'></td>" );
				out.print("<td><input type=text id='cups"+rs.getString(1)+"' size='8' value='"+rs.getString(7)+"' onBlur='ModificarPExistente("+rs.getString(1)+",4)'></td>" );
				out.print("<td><input type=text id='desc"+rs.getString(1)+"' size='35' value='"+rs.getString(8)+"' onBlur='ModificarPExistente("+rs.getString(1)+",5)'></td>"); 
				
				out.print("<td><label><select style='width:100%;' name='espe' id='espe"+rs.getString(1)+"' onChange='ModificarPExistente("+rs.getString(1)+",6)'><option value='"+rs.getString(9)+"'>"+rs.getString(10)+"</option>");
				rs3=mp.ObtenerEspecialidad(rs.getString(9));
				try {
					while(rs3.next()){
					out.print("<option title="+rs3.getString(2)+" value="+rs3.getString(1)+">"+rs3.getString(2)+"</option>");
					}
					rs3.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				
				out.print("<td><label><select  style='width:100%;' name='serv' id='serv"+rs.getString(1)+"' onChange='ModificarPExistente("+rs.getString(1)+",7)'><option value='"+rs.getString(11)+"'>"+rs.getString(12)+"</option>");
				rs4=mp.ObtenerServicio(rs.getString(11));
				try {
					while(rs4.next()){
					out.print("<option title="+rs4.getString(2)+" value="+rs4.getString(1)+">"+rs4.getString(2)+"</option>");
					}
					rs4.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				
				out.print("<td><label><select  style='width:100%;' name='subc' id='subc"+rs.getString(1)+"' onChange='ModificarPExistente("+rs.getString(1)+",8)'><option value='"+rs.getString(13)+"'>"+rs.getString(14)+"</option>");
				rs5=mp.ObtenerSubcentro(rs.getString(13));
				try {
					while(rs5.next()){
					out.print("<option title="+rs5.getString(2)+" value="+rs5.getString(1)+">"+rs5.getString(2)+"</option>");
					}
					rs5.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				
				out.print("<td><label><select  style='width:100%;' name='rips' id='rips"+rs.getString(1)+"' onChange='ModificarPExistente("+rs.getString(1)+",9)'><option value='"+rs.getString(15)+"'>"+rs.getString(16)+"</option>");
				rs6=mp.ObtenerRips(rs.getString(15));
				try {
					while(rs6.next()){
					out.print("<option title="+rs6.getString(2)+" value="+rs6.getString(1)+">"+rs6.getString(2)+"</option>");
					}
					rs6.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				if(rs.getString(17).equals("0")){
				out.print("<td><div align='center'><input type='checkbox' name='rn' id='rn"+rs.getString(1)+"' onClick='ModificarPExistente("+rs.getString(1)+",10)' /></div></td>");}
				//out.print("<td><label><select  style='width:100%;' name='rn' id='rn"+rs.getString(1)+"' onChange='ModificarPExistente("+rs.getString(1)+",10)'><option value='0'>No</option>");}
				if(rs.getString(17).equals("1")){
				out.print("<td><div align='center'><input type='checkbox' name='rn' id='rn"+rs.getString(1)+"' onClick='ModificarPExistente("+rs.getString(1)+",10)' CHECKED/></div></td>");}
				//out.print("<td><label><select  style='width:100%;' name='rn' id='rn"+rs.getString(1)+"' onChange='ModificarPExistente("+rs.getString(1)+",10)'><option value='1'>Si</option>");}
		    	if(rs.getString(18).equals("0")){
		    	out.print("<td><div align='center'><input type='checkbox' name='aq' id='aq"+rs.getString(1)+"' onClick='ModificarPExistente("+rs.getString(1)+",11)' /></div></td>");}
				if(rs.getString(18).equals("1")){
				out.print("<td><div align='center'><input type='checkbox' name='aq' id='aq"+rs.getString(1)+"' onClick='ModificarPExistente("+rs.getString(1)+",11)' CHECKED/></div></td>");}	
				if(rs.getString(19).equals("0")){
				out.print("<td><div align='center'><input type='checkbox' name='med' id='med"+rs.getString(1)+"' onClick='ModificarPExistente("+rs.getString(1)+",12)' /></div></td>");}
				if(rs.getString(19).equals("1")){
				out.print("<td><div align='center'><input type='checkbox' name='med' id='med"+rs.getString(1)+"' onClick='ModificarPExistente("+rs.getString(1)+",12)' CHECKED/></div></td>");}
					
				//
				
		//		verifica porq sale si en la ultima opcion a rn en el campo q debe traer los estantes
				
			/*	rs7=mp.ObtenerRn(rs.getString(17));
				try {
					while(rs7.next()){
					if(rs7.getString(1).equals("0")){out.print("<option value="+rs7.getString(1)+">No</option>");}
					if(rs7.getString(1).equals("1")){out.print("<option value="+rs7.getString(1)+">Si</option>");}
					}
					rs7.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}*/
				//out.print("<td><input type=text id='subc' size='10' value='"+rs.getString(11)+"'></td>" );
				//out.print("<td><input type=text id='rips' size='8' value='"+rs.getString(12)+"'></td></tr>");
			}
			
			
		////ingresar nuevo en la consulta/////////////////////		
			out.print("<tr><td><label><select  style='width:100%;' name='mbase' id='mbase"+valida+"' ><option value='Seleccione'>Seleccione</option>");
			rs1=mp.ObtenerMbase();
			try {
				while(rs1.next()){
				out.print("<option title="+rs1.getString(2)+" value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			out.print("<td><label><select  style='width:100%;' name='comp' id='comp"+valida+"'><option value='Seleccione'>Seleccione</option>");
			rs2=mp.ObtenerNivel();
			try {
				while(rs2.next()){
				out.print("<option title="+rs2.getString(2)+" value="+rs2.getString(1)+">"+rs2.getString(2)+"</option>");
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			//out.print("<td><input type=text id='mbase' size='8' ></td>" );
			//out.print("<td><input type=text id='comp' size='8' ></td>" );
			out.print("<td><input type=text id='cref"+valida+"' size='8' ></td>" );
			out.print("<td><input type=text id='cups"+valida+"' size='8' ></td>" );
			out.print("<td><input type=text id='desc"+valida+"' size='35'></td>"); 
			
			out.print("<td><label><select style='width:100%;' name='espe' id='espe"+valida+"' ><option value='Seleccione'>Seleccione</option>");
			rs3=mp.ObtenerEspecialidad();
			try {
				while(rs3.next()){
				out.print("<option title="+rs3.getString(2)+" value="+rs3.getString(1)+">"+rs3.getString(2)+"</option>");
				}
				rs3.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			out.print("<td><label><select  style='width:100%;' name='serv' id='serv"+valida+"' ><option value='Seleccione'>Seleccione</option>");
			rs4=mp.ObtenerServicio();
			try {
				while(rs4.next()){
				out.print("<option title="+rs4.getString(2)+" value="+rs4.getString(1)+">"+rs4.getString(2)+"</option>");
				}
				rs4.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			out.print("<td><label><select  style='width:100%;' name='subc' id='subc"+valida+"' ><option value='Seleccione'>Seleccione</option>");
			rs5=mp.ObtenerSubcentro();
			try {
				while(rs5.next()){
				out.print("<option title="+rs5.getString(2)+" value="+rs5.getString(1)+">"+rs5.getString(2)+"</option>");
				}
				rs5.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			out.print("<td><label><select  style='width:100%;' name='rips' id='rips"+valida+"' ><option value='Seleccione'>Seleccione</option>");
			rs6=mp.ObtenerRips();
			try {
				while(rs6.next()){
				out.print("<option title="+rs6.getString(2)+" value="+rs6.getString(1)+">"+rs6.getString(2)+"</option>");
				}
				rs6.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			//out.print("<td><label><select  style='width:100%;' name='rn' id='rn"+valida+"' ><option value='Seleccione'>Seleccione</option><option value='0'>No</option><option value='1'>Si</option>");
			out.print("<td><div align='center'><input type='checkbox' name='rnn' id='rnn"+valida+"'  /></div></td>");
			out.print("<td><div align='center'><input type='checkbox' name='aqn' id='aqn"+valida+"'  /></div></td>");
			out.print("<td><div align='center'><input type='checkbox' name='medn' id='medn"+valida+"'  /></div></td>");
			
			
			//out.print("<td><div align='center'><a  href='#'onclick='CrearP()'>Crear</a></div></td>");
		/**///	out.print("<td><a  href='#'onclick='veranular("+rs.getString(1)+")'>Crear</a></td>");
			//out.print("</table><table width='100%' border='0'><tr><td>&nbsp</td></tr><tr><td colspan='4'><div align='right'><input type='button' name='btnConsultarP' id='btnConsultarP' value='     Consultar     ' onClick='ConsultarP()'></div></td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td><td colspan='4'><div align='left'><input type='button' name='btnIngresarP' id='btnIngresarP' value='        Crear        ' onClick='CrearP()'></div></td></tr>");
		
	////fin de ingresar nuevo en la consulta//////////////
			
			out.print("</table><tr><td colspan='4'><div align='center'><label><input type='button' name='btnIngresarArticulo' id='btnIngresarArticulo' value='     Crear     ' onClick='CrearP2("+valida+")'></label></div></td></tr>");
			//out.print("<td><div id='ripss'>Cesar:\n</div></td>");
			
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		
		
		/*	out.print("<table width='100%' class='style6'>");
			out.print("<tr><td colspan='5'><div align='center' class='style11' id='cabecera2'>Seleccione Una Opcion </div></td></tr>");
			out.print("<tr><td><label><input name='radiobutton' type='radio' value='radiobutton' id='1' onclick='Radios()'/>Programas</label></td><td><label><input name='radiobutton' type='radio' value='radiobutton' id='2' onclick='Radios()' />Servicios</label></td></tr><tr><td colspan='5'><div id='Opcion'></div></td></tr></table>");
			*/
		}	
			
		
		if(va.equals("5")){
			Calendar c = new GregorianCalendar();
			String dia = Integer.toString(c.get(Calendar.DATE));
			String mes = Integer.toString(c.get(Calendar.MONTH));
			String annio = Integer.toString(c.get(Calendar.YEAR));
			int m=Integer.parseInt(mes)+1;
			mes=String.valueOf(m);
			int d=Integer.parseInt(dia);
			if(d<10){dia="0"+d;}
			if(m<10){mes="0"+m;}
			String fecha=annio+"-"+mes+"-"+dia;
			out.print("El prn es: "+prn+"  : ");
			
			Calendar calendario = Calendar.getInstance();
			//	Calendar calendario = new GregorianCalendar();
			int hora, minutos, segundos;
			hora =calendario.get(Calendar.HOUR_OF_DAY);
			minutos = calendario.get(Calendar.MINUTE);
			segundos = calendario.get(Calendar.SECOND);
			String hra= hora+":"+minutos+":"+segundos;
			//out.print(" La hora actual es: "+hra);
			
			String user = req.getParameter("user");
			//out.print(" El usuario es: : "+user);
			//System.out.print("este es prn: "+prn);
			mp.CrearPrograma(mbase, comp, cref, cups, desc, espe, serv, subc, rips,prn,fecha,hra,user,paq,pme);	
			
			////////////////////CREAR PROGRAMA CONTABLE PARA PARAMETRIZAR ////////////////////
			String coppp="0";
			rs5=mp.ObtenerUProgramadeUsuario(fecha,hra,user);
			try {
				if(rs5.next()){
					coppp=rs5.getString(1);
				}
				rs5.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			
			String ccppp="0";
			rs5=mp.ObtenerCentrodeCosto(subc);
			try {
				if(rs5.next()){
					ccppp=rs5.getString(1);
				}
				rs5.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			mp.CrearInterfazdeFacturacion(ccppp, subc,coppp);			


}
		
		if(va.equals("6")){
			
			String sql="";
			//out.print("1RO_YYYY= "+y);
			//out.print("VVVV= "+v+"  ");
			
			if(y.equals("1")){
				//out.print("Y= "+y);
				//out.print("mbase= "+mbase);
				sql="update fact_programas set manual_base='"+mbase+"' where cod_programa='"+v+"'";
			}
			if(y.equals("2")){
				//out.print("Y= "+y);
				//out.print("mbase= "+comp);
				sql="update fact_programas set nivel_complejidad='"+comp+"' where cod_programa='"+v+"'";
			}
			if(y.equals("3")){
				//out.print("Y= "+y);
				//out.print("mbase= "+cref);
				sql="update fact_programas set cod_referencia='"+cref+"' where cod_programa='"+v+"'";
			}
			if(y.equals("4")){
				//out.print("Y= "+y);
				//out.print("mbase= "+cups);
				sql="update fact_programas set cod_cups='"+cups+"' where cod_programa='"+v+"'";
			}
			if(y.equals("5")){
				//out.print("Y= "+y);
				//out.print("mbase= "+desc);
				sql="update fact_programas set descripcion='"+desc+"' where cod_programa='"+v+"'";
			}
			if(y.equals("6")){
				//out.print("Y= "+y);
				//out.print("mbase= "+espe);
				sql="update fact_programas set especialidad='"+espe+"' where cod_programa='"+v+"'";
			}
			if(y.equals("7")){
				//out.print("Y= "+y);
				//out.print("mbase= "+serv);
				sql="update fact_programas set clase_servicio='"+serv+"' where cod_programa='"+v+"'";
			}
			if(y.equals("8")){
				//out.print("Y= "+y);
				//out.print("mbase= "+subc);
				sql="update fact_programas set subcentro_costo='"+subc+"' where cod_programa='"+v+"'";
			

				////////////////////CREAR PROGRAMA CONTABLE PARA PARAMETRIZAR ////////////////////
								
				String ccppp="0";
				rs5=mp.ObtenerCentrodeCosto(subc);
				try {
					if(rs5.next()){
						ccppp=rs5.getString(1);
					}
					rs5.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				
				
				
				int ww=0;
				rs5=mp.ConsultarInterfazFacturacion(ccppp,subc,v);
				try {
					if(rs5.next()){
						ww=1;
					}
					rs5.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				
				if(ww==0){
					mp.CrearInterfazdeFacturacion(ccppp, subc,v);	
				}
				
				////////////////////FIN INTERFAZ//////////////////



}
			if(y.equals("9")){
				//out.print("Y= "+y);
				//out.print("mbase= "+rips);
				sql="update fact_programas set archivo_rip='"+rips+"' where cod_programa='"+v+"'";
			}
			if(y.equals("10")){
				//out.print("Y= "+y);
				//out.print("prn= "+prn);
				sql="update fact_programas set rn='"+prn+"' where cod_programa='"+v+"'";
			}
			if(y.equals("11")){
				//out.print("Y= "+y);
				//out.print("prn= "+prn);
				sql="update fact_programas set actoqx='"+paq+"' where cod_programa='"+v+"'";
			}
			if(y.equals("12")){
				//out.print("Y= "+y);
				//out.print("prn= "+prn);
				sql="update fact_programas set medico='"+pme+"' where cod_programa='"+v+"'";
			}
			mp.ModificarPExistente(sql);	
		}
		
		
		if(va.equals("7")){
			out.print("<table width='100%' class='style6'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Resultados de la Busqueda</div></td></tr>");
			String sql="select p.cod_paquete,fmb.cod_manual_base,fmb.descripcion,fnc.cod_niv_compl,fnc.descripcion,p.cod_referencia,p.descripcion,fsc.cod_subcentro_costo,fsc.descripcion from fact_paquetes p, fact_manuales_base fmb, fact_niveles_complejidad fnc, fact_subcentros_costo fsc where p.manual_base=fmb.cod_manual_base and fnc.cod_niv_compl=p.nivel_complejidad  and fsc.cod_subcentro_costo=p.cod_subcentro_costo_fk ";
			
			if(!mbase.equals("Seleccione")){
				sql=sql+" and fmb.cod_manual_base='"+mbase+"'";
			}
			if(!comp.equals("Seleccione")){
				sql=sql+" and fnc.cod_niv_compl='"+comp+"'";
			}
			if(!cref.equals("")){
				sql=sql+" and p.cod_referencia like '%"+cref+"%'";
			}
			if(!desc.equals("")){
				sql=sql+" and p.descripcion like '%"+desc+"%'"; 
			}
			if(!subc.equals("Seleccione")){
				sql=sql+" and fsc.cod_subcentro_costo='"+subc+"'";
			}
			out.print("<table id='tpq' width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='8%'><i><div align='left'>Manual Base</div></i></td><td width='8%'><i><div align='left'>Complejidad</div></i></td><td width='7%'><i><div align='left'>Cod. de Ref.</div></i></td><td width='23%'><i><div align='left'>Descripci�n</div></i></td></i></td><td width='9%'><i><div align='left'>SubC. Costo</div></i></td><td width='5%'><i><div align='left'>Acci�n<input type='checkbox' name='all' id='all' onclick='checkAll();' /></div></i></td></tr>");  //
			
			rs =mp.ObtenerProgramas(sql);
			//String color="'#81BEF7'";#E6E6E6
			int contadorq=0;
			try {
				while(rs.next()){
					out.print("<tr id='tr"+contadorq+"' onClick=pq("+contadorq+","+rs.getString(1)+")  >");
					//out.print("<tr>");
					out.print("<td>"+rs.getString(3)+"</td>");
					out.print("<td>"+rs.getString(5)+"</td>");
					out.print("<td>"+rs.getString(6)+"</td>");
					out.print("<td><input type=text id='descr' size='57' readonly='readonly' value='"+rs.getString(7)+"'></td>");
					out.print("<td>"+rs.getString(9)+"</td>");
					out.print("<td align='center'><label><input name='chkvalidar' type='checkbox' id='ca' value='"+rs.getString(1)+"' /></label></td>");
					contadorq=contadorq+1;
					//out.print("<td><a  href='#'onclick='veranular("+rs.getString(1)+")'>VER</a></td>");
					
					//out.print("<td onclick='anular("+rs.getString(1)+")'>"+rs.getString(6)+"</td>");
				}
				
			////ingresar nuevo en la consulta/////////////////////		
				out.print("<tr><td><label><select  style='width:100%;' name='mbase' id='mbase' ><option value='Seleccione'>Seleccione</option>");
				rs1=mp.ObtenerMbase();
				try {
					while(rs1.next()){
					out.print("<option title="+rs1.getString(2)+" value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
					}
					rs1.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				
				out.print("<td><label><select  style='width:100%;' name='comp' id='comp'><option value='Seleccione'>Seleccione</option>");
				rs2=mp.ObtenerNivel();
				try {
					while(rs2.next()){
					out.print("<option title="+rs2.getString(2)+" value="+rs2.getString(1)+">"+rs2.getString(2)+"</option>");
					}
					rs2.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				
				//out.print("<td><input type=text id='mbase' size='8' ></td>" );
				//out.print("<td><input type=text id='comp' size='8' ></td>" );
				out.print("<td><input type=text id='cref' size='15' ></td>" );
				out.print("<td><input type=text id='desc' size='57'></td>"); 
				
			
			
				out.print("<td><label><select  style='width:100%;' name='subc' id='subc' ><option value='Seleccione'>Seleccione</option>");
				rs5=mp.ObtenerSubcentro();
				try {
					while(rs5.next()){
					out.print("<option title="+rs5.getString(2)+" value="+rs5.getString(1)+">"+rs5.getString(2)+"</option>");
					}
					rs5.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				
				
				out.print("<td><div align='center'><a  href='#'onclick='CrearPq()'>Crear</a></div></td>");
			
		////fin de ingresar nuevo en la consulta//////////////
				
				out.print("</table><tr><td colspan='4'><div align='center'><label><input type='button' name='btnIngresarArticulo' id='btnIngresarArticulo' value='     Modificar     ' onClick='ModificarPq()'></label></div></td></tr>");
				out.println("<input name='yoq' id='yoq' type='hidden' value="+contadorq+">");
				
			///div para dibujar los programas asociados al servicio	
				out.print("<td><div id='prog'>");
				out.print("<table width='100%' class='style6'><tr><td colspan='5'><div align='center' class='style11' id='cabecera2'>Programas asociados al Servicio </div></td></tr>");
				out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='57%'><i><div align='left'>Descripci�n</div></i></td><td width='17%'><i><div align='left'>Especialidad</div></i></td><td width='17%'><i><div align='left'>Clase de Serv.</div></i></td></tr>");  //
				out.print("</div></td>");
				
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
		}
		
		
		
		if(va.equals("8")){
			
			String sql2=" select p.cod_paquete,fmb.cod_manual_base,fmb.descripcion,fnc.cod_niv_compl,fnc.descripcion,p.cod_referencia,p.descripcion,fsc.cod_subcentro_costo,fsc.descripcion from fact_paquetes p, fact_manuales_base fmb, fact_niveles_complejidad fnc, fact_subcentros_costo fsc where p.manual_base=fmb.cod_manual_base and fnc.cod_niv_compl=p.nivel_complejidad and fsc.cod_subcentro_costo=p.cod_subcentro_costo_fk and (";
			
			String V[] = new String[50];
			StringTokenizer elementos;  
			elementos = new StringTokenizer(modifica,"|"); 
			   int i1=0;
			   while(elementos.hasMoreTokens()){ 
				  // and (p.cod_programa=1 or p.cod_programa=16)
				   if(i1==0){
					sql2=sql2+"p.cod_paquete='"+elementos.nextToken()+"'";
				   }else{
					sql2=sql2+"or p.cod_paquete='"+elementos.nextToken()+"'";
				   }
				  // V[i1] = elementos.nextToken();   
				   i1++;
			   }
			   sql2=sql2+")";
			/*   for(int z=0; z<i1; z++){
				   out.print(" V["+z+"]="+V[z]+"\n"); 
			   }
			*/   
			 //  out.print("SQL= "+sql2);
	    			   
			   
		out.print("<table width='100%' class='style6'><tr><td colspan='5'><div align='center' class='style11' id='cabecera2'>Servicios </div></td></tr>");
		out.print("<table id='tapq' width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='8%'><i><div align='left'>Manual Base</div></i></td><td width='8%'><i><div align='left'>Complejidad</div></i></td><td width='7%'><i><div align='left'>Cod. de Ref.</div></i></td><td width='23%'><i><div align='left'>Descripci�n</div></i></td></i></td><td width='9%'><i><div align='left'>SubC. Costo</div></i></td></tr>");  //
		//out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='8%'><i><div align='left'>Manual Base</div></i></td><td width='8%'><i><div align='left'>Complejidad</div></i></td><td width='7%'><i><div align='left'>Cod. de Ref.</div></i></td><td width='23%'><i><div align='left'>Descripci�n</div></i></td></i></td><td width='9%'><i><div align='left'>SubC. Costo</div></i></td><td width='5%'><i><div align='left'>Acci�n<input type='checkbox' name='all' id='all' onclick='checkAll();' /></div></i></td></tr>");  //
		int conq=0;
		rs=mp.ConsultarProgramas(sql2);
		try {
			while(rs.next()){
				conq++;					
				out.print("<tr id='tr"+conq+"' onClick='pq2("+conq+","+rs.getString(1)+"); '><td><label><select  style='width:100%;' name='mbase' id='mbase"+rs.getString(1)+"' onChange='ModificarPqExistente("+rs.getString(1)+",1)'><option value='"+rs.getString(2)+"'>"+rs.getString(3)+"</option>");
				rs1=mp.ObtenerMbase(rs.getString(2));
				try {
					while(rs1.next()){
					out.print("<option title="+rs1.getString(2)+" value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
					}
					rs1.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				
				out.print("<td><label><select  style='width:100%;' name='comp' id='comp"+rs.getString(1)+"' onChange='ModificarPqExistente("+rs.getString(1)+",2)'><option value='"+rs.getString(4)+"'>"+rs.getString(5)+"</option>");
				rs2=mp.ObtenerNivel(rs.getString(4));
				try {
					while(rs2.next()){
					out.print("<option title="+rs2.getString(2)+" value="+rs2.getString(1)+">"+rs2.getString(2)+"</option>");
					}
					rs2.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				
				out.print("<td><input type=text id='cref"+rs.getString(1)+"' size='15' value='"+rs.getString(6)+"' onBlur='ModificarPqExistente("+rs.getString(1)+",3)'></td>" );
				out.print("<td><input type=text id='desc"+rs.getString(1)+"' size='65' value='"+rs.getString(7)+"' onBlur='ModificarPqExistente("+rs.getString(1)+",4)'></td>"); 
				
				
				out.print("<td><label><select  style='width:100%;' name='subc' id='subc"+rs.getString(1)+"' onChange='ModificarPqExistente("+rs.getString(1)+",5)'><option value='"+rs.getString(8)+"'>"+rs.getString(9)+"</option>");
				rs5=mp.ObtenerSubcentro(rs.getString(8));
				try {
					while(rs5.next()){
					out.print("<option title="+rs5.getString(2)+" value="+rs5.getString(1)+">"+rs5.getString(2)+"</option>");
					}
					rs5.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
			}
			
			
		////ingresar nuevo en la consulta/////////////////////		
			out.print("<tr><td><label><select  style='width:100%;' name='mbase' id='mbasen"+valida+"' ><option value='Seleccione'>Seleccione</option>");
			rs1=mp.ObtenerMbase();
			try {
				while(rs1.next()){
				out.print("<option title="+rs1.getString(2)+" value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			out.print("<td><label><select  style='width:100%;' name='comp' id='compn"+valida+"'><option value='Seleccione'>Seleccione</option>");
			rs2=mp.ObtenerNivel();
			try {
				while(rs2.next()){
				out.print("<option title="+rs2.getString(2)+" value="+rs2.getString(1)+">"+rs2.getString(2)+"</option>");
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			out.print("<td><input type=text id='crefn"+valida+"' size='15' ></td>" );
			out.print("<td><input type=text id='descn"+valida+"' size='65'></td>"); 
			
			out.print("<td><label><select  style='width:100%;' name='subc' id='subcn"+valida+"' ><option value='Seleccione'>Seleccione</option>");
			rs5=mp.ObtenerSubcentro();
			try {
				while(rs5.next()){
				out.print("<option title="+rs5.getString(2)+" value="+rs5.getString(1)+">"+rs5.getString(2)+"</option>");
				}
				rs5.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
					
	////fin de ingresar nuevo en la consulta//////////////
			
			out.print("</table><tr><td colspan='4'><div align='center'><label><input type='button' name='btnIngresarArticulo' id='btnIngresarArticulo' value='     Crear     ' onClick='CrearPq2("+valida+")'></label></div></td></tr>");
		
			///div para dibujar los programas asociados al servicio	
			out.print("<tr><td><div id='progs'>");
			out.print("<table width='100%' class='style6'><tr><td colspan='5'><div align='center' class='style11' id='cabecera2'>Programas asociados al Servicio </div></td></tr>");
			out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='57%'><i><div align='left'>Descripci�n</div></i></td><td width='17%'><i><div align='left'>Especialidad</div></i></td><td width='17%'><i><div align='left'>Clase de Serv.</div></i></td><td width='9%'><i><div align='left'>Acci�n</div></i></td></tr>");  //
			out.print("</div></td></tr>");
			
		//	out.print("<td><div id='ripss'>Cesar:\n</div></td>");
			
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
	}
		
		
		if(va.equals("9")){
			String sql="";
			out.print("1RO_YYYY= "+y);
			out.print("VVVV= "+v+"  ");
			
			if(y.equals("1")){
				//out.print("Y= "+y);
				//out.print("mbase= "+mbase);
				sql="update fact_paquetes set manual_base='"+mbase+"' where cod_paquete='"+v+"'";
			}
			if(y.equals("2")){
				//out.print("Y= "+y);
				//out.print("mbase= "+comp);
				sql="update fact_paquetes set nivel_complejidad='"+comp+"' where cod_paquete='"+v+"'";
			}
			if(y.equals("3")){
				//out.print("Y= "+y);
				//out.print("mbase= "+cref);
				sql="update fact_paquetes set cod_referencia='"+cref+"' where cod_paquete='"+v+"'";
			}
			if(y.equals("4")){
				//out.print("Y= "+y);
				//out.print("mbase= "+desc);
				sql="update fact_paquetes set descripcion='"+desc+"' where cod_paquete='"+v+"'";
			}
			if(y.equals("5")){
				//out.print("Y= "+y);
				//out.print("mbase= "+subc);
				sql="update fact_paquetes set cod_subcentro_costo_fk='"+subc+"' where cod_paquete='"+v+"'";
			}
	
			mp.ModificarPExistente(sql);
		}
		
		
		if(va.equals("10")){
			//System.out.print("este es prn: "+prn);
			mp.CrearServicio(mbase, comp, cref, desc, subc);	
		}
		
		
		if(va.equals("11")){
			//out.print("prog: "+prog);
			out.print("<table width='100%' class='style6'><tr><td colspan='5'><div align='center' class='style11' id='cabecera2'>Programas asociados al Servicio </div></td></tr>");
			out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='60%'><i><div align='left'>Descripci�n</div></i></td><td width='20%'><i><div align='left'>Especialidad</div></i></td><td width='20%'><i><div align='left'>Clase de Serv.</div></i></td></tr>");  //
						
			String sqlp="select p.descripcion, p.especialidad, e.descripcion, p.clase_servicio, cs.descripcion from fact_progs_paquetes pp, fact_programas p, fact_especialidades e, fact_clases_servicio cs where pp.cod_paquete='"+prog+"' and pp.cod_programa=p.cod_programa and p.especialidad=e.cod_especialidad and p.clase_servicio=cs.cod_clase_servicio";
	
			rs =mp.ObtenerProgramas(sqlp);
			try {
			while(rs.next()){
			out.print("<tr><td><input type=text id='desc' size='93' readonly='readonly' value='"+rs.getString(1)+"'></td>"); 
			out.print("<td><label><select style='width:100%;' name='espe' id='espe' ><option value='"+rs.getString(2)+"'>"+rs.getString(3)+"</option>");
			out.print("<td><label><select  style='width:100%;' name='serv' id='serv' ><option value='"+rs.getString(4)+"'>"+rs.getString(5)+"</option>");
			out.print("</tr>");
			}
			rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
		}
		
		
		if(va.equals("12")){
			//out.print("prog: "+prog);
			out.print("<table width='100%' class='style6'><tr><td colspan='5'><div align='center' class='style11' id='cabecera2'>Programas asociados al Servicio </div></td></tr>");
			out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='57%'><i><div align='left'>Descripci�n</div></i></td><td width='17%'><i><div align='left'>Especialidad</div></i></td><td width='17%'><i><div align='left'>Clase de Serv.</div></i></td><td width='9%'><i><div align='left'>Acci�n</div></i></td></tr>");  //
						
			String sqlp="select p.cod_programa, p.descripcion, p.especialidad, e.descripcion, p.clase_servicio, cs.descripcion, pp.cod_pxp from fact_progs_paquetes pp, fact_programas p, fact_especialidades e, fact_clases_servicio cs where pp.cod_paquete='"+prog+"' and pp.cod_programa=p.cod_programa and p.especialidad=e.cod_especialidad and p.clase_servicio=cs.cod_clase_servicio";
	
			rs =mp.ObtenerProgramas(sqlp);
			try {
			while(rs.next()){
			out.print("<tr><td><input type=text id='desc' size='93' readonly='readonly' value='"+rs.getString(2)+"'></td>"); 
			out.print("<td><label><select style='width:100%;' name='espe' id='espe' ><option value='"+rs.getString(3)+"'>"+rs.getString(4)+"</option>");
			out.print("<td><label><select  style='width:100%;' name='serv' id='serv' ><option value='"+rs.getString(5)+"'>"+rs.getString(6)+"</option>");
			out.print("<td><div align='center'><a  href='#'onclick='Eliminarpq("+rs.getString(7)+","+prog+","+v+")'>Eliminar</a></div></td>");
			out.print("</tr>");
			}rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			out.print("<tr><td><input type=text id='desc0' size='93' onClick='cesarj();' onkeyup='autocompletaPrograma(0,0)' ></td>"); 
			out.print("<input name='desch0' type='hidden' id='desch0'/></td>");
			out.print("<td><label><select style='width:100%;' name='espe0' id='espe0' ><option value='Seleccione'>Seleccione</option>");
			out.print("<td><label><select  style='width:100%;' name='serv0' id='serv0' ><option value='Seleccione'>Seleccione</option>");
			out.print("<input name='mbase0' type='hidden' id='mbase0' value='"+mbase+"'/></td>");
			out.print("<td><div align='center'><a  href='#'onclick='Asignarpq("+prog+","+v+")'>Asignar</a></div></td>");
			out.print("</tr>");
			out.print("<tr><td><div id='sugerencias0'></div></td></tr>");
			
			
		}
		
		
		if(va.equals("13")){
			mp.AsignarPrograma(codp,prog);	
		}

		if(va.equals("14")){
			mp.EliminarPrograma(pp);	
		}
		
		if(va.equals("15")){
			//out.print("333333333333333");			
			out.print("<table width='100%' class='style6'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Resultados de la Busqueda</div></td></tr>");
			String sql=" select cod_programa,cod_referencia,descripcion from fact_programas where manual_base=0 or nivel_complejidad=0 or especialidad=0 or clase_servicio=0 or subcentro_costo=0 or archivo_rip=0";
						
			out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='9%'><i><div align='center'>Cod. de Ref.</div></i></td><td width='25%'><i><div align='center'>Descripci�n</div></i></td><td width='5%'><i><div align='center'>Acci�n<input type='checkbox' name='all' id='all' onclick='checkAll();' /></div></i></td></tr>");            
			rs =mp.ObtenerProgramas(sql);
			String color="'#FF6699'";
			int contador=0;
			try {
				while(rs.next()){
					//out.print("<tr onMouseOver=this.style.background='D3D3D3' onmouseout=this.style.background='white'  >");
					out.print("<tr>");
					out.print("<td>"+rs.getString(2)+"</td>");
					out.print("<td><input type=text id='descr' size='99' readonly='readonly' value='"+rs.getString(3)+"'></td>");
					out.print("<td align='center'><label><input name='chkvalidar' type='checkbox' id='ca' value='"+rs.getString(1)+"' /></label></td>");
					contador=contador+1;
					}
									
				out.print("</table><tr><td colspan='4'><div align='center'><label><input type='button' name='btnIngresarArticulo' id='btnIngresarArticulo' value='     Modificar     ' onClick='ModificarPP()'></label></div></td></tr>");
				out.println("<input name='yo' id='yo' type='hidden' value="+contador+">");
				
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
		}
		
		
		
		if(va.equals("16")){
			
			String sql2="select p.cod_programa,fmb.cod_manual_base,fmb.descripcion,fnc.cod_niv_compl,fnc.descripcion,p.cod_referencia,p.cod_cups,p.descripcion,fe.cod_especialidad,fe.descripcion,fcs.cod_clase_servicio,fcs.descripcion,fsc.cod_subcentro_costo,fsc.descripcion, fr.cod_rip,fr.descripcion,p.rn,p.actoqx,p.medico from fact_programas p, fact_manuales_base fmb, fact_niveles_complejidad fnc, fact_especialidades fe, fact_clases_servicio fcs, fact_subcentros_costo fsc, fact_rips fr where p.manual_base=fmb.cod_manual_base and fnc.cod_niv_compl=p.nivel_complejidad and fe.cod_especialidad=p.especialidad and fcs.cod_clase_servicio=p.clase_servicio and fsc.cod_subcentro_costo=p.subcentro_costo and fr.cod_rip=p.archivo_rip and (";
			
			String V[] = new String[50];
			StringTokenizer elementos;  
			elementos = new StringTokenizer(modifica,"|"); 
			   int i1=0;
			   while(elementos.hasMoreTokens()){ 
				   if(i1==0){
					sql2=sql2+"p.cod_programa='"+elementos.nextToken()+"'";
				   }else{
					sql2=sql2+"or p.cod_programa='"+elementos.nextToken()+"'";
				   }
				   i1++;
			   }
			   sql2=sql2+")";
		
		out.print("<table width='100%' class='style6'><tr><td colspan='5'><div align='center' class='style11' id='cabecera2'>Programas </div></td></tr>");
		out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='8%'><i><div align='left'>Manual Base</div></i></td><td width='8%'><i><div align='left'>Complejidad</div></i></td><td width='7%'><i><div align='left'>Cod. de Ref.</div></i></td><td width='6%'><i><div align='left'>Cod. CUPS</div></i></td><td width='23%'><i><div align='left'>Descripci�n</div></i></td><td width='9%'><i><div align='left'>Especialidad</div></i></td><td width='9%'><i><div align='left'>Clase Serv.</div></i></td><td width='9%'><i><div align='left'>SubC. Costo</div></i></td><td width='9%'><i><div align='left'>Archivo RIPS</div></i></td><td width='6%'><i><div align='left'>RN</div></i></td><td width='6%'><i><div align='left'>Req. Act.Qx</div></i></td><td width='6%'><i><div align='left'>Req. Medico</div></i></td></tr>");  //
		
			
		rs=mp.ConsultarProgramas(sql2);
		try {
			while(rs.next()){
									
				out.print("<tr><td><label><select  style='width:100%;' name='mbase' id='mbase"+rs.getString(1)+"' onChange='ModificarPExistente("+rs.getString(1)+",1)'><option value='"+rs.getString(2)+"'>"+rs.getString(3)+"</option>");
				rs1=mp.ObtenerMbase(rs.getString(2));
				try {
					while(rs1.next()){
					out.print("<option title="+rs1.getString(2)+" value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
					}
					rs1.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				
				out.print("<td><label><select  style='width:100%;' name='comp' id='comp"+rs.getString(1)+"' onChange='ModificarPExistente("+rs.getString(1)+",2)'><option value='"+rs.getString(4)+"'>"+rs.getString(5)+"</option>");
				rs2=mp.ObtenerNivel(rs.getString(4));
				try {
					while(rs2.next()){
					out.print("<option title="+rs2.getString(2)+" value="+rs2.getString(1)+">"+rs2.getString(2)+"</option>");
					}
					rs2.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				
				out.print("<td><input type=text id='cref"+rs.getString(1)+"' size='8' value='"+rs.getString(6)+"' onBlur='ModificarPExistente("+rs.getString(1)+",3)'></td>" );
				out.print("<td><input type=text id='cups"+rs.getString(1)+"' size='8' value='"+rs.getString(7)+"' onBlur='ModificarPExistente("+rs.getString(1)+",4)'></td>" );
				out.print("<td><input type=text id='desc"+rs.getString(1)+"' size='35' value='"+rs.getString(8)+"' onBlur='ModificarPExistente("+rs.getString(1)+",5)'></td>"); 
				
				out.print("<td><label><select style='width:100%;' name='espe' id='espe"+rs.getString(1)+"' onChange='ModificarPExistente("+rs.getString(1)+",6)'><option value='"+rs.getString(9)+"'>"+rs.getString(10)+"</option>");
				rs3=mp.ObtenerEspecialidad(rs.getString(9));
				try {
					while(rs3.next()){
					out.print("<option title='"+rs3.getString(2)+"' value="+rs3.getString(1)+">"+rs3.getString(2)+"</option>");
					}
					rs3.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				
				out.print("<td><label><select style='width:100%;' name='serv' id='serv"+rs.getString(1)+"' onChange='ModificarPExistente("+rs.getString(1)+",7)'><option value='"+rs.getString(11)+"'>"+rs.getString(12)+"</option>");
				rs4=mp.ObtenerServicio(rs.getString(11));
				try {
					while(rs4.next()){
					out.print("<option title="+rs4.getString(2)+" value="+rs4.getString(1)+">"+rs4.getString(2)+"</option>");
					}
					rs4.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				
				out.print("<td><label><select  style='width:100%;' name='subc' id='subc"+rs.getString(1)+"' onChange='ModificarPExistente("+rs.getString(1)+",8)'><option value='"+rs.getString(13)+"'>"+rs.getString(14)+"</option>");
				rs5=mp.ObtenerSubcentro(rs.getString(13));
				try {
					while(rs5.next()){
					out.print("<option title="+rs5.getString(2)+" value="+rs5.getString(1)+">"+rs5.getString(2)+"</option>");
					}
					rs5.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				
				out.print("<td><label><select  style='width:100%;' name='rips' id='rips"+rs.getString(1)+"' onChange='ModificarPExistente("+rs.getString(1)+",9)'><option value='"+rs.getString(15)+"'>"+rs.getString(16)+"</option>");
				rs6=mp.ObtenerRips(rs.getString(15));
				try {
					while(rs6.next()){
					out.print("<option title="+rs6.getString(2)+" value="+rs6.getString(1)+">"+rs6.getString(2)+"</option>");
					}
					rs6.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
			/*	if(rs.getString(17).equals("0")){
				out.print("<td><label><select  style='width:100%;' name='rn' id='rn"+rs.getString(1)+"' onChange='ModificarPExistente("+rs.getString(1)+",10)'><option value='0'>No</option>");}
				if(rs.getString(17).equals("1")){
				out.print("<td><label><select  style='width:100%;' name='rn' id='rn"+rs.getString(1)+"' onChange='ModificarPExistente("+rs.getString(1)+",10)'><option value='1'>Si</option>");}
			*/	
				if(rs.getString(17).equals("0")){
					out.print("<td><div align='center'><input type='checkbox' name='rn' id='rn"+rs.getString(1)+"' onClick='ModificarPExistente("+rs.getString(1)+",10)' /></div></td>");}
					//out.print("<td><label><select  style='width:100%;' name='rn' id='rn"+rs.getString(1)+"' onChange='ModificarPExistente("+rs.getString(1)+",10)'><option value='0'>No</option>");}
					if(rs.getString(17).equals("1")){
					out.print("<td><div align='center'><input type='checkbox' name='rn' id='rn"+rs.getString(1)+"' onClick='ModificarPExistente("+rs.getString(1)+",10)' CHECKED/></div></td>");}
					//out.print("<td><label><select  style='width:100%;' name='rn' id='rn"+rs.getString(1)+"' onChange='ModificarPExistente("+rs.getString(1)+",10)'><option value='1'>Si</option>");}
			    	if(rs.getString(18).equals("0")){
			    	out.print("<td><div align='center'><input type='checkbox' name='aq' id='aq"+rs.getString(1)+"' onClick='ModificarPExistente("+rs.getString(1)+",11)' /></div></td>");}
					if(rs.getString(18).equals("1")){
					out.print("<td><div align='center'><input type='checkbox' name='aq' id='aq"+rs.getString(1)+"' onClick='ModificarPExistente("+rs.getString(1)+",11)' CHECKED/></div></td>");}	
					if(rs.getString(19).equals("0")){
					out.print("<td><div align='center'><input type='checkbox' name='med' id='med"+rs.getString(1)+"' onClick='ModificarPExistente("+rs.getString(1)+",12)' /></div></td>");}
					if(rs.getString(19).equals("1")){
					out.print("<td><div align='center'><input type='checkbox' name='med' id='med"+rs.getString(1)+"' onClick='ModificarPExistente("+rs.getString(1)+",12)' CHECKED/></div></td>");}
					
				
				//
				
		//		verifica porq sale si en la ultima opcion a rn en el campo q debe traer los estantes
				
			/*	rs7=mp.ObtenerRn(rs.getString(17));
				try {
					while(rs7.next()){
					if(rs7.getString(1).equals("0")){out.print("<option value="+rs7.getString(1)+">No</option>");}
					if(rs7.getString(1).equals("1")){out.print("<option value="+rs7.getString(1)+">Si</option>");}
					}
					rs7.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}*/
				
			}
							
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		
		
		/*	out.print("<table width='100%' class='style6'>");
			out.print("<tr><td colspan='5'><div align='center' class='style11' id='cabecera2'>Seleccione Una Opcion </div></td></tr>");
			out.print("<tr><td><label><input name='radiobutton' type='radio' value='radiobutton' id='1' onclick='Radios()'/>Programas</label></td><td><label><input name='radiobutton' type='radio' value='radiobutton' id='2' onclick='Radios()' />Servicios</label></td></tr><tr><td colspan='5'><div id='Opcion'></div></td></tr></table>");
			*/
		}	
		
		
		
		///////////////////////
		if(va.equals("autoinv")){
			
			try {
				//System.out.print("ESSSTOO."+xx);
				rs =mp.listarArticulos(texto,xx);
				String cadena ="";
				cadena="[";
				while(rs.next()){
					cadena = cadena+"'"+rs.getString(2)+"|"+rs.getString(1)+"|"+rs.getString(3)+"|"+rs.getString(4)+"|"+rs.getString(5)+"|"+rs.getString(6)+"'";
                	cadena = cadena +",";	
				}
				cadena = cadena+"]";
				//System.out.print("ESSSTOO."+cadena);
				res.getWriter().write(cadena);
				rs.getStatement().getConnection().close();
			} catch (Exception e) {
				e.getMessage();
				e.printStackTrace();
			}
		}
		
	
		//////////////////////
	
		
////////////////////////////**************ANEXOS TECNICOS*********************///////////////////////////
		
		if(va.equals("ATanterior")){



			//La idea es  consultar las facturas
			out.print("<table width='100%' border='0' align='center'><tr><td colspan='4' height='30' valign='top'><div align='center' class='style11' id='cabecera2'>Anexos tecnicos No. 2</div></td></tr></table>");
			
			//out.print("<table width='100%' border=0' class='style6' ><tr BGCOLOR='#D3D3E3' ><td colspan='4' valign='top'><i><div align='center'>Anexos Generados</div></i></td></tr><tr></tr><tr></tr><tr></tr></table>");
			out.print("<table width='100%' border=0' class='style6' ><tr><td><div id='xgenerar'>");
			out.print("<table width='100%' border=0' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='7%'><i><div align='center'>Admision</div></i></td><td width='10%'><i><div align='center'>Fecha/Hora</div></i></td><td width='40%'><i><div align='center'>Paciente</div></i></td><td width='36%'><i><div align='center'>Entidad/Regimen</div></i></td><td width='7%'><i><div align='center'>Accion</div></i></td></tr>");
			String [][] M = new String[400][4];  
			int t=0;
			
			rs2=mm.Anexos();
			try {
				while(rs2.next()){
					int sw=0;	
					///////////////////////////CONSULTAR SI LA ADMISION TIENE DX E HISTORIA CLINICA/////////////////
					rs=mm.ConsultarDx(rs2.getString(1));
					try {
						if(rs.next()){
							out.print("<tr onMouseOver=this.style.background='D3D3D3' onmouseout=this.style.background='white'  >");
							int sw2=0;
							///////////////////////////CONSULTAR EL ESTADO DEL ANEXO/////////////////
							rs3=mm.ConsultarAnexo2(rs2.getString(1));
							try {
								if(rs3.next()){
									//falta coger el 7 para mostrar la entidad y el 8 para que solo sean usuarios subsidiados con un if=subsidiado y listo
									out.print("<td><div align='left'>"+rs2.getString(1)+"</div></td><td><div align='left'>"+rs2.getString(2)+" / "+rs2.getString(3)+"</div></td><td><div align='left'>"+rs2.getString(4)+" "+rs2.getString(5)+" "+rs2.getString(6)+"</div></td><td><div align='left'>"+rs2.getString(7)+" / "+rs2.getString(8)+"</div></td><td><div align='left'><a href='#' onclick='ImprimirAnexo(&quot;"+rs2.getString(1)+"&quot;)'>Imprimir</div></td></tr>");
									sw2=1;	
								}
								rs3.getStatement().getConnection().close();
							} catch (SQLException e) {
								out.print("Error "+e);
								e.printStackTrace();
							}
							////////////////////////////////////
							if(sw2==0){
							out.print("<td><div align='left'>"+rs2.getString(1)+"</div></td><td><div align='left'>"+rs2.getString(2)+" / "+rs2.getString(3)+"</div></td><td><div align='left'>"+rs2.getString(4)+" "+rs2.getString(5)+" "+rs2.getString(6)+"</div></td><td><div align='left'>"+rs2.getString(7)+" / "+rs2.getString(8)+"</div></td><td><div align='left'><a href='#' onclick='RevisarAnexo(&quot;"+rs2.getString(1)+"&quot;)'>Revisar</div></td></tr>");
							}
							
							sw=1;	
						}
						rs.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					
					if(sw==0){
						M[t][0]=rs2.getString(1);
						M[t][1]=rs2.getString(2)+" / "+rs2.getString(3);
						M[t][2]=rs2.getString(4)+" "+rs2.getString(5)+" "+rs2.getString(6);
						M[t][3]="0";
					//	System.out.println("t:"+t+" M[t][0]: "+M[t][0]);
						t++;	
					//out.print("<tr><td><div align='left'>"+rs2.getString(1)+"</div></td><td><div align='left'>"+rs2.getString(2)+" / "+rs2.getString(3)+"</div></td><td><div align='left'>"+rs2.getString(4)+" "+rs2.getString(5)+" "+rs2.getString(6)+"</div></td><td><div align='left'>Guardar</div></td></tr>");
					}
					
					
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("</table>");
			out.print("</div></td></tr></table>");
			
			
			out.print("<table width='100%' border='0' align='center'><tr><td colspan='4' height='30' valign='top'><div align='center' class='style11' id='cabecera2'>Anexos tecnicos por generar</div></td></tr></table>");
			//out.print("<table width='100%' border=0' class='style6' ><tr BGCOLOR='#D3D3E3' ><td colspan='4' valign='top'><i><div align='center'>Anexos por Generar</div></i></td></tr><tr></tr><tr></tr><tr></tr></table>");
			out.print("<table width='100%' border=0' class='style6' ><tr><td><div id='generar'>");
			out.print("<table width='100%' border=0' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='10%'><i><div align='center'>Admision</div></i></td><td width='10%'><i><div align='center'>Fecha/Hora</div></i></td><td width='80%'><i><div align='center'>Paciente</div></i></td></tr>");
			
			for(int i=0;i<t;i++){
				out.print("<tr onMouseOver=this.style.background='D3D3D3' onmouseout=this.style.background='white'  >");
				out.print("<td><div align='left'>"+M[i][0]+"</div></td><td><div align='left'>"+M[i][1]+"</div></td><td><div align='left'>"+M[i][2]+"</div></td></tr>");
			}
			out.print("</table>");
			out.print("</div></td></tr></table>");
			//out.print("<tr></tr><tr><td width='15%'><div align='right'>Factura No:</div></td><td width='30%'><input name='txtFact' type='text' id='txtFact' size='39' maxlength='100' /></td>");
		
			
		}//fin AT
		
		

				if(va.equals("AT")){//NINOJESUS



			//La idea es  consultar las facturas
			out.print("<table width='100%' border='0' align='center'><tr><td colspan='4' height='30' valign='top'><div align='center' class='style11' id='cabecera2'>Anexos tecnicos No. 2</div></td></tr></table>");
			
			//out.print("<table width='100%' border=0' class='style6' ><tr BGCOLOR='#D3D3E3' ><td colspan='4' valign='top'><i><div align='center'>Anexos Generados</div></i></td></tr><tr></tr><tr></tr><tr></tr></table>");
			out.print("<table width='100%' border=0' class='style6' ><tr><td><div id='xgenerar'>");
			out.print("<table width='100%' border=0' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='7%'><i><div align='center'>Admision</div></i></td><td width='10%'><i><div align='center'>Fecha/Hora</div></i></td><td width='40%'><i><div align='center'>Paciente</div></i></td><td width='36%'><i><div align='center'>Entidad/Regimen</div></i></td><td width='7%'><i><div align='center'>Accion</div></i></td></tr>");
			String [][] M = new String[400][4];  
			int t=0;
			
			rs2=mm.Anexosninojesus();
			try {
				while(rs2.next()){
					//int sw=0;	
					///////////////////////////CONSULTAR SI LA ADMISION TIENE DX E HISTORIA CLINICA/////////////////
				/*	rs=mm.ConsultarDx(rs2.getString(1));
					try {
						if(rs.next()){*/
							out.print("<tr onMouseOver=this.style.background='D3D3D3' onmouseout=this.style.background='white'  >");
							int sw2=0;
							///////////////////////////CONSULTAR EL ESTADO DEL ANEXO/////////////////
							rs3=mm.ConsultarAnexo2(rs2.getString(1));
							try {
								if(rs3.next()){
									//falta coger el 7 para mostrar la entidad y el 8 para que solo sean usuarios subsidiados con un if=subsidiado y listo
									out.print("<td><div align='left'>"+rs2.getString(1)+"</div></td><td><div align='left'>"+rs2.getString(2)+" / "+rs2.getString(3)+"</div></td><td><div align='left'>"+rs2.getString(4)+" "+rs2.getString(5)+" "+rs2.getString(6)+"</div></td><td><div align='left'>"+rs2.getString(7)+" / "+rs2.getString(8)+"</div></td><td><div align='left'><a href='#' onclick='ImprimirAnexo(&quot;"+rs2.getString(1)+"&quot;)'>Imprimir</div></td></tr>");
									sw2=1;	
								}
								rs3.getStatement().close();
							} catch (SQLException e) {
								out.print("Error "+e);
								e.printStackTrace();
							}
							////////////////////////////////////
							if(sw2==0){
							out.print("<td><div align='left'>"+rs2.getString(1)+"</div></td><td><div align='left'>"+rs2.getString(2)+" / "+rs2.getString(3)+"</div></td><td><div align='left'>"+rs2.getString(4)+" "+rs2.getString(5)+" "+rs2.getString(6)+"</div></td><td><div align='left'>"+rs2.getString(7)+" / "+rs2.getString(8)+"</div></td><td><div align='left'><a href='#' onclick='RevisarAnexo(&quot;"+rs2.getString(1)+"&quot;)'>Revisar</div></td></tr>");
							}
							
							//sw=1;	
					/*	}
						rs.getStatement().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					*/
				/*	if(sw==0){
						M[t][0]=rs2.getString(1);
						M[t][1]=rs2.getString(2)+" / "+rs2.getString(3);
						M[t][2]=rs2.getString(4)+" "+rs2.getString(5)+" "+rs2.getString(6);
						M[t][3]="0";
					//	System.out.println("t:"+t+" M[t][0]: "+M[t][0]);
						t++;	
					//out.print("<tr><td><div align='left'>"+rs2.getString(1)+"</div></td><td><div align='left'>"+rs2.getString(2)+" / "+rs2.getString(3)+"</div></td><td><div align='left'>"+rs2.getString(4)+" "+rs2.getString(5)+" "+rs2.getString(6)+"</div></td><td><div align='left'>Guardar</div></td></tr>");
					}*/
					
					
				}
				rs2.getStatement().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("</table>");
			out.print("</div></td></tr></table>");
			
		
			
		}//fin ATNINO
		
		
		
		
		if(va.equals("AT1anterior")){
			
			String anex="";
			
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
			int hora, minutos, segundos;
			hora =calendario.get(Calendar.HOUR_OF_DAY);
			minutos = calendario.get(Calendar.MINUTE);
			segundos = calendario.get(Calendar.SECOND);
			String hra= hora+":"+minutos+":"+segundos;		
			
			out.print("<table width='100%' border='0' align='center'><tr><td colspan='6' height='30' valign='top'><div align='center' class='style11' id='cabecera2'>Anexo tecnico No. 2 Informe de la atencion inicia de urgencias</div></td></tr>");
			out.print("<tr><td>Numero Atencion:</td><td><input type='text' id='adm' size='39' value='"+adm01+"' readonly='readonly'/></td><td>Fecha:</td><td><input type='text' id='FI' size='39'  value='"+fechacj+"'  onKeyup='masca(this,patron,true,0,0,0)' onblur='vfi()';/></td><td>Hora:</td><td><input type='text' id='hramy' size='39'  value='"+hra+"'/></td></tr>");
			out.print("<tr></tr><tr BGCOLOR='#D3D3D3' ><td colspan='6'><i><div align='center'>Datos de la Entidad</div></i></td></tr>");
			
			
			rs3=mm.ConsultarDatosAnexo2(adm01);
			try {
			if(rs3.next()){
				
			out.print("<tr><td>Entidad:</td><td><input type='text' id='ent' size='39'  value='"+rs3.getString(16)+"' readonly='readonly'/></td><td>Codigo:</td><td><input type='text' id='codent' size='39' value='"+rs3.getString(17)+"' readonly='readonly'/></td><td>Regimen</td><td><input type='text' id='regent' size='39' value='"+rs3.getString(21)+"' readonly='readonly'/></td></tr>");
			out.print("<tr></tr><tr BGCOLOR='#D3D3D3' ><td colspan='6'><i><div align='center'>Datos del Usuario</div></i></td></tr>");
			out.print("<tr><td>Usuario:</td><td colspan='5'><input type='text' id='usu' size='100%' value='"+rs3.getString(6)+" "+rs3.getString(7)+" "+rs3.getString(5)+"' readonly='readonly' /></td></tr>");
			out.print("<tr><td>Tipo Documento:</td><td><input type='text' id='tdoc' size='39' value='"+rs3.getString(8)+"' readonly='readonly'/></td><td>Documento:</td><td><input type='text' id='doc' size='39' value='"+rs3.getString(9)+"' readonly='readonly'/></td><td>Fecha naciemiento:</td><td><input type='text' id='fecnac' size='39' value='"+rs3.getString(14)+"' readonly='readonly'/></td></tr>");
			out.print("<tr><td>Direccion:</td><td><input type='text' id='dir' size='39' value='"+rs3.getString(10)+"' readonly='readonly'/></td><td>Departamento:</td><td><input type='text' id='dpto' size='39' value='"+rs3.getString(19)+"' readonly='readonly'/></td><td>Municipio:</td><td><input type='text' id='munic' size='39' value='"+rs3.getString(18)+"' readonly='readonly'/></td></tr>");
			out.print("<tr></tr><tr BGCOLOR='#D3D3D3' ><td colspan='6'><i><div align='center'>Informacion de la atencion</div></i></td></tr>");
					
			out.print("<tr><td>Origen de la atenci�n:</td><td><select  style='width:269px' name='oatenc' id='oatenc' >");
			rs=mm.ConsultarOrigendeAtencion();
			try {
			while(rs.next()){
			out.print("<option title='"+rs.getString(2)+"' value='"+rs.getString(2)+"'>"+rs.getString(2)+"</option>");
			}
			rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("</select ></td>");
			
			out.print("<td>Clasificaci�n de triage:</td><td><select style='width:269px' id='tri'>" +
					"<option value='2'>2. Amarillo</option>" +
					"<option value='1'>1. Rojo</option>" +
					"<option value='3'>3. Verde</option>" +
					"</select></td>");
			
			out.print("<td>Viene remitido:</td><td><select style='width:269px' id='vr' onChange='remitido()'>" +
					"<option value='0'>No</option>" +
					"<option value='1'>Si</option>" +
					"</td></tr>");
			
			out.print("<tr><td colspan='6'><div id='remitediv'></div></td></tr>");
			
			
			out.print("<tr></tr><tr BGCOLOR='#D3D3D3' ><td colspan='6'><i><div align='center'>Enfermedad actual</div></i></td></tr>");
					
			rs=mm.ConsultarEnfermedadActual(adm01);
			try {
			if(rs.next()){
				out.print("<tr><td>Enfermedad actual:</td><td colspan='5'><textarea id='eac' cols='92' rows='5' >"+rs.getString(1)+"</textarea></td></tr>");
			}else{
				out.print("<tr><td>Enfermedad actual:</td><td colspan='5'><textarea id='eac' cols='92' rows='5' ></textarea></td></tr>");
			}
			rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
					
			rs=mm.ConsultarDxdeIngreso(adm01);
			int dxr=0;
			try {
			while(rs.next()){
			dxr++;
			out.print("<tr><td>Diagnostico relacionado "+dxr+":</td><td><input type='text' id='dxr"+dxr+"' size='39' value='"+rs.getString(1)+"' readonly='readonly' /></td><td>Descripci�n:</td><td><input type='text' id='dsrd"+dxr+"' size='39' value='"+rs.getString(2)+"' readonly='readonly' /></td>");
			
			}
			rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.println("<input  id='dxr' type='hidden' value="+dxr+">");
			
			out.print("<td>Destino del paciente:</td><td><select style='width:269px' id='dpac'>" +
					"<option value='1'>Observacion</option>" +
					"<option value='0'>Domiclio</option>" +
					"<option value='2'>Internacion</option>" +
					"<option value='3'>Remision</option>" +
					"<option value='4'>Contrarremision</option>" +
					"<option value='5'>Otro</option>" +
					"</select></td></tr>");
							
			out.print("</table>");
			
			
			}
			rs3.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			
		out.print("<table width='100%' border='0' class='style6' ><tr></tr><tr></tr><tr><td colspan='4'><div align='center'><input type='button' id='btn' value='     Guardar Anexo    ' onclick='GuardarAnexo("+adm01+")' /></div></td></tr></table>");
					
		}//fin AT1
		
		

		if(va.equals("AT1")){
			
			String anex="";
			
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
			int hora, minutos, segundos;
			hora =calendario.get(Calendar.HOUR_OF_DAY);
			minutos = calendario.get(Calendar.MINUTE);
			segundos = calendario.get(Calendar.SECOND);
			String hra= hora+":"+minutos+":"+segundos;		
			
			out.print("<table width='100%' border='0' align='center'><tr><td colspan='6' height='30' valign='top'><div align='center' class='style11' id='cabecera2'>Anexo tecnico No. 2 Informe de la atencion inicia de urgencias</div></td></tr>");
			out.print("<tr><td>Numero Atencion:</td><td><input type='text' id='adm' size='39' value='"+adm01+"' readonly='readonly'/></td><td>Fecha:</td><td><input type='text' id='FI' size='39'  value='"+fechacj+"'  onKeyup='masca(this,patron,true,0,0,0)' onblur='vfi()';/></td><td>Hora:</td><td><input type='text' id='hramy' size='39'  value='"+hra+"'/></td></tr>");
			out.print("<tr></tr><tr BGCOLOR='#D3D3D3' ><td colspan='6'><i><div align='center'>Datos de la Entidad</div></i></td></tr>");
			
			
			rs3=mm.ConsultarDatosAnexo2(adm01);
			try {
			if(rs3.next()){
				System.out.println("YA ENTRO AL INFORME "+adm01+" - "+rs3.getString(6));
			out.print("<tr><td>Entidad:</td><td><input type='text' id='ent' size='39'  value='"+rs3.getString(16)+"' readonly='readonly'/></td><td>Codigo:</td><td><input type='text' id='codent' size='39' value='"+rs3.getString(17)+"' readonly='readonly'/></td><td>Regimen</td><td><input type='text' id='regent' size='39' value='"+rs3.getString(21)+"' readonly='readonly'/></td></tr>");
			out.print("<tr></tr><tr BGCOLOR='#D3D3D3' ><td colspan='6'><i><div align='center'>Datos del Usuario</div></i></td></tr>");
			out.print("<tr><td>Usuario:</td><td colspan='5'><input type='text' id='usu' size='100%' value='"+rs3.getString(6)+" "+rs3.getString(7)+" "+rs3.getString(5)+"' readonly='readonly' /></td></tr>");
			out.print("<tr><td>Tipo Documento:</td><td><input type='text' id='tdoc' size='39' value='"+rs3.getString(8)+"' readonly='readonly'/></td><td>Documento:</td><td><input type='text' id='doc' size='39' value='"+rs3.getString(9)+"' readonly='readonly'/></td><td>Fecha naciemiento:</td><td><input type='text' id='fecnac' size='39' value='"+rs3.getString(14)+"' readonly='readonly'/></td></tr>");
			out.print("<tr><td>Direccion:</td><td><input type='text' id='dir' size='39' value='"+rs3.getString(10)+"' readonly='readonly'/></td><td>Departamento:</td><td><input type='text' id='dpto' size='39' value='"+rs3.getString(19)+"' readonly='readonly'/></td><td>Municipio:</td><td><input type='text' id='munic' size='39' value='"+rs3.getString(18)+"' readonly='readonly'/></td></tr>");
			out.print("<tr></tr><tr BGCOLOR='#D3D3D3' ><td colspan='6'><i><div align='center'>Informacion de la atencion</div></i></td></tr>");
					
			out.print("<tr><td>Origen de la atenci�n:</td><td><select  style='width:269px' name='oatenc' id='Ent' >");
			rs=mm.ConsultarOrigendeAtencion();
			System.out.println("YA ENTRO A ConsultarOrigendeAtencion "+adm01+" - "+rs3.getString(6));
			try {
			while(rs.next()){
			out.print("<option title='"+rs.getString(2)+"' value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
			}
			rs.getStatement().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("</select ></td>");
			
			out.print("<td>Clasificaci�n de triage:</td><td><select style='width:269px' id='tri'>" +
					"<option value='2'>2. Amarillo</option>" +
					"<option value='1'>1. Rojo</option>" +
					"<option value='3'>3. Verde</option>" +
					"</select></td>");
			
			out.print("<td>Viene remitido:</td><td><select style='width:269px' id='vr' onChange='remitido()'>" +
					"<option value='0'>No</option>" +
					"<option value='1'>Si</option>" +
					"</td></tr>");
			
			out.print("<tr><td colspan='6'><div id='remitediv'></div></td></tr>");
			
			
			out.print("<tr></tr><tr BGCOLOR='#D3D3D3' ><td colspan='6'><i><div align='center'>Enfermedad actual</div></i></td></tr>");
					
			//rs=mm.ConsultarEnfermedadActual(adm01);
			//System.out.println("xxxxxxxxxx YA ENTRO A ConsultarEnfermedadActual "+adm01+" - "+rs3.getString(6));
			
			//try {
			//if(rs.next()){
			out.print("<tr><td>Enfermedad actual:</td><td colspan='5'><textarea id='eac' cols='92' rows='5' ></textarea></td></tr>");
			//}
			//rs.getStatement().close();
			//} catch (SQLException e) {
			//	out.print("Error "+e);
			//	e.printStackTrace();
			//}
			
					
			//rs=mm.ConsultarDxdeIngreso(adm01);
			//System.out.println("xxxxxxxxxx YA ENTRO A ConsultarDxdeIngreso "+adm01+" - "+rs3.getString(6));
			
		//	int dxr=0;
		//	try {
		//	while(rs.next()){
			
			//	if(dxr<4){
		//	dxr++;
			out.print("<tr><td>Diagnostico relacionado :</td><td><input type='text' id='dxr1' size='39'  /></td><td>Descripci�n:</td><td><input type='text' id='dsrd1' size='39' /></td>");
			//	}
		//	}
		//	rs.getStatement().close();
		//	} catch (SQLException e) {
			//	out.print("Error "+e);
			//	e.printStackTrace();
			//}
			out.println("<input  id='dxr' type='hidden' value='1'>");
			
			out.print("<td>Destino del paciente:</td><td><select style='width:269px' id='dpac'>" +
					"<option value='1'>Observacion</option>" +
					"<option value='0'>Domiclio</option>" +
					"<option value='2'>Internacion</option>" +
					"<option value='3'>Remision</option>" +
					"<option value='4'>Contrarremision</option>" +
					"<option value='5'>Otro</option>" +
					"</select></td></tr>");
							
			out.print("</table>");
			
			
			}
			rs3.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			
		out.print("<table width='100%' border='0' class='style6' ><tr></tr><tr></tr><tr><td colspan='4'><div align='center'><input type='button' id='btn' value='     Guardar Anexo    ' onclick='GuardarAnexo("+adm01+")' /></div></td></tr></table>");
					
		}//fin AT1 n�o jesus
		
		
		

		if(va.equals("AT2")){	
			out.print("<table width='100%' border='0' >");
			out.print("<tr></tr><tr BGCOLOR='#D3D3D3' ><td colspan='6'><i><div align='center'>Datos de la remisi�n</div></i></td></tr>");
			out.print("<tr><td>Entidad:</td><td><input type='text' id='entr' size='39'  /></td><td>Codigo:</td><td><input type='text' id='coder' size='39' /></td><td></td><td></td></tr>");
			out.print("<tr><td>Departamento:</td><td><select  style='width:269px' id='anexdep' onChange='anexomun()'><option value='0'>Seleccione</option>");
			rs = mm.ConsultarDepartamentos();
			try {
				while(rs.next()){
					out.print("<option title='"+rs.getString(2)+"' value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("</select></td>");
		
			
			
			out.print("<td>Municipio:</td><td><div id='muni'><select  style='width:269px' id='sccostoc' ><option value='0'>Seleccione</option></div></td><td></td><td></td></tr>");
			out.print("</table>");
			
		}//fin AT2
		
		
if(va.equals("AT3")){	
			
			out.print("<select  style='width:269px' id='sccostoc' >");
			rs = mm.ConsultarMunicipios(subt);
			try {
				while(rs.next()){
					out.print("<option title='"+rs.getString(2)+"' value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("</select>");
		}//fin AT3
		
		


		
		if(va.equals("AT4")){	
			
			String aa=req.getParameter("aa");
			String ab=req.getParameter("ab");
			String ac=req.getParameter("ac");
			String ad=req.getParameter("ad");
			String ae=req.getParameter("ae");
			String af=req.getParameter("af");
			String ag=req.getParameter("ag");
			String ah=req.getParameter("ah");
			String ai=req.getParameter("ai");
			String aj=req.getParameter("aj");
			String ak=req.getParameter("ak");
			String al=req.getParameter("al");
			String am=req.getParameter("am");
			
			String ao=req.getParameter("ao");
			String aod=req.getParameter("aod");
			String ap=req.getParameter("ap");
			String apd=req.getParameter("apd");
			String aq=req.getParameter("aq");
			String aqd=req.getParameter("aqd");
			String ar=req.getParameter("ar");
			String ard=req.getParameter("ard");
		
						
			String rrs1="";
			String rrs2="";
			String rrs3="";
			String rrs4="";
			String rrs5="";
			String rrs6="";
			String rrs7="";
			String rrs8="";
			String rrs9="";
			String rrs10="";
			String rrs11="";
			
			rs = mm.ConsultarDatosEmpresa();
			try {
				if(rs.next()){
					rrs1=rs.getString(1);
					rrs2=rs.getString(2);
					rrs3=rs.getString(3);
					rrs4=rs.getString(4);
					rrs5=rs.getString(5);
					rrs6=rs.getString(6);
					rrs7=rs.getString(7);
					rrs8=rs.getString(8);
					rrs9=rs.getString(9);	
					rrs10=rs.getString(10);	
					rrs11=rs.getString(11);
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			
			String dv8=aa.substring(0, 2);
			String mv8=aa.substring(3, 5);
			String av8=aa.substring(6, 10);
			String fv8=av8+"-"+mv8+"-"+dv8;
			
			//if(an.equals("1")){}
			
			rs3=mm.ConsultarDatosAnexo2(adm01);
			try {
			if(rs3.next()){
				String[] nombre = rs3.getString(5).split(" "); 
				
				String n1=nombre[0];
				String n2="";
				if(nombre.length==2){
					n2=nombre[1];
				}else{
					for(int z=1;z<nombre.length;z++){
						n2=n2+nombre[z];
					}
				}
				
				mm.CrearAnexoTecnico2(adm01,"2",fv8,ab,rrs1,"NIT",rrs2,rrs3,rrs4,"035",rrs5,rrs7,rrs6,rrs9,rrs8,rs3.getString(16),rs3.getString(17),rs3.getString(6),rs3.getString(7),n1,n2,rs3.getString(8),rs3.getString(9),rs3.getString(14),rs3.getString(10),rs3.getString(20),rs3.getString(19),rs3.getString(13),rs3.getString(18),rs3.getString(11),rs3.getString(12),rs3.getString(21),ac,"","","",al,rs3.getString(22)+" "+rs3.getString(23),rs3.getString(24),"035",rrs10,rrs11,rs3.getString(1),rs3.getString(2),ad,ae,af,ag,ah,ai,aj,ak,am,"1",ao,aod,ap,apd,aq,aqd,ar,ard);
				
			}
			rs3.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}

		
		}//fin AT4


	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		
////////////////////////////**************ANEXOS TECNICOS N 3*********************///////////////////////////
		
		if(va.equals("ATT")){
			
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
			int hora, minutos, segundos;
			hora =calendario.get(Calendar.HOUR_OF_DAY);
			minutos = calendario.get(Calendar.MINUTE);
			segundos = calendario.get(Calendar.SECOND);
			String hra= hora+":"+minutos+":"+segundos;
			
			String rrs1="";
			String rrs2="";
			String rrs3="";
			String rrs4="";
			String rrs5="";
			String rrs6="";
			String rrs7="";
			String rrs8="";
			String rrs9="";
			String rrs10="";
			String rrs11="";
			
			rs = mm.ConsultarDatosEmpresa();
			try {
				if(rs.next()){
					rrs1=rs.getString(1);
					rrs2=rs.getString(2);
					rrs3=rs.getString(3);
					rrs4=rs.getString(4);
					rrs5=rs.getString(5);
					rrs6=rs.getString(6);
					rrs7=rs.getString(7);
					rrs8=rs.getString(8);
					rrs9=rs.getString(9);	
					rrs10=rs.getString(10);	
					rrs11=rs.getString(11);
				}
				rs.getStatement().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
		
			String oa="";
			rs=mm.ConsultarOrigendeAtencion();
			try {
			if(rs.next()){
			oa=rs.getString(2);
			}
			rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
		int sw=0;
		rs=mm.ConsultarCodAnexo3(adm01);
		try {
		if(rs.next()){
		sw=1;
		}
		rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		
		String u="";
		String s="";
		String ca="";
		String cama=req.getParameter("cama");
		rs=mm.ConsultarUbicacion(cama);
		try {
		if(rs.next()){
			u=rs.getString(1);
			s=rs.getString(2);
			ca=rs.getString(3);
		}
		rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		
		String ea="";
		rs=mm.ConsultarEnfermedadActualA3(adm01);
		try {
		while(rs.next()){
			ea=ea+(rs.getString(1)+" \n");
		}
		out.print("</textarea></td></tr>");
		rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		
		String ao="";
		String aod="";
		String ap="";
		String apd="";
		String aq="";
		String aqd="";
		String ar="";
		String ard="";
		
		rs=mm.ConsultarDxdeEgreso(adm01);
		int cdxr=0;
		try {
		while(rs.next()){
			if(cdxr==0){
				ao=rs.getString(1);
				aod=rs.getString(2);	
			}
			if(cdxr==1){
				ap=rs.getString(1);
				apd=rs.getString(2);	
			}
			if(cdxr==2){
				aq=rs.getString(1);
				aqd=rs.getString(2);	
			}
			if(cdxr==3){
				ar=rs.getString(1);
				ard=rs.getString(2);	
			}
			
			cdxr++;	
		}
		rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		
		
		if(sw==0){//creamos el anexo 3
		
			rs3=mm.ConsultarDatosAnexo2(adm01);
			try {
			if(rs3.next()){
				String[] nombre = rs3.getString(5).split(" "); 
				
				String n1=nombre[0];
				String n2="";
				if(nombre.length==2){
					n2=nombre[1];
				}else{
					for(int z=1;z<nombre.length;z++){
						n2=n2+nombre[z];
					}
				}
				
				mm.CrearAnexoTecnico2(adm01,"3",fechacjmysql,hra,rrs1,"NIT",rrs2,rrs3,rrs4,"035",rrs5,rrs7,rrs6,rrs9,rrs8,rs3.getString(16),rs3.getString(17),rs3.getString(6),rs3.getString(7),n1,n2,rs3.getString(8),rs3.getString(9),rs3.getString(14),rs3.getString(10),rs3.getString(20),rs3.getString(19),rs3.getString(13),rs3.getString(18),rs3.getString(11),rs3.getString(12),rs3.getString(21),oa,u,s,ca,ea,rs3.getString(22)+" "+rs3.getString(23),rs3.getString(24),"035",rrs10,rrs11,"1111-01-01","01:00:00","","","","","","","","","","0",ao,aod,ap,apd,aq,aqd,ar,ard);
				
			}
			rs3.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
		}

////////////////////////////////////////////////

		
		String anex="";
			
						
			out.print("<table width='100%' border='0' align='center'><tr><td colspan='6' height='30' valign='top'><div align='center' class='style11' id='cabecera2'>Anexo tecnico No. 2 Informe de la atencion inicia de urgencias</div></td></tr>");
			out.print("<tr><td>Numero Atencion:</td><td><input type='text' id='adm' size='39' value='"+adm01+"' readonly='readonly'/></td><td>Fecha:</td><td><input type='text' id='FI' size='39'  value='"+fechacj+"'  onKeyup='masca(this,patron,true,0,0,0)' onblur='vfi()';/></td><td>Hora:</td><td><input type='text' id='hramy' size='39'  value='"+hra+"'/></td></tr>");
			out.print("<tr></tr><tr BGCOLOR='#D3D3D3' ><td colspan='6'><i><div align='center'>Datos de la Entidad</div></i></td></tr>");
			
			
			rs3=mm.ConsultarAnexo3(adm01);
			try {
			if(rs3.next()){
			out.print("<tr><td>Entidad:</td><td><input type='text' id='ent' size='39'  value='"+rs3.getString(17)+"' readonly='readonly'/></td><td>Codigo:</td><td><input type='text' id='codent' size='39' value='"+rs3.getString(18)+"' readonly='readonly'/></td><td>Regimen</td><td><input type='text' id='regent' size='39' value='"+rs3.getString(33)+"' readonly='readonly'/></td></tr>");
			out.print("<tr></tr><tr BGCOLOR='#D3D3D3' ><td colspan='6'><i><div align='center'>Datos del Usuario</div></i></td></tr>");
			out.print("<tr><td>Usuario:</td><td colspan='5'><input type='text' id='usu' size='100%' value='"+rs3.getString(19)+" "+rs3.getString(20)+" "+rs3.getString(21)+" "+rs3.getString(22)+"' readonly='readonly' /></td></tr>");
			out.print("<tr><td>Tipo Documento:</td><td><input type='text' id='tdoc' size='39' value='"+rs3.getString(23)+"' readonly='readonly'/></td><td>Documento:</td><td><input type='text' id='doc' size='39' value='"+rs3.getString(24)+"' readonly='readonly'/></td><td>Fecha naciemiento:</td><td><input type='text' id='fecnac' size='39' value='"+rs3.getString(25)+"' readonly='readonly'/></td></tr>");
			out.print("<tr><td>Direccion:</td><td><input type='text' id='dir' size='39' value='"+rs3.getString(26)+"' readonly='readonly'/></td><td>Departamento:</td><td><input type='text' id='dpto' size='39' value='"+rs3.getString(28)+"' readonly='readonly'/></td><td>Municipio:</td><td><input type='text' id='munic' size='39' value='"+rs3.getString(30)+"' readonly='readonly'/></td></tr>");
			out.print("<tr></tr><tr BGCOLOR='#D3D3D3' ><td colspan='6'><i><div align='center'>Informacion de la atenci�n y servicios solicitados</div></i></td></tr>");
					
			out.print("<tr><td>Origen de la atenci�n:</td><td><select  style='width:269px' name='oatenc' id='Ent' >");
			rs=mm.ConsultarOrigendeAtencion();
			System.out.println("YA ENTRO A ConsultarOrigendeAtencion "+adm01+" - "+rs3.getString(6));
			try {
			while(rs.next()){
			out.print("<option title='"+rs.getString(2)+"' value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
			}
			rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("</select ></td>");
			
			out.print("<td>Tipo de servicio</td><td><select style='width:269px' id='tri'>" +
					"<option title='Posterior a la atencion inicial de urgencias' value='1'>Posterior a la atencion inicial de urgencias</option>" +
					"<option title='Servicios electivos' value='1'>Servicios electivos</option>" +
					"</select></td>");
			
			out.print("<td>Prioridad de la atenci�n</td><td><select style='width:269px' id='vr'>" +
					"<option value='0'>Prioritaria</option>" +
					"<option value='1'>No prioritaria</option>" +
					"</td></tr>");
			
			
			//out.print("<tr><td colspan='6'><div id='remitediv'></div></td></tr>");
			
			rs=mm.ConsultarUbicacion(cama);
			try {
			if(rs.next()){
				out.print("<tr></tr><tr></tr><tr></tr><tr><td colspan='6'><i>Ubicacion del paciente al momento de la solicitud de autorizaci�n:<i></td></tr>");
				
				out.print("<tr></tr><tr><td>Ubicaci�n actual</td><td><input type='text' id='tdoc' size='39' value='"+rs.getString(1)+"' readonly='readonly'/></td>");
				out.print("<td>Servicio</td><td><input type='text' id='tdoc' size='39' value='"+rs.getString(2)+"' readonly='readonly'/></td>");
				out.print("<td>Cama</td><td><input type='text' id='tdoc' size='39' value='"+rs.getString(3)+"' readonly='readonly'/></td></tr>");
			}
			rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
		
		
			
			
			out.print("<tr></tr><tr BGCOLOR='#D3D3D3' ><td colspan='6'><i><div align='center'>Manejo integral del paciente</div></i></td></tr>");
			out.print("<tr></tr><tr><td>Seg�n guia de (especialidad): </td><td><select  style='width:269px'  id='espe' >");
			
			rs=mm.ConsultarEspecialidad();
			try {
			while(rs.next()){
			out.print("<option title='"+rs.getString(2)+"' value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
			}
			rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("</select ></td>");
			out.print("<td></td><td></td>");
			out.print("<td></td><td></td></tr>");
			
		
			out.print("<tr></tr><tr><td>Codigo CUPS</td><td><input type='text' id='ref0' size='39'  onkeyup='autocompletaAnexo3(0,0,0)'/></td>");
			out.print("<td>Procedimiento:</td><td><input type='text' id='ref1' size='39' onkeyup='autocompletaAnexo3(1,1,1)' /><input  id='refh' type='hidden'></td>");
			out.print("<td>Cantidad</td><td><input type='text' id='cant' size='19'  readonly='readonly'/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type='button' id='btn' value='Adicionar' onclick='AdicionarCUPS("+rs3.getString(1)+")' /></td></tr>");
			out.print("<tr><td></td><td><div id='sugerencias3210'></div></td>");
			out.print("<td></td><td><div id='sugerencias3211'></div></td>");
			out.print("<td></td><td></td></tr>");
			
			
			out.print("<tr></tr><tr></tr><tr></tr><tr><td colspan='6'><i>Codigos CUPS Ingresados:<i></td></tr>");
			
			out.print("<tr><td colspan='6' ><div id='cups'>");
			rs=mm.ConsultarCUPSAnexos(rs3.getString(1));
			int item=0;
			try {
			while(rs.next()){
			item++;
			out.print(item+". &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+rs.getString(3)+" &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+rs.getString(4)+"&nbsp;&nbsp;&nbsp;&nbsp;  <a href='#' onclick='EliminarCUPS("+rs.getString(1)+","+rs3.getString(1)+")'>Eliminar</a> <br>");
			}
			rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("</div></td></tr>");
			
			
			out.print("<tr></tr><tr BGCOLOR='#D3D3D3' ><td colspan='6'><i><div align='center'>Enfermedad actual</div></i></td></tr>");
					
			rs=mm.ConsultarEnfermedadActualA3(adm01);
			System.out.println("xxxxxxxxxx YA ENTRO A ConsultarEnfermedadActual "+adm01+" - "+rs3.getString(6));
			
			try {
				out.print("<tr><td>Justificaci�n Cl�nica:</td><td colspan='5'><textarea id='eac' cols='92' rows='5' >");
				
			while(rs.next()){
				out.print(rs.getString(1)+" \n");
			}
			out.print("</textarea></td></tr>");
			rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
					
			rs=mm.ConsultarDxdeEgreso(adm01);
			//System.out.println("xxxxxxxxxx YA ENTRO A ConsultarDxdeIngreso "+adm01+" - "+rs3.getString(6));
			
			int dxr=0;
			try {
			while(rs.next()){
				
			if(dxr<2){
			if(dxr==0){
			out.print("<tr><td>Diagnostico Principal:</td><td><input type='text' id='dxr"+dxr+"' size='39' value='"+rs.getString(1)+"' readonly='readonly' /></td><td>Descripci�n:</td><td><input type='text' id='dsrd"+dxr+"' size='39' value='"+rs.getString(2)+"' readonly='readonly' /></td>");
			}else{
			out.print("<tr><td>Diagnostico relacionado "+dxr+":</td><td><input type='text' id='dxr"+dxr+"' size='39' value='"+rs.getString(1)+"' readonly='readonly' /></td><td>Descripci�n:</td><td><input type='text' id='dsrd"+dxr+"' size='39' value='"+rs.getString(2)+"' readonly='readonly' /></td>");
			}
			dxr++;
			}
			}
			
			rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.println("<input  id='dxr' type='hidden' value="+dxr+">");
			
			out.print("<td></td></tr>");
							
			out.print("</table>");
			
			out.print("<table width='100%' border='0' class='style6' ><tr></tr><tr></tr><tr><td colspan='4'><div align='center'><input type='button' id='btn' value='     Guardar Anexo    ' onclick='GuardarAnexo3("+rs3.getString(1)+","+adm01+")' /></div></td></tr></table>");

			}
			rs3.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			
		
						
						
		}//fin ATT
		
		
		
		
		if(va.equals("ATT1")){
		
			String aa=req.getParameter("aa");
			String ab=req.getParameter("ab");
			String ac=req.getParameter("ac");
			String ad=req.getParameter("ad");
			
			mm.InsertarCUPS(aa,ab,ac,ad,adm01);
		
		rs=mm.ConsultarCUPSAnexos(adm01);
		int item=0;
		try {
		while(rs.next()){
		item++;
		out.print(item+". &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+rs.getString(3)+" &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+rs.getString(4)+"  &nbsp;&nbsp;&nbsp;&nbsp;  <a href='#' onclick='EliminarCUPS("+rs.getString(1)+","+adm01+")'>Eliminar</a><br>");
		}
		rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		
		
		}
		
		
		
		if(va.equals("ATT2")){	
				
			String aa=req.getParameter("aa");
			String ab=req.getParameter("ab");
			String ac=req.getParameter("ac");
			String ad=req.getParameter("ad");
			String ae=req.getParameter("ae");
			String ai=req.getParameter("ai");
			String al=req.getParameter("al");
							
			String dv8=aa.substring(0, 2);
			String mv8=aa.substring(3, 5);
			String av8=aa.substring(6, 10);
			String fv8=av8+"-"+mv8+"-"+dv8;
			
			mm.ActualizarAnexo3(fv8,ab,ac,ad,ae,ai,al,adm01);
			
		
		}//fin ATT2
		//hay q hacerf el improimir formato 3 y el jasper
		
		if(va.equals("ATT3")){
			
			String aa=req.getParameter("aa");
			mm.EliminarCUPS(aa);
		
		rs=mm.ConsultarCUPSAnexos(adm01);
		int item=0;
		try {
		while(rs.next()){
		item++;
		out.print(item+". &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+rs.getString(3)+" &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+rs.getString(4)+"  &nbsp;&nbsp;&nbsp;&nbsp;  <a href='#' onclick='EliminarCUPS("+rs.getString(1)+","+adm01+")'>Eliminar</a> <br>");
		}
		rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		
		
		}
		
		
		if(va.equals("ATT4")){
			//out.print("<table width='100%' class='style6'><tr><td colspan='5'><div align='center' class='style11' id='cabecera2'>Criterios de busqueda </div></td></tr>");
			out.print("<table width='100%' ><tr BGCOLOR='#D3D3D3' ><td width='8%'><i>Resultados de la Busqueda</i></td></tr>");  //
			try {
				rs =mm.ConsultarCodigosAnexo3(adm01);
				while(rs.next()){
					out.print("<tr><td><a href='#' onclick='ImprimirAnexo3("+rs.getString(1)+")'>Anexo No: "+rs.getString(1)+"</a></td></tr>");	
					
				}
				rs.getStatement().getConnection().close();
			} catch (Exception e) {
				e.getMessage();
				e.printStackTrace();
			}
			
			
		}
		///////////////////////
		if(va.equals("autocups")){
			
			try {
				//System.out.print("ESSSTOO."+xx);
				rs =mm.ConsultarCUPS(texto,xx);
				String cadena ="";
				cadena="[";
				while(rs.next()){
					cadena = cadena+"'"+rs.getString(1)+"|"+rs.getString(2)+"|"+rs.getString(3)+"'";
                	cadena = cadena +",";	
				}
				cadena = cadena+"]";
				//System.out.print("ESSSTOO."+cadena);
				res.getWriter().write(cadena);
				rs.getStatement().getConnection().close();
			} catch (Exception e) {
				e.getMessage();
				e.printStackTrace();
			}
		}
		
		/////////////////////////////////////////////////////////////////////////////
		
		///////////////////////////////////////////////////////////
		
		


		///////////////////////////////////////////
	}//fin doPost

}//fin controlador
