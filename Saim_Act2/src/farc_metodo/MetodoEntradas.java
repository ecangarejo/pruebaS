package farc_metodo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import adm_logica.Conexion;
import farc_bean.Entradas;


public class MetodoEntradas {

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
	        	System.out.println("Error en MetodoEntradas>>ObtenerBodegas "+ex);
	        }
	        	return r;     
	    }
	
	public ResultSet listarArticulos(String texto) throws Exception {
		/**
		 */
		  java.sql.ResultSet r=null;
	        Statement st = null;
	       Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	r=st.executeQuery("select m.codigo,m.nombre,m.concentracion,u.sigla,f.descripcion,m.descripcion, m.cod_grupoFK from medicamentos m, farc_unidades u, farc_formafarmaceutica f where m.cod_unidadFK=u.codigo and m.cod_formaFK=f.codigo and m.estado=1 and nombre like '%"+texto+"%' ");
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
	        	System.out.println("Error en MetodoEntradas>>listarLaboratorios "+ex);
	        }
	        	return r;     
	    }

	
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
	        	System.out.println("Error en MetodoEntradas>>listarEmbalaje "+ex);
	        }
	        	return r;     
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
	        	System.out.println("Error en MetodoEntradas>>listarEmpaque "+ex);
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
	        	System.out.println("Error en MetodoEntradas>>listarEmvase "+ex);
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
	        	System.out.println("Error en MetodoEntradas>>listarEnvasePrimario "+ex);
	        }
	        	return r;     
	    }

	public ResultSet listarEmpaqueSecundario() {
		/**
		 */
		  java.sql.ResultSet r=null;
	      Statement st = null;
	      try{
	       Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	r=st.executeQuery("select codigo, descripcion from farc_emp_secundario where estado='0' ");
	      }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoEntradas>>listarEmpaqueSecundario "+ex);
	        }
	        	return r;     
	    }

	public ResultSet listarLaboratorios(String c) {
		/**
		 */
		  java.sql.ResultSet r=null;
	      Statement st = null;
	      try{
	       Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	r=st.executeQuery("select * from farc_laboratorio where codigo='"+c+"' ");
	      }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoEntradas>>listarLaboratorios "+ex);
	        }
	        	return r;     
	    }

	public ResultSet listarLaboratoriosno(String c) {
		/**
		 */
		  java.sql.ResultSet r=null;
	      Statement st = null;
	      try{
	       Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	r=st.executeQuery("select * from farc_laboratorio where codigo!='"+c+"' ");
	      }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoEntradas>>listarLaboratorios "+ex);
	        }
	        	return r;     
	    }

	public ResultSet listarEmbalaje(String c) {
		/**
		 */
		  java.sql.ResultSet r=null;
	      Statement st = null;
	      try{
	       Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	r=st.executeQuery("select codigo, sigla, descripcion  from farc_tipo_embalaje  where codigo='"+c+"' ");
	      }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoEntradas>>listarEmbalaje "+ex);
	        }
	        	return r;     
	    }

	public ResultSet listarEmbalajeno(String c) {
		/**
		 */
		  java.sql.ResultSet r=null;
	      Statement st = null;
	      try{
	       Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	r=st.executeQuery("select codigo, sigla, descripcion  from farc_tipo_embalaje  where codigo!='"+c+"'  ");
	      }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoEntradas>>listarEmbalaje "+ex);
	        }
	        	return r;     
	    }

	public ResultSet listarEmpaque(String c) {
		/**
		 */
		  java.sql.ResultSet r=null;
	      Statement st = null;
	      try{
	       Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	r=st.executeQuery("select codigo, sigla, descripcion from farc_tipo_empaque   where codigo='"+c+"'  ");
	      }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoEntradas>>listarEmpaque "+ex);
	        }
	        	return r;     
	    }
	
	public ResultSet listarEmpaqueno(String c) {
		/**
		 */
		  java.sql.ResultSet r=null;
	      Statement st = null;
	      try{
	       Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	r=st.executeQuery("select codigo, sigla, descripcion from farc_tipo_empaque   where codigo!='"+c+"' ");
	      }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoEntradas>>listarEmpaque "+ex);
	        }
	        	return r;     
	    }
	

	public ResultSet listarEnvase(String c) {
		/**
		 */
		  java.sql.ResultSet r=null;
	      Statement st = null;
	      try{
	       Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	r=st.executeQuery("select codigo, sigla, descripcion from farc_tipo_envase   where codigo='"+c+"'  ");
	      }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoEntradas>>listarEmvase "+ex);
	        }
	        	return r;     
	    }
	
	public ResultSet listarEnvaseno(String c) {
		/**
		 */
		  java.sql.ResultSet r=null;
	      Statement st = null;
	      try{
	       Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	r=st.executeQuery("select codigo, sigla, descripcion from farc_tipo_envase   where codigo!='"+c+"'  ");
	      }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoEntradas>>listarEmvase "+ex);
	        }
	        	return r;     
	    }
	

	public ResultSet listarEnvasePrimario(String c) {
		/**
		 */
		  java.sql.ResultSet r=null;
	      Statement st = null;
	      try{
	       Conexion con=new Conexion();
	        	st = con.conn.createStatement(); 
	        	r=st.executeQuery("select codigo, descripcion from farc_env_primario where codigo='"+c+"' and estado='0' ");
	      }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoEntradas>>listarEnvasePrimario "+ex);
	        }
	        	return r;     
	    }
	
	public ResultSet listarEnvasePrimariono(String c) {
		/**
		 */
		  java.sql.ResultSet r=null;
	      Statement st = null;
	      try{
	       Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	r=st.executeQuery("select codigo, descripcion from farc_env_primario where codigo!='"+c+"' and estado='0' ");
	      }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoEntradas>>listarEnvasePrimario "+ex);
	        }
	        	return r;     
	    }

	public ResultSet listarEmpaqueSecundario(String c) {
		/**
		 */
		  java.sql.ResultSet r=null;
	      Statement st = null;
	      try{
	       Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	r=st.executeQuery("select codigo, descripcion from farc_emp_secundario  where codigo='"+c+"' and estado='0' ");
	      }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoEntradas>>listarEmpaqueSecundario "+ex);
	        }
	        	return r;     
	    }
	
	public ResultSet listarEmpaqueSecundariono(String c) {
		/**
		 */
		  java.sql.ResultSet r=null;
	      Statement st = null;
	      try{
	       Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	r=st.executeQuery("select codigo, descripcion from farc_emp_secundario  where codigo!='"+c+"' and estado='0' ");
	      }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoEntradas>>listarEmpaqueSecundario "+ex);
	        }
	        	return r;     
	    }
	
	public ResultSet listarIvas(String texto) throws Exception {
		/**
		 */
		  java.sql.ResultSet r=null;
	        Statement st = null;
	       Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	r=st.executeQuery("select codigo,valor from farc_iva where valor like '"+texto+"%'");
	        return r;
	    }

	public java.sql.ResultSet ObtenerBodegas(String user){	
		/**
		 * consulta que obtiene las unidades del modulo de farmacia, 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT b.codigo, b.nombre FROM seg_bodegas_autorizadas s, farc_bodegas b WHERE s.cod_usuariofk='"+user+"' AND s.cod_bodegafk=b.codigo");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoEntradas>>ObtenerBodegas "+ex);
        }	
        return rs;
    }

	

	public java.sql.ResultSet BuscarVolumenAsignado(String texto){	
	   java.sql.ResultSet rs=null;
	   Statement st = null;
	   try{
	   	Conexion con=new Conexion();
	   	st = con.conn.createStatement();
	   	rs=st.executeQuery("SELECT volumen_final, presentacion_final FROM farc_detalle_orden_produccion WHERE codigo='"+texto+"'  ");
		}
	   catch(Exception ex){
	   	System.out.println("Error en MetodoEntradas>>BuscarVolumenAsignado "+ex);
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
        	System.out.println("Error en MetodoEntradas>>ObtenerProveedor "+ex);
        }	
        return rs;
    }
	
public java.sql.ResultSet ObtenerMovimiento(String x){	
		
		//consulta que obtiene el codigo de la tabla paisesa, 

       java.sql.ResultSet rs=null;
       Statement st = null;
       try{
       	Conexion con=new Conexion();
       	st = con.conn.createStatement();
       	rs=st.executeQuery("select codigo, descripcion from farc_tipomovimiento where descripcion like'"+x+"%'");
       }
       catch(Exception ex){
       	System.out.println("Error en MetodoCrearProveedor>>ObtenerMovimiento "+ex);
       }	
       return rs;
   }

public java.sql.ResultSet ObtenerEntradas(){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 

   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery("select codigo, descripcion from farc_tipomovimiento where cod_movFk='1'");
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoCrearProveedor>>ObtenerMovimiento "+ex);
   }	
   return rs;
}

public java.sql.ResultSet ObtenerArticulo(){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 

   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery("select codigo, nombre, concentracion from medicamentos");
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoCrearProveedor>>ObtenerMovimiento "+ex);
   }	
   return rs;
}

////////////////////////////////

public java.sql.ResultSet ObtenerConsecutivo(String c){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 

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

public void CrearMovimientos(String cantidad, String movimiento, String fecha,  String hra, String user, String factura, String concepto, String consec){
	
	// creamos los ingresos de movimientos
	
	Entradas ci = new Entradas();
	
	//ci.setbodega(bodega);
	ci.setcantidad(cantidad);
	ci.setmovimiento(movimiento);
	ci.setfecha(fecha);
	ci.setfactura(factura);
	ci.setconcepto(concepto);
	
	
	try{
			PreparedStatement ps = null;
		    Conexion con=new Conexion();
		    ps=con.conn.prepareStatement("insert into farc_movimientos(cantidad,tipo_mvtoFk,fecha,hora,cod_usuarioFk,nsoporte,concepto,consec)values(?,?,?,?,?,?,?,?)");
		    //ps.setString(1,ci.getbodega());
		    ps.setString(1,ci.getcantidad());
		    ps.setString(2,ci.getmovimiento());
		    ps.setString(3,ci.getfecha());
		    ps.setString(4,hra);//hora
		    ps.setString(5,user);//cod_usuario
		    ps.setString(6,ci.getfactura());
		    ps.setString(7,ci.getconcepto());
		    ps.setString(8,consec);//hora
		    ps.executeUpdate();
			ps.close();
			con.cerrar();				
		}catch(Exception ex){
        	System.out.println("ERROR EN MetodoEntradas>>CrearEntradas "+ex);
		}

	}
/////////////////////////////

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
        	System.out.println("ERROR EN MetodoEntradas>>CrearDetalle "+ex);
		}

	}

//////////////////////////

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

/////////////////////////////
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

/////////////////////////////
public java.sql.ResultSet ObtenerUEntradas(String fec, String hra){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 

   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery("select codigo from farc_inventario where fecha='"+fec+"' and hora='"+hra+"' ");
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoCrearProveedor>>ObtenerMovimiento "+ex);
   }	
   return rs;
}



public void CrearActa(String i,String o,String f, String cs, String cr, String cp, String temb, String temp, String tenv, String t, String envp, String envs, String r, String e, String tm, String pm, String cm, String res, String cte, String ver, String obs, String dona, String fec, String hra, String te, String cl ){
	
	// creamos los ingresos al ACTA de RECEPCION

	
	try{
			PreparedStatement ps = null;
		    Conexion con=new Conexion();
		    ps=con.conn.prepareStatement("INSERT INTO farc_act_recep(cod_invfk,numero_orden,numero_factura,cantidad_solicitada,cantidad_recibida,cantidad_pendiente,cod_tipo_embalajefk,cod_tipo_empaquefk,cod_tipo_envasefk,temperatura,cod_env_primariofk,cod_env_secundariofk,recibe,entrega,tipo_muestreo,proc_muestreo,cant_muestreo,resultado,cump_timp_entr,verifico,observacion,donado,fecha,hora,tiemp_entr,cod_laboratoriofk)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
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
		    ps.setString(12,envs);
		    ps.setString(13,r);
		    ps.setString(14,e);
		    ps.setString(15,tm);
		    ps.setString(16,pm);
		    ps.setString(17,cm);
		    ps.setString(18,res);
		    ps.setString(19,cte);
		    ps.setString(20,ver);
		    ps.setString(21,obs);
		    ps.setString(22,dona);
		    ps.setString(23,fec);
		    ps.setString(24,hra);
		    ps.setString(25,te);
		    ps.setString(26,cl);
		    ps.executeUpdate();
			ps.close();
			con.cerrar();				
		}catch(Exception ex){
        	System.out.println("ERROR EN MetodoEntradas>>CrearActa "+ex);
		}

	}



//////////
public java.sql.ResultSet ObtenerMoviA(String SQL){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 

   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery(SQL);
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoEntradas>>ObtenerMoviA "+ex);
   }	
   return rs;
}
//////////

public void CrearAnulacion(String fec,String hra,String user,String concepto,String tmov, String mov){
	
	// creamos los ingresos de movimientos
	
	try{
			PreparedStatement ps = null;
		    Conexion con=new Conexion();
		    ps=con.conn.prepareStatement("insert into farc_anulacion(fecha,hora,cod_usuarioFk,concepto,tipo_mvtoFk,cod_movFk)values(?,?,?,?,?,?)");
		    ps.setString(1,fec);
		    ps.setString(2,hra);
		    ps.setString(3,user);
		    ps.setString(4,concepto);
		    ps.setString(5,tmov);
		    ps.setString(6,mov);
		    ps.executeUpdate();
			ps.close();
			con.cerrar();				
		}catch(Exception ex){
        	System.out.println("ERROR EN MetodoEntradas>>CrearAnulacion "+ex);
		}

	}

public java.sql.ResultSet ObtenerAnulacion(String fec, String hra){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 

   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery("select codigo from farc_anulacion where fecha='"+fec+"' and hora='"+hra+"' ");
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoEntradas>>ObtenerAnulacion "+ex);
   }	
   return rs;
}


public java.sql.ResultSet ObtenerDetalleAnulacion(String mov){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 

   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery("select i.cod_medFk, i.vencimiento, i.lote, i.cantidad, i.vunitario, i.vtotal, i.cod_ivaFk, i.cod_bodegaFk, i.cod_proveedorFk, i.invima  from farc_detallemov fd, farc_inventario i where fd.cod_movFk='"+mov+"' and fd.cod_invFk=i.codigo ");
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoEntradas>>ObtenerDetalleAnulacion "+ex);
   }	
   return rs;
}


public java.sql.ResultSet ObtenerInvAnular(String mov){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 

   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery("select cod_invFk from farc_detallemov where cod_movFk='"+mov+"'");
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoEntradas>>ObtenerDetalleAnulacion "+ex);
   }	
   return rs;
}

public java.sql.ResultSet ObtenerMovAnular(String mov, String inv){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 

   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery("select codigo from farc_detallemov where cod_movFk!='"+mov+"' and cod_invFk='"+inv+"'");
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoEntradas>>ObtenerDetalleAnulacion "+ex);
   }	
   return rs;
}

public java.sql.ResultSet ObtenerDispAnular(String inv){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 

   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery("select codigo  from farc_mov_dispensa where cod_invfk='"+inv+"'");
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoEntradas>>ObtenerDetalleAnulacion "+ex);
   }	
   return rs;
}

public void CrearDetalleAnulacion(String coda,String med,String ven,String lot,String ca, String vu, String vt, String iva, String bod, String p, String inv){
	
	// creamos los ingresos de movimientos
	
	try{
			PreparedStatement ps = null;
		    Conexion con=new Conexion();
		    ps=con.conn.prepareStatement("insert into farc_detalleanulacion(cod_anulFk,cod_medFk,vencimiento,lote,cantidad,vunitario,vtotal,cod_ivaFk,cod_bodegaFk,cod_proveedorFk,invima)values(?,?,?,?,?,?,?,?,?,?,?)");
		    ps.setString(1,coda);
		    ps.setString(2,med);
		    ps.setString(3,ven);
		    ps.setString(4,lot);
		    ps.setString(5,ca);
		    ps.setString(6,vu);
		    ps.setString(7,vt);
		    ps.setString(8,iva);
		    ps.setString(9,bod);
		    ps.setString(10,p);
		    ps.setString(11,inv);
		    ps.executeUpdate();
			ps.close();
			con.cerrar();				
		}catch(Exception ex){
        	System.out.println("ERROR EN MetodoEntradas>>CrearDetalleanulacion "+ex);
		}

	}


public void Anulardmov(String mov){
	PreparedStatement st = null;
    try{
    	Conexion con=new Conexion();
    	st= con.conn.prepareStatement("delete from farc_detallemov where cod_movFk='"+mov+"'");
    	st.executeUpdate();
    	st.close();
    	con.cerrar();
    	
    }
    catch(Exception ex){
    	System.out.println("ERROR EN MetodoEntradas>>Anularmov "+ex);
    
    }	
}

public void Anularinv(String mov){
	PreparedStatement st = null;
    try{
    	Conexion con=new Conexion();
    	st= con.conn.prepareStatement("delete from farc_inventario where cod_movFk='"+mov+"'");
    	st.executeUpdate();
    	st.close();
    	con.cerrar();
    	
    }
    catch(Exception ex){
    	System.out.println("ERROR EN MetodoEntradas>>Anularinv "+ex);
    
    }	
}
public void Anularmov(String mov){
	PreparedStatement st = null;
    try{
    	Conexion con=new Conexion();
    	st= con.conn.prepareStatement("delete from farc_movimientos where codigo='"+mov+"'");
    	st.executeUpdate();
    	st.close();
    	con.cerrar();
    	
    }
    catch(Exception ex){
    	System.out.println("ERROR EN MetodoEntradas>>Anularinv "+ex);
    
    }	
}


////////////////////PERFIL FARMACOTERAPEUTICO/////////////////

public ResultSet listarPacientesh(String texto) throws Exception {
	/**
	 *///System.out.println("metodoentradas");
	  java.sql.ResultSet r=null;
        Statement st = null;
       Conexion con=new Conexion();
        	st = con.conn.createStatement();
        																																																																
        	r=st.executeQuery(" select a.adm_numero_ingreso, p.nombre, p.primer_apellido, p.segundo_apellido,p.pac_codigo_paciente , c.ent_nit_contratante_fk, e.nombre_entidad, fe.cod_enc_factura from adm_admisiones a, adm_paciente p, adm_convenio c, adm_entidad e, fact_enc_factura fe	where a.estado='0' and a.atendido='0' and a.ahosp='1' and a.pac_codigo_paciente_fk=p.pac_codigo_paciente and p.conv_numero_contrato_fk=c.conv_numero_contrato and c.ent_nit_contratante_fk=e.ent_nit and a.adm_numero_ingreso=fe.cod_admision and (p.nombre like '"+texto+"%' or p.primer_apellido like '"+texto+"%' or p.segundo_apellido like '"+texto+"%') GROUP BY a.adm_numero_ingreso ");
        	//r=st.executeQuery(" select p.cod_programa, p.descripcion, p.especialidad, e.descripcion, p.clase_servicio, cs.descripcion from fact_programas p, fact_especialidades e, fact_clases_servicio cs where p.especialidad=e.cod_especialidad and p.clase_servicio=cs.cod_clase_servicio and p.descripcion like '"+texto+"%'  ");
   	return r;
}


public java.sql.ResultSet listarPerfil(String texto){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 

   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery(" SELECT * FROM farc_perfil_farmacoterapeutico WHERE cod_admisionfk='"+texto+"' ");
	}
   catch(Exception ex){
   	System.out.println("Error en MetodoEntradas>>listarPerfil "+ex);
   }	
   return rs;
}

public java.sql.ResultSet listarDetallePerfil(String texto){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 

   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery(" SELECT dp.*,dd.cantidad_dispensada,dd.cantidad_devuelta,dd.fecha FROM farc_detalle_perfil_farmacoterapeutico dp, farc_detalle_dispensa_perfil dd  WHERE dp.cod_perfilfk='"+texto+"' AND dp.codigo=dd.cod_detalleperfilfk ");
	}
   catch(Exception ex){
   	System.out.println("Error en MetodoEntradas>>listarDetallePerfil "+ex);
   }	
   return rs;
}


public java.sql.ResultSet listarDetallePerfil(String texto, String m, String v, String f){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 

   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery(" SELECT dp.*,dd.cantidad_dispensada,dd.cantidad_devuelta,dd.fecha,p.nombre, p.apellido FROM farc_detalle_perfil_farmacoterapeutico dp, farc_detalle_dispensa_perfil dd,seg_usuario u,seg_datos_personales p   WHERE dp.cod_perfilfk='"+texto+"' AND dp.codigo=dd.cod_detalleperfilfk AND cod_medicamentofk='"+m+"'  AND via_admon='"+v+"' AND frecuencia='"+f+"' AND u.usu_codigo=dp.cod_medico AND u.dat_codigo_fk=p.dat_codigo ");
	}
   catch(Exception ex){
   	System.out.println("Error en MetodoEntradas>>listarDetallePerfil "+ex);
   }	
   return rs;
}

public java.sql.ResultSet listarMedicamentosPerfil(String texto){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 

   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery(" SELECT m.codigo,m.nombre,m.concentracion,u.sigla,f.descripcion FROM medicamentos m,farc_unidades u,farc_formafarmaceutica f WHERE m.cod_unidadFK=u.codigo AND m.cod_formaFK=f.codigo AND m.codigo='"+texto+"' ");
	}
   catch(Exception ex){
   	System.out.println("Error en MetodoEntradas>>listarMedicamentosPerfil "+ex);
   }	
   return rs;
}


public java.sql.ResultSet listarDetallePerfil2(String texto){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 

   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery(" SELECT cod_perfilfk,cod_medicamentofk,via_admon,frecuencia FROM farc_detalle_perfil_farmacoterapeutico WHERE codigo='"+texto+"'  ");
	}
   catch(Exception ex){
   	System.out.println("Error en MetodoEntradas>>listarDetallePerfil2 "+ex);
   }	
   return rs;
}


public void ActualizarEstadoDPerfil(String x,String cp,String cm,String vi,String fr){

	PreparedStatement st = null;
    try{
    	Conexion con=new Conexion();
    	st= con.conn.prepareStatement(" UPDATE farc_detalle_perfil_farmacoterapeutico SET estado='"+x+"' WHERE cod_perfilfk='"+cp+"' AND cod_medicamentofk='"+cm+"' AND via_admon='"+vi+"' AND frecuencia='"+fr+"' ");
    	st.executeUpdate();
    	st.close();
    	con.cerrar();
    	
    }
    catch(Exception ex){
    	System.out.println("ERROR EN MetodoEntradas>>ActualizarEstadoDPerfil "+ex);
    
    }	
}

public void ActualizarObservacionPerfil(String x,String cp){

	PreparedStatement st = null;
    try{
    	Conexion con=new Conexion();
    	st= con.conn.prepareStatement(" UPDATE farc_perfil_farmacoterapeutico SET observacion='"+x+"' WHERE cod_admisionfk='"+cp+"' ");
    	st.executeUpdate();
    	st.close();
    	con.cerrar();
    	
    }
    catch(Exception ex){
    	System.out.println("ERROR EN MetodoEntradas>>ActualizarObservacionPerfil "+ex);
    
    }	
}


public java.sql.ResultSet listarDetallePerfil3(String texto){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 

   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery(" SELECT dp.* FROM farc_detalle_perfil_farmacoterapeutico dp  WHERE dp.cod_perfilfk='"+texto+"'  ");
	}
   catch(Exception ex){
   	System.out.println("Error en MetodoEntradas>>listarDetallePerfil "+ex);
   }	
   return rs;
}


//////////////////////////////////////////////ORDEN DE PRODUCCION////////////////////////////////////

public java.sql.ResultSet listarOrdenP(){	
   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery(" SELECT codigo,fecha FROM farc_orden_produccion WHERE estado=0 ");
	}
   catch(Exception ex){
   	System.out.println("Error en MetodoEntradas>>listarOrdenP "+ex);
   }	
   return rs;
}

public java.sql.ResultSet listarDetalleOrdenP(String texto){	
	   java.sql.ResultSet rs=null;
	   Statement st = null;
	   try{
	   	Conexion con=new Conexion();
	   	st = con.conn.createStatement();
	   	//rs=st.executeQuery(" SELECT m.* FROM farc_detalle_orden_produccion m, farc_med_orden_produccion mo, farc_grupofarmacologico gf WHERE m.cod_ordenproduccionfk='"+texto+"' and m.estado='0' AND m.cod_medOrden_Produccionfk=mo.codigo AND mo.cod_grupoffk=gf.cod_grupoFarmacologia ORDER BY gf.ident_riesgo,  m.cod_medOrden_Produccionfk ");
		rs=st.executeQuery(" SELECT m.*,mop.cod_medfk,mop.concentracion,mop.diluyente,mop.VR,mop.cod_interno,mop.cod_grupoffk FROM farc_detalle_orden_produccion m, farc_med_orden_produccion mo, farc_grupofarmacologico gf,farc_med_orden_produccion mop WHERE m.cod_ordenproduccionfk='"+texto+"' and (m.estado='0' OR m.estado='3') AND m.cod_medOrden_Produccionfk=mo.codigo AND mo.cod_grupoffk=gf.cod_grupoFarmacologia AND m.cod_medOrden_Produccionfk=mop.codigo ORDER BY gf.ident_riesgo,  m.cod_medOrden_Produccionfk  ");
		
		}
	   catch(Exception ex){
	   	System.out.println("Error en MetodoEntradas>>listarDetalleOrdenP "+ex);
	   }	
	   return rs;
	}

public java.sql.ResultSet listarDatosMedicamentos(String texto){	
	   java.sql.ResultSet rs=null;
	   Statement st = null;
	   try{
	   	Conexion con=new Conexion();
	   	st = con.conn.createStatement();
	   	rs=st.executeQuery(" SELECT * FROM farc_med_orden_produccion WHERE codigo='"+texto+"'  ");
		}
	   catch(Exception ex){
	   	System.out.println("Error en MetodoEntradas>>listarDatosMedicamentos "+ex);
	   }	
	   return rs;
	}

public java.sql.ResultSet listarPacientes(String texto){	
	   java.sql.ResultSet rs=null;
	   Statement st = null;
	   try{
	   	Conexion con=new Conexion();
	   	st = con.conn.createStatement();
	   	rs=st.executeQuery(" SELECT tipo_documento,numero_documento,nombre,primer_apellido,segundo_apellido FROM adm_paciente WHERE pac_codigo_paciente='"+texto+"'  ");
		}
	   catch(Exception ex){
	   	System.out.println("Error en MetodoEntradas>>listarPacientes "+ex);
	   }	
	   return rs;
	}

public java.sql.ResultSet listarGrupoFarmacologico(String texto){	
	   java.sql.ResultSet rs=null;
	   Statement st = null;
	   try{
	   	Conexion con=new Conexion();
	   	st = con.conn.createStatement();
	   	rs=st.executeQuery(" SELECT descripcion,ident_riesgo FROM farc_grupofarmacologico WHERE cod_grupoFarmacologia='"+texto+"'  ");
		}
	   catch(Exception ex){
	   	System.out.println("Error en MetodoEntradas>>listarGrupoFarmacologico "+ex);
	   }	
	   return rs;
	}

public java.sql.ResultSet listarMedicamento(String texto){	
	   java.sql.ResultSet rs=null;
	   Statement st = null;
	   try{
	   	Conexion con=new Conexion();
	   	st = con.conn.createStatement();
	   	rs=st.executeQuery(" SELECT nombre FROM medicamentos WHERE codigo='"+texto+"'  ");
		}
	   catch(Exception ex){
	   	System.out.println("Error en MetodoEntradas>>listarMedicamento "+ex);
	   }	
	   return rs;
	}

public java.sql.ResultSet listarConstantesVolumen(String texto){	
	   java.sql.ResultSet rs=null;
	   Statement st = null;
	   try{
	   	Conexion con=new Conexion();
	   	st = con.conn.createStatement();
	   	rs=st.executeQuery(" SELECT codigo, valor, descripcion FROM farc_constantes_volumen WHERE cod_med_orden_producionfk='"+texto+"'  ");
		}
	   catch(Exception ex){
	   	System.out.println("Error en MetodoEntradas>>listarConstantesVolumen "+ex);
	   }	
	   return rs;
	}


public void ActualizarPerfil(String r,String a,String h,String u,String c){

	PreparedStatement st = null;
    try{
    	Conexion con=new Conexion();
    	st= con.conn.prepareStatement(" UPDATE farc_orden_produccion SET responsable='"+r+"',auxiliar='"+a+"',hora_fin='"+h+"',cod_usuariofk='"+u+"',estado='1' WHERE codigo='"+c+"' ");
    	st.executeUpdate();
    	st.close();
    	con.cerrar();
    	
    }
    catch(Exception ex){
    	System.out.println("ERROR EN MetodoEntradas>>ActualizarObservacionPerfil "+ex);
    
    }	
}



public void ActualizarDetalle(String r,String a,String c){

	PreparedStatement st = null;
    try{
    	Conexion con=new Conexion();
    	st= con.conn.prepareStatement(" UPDATE farc_detalle_orden_produccion SET volumen_final='"+r+"',presentacion_final='"+a+"' WHERE codigo='"+c+"' ");
    	st.executeUpdate();
    	st.close();
    	con.cerrar();
    	
    }
    catch(Exception ex){
    	System.out.println("ERROR EN MetodoEntradas>>ActualizarDetalle "+ex);
    
    }	
}


public java.sql.ResultSet listarConstantesVolumenxcodigo(String texto){	
	   java.sql.ResultSet rs=null;
	   Statement st = null;
	   try{
	   	Conexion con=new Conexion();
	   	st = con.conn.createStatement();
	   	rs=st.executeQuery(" SELECT valor, descripcion FROM farc_constantes_volumen WHERE codigo='"+texto+"'  ");
		}
	   catch(Exception ex){
	   	System.out.println("Error en MetodoEntradas>>listarConstantesVolumen "+ex);
	   }	
	   return rs;
	}


public void CrearDetalleIndicador(String orden,String detalle){
	try{
			PreparedStatement ps = null;
		    Conexion con=new Conexion();
		    ps=con.conn.prepareStatement("insert into farc_detalle_ordenindicador(cod_ordenp,cod_det_ordenp)values(?,?)");
		    ps.setString(1,orden);
		    ps.setString(2,detalle);
		    ps.executeUpdate();
			ps.close();
			con.cerrar();				
		}catch(Exception ex){
        	System.out.println("ERROR EN MetodoEntradas>>CrearDetalleIndicador "+ex);
		}

	}


public void EliminarDetalle(String o, String u,String c){

	PreparedStatement st = null;
    try{
    	Conexion con=new Conexion();
    	st= con.conn.prepareStatement(" UPDATE farc_detalle_orden_produccion SET estado='1',observacion='"+o+"', usuario='"+u+"' WHERE codigo='"+c+"' ");
    	st.executeUpdate();
    	st.close();
    	con.cerrar();
    	
    }
    catch(Exception ex){
    	System.out.println("ERROR EN MetodoEntradas>>EliminarDetalle "+ex);
    
    }	
}


public void ActualizarDosis(String o, String c){

	PreparedStatement st = null;
    try{
    	Conexion con=new Conexion();
    	st= con.conn.prepareStatement(" UPDATE farc_detalle_orden_produccion SET dosis='"+o+"' WHERE codigo='"+c+"' ");
    	st.executeUpdate();
    	st.close();
    	con.cerrar();
    	
    }
    catch(Exception ex){
    	System.out.println("ERROR EN MetodoEntradas>>EliminarDetalle "+ex);
    
    }	
}


public void ActualizarVf(String vf, String c){
	
	//System.out.println("UPDATE farc_detalle_orden_produccion SET volumen_final='"+vf+"' WHERE codigo='"+c+"' ");
	PreparedStatement st = null;
    try{
    	Conexion con=new Conexion();
    	st= con.conn.prepareStatement(" UPDATE farc_detalle_orden_produccion SET volumen_final='"+vf+"' WHERE codigo='"+c+"' ");
    	st.executeUpdate();
    	st.close();
    	con.cerrar();
    	System.out.println("UPDATE farc_detalle_orden_produccion SET volumen_final='"+vf+"' WHERE codigo='"+c+"' ");
    }
    catch(Exception ex){
    	System.out.println("ERROR EN MetodoEntradas>>ActualizarVf "+ex);
    
    }	
}


public void ActualizarEsta(String c){
	
	//System.out.println("UPDATE farc_detalle_orden_produccion SET volumen_final='"+vf+"' WHERE codigo='"+c+"' ");
	PreparedStatement st = null;
    try{
    	Conexion con=new Conexion();
    	st= con.conn.prepareStatement(" UPDATE farc_detalle_orden_produccion SET estado=3 WHERE codigo='"+c+"' ");
    	st.executeUpdate();
    	st.close();
    	con.cerrar();
    	System.out.println("UPDATE farc_detalle_orden_produccion SET volumen_final=3 WHERE codigo='"+c+"' ");
    }
    catch(Exception ex){
    	System.out.println("ERROR EN MetodoEntradas>>ActualizarEsta "+ex);
    
    }	
}

public java.sql.ResultSet BuscarEstado(String c){	
	   java.sql.ResultSet rs=null;
	   Statement st = null;
	   try{
	   	Conexion con=new Conexion();
	   	st = con.conn.createStatement();
	   	rs=st.executeQuery(" SELECT f.estado FROM farc_detalle_orden_produccion f where f.codigo='"+c+"' ");
		}
	   catch(Exception ex){
	   	System.out.println("Error en MetodoEntradas>>listarDetalleOrdenP "+ex);
	   }	
	   return rs;
	}



/////////////////
}



/*

public class MetodoCrearIvaGrupoUnidad {

	public void CrearIva(String nombreIva,String valor,String descripcion){
		
		 // creamos los tipos de iva
		
		CrearIvaGrupoUnidad ci = new CrearIvaGrupoUnidad();
		
		ci.setnombreIva(nombreIva);
		ci.setvalor(valor);
		ci.setdescripcion(descripcion);
		
		
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("insert into farc_iva(descripcion,valor,observacion)values(?,?,?)");
			    ps.setString(1,ci.getnombreIva());
			    ps.setString(2,ci.getvalor());
			    ps.setString(3,ci.getdescripcion());
			    ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoCrearIvaGrupoUnidad>>CrearIva "+ex);
			}

		}
	
	public void CrearGrupo(String nombreGrupo,String observacion){
		
		// creamos los grupos
		
		CrearIvaGrupoUnidad ci = new CrearIvaGrupoUnidad();
		
		ci.setnombreGrupo(nombreGrupo);
		ci.setobservacion(observacion);
		
		
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("insert into farc_grupo(descripcion,observacion)values(?,?)");
			    ps.setString(1,ci.getnombreGrupo());
			    ps.setString(2,ci.getobservacion());
			    ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoCrearIvaGrupoUnidad>>CrearGrupo "+ex);
			}

		}
	
	
	public void CrearUnidad(String nombreUnidad,String sigla){
	
		// creamos los grupos
		
		CrearIvaGrupoUnidad ci = new CrearIvaGrupoUnidad();
		
		ci.setnombreUnidad(nombreUnidad);
		ci.setsigla(sigla);
		
		
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("insert into farc_unidades(descripcion,sigla)values(?,?)");
			    ps.setString(1,ci.getnombreUnidad());
			    ps.setString(2,ci.getsigla());
			    ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoCrearIvaGrupoUnidad>>CrearUnidad "+ex);
			}

		}
	
	public void CrearForma(String nombreForma,String siglaF){
		
		// creamos los grupos
		
		CrearIvaGrupoUnidad ci = new CrearIvaGrupoUnidad();
		
		ci.setnombreForma(nombreForma);
		ci.setsiglaF(siglaF);
		
		
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("insert into farc_formafarmaceutica(descripcion,forma_farmaceutica)values(?,?)");
			    ps.setString(1,ci.getnombreForma());
			    ps.setString(2,ci.getsiglaF());
			    ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoCrearIvaGrupoUnidad>>CrearFormaFarmaceutica "+ex);
			}

		}
	
	
public java.sql.ResultSet ObtenerIva(){	
		
		// consulta que obtiene los diferentes tipos de ivas., 
		
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo,descripcion, valor, observacion from farc_iva");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearIvaGrupoUnidad>>ObtenerIva "+ex);
        }	
        return rs;
    }


public java.sql.ResultSet ObtenerGrupo(){	
	
	// consulta que obtiene los diferentes Grupos., 
	
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery("select codigo,descripcion,observacion from farc_grupo");
    }
    catch(Exception ex){
    	System.out.println("Error en MetodoCrearIvaGrupoUnidad>>ObtenerGrupo "+ex);
    }	
    return rs;
}

public java.sql.ResultSet ObtenerUnidad(){	
	
	// consulta que obtiene los diferentes Unidades., 
	
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery("select codigo,descripcion,sigla from farc_unidades");
    }
    catch(Exception ex){
    	System.out.println("Error en MetodoCrearIvaGrupoUnidad>>ObtenerUnidad "+ex);
    }	
    return rs;
}

public java.sql.ResultSet ObtenerForma(){	
	
	// consulta que obtiene los diferentes Formas Farmaceuticas., 
	
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery("select codigo,descripcion,forma_farmaceutica from farc_formafarmaceutica");
    }
    catch(Exception ex){
    	System.out.println("Error en MetodoCrearIvaGrupoUnidad>>ObtenerUnidad "+ex);
    }	
    return rs;
}

	
public java.sql.ResultSet ObtenerCodigoIva(String nombreIva){	
	
	 // consulta que obtiene las bodegas, 
	 
   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery("select codigo from farc_iva where descripcion='"+nombreIva+"'");
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoCrearIvaGrupoUnidad>>ObtenerCodigoIva "+ex);
   }	
   return rs;
}

public java.sql.ResultSet ObtenerCodigoGrupo(String nombreGrupo){	
	
	 // consulta que obtiene las bodegas, 
	 
  java.sql.ResultSet rs=null;
  Statement st = null;
  try{
  	Conexion con=new Conexion();
  	st = con.conn.createStatement();
  	rs=st.executeQuery("select codigo from farc_grupo where descripcion='"+nombreGrupo+"'");
  }
  catch(Exception ex){
  	System.out.println("Error en MetodoCrearIvaGrupoUnidad>>ObtenerCodigoGrupo "+ex);
  }	
  return rs;
}


public java.sql.ResultSet ObtenerCodigoUnidad(String nombreUnidad){	
	
	 // consulta que obtiene las bodegas, 
	 
 java.sql.ResultSet rs=null;
 Statement st = null;
 try{
 	Conexion con=new Conexion();
 	st = con.conn.createStatement();
 	rs=st.executeQuery("select codigo from farc_unidades where descripcion='"+nombreUnidad+"'");
 }
 catch(Exception ex){
 	System.out.println("Error en MetodoCrearIvaGrupoUnidad>>ObtenerCodigoUnidad "+ex);
 }	
 return rs;
}

public java.sql.ResultSet ObtenerCodigoForma(String nombreForma){	
	
	 // consulta que obtiene las bodegas, 
	 
java.sql.ResultSet rs=null;
Statement st = null;
try{
	Conexion con=new Conexion();
	st = con.conn.createStatement();
	rs=st.executeQuery("select codigo from farc_formafarmaceutica where descripcion='"+nombreForma+"'");
}
catch(Exception ex){
	System.out.println("Error en MetodoCrearIvaGrupoUnidad>>ObtenerCodigoForma "+ex);
}	
return rs;
}

}
*/
