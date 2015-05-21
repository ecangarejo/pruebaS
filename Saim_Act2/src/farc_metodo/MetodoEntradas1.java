package farc_metodo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import farc_bean.Entradas;

import adm_logica.Conexion;

public class MetodoEntradas1 {

	public ResultSet listarEmbalaje() {
		/**
		 */
		  java.sql.ResultSet r=null;
	      Statement st = null;
	      try{
	       Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	r=st.executeQuery("select codigo, sigla, descripcion  from farc_tipo_embalaje ");
	      }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoEntradas1>>listarEmbalaje "+ex);
	        }
	        	return r;     
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
	
	
	public ResultSet listarEmpaque() {
		/**
		 */
		  java.sql.ResultSet r=null;
	      Statement st = null;
	      try{
	       Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	r=st.executeQuery("select codigo, sigla, descripcion from farc_tipo_empaque ");
	      }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoEntradas1>>listarEmpaque "+ex);
	        }
	        	return r;     
	    }

	
	
	public ResultSet listarEnvase() {
		/**
		 */
		  java.sql.ResultSet r=null;
	      Statement st = null;
	      try{
	       Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	r=st.executeQuery("select codigo, sigla, descripcion from farc_tipo_envase ");
	      }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoEntradas1>>listarEmvase "+ex);
	        }
	        	return r;     
	    }
	
	
	public ResultSet listarEnvasePrimario() {
		/**
		 */
		  java.sql.ResultSet r=null;
	      Statement st = null;
	      try{
	       Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	r=st.executeQuery("select codigo, descripcion from farc_env_primario where estado='0' ");
	      }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoEntradas1>>listarEnvasePrimario "+ex);
	        }
	        	return r;     
	    }
	
	

	public ResultSet listarLaboratorios() {
		/**
		 */
		  java.sql.ResultSet r=null;
	      Statement st = null;
	      try{
	       Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	r=st.executeQuery("select * from farc_laboratorio ");
	      }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoEntradas1>>listarLaboratorios "+ex);
	        }
	        	return r;     
	    }
	
	public java.sql.ResultSet ObtenerBodegas(String user1){	
		/**
		 * consulta que obtiene las unidades del modulo de farmacia, 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT b.codigo, b.nombre FROM seg_bodegas_autorizadas s, farc_bodegas b WHERE s.cod_usuariofk='"+user1+"' and s.cod_bodegafk=b.codigo");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoEntradas1>>ObtenerBodegas "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerProveedor(){	
		/**
		 * consulta que obtiene las unidades del modulo de farmacia, 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo, razon_social from farc_proveedor");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoEntradas1>>ObtenerProveedor "+ex);
        }	
        return rs;
    }
	
	public ResultSet listarMovimientose() {
		/**
		 */
		  java.sql.ResultSet r=null;
	      Statement st = null;
	      try{
	       Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	r=st.executeQuery("select codigo,descripcion from farc_tipomovimiento where cod_movFk='1'");
	      }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoEntradas1>>ObtenerBodegas "+ex);
	        }
	        	return r;     
	    }
	

	
	public java.sql.ResultSet listarIvas(String texto){	
	java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select codigo, valor, codigo from farc_iva where valor like '"+texto+"%'");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoEnntradas1>>listarIvas "+ex);
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
		
	
		
		public java.sql.ResultSet listarArticulos(String texto){	
			java.sql.ResultSet rs=null;
			Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery(" select m.codigo,m.nombre,m.concentracion,u.sigla,f.descripcion,m.descripcion, m.cod_grupoFK from medicamentos m, farc_unidades u, farc_formafarmaceutica f where m.cod_unidadFK=u.codigo and m.cod_formaFK=f.codigo and m.estado=1 and nombre like '%"+texto+"%'");
	        	//rs=st.executeQuery(" SELECT m.codigo, m.nombre, CONCAT( m.concentracion, u.sigla,f.descripcion, m.descripcion) AS descripcion, m.cod_grupoFK  from medicamentos m, farc_unidades u, farc_formafarmaceutica f where m.cod_unidadFK=u.codigo and m.cod_formaFK=f.codigo and m.estado=1 and nombre like '%"+texto+"%'");
	        	System.out.println(" select m.codigo,m.nombre,m.concentracion,u.sigla,f.descripcion,m.descripcion, m.cod_grupoFK from medicamentos m, farc_unidades u, farc_formafarmaceutica f where m.cod_unidadFK=u.codigo and m.cod_formaFK=f.codigo and m.estado=1 and nombre like '%"+texto+"%'");
	        	
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoEntradas11>>listarArticulos"+ex);
	        }	
	        return rs;
	    }
		
		
		public java.sql.ResultSet ObtenerConsecutivo(String c){	
			
		   java.sql.ResultSet rs=null;
		   Statement st = null;
		   try{
		   	Conexion con=new Conexion();
		   	st = con.conn.createStatement();
		   	rs=st.executeQuery("SELECT sigla,consec FROM farc_consecutivos WHERE codigo='"+c+"'  ");
		   }
		   catch(Exception ex){
		   	System.out.println("Error en MetodoEntradas>>ObtenerMovimiento "+ex);
		   }	
		   return rs;
		}
		
		public void ActualizaConsecutivo(String c, String n){

			PreparedStatement st = null;
		    try{
		    	Conexion con=new Conexion();
		    	st= con.conn.prepareStatement("update farc_consecutivos set consec='"+n+"' where codigo='"+c+"'");
		    	st.executeUpdate();
		    	st.close();
		    	con.cerrar();
		    	
		    }
		    catch(Exception ex){
		    	System.out.println("ERROR EN MetodoSalidas>>Actualizaid "+ex);
		    
		    }	
		}
		
		public java.sql.ResultSet ObtenerUMovimiento(String fec, String hra){	
			
			//consulta que obtiene el codigo de la tabla paisesa, 

		   java.sql.ResultSet rs=null;
		   Statement st = null;
		   try{
		   	Conexion con=new Conexion();
		   	st = con.conn.createStatement();
		   	rs=st.executeQuery("select codigo from farc_movimientos where fecha='"+fec+"' and hora='"+hra+"' ");
		   }
		   catch(Exception ex){
		   	System.out.println("Error en MetodoEntradas>>ObtenerMovimiento "+ex);
		   }	
		   return rs;
		}
		
		public void CrearMovimientos(String cantidad, String movimiento, String fecha,  String hra, String user, String factura, String consec){
		
			
			// creamos los ingresos de movimientos
			
			Entradas ci = new Entradas();
			
			//ci.setbodega(bodega);
			ci.setcantidad(cantidad);
			ci.setmovimiento(movimiento);
			ci.setfecha(fecha);
			ci.setfactura(factura);
			//ci.setconcepto(concepto);
			
			
			try{
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    
				    ps=con.conn.prepareStatement("insert into farc_movimientos(cantidad,tipo_mvtoFk,fecha,hora,cod_usuarioFk,nsoporte,consec)values(?,?,?,?,?,?,?)");
				    
				    //ps.setString(1,ci.getbodega());
				    ps.setString(1,ci.getcantidad());
				    ps.setString(2,ci.getmovimiento());
				    ps.setString(3,ci.getfecha());
				    ps.setString(4,hra);//hora
				    ps.setString(5,user);//cod_usuario
				    ps.setString(6,ci.getfactura());
				    //ps.setString(7,ci.getconcepto());
				    ps.setString(7,consec);//hora
				    ps.executeUpdate();
					ps.close();
					con.cerrar();				
				}catch(Exception ex){
		        	System.out.println("ERROR EN MetodoEntradas1>>CrearMovimiento "+ex);
				}

			}

		public void CrearEntradas(String movimiento,String articulo,String vence, String lote, String cantidad, String vunitario, String vtotal, String iva, String bodega, String fecha, String hra, String proveedor,String invima){
			
			// creamos los ingresos de INVENTARIO
			
			Entradas ci = new Entradas();
			
			ci.setarticulo(articulo);
			ci.setvence(vence);
			ci.setlote(lote);
			ci.setcantidad(cantidad);
			ci.setvunitario(vunitario);
			ci.setvtotal(vtotal);
			ci.setiva(iva);
			ci.setbodega(bodega);
			ci.setfecha(fecha);
			ci.setproveedor(proveedor);
			ci.setinvima(invima);
			
			try{
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("insert into farc_inventario(cod_movFk,cod_medFk,vencimiento,lote,cantidad,vunitario,vtotal,cod_ivaFk,cod_bodegaFk,fecha,hora,cod_proveedorFk,invima,cantinicial)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				    ps.setString(1,movimiento);
				    ps.setString(2,ci.getarticulo());
				    ps.setString(3,ci.getvence());
				    ps.setString(4,ci.getlote());
				    ps.setString(5,ci.getcantidad());
				    ps.setString(6,ci.getvunitario());
				    ps.setString(7,ci.getvtotal());
				    ps.setString(8,ci.getiva());
				    ps.setString(9,ci.getbodega());
				    ps.setString(10,ci.getfecha());
				    ps.setString(11,hra);//hora
				    ps.setString(12,ci.getproveedor());
				    ps.setString(13,ci.getinvima());
				    ps.setString(14,ci.getcantidad());
				    ps.executeUpdate();
					ps.close();
					con.cerrar();			
				}catch(Exception ex){
		        	System.out.println("ERROR EN MetodoEntradas>>CrearEntradas "+ex);
				}

			}

		public java.sql.ResultSet ObtenerUEntradas(String fec, String hras){	
			
			//consulta que obtiene el codigo de la tabla paisesa, 

		   java.sql.ResultSet rs=null;
		   Statement st = null;
		   try{
		   	Conexion con=new Conexion();
		   	st = con.conn.createStatement();
		   	rs=st.executeQuery("select codigo from farc_inventario where fecha='"+fec+"' and hora='"+hras+"' ");
		   }
		   catch(Exception ex){
		   	System.out.println("Error en MetodoCrearProveedor>>ObtenerMovimiento "+ex);
		   }	
		   return rs;
		}

		

public void CrearActa(String i,String o,String f, String cs, String cr, String cp, String temb, String temp, String tenv, String t, String envp, String r, String e, String tm, String pm, String cm, String res, String cte, String ver, String dona, String fec, String hras, String cl, String cum ){
	
	// creamos los ingresos al ACTA de RECEPCION

	
	try{
			PreparedStatement ps = null;
		    Conexion con=new Conexion();
		    ps=con.conn.prepareStatement("INSERT INTO farc_act_recep(cod_invfk,numero_orden,numero_factura,cantidad_solicitada,cantidad_recibida,cantidad_pendiente,cod_tipo_embalajefk,cod_tipo_empaquefk,cod_tipo_envasefk,temperatura,cod_env_primariofk,recibe,entrega,tipo_muestreo,proc_muestreo,cant_muestreo,resultado,cump_timp_entr,verifico,donado,fecha,hora,cod_laboratoriofk,cum)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		    ps.setString(1,i);
		    ps.setString(2,o);
		    ps.setString(3,f);
		    ps.setString(4,cs);
		    ps.setString(5,cr);
		    ps.setString(6,cp);
		    ps.setString(7,temb);
		    ps.setString(8,temp);
		    ps.setString(9,tenv);
		    ps.setString(10,t);
		    ps.setString(11,envp);
		    ps.setString(12,r);
		    ps.setString(13,e);
		    ps.setString(14,tm);
		    ps.setString(15,pm);
		    ps.setString(16,cm);
		    ps.setString(17,res);
		    ps.setString(18,cte);
		    ps.setString(19,ver);
		    ps.setString(20,dona);
		    ps.setString(21,fec);
		    ps.setString(22,hras);
		    ps.setString(23,cl);
		    ps.setString(24,cum);
		    ps.executeUpdate();
			ps.close();
			con.cerrar();				
		}catch(Exception ex){
        	System.out.println("ERROR EN MetodoEntradas1>>CrearActa "+ex);
		}

	}

public void CrearDetalle(String movi,String inv, String bod, String ca){
	
	// creamos los ingresos de movimientos
	
	try{
			PreparedStatement ps = null;
		    Conexion con=new Conexion();
		    ps=con.conn.prepareStatement("insert into farc_detallemov(cod_movFk,cod_invFk, cod_bodegai, cantidad)values(?,?,?,?)");
		    ps.setString(1,movi);
		    ps.setString(2,inv);
		    ps.setString(3,bod);
		    ps.setString(4,ca);
		    ps.executeUpdate();
			ps.close();
			con.cerrar();				
		}catch(Exception ex){
        	System.out.println("ERROR EN MetodoEntradas1>>CrearDetalle "+ex);
		}

	}

		

		
		


		
		
}//Fin MetodoDocumentos

