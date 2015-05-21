package ord_metodo;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;

import farc_bean.CrearProveedor;

import adm_logica.Conexion;

public class MetodoProve {
	
public java.sql.ResultSet ObtenerPais(){	
		
		//consulta que obtiene el codigo de la tabla paisesa, 

       java.sql.ResultSet rs=null;
       Statement st = null;
       try{
       	Conexion con=new Conexion();
       	st = con.conn.createStatement();
       	rs=st.executeQuery("select pais_codigo, nombre from adm_pais");
       }
       catch(Exception ex){
       	System.out.println("Error en MetodoProve>>ObtenerPaises "+ex);
       }	
       return rs;
   }

	public java.sql.ResultSet ObtenerProveedor(String iden){	
		
		//consulta que obtiene el codigo de la tabla paisesa, 
	
	   java.sql.ResultSet rs=null;
	   Statement st = null;
	   try{
	   	Conexion con=new Conexion();
	   	st = con.conn.createStatement();
	   	rs=st.executeQuery("select p.nit, p.razon_social, p.codigo, p.estado, p.tipo_identificacion, p.dig_verif FROM farc_proveedor p, ord_prov_terc opt  WHERE  opt.cod_provefk=p.codigo and p.estado='"+iden+"'order by p.estado,p.razon_social ");
	   }
	   catch(Exception ex){
	   	System.out.println("Error en MetodoProve>>ObtenerProveedor "+ex);
	   }	
	   return rs;
	}
	
	
	public java.sql.ResultSet BuscarBancos(String NomBanco){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM cont_bancos WHERE nombre LIKE '%"+NomBanco+"%' ");
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProve>>BuscarBancos "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet ObtenerCodigoProveedor(String nit){	
		/**
		 * consulta que obtiene las bodegas, 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo from farc_proveedor where nit='"+nit+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearBodega>>ObtenerCodigoProveedor "+ex);
        }	
        return rs;
    }
	
	
	public void CrearProveedor(String nit,String muni,String telefono,String fax,String email,String contacto,String clase,String razon,String direccion, String observacion,String  tc,String dv,String estado,String diaspla){
		/**
			 * creamos el proveedor
		 */
			
			try{
					PreparedStatement ps = null;
				    Conexion con=new Conexion();                                                      
				    ps=con.conn.prepareStatement("insert into farc_proveedor(nit,municipio_fk,telefono,fax,email,contacto,clase_contribuyente,razon_social,direccion,comentario,tipo_identificacion,dig_verif,estado,dias_plazo)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				    ps.setString(1,nit);
				    ps.setString(2,muni);
				    ps.setString(3,telefono);
				    ps.setString(4,fax);
				    ps.setString(5,email);
				    ps.setString(6,contacto);
				    ps.setString(7,clase);
				    ps.setString(8,razon);
				    ps.setString(9,direccion);
				    ps.setString(10,observacion);
				    ps.setString(11, tc);
				    ps.setString(12, dv);
				    ps.setString(13, estado);
				    ps.setString(14, diaspla);
				    ps.executeUpdate();
					ps.close();
					con.cerrar();				
				}catch(Exception ex){
		        	System.out.println("ERROR EN MetodoProve>>CrearProveedor "+ex);
				}

			}
	
	
	public void GuardarActEconoForma(String cod,String aecon,String formapago,String cta,String rfte,String ica,Date Fecha,Time Hora,String user){
		/**
			 *Guardamos la actividad economica 
		 */
			
			try{
					PreparedStatement ps = null;
				    Conexion con=new Conexion();                                                      
				    ps=con.conn.prepareStatement("INSERT INTO ord_prove_actecono(cod_provefk,cod_aecofk,cod_reftefk, cod_icafk,fecha,hora,cod_usufk,cod_formapagofk,cta)values(?,?,?,?,?,?,?,?,?)");
				    ps.setString(1,cod);
				    ps.setString(2,aecon);
				    ps.setString(3,rfte);
				    ps.setString(4,ica);
				    ps.setDate(5,Fecha);
				    ps.setTime(6,Hora);
				    ps.setString(7,user);
				    ps.setString(8,formapago);
				    ps.setString(9,cta);
				    ps.executeUpdate();
					ps.close();
					con.cerrar();				
				}catch(Exception ex){
		        	System.out.println("ERROR EN MetodoProve>>GuardarActEconoForma "+ex);
				}

			}
	
	
	public void ActualizarActEconoForma(String cod,String aecon,String formapago,String cta,String rfte,String ica,Date Fecha,Time Hora,String user){
		/**
			 *Actualiza la actividad economica 
		 */
			
			try{
					PreparedStatement ps = null;
				    Conexion con=new Conexion();                                                      
				    ps=con.conn.prepareStatement("UPDATE  ord_prove_actecono SET  cod_aecofk='"+aecon+"' , cod_reftefk='"+rfte+"' , cod_icafk='"+ica+"', cod_formapagofk='"+formapago+"', cta='"+cta+"' WHERE cod_provefk='"+cod+"' ");
				    ps.executeUpdate();
					ps.close();
					con.cerrar();	
					
				}catch(Exception ex){
		        	System.out.println("ERROR EN MetodoProve>>ActualizarActEconoForma "+ex);
				}

			}
	
	public void GuardarContTerceros(String nit,String razon,String telefono,Date Fecha,String tdep, String tmuni,String direccion,String codprove,String telcontacto,String codbanco,String cta, String estado,String clase,String email,String diaspla)  throws SQLException{
		/**
		 */
		 java.sql.ResultSet rs=null;
		 Statement st = null;
			
			try{
					PreparedStatement ps = null;
				    Conexion con=new Conexion();                                                      
				    ps=con.conn.prepareStatement("INSERT INTO cont_terceros (tipo_identificacion,numero_documento,razon_social,nombre_razonsoc,autoretenedor,tipo_regimen,direccion,telefono,telcontacto,emailcontac,departamento,ciudad,operacion_extranjera,Ind_Cliente,Ind_Proveedor,Ind_Empleado,porc_retiva,porc_retica,dias_plazo,estado,fecha_ingreso,cuenta_banco,banco) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				    ps.setString(1,"NI");
				    ps.setString(2,nit);
				    ps.setString(3,razon);
				    ps.setString(4,razon);
				    ps.setString(5,"No");
				    ps.setString(6,clase);
				    ps.setString(7,direccion);
				    ps.setString(8,telefono);
				    ps.setString(9,telcontacto);
				    ps.setString(10,email);
				    ps.setString(11,tdep);
				    ps.setString(12,tmuni);
				    ps.setString(13,"No");
				    ps.setString(14,"0");
				    ps.setString(15,"0");
				    ps.setString(16,"0");
				    ps.setString(17,"0");
				    ps.setString(18,"0");
				    ps.setString(19, diaspla);
				    ps.setString(20,estado);
				    ps.setDate(21,Fecha);
				    ps.setString(22,cta);
				    ps.setString(23,codbanco);
				    ps.executeUpdate();
					ps.close();
					con.cerrar();	
					
					Conexion con2=new Conexion();
		        	st = con2.conn.createStatement();
		        	rs=st.executeQuery("SELECT * from cont_terceros where tipo_identificacion='NI' AND numero_documento='"+nit+"' AND razon_social='"+razon+"' AND nombre_razonsoc='"+razon+"' AND autoretenedor='No'  AND tipo_regimen='"+clase+"' AND direccion='"+direccion+"' AND telefono='"+telefono+"' AND " +
		        						"telcontacto='"+telcontacto+"' AND emailcontac='"+email+"'  AND departamento='"+tdep+"' AND ciudad='"+tmuni+"' AND operacion_extranjera='No'  AND " +
		        						"Ind_Cliente=0 AND Ind_Proveedor=0 AND Ind_Empleado=0 AND porc_retiva=0 AND porc_retica=0 AND dias_plazo='"+diaspla+"' AND estado='"+estado+"' AND fecha_ingreso='"+Fecha+"' AND cuenta_banco='"+cta+"' and banco='"+codbanco+"' ");
					
		        
		        	String codterc="";
		        	if(rs.next()){
		        		System.out.println("entrando a busqueda de codtercero");
		        		codterc=rs.getString(1);
		        	}rs.getStatement().getConnection().close();
		        	con2.cerrar();
		        	
		        	PreparedStatement pss = null;
		        	Conexion con3=new Conexion();                                                       
		        	pss=con3.conn.prepareStatement("INSERT INTO ord_prov_terc (cod_provefk,cod_tercerofk) values(?,?) ");
		            pss.setString(1,codprove);
		            pss.setString(2,codterc);
		            pss.executeUpdate();
					pss.close();
					con3.cerrar();	
		        	
				}catch(Exception ex){
		        	System.out.println("ERROR EN MetodoProve>>GuardarContTerceros "+ex);
				}

			}
	
	
	public void ActualizaContTerceros(String nit,String razon,String telefono,Date Fecha,String tdep, String tmuni,String direccion,String tercero, String telcontacto,String codbanco,String cta,String estado,String clase,String email,String diaspla)  throws SQLException{
		/**
		 */
		 java.sql.ResultSet rs=null;
		 Statement st = null;
		// ps=con.conn.prepareStatement("INSERT INTO cont_terceros (tipo_identificacion,numero_documento,razon_social,nombre_razonsoc,autoretenedor,tipo_regimen,direccion,telefono,telcontacto,emailcontac,departamento,ciudad,operacion_extranjera,Ind_Cliente,Ind_Proveedor,Ind_Empleado,porc_retiva,porc_retica,dias_plazo,estado,fecha_ingreso,cuenta_banco,banco) 
			try{
					PreparedStatement ps = null;
				    Conexion con=new Conexion();                                                      
				    ps=con.conn.prepareStatement("UPDATE cont_terceros SET numero_documento='"+nit+"', razon_social='"+razon+"' ,nombre_razonsoc='"+razon+"',direccion='"+direccion+"', telefono='"+telefono+"', departamento='"+tdep+"', ciudad='"+tmuni+"', tipo_regimen='"+clase+"' , telcontacto='"+telcontacto+"' , dias_plazo='"+diaspla+"',estado='"+estado+"', cuenta_banco='"+cta+"',banco='"+codbanco+"' where codigo='"+tercero+"' ");
				    ps.executeUpdate();
					ps.close();
					con.cerrar();
				   
				}catch(Exception ex){
		        	System.out.println("ERROR EN MetodoProve>>ActualizaContTerceros "+ex);
				}

			}
	
	
public java.sql.ResultSet BuscarMuni(String codmuni){	
		
		//consulta que obtiene el texto del departamento y del municipio teniendo en cuenta el  codigo del municipio

       java.sql.ResultSet rs=null;
       Statement st = null;
       try{
       	Conexion con=new Conexion();
       	st = con.conn.createStatement();
       	rs=st.executeQuery("SELECT ad.nombre AS nombre_dep ,am.nombre AS nombre_muni  FROM adm_departamento ad, adm_municipio am WHERE am.dept_codigo_fk=ad.dept_codigo AND am.muni_cod='"+codmuni+"' ");
       }
       catch(Exception ex){
       	System.out.println("Error en MetodoProve>>BuscarMuni "+ex);
       }	
       return rs;
   }
	
	
	
	
	public java.sql.ResultSet ObtenerDpto(String pais){	
		
		//consulta que obtiene los departamentos 

       java.sql.ResultSet rs=null;
       Statement st = null;
       try{
       	Conexion con=new Conexion();
       	st = con.conn.createStatement();
       	rs=st.executeQuery("select dept_codigo, nombre from adm_departamento where pais_codigo_fk='"+pais+"'");
       }
       catch(Exception ex){
       	System.out.println("Error en MetodoProve>>ObtenerDpto "+ex);
       }	
       return rs;
   }
	
	
	public java.sql.ResultSet VerificarAct(String codigo){	
		
		//Verifica si el proveedor ha sido actualizado. 

       java.sql.ResultSet rs=null;
       Statement st = null;
       Conexion con = null;
       try{
       	 con=new Conexion();
       	st = con.conn.createStatement();
       	rs=st.executeQuery("SELECT fecha FROM ord_audproveedor where cod_provefk='"+codigo+"' GROUP BY cod_provefk ORDER BY fecha ASC ");
       	System.out.println("SELECT * FROM ord_audproveedor where cod_provefk='"+codigo+"' ORDER BY fecha ASC ");
       
       }
       catch(Exception ex){
       	System.out.println("Error en MetodoProve>>VerificarAct "+ex);
       }
       return rs;
      
   }
	
public java.sql.ResultSet ObtenerMuni(String ciudad){	
		
		//consulta que obtiene los municipios, 

       java.sql.ResultSet rs=null;
       Statement st = null;
       try{
       	Conexion con=new Conexion();
       	st = con.conn.createStatement();
       	rs=st.executeQuery("select muni_cod, nombre from adm_municipio where dept_codigo_fk='"+ciudad+"' order by nombre");
       }
       catch(Exception ex){
       	System.out.println("Error en MetodoProve>>ObtenerMuni "+ex);
       }	
       return rs;
   }

public java.sql.ResultSet ObtenerProveedord(String cod){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 

   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery("SELECT p.nit, p.municipio_fk, p.telefono, p.fax, p.email, p.contacto, p.clase_contribuyente, p.razon_social, p.direccion, p.comentario, opt.cod_tercerofk, p.tipo_identificacion,p.dig_verif,p.dias_plazo,t.banco,t.cuenta_banco,t.telcontacto,p.estado "+ 
   		 " FROM farc_proveedor p, ord_prov_terc opt, cont_terceros t  WHERE  t.codigo=opt.cod_tercerofk AND opt.cod_provefk=p.codigo AND p.codigo='"+cod+"'");
  
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoProve>>ObtenerProveedor "+ex);
   }	
   return rs;
}



public java.sql.ResultSet BusActFp(String codigo){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 

   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery("SELECT oa.codigo, oa.actividad_economica, oa.codigo_ciiu, oa.cod_crefk, oc.valor AS val_cre, of.descripcion, p.cta, orfte.codigo AS cod_rfte, orfte.valor AS val_rfte, oic.codigo AS cod_ica , oic.valor AS val_ica, p.cod_formapagofk  "+
   			           "FROM ord_prove_actecono  p, ord_formadepago of, ord_actividadeconomica oa, ord_retefuente orfte, ord_ica oic, ord_cre oc "+
                       "WHERE p.cod_aecofk=oa.codigo AND of.codigo=p.cod_formapagofk AND oa.cod_crefk=oc.codigo AND orfte.codigo=p.cod_reftefk AND p.cod_icafk=oic.codigo AND p.cod_provefk='"+codigo+"' ");
  
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoProve>>BusActFp "+ex);
   }	
   return rs;
}





public java.sql.ResultSet ObtenerNMuni(String cod){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 

   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery("select nombre, dept_codigo_fk from adm_municipio where muni_cod='"+cod+"'");
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoProve>>ObtenerPaises "+ex);
   }	
   return rs;
}


public java.sql.ResultSet BusBanco(String cod){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 

   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery("select * from cont_bancos where codigo='"+cod+"' ");
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoProve>>BusBanco "+ex);
   }	
   return rs;
}

public java.sql.ResultSet ObtenerNDpto(String cod){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 

   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery("select nombre, pais_codigo_fk from adm_departamento where dept_codigo='"+cod+"'");
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoProve>>ObtenerPaises "+ex);
   }	
   return rs;
}

public java.sql.ResultSet ObtenerNPais(String cod){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 

   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery("select nombre from adm_pais where pais_codigo ='"+cod+"'");
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoProve>>ObtenerPaises "+ex);
   }	
   return rs;
}

public void ModificarProveedor(String nit,String muni,String telefono,String fax,String email,String contacto,String clase,String razon,String direccion, String observacion, String c,String  tc,String dv,String estado,String diaspla){

		try{
			PreparedStatement st = null;
			    Conexion con=new Conexion();                                                      
			  //  ps=con.conn.prepareStatement("insert into farc_proveedor(nit,municipio_fk,telefono,fax,email,contacto,clase_contribuyente,razon_social,direccion,comentario)values(?,?,?,?,?,?,?,?,?,?)");
			    st= con.conn.prepareStatement("update farc_proveedor set nit='"+nit+"', municipio_fk='"+muni+"', telefono='"+telefono+"', fax='"+fax+"', email='"+email+"', contacto='"+contacto+"', clase_contribuyente='"+clase+"', razon_social='"+razon+"', direccion='"+direccion+"', comentario='"+observacion+"'  , tipo_identificacion='"+tc+"', dig_verif='"+dv+"' , estado='"+estado+"' , dias_plazo='"+diaspla+"' where codigo='"+c+"' ");
		    	st.executeUpdate();
		    	st.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoProve>>CrearProveedor "+ex);
			}

		}

public void Auditoria(String user,String descripcion,String codpp,Date Fecha,Time Hora){	
		try{
			PreparedStatement st = null;
			    Conexion con=new Conexion();                                                      
			
			    st= con.conn.prepareStatement("INSERT INTO  ord_audproveedor (fecha,hora,cod_userfk,cod_provefk,descripcion)  VALUES (?,?,?,?,?) ");
			    st.setDate(1, Fecha);
			    st.setTime(2, Hora);
			    st.setString(3, user);
			    st.setString(4, codpp);
			    st.setString(5, descripcion);
			    st.executeUpdate();
		    	st.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoProve>>Auditoria "+ex);
			}

		}

public java.sql.ResultSet DatosProve(String codprove){	
	java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery("SELECT p.nit, p.municipio_fk, p.telefono, p.fax, p.email, p.contacto, p.clase_contribuyente, p.razon_social, p.direccion, p.comentario, opt.cod_tercerofk, p.tipo_identificacion,p.dig_verif,p.dias_plazo,t.banco,t.cuenta_banco,t.telcontacto, t.banco "+
    				"FROM farc_proveedor p, ord_prov_terc opt, cont_terceros t  WHERE  t.codigo=opt.cod_tercerofk AND opt.cod_provefk=p.codigo and p.codigo='"+codprove+"' ");
    }
    catch(Exception ex){
    	System.out.println("Error en MetodoProve>>DatosProve "+ex);
    }	
    return rs;
}

public java.sql.ResultSet ConsultarTiposDocumentos(){	
	java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery(" SELECT * FROM adm_tipodocumento ");
    }
    catch(Exception ex){
    	System.out.println("Error en MetodoProve>>ConsultarTiposDocumentos "+ex);
    }	
    return rs;
}

public java.sql.ResultSet BuscarformasdePago(){	
	
	
   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery("SELECT * FROM ord_formadepago ");
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoProve>>BuscarformardePago "+ex);
   }	
   return rs;
}


public java.sql.ResultSet BuscarActividad(String texto){	
	
	
	   java.sql.ResultSet rs=null;
	   Statement st = null;
	   try{
	   	Conexion con=new Conexion();
	   	st = con.conn.createStatement();
	   	rs=st.executeQuery("SELECT * FROM ord_actividadeconomica where (( actividad_economica like '%"+texto+"%' ) OR ( codigo_ciiu LIKE '%"+texto+"%' ))");
	   }
	   catch(Exception ex){
	   	System.out.println("Error en MetodoProve>>BuscarActividad"+ex);
	   }	
	   return rs;
	}

public java.sql.ResultSet BuscarDatosActividad(String codaeco){	
	
	
	   java.sql.ResultSet rs=null;
	   Statement st = null;
	   try{
	   	Conexion con=new Conexion();
	   	st = con.conn.createStatement();
	   	rs=st.executeQuery("SELECT oa.codigo, oa.codigo_ciiu, oa.actividad_economica, oc.valor AS valorcre "+ 
	   						"FROM ord_actividadeconomica oa, ord_cre oc "+
	   						"WHERE oa.cod_crefk=oc.codigo and oa.codigo='"+codaeco+"'");
	   }
	   catch(Exception ex){
	   	System.out.println("Error en MetodoProve>>BuscarDatosActividad"+ex);
	   }	
	   return rs;
	}


public java.sql.ResultSet BuscarRefte(String texto){	
	
	
	   java.sql.ResultSet rs=null;
	   Statement st = null;
	   try{
	   	Conexion con=new Conexion();
	   	st = con.conn.createStatement();
	   	rs=st.executeQuery("SELECT * FROM ord_retefuente where valor like '%"+texto+"%' ");
	   }
	   catch(Exception ex){
	   	System.out.println("Error en MetodoProve>>BuscarRefte"+ex);
	   }	
	   return rs;
	}

public java.sql.ResultSet Buscarica(String texto){	
	
	
	   java.sql.ResultSet rs=null;
	   Statement st = null;
	   try{
	   	Conexion con=new Conexion();
	   	st = con.conn.createStatement();
	   	rs=st.executeQuery("SELECT * FROM ord_ica where valor like '%"+texto+"%' ");
	   }
	   catch(Exception ex){
	   	System.out.println("Error en MetodoProve>>Buscarica"+ex);
	   }	
	   return rs;
	}


}
