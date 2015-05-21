package ord_metodo;

import adm_logica.Conexion;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;

public class MetodoAprobar {
	
	public java.sql.ResultSet BusOrdxAprob(){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT o.consec, o.fecha,o.fecha_entrega,o.cod_usufk,o.concepto,p.razon_social,SUM(od.valor_total) AS total, o.codigo FROM ord_orden_compra o, farc_proveedor p, ord_detalle_ordencompra od "+
	        						"WHERE o.codigo=od.cod_ordencomprafk AND p.codigo=o.cod_proveedorfk AND (od.estado=1 OR od.estado=6) AND o.estado=1 GROUP BY o.consec ");
	        	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoAprobar>>BusOrdxAprob"+ex);
	            }	
	            return rs;
	}
	
	public java.sql.ResultSet BuscarUser(String user){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT CONCAT(sd.nombre,' ',sd.apellido) AS  nombre, u.*, sd.* FROM seg_usuario u, seg_datos_personales sd "+
	        					  "WHERE u.dat_codigo_fk=sd.dat_codigo AND u.usu_codigo="+user+"  ");
	        	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoAprobar>>BuscarUser"+ex);
	            }	
	            return rs;
	}
	
	public java.sql.ResultSet BuscarDetalleOc(String codoc){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT o.cod_centrocostofk, o.cod_sucursalfk, o.cod_subcentrocostofk, o.concepto, o.fecha_entrega, o.forma_pago, p.razon_social,p.telefono, od.cod_referencia,  "+
	        						"od.nombre, od.cant, od.descuento, od.precio_unitario, od.valor_iva, od.subtotal,od.valor_total, o.consec, of.descripcion, o.valor_total, od.codigo, p.codigo "+
	        						"FROM ord_orden_compra o, ord_detalle_ordencompra od, farc_proveedor p , ord_formadepago of "+
	        						"WHERE o.codigo=od.cod_ordencomprafk AND p.codigo=o.cod_proveedorfk  AND (od.estado=1 OR od.estado=6) AND  "+
	        						"o.estado=1 AND o.codigo AND of.codigo=o.forma_pago AND o.codigo="+codoc+" ");
	        	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoAprobar>>BuscarDetalleOc"+ex);
	            }	
	            return rs;
	}
	
	public java.sql.ResultSet BusSuc(String codsuc){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT * FROM cont_sucursales where codigo='"+codsuc+"' ");
	        	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoAprobar>>BusSuc"+ex);
	            }	
	            return rs;
	}
	
	public java.sql.ResultSet BusCentro(String codcen){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT * FROM fact_centrocosto where codigo='"+codcen+"' ");
	        	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoAprobar>>BusCentro"+ex);
	            }	
	            return rs;
	}

	
	public java.sql.ResultSet BusSubcen(String codscen){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT * FROM fact_subcentros_costo where cod_subcentro_costo='"+codscen+"' ");
	        	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoAprobar>>BusSubcen"+ex);
	            }	
	            return rs;
	}
	
	public java.sql.ResultSet BusOC(String codoc){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT o.*, p.razon_social FROM ord_orden_compra o, farc_proveedor p where o.cod_proveedorfk=p.codigo AND  o.codigo="+codoc+" ");
	        	System.out.println("SELECT o.*, p.razon_social FROM ord_orden_compra o, farc_proveedor p where o.cod_proveedorfk=p.codigo AND  o.codigo="+codoc+" ");        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoAprobar>>BusOC"+ex);
	            }	
	            return rs;
	}
	
	public void AprobOrden(String codoc,Date Fecha,Time Hora,String user,String concepto) throws SQLException{
		try{
			PreparedStatement ps = null;
		    Conexion con=new Conexion();													
		    ps=con.conn.prepareStatement("INSERT INTO ord_movestados(cod_ordenfk,estado,descripcion,cod_user,fecha,hora)" +
		    							"values(?,?,?,?,?,?) ");
		    ps.setString(1,codoc);
		    ps.setString(2,"2");
		    ps.setString(3,"OC APROBADA ("+concepto+")");
		    ps.setString(4,user);
		    ps.setDate(5,Fecha);
		    ps.setTime(6,Hora);
		    ps.executeUpdate();
			ps.close();
			con.cerrar();
			
			PreparedStatement st = null;
		    Conexion con2=new Conexion();                    
		    st= con2.conn.prepareStatement("UPDATE ord_orden_compra set estado=2 where codigo='"+codoc+"'  ");
	    	st.executeUpdate();
	    	st.close();
			con2.cerrar();	
			
		}catch(Exception ex){
        	System.out.println("ERROR EN MetodoAprobar>>AprobOrden"+ex);
		}
	
	}
	
	public void RechOrden(String codoc,Date Fecha,Time Hora,String user,String concepto) throws SQLException{
		try{
			PreparedStatement ps = null;
		    Conexion con=new Conexion();													
		    ps=con.conn.prepareStatement("INSERT INTO ord_movestados(cod_ordenfk,estado,descripcion,cod_user,fecha,hora)" +
		    							"values(?,?,?,?,?,?) ");
		    ps.setString(1,codoc);
		    ps.setString(2,"3");
		    ps.setString(3,"OC RECHAZADA ("+concepto+")");
		    ps.setString(4,user);
		    ps.setDate(5,Fecha);
		    ps.setTime(6,Hora);
		    ps.executeUpdate();
			ps.close();
			con.cerrar();
			
			PreparedStatement st = null;
		    Conexion con2=new Conexion();                                                      
		    st= con2.conn.prepareStatement("UPDATE ord_orden_compra set estado=3 where codigo='"+codoc+"'  ");
	    	st.executeUpdate();
	    	st.close();
			con2.cerrar();	
			
		}catch(Exception ex){
        	System.out.println("ERROR EN MetodoAprobar>>RechOrden"+ex);
		}
	
	}
		
	public void AdicionarProducto(String codoc,String user,String codprove,String codprod,String codref,String nombre,String cant,String punit,String subtotal,String iva,String ptotal,String codiiva,String codtarifa,String desc,Date Fecha,Time Hora) throws SQLException{
		try{
			//String descrip="[cod_ordencompra="+codoc+", proveedor="+codprove+" , cod_producto="+codprod+", cod_ref="+codref+" ,nombre="+nombre+", cant="+cant+" , precio_unitario="+punit+" , descuento="+desc+" , subtotal="+subtotal+" , valor_iva="+iva+" , valor_total="+ptotal+", estado=6 , cod_usufk="+user+" , fecha="+Fecha+" , hora="+Hora+" ]";
			
			PreparedStatement ps = null;
		    Conexion con=new Conexion();													
		    ps=con.conn.prepareStatement("INSERT INTO ord_detalle_ordencompra (cod_ordencomprafk, cod_proveedorfk,cod_productofk,cod_referencia, nombre, cant, precio_unitario, descuento, subtotal,valor_iva,valor_total,cod_usufk,fecha,hora,cod_ivafk,estado)" +
			"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
			ps.setString(1,codoc);
			ps.setString(2,codprove);
			ps.setString(3,codprod);
			ps.setString(4,codref);
			ps.setString(5,nombre);
			ps.setString(6,cant);
			ps.setString(7,punit);
			ps.setString(8,desc);
			ps.setString(9,subtotal);
			ps.setString(10,iva);
			ps.setString(11,ptotal);
			ps.setString(12,user);
			ps.setDate(13,Fecha);
			ps.setTime(14,Hora);
			ps.setString(15,codiiva);
			ps.setString(16,"6");
			ps.executeUpdate();
			ps.close();
			con.cerrar();	
			
			java.sql.ResultSet rs=null;
			Statement st = null;
	        Conexion con2=new Conexion();
	        st = con2.conn.createStatement();
	        rs=st.executeQuery("SELECT SUM(valor_total) as total FROM ord_detalle_ordencompra where cod_ordencomprafk="+codoc+" and (estado=1 OR estado=6) ");
			String total="0";
			if(rs.next()){
				total=rs.getString(1);
			}rs.getStatement().getConnection().close();
			
			con2.cerrar();
			
			PreparedStatement pss = null;
		    Conexion con3=new Conexion();	
		    pss=con3.conn.prepareStatement("UPDATE ord_orden_compra SET valor_total='"+total+"' where codigo="+codoc+" ");;
		    pss.executeUpdate();
			pss.close();
			con3.cerrar();
		    
		    
		}catch(Exception ex){
        	System.out.println("ERROR EN MetodoAprobar>>AdicionarProducto"+ex);
		}
	}
	
	
	public void EliminarDetOC(String codoc,String coddet,Date Fecha, Time Hora,String user,String concep) throws SQLException{
		try{
			java.sql.ResultSet rs=null;
			Statement st = null;
	        Conexion con2=new Conexion();
	        st = con2.conn.createStatement();
	        rs=st.executeQuery("select estado from ord_detalle_ordencompra where codigo="+coddet+" ");
	       System.out.println("select estado from ord_detalle_ordencompra where codigo="+coddet+" ");
			String antest=""; //estado con el que estaba  ese detalle que vamos a eliminar 
			if(rs.next()){
				antest=rs.getString(1);
			}rs.getStatement().getConnection().close();
			con2.cerrar();
			String nvoest="";
			if(antest.equals("1")){
				nvoest="31"; //Eliminado desde el modulo de aprobacion y el item fue generado en el proceso de emision 
			}else{
				if(antest.equals("6")){
					nvoest="36"; //Eliminado desde el modulo de aprobacion y el item fue creado desde este mismo modulo 
				}
			}
			System.out.println("nvoest "+nvoest);
			PreparedStatement pss = null;
		    Conexion con3=new Conexion();	
		    pss=con3.conn.prepareStatement("INSERT INTO ord_auditaorden (cod_detalleordcomp_fk,estado,descrip,user,fecha,hora) VALUES (?,?,?,?,?,?)");;
		    pss.setString(1,coddet);
			pss.setString(2,nvoest);
			pss.setString(3,"ELIMINADO DESDE EL MODULO DE APROBACION POR ["+concep+"]");
			pss.setString(4,user);
			pss.setDate(5,Fecha);
			pss.setTime(6,Hora);
		    pss.executeUpdate();
			pss.close();
			con3.cerrar();
			
			PreparedStatement ps = null;
		    Conexion con=new Conexion();	
		    ps=con.conn.prepareStatement("UPDATE ord_detalle_ordencompra SET estado="+nvoest+"  where codigo="+coddet+" ");
		    ps.executeUpdate();
			ps.close();
			con.cerrar();
			System.out.println("UPDATE ord_detalle_ordencompra SET estado="+nvoest+"  where codigo="+coddet+" ");;
			
			java.sql.ResultSet rss=null;
			Statement stt = null;
	        Conexion con4=new Conexion();
	        stt = con4.conn.createStatement();
	        rss=stt.executeQuery("SELECT SUM(valor_total) as total FROM ord_detalle_ordencompra where cod_ordencomprafk="+codoc+" and (estado=1 OR estado=6) ");
			String total="0";
			if(rss.next()){
				total=rss.getString(1);
			}rss.getStatement().getConnection().close();
			con4.cerrar();
			
			PreparedStatement psa = null;
		    Conexion con5=new Conexion();	
		    psa=con5.conn.prepareStatement("UPDATE ord_orden_compra SET valor_total='"+total+"' where codigo="+codoc+" ");;
		    psa.executeUpdate();
			psa.close();
			con5.cerrar();
	
		}catch(Exception ex){
        	System.out.println("ERROR EN MetodoAprobar>>EliminarDetOC"+ex);
		}
	
	}
	
}
