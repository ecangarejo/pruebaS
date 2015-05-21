package ord_metodo;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;

import adm_logica.Conexion;

public class MetodoProductos {

	
	public java.sql.ResultSet BuscarGrupo(String nombreg){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT nombre, cod_cuentafk, cuenta FROM ord_grupo WHERE nombre='"+nombreg+"' ");
	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoProductos>>BuscarGrupo "+ex);
	            }	
	            return rs;
	}	

	
	
	public void GuardarGrupo(String nombreg, String estg, String user, Date Fecha, Time  Hora) throws SQLException{
		//System.out.print(cod_medico);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();													
			    ps=con.conn.prepareStatement("INSERT INTO ord_grupo(nombre,estado,fecha_insercion,hora_insercion,cod_usuario)values(?,?,?,?,?) ");
			    ps.setString(1,nombreg);
			    ps.setString(2,estg);
			    ps.setDate(3,Fecha);
			    ps.setTime(4,Hora);
			    ps.setString(5,user);
			    ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoProductos>>GuardarGrupo "+ex);
			}
			java.sql.ResultSet rs=null;
			Statement st = null;
	       try{
	        	Conexion con2=new Conexion();
	        	st = con2.conn.createStatement();
	        	rs=st.executeQuery("SELECT codigo FROM ord_grupo WHERE nombre='"+nombreg+"' ");
	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoProductos>>BuscarGrupo "+ex);
	            }	
	           
			String descripcion="";
			String codgrupo="";
			int  vali=0;
			if(rs.next()){
				codgrupo=rs.getString(1);
				descripcion="Datos de insercion nombre="+nombreg+", estado="+estg+", fecha="+Fecha+", hora="+Hora;
				vali=1;
			}
			if(vali!=0){
				//Guarda registros de auditoria de la insercion del grupo.
				try{	
						PreparedStatement ps = null;
					    Conexion con3=new Conexion();													
					    ps=con3.conn.prepareStatement("INSERT INTO ord_audgrupo(cod_grupofk,descripcion,fecha_insercion,hora_insercion,cod_usuario ) VALUES (?,?,?,?,?) ");
					    ps.setString(1,codgrupo);
					    ps.setString(2,descripcion);
					    ps.setDate(3,Fecha);
					    ps.setTime(4,Hora);
					    ps.setString(5,user);
					    ps.executeUpdate();
						ps.close();
						con3.cerrar();	
				}catch(Exception ex){
		        	System.out.println("ERROR EN MetodoProductos>>GuardarAudGrupo "+ex);
				}
			}
			
		}
	
	
	public java.sql.ResultSet MostrarGrupos(){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT nombre,cod_cuentafk, cuenta FROM ord_grupo ");
	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoProductos>>BuscarGrupo "+ex);
	            }	
	            return rs;
	}
	
	public java.sql.ResultSet MostrarTipos(){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT nombre FROM ord_tipoproducto ");
	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoProductos>>MostrarTipos "+ex);
	            }	
	            return rs;
	}
	
	public java.sql.ResultSet BuscarTp(String nameTP){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT * from ord_tipoproducto where nombre='"+nameTP+"' ");
	        	System.out.println("SELECT * from ord_tipoproducto where nombre='"+nameTP+"' ");
	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoProductos>>BuscarTp "+ex);
	            }	
	            return rs;
	}
	
	public void GuardarTipoP(String nametp, String user, Date Fecha, Time  Hora) throws SQLException{
		//System.out.print(cod_medico);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();													
			    ps=con.conn.prepareStatement("INSERT INTO ord_tipoproducto(nombre)values(?) ");
			    ps.setString(1,nametp);
			    ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoProductos>>GuardarTipoP	 "+ex);
			}
			java.sql.ResultSet rs=null;
			Statement st = null;
	       try{
	        	Conexion con2=new Conexion();
	        	st = con2.conn.createStatement();
	        	rs=st.executeQuery("SELECT codigo FROM ord_tipoproducto WHERE nombre='"+nametp+"' ");
	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoProductos>>BuscarTp "+ex);
	            }	
	           
			String descripcion="";
			String codtipop="";
			int  vali=0;
			if(rs.next()){
				codtipop=rs.getString(1);
				descripcion="Datos de insercion nombre="+nametp+", user="+user+", fecha="+Fecha+", hora="+Hora;
				vali=1;
			} 
			if(vali!=0){
				//Guarda registros de auditoria de la insercion de tipo.
				try{	
						PreparedStatement ps = null;
					    Conexion con3=new Conexion();													
					    ps=con3.conn.prepareStatement("INSERT INTO ord_aud_tipop(cod_tipo_producto,descripcion,user,fecha_insercion,hora_insercion) VALUES (?,?,?,?,?) ");
					    ps.setString(1,codtipop);
					    ps.setString(2,descripcion);
					    ps.setString(3,user);
					    ps.setDate(4,Fecha);
					    ps.setTime(5,Hora);
					    ps.executeUpdate();
						ps.close();
						con3.cerrar();	
				}catch(Exception ex){
		        	System.out.println("ERROR EN MetodoProductos>>GuardarAudTipop "+ex);
				}
			}
			
		}
	

	public java.sql.ResultSet BuscarProducto(String nombre){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT * from ord_producto where nombre='"+nombre+"' ");
	        	
	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoProductos>>BuscarProducto "+ex);
	            }	
	            return rs;
	}
	
	public java.sql.ResultSet BuscarProd(String nombre, String ident, String codprove){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	if(ident.equals("1")){
	        		rs=st.executeQuery("SELECT op.codigo, op.cod_referencia, op.nombre, ot.nombre  FROM ord_producto op, ord_tipoproducto ot where op.tipo_productofk=ot.codigo AND op.estado=0 AND ((op.nombre LIKE '%"+nombre+"%' ) OR (op.cod_referencia LIKE '%"+nombre+"%'))");
	        	}else{
	        		if(ident.equals("2")){
	        		rs=st.executeQuery("SELECT op.codigo, op.cod_referencia, op.nombre, ot.nombre  FROM ord_producto op, ord_tipoproducto ot where op.tipo_productofk=ot.codigo AND op.estado=0 AND ((op.nombre LIKE '%"+nombre+"%' ) OR (op.cod_referencia LIKE '%"+nombre+"%')) AND op.codigo IN (SELECT cod_productofk FROM ord_produc_grupo )");
	        		System.out.println("SELECT op.codigo, op.cod_referencia, op.nombre, ot.nombre  FROM ord_producto op, ord_tipoproducto ot where op.tipo_productofk=ot.codigo AND op.estado=0 AND ((op.nombre LIKE '%"+nombre+"%' ) OR (op.cod_referencia LIKE '%"+nombre+"%')) AND op.codigo IN (SELECT cod_productofk FROM ord_produc_grupo )");
	        		}else{
	        			if(ident.equals("3")){
	        				rs=st.executeQuery("SELECT op.codigo, op.cod_referencia, op.nombre, otp.nombre AS tipo, opp.codigo "+
	        								"FROM ord_Prove_Prod opp, ord_producto op, ord_tipoproducto otp "+
	        								"WHERE opp.cod_productofk=op.codigo AND otp.codigo=op.tipo_productofk "+
	        								"AND opp.cod_proveedorfk='"+codprove+"' AND op.estado=0 AND ((op.nombre LIKE '%"+nombre+"%' ) OR (op.cod_referencia LIKE '%"+nombre+"%')) ");				
	        			}else{
	        				if(ident.equals("4")){
		        				rs=st.executeQuery("SELECT op.codigo, op.cod_referencia, op.nombre, otp.nombre AS tipo, opp.codigo, ot.codigo AS codigotarifa, ot.tarifa, ot.cod_ivafk, oi.valor "+
		        								"FROM ord_Prove_Prod opp, ord_producto op, ord_tipoproducto otp, ord_tarifas ot, ord_iva oi "+
		        								"WHERE opp.cod_productofk=op.codigo AND otp.codigo=op.tipo_productofk  "+
		        								"AND ot.cod_productofk=op.codigo AND ot.cod_proveedorfk=opp.cod_proveedorfk AND ot.cod_productofk=opp.cod_productofk  AND oi.codigo=ot.cod_ivafk "+
		        								"AND opp.cod_proveedorfk='"+codprove+"' AND op.estado=0 AND ((op.nombre LIKE '%"+nombre+"%' ) OR (op.cod_referencia LIKE '%"+nombre+"%')) ");
		        				System.out.println("SELECT op.codigo, op.cod_referencia, op.nombre, otp.nombre AS tipo, opp.codigo, ot.codigo AS codigotarifa, ot.tarifa, ot.cod_ivafk, oi.valor "+
        								"FROM ord_Prove_Prod opp, ord_producto op, ord_tipoproducto otp, ord_tarifas ot, ord_iva oi "+
        								"WHERE opp.cod_productofk=op.codigo AND otp.codigo=op.tipo_productofk  "+
        								"AND ot.cod_productofk=op.codigo AND ot.cod_proveedorfk=opp.cod_proveedorfk AND ot.cod_productofk=opp.cod_productofk  AND oi.codigo=ot.cod_ivafk "+
        								"AND opp.cod_proveedorfk='"+codprove+"' AND op.estado=0 AND ((op.nombre LIKE '%"+nombre+"%' ) OR (op.cod_referencia LIKE '%"+nombre+"%')) ");
	        				}
	        			}
	        		}
	        	}
	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoProductos>>BuscarProd "+ex);
	            }	
	            return rs;
	}


	public java.sql.ResultSet BuscarProvee(String nombre){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT codigo,nit, razon_social from farc_proveedor where ((nit LIKE '%"+nombre+"%') OR (razon_social LIKE '%"+nombre+"%')) ");
	        	System.out.println("SELECT codigo,nit, razon_social from farc_proveedor where ((nit LIKE '%"+nombre+"%') OR (razon_social LIKE '%"+nombre+"%')) ");
	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoProductos>>BuscarProvee "+ex);
	            }	
	            return rs;
	}
	
	public java.sql.ResultSet BuscarProveedor(String codigo){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT codigo,nit, razon_social from farc_proveedor where codigo='"+codigo+"' ");
	        	
	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoProductos>>BuscarProvee "+ex);
	            }	
	            return rs;
	}
	
	public java.sql.ResultSet BuscarP(String codigo){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT op.codigo, op.cod_referencia, op.nombre, ot.nombre  FROM ord_producto op, ord_tipoproducto ot where op.tipo_productofk=ot.codigo and op.codigo='"+codigo+"'");
	        	System.out.println("SELECT op.codigo, op.cod_referencia, op.nombre, ot.nombre  FROM ord_producto op, ord_tipoproducto ot where op.tipo_productofk=ot.codigo and op.codigo='"+codigo+"'");
	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoProductos>>BuscarP "+ex);
	            }	
	            return rs;
	}
	
	public void GuardarProducto(String codref, String nameP,Date Fecha,Time Hora,String user,String tipop) throws SQLException{
		//System.out.print(cod_medico);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();													
			    ps=con.conn.prepareStatement("INSERT INTO ord_producto (cod_referencia,nombre,cod_usuariofk,tipo_productofk,fecha_insercion,hora_insercion)values(?,?,?,?,?,?) ");
			    ps.setString(1,codref);
			    ps.setString(2,nameP);
			    ps.setString(3,user);
			    ps.setString(4,tipop);
			    ps.setDate(5,Fecha);
			    ps.setTime(6,Hora);
			    ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoProductos>>GuardarProducto	 "+ex);
			}
			
			
			
		}
	
	public java.sql.ResultSet BuscarGrupo(){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT codigo, nombre, cod_cuentafk, cuenta FROM ord_grupo WHERE cod_cuentafk!=0 ");
	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoProductos>>BuscarGrupo "+ex);
	            }	
	            return rs;
	}
	
	public java.sql.ResultSet BuscarProductoenGrupo(String codprod){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT * FROM ord_produc_grupo WHERE cod_productofk='"+codprod+"' ");
	        	
	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoProductos>>BuscarProductoenGrupo "+ex);
	            }	
	            return rs;
	}
	
	public java.sql.ResultSet BuscarproductoenProve(String codprod, String codprove){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT * FROM ord_prove_prod WHERE cod_productofk='"+codprod+"' and cod_proveedorfk='"+codprove+"' ");
	        	
	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoProductos>>BuscarproductoenProve "+ex);
	            }	
	            return rs;
	}
	
	public void GuardarAsigGrupo(String codprod,String codgrupo,String user,Date Fecha,Time Hora) throws SQLException{
		//System.out.print(cod_medico);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();													
			    ps=con.conn.prepareStatement("INSERT INTO ord_produc_grupo (cod_productofk,cod_grupofk,cod_usuariofk,fecha,hora)values(?,?,?,?,?) ");
			    ps.setString(1,codprod);
			    ps.setString(2,codgrupo);
			    ps.setString(3,user);
			    ps.setDate(4,Fecha);
			    ps.setTime(5,Hora);
			    ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoProductos>>GuardarAsigGrupo	 "+ex);
			}
		}
	
	public void GuardarAsigProve(String codprod,String codprove,String user,Date Fecha,Time Hora) throws SQLException{
		//System.out.print(cod_medico);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();													
			    ps=con.conn.prepareStatement("INSERT INTO ord_prove_prod (cod_productofk,cod_proveedorfk,user,fecha,hora)values(?,?,?,?,?) ");
			    ps.setString(1,codprod);
			    ps.setString(2,codprove);
			    ps.setString(3,user);
			    ps.setDate(4,Fecha);
			    ps.setTime(5,Hora);
			    ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoProductos>>GuardarAsigProve	 "+ex);
			}
			
			
			
		}
	
	
	public java.sql.ResultSet BuscarProveedores(){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT codigo, nit, razon_social FROM farc_proveedor order by razon_social  ");
	        	
	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoProductos>>BuscarProveedores "+ex);
	            }	
	            return rs;
	}
	
	
	public java.sql.ResultSet BuscarListaProductos(String cprove){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery(" SELECT opp.cod_proveedorfk, opp.cod_productofk, op.cod_referencia, op.nombre AS nombreproducto, otp.nombre AS tipoporducto , og.nombre AS grupo, og.cuenta "+
	        						"FROM ord_prove_prod opp, ord_producto op, farc_proveedor fp , ord_tipoproducto otp, ord_produc_grupo opg, ord_grupo og "+
	        						"WHERE opp.cod_productofk=op.codigo AND opp.cod_proveedorfk=fp.codigo AND otp.codigo=op.tipo_productofk AND opg.cod_productofk=op.codigo AND og.codigo=opg.cod_grupofk and opp.cod_proveedorfk='"+cprove+"' ");
	        	
	        	System.out.println(" SELECT opp.cod_proveedorfk, opp.cod_productofk, op.cod_referencia, op.nombre AS nombreproducto, otp.nombre AS tipoporducto , og.nombre AS grupo, og.cuenta "+
						"FROM ord_prove_prod opp, ord_producto op, farc_proveedor fp , ord_tipoproducto otp, ord_produc_grupo opg, ord_grupo og "+
						"WHERE opp.cod_productofk=op.codigo AND opp.cod_proveedorfk=fp.codigo AND otp.codigo=op.tipo_productofk AND opg.cod_productofk=op.codigo AND og.codigo=opg.cod_grupofk and opp.cod_proveedorfk='"+cprove+"' ");
	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoProductos>>BuscarListaProductos "+ex);
	            }	
	            return rs;
	}
	
	
	public java.sql.ResultSet BuscarTarifas(String cprodu, String cprove){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT ot.*, oi.valor from ord_tarifas ot, ord_iva oi where ot.cod_ivafk=oi.codigo AND ot.cod_productofk='"+cprodu+"' and ot.cod_proveedorfk='"+cprove+"' ");
	        	
	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoProductos>>BuscarTarifas "+ex);
	            }	
	            return rs;
	}
	
	
	public void GuardarTarifas(String cprove,String cprodu,String user,Date Fecha,Time Hora,String tarifa,String civa) throws SQLException{
		//System.out.print(cod_medico);
		try{
			System.out.println("tarifas "+tarifa);
				PreparedStatement ps = null;
			    Conexion con=new Conexion();													
			    ps=con.conn.prepareStatement("INSERT INTO ord_tarifas (cod_productofk,cod_proveedorfk,tarifa,cod_usuariofk,fecha,hora,cod_ivafk)values(?,?,?,?,?,?,?) ");
			    ps.setString(1,cprodu);
			    ps.setString(2,cprove);
			    ps.setString(3,tarifa);
			    ps.setString(4,user);
			    ps.setDate(5,Fecha);
			    ps.setTime(6,Hora);
			    ps.setString(7,civa);
			    ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoProductos>>GuardarTarifas "+ex);
			}
			
			
			
		}
	
	
	public void BorrarTarifa(String codtarifa,String cprove,String cprodu,Date Fecha,Time Hora, String user) throws SQLException{
		
		java.sql.ResultSet rs=null;
		Statement st = null;
       try{
        	Conexion con2=new Conexion();
        	st = con2.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM ord_tarifas WHERE codigo='"+codtarifa+"' ");
        	
        }catch(Exception ex){
            	System.out.println("Error en MetodoProductos>>BuscarTarifaseguncodigo "+ex);
            }	
		String cadena=" Datos Borrados [ ";
        while(rs.next()){
        	cadena=cadena+" codigo="+rs.getString(1)+" cod_productofk="+rs.getString(2)+" cod_proveedorfk="+rs.getString(3)+" tarifa="+rs.getString(4)+" cod_usuariofk="+rs.getString(5)+" fecha="+rs.getString(6)+" hora="+rs.getString(7);
        }
        cadena=cadena+" ]";
        rs.getStatement().getConnection().close();
       
        try{
			PreparedStatement ps = null;
		    Conexion con=new Conexion();													
		    ps=con.conn.prepareStatement("INSERT INTO ord_audtarifa(descripcion,cod_tarifafk,cod_userfk,fecha_insercion,hora_insercion)values(?,?,?,?,?) ");
		    ps.setString(1,cadena);
		    ps.setString(2,codtarifa);
		    ps.setString(3,user);
		    ps.setDate(4,Fecha);
		    ps.setTime(5,Hora);
		    ps.executeUpdate();
			ps.close();
			con.cerrar();				
		}catch(Exception ex){
        	System.out.println("ERROR EN MetodoProductos>>GuardarRegistroAuditaTarifa "+ex);
		}
        
        
        PreparedStatement stt = null;
        try{
        	Conexion con3=new Conexion();
        	stt= con3.conn.prepareStatement("DELETE from ord_tarifas where codigo='"+codtarifa+"'");
        	stt.executeUpdate();
        	stt.close();
        	con3.cerrar();
        	
        }
        catch(Exception ex){
        	ex.getMessage();
        
        }	
               
				
	}
	
	
	public java.sql.ResultSet BuscarProveparaOrdenCompra(String texto){
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
	            	System.out.println("Error en MetodoProductos>>BuscarProveparaOrdenCompra "+ex);
	            }	
	            return rs;
	}
	
	public java.sql.ResultSet BuscarProveOrdenCompra(String codigo){
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
	            	System.out.println("Error en MetodoProductos>>BuscarProveOrdenCompra "+ex);
	            }	
	            return rs;
	}
	
	
	public java.sql.ResultSet BuscarFormaPago(){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT * FROM ord_formadepago ");
	        	
	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoProductos>>BuscarFormaPago "+ex);
	            }	
	            return rs;
	}
	
	
	public java.sql.ResultSet BuscarTarifa(String codtarifa){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT op.codigo, op.cod_referencia, op.nombre, otp.nombre AS tipo, opp.codigo, ot.codigo AS codigotarifa, ot.tarifa, ot.cod_ivafk, oi.valor "+
	        					"FROM ord_Prove_Prod opp, ord_producto op, ord_tipoproducto otp, ord_tarifas ot, ord_iva oi "+
	        					"WHERE opp.cod_productofk=op.codigo AND otp.codigo=op.tipo_productofk "+
	        					"AND ot.cod_productofk=op.codigo AND ot.cod_proveedorfk=opp.cod_proveedorfk AND ot.cod_productofk=opp.cod_productofk  AND oi.codigo=ot.cod_ivafk AND op.estado=0 "+
	        					"AND ot.codigo='"+codtarifa+"'");
	        	System.out.println("BUSCAR TARIFA SELECT op.codigo, op.cod_referencia, op.nombre, otp.nombre AS tipo, opp.codigo, ot.codigo AS codigotarifa, ot.tarifa, ot.cod_ivafk, oi.valor "+
    					"FROM ord_Prove_Prod opp, ord_producto op, ord_tipoproducto otp, ord_tarifas ot, ord_iva oi "+
    					"WHERE opp.cod_productofk=op.codigo AND otp.codigo=op.tipo_productofk "+
    					"AND ot.cod_productofk=op.codigo AND ot.cod_proveedorfk=opp.cod_proveedorfk AND ot.cod_productofk=opp.cod_productofk  AND oi.codigo=ot.cod_ivafk AND op.estado=0 "+
    					"AND ot.codigo='"+codtarifa+"'");
	        	
	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoProductos>>BuscarTarifa"+ex);
	            }	
	            return rs;
	}
	
	public java.sql.ResultSet BuscarIva(String texto ){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT * FROM ord_iva where valor like '%"+texto+"%' ");
	        	
	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoProductos>>BuscarIva "+ex);
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
	            	System.out.println("Error en MetodoProductos>>BuscarSucursal "+ex);
	            }	
	            return rs;
	}
	
	
	public java.sql.ResultSet BuscarCentroCosto(String suc){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT fc.codigo, fc.NombreCentroCosto, fc.Abreviado FROM cont_sucursales cs, cont_surcursal_centrocosto csc, fact_centrocosto fc "+
	        						" WHERE cs.codigo=csc.cod_sucursal_fk AND csc.cod_centro_costo_fk=fc.codigo and cs.codigo='"+suc+"' order by fc.NombreCentroCosto asc ");
	        	
	        	 
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoProductos>>BuscarCentroCosto "+ex);
	            }	
	            return rs;
	}
	
	public java.sql.ResultSet Buscarsubcentro(String ccosto,String suc){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT  "+
	        				"fsc.cod_subcentro_costo, "+
	        				"fsc.descripcion "+
	        				"FROM "+
	        				"fact_subcentros_costo fsc, "+
	        				"cont_centro_subcentro ccs, "+
	        				"cont_surcursal_centrocosto csc "+
	        				"WHERE fsc.cod_subcentro_costo = ccs.cod_sub_centro_costo_fk  "+
	        				"AND ccs.cod_centro_costo_fk =csc.codigo AND csc.cod_centro_costo_fk='"+ccosto+"' AND csc.cod_sucursal_fk='"+suc+"' ");
	        	
	        	 
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoProductos>>Buscarsubcentro "+ex);
	            }	
	            return rs;
	}
	
	public java.sql.ResultSet Buscarccysc(String ccosto, String subcc,String suc){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT "+
	        					"ccs.codigo, "+
	        					"csc.codigo "+
	        					"FROM "+
	        					"cont_centro_subcentro ccs, "+
	        					"cont_surcursal_centrocosto csc "+
	        					"WHERE ccs.cod_centro_costo_fk=csc.codigo "+
	        					"AND csc.cod_sucursal_fk = '"+suc+"'  "+
	        					"AND csc.cod_centro_costo_fk = '"+ccosto+"'  "+
	        					"AND ccs.cod_sub_centro_costo_fk = '"+subcc+"' ");
	        	
	        	 
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoProductos>>Buscarccysc "+ex);
	            }	
	            return rs;
	}
	
	
	public java.sql.ResultSet Biva(String texto){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT * FROM ord_iva where valor like '%"+texto+"%' ");
	        	System.out.println("SELECT * FROM ord_iva where valor like '%"+texto+"%' ");
	        	 
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoProductos>>Biva"+ex);
	            }	
	            return rs;
	}
	
	
	public void GuardarPreOrden(String ocodprove, String ofechaentrega,String osuc,String occosto,String osubcc,String ocodsuccosto,String ocodcensubcc,String oconcep,String oformapago,String  ouser) throws SQLException{
		//System.out.print(cod_medico);
		try{
			System.out.println("fecha de entrega "+ofechaentrega);
				PreparedStatement ps = null;
			    Conexion con=new Conexion();													
			    ps=con.conn.prepareStatement("INSERT INTO ord_orden_compra (fecha_entrega,concepto,cod_sucursalfk,cod_centrocostofk,cod_subcentrocostofk,cod_sucursal_costo_fk,cod_centro_subcentro_fk,cod_usufk,forma_pago,cod_proveedorfk)values(?,?,?,?,?,?,?,?,?,?) ");
			    ps.setString(1,ofechaentrega);
			    ps.setString(2,oconcep);
			    if(osuc.equals("")){
			    	 ps.setString(3,"0");
			    }else{
			    	 ps.setString(3,osuc);
			    }
			    if(occosto.equals("")){
			    	ps.setString(4,"0");
			    }else{
			    	ps.setString(4,occosto);
			    }
			    
			    if(osubcc.equals("")){
			    	ps.setString(5,"0");
			    }else{
			    	ps.setString(5,osubcc);
			    }
			    
			    if(ocodsuccosto.equals("")){
			    	ps.setString(6,"0");
			    }else{
			    	ps.setString(6,ocodsuccosto);
			    }
			    
			    if(ocodcensubcc.equals("")){
			    	ps.setString(7,"0");
			    }else{
			    	ps.setString(7,ocodcensubcc);
			    }
			    
			    ps.setString(8,ouser);
			    ps.setString(9,oformapago);
			    ps.setString(10,ocodprove);
			    ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoProductos>>GuardarPreOrden"+ex);
			}
			
	}
	
	public java.sql.ResultSet BuscarCodPreOrden(String ocodprove,String  ofechaentrega,String osuc,String occosto,String osubcc,String ocodsuccosto,String ocodcensubcc,String oconcep,String oformapago,String ouser){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	if((osuc.equals(""))||(occosto.equals(""))||(osubcc.equals(""))||(ocodsuccosto.equals(""))||(ocodcensubcc.equals(""))){
	        		
	        		rs=st.executeQuery("SELECT codigo FROM ord_orden_compra where concepto='"+oconcep+"' and cod_sucursalfk=0  and cod_centrocostofk=0 and cod_subcentrocostofk=0 and cod_sucursal_costo_fk=0 and cod_centro_subcentro_fk=0 and cod_usufk='"+ouser+"' and forma_pago='"+oformapago+"' and cod_proveedorfk='"+ocodprove+"' ");
	        		System.out.println("BUSCARCODPREORDEN SELECT codigo FROM ord_orden_compra where concepto='"+oconcep+"' and cod_sucursalfk=0  and cod_centrocostofk=0 and cod_subcentrocostofk=0 and cod_sucursal_costo_fk=0 and cod_centro_subcentro_fk=0 and cod_usufk='"+ouser+"' and forma_pago='"+oformapago+"' and cod_proveedorfk='"+ocodprove+"' ");
	        	}else{
	        		rs=st.executeQuery("SELECT codigo FROM ord_orden_compra where concepto='"+oconcep+"' and cod_sucursalfk='"+osuc+"'  and cod_centrocostofk='"+occosto+"' and cod_subcentrocostofk='"+osubcc+"' and cod_sucursal_costo_fk='"+ocodsuccosto+"' and cod_centro_subcentro_fk='"+ocodcensubcc+"' and cod_usufk='"+ouser+"' and forma_pago='"+oformapago+"' and cod_proveedorfk='"+ocodprove+"' ");
	        			System.out.println("SELECT codigo FROM ord_orden_compra where concepto='"+oconcep+"' and cod_sucursalfk='"+osuc+"'  and cod_centrocostofk='"+occosto+"' and cod_subcentrocostofk='"+osubcc+"' and cod_sucursal_costo_fk='"+ocodsuccosto+"' and cod_centro_subcentro_fk='"+ocodcensubcc+"' and cod_usufk='"+ouser+"' and forma_pago='"+oformapago+"' and cod_proveedorfk='"+ocodprove+"' ");
	        	}
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoProductos>>BuscarCodPreOrden"+ex);
	            }	
	            return rs;
	}
	
	public void GuardarDetorden(String codordencompra,String ocodprove,String ocodprodu,String ocodref,String onombre,String  ocant,String  opunit,String  osubtotal,String  oiva,String  optotal,String  ocodiiva,String ocodtarifa ,String  odesc,Date Fecha,Time  Hora,String ouser) throws SQLException{
		//System.out.print(cod_medico);
		try{
			System.out.println("codordencompra "+codordencompra);
				PreparedStatement ps = null;
			    Conexion con=new Conexion();													
			    ps=con.conn.prepareStatement("INSERT INTO ord_detalle_ordencompra (cod_ordencomprafk, cod_proveedorfk,cod_productofk,cod_referencia, nombre, cant, precio_unitario, descuento, subtotal,valor_iva,valor_total,cod_usufk,fecha,hora,cod_ivafk)" +
			    							"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
			    ps.setString(1,codordencompra);
			    ps.setString(2,ocodprove);
			    ps.setString(3,ocodprodu);
			    ps.setString(4,ocodref);
			    ps.setString(5,onombre);
			    ps.setString(6,ocant);
			    ps.setString(7,opunit);
			    ps.setString(8,odesc);
			    ps.setString(9,osubtotal);
			    ps.setString(10,oiva);
			    ps.setString(11,optotal);
			    ps.setString(12,ouser);
			    ps.setDate(13,Fecha);
			    ps.setTime(14,Hora);
			    ps.setString(15,ocodiiva);
			    ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoProductos>>GuardarDetorden"+ex);
			}
			
	}
	
	public java.sql.ResultSet BuscarDetOrden(String codordencompra){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select * from ord_detalle_ordencompra where cod_ordencomprafk='"+codordencompra+"' and estado!='3' ");
	        	System.out.println("select * from ord_detalle_ordencompra where cod_ordencomprafk='"+codordencompra+"' and estado!='3' ");
	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoProductos>>BuscarDetOrden"+ex);
	            }	
	            return rs;
	}
	
	public void ActuaEncOrden(String codordencompra,String ofechaentrega,String osuc,String occosto,String osubcc,String ocodsuccosto,String ocodcensubcc,String oconcep,String oformapago, String ocodprove){
		/**
			 * Actualiza el encabezado de la orden de compra ya existente. 
		 */
			try{
				PreparedStatement st = null;
				    Conexion con=new Conexion();                                                      
				    st= con.conn.prepareStatement("UPDATE ord_orden_compra SET fecha_entrega='"+ofechaentrega+"' , concepto='"+oconcep+"' , cod_sucursalfk='"+osuc+"', cod_centrocostofk='"+occosto+"', cod_subcentrocostofk='"+osubcc+"' , cod_sucursal_costo_fk='"+ocodsuccosto+"', cod_centro_subcentro_fk='"+ocodcensubcc+"', forma_pago='"+oformapago+"', cod_proveedorfk='"+ocodprove+"' WHERE codigo='"+codordencompra+"' ");
			    	st.executeUpdate();
			    	st.close();
					con.cerrar();				
				}catch(Exception ex){
		        	System.out.println("ERROR EN MetodoProductos>>ActuaEncOrden "+ex);
				}

			}
	
	public java.sql.ResultSet BuscarPreOrdenes(String codprove, String user){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select o.codigo, odo.fecha,concepto from ord_orden_compra o, ord_detalle_ordencompra odo where odo.cod_ordencomprafk=o.codigo and o.estado=0 and o.cod_proveedorfk='"+codprove+"' and o.cod_usufk='"+user+"' group by o.codigo order by odo.fecha desc ");
	        	System.out.println("select o.codigo, odo.fecha,concepto from ord_orden_compra o, ord_detalle_ordencompra odo where odo.cod_ordencomprafk=o.codigo and o.estado=0 and o.cod_proveedorfk='"+codprove+"' and o.cod_usufk='"+user+"' group by o.codigo order by odo.fecha desc ");
	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoProductos>>BuscarPreOrdenes"+ex);
	            }	
	            return rs;
	}
	
	public java.sql.ResultSet BusOrdExist(String codprove){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT * FROM ord_orden_compra o, ord_detalle_ordencompra od WHERE o.cod_proveedorfk='"+codprove+"' AND o.estado=0 AND od.cod_ordencomprafk=o.codigo ");
	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoProductos>>BusOrdExist "+ex);
	            }	
	            return rs;
	}
	
	public java.sql.ResultSet BusDatOrden(String codpreorden){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT o.*, of.descripcion FROM ord_orden_compra o, ord_formadepago of WHERE o.codigo="+codpreorden+" and of.codigo=o.forma_pago");
	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoProductos>>BusDatOrden "+ex);
	            }	
	            return rs;
	}
	
	public java.sql.ResultSet BusSuc(String suc){//Busca nombre de sucursal 
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT * from cont_sucursales where codigo='"+suc+"'");
	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoProductos>>BusSuc(String suc) "+ex);
	            }	
	            return rs;
	}
	
	public java.sql.ResultSet Buscc(String cc){//Busca nombre de centro de costo
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT * from fact_centrocosto where codigo='"+cc+"'");
	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoProductos>>Buscc "+ex);
	            }	
	            return rs;
	}
	
	public java.sql.ResultSet Bussubc(String subc){//Busca nombre del subcentro de costo
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT * from fact_subcentros_costo  where cod_subcentro_costo='"+subc+"'");
	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoProductos>>Bussubc "+ex);
	            }	
	            return rs;
	}
	
	
	public void EliminarDetOrden(String codord,String  user,Date Fecha,Time Hora) throws SQLException{
		//System.out.print(cod_medico);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();													
			    ps=con.conn.prepareStatement("INSERT INTO ord_auditaorden (cod_detalleordcomp_fk,estado,descrip,user,fecha, hora)" +
			    							"values(?,?,?,?,?,?) ");
			    ps.setString(1,codord);
			    ps.setString(2,"3");
			    ps.setString(3,"CAMBIO DE ESTADO A ELIMINADO DETALLE COD. "+codord);
			    ps.setString(4,user);
			    ps.setDate(5,Fecha);
			    ps.setTime(6,Hora);
			    ps.executeUpdate();
				ps.close();
				con.cerrar();
				
				PreparedStatement st = null;
			    Conexion con2=new Conexion();                                                      
			    st= con2.conn.prepareStatement("UPDATE ord_detalle_ordencompra set estado=3 where codigo='"+codord+"'  ");
		    	st.executeUpdate();
		    	st.close();
				con2.cerrar();	
				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoProductos>>EliminarDetOrden"+ex);
			}
			
	}
	
	public java.sql.ResultSet BuscCodOrden(String coddetord){//Busca el codigo de la orden basando en el codigo de detalle y los demas datos correspondientes al detalle
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT o.cod_ordencomprafk,  o.*  FROM ord_detalle_ordencompra o WHERE o.codigo='"+coddetord+"' ");
	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoProductos>>BuscCodOrden "+ex);
	            }	
	            return rs;
	}
	
	
	public java.sql.ResultSet BusCodTarifa(String punit, String codprove, String codprod){//Busca el codigo de la tarifa
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT * from ord_tarifas where tarifa='"+punit+"' and cod_proveedorfk='"+codprove+"' and cod_productofk='"+codprod+"' ");
	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoProductos>>BusCodTarifa "+ex);
	            }	
	            return rs;
	}
	
	
	public void ActDetOrden(String coddetord,String codorden,String  ocodref,String onombre,String ocodprodu,String ocant,String opunit,String osubtotal,String oiva,String optotal,String ocodiiva,String ocodtarifa,String odesc,String user,Date Fecha,Time Hora) throws SQLException{
		
		 java.sql.ResultSet rs=null;
	        Statement st = null;
		try{
			
				Conexion con2=new Conexion();
				st=con2.conn.createStatement();
				String Obs="";
				String estado="";
				rs=st.executeQuery("select * from ord_detalle_ordencompra where codigo='"+coddetord+"' and estado!='3'  ");
				System.out.println("select * from ord_detalle_ordencompra where codigo='"+coddetord+"' and estado!='3'  ");
				if(rs.next()){
					Obs=" CAMBIO DE DETALLE NO. "+coddetord+" ANTES [cod_ordencompra="+rs.getString(2)+" , proveedor='"+rs.getString(3)+"' , cod_producto='"+rs.getString(4)+"' , cod_ref='"+rs.getString(5)+"' , nombre='"+rs.getString(6)+"' , cant='"+rs.getString(7)+"' , precio_unitario='"+rs.getString(8)+"' , descuento='"+rs.getString(9)+"' , subtotal='"+rs.getString(10)+"' , valor_iva='"+rs.getString(11)+"' , valor_total='"+rs.getString(12)+"' , estado='"+rs.getString(13)+"' , cod_usufk='"+rs.getString(14)+"' , fecha='"+rs.getString(15)+"' , hora='"+rs.getString(16)+"' ] AHORA [ cod_ordencompra="+codorden+" , proveedor='"+rs.getString(3)+"' , cod_producto='"+ocodprodu+"' , cod_ref='"+ocodref+"' , nombre='"+onombre+"' , cant='"+ocant+"' , precio_unitario='"+opunit+"' , descuento='"+odesc+"' , subtotal='"+osubtotal+"' , valor_iva='"+oiva+"' , valor_total='"+optotal+"' , estado=2 , cod_usufk='"+user+"' , fecha='"+Fecha+"' , hora='"+Hora+"'  ]";
					estado=rs.getString(13);
				}rs.getStatement().getConnection().close();
				System.out.println("Valor de estado "+estado);
				System.out.println("Valor de Obs = "+Obs);
				PreparedStatement ps = null;
			    Conexion con=new Conexion();													
			    ps=con.conn.prepareStatement("INSERT INTO ord_auditaorden (cod_detalleordcomp_fk,estado,descrip,user,fecha, hora)" +
			    							"values(?,?,?,?,?,?) ");
			    ps.setString(1,coddetord);
			    ps.setString(2,"2");
			    ps.setString(3,Obs);
			    ps.setString(4,user);
			    ps.setDate(5,Fecha);
			    ps.setTime(6,Hora);
			    ps.executeUpdate();
				ps.close();
				con.cerrar();
				
				PreparedStatement stt = null;
			    Conexion con3=new Conexion();                                                      
			    stt= con3.conn.prepareStatement("UPDATE ord_detalle_ordencompra set cod_ordencomprafk='"+codorden+"' , cod_productofk='"+ocodprodu+"' , cod_referencia='"+ocodref+"' , nombre='"+onombre+"' , cant='"+ocant+"' , precio_unitario='"+opunit+"' , descuento='"+odesc+"' , subtotal='"+osubtotal+"' , valor_iva='"+oiva+"' , valor_total='"+optotal+"' , estado='"+estado+"' , cod_usufk='"+user+"' , fecha='"+Fecha+"' , Hora='"+Hora+"' where codigo='"+coddetord+"'  ");
		    	stt.executeUpdate();
		    	stt.close();
				con3.cerrar();	
				 
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoProductos>>ActDetOrden"+ex);
			}
			
	}
	
	
	public void GuardarOrden(String codord,Date Fecha,Time Hora,String oconcep,String user) throws SQLException{
		 java.sql.ResultSet rs=null;
		 java.sql.ResultSet rss=null;
		 Statement st = null;
		 Statement st1 = null;
		try{
			
			PreparedStatement ps = null;
		    Conexion con=new Conexion();													
		    ps=con.conn.prepareStatement("INSERT INTO ord_movestados(cod_ordenfk,estado,descripcion,cod_user,fecha, hora)" +
		    							"values(?,?,?,?,?,?) ");
		    ps.setString(1,codord);
		    ps.setString(2,"1");
		    ps.setString(3,"EMISION DE LA ORDEN COMPRA POR CONCEPTO='"+oconcep+"'");
		    ps.setString(4,user);
		    ps.setDate(5,Fecha);
		    ps.setTime(6,Hora);
		    ps.executeUpdate();
			ps.close();
			con.cerrar();
			
			PreparedStatement sta = null;
		    Conexion con2=new Conexion();                                                      
		    sta= con2.conn.prepareStatement("UPDATE ord_detalle_ordencompra SET estado=1 where cod_ordencomprafk='"+codord+"' and (estado=0 OR estado=2) ");
	    	sta.executeUpdate();
	    	sta.close();
			con2.cerrar();
			
			
			Conexion con4=new Conexion();
        	st = con4.conn.createStatement();
        	rs=st.executeQuery("SELECT * from ord_consecutivos where codigo=1");
        	String sigla="";
        	int consec=0;
        	if(rs.next()){
        		sigla=rs.getString(4);
        		consec=((rs.getInt(2))+1);
        	}
        	
        	PreparedStatement pss=null;
        	Conexion con5=new Conexion();
        	pss=con5.conn.prepareStatement("UPDATE ord_consecutivos SET numero='"+consec+"' where  codigo=1 ");
        	pss.executeUpdate();
 			pss.close();
 			con5.cerrar();
 			
 			
 			Conexion con6=new Conexion();
        	st1 = con6.conn.createStatement();
        	rss=st.executeQuery("SELECT SUM(od.valor_total) FROM  ord_detalle_ordencompra od  where od.estado=1 and od.cod_ordencomprafk='"+codord+"' ");
        	String sum="0";
        	if(rss.next()){
        		sum=rss.getString(1);
        		
        	}rss.getStatement().getConnection().close();
        	
 			
			PreparedStatement stt = null;
		    Conexion con3=new Conexion();                                                      
		    stt= con3.conn.prepareStatement("UPDATE ord_orden_compra SET estado=1, fecha='"+Fecha+"', hora='"+Hora+"', consec='"+sigla+""+consec+"', concepto='"+oconcep+"', cod_usufk='"+user+"', valor_total='"+sum+"'  where codigo='"+codord+"' ");
	    	stt.executeUpdate();
	    	stt.close();
			con3.cerrar();
				
				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoProductos>>GuardarOrden"+ex);
			}
			
	}
	
	
}
