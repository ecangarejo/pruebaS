package farc_controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import farc_metodo.MetodoCrearBodega;//BORRAR
import farc_metodo.MetodoCrearIvaGrupoUnidad;

/**
 * Servlet implementation class ControlCrearIvaGrupoUnidad
 */
public class ControlCrearIvaGrupoUnidad extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String va = request.getParameter("valor");
		PrintWriter out=response.getWriter();
		MetodoCrearIvaGrupoUnidad mci = new MetodoCrearIvaGrupoUnidad();
		
		String nombreIva=request.getParameter("nombreIva");
		String valor=request.getParameter("valor1");
		String descripcion=request.getParameter("descripcion");
		
		String nombreGrupo=request.getParameter("nombreGrupo");
		String observacion=request.getParameter("observacion");	
		String tipog=request.getParameter("tipog");	
		
		String nombreUnidad=request.getParameter("nombreUnidad");	
		String sigla=request.getParameter("sigla");	
		
		String nombreForma=request.getParameter("nombreForma");	
		String siglaF=request.getParameter("siglaF");	
		
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		
		if(va.equals("Iva")){			
			out.print("<table width='100%' class='style6'><tr><td colspan='2'><div align='center' class='style11' id='cabecera2'>Tipos de IVA</div></td></tr>");
			out.print("<tr><td width='12%'>Nombre</td><td><label><input name='txtNombreIva' type='text' id='txtNombreIva' onkeyup='this.value=this.value.toUpperCase();' size='48' /></label></td></tr>");
			out.print("<tr><td width='12%'>Valor</td><td><label><input name='txtValor' type='text' id='txtValor' size='48' /> %</label></td></tr>");
			out.print("<tr><td>Descripcion</td><td><label><textarea name='txtDescripcion' id='txtDescripcion' cols='38' rows='3'></textarea></label></td></tr>");
			out.print("<tr><td colspan='2'><div align='left'><label><input type='button' name='btnCrearIva' id='btnCrearIva' value='Crear' onClick='CrearIva()'></label></div></td></tr>");		
			out.print("<table width='100%' class='style6'><tr><td colspan='2'><div align='center' class='style11' id='cabecera2'>Tipos de Iva Creados</div></td></tr>");
			out.print("<table width='100%' border='0' class='style6'><tr><td width='12%'><u><i><div align='left'>Nombre</div></i></u></td><td width='88%'><u><i><div align='left'>Valor</div></i></u></td></tr>");
			out.print("<table width='100%' border='0' class='style6'>");
			
			String codigo1=null;
			rs1=mci.ObtenerIva();
								
			try {
				while(rs1.next()){
					codigo1=rs1.getString(2);
					out.print("<tr><td width='12%'>"+codigo1+"</td><td>"+rs1.getString(3)+"</td></tr>");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			out.print("<tr><td colspan='2'>&nbsp;</td></tr></table>");
			
		}
		
		if(va.equals("Grupo")){			
			out.print("<table width='100%' class='style6'><tr><td colspan='2'><div align='center' class='style11' id='cabecera2'>Grupos</div></td></tr>");
			out.print("<tr><td width='12%'>Nombre</td><td><label><input name='txtNombreGrupo' type='text' id='txtNombreGrupo' onkeyup='this.value=this.value.toUpperCase();' size='48' /></label></td></tr>");
			out.print("<tr><td>Descripcion</td><td><label><textarea name='txtObservacion' id='txtObservacion' cols='38' rows='3'></textarea></label></td></tr>");
			out.print("<tr><td width='12%'>Tipo de Grupo</td><td><label><select name='cmbTg' id='cmbTg'><option value='Seleccione'>Seleccione</option><option value='0'>Medicamentos</option><option value='1'>Insumos</option></td></tr>");
			out.print("<tr><td colspan='2'><div align='left'><label><input type='button' name='btnCrearGrupo' id='btnCrearGrupo' value='Crear' onClick='CrearGrupo()'></label></div></td></tr>");		
			out.print("<table width='100%' class='style6'><tr><td colspan='2'><div align='center' class='style11' id='cabecera2'>Grupos Creados</div></td></tr>");

			String codigo1=null;
			rs1=mci.ObtenerGrupo();
								
			try {
				while(rs1.next()){
					codigo1=rs1.getString(2);
					out.print("<tr><td width='12%'>"+codigo1+"</td></tr>");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			out.print("<tr><td colspan='2'>&nbsp;</td></tr></table>");
			
		}
		
		if(va.equals("Unidad")){			
			out.print("<table width='100%' class='style6'><tr><td colspan='2'><div align='center' class='style11' id='cabecera2'>Unidades de Medida</div></td></tr>");
			out.print("<tr><td width='12%'>Nombre</td><td><label><input name='txtNombreUnidad' type='text' id='txtNombreUnidad' onkeyup='this.value=this.value.toUpperCase();' size='48' /></label></td></tr>");
			out.print("<tr><td width='12%'>Sigla</td><td><label><input name='txtSigla' type='text' id='txtSigla' size='48' /></label></td></tr>");
			out.print("<tr><td colspan='2'><div align='left'><label><input type='button' name='btnCrearUnidad' id='btnCrearUnidad' value='Crear' onClick='CrearUnidad()'></label></div></td></tr>");		
			out.print("<table width='100%' class='style6'><tr><td colspan='2'><div align='center' class='style11' id='cabecera2'>Unidades de Medida Creadas</div></td></tr>");
			out.print("<table width='100%' border='0' class='style6'><tr><td width='12%'><u><i><div align='left'>Nombre</div></i></u></td><td width='88%'><u><i><div align='left'>Sigla</div></i></u></td></tr>");
			out.print("<table width='100%' border='0' class='style6'>");
			
			String codigo1=null;
			String codigo=null;
			rs1=mci.ObtenerUnidad();
								
			try {
				while(rs1.next()){
					codigo1=rs1.getString(2);
					codigo=rs1.getString(3);
					out.print("<tr><td width='12%'>"+codigo1+"</td><td>"+codigo+"</td></tr>");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			out.print("<tr><td colspan='2'>&nbsp;</td></tr></table>");
			
		}
		
		if(va.equals("Forma")){			
			out.print("<table width='100%' class='style6'><tr><td colspan='2'><div align='center' class='style11' id='cabecera2'>Formas Farmaceuticas</div></td></tr>");
			out.print("<tr><td width='12%'>Nombre</td><td><label><input name='txtNombreForma' type='text' id='txtNombreForma' onkeyup='this.value=this.value.toUpperCase();' size='48' /></label></td></tr>");
			out.print("<tr><td width='12%'>Sigla</td><td><label><input name='txtSiglaF' type='text' id='txtSiglaF' size='48' /></label></td></tr>");
			out.print("<tr><td colspan='2'><div align='left'><label><input type='button' name='btnCrearForma' id='btnCrearForma' value='Crear' onClick='CrearForma()'></label></div></td></tr>");		
			out.print("<table width='100%' class='style6'><tr><td colspan='2'><div align='center' class='style11' id='cabecera2'>Formas Farmaceuticas Creadas</div></td></tr>");
			out.print("<table width='100%' border='0' class='style6'><tr><td width='12%'><u><i><div align='left'>Nombre</div></i></u></td><td width='88%'><u><i><div align='left'>Sigla</div></i></u></td></tr>");
			out.print("<table width='100%' border='0' class='style6'>");
			
			String codigo1=null;
			String codigo=null;
			rs1=mci.ObtenerForma();
								
			try {
				while(rs1.next()){
					codigo1=rs1.getString(2);
					codigo=rs1.getString(3);
					out.print("<tr><td width='12%'>"+codigo1+"</td><td>"+codigo+"</td></tr>");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			out.print("<tr><td colspan='2'>&nbsp;</td></tr></table>");
			
		}
		
		if(va.equals("1")){
			
			 // Aqui  se verifica si existe un formato con el mismo nombre.
			 
			String codigo=null;
			for(int i=0;i<nombreIva.length();i++){
			      nombreIva=nombreIva.replace('@','%');
			}
			rs=mci.ObtenerCodigoIva(nombreIva);
			try {
				if(rs.next()){
					codigo=rs.getString(1);
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			 // Si No existe Ninguno Con el mismo Nombre Se Procede a Ingresar El Nuevo Formato.
			 
			if(codigo==null){
				mci.CrearIva(nombreIva,valor, descripcion);
								
			}else{
				out.println("EL tipo de IVA ya existe Por Favor Intente nuevamente.");
			}
			
		}
		
		
		if(va.equals("2")){
			
			 // Aqui  se verifica si existe un formato con el mismo nombre.
			 
			String codigo=null;
			
			rs=mci.ObtenerCodigoGrupo(nombreGrupo);
			try {
				if(rs.next()){
					codigo=rs.getString(1);
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			 // Si No existe Ninguno Con el mismo Nombre Se Procede a Ingresar El Nuevo Formato.
			 
			if(codigo==null){
				mci.CrearGrupo(nombreGrupo,observacion,tipog);
								
			}else{
				out.println("El Grupo ya existe Por Favor Intente nuevamente.");
			}
		}
		
		if(va.equals("3")){
			
			 // Aqui  se verifica si existe un formato con el mismo nombre.
			 
			String codigo=null;
			
			for(int i=0;i<sigla.length();i++){
			      sigla=sigla.replace('@','%');
			}
			
			rs=mci.ObtenerCodigoUnidad(nombreUnidad);
			try {
				if(rs.next()){
					codigo=rs.getString(1);
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			 // Si No existe Ninguno Con el mismo Nombre Se Procede a Ingresar El Nuevo Formato.
			 
			if(codigo==null){
				mci.CrearUnidad(nombreUnidad,sigla);
								
			}else{
				out.println("La Unidad de Medida ya existe Por Favor Intente nuevamente.");
			}
		}
		
		if(va.equals("4")){
			
			 // Aqui  se verifica si existe un formato con el mismo nombre.
			 
			String codigo=null;
			
			rs=mci.ObtenerCodigoForma(nombreForma);
			try {
				if(rs.next()){
					codigo=rs.getString(1);
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			 // Si No existe Ninguno Con el mismo Nombre Se Procede a Ingresar El Nuevo Formato.
			 
			if(codigo==null){
				mci.CrearForma(nombreForma,siglaF);
								
			}else{
				out.println("La Forma Farmaceutica ya existe Por Favor Intente nuevamente.");
			}
		}
		
		
		//////////////////////////////
		/*
		 if(va.equals("Modificarboe")){
			String codigo=null;
				rs=mcb.ObtenerCodigoBodega(nombreBodega);
				try {
					while(rs.next()){
						if(!rs.getString(1).equals(code)){
							codigo=rs.getString(1);
							//out.print("code:"+code+" rs:"+rs.getString(1));
						}	
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			
				if(codigo==null){
					mcb.ModificarBodega(nombreBodega, descripcion, code);
									
				}else{
					out.println("La bodega ya existe Por Favor Intente nuevamente.");
				}
			}
		  */
		/////////////////////////
	}

}
