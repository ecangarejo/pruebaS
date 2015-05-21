package farc_metodo;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Time;

import adm_logica.Conexion;
import farc_bean.CrearArticulo;
import farc_bean.CrearGrupo;
import farc_bean.CrearProveedor;


public class MetodoCrearProveedor {

	public void CrearProveedor(String nit,String muni,String telefono,String fax,String email,String contacto,String clase,String razon,String direccion, String observacion){
		/**
			 * creamos el articulo.
		 */
			
		    CrearProveedor ca = new CrearProveedor();
			
			ca.setnit(nit);
			//ca.setpais(pais);
			//ca.setciudad(ciudad);
			ca.setmuni(muni);
			ca.settelefono(telefono);
			ca.setfax(fax);
			ca.setemail(email);
			ca.setcontacto(contacto);
			ca.setclase(clase);
			ca.setrazon(razon);
			ca.setdireccion(direccion);
			ca.setobservacion(observacion);
			
			
			try{
					PreparedStatement ps = null;
				    Conexion con=new Conexion();                                                      
				    ps=con.conn.prepareStatement("insert into farc_proveedor(nit,municipio_fk,telefono,fax,email,contacto,clase_contribuyente,razon_social,direccion,comentario)values(?,?,?,?,?,?,?,?,?,?)");
				    ps.setString(1,ca.getnit());
				    ps.setString(2,ca.getmuni());
				    ps.setString(3,ca.gettelefono());
				    ps.setString(4,ca.getfax());
				    ps.setString(5,ca.getemail());
				    ps.setString(6,ca.getcontacto());
				    ps.setString(7,ca.getclase());
				    ps.setString(8,ca.getrazon());
				    ps.setString(9,ca.getdireccion());
				    ps.setString(10,ca.getobservacion());
				    ps.executeUpdate();
					ps.close();
					con.cerrar();				
				}catch(Exception ex){
		        	System.out.println("ERROR EN MetodoCrearProveedor>>CrearProveedor "+ex);
				}

			}
	


		public void CrearCorte(String HoraC, String usuario, Date Fecha, Time Hora){
		
		//	 * Creamos fecha de corte
			try{
					PreparedStatement ps = null;
				    Conexion con=new Conexion();                                                      
				    ps=con.conn.prepareStatement("insert into farc_corte_produccion (hora_corte,fecha,hora_creacion,cod_user)values(?,?,?,?)");
				    ps.setString(1,HoraC);
				    ps.setDate(2,Fecha);
				    ps.setTime(3,Hora);
				    ps.setString(4,usuario);
				    ps.executeUpdate();
					ps.close();
					con.cerrar();				
				}catch(Exception ex){
		        	System.out.println("ERROR EN MetodoCrearProveedor>>CrearCorte "+ex);
				}

			}



	public java.sql.ResultSet ObtenerCorteCreado(){	
		
		//Consulta q obtiene los cortes creados 

       java.sql.ResultSet rs=null;
       Statement st = null;
       try{
       	Conexion con=new Conexion();
       	st = con.conn.createStatement();
       	rs=st.executeQuery("SELECT fcp.hora_corte, CONCAT(sdp.nombre,' ',sdp.apellido) AS USUARIO FROM  farc_corte_produccion fcp, seg_usuario su, seg_datos_personales sdp "+
       						"WHERE fcp.cod_user=su.usu_codigo "+
       						"AND su.dat_codigo_fk=sdp.dat_codigo ");
       }
       catch(Exception ex){
       	System.out.println("Error en MetodoCrearProveedor>>ObtenerCorteCreado "+ex);
       }	
       return rs;
   	}


	public java.sql.ResultSet ObtenerGFarmacologico(){	
		
		//consulta que obtiene el codigo de la tabla paisesa, 

       java.sql.ResultSet rs=null;
       Statement st = null;
       try{
       	Conexion con=new Conexion();
       	st = con.conn.createStatement();
       	rs=st.executeQuery("select descripcion, ident_riesgo, cod_grupoFarmacologia from farc_grupofarmacologico where cod_grupoFarmacologia>0 order by descripcion");
       }
       catch(Exception ex){
       	System.out.println("Error en MetodoCrearProveedor>>ObtenerGFarmacologico "+ex);
       }	
       return rs;
   }



	public java.sql.ResultSet ObtenerGrupoF(String codGrupo){	
	
	//consulta que obtiene el codigo de la tabla paisesa, 

  	 java.sql.ResultSet rs=null;
  	 Statement st = null;
  	 try{
   		Conexion con=new Conexion();
   		st = con.conn.createStatement();
   		rs=st.executeQuery("select descripcion, ident_riesgo, cod_grupoFarmacologia from farc_grupofarmacologico where cod_grupoFarmacologia>0 and cod_grupoFarmacologia='"+codGrupo+"' order by descripcion");
 	  }
  	 catch(Exception ex){
   		System.out.println("Error en MetodoCrearProveedor>>ObtenerGrupoF "+ex);
  	 }	
  	 return rs;
	}


	public void ModGrupo(String codGrupo,String descripcion,String Iriesgo){
	/**
		 * Modifica el grupo farmacologico 
	 */
		try{
			System.out.println("codGrupo"+codGrupo);
			System.out.println("descripcion "+descripcion);
			PreparedStatement st = null;
			    Conexion con=new Conexion();                                                      
			  //  ps=con.conn.prepareStatement("insert into farc_proveedor(nit,municipio_fk,telefono,fax,email,contacto,clase_contribuyente,razon_social,direccion,comentario)values(?,?,?,?,?,?,?,?,?,?)");
			    st= con.conn.prepareStatement("update farc_grupofarmacologico set descripcion='"+descripcion+"', ident_riesgo='"+Iriesgo+"'  where cod_grupoFarmacologia='"+codGrupo+"'");
		    	System.out.println("update farc_grupofarmacologico set descripcion='"+descripcion+"', ident_riesgo='"+Iriesgo+"'  where cod_grupoFarmacologia='"+codGrupo+"'");
			    st.executeUpdate();
		    	st.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoCrearProveedor>>ModGrupo "+ex);
			}

		}

	public void CrearGrupo(String desc,String Iriesgo){
	/**
		 * Crea Grupo Farmacologico.
	 */
		
	    CrearGrupo cg = new CrearGrupo();
		
		cg.setdesc(desc);
		cg.setIriesgo(Iriesgo);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();                                                      
			    ps=con.conn.prepareStatement("insert into farc_grupofarmacologico(descripcion,ident_riesgo)values(?,?)");
			    ps.setString(1,cg.getdesc());
			    ps.setString(2,cg.getIriesgo());
			    ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoCrearProveedor>>CrearGrupo "+ex);
			}

		}

	
	public void CrearTipoMovimiento(String descripcion, String cod_movFk){
		
		//	 * creamos el articulo.
		
			
		    CrearProveedor ca = new CrearProveedor();
			

			ca.setTipoMovimiento(descripcion);
			ca.setOperacion(cod_movFk);
			
			
			try{
					PreparedStatement ps = null;
				    Conexion con=new Conexion();                                                      
				    ps=con.conn.prepareStatement("insert into farc_tipomovimiento(descripcion,cod_movFk)values(?,?)");
				    ps.setString(1,ca.getTipoMovimiento());
				    ps.setString(2,ca.getOperacion());
				    ps.executeUpdate();
					ps.close();
					con.cerrar();				
				}catch(Exception ex){
		        	System.out.println("ERROR EN MetodoCrearProveedor>>CrearTipoMovimiento "+ex);
				}

			}

	
	
	public void ModificarProveedor(String nit,String muni,String telefono,String fax,String email,String contacto,String clase,String razon,String direccion, String observacion, String c){
		/**
			 * creamos el articulo.
		 */
			try{
				PreparedStatement st = null;
				    Conexion con=new Conexion();                                                      
				  //  ps=con.conn.prepareStatement("insert into farc_proveedor(nit,municipio_fk,telefono,fax,email,contacto,clase_contribuyente,razon_social,direccion,comentario)values(?,?,?,?,?,?,?,?,?,?)");
				    st= con.conn.prepareStatement("update farc_proveedor set nit='"+nit+"', municipio_fk='"+muni+"', telefono='"+telefono+"', fax='"+fax+"', email='"+email+"', contacto='"+contacto+"', clase_contribuyente='"+clase+"', razon_social='"+razon+"', direccion='"+direccion+"', comentario='"+observacion+"' where codigo='"+c+"'");
			    	st.executeUpdate();
			    	st.close();
					con.cerrar();				
				}catch(Exception ex){
		        	System.out.println("ERROR EN MetodoCrearProveedor>>CrearProveedor "+ex);
				}

			}
	
		
		
	
	
	
	
	
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
       	System.out.println("Error en MetodoCrearProveedor>>ObtenerPaises "+ex);
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
       	System.out.println("Error en MetodoCrearProveedor>>ObtenerPaises "+ex);
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
       	System.out.println("Error en MetodoCrearProveedor>>ObtenerPaises "+ex);
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
       	System.out.println("Error en MetodoCrearProveedor>>ObtenerPaises "+ex);
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
       	System.out.println("Error en MetodoCrearProveedor>>ObtenerDpto "+ex);
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
       	System.out.println("Error en MetodoCrearProveedor>>ObtenerMuni "+ex);
       }	
       return rs;
   }
	
    public java.sql.ResultSet ObtenerProveedor(){	
		
		//consulta que obtiene el codigo de la tabla paisesa, 

       java.sql.ResultSet rs=null;
       Statement st = null;
       try{
       	Conexion con=new Conexion();
       	st = con.conn.createStatement();
       	rs=st.executeQuery("select nit, razon_social, codigo from farc_proveedor order by razon_social");
       }
       catch(Exception ex){
       	System.out.println("Error en MetodoCrearProveedor>>ObtenerProveedor "+ex);
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
       	rs=st.executeQuery("select nit, municipio_fk, telefono, fax, email, contacto, clase_contribuyente, razon_social, direccion, comentario from farc_proveedor where codigo='"+cod+"'");
       }
       catch(Exception ex){
       	System.out.println("Error en MetodoCrearProveedor>>ObtenerProveedor "+ex);
       }	
       return rs;
   }
    
    public java.sql.ResultSet ObtenerTipoMovimiento(){	
		
		//consulta que obtiene el codigo de la tabla paisesa, 

       java.sql.ResultSet rs=null;
       Statement st = null;
       try{
       	Conexion con=new Conexion();
       	st = con.conn.createStatement();
       	rs=st.executeQuery("select codigo, descripcion from cont_movimiento");
       }
       catch(Exception ex){
       	System.out.println("Error en MetodoCrearProveedor>>ObtenerTipoMovimiento "+ex);
       }	
       return rs;
   }
    
    public java.sql.ResultSet ObtenerTipoMovimiento(String x){	
		
		//consulta que obtiene el codigo de la tabla paisesa, 

       java.sql.ResultSet rs=null;
       Statement st = null;
       try{
       	Conexion con=new Conexion();
       	st = con.conn.createStatement();
       	rs=st.executeQuery("select descripcion from cont_movimiento where codigo='"+x+"'");
       }
       catch(Exception ex){
       	System.out.println("Error en MetodoCrearProveedor>>ObtenerTipoMovimiento "+ex);
       }	
       return rs;
   }
    
    public java.sql.ResultSet ObtenerMovimiento(){	
		
		//consulta que obtiene el codigo de la tabla paisesa, 

       java.sql.ResultSet rs=null;
       Statement st = null;
       try{
       	Conexion con=new Conexion();
       	st = con.conn.createStatement();
       	rs=st.executeQuery("select cod_movFk, descripcion from farc_tipomovimiento");
       }
       catch(Exception ex){
       	System.out.println("Error en MetodoCrearProveedor>>ObtenerMovimiento "+ex);
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
       	rs=st.executeQuery("select codigo from farc_tipomovimiento where descripcion ='"+x+"'");
       }
       catch(Exception ex){
       	System.out.println("Error en MetodoCrearProveedor>>ObtenerMovimiento "+ex);
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
    
}