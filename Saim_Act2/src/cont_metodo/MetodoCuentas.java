package cont_metodo;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Time;
import java.util.regex.Pattern;

import adm_logica.Conexion;
import cont_bean.Facturas;

public class MetodoCuentas {
	

	public java.sql.ResultSet BuscarDatosPacientesFacturar(String NumDoc,String TipoDoc){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT CONCAT(ap.nombre,' ',ap.primer_apellido,' ',ap.segundo_apellido) AS NombrePaciente,ap.direccion,CONCAT(ap.telefono_celular,' ',ap.telefono_fijo,' ',ap.telefono_oficina) AS Telefono,ap.tipo_afiliacion,ap.estrato FROM adm_paciente ap WHERE ap.tipo_documento='"+TipoDoc+"' AND ap.numero_documento='"+NumDoc+"'");
        	//System.out.println("select cantidad,fecha_insercion,hora_insercion,num_soporte,fecha,restante,observacion,codigo,cod_fact_fk from cont_detalle_factura where cod_fact_fk="+cod_fact_fk+" and cantidad>0 order by codigo desc");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>BuscarDatosPacientesFacturar "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ListarEntidadesCodigo(String CodEps){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM adm_entidad WHERE estado='Activo' AND nombre_entidad!='' and ent_nit="+CodEps+" ORDER BY nombre_entidad");
        	//System.out.println("select cantidad,fecha_insercion,hora_insercion,num_soporte,fecha,restante,observacion,codigo,cod_fact_fk from cont_detalle_factura where cod_fact_fk="+cod_fact_fk+" and cantidad>0 order by codigo desc");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>ListarEntidades "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ConsultarEncabezado(String Fecha,String Hora){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select * from fact_enc_factura where fecha='"+Fecha+"' and hora='"+Hora+"'");
        	//System.out.println("select cantidad,fecha_insercion,hora_insercion,num_soporte,fecha,restante,observacion,codigo,cod_fact_fk from cont_detalle_factura where cod_fact_fk="+cod_fact_fk+" and cantidad>0 order by codigo desc");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>ConsultarEncabezado "+ex);
        }	
        return rs;
    }
	
	
	public void InsertarEncabezadoFactura(String cod_eps,String razon_social,String nit,String direccion,String telefono, 
			String ciudad,String condicion_pago,String nombre_paciente,String documento,String direccion_p, 
			String telefono_p,String tipo_afiliacion,String estrato,String fecha_ingreso,String fecha_egreso, 
			String cod_admision,String num_autorizacion,String cod_usuario_fk,String poliza,String fecha, 
			String hora,String tipo,String valor,String valorletras,String anticipos, 
			String copago,String moderacion,String efectivo,String estado){
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("INSERT INTO fact_enc_factura( " +
			    		" cod_eps,razon_social,nit,direccion,telefono, " +
			    		" ciudad,condicion_pago,nombre_paciente,documento,direccion_p, " +
			    		" telefono_p,tipo_afiliacion,estrato,fecha_ingreso,fecha_egreso, " +
			    		" cod_admision,num_autorizacion,cod_usuario_fk,poliza,fecha, " +
			    		" hora,tipo,valor,valorletras,anticipos, " +
			    		" copago,moderacion,efectivo,estado) " +
			    		"VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			    ps.setString(1,cod_eps);
			    ps.setString(2,razon_social);
			    ps.setString(3,nit);
			    ps.setString(4,direccion);
			    ps.setString(5,telefono);
			    
			    ps.setString(6,ciudad);
			    ps.setString(7,condicion_pago);
			    ps.setString(8,nombre_paciente);
			    ps.setString(9,documento);
			    ps.setString(10,direccion_p);
			    
			    ps.setString(11,telefono_p);
			    ps.setString(12,tipo_afiliacion);
			    ps.setString(13,estrato);
			    ps.setString(14,fecha_ingreso);
			    ps.setString(15,fecha_egreso);
			    
			    ps.setString(16,cod_admision);
			    ps.setString(17,num_autorizacion);
			    ps.setString(18,cod_usuario_fk);
			    ps.setString(19,poliza);
			    ps.setString(20,fecha);
			    
			    ps.setString(21,hora);
			    ps.setString(22,tipo);
			    ps.setString(23,valor);
			    ps.setString(24,valorletras);
			    ps.setString(25,anticipos);
			    
			    ps.setString(26,copago);
			    ps.setString(27,moderacion);
			    ps.setString(28,efectivo);
			    ps.setString(29,estado);
			    
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoCuentas>>InsertarEncabezadoFactura "+ex);
			}

		}
	
	public void InsertarNumeradas(String cod_enc_fact_fk,String consecutivo,String fecha,String estadoFact){

		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("INSERT INTO fact_numeradas(cod_enc_fact_fk,consecutivo,fecha,estadoFact)VALUES(?,?,?,?)");
			    ps.setString(1,cod_enc_fact_fk);
			    ps.setString(2,consecutivo);
			    ps.setString(3,fecha);
			    ps.setString(4,estadoFact);

			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoCuentas>>InsertarNumeradas "+ex);
			}

		}
	
	
	
	public java.sql.ResultSet ListarUsuarios(){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT su.usu_codigo,su.usuario FROM seg_usuario su,fact_enc_factura fef WHERE su.usu_codigo=fef.cod_usuario_fk AND fef.fecha LIKE '%2014%' GROUP BY su.usuario");
        	//System.out.println("select cantidad,fecha_insercion,hora_insercion,num_soporte,fecha,restante,observacion,codigo,cod_fact_fk from cont_detalle_factura where cod_fact_fk="+cod_fact_fk+" and cantidad>0 order by codigo desc");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>ListarUsuarios "+ex);
        }	
        return rs;
    }
	public java.sql.ResultSet ListarEntidades(){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM adm_entidad WHERE estado='Activo' AND nombre_entidad!='' ORDER BY nombre_entidad");
        	//System.out.println("select cantidad,fecha_insercion,hora_insercion,num_soporte,fecha,restante,observacion,codigo,cod_fact_fk from cont_detalle_factura where cod_fact_fk="+cod_fact_fk+" and cantidad>0 order by codigo desc");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>ListarEntidades "+ex);
        }	
        return rs;
    }
	
	public void InsertarDetalleFacturaEstadoCuenta(String CodEstadoCuenta,String CodDetalleFactura){
		
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("insert into cont_detfact_estcuenta(cod_det_fact_fk,cod_est_cuenta)values(?,?)");
			    ps.setString(1,CodDetalleFactura);
			    ps.setString(2,CodEstadoCuenta);
			   
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoCuentas>>InsertarEstadoCuenta "+ex);
			}
		}
	
	public void InsertarEstadoGeneralCuenta(String cod_cuenta_fk,String cantidad,String tipo){
		
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("insert into cont_estado_general_cuenta(cod_cuenta_fk,cantidad,tipo)values(?,?,?)");
			    ps.setString(1,cod_cuenta_fk);
			    ps.setString(2,cantidad);
			    ps.setString(3,tipo);
			   
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoCuentas>>InsertarEstadoGeneralCuenta "+ex);
			}
		}
	
	public void ActualizarFacturaAbonadoPendiente(String CodFactura,String ValorPendiente,String ValorAbonado){
		//System.out.println("UPDATE cont_factura SET valor_abonado='"+ValorAbonado+"',valor_pendiente='"+ValorPendiente+"' WHERE codigo="+CodFactura+" ");
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE cont_factura SET valor_abonado='"+ValorAbonado+"',valor_pendiente='"+ValorPendiente+"' WHERE codigo="+CodFactura+" ");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	    	 System.out.println("ERROR EN MetodoCuentas>>ActualizarFacturaAbonadoPendiente "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public void ActualizarEstadoGeneralCuenta(String cod_cuenta_fk,String tipo,String cantidad){
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("update cont_estado_general_cuenta set cantidad='"+cantidad+"' where cod_cuenta_fk='"+cod_cuenta_fk+"'and tipo='"+tipo+"'");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	    	 System.out.println("ERROR EN MetodoCuentas>>ActualizarEstadoGeneralCuenta "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public void ActualizarEstadoGeneralCuentaMenos(String cod_cuenta_fk,String cantidad){
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("update cont_estado_general_cuenta set cantidad='"+cantidad+"' where cod_cuenta_fk='"+cod_cuenta_fk+"'");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	    	 System.out.println("ERROR EN MetodoCuentas>>ActualizarEstadoGeneralCuentaMenos "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public void CerrarFactura(String CodFactura,String Estado){
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("update cont_factura set estado="+Estado+" where codigo="+CodFactura+"");
	     	//System.out.println("update cont_factura set estado="+Estado+" where codigo="+CodFactura+"");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	    	 System.out.println("ERROR EN MetodoCuentas>>CerrarFactura "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public void InsertarEstadoCuenta(String cod_cuenta_fk,String cantidad,Date fecha,Time hora,String tipo){
	
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("insert into cont_estado_cuenta(cod_cuenta_fk,cantidad,fecha,hora,tipo)values(?,?,?,?,?)");
			    ps.setString(1,cod_cuenta_fk);
			    ps.setString(2,cantidad);
			    ps.setDate(3,fecha);
			    ps.setTime(4,hora);
			    ps.setString(5, tipo);
			   
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoCuentas>>InsertarEstadoCuenta "+ex);
			}
		}
	
	public void InsertarDetalleFactura(String cod_fact_fk,String cantidad,String fecha,String tipo,String restante,String num_soporte,
			String observacion,String fecha_insercion,String hora_insercion,String usuario ){
		String ValorLetra=Convertir(cantidad);
		
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("insert into cont_detalle_factura(cod_fact_fk,cantidad,fecha,tipo,restante,num_soporte,observacion,fecha_insercion,hora_insercion,usuario,valorletras)values(?,?,?,?,?,?,?,?,?,?,?)");
			    ps.setString(1,cod_fact_fk);
			    ps.setString(2,cantidad);
			    ps.setString(3,fecha);
			    ps.setString(4,tipo);
			    ps.setString(5,restante);
			    ps.setString(6,num_soporte);
			    ps.setString(7,observacion);
			    ps.setString(8,fecha_insercion);
			    ps.setString(9,hora_insercion);
			    ps.setString(10,usuario);
			    ps.setString(11, ValorLetra);
			    
			   
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoCuentas>>InsertarDetalleFactura "+ex);
			}
		}
	
	public java.sql.ResultSet TodosCamposFactura(String cod_fact){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select * from cont_factura where numero_factura='"+cod_fact+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>TodosCamposFactura "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet CamposEncabezado(String cod_enc){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM fact_enc_factura WHERE cod_enc_factura='"+cod_enc+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>CamposEncabezado "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet RestanteFactura(String cod_fact_fk){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT IFNULL(SUM(cdf.cantidad),0) AS restante,cf.precio_factura FROM cont_detalle_factura cdf,cont_factura cf WHERE cdf.cod_fact_fk="+cod_fact_fk+"  AND cf.codigo=cdf.cod_fact_fk");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>RestanteFactura "+ex);
        }	
        return rs;
    }
	
	/*public java.sql.ResultSet ObtenerUltimoDetalleFacturaLimite1(String cod_fact_fk){	
		
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select cantidad,fecha_insercion,hora_insercion,num_soporte,fecha,restante from cont_detalle_factura where cod_fact_fk="+cod_fact_fk+" order by codigo desc limit 1");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>ObtenerUltimoDetalleFacturaLimite1 "+ex);
        }	
        return rs;
    }*/
	
	
	public java.sql.ResultSet BuscarBancos(){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM cont_bancos WHERE  estado='Activo' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>BuscarBancos "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet ObtenerMovimientosCredito(){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM cont_movimientos_credito");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>ObtenerMovimientosCredito "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerCuentaEstadoGeneral(String cod_cuenta_fk, String tipo){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo from cont_estado_general_cuenta where cod_cuenta_fk="+cod_cuenta_fk+" and tipo="+tipo+"");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>ObtenerCuentaEstadoGeneral "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet FacturaEnRecibodeCaja(String CodFactura){	
		//System.out.println("SELECT * FROM cont_detalle_recibo_caja WHERE cod_factura_fk="+CodFactura+" ");
		System.out.println("SELECT drc.*,crc.fecha,crc.obsevacion FROM cont_detalle_recibo_caja drc,cont_recibo_caja crc WHERE crc.codigo=drc.cod_recibo_caja_fk AND drc.cod_factura_fk="+CodFactura+"  ");
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT drc.*,crc.fecha,crc.obsevacion FROM cont_detalle_recibo_caja drc,cont_recibo_caja crc WHERE crc.codigo=drc.cod_recibo_caja_fk AND drc.cod_factura_fk="+CodFactura+"  ");
        	System.out.println("SELECT drc.*,crc.fecha,crc.obsevacion FROM cont_detalle_recibo_caja drc,cont_recibo_caja crc WHERE crc.codigo=drc.cod_recibo_caja_fk AND drc.cod_factura_fk="+CodFactura+"  ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>FacturaEnRecibodeCaja "+ex);
        }	
        return rs;
    }
	

	
	public java.sql.ResultSet ReportesGlosas(String CodDetFact){	
		
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT cdf.codigo,cf.numero_factura,cf.precio_factura,ccf.tipo_pago FROM cont_complemento_factura ccf,cont_factura cf,cont_detalle_factura cdf WHERE ccf.cod_det_factura_fk=cdf.codigo AND ccf.cod_factura_fk=cf.codigo AND ccf.tipo_pago!=0 AND cdf.codigo="+CodDetFact+"");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>ReportesGlosas "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet ConsultarPrecioFactura(String cod_fact){	
		
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select * from cont_factura where codigo="+cod_fact+"");
        	//System.out.println("select * from cont_factura where codigo="+cod_fact+"");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>ConsultarPrecioFactura "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet ObtenerDetalleFactura(String cod_fact_fk){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select cantidad,fecha_insercion,hora_insercion,num_soporte,fecha,restante,observacion,codigo,cod_fact_fk from cont_detalle_factura where cod_fact_fk="+cod_fact_fk+" and cantidad>0 order by codigo desc");
        	//System.out.println("select cantidad,fecha_insercion,hora_insercion,num_soporte,fecha,restante,observacion,codigo,cod_fact_fk from cont_detalle_factura where cod_fact_fk="+cod_fact_fk+" and cantidad>0 order by codigo desc");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>ObtenerUltimoDetalleFactura "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerDetalleEstadoCuenta(String cod_cuenta_fk){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select cantidad from cont_estado_cuenta where cod_cuenta_fk="+cod_cuenta_fk+" order by codigo desc");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>ObtenerDetalleEstadoCuenta "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarCuentasReciboCajaDetalle(String CodRecCaja){			
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT ctp.FormaPago,ccr.valor,cb.nombre AS nombre_banco, cb.cod_cuenta_fk AS cuenta_banco ,ccr.tipo,ccu.codigo AS Otr_cuenta,ccu.NombreCuenta FROM cont_complemento_recibo_caja ccr,cont_recibo_caja crc,cont_tipo_pago ctp,cont_bancos cb,cont_cuentas ccu WHERE crc.codigo = ccr.CodReciboCaja_fk AND ctp.codigo = ccr.CodFormaPago_fk AND crc.codigo = '"+CodRecCaja+"' AND cb.codigo = ccr.codBanco_fk  AND ccu.codigo=ctp.cuenta_fk ");
        	//System.out.println("SELECT * FROM cont_cartera_plazo WHERE numero_factura='"+NumeroFactura+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>BuscarCuentasReciboCajaDetalle "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarFacturasEnCarteraPlazo(String NumeroFactura){			
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM cont_cartera_plazo WHERE numero_factura='"+NumeroFactura+"' ");
        	//System.out.println("SELECT * FROM cont_cartera_plazo WHERE numero_factura='"+NumeroFactura+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>BuscarFacturasEnCarteraPlazo "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerFacturasFaltantes(){			
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM facturas_faltantes  ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>ObtenerFacturasFaltantes "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerFacturasRadicadas(){			
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM radicacion_temporal  ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>ObtenerFacturas "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ListarCuentaEmpresas(){			
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT cc.codigo,cc.NombreCuenta FROM cont_cuentas_empresas cc WHERE cc.nit IS NOT NULL ORDER BY cc.NombreCuenta");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>ListarCuentaEmpresas "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerFacturasCuenta(String CodCuentaEmpresa){	
		System.out.println("SELECT * FROM cont_factura where cod_cuenta_fk="+CodCuentaEmpresa+" ");
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM cont_factura where cod_cuenta_fk="+CodCuentaEmpresa+" ");
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>ObtenerFacturas "+ex);
        }	
        return rs;
    }

	public java.sql.ResultSet ObtenerFacturas(){			
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM cont_factura ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>ObtenerFacturas "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerCantidadEstadoCuenta(String cod_cuenta_fk){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select cantidad,codigo from cont_estado_general_cuenta where cod_cuenta_fk="+cod_cuenta_fk+"");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>ObtenerCantidadEstadoCuenta "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarEstadoCuentaFechaHora(Date fecha,Time hora){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo from cont_estado_cuenta where fecha='"+fecha+"' and hora='"+hora+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>BuscarEstadoCuenta "+ex);
        }	
        return rs;
    }
	
	
	
	public java.sql.ResultSet BuscarDetalleFacturaFechaHora(String fecha,String hora,String CodFactura){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo from cont_detalle_factura where fecha_insercion='"+fecha+"' and hora_insercion='"+hora+"' and cod_fact_fk="+CodFactura+"");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>BuscarDetalleFactura "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet RelacionRecibocajaFactura(String CodRecCaja,String CodFactura){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM cont_detalle_recibo_caja WHERE cod_recibo_caja_fk="+CodRecCaja+" AND cod_factura_fk="+CodFactura+"");
        	System.out.println("SELECT * FROM cont_detalle_recibo_caja WHERE cod_recibo_caja_fk="+CodRecCaja+" AND cod_factura_fk="+CodFactura+"");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>CargarCuentaFactura "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerNitEmpresa(String Nit){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM cont_cuentas_empresas WHERE nit="+Nit+" ");
        	//System.out.println("select cf.codigo,cc.CodigoCuenta,cc.NombreCuenta,cf.fecha_factura,cf.numero_factura,cf.precio_factura,cf.iva,cf.ret_ica,cf.valor_abonado,cf.valor_pendiente from cont_factura cf,cont_cuentas_empresas cc where cc.codigo=cf.cod_cuenta_fk  and cf.codigo="+CodFactura+" order by cf.fecha_factura   ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>ObtenerNitEmpresa "+ex);
        }	
        return rs;
    }
	
	
	/*
	 *
	 */
	
	public java.sql.ResultSet ConsultarValoresEntidades(String FechaIN,String FechaFI){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT SUM(co.cantidades) AS cantidad,SUM(co.efectivo) AS Pagado,SUM(co.moderacion) AS AsumidoESE,co.* FROM(SELECT SUM((fdf.cantidad * fdf.valor)) AS cantidades,SUM(fef.moderacion) AS AsumidoESEs,SUM(fef.efectivo) AS Pagados,fef.efectivo,fef.moderacion,fef.razon_social FROM fact_enc_factura fef,fact_numeradas fn, fact_det_factura fdf WHERE fdf.cod_enc_factura_fk = fef.cod_enc_factura  AND fef.cod_enc_factura = fn.cod_enc_fact_fk   AND fef.fecha BETWEEN '"+FechaIN+"' AND '"+FechaFI+"' AND fef.estado='1' GROUP BY fn.consecutivo ORDER BY fef.razon_social) AS co GROUP BY co.razon_social");
        	//rs=st.executeQuery("select SUM((fdf.cantidad*fdf.valor)) as cantidad,sum(fef.moderacion) as AsumidoESE,sum(fef.efectivo) as Pagado,fef.razon_social from fact_enc_factura fef,fact_numeradas fn,fact_det_factura fdf  where fdf.cod_enc_factura_fk=fef.cod_enc_factura and  fef.cod_enc_factura=fn.cod_enc_fact_fk and fef.fecha between '"+FechaIN+"' and '"+FechaFI+"' and fef.estado='1' and fef.cod_eps='"+Cod_eps+"' group by fef.cod_eps order by fef.razon_social");
            //System.out.println("SELECT SUM(co.cantidades) AS cantidad,SUM(co.efectivo) AS Pagado,SUM(co.moderacion) AS AsumidoESE,co.* FROM(SELECT SUM((fdf.cantidad * fdf.valor)) AS cantidades,SUM(fef.moderacion) AS AsumidoESEs,SUM(fef.efectivo) AS Pagados,fef.efectivo,fef.moderacion,fef.razon_social FROM fact_enc_factura fef,fact_numeradas fn, fact_det_factura fdf WHERE fdf.cod_enc_factura_fk = fef.cod_enc_factura  AND fef.cod_enc_factura = fn.cod_enc_fact_fk   AND fef.fecha BETWEEN '"+FechaIN+"' AND '"+FechaFI+"' AND fef.estado='1' AND fef.cod_eps='"+Cod_eps+"' GROUP BY fn.consecutivo ORDER BY fef.razon_social) AS co GROUP BY co.razon_social");
        	System.out.println("SELECT SUM(co.cantidades) AS cantidad,SUM(co.efectivo) AS Pagado,SUM(co.moderacion) AS AsumidoESE,co.* FROM(SELECT SUM((fdf.cantidad * fdf.valor)) AS cantidades,SUM(fef.moderacion) AS AsumidoESEs,SUM(fef.efectivo) AS Pagados,fef.efectivo,fef.moderacion,fef.razon_social FROM fact_enc_factura fef,fact_numeradas fn, fact_det_factura fdf WHERE fdf.cod_enc_factura_fk = fef.cod_enc_factura  AND fef.cod_enc_factura = fn.cod_enc_fact_fk   AND fef.fecha BETWEEN '"+FechaIN+"' AND '"+FechaFI+"' AND fef.estado='1' GROUP BY fn.consecutivo ORDER BY fef.razon_social) AS co GROUP BY co.razon_social");
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>CargarCuentaFactura "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ConsultarFacturadoCentroCostoD(String FechaIN,String FechaFI,String razon_social){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT fef.cod_eps,fef.razon_social,fdf.nombre_programa,fdf.nombre_paquete,fsc.descripcion AS SubcentroCosto,fdf.clase_servicio,SUM(fdf.cantidad) AS cantidad,fdf.valor as ValPro,SUM((fdf.cantidad*fdf.valor)) AS Valor  FROM fact_enc_factura fef,fact_numeradas fn,fact_det_factura fdf,fact_programas fp,fact_subcentros_costo fsc WHERE fef.cod_enc_factura=fn.cod_enc_fact_fk AND fef.cod_enc_factura=fdf.cod_enc_factura_fk AND fef.fecha BETWEEN '"+FechaIN+"' AND '"+FechaFI+"' AND fef.estado='1' AND fef.razon_social='"+razon_social+"' AND fp.cod_programa=fdf.cod_programafk AND fsc.cod_subcentro_costo=fp.subcentro_costo GROUP BY fef.cod_eps,fdf.nombre_programa,fdf.valor,fdf.nombre_paquete ORDER BY fsc.descripcion");
            //System.out.println("SELECT fef.cod_eps,fef.razon_social,fdf.nombre_programa,fdf.nombre_paquete,fsc.descripcion AS SubcentroCosto,fdf.clase_servicio,SUM(fdf.cantidad) AS cantidad,fdf.valor as ValPro,SUM((fdf.cantidad*fdf.valor)) AS Valor  FROM fact_enc_factura fef,fact_numeradas fn,fact_det_factura fdf,fact_programas fp,fact_subcentros_costo fsc WHERE fef.cod_enc_factura=fn.cod_enc_fact_fk AND fef.cod_enc_factura=fdf.cod_enc_factura_fk AND fef.fecha BETWEEN '"+FechaIN+"' AND '"+FechaFI+"' AND fef.estado='1' AND fef.cod_eps='"+Cod_eps+"' AND fp.cod_programa=fdf.cod_programafk AND fsc.cod_subcentro_costo=fp.subcentro_costo GROUP BY fef.cod_eps,fdf.nombre_programa,fdf.valor,fdf.nombre_paquete ORDER BY fsc.descripcion");
            System.out.println("SELECT fef.cod_eps,fef.razon_social,fdf.nombre_programa,fdf.nombre_paquete,fsc.descripcion AS SubcentroCosto,fdf.clase_servicio,SUM(fdf.cantidad) AS cantidad,fdf.valor as ValPro,SUM((fdf.cantidad*fdf.valor)) AS Valor  FROM fact_enc_factura fef,fact_numeradas fn,fact_det_factura fdf,fact_programas fp,fact_subcentros_costo fsc WHERE fef.cod_enc_factura=fn.cod_enc_fact_fk AND fef.cod_enc_factura=fdf.cod_enc_factura_fk AND fef.fecha BETWEEN '"+FechaIN+"' AND '"+FechaFI+"' AND fef.estado='1' AND fef.razon_social='"+razon_social+"' AND fp.cod_programa=fdf.cod_programafk AND fsc.cod_subcentro_costo=fp.subcentro_costo GROUP BY fef.cod_eps,fdf.nombre_programa,fdf.valor,fdf.nombre_paquete ORDER BY fsc.descripcion");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>CargarCuentaFactura "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ConsultarFacturadoCentroCosto(String FechaIN,String FechaFI,String razon_social){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT fef.cod_eps,fef.razon_social,fsc.descripcion,SUM((fdf.cantidad*fdf.valor)) AS Valor FROM fact_enc_factura fef,fact_numeradas fn,fact_det_factura fdf,fact_programas fp,fact_subcentros_costo fsc WHERE fef.cod_enc_factura=fn.cod_enc_fact_fk AND fef.cod_enc_factura=fdf.cod_enc_factura_fk AND fef.fecha BETWEEN '"+FechaIN+"' AND '"+FechaFI+"' AND fef.estado='1' AND fef.razon_social='"+razon_social+"' AND fp.cod_programa=fdf.cod_programafk AND fsc.cod_subcentro_costo=fp.subcentro_costo GROUP BY fef.cod_eps,fsc.descripcion ORDER BY fef.razon_social");
            //System.out.println("SELECT fef.cod_eps,fef.razon_social,fsc.descripcion,SUM((fdf.cantidad*fdf.valor)) AS Valor FROM fact_enc_factura fef,fact_numeradas fn,fact_det_factura fdf,fact_programas fp,fact_subcentros_costo fsc WHERE fef.cod_enc_factura=fn.cod_enc_fact_fk AND fef.cod_enc_factura=fdf.cod_enc_factura_fk AND fef.fecha BETWEEN '"+FechaIN+"' AND '"+FechaFI+"' AND fef.estado='1' AND fef.cod_eps='"+Cod_eps+"' AND fp.cod_programa=fdf.cod_programafk AND fsc.cod_subcentro_costo=fp.subcentro_costo GROUP BY fef.cod_eps,fsc.descripcion ORDER BY fef.razon_social");
        	System.out.println("SELECT fef.cod_eps,fef.razon_social,fsc.descripcion,SUM((fdf.cantidad*fdf.valor)) AS Valor FROM fact_enc_factura fef,fact_numeradas fn,fact_det_factura fdf,fact_programas fp,fact_subcentros_costo fsc WHERE fef.cod_enc_factura=fn.cod_enc_fact_fk AND fef.cod_enc_factura=fdf.cod_enc_factura_fk AND fef.fecha BETWEEN '"+FechaIN+"' AND '"+FechaFI+"' AND fef.estado='1' AND fef.razon_social='"+razon_social+"' AND fp.cod_programa=fdf.cod_programafk AND fsc.cod_subcentro_costo=fp.subcentro_costo GROUP BY fef.cod_eps,fsc.descripcion ORDER BY fef.razon_social");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>CargarCuentaFactura "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ConsultarEntidadesL(String Periodo){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT razon_social,cod_eps FROM fact_enc_factura  where estado='1' and fecha like '%"+Periodo+"%' group by cod_eps ORDER BY razon_social ");
            System.out.println("SELECT razon_social,cod_eps FROM fact_enc_factura  where estado='1' and fecha like '%"+Periodo+"%' group by cod_eps ORDER BY razon_social ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>CargarCuentaFactura "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ConsultarEntidades(String FechaIni, String FechaFin){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT razon_social,cod_eps FROM fact_enc_factura  where estado='1' and fecha between '"+FechaIni+"' and '"+FechaFin+"' group by razon_social ORDER BY razon_social ");
            System.out.println("SELECT razon_social,cod_eps FROM fact_enc_factura  where estado='1' and fecha between '"+FechaIni+"' and '"+FechaFin+"' group by razon_social ORDER BY razon_social ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>CargarCuentaFactura "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ConsultarEntidadesF(String FechaIN,String FechaFN){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT razon_social,cod_eps FROM fact_enc_factura  where estado='1' and fecha between '"+FechaIN+"' and '"+FechaFN+"' group by razon_social ORDER BY razon_social ");
            System.out.println("SELECT razon_social,cod_eps FROM fact_enc_factura  where estado='1' and fecha between '"+FechaIN+"' and '"+FechaFN+"' group by razon_social ORDER BY razon_social ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>CargarCuentaFactura "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet ConsultarFacturadof(String FechaInicial,String FechaFinal,String cod_eps){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT fef.razon_social,SUM((fdf.valor*fdf.cantidad)) as Facturado,fef.cod_eps FROM fact_enc_factura fef,fact_numeradas fn,fact_det_factura fdf  where fdf.cod_enc_factura_fk=fef.cod_enc_factura and fef.cod_enc_factura=fn.cod_enc_fact_fk and  fef.fecha between '"+FechaInicial+"' and '"+FechaFinal+"' and fef.estado='1' and fef.cod_eps='"+cod_eps+"' group by cod_eps order by fef.razon_social ");
            System.out.println("SELECT fef.razon_social,SUM((fdf.valor*fdf.cantidad)) as Facturado,fef.cod_eps FROM fact_enc_factura fef,fact_numeradas fn,fact_det_factura fdf  where fdf.cod_enc_factura_fk=fef.cod_enc_factura and fef.cod_enc_factura=fn.cod_enc_fact_fk and  fef.fecha between '"+FechaInicial+"' and '"+FechaFinal+"' and fef.estado='1' and fef.cod_eps='"+cod_eps+"' group by cod_eps order by fef.razon_social ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>ConsultarFacturado "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ConsultarFacturado(String Periodo,String cod_eps){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT fef.razon_social,SUM((fdf.valor*fdf.cantidad)) as Facturado,fef.cod_eps FROM fact_enc_factura fef,fact_numeradas fn,fact_det_factura fdf  where fdf.cod_enc_factura_fk=fef.cod_enc_factura and fef.cod_enc_factura=fn.cod_enc_fact_fk and fef.fecha like '%"+Periodo+"%' and fef.estado='1' and fef.cod_eps='"+cod_eps+"' group by cod_eps order by fef.razon_social ");
            System.out.println("SELECT fef.razon_social,SUM((fdf.valor*fdf.cantidad)) as Facturado,fef.cod_eps FROM fact_enc_factura fef,fact_numeradas fn,fact_det_factura fdf  where fdf.cod_enc_factura_fk=fef.cod_enc_factura and fef.cod_enc_factura=fn.cod_enc_fact_fk and fef.fecha like '%"+Periodo+"%' and fef.estado='1' and fef.cod_eps='"+cod_eps+"' group by cod_eps order by fef.razon_social ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>ConsultarFacturado "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet ConsultarRadicadof(String FechaInicial,String FechaFinal,String cod_eps){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT fef.razon_social,SUM(fef.valor) AS Radicado FROM fact_facturas_radicadas ffr,fact_numeradas fn,fact_enc_factura fef WHERE fef.cod_enc_factura=fn.cod_enc_fact_fk AND fn.cod_fact_numerada=ffr.codFact AND fef.fecha between '"+FechaInicial+"' and '"+FechaFinal+"' AND fef.cod_eps='"+cod_eps+"' GROUP BY fef.cod_eps ORDER BY razon_social");
            System.out.println("SELECT fef.razon_social,SUM(fef.valor) AS Radicado FROM fact_facturas_radicadas ffr,fact_numeradas fn,fact_enc_factura fef WHERE fef.cod_enc_factura=fn.cod_enc_fact_fk AND fn.cod_fact_numerada=ffr.codFact AND fef.fecha between '"+FechaInicial+"' and '"+FechaFinal+"' AND fef.cod_eps='"+cod_eps+"' GROUP BY fef.cod_eps ORDER BY razon_social");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>ConsultarRadicado "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ConsultarRadicado(String Periodo,String cod_eps){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT fef.razon_social,SUM(fef.valor) AS Radicado FROM fact_facturas_radicadas ffr,fact_numeradas fn,fact_enc_factura fef WHERE fef.cod_enc_factura=fn.cod_enc_fact_fk AND fn.cod_fact_numerada=ffr.codFact AND fef.fecha LIKE '%"+Periodo+"%' AND fef.cod_eps='"+cod_eps+"' GROUP BY fef.cod_eps ORDER BY razon_social");
            System.out.println("SELECT fef.razon_social,SUM(fef.valor) AS Radicado FROM fact_facturas_radicadas ffr,fact_numeradas fn,fact_enc_factura fef WHERE fef.cod_enc_factura=fn.cod_enc_fact_fk AND fn.cod_fact_numerada=ffr.codFact AND fef.fecha LIKE '%"+Periodo+"%' AND fef.cod_eps='"+cod_eps+"' GROUP BY fef.cod_eps ORDER BY razon_social");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>ConsultarRadicado "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet CargarCuentaFactura(String CodFactura){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select cf.codigo,cc.CodigoCuenta,cc.NombreCuenta,cf.fecha_factura,cf.numero_factura,cf.precio_factura,cf.iva,cf.ret_ica,cf.valor_abonado,cf.valor_pendiente from cont_factura cf,cont_cuentas_empresas cc where cc.codigo=cf.cod_cuenta_fk  and cf.codigo="+CodFactura+" order by cf.fecha_factura   ");
        	//System.out.println("select cf.codigo,cc.CodigoCuenta,cc.NombreCuenta,cf.fecha_factura,cf.numero_factura,cf.precio_factura,cf.iva,cf.ret_ica,cf.valor_abonado,cf.valor_pendiente from cont_factura cf,cont_cuentas_empresas cc where cc.codigo=cf.cod_cuenta_fk  and cf.codigo="+CodFactura+" order by cf.fecha_factura   ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>CargarCuentaFactura "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet TodosCuentasEmpresa(){	
		
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM cont_cuentas_empresas WHERE NombreCuenta!='' ORDER BY NombreCuenta");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>TodosCuentasEmpresa "+ex);
        }	
        return rs;
    }
	
public java.sql.ResultSet BuscarCarteraEntidad(String CodEps){	
	System.out.println("SELECT * FROM (SELECT (plazo_corto+plazo_30+plazo_60+plazo_90+plazo_180+plazo_360+por_vencer) AS total_fact,cf.fecha_factura,aen.nombre_entidad,aen.telefono telent,aen.direccion dirent,aen.ent_nit_contratante,ccp.* " +
			" FROM cont_cartera_plazo ccp,cont_factura cf,cont_cuentas_empresas cc,adm_entidad aen " +
			" WHERE aen.ent_nit=cc.nit " +
			" AND cc.codigo=cf.cod_cuenta_fk  " +
			" AND cc.codigo="+CodEps+"AND cf.codigo=ccp.cod_factura_fk AND cf.precio_factura>0  ORDER BY cf.numero_factura) AS a WHERE a.total_fact>0 ");

		
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM (SELECT (plazo_corto+plazo_30+plazo_60+plazo_90+plazo_180+plazo_360+por_vencer) AS total_fact,cf.fecha_factura,aen.nombre_entidad,aen.telefono telent,aen.direccion dirent,aen.ent_nit_contratante,ccp.* " +
        			" FROM cont_cartera_plazo ccp,cont_factura cf,cont_cuentas_empresas cc,adm_entidad aen " +
        			" WHERE aen.ent_nit=cc.nit " +
        			" AND cc.codigo=cf.cod_cuenta_fk  " +
        			" AND cc.codigo="+CodEps+" AND cf.codigo=ccp.cod_factura_fk AND cf.precio_factura>0  ORDER BY cf.numero_factura) AS a WHERE a.total_fact>0 ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>BuscarCarteraEntidad "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet SincronizarCarteraGeneral(){	
		
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM (SELECT (plazo_corto+plazo_30+plazo_60+plazo_90+plazo_180+plazo_360+por_vencer) AS total_fact,cf.fecha_factura,aen.nombre_entidad,aen.telefono telent,aen.direccion dirent,aen.ent_nit_contratante,ccp.* " +
        			" FROM cont_cartera_plazo ccp,cont_factura cf,cont_cuentas_empresas cc,adm_entidad aen " +
        			" WHERE aen.ent_nit=cc.nit " +
        			" AND cc.codigo=cf.cod_cuenta_fk  " +
        			" AND cf.codigo=ccp.cod_factura_fk AND cf.precio_factura>0  ORDER BY cf.numero_factura) AS a WHERE a.total_fact>0 ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>SincronizarCarteraDetallada "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet BuscarFacturaSinDetalle(String CodFactura){	
		
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM (SELECT (plazo_corto+plazo_30+plazo_60+plazo_90+plazo_180+plazo_360+por_vencer) AS total_fact,cf.fecha_factura,aen.nombre_entidad,aen.telefono telent,aen.direccion dirent,aen.ent_nit_contratante,ccp.* " +
        			" FROM cont_cartera_plazo ccp,cont_factura cf,cont_cuentas_empresas cc,adm_entidad aen " +
        			" WHERE aen.ent_nit=cc.nit " +
        			" AND cc.codigo=cf.cod_cuenta_fk and cf.codigo="+CodFactura+" " +
        			" AND cf.codigo=ccp.cod_factura_fk AND cf.precio_factura>0  ORDER BY cf.numero_factura) AS a WHERE a.total_fact>0 ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>BuscarFacturaSinDetalle "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet SincronizarCarteraDetallada(String CodEnt){	
		
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM (SELECT (plazo_corto+plazo_30+plazo_60+plazo_90+plazo_180+plazo_360+por_vencer) AS total_fact,cf.fecha_factura,aen.nombre_entidad,aen.telefono telent,aen.direccion dirent,aen.ent_nit_contratante,ccp.* " +
        			" FROM cont_cartera_plazo ccp,cont_factura cf,cont_cuentas_empresas cc,adm_entidad aen " +
        			" WHERE aen.ent_nit=cc.nit " +
        			" AND cc.codigo=cf.cod_cuenta_fk " +
        			" AND cf.codigo=ccp.cod_factura_fk AND aen.ent_nit="+CodEnt+" AND cf.precio_factura>0  ORDER BY cf.numero_factura) AS a WHERE a.total_fact>0 ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>SincronizarCarteraDetallada "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet NumeroDiasFecha(String FechaFactura){	
		//System.out.println("SELECT TO_DAYS('"+FechaFactura+"')");
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT TO_DAYS('"+FechaFactura+"')");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>NumeroDiasFecha "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet NumeroDiasFechaActual(){	
	//	System.out.println("SELECT TO_DAYS(CURDATE())");
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT TO_DAYS(CURDATE())");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>NumeroDiasFechaActual "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ResumenRestanteFactura(String CodFactura){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT cf.codigo,cf.numero_factura, cf.precio_factura, " +
        			" (cf.precio_factura-(SELECT (SUM(cdf.cantidad)) FROM cont_factura cf,cont_detalle_factura cdf " +
        			" WHERE cf.codigo = cdf.cod_fact_fk AND cf.codigo="+CodFactura+" )) restante_fact,cdf.fecha,cf.fecha_factura  " +
        					" FROM  cont_factura cf, cont_detalle_factura cdf  " +
        					" WHERE cf.codigo = cdf.cod_fact_fk AND cf.codigo="+CodFactura+"  " +
        							" GROUP BY cdf.codigo ORDER BY cdf.codigo DESC LIMIT 1 ");
        	
        	/*System.out.println(" SELECT cf.codigo,cf.numero_factura, cf.precio_factura, " +
        			" (cf.precio_factura-(SELECT (SUM(cdf.cantidad)) FROM cont_factura cf,cont_detalle_factura cdf " +
        			" WHERE cf.codigo = cdf.cod_fact_fk AND cf.codigo="+CodFactura+" )) restante_fact,cdf.fecha,cf.fecha_factura  " +
        					" FROM  cont_factura cf, cont_detalle_factura cdf  " +
        					" WHERE cf.codigo = cdf.cod_fact_fk AND cf.codigo="+CodFactura+"  " +
        							" GROUP BY cdf.codigo ORDER BY cdf.codigo DESC LIMIT 1 ");*/
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>ResumenRestanteFactura "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet FacturasEntidad(String CodEnt){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT cf.codigo,cf.numero_factura,cf.precio_factura,cf.fecha_factura " +
        			"FROM cont_factura cf,cont_detalle_factura cdf,cont_cuentas_empresas ccu,adm_entidad aen " +
        			"WHERE cf.codigo=cdf.cod_fact_fk AND ccu.codigo=cf.cod_cuenta_fk  " +
        			"AND aen.ent_nit=ccu.nit AND ccu.nit="+CodEnt+"");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>FacturasEntidad "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet VerificarInsercion(String Fecha,String Hora){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM cont_nota_debito WHERE fecha_ingreso='"+Fecha+"' AND hora_ingreso='"+Hora+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>VerificarInsercion "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ListaNotasDebito(String CodFactura){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT cnb.codigo,fn.consecutivo,fef.razon_social,cnb.fecha_ingreso,cnb.ValorND,cnb.fecha_nota FROM cont_nota_debito cnb,fact_enc_factura fef,fact_numeradas fn WHERE cnb.CodEncFactura = '"+CodFactura+"' AND fef.cod_enc_factura=cnb.CodEncFactura AND fn.cod_enc_fact_fk=fef.cod_enc_factura ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>FacturaND "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet FacturaND(String CodFactura){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT fn.consecutivo,fef.razon_social,fef.nit,(fef.valor-fef.efectivo) AS ValorFactura FROM fact_numeradas fn,fact_enc_factura fef WHERE fn.cod_enc_fact_fk='"+CodFactura+"' AND fef.cod_enc_factura=fn.cod_enc_fact_fk");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>FacturaND "+ex);
        }	
        return rs;
    }
	

	
	public java.sql.ResultSet CargarCuenta(String CodCuenta){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select cf.codigo,cc.CodigoCuenta,cc.NombreCuenta,cf.fecha_factura,cf.numero_factura,cf.precio_factura,cf.iva,cf.ret_ica from cont_factura cf,cont_cuentas_empresas cc where cc.codigo=cf.cod_cuenta_fk and cf.estado=0 and cc.CodigoCuenta="+CodCuenta+" order by cf.fecha_factura   ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>CargarCuenta "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarDatosParaFactura(String CodEnca){	
		
		//System.out.println("Error en MetodoCuentas>>BuscarDatosParaFactura ");
		//System.out.println("SELECT ccu.CodigoCuenta,fnu.consecutivo,fef.valor,fef.fecha,fef.razon_social,fef.nit,CURDATE() AS fecha,CURTIME() AS hora,ccu.codigo FROM fact_enc_factura fef,adm_admisiones aad,cont_cuentas_empresas ccu,fact_numeradas fnu,adm_entidad aent WHERE fef.cod_admision=aad.adm_numero_ingreso AND ccu.nit=aent.ent_nit AND fnu.cod_enc_fact_fk=fef.cod_enc_factura AND fef.cod_enc_factura="+CodEnca+" AND fef.cod_eps=ccu.nit");
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT ccu.CodigoCuenta,fnu.consecutivo,fef.valor,fef.fecha,fef.razon_social,fef.nit,CURDATE() AS fecha,CURTIME() AS hora,ccu.codigo FROM fact_enc_factura fef,adm_admisiones aad,cont_cuentas_empresas ccu,fact_numeradas fnu,adm_entidad aent WHERE fef.cod_admision=aad.adm_numero_ingreso AND ccu.nit=aent.ent_nit AND fnu.cod_enc_fact_fk=fef.cod_enc_factura AND fef.cod_enc_factura="+CodEnca+" AND fef.cod_eps=ccu.nit AND fnu.estadoFact!=5");
        	System.out.println("SELECT ccu.CodigoCuenta,fnu.consecutivo,fef.valor,fef.fecha,fef.razon_social,fef.nit,CURDATE() AS fecha,CURTIME() AS hora,ccu.codigo FROM fact_enc_factura fef,adm_admisiones aad,cont_cuentas_empresas ccu,fact_numeradas fnu,adm_entidad aent WHERE fef.cod_admision=aad.adm_numero_ingreso AND ccu.nit=aent.ent_nit AND fnu.cod_enc_fact_fk=fef.cod_enc_factura AND fef.cod_enc_factura="+CodEnca+" AND fef.cod_eps=ccu.nit AND fnu.estadoFact!=5");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>BuscarDatosParaFactura "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet DatosFacturaDetalle(String NumeroFactura){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM cont_factura WHERE numero_factura = '"+NumeroFactura+"' ");
        	System.out.println("SELECT * FROM cont_factura WHERE numero_factura = '"+NumeroFactura+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>BuscarDatosParaFactura "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarDatosEmpresa(String CodEnt){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT  * FROM adm_entidad WHERE ent_nit='"+CodEnt+"'");
        	//System.out.println("SELECT  * FROM adm_entidad WHERE ent_nit='"+CodEnt+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>BuscarDatosEmpresa "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ValidarFacturaExiste(String consecutivo){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT fn.* FROM fact_numeradas fn WHERE fn.consecutivo LIKE '%"+consecutivo+"%' ");
        	System.out.println("SELECT fn.* FROM fact_numeradas fn WHERE fn.consecutivo LIKE '%"+consecutivo+"%' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>ValidarFacturaExiste "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerNit(String CodEps){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT ent_nit_contratante FROM adm_entidad WHERE ent_nit='"+CodEps+"'");
        	//System.out.println("SELECT cf.numero_factura,SUM(cdf.cantidad) AS Deducido,cf.precio_factura,(cf.precio_factura-(SUM(cdf.cantidad))) saldo FROM cont_factura cf,cont_detalle_factura cdf WHERE cf.codigo=cdf.cod_fact_fk AND cf.numero_factura LIKE '%"+consecutivo+"%' GROUP BY cf.numero_factura");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>ObtenerNit "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ValidarFacturaEmpresaNit(String consecutivo,String CodEps){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT fef.* FROM fact_enc_factura fef,fact_numeradas fn WHERE fef.cod_enc_factura=fn.cod_enc_fact_fk AND fn.consecutivo LIKE '%"+consecutivo+"%' AND fef.nit like '%"+CodEps+"%' ");
        	//System.out.println("SELECT cf.numero_factura,SUM(cdf.cantidad) AS Deducido,cf.precio_factura,(cf.precio_factura-(SUM(cdf.cantidad))) saldo FROM cont_factura cf,cont_detalle_factura cdf WHERE cf.codigo=cdf.cod_fact_fk AND cf.numero_factura LIKE '%"+consecutivo+"%' GROUP BY cf.numero_factura");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>ValidarFacturaEmpresa "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ValidarFacturaEmpresa(String consecutivo,String CodEps){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT fef.* FROM fact_enc_factura fef,fact_numeradas fn WHERE fef.cod_enc_factura=fn.cod_enc_fact_fk AND fn.consecutivo LIKE '%"+consecutivo+"%' AND fef.cod_eps='"+CodEps+"' ");
        	//System.out.println("SELECT cf.numero_factura,SUM(cdf.cantidad) AS Deducido,cf.precio_factura,(cf.precio_factura-(SUM(cdf.cantidad))) saldo FROM cont_factura cf,cont_detalle_factura cdf WHERE cf.codigo=cdf.cod_fact_fk AND cf.numero_factura LIKE '%"+consecutivo+"%' GROUP BY cf.numero_factura");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>ValidarFacturaEmpresa "+ex);
        }	
        return rs;
    }
	

	
	public java.sql.ResultSet BuscarFormasPago(){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM cont_tipo_pago where tipo=1 and estado=0");
        	//System.out.println("SELECT cf.numero_factura,SUM(cdf.cantidad) AS Deducido,cf.precio_factura,(cf.precio_factura-(SUM(cdf.cantidad))) saldo FROM cont_factura cf,cont_detalle_factura cdf WHERE cf.codigo=cdf.cod_fact_fk AND cf.numero_factura LIKE '%"+consecutivo+"%' GROUP BY cf.numero_factura");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>BuscarFormasPago "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarDeducciones(){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM cont_tipo_pago where tipo=0 and estado=0");
        	//System.out.println("SELECT cf.numero_factura,SUM(cdf.cantidad) AS Deducido,cf.precio_factura,(cf.precio_factura-(SUM(cdf.cantidad))) saldo FROM cont_factura cf,cont_detalle_factura cdf WHERE cf.codigo=cdf.cod_fact_fk AND cf.numero_factura LIKE '%"+consecutivo+"%' GROUP BY cf.numero_factura");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>BuscarDeducciones "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet BuscarFactura(String consecutivo){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT cf.numero_factura,SUM(cdf.cantidad) AS Deducido,cf.precio_factura,(cf.precio_factura-(SUM(cdf.cantidad))) saldo,cf.codigo FROM cont_factura cf,cont_detalle_factura cdf WHERE cf.codigo=cdf.cod_fact_fk AND cf.numero_factura LIKE '%"+consecutivo+"%' GROUP BY cf.numero_factura");
        	//System.out.println("SELECT cf.numero_factura,SUM(cdf.cantidad) AS Deducido,cf.precio_factura,(cf.precio_factura-(SUM(cdf.cantidad))) saldo FROM cont_factura cf,cont_detalle_factura cdf WHERE cf.codigo=cdf.cod_fact_fk AND cf.numero_factura LIKE '%"+consecutivo+"%' GROUP BY cf.numero_factura");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>BuscarDatosParaFactura "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet BuscarDetalleFactura(String CodFacturaCF){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT SUM(cdf.cantidad) AS Deducido,cdf.* FROM cont_detalle_factura cdf WHERE cdf.cod_fact_fk='"+CodFacturaCF+"'");
        	System.out.println("SELECT SUM(cdf.cantidad) AS Deducido,cdf.* FROM cont_detalle_factura cdf WHERE cdf.cod_fact_fk='"+CodFacturaCF+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>BuscarDatosParaFactura "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet BuscarCargarCuenta(String TipoCuenta,String DatoBusqueda){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select cf.codigo,cc.CodigoCuenta,cc.NombreCuenta,cf.fecha_factura,cf.numero_factura,cf.precio_factura,cf.iva,cf.ret_ica from cont_factura cf,cont_cuentas_empresas cc where cc.codigo=cf.cod_cuenta_fk and cf.estado=0 and cf.tipo="+TipoCuenta+" and (cc.NombreCuenta LIKE '"+DatoBusqueda+"%' or cc.CodigoCuenta LIKE'"+DatoBusqueda+"%')  group by cc.NombreCuenta  ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>BuscarCargarCuenta "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarFacturaCobrarND(String TipoCuenta,String NumeroFactura){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT cf.cod_enc_factura,cf.razon_social,cf.fecha,cf.valor,cc.consecutivo FROM fact_enc_factura cf,fact_numeradas cc WHERE cc.cod_enc_fact_fk = cf.cod_enc_factura  AND cc.consecutivo LIKE '%"+NumeroFactura+"%'");
        	//System.out.println("SELECT cf.codigo,cc.CodigoCuenta,cc.NombreCuenta,cf.fecha_factura,cf.numero_factura,cf.precio_factura,cf.iva,'' FROM cont_factura cf,cont_cuentas_empresas cc WHERE cc.CodigoCuenta = cf.cod_cuenta_fk AND cf.numero_factura = '"+NumeroFactura+"' AND cf.estado = 0 ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>BuscarFacturaCobrar "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarFacturaCobrar(String TipoCuenta,String NumeroFactura){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT cf.codigo,cc.CodigoCuenta,cc.NombreCuenta,cf.fecha_factura,cf.numero_factura,cf.precio_factura,cf.iva,'' FROM cont_factura cf,cont_cuentas_empresas cc WHERE cc.codigo = cf.cod_cuenta_fk AND cf.numero_factura like '%"+NumeroFactura+"%' ");
        	//System.out.println("SELECT cf.codigo,cc.CodigoCuenta,cc.NombreCuenta,cf.fecha_factura,cf.numero_factura,cf.precio_factura,cf.iva,'' FROM cont_factura cf,cont_cuentas_empresas cc WHERE cc.CodigoCuenta = cf.cod_cuenta_fk AND cf.numero_factura = '"+NumeroFactura+"' AND cf.estado = 0 ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>BuscarFacturaCobrar "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet BuscarReciboCaja(Date fecha, 
			Time hora){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM cont_recibo_caja WHERE fecha='"+fecha+"' AND hora='"+hora+"' ");

        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>BuscarReciboCaja "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarSinTerminar(String CodUsu){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT crc.codigo,ctc.Concepto,en.nombre_entidad,crc.obsevacion,crc.FechaPago,en.ent_nit  FROM cont_recibo_caja crc,cont_tipo_concepto ctc,adm_entidad en WHERE crc.valor_total = '' AND crc.cod_usu_fk = "+CodUsu+" AND ctc.codigo=crc.concepto AND en.ent_nit=crc.cod_entidad_fk");
        	//System.out.println("SELECT ctp.FormaPago,ccr.valor FROM cont_complemento_recibo_caja ccr,cont_recibo_caja crc,cont_tipo_pago ctp WHERE crc.codigo=ccr.CodReciboCaja_fk AND ctp.codigo=ccr.CodFormaPago_fk AND crc.codigo="+CodRecCaja+"");

        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>BuscarSinTerminar "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarComplementoReciboCaja(String CodRecCaja,String Tipo){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT ctp.FormaPago,ccr.valor,ccr.codigo,cb.nombre FROM cont_complemento_recibo_caja ccr,cont_recibo_caja crc,cont_tipo_pago ctp, cont_bancos cb WHERE crc.codigo = ccr.CodReciboCaja_fk  AND ccr.tipo = "+Tipo+"  AND ctp.codigo = ccr.CodFormaPago_fk  AND crc.codigo = "+CodRecCaja+" AND cb.codigo=ccr.codBanco_fk");
        	System.out.println("SELECT ctp.FormaPago,ccr.valor,ccr.codigo,cb.nombre FROM cont_complemento_recibo_caja ccr,cont_recibo_caja crc,cont_tipo_pago ctp, cont_bancos cb WHERE crc.codigo = ccr.CodReciboCaja_fk  AND ccr.tipo = "+Tipo+"  AND ctp.codigo = ccr.CodFormaPago_fk  AND crc.codigo = "+CodRecCaja+" AND cb.codigo=ccr.codBanco_fk");

        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>BuscarComplementoReciboCaja "+ex);
        }	
        return rs;
    }
	
	
	public void OmitirDetalleFactura(String ValorAbono,String CodFactOM){	
		/**
		 */
		try {
		Conexion con=new Conexion();
		PreparedStatement ps = null;			    
		ps=con.conn.prepareStatement("DELETE FROM cont_detalle_factura WHERE cod_fact_fk="+CodFactOM+" and cantidad="+ValorAbono+"");
		//System.out.println("DELETE FROM cont_detalle_recibo_caja WHERE codigo="+CodDetCaja+"");
		ps.executeUpdate();
		ps.close();
		con.cerrar();				
	}catch(Exception ex){
		System.out.println("ERROR EN MetodoCuentas>>OmitirDetalleReciboCaja "+ex);
	}

    }
	
	public java.sql.ResultSet BuscarDetalleReciboCaja(String CodRecCaja){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM cont_detalle_recibo_caja WHERE cod_recibo_caja_fk="+CodRecCaja+"");
        	//System.out.println("SELECT * FROM cont_detalle_recibo_caja WHERE cod_recibo_caja_fk="+CodRecCaja+"");

        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>BuscarDetalleReciboCaja "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet ValorBonado(String CodFact){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT SUM(cantidad) cantidad FROM cont_detalle_factura WHERE cod_fact_fk="+CodFact+"");
        	System.out.println("SELECT SUM(cantidad) cantidad FROM cont_detalle_factura WHERE cod_fact_fk="+CodFact+"");

        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>ValorBonado "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerNitCuentaEmpresa(String CodEntidad){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM cont_cuentas_empresas WHERE nit="+CodEntidad+" ");
        	//rs=st.executeQuery("SELECT  cf.numero_factura,cf.precio_factura,cf.codigo,(SELECT ((SELECT precio_factura FROM cont_factura  WHERE numero_factura LIKE '%"+Factura+"%') - COALESCE((SUM(cdf.cantidad)), 0)) FROM cont_factura cc,cont_detalle_factura cdf  WHERE cc.codigo = cdf.cod_fact_fk  AND cc.numero_factura LIKE '%"+Factura+"%' ) AS diferencia FROM cont_cuentas_empresas cc,cont_factura cf,cont_detalle_factura cdf WHERE cc.nit = "+CodEntidad+" AND cdf.cod_fact_fk = cf.codigo AND cc.codigo = cf.cod_cuenta_fk  AND cf.precio_factura > 0  AND cf.numero_factura LIKE '%"+Factura+"%'  GROUP BY cf.codigo LIMIT 5 ");

        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>ObtenerNitCuentaEmpresa "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet AutocompletaBuscarFacturaNitAgrupado(String Nit, 
			String Factura){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT cf.numero_factura,cf.precio_factura,cf.codigo FROM  cont_cuentas_empresas cc,cont_factura cf WHERE cc.CodigoCuenta = "+Nit+"   AND cc.codigo = cf.cod_cuenta_fk   AND cf.precio_factura > 0   AND cf.numero_factura LIKE '%"+Factura+"%' LIMIT 5 ");
        	System.out.println("SELECT cf.numero_factura,cf.precio_factura,cf.codigo FROM  cont_cuentas_empresas cc,cont_factura cf WHERE cc.CodigoCuenta = "+Nit+"   AND cc.codigo = cf.cod_cuenta_fk   AND cf.precio_factura > 0   AND cf.numero_factura LIKE '%"+Factura+"%' LIMIT 5 ");

        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>AutocompletaBuscarFactura "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet AutocompletaBuscarFactura(String CodEntidad, 
			String Factura){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT cf.numero_factura,cf.precio_factura,cf.codigo FROM cont_cuentas_empresas cc,cont_factura cf WHERE cc.nit="+CodEntidad+" AND cc.codigo=cf.cod_cuenta_fk and cf.precio_factura > 0 AND cf.numero_factura LIKE '%"+Factura+"%' limit 5 ");
        	//rs=st.executeQuery("SELECT  cf.numero_factura,cf.precio_factura,cf.codigo,(SELECT ((SELECT precio_factura FROM cont_factura  WHERE numero_factura LIKE '%"+Factura+"%') - COALESCE((SUM(cdf.cantidad)), 0)) FROM cont_factura cc,cont_detalle_factura cdf  WHERE cc.codigo = cdf.cod_fact_fk  AND cc.numero_factura LIKE '%"+Factura+"%' ) AS diferencia FROM cont_cuentas_empresas cc,cont_factura cf,cont_detalle_factura cdf WHERE cc.nit = "+CodEntidad+" AND cdf.cod_fact_fk = cf.codigo AND cc.codigo = cf.cod_cuenta_fk  AND cf.precio_factura > 0  AND cf.numero_factura LIKE '%"+Factura+"%'  GROUP BY cf.codigo LIMIT 5 ");

        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>AutocompletaBuscarFactura "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet PrecioFactura(String Factura){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT ((SELECT precio_factura FROM cont_factura WHERE numero_factura ='"+Factura+"')-COALESCE((SUM(cdf.cantidad)),0) ) AS Diferencia FROM cont_factura cc,cont_detalle_factura cdf WHERE cc.codigo = cdf.cod_fact_fk AND cc.numero_factura like '%"+Factura+"%' ");
        	System.out.println("SELECT ((SELECT precio_factura FROM cont_factura WHERE numero_factura ='"+Factura+"')-COALESCE((SUM(cdf.cantidad)),0) ) AS Diferencia FROM cont_factura cc,cont_detalle_factura cdf WHERE cc.codigo = cdf.cod_fact_fk AND cc.numero_factura like '%"+Factura+"%' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>PrecioFactura "+ex);
        }	
        return rs;
    }
	
	
	
	public java.sql.ResultSet BuscarFactura(String fecha_insercion, 
			String hora_insercion){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM cont_factura cf WHERE cf.fecha_insercion = '"+fecha_insercion+"' AND cf.hora_insercion = '"+hora_insercion+"' ");

        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>BuscarFactura "+ex);
        }	
        return rs;
    }
	
	public void InsertarFechaEmisionEnCarteraPlazoRadicar(String fecha_radicado,String Codfactura){

		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("UPDATE cont_cartera_plazo set fecha_radicado='"+fecha_radicado+"' where cod_factura_fk="+Codfactura+" ");
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoCuentas>>InsertarFechaEmisionEnCarteraPlazoRadicar "+ex);
			}

		}
	
	public void InsertarFechaEmisionEnCarteraPlazo(String FechaEmision,String Codfactura){

		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("UPDATE cont_cartera_plazo set fecha_emision='"+FechaEmision+"' where cod_factura_fk="+Codfactura+" ");
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoCuentas>>InsertarFechaEmisionEnCarteraPlazo "+ex);
			}

		}
	
	public void InsertarNotasDebito(String CodEncFactura,String ConceptoND,String ValorND,String fecha_ingreso,String hora_ingreso,String CodUsu_fk,String fecha_nota,String ValorLetras){
		System.out.println("Metodo ConceptoND "+ConceptoND);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("INSERT INTO cont_nota_debito(CodEncFactura,ConceptoND,ValorND,fecha_ingreso,hora_ingreso,CodUsu_fk,fecha_nota,valor_letras)VALUES(?,?,?,?,?,?,?,?)");
			    ps.setString(1,CodEncFactura);
			    ps.setString(2,ConceptoND);
			    ps.setString(3,ValorND);
			    ps.setString(4,fecha_ingreso);
			    ps.setString(5,hora_ingreso);
			    ps.setString(6,CodUsu_fk);
			    ps.setString(7,fecha_nota);
			    ps.setString(8, ValorLetras);
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoCuentas>>InsertarNotasDebito "+ex);
			}

		}
	
	
	public void InsertarTodoEnCarteraPlazo(String Numero_Factura,String Plazo_Corto,
			String Cod_Factura,String FechaEmision){

		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("INSERT INTO cont_cartera_plazo (numero_factura,plazo_corto,cod_factura_fk,fecha_emision)VALUES(?,?,?,?)");
			    ps.setString(1,Numero_Factura);
			    ps.setString(2,Plazo_Corto);
			    ps.setString(3,Cod_Factura);
			    ps.setString(4,FechaEmision);

			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoCuentas>>InsertarTodoEnCarteraPlazo "+ex);
			}

		}
	
	public void InsertarTodoEnCarteraPlazoRadicar(String Numero_Factura,String Plazo_Corto,
			String Cod_Factura,String FechaRadicacion){

		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("INSERT INTO cont_cartera_plazo (numero_factura,plazo_corto,cod_factura_fk,fecha_radicado)VALUES(?,?,?,?)");
			    ps.setString(1,Numero_Factura);
			    ps.setString(2,Plazo_Corto);
			    ps.setString(3,Cod_Factura);
			    ps.setString(4,FechaRadicacion);

			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoCuentas>>InsertarTodoEnCarteraPlazo "+ex);
			}

		}
	
	
	public void CrearComplementoFactura(String cod_factura,String cod_det_factura,
			String cod_centro_costo,String ret_fuente,String tipo_pago){

		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("insert into cont_complemento_factura(cod_factura_fk,cod_det_factura_fk,cod_centro_costo_fk,ret_fuente,tipo_pago)values(?,?,?,?,?)");
			    ps.setString(1,cod_factura);
			    ps.setString(2,cod_det_factura);
			    ps.setString(3,cod_centro_costo);
			    ps.setString(4,ret_fuente);
			    ps.setString(5,tipo_pago);

			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoCuentas>>CrearComplementoFactura "+ex);
			}

		}
	
	public void FinReciboCaja(String CodRecCaja,String ValorRecibidoP){

		String ValorPagadoLetra=Convertir(ValorRecibidoP);
		try{
			Conexion con=new Conexion();
			PreparedStatement ps = null;			    
			ps=con.conn.prepareStatement("UPDATE cont_recibo_caja SET valor_total='"+ValorRecibidoP+"',valorletra='"+ValorPagadoLetra+"' WHERE codigo="+CodRecCaja+"");
			//System.out.println("DELETE FROM cont_detalle_recibo_caja WHERE codigo="+CodDetCaja+"");
			ps.executeUpdate();
			ps.close();
			con.cerrar();				
		}catch(Exception ex){
			System.out.println("ERROR EN MetodoCuentas>>FinReciboCaja "+ex);
		}
	}
	
	public void OmitirComplementoReciboCaja(String CodigoDetalle){
		/**
		 **/
		try{
			Conexion con=new Conexion();
			PreparedStatement ps = null;			    
			ps=con.conn.prepareStatement("DELETE FROM cont_complemento_recibo_caja WHERE codigo="+CodigoDetalle+"");
			ps.executeUpdate();
			ps.close();
			con.cerrar();				
		}catch(Exception ex){
			System.out.println("ERROR EN MetodoCuentas>>OmitirComplementoReciboCaja "+ex);
		}
	}
	
	public void OmitirDetalleReciboCaja(String CodDetCaja){
		/**
		 **/
		try{
			Conexion con=new Conexion();
			PreparedStatement ps = null;			    
			ps=con.conn.prepareStatement("DELETE FROM cont_detalle_recibo_caja WHERE codigo="+CodDetCaja+"");
			//System.out.println("DELETE FROM cont_detalle_recibo_caja WHERE codigo="+CodDetCaja+"");
			ps.executeUpdate();
			ps.close();
			con.cerrar();				
		}catch(Exception ex){
			System.out.println("ERROR EN MetodoCuentas>>OmitirDetalleReciboCaja "+ex);
		}
	}
	
	public void CrearComplementoReciboCaja(String CodFormaPago_fk
			,String valor,String CodReciboCaja_fk,String Tipo,String CodBanco ){
		/**
		 **/
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("INSERT INTO cont_complemento_recibo_caja (CodFormaPago_fk,valor,CodReciboCaja_fk,tipo,codBanco_fk) VALUES (?,?,?,?,?)");
			    ps.setString(1,CodFormaPago_fk);
			    ps.setString(2,valor);
			    ps.setString(3,CodReciboCaja_fk);
			    ps.setString(4, Tipo);
			    ps.setString(5, CodBanco);

			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoCuentas>>CrearComplementoReciboCaja "+ex);
			}

		}
	
	public void CrearReciboCaja(String valor_total,Date fecha,Time hora,
			String cod_usu_fk,String cod_entidad_fk,String tipo_pago,
			String obsevacion,String Concepto,String FechaPago ){
		/**
		 **/
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("INSERT INTO cont_recibo_caja (valor_total,fecha,hora,cod_usu_fk,cod_entidad_fk,tipo_pago,obsevacion,concepto,FechaPago) VALUES (?,?,?,?,?,?,?,?,?)");
			    ps.setString(1,valor_total);
			    ps.setDate(2,fecha);
			    ps.setTime(3,hora);
			    ps.setString(4,cod_usu_fk);
			    ps.setString(5,cod_entidad_fk);
			    ps.setString(6,tipo_pago);
			    ps.setString(7,obsevacion);
			    ps.setString(8,Concepto);
			    ps.setString(9,FechaPago);
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoCuentas>>CrearReciboCaja "+ex);
			}

		}
	
	public void CrearPlazoCartera(String numero_factura,String valor_factura,String Cod_factura ){
		
		
		System.out.println("INSERT INTO cont_cartera_plazo(numero_factura,total,cod_factura_fk,por_vencer) VALUES ('"+numero_factura+"',"+valor_factura+","+Cod_factura+","+valor_factura+")");
	
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("INSERT INTO cont_cartera_plazo(numero_factura,total,cod_factura_fk,por_vencer) VALUES (?,?,?,?)");
			    ps.setString(1,numero_factura);
			    ps.setString(2,valor_factura);
			    ps.setString(3,Cod_factura);
			    ps.setString(4,valor_factura);
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoCuentas>>CrearPlazoCartera "+ex);
			}

		}
	
	public void CrearDetalleReciboCaja(String cod_recibo_caja_fk,
			String cod_factura_fk,String valor_factura,String valor_abono,String numero_factura ){
		/**
		 * creamos la detalle factura
		 **/
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("INSERT INTO cont_detalle_recibo_caja (cod_recibo_caja_fk,cod_factura_fk,valor_factura,valor_abono,numero_factura) VALUES (?, ?, ?, ?, ?)");
			    ps.setString(1,cod_recibo_caja_fk);
			    ps.setString(2,cod_factura_fk);
			    ps.setString(3,valor_factura);
			    ps.setString(4,valor_abono);
			    ps.setString(5,numero_factura);
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoCuentas>>CrearDetalleReciboCaja "+ex);
			}

		}
	
	public void CrearDetalleFactura(String cod_fact_fk,String cantidad,String fecha,String tipo,
			String restante,String num_soporte,String observacion,String fecha_insercion,
			String hora_insercion,String usuario,String valorletras ){
		
		//System.out.println("ERROR EN MetodoCuentas>>CrearDetalleFactura ");
		/**
		 * creamos la factura
		 */
		//System.out.print("fecha_factura "+fecha_factura+" fecha_insercion "+fecha_insercion+" hora_insercion "+hora_insercion);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("INSERT INTO cont_detalle_factura (cod_fact_fk,cantidad,fecha,tipo,restante,num_soporte,observacion,fecha_insercion,hora_insercion,usuario,valorletras) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			    ps.setString(1,cod_fact_fk);
			    ps.setString(2,cantidad);
			    ps.setString(3,fecha);
			    ps.setString(4,tipo);
			    ps.setString(5,restante);
			    ps.setString(6,num_soporte);
			    ps.setString(7,observacion);
			    ps.setString(8,fecha_insercion);
			    ps.setString(9,hora_insercion);
			    ps.setString(10,usuario);
			    ps.setString(11,valorletras);
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoCuentas>>CrearDetalleFactura "+ex);
			}

		}
	
	
	public void CrearFactura(String cod_cuenta_fk,String estado,String fecha_factura,String fecha_insercion,String hora_insercion,String iva,String numero_factura,String observacion,String precio_factura,String ret_ica,String tipo,String usuario_insercion ){
		//System.out.println("ERROR EN MetodoCuentas>>CrearFactura ");
		//System.out.print("numero_factura= "+numero_factura+" cod_cuenta_fk="+cod_cuenta_fk+" fecha_factura "+fecha_factura+" fecha_insercion "+fecha_insercion+" hora_insercion "+hora_insercion);
		Facturas fc = new Facturas();
		fc.setCod_cuenta_fk(cod_cuenta_fk);
		fc.setEstado(estado);
		fc.setFecha_factura(fecha_factura);
		fc.setFecha_insercion(fecha_insercion);
		fc.setHora_insercion(hora_insercion);
		fc.setIva(iva);
		fc.setNumero_factura(numero_factura);
		fc.setObservacion(observacion);
		fc.setPrecio_factura(precio_factura);
		fc.setRet_ica(ret_ica);
		fc.setTipo(tipo);
		fc.setUsuario_insercion(usuario_insercion);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("insert into cont_factura(cod_cuenta_fk,numero_factura,precio_factura,fecha_factura,fecha_insercion,hora_insercion,usuario_insercion,tipo,estado,observacion,iva,ret_ica)values(?,?,?,?,?,?,?,?,?,?,?,?)");
			    ps.setString(1,fc.getCod_cuenta_fk());
			    ps.setString(2,fc.getNumero_factura());
			    ps.setString(3,fc.getPrecio_factura());
			    ps.setString(4,fc.getFecha_factura());
			    ps.setString(5,fc.getFecha_insercion());
			    ps.setString(6,fc.getHora_insercion());
			    ps.setString(7,fc.getUsuario_insercion());
			    ps.setString(8,fc.getTipo());
			    ps.setString(9,fc.getEstado());
			    ps.setString(10,fc.getObservacion());
			    ps.setString(11,fc.getIva());
			    ps.setString(12,fc.getRet_ica());
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoCuentas>>CrearFactura "+ex);
			}

		}

	
	public java.sql.ResultSet AutoCompletarCuenta(String Cad){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT codigo,NombreCuenta FROM cont_cuentas_empresas WHERE NombreCuenta LIKE '"+Cad+"%' or CodigoCuenta LIKE'"+Cad+"%'");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCuentas>>BuscarCuentaProveedor "+ex);
        }	
        return rs;
    }
	

	

	
	public void ActualizarPlazoCartera(String CodFactura,String plazo_corto,String plazo_30,String plazo_60,String plazo_90,String plazo_180,String plazo_360,String Por_vencer){
		
		PreparedStatement st = null;	     
		try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE cont_cartera_plazo SET plazo_corto='"+plazo_corto+"',plazo_30='"+plazo_30+"',plazo_60='"+plazo_60+"',plazo_90='"+plazo_90+"',plazo_180='"+plazo_180+"',plazo_360='"+plazo_360+"',por_vencer='"+Por_vencer+"' WHERE cod_factura_fk='"+CodFactura+"' ");
	     	           System.out.println("UPDATE cont_cartera_plazo SET plazo_corto='"+plazo_corto+"',plazo_30='"+plazo_30+"',plazo_60='"+plazo_60+"',plazo_90='"+plazo_90+"',plazo_180='"+plazo_180+"',plazo_360='"+plazo_360+"',por_vencer='"+Por_vencer+"' WHERE cod_factura_fk='"+CodFactura+"' ");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("ERROR EN MetodoCuentas>>ActualizarCuenta "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	
	public void ActualizarCuenta(String CodigoCuenta,String NombreCuenta,String TipoCuenta,String NaturalezaCuenta,String Nivel,String estado,String CodCuenta){
		PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("update cont_cuentas_empresas set CodigoCuenta=?,NombreCuenta=?,TipoCuenta=?,NaturalezaCuenta=?,Nivel="+Nivel+",estado=? where codigo=?");
	     	st.setString(1,CodigoCuenta);
	     	st.setString(2,NombreCuenta);
	     	st.setString(3,TipoCuenta);
	     	st.setString(4,NaturalezaCuenta);
	     	st.setString(5,estado);
	     	st.setString(6,CodCuenta);
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("ERROR EN MetodoCuentas>>ActualizarCuenta "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public void InserEmcabe(String cod_eps,String razon_social,String nit,String direccion,String telefono,
			String ciudad,String nombre_paciente,String documento,String direccion_p,String telefono_p,String tipo_afiliacion,
			String estrato,String fecha_ingreso,String fecha_egreso,String CodAdm,String num_autorizacion,String poliza,String ViaIng){
	    PreparedStatement psc = null;
	     try{
	    	 Conexion con=new Conexion();
	    	 psc=con.conn.prepareStatement("insert into fact_enc_factura(cod_eps, razon_social," +
     				" nit, direccion," +
     				" telefono,ciudad,condicion_pago,nombre_paciente,documento," +
     				"direccion_p,telefono_p," +
     				"tipo_afiliacion,estrato,fecha_ingreso,fecha_egreso," +
     				"cod_admision,num_autorizacion," +
     				"cod_usuario_fk,poliza,tipo) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");	
  			psc.setString(1,cod_eps);
  			psc.setString(2,razon_social);
  			psc.setString(3,nit);
  			psc.setString(4,direccion);
  			psc.setString(5,telefono);
  			psc.setString(6,ciudad);
  			psc.setString(7,"30 Dias");
  			psc.setString(8,nombre_paciente);
  			psc.setString(9,documento);
  			psc.setString(10,direccion_p);
  			psc.setString(11,telefono_p);
  			psc.setString(12,tipo_afiliacion);
  			psc.setString(13,estrato);
  			psc.setString(14,fecha_ingreso);
  			psc.setString(15,fecha_egreso);
  			psc.setString(16,CodAdm);
  			psc.setString(17,num_autorizacion);
  			psc.setString(18,null);
  			psc.setString(19,poliza);
  			psc.setString(20, ViaIng);
  			psc.executeUpdate();
 			psc.close();
 			con.cerrar();
 			
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoCuentas>>InserEmcabe "+ex);
	        }	
	 }
	
	/********************************************************************************/
	/*public String FormatoMoneda(String valor){		
		String temp2="";String temp1="";
		int ud=1;int logCad = valor.length();		
		for (int i=logCad-1;i>=0;i--){			
			temp2=valor.substring(i,i+1);
			temp2+=temp1;
			if(ud==3){
				if(i!=0){temp1="."+temp2;}else{temp1=temp2;}ud=0;
			}else{temp1=temp2;}
			ud++;
		}
		temp1="$ "+temp1;
		return temp1;
	}*/
	/********************************************************************************/
    private final String[] UNIDADES = {"", "un ", "dos ", "tres ", "cuatro ", "cinco ", "seis ", "siete ", "ocho ", "nueve "};
    private final String[] DECENAS = {"diez ", "once ", "doce ", "trece ", "catorce ", "quince ", "dieciseis ",
        "diecisiete ", "dieciocho ", "diecinueve", "veinte ", "treinta ", "cuarenta ",
        "cincuenta ", "sesenta ", "setenta ", "ochenta ", "noventa "};
    private final String[] CENTENAS = {"", "ciento ", "doscientos ", "trecientos ", "cuatrocientos ", "quinientos ", "seiscientos ",
        "setecientos ", "ochocientos ", "novecientos "};

   /*public Numero_a_Letra() {
   }*/

    public String Convertir(String numero) {
        String literal = "";
        String parte_decimal;    
        //si el numero utiliza (.) en lugar de (,) -> se reemplaza
        numero = numero.replace(".", ",");
        //si el numero no tiene parte decimal, se le agrega ,00
        if(numero.indexOf(",")==-1){
            numero = numero + "";
        }
        //se valida formato de entrada -> 0,00 y 999 999 999,00
        if (Pattern.matches("\\d{1,9}", numero)) {
            //se divide el numero 0000000,00 -> entero y decimal
            String Num[] = numero.split(",");            
            //de da formato al numero decimal
            parte_decimal =  "  Pesos";
            //se convierte el numero a literal
            if (Integer.parseInt(Num[0]) == 0) {//si el valor es cero
                literal = "cero ";
            } else if (Integer.parseInt(Num[0]) > 999999) {//si es millon
                literal = getMillones(Num[0]);
            } else if (Integer.parseInt(Num[0]) > 999) {//si es miles
                literal = getMiles(Num[0]);
            } else if (Integer.parseInt(Num[0]) > 99) {//si es centena
                literal = getCentenas(Num[0]);
            } else if (Integer.parseInt(Num[0]) > 9) {//si es decena
                literal = getDecenas(Num[0]);
            } else {//sino unidades -> 9
                literal = getUnidades(Num[0]);
            }
            //devuelve el resultado en mayusculas o minusculas
           // if (mayusculas) {
                return (literal + parte_decimal).toUpperCase();
           // } else {
               // return (literal + parte_decimal);
            //}
        } else {//error, no se puede convertir
            return literal = null;
        }
    }

    /* funciones para convertir los numeros a literales */

    private String getUnidades(String numero) {// 1 - 9
        //si tuviera algun 0 antes se lo quita -> 09 = 9 o 009=9
        String num = numero.substring(numero.length() - 1);
        return UNIDADES[Integer.parseInt(num)];
    }

    private String getDecenas(String num) {// 99                        
        int n = Integer.parseInt(num);
        if (n < 10) {//para casos como -> 01 - 09
            return getUnidades(num);
        } else if (n > 19) {//para 20...99
            String u = getUnidades(num);
            if (u.equals("")) { //para 20,30,40,50,60,70,80,90
                return DECENAS[Integer.parseInt(num.substring(0, 1)) + 8];
            } else {
                return DECENAS[Integer.parseInt(num.substring(0, 1)) + 8] + "y " + u;
            }
        } else {//numeros entre 11 y 19
            return DECENAS[n - 10];
        }
    }

    private String getCentenas(String num) {// 999 o 099
        if( Integer.parseInt(num)>99 ){//es centena
            if (Integer.parseInt(num) == 100) {//caso especial
                return " cien ";
            } else {
                 return CENTENAS[Integer.parseInt(num.substring(0, 1))] + getDecenas(num.substring(1));
            } 
        }else{//por Ej. 099 
            //se quita el 0 antes de convertir a decenas
            return getDecenas(Integer.parseInt(num)+"");            
        }        
    }

    private String getMiles(String numero) {// 999 999
        //obtiene las centenas
        String c = numero.substring(numero.length() - 3);
        //obtiene los miles
        String m = numero.substring(0, numero.length() - 3);
        String n="";
        //se comprueba que miles tenga valor entero
        if (Integer.parseInt(m) > 0) {
            n = getCentenas(m);           
            return n + "mil " + getCentenas(c);
        } else {
            return "" + getCentenas(c);
        }

    }

    private String getMillones(String numero) { //000 000 000        
        //se obtiene los miles
        String miles = numero.substring(numero.length() - 6);
        //se obtiene los millones
        String millon = numero.substring(0, numero.length() - 6);
        String n = "";
        if(millon.length()>1){
            n = getCentenas(millon) + "millones ";
        }else{
            n = getUnidades(millon) + "millon ";
        }
        return n + getMiles(miles);        
    }


}
