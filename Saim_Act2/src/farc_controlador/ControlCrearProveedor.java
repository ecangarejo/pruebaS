package farc_controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import farc_metodo.MetodoCrearProveedor;

/**
 * Servlet implementation class ControlCrearProveedor
 */
public class ControlCrearProveedor extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String va = request.getParameter("valor");
		PrintWriter out=response.getWriter();
		MetodoCrearProveedor mcp = new MetodoCrearProveedor();
				
		String nit=request.getParameter("nit");//
		String pais=request.getParameter("pais");//
		String ciudad=request.getParameter("ciudad");//
		String muni=request.getParameter("muni");//
		String telefono=request.getParameter("telefono");//	
		String fax=request.getParameter("fax");	//
		String email=request.getParameter("email");	//
		String contacto=request.getParameter("contacto");	
		String clase=request.getParameter("clase");	//
		String razon=request.getParameter("razon");	//
		String direccion=request.getParameter("direccion");//
		String observacion=request.getParameter("observacion");//
		String nombreTipo=request.getParameter("nombreTipo");//
		String Operacion=request.getParameter("Operacion");//
		String ni=request.getParameter("ni");	//
		String codpp=request.getParameter("codpp");	//
		
		
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		ResultSet rs3=null;
		ResultSet rs4=null;
		ResultSet rs5=null;
		ResultSet rs6=null;
		


		if(va.equals("GFarmacologico")){			
			
			out.print("<table width='100%' class='style6'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Crear Grupo Farmacologico</div></td></tr>");
			out.print("<tr><td width='12%'>Descripción</td><td width='30%'><label><input name='txtDescripcion' type='text' id='txtDescripcion' size='40'  /></label></td><td width='13%'>Identificador de Riesgo</td><td width='45%'><label><input name='txtIRiesgo' type='text' id='txtIRiesgo' size='40' /></label></td></tr>");
			out.print("<tr><td colspan='4'><div align='left'><label><br><br><input type='button' name='btnIngresarGFarmacologico' id='btnIngresarGFarmacologico' value='     Crear     ' onClick='CrearGFarmacologico()'></label></div></td></tr>");
			out.print("<tr><td colspan='2'>&nbsp;</td></tr></table>");
			out.print("<table width='100%' class='style6'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Grupos Creados</div></td></tr>");
			out.print("<table border='0' class='style6'><tr><td  ><u><i><div align='left'>Descripcion</div></i></u></td><td><u><i><div align='left'>Identificador de Riesgo &nbsp;</div></i></u></td><td><u><i><div align='left'>&nbsp;Modificar</div></i></u></td></tr>");
			//out.print("<table width='100%' border='0' class='style6'>");
			String codigo1=null;
			String codigo2=null;
			String codigo3=null;
			rs1=mcp.ObtenerGFarmacologico();				
			try {
				while(rs1.next()){
					codigo2=rs1.getString(2);
					codigo1=rs1.getString(1);
					codigo3=rs1.getString(3);
					out.print("<tr><td>"+codigo1+"</td>");
					out.print("<td>"+codigo2+" &nbsp;</td>");
					out.print("<td>&nbsp;<input type='radio'  name='ModGrupo'  onclick='ModGrupoF("+codigo3+")' ></td></tr>");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			out.print("<tr><td colspan='2'>&nbsp;</td></tr></table>");
		
		}

	if(va.equals("CorteProduccion")){			
	String usuario=request.getParameter("usuario");
	out.print("<table width='100%' class='style6'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Crear Corte de Produccion </div></td></tr>");
	out.print("<tr><td width='12%'>Hora de Corte </td>");
	out.print("<td><select id='Ph'><option value='invalido'>--</option>");
	int i=0;
	for(i=0;i<25;i++){
		if(i<10){out.print("<option value='0"+i+"'>0"+i+"</option>");
		}else{
		out.print("<option value='"+i+"'>"+i+"</option>");
		}
		//<td width='30%'><label><input name='txtHoraC' type='text' id='txtHoraC' size='40'  /></label></td><td width='13%'>Identificador de Riesgo</td><td width='45%'><label><input name='txtIRiesgo' type='text' id='txtIRiesgo' size='40' /></label></td></tr>");
	}
	out.print("</select>:<select id='Sh'><option value='invalido'>--</option>");
	int j=0;
	for(j=0;j<61;j++){
		if(j<10){out.print("<option value='0"+j+"'>0"+j+"</option>");
		}else{
		out.print("<option value='"+j+"'>"+j+"</option>");
		}
	}
	out.print("</select></td></tr>");
	out.print("<tr><td colspan='4'><div align='left'><label><br><br><input type='button' name='btnHoraCorte' id='btnHoraCorte' value='     Crear     ' onClick='CrearCorte("+usuario+")'></label></div></td></tr>");
	out.print("<tr><td colspan='2'>&nbsp;</td></tr></table>");
	out.print("<table width='100%' class='style6'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Cortes Creados</div></td></tr>");
	out.print("<table border='0' class='style6'><tr><td  ><u><i><div align='left'>Corte Creado</div></i></u></td><td><u><i><div align='left'>Usuario &nbsp;</div></i></u></td></tr>");
	//out.print("<table width='100%' border='0' class='style6'>");
	String codigo1=null;
	String codigo2=null;
	String codigo3=null;
	rs1=mcp.ObtenerCorteCreado();				
	try {
		while(rs1.next()){
			codigo2=rs1.getString(2);
			codigo1=rs1.getString(1);
			out.print("<tr><td>"+codigo1+"</td>");
			out.print("<td>"+codigo2+" &nbsp;</td></tr>");
			
		}
		rs1.getStatement().getConnection().close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	out.print("<tr><td colspan='2'>&nbsp;</td></tr></table>");

	}


	if(va.equals("MGFarm")){			
		String codGrupo=request.getParameter("codGrupo");
		rs=mcp.ObtenerGrupoF(codGrupo);
		out.print("<table width='100%' class='style6'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Modificar Grupo Farmacologico</div></td></tr>"); 
		try {
			while(rs.next()){
				out.print("<tr><td width='12%'>Descripción</td><td width='30%'>");
				out.print("<label><input name='txtDescripcion' type='text' id='txtDescripcion' size='40' value='"+rs.getString(1)+"'></input></label></td><td width='13%'>Identificador de Riesgo</td><td><label><input name='txtIRiesgo' type='text' id='txtIRiesgo' size='40' value="+rs.getString(2)+"></input></label></td></tr>");
				out.print("<tr><td colspan='4'><div align='left'><label><br><br><input type='button' name='btnIngresarGFarmacologico' id='btnIngresarGFarmacologico' value='     Modificar Grupo    ' onClick='ModGFarmacologico("+codGrupo+")'></label></div></td></tr>");
				System.out.println("descripcion mgfarm "+rs.getString(1));
			}
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
		out.print("</table>");
	}
		
	if(va.equals("CGrupo")){
		String desc=request.getParameter("desc");
		String Iriesgo=request.getParameter("Iriesgo");
		mcp.CrearGrupo(desc,Iriesgo);
	}
	
	if(va.equals("CHora")){
		String Ph=request.getParameter("Ph");
		String Sh=request.getParameter("Sh");
		String usuario=request.getParameter("usuario");
		String HoraC=Ph+":"+Sh+":00";
		java.util.Date fechaActual = new java.util.Date();
		java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
		java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());
		mcp.CrearCorte(HoraC,usuario,Fecha,Hora);
	}
		
	if(va.equals("MGrupo")){
	
		String codGrupo=request.getParameter("codGrupo");
		String descripcion=request.getParameter("d");
		String Iriesgo=request.getParameter("Iriesgo");
		System.out.println("MGrupo"+codGrupo);
		System.out.println("descripcion "+descripcion);
		mcp.ModGrupo(codGrupo,descripcion,Iriesgo);
		
	}

		if(va.equals("Proveedor")){			
			
			out.print("<table width='100%' class='style6'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Crear Proveedores</div></td></tr>");
			out.print("<tr><td width='12%'>Razon Social</td><td width='30%'><label><input name='txtRazon' type='text' id='txtRazon' size='40' onkeyup='this.value=this.value.toUpperCase();' /></label></td><td width='13%'>Nit</td><td width='45%'><label><input name='txtNit' type='text' id='txtNit' size='40' /></label></td></tr>");
			out.print("<tr><td>Direccion</td><td><label><input name='txtDireccion' type='text' id='txtDireccion' size='40'/></label></td><td width='13%'>Telefono</td><td width='45%'><label><input name='txtTelefono' type='text' id='txtTelefono' size='40' /></label></td></tr>");
			out.print("<tr><td>Fax</td><td><label><input name='txtFax' type='text' id='txtFax' size='40'/></label></td><td>Correo Electronico</td><td><label><input name='txtEmail' type='text' id='txtEmail' size='40'/></label></td></tr>");
			out.print("<tr><td width='12%'>Nacionalidad</td><td><label><select name='cmbPais' id='cmbPais' onChange='Dpto()'><option value='Seleccione'>Seleccione</option>");
			rs1=mcp.ObtenerPais();
			try {
				while(rs1.next()){
				out.print("<option value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("</select></label></td>");
			out.print("<td width='12%'>Departamento</td><td><label><div id='dpto'><select name='cmbDpto' id='cmbDpto'><option value='Seleccione'>Seleccione</option>");
			out.print("</select></div></label></td></tr>");
			out.print("<tr><td width='12%'>Municipio</td><td><label><div id='Muni'><select name='cmbMuni' id='cmbMuni'><option value='Seleccione'>Seleccione</option>");
			out.print("</select></div></label></td>");
			out.print("<td>Clase Contribuyente</td><td><label><select name='cmbclase' id='cmbClase' ><option value='Seleccione'>Seleccione</option><option value='Regimen Comun'>Regimen Comun</option><option value='Regimen Simplificado'>Regimen Simplificado</option><option value='Grandes Contribuyentes'>Grandes Contribuyentes</option></select></label></td></tr>");
			out.print("<tr><td width='12%'>Contacto</td><td width='30%'><label><input name='txtContacto' type='text' id='txtContacto' size='40'/></label></td>");
			out.print("<tr><td>Observacion</td><td><label><textarea name='txtDescripcion' id='txtDescripcion' cols='32' rows='3'></textarea></label></td></tr>");
			out.print("<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>");
			out.print("<tr><td colspan='4'><div align='center'><label><input type='button' name='btnIngresarProveedor' id='btnIngresarProveedor' value='     Crear     ' onClick='CrearProveedor()'></label></div></td></tr>");
			out.print("<tr><td colspan='2'>&nbsp;</td></tr></table>");
			
			out.print("<table width='100%' class='style6'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Proveedores Creados</div></td></tr>");
			out.print("<table width='100%' border='0' class='style6'><tr><td width='12%'><u><i><div align='left'>NIT</div></i></u></td><td width='88%'><u><i><div align='left'>Razon Social</div></i></u></td></tr>");
			out.print("<table width='100%' border='0' class='style6'>");
			String codigo1=null;
			String codigo2=null;
			rs1=mcp.ObtenerProveedor();
								
			try {
				while(rs1.next()){
					codigo2=rs1.getString(2);
					codigo1=rs1.getString(1);
					out.print("<tr><td width='12%'>"+codigo1+"</td>");
					out.print("<td>"+codigo2+"</td></tr>");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			out.print("<tr><td colspan='2'>&nbsp;</td></tr></table>");
		
		}
		
		
		if(va.equals("Tipos")){			
			out.print("<table width='100%' class='style6'><tr><td colspan='2'><div align='center' class='style11' id='cabecera2'>Tipos de Movimientos</div></td></tr>");
			out.print("<tr><td width='12%'>Nombre</td><td><label><input name='txtNombreTipo' type='text' id='txtNombreTipo' onkeyup='this.value=this.value.toUpperCase();' size='48' /></label></td></tr>");
			out.print("<tr><td width='13%'>Operacion</td><td width='45%'><label><select name='cmbOperacion' id='cmbOperacion'><option value='Seleccione'>Seleccione</option>");
			rs1=mcp.ObtenerTipoMovimiento();
			try {
				while(rs1.next()){
				out.print("<option value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("</select></label></td></tr>");		
		
			out.print("<tr><td colspan='2'><div align='left'><label><input type='button' name='btnCrearTipo' id='btnCrearTipo' value='Crear' onClick='CrearTipo()'></label></div></td></tr>");		
			out.print("<table width='100%' class='style6'><tr><td colspan='2'><div align='center' class='style11' id='cabecera2'>Tipos de Movimientos Creados</div></td></tr>");
			out.print("<table width='100%' border='0' class='style6'><tr><td width='12%'><u><i><div align='left'>Nombre</div></i></u></td><td width='88%'><u><i><div align='left'>Operacion</div></i></u></td></tr>");
			out.print("<table width='100%' border='0' class='style6'>");
			
			String codigo1=null;
			//String codigo2=null;
			String codigo=null;
			rs1=mcp.ObtenerMovimiento();
			
								
			try {
				while(rs1.next()){
					codigo1=rs1.getString(1);
					
					rs2=mcp.ObtenerTipoMovimiento(codigo1);
					try {
						while(rs2.next()){
							codigo1=rs2.getString(1);
						}
						rs2.getStatement().getConnection().close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					codigo=rs1.getString(2);
					out.print("<tr><td width='12%'>"+codigo+"</td><td>"+codigo1+"</td></tr>");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			out.print("<tr><td colspan='2'>&nbsp;</td></tr></table>");
			
		}
		
		if(va.equals("1")){			
			out.print("<select name='cmbDpto' id='cmbDpto' onChange='Muni()'><option value='Seleccione'>Seleccione</option>");
			rs1=mcp.ObtenerDpto(pais);
			try {
				while(rs1.next()){
				out.print("<option value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("</select></label>");
			
		}
		if(va.equals("2")){			
			out.print("<select name='cmbMuni' id='cmbMuni'><option value='Seleccione'>Seleccione</option>");
			rs1=mcp.ObtenerMuni(ciudad);
			try {
				while(rs1.next()){
				out.print("<option value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("</select></label>");
			
		}
		
		if(va.equals("3")){
			  
			 /* Aqui  se verifica si existe un formato con el mismo nombre.
			 */
			String codigo=null;
			
			rs=mcp.ObtenerCodigoProveedor(nit);
			try {
				if(rs.next()){
					codigo=rs.getString(1);
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			/**
			 * Si No existe Ninguno Con el mismo Nombre Se Procede a Ingresar El Nuevo Formato.
			 */
			
			if(codigo==null){
			
			mcp.CrearProveedor(nit,muni,telefono,fax,email,contacto,clase,razon,direccion,observacion);
			out.print("Ingreso Exitoso.");
			
			}else{
				out.println("Ya existe un Proveedor con el mismo NIT Por Favor Intente nuevamente.");
			}
		}
		
		if(va.equals("4")){
			  
			 /* Aqui  se verifica si existe un formato con el mismo nombre.
			 */
			String codigo=null;
			
			rs=mcp.ObtenerMovimiento(nombreTipo);
			try {
				if(rs.next()){
					codigo=rs.getString(1);
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			/**
			 * Si No existe Ninguno Con el mismo Nombre Se Procede a Ingresar El Nuevo Formato.
			 */
			
			if(codigo==null){
				
			mcp.CrearTipoMovimiento(nombreTipo,Operacion);
			//out.print("Ingreso Exitoso.");
			
			}else{
				out.println("Ya existe el Tipo de Movimiento por Favor Intente nuevamente.");
			}
		}
		
		
		if(va.equals("MProveedor")){			
			
			out.print("<table width='100%' class='style6'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Proveedores Creados</div></td></tr>");
			out.print("<table width='100%' border='0' class='style6'><tr><td width='12%'><u><i><div align='left'>NIT</div></i></u></td><td width='88%'><u><i><div align='left'>Razon Social</div></i></u></td></tr>");
			out.print("<table width='100%' border='0' class='style6'>");
			
			String codigo1=null;
			String codigo2=null;
			String codigo3=null;
			rs1=mcp.ObtenerProveedor();
								
			try {
				while(rs1.next()){
					codigo3=rs1.getString(3);
					codigo2=rs1.getString(2);
					codigo1=rs1.getString(1);
					out.print("<tr><td width='12%'>"+codigo1+"</td>");			
					out.print("<td><a  href='#'onclick='Actualizarp("+codigo3+")'>"+codigo2+"</a></td></tr>");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			out.print("<tr><td colspan='2'>&nbsp;</td></tr></table>");
		
		}
		
		if(va.equals("Actualizarp")){	
			out.print("<table width='100%' class='style6'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Modificar Proveedores</div></td></tr>");
			
			rs2=mcp.ObtenerProveedord(ni);
			String nmunic="";
			String cdept="";
			String ndept="";
			String cpais="";
			String npais="";
			try {
				if(rs2.next()){
					out.print("<tr><td width='12%'>Razon Social</td><td width='30%'><label><input name='txtRazon' type='text' id='txtRazon' value='"+rs2.getString(8)+"' size='40' onkeyup='this.value=this.value.toUpperCase();' /></label></td><td width='13%'>Nit</td><td width='45%'><label><input name='txtNit' type='text' id='txtNit' value='"+rs2.getString(1)+"' size='40' /></label></td></tr>");
					out.print("<tr><td>Direccion</td><td><label><input name='txtDireccion' type='text' id='txtDireccion' value='"+rs2.getString(9)+"' size='40'/></label></td><td width='13%'>Telefono</td><td width='45%'><label><input name='txtTelefono' type='text' id='txtTelefono' value='"+rs2.getString(3)+"' size='40' /></label></td></tr>");
					out.print("<tr><td>Fax</td><td><label><input name='txtFax' type='text' id='txtFax' value='"+rs2.getString(4)+"' size='40'/></label></td><td>Correo Electronico</td><td><label><input name='txtEmail' type='text' id='txtEmail' value='"+rs2.getString(5)+"' size='40'/></label></td></tr>");
					
					//obtengo el municipio
					rs1=mcp.ObtenerNMuni(rs2.getString(2));
					try {
						if(rs1.next()){
						nmunic=rs1.getString(1);
						cdept=rs1.getString(2);
						}
						rs1.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					
					//obtengo el departamento
					rs3=mcp.ObtenerNDpto(cdept);
					try {
						if(rs3.next()){
						ndept=rs3.getString(1);
						cpais=rs3.getString(2);
						}
						rs3.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					
					//obtengo el Pais
					rs4=mcp.ObtenerNPais(cpais);
					try {
						if(rs4.next()){
						npais=rs4.getString(1);
						}
						rs4.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					
					out.print("<tr><td width='12%'>Nacionalidad</td><td><label><select name='cmbPais' id='cmbPais' onChange='Dpto()'><option value='"+cpais+"'>"+npais+"</option>");
					rs1=mcp.ObtenerPais();
					try {
						while(rs1.next()){
						out.print("<option value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
						}
						rs1.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					out.print("</select></label></td>");
					out.print("<td width='12%'>Departamento</td><td><label><div id='dpto'><select name='cmbDpto' id='cmbDpto' onChange='Muni()'><option value='"+cdept+"'>"+ndept+"</option>");
					rs5=mcp.ObtenerDpto(cpais);
					try {
						while(rs5.next()){
						out.print("<option value="+rs5.getString(1)+">"+rs5.getString(2)+"</option>");
						}
						rs5.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					out.print("</select></div></label></td></tr>");
					out.print("<tr><td width='12%'>Municipio</td><td><label><div id='Muni'><select name='cmbMuni' id='cmbMuni' ><option value='"+rs2.getString(2)+"'>"+nmunic+"</option>");
					rs6=mcp.ObtenerMuni(cdept);
					try {
						while(rs6.next()){
						out.print("<option value="+rs6.getString(1)+">"+rs6.getString(2)+"</option>");
						}
						rs6.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					out.print("</select></div></label></td>");
					if(rs2.getString(7).equals("Regimen Comun")){
					out.print("<td>Clase Contribuyente</td><td><label><select name='cmbclase' id='cmbClase' ><option value='"+rs2.getString(7)+"'>"+rs2.getString(7)+"</option><option value='Regimen Simplificado'>Regimen Simplificado</option><option value='Grandes Contribuyentes'>Grandes Contribuyentes</option></select></label></td></tr>");
					}else{if(rs2.getString(7).equals("Regimen Simplificado")){
					out.print("<td>Clase Contribuyente</td><td><label><select name='cmbclase' id='cmbClase' ><option value='"+rs2.getString(7)+"'>"+rs2.getString(7)+"</option><option value='Regimen Comun'>Regimen Comun</option><option value='Grandes Contribuyentes'>Grandes Contribuyentes</option></select></label></td></tr>");
					}else{out.print("<td>Clase Contribuyente</td><td><label><select name='cmbclase' id='cmbClase' ><option value='"+rs2.getString(7)+"'>"+rs2.getString(7)+"</option><option value='Regimen Comun'>Regimen Comun</option><option value='Regimen Simplificado'>Regimen Simplificado</option></select></label></td></tr>");
					}
					}
					out.print("<tr><td width='12%'>Contacto</td><td width='30%'><label><input name='txtContacto' type='text' id='txtContacto' value='"+rs2.getString(6)+"' size='40'/></label></td>");
					out.print("<tr><td>Observacion</td><td><label><textarea name='txtDescripcion' id='txtDescripcion' cols='32' rows='3' >"+rs2.getString(10)+"</textarea></label></td></tr>");
					out.print("<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>");
					out.print("<tr><td colspan='4'><div align='center'><label><input type='button' name='btnIngresarProveedor' id='btnIngresarProveedor' value='     Modificar     ' onClick='ModificarProveedor("+ni+")'></label></div></td></tr>");
					out.print("<tr><td colspan='2'>&nbsp;</td></tr></table>");
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		
		if(va.equals("MP")){
			  int sw=0;
			 /* Aqui  se verifica si existe un formato con el mismo nombre.
			 */
			String codigo=null;
			
			//el nit es nit
			//el codpp es cod
			
			rs=mcp.ObtenerCodigoProveedor(nit);
			try {
				while(rs.next()){
					if(!rs.getString(1).equals(codpp)){
						sw=1;
					}
					codigo=rs.getString(1);
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			/**
			 * Si No existe Ninguno Con el mismo Nombre Se Procede a Ingresar El Nuevo Formato.
			 */
			if(sw==0){
			mcp.ModificarProveedor(nit,muni,telefono,fax,email,contacto,clase,razon,direccion,observacion,codpp);
			out.print("Ingreso Exitoso.");
			}else{
			out.print("Ya existe un proveedor con el mismo NIT.");	
			}
			
		}
		
  }
}

