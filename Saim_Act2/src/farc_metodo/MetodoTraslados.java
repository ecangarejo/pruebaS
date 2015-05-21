package farc_metodo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import adm_logica.Conexion;
import farc_bean.Entradas;


public class MetodoTraslados {

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
	
	public java.sql.ResultSet ObtenerBodegaso(String x){	
		/**
		 * consulta que obtiene las unidades del modulo de farmacia, 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo, nombre from farc_bodegas where codigo="+x+"");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoEntradas>>ObtenerBodegas "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerBodegasd(String x, String user){	
		/**
		 * consulta que obtiene las unidades del modulo de farmacia, 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT b.codigo, b.nombre FROM seg_bodegas_autorizadas s, farc_bodegas b WHERE s.cod_usuariofk="+user+" AND s.cod_bodegafk=b.codigo AND s.cod_bodegafk!="+x+"");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoEntradas>>ObtenerBodegas "+ex);
        }	
        return rs;
    }
	
	
	public ResultSet listarArticulos(String texto, String xx) throws Exception {
		/**
		 */
		  java.sql.ResultSet r=null;
	        Statement st = null;
	       Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	//cesar listar art pero de la tabla inv
	        	r=st.executeQuery("select m.codigo,m.nombre,m.concentracion,i.lote,i.invima,i.vencimiento,i.cantidad,i.vunitario,p.razon_social,i.codigo from medicamentos m, farc_inventario i, farc_proveedor p where m.codigo=i.cod_medFk and p.codigo=i.cod_proveedorFk and i.cod_bodegaFk='"+xx+"' and i.cantidad>0 and m.nombre like '"+texto+"%'");
	        return r;
	    }
	
	////////////////////////////////////////
	public ResultSet listarMovimientoss() {
		/**
		 */
		  java.sql.ResultSet r=null;
	      Statement st = null;
	      try{
	       Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	r=st.executeQuery("select codigo,descripcion from farc_tipomovimiento where cod_movFk='2'");
	      }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoEntradas>>ObtenerBodegas "+ex);
	        }
	        	return r;     
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



public java.sql.ResultSet ObtenerCodTraslado(){	
	/**
	 * consulta que obtiene las unidades del modulo de farmacia, 
	 */
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery("select codigo from farc_tipomovimiento where cod_movFk = '3' ");
    }
    catch(Exception ex){
    	System.out.println("Error en MetodoEntradas>>ObtenerCodTraslado "+ex);
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



public void CrearMovimientos( String cantidad, String movimiento, String fecha,  String hra, String user, String factura, String concepto, String consec){
	
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
		    ps=con.conn.prepareStatement("insert into farc_movimientos(cantidad,tipo_mvtoFk,fecha,hora,cod_usuariofK,nsoporte,concepto,consec)values(?,?,?,?,?,?,?,?)");
		  //  ps.setString(1,ci.getbodega());
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

//////////////////////////

public void CrearDetalle(String movi,String inv, String bod, String ca){
	
	// creamos los ingresos de movimientos
	
	try{
			PreparedStatement ps = null;
		    Conexion con=new Conexion();
		    ps=con.conn.prepareStatement("insert into farc_detallemov(cod_movFk,cod_invFk,cod_bodegai, cantidad)values(?,?,?,?)");
		    ps.setString(1,movi);
		    ps.setString(2,inv);
		    ps.setString(3,bod);
		    ps.setString(4,ca);
		    ps.executeUpdate();
			ps.close();
			con.cerrar();				
		}catch(Exception ex){
        	System.out.println("ERROR EN MetodoSalidas>>CrearDetalle "+ex);
		}

	}

////////////////////////


public java.sql.ResultSet ObtenerIva(String xx){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 

   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery("select cod_ivaFk from farc_inventario where codigo = '"+xx+"' ");
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoCrearProveedor>>ObtenerMovimiento "+ex);
   }	
   return rs;                
}
///////////////////////

public java.sql.ResultSet ObtenerVIva(String xx){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 

   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery("select valor from farc_iva where codigo = '"+xx+"' ");
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoCrearProveedor>>ObtenerMovimiento "+ex);
   }	
   return rs;
}
///////////////////////

public java.sql.ResultSet ObtenerPro(String xx){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 

   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery("select cod_proveedorFk from farc_inventario where codigo = '"+xx+"' ");
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoCrearProveedor>>ObtenerMovimiento "+ex);
   }	
   return rs;
}


/////////////////////////

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
   	System.out.println("Error en MetodoCrearProveedor>>ObtenerMovimiento "+ex);
   }	
   return rs;
}

/////////////////////////////
public void CrearSalidas(String inv,String cant){
	
	// creamos los ingresos de INVENTARIO
	
	PreparedStatement st = null;
    try{
    	Conexion con=new Conexion();
    	st= con.conn.prepareStatement("update farc_inventario set cantidad='"+cant+"' where codigo='"+inv+"'");
    	st.executeUpdate();
    	st.close();
    	con.cerrar();
    	
    }
    catch(Exception ex){
    	System.out.println("ERROR EN MetodoSalidas>>CrearSalidas "+ex);
    
    }	
	
}

/////////////////////////////

public void CrearEntradasT(String movimiento,String articulo,String vence, String lote, String cantidad, String vunitario, String vtotal, String iva, String bodega, String fecha, String hra, String proveedor,String invima){
	
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

///////////////////


///////////////////////////



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
