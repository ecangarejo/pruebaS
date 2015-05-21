package ord_metodo;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;

import adm_logica.Conexion;

public class MetodoDescOC {
	
	public java.sql.ResultSet BuscarMeses(){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT * FROM pre_meses order by codigo ");
	        	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoDescOC>>BuscarMeses"+ex);
	            }	
	            return rs;
	}
	
	public java.sql.ResultSet BuscarDetOC(String codoc){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT  od.cod_referencia, od.nombre, od.cant, od.precio_unitario, od.descuento, od.valor_iva , oi.valor,od.cod_productofk, od.codigo,od.cant_desc, oc.* " +
	        						"FROM ord_detalle_ordencompra od, ord_orden_compra oc, ord_iva oi WHERE (od.estado!=3 AND od.estado!=5 and od.estado!=31 and od.estado!=36 ) AND oi.codigo=od.cod_ivafk AND oc.codigo=od.cod_ordencomprafk AND od.cod_ordencomprafk="+codoc+" ");
	        	
	        	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoDescOC>>BuscarDetOC"+ex);
	            }	
	            return rs;
	}
	
	
	public java.sql.ResultSet BusFact(String codprove, String nofactura){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT * FROM ord_orden_compra o, ord_contpredoc oc WHERE o.codigo=oc.cod_ordfk AND oc.nofactura='"+nofactura+"' AND o.cod_proveedorfk='"+codprove+"' LIMIT 1 ");
	        	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoDescOC>>BusFact "+ex);
	            }	
	            return rs;
	}
	
	
	public java.sql.ResultSet BuscAgruDetOc(String codigo){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery(" SELECT od.cant, (od.cant*od.precio_unitario) AS precio, od.descuento, od.valor_iva,od.valor_total, p.cod_cuentafk, i.valor, r.base_uvt, r.uvt, r.valor, iv.cod_cuentafk as codctaiva, r.cod_cuentafk, i.cod_ctaicfk "+
	        						"FROM ord_detalle_ordencompra od, ord_producto p, ord_prove_actecono a, ord_ica i, ord_retefuente r, ord_iva iv "+
	        						"WHERE   od.cod_productofk=p.codigo AND a.cod_provefk=od.cod_proveedorfk AND a.cod_icafk=i.codigo AND a.cod_reftefk=r.codigo and iv.codigo=od.cod_ivafk AND  od.cod_ordencomprafk='"+codigo+"' "+
	        					"GROUP BY od.codigo ");
	        	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoDescOC>>BuscAgruDetOc "+ex);
	            }	
	            return rs;
	}
	
	
	public java.sql.ResultSet BuscarTipos(){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT * FROM ord_tipoproducto order by nombre ASC ");
	        	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoDescOC>>BuscarTipos "+ex);
	            }	
	            return rs;
	}
	
	public java.sql.ResultSet BuscarProve(String texto){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT fp.codigo, fp.nit,fp.razon_social, oa.codigo_ciiu,oa.actividad_economica, oc.valor, oi.valor , orf.valor, ofp.descripcion, opa.cta "+
	        					"FROM farc_proveedor fp, ord_prove_actecono opa , ord_ica oi, ord_retefuente orf, ord_formadepago ofp, ord_cre oc, ord_actividadeconomica oa "+
	        					"WHERE fp.codigo=opa.cod_provefk AND opa.cod_aecofk=oa.codigo AND opa.cod_formapagofk=ofp.codigo AND opa.cod_icafk=oi.codigo AND opa.cod_reftefk=orf.codigo "+
	        					"AND oa.cod_crefk=oc.codigo AND ((fp.nit like '%"+texto+"%') OR (fp.razon_social like '%"+texto+"%')) ");
	        	
	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoDescOC>>BuscarProve "+ex);
	            }	
	            return rs;
	}
	
	
	public java.sql.ResultSet BusProveOrd(String codigo){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT fp.codigo, fp.nit,fp.razon_social, oa.codigo_ciiu,oa.actividad_economica,fp.direccion, fp.telefono,oc.valor, oi.valor , orf.valor,ofp.codigo, ofp.descripcion, opa.cta "+
	        					"FROM farc_proveedor fp, ord_prove_actecono opa , ord_ica oi, ord_retefuente orf, ord_formadepago ofp, ord_cre oc, ord_actividadeconomica oa "+
	        					"WHERE fp.codigo=opa.cod_provefk AND opa.cod_aecofk=oa.codigo AND opa.cod_formapagofk=ofp.codigo AND opa.cod_icafk=oi.codigo AND opa.cod_reftefk=orf.codigo "+
	        					"AND oa.cod_crefk=oc.codigo AND fp.codigo='"+codigo+"' ");
	        
	        	
	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoDescOC>>BusProveOrd "+ex);
	            }	
	            return rs;
	}
	
	
	public java.sql.ResultSet BuscarSucursal(){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT * FROM cont_sucursales order by nombre asc ");
	        	
	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoDescOC>>BuscarSucursal "+ex);
	            }	
	            return rs;
	}
	
	
	public java.sql.ResultSet BusProducto(String tip, String texto,String codprove){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT * FROM ord_producto p, ord_prove_prod op WHERE op.cod_productofk=p.codigo AND  op.cod_proveedorfk='"+codprove+"' AND p.tipo_productofk='"+tip+"' and p.estado=0 AND ((p.cod_referencia LIKE '%"+texto+"%') OR (p.nombre LIKE '%"+texto+"%')) ");
	        	
	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoDescOC>>BusProducto "+ex);
	            }	
	            return rs;
	}
	
	public java.sql.ResultSet VerifTarifa(String codprove,String codproducto){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT t.* , i.codigo, i.valor FROM ord_tarifas t, ord_iva i where i.codigo=t.cod_ivafk and t.cod_productofk='"+codproducto+"' AND t.cod_proveedorfk='"+codprove+"' ");
	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoDescOC>>VerifTarifa "+ex);
	            }	
	            return rs;
	}
	
	public java.sql.ResultSet BusOCA(String texto){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT * FROM ord_orden_compra where estado=2 and consec like '%"+texto+"%'");
	        	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoDescOC>>BusOCA"+ex);
	            }	
	            return rs;
	}
	
	
	public java.sql.ResultSet BusDescTipo(String cod){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT * FROM ord_tipoproducto where codigo='"+cod+"' ");
	        	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoDescOC>>BusDescTipo"+ex);
	            }	
	            return rs;
	}
	
	
	public java.sql.ResultSet BuscarOCA(String codigo){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT * FROM ord_orden_compra where codigo='"+codigo+"'");
	        	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoDescOC>>BuscarOCA"+ex);
	            }	
	            return rs;
	}
	
	public java.sql.ResultSet BuscarProveedor(String codigo){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT * FROM farc_proveedor where codigo='"+codigo+"' ");
	        	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoDescOC>>BuscarProveedor"+ex);
	            }	
	            return rs;
	}
	
	public java.sql.ResultSet BSucursal(String codigo){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT * FROM cont_sucursales where codigo='"+codigo+"' ");
	        	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoDescOC>>BSucursal"+ex);
	            }	
	            return rs;
	}
	
	public java.sql.ResultSet Bcentrocosto(String codigo){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT * FROM  fact_centrocosto where codigo='"+codigo+"' ");
	        	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoDescOC>>Bcentrocosto "+ex);
	            }	
	            return rs;
	}
	
	public java.sql.ResultSet Bsubcc(String codigo){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT * FROM  fact_subcentros_costo where cod_subcentro_costo='"+codigo+"' ");
	        	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoDescOC>>Bsubcc "+ex);
	            }	
	            return rs;
	}
	
	public java.sql.ResultSet Buscarfact(String fact, String codoc){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT * FROM cont_predoc WHERE no_factura='"+fact+"' AND cod_ordenfk='"+codoc+"' LIMIT 1 ");
	        	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoDescOC>>Buscarfact "+ex);
	            }	
	            return rs;
	}
	
	public java.sql.ResultSet BusTipoProd(String producto){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT tp.codigo, tp.nombre "+
	        						"FROM ord_producto p, ord_tipoproducto tp "+
	        						"WHERE p.tipo_productofk=tp.codigo AND p.codigo='"+producto+"' ");
	        	System.out.println("SELECT tp.codigo, tp.nombre "+
						"FROM ord_producto p, ord_tipoproducto tp "+
						"WHERE p.tipo_productofk=tp.codigo AND p.codigo='"+producto+"' ");
	        	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoDescOC>>BusTipoProd "+ex);
	            }	
	            return rs;
	}
	
	
	public java.sql.ResultSet BusProdenOC(String coddet){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT * FROM ord_detalle_ordencompra od WHERE od.codigo='"+coddet+"' ");
	        	//System.out.println("VERIFF SELECT * FROM ord_detalle_ordencompra od WHERE od.codigo='"+coddet+"' ");
	        	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoDescOC>>BusProdenOC "+ex);
	            }	
	            return rs;
	}
	
	public java.sql.ResultSet BusBodInventario(){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT * FROM inv_bodegas ");
	        	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoDescOC>>BusBodInventario "+ex);
	            }	
	            return rs;
	}
	
	
	public java.sql.ResultSet BusDetOC(String coddet){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT o.*, tp.codigo  FROM ord_detalle_ordencompra o, ord_producto p, ord_tipoproducto tp where o.cod_productofk=p.codigo AND p.tipo_productofk=tp.codigo AND o.codigo='"+coddet+"' ");
	        	System.out.println("SELECT o.*, tp.codigo  FROM ord_detalle_ordencompra o, ord_producto p, ord_tipoproducto tp where o.cod_productofk=p.codigo AND p.tipo_productofk=tp.codigo AND o.codigo='"+coddet+"' ");
	        	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoDescOC>>BusDetOC "+ex);
	            }	
	            return rs;
	}
	
	public java.sql.ResultSet BuscarTercero(String codprove){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT * FROM ord_prov_terc where cod_provefk='"+codprove+"' ");
	        	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoDescOC>>BuscarTercero "+ex);
	            }	
	            return rs;
	}
	
	
	public java.sql.ResultSet BMovimiento(String texto){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT * FROM farc_tipomovimiento where descripcion='"+texto+"' ");
	        	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoDescOC>>BMovimiento "+ex);
	            }	
	            return rs;
	}
	
	public java.sql.ResultSet BConsecutivo(String cod){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT * FROM farc_consecutivos where codigo='"+cod+"' ");
	        	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoDescOC>>BConsecutivo "+ex);
	            }	
	            return rs;
	}
	
	public void ActConsecutivo(String codigo, long consEnt){
		
		try{		
				PreparedStatement ps = null;
			    Conexion con=new Conexion();													
			    ps=con.conn.prepareStatement("UPDATE farc_consecutivos SET consec='"+consEnt+"' WHERE codigo='"+codigo+"'");
			    ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoDescOC>>ActConsecutivo "+ex);
			}
		}
	
	public void EliminarCargue(String coddet){
		
		try{		
				PreparedStatement ps = null;
			    Conexion con=new Conexion();													
			    ps=con.conn.prepareStatement("UPDATE ord_detalle_ordencompra SET estado='3' WHERE codigo='"+coddet+"'");
			    ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoDescOC>>EliminarCargue "+ex);
			}
		}
	
	
	
public void DetDocumento(String codterc,String doc,String codsucycc,String codcentrosubc,String codcta,long preciodeb,long valcredito,String origen,String obs){
		
		try{		
				PreparedStatement ps = null;
			    Conexion con=new Conexion();													
			    ps=con.conn.prepareStatement("INSERT INTO cont_detalle_documentos (cod_documento_fk, cod_cuenta_fk,cod_sucursal_costo_fk,cod_centro_subcentro_fk,cod_terceros_fk,descripcion,valor_base,documento_referencia,valor_debito,valor_credito,origen,cod_diferido_fk) values (?,?,?,?,?,?,?,?,?,?,?,?) ");
			    ps.setString(1, doc);
			    ps.setString(2, codcta);
			    ps.setString(3, codsucycc);
			    ps.setString(4, codcentrosubc);
			    ps.setString(5, codterc);
			    ps.setString(6, obs);
			    ps.setString(7, "0");
			    ps.setString(8, "");
			    ps.setLong(9, preciodeb);
			    ps.setLong(10,valcredito);
			    ps.setString(11,origen);
			    ps.setString(12,"0");
			    ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoDescOC>>DetDocumento "+ex);
			}
		}
	
	
	public void GuardarInvenFarm(String cant,String codmov,Date Fecha,Time Hora,String user,String nsoporte,String concept,String consEnt,String codprod,String lote, String invima, String fechavenc,String valor_iva,long cantsol, long vunitario,long desc,String BodFarmacia,String codprove,String consecOC, String nofactura,String codoc, double sumcantf) throws SQLException{
		
		 java.sql.ResultSet rs=null;
		 java.sql.ResultSet rs1=null;
		 java.sql.ResultSet rs2=null;
		 java.sql.ResultSet rs3=null;
		 java.sql.ResultSet rs4=null;
		 java.sql.ResultSet rss2=null;
		 java.sql.ResultSet rss1=null;
		 Statement st = null;
		 Statement st1 = null;
		 Statement st2 = null;
		 Statement st3 = null;
		 Statement st4 = null;
		 Statement st5 = null;
		 Statement st6 = null;
		 Statement st11 = null;
		try{
			//INSERCION A FARC_MOVIMIENTOS	
			//System.out.println("vencimiento "+fechavenc);
			String pfecha[]=fechavenc.split("/");
			fechavenc=pfecha[2]+"-"+pfecha[1]+"-"+pfecha[0];
			//System.out.println("valor de fechavenc"+fechavenc);
			System.out.println("Entrando a GuardarInveFarm ");
			Conexion con1=new Conexion();
			long sumcantff=(long) sumcantf;
			st1 = con1.conn.createStatement();
			rs=st1.executeQuery("SELECT * FROM  farc_movimientos WHERE cantidad='"+sumcantff+"' AND tipo_mvtoFk='"+codmov+"' AND fecha='"+Fecha+"' AND hora='"+Hora+"' AND nsoporte='"+nsoporte+"' AND concepto='"+concept+"' AND cod_usuarioFk='"+user+"' AND consec='"+consEnt+"'  ");
			String codmovimiento="";
			if(rs.next()){
				codmovimiento=rs.getString(1);
			}else{
				codmovimiento="no";
			}rs.getStatement().getConnection().close();
			con1.cerrar();
			
			System.out.println("antes del si codmovimiento "+codmovimiento);
			if(codmovimiento.equals("no")){
				
				System.out.println("validacion de codmovimiento"+sumcantff);
				PreparedStatement ps = null;
	        	Conexion con=new Conexion();
	        	ps = con.conn.prepareStatement("INSERT INTO farc_movimientos (cantidad,tipo_mvtoFk,fecha,hora,nsoporte,concepto,cod_usuarioFk,consec) VALUES (?,?,?,?,?,?,?,?)");
	        	ps.setLong(1,sumcantff);
	        	ps.setString(2,codmov);
	        	ps.setDate(3,Fecha);
	        	ps.setTime(4,Hora);
	        	ps.setString(5,nsoporte);
	        	ps.setString(6,concept);
	        	ps.setString(7,user);
	        	ps.setString(8,consEnt);
	        	ps.executeUpdate();
				ps.close();
				con.cerrar();	
				//FIN INSERCION
				
				Conexion con11=new Conexion();
				st11 = con11.conn.createStatement();
				rss1=st11.executeQuery("SELECT * FROM  farc_movimientos WHERE cantidad='"+sumcantff+"' AND tipo_mvtoFk='"+codmov+"' AND fecha='"+Fecha+"' AND hora='"+Hora+"' AND nsoporte='"+nsoporte+"' AND concepto='"+concept+"' AND cod_usuarioFk='"+user+"' AND consec='"+consEnt+"'  ");
				System.out.println("SELECT * FROM  farc_movimientos WHERE cantidad='"+sumcantff+"' AND tipo_mvtoFk='"+codmov+"' AND fecha='"+Fecha+"' AND hora='"+Hora+"' AND nsoporte='"+nsoporte+"' AND concepto='"+concept+"' AND cod_usuarioFk='"+user+"' AND consec='"+consEnt+"'  ");
				if(rss1.next()){
					codmovimiento=rss1.getString(1);
				}else{
					codmovimiento="no";
				}rss1.getStatement().getConnection().close();
				con11.cerrar();
				
				
				
			}
			
			
			
			System.out.println("codmovimiento "+codmovimiento);
			// BUSQUEDA DEL CODIGO DEL MOVIMIENTO 
		
			
			// FIN BUSQUEDA 
			
			//BUSQUEDA DEL MEDICAMENTO 
			Conexion con2=new Conexion();
			st2 = con2.conn.createStatement();
			rs1=st2.executeQuery("SELECT * FROM ord_prod_medprog WHERE cod_productofk='"+codprod+"' LIMIT 1 ");
			String codmed="";
			if(rs1.next()){
				codmed=rs1.getString(4);
			}rs1.getStatement().getConnection().close();
			con2.cerrar();
			//FIN BUSQUEDA
			
			//Verificacion DEL IVA 
			Conexion con3=new Conexion();
			st3 = con3.conn.createStatement();
			rs1=st3.executeQuery("SELECT * FROM farc_iva where valor='"+valor_iva+"' ");
			String codiva="";
			if(rs1.next()){
				codiva=rs1.getString(1);
			}else{
				codiva="no";
			}rs1.getStatement().getConnection().close();
			if(codiva.equals("no")){
				PreparedStatement ss= null;
				ss=con3.conn.prepareStatement("INSERT INTO farc_iva (valor,observacion) values (?,?)");
				ss.setString(1,valor_iva);
				ss.setString(2, "CREADO DESDE ORDEN DE COMPRA POR USER"+user);
				ss.executeUpdate();
				ss.close();
				st=con3.conn.createStatement();
				rs2=st.executeQuery("SELECT * FROM farc_iva where valor='"+valor_iva+"' ");
				if(rs2.next()){
					codiva=rs2.getString("1");
				}rs2.getStatement().getConnection().close();
			}
			con3.cerrar();
			// FIN VERIFICACION IVA 
			
			
			long cantrec=Long.parseLong(cant);
			long iva=Long.parseLong(valor_iva);
			long mult=0;
			long opeiva=0;
			long total=0;
			long opedesc=0;
			mult=cantrec*vunitario;
			opedesc=((mult*desc)/100);
			mult=mult-opedesc;
			opeiva=((mult*iva)/100);
			total=mult+opeiva;
			
			//Insercion a farc_inventario 
			
			String codinv="";
			Conexion con5=new Conexion();
			st4 = con5.conn.createStatement();
			rs3=st4.executeQuery("SELECT * FROM farc_inventario where cod_movFk='"+codmovimiento+"' AND  cod_medFk='"+codmed+"' AND vencimiento='"+fechavenc+"' AND lote='"+lote+"' AND cantidad='"+cantrec+"' AND vunitario='"+vunitario+"' AND vtotal='"+total+"' AND cod_ivaFk='"+codiva+"' AND cod_bodegaFk='"+BodFarmacia+"' AND fecha='"+Fecha+"' AND hora='"+Hora+"' AND cod_proveedorFk='"+codprove+"' AND invima='"+invima+"' AND cantinicial='"+cantrec+"' ");
			if(rs3.next()){
				codinv=rs3.getString(1);
			}else{
				codinv="no";
			}rs3.getStatement().getConnection().close();
			con5.cerrar();
			
			if(codinv.equals("no")){
					System.out.println("entrando a validacion de codinv");
					PreparedStatement pp=null;
					Conexion con4=new Conexion();
					pp = con4.conn.prepareStatement("INSERT INTO farc_inventario (cod_movFk, cod_medFk,vencimiento,lote,cantidad,vunitario,vtotal,cod_ivaFk,cod_bodegaFk,fecha,hora,cod_proveedorFk,invima,cantinicial) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
					pp.setString(1, codmovimiento);
					pp.setString(2, codmed);
					pp.setString(3, fechavenc);
					pp.setString(4, lote);
					pp.setLong(5, cantrec);
					pp.setLong(6, vunitario);
					pp.setLong(7,total);
					pp.setString(8,codiva);
					pp.setString(9, BodFarmacia);
					pp.setDate(10,Fecha);
					pp.setTime(11,Hora);
					pp.setString(12,codprove);
					pp.setString(13,invima);
					pp.setLong(14, cantrec);
					pp.executeUpdate();
					pp.close();
					
					st4 = con4.conn.createStatement();
					rs3=st4.executeQuery("SELECT * FROM farc_inventario where cod_movFk='"+codmovimiento+"' AND  cod_medFk='"+codmed+"' AND vencimiento='"+fechavenc+"' AND lote='"+lote+"' AND cantidad='"+cantrec+"' AND vunitario='"+vunitario+"' AND vtotal='"+total+"' AND cod_ivaFk='"+codiva+"' AND cod_bodegaFk='"+BodFarmacia+"' AND fecha='"+Fecha+"' AND hora='"+Hora+"' AND cod_proveedorFk='"+codprove+"' AND invima='"+invima+"' AND cantinicial='"+cantrec+"' ");
					if(rs3.next()){
						codinv=rs3.getString(1);
					}else{
						codinv="no";
					}rs3.getStatement().getConnection().close();
					con4.cerrar();	
			}
			//fin de insercion a farc_inventario 
			
			
			
			///Insercion farc_detallemov
			
			PreparedStatement p=null;
			Conexion con8=new Conexion();
			p=con8.conn.prepareStatement("INSERT INTO farc_detallemov (cod_movFk,cod_invFk,cod_bodegai,cantidad) VALUES (?,?,?,?) ");
			p.setString(1, codmovimiento);
			p.setString(2, codinv);
			p.setString(3, BodFarmacia);
			p.setLong(4,cantrec);
			p.executeUpdate();
			p.close();
			con8.cerrar();
			System.out.println("{1}");
			//fin insercion farc_detallemov
			
			//Insercion a tabla de act_recep
			long cantpend=cantsol-cantrec;
			PreparedStatement pss=null;
			Conexion con6=new Conexion();
			pss = con6.conn.prepareStatement("INSERT INTO farc_act_recep (cod_invfk,numero_orden,numero_factura,cantidad_solicitada,cantidad_recibida,cantidad_pendiente,observacion) VALUES (?,?,?,?,?,?,?) ");
			pss.setString(1,codinv);
			pss.setString(2, consecOC);
			pss.setString(3, nofactura);
			pss.setLong(4,cantsol);
			pss.setLong(5,cantrec);
			pss.setLong(6,cantpend);
			pss.setString(7,concept);
			pss.executeUpdate();
			pss.close();
			con6.cerrar();
			//Fin act recep;
			System.out.println("{2}");
			
			String cod_contpredoc="";
			Conexion con7=new Conexion();
			st5 = con7.conn.createStatement();
			rs4=st5.executeQuery("SELECT * FROM  ord_contpredoc WHERE cod_ordfk='"+codoc+"' AND nofactura='"+nofactura+"' ");
			if(rs4.next()){
				cod_contpredoc=rs4.getString(1);
			}rs4.getStatement().getConnection().close();
			System.out.println("{3} "+cod_contpredoc+"ddd");
			if(cod_contpredoc.equals("")){
				
				PreparedStatement sas= null;
				sas=con7.conn.prepareStatement("INSERT INTO ord_contpredoc (cod_ordfk,nofactura) VALUES (?,?) ");
				sas.setString(1,codoc);
				sas.setString(2,nofactura);
				sas.executeUpdate();
				sas.close();
				//System.out.println("entrando a validacion");
			}
			con7.cerrar();
			Conexion conn7=new Conexion();
				st6=conn7.conn.createStatement();
				rss2=st6.executeQuery("SELECT * FROM  ord_contpredoc WHERE cod_ordfk='"+codoc+"' AND nofactura='"+nofactura+"'  ");
				//System.out.println("SELECT * FROM  ord_contpredoc WHERE cod_ordfk='"+codoc+"' AND nofactura='"+nofactura+"'  ");
				if(rss2.next()){
					cod_contpredoc=rss2.getString(1);
				}rss2.getStatement().getConnection().close();
				conn7.cerrar();
			
			System.out.println("{4}");
			//Insercion a tabla de seguimiento 
			
			
			Conexion con9=new Conexion();
			st5 = con9.conn.createStatement();
			rs4=st5.executeQuery("SELECT * FROM  ord_seguimiento_desc WHERE cod_ordpredoc='"+cod_contpredoc+"' ");
			int val=0;
			if(rs4.next()){
				if((rs4.getString(2).equals("(NULL)"))||(rs4.getString(2).equals(""))){
					
					PreparedStatement sss= null;
					sss=con9.conn.prepareStatement("UPDATE  ord_seguimiento_desc SET cod_consecfk='"+consEnt+"' WHERE cod_ordpredoc='"+cod_contpredoc+"' ");
					sss.executeUpdate();
					sss.close();
					val=1;
				}
			}rs4.getStatement().getConnection().close();
			System.out.println("{5}");
			if(val==0){
				PreparedStatement sss= null;
				sss=con9.conn.prepareStatement("INSERT INTO  ord_seguimiento_desc (cod_consecfk,cod_ordpredoc) VALUES (?,?) ");
				sss.setString(1, consEnt);
				sss.setString(2, cod_contpredoc);
				sss.executeUpdate();
				sss.close();
			}
			con9.cerrar();
			//Fin Insercion tabla seguimiento 
			System.out.println("{6}");
			
		}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoDescOC>>GuardarInvenFarm "+ex);
			}
		return;
	}
	
	
	public java.sql.ResultSet BusOrdcontpre(String nofactura,String codoc){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT * FROM ord_contpredoc WHERE cod_ordfk='"+codoc+"' AND nofactura='"+nofactura+"' ");
	        	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoDescOC>>BusOrdcontpre "+ex);
	            }	
	            return rs;
	}
	
	
	public java.sql.ResultSet BuscarCtaProd(String codigo){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT * FROM ord_producto WHERE codigo='"+codigo+"' ");
	        	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoDescOC>>BuscarCtaProd "+ex);
	            }	
	            return rs;
	}
	
	public java.sql.ResultSet BuscarCta(String texto){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT codigo,CodigoCuenta,NombreCuenta FROM cont_cuentas where TipoCuenta='Auxiliar' and (CodigoCuenta LIKE '%"+texto+"%' OR NombreCuenta LIKE '%"+texto+"%' ) ORDER BY CodigoCuenta   LIMIT 20 ");
	        	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoDescOC>>BuscarCta "+ex);
	            }	
	            return rs;
	}
	
	
	public void ActdetOrdenCompra(String detoc,String cantrec,long cantsol, long cantdes,String user, Date Fecha, Time Hora)throws SQLException{
		
		try{
			
			long cantr=Long.parseLong(cantrec);
			long suma=cantr+cantdes;
			int estado=0;
			if(suma==cantsol){
				estado=5;
			}else{
				if(suma<cantsol){
					estado=4;
				}
			}
			if(estado!=0){
				PreparedStatement ps=null;
				Conexion con=new Conexion();
				ps=con.conn.prepareStatement("UPDATE ord_detalle_ordencompra SET  estado='"+estado+"' , cant_desc='"+suma+"' WHERE codigo='"+detoc+"' ");
				ps.executeUpdate();
	    		ps.close();
	    		con.cerrar();
	    		
	    		String obs="";
	    		if(estado==4){
	    			obs="ORDEN PREDESCARGADA , ";
	    		}else{
	    			if(estado==5){
	    				obs="ORDEN DESCARGADA , ";
	    			}
	    		}
	    		
	    		obs=obs+" CANTIDAD CARGADA ="+cantrec;    	
	    		
	    		PreparedStatement pss = null;
			    Conexion con1=new Conexion();													
			    pss=con1.conn.prepareStatement("INSERT INTO ord_auditaorden (cod_detalleordcomp_fk,estado,descrip,user,fecha, hora) values (?,?,?,?,?,?) ");
			    pss.setString(1,detoc);
			    pss.setInt(2,estado);
			    pss.setString(3,obs);
			    pss.setString(4,user);
			    pss.setDate(5,Fecha);
			    pss.setTime(6,Hora);
			    pss.executeUpdate();
				pss.close();
				con1.cerrar();
			}
			
			
		}catch(Exception ex){
			System.out.println("Error en MetodoDescOC>>ActdetOrdenCompra "+ex);
		}	
	}
	
	public void CrearCont_Predoc(String codterc,String suc_ccosto,String cen_succ,String codoc,String nofactura,String codcuentaprove,String codctaprod,String codprod,Date Fecha,Time Hora,String fecha,String cod_ordcontpre ,String user,String cantrecibida,long cantsol,long vunitario, long desc,long viva,String cod_iva,String concept,String codprove, long sumtotal) throws SQLException{
	 
		java.sql.ResultSet rs=null;
		java.sql.ResultSet rs1=null;
		java.sql.ResultSet rs2=null;
		java.sql.ResultSet rs3=null;
		java.sql.ResultSet rs4=null;
		java.sql.ResultSet rs5=null;
		java.sql.ResultSet rs6=null;
		java.sql.ResultSet rs7=null;
		java.sql.ResultSet rs8=null;
		java.sql.ResultSet rs9=null;
        Statement st = null;
        Statement st1 = null;
        Statement st2 = null;
        Statement st3 = null;
        Statement st4 = null;
        Statement st5 = null;
        Statement st6 = null;
        Statement st7 = null;
        Statement st8 = null;
        
        try{
        	
        	long cantrec=Long.parseLong(cantrecibida);
			long mult=0;
			long opeiva=0;
			long total=0;
			long opedesc=0;
			mult=cantrec*vunitario;
			opedesc=((mult*desc)/100);
			mult=mult-opedesc;
			opeiva=((mult*viva)/100);
			total=mult+opeiva;
			/* Insercion de la cuenta  relacionada al producto va a debito*/
			//System.out.println("Valor de fecha de descargue"+fecha);
			
			String detfecha[]=fecha.split("-");
			String anio=detfecha[0];
			String periodo=detfecha[1];
        	PreparedStatement ps=null;
        	Conexion con=new Conexion();
        	ps=con.conn.prepareStatement("INSERT INTO cont_predoc (cod_cuentafk,cod_ordenfk,no_factura,cod_tercerofk,cod_sucursal_ccostofk,cod_centro_subcentrofk,detalle,observacion,valor_base,valor_debito,valor_credito,fecha_descargue,fecha_insercion,hora_insercion,cod_userfk,cod_productofk,cod_ord_contprefk,anio,periodo) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        	ps.setString(1,codctaprod); 
        	ps.setString(2,codoc);
        	ps.setString(3,nofactura);
        	ps.setString(4, codterc);
        	ps.setString(5,suc_ccosto);
        	ps.setString(6,cen_succ);
        	ps.setString(7,concept);
        	ps.setString(8,"");
        	ps.setString(9,"0");
        	ps.setLong(10,mult);
        	ps.setLong(11,0);
        	ps.setString(12,fecha);
        	ps.setDate(13,Fecha);
        	ps.setTime(14,Hora);
        	ps.setString(15,user);
        	ps.setString(16,codprod);
        	ps.setString(17,cod_ordcontpre);
        	ps.setString(18,anio);
        	ps.setString(19, periodo);
        	ps.executeUpdate();
    		ps.close();
    		con.cerrar();
    		/*  Fin de insercion */
    		System.out.println("[1]");
    		/*Busqueda de la cuenta relacionada al iva*/
    		String cod_ctaiva="";
    		Conexion con1=new Conexion();
    		st = con1.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM ord_iva where codigo='"+cod_iva+"' ");
        	//System.out.println("SELECT * FROM ord_iva where codigo='"+cod_iva+"' ");
        	if(rs.next()){
        		//System.out.println("entrando a validacion de iva ");
        		cod_ctaiva=rs.getString(4);
        	}rs.getStatement().getConnection().close();
    		con1.cerrar();
    		/*fin de busqueda */
    		System.out.println("[2]");
        	/*Busqueda de codigos de cta  de impuestos  del proveedor */
        	String cod_ctacre="";
        	String cod_ctarfte="";
        	String cod_ctaica="";
        	double v_cre=0;
        	double v_rfte=0;
        	double v_ica=0;
        	long uvt=0;
        	int vali=0;
        	long rfte=0;
        	long cre=0;
        	long ica=0;
        	Conexion con2=new Conexion();
        	st1=con2.conn.createStatement();
        	rs1=st1.executeQuery("SELECT p.codigo, oc.cod_ctacrefk, oc.valor, oic.valor, oic.cod_ctaicfk, ore.valor, ore.base_uvt, ore.uvt, ore.cod_cuentafk "+
        						"FROM ord_prove_actecono p , ord_actividadeconomica oa, ord_cre oc, ord_ica oic, ord_retefuente ore "+
        						"WHERE p.cod_aecofk=oa.codigo AND oa.cod_crefk=oc.codigo AND oic.codigo=p.cod_icafk AND ore.codigo=p.cod_reftefk AND p.cod_provefk='"+codprove+"' ");
        	System.out.println("SELECT p.codigo, oc.cod_ctacrefk, oc.valor, oic.valor, oic.cod_ctaicfk, ore.valor, ore.base_uvt, ore.uvt, ore.cod_cuentafk "+
					"FROM ord_prove_actecono p , ord_actividadeconomica oa, ord_cre oc, ord_ica oic, ord_retefuente ore "+
					"WHERE p.cod_aecofk=oa.codigo AND oa.cod_crefk=oc.codigo AND oic.codigo=p.cod_icafk AND ore.codigo=p.cod_reftefk AND p.cod_provefk='"+codprove+"' ");
        	if(rs1.next()){
        		cod_ctacre=rs1.getString(2);
        		cod_ctarfte=rs1.getString(9);
        		cod_ctaica=rs1.getString(5);
        		
        		/* calculo del cre */
        		v_cre=((mult*(rs1.getDouble(3)))/100);
        		cre=(new Double(v_cre)).longValue();
        		System.out.println("Para un total de "+mult+"valor de cre"+cre);
        		/* fin*/
        		
        		/* calculo del ica */
        		v_ica=((mult)*(rs1.getDouble(4))/1000);
        		ica=(new Double(v_ica)).longValue();
        		System.out.println("Para un total de "+mult+"valor de ica"+cre);
        		/* fin*/
        		
        		/* calculo del rfte */
        		
        		uvt=((rs1.getLong(7))*(rs1.getLong(8)));
        		if((sumtotal>uvt)||(sumtotal==uvt)){
        			v_rfte=(((mult)*(rs1.getDouble(6)))/100);
        			rfte=(new Double(v_rfte)).longValue();
        		}
        		System.out.println("Para un total de "+mult+"valor de rfte"+rfte);
        		/* fin*/
        	}rs1.getStatement().getConnection().close();
        	con2.cerrar();
        	/*Fin de la busqueda*/
        	
        	Conexion conn1=new Conexion();
        	st8=conn1.conn.createStatement();
        	rs9=st8.executeQuery("SELECT p.clase_contribuyente, d.nombre, e.departamento FROM farc_proveedor p, adm_municipio m, adm_departamento d, empresa e WHERE p.municipio_fk=m.muni_cod AND d.dept_codigo=m.dept_codigo_fk  and p.codigo='"+codprove+"' LIMIT 1 ");
        	String regimen="";
        	String depprove="";
        	String depemp="";
        	if(rs9.next()){
        		regimen=rs9.getString(1);
        		depprove=rs9.getString(2);
        		depemp=rs9.getString(3);
        	}rs9.getStatement().getConnection().close();
        	
        	System.out.println("[3]");
        	/*Insercion de impuestos */
        	
        	PreparedStatement pss=null;
        	Conexion con3=new Conexion();
        	pss=con3.conn.prepareStatement("INSERT INTO cont_predoc (cod_cuentafk,cod_ordenfk,no_factura,cod_tercerofk,cod_sucursal_ccostofk,cod_centro_subcentrofk,detalle,observacion,valor_base,valor_debito,valor_credito,fecha_descargue,fecha_insercion,hora_insercion,cod_userfk,cod_productofk,cod_ord_contprefk,anio,periodo) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        	pss.setString(1, cod_ctaiva); 
        	pss.setString(2,codoc);
        	pss.setString(3,nofactura);
        	pss.setString(4, codterc);
        	pss.setString(5,suc_ccosto);
        	pss.setString(6,cen_succ);
        	pss.setString(7,concept);
        	pss.setString(8, "");
        	pss.setString(9, "0");
        	pss.setLong(10,opeiva);
        	pss.setLong(11, 0);
        	pss.setString(12, fecha);
        	pss.setDate(13, Fecha);
        	pss.setTime(14, Hora);
        	pss.setString(15, user);
        	pss.setString(16, codprod);
        	pss.setString(17, cod_ordcontpre);
        	pss.setString(18,anio);
        	pss.setString(19, periodo);
        	pss.executeUpdate();
    		pss.close();
    		con3.cerrar();
        	
    		System.out.println("[4]");
    		if((depprove.equals(depemp))&&(!regimen.equals("Grandes Contribuyentes"))&&((sumtotal>uvt)||(sumtotal==uvt))){
	    		PreparedStatement pp=null;
	        	Conexion con4=new Conexion();
	        	pp=con4.conn.prepareStatement("INSERT INTO cont_predoc (cod_cuentafk,cod_ordenfk,no_factura,cod_tercerofk,cod_sucursal_ccostofk,cod_centro_subcentrofk,detalle,observacion,valor_base,valor_debito,valor_credito,fecha_descargue,fecha_insercion,hora_insercion,cod_userfk,cod_productofk,cod_ord_contprefk,anio,periodo) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
	        	pp.setString(1, cod_ctaica); 
	        	pp.setString(2,codoc);
	        	pp.setString(3,nofactura);
	        	pp.setString(4, codterc);
	        	pp.setString(5,suc_ccosto);
	        	pp.setString(6,cen_succ);
	        	pp.setString(7,concept);
	        	pp.setString(8, "");
	        	pp.setString(9, "0");
	        	pp.setLong(10,0);
	        	pp.setLong(11,ica);
	        	pp.setString(12, fecha);
	        	pp.setDate(13, Fecha);
	        	pp.setTime(14, Hora);
	        	pp.setString(15, user);
	        	pp.setString(16, codprod);
	        	pp.setString(17, cod_ordcontpre);
	        	pp.setString(18,anio);
	        	pp.setString(19, periodo);
	        	pp.executeUpdate();
	    		pp.close();
	    		con4.cerrar();
    		}
    		System.out.println("[5]");
    		/*PreparedStatement pps=null;
        	Conexion con5=new Conexion();
        	pps=con5.conn.prepareStatement("INSERT INTO cont_predoc (cod_cuentafk,cod_ordenfk,no_factura,cod_tercerofk,cod_sucursal_ccostofk,cod_centro_subcentrofk,detalle,observacion,valor_base,valor_debito,valor_credito,fecha_descargue,fecha_insercion,hora_insercion,cod_userfk,cod_productofk,cod_ord_contprefk,anio,periodo) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        	pps.setString(1, cod_ctacre); 
        	pps.setString(2,codoc);
        	pps.setString(3,nofactura);
        	pps.setString(4, codterc);
        	pps.setString(5,suc_ccosto);
        	pps.setString(6,cen_succ);
        	pps.setString(7,concept);
        	pps.setString(8, "");
        	pps.setString(9, "0");
        	pps.setLong(10,0);
        	pps.setLong(11,cre);
        	pps.setString(12, fecha);
        	pps.setDate(13, Fecha);
        	pps.setTime(14, Hora);
        	pps.setString(15, user);
        	pps.setString(16, codprod);
        	pps.setString(17, cod_ordcontpre);
        	pps.setString(18,anio);
        	pps.setString(19, periodo);
        	pps.executeUpdate();
    		pps.close();
    		con5.cerrar();
    		*/
    		System.out.println("[6]");
    		if((!regimen.equals("Grandes Contribuyentes"))&&((sumtotal>uvt)||(sumtotal==uvt))){
    		PreparedStatement pe=null;
        	Conexion con6=new Conexion();  
        	pe=con6.conn.prepareStatement("INSERT INTO cont_predoc (cod_cuentafk,cod_ordenfk,no_factura,cod_tercerofk,cod_sucursal_ccostofk,cod_centro_subcentrofk,detalle,observacion,valor_base,valor_debito,valor_credito,fecha_descargue,fecha_insercion,hora_insercion,cod_userfk,cod_productofk,cod_ord_contprefk,anio,periodo) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        	pe.setString(1,cod_ctarfte); 
        	pe.setString(2,codoc);
        	pe.setString(3,nofactura);
        	pe.setString(4,codterc);
        	pe.setString(5,suc_ccosto);
        	pe.setString(6,cen_succ);
        	pe.setString(7,concept);
        	pe.setString(8, "");
        	pe.setString(9, "0");
        	pe.setLong(10,0);
        	pe.setLong(11,rfte);
        	pe.setString(12, fecha);
        	pe.setDate(13, Fecha);
        	pe.setTime(14, Hora);
        	pe.setString(15, user);
        	pe.setString(16, codprod);
        	pe.setString(17, cod_ordcontpre);
        	pe.setString(18,anio);
        	pe.setString(19, periodo);
        	pe.executeUpdate();
    		pe.close();
    		con6.cerrar();
    		}
    		System.out.println("[7]");
        	/*fin de insercion */
    		
    		/*insercion de  cta proveedor*/
    		long v_prove=0;
    		if((depprove.equals(depemp))&&(!regimen.equals("Grandes Contribuyentes"))){
    			v_prove=total-ica;
    		}else{
    			v_prove=total;
    		}
    		v_prove=v_prove-rfte;
    		//v_prove=v_prove-cre;
    		
    		PreparedStatement pes=null;
        	Conexion con7=new Conexion();
        	pes=con7.conn.prepareStatement("INSERT INTO cont_predoc (cod_cuentafk,cod_ordenfk,no_factura,cod_tercerofk,cod_sucursal_ccostofk,cod_centro_subcentrofk,detalle,observacion,valor_base,valor_debito,valor_credito,fecha_descargue,fecha_insercion,hora_insercion,cod_userfk,cod_productofk,cod_ord_contprefk,anio,periodo) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        	pes.setString(1,codcuentaprove); 
        	pes.setString(2,codoc);
        	pes.setString(3,nofactura);
        	pes.setString(4,codterc);
        	pes.setString(5,suc_ccosto);
        	pes.setString(6,cen_succ);
        	pes.setString(7,concept);
        	pes.setString(8, "");
        	pes.setString(9, "0");
        	pes.setLong(10,0);
        	pes.setLong(11,v_prove);
        	pes.setString(12, fecha);
        	pes.setDate(13, Fecha);
        	pes.setTime(14, Hora);
        	pes.setString(15, user);
        	pes.setString(16, codprod);
        	pes.setString(17, cod_ordcontpre);
        	pes.setString(18,anio);
        	pes.setString(19, periodo);
        	pes.executeUpdate();
    		pes.close();
    		con7.cerrar();
    		System.out.println("[8]");
        }catch(Exception ex){
        	System.out.println("Error en MetodoDescOC>>CrearCont_Predoc "+ex);
        }
	}
	
	

	
	public java.sql.ResultSet VerificarTipos(String cod){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT tp.codigo FROM ord_detalle_ordencompra od, ord_producto p, ord_tipoproducto tp "+
	        						"WHERE od.cod_productofk=p.codigo AND p.tipo_productofk=tp.codigo AND od.cod_ordencomprafk='"+cod+"' GROUP BY tp.codigo ");
	        	System.out.println("SELECT tp.codigo FROM ord_detalle_ordencompra od, ord_producto p, ord_tipoproducto tp "+
						"WHERE od.cod_productofk=p.codigo AND p.tipo_productofk=tp.codigo AND od.cod_ordencomprafk='"+cod+"' GROUP BY tp.codigo ");
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoDescOC>>VerificarTipos "+ex);
	            }	
	            return rs;
	}
	
	public java.sql.ResultSet BConsecInv(String cod){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT * FROM inv_consecutivos where codigo='"+cod+"' ");
	        	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoDescOC>>BConsecInv "+ex);
	            }	
	            return rs;
	}
	
	
	public java.sql.ResultSet BuscarDatosProve(String codprove){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT p.clase_contribuyente, d.nombre, e.departamento, opt.cod_cuentafk FROM farc_proveedor p, adm_municipio m, adm_departamento d, empresa e, ord_prov_terc opt WHERE opt.cod_provefk=p.codigo and p.municipio_fk=m.muni_cod AND d.dept_codigo=m.dept_codigo_fk  and p.codigo='"+codprove+"' LIMIT 1 ");
	        	System.out.println("SELECT p.clase_contribuyente, d.nombre, e.departamento, opt.cod_cuentafk FROM farc_proveedor p, adm_municipio m, adm_departamento d, empresa e, ord_prov_terc opt WHERE opt.cod_provefk=p.codigo and p.municipio_fk=m.muni_cod AND d.dept_codigo=m.dept_codigo_fk  and p.codigo='"+codprove+"' LIMIT 1 ");
	        	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoDescOC>>BuscarDatosProve "+ex);
	            }	
	            return rs;
	}
	
	
	public java.sql.ResultSet BuscarConsecDoc(String texto){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT * FROM cont_tipo_documento where sigla='"+texto+"' ");
	        	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoDescOC>>BuscarConsecDoc "+ex);
	            }	
	            return rs;
	}
	
	public void ActConsecInv(String codigo, long consEnt){
		
		try{		
				PreparedStatement ps = null;
			    Conexion con=new Conexion();													
			    ps=con.conn.prepareStatement("UPDATE inv_consecutivos  SET consec='"+consEnt+"' WHERE codigo='"+codigo+"'");
			    ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoDescOC>>ActConsecInv "+ex);
			}
		}
	
	
	
	public void EncabeDocumento(String anno,String perio,Date Fecha,String nofact,String resp,String codterc,String user,Time Hora,String consn){
		java.sql.ResultSet rs=null;
		Statement st = null;
		try{
			
				PreparedStatement ps = null;
			    Conexion con=new Conexion();													
			    ps=con.conn.prepareStatement("INSERT INTO cont_documentos(anio,periodo,tipo_documento_fk,numero_documento,fecha,detalle,cod_plantilla_fk,valor_base,cod_usaurio,fecha_creacion,hora_creacion)VALUES(?,?,?,?,?,?,?,?,?,?,?)");
			    ps.setString(1,anno);
			    ps.setString(2, perio);
			    ps.setString(3, "110");
			    ps.setString(4, consn);
			    ps.setDate(5,Fecha);
			    ps.setString(6," AUTORIZADO  POR "+resp);
			    ps.setString(7,"0");
			    ps.setString(8,"0");
			    ps.setString(9,user);
			    ps.setDate(10,Fecha);
			    ps.setTime(11,Hora);
			    ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoDescOC>>EncabeDocumento "+ex);
			}
		}
	
	
	public void ActualizarConsecutivo(long ctan, String td){	
		/**
		 */
		 PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE cont_tipo_documento SET consecutivo='"+ctan+"' WHERE codigo='"+td+"' ");
	     	System.out.println("UPDATE cont_tipo_documento SET consecutivo='"+ctan+"' WHERE codigo='"+td+"' ");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoDescOC>>ActualizarConsecutivo "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public void ActEncabezado(String doc,long totaldebito,long totalcredito){	
		/**
		 */
		 PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE cont_documentos SET valor_debito='"+totaldebito+"' ,valor_credito='"+totalcredito+"' WHERE codigo='"+doc+"' ");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoDescOC>>ActEncabezado "+ex);
	     	ex.getMessage();	     
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
        	System.out.println("Error en MetodoDescOC>>ConsecutivoUDocumento "+ex);
        }	
        return rs;
    }
	
	
	public void CrearProducto(String codcuenta,Date Fecha,Time Hora,String user){
		java.sql.ResultSet rs=null;
		Statement st = null;
		try{
			
			
			Conexion con=new Conexion();
        	st=con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM cont_cuentas where codigo='"+codcuenta+"' ");
			String CodigoCuenta="";
			String NombreCuenta="";
        	if(rs.next()){
				CodigoCuenta=rs.getString(2);
				NombreCuenta=rs.getString(3);
			}rs.getStatement().getConnection().close();
			
				PreparedStatement ps = null;
			    Conexion con1=new Conexion();													
			    ps=con1.conn.prepareStatement("INSERT INTO ord_producto (estado,cod_usuariofk,tipo_productofk,fecha_insercion,hora_insercion,cod_cuentafk,CodigoCuenta,NombreCuenta) values (?,?,?,?,?,?,?,?) ");
			    ps.setString(1,"1");
			    ps.setString(2, user);
			    ps.setString(3, "3");
			    ps.setDate(4,Fecha);
			    ps.setTime(5,Hora);
			    ps.setString(6,codcuenta);
			    ps.setString(7,CodigoCuenta);
			    ps.setString(8, NombreCuenta);
			    ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoDescOC>>CrearProducto "+ex);
			}
		}
	
	
	public void GuardarInvenProducto(String cantrec ,String codmov,Date Fecha,Time Hora,String user,String nsoporte,String concept,String consecEntI,String codprod,String lotep,String fechavenc,long cantsol,long vunitario,long vdesc,long viva,String BodProducto,String codprove,String consecOC,String nofactura,String codoc,String cod_iva, double sumcantp) throws SQLException{
		java.sql.ResultSet rs=null;
		java.sql.ResultSet rs1=null;
		java.sql.ResultSet rs2=null;
		java.sql.ResultSet rs3=null;
		java.sql.ResultSet rs4=null;
		java.sql.ResultSet rs5=null;
		java.sql.ResultSet rs6=null;
		java.sql.ResultSet rs7=null;
		java.sql.ResultSet rs8=null;
        Statement st = null;
        Statement st1 = null;
        Statement st2 = null;
        Statement st3 = null;
        Statement st4 = null;
        Statement st5 = null;
        Statement st6 = null;
        Statement st7 = null;
        
        try{
        	/* Ingreso a inv_pmovimiento */
        	System.out.println("(0)");
        	
        	/*Busqueda del codigo de movimiento en inventario */
			String codmovinv="";
			Conexion con=new Conexion();
        	st=con.conn.createStatement();
        	rs=st.executeQuery(" SELECT * FROM inv_pmovimiento WHERE cant='"+sumcantp+"' AND cod_userfk='"+user+"' AND cod_tipomovfk='"+codmov+"' AND fecha='"+Fecha+"' AND hora='"+Hora+"' AND consec='"+consecEntI+"' AND nsoporte='"+nsoporte+"' AND concepto='"+concept+"' ");
			if(rs.next()){
				codmovinv=rs.getString(1);
			}else{
				codmovinv="no";
			}
			rs.getStatement().getConnection().close();
			/*Fin */
        	if(codmovinv.equals("no")){
	        	PreparedStatement ps = null;
	        	ps = con.conn.prepareStatement("INSERT INTO inv_pmovimiento(cant,cod_userfk,cod_tipomovfk,fecha,hora,consec,nsoporte,concepto) VALUES (?,?,?,?,?,?,?,?) ");
	        	ps.setDouble(1,sumcantp);
	        	ps.setString(2,user);
	        	ps.setString(3,codmov);
	        	ps.setDate(4,Fecha);
	        	ps.setTime(5,Hora);
	        	ps.setString(6,consecEntI);
	        	ps.setString(7,nsoporte);
	        	ps.setString(8,concept);
	        	ps.executeUpdate();
				ps.close();
        	}
        	System.out.println("(1)");
        	/*fin ingreso */
			
			
			System.out.println("(2)");
			long cant=Long.parseLong(cantrec);
			
			long mult=0;
			long opeiva=0;
			long total=0;
			long opedesc=0;
			mult=cant*vunitario;
			opedesc=((mult*vdesc)/100);
			mult=mult-opedesc;
			opeiva=((mult*viva)/100);
			total=mult+opeiva;
			
			System.out.println("(3)");
			//System.out.println("fechavenc"+fechavenc+" valor de codmovinv"+codmovinv);
			String fe[]=fechavenc.split("/");
			String fee=fe[2]+"-"+fe[1]+"-"+fe[0];
			System.out.println("valor de fecha de vencimiento "+fee);
			/*Ingreso a inv_producto */
			 PreparedStatement pss=null;
			 pss=con.conn.prepareStatement("INSERT INTO inv_producto (cod_productofk,cod_bodegafk,cant,cant_ini,vunitario,vtotal,viva,cod_user,fecha,hora,cod_pmovimientofk,cod_provefk,cod_ivafk,lote,fechavenc) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
			 pss.setString(1, codprod);
			 pss.setString(2, BodProducto);
			 pss.setString(3, cantrec);
			 pss.setString(4, cantrec);
			 pss.setLong(5, vunitario);
			 pss.setLong(6, total);
			 pss.setLong(7, viva);
			 pss.setString(8, user);
			 pss.setDate(9, Fecha);
			 pss.setTime(10, Hora);
			 pss.setString(11,codmovinv);
			 pss.setString(12,codprove);
			 pss.setString(13,cod_iva);
			 pss.setString(14, lotep);
			 pss.setString(15, fee);
			 pss.executeUpdate();
			 pss.close();
        	/*fin */
			
			 System.out.println("(4)");
			 
			 String codinv="";
				st1=con.conn.createStatement();
				rs1=st1.executeQuery("SELECT * FROM inv_producto WHERE cod_productofk='"+codprod+"' AND cod_bodegafk='"+BodProducto+"' AND cant='"+cantrec+"' AND cant_ini='"+cantrec+"' AND vunitario='"+vunitario+"' AND  vtotal='"+total+"' AND viva='"+viva+"' AND cod_user='"+user+"' AND fecha='"+Fecha+"' AND hora='"+Hora+"' AND cod_pmovimientofk='"+codmovinv+"' AND cod_provefk='"+codprove+"' AND cod_ivafk='"+cod_iva+"' AND lote='"+lotep+"' AND fechavenc='"+fechavenc+"' ");
				if(rs1.next()){
					codinv=rs1.getString(1);
				}rs1.getStatement().getConnection().close();
			 
				System.out.println("(5)");
        	/*Ingreso a det_inv_movimiento*/
			System.out.println("valor de codinv "+codinv);
			PreparedStatement pp=null;
			pp=con.conn.prepareStatement("INSERT INTO inv_detpmov (cod_pmovfk,cod_invpfk,cod_bodegainifk,cant) VALUES (?,?,?,?)");
			pp.setString(1,codmovinv);
			pp.setString(2, codinv);
			pp.setString(3, BodProducto);
			pp.setString(4,cantrec);
			pp.executeUpdate();
			pp.close();
        	/*fin */
			con.cerrar();	
        	
			System.out.println("(6)");
			String cod_contpredoc="";
			Conexion con7=new Conexion();
			st5 = con7.conn.createStatement();
			rs4=st5.executeQuery("SELECT * FROM  ord_contpredoc WHERE cod_ordfk='"+codoc+"' AND nofactura='"+nofactura+"' ");
			if(rs4.next()){
				cod_contpredoc=rs4.getString(1);
			}rs4.getStatement().getConnection().close();
			if(cod_contpredoc.equals("")){
				
				PreparedStatement sss= null;
				sss=con7.conn.prepareStatement("INSERT INTO ord_contpredoc (cod_ordfk,nofactura) VALUES (?,?) ");
				sss.setString(1,codoc);
				sss.setString(2,nofactura);
				sss.executeUpdate();
				sss.close();
				st6=con7.conn.createStatement();
				rs2=st.executeQuery("SELECT * FROM  ord_contpredoc WHERE cod_ordfk='"+codoc+"' AND nofactura='"+nofactura+"'  ");
				if(rs2.next()){
					cod_contpredoc=rs2.getString("1");
				}rs2.getStatement().getConnection().close();
				
			}
			con7.cerrar();
			
			//Insercion a tabla de seguimiento 
			
			System.out.println("(7)");
			Conexion con9=new Conexion();
			st5 = con9.conn.createStatement();
			rs4=st5.executeQuery("SELECT * FROM  ord_seguimiento_desc WHERE cod_ordpredoc='"+cod_contpredoc+"' ");
			int val=0;
			if(rs4.next()){
				if((rs4.getString(2).equals("(NULL)"))||(rs4.getString(2).equals(""))){
					
					PreparedStatement sss= null;
					sss=con9.conn.prepareStatement("UPDATE  ord_seguimiento_desc SET cod_invprod='"+consecEntI+"' WHERE cod_ordpredoc='"+cod_contpredoc+"' ");
					sss.executeUpdate();
					sss.close();
					val=1;
				}
			}rs4.getStatement().getConnection().close();
			
			if(val==0){
				PreparedStatement sss= null;
				sss=con9.conn.prepareStatement("INSERT INTO  ord_seguimiento_desc (cod_invprod,cod_ordpredoc) VALUES (?,?) ");
				sss.setString(1, consecEntI);
				sss.setString(2, cod_contpredoc);
				sss.executeUpdate();
				sss.close();
			}
			con9.cerrar();
			//Fin Insercion tabla seguimiento 
			
			System.out.println("(8)");
        	
        }catch(Exception ex){
        	System.out.println("ERROR EN MetodoDescOC>>GuardarInvenProducto "+ex);
        }
	}
	
	
	public void HacerEnca(String tip,String codprove,String cprod,String civa,String codref,String nofact,String ndescrip,String precio,String desc,String viva,String total,String user,Date Fecha,Time Hora) throws SQLException{
		java.sql.ResultSet rs=null;
		java.sql.ResultSet rs1=null;
		java.sql.ResultSet rs2=null;
		
        Statement st = null;
        Statement st1 = null;       
        try{ 	/*Este metodo hace el encabezado por  primera vez y  luego lo graba en el detalle */
        	Conexion con=new Conexion();
        	PreparedStatement ps= null;
			ps=con.conn.prepareStatement("INSERT INTO ord_orden_compra (fecha_entrega,cod_usufk,fecha,hora,cod_proveedorfk,tipo_orden) VALUES (?,?,?,?,?,?)");
        	ps.setDate(1, Fecha);
        	ps.setString(2,user);
        	ps.setDate(3,Fecha);
        	ps.setTime(4,Hora);
        	ps.setString(5, codprove);
        	ps.setString(6, "1");//Lo mando siempre como tipo 1 para q se epa que es manual pero cuando se vaya a dar por terminado el cargue es q se define que tipo es. 
        	ps.executeUpdate();
			ps.close();
			
			st1=con.conn.createStatement();
			rs1=st1.executeQuery("SELECT * FROM ord_orden_compra where fecha_entrega='"+Fecha+"' AND cod_usufk='"+user+"' AND fecha='"+Fecha+"' and hora='"+Hora+"' and cod_proveedorfk='"+codprove+"'  and tipo_orden=1 ");
			
			String codoc="";
			if(rs1.next()){
				codoc=rs1.getString(1);
			}rs1.getStatement().getConnection().close();
			
			if(!codoc.equals("")){
				PreparedStatement pss= null;
				pss=con.conn.prepareStatement("INSERT INTO ord_detalle_ordencompra (cod_ordencomprafk,cod_proveedorfk,cod_productofk,cod_referencia,nombre,cant,precio_unitario,descuento,subtotal,valor_iva,valor_total,cod_usufk,fecha,hora,cod_ivafk) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
	        	pss.setString(1, codoc);
	        	pss.setString(2,codprove);
	        	pss.setString(3,cprod);
	        	pss.setString(4,codref);
	        	pss.setString(5,ndescrip);
	        	pss.setString(6, "1");
	        	pss.setString(7, precio);
	        	pss.setString(8, desc);
	        	pss.setString(9, "0");
	        	pss.setString(10, viva);
	        	pss.setString(11, total);
	        	pss.setString(12,user);
	        	pss.setDate(13,Fecha);
	        	pss.setTime(14,Hora);
	        	pss.setString(15, civa);
	           	pss.executeUpdate();
				pss.close();
			}
			
			
        }catch(Exception ex){
        	System.out.println("ERROR EN MetodoDescOC>>HacerEnca "+ex);
        }
	}
	
	
	public void GuardarDetalle(String tip,String codprove,String cprod,String civa,String codref,String nofact,String ndescrip,String precio,String desc,String viva,String total,String user,Date Fecha,Time Hora,String codigo) throws SQLException{
		java.sql.ResultSet rs=null;
		java.sql.ResultSet rs1=null;
		java.sql.ResultSet rs2=null;
		
        Statement st = null;
        Statement st1 = null;       
        try{ 	/*Este metodo hace el encabezado por  primera vez y  luego lo graba en el detalle */
        	Conexion con=new Conexion();
        	
				PreparedStatement pss= null;
				pss=con.conn.prepareStatement("INSERT INTO ord_detalle_ordencompra (cod_ordencomprafk,cod_proveedorfk,cod_productofk,cod_referencia,nombre,cant,precio_unitario,descuento,subtotal,valor_iva,valor_total,cod_usufk,fecha,hora,cod_ivafk) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
	        	pss.setString(1, codigo);
	        	pss.setString(2,codprove);
	        	pss.setString(3,cprod);
	        	pss.setString(4,codref);
	        	pss.setString(5,ndescrip);
	        	pss.setString(6, "1");
	        	pss.setString(7, precio);
	        	pss.setString(8, desc);
	        	pss.setString(9, "0");
	        	pss.setString(10, viva);
	        	pss.setString(11, total);
	        	pss.setString(12,user);
	        	pss.setDate(13,Fecha);
	        	pss.setTime(14,Hora);
	        	pss.setString(15, civa);
	           	pss.executeUpdate();
				pss.close();
			
			//Solo para servicio
        }catch(Exception ex){
        	System.out.println("ERROR EN MetodoDescOC>>GuardarDetalle "+ex);
        }
	}
	
	
	public java.sql.ResultSet BusCodigo(String tip,String codprove,String cprod,String civa,String codref,String nofact,String ndescrip,String precio,String desc,String viva,String total,String user,Date Fecha,Time Hora){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT * FROM ord_orden_compra where fecha_entrega='"+Fecha+"' AND cod_usufk='"+user+"' AND fecha='"+Fecha+"' and hora='"+Hora+"' and cod_proveedorfk='"+codprove+"'  and tipo_orden=1  ");        	
	        	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoDescOC>>BusCodigo "+ex);
	            }	
	            return rs;
	}
	
	
	
	public java.sql.ResultSet BuscarDetalle(String codigo){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT * FROM ord_detalle_ordencompra WHERE cod_ordencomprafk='"+codigo+"' and estado!=3 ");        	
	        	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoDescOC>>BuscarDetalle "+ex);
	            }	
	            return rs;
	}
	
	
	public java.sql.ResultSet BusCodmov(int tipo, String consec,Date Fecha){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	if(tipo==1){ //farmacia
	        		rs=st.executeQuery("SELECT * FROM farc_movimientos WHERE consec='"+consec+"' AND fecha='"+Fecha+"' ORDER BY codigo DESC ");
	        		System.out.println("SELECT * FROM farc_movimientos WHERE consec='"+consec+"' AND fecha='"+Fecha+"' ORDER BY codigo DESC ");
	        	}else{ //inventario
	        		if(tipo==2){
	        			rs=st.executeQuery("SELECT * FROM inv_pmovimiento WHERE consec='"+consec+"' AND fecha='"+Fecha+"' ORDER BY codigo DESC ");
	        		}
	        	}
	        	
	        	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoDescOC>>BuscarCtaProd "+ex);
	            }	
	            return rs;
	}
	
	public java.sql.ResultSet VerifOrden(String codoc){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery(" SELECT * FROM ord_detalle_ordencompra od WHERE  od.cod_ordencomprafk='"+codoc+"' AND  (od.estado=4 OR od.estado=5)  ");        	
	        	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoDescOC>>VerifOrden "+ex);
	            }	
	            return rs;
	}
	

public void CambEstO(String codoc,String est,String descrip,Date Fecha,Time Hora,String user)throws SQLException{
		
	try{
			
			 
	   	PreparedStatement pss = null;
		Conexion con1=new Conexion();													
		pss=con1.conn.prepareStatement("INSERT INTO ord_movestados (cod_ordenfk,estado,descripcion,cod_user,fecha, hora) values (?,?,?,?,?,?) ");
		pss.setString(1,codoc);
		pss.setString(2,est);
		pss.setString(3,descrip);
	    pss.setString(4,user);
	    pss.setDate(5,Fecha);
		pss.setTime(6,Hora);
		pss.executeUpdate();
		pss.close();
		con1.cerrar();
		
		PreparedStatement ps=null;
		Conexion con=new Conexion();
		ps=con.conn.prepareStatement("UPDATE ord_orden_compra SET estado='"+est+"' WHERE codigo='"+codoc+"'");
		ps.executeUpdate();
		ps.close();
		con.cerrar();
			
		}catch(Exception ex){
			System.out.println("Error en MetodoDescOC>>CambEstO "+ex);
		}	
	}

public java.sql.ResultSet BuscarCodProd(String cprod,Date Fecha,Time Hora,String user){
	 java.sql.ResultSet rs=null;
       Statement st = null;
      try{
       	Conexion con=new Conexion();
       	st = con.conn.createStatement();
       	rs=st.executeQuery("select * from ord_producto where estado=1 and cod_usuariofk='"+user+"' and tipo_productofk=3 and fecha_insercion='"+Fecha+"' and hora_insercion='"+Hora+"' and cod_cuentafk='"+cprod+"' ");
       	
       }catch(Exception ex){
           	System.out.println("Error en MetodoDescOC>>BuscarCodProd "+ex);
           }	
           return rs;
}
	
}
