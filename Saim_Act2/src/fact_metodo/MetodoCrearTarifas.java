package fact_metodo;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JProgressBar;

import fact_bean.Tarifas;
//import fact_controlador.Rectangle;

import adm_logica.Conexion;


import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;




public class MetodoCrearTarifas {
	
	public void CrearManualTarifario(String NombreManual){
		/**
		 * creamos los manuales tarifarios
		 */
		Tarifas t = new Tarifas();
		t.setManual(NombreManual);   
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("insert into fact_manualestarifarios(NombreManual)values(?)");
			    ps.setString(1,t.getManual());
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoCrearTarifas>>CrearManualTarifario "+ex);
			}
		}
		
	public java.sql.ResultSet BuscarManualTarifario(){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" select cod_manual_tarifario,descripcion from fact_manuales_tarifarios ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearTarifas>>BuscarManualTarifario "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarManualBase(){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT cod_manual_base,descripcion FROM fact_manuales_base");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearTarifas>>BuscarManualBase "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarManualTarifario(String cod){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" select descripcion from fact_manuales_tarifarios where cod_manual_tarifario='"+cod+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearTarifas>>BuscarManualTarifario "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarManualTarifariom(String cod){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" select * from fact_manuales_tarifarios where cod_manual_tarifario!='"+cod+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearTarifas>>BuscarManualTarifario "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarPrograma(String cod){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" select descripcion from fact_programas  where cod_programa ='"+cod+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearTarifas>>BuscarPrograma "+ex);
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
	        	r=st.executeQuery(" select p.cod_programa, p.descripcion, p.especialidad, e.descripcion, p.clase_servicio, cs.descripcion from fact_programas p, fact_especialidades e, fact_clases_servicio cs, fact_manuales_tarifarios mt where p.especialidad=e.cod_especialidad and p.clase_servicio=cs.cod_clase_servicio and p.descripcion like '"+texto+"%' and p.manual_base=mt.manual_base and mt.cod_manual_tarifario='"+xx+"' ");
	        return r;
	    }
	
	  public ResultSet listarArticulos2(String texto) throws Exception {
			/**
			 */
			  java.sql.ResultSet r=null;
		        Statement st = null;
		       Conexion con=new Conexion();
		        	st = con.conn.createStatement();
		        	//cesar listar art pero de la tabla inv
		        	r=st.executeQuery(" select p.cod_programa, p.descripcion, p.especialidad, e.descripcion, p.clase_servicio, cs.descripcion from fact_programas p, fact_especialidades e, fact_clases_servicio cs where p.especialidad=e.cod_especialidad and p.clase_servicio=cs.cod_clase_servicio and p.descripcion like '"+texto+"%'  ");
		        return r;
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
		   	System.out.println("Error en MetodoCrearTarifas>>ObtenerProgramas "+ex);
		   }	
		   return rs;
		}
	
	
	  public void CrearTarifa(String mb, String p, String fi, String ff, String v){
			/**
			 * creamos los manuales tarifarios
			 */
			
			try{
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("insert into fact_tarifas(manual_tarifario,programa,fecha_ini,fecha_fin,valor)values(?,?,?,?,?)");
				    ps.setString(1,mb);
				    ps.setString(2,p);
				    ps.setString(3,fi);
				    ps.setString(4,ff);
				    ps.setString(5,v);
				 	ps.executeUpdate();
					ps.close();
					con.cerrar();				
				}catch(Exception ex){
		        	System.out.println("ERROR EN MetodoCrearTarifas>>CrearTarifa "+ex);
				}
			}
	
	  
		public void ModificarTExistente(String sql){
			
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
	
		
		public java.sql.ResultSet ObtenerTarifaR(String mbasec, String descch0) {
			/**
			 */
			  java.sql.ResultSet r=null;
		        Statement st = null;
		        try{
		        Conexion con=new Conexion();
		        st = con.conn.createStatement();
		        r=st.executeQuery(" select cod_tarifa from fact_tarifas where manual_tarifario='"+mbasec+"' and programa='"+descch0+"'  ");
		        } catch(Exception ex){
				   	System.out.println("Error en MetodoCrearTarifas>>ObtenerTarifasR "+ex);
				}
		       	return r;
		    }
		
			  	
		public void EliminarTarifa(String ct){
			
			try{
				PreparedStatement st = null;
				    Conexion con=new Conexion();                                                      
				  //  ps=con.conn.prepareStatement("insert into farc_proveedor(nit,municipio_fk,telefono,fax,email,contacto,clase_contribuyente,razon_social,direccion,comentario)values(?,?,?,?,?,?,?,?,?,?)");
				    st= con.conn.prepareStatement("delete from fact_tarifas where cod_tarifa ='"+ct+"' ");
			    	st.executeUpdate();
			    	st.close();
					con.cerrar();				
				}catch(Exception ex){
		        	System.out.println("ERROR EN MetodoProgramas>>EliminarTarifa "+ex);
			}

		}
		
		/////////////////////Metodo para generar e incrementar tarifas////////////////////
		//private JProgressBar progressBar; 
		private JProgressBar progressBar;
//		progressBar = new JProgressBar(0, 100);

		private	JButton button;
		private	JLabel  label1;
		private	JPanel  topPanel;


		
		public  void GenerarTarifas(String ct, String fechai0, String fechaf0, String valor0, String descch0, String mbasec ){	
			/**
			 */
	       
			String d=fechai0.substring(0, 2);
			String m=fechai0.substring(3, 5);
			String a=fechai0.substring(6, 10);
			String f=a+"-"+m+"-"+d;
			String df=fechaf0.substring(0, 2);
			String mf=fechaf0.substring(3, 5);
			String af=fechaf0.substring(6, 10);
			String ff=af+"-"+mf+"-"+df;
							
			float valor0F=Float.valueOf(valor0).floatValue(); 
			float cien=valor0F/100;
			
			java.sql.ResultSet rs=null;
			java.sql.ResultSet rs1=null;
	        Statement st = null;
	        Statement st1 = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	        	
	        	rs=st.executeQuery("select programa, valor from fact_tarifas where manual_tarifario='"+ct+"'");
	        	
	        		        	
	        	while(rs.next()){
	        		
	        		float rs1F=Float.valueOf(rs.getString(2)).floatValue(); 
	    			float cientotal=rs1F*cien;
	    			float valortotal=rs1F+cientotal;
	    			int w = Math.round(valortotal);  			
	    			int at=0;
	    			
					if(descch0.equals("100")){		
						if(w>50){
							int a1=w/100;
							int a2=a1*100;	
							int a3=w-a2;
							if(a3>50){at=a2+100;}
							else{at=a2;}
						}else{at=100;}
					}
					
					if(descch0.equals("10")){
						if(w>5){
							int a1=w/10;
							int a2=a1*10;
							int a3=w-a2;
							if(a3>5){at=a2+10;}
							else{at=a2;}
						}else{at=10;}
					}
					
					if(descch0.equals("1")){
					at=w;
					}
					
					String ats=String.valueOf(at);
					///////////////////
					PreparedStatement ps = null;
				    ps=con.conn.prepareStatement("insert into fact_tarifas(manual_tarifario,programa,fecha_ini,fecha_fin,valor)values(?,?,?,?,?)");
				    ps.setString(1,mbasec);
				    ps.setString(2,rs.getString(1));
				    ps.setString(3,f);
				    ps.setString(4,ff);
				    ps.setString(5,ats);
				 	ps.executeUpdate();
					ps.close();
					
					
					////////////////	
	        	}
	        	rs.getStatement().getConnection().close();
	        	con.cerrar();
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoCrearTarifas>>GenerarTarifasCJ "+ex);
	        }	
	       // return rs;
	    }
		
		
		
		public  void GenerarTarifasd(String ct, String fechai0, String fechaf0, String valor0, String descch0, String mbasec ){	
			/**
			 */
	       
			String d=fechai0.substring(0, 2);
			String m=fechai0.substring(3, 5);
			String a=fechai0.substring(6, 10);
			String f=a+"-"+m+"-"+d;
			String df=fechaf0.substring(0, 2);
			String mf=fechaf0.substring(3, 5);
			String af=fechaf0.substring(6, 10);
			String ff=af+"-"+mf+"-"+df;
							
			float valor0F=Float.valueOf(valor0).floatValue(); 
			float cien=valor0F/100;
			
			java.sql.ResultSet rs=null;
			java.sql.ResultSet rs1=null;
	        Statement st = null;
	        Statement st1 = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	        	
	        	rs=st.executeQuery("select programa, valor from fact_tarifas where manual_tarifario='"+ct+"'");
	        	
	        		        	
	        	while(rs.next()){
	        		
	        		float rs1F=Float.valueOf(rs.getString(2)).floatValue(); 
	    			float cientotal=rs1F*cien;
	    			float valortotal=rs1F-cientotal;
	    			int w = Math.round(valortotal);  			
	    			int at=0;
	    			
					if(descch0.equals("100")){		
						if(w>50){
							int a1=w/100;
							int a2=a1*100;	
							int a3=w-a2;
							if(a3>50){at=a2+100;}
							else{at=a2;}
						}else{at=100;}
					}
					
					if(descch0.equals("10")){
						if(w>5){
							int a1=w/10;
							int a2=a1*10;
							int a3=w-a2;
							if(a3>5){at=a2+10;}
							else{at=a2;}
						}else{at=10;}
					}
					
					if(descch0.equals("1")){
					at=w;
					}
					
					String ats=String.valueOf(at);
					///////////////////
					PreparedStatement ps = null;
				    ps=con.conn.prepareStatement("insert into fact_tarifas(manual_tarifario,programa,fecha_ini,fecha_fin,valor)values(?,?,?,?,?)");
				    ps.setString(1,mbasec);
				    ps.setString(2,rs.getString(1));
				    ps.setString(3,f);
				    ps.setString(4,ff);
				    ps.setString(5,ats);
				 	ps.executeUpdate();
					ps.close();
					
					
					////////////////	
	        	}
	        	rs.getStatement().getConnection().close();
	        	con.cerrar();
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoCrearTarifas>>GenerarTarifasCJ "+ex);
	        }	
	       // return rs;
	    }
		
		public java.sql.ResultSet obtenerNivelC(String mbase) {
		  java.sql.ResultSet r=null;
	        Statement st = null;
	        try{
	        Conexion con=new Conexion();
	        st = con.conn.createStatement();
	        r=st.executeQuery("SELECT fe.cod_especialidad,fe.descripcion FROM fact_manuales_tarifarios fmt,fact_programas fp,fact_especialidades fe WHERE fmt.cod_manual_tarifario="+mbase+" AND fmt.manual_base=fp.manual_base AND fp.especialidad=fe.cod_especialidad AND fe.cod_especialidad>0 GROUP BY fe.cod_especialidad");

	        } catch(Exception ex){
			   	System.out.println("Error en MetodoCrearTarifas>>obtenerNivelC "+ex);
			}
	       	return r;
	    }
		
		public java.sql.ResultSet obtenerEspecialidad(String mbase) {
			  java.sql.ResultSet r=null;
		        Statement st = null;
		        try{
		        Conexion con=new Conexion();
		        st = con.conn.createStatement();
		        r=st.executeQuery("SELECT fnc.cod_niv_compl,fnc.descripcion FROM fact_manuales_tarifarios fmt,fact_programas fp,fact_niveles_complejidad fnc WHERE fmt.cod_manual_tarifario="+mbase+" AND fmt.manual_base=fp.manual_base AND fp.nivel_complejidad=fnc.cod_niv_compl GROUP BY fnc.cod_niv_compl");

		        } catch(Exception ex){
				   	System.out.println("Error en MetodoCrearTarifas>>obtenerEspecialidad "+ex);
				}
		       	return r;
		}
		
		public java.sql.ResultSet obtenerClaseServicio(String mbase) {
			  java.sql.ResultSet r=null;
		        Statement st = null;
		        try{
		        Conexion con=new Conexion();
		        st = con.conn.createStatement();
		        r=st.executeQuery("SELECT fcs.cod_clase_servicio,fcs.descripcion FROM fact_manuales_tarifarios fmt,fact_programas fp,fact_clases_servicio fcs WHERE fmt.cod_manual_tarifario="+mbase+" AND fmt.manual_base=fp.manual_base AND fp.clase_servicio=fcs.cod_clase_servicio GROUP BY fcs.cod_clase_servicio");

		        } catch(Exception ex){
				   	System.out.println("Error en MetodoCrearTarifas>>obtenerClaseServicio "+ex);
				}
		       	return r;
		}
		
		public java.sql.ResultSet obtenerSubcentroCosto(String mbase) {
			  java.sql.ResultSet r=null;
		        Statement st = null;
		        try{
		        Conexion con=new Conexion();
		        st = con.conn.createStatement();
		        r=st.executeQuery("SELECT fsc.cod_subcentro_costo,fsc.descripcion FROM fact_manuales_tarifarios fmt,fact_programas fp,fact_subcentros_costo fsc WHERE fmt.cod_manual_tarifario="+mbase+" AND fmt.manual_base=fp.manual_base AND fp.subcentro_costo=fsc.cod_subcentro_costo GROUP BY fsc.cod_subcentro_costo");

		        } catch(Exception ex){
				   	System.out.println("Error en MetodoCrearTarifas>>obtenerSubcentroCosto "+ex);
				}
		       	return r;
		}
		
		public java.sql.ResultSet obtenerProgManualBase(String mtarifario,String descripcion) {
			  java.sql.ResultSet r=null;
		        Statement st = null;
		        try{
		        Conexion con=new Conexion();
		        st = con.conn.createStatement();
		        r=st.executeQuery("SELECT fp.cod_referencia,fp.descripcion,fp.cod_programa FROM fact_programas fp,fact_manuales_tarifarios fmt WHERE fmt.cod_manual_tarifario = "+mtarifario+" AND fmt.manual_base=fp.manual_base AND fp.cod_programa NOT IN ( SELECT ft.programa FROM fact_tarifas ft WHERE ft.manual_tarifario = "+mtarifario+" )AND (fp.descripcion LIKE '%"+descripcion+"%' OR fp.cod_referencia LIKE '%"+descripcion+"%')");
		         } catch(Exception ex){
				   	System.out.println("Error en MetodoCrearTarifas>>obtenerProgManualBase "+ex);
				}
		       	return r;
		}
		
		public java.sql.ResultSet obtenerProgManualTarifario(String mtarifario,String descripcion) {

			  java.sql.ResultSet r=null;
		        Statement st = null;
		        try{
		        Conexion con=new Conexion();
		        st = con.conn.createStatement();
		        r=st.executeQuery("SELECT fp.cod_referencia,fp.descripcion,ft.fecha_ini,ft.fecha_fin,ft.valor,ft.cod_tarifa FROM fact_tarifas ft,fact_programas fp WHERE ft.manual_tarifario ='"+mtarifario+"' AND ft.programa=fp.cod_programa AND (fp.descripcion LIKE '%"+descripcion+"%' OR fp.cod_referencia LIKE '%"+descripcion+"%')");
		       } catch(Exception ex){
				   	System.out.println("Error en MetodoCrearTarifas>>obtenerProgManualTarifario "+ex);
				}
		       	return r;
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
		
		public java.sql.ResultSet obtenerCodUltTarif() {
			  java.sql.ResultSet r=null;
		        Statement st = null;
		        try{
		        Conexion con=new Conexion();
		        st = con.conn.createStatement();
		        r=st.executeQuery("SELECT t.cod_tarifa FROM fact_tarifas t ORDER BY t.cod_tarifa DESC LIMIT 1");

		        } catch(Exception ex){
				   	System.out.println("Error en MetodoCrearTarifas>>obtenerCodUltTarif "+ex);
				}
		       	return r;
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
}
