package cont_metodo;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;

import adm_logica.Conexion;

public class MetodoProduc {
	public java.sql.ResultSet BuscarGrupoporParametrizar(){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT * FROM ord_grupo WHERE cod_cuentafk=0 ");
	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoProduc>>BuscarGrupo "+ex);
	            }	
	            return rs;
	}	
	
	public java.sql.ResultSet BuscarProductosxParametrizar(){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT o.* FROM ord_producto o , ord_produc_grupo opg  WHERE o.cod_cuentafk=0 and opg.cod_productofk=o.codigo ");
	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoProduc>>BuscarProductosxParametrizar "+ex);
	            }	
	            return rs;
	}	
	
	public java.sql.ResultSet BusProdxParaG(String codgrup){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT o.* FROM ord_producto o , ord_produc_grupo opg  WHERE o.cod_cuentafk!=0 and opg.cod_productofk=o.codigo and opg.cod_grupofk='"+codgrup+"' ");
	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoProduc>>BusProdxParaG "+ex);
	            }	
	            return rs;
	}
	
	public java.sql.ResultSet BuscarCtaGrupo(String codpro){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT g.* FROM ord_grupo g , ord_produc_grupo opg  WHERE  opg.cod_grupofk=g.codigo and g.cod_cuentafk!=0 and opg.cod_productofk='"+codpro+"' ");
	        	System.out.println("SELECT g.* FROM ord_grupo g , ord_produc_grupo opg  WHERE  opg.cod_grupofk=g.codigo and g.cod_cuentafk!=0 and opg.cod_productofk='"+codpro+"' ");
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoProduc>>BuscarProductosxParametrizar "+ex);
	            }	
	            return rs;
	}
	
	public java.sql.ResultSet BProd(String texto){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	        /* Busca todas los productos que ya tiene uan cuenta asignada */
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT * FROM ord_producto  where nombre like '%"+texto+"%' and cod_cuentafk!=0 ");
	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoProduc>>BProd "+ex);
	            }	
	            return rs;
	}
	
	public java.sql.ResultSet BGrup(String texto){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	        /* Busca todas los grupos que ya tiene una cuenta asignada */
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT * FROM ord_grupo where nombre like '%"+texto+"%' and cod_cuentafk!=0 ");
	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoProduc>>BGrup "+ex);
	            }	
	            return rs;
	}
	
	public java.sql.ResultSet BusGrupo(String codgrup){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       /*Busca la informacion del grupo pero por codigo */
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT * FROM ord_grupo where codigo='"+codgrup+"' ");
	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoProduc>>BusGrupo "+ex);
	            }	
	            return rs;
	}
	
	public java.sql.ResultSet BusProdPara(){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT g.cuenta, g.nombre, p.CodigoCuenta, p.NombreCuenta, p.cod_referencia,p.nombre, ot.nombre "+
	        						"FROM ord_producto p, ord_produc_grupo opg, ord_grupo g , ord_tipoproducto ot "+
	        						"WHERE p.codigo=opg.cod_productofk AND opg.cod_grupofk=g.codigo AND p.cod_cuentafk!=0 AND g.cod_cuentafk!=0 AND ot.codigo=p.tipo_productofk ");
	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoProduc>>BusProdPara "+ex);
	            }	
	            return rs;
	}
	
	
	public java.sql.ResultSet BusGrupPara(){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery(" SELECT g.nombre, c.CodigoCuenta, c.NombreCuenta "+
	        						"FROM ord_grupo g, cont_cuentas c "+
	        						"WHERE c.codigo=g.cod_cuentafk ");
	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoProduc>>BusGrupPara "+ex);
	            }	
	            return rs;
	}
	
	public java.sql.ResultSet BGrupodeProd(String codprod){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	        /* Busca el grupo de del producto seleccionado, esta consulta se hace para jalar la cuenta del grupo 
	         * y las cuenta seleccionadas para el producto sean derivada de la cuenta del grupo  */
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT g.* FROM ord_produc_grupo opg, ord_grupo g WHERE opg.cod_grupofk=g.codigo and opg.cod_productofk='"+codprod+"' ");
	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoProduc>>BGrupodeProd "+ex);
	            }	
	            return rs;
	}
	
	public java.sql.ResultSet BCuentaGrupo(String codgrup){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	        /*Busca la cuenta del grupo   */
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT g.*, c.NombreCuenta  FROM ord_grupo g, cont_cuentas c  WHERE g.cod_cuentafk=c.codigo AND g.codigo='"+codgrup+"' ");
	        	System.out.println("SELECT g.*, c.NombreCuenta  FROM ord_grupo g, cont_cuentas c  WHERE g.cod_cuentafk=c.codigo AND g.codigo='"+codgrup+"' ");
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoProduc>>BCuentaGrupo "+ex);
	            }	
	            return rs;
	}
	
	
	public java.sql.ResultSet BusNombreProducto(String codprod){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	        /* Busca el nombre del producto por codigo */
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT * FROM ord_producto  where codigo='"+codprod+"' ");
	        	System.out.println("SELECT * FROM ord_producto  where codigo='"+codprod+"' ");
	        	
	        }catch(Exception ex){
	        	System.out.println("ERROR SELECT * FROM ord_producto  where codigo='"+codprod+"' ");
	            	System.out.println("Error en MetodoProduc>>BusNombreProducto "+ex);
	            }	
	            return rs;
	}
	
	public java.sql.ResultSet BuscarCtasProducto(String ctagrup, String codprod,String texto){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	        /* Busca las cuentas para el producto escogido teniendo en cuenta */
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT codigo,CodigoCuenta,NombreCuenta FROM cont_cuentas where TipoCuenta='Auxiliar' and (CodigoCuenta LIKE '%"+texto+"%' OR NombreCuenta LIKE '%"+texto+"%' ) AND CodigoCuenta LIKE '"+ctagrup+"%' ORDER BY CodigoCuenta ");
	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoProduc>>BuscarCtasProducto "+ex);
	            }	
	            return rs;
	}
	
	
	
	
	public void ActCuenta(String codprod,String  codcuenta_ant,String codigocuenta_ant,String nombrecta_ant,String codcuenta,String  codigocuenta_nva,String nombrecta_nva,Date Fecha,Time Hora,String user) throws SQLException{
		
		java.sql.ResultSet rs=null;
		 Statement stt = null;
		try{	
				PreparedStatement ps = null;
			    Conexion con3=new Conexion();													
			    ps=con3.conn.prepareStatement("INSERT INTO ord_aud_ctaprod(cod_productofk,cod_cta_anteriorfk,codigocta_ant,nombre_ant,cod_cta_nuevafk,codigocta_nva,nombre_nva,Fecha,Hora,user ) VALUES (?,?,?,?,?,?,?,?,?,?) ");
			    ps.setString(1,codprod);
			    ps.setString(2,codcuenta_ant);
			    ps.setString(3,codigocuenta_ant);
			    ps.setString(4,nombrecta_ant);
			    ps.setString(5,codcuenta);
			    ps.setString(6,codigocuenta_nva);
			    ps.setString(7,nombrecta_nva);
			    ps.setDate(8,Fecha);
			    ps.setTime(9,Hora);
			    ps.setString(10,user);
			    ps.executeUpdate();
				ps.close();
				con3.cerrar();	
				
				PreparedStatement st = null;
			    Conexion con=new Conexion();                                                      
			    st= con.conn.prepareStatement("UPDATE ord_producto SET cod_cuentafk='"+codcuenta+"' , CodigoCuenta='"+codigocuenta_nva+"' , NombreCuenta='"+nombrecta_nva+"' where codigo='"+codprod+"' ");
		    	st.executeUpdate();
		    	st.close();
				con.cerrar();
				
				Conexion con1=new Conexion();
	        	stt = con1.conn.createStatement();
	        	rs=stt.executeQuery("SELECT codigo FROM pre_presupuesto WHERE estado=0 ");
	        	
				while(rs.next()){
					PreparedStatement stp = null;
				    Conexion con2=new Conexion();                                                      
				    stp= con2.conn.prepareStatement("UPDATE pre_detalle_presupuesto SET cod_cuentafk='"+codcuenta+"' , codigocuenta='"+codigocuenta_nva+"' , nombrecuenta='"+nombrecta_nva+"'  WHERE cod_producfk='"+codprod+"' AND cod_presupuestofk='"+rs.getString(1)+"'  ");
			    	stp.executeUpdate();
			    	stp.close();
					con2.cerrar();
				}rs.getStatement().getConnection().close();
				
				
		}catch(Exception ex){
			System.out.println("ERROR EN MetodoProduc>>ActCuenta "+ex);
		}
		
}
	
	
	public java.sql.ResultSet BusCta(String codcta){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT * FROM cont_cuentas where codigo='"+codcta+"' ");
	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoProduc>>BusCta "+ex);
	            }	
	            return rs;
	}
	
	

	
	public java.sql.ResultSet BuscarProdAsocGrupo(String codgrup){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT * FROM ord_grupo g, ord_produc_grupo opg, ord_producto p "+
	        						"WHERE p.codigo=opg.cod_productofk AND g.codigo=opg.cod_grupofk  AND p.cod_cuentafk!=0 AND p.estado=0  AND g.codigo='"+codgrup+"' LIMIT 1 ");
	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoProduc>>BuscarProdAsocGrupo "+ex);
	            }	
	            return rs;
	}
	
	public java.sql.ResultSet BuscarAux(String codigo,String tipo,String numcuenta){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(tipo.equals("1")){
        	//Grupo Cuentas Mayor 	 
        		rs=st.executeQuery("SELECT codigo,CodigoCuenta,NombreCuenta FROM cont_cuentas where TipoCuenta='Mayor' and (CodigoCuenta LIKE '%"+codigo+"%' OR NombreCuenta LIKE '%"+codigo+"%' ) ORDER BY CodigoCuenta  LIMIT 20");
        		//System.out.println("1 -> SELECT codigo,CodigoCuenta,NombreCuenta FROM cont_cuentas where TipoCuenta='Mayor' and (CodigoCuenta LIKE '%"+codigo+"%' OR NombreCuenta LIKE '%"+codigo+"%' ) ORDER BY CodigoCuenta LIMIT 20 ");
        	}else{
        		if(tipo.equals("2")){
        		// Grupo Cuentas Auxiliar o Proveedores 
        		rs=st.executeQuery("SELECT codigo,CodigoCuenta,NombreCuenta FROM cont_cuentas where TipoCuenta='Auxiliar' and (CodigoCuenta LIKE '%"+codigo+"%' OR NombreCuenta LIKE '%"+codigo+"%' ) ORDER BY CodigoCuenta   LIMIT 20 ");
        		//System.out.println("2 -> SELECT codigo,CodigoCuenta,NombreCuenta FROM cont_cuentas where TipoCuenta='Auxiliar' and (CodigoCuenta LIKE '%"+codigo+"%' OR NombreCuenta LIKE '%"+codigo+"%' ) ORDER BY CodigoCuenta LIMIT 20 ");
        		}else{
        			//para productos
        			rs=st.executeQuery("SELECT codigo,CodigoCuenta,NombreCuenta FROM cont_cuentas where TipoCuenta='Auxiliar' and (CodigoCuenta LIKE '%"+codigo+"%' OR NombreCuenta LIKE '%"+codigo+"%' ) AND CodigoCuenta LIKE '"+numcuenta+"%' ORDER BY CodigoCuenta LIMIT 20 ");
        			//System.out.println("3 -> SELECT codigo,CodigoCuenta,NombreCuenta FROM cont_cuentas where TipoCuenta='Auxiliar' and (CodigoCuenta LIKE '%"+codigo+"%' OR NombreCuenta LIKE '%"+codigo+"%' ) AND CodigoCuenta LIKE '"+numcuenta+"%' ORDER BY CodigoCuenta LIMIT 20 ");
        		}
        	}
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProduc>>BuscarAux "+ex);
        }	
        return rs;
    }
	
	public void GuardarCtaGrupo(String ctaaux,String codcta,String codgrupo,Date Fecha, Time Hora, String user) throws SQLException{
		//System.out.print(cod_medico);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();													
			    ps=con.conn.prepareStatement("UPDATE ord_grupo SET cod_cuentafk=?, cuenta=? WHERE codigo='"+codgrupo+"'");
			    ps.setString(1,codcta);
			    ps.setString(2,ctaaux);
			    ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoProduc>>GuardarGrupo "+ex);
			}
			
			String descripcion="";
			int  vali=0;
			descripcion="Datos de asignacion de cuenta cod_cuentafk="+codcta+", ctaaux="+ctaaux+", fecha="+Fecha+", hora="+Hora;
		
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
		        	System.out.println("ERROR EN MetodoProduc>>GuardarAudGrupo "+ex);
				}
			}
			
	
	public void GuardarCtaProd(String ctaaux,String codcta,String codgrupo,Date Fecha, Time Hora, String user) throws SQLException{
		
		 java.sql.ResultSet rs=null;
		 Statement st = null;
		try{
				Conexion con1=new Conexion();
				st = con1.conn.createStatement();
				rs=st.executeQuery("SELECT codigo,CodigoCuenta,NombreCuenta,NaturalezaCuenta FROM cont_cuentas where codigo='"+codcta+"' ");
				String nombreCuenta="";
				if(rs.next()){
					nombreCuenta=rs.getString(3);
				}rs.getStatement().getConnection().close();
				
				PreparedStatement ps = null;
			    Conexion con=new Conexion();													
			    ps=con.conn.prepareStatement("UPDATE ord_producto SET cod_cuentafk=?, CodigoCuenta=?, NombreCuenta=? WHERE codigo='"+codgrupo+"'");
			    ps.setString(1,codcta);
			    ps.setString(2,ctaaux);
			    ps.setString(3,nombreCuenta);
			    ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoProduc>>GuardarCtaProd "+ex);
			}
		}
	
	public void GuardarCtaProd2(String codcta,String codgrupo,Date Fecha, Time Hora, String user) throws SQLException{
		
		 java.sql.ResultSet rs=null;
		 Statement st = null;
		try{
				Conexion con1=new Conexion();
				st = con1.conn.createStatement();
				rs=st.executeQuery("SELECT codigo,CodigoCuenta,NombreCuenta,NaturalezaCuenta FROM cont_cuentas where codigo='"+codcta+"' ");
				String nombreCuenta="";
				String ctaaux="";
				if(rs.next()){
					ctaaux=rs.getString(2);
					nombreCuenta=rs.getString(3);
				}rs.getStatement().getConnection().close();
				
				PreparedStatement ps = null;
			    Conexion con=new Conexion();													
			    ps=con.conn.prepareStatement("UPDATE ord_producto SET cod_cuentafk=?, CodigoCuenta=?, NombreCuenta=? WHERE codigo='"+codgrupo+"'");
			    ps.setString(1,codcta);
			    ps.setString(2,ctaaux);
			    ps.setString(3,nombreCuenta);
			    ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoProduc>>GuardarCtaProd2 "+ex);
			}
		}
	
	
	public void EnviarMensaje(String useradmin ,String user,String asunto,Date Fecha, Time Hora,String descrip) {
		
		 java.sql.ResultSet rs=null;
		 Statement st = null;
		try{
			PreparedStatement ps = null;
        	Conexion con=new Conexion();
        	ps = con.conn.prepareStatement("INSERT INTO seg_mensajes (cod_usuario,remitente,estado,fechaenvio,horaenvio,asunto,mensaje) VALUES (?,?,?,?,?,?,?)");
        	ps.setString(1,useradmin);
        	ps.setString(2,user);
        	ps.setString(3,"0");
        	ps.setDate(4,Fecha);
        	ps.setTime(5,Hora);
        	ps.setString(6,asunto);
        	ps.setString(7,descrip);
        	ps.executeUpdate();
			ps.close();
			con.cerrar();
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoProduc>>EnviarMensaje "+ex);
			}
		}
	
	public java.sql.ResultSet BuscarProveedores(){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT p.nit, p.razon_social, p.codigo FROM  farc_proveedor p, ord_prov_terc t WHERE  p.codigo=t.cod_provefk");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProduc>>BuscarProveedores "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet BusProvexCta(){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT p.nit, p.razon_social, p.codigo FROM  farc_proveedor p, ord_prov_terc t WHERE  p.codigo=t.cod_provefk AND cod_cuentafk=0 ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProduc>>BusProvexCta "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet BuscarIva(){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM ord_iva ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProduc>>BuscarIva "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarRfte(){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM ord_retefuente ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProduc>>BuscarRfte "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarIca(){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM ord_ica");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProduc>>BuscarIca "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarCre(){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM ord_cre ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProduc>>BuscarCre "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet BuscarValores(String Tipo, String dato, String reg){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	System.out.println("Tip "+Tipo+" dato"+dato);
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(Tipo.equals("1")){
        		rs=st.executeQuery("SELECT * FROM ord_iva where valor='"+dato+"' and codigo!='"+reg+"'  LIMIT 1 ");
        		System.out.println("SELECT * FROM ord_iva where valor='"+dato+"' and codigo!='"+reg+"' LIMIT 1 ");
			}else{
				if(Tipo.equals("2")){
					rs=st.executeQuery("SELECT * FROM ord_retefuente where valor='"+dato+"' and codigo!='"+reg+"' LIMIT 1 ");
					System.out.println("SELECT * FROM ord_retefuente where valor='"+dato+"' and codigo!='"+reg+"' LIMIT 1 ");
				}else{
					if(Tipo.equals("3")){
						rs=st.executeQuery("SELECT * FROM ord_ica where valor='"+dato+"' and codigo!='"+reg+"' LIMIT 1 ");
						System.out.println("SELECT * FROM ord_ica where valor='"+dato+"' and codigo!='"+reg+"' LIMIT 1 ");
					}else{
						if(Tipo.equals("4")){
							rs=st.executeQuery("SELECT * FROM ord_cre where valor='"+dato+"' and codigo!='"+reg+"' LIMIT 1 ");
							System.out.println("SELECT * FROM ord_cre where valor='"+dato+"' and codigo!='"+reg+"' LIMIT 1 ");
						}
					}
				}
			}
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProduc>>BuscarValores "+ex);
        }	
        return rs;
    }
	
	public void AsigCtaProveG(String codcta,Date Fecha, Time Hora, String user) throws SQLException{
		 java.sql.ResultSet rs=null;
		 java.sql.ResultSet rs1=null;
	     Statement st = null;
	     Statement stt = null;
		try{
			
			Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT codigo,CodigoCuenta,NombreCuenta,NaturalezaCuenta FROM cont_cuentas where codigo='"+codcta+"' ");
        	
        	String CodigoCuenta="";
        	String NombreCuenta="";
        	String naturaleza="";
	        	if(rs.next()){
	        		CodigoCuenta=rs.getString(2);
	        		NombreCuenta=rs.getString(3);
	        		naturaleza=rs.getString(4);
	        	}rs.getStatement().getConnection().close();
			System.out.println("cumpliendo .. ");
	        Conexion con4=new Conexion();
	        stt = con4.conn.createStatement();
        	rs1=stt.executeQuery("SELECT * FROM ord_prov_terc");
        	String descripcion="";
	        	while(rs1.next()){
	        		
	        		descripcion=" DATOS ENCONTRADOS [ cod_cuentafk='"+rs1.getString(4)+"' , codigocuenta='"+rs1.getString(5)+"' , nombre='"+rs1.getString(6)+"' , naturaleza='"+rs1.getString(7)+"' ] DATOS NUEVOS [cod_cuentafk='"+codcta+"' , codigocuenta='"+CodigoCuenta+"' , nombre='"+NombreCuenta+"' , naturaleza='"+naturaleza+"' ]";
	        		
	        		PreparedStatement pss = null;
	        		Conexion con3=new Conexion();
	        		pss=con3.conn.prepareStatement("INSERT INTO ord_aud_provterc(cod_provefk,fecha,hora,descripcion)values(?,?,?,?) ");
	        		pss.setString(1,rs1.getString(2));
				    pss.setDate(2,Fecha);
				    pss.setTime(3,Hora);
				    pss.setString(4,descripcion);
				    pss.executeUpdate();
					pss.close();
					con3.cerrar();	
					System.out.println("vamos bien...");
	        	}rs1.getStatement().getConnection().close();
	        	
				PreparedStatement ps = null;
				Conexion con2=new Conexion();													
				ps=con2.conn.prepareStatement("UPDATE ord_prov_terc SET cod_cuentafk=?, codigocuenta=? , nombre=?, naturaleza=? ");
				    ps.setString(1,codcta);
				    ps.setString(2,CodigoCuenta);
				    ps.setString(3,NombreCuenta);
				    ps.setString(4,naturaleza);
				    ps.executeUpdate();
					ps.close();
					con.cerrar();
					System.out.println("Lo has logrado ");
				}catch(Exception ex){
		        	System.out.println("ERROR EN MetodoProduc>>AsigCtaProveG "+ex);
				}	
	
	}
	
	
	public void AsigCtaProveI(String codcta,Date Fecha, Time Hora, String user, String codprove) throws SQLException{
		 java.sql.ResultSet rs=null;
		 java.sql.ResultSet rs1=null;
	     Statement st = null;
	     Statement stt = null;
		try{
			
		Conexion con=new Conexion();
       	st = con.conn.createStatement();
       	rs=st.executeQuery("SELECT codigo,CodigoCuenta,NombreCuenta,NaturalezaCuenta FROM cont_cuentas where codigo='"+codcta+"' ");
       	
       	String CodigoCuenta="";
       	String NombreCuenta="";
       	String naturaleza="";
	        	if(rs.next()){
	        		CodigoCuenta=rs.getString(2);
	        		NombreCuenta=rs.getString(3);
	        		naturaleza=rs.getString(4);
	        	}rs.getStatement().getConnection().close();
	      System.out.println("Comenzando individual...");
	    Conexion con4=new Conexion();
	    stt = con4.conn.createStatement();	
       	rs1=stt.executeQuery("SELECT * FROM ord_prov_terc where cod_provefk='"+codprove+"' ");
       	String descripcion="";
	        	while(rs1.next()){
	        		
	        		descripcion=" DATOS ENCONTRADOS [ cod_cuentafk='"+rs1.getString(4)+"' , codigocuenta='"+rs1.getString(5)+"' , nombre='"+rs1.getString(6)+"' , naturaleza='"+rs1.getString(7)+"' ] DATOS NUEVOS [cod_cuentafk='"+codcta+"' , codigocuenta='"+CodigoCuenta+"' , nombre='"+NombreCuenta+"' , naturaleza='"+naturaleza+"' ]";
	        		
	        		PreparedStatement pss = null;
	        		Conexion con3=new Conexion();
	        		pss=con3.conn.prepareStatement("INSERT INTO ord_aud_provterc (cod_provefk,fecha,hora,descripcion )values(?,?,?,?) ");
	        		pss.setString(1,rs1.getString(2));
				    pss.setDate(2,Fecha);
				    pss.setTime(3,Hora);
				    pss.setString(4,descripcion);
				    pss.executeUpdate();
					pss.close();
					con3.cerrar();	
					System.out.println("vamos bien en Individual....");
	        	}rs1.getStatement().getConnection().close();
	        	
				PreparedStatement ps = null;
				Conexion con2=new Conexion();													
				ps=con2.conn.prepareStatement("UPDATE ord_prov_terc SET cod_cuentafk=?, codigocuenta=? , nombre=?, naturaleza=? WHERE cod_provefk='"+codprove+"'");
				    ps.setString(1,codcta);
				    ps.setString(2,CodigoCuenta);
				    ps.setString(3,NombreCuenta);
				    ps.setString(4,naturaleza);
				    ps.executeUpdate();
					ps.close();
					con2.cerrar();	
					System.out.println("individual ok  UPDATE ord_prov_terc SET cod_cuentafk=?, codigocuenta=? , nombre=?, naturaleza=? WHERE cod_provefk='"+codprove+"'");
				}catch(Exception ex){
		        	System.out.println("ERROR EN MetodoProduc>>AsigCtaProveI "+ex);
				}	
	
	}
	
	
	public void ActImpuestos(String codreg,String cant,String desc, String codcta,String uvt,String base,String  user,Date Fecha,Time Hora,String Tipo, String tabla) throws SQLException{
		
		java.sql.ResultSet rs=null;
		java.sql.ResultSet rs1=null;
		 Statement stt = null;
		 Statement st = null;
		try{	
			
				String sql1="";//Busqueda de valores identicos para o ingresar estos datos a la tabla de auditoria
				String sql2="";//Actualizacion de la tabla 
				String sql3="";//Busqueda de datos
				
				if(Tipo.equals("1")){
					sql1="SELECT * FROM ord_iva where codigo='"+codreg+"' and valor='"+cant+"' and descri='"+desc+"' and cod_cuentafk='"+codcta+"' ";
					sql2="UPDATE ord_iva SET valor='"+cant+"' , descri='"+desc+"' , cod_cuentafk='"+codcta+"' where codigo='"+codreg+"' ";
					sql3="SELECT * FROM ord_iva where codigo='"+codreg+"' ";
					//System.out.println("REVISION DE CONSULTA "+sql2);
					
				}else{
					if(Tipo.equals("2")){
						sql1="SELECT codigo FROM ord_retefuente where codigo='"+codreg+"' and valor='"+cant+"' and descri='"+desc+"' and cod_cuentafk='"+codcta+"' and base_uvt='"+base+"' and uvt='"+uvt+"' ";
						sql2="UPDATE ord_retefuente SET valor='"+cant+"' , descri='"+desc+"' , cod_cuentafk='"+codcta+"' , base_uvt='"+base+"' , uvt='"+uvt+"' where codigo='"+codreg+"' ";
						sql3="SELECT * FROM ord_retefuente where codigo='"+codreg+"' ";
					}else{
						if(Tipo.equals("3")){
							sql1="SELECT codigo FROM ord_ica where codigo='"+codreg+"' and valor='"+cant+"' and descri='"+desc+"' and cod_ctaicfk='"+codcta+"' ";
							sql2="UPDATE ord_ica SET valor='"+cant+"' , descri='"+desc+"' , cod_ctaicfk='"+codcta+"' where codigo='"+codreg+"' ";
							sql3="SELECT * FROM ord_ica where codigo='"+codreg+"' ";
						}else{
							if(Tipo.equals("4")){
								
								sql1="SELECT codigo FROM ord_cre where codigo='"+codreg+"' and valor='"+cant+"' and descri='"+desc+"' and cod_ctacrefk='"+codcta+"' ";
								sql2="UPDATE ord_cre SET valor='"+cant+"' , descri='"+desc+"' , cod_ctacrefk='"+codcta+"' where codigo='"+codreg+"' ";
								sql3="SELECT * FROM ord_cre where codigo='"+codreg+"' ";
							}
						}
					}
				}
			
				Conexion con1=new Conexion();
	        	stt = con1.conn.createStatement();
	        	rs=stt.executeQuery(sql1);
	        	System.out.println(sql1);
	        	String cod="";
	        	String acant="";
				String adesc="";
				String acodcta="";
				String abase="";
				String auvt="";
				String acodreg="";
				if(rs.next()){
					cod="SI";
		        	
					
					
				}else{
					Conexion con2=new Conexion();
		        	st = con2.conn.createStatement();
		        	rs1=st.executeQuery(sql3);
		        	if(rs1.next()){
		        		acant=rs1.getString(2);
						adesc=rs1.getString(3);
						if(Tipo.equals("2")){
							acodcta=rs1.getString(6);
							abase=rs1.getString(4);
							auvt=rs1.getString(5);
						}else{
							acodcta=rs1.getString(4);
							abase="";
							auvt="";
						}
						acodreg=rs1.getString(1);
		        		
		        	}rs1.getStatement().getConnection().close();
		    
				}rs.getStatement().getConnection().close();
				
			
				if(!cod.equals("SI")){
					PreparedStatement ps = null;
				    Conexion con3=new Conexion();
				    ps=con3.conn.prepareStatement(sql2);
				    System.out.println(sql2);
				    ps.executeUpdate();
					ps.close();
					con3.cerrar();
					
					PreparedStatement p=null;
					Conexion con=new Conexion();
					p=con.conn.prepareStatement("INSERT INTO ord_audimpuestos  (valor,descrip,ctafk,base,uvt,cod_registro,tabla,user,fecha,hora) values (?,?,?,?,?,?,?,?,?,?) ");
					System.out.println("INSERT INTO ord_audimpuestos  (valor,descrip,ctafk,base,uvt,cod_registro,tabla,user,fecha,hora) values (?,?,?,?,?,?,?,?,?,?) ");
					p.setString(1, acant);
					p.setString(2, adesc);
					p.setString(3, acodcta);
					p.setString(4, abase);
					p.setString(5, auvt);
					p.setString(6, acodreg);
					p.setString(7, tabla);
					p.setString(8, user);
					p.setDate(9, Fecha);
					p.setTime(10, Hora);
					p.executeUpdate();
					p.close();
					con.cerrar();
				}
				
		}catch(Exception ex){
			System.out.println("ERROR EN MetodoProduc>>ActImpuestos "+ex);
		}
		
}
	
	public void GuardarImpuestos(String Tipo,String cant,String descrip,String codcta,String base,String uvt,String user) {
		
		 java.sql.ResultSet rs=null;
		 Statement st = null;
		try{
			PreparedStatement ps = null;
			Conexion con=new Conexion();
			if(Tipo.equals("1")){
				ps = con.conn.prepareStatement("INSERT INTO ord_iva (valor,descri,cod_cuentafk) VALUES (?,?,?)");
				ps.setString(1,cant);
				ps.setString(2,descrip);
	       		ps.setString(3,codcta);
	       		ps.executeUpdate();
	       		ps.close();
			}else{
				if(Tipo.equals("2")){
					ps = con.conn.prepareStatement("INSERT INTO ord_retefuente (valor,descri,base_uvt, uvt,cod_cuentafk) VALUES (?,?,?,?,?)");
					ps.setString(1,cant);
					ps.setString(2,descrip);
					ps.setString(3,base);
					ps.setString(4,uvt);
		       		ps.setString(5,codcta);
		       		ps.executeUpdate();
		       		ps.close();
				}else{
					if(Tipo.equals("3")){
						ps = con.conn.prepareStatement("INSERT INTO ord_ica (valor,descri,cod_ctaicfk) VALUES (?,?,?)");
						ps.setString(1,cant);
						ps.setString(2,descrip);
			       		ps.setString(3,codcta);
			       		ps.executeUpdate();
			       		ps.close();
					}else{
						if(Tipo.equals("4")){
							ps = con.conn.prepareStatement("INSERT INTO ord_cre (valor,descri,cod_ctacrefk) VALUES (?,?,?)");
							ps.setString(1,cant);
							ps.setString(2,descrip);
				       		ps.setString(3,codcta);
				       		ps.executeUpdate();
				       		ps.close();
						}
					}
				}
			}
		
			con.cerrar();
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoProduc>>GuardarImpuestos "+ex);
			}
		}
	
	
	
}
