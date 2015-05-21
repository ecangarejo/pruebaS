package farc_metodo;

import java.sql.PreparedStatement;
import java.sql.Statement;

import adm_logica.Conexion;
import farc_bean.CrearArticulo;

public class MetodoReporte {
	
	public java.sql.ResultSet BuscarPeriodoV(String fechaini,String fechafin) {
		java.sql.ResultSet rs = null;
		Statement st = null;
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			
			rs=st.executeQuery("SELECT SUBSTRING(fef.fecha, 1, 7) Mes,fdf.nombre_programa AS Medicamento,fp.cod_cups AS CUM,fp.cod_programa FROM fact_det_factura fdf,fact_enc_factura fef,fact_numeradas fn,fact_programas fp WHERE fef.cod_enc_factura=fdf.cod_enc_factura_fk AND fef.cod_enc_factura=fn.cod_enc_fact_fk AND fef.fecha BETWEEN '"+fechaini+"' AND '"+fechafin+"' AND fp.cod_programa=fdf.cod_programafk AND fp.clase_servicio=12 and fn.estadoFact!='5' GROUP BY SUBSTRING(fef.fecha, 1, 7),fp.cod_cups,fp.cod_programa ORDER BY fef.fecha");
			System.out.println("SELECT SUBSTRING(fef.fecha, 1, 7) Mes,fdf.nombre_programa AS Medicamento,fp.cod_cups AS CUM,fp.cod_programa FROM fact_det_factura fdf,fact_enc_factura fef,fact_numeradas fn,fact_programas fp WHERE fef.cod_enc_factura=fdf.cod_enc_factura_fk AND fef.cod_enc_factura=fn.cod_enc_fact_fk AND fef.fecha BETWEEN '"+fechaini+"' AND '"+fechafin+"' AND fp.cod_programa=fdf.cod_programafk AND fp.clase_servicio=12 and fn.estadoFact!='5' GROUP BY SUBSTRING(fef.fecha, 1, 7),fp.cod_cups,fp.cod_programa ORDER BY fef.fecha");
			//System.out.println("SELECT SUBSTRING(fef.fecha, 1, 7) Mes,fdf.nombre_programa AS Medicamento,fp.cod_cups AS CUM,MIN(fdf.valor) AS Precio_Minimo,(SUM(fdf.cantidad)) * (MIN(fdf.valor)) AS suma,SUM(fdf.cantidad) AS Cantidad,fp.cod_programa,fef.cod_enc_factura FROM fact_det_factura fdf,fact_enc_factura fef,fact_numeradas fn,fact_programas fp WHERE fef.cod_enc_factura=fdf.cod_enc_factura_fk AND fef.cod_enc_factura=fn.cod_enc_fact_fk AND fef.fecha BETWEEN '"+fechaini+"' AND '"+fechafin+"' AND fp.cod_programa=fdf.cod_programafk AND fp.clase_servicio=12 and fn.estadoFact!='5' GROUP BY SUBSTRING(fef.fecha, 1, 7),fp.cod_cups,fp.cod_programa ORDER BY fef.fecha");
			/*rs = st.executeQuery("SELECT inv.fecha,CONCAT(med.nombre,'-',med.concentracion)AS Medicamento,fcu.codigo_cum, SUM(far.cantidad_recibida) AS cantidad," +
					"MIN(inv.vunitario) AS Precio_Minimo, MAX(inv.vunitario) AS Precio_Maximo, MIN(inv.vunitario)*SUM(far.cantidad_recibida) AS valorTotal " +
					"FROM farc_inventario inv, medicamentos med,farc_act_recep far,farc_cum_med_lab fcu " +
					"WHERE inv.cod_medFk=med.codigo " +
					"AND far.cod_invfk=inv.codigo " +
					"AND fcu.codigo_med_fk=med.codigo AND far.fecha BETWEEN '"+fechaini+"' AND '"+fechafin+"' " +
					"GROUP BY med.codigo " +
					"ORDER BY inv.fecha");*/
			/*System.out.println("SELECT inv.fecha,CONCAT(med.nombre,'-',med.concentracion)AS Medicamento,fcu.codigo_cum, SUM(far.cantidad_recibida) AS cantidad," +
					"MIN(inv.vunitario) AS Precio_Minimo, MAX(inv.vunitario) AS Precio_Maximo, MIN(inv.vunitario)*SUM(far.cantidad_recibida) AS valorTotal " +
					"FROM farc_inventario inv, medicamentos med,farc_act_recep far,farc_cum_med_lab fcu " +
					"WHERE inv.cod_medFk=med.codigo " +
					"AND far.cod_invfk=inv.codigo " +
					"AND fcu.codigo_med_fk=med.codigo AND far.fecha BETWEEN '"+fechaini+"' AND '"+fechafin+"' " +
					"GROUP BY med.codigo " +
					"ORDER BY inv.fecha");*/
		
		} catch (Exception ex) {
			System.out.println("Error en MetodoReporte>>BuscarPeriodo " + ex);
		}
		return rs;
	}
	
	public java.sql.ResultSet BuscarPeriodo(String fechaini,String fechafin) {
		java.sql.ResultSet rs = null;
		Statement st = null;
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			
			rs=st.executeQuery("SELECT inv.fecha,CONCAT(med.nombre,'-',med.concentracion) AS Medicamento, fp.cod_cups as cod_cups,  SUM(far.cantidad_recibida) AS cantidad,  MIN(inv.vunitario) AS Precio_Minimo,  MAX(inv.vunitario) AS Precio_Maximo,  MIN(inv.vunitario) * SUM(far.cantidad_recibida) AS valorTotal,far.codigo,med.codigo AS CodMedica FROM  farc_inventario inv,  medicamentos med,  farc_act_recep far,fact_prog_med fpm,fact_programas fp WHERE inv.cod_medFk = med.codigo   AND far.cod_invfk = inv.codigo   AND far.fecha BETWEEN '"+fechaini+"' AND '"+fechafin+"' AND med.cod_grupoFK='1' AND fpm.cod_med=med.codigo AND fpm.cod_prog=fp.cod_programa GROUP BY med.codigo,SUBSTRING(inv.fecha,1,7) ORDER BY inv.fecha ");
			System.out.println("SELECT inv.fecha,CONCAT(med.nombre,'-',med.concentracion) AS Medicamento, fp.cod_cups as cod_cups,  SUM(far.cantidad_recibida) AS cantidad,  MIN(inv.vunitario) AS Precio_Minimo,  MAX(inv.vunitario) AS Precio_Maximo,  MIN(inv.vunitario) * SUM(far.cantidad_recibida) AS valorTotal,far.codigo,med.codigo AS CodMedica FROM  farc_inventario inv,  medicamentos med,  farc_act_recep far,fact_prog_med fpm,fact_programas fp WHERE inv.cod_medFk = med.codigo   AND far.cod_invfk = inv.codigo   AND far.fecha BETWEEN '"+fechaini+"' AND '"+fechafin+"' AND med.cod_grupoFK='1' AND fpm.cod_med=med.codigo AND fpm.cod_prog=fp.cod_programa GROUP BY med.codigo,SUBSTRING(inv.fecha,1,7) ORDER BY inv.fecha ");
			/*rs = st.executeQuery("SELECT inv.fecha,CONCAT(med.nombre,'-',med.concentracion)AS Medicamento,fcu.codigo_cum, SUM(far.cantidad_recibida) AS cantidad," +
					"MIN(inv.vunitario) AS Precio_Minimo, MAX(inv.vunitario) AS Precio_Maximo, MIN(inv.vunitario)*SUM(far.cantidad_recibida) AS valorTotal " +
					"FROM farc_inventario inv, medicamentos med,farc_act_recep far,farc_cum_med_lab fcu " +
					"WHERE inv.cod_medFk=med.codigo " +
					"AND far.cod_invfk=inv.codigo " +
					"AND fcu.codigo_med_fk=med.codigo AND far.fecha BETWEEN '"+fechaini+"' AND '"+fechafin+"' " +
					"GROUP BY med.codigo " +
					"ORDER BY inv.fecha");*/
			/*System.out.println("SELECT inv.fecha,CONCAT(med.nombre,'-',med.concentracion)AS Medicamento,fcu.codigo_cum, SUM(far.cantidad_recibida) AS cantidad," +
					"MIN(inv.vunitario) AS Precio_Minimo, MAX(inv.vunitario) AS Precio_Maximo, MIN(inv.vunitario)*SUM(far.cantidad_recibida) AS valorTotal " +
					"FROM farc_inventario inv, medicamentos med,farc_act_recep far,farc_cum_med_lab fcu " +
					"WHERE inv.cod_medFk=med.codigo " +
					"AND far.cod_invfk=inv.codigo " +
					"AND fcu.codigo_med_fk=med.codigo AND far.fecha BETWEEN '"+fechaini+"' AND '"+fechafin+"' " +
					"GROUP BY med.codigo " +
					"ORDER BY inv.fecha");*/
		
		} catch (Exception ex) {
			System.out.println("Error en MetodoReporte>>BuscarPeriodo " + ex);
		}
		return rs;
	}

	public java.sql.ResultSet Buscarfact(String vunitario,String codigo) {
		java.sql.ResultSet rs = null;
		Statement st = null;
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs=st.executeQuery("SELECT far.numero_factura FROM farc_inventario inv,farc_act_recep far WHERE inv.vunitario = '"+vunitario+"' AND inv.cod_medFk ='"+codigo+"' AND far.cod_invfk = inv.codigo");
			System.out.println("SELECT far.numero_factura FROM farc_inventario inv,farc_act_recep far WHERE inv.vunitario = '"+vunitario+"' AND inv.cod_medFk ='"+codigo+"' AND far.cod_invfk = inv.codigo");
			/*rs = st.executeQuery("SELECT fcu.codigo_cum,inv.vunitario, far.numero_factura " +
					"FROM farc_inventario inv, medicamentos med,farc_act_recep far,farc_cum_med_lab fcu " +
					"WHERE inv.vunitario='"+vunitario+"' AND fcu.codigo_cum='"+codigocum+"'" +
					"AND inv.cod_medFk=med.codigo AND far.cod_invfk=inv.codigo " +
					"AND fcu.codigo_med_fk=med.codigo GROUP BY med.codigo");*/
			
			/*System.out.println("SELECT fcu.codigo_cum,inv.vunitario, far.numero_factura " +
					"FROM farc_inventario inv, medicamentos med,farc_act_recep far,farc_cum_med_lab fcu " +
					"WHERE inv.vunitario='"+vunitario+"' AND fcu.codigo_cum='"+codigocum+"'" +
					"AND inv.cod_medFk=med.codigo AND far.cod_invfk=inv.codigo " +
					"AND fcu.codigo_med_fk=med.codigo GROUP BY med.codigo");*/
		} catch (Exception ex) {
			System.out.println("Error en MetodoReporte>>BuscarPeriodo " + ex);
		}
		return rs;
	}
	
	public java.sql.ResultSet BuscarfactVCantidad(String Fecha,String CodPrograma) {
		java.sql.ResultSet rs = null;
		Statement st = null;
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs=st.executeQuery("SELECT  SUM(fdf.cantidad) as cantidad FROM  fact_det_factura fdf,  fact_enc_factura fef,  fact_numeradas fn,  fact_programas fp WHERE fef.cod_enc_factura = fdf.cod_enc_factura_fk   AND fef.cod_enc_factura = fn.cod_enc_fact_fk   AND fef.fecha LIKE '%"+Fecha+"%'  AND fp.cod_programa='"+CodPrograma+"'  AND fp.cod_programa = fdf.cod_programafk   AND fp.clase_servicio = 12  AND fn.estadoFact != '5' ");
			System.out.println("SELECT  SUM(fdf.cantidad) as cantidad FROM  fact_det_factura fdf,  fact_enc_factura fef,  fact_numeradas fn,  fact_programas fp WHERE fef.cod_enc_factura = fdf.cod_enc_factura_fk   AND fef.cod_enc_factura = fn.cod_enc_fact_fk   AND fef.fecha LIKE '%"+Fecha+"%'  AND fp.cod_programa='"+CodPrograma+"'  AND fp.cod_programa = fdf.cod_programafk   AND fp.clase_servicio = 12  AND fn.estadoFact != '5' ");
			//System.out.println("SELECT fn.consecutivo FROM fact_enc_factura fef,fact_det_factura fdf,fact_numeradas fn WHERE fef.cod_enc_factura=fdf.cod_enc_factura_fk AND fef.cod_enc_factura=fn.cod_enc_fact_fk AND fdf.cod_programafk='"+codigo+"' AND fdf.valor='"+vunitario+"' AND fef.fecha BETWEEN '"+FechaI+"' AND '"+FechaF+"' and fn.estadoFact!='5' limit 1");
			/*rs = st.executeQuery("SELECT fcu.codigo_cum,inv.vunitario, far.numero_factura " +
					"FROM farc_inventario inv, medicamentos med,farc_act_recep far,farc_cum_med_lab fcu " +
					"WHERE inv.vunitario='"+vunitario+"' AND fcu.codigo_cum='"+codigocum+"'" +
					"AND inv.cod_medFk=med.codigo AND far.cod_invfk=inv.codigo " +
					"AND fcu.codigo_med_fk=med.codigo GROUP BY med.codigo");*/
			
			/*System.out.println("SELECT fcu.codigo_cum,inv.vunitario, far.numero_factura " +
					"FROM farc_inventario inv, medicamentos med,farc_act_recep far,farc_cum_med_lab fcu " +
					"WHERE inv.vunitario='"+vunitario+"' AND fcu.codigo_cum='"+codigocum+"'" +
					"AND inv.cod_medFk=med.codigo AND far.cod_invfk=inv.codigo " +
					"AND fcu.codigo_med_fk=med.codigo GROUP BY med.codigo");*/
		} catch (Exception ex) {
			System.out.println("Error en MetodoReporte>>BuscarfactVMN " + ex);
		}
		return rs;
	}
	
	public java.sql.ResultSet BuscarfactVMN(String Fecha,String CodPrograma) {
		java.sql.ResultSet rs = null;
		Statement st = null;
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs=st.executeQuery("SELECT  SUBSTRING(fef.fecha, 1, 7) Mes,fdf.nombre_programa AS Medicamento,fp.cod_cups AS CUM,fp.cod_programa,fdf.valor,fn.consecutivo FROM  fact_det_factura fdf,  fact_enc_factura fef,  fact_numeradas fn,  fact_programas fp WHERE fef.cod_enc_factura = fdf.cod_enc_factura_fk   AND fef.cod_enc_factura = fn.cod_enc_fact_fk   AND fef.fecha LIKE '%"+Fecha+"%'  AND fp.cod_programa='"+CodPrograma+"'  AND fp.cod_programa = fdf.cod_programafk   AND fp.clase_servicio = 12  AND fn.estadoFact != '5' ORDER BY  LENGTH(fdf.valor) asc,fdf.valor asc LIMIT 1");
			System.out.println("SELECT  SUBSTRING(fef.fecha, 1, 7) Mes,fdf.nombre_programa AS Medicamento,fp.cod_cups AS CUM,fp.cod_programa,fdf.valor,fn.consecutivo FROM  fact_det_factura fdf,  fact_enc_factura fef,  fact_numeradas fn,  fact_programas fp WHERE fef.cod_enc_factura = fdf.cod_enc_factura_fk   AND fef.cod_enc_factura = fn.cod_enc_fact_fk   AND fef.fecha LIKE '%"+Fecha+"%'  AND fp.cod_programa='"+CodPrograma+"'  AND fp.cod_programa = fdf.cod_programafk   AND fp.clase_servicio = 12  AND fn.estadoFact != '5' ORDER BY  LENGTH(fdf.valor) asc,fdf.valor asc LIMIT 1");
			//System.out.println("SELECT fn.consecutivo FROM fact_enc_factura fef,fact_det_factura fdf,fact_numeradas fn WHERE fef.cod_enc_factura=fdf.cod_enc_factura_fk AND fef.cod_enc_factura=fn.cod_enc_fact_fk AND fdf.cod_programafk='"+codigo+"' AND fdf.valor='"+vunitario+"' AND fef.fecha BETWEEN '"+FechaI+"' AND '"+FechaF+"' and fn.estadoFact!='5' limit 1");
			/*rs = st.executeQuery("SELECT fcu.codigo_cum,inv.vunitario, far.numero_factura " +
					"FROM farc_inventario inv, medicamentos med,farc_act_recep far,farc_cum_med_lab fcu " +
					"WHERE inv.vunitario='"+vunitario+"' AND fcu.codigo_cum='"+codigocum+"'" +
					"AND inv.cod_medFk=med.codigo AND far.cod_invfk=inv.codigo " +
					"AND fcu.codigo_med_fk=med.codigo GROUP BY med.codigo");*/
			
			/*System.out.println("SELECT fcu.codigo_cum,inv.vunitario, far.numero_factura " +
					"FROM farc_inventario inv, medicamentos med,farc_act_recep far,farc_cum_med_lab fcu " +
					"WHERE inv.vunitario='"+vunitario+"' AND fcu.codigo_cum='"+codigocum+"'" +
					"AND inv.cod_medFk=med.codigo AND far.cod_invfk=inv.codigo " +
					"AND fcu.codigo_med_fk=med.codigo GROUP BY med.codigo");*/
		} catch (Exception ex) {
			System.out.println("Error en MetodoReporte>>BuscarfactVMN " + ex);
		}
		return rs;
	}
	
	public java.sql.ResultSet BuscarfactVMX(String Fecha,String CodPrograma) {
		java.sql.ResultSet rs = null;
		Statement st = null;
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs=st.executeQuery("SELECT  SUBSTRING(fef.fecha, 1, 7) Mes,fdf.nombre_programa AS Medicamento,fp.cod_cups AS CUM,fp.cod_programa,fdf.valor,fn.consecutivo FROM  fact_det_factura fdf,  fact_enc_factura fef,  fact_numeradas fn,  fact_programas fp WHERE fef.cod_enc_factura = fdf.cod_enc_factura_fk   AND fef.cod_enc_factura = fn.cod_enc_fact_fk   AND fef.fecha LIKE '%"+Fecha+"%'  AND fp.cod_programa='"+CodPrograma+"'  AND fp.cod_programa = fdf.cod_programafk   AND fp.clase_servicio = 12  AND fn.estadoFact != '5' ORDER BY LENGTH(fdf.valor) DESC,fdf.valor DESC LIMIT 1");
			System.out.println("SELECT  SUBSTRING(fef.fecha, 1, 7) Mes,fdf.nombre_programa AS Medicamento,fp.cod_cups AS CUM,fp.cod_programa,fdf.valor,fn.consecutivo FROM  fact_det_factura fdf,  fact_enc_factura fef,  fact_numeradas fn,  fact_programas fp WHERE fef.cod_enc_factura = fdf.cod_enc_factura_fk   AND fef.cod_enc_factura = fn.cod_enc_fact_fk   AND fef.fecha LIKE '%"+Fecha+"%'  AND fp.cod_programa='"+CodPrograma+"'  AND fp.cod_programa = fdf.cod_programafk   AND fp.clase_servicio = 12  AND fn.estadoFact != '5' ORDER BY LENGTH(fdf.valor) DESC,fdf.valor DESC LIMIT 1");
			//System.out.println("SELECT fn.consecutivo FROM fact_enc_factura fef,fact_det_factura fdf,fact_numeradas fn WHERE fef.cod_enc_factura=fdf.cod_enc_factura_fk AND fef.cod_enc_factura=fn.cod_enc_fact_fk AND fdf.cod_programafk='"+codigo+"' AND fdf.valor='"+vunitario+"' AND fef.fecha BETWEEN '"+FechaI+"' AND '"+FechaF+"' and fn.estadoFact!='5' limit 1");
			/*rs = st.executeQuery("SELECT fcu.codigo_cum,inv.vunitario, far.numero_factura " +
					"FROM farc_inventario inv, medicamentos med,farc_act_recep far,farc_cum_med_lab fcu " +
					"WHERE inv.vunitario='"+vunitario+"' AND fcu.codigo_cum='"+codigocum+"'" +
					"AND inv.cod_medFk=med.codigo AND far.cod_invfk=inv.codigo " +
					"AND fcu.codigo_med_fk=med.codigo GROUP BY med.codigo");*/
			
			/*System.out.println("SELECT fcu.codigo_cum,inv.vunitario, far.numero_factura " +
					"FROM farc_inventario inv, medicamentos med,farc_act_recep far,farc_cum_med_lab fcu " +
					"WHERE inv.vunitario='"+vunitario+"' AND fcu.codigo_cum='"+codigocum+"'" +
					"AND inv.cod_medFk=med.codigo AND far.cod_invfk=inv.codigo " +
					"AND fcu.codigo_med_fk=med.codigo GROUP BY med.codigo");*/
		} catch (Exception ex) {
			System.out.println("Error en MetodoReporte>>BuscarfactVMX " + ex);
		}
		return rs;
	}

}