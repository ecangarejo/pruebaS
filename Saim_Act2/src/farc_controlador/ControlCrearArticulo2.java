package farc_controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.*; //para llamar la librería 


import java.io.PrintWriter;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.io.*;

import javax.servlet.http.HttpSession;


import farc_metodo.MetodoCrearArticulo2;

public class ControlCrearArticulo2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(request, response);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		HttpSession session = req.getSession(true);
		
		String va = req.getParameter("valor");

		
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out=res.getWriter();
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		ResultSet rs3=null;
		ResultSet rs4=null;
		ResultSet rsfc=null;
		ResultSet rsef=null;
		
		MetodoCrearArticulo2 mca = new MetodoCrearArticulo2();
		
		
	if(va.equals("30A")){
		
		out.print("<table width='100%' border='0' align='center'>" +
				"<tr>" +
				"<td colspan='4' height='30' valign='top'>" +
					"<div align='center' class='style11' id='cabecera2'>CREAR MEDICAMENTO y/o ARTICULO</div>" +
				"</td>" +
				"</tr>" +
				"</table>");
		out.print("<table width='100%' border=0' class='style6' ><tr BGCOLOR='#D3D3D3' ><td colspan='4' height='30' valign='top'><i><div align='center'>DESCRIPCION</div></i></td></tr>");
		out.print("<table width='100%' class='style6'><tr>" +
				"<td width='25%'><div align='center'>Nombre Medicamento o Insumo: </div></td><td><div><input name='txtNombreArticulo' type='text' id='txtNombreArticulo'  size='50'  /></div></td>" +
				"<td width='15%'><div align='center'>Clasificacion: </div></td><td><div><input name='cmbTipo' id='cmbTipo' type='text' value='Generico' size='20'  /></div></td>" );
		out.print("<td width='2%'><div align='center'>Grupo: </div></td><td><div><select name='cmbGrupo' id='cmbGrupo'  onChange='medoins()'><option value='Seleccione'>Seleccione</option>");
		rs=mca.listarGrupo();
		try {
			while(rs.next()){
				out.print("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
			}
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		out.print("</select></div></td>");
		out.print("<tr><td width='10%'><div align='center'>Concentracion: </div></td><td><div><input name='txtConcentracion' type='text' id='txtConcentracion' size='20'/></div></td>");
		out.print("<td width='10%'><div align='center'>Unidad: </div></td><td><div><select name='cmbUnidad' id='cmbUnidad'><option value='Seleccione'>Seleccione</option>");
		rs1=mca.listarUnidades();
		try {
			while(rs1.next()){
				out.print("<option value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
			}
			rs1.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		out.print("</select></div></td>");
		out.print("<td width='10%'><div align='center'>F. Farmaceutica: </div></td><td><div><select name='cmbFFarmaceutica' id='cmbFFarmaceutica'><option value='Seleccione'>Seleccione</option>");
		rs1=mca.listarForma();
		try {
			while(rs1.next()){
				out.print("<option value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
			}
			rs1.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		out.print("</select></div></td>");
	
		out.print("</tr><tr><td width='10%'><div align='center'>Med.Control: </div></td><td><div><select name='cmbControl' id='cmbControl'><option value='Seleccione' >Seleccione</option><option value='Si'>Si</option><option value='No'>No</option></select></div></td>");
		
		out.print("<td><div class='lDet' id='tipoMedicamento'><p class='desc'>Tipo medicamento:</p><select id='cmbClasificacion' name='cmbClasificacion' onchange='activarATC(this)'><option>Seleccione...</option><option value='No Pos'>No Pos</option><option value='Pos'>Pos</option></select></div></td>");
		out.print("</tr></table>");
		out.print("<br>");
		out.print("<table width='100%' border='0' class='style6' ><tr><td colspan='4'><div align='center'><input type='button' name='Button' value='Ingresar' onclick='GuardarArticuloPrecio()'  /></div></td></tr><tr></tr><tr></tr></table>");
		
		

		
			
	}
	
	if(va.equals("CAV")){
		String txtNombreArticulo=req.getParameter("txtNombreArticulo");
		String txtCodigoATC=req.getParameter("txtCodigoATC");
		String cmbGrupo=req.getParameter("cmbGrupo");
		String txtConcentracion=req.getParameter("txtConcentracion");
		String cmbUnidad=req.getParameter("cmbUnidad");
		String cmbFFarmaceutica=req.getParameter("cmbFFarmaceutica");
		String cmbControl=req.getParameter("cmbControl");
		String cmbClasificacion=req.getParameter("cmbClasificacion");
		String cmbTipo=req.getParameter("cmbTipo");
	
		String txtCodusuario=req.getParameter("txtCodusuario");
		
		try {
		//Crear en la Tabla Medicamento.
		mca.CrearArticulo(txtCodigoATC, txtNombreArticulo, txtConcentracion, "-", cmbGrupo, txtNombreArticulo, cmbUnidad, cmbClasificacion,cmbTipo , cmbFFarmaceutica, cmbControl);
		//se consulta el articulo creado
		rs2=mca.ObtenerCodigoMedicamento(txtCodigoATC, txtNombreArticulo);			
			if(rs2.next()){
				String CodArticulo=rs2.getString("codigo");
				// se crea en la tabla fact_programas.
				java.util.Date FechaAc1 = new java.util.Date();
				java.sql.Date Fecha_Insercion = new java.sql.Date(FechaAc1.getTime());		
				java.sql.Time Hora_Insercion = new java.sql.Time(FechaAc1.getTime());
				mca.CrearProgramaFacturacion(txtCodigoATC, txtNombreArticulo, txtCodusuario, Fecha_Insercion+"", Hora_Insercion+"");
				// se consulta la creacion de programa facturacion
				rs3=mca.ObtenerCodigoPrograma(Fecha_Insercion+"", Hora_Insercion+"");
				if(rs3.next()){
					String CodPrograma=rs3.getString("cod_programa");
					//se crea en programa medicamento
					mca.CrearProgramaMedicamento(CodArticulo, CodPrograma);
					
					// se crea la tarifa en fact tarifa
					String CodManualTari="";String FechaIni="2014-01-01";String FechaFin="2014-12-31";
					if(cmbGrupo.equals("1")){CodManualTari="16";}
					if(cmbGrupo.equals("2")){CodManualTari="19";}
					mca.CrearTarifaArticulo(CodManualTari, CodPrograma);
					out.print("Articulo Creado Exitosamente.");
				}else{
					out.print("Ocurrio un Error, Vuelva a Intentarlo. No Ingreso en Facturacion");
				}	
				rs3.getStatement().getConnection().close();
			}else{
				out.print("Ocurrio un Error, Vuelva a Intentarlo. No Ingreso Medicamento");
			}
			rs2.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
	}
	
	if(va.equals("CAV1")){
		String txtNombreArticulo=req.getParameter("txtNombreArticulo");
		String txtCodigoATC="";
		String cmbGrupo=req.getParameter("cmbGrupo");
		String txtConcentracion="";
		String cmbUnidad="";
		String cmbFFarmaceutica="";
		String cmbControl="";
		String cmbClasificacion=req.getParameter("cmbClasificacion");
		String cmbTipo="";
	
		String txtCodusuario=req.getParameter("txtCodusuario");
		
		try {
		//Crear en la Tabla Medicamento.
		mca.CrearArticulo(txtCodigoATC, txtNombreArticulo, txtConcentracion, "-", cmbGrupo, txtNombreArticulo, cmbUnidad, cmbClasificacion,cmbTipo , cmbFFarmaceutica, cmbControl);
		//se consulta el articulo creado
		rs2=mca.ObtenerCodigoMedicamento(txtCodigoATC, txtNombreArticulo);			
			if(rs2.next()){
				String CodArticulo=rs2.getString("codigo");
				// se crea en la tabla fact_programas.
				java.util.Date FechaAc1 = new java.util.Date();
				java.sql.Date Fecha_Insercion = new java.sql.Date(FechaAc1.getTime());		
				java.sql.Time Hora_Insercion = new java.sql.Time(FechaAc1.getTime());
				mca.CrearProgramaFacturacion(txtCodigoATC, txtNombreArticulo, txtCodusuario, Fecha_Insercion+"", Hora_Insercion+"");
				// se consulta la creacion de programa facturacion
				rs3=mca.ObtenerCodigoPrograma(Fecha_Insercion+"", Hora_Insercion+"");
				if(rs3.next()){
					String CodPrograma=rs3.getString("cod_programa");
					//se crea en programa medicamento
					mca.CrearProgramaMedicamento(CodArticulo, CodPrograma);
					
					// se crea la tarifa en fact tarifa
					String CodManualTari="";String FechaIni="2014-01-01";String FechaFin="2014-12-31";
					if(cmbGrupo.equals("1")){CodManualTari="16";}
					if(cmbGrupo.equals("2")){CodManualTari="19";}
					mca.CrearTarifaArticulo(CodManualTari, CodPrograma);
					out.print("Articulo Creado Exitosamente.");
				}else{
					out.print("Ocurrio un Error, Vuelva a Intentarlo. No Ingreso en Facturacion");
				}	
				rs3.getStatement().getConnection().close();
			}else{
				out.print("Ocurrio un Error, Vuelva a Intentarlo. No Ingreso Medicamento");
			}
			rs2.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
	}
	


	
	

	
	

		
	
}
}

