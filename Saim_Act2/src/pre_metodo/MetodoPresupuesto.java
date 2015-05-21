package pre_metodo;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;

import adm_logica.Conexion;

public class MetodoPresupuesto {
	
	public java.sql.ResultSet BuscarPresupuesto(String rpi,String rpf,String ano,String concep,int tipo,String suc,String ccosto,String subcc){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	if((tipo==3)||(tipo==4)){
	        		if(ccosto.equals("todas")){
	        			if((subcc.equals(""))||(subcc.equals("todas"))){
	        				rs=st.executeQuery("SELECT * FROM pre_presupuesto WHERE periodoini='"+rpi+"' and periodofin='"+rpf+"' and ano='"+ano+"'  and cod_tipofk='"+tipo+"' and suc='"+suc+"' and estado=0 ");
	        			}else{
	        				rs=st.executeQuery("SELECT * FROM pre_presupuesto WHERE periodoini='"+rpi+"' and periodofin='"+rpf+"' and ano='"+ano+"'  and cod_tipofk='"+tipo+"' and suc='"+suc+"' and subccosto='"+subcc+"' and estado=0 ");
	        			}
	        		}else{
	        			if((subcc.equals(""))||(subcc.equals("todas"))){
	        				rs=st.executeQuery("SELECT * FROM pre_presupuesto WHERE periodoini='"+rpi+"' and periodofin='"+rpf+"' and ano='"+ano+"'  and cod_tipofk='"+tipo+"' and suc='"+suc+"' and ccosto='"+ccosto+"' and estado=0 ");
	        			}else{
	        				
	        				rs=st.executeQuery("SELECT * FROM pre_presupuesto WHERE periodoini='"+rpi+"' and periodofin='"+rpf+"' and ano='"+ano+"'  and cod_tipofk='"+tipo+"' and suc='"+suc+"' and subccosto='"+subcc+"' and ccosto='"+ccosto+"' and estado=0 ");
	        			}	        			
	        		}
	        		
	        	}else{
	        			rs=st.executeQuery("SELECT * FROM pre_presupuesto WHERE periodoini='"+rpi+"' and periodofin='"+rpf+"' and ano='"+ano+"'  and cod_tipofk='"+tipo+"' and suc=0 and subccosto=0 and ccosto=0 and estado=0 ");
	        	}      	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoPresupuesto>>BuscarPresupuesto"+ex);
	            }	
	            return rs;
	}

	public java.sql.ResultSet BusTipo(){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT * FROM pre_tipo ");
	        		        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoPresupuesto>>BusTipo"+ex);
	            }	
	            return rs;
	}
	
	public java.sql.ResultSet BusSuc(){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT * FROM cont_sucursales  ");
	        		        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoPresupuesto>>BusSuc"+ex);
	            }	
	            return rs;
	}
	public java.sql.ResultSet Busccosto(String s){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT fc.* FROM cont_surcursal_centrocosto csc, fact_centrocosto fc WHERE  csc.cod_sucursal_fk='"+s+"' AND csc.cod_centro_costo_fk=fc.codigo  ");
	        		        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoPresupuesto>>Busccosto"+ex);
	            }	
	            return rs;
	}
	
	public java.sql.ResultSet BusSubcc(String ccost){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	if(ccost.equals("todas")){
	        		
	        		rs=st.executeQuery("SELECT * FROM fact_subcentros_costo ORDER BY descripcion ASC ");
	        	}else{
	        		rs=st.executeQuery("SELECT fsc.* FROM  cont_centro_subcentro ccs, fact_subcentros_costo fsc WHERE ccs.cod_centro_costo_fk='"+ccost+"' and ccs.cod_sub_centro_costo_fk=fsc.cod_subcentro_costo  ");
	        	}
	        		        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoPresupuesto>>BusSubcc"+ex);
	            }	
	            return rs;
	}
	
	
	public java.sql.ResultSet BusCta(String texto){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT codigo,CodigoCuenta,NombreCuenta FROM cont_cuentas where TipoCuenta='Auxiliar' and (CodigoCuenta LIKE '%"+texto+"%' OR NombreCuenta LIKE '%"+texto+"%' ) ORDER BY CodigoCuenta");
        	System.out.println("SELECT codigo,CodigoCuenta,NombreCuenta FROM cont_cuentas where TipoCuenta='Auxiliar' and (CodigoCuenta LIKE '%"+texto+"%' OR NombreCuenta LIKE '%"+texto+"%' ) ORDER BY CodigoCuenta");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoPresupuesto>>BusCta "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarDatosCta(String codcta){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT codigo,CodigoCuenta,NombreCuenta FROM cont_cuentas where codigo='"+codcta+"' ");
        	System.out.println("SELECT codigo,CodigoCuenta,NombreCuenta FROM cont_cuentas where codigo='"+codcta+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoPresupuesto>>BuscarDatosCta "+ex);
        }	
        return rs;
    }
	
	public void GuardarPre(String rpi,String rpf,String ano,String concep,String t,String suc,String ccosto,String subcc,Date Fecha,Time Hora,String user){
		//System.out.print(cod_medico);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();													
			    ps=con.conn.prepareStatement("insert into pre_presupuesto(periodoini,periodofin,ano,fecha,hora,estado,cod_usufk,cod_tipofk,suc,ccosto,subccosto,concep)values(?,?,?,?,?,?,?,?,?,?,?,?) ");
			    ps.setString(1,rpi);
			    ps.setString(2,rpf);
			    ps.setString(3,ano);
			    ps.setDate(4,Fecha);
			    ps.setTime(5,Hora);
			    ps.setString(6,"0");
			    ps.setString(7, user);
			    ps.setString(8, t);
			    ps.setString(9,suc);
			    ps.setString(10,ccosto);
			    ps.setString(11,subcc);
			    ps.setString(12,concep);
			    ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoPresupuesto>>GuardarPre "+ex);
			}
		}
	
	public java.sql.ResultSet BuscarCodPre(String rpi,String rpf,String ano,String concep,String t,String suc,String ccosto,String subcc,Date Fecha,Time Hora,String user){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM pre_presupuesto WHERE periodoini='"+rpi+"' and periodofin='"+rpf+"' and ano='"+ano+"' and fecha='"+Fecha+"' and hora='"+Hora+"' and estado=0  and cod_tipofk='"+t+"'  and suc='"+suc+"' and ccosto='"+ccosto+"'  and subccosto='"+subcc+"' and concep='"+concep+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoPresupuesto>>BuscarCodPre "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscPeriodo(String codigo){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT * FROM pre_presupuesto where codigo='"+codigo+"'  ");
	        		        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoPresupuesto>>BuscPeriodo"+ex);
	            }	
	            return rs;
	}
	
	public java.sql.ResultSet BuscarMeses(String pini,String pfin){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT * FROM pre_meses where codigo between '"+pini+"' and '"+pfin+"' ");
	        	System.out.println("SELECT * FROM pre_meses where codigo between '"+pini+"' and '"+pfin+"' ");	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoPresupuesto>>BuscarMeses"+ex);
	            }	
	            return rs;
	}
	
	public java.sql.ResultSet BuscarMesesDesc(String pini,String pfin){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT * FROM pre_meses where codigo between '"+pini+"' and '"+pfin+"' ORDER BY codigo DESC");
	        	System.out.println("SELECT * FROM pre_meses where codigo between '"+pini+"' and '"+pfin+"' ORDER BY codigo DESC ");	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoPresupuesto>>BuscarMeses"+ex);
	            }	
	            return rs;
	}
	
	public void Savecta(String codcta,String ref,String nombre, Date Fecha, Time Hora,String user, String codpre){
		//System.out.print(cod_medico);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();													
			    ps=con.conn.prepareStatement("insert into pre_detalle_presupuesto(cod_presupuestofk,cod_cuentafk,codigocuenta,nombrecuenta,cod_usufk,fecha,hora)values(?,?,?,?,?,?,?) ");
			    ps.setString(1,codpre);
			    ps.setString(2,codcta);
			    ps.setString(3,ref);
			    ps.setString(4,nombre);
			    ps.setString(5,user);
			    ps.setDate(6,Fecha);
			    ps.setTime(7,Hora);
			    ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoPresupuesto>>Savecta "+ex);
			}
		}
	
	
	public void Savecta2(String codcta,String ref,String nombre, Date Fecha, Time Hora,String user, String codpre,String codprod){
		//System.out.print(cod_medico);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();													
			    ps=con.conn.prepareStatement("insert into pre_detalle_presupuesto(cod_presupuestofk,cod_cuentafk,codigocuenta,nombrecuenta,cod_usufk,fecha,hora,cod_producfk)values(?,?,?,?,?,?,?,?) ");
			    ps.setString(1,codpre);
			    ps.setString(2,codcta);
			    ps.setString(3,ref);
			    ps.setString(4,nombre);
			    ps.setString(5,user);
			    ps.setDate(6,Fecha);
			    ps.setTime(7,Hora);
			    ps.setString(8, codprod);
			    ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoPresupuesto>>Savecta2 "+ex);
			}
		}
	
	public java.sql.ResultSet BuscodCta(String codcta,String ref,String nombre,Date Fecha, Time Hora,String user,String codpre){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT codigo FROM pre_detalle_presupuesto where cod_presupuestofk='"+codpre+"' and cod_cuentafk='"+codcta+"' and codigocuenta='"+ref+"' and nombrecuenta='"+nombre+"' and fecha='"+Fecha+"' and hora='"+Hora+"' ");
	        	System.out.println("SELECT codigo FROM pre_detalle_presupuesto where cod_presupuestofk='"+codpre+"' and cod_cuentafk='"+codcta+"' and codigocuenta='"+ref+"' and nombrecuenta='"+nombre+"' and fecha='"+Fecha+"' and hora='"+Hora+"' ");       	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoPresupuesto>>BuscodCta"+ex);
	            }	
	            return rs;
	}
	
	
	public java.sql.ResultSet BuscodCta2(String codcta,String ref,String nombre,Date Fecha, Time Hora,String user,String codpre, String codprod){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT codigo FROM pre_detalle_presupuesto where cod_presupuestofk='"+codpre+"' and cod_cuentafk='"+codcta+"' and codigocuenta='"+ref+"' and nombrecuenta='"+nombre+"' and fecha='"+Fecha+"' and hora='"+Hora+"' and cod_producfk='"+codprod+"' ");
	        	System.out.println("SELECT codigo FROM pre_detalle_presupuesto where cod_presupuestofk='"+codpre+"' and cod_cuentafk='"+codcta+"' and codigocuenta='"+ref+"' and nombrecuenta='"+nombre+"' and fecha='"+Fecha+"' and hora='"+Hora+"' and cod_producfk='"+codprod+"' ");       	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoPresupuesto>>BuscodCta2 "+ex);
	            }	
	            return rs;
	}
	
	public java.sql.ResultSet BusNombMes(String cmes){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT * FROM pre_meses where codigo='"+cmes+"' ");
	        	System.out.println("SELECT * FROM pre_meses where codigo='"+cmes+"' ");        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoPresupuesto>>BusNombMes"+ex);
	            }	
	            return rs;
	}
	
	public void InsertValores(String valor,String cmes,String coddetcta,String nmes){
			try{
				PreparedStatement st = null;
				    Conexion con=new Conexion();                                                      
				    st= con.conn.prepareStatement("UPDATE pre_detalle_presupuesto SET "+nmes+"="+valor+" where codigo='"+coddetcta+"'");
			    	st.executeUpdate();
			    	st.close();
					con.cerrar();	
					
				}catch(Exception ex){
		        	System.out.println("ERROR EN MetodoPresupuesto>>InsertValores "+ex);
				}
				System.out.println("UPDATE pre_detalle_presupuesto SET "+nmes+"="+valor+" where codigo='"+coddetcta+"'");

			}
	
	public java.sql.ResultSet BusPre(String codpre){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	        //Encabezado del presupuesto 
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT * FROM pre_presupuesto where codigo='"+codpre+"' ");
	        	System.out.println("SELECT * FROM pre_presupuesto where codigo='"+codpre+"' ");        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoPresupuesto>>BusPre"+ex);
	            }	
	            return rs;
	}
	
	
	public java.sql.ResultSet BuscarCuenta(String codprogru){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	        //Encabezado del presupuesto 
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT p.*, c.NombreCuenta FROM ord_produc_grupo opg, ord_producto p, cont_cuentas c WHERE p.codigo=opg.cod_productofk  AND c.codigo=p.cod_cuentafk AND opg.codigo='"+codprogru+"'   ");
	                	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoPresupuesto>>BuscarCuenta"+ex);
	            }	
	            return rs;
	}
	
	public java.sql.ResultSet BusCtaPre(String codpre,String codcta){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT * FROM pre_detalle_presupuesto where cod_presupuestofk='"+codpre+"' and cod_cuentafk='"+codcta+"' ");
	        	System.out.println("SELECT * FROM pre_detalle_presupuesto where cod_presupuestofk='"+codpre+"' and cod_cuentafk='"+codcta+"' ");	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoPresupuesto>>BusCtaPre"+ex);
	        }	
	            return rs;
	}
	
	
	public void CambiarEstPre(String codpre,String ident,String user, Date Fecha, Time Hora, String obs ) throws SQLException{
		//System.out.print(cod_medico);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			   
				    ps=con.conn.prepareStatement("INSERT INTO pre_aud_estados(estado,cod_prefk,cod_usufk,fecha,hora,observacion)values(?,?,?,?,?,?) ");
				    ps.setString(1,ident);
				    ps.setString(2,codpre);
				    ps.setString(3,user);
				    ps.setDate(4,Fecha);
				    ps.setTime(5,Hora);
				    if(ident.equals("1")){
				    	ps.setString(6,"EMISION DE PRESUPUESTO");
				    	System.out.println("entrando");
				    }else{
				    	if(ident.equals("2")){
				    		// presupuesto aprobado
				    		ps.setString(6,obs);
				    	}else{
				    		if(ident.equals("3")){
				    			// presupuesto ejecutado
				    			ps.setString(6,"PRESUPUESTO EJECUTADO");
				    		}else{
				    			if(ident.equals("4")){
				    				//presupuesto anulado
				    				ps.setString(6,obs);
				    			}else{
				    				if(ident.equals("5")){
				    					//presupuesto rechazado
				    					ps.setString(6,obs);
				    				}
				    			}
				    		}
				    	}
				    }
				    ps.executeUpdate();
					ps.close();
							   
					con.cerrar();
					
					Conexion con2=new Conexion();
					PreparedStatement st = null;
					st= con2.conn.prepareStatement("UPDATE pre_presupuesto SET estado='"+ident+"' where codigo='"+codpre+"'");
				    st.executeUpdate();
				    st.close();
				    con2.cerrar();
	    
			}catch(Exception ex){
	        	System.out.println("MetodoPresupuesto>>CambiarEstPre "+ex);
			}			
		}
	
	public java.sql.ResultSet BusPresu(String codpre){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	        ///DETALLE DEL PRESUPUESTO. 
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT pd.* "+
	        						"FROM pre_presupuesto p, pre_detalle_presupuesto pd "+
	        						"WHERE p.codigo=pd.cod_presupuestofk and p.codigo='"+codpre+"' ");
	        	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoPresupuesto>>BusPresu"+ex);
	            }	
	            return rs;
	}
	
	public java.sql.ResultSet BuscarNombreEmpre(){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT * from empresa ");
	        	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoPresupuesto>>BuscarNombreEmpre"+ex);
	            }	
	            return rs;
	}
	
	public java.sql.ResultSet BuscarFechaR(String codpre){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT * FROM pre_aud_estados where cod_prefk='"+codpre+"' and estado=1 ");
	        	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoPresupuesto>>BuscarFechaR"+ex);
	            }	
	            return rs;
	}
	
	public java.sql.ResultSet BuscarEncGrupo(String codpre){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT SUM(pd.ENERO) AS ENERO,SUM(pd.FEBRERO) AS FEBRERO , SUM(pd.MARZO) AS MARZO, SUM(pd.ABRIL) AS ABRIL, SUM(pd.MAYO) AS MAYO,  "+
	        						"SUM(pd.JUNIO) AS JUNIO,  SUM(pd.JULIO) AS JULIO,  SUM(pd.AGOSTO) AS AGOSTO,  SUM(pd.SEPTIEMBRE) AS SEPTIEMBRE,  SUM(pd.OCTUBRE) AS OCTUBRE, "+
	        						"SUM(pd.NOVIEMBRE) AS NOVIEMBRE,  SUM(pd.DICIEMBRE) AS DICIEMBRE, opg.cod_grupofk,g.cuenta "+
	        						"FROM pre_detalle_presupuesto pd, ord_produc_grupo opg , ord_grupo g WHERE  g.codigo=opg.cod_grupofk AND pd.cod_presupuestofk="+codpre+" AND pd.cod_producfk=opg.cod_productofk GROUP BY  opg.cod_grupofk ");
	        	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoPresupuesto>>BuscarEncGrupo"+ex);
	            }	
	            return rs;
	}
	
	public java.sql.ResultSet BusAgruProducto(String codpre){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT o.codigo, o.cod_referencia, o.nombre , o.cod_cuentafk,o.CodigoCuenta,o.NombreCuenta "+
	        						"FROM pre_detalle_presupuesto pd, ord_producto o "+
	        						"WHERE o.codigo=pd.cod_producfk AND  pd.cod_presupuestofk="+codpre+" GROUP BY pd.cod_producfk");
	        	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoPresupuesto>>BusAgruProducto"+ex);
	            }	
	            return rs;
	}
	
	public java.sql.ResultSet BusDetPres(String codpre,String codpro,String mes,String grupo){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT pdi.mes, SUM(pdi.cant) AS cant,SUM(pdi.valor) AS valor "+
						 "FROM ord_produc_grupo opg,pre_det_preinventario pdi WHERE opg.cod_grupofk='"+grupo+"' AND pdi.cod_progrufk=opg.codigo AND pdi.cod_presupuestofk='"+codpre+"' "+ 
						 "AND pdi.mes='"+mes+"'  AND pdi.cod_productofk='"+codpro+"' ");
	        	System.out.println("SELECT pdi.mes, SUM(pdi.cant) AS cant,SUM(pdi.valor) AS valor "+
						 "FROM ord_produc_grupo opg,pre_det_preinventario pdi WHERE opg.cod_grupofk='"+grupo+"' AND pdi.cod_progrufk=opg.codigo AND pdi.cod_presupuestofk='"+codpre+"' "+ 
						 "AND pdi.mes='"+mes+"'  AND pdi.cod_productofk='"+codpro+"' ");
	        	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoPresupuesto>>BusDetPres"+ex);
	            }	
	            return rs;
	}
	
	
	public java.sql.ResultSet BusUsu(String user){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT concat(sdp.nombre,' ',sdp.apellido) as nombre, sdp.profesion,sdp.ocupacion  FROM seg_usuario u, seg_datos_personales sdp WHERE sdp.dat_codigo=u.dat_codigo_fk and u.usu_codigo='"+user+"' ");
	        	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoPresupuesto>>BusUsu"+ex);
	            }	
	            return rs;
	}
	
	public java.sql.ResultSet BuscarEstadosPre(String codpre){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT p.*,  concat(sdp.nombre,' ',sdp.apellido) as nombre, sdp.profesion,sdp.ocupacion  FROM pre_aud_estados p, seg_usuario u, seg_datos_personales sdp " +
	        						" where p.cod_prefk='"+codpre+"' and u.usu_codigo=p.cod_usufk and sdp.dat_codigo=u.dat_codigo_fk group by p.estado order by p.estado ");
	        	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoPresupuesto>>BuscarEstadosPre"+ex);
	            }	
	            return rs;
	}
	
	
	public java.sql.ResultSet BusPrePorEmit(String user){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT p.*, t.descripcion FROM pre_presupuesto p, pre_tipo t where p.cod_tipofk=t.codigo and p.estado=0  and p.cod_usufk='"+user+"' ");
	        	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoPresupuesto>>BusPrePorEmit"+ex);
	            }	
	            return rs;
	}
	
	
	public java.sql.ResultSet BusPrexA(){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT p.*, t.descripcion FROM pre_presupuesto p, pre_tipo t where p.cod_tipofk=t.codigo and p.estado=1   ");
	        	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoPresupuesto>>BusPrexA"+ex);
	            }	
	            return rs;
	}
	
	public java.sql.ResultSet Bsuc(String codigo){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT * FROM cont_sucursales where codigo='"+codigo+"' ");
	        	System.out.println("SELECT * FROM cont_sucursales where codigo='"+codigo+"' ");        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoPresupuesto>>Bsuc"+ex);
	            }	
	            return rs;
	}
	
	public java.sql.ResultSet Bccosto(String codigo){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT * FROM fact_centrocosto where codigo='"+codigo+"' ");
	        	System.out.println("SELECT * FROM fact_centrocosto where codigo='"+codigo+"' ");        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoPresupuesto>>Bccosto"+ex);
	            }	
	            return rs;
	}
	
	
	public java.sql.ResultSet Bsubcc(String codigo){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT * FROM fact_subcentros_costo where codigo='"+codigo+"' ");
	        	System.out.println("SELECT * FROM fact_subcentros_costo  where codigo='"+codigo+"' ");        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoPresupuesto>>Bsubcc"+ex);
	            }	
	            return rs;
	}
	
	
	public void GuardarConcep(String codpre, String concep){
		
			try{
				PreparedStatement st = null;
				    Conexion con=new Conexion();                                                      
				    st= con.conn.prepareStatement("update pre_presupuesto set concep='"+concep+"' where codigo='"+codpre+"'");
			    	st.executeUpdate();
			    	st.close();
					con.cerrar();				
				}catch(Exception ex){
		        	System.out.println("ERROR EN MetodoPresupuesto>>GuardarConcep "+ex);
				}

			}
	
	public java.sql.ResultSet BuscarProducto(String texto, int ident){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	if(ident==1){
	        	rs=st.executeQuery("SELECT p.*, opg.codigo, g.codigo, g.cod_cuentafk, g.nombre, g.cuenta FROM ord_producto p, ord_produc_grupo opg, ord_grupo g "+
	        			"WHERE opg.cod_productofk=p.codigo AND g.codigo=opg.cod_grupofk AND p.estado=0 AND g.estado=0 AND g.cod_cuentafk!=0 and p.cod_cuentafk!=0 ");
	        	}else{
	        		//agrupa por grupo y sirve para el AutoComGrup
	        		rs=st.executeQuery("SELECT p.*, opg.codigo, g.codigo, g.cod_cuentafk, g.nombre, g.cuenta FROM ord_producto p, ord_produc_grupo opg, ord_grupo g "+
        			"WHERE opg.cod_productofk=p.codigo AND g.codigo=opg.cod_grupofk AND p.estado=0 AND g.estado=0 AND g.cod_cuentafk!=0  and p.cod_cuentafk!=0 group by g.codigo ");
	        	}
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoPresupuesto>>BuscarProducto"+ex);
	            }	
	            return rs;
	}
	
	
	public java.sql.ResultSet BusProducto(String codproduc){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	        //Busca los producto que tengan una cuenta asociada, se coloca esta deliminatante para que el reporte de ejecucion 
	        //funcione correctamente, pues se  busca lo consumido por cta. 
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT o.*, og.codigo FROM ord_producto  o, ord_produc_grupo og where o.codigo='"+codproduc+"' and o.estado=0 and og.cod_productofk=o.codigo ");
	        		        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoPresupuesto>>BusProducto"+ex);
	            }	
	            return rs;
	}

	
	public java.sql.ResultSet BusGrupo(String codgru){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	        //Busca los datos del grupo
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT * FROM ord_grupo where cod_cuentafk!=0 and codigo='"+codgru+"' ");
	        	System.out.println("SELECT * FROM ord_grupo where cod_cuentafk!=0 and codigo='"+codgru+"' ");
	        		        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoPresupuesto>>BusGrupo"+ex);
	            }	
	            return rs;
	}
	
	
	public java.sql.ResultSet BusProdxGrupo(String codgru){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	        //Busca los datos del grupo
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT p.*, opg.codigo FROM ord_produc_grupo opg, ord_producto  p WHERE opg.cod_grupofk='"+codgru+"' AND p.codigo=opg.cod_productofk  ");
	        		        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoPresupuesto>>BusProdxGrupo"+ex);
	            }	
	            return rs;
	}
	
	
	public void InsertPreDetInventario(String cant,String valor,String nmes,String codpre,String codprod,Date Fecha,Time Hora,String coddetpre,String codprogru){
		//System.out.print(cod_medico);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();													
			    ps=con.conn.prepareStatement("insert into pre_det_preinventario(cod_presupuestofk,cod_productofk,mes,cant,valor,cod_progrufk,fecha,hora,cod_predetpre)values(?,?,?,?,?,?,?,?,?) ");
			    ps.setString(1,codpre);
			    ps.setString(2,codprod);
			    ps.setString(3,nmes);
			    ps.setString(4,cant);
			    ps.setString(5,valor);
			    ps.setString(6,codprogru);
			    ps.setDate(7,Fecha);
			    ps.setTime(8,Hora);
			    ps.setString(9,coddetpre);
			    ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoPresupuesto>>InsertPreDetInventario "+ex);
			}
		}
	
	
}
