package cont_metodo;

import java.sql.PreparedStatement;
import java.sql.Statement;

import adm_logica.Conexion;

public class MetodoNomina {
	
	public java.sql.ResultSet ConsultarNomina(){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT * FROM cont_ndatosnomina WHERE estado=0  ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoNomina>>ConsultarNomina "+ex);
        }	
        return rs;
    }
	
	public void CrearDatosBasicos(String a,String b,String c,String d, String e,String f,String g,String h,String i, String j, String k){
		//System.out.print(cod_medico);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			 					
			    ps=con.conn.prepareStatement("INSERT INTO cont_ndatosnomina(anio,salariominimo,auxitransdiarioodia,auxitransdiarioonoche,pension,salud,fsp,arp,fechaactualizacion,horaactualizacion,usuario) VALUES(?,?,?,?,?,?,?,?,?,?,?)");
			   			    
			    ps.setString(1,a);
			    ps.setString(2,b);
			    ps.setString(3,c);
			    ps.setString(4,d);		
			    ps.setString(5,e);	
			    ps.setString(6,f);
			    ps.setString(7,g);
			    ps.setString(8,h);
			    ps.setString(9,i);		
			    ps.setString(10,j);	
			    ps.setString(11,k);	
			    
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN. MetodoNomina>>CrearDatosBasicos "+ex);
			}
		}
	
	
	public void ActualizarEstadoDatosBasicos(){
		/**
		 */
		 PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE cont_ndatosnomina SET estado='1'  WHERE estado='0' ");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoNomina>>ActualizarEstadoDatosBasicos "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	
	public java.sql.ResultSet ConsultarPliquidacion(){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT * FROM cont_nparametroliquidacion WHERE estado=0  ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoNomina>>ConsultarPliquidacion "+ex);
        }	
        return rs;
    }
	
	
	public void CrearPliquidacion(String a,String b,String c,String d, String e,String f,String g,String h,String i, String j, String k,String l,String m,String n,String o, String p, String q){
		//System.out.print(cod_medico);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			 					
			    ps=con.conn.prepareStatement("INSERT INTO cont_nparametroliquidacion(annio,dcesantias,dprimas,dvacaciones,dindemnizacionpa,dindemnizacionpaa,dindemnizacionpamid,dindemnizacionpaamid,pinteresescesantias,ptopedescuentop,pprovisionprimas,pprovisionvacaciones,pprovicioncesantias,pprovisionintiresescesantias,usuario,fecha,hora) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			   			    
			    ps.setString(1,a);
			    ps.setString(2,b);
			    ps.setString(3,c);
			    ps.setString(4,d);		
			    ps.setString(5,e);	
			    ps.setString(6,f);
			    ps.setString(7,g);
			    ps.setString(8,h);
			    ps.setString(9,i);		
			    ps.setString(10,j);	
			    ps.setString(11,k);	
			    ps.setString(12,l);
			    ps.setString(13,m);
			    ps.setString(14,n);
			    ps.setString(15,o);		
			    ps.setString(16,p);	
			    ps.setString(17,q);	
			    
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN. MetodoNomina>>CrearPliquidacion "+ex);
			}
		}
	
	
	public void ActualizarEstadoPliquidacion(){
		/**
		 */
		 PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE cont_nparametroliquidacion SET estado='1'  WHERE estado='0' ");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoNomina>>ActualizarEstadoDatosBasicos "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	
	public java.sql.ResultSet ConsultarUsuario(String p){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT d.nombre, d.apellido FROM seg_datos_personales d, seg_usuario u WHERE u.usu_codigo="+p+" AND d.dat_codigo=u.dat_codigo_fk" );
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoNomina>>ConsultarUsuario "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet ConsultarTiposdeConcepto(){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM cont_ntipoconcepto ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoNomina>>ConsultarTiposdeConcepto "+ex);
        }	
        return rs;
    }
	
	
	
	/*public void CrearTiposdeConcepto(String desc,String sigla,String cod_usuario,String fecha, String hora){
		//System.out.print(cod_medico);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			 					
			    ps=con.conn.prepareStatement("INSERT INTO cont_ntipoconcepto(descripcion,sigla,usuario,fecha,hora) VALUES(?,?,?,?,?)");
			   			    
			    ps.setString(1,desc);
			    ps.setString(2,sigla);
			    ps.setString(3,cod_usuario);
			    ps.setString(4,fecha);		
			    ps.setString(5,hora);	
			    
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN. MetodoNomina>>CrearTiposdeConcepto "+ex);
			}
		}*/
	
	
	public void CrearConceptos(String desc,String sigla,String cod_usuario,String fecha, String hora){
		//System.out.print(cod_medico);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			 					
			    ps=con.conn.prepareStatement("INSERT INTO cont_nconceptos(descripcion,tipoconceptofk,usuario,fecha,hora) VALUES(?,?,?,?,?)");
			   			    
			    ps.setString(1,desc);
			    ps.setString(2,sigla);
			    ps.setString(3,cod_usuario);
			    ps.setString(4,fecha);		
			    ps.setString(5,hora);	
			    
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN. MetodoNomina>>CrearConceptos "+ex);
			}
		}
	
	
	public java.sql.ResultSet ConsultarConceptos(){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT c.*,tc.descripcion FROM cont_nconceptos c, cont_ntipoconcepto tc WHERE c.tipoconceptofk=tc.codigo ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoNomina>>ConsultarConceptos "+ex);
        }	
        return rs;
    }

	public java.sql.ResultSet ConsultarModalidad(){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM cont_nmodalidades ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoNomina>>ConsultarModalidad "+ex);
        }	
        return rs;
    }

	
	public void CrearSubconceptos(String desc,String codc,String sigla,String clase,String mod,String pdistri,String tope,String orden,String aprest,String avaca,String asegs,String cod_usuario,String fecha, String hora){
		//System.out.print(cod_medico);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			 					
			    ps=con.conn.prepareStatement("INSERT INTO cont_nsubconceptos(descripcion,cod_conceptofk,sigla,clase,modalidadfk,pdistribucion,tope,orden,afectaprestaciones,afectavacaciones,afectasegsocial,usuario,fecha,hora) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			   			    
			    ps.setString(1,desc);
			    ps.setString(2,codc);
			    ps.setString(3,sigla);
			    ps.setString(4,clase);		
			    ps.setString(5,mod);  
			    ps.setString(6,pdistri);
			    ps.setString(7,tope);
			    ps.setString(8,orden);
			    ps.setString(9,aprest);		
			    ps.setString(10,avaca);	
			    ps.setString(11,asegs);
			    ps.setString(12,cod_usuario);
			    ps.setString(13,fecha);		
			    ps.setString(14,hora);	
			    
			    
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN. MetodoNomina>>CrearSubconceptos "+ex);
			}
		}
	
	
	public java.sql.ResultSet ConsultarSubconceptos(){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT s.*,c.descripcion,m.descripcion FROM cont_nsubconceptos s, cont_nconceptos c, cont_nmodalidades m WHERE s.cod_conceptofk=c.codigo AND s.modalidadfk=m.codigo ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoNomina>>ConsultarConceptos "+ex);
        }	
        return rs;
    }
	
	
	public void CrearProvisiones(String desc,String sigla){
		//System.out.print(cod_medico);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			 					
			    ps=con.conn.prepareStatement("INSERT INTO cont_ntiposprovisiones(descripcion,aplicaauxtransp) VALUES(?,?)");
			   			    
			    ps.setString(1,desc);
			    ps.setString(2,sigla);
			    	
			    
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN. MetodoNomina>>CrearProvisiones "+ex);
			}
		}
	
	
	public java.sql.ResultSet ConsultarTiposdeProvisiones(){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM cont_ntiposprovisiones ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoNomina>>ConsultarTiposdeProvisiones "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet ConsultarTiposDocumentos(){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT * FROM adm_tipodocumento");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoNomina>>ConsultarTiposDocumentos "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ConsultarTiposDocumentos(String c){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT * FROM adm_tipodocumento WHERE codigo='"+c+"' UNION SELECT *FROM adm_tipodocumento WHERE codigo!='"+c+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoNomina>>ConsultarTiposDocumentos "+ex);
        }	
        return rs;
    }
	

	
	
	public void CrearFondosYEPS(String a,String b,String c,String d, String e,String f,String g,String h,String i, String j, String k,String l,String m,String n,String o){
		//System.out.print(cod_medico);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			 					
			    ps=con.conn.prepareStatement("INSERT INTO cont_nfondosyoeps(nombre,tipodoc,numdoc,digitoverificacion,ppension,psalud,pfsp,parp,pcajacompensa,picbf,psena,cuenta,usuario,fecha,hora) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			   			    
			    ps.setString(1,a);
			    ps.setString(2,b);
			    ps.setString(3,c);
			    ps.setString(4,d);		
			    ps.setString(5,e);	
			    ps.setString(6,f);
			    ps.setString(7,g);
			    ps.setString(8,h);
			    ps.setString(9,i);		
			    ps.setString(10,j);	
			    ps.setString(11,k);	
			    ps.setString(12,l);
			    ps.setString(13,m);
			    ps.setString(14,n);
			    ps.setString(15,o);		
					  
			    
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN. MetodoNomina>>CrearFondosYEPS "+ex);
			}
		}
	
	
	public java.sql.ResultSet ConsultarFondosYEPS(){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT f.*, d.sigla FROM cont_nfondosyoeps f, adm_tipodocumento d WHERE f.estado='0' AND f.tipodoc=d.codigo ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoNomina>>ConsultarFondosYEPS "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet ConsultarPeriodosNomina(){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM cont_nperiodosliquidacion where estado='0' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoNomina>>ConsultarPeriodosNomina "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ConsultarPeriodosNomina(String c){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM cont_nperiodosliquidacion where codigo='"+c+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoNomina>>ConsultarPeriodosNomina "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ConsultarEmpleados(String texto){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT e.*,t.sigla,l.sueldo_diario FROM cont_nempleados e, adm_tipodocumento t, cont_nemp_infolaboral l  where estado='0' and e.tipo_doc=t.codigo AND e.codigo=l.cod_empleadofk and (primer_apellido LIKE '%"+texto+"%' or primer_nombre LIKE '%"+texto+"%') ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoNomina>>ConsultarEmpleados "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet ConsultarDevengados(String cm,String clase){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	//rs=st.executeQuery("SELECT s.*, t.codigo,t.descripcion,m.* FROM cont_nsubconceptos s, cont_nconceptos c, cont_ntipoconcepto t, cont_nmodalidades m WHERE s.cod_conceptofk=c.codigo AND c.tipoconceptofk=t.codigo AND s.modalidadfk=m.codigo AND t.codigo='2'  ");
        	rs=st.executeQuery("SELECT d.*,s.codigo,s.descripcion,s.cod_conceptofk,s.clase,s.pdistribucion,s.tope,s.orden,s.afectaprestaciones,s.afectavacaciones,s.afectasegsocial,m.* FROM cont_ndetallemovimientos d,cont_nsubconceptos s,cont_nconceptos c,cont_ntipoconcepto t,cont_nmodalidades m WHERE cod_nmovimientosfk='"+cm+"' AND s.codigo=d.cod_subconceptofk AND s.cod_conceptofk=c.codigo AND c.tipoconceptofk=t.codigo AND s.modalidadfk=m.codigo AND t.codigo='2' AND s.clase='"+clase+"' "); 

        }
        catch(Exception ex){
        	System.out.println("Error en MetodoNomina>>ConsultarDeduccionesDevengados "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ConsultarDeducciones(String cm,String clase){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	//rs=st.executeQuery(" SELECT s.*, t.codigo,t.descripcion,m.* FROM cont_nsubconceptos s, cont_nconceptos c, cont_ntipoconcepto t, cont_nmodalidades m WHERE s.cod_conceptofk=c.codigo AND c.tipoconceptofk=t.codigo AND s.modalidadfk=m.codigo AND t.codigo='1' ");
        	rs=st.executeQuery("SELECT d.*,s.codigo,s.descripcion,s.cod_conceptofk,s.clase,s.pdistribucion,s.tope,s.orden,s.afectaprestaciones,s.afectavacaciones,s.afectasegsocial,m.* FROM cont_ndetallemovimientos d,cont_nsubconceptos s,cont_nconceptos c,cont_ntipoconcepto t,cont_nmodalidades m WHERE cod_nmovimientosfk='"+cm+"' AND s.codigo=d.cod_subconceptofk AND s.cod_conceptofk=c.codigo AND c.tipoconceptofk=t.codigo AND s.modalidadfk=m.codigo AND t.codigo='1'  AND s.clase='"+clase+"' "); 

        }
        catch(Exception ex){
        	System.out.println("Error en MetodoNomina>>ConsultarDeduccionesDevengados "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ConsultarMovimientos(String p, String e){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM cont_nmovimientos WHERE periodo_fk='"+p+"' AND cod_empleadofk='"+e+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoNomina>>ConsultarMovimientos "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ConsultarDetalleMovimientos(String c){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT COUNT(*) FROM cont_ndetallemovimientos WHERE cod_nmovimientosfk='"+c+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoNomina>>ConsultarDetalleMovimientos "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ConsultarSubconceptosNomina(){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT COUNT(*) FROM cont_nsubconceptos ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoNomina>>ConsultarSubconceptosNomina "+ex);
        }	
        return rs;
    }
	
	
	public void CrearMovimiento(String p,String e,String f,String h, String u){
		//System.out.print(cod_medico);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			 					
			    ps=con.conn.prepareStatement("INSERT INTO cont_nmovimientos(periodo_fk,cod_empleadofk,fecha,hora,usuariofk) VALUES(?,?,?,?,?)");
			   			    
			    ps.setString(1,p);
			    ps.setString(2,e);
			    ps.setString(3,f);
			    ps.setString(4,h);		
			    ps.setString(5,u);	
			    
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN. MetodoNomina>>CrearMovimiento "+ex);
			}
		}
	
	public void CrearDetalleMovimiento(String cm,String c,String v,String s){
		//System.out.print(cod_medico);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			 					
			    ps=con.conn.prepareStatement("INSERT INTO cont_ndetallemovimientos(cod_nmovimientosfk,cantidad,valoru,cod_subconceptofk) VALUES(?,?,?,?)");
			   			    
			    ps.setString(1,cm);
			    ps.setString(2,c);
			    ps.setString(3,v);
			    ps.setString(4,s);		
	
			    
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN. MetodoNomina>>CrearDetalleMovimiento "+ex);
			}
		}
	
	public java.sql.ResultSet ConsultarCodigosSubconceptos(){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("  SELECT codigo FROM cont_nsubconceptos ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoNomina>>ConsultarCodigosSubconceptos "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ConsultarMovimientosSubconceptos(String c){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("  SELECT codigo FROM cont_nsubconceptos WHERE codigo NOT IN (SELECT cod_subconceptofk FROM cont_ndetallemovimientos WHERE cod_nmovimientosfk='"+c+"') ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoNomina>>ConsultarMovimientosSubconceptos "+ex);
        }	
        return rs;
    }
	

	public void ActualizarNdetallemovimientos(String c,String vu,String vt,String cm){
		/**
		 */
		 PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE cont_ndetallemovimientos SET valoru='"+vu+"',cantidad='"+c+"',valort='"+vt+"' WHERE codigo='"+cm+"' ");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoNomina>>ActualizarNdetallemovimientos "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	
	public java.sql.ResultSet ConsultarSumMovimientos(String m, String c, String tc){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("  SELECT SUM(d.valort) FROM cont_nmovimientos m, cont_ndetallemovimientos d,cont_nsubconceptos s, cont_nconceptos c WHERE m.codigo='"+m+"' AND m.codigo=d.cod_nmovimientosfk AND d.cod_subconceptofk=s.codigo AND s.clase='"+c+"' AND s.cod_conceptofk=c.codigo AND c.tipoconceptofk='"+tc+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoNomina>>ConsultarSumMovimientos "+ex);
        }	
        return rs;
    }
	
	public void ActualizarNTotalesNovedades(String vt,String cm,String dod){
		/**
		 */
		 PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	
	     	
	     	if(dod.equals("2")){
	     	st= con.conn.prepareStatement("UPDATE cont_nmovimientos SET devengadosnovedades='"+vt+"' WHERE codigo='"+cm+"' ");
	     	}
	     	if(dod.equals("1")){
		    st= con.conn.prepareStatement("UPDATE cont_nmovimientos SET deduccionesnovedades='"+vt+"' WHERE codigo='"+cm+"' ");
		    }
	     	
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoNomina>>ActualizarNTotalesNovedades "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	
	public void ActualizarNTotalesFijas(String vt,String cm,String dod,String dt){
		/**
		 */
		 PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	
	     	if(dod.equals("2")){
	     	st= con.conn.prepareStatement("UPDATE cont_nmovimientos SET devengadosfijos='"+vt+"' ,diastrabajados='"+dt+"'  WHERE codigo='"+cm+"' ");
	     	}
	     	if(dod.equals("1")){
		    st= con.conn.prepareStatement("UPDATE cont_nmovimientos SET deduccionesfijas='"+vt+"' ,diastrabajados='"+dt+"' WHERE codigo='"+cm+"' ");
		    }
	     	
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoNomina>>ActualizarNTotalesFijas "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	
	/////////////////EMPLEADOS/////////////////
	public java.sql.ResultSet ConsultarCentrosdeCosto(){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT * FROM fact_centrocosto");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoNomina>>ConsultarCentrosdeCosto "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ConsultarCentrosdeCosto(String c){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT * FROM fact_centrocosto WHERE codigo='"+c+"' UNION SELECT * FROM fact_centrocosto WHERE codigo!='"+c+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoNomina>>ConsultarCentrosdeCosto "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ConsultarSubCentrosdeCosto(String cc){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT s.* FROM fact_subcentros_costo s,cont_centro_subcentro cs WHERE s.cod_subcentro_costo=cod_sub_centro_costo_fk AND cod_centro_costo_fk='"+cc+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoNomina>>ConsultarCentrosdeCosto "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ConsultarSubCentrosdeCosto(String cc, String sc){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT * FROM fact_subcentros_costo WHERE cod_subcentro_costo='"+sc+"' UNION SELECT s.* FROM fact_subcentros_costo s,cont_centro_subcentro cs WHERE s.cod_subcentro_costo=cod_sub_centro_costo_fk AND cod_centro_costo_fk='"+cc+"'  AND cod_subcentro_costo!='"+sc+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoNomina>>ConsultarCentrosdeCosto "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ConsultarClaseEmpleado(){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT * FROM cont_nclaseempleado");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoNomina>>ConsultarClaseEmpleado "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ConsultarClaseEmpleado(String c){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT * FROM cont_nclaseempleado WHERE codigo="+c+" UNION SELECT * FROM cont_nclaseempleado WHERE codigo!="+c+" ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoNomina>>ConsultarClaseEmpleado "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ConsultarTiposdeContrato(){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT * FROM cont_ntipocontrato");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoNomina>>ConsultarTiposdeContrato "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet ConsultarTiposdeContrato(String c){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT * FROM cont_ntipocontrato WHERE codigo="+c+" UNION SELECT * FROM cont_ntipocontrato WHERE codigo!="+c+" ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoNomina>>ConsultarTiposdeContrato "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet ConsultarTiposdeCuenta(){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT * FROM cont_ntipodecuentas");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoNomina>>ConsultarTiposdeCuenta "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ConsultarTiposdeCuenta(String c){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT * FROM cont_ntipodecuentas WHERE codigo="+c+" UNION SELECT * FROM cont_ntipodecuentas WHERE codigo!="+c+" ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoNomina>>ConsultarTiposdeCuenta "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet ConsultarFormasdePago(){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT * FROM cont_nformasdepago");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoNomina>>ConsultarFormasdePago "+ex);
        }	
        return rs;
    }
	 
	 public java.sql.ResultSet ConsultarFormasdePago(String c){	
			java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery(" SELECT * FROM cont_nformasdepago WHERE codigo="+c+" UNION SELECT * FROM cont_nformasdepago WHERE codigo!="+c+" ");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoNomina>>ConsultarFormasdePago "+ex);
	        }	
	        return rs;
	    }
	 
	 
	public java.sql.ResultSet ConsultarEscolaridad(){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT * FROM cont_nescolaridad");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoNomina>>ConsultarEscolaridad "+ex);
        }	
        return rs;
    }
	 
		public java.sql.ResultSet ConsultarEscolaridad(String c){	
			java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery(" SELECT * FROM cont_nescolaridad WHERE codigo="+c+" UNION SELECT * FROM cont_nescolaridad WHERE codigo!="+c+" ");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoNomina>>ConsultarEscolaridad "+ex);
	        }	
	        return rs;
	    }
	 
	 
	public java.sql.ResultSet ConsultarGrupoSAnguineo(){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT * FROM cont_ngruposanguineo");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoNomina>>ConsultarGrupoSAnguineo "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ConsultarGrupoSAnguineo(String c){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT * FROM cont_ngruposanguineo  WHERE codigo="+c+" UNION SELECT * FROM cont_ngruposanguineo WHERE codigo!="+c+" ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoNomina>>ConsultarGrupoSAnguineo "+ex);
        }	
        return rs;
    }
	
	
	public void CrearEmpleados(String a,String b,String c,String d, String e,String f,String g,String h,String i, String j, String k,String l,String m,String n,String o,String p,String q){
		//System.out.print(cod_medico);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			 					
			    ps=con.conn.prepareStatement("INSERT INTO cont_nempleados(tipo_doc,num_doc,primer_apellido,segundo_apellido,primer_nombre,segundo_nombre,direccion,telefono,centrocosto_fk,subcentro_fk,estadocivil_fk,sexo,fecha_nacimiento,estado,usuario,fecha,hora) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			   			    
			    ps.setString(1,a);
			    ps.setString(2,b);
			    ps.setString(3,c);
			    ps.setString(4,d);		
			    ps.setString(5,e);	
			    ps.setString(6,f);
			    ps.setString(7,g);
			    ps.setString(8,h);
			    ps.setString(9,i);		
			    ps.setString(10,j);	
			    ps.setString(11,k);	
			    ps.setString(12,l);
			    ps.setString(13,m);
			    ps.setString(14,n);
			    ps.setString(15,o);
			    ps.setString(16,p);
			    ps.setString(17,q);
		    
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN. MetodoNomina>>CrearEmpleados "+ex);
			}
		}
	
	public java.sql.ResultSet ConsultarCodEmpleado(String u, String f, String h){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT codigo FROM cont_nempleados WHERE usuario='"+u+"' and fecha='"+f+"' and hora='"+h+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoNomina>>ConsultarCodEmpleado "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ConsultarCodEmpleados(){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT codigo FROM cont_nempleados ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoNomina>>ConsultarCodEmpleados "+ex);
        }	
        return rs;
    }
	
	public void CrearEmpleados2(String a,String b,String c,String d, String e,String f,String g,String h,String i, String j, String k){
		//System.out.print(cod_medico);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			 					
			    ps=con.conn.prepareStatement("INSERT INTO cont_nemp_infolaboral(cod_empleadofk,cargo,clase_empleadofk,contratofk,fechainicial,tipo_cuentafk,numero_cuenta,formadepago_fk,sueldo_diario,fecha_aumento,fecha_retiro) VALUES(?,?,?,?,?,?,?,?,?,?,?)");
			   			    
			    ps.setString(1,a);
			    ps.setString(2,b);
			    ps.setString(3,c);
			    ps.setString(4,d);		
			    ps.setString(5,e);	
			    ps.setString(6,f);
			    ps.setString(7,g);
			    ps.setString(8,h);
			    ps.setString(9,i);		
			    ps.setString(10,j);	
			    ps.setString(11,k);	
		
			
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN. MetodoNomina>>CrearEmpleados2 "+ex);
			}
		}
	//
	
	public void CrearEmpleados3(String a,String b,String c,String d, String e,String f,String g,String h,String i, String j){
		//System.out.print(cod_medico);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			 					
			    ps=con.conn.prepareStatement("INSERT INTO cont_nemp_infosalud(cod_empleadofk,cod_arpfk,cod_epsfk,cod_cajacompensacionfk,cod_fondocesantiasfk,cod_pensionfk,cod_pensionvoluntariafk,cod_icbffk,cod_senafk,auxilio) VALUES(?,?,?,?,?,?,?,?,?,?)");
			   			    
			    ps.setString(1,a);
			    ps.setString(2,b);
			    ps.setString(3,c);
			    ps.setString(4,d);		
			    ps.setString(5,e);	
			    ps.setString(6,f);
			    ps.setString(7,g);
			    ps.setString(8,h);
			    ps.setString(9,i);		
			    ps.setString(10,j);	
				
			
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN. MetodoNomina>>CrearEmpleados3 "+ex);
			}
		}
	
	//
	public void CrearEmpleados4(String a,String b,String c,String d, String e,String f,String g,String h){
		//System.out.print(cod_medico);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			 					
			    ps=con.conn.prepareStatement("INSERT INTO cont_nemp_infoadicional(cod_empleadofk,escolaridadfk,gruposanguineofk,tallacamisa,tallapantalon,tallazapatos,email,hijos) VALUES(?,?,?,?,?,?,?,?)");
			   			    
			    ps.setString(1,a);
			    ps.setString(2,b);
			    ps.setString(3,c);
			    ps.setString(4,d);		
			    ps.setString(5,e);	
			    ps.setString(6,f);
			    ps.setString(7,g);
			    ps.setString(8,h);
		
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN. MetodoNomina>>CrearEmpleados4 "+ex);
			}
		}
	
	
	public java.sql.ResultSet ConsultarAuxilioTransporte(){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT auxitransdiarioodia FROM cont_ndatosnomina WHERE estado='0' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoNomina>>ConsultarAuxilioTransporte "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ConsultarSueldoDiario(String e){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT sueldo_diario FROM cont_nemp_infolaboral WHERE cod_empleadofk='"+e+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoNomina>>ConsultarSueldoDiario "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ConsultarDiastrabajados(String e){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT diastrabajados FROM cont_nmovimientos WHERE codigo='"+e+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoNomina>>ConsultarDiastrabajados "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ConsultarTotalDevengados(String e){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT devengadosfijos+devengadosnovedades FROM cont_nmovimientos WHERE codigo='"+e+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoNomina>>ConsultarTotalDevengados "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet ConsultarEInformacion(String e){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT * FROM cont_nempleados where codigo='"+e+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoNomina>>ConsultarEInformacion "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ConsultarEInformacionLaboral(String e){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT * FROM cont_nemp_infolaboral WHERE cod_empleadofk='"+e+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoNomina>>ConsultarEInformacionLaboral "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ConsultarEInformacionSalud(String e){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT * FROM cont_nemp_infosalud WHERE cod_empleadofk='"+e+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoNomina>>ConsultarEInformacionSalud "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ConsultarEInformacionAdicional(String e){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT * FROM cont_nemp_infoadicional WHERE cod_empleadofk='"+e+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoNomina>>ConsultarEInformacionAdicional "+ex);
        }	
        return rs;
    }
	
	public void ActualizarEmpleado(String sql){
		/**
		 */
		 PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement(sql);
	     		     	
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoNomina>>ActualizarEmpleado "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	//////////////////////////PRELIQUIDACION DE NOMINA/////////////////////
	public java.sql.ResultSet ConsultarEmpleadosxCentroNovedades(String c,String s,String p){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	
           	//rs=st.executeQuery(" SELECT SUM(d.valoru),e.codigo,e.num_doc,e.primer_apellido,e.segundo_apellido,e.primer_nombre,e.segundo_nombre,m.devengadosfijos,m.devengadosnovedades,m.deduccionesfijas,m.deduccionesnovedades FROM cont_nempleados e, cont_nmovimientos m, cont_ndetallemovimientos d, cont_nsubconceptos s  WHERE centrocosto_fk='"+c+"' AND subcentro_fk='"+s+"' AND e.estado='0' AND e.codigo=m.cod_empleadofk AND m.periodo_fk='"+p+"' AND m.codigo=d.cod_nmovimientosfk AND d.cod_subconceptofk=s.codigo AND s.clase='0' GROUP BY e.codigo "); 
        	rs=st.executeQuery(" SELECT e.codigo,e.num_doc,e.primer_apellido,e.segundo_apellido,e.primer_nombre,e.segundo_nombre,m.devengadosfijos,m.devengadosnovedades,m.deduccionesfijas,m.deduccionesnovedades FROM cont_nempleados e, cont_nmovimientos m WHERE  centrocosto_fk='"+c+"' AND subcentro_fk='"+s+"' AND e.estado='0' AND e.codigo=m.cod_empleadofk AND m.periodo_fk='"+p+"' "); 

        }
        catch(Exception ex){
        	System.out.println("Error en MetodoNomina>>ConsultarEmpleadosxCentroNovedades "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ConsultarEmpleadosParaProvisiones(String c,String s,String p){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	
           	//rs=st.executeQuery(" SELECT SUM(d.valoru),e.codigo,e.num_doc,e.primer_apellido,e.segundo_apellido,e.primer_nombre,e.segundo_nombre,m.devengadosfijos,m.devengadosnovedades,m.deduccionesfijas,m.deduccionesnovedades FROM cont_nempleados e, cont_nmovimientos m, cont_ndetallemovimientos d, cont_nsubconceptos s  WHERE centrocosto_fk='"+c+"' AND subcentro_fk='"+s+"' AND e.estado='0' AND e.codigo=m.cod_empleadofk AND m.periodo_fk='"+p+"' AND m.codigo=d.cod_nmovimientosfk AND d.cod_subconceptofk=s.codigo AND s.clase='0' GROUP BY e.codigo "); 
        	rs=st.executeQuery(" SELECT m.codigo,e.codigo,m.devengadosfijos,m.devengadosnovedades,m.deduccionesfijas,m.deduccionesnovedades,m.diastrabajados FROM cont_nempleados e, cont_nmovimientos m  WHERE  centrocosto_fk='"+c+"' AND subcentro_fk='"+s+"' AND e.estado='0' AND e.codigo=m.cod_empleadofk AND m.periodo_fk='"+p+"' "); 

        }
        catch(Exception ex){
        	System.out.println("Error en MetodoNomina>>ConsultarEmpleadosParaProvisiones "+ex);
        }	
        return rs;
    }
	
	
	public void CrearProvision(String a,String b,String c,String d, String e,String f,String g,String h,String i, String j, String k, String l, String m){
		//System.out.print(cod_medico);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			 					
			    ps=con.conn.prepareStatement("INSERT INTO cont_nprovisiones(codmovimientofk,periodofk,codempleadofk,salud,pension,cesantias,intereses,primas,vacaciones,cajadecompensacion,icbf,sena,arp) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");
			   			    
			    ps.setString(1,a);
			    ps.setString(2,b);
			    ps.setString(3,c);
			    ps.setString(4,d);		
			    ps.setString(5,e);	
			    ps.setString(6,f);
			    ps.setString(7,g);
			    ps.setString(8,h);
			    ps.setString(9,i);		
			    ps.setString(10,j);	
			    ps.setString(11,k);	
			    ps.setString(12,l);	
			    ps.setString(13,m);	
			
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN. MetodoNomina>>CrearProvision "+ex);
			}
		}
	
	
	public java.sql.ResultSet ConsultarSubConceptosNomina(String e){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT s.codigo,s.descripcion FROM cont_nsubconceptos s,cont_nconceptos c WHERE s.cod_conceptofk=c.codigo AND c.tipoconceptofk='"+e+"'  ORDER BY s.descripcion ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoNomina>>ConsultarSubConceptosNomina "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ConsultarInterfazdeNomina(String e, String c, String s){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT i.codigo,c.CodigoCuenta,c.NombreCuenta FROM cont_ninterfaz_nomina i, cont_cuentas c WHERE i.subconceptofk='"+e+"'  AND i.centrocostofk='"+c+"' AND i.subcentrofk='"+s+"'  AND i.cuentafk=c.codigo ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoNomina>>ConsultarInterfazdeNomina "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet NConsultarCuentasContables(String e){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT codigo,CodigoCuenta,NombreCuenta FROM cont_cuentas WHERE CodigoCuenta LIKE '"+e+"%'  LIMIT 100  ");
        	//System.out.println(" wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww SELECT codigo,CodigoCuenta,NombreCuenta FROM cont_cuentas WHERE CodigoCuenta LIKE '"+e+"%' ");
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoNomina>>NConsultarCuentasContables "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ConsultarInterfazN(String cc, String sc, String s){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT codigo FROM cont_ninterfaz_nomina WHERE centrocostofk="+cc+" and subcentrofk="+sc+" and subconceptofk="+s+"  ");
        	//System.out.println(" SELECT codigo,CodigoCuenta,NombreCuenta FROM cont_cuentas WHERE CodigoCuenta LIKE '"+e+"%' ");
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoNomina>>ConsultarInterfazN "+ex);
        }	
        return rs;
    }
	
	public void CrearInterfazN(String a,String b,String c,String d, String e,String f,String g){
		//System.out.print(cod_medico);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			 					
			    ps=con.conn.prepareStatement("INSERT INTO cont_ninterfaz_nomina(centrocostofk,subcentrofk,subconceptofk,cuentafk,fecha,hora,usuario) VALUES(?,?,?,?,?,?,?)");
			   			    
			    ps.setString(1,a);
			    ps.setString(2,b);
			    ps.setString(3,c);
			    ps.setString(4,d);		
			    ps.setString(5,e);	
			    ps.setString(6,f);
			    ps.setString(7,g);
						
			
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN. MetodoNomina>>CrearInterfazN "+ex);
			}
		}
	
	public void ActualizarInterfazN(String cod,String c,String f, String h, String u){
		/**
		 */
		 PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE cont_ninterfaz_nomina SET cuentafk='"+c+"' , fecha='"+f+"', hora='"+h+"', usuario='"+u+"' WHERE codigo='"+cod+"' ");
	     		     	
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoNomina>>ActualizarInterfazN "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	
	public java.sql.ResultSet ConsultarProvisionesNomina(){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("  SELECT * FROM cont_nprovisionesdenomina ORDER BY descripcion ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoNomina>>ConsultarProvisionesNomina "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ConsultarInterfazdeProvisiones(String e, String c, String s){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	/*rs=st.executeQuery(" SELECT i.codigo,c.CodigoCuenta,c.NombreCuenta ," +
        			"(SELECT c.CodigoCuenta FROM cont_ninterfaz_provisiones i, cont_cuentas c WHERE i.provision='"+e+"'  AND i.centrocostofk='"+c+"' AND i.subcentrofk='"+s+"'  AND i.cuentagfk=c.codigo ) AS gastos ," +
        			"(SELECT c.NombreCuenta FROM cont_ninterfaz_provisiones i, cont_cuentas c WHERE i.provision='"+e+"'  AND i.centrocostofk='"+c+"' AND i.subcentrofk='"+s+"'  AND i.cuentagfk=c.codigo ) AS ngastos " +
        		                        "FROM cont_ninterfaz_provisiones i, cont_cuentas c  WHERE i.provision='"+e+"'  AND i.centrocostofk='"+c+"' AND i.subcentrofk='"+s+"'  AND i.cuentapfk=c.codigo ");
        	*/
        	rs=st.executeQuery(" SELECT i.codigo, " +
        			"IFNULL((SELECT c.CodigoCuenta FROM cont_ninterfaz_provisiones i, cont_cuentas c WHERE i.provision='"+e+"'  AND i.centrocostofk='"+c+"' AND i.subcentrofk='"+s+"'  AND i.cuentapfk=c.codigo ),'') AS prov ,  " +
        			"IFNULL((SELECT c.NombreCuenta FROM cont_ninterfaz_provisiones i, cont_cuentas c WHERE i.provision='"+e+"'  AND i.centrocostofk='"+c+"' AND i.subcentrofk='"+s+"'  AND i.cuentapfk=c.codigo ),'') AS nprov,  " +
        			"IFNULL((SELECT c.CodigoCuenta FROM cont_ninterfaz_provisiones i, cont_cuentas c WHERE i.provision='"+e+"'  AND i.centrocostofk='"+c+"' AND i.subcentrofk='"+s+"'  AND i.cuentagfk=c.codigo ),'') AS gastos ,  " +
        			"IFNULL((SELECT c.NombreCuenta FROM cont_ninterfaz_provisiones i, cont_cuentas c WHERE i.provision='"+e+"'  AND i.centrocostofk='"+c+"' AND i.subcentrofk='"+s+"'  AND i.cuentagfk=c.codigo ),'') AS ngastos   " +
        			"                                       FROM cont_ninterfaz_provisiones i WHERE i.provision='"+e+"'  AND i.centrocostofk='"+c+"' AND i.subcentrofk='"+s+"' ");
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoNomina>>ConsultarInterfazdeNomina "+ex);
        }	
        return rs;
    }
	

	public java.sql.ResultSet ConsultarInterfazP(String cc, String sc, String s){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT codigo FROM cont_ninterfaz_provisiones WHERE centrocostofk="+cc+" and subcentrofk="+sc+" and provision="+s+"  ");
        	//System.out.println(" SELECT codigo,CodigoCuenta,NombreCuenta FROM cont_cuentas WHERE CodigoCuenta LIKE '"+e+"%' ");
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoNomina>>ConsultarInterfazP "+ex);
        }	
        return rs;
    }
	
	public void CrearInterfazP(String ind,String a,String b,String c,String d, String e,String f,String g){
		//System.out.print(cod_medico);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			 	
			    if(ind.equals("2")){ps=con.conn.prepareStatement("INSERT INTO cont_ninterfaz_provisiones(centrocostofk,subcentrofk,provision,cuentapfk,fecha,hora,usuario) VALUES(?,?,?,?,?,?,?)");}
			    if(ind.equals("3")){ps=con.conn.prepareStatement("INSERT INTO cont_ninterfaz_provisiones(centrocostofk,subcentrofk,provision,cuentagfk,fecha,hora,usuario) VALUES(?,?,?,?,?,?,?)");}
				
			    System.out.println("INDICE "+ind+" INSERT INTO cont_ninterfaz_provisiones(centrocostofk,subcentrofk,provision,cuentagfk,fecha,hora,usuario) VALUES("+a+","+b+","+c+","+d+","+e+","+f+","+g+")");	     	
				   
			    
			    ps.setString(1,a);
			    ps.setString(2,b);
			    ps.setString(3,c);
			    ps.setString(4,d);		
			    ps.setString(5,e);	
			    ps.setString(6,f);
			    ps.setString(7,g);
						
			
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN. MetodoNomina>>CrearInterfazP "+ex);
			}
		}
	
	public void ActualizarInterfazP(String ind,String cod,String c,String f, String h, String u){
		/**
		 */
		 PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	
	     	if(ind.equals("2")){st= con.conn.prepareStatement("UPDATE cont_ninterfaz_provisiones SET cuentapfk='"+c+"' , fecha='"+f+"', hora='"+h+"', usuario='"+u+"' WHERE codigo='"+cod+"' ");}
	     	if(ind.equals("3")){st= con.conn.prepareStatement("UPDATE cont_ninterfaz_provisiones SET cuentagfk='"+c+"' , fecha='"+f+"', hora='"+h+"', usuario='"+u+"' WHERE codigo='"+cod+"' ");}	     	
	     	
	     	System.out.println("INDICE "+ind+" UPDATE cont_ninterfaz_provisiones SET cuentagfk='"+c+"' , fecha='"+f+"', hora='"+h+"', usuario='"+u+"' WHERE codigo='"+cod+"' ");	     	
		     	
	     	
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoNomina>>ActualizarInterfazP "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public java.sql.ResultSet ConsultarProgramasdeFacturacion(String a){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT cod_programa,descripcion,especialidad,clase_servicio FROM fact_programas  WHERE descripcion LIKE '"+a+"%' ORDER BY descripcion ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoNomina>>ConsultarProgramasdeFacturacion "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ConsultarInterfazdeFacturacion1(String e, String c, String s){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT i.codigo,c.CodigoCuenta,c.NombreCuenta FROM cont_ninterfaz_facturacion i, cont_cuentas c WHERE i.programafk='"+e+"'  AND i.centrocostofk='"+c+"' AND i.subcentrofk='"+s+"'  AND i.cuentaifk=c.codigo ");
        	/*rs=st.executeQuery(" SELECT i.codigo,c.CodigoCuenta,c.NombreCuenta" +
        			",(SELECT c.CodigoCuenta " +
        			"FROM cont_ninterfaz_facturacion i, cont_cuentas c " +
        			"WHERE i.programafk='"+e+"'  AND i.centrocostofk='"+c+"' AND i.subcentrofk='"+s+"'  AND i.cuentacfk=c.codigo ) AS costo " +
        			",(SELECT c.NombreCuenta " +
        			"FROM cont_ninterfaz_facturacion i, cont_cuentas c " +
        			"WHERE i.programafk='"+e+"'  AND i.centrocostofk='"+c+"' AND i.subcentrofk='"+s+"'  AND i.cuentacfk=c.codigo ) AS nombrecosto " +
        			",(SELECT c.CodigoCuenta " +
        			"FROM cont_ninterfaz_facturacion i, cont_cuentas c " +
        			"WHERE i.programafk='"+e+"'  AND i.centrocostofk='"+c+"' AND i.subcentrofk='"+s+"'  AND i.cuentagfk=c.codigo ) AS inventario " +
        			",(SELECT c.NombreCuenta " +
        			"FROM cont_ninterfaz_facturacion i, cont_cuentas c " +
        			"WHERE i.programafk='"+e+"'  AND i.centrocostofk='"+c+"' AND i.subcentrofk='"+s+"'  AND i.cuentagfk=c.codigo ) AS nombreinventario " +
        			"FROM cont_ninterfaz_facturacion i, cont_cuentas c " +
        			"WHERE i.programafk='"+e+"'  AND i.centrocostofk='"+c+"' AND i.subcentrofk='"+s+"'  AND i.cuentaifk=c.codigo   ");
        			*/
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoNomina>>ConsultarInterfazdeFacturacion1 "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ConsultarInterfazdeFacturacion2(String e, String c, String s){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT i.codigo,c.CodigoCuenta,c.NombreCuenta FROM cont_ninterfaz_facturacion i, cont_cuentas c WHERE i.programafk='"+e+"'  AND i.centrocostofk='"+c+"' AND i.subcentrofk='"+s+"'  AND i.cuentacfk=c.codigo ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoNomina>>ConsultarInterfazdeFacturacion2 "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ConsultarInterfazdeFacturacion3(String e, String c, String s){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT i.codigo,c.CodigoCuenta,c.NombreCuenta FROM cont_ninterfaz_facturacion i, cont_cuentas c WHERE i.programafk='"+e+"'  AND i.centrocostofk='"+c+"' AND i.subcentrofk='"+s+"'  AND i.cuentagfk=c.codigo ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoNomina>>ConsultarInterfazdeFacturacion3 "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ConsultarInterfazF(String cc, String sc, String s){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT codigo FROM cont_ninterfaz_facturacion WHERE centrocostofk="+cc+" and subcentrofk="+sc+" and programafk="+s+"  ");
        	//System.out.println(" SELECT codigo,CodigoCuenta,NombreCuenta FROM cont_cuentas WHERE CodigoCuenta LIKE '"+e+"%' ");
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoNomina>>ConsultarInterfazF "+ex);
        }	
        return rs;
    }
	
	public void CrearInterfazF(String a,String b,String c,String d, String e,String f,String g,String icg){
		//System.out.print(cod_medico);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			 	
			    if(icg.equals("1")){ ps=con.conn.prepareStatement("INSERT INTO cont_ninterfaz_facturacion(centrocostofk,subcentrofk,programafk,cuentaifk,fecha,hora,usuario) VALUES(?,?,?,?,?,?,?)");}
			    if(icg.equals("2")){ ps=con.conn.prepareStatement("INSERT INTO cont_ninterfaz_facturacion(centrocostofk,subcentrofk,programafk,cuentacfk,fecha,hora,usuario) VALUES(?,?,?,?,?,?,?)");}
			    if(icg.equals("3")){ ps=con.conn.prepareStatement("INSERT INTO cont_ninterfaz_facturacion(centrocostofk,subcentrofk,programafk,cuentagfk,fecha,hora,usuario) VALUES(?,?,?,?,?,?,?)");}
				   			    
			    ps.setString(1,a);
			    ps.setString(2,b);
			    ps.setString(3,c);
			    ps.setString(4,d);		
			    ps.setString(5,e);	
			    ps.setString(6,f);
			    ps.setString(7,g);
						
			
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN. MetodoNomina>>CrearInterfazF "+ex);
			}
		}
	
	public void ActualizarInterfazF(String cod,String c,String f, String h, String u,String icg){
		/**
		 */
		 PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	
	     	if(icg.equals("1")){ 	st= con.conn.prepareStatement("UPDATE cont_ninterfaz_facturacion SET cuentaifk='"+c+"' , fecha='"+f+"', hora='"+h+"', usuario='"+u+"' WHERE codigo='"+cod+"' "); }
	     	if(icg.equals("2")){ 	st= con.conn.prepareStatement("UPDATE cont_ninterfaz_facturacion SET cuentacfk='"+c+"' , fecha='"+f+"', hora='"+h+"', usuario='"+u+"' WHERE codigo='"+cod+"' "); }
	     	if(icg.equals("3")){ 	st= con.conn.prepareStatement("UPDATE cont_ninterfaz_facturacion SET cuentagfk='"+c+"' , fecha='"+f+"', hora='"+h+"', usuario='"+u+"' WHERE codigo='"+cod+"' "); }
	     		     	
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoNomina>>ActualizarInterfazF "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	

	public java.sql.ResultSet ConsultarCodEmpleados(String sql){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT codigo FROM cont_nempleados  "+sql);
        	System.out.println(" SELECT codigo FROM cont_nempleados  "+sql);
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoNomina>>ConsultarCodEmpleados "+ex);
        }	
        return rs;
    	}

	
	public java.sql.ResultSet ConsultarDatosiniciales(){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT * FROM cont_datosiniciales WHERE estado=0  ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoNomina>>ConsultarDatosiniciales "+ex);
        }	
        return rs;
    }
	
	

	public void CrearDatosBasicosI(String a,String b,String c,String d, String e,String f,String g,String h,String i, String j, String k, String l, String m, String n, String o){
		//System.out.print(cod_medico);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			 					
			    ps=con.conn.prepareStatement("INSERT INTO cont_datosiniciales(cod_cta_clientesfk,cod_cta_clientes,nom_cta_clientes,cod_cta_ingresosfk,cod_cta_ingresos,nom_cta_ingresos,cod_cta_copagosfk,cod_cta_copagos,nom_cta_copagos,cod_cta_cuotamoderadorafk,cod_cta_cuotamoderadora,nom_cta_cuotamoderadora,fechaactualizacion,horaactualizacion,usuario) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			   			    
			    ps.setString(1,a);
			    ps.setString(2,b);
			    ps.setString(3,c);
			    ps.setString(4,d);		
			    ps.setString(5,e);	
			    ps.setString(6,f);
			    ps.setString(7,g);
			    ps.setString(8,h);
			    ps.setString(9,i);		
			    ps.setString(10,j);	
			    ps.setString(11,k);	
			    ps.setString(12,l);	
			    ps.setString(13,m);	
			    ps.setString(14,n);	
			    ps.setString(15,o);	
			    
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN. MetodoNomina>>CrearDatosBasicosI "+ex);
			}
		}
	
	
	public void ActualizarEstadoDatosBasicosI(){
		/**
		 */
		 PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE cont_datosiniciales SET estado='1'  WHERE estado='0' ");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoNomina>>ActualizarEstadoDatosBasicosI "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	






}//Fin MetodoNomina