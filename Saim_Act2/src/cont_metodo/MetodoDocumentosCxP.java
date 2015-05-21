package cont_metodo;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Time;

import adm_logica.Conexion;

public class MetodoDocumentosCxP {
	
	
	public java.sql.ResultSet AutoCompletarChequera(String NomChequera,String CodBanco){	
		
		//System.out.println("SELECT * FROM cont_historico_chequera WHERE cod_banco_fk="+CodBanco+" AND cod_chequera LIKE '%"+NomChequera+"%'");
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM cont_historico_chequera WHERE cod_banco_fk="+CodBanco+" AND cod_chequera LIKE '%"+NomChequera+"%'");
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoDocumentosCxP>>AutoCompletarChequera "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet ConsultarDocumentosCxP(String SQL){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT DC.codigo,dc.numero_documento,dc.factura_proveedor,dc.fecha_documento,ct.nombre_razonsoc FROM cont_documentos_cxp dc,cont_terceros ct WHERE dc.cod_proveedor_fk=ct.codigo  "+SQL+"  ORDER BY dc.numero_documento");
        	
        	System.out.println("SELECT DC.codigo,dc.numero_documento,dc.factura_proveedor,dc.fecha_documento,ct.nombre_razonsoc FROM cont_documentos_cxp dc,cont_terceros ct WHERE dc.cod_proveedor_fk=ct.codigo  "+SQL+"  ORDER BY dc.numero_documento");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoDocumentosCxP>>ConsultarDocumentosCxP "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet ConsultaSaldoDocumentoCxP(String CodDocumentoCxP){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT SUM(valor_pagar) AS valor,cod_intercontable_fk FROM cont_detalle_documentos_cxp WHERE cod_documentocxp_fk="+CodDocumentoCxP+"");
        	System.out.println("SELECT SUM(valor_pagar) AS valor,cod_intercontable_fk FROM cont_detalle_documentos_cxp WHERE cod_documentocxp_fk="+CodDocumentoCxP+"");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoDocumentosCxP>>ConsultaSaldoDocumentoCxP "+ex);
        }	
        return rs;
    }
	
	
	
	public java.sql.ResultSet ConsultarBancos(String Parametro){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM cont_bancos WHERE nombre LIKE '%"+Parametro+"%'  OR sigla LIKE '%"+Parametro+"%' limit 20 ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoDocumentosCxP>>ConsultarBancos "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ConsultarPeriodo(String p, String a){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT codigo, bloqueo,bloqueo_cxp FROM cont_anio_periodo WHERE anio="+a+" AND periodo="+p);
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoDocumentosCxP>>ConsultarPeriodo "+ex);
        }	
        return rs;
    }

	public java.sql.ResultSet ConsultarTiposDocumentoscCxP(){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	//rs=st.executeQuery(" SELECT * FROM cont_tipo_documento_cxp");
        	rs=st.executeQuery(" SELECT * FROM cont_tipo_documento");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoDocumentosCxP>>ConsultarTiposDocumentoscCxP "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ConsultarPlantillas(){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM cont_plantillas_contables ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoDocumentosCxP>>ConsultarPlantillas "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet ConsultarDetallePlantillas(String cod){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT codigo,cod_cuenta_fk,formula_base,formula_debito,formula_credito FROM cont_detalle_plantillas WHERE cod_plantilla_contable_fk="+cod+" ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoDocumentosCxP>>ConsultarDetallePlantillas "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerInterfasContable(String CodInterfaz){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM cont_interfases_contable WHERE codigo="+CodInterfaz+" ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoDocumentosCxP>>InterfasContable "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet InterfasContable(){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM cont_interfases_contable ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoDocumentosCxP>>InterfasContable "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet ObtenerDatosTipoAfectacion(String CodTipoAfectacion){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT cdc.codigo,cc.codigo,cc.NombreCuenta,cs.codigo,cs.nombre,fc.codigo,fc.NombreCentroCosto,fsc.cod_subcentro_costo,fsc.descripcion FROM cont_distribucion_contable cdc,cont_cuentas cc,cont_sucursales cs,fact_centrocosto fc,fact_subcentros_costo fsc WHERE cdc.cod_centrocco_fk=fc.codigo AND cdc.cod_cuenta_fk=cc.codigo AND cdc.cod_subcentrocco_fk=fsc.cod_subcentro_costo AND cdc.cod_sucursal_fk=cs.codigo AND cdc.cod_discontable_fk="+CodTipoAfectacion+" ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoDocumentosCxP>>ObtenerDatosTipoAfectacion "+ex);
        }	
        return rs;
    }
	
	
	
	public java.sql.ResultSet Sucursales(){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT codigo, nombre FROM cont_sucursales ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoDocumentosCxP>>Sucursales "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet SucursalesDiferentes(String cod){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT codigo, nombre FROM cont_sucursales where codigo!="+cod+" ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoDocumentosCxP>>Sucursales "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet CentrosdeCosto(String codSucursal){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT cc.codigo, cc.NombreCentroCosto,scc.codigo FROM fact_centrocosto cc, cont_surcursal_centrocosto scc WHERE  scc.cod_sucursal_fk="+codSucursal+" AND cc.codigo=scc.cod_centro_costo_fk ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoDocumentosCxP>>CentrosdeCosto "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet CentrosdeCostoSolo(){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT cc.codigo, cc.NombreCentroCosto FROM fact_centrocosto cc");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoDocumentosCxP>>CentrosdeCostoSolo "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet SubCentrosdeCosto(String cod){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT sc.cod_subcentro_costo, sc.descripcion,scs.codigo FROM fact_subcentros_costo sc, cont_centro_subcentro scs WHERE  scs.cod_centro_costo_fk="+cod+" AND sc.cod_subcentro_costo=scs.cod_sub_centro_costo_fk ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoDocumentosCxP>>SubCentrosdeCosto "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet SubCentrosdeCostoSolo(){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT sc.cod_subcentro_costo, sc.descripcion FROM fact_subcentros_costo sc");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoDocumentosCxP>>SubCentrosdeCostoSolo "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet DatosTerceros(String CodTercero){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT * FROM cont_terceros where codigo="+CodTercero+" ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoDocumentosCxP>>DatosTerceros "+ex);
        }	
        return rs;
    }

	public java.sql.ResultSet AutocompletaDistribucionContable(String TipoAfectacion){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT * FROM cont_ingreso_distribucion_contable WHERE nombre LIKE '%"+TipoAfectacion+"%' ");
        	System.out.println(" SELECT * FROM cont_ingreso_distribucion_contable WHERE nombre LIKE '%"+TipoAfectacion+"%' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoDocumentosCxP>>AutocompletaDistribucionContable "+ex);
        }	
        return rs;
    }
	
		public java.sql.ResultSet ConsecutivosdeCuentas(String cod){	
			//System.out.println(" SELECT sigla, consecutivo FROM cont_tipo_documento_cxp WHERE tipo_documento="+cod+" ");
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	//rs=st.executeQuery(" SELECT sigla, consecutivo FROM cont_tipo_documento_cxp WHERE tipo_documento="+cod+" ");
        	rs=st.executeQuery(" SELECT sigla, consecutivo FROM cont_tipo_documento WHERE codigo="+cod+" ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoDocumentosCxP>>ConsecutivosdeCuentas "+ex);
        }	
        return rs;
    }
		
		
		
	
		
	public void CrearDocumentoCxP(String TipoDocumento,String FechaMovimientoCxP,String NumeroDocumentoCxP,String DetalleCxP,
			String NumeroFacturaProveedor,String CodTercero,String TipoAfectacionCxP,String DiasPlazo,
			String FechaRecibo,String pec,String anc,Date Fecha,Time Hora,String Usuario,
			String cod_banco_fk,String cod_hcheque_fk,String indicador_pago,String Numero_Cheque){
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("INSERT INTO cont_documentos_cxp(anio,periodo," +
			    		"cod_tipo_documento_fk,numero_documento,fecha_documento, " +
			    		"detalle,cod_banco_fk,cod_hcheque_fk, " +
			    		"cod_afectacion_contable_fk,dias_plazo, " +
			    		"cod_proveedor_fk,factura_proveedor, " +
			    		"fecha_recibo,indicador_pago,fecha_registro,hora_registro,codUsuario,num_cheque) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			    
			    ps.setString(1,anc);
			    ps.setString(2,pec);
			    ps.setString(3,TipoDocumento);
			    ps.setString(4,NumeroDocumentoCxP);
			    ps.setString(5,FechaMovimientoCxP);
			    ps.setString(6,DetalleCxP);
			    ps.setString(7,cod_banco_fk);//
			    ps.setString(8,cod_hcheque_fk);//
			    ps.setString(9,TipoAfectacionCxP);
			    ps.setString(10,DiasPlazo);
			    ps.setString(11,CodTercero);
			    ps.setString(12,NumeroFacturaProveedor);
			    ps.setString(13,FechaRecibo);
			    ps.setString(14,indicador_pago);//
			    ps.setDate(15,Fecha);//
			    ps.setTime(16,Hora);//
			    ps.setString(17,Usuario);//
			    ps.setString(18,Numero_Cheque);//
			    
			    
			    ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoDocumentosCxP>>CrearDocumentoCxP "+ex);
			}
		}
	
	
	public java.sql.ResultSet ObtenerDocumentosEA(String NumeroDocumentoEA,String TipoDocumentoEA){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM cont_documentos_cxp WHERE cod_tipo_documento_fk='"+TipoDocumentoEA+"' AND numero_documento LIKE '%"+NumeroDocumentoEA+"%' ");
        	System.out.println("SELECT * FROM cont_documentos_cxp WHERE cod_tipo_documento_fk='"+TipoDocumentoEA+"' AND numero_documento LIKE '%"+NumeroDocumentoEA+"%' ");

        }
        catch(Exception ex){
        	System.out.println("Error en MetodoDocumentosCxP>>ObtenerCodigoDocumentoCxP "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerDocumentosEAT(String NumeroDocumentoEA,String TipoDocumentoEA,String CodTercero){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT  cd.*, SUM(cdc.saldo_pagar) suma FROM cont_documentos_cxp cd,cont_detalle_documentos_cxp cdc WHERE cd.cod_proveedor_fk = "+CodTercero+"  AND cd.cod_tipo_documento_fk = '"+TipoDocumentoEA+"'  AND cd.numero_documento LIKE '%"+NumeroDocumentoEA+"%'  AND cd.codigo=cdc.cod_documentocxp_fk ");
        	System.out.println("SELECT  cd.*, SUM(cdc.saldo_pagar) suma FROM cont_documentos_cxp cd,cont_detalle_documentos_cxp cdc WHERE cd.cod_proveedor_fk = "+CodTercero+"  AND cd.cod_tipo_documento_fk = '"+TipoDocumentoEA+"'  AND cd.numero_documento LIKE '%"+NumeroDocumentoEA+"%'  AND cd.codigo=cdc.cod_documentocxp_fk ");

        }
        catch(Exception ex){
        	System.out.println("Error en MetodoDocumentosCxP>>ObtenerCodigoDocumentoCxP "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet ObtenerCodigoDocumentoCxP(String NumeroDocumentoCxP){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT * FROM cont_documentos_cxp WHERE numero_documento='"+NumeroDocumentoCxP+"' ");
        	System.out.println(" SELECT * FROM cont_documentos_cxp WHERE numero_documento='"+NumeroDocumentoCxP+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoDocumentosCxP>>ObtenerCodigoDocumentoCxP "+ex);
        }	
        return rs;
    }
	
	
	/*public java.sql.ResultSet HistoricoChequera(String CodChequera){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoDocumentosCxP>>ObtenerCodigoDocumentoCxP "+ex);
        }	
        return rs;
    }*/
	
	
	
	/*public java.sql.ResultSet ConsecutivoUDocumento(String cod){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT codigo FROM cont_documentos WHERE numero_documento='"+cod+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoDocumentosCxP>>ConsecutivoUDocumento "+ex);
        }	
        return rs;
    }*/
	
	
	public void ActualizarValorenLetras(String ValorLetras, String CodDocumentoCxP){	
		 PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE cont_documentos_cxp SET valor_letras='"+ValorLetras+"' WHERE codigo="+CodDocumentoCxP+" ");
	     	//System.out.println("UPDATE cont_detalle_documentos_cxp SET saldo_pagar='"+SaldoDoc+"' WHERE codigo="+CodDetDocu+" ");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoDocumentosCxP>>ActualizarValorDocumento "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	
	
	public void ActualizarValorDocumento(String CodDetDocu, String SaldoDoc){	
		 PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE cont_detalle_documentos_cxp SET saldo_pagar='"+SaldoDoc+"' WHERE codigo="+CodDetDocu+" ");
	     	//System.out.println("UPDATE cont_detalle_documentos_cxp SET saldo_pagar='"+SaldoDoc+"' WHERE codigo="+CodDetDocu+" ");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoDocumentosCxP>>ActualizarValorDocumento "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	
	
	public void ActualizarConsecutivoChequera(String NumeroCheque, String CodChequera){	
		//System.out.println("UPDATE cont_tipo_documento_cxp SET consecutivo='"+ctan+"' WHERE tipo_documento='"+td+"' ");
		 PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE cont_historico_chequera SET consecutivo_siguiente='"+NumeroCheque+"' WHERE codigo="+CodChequera+" ");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoDocumentosCxP>>ActualizarConsecutivoChequera "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	
	public void ActualizarConsecutivo(String ctan, String td){	
		 PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	//st= con.conn.prepareStatement("UPDATE cont_tipo_documento_cxp SET consecutivo='"+ctan+"' WHERE tipo_documento='"+td+"' ");
	     	st= con.conn.prepareStatement("UPDATE cont_tipo_documento SET consecutivo='"+ctan+"' WHERE codigo='"+td+"' ");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoDocumentosCxP>>ActualizarConsecutivo "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	
	public java.sql.ResultSet Cuentas(String cod){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT codigo,CodigoCuenta,NombreCuenta FROM cont_cuentas WHERE TipoCuenta='Auxiliar' AND CodigoCuenta LIKE '"+cod+"%' limit 100");
          }
        catch(Exception ex){
        	System.out.println("Error en MetodoDocumentosCxP>>Cuentas "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet Terceros(String cod){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT codigo, numero_documento, razon_social FROM cont_terceros WHERE numero_documento LIKE '"+cod+"%' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoDocumentosCxP>>Sucursales "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet DetallePlantillas(String cod){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT cod_cuenta_fk,nombre_cuenta_fk,formula_base,formula_debito,formula_credito FROM cont_detalle_plantillas WHERE cod_plantilla_contable_fk="+cod+" ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoDocumentosCxP>>DetallePlantillas "+ex);
        }	
        return rs;
    }
	
	
	public void CrearDetalleDocumentoCxPEA(String CodDocumentoCxP, 
			String CodSucursal,String  CodCCosto, 
			String CodScosto,String  InterfasContable,
			String tipo_referencia,String documento_referencia,
			String valor_documento, String origen,
			String ValDesc,String ValRetFue,String ValRetIva,
			String ValIca,String ValPagar){
		
		   System.out.println("INSERT INTO cont_detalle_documentos_cxp  ( " +
		    		"cod_documentocxp_fk," +
		    		"cod_sucursal_fk," +
		    		"cod_centrocco_fk," +
		    		"cod_scentrocco_fk, " +
		    		"cod_intercontable_fk," +
		    		"tipo_referencia," +
		    		"documento_referencia," +
		    		"valor_documento," +
		    		"origen," +
		    		"valor_desc, " +
		    		"valor_retefuente, " +
		    		"valor_reteiva," +
		    		"valor_ica," +
		    		"valor_pagar," +
		    		"saldo_pagar)" +
		    		" VALUES ("+CodDocumentoCxP+","+CodSucursal+"," +
		    		""+CodCCosto+","+CodScosto+","+InterfasContable+"," +
		    		""+tipo_referencia+",'"+documento_referencia+"'," +
		    		""+valor_documento+",'"+origen+"',"+ValDesc+"," +
		    		""+ValRetFue+","+ValRetIva+","+ValIca+","+ValPagar+","+ValPagar+")");
		
	   /* System.out.println("CodDocumentoCxP "+CodDocumentoCxP+" CodSucursal "+CodSucursal+" CodCCosto "+CodCCosto+
	    		" CodScosto "+CodScosto+" InterfasContable "+InterfasContable+" tipo_referencia "+tipo_referencia+
	    		" documento_referencia "+documento_referencia+" valor_documento "+valor_documento+" origen "+origen+
	    		" ValDesc "+ValDesc+" ValRetFue "+ValRetFue+" ValRetIva "+ValRetIva+" ValIca "+ValIca+" ValPagar "+ValPagar);
*/
		try{
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("INSERT INTO cont_detalle_documentos_cxp  ( " +
				    		"cod_documentocxp_fk," +
				    		"cod_sucursal_fk," +
				    		"cod_centrocco_fk," +
				    		"cod_scentrocco_fk, " +
				    		"cod_intercontable_fk," +
				    		"tipo_referencia," +
				    		"documento_referencia," +
				    		"valor_documento," +
				    		"origen," +
				    		"valor_desc, " +
				    		"valor_retefuente, " +
				    		"valor_reteiva," +
				    		"valor_ica," +
				    		"valor_pagar," +
				    		"saldo_pagar," +
				    		"valor)" +
				    		" VALUES ("+CodDocumentoCxP+","+CodSucursal+"," +
				    		""+CodCCosto+","+CodScosto+","+InterfasContable+"," +
				    		""+tipo_referencia+",'"+documento_referencia+"'," +
				    		""+valor_documento+",'"+origen+"',"+ValDesc+"," +
				    		""+ValRetFue+","+ValRetIva+","+ValIca+","+ValPagar+"," +
				    		""+ValPagar+","+valor_documento+")");
				    
				 
				    
				    //ps.setString(1,CodDocumentoCxP);
				    //ps.setString(2,CodSucursal);
				    //ps.setString(3,CodCCosto);
				    //ps.setString(4,CodScosto);
				    //ps.setString(5,InterfasContable);
				    //ps.setString(6,tipo_referencia);
				    //ps.setString(7,documento_referencia);
				    //ps.setString(8,valor_documento);
				    //ps.setString(9,origen);
				    //ps.setString(10,ValDesc);
				    //ps.setString(11,ValRetFue);
				    //ps.setString(12,ValRetIva);
				    //ps.setString(13,ValIca);
				    //ps.setString(14,ValPagar);				    	
				    //ps.setString(15,ValPagar);	
				  
				    ps.executeUpdate();
					ps.close();
					con.cerrar();				
				}catch(Exception ex){
		        	System.out.println("ERROR EN MetodoDocumentosCxP>>CrearDetalleDocumentoCxPEA "+ex);
				}
			}
	
	
	public void CrearDetalleDocumentoCxPANT(String CodDocumentoCxP,String CodSucursal,
			String CodCCosto,String CodScosto,String InterfasContable,
			String tipo_anticipo,String documento_anticipo,String origen,String valor_anticipo,
			String SalPagar
		){
			try{
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("INSERT INTO cont_detalle_documentos_cxp (" +
				    		" cod_documentocxp_fk,cod_sucursal_fk,cod_centrocco_fk,cod_scentrocco_fk, " +
				    		" cod_intercontable_fk,tipo_anticipo,documento_anticipo,origen," +
				    		" valor_anticipo,saldo_pagar) " +
				    		" VALUES(?,?,?,?,?,?,?,?,?,?)");
				    ps.setString(1,CodDocumentoCxP);
				    ps.setString(2,CodSucursal);
				    ps.setString(3,CodCCosto);
				    ps.setString(4,CodScosto);
				    ps.setString(5,InterfasContable);
				    ps.setString(6,tipo_anticipo);
				    ps.setString(7,documento_anticipo);
				    ps.setString(8,origen);
				    ps.setString(9,valor_anticipo);
				    ps.setString(10,SalPagar);
				    ps.executeUpdate();
					ps.close();
					con.cerrar();				
				}catch(Exception ex){
		        	System.out.println("ERROR EN MetodoDocumentosCxP>>CrearDetalleDocumento "+ex);
				}
			}
	
	public void CrearDetalleDocumentoCxP(String CodDocumentoCxP,String CodSucursal,
		String CodCCosto,String CodScosto,String InterfasContable,
		String valor_documento,String origen,String Neto,String PorDesc,
		String ValDesc,String Subtot,String PorIva,String ValIva,
		String ValorFactu,String PorRetFue,String ValRetFue,String PorRetIva,
		String ValRetIva,String PorIca,String ValIca,String ValPagar,String SalPagar
	){
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("INSERT INTO cont_detalle_documentos_cxp (cod_documentocxp_fk,cod_sucursal_fk,cod_centrocco_fk,cod_scentrocco_fk, " +
			    		" cod_intercontable_fk,valor_documento,origen,neto,porc_desc,valor_desc, " +
			    		" subtotal,porc_iva,valor_iva,total,porc_retefuente, " +
			    		" valor_retefuente,porc_reteiva,valor_reteiva,porc_ica,valor_ica, " +
			    		" valor_pagar,saldo_pagar) " +
			    		" VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			    ps.setString(1,CodDocumentoCxP);
			    ps.setString(2,CodSucursal);
			    ps.setString(3,CodCCosto);
			    ps.setString(4,CodScosto);
			    ps.setString(5,InterfasContable);
			    ps.setString(6,valor_documento);
			    ps.setString(7,origen);
			    ps.setString(8,Neto);
			    ps.setString(9,PorDesc);
			    ps.setString(10,ValDesc);
			    ps.setString(11,Subtot);
			    ps.setString(12,PorIva);
			    ps.setString(13,ValIva);
			    ps.setString(14,ValorFactu);
			    ps.setString(15,PorRetFue);
			    ps.setString(16,ValRetFue);
			    ps.setString(17,PorRetIva);
			    ps.setString(18,ValRetIva);
			    ps.setString(19,PorIca);
			    ps.setString(20,ValIca);
			    ps.setString(21,ValPagar);
			    ps.setString(22,SalPagar);
			    ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoDocumentosCxP>>CrearDetalleDocumento "+ex);
			}
		}
	
	public void OmitirDetalleDocumentoCxP(String CodDetCxP){	
		/**
		 */
		 PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("delete from cont_detalle_documentos_cxp where codigo="+CodDetCxP+"");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoDocumentosCxP>>OmitirDetalleDocumentoCxP "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public void ActualizarDocumentoDebitoCredito(String docu, String vd, String vc){	
		/**
		 */
		 PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE cont_documentos SET valor_debito='"+vd+"', valor_credito='"+vc+"' WHERE codigo='"+docu+"' ");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoDocumentosCxP>>ActualizarDocumentoDebitoCredito "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	
	public java.sql.ResultSet ConsultarDetalleDocumentoCxpNumeroDocumentoT(String numero_documento){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT cdc.* FROM cont_documentos_cxp cd,cont_detalle_documentos_cxp cdc WHERE cd.numero_documento='"+numero_documento+"' AND cd.codigo=cdc.cod_documentocxp_fk ");
        	System.out.println(" SELECT cdc.* FROM cont_documentos_cxp cd,cont_detalle_documentos_cxp cdc WHERE cd.numero_documento='"+numero_documento+"' AND cd.codigo=cdc.cod_documentocxp_fk ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoDocumentosCxP>>ConsultarDetalleDocumentoCxpNumeroDocumentoT "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ConsultarDetalleDocumentoCxpNumeroDocumento(String numero_documento){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT cdc.* FROM cont_documentos_cxp cd,cont_detalle_documentos_cxp cdc WHERE cd.numero_documento='"+numero_documento+"' AND cd.codigo=cdc.cod_documentocxp_fk and cdc.saldo_pagar!=0 ");
        	System.out.println(" SELECT cdc.* FROM cont_documentos_cxp cd,cont_detalle_documentos_cxp cdc WHERE cd.numero_documento='"+numero_documento+"' AND cd.codigo=cdc.cod_documentocxp_fk and cdc.saldo_pagar!=0 ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoDocumentosCxP>>ConsultarDetalleDocumentoCxp "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ConsultarDetalleDocumentoCxp(String CodDocCxP){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT * FROM cont_detalle_documentos_cxp WHERE cod_documentocxp_fk="+CodDocCxP+" ");
        	System.out.println(" SELECT * FROM cont_detalle_documentos_cxp WHERE cod_documentocxp_fk="+CodDocCxP+" ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoDocumentosCxP>>ConsultarDetalleDocumentoCxp "+ex);
        }	
        return rs;
    }
	
	
	
	public java.sql.ResultSet ConsultarDocumentos(String sql){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT * FROM cont_documentos WHERE codigo!=0  "+sql+" ");
        	System.out.println(" SELECT * FROM cont_documentos WHERE codigo!=0  "+sql+" ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoDocumentosCxP>>DetallePlantillas "+ex);
        }	
        return rs;
    }
	
	
}//Fin MetodoDocumentosCxP
