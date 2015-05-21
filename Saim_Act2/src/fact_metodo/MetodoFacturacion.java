package fact_metodo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import adm_logica.Conexion;



public class MetodoFacturacion {
	
	public void ActualizarEncabezadoFactura(String valor,String valorletras,String anticipos,String copago,String moderacion,String efectivo,String  CodEnc,String FechaActual,String HoraActual,String CodUsu){
		//System.out.print(cod_medico);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
				ps=con.conn.prepareStatement("UPDATE  fact_enc_factura SET  valor = '"+valor+"',valorletras = '"+valorletras+"',anticipos = '"+anticipos+"',copago = '"+copago+"',moderacion = '"+moderacion+"',efectivo = '"+efectivo+"',estado='1',fecha='"+FechaActual+"',hora='"+HoraActual+"',cod_usuario_fk='"+CodUsu+"' WHERE cod_enc_factura = '"+CodEnc+"' ");
			 	ps.executeUpdate();
				ps.close();			    
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoFacturacion>>ActualizarDatallePreFactura "+ex);
			}
		}
	
	public void ActualizarEncabezadoPreFactura(String valor,String valorletras,String anticipos,String copago,String moderacion,String efectivo,String  CodEnc){
		//System.out.print(cod_medico);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
				ps=con.conn.prepareStatement("UPDATE  fact_enc_factura SET  valor = '"+valor+"',valorletras = '"+valorletras+"',anticipos = '"+anticipos+"',copago = '"+copago+"',moderacion = '"+moderacion+"',efectivo = '"+efectivo+"' WHERE cod_enc_factura = '"+CodEnc+"' ");
			 	ps.executeUpdate();
				ps.close();			    
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoFacturacion>>ActualizarDatallePreFactura "+ex);
			}
		}
	public java.sql.ResultSet NumFacturas(){	
		//System.out.println("NumFacturas");
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT * FROM fact_numeros_fact ");
        	//rs=st.executeQuery(" SELECT consecutivo FROM fact_numeros_fact ");
        	//System.out.println(" SELECT consecutivo FROM fact_numeros_fact ");
          }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>NumFacturas "+ex);
        }	
        return rs;
    }
	
	public void ActualizarNumFacturas(String valor){	
		//System.out.println("ActualizarNumFacturas");
		 PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement(" UPDATE fact_numeros_fact SET consecutivo='"+valor+"' ");
	     	//System.out.println("UPDATE fact_numeros_fact SET consecutivo='"+valor+"'" );
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoMovimientos>>ActualizarNumFacturas "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public void AsignarNumFact(String fecha,String enca,String nf){
		//System.out.println(" AsignarNumFact fecha "+fecha+" enca "+enca+" nf "+nf);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    															
			    ps=con.conn.prepareStatement("insert into fact_numeradas(cod_enc_fact_fk,consecutivo,fecha)values(?,?,?)");
			    ps.setString(1,enca);
			    ps.setString(2,nf);
			    ps.setString(3,fecha);
						
			    
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN. MetodoMovimientos>>AsignarNumFact "+ex);
			}
		}
public ResultSet BuscarCodFactNumerada(String fecha,String enca,String nf){
		
		//System.out.println(" AsignarNumFact fecha "+fecha+" enca "+enca+" nf "+nf);
		
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();													
        	rs=st.executeQuery("select cod_fact_numerada from fact_numeradas where cod_enc_fact_fk='"+enca+"' and consecutivo='"+nf+"' and fecha='"+fecha+"' ");
        	
        }catch(Exception ex){
	        	System.out.println("ERROR EN. MetodoMovimientos>>BuscarCodFactNumerada"+ex);
			}
        	 return rs;
		}

public void recordEstadoFact(String codFact,String estado,String fecha,String usuario){
	try{
		PreparedStatement ps = null;
	    Conexion con=new Conexion();
	    															
	    ps=con.conn.prepareStatement("INSERT INTO fact_movfacturas(codFactNumerada,estadoFact,fecha,usuario) VALUES (?,?,?,?)");
	    System.out.print("ya se inserttaron los mov");
	    ps.setString(1,codFact);
	    ps.setString(2,estado);		
	    ps.setString(3,fecha);	
	    ps.setString(4,usuario);
	 	ps.executeUpdate();
		ps.close();
		con.cerrar();				
	}catch(Exception ex){
    	System.out.println("ERROR EN. MetodoMovimientos>>recordEstadoFact "+ex);
	}
}

public java.sql.ResultSet ValorFact(String enca){	
	/**
	 */
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery(" SELECT valor,nit FROM fact_enc_factura WHERE cod_enc_factura='"+enca+"' ");
      }
    catch(Exception ex){
    	System.out.println("Error en MetodoMovimientos>>NumFacturas "+ex);
    }	
    return rs;
	}

public void ActualizarAuditoriadeConvenios(String ent, String val){	
	/**
	 */
	 PreparedStatement st = null;
     try{
     	Conexion con=new Conexion(); 
     	st= con.conn.prepareStatement(" UPDATE fact_audita_convenio SET consumido=(consumido+'"+val+"') where cod_entidadfk='"+ent+"'  ");
     	st.executeUpdate();	 
     	st.close();
     	con.cerrar();
     }
     catch(Exception ex){
     	System.out.println("Error en MetodoMovimientos>>ActualizarNumFacturas "+ex);
     	ex.getMessage();	     
     }	
 }

public java.sql.ResultSet ConsultarProgramasValores(String enca){	
	/**
	 */
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	//rs=st.executeQuery(" SELECT cod_programafk,SUM(cantidad),SUM(cantidad*valor),nombre_programa,clase_servicio  FROM fact_det_factura, fact_prog_med WHERE cod_programafk=cod_prog AND cod_enc_factura_fk='"+enca+"' GROUP BY cod_programafk   ");
    	rs=st.executeQuery(" SELECT cod_programafk,SUM(cantidad),SUM(cantidad*valor),nombre_programa,clase_servicio  FROM fact_det_factura WHERE cod_enc_factura_fk='"+enca+"' GROUP BY cod_programafk ");
    	System.out.println(" SELECT cod_programafk,SUM(cantidad),SUM(cantidad*valor),nombre_programa,clase_servicio  FROM fact_det_factura WHERE cod_enc_factura_fk='"+enca+"' GROUP BY cod_programafk ");
    }
    catch(Exception ex){
    	System.out.println("Error en MetodoMovimientos>>ConsultarProgramasValores "+ex);
    }	
    return rs;
}

public java.sql.ResultSet ConsultarProgramasCostos(String adm, String prog){	
	/**
	 */
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery(" SELECT SUM(cunitario), COUNT(codigo), SUM(ctotal) AS costo FROM farc_costosdedispensacion WHERE cod_admfk='"+adm+"' AND cod_programafk='"+prog+"'   ");
    }
    catch(Exception ex){
    	System.out.println("Error en MetodoMovimientos>>ConsultarProgramasCostos "+ex);
    }	
    return rs;
}

public void actualizarCostosdeDispensaciont(String e, String vi, String ci){	
	System.out.println("ActualizarEncabezado "+e);
	 PreparedStatement st = null;
     try{
     	Conexion con=new Conexion();
     	st= con.conn.prepareStatement(" UPDATE fact_enc_factura SET valor_inventario='"+vi+"', costo_inventario='"+ci+"' where cod_enc_factura='"+e+"'  ");
     	//System.out.println(" UPDATE fact_enc_factura SET fecha='"+f+"' , hora='"+h+"', cod_usuario_fk='"+u+"' where cod_enc_factura='"+e+"'  ");
     	st.executeUpdate();	 
     	st.close();
     	con.cerrar();
     }
     catch(Exception ex){
     	System.out.println("Error en MetodoMovimientos>>actualizarCostosdeDispensaciont "+ex);
     	ex.getMessage();	     
     }	
 }

public java.sql.ResultSet Consultarcopagoymoderacionodescuento(String cod){	
	java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery(" SELECT copago,moderacion FROM fact_enc_factura WHERE cod_enc_factura="+cod+" ");
    }
    catch(Exception ex){
    	System.out.println("Error en MetodoDocumentos>>Consultarcopagoymoderacionodescuento "+ex);
    }	
    return rs;
}

public java.sql.ResultSet ConsultarcuentaCopago(){	
	java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery(" SELECT cod_cta_copagosfk,nom_cta_copagos FROM cont_datosiniciales WHERE estado=0 ");
    }
    catch(Exception ex){
    	System.out.println("Error en MetodoDocumentos>>ConsultarcuentaCopago "+ex);
    }	
    return rs;
}

public java.sql.ResultSet ConsultarcuentaModeracion(){	
	java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery(" SELECT cod_cta_cuotamoderadorafk,nom_cta_cuotamoderadora  FROM cont_datosiniciales WHERE estado=0 ");
    }
    catch(Exception ex){
    	System.out.println("Error en MetodoDocumentos>>ConsultarcuentaModeracion "+ex);
    }	
    return rs;
}

public void ActualizarDocumentoDebitoCredito(String docu, String vd, String vc){	
	/**
	 */
	 PreparedStatement st = null;
     try{
     	Conexion con=new Conexion();
     	st= con.conn.prepareStatement("UPDATE cont_documentos SET valor_debito='"+vd+"', valor_credito='"+vc+"' WHERE codigo='"+docu+"' ");
     	st.executeUpdate();	 
     	st.close();
     	con.cerrar();
     }
     catch(Exception ex){
     	System.out.println("Error en MetodoDocumentos>>ActualizarDocumentoDebitoCredito "+ex);
     	ex.getMessage();	     
     }	
 }


public java.sql.ResultSet ConsecutivosdeCuentas(String cod){	
	java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery(" SELECT sigla, consecutivo FROM cont_tipo_documento WHERE codigo="+cod+" ");
    }
    catch(Exception ex){
    	System.out.println("Error en MetodoDocumentos>>ConsecutivosdeCuentas "+ex);
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

public void ActualizarConsecutivo(String ctan, String td){	
	/**
	 */
	 PreparedStatement st = null;
     try{
     	Conexion con=new Conexion();
     	st= con.conn.prepareStatement("UPDATE cont_tipo_documento SET consecutivo='"+ctan+"' WHERE codigo='"+td+"' ");
     	st.executeUpdate();	 
     	st.close();
     	con.cerrar();
     }
     catch(Exception ex){
     	System.out.println("Error en MetodoDocumentos>>ActualizarConsecutivo "+ex);
     	ex.getMessage();	     
     }	
 }

public java.sql.ResultSet ConsultarcuentaIngresoClientes(){	
	java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery(" SELECT cod_cta_clientesfk,nom_cta_clientes FROM cont_datosiniciales WHERE estado=0 ");
    }
    catch(Exception ex){
    	System.out.println("Error en MetodoDocumentos>>ConsultarcuentaIngreso "+ex);
    }	
    return rs;
}

public java.sql.ResultSet ConsultarTercerosconNIT(String cod){	
	java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery(" SELECT codigo FROM cont_terceros WHERE numero_documento='"+cod+"'  AND tipo_identificacion='NIT' ");
    	System.out.println(" SELECT codigo FROM cont_terceros WHERE numero_documento='"+cod+"'  AND tipo_identificacion='NIT' ");
    }
    catch(Exception ex){
    	System.out.println("Error en MetodoDocumentos>>ConsultarTercerosconNIT "+ex);
    }	
    return rs;
}

public void CrearDetalleDocumento(String ac,String pc,String td,String nd,String fd,String dd,String vd,String vc,String pd,String bd,String dif,String ct){
	try{
			PreparedStatement ps = null;
		    Conexion con=new Conexion();
		    ps=con.conn.prepareStatement("INSERT INTO cont_detalle_documentos (cod_documento_fk,cod_cuenta_fk,cod_sucursal_costo_fk,cod_centro_subcentro_fk,cod_terceros_fk,descripcion,valor_base,documento_referencia,valor_debito,valor_credito,cod_diferido_fk,origen) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
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
		    ps.setString(11,dif);
		    ps.setString(12,ct);
		    ps.executeUpdate();
			ps.close();
			con.cerrar();				
		}catch(Exception ex){
        	System.out.println("ERROR EN MetodoDocumentos>>CrearDetalleDocumento "+ex);
		}
	}

public java.sql.ResultSet Consultarcuentasdeprogramas(String cc, String scc, String p){	
	java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery(" SELECT cuentaifk,cuentacfk,cuentagfk FROM cont_ninterfaz_facturacion WHERE centrocostofk="+cc+" AND subcentrofk="+scc+" AND programafk="+p+" ");
    	System.out.println("xxxxxxxxxxxxxxxxxxxxx SELECT cuentaifk,cuentacfk,cuentagfk FROM cont_ninterfaz_facturacion WHERE centrocostofk="+cc+" AND subcentrofk="+scc+" AND programafk="+p+" ");
           
    }
    catch(Exception ex){
    	System.out.println("Error en MetodoDocumentos>>Consultarcuentasdeprogramas "+ex);
    }	
    return rs;
}


	public void ActualizarDetalleFactura(String CodEnca,String Estado){
		//System.out.print(cod_medico);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
				ps=con.conn.prepareStatement("UPDATE fact_det_factura SET facturado='"+Estado+"' WHERE cod_enc_factura_fk='"+CodEnca+"' ");
			 	ps.executeUpdate();
				ps.close();			    
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoFacturacion>>ActualizarDetalleFactura "+ex);
			}
		}
	
	public void ActualizarDatallePreFactura(String CodNuevoEnca,String CodDetalleFact,String Estado){
		//System.out.print(cod_medico);
		System.out.println("UPDATE fact_det_factura SET cod_enc_factura_fk='"+CodNuevoEnca+"',facturado='"+Estado+"' WHERE cod_det_factura='"+CodDetalleFact+"' ");
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
				ps=con.conn.prepareStatement("UPDATE fact_det_factura SET cod_enc_factura_fk='"+CodNuevoEnca+"',facturado='"+Estado+"' WHERE cod_det_factura='"+CodDetalleFact+"' ");
			 	ps.executeUpdate();
				ps.close();			    
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoFacturacion>>ActualizarDatallePreFactura "+ex);
			}
		}

	public void CrearNuevoEncabezado(String CodEntidad,String CodAdmision){
		System.out.println("INSERT INTO fact_enc_factura (cod_eps,  razon_social,  nit,  direccion,  telefono,  ciudad,  condicion_pago,  nombre_paciente,  documento,  direccion_p,  telefono_p,  tipo_afiliacion,  estrato,  fecha_ingreso,  fecha_egreso,  cod_admision,  num_autorizacion,  cod_usuario_fk,  poliza,  fecha,  hora,  tipo,  valor,  valorletras,  anticipos,  copago,  moderacion,  efectivo,  estado,  valor_inventario,  costo_inventario )SELECT cod_eps,  razon_social,  nit,  direccion,  telefono,  ciudad,  condicion_pago,  nombre_paciente,  documento,  direccion_p,  telefono_p,  tipo_afiliacion,  estrato,  fecha_ingreso,  fecha_egreso,  cod_admision,  num_autorizacion,  cod_usuario_fk,  poliza,  fecha,  hora,  tipo,  valor,  valorletras,  anticipos,  copago,  moderacion,  efectivo,  '3',  valor_inventario,  costo_inventario FROM  fact_enc_factura  WHERE cod_eps = '"+CodEntidad+"' AND cod_admision = '"+CodAdmision+"' AND estado = '0' ");
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
				ps=con.conn.prepareStatement("INSERT INTO fact_enc_factura (cod_eps,  razon_social,  nit,  direccion,  telefono,  ciudad,  condicion_pago,  nombre_paciente,  documento,  direccion_p,  telefono_p,  tipo_afiliacion,  estrato,  fecha_ingreso,  fecha_egreso,  cod_admision,  num_autorizacion,  cod_usuario_fk,  poliza,  fecha,  hora,  tipo,  valor,  valorletras,  anticipos,  copago,  moderacion,  efectivo,  estado,  valor_inventario,  costo_inventario )SELECT cod_eps,  razon_social,  nit,  direccion,  telefono,  ciudad,  condicion_pago,  nombre_paciente,  documento,  direccion_p,  telefono_p,  tipo_afiliacion,  estrato,  fecha_ingreso,  fecha_egreso,  cod_admision,  num_autorizacion,  cod_usuario_fk,  poliza,  fecha,  hora,  tipo,  valor,  valorletras,  anticipos,  copago,  moderacion,  efectivo,  '3',  valor_inventario,  costo_inventario FROM  fact_enc_factura  WHERE cod_eps = '"+CodEntidad+"' AND cod_admision = '"+CodAdmision+"' AND estado = '0' ");
			 	ps.executeUpdate();
				ps.close();			    
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoFacturacion>>asignarCuentaDev "+ex);
			}
		}
	
	public java.sql.ResultSet ConsultarNuevoEncabezado(String CodEntidad,String CodAdmision,String Estado){	
	    java.sql.ResultSet rs=null;
	    Statement st = null;
	    try{
	    	Conexion con=new Conexion();
	    	st = con.conn.createStatement();
	    	rs=st.executeQuery("SELECT * FROM fact_enc_factura WHERE cod_eps='"+CodEntidad+"' AND cod_admision='"+CodAdmision+"' AND estado='"+Estado+"' ORDER BY cod_enc_factura DESC LIMIT 1");
	    	System.out.println("SELECT * FROM fact_enc_factura WHERE cod_eps='"+CodEntidad+"' AND cod_admision='"+CodAdmision+"' AND estado='"+Estado+"' ORDER BY cod_enc_factura DESC LIMIT 1");
	    }
	    catch(Exception ex){
	    	System.out.println("Error en MetodoFacturacion>>ConsultarNuevoEncabezado "+ex);
	    }	
	    return rs;
	}
	
	
	public ResultSet listarPacientesUrgencias(String texto) throws Exception {
		   java.sql.ResultSet r=null;
	       Statement st = null;
	       Conexion con=new Conexion();
	       st = con.conn.createStatement();
	       r=st.executeQuery(" SELECT fe.cod_enc_factura,fe.documento,fe.nombre_paciente,fe.razon_social,a.adm_numero_ingreso,a.pac_codigo_paciente_fk,fe.cod_eps FROM fact_enc_factura fe,adm_admisiones a WHERE a.adm_numero_ingreso=fe.cod_admision AND (fe.nombre_paciente LIKE '%"+texto+"%' OR fe.documento LIKE '%"+texto+"%' OR a.adm_numero_ingreso LIKE '%"+texto+"%') AND a.estado!='2' AND a.atendido='0' AND a.aurg='1' AND a.ahosp='0' LIMIT 100 ");
	       System.out.println(" SELECT fe.cod_enc_factura,fe.documento,fe.nombre_paciente,fe.razon_social,a.adm_numero_ingreso,a.pac_codigo_paciente_fk,fe.cod_eps FROM fact_enc_factura fe,adm_admisiones a WHERE a.adm_numero_ingreso=fe.cod_admision AND (fe.nombre_paciente LIKE '%"+texto+"%' OR fe.documento LIKE '%"+texto+"%' OR a.adm_numero_ingreso LIKE '%"+texto+"%') AND a.estado!='2' AND a.atendido='0' AND a.aurg='1' AND a.ahosp='0' LIMIT 100 ");
	       return r;
	}
	
	public ResultSet listarPacientesHospitalariosyAmbulatorios(String texto, String t) throws Exception {
		   java.sql.ResultSet r=null;
	       Statement st = null;
	       Conexion con=new Conexion();
	       st = con.conn.createStatement();
	       System.out.println("t: "+t);
	       if(t.equals("0")){
	    	   r=st.executeQuery("SELECT fe.cod_enc_factura,fe.documento,fe.nombre_paciente,fe.razon_social,a.adm_numero_ingreso,a.pac_codigo_paciente_fk,fe.cod_eps FROM adm_admisiones a, fact_enc_factura fe WHERE a.estado!='2' AND a.atendido='0' AND a.aurg='0' AND a.ahosp='1' AND a.adm_numero_ingreso=fe.cod_admision AND fe.tipo='2' AND fe.estado='0' AND (fe.nombre_paciente LIKE '%"+texto+"%' OR fe.documento LIKE '%"+texto+"%'  OR a.adm_numero_ingreso LIKE '%"+texto+"%') GROUP BY fe.razon_social,a.adm_numero_ingreso  ORDER BY fe.nombre_paciente LIMIT 100 ");																																																															
	    	   System.out.println("SELECT fe.cod_enc_factura,fe.documento,fe.nombre_paciente,fe.razon_social,a.adm_numero_ingreso,a.pac_codigo_paciente_fk,fe.cod_eps FROM adm_admisiones a, fact_enc_factura fe WHERE a.estado!='2' AND a.atendido='0' AND a.aurg='0' AND a.ahosp='1' AND a.adm_numero_ingreso=fe.cod_admision AND fe.tipo='2' AND fe.estado='0' AND (fe.nombre_paciente LIKE '%"+texto+"%' OR fe.documento LIKE '%"+texto+"%'  OR a.adm_numero_ingreso LIKE '%"+texto+"%') GROUP BY fe.razon_social,a.adm_numero_ingreso  ORDER BY fe.nombre_paciente LIMIT 100 ");
	       }
	       if(t.equals("1")){
	    	   r=st.executeQuery("SELECT fe.cod_enc_factura,fe.documento,fe.nombre_paciente,fe.razon_social,a.adm_numero_ingreso,a.pac_codigo_paciente_fk,fe.cod_eps,fe.num_autorizacion FROM adm_admisiones a, fact_enc_factura fe WHERE a.estado!='2' AND a.atendido='0' AND a.aurg='0' AND a.ahosp='1' AND a.adm_numero_ingreso=fe.cod_admision AND (fe.tipo='3' or fe.tipo=4) AND fe.estado='0' AND (fe.nombre_paciente LIKE '%"+texto+"%' OR fe.documento LIKE '%"+texto+"%'  OR a.adm_numero_ingreso LIKE '%"+texto+"%') GROUP BY fe.razon_social,a.adm_numero_ingreso  ORDER BY fe.nombre_paciente LIMIT 100 ");																																																															
	    	   System.out.println("SELECT fe.cod_enc_factura,fe.documento,fe.nombre_paciente,fe.razon_social,a.adm_numero_ingreso,a.pac_codigo_paciente_fk,fe.cod_eps,fe.num_autorizacion FROM adm_admisiones a, fact_enc_factura fe WHERE a.estado!='2' AND a.atendido='0' AND a.aurg='0' AND a.ahosp='1' AND a.adm_numero_ingreso=fe.cod_admision AND (fe.tipo='3' or fe.tipo=4) AND fe.estado='0' AND (fe.nombre_paciente LIKE '%"+texto+"%' OR fe.documento LIKE '%"+texto+"%'  OR a.adm_numero_ingreso LIKE '%"+texto+"%') GROUP BY fe.razon_social,a.adm_numero_ingreso  ORDER BY fe.nombre_paciente LIMIT 100 ");
	       }
	    return r;
	}
	
	public java.sql.ResultSet EmpresasDiferentes(String ent){//Buscar las demas entidades	
	    java.sql.ResultSet rs=null;
	    Statement st = null;
	    try{
	    	Conexion con=new Conexion();
	    	st = con.conn.createStatement();
	    	rs=st.executeQuery(" SELECT ent_nit, nombre_entidad FROM adm_entidad WHERE  ent_nit!='"+ent+"%' ORDER BY nombre_entidad ");
	    	//System.out.println(" SELECT ent_nit, nombre_entidad FROM adm_entidad WHERE  ent_nit!='"+ent+"%' ORDER BY nombre_entidad ");
	    }
	    catch(Exception ex){
	    	System.out.println("Error en MetodoMovimientos>>EmpresasDiferentes "+ex);
	    }	
	    return rs;
	}
	
	public java.sql.ResultSet InfoEncaxEntidad(String codAdmision, String eps){	
	    java.sql.ResultSet rs=null;
	    Statement st = null;
	    try{
	    	Conexion con=new Conexion();
	    	st = con.conn.createStatement();
	    	rs=st.executeQuery("SELECT * FROM fact_enc_factura fef WHERE fef.cod_admision='"+codAdmision+"' AND cod_eps='"+eps+"' AND cod_usuario_fk IS NULL AND fecha IS NULL AND hora IS NULL");	
	    }
	    catch(Exception ex){
	    	System.out.println("Error en MetodoMovimientos>>InfoEncaxEntidad "+ex);
	    }	
	    return rs;
	}
	
	public java.sql.ResultSet obtenerInfoEnca(String codAdmision, String eps){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM fact_enc_factura fef WHERE fef.cod_admision='"+codAdmision+"' AND cod_eps='"+eps+"' AND fef.estado!=5  ORDER BY  fef.cod_enc_factura DESC LIMIT 1");	
        	//System.out.println("ObtenerInfoEnca -- > SELECT * FROM fact_enc_factura fef WHERE fef.cod_admision='"+codAdmision+"' and fef.estado!=5  ORDER BY  fef.cod_enc_factura DESC LIMIT 1");	
          }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>obtenerInfoEnca "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarUltioEncabezadoCreado(String CodAdm){	
		/**
		 */
	    java.sql.ResultSet rs=null;
	    Statement st = null;
	    try{
	    	Conexion con=new Conexion();
	    	st = con.conn.createStatement();
	    	rs=st.executeQuery("SELECT * FROM fact_enc_factura WHERE cod_admision="+CodAdm+" ORDER BY cod_enc_factura DESC LIMIT 1");	
	    }
	    catch(Exception ex){
	    	System.out.println("Error en MetodoFacturacion>>BuscarUltioEncabezadoCreado "+ex);
	    }	
	    return rs;
	}
	
	public java.sql.ResultSet BuscarEntidadNueva(String ceps){	
		/**
		 */
	    java.sql.ResultSet rs=null;
	    Statement st = null;
	    try{
	    	Conexion con=new Conexion();
	    	st = con.conn.createStatement();
	    	rs=st.executeQuery("SELECT * FROM adm_entidad WHERE ent_nit="+ceps+"");	
	    }
	    catch(Exception ex){
	    	System.out.println("Error en MetodoMovimientos>>InfoEncaxEntidad "+ex);
	    }	
	    return rs;
	}
	
	public java.sql.ResultSet ConsultaFacturasGeneradas(String CodAdm ){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
       // 	revisar bn esta consulta para q muestre los cargues de servicios Y los programas asociados
        	rs=st.executeQuery("SELECT fnu.cod_enc_fact_fk,fnu.consecutivo FROM fact_enc_factura fef,fact_numeradas fnu WHERE fnu.cod_enc_fact_fk=fef.cod_enc_factura AND fef.cod_admision='"+CodAdm+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>ConsultaFacturasGeneradas "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ConsultarCarguesEncabezado(String CodEncFactura ){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM fact_det_factura WHERE cod_enc_factura_fk='"+CodEncFactura+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>ConsultarCarguesEncabezado "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet VerificarRegimenEntidad(String CodEncFactura ){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT aen.regimen,fef.anticipos,fef.copago,fef.moderacion AS Descuento FROM fact_enc_factura fef,adm_entidad aen WHERE fef.cod_eps=aen.ent_nit AND fef.cod_enc_factura='"+CodEncFactura+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>VerificarRegimenEntidad "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ConsultarEncabezado(String CodEnc ){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select * from fact_enc_factura where cod_enc_factura='"+CodEnc+"' ");
        	System.out.println("select * from fact_enc_factura where cod_enc_factura='"+CodEnc+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>ConsultarEncabezado "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ConsultarMovimientosActivos(String CodAdm,String CodEps ){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT fef.* FROM fact_enc_factura fef WHERE  fef.cod_admision='"+CodAdm+"' AND fef.cod_eps='"+CodEps+"' and fef.estado='0'  GROUP BY fef.cod_enc_factura ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>ConsultarMovimientosActivos "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet ConsultarPrefacturasPendientes(String CodAdm,String CodEps ){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT fef.* FROM fact_enc_factura fef,fact_det_factura fdf WHERE fef.cod_enc_factura=fdf.cod_enc_factura_fk AND fef.cod_admision='"+CodAdm+"' AND fef.cod_eps='"+CodEps+"' and fef.estado='3'  GROUP BY fef.cod_enc_factura ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>ConsultarPrefacturasPendientes "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ConsultarEncabezadoNumeradas(String CodEncFactura ){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM fact_numeradas WHERE cod_enc_fact_fk='"+CodEncFactura+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>ConsultarEncabezadoNumeradas "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ConsultarMovimientosActivos(String CodAdm ){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT fdf.* FROM fact_enc_factura fef,fact_det_factura fdf WHERE fef.cod_enc_factura=fdf.cod_enc_factura_fk AND fef.cod_admision='"+CodAdm+"' AND (fdf.facturado='0' OR fdf.facturado='3')");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>ConsultarMovimientosActivos "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ConsultarPrefacturasVacias(String CodAdm ){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM fact_enc_factura WHERE cod_admision='"+CodAdm+"' AND estado='3'");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>ConsultarPrefacturasVacias "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet VerificarDetalleEncabezado(String CodEnca ){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM fact_det_factura fdf WHERE fdf.cod_enc_factura_fk='"+CodEnca+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>VerificarDetalleEncabezado "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet FactDetalleFacturar(String cod){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
       // 	revisar bn esta consulta para q muestre los cargues de servicios Y los programas asociados
        	rs=st.executeQuery("( SELECT cod_det_factura,tipopop,cod_programa,nombre_programa,cod_paquete, " +
        			           "nombre_paquete,fecha_realizacion,cantidad,valor,facturado,fecha,hora " +
        			           "FROM fact_det_factura " +
        			           "WHERE cod_enc_factura_fk = '"+cod+"' " +
        			           "AND tipopop='1' and facturado='0' " +
        			           "ORDER BY fecha,nombre_programa ) " +
        			           "UNION " +
        			           "(SELECT cod_det_factura,tipopop,cod_programa,nombre_programa,cod_paquete, " +
        			           "nombre_paquete,fecha_realizacion,cantidad,valor,facturado,fecha,hora " +
        			           "FROM fact_det_factura " +
        			           "WHERE cod_enc_factura_fk = '"+cod+"' " +
        			           "AND tipopop='2' and facturado='0' ) " +
        			           "ORDER BY fecha DESC, hora DESC ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>FactDetalle "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet FactDetalle(String cod){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
       // 	revisar bn esta consulta para q muestre los cargues de servicios Y los programas asociados
        	
//NI�O JESUS
		/*rs=st.executeQuery("( SELECT cod_det_factura,tipopop,cod_programa,nombre_programa,cod_paquete, " +
        			           "nombre_paquete,fecha_realizacion,cantidad,valor,facturado,fecha,hora " +
        			           "FROM fact_det_factura " +
        			           "WHERE cod_enc_factura_fk = '"+cod+"' " +
        			           "AND tipopop='1' and facturado!=5 " +
        			           "ORDER BY fecha,nombre_programa ) " +
        			           "UNION " +
        			           "( SELECT cod_det_factura,tipopop,cod_programa,nombre_programa,cod_paquete, " +
        			           "nombre_paquete,fecha_realizacion,cantidad,valor,facturado,fecha,hora " +
        			           "FROM fact_det_factura " +
        			           "WHERE cod_enc_factura_fk = '"+cod+"' " +
        			           "AND tipopop='2' and facturado!=5 ) " +
        			           "ORDER BY fecha DESC, hora DESC ");*/

//CLINICA DE LA COSTA
        	
rs=st.executeQuery("( SELECT cod_det_factura,tipopop,cod_programa,nombre_programa,cod_paquete, " +
        			           "nombre_paquete,fecha_realizacion,SUM(cantidad),valor,facturado,fecha,hora " +
        			           "FROM fact_det_factura " +
        			           "WHERE cod_enc_factura_fk = '"+cod+"' " +
        			           "AND tipopop='1' and facturado!=5 " +
        			           "GROUP BY cod_programafk,valor  ORDER BY fecha,nombre_programa ) " +
        			           "UNION " +
        			           "( SELECT cod_det_factura,tipopop,cod_programa,nombre_programa,cod_paquete, " +
        			           "nombre_paquete,fecha_realizacion,cantidad,valor,facturado,fecha,hora " +
        			           "FROM fact_det_factura " +
        			           "WHERE cod_enc_factura_fk = '"+cod+"' " +
        			           "AND tipopop='2' and facturado!=5 ) " +
        			           "ORDER BY fecha DESC, hora DESC ");



        }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>FactDetalle "+ex);
        }	
        return rs;
    }
	
	public void CerrarAdmisionParaFacturar(String CodAdm){
		PreparedStatement st = null;
	    try{
	    	Conexion con=new Conexion();
	    	st= con.conn.prepareStatement("UPDATE adm_admisiones SET atendido='1' WHERE adm_numero_ingreso='"+CodAdm+"'");
	    	st.executeUpdate();
	    	st.close();
	    	con.cerrar();
	    	
	    }
	    catch(Exception ex){
	    	System.out.println("ERROR EN MetodoMovimientos>>CerrarAdmisionParaFacturar "+ex);
	    
	    }	
	}
	
	public void EliminarEncabezado(String CodEnca){
		PreparedStatement st = null;
	    try{
	    	Conexion con=new Conexion();
	    	st= con.conn.prepareStatement("DELETE FROM fact_enc_factura WHERE cod_enc_factura='"+CodEnca+"'");
	    	st.executeUpdate();
	    	st.close();
	    	con.cerrar();
	    	
	    }
	    catch(Exception ex){
	    	System.out.println("ERROR EN MetodoMovimientos>>EliminarEncabezado "+ex);
	    
	    }	
	}
	
	public void AnularMov(String mov){
		PreparedStatement st = null;
	    try{
	    	Conexion con=new Conexion();
	    	st= con.conn.prepareStatement("delete from fact_det_factura where cod_det_factura='"+mov+"' and facturado='0' ");
	    	System.out.println("delete from fact_det_factura where cod_det_factura='"+mov+"'");
	    	st.executeUpdate();
	    	st.close();
	    	con.cerrar();
	    	
	    }
	    catch(Exception ex){
	    	System.out.println("ERROR EN MetodoMovimientos>>Anularmov "+ex);
	    
	    }	
	}
	
	
	public java.sql.ResultSet Estancias(String xx){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	 	rs=st.executeQuery("SELECT fecha_ingreso,fecha_egreso FROM fact_enc_factura WHERE cod_enc_factura="+xx+"");
          }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>Estancias "+ex);
        }	
        return rs;
    }
	

	
	
	
	////////////////////////////NUEVOS CJ///////////////////////////
	
	public java.sql.ResultSet listarFormasActoQx(){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT codigo, descripcion FROM fact_formasactoqx ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>listarFormasActoQx "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet listarFormasActoQx(String x){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT codigo, descripcion FROM fact_formasactoqx  WHERE codigo!="+x+" ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>listarFormasActoQx "+ex);
        }	
        return rs;
    }
	
	public ResultSet listarPYSoRef(String texto, String xx) throws Exception {
		/**
		 */
		  java.sql.ResultSet r=null;
	        Statement st = null;
	       Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	
	        	r=st.executeQuery("(SELECT DISTINCT 1,p.cod_referencia,p.descripcion,mt.manual_base,p.cod_programa,cs.descripcion,ft.cod_tarifa,ft.manual_tarifario,ft.valor,p.actoqx,p.medico  " +
	        					  "FROM fact_programas p,fact_tarifas ft,fact_manuales_tarifarios mt,fact_clases_servicio cs " +
	        					  "WHERE mt.cod_manual_tarifario IN (SELECT ftc.cod_manualTarifario FROM fact_convenios fc,fact_tarifas_convenios ftc WHERE fc.cod_entidad = "+xx+" AND fc.cod_convenio = ftc.cod_convenio) " +
	        					  		"AND mt.cod_manual_tarifario=ft.manual_tarifario " +
	        					  		"AND ft.programa = p.cod_programa " +
	        					  		"AND (p.cod_referencia LIKE '"+texto+"%' OR p.descripcion LIKE '"+texto+"%' ) " +
	        					  		"AND p.clase_servicio=cs.cod_clase_servicio) " +
	        					  		"UNION " +
	        					  		"(SELECT DISTINCT 2,pq.cod_referencia, pq.descripcion,mt.manual_base,pq.cod_paquete,'','','','','','' " +
	        					  		"FROM fact_tarifas ft,fact_manuales_tarifarios mt,fact_progs_paquetes fpp,fact_paquetes pq " +
	        					  		"WHERE mt.cod_manual_tarifario IN (SELECT ftc.cod_manualTarifario FROM fact_convenios fc,fact_tarifas_convenios ftc WHERE fc.cod_entidad = "+xx+" AND fc.cod_convenio = ftc.cod_convenio) " +
	        					  		"AND mt.cod_manual_tarifario=ft.manual_tarifario  " +
	        					  		"AND ft.programa = fpp.cod_programa " +
	        					  		"AND fpp.cod_paquete=pq.cod_paquete " +
	        					  		"AND (pq.cod_referencia LIKE '"+texto+"%' OR pq.descripcion LIKE '"+texto+"%' )) LIMIT 100 ");

	        	
	        	System.out.println("(SELECT DISTINCT 1,p.cod_referencia,p.descripcion,mt.manual_base,p.cod_programa,cs.descripcion,ft.cod_tarifa,ft.manual_tarifario,ft.valor,p.actoqx,p.medico  " +
  					  "FROM fact_programas p,fact_tarifas ft,fact_manuales_tarifarios mt,fact_clases_servicio cs " +
					  "WHERE mt.cod_manual_tarifario IN (SELECT ftc.cod_manualTarifario FROM fact_convenios fc,fact_tarifas_convenios ftc WHERE fc.cod_entidad = "+xx+" AND fc.cod_convenio = ftc.cod_convenio) " +
					  		"AND mt.cod_manual_tarifario=ft.manual_tarifario " +
					  		"AND ft.programa = p.cod_programa " +
					  		"AND (p.cod_referencia LIKE '"+texto+"%' OR p.descripcion LIKE '"+texto+"%' ) " +
					  		"AND p.clase_servicio=cs.cod_clase_servicio) " +
					  		"UNION " +
					  		"(SELECT DISTINCT 2,pq.cod_referencia, pq.descripcion,mt.manual_base,pq.cod_paquete,'','','','','','' " +
					  		"FROM fact_tarifas ft,fact_manuales_tarifarios mt,fact_progs_paquetes fpp,fact_paquetes pq " +
					  		"WHERE mt.cod_manual_tarifario IN (SELECT ftc.cod_manualTarifario FROM fact_convenios fc,fact_tarifas_convenios ftc WHERE fc.cod_entidad = "+xx+" AND fc.cod_convenio = ftc.cod_convenio) " +
					  		"AND mt.cod_manual_tarifario=ft.manual_tarifario  " +
					  		"AND ft.programa = fpp.cod_programa " +
					  		"AND fpp.cod_paquete=pq.cod_paquete " +
					  		"AND (pq.cod_referencia LIKE '"+texto+"%' OR pq.descripcion LIKE '"+texto+"%' )) LIMIT 100 ");

	        	
	        	
		      return r;
	}
	
	
	public void duplicarEncabezados(String cod_eps,String razon_social,String nit,String direccion,String telefono,String ciudad,String condicion_pago,String nombre_paciente,String documento,String direccion_p,String telefono_p,String tipo_afiliacion,String estrato,String fecha_ingreso,String cod_admision,String num_autorizacion,String poliza,String tipo,String fechaEgreso,String anticipos,String copago,String moderacion,String efectivo){
		//System.out.print(cod_medico);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    															
			    ps=con.conn.prepareStatement("INSERT INTO fact_enc_factura(cod_eps,"
			    		+ "razon_social,"
			    		+ "nit,"
			    		+ "direccion,"
			    		+ "telefono,"
			    		+ "ciudad,"
			    		+ "condicion_pago,"
			    		+ "nombre_paciente,"
			    		+ "documento,"
			    		+ "direccion_p,"
			    		+ "telefono_p,"
			    		+ "tipo_afiliacion,"
			    		+ "estrato,"
			    		+ "fecha_ingreso,"
			    		+ "cod_admision,"
			    		+ "num_autorizacion,"
			    		+ "poliza,"
			    		+ "tipo,"
			    		+ "fecha_egreso,"
			    		+ "anticipos,"
			    		+ "copago,moderacion,efectivo) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			    ps.setString(1,cod_eps);
			    ps.setString(2,razon_social);
			    ps.setString(3,nit);			   
			    ps.setString(4,direccion);
			    ps.setString(5,telefono);
			    ps.setString(6,ciudad);			    
			    ps.setString(7,condicion_pago);
			    ps.setString(8,nombre_paciente);
			    ps.setString(9,documento);			    
			    ps.setString(10,direccion_p);
			    ps.setString(11,telefono_p);
			    ps.setString(12,tipo_afiliacion);			    
			    ps.setString(13,estrato);
			    ps.setString(14,fecha_ingreso);
			    ps.setString(15,cod_admision);			    
			    ps.setString(16,num_autorizacion);
			    ps.setString(17,poliza);
			    ps.setString(18,tipo);
			    ps.setString(19, fechaEgreso);
			    
			    ps.setString(20, anticipos);
			    ps.setString(21, copago);
			    ps.setString(22, moderacion);
			    ps.setString(23, efectivo);
						
			    
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN. MetodoMovimientos>>duplicarEncabezado "+ex);
			}
		}
	
	
	
	public java.sql.ResultSet listarProgramasdeServicios(String texto, String eps){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT p.cod_programa,p.cod_referencia,p.descripcion,p.actoqx, " +
        			           "ft.valor,p.medico,p.idporcentajeqx,fcs.descripcion " +
        			           "FROM fact_programas p,fact_tarifas ft,fact_manuales_tarifarios mt,fact_clases_servicio fcs,fact_progs_paquetes fpp " +
        			           "WHERE mt.cod_manual_tarifario IN (SELECT ftc.cod_manualTarifario FROM fact_convenios fc,fact_tarifas_convenios ftc " +
        			           "WHERE fc.cod_entidad = "+eps+" AND fc.cod_convenio = ftc.cod_convenio) " +
        			           		"AND mt.cod_manual_tarifario=ft.manual_tarifario " +
        			           		" AND ft.programa = fpp.cod_programa " +
        			           		"AND fpp.cod_paquete = '"+texto+"' " +
        			           				"AND fpp.cod_programa=p.cod_programa " +
        			           				"AND p.clase_servicio=fcs.cod_clase_servicio");
        	
        	

        }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>listarProgramasdeServicios "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet obtenerManualBase(String eps){
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT GROUP_CONCAT(mb.descripcion) FROM fact_manuales_base mb WHERE mb.cod_manual_base IN " +
        			           "(SELECT DISTINCT fmb.cod_manual_base " +
        			           "FROM fact_manuales_tarifarios fmt,fact_manuales_base fmb " +
        			           "WHERE fmt.cod_manual_tarifario IN (SELECT ftc.cod_manualTarifario FROM fact_convenios fc,fact_tarifas_convenios ftc WHERE fc.cod_entidad = "+eps+" AND fc.cod_convenio = ftc.cod_convenio ) " +
        			           "AND fmt.manual_base=fmb.cod_manual_base)");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>obtenerManualBase "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet listarActoQx(String texto,String mb1,String mb2){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT codigo, descripcion FROM fact_actoqx WHERE descripcion_rips='"+texto+"' AND (descripcion LIKE '%"+mb1+"%' OR descripcion LIKE '%"+mb2+"%')");
        	System.out.println("SELECT codigo, descripcion FROM fact_actoqx WHERE descripcion_rips='"+texto+"' AND (descripcion LIKE '%"+mb1+"%' OR descripcion LIKE '%"+mb2+"%')");

        }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>listarActoQx "+ex);
        }	
        return rs;
    }
		
	
	public java.sql.ResultSet listarActoQx(String texto,String mb1,String mb2,String x){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT codigo, descripcion FROM fact_actoqx WHERE codigo!="+x+" AND descripcion_rips='"+texto+"' AND (descripcion LIKE '%"+mb1+"%' OR descripcion LIKE '%"+mb2+"%')");
        	System.out.println("SELECT codigo, descripcion FROM fact_actoqx WHERE codigo!="+x+" AND descripcion_rips='"+texto+"' AND (descripcion LIKE '%"+mb1+"%' OR descripcion LIKE '%"+mb2+"%')");

        }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>listarActoQx "+ex);
        }	
        return rs;
    }
	
		
	public java.sql.ResultSet listarMed(String texto){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT u.usu_codigo, nombre, apellido, ocupacion FROM seg_datos_personales dat, seg_usuario u WHERE (dat.profesion='Especialista' OR dat.profesion='Radiologo' OR dat.profesion='Sicologia' OR dat.profesion='Nutricionista' ) AND dat_codigo=u.dat_codigo_fk  AND (nombre LIKE '"+texto+"%' OR apellido LIKE '"+texto+"%' ) ORDER BY nombre ");
          }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>listarMed "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet obtenerEspPrograma(String codPr,String eps){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT fp.especialidad,t.valor " +
        			           "FROM fact_programas fp,fact_tarifas t, fact_manuales_tarifarios fmf " +
        			           "WHERE fmf.cod_manual_tarifario IN (SELECT ftc.cod_manualTarifario FROM fact_convenios fc,fact_tarifas_convenios ftc WHERE fc.cod_entidad = "+eps+" AND fc.cod_convenio = ftc.cod_convenio) " +
        			           "AND fmf.cod_manual_tarifario=t.manual_tarifario " +
        			           "AND t.programa = fp.cod_programa " +
        			           "AND fp.cod_programa = "+codPr);
          }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>obtenerEspPrograma "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet listarPHemodinamia(String eps){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT p.cod_programa,p.cod_referencia,p.descripcion,p.actoqx, " +
        			           "t.valor,p.medico,p.idporcentajeqx,fcs.descripcion " +
        			           "FROM fact_manuales_tarifarios fmf, fact_tarifas t, " +
        			           "fact_programas p,fact_progs_paquetes pp,fact_clases_servicio fcs, fact_paquetes fpq " +
        			           "WHERE fmf.cod_manual_tarifario IN (SELECT ftc.cod_manualTarifario FROM fact_convenios fc,fact_tarifas_convenios ftc WHERE fc.cod_entidad = "+eps+" AND fc.cod_convenio = ftc.cod_convenio)" +
        			           "AND fmf.cod_manual_tarifario=t.manual_tarifario " +
        			           "AND t.programa=p.cod_programa " +
        			           "AND p.cod_programa=pp.cod_programa " +
        			           "AND pp.cod_paquete = fpq.cod_paquete " +
        			           "AND fpq.descripcion LIKE 'hemodinamia%' " +
        			           "AND p.clase_servicio = fcs.cod_clase_servicio");
        	
        	
        	
        	

        }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>listarSYPHemodinamia "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet listarPrograma(String texto){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT fcs.descripcion , p.subcentro_costo FROM  fact_programas p, fact_clases_servicio fcs  WHERE p.cod_programa='"+texto+"' AND p.clase_servicio=fcs.cod_clase_servicio ");

        }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>listarP "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet PorcentajeQx(String valor){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT honorarios_cirujano,horarios_anestesiologos,horarios_ayudantia,derecho_sala,materiales,otros FROM fact_actoqx WHERE codigo='"+valor+"' ");
          }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>PorcentajeQx "+ex);
        }	
        return rs;
    }
	
	
	public void CargarDetalle(String fecha,String hora,String pop,String cod_programafk,String cod_programa,String nombre_programa,String cod_paquete,String nombre_paquete,String clase_servicio,String fecha_realizacion,String cantidad,String valor,String cod_usuario,String cod_enc_factura_fk,String cod_medico,String subc,String formaqx, String p){
		//System.out.print(cod_medico);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    															
			    ps=con.conn.prepareStatement("insert into fact_det_factura(fecha,hora,tipopop,cod_programafk,cod_programa,nombre_programa,cod_paquete,nombre_paquete,clase_servicio,fecha_realizacion,cantidad,valor,cod_usuario,cod_enc_factura_fk,cod_medico,subcentrodecosto,formaactoqx,porcentaje)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			    ps.setString(1,fecha);
			    ps.setString(2,hora);
			    ps.setString(3,pop);
			    ps.setString(4,cod_programafk);
			    ps.setString(5,cod_programa);
			    ps.setString(6,nombre_programa);
			    ps.setString(7,cod_paquete);
			    ps.setString(8,nombre_paquete);
			    ps.setString(9,clase_servicio);
			    ps.setString(10,fecha_realizacion);
			    ps.setString(11,cantidad);
			    ps.setString(12,valor);
			    ps.setString(13,cod_usuario);
			    ps.setString(14,cod_enc_factura_fk);
			    ps.setString(15,cod_medico);
			    ps.setString(16,subc);
			    ps.setString(17,formaqx);
			    ps.setString(18,p);
			    
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN. MetodoMovimientos>>CargarDetalle "+ex);
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
	
	
	
	public ResultSet ActualizarNumAutorizacion(String Enca,String noauto){
		/*Guarda el numero de autorizacion en la tabla fact_enc_factura simepre y cuando los enc esten en cero y pertenezcan a la eps actual del paciente*/
		ResultSet rs = null;
		PreparedStatement st = null;
	    try{
	    	Conexion con=new Conexion();
	    	st= con.conn.prepareStatement("UPDATE fact_enc_factura SET num_autorizacion='"+noauto+"' WHERE cod_enc_factura='"+Enca+"' ");
	    	System.out.println(" UPDATE fact_enc_factura SET num_autorizacion='"+noauto+"' WHERE cod_enc_factura='"+Enca+"' ");
	    	st.executeUpdate();
	    	st.close();
	    	con.cerrar();
	    	
	    }
	    catch(Exception ex){
	    	System.out.println("ERROR EN MetodoFacturacion>>ActualizarNumAutorizacion"+ex);
	    
	    }
		return rs;	
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		
	
	
}//fin metodo movimientos
