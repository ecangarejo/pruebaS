package fact_metodo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import fact_bean.Tarifas;
import farc_bean.Entradas;

import adm_logica.Conexion;

public class MetodoProgramas {

	
	public java.sql.ResultSet ConsultarPrograma(){	
		
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" select p.cod_programa,fmb.cod_manual_base,fmb.descripcion,fnc.cod_niv_compl,fnc.descripcion,p.cod_referencia,p.cod_cups,p.descripcion,fe.cod_especialidad,fe.descripcion,fcs.cod_clase_servicio,fcs.descripcion,fsc.cod_subcentro_costo,fsc.descripcion, fr.cod_rip,fr.descripcion from fact_programas p, fact_manuales_base fmb, fact_niveles_complejidad fnc, fact_especialidades fe, fact_clases_servicio fcs, fact_subcentros_costo fsc, fact_rips fr where p.manual_base=fmb.cod_manual_base and fnc.cod_niv_compl=p.nivel_complejidad and fe.cod_especialidad=p.especialidad and fcs.cod_clase_servicio=p.clase_servicio and fsc.cod_subcentro_costo=p.subcentro_costo and fr.cod_rip=p.archivo_rip ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProgramas>>ConsultarPrograma "+ex);
        }	
        return rs;
    }
	
	
	
	public java.sql.ResultSet ConsultarProgramas(String SQL){	
		
		//consulta que obtiene el codigo de la tabla paisesa, 

	   java.sql.ResultSet rs=null;
	   Statement st = null;
	   try{
	   	Conexion con=new Conexion();
	   	st = con.conn.createStatement();
	   	rs=st.executeQuery(SQL);
	   }
	   catch(Exception ex){
	   	System.out.println("Error en MetodoProgramas>>ConsultarProgramas "+ex);
	   }	
	   return rs;
	}
	
	
	public java.sql.ResultSet ObtenerMbase(String nb){	
		/**
		 * consulta que obtiene las bodegas, 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select cod_manual_base,descripcion from fact_manuales_base where cod_manual_base!='"+nb+"' and cod_manual_base>0");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProgramas>>ObtenerMbase "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerMbase(){	
		/**
		 * consulta que obtiene las bodegas, 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select cod_manual_base,descripcion from fact_manuales_base where cod_manual_base>0");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProgramas>>ObtenerMbase "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerNivel(String nb){	
		/**
		 * consulta que obtiene las bodegas, 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select cod_niv_compl,descripcion from fact_niveles_complejidad where cod_niv_compl!='"+nb+"' and cod_niv_compl>0");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProgramas>>ObtenerNivel "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerNivel(){	
		/**
		 * consulta que obtiene las bodegas, 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select cod_niv_compl,descripcion from fact_niveles_complejidad where cod_niv_compl>0 ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProgramas>>ObtenerNivel "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerEspecialidad(String nb){	
		/**
		 * consulta que obtiene las bodegas, 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" select cod_especialidad,descripcion from fact_especialidades where cod_especialidad!='"+nb+"' and cod_especialidad>0");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProgramas>>ObtenerEspecialidad "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerEspecialidad(){	
		/**
		 * consulta que obtiene las bodegas, 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" select cod_especialidad,descripcion from fact_especialidades where cod_especialidad>0 ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProgramas>>ObtenerEspecialidad "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerServicio(String nb){	
		/**
		 * consulta que obtiene las bodegas, 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" select cod_clase_servicio,descripcion from fact_clases_servicio where cod_clase_servicio!='"+nb+"' and cod_clase_servicio>0");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProgramas>>ObtenerServicio "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerServicio(){	
		/**
		 * consulta que obtiene las bodegas, 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" select cod_clase_servicio,descripcion from fact_clases_servicio where cod_clase_servicio>0");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProgramas>>ObtenerServicio "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerSubcentro(String nb){	
		/**
		 * consulta que obtiene las bodegas, 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" select cod_subcentro_costo,descripcion from fact_subcentros_costo where cod_subcentro_costo!='"+nb+"' and cod_subcentro_costo>0");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProgramas>>ObtenerSubcentro "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerSubcentro(){	
		/**
		 * consulta que obtiene las bodegas, 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" select cod_subcentro_costo,descripcion from fact_subcentros_costo where cod_subcentro_costo>0 ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProgramas>>ObtenerSubcentro "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerRips(String nb){	
		/**
		 * consulta que obtiene las bodegas, 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" select cod_rip,descripcion from fact_rips where cod_rip!='"+nb+"' and cod_rip>0");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProgramas>>ObtenerRips "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerRips(){	
		/**
		 * consulta que obtiene las bodegas, 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" select cod_rip,descripcion from fact_rips where cod_rip>0 ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProgramas>>ObtenerRips "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerRn(String nb){	
		/**
		 * consulta que obtiene las bodegas, 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" select rn from fact_programas where rn!='"+nb+"' group by rn");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProgramas>>ObtenerRips "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet ObtenerProgramas(String SQL){	
		
		//consulta que obtiene el codigo de la tabla paisesa, 

	   java.sql.ResultSet rs=null;
	   Statement st = null;
	   try{
	   	Conexion con=new Conexion();
	   	st = con.conn.createStatement();
	   	rs=st.executeQuery(SQL);
	   }
	   catch(Exception ex){
	   	System.out.println("Error en MetodoProgramas>>ObtenerProgramas "+ex);
	   }	
	   return rs;
	}
	
	public void CrearPrograma(String mabase,String comp,String cref,String cups,String desc,String espe,String serv,String subc,String rips,String rn,String fecha,String hra,String user,String paq,String pme){
		
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("insert into fact_programas(manual_base,nivel_complejidad,cod_referencia,cod_cups,descripcion,especialidad,clase_servicio,subcentro_costo,archivo_rip,rn,fecha,hora,cod_usuario,actoqx,medico)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			    //ps.setString(1,ci.getbodega());
			    ps.setString(1,mabase);
			    ps.setString(2,comp);
			    ps.setString(3,cref);
			    ps.setString(4,cups);//hora
			    ps.setString(5,desc);//cod_usuario
			    ps.setString(6,espe);
			    ps.setString(7,serv);
			    ps.setString(8,subc);
			    ps.setString(9,rips);
			    ps.setString(10,rn);
			    ps.setString(11,fecha);
			    ps.setString(12,hra);
			    ps.setString(13,user);
			    ps.setString(14,paq);
			    ps.setString(15,pme);
			    ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoProgramas>>CrearPrograma "+ex);
			}

		}
	
		public java.sql.ResultSet ObtenerUProgramadeUsuario(String f, String h, String u){	
		 java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" select cod_programa from fact_programas where fecha='"+f+"' and hora='"+h+"' and cod_usuario='"+u+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProgramas>>ObtenerUProgramadeUsuario "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerCentrodeCosto(String c){	
		 java.sql.ResultSet rs=null;
       Statement st = null;
       try{
       	Conexion con=new Conexion();
       	st = con.conn.createStatement();
       	rs=st.executeQuery(" SELECT cod_centro_costo_fk FROM cont_centro_subcentro WHERE cod_sub_centro_costo_fk='"+c+"' ");
       }
       catch(Exception ex){
       	System.out.println("Error en MetodoProgramas>>ObtenerCentrodeCosto "+ex);
       }	
       return rs;
   }


public void CrearInterfazdeFacturacion(String c,String s,String p){
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("insert into cont_ninterfaz_facturacion(centrocostofk,subcentrofk,programafk)values(?,?,?)");
			    //ps.setString(1,ci.getbodega());
			    ps.setString(1,c);
			    ps.setString(2,s);
			    ps.setString(3,p);
			    ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoProgramas>>CrearInterfazdeFacturacion "+ex);
			}

		}

	
	public java.sql.ResultSet ConsultarInterfazFacturacion(String c, String s, String p){	
		 java.sql.ResultSet rs=null;
      Statement st = null;
      try{
      	Conexion con=new Conexion();
      	st = con.conn.createStatement();
      	rs=st.executeQuery(" SELECT codigo FROM cont_ninterfaz_facturacion WHERE centrocostofk='"+c+"' AND subcentrofk='"+s+"' AND programafk='"+p+"'  ");
      }
      catch(Exception ex){
      	System.out.println("Error en MetodoProgramas>>ConsultarInterfazFacturacion "+ex);
      }	
      return rs;
  }
	
	

	
	public void ModificarPExistente(String sql){
		
			try{
				PreparedStatement st = null;
				    Conexion con=new Conexion();                                                      
				  //  ps=con.conn.prepareStatement("insert into farc_proveedor(nit,municipio_fk,telefono,fax,email,contacto,clase_contribuyente,razon_social,direccion,comentario)values(?,?,?,?,?,?,?,?,?,?)");
				    st= con.conn.prepareStatement(sql);
			    	st.executeUpdate();
			    	st.close();
					con.cerrar();				
				}catch(Exception ex){
		        	System.out.println("ERROR EN MetodoProgramas>>ModificarPExistente "+ex);
			}

	}
	
	
	public void CrearServicio(String mabase,String comp,String cref,String desc,String subc){
		
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("insert into fact_paquetes(manual_base,nivel_complejidad,cod_referencia,descripcion,cod_subcentro_costo_fk)values(?,?,?,?,?)");
			    //ps.setString(1,ci.getbodega());
			    ps.setString(1,mabase);
			    ps.setString(2,comp);
			    ps.setString(3,cref);
			    ps.setString(4,desc);//cod_usuario
			    ps.setString(5,subc);
			    ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoProgramas>>CrearServicio "+ex);
			}

		}
	
	public ResultSet listarArticulos(String texto, String xx) throws Exception {
		/**
		 */
		  java.sql.ResultSet r=null;
	        Statement st = null;
	       Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	//cesar listar art pero de la tabla inv
	        	r=st.executeQuery(" select p.cod_programa, p.descripcion, p.especialidad, e.descripcion, p.clase_servicio, cs.descripcion from fact_programas p, fact_especialidades e, fact_clases_servicio cs where p.especialidad=e.cod_especialidad and p.clase_servicio=cs.cod_clase_servicio and p.manual_base='"+xx+"' and p.descripcion like '%"+texto+"%' ");
	       System.out.print(" select p.cod_programa, p.descripcion, p.especialidad, e.descripcion, p.clase_servicio, cs.descripcion from fact_programas p, fact_especialidades e, fact_clases_servicio cs where p.especialidad=e.cod_especialidad and p.clase_servicio=cs.cod_clase_servicio and p.manual_base='"+xx+"' and p.descripcion like '%"+texto+"%' ");
	        	return r;
	    }
	
	
	
	public void AsignarPrograma(String codp,String prog){
		
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("insert into fact_progs_paquetes (cod_paquete,cod_programa)values(?,?)");
			    //ps.setString(1,ci.getbodega());
			    ps.setString(1,prog);
			    ps.setString(2,codp);
			    ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoProgramas>>AsignarPrograma "+ex);
			}

		}
	
	public void EliminarPrograma(String codp){
		
			try{
				PreparedStatement st = null;
				    Conexion con=new Conexion();                                                      
				  //  ps=con.conn.prepareStatement("insert into farc_proveedor(nit,municipio_fk,telefono,fax,email,contacto,clase_contribuyente,razon_social,direccion,comentario)values(?,?,?,?,?,?,?,?,?,?)");
				    st= con.conn.prepareStatement("delete from fact_progs_paquetes where cod_pxp='"+codp+"' ");
			    	st.executeUpdate();
			    	st.close();
					con.cerrar();				
				}catch(Exception ex){
		        	System.out.println("ERROR EN MetodoProgramas>>EliminarPrograma "+ex);
			}

		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
}
