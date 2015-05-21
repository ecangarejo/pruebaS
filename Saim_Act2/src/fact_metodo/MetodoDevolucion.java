package fact_metodo;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Time;

import adm_logica.Conexion;

public class MetodoDevolucion {

	
	public java.sql.ResultSet reportedev(String cons){	
		/**
		 */
	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	
        	rs=st.executeQuery(" SELECT DISTINCT CONCAT('DEV',ffd.consDev)consecutivo, fn.consecutivo, fef.valor, fef.razon_social, fef.nombre_paciente,ffd.motivo, CONCAT(sdt.nombre,' ',sdt.apellido) usuario, ffd.fecha "+ 
        			"FROM fact_factdev ffd, fact_movfacturas ffm, fact_numeradas fn, seg_usuario su, fact_facturas_dev ffdev , fact_enc_factura fef, seg_datos_personales sdt "+
        			"WHERE ffd.consDev=ffdev.consdev AND ffdev.codFact=ffm.codFactNumerada AND fn.cod_fact_numerada=ffm.codFactNumerada AND fef.cod_enc_factura=fn.cod_enc_fact_fk "+
        			"AND ffd.cod_usuario=su.usu_codigo AND sdt.dat_codigo=su.dat_codigo_fk AND ffd.consDev= '"+cons+"'");
        	
        	
          }
        catch(Exception ex){
        	System.out.println("Error en MetodoDevolucion>>reportedev "+ex);
        }	
        return rs;
    }


public java.sql.ResultSet GeneraSQLCtaFactDev(String sql, String ri){	
		
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(ri.equals("1")){
        	rs=st.executeQuery(" SELECT DISTINCT CONCAT('DEV0000',ffd.consDev)consecutivo,ffd.valor,ffd.fecha,ae.nombre_entidad,CONCAT(sdt.nombre,' ',sdt.apellido) usuario,ftd.descripcion, ffd.consDev "+  
        						"FROM fact_factdev ffd, seg_usuario su, seg_datos_personales sdt, fact_numeradas fn, fact_tipodevoluciones ftd, "+
        						"fact_facturas_devueltas ffdev,fact_enc_factura fef, adm_admisiones ad, adm_paciente ap, adm_entidad ae "+
        						" WHERE ffd.cod_usuario=su.usu_codigo AND su.dat_codigo_fk=sdt.dat_codigo AND ffd.consDev=ffdev.consdev "+
        						" AND ffdev.codFact=fn.cod_fact_numerada AND fn.cod_enc_fact_fk=fef.cod_enc_factura "+
        						"AND fef.cod_admision=ad.adm_numero_ingreso AND ad.pac_codigo_paciente_fk=ap.pac_codigo_paciente "+
        					     "AND fef.cod_eps=ae.ent_nit AND ftd.codigo=ffd.motivo "+sql);
        	}
        	
          }
        catch(Exception ex){
        	System.out.println("Error en MetodoDevolucion>>GeneraSQLCtaFactDev "+ex);
        }	
        return rs;
    }



	public java.sql.ResultSet FactEst168(String sql){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT fn.cod_fact_numerada,fn.consecutivo,CONCAT(p.nombre,' ',p.primer_apellido,' ',p.segundo_apellido) paciente, " +
        			           "CONCAT(p.tipo_documento,' ',p.numero_documento) documento,fef.fecha_ingreso AS 'f.ingreso', IF(fef.fecha_egreso<>NULL,'Sin alta',fef.fecha_egreso) AS 'f.egreso', " +
        			           "fef.valor,ae.nombre_entidad,am.nombre,fn.estadoFact " +
        			           "FROM fact_numeradas fn,fact_enc_factura fef,adm_entidad ae,adm_paciente p, adm_admisiones adm, adm_municipio am " +
        			           "WHERE (fn.estadoFact='8' or fn.estadoFact='6' or fn.estadoFact='1' or fn.estadoFact='2') " +
        			           "AND fn.cod_enc_fact_fk=fef.cod_enc_factura " +
        			           "AND fef.cod_eps=ae.ent_nit " +
        			           "AND fef.cod_admision=adm.adm_numero_ingreso " +
        			           "AND adm.pac_codigo_paciente_fk=p.pac_codigo_paciente " +
        			           "AND p.mun_codigo_fk=am.muni_cod"+sql+" ORDER BY fn.consecutivo ");
        	
        	System.out.println("SELECT fn.cod_fact_numerada,fn.consecutivo,CONCAT(p.nombre,' ',p.primer_apellido,' ',p.segundo_apellido) paciente, " +
			           "CONCAT(p.tipo_documento,' ',p.numero_documento) documento,fef.fecha_ingreso AS 'f.ingreso', IF(fef.fecha_egreso<>NULL,'Sin alta',fef.fecha_egreso) AS 'f.egreso', " +
			           "fef.valor,ae.nombre_entidad,am.nombre,fn.estadoFact " +
			           "FROM fact_numeradas fn,fact_enc_factura fef,adm_entidad ae,adm_paciente p, adm_admisiones adm, adm_municipio am " +
			           "WHERE (fn.estadoFact='8' or fn.estadoFact='6' or fn.estadoFact='1' or fn.estadoFact='2') " +
			           "AND fn.cod_enc_fact_fk=fef.cod_enc_factura " +
			           "AND fef.cod_eps=ae.ent_nit " +
			           "AND fef.cod_admision=adm.adm_numero_ingreso " +
			           "AND adm.pac_codigo_paciente_fk=p.pac_codigo_paciente " +
			           "AND p.mun_codigo_fk=am.muni_cod"+sql+" ORDER BY fn.consecutivo ");
          }
        catch(Exception ex){
        	System.out.println("Error en MetodoDevolucion>>FactEst168 "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet Fact2Est168(String sql){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT fn.cod_fact_numerada,fn.consecutivo,CONCAT(p.nombre,' ',p.primer_apellido,' ',p.segundo_apellido) paciente, " +
        			           "CONCAT(p.tipo_documento,' ',p.numero_documento) documento,fef.fecha_ingreso AS 'f.ingreso', IF(fef.fecha_egreso<>NULL,'Sin alta',fef.fecha_egreso) AS 'f.egreso', " +
        			           "fef.valor,ae.nombre_entidad,am.nombre,fn.estadoFact " +
        			           "FROM fact_numeradas fn,fact_enc_factura fef,adm_entidad ae,adm_paciente p, adm_admisiones adm, adm_municipio am, fact_facturas_enviadas ffe " +
        			           "WHERE (fn.estadoFact='8' or fn.estadoFact='6' or fn.estadoFact='1' or fn.estadoFact='2') " +
        			           "AND fn.cod_enc_fact_fk=fef.cod_enc_factura " +
        			           "AND fef.cod_eps=ae.ent_nit " +
        			           "AND fef.cod_admision=adm.adm_numero_ingreso " +
        			           "AND adm.pac_codigo_paciente_fk=p.pac_codigo_paciente " +
        			           "AND p.mun_codigo_fk=am.muni_cod  AND ffe.codFact=fn.cod_fact_numerada"+sql+" ORDER BY fn.consecutivo ");
        	
        	System.out.println("SELECT fn.cod_fact_numerada,fn.consecutivo,CONCAT(p.nombre,' ',p.primer_apellido,' ',p.segundo_apellido) paciente, " +
			           "CONCAT(p.tipo_documento,' ',p.numero_documento) documento,fef.fecha_ingreso AS 'f.ingreso', IF(fef.fecha_egreso<>NULL,'Sin alta',fef.fecha_egreso) AS 'f.egreso', " +
			           "fef.valor,ae.nombre_entidad,am.nombre,fn.estadoFact " +
			           "FROM fact_numeradas fn,fact_enc_factura fef,adm_entidad ae,adm_paciente p, adm_admisiones adm, adm_municipio am, fact_facturas_enviadas ffe " +
			           "WHERE (fn.estadoFact='8' or fn.estadoFact='6' or fn.estadoFact='1' or fn.estadoFact='2') " +
			           "AND fn.cod_enc_fact_fk=fef.cod_enc_factura " +
			           "AND fef.cod_eps=ae.ent_nit " +
			           "AND fef.cod_admision=adm.adm_numero_ingreso " +
			           "AND adm.pac_codigo_paciente_fk=p.pac_codigo_paciente " +
			           "AND p.mun_codigo_fk=am.muni_cod  AND ffe.codFact=fn.cod_fact_numerada"+sql);
          }
        catch(Exception ex){
        	System.out.println("Error en MetodoDevolucion>>Fact2Est168 "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarNotasCredito(String  codfact, String est){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(est.equals("1")){
        		//enviada
        		rs=st.executeQuery("SELECT valornc from fact_facturas_enviadas where codFact='"+codfact+"' ");
        		
        	}else{
        		if(est.equals("2")){
        			//radicada
        			rs=st.executeQuery("SELECT valornc from fact_facturas_radicadas where codFact='"+codfact+"' ");
        		}else{
        			if(est.equals("8")){
        				//radicacion interna
        				rs=st.executeQuery("SELECT valornc from fact_facturas_radicadas where codFact='"+codfact+"' ");
        			}else{
        				if(est.equals("6")){
        					//auditoia interna
        					rs=st.executeQuery("SELECT valornc from fact_facturas_auditadas where codFact='"+codfact+"' ");
        					
        				}
        			}
        		}
        	}
        	
		

 }
        catch(Exception ex){
        	System.out.println("Error en MetodoDevolucion>>BuscarNotasCredito "+ex);
        }	
        return rs;
    }



public java.sql.ResultSet BuscarNotasRc(String consecutivo){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT SUM(cdf.cantidad) FROM cont_detalle_factura cdf, cont_factura cf WHERE cdf.cod_fact_fk=cf.codigo AND cf.numero_factura='"+consecutivo+"' ");
    		System.out.println("SELECT SUM(cdf.cantidad) FROM cont_detalle_factura cdf, cont_factura cf WHERE cdf.cod_fact_fk=cf.codigo AND cf.numero_factura='"+consecutivo+"' ");         

  }
        catch(Exception ex){
        	System.out.println("Error en MetodoDevolucion>>BuscarNotasRc "+ex);
        }	
        return rs;
    }


	
	public java.sql.ResultSet Empresas(){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT ent_nit, nombre_entidad FROM adm_entidad ORDER BY nombre_entidad ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoDevolucion>>Empresas "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet TipoDoc(){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT codigo, sigla FROM adm_tipodocumento ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoDevolucion>>Empresas "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ConsultarCodigoDevolucion(String ValorFactura,String ValorLetra,String txtCodusuario,String fecha,String Motivo){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();        	
        	rs=st.executeQuery("SELECT * FROM fact_factdev WHERE valor='"+ValorFactura+"' AND valorLetra='"+ValorLetra+"' AND cod_usuario='"+txtCodusuario+"' AND fecha='"+fecha+"' AND motivo='"+Motivo+"' ORDER BY consDev DESC LIMIT 1");
        	        	   
          }
        catch(Exception ex){
        	System.out.println("Error en MetodoDevolucion>>ConsultarCodigoDevolucion "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet obtenerConsecutivoCR(String ri){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(ri.equals("0")){	
        		rs=st.executeQuery("SELECT consRadicado FROM fact_factradicadas ORDER BY consRadicado DESC LIMIT 1");
        	}
        	if(ri.equals("1")){	
        		rs=st.executeQuery("SELECT consRadicado FROM fact_factradicadasi ORDER BY consRadicado DESC LIMIT 1");
            }        	
        	if(ri.equals("2")){	
        		rs=st.executeQuery("SELECT consDev FROM fact_factdev ORDER BY consDev DESC LIMIT 1");
        	}        	   
          }
        catch(Exception ex){
        	System.out.println("Error en MetodoDevolucion>>obtenerConsecutivoCR "+ex);
        }	
        return rs;
    }
	
	
	public void asignarCDevFact(String consCC,String consFact,String Fecha,String valornc){
		try{
			PreparedStatement ps = null;
		    Conexion con=new Conexion();
		    															
		    ps=con.conn.prepareStatement("INSERT INTO fact_facturas_devueltas(consDev,codFact,fechadev,valornc) VALUES (?,?,?,?)");
		    ps.setString(1,consCC);
		    ps.setString(2,consFact);		
		    ps.setString(3, Fecha);
		    ps.setString(4, valornc);
		    
		 	ps.executeUpdate();
			ps.close();
			con.cerrar();				
		}catch(Exception ex){
        	System.out.println("ERROR EN. MetodoDevolucion>>asignarCDevFact "+ex);
		}
	}
	
	public java.sql.ResultSet BuscarDetalleFacturaEnvio(String CodNumerada){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT ffr.consEnvio,(ff.valor - (fef.valor+ffr.valornc)) AS NuevoValorEnvio,fef.valor as ValorFactura,ffr.valornc  FROM fact_facturas_enviadas ffr,fact_numeradas fn,fact_enc_factura fef,fact_factenviadas ff WHERE ffr.codFact=fn.cod_fact_numerada AND fn.cod_enc_fact_fk=fef.cod_enc_factura AND ff.consEnvio=ffr.consEnvio AND ffr.codFact='"+CodNumerada+"' ");
        	
          }
        catch(Exception ex){
        	System.out.println("Error en MetodoDevolucion>>BuscarDetalleFacturaEnvio "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarDetalleFacturaradicada(String CodNumerada){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT ffr.consRadicado,(ff.valor - (fef.valor+ffr.valornc)) AS NuevoValorRadicacion FROM fact_facturas_radicadas ffr,fact_numeradas fn,fact_enc_factura fef,fact_factradicadas ff WHERE ffr.codFact=fn.cod_fact_numerada AND fn.cod_enc_fact_fk=fef.cod_enc_factura AND ff.consRadicado=ffr.consRadicado AND ffr.codFact='"+CodNumerada+"' ");
        	
          }
        catch(Exception ex){
        	System.out.println("Error en MetodoDevolucion>>BuscarDetalleFacturaradicada "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarCodigoNumerada(String NumeroFactura){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM fact_numeradas where consecutivo='"+NumeroFactura+"' ");
        	
          }
        catch(Exception ex){
        	System.out.println("Error en MetodoDevolucion>>BuscarCodigoNumerada "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarMotivo(){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM fact_tipodevoluciones order by descripcion");
        	
          }
        catch(Exception ex){
        	System.out.println("Error en MetodoDevolucion>>BuscarMotivo "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet BuscarEstadoFact(String Codfact){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT estadoFact from fact_numeradas where cod_fact_numerada='"+Codfact+"' ");
        	
          }
        catch(Exception ex){
        	System.out.println("Error en MetodoDevolucion>>BuscarEstadoFact "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet BuscarCtaE(String Codfact){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT fn.cod_fact_numerada, ffe.consEnvio, fn.consecutivo, ffe.codigo FROM fact_facturas_enviadas ffe, fact_numeradas fn WHERE fn.cod_fact_numerada=ffe.codFact AND ffe.codFact='"+Codfact+"' ");
        	
          }
        catch(Exception ex){
        	System.out.println("Error en MetodoDevolucion>>BuscarCtaE "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarCtaA(String Codfact){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT fn.cod_fact_numerada, fa.consAudita, fn.consecutivo, fa.codigo FROM fact_facturas_auditadas fa, fact_numeradas fn WHERE fa.codFact='"+Codfact+"' AND fn.cod_fact_numerada=fa.codFact ");
        	
          }
        catch(Exception ex){
        	System.out.println("Error en MetodoDevolucion>>BuscarCtaA "+ex);
        }	
        return rs;
    }
	
	
	public void registroEliminacion(String codFact,String consec,String usuario,Date Fecha, Time Hora,String tabla){
		try{
			PreparedStatement ps = null;
		    Conexion con=new Conexion();
		 														
			ps=con.conn.prepareStatement("INSERT INTO fact_regeliminacion(codFact,consec,Fecha,Hora,usuario,tabla) VALUES(?,?,?,?,?,?)");
			//System.out.println("Valor de Motivo en insercion de factdev "+motivo);
			    ps.setString(1,codFact);
			    ps.setString(2,consec);
			    ps.setDate(3,Fecha);
			    ps.setTime(4,Hora);
			    ps.setString(5,usuario);
			    ps.setString(6,tabla);
			 	ps.executeUpdate();
				ps.close();
		   
			con.cerrar();				
		}catch(Exception ex){
        	System.out.println("ERROR EN. MetodoDevolucion>>registroEliminacion"+ex);
		}
	}
	
	public void actualizarEstFactR(String codFactNum,String ea){	
		 
		 PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	if(ea.equals("0")){				
	     	st= con.conn.prepareStatement("UPDATE fact_numeradas SET estadoFact='2' WHERE cod_fact_numerada='"+codFactNum+"'");
	     	}
	     	if(ea.equals("1")){				
		    st= con.conn.prepareStatement("UPDATE fact_numeradas SET estadoFact='6' WHERE cod_fact_numerada='"+codFactNum+"'");
		    //System.out.print("UPDATE fact_numeradas SET estadoFact='6' WHERE cod_fact_numerada='"+codFactNum+"'");
	     	}
	     	if(ea.equals("2")){				
		     	st= con.conn.prepareStatement("UPDATE fact_numeradas SET estadoFact='8' WHERE cod_fact_numerada='"+codFactNum+"'");
		     }
	     	if(ea.equals("3")){				
		     	st= con.conn.prepareStatement("UPDATE fact_numeradas SET estadoFact='0' WHERE cod_fact_numerada='"+codFactNum+"'");
		     	//System.out.println("UPDATE fact_numeradas SET estadoFact='0' WHERE cod_fact_numerada='"+codFactNum+"'");
		     }

	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoDevolucion>>actualizarEstFact "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public void CrearDevolucion(String valor,String valorLetra,String cod_usuario,String fecha, String motivo){
		System.out.print("cod_usuario "+cod_usuario);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
				ps=con.conn.prepareStatement("INSERT INTO fact_factdev(valor,valorLetra,cod_usuario,fecha,motivo) VALUES(?,?,?,?,?)");
			    ps.setString(1,valor);
			    ps.setString(2,valorLetra);
			    ps.setString(3,cod_usuario);
			    ps.setString(4,fecha);
			    ps.setString(5,motivo);
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN. MetodoDevolucion>>CrearDevolucion "+ex);
			}
		}
	
	public void asignarCuentaDev(String valor,String valorLetra,String cod_usuario,String fecha, String motivo, String ri){
		//System.out.print(cod_medico);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    if(ri.equals("1")){															
				ps=con.conn.prepareStatement("INSERT INTO fact_factdev(valor,valorLetra,cod_usuario,fecha,motivo) VALUES(?,?,?,?,?)");
				System.out.println("Valor de Motivo en insercion de factdev "+motivo);
			    ps.setString(1,valor);
			    ps.setString(2,valorLetra);
			    ps.setString(3,cod_usuario);
			    ps.setString(4,fecha);
			    ps.setString(5,motivo);
			 	ps.executeUpdate();
				ps.close();
			    }
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN. MetodoDevolucion>>asignarCuentaDev "+ex);
			}
		}
	
	 public void recordEstadoFactDEV(String codFact,String estado,String fecha,String usuario,String motivo, String consecutivo){
		try{
			PreparedStatement ps = null;
		    Conexion con=new Conexion();
		    															
		    ps=con.conn.prepareStatement("INSERT INTO fact_movfacturas(codFactNumerada,estadoFact,fecha,usuario,Observacion,cod_Devfk) VALUES (?,?,?,?,?,?)");
		    System.out.print("ya se inserttaron los mov");
		    ps.setString(1,codFact);
		    ps.setString(2,estado);		
		    ps.setString(3,fecha);	
		    ps.setString(4,usuario);
		    ps.setString(5,motivo);
		    ps.setString(6,consecutivo);
		 	ps.executeUpdate();
			ps.close();
			con.cerrar();		
			System.out.println("Ingresando datos a fact_movfacturas");
		}catch(Exception ex){
        	System.out.println("ERROR EN. MetodoDevolucion>>recordEstadoFactDev "+ex);
		}
	}
	
	public void ActualizarDatosCtaEnvio(String CodEnvio,String ValorNuevoCtaEnvio,String ValorLetrasCtaEnvio){
		PreparedStatement st = null;
	    try{
	    	Conexion con=new Conexion();
	    	st= con.conn.prepareStatement("UPDATE fact_factenviadas SET valor='"+ValorNuevoCtaEnvio+"',valorLetra='"+ValorLetrasCtaEnvio+"' WHERE consEnvio='"+CodEnvio+"' ");
	    	st.executeUpdate();
	    	st.close();
	    	con.cerrar();
	    	
	    }
	    catch(Exception ex){
	    	System.out.println("ERROR EN MetodoDevolucion>>ActualizarDatosCtaEnvio "+ex);
	    
	    }	
	}
	
	public void ActualizarEstadoFactura(String CodNumerada){
		PreparedStatement st = null;
	    try{
	    	Conexion con=new Conexion();
	    	st= con.conn.prepareStatement("UPDATE fact_numeradas SET estadoFact='0' WHERE cod_fact_numerada='"+CodNumerada+"' ");
	    	st.executeUpdate();
	    	st.close();
	    	con.cerrar();
	    	
	    }
	    catch(Exception ex){
	    	System.out.println("ERROR EN MetodoDevolucion>>ActualizarEstadoFactura "+ex);
	    
	    }	
	}
	
	public void ActualizarDatosCtaRadicacion(String CodRadicacion,String ValorNuevoCtaRadicacion,String ValorLetrasCtaRadicacion){
		PreparedStatement st = null;
	    try{
	    	Conexion con=new Conexion();
	    	st= con.conn.prepareStatement("UPDATE fact_factradicadas SET valor='"+ValorNuevoCtaRadicacion+"',valorLetra='"+ValorLetrasCtaRadicacion+"' WHERE consRadicado='"+CodRadicacion+"' ");
	    	st.executeUpdate();
	    	st.close();
	    	con.cerrar();
	    	
	    }
	    catch(Exception ex){
	    	System.out.println("ERROR EN MetodoDevolucion>>ActualizarDatosCtaRadicacion "+ex);
	    
	    }	
	}
	
	public void EliminarFacturaCuentaEnvio(String CodNumerada){
		PreparedStatement st = null;
	    try{
	    	Conexion con=new Conexion();
	    	st= con.conn.prepareStatement("delete from fact_facturas_enviadas WHERE codFact='"+CodNumerada+"' ");
	    	st.executeUpdate();
	    	st.close();
	    	con.cerrar();
	    	
	    }
	    catch(Exception ex){
	    	System.out.println("ERROR EN MetodoDevolucion>>EliminarFacturaCuentaEnvio "+ex);
	    }	
	}
	
	public void EliminarFacturaCuentaRadicacion(String CodNumerada){
		PreparedStatement st = null;
	    try{
	    	Conexion con=new Conexion();
	    	st= con.conn.prepareStatement("delete from fact_facturas_radicadas WHERE codFact='"+CodNumerada+"' ");
	    	st.executeUpdate();
	    	st.close();
	    	con.cerrar();
	    	
	    }
	    catch(Exception ex){
	    	System.out.println("ERROR EN MetodoDevolucion>>EliminarFacturaCuentaRadicacion "+ex);
	    }	
	}
	
	public void EliminarRegCta(String codFact,String consec, String tipo){
		PreparedStatement st = null;
	    try{
	    	Conexion con=new Conexion();
	    	if(tipo.equals("1")){
	    	st= con.conn.prepareStatement(" DELETE FROM fact_facturas_enviadas WHERE consEnvio='"+consec+"' and codFact='"+codFact+"' ");
	    	}else{
	    		if(tipo.equals("2")){
	    			st= con.conn.prepareStatement(" DELETE FROM fact_facturas_auditadas WHERE consAudita='"+consec+"' and codFact='"+codFact+"' ");
	    		}else{
	    			st= con.conn.prepareStatement(" DELETE FROM fact_facturas_radicadasi WHERE consRadicado='"+consec+"' and codFact='"+codFact+"' ");
	    		}
	    	}
	    	st.executeUpdate();
	    	st.close();
	    	con.cerrar();
	    	
	    }
	    catch(Exception ex){
	    	System.out.println("ERROR EN MetodoDevolucion>>EliminarRegCta "+ex);
	    
	    }	
	}
	
	public java.sql.ResultSet BuscarValoresCtaCobro(String consec, String tipo){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(tipo.equals("1")){
        		rs=st.executeQuery("SELECT fef.valor, ffenv.codFact, fn.consecutivo FROM fact_facturas_enviadas ffenv, fact_numeradas fn, fact_enc_factura fef "+
        				"WHERE  ffenv.codFact=fn.cod_fact_numerada AND fn.cod_enc_fact_fk=fef.cod_enc_factura AND ffenv.consEnvio='"+consec+"' ");
        	}else{
        		if(tipo.equals("2")){
        			rs=st.executeQuery("SELECT  fef.valor , a.codFact,  fn.consecutivo "+
        								"FROM  fact_facturas_auditadas a, fact_numeradas fn,  fact_enc_factura fef "+
        								"WHERE a.codFact = fn.cod_fact_numerada   AND fn.cod_enc_fact_fk = fef.cod_enc_factura  AND a.consAudita='"+consec+"' ");
        		}else{
        			rs=st.executeQuery("SELECT  fef.valor , a.codFact,  fn.consecutivo "+
							"FROM  fact_facturas_radicadasi a, fact_numeradas fn,  fact_enc_factura fef "+
							"WHERE a.codFact = fn.cod_fact_numerada   AND fn.cod_enc_fact_fk = fef.cod_enc_factura  AND a.consRadicado='"+consec+"' ");
        		}
        	}
          }
        catch(Exception ex){
        	System.out.println("Error en MetodoDevolucion>>BuscarValoresCtaCobro "+ex);
        }	
        return rs;
    }
	
	
	public void ActualizarCtaCobro(String consecutivo,long valorfact, String Let,String tipo){	
		 
		 PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	if(tipo.equals("1")){		
	     	st= con.conn.prepareStatement("UPDATE fact_factenviadas SET valorLetra='"+Let+"' , valor='"+valorfact+"' WHERE consEnvio='"+consecutivo+"' ");
	     	//System.out.println("UPDATE fact_factenviadas SET valorLetra='"+Let+"' , valor='"+valorfact+"' WHERE consEnvio='"+consecutivo+"' ");
	     	}else{
	     		if(tipo.equals("2")){
	     			st= con.conn.prepareStatement("UPDATE fact_factauditadas SET valorLetra='"+Let+"' , valor='"+valorfact+"' WHERE consAudita='"+consecutivo+"' ");
	     		}else{
	     			st= con.conn.prepareStatement("UPDATE fact_factradicadasi SET valorLetra='"+Let+"' , valor='"+valorfact+"' WHERE consRadicado='"+consecutivo+"' ");
	     		}
	     	}
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoDevolucion>>ActualizarCtaCobro "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
public String formatMoneda(String valor){
		
		String temp2="";
		String temp1="";
		int ud=1;
		int logCad = valor.length();
		
		for (int i=logCad-1;i>=0;i--){
			
			temp2=valor.substring(i,i+1);

			temp2+=temp1;
			if(ud==3){
				if(i!=0){
					temp1="."+temp2;
				}else{
					temp1=temp2;
				}
				ud=0;
			}else{
				temp1=temp2;
			}
			ud++;
		}
		temp1="$ "+temp1;
		return temp1;
	}
	
	
}
