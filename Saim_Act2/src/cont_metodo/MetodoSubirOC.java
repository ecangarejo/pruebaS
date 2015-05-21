package cont_metodo;

import java.sql.PreparedStatement;
import java.sql.Statement;

import adm_logica.Conexion;

public class MetodoSubirOC {
	
			public java.sql.ResultSet  BuscarListUsuarios(){
					/*Me Busca solo los usuarios que alguna vez hubieran descargado una orden de compra */
						 java.sql.ResultSet rs=null;
					     Statement st = null;
					   
							try{		
								 Conexion con=new Conexion();
					        	st = con.conn.createStatement();
					        	rs=st.executeQuery("SELECT u.usuario, u.usu_codigo FROM cont_predoc  c, seg_usuario u WHERE c.cod_userfk=u.usu_codigo GROUP BY u.usu_codigo ");   
					        	
							}catch(Exception ex){
					            	System.out.println("Error en MetodoSubirOC>BuscarListUsuarios "+ex);
					            }	
					        return rs;    
			}
			
			
			public java.sql.ResultSet  BuscarProvedores(){
				/*Me Busca solo los terceros/ proveedores  que han sido descargados */
					 java.sql.ResultSet rs=null;
				     Statement st = null;
				   
						try{		
							 Conexion con=new Conexion();
				        	st = con.conn.createStatement();
				        	rs=st.executeQuery("SELECT t.razon_social, t.codigo FROM cont_predoc  c, cont_terceros t  WHERE c.cod_tercerofk=t.codigo GROUP BY t.codigo  ORDER BY t.razon_social ASC ");   
				        	
						}catch(Exception ex){
				            	System.out.println("Error en MetodoSubirOC>>BuscarProvedores "+ex);
				            }	
				        return rs;    
		}
			
			
			public java.sql.ResultSet  BuscarOrdenes(String sqll){
				/*Me Busca solo los terceros/ proveedores  que han sido descargados */
					 java.sql.ResultSet rs=null;
				     Statement st = null;
				   
						try{		
							 Conexion con=new Conexion();
				        	st = con.conn.createStatement();
				        	rs=st.executeQuery(sqll); 
				        	System.out.println(sqll);
				        	
						}catch(Exception ex){
				            	System.out.println("Error en MetodoSubirOC>>BuscarOrdenes "+ex);
				            }	
				        return rs;    
		}
			
			public java.sql.ResultSet  BuscarEstAnioPeriodo(String anio,String periodo){
				/*Me informa si el periodo existe y si esta activo  */
					 java.sql.ResultSet rs=null;
				     Statement st = null;
				   
						try{		
							 Conexion con=new Conexion();
				        	st = con.conn.createStatement();
				        	rs=st.executeQuery("SELECT bloqueo FROM cont_anio_periodo WHERE anio='"+anio+"' AND periodo like '%"+periodo+"' "); 
				        	System.out.println("SELECT bloqueo FROM cont_anio_periodo WHERE anio='"+anio+"' AND periodo like '%"+periodo+"' ");
				        	
						}catch(Exception ex){
				            	System.out.println("Error en MetodoSubirOC>>BuscarEstAnioPeriodo "+ex);
				            }	
				        return rs;    
		}
			
			public java.sql.ResultSet  VerPrecargue(String codoc,String nofact){
				/*Me informa si el periodo existe y si esta activo  */
					 java.sql.ResultSet rs=null;
				     Statement st = null;
				   
						try{		
							 Conexion con=new Conexion();
				        	st = con.conn.createStatement();
				        	rs=st.executeQuery("SELECT  o.consec, cp.no_factura, cp.fecha_descargue, cp.fecha_insercion, u.usuario , t.razon_social, SUM(cp.valor_debito) as sumadeb, o.concepto, cp.anio, cp.periodo, c.CodigoCuenta, c.NombreCuenta, cp.cod_centro_subcentrofk,cp.cod_sucursal_ccostofk, SUM(cp.valor_credito) "+
				        						"FROM  cont_predoc cp, ord_orden_compra o, cont_terceros t, seg_usuario u, cont_cuentas c "+
				        						"WHERE  cp.cod_ordenfk=o.codigo AND cp.cod_tercerofk=t.codigo AND u.usu_codigo=cp.cod_userfk AND c.codigo=cp.cod_cuentafk AND o.codigo='"+codoc+"' and cp.no_factura='"+nofact+"' GROUP BY c.CodigoCuenta ORDER BY  sumadeb desc "); 
				        
						}catch(Exception ex){
				            	System.out.println("Error en MetodoSubirOC>>VerPrecargue "+ex);
				            }	
				        return rs;    
		}
			
}
