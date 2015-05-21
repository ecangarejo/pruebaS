package cont_metodo;

import java.sql.Statement;

import adm_logica.Conexion;

public class MetodoReportesFact {

	public java.sql.ResultSet reportenc(String Fechai, String Fechaf, String ftent,String tnota){
		/**
		 * consulta tiene como parametro las fecha inicial y final ,
		 * para traer el reporte de notas creditos.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(ftent.equals("1")&&(tnota.equals("40"))){
        	
		        	rs=st.executeQuery("SELECT ccf.codigo, cc.NombreCuenta,concat(cdf.fecha_insercion,'/',cdf.hora_insercion) as FechaInsercion,concat(sdp.nombre,' ',sdp.apellido) usuario,cf.numero_factura,cdf.cantidad,cdf.observacion,cmc.descripcion, cf.precio_factura,cf.fecha_factura " +
		        			"FROM cont_cuentas_empresas cc,cont_movimientos_credito cmc,cont_complemento_factura ccf,cont_factura cf,cont_detalle_factura cdf,seg_usuario su,seg_datos_personales sdp " +
		        			"WHERE ccf.cod_det_factura_fk=cdf.codigo AND ccf.cod_factura_fk=cf.codigo " +
		        			"and su.usu_codigo=cdf.usuario " +
		        			"and su.dat_codigo_fk=sdp.dat_codigo " +
		        			"and cmc.codigo=ccf.tipo_pago " +
		        			"and cc.codigo=cf.cod_cuenta_fk " +
		        			"AND ccf.tipo_pago!=0 AND cdf.cantidad!=0 " +
		        			"and cdf.fecha_insercion between '"+Fechai+"' and '"+Fechaf+"' AND cdf.observacion NOT LIKE '%NOTA CREDITO ANULADA%' "); 
		        	System.out.println("SELECT ccf.codigo, cc.NombreCuenta,concat(cdf.fecha_insercion,'/',cdf.hora_insercion) as FechaInsercion,concat(sdp.nombre,' ',sdp.apellido) usuario,cf.numero_factura,cdf.cantidad,cdf.observacion,cmc.descripcion, cf.precio_factura,cf.fecha_factura " +
		        			"FROM cont_cuentas_empresas cc,cont_movimientos_credito cmc,cont_complemento_factura ccf,cont_factura cf,cont_detalle_factura cdf,seg_usuario su,seg_datos_personales sdp " +
		        			"WHERE ccf.cod_det_factura_fk=cdf.codigo AND ccf.cod_factura_fk=cf.codigo " +
		        			"and su.usu_codigo=cdf.usuario " +
		        			"and su.dat_codigo_fk=sdp.dat_codigo " +
		        			"and cmc.codigo=ccf.tipo_pago " +
		        			"and cc.codigo=cf.cod_cuenta_fk " +
		        			"AND ccf.tipo_pago!=0 AND cdf.cantidad!=0 " +
		        			"and cdf.fecha_insercion between '"+Fechai+"' and '"+Fechaf+"' AND cdf.observacion NOT LIKE '%NOTA CREDITO ANULADA%' "); 
        	}else{
        		if(ftent.equals("1")){
        			rs=st.executeQuery("SELECT ccf.codigo, cc.NombreCuenta,concat(cdf.fecha_insercion,'/',cdf.hora_insercion) as FechaInsercion,concat(sdp.nombre,' ',sdp.apellido) usuario,cf.numero_factura,cdf.cantidad,cdf.observacion,cmc.descripcion, cf.precio_factura,cf.fecha_factura " +
		        			"FROM cont_cuentas_empresas cc,cont_movimientos_credito cmc,cont_complemento_factura ccf,cont_factura cf,cont_detalle_factura cdf,seg_usuario su,seg_datos_personales sdp " +
		        			"WHERE ccf.cod_det_factura_fk=cdf.codigo AND ccf.cod_factura_fk=cf.codigo " +
		        			"and su.usu_codigo=cdf.usuario " +
		        			"and su.dat_codigo_fk=sdp.dat_codigo " +
		        			"and cmc.codigo=ccf.tipo_pago " +
		        			"and cc.codigo=cf.cod_cuenta_fk " +
		        			"AND ccf.tipo_pago!=0 AND cdf.cantidad!=0" +
		        			"AND cmc.codigo='"+tnota+"' " +
		        			"and cdf.fecha_insercion between '"+Fechai+"' and '"+Fechaf+"' AND cdf.observacion NOT LIKE '%NOTA CREDITO ANULADA%' "); 
        		
        		}else{
        			if(tnota.equals("40")){
        				rs=st.executeQuery("SELECT ccf.codigo, cc.NombreCuenta,concat(cdf.fecha_insercion,'/',cdf.hora_insercion) as FechaInsercion,concat(sdp.nombre,' ',sdp.apellido) usuario,cf.numero_factura,cdf.cantidad,cdf.observacion,cmc.descripcion, cf.precio_factura,cf.fecha_factura " +
                    			"FROM cont_cuentas_empresas cc,cont_movimientos_credito cmc,cont_complemento_factura ccf,cont_factura cf,cont_detalle_factura cdf,seg_usuario su,seg_datos_personales sdp " +
                    			"WHERE ccf.cod_det_factura_fk=cdf.codigo AND ccf.cod_factura_fk=cf.codigo " +
                    			"and su.usu_codigo=cdf.usuario " +
                    			"and su.dat_codigo_fk=sdp.dat_codigo " +
                    			"and cmc.codigo=ccf.tipo_pago " +
                    			"and cc.codigo=cf.cod_cuenta_fk " +
                    			"AND ccf.tipo_pago!=0 AND cdf.cantidad!=0 " +
                    			"and cdf.fecha_insercion between '"+Fechai+"' and '"+Fechaf+"' and cc.nit='"+ftent+"' AND cdf.observacion NOT LIKE '%NOTA CREDITO ANULADA%'"); 
        			}else{
        				
        				rs=st.executeQuery("SELECT ccf.codigo, cc.NombreCuenta,concat(cdf.fecha_insercion,'/',cdf.hora_insercion) as FechaInsercion,concat(sdp.nombre,' ',sdp.apellido) usuario,cf.numero_factura,cdf.cantidad,cdf.observacion,cmc.descripcion, cf.precio_factura,cf.fecha_factura " +
                    			"FROM cont_cuentas_empresas cc,cont_movimientos_credito cmc,cont_complemento_factura ccf,cont_factura cf,cont_detalle_factura cdf,seg_usuario su,seg_datos_personales sdp " +
                    			"WHERE ccf.cod_det_factura_fk=cdf.codigo AND ccf.cod_factura_fk=cf.codigo " +
                    			"and su.usu_codigo=cdf.usuario " +
                    			"and su.dat_codigo_fk=sdp.dat_codigo " +
                    			"and cmc.codigo=ccf.tipo_pago " +
                    			"and cc.codigo=cf.cod_cuenta_fk " +
                    			"AND ccf.tipo_pago!=0 AND cdf.cantidad!=0 " +
                    			"AND cmc.codigo='"+tnota+"' " +
                    			"and cdf.fecha_insercion between '"+Fechai+"' and '"+Fechaf+"' and cc.nit='"+ftent+"' AND cdf.observacion NOT LIKE '%NOTA CREDITO ANULADA%'"); 
        				
        			}
        		}
        	
        	}
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoResportesFact>>reportenc"+ex);
        }	
        return rs;
	}
	
	
	public java.sql.ResultSet reportencconsolidado(String Fechai, String Fechaf, String ftent,String tnota){
		/**
		 * consulta tiene como parametro las fecha inicial y final ,
		 * para traer el reporte de notas creditos.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(ftent.equals("1")&&(tnota.equals("40"))){
            	
	        	rs=st.executeQuery("SELECT ccf.codigo, cc.NombreCuenta,concat(cdf.fecha_insercion,'/',cdf.hora_insercion) as FechaInsercion,concat(sdp.nombre,' ',sdp.apellido) usuario,cf.numero_factura,cdf.cantidad,cdf.observacion,cmc.descripcion, cf.precio_factura " +
	        			"FROM cont_cuentas_empresas cc,cont_movimientos_credito cmc,cont_complemento_factura ccf,cont_factura cf,cont_detalle_factura cdf,seg_usuario su,seg_datos_personales sdp " +
	        			"WHERE ccf.cod_det_factura_fk=cdf.codigo AND ccf.cod_factura_fk=cf.codigo " +
	        			"and su.usu_codigo=cdf.usuario " +
	        			"and su.dat_codigo_fk=sdp.dat_codigo " +
	        			"and cmc.codigo=ccf.tipo_pago " +
	        			"and cc.codigo=cf.cod_cuenta_fk " +
	        			"AND ccf.tipo_pago!=0 " +
	        			"and cdf.fecha_insercion between '"+Fechai+"' and '"+Fechaf+"' AND cdf.observacion NOT LIKE '%NOTA CREDITO ANULADA%' GROUP BY cf.numero_factura"); 
    	}else{
    		if(ftent.equals("1")){
    			rs=st.executeQuery("SELECT ccf.codigo, cc.NombreCuenta,concat(cdf.fecha_insercion,'/',cdf.hora_insercion) as FechaInsercion,concat(sdp.nombre,' ',sdp.apellido) usuario,cf.numero_factura,cdf.cantidad,cdf.observacion,cmc.descripcion, cf.precio_factura " +
	        			"FROM cont_cuentas_empresas cc,cont_movimientos_credito cmc,cont_complemento_factura ccf,cont_factura cf,cont_detalle_factura cdf,seg_usuario su,seg_datos_personales sdp " +
	        			"WHERE ccf.cod_det_factura_fk=cdf.codigo AND ccf.cod_factura_fk=cf.codigo " +
	        			"and su.usu_codigo=cdf.usuario " +
	        			"and su.dat_codigo_fk=sdp.dat_codigo " +
	        			"and cmc.codigo=ccf.tipo_pago " +
	        			"and cc.codigo=cf.cod_cuenta_fk " +
	        			"AND ccf.tipo_pago!=0 " +
	        			"AND cmc.codigo='"+tnota+"' " +
	        			"and cdf.fecha_insercion between '"+Fechai+"' and '"+Fechaf+"' AND cdf.observacion NOT LIKE '%NOTA CREDITO ANULADA%' GROUP BY cf.numero_factura"); 
    		
    		}else{
    			if(tnota.equals("40")){
    				rs=st.executeQuery("SELECT ccf.codigo, cc.NombreCuenta,concat(cdf.fecha_insercion,'/',cdf.hora_insercion) as FechaInsercion,concat(sdp.nombre,' ',sdp.apellido) usuario,cf.numero_factura,cdf.cantidad,cdf.observacion,cmc.descripcion, cf.precio_factura " +
                			"FROM cont_cuentas_empresas cc,cont_movimientos_credito cmc,cont_complemento_factura ccf,cont_factura cf,cont_detalle_factura cdf,seg_usuario su,seg_datos_personales sdp " +
                			"WHERE ccf.cod_det_factura_fk=cdf.codigo AND ccf.cod_factura_fk=cf.codigo " +
                			"and su.usu_codigo=cdf.usuario " +
                			"and su.dat_codigo_fk=sdp.dat_codigo " +
                			"and cmc.codigo=ccf.tipo_pago " +
                			"and cc.codigo=cf.cod_cuenta_fk " +
                			"AND ccf.tipo_pago!=0 " +
                			"and cdf.fecha_insercion between '"+Fechai+"' and '"+Fechaf+"' and cc.nit='"+ftent+"' AND cdf.observacion NOT LIKE '%NOTA CREDITO ANULADA%' GROUP BY cf.numero_factura"); 
    			}else{
    				
    				rs=st.executeQuery("SELECT ccf.codigo, cc.NombreCuenta,concat(cdf.fecha_insercion,'/',cdf.hora_insercion) as FechaInsercion,concat(sdp.nombre,' ',sdp.apellido) usuario,cf.numero_factura,cdf.cantidad,cdf.observacion,cmc.descripcion, cf.precio_factura " +
                			"FROM cont_cuentas_empresas cc,cont_movimientos_credito cmc,cont_complemento_factura ccf,cont_factura cf,cont_detalle_factura cdf,seg_usuario su,seg_datos_personales sdp " +
                			"WHERE ccf.cod_det_factura_fk=cdf.codigo AND ccf.cod_factura_fk=cf.codigo " +
                			"and su.usu_codigo=cdf.usuario " +
                			"and su.dat_codigo_fk=sdp.dat_codigo " +
                			"and cmc.codigo=ccf.tipo_pago " +
                			"and cc.codigo=cf.cod_cuenta_fk " +
                			"AND ccf.tipo_pago!=0 " +
                			"AND cmc.codigo='"+tnota+"' " +
                			"and cdf.fecha_insercion between '"+Fechai+"' and '"+Fechaf+"' and cc.nit='"+ftent+"' AND cdf.observacion NOT LIKE '%NOTA CREDITO ANULADA%' GROUP BY cf.numero_factura"); 
    				
    			}
    		}
    	
    	}
    }
        catch(Exception ex){
        	System.out.println("Error en MetodoResportesFact>>reportencconsolidado"+ex);
        }	
        return rs;
	}
	
	
	public java.sql.ResultSet reportefact(String Fechai, String Fechaf,String ftent){
		/**
		 * consulta tiene como parametro las fecha inicial y final ,
		 * para traer el reporte de facturas.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(ftent.equals("1")){
        		// sin entidad
        		rs=st.executeQuery("SELECT n.consecutivo,ef.razon_social,n.fecha,(ef.valor + ef.anticipos + ef.copago + ef.moderacion) AS valor,(ef.valor + ef.anticipos + ef.copago + ef.moderacion) AS valorFactura,(ef.anticipos + ef.copago) AS valorUsuario,(ef.valor) AS valorTotal,n.estadoFact,ef.fecha_ingreso,ef.fecha_egreso,ef.nombre_paciente,u.usuario,ef.cod_enc_factura,ef.documento,SUM(fdf.cantidad * fdf.valor) AS valorcargues  FROM fact_numeradas n,fact_enc_factura ef,fact_det_factura fdf,seg_usuario u WHERE n.cod_enc_fact_fk = ef.cod_enc_factura AND ef.cod_enc_factura = fdf.cod_enc_factura_fk  AND  ef.cod_usuario_fk = u.usu_codigo AND n.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"' GROUP BY n.consecutivo ORDER BY n.consecutivo ");
        		System.out.println("SELECT n.consecutivo,ef.razon_social,n.fecha,(ef.valor + ef.anticipos + ef.copago + ef.moderacion) AS valor,(ef.valor + ef.anticipos + ef.copago + ef.moderacion) AS valorFactura,(ef.anticipos + ef.copago) AS valorUsuario,(ef.valor) AS valorTotal,n.estadoFact,ef.fecha_ingreso,ef.fecha_egreso,ef.nombre_paciente,u.usuario,ef.cod_enc_factura,ef.documento,SUM(fdf.cantidad * fdf.valor) AS valorcargues  FROM fact_numeradas n,fact_enc_factura ef,fact_det_factura fdf,seg_usuario u WHERE n.cod_enc_fact_fk = ef.cod_enc_factura AND ef.cod_enc_factura = fdf.cod_enc_factura_fk  AND ef.cod_usuario_fk = u.usu_codigo AND n.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"' GROUP BY n.consecutivo ORDER BY n.consecutivo ");
        	}else{
        		//con entidad
        		rs=st.executeQuery("SELECT n.consecutivo,ef.razon_social,n.fecha,(ef.valor + ef.anticipos + ef.copago + ef.moderacion) AS valor,(ef.valor + ef.anticipos + ef.copago + ef.moderacion) AS valorFactura,(ef.anticipos + ef.copago) AS valorUsuario,(ef.valor) AS valorTotal,n.estadoFact,ef.fecha_ingreso,ef.fecha_egreso,ef.nombre_paciente,u.usuario,ef.cod_enc_factura,ef.documento,SUM(fdf.cantidad * fdf.valor) AS valorcargues  FROM fact_numeradas n,fact_enc_factura ef,fact_det_factura fdf,seg_usuario u WHERE n.cod_enc_fact_fk = ef.cod_enc_factura AND ef.cod_enc_factura = fdf.cod_enc_factura_fk  AND ef.cod_usuario_fk = u.usu_codigo AND n.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"' AND ef.cod_eps='"+ftent+"' GROUP BY n.consecutivo ORDER BY n.consecutivo ");
        		System.out.println("SELECT n.consecutivo,ef.razon_social,n.fecha,(ef.valor + ef.anticipos + ef.copago + ef.moderacion) AS valor,(ef.valor + ef.anticipos + ef.copago + ef.moderacion) AS valorFactura,(ef.anticipos + ef.copago) AS valorUsuario,(ef.valor) AS valorTotal,n.estadoFact,ef.fecha_ingreso,ef.fecha_egreso,ef.nombre_paciente,u.usuario,ef.cod_enc_factura,ef.documento,SUM(fdf.cantidad * fdf.valor) AS valorcargues  FROM fact_numeradas n,fact_enc_factura ef,fact_det_factura fdf,seg_usuario u WHERE n.cod_enc_fact_fk = ef.cod_enc_factura AND ef.cod_enc_factura = fdf.cod_enc_factura_fk  AND ef.cod_usuario_fk = u.usu_codigo AND n.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"' AND ef.cod_eps='"+ftent+"' GROUP BY n.consecutivo ORDER BY n.consecutivo ");	
        	}
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoResportesFact>>reportefact"+ex);
        }	
        return rs;
	}
	
	public java.sql.ResultSet empresa(){
		/**
		 * Busca los datos de la empresa
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	
        	rs=st.executeQuery("SELECT * FROM empresa");
        	
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoResportesFact>>empresa"+ex);
        }	
        return rs;
	}
	
	public java.sql.ResultSet BuscarTipoNota(String tnota){
		/**
		 * Buscar los nombres de los tipos de nota 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	
        	rs=st.executeQuery("SELECT * FROM cont_movimientos_credito where codigo="+tnota+" ");
        	
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoResportesFact>>BuscarTipoNota"+ex);
        }	
        return rs;
	}
	
	
	
	public java.sql.ResultSet reportercAnu(String Fechai, String Fechaf,String ftent){
		/**
		 * consulta tiene como parametro las fecha inicial y final ,
		 * para traer el reporte de facturas, en esta consulta se tomo como referencia la fecha de insercion porque al momento de anular
		 * un RC NO existe un registro de la fecha. 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(ftent.equals("1")){
        	//System.out.println("SELECT n.consecutivo, ef.razon_social, ef.fecha, (ef.valor+ef.efectivo) AS valor , (ef.valor+ef.efectivo) AS valorFactura,ef.efectivo AS valorUsuario,(ef.valor) AS valorTotal,'FACTURADA', ef.nombre_paciente, u.usuario FROM fact_numeradas n, fact_enc_factura ef, seg_usuario u WHERE n.cod_enc_fact_fk=ef.cod_enc_factura AND n.estadoFact!=5 and ef.cod_usuario_fk=u.usu_codigo AND ef.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"'");
        	rs=st.executeQuery("SELECT DISTINCT crc.codigo rec_caja," +
        						"aent.nombre_entidad, ctc.Concepto, " +
        						"CONCAT(sdt.nombre, ' ', sdt.apellido) AS usuario, " +
        						"CONCAT(crc.fecha, '/', crc.hora) AS fecha_insercion ,crc.FechaPago, crc.obsevacion, crc.valor_total " +
        						" FROM  cont_recibo_caja crc, " +
								" adm_entidad aent, " +
								"  seg_usuario su, " +
								"  seg_datos_personales sdt, " +
								"  cont_detalle_recibo_caja cdr, " +
								"  cont_tipo_concepto ctc " +
								" WHERE crc.cod_entidad_fk = aent.ent_nit " +
								"  AND crc.concepto = ctc.codigo " +
								"  AND su.usu_codigo = crc.cod_usu_fk " +
								"  AND sdt.dat_codigo = su.dat_codigo_fk " +
								"  AND crc.codigo = cdr.cod_recibo_caja_fk " +
								" and crc.fecha between '"+Fechai+"' and '"+Fechaf+"' AND crc.obsevacion LIKE '%RECIBO DE CAJA ANULADO%'"); 
        	System.out.println("SELECT DISTINCT crc.codigo rec_caja," +
					"aent.nombre_entidad, ctc.Concepto, " +
					"CONCAT(sdt.nombre, ' ', sdt.apellido) AS usuario, " +
					"CONCAT(crc.fecha, '/', crc.hora) AS fecha_insercion ,crc.FechaPago, crc.obsevacion, crc.valor_total " +
					" FROM  cont_recibo_caja crc, " +
					" adm_entidad aent, " +
					"  seg_usuario su, " +
					"  seg_datos_personales sdt, " +
					"  cont_detalle_recibo_caja cdr, " +
					"  cont_tipo_concepto ctc " +
					" WHERE crc.cod_entidad_fk = aent.ent_nit " +
					"  AND crc.concepto = ctc.codigo " +
					"  AND su.usu_codigo = crc.cod_usu_fk " +
					"  AND sdt.dat_codigo = su.dat_codigo_fk " +
					"  AND crc.codigo = cdr.cod_recibo_caja_fk " +
					" and crc.fecha between '"+Fechai+"' and '"+Fechaf+"' AND crc.obsevacion LIKE '%RECIBO DE CAJA ANULADO%'"); 
        }else{
        	
        	rs=st.executeQuery("SELECT DISTINCT crc.codigo rec_caja," +
					"aent.nombre_entidad, ctc.Concepto, " +
					"CONCAT(sdt.nombre, ' ', sdt.apellido) AS usuario, " +
					"CONCAT(crc.fecha, '/', crc.hora) AS fecha_insercion ,crc.FechaPago,crc.obsevacion, crc.valor_total " +
					" FROM  cont_recibo_caja crc, " +
					" adm_entidad aent, " +
					"  seg_usuario su, " +
					"  seg_datos_personales sdt, " +
					"  cont_detalle_recibo_caja cdr, " +
					"  cont_tipo_concepto ctc " +
					" WHERE crc.cod_entidad_fk = aent.ent_nit " +
					"  AND crc.concepto = ctc.codigo " +
					"  AND su.usu_codigo = crc.cod_usu_fk " +
					"  AND sdt.dat_codigo = su.dat_codigo_fk " +
					"  AND crc.codigo = cdr.cod_recibo_caja_fk " +
					" and crc.fecha between '"+Fechai+"' and '"+Fechaf+"'  AND aent.ent_nit='"+ftent+"' and crc.obsevacion LIKE '%RECIBO DE CAJA ANULADO%'"); 
        }
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoResportesFact>>reportercAnu"+ex);
        }	
        return rs;
	}
	
	public java.sql.ResultSet reportefactanu(String Fechai, String Fechaf,String ftent){
		/**
		 * consulta tiene como parametro las fecha inicial y final ,
		 * para traer el reporte de facturas.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(ftent.equals("1")){
        	rs=st.executeQuery("SELECT n.consecutivo, cce.NombreCuenta, n.fecha, (ef.valor+ef.efectivo) AS valor , (ef.valor+ef.efectivo) AS valorFactura,ef.efectivo AS valorUsuario,(ef.valor) AS valorTotal,'ANULADA', ef.nombre_paciente, u.usuario,  fmv.usuario FROM fact_numeradas n, fact_enc_factura ef, seg_usuario u,fact_movfacturas fmv,cont_factura cf,cont_cuentas_empresas cce WHERE n.cod_enc_fact_fk=ef.cod_enc_factura AND n.estadoFact=5 AND fmv.codFactNumerada=n.cod_fact_numerada and fmv.estadoFact=5  AND cf.numero_factura=n.consecutivo AND cf.cod_cuenta_fk=cce.codigo and ef.cod_usuario_fk=u.usu_codigo AND fmv.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"'");
        	//System.out.println("SELECT n.consecutivo, ef.razon_social, ef.fecha, (ef.valor+ef.efectivo) AS valor , (ef.valor+ef.efectivo) AS valorFactura,ef.efectivo AS valorUsuario,(ef.valor) AS valorTotal,'FACTURADA', ef.nombre_paciente, u.usuario FROM fact_numeradas n, fact_enc_factura ef, seg_usuario u WHERE n.cod_enc_fact_fk=ef.cod_enc_factura AND n.estadoFact!=5 and ef.cod_usuario_fk=u.usu_codigo AND ef.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"'");
        	}else{
        		rs=st.executeQuery("SELECT n.consecutivo, cce.NombreCuenta, n.fecha, (ef.valor+ef.efectivo) AS valor , (ef.valor+ef.efectivo) AS valorFactura,ef.efectivo AS valorUsuario,(ef.valor) AS valorTotal,'ANULADA', ef.nombre_paciente, u.usuario,  fmv.usuario FROM fact_numeradas n, fact_enc_factura ef, seg_usuario u,fact_movfacturas fmv,cont_factura cf,cont_cuentas_empresas cce WHERE n.cod_enc_fact_fk=ef.cod_enc_factura AND n.estadoFact=5 AND fmv.codFactNumerada=n.cod_fact_numerada and fmv.estadoFact=5  AND cf.numero_factura=n.consecutivo AND cf.cod_cuenta_fk=cce.codigo and ef.cod_usuario_fk=u.usu_codigo AND fmv.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"' and cce.nit='"+ftent+"'");
        	}
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoResportesFact>>reportefactanu"+ex);
        }	
        return rs;
	}
	
	public java.sql.ResultSet BuscarNombreUsuario(String CodigoUsuario){	
		/**
		 * consulta tiene como parametro el codigo del usuario
		 * para buscar el nombre del usuario
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT CONCAT(sdt.nombre,' ',sdt.apellido)AS nombreuser, su.usu_codigo, sdt.nombre FROM seg_datos_personales sdt, seg_usuario su WHERE su.usu_codigo='"+CodigoUsuario+"' AND sdt.dat_codigo=su.dat_codigo_fk");
        			}
        catch(Exception ex){
        	System.out.println("Error en MetodoResportesFact>>BuscarNombreUsuario "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet reportencAnu(String Fechai, String Fechaf, String ftent){
		/**
		 * consulta tiene como parametro las fecha inicial y final ,
		 * para traer el reporte de notas creditos, no existe ahora mismo forma de sacar la fecha de anulacion. 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(ftent.equals("1")){
        	//System.out.println("SELECT n.consecutivo, ef.razon_social, ef.fecha, (ef.valor+ef.efectivo) AS valor , (ef.valor+ef.efectivo) AS valorFactura,ef.efectivo AS valorUsuario,(ef.valor) AS valorTotal,'FACTURADA', ef.nombre_paciente, u.usuario FROM fact_numeradas n, fact_enc_factura ef, seg_usuario u WHERE n.cod_enc_fact_fk=ef.cod_enc_factura AND n.estadoFact!=5 and ef.cod_usuario_fk=u.usu_codigo AND ef.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"'");
        	rs=st.executeQuery("SELECT ccf.codigo, cc.NombreCuenta,concat(cdf.fecha_insercion,'/',cdf.hora_insercion) as FechaInsercion,concat(sdp.nombre,' ',sdp.apellido) usuario,cf.numero_factura,cdf.cantidad,cdf.observacion,cmc.descripcion, cf.precio_factura " +
        			"FROM cont_cuentas_empresas cc,cont_movimientos_credito cmc,cont_complemento_factura ccf,cont_factura cf,cont_detalle_factura cdf,seg_usuario su,seg_datos_personales sdp " +
        			"WHERE ccf.cod_det_factura_fk=cdf.codigo AND ccf.cod_factura_fk=cf.codigo " +
        	"and su.usu_codigo=cdf.usuario " +
        	"and su.dat_codigo_fk=sdp.dat_codigo " +
        	"and cmc.codigo=ccf.tipo_pago " +
        	"and cc.codigo=cf.cod_cuenta_fk " +
        	"AND ccf.tipo_pago!=0 " +
        	"and cdf.fecha_insercion between '"+Fechai+"' and '"+Fechaf+"' AND cdf.observacion LIKE '%NOTA CREDITO ANULADA%'"); 
        	}else{
        		rs=st.executeQuery("SELECT ccf.codigo, cc.NombreCuenta,concat(cdf.fecha_insercion,'/',cdf.hora_insercion) as FechaInsercion,concat(sdp.nombre,' ',sdp.apellido) usuario,cf.numero_factura,cdf.cantidad,cdf.observacion,cmc.descripcion, cf.precio_factura " +
            			"FROM cont_cuentas_empresas cc,cont_movimientos_credito cmc,cont_complemento_factura ccf,cont_factura cf,cont_detalle_factura cdf,seg_usuario su,seg_datos_personales sdp " +
            			"WHERE ccf.cod_det_factura_fk=cdf.codigo AND ccf.cod_factura_fk=cf.codigo " +
            	"and su.usu_codigo=cdf.usuario " +
            	"and su.dat_codigo_fk=sdp.dat_codigo " +
            	"and cmc.codigo=ccf.tipo_pago " +
            	"and cc.codigo=cf.cod_cuenta_fk " +
            	"AND ccf.tipo_pago!=0 " +
            	"and cdf.fecha_insercion between '"+Fechai+"' and '"+Fechaf+"' and cc.nit='"+ftent+"' AND cdf.observacion LIKE '%NOTA CREDITO ANULADA%'"); 
        	System.out.println("valor de ent nc anulada"+ftent);
        	}
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoResportesFact>>reportencAnu"+ex);
        }	
        return rs;
	}
	
	
	
	public java.sql.ResultSet reportencAnuConsolidado(String Fechai, String Fechaf, String ftent){
		/**
		 * consulta tiene como parametro las fecha inicial y final ,
		 * para traer el reporte de notas creditos, no existe ahora mismo forma de sacar la fecha de anulacion. 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(ftent.equals("1")){
        	//System.out.println("SELECT n.consecutivo, ef.razon_social, ef.fecha, (ef.valor+ef.efectivo) AS valor , (ef.valor+ef.efectivo) AS valorFactura,ef.efectivo AS valorUsuario,(ef.valor) AS valorTotal,'FACTURADA', ef.nombre_paciente, u.usuario FROM fact_numeradas n, fact_enc_factura ef, seg_usuario u WHERE n.cod_enc_fact_fk=ef.cod_enc_factura AND n.estadoFact!=5 and ef.cod_usuario_fk=u.usu_codigo AND ef.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"'");
        	rs=st.executeQuery("SELECT ccf.codigo, cc.NombreCuenta,concat(cdf.fecha_insercion,'/',cdf.hora_insercion) as FechaInsercion,concat(sdp.nombre,' ',sdp.apellido) usuario,cf.numero_factura,cdf.cantidad,cdf.observacion,cmc.descripcion, cf.precio_factura " +
        			"FROM cont_cuentas_empresas cc,cont_movimientos_credito cmc,cont_complemento_factura ccf,cont_factura cf,cont_detalle_factura cdf,seg_usuario su,seg_datos_personales sdp " +
        			"WHERE ccf.cod_det_factura_fk=cdf.codigo AND ccf.cod_factura_fk=cf.codigo " +
        	"and su.usu_codigo=cdf.usuario " +
        	"and su.dat_codigo_fk=sdp.dat_codigo " +
        	"and cmc.codigo=ccf.tipo_pago " +
        	"and cc.codigo=cf.cod_cuenta_fk " +
        	"AND ccf.tipo_pago!=0 " +
        	"and cdf.fecha_insercion between '"+Fechai+"' and '"+Fechaf+"' AND cdf.observacion LIKE '%NOTA CREDITO ANULADA%' group by cf.numero_factura "); 
        	}else{
        		rs=st.executeQuery("SELECT ccf.codigo, cc.NombreCuenta,concat(cdf.fecha_insercion,'/',cdf.hora_insercion) as FechaInsercion,concat(sdp.nombre,' ',sdp.apellido) usuario,cf.numero_factura,cdf.cantidad,cdf.observacion,cmc.descripcion, cf.precio_factura " +
            			"FROM cont_cuentas_empresas cc,cont_movimientos_credito cmc,cont_complemento_factura ccf,cont_factura cf,cont_detalle_factura cdf,seg_usuario su,seg_datos_personales sdp " +
            			"WHERE ccf.cod_det_factura_fk=cdf.codigo AND ccf.cod_factura_fk=cf.codigo " +
            	"and su.usu_codigo=cdf.usuario " +
            	"and su.dat_codigo_fk=sdp.dat_codigo " +
            	"and cmc.codigo=ccf.tipo_pago " +
            	"and cc.codigo=cf.cod_cuenta_fk " +
            	"AND ccf.tipo_pago!=0 " +
            	"and cdf.fecha_insercion between '"+Fechai+"' and '"+Fechaf+"' and cc.nit='"+ftent+"' AND cdf.observacion LIKE '%NOTA CREDITO ANULADA%' group by cf.numero_factura"); 
        	//System.out.println("valor de ent nc anulada"+ftent);
        	}
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoResportesFact>>reportencAnuConsolidado"+ex);
        }	
        return rs;
	}
	
	
	
	public java.sql.ResultSet reportefactnc(String Fechai, String Fechaf, String ftent){
		/**
		 * consulta tiene como parametro las fecha inicial y final ,
		 * para traer el reporte de notas creditos.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        System.out.println("reportefactnc"+ftent);
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(ftent.equals("1")){
        	//System.out.println("SELECT n.consecutivo, ef.razon_social, ef.fecha, (ef.valor+ef.efectivo) AS valor , (ef.valor+ef.efectivo) AS valorFactura,ef.efectivo AS valorUsuario,(ef.valor) AS valorTotal,'FACTURADA', ef.nombre_paciente, u.usuario FROM fact_numeradas n, fact_enc_factura ef, seg_usuario u WHERE n.cod_enc_fact_fk=ef.cod_enc_factura AND n.estadoFact!=5 and ef.cod_usuario_fk=u.usu_codigo AND ef.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"'");
        	rs=st.executeQuery("SELECT n.consecutivo, ef.razon_social, n.fecha, (ef.valor+ef.efectivo) AS valorFactura, ef.efectivo AS valorUsuario,ef.valor AS valorTotal, cdf.cantidad, (ef.valor-cdf.cantidad) AS ValorConNC, cdf.observacion  ,  n.estadoFact, ef.nombre_paciente, u.usuario,cdf.usuario "+
        			"FROM fact_numeradas n, fact_enc_factura ef, seg_usuario u, cont_detalle_factura cdf, cont_complemento_factura ccf, cont_factura cf "+
        			"WHERE n.cod_enc_fact_fk=ef.cod_enc_factura "+
        			"AND ef.cod_usuario_fk=u.usu_codigo " + 
        			"AND ccf.cod_det_factura_fk=cdf.codigo "+
        			"AND ccf.cod_factura_fk=cf.codigo "+
        			"AND n.consecutivo=cf.numero_factura AND n.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"'"); 
        	System.out.println("reportefactnc"+ftent);
        	}else{
        		rs=st.executeQuery("SELECT n.consecutivo, ef.razon_social, n.fecha, (ef.valor+ef.efectivo) AS valorFactura, ef.efectivo AS valorUsuario,ef.valor AS valorTotal, cdf.cantidad, (ef.valor-cdf.cantidad) AS ValorConNC, cdf.observacion ,  n.estadoFact, ef.nombre_paciente, u.usuario,cdf.usuario "+
        				"FROM fact_numeradas n, fact_enc_factura ef, seg_usuario u, cont_detalle_factura cdf, cont_complemento_factura ccf, cont_factura cf "+
        				"WHERE n.cod_enc_fact_fk=ef.cod_enc_factura "+
        				"AND ef.cod_usuario_fk=u.usu_codigo "+
        				"AND ccf.cod_det_factura_fk=cdf.codigo "+ 
        				"AND ccf.cod_factura_fk=cf.codigo "+
        				"AND n.consecutivo=cf.numero_factura AND n.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"' AND ef.cod_eps='"+ftent+"'"); 
        	System.out.println("reportefactnc"+ftent);
        	}
        }catch(Exception ex){
        	System.out.println("Error en MetodoResportesFact>>reportefactNC"+ex);
        }	
        return rs;
	}
	
	
	public java.sql.ResultSet reportefactrad(String Fechai, String Fechaf,String ftent,String tipo){
		/**
		 * consulta tiene como parametro las fecha inicial y final ,
		 * para traer el reporte de facturas.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(tipo.equals("40")){
	        	if(ftent.equals("1")){
		        	rs=st.executeQuery("SELECT ffr.consRadicado AS CuentaCobro, "+
		        						"fn.consecutivo,ffr.valor AS Valortotal, ffe.fecha AS fechaEnvio,sdt.nombre, sdt.apellido , "+
		        						"p.nombre , p.primer_apellido, p.segundo_apellido, p.tipo_documento,p.numero_documento , ffr.fecha AS fechainsercion, "+
		        						"fef.fecha_ingreso AS 'f.ingreso',fef.fecha_egreso  AS 'f.egreso', "+
		        						"fef.valor AS valorfactura,ae.nombre_entidad,ffr.valorLetra,ffra.fechaRadicado ,fn.fecha , ffra.valornc, '1' AS tipo "+
		        						"FROM  fact_numeradas fn, fact_enc_factura fef, adm_entidad ae, adm_paciente p, adm_admisiones adm, fact_factradicadas ffr, "+
		        						"fact_facturas_radicadas ffra, fact_facturas_enviadas ffenv, fact_factenviadas ffe , seg_usuario su, seg_datos_personales sdt,empresa emp "+
		        						"WHERE ffra.fechaRadicado BETWEEN '"+Fechai+"' AND '"+Fechaf+"' AND ffenv.consEnvio=ffe.consEnvio AND ffenv.codFact=fn.cod_fact_numerada "+
		        						"AND ffr.consRadicado = ffra.consRadicado AND ffra.codFact = fn.cod_fact_numerada  AND fn.cod_enc_fact_fk = fef.cod_enc_factura "+
		        						"AND fef.cod_admision = adm.adm_numero_ingreso AND adm.pac_codigo_paciente_fk = p.pac_codigo_paciente "+
		        						"AND fef.cod_eps = ae.ent_nit AND ffr.cod_usuario = su.usu_codigo  AND su.dat_codigo_fk = sdt.dat_codigo "+
		        						""+
		        						"UNION "+
		        						""+
		        						"SELECT ffr.consRadicado AS CuentaCobro, "+
		        						"fn.consecutivo,ffr.valor AS Valortotal, ffe.fecha AS fechaEnvio,sdt.nombre, sdt.apellido , "+
		        						"p.nombre , p.primer_apellido, p.segundo_apellido, p.tipo_documento,p.numero_documento , ffr.fecha AS fechainsercion, "+ 
		        						"fef.fecha_ingreso AS 'f.ingreso',fef.fecha_egreso  AS 'f.egreso', "+
		        						"fef.valor AS valorfactura,ae.nombre_entidad,ffr.valorLetra,ffra.fechaRadicado ,fn.fecha ,ffra.valornc, '2' AS tipo "+
		        						"FROM  fact_numeradas fn, fact_enc_factura fef, adm_entidad ae, adm_paciente p, adm_admisiones adm, fact_factradicadasi ffr, "+
		        						"fact_facturas_radicadasi ffra, fact_facturas_enviadas ffenv, fact_factenviadas ffe , seg_usuario su, seg_datos_personales sdt,empresa emp  "+
		        						"WHERE ffra.fechaRadicado BETWEEN '"+Fechai+"' AND '"+Fechaf+"' AND ffenv.consEnvio=ffe.consEnvio AND ffenv.codFact=fn.cod_fact_numerada  "+
		        						"AND ffr.consRadicado = ffra.consRadicado AND ffra.codFact = fn.cod_fact_numerada  AND fn.cod_enc_fact_fk = fef.cod_enc_factura "+
		        						"AND fef.cod_admision = adm.adm_numero_ingreso AND adm.pac_codigo_paciente_fk = p.pac_codigo_paciente "+
		        						"AND fef.cod_eps = ae.ent_nit AND ffr.cod_usuario = su.usu_codigo  AND su.dat_codigo_fk = sdt.dat_codigo");
        	       	}else{
        	       		
        	       		rs=st.executeQuery("SELECT ffr.consRadicado AS CuentaCobro, "+
        						"fn.consecutivo,ffr.valor AS Valortotal, ffe.fecha AS fechaEnvio,sdt.nombre, sdt.apellido , "+
        						"p.nombre , p.primer_apellido, p.segundo_apellido, p.tipo_documento,p.numero_documento , ffr.fecha AS fechainsercion, "+
        						"fef.fecha_ingreso AS 'f.ingreso',fef.fecha_egreso  AS 'f.egreso', "+
        						"fef.valor AS valorfactura,ae.nombre_entidad,ffr.valorLetra,ffra.fechaRadicado ,fn.fecha , ffra.valornc, '1' AS tipo "+
        						"FROM  fact_numeradas fn, fact_enc_factura fef, adm_entidad ae, adm_paciente p, adm_admisiones adm, fact_factradicadas ffr, "+
        						"fact_facturas_radicadas ffra, fact_facturas_enviadas ffenv, fact_factenviadas ffe , seg_usuario su, seg_datos_personales sdt,empresa emp "+
        						"WHERE ffra.fechaRadicado BETWEEN '"+Fechai+"' AND '"+Fechaf+"' AND ffenv.consEnvio=ffe.consEnvio AND ffenv.codFact=fn.cod_fact_numerada "+
        						"AND ffr.consRadicado = ffra.consRadicado AND ffra.codFact = fn.cod_fact_numerada  AND fn.cod_enc_fact_fk = fef.cod_enc_factura "+
        						"AND fef.cod_admision = adm.adm_numero_ingreso AND adm.pac_codigo_paciente_fk = p.pac_codigo_paciente "+
        						"and fef.cod_eps='"+ftent+"' "+
        						"AND fef.cod_eps = ae.ent_nit AND ffr.cod_usuario = su.usu_codigo  AND su.dat_codigo_fk = sdt.dat_codigo "+
        						""+
        						"UNION "+
        						""+
        						"SELECT ffr.consRadicado AS CuentaCobro, "+
        						"fn.consecutivo,ffr.valor AS Valortotal, ffe.fecha AS fechaEnvio,sdt.nombre, sdt.apellido , "+
        						"p.nombre , p.primer_apellido, p.segundo_apellido, p.tipo_documento,p.numero_documento , ffr.fecha AS fechainsercion, "+ 
        						"fef.fecha_ingreso AS 'f.ingreso',fef.fecha_egreso  AS 'f.egreso', "+
        						"fef.valor AS valorfactura,ae.nombre_entidad,ffr.valorLetra,ffra.fechaRadicado ,fn.fecha ,ffra.valornc, '2' AS tipo "+
        						"FROM  fact_numeradas fn, fact_enc_factura fef, adm_entidad ae, adm_paciente p, adm_admisiones adm, fact_factradicadasi ffr, "+
        						"fact_facturas_radicadasi ffra, fact_facturas_enviadas ffenv, fact_factenviadas ffe , seg_usuario su, seg_datos_personales sdt,empresa emp  "+
        						"WHERE ffra.fechaRadicado BETWEEN '"+Fechai+"' AND '"+Fechaf+"' AND ffenv.consEnvio=ffe.consEnvio AND ffenv.codFact=fn.cod_fact_numerada  "+
        						"AND ffr.consRadicado = ffra.consRadicado AND ffra.codFact = fn.cod_fact_numerada  AND fn.cod_enc_fact_fk = fef.cod_enc_factura "+
        						"AND fef.cod_admision = adm.adm_numero_ingreso AND adm.pac_codigo_paciente_fk = p.pac_codigo_paciente "+
        						"and fef.cod_eps='"+ftent+"' "+
        						"AND fef.cod_eps = ae.ent_nit AND ffr.cod_usuario = su.usu_codigo  AND su.dat_codigo_fk = sdt.dat_codigo");
        		
        	       		
        	       	}
        }else{
        	if(tipo.equals("1")){
        		if(ftent.equals("1")){
		        		rs=st.executeQuery("SELECT ffr.consRadicado AS CuentaCobro, "+
						"fn.consecutivo,ffr.valor AS Valortotal, ffe.fecha AS fechaEnvio,sdt.nombre, sdt.apellido , "+
						"p.nombre , p.primer_apellido, p.segundo_apellido, p.tipo_documento,p.numero_documento , ffr.fecha AS fechainsercion, "+
						"fef.fecha_ingreso AS 'f.ingreso',fef.fecha_egreso  AS 'f.egreso', "+
						"fef.valor AS valorfactura,ae.nombre_entidad,ffr.valorLetra,ffra.fechaRadicado ,fn.fecha , ffra.valornc, '1' AS tipo "+
						"FROM  fact_numeradas fn, fact_enc_factura fef, adm_entidad ae, adm_paciente p, adm_admisiones adm, fact_factradicadas ffr, "+
						"fact_facturas_radicadas ffra, fact_facturas_enviadas ffenv, fact_factenviadas ffe , seg_usuario su, seg_datos_personales sdt,empresa emp "+
						"WHERE ffra.fechaRadicado BETWEEN '"+Fechai+"' AND '"+Fechaf+"' AND ffenv.consEnvio=ffe.consEnvio AND ffenv.codFact=fn.cod_fact_numerada "+
						"AND ffr.consRadicado = ffra.consRadicado AND ffra.codFact = fn.cod_fact_numerada  AND fn.cod_enc_fact_fk = fef.cod_enc_factura "+
						"AND fef.cod_admision = adm.adm_numero_ingreso AND adm.pac_codigo_paciente_fk = p.pac_codigo_paciente "+
						"AND fef.cod_eps = ae.ent_nit AND ffr.cod_usuario = su.usu_codigo  AND su.dat_codigo_fk = sdt.dat_codigo order by ffr.consRadicado");
        		}else{
        			
        			rs=st.executeQuery("SELECT ffr.consRadicado AS CuentaCobro, "+
    						"fn.consecutivo,ffr.valor AS Valortotal, ffe.fecha AS fechaEnvio,sdt.nombre, sdt.apellido , "+
    						"p.nombre , p.primer_apellido, p.segundo_apellido, p.tipo_documento,p.numero_documento , ffr.fecha AS fechainsercion, "+
    						"fef.fecha_ingreso AS 'f.ingreso',fef.fecha_egreso  AS 'f.egreso', "+
    						"fef.valor AS valorfactura,ae.nombre_entidad,ffr.valorLetra,ffra.fechaRadicado ,fn.fecha , ffra.valornc, '1' AS tipo "+
    						"FROM  fact_numeradas fn, fact_enc_factura fef, adm_entidad ae, adm_paciente p, adm_admisiones adm, fact_factradicadas ffr, "+
    						"fact_facturas_radicadas ffra, fact_facturas_enviadas ffenv, fact_factenviadas ffe , seg_usuario su, seg_datos_personales sdt,empresa emp "+
    						"WHERE ffra.fechaRadicado BETWEEN '"+Fechai+"' AND '"+Fechaf+"' AND ffenv.consEnvio=ffe.consEnvio AND ffenv.codFact=fn.cod_fact_numerada "+
    						"AND ffr.consRadicado = ffra.consRadicado AND ffra.codFact = fn.cod_fact_numerada  AND fn.cod_enc_fact_fk = fef.cod_enc_factura "+
    						"AND fef.cod_admision = adm.adm_numero_ingreso AND adm.pac_codigo_paciente_fk = p.pac_codigo_paciente "+
    						"and fef.cod_eps='"+ftent+"' "+
    						"AND fef.cod_eps = ae.ent_nit AND ffr.cod_usuario = su.usu_codigo  AND su.dat_codigo_fk = sdt.dat_codigo order by ffr.consRadicado");
        		}
        	}else{
        		if(tipo.equals("2")){
        			if(ftent.equals("2")){
        				rs=st.executeQuery("SELECT ffr.consRadicado AS CuentaCobro, "+
						"fn.consecutivo,ffr.valor AS Valortotal, ffe.fecha AS fechaEnvio,sdt.nombre, sdt.apellido , "+
						"p.nombre , p.primer_apellido, p.segundo_apellido, p.tipo_documento,p.numero_documento , ffr.fecha AS fechainsercion, "+ 
						"fef.fecha_ingreso AS 'f.ingreso',fef.fecha_egreso  AS 'f.egreso', "+
						"fef.valor AS valorfactura,ae.nombre_entidad,ffr.valorLetra,ffra.fechaRadicado ,fn.fecha ,ffra.valornc, '2' AS tipo "+
						"FROM  fact_numeradas fn, fact_enc_factura fef, adm_entidad ae, adm_paciente p, adm_admisiones adm, fact_factradicadasi ffr, "+
						"fact_facturas_radicadasi ffra, fact_facturas_enviadas ffenv, fact_factenviadas ffe , seg_usuario su, seg_datos_personales sdt,empresa emp  "+
						"WHERE ffra.fechaRadicado BETWEEN '"+Fechai+"' AND '"+Fechaf+"' AND ffenv.consEnvio=ffe.consEnvio AND ffenv.codFact=fn.cod_fact_numerada  "+
						"AND ffr.consRadicado = ffra.consRadicado AND ffra.codFact = fn.cod_fact_numerada  AND fn.cod_enc_fact_fk = fef.cod_enc_factura "+
						"AND fef.cod_admision = adm.adm_numero_ingreso AND adm.pac_codigo_paciente_fk = p.pac_codigo_paciente "+
						"AND fef.cod_eps = ae.ent_nit AND ffr.cod_usuario = su.usu_codigo  AND su.dat_codigo_fk = sdt.dat_codigo order by ffr.consRadicado");
        				
        			}else{
        				rs=st.executeQuery("SELECT ffr.consRadicado AS CuentaCobro, "+
        						"fn.consecutivo,ffr.valor AS Valortotal, ffe.fecha AS fechaEnvio,sdt.nombre, sdt.apellido , "+
        						"p.nombre , p.primer_apellido, p.segundo_apellido, p.tipo_documento,p.numero_documento documento, ffr.fecha AS fechainsercion, "+ 
        						"fef.fecha_ingreso AS 'f.ingreso',fef.fecha_egreso  AS 'f.egreso', "+
        						"fef.valor AS valorfactura,ae.nombre_entidad,ffr.valorLetra,ffra.fechaRadicado ,fn.fecha ,ffra.valornc, '2' AS tipo "+
        						"FROM  fact_numeradas fn, fact_enc_factura fef, adm_entidad ae, adm_paciente p, adm_admisiones adm, fact_factradicadasi ffr, "+
        						"fact_facturas_radicadasi ffra, fact_facturas_enviadas ffenv, fact_factenviadas ffe , seg_usuario su, seg_datos_personales sdt,empresa emp  "+
        						"WHERE ffra.fechaRadicado BETWEEN '"+Fechai+"' AND '"+Fechaf+"' AND ffenv.consEnvio=ffe.consEnvio AND ffenv.codFact=fn.cod_fact_numerada  "+
        						"AND ffr.consRadicado = ffra.consRadicado AND ffra.codFact = fn.cod_fact_numerada  AND fn.cod_enc_fact_fk = fef.cod_enc_factura "+
        						"AND fef.cod_admision = adm.adm_numero_ingreso AND adm.pac_codigo_paciente_fk = p.pac_codigo_paciente "+
        						"and fef.cod_eps='"+ftent+"' "+
        						"AND fef.cod_eps = ae.ent_nit AND ffr.cod_usuario = su.usu_codigo  AND su.dat_codigo_fk = sdt.dat_codigo order by ffr.consRadicado");
        				
        			}
        		}
        	}
        }
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoResportesFact>>reportefactrad"+ex);
        }	
        return rs;
	}
	
	
	public java.sql.ResultSet reportefactradConsolidado(String Fechai, String Fechaf,String ftent, String tipo){
		/**
		 * Esta consulta sirve para sacar el total en radicacion pero sacado de la tabla fact_factradicadas
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(tipo.equals("1")){
	        	if(ftent.equals("1")){
	        		
	        		rs=st.executeQuery("SELECT "+
							"fn.consecutivo, fef.valor AS valorfactura "+
							"FROM  fact_numeradas fn, fact_enc_factura fef, adm_entidad ae, adm_paciente p, adm_admisiones adm, fact_factradicadas ffr, "+
							"fact_facturas_radicadas ffra, fact_facturas_enviadas ffenv, fact_factenviadas ffe , seg_usuario su, seg_datos_personales sdt,empresa emp "+
							"WHERE ffra.fechaRadicado BETWEEN '"+Fechai+"' AND '"+Fechaf+"' AND ffenv.consEnvio=ffe.consEnvio AND ffenv.codFact=fn.cod_fact_numerada "+
							"AND ffr.consRadicado = ffra.consRadicado AND ffra.codFact = fn.cod_fact_numerada  AND fn.cod_enc_fact_fk = fef.cod_enc_factura "+
							"AND fef.cod_admision = adm.adm_numero_ingreso AND adm.pac_codigo_paciente_fk = p.pac_codigo_paciente "+
							"AND fef.cod_eps = ae.ent_nit AND ffr.cod_usuario = su.usu_codigo  AND su.dat_codigo_fk = sdt.dat_codigo group by fn.consecutivo ");
	        		
	        	System.out.println("SELECT "+
						"fn.consecutivo, fef.valor AS valorfactura "+
						"FROM  fact_numeradas fn, fact_enc_factura fef, adm_entidad ae, adm_paciente p, adm_admisiones adm, fact_factradicadas ffr, "+
						"fact_facturas_radicadas ffra, fact_facturas_enviadas ffenv, fact_factenviadas ffe , seg_usuario su, seg_datos_personales sdt,empresa emp "+
						"WHERE ffra.fechaRadicado BETWEEN '"+Fechai+"' AND '"+Fechaf+"' AND ffenv.consEnvio=ffe.consEnvio AND ffenv.codFact=fn.cod_fact_numerada "+
						"AND ffr.consRadicado = ffra.consRadicado AND ffra.codFact = fn.cod_fact_numerada  AND fn.cod_enc_fact_fk = fef.cod_enc_factura "+
						"AND fef.cod_admision = adm.adm_numero_ingreso AND adm.pac_codigo_paciente_fk = p.pac_codigo_paciente "+
						"AND fef.cod_eps = ae.ent_nit AND ffr.cod_usuario = su.usu_codigo  AND su.dat_codigo_fk = sdt.dat_codigo group by fn.consecutivo ");
	        	}else{
	        		
	        		rs=st.executeQuery("SELECT "+
							"fn.consecutivo, fef.valor AS valorfactura "+
							"FROM  fact_numeradas fn, fact_enc_factura fef, adm_entidad ae, adm_paciente p, adm_admisiones adm, fact_factradicadas ffr, "+
							"fact_facturas_radicadas ffra, fact_facturas_enviadas ffenv, fact_factenviadas ffe , seg_usuario su, seg_datos_personales sdt,empresa emp "+
							"WHERE ffra.fechaRadicado BETWEEN '"+Fechai+"' AND '"+Fechaf+"' AND ffenv.consEnvio=ffe.consEnvio AND ffenv.codFact=fn.cod_fact_numerada "+
							"AND ffr.consRadicado = ffra.consRadicado AND ffra.codFact = fn.cod_fact_numerada  AND fn.cod_enc_fact_fk = fef.cod_enc_factura "+
							"AND fef.cod_admision = adm.adm_numero_ingreso AND adm.pac_codigo_paciente_fk = p.pac_codigo_paciente "+
							"and fef.cod_eps='"+ftent+"' "+
							"AND fef.cod_eps = ae.ent_nit AND ffr.cod_usuario = su.usu_codigo  AND su.dat_codigo_fk = sdt.dat_codigo group by fn.consecutivo ");
	        	}
        	}else{
        		if(ftent.equals("1")){
        			 
        			rs=st.executeQuery("SELECT  "+
    						"fn.consecutivo, fef.valor AS valorfactura "+
    						"FROM  fact_numeradas fn, fact_enc_factura fef, adm_entidad ae, adm_paciente p, adm_admisiones adm, fact_factradicadasi ffr, "+
    						"fact_facturas_radicadasi ffra, fact_facturas_enviadas ffenv, fact_factenviadas ffe , seg_usuario su, seg_datos_personales sdt,empresa emp  "+
    						"WHERE ffra.fechaRadicado BETWEEN '"+Fechai+"' AND '"+Fechaf+"' AND ffenv.consEnvio=ffe.consEnvio AND ffenv.codFact=fn.cod_fact_numerada  "+
    						"AND ffr.consRadicado = ffra.consRadicado AND ffra.codFact = fn.cod_fact_numerada  AND fn.cod_enc_fact_fk = fef.cod_enc_factura "+
    						"AND fef.cod_admision = adm.adm_numero_ingreso AND adm.pac_codigo_paciente_fk = p.pac_codigo_paciente "+
    						"AND fef.cod_eps = ae.ent_nit AND ffr.cod_usuario = su.usu_codigo  AND su.dat_codigo_fk = sdt.dat_codigo group by fn.consecutivo ");
        			
        			System.out.println("SELECT  "+
    						"fn.consecutivo, fef.valor AS valorfactura "+
    						"FROM  fact_numeradas fn, fact_enc_factura fef, adm_entidad ae, adm_paciente p, adm_admisiones adm, fact_factradicadasi ffr, "+
    						"fact_facturas_radicadasi ffra, fact_facturas_enviadas ffenv, fact_factenviadas ffe , seg_usuario su, seg_datos_personales sdt,empresa emp  "+
    						"WHERE ffra.fechaRadicado BETWEEN '"+Fechai+"' AND '"+Fechaf+"' AND ffenv.consEnvio=ffe.consEnvio AND ffenv.codFact=fn.cod_fact_numerada  "+
    						"AND ffr.consRadicado = ffra.consRadicado AND ffra.codFact = fn.cod_fact_numerada  AND fn.cod_enc_fact_fk = fef.cod_enc_factura "+
    						"AND fef.cod_admision = adm.adm_numero_ingreso AND adm.pac_codigo_paciente_fk = p.pac_codigo_paciente "+
    						"AND fef.cod_eps = ae.ent_nit AND ffr.cod_usuario = su.usu_codigo  AND su.dat_codigo_fk = sdt.dat_codigo group by fn.consecutivo ");
        			
        		}else{
        			rs=st.executeQuery("SELECT  "+
    						"fn.consecutivo, fef.valor AS valorfactura "+
    						"FROM  fact_numeradas fn, fact_enc_factura fef, adm_entidad ae, adm_paciente p, adm_admisiones adm, fact_factradicadasi ffr, "+
    						"fact_facturas_radicadasi ffra, fact_facturas_enviadas ffenv, fact_factenviadas ffe , seg_usuario su, seg_datos_personales sdt,empresa emp  "+
    						"WHERE ffra.fechaRadicado BETWEEN '"+Fechai+"' AND '"+Fechaf+"' AND ffenv.consEnvio=ffe.consEnvio AND ffenv.codFact=fn.cod_fact_numerada  "+
    						"AND ffr.consRadicado = ffra.consRadicado AND ffra.codFact = fn.cod_fact_numerada  AND fn.cod_enc_fact_fk = fef.cod_enc_factura "+
    						"AND fef.cod_admision = adm.adm_numero_ingreso AND adm.pac_codigo_paciente_fk = p.pac_codigo_paciente "+
    						"and fef.cod_eps='"+ftent+"' "+
    						"AND fef.cod_eps = ae.ent_nit AND ffr.cod_usuario = su.usu_codigo  AND su.dat_codigo_fk = sdt.dat_codigo group by fn.consecutivo ");
        			
        		}
	        		
	        }
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoReportesFact>>reportefactradConsolidado"+ex);
        }	
        return rs;
	}
	
	
	public java.sql.ResultSet reporterc(String Fechai, String Fechaf,String ftent){
		/**
		 * consulta tiene como parametro las fecha inicial y final ,
		 * para traer el reporte de facturas.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(ftent.equals("1")){
        	//System.out.println("SELECT n.consecutivo, ef.razon_social, ef.fecha, (ef.valor+ef.efectivo) AS valor , (ef.valor+ef.efectivo) AS valorFactura,ef.efectivo AS valorUsuario,(ef.valor) AS valorTotal,'FACTURADA', ef.nombre_paciente, u.usuario FROM fact_numeradas n, fact_enc_factura ef, seg_usuario u WHERE n.cod_enc_fact_fk=ef.cod_enc_factura AND n.estadoFact!=5 and ef.cod_usuario_fk=u.usu_codigo AND ef.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"'");
        	rs=st.executeQuery("SELECT DISTINCT crc.codigo rec_caja," +
        						"aent.nombre_entidad, ctc.Concepto, " +
        						"CONCAT(sdt.nombre, ' ', sdt.apellido) AS usuario, " +
        						"CONCAT(crc.fecha, '/', crc.hora) AS fecha_insercion ,crc.FechaPago,crc.obsevacion, crc.valor_total " +
        						" FROM  cont_recibo_caja crc, " +
								" adm_entidad aent, " +
								"  seg_usuario su, " +
								"  seg_datos_personales sdt, " +
								"  cont_detalle_recibo_caja cdr, " +
								"  cont_tipo_concepto ctc " +
								" WHERE crc.cod_entidad_fk = aent.ent_nit " +
								"  AND crc.concepto = ctc.codigo " +
								"  AND su.usu_codigo = crc.cod_usu_fk " +
								"  AND sdt.dat_codigo = su.dat_codigo_fk " +
								"  AND crc.codigo = cdr.cod_recibo_caja_fk " +
								" and crc.fecha between '"+Fechai+"' and '"+Fechaf+"' and crc.obsevacion NOT LIKE '%RECIBO DE CAJA ANULADO%'"); 
        	}else{
        		rs=st.executeQuery("SELECT DISTINCT crc.codigo rec_caja," +
						"aent.nombre_entidad, ctc.Concepto, " +
						"CONCAT(sdt.nombre, ' ', sdt.apellido) AS usuario, " +
						"CONCAT(crc.fecha, '/', crc.hora) AS fecha_insercion ,crc.FechaPago,crc.obsevacion, crc.valor_total " +
						" FROM  cont_recibo_caja crc, " +
						" adm_entidad aent, " +
						"  seg_usuario su, " +
						"  seg_datos_personales sdt, " +
						"  cont_detalle_recibo_caja cdr, " +
						"  cont_tipo_concepto ctc " +
						" WHERE crc.cod_entidad_fk = aent.ent_nit " +
						"  AND crc.concepto = ctc.codigo " +
						"  AND su.usu_codigo = crc.cod_usu_fk " +
						"  AND sdt.dat_codigo = su.dat_codigo_fk " +
						"  AND crc.codigo = cdr.cod_recibo_caja_fk " +
						     "and crc.fecha between '"+Fechai+"' and '"+Fechaf+"' and aent.ent_nit='"+ftent+"' and crc.obsevacion NOT LIKE '%RECIBO DE CAJA ANULADO%'"); 
        	}
        	
        	}        	
        catch(Exception ex){
        	System.out.println("Error en MetodoReportesFact>>reporterc"+ex);
        }	
        return rs;
	}
	
	
}
