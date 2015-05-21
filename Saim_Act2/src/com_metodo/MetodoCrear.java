package com_metodo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import adm_logica.Conexion;

public class MetodoCrear {
	/*INSERT*/

	// Insertar proveedor
	public void CrearProveedor(String nit, String razon_social,String direccion, String telefono, String contacto,
								   String clase_contribuyente, String fax, String email,String tiempo_entrega, String cod_dpto_fk, String cod_muni_fk) {
		try {
			PreparedStatement ps = null;
			Conexion con = new Conexion();
			ps = con.conn.prepareStatement("INSERT INTO com_proveedor(nit,razon_social,direccion,telefono,"
							+ "contacto,clase_contribuyente,fax,email,tiempo_entrega,cod_dpto_fk,cod_muni_fk,estado) "
							+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,0)");
	
			ps.setString(1, nit);
			ps.setString(2, razon_social);
			ps.setString(3, direccion);
			ps.setString(4, telefono);
			ps.setString(5, contacto);
			ps.setString(6, clase_contribuyente);
			ps.setString(7, fax);
			ps.setString(8, email);
			ps.setString(9, tiempo_entrega);
			ps.setString(10, cod_dpto_fk);
			ps.setString(11, cod_muni_fk);

			ps.execute();
			ps.close();
			con.cerrar();
		} catch (Exception ex) {
			ex.getMessage();
			System.out.print("Error Crear Proveedor " + ex);
		}
	}
	
	// Insertar producto
	public void CrearProducto(String nombre,  String fecha,String hra, String grupo,String subgrupo) {
		try {
			PreparedStatement ps = null;
			Conexion con = new Conexion();
			ps = con.conn.prepareStatement("INSERT INTO com_producto(nombre,estado,fecha,hora,grupo,subgrupo) VALUES(?,0,?,?,?,?)");
	
			ps.setString(1, nombre);
			ps.setString(2, fecha);
			ps.setString(3, hra );
			ps.setString(4, grupo );
			ps.setString(5, subgrupo );

			ps.execute();
			ps.close();
			con.cerrar();
		} catch (Exception ex) {
			ex.getMessage();
			System.out.print("Error Crear Proveedor " + ex);
		}
	}
	
	// Insertar proveedor y producto
	public void AsignarProductoProveedor(String com_proveedor_codigo, String com_producto_codigo,String descripcion_producto,String precio, String com_iva_codigo) {
		try {
			PreparedStatement ps = null;
			Conexion con = new Conexion();
			ps = con.conn.prepareStatement("INSERT INTO com_proveedor_producto(com_proveedor_codigo,com_producto_codigo,descripcion_producto,precio,com_iva_codigo,estado) "
							+ "VALUES(?,?,?,?,?,0)");
	
			ps.setString(1, com_proveedor_codigo);
			ps.setString(2, com_producto_codigo);
			ps.setString(3, descripcion_producto);
			ps.setString(4, precio);
			ps.setString(5, com_iva_codigo);

			ps.execute();
			ps.close();
			con.cerrar();
		} catch (Exception ex) {
			ex.getMessage();
			System.out.print("Error Asignar Producto a Proveedor " + ex);
		}
	}
	
	// Insertar orden
	public void CrearOrden(String fecha_realizada, String hora_realizada,String usuario_realiza, String fecha_entrega, String com_proveedor_codigo,
								   String plazo_dias) {
		try {
			PreparedStatement ps = null;
			Conexion con = new Conexion();
			ps = con.conn.prepareStatement("INSERT INTO com_orden(fecha_realizada,hora_realizada,usuario_realiza,fecha_entrega,com_proveedor_codigo," +
					                       "plazo_dias,valor_total,estado,observacion,fecha_aprobada,hora_aprobada,usuario_aprueba,fecha_anulada,hora_anulada," +
					                       "usuario_anula,motivo_anulacion) "
					                       + "VALUES(?,?,?,?,?,?,0,0,'','0000-00-00','',0,'0000-00-00','',0,'')");
	
			ps.setString(1, fecha_realizada);
			ps.setString(2, hora_realizada);
			ps.setString(3, usuario_realiza);
			ps.setString(4, fecha_entrega);
			ps.setString(5, com_proveedor_codigo);
			ps.setString(6, plazo_dias);

			ps.execute();
			ps.close();
			con.cerrar();
		} catch (Exception ex) {
			ex.getMessage();
			System.out.print("Error Crear Orden " + ex);
		}
	}
	
	// Insertar detalle oren
	public void CrearDetalleOrden(String codorden, String producto,String nomproducto,String cantidad, String valor_unitario, String descuento,
								   String iva, String subtotal) {
		try {
			PreparedStatement ps = null;
			Conexion con = new Conexion();
			ps = con.conn.prepareStatement("INSERT INTO com_detalle_orden(com_orden_id_orden,com_producto_codigo,producto_descripcion,cantidad,valor_unitario," +
					                       "descuento,iva,subtotal) "
					                       + "VALUES(?,?,?,?,?,?,?,?)");
			ps.setString(1, codorden);
			ps.setString(2, producto);
			ps.setString(3, nomproducto);
			ps.setString(4, cantidad);
			ps.setString(5, valor_unitario);
			ps.setString(6, descuento);
			ps.setString(7, iva);
			ps.setString(8, subtotal);
			ps.execute();
			ps.close();
			con.cerrar();
		} catch (Exception ex) {
			ex.getMessage();
			System.out.print("Error Crear Detalle Orden " + ex);
		}
	}
	
	
	public void CrearGrupoProducto(String nom_grupo, String cuenta, String cod_cuenta) {
		try {
			PreparedStatement ps = null;
			Conexion con = new Conexion();
			ps = con.conn.prepareStatement("INSERT INTO cont_producto_cuenta(nombre,cuenta,cod_cuenta) "
					+ "VALUES(?,?,?)");

			ps.setString(1, nom_grupo);
			ps.setString(2, cuenta);
			ps.setString(3, cod_cuenta);

			ps.execute();
			ps.close();
			con.cerrar();
		} catch (Exception ex) {
			ex.getMessage();
			System.out.print("Error Crear grupo producto " + ex);
		}
	}

	public void CrearSubGrupoProducto(String grupo,String nom_grupo, String cuenta,String cuentac) {
		try {
			PreparedStatement ps = null;
			Conexion con = new Conexion();
			ps = con.conn.prepareStatement("INSERT INTO cont_subproducto_cuenta(Cod_Producto_Cuenta,Nombre,Cuenta,Cod_Cuenta) "
					+ "VALUES(?,?,?,?)");
			
			ps.setString(1, grupo);
			ps.setString(2, nom_grupo);
			ps.setString(3, cuenta);
			ps.setString(4, cuentac);

			ps.execute();
			ps.close();
			con.cerrar();
		} catch (Exception ex) {
			ex.getMessage();
			System.out.print("Error Crear grupo producto " + ex);
		}
	}

	
	
	
	
	/*UPDATE*/
	
	// Actualiza el precio y el iva del producto y proveedor
	public void ActualizarProductoProveedor(String producto, String proveedor,String descripcion, String precio, String iva) {
		try {
			PreparedStatement ps = null;
			Conexion con = new Conexion();
			ps = con.conn.prepareStatement("UPDATE com_proveedor_producto SET precio='"+precio+"', com_iva_codigo='"+iva+"', descripcion_producto='"+descripcion+"'  WHERE codigo="+producto);
			ps.execute();
			ps.close();
			con.cerrar();
		} catch (Exception ex) {
			ex.getMessage();
			System.out.println("Error Update producto proveedor " + ex);
		}
	}
	
	// Actualiza el la cantidad y descuento del detalle
	public void ActualizarDetalle(String cod_detalle, String cantidad,String descuento, String iva, String subtotal) {
		try {
			PreparedStatement ps = null;
			Conexion con = new Conexion();
			ps = con.conn.prepareStatement("UPDATE com_detalle_orden SET cantidad="+cantidad+", descuento="+descuento+", iva="+iva+", subtotal="+subtotal+" WHERE id_detalle_orden="+cod_detalle);
			ps.execute();
			ps.close();
			con.cerrar();
		} catch (Exception ex) {
			ex.getMessage();
			System.out.println("Error Update detalle " + ex);
		}
	}
	
	// Actualiza el la cantidad y descuento del detalle
	public void ActualizarValorTotalOrden(String cod_orden, String valortotal) {
		try {
			PreparedStatement ps = null;
			Conexion con = new Conexion();
			ps = con.conn.prepareStatement("UPDATE com_orden SET valor_total="+valortotal+" WHERE id_orden="+cod_orden);
			ps.execute();
			ps.close();
			con.cerrar();
		} catch (Exception ex) {
			ex.getMessage();
			System.out.println("Error Update detalle " + ex);
		}
	}
	
	// Actualiza el la cantidad y descuento del detalle
	public void FinalizarOrden(String codorden, String Observacion,String vtotal) {
		try {
			PreparedStatement ps = null;
			Conexion con = new Conexion();
			ps = con.conn.prepareStatement("UPDATE com_orden SET valor_total="+vtotal+",observacion='"+Observacion+"' WHERE id_orden="+codorden);
			ps.execute();
			ps.close();
			con.cerrar();
		} catch (Exception ex) {
			ex.getMessage();
			System.out.println("Error Update finalizar orden " + ex);
		}
	}
	
	// Aprobar orden estado=1
	public void AprobarOrden(String codorden, String Usuario, String fecha, String Hora) {
		try {
			PreparedStatement ps = null;
			Conexion con = new Conexion();
			ps = con.conn.prepareStatement("UPDATE com_orden SET estado=1,usuario_aprueba="+Usuario+",fecha_aprobada='"+fecha+"',hora_aprobada='"+Hora+"' WHERE id_orden="+codorden);
			ps.execute();
			ps.close();
			con.cerrar();
		} catch (Exception ex) {
			ex.getMessage();
			System.out.println("Error Update Aprobar orden " + ex);
		}
	}
	
	// Anular orden estado=2
	public void AnularOrden(String codorden, String Usuario, String fecha, String Hora, String motivo) {
		try {
			PreparedStatement ps = null;
			Conexion con = new Conexion();
			ps = con.conn.prepareStatement("UPDATE com_orden SET estado=2,usuario_anula="+Usuario+",fecha_anulada='"+fecha+"',hora_anulada='"+Hora+"',motivo_anulacion='"+motivo+"' WHERE id_orden="+codorden);
			ps.execute();
			ps.close();
			con.cerrar();
		} catch (Exception ex) {
			ex.getMessage();
			System.out.println("Error Update Anular orden " + ex);
		}
	}
	
	// Anular orden estado=2
	public void EstadoProducto(String cod, String estado) {
		try {
			PreparedStatement ps = null;
			Conexion con = new Conexion();
			ps = con.conn.prepareStatement("UPDATE com_proveedor_producto SET estado="+estado+" WHERE codigo="+cod);
			ps.execute();
			ps.close();
			con.cerrar();
		} catch (Exception ex) {
			ex.getMessage();
			System.out.println("Error Update Anular orden " + ex);
		}
	}
	
	
	
	/*SELECT*/
	
	//Buscar el producto
	public java.sql.ResultSet BuscarProducto(String nombre) {
		java.sql.ResultSet rs = null;
		Statement st = null;
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("SELECT * FROM com_producto WHERE nombre='"+nombre+"' AND  estado=0");
		} catch (Exception ex) {
			System.out.println("Error en Buscar producto " + ex);
		}
		return rs;
	}
	
	//AutoCompleta Proveedor
	public java.sql.ResultSet AutocompletaProveedor(String proveedor){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT codigo, razon_social FROM cont_terceros WHERE  estado=0 AND razon_social LIKE '%"+proveedor+"%' LIMIT 5");
        }catch(Exception ex){
        	System.out.println("Error en Autocompleta Proveedor "+ex);
        }	
        return rs;
    }
	
	//AutoCompleta Producto
	public java.sql.ResultSet AutocompletaProducto(String producto){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM com_producto WHERE  estado=0 AND nombre LIKE '%"+producto+"%' LIMIT 5" );
        }catch(Exception ex){
        	System.out.println("Error en Autocompleta Producto "+ex);
        }	
        return rs;
    }

	//buscar producto y proveedor
	public java.sql.ResultSet BuscarProductoProveedor(String producto,String proveedor){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM com_proveedor_producto WHERE com_proveedor_codigo="+proveedor+" AND com_producto_codigo="+producto);
        }catch(Exception ex){
        	System.out.println("Error en Buscar Producto y Proveedor "+ex);
        }	
        return rs;
    }
	
	//buscar iva
	public java.sql.ResultSet BuscarIva(){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM com_iva");
        }catch(Exception ex){
        	System.out.println("Error en Buscar iva "+ex);
        }	
        return rs;
    }
	
	//buscar productos por proveedor
	public java.sql.ResultSet BuscarProductosPorProveedor(String proveedor){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT pp.com_producto_codigo, pro.nombre FROM com_proveedor_producto pp, com_producto pro WHERE pp.com_proveedor_codigo="+proveedor+" AND pro.codigo=pp.com_producto_codigo");
        }catch(Exception ex){
        	System.out.println("Error en Buscar Productos por Proveedor "+ex);
        }	
        return rs;
    }
	
	//buscar orden 
	public java.sql.ResultSet BuscarOrden(String fecha, String hora, String usuario){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT id_orden FROM com_orden WHERE fecha_realizada='"+fecha+"' AND hora_realizada='"+hora+"' AND usuario_realiza="+usuario+" AND estado=0");
        }catch(Exception ex){
        	System.out.println("Error en Buscar Orden "+ex);
        }	
        return rs;
    }
		
	//Autocompleta Producto por proveedor
	public java.sql.ResultSet AutocompletaProductoPorProveedor(String nomproducto, String proveedor){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT pp.com_producto_codigo,pp.precio,pp.com_iva_codigo, pro.nombre, pp.descripcion_producto  " +
        			           " FROM com_proveedor_producto pp, com_producto pro " +
        					   "WHERE pp.com_proveedor_codigo="+proveedor+" AND pro.codigo=pp.com_producto_codigo " +
        					   "AND pro.nombre LIKE '%"+nomproducto+"%' AND pp.estado=0");
        }catch(Exception ex){
        	System.out.println("Error en Autocompleta producto por proveedor "+ex);
        }	
        return rs;
    }
	
	//buscar detalle orden
	public java.sql.ResultSet BuscarDetalleOrden(String idorden){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT det.com_producto_codigo,det.producto_descripcion,det.valor_unitario, det.cantidad, det.descuento, det.iva, det.subtotal,det.id_detalle_orden " +
        			" FROM com_orden orden,com_detalle_orden det" +
        			" WHERE orden.id_orden='"+idorden+"' AND det.com_orden_id_orden=orden.id_orden");
        }catch(Exception ex){
        	System.out.println("Error en Buscar Detalle de orden "+ex);
        }	
        return rs;
    }
	
	//buscar VALOR TOTAL DE LA ORDEN
	public java.sql.ResultSet BuscarValorTotalOrden(String idorden){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT SUM(subtotal), SUM(iva), SUM(subtotal)+ SUM(iva) AS total FROM com_detalle_orden WHERE com_orden_id_orden="+idorden);
        }catch(Exception ex){
        	System.out.println("Error en Buscar Valor Total de la Orden "+ex);
        }	
        return rs;
    }
	
	//buscar detalle de la orden por ID
	public java.sql.ResultSet BuscarDetalleOrdenId(String idorden){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT det.id_detalle_orden,det.producto_descripcion, det.cantidad, det.valor_unitario, det.iva, det.descuento , pp.com_iva_codigo " +
        			" FROM com_detalle_orden det, com_orden orden , com_proveedor_producto pp" +
        			" WHERE id_detalle_orden='"+idorden+"' " +
        			" AND det.com_orden_id_orden=orden.id_orden AND pp.com_producto_codigo=det.com_producto_codigo ");
        }catch(Exception ex){
        	System.out.println("Error en Buscar detalle de la Orden "+ex);
        }	
        return rs;
    }
	
	//buscar ordenes creadas estado=0
	public java.sql.ResultSet BuscarOrden(){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT orden.id_orden,orden.fecha_realizada,orden.hora_realizada, provee.razon_social, orden.valor_total," +
        			"orden.fecha_entrega, CONCAT(datos.nombre,' ',datos.apellido) AS Responsable " +
        			" FROM com_orden orden, cont_terceros provee, seg_usuario usu, seg_datos_personales datos, com_detalle_orden dorden " +
        			" WHERE orden.estado=0 AND provee.codigo=orden.com_proveedor_codigo AND usu.usu_codigo=orden.usuario_realiza " +
        			" AND datos.dat_codigo=usu.dat_codigo_fk AND dorden.com_orden_id_orden=orden.id_orden " +
        			"  GROUP BY orden.id_orden");
        }catch(Exception ex){
        	System.out.println("Error en Buscar Ordenes sin aprobar "+ex);
        }	
        return rs;
    }
	
	//
	//buscar ordenes creadas estado=0
	public java.sql.ResultSet BuscarOrdenes(String codorden){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT orden.id_orden, provee.razon_social, orden.valor_total " +
        			"FROM com_orden orden, cont_terceros provee " +
        			"WHERE orden.id_orden="+codorden+" AND provee.codigo=orden.com_proveedor_codigo");
        }catch(Exception ex){
        	System.out.println("Error en Buscar Ordenes "+ex);
        }	
        return rs;
    }
	
	//buscar ordenes creadas estado=0 sin aprobar
	public java.sql.ResultSet BuscarOrdenSinAprobar(String desde1, String hasta1){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT orden.id_orden,CONCAT(orden.fecha_realizada,' - ',orden.hora_realizada) AS fecha, provee.razon_social, orden.valor_total," +
        			"orden.fecha_entrega, CONCAT(datos.nombre,' ',datos.apellido) AS Responsable " +
        			" FROM com_orden orden, cont_terceros provee, seg_usuario usu, seg_datos_personales datos " +
        			" WHERE orden.estado=0 AND provee.codigo=orden.com_proveedor_codigo AND usu.usu_codigo=orden.usuario_realiza AND datos.dat_codigo=usu.dat_codigo_fk" +
        			" AND orden.fecha_realizada BETWEEN '"+desde1+"' AND '"+hasta1+"'");
        }catch(Exception ex){
        	System.out.println("Error en Buscar Ordenes sin aprobar "+ex);
        }	
        return rs;
    }
	
	//buscar ordenes aprobadas estado=1
	public java.sql.ResultSet BuscarOrdenAprobadas(String desde1, String hasta1){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT orden.id_orden,CONCAT(orden.fecha_realizada,' - ',orden.hora_realizada) AS fecharealizada, provee.razon_social, orden.valor_total,orden.fecha_entrega, " +
        			"CONCAT(datos.nombre,' ',datos.apellido) AS Responsable, CONCAT(orden.fecha_aprobada,' - ',orden.hora_aprobada) AS fechaaprobada, CONCAT(datos.nombre,' ',datos.apellido) AS Aprobo" +
        			" FROM com_orden orden, cont_terceros provee, seg_usuario usu, seg_datos_personales datos, seg_usuario usu2, seg_datos_personales datos2" +
        			" WHERE orden.estado=1 AND provee.codigo=orden.com_proveedor_codigo AND usu.usu_codigo=orden.usuario_realiza AND datos.dat_codigo=usu.dat_codigo_fk" +
        			" AND usu2.usu_codigo=orden.usuario_aprueba AND datos2.dat_codigo=usu2.dat_codigo_fk " +
        			" AND orden.fecha_aprobada BETWEEN '"+desde1+"' AND '"+hasta1+"'" +
        			" ORDER BY orden.id_orden");
        }catch(Exception ex){
        	System.out.println("Error en Buscar Ordenes aprobadas "+ex);
        }	
        return rs;
    }
	
	//buscar ordenes aprobadas estado=1
	public java.sql.ResultSet BuscarOrdenAnuladas(String desde1, String hasta1){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT orden.id_orden,CONCAT(orden.fecha_realizada,' - ',orden.hora_realizada) AS fecharealizada, provee.razon_social, orden.valor_total,orden.fecha_entrega, " +
        			"CONCAT(datos.nombre,' ',datos.apellido) AS Responsable, CONCAT(orden.fecha_anulada,' - ',orden.hora_anulada) AS fechaanulada, CONCAT(datos.nombre,' ',datos.apellido) AS Anulo, motivo_anulacion" +
        			" FROM com_orden orden, cont_terceros provee, seg_usuario usu, seg_datos_personales datos, seg_usuario usu2, seg_datos_personales datos2" +
        			" WHERE orden.estado=2 AND provee.codigo=orden.com_proveedor_codigo AND usu.usu_codigo=orden.usuario_realiza AND datos.dat_codigo=usu.dat_codigo_fk" +
        			" AND usu2.usu_codigo=orden.usuario_anula AND datos2.dat_codigo=usu2.dat_codigo_fk " +
        			" AND orden.fecha_anulada BETWEEN '"+desde1+"' AND '"+hasta1+"'" +
        			" ORDER BY orden.id_orden");
        }catch(Exception ex){
        	System.out.println("Error en Buscar Ordenes aprobadas "+ex);
        }	
        return rs;
    }
	
	
	//buscar ordenes aprobadas estado=1
	public java.sql.ResultSet BuscarOrdAprobadas(String No_orden){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT orden.id_orden,orden.fecha_realizada,orden.hora_realizada, provee.razon_social, orden.valor_total,orden.fecha_entrega, " +
        			" CONCAT(datos.nombre,' ',datos.apellido) AS Responsable, orden.fecha_aprobada,orden.hora_aprobada, CONCAT(datos.nombre,' ',datos.apellido) AS Aprobo, orden.valor_total " +
        			" FROM com_orden orden, cont_terceros provee, seg_usuario usu, seg_datos_personales datos, seg_usuario usu2, seg_datos_personales datos2" +
        			" WHERE orden.estado=1 AND provee.codigo=orden.com_proveedor_codigo AND usu.usu_codigo=orden.usuario_realiza AND datos.dat_codigo=usu.dat_codigo_fk" +
        			" AND usu2.usu_codigo=orden.usuario_aprueba AND datos2.dat_codigo=usu2.dat_codigo_fk " +
        			" AND orden.id_orden='"+No_orden+"'" +
        			" ORDER BY orden.id_orden");
        }catch(Exception ex){
        	System.out.println("Error en Buscar Orden aprobadas "+ex);
        }	
        return rs;
    }
	
	//buscar proveedores activos
	public java.sql.ResultSet Proveedor(){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT codigo, razon_social FROM cont_terceros WHERE  estado=0");
        }catch(Exception ex){
        	System.out.println("Error en buscar Proveedor "+ex);
        }	
        return rs;
    }
	
	//buscar productos activos
	public java.sql.ResultSet Productos(){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM com_producto WHERE estado=0");
        }catch(Exception ex){
        	System.out.println("Error en buscar proveedor "+ex);
        }	
        return rs;
    }
	
	//buscar ordenes aprobadas estado=1, proveedor y fecha
	public java.sql.ResultSet BuscarOrdProveedor(String pro,String sql){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT orden.id_orden,orden.fecha_realizada,orden.hora_realizada, provee.razon_social, orden.valor_total,orden.fecha_entrega, " +
        			" CONCAT(datos.nombre,' ',datos.apellido) AS Responsable, orden.fecha_aprobada,orden.hora_aprobada, CONCAT(datos.nombre,' ',datos.apellido) AS Aprobo" +
        			" FROM com_orden orden, cont_terceros provee, seg_usuario usu, seg_datos_personales datos, seg_usuario usu2, seg_datos_personales datos2" +
        			" WHERE orden.estado=1 AND provee.codigo=orden.com_proveedor_codigo AND usu.usu_codigo=orden.usuario_realiza AND datos.dat_codigo=usu.dat_codigo_fk" +
        			" AND usu2.usu_codigo=orden.usuario_aprueba AND datos2.dat_codigo=usu2.dat_codigo_fk " +
        			" AND provee.codigo='"+pro+"' " + sql+
        			" ORDER BY orden.id_orden");
        }catch(Exception ex){
        	System.out.println("Error en Buscar Orden aprobadas "+ex);
        }	
        return rs;
    }
	
	//buscar ordenes aprobadas estado=1 por fecha
	public java.sql.ResultSet BuscarOrd(String FI,String FF){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT orden.id_orden,orden.fecha_realizada,orden.hora_realizada, provee.razon_social, orden.valor_total,orden.fecha_entrega, " +
        			"CONCAT(datos.nombre,' ',datos.apellido) AS Responsable, orden.fecha_aprobada,orden.hora_aprobada, CONCAT(datos.nombre,' ',datos.apellido) AS Aprobo" +
        			" FROM com_orden orden, cont_terceros provee, seg_usuario usu, seg_datos_personales datos, seg_usuario usu2, seg_datos_personales datos2" +
        			" WHERE orden.estado=1 AND provee.codigo=orden.com_proveedor_codigo AND usu.usu_codigo=orden.usuario_realiza AND datos.dat_codigo=usu.dat_codigo_fk" +
        			" AND usu2.usu_codigo=orden.usuario_aprueba AND datos2.dat_codigo=usu2.dat_codigo_fk " +
        			" AND orden.fecha_realizada BETWEEN '"+FI+"' AND '"+FF+"' " +
        			" ORDER BY orden.id_orden");
        	System.out.println(" SELECT orden.id_orden,orden.fecha_realizada,orden.hora_realizada, provee.razon_social, orden.valor_total,orden.fecha_entrega, " +
        			"CONCAT(datos.nombre,' ',datos.apellido) AS Responsable, orden.fecha_aprobada,orden.hora_aprobada, CONCAT(datos.nombre,' ',datos.apellido) AS Aprobo" +
        			" FROM com_orden orden, cont_terceros provee, seg_usuario usu, seg_datos_personales datos, seg_usuario usu2, seg_datos_personales datos2" +
        			" WHERE orden.estado=1 AND provee.codigo=orden.com_proveedor_codigo AND usu.usu_codigo=orden.usuario_realiza AND datos.dat_codigo=usu.dat_codigo_fk" +
        			" AND usu2.usu_codigo=orden.usuario_aprueba AND datos2.dat_codigo=usu2.dat_codigo_fk " +
        			" AND orden.fecha_realizada BETWEEN '"+FI+"' AND '"+FF+"' " +
        			" ORDER BY orden.id_orden");
        }catch(Exception ex){
        	System.out.println("Error en Buscar Orden aprobadas por fechas"+ex);
        }	
        return rs;
    }
	
	//buscar precio por proveedor
	public java.sql.ResultSet BuscarPrecioProducto(String Pro){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT CONCAT(pro.nombre,' - ', pp.descripcion_producto) AS producto, CONCAT(ter.razon_social,'  ',ter.tipo_identificacion,'- ',ter.numero_documento ) as proveedor, pp.precio, iva.valor" +
        			" FROM com_producto pro, com_proveedor_producto pp, cont_terceros ter,com_iva iva " +
        			" WHERE pp.com_producto_codigo='"+Pro+"' AND pro.codigo=pp.com_producto_codigo" +
        			" AND pp.com_proveedor_codigo=ter.codigo AND pp.com_iva_codigo=iva.codigo" +
        			" AND ter.estado=0");
        }catch(Exception ex){
        	System.out.println("Error en Buscar precio por proveedores"+ex);
        }	
        return rs;
    }
	
	//buscar precio por proveedor
	public java.sql.ResultSet TodasOrdenes(String desde2, String hasta2){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT orden.id_orden,CONCAT(orden.fecha_realizada,' - ',orden.hora_realizada) AS fecharealiza, provee.razon_social, orden.valor_total," +
        			"orden.fecha_entrega, CONCAT(datos.nombre,' ',datos.apellido) AS Responsable, orden.estado," +
        			"empresa.nombre, empresa.nit, empresa.direccion, empresa.departamento, empresa.municipio, empresa.logo " +
        			" FROM com_orden orden, cont_terceros provee, seg_usuario usu, seg_datos_personales datos, empresa" +
        			" WHERE provee.codigo=orden.com_proveedor_codigo AND usu.usu_codigo=orden.usuario_realiza AND datos.dat_codigo=usu.dat_codigo_fk" +
        			" AND orden.fecha_realizada BETWEEN '"+desde2+"' AND '"+hasta2+"'" );
        }catch(Exception ex){
        	System.out.println("Error en Buscar precio por proveedores"+ex);
        }	
        return rs;
    }
	
	
	//buscar precio por proveedor
	public java.sql.ResultSet ProveedorProducto(String proveedor, String producto){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT pp.codigo, p.nombre, pp.descripcion_producto, pp.precio, pp.com_iva_codigo, pp.estado " +
        			" FROM com_proveedor_producto pp, com_producto p" +
        			" WHERE p.nombre LIKE '%"+producto+"%' AND pp.com_producto_codigo=p.codigo" +
        			" AND pp.com_proveedor_codigo="+proveedor);
        }catch(Exception ex){
        	System.out.println("Error en Buscar precio por proveedores "+ex);
        }	
        return rs;
    }
	
	
	//buscar precio por proveedor
	public java.sql.ResultSet SinProveedorProducto(String proveedor, String producto){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT p.codigo, p.nombre " +
        			"FROM com_producto p " +
        			"WHERE p.nombre LIKE '%"+producto+"%' AND p.codigo NOT IN (SELECT com_producto_codigo FROM com_proveedor_producto WHERE com_proveedor_codigo="+proveedor+" )");
        }catch(Exception ex){
        	System.out.println("Error en Buscar precio por proveedores "+ex);
        }	
        return rs;
    }
	
	
	//buscar iva
	public java.sql.ResultSet BuscarGrupos(){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM cont_producto_cuenta");
        }catch(Exception ex){
        	System.out.println("Error en Buscar cont_producto_cuenta "+ex);
        }	
        return rs;
    }
	
	//buscar iva
	public java.sql.ResultSet BuscarsubGrupos(String cp){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM cont_subproducto_cuenta where Cod_Producto_Cuenta="+cp);
        }catch(Exception ex){
        	System.out.println("Error en Buscar cont_producto_cuenta "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet BuscarGruposN(String nom_grupo){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM cont_producto_cuenta WHERE nombre='"+nom_grupo+"'");
        }catch(Exception ex){
        	System.out.println("Error en Buscar cont_producto_cuenta por nombre "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet AutocompletaCuenta(String cuentab){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM cont_cuentas WHERE CodigoCuenta LIKE '%"+cuentab+"%' OR NombreCuenta LIKE '%"+cuentab+"%' LIMIT 10");
        }catch(Exception ex){
        	System.out.println("Error en Autocompleta cuenta "+ex);
        }	
        return rs;
	}
	
	
	public java.sql.ResultSet BuscaGrupos(){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM cont_producto_cuenta");
        }catch(Exception ex){
        	System.out.println("Error en Buscar cont_producto_cuenta "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscaSubGrupos(String grupo){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM cont_subproducto_cuenta WHERE Cod_Producto_Cuenta="+grupo);
        }catch(Exception ex){
        	System.out.println("Error en Buscar cont_subproducto_cuenta "+ex);
        }	
        return rs;
    }
	
	
	
	/*DELETE*/
	// ELIMINA UN DETALLE DE LA ORDEN
	public void EliminarDetalle(String cod_detalle) {
		try {
			PreparedStatement ps = null;
			Conexion con = new Conexion();
			ps = con.conn.prepareStatement("DELETE FROM com_detalle_orden WHERE id_detalle_orden="+cod_detalle);
			ps.execute();
			ps.close();
			con.cerrar();
		} catch (Exception ex) {
			ex.getMessage();
			System.out.println("Error delete detalle de orden " + ex);
		}
	}
	
	// ELIMINA UN DETALLE DE LA ORDEN
	public void EliminarOrden(String cod) {
		try {
			PreparedStatement ps = null;
			Conexion con = new Conexion();
			ps = con.conn.prepareStatement("DELETE FROM com_orden WHERE id_orden="+cod);
			ps.execute();
			ps.close();
			con.cerrar();
		} catch (Exception ex) {
			ex.getMessage();
			System.out.println("Error delete orden " + ex);
		}
	}
	
}