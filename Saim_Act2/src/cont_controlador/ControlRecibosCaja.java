package cont_controlador;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import Anexos_metodo.MetodoBDCapitado;

import com.csvreader.CsvReader;

import cont_metodo.MetodoCuentas;

/**
 * Servlet implementation class ControlRecibosCaja
 */
public class ControlRecibosCaja extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		/*if(va.equals("ValPa")){
			try{
				String Banco=req.getParameter("Banco");
				String Tipo="1";
			mc.CrearComplementoReciboCaja(FormaPago, ValorPagado, CodRecCaja,Tipo,Banco);
			rs=mc.BuscarComplementoReciboCaja(CodRecCaja,Tipo);
			out.print("<table border='1' width='100%'>");
			out.print("<tr><td>Forma Pago</td><td>Valor Pagado</td><td>Banco</td><td>Accion</td></tr>");
			long ValorPagadoR=0;
			while(rs.next()){
				out.print("<tr><td>"+rs.getString(1)+"</td><td>"+FormatoMoneda(rs.getString(2))+"</td><td>"+rs.getString(4)+"</td><td><a href='#' onclick='OmitirDetallePago("+rs.getString(3)+")'>Omitir</a></td></tr>");
				ValorPagadoR=ValorPagadoR+rs.getLong(2);
			}
			rs.getStatement().getConnection().close();
			out.print("<input id='txtValorPagadoR' type='hidden' value="+ValorPagadoR+" /></table>");
			} catch (SQLException e) {
				out.println(e);
				e.printStackTrace();
			}
		}
		
		if(va.equals("ValDe")){
			try{
				String Tipo="0";
				mc.CrearComplementoReciboCaja(Deduccion, ValorDeduccion, CodRecCaja,Tipo,Tipo);
				rs=mc.BuscarComplementoReciboCaja(CodRecCaja,Tipo);
				out.print("<table border='1' width='100%'>");
				out.print("<tr><td>Concepto</td><td>Valor</td><td>Accion</td></tr>");
				long ValorDeducciones=0;
				while(rs.next()){
					out.print("<tr><td>"+rs.getString(1)+"</td><td>"+FormatoMoneda(rs.getString(2))+"</td><td><a href='#' onclick='OmitirDeduccion("+rs.getString(3)+")'>Omitir</a></td></tr>");
					ValorDeducciones=ValorDeducciones+rs.getLong(2);
				}
				rs.getStatement().getConnection().close();
				out.print("<input id='txtTotalValorDeduccion' type='hidden' value="+ValorDeducciones+" /></table>");
				} catch (SQLException e) {
					out.println(e);
					e.printStackTrace();
				}

		}*/
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		MetodoCuentas mc = new MetodoCuentas();
		ResultSet rs, rs1, rs2 = null;
		String BusquedaAgrupada="off";
		String codempresa = "";
		String consecutivo = "";
		String valor = "0";
		String prueba = "0";
	
		
		
		
		int contE = 0;
		int contNE = 0;

		boolean isMultipart = ServletFileUpload.isMultipartContent(req);

		String destination = "C:\\ReciboCaja";
		DiskFileItemFactory factory1 = new DiskFileItemFactory();
		FileItemFactory factory = new DiskFileItemFactory();
		factory1.setRepository(new File(destination));
		ServletFileUpload upload = new ServletFileUpload(factory);
		ServletFileUpload uploader = new ServletFileUpload(factory1);
		List items = null;
		List items1 = null;

		try {
			items = upload.parseRequest(req);
			items1 = uploader.parseRequest(req);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}

		Iterator itr = items.iterator();
		while (itr.hasNext()) {
			FileItem item = (FileItem) itr.next();

			if (item.isFormField()) {
				String fieldName = item.getFieldName();
				if (fieldName.equals("codempresa")) {
					codempresa = item.getString();
					//System.out.println("este es el codempresa " + codempresa);
				}	
				if(fieldName.equals("unidos")){
					BusquedaAgrupada=item.getString();
					
				}
			}
			prueba = item.getName();
		}
		
		// capturo la extension del archivo a cargar
		String ext = "";
		ext = prueba.substring(prueba.length() - 3, prueba.length());
		/*try {
			// se consulta el codigo del convenio de la empresa
			rs1 = mc.BuscarDetalleFactura(CodFacturaCF);
			if (rs1.next()) {
				//codconvenio = rs1.getString(1);
			}
			rs1.getStatement().getConnection().close();
			//System.out.println("convenio " + codconvenio);

		} catch (Exception e) {
			// TODO: handle exception
		}*/
		String nombre = "";
		
		// upload al servidor
		try {
			Iterator iterator = items.iterator();

			while (iterator.hasNext()) {
				FileItem item = (FileItem) iterator.next();
				System.out.println("este es el codempresa " + codempresa);
				System.out.println("BusquedaAgrupada "+BusquedaAgrupada);
				nombre = item.getName();

				File file = new File(destination, codempresa + "." + ext);
				item.write(file);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}		
		// *** leer archivo cvs
		if (ext.equals("csv")) {
			String DatosFormulario="";
			String DatoFactura="";
			String FechaFactura="";
			long ValidarPago=0;
			//String='"';
			CsvReader reader = null;
			try {
				DatosFormulario="<table width='100%' border='1' cellspacing='0' cellpadding='0' >";
				DatosFormulario=DatosFormulario+"<tr id='cabecera2' class='style11' ><td colspan='8' align='center'>PAGO DE FACTURA</td></tr>";
				rs=mc.BuscarDatosEmpresa(codempresa);
				if(rs.next()){
					DatosFormulario=DatosFormulario+"<tr><td>Entidad</td><td colspan='3'>"+rs.getString("nombre_entidad")+" - "+rs.getString("ent_nit_contratante")+"</td><td>Direccion</td><td colspan='2'>"+rs.getString("direccion")+"  Telefono "+rs.getString("telefono")+"</td><td></td></tr>";
					
					DatosFormulario=DatosFormulario+"<tr><td>Movimiento</td><td colspan='3' >PAGO Y/O ABONO A FACTURA</td><td colspan='2'>Fecha <input name='txtFechaPago' type='text' id='txtFechaPago' onKeyUp='masca(this,&quot;/&quot;,patron,true)' ></td><td>Concepto</td><td ><textarea name='txtObservacionRC' id='txtObservacionRC' cols='35' rows='3'></textarea></td></tr>";
				}
				rs.getStatement().getConnection().close();
				DatosFormulario=DatosFormulario+"<tr bgcolor='#DADADA' class='style12' align='center' ><td width='5%'>ITEM</td><td width='9%'>NUMERO FACTURA</td><td width='8%'>FECHA FACTURA</td><td width='8%'>VALOR FACTURA</td><td width='8%'>SALDO FACTURA</td><td width='8%'>ABONO</td><td width='8%'>ESTADO FACTURA</td><td width='40%'>OBSERVACION</td></tr></table>";
				DatosFormulario=DatosFormulario+"<table width='100%' border='1' cellspacing='0' cellpadding='1' >" +
						"<tr><td><div style='width:100%; height:500px; overflow: scroll;'>" +
						"<table width='100%' border='1' cellspacing='0' cellpadding='0'>";
				DatosFormulario=DatosFormulario+"<tr><td width='5%'></td><td width='8%'></td><td width='7%'></td><td width='7%'></td><td width='10%'></td><td width='7%'></td><td width='9%'></td><td width='37%'></td></tr>";
				// instancio el objeto readerCSV
				reader = new CsvReader(destination + "\\" + codempresa + "."+ ext);
				// asigno separador de valores punto y coma, si no lo cambian
				// queda por defecto la coma
				reader.setDelimiter(';');
				// recorremos las filas del fichero
				int cont=0;
				long totalAbonos=0;
				while (reader.readRecord()) {
					consecutivo = reader.get(0);
					valor = reader.get(1);					
					long saldo=0;
					try {
						rs1=mc.ValidarFacturaExiste(consecutivo);
						if(rs1.next()){
							DatoFactura=rs1.getString("estadoFact");
							FechaFactura=rs1.getString("fecha");
							if(DatoFactura.equals("0")){DatoFactura="EMITIDA";}
							if(DatoFactura.equals("1")){DatoFactura="ENVIADA";}
							if(DatoFactura.equals("2")){DatoFactura="RADICADA";}
							if(DatoFactura.equals("3")){DatoFactura="GLOSADA";}
							if(DatoFactura.equals("4")){DatoFactura="DEVUELTA";}
							if(DatoFactura.equals("5")){DatoFactura="ANULADA";}
							if(DatoFactura.equals("6")){DatoFactura="AUDITORIA CONCURRENTE";}
							if(DatoFactura.equals("6")){DatoFactura="RADICACION INTERNA";}
							if(DatoFactura.equals("8")){DatoFactura="PARA REENVIAR";}
							if(BusquedaAgrupada.equals("on")){
								System.out.print("entro agrupada");
								rs=mc.ObtenerNit(codempresa);
								if(rs.next()){
									rs2=mc.ValidarFacturaEmpresaNit(consecutivo, rs.getString("ent_nit_contratante"));
								}
								rs.getStatement().getConnection().close();
							}
							if(BusquedaAgrupada.equals("off")){
								System.out.print("entro sin agrupar");
								rs2=mc.ValidarFacturaEmpresa(consecutivo, codempresa);
							}
							
							if(rs2.next()){
								rs = mc.BuscarFactura(consecutivo+"");
								if (rs.next()) {									
									saldo=rs.getLong("saldo");
									totalAbonos=totalAbonos+Long.parseLong(valor);
									if(DatoFactura.equals("ANULADA")){
										DatosFormulario=DatosFormulario+"<tr bgcolor='#2E64FE' ><td><font color='white'>N/A</font></td><td><font color='white'>"+consecutivo+"</font></td><td colspan='6' ><font color='white'> ESTE NUMERO DE FACTURA ESTA ANULADA.</font></td></tr>";
									}else{
										
										ValidarPago=saldo-Long.parseLong(valor);
										if(ValidarPago<0){
											DatosFormulario=DatosFormulario+"<tr bgcolor='#AC58FA'><td><font color='white'>N/A</font></td><td><font color='white'>"+consecutivo+"</font></td><td><font color='white'>"+FechaFactura+"</font></td><td><font color='white'>"+formatMoneda(rs.getString("precio_factura"))+"</font></td><td><font color='white'>"+formatMoneda(saldo+"")+"</font></td><td><font color='white'>"+formatMoneda(valor)+"</font><td><font color='white'>"+DatoFactura+"</font></td><td><font color='white'>EL ABONO NO PUEDE SER MAYOR AL SALDO "+ValidarPago+"</font></td></tr>";
										}else{
											cont=cont+1;
											DatosFormulario=DatosFormulario+"<tr><td>"+cont+"</td><td><input type='hidden' id='txtCodigoFactura' value="+rs.getString("codigo")+" >"+consecutivo+"</td><td>"+FechaFactura+"</td><td>"+formatMoneda(rs.getString("precio_factura"))+"</td><td>"+formatMoneda(saldo+"")+"</td><td><input type='hidden' id='txtAbono' value="+valor+" >"+formatMoneda(valor+"")+"<td>"+DatoFactura+"</td><td>VALIDADO</td></tr>";
										}										
									}
								} 
								rs.getStatement().getConnection().close();
							}else{
								//cont=cont+1;								
								DatosFormulario=DatosFormulario+"<tr bgcolor='#F3F781' ><td>N/A</td><td>"+consecutivo+"</td><td colspan='6'><font color='black'> ESTE NUMERO DE FACTURA NO PERTENECE A ESTA ENTIDAD.</font></td></tr>";
							}
							rs2.getStatement().getConnection().close();
							
						}else{
							//cont=cont+1;
							DatosFormulario=DatosFormulario+"<tr bgcolor='#FA5858' ><td><font color='white'>N/A</font></td><td><font color='white'>"+consecutivo+"</font></td><td colspan='6' ><font color='white'> ESTE NUMERO DE FACTURA NO EXISTE.</font></td></tr>";
						}
						rs1.getStatement().getConnection().close();
						
					} catch (SQLException e) {
						e.printStackTrace();
					}

				}// end while - recorrido del csv
				DatosFormulario=DatosFormulario+"</table></div></td></tr></table> " +
						"<table width='100%' border='1' cellspacing='0' cellpadding='0' > " +
						"<tr id='cabecera2' class='style11' ><td colspan='8' align='center'>DETALLE DEL PAGO</td></tr>"+
						"<tr>" +
						"<td>Deducciones</td>" +
						"<td><select name='cmbDeducciones' id='cmbDeducciones'><option value='Seleccione'>Seleccione</option> ";
						rs=mc.BuscarDeducciones();
						while(rs.next()){
							DatosFormulario=DatosFormulario +"<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>";
						}
						rs.getStatement().getConnection().close();
						DatosFormulario=DatosFormulario+"</select></td>" +
						"<td>Valor Deduccion</td>" +
						"<td><input name='txtValorDeduccion' type='text' id='txtValorDeduccion' /></td>" +
						"<td><input name='btnDeducir' type='button' id='btnDeducir' value='Deducir' onclick='IngresarDeduccion()'></td>" +
						"<td></td>" +
						"<td></td>" +
						"</tr> " +
						"<tr>" +
						"<td>Forma de Pago</td>" +
						"<td><select name='cmbFormaPago' id='cmbFormaPago' ><option value='Seleccione'>Seleccione</option> ";
						rs=mc.BuscarFormasPago();
						while(rs.next()){
							DatosFormulario=DatosFormulario +"<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>";
						}
						rs.getStatement().getConnection().close();
						DatosFormulario=DatosFormulario+"</select></td>" +
						"<td>Valor Pagado</td>" +
						"<td><input name='txtValorPagado' type='text' id='txtValorPagado' value="+totalAbonos+"></td>" +
						"<td>Banco</td>" +
						"<td><select name='cmbBanco' id='cmbBanco'><option value='Seleccione'>Seleccione</option> ";
						rs=mc.BuscarBancos();
						while(rs.next()){
							DatosFormulario=DatosFormulario +"<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>";
						}
						rs.getStatement().getConnection().close();
						DatosFormulario=DatosFormulario+"</select></td>" +
						"<td><input type='button' value='Ingresar'  onclick='GenerarRC()' /></td>" +
						"</tr> " +
						"</table>";				
				HttpSession session = req.getSession(true);
				session.setAttribute("FormularioRC", DatosFormulario);
				res.sendRedirect("Cont_RecibosCajaR.jsp");

			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				reader.close();
			} // end try
		}
	}
	public String formatMoneda(String valor){		
		String temp2="";String temp1="";
		int ud=1;int logCad = valor.length();		
		for (int i=logCad-1;i>=0;i--){			
			temp2=valor.substring(i,i+1);
			temp2+=temp1;
			if(ud==3){
				if(i!=0){temp1="."+temp2;}else{temp1=temp2;}ud=0;
			}else{temp1=temp2;}
			ud++;
		}
		temp1="$ "+temp1;
		return temp1;
	}
}
