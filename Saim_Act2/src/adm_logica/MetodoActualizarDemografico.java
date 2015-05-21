/**
 * NOMBRE DE LA CLASE: MetodoActualizarDemografico
 * AUTOR: Oscar Rolong Schweiger
 * DESCRIPCION: En este metodo se encuentra lo necesario para consultar y actualizar
 * 				los datos demograficos del paciente.
 * 				
 */
package adm_logica;

import java.sql.PreparedStatement;
import java.sql.Statement;

import adm_bean.Acompanante;

public class MetodoActualizarDemografico {
	
	public java.sql.ResultSet obtenerDatosPaciente(String ced,String tipodoc){
		/**
		 * en este metodo se obtienen los datos del paciente 
		 * al cual se le quiere modificar algun dato demografico
		 * y tiene como datos de entrada el tipo de documento y el numero del documento.  
		 * */
		
	    java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT op.ocu_nombre,mun.nombre,ent.nombre_entidad,pa.tipo_documento,pa.numero_documento,pa.tipo_afiliacion,pa.nivel_cotizante,pa.primer_apellido,pa.segundo_apellido,pa.nombre,pa.genero,pa.nacionalidad,pa.direccion,pa.telefono_fijo,pa.telefono_oficina,pa.telefono_celular,pa.empresa_labora,pa.zona_residencial,pa.religion,pa.estado_civil,pa.raza,pa.estrato,pa.email,pa.pac_codigo_paciente,pa.fecha_nacimiento,de.nombre,pa.ocu_codigo_fk,pa.mun_codigo_fk,pa.conv_numero_contrato_fk,pa.longitud, pa.latitud,pa.barrio FROM adm_paciente pa,adm_ocupacion_paciente op,adm_municipio mun,adm_departamento de,adm_convenio con,adm_entidad ent WHERE pa.tipo_documento='"+tipodoc+"' AND pa.numero_documento='"+ced+"' AND pa.ocu_codigo_fk=op.ocu_codigo AND pa.mun_codigo_fk=mun.muni_cod AND mun.dept_codigo_fk=de.dept_codigo AND ent.ent_nit=con.ent_nit_contratante_fk AND pa.conv_numero_contrato_fk=con.conv_numero_contrato");
        }
        catch(Exception ex){
        	
        }
      
        return rs;
        
    }
	
	public java.sql.ResultSet obtenerDatosPacienteActuaDemogra(String ced,String tipodoc){
		/**
		 * en este metodo se obtienen los datos del paciente 
		 * al cual se le quiere modificar algun dato demografico
		 * y tiene como datos de entrada el tipo de documento y el numero del documento.  
		 * */
		
	    java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT op.ocu_nombre,mun.nombre,ent.nombre_entidad,pa.tipo_documento,pa.numero_documento,pa.tipo_afiliacion,pa.nivel_cotizante,pa.primer_apellido,pa.segundo_apellido,pa.nombre,pa.genero,pa.nacionalidad,pa.direccion,pa.telefono_fijo,pa.telefono_oficina,pa.telefono_celular,pa.empresa_labora,pa.zona_residencial,pa.religion,pa.estado_civil,pa.raza,pa.estrato,pa.email,pa.pac_codigo_paciente,pa.fecha_nacimiento,de.nombre,pa.ocu_codigo_fk,pa.mun_codigo_fk,pa.conv_numero_contrato_fk,pa.longitud, pa.latitud,pa.barrio,de.dept_codigo,de.pais_codigo_fk FROM adm_paciente pa,adm_ocupacion_paciente op,adm_municipio mun,adm_departamento de,adm_convenio con,adm_entidad ent WHERE pa.tipo_documento='"+tipodoc+"' AND pa.numero_documento='"+ced+"' AND pa.ocu_codigo_fk=op.ocu_codigo AND pa.mun_codigo_fk=mun.muni_cod AND mun.dept_codigo_fk=de.dept_codigo AND ent.ent_nit=con.ent_nit_contratante_fk AND pa.conv_numero_contrato_fk=con.conv_numero_contrato");
        }
        catch(Exception ex){
        	
        }
      
        return rs;
        
    }
	
	public java.sql.ResultSet ObtenerDatosEntidad(String CodEnt){
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM adm_entidad WHERE ent_nit="+CodEnt+"");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoActualizarDemografico>>ObtenerDatosEntidad "+ex);
        }
      
        return rs;
	}
	
	
	public java.sql.ResultSet ObtenerAdmisionSinFacturar(String TipoDocumento,String NumeroDocumento){
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT aad.adm_numero_ingreso FROM adm_admisiones aad,adm_paciente apac WHERE aad.pac_codigo_paciente_fk=apac.pac_codigo_paciente  AND apac.tipo_documento='"+TipoDocumento+"' AND apac.numero_documento='"+NumeroDocumento+"' AND aad.atendido=0 ORDER BY adm_numero_ingreso DESC LIMIT 1 ");
        	System.out.println("SELECT aad.adm_numero_ingreso FROM adm_admisiones aad,adm_paciente apac WHERE aad.pac_codigo_paciente_fk=apac.pac_codigo_paciente  AND apac.tipo_documento='"+TipoDocumento+"' AND apac.numero_documento='"+NumeroDocumento+"' AND aad.atendido=0 ORDER BY adm_numero_ingreso DESC LIMIT 1 ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoActualizarDemografico>>ObtenerAdmisionSinFacturar "+ex);
        }
      
        return rs;
	}
	
	
	public java.sql.ResultSet DatosEncabezado(String CodAdmision){
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM fact_enc_factura WHERE cod_admision="+CodAdmision+" ORDER BY cod_enc_factura DESC  LIMIT 1");
        	System.out.println("SELECT * FROM fact_enc_factura WHERE cod_admision="+CodAdmision+" ORDER BY cod_enc_factura DESC  LIMIT 1");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoActualizarDemografico>>DatosEncabezado "+ex);
        }
      
        return rs;
	}
	
	
	
	public java.sql.ResultSet obtenerConvenio(String codEnt){
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select conv_numero_contrato from adm_convenio where ent_nit_contratante_fk='"+codEnt+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoActualizarDemografico>>obtenerConvenio "+ex);
        }
      
        return rs;
	}
	
	public void ActualizarEncabezadoFactura(String CodEntidad,String RazonSocial, String Nit, String DireccionEntidad, String TelefonoEnt,String CiudadEnt, String CondicionPago,String CodAdmision,String NombreCompleto,String DocumentoCompleto){
	       PreparedStatement st = null;
	        try{
	        	Conexion con=new Conexion();

	        	st= con.conn.prepareStatement("UPDATE fact_enc_factura SET nombre_paciente='"+NombreCompleto+"',documento='"+DocumentoCompleto+"' WHERE cod_admision = '"+CodAdmision+"' and estado='0' ");
	        	st.executeUpdate();
	        	st.close();
	        	con.cerrar();
	        	
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        }	
	    }
	
	
	public void ActualizarEncabezadoFacturaDatos(String CodAdmision,String NombreCompleto,String DocumentoCompleto){
		       PreparedStatement st = null;
		        try{
		        	Conexion con=new Conexion();

		        	st= con.conn.prepareStatement("UPDATE fact_enc_factura SET  nombre_paciente = '"+NombreCompleto+"', documento = '"+DocumentoCompleto+"' WHERE cod_admision = '"+CodAdmision+"'   AND valor = '0'  ");
		        	st.executeUpdate();
		        	st.close();
		        	con.cerrar();
		        	
		        }
		        catch(Exception ex){
		        	ex.getMessage();
		        }	
		    }
	
	
public void Actualizar( String tip,String ced, String afiliacion, String nivel, String pape,String sape, String nom,String gene,String nacionali,String dir,String tel,String telofi,String telcel,String ocu,String emp,String zona,String reli,String estadoci,String ra,String estra,String ema,String mun_cod,String fechanaci,String numcontra,String codpac,String longitud,String latitud,String barrio){

	/**
	 * en este metodo se obtienen los datos que fueron 
	 * modificados para su posterior actualizacion.
	 * se tienen como datos de entrada todos los datos que fueron modificados
	 * */
	
	       PreparedStatement st = null;
	        try{
	        	Conexion con=new Conexion();

	        	st= con.conn.prepareStatement("update adm_paciente set tipo_documento='"+tip+"',numero_documento='"+ced+"',tipo_afiliacion='"+afiliacion+"',nivel_cotizante='"+nivel+"',primer_apellido='"+pape+"',segundo_apellido='"+sape+"',nombre='"+nom+"',genero='"+gene+"',nacionalidad='"+nacionali+"',direccion='"+dir+"',telefono_fijo='"+tel+"',telefono_oficina='"+telofi+"',telefono_celular='"+telcel+"',ocu_codigo_fk='"+ocu+"',empresa_labora='"+emp+"',zona_residencial='"+zona+"',religion='"+reli+"',estado_civil='"+estadoci+"',raza='"+ra+"',estrato='"+estra+"',email='"+ema+"',mun_codigo_fk='"+mun_cod+"',fecha_nacimiento='"+fechanaci+"',conv_numero_contrato_fk='"+numcontra+"',longitud='"+longitud+"',latitud='"+latitud+"',barrio='"+barrio+"' where pac_codigo_paciente='"+codpac+"'");
	        	st.executeUpdate();
	        	st.close();
	        	con.cerrar();
	        	
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        }	
	    }


public void CrearEncabezadoFactura(String cod_eps,String razon_social,String nit,String direccion,String telefono,String ciudad,String condicion_pago,String nombre_paciente,String documento,String direccion_p,String telefono_p,String tipo_afiliacion,String estrato,String fecha_ingreso,String fecha_egreso,String cod_admision,String num_autorizacion,String tipo){
	try{
		PreparedStatement ps = null;
	    Conexion con=new Conexion();
	 
	    //ps=con.conn.prepareStatement("INSERT INTO fact_enc_factura (cod_eps,razon_social,nit,direccion,telefono,ciudad,condicion_pago,nombre_paciente,documento,direccion_p,telefono_p,tipo_afiliacion,estrato,fecha_ingreso,fecha_egreso,cod_admision,num_autorizacion,cod_usuario_fk,poliza,fecha,hora,tipo,valor,valorletras,anticipos,copago,moderacion,efectivo,estado) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");				
	    ps=con.conn.prepareStatement("INSERT INTO fact_enc_factura (cod_eps,razon_social,nit,direccion,telefono,ciudad,condicion_pago,nombre_paciente,documento,direccion_p,telefono_p,tipo_afiliacion,estrato,fecha_ingreso,fecha_egreso,cod_admision,num_autorizacion,tipo) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
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
		ps.setString(15,fecha_egreso);
		ps.setString(16,cod_admision);
		ps.setString(17,num_autorizacion);
		ps.setString(18,tipo);
		/*//ps.setString(18,cod_usuario_fk);
		ps.setString(18,poliza);
		ps.setString(19,fecha);
		ps.setString(20,hora);
		
		ps.setString(22,valor);
		ps.setString(23,valorletras);
		ps.setString(24,anticipos);
		ps.setString(25,copago);
		ps.setString(26,moderacion);
		ps.setString(28,efectivo);
		ps.setString(29,estado);*/
		ps.executeUpdate();
		
		ps.close();
		con.cerrar();

	}catch(Exception ex){
		System.out.print("Error en MetodoAcompanante>>CrearEncabezadoFactura "+ex);
	}
	
	
}



}
