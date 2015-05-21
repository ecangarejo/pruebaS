/**
 * En este metodo estan las consultas,inserciones,actualizaciones.
 * para poder crear las especialidades,tipo de especialidades y detalle de especialidad.
 */
package fact_metodo;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import fact_bean.Convenio;
import adm_logica.Conexion;

public class MetodoCrearConvenio {
	/**
	 * Creamos un convenio
	 * @param numContrato
	 * @param valor
	 * @param fechaI
	 * @param fechaF
	 * @param nivComplejidad
	 * @param codTarifa1
	 * @param codTarifa2
	 * @param autoriza
	 * @param codEntidad
	 * @param obs Observaci&oacute;n
	 * @param modalidad
	 * @return
	 */
	public boolean Crear(String numContrato, String valor, String fechaI, String fechaF, String nivComplejidad, String autoriza, String codEntidad, String obs, String modalidad,String Modalidad_Pago, String user){
		Convenio bean = new Convenio();
		bean.setNumContrato(numContrato);
		bean.setValor(valor);
		bean.setFechaI(fechaI);
		bean.setFechaF(fechaF);
		bean.setNivComplejidad(nivComplejidad);
		bean.setAutoriza(autoriza);
		bean.setCodEntidad(codEntidad);
		bean.setObs(obs);
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		Conexion con = null;
		Conexion con2 = null;
		try{
		    con = new Conexion();
		    
		    	ps = con.conn.prepareStatement("INSERT INTO fact_convenios(num_contrato, valor, fecha_ini, fecha_final, nivel_complej, autoriza, cod_entidad, observacion,modalidad_pago,cod_usuariofk) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
		    	ps.setString(1, bean.getNumContrato());
			    ps.setString(2, bean.getValor());
			    ps.setString(3, bean.getFechaI());
			    ps.setString(4, bean.getFechaF());
			    ps.setString(5, bean.getNivComplejidad());
			    ps.setString(6, bean.getAutoriza());
			    ps.setString(7, bean.getCodEntidad());
			    ps.setString(8, bean.getObs());
			    ps.setString(9, Modalidad_Pago);
			    ps.setString(10, user);
		    
		 	ps.executeUpdate();
			ps.close();
			
			/*En esta se ingresan los codigos de las modalidades de pago escogidas por el usuario
			 *en la tabla 'fact_modalidades_convenio' con el respectivo codigo del convenio*/
			Statement st = null;
			try{
				String cod = getCodByNumContrato(numContrato);
				String[] mods = modalidad.split("-");
			    con.conn.setAutoCommit(false);
			    st = con.conn.createStatement();
			    for(int i=0; i<mods.length; i++){
			    	//System.out.println(i+"Cod_modalidad: "+mods[i]);		    	
			    	st.addBatch("INSERT INTO fact_modalidades_convenio (cod_modalidad_fk, cod_convenio_fk) VALUES ("+mods[i]+", "+cod+");");
			    }
			    st.executeBatch();
			    con.conn.commit();
			    st.close();
			} catch(SQLException SQLex){
				System.err.println("Error en MetodoCrearConvenio>>Crear");
				System.err.println("ERROR EJECUTANDO BATCH. LOS CAMBIOS NO SE PRODUJERON EN LA BD...\n"+SQLex.getMessage()+"");
				try{
					con.conn.rollback();
					System.out.println("SE HIZO EL ROLLBACK");
				}catch (Exception ex) {
					System.err.println("ERROR EJECUTANDO ROLLBACK... "+ex.getMessage()+"");
				}
			}catch(Exception e){
				System.err.println("Error en MetodoCrearConvenio>>Crear>>En el codigo de insercion de las modalidades\n"+e.getMessage());
			}
			con.cerrar();
			return true;
			
		}catch(Exception ex){
			System.err.println("ERROR EN MetodoCrearConvenio>>Crear>>Antes de Insertar las modalidades\n"+ex);
			return false;
		}
	
	}
	
	/**
	 * Este m&eacute;todo es diferente a los dem&aacute;s ya que se busca de 2 tablas
	 * @return {@link ResultSet} con el c&oacute;digo y n&uacute;mero de contrato del
	 * convenio y el nombre de la entidad con la que se hace guardados en la BD.
	 */
	public ResultSet getAll(){
		Conexion con = null;
        Statement st = null;
        ResultSet rs = null;
        try{
        	con = new Conexion();
        	st = con.conn.createStatement();
        	rs = st.executeQuery("SELECT fc.cod_convenio, fc.num_contrato, ae.nombre_entidad FROM fact_convenios fc, adm_entidad ae WHERE ae.ent_nit=fc.cod_entidad order by ae.nombre_entidad");
        } catch(Exception ex){
        	System.err.println("Error en MetodoCrearConvenio>>getAll\n"+ex);
        }
        return rs;
    }
	
	/**
	 * @param cod C&oacute;digo del convenio.
	 * @return {@link ResultSet} con los datos del convenio
	 * de acuerdo al c&oacute;digo del mismo pasado como par&aacute;metro.
	 */
	public ResultSet getByCod(String cod){
		Conexion con = null;
        Statement st = null;
        ResultSet rs = null;
        try{
        	con = new Conexion();
        	st = con.conn.createStatement();
        	rs = st.executeQuery("SELECT num_contrato, valor, fecha_ini, fecha_final, nivel_complej, autoriza, cod_entidad, observacion,modalidad_pago FROM fact_convenios WHERE cod_convenio='"+cod+"';");
        } catch(Exception ex){
        	System.err.println("Error en MetodoCrearConvenio>>getByCod\n"+ex);
        }
        return rs;
    }
	
	/**
	 * Actualiza un convenio
	 * @param numContrato
	 * @param valor
	 * @param fechaI
	 * @param fechaF
	 * @param nivComplejidad
	 * @param codTarifa1
	 * @param codTarifa2
	 * @param autoriza
	 * @param codEntidad
	 * @param cod C&oacute;digo del convenio.
	 * @param modalidad {@link String} con el(los) c&oacute;digo(s) de la(s) modalidad(es)
	 * @return true si actualiza el registro en la tabla, false en caso contrario.
	 */
	public boolean Actualizar(String numContrato, String valor, String fechaI, String fechaF, String nivComplejidad, String autoriza, String codEntidad, String obs, String cod, String modalidad,String Modalidad_Pago, String estc, String user, String f, String h, String t){
		Convenio bean = new Convenio();
		bean.setNumContrato(numContrato);
		bean.setValor(valor);
		bean.setFechaI(fechaI);
		bean.setFechaF(fechaF);
		bean.setNivComplejidad(nivComplejidad);

		bean.setAutoriza(autoriza);
		bean.setCodEntidad(codEntidad);
		bean.setObs(obs);
		PreparedStatement ps = null;
		Conexion con = null;
		
		
		try{
			
			////Auditoria//////
			  Statement st6 = null;
		        ResultSet rs = null;
		        try{
		        	con = new Conexion();
		        	st6 = con.conn.createStatement();
		        	rs = st6.executeQuery("SELECT num_contrato, valor, fecha_ini, fecha_final, nivel_complej, autoriza, cod_entidad, observacion,modalidad_pago FROM fact_convenios WHERE cod_convenio='"+cod+"';");
		        } catch(Exception ex){
		        	System.err.println("Error en MetodoCrearConvenio>>Auditoriaconsultaconveniooriginal\n"+ex);
		        }
		        
		       String original=""; 
		  	  if(rs.next()){
		  		original="Datos originales num_contrato="+rs.getString(1)+" valor="+rs.getString(2)+" fecha_ini="+rs.getString(3)+" fecha_final="+rs.getString(4)+"  nivel_complej="+rs.getString(5)+" autoriza="+rs.getString(6)+" cod_entidad="+rs.getString(7)+" observacion="+rs.getString(8)+" modalidad_pago="+rs.getString(9);
			  }
			    
		  	  
				////Auditoria//////
				Conexion con2 = null;  
				Statement st7 = null;
			        ResultSet rs7 = null;
			        try{
			        	con2 = new Conexion();
			        	st7 = con2.conn.createStatement();
			        	rs7 = st7.executeQuery("SELECT tope FROM fact_audita_convenio WHERE cod_convfk='"+cod+"';");
			        } catch(Exception ex){
			        	System.err.println("Error en MetodoCrearConvenio>>AuditoriaUpdatetope\n"+ex);
			        }
			        
			       String topea=""; 
			  	  if(rs7.next()){
			  		topea=rs7.getString(1);
				  }
				
			  	  
		      			
		    con = new Conexion();

	    	ps = con.conn.prepareStatement("UPDATE fact_convenios SET num_contrato=?, valor=?, fecha_ini=?, fecha_final=?, nivel_complej=?, autoriza=?, cod_entidad=?, observacion=?,modalidad_pago=?,estado=? WHERE cod_convenio='"+cod+"';");
		    ps.setString(1, bean.getNumContrato());
		    ps.setString(2, bean.getValor());
		    ps.setString(3, bean.getFechaI());
		    ps.setString(4, bean.getFechaF());
		    ps.setString(5, bean.getNivComplejidad());

		    ps.setString(6, bean.getAutoriza());
		    ps.setString(7, bean.getCodEntidad());
		    ps.setString(8, bean.getObs());
		    ps.setString(9, Modalidad_Pago);
		    ps.setString(10, estc);

		    		    
		 	ps.executeUpdate();
			ps.close();
			/*En esta parte de codigo se eliminan los codigos de las modalidades de pago ingresadas por el
			 *usuario en la tabla 'fact_modalidades_convenio' con el respectivo codigo del convenio*/
			PreparedStatement ps1 = null;
			ps1 = con.conn.prepareStatement("DELETE FROM fact_modalidades_convenio WHERE cod_convenio_fk='"+cod+"';");
			ps1.executeUpdate();
			ps1.close();
			
			//////Auditoria///////////
							
			PreparedStatement ps5 = null;
			//  ="+bean.getNumContrato()+"  valor='"+bean.getValor()+"' fecha_ini='"+bean.getFechaI()+"' fecha_final='"+bean.getFechaF()+"' nivel_complej='"+bean.getNivComplejidad()+"' autoriza='"+bean.getAutoriza()+"' cod_entidad='"+bean.getCodEntidad()+"' observacion='"+bean.getObs()+"' modalidad_pago='"+Modalidad_Pago+"' estado='"+estc+"' WHERE cod_convenio='"+cod+"'
			String accion=" SE HIZO UN UPDATE num_contrato ="+bean.getNumContrato()+"  valor="+bean.getValor()+" fecha_ini="+bean.getFechaI()+" fecha_final="+bean.getFechaF()+"  nivel_complej="+bean.getNivComplejidad()+" autoriza="+bean.getAutoriza()+" cod_entidad="+bean.getCodEntidad()+" observacion="+bean.getObs()+" modalidad_pago="+Modalidad_Pago+" estado="+estc+" WHERE cod_convenio="+cod+"   ";
			ps5 = con.conn.prepareStatement(" INSERT INTO fact_auditoria_de_convenio (cod_conveniofk,cod_entidadfk,cod_usuariofk,fecha,hora,original,accion,topea,tope) VALUES ("+cod+","+ bean.getCodEntidad()+","+user+",'"+f+"','"+h+"','"+original+"','"+accion+"',"+topea+","+t+"  ); ");
			ps5.executeUpdate();
			ps5.close();
			
			
			/*En esta parte de codigo se ingresan los codigos de las modalidades de pago escogidas por el
			 *usuario en la tabla 'fact_modalidades_convenio' con el respectivo codigo del convenio*/
			Statement st = null;
			try{
				//String cod = getCodByNumContrato(numContrato);
				String[] mods = modalidad.split("-");
			    con.conn.setAutoCommit(false);
			    st = con.conn.createStatement();
			    for(int i=0; i<mods.length; i++){
			    	//System.out.println(i+"Cod_modalidad: "+mods[i]);		    	
			    	st.addBatch("INSERT INTO fact_modalidades_convenio (cod_modalidad_fk, cod_convenio_fk) VALUES ("+mods[i]+", "+cod+");");
			    }
			    st.executeBatch();
			    con.conn.commit();
			    st.close();
			} catch(SQLException SQLex){
				System.err.println("Error en MetodoCrearConvenio>>Actualizar");
				System.err.println("ERROR EJECUTANDO BATCH. LOS CAMBIOS NO SE PRODUJERON EN LA BD...\n"+SQLex.getMessage());
				try{
					con.conn.rollback();
					System.out.println("SE HIZO EL ROLLBACK");
				}catch (Exception ex) {
					System.err.println("ERROR EJECUTANDO ROLLBACK... "+ex.getMessage()+"");
				}
			}catch(Exception e){
				System.err.println("Error en MetodoCrearConvenio>>Crear>>En el codigo de insercion de las modalidades\n"+e.getMessage());
			}
			con.cerrar();
			return true;
		}catch(Exception ex){
			System.err.println("ERROR en MetodoCrearConvenio>>Actualizar\n"+ex);
			return false;
		}
	}
	
	/*public ResultSet getCodByNumContrato(String numContrato){
		Conexion con = null;
	    Statement st = null;
	    ResultSet rs = null;
	    try{
	    	con = new Conexion();
	    	st = con.conn.createStatement();
	    	rs = st.executeQuery("SELECT cod_convenio FROM fact_convenios WHERE num_contrato='"+numContrato+"';");
	    } catch(Exception ex){
	    	System.err.println("Error en MetodoCrearConvenio>>getCodByNumContrato\n"+ex);
	    }
	    return rs;
	}*/
	
	/**
	 * 
	 * @param numContrato
	 * @return {@link String} con el c&oacute;digo del convenio.
	 */
	public String getCodByNumContrato(String numContrato){
		Conexion con = null;
        Statement st = null;
        ResultSet rs = null;
        String aux = "";
        try{
        	con = new Conexion();
        	st = con.conn.createStatement();
        	rs = st.executeQuery("SELECT cod_convenio FROM fact_convenios WHERE num_contrato='"+numContrato+"';");
        	if(rs.next()){
        		aux = rs.getString(1);
        	}
        } catch(Exception ex){
        	System.err.println("Error en MetodoCrearConvenio>>getCodByNumContrato\n"+ex);
        }
        return aux;
    }
	
	
	public String getCodByNumEntidad(String numContrato){
		Conexion con = null;
        Statement st = null;
        ResultSet rs = null;
        String aux = "";
        try{
        	con = new Conexion();
        	st = con.conn.createStatement();
        	rs = st.executeQuery("SELECT cod_convenio FROM fact_convenios WHERE cod_entidad='"+numContrato+"';");
        	if(rs.next()){
        		aux = rs.getString(1);
        	}
        } catch(Exception ex){
        	System.err.println("Error en MetodoCrearConvenio>>getCodByNumEntidad\n"+ex);
        }
        return aux;
    }
	
	/**
	 * @param cod C&oacute;digo del convenio.
	 * @return {@link ResultSet} con el(los) c&oacute;digo(s) de la(s) madalidad(es).
	 */
	public ResultSet getModalidadesByCod(String cod){
		Conexion con = null;
        Statement st = null;
        ResultSet rs = null;
        try{
        	con = new Conexion();
        	st = con.conn.createStatement();
        	rs = st.executeQuery("SELECT cod_modalidad_fk FROM fact_modalidades_convenio WHERE cod_convenio_fk='"+cod+"';");
        } catch(Exception ex){
        	System.err.println("Error en MetodoCrearConvenio>>getModalidadesByCod\n"+ex);
        }
        return rs;
    }
	
	public String obtenerCodConvenio(){
		String aux = "";
		Conexion con = null;
        Statement st = null;
        ResultSet rs = null;
        try{
        	con = new Conexion();
        	st = con.conn.createStatement();
        	rs = st.executeQuery("SELECT fc.cod_convenio FROM fact_convenios fc ORDER BY fc.cod_convenio DESC LIMIT 1");
        	if(rs.next()){
        		aux = rs.getString(1);
        	}
        } catch(Exception ex){
        	System.err.println("Error en MetodoCrearConvenio>>obtenerCodConvenio"+ex);
        }
        return aux;
    }
	
	public void agregarTarifaAConvenio(String codConvenio, String codTarifa){
		//System.out.print(cod_medico);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    															
			    ps=con.conn.prepareStatement("insert into fact_tarifas_convenios(cod_convenio,cod_manualTarifario) values(?,?)");
			    ps.setString(1,codConvenio);
			    ps.setString(2,codTarifa);
			    
			    
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN. MetodoMovimientos>>CargarDetalle "+ex);
			}
		}
	
	public ResultSet obtenerTarifasConvenio(String cod){
		Conexion con = null;
        Statement st = null;
        ResultSet rs = null;
        try{
        	con = new Conexion();
        	st = con.conn.createStatement();
        	rs = st.executeQuery("SELECT fmt.cod_manual_tarifario,fmt.descripcion FROM fact_tarifas_convenios ftc,fact_manuales_tarifarios fmt  WHERE ftc.cod_convenio = "+cod+" AND ftc.cod_manualTarifario=fmt.cod_manual_tarifario");
        } catch(Exception ex){
        	System.err.println("Error en MetodoCrearConvenio>>obtenerTarifasConvenio"+ex);
        }
        return rs;
    }
	
	public void borrarTarifasConvenio(String cod){
		Conexion con = null;
		try{
			con = new Conexion();
			PreparedStatement ps1 = null;
			ps1 = con.conn.prepareStatement("DELETE FROM fact_tarifas_convenios WHERE cod_convenio ="+cod);
			ps1.executeUpdate();
			ps1.close();

		}catch(Exception ex){
			System.err.println("ERROR en MetodoCrearConvenio>>Actualizar\n"+ex);
		}
	}
	
	public void AuditaConvenio(String codConvenio,  String ent, String ff, String v, String t,String c){
		//System.out.print(cod_medico);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    															
			    ps=con.conn.prepareStatement("insert into fact_audita_convenio(cod_convfk,cod_entidadfk,fechafin,valor,tope,consumido) values(?,?,?,?,?,?)");
			    ps.setString(1,codConvenio);
			    ps.setString(2,ent);
			    ps.setString(3,ff);
			    ps.setString(4,v);
			    ps.setString(5,t);
			    ps.setString(6,c);
			    
			    
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN. MetodoCrearConvenio>>AuditaConvenio "+ex);
			}
		}
	
	public void UpdateAuditaConvenio(String cod, String ff, String valor,String tope){	
		/**
		 */
		//System.out.println(cod+" - "+ff+" - "+valor+" - "+tope);
		 PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE fact_audita_convenio SET fechafin='"+ff+"', valor='"+valor+"', tope='"+tope+"' WHERE cod_convfk='"+cod+"' ");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     	
	     	
	      }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoCrearConvenio>>UpdateAuditaConvenio "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public ResultSet obtenerAuditaConvenio(String cod){
		Conexion con = null;
        Statement st = null;
        ResultSet rs = null;
        try{
        	con = new Conexion();
        	st = con.conn.createStatement();
        	rs = st.executeQuery("SELECT fechafin, valor, tope, consumido FROM fact_audita_convenio WHERE cod_convfk= "+cod+" ");
        } catch(Exception ex){
        	System.err.println("Error en MetodoCrearConvenio>>obtenerAuditaConvenio"+ex);
        }
        return rs;
    }
	
	public ResultSet obtenerEstadoConvenio(String cod){
		Conexion con = null;
        Statement st = null;
        ResultSet rs = null;
        try{
        	con = new Conexion();
        	st = con.conn.createStatement();
        	rs = st.executeQuery("SELECT estado FROM fact_convenios WHERE cod_convenio= "+cod+" ");
        } catch(Exception ex){
        	System.err.println("Error en MetodoCrearConvenio>>obtenerAuditaConvenio"+ex);
        }
        return rs;
    }
	
	
}
