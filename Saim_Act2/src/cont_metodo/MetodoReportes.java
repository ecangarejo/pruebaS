package cont_metodo;

import java.sql.Statement;

import adm_logica.Conexion;

public class MetodoReportes {

	
	public java.sql.ResultSet BuscarTDoc(){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo, nombre, sigla from cont_tipo_documento");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProveedor>>BuscarTDoc"+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet BuscarEmpresa(){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select nombre, nit, logo from empresa");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoReportes>>Empresa"+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet BuscarCuentas4(String codigo){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT codigo,CodigoCuenta,NombreCuenta FROM cont_cuentas where CodigoCuenta LIKE '%"+codigo+"%' OR NombreCuenta LIKE '%"+codigo+"%' ORDER BY CodigoCuenta ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProveedor>>BuscarCuentas "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet AgrupadoPorFecha(String Fechai, String Fechaf, String TDOC){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(TDOC.equals("todos")){
        	rs=st.executeQuery("SELECT cd.fecha, SUM(cdd.valor_debito), SUM(cdd.valor_credito)  FROM cont_documentos cd , cont_detalle_documentos cdd, cont_cuentas cc, cont_tipo_documento ctd "+
        						"WHERE cd.codigo=cdd.cod_documento_fk AND cdd.cod_cuenta_fk=cc.codigo AND ctd.codigo=cd.tipo_documento_fk AND cd.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"' GROUP BY cd.fecha ");
        	}else{
        	rs=st.executeQuery("SELECT cd.fecha, SUM(cdd.valor_debito), SUM(cdd.valor_credito)  FROM cont_documentos cd , cont_detalle_documentos cdd, cont_cuentas cc, cont_tipo_documento ctd "+
						"WHERE cd.codigo=cdd.cod_documento_fk AND cdd.cod_cuenta_fk=cc.codigo AND ctd.codigo=cd.tipo_documento_fk AND cd.tipo_documento_fk='"+TDOC+"' AND cd.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"' GROUP BY cd.fecha ");
        	System.out.println("SELECT cd.fecha, SUM(cdd.valor_debito), SUM(cdd.valor_credito)  FROM cont_documentos cd , cont_detalle_documentos cdd, cont_cuentas cc, cont_tipo_documento ctd "+
					"WHERE cd.codigo=cdd.cod_documento_fk AND cdd.cod_cuenta_fk=cc.codigo AND ctd.codigo=cd.tipo_documento_fk AND cd.tipo_documento_fk='"+TDOC+"' AND cd.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"' GROUP BY cd.fecha ");
        	}
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoReportes>>AgrupadoPorFecha "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet AgrupadoPorTdoc(String Fecha, String TDOC){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(TDOC.equals("todos")){
        	rs=st.executeQuery("SELECT cd.tipo_documento_fk, ctd.nombre FROM cont_documentos cd , cont_detalle_documentos cdd, cont_cuentas cc, cont_tipo_documento ctd "+
        						"WHERE cd.codigo=cdd.cod_documento_fk AND cdd.cod_cuenta_fk=cc.codigo AND ctd.codigo=cd.tipo_documento_fk AND cd.fecha='"+Fecha+"' GROUP BY cd.tipo_documento_fk ");
        	}else{
        	rs=st.executeQuery("SELECT cd.tipo_documento_fk, ctd.nombre FROM cont_documentos cd , cont_detalle_documentos cdd, cont_cuentas cc, cont_tipo_documento ctd "+
						"WHERE cd.codigo=cdd.cod_documento_fk AND cdd.cod_cuenta_fk=cc.codigo AND ctd.codigo=cd.tipo_documento_fk AND cd.tipo_documento_fk='"+TDOC+"' AND cd.fecha='"+Fecha+"' GROUP BY cd.tipo_documento_fk ");
        	}
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoReportes>>AgrupadoPorTdoc "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet AgrupadoPorCuenta(String TDOC, String fecha){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT cc.CodigoCuenta, cc.NombreCuenta, SUM(cdd.valor_debito), SUM(cdd.valor_credito) "+
        				"FROM cont_documentos cd , cont_detalle_documentos cdd, cont_cuentas cc, cont_tipo_documento ctd "+
        				"WHERE cd.codigo=cdd.cod_documento_fk AND cdd.cod_cuenta_fk=cc.codigo AND ctd.codigo=cd.tipo_documento_fk  AND cd.tipo_documento_fk ='"+TDOC+"' AND cd.fecha='"+fecha+"' GROUP BY cc.CodigoCuenta ");

        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoReportes>>AgrupadoPorCuenta "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet BuscarCuentas5(String codigo){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT codigo,CodigoCuenta,NombreCuenta, LEFT(CodigoCuenta,LOCATE('',CodigoCuenta,1)) as cuenta FROM cont_cuentas where CodigoCuenta LIKE '%"+codigo+"%' OR NombreCuenta LIKE '%"+codigo+"%' ORDER BY CodigoCuenta ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoReportes>>BuscarCuentas5 "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarCuentas3(){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT CodigoCuenta,NombreCuenta FROM cont_cuentas where TipoCuenta='Auxiliar' ORDER BY CodigoCuenta");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoReportes>>BuscarCuentas3 "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarCuentasAux(String codigo){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT codigo,CodigoCuenta,NombreCuenta FROM cont_cuentas where TipoCuenta='Auxiliar' and (CodigoCuenta LIKE '%"+codigo+"%' OR NombreCuenta LIKE '%"+codigo+"%' ) ORDER BY CodigoCuenta");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoReportes>>BuscarCuentasAux "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarSaldoAnt(String RC1,String RC2,String AC,String MC,String MC2, String TDOC){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	System.out.println("RC1"+RC1+" RC2"+RC2+" MC"+MC+" MC2"+MC2+" AC"+AC+" TDOC"+TDOC);
        	if((RC1.equals("TODAS"))&&(RC2.equals("TODAS"))){
        		if(TDOC.equals("todas")){
        		rs=st.executeQuery("SELECT cc.codigo, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior) , SUM(cdasc.saldo_nuevo)"+
						"FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc "+
						"WHERE cc.TipoCuenta='Auxiliar' and cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk AND casc.anio='"+AC+"' AND cdasc.periodo BETWEEN '"+MC+"' AND '"+MC2+"'  "+
						"AND cc.codigo IN (SELECT cdd.cod_cuenta_fk FROM cont_documentos cd, cont_detalle_documentos cdd WHERE cd.codigo=cdd.cod_documento_fk AND cd.anio='"+AC+"' AND cd.periodo BETWEEN '"+MC+"' AND '"+MC2+"' ) GROUP BY cc.codigo");
        		System.out.println("val1");
        		System.out.println("SELECT cc.codigo, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior) , SUM(cdasc.saldo_nuevo)"+
						"FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc "+
						"WHERE cc.TipoCuenta='Auxiliar' and cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk AND casc.anio='"+AC+"' AND cdasc.periodo BETWEEN '"+MC+"' AND '"+MC2+"'  "+
						"AND cc.codigo IN (SELECT cdd.cod_cuenta_fk FROM cont_documentos cd, cont_detalle_documentos cdd WHERE cd.codigo=cdd.cod_documento_fk AND cd.anio='"+AC+"' AND cd.periodo BETWEEN '"+MC+"' AND '"+MC2+"' ) GROUP BY cc.codigo");
        		}else{
        			rs=st.executeQuery("SELECT cc.codigo, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior) , SUM(cdasc.saldo_nuevo)"+
    						"FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc "+
    						"WHERE cc.TipoCuenta='Auxiliar' and cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk AND casc.anio='"+AC+"' AND cdasc.periodo BETWEEN '"+MC+"' AND '"+MC2+"'  "+
    						"AND cc.codigo IN (SELECT cdd.cod_cuenta_fk FROM cont_documentos cd, cont_detalle_documentos cdd WHERE cd.codigo=cdd.cod_documento_fk AND cd.tipo_documento_fk='"+TDOC+"' AND cd.anio='"+AC+"' AND cd.periodo BETWEEN '"+MC+"' AND '"+MC2+"' ) GROUP BY cc.codigo");
        		System.out.println("val2");
        		System.out.println("SELECT cc.codigo, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior) , SUM(cdasc.saldo_nuevo)"+
						"FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc "+
						"WHERE cc.TipoCuenta='Auxiliar' and cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk AND casc.anio='"+AC+"' AND cdasc.periodo BETWEEN '"+MC+"' AND '"+MC2+"'  "+
						"AND cc.codigo IN (SELECT cdd.cod_cuenta_fk FROM cont_documentos cd, cont_detalle_documentos cdd WHERE cd.codigo=cdd.cod_documento_fk AND cd.tipo_documento_fk='"+TDOC+"' AND cd.anio='"+AC+"' AND cd.periodo BETWEEN '"+MC+"' AND '"+MC2+"' ) GROUP BY cc.codigo");
        		}
        		
        	}else{
        		if(TDOC.equals("todas")){
		        	rs=st.executeQuery("SELECT cc.codigo, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior), SUM(cdasc.saldo_nuevo) "+
		        						"FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc "+
		        						"WHERE cc.TipoCuenta='Auxiliar' and cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk AND casc.anio='"+AC+"' AND cdasc.periodo BETWEEN '"+MC+"' AND '"+MC2+"' AND cc.CodigoCuenta BETWEEN '"+RC1+"'  AND  '"+RC2+"' "+
		        						"AND cc.codigo IN (SELECT cdd.cod_cuenta_fk FROM cont_documentos cd, cont_detalle_documentos cdd WHERE cd.codigo=cdd.cod_documento_fk AND cd.anio='"+AC+"' AND cd.periodo BETWEEN '"+MC+"' AND '"+MC2+"') GROUP BY cc.codigo");
        		System.out.println("val3");
        		System.out.println("SELECT cc.codigo, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior), SUM(cdasc.saldo_nuevo) "+
				"FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc "+
				"WHERE cc.TipoCuenta='Auxiliar' and cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk AND casc.anio='"+AC+"' AND cdasc.periodo BETWEEN '"+MC+"' AND '"+MC2+"' AND cc.CodigoCuenta BETWEEN '"+RC1+"'  AND  '"+RC2+"' "+
				"AND cc.codigo IN (SELECT cdd.cod_cuenta_fk FROM cont_documentos cd, cont_detalle_documentos cdd WHERE cd.codigo=cdd.cod_documento_fk AND cd.anio='"+AC+"' AND cd.periodo BETWEEN '"+MC+"' AND '"+MC2+"') GROUP BY cc.codigo");
        		}else{
        			rs=st.executeQuery("SELECT cc.codigo, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior), SUM(cdasc.saldo_nuevo) "+
    						"FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc "+
    						"WHERE cc.TipoCuenta='Auxiliar' and cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk AND casc.anio='"+AC+"' AND cdasc.periodo BETWEEN '"+MC+"' AND '"+MC2+"' AND cc.CodigoCuenta BETWEEN '"+RC1+"'  AND  '"+RC2+"' "+
    						"AND cc.codigo IN (SELECT cdd.cod_cuenta_fk FROM cont_documentos cd, cont_detalle_documentos cdd WHERE cd.codigo=cdd.cod_documento_fk AND cd.tipo_documento_fk='"+TDOC+"' AND cd.anio='"+AC+"' AND cd.periodo BETWEEN '"+MC+"' AND '"+MC2+"') GROUP BY cc.codigo");
        		
        		System.out.println("val4");
        		System.out.println("SELECT cc.codigo, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior), SUM(cdasc.saldo_nuevo) "+
						"FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc "+
						"WHERE cc.TipoCuenta='Auxiliar' and cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk AND casc.anio='"+AC+"' AND cdasc.periodo BETWEEN '"+MC+"' AND '"+MC2+"' AND cc.CodigoCuenta BETWEEN '"+RC1+"'  AND  '"+RC2+"' "+
						"AND cc.codigo IN (SELECT cdd.cod_cuenta_fk FROM cont_documentos cd, cont_detalle_documentos cdd WHERE cd.codigo=cdd.cod_documento_fk AND cd.tipo_documento_fk='"+TDOC+"' AND cd.anio='"+AC+"' AND cd.periodo BETWEEN '"+MC+"' AND '"+MC2+"') GROUP BY cc.codigo");
        		}
        	}
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoReportes>>BuscarSaldoAnt "+ex);
        }	
        return rs;
    }
	
	
	
	public java.sql.ResultSet BuscarSaldoAntSucCost(String RC1,String RC2,String AC,String MC,String MC2, String TDOC, String Suc, String ccosto){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	System.out.println("RC1"+RC1+" RC2"+RC2+" MC"+MC+" MC2"+MC2+" AC"+AC+" TDOC"+TDOC+"Suc"+Suc+"ccosto"+ccosto);
        	//System.out.println("BuscarSaldoAntSucCost");
        	if((RC1.equals("TODAS"))&&(RC2.equals("TODAS"))){
        		if(TDOC.equals("todas")){
        			if((Suc.equals(""))&&(ccosto.equals(""))){
        				//aqui no va nada solo sirve esto para hacer la validacion 
        			}else{
        				if(ccosto.equals("")){
        					rs=st.executeQuery("SELECT cc.codigo, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior), SUM(cdasc.saldo_nuevo) " +
        							"FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, cont_surcursal_centrocosto csc, fact_centrocosto fc, cont_sucursales cs  " +
        							"WHERE cc.TipoCuenta = 'Auxiliar' AND cc.codigo = casc.cod_cuenta_fk "+
        							"AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk   AND csc.cod_sucursal_fk=casc.cod_sucursal_centrocosto_fk "+
        							"AND csc.cod_centro_costo_fk=fc.codigo AND csc.cod_sucursal_fk=cs.codigo  AND cs.codigo='"+Suc+"' "+
        							"AND casc.anio = '"+AC+"' "+
        							"AND cdasc.periodo BETWEEN '"+MC+"'  AND '"+MC2+"'  AND cc.codigo IN "+
        							"(SELECT  cdd.cod_cuenta_fk "+
        							"FROM  cont_documentos cd, cont_detalle_documentos cdd, cont_surcursal_centrocosto csc, cont_sucursales cs, fact_centrocosto fc "+
        							"WHERE cd.codigo = cdd.cod_documento_fk  AND csc.cod_centro_costo_fk=fc.codigo "+
        							"AND csc.cod_sucursal_fk=cdd.cod_sucursal_costo_fk "+
        							"AND csc.cod_sucursal_fk=cs.codigo  AND cs.codigo='"+Suc+"' "+
        							"AND cd.anio = '"+AC+"'   AND cd.periodo BETWEEN '"+MC+"'  AND '"+MC2+"') GROUP BY cc.codigo ");
        					System.out.println("SELECT cc.codigo, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior), SUM(cdasc.saldo_nuevo) " +
        							"FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, cont_surcursal_centrocosto csc, fact_centrocosto fc, cont_sucursales cs  " +
        							"WHERE cc.TipoCuenta = 'Auxiliar' AND cc.codigo = casc.cod_cuenta_fk "+
        							"AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk   AND csc.cod_sucursal_fk=casc.cod_sucursal_centrocosto_fk "+
        							"AND csc.cod_centro_costo_fk=fc.codigo AND csc.cod_sucursal_fk=cs.codigo  AND cs.codigo='"+Suc+"' "+
        							"AND casc.anio = '"+AC+"' "+
        							"AND cdasc.periodo BETWEEN '"+MC+"'  AND '"+MC2+"'  AND cc.codigo IN "+
        							"(SELECT  cdd.cod_cuenta_fk "+
        							"FROM  cont_documentos cd, cont_detalle_documentos cdd, cont_surcursal_centrocosto csc, cont_sucursales cs, fact_centrocosto fc "+
        							"WHERE cd.codigo = cdd.cod_documento_fk  AND csc.cod_centro_costo_fk=fc.codigo "+
        							"AND csc.cod_sucursal_fk=cdd.cod_sucursal_costo_fk "+
        							"AND csc.cod_sucursal_fk=cs.codigo  AND cs.codigo='"+Suc+"' "+
        							"AND cd.anio = '"+AC+"'   AND cd.periodo BETWEEN '"+MC+"'  AND '"+MC2+"') GROUP BY cc.codigo ");
        					//aqui va la consulta q buscar por la sucursal nada mas
        				}else{
        					
        					rs=st.executeQuery("SELECT cc.codigo, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior), SUM(cdasc.saldo_nuevo) " +
        							"FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, cont_surcursal_centrocosto csc, fact_centrocosto fc, cont_sucursales cs  " +
        							"WHERE cc.TipoCuenta = 'Auxiliar' AND cc.codigo = casc.cod_cuenta_fk "+
        							"AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk   AND csc.cod_sucursal_fk=casc.cod_sucursal_centrocosto_fk "+
        							"AND csc.cod_centro_costo_fk=fc.codigo AND csc.cod_sucursal_fk=cs.codigo  AND cs.codigo='"+Suc+"' AND fc.codigo='"+ccosto+"' "+
        							"AND casc.anio = '"+AC+"' "+
        							"AND cdasc.periodo BETWEEN '"+MC+"'  AND '"+MC2+"'  AND cc.codigo IN "+
        							"(SELECT  cdd.cod_cuenta_fk "+
        							"FROM  cont_documentos cd, cont_detalle_documentos cdd, cont_surcursal_centrocosto csc, cont_sucursales cs, fact_centrocosto fc "+
        							"WHERE cd.codigo = cdd.cod_documento_fk  AND csc.cod_centro_costo_fk=fc.codigo "+
        							"AND csc.cod_sucursal_fk=cdd.cod_sucursal_costo_fk "+
        							"AND csc.cod_sucursal_fk=cs.codigo  AND cs.codigo='"+Suc+"' and fc.codigo='"+ccosto+"' "+
        							"AND cd.anio = '"+AC+"'   AND cd.periodo BETWEEN '"+MC+"'  AND '"+MC2+"') GROUP BY cc.codigo ");
        					
        				}
        			}
        			
        		}else{
        			
        			if((Suc.equals(""))&&(ccosto.equals(""))){
        				//aqui no va nada solo sirve esto para hacer la validacion 
        			}else{
        				if(ccosto.equals("")){
        					
        					rs=st.executeQuery("SELECT cc.codigo, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior), SUM(cdasc.saldo_nuevo) " +
        							"FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, cont_surcursal_centrocosto csc, fact_centrocosto fc, cont_sucursales cs  " +
        							"WHERE cc.TipoCuenta = 'Auxiliar' AND cc.codigo = casc.cod_cuenta_fk "+
        							"AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk   AND csc.cod_sucursal_fk=casc.cod_sucursal_centrocosto_fk "+
        							"AND csc.cod_centro_costo_fk=fc.codigo AND csc.cod_sucursal_fk=cs.codigo  AND cs.codigo='"+Suc+"' "+
        							"AND casc.anio = '"+AC+"' "+
        							"AND cdasc.periodo BETWEEN '"+MC+"'  AND '"+MC2+"'  AND cc.codigo IN "+
        							"(SELECT  cdd.cod_cuenta_fk "+
        							"FROM  cont_documentos cd, cont_detalle_documentos cdd, cont_surcursal_centrocosto csc, cont_sucursales cs, fact_centrocosto fc "+
        							"WHERE cd.codigo = cdd.cod_documento_fk  AND csc.cod_centro_costo_fk=fc.codigo "+
        							"AND csc.cod_sucursal_fk=cdd.cod_sucursal_costo_fk "+
        							"AND csc.cod_sucursal_fk=cs.codigo  AND cs.codigo='"+Suc+"' "+
        							"AND cd.anio = '"+AC+"'  cd.tipo_documento_fk='"+TDOC+"' AND cd.periodo BETWEEN '"+MC+"'  AND '"+MC2+"') GROUP BY cc.codigo ");
        					System.out.println("SELECT cc.codigo, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior), SUM(cdasc.saldo_nuevo) " +
        							"FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, cont_surcursal_centrocosto csc, fact_centrocosto fc, cont_sucursales cs  " +
        							"WHERE cc.TipoCuenta = 'Auxiliar' AND cc.codigo = casc.cod_cuenta_fk "+
        							"AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk   AND csc.cod_sucursal_fk=casc.cod_sucursal_centrocosto_fk "+
        							"AND csc.cod_centro_costo_fk=fc.codigo AND csc.cod_sucursal_fk=cs.codigo  AND cs.codigo='"+Suc+"' "+
        							"AND casc.anio = '"+AC+"' "+
        							"AND cdasc.periodo BETWEEN '"+MC+"'  AND '"+MC2+"'  AND cc.codigo IN "+
        							"(SELECT  cdd.cod_cuenta_fk "+
        							"FROM  cont_documentos cd, cont_detalle_documentos cdd, cont_surcursal_centrocosto csc, cont_sucursales cs, fact_centrocosto fc "+
        							"WHERE cd.codigo = cdd.cod_documento_fk  AND csc.cod_centro_costo_fk=fc.codigo "+
        							"AND csc.cod_sucursal_fk=cdd.cod_sucursal_costo_fk "+
        							"AND csc.cod_sucursal_fk=cs.codigo  AND cs.codigo='"+Suc+"' "+
        							"AND cd.anio = '"+AC+"'  cd.tipo_documento_fk='"+TDOC+"' AND cd.periodo BETWEEN '"+MC+"'  AND '"+MC2+"') GROUP BY cc.codigo ");
        					//aqui va la consulta q buscar por la sucursal nada mas
        				}else{
        					
        					rs=st.executeQuery("SELECT cc.codigo, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior), SUM(cdasc.saldo_nuevo) " +
        							"FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, cont_surcursal_centrocosto csc, fact_centrocosto fc, cont_sucursales cs  " +
        							"WHERE cc.TipoCuenta = 'Auxiliar' AND cc.codigo = casc.cod_cuenta_fk "+
        							"AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk   AND csc.cod_sucursal_fk=casc.cod_sucursal_centrocosto_fk "+
        							"AND csc.cod_centro_costo_fk=fc.codigo AND csc.cod_sucursal_fk=cs.codigo  AND cs.codigo='"+Suc+"' "+
        							"AND casc.anio = '"+AC+"' "+
        							"AND cdasc.periodo BETWEEN '"+MC+"'  AND '"+MC2+"'  AND cc.codigo IN "+
        							"(SELECT  cdd.cod_cuenta_fk "+
        							"FROM  cont_documentos cd, cont_detalle_documentos cdd, cont_surcursal_centrocosto csc, cont_sucursales cs, fact_centrocosto fc "+
        							"WHERE cd.codigo = cdd.cod_documento_fk  AND csc.cod_centro_costo_fk=fc.codigo "+
        							"AND csc.cod_sucursal_fk=cdd.cod_sucursal_costo_fk "+
        							"AND csc.cod_sucursal_fk=cs.codigo  AND cs.codigo='"+Suc+"'  and fc.codigo='"+ccosto+"' "+
        							"AND cd.anio = '"+AC+"'  cd.tipo_documento_fk='"+TDOC+"' AND cd.periodo BETWEEN '"+MC+"'  AND '"+MC2+"') GROUP BY cc.codigo ");
        					
        					//aqui va la consulta para por sucursal y por centro de costo 
        				}
        			}
        			
        		}
        		
        	}else{
        		if(TDOC.equals("todas")){
        			
        			if((Suc.equals(""))&&(ccosto.equals(""))){
        				//aqui no va nada solo sirve esto para hacer la validacion 
        			}else{
        				if(ccosto.equals("")){
        					
        					rs=st.executeQuery("SELECT cc.codigo, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior), SUM(cdasc.saldo_nuevo) " +
        							"FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, cont_surcursal_centrocosto csc, fact_centrocosto fc, cont_sucursales cs  " +
        							"WHERE cc.TipoCuenta = 'Auxiliar' AND cc.codigo = casc.cod_cuenta_fk "+
        							"AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk   AND csc.cod_sucursal_fk=casc.cod_sucursal_centrocosto_fk "+
        							"AND csc.cod_centro_costo_fk=fc.codigo AND csc.cod_sucursal_fk=cs.codigo  AND cs.codigo='"+Suc+"' "+
        							"AND casc.anio = '"+AC+"' AND cc.CodigoCuenta BETWEEN '"+RC1+"'  AND  '"+RC2+"' "+
        							"AND cdasc.periodo BETWEEN '"+MC+"'  AND '"+MC2+"'  AND cc.codigo IN "+
        							"(SELECT  cdd.cod_cuenta_fk "+
        							"FROM  cont_documentos cd, cont_detalle_documentos cdd, cont_surcursal_centrocosto csc, cont_sucursales cs, fact_centrocosto fc "+
        							"WHERE cd.codigo = cdd.cod_documento_fk  AND csc.cod_centro_costo_fk=fc.codigo "+
        							"AND csc.cod_sucursal_fk=cdd.cod_sucursal_costo_fk "+
        							"AND csc.cod_sucursal_fk=cs.codigo  AND cs.codigo='"+Suc+"' "+
        							"AND cd.anio = '"+AC+"'   AND cd.periodo BETWEEN '"+MC+"'  AND '"+MC2+"') GROUP BY cc.codigo ");

        					
        					//aqui va la consulta q buscar por la sucursal nada mas
        				}else{
        					
        					rs=st.executeQuery("SELECT cc.codigo, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior), SUM(cdasc.saldo_nuevo) " +
        							"FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, cont_surcursal_centrocosto csc, fact_centrocosto fc, cont_sucursales cs  " +
        							"WHERE cc.TipoCuenta = 'Auxiliar' AND cc.codigo = casc.cod_cuenta_fk "+
        							"AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk   AND csc.cod_sucursal_fk=casc.cod_sucursal_centrocosto_fk "+
        							"AND csc.cod_centro_costo_fk=fc.codigo AND csc.cod_sucursal_fk=cs.codigo  AND cs.codigo='"+Suc+"' "+
        							"AND casc.anio = '"+AC+"' AND cc.CodigoCuenta BETWEEN '"+RC1+"'  AND  '"+RC2+"' "+
        							"AND cdasc.periodo BETWEEN '"+MC+"'  AND '"+MC2+"'  AND cc.codigo IN "+
        							"(SELECT  cdd.cod_cuenta_fk "+
        							"FROM  cont_documentos cd, cont_detalle_documentos cdd, cont_surcursal_centrocosto csc, cont_sucursales cs, fact_centrocosto fc "+
        							"WHERE cd.codigo = cdd.cod_documento_fk  AND csc.cod_centro_costo_fk=fc.codigo "+
        							"AND csc.cod_sucursal_fk=cdd.cod_sucursal_costo_fk "+
        							"AND csc.cod_sucursal_fk=cs.codigo  AND cs.codigo='"+Suc+"' and fc.codigo='"+ccosto+"' "+
        							"AND cd.anio = '"+AC+"'   AND cd.periodo BETWEEN '"+MC+"'  AND '"+MC2+"') GROUP BY cc.codigo ");
        					//aqui va la consulta para por sucursal y por centro de costo 
        				}
        			}
		        	
        		}else{
        			
        			if((Suc.equals(""))&&(ccosto.equals(""))){
        				//aqui no va nada solo sirve esto para hacer la validacion 
        			}else{
        				if(ccosto.equals("")){
        					
        					rs=st.executeQuery("SELECT cc.codigo, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior), SUM(cdasc.saldo_nuevo) " +
        							"FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, cont_surcursal_centrocosto csc, fact_centrocosto fc, cont_sucursales cs  " +
        							"WHERE cc.TipoCuenta = 'Auxiliar' AND cc.codigo = casc.cod_cuenta_fk "+
        							"AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk   AND csc.cod_sucursal_fk=casc.cod_sucursal_centrocosto_fk "+
        							"AND csc.cod_centro_costo_fk=fc.codigo AND csc.cod_sucursal_fk=cs.codigo  AND cs.codigo='"+Suc+"' "+
        							"AND casc.anio = '"+AC+"' AND cc.CodigoCuenta BETWEEN '"+RC1+"'  AND  '"+RC2+"' "+
        							"AND cdasc.periodo BETWEEN '"+MC+"'  AND '"+MC2+"'  AND cc.codigo IN "+
        							"(SELECT  cdd.cod_cuenta_fk "+
        							"FROM  cont_documentos cd, cont_detalle_documentos cdd, cont_surcursal_centrocosto csc, cont_sucursales cs, fact_centrocosto fc "+
        							"WHERE cd.codigo = cdd.cod_documento_fk  AND csc.cod_centro_costo_fk=fc.codigo "+
        							"AND csc.cod_sucursal_fk=cdd.cod_sucursal_costo_fk "+
        							"AND csc.cod_sucursal_fk=cs.codigo  AND cs.codigo='"+Suc+"' "+
        							"AND cd.anio = '"+AC+"'  cd.tipo_documento_fk='"+TDOC+"' AND cd.periodo BETWEEN '"+MC+"'  AND '"+MC2+"') GROUP BY cc.codigo ");
        					//aqui va la consulta q buscar por la sucursal nada mas
        				}else{
        					
        					rs=st.executeQuery("SELECT cc.codigo, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior), SUM(cdasc.saldo_nuevo) " +
        							"FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, cont_surcursal_centrocosto csc, fact_centrocosto fc, cont_sucursales cs  " +
        							"WHERE cc.TipoCuenta = 'Auxiliar' AND cc.codigo = casc.cod_cuenta_fk "+
        							"AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk   AND csc.cod_sucursal_fk=casc.cod_sucursal_centrocosto_fk "+
        							"AND csc.cod_centro_costo_fk=fc.codigo AND csc.cod_sucursal_fk=cs.codigo  AND cs.codigo='"+Suc+"' "+
        							"AND casc.anio = '"+AC+"' AND cc.CodigoCuenta BETWEEN '"+RC1+"'  AND  '"+RC2+"' "+
        							"AND cdasc.periodo BETWEEN '"+MC+"'  AND '"+MC2+"'  AND cc.codigo IN "+
        							"(SELECT  cdd.cod_cuenta_fk "+
        							"FROM  cont_documentos cd, cont_detalle_documentos cdd, cont_surcursal_centrocosto csc, cont_sucursales cs, fact_centrocosto fc "+
        							"WHERE cd.codigo = cdd.cod_documento_fk  AND csc.cod_centro_costo_fk=fc.codigo "+
        							"AND csc.cod_sucursal_fk=cdd.cod_sucursal_costo_fk "+
        							"AND csc.cod_sucursal_fk=cs.codigo  AND cs.codigo='"+Suc+"' and fc.codigo='"+ccosto+"' "+
        							"AND cd.anio = '"+AC+"'  cd.tipo_documento_fk='"+TDOC+"' AND cd.periodo BETWEEN '"+MC+"'  AND '"+MC2+"') GROUP BY cc.codigo ");
        					
        					//aqui va la consulta para por sucursal y por centro de costo 
        				}
        			}
        			
        		}
        	}
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoReportes>>BuscarSaldoAntSucCost "+ex);
        }	
        return rs;
    }
	
	
	
	
	
	public java.sql.ResultSet BuscarCuentaAuxDet(String cuenta,String AC, String MC, String MC2, String TDOC ){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(TDOC.equals("todas")){
        	rs=st.executeQuery("SELECT cd.fecha,cd.tipo_documento_fk, cs.codigo,fcc.codigo, fsc.cod_subcentro_costo,ct.numero_documento,cdd.descripcion,cdd.documento_referencia, cdd.valor_debito, cdd.valor_credito,cc.NaturalezaCuenta "+
        						"FROM cont_documentos cd, cont_detalle_documentos cdd, cont_cuentas cc,cont_surcursal_centrocosto csc, "+
        						"cont_sucursales cs , cont_centro_subcentro ccs, fact_subcentros_costo fsc, fact_centrocosto fcc, cont_terceros ct "+
        						"WHERE cd.codigo=cdd.cod_documento_fk "+
        						"AND cc.codigo=cdd.cod_cuenta_fk "+
        						"AND csc.cod_sucursal_fk = cdd.cod_sucursal_costo_fk AND fcc.codigo = csc.cod_centro_costo_fk "+
        						"AND csc.cod_sucursal_fk=cs.codigo "+
        						"AND ccs.codigo=cdd.cod_centro_subcentro_fk "+
        						"AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo "+
        						"AND fcc.codigo=ccs.cod_centro_costo_fk "+
        						"AND ct.codigo=cdd.cod_terceros_fk "+
        						"AND cc.codigo='"+cuenta+"' "+
        						"AND cd.anio='"+AC+"' "+
        						"AND cd.periodo BETWEEN '"+MC+"' AND '"+MC2+"' ");
        	}else{
        		
        		rs=st.executeQuery("SELECT cd.fecha,cd.tipo_documento_fk, cs.codigo,fcc.codigo, fsc.cod_subcentro_costo,ct.numero_documento,cdd.descripcion,cdd.documento_referencia, cdd.valor_debito, cdd.valor_credito,cc.NaturalezaCuenta "+
						"FROM cont_documentos cd, cont_detalle_documentos cdd, cont_cuentas cc,cont_surcursal_centrocosto csc, "+
						"cont_sucursales cs , cont_centro_subcentro ccs, fact_subcentros_costo fsc, fact_centrocosto fcc, cont_terceros ct "+
						"WHERE cd.codigo=cdd.cod_documento_fk "+
						"AND cc.codigo=cdd.cod_cuenta_fk "+
						"AND csc.cod_sucursal_fk = cdd.cod_sucursal_costo_fk AND fcc.codigo = csc.cod_centro_costo_fk "+
						"AND csc.cod_sucursal_fk=cs.codigo "+
						"AND ccs.codigo=cdd.cod_centro_subcentro_fk "+
						"AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo "+
						"AND fcc.codigo=ccs.cod_centro_costo_fk "+
						"AND ct.codigo=cdd.cod_terceros_fk "+
						"AND cc.codigo='"+cuenta+"' "+
						"AND cd.anio='"+AC+"' AND cd.tipo_documento_fk='"+TDOC+"'"+
						"AND cd.periodo BETWEEN '"+MC+"' AND '"+MC2+"' ");
        	}
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoReportes>>BuscarCuentaAuxDet"+ex);
        }	
        return rs;
    }
	
	
	
	public java.sql.ResultSet BuscarCuentaAuxDetSucyCcosto(String cuenta,String AC, String MC, String MC2, String TDOC, String Suc, String ccosto){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	//System.out.println("BuscarCuentaAuxDetSucyCcosto valor de centro de costo"+ccosto);
        	if(TDOC.equals("todas")){
        		if((Suc.equals(""))&&(ccosto.equals(""))){
        			//no debe entrar a este solo sirve como referencia para hacer la validacion 
        			// la consulta 	q esta en SQL FUNCIONa NO LA MUEVAS !!!
        			
        		}else{
        			if(ccosto.equals("")){
        				//System.out.println("entrando a validacion de cuando ccosto='' y Suc="+Suc);
        				// Aqui va la consulta donde se busca el detalle de la cuenta auxiliar por sucursal 
        				rs=st.executeQuery("SELECT cd.fecha,cd.tipo_documento_fk, cs.codigo,fcc.codigo, fsc.cod_subcentro_costo,ct.numero_documento,cdd.descripcion,cdd.documento_referencia, cdd.valor_debito, cdd.valor_credito,cc.NaturalezaCuenta "+
        							"FROM cont_documentos cd, cont_detalle_documentos cdd, cont_cuentas cc,cont_surcursal_centrocosto csc, "+
        							"cont_sucursales cs , cont_centro_subcentro ccs, fact_subcentros_costo fsc, fact_centrocosto fcc, cont_terceros ct "+
        							"WHERE cd.codigo=cdd.cod_documento_fk AND cc.codigo=cdd.cod_cuenta_fk "+
        							"AND csc.cod_sucursal_fk = cdd.cod_sucursal_costo_fk AND fcc.codigo = csc.cod_centro_costo_fk AND csc.cod_sucursal_fk=cs.codigo "+
        							"AND ccs.codigo=cdd.cod_centro_subcentro_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo "+
        							"AND fcc.codigo=ccs.cod_centro_costo_fk  AND ct.codigo=cdd.cod_terceros_fk AND cc.codigo='"+cuenta+"'  AND cs.codigo='"+Suc+"'  AND cd.anio='"+AC+"' "+
        							"AND cd.periodo BETWEEN '"+MC+"' AND '"+MC2+"' ");
        				
        			/*	System.out.println("SELECT cd.fecha,cd.tipo_documento_fk, cs.codigo,fcc.codigo, fsc.cod_subcentro_costo,ct.numero_documento,cdd.descripcion,cdd.documento_referencia, cdd.valor_debito, cdd.valor_credito,cc.NaturalezaCuenta "+
    							"FROM cont_documentos cd, cont_detalle_documentos cdd, cont_cuentas cc,cont_surcursal_centrocosto csc, "+
    							"cont_sucursales cs , cont_centro_subcentro ccs, fact_subcentros_costo fsc, fact_centrocosto fcc, cont_terceros ct "+
    							"WHERE cd.codigo=cdd.cod_documento_fk AND cc.codigo=cdd.cod_cuenta_fk "+
    							"AND csc.cod_sucursal_fk = cdd.cod_sucursal_costo_fk AND fcc.codigo = csc.cod_centro_costo_fk AND csc.cod_sucursal_fk=cs.codigo "+
    							"AND ccs.codigo=cdd.cod_centro_subcentro_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo "+
    							"AND fcc.codigo=ccs.cod_centro_costo_fk  AND ct.codigo=cdd.cod_terceros_fk AND cc.codigo='"+cuenta+"'  AND cs.codigo='"+Suc+"'  AND cd.anio='"+AC+"' "+
    							"AND cd.periodo BETWEEN '"+MC+"' AND '"+MC2+"' ");*/
        				
        			}else{
        				// aqui vala consulta donde se busca el detalle de la cuenta auxiliar por sucursal y por centro de costo
        				rs=st.executeQuery("SELECT cd.fecha,cd.tipo_documento_fk, cs.codigo,fcc.codigo, fsc.cod_subcentro_costo,ct.numero_documento,cdd.descripcion,cdd.documento_referencia, cdd.valor_debito, cdd.valor_credito,cc.NaturalezaCuenta "+
    							"FROM cont_documentos cd, cont_detalle_documentos cdd, cont_cuentas cc,cont_surcursal_centrocosto csc, "+
    							"cont_sucursales cs , cont_centro_subcentro ccs, fact_subcentros_costo fsc, fact_centrocosto fcc, cont_terceros ct "+
    							"WHERE cd.codigo=cdd.cod_documento_fk AND cc.codigo=cdd.cod_cuenta_fk "+
    							"AND csc.cod_sucursal_fk = cdd.cod_sucursal_costo_fk AND fcc.codigo = csc.cod_centro_costo_fk AND csc.cod_sucursal_fk=cs.codigo "+
    							"AND ccs.codigo=cdd.cod_centro_subcentro_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo "+
    							"AND fcc.codigo=ccs.cod_centro_costo_fk  AND ct.codigo=cdd.cod_terceros_fk AND cc.codigo='"+cuenta+"'  AND cs.codigo='"+Suc+"' AND fcc.codigo='"+ccosto+"'  AND cd.anio='"+AC+"' "+
    							"AND cd.periodo BETWEEN '"+MC+"' AND '"+MC2+"' ");
        			}
        		}
        	
        	}else{
        		
        		if((Suc.equals(""))&&(ccosto.equals(""))){
        			//no debe entrar a este solo sirve como referencia para hacer la validacion 
        		}else{
        			if(ccosto.equals("")){
        				// Aqui va la consulta donde se busca el detalle de la cuenta auxiliar por sucursal 
        				rs=st.executeQuery("SELECT cd.fecha,cd.tipo_documento_fk, cs.codigo,fcc.codigo, fsc.cod_subcentro_costo,ct.numero_documento,cdd.descripcion,cdd.documento_referencia, cdd.valor_debito, cdd.valor_credito,cc.NaturalezaCuenta "+
    							"FROM cont_documentos cd, cont_detalle_documentos cdd, cont_cuentas cc,cont_surcursal_centrocosto csc, "+
    							"cont_sucursales cs , cont_centro_subcentro ccs, fact_subcentros_costo fsc, fact_centrocosto fcc, cont_terceros ct "+
    							"WHERE cd.codigo=cdd.cod_documento_fk AND cc.codigo=cdd.cod_cuenta_fk "+
    							"AND csc.cod_sucursal_fk = cdd.cod_sucursal_costo_fk AND fcc.codigo = csc.cod_centro_costo_fk AND csc.cod_sucursal_fk=cs.codigo "+
    							"AND ccs.codigo=cdd.cod_centro_subcentro_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo "+
    							"AND fcc.codigo=ccs.cod_centro_costo_fk  AND ct.codigo=cdd.cod_terceros_fk AND cc.codigo='"+cuenta+"'  AND cs.codigo='"+Suc+"'  AND cd.anio='"+AC+"' AND cd.tipo_documento_fk='"+TDOC+"'  "+
    							"AND cd.periodo BETWEEN '"+MC+"' AND '"+MC2+"' ");
        				
        			}else{
        				// aqui vala consulta donde se busca el detalle de la cuenta auxiliar por sucursal y por centro de costo 
        				
        				rs=st.executeQuery("SELECT cd.fecha,cd.tipo_documento_fk, cs.codigo,fcc.codigo, fsc.cod_subcentro_costo,ct.numero_documento,cdd.descripcion,cdd.documento_referencia, cdd.valor_debito, cdd.valor_credito,cc.NaturalezaCuenta "+
    							"FROM cont_documentos cd, cont_detalle_documentos cdd, cont_cuentas cc,cont_surcursal_centrocosto csc, "+
    							"cont_sucursales cs , cont_centro_subcentro ccs, fact_subcentros_costo fsc, fact_centrocosto fcc, cont_terceros ct "+
    							"WHERE cd.codigo=cdd.cod_documento_fk AND cc.codigo=cdd.cod_cuenta_fk "+
    							"AND csc.cod_sucursal_fk = cdd.cod_sucursal_costo_fk AND fcc.codigo = csc.cod_centro_costo_fk AND csc.cod_sucursal_fk=cs.codigo "+
    							"AND ccs.codigo=cdd.cod_centro_subcentro_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo "+
    							"AND fcc.codigo=ccs.cod_centro_costo_fk  AND ct.codigo=cdd.cod_terceros_fk AND cc.codigo='"+cuenta+"'  AND cs.codigo='"+Suc+"'  AND cd.anio='"+AC+"'  AND fcc.codigo='"+ccosto+"' AND cd.tipo_documento_fk='"+TDOC+"'  "+
    							"AND cd.periodo BETWEEN '"+MC+"' AND '"+MC2+"' ");
        			}
        		}
        		
        	}
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoReportes>> BuscarCuentaAuxDetSucyCcosto"+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet BuscarCuentaAuxDetTer(String cuenta,String AC, String MC, String MC2, String TDOC, String TERC ){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(TDOC.equals("todas")){
        		
        			rs=st.executeQuery("SELECT cd.fecha,cd.tipo_documento_fk, cs.codigo,fcc.codigo, fsc.cod_subcentro_costo,ct.numero_documento,cdd.descripcion,cdd.documento_referencia, cdd.valor_debito, cdd.valor_credito,cc.NaturalezaCuenta "+
    						"FROM cont_documentos cd, cont_detalle_documentos cdd, cont_cuentas cc,cont_surcursal_centrocosto csc, "+
    						"cont_sucursales cs , cont_centro_subcentro ccs, fact_subcentros_costo fsc, fact_centrocosto fcc, cont_terceros ct "+
    						"WHERE cd.codigo=cdd.cod_documento_fk "+
    						"AND cc.codigo=cdd.cod_cuenta_fk "+
    						"AND csc.cod_sucursal_fk = cdd.cod_sucursal_costo_fk AND fcc.codigo = csc.cod_centro_costo_fk "+
    						"AND csc.cod_sucursal_fk=cs.codigo "+
    						"AND ccs.codigo=cdd.cod_centro_subcentro_fk "+
    						"AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo "+
    						"AND fcc.codigo=ccs.cod_centro_costo_fk "+
    						"AND ct.codigo=cdd.cod_terceros_fk "+
    						"AND cdd.cod_terceros_fk='"+TERC+"' "+
    						"AND cc.codigo='"+cuenta+"' "+
    						"AND cd.anio='"+AC+"' "+
    						"AND cd.periodo BETWEEN '"+MC+"' AND '"+MC2+"' ");
        			
        			
        		
        	}else{
        		
        			rs=st.executeQuery("SELECT cd.fecha,cd.tipo_documento_fk, cs.codigo,fcc.codigo, fsc.cod_subcentro_costo,ct.numero_documento,cdd.descripcion,cdd.documento_referencia, cdd.valor_debito, cdd.valor_credito,cc.NaturalezaCuenta "+
    						"FROM cont_documentos cd, cont_detalle_documentos cdd, cont_cuentas cc,cont_surcursal_centrocosto csc, "+
    						"cont_sucursales cs , cont_centro_subcentro ccs, fact_subcentros_costo fsc, fact_centrocosto fcc, cont_terceros ct "+
    						"WHERE cd.codigo=cdd.cod_documento_fk "+
    						"AND cc.codigo=cdd.cod_cuenta_fk "+
    						"AND csc.cod_sucursal_fk = cdd.cod_sucursal_costo_fk AND fcc.codigo = csc.cod_centro_costo_fk "+
    						"AND csc.cod_sucursal_fk=cs.codigo "+
    						"AND ccs.codigo=cdd.cod_centro_subcentro_fk "+
    						"AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo "+
    						"AND fcc.codigo=ccs.cod_centro_costo_fk "+
    						"AND ct.codigo=cdd.cod_terceros_fk "+
    						"AND cdd.cod_terceros_fk='"+TERC+"' "+
    						"AND cc.codigo='"+cuenta+"' "+
    						"AND cd.anio='"+AC+"' AND cd.tipo_documento_fk='"+TDOC+"'"+
    						"AND cd.periodo BETWEEN '"+MC+"' AND '"+MC2+"' ");
        			
        			
        			
        		
        	}
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoReportes>>BuscarCuentaAuxDet"+ex);
        }	
        return rs;
    }
	
	
	
	public java.sql.ResultSet BuscarTercero(){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT codigo, numero_documento, nombre_razonsoc FROM cont_terceros WHERE estado=0");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoReportes>>BuscarTercero"+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet BuscarSaldos(String RC1,String  RC2,String  MC,String  AC){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if((RC1.equals("TODAS"))&&(RC2.equals("TODAS"))){
        	rs=st.executeQuery("SELECT cc.codigo, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior) , SUM(cdasc.saldo_nuevo), SUM(cdasc.debito), SUM(cdasc.credito) "+
        				"FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc "+
        				"WHERE cc.TipoCuenta='Auxiliar' "+
        				"AND cc.codigo=casc.cod_cuenta_fk "+
        				"AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk "+
        				"AND casc.anio='"+AC+"' AND cdasc.periodo='"+MC+"' GROUP BY cc.codigo ");
        	}else{
        		rs=st.executeQuery("SELECT cc.codigo, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior) , SUM(cdasc.saldo_nuevo), SUM(cdasc.debito), SUM(cdasc.credito) "+
        				"FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc "+
        				"WHERE cc.TipoCuenta='Auxiliar' "+
        				"AND cc.codigo=casc.cod_cuenta_fk "+
        				"AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk "+
        				"AND casc.anio='"+AC+"' AND cdasc.periodo='"+MC+"' and cc.CodigoCuenta BETWEEN '"+RC1+"' AND  '"+RC2+"' GROUP BY cc.codigo ");
        	}
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoReportes>>BuscarSaldos"+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet BuscarSaldosAcum(String cuenta, String  MC, String MC2, String  AC){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	
        	rs=st.executeQuery("SELECT cc.codigo, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.debito), SUM(cdasc.credito), SUM(cdasc.saldo_nuevo) "+
        				"FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc "+
        				"WHERE cc.TipoCuenta='Auxiliar' "+
        				"AND cc.codigo=casc.cod_cuenta_fk "+
        				"AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk "+
        				"AND cc.codigo='"+cuenta+"' "+
        				"AND casc.anio='"+AC+"' AND cdasc.periodo between '"+MC+"' and  '"+MC2+"' GROUP BY cc.codigo ");
        	
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoReportes>>BuscarSaldosAcum"+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarSaldoCuenT(String cuenta,String  AC,String  MC,String TERC, String TDOC, String MC2){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	 if(TERC.equals("TODAS")){
        			if(TDOC.equals("todas")){
        				rs=st.executeQuery("SELECT cc.codigo, cc.CodigoCuenta, cc.NombreCuenta ,ct.codigo, ct.numero_documento, ct.nombre_razonsoc, SUM(cdat.saldo_anterior), SUM(cdat.saldo_nuevo) "+
						"FROM cont_cuentas cc, cont_acumulado_tercero cat, cont_detalle_acumulado_tercero cdat, cont_terceros ct "+
						"WHERE cc.codigo=cat.cod_cuenta_fk "+
						"AND cat.codigo=cdat.cod_acumulado_tercero_fk "+
						"AND ct.codigo=cat.cod_tercero_fk "+
						"AND cat.anio='"+AC+"' "+
						"AND cc.codigo='"+cuenta+"'"+
						"AND cdat.periodo BETWEEN '"+MC+"' and '"+MC2+"'  "+
						"AND cc.codigo IN (SELECT cdd.cod_cuenta_fk FROM cont_documentos cd, cont_detalle_documentos cdd WHERE cd.codigo=cdd.cod_documento_fk AND cd.anio='"+AC+"' AND cd.periodo BETWEEN '"+MC+"' and '"+MC2+"'   ) GROUP BY ct.codigo, cc.codigo ");
        				System.out.println("SELECT cc.codigo, cc.CodigoCuenta, cc.NombreCuenta ,ct.codigo, ct.numero_documento, ct.nombre_razonsoc, SUM(cdat.saldo_anterior), SUM(cdat.saldo_nuevo) "+
        						"FROM cont_cuentas cc, cont_acumulado_tercero cat, cont_detalle_acumulado_tercero cdat, cont_terceros ct "+
        						"WHERE cc.codigo=cat.cod_cuenta_fk "+
        						"AND cat.codigo=cdat.cod_acumulado_tercero_fk "+
        						"AND ct.codigo=cat.cod_tercero_fk "+
        						"AND cat.anio='"+AC+"' "+
        						"AND cdat.periodo BETWEEN '"+MC+"' and '"+MC2+"'  "+
        						"AND cc.codigo IN (SELECT cdd.cod_cuenta_fk FROM cont_documentos cd, cont_detalle_documentos cdd WHERE cd.codigo=cdd.cod_documento_fk AND cd.anio='"+AC+"' AND cd.periodo BETWEEN '"+MC+"' and '"+MC2+"'   ) GROUP BY ct.codigo, cc.codigo ");
        			}else{
        				rs=st.executeQuery("SELECT cc.codigo, cc.CodigoCuenta, cc.NombreCuenta ,ct.codigo, ct.numero_documento, ct.nombre_razonsoc, SUM(cdat.saldo_anterior), SUM(cdat.saldo_nuevo) "+
        						"FROM cont_cuentas cc, cont_acumulado_tercero cat, cont_detalle_acumulado_tercero cdat, cont_terceros ct "+
        						"WHERE cc.codigo=cat.cod_cuenta_fk "+
        						"AND cat.codigo=cdat.cod_acumulado_tercero_fk "+
        						"AND ct.codigo=cat.cod_tercero_fk "+
        						"AND cat.anio='"+AC+"' "+
        						"AND cc.codigo='"+cuenta+"'"+
        						"AND cdat.periodo BETWEEN '"+MC+"' and '"+MC2+"'  "+
        						"AND cc.codigo IN (SELECT cdd.cod_cuenta_fk FROM cont_documentos cd, cont_detalle_documentos cdd WHERE cd.codigo=cdd.cod_documento_fk AND cd.anio='"+AC+"' AND cd.periodo BETWEEN '"+MC+"' and '"+MC2+"'  AND tipo_documento_fk='"+TDOC+"'  ) GROUP BY ct.codigo, cc.codigo ");
        				System.out.println("SELECT cc.codigo, cc.CodigoCuenta, cc.NombreCuenta ,ct.codigo, ct.numero_documento, ct.nombre_razonsoc, SUM(cdat.saldo_anterior), SUM(cdat.saldo_nuevo) "+
        						"FROM cont_cuentas cc, cont_acumulado_tercero cat, cont_detalle_acumulado_tercero cdat, cont_terceros ct "+
        						"WHERE cc.codigo=cat.cod_cuenta_fk "+
        						"AND cat.codigo=cdat.cod_acumulado_tercero_fk "+
        						"AND ct.codigo=cat.cod_tercero_fk "+
        						"AND cat.anio='"+AC+"' "+
        						"AND cdat.periodo BETWEEN '"+MC+"' and '"+MC2+"'  "+
        						"AND cc.codigo IN (SELECT cdd.cod_cuenta_fk FROM cont_documentos cd, cont_detalle_documentos cdd WHERE cd.codigo=cdd.cod_documento_fk AND cd.anio='"+AC+"' AND cd.periodo BETWEEN '"+MC+"' and '"+MC2+"'  AND tipo_documento_fk='"+TDOC+"'  ) GROUP BY ct.codigo, cc.codigo ");
        			}
        			
        		}else{
        			if(TDOC.equals("todas")){
        				rs=st.executeQuery("SELECT cc.codigo, cc.CodigoCuenta, cc.NombreCuenta, ct.codigo, ct.numero_ documento, ct.nombre_razonsoc, SUM(cdat.saldo_anterior), SUM(cdat.saldo_nuevo) "+
        						"FROM cont_cuentas cc, cont_acumulado_tercero cat, cont_detalle_acumulado_tercero cdat, cont_terceros ct "+
        						"WHERE cc.codigo=cat.cod_cuenta_fk "+
        						"AND cat.codigo=cdat.cod_acumulado_tercero_fk "+
        						"AND ct.codigo=cat.cod_tercero_fk "+
        						"AND cat.anio='"+AC+"' "+
        						"AND cdat.periodo BETWEEN '"+MC+"' and '"+MC2+"'  "+
        						"AND cat.cod_tercero_fk='"+TERC+"' "+
        						"AND cc.codigo='"+cuenta+"'"+
        						"AND cc.codigo IN (SELECT cdd.cod_cuenta_fk FROM cont_documentos cd, cont_detalle_documentos cdd WHERE cd.codigo=cdd.cod_documento_fk AND cd.anio='"+AC+"' AND cd.periodo BETWEEN '"+MC+"' and '"+MC2+"' AND cod_terceros_fk='"+TERC+"'  ) GROUP BY ct.codigo, cc.codigo ");
        				System.out.println("SELECT cc.codigo, cc.CodigoCuenta, cc.NombreCuenta, ct.codigo, ct.numero_documento, ct.nombre_razonsoc, SUM(cdat.saldo_anterior), SUM(cdat.saldo_nuevo) "+
        						"FROM cont_cuentas cc, cont_acumulado_tercero cat, cont_detalle_acumulado_tercero cdat, cont_terceros ct "+
        						"WHERE cc.codigo=cat.cod_cuenta_fk "+
        						"AND cat.codigo=cdat.cod_acumulado_tercero_fk "+
        						"AND ct.codigo=cat.cod_tercero_fk "+
        						"AND cat.anio='"+AC+"' "+
        						"AND cdat.periodo BETWEEN '"+MC+"' and '"+MC2+"'  "+
        						"AND cat.cod_tercero_fk='"+TERC+"' "+
        						"AND cc.codigo='"+cuenta+"'"+
        						"AND cc.codigo IN (SELECT cdd.cod_cuenta_fk FROM cont_documentos cd, cont_detalle_documentos cdd WHERE cd.codigo=cdd.cod_documento_fk AND cd.anio='"+AC+"' AND cd.periodo BETWEEN '"+MC+"' and '"+MC2+"' AND cod_terceros_fk='"+TERC+"'  ) GROUP BY ct.codigo, cc.codigo ");
        			}else{
        				rs=st.executeQuery("SELECT cc.codigo, cc.CodigoCuenta, cc.NombreCuenta, ct.codigo, ct.numero_documento, ct.nombre_razonsoc, SUM(cdat.saldo_anterior), SUM(cdat.saldo_nuevo) "+
        						"FROM cont_cuentas cc, cont_acumulado_tercero cat, cont_detalle_acumulado_tercero cdat, cont_terceros ct "+
        						"WHERE cc.codigo=cat.cod_cuenta_fk "+
        						"AND cat.codigo=cdat.cod_acumulado_tercero_fk "+
        						"AND ct.codigo=cat.cod_tercero_fk "+
        						"AND cat.anio='"+AC+"' "+
        						"AND cc.codigo='"+cuenta+"'"+
        						"AND cdat.periodo BETWEEN '"+MC+"' and '"+MC2+"'  "+
        						"AND cat.cod_tercero_fk='"+TERC+"' "+
        						"AND cc.codigo IN (SELECT cdd.cod_cuenta_fk FROM cont_documentos cd, cont_detalle_documentos cdd WHERE cd.codigo=cdd.cod_documento_fk AND cd.anio='"+AC+"' AND cd.periodo BETWEEN '"+MC+"' and '"+MC2+"' AND cod_terceros_fk='"+TERC+"' AND tipo_documento_fk='"+TDOC+"'  ) GROUP BY ct.codigo, cc.codigo ");
        			}
        		}
        	
        	
        		
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoReportes>>BuscarSaldoCuenT"+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet BuscarCuentaTerc(String RC1,String  RC2,String  AC,String  MC,String TERC, String TDOC, String MC2){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if((RC1.equals("TODAS"))&&(RC2.equals("TODAS"))){
        		if(TERC.equals("TODAS")){
        			if(TDOC.equals("todas")){
        				rs=st.executeQuery("SELECT cc.codigo, cc.CodigoCuenta, cc.NombreCuenta ,ct.codigo, ct.numero_documento, ct.nombre_razonsoc, SUM(cdat.saldo_anterior), SUM(cdat.saldo_nuevo) "+
						"FROM cont_cuentas cc, cont_acumulado_tercero cat, cont_detalle_acumulado_tercero cdat, cont_terceros ct "+
						"WHERE cc.codigo=cat.cod_cuenta_fk "+
						"AND cat.codigo=cdat.cod_acumulado_tercero_fk "+
						"AND ct.codigo=cat.cod_tercero_fk "+
						"AND cat.anio='"+AC+"' "+
						"AND cdat.periodo BETWEEN '"+MC+"' and '"+MC2+"'  "+
						"AND cc.codigo IN (SELECT cdd.cod_cuenta_fk FROM cont_documentos cd, cont_detalle_documentos cdd WHERE cd.codigo=cdd.cod_documento_fk AND cd.anio='"+AC+"' AND cd.periodo BETWEEN '"+MC+"' and '"+MC2+"'   ) GROUP BY cc.codigo ");
        			}else{
        				rs=st.executeQuery("SELECT cc.codigo, cc.CodigoCuenta, cc.NombreCuenta ,ct.codigo, ct.numero_documento, ct.nombre_razonsoc, SUM(cdat.saldo_anterior), SUM(cdat.saldo_nuevo) "+
        						"FROM cont_cuentas cc, cont_acumulado_tercero cat, cont_detalle_acumulado_tercero cdat, cont_terceros ct "+
        						"WHERE cc.codigo=cat.cod_cuenta_fk "+
        						"AND cat.codigo=cdat.cod_acumulado_tercero_fk "+
        						"AND ct.codigo=cat.cod_tercero_fk "+
        						"AND cat.anio='"+AC+"' "+
        						"AND cdat.periodo BETWEEN '"+MC+"' and '"+MC2+"'  "+
        						"AND cc.codigo IN (SELECT cdd.cod_cuenta_fk FROM cont_documentos cd, cont_detalle_documentos cdd WHERE cd.codigo=cdd.cod_documento_fk AND cd.anio='"+AC+"' AND cd.periodo BETWEEN '"+MC+"' and '"+MC2+"'  AND tipo_documento_fk='"+TDOC+"'  ) GROUP BY  cc.codigo ");
        				System.out.println("SELECT cc.codigo, cc.CodigoCuenta, cc.NombreCuenta, ct.codigo, ct.numero_documento, ct.nombre_razonsoc, SUM(cdat.saldo_anterior), SUM(cdat.saldo_nuevo) "+
        						"FROM cont_cuentas cc, cont_acumulado_tercero cat, cont_detalle_acumulado_tercero cdat, cont_terceros ct "+
        						"WHERE cc.codigo=cat.cod_cuenta_fk "+
        						"AND cat.codigo=cdat.cod_acumulado_tercero_fk "+
        						"AND ct.codigo=cat.cod_tercero_fk "+
        						"AND cat.anio='"+AC+"' "+
        						"AND cdat.periodo BETWEEN '"+MC+"' and '"+MC2+"'  "+
        						"AND cat.cod_tercero_fk='"+TERC+"' "+
        						"AND cc.codigo IN (SELECT cdd.cod_cuenta_fk FROM cont_documentos cd, cont_detalle_documentos cdd WHERE cd.codigo=cdd.cod_documento_fk AND cd.anio='"+AC+"' AND cd.periodo BETWEEN '"+MC+"' and '"+MC2+"' AND cod_terceros_fk='"+TERC+"' AND tipo_documento_fk='"+TDOC+"'  ) GROUP BY ct.codigo, cc.codigo ");
        			}
        			
        		}else{
        			if(TDOC.equals("todas")){
        				rs=st.executeQuery("SELECT cc.codigo, cc.CodigoCuenta, cc.NombreCuenta, ct.codigo, ct.numero_documento, ct.nombre_razonsoc, SUM(cdat.saldo_anterior), SUM(cdat.saldo_nuevo) "+
        						"FROM cont_cuentas cc, cont_acumulado_tercero cat, cont_detalle_acumulado_tercero cdat, cont_terceros ct "+
        						"WHERE cc.codigo=cat.cod_cuenta_fk "+
        						"AND cat.codigo=cdat.cod_acumulado_tercero_fk "+
        						"AND ct.codigo=cat.cod_tercero_fk "+
        						"AND cat.anio='"+AC+"' "+
        						"AND cdat.periodo BETWEEN '"+MC+"' and '"+MC2+"'  "+
        						"AND cat.cod_tercero_fk='"+TERC+"' "+
        						"AND cc.codigo IN (SELECT cdd.cod_cuenta_fk FROM cont_documentos cd, cont_detalle_documentos cdd WHERE cd.codigo=cdd.cod_documento_fk AND cd.anio='"+AC+"' AND cd.periodo BETWEEN '"+MC+"' and '"+MC2+"' AND cod_terceros_fk='"+TERC+"'  ) GROUP BY cc.codigo ");
        			}else{
        				rs=st.executeQuery("SELECT cc.codigo, cc.CodigoCuenta, cc.NombreCuenta, ct.codigo, ct.numero_documento, ct.nombre_razonsoc, SUM(cdat.saldo_anterior), SUM(cdat.saldo_nuevo) "+
        						"FROM cont_cuentas cc, cont_acumulado_tercero cat, cont_detalle_acumulado_tercero cdat, cont_terceros ct "+
        						"WHERE cc.codigo=cat.cod_cuenta_fk "+
        						"AND cat.codigo=cdat.cod_acumulado_tercero_fk "+
        						"AND ct.codigo=cat.cod_tercero_fk "+
        						"AND cat.anio='"+AC+"' "+
        						"AND cdat.periodo BETWEEN '"+MC+"' and '"+MC2+"'  "+
        						"AND cat.cod_tercero_fk='"+TERC+"' "+
        						"AND cc.codigo IN (SELECT cdd.cod_cuenta_fk FROM cont_documentos cd, cont_detalle_documentos cdd WHERE cd.codigo=cdd.cod_documento_fk AND cd.anio='"+AC+"' AND cd.periodo BETWEEN '"+MC+"' and '"+MC2+"' AND cod_terceros_fk='"+TERC+"' AND tipo_documento_fk='"+TDOC+"'  ) GROUP BY  cc.codigo ");
        			}
        		}
        	
        	}else{
        		System.out.println("entrando");
        		if(TERC.equals("TODAS")){
        			System.out.println("val1");
        			if(TDOC.equals("todas")){
        				System.out.println("val2");
        				rs=st.executeQuery("SELECT cc.codigo, cc.CodigoCuenta, cc.NombreCuenta, ct.codigo, ct.numero_documento, ct.nombre_razonsoc, SUM(cdat.saldo_anterior), SUM(cdat.saldo_nuevo) "+
        						"FROM cont_cuentas cc, cont_acumulado_tercero cat, cont_detalle_acumulado_tercero cdat,cont_terceros ct "+
        						"WHERE cc.codigo=cat.cod_cuenta_fk "+
        						"AND cat.codigo=cdat.cod_acumulado_tercero_fk "+
        						"AND ct.codigo=cat.cod_tercero_fk "+
        						"AND cat.anio='"+AC+"' "+
        						"AND cdat.periodo BETWEEN '"+MC+"' and '"+MC2+"'  "+
        						"AND cc.CodigoCuenta  BETWEEN '"+RC1+"'  AND '"+RC2+"' "+
        						"AND cc.codigo IN (SELECT cdd.cod_cuenta_fk FROM cont_documentos cd, cont_detalle_documentos cdd WHERE cd.codigo=cdd.cod_documento_fk AND cd.anio='"+AC+"' AND cd.periodo BETWEEN '"+MC+"' and '"+MC2+"'  ) GROUP BY  cc.codigo ");
        				
        				
        				System.out.println("SELECT cc.codigo, cc.CodigoCuenta, cc.NombreCuenta, ct.codigo, ct.numero_documento, ct.nombre_razonsoc, SUM(cdat.saldo_anterior), SUM(cdat.saldo_nuevo) "+
        						"FROM cont_cuentas cc, cont_acumulado_tercero cat, cont_detalle_acumulado_tercero cdat,cont_terceros ct "+
        						"WHERE cc.codigo=cat.cod_cuenta_fk "+
        						"AND cat.codigo=cdat.cod_acumulado_tercero_fk "+
        						"AND ct.codigo=cat.cod_tercero_fk "+
        						"AND cat.anio='"+AC+"' "+
        						"AND cdat.periodo BETWEEN '"+MC+"' and '"+MC2+"'  "+
        						"AND cc.CodigoCuenta  BETWEEN '"+RC1+"'  AND '"+RC2+"' "+
        						"AND cc.codigo IN (SELECT cdd.cod_cuenta_fk FROM cont_documentos cd, cont_detalle_documentos cdd WHERE cd.codigo=cdd.cod_documento_fk AND cd.anio='"+AC+"' AND cd.periodo BETWEEN '"+MC+"' and '"+MC2+"'  ) GROUP BY  cc.codigo ");

        				
        				
        				}else{
        				rs=st.executeQuery("SELECT cc.codigo, cc.CodigoCuenta, cc.NombreCuenta, ct.codigo, ct.numero_documento, ct.nombre_razonsoc, SUM(cdat.saldo_anterior), SUM(cdat.saldo_nuevo) "+
        						"FROM cont_cuentas cc, cont_acumulado_tercero cat, cont_detalle_acumulado_tercero cdat,cont_terceros ct "+
        						"WHERE cc.codigo=cat.cod_cuenta_fk "+
        						"AND cat.codigo=cdat.cod_acumulado_tercero_fk "+
        						"AND cat.anio='"+AC+"' "+
        						"AND cdat.periodo BETWEEN '"+MC+"' and '"+MC2+"'  "+
        						"AND cc.CodigoCuenta  BETWEEN '"+RC1+"'  AND '"+RC2+"' "+
        						"AND cc.codigo IN (SELECT cdd.cod_cuenta_fk FROM cont_documentos cd, cont_detalle_documentos cdd WHERE cd.codigo=cdd.cod_documento_fk AND cd.anio='"+AC+"' AND cd.periodo BETWEEN '"+MC+"' and '"+MC2+"'  AND tipo_documento_fk='"+TDOC+"'  ) GROUP BY cc.codigo ");
        				}
        			
        		}else{
        			if(TDOC.equals("todas")){
        				rs=st.executeQuery("SELECT cc.codigo, cc.CodigoCuenta, cc.NombreCuenta , ct.codigo, ct.numero_documento, ct.nombre_razonsoc, SUM(cdat.saldo_anterior), SUM(cdat.saldo_nuevo)"+
        						"FROM cont_cuentas cc, cont_acumulado_tercero cat, cont_detalle_acumulado_tercero cdat,cont_terceros ct "+
        						"WHERE cc.codigo=cat.cod_cuenta_fk "+
        						"AND cat.codigo=cdat.cod_acumulado_tercero_fk "+
        						"AND ct.codigo=cat.cod_tercero_fk "+
        						"AND cat.anio='"+AC+"' "+
        						"AND cdat.periodo BETWEEN '"+MC+"' and '"+MC2+"'  "+
        						"AND cat.cod_tercero_fk='"+TERC+"' "+
        						"AND cc.CodigoCuenta  BETWEEN '"+RC1+"'  AND '"+RC2+"' "+
        						"AND cc.codigo IN (SELECT cdd.cod_cuenta_fk FROM cont_documentos cd, cont_detalle_documentos cdd WHERE cd.codigo=cdd.cod_documento_fk AND cd.anio='"+AC+"' AND cd.periodo BETWEEN '"+MC+"' and '"+MC2+"' AND cod_terceros_fk='"+TERC+"'  ) cc.codigo ");
        			}else{
        				rs=st.executeQuery("SELECT cc.codigo, cc.CodigoCuenta, cc.NombreCuenta ,ct.codigo, ct.numero_documento, ct.nombre_razonsoc, SUM(cdat.saldo_anterior), SUM(cdat.saldo_nuevo) "+
        						"FROM cont_cuentas cc, cont_acumulado_tercero cat, cont_detalle_acumulado_tercero cdat,cont_terceros ct "+
        						"WHERE cc.codigo=cat.cod_cuenta_fk "+
        						"AND cat.codigo=cdat.cod_acumulado_tercero_fk "+
        						"AND ct.codigo=cat.cod_tercero_fk "+
        						"AND cat.anio='"+AC+"' "+
        						"AND cdat.periodo BETWEEN '"+MC+"' and '"+MC2+"'  "+
        						"AND cat.cod_tercero_fk='"+TERC+"' "+
        						"AND cc.CodigoCuenta  BETWEEN '"+RC1+"'  AND '"+RC2+"' "+
        						"AND cc.codigo IN (SELECT cdd.cod_cuenta_fk FROM cont_documentos cd, cont_detalle_documentos cdd WHERE cd.codigo=cdd.cod_documento_fk AND cd.anio='"+AC+"' AND cd.periodo BETWEEN '"+MC+"' and '"+MC2+"' AND cod_terceros_fk='"+TERC+"' AND tipo_documento_fk='"+TDOC+"'  ) GROUP BY  cc.codigo ");
        			}
        		}
        		
        	}
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoReportes>>BuscarCuentaTerc"+ex);
        }	
        return rs;
    }
	
	
	
	
	public java.sql.ResultSet BuscarCuentaTercero(String cuenta,String  MC,String  AC, String TERC){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	//System.out.println("valor de TERC Al llegar al BuscarCuentaTerc"+TERC);
        	if(TERC.equals("TODAS")){
        	   rs=st.executeQuery("SELECT ct.numero_documento, ct.nombre_razonsoc, SUM(cdat.saldo_anterior), SUM(cdat.debito),SUM(cdat.credito), SUM(cdat.saldo_nuevo) "+
        			   	"FROM cont_detalle_acumulado_tercero cdat, cont_acumulado_tercero cat, cont_cuentas cc, cont_terceros ct "+
        			   "WHERE cc.codigo=cat.cod_cuenta_fk "+
        			   "AND cat.codigo=cdat.cod_acumulado_tercero_fk "+
        			   "AND ct.codigo=cat.cod_tercero_fk "+
        			   "AND cat.anio='"+AC+"' "+
        			   "AND cdat.periodo='"+MC+"' "+
        			   "AND cc.codigo='"+cuenta+"' GROUP BY  ct.codigo ");
        	  System.out.println("SELECT ct.numero_documento, ct.nombre_razonsoc, SUM(cdat.saldo_anterior), SUM(cdat.debito),SUM(cdat.credito), SUM(cdat.saldo_nuevo) "+
       			   	"FROM cont_detalle_acumulado_tercero cdat, cont_acumulado_tercero cat, cont_cuentas cc, cont_terceros ct "+
       			   "WHERE cc.codigo=cat.cod_cuenta_fk "+
       			   "AND cat.codigo=cdat.cod_acumulado_tercero_fk "+
       			   "AND ct.codigo=cat.cod_tercero_fk "+
       			   "AND cat.anio='"+AC+"' "+
       			   "AND cdat.periodo='"+MC+"' "+
       			   "AND cc.codigo='"+cuenta+"' GROUP BY  ct.codigo ");
        	}
        	else{
        		 rs=st.executeQuery("SELECT ct.numero_documento, ct.nombre_razonsoc, SUM(cdat.saldo_anterior), SUM(cdat.debito),SUM(cdat.credito), SUM(cdat.saldo_nuevo) "+
         			   	"FROM cont_detalle_acumulado_tercero cdat, cont_acumulado_tercero cat, cont_cuentas cc, cont_terceros ct "+
         			   "WHERE cc.codigo=cat.cod_cuenta_fk "+
         			   "AND cat.codigo=cdat.cod_acumulado_tercero_fk "+
         			   "AND ct.codigo=cat.cod_tercero_fk "+
         			   "AND cat.anio='"+AC+"' "+
         			   "AND cdat.periodo='"+MC+"' "+
         			   "AND cc.codigo='"+cuenta+"' AND cat.cod_tercero_fk='"+TERC+"' GROUP BY  ct.codigo ");
        		 System.out.println("SELECT ct.numero_documento, ct.nombre_razonsoc, SUM(cdat.saldo_anterior), SUM(cdat.debito),SUM(cdat.credito), SUM(cdat.saldo_nuevo) "+
          			   	"FROM cont_detalle_acumulado_tercero cdat, cont_acumulado_tercero cat, cont_cuentas cc, cont_terceros ct "+
          			   "WHERE cc.codigo=cat.cod_cuenta_fk "+
          			   "AND cat.codigo=cdat.cod_acumulado_tercero_fk "+
          			   "AND ct.codigo=cat.cod_tercero_fk "+
          			   "AND cat.anio='"+AC+"' "+
          			   "AND cdat.periodo='"+MC+"' "+
          			   "AND cc.codigo='"+cuenta+"' AND cat.cod_tercero_fk='"+TERC+"' GROUP BY  ct.codigo ");
        	}
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoReportes>>BuscarCuentaTercero"+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarNivel(){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT Nivel FROM cont_cuentas group by Nivel");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoReportes>>BuscarNivel "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet BuscarSucursal(){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT codigo,nombre FROM cont_sucursales");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoReportes>>BuscarSucursal "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarCentroCosto(){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT codigo,NombreCentroCosto FROM fact_centrocosto ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoReportes>>BuscarCentroCosto "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarNombreCentroCosto(String ccosto){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(ccosto.equals("todas")){
        		rs=st.executeQuery("SELECT codigo,NombreCentroCosto FROM fact_centrocosto ");
        	}else{
        		rs=st.executeQuery("SELECT codigo,NombreCentroCosto FROM fact_centrocosto where codigo='"+ccosto+"' ");
        	}
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoReportes>>BuscarNombreCentroCosto "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet BuscarNombreSuc(String Suc){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(Suc.equals("todas")){
        		rs=st.executeQuery("SELECT codigo,nombre FROM cont_sucursales ");
        	}else{
        		rs=st.executeQuery("SELECT codigo,nombre FROM cont_sucursales where codigo='"+Suc+"' ");
        	}
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoReportes>>BuscarNombreSuc "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarDatosBalanceG1(String RC1, String RC2, String AC, String MC, String nivel,String Suc, String ccosto){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if((RC1.equals("TODAS"))&&(RC2.equals("TODAS"))){
        		if((Suc.equals("nada"))&&(ccosto.equals("nada"))){
			        	rs=st.executeQuery("SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo), cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc "+
			        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"' GROUP BY cuenta  ORDER BY cuenta ASC ");
			        	
			        	System.out.println("SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo), cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc "+
								"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"' GROUP BY cuenta  ORDER BY cuenta ASC ");
        		}else{
        			if((Suc.equals("todas"))&&(ccosto.equals("nada"))){
        			
        						///no debe entrar 	aqui solo sirve para efectos de validacion
        				
        			}else{
        				if(ccosto.equals("nada")){
        			
        				rs=st.executeQuery("SELECT "+
    							"LEFT(cc.CodigoCuenta,LOCATE('', cc.CodigoCuenta,'"+nivel+"')) AS cuenta, cc.CodigoCuenta,cc.NombreCuenta, "+
    							"SUM(cdasc.saldo_anterior), SUM(cdasc.debito), SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('', cc.CodigoCuenta, 1)) AS cuenta1, cs.nombre "+
    							"FROM "+
    							"cont_cuentas cc,cont_acumulado_sucursal_centrocosto casc,cont_detalle_acumulado_surcursal_ccosto cdasc,fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs   "+				
    							"WHERE cc.codigo = casc.cod_cuenta_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk  "+
    							"AND cdasc.periodo = '"+MC+"'  AND casc.anio = '"+AC+"' "+
    							"AND cs.codigo = csc.cod_sucursal_fk  AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  AND casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk  AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo  and cs.codigo='"+Suc+"' "+
    							"GROUP BY cuenta "+
    							"ORDER BY cuenta ASC ");
        				
        				
        				}else{
        					if((ccosto.equals("todas"))&&(Suc.equals("nada"))){
        						System.out.println("No debe entrar");
        					}else{
        						if(Suc.equals("nada")){
        							
        							rs=st.executeQuery("SELECT "+
        	    							"LEFT(cc.CodigoCuenta,LOCATE('', cc.CodigoCuenta,'"+nivel+"')) AS cuenta, cc.CodigoCuenta,cc.NombreCuenta, "+
        	    							"SUM(cdasc.saldo_anterior), SUM(cdasc.debito), SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('', cc.CodigoCuenta, 1)) AS cuenta1, cs.nombre "+
        	    							"FROM "+
        	    							"cont_cuentas cc,cont_acumulado_sucursal_centrocosto casc,cont_detalle_acumulado_surcursal_ccosto cdasc,fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs   "+				
        	    							"WHERE cc.codigo = casc.cod_cuenta_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk  "+
        	    							"AND cdasc.periodo = '"+MC+"'  AND casc.anio = '"+AC+"' "+
        	    							"    AND cs.codigo = csc.cod_sucursal_fk  AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  AND casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk  AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo and fc.codigo='"+ccosto+"' "+
        	    							"GROUP BY cuenta "+
        	    							"ORDER BY cuenta ASC ");
        							
        						}else{
        							if((Suc.equals("todas"))&&(ccosto.equals("todas"))){
        								System.out.println("No debe entrar");
        							}else{
        								rs=st.executeQuery("SELECT "+
            	    							"LEFT(cc.CodigoCuenta,LOCATE('', cc.CodigoCuenta,'"+nivel+"')) AS cuenta, cc.CodigoCuenta,cc.NombreCuenta, "+
            	    							"SUM(cdasc.saldo_anterior), SUM(cdasc.debito), SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('', cc.CodigoCuenta, 1)) AS cuenta1, cs.nombre "+
            	    							"FROM "+
            	    							"cont_cuentas cc,cont_acumulado_sucursal_centrocosto casc,cont_detalle_acumulado_surcursal_ccosto cdasc,fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs   "+				
            	    							"WHERE cc.codigo = casc.cod_cuenta_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk  "+
            	    							"AND cdasc.periodo = '"+MC+"'  AND casc.anio = '"+AC+"' "+
            	    							"AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  AND casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk  AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo and fc.codigo='"+ccosto+"' AND  cs.codigo='"+Suc+"' "+
            	    							"GROUP BY cuenta "+
            	    							"ORDER BY cuenta ASC ");
        							}
        						}
        					}
        				}
        			}
        		}
        		}else{
        			if((Suc.equals("nada"))&&(ccosto.equals("nada"))){
        				rs=st.executeQuery("SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel+"))as cuenta,cc.CodigoCuenta, cc.NombreCuenta,SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo), cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc "+
        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"' and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' GROUP BY cuenta  ORDER BY cuenta ASC ");
            
                		System.out.println("SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel+"))as cuenta,cc.CodigoCuenta, cc.NombreCuenta,SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo), cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc "+
        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"' and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' GROUP BY cuenta  ORDER BY cuenta ASC ");
        			}else{
        				if((Suc.equals("todas"))&&(ccosto.equals("nada"))){
        					
            				///NO DEBE ENTRAR 
            			
        					
        				}else{
        					if(ccosto.equals("nada")){
        					rs=st.executeQuery("SELECT "+
        							"LEFT(cc.CodigoCuenta,LOCATE('', cc.CodigoCuenta,'"+nivel+"')) AS cuenta, cc.CodigoCuenta,cc.NombreCuenta, "+
        							"SUM(cdasc.saldo_anterior), SUM(cdasc.debito), SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('', cc.CodigoCuenta, 1)) AS cuenta1, cs.nombre "+
        							"FROM "+
        							"cont_cuentas cc,cont_acumulado_sucursal_centrocosto casc,cont_detalle_acumulado_surcursal_ccosto cdasc,fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+				
        							"WHERE cc.codigo = casc.cod_cuenta_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk  "+
        							"AND cdasc.periodo = '"+MC+"'  AND casc.anio = '"+AC+"' "+
        							"AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  AND casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk  AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' AND cs.codigo='"+Suc+"' GROUP BY cuenta "+
        							"ORDER BY cuenta ASC ");
        					
        					}else{
            					if((ccosto.equals("todas"))&&(Suc.equals("nada"))){
            						
            						//NO DEBE ENTRAR 
            					}else{
            						if(Suc.equals("nada")){
            							rs=st.executeQuery("SELECT "+
                    							"LEFT(cc.CodigoCuenta,LOCATE('', cc.CodigoCuenta,'"+nivel+"')) AS cuenta, cc.CodigoCuenta,cc.NombreCuenta, "+
                    							"SUM(cdasc.saldo_anterior), SUM(cdasc.debito), SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('', cc.CodigoCuenta, 1)) AS cuenta1, cs.nombre "+
                    							"FROM "+
                    							"cont_cuentas cc,cont_acumulado_sucursal_centrocosto casc,cont_detalle_acumulado_surcursal_ccosto cdasc,fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+				
                    							"WHERE cc.codigo = casc.cod_cuenta_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk  "+
                    							"AND cdasc.periodo = '"+MC+"'  AND casc.anio = '"+AC+"' "+
                    							"AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  AND casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk  AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' AND fc.codigo='"+ccosto+"' GROUP BY cuenta "+
                    							"ORDER BY cuenta ASC ");
            						}else{
            							if((Suc.equals("todas"))&&(ccosto.equals("todas"))){
            								System.out.println("no debe entrar");
            							}else{
            								rs=st.executeQuery("SELECT "+
                        							"LEFT(cc.CodigoCuenta,LOCATE('', cc.CodigoCuenta,'"+nivel+"')) AS cuenta, cc.CodigoCuenta,cc.NombreCuenta, "+
                        							"SUM(cdasc.saldo_anterior), SUM(cdasc.debito), SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('', cc.CodigoCuenta, 1)) AS cuenta1, cs.nombre "+
                        							"FROM "+
                        							"cont_cuentas cc,cont_acumulado_sucursal_centrocosto casc,cont_detalle_acumulado_surcursal_ccosto cdasc,fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+				
                        							"WHERE cc.codigo = casc.cod_cuenta_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk  "+
                        							"AND cdasc.periodo = '"+MC+"'  AND casc.anio = '"+AC+"' "+
                        							"AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  AND casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk  AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo  and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' AND fc.codigo='"+ccosto+"' AND cs.codigo='"+Suc+"' GROUP BY cuenta "+
                        							"ORDER BY cuenta ASC ");
            							}
            						}
            					}
            				}
        					
        				}
        			}
        		
        	}
        	
        }
        catch(Exception ex){
      
        	System.out.println("Error en MetodoReportes>>BuscarDatosBalanceG1 "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarDatosBalanceG2(String RC1, String RC2, String AC, String MC, String nivel1, String nivel2,String Suc,String ccosto){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if((RC1.equals("TODAS"))&&(RC2.equals("TODAS"))){
        		if((Suc.equals("nada"))&&(ccosto.equals("nada"))){
        				rs=st.executeQuery("SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel1+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc "+
        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"' GROUP BY cuenta "+
        						"UNION "+
        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel2+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc "+
        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  GROUP BY cuenta ORDER BY cuenta ASC ");
        		}else{
        			if((Suc.equals("todas"))&&(ccosto.equals("nada"))){
        				//no debe entrar
        			}else{
        				if(ccosto.equals("nada")){
        				rs=st.executeQuery("SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel1+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs   "+
        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"' AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk  AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo AND cs.codigo='"+Suc+"' GROUP BY cuenta "+
        						"UNION "+
        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel2+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs   "+
        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"' AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk  AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo AND cs.codigo='"+Suc+"' GROUP BY cuenta ORDER BY cuenta ASC ");
        				}else{
        					if((ccosto.equals("todas"))&&(Suc.equals("nada"))){
        						System.out.println("no debe entrar");
        					}else{
        						if(Suc.equals("nada")){
        							rs=st.executeQuery("SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel1+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs   "+
        	        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"' AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk  AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo AND fc.codigo='"+ccosto+"' GROUP BY cuenta "+
        	        						"UNION "+
        	        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel2+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs   "+
        	        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"' AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk  AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo AND fc.codigo='"+ccosto+"' GROUP BY cuenta ORDER BY cuenta ASC ");
        						}else{
        							if((Suc.equals("todas"))&&(ccosto.equals("todas"))){
        								System.out.println("no debe entrar");
        							}else{
        								rs=st.executeQuery("SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel1+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs   "+
            	        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"' AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo AND fc.codigo='"+ccosto+"' AND cs.codigo='"+Suc+"' GROUP BY cuenta "+
            	        						"UNION "+
            	        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel2+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs   "+
            	        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"' AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo AND fc.codigo='"+ccosto+"' AND cs.codigo='"+Suc+"' GROUP BY cuenta ORDER BY cuenta ASC ");
        							}
        						}
        					}
        				}
        			}
        		}
        	}else{
        		if((Suc.equals("nada"))&&(ccosto.equals("nada"))){
        			rs=st.executeQuery("SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel1+"))as cuenta,cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc "+
    						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"' and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' GROUP BY cuenta  "+
    						"UNION "+
    						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel2+"))as cuenta,cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc "+
    						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"' and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' GROUP BY cuenta  ORDER BY cuenta ASC ");
        		}else{
        			if((Suc.equals("todas"))&&(ccosto.equals("nada"))){
        				
        				rs=st.executeQuery("SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel1+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs   "+
        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' GROUP BY cuenta "+
        						"UNION "+
        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel2+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs   "+
        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' GROUP BY cuenta ORDER BY cuenta ASC ");
        			}else{
        				if(ccosto.equals("nada")){
        				rs=st.executeQuery("SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel1+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs   "+
        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' AND cs.codigo='"+Suc+"' GROUP BY cuenta "+
        						"UNION "+
        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel2+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs   "+
        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' AND cs.codigo='"+Suc+"' GROUP BY cuenta ORDER BY cuenta ASC ");
        				}else{
        					if((ccosto.equals("todas"))&&(Suc.equals("nada"))){
        						System.out.println("no debe entrar");
        					}else{
        						if(Suc.equals("nada")){
        							rs=st.executeQuery("SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel1+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs   "+
        	        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' AND fc.codigo='"+ccosto+"' GROUP BY cuenta "+
        	        						"UNION "+
        	        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel2+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs   "+
        	        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' AND fc.codigo='"+ccosto+"' GROUP BY cuenta ORDER BY cuenta ASC ");
        						}else{
        							if((Suc.equals("todas"))&&(ccosto.equals("todas"))){
        								
        								System.out.println("no debe entrar");
        							}else{
        								rs=st.executeQuery("SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel1+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs   "+
        		        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' AND cs.codigo='"+Suc+"' AND fc.codigo='"+ccosto+"' GROUP BY cuenta "+
        		        						"UNION "+
        		        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel2+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs   "+
        		        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' AND cs.codigo='"+Suc+"' AND fc.codigo='"+ccosto+"' GROUP BY cuenta ORDER BY cuenta ASC ");
        							}
        						}
        					}
        				}
        			}
        		}
        		
    
        	}
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoReportes>>BuscarDatosBalanceG2 "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarDatosBalanceG3(String RC1, String RC2, String AC, String MC, String nivel1, String nivel2, String nivel3,String Suc,String ccosto){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if((RC1.equals("TODAS"))&&(RC2.equals("TODAS"))){
        		if((Suc.equals("nada"))&&(ccosto.equals("nada"))){
        			rs=st.executeQuery("SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel1+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc "+
    						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"' GROUP BY cuenta "+
    						"UNION "+
    						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel2+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc "+
    						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"' GROUP BY cuenta "+
    						"UNION "+
    						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel3+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc "+
    						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"' GROUP BY cuenta ORDER BY cuenta ASC");
        		}else{
        			if((Suc.equals("todas"))&&(ccosto.equals("nada"))){
        				
        				rs=st.executeQuery("SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel1+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo GROUP BY cuenta "+
        						"UNION "+
        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel2+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo GROUP BY cuenta "+
        						"UNION "+
        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel3+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo GROUP BY cuenta ORDER BY cuenta ASC");
        				
        			}else{
        				if(ccosto.equals("nada")){
        				rs=st.executeQuery("SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel1+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo AND cs.codigo='"+Suc+"' GROUP BY cuenta "+
        						"UNION "+
        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel2+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo AND cs.codigo='"+Suc+"' GROUP BY cuenta "+
        						"UNION "+
        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel3+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo AND cs.codigo='"+Suc+"' GROUP BY cuenta ORDER BY cuenta ASC");
        				}else{
        					if((ccosto.equals("todas"))&&(Suc.equals("nada"))){
        						System.out.println("no debe entrar ");
        					}else{
        						if(Suc.equals("nada")){
        							rs=st.executeQuery("SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel1+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
        	        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo AND fc.codigo='"+ccosto+"' GROUP BY cuenta "+
        	        						"UNION "+
        	        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel2+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
        	        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo AND fc.codigo='"+ccosto+"' GROUP BY cuenta "+
        	        						"UNION "+
        	        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel3+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
        	        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo AND fc.codigo='"+ccosto+"' GROUP BY cuenta ORDER BY cuenta ASC");
        							
        						}else{
        							if((Suc.equals("todas"))&&(ccosto.equals("todas"))){
        								System.out.println("no debe entrar ");
        							}else{
        								
        								rs=st.executeQuery("SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel1+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
        		        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo AND cs.codigo='"+Suc+"'  AND fc.codigo='"+ccosto+"' GROUP BY cuenta "+
        		        						"UNION "+
        		        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel2+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
        		        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo AND cs.codigo='"+Suc+"'  AND fc.codigo='"+ccosto+"' GROUP BY cuenta "+
        		        						"UNION "+
        		        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel3+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
        		        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo AND cs.codigo='"+Suc+"' AND fc.codigo='"+ccosto+"' GROUP BY cuenta ORDER BY cuenta ASC");
        							}
        						}
        					}
        				}
        			}
        		}
        	
        	
        	}else{
        		if((Suc.equals("nada"))&&(ccosto.equals("nada"))){
        			rs=st.executeQuery("SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel1+"))as cuenta,cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc "+
    						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"' and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' GROUP BY cuenta  "+
    						"UNION "+
    						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel2+"))as cuenta,cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc "+
    						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"' and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' GROUP BY cuenta "+
    						"UNION "+
    						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel3+"))as cuenta,cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc "+
    						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"' and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' GROUP BY cuenta ORDER BY cuenta ASC");
        		}else{
        			if((Suc.equals("todas"))&&(ccosto.equals("nada"))){
        				
        				rs=st.executeQuery("SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel1+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"'  GROUP BY cuenta "+
        						"UNION "+
        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel2+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"'  GROUP BY cuenta "+
        						"UNION "+
        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel3+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' AND  GROUP BY cuenta ORDER BY cuenta ASC");
        			}else{
        				if(ccosto.equals("nada")){
        				rs=st.executeQuery("SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel1+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' AND cs.codigo='"+Suc+"' GROUP BY cuenta "+
        						"UNION "+
        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel2+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' AND cs.codigo='"+Suc+"' GROUP BY cuenta "+
        						"UNION "+
        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel3+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' AND cs.codigo='"+Suc+"' GROUP BY cuenta ORDER BY cuenta ASC");
        			
        				}else{
        					if((ccosto.equals("todas"))&&(Suc.equals("nada"))){
        						System.out.println("no debe entrar");
        					}else{
        						if(Suc.equals("nada")){
        							
        							rs=st.executeQuery("SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel1+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
        	        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' AND fc.codigo='"+ccosto+"' GROUP BY cuenta "+
        	        						"UNION "+
        	        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel2+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
        	        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' AND fc.codigo='"+ccosto+"' GROUP BY cuenta "+
        	        						"UNION "+
        	        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel3+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
        	        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' AND fc.codigo='"+ccosto+"' GROUP BY cuenta ORDER BY cuenta ASC");
        							
        						}else{
        							if((Suc.equals("todas"))&&(ccosto.equals("todas"))){
        								System.out.println("no debe entrar ");
        							}else{
        							
        								rs=st.executeQuery("SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel1+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
        		        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' AND cs.codigo='"+Suc+"' AND fc.codigo='"+ccosto+"' GROUP BY cuenta "+
        		        						"UNION "+
        		        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel2+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
        		        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' AND cs.codigo='"+Suc+"' AND fc.codigo='"+ccosto+"' GROUP BY cuenta "+
        		        						"UNION "+
        		        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel3+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
        		        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' AND cs.codigo='"+Suc+"' AND fc.codigo='"+ccosto+"' GROUP BY cuenta ORDER BY cuenta ASC");
        							}
        						}
        					}
        				}
        			}
        			
        		}
        		
    
        	}
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoReportes>>BuscarDatosBalanceG3 "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet BuscarDatosBalanceG4(String RC1, String RC2, String AC, String MC, String nivel1, String nivel2, String nivel3,String nivel4, String Suc, String ccosto){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if((RC1.equals("TODAS"))&&(RC2.equals("TODAS"))){
        		if((Suc.equals("nada"))&&(ccosto.equals("nada"))){
        			
        			rs=st.executeQuery("SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel1+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc "+
    						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"' GROUP BY cuenta "+
    						"UNION "+
    						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel2+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc "+
    						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"' GROUP BY cuenta "+
    						"UNION "+
    						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel3+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc "+
    						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"' GROUP BY cuenta "+
    						"UNION "+
    						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel4+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc "+
    						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"' GROUP BY cuenta ORDER BY cuenta ASC");
        			
        		}else{
        			if((Suc.equals("todas"))&&(ccosto.equals("nada"))){
        				rs=st.executeQuery("SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel1+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo and cc.CodigoCuenta GROUP BY cuenta "+
        						"UNION "+
        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel2+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo and cc.CodigoCuenta GROUP BY cuenta "+
        						"UNION "+
        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel3+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo and cc.CodigoCuenta GROUP BY cuenta "+
        						"UNION "+
        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel4+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo and cc.CodigoCuenta GROUP BY cuenta ORDER BY cuenta ASC");
        			}else{
        				if(ccosto.equals("nada")){
        				rs=st.executeQuery("SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel1+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo and cc.CodigoCuenta AND cs.codigo='"+Suc+"' GROUP BY cuenta "+
        						"UNION "+
        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel2+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo and cc.CodigoCuenta AND cs.codigo='"+Suc+"' GROUP BY cuenta "+
        						"UNION "+
        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel3+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo and cc.CodigoCuenta AND cs.codigo='"+Suc+"' GROUP BY cuenta "+
        						"UNION "+
        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel4+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs   "+
        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo and cc.CodigoCuenta AND cs.codigo='"+Suc+"' GROUP BY cuenta ORDER BY cuenta ASC");
        			
        				}else{
        					if((ccosto.equals("todas"))&&(Suc.equals("nada"))){
        						System.out.println("no debe entrar ");
        					}else{
        						if(Suc.equals("nada")){
        							rs=st.executeQuery("SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel1+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
        	        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo and cc.CodigoCuenta AND fc.codigo='"+ccosto+"' GROUP BY cuenta "+
        	        						"UNION "+
        	        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel2+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
        	        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo and cc.CodigoCuenta AND fc.codigo='"+ccosto+"' GROUP BY cuenta "+
        	        						"UNION "+
        	        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel3+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
        	        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo and cc.CodigoCuenta AND fc.codigo='"+ccosto+"' GROUP BY cuenta "+
        	        						"UNION "+
        	        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel4+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs   "+
        	        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo and cc.CodigoCuenta AND fc.codigo='"+ccosto+"' GROUP BY cuenta ORDER BY cuenta ASC");
        							
        						}else{
        							if((Suc.equals("todas"))&&(ccosto.equals("todas"))){
        								System.out.println("no debe entrar ");
        							}else{
        							
        								rs=st.executeQuery("SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel1+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
        		        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo and cc.CodigoCuenta AND cs.codigo='"+Suc+"' AND fc.codigo='"+ccosto+"' GROUP BY cuenta "+
        		        						"UNION "+
        		        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel2+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
        		        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo and cc.CodigoCuenta AND cs.codigo='"+Suc+"' AND fc.codigo='"+ccosto+"' GROUP BY cuenta "+
        		        						"UNION "+
        		        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel3+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
        		        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo and cc.CodigoCuenta AND cs.codigo='"+Suc+"' AND fc.codigo='"+ccosto+"' GROUP BY cuenta "+
        		        						"UNION "+
        		        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel4+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs   "+
        		        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo and cc.CodigoCuenta AND cs.codigo='"+Suc+"' AND fc.codigo='"+ccosto+"' GROUP BY cuenta ORDER BY cuenta ASC");
        								
        							}
        						}
        					}
        				}
        			}
        		}
        	
        	
        	}else{
        		if((Suc.equals("nada"))&&(ccosto.equals("nada"))){
        			
        			rs=st.executeQuery("SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel1+"))as cuenta,cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc "+
    						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"' and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' GROUP BY cuenta  "+
    						"UNION "+
    						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel2+"))as cuenta,cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc "+
    						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"' and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' GROUP BY cuenta "+
    						"UNION "+
    						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel3+"))as cuenta,cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc "+
    						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"' and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' GROUP BY cuenta "+
    						"UNION "+
    						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel4+"))as cuenta,cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc "+
    						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"' and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' GROUP BY cuenta ORDER BY cuenta ASC");
        		}else{
        			if((Suc.equals("todas"))&&(ccosto.equals("nada"))){
        				
        				rs=st.executeQuery("SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel1+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs   "+
        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' GROUP BY cuenta "+
        						"UNION "+
        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel2+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs   "+
        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo  and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' GROUP BY cuenta "+
        						"UNION "+
        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel3+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs   "+
        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo  and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' GROUP BY cuenta "+
        						"UNION "+
        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel4+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs   "+
        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo  and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' GROUP BY cuenta ORDER BY cuenta ASC");
        				
        			}else{
        				if(ccosto.equals("nada")){
        				rs=st.executeQuery("SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel1+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs   "+
        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' AND cs.codigo='"+Suc+"' GROUP BY cuenta "+
        						"UNION "+
        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel2+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo  and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' AND cs.codigo='"+Suc+"' GROUP BY cuenta "+
        						"UNION "+
        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel3+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs   "+
        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' AND cs.codigo='"+Suc+"' GROUP BY cuenta "+
        						"UNION "+
        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel4+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs   "+
        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' AND cs.codigo='"+Suc+"' GROUP BY cuenta ORDER BY cuenta ASC");
        				
        				}else{
        					if((ccosto.equals("todas"))&&(Suc.equals("nada"))){
        						System.out.println("no debe entrar");
        					}else{
        						if(Suc.equals("nada")){
        							rs=st.executeQuery("SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel1+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs   "+
        	        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' AND fc.codigo='"+ccosto+"' GROUP BY cuenta "+
        	        						"UNION "+
        	        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel2+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
        	        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo  and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' AND fc.codigo='"+ccosto+"' GROUP BY cuenta "+
        	        						"UNION "+
        	        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel3+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs   "+
        	        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' AND fc.codigo='"+ccosto+"' GROUP BY cuenta "+
        	        						"UNION "+
        	        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel4+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs   "+
        	        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' AND fc.codigo='"+ccosto+"' GROUP BY cuenta ORDER BY cuenta ASC");
        						}else{
        							if((Suc.equals("todas"))&&(ccosto.equals("todas"))){
        								System.out.println("no debe entrar");
        							}else{
        								
        								rs=st.executeQuery("SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel1+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs   "+
        		        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' AND cs.codigo='"+Suc+"' AND fc.codigo='"+ccosto+"' GROUP BY cuenta "+
        		        						"UNION "+
        		        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel2+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
        		        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo  and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' AND cs.codigo='"+Suc+"' AND fc.codigo='"+ccosto+"' GROUP BY cuenta "+
        		        						"UNION "+
        		        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel3+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs   "+
        		        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' AND cs.codigo='"+Suc+"' AND fc.codigo='"+ccosto+"' GROUP BY cuenta "+
        		        						"UNION "+
        		        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel4+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs   "+
        		        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' AND cs.codigo='"+Suc+"' AND fc.codigo='"+ccosto+"' GROUP BY cuenta ORDER BY cuenta ASC");
        								
        							}
        						}
        					}
        				}
        			}
        		}
        		
    
        	}
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoReportes>>BuscarDatosBalanceG4 "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet BuscarDatosBalanceG5(String RC1, String RC2, String AC, String MC, String nivel1, String nivel2, String nivel3,String nivel4, String nivel5,String Suc, String ccosto){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if((RC1.equals("TODAS"))&&(RC2.equals("TODAS"))){
        		if((Suc.equals("nada"))&&(ccosto.equals("nada"))){
        			
        			rs=st.executeQuery("SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel1+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc "+
    						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"' GROUP BY cuenta "+
    						"UNION "+
    						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel2+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc "+
    						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"' GROUP BY cuenta "+
    						"UNION "+
    						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel3+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta,SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc "+
    						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"' GROUP BY cuenta "+
    						"UNION "+
    						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel4+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta,SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc "+
    						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"' GROUP BY cuenta "+
    						"UNION "+
    						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel5+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc "+
    						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"' GROUP BY cuenta ORDER BY cuenta ASC");
        		}else{
        			if((Suc.equals("todas"))&&(ccosto.equals("nada"))){
        				
        				rs=st.executeQuery("SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel1+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"' AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo GROUP BY cuenta "+
        						"UNION "+
        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel2+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"' AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo GROUP BY cuenta "+
        						"UNION "+
        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel3+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta,SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"' AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo  GROUP BY cuenta "+
        						"UNION "+
        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel4+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta,SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"' AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo GROUP BY cuenta "+
        						"UNION "+
        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel5+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"' AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo GROUP BY cuenta ORDER BY cuenta ASC");
        				
        			}else{
        				if(ccosto.equals("nada")){
        				rs=st.executeQuery("SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel1+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo AND cs.codigo='"+Suc+"' GROUP BY cuenta "+
        						"UNION "+
        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel2+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo AND cs.codigo='"+Suc+"' GROUP BY cuenta "+
        						"UNION "+
        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel3+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta,SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo  AND cs.codigo='"+Suc+"' GROUP BY cuenta "+
        						"UNION "+
        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel4+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta,SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo AND cs.codigo='"+Suc+"' GROUP BY cuenta "+
        						"UNION "+
        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel5+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo AND cs.codigo='"+Suc+"' GROUP BY cuenta ORDER BY cuenta ASC");
        				}else{
        					System.out.println("Valor del centro de costo "+ccosto);
        					if((ccosto.equals("todas"))&&(Suc.equals("nada"))){
        						System.out.println("aqui no debe entrar");
        					}else{
        						if(Suc.equals("nada")){
        							rs=st.executeQuery("SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel1+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
        	        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo AND fc.codigo='"+ccosto+"' GROUP BY cuenta "+
        	        						"UNION "+
        	        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel2+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
        	        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo AND fc.codigo='"+ccosto+"' GROUP BY cuenta "+
        	        						"UNION "+
        	        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel3+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta,SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
        	        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo  AND fc.codigo='"+ccosto+"' GROUP BY cuenta "+
        	        						"UNION "+
        	        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel4+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta,SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
        	        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo AND fc.codigo='"+ccosto+"' GROUP BY cuenta "+
        	        						"UNION "+
        	        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel5+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
        	        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo AND fc.codigo='"+ccosto+"' GROUP BY cuenta ORDER BY cuenta ASC");
        						}else{
        							if((Suc.equals("todas"))&&(ccosto.equals("todas"))){
        								
        								System.out.println("no debe entrar ");
        								
        							}else{
        								
        								rs=st.executeQuery("SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel1+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
            	        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo AND fc.codigo='"+ccosto+"' and  cs.codigo='"+Suc+"' GROUP BY cuenta "+
            	        						"UNION "+
            	        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel2+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
            	        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo AND fc.codigo='"+ccosto+"' and cs.codigo='"+Suc+"' GROUP BY cuenta "+
            	        						"UNION "+
            	        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel3+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta,SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
            	        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo  AND fc.codigo='"+ccosto+"' and cs.codigo='"+Suc+"' GROUP BY cuenta "+
            	        						"UNION "+
            	        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel4+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta,SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
            	        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo AND fc.codigo='"+ccosto+"' and cs.codigo='"+Suc+"' GROUP BY cuenta "+
            	        						"UNION "+
            	        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel5+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
            	        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo AND fc.codigo='"+ccosto+"' and cs.codigo='"+Suc+"' GROUP BY cuenta ORDER BY cuenta ASC");
        								
        								
        							}
        						}
        					}
        				}
        				
        				}
        		}
        	
        	
        	}else{
        		
        		if((Suc.equals("nada"))&&(ccosto.equals("nada"))){
        			
        			rs=st.executeQuery("SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel1+"))as cuenta,cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc "+
    						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"' and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' GROUP BY cuenta  "+
    						"UNION "+
    						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel2+"))as cuenta,cc.CodigoCuenta, cc.NombreCuenta,SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc "+
    						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"' and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' GROUP BY cuenta "+
    						"UNION "+
    						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel3+"))as cuenta,cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc "+
    						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"' and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' GROUP BY cuenta "+
    						"UNION "+
    						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel4+"))as cuenta,cc.CodigoCuenta, cc.NombreCuenta,SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc "+
    						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"' and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' GROUP BY cuenta "+
    						"UNION "+
    						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel5+"))as cuenta,cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc "+
    						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"' and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' GROUP BY cuenta ORDER BY cuenta ASC");
        			
        		}else{
        			if((Suc.equals("todas"))&&(ccosto.equals("nada"))){
        				
        				rs=st.executeQuery("SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel1+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"' AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' GROUP BY cuenta "+
        						"UNION "+
        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel2+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"' AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' GROUP BY cuenta "+
        						"UNION "+
        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel3+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta,SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"' AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' GROUP BY cuenta "+
        						"UNION "+
        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel4+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta,SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"' AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' GROUP BY cuenta "+
        						"UNION "+
        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel5+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"' AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' GROUP BY cuenta ORDER BY cuenta ASC");
        				
        			}else{
        				if(ccosto.equals("nada")){
        				rs=st.executeQuery("SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel1+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' AND cs.codigo='"+Suc+"' GROUP BY cuenta "+
        						"UNION "+
        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel2+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' AND cs.codigo='"+Suc+"' GROUP BY cuenta "+
        						"UNION "+
        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel3+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta,SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' AND cs.codigo='"+Suc+"' GROUP BY cuenta "+
        						"UNION "+
        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel4+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta,SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' AND cs.codigo='"+Suc+"' GROUP BY cuenta "+
        						"UNION "+
        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel5+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' AND cs.codigo='"+Suc+"' GROUP BY cuenta ORDER BY cuenta ASC");
        				}else{
        					if((ccosto.equals("todas"))&&(Suc.equals("nada"))){
        						System.out.println("aqui no debe entrar");
        					}else{
        						if(Suc.equals("nada")){
        							
        							rs=st.executeQuery("SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel1+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
        	        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' AND fc.codigo='"+ccosto+"' GROUP BY cuenta "+
        	        						"UNION "+
        	        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel2+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
        	        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' AND fc.codigo='"+ccosto+"' GROUP BY cuenta "+
        	        						"UNION "+
        	        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel3+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta,SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
        	        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' AND fc.codigo='"+ccosto+"' GROUP BY cuenta "+
        	        						"UNION "+
        	        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel4+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta,SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
        	        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' AND fc.codigo='"+ccosto+"' GROUP BY cuenta "+
        	        						"UNION "+
        	        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel5+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
        	        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' AND fc.codigo='"+ccosto+"' GROUP BY cuenta ORDER BY cuenta ASC");
        							
        						}else{
        							if((Suc.equals("todas"))&&(ccosto.equals("todas"))){
        								System.out.println("no debe entrar ");
        							}else{
        								
        								rs=st.executeQuery("SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel1+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
        		        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' AND cs.codigo='"+Suc+"' AND fc.codigo='"+ccosto+"' GROUP BY cuenta "+
        		        						"UNION "+
        		        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel2+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
        		        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' AND cs.codigo='"+Suc+"' AND fc.codigo='"+ccosto+"'  GROUP BY cuenta "+
        		        						"UNION "+
        		        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel3+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta,SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
        		        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' AND cs.codigo='"+Suc+"' AND fc.codigo='"+ccosto+"' GROUP BY cuenta "+
        		        						"UNION "+
        		        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel4+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta,SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
        		        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' AND cs.codigo='"+Suc+"' AND fc.codigo='"+ccosto+"' GROUP BY cuenta "+
        		        						"UNION "+
        		        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel5+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, fact_centrocosto fc, fact_subcentros_costo fsc, cont_centro_subcentro ccs,  cont_surcursal_centrocosto csc, cont_sucursales cs  "+
        		        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'  AND cs.codigo = csc.cod_sucursal_fk  AND casc.codigo = cdasc.cod_acu_sucur_ccosto_fk AND casc.cod_centros_subcentrocosto_fk = ccs.codigo  and casc.cod_sucursal_centrocosto_fk=csc.cod_sucursal_fk AND fc.codigo = ccs.cod_centro_costo_fk AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo AND ccs.cod_centro_costo_fk = fc.codigo AND csc.cod_centro_costo_fk=fc.codigo and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' AND cs.codigo='"+Suc+"' AND fc.codigo='"+ccosto+"' GROUP BY cuenta ORDER BY cuenta ASC");
        								
        							}
        						}
        					}
        				}
        				
        			}
        		}
        		
    
        		
        		
        	}
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoReportes>>BuscarDatosBalanceG5 "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet BuscarCuenta(String CodigoCuenta){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo,CodigoCuenta,NombreCuenta,TipoCuenta,NaturalezaCuenta,Nivel,estado,Ind_ccosto,Ind_tercero,Ind_diferido,Ind_Base,porcentaje from cont_cuentas where CodigoCuenta='"+CodigoCuenta+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoReportes>>BuscarCuenta "+ex);
        }	
        return rs;
    }
}
