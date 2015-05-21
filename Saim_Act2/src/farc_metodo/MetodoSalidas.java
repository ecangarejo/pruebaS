package farc_metodo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import adm_logica.Conexion;
import farc_bean.Entradas;


public class MetodoSalidas {

	public java.sql.ResultSet ObtenerBodegas(String user){	
		/**
		 * consulta que obtiene las unidades del modulo de farmacia, 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT b.codigo, b.nombre FROM seg_bodegas_autorizadas s, farc_bodegas b WHERE s.cod_usuariofk='"+user+"' AND s.cod_bodegafk=b.codigo");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoSalidass>>ObtenerBodegas "+ex);
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
	        	r=st.executeQuery("select m.codigo,m.nombre,m.concentracion,u.sigla,f.forma_farmaceutica,i.lote,i.invima,i.vencimiento,i.cantidad,i.vunitario,p.razon_social,i.codigo from medicamentos m, farc_inventario i, farc_proveedor p, farc_unidades u , farc_formafarmaceutica f where m.codigo=i.cod_medFk and p.codigo=i.cod_proveedorFk and i.cod_bodegaFk='"+xx+"' and m.cod_unidadFK=u.codigo and m.cod_formaFK=f.codigo and i.cantidad>0 and m.nombre like '"+texto+"%'");
	        	System.out.println("select m.codigo,m.nombre,m.concentracion,u.sigla,f.forma_farmaceutica,i.lote,i.invima,i.vencimiento,i.cantidad,i.vunitario,p.razon_social,i.codigo from medicamentos m, farc_inventario i, farc_proveedor p, farc_unidades u , farc_formafarmaceutica f where m.codigo=i.cod_medFk and p.codigo=i.cod_proveedorFk and i.cod_bodegaFk='"+xx+"' and m.cod_unidadFK=u.codigo and m.cod_formaFK=f.codigo and i.cantidad>0 and m.nombre like '"+texto+"%'");
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
	        	System.out.println("Error en MetodoEntradas>>listar Movimientoss "+ex);
	        }
	        	return r;     
	    }
	
	
	
	public ResultSet ValidaBodega(String bode,String usu) {
		/**
		 */
		  java.sql.ResultSet r=null;
	      Statement st = null;
	      try{
	       Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	r=st.executeQuery("select codigo from seg_bodegas_autorizadas where cod_bodegafk='"+bode+"' and cod_usuariofk='"+usu+"' ");
	      }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoEntradas>>ValidaBodega "+ex);
	        }
	        	return r;     
	    }
	
	public ResultSet listarMovimientossc(String movi) {
		/**
		 */
		  java.sql.ResultSet r=null;
	      Statement st = null;
	      try{
	       Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	r=st.executeQuery("select codigo,descripcion from farc_tipomovimiento where codigo='"+movi+"'");
	      }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoEntradas>>listarMovimientossc "+ex);
	        }
	        	return r;     
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
   	System.out.println("Error en MetodoCrearProveedor>>ObtenerArticulo "+ex);
   }	
   return rs;
}

public java.sql.ResultSet ObtenerArticuloe(String x){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 

   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery("select m.nombre, m.concentracion from medicamentos m, farc_inventario i where i.codigo='"+x+"' and i.cod_medFk=m.codigo");
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoCrearProveedor>>ObtenerArticuloe "+ex);
   }	
   return rs;
}

public java.sql.ResultSet ObtenerFormula(){	
	

   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery("select hf.codigo, hf.fecha, hf.Hora, p.primer_apellido, p.segundo_apellido, p.nombre, a.nombre, s.nombre, c.codigocama, up.nombre, up.apellido, hf.CodAdm_fk, hf.CodPac_fk   from hic_formulacion hf, adm_paciente p, adm_area a, adm_subarea s, adm_censo_cama c, seg_usuario u, seg_datos_personales up where (hf.estado = '0' OR hf.estado='2') and p.pac_codigo_paciente=hf.CodPac_fk and hf.CodArea_fk=a.codigo and hf.CodSubarea_fk=s.codigo and hf.CodCama_fk=c.cen_numero_cama and hf.CodUsu_fk=u.usu_codigo and u.dat_codigo_fk=up.dat_codigo AND hf.fecha BETWEEN (DATE_SUB(CURDATE(),INTERVAL 2 DAY)) AND CURDATE() order by hf.codigo");
   System.out.println("Estas son las formulas:  select hf.codigo, hf.fecha, hf.Hora, p.primer_apellido, p.segundo_apellido, p.nombre, a.nombre, s.nombre, c.codigocama, up.nombre, up.apellido, hf.CodAdm_fk, hf.CodPac_fk   from hic_formulacion hf, adm_paciente p, adm_area a, adm_subarea s, adm_censo_cama c, seg_usuario u, seg_datos_personales up where hf.estado='0' and p.pac_codigo_paciente=hf.CodPac_fk and hf.CodArea_fk=a.codigo and hf.CodSubarea_fk=s.codigo and hf.CodCama_fk=c.cen_numero_cama and hf.CodUsu_fk=u.usu_codigo and u.dat_codigo_fk=up.dat_codigo AND hf.fecha BETWEEN (DATE_SUB(CURDATE(),INTERVAL 2 DAY)) AND CURDATE()  order by hf.codigo");
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoCrearProveedor>>ObtenerFormula "+ex);
   }	
   return rs;
}
//////////////////

public java.sql.ResultSet ObtenerFormulae(String f){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 

   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery("select hf.codigo, hf.fecha, hf.Hora, p.primer_apellido, p.segundo_apellido, p.nombre, a.nombre, s.nombre, c.codigocama, up.nombre, up.apellido  from hic_formulacion hf, adm_paciente p, adm_area a, adm_subarea s, adm_censo_cama c, seg_usuario u, seg_datos_personales up where (hf.estado='0' or hf.estado='2') and p.pac_codigo_paciente=hf.CodPac_fk and hf.CodArea_fk=a.codigo and hf.CodSubarea_fk=s.codigo and hf.CodCama_fk=c.cen_numero_cama and hf.CodUsu_fk=u.usu_codigo and u.dat_codigo_fk=up.dat_codigo and hf.codigo='"+f+"'");
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoCrearProveedor>>ObtenerFormulae "+ex);
   }	
   return rs;
}


////////////////////////////////



public java.sql.ResultSet ObtenerMedicamento(String med){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 

   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
  // 	rs=st.executeQuery("select m.nombre, m.concentracion, df.cantidad, d.cantidad_dispensada, m.codigo, df.codigo, df.dosis, df.estado, d.codigo, u.sigla, ff.descripcion, i.lote, fmd.cod_invfk, fmd.codigo from hic_detalle_formulacion df, medicamentos m, farc_dispensacion d, farc_unidades u, farc_formafarmaceutica ff, farc_mov_dispensa fmd, farc_inventario i where df.CodFormulacion_fk='"+med+"' and df.CodMedicamento_fk=m.codigo and  df.codigo=d.cod_detalle_fk AND m.cod_unidadFK=u.codigo AND m.cod_formaFK=ff.codigo AND d.codigo=fmd.cod_dispensafk AND fmd.cod_invfk=i.codigo ");
   	rs=st.executeQuery("select m.nombre, m.concentracion, df.cantidad, d.cantidad_dispensada, m.codigo, df.codigo, df.dosis, df.estado, d.codigo, u.sigla, ff.descripcion from hic_detalle_formulacion df, medicamentos m, farc_dispensacion d, farc_unidades u, farc_formafarmaceutica ff where df.CodFormulacion_fk='"+med+"' and df.CodMedicamento_fk=m.codigo and  df.codigo=d.cod_detalle_fk AND m.cod_unidadFK=u.codigo AND m.cod_formaFK=ff.codigo GROUP BY df.codigo ");
    					
   	System.out.println("select m.nombre, m.concentracion, df.cantidad, d.cantidad_dispensada, m.codigo, df.codigo, df.dosis, df.estado, d.codigo, u.sigla, ff.descripcion from hic_detalle_formulacion df, medicamentos m, farc_dispensacion d, farc_unidades u, farc_formafarmaceutica ff where df.CodFormulacion_fk='"+med+"' and df.CodMedicamento_fk=m.codigo and  df.codigo=d.cod_detalle_fk AND m.cod_unidadFK=u.codigo AND m.cod_formaFK=ff.codigo GROUP BY df.codigo ");
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoSalidas>>ObtenerMedicamento "+ex);
   }	
   return rs;
}

public java.sql.ResultSet ObtenerMedicamentoCJ(String med){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 

   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
  	rs=st.executeQuery("select m.nombre, m.concentracion, df.cantidad, fmd.cantidad_dispensada, m.codigo, df.codigo, df.dosis, df.estado, d.codigo, u.sigla, ff.descripcion, i.lote, fmd.cod_invfk, fmd.codigo, fmd.fecha, fmd.hora,i.vencimiento from hic_detalle_formulacion df, medicamentos m, farc_dispensacion d, farc_unidades u, farc_formafarmaceutica ff, farc_mov_dispensa fmd, farc_inventario i  where df.CodFormulacion_fk='"+med+"' and df.CodMedicamento_fk=m.codigo and  df.codigo=d.cod_detalle_fk AND m.cod_unidadFK=u.codigo AND m.cod_formaFK=ff.codigo AND d.codigo=fmd.cod_dispensafk AND fmd.cod_invfk=i.codigo order by  m.nombre, fmd.fecha");
   //	rs=st.executeQuery("select m.nombre, m.concentracion, df.cantidad, d.cantidad_dispensada, m.codigo, df.codigo, df.dosis, df.estado, d.codigo, u.sigla, ff.descripcion from hic_detalle_formulacion df, medicamentos m, farc_dispensacion d, farc_unidades u, farc_formafarmaceutica ff where df.CodFormulacion_fk='"+med+"' and df.CodMedicamento_fk=m.codigo and  df.codigo=d.cod_detalle_fk AND m.cod_unidadFK=u.codigo AND m.cod_formaFK=ff.codigo ");
  
  	
  	System.out.println("select m.nombre, m.concentracion, df.cantidad, fmd.cantidad_dispensada, m.codigo, df.codigo, df.dosis, df.estado, d.codigo, u.sigla, ff.descripcion, i.lote, fmd.cod_invfk, fmd.codigo, fmd.fecha, fmd.hora, SUM(de.cantidad),i.vencimiento from hic_detalle_formulacion df, medicamentos m, farc_dispensacion d, farc_unidades u, farc_formafarmaceutica ff, farc_mov_dispensa fmd, farc_inventario i , farc_devolucion de  where df.CodFormulacion_fk='"+med+"' and df.CodMedicamento_fk=m.codigo and  df.codigo=d.cod_detalle_fk AND m.cod_unidadFK=u.codigo AND m.cod_formaFK=ff.codigo AND d.codigo=fmd.cod_dispensafk AND fmd.cod_invfk=i.codigo AND fmd.codigo=de.cod_mov_dispensafk AND d.codigo=de.cod_dispensacionFk GROUP BY de.cod_mov_dispensafk ");
    
	
  	
   	//System.out.println("select m.nombre, m.concentracion, df.cantidad, d.cantidad_dispensada, m.codigo, df.codigo, df.dosis, df.estado, d.codigo, u.sigla, ff.descripcion from hic_detalle_formulacion df, medicamentos m, farc_dispensacion d, farc_unidades u, farc_formafarmaceutica ff where df.CodFormulacion_fk='"+med+"' and df.CodMedicamento_fk=m.codigo and  df.codigo=d.cod_detalle_fk AND m.cod_unidadFK=u.codigo AND m.cod_formaFK=ff.codigo ");
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoSalidas>>ObtenerMedicamento "+ex);
   }	
   return rs;
}

public java.sql.ResultSet ObtenerMedicamentoCJxformula(String med){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 

   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
  	rs=st.executeQuery(" SELECT m.nombre, m.concentracion, df.cantidad, fmd.cantidad_dispensada, m.codigo, df.codigo, df.dosis, df.estado, d.codigo, u.sigla, ff.descripcion, i.lote, fmd.cod_invfk, fmd.codigo, fmd.fecha, fmd.hora,i.vencimiento,f.codigo, f.hora, f.fecha, dp.nombre, dp.apellido, CodPac_fk   FROM hic_detalle_formulacion df, medicamentos m, farc_dispensacion d, farc_unidades u, farc_formafarmaceutica ff, farc_mov_dispensa fmd, farc_inventario i, hic_formulacion f, seg_usuario us, seg_datos_personales dp  WHERE f.CodAdm_fk='"+med+"' AND  f.CodUsu_fk=us.usu_codigo AND  us.dat_codigo_fk=dp.dat_codigo AND df.CodFormulacion_fk=f.codigo AND df.CodMedicamento_fk=m.codigo AND  df.codigo=d.cod_detalle_fk AND m.cod_unidadFK=u.codigo AND m.cod_formaFK=ff.codigo AND d.codigo=fmd.cod_dispensafk AND fmd.cod_invfk=i.codigo ORDER BY  m.nombre, fmd.fecha   ");
   //	rs=st.executeQuery("select m.nombre, m.concentracion, df.cantidad, d.cantidad_dispensada, m.codigo, df.codigo, df.dosis, df.estado, d.codigo, u.sigla, ff.descripcion from hic_detalle_formulacion df, medicamentos m, farc_dispensacion d, farc_unidades u, farc_formafarmaceutica ff where df.CodFormulacion_fk='"+med+"' and df.CodMedicamento_fk=m.codigo and  df.codigo=d.cod_detalle_fk AND m.cod_unidadFK=u.codigo AND m.cod_formaFK=ff.codigo ");
  
  	
  	System.out.println("select m.nombre, m.concentracion, df.cantidad, fmd.cantidad_dispensada, m.codigo, df.codigo, df.dosis, df.estado, d.codigo, u.sigla, ff.descripcion, i.lote, fmd.cod_invfk, fmd.codigo, fmd.fecha, fmd.hora, SUM(de.cantidad),i.vencimiento from hic_detalle_formulacion df, medicamentos m, farc_dispensacion d, farc_unidades u, farc_formafarmaceutica ff, farc_mov_dispensa fmd, farc_inventario i , farc_devolucion de  where df.CodFormulacion_fk='"+med+"' and df.CodMedicamento_fk=m.codigo and  df.codigo=d.cod_detalle_fk AND m.cod_unidadFK=u.codigo AND m.cod_formaFK=ff.codigo AND d.codigo=fmd.cod_dispensafk AND fmd.cod_invfk=i.codigo AND fmd.codigo=de.cod_mov_dispensafk AND d.codigo=de.cod_dispensacionFk GROUP BY de.cod_mov_dispensafk ");
    
	
  	
   	//System.out.println("select m.nombre, m.concentracion, df.cantidad, d.cantidad_dispensada, m.codigo, df.codigo, df.dosis, df.estado, d.codigo, u.sigla, ff.descripcion from hic_detalle_formulacion df, medicamentos m, farc_dispensacion d, farc_unidades u, farc_formafarmaceutica ff where df.CodFormulacion_fk='"+med+"' and df.CodMedicamento_fk=m.codigo and  df.codigo=d.cod_detalle_fk AND m.cod_unidadFK=u.codigo AND m.cod_formaFK=ff.codigo ");
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoSalidas>>ObtenerMedicamento "+ex);
   }	
   return rs;
}

public java.sql.ResultSet ObtenerDevoluciones(String med, String inv, String cmd){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 

   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery("select d.cantidad, d.cod_invFk from farc_devolucion d, farc_dispensacion di where d.cod_dispensacionFk='"+med+"' and d.cod_dispensacionFk=di.codigo and d.cod_invFk='"+inv+"' and d.cod_mov_dispensafk='"+cmd+"' ");
   //	System.out.println("select d.cantidad, d.cod_invFk from farc_devolucion d, farc_dispensacion di where d.cod_dispensacionFk='"+med+"' and d.cod_dispensacionFk=di.codigo and d.cod_invFk='"+inv+"'");
    
   }
	catch(Exception ex){
   	System.out.println("Error en MetodoSalidas>>ObtenerDevoluciones "+ex);
   }	
   return rs;
}

//////////////////////////////////
public java.sql.ResultSet EstadosD(String f){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 

   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery("select hdf.codigo from  hic_detalle_formulacion hdf, farc_dispensacion fd where hdf.codigo=fd.cod_detalle_fk and hdf.cantidad=fd.cantidad_dispensada and hdf.CodFormulacion_fk='"+f+"' ");
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoSalidas>>EstadosD "+ex);
   }	
   return rs;
}



public void ActualizaEstadosD(String cod, String e){

	PreparedStatement st = null;
    try{
    	Conexion con=new Conexion();
    	st= con.conn.prepareStatement("update hic_detalle_formulacion set estado='"+e+"' where codigo='"+cod+"'");
    	st.executeUpdate();
    	st.close();
    	con.cerrar();
    	
    }
    catch(Exception ex){
    	System.out.println("ERROR EN MetodoSalidas>>Actualizaid "+ex);
    
    }	
}


public java.sql.ResultSet EstadosF(String f){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 

   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery("select hdf.CodFormulacion_fk from  hic_detalle_formulacion hdf where  hdf.CodFormulacion_fk='"+f+"' and hdf.estado='0' ");
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoSalidas>>EstadosF "+ex);
   }	
   return rs;
}


public void ActualizaEstadosF(String f){

	PreparedStatement st = null;
    try{
    	Conexion con=new Conexion();
    	st= con.conn.prepareStatement("update hic_formulacion set estado='1' where codigo='"+f+"'");
    	st.executeUpdate();
    	st.close();
    	con.cerrar();
    	
    }
    catch(Exception ex){
    	System.out.println("ERROR EN MetodoSalidas>>Actualizaid "+ex);
    
    }	
}

//////////////////////////////

public java.sql.ResultSet ObtenerBodegaM(String med, String usu){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 

   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery("SELECT * FROM(SELECT b.codigo, b.nombre, i.vencimiento FROM farc_bodegas b, farc_inventario i, seg_bodegas_autorizadas ba WHERE i.cod_medFk='"+med+"' AND i.cod_bodegaFk=b.codigo AND i.cantidad>0 AND b.codigo=ba.cod_bodegafk AND ba.cod_usuariofk='"+usu+"' ORDER BY i.vencimiento ) AS a GROUP BY a.nombre ORDER BY a.vencimiento ");
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoCrearProveedor>>ObtenerBodegaM "+ex);
   }	
   return rs;
}



public java.sql.ResultSet ObtenerBodegac(String bod){	
	/**
	 * consulta que obtiene las unidades del modulo de farmacia, 
	 */
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery("select codigo, nombre from farc_bodegas where codigo='"+bod+"'");
    }
    catch(Exception ex){
    	System.out.println("Error en MetodoEntradas>>ObtenerBodegac "+ex);
    }	
    return rs;
}

public java.sql.ResultSet ObtenerBodegasc(String bod, String med){	
	/**
	 * consulta que obtiene las unidades del modulo de farmacia, 
	 */
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery("select b.codigo, b.nombre from farc_bodegas b, farc_inventario i where i.cod_medFk='"+med+"' and i.cod_bodegaFk=b.codigo and i.cantidad>0 and b.codigo!='"+bod+"' group by b.nombre ");
    	//rs=st.executeQuery("select codigo, nombre from farc_bodegas where codigo!='"+bod+"'");
    }
    catch(Exception ex){
    	System.out.println("Error en MetodoEntradas>>ObtenerBodegasc "+ex);
    }	
    return rs;
}


public java.sql.ResultSet ObtenerMedicamentosL(String med, String bod){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 

   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery("select i.codigo, m.nombre, m.concentracion, i.lote, i.cantidad, i.vencimiento, u.sigla from medicamentos m, farc_inventario i, farc_unidades u  where i.cod_medFk='"+med+"' and i.cod_bodegaFk='"+bod+"' and i.cod_medFk=m.codigo and i.cantidad>0  AND m.cod_unidadFK=u.codigo  ORDER BY vencimiento ");
	//System.out.println("select i.codigo, m.nombre, m.concentracion, i.lote, i.cantidad, i.vencimiento from medicamentos m, farc_inventario i where i.cod_medFk='"+med+"' and i.cod_bodegaFk='"+bod+"' and i.cod_medFk=m.codigo and i.cantidad>0");
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoCrearProveedor>>ObtenerMedicamentosL "+ex);
   }	
   return rs;
}


public java.sql.ResultSet ObtenerMedicamentosLc(String med, String bod, String inv){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 

   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery("select i.codigo, m.nombre, m.concentracion, i.lote, i.cantidad from medicamentos m, farc_inventario i where i.cod_medFk='"+med+"' and i.cod_bodegaFk='"+bod+"' and i.cod_medFk=m.codigo and i.cantidad>0 and i.codigo='"+inv+"'");
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoCrearProveedor>>ObtenerMedicamentosLc "+ex);
   }	
   return rs;
}

public java.sql.ResultSet ObtenerMedicamentosLsc(String med, String bod, String inv){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 

   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery("select i.codigo, m.nombre, m.concentracion, i.lote, i.cantidad, i.vencimiento from medicamentos m, farc_inventario i where i.cod_medFk='"+med+"' and i.cod_bodegaFk='"+bod+"' and i.cod_medFk=m.codigo and i.cantidad>0 and i.codigo!='"+inv+"'");
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoCrearProveedor>>ObtenerMedicamentosLsc "+ex);
   }	
   return rs;
}


public java.sql.ResultSet ObtenerMedicamentosD(String cod){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 

   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery("select fmd.cod_invfk, fmd.cantidad_dispensada, m.nombre, m.concentracion, i.lote, u.sigla, ff.descripcion, fmd.codigo from farc_mov_dispensa fmd, farc_dispensacion fd, farc_inventario i, medicamentos m, farc_unidades u, farc_formafarmaceutica ff where fmd.cod_dispensafk=fd.codigo and fd.cod_detalle_fk='"+cod+"' and fmd.cod_invfk=i.codigo and i.cod_medFk=m.codigo AND m.cod_unidadFK=u.codigo AND m.cod_formaFK=ff.codigo");
	//System.out.println("select fmd.cod_invfk, fmd.cantidad_dispensada, m.nombre, m.concentracion, i.lote, u.sigla, ff.descripcion, fmd.codigo from farc_mov_dispensa fmd, farc_dispensacion fd, farc_inventario i, medicamentos m, farc_unidades u, farc_formafarmaceutica ff where fmd.cod_dispensafk=fd.codigo and fd.cod_detalle_fk='"+cod+"' and fmd.cod_invfk=i.codigo and i.cod_medFk=m.codigo AND m.cod_unidadFK=u.codigo AND m.cod_formaFK=ff.codigo");
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoSalidas>>ObtenerMedicamentosD "+ex);
   }	
   return rs;
}

public java.sql.ResultSet ObtenerMedicamentosD2(String cod){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 

   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery(" SELECT fmd.cod_invfk, fmd.cantidad_dispensada, m.nombre, m.concentracion, i.lote, i.invima, i.cantidad, u.sigla, ff.descripcion FROM farc_mov_dispensa fmd, farc_dispensacion fd, farc_inventario i, medicamentos m, farc_unidades u, farc_formafarmaceutica ff WHERE fmd.cod_dispensafk=fd.codigo AND fmd.codigo='"+cod+"' AND fmd.cod_invfk=i.codigo AND i.cod_medFk=m.codigo  AND m.cod_unidadFK=u.codigo AND m.cod_formaFK=ff.codigo  ");
	//System.out.println("SELECT fmd.cod_invfk, fmd.cantidad_dispensada, m.nombre, m.concentracion, i.lote, i.invima, i.cantidad FROM farc_mov_dispensa fmd, farc_dispensacion fd, farc_inventario i, medicamentos m WHERE fmd.cod_dispensafk=fd.codigo AND fmd.codigo='"+cod+"' AND fmd.cod_invfk=i.codigo AND i.cod_medFk=m.codigo  ");
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoSalidas>>ObtenerMedicamentosD2 "+ex);
   }	
   return rs;
}
/*
public java.sql.ResultSet ObtenerCntidadI(String det, String inv){	
	
	//consulta que obtiene la cantidad dispensada del inventario para devolver, 

   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery(" select fmd.cantidad_dispensada from farc_mov_dispensa fmd, farc_dispensacion fd, farc_inventario i where fmd.cod_dispensafk=fd.codigo and fd.cod_detalle_fk='"+det+"' and fmd.cod_invfk=i.codigo and i.codigo='"+inv+"' ");
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoSalidas>>ObtenerMedicamentosD "+ex);
   }	
   return rs;
}
*/
///////////////////////////////////////

public java.sql.ResultSet ObtenerIdentificador(String formula){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 

   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery("select identificadorm from  farc_identificador_mov where formula='"+formula+"'");
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoCrearProveedor>>ObtenerIdentificador "+ex);
   }	
   return rs;
}

/////////////////////////////////////

public java.sql.ResultSet listarDocumentos(){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 

   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery("select sigla from adm_tipodocumento");
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoSalidas>>listarDocumentos "+ex);
   }	
   return rs;
}


public java.sql.ResultSet ConsultarPaciente(String D, String N){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 

   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery("select primer_apellido, segundo_apellido, nombre, pac_codigo_paciente from adm_paciente where tipo_documento='"+D+"' and numero_documento='"+N+"'");
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoSalidas>>listarDocumentos "+ex);
   }	
   return rs;
}

public java.sql.ResultSet ConsultarAdmisiones(String cp){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 

   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery("select adm_numero_ingreso, fecha_registro from adm_admisiones where pac_codigo_paciente_fk='"+cp+"' order by fecha_registro desc limit 1 ");
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoSalidas>>ConsultarAdmisiones "+ex);
   }	
   return rs;
}


public java.sql.ResultSet ConsultarFormulas(String adm){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 

   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery("select codigo, hora, fecha, dp.nombre, dp.apellido, CodPac_fk  from hic_formulacion, seg_usuario u, seg_datos_personales dp where CodAdm_fk='"+adm+"' and  CodUsu_fk=u.usu_codigo and  u.dat_codigo_fk=dp.dat_codigo  ");
   	System.out.println("select codigo, hora, fecha, dp.nombre, dp.apellido, CodPac_fk  from hic_formulacion, seg_usuario u, seg_datos_personales dp where CodAdm_fk='"+adm+"' and  CodUsu_fk=u.usu_codigo and  u.dat_codigo_fk=dp.dat_codigo  ");
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoSalidas>>ConsultarFormulas "+ex);
   }	
   return rs;
}


public java.sql.ResultSet ConsultarDevoluciones(String adm, String cod){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 

   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery("SELECT  SUM(cantidad) FROM farc_devolucion WHERE cod_dispensacionFk='"+adm+"' AND cod_mov_dispensafk='"+cod+"'   ");
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoSalidas>>ConsultarDevoluciones "+ex);
   }	
   return rs;
}

///////////////////////////////////

public java.sql.ResultSet ObtenerConsecutivo(String c){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 

   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery("SELECT sigla,consec FROM farc_consecutivos WHERE codigo='"+c+"'  ");
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoEntradas>>ObtenerMovimiento "+ex);
   }	
   return rs;
}


public void ActualizaConsecutivo(String c, String n){

	PreparedStatement st = null;
    try{
    	Conexion con=new Conexion();
    	st= con.conn.prepareStatement("update farc_consecutivos set consec='"+n+"' where codigo='"+c+"'");
    	st.executeUpdate();
    	st.close();
    	con.cerrar();
    	
    }
    catch(Exception ex){
    	System.out.println("ERROR EN MetodoSalidas>>Actualizaid "+ex);
    
    }	
}

public void CrearMovimientos(String cantidad, String movimiento, String fecha,  String hra, String user, String factura, String concepto, String consec){
	
	// creamos los ingresos de movimientos
	
	Entradas ci = new Entradas();
	
	//ci.setbodega(bodega);
	ci.setcantidad(cantidad);
	ci.setmovimiento(movimiento);
	ci.setfecha(fecha);
	ci.setfactura(factura);
	ci.setconcepto(concepto);
	
	
	try{
			PreparedStatement ps = null;
		    Conexion con=new Conexion();
		    ps=con.conn.prepareStatement("insert into farc_movimientos(cantidad,tipo_mvtoFk,fecha,hora,cod_usuariofK,nsoporte,concepto,consec)values(?,?,?,?,?,?,?,?)");
		  //  ps.setString(1,ci.getbodega());
		    ps.setString(1,ci.getcantidad());
		    ps.setString(2,ci.getmovimiento());
		    ps.setString(3,ci.getfecha());
		    ps.setString(4,hra);//hora
		    ps.setString(5,user);//cod_usuario
		    ps.setString(6,ci.getfactura());
		    ps.setString(7,ci.getconcepto());
		    ps.setString(8,consec);//hora
		    ps.executeUpdate();
			ps.close();
			con.cerrar();				
		}catch(Exception ex){
        	System.out.println("ERROR EN MetodoEntradas>>CrearMovimientos "+ex);
		}

	}

//////////////////////////

public void CrearDetalle(String movi,String inv, String bod, String ca){
	
	// creamos los ingresos de movimientos
	
	try{
			PreparedStatement ps = null;
		    Conexion con=new Conexion();
		    ps=con.conn.prepareStatement("insert into farc_detallemov(cod_movFk,cod_invFk,cod_bodegai, cantidad)values(?,?,?,?)");
		    ps.setString(1,movi);
		    ps.setString(2,inv);
		    ps.setString(3,bod);
		    ps.setString(4,ca);
		    ps.executeUpdate();
			ps.close();
			con.cerrar();				
		}catch(Exception ex){
        	System.out.println("ERROR EN MetodoSalidas>>CrearDetalle "+ex);
		}

	}

/////////////////////////

public void CrearIdentificadorm(String formula){
	
	// creamos los ingresos de movimientos
	
	try{
			PreparedStatement ps = null;
		    Conexion con=new Conexion();
		    ps=con.conn.prepareStatement("insert into farc_identificador_mov(formula,identificadorm)values(?,?)");
		    ps.setString(1,formula);
		    ps.setString(2,"1");
		    ps.executeUpdate();
			ps.close();
			con.cerrar();				
		}catch(Exception ex){
        	System.out.println("ERROR EN MetodoSalidas>>CrearIdentificador "+ex);
		}

	}



public void Actualizaid(String formula, String id){

	PreparedStatement st = null;
    try{
    	Conexion con=new Conexion();
    	st= con.conn.prepareStatement("update farc_identificador_mov set identificadorm='"+id+"' where formula='"+formula+"'");
    	st.executeUpdate();
    	st.close();
    	con.cerrar();
    	
    }
    catch(Exception ex){
    	System.out.println("ERROR EN MetodoSalidas>>Actualizaid "+ex);
    
    }	
}

///////////////////////

public void CrearMovDis(String cd,String cad, String tm, String fec, String hra, String usu, String cf, String inv, String bod, String id, String enca){
	
	// creamos los ingresos de movimientos
	
	try{
			PreparedStatement ps = null;
		    Conexion con=new Conexion();
		    ps=con.conn.prepareStatement("insert into farc_mov_dispensa(cod_dispensafk,cantidad_dispensada,tipo_mvtofk,fecha,hora,usuario,cod_formulacionfk,cod_invfk,bodegai,identificador,cod_enca_fact_fk)values(?,?,?,?,?,?,?,?,?,?,?)");
		    ps.setString(1,cd);
		    ps.setString(2,cad);
		    ps.setString(3,tm);
		    ps.setString(4,fec);
		    ps.setString(5,hra);
		    ps.setString(6,usu);
		    ps.setString(7,cf);
		    ps.setString(8,inv);
		    ps.setString(9,bod);
		    ps.setString(10,id);
		    ps.setString(11,enca);
		    ps.executeUpdate();
			ps.close();
			con.cerrar();				
		}catch(Exception ex){
        	System.out.println("ERROR EN MetodoSalidas>>CrearMovDis "+ex);
		}

	}

////////////////

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
   	System.out.println("Error en MetodoCrearProveedor>>ObtenerUMovimiento "+ex);
   }	
   return rs;
}


public java.sql.ResultSet ObtenerUDevolucion(String fec, String hra){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 

   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery("select codigo from farc_devolucion where fecha='"+fec+"' and hora='"+hra+"' ");
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoCrearProveedor>>ObtenerUMovimiento "+ex);
   }	
   return rs;
}
///////////////////////


public java.sql.ResultSet ConsultarMov(String id, String formula){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 

   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery("select fmd.cantidad_dispensada, fmd.tipo_mvtofk, fmd.usuario, fmd.concepto, fmd.cod_invfk, fmd.bodegai from  farc_mov_dispensa fmd, farc_dispensacion fd where fmd.identificador='"+id+"' and fmd.cod_dispensafk=fd.codigo and fmd.cod_formulacionfk='"+formula+"'");
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoCrearProveedor>>ConsultarMov "+ex);
   }	
   return rs;
}



public java.sql.ResultSet ConsultarEncabezado(String adm, String cp){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 

   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery("SELECT cod_enc_factura FROM fact_enc_factura WHERE cod_admision='"+adm+"' AND cod_eps=(SELECT e.ent_nit FROM adm_entidad e, adm_convenio c, adm_paciente p WHERE e.ent_nit=c.ent_nit_contratante_fk AND p.pac_codigo_paciente='"+cp+"' AND p.conv_numero_contrato_fk=c.conv_numero_contrato) ");
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoSalidas>>ConsultarEncabezado "+ex);
   }	
   return rs;
}
/////////////////////////////




public void LlenarMov(String tcs, String tp, String fec, String hra, String us, String formula, String co, String consec){
	
	// creamos los ingresos de movimientos
	
	try{
			PreparedStatement ps = null;
		    Conexion con=new Conexion();
		    ps=con.conn.prepareStatement("insert into farc_movimientos(cantidad,tipo_mvtoFk,fecha,hora,cod_usuarioFk,nsoporte,concepto,consec)values(?,?,?,?,?,?,?,?)");
		    ps.setString(1,tcs);
		    ps.setString(2,tp);
		    ps.setString(3,fec);
		    ps.setString(4,hra);
		    ps.setString(5,us);
		    ps.setString(6,formula);
		    ps.setString(7,co);
		    ps.setString(8,consec);//hora
		    ps.executeUpdate();
			ps.close();
			con.cerrar();				
		}catch(Exception ex){
        	System.out.println("ERROR EN MetodoSalidas>>LlenarMov "+ex);
		}

	}


	public void LlenarDMov(String movi,String inv, String bod, String ca){
		
		// creamos los ingresos de movimientos
		
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("insert into farc_detallemov(cod_movFk,cod_invFk,cod_bodegai, cantidad)values(?,?,?,?)");
			    ps.setString(1,movi);
			    ps.setString(2,inv);
			    ps.setString(3,bod);
			    ps.setString(4,ca);
			    ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoSalidas>>LlenarDMov "+ex);
			}

		}

/////////////////////////////

public java.sql.ResultSet ObtenerUDispensa(String cd){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 

   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery("select codigo from farc_dispensacion where cod_detalle_fk='"+cd+"' ");
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoCrearProveedor>>ObtenerUDispensa "+ex);
   }	
   return rs;
}

///////////////////////////
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
//formulacion servicio farmaceutico
public java.sql.ResultSet Consultare(String inv){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 

   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery("select cantidad, vunitario,cod_ivaFk,lote,cod_medFk from farc_inventario where codigo='"+inv+"' ");
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoCrearProveedor>>Consultare "+ex);
   }	
   return rs;
}

public java.sql.ResultSet Consultard(String detalle){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 

   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery("select cantidad_dispensada from farc_dispensacion where cod_detalle_fk='"+detalle+"' ");
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoCrearProveedor>>Consultard "+ex);
   }	
   return rs;
}

public void Dispensari(String inv,String ca){

	PreparedStatement st = null;
    try{
    	Conexion con=new Conexion();
    	st= con.conn.prepareStatement("update farc_inventario set cantidad='"+ca+"' where codigo='"+inv+"'");
    	st.executeUpdate();
    	st.close();
    	con.cerrar();
    	
    }
    catch(Exception ex){
    	System.out.println("ERROR EN MetodoSalidas>>Dispensari "+ex);
    
    }	
}

public void Dispensarf(String detalle,String ca){

	PreparedStatement st = null;
    try{
    	Conexion con=new Conexion();
    	st= con.conn.prepareStatement("update farc_dispensacion set cantidad_dispensada='"+ca+"' where cod_detalle_fk='"+detalle+"'");
    	st.executeUpdate();
    	st.close();
    	con.cerrar();
    	
    }
    catch(Exception ex){
    	System.out.println("ERROR EN MetodoSalidas>>Dispensarf "+ex);
    
    }	
}

///////////////////////////////////////////////////////

//////////////////////////////////////////////////////////
public java.sql.ResultSet Dispensacion(String formula){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 

   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery("select fd.cantidad_dispensada from farc_dispensacion fd, hic_detalle_formulacion df where df.CodFormulacion_fk='"+formula+"' and fd.cod_detalle_fk=df.codigo");
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoCrearProveedor>>Dispensacion "+ex);
   }	
   return rs;
}
/////////////////////////////

public java.sql.ResultSet CantidadEnInventario(String inv){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 

   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery("select cantidad from farc_inventario where codigo='"+inv+"'");
   //	System.out.println("select cantidad from farc_inventario where codigo='"+inv+"'");
    
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoSalidas>>CantidadEnInventario "+ex);
   }	
   return rs;
}

public void Devolucion(String inv,String ca){

	PreparedStatement st = null;
    try{
    	Conexion con=new Conexion();
    	st= con.conn.prepareStatement("update farc_inventario set cantidad='"+ca+"' where codigo='"+inv+"'");
    	st.executeUpdate();
    	st.close();
    	con.cerrar();
    	
    }
    catch(Exception ex){
    	System.out.println("ERROR EN MetodoSalidas>>Dispensari "+ex);
    
    }	
}

///////////////////////////////////
public void LlenarDevolucion(String cd,String ca, String usu, String fec, String hra, String lot, String conc, String codpa, String cmd){
	
	// creamos los ingresos de movimientos
	
	try{
			PreparedStatement ps = null;
		    Conexion con=new Conexion();
		    ps=con.conn.prepareStatement("insert into farc_devolucion(cod_dispensacionFk, cantidad, usuario, fecha, hora, cod_invFk, concepto, codpaciente, cod_mov_dispensafk)values(?,?,?,?,?,?,?,?,?)");
		    ps.setString(1,cd);
		    ps.setString(2,ca);
		    ps.setString(3,usu);
		    ps.setString(4,fec);
		    ps.setString(5,hra);
		    ps.setString(6,lot);
		    ps.setString(7,conc);
		    ps.setString(8,codpa);
		    ps.setString(9,cmd);
		    ps.executeUpdate();
			ps.close();
			con.cerrar();				
		}catch(Exception ex){
        	System.out.println("ERROR EN MetodoSalidas>>LlenarDevolucion "+ex);
		}

	}




/****CARGUE AUTOMATICO A FACTURACION*********/

public java.sql.ResultSet ConsultarEncabezado(String inv){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 

   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery("SELECT cod_enc_factura FROM fact_enc_factura WHERE cod_admision='"+inv+"'");
   //	System.out.println("select cantidad from farc_inventario where codigo='"+inv+"'");
    
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoSalidas>>ConsultarEncabezado "+ex);
   }	
   return rs;
}

public java.sql.ResultSet ConsultarPrograma(String inv){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 

   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery("SELECT cod_prog FROM fact_prog_med WHERE cod_med='"+inv+"'");
   //	System.out.println("select cantidad from farc_inventario where codigo='"+inv+"'");
    
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoSalidas>>ConsultarPrograma "+ex);
   }	
   return rs;
}

public java.sql.ResultSet DetallePrograma(String inv){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 

   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery("SELECT p.cod_referencia,p.descripcion,cs.descripcion,p.subcentro_costo FROM fact_programas p, fact_clases_servicio cs WHERE p.cod_programa='"+inv+"' AND p.clase_servicio=cs.cod_clase_servicio");
   //	System.out.println("select cantidad from farc_inventario where codigo='"+inv+"'");
    
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoSalidas>>DetallePrograma "+ex);
   }	
   return rs;
}

public java.sql.ResultSet ValorPrograma(String pac, String pro ){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 

   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery("   SELECT t.valor FROM adm_paciente p, adm_convenio ac, fact_convenios fc, fact_tarifas_convenios tc, fact_tarifas t WHERE p.pac_codigo_paciente='"+pac+"' AND p.conv_numero_contrato_fk=ac.conv_numero_contrato AND ac.ent_nit_contratante_fk=fc.cod_entidad AND fc.cod_convenio=tc.cod_convenio AND tc.cod_manualTarifario=t.manual_tarifario AND t.programa='"+pro+"' ");
   //	System.out.println("select cantidad from farc_inventario where codigo='"+inv+"'");
    
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoSalidas>>ValorPrograma "+ex);
   }	
   return rs;
}



public void CargarDetalle(String fecha,String hora,String pop,String cod_programafk,String cod_programa,String nombre_programa,String cod_paquete,String nombre_paquete,String clase_servicio,String fecha_realizacion,String cantidad,String valor,String cod_usuario,String cod_enc_factura_fk,String cod_medico,String subc,String formaqx, String p){
	//System.out.print(cod_medico);
	try{
			PreparedStatement ps = null;
		    Conexion con=new Conexion();
		    															
		    ps=con.conn.prepareStatement("insert into fact_det_factura(fecha,hora,tipopop,cod_programafk,cod_programa,nombre_programa,cod_paquete,nombre_paquete,clase_servicio,fecha_realizacion,cantidad,valor,cod_usuario,cod_enc_factura_fk,cod_medico,subcentrodecosto,formaactoqx,porcentaje)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		    ps.setString(1,fecha);
		    ps.setString(2,hora);
		    ps.setString(3,pop);
		    ps.setString(4,cod_programafk);
		    ps.setString(5,cod_programa);
		    ps.setString(6,nombre_programa);
		    ps.setString(7,cod_paquete);
		    ps.setString(8,nombre_paquete);
		    ps.setString(9,clase_servicio);
		    ps.setString(10,fecha_realizacion);
		    ps.setString(11,cantidad);
		    ps.setString(12,valor);
		    ps.setString(13,cod_usuario);
		    ps.setString(14,cod_enc_factura_fk);
		    ps.setString(15,cod_medico);
		    ps.setString(16,subc);
		    ps.setString(17,formaqx);
		    ps.setString(18,p);
		    
		 	ps.executeUpdate();
			ps.close();
			con.cerrar();				
		}catch(Exception ex){
        	System.out.println("ERROR EN. MetodoSalidas>>CargarDetalle "+ex);
		}
	}

public void CargarDetalleSintarifa(String cod_programafk){
	//System.out.print(cod_medico);
	try{
			PreparedStatement ps = null;
		    Conexion con=new Conexion();
		    															
		    ps=con.conn.prepareStatement("insert into fact_programassintarifa(cod_programafk)values(?)");
		    ps.setString(1,cod_programafk);
		   
		    
		 	ps.executeUpdate();
			ps.close();
			con.cerrar();				
		}catch(Exception ex){
        	System.out.println("ERROR EN. MetodoSalidas>>CargarDetalleSintarifa "+ex);
		}
	}



public java.sql.ResultSet ObtenerEncabezado(String fec){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 

   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery("SELECT fecha,hora,cod_enca_fact_fk FROM farc_mov_dispensa WHERE codigo='"+fec+"' ");
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoCrearProveedor>>ObtenerEncabezado "+ex);
   }	
   return rs;
}

public java.sql.ResultSet ObtenerCantidadCargado(String enca, String fec, String hra){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 

   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery("SELECT cantidad FROM fact_det_factura WHERE cod_enc_factura_fk='"+enca+"' AND fecha='"+fec+"' AND hora='"+hra+"' ");
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoCrearProveedor>>ObtenerEncabezado "+ex);
   }	
   return rs;
}

public void ActualizaCargue(String enca, String fec, String hra, String ca){

	PreparedStatement st = null;
    try{
    	Conexion con=new Conexion();
    	st= con.conn.prepareStatement("update fact_det_factura set cantidad='"+ca+"' WHERE cod_enc_factura_fk='"+enca+"' AND fecha='"+fec+"' AND hora='"+hra+"'");
    	st.executeUpdate();
    	st.close();
    	con.cerrar();
    	
    }
    catch(Exception ex){
    	System.out.println("ERROR EN MetodoSalidas>>Dispensari "+ex);
    
    }	
}

public void EliminaCargue(String enca, String fec, String hra){

	PreparedStatement st = null;
    try{
    	Conexion con=new Conexion();
    	st= con.conn.prepareStatement("DELETE FROM fact_det_factura WHERE cod_enc_factura_fk='"+enca+"' AND fecha='"+fec+"' AND hora='"+hra+"'");
    	st.executeUpdate();
    	st.close();
    	con.cerrar();
    	
    }
    catch(Exception ex){
    	System.out.println("ERROR EN MetodoSalidas>>Dispensari "+ex);
    
    }	
}



///////////////////////////////////////////////////////////PERFIL FARMACOTERAPEUTICO/////(/((((((((((((((((//////////////////

public java.sql.ResultSet ConsultarDetallePerfil(String dformula){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 

   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery(" SELECT codigo FROM farc_detalle_perfil_farmacoterapeutico WHERE cod_dformulacion='"+dformula+"' ");
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoSalidas>>ConsultarDetallePerfil "+ex);
   }	
   return rs;
}

public java.sql.ResultSet ConsultarDispensacionPerfil(String dperfil){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 

   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery(" SELECT codigo FROM farc_detalle_dispensa_perfil WHERE cod_detalleperfilfk='"+dperfil+"' ");
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoSalidas>>ConsultarDispensacionPerfil "+ex);
   }	
   return rs;
}


public void InsertarDispensacionPerfil(String cd,String ca, String cad, String fec, String hra, String usu){
	
	// creamos los ingresos de movimientos
	
	try{
			PreparedStatement ps = null;
		    Conexion con=new Conexion();
		    ps=con.conn.prepareStatement(" INSERT INTO farc_detalle_dispensa_perfil(cod_detalleperfilfk, cantidad_dispensada, cantidad_devuelta, fecha, hora, cod_usuariofk) values(?,?,?,?,?,?)");
		    ps.setString(1,cd);
		    ps.setString(2,ca);
		    ps.setString(3,cad);
		    ps.setString(4,fec);
		    ps.setString(5,hra);
		    ps.setString(6,usu);
		    ps.executeUpdate();
			ps.close();
			con.cerrar();				
		}catch(Exception ex){
        	System.out.println("ERROR EN MetodoSalidas>>InsertarDispensacionPerfil "+ex);
		}

	}

public void ActualizarDispensacionPerfil(String ca,String fec,String hra,String usu,String detalle){

	PreparedStatement st = null;
    try{
    	Conexion con=new Conexion();
    	st= con.conn.prepareStatement(" UPDATE farc_detalle_dispensa_perfil SET cantidad_dispensada='"+ca+"', fecha='"+fec+"', hora='"+hra+"', cod_usuariofk='"+usu+"'  WHERE cod_detalleperfilfk='"+detalle+"' ");
    	st.executeUpdate();
    	st.close();
    	con.cerrar();
    	
    }
    catch(Exception ex){
    	System.out.println("ERROR EN MetodoSalidas>>ActualizarDispensacionPerfil "+ex);
    
    }	
}


public java.sql.ResultSet ConsultarDetalleFormulacion(String dformula){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 

   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery(" SELECT cod_detalle_fk FROM farc_dispensacion  WHERE codigo='"+dformula+"' ");
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoSalidas>>ConsultarDetallePerfil "+ex);
   }	
   return rs;
}


public void ActualizarDispensacionPerfilD(String ca,String fec,String hra,String usu,String detalle){

	PreparedStatement st = null;
    try{
    	Conexion con=new Conexion();
    	st= con.conn.prepareStatement(" UPDATE farc_detalle_dispensa_perfil SET cantidad_devuelta='"+ca+"', fecha='"+fec+"', hora='"+hra+"', cod_usuariofk='"+usu+"'  WHERE cod_detalleperfilfk='"+detalle+"' ");
    	st.executeUpdate();
    	st.close();
    	con.cerrar();
    	
    }
    catch(Exception ex){
    	System.out.println("ERROR EN MetodoSalidas>>ActualizarDispensacionPerfil "+ex);
    
    }	
}




public java.sql.ResultSet ConsultarTipoMed(String dformula){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 

   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery(" SELECT cod_grupoFK FROM medicamentos WHERE codigo='"+dformula+"' ");
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoSalidas>>ConsultarTipoMed "+ex);
   }	
   return rs;
}




/////////////COSTOS DE DISPENSACION//////////////
public java.sql.ResultSet ConsultarProgramaMedicamento(String dformula){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 

   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery("  SELECT cod_prog FROM fact_prog_med WHERE cod_med='"+dformula+"' ");
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoSalidas>>ConsultarTipoMed "+ex);
   }	
   return rs;
}


public void InsertarCostos(String admp,String inv, String fec, String hra, String ca, String vunitario, String vtotal, String codm, String lote,String entidad){
	
	// creamos los ingresos de movimientos
	
	try{
			PreparedStatement ps = null;
		    Conexion con=new Conexion();
		    ps=con.conn.prepareStatement("insert into farc_costosdedispensacion(cod_admfk,cod_invfk,fecha,hora,cantidad,cunitario,ctotal,cod_medfk,lote,cod_programafk)values(?,?,?,?,?,?,?,?,?,?)");
		    ps.setString(1,admp);
		    ps.setString(2,inv);
		    ps.setString(3,fec);
		    ps.setString(4,hra);
		    ps.setString(5,ca);
		    ps.setString(6,vunitario);
		    ps.setString(7,vtotal);
		    ps.setString(8,codm);
		    ps.setString(9,lote);
		    ps.setString(10,entidad);
		    ps.executeUpdate();
			ps.close();
			con.cerrar();				
		}catch(Exception ex){
        	System.out.println("ERROR EN MetodoSalidas>>InsertarCostos "+ex);
		}

	}


public java.sql.ResultSet ObtenerDevolucionesalCosto(String codmovdis){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 

   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery(" SELECT fmd.cod_invfk,fmd.cantidad_dispensada,hdf.CodMedicamento_fk,hf.CodAdm_fk, (SELECT (fmd.cantidad_dispensada-d.cantidad) FROM farc_mov_dispensa fmd, farc_dispensacion fd, hic_detalle_formulacion hdf, hic_formulacion hf,farc_devolucion d WHERE fmd.codigo='"+codmovdis+"' AND fmd.cod_dispensafk=fd.codigo AND fd.cod_detalle_fk=hdf.codigo AND hdf.CodFormulacion_fk=hf.codigo AND d.cod_dispensacionFk=fmd.cod_dispensafk AND d.cod_invFk=fmd.cod_invfk AND fmd.codigo=d.cod_mov_dispensafk  LIMIT 1) AS total, i.lote  FROM farc_mov_dispensa fmd, farc_dispensacion fd, hic_detalle_formulacion hdf, hic_formulacion hf, farc_inventario i WHERE fmd.codigo='"+codmovdis+"' AND fmd.cod_dispensafk=fd.codigo AND fd.cod_detalle_fk=hdf.codigo AND hdf.CodFormulacion_fk=hf.codigo AND fmd.cod_invfk=i.codigo ");
   	System.out.println(" SELECT fmd.cod_invfk,fmd.cantidad_dispensada,hdf.CodMedicamento_fk,hf.CodAdm_fk, (SELECT (fmd.cantidad_dispensada-d.cantidad) FROM farc_mov_dispensa fmd, farc_dispensacion fd, hic_detalle_formulacion hdf, hic_formulacion hf,farc_devolucion d WHERE fmd.codigo='"+codmovdis+"' AND fmd.cod_dispensafk=fd.codigo AND fd.cod_detalle_fk=hdf.codigo AND hdf.CodFormulacion_fk=hf.codigo AND d.cod_dispensacionFk=fmd.cod_dispensafk AND d.cod_invFk=fmd.cod_invfk AND fmd.codigo=d.cod_mov_dispensafk  LIMIT 1) AS total, i.lote  FROM farc_mov_dispensa fmd, farc_dispensacion fd, hic_detalle_formulacion hdf, hic_formulacion hf, farc_inventario i WHERE fmd.codigo='"+codmovdis+"' AND fmd.cod_dispensafk=fd.codigo AND fd.cod_detalle_fk=hdf.codigo AND hdf.CodFormulacion_fk=hf.codigo AND fmd.cod_invfk=i.codigo ");
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoCrearProveedor>>ObtenerDevolucionesalCosto "+ex);
   }	
   return rs;
}
///

public java.sql.ResultSet ObtenerCostosdeDispensacion(String adm, String inv, String med, String lot, String ca){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 

   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery(" SELECT codigo,cantidad,cunitario FROM farc_costosdedispensacion WHERE cod_admfk='"+adm+"' AND cod_invfk='"+inv+"' AND cod_medfk='"+med+"' AND lote='"+lot+"' AND cantidad='"+ca+"' ");
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoCrearProveedor>>ObtenerCostosdeDispensacion "+ex);
   }	
   return rs;
}


public void ActualizarCostos(String ca,String tot,String cod){

	PreparedStatement st = null;
    try{
    	Conexion con=new Conexion();
    	st= con.conn.prepareStatement(" UPDATE farc_costosdedispensacion SET cantidad='"+ca+"', ctotal='"+tot+"'  WHERE codigo='"+cod+"' ");
    	st.executeUpdate();
    	st.close();
    	con.cerrar();
    	
    }
    catch(Exception ex){
    	System.out.println("ERROR EN MetodoSalidas>>ActualizarCostos "+ex);
    
    }	
}



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
