package farc_metodo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import adm_logica.Conexion;
import farc_bean.CrearConstVolumen;
import farc_bean.CrearMedOrdenP;
import farc_bean.Entradas;


public class MetodoExistencias {

	public java.sql.ResultSet BuscarDxI(String CodAdm){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT GROUP_CONCAT(c.nombre,'-',c.codigoCIE) AS DxIng FROM hic_diagnosticoingreso hdi,cie10 c WHERE hdi.CodDiag_fk=c.codigo AND hdi.codAdm="+CodAdm+"");
        	System.out.println("SELECT GROUP_CONCAT(c.nombre,'-',c.codigoCIE) AS DxIng FROM hic_diagnosticoingreso hdi,cie10 c WHERE hdi.CodDiag_fk=c.codigo AND hdi.codAdm="+CodAdm+"");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoEntradas>>BuscarDxI "+ex);
        }	
        return rs;
    }
	public java.sql.ResultSet BuscarDxE(String CodAdm){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT GROUP_CONCAT(c.nombre,'-',c.codigoCIE) AS DxEgr FROM hic_diagnosticoegreso hdi,cie10 c WHERE hdi.CodDiag_fk=c.codigo AND hdi.codAdm="+CodAdm+"");
        	System.out.println("SELECT GROUP_CONCAT(c.nombre,'-',c.codigoCIE) AS DxEgr FROM hic_diagnosticoegreso hdi,cie10 c WHERE hdi.CodDiag_fk=c.codigo AND hdi.codAdm="+CodAdm+"");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoEntradas>>BuscarDxE "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet ReporteSalidasFarmacia(String FechaInicial,String FechaFinal){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT IF(me.cod_grupoFK = '1','Medicamentos','Insumos') AS tipo,  cm.descripcion,  me.nombre,  SUM(fdm.cantidad) AS CANTIDAD,  tm.descripcion AS BodegaD FROM  farc_movimientos fmov,  farc_tipomovimiento tm,  farc_detallemov fdm,  farc_inventario fin,  cont_movimiento cm,  medicamentos me WHERE fmov.tipo_mvtoFk = tm.codigo  AND fdm.cod_movFk = fmov.codigo   AND fdm.cod_invFk = fin.codigo   AND cm.codigo = tm.cod_movFk   AND me.codigo = fin.cod_medFk   AND cm.descripcion = 'SALIDA'   AND fmov.fecha BETWEEN '"+FechaInicial+"' AND '"+FechaFinal+"' GROUP BY me.nombre,tm.descripcion ORDER BY me.cod_grupoFK,me.nombre ");
        	System.out.println("SELECT IF(me.cod_grupoFK = '1','Medicamentos','Insumos') AS tipo,  cm.descripcion,  me.nombre,  SUM(fdm.cantidad) AS CANTIDAD,  tm.descripcion AS BodegaD FROM  farc_movimientos fmov,  farc_tipomovimiento tm,  farc_detallemov fdm,  farc_inventario fin,  cont_movimiento cm,  medicamentos me WHERE fmov.tipo_mvtoFk = tm.codigo  AND fdm.cod_movFk = fmov.codigo   AND fdm.cod_invFk = fin.codigo   AND cm.codigo = tm.cod_movFk   AND me.codigo = fin.cod_medFk   AND cm.descripcion = 'SALIDA'   AND fmov.fecha BETWEEN '"+FechaInicial+"' AND '"+FechaFinal+"' GROUP BY me.nombre,tm.descripcion ORDER BY me.cod_grupoFK,me.nombre ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoEntradas>>BuscarMedicamentosDx "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet BuscarMedicamentosDx(String FechaInicial,String FechaFinal){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select fdf.fecha_realizacion,fdf.nombre_programa,fdf.cantidad,fef.cod_admision from fact_enc_factura fef,fact_det_factura fdf where fef.estado='1' and fef.cod_enc_factura=fdf.cod_enc_factura_fk and fdf.clase_servicio='MEDICAMENTOS' AND fdf.fecha_realizacion between '"+FechaInicial+"' and '"+FechaFinal+"' order by fdf.fecha,fdf.nombre_programa");
        	System.out.println("select fdf.fecha_realizacion,fdf.nombre_programa,fdf.cantidad,fef.cod_admision from fact_enc_factura fef,fact_det_factura fdf where fef.estado='1' and fef.cod_enc_factura=fdf.cod_enc_factura_fk and fdf.clase_servicio='MEDICAMENTOS' AND fdf.fecha_realizacion between '"+FechaInicial+"' and '"+FechaFinal+"' order by fdf.fecha,fdf.nombre_programa");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoEntradas>>BuscarMedicamentosDx "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet ObtenerBodegas(String user){	
		/**
		 * consulta que obtiene las unidades del modulo de farmacia, 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT b.codigo, b.nombre FROM seg_bodegas_autorizadas s, farc_bodegas b WHERE s.cod_usuariofk='"+user+"' AND s.cod_bodegafk=b.codigo ");
        	System.out.println("SELECT b.codigo, b.nombre FROM seg_bodegas_autorizadas s, farc_bodegas b WHERE s.cod_usuariofk='"+user+"' AND s.cod_bodegafk=b.codigo ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoEntradas>>ObtenerBodegas "+ex);
        }	
        return rs;
    }



public java.sql.ResultSet BuscarEmpresa(){	
		/**
		 *Nombre de la empresa
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * from empresa ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoExistencias>>BuscarEmpresa "+ex);
        }	
        return rs;
    }


public java.sql.ResultSet BuscarBodega(String codigomov){	
	
	//Busca el nombre de la bodega segun codigo de inventario, esto sirve para saber la bodega de los tralados.  

   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery("SELECT b.nombre FROM farc_inventario i, farc_bodegas b WHERE i.cod_bodegaFk=b.codigo AND i.cod_movFk="+codigomov+" LIMIT 1 ");
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoCrearProveedor>>BuscarBodega"+ex);
   }	
   return rs;
}




public java.sql.ResultSet BusUsuario(String coduser){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 

   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery("SELECT CONCAT(sdp.nombre,' ',sdp.apellido) as usuario  FROM seg_usuario u, seg_datos_personales sdp WHERE u.usu_codigo='"+coduser+"' AND u.dat_codigo_fk=sdp.dat_codigo ");
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoExistencias>BusUsuario "+ex);
   }	
   return rs;
}


	
public java.sql.ResultSet BuscarOrdenesProducciones(String Fechai,String Fechaf){	
		/**
		 * Consulta la ordenes de produccion existentes. 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT fo.codigo, fo.fecha, fo.responsable,fo.auxiliar,CONCAT(sdp.nombre,' ',sdp.apellido) AS nombre  FROM farc_orden_produccion fo, seg_usuario su, seg_datos_personales sdp WHERE su.usu_codigo=fo.cod_usuariofk AND sdp.dat_codigo=su.dat_codigo_fk AND fo.estado=1 and fo.fecha between '"+Fechai+"' and '"+Fechaf+"'");
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoEntradas>>BuscarOrdenesProducciones "+ex);
        }	
        return rs;
    }


public java.sql.ResultSet BuscarMFaltCte(){	
		/**
		 * consulta los medicamentos habilitados sin constantes de volumen
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT fmedp.cod_interno,m.nombre, fmedp.concentracion, fmedp.diluyente,fmedp.VR, fgf.descripcion, fmedp.estabilidad, fmedp.via_admon, fmedp.codigo, fmedp.cod_medfk  " +
        			"FROM farc_med_orden_produccion fmedp, farc_grupofarmacologico fgf, medicamentos m " +
        			"WHERE fmedp.cod_grupoffk=fgf.cod_grupoFarmacologia " +
        			"AND m.codigo=fmedp.cod_medfk AND fmedp.codigo NOT IN (SELECT fv.cod_med_orden_producionfk FROM farc_constantes_volumen fv)");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoExistencias>>BuscarMFaltCte "+ex);
        }	
        return rs;
    }


public java.sql.ResultSet BuscarOrdenP(String codop){	
		/**
		 * Buscar Orden  de Produccion
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        
        	rs=st.executeQuery("SELECT fgp.descripcion, fdop.cod_medOrden_Produccionfk, fmedop.cod_medfk, m.nombre, fmedop.concentracion, fmedop.diluyente "+
        						"FROM farc_orden_produccion fop, farc_detalle_orden_produccion fdop, medicamentos m, farc_med_orden_produccion fmedop,farc_grupofarmacologico fgp "+
        						"WHERE fop.codigo=fdop.cod_ordenproduccionfk "+
        						"AND fdop.cod_medOrden_Produccionfk=fmedop.codigo "+
        						"AND fmedop.cod_medfk=m.codigo "+
        						"AND fmedop.cod_grupoffk=fgp.cod_grupoFarmacologia "+
        						"AND fdop.estado=0 AND fop.codigo='"+codop+"' GROUP BY fdop.cod_medOrden_Produccionfk "+
        						"ORDER BY fgp.ident_riesgo,fdop.cod_medOrden_Produccionfk");
        
        		
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoExistencias>>BuscarOrdenP "+ex);
        }	
        return rs;
    }
	

public java.sql.ResultSet ObtenerMedOrden(String cm){	
		/**
		 * consulta el medicamento que esta en orden de produccion
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT codigo FROM farc_med_orden_produccion WHERE cod_medfk='"+cm+"' ");
        //	System.out.println("SELECT codigo FROM farc_med_orden_produccion WHERE cod_medfk='"+cm+"' AND  concentracion='"+concen+"' AND diluyente='"+diluy+"' AND VR='"+VR+"' AND cod_interno='"+codi+"' AND cod_grupoffk='"+grupof+"' AND estabilidad='"+Est+"' AND via_admon='"+VAdm+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoExistencias>>ObtenerMedOrden "+ex);
        }	
        return rs;
    }

public java.sql.ResultSet BuscarOrdenPV(String codop, String tipo, String a, String b){	
		/**
		 * Buscar Orden  de Produccion
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(tipo.equals("1")){
        		rs=st.executeQuery("SELECT fgp.descripcion, fdop.cod_medOrden_Produccionfk, fmedop.cod_medfk, m.nombre, fdop.frecuencia, fdop.volumen_final, fdop.cod_formulafk "+
 						"FROM farc_orden_produccion fop, farc_detalle_orden_produccion fdop, medicamentos m, farc_med_orden_produccion fmedop,farc_grupofarmacologico fgp "+
 						"WHERE fop.codigo=fdop.cod_ordenproduccionfk "+
 						"AND fdop.cod_medOrden_Produccionfk=fmedop.codigo "+
 						"AND fmedop.cod_medfk=m.codigo "+
 						"AND fmedop.cod_grupoffk=fgp.cod_grupoFarmacologia "+
 						"AND fdop.estado=0 AND fop.codigo='"+codop+"' "+
 						"AND fdop.volumen_final<='"+a+"' "+
 						"ORDER BY fdop.volumen_final, fgp.ident_riesgo ASC ");
        	}else{
        		if((tipo.equals("7"))||(tipo.equals("8"))){
        			rs=st.executeQuery("SELECT fgp.descripcion, fdop.cod_medOrden_Produccionfk, fmedop.cod_medfk, m.nombre, fdop.frecuencia, fdop.volumen_final, fdop.cod_formulafk "+
     						"FROM farc_orden_produccion fop, farc_detalle_orden_produccion fdop, medicamentos m, farc_med_orden_produccion fmedop,farc_grupofarmacologico fgp "+
     						"WHERE fop.codigo=fdop.cod_ordenproduccionfk "+
     						"AND fdop.cod_medOrden_Produccionfk=fmedop.codigo "+
     						"AND fmedop.cod_medfk=m.codigo "+
     						"AND fmedop.cod_grupoffk=fgp.cod_grupoFarmacologia "+
     						"AND fdop.estado=0 AND fop.codigo='"+codop+"' "+
     						"AND fdop.volumen_final>='"+a+"' AND fdop.volumen_final<='"+b+"' "+
     						"ORDER BY fdop.volumen_final, fgp.ident_riesgo ASC ");
        		}else{
        			  if(tipo.equals("9")){
				         	rs=st.executeQuery("SELECT fgp.descripcion, fdop.cod_medOrden_Produccionfk, fmedop.cod_medfk, m.nombre, fdop.frecuencia, fdop.volumen_final, fdop.cod_formulafk "+
				         						"FROM farc_orden_produccion fop, farc_detalle_orden_produccion fdop, medicamentos m, farc_med_orden_produccion fmedop,farc_grupofarmacologico fgp "+
				         						"WHERE fop.codigo=fdop.cod_ordenproduccionfk "+
				         						"AND fdop.cod_medOrden_Produccionfk=fmedop.codigo "+
				         						"AND fmedop.cod_medfk=m.codigo "+
				         						"AND fmedop.cod_grupoffk=fgp.cod_grupoFarmacologia "+
				         						"AND fdop.estado=0 AND fop.codigo='"+codop+"' "+
				         						"AND fdop.volumen_final>='"+a+"' "+
				         						"ORDER BY fdop.volumen_final, fgp.ident_riesgo ASC ");
				        	}else{
				        		rs=st.executeQuery("SELECT fgp.descripcion, fdop.cod_medOrden_Produccionfk, fmedop.cod_medfk, m.nombre, fdop.frecuencia, fdop.volumen_final, fdop.cod_formulafk "+
				 						"FROM farc_orden_produccion fop, farc_detalle_orden_produccion fdop, medicamentos m, farc_med_orden_produccion fmedop,farc_grupofarmacologico fgp "+
				 						"WHERE fop.codigo=fdop.cod_ordenproduccionfk "+
				 						"AND fdop.cod_medOrden_Produccionfk=fmedop.codigo "+
				 						"AND fmedop.cod_medfk=m.codigo "+
				 						"AND fmedop.cod_grupoffk=fgp.cod_grupoFarmacologia "+
				 						"AND fdop.estado=0 AND fop.codigo='"+codop+"' "+
				 						"AND fdop.volumen_final>'"+a+"' AND fdop.volumen_final<='"+b+"'  "+
				 						"ORDER BY fdop.volumen_final, fgp.ident_riesgo ASC ");
				        		System.out.println("entrenado "+codop);
				        	}
        		}
        	}  	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoExistencias>>BuscarOrdenPV "+ex);
        }	
        return rs;
    }
	



	public java.sql.ResultSet Buscarfrec(String codop, String CodMedProd, String CodMed){	
		/**
		 * Buscar la frecuencia y el volumen final del medicamento en mencion 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT fgp.descripcion, m.nombre,fdop.frecuencia,fdop.volumen_final,fdop.cod_formulafk, fdop.dosis,fmedop.concentracion "+
        						"FROM farc_orden_produccion fop, farc_detalle_orden_produccion fdop, medicamentos m, farc_med_orden_produccion fmedop,farc_grupofarmacologico fgp "+
        						"WHERE fop.codigo=fdop.cod_ordenproduccionfk "+
        						"AND fdop.cod_medOrden_Produccionfk=fmedop.codigo "+
        						"AND fmedop.cod_medfk=m.codigo "+
        						"AND fmedop.cod_grupoffk=fgp.cod_grupoFarmacologia "+
        						"AND fdop.estado=0 AND fop.codigo='"+codop+"' AND fdop.cod_medOrden_Produccionfk='"+CodMedProd+"'  AND fmedop.cod_medfk='"+CodMed+"' "+
        						"ORDER BY fgp.ident_riesgo,fdop.cod_medOrden_Produccionfk, fdop.volumen_final");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoExistencias>>Buscarfrec "+ex);
        }	
        return rs;
    }


public java.sql.ResultSet BuscarGrupoFarmacologico(){	
		/**
		 * consulta los grupos farmacologicos existentes 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT cod_grupoFarmacologia, descripcion FROM farc_grupofarmacologico where cod_grupoFarmacologia>0 ORDER BY descripcion ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoExistencias>>BuscarGrupoFarmacologico "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet ObtenerMedOrdenP(String cm,String grupof,String concen,String diluy,String codi,String VR,String VAdm,String Est){	
		/**
		 * consulta el medicamento que esta en orden de produccion
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT codigo FROM farc_med_orden_produccion WHERE cod_medfk='"+cm+"' AND  concentracion='"+concen+"' AND diluyente='"+diluy+"' AND VR='"+VR+"' AND cod_interno='"+codi+"' AND cod_grupoffk='"+grupof+"' AND estabilidad='"+Est+"' AND via_admon='"+VAdm+"'");
        	System.out.println("SELECT codigo FROM farc_med_orden_produccion WHERE cod_medfk='"+cm+"' AND  concentracion='"+concen+"' AND diluyente='"+diluy+"' AND VR='"+VR+"' AND cod_interno='"+codi+"' AND cod_grupoffk='"+grupof+"' AND estabilidad='"+Est+"' AND via_admon='"+VAdm+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoExistencias>>ObtenerMedOrdenP "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet BuscarViaAdmon(){	
		/**
		 * consulta las vias de administracion existentes
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT sigla, codigo,nombre FROM farc_via_admon");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoExistencias>>BuscarViaAdmon "+ex);
        }	
        return rs;
    }
	
	public void ModMedh(String cm,String concen,String diluy,String grupof,String codi,String VR,String VAdm,String Est,String CodmedH){
		
		// Modifica el medicamento habilitado
		
		PreparedStatement st = null;
	    try{
	    	Conexion con=new Conexion();
	    	st= con.conn.prepareStatement("update farc_med_orden_produccion set cod_medfk='"+cm+"' , concentracion='"+concen+"', diluyente='"+diluy+"' , VR='"+VR+"', cod_interno='"+codi+"' , cod_grupoffk='"+grupof+"' , estabilidad='"+Est+"', via_admon='"+VAdm+"' where codigo='"+CodmedH+"'");
	    	st.executeUpdate();
	    	st.close();
	    	con.cerrar();
	    	
	    }
	    catch(Exception ex){
	    	System.out.println("ERROR EN MetodoExistencias>>ModMedh "+ex);
	    
    }	
	}


		
	public void ModCteV(String CodCteV, String Val, String Desc){
		
		// Modifica la cte de volumen asignada
		
		PreparedStatement st = null;
	    try{
	    	Conexion con=new Conexion();
	    	st= con.conn.prepareStatement("update farc_constantes_volumen set decripcion='"+Desc+"' , valor='"+Val+"'  where codigo='"+CodCteV+"'");
	    	st.executeUpdate();
	    	st.close();
	    	con.cerrar();
	    	
	    }
	    catch(Exception ex){
	    	System.out.println("ERROR EN MetodoExistencias>>ModMedh "+ex);
	    
    }	
	}


	
		public java.sql.ResultSet BuscarMedHabilitado(){	
		/**
		 * consulta los medicamentos habilitados
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT fmedp.cod_interno,m.nombre, fmedp.concentracion, fmedp.diluyente,fmedp.VR, fgf.descripcion, fc.valor, fc.descripcion, fmedp.estabilidad, fmedp.via_admon, fmedp.codigo, fc.codigo "+
        						"FROM farc_med_orden_produccion fmedp, farc_grupofarmacologico fgf,farc_constantes_volumen fc, medicamentos m "+
        						"WHERE fmedp.cod_grupoffk=fgf.cod_grupoFarmacologia "+
        						"AND fc.cod_med_orden_producionfk=fmedp.codigo "+
        						"AND m.codigo=fmedp.cod_medfk ORDER BY fmedp.cod_interno");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoExistencias>>BuscarMedHabilitado "+ex);
        }	
        return rs;
    }


	public java.sql.ResultSet BuscarCteV(String CodCteV){	
		/**
		 * consulta los medicamentos habilitados
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT codigo,cod_med_orden_producionfk,descripcion,valor FROM farc_constantes_volumen WHERE codigo='"+CodCteV+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoExistencias>>BuscarCteV "+ex);
        }	
        return rs;
    }


	
	public java.sql.ResultSet ObtenerMedH(String CodmedH,String CodCteV){	
		/**
		 * consulta los medicamentos habilitados
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT fmedp.cod_interno,m.nombre, fmedp.concentracion, fmedp.diluyente,fmedp.VR, fgf.descripcion, fc.valor, fc.descripcion, fmedp.estabilidad, fmedp.via_admon, fmedp.codigo, fc.codigo ,fgf.cod_grupoFarmacologia, m.codigo "+
        						"FROM farc_med_orden_produccion fmedp, farc_grupofarmacologico fgf,farc_constantes_volumen fc, medicamentos m "+
        						"WHERE fmedp.cod_grupoffk=fgf.cod_grupoFarmacologia "+
        						"AND fc.cod_med_orden_producionfk=fmedp.codigo "+
        						"AND m.codigo=fmedp.cod_medfk AND fmedp.codigo='"+CodmedH+"' AND fc.codigo='"+CodCteV+"'");
        	System.out.println("SELECT fmedp.cod_interno,m.nombre, fmedp.concentracion, fmedp.diluyente,fmedp.VR, fgf.descripcion, fc.valor, fc.descripcion, fmedp.estabilidad, fmedp.via_admon, fmedp.codigo, fc.codigo "+
					"FROM farc_med_orden_produccion fmedp, farc_grupofarmacologico fgf,farc_constantes_volumen fc, medicamentos m "+
					"WHERE fmedp.cod_grupoffk=fgf.cod_grupoFarmacologia "+
					"AND fc.cod_med_orden_producionfk=fmedp.codigo "+
					"AND m.codigo=fmedp.cod_medfk AND fmedp.codigo='"+CodmedH+"' AND fc.codigo='"+CodCteV+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoExistencias>>ObtenerMedH "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarMedCtaVolumen(String codigo){	
		
		//verifica si el mediacamento creado en habilitacion esta en constante de volumen

	   java.sql.ResultSet rs=null;
	   Statement st = null;
	   try{
	   	Conexion con=new Conexion();
	   	st = con.conn.createStatement();
	   	rs=st.executeQuery("SELECT codigo FROM farc_constantes_volumen WHERE cod_med_orden_producionfk='"+codigo+"'");
	   }
	   catch(Exception ex){
	   	System.out.println("Error en MetodoCrearProveedor>>BuscarMedCtaVolumen"+ex);
	   }	
	   return rs;
	}
	public java.sql.ResultSet ObtenerConstVol(String codigo,String valor,String desc){	
		/**
		 * consulta el las constantes de volumen 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT codigo FROM farc_constantes_volumen WHERE cod_med_orden_producionfk='"+codigo+"' AND descripcion='"+desc+"' AND valor='"+valor+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoExistencias>>ObtenerConstVol "+ex);
        }	
        return rs;
    }
	public void CrearMedOrdenP(String cm,String grupof,String concen,String diluy,String codi,String VR,String Est,String VAdm){
		
		// creamos los ingresos de movimientos
		
		CrearMedOrdenP cmed = new CrearMedOrdenP();
		
		cmed.setcm(cm);
		cmed.setgrupof(grupof);
		cmed.setconcen(concen);
		cmed.setdiluy(diluy);
		cmed.setcodi(codi);
		cmed.setVR(VR);
		cmed.setEst(Est);
		cmed.setVAdm(VAdm);
		System.out.println("estabilidad en el metodo"+Est);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("insert into farc_med_orden_produccion(cod_medfk,concentracion,diluyente,VR,cod_interno,cod_grupoffk,estabilidad,via_admon)values(?,?,?,?,?,?,?,?)");
			    ps.setString(1,cmed.getcm());
			    ps.setString(2,cmed.getconcen());
			    ps.setString(3,cmed.getdiluy());
			    ps.setString(4,cmed.getVR());
			    ps.setString(5,cmed.getcodi());
			    ps.setString(6,cmed.getgrupof());
			    ps.setString(7,cmed.getEst());
			    ps.setString(8, cmed.getVAdm());
			    ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoEXistencias>>CrearMedOrdenP "+ex);
			}

		}
	
	public void CrearConstVol(String codigo,String valor,String desc){
		
		// creamos los ingresos de las constantes de volumen
		
		CrearConstVolumen cvol = new CrearConstVolumen();
		
		cvol.setcod_medfk(codigo);
		cvol.setvalor(valor);
		cvol.setdesc(desc);
		
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("insert into farc_constantes_volumen(cod_med_orden_producionfk,descripcion,valor)values(?,?,?)");
			    ps.setString(1,cvol.getcod_medfk());
			    ps.setString(2,cvol.getdesc());
			    ps.setString(3,cvol.getvalor());
			    ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoEXistencias>>CrearConstVol "+ex);
			}

		}
	



	public java.sql.ResultSet ObtenerExistencias(){	
		/**
		 * consulta que obtiene las unidades del modulo de farmacia, 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT m.nombre, m.concentracion, i.lote, i.vencimiento, i.cantidad, i.vunitario, iv.valor, i.vtotal, e.nombre AS empresa, e.nit, e.logo FROM medicamentos m, farc_inventario i, farc_iva iv, empresa e WHERE i.cantidad>0 AND m.codigo=i.cod_medFK AND iv.codigo=i.cod_ivaFK ORDER BY m.nombre");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoEntradas>>ObtenerExistencias "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerLogo(){	
		/**
		 * consulta que obtiene las unidades del modulo de farmacia, 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        byte[] img = new byte[300000]; 
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT nombre, nit, logo FROM  empresa ");
          
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoEntradas>>ObtenerExistencias "+ex);
        }	
        return rs;
    }
	
	
	/////////////////
	

	
	////////////////
	
	public java.sql.ResultSet ObtenerBodegaso(String x){	
		/**
		 * consulta que obtiene las unidades del modulo de farmacia, 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo, nombre from farc_bodegas where codigo="+x+"");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoEntradas>>ObtenerBodegas "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerBodegasd(String x){	
		/**
		 * consulta que obtiene las unidades del modulo de farmacia, 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo, nombre from farc_bodegas where codigo!="+x+"");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoEntradas>>ObtenerBodegas "+ex);
        }	
        return rs;
    }
	
	
	public ResultSet listarArticulos(String texto, String xx) throws Exception {
		/**
		 */
		  java.sql.ResultSet r=null;
	        Statement st = null;
	       Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	//cesar listar art pero de la tabla inv
	        	r=st.executeQuery("select m.codigo,m.nombre,m.concentracion,i.lote,i.invima,i.vencimiento,i.cantidad,i.vunitario,p.razon_social,i.codigo from medicamentos m, farc_inventario i, farc_proveedor p where m.codigo=i.cod_medFk and p.codigo=i.cod_proveedorFk and i.cod_bodegaFk='"+xx+"' and i.cantidad>0 and m.nombre like '"+texto+"%'");
	        return r;
	    }
	
	////////////////////////////////////////
	public ResultSet listarMovimientoss() {
		/**
		 */
		  java.sql.ResultSet r=null;
	      Statement st = null;
	      try{
	       Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	r=st.executeQuery("select codigo,descripcion from farc_tipomovimiento where cod_movFk='2'");
	      }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoEntradas>>ObtenerBodegas "+ex);
	        }
	        	return r;     
	    }
	
	


public java.sql.ResultSet BusCodMedConsumo(String Fechai, String Fechaf){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 
 System.out.println("Fechai"+Fechai+"Fechaf"+Fechaf+"");
   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery("SELECT med.codigo AS codmed "+
   						"FROM hic_formulacion hf, hic_detalle_formulacion hdf,farc_formafarmaceutica fff,  farc_mov_dispensa fmd, medicamentos med, farc_inventario i, seg_usuario u, seg_datos_personales ud, adm_paciente ap "+
   						"WHERE hf.codigo=hdf.CodFormulacion_fk "+
   						"AND fmd.cod_formulacionfk=hf.codigo "+
   						"and fmd.cod_invfk=i.codigo "+
   						"and i.cod_medFk=med.codigo "+
   						"AND med.codigo=hdf.CodMedicamento_fk "+
   						"AND fff.codigo=med.cod_formaFK "+
   						"AND i.codigo=fmd.cod_invfk  "+
   						"AND u.usu_codigo=hf.CodUsu_fk "+
   						"AND ap.pac_codigo_paciente=hf.CodPac_fk "+
   						"AND u.dat_codigo_fk=ud.dat_codigo "+
   						"AND fmd.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"' GROUP BY med.codigo ORDER BY med.nombre");
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoExistencias>>BusCodMedConsumo "+ex);
   }	
   return rs;
}


public java.sql.ResultSet BuscarEntProd(String Fechai, String Fechaf, String bodega, String cm){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 
 System.out.println("Fechai"+Fechai+"Fechaf"+Fechaf+"");
   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	if(bodega.equals("todas")){
   	rs=st.executeQuery("SELECT fm.codigo AS codmov, fm.consec, fm.concepto, CONCAT(sdp.nombre,' ',sdp.apellido) AS usuario , fdm.cantidad AS fmcantidad, fi.cantinicial AS ficantinicial,ftm.descripcion, fb.nombre AS BODEGA, CONCAT(m.nombre,' ',m.concentracion,' ',fff.forma_farmaceutica) AS Medicamento, fi.lote, fm.fecha, fi.vunitario, fi.vtotal, ftm.cod_movFk "+
   						"FROM farc_movimientos fm, farc_inventario fi, seg_usuario u, seg_datos_personales sdp, cont_movimiento cm, farc_tipomovimiento ftm, farc_bodegas fb, empresa e, medicamentos m, farc_formafarmaceutica fff, farc_detallemov fdm "+
   						"WHERE fm.codigo=fdm.cod_movFk "+
   						"AND fi.codigo=fdm.cod_invFk "+
   						"AND fm.cod_usuarioFk=u.usu_codigo "+
   						"AND u.dat_codigo_fk=sdp.dat_codigo "+
   						"AND cm.codigo=ftm.cod_movFk "+
   						"AND ftm.codigo=fm.tipo_mvtoFk " +
   						"AND fb.codigo=fi.cod_bodegaFk  "+
   						"AND m.codigo=fi.cod_medFk AND ftm.cod_movFk=1 and m.codigo='"+cm+"'"+
   						"AND fff.codigo=m.cod_formaFK AND fm.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"'");
   	}else{
   		rs=st.executeQuery("SELECT fm.codigo AS codmov, fm.consec, fm.concepto, CONCAT(sdp.nombre,' ',sdp.apellido) AS usuario , fdm.cantidad AS fmcantidad, fi.cantinicial AS ficantinicial,ftm.descripcion, fb.nombre AS BODEGA, CONCAT(m.nombre,' ',m.concentracion,' ',fff.forma_farmaceutica) AS Medicamento, fi.lote, fm.fecha, fi.vunitario, fi.vtotal, ftm.cod_movFk "+
					"FROM farc_movimientos fm, farc_inventario fi, seg_usuario u, seg_datos_personales sdp, cont_movimiento cm, farc_tipomovimiento ftm, farc_bodegas fb, empresa e, medicamentos m, farc_formafarmaceutica fff, farc_detallemov fdm "+
					"WHERE fm.codigo=fdm.cod_movFk "+
					"AND fi.codigo=fdm.cod_invFk "+
					"AND fm.cod_usuarioFk=u.usu_codigo "+
					"AND u.dat_codigo_fk=sdp.dat_codigo "+
					"AND cm.codigo=ftm.cod_movFk "+
					"AND ftm.codigo=fm.tipo_mvtoFk " +
					"AND fb.codigo=fi.cod_bodegaFk  and m.codigo='"+cm+"'"+
					"AND m.codigo=fi.cod_medFk AND ftm.cod_movFk=1 and fdm.cod_bodegai='"+bodega+"'"+
					"AND fff.codigo=m.cod_formaFK AND fm.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"'");
   	}
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoExistencias>>BuscarEntProd "+ex);
   }	
   return rs;
}



public java.sql.ResultSet BuscarMovFarmacia(String Fechai, String Fechaf, String bodega){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 
 System.out.println("Fechai"+Fechai+"Fechaf"+Fechaf+"");
   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	if(bodega.equals("todas")){
   	rs=st.executeQuery("SELECT fm.codigo AS codmov, fm.consec, fm.concepto, CONCAT(sdp.nombre,' ',sdp.apellido) AS usuario , fdm.cantidad AS fmcantidad, fi.cantinicial AS ficantinicial,ftm.descripcion, fb.nombre AS BODEGA, CONCAT(m.nombre,' ',m.concentracion,' ',fff.forma_farmaceutica) AS Medicamento, fi.lote, fm.fecha, fi.vunitario, fi.vtotal, ftm.cod_movFk, fm.hora , fm.concepto, ftm.codigo, fm.nsoporte "+
   						"FROM farc_movimientos fm, farc_inventario fi, seg_usuario u, seg_datos_personales sdp, cont_movimiento cm, farc_tipomovimiento ftm, farc_bodegas fb, empresa e, medicamentos m, farc_formafarmaceutica fff, farc_detallemov fdm "+
   						"WHERE fm.codigo=fdm.cod_movFk "+
   						"AND fi.codigo=fdm.cod_invFk "+
   						"AND fm.cod_usuarioFk=u.usu_codigo "+
   						"AND u.dat_codigo_fk=sdp.dat_codigo "+
   						"AND cm.codigo=ftm.cod_movFk "+
   						"AND ftm.codigo=fm.tipo_mvtoFk "+
   						"AND fb.codigo=fi.cod_bodegaFk  "+
   						"AND m.codigo=fi.cod_medFk "+
   						"AND fff.codigo=m.cod_formaFK AND fm.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"'");
   	}else{
   		rs=st.executeQuery("SELECT fm.codigo AS codmov, fm.consec, fm.concepto, CONCAT(sdp.nombre,' ',sdp.apellido) AS usuario , fdm.cantidad AS fmcantidad, fi.cantinicial AS ficantinicial,ftm.descripcion, fb.nombre AS BODEGA, CONCAT(m.nombre,' ',m.concentracion,' ',fff.forma_farmaceutica) AS Medicamento, fi.lote, fm.fecha, fi.vunitario, fi.vtotal, ftm.cod_movFk ,fm.hora , fm.concepto,ftm.codigo, fm.nsoporte "+
					"FROM farc_movimientos fm, farc_inventario fi, seg_usuario u, seg_datos_personales sdp, cont_movimiento cm, farc_tipomovimiento ftm, farc_bodegas fb, empresa e, medicamentos m, farc_formafarmaceutica fff, farc_detallemov fdm "+
					"WHERE fm.codigo=fdm.cod_movFk "+
					"AND fi.codigo=fdm.cod_invFk "+
					"AND fm.cod_usuarioFk=u.usu_codigo "+
					"AND u.dat_codigo_fk=sdp.dat_codigo "+
					"AND cm.codigo=ftm.cod_movFk "+
					"AND ftm.codigo=fm.tipo_mvtoFk "+
					"AND fb.codigo=fi.cod_bodegaFk  "+
					"AND m.codigo=fi.cod_medFk "+
					"AND fff.codigo=m.cod_formaFK AND fdm.cod_bodegai='"+bodega+"' AND fm.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"'");
   	}
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoExistencias>>BuscarMovFarmacia "+ex);
   }	
   return rs;
}



public java.sql.ResultSet BuscarMovEnt_Tras(String Fechai, String Fechaf, String bodega,String mov){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 
 System.out.println("Fechai"+Fechai+"Fechaf"+Fechaf+"");
   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	if((bodega.equals("todas"))&&(mov.equals("2"))){
   	rs=st.executeQuery("SELECT fm.codigo AS codmov, fm.consec, fm.concepto, CONCAT(sdp.nombre,' ',sdp.apellido) AS usuario , fdm.cantidad AS fmcantidad, fi.cantinicial AS ficantinicial,ftm.descripcion, fb.nombre AS BODEGA, CONCAT(m.nombre,' ',m.concentracion,' ',fff.forma_farmaceutica) AS Medicamento, fi.lote, fm.fecha, fi.vunitario, fi.vtotal, ftm.cod_movFk, fm.hora, fm.concepto, fm.codigo, ftm.codigo, fm.nsoporte "+
   						"FROM farc_movimientos fm, farc_inventario fi, seg_usuario u, seg_datos_personales sdp, cont_movimiento cm, farc_tipomovimiento ftm, farc_bodegas fb, empresa e, medicamentos m, farc_formafarmaceutica fff, farc_detallemov fdm "+
   						"WHERE fm.codigo=fdm.cod_movFk "+
   						"AND fi.codigo=fdm.cod_invFk "+
   						"AND fm.cod_usuarioFk=u.usu_codigo "+
   						"AND u.dat_codigo_fk=sdp.dat_codigo "+
   						"AND cm.codigo=ftm.cod_movFk "+
   						"AND ftm.codigo=fm.tipo_mvtoFk " +
   						"AND fb.codigo=fi.cod_bodegaFk  "+
   						"AND m.codigo=fi.cod_medFk AND ftm.cod_movFk=1 "+
   						"AND fff.codigo=m.cod_formaFK AND fm.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"'");
   	}else{
   		if(mov.equals("2")){
   			rs=st.executeQuery("SELECT fm.codigo AS codmov, fm.consec, fm.concepto, CONCAT(sdp.nombre,' ',sdp.apellido) AS usuario , fdm.cantidad AS fmcantidad, fi.cantinicial AS ficantinicial,ftm.descripcion, fb.nombre AS BODEGA, CONCAT(m.nombre,' ',m.concentracion,' ',fff.forma_farmaceutica) AS Medicamento, fi.lote, fm.fecha, fi.vunitario, fi.vtotal, ftm.cod_movFk, fm.hora, fm.concepto, fm.codigo, ftm.codigo, fm.nsoporte "+
   						"FROM farc_movimientos fm, farc_inventario fi, seg_usuario u, seg_datos_personales sdp, cont_movimiento cm, farc_tipomovimiento ftm, farc_bodegas fb, empresa e, medicamentos m, farc_formafarmaceutica fff, farc_detallemov fdm "+
   						"WHERE fm.codigo=fdm.cod_movFk "+
   						"AND fi.codigo=fdm.cod_invFk "+
   						"AND fm.cod_usuarioFk=u.usu_codigo "+
   						"AND u.dat_codigo_fk=sdp.dat_codigo "+
   						"AND cm.codigo=ftm.cod_movFk "+
   						"AND ftm.codigo=fm.tipo_mvtoFk " +
   						"AND fb.codigo=fi.cod_bodegaFk  "+
   						"AND m.codigo=fi.cod_medFk AND ftm.cod_movFk=1 and fdm.cod_bodegai='"+bodega+"' "+
   						"AND fff.codigo=m.cod_formaFK AND fm.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"'");
   		}else{
   			if((bodega.equals("todas"))&&(mov.equals("3"))){
   				rs=st.executeQuery("SELECT fm.codigo AS codmov, fm.consec, fm.concepto, CONCAT(sdp.nombre,' ',sdp.apellido) AS usuario , fdm.cantidad AS fmcantidad, fi.cantinicial AS ficantinicial,ftm.descripcion, fb.nombre AS BODEGA, CONCAT(m.nombre,' ',m.concentracion,' ',fff.forma_farmaceutica) AS Medicamento, fi.lote, fm.fecha, fi.vunitario, fi.vtotal, ftm.cod_movFk , fm.hora, fm.concepto, fm.codigo, ftm.codigo , fm.nsoporte "+
   						"FROM farc_movimientos fm, farc_inventario fi, seg_usuario u, seg_datos_personales sdp, cont_movimiento cm, farc_tipomovimiento ftm, farc_bodegas fb, empresa e, medicamentos m, farc_formafarmaceutica fff, farc_detallemov fdm "+
   						"WHERE fm.codigo=fdm.cod_movFk "+
   						"AND fi.codigo=fdm.cod_invFk "+
   						"AND fm.cod_usuarioFk=u.usu_codigo "+
   						"AND u.dat_codigo_fk=sdp.dat_codigo "+
   						"AND cm.codigo=ftm.cod_movFk "+
   						"AND ftm.codigo=fm.tipo_mvtoFk " +
   						"AND fb.codigo=fi.cod_bodegaFk  "+
   						"AND m.codigo=fi.cod_medFk AND ftm.cod_movFk=3 "+
   						"AND fff.codigo=m.cod_formaFK AND fm.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"'");
   			}else{
   				if(mov.equals("3")){
   					rs=st.executeQuery("SELECT fm.codigo AS codmov, fm.consec, fm.concepto, CONCAT(sdp.nombre,' ',sdp.apellido) AS usuario , fdm.cantidad AS fmcantidad, fi.cantinicial AS ficantinicial,ftm.descripcion, fb.nombre AS BODEGA, CONCAT(m.nombre,' ',m.concentracion,' ',fff.forma_farmaceutica) AS Medicamento, fi.lote, fm.fecha, fi.vunitario, fi.vtotal, ftm.cod_movFk, fm.hora, fm.concepto, fm.codigo, ftm.codigo, fm.nsoporte "+
   	   						"FROM farc_movimientos fm, farc_inventario fi, seg_usuario u, seg_datos_personales sdp, cont_movimiento cm, farc_tipomovimiento ftm, farc_bodegas fb, empresa e, medicamentos m, farc_formafarmaceutica fff, farc_detallemov fdm "+
   	   						"WHERE fm.codigo=fdm.cod_movFk "+
   	   						"AND fi.codigo=fdm.cod_invFk "+
   	   						"AND fm.cod_usuarioFk=u.usu_codigo "+
   	   						"AND u.dat_codigo_fk=sdp.dat_codigo "+
   	   						"AND cm.codigo=ftm.cod_movFk "+
   	   						"AND ftm.codigo=fm.tipo_mvtoFk " +
   	   						"AND fb.codigo=fi.cod_bodegaFk  "+
   	   						"AND m.codigo=fi.cod_medFk AND ftm.cod_movFk=3 and fdm.cod_bodegai='"+bodega+"' "+
   	   						"AND fff.codigo=m.cod_formaFK AND fm.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"'");
   				}
   			}
   		}
   		
   	}
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoExistencias>>BuscarMovEnt_Tras "+ex);
   }	
   return rs;
}


public java.sql.ResultSet BuscarMovSal(String Fechai, String Fechaf,String bodega,String mov){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 
 System.out.println("Fechai"+Fechai+"Fechaf"+Fechaf+"");
   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	if(bodega.equals("todas")&&(mov.equals("4"))){
   	rs=st.executeQuery("SELECT fm.codigo AS codmov, fm.consec, fm.concepto, CONCAT(sdp.nombre,' ',sdp.apellido) AS usuario , fdm.cantidad AS fmcantidad, fi.cantinicial AS ficantinicial,ftm.descripcion, fb.nombre AS BODEGA, CONCAT(m.nombre,' ',m.concentracion,' ',fff.forma_farmaceutica) AS Medicamento, fi.lote, fm.fecha, fi.vunitario, fi.vtotal, ftm.cod_movFk , fm.hora, fm.nsoporte "+
   						"FROM farc_movimientos fm, farc_inventario fi, seg_usuario u, seg_datos_personales sdp, cont_movimiento cm, farc_tipomovimiento ftm, farc_bodegas fb, empresa e, medicamentos m, farc_formafarmaceutica fff, farc_detallemov fdm "+
   						"WHERE fm.codigo=fdm.cod_movFk "+
   						"AND fi.codigo=fdm.cod_invFk "+
   						"AND fm.cod_usuarioFk=u.usu_codigo "+ 
   						"AND u.dat_codigo_fk=sdp.dat_codigo "+
   						"AND cm.codigo=ftm.cod_movFk "+
   						"AND ftm.codigo=fm.tipo_mvtoFk "+
   						"AND fb.codigo=fi.cod_bodegaFk  "+
   						"AND m.codigo=fi.cod_medFk AND ftm.cod_movFk='2' "+
   						"AND fff.codigo=m.cod_formaFK "+
   						"AND concepto  NOT LIKE '%Salida con cargo a paciente%' AND fm.fecha BETWEEN '"+Fechai+"' and '"+Fechaf+"'");
  
   	}else{
   		if(mov.equals("4")){
	   		rs=st.executeQuery("SELECT fm.codigo AS codmov, fm.consec, fm.concepto, CONCAT(sdp.nombre,' ',sdp.apellido) AS usuario , fdm.cantidad AS fmcantidad, fi.cantinicial AS ficantinicial,ftm.descripcion, fb.nombre AS BODEGA, CONCAT(m.nombre,' ',m.concentracion,' ',fff.forma_farmaceutica) AS Medicamento, fi.lote, fm.fecha, fi.vunitario, fi.vtotal, ftm.cod_movFk, fm.hora , fm.nsoporte  "+
						"FROM farc_movimientos fm, farc_inventario fi, seg_usuario u, seg_datos_personales sdp, cont_movimiento cm, farc_tipomovimiento ftm, farc_bodegas fb, empresa e, medicamentos m, farc_formafarmaceutica fff, farc_detallemov fdm "+
						"WHERE fm.codigo=fdm.cod_movFk "+
						"AND fi.codigo=fdm.cod_invFk "+
						"AND fm.cod_usuarioFk=u.usu_codigo "+ 
						"AND u.dat_codigo_fk=sdp.dat_codigo "+
						"AND cm.codigo=ftm.cod_movFk "+
						"AND ftm.codigo=fm.tipo_mvtoFk "+
						"AND fb.codigo=fi.cod_bodegaFk  "+
						"AND m.codigo=fi.cod_medFk AND ftm.cod_movFk='2' "+
						"AND fff.codigo=m.cod_formaFK and fdm.cod_bodegai='"+bodega+"' "+
						"AND concepto  NOT LIKE '%Salida con cargo a paciente%' AND fm.fecha BETWEEN '"+Fechai+"' and '"+Fechaf+"'");
   		}
   	}
   	}
   catch(Exception ex){
   	System.out.println("Error en MetodoExistencias>>BuscarMovSal "+ex);
   }	
   return rs;
}



public java.sql.ResultSet BuscarTrasProd(String Fechai, String Fechaf,String bodega,String cm){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 
 System.out.println("Fechai"+Fechai+"Fechaf"+Fechaf+"");
   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	if(bodega.equals("todas")){
   	rs=st.executeQuery("SELECT fm.codigo AS codmov, fm.consec, fm.concepto, CONCAT(sdp.nombre,' ',sdp.apellido) AS usuario , fdm.cantidad AS fmcantidad, fi.cantinicial AS ficantinicial,ftm.descripcion, fb.nombre AS BODEGA, CONCAT(m.nombre,' ',m.concentracion,' ',fff.forma_farmaceutica) AS Medicamento, fi.lote, fm.fecha, fi.vunitario, fi.vtotal, ftm.cod_movFk , fm.hora, fm.nsoporte "+
					"FROM farc_movimientos fm, farc_inventario fi, seg_usuario u, seg_datos_personales sdp, cont_movimiento cm, farc_tipomovimiento ftm, farc_bodegas fb, empresa e, medicamentos m, farc_formafarmaceutica fff, farc_detallemov fdm "+
					"WHERE fm.codigo=fdm.cod_movFk "+
					"AND fi.codigo=fdm.cod_invFk "+
					"AND fm.cod_usuarioFk=u.usu_codigo "+
					"AND u.dat_codigo_fk=sdp.dat_codigo "+
					"AND cm.codigo=ftm.cod_movFk "+
					"AND ftm.codigo=fm.tipo_mvtoFk " +
					"AND fb.codigo=fi.cod_bodegaFk  "+
					"AND m.codigo=fi.cod_medFk AND ftm.cod_movFk=3 and m.codigo='"+cm+"' "+
					"AND fff.codigo=m.cod_formaFK AND fm.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"'");
  
   	}else{
   		
	   		rs=st.executeQuery("SELECT fm.codigo AS codmov, fm.consec, fm.concepto, CONCAT(sdp.nombre,' ',sdp.apellido) AS usuario , fdm.cantidad AS fmcantidad, fi.cantinicial AS ficantinicial,ftm.descripcion, fb.nombre AS BODEGA, CONCAT(m.nombre,' ',m.concentracion,' ',fff.forma_farmaceutica) AS Medicamento, fi.lote, fm.fecha, fi.vunitario, fi.vtotal, ftm.cod_movFk, fm.hora, fm.nsoporte "+
					"FROM farc_movimientos fm, farc_inventario fi, seg_usuario u, seg_datos_personales sdp, cont_movimiento cm, farc_tipomovimiento ftm, farc_bodegas fb, empresa e, medicamentos m, farc_formafarmaceutica fff, farc_detallemov fdm "+
					"WHERE fm.codigo=fdm.cod_movFk "+
					"AND fi.codigo=fdm.cod_invFk "+
					"AND fm.cod_usuarioFk=u.usu_codigo "+
					"AND u.dat_codigo_fk=sdp.dat_codigo "+
					"AND cm.codigo=ftm.cod_movFk "+
					"AND ftm.codigo=fm.tipo_mvtoFk " +
					"AND fb.codigo=fi.cod_bodegaFk  "+
					"AND m.codigo=fi.cod_medFk AND ftm.cod_movFk=3 and m.codigo='"+cm+"' and fdm.cod_bodegai='"+bodega+"' "+
					"AND fff.codigo=m.cod_formaFK AND fm.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"'");
   		
   	}
   	}
   catch(Exception ex){
   	System.out.println("Error en MetodoExistencias>>BuscarTrasProd "+ex);
   }	
   return rs;
}



public java.sql.ResultSet BuscarDevProd(String Fechai, String Fechaf,String bodega,String cm){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 
 System.out.println("Fechai"+Fechai+"Fechaf"+Fechaf+"");
   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	if(bodega.equals("todas")){
   	rs=st.executeQuery("SELECT fd.codigo,fd.cantidad, fd.concepto, CONCAT(sdp.nombre,' ',sdp.apellido) AS usuario, CONCAT(m.nombre,' ', m.concentracion,' ', fff.forma_farmaceutica) AS MED,fi.lote, fd.fecha,fi.vunitario,fi.vtotal, fmovd.concepto,fb.nombre,fb.nombre,fb.nombre,fd.hora,hf.codigo "+
				"FROM farc_devolucion fd, hic_formulacion hf, hic_detalle_formulacion hdf, adm_paciente ap, medicamentos m, farc_formafarmaceutica fff, farc_inventario fi, farc_mov_dispensa fmovd, seg_usuario u, seg_datos_personales sdp, farc_bodegas fb "+
					"WHERE fd.cod_invFk=fi.codigo "+
					"AND fd.codpaciente=ap.pac_codigo_paciente "+
					"AND fd.cod_mov_dispensafk=fmovd.codigo "+
					"AND hf.codigo=hdf.CodFormulacion_fk  "+
					"AND hf.CodPac_fk=ap.pac_codigo_paciente "+
					"AND hdf.CodMedicamento_fk=m.codigo "+
					"AND m.cod_formaFK=fff.codigo "+
					"AND m.codigo=fi.cod_medFk  "+
					"AND fmovd.cod_invfk=fi.codigo "+ 
					"AND hf.codigo=fmovd.cod_formulacionfk "+
					"AND u.dat_codigo_fk=sdp.dat_codigo "+
					"AND fb.codigo=fi.cod_bodegaFk "+
					"AND fd.usuario=usu_codigo and m.codigo='"+cm+"' "+
					"AND fd.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"'");
   		}else{
   			rs=st.executeQuery("SELECT fd.codigo,fd.cantidad, fd.concepto, CONCAT(sdp.nombre,' ',sdp.apellido) AS usuario, CONCAT(m.nombre,' ', m.concentracion,' ', fff.forma_farmaceutica) AS MED,fi.lote, fd.fecha,fi.vunitario,fi.vtotal, fmovd.concepto,fb.nombre,fb.nombre,fb.nombre,fd.hora,hf.codigo "+
			"FROM farc_devolucion fd, hic_formulacion hf, hic_detalle_formulacion hdf, adm_paciente ap, medicamentos m, farc_formafarmaceutica fff, farc_inventario fi, farc_mov_dispensa fmovd, seg_usuario u, seg_datos_personales sdp, farc_bodegas fb "+
			"WHERE fd.cod_invFk=fi.codigo "+
			"AND fd.codpaciente=ap.pac_codigo_paciente "+
			"AND fd.cod_mov_dispensafk=fmovd.codigo "+
			"AND hf.codigo=hdf.CodFormulacion_fk  "+
			"AND hf.CodPac_fk=ap.pac_codigo_paciente "+
			"AND hdf.CodMedicamento_fk=m.codigo "+
			"AND m.cod_formaFK=fff.codigo "+
			"AND m.codigo=fi.cod_medFk  "+
			"AND fmovd.cod_invfk=fi.codigo "+ 
			"AND hf.codigo=fmovd.cod_formulacionfk "+
			"AND u.dat_codigo_fk=sdp.dat_codigo "+
			"AND fb.codigo=fi.cod_bodegaFk "+
			"AND fd.usuario=usu_codigo and m.codigo='"+cm+"' and fmovd.bodegai='"+bodega+"'"+
			"AND fd.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"'");

   	}
   	}
   catch(Exception ex){
   	System.out.println("Error en MetodoExistencias>>BuscarDevProd "+ex);
   }	
   return rs;
}




public java.sql.ResultSet BuscarSalProd(String Fechai, String Fechaf,String bodega,String cm){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 
 System.out.println("Fechai"+Fechai+"Fechaf"+Fechaf+"");
   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	if(bodega.equals("todas")){
   	rs=st.executeQuery("SELECT fm.codigo AS codmov, fm.consec, fm.concepto, CONCAT(sdp.nombre,' ',sdp.apellido) AS usuario , fdm.cantidad AS fmcantidad, fi.cantinicial AS ficantinicial,ftm.descripcion, fb.nombre AS BODEGA, CONCAT(m.nombre,' ',m.concentracion,' ',fff.forma_farmaceutica) AS Medicamento, fi.lote, fm.fecha, fi.vunitario, fi.vtotal, ftm.cod_movFk, fm.hora, fm.nsoporte "+
   						"FROM farc_movimientos fm, farc_inventario fi, seg_usuario u, seg_datos_personales sdp, cont_movimiento cm, farc_tipomovimiento ftm, farc_bodegas fb, empresa e, medicamentos m, farc_formafarmaceutica fff, farc_detallemov fdm "+
   						"WHERE fm.codigo=fdm.cod_movFk "+
   						"AND fi.codigo=fdm.cod_invFk "+
   						"AND fm.cod_usuarioFk=u.usu_codigo "+ 
   						"AND u.dat_codigo_fk=sdp.dat_codigo "+
   						"AND cm.codigo=ftm.cod_movFk "+
   						"AND ftm.codigo=fm.tipo_mvtoFk "+
   						"AND fb.codigo=fi.cod_bodegaFk  "+
   						"AND m.codigo=fi.cod_medFk AND ftm.cod_movFk='2' "+
   						"AND fff.codigo=m.cod_formaFK and m.codigo='"+cm+"' "+
   						"AND concepto  NOT LIKE '%Salida con cargo a paciente%' AND fm.fecha BETWEEN '"+Fechai+"' and '"+Fechaf+"'");
  
   	}else{
	   		rs=st.executeQuery("SELECT fm.codigo AS codmov, fm.consec, fm.concepto, CONCAT(sdp.nombre,' ',sdp.apellido) AS usuario , fdm.cantidad AS fmcantidad, fi.cantinicial AS ficantinicial,ftm.descripcion, fb.nombre AS BODEGA, CONCAT(m.nombre,' ',m.concentracion,' ',fff.forma_farmaceutica) AS Medicamento, fi.lote, fm.fecha, fi.vunitario, fi.vtotal, ftm.cod_movFk , fm.hora, fm.nsoporte "+
						"FROM farc_movimientos fm, farc_inventario fi, seg_usuario u, seg_datos_personales sdp, cont_movimiento cm, farc_tipomovimiento ftm, farc_bodegas fb, empresa e, medicamentos m, farc_formafarmaceutica fff, farc_detallemov fdm "+
						"WHERE fm.codigo=fdm.cod_movFk "+
						"AND fi.codigo=fdm.cod_invFk "+
						"AND fm.cod_usuarioFk=u.usu_codigo "+ 
						"AND u.dat_codigo_fk=sdp.dat_codigo "+
						"AND cm.codigo=ftm.cod_movFk "+
						"AND ftm.codigo=fm.tipo_mvtoFk "+
						"AND fb.codigo=fi.cod_bodegaFk  "+
						"AND m.codigo=fi.cod_medFk AND ftm.cod_movFk='2' "+
						"AND fff.codigo=m.cod_formaFK and fdm.cod_bodegai='"+bodega+"' and m.codigo='"+cm+"' "+
						"AND concepto  NOT LIKE '%Salida con cargo a paciente%' AND fm.fecha BETWEEN '"+Fechai+"' and '"+Fechaf+"'");
   		
   	}
   	}
   catch(Exception ex){
   	System.out.println("Error en MetodoExistencias>>BuscarSalProd "+ex);
   }	
   return rs;
}





public java.sql.ResultSet BuscarMovDev(String Fechai, String Fechaf, String bodega){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 
 System.out.println("Fechai"+Fechai+"Fechaf"+Fechaf+"");
   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	if(bodega.equals("todas")){
   	rs=st.executeQuery("SELECT fd.codigo,fd.cantidad, fd.concepto, CONCAT(sdp.nombre,' ',sdp.apellido) AS usuario, CONCAT(m.nombre,' ', m.concentracion,' ', fff.forma_farmaceutica) AS MED,fi.lote, fd.fecha,fi.vunitario,fi.vtotal, fmovd.concepto,fb.nombre,fb.nombre,fb.nombre, fd.hora, fd.concepto, hf.codigo "+
   						"FROM farc_devolucion fd, hic_formulacion hf, hic_detalle_formulacion hdf, adm_paciente ap, medicamentos m, farc_formafarmaceutica fff, farc_inventario fi, farc_mov_dispensa fmovd, seg_usuario u, seg_datos_personales sdp, farc_bodegas fb "+
   						"WHERE fd.cod_invFk=fi.codigo "+
   						"AND fd.codpaciente=ap.pac_codigo_paciente "+
   						"AND fd.cod_mov_dispensafk=fmovd.codigo "+
   						"AND hf.codigo=hdf.CodFormulacion_fk  "+
   						"AND hf.CodPac_fk=ap.pac_codigo_paciente "+
   						"AND hdf.CodMedicamento_fk=m.codigo "+
   						"AND m.cod_formaFK=fff.codigo "+
   						"AND m.codigo=fi.cod_medFk  "+
   						"AND fmovd.cod_invfk=fi.codigo "+ 
   						"AND hf.codigo=fmovd.cod_formulacionfk "+
   						"AND u.dat_codigo_fk=sdp.dat_codigo "+
   						"AND fb.codigo=fi.cod_bodegaFk "+
   						"AND fd.usuario=usu_codigo "+
   						"AND fd.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"'");
   	}else{
   		rs=st.executeQuery("SELECT fd.codigo,fd.cantidad, fd.concepto, CONCAT(sdp.nombre,' ',sdp.apellido) AS usuario, CONCAT(m.nombre,' ', m.concentracion,' ', fff.forma_farmaceutica) AS MED,fi.lote, fd.fecha,fi.vunitario,fi.vtotal, fmovd.concepto,fb.nombre,fb.nombre,fb.nombre, fd.hora, fd.concepto , hf.codigo "+
					"FROM farc_devolucion fd, hic_formulacion hf, hic_detalle_formulacion hdf, adm_paciente ap, medicamentos m, farc_formafarmaceutica fff, farc_inventario fi, farc_mov_dispensa fmovd, seg_usuario u, seg_datos_personales sdp, farc_bodegas fb "+
					"WHERE fd.cod_invFk=fi.codigo "+
					"AND fd.codpaciente=ap.pac_codigo_paciente "+
					"AND fd.cod_mov_dispensafk=fmovd.codigo "+
					"AND hf.codigo=hdf.CodFormulacion_fk  "+
					"AND hf.CodPac_fk=ap.pac_codigo_paciente "+
					"AND hdf.CodMedicamento_fk=m.codigo "+
					"AND m.cod_formaFK=fff.codigo "+
					"AND m.codigo=fi.cod_medFk  "+
					"AND fmovd.cod_invfk=fi.codigo "+ 
					"AND hf.codigo=fmovd.cod_formulacionfk "+
					"AND u.dat_codigo_fk=sdp.dat_codigo "+
					"AND fb.codigo=fi.cod_bodegaFk "+
					"AND fd.usuario=usu_codigo and fmovd.bodegai='"+bodega+"'"+
					"AND fd.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"'");
   	}
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoExistencias>>BuscarMovDev "+ex);
   }	
   return rs;
}



public java.sql.ResultSet BusRepDisPac(String Fechai, String Fechaf, int sql,String bodega,String med){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 
 System.out.println("Fechai"+Fechai+"Fechaf"+Fechaf+"");
   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	if(sql==1){
   	rs=st.executeQuery("SELECT * FROM(SELECT DISTINCT  ap.nombre AS nombrep,ap.primer_apellido AS primerap,ap.segundo_apellido AS segundoap,ap.tipo_documento AS Tipodoc, ap.numero_documento ,  fmd.codigo AS CodMovimient, fmd.cod_formulacionfk AS formula, fmd.fecha,fmd.hora, fmd.concepto, t.descripcion,m.nombre AS med, m.concentracion  AS concetracion , fi.lote, fi.vencimiento,hdf.cantidad AS cantidad_formulada,  fmd.cantidad_dispensada AS cantidad_dispensada, ' ' AS cantidad_devuelta,  fb.nombre AS bodega, ' ' AS fechadev , ' ' AS horadev, fmd.usuario AS userdisp, ' ' AS userdev "+
   						"FROM farc_mov_dispensa fmd, hic_formulacion hf , adm_paciente ap, farc_detallemov fdp, farc_dispensacion fd, farc_tipomovimiento t, farc_inventario fi, medicamentos m, hic_detalle_formulacion hdf , farc_bodegas fb "+
   						"WHERE fmd.cod_formulacionfk=hf.codigo AND hf.CodPac_fk=ap.pac_codigo_paciente AND fd.codigo=fmd.cod_dispensafk AND fdp.cod_invFk=fmd.cod_invfk AND t.codigo=fmd.tipo_mvtofk AND fmd.cod_invFk=fi.codigo AND m.codigo=hdf.CodMedicamento_fk AND hf.codigo=hdf.CodFormulacion_fk AND fi.cod_medFk=m.codigo AND  fb.codigo=fmd.bodegai AND fmd.codigo NOT IN (SELECT cod_mov_dispensafk FROM farc_devolucion WHERE fecha BETWEEN  '"+Fechai+"'  AND '"+Fechaf+"')AND fmd.fecha BETWEEN '"+Fechai+"'  AND '"+Fechaf+"' "+
   						"UNION "+
   						"SELECT DISTINCT  ap.nombre AS nombreap,ap.primer_apellido AS primerap,ap.segundo_apellido AS segundoap , ap.tipo_documento AS Tipodoc, ap.numero_documento, fmd.codigo AS CodMovimient, fmd.cod_formulacionfk AS formula, fmd.fecha,fmd.hora, fmd.concepto, t.descripcion,m.nombre AS med, m.concentracion AS concetracion, fi.lote, fi.vencimiento,hdf.cantidad AS cantidad_formulada, fmd.cantidad_dispensada AS cantidad_dispensada , fdev.cantidad AS cantidad_devuelta,fb.nombre AS bodega, fdev.fecha AS fechadev, fdev.hora AS horadev, fmd.usuario AS userdisp, fdev.usuario AS userdev "+
   						"FROM farc_mov_dispensa fmd, hic_formulacion hf , adm_paciente ap, farc_detallemov fdp, farc_dispensacion fd, farc_tipomovimiento t, farc_inventario fi, medicamentos m, hic_detalle_formulacion hdf, farc_devolucion fdev, farc_bodegas fb "+
   						"WHERE fdev.cod_dispensacionFk=fmd.cod_dispensafk AND fdev.cod_invFk=fi.codigo AND fdev.cod_mov_dispensafk=fmd.codigo AND fmd.cod_formulacionfk=hf.codigo AND hf.CodPac_fk=ap.pac_codigo_paciente AND fd.codigo=fmd.cod_dispensafk AND fdp.cod_invFk=fmd.cod_invfk AND t.codigo=fmd.tipo_mvtofk AND fmd.cod_invFk=fi.codigo AND m.codigo=hdf.CodMedicamento_fk AND hf.codigo=hdf.CodFormulacion_fk AND fi.cod_medFk=m.codigo  AND  fb.codigo=fmd.bodegai AND fmd.fecha BETWEEN '"+Fechai+"'  AND '"+Fechaf+"' ) AS a ORDER BY a.formula, a.med	");
 
   	}else{
	   if(sql==2){
		   rs=st.executeQuery("SELECT * FROM(SELECT DISTINCT  ap.nombre AS nombrep,ap.primer_apellido AS primerap,ap.segundo_apellido AS segundoap,ap.tipo_documento AS Tipodoc, ap.numero_documento ,  fmd.codigo AS CodMovimient, fmd.cod_formulacionfk AS formula, fmd.fecha,fmd.hora, fmd.concepto, t.descripcion,m.nombre AS med, m.concentracion  AS concetracion , fi.lote, fi.vencimiento,hdf.cantidad AS cantidad_formulada,  fmd.cantidad_dispensada AS cantidad_dispensada, ' ' AS cantidad_devuelta,  fb.nombre AS bodega, ' ' AS fechadev, ' ' AS horadev , fmd.usuario AS userdisp, ' ' AS userdev "+
						"FROM farc_mov_dispensa fmd, hic_formulacion hf , adm_paciente ap, farc_detallemov fdp, farc_dispensacion fd, farc_tipomovimiento t, farc_inventario fi, medicamentos m, hic_detalle_formulacion hdf , farc_bodegas fb "+
						"WHERE fmd.cod_formulacionfk=hf.codigo AND hf.CodPac_fk=ap.pac_codigo_paciente AND fd.codigo=fmd.cod_dispensafk AND fdp.cod_invFk=fmd.cod_invfk AND t.codigo=fmd.tipo_mvtofk AND fmd.cod_invFk=fi.codigo AND m.codigo=hdf.CodMedicamento_fk AND hf.codigo=hdf.CodFormulacion_fk AND fi.cod_medFk=m.codigo AND  fb.codigo=fmd.bodegai AND fmd.codigo NOT IN (SELECT cod_mov_dispensafk FROM farc_devolucion WHERE fecha BETWEEN  '"+Fechai+"'  AND '"+Fechaf+"')  AND fmd.bodegai='"+bodega+"' AND fmd.fecha BETWEEN '"+Fechai+"'  AND '"+Fechaf+"' "+
						"UNION "+
						"SELECT DISTINCT  ap.nombre AS nombreap,ap.primer_apellido AS primerap,ap.segundo_apellido AS segundoap , ap.tipo_documento AS Tipodoc, ap.numero_documento, fmd.codigo AS CodMovimient, fmd.cod_formulacionfk AS formula, fmd.fecha,fmd.hora, fmd.concepto, t.descripcion,m.nombre AS med, m.concentracion AS concetracion, fi.lote, fi.vencimiento,hdf.cantidad AS cantidad_formulada, fmd.cantidad_dispensada AS cantidad_dispensada , fdev.cantidad AS cantidad_devuelta,fb.nombre AS bodega, fdev.fecha AS fechadev, fdev.hora AS horadev , fmd.usuario AS userdisp, fdev.usuario AS userdev "+
						"FROM farc_mov_dispensa fmd, hic_formulacion hf , adm_paciente ap, farc_detallemov fdp, farc_dispensacion fd, farc_tipomovimiento t, farc_inventario fi, medicamentos m, hic_detalle_formulacion hdf, farc_devolucion fdev, farc_bodegas fb "+
						"WHERE fdev.cod_dispensacionFk=fmd.cod_dispensafk AND fdev.cod_invFk=fi.codigo AND fdev.cod_mov_dispensafk=fmd.codigo AND fmd.cod_formulacionfk=hf.codigo AND hf.CodPac_fk=ap.pac_codigo_paciente AND fd.codigo=fmd.cod_dispensafk AND fdp.cod_invFk=fmd.cod_invfk AND t.codigo=fmd.tipo_mvtofk AND fmd.cod_invFk=fi.codigo AND m.codigo=hdf.CodMedicamento_fk AND hf.codigo=hdf.CodFormulacion_fk AND fi.cod_medFk=m.codigo  AND  fb.codigo=fmd.bodegai AND fmd.bodegai='"+bodega+"' AND fmd.fecha BETWEEN '"+Fechai+"'  AND '"+Fechaf+"' ) AS a ORDER BY a.formula, a.med	");
	   }else{
		   if(sql==3){
				rs=st.executeQuery("SELECT * FROM(SELECT DISTINCT  ap.nombre AS nombrep,ap.primer_apellido AS primerap,ap.segundo_apellido AS segundoap,ap.tipo_documento AS Tipodoc, ap.numero_documento ,  fmd.codigo AS CodMovimient, fmd.cod_formulacionfk AS formula, fmd.fecha,fmd.hora, fmd.concepto, t.descripcion,m.nombre AS med, m.concentracion  AS concetracion , fi.lote, fi.vencimiento,hdf.cantidad AS cantidad_formulada,  fmd.cantidad_dispensada AS cantidad_dispensada, ' ' AS cantidad_devuelta,  fb.nombre AS bodega, ' ' AS fechadev, ' ' AS horadev , fmd.usuario AS userdisp, ' ' AS userdev "+
   						"FROM farc_mov_dispensa fmd, hic_formulacion hf , adm_paciente ap, farc_detallemov fdp, farc_dispensacion fd, farc_tipomovimiento t, farc_inventario fi, medicamentos m, hic_detalle_formulacion hdf , farc_bodegas fb "+
   						"WHERE fmd.cod_formulacionfk=hf.codigo AND hf.CodPac_fk=ap.pac_codigo_paciente AND fd.codigo=fmd.cod_dispensafk AND fdp.cod_invFk=fmd.cod_invfk AND t.codigo=fmd.tipo_mvtofk AND fmd.cod_invFk=fi.codigo AND m.codigo=hdf.CodMedicamento_fk AND hf.codigo=hdf.CodFormulacion_fk AND fi.cod_medFk=m.codigo AND  fb.codigo=fmd.bodegai AND fmd.codigo NOT IN (SELECT cod_mov_dispensafk FROM farc_devolucion WHERE fecha BETWEEN  '"+Fechai+"'  AND '"+Fechaf+"') and m.codigo='"+med+"' AND fmd.fecha BETWEEN '"+Fechai+"'  AND '"+Fechaf+"' "+
   						"UNION "+
   						"SELECT DISTINCT  ap.nombre AS nombreap,ap.primer_apellido AS primerap,ap.segundo_apellido AS segundoap , ap.tipo_documento AS Tipodoc, ap.numero_documento, fmd.codigo AS CodMovimient, fmd.cod_formulacionfk AS formula, fmd.fecha,fmd.hora, fmd.concepto, t.descripcion,m.nombre AS med, m.concentracion AS concetracion, fi.lote, fi.vencimiento,hdf.cantidad AS cantidad_formulada, fmd.cantidad_dispensada AS cantidad_dispensada , fdev.cantidad AS cantidad_devuelta,fb.nombre AS bodega, fdev.fecha AS fechadev, fdev.hora AS horadev , fmd.usuario AS userdisp, fdev.usuario AS userdev "+
   						"FROM farc_mov_dispensa fmd, hic_formulacion hf , adm_paciente ap, farc_detallemov fdp, farc_dispensacion fd, farc_tipomovimiento t, farc_inventario fi, medicamentos m, hic_detalle_formulacion hdf, farc_devolucion fdev, farc_bodegas fb "+
   						"WHERE fdev.cod_dispensacionFk=fmd.cod_dispensafk AND fdev.cod_invFk=fi.codigo AND fdev.cod_mov_dispensafk=fmd.codigo AND fmd.cod_formulacionfk=hf.codigo AND hf.CodPac_fk=ap.pac_codigo_paciente AND fd.codigo=fmd.cod_dispensafk AND fdp.cod_invFk=fmd.cod_invfk AND t.codigo=fmd.tipo_mvtofk AND fmd.cod_invFk=fi.codigo AND m.codigo=hdf.CodMedicamento_fk AND hf.codigo=hdf.CodFormulacion_fk AND fi.cod_medFk=m.codigo  AND  fb.codigo=fmd.bodegai and m.codigo='"+med+"' AND fmd.fecha BETWEEN '"+Fechai+"'  AND '"+Fechaf+"' ) AS a ORDER BY a.formula, a.med	");
 
			   
		   }else{
			   if(sql==4){
				   rs=st.executeQuery("SELECT * FROM(SELECT DISTINCT  ap.nombre AS nombrep,ap.primer_apellido AS primerap,ap.segundo_apellido AS segundoap,ap.tipo_documento AS Tipodoc, ap.numero_documento ,  fmd.codigo AS CodMovimient, fmd.cod_formulacionfk AS formula, fmd.fecha,fmd.hora, fmd.concepto, t.descripcion,m.nombre AS med, m.concentracion  AS concetracion , fi.lote, fi.vencimiento,hdf.cantidad AS cantidad_formulada,  fmd.cantidad_dispensada AS cantidad_dispensada, ' ' AS cantidad_devuelta,  fb.nombre AS bodega, ' ' AS fechadev , ' ' AS horadev, fmd.usuario  AS userdisp, ' ' AS userdev "+
							"FROM farc_mov_dispensa fmd, hic_formulacion hf , adm_paciente ap, farc_detallemov fdp, farc_dispensacion fd, farc_tipomovimiento t, farc_inventario fi, medicamentos m, hic_detalle_formulacion hdf , farc_bodegas fb "+
							"WHERE fmd.cod_formulacionfk=hf.codigo AND hf.CodPac_fk=ap.pac_codigo_paciente AND fd.codigo=fmd.cod_dispensafk AND fdp.cod_invFk=fmd.cod_invfk AND t.codigo=fmd.tipo_mvtofk AND fmd.cod_invFk=fi.codigo AND m.codigo=hdf.CodMedicamento_fk AND hf.codigo=hdf.CodFormulacion_fk AND fi.cod_medFk=m.codigo AND  fb.codigo=fmd.bodegai AND fmd.codigo NOT IN (SELECT cod_mov_dispensafk FROM farc_devolucion WHERE fecha BETWEEN  '"+Fechai+"'  AND '"+Fechaf+"')  AND fmd.bodegai='"+bodega+"' and m.codigo='"+med+"' AND fmd.fecha BETWEEN '"+Fechai+"'  AND '"+Fechaf+"' "+
							"UNION "+
							"SELECT DISTINCT  ap.nombre AS nombreap,ap.primer_apellido AS primerap,ap.segundo_apellido AS segundoap , ap.tipo_documento AS Tipodoc, ap.numero_documento, fmd.codigo AS CodMovimient, fmd.cod_formulacionfk AS formula, fmd.fecha,fmd.hora, fmd.concepto, t.descripcion,m.nombre AS med, m.concentracion AS concetracion, fi.lote, fi.vencimiento,hdf.cantidad AS cantidad_formulada, fmd.cantidad_dispensada AS cantidad_dispensada , fdev.cantidad AS cantidad_devuelta,fb.nombre AS bodega, fdev.fecha AS fechadev, fdev.hora AS horadev, fmd.usuario AS userdisp, fdev.usuario AS userdev "+
							"FROM farc_mov_dispensa fmd, hic_formulacion hf , adm_paciente ap, farc_detallemov fdp, farc_dispensacion fd, farc_tipomovimiento t, farc_inventario fi, medicamentos m, hic_detalle_formulacion hdf, farc_devolucion fdev, farc_bodegas fb "+
							"WHERE fdev.cod_dispensacionFk=fmd.cod_dispensafk AND fdev.cod_invFk=fi.codigo AND fdev.cod_mov_dispensafk=fmd.codigo AND fmd.cod_formulacionfk=hf.codigo AND hf.CodPac_fk=ap.pac_codigo_paciente AND fd.codigo=fmd.cod_dispensafk AND fdp.cod_invFk=fmd.cod_invfk AND t.codigo=fmd.tipo_mvtofk AND fmd.cod_invFk=fi.codigo AND m.codigo=hdf.CodMedicamento_fk AND hf.codigo=hdf.CodFormulacion_fk AND fi.cod_medFk=m.codigo  AND  fb.codigo=fmd.bodegai AND fmd.bodegai='"+bodega+"' and m.codigo='"+med+"' AND fmd.fecha BETWEEN '"+Fechai+"'  AND '"+Fechaf+"' ) AS a ORDER BY a.formula, a.med	");
			   }
		   }
	   }
   }
	   
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoExistencias>>BusRepDisPac "+ex);
   }	
   return rs;
}



public java.sql.ResultSet BusCodMedConsMed(String producto, String Fechai, String Fechaf){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 
 System.out.println("Fechai"+Fechai+"Fechaf"+Fechaf+"");
   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery("SELECT med.codigo AS codmed "+
   						"FROM hic_formulacion hf, hic_detalle_formulacion hdf,farc_formafarmaceutica fff,  farc_mov_dispensa fmd, medicamentos med, farc_inventario i, seg_usuario u, seg_datos_personales ud, adm_paciente ap "+
   						"WHERE hf.codigo=hdf.CodFormulacion_fk "+
   						"AND fmd.cod_formulacionfk=hf.codigo "+
   						"AND med.codigo=hdf.CodMedicamento_fk "+
   						"AND fff.codigo=med.cod_formaFK "+
   						"AND i.codigo=fmd.cod_invfk "+
   						"AND u.usu_codigo=hf.CodUsu_fk "+
   						"AND ap.pac_codigo_paciente=hf.CodPac_fk AND  med.cod_grupoFK='"+producto+"' AND u.dat_codigo_fk=ud.dat_codigo "+
   						"AND fmd.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"'  GROUP BY med.codigo ORDER BY med.nombre");
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoCrearProveedor>>BusCodMedConsMed "+ex);
   }	
   return rs;
}


public java.sql.ResultSet BusCodMedConsumoMedCont(String Fechai, String Fechaf){	
	
	
   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery("SELECT med.codigo AS codmed "+
   						"FROM hic_formulacion hf, hic_detalle_formulacion hdf,farc_formafarmaceutica fff,  farc_mov_dispensa fmd, medicamentos med, farc_inventario i, seg_usuario u, seg_datos_personales ud, adm_paciente ap "+
   						"WHERE hf.codigo=hdf.CodFormulacion_fk "+
   						"AND fmd.cod_formulacionfk=hf.codigo "+
   						"AND med.codigo=hdf.CodMedicamento_fk "+
   						"AND fff.codigo=med.cod_formaFK "+
   						"and fmd.cod_invfk=i.codigo "+
   						"and i.cod_medFk=med.codigo "+
   						"AND i.codigo=fmd.cod_invfk "+
   						"AND u.usu_codigo=hf.CodUsu_fk "+
   						"AND ap.pac_codigo_paciente=hf.CodPac_fk "+
   						"AND u.dat_codigo_fk=ud.dat_codigo "+
   						"and med.control='Si' AND fmd.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"'  GROUP BY med.codigo ORDER BY med.nombre");
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoCrearProveedor>>BusCodMedConsumoMedCont"+ex);
   }	
   return rs;
}




public java.sql.ResultSet BuscarPabellones(){	
	
	
   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery("SELECT aas.codigo, aas.nombre FROM adm_area aa, adm_subarea aas WHERE aa.codigo=aas.codigoarea");
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoCrearProveedor>>BuscarPabellones"+ex);
   }	
   return rs;
}
public java.sql.ResultSet ObtenerPabellones(String tunidad){	
	
	
	   java.sql.ResultSet rs=null;
	   Statement st = null;
	   try{
	   	Conexion con=new Conexion();
	   	st = con.conn.createStatement();
	   	rs=st.executeQuery("SELECT aas.codigo, aas.nombre FROM adm_area aa, adm_subarea aas WHERE aa.codigo=aas.codigoarea and aas.codigo='"+tunidad+"'");
	   System.out.println("SELECT aas.codigo, aas.nombre FROM adm_area aa, adm_subarea aas WHERE aa.codigo=aas.codigoarea and aas.codigoarea='"+tunidad+"'");
	   }
	   catch(Exception ex){
	   	System.out.println("Error en MetodoCrearProveedor>>ObtenerPabellones"+ex);
	   }	
	   return rs;
	}

public java.sql.ResultSet BuscarOrdenes(String Fechai , String Fechaf, String tunidad){	
	
	
	   java.sql.ResultSet rs=null;
	   Statement st = null;
	   try{
	   	Conexion con=new Conexion();
	   	st = con.conn.createStatement();
	   	if (tunidad.equals("todas")){
	   	rs=st.executeQuery("SELECT DISTINCT hf.codigo,CONCAT(ap.tipo_documento,' ',ap.numero_documento) AS documento,CONCAT(ap.nombre,' ',ap.primer_apellido,' ',ap.segundo_apellido) AS paciente, hf.estado, aa.nombre, aas.nombre, CONCAT(sdp.nombre, sdp.apellido) AS NombreMed, sdp.profesion, sdp.cedula AS rg,concat(hf.fecha,' ',hf.hora) as fecha "+
	   						"FROM hic_formulacion hf, farc_mov_dispensa fmd, adm_paciente ap, adm_area aa, adm_subarea aas, seg_usuario su, seg_datos_personales sdp "+
	   						"WHERE hf.codigo=fmd.cod_formulacionfk "+
	   						"AND ap.pac_codigo_paciente=hf.CodPac_fk "+
	   						"AND aa.codigo=hf.CodArea_fk "+
	   						"AND aa.codigo=aas.codigoarea "+
	   						"AND aas.codigo=hf.CodSubarea_fk "+
	   						"AND hf.CodUsu_fk=su.usu_codigo "+
	   						"AND su.dat_codigo_fk=sdp.dat_codigo "+
	   						"AND hf.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"'");
	   	}else{
	   		rs=st.executeQuery("SELECT DISTINCT hf.codigo,CONCAT(ap.tipo_documento,' ',ap.numero_documento) AS documento,CONCAT(ap.nombre,' ',ap.primer_apellido,' ',ap.segundo_apellido) AS paciente, hf.estado, aa.nombre, aas.nombre, CONCAT(sdp.nombre, sdp.apellido) AS NombreMed, sdp.profesion, sdp.cedula AS rg,concat(hf.fecha,' ',hf.hora) as fecha "+
						"FROM hic_formulacion hf, farc_mov_dispensa fmd, adm_paciente ap, adm_area aa, adm_subarea aas, seg_usuario su, seg_datos_personales sdp "+
						"WHERE hf.codigo=fmd.cod_formulacionfk "+
						"AND ap.pac_codigo_paciente=hf.CodPac_fk "+
						"AND aa.codigo=hf.CodArea_fk "+
						"AND aa.codigo=aas.codigoarea "+
						"AND aas.codigo=hf.CodSubarea_fk "+
						"AND hf.CodUsu_fk=su.usu_codigo "+
						"AND su.dat_codigo_fk=sdp.dat_codigo and hf.CodSubarea_fk='"+tunidad+"' "+
						"AND hf.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"'");
	   	}
	   }
	   catch(Exception ex){
	   	System.out.println("Error en MetodoCrearProveedor>>BuscarOrdenes"+ex);
	   }	
	   return rs;
	}


public java.sql.ResultSet BusCodMedConsumoMedContB(String bodega, String Fechai, String Fechaf){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 
	//System.out.println("codmed"+codmed+"Fechai"+Fechai+"Fechaf"+Fechaf+"");
   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery("SELECT med.codigo AS codmed,e.*, fb.nombre as nombrebod "+
   						"FROM hic_formulacion hf, hic_detalle_formulacion hdf,farc_formafarmaceutica fff,  farc_mov_dispensa fmd, medicamentos med, farc_inventario i, seg_usuario u, seg_datos_personales ud, adm_paciente ap, empresa e,farc_bodegas fb "+
   						"WHERE hf.codigo=hdf.CodFormulacion_fk "+
   						"AND fmd.cod_formulacionfk=hf.codigo "+
   						"AND med.codigo=hdf.CodMedicamento_fk "+
   						"AND fff.codigo=med.cod_formaFK "+
   						"AND i.codigo=fmd.cod_invfk "+
   						"and i.cod_medFk=med.codigo "+
   						"AND u.usu_codigo=hf.CodUsu_fk "+
   						"AND ap.pac_codigo_paciente=hf.CodPac_fk "+ 
   						"AND u.dat_codigo_fk=ud.dat_codigo "+
   						"AND med.control='Si' "+
   						"AND fmd.bodegai='"+bodega+"' and fmd.bodegai=fb.codigo "+
   						"AND fmd.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"'  GROUP BY med.codigo ORDER BY med.nombre");
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoCrearProveedor>>BusCodMedConsumoMedContB"+ex);
   }	
   return rs;
}

public java.sql.ResultSet BusDetMedConsumoMedCont(String codmed,String Fechai, String Fechaf){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 
	System.out.println("codmed"+codmed+"Fechai"+Fechai+"Fechaf"+Fechaf+"");
   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery("SELECT CONCAT(med.nombre,' ',med.concentracion,' ',fff.forma_farmaceutica) AS MED, hdf.cantidad AS formulada ,SUM(fmd.cantidad_dispensada) AS disp, i.lote , CONCAT(ud.nombre,' ',ud.apellido) AS USUARIO, CONCAT(ap.nombre,' ',ap.primer_apellido,' ',ap.segundo_apellido) AS PACIENTE, CONCAT(ap.tipo_documento,' ',ap.numero_documento) AS DOCUMENTO, ud.cedula, hf.fecha AS fechaform, fmd.fecha AS fechadisp, ud.profesion, IF(((ud.profesion LIKE '%Medico General%')OR (ud.profesion LIKE '%Especialista%')),ud.cedula,' ') AS rg "+
   						"FROM hic_formulacion hf, hic_detalle_formulacion hdf,farc_formafarmaceutica fff,  farc_mov_dispensa fmd, medicamentos med, farc_inventario i, seg_usuario u, seg_datos_personales ud, adm_paciente ap "+
   						"WHERE hf.codigo=hdf.CodFormulacion_fk "+
   						"AND fmd.cod_formulacionfk=hf.codigo "+
   						"AND med.codigo=hdf.CodMedicamento_fk "+
   						"AND fff.codigo=med.cod_formaFK "+
   						"AND i.codigo=fmd.cod_invfk  "+
   						"and fmd.cod_invfk=i.codigo "+
   						"and i.cod_medFk=med.codigo "+
   						"AND u.usu_codigo=hf.CodUsu_fk "+
   						"AND ap.pac_codigo_paciente=hf.CodPac_fk "+
   						"AND u.dat_codigo_fk=ud.dat_codigo "+
   						"AND med.codigo='"+codmed+"' and med.control='Si' "+
   						"AND fmd.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"' GROUP BY hdf.codigo ORDER BY med.nombre");
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoCrearProveedor>>BusDetMedConsumoMedCont"+ex);
   }	
   return rs;
}


public java.sql.ResultSet BusDetMedConsumoMedContB(String codmed,String bodega,String Fechai, String Fechaf){	
	
	 
	System.out.println("codmed"+codmed+"Fechai"+Fechai+"Fechaf"+Fechaf+"");
   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery("SELECT CONCAT(med.nombre,' ',med.concentracion,' ',fff.forma_farmaceutica) AS MED, hdf.cantidad AS formulada ,SUM(fmd.cantidad_dispensada) AS disp, i.lote , CONCAT(ud.nombre,' ',ud.apellido) AS USUARIO, CONCAT(ap.nombre,' ',ap.primer_apellido,' ',ap.segundo_apellido) AS PACIENTE, CONCAT(ap.tipo_documento,' ',ap.numero_documento) AS documento, ud.cedula, hf.fecha AS fechaform, fmd.fecha AS fechadisp, ud.profesion, IF(((ud.profesion LIKE '%Medico General%')OR (ud.profesion LIKE '%Especialista%')),ud.cedula,' ') AS rg , hf.hora AS horaform, fmd.hora horadisp "+
   						"FROM hic_formulacion hf, hic_detalle_formulacion hdf,farc_formafarmaceutica fff,  farc_mov_dispensa fmd, medicamentos med, farc_inventario i, seg_usuario u, seg_datos_personales ud, adm_paciente ap, empresa e, farc_bodegas fb "+
   						"WHERE hf.codigo=hdf.CodFormulacion_fk "+
   						"AND fmd.cod_formulacionfk=hf.codigo "+
   						"AND med.codigo=hdf.CodMedicamento_fk "+
   						"AND fff.codigo=med.cod_formaFK "+
   						"AND i.codigo=fmd.cod_invfk "+
   						"and i.cod_medFk=med.codigo "+
   						"AND u.usu_codigo=hf.CodUsu_fk "+
   						"AND ap.pac_codigo_paciente=hf.CodPac_fk "+
   						"AND u.dat_codigo_fk=ud.dat_codigo "+ 
   						"AND med.codigo='"+codmed+"' "+
   						"and med.control='Si' "+
   						"and fmd.bodegai=fb.codigo "+
   						"and fmd.bodegai='"+bodega+"' AND fmd.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"'  GROUP BY hdf.codigo ORDER BY med.nombre");
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoCrearProveedor>>BusDetMedConsumoMedContB"+ex);
   }	
   return rs;
}



public java.sql.ResultSet BusDetMedConsumo(String codmed,String Fechai, String Fechaf){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 
	System.out.println("codmed"+codmed+"Fechai"+Fechai+"Fechaf"+Fechaf+"");
   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery("SELECT CONCAT(med.nombre,' ', med.concentracion,' ', fff.forma_farmaceutica) AS medicamento, hdf.cantidad AS formulada ,SUM(fmd.cantidad_dispensada) AS disp, i.lote , CONCAT(ud.nombre,' ',ud.apellido) AS usuario, CONCAT(ap.nombre,' ',ap.primer_apellido,' ',ap.segundo_apellido) AS paciente, CONCAT(ap.tipo_documento,' ',ap.numero_documento) as documento, ud.cedula, hf.fecha AS fechaform, fmd.fecha AS fechadisp, ud.profesion, IF(((ud.profesion LIKE '%Medico General%')OR (ud.profesion LIKE '%Especialista%')),ud.cedula,' ') AS rg , hf.hora AS horaform, fmd.hora AS horadisp "+
   						"FROM hic_formulacion hf, hic_detalle_formulacion hdf,farc_formafarmaceutica fff,  farc_mov_dispensa fmd, medicamentos med, farc_inventario i, seg_usuario u, seg_datos_personales ud, adm_paciente ap "+
   						"WHERE hf.codigo=hdf.CodFormulacion_fk "+
   						"AND fmd.cod_formulacionfk=hf.codigo  "+
   						"and fmd.cod_invfk=i.codigo "+
   						"and i.cod_medFk=med.codigo "+
   						"AND med.codigo=hdf.CodMedicamento_fk "+
   						"AND fff.codigo=med.cod_formaFK "+
   						"AND i.codigo=fmd.cod_invfk "+
   						"AND u.usu_codigo=hf.CodUsu_fk "+
   						"AND ap.pac_codigo_paciente=hf.CodPac_fk "+
   						"AND u.dat_codigo_fk=ud.dat_codigo "+
   						"AND med.codigo="+codmed+" AND fmd.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"'  GROUP BY hdf.codigo, med.codigo ORDER BY med.nombre");
   
   
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoCrearProveedor>>BusDetMedConsumo "+ex);
   }	
   return rs;
}




public java.sql.ResultSet BusDetMedConsumoP(String cm,String Fechai, String Fechaf){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 
	System.out.println("codmed"+cm+"Fechai"+Fechai+"Fechaf"+Fechaf+"");
   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery("SELECT CONCAT(med.nombre,' ', med.concentracion,' ', fff.forma_farmaceutica) AS medicamento, hdf.cantidad AS formulada ,SUM(fmd.cantidad_dispensada) AS disp, i.lote , CONCAT(ud.nombre,' ',ud.apellido) AS usuario, CONCAT(ap.nombre,' ',ap.primer_apellido,' ',ap.segundo_apellido) AS paciente, CONCAT(ap.tipo_documento,' ',ap.numero_documento) as documento, ud.cedula, hf.fecha AS fechaform, fmd.fecha AS fechadisp, ud.profesion, IF(((ud.profesion LIKE '%Medico General%')OR (ud.profesion LIKE '%Especialista%')),ud.cedula,' ') AS rg, hf.codigo , hf.hora  AS horaformulacion, fmd.hora AS horadispensacion  "+
   						"FROM hic_formulacion hf, hic_detalle_formulacion hdf,farc_formafarmaceutica fff,  farc_mov_dispensa fmd, medicamentos med, farc_inventario i, seg_usuario u, seg_datos_personales ud, adm_paciente ap "+
   						"WHERE hf.codigo=hdf.CodFormulacion_fk "+
   						"AND fmd.cod_formulacionfk=hf.codigo  "+
   						"and fmd.cod_invfk=i.codigo "+
   						"and i.cod_medFk=med.codigo "+
   						"AND med.codigo=hdf.CodMedicamento_fk "+
   						"AND fff.codigo=med.cod_formaFK "+
   						"AND i.codigo=fmd.cod_invfk "+
   						"AND u.usu_codigo=hf.CodUsu_fk "+
   						"AND ap.pac_codigo_paciente=hf.CodPac_fk "+
   						"AND u.dat_codigo_fk=ud.dat_codigo "+
   						"AND med.codigo="+cm+" AND fmd.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"'  GROUP BY hdf.codigo ORDER BY med.nombre");
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoCrearProveedor>>BusDetMedConsumoP "+ex);
   }	
   return rs;
}





public java.sql.ResultSet BusDetMedConsumoPBod(String cm,String bodega, String Fechai, String Fechaf){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 
	System.out.println("codmed"+cm+"Fechai"+Fechai+"Fechaf"+Fechaf+"");
   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery("SELECT CONCAT(med.nombre,' ', med.concentracion,' ', fff.forma_farmaceutica) AS medicamento, hdf.cantidad AS formulada ,SUM(fmd.cantidad_dispensada) AS disp, i.lote , CONCAT(ud.nombre,' ',ud.apellido) AS usuario, CONCAT(ap.nombre,' ',ap.primer_apellido,' ',ap.segundo_apellido) AS paciente, CONCAT(ap.tipo_documento,' ',ap.numero_documento) as documento, ud.cedula, hf.fecha AS fechaform, fmd.fecha AS fechadisp, ud.profesion, IF(((ud.profesion LIKE '%Medico General%')OR (ud.profesion LIKE '%Especialista%')),ud.cedula,' ') AS rg, hf.codigo "+
   						"FROM hic_formulacion hf, hic_detalle_formulacion hdf,farc_formafarmaceutica fff,  farc_mov_dispensa fmd, medicamentos med, farc_inventario i, seg_usuario u, seg_datos_personales ud, adm_paciente ap, farc_bodegas fb "+
   						"WHERE hf.codigo=hdf.CodFormulacion_fk "+
   						"AND fmd.cod_formulacionfk=hf.codigo  "+
   						"and fmd.cod_invfk=i.codigo "+
   						"and i.cod_medFk=med.codigo "+
   						"AND med.codigo=hdf.CodMedicamento_fk "+
   						"AND fff.codigo=med.cod_formaFK "+
   						"AND i.codigo=fmd.cod_invfk "+
   						"AND u.usu_codigo=hf.CodUsu_fk "+
   						"AND ap.pac_codigo_paciente=hf.CodPac_fk "+
   						"AND u.dat_codigo_fk=ud.dat_codigo "+
   						"and fb.codigo=fmd.bodegai "+
   		   				"and fmd.bodegai='"+bodega+"' AND med.codigo="+cm+"  AND fmd.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"'  GROUP BY hdf.codigo ORDER BY med.nombre");
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoCrearProveedor>>BusDetMedConsumoPBod "+ex);
   }	
   return rs;
}

public java.sql.ResultSet BusDetMedConsumoBod(String codmed,String Fechai, String Fechaf, String bodega){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 
	System.out.println("codmed"+codmed+"Fechai"+Fechai+"Fechaf"+Fechaf+"");
   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery("SELECT CONCAT(med.nombre,' ', med.concentracion,' ', fff.forma_farmaceutica) AS medicamento, hdf.cantidad AS formulada ,SUM(fmd.cantidad_dispensada) AS disp, i.lote , CONCAT(ud.nombre,' ',ud.apellido) AS usuario, CONCAT(ap.nombre,' ',ap.primer_apellido,' ',ap.segundo_apellido) AS paciente, CONCAT(ap.tipo_documento,' ',ap.numero_documento) as documento, ud.cedula, hf.fecha AS fechaform, fmd.fecha AS fechadisp, ud.profesion, IF(((ud.profesion LIKE '%Medico General%')OR (ud.profesion LIKE '%Especialista%')),ud.cedula,' ') AS rg "+
   			"FROM hic_formulacion hf, hic_detalle_formulacion hdf,farc_formafarmaceutica fff,  farc_mov_dispensa fmd, medicamentos med, farc_inventario i, seg_usuario u, seg_datos_personales ud, adm_paciente ap, farc_bodegas fb "+
				"WHERE hf.codigo=hdf.CodFormulacion_fk "+
				"AND fmd.cod_formulacionfk=hf.codigo "+
				"and fmd.cod_invfk=i.codigo "+
				"and i.cod_medFk=med.codigo "+
				"AND med.codigo=hdf.CodMedicamento_fk "+
				"AND fff.codigo=med.cod_formaFK "+
				"AND i.codigo=fmd.cod_invfk  "+
				"AND u.usu_codigo=hf.CodUsu_fk "+
				"AND ap.pac_codigo_paciente=hf.CodPac_fk "+
				"AND u.dat_codigo_fk=ud.dat_codigo "+
				"and fb.codigo=fmd.bodegai "+
   				"and fmd.bodegai='"+bodega+"' AND med.codigo='"+codmed+"' AND fmd.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"' GROUP BY hdf.codigo, med.codigo ORDER BY med.nombre");
  

   }
   catch(Exception ex){
   	System.out.println("Error en MetodoCrearProveedor>>BusDetMedConsumoBod "+ex);
   }	
   return rs;
}


public java.sql.ResultSet BusDetMedConsumoBodMed(String codmed,String Fechai, String Fechaf, String bodega, String producto){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 
	System.out.println("codmed"+codmed+"Fechai"+Fechai+"Fechaf"+Fechaf+"");
   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery("SELECT concat(med.nombre,' ',med.concentracion,' ',fff.forma_farmaceutica) as med, hdf.cantidad AS formulada ,SUM(fmd.cantidad_dispensada) AS disp, i.lote , CONCAT(ud.nombre,' ',ud.apellido) AS usuario, CONCAT(ap.nombre,' ',ap.primer_apellido,' ',ap.segundo_apellido) AS paciente, CONCAT(ap.tipo_documento,'-',ap.numero_documento) AS documento, ud.cedula, hf.fecha AS fechaform, fmd.fecha AS fechadisp, ud.profesion, IF(((ud.profesion LIKE '%Medico General%')OR (ud.profesion LIKE '%Especialista%')),ud.cedula,' ') AS rg "+
   					"FROM hic_formulacion hf, hic_detalle_formulacion hdf,farc_formafarmaceutica fff,  farc_mov_dispensa fmd, medicamentos med, farc_inventario i, seg_usuario u, seg_datos_personales ud, adm_paciente ap, empresa e, farc_bodegas fb "+
   					"WHERE hf.codigo=hdf.CodFormulacion_fk "+
   					"AND fmd.cod_formulacionfk=hf.codigo "+
   					"AND med.codigo=hdf.CodMedicamento_fk "+
   					"AND fff.codigo=med.cod_formaFK "+
   					"AND i.codigo=fmd.cod_invfk "+
   					"AND u.usu_codigo=hf.CodUsu_fk "+
   					"AND ap.pac_codigo_paciente=hf.CodPac_fk AND  med.cod_grupoFK='"+producto+"' AND u.dat_codigo_fk=ud.dat_codigo "+
   					"AND fb.codigo=fmd.bodegai "+
   					"AND fmd.bodegai='"+bodega+"' AND med.codigo='"+codmed+"' AND fmd.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"'  GROUP BY hdf.codigo,med.codigo ORDER BY med.nombre");
  

   }
   catch(Exception ex){
   	System.out.println("Error en MetodoCrearProveedor>>BusDetMedConsumoBodMed "+ex);
   }	
   return rs;
}



public java.sql.ResultSet BusCodMedConsumoBod(String bodega,String Fechai, String Fechaf){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 
 System.out.println("bodega"+bodega+"Fechai"+Fechai+"Fechaf"+Fechaf+"");
   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery("SELECT med.codigo AS codmed "+
   						"FROM hic_formulacion hf, hic_detalle_formulacion hdf,farc_formafarmaceutica fff,  farc_mov_dispensa fmd, medicamentos med, farc_inventario i, seg_usuario u, seg_datos_personales ud, adm_paciente ap, farc_bodegas fb "+
   						"WHERE hf.codigo=hdf.CodFormulacion_fk "+
   						"AND fmd.cod_formulacionfk=hf.codigo "+
   						"and fmd.cod_invfk=i.codigo "+
   						"and i.cod_medFk=med.codigo "+
   						"AND med.codigo=hdf.CodMedicamento_fk "+
   						"AND fff.codigo=med.cod_formaFK "+
   						"AND i.codigo=fmd.cod_invfk  "+
   						"AND u.usu_codigo=hf.CodUsu_fk "+
   						"AND ap.pac_codigo_paciente=hf.CodPac_fk "+
   						"AND u.dat_codigo_fk=ud.dat_codigo "+
   						"and fb.codigo=fmd.bodegai "+
   						"and fmd.bodegai="+bodega+" AND fmd.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"' GROUP BY med.codigo ORDER BY med.nombre");
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoCrearProveedor>>BusCodMedConsumoBod "+ex);
   }	
   return rs;
}

public java.sql.ResultSet BusCodMedConsumoBodMed(String bodega,String producto,String Fechai, String Fechaf){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 
 System.out.println("bodega"+bodega+"Fechai"+Fechai+"Fechaf"+Fechaf+"");
   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery("SELECT med.codigo AS codmed,e.*, fmd.bodegai,fb.nombre as nombod "+
   						"FROM hic_formulacion hf, hic_detalle_formulacion hdf,farc_formafarmaceutica fff,  farc_mov_dispensa fmd, medicamentos med, farc_inventario i, seg_usuario u, seg_datos_personales ud, adm_paciente ap, empresa e, farc_bodegas fb "+
   						"WHERE hf.codigo=hdf.CodFormulacion_fk "+
   						"AND fmd.cod_formulacionfk=hf.codigo "+
   						"AND med.codigo=hdf.CodMedicamento_fk "+
   						"AND fff.codigo=med.cod_formaFK "+
   						"AND i.codigo=fmd.cod_invfk "+
   						"AND u.usu_codigo=hf.CodUsu_fk "+
   						"AND ap.pac_codigo_paciente=hf.CodPac_fk AND  med.cod_grupoFK='"+producto+"' AND u.dat_codigo_fk=ud.dat_codigo "+
   						"and fb.codigo=fmd.bodegai "+
   						"and fmd.bodegai='"+bodega+"' AND fmd.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"'  GROUP BY med.codigo ORDER BY med.nombre");
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoCrearProveedor>>BusCodMedConsumoBodMed "+ex);
   }	
   return rs;
}





public java.sql.ResultSet ObtenerArticulo(){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 

   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery("select codigo, nombre, concentracion from medicamentos");
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoCrearProveedor>>ObtenerMovimiento "+ex);
   }	
   return rs;
}



public java.sql.ResultSet ObtenerCodTraslado(){	
	/**
	 * consulta que obtiene las unidades del modulo de farmacia, 
	 */
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery("select codigo from farc_tipomovimiento where cod_movFk = '3' ");
    }
    catch(Exception ex){
    	System.out.println("Error en MetodoEntradas>>ObtenerCodTraslado "+ex);
    }	
    return rs;
}

////////////////////////////////


public void CrearMovimientos(String bodega, String cantidad, String movimiento, String fecha,  String hra, String user, String factura, String concepto){
	
	// creamos los ingresos de movimientos
	
	Entradas ci = new Entradas();
	
	ci.setbodega(bodega);
	ci.setcantidad(cantidad);
	ci.setmovimiento(movimiento);
	ci.setfecha(fecha);
	ci.setfactura(factura);
	ci.setconcepto(concepto);
	
	
	try{
			PreparedStatement ps = null;
		    Conexion con=new Conexion();
		    ps=con.conn.prepareStatement("insert into farc_movimientos(cod_bodegai,cantidad,tipo_mvtoFk,fecha,hora,cod_usuariofK,nsoporte,concepto)values(?,?,?,?,?,?,?,?)");
		    ps.setString(1,ci.getbodega());
		    ps.setString(2,ci.getcantidad());
		    ps.setString(3,ci.getmovimiento());
		    ps.setString(4,ci.getfecha());
		    ps.setString(5,hra);//hora
		    ps.setString(6,user);//cod_usuario
		    ps.setString(7,ci.getfactura());
		    ps.setString(8,ci.getconcepto());
		    ps.executeUpdate();
			ps.close();
			con.cerrar();				
		}catch(Exception ex){
        	System.out.println("ERROR EN MetodoEntradas>>CrearEntradas "+ex);
		}

	}

//////////////////////////

public void CrearDetalle(String movi,String inv){
	
	// creamos los ingresos de movimientos
	
	try{
			PreparedStatement ps = null;
		    Conexion con=new Conexion();
		    ps=con.conn.prepareStatement("insert into farc_detallemov(cod_movFk,cod_invFk)values(?,?)");
		    ps.setString(1,movi);
		    ps.setString(2,inv);
		    ps.executeUpdate();
			ps.close();
			con.cerrar();				
		}catch(Exception ex){
        	System.out.println("ERROR EN MetodoSalidas>>CrearDetalle "+ex);
		}

	}

////////////////////////


public java.sql.ResultSet ObtenerIva(String xx){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 

   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery("select cod_ivaFk from farc_inventario where codigo = '"+xx+"' ");
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoCrearProveedor>>ObtenerMovimiento "+ex);
   }	
   return rs;                
}
///////////////////////

public java.sql.ResultSet ObtenerVIva(String xx){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 

   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery("select valor from farc_iva where codigo = '"+xx+"' ");
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoCrearProveedor>>ObtenerMovimiento "+ex);
   }	
   return rs;
}
///////////////////////

public java.sql.ResultSet ObtenerPro(String xx){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 

   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery("select cod_proveedorFk from farc_inventario where codigo = '"+xx+"' ");
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoCrearProveedor>>ObtenerMovimiento "+ex);
   }	
   return rs;
}


/////////////////////////

public java.sql.ResultSet ObtenerUMovimiento(String fec, String hra){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 

   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery("select codigo from farc_movimientos where fecha='"+fec+"' and hora='"+hra+"' ");
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoCrearProveedor>>ObtenerMovimiento "+ex);
   }	
   return rs;
}

/////////////////////////////
public void CrearSalidas(String inv,String cant){
	
	// creamos los ingresos de INVENTARIO
	
	PreparedStatement st = null;
    try{
    	Conexion con=new Conexion();
    	st= con.conn.prepareStatement("update farc_inventario set cantidad='"+cant+"' where codigo='"+inv+"'");
    	st.executeUpdate();
    	st.close();
    	con.cerrar();
    	
    }
    catch(Exception ex){
    	System.out.println("ERROR EN MetodoSalidas>>CrearSalidas "+ex);
    
    }	
	
}

/////////////////////////////

public void CrearEntradasT(String movimiento,String articulo,String vence, String lote, String cantidad, String vunitario, String vtotal, String iva, String bodega, String fecha, String hra, String proveedor,String invima){
	
	// creamos los ingresos de INVENTARIO
	
	Entradas ci = new Entradas();
	
	ci.setarticulo(articulo);
	ci.setvence(vence);
	ci.setlote(lote);
	ci.setcantidad(cantidad);
	ci.setvunitario(vunitario);
	ci.setvtotal(vtotal);
	ci.setiva(iva);
	ci.setbodega(bodega);
	ci.setfecha(fecha);
	ci.setproveedor(proveedor);
	ci.setinvima(invima);
	
	try{
			PreparedStatement ps = null;
		    Conexion con=new Conexion();
		    ps=con.conn.prepareStatement("insert into farc_inventario(cod_movFk,cod_medFk,vencimiento,lote,cantidad,vunitario,vtotal,cod_ivaFk,cod_bodegaFk,fecha,hora,cod_proveedorFk,invima)values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
		    ps.setString(1,movimiento);
		    ps.setString(2,ci.getarticulo());
		    ps.setString(3,ci.getvence());
		    ps.setString(4,ci.getlote());
		    ps.setString(5,ci.getcantidad());
		    ps.setString(6,ci.getvunitario());
		    ps.setString(7,ci.getvtotal());
		    ps.setString(8,ci.getiva());
		    ps.setString(9,ci.getbodega());
		    ps.setString(10,ci.getfecha());
		    ps.setString(11,hra);//hora
		    ps.setString(12,ci.getproveedor());
		    ps.setString(13,ci.getinvima());
		    ps.executeUpdate();
			ps.close();
			con.cerrar();				
		}catch(Exception ex){
        	System.out.println("ERROR EN MetodoEntradas>>CrearEntradas "+ex);
		}
	}

///////////////////


///////////////////////////



}



/*

public class MetodoCrearIvaGrupoUnidad {

	public void CrearIva(String nombreIva,String valor,String descripcion){
		
		 // creamos los tipos de iva
		
		CrearIvaGrupoUnidad ci = new CrearIvaGrupoUnidad();
		
		ci.setnombreIva(nombreIva);
		ci.setvalor(valor);
		ci.setdescripcion(descripcion);
		
		
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("insert into farc_iva(descripcion,valor,observacion)values(?,?,?)");
			    ps.setString(1,ci.getnombreIva());
			    ps.setString(2,ci.getvalor());
			    ps.setString(3,ci.getdescripcion());
			    ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoCrearIvaGrupoUnidad>>CrearIva "+ex);
			}

		}
	
	public void CrearGrupo(String nombreGrupo,String observacion){
		
		// creamos los grupos
		
		CrearIvaGrupoUnidad ci = new CrearIvaGrupoUnidad();
		
		ci.setnombreGrupo(nombreGrupo);
		ci.setobservacion(observacion);
		
		
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("insert into farc_grupo(descripcion,observacion)values(?,?)");
			    ps.setString(1,ci.getnombreGrupo());
			    ps.setString(2,ci.getobservacion());
			    ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoCrearIvaGrupoUnidad>>CrearGrupo "+ex);
			}

		}
	
	
	public void CrearUnidad(String nombreUnidad,String sigla){
	
		// creamos los grupos
		
		CrearIvaGrupoUnidad ci = new CrearIvaGrupoUnidad();
		
		ci.setnombreUnidad(nombreUnidad);
		ci.setsigla(sigla);
		
		
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("insert into farc_unidades(descripcion,sigla)values(?,?)");
			    ps.setString(1,ci.getnombreUnidad());
			    ps.setString(2,ci.getsigla());
			    ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoCrearIvaGrupoUnidad>>CrearUnidad "+ex);
			}

		}
	
	public void CrearForma(String nombreForma,String siglaF){
		
		// creamos los grupos
		
		CrearIvaGrupoUnidad ci = new CrearIvaGrupoUnidad();
		
		ci.setnombreForma(nombreForma);
		ci.setsiglaF(siglaF);
		
		
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("insert into farc_formafarmaceutica(descripcion,forma_farmaceutica)values(?,?)");
			    ps.setString(1,ci.getnombreForma());
			    ps.setString(2,ci.getsiglaF());
			    ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoCrearIvaGrupoUnidad>>CrearFormaFarmaceutica "+ex);
			}

		}
	





	
public java.sql.ResultSet ObtenerIva(){	
		
		// consulta que obtiene los diferentes tipos de ivas., 
		
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo,descripcion, valor, observacion from farc_iva");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearIvaGrupoUnidad>>ObtenerIva "+ex);
        }	
        return rs;
    }


public java.sql.ResultSet ObtenerGrupo(){	
	
	// consulta que obtiene los diferentes Grupos., 
	
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery("select codigo,descripcion,observacion from farc_grupo");
    }
    catch(Exception ex){
    	System.out.println("Error en MetodoCrearIvaGrupoUnidad>>ObtenerGrupo "+ex);
    }	
    return rs;
}

public java.sql.ResultSet ObtenerUnidad(){	
	
	// consulta que obtiene los diferentes Unidades., 
	
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery("select codigo,descripcion,sigla from farc_unidades");
    }
    catch(Exception ex){
    	System.out.println("Error en MetodoCrearIvaGrupoUnidad>>ObtenerUnidad "+ex);
    }	
    return rs;
}

public java.sql.ResultSet ObtenerForma(){	
	
	// consulta que obtiene los diferentes Formas Farmaceuticas., 
	
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery("select codigo,descripcion,forma_farmaceutica from farc_formafarmaceutica");
    }
    catch(Exception ex){
    	System.out.println("Error en MetodoCrearIvaGrupoUnidad>>ObtenerUnidad "+ex);
    }	
    return rs;
}

	
public java.sql.ResultSet ObtenerCodigoIva(String nombreIva){	
	
	 // consulta que obtiene las bodegas, 
	 
   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery("select codigo from farc_iva where descripcion='"+nombreIva+"'");
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoCrearIvaGrupoUnidad>>ObtenerCodigoIva "+ex);
   }	
   return rs;
}

public java.sql.ResultSet ObtenerCodigoGrupo(String nombreGrupo){	
	
	 // consulta que obtiene las bodegas, 
	 
  java.sql.ResultSet rs=null;
  Statement st = null;
  try{
  	Conexion con=new Conexion();
  	st = con.conn.createStatement();
  	rs=st.executeQuery("select codigo from farc_grupo where descripcion='"+nombreGrupo+"'");
  }
  catch(Exception ex){
  	System.out.println("Error en MetodoCrearIvaGrupoUnidad>>ObtenerCodigoGrupo "+ex);
  }	
  return rs;
}


public java.sql.ResultSet ObtenerCodigoUnidad(String nombreUnidad){	
	
	 // consulta que obtiene las bodegas, 
	 
 java.sql.ResultSet rs=null;
 Statement st = null;
 try{
 	Conexion con=new Conexion();
 	st = con.conn.createStatement();
 	rs=st.executeQuery("select codigo from farc_unidades where descripcion='"+nombreUnidad+"'");
 }
 catch(Exception ex){
 	System.out.println("Error en MetodoCrearIvaGrupoUnidad>>ObtenerCodigoUnidad "+ex);
 }	
 return rs;
}

public java.sql.ResultSet ObtenerCodigoForma(String nombreForma){	
	
	 // consulta que obtiene las bodegas, 
	 
java.sql.ResultSet rs=null;
Statement st = null;
try{
	Conexion con=new Conexion();
	st = con.conn.createStatement();
	rs=st.executeQuery("select codigo from farc_formafarmaceutica where descripcion='"+nombreForma+"'");
}
catch(Exception ex){
	System.out.println("Error en MetodoCrearIvaGrupoUnidad>>ObtenerCodigoForma "+ex);
}	
return rs;
}

}
*/
