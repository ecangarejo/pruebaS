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
import cont_metodo.MetodoProveedorN;

public class ControlCrear extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ControlCrear() {
		super();
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {

		ResultSet rs = null;
		ResultSet rs1 = null;
		MetodoCrear metodos = new MetodoCrear();
		MetodoProveedorN mpn = new MetodoProveedorN();
		PrintWriter out = res.getWriter();
		String va = req.getParameter("valor");

		// variables de producto
		String nombre = req.getParameter("nombre");
		String proveedor = req.getParameter("proveedor");
		String precio = req.getParameter("precio");
		String iva = req.getParameter("iva");
		String Descripcion = req.getParameter("Descripcion");
		String producto = req.getParameter("producto");
		String fec_entrega = req.getParameter("fec_entrega");
		String plazo = req.getParameter("plazo");
		String usuario = req.getParameter("usuario");
		String nomproveedor = req.getParameter("nomproveedor");
		String nomproducto = req.getParameter("nomproducto");
		String grupo = req.getParameter("grupo");
		String subgrupo = req.getParameter("subgrupo");

		String codorden = req.getParameter("codorden");
		String cantidad = req.getParameter("cantidad");
		String valor_unitario = req.getParameter("valor_unitario");
		String descuento = req.getParameter("descuento");

		String cod_detalle = req.getParameter("cod_detalle");
		String Observacion = req.getParameter("Observacion");
		String vtotal = req.getParameter("vtotal");

		String val = req.getParameter("val");
		
		String cod = req.getParameter("cod");
		String estado = req.getParameter("estado");
		String nom_grupo = req.getParameter("nom_grupo");
		String cuenta = req.getParameter("cuenta");
		String cuentac = req.getParameter("cuentac");
		
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
		String fecha = annio + "-" + mes + "-" + dia; // para la base de dtaos

		Calendar calendario = Calendar.getInstance();
		int hora2, minutos, segundos;
		hora2 = calendario.get(Calendar.HOUR_OF_DAY);
		minutos = calendario.get(Calendar.MINUTE);
		segundos = calendario.get(Calendar.SECOND);
		String hra = hora2 + ":" + minutos + ":" + segundos;

		// buscar producto
		if (va.equals("1")) {
			rs = metodos.BuscarProducto(nombre);
			try {
				if (rs.next()) {
					out.print("1"); // se encuentra el producto
				} else {
					out.print("0");
				}
				rs.getStatement().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// AutoCompleta Proveedor
		if (va.equals("2")) {
			try {
				out.print("<table>");
				rs = metodos.AutocompletaProveedor(nomproveedor);
				while (rs.next()) {
					out.print("<tr >" +
							  "<td colspan='3'><a href='#' onclick='Seleccion(&quot;"+rs.getString(1)+"&quot;,&quot;"+rs.getString(2)+"&quot;)'>"
									+ rs.getString(2)+"</a></td></tr>");
				}
				out.print("</table>");
				rs.getStatement().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
				
		// Insertar datos producto
		if (va.equals("3")) {
			if(subgrupo.equals("0")){
				String[] n2 = grupo.split(",");
				metodos.CrearProducto(nombre,fecha,hra,n2[0],"0");
				mpn.InsertLog(fecha, hra, usuario, "INSERT INTO com_producto, nombre: "+nombre
						+" ,fecha: "+fecha+" , hra: "+hra+" ,grupo: "+n2[0]+" ,subgrupo: 0 ");
			}else{
				String[] n1 = grupo.split(",");
				String[] n2 = subgrupo.split(",");
				metodos.CrearProducto(nombre,fecha,hra,n1[0],n2[0]);
				mpn.InsertLog(fecha, hra, usuario, "INSERT INTO com_producto, nombre: "+nombre
						+" ,fecha: "+fecha+" , hra: "+hra+" ,grupo: "+n1[0]+" ,subgrupo: "+n2[0]);
			}
			//String[] n2 = grupo.split(",");
			
			
			rs = metodos.BuscarProducto(nombre);
			try {
				if (rs.next()) {
					metodos.AsignarProductoProveedor(proveedor,rs.getString("codigo"), Descripcion,precio, iva);
					mpn.InsertLog(fecha, hra, usuario, "INSERT INTO com_proveedor_producto, codigo_Proveedor: "+proveedor
							+" ,codigo producto: "+rs.getString("codigo")+" ,Descripcion producto: "+Descripcion+" ,precio: "+precio+" ,iva: "+iva+" ,estado: 0");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// generar orden de compra
		if (va.equals("4")) {
     	out.print("<table width='100%' align='center'  >" +
				     "<tr>"
					+ "<td id='cabecera2' class='style11' colspan='6' align='center'>DATOS DEL PRODUCTO</td>"
					+ "</tr>"
					+ "<tr>"
					+ "<td >Producto:</td>"
					+ "<td ><input id='nomproducto' type='text' onkeyup='AutoCompleta3()' style='width: 90%' class='styletxt' onKeyUp='this.value=this.value.toUpperCase();'/>"
					+ "<input id='producto' type='hidden' class='styletxt' /><span class='style8'> *</span>"
					+ "<div id='AutoProducto'></div></td>"
					+ "<td >Valor Unitario($/Col):</td>"
					+ "<td ><input id='precio' type='text' class='styletxt' onkeypress='ValidaSoloNumeros();txNombres();' readonly='readonly'/></td>"
					+ "<td >Iva (%):</td>"
					+ "<td ><input id='iva' type='text' class='styletxt' readonly='readonly'/></td>"
					+ "</tr>"
					+ "<tr>"
					+ "<td >Cantidad:</td>"
					+ "<td ><input id='cantidad' type='text'  class='styletxt' onkeypress='ValidaSoloNumeros()'/></td>"
					+ "<td >Descuento (%):</td>"
					+ "<td ><input id='descuento' type='text' class='styletxt' /></td>"
					+ "</tr>"
					+ "<tr><td colspan='6' align='center'><input id='guardar' type='button' value='Agregar' onClick='Guardar_detalle()' class='styletxt'></td></tr>"
				    + "</table>");
		}
		
		// AutoCompleta Producto con proveedor
		if (va.equals("5")) {
			try {
				out.print("<table>");
				rs = metodos.AutocompletaProductoPorProveedor(nomproducto,proveedor);
				while (rs.next()) {
					out.print("<tr ><td colspan='4'><a href='#' onclick='Seleccion3(&quot;"+ rs.getString(1)+ "&quot;,&quot;"
									+ rs.getString(2)+ "&quot;,&quot;"+ rs.getString(3)+ "&quot;,&quot;"+ rs.getString(4)+"-"+ rs.getString(5)
									+ "&quot;)'>"+ rs.getString(4)+"-"+ rs.getString(5)+ "</a></td></tr>");
				}
				out.print("</table>");
				rs.getStatement().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		// generar detalle
		if (va.equals("6")) {
			metodos.CrearOrden(fecha, hra, usuario, fec_entrega, proveedor,	plazo);
			rs = metodos.BuscarOrden(fecha, hra, usuario);
			try {
				if(rs.next()){
					String codigo=rs.getString(1);
				
			double subto = 0;
			double sub = (Double.valueOf(valor_unitario))* (Double.valueOf(cantidad));
			double desc = 0;
			double iv = 0;
			if (descuento != "0") {
				desc = sub * (Double.valueOf(descuento) / 100);
			}
			subto = sub - desc;
			if (iva.equals("16")) {
				iv = subto * 0.16;
			}
			metodos.CrearDetalleOrden(codigo, producto,nomproducto, cantidad,valor_unitario, descuento, String.valueOf(iv), String.valueOf(subto));
			out.print("<table><tr><td>");
			out.print("<input id='orden' type='hidden' style='width: 50%' class='styletxt' value='"+codigo+"'/>");
			out.print("</td></tr></table>");
			rs.getStatement().close();
			}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		// generar detalle
		if (va.equals("6.5")) {
		
			double subto = 0;
			double sub = (Double.valueOf(valor_unitario))* (Double.valueOf(cantidad));
			double desc = 0;
			double iv = 0;
			if (descuento != "0") {
				desc = sub * (Double.valueOf(descuento) / 100);
			}
			subto = sub - desc;
			if (iva.equals("16")) {
				iv = subto * 0.16;
			}
			metodos.CrearDetalleOrden(codorden, producto,nomproducto, cantidad,valor_unitario, descuento, String.valueOf(iv), String.valueOf(subto));
			System.out.println("CrearDetalleOrden("+codorden+", "+producto+", "+nomproducto+", "+cantidad+", "+valor_unitario+", "+descuento+", "+String.valueOf(iv)+", "+String.valueOf(subto));
			
		}
		
		
		
		if (va.equals("7")) {
			out.print("<table width='100%' align='center'  >" +
				     "<tr>"
					+ "<td id='cabecera2' class='style11' colspan='6' align='center'>DATOS DEL PRODUCTO</td>"
					+ "</tr>"
					+ "<tr>"
					+ "<td >Producto:</td>"
					+ "<td ><input id='nomproducto' type='text' onkeyup='AutoCompleta3()' style='width: 50%' class='styletxt' onKeyUp='this.value=this.value.toUpperCase();'/>"
					+ "<input id='producto' type='hidden' style='width: 50%' class='styletxt' /><span class='style8'> *</span>"
					+ "<div id='AutoProducto'></div></td>"
					+ "<td >Valor Unitario($/Col):</td>"
					+ "<td ><input id='precio' type='text' style='width: 50%' class='styletxt' onkeypress='ValidaSoloNumeros();txNombres();' readonly='readonly' ></td>"
					+ "<td >Iva (%):</td>"
					+ "<td ><input id='iva' type='text' style='width: 50%' class='styletxt' readonly='readonly' ></td>"
					+ "</tr>"
					+ "<tr>"
					+ "<td >Cantidad:</td>"
					+ "<td ><input id='cantidad' type='text'  style='width: 50%' class='styletxt' onkeypress='ValidaSoloNumeros()'/></td>"
					+ "<td >Descuento (%):</td>"
					+ "<td ><input id='descuento' type='text' style='width: 50%' class='styletxt'/></td>"
					+ "</tr>"
					+ "<tr><td colspan='6' align='center'><input id='guardar' type='button' value=' Agregar ' onClick='Guardar_det()' class='styletxt'></td></tr>"
				    + "</table>");
			
			
			
			rs = metodos.BuscarDetalleOrden(codorden);
			try {
				out.print("<table width='100%' align='center'>"
								+ "<tr>"
								+ "<td id='cabecera2' class='style11' colspan='7' align='center'>DETALLE DE LA ORDEN</td>"
								+ "</tr>"
								+ "<tr id='cabecera2' class='style11' align='center'>"
								+ "<td width='40%'>Nombre del Producto: </td>"
								+ "<td width='10%'>Valor Unitario ($): </td>"
								+ "<td width='8%'>Cantidad: </td>"
								+ "<td width='8%'>Descuento: </td>"
								+ "<td width='10%'>SubTotal: </td>"
								+ "<td colspan='2' width='10%'>Acciones </td>" + "</tr>");

				while (rs.next()) {
					out.print("<tr >" + 
							  "<td >"+ rs.getString("det.producto_descripcion")+"</td>"
							+ "<td align='right'>"+ rs.getString("det.valor_unitario")+ "</td>"
							+ "<td align='center'>"+ rs.getString("det.cantidad")+ "</td>"
							+ "<td align='center'>"+ rs.getString("det.descuento")+ " % </td>"
							+ "<td align='right'>"+ rs.getString("det.subtotal")+ "</td>"
							+ "<td align='center'><a href='#' onclick='Actualizar_Detalle(&quot;"+ rs.getString("det.id_detalle_orden")+"&quot;)'>Editar</a></td>"
							+ "<td align='center'><a href='#' onclick='Eliminar_Detalle(&quot;"+ rs.getString("det.id_detalle_orden")+ "&quot;)'>Omitir</a></td>" 
							+ "</tr>");
				}

				rs1 = metodos.BuscarValorTotalOrden(codorden);
				if (rs1.next()) {
					out.print("<tr >"
							+ "<td ></td>"
							+ "<td align='center'></td>"
							+ "<td align='center'><input id='viva' type='hidden' value='"	+ rs1.getString(2)+ "'/></td>"
							+ "<td align='center' ><b>I.V.A</b></td>"
							+ "<td align='right' ><b>" + rs1.getString(2)
							+ "</b></td>" 
							+ "<td align='center'></td>"
							+ "<td align='center'></td>"
							+ "</tr>");
					out.print("<tr >"
									+ "<td ></td>"
									+ "<td align='center'></td>"
									+ "<td align='center'><input id='vtotal' type='hidden' value='"	+ rs1.getString(3)+ "'/></td>"
									+ "<td align='center' ><b>Valor Total</b></td>"
									+ "<td align='right' ><b>" + rs1.getString(3)
									+ "</b></td>"
									+ "<td align='center'></td>"
									+ "<td align='center'></td>"
									+ "</tr>");
				}
				out.print("<tr><td colspan='4' align='center'><input id='guardar' type='button' value='Finalizar Orden' onClick='Finalizar_Orden()' class='styletxt'></td>" +
						 "<td colspan='3' align='center'><input id='guardar' type='button' value='Cancelar' onClick='cancelar(&quot;"+ codorden+"&quot;)' class='styletxt'></td></tr>"
						+"</tr>"
								+ "</table>");
				rs1.getStatement().close();
				rs.getStatement().close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		// generar orden de compra
		if (va.equals("8")) {
			rs = metodos.BuscarDetalleOrdenId(cod_detalle);
			try {
				if (rs.next()) {
					if(rs.getString("pp.com_iva_codigo").equals("1")){
						iva="0";
					}else{
						iva="16";
					}
					out.print("<table width='100%' align='center'>"
								+ "<tr>"
								+ "<td id='cabecera2' class='style11' colspan='8' align='center'>ORDEN DE COMPRA No: "+ codorden+ "</td>"
								+ "<input id='orden' type='hidden' value='"+ codorden+ "'/>"
								+ "</tr>"
								+ "<tr>"
								+ "<td id='cabecera2' class='style11' colspan='6' align='center'>ACTUALIZAR DETALLE DE LA ORDEN</td>"
								+ "</tr>"
								+ "<tr>"
								+ "<td >Producto:</td>"
								+ "<td >"+ rs.getString("det.producto_descripcion")+ "</td>"
								+ "<td >Valor Unitario($/Col):</td>"
								+ "<td >"+ rs.getString("det.valor_unitario")+ "<input id='valor_unitario1' type='hidden' value='"	+ rs.getString("det.valor_unitario")+ "'/></td>"
								+ "<td >Iva (%):</td>"
								+ "<td >"+ rs.getString("det.iva")+ "<input id='iva1' type='hidden' value='"+ iva+ "'/></td>"
								+ "</tr>"
								+ "<tr>"
								+ "<td >Cantidad:</td>"
								+ "<td ><input id='cantidad1' type='text'  style='width: 50%' class='styletxt' value='"+ rs.getString("det.cantidad")+ "' onkeypress='ValidaSoloNumeros()'/></td>"
								+ "<td >Descuento (%):</td>"
								+ "<td ><input id='descuento1' type='text' style='width: 50%' class='styletxt' value='"+ rs.getString("det.descuento")+ "'/></td>"
								+ "</tr>"
								+ "<tr>" 
								+ "<td colspan='4' align='center'><input id='guardar' type='button' value='Actualizar' onClick='Update_Detalle(&quot;"+cod_detalle+ "&quot;)' class='styletxt'></td>" 
								+ "</tr>"
								+ "</table>");
				}
				rs.getStatement().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		if (va.equals("9")) {
			double subto = 0;
			double sub = (Double.valueOf(valor_unitario))* (Double.valueOf(cantidad));
			double desc = 0;
			double iv = 0;
			if (descuento != "0") {
				desc = sub * (Double.valueOf(descuento) / 100);
				
			} else {
				desc = 0;
			}
			subto = sub - desc;
			if (iva.equals("16")) {
				iv = subto * 0.16;
			}else{
				iv = 0;
			}
			
			System.out.println(cod_detalle+" , "+cantidad+" , "+String.valueOf(desc)+" , "+String.valueOf(iv)+" , "+String.valueOf(subto));
			metodos.ActualizarDetalle(cod_detalle, cantidad, String.valueOf(desc), String.valueOf(iv), String.valueOf(subto));
			mpn.InsertLog(fecha, hra, usuario, "UPDATE com_detalle_orden SET cantidad="+cantidad+", descuento="+descuento+", iva="+iva+", subtotal="+String.valueOf(subto)+" WHERE id_detalle_orden="+cod_detalle);
			
		}
		
		
		if (va.equals("10")) {
			metodos.EliminarDetalle(cod_detalle);
		
		}
		
		

		if (va.equals("11")) {
			out.print("<table width='100%' align='center'>"
							+ "<tr>"
							+ "<td id='cabecera2' class='style11' colspan='5' align='center'>OBSERVACION</td>"
							+ "</tr>"
							+ "<tr align='center'>"
							+ "<td >Observacion: </td>"
							+ "<td colspan='4'><textarea id='Observacion' name='Observacion' cols='50' rows='5' /></textarea> </td>"
							+ "</tr>"
							+ "<tr><td colspan='5' align='center'><input id='guardar' type='button' value='Finalizar Orden' onClick='Finalizar()' class='styletxt'></td></tr>"
							+ "</table>");
		}
		
		if (va.equals("12")) {
			metodos.FinalizarOrden(codorden, Observacion, vtotal);
		}
		
		if (va.equals("13")) {
			metodos.EliminarOrden(codorden);
		}
		

		// AutoCompleta Producto
		if (va.equals("14")) {
			try {
				out.print("<table>");
				rs = metodos.AutocompletaProducto(nomproducto);
				while (rs.next()) {
					out.print("<tr collspan='3'><td><a href='#' onclick='Seleccion2(&quot;"
									+ rs.getString("codigo")+ "&quot;,&quot;"+ rs.getString("nombre")+ "&quot;)'>"
									+ rs.getString("nombre") + "</a></td></tr>");
				}
				out.print("</table>");
				rs.getStatement().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		

		// buscar producto y proveedor
		if (va.equals("15")) {
			rs = metodos.BuscarProductoProveedor(producto, proveedor);
			try {
				if (rs.next()) {
					out.print("<table width='100%' align='center'>"
									+ "<tr>"
									+ "<td >Precio:</td>"
									+ "<td ><input id='precio' type='text' style='width: 50%' class='styletxt' onkeypress='ValidaSoloNumeros()' value='"
									+ rs.getString("precio")
									+ "'/><span class='style8'> *</span></td>"
									+ "<td >IVA:</td>"
									+ "<td > <select name='iva' size='1' id='iva'  style='width: 50%'>");
					if ((rs.getString("com_iva_codigo")).equals("1")) {
						out.print("<option value='1'>0</option>");
						out.print("<option value='2'>16</option>");
					} else if ((rs.getString("com_iva_codigo")).equals("2")) {
						out.print("<option value='2'>16</option>");
						out.print("<option value='1'>0</option>");
					}
					out.print("</select><span class='style8'> *</span></td>"
									+ "</tr>"
									+ "<tr>" 
									+ "<td >Descripcion:</td>" 
									+ "<td colspan='4'><textarea id='Descripcion' name='Descripcion' cols='50' rows='5' onKeyUp='this.value=this.value.toUpperCase();' value='"+rs.getString("descripcion_producto")+"'/></textarea></td>" 
									+ "</tr>" 
									+ "<tr><td colspan='4' align='center'><input id='guardar' type='button' value='Actualizar' onClick='Asignar_Producto(2)' class='styletxt'></td>"
									+ "</tr>" 
									+ "</table>");
				} else {
					out.print("<table width='100%' align='center'>"
									+ "<tr>"
									+ "<td >Precio:</td>"
									+ "<td ><input id='precio' type='text' style='width: 50%' class='styletxt' onkeypress='ValidaSoloNumeros()'/><span class='style8'> *</span></td>"
									+ "<td >IVA:</td>"
									+ "<td > <select name='iva' size='1' id='iva'  style='width: 50%'>");

					try {
						rs1 = metodos.BuscarIva();
						while (rs1.next()) {
							out.print("<option value='" + rs1.getString(1)+ "'>" + rs1.getString(2) + "</option>");
						}
						rs1.getStatement().close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					out.print("</select><span class='style8'> *</span></td>"
									+ "</tr>"
									+ "<tr><td colspan='4' align='center'><input id='guardar' type='button' value='Guardar' onClick='Asignar_Producto(1)' class='styletxt'></td>"
									+ "</tr>" + "</table>");
				}
				rs.getStatement().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// Insertar Asignar precio
		if (va.equals("16")) {
			metodos.AsignarProductoProveedor(proveedor, producto,Descripcion, precio, iva);
			mpn.InsertLog(fecha, hra, usuario, "INSERT INTO com_proveedor_producto, codigo_Proveedor: "+proveedor
					+" ,codigo producto: "+producto+" ,descripcion: "+Descripcion+" ,precio: "+precio+" ,iva: "+iva+" ,estado: 0");
			System.out.println("INSERT INTO com_proveedor_producto, codigo_Proveedor: "+proveedor
					+" ,codigo producto: "+producto+" ,descripcion: "+Descripcion+" ,precio: "+precio+" ,iva: "+iva+" ,estado: 0");
		}

		// Insertar Asignar precio
		if (va.equals("17")) {
			metodos.ActualizarProductoProveedor(producto, proveedor, Descripcion,precio,iva);
			mpn.InsertLog(fecha, hra, usuario, "UPDATE com_proveedor_producto SET precio='"+precio+"', com_iva_codigo='"+iva+"', " +
					"descripcion_producto='"+Descripcion+"' WHERE com_proveedor_codigo="+proveedor+" AND com_producto_codigo="+producto);
		}
		
		
		//interfaz de consultas
		if (va.equals("18")) {
			if (val.equals("1")) {
				out.print("<table width='100%' >" +
						    "<tr>" +
						    "<td id='cabecera2' class='style11' colspan='6' align='center'>CREAR PRODUCTO </td>" +
						    "</tr>" +
						    "<tr >" +
						    "<td >Nombre:</td>" +
						    "<td ><input id='nombre' type='text' style='width: 80%' class='styletxt' onblur='buscar()' onKeyUp='this.value=this.value.toUpperCase();'/><span class='style8'> *</span></td>" +
						    "<td >Descripcion:</td>" +
							"<td colspan='3'><textarea id='Descripcion' name='Descripcion' cols='50' rows='2' onKeyUp='this.value=this.value.toUpperCase();'/></textarea><span class='style8'> *</span></td>" +
						    "</tr><tr>" +
						    "<td >Grupo:</td>" +
						    "<td > <select name='grupo' size='1' id='grupo'  style='width: 50%' onchange='buscarsubgrupo()'>" +
						    "<option selected='selected'>SELECCIONE</option>");
						try {
							rs1 = metodos.BuscarGrupos();
							while (rs1.next()) {
							out.print("<option value='"+rs1.getString("codigo")+","+rs1.getString("cuenta")+","+rs1.getString("cod_cuenta")+"'>"+rs1.getString("nombre")+"</option>" );
							}
							rs1.getStatement().close();
						} catch (SQLException e) {
							System.out.println(e);
							out.println("no se pudo realizar la conexion!<br/>");
						}
				out.print("</select><span class='style8'> *</span></td><td colspan='2'><div id='subgrupo'></td></div>" +
							"</tr>" +
						    "<tr>" +
						    "<td >Proveedor:</td>" +
						    "<td ><input id='nomproveedor' type='text' onkeyup='AutoCompleta()' style='width: 80%' class='styletxt' onKeyUp='this.value=this.value.toUpperCase();'/>" +
						    "<input id='proveedor' type='hidden' class='styletxt' /><span class='style8'> *</span>" +
						    "<div id='AutoProveedor'></div></td>" +
						    "<td >Precio:</td>" +
						    "<td ><input id='precio' type='text' class='styletxt' onkeypress='ValidaSoloNumeros()' /><span class='style8'> *</span></td>" +
						    "<td >IVA:</td>" +
						    "<td > <select name='iva' size='1' id='iva'  >" +
						    "<option selected='selected'>SELECCIONE</option>");
						try {
							rs = metodos.BuscarIva();
							while (rs.next()) {
							out.print("<option value='"+rs.getString(1)+"'>"+rs.getString(2)+"</option>" );
							}
							rs.getStatement().close();
						} catch (SQLException e) {
							System.out.println(e);
							out.println("no se pudo realizar la conexion!<br/>");
						}
				out.print("</select><span class='style8'> *</span></td>" +
						"</tr>" +
						"</tr>" +
						"<tr>" +
						
						"</tr>" +
						"<tr>" +
						"<td colspan='4' align='center'><div id='btguardar'><input id='guardar' type='button' value='Guardar' onClick='Crear_Producto(&quot;1&quot;)' class='styletxt'></div></td>" +
						"</tr>" +
						"</table>");
			}
			
			
			if (val.equals("2")) {
				out.print("<table width='100%' >" +
						    "<tr>" +
						    "<td id='cabecera2' class='style11' colspan='6' align='center'>AGREGAR DESCRIPCION A UN PRODUCTO </td>" +
						    "</tr>" +
						    "<tr >" +
						    "<td >Nombre:</td>" +
						    "<td ><input id='nomproducto' type='text' onkeyup='AutoCompleta2()' style='width: 50%' class='styletxt' onKeyUp='this.value=this.value.toUpperCase();'/>" +
							"<input id='producto' type='hidden' style='width: 50%' class='styletxt' /><span class='style8'> *</span>" +
							"<div id='AutoProducto'></div></td>" +
						    "<td >Descripcion:</td>" +
							"<td colspan='3'><textarea id='Descripcion' name='Descripcion' cols='50' rows='2' onKeyUp='this.value=this.value.toUpperCase();'/></textarea><span class='style8'> *</span></td>" +
						    "</tr>" +
						    "<tr>" +
						    "<td >Proveedor:</td>" +
						    "<td ><input id='nomproveedor' type='text' onkeyup='AutoCompleta()' style='width: 80%' class='styletxt' onKeyUp='this.value=this.value.toUpperCase();'/>" +
						    "<input id='proveedor' type='hidden' class='styletxt' /><span class='style8'> *</span>" +
						    "<div id='AutoProveedor'></div></td>" +
						    "<td >Precio:</td>" +
						    "<td ><input id='precio' type='text' class='styletxt' onkeypress='ValidaSoloNumeros()' /><span class='style8'> *</span></td>" +
						    "<td >IVA:</td>" +
						    "<td > <select name='iva' size='1' id='iva'  >" +
						    "<option selected='selected'>SELECCIONE</option>");
						try {
							rs = metodos.BuscarIva();
							while (rs.next()) {
							out.print("<option value='"+rs.getString(1)+"'>"+rs.getString(2)+"</option>" );
							}
							rs.getStatement().close();
						} catch (SQLException e) {
							System.out.println(e);
							out.println("no se pudo realizar la conexion!<br/>");
						}
				out.print("</select><span class='style8'> *</span></td>" +
						"</tr>" +
						"</tr>" +
						"<tr>" +
						
						"</tr>" +
						"<tr>" +
						"<td colspan='4' align='center'><input id='guardar' type='button' value='Guardar' onClick='CrearNuevaDescripcion()' class='styletxt'></td>" +
						"</tr>" +
						"</table>");
			}
			
			
			
			
			if (val.equals("3")) {
				out.print("<table width='100%' border='0' align='center'><tr><td colspan='4' height='30' valign='top'><div align='center' class='style11' id='cabecera2'>Consulta de Ordenes</div></td></tr></table>");
				out.print("<table width='100%' border=0' class='style6' ><tr BGCOLOR='#D3D3D3' ><td colspan='4' height='30' valign='top'><i><div align='center'>Criterios de Busqueda</div></i></td></tr>");
				out.print("<tr></tr><tr><td >Proveedor:</td>" +
						"<td ><input id='nomproveedor' type='text' onKeyUp='AutoCompleta()' style='width: 50%' class='styletxt' onKeyUp='this.value=this.value.toUpperCase();'/> <input id='proveedor' type='hidden' style='width: 50%' class='styletxt' /><span class='style8'> *</span>" +
						"<div id='AutoProveedor'></div></td>");
				out.print("<td >Nombre Producto:</td>" +
						"<td ><input id='nompro' type='text' style='width: 50%' class='styletxt' onKeyUp='this.value=this.value.toUpperCase();'/> </tr>");
				out.print("<tr><td colspan='4'><div align='center'><input name='btnCrearEntidad' type='button' id='btnCrearEntidad' value='     Consultar    ' onclick='CPP()' /></div></td></tr><tr></tr><tr></tr></table>");
				out.print("<table width='100%' border='0' class='style6' ><tr></tr><tr BGCOLOR='#D3D3D3' ><td colspan='4' height='30' valign='top'><i><div align='center'>Resultados de Busqueda</div></i></td></tr><tr></tr></table>");
				out.print("<div id='resultadosf' style='height: 240px; overflow-y: scroll'></div>");
			}
		}

		// AutoCompleta Producto
		if (va.equals("19")) {
			rs = metodos.ProveedorProducto(proveedor, producto);
			try {
				out.print("<table width='100%' align='center'>"
								+ "<tr>"
								+ "<td id='cabecera2' class='style11' colspan='4' align='center'>Productos</td>"
								+ "</tr>"
								+ "<tr id='cabecera2' class='style11' align='center'>"
								+ "<td >Nombre </td>"
								+ "<td >Descripcion </td>"
								+ "<td >Precio </td>"
								+ "<td >Estado </td>"
								+ "</tr>");
				while (rs.next()) {
					out.print("<tr >" 
							+ "<td >" + rs.getString(2) + "</td>"
							+ "<td >" + rs.getString(3)+ "</td>" 
							+ "<td align='center'>" + rs.getString(4) + "</td>");
					if(rs.getString(5).equals("0")){
						out.print("<td align='center'><a href='#' onclick='PP(&quot;"
									+ rs.getString(1)+ "&quot;,&quot;1&quot;)'> Inactivar </a></td>");
					}else{
						out.print("<td align='center'><a href='#' onclick='PP(&quot;"
								+ rs.getString(1)+ "&quot;,&quot;0&quot;)'> Habilitar </a></td>");
					}
					out.print( "</tr>");
				}
				out.print("</table>");
				rs.getStatement().close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (va.equals("20")) {
			metodos.EstadoProducto(cod, estado);
		}
		
		if (va.equals("21")) {
			out.print("<table width='100%' border='0' align='center'><tr><td colspan='4' height='30' valign='top'><div align='center' class='style11' id='cabecera2'>Asignar Precio</div></td></tr></table>");
			out.print("<table width='100%' border=0' class='style6' ><tr BGCOLOR='#D3D3D3' ><td colspan='5' height='30' valign='top'><i><div align='center'>Criterios de Busqueda</div></i></td></tr>");
			out.print("<tr></tr><tr><td >Proveedor:</td>" +
					"<td ><input id='nomproveedor' type='text' onKeyUp='AutoCompleta()' style='width: 100%' class='styletxt' onKeyUp='this.value=this.value.toUpperCase();'/> <input id='proveedor' type='hidden' class='styletxt' /><span class='style8'> *</span>" +
					"<div id='AutoProveedor'></div></td>");
			out.print("<td >Nombre Producto:</td>" +
					"<td ><input id='nompro' type='text' style='width: 100%' class='styletxt' onKeyUp='this.value=this.value.toUpperCase();'/> <td ><div align='center'><input name='btnCrearEntidad' type='button' id='btnCrearEntidad' value='     Consultar    ' onclick='PPP()' /></div></td></tr><tr></tr><tr></tr></table>");
			out.print("<table width='100%' border='0' class='style6' ><tr></tr><tr BGCOLOR='#D3D3D3' ><td colspan='5' height='30' valign='top'><i><div align='center'>Resultados de Busqueda</div></i></td></tr><tr></tr></table>");
			out.print("<div id='resultadosf' style='height: 550px; overflow-y: scroll'></div>");
		}
		
		
		// AutoCompleta Producto
		if (va.equals("22")) {
			
			try {
				out.print("<table width='100%' align='center'>"
								+ "<tr>"
								+ "<td id='cabecera2' class='style11' colspan='5' align='center'>Productos</td>"
								+ "</tr>"
								+ "<tr id='cabecera2' class='style11' align='center'>"
								+ "<td >Nombre </td>"
								+ "<td >Descripcion </td>"
								+ "<td >Precio </td>"
								+ "<td >Iva </td>"
								+ "<td >Estado </td>"
								+ "</tr>");
			
				int linea = 0;
				rs = metodos.ProveedorProducto(proveedor, producto);
				
				while(rs.next()){
					System.out.println("linea: "+linea);
					System.out.println(rs.getString(1));
				out.print("<tr>");
				out.print("<td >"+rs.getString("nombre")+"<input id='Prod"+linea+"' type='hidden' value='"+rs.getString("pp.codigo")+"'/></td>");
				out.print("<td ><textarea id='desc"+linea+"' name='Descripcion' cols='50' rows='1' onKeyUp='this.value=this.value.toUpperCase();' />"+rs.getString("descripcion_producto")+"</textarea></td>");
				out.print("<td align='center'><input id='prec"+linea+"' type='text' style='width: 80%;border: 1;align:center' value='"+rs.getString("precio")+"'/></td>");
				out.print("<td align='center'><select name='iva' size='1' id='iva"+linea+"'  style='width: 100%'>" );
				if ((rs.getString("com_iva_codigo")).equals("1")) {
					out.print("<option value='1'>0</option>");
					out.print("<option value='2'>16</option>");
				} else if ((rs.getString("com_iva_codigo")).equals("2")) {
					out.print("<option value='2'>16</option>");
					out.print("<option value='1'>0</option>");
				}
	
				out.print("</select></td>");
				
				
				out.print("<td align='center'><a  href='#' onclick='Asignar_Producto(&quot;"+linea+"&quot;,&quot;2&quot;)'>Actualizar</a></td>");
				out.print("</tr>");
				
				linea++;
				}
				rs.getStatement().getConnection().close();
			
			rs1 =metodos.SinProveedorProducto(proveedor, producto);
			while(rs1.next()){
				System.out.println("linea: "+linea);
				System.out.println(rs1.getString(1));
				out.print("<tr>");
				out.print("<td >"+rs1.getString("nombre")+"<input id='Prod"+linea+"' type='hidden' value='"+rs1.getString("codigo")+"'/></td>");
				out.print("<td ><textarea id='desc"+linea+"' name='Descripcion' cols='50' rows='1' onKeyUp='this.value=this.value.toUpperCase();' /></textarea></td>");
				out.print("<td align='center'><input id='prec"+linea+"' type='text' style='width: 80%;border: 1;align:center' /></td>");
				out.print("<td align='center'><select name='iva' size='1' id='iva"+linea+"'  style='width: 100%'>" +
						"<option value='Seleccione'>Seleccione</option>" );
				rs = metodos.BuscarIva();
				while (rs.next()) {
					out.print("<option value='" + rs.getString(1)+ "'>" + rs.getString(2) + "</option>");
				}
				rs .getStatement().close();
	
				out.print("</select></td>");
				
				
				out.print("<td align='center'><a  href='#' onclick='Asignar_Producto(&quot;"+linea+"&quot;,&quot;1&quot;)'>Asignar</a></td>");
				out.print("</tr>");
				linea++;
			}
			rs1.getStatement().getConnection().close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		if(va.equals("23")){
			out.print("<table>");
			out.print("<table width='100%' align='center'  >" +
					"<tr>" +
					"<td id='cabecera2' class='style11' colspan='3' align='center'>CREAR GRUPO DE PRODUCTOS </td>" +
					"</tr>");
			out.print("<tr>");
			out.print("<td align='center'>Nombre del grupo: <input id='nom_grupo' type='text' style='width: 60%' onblur='verificar()' onKeyUp='this.value=this.value.toUpperCase();'/></td>");
			out.print("<td align='center'>Cuenta: " +
					"<input id='cuentab' type='text' onKeyUp='AutoCompletas()' style='width: 50%' class='styletxt' onKeyUp='this.value=this.value.toUpperCase();'/> " +
					"<input id='cuenta' type='hidden' style='width: 50%' class='styletxt' />" +
					"<input id='cuentac' type='hidden' style='width: 50%' class='styletxt' /><span class='style8'> *</span>" +
					"<div id='AutoCuenta'></div></td>");
			out.print("<td align='center'><input name='btnCrearEntidad' type='button' id='btnCrearEntidad' value='     Crear    ' onclick='Crear_grupo()' /></td>");
			out.print("</tr>");
			out.print("</table>");
		}
		
		if(va.equals("24")){
			out.print("<table>");
			out.print("<tr id='cabecera2' class='style11' align='center'>");
			out.print("<td colspan='2' >Nombre del grupo</td>");
			out.print("<td >Cuenta: </td>");
			out.print("</tr>");
			out.print("<tr>");
			
			out.print("<tr>");
			out.print("<td ></td>");
			out.print("<td align='center'> </td>");
			out.print("</tr>");
			
			
			rs = metodos.BuscarGrupos();
			try {
				while (rs.next()) {
					out.print("<tr>");
					out.print("<td width='40%'  colspan='2'>"+ rs.getString("nombre")+"</td>");
					out.print("<td width='30%' align='center'>"+ rs.getString("cuenta")+" </td>");
					out.print("</tr>");
					
					rs1=metodos.BuscarsubGrupos(rs.getString("codigo"));
						while (rs1.next()){
							out.print("<tr>");
							out.print("<td width='10%'></td><td width='30%'>"+ rs1.getString("Nombre")+"</td>");
							out.print("<td width='30%' align='center'>"+ rs1.getString("Cuenta")+" </td>");
							out.print("</tr>");
						}
				}
			
			rs.getStatement().close();	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			out.print("</tr>");
			out.print("</table>");
		}
		
		if(va.equals("25")){
			metodos.CrearGrupoProducto(nom_grupo, cuenta,cuentac);
			mpn.InsertLog(fecha, hra, usuario, "INSERT INTO cont_producto_cuenta(nombre,cuenta,cod_cuenta) "
					+ "VALUES("+nom_grupo+","+cuenta+","+cuentac+")");
		}
		
		if(va.equals("26")){
			rs= metodos.BuscarGruposN(nom_grupo);
			try {
				if(rs.next()){
					out.print("1");
				}
				rs.getStatement().close();	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if (va.equals("27")) {
			try {
				out.print("<table>");
				rs = metodos.AutocompletaCuenta(cuenta);
				while (rs.next()) {
					out.print("<tr collspan='3'>" +
							  "<td><a href='#' onclick='Seleccionc(&quot;"+rs.getString("codigo")+"&quot;,&quot;"+rs.getString("CodigoCuenta")+"&quot;,&quot;"+rs.getString("NombreCuenta")+"&quot;)'>"
									+ rs.getString("CodigoCuenta")+"-"+rs.getString("NombreCuenta")+"</a></td></tr>");
				}
				out.print("</table>");
				rs.getStatement().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(va.equals("28")){
			out.print("<table>");
			out.print("<table width='100%' align='center'  >" +
					"<tr>" +
					"<td id='cabecera2' class='style11' colspan='4' align='center'>CREAR SUBGRUPOS DE PRODUCTOS </td>" +
					"</tr>");
			out.print("<tr>");
			out.print("<td >Grupos: </td><td><select size='1' id='grupo'  style='width: 50%'>" +
			"<option value='Seleccione'>Seleccione</option>" );
	rs = metodos.BuscaGrupos();
	try {
		while (rs.next()) {
			out.print("<option value='" + rs.getString("codigo")+ "'>" + rs.getString("nombre") + "</option>");
		}
		rs .getStatement().close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	

	out.print("</select></td></tr>");
			out.print("<tr><td >Nombre del grupo: </td><td><input id='nom_grupo' type='text' style='width: 50%' onKeyUp='this.value=this.value.toUpperCase();'/></td>");
			out.print("<td align='right'>Cuenta: </td><td>" +
					"<input id='cuentab' type='text' onKeyUp='AutoCompletas()' style='width: 80%' class='styletxt' onKeyUp='this.value=this.value.toUpperCase();'/> " +
					"<input id='cuenta' type='hidden' class='styletxt' />" +
					"<input id='cuentac' type='hidden' style='width: 50%' class='styletxt' /><span class='style8'> *</span>" +
					"<div id='AutoCuenta'></div></td></tr>");
			out.print("<tr ><td align='center' colspan='5'><input name='btnCrearEntidad' type='button' id='btnCrearEntidad' value='     Crear    ' onclick='Crear_subgrupo()' /></td>");
			out.print("</tr>");
			out.print("</table>");
		}
		
		if(va.equals("29")){
			metodos.CrearSubGrupoProducto(grupo, nom_grupo, cuenta,cuentac);
			mpn.InsertLog(fecha, hra, usuario, "INSERT INTO cont_subproducto_cuenta(Cod_Producto_Cuenta,Nombre,Cuenta,Cod_Cuenta) "
					+ "VALUES("+grupo+","+nom_grupo+","+cuenta+","+cuentac+")");
		}
		
		if(va.equals("30")){
			metodos.AsignarProductoProveedor(proveedor,producto, Descripcion,precio, iva);
			mpn.InsertLog(fecha, hra, usuario, "INSERT INTO com_proveedor_producto, codigo_Proveedor: "+proveedor
					+" ,codigo producto: "+producto+" ,Descripcion producto: "+Descripcion+" ,precio: "+precio+" ,iva: "+iva+" ,estado: 0");

		}
		
		if(va.equals("31")){
			String[] n = grupo.split(",");
			
			
			rs= metodos.BuscaSubGrupos(n[0]);
			rs1= metodos.BuscaSubGrupos(n[0]);
			try {
				if(rs1.next()){
					out.print("<td>Subgrupo: </td><td><select size='1' id='subgrupos'  style='width: 50%'>" +
							"<option selected='selected'>Seleccione</option>");
				while (rs.next()) {
					out.print("<option value='" +rs.getString("Codigo")+","+ rs.getString("Cuenta")+ ","+ rs.getString("Cod_Cuenta")+ "'>" + rs.getString("nombre") + "</option>");
				}
				out.print("</select></td>");
				rs .getStatement().close();
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
	}
}