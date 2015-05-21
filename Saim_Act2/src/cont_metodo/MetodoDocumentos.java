package cont_metodo;

import java.sql.PreparedStatement;
import java.sql.Statement;

import adm_logica.Conexion;

public class MetodoDocumentos {
	
	public java.sql.ResultSet ConsultarPeriodo(String p, String a){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT codigo, bloqueo FROM cont_anio_periodo WHERE anio="+a+" AND periodo="+p);
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoDocumentos>>ConsultarPeriodo "+ex);
        }	
        return rs;
    }

	public java.sql.ResultSet ConsultarTiposDocumentos(){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT codigo, nombre FROM cont_tipo_documento");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoDocumentos>>ConsultarTiposDocumentos "+ex);
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
        	System.out.println("Error en MetodoDocumentos>>ConsultarPlantillas "+ex);
        }	
        return rs;
    }
	


	/*VALIDACION DE CONSECUTIVOS */
	

	public java.sql.ResultSet VerifcacionConsecutivo(String nd){	
			java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT * FROM cont_documentos WHERE numero_documento='"+nd+"'  ");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoDocumentos>>VerifcacionConsecutivo "+ex);
	        }	
	        return rs;
	    }

		
		public java.sql.ResultSet VerifConttipoDoc(String td){	
			java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT * FROM cont_tipo_documento where codigo='"+td+"'  ");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoDocumentos>>VerifConttipoDoc "+ex);
	        }	
	        return rs;
	    }
	
	/*FIN VALIDACION*/
	
	public java.sql.ResultSet ConsultarDetallePlantillas(String cod){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT codigo,cod_cuenta_fk,formula_base,formula_debito,formula_credito FROM cont_detalle_plantillas WHERE cod_plantilla_contable_fk="+cod+" ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoDocumentos>>ConsultarDetallePlantillas "+ex);
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
        	System.out.println("Error en MetodoDocumentos>>Sucursales "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet Bancos(){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT codigo,nombre,cod_cuenta_fk FROM cont_bancos");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoDocumentos>>Sucursales "+ex);
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
        	System.out.println("Error en MetodoDocumentos>>Sucursales "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet CentrosdeCosto(String cod){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT cc.codigo, cc.NombreCentroCosto,scc.codigo FROM fact_centrocosto cc, cont_surcursal_centrocosto scc WHERE  scc.cod_sucursal_fk="+cod+" AND cc.codigo=scc.cod_centro_costo_fk ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoDocumentos>>CentrosdeCosto "+ex);
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
        	System.out.println("Error en MetodoDocumentos>>SubCentrosdeCosto "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet Terceros(){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT codigo, numero_documento,razon_social FROM cont_terceros");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoDocumentos>>Terceros "+ex);
        }	
        return rs;
    }

	public java.sql.ResultSet ConceptordeEgreso(){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT codigo, nombre, cod_cta FROM cont_conceptos_egresos");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoDocumentos>>ConceptordeEgreso "+ex);
        }	
        return rs;
    }
	
		public java.sql.ResultSet ConsecutivosdeCuentas(String cod){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT sigla, consecutivo FROM cont_tipo_documento WHERE codigo="+cod+" ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoDocumentos>>ConsecutivosdeCuentas "+ex);
        }	
        return rs;
    }
	
		
	public void CrearDocumento(String ac,String pc,String td,String nd,String fd,String dd,String vd,String vc,String pd,String bd,String user,String fc,String hc){
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("INSERT INTO cont_documentos(anio,periodo,tipo_documento_fk,numero_documento,fecha,detalle,valor_debito,valor_credito,cod_plantilla_fk,valor_base,cod_usaurio,fecha_creacion,hora_creacion)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");
			    ps.setString(1,ac);
			    ps.setString(2,pc);
			    ps.setString(3,td);
			    ps.setString(4,nd);
			    ps.setString(5,fd);
			    ps.setString(6,dd);
			    ps.setString(7,vd);
			    ps.setString(8,vc);
			    ps.setString(9,pd);
			    ps.setString(10,bd);
			    ps.setString(11,user);
			    ps.setString(12,fc);
			    ps.setString(13,hc);
			    ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoDocumentos>>CrearDocumento "+ex);
			}
		}
	
	public java.sql.ResultSet ConsecutivoUDocumento(String cod){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT codigo FROM cont_documentos WHERE numero_documento='"+cod+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoDocumentos>>ConsecutivoUDocumento "+ex);
        }	
        return rs;
    }
	
	
	
	public void ActualizarConsecutivo(String ctan, String td){	
		/**
		 */
		 PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE cont_tipo_documento SET consecutivo='"+ctan+"' WHERE codigo='"+td+"' ");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoDocumentos>>ActualizarConsecutivo "+ex);
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
        	System.out.println("Error en MetodoDocumentos>>Cuentas "+ex);
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
        	System.out.println("Error en MetodoDocumentos>>Terceros "+ex);
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
        	System.out.println("Error en MetodoDocumentos>>DetallePlantillas "+ex);
        }	
        return rs;
    }
	
	public void CrearDetalleDocumento(String ac,String pc,String td,String nd,String fd,String dd,String vd,String vc,String pd,String bd,String dif,String ct){
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("INSERT INTO cont_detalle_documentos (cod_documento_fk,cod_cuenta_fk,cod_sucursal_costo_fk,cod_centro_subcentro_fk,cod_terceros_fk,descripcion,valor_base,documento_referencia,valor_debito,valor_credito,cod_diferido_fk,origen) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
			    ps.setString(1,ac);
			    ps.setString(2,pc);
			    ps.setString(3,td);
			    ps.setString(4,nd);
			    ps.setString(5,fd);
			    ps.setString(6,dd);
			    ps.setString(7,vd);
			    ps.setString(8,vc);
			    ps.setString(9,pd);
			    ps.setString(10,bd);
			    ps.setString(11,dif);
			    ps.setString(12,ct);
			    ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoDocumentos>>CrearDetalleDocumento "+ex);
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
	     	System.out.println("Error en MetodoDocumentos>>ActualizarDocumentoDebitoCredito "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public java.sql.ResultSet ConsultarTipoDocumento(String sql){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT tipo_documento_fk, numero_documento, fecha, valor_debito FROM cont_documentos WHERE codigo='"+sql+"' ");
        //	System.out.println(" SELECT * FROM cont_documentos WHERE codigo!=0  "+sql+" ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoDocumentos>>ConsultarDocumentos "+ex);
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
        //	System.out.println(" SELECT * FROM cont_documentos WHERE codigo!=0  "+sql+" ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoDocumentos>>ConsultarDocumentos "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet ConsultarDocumentosaModificar(String sql){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT * FROM cont_documentos WHERE codigo!=0  "+sql+" ");
        	//System.out.println(" SELECT * FROM cont_documentos WHERE codigo!=0  "+sql+" ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoDocumentos>>ConsultarDocumentos "+ex);
        }	
        return rs;
    }
	
	
		public java.sql.ResultSet CentrosySubcentrosdeCosto(String cod){	
			java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery(" SELECT cod_centro_costo_fk,cod_sub_centro_costo_fk FROM cont_centro_subcentro WHERE codigo="+cod+"  ");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoDocumentos>>CentrosySubcentrosdeCosto "+ex);
	        }	
	        return rs;
	    }
		
		public java.sql.ResultSet Acumulado_Sucursal_Centrocco(String sc, String cs, String cta, String a){	
			java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery(" SELECT codigo FROM cont_acumulado_sucursal_centrocosto WHERE cod_sucursal_centrocosto_fk="+sc+" AND cod_centros_subcentrocosto_fk="+cs+" AND cod_cuenta_fk="+cta+" AND anio="+a+"  ");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoDocumentos>>Acumulado_Sucursal_Centrocco "+ex);
	        }	
	        return rs;
	    }
	
		public java.sql.ResultSet Acumulado_Terceros(String ter,  String cta, String a, String sc, String cs){	
			//System.out.println("  SELECT codigo FROM cont_acumulado_tercero WHERE cod_tercero_fk="+ter+" AND cod_cuenta_fk="+cta+"  AND anio="+a+" AND cod_sucursal_ccosto_fk="+sc+" AND cod_centros_subcentrocosto_fk="+cs+"   ");
			java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery(" SELECT codigo FROM cont_acumulado_tercero WHERE cod_tercero_fk="+ter+" AND cod_cuenta_fk="+cta+"  AND anio="+a+" AND cod_sucursal_ccosto_fk="+sc+" AND cod_centros_subcentrocosto_fk="+cs+"   ");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoDocumentos>>Acumulado_Terceros "+ex);
	        }	
	        return rs;
	    }
	
		
		public void CrearAcumulado_Sucursal_Centrocco(String sc, String cs, String cta, String a){
			try{
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("INSERT INTO cont_acumulado_sucursal_centrocosto (cod_sucursal_centrocosto_fk,cod_centros_subcentrocosto_fk,cod_cuenta_fk,anio) VALUES(?,?,?,?)");
				    ps.setString(1,sc);
				    ps.setString(2,cs);
				    ps.setString(3,cta);
				    ps.setString(4,a);
				    ps.executeUpdate();
					ps.close();
					con.cerrar();				
				}catch(Exception ex){
		        	System.out.println("ERROR EN MetodoDocumentos>>CrearAcumulado_Sucursal_Centrocco "+ex);
				}
			}
		
		public void CrearAcumulado_Terceros(String ter,  String cta, String a, String sc, String cs){
			try{
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("INSERT INTO cont_acumulado_tercero (cod_tercero_fk,cod_cuenta_fk,anio,cod_sucursal_ccosto_fk,cod_centros_subcentrocosto_fk) VALUES(?,?,?,?,?)");
				    ps.setString(1,ter);
				    ps.setString(2,cta);
				    ps.setString(3,a);
				    ps.setString(4,sc);
				    ps.setString(5,cs);
				    ps.executeUpdate();
					ps.close();
					con.cerrar();				
				}catch(Exception ex){
		        	System.out.println("ERROR EN MetodoDocumentos>>CrearAcumulado_Terceros "+ex);
				}
			}
		
		public void CrearDetalle_Acumulado_Sucursal_Centrocco(String casc, String p, String sa, String d, String c, String sn){
			try{
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("INSERT INTO cont_detalle_acumulado_surcursal_ccosto (cod_acu_sucur_ccosto_fk,periodo,saldo_anterior,debito,credito,saldo_nuevo) VALUES(?,?,?,?,?,?)");
				    ps.setString(1,casc);
				    ps.setString(2,p);
				    ps.setString(3,sa);
				    ps.setString(4,d);
				    ps.setString(5,c);
				    ps.setString(6,sn);
				    ps.executeUpdate();
					ps.close();
					con.cerrar();				
				}catch(Exception ex){
		        	System.out.println("ERROR EN MetodoDocumentos>>CrearDetalle_Acumulado_Sucursal_Centrocco "+ex);
				}
			}
		
		public void CrearDetalle_Acumulado_Tercero(String cat, String p, String sa, String d, String c, String sn){
			try{
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("INSERT INTO cont_detalle_acumulado_tercero (cod_acumulado_tercero_fk,periodo,saldo_anterior,debito,credito,saldo_nuevo) VALUES(?,?,?,?,?,?)");
				    ps.setString(1,cat);
				    ps.setString(2,p);
				    ps.setString(3,sa);
				    ps.setString(4,d);
				    ps.setString(5,c);
				    ps.setString(6,sn);
				    ps.executeUpdate();
					ps.close();
					con.cerrar();				
				}catch(Exception ex){
		        	System.out.println("ERROR EN MetodoDocumentos>>CrearDetalle_Acumulado_Tercero "+ex);
				}
			}
		
		
		public java.sql.ResultSet Detalle_Acumulado_Sucursal_CentroccoP13(String cod){	
			java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery(" SELECT saldo_nuevo FROM cont_detalle_acumulado_surcursal_ccosto WHERE cod_acu_sucur_ccosto_fk="+cod+" AND periodo='13' ");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoDocumentos>>Detalle_Acumulado_Sucursal_CentroccoP13 "+ex);
	        }	
	        return rs;
	    }
		
		 
		public java.sql.ResultSet Detalle_Acumulado_TerceroP13(String cod){	
				java.sql.ResultSet rs=null;
		        Statement st = null;
		        try{
		        	Conexion con=new Conexion();
		        	st = con.conn.createStatement();
		        	rs=st.executeQuery(" SELECT saldo_nuevo FROM cont_detalle_acumulado_tercero WHERE cod_acumulado_tercero_fk="+cod+" AND periodo='13' ");
		        }
		        catch(Exception ex){
		        	System.out.println("Error en MetodoDocumentos>>Detalle_Acumulado_Sucursal_CentroccoP13 "+ex);
		        }	
		        return rs;
		    }
		
		public java.sql.ResultSet Detalle_Acumulado_Sucursal_CentroccoPeriodo(String cod, String p){	
			java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery(" SELECT saldo_anterior,debito,credito,saldo_nuevo FROM cont_detalle_acumulado_surcursal_ccosto WHERE cod_acu_sucur_ccosto_fk="+cod+" AND periodo='"+p+"' ");
	        	//System.out.println(" SELECT saldo_anterior,debito,credito,saldo_nuevo FROM cont_detalle_acumulado_surcursal_ccosto WHERE cod_acu_sucur_ccosto_fk="+cod+" AND periodo='"+p+"' ");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoDocumentos>>Detalle_Acumulado_Sucursal_CentroccoPeriodo "+ex);
	        }	
	        return rs;
	    }
		
		
		public java.sql.ResultSet Detalle_Acumulado_Tercero(String cod, String p){	
			java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery(" SELECT saldo_anterior,debito,credito,saldo_nuevo FROM cont_detalle_acumulado_tercero WHERE cod_acumulado_tercero_fk="+cod+" AND periodo='"+p+"' ");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoDocumentos>>Detalle_Acumulado_Sucursal_CentroccoPeriodo "+ex);
	        }	
	        return rs;
	    }
		
		public void ActualizarDetalle_Acumulado_Sucursal_Centrocco(String sa, String vd, String vc , String sn, String cod, String pc){	
			/**
			 */
			 PreparedStatement st = null;
		     try{
		     	Conexion con=new Conexion();
		     	st= con.conn.prepareStatement("UPDATE cont_detalle_acumulado_surcursal_ccosto SET saldo_anterior='"+sa+"', debito='"+vd+"' , credito='"+vc+"', saldo_nuevo='"+sn+"' WHERE cod_acu_sucur_ccosto_fk='"+cod+"' AND periodo='"+pc+"' ");
		    	//System.out.println("UPDATE cont_detalle_acumulado_surcursal_ccosto SET saldo_anterior='"+sa+"', debito='"+vd+"' , credito='"+vc+"', saldo_nuevo='"+sn+"' WHERE cod_acu_sucur_ccosto_fk='"+cod+"' AND periodo='"+pc+"' ");
		     	st.executeUpdate();	 
		     	st.close();
		     	con.cerrar();
		     }
		     catch(Exception ex){
		     	System.out.println("Error en MetodoDocumentos>>ActualizarDetalle_Acumulado_Sucursal_Centrocco "+ex);
		     	ex.getMessage();	     
		     }	
		 }
		
		public void ActualizarDetalle_Acumulado_Tercero(String sa, String vd, String vc , String sn, String cod, String pc){	
			/**
			 */
			 PreparedStatement st = null;
		     try{
		     	Conexion con=new Conexion();
		     	st= con.conn.prepareStatement(" UPDATE cont_detalle_acumulado_tercero SET saldo_anterior='"+sa+"', debito='"+vd+"' , credito='"+vc+"', saldo_nuevo='"+sn+"' WHERE cod_acumulado_tercero_fk='"+cod+"' AND periodo='"+pc+"' ");
		     	st.executeUpdate();	 
		     	st.close();
		     	con.cerrar();
		     }
		     catch(Exception ex){
		     	System.out.println("Error en MetodoDocumentos>>ActualizarDetalle_Acumulado_Sucursal_Centrocco "+ex);
		     	ex.getMessage();	     
		     }	
		 }
		
		
		public java.sql.ResultSet ConsultarNombreCta(String cod){	
			java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery(" SELECT CodigoCuenta FROM cont_cuentas WHERE codigo='"+cod+"' ");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoDocumentos>>ConsultarNombreCta "+ex);
	        }	
	        return rs;
	    }
		
		
		public java.sql.ResultSet ConsultarDocumentosyDetalles(String doc){	
			java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery(" SELECT d.anio,d.periodo,d.numero_documento,d.fecha,d.valor_debito,d.valor_credito,d.cod_plantilla_fk,d.valor_base,dd.codigo,dd.cod_documento_fk,dd.cod_cuenta_fk,c.CodigoCuenta, " +
	        			"dd.cod_sucursal_costo_fk,s.codigo,cc.codigo,dd.cod_centro_subcentro_fk,scc.cod_subcentro_costo,dd.cod_terceros_fk,t.numero_documento,dd.descripcion,dd.valor_base,dd.documento_referencia,dd.valor_debito,dd.valor_credito,dd.cod_diferido_fk,dd.origen  " +
	        			"FROM cont_documentos d, cont_detalle_documentos dd, cont_cuentas c,cont_surcursal_centrocosto sc,cont_sucursales s,fact_centrocosto cc,cont_centro_subcentro cs,fact_subcentros_costo scc,cont_terceros t  " +
	        			"WHERE d.codigo='"+doc+"' AND d.codigo=dd.cod_documento_fk AND dd.cod_cuenta_fk=c.codigo AND dd.cod_sucursal_costo_fk=sc.codigo AND sc.cod_sucursal_fk=s.codigo AND sc.cod_centro_costo_fk=cc.codigo  " +
	        			"AND dd.cod_centro_subcentro_fk=cs.codigo AND cs.cod_sub_centro_costo_fk=scc.cod_subcentro_costo AND dd.cod_terceros_fk=t.codigo ");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoDocumentos>>ConsultarDocumentosyDetalles "+ex);
	        }	
	        return rs;
	    }
		
		public java.sql.ResultSet ConsultarDocumento(String doc){	
			java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery(" SELECT * FROM cont_documentos WHERE codigo='"+doc+"'  ");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoDocumentos>>ConsultarDocumento "+ex);
	        }	
	        return rs;
	    }
		
			
		public void CrearMoviemientoDocumento(String a, String b, String c, String d, String e, String f, String g, String h, String i, String j, String k, String l, String m, String n, String o){
			try{
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("INSERT INTO cont_movdocumentos (codigo_documento_fk,anio,periodo,tipo_documento_fk,numero_documento,fecha,detalle,valor_debito,valor_credito,cod_plantilla_fk,valor_base ,estado,cod_usuario,fecha_creacion,hora_creacion) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				    ps.setString(1,a);
				    ps.setString(2,b);
				    ps.setString(3,c);
				    ps.setString(4,d);
				    ps.setString(5,e);
				    ps.setString(6,f);
				    ps.setString(7,g);
				    ps.setString(8,h);
				    ps.setString(9,i);
				    ps.setString(10,j);
				    ps.setString(11,k);
				    ps.setString(12,l);
				    ps.setString(13,m);
				    ps.setString(14,n);
				    ps.setString(15,o);
				    ps.executeUpdate();
					ps.close();
					con.cerrar();				
				}catch(Exception ex){
		        	System.out.println("ERROR EN MetodoDocumentos>>CrearMoviemientoDocumento "+ex);
				}
			}
		
		
		public java.sql.ResultSet ConsultarDetalleDocumento(String doc){	
			java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery(" SELECT * FROM cont_detalle_documentos WHERE cod_documento_fk='"+doc+"'  ");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoDocumentos>>ConsultarDocumento "+ex);
	        }	
	        return rs;
	    }
		
		
		public void CrearMoviemientoDetalleDocumento(String a, String b, String c, String d, String e, String f, String g, String h, String i, String j, String k, String l, String m, String n){
			try{
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("INSERT INTO cont_movdetalle_documentos (cod_movdocumentos_fk,cod_detalle_documento_fk,cod_documento_fk,cod_cuenta_fk,cod_sucursal_costo_fk,cod_centro_subcentro_fk,cod_terceros_fk,descripcion,valor_base,documento_referencia,valor_debito,valor_credito,cod_diferido_fk,origen) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				    ps.setString(1,a);
				    ps.setString(2,b);
				    ps.setString(3,c);
				    ps.setString(4,d);
				    ps.setString(5,e);
				    ps.setString(6,f);
				    ps.setString(7,g);
				    ps.setString(8,h);
				    ps.setString(9,i);
				    ps.setString(10,j);
				    ps.setString(11,k);
				    ps.setString(12,l);
				    ps.setString(13,m);
				    ps.setString(14,n);
				    ps.executeUpdate();
					ps.close();
					con.cerrar();				
				}catch(Exception ex){
		        	System.out.println("ERROR EN MetodoDocumentos>>CrearMoviemientoDetalleDocumento "+ex);
				}
			}
		
		
		public java.sql.ResultSet ConsultarCodMovDocumento(String doc){	
			java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery(" SELECT codigo FROM cont_movdocumentos WHERE codigo_documento_fk='"+doc+"' ORDER BY codigo DESC LIMIT 1    ");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoDocumentos>>ConsultarDocumento "+ex);
	        }	
	        return rs;
	    }
		
		
		
		public void ActualizarDetalle_Documento(String cta, String sc, String cs, String ter, String des, String bas, String ref, String deb, String cre, String dif, String ori, String cod){	
			/**
			 */
			 PreparedStatement st = null;
		     try{
		     	Conexion con=new Conexion();
		     	st= con.conn.prepareStatement(" UPDATE cont_detalle_documentos SET cod_cuenta_fk='"+cta+"', cod_sucursal_costo_fk='"+sc+"' , cod_centro_subcentro_fk='"+cs+"', cod_terceros_fk='"+ter+"', descripcion='"+des+"', valor_base='"+bas+"' , documento_referencia='"+ref+"', valor_debito='"+deb+"', valor_credito='"+cre+"', cod_diferido_fk='"+dif+"' , origen='"+ori+"' WHERE codigo='"+cod+"' ");
		     	st.executeUpdate();	 
		     	st.close();
		     	con.cerrar();
		     }
		     catch(Exception ex){
		     	System.out.println("Error en MetodoDocumentos>>ActualizarDetalle_Acumulado_Sucursal_Centrocco "+ex);
		     	ex.getMessage();	     
		     }	
		 }
		
		
		public java.sql.ResultSet ConsultarDetalleDocumentoDebitoyCredito(String doc){	
			java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery(" SELECT d.valor_debito,d.valor_credito,d.cod_cuenta_fk,d.cod_sucursal_costo_fk,d.cod_centro_subcentro_fk,d.cod_terceros_fk,o.anio FROM cont_detalle_documentos d, cont_documentos o WHERE d.codigo='"+doc+"' AND d.cod_documento_fk=o.codigo   ");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoDocumentos>>ConsultarDetalleDocumentoDebitoyCredito "+ex);
	        }	
	        return rs;
	    }
		
		
		
		public void EliminarDetalle_Documento(String cod){
			PreparedStatement st = null;
		    try{
		    	Conexion con=new Conexion();
		    	st= con.conn.prepareStatement("DELETE FROM cont_detalle_documentos WHERE codigo='"+cod+"'");
		    	st.executeUpdate();
		    	st.close();
		    	con.cerrar();
		    	
		    }
		    catch(Exception ex){
		    	System.out.println("ERROR EN MetodoDocumentos>>EliminarDetalle_Documento "+ex);
		    
		    }	
		}
		
		
		public void EliminarDocumento(String doc){
			PreparedStatement st = null;
		    try{
		    	Conexion con=new Conexion();
		    	st= con.conn.prepareStatement("DELETE FROM cont_documentos WHERE codigo='"+doc+"' ");
		    	st.executeUpdate();
		    	st.close();
		    	con.cerrar();
		    	
		    }
		    catch(Exception ex){
		    	System.out.println("ERROR EN MetodoDocumentos>>EliminarDocumento "+ex);
		    
		    }	
		}
		
		public java.sql.ResultSet ConsultarCheque(String sql){	
			java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT d.numero_documento,dd.documento_referencia,d.fecha,d.detalle, t.razon_social,d.valor_credito FROM cont_documentos d, cont_detalle_documentos dd,cont_terceros t WHERE d.tipo_documento_fk='140' AND d.codigo=dd.cod_documento_fk AND dd.cod_terceros_fk=t.codigo  "+sql+"  GROUP BY d.numero_documento" );
	        	System.out.println("SELECT d.numero_documento,dd.documento_referencia,d.fecha,d.detalle, t.razon_social,d.valor_credito FROM cont_documentos d, cont_detalle_documentos dd,cont_terceros t WHERE d.tipo_documento_fk='140' AND d.codigo=dd.cod_documento_fk AND dd.cod_terceros_fk=t.codigo  "+sql+"  GROUP BY d.numero_documento" );
	 	       
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoDocumentos>>ConsultarCheque "+ex);
	        }	
	        return rs;
	    }
		

		public void AcumuladosSCCaCero(String a, String p){	
			/**
			 */
			 PreparedStatement st = null;
		     try{
		     	Conexion con=new Conexion();
		     	st= con.conn.prepareStatement(" UPDATE cont_detalle_acumulado_surcursal_ccosto,cont_acumulado_sucursal_centrocosto SET cont_detalle_acumulado_surcursal_ccosto.debito='0', cont_detalle_acumulado_surcursal_ccosto.credito='0' WHERE  cont_detalle_acumulado_surcursal_ccosto.cod_acu_sucur_ccosto_fk=cont_acumulado_sucursal_centrocosto.codigo AND cont_acumulado_sucursal_centrocosto.anio='"+a+"' AND cont_detalle_acumulado_surcursal_ccosto.periodo>="+p+"  ");
		     	st.executeUpdate();	 
		     	st.close();
		     	con.cerrar();
		     }
		     catch(Exception ex){
		     	System.out.println("Error en MetodoDocumentos>>AcumuladosSCCaCero "+ex);
		     	ex.getMessage();	     
		     }	
		 }
		
		public void AcumuladosTaCero(String a, String p){	
			/**
			 */
			 PreparedStatement st = null;
		     try{
		     	Conexion con=new Conexion();
		     	st= con.conn.prepareStatement(" UPDATE cont_detalle_acumulado_tercero,cont_acumulado_tercero SET cont_detalle_acumulado_tercero.debito='0', cont_detalle_acumulado_tercero.credito='0'  WHERE  cont_detalle_acumulado_tercero.cod_acumulado_tercero_fk=cont_acumulado_tercero.codigo AND cont_acumulado_tercero.anio='"+a+"' AND cont_detalle_acumulado_tercero.periodo>="+p+"  ");
		     	st.executeUpdate();	 
		     	st.close();
		     	con.cerrar();
		     }
		     catch(Exception ex){
		     	System.out.println("Error en MetodoDocumentos>>AcumuladosTaCero "+ex);
		     	ex.getMessage();	     
		     }	
		 }
		
		public java.sql.ResultSet ConsultarDocyDet(String ac, String pc){	
			java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT d.anio, d.periodo, dd.cod_cuenta_fk, dd.cod_sucursal_costo_fk, dd.cod_centro_subcentro_fk, dd.cod_terceros_fk, dd.valor_debito, dd.valor_credito, c.CodigoCuenta FROM cont_documentos d, cont_detalle_documentos dd, cont_cuentas c WHERE d.codigo=dd.cod_documento_fk AND d.anio='"+ac+"' AND d.periodo>="+pc+" AND dd.cod_cuenta_fk=c.codigo   ");
	        	System.out.println("SELECT d.anio, d.periodo, dd.cod_cuenta_fk, dd.cod_sucursal_costo_fk, dd.cod_centro_subcentro_fk, dd.cod_terceros_fk, dd.valor_debito, dd.valor_credito, c.CodigoCuenta FROM cont_documentos d, cont_detalle_documentos dd, cont_cuentas c WHERE d.codigo=dd.cod_documento_fk AND d.anio='"+ac+"' AND d.periodo>="+pc+" AND dd.cod_cuenta_fk=c.codigo   ");
	 	       
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoDocumentos>>ConsultarDocyDet "+ex);
	        }	
	        return rs;
	    }
		
		
		//////////////////ACTUALIZACION DE SALDOS////////////////
		public java.sql.ResultSet AcumuladosSconsaldo(String ac){	
			java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT ac.*,dac.* FROM cont_acumulado_sucursal_centrocosto ac,cont_detalle_acumulado_surcursal_ccosto dac WHERE  ac.anio='"+ac+"' AND ac.codigo=dac.cod_acu_sucur_ccosto_fk AND dac.periodo='13' AND dac.saldo_nuevo!=0");
	        	//System.out.println("SELECT d.numero_documento,dd.documento_referencia,d.fecha,d.detalle, t.razon_social,d.valor_credito FROM cont_documentos d, cont_detalle_documentos dd,cont_terceros t WHERE d.tipo_documento_fk='310' AND d.codigo=dd.cod_documento_fk AND dd.cod_terceros_fk=t.codigo  "+sql+"  GROUP BY d.numero_documento" );
	 	     }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoDocumentos>>AcumuladosSconsaldo "+ex);
	        }	
	        return rs;
	    }
		
		public java.sql.ResultSet AcumuladosTconsaldo(String ac){	
			java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT ac.*,dac.* FROM cont_acumulado_tercero ac,cont_detalle_acumulado_tercero dac WHERE  ac.anio='"+ac+"' AND  ac.codigo=dac.cod_acumulado_tercero_fk AND dac.periodo='13' AND dac.saldo_nuevo!=0");
	        	//System.out.println("SELECT d.numero_documento,dd.documento_referencia,d.fecha,d.detalle, t.razon_social,d.valor_credito FROM cont_documentos d, cont_detalle_documentos dd,cont_terceros t WHERE d.tipo_documento_fk='310' AND d.codigo=dd.cod_documento_fk AND dd.cod_terceros_fk=t.codigo  "+sql+"  GROUP BY d.numero_documento" );
	 	     }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoDocumentos>>AcumuladosTconsaldo "+ex);
	        }	
	        return rs;
	    }
		
		public java.sql.ResultSet Detalle_Acumulado_Sucursal_CentroccoPeriodoAnterior(String cod, String p){	
			java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery(" SELECT codigo FROM cont_detalle_acumulado_surcursal_ccosto WHERE cod_acu_sucur_ccosto_fk="+cod+" AND periodo='"+p+"' ");
	        	//System.out.println(" SELECT saldo_anterior,debito,credito,saldo_nuevo FROM cont_detalle_acumulado_surcursal_ccosto WHERE cod_acu_sucur_ccosto_fk="+cod+" AND periodo='"+p+"' ");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoDocumentos>>Detalle_Acumulado_Sucursal_CentroccoPeriodoAnterior "+ex);
	        }	
	        return rs;
	    }
		
		public java.sql.ResultSet Detalle_Acumulado_TerceroPeriodoAnterior(String cod, String p){	
			java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery(" SELECT codigo FROM cont_detalle_acumulado_tercero WHERE cod_acumulado_tercero_fk="+cod+" AND periodo='"+p+"' ");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoDocumentos>>Detalle_Acumulado_Sucursal_CentroccoPeriodo "+ex);
	        }	
	        return rs;
	    }
		
		public java.sql.ResultSet CuentasCausa(String cod){	
			java.sql.ResultSet rs=null;
			Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery(" SELECT codigo,CodigoCuenta,NombreCuenta FROM cont_cuentas WHERE TipoCuenta='Auxiliar' AND CodigoCuenta LIKE '"+cod+"%' limit 200");
	        	System.out.println(" SELECT codigo,CodigoCuenta,NombreCuenta FROM cont_cuentas WHERE TipoCuenta='Auxiliar' AND CodigoCuenta LIKE '"+cod+"%' limit 200");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoDocumentos>>CuentasCausa "+ex);
	        }	
	        return rs;
	    }
		
		public java.sql.ResultSet CompletaTerceros(String cod){	
			java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT codigo, numero_documento, razon_social FROM cont_terceros WHERE numero_documento LIKE '"+cod+"%' limit 200 ");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoDocumentos>>CompletaTerceros "+ex);
	        }	
	        return rs;
	    }
		
		public java.sql.ResultSet OrdenTerceros(String cod){	
			java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT * FROM com_orden WHERE com_proveedor_codigo="+cod+" AND estado=1 AND causacion=0  ");
	        	System.out.println("SELECT * FROM com_orden WHERE com_proveedor_codigo="+cod+" AND estado=1 AND causacion=0");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoDocumentos>>CompletaTerceros "+ex);
	        }	
	        return rs;
	    }
		
		public java.sql.ResultSet DetOrden(String cod){	
			java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT * FROM com_orden orden, com_detalle_orden det, com_producto pro WHERE orden.id_orden="+cod+"  AND det.com_orden_id_orden=orden.id_orden AND pro.codigo=det.com_producto_codigo ");
	        	System.out.println("SELECT * FROM com_orden orden, com_detalle_orden det, com_producto pro WHERE orden.id_orden="+cod+"  AND det.com_orden_id_orden=orden.id_orden AND pro.codigo=det.com_producto_codigo");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoDocumentos>>CompletaTerceros "+ex);
	        }	
	        return rs;
	    }
		
		public java.sql.ResultSet CuentaGrupo(String cod){	
			java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT * FROM cont_producto_cuenta cpc, cont_cuentas cc WHERE cpc.codigo="+cod+" AND cc.codigo=cpc.cod_cuenta");
	        	System.out.println("SELECT * FROM cont_producto_cuenta cpc, cont_cuentas cc WHERE cpc.codigo="+cod+" AND cc.codigo=cpc.cod_cuenta");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoDocumentos>>CompletaTerceros "+ex);
	        }	
	        return rs;
	    }
		
		public java.sql.ResultSet CuentaSubGrupo(String cod){	
			java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT * FROM cont_subproducto_cuenta cspc, cont_cuentas cc WHERE cspc.Codigo="+cod+" AND cc.codigo=cspc.Cod_Cuenta");
	        	System.out.println("SELECT * FROM cont_subproducto_cuenta cspc, cont_cuentas cc WHERE cspc.Codigo="+cod+" AND cc.codigo=cspc.Cod_Cuenta");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoDocumentos>>CompletaTerceros "+ex);
	        }	
	        return rs;
	    }
		
		public void OrdenCausacion(String ord, String doc){	
			 PreparedStatement st = null;
		     try{
		     	Conexion con=new Conexion();
		     	st= con.conn.prepareStatement(" UPDATE com_orden SET causacion='"+doc+"'  WHERE id_orden="+ord+"  ");
		     	System.out.println(" UPDATE com_orden SET causacion='"+doc+"'  WHERE id_orden="+ord+"  ");
		     	st.executeUpdate();	 
		     	st.close();
		     	con.cerrar();
		     }
		     catch(Exception ex){
		     	System.out.println("Error en MetodoDocumentos>>AcumuladosTaCero "+ex);
		     	ex.getMessage();	     
		     }	
		 }
		
		
}//Fin MetodoDocumentos
