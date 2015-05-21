package com_controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com_metodo.MetodoCrear;

public class ControlAutorizarOrden extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ControlAutorizarOrden() {
		super();
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {

		ResultSet rs = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		MetodoCrear metodos = new MetodoCrear();
		PrintWriter out = res.getWriter();
		String va = req.getParameter("valor");
		String val = req.getParameter("val");
		String codorden = req.getParameter("codorden");
		String proveedor = req.getParameter("proveedor");
		String usuario = req.getParameter("usuario");
		String motivo = req.getParameter("motivo");
		String cod_detalle = req.getParameter("cod_detalle");
		String desde1 = req.getParameter("desde");
		String hasta1 = req.getParameter("hasta");
		String estado = req.getParameter("estado");
		String No_orden = req.getParameter("No_orden");
		String txtOrden = req.getParameter("txtOrden");
		String Pro = req.getParameter("Pro");
		String FI = req.getParameter("FI");
		String FF = req.getParameter("FF");

		Calendar c = new GregorianCalendar();
		String dia = Integer.toString(c.get(Calendar.DATE));
		String mes = Integer.toString(c.get(Calendar.MONTH));
		String annio = Integer.toString(c.get(Calendar.YEAR));
		int m = Integer.parseInt(mes) + 1;
		mes = String.valueOf(m);
		int d = Integer.parseInt(dia);
		if (d < 10) {
			dia = "0" + d;
		}
		if (m < 10) {
			mes = "0" + m;
		}
		String fecha = annio + "-" + mes + "-" + dia; // para la base de datos

		Calendar calendario = Calendar.getInstance();
		int hora2, minutos, segundos;
		hora2 = calendario.get(Calendar.HOUR_OF_DAY);
		minutos = calendario.get(Calendar.MINUTE);
		segundos = calendario.get(Calendar.SECOND);
		String hra = hora2 + ":" + minutos + ":" + segundos;

		//ordenes sin aprobar
		if (va.equals("1")) {
			rs = metodos.BuscarOrden();
			try {
				out.print("<table width='100%' align='center'>"
								+ "<tr>"
								+ "<td id='cabecera2' class='style11' colspan='7' align='center'>ORDENES SIN APROBAR</td>"
								+ "</tr>"
								+ "<tr id='cabecera2' class='style11' align='center'>"
								+ "<td >Fecha y hora de realizacion </td>"
								+ "<td >Numero de la orden </td>"
								+ "<td >Proveedor </td>"
								+ "<td >Valor total a pagar </td>"
								+ "<td >Fecha de entrega </td>"
								+ "<td >Responsable </td>"
								+ "<td >Acciones </td>" + "</tr>");
				while (rs.next()) {
					out.print("<tr >" 
							+ "<td >"+ rs.getString(2)+ " - "+ rs.getString(3)+ "</td>"
							+ "<td align='center'>"+ rs.getString(1)+ "</td>"
							+ "<td align='center'><a href='#' onclick='Detalle_Sin_Aprobar(&quot;"+rs.getString(1)+"&quot;,&quot;"+rs.getString(4)+"&quot;)'>"+rs.getString(4)+"</a></td>"
							+ "<td align='center'>"+ rs.getString(5)+ "</td>"
							+ "<td align='center'>"+ rs.getString(6)+ "</td>"
							+ "<td align='center'>"+ rs.getString(7)+ "</td>"
							+ "<td align='center'><a href='#' onclick='Anular_Orden(&quot;"+ rs.getString(1)+ "&quot;)'>Anular </a>  / "
							+ "<a href='#' onclick='Reporte2(&quot;"+ rs.getString(1)+ "&quot;)'>Imprimir</a></td>" 
							+ "</tr>");
				}
				out.print("</table>");
				rs.getStatement().close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		
		//detalle de la orden sin aprobar
		if (va.equals("2")) {
			rs = metodos.BuscarDetalleOrden(codorden);
			try {
				out.print("<table width='100%' align='center'>"
						+ "<tr>"
						+ "<td id='cabecera2' class='style11' colspan='5' align='center'>ORDEN DE COMPRA No: "+ codorden+ "</td>"
						+ "</tr>"
						+ "<tr>"
						+ "<td id='cabecera2' class='style11' colspan='5' align='center'>PROVEEDOR: "+ proveedor+ "</td>"
						+ "</tr>"
						+ "<tr id='cabecera2' class='style11' align='center'>"
						+ "<td >Nombre del Producto: </td>"
						+ "<td >Valor Unitario ($): </td>"
						+ "<td >Cantidad: </td>"
						+ "<td >Descuento: </td>"
						+ "<td >Total: </td>"
						+ "</tr>");

				while (rs.next()) {
					out.print("<tr >" 
							+ "<td >" + rs.getString("det.producto_descripcion") + "</td>"
							+ "<td align='right'>" + rs.getString("det.valor_unitario") + "</td>"
							+ "<td align='center'>" + rs.getString("det.cantidad") + "</td>"
							+ "<td align='right'>" + rs.getString("det.descuento") + "</td>"
							+ "<td align='right'>" + rs.getString("det.subtotal") + "</td>"
							+ "</tr>");
				}

				rs1 = metodos.BuscarValorTotalOrden(codorden);
				if (rs1.next()) {
					out.print("<tr >"
							+ "<td ></td>"
							+ "<td align='center'></td>"
							+ "<td align='center'><input id='viva' type='hidden' value='"+ rs1.getString(2)+ "'/></td>"
							+ "<td align='right' ><b>I.V.A </b></td>"
							+ "<td align='right' ><b>" + rs1.getString(2)+ "</b></td>" 
							+ "</tr>");
					out.print("<tr >"
							+ "<td ></td>"
							+ "<td align='center'></td>"
						+ "<td align='center'><input id='vtotal' type='hidden' value='"+ rs1.getString(3)+ "'/></td>"
							+ "<td align='right' ><b>Valor Total</b></td>"
							+ "<td align='right' ><b>" + rs1.getString(3)+ "</b></td>" 
							+ "</tr>");
				}

				out.print("<tr>" 
						+ "<td colspan='6' align='center'><input id='guardar' type='button' value='Aprobar Orden' onClick='Aprobar_Orden(&quot;"+ codorden+ "&quot;)' class='styletxt'>"
						+ "<input id='guardar' type='button' value='Editar Orden' onClick='Editar_Orden(&quot;"+ codorden
						+ "&quot;,&quot;"+ proveedor+ "&quot;)' class='styletxt'></td>" 
						+ "</tr>"
						+ "</table>");
				rs1.getStatement().close();
				rs.getStatement().close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		//anular orden muestra interfaz
		if (va.equals("3")) {
			rs = metodos.BuscarOrdenes(codorden);
			try {
				if (rs.next()) {
					out.print("<table width='100%' align='center'>"
							+ "<tr>"
							+ "<td id='cabecera2' class='style11' colspan='8' align='center'>ANULAR ORDEN DE COMPRA No: "+ codorden+ "</td>"
							+ "</tr>"
							+ "<tr >"
							+ "<td >Proveedor:    "+ rs.getString(2)+ "</td>"
							+ "<td > </td>"
							+ "<td >Valor Total:   "+ rs.getString(3)+ "</td>"
							+ "</tr>"
							+ "<tr >"
							+ "<td >Motivo Anulacion:</td>"
							+ "<td colspan='2'><textarea id='motivo' name='motivo' cols='50' rows='5' /></textarea></td>"
							+ "</tr>");
				}
				out.print("<tr><td colspan='5' align='center'><input id='guardar' type='button' value='Anular Orden' onClick='Anular(&quot;"+ codorden+ "&quot;)' class='styletxt'></td>"
						+"</tr>"
						+ "</table>");
				rs.getStatement().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		//anular la orden
		if (va.equals("4")) {
			metodos.AnularOrden(codorden, usuario, fecha, hra, motivo);
		}
		
		
		//aprobar orden
		if (va.equals("5")) {
			metodos.AprobarOrden(codorden, usuario, fecha, hra);
		}
		
		
		if (va.equals("6")) {
			rs = metodos.BuscarDetalleOrden(codorden);
			try {
				out.print("<table width='100%' align='center'>"
						+ "<tr>"
						+ "<td id='cabecera2' class='style11' colspan='8' align='center'>ORDEN DE COMPRA No: "+ codorden+ "</td>"
						+ "<input id='orden' type='hidden' value='"+ codorden+ "'/>"
						+ "</tr>"
						+ "<tr>"
						+ "<td id='cabecera2' class='style11' colspan='8' align='center'>PROVEEDOR: "+ proveedor+ "</td>"
						+ "</tr>"
						+ "<tr id='cabecera2' class='style11' align='center'>"
						+ "<td >Nombre del Producto: </td>"
						+ "<td >Valor Unitario ($): </td>"
						+ "<td >Cantidad: </td>"
						+ "<td >Descuento: </td>"
						+ "<td >Total: </td>"
						+ "<td colspan='2' >Acciones </td>" + "</tr>");

				while (rs.next()) {
					out.print("<tr >" 
						+ "<td >"+ rs.getString(2)+ "</td>"
						+ "<td align='right'>"+ rs.getString(3)+ "</td>"
						+ "<td align='center'>"+ rs.getString(4)+ "</td>"
						+ "<td align='center'>"+ rs.getString(5)+ "</td>"
						+ "<td align='right'>"+ rs.getString(7)+ "</td>"
						+ "<td align='center'><a href='#' onclick='Actualizar_Detalle(&quot;"+ rs.getString(8)+ "&quot;,&quot;"	+ codorden+ "&quot;,&quot;"	+ proveedor+ "&quot;)'>Editar</a></td>"
						+ "<td align='center'><a href='#' onclick='Eliminar_Detalle(&quot;"+ rs.getString(8)+ "&quot;,&quot;"	+ codorden+ "&quot;,&quot;"	+ proveedor+ "&quot;)'>Omitir</a></td>" 
						+ "</tr>");
				}

				rs1 = metodos.BuscarValorTotalOrden(codorden);
				if (rs1.next()) {
					out.print("<tr >"
							+ "<td ></td>"
							+ "<td align='center'></td>"
							+ "<td align='center'><input id='viva' type='hidden' value='"+ rs1.getString(2)+ "'/></td>"
							+ "<td align='right' ><b>I.V.A</b></td>"
							+ "<td align='right' ><b>" + rs1.getString(2)+ "</b></td>"
							+ "<td align='center'></td>"
							+ "</tr>");
						out.print("<tr >"
						+ "<td ></td>"
						+ "<td align='center'></td>"
						+ "<td align='center'><input id='vtotal' type='hidden' value='"+ rs1.getString(3)+ "'/></td>"
						+ "<td align='right' ><b>Valor Total</b></td>"
						+ "<td align='right' ><b>" + rs1.getString(3)+ "</b></td>"
						+ "<td align='center'></td>"
						+ "</tr>");
						rs2 = metodos.BuscarValorTotalOrden(codorden);
						try {
							if(rs2.next()){
								metodos.ActualizarValorTotalOrden(codorden, rs2.getString(3));
								System.out.println("ActualizarValorTotalOrden("+codorden+", "+rs2.getString(3)+")");
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
				out.print("<tr>" 
						+ "<td colspan='7' align='center'><input id='guardar' type='button' value='Aprobar Orden' onClick='Aprobar_Orden(&quot;"+ codorden+ "&quot;)' class='styletxt'></td>" 
						+ "</tr>"
						+ "</table>");
				rs1.getStatement().close();
				rs.getStatement().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		// editar detalle de la orden de compra
		if (va.equals("7")) {
			rs = metodos.BuscarDetalleOrdenId(cod_detalle);
			try {
				if (rs.next()) {
					out.print("<table width='100%' align='center'>"
						+ "<tr>"
						+ "<td id='cabecera2' class='style11' colspan='8' align='center'>ORDEN DE COMPRA No: "+ codorden+ "</td>"
						+ "<input id='orden' type='hidden' value='"+ codorden+ "'/>"
						+ "</tr>"
						+ "<tr>"
						+ "<td id='cabecera2' class='style11' colspan='8' align='center'>PROVEEDOR: "+ proveedor+ "</td>"
						+ "</tr>"
						+ "<tr>"
						+ "<td id='cabecera2' class='style11' colspan='6' align='center'>ACTUALIZAR DETALLE DE LA ORDEN</td>"
						+ "</tr>"
						+ "<tr>"
						+ "<td >Producto:</td>"
						+ "<td >"+ rs.getString(2)+ "</td>"
						+ "<td >Valor Unitario($/Col):</td>"
						+ "<td >"+ rs.getString(4)+ "<input id='valor_unitario1' type='hidden' value='"+ rs.getString(4)+ "'/></td>"
						+ "<td >Iva (%):</td>"
						+ "<td >"+ rs.getString(5)+ "<input id='iva1' type='hidden' value='"+ rs.getString(5)+ "'/></td>"
						+ "</tr>"
						+ "<tr>"
						+ "<td >Cantidad:</td>"
						+ "<td ><input id='cantidad1' type='text'  style='width: 50%' class='styletxt' value='"+ rs.getString(3)+ "' onkeypress='ValidaSoloNumeros()'/></td>"
						+ "<td >Descuento (%):</td>"
						+ "<td ><input id='descuento1' type='text' style='width: 50%' class='styletxt' value='"+ rs.getString(6)+ "'/></td>"
						+ "</tr>"
						+ "<tr>" 
						+ "<td colspan='4' align='center'><input id='guardar' type='button' value='Actualizar' onClick='Update_Detalle(&quot;"
									+ rs.getString(1)+ "&quot;,&quot;"+ codorden+ "&quot;,&quot;"+ proveedor+ "&quot;)' class='styletxt'></td>" 
						+ "</tr>"
						+ "</table>");
				}
				rs.getStatement().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		//interfaz buscar orden
		if (va.equals("8")) {
			out.print("<table width='100%' align='center'>"
						+ "<tr>"
						+ "<td id='cabecera2'  colspan='3' class='style11' align='center'>BUSCAR ORDEN</td>"
						+ "</tr>"
						+ "<tr align='center'>"
						+ "<td >No Orden </td>"
						+ "<td ><input id='No_orden' type='text'/> </td>"
						+ "<td ><input type='button' value='Buscar Orden' onclick='OrdenAprobada()'/> </td>"
						+ "</tr>" 
						+ "</table>");
		}
		
		
		//orden aprobada
		if (va.equals("9")) {
			rs = metodos.BuscarOrdAprobadas(No_orden);
			try {
				out.print("<table width='100%' align='center'>"
						+ "<tr>"
						+ "<td id='cabecera2' class='style11' colspan='9' align='center'>ORDENES APROBADAS</td>"
						+ "</tr>"
						+ "<tr id='cabecera2' class='style11' align='center'>"
						+ "<td >Fecha y hora de realizacion </td>"
						+ "<td >Numero de la orden </td>"
						+ "<td >Proveedor </td>"
						+ "<td >Valor total a pagar </td>"
						+ "<td >Fecha de entrega </td>"
						+ "<td >Responsable </td>"
						+ "<td >Fecha y hora de aprobacion </td>"
						+ "<td >Responsable aprobacion </td>"
						+ "<td >Accion </td>" + "</tr>");
				
				if (rs.next()) {
				out.print("<tr >" 
						+ "<td >"+ rs.getString(2)+ " - "+ rs.getString(3)+ "</td>"
						+ "<td align='center'>"+ rs.getString(1)+ "</td>"
						+ "<td align='center'>"+ rs.getString(4)+ "</td>"
						+ "<td align='right'>"+ rs.getString(5)+ "</td>"
						+ "<td align='center'>"+ rs.getString(6)+ "</td>"
						+ "<td align='center'>"+ rs.getString(7)+ "</td>"
						+ "<td >"+ rs.getString(8)+ " - "+ rs.getString(9)+ "</td>"
						+ "<td align='center'>"+ rs.getString(10)+ "</td>"
						+ "<td align='center'><a href='#' onclick='Anular_Orden(&quot;"+ rs.getString(1)+ "&quot;)'>Anular Orden</a></td>"
						+ "</tr>");
				}
				out.print("</table>");
				rs.getStatement().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		//interfaz de consultas
		if (va.equals("10")) {
			if (val.equals("2")) {
				out.print("<table width='100%' border='0' align='center'>" 
						+ "<tr><td colspan='4' height='30' valign='top'><div align='center' class='style11' id='cabecera2'>Consulta de Ordenes</div></td>" 
						+ "</tr>" 
						+ "</table>");
				out.print("<table width='100%' border=0' class='style6' >" 
						+ "<tr BGCOLOR='#D3D3D3' >" 
						+ "<td colspan='4' height='30' valign='top'><i><div align='center'>Criterios de Busqueda</div></i></td>" 
						+ "</tr>");
				out.print("<tr></tr>" 
						+ "<tr>" 
						+ "<td width='15%'><div align='right'>No Orden:</div></td>" 
						+ "<td width='30%'><input name='txtOrden' type='text' id='txtOrden' size='39' maxlength='100' /></td>");
				out.print("<td width='15%'><div align='right'>Proveedores:</div></td>" 
						+ "<td width='40%'><select  style='width:269px' name='Pro' id='Pro' ><option value='Seleccione'>Seleccione</option>");
				rs = metodos.Proveedor();
				try {
					while (rs.next()) {
						out.print("<option title='" + rs.getString(2)+ "' value=" + rs.getString(1) + ">"+ rs.getString(2) + "</option>");
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error " + e);
					e.printStackTrace();
				}
				out.print("</td></tr>");
				out.print("<tr><td><div align='right'>Fecha Inicial:</div></td><td><input name='FI' type='text' id='FI' size='10'  onKeyup='masca(this,patron,true,0,0,0)' onblur='vfi()'; /></td><td><div align='right'>Fecha Final:</div></td><td><input name='FF' type='text' id='FF' size='10' onKeyup='masca(this,patron,true,0,0,0)' onblur='vff()'; /></td></tr><tr></tr></table>");
				out.print("<table width='100%' border='0' class='style6' ><tr><td colspan='4'><div align='center'><input name='btnCrearEntidad' type='button' id='btnCrearEntidad' value='     Consultar Ordenes    ' onclick='COrden()' /></div></td></tr><tr></tr><tr></tr></table>");
				out.print("<table width='100%' border='0' class='style6' ><tr></tr><tr BGCOLOR='#D3D3D3' ><td colspan='4' height='30' valign='top'><i><div align='center'>Resultados de Busqueda</div></i></td></tr><tr></tr></table>");
				out.print("<div id='resultadosf' style='height: 240px; overflow-y: scroll'></div>");
			}
			
			if (val.equals("3")) {
				out.print("<table width='100%' border='0' align='center'><tr><td colspan='4' height='30' valign='top'><div align='center' class='style11' id='cabecera2'>Consulta de Ordenes</div></td></tr></table>");
				out.print("<table width='100%' border=0' class='style6' ><tr BGCOLOR='#D3D3D3' ><td colspan='4' height='30' valign='top'><i><div align='center'>Criterios de Busqueda</div></i></td></tr>");
				out.print("<tr></tr><tr><td ><div align='right'>Productos:</div></td><td width='40%'><select  style='width:269px' name='Pro' id='Pro' ><option value='Seleccione'>Seleccione</option>");
				rs = metodos.Productos();
				try {
					while (rs.next()) {
						out.print("<option title='" + rs.getString(2)+ "' value=" + rs.getString(1) + ">"+ rs.getString(2) + "</option>");
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error " + e);
					e.printStackTrace();
				}
				out.print("</td><td><div align='center'><input name='btnCrearEntidad' type='button' id='btnCrearEntidad' value='     Consultar Ordenes    ' onclick='CPro()' /></div></td></tr><tr></tr><tr></tr></table>");
				out.print("<table width='100%' border='0' class='style6' ><tr></tr><tr BGCOLOR='#D3D3D3' ><td colspan='4' height='30' valign='top'><i><div align='center'>Resultados de Busqueda</div></i></td></tr><tr></tr></table>");
				out.print("<div id='resultadosf' style='height: 240px; overflow-y: scroll'></div>");
			}
			
			if (val.equals("4")) {
				out.print("<table width='100%' border='0' align='center'>" 
						+ "<tr><td colspan='4' height='30' valign='top'><div align='center' class='style11' id='cabecera2'>Consulta de Ordenes</div></td>" 
						+ "</tr>" 
						+ "</table>");
				out.print("<table width='100%' border=0' class='style6' >" 
						+ "<tr BGCOLOR='#D3D3D3' >" 
						+ "<td colspan='4' height='30' valign='top'><i><div align='center'>Criterios de Busqueda</div></i></td>" 
						+ "</tr>");
				out.print("<tr></tr>" 
						+ "<tr>" );
				out.print("<td width='15%'><div align='right'>Proveedores:</div></td>" 
						+ "<td width='40%'><select  style='width:269px' name='Pro' id='Pro' ><option value='Seleccione'>Seleccione</option>");
				rs = metodos.Proveedor();
				try {
					while (rs.next()) {
						out.print("<option title='" + rs.getString(2)+ "' value=" + rs.getString(1) + ">"+ rs.getString(2) + "</option>");
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error " + e);
					e.printStackTrace();
				}
				out.print("</td>");
				out.print("<td width='15%'><div align='right'>Productos:</div></td>" 
						+ "<td width='40%'><select  style='width:269px' name='Prod' id='Prod' ><option value='Seleccione'>Seleccione</option>");
				rs = metodos.Productos();
				try {
					while (rs.next()) {
						out.print("<option title='" + rs.getString(2)+ "' value=" + rs.getString(1) + ">"+ rs.getString(2) + "</option>");
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error " + e);
					e.printStackTrace();
				}
				out.print("</td></tr>");
				out.print("<tr><td><div align='right'>Fecha Inicial:</div></td><td><input name='FI' type='text' id='FI' size='10'  onKeyup='masca(this,patron,true,0,0,0)' onblur='vfi()'; /></td><td><div align='right'>Fecha Final:</div></td><td><input name='FF' type='text' id='FF' size='10' onKeyup='masca(this,patron,true,0,0,0)' onblur='vff()'; /></td></tr><tr></tr></table>");
				out.print("<table width='100%' border='0' class='style6' ><tr><td colspan='4'><div align='center'><input name='btnCrearEntidad' type='button' id='btnCrearEntidad' value='     Consultar Ordenes    ' onclick='CProveedor()' /></div></td></tr><tr></tr><tr></tr></table>");
				out.print("<table width='100%' border='0' class='style6' ><tr></tr><tr BGCOLOR='#D3D3D3' ><td colspan='4' height='30' valign='top'><i><div align='center'>Resultados de Busqueda</div></i></td></tr><tr></tr></table>");
				out.print("<div id='resultadosf' style='height: 240px; overflow-y: scroll'></div>");
			}
		}

		
		
		
	
		//estado de las ordenes
		if (va.equals("11")) {
			String[] fec = desde1.split("-");
			String desde2 = fec[2] + "-" + fec[1] + "-" + fec[0];

			String[] fec2 = hasta1.split("-");
			String hasta2 = fec2[2] + "-" + fec2[1] + "-" + fec2[0];
			if (estado.equals("0")) {
				rs = metodos.BuscarOrdenSinAprobar(desde2, hasta2);
				try {
					out.print("<table width='100%' align='center'>"
									+ "<tr>"
									+ "<td id='cabecera2' class='style11' colspan='7' align='center'>ORDENES EN ESTUDIO</td>"
									+ "</tr>"
									+ "<tr id='cabecera2' class='style11' align='center'>"
									+ "<td >Fecha y hora de realizacion </td>"
									+ "<td >Numero de la orden </td>"
									+ "<td >Proveedor </td>"
									+ "<td >Valor total a pagar </td>"
									+ "<td >Fecha de entrega </td>"
									+ "<td >Responsable </td>" 
									+ "</tr>");
					while (rs.next()) {
						out.print("<tr >" 
								+ "<td >" + rs.getString("fecha") + "</td>"
								+ "<td align='center'>" + rs.getString("orden.id_orden")+ "</td>" 
								+ "<td align='center'>"	+ rs.getString("provee.razon_social") + "</td>"
								+ "<td align='right'>" + rs.getString("orden.valor_total")+ "</td>" 
								+ "<td align='center'>" + rs.getString("orden.fecha_entrega") + "</td>"
								+ "<td align='center'>" + rs.getString("Responsable")+ "</td>" 
								+ "</tr>");
					}
					out.print("<tr>"
							+ "<td colspan='7' align='center'><input name='btnCrearEntidad' type='button' id='Imprimir' value='     Generar Reporte    ' onclick='Reportes(&quot;"+ desde2+ "&quot;,&quot;"+ hasta2+ "&quot;,&quot;"+ estado+ "&quot;)' /></td>"
							+ "</tr>");
					out.print("</table>");
					rs.getStatement().close();

				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else if (estado.equals("1")) {
				rs = metodos.BuscarOrdenAprobadas(desde2, hasta2);
				try {
					out.print("<table width='100%' align='center'>"
									+ "<tr>"
									+ "<td id='cabecera2' class='style11' colspan='8' align='center'>ORDENES APROBADAS</td>"
									+ "</tr>"
									+ "<tr id='cabecera2' class='style11' align='center'>"
									+ "<td >Fecha y hora de realizacion </td>"
									+ "<td >Numero de la orden </td>"
									+ "<td >Proveedor </td>"
									+ "<td >Valor total a pagar </td>"
									+ "<td >Fecha de entrega </td>"
									+ "<td >Responsable </td>"
									+ "<td >Fecha y hora de aprobacion </td>"
									+ "<td >Responsable aprobacion </td>"
									+ "</tr>");
					while (rs.next()) {
						out.print("<tr >" 
								+ "<td >" + rs.getString("fecharealizada") + "</td>"
								+ "<td align='center'>" + rs.getString("orden.id_orden")+ "</td>" 
								+ "<td align='center'>"+ rs.getString("provee.razon_social") + "</td>"
								+ "<td align='right'>" + rs.getString("orden.valor_total")+ "</td>" 
								+ "<td align='center'>"	+ rs.getString("orden.fecha_entrega") + "</td>"
								+ "<td align='center'>" + rs.getString("Responsable")+ "</td>" 
								+ "<td >" + rs.getString("fechaaprobada") + "</td>"
								+ "<td align='center'>" + rs.getString("Aprobo")+ "</td>" 
								+ "</tr>");
					}
					out.print("<tr>"
							+ "<td colspan='7' align='center'><input name='btnCrearEntidad' type='button' id='Imprimir' value='     Generar Reporte    ' onclick='Reportes(&quot;"+ desde2+ "&quot;,&quot;"+ hasta2+ "&quot;,&quot;"+ estado+ "&quot;)' /></td>"
							+ "</tr>");
					out.print("</table>");
					rs.getStatement().close();

				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else if (estado.equals("2")) {
				rs = metodos.BuscarOrdenAnuladas(desde2, hasta2);
				try {
					out.print("<table width='100%' align='center'>"
									+ "<tr>"
									+ "<td id='cabecera2' class='style11' colspan='9' align='center'>ORDENES ANULADAS</td>"
									+ "</tr>"
									+ "<tr id='cabecera2' class='style11' align='center'>"
									+ "<td >Fecha y hora de realizacion </td>"
									+ "<td >Numero de la orden </td>"
									+ "<td >Proveedor </td>"
									+ "<td >Valor total a pagar </td>"
									+ "<td >Fecha de entrega </td>"
									+ "<td >Responsable </td>"
									+ "<td >Fecha y hora de anulacion </td>"
									+ "<td >Responsable anulacion </td>"
									+ "<td >Motivo anulacion </td>" 
									+ "</tr>");
					while (rs.next()) {
						out.print("<tr >" 
								+ "<td >" + rs.getString("fecharealizada") + "</td>"
								+ "<td align='center'>" + rs.getString("orden.id_orden")+ "</td>" 
								+ "<td align='center'>" + rs.getString("provee.razon_social") + "</td>"
								+ "<td align='center'>" + rs.getString("orden.valor_total")+ "</td>" 
								+ "<td align='center'>"	+ rs.getString("orden.fecha_entrega") + "</td>"
								+ "<td align='center'>" + rs.getString("Responsable")+ "</td>" 
								+ "<td >" + rs.getString("fechaanulada") + "</td>"
								+ "<td align='center'>" + rs.getString("Anulo")+ "</td>" 
								+ "<td align='center'>" + rs.getString("motivo_anulacion") + "</td>" 
								+ "</tr>");
					}
					out.print("<tr>"
							+ "<td colspan='7' align='center'><input name='btnCrearEntidad' type='button' id='Imprimir' value='     Generar Reporte    ' onclick='Reportes(&quot;"+ desde2+ "&quot;,&quot;"+ hasta2+ "&quot;,&quot;"+ estado+ "&quot;)' /></td>"
							+ "</tr>");
					out.print("</table>");
					rs.getStatement().close();

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}else if(estado.equals("3")){
				rs = metodos.TodasOrdenes(desde2, hasta2);
				try {
					out.print("<table width='100%' align='center'>"
									+ "<tr>"
									+ "<td id='cabecera2' class='style11' colspan='9' align='center'>ORDENES</td>"
									+ "</tr>"
									+ "<tr id='cabecera2' class='style11' align='center'>"
									+ "<td >Fecha y hora de realizacion </td>"
									+ "<td >Numero de la orden </td>"
									+ "<td >Proveedor </td>"
									+ "<td >Valor total a pagar </td>"
									+ "<td >Fecha de entrega </td>"
									+ "<td >Responsable </td>"
									+ "<td >Estado </td>"
									+ "</tr>");
					while (rs.next()) {
						out.print("<tr >" 
								+ "<td >" + rs.getString("fecharealiza") + "</td>"
								+ "<td align='center'>" + rs.getString("orden.id_orden")+ "</td>" 
								+ "<td align='center'>" + rs.getString("provee.razon_social") + "</td>"
								+ "<td align='right'>" + rs.getString("orden.valor_total")+ "</td>" 
								+ "<td align='center'>"	+ rs.getString("orden.fecha_entrega") + "</td>"
								+ "<td align='center'>" + rs.getString("Responsable")+ "</td>"); 
						if(rs.getString("orden.estado").equals("0")){
							out.print("<td align='center'>En Estudio</td>");
						}		
						if(rs.getString("orden.estado").equals("1")){
							out.print("<td align='center'>Aprobada</td>");
						}
						if(rs.getString("orden.estado").equals("2")){
							out.print("<td align='center'>Anulada</td>");
						}
						 
					out.print( "</tr>");
					}
					out.print("<tr>"
							+ "<td colspan='7' align='center'><input name='btnCrearEntidad' type='button' id='Imprimir' value='     Generar Reporte    ' onclick='Reportes(&quot;"+ desde2+ "&quot;,&quot;"+ hasta2+ "&quot;,&quot;"+ estado+ "&quot;)' /></td>"
							+ "</tr>");
					out.print("</table>");
					rs.getStatement().close();

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		

		

		
		if (va.equals("12")) {
			try {
				out.print("<table width='100%' align='center'>"
						+ "<tr id='cabecera2' class='style11' align='center'>"
						+ "<td >Fecha </td>" + "<td >Numero de la orden </td>"
						+ "<td >Proveedor </td>"
						+ "<td >Valor total a pagar </td>"
						+ "<td >Responsable </td>" + "</tr>");

				if (txtOrden.equals("")) {
					if (Pro.equals("Seleccione")) {
						if ((FI.equals("")) && (FF.equals(""))) {
						} else {
							String[] fec = FI.split("-");
							String FI2 = fec[2] + "-" + fec[1] + "-" + fec[0];

							String[] fec2 = FF.split("-");
							String FF2 = fec2[2] + "-" + fec2[1] + "-"+ fec2[0];

							rs = metodos.BuscarOrd(FI2, FF2);
							while (rs.next()) {
					out.print("<tr >"
						+ "<td >"+ rs.getString(2)+ "</td>"
						+ "<td align='center'><a href='#' onclick='Reporte(&quot;"+ rs.getString(1) + "&quot;)'>"+ rs.getString(1) + "</a></td>"
						+ "<td align='center'>"+ rs.getString(4) + "</td>"
						+ "<td align='center'>"+ rs.getString(5) + "</td>"
						+ "<td align='center'>"+ rs.getString(7) + "</td>"
						+ "</tr>");
							}
						
							out.print("</table>");
							rs.getStatement().close();
							
						}
					} else {
						String sql = "";
						if ((FI.equals("")) && (FF.equals(""))) {
						} else {
							String[] fec = FI.split("-");
							String FI2 = fec[2] + "-" + fec[1] + "-" + fec[0];

							String[] fec2 = FF.split("-");
							String FF2 = fec2[2] + "-" + fec2[1] + "-"+ fec2[0];
							sql = " AND orden.fecha_realizada BETWEEN '" + FI2+ "' AND '" + FF2 + "' ";
						}
						rs = metodos.BuscarOrdProveedor(Pro, sql);
						while (rs.next()) {
							out.print("<tr >"
									+ "<td >"+ rs.getString(2)+ "</td>"
									+ "<td align='center'><a href='#' onclick='Reporte(&quot;"+ rs.getString(1) + "&quot;)'>"+ rs.getString(1) + "</a></td>"
									+ "<td align='center'>"+ rs.getString(4) + "</td>"
									+ "<td align='center'>"+ rs.getString(5) + "</td>"
									+ "<td align='center'>"+ rs.getString(7) + "</td>"
									+ "</tr>");
						}
						out.print("</table>");
						rs.getStatement().close();
					}
				} else {
					rs = metodos.BuscarOrdAprobadas(txtOrden);
					while (rs.next()) {
						out.print("<tr >"
								+ "<td >"+ rs.getString(2)+ "</td>"
								+ "<td align='center'><a href='#' onclick='Reporte(&quot;"+ rs.getString(1) + "&quot;)'>"+ rs.getString(1) + "</a></td>"
								+ "<td align='center'>"	+ rs.getString(4) + "</td>"
								+ "<td align='center'>"+ rs.getString(11) + "</td>"
								+ "<td align='center'>"+ rs.getString(10) + "</td>" 
								+ "</tr>");
					}
					out.print("</table>");
					rs.getStatement().close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		
		
		
		if (va.equals("13")) {
			try {
				rs = metodos.BuscarPrecioProducto(Pro);
				out.print("<table width='75%' align='center'>"
						+ "<tr id='cabecera2' class='style11' align='center'>"
						+ "<td width='35%'>Producto y Descripcion  </td>"
						+ "<td width='35%'>Proveedor  </td>"
						+ "<td width='15%'>Precio </td>"
						+ "<td width='15%'>Iva </td>" + "</tr>");

				while (rs.next()) {
					out.print("<tr >" 
							+ "<td >" + rs.getString(1) + "</td>"
							+ "<td >" + rs.getString("proveedor") + "</td>"
							+ "<td align='right'>" + rs.getString("pp.precio") + "</td>"
							+ "<td align='center'>" + rs.getString("iva.valor") + "</td>"
							+ "</tr>");
				}

				out.print("</table>");
				rs.getStatement().close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}