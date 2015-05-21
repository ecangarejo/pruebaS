package cont_metodo;

import java.sql.PreparedStatement;
import java.sql.Statement;

import adm_logica.Conexion;
import cont_bean.Cuentas;



public class MetodoProveedor {
	
	
	
	public void CrearAPeriodo(String anio,String periodo,String bloqueo,
			String nombre,String BloqueoCxP){
		
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("INSERT INTO cont_anio_periodo(anio,periodo,bloqueo,nombre,bloqueo_cxp)VALUES(?,?,?,?,?)");
			    ps.setString(1,anio);
			    ps.setString(2,periodo);
			    ps.setString(3,bloqueo);
			    ps.setString(4,nombre);
			    ps.setString(5,BloqueoCxP);

			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoProveedor>>CrearAPeriodo "+ex);
			}

		}
	
	
	
	public void CrearBanco(String NombreBanco,String CodigoCuentaBanco,String codNacional,String descripcion,String tipo,String estado,String observacion ){
		
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("INSERT INTO cont_bancos (nombre,cod_cuenta_fk,sigla,codNacional,descripcion,tipo,estado,observacion) VALUES (?,?,?,?,?,?,?,?)");
			    ps.setString(1,NombreBanco);
			    ps.setString(2,CodigoCuentaBanco);
			    ps.setString(3,NombreBanco);
			    ps.setString(4,codNacional);
			    ps.setString(5,descripcion);
			    ps.setString(6,tipo);
			    ps.setString(7,estado);
			    ps.setString(8,observacion);
			    
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoProveedor>>CrearBanco "+ex);
			}

		}
	
	
	
	public void CrearDiferido(String CodCuentaDife,String CodTercero,String CodCuentaGasto,
			String FechaInicial,String FechaFinal,String Monto,String Descripcion,
			String CuentaDiferido,String CuentaGasto,String Tercero){
		
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("INSERT INTO cont_diferidos (cuenta_diferido_fk,cuenta_gasto_ingreso_fk,tercero_fk,fecha_inicial,fecha_final,descripcion,monto,cuenta_diferido,cuenta_gasto_ingreso,tercero) VALUES(?,?,?,?,?,?,?,?,?,?)");
			    ps.setString(1,CodCuentaDife);
			    ps.setString(2,CodCuentaGasto);
			    ps.setString(3,CodTercero);
			    ps.setString(4,FechaInicial);
			    ps.setString(5,FechaFinal);			   
			    ps.setString(6,Descripcion);
			    ps.setString(7,Monto);
			    ps.setString(8,CuentaDiferido);			   
			    ps.setString(9,CuentaGasto);
			    ps.setString(10,Tercero);
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoProveedor>>CrearDiferido "+ex);
			}

		}
	
	public void CrearSucursal(String NombreSucursal,String DireSucursal,String Telefono1,
			String Telefono2,String Municipio,String Departamento){
		
			System.out.print("NombreSucursal="+NombreSucursal+" DireSucursal="+DireSucursal+" Telefono1="+Telefono1+" Telefono2="+Telefono2+" Municipio="+Municipio+" Departamento="+Departamento);
		
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("INSERT INTO cont_sucursales(nombre,direccion,ciudad,telefono,telefono2,departamento)VALUES(?,?,?,?,?,?)");
			    ps.setString(1,NombreSucursal);
			    ps.setString(2,DireSucursal);
			    ps.setString(3,Municipio);
			    ps.setString(4,Telefono1);
			    ps.setString(5,Telefono2);			   
			    ps.setString(6,Departamento);

			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoProveedor>>CrearSucursal "+ex);
			}

		}


///INICIO CONSULTAS DE REPORTES DE CONTABILIDAD/////

public java.sql.ResultSet BuscarCuentas(){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT CodigoCuenta,NombreCuenta FROM cont_cuentas ORDER BY CodigoCuenta");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProveedor>>BuscarCuentas "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarCuentas2(){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT CodigoCuenta,NombreCuenta FROM cont_cuentas where CodigoCuenta<10 ORDER BY CodigoCuenta");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProveedor>>BuscarCuentas "+ex);
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
        	System.out.println("Error en MetodoProveedor>>BuscarCuentas5 "+ex);
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
        	System.out.println("Error en MetodoProveedor>>BuscarCuentas3 "+ex);
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
        	System.out.println("Error en MetodoProveedor>>BuscarCuentasAux "+ex);
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
        	System.out.println("Error en MetodoProveedor>>BuscarSaldoAnt "+ex);
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
        						"AND csc.codigo=cdd.cod_sucursal_costo_fk "+
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
						"AND csc.codigo=cdd.cod_sucursal_costo_fk "+
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
        	System.out.println("Error en MetodoProveedor>>BuscarCuentaAuxDet"+ex);
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
    						"AND csc.codigo=cdd.cod_sucursal_costo_fk "+
    						"AND csc.cod_sucursal_fk=cs.codigo "+
    						"AND ccs.codigo=cdd.cod_centro_subcentro_fk "+
    						"AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo "+
    						"AND fcc.codigo=ccs.cod_centro_costo_fk "+
    						"AND ct.codigo=cdd.cod_terceros_fk "+
    						"AND cdd.cod_terceros_fk='"+TERC+"' "+
    						"AND cc.codigo='"+cuenta+"' "+
    						"AND cd.anio='"+AC+"' "+
    						"AND cd.periodo BETWEEN '"+MC+"' AND '"+MC2+"' ");
        			
        			System.out.println("SELECT cd.fecha,cd.tipo_documento_fk, cs.codigo,fcc.codigo, fsc.cod_subcentro_costo,ct.numero_documento,cdd.descripcion,cdd.documento_referencia, cdd.valor_debito, cdd.valor_credito,cc.NaturalezaCuenta "+
    						"FROM cont_documentos cd, cont_detalle_documentos cdd, cont_cuentas cc,cont_surcursal_centrocosto csc, "+
    						"cont_sucursales cs , cont_centro_subcentro ccs, fact_subcentros_costo fsc, fact_centrocosto fcc, cont_terceros ct "+
    						"WHERE cd.codigo=cdd.cod_documento_fk "+
    						"AND cc.codigo=cdd.cod_cuenta_fk "+
    						"AND csc.codigo=cdd.cod_sucursal_costo_fk "+
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
    						"AND csc.codigo=cdd.cod_sucursal_costo_fk "+
    						"AND csc.cod_sucursal_fk=cs.codigo "+
    						"AND ccs.codigo=cdd.cod_centro_subcentro_fk "+
    						"AND ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo "+
    						"AND fcc.codigo=ccs.cod_centro_costo_fk "+
    						"AND ct.codigo=cdd.cod_terceros_fk "+
    						"AND cdd.cod_terceros_fk='"+TERC+"' "+
    						"AND cc.codigo='"+cuenta+"' "+
    						"AND cd.anio='"+AC+"' AND cd.tipo_documento_fk='"+TDOC+"'"+
    						"AND cd.periodo BETWEEN '"+MC+"' AND '"+MC2+"' ");
        			
        			System.out.println("SELECT cd.fecha,cd.tipo_documento_fk, cs.codigo,fcc.codigo, fsc.cod_subcentro_costo,ct.numero_documento,cdd.descripcion,cdd.documento_referencia, cdd.valor_debito, cdd.valor_credito,cc.NaturalezaCuenta "+
    						"FROM cont_documentos cd, cont_detalle_documentos cdd, cont_cuentas cc,cont_surcursal_centrocosto csc, "+
    						"cont_sucursales cs , cont_centro_subcentro ccs, fact_subcentros_costo fsc, fact_centrocosto fcc, cont_terceros ct "+
    						"WHERE cd.codigo=cdd.cod_documento_fk "+
    						"AND cc.codigo=cdd.cod_cuenta_fk "+
    						"AND csc.codigo=cdd.cod_sucursal_costo_fk "+
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
        	System.out.println("Error en MetodoProveedor>>BuscarCuentaAuxDet"+ex);
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
        	System.out.println("Error en MetodoProveedor>>BuscarSaldos"+ex);
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
        	System.out.println("Error en MetodoProveedor>>BuscarSaldosAcum"+ex);
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
        	System.out.println("Error en MetodoProveedor>>BuscarSaldoCuenT"+ex);
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
        	System.out.println("Error en MetodoProveedor>>BuscarCuentaTerc"+ex);
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
        	System.out.println("Error en MetodoProveedor>>BuscarCuentaTercero"+ex);
        }	
        return rs;
    }
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
        	System.out.println("Error en MetodoProveedor>>BuscarNivel "+ex);
        }	
        return rs;
    }

	public java.sql.ResultSet BuscarDatosBalanceG2(String RC1, String RC2, String AC, String MC, String nivel1, String nivel2){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if((RC1.equals("TODAS"))&&(RC2.equals("TODAS"))){
        	rs=st.executeQuery("SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel1+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc "+
        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"' GROUP BY cuenta "+
        						"UNION "+
        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel2+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc "+
        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"' GROUP BY cuenta ORDER BY cuenta ASC ");
        	
        	}else{
        		rs=st.executeQuery("SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel1+"))as cuenta,cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc "+
						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' GROUP BY cuenta  "+
						"UNION "+
						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel2+"))as cuenta,cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc "+
						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' GROUP BY cuenta  ORDER BY cuenta ASC ");
    
        	}
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProveedor>>BuscarDatosBalanceG2 "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarDatosBalanceG3(String RC1, String RC2, String AC, String MC, String nivel1, String nivel2, String nivel3){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if((RC1.equals("TODAS"))&&(RC2.equals("TODAS"))){
        	rs=st.executeQuery("SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel1+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc "+
        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"' GROUP BY cuenta "+
        						"UNION "+
        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel2+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc "+
        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"' GROUP BY cuenta "+
        						"UNION "+
        						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel3+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc "+
        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"' GROUP BY cuenta ORDER BY cuenta ASC");
        	
        	}else{
        		rs=st.executeQuery("SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel1+"))as cuenta,cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc "+
						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' GROUP BY cuenta  "+
						"UNION "+
						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel2+"))as cuenta,cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc "+
						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' GROUP BY cuenta "+
						"UNION "+
						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel3+"))as cuenta,cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc "+
						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' GROUP BY cuenta ORDER BY cuenta ASC");
    
        	}
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProveedor>>BuscarDatosBalanceG3 "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet BuscarDatosBalanceG4(String RC1, String RC2, String AC, String MC, String nivel1, String nivel2, String nivel3,String nivel4){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if((RC1.equals("TODAS"))&&(RC2.equals("TODAS"))){
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
        		rs=st.executeQuery("SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel1+"))as cuenta,cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc "+
						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' GROUP BY cuenta  "+
						"UNION "+
						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel2+"))as cuenta,cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc "+
						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' GROUP BY cuenta "+
						"UNION "+
						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel3+"))as cuenta,cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc "+
						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' GROUP BY cuenta "+
						"UNION "+
						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel4+"))as cuenta,cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc "+
						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' GROUP BY cuenta ORDER BY cuenta ASC");
    
        	}
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProveedor>>BuscarDatosBalanceG4 "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet BuscarDatosBalanceG5(String RC1, String RC2, String AC, String MC, String nivel1, String nivel2, String nivel3,String nivel4, String nivel5){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if((RC1.equals("TODAS"))&&(RC2.equals("TODAS"))){
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
        		rs=st.executeQuery("SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel1+"))as cuenta,cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc "+
						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' GROUP BY cuenta  "+
						"UNION "+
						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel2+"))as cuenta,cc.CodigoCuenta, cc.NombreCuenta,SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc "+
						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' GROUP BY cuenta "+
						"UNION "+
						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel3+"))as cuenta,cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc "+
						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' GROUP BY cuenta "+
						"UNION "+
						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel4+"))as cuenta,cc.CodigoCuenta, cc.NombreCuenta,SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc "+
						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' GROUP BY cuenta "+
						"UNION "+
						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel5+"))as cuenta,cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc "+
						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' GROUP BY cuenta ORDER BY cuenta ASC");
    
        		System.out.println("SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel1+"))as cuenta,cc.CodigoCuenta, cc.NombreCuenta,SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc "+
						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' GROUP BY cuenta  "+
						"UNION "+
						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel2+"))as cuenta,cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc "+
						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' GROUP BY cuenta "+
						"UNION "+
						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel3+"))as cuenta,cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc "+
						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' GROUP BY cuenta "+
						"UNION "+
						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel4+"))as cuenta,cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc "+
						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' GROUP BY cuenta "+
						"UNION "+
						"SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel5+"))as cuenta,cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo),cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc "+
						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'and cc.CodigoCuenta between '"+RC1+"' and '"+RC2+"' GROUP BY cuenta ORDER BY cuenta ASC");
        		
        	}
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProveedor>>BuscarDatosBalanceG5 "+ex);
        }	
        return rs;
    }

	
	public java.sql.ResultSet BuscarDatosBalanceG1(String RC1, String RC2, String AC, String MC, String nivel){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if((RC1.equals("TODAS"))&&(RC2.equals("TODAS"))){
        	rs=st.executeQuery("SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo), cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc "+
        						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"' GROUP BY cuenta  ORDER BY cuenta ASC ");
        	System.out.println("entrando a buscardatosbalanceg1");
        	System.out.println("SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo), cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc "+
					"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"' GROUP BY cuenta  ORDER BY cuenta ASC ");
        	}else{
        		
        		rs=st.executeQuery("SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel+"))as cuenta,cc.CodigoCuenta, cc.NombreCuenta,SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo), cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc "+
						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"' and cc. CodigoCuenta between '"+RC1+"' and '"+RC2+"' GROUP BY cuenta  ORDER BY cuenta ASC ");
    
        		System.out.println("SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel+"))as cuenta,cc.CodigoCuenta, cc.NombreCuenta,SUM(cdasc.saldo_anterior),SUM(cdasc.debito),SUM(cdasc.credito),SUM(cdasc.saldo_nuevo), cc.NaturalezaCuenta,LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,1)) AS cuenta1 FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc "+
						"WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"' and cc. CodigoCuenta between '"+RC1+"' and '"+RC2+"' GROUP BY cuenta  ORDER BY cuenta ASC ");
        	}
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProveedor>>BuscarDatosBalanceG1 "+ex);
        }	
        return rs;
    }


////FIN REPORTES DE CONTABILIDAD//////
	
public void CrearSubcentroCosto(String NomSubentroCosto){
		
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("INSERT INTO fact_subcentros_costo(descripcion)VALUES(?)");
			    ps.setString(1,NomSubentroCosto);

			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoProveedor>>CrearSubentroCosto "+ex);
			}

		}
	

	public void RelacionCentroSuncentro(String CodCCosto,String CodSubCCosto){
	
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("INSERT INTO cont_centro_subcentro(cod_centro_costo_fk,cod_sub_centro_costo_fk)VALUES(?,?)");
			    ps.setString(1,CodCCosto);
			    ps.setString(2,CodSubCCosto);
	
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoProveedor>>RelacionCentroSuncentro "+ex);
			}

	}
	
	public void CrearGrupoCuenta(String NombreGruCuenta){
		
		try{
			PreparedStatement ps = null;
			Conexion con=new Conexion();
			ps=con.conn.prepareStatement("INSERT INTO cont_grupo_cuentas(nombre)VALUES(?)");
			ps.setString(1,NombreGruCuenta);

			ps.executeUpdate();
			ps.close();
			con.cerrar();				
		}catch(Exception ex){
			System.out.println("ERROR EN MetodoProveedor>>CrearGrupoCuenta "+ex);
		}

	}
	public void CrearCentroCosto(String NomCentroCosto){
		
		try{
			PreparedStatement ps = null;
			Conexion con=new Conexion();
			ps=con.conn.prepareStatement("INSERT INTO fact_centrocosto(NombreCentroCosto)VALUES(?)");
			ps.setString(1,NomCentroCosto);

			ps.executeUpdate();
			ps.close();
			con.cerrar();				
		}catch(Exception ex){
			System.out.println("ERROR EN MetodoProveedor>>CrearCentroCosto "+ex);
		}

	}
	
	public void RelacionCentroSucursal(String CodCCosto,String CodSucursal){
		
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("INSERT INTO cont_surcursal_centrocosto(cod_centro_costo_fk,cod_sucursal_fk)VALUES(?,?)");
			    ps.setString(1,CodCCosto);
			    ps.setString(2,CodSucursal);

			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoProveedor>>RelacionCentroSucursal "+ex);
			}

		}
	
	
	public void RelacionProveedorCuenta(String CodProveedor,String CodCuenta){
		
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("insert into cont_cuenta_proveedor(cod_prov_fk,cod_cuen_fk)values(?,?)");
			    ps.setString(1,CodProveedor);
			    ps.setString(2,CodCuenta);

			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoProveedor>>RelacionProveedorCuenta "+ex);
			}

		}
	
	public void CrearProveedor(
		String tipo_identificacion,String numero_documento,String nombre_razonsoc,String Nombre1,
		String Nombre2,String Apellido1,String Apellido2,String RazonSocialDian,String OperExt,String direccion,
		String telefono,String telcontacto,String emailcontac,String IndCliente,String IndProveedor,String IndEmpleado,String Municipio,String Departamento,
		String txtBanco,String cmbTipoCuenta,String cmbEstado,String txtPorcretIva,String txtFechaIngreso,String txtPorcretIca,
		String txtPagWeb,String txtDiasPlazo,String txtLineaProducto,String cmbAutoRetenedor,String txtNumCuenta){
		System.out.println(" tipo_identificacion "+tipo_identificacion+" numero_documento "+numero_documento+" nombre_razonsoc "+nombre_razonsoc+" Nombre1 "+Nombre1);
		System.out.println(" Nombre2 "+Nombre2+" Apellido1 "+Apellido1+" Apellido2 "+Apellido2+" RazonSocialDian "+RazonSocialDian+" OperExt "+OperExt+" direccion "+direccion);
		System.out.println(" telefono "+telefono+" telcontacto "+telcontacto+" emailcontac "+emailcontac+" IndCliente "+IndCliente+" IndProveedor "+IndProveedor+" IndEmpleado "+IndEmpleado+" Municipio "+Municipio+" Departamento "+Departamento);
		System.out.println(" txtBanco "+txtBanco+" cmbTipoCuenta "+cmbTipoCuenta+" cmbEstado "+cmbEstado+" txtPorcretIva "+txtPorcretIva+" txtFechaIngreso "+txtFechaIngreso+" txtPorcretIca "+txtPorcretIca);
		System.out.println(" txtPagWeb "+txtPagWeb+" txtDiasPlazo "+txtDiasPlazo+" txtLineaProducto "+txtLineaProducto+" cmbAutoRetenedor "+cmbAutoRetenedor+" txtNumCuenta "+txtNumCuenta);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("INSERT INTO cont_terceros (tipo_identificacion,numero_documento,razon_social,primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,nombre_razonsoc,operacion_extranjera,direccion,telefono,telcontacto,emailcontac,Ind_Cliente,Ind_Proveedor,Ind_Empleado,departamento,ciudad,banco,tipo_cuenta,estado,porc_retiva,fecha_ingreso,porc_retica,paginaweb,dias_plazo,linea_producto,autoretenedor,cuenta_banco) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");
			    ps.setString(1,tipo_identificacion);
			    ps.setString(2,numero_documento);
			    ps.setString(3,nombre_razonsoc);
			    ps.setString(4,Nombre1);
			    ps.setString(5,Nombre2);
			    ps.setString(6,Apellido1);
			    ps.setString(7,Apellido2);
			    ps.setString(8,RazonSocialDian);
			    ps.setString(9,OperExt);
			    ps.setString(10,direccion);
			    ps.setString(11,telefono);
			    ps.setString(12,telcontacto);
			    ps.setString(13,emailcontac);
			    ps.setString(14,IndCliente);
			    ps.setString(15,IndProveedor);
			    ps.setString(16,IndEmpleado);
			    ps.setString(17,Departamento);
			    ps.setString(18,Municipio);
			    ps.setString(19,txtBanco);
			    ps.setString(20,cmbTipoCuenta);
			    ps.setString(21,cmbEstado);
			    ps.setString(22,txtPorcretIva);
			    ps.setString(23,txtFechaIngreso);
			    ps.setString(24,txtPorcretIca);
			    ps.setString(25,txtPagWeb);
			    ps.setString(26,txtDiasPlazo);
			    ps.setString(27,txtLineaProducto);
			    ps.setString(28,cmbAutoRetenedor);
			    ps.setString(29,txtNumCuenta);
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoProveedor>>CrearProveedor "+ex);
			}

		}
	
	public void CrearCuentaEmpresas(String CodigoCuenta,String NombreCuenta,String TipoCuenta,
			String NaturalezaCuenta,
			String Nivel,String estado,String nit,
			String IndDiferido,String IndiBase,String IndiCCosto,String IndTercero,String PorcBase){
		
		Cuentas ct = new Cuentas();
		ct.setCodigoCuenta(CodigoCuenta);
		ct.setNombreCuenta(NombreCuenta);
		ct.setTipoCuenta(TipoCuenta);
		ct.setNaturalezaCuenta(NaturalezaCuenta);
		ct.setNivel(Nivel);
		ct.setestado(estado);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("insert into cont_cuentas_empresas(CodigoCuenta,NombreCuenta,TipoCuenta,NaturalezaCuenta,Nivel,estado,nit,Ind_diferido,Ind_Base,Ind_ccosto,Ind_tercero,porcentaje)values(?,?,?,?,?,?,?,?,?,?,?,?)");
			    ps.setString(1,ct.getCodigoCuenta());
			    ps.setString(2,ct.getNombreCuenta());
			    ps.setString(3,ct.getTipoCuenta());
			    ps.setString(4,ct.getNaturalezaCuenta());
			    ps.setString(5,ct.getNivel());
			    ps.setString(6,ct.getestado());
			    ps.setString(7,nit);
			    
			    ps.setString(8,IndDiferido);
			    ps.setString(9,IndiBase);
			    ps.setString(10,IndiCCosto);
			    ps.setString(11,IndTercero);
			    ps.setString(12,PorcBase);
			 
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoProveedor>>CrearCuenta "+ex);
			}

		}
	
	
	
	public void CrearCuenta(String CodigoCuenta,String NombreCuenta,String TipoCuenta,
			String NaturalezaCuenta,
			String Nivel,String estado,String nit,
			String IndDiferido,String IndiBase,String IndiCCosto,String IndTercero,String PorcBase){
		
		Cuentas ct = new Cuentas();
		ct.setCodigoCuenta(CodigoCuenta);
		ct.setNombreCuenta(NombreCuenta);
		ct.setTipoCuenta(TipoCuenta);
		ct.setNaturalezaCuenta(NaturalezaCuenta);
		ct.setNivel(Nivel);
		ct.setestado(estado);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("insert into cont_cuentas(CodigoCuenta,NombreCuenta,TipoCuenta,NaturalezaCuenta,Nivel,estado,nit,Ind_diferido,Ind_Base,Ind_ccosto,Ind_tercero,porcentaje)values(?,?,?,?,?,?,?,?,?,?,?,?)");
			    ps.setString(1,ct.getCodigoCuenta());
			    ps.setString(2,ct.getNombreCuenta());
			    ps.setString(3,ct.getTipoCuenta());
			    ps.setString(4,ct.getNaturalezaCuenta());
			    ps.setString(5,ct.getNivel());
			    ps.setString(6,ct.getestado());
			    ps.setString(7,nit);
			    
			    ps.setString(8,IndDiferido);
			    ps.setString(9,IndiBase);
			    ps.setString(10,IndiCCosto);
			    ps.setString(11,IndTercero);
			    ps.setString(12,PorcBase);
			 
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoProveedor>>CrearCuenta "+ex);
			}

		}
	
	public java.sql.ResultSet BuscarBancos(String NomBanco){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM cont_bancos WHERE nombre LIKE '%"+NomBanco+"%' ");
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProveedor>>BuscarBancos "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarBancosCodigo(String Codigo){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM cont_bancos WHERE codigo="+Codigo+"  ");
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProveedor>>BuscarBancosCodigo "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet TodosBancos(){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT cb.*,cc.CodigoCuenta,cc.NombreCuenta FROM cont_bancos cb,cont_cuentas cc WHERE cb.cod_cuenta_fk=cc.codigo  ");
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProveedor>>TodosBancos "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarAnioPeriodo(String Anio,String Periodo){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM cont_anio_periodo WHERE anio='"+Anio+"' AND periodo='"+Periodo+"'");
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProveedor>>BuscarAnioPeriodo "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet AnioPeriodoSeleccionado(String CodAP){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM cont_anio_periodo where codigo="+CodAP+" ");        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProveedor>>AnioPeriodoSeleccionado "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet TodosAnioPeriodo(){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM cont_anio_periodo ");
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProveedor>>TodosAnioPeriodo "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet ListarDiferidos(){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM cont_diferidos where codigo>0");
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProveedor>>ListarDiferidos "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ListarSucursales(){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM cont_sucursales ORDER BY nombre");
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProveedor>>ListarSucursales "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarRelacionSucursalCentroCosto(String CodCCosto,String CodSucursal){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM cont_surcursal_centrocosto WHERE cod_sucursal_fk="+CodSucursal+" AND cod_centro_costo_fk="+CodCCosto+" ");
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProveedor>>BuscarRelacionSucursalCentroCosto "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarRelacionCentrosCosto(String CodCCosto,String CodSubCCosto){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM cont_centro_subcentro WHERE cod_centro_costo_fk="+CodCCosto+" AND cod_sub_centro_costo_fk="+CodSubCCosto+"  ");
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProveedor>>BuscarRelacionCentrosCosto "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ListarGrupoCuenta(){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM cont_grupo_cuentas ORDER BY nombre ");
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProveedor>>ListarGrupoCuenta "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ListarCentrosCosto(){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM fact_centrocosto ORDER BY NombreCentroCosto ");
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProveedor>>ListarCentrosCosto "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ListarCentrosCostoCodigo(String CodCCosto){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM fact_centrocosto where codigo="+CodCCosto+" ORDER BY NombreCentroCosto ");
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProveedor>>ListarCentrosCosto "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ListarGrupoCuenta(String CodCCosto){
		System.out.println("SELECT * FROM cont_grupo_cuentas WHERE codigo="+CodCCosto+" ORDER BY nombre ");
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM cont_grupo_cuentas WHERE codigo="+CodCCosto+" ORDER BY nombre ");
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProveedor>>ListarGrupoCuenta "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ListarSubCentrosCostoCodigo(String CodSubCCosto){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM fact_subcentros_costo WHERE cod_subcentro_costo="+CodSubCCosto+" ORDER BY descripcion");
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProveedor>>ListarSubCentrosCostoCodigo "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet FacturasReciboCajaAnular(String CodRecCaja){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT cdrc.valor_abono,cdrc.cod_factura_fk FROM cont_detalle_recibo_caja cdrc WHERE cod_recibo_caja_fk="+CodRecCaja+"");
        	System.out.println("SELECT cdrc.valor_abono,cdrc.cod_factura_fk FROM cont_detalle_recibo_caja cdrc WHERE cod_recibo_caja_fk="+CodRecCaja+"");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>FactutasReciboCajaAnular "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ListarSubCentrosCosto(){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM fact_subcentros_costo ORDER BY descripcion ");
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProveedor>>ListarSubCentrosCosto "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ValorFacturasRadicadasIndividual(String CodFactura ,String Fecha ){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT cf.numero_factura,cf.precio_factura,SUM(cdf.cantidad) AS valor_deducido,(cf.precio_factura-(SUM(cdf.cantidad))) AS total FROM cont_factura cf,cont_detalle_factura cdf WHERE cf.codigo=cdf.cod_fact_fk AND cf.codigo="+CodFactura+" AND cdf.fecha<='"+Fecha+"' ");
        	//System.out.println("SELECT cf.numero_factura,cf.precio_factura,SUM(cdf.cantidad) AS valor_deducido,(cf.precio_factura-(SUM(cdf.cantidad))) AS total FROM cont_factura cf,cont_detalle_factura cdf WHERE cf.codigo=cdf.cod_fact_fk AND cf.codigo="+CodFactura+" AND cdf.fecha<='"+Fecha+"' ");
        	//System.out.println("SELECT * FROM (SELECT ae.ent_nit,ae.nombre_entidad,ae.ent_nit_contratante,cf.numero_factura,cf.fecha_factura,cf.precio_factura,SUM(cdf.cantidad) AS Deducido,(cf.precio_factura-(SUM(cdf.cantidad))) AS Restante,DATEDIFF('"+Fecha+"',cf.fecha_factura) dias,cf.estado FROM cont_factura cf,cont_detalle_factura cdf,cont_cuentas_empresas cce,adm_entidad ae WHERE cf.codigo=cdf.cod_fact_fk AND cce.codigo=cf.cod_cuenta_fk AND ae.ent_nit=cce.nit AND ae.ent_nit="+Nit+" AND cf.fecha_factura<='"+Fecha+"' AND cdf.fecha<='"+Fecha+"' GROUP BY cf.numero_factura ) AS a WHERE a.restante>0");
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProveedor>>ValorFacturasRadicadasIndividual "+ex);
        }	
        
        return rs;
    }
	
	
	
	public java.sql.ResultSet BuscarFacturasRadicadasUnido(String Nit ,String Fecha ){	
		System.out.println("SELECT cf.codigo,cf.fecha_factura,cf.numero_factura,cf.precio_factura,aen.nombre_entidad,ccp.fecha_radicado, DATEDIFF('"+Fecha+"', ccp.fecha_radicado) dias,aen.ent_nit_contratante FROM cont_cartera_plazo ccp,cont_factura cf,cont_cuentas_empresas ce,adm_entidad aen WHERE cf.codigo=ccp.cod_factura_fk AND cf.estado=2 AND ce.codigo=cf.cod_cuenta_fk AND aen.ent_nit=ce.nit AND aen.ent_nit_contratante="+Nit+" AND ccp.fecha_radicado<='"+Fecha+"' ORDER BY cf.numero_factura ");
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT cf.codigo,cf.fecha_factura,cf.numero_factura,cf.precio_factura,aen.nombre_entidad,ccp.fecha_radicado, DATEDIFF('"+Fecha+"', ccp.fecha_radicado) dias,aen.ent_nit_contratante FROM cont_cartera_plazo ccp,cont_factura cf,cont_cuentas_empresas ce,adm_entidad aen WHERE cf.codigo=ccp.cod_factura_fk AND cf.estado=2 AND ce.codigo=cf.cod_cuenta_fk AND aen.ent_nit=ce.nit AND aen.ent_nit_contratante="+Nit+" AND ccp.fecha_radicado<='"+Fecha+"' ORDER BY cf.numero_factura ");
        	System.out.println("SELECT cf.codigo,cf.fecha_factura,cf.numero_factura,cf.precio_factura,aen.nombre_entidad,ccp.fecha_radicado, DATEDIFF('"+Fecha+"', ccp.fecha_radicado) dias,aen.ent_nit_contratante FROM cont_cartera_plazo ccp,cont_factura cf,cont_cuentas_empresas ce,adm_entidad aen WHERE cf.codigo=ccp.cod_factura_fk AND cf.estado=2 AND ce.codigo=cf.cod_cuenta_fk AND aen.ent_nit=ce.nit AND aen.ent_nit_contratante="+Nit+" AND ccp.fecha_radicado<='"+Fecha+"' ORDER BY cf.numero_factura ");
        	//System.out.println("SELECT * FROM (SELECT ae.ent_nit,ae.nombre_entidad,ae.ent_nit_contratante,cf.numero_factura,cf.fecha_factura,cf.precio_factura,SUM(cdf.cantidad) AS Deducido,(cf.precio_factura-(SUM(cdf.cantidad))) AS Restante,DATEDIFF('"+Fecha+"',cf.fecha_factura) dias,cf.estado FROM cont_factura cf,cont_detalle_factura cdf,cont_cuentas_empresas cce,adm_entidad ae WHERE cf.codigo=cdf.cod_fact_fk AND cce.codigo=cf.cod_cuenta_fk AND ae.ent_nit=cce.nit AND ae.ent_nit="+Nit+" AND cf.fecha_factura<='"+Fecha+"' AND cdf.fecha<='"+Fecha+"' GROUP BY cf.numero_factura ) AS a WHERE a.restante>0");
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProveedor>>BuscarFacturasRadicadasIndividual "+ex);
        }	
        return rs;
    }
	
	
	
	public java.sql.ResultSet BuscarFacturasRadicadasIndividual(String Nit ,String Fecha ){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	//rs=st.executeQuery("SELECT cf.codigo,cf.fecha_factura,cf.numero_factura,cf.precio_factura,aen.nombre_entidad,ccp.fecha_radicado, DATEDIFF('"+Fecha+"', ccp.fecha_radicado) dias,aen.ent_nit_contratante FROM cont_cartera_plazo ccp,cont_factura cf,cont_cuentas_empresas ce,adm_entidad aen WHERE cf.codigo=ccp.cod_factura_fk AND cf.estado=2 AND ce.codigo=cf.cod_cuenta_fk AND aen.ent_nit=ce.nit AND aen.ent_nit="+Nit+" AND ccp.fecha_radicado<='"+Fecha+"' order by cf.numero_factura ");
        	rs=st.executeQuery("SELECT fn.consecutivo,fef.valor,DATEDIFF('"+Fecha+"',ffr.fechaRadicado) dias,ffr.fechaRadicado AS fecha_radicado,fef.fecha FROM fact_enc_factura fef,fact_numeradas fn,fact_facturas_radicadas ffr WHERE fef.cod_enc_factura=fn.cod_enc_fact_fk AND fn.cod_fact_numerada=ffr.codFact AND fn.estadoFact='2' AND ffr.fechaRadicado<='"+Fecha+"' AND fef.cod_eps='"+Nit+"' ");
        	//System.out.println("SELECT * FROM (SELECT ae.ent_nit,ae.nombre_entidad,ae.ent_nit_contratante,cf.numero_factura,cf.fecha_factura,cf.precio_factura,SUM(cdf.cantidad) AS Deducido,(cf.precio_factura-(SUM(cdf.cantidad))) AS Restante,DATEDIFF('"+Fecha+"',cf.fecha_factura) dias,cf.estado FROM cont_factura cf,cont_detalle_factura cdf,cont_cuentas_empresas cce,adm_entidad ae WHERE cf.codigo=cdf.cod_fact_fk AND cce.codigo=cf.cod_cuenta_fk AND ae.ent_nit=cce.nit AND ae.ent_nit="+Nit+" AND cf.fecha_factura<='"+Fecha+"' AND cdf.fecha<='"+Fecha+"' GROUP BY cf.numero_factura ) AS a WHERE a.restante>0");
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProveedor>>BuscarFacturasRadicadasIndividual "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet DetallesFacturasRadicadaz(String Nit ,String Fecha,String h ){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("");
        	//System.out.println("SELECT * FROM (SELECT ae.ent_nit,ae.nombre_entidad,ae.ent_nit_contratante,cf.numero_factura,cf.fecha_factura,cf.precio_factura,SUM(cdf.cantidad) AS Deducido,(cf.precio_factura-(SUM(cdf.cantidad))) AS Restante,DATEDIFF('"+Fecha+"',cf.fecha_factura) dias,cf.estado FROM cont_factura cf,cont_detalle_factura cdf,cont_cuentas_empresas cce,adm_entidad ae WHERE cf.codigo=cdf.cod_fact_fk AND cce.codigo=cf.cod_cuenta_fk AND ae.ent_nit=cce.nit AND ae.ent_nit="+Nit+" AND cf.fecha_factura<='"+Fecha+"' AND cdf.fecha<='"+Fecha+"' GROUP BY cf.numero_factura ) AS a WHERE a.restante>0");
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProveedor>>DetallesFacturas "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet VerificarDeduccion(String Numero_Factura, String fecha ){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT SUM(cdf.cantidad) AS Deducciones FROM cont_factura cf,cont_detalle_factura cdf WHERE cf.codigo=cdf.cod_fact_fk AND cf.numero_factura='"+Numero_Factura+"' and cdf.fecha<='"+fecha+"' ");
        	System.out.println("SELECT SUM(cdf.cantidad) AS Deducciones FROM cont_factura cf,cont_detalle_factura cdf WHERE cf.codigo=cdf.cod_fact_fk AND cf.numero_factura='"+Numero_Factura+"' and cdf.fecha<='"+fecha+"' ");
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProveedor>>VerificarDeduccion "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet DetallesFacturas(String Nit ,String Fecha ){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	//rs=st.executeQuery("SELECT * FROM (SELECT ae.ent_nit,ae.nombre_entidad,ae.ent_nit_contratante,cf.numero_factura,cf.fecha_factura,cf.precio_factura,SUM(cdf.cantidad) AS Deducido,(cf.precio_factura-(SUM(cdf.cantidad))) AS Restante,DATEDIFF('"+Fecha+"',cf.fecha_factura) dias,cf.estado FROM cont_factura cf,cont_detalle_factura cdf,cont_cuentas_empresas cce,adm_entidad ae WHERE cf.codigo=cdf.cod_fact_fk AND cce.codigo=cf.cod_cuenta_fk AND ae.ent_nit=cce.nit AND ae.ent_nit="+Nit+" AND cf.fecha_factura<='"+Fecha+"' AND cdf.fecha<='"+Fecha+"' GROUP BY cf.numero_factura ) AS a WHERE a.restante>0");
        	rs=st.executeQuery("SELECT fn.consecutivo,fef.valor,DATEDIFF('"+Fecha+"', fef.fecha) dias FROM fact_enc_factura fef,fact_numeradas fn WHERE fef.cod_enc_factura=fn.cod_enc_fact_fk AND fef.fecha<= '"+Fecha+"' AND fef.cod_eps= '"+Nit+"' AND fn.estadoFact!=5");
        	//System.out.println("SELECT * FROM (SELECT ae.ent_nit,ae.nombre_entidad,ae.ent_nit_contratante,cf.numero_factura,cf.fecha_factura,cf.precio_factura,SUM(cdf.cantidad) AS Deducido,(cf.precio_factura-(SUM(cdf.cantidad))) AS Restante,DATEDIFF('"+Fecha+"',cf.fecha_factura) dias,cf.estado FROM cont_factura cf,cont_detalle_factura cdf,cont_cuentas_empresas cce,adm_entidad ae WHERE cf.codigo=cdf.cod_fact_fk AND cce.codigo=cf.cod_cuenta_fk AND ae.ent_nit=cce.nit AND ae.ent_nit="+Nit+" AND cf.fecha_factura<='"+Fecha+"' AND cdf.fecha<='"+Fecha+"' GROUP BY cf.numero_factura ) AS a WHERE a.restante>0");
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProveedor>>DetallesFacturas "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ConsultarSaldoPendiente(String NumeroFactura){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT cf.precio_factura,(cf.precio_factura-SUM(cdf.cantidad)) AS SaldoFactura FROM cont_detalle_factura cdf,cont_factura cf WHERE cf.codigo=cdf.cod_fact_fk AND cf.numero_factura='"+NumeroFactura+"' ");
        	System.out.println("SELECT cf.precio_factura,(cf.precio_factura-SUM(cdf.cantidad)) AS SaldoFactura FROM cont_detalle_factura cdf,cont_factura cf WHERE cf.codigo=cdf.cod_fact_fk AND cf.numero_factura='"+NumeroFactura+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProveedor>>ConsultarSaldoPendiente "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ConsultarValorAbonado(String NumeroFactura){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT SUM(valor_abono) AS ValorRecaudo FROM cont_recibo_caja crc,cont_detalle_recibo_caja cdrc WHERE crc.codigo=cdrc.cod_recibo_caja_fk AND cdrc.numero_factura='"+NumeroFactura+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProveedor>>ConsultarValorAbonado "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ConsultarGlosasIniciales(String NumeroFactura){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT SUM(valorglosa) AS GlosaInicial FROM fact_glosasnotificadassdetalle WHERE factura='"+NumeroFactura+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProveedor>>ConsultarGlosasIniciales "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet ConsultarGlosasAceptadas(String NumeroFactura){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT SUM(valoraceptado) AS GlosaAceptada FROM fact_glosasenviadasdetalle WHERE factura='"+NumeroFactura+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProveedor>>ConsultarGlosasAceptadas "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet ConsultarCuentaCobro(String NumeroFactura){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT CONCAT('CTA000',ffe.consEnvio) AS CtaCobro FROM fact_enc_factura fef,fact_numeradas fn,fact_facturas_enviadas ffe WHERE fef.cod_enc_factura=fn.cod_enc_fact_fk AND fn.consecutivo='"+NumeroFactura+"' AND fn.cod_fact_numerada=ffe.codFact");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProveedor>>ConsultarCuentaCobro "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ConsultarDatosRadicacion(String NumeroFactura){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT fn.consecutivo,fef.fecha,fef.valor,ff.fecha AS FechaRadicado,CONCAT('RAD0',ff.consRadicado) AS Radicado,(fef.valor-ffr.valornc) AS ValorRadicado FROM fact_enc_factura fef,fact_numeradas fn,fact_factradicadas ff,fact_facturas_radicadas ffr WHERE fef.cod_enc_factura=fn.cod_enc_fact_fk AND fn.consecutivo='"+NumeroFactura+"' AND ff.consRadicado=ffr.consRadicado AND ffr.codFact=fn.cod_fact_numerada LIMIT 1");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProveedor>>ConsultarDatosRadicacion "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ConsultarNotasCredito(String NumeroFactura){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT SUM(cdf.cantidad) AS NotasCredito FROM cont_factura cf,cont_detalle_factura cdf,cont_complemento_factura ccf WHERE cf.codigo=cdf.cod_fact_fk AND cf.numero_factura='"+NumeroFactura+"' AND ccf.cod_det_factura_fk=cdf.codigo");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProveedor>>ConsultarNotasCredito "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet ConsultarFacturasCartera(String Sql){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT fef.razon_social,fn.consecutivo,fn.fecha,fef.valor,fn.estadoFact FROM fact_enc_factura fef,fact_numeradas fn WHERE fef.cod_enc_factura=fn.cod_enc_fact_fk and fn.estadoFact!='5' "+Sql);
        	System.out.println("SELECT fef.razon_social,fn.consecutivo,fn.fecha,fef.valor,fn.estadoFact FROM fact_enc_factura fef,fact_numeradas fn WHERE fef.cod_enc_factura=fn.cod_enc_fact_fk "+Sql);
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProveedor>>ConsultarFacturasCartera "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet Entidades(){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT ent_nit,nombre_entidad FROM adm_entidad WHERE ent_nit!='0' and nombre_entidad!='' ORDER BY nombre_entidad");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProveedor>>Entidades "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet BuscarRecibocaja(String NumeroReciboCaja){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT crc.codigo rec_caja,aent.nombre_entidad,ctc.Concepto,CONCAT(sdt.nombre,' ',sdt.apellido) AS usuario,CONCAT(crc.fecha,'/',crc.hora) AS fecha_insercion,crc.valor_total FROM cont_recibo_caja crc,adm_entidad aent,seg_usuario su,seg_datos_personales sdt,cont_detalle_recibo_caja cdr,cont_tipo_concepto ctc WHERE crc.cod_entidad_fk=aent.ent_nit AND crc.concepto=ctc.codigo AND su.usu_codigo=crc.cod_usu_fk AND sdt.dat_codigo=su.dat_codigo_fk AND crc.codigo=cdr.cod_recibo_caja_fk AND crc.codigo='"+NumeroReciboCaja+"' LIMIT 1");
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProveedor>>BuscarRecibocaja "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet Nombreusuario(String CodUsuario){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT concat(sdt.nombre,' ',sdt.apellido) nom_usu FROM seg_usuario su,seg_datos_personales sdt WHERE su.dat_codigo_fk=sdt.dat_codigo and su.usu_codigo="+CodUsuario+"");
        	System.out.println("SELECT concat(sdt.nombre,' ',sdt.apellido) nom_usu FROM seg_usuario su,seg_datos_personales sdt WHERE su.dat_codigo_fk=sdt.dat_codigo and su.usu_codigo="+CodUsuario+"");
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProveedor>>Nombreusuario "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarNotaCreditoNC(String NumeroNotaCredito){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT cdf.codigo,cdf.cod_fact_fk,cf.numero_factura,cdf.cantidad,CONCAT(cdf.fecha_insercion,'/',cdf.hora_insercion),ccf.codigo fecha FROM  cont_factura cf,cont_detalle_factura cdf,cont_complemento_factura ccf WHERE cf.codigo = cdf.cod_fact_fk AND ccf.cod_det_factura_fk = cdf.codigo AND ccf.codigo = '"+NumeroNotaCredito+"' ");
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProveedor>>BuscarNotaCreditoNC "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarTerceroAutocompletar(String Parametro){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM cont_terceros WHERE numero_documento LIKE '%"+Parametro+"%' limit 40");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProveedor>>BuscarTerceroAutocompletar "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet BuscarCuentaDiferidoAutocompletar(String Parametro){	
		
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM cont_cuentas WHERE Ind_diferido='0' AND  TipoCuenta='Auxiliar' AND (CodigoCuenta LIKE '%"+Parametro+"%' OR NombreCuenta LIKE '%"+Parametro+"%')  limit 40");
        	System.out.println("SELECT * FROM cont_cuentas WHERE Ind_diferido='0' AND  TipoCuenta='Auxiliar' AND (CodigoCuenta LIKE '%"+Parametro+"%' OR NombreCuenta LIKE '%"+Parametro+"%')  limit 40");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProveedor>>BuscarCuentaDiferidoAutocompletar "+ex);
        }	
        return rs;
    }

	
	public java.sql.ResultSet BuscarCuentaAutocompletar(String Parametro){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM cont_cuentas WHERE TipoCuenta = 'Auxiliar'  AND (CodigoCuenta LIKE '%"+Parametro+"%' OR NombreCuenta LIKE '%"+Parametro+"%') limit 40");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProveedor>>BuscarCuentaAutocompletar "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarNotaCreditoNF(String NumeroFactura){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT cdf.codigo,cdf.cod_fact_fk,cf.numero_factura,cdf.cantidad,CONCAT(cdf.fecha_insercion,'/',cdf.hora_insercion) fecha,ccf.codigo FROM cont_factura cf,cont_detalle_factura cdf,cont_complemento_factura ccf WHERE cf.codigo=cdf.cod_fact_fk AND ccf.cod_det_factura_fk = cdf.codigo AND cf.numero_factura LIKE '%"+NumeroFactura+"%' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProveedor>>BuscarNotaCreditoNF "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet BuscarCuentaProveedor(String tipo_identificacion,String numero_documento){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select ccu.codigo,ccu.CodigoCuenta,ccu.NombreCuenta,ccu.TipoCuenta,ccu.NaturalezaCuenta,ccu.Nivel,ccu.estado from cont_terceros cpr,cont_cuenta_proveedor ccp,cont_cuentas ccu where cpr.tipo_identificacion='"+tipo_identificacion+"' and cpr.numero_documento='"+numero_documento+"' and cpr.codigo=ccp.cod_prov_fk and ccu.codigo=ccp.cod_cuen_fk ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProveedor>>BuscarCuentaProveedor "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet Departamentos(){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM adm_departamento ORDER BY nombre ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProveedor>>Departamentos "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet Municipios(String CodDept){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM adm_municipio WHERE dept_codigo_fk="+CodDept+" ORDER BY nombre ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProveedor>>Municipios "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ListarTodosTerceros(){	
		
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select * from cont_terceros ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProveedor>>ListarTodosTerceros "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarProveedorLike(String tipo_identificacion,String numero_documento){	
		
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select * from cont_terceros where tipo_identificacion='"+tipo_identificacion+"' and numero_documento like '%"+numero_documento+"%'");
        	//System.out.println("select * from cont_terceros where tipo_identificacion='"+tipo_identificacion+"' and numero_documento='"+numero_documento+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProveedor>>BuscarProveedor "+ex);
        }	
        return rs;
    }
	
	
public java.sql.ResultSet BuscarCodigoFactura(String CodDetalle){	
		
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM cont_detalle_factura WHERE codigo="+CodDetalle+" ");
        	//System.out.println("select * from cont_terceros where tipo_identificacion='"+tipo_identificacion+"' and numero_documento='"+numero_documento+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProveedor>>BuscarCodigoFactura "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarProveedor(String tipo_identificacion,String numero_documento){	
		
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select * from cont_terceros where tipo_identificacion='"+tipo_identificacion+"' and numero_documento='"+numero_documento+"'");
        	//System.out.println("select * from cont_terceros where tipo_identificacion='"+tipo_identificacion+"' and numero_documento='"+numero_documento+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProveedor>>BuscarProveedor "+ex);
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
        	System.out.println("Error en MetodoProveedor>>BuscarCuenta "+ex);
        }	
        return rs;
    }
	
	public void ActualizarTercero(String tipo_identificacion,
			String numero_documento,
			String nombre_razonsoc,
			String Nombre1,
			String Nombre2,
			String Apellido1,
			String Apellido2,
			String RazonSocialDian,
			String OperExt,
			String direccion,
			String telefono,
			String telcontacto,
			String emailcontac,
			String IndCliente,
			String IndProveedor,
			String IndEmpleado,
			String Municipio,
			String Departamento,
			String txtBanco,String cmbTipoCuenta,String cmbEstado,
			String txtPorcretIva,String txtFechaIngreso,String txtPorcretIca,String txtPagWeb,String txtDiasPlazo,String txtLineaProducto,
			String cmbAutoRetenedor,String txtNumCuenta,
			String CodTercero){

		PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE  cont_terceros SET " +
	     			" tipo_identificacion = '"+tipo_identificacion+"', numero_documento = '"+numero_documento+"', " +
	     					" primer_nombre = '"+Nombre1+"', segundo_nombre = '"+Nombre2+"', " +
	     							" primer_apellido = '"+Apellido1+"', segundo_apellido = '"+Apellido2+"', " +
	     									" razon_social = '"+nombre_razonsoc+"', nombre_razonsoc = '"+RazonSocialDian+"', " +
	     											" direccion = '"+direccion+"',telefono = '"+telefono+"' , telcontacto='"+telcontacto+"', " +
	     													" emailcontac='"+emailcontac+"', departamento='"+Departamento+"', ciudad='"+Municipio+"', " +
	     															" operacion_extranjera='"+OperExt+"',Ind_Cliente="+IndCliente+",Ind_Proveedor="+IndProveedor+", " +
	     																	" Ind_Empleado="+IndEmpleado+",porc_retiva='"+txtPorcretIva+"',porc_retica='"+txtPorcretIca+"', " +
	     																			"dias_plazo='"+txtDiasPlazo+"',estado='"+cmbEstado+"',fecha_ingreso='"+txtFechaIngreso+"', " +
	     																			"paginaweb='"+txtPagWeb+"',linea_producto='"+txtLineaProducto+"',cuenta_banco='"+Municipio+"', " +
	     																					"banco='"+txtBanco+"',tipo_cuenta='"+cmbTipoCuenta+"',autoretenedor='"+cmbAutoRetenedor+"' WHERE codigo = "+CodTercero+" ");

	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("ERROR EN MetodoProveedor>>ActualizarTercero "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public void OmitirReciboCajaTotal(String CodRecCaja,String MsjOmision){	
		PreparedStatement st = null;	     
		try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE  cont_recibo_caja SET obsevacion = '"+MsjOmision+"', valor_total = '0' WHERE codigo = '"+CodRecCaja+"' ");
	     	System.out.println("UPDATE  cont_recibo_caja SET obsevacion = '"+MsjOmision+"', valor_total = '0' WHERE codigo = '"+CodRecCaja+"' ");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("ERROR EN MetodoCuentas>>OmitirReciboCajaTotal "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public void OmitirDetalleFacturadeReciboCajaAnulado(String Valor_Abono,String CodigoFacturaA,String MsjOmision){	
		System.out.println("MsjOmision en Metodo: "+MsjOmision);
		PreparedStatement st = null;	     
		try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE cont_detalle_factura SET cantidad='0',observacion='"+MsjOmision+"' WHERE cantidad='"+Valor_Abono+"' AND cod_fact_fk='"+CodigoFacturaA+"'");
	     	System.out.println("UPDATE cont_detalle_factura SET cantidad='0',observacion='"+MsjOmision+"' WHERE cantidad='"+Valor_Abono+"' AND cod_fact_fk='"+CodigoFacturaA+"'");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("ERROR EN MetodoCuentas>>OmitirDetalleFacturadeReciboCajaAnulado "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public void OmitirDetallesReciboCajaTotal(String CodRecCaja){	
		PreparedStatement st = null;	     
		try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE cont_detalle_recibo_caja SET valor_abono='0' WHERE cod_recibo_caja_fk='"+CodRecCaja+"'");
	     	System.out.println("UPDATE cont_detalle_recibo_caja SET valor_abono='0' WHERE cod_recibo_caja_fk='"+CodRecCaja+"'");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("ERROR EN MetodoCuentas>>OmitirDetallesReciboCajaTotal "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public void ActualizarCuenta(String CodigoCuenta,String NombreCuenta,String TipoCuenta,String NaturalezaCuenta,String Nivel,String estado,String CodCuenta
			,String IndDiferido,String IndiBase,String IndiCCosto,String IndTercero,String PorcBase){
		
		System.out.println("update cont_cuentas set CodigoCuenta="+CodigoCuenta+",NombreCuenta='"+NombreCuenta+"',TipoCuenta='"+TipoCuenta+"',NaturalezaCuenta='"+NaturalezaCuenta+"',Nivel="+Nivel+",estado='"+estado+"',Ind_diferido="+IndDiferido+",Ind_Base="+IndiBase+",Ind_ccosto="+IndiCCosto+",Ind_tercero="+IndTercero+" where codigo="+CodCuenta+" ");
		
		System.out.println("CodigoCuenta"+CodigoCuenta+" NombreCuenta"+NombreCuenta+" TipoCuenta "+TipoCuenta+" NaturalezaCuenta "+NaturalezaCuenta+" Nivel "+Nivel+" estado "+estado+" CodCuenta "+CodCuenta
				+" IndDiferido "+IndDiferido+" IndiBase "+IndiBase+" IndiCCosto "+IndiCCosto+" IndTercero "+IndTercero);
				
		PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("update cont_cuentas set CodigoCuenta="+CodigoCuenta+",NombreCuenta='"+NombreCuenta+"',TipoCuenta='"+TipoCuenta+"',NaturalezaCuenta='"+NaturalezaCuenta+"',Nivel="+Nivel+",estado='"+estado+"',Ind_diferido="+IndDiferido+",Ind_Base="+IndiBase+",Ind_ccosto="+IndiCCosto+",Ind_tercero="+IndTercero+",porcentaje='"+PorcBase+"' where codigo="+CodCuenta+" ");

	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("ERROR EN MetodoProveedor>>ActualizarCuenta "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public void ActualizarAnioPeriodo(String CodAP,String Bloqueo,String BloqueoCxP){
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE cont_anio_periodo SET bloqueo = '"+Bloqueo+"',bloqueo_cxp = '"+BloqueoCxP+"' WHERE codigo = "+CodAP+" ");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("ERROR EN MetodoProveedor>>ActualizarAnioPeriodo "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public void ActualizarProveedor(String tipo_identificacion,String numero_documento,String nombre_razonsoc,String autoretenedor,String tipo_regimen,String direccion,String telefono,String contacto,String telcontacto,String emailcontac,String codigo){
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("update cont_terceros set tipo_identificacion='"+tipo_identificacion+"',numero_documento='"+numero_documento+"',nombre_razonsoc='"+nombre_razonsoc+"',autoretenedor='"+autoretenedor+"',tipo_regimen='"+tipo_regimen+"',direccion='"+direccion+"',telefono='"+telefono+"',contacto='"+contacto+"',telcontacto='"+telcontacto+"',emailcontac='"+emailcontac+"' where codigo='"+codigo+"'");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("ERROR EN MetodoProveedor>>ActualizarProveedor "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public void OmitirNotaCredito(String Obs,String Codigo){
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE cont_detalle_factura SET cantidad='0',observacion='"+Obs+"' WHERE codigo="+Codigo+"");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("ERROR EN MetodoProveedor>>OmitirNotaCredito "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public void ActualizarCentroCosto(String CodCCosto,String NomCentroCosto){
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE fact_centrocosto SET NombreCentroCosto='"+NomCentroCosto+"' WHERE codigo="+CodCCosto+" ");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("ERROR EN MetodoProveedor>>ActualizarCentroCosto "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public void ActualizarSubCentroCosto(String CodSubCCosto,String SubcentroCosto){
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE fact_subcentros_costo SET descripcion='"+SubcentroCosto+"' WHERE cod_subcentro_costo="+CodSubCCosto+" ");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("ERROR EN MetodoProveedor>>ActualizarSubCentroCosto "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public void ActualizarGrupoCuenta(String CodGruCuenta,String NombreGruCuenta){
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE cont_grupo_cuentas SET nombre='"+NombreGruCuenta+"' WHERE codigo="+CodGruCuenta+" ");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("ERROR EN MetodoProveedor>>ActualizarGrupoCuenta "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	
//////////////METODOS DE ANULACION DE FACTURAS////////////////////
	
	public java.sql.ResultSet MovimientosFactura(String v1){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT ccf.codigo FROM cont_factura cf,cont_detalle_factura cdf,cont_complemento_factura ccf WHERE cf.codigo = cdf.cod_fact_fk  AND cf.numero_factura LIKE '%"+v1+"%' AND ccf.cod_det_factura_fk=cdf.codigo ");
          }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>MovimientosFactura "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet GeneraSQL(String v1){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT n.consecutivo, ef.razon_social, p.tipo_documento, p.numero_documento, p.nombre, p.primer_apellido, p.segundo_apellido, ef.fecha,mf.estadoFact,u.usuario,a.adm_numero_ingreso,ef.cod_enc_factura,ef.efectivo,ef.valor,n.cod_fact_numerada,'' AS valor_fact FROM fact_enc_factura ef, adm_admisiones a, adm_paciente p, fact_numeradas n, fact_movfacturas mf, seg_usuario u WHERE ef.cod_admision=a.adm_numero_ingreso AND a.pac_codigo_paciente_fk=p.pac_codigo_paciente AND ef.cod_enc_factura=n.cod_enc_fact_fk AND n.cod_fact_numerada=mf.codFactNumerada AND n.estadoFact=mf.estadoFact AND mf.usuario=u.usu_codigo AND n.consecutivo like '%"+v1+"%'");
          }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>GeneraSQL "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarTipoPago(String FormaPago){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM cont_tipo_pago WHERE FormaPago='"+FormaPago+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>BuscarTipoPago "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet MovimientosTipoCredito(){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT cmc.descripcion,cc.NombreCuenta,cc.CodigoCuenta FROM cont_movimientos_credito cmc,cont_cuentas cc WHERE cmc.CodCuenta_fk=cc.codigo");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>MovimientosTipoCredito "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet TodosTipoPago(){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT ctp.*,cc.CodigoCuenta,cc.NombreCuenta,cc.NaturalezaCuenta FROM cont_tipo_pago ctp,cont_cuentas cc WHERE ctp.cuenta_fk=cc.codigo");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>TodosTipoPago "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet GeneraSQLSF(String v1){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT n.consecutivo, ef.razon_social, p.tipo_documento, p.numero_documento, p.nombre, p.primer_apellido, p.segundo_apellido, ef.fecha, 'FACTURADA ',u.usuario,a.adm_numero_ingreso,ef.cod_enc_factura, ef.efectivo,ef.valor,n.cod_fact_numerada FROM fact_enc_factura ef, adm_admisiones a, adm_paciente p, fact_numeradas n, seg_usuario u WHERE ef.cod_admision=a.adm_numero_ingreso AND a.pac_codigo_paciente_fk=p.pac_codigo_paciente AND ef.cod_usuario_fk=u.usu_codigo AND ef.cod_enc_factura=n.cod_enc_fact_fk AND n.consecutivo='"+v1+"'");
        	//System.out.println("SELECT n.consecutivo, ef.razon_social, p.tipo_documento, p.numero_documento, p.nombre, p.primer_apellido, p.segundo_apellido, ef.fecha, 'FACTURADA ',u.usuario,a.adm_numero_ingreso,ef.cod_enc_factura, ef.efectivo,ef.valor,n.cod_fact_numerada FROM fact_enc_factura ef, adm_admisiones a, adm_paciente p, fact_numeradas n, seg_usuario u WHERE ef.cod_admision=a.adm_numero_ingreso AND a.pac_codigo_paciente_fk=p.pac_codigo_paciente AND ef.cod_usuario_fk=u.usu_codigo AND ef.cod_enc_factura=n.cod_enc_fact_fk AND n.consecutivo='"+v1+"'");  
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>GeneraSQL "+ex);
        }	
        return rs;
    }
	
	
	public void ActualizarEstadoAdmision(String CodAdm){
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE adm_admisiones SET atendido='0' WHERE adm_numero_ingreso='"+CodAdm+"' ");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("ERROR EN MetodoProveedor>>ActualizarEstadoAdmision "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public void ActualizarEstadoDetalleFactura(String CodEnca){
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE fact_det_factura SET facturado='5' WHERE cod_enc_factura_fk='"+CodEnca+"' ");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("ERROR EN MetodoProveedor>>ActualizarEstadoDetalleFactura "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	
	public void ActualizarEstadoEncabezadoFactura(String CodEnca){
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE fact_enc_factura SET estado='5' WHERE cod_enc_factura='"+CodEnca+"' ");
	     	st.executeUpdate();	 
	     	
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("ERROR EN MetodoProveedor>>ActualizarEstadoEncabezadoFactura "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	
	public void ActualizarEstadoCont(String codigo){
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement(" UPDATE cont_factura SET estado='3' WHERE numero_factura='"+codigo+"' ");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("ERROR EN MetodoProveedor>>ActualizarEstadoCont "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public void ActualizarEstadoFactNumeradas(String codigo){
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	//st= con.conn.prepareStatement(" UPDATE fact_numeradas SET estadoFact='5' WHERE cod_enc_fact_fk='"+codigo+"' ");
	     	st= con.conn.prepareStatement(" UPDATE fact_numeradas SET estadoFact='5' WHERE consecutivo='"+codigo+"' ");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("ERROR EN MetodoProveedor>>ActualizarEstadoCont "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public void InsertarMovimientoCredito(String Descripcion,String cuenta_fk){
		try{
			PreparedStatement ps = null;
		    Conexion con=new Conexion();
		    															
		    ps=con.conn.prepareStatement("INSERT INTO cont_movimientos_credito(descripcion,CodCuenta_fk) VALUES (?,?)");
		    ps.setString(1,Descripcion);
		    ps.setString(2,cuenta_fk);		
		
		 	ps.executeUpdate();
			ps.close();
			con.cerrar();				
		}catch(Exception ex){
        	System.out.println("ERROR EN MetodoProveedor>>InsertarMovimientoCredito "+ex);
		}
	}
	
	public void InsertarTipoPago(String FormaPago,String cuenta_fk,String naturaleza,String comision,String retefuente, String iva,String reteiva,String reteica){
		try{
			PreparedStatement ps = null;
		    Conexion con=new Conexion();
		    															
		    ps=con.conn.prepareStatement("INSERT INTO cont_tipo_pago(FormaPago,cuenta_fk,naturaleza,comision,retefuente,iva,reteiva,reteica) VALUES (?,?,?,?,?,?,?,?)");
		    ps.setString(1,FormaPago);
		    ps.setString(2,cuenta_fk);		
		    ps.setString(3,naturaleza);	
		    ps.setString(4,comision);
		    ps.setString(5,retefuente);
		    ps.setString(6,iva);
		    ps.setString(7,reteiva);
		    ps.setString(8,reteica);
		 	ps.executeUpdate();
			ps.close();
			con.cerrar();				
		}catch(Exception ex){
        	System.out.println("ERROR EN MetodoProveedor>>InsertarTipoPago "+ex);
		}
	}
	
	public void recordEstadoFact(String codFact,String estado,String fecha,String usuario,String Observacion){
		try{
			PreparedStatement ps = null;
		    Conexion con=new Conexion();
		    															
		    ps=con.conn.prepareStatement("INSERT INTO fact_movfacturas(codFactNumerada,estadoFact,fecha,usuario,Observacion) VALUES (?,?,?,?,?)");
		    ps.setString(1,codFact);
		    ps.setString(2,estado);		
		    ps.setString(3,fecha);	
		    ps.setString(4,usuario);
		    ps.setString(5,Observacion);
		 	ps.executeUpdate();
			ps.close();
			con.cerrar();				
		}catch(Exception ex){
        	System.out.println("ERROR EN. MetodoProveedor>>recordEstadoFact "+ex);
		}
	}
	

}
