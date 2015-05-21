package fact_metodo;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

import fact_bean.Entidad;
import adm_logica.Conexion;

public class MetodoCrearEntidad {
	
	/**
	 * Se crea la entidad con los datos pasados como par&aacute;metros.
	 * @param NitEntidad
	 * @param NombreEntidad
	 * @param dir
	 * @param tel
	 * @param fax
	 * @param contacto
	 * @param depto
	 * @param ciudad
	 * @param obs
	 * @param regimen
	 * @param modPago
	 * @param codPrestador
	 * @param estado
	 * @param NombreRepresentante
	 * @param TipoIdentificacion
	 * @param NumeroIdentificacion
	 */
	public void CrearEntidad(String NitEntidad, String NombreEntidad, String dir, String tel, String fax, String contacto, String depto, String ciudad, String obs, String regimen, String modPago,String codPrestador, String estado, String NombreRepresentante, String TipoIdentificacion, String NumeroIdentificacion){
		Entidad e = new Entidad();
		e.setNitEntidad(NitEntidad);
		e.setNombreEntidad(NombreEntidad);
		e.setNombreRepresentante(NombreRepresentante);
		e.setTipoIdentificacion(TipoIdentificacion);
		e.setNumeroIdentificacion(NumeroIdentificacion);
		e.setDireccion(dir);
		e.setTelefono(tel);
		e.setFax(fax);
		e.setContacto(contacto);
		e.setDepartamento(depto);
		e.setCiudad(ciudad);
		e.setObservacion(obs);
		e.setRegimen(regimen);
		e.setModalidadPago(modPago);
		e.setCodPrestador(codPrestador);
		e.setEstado(estado);
		try{
			PreparedStatement ps = null;
		    Conexion con=new Conexion();
		    ps=con.conn.prepareStatement("INSERT INTO adm_entidad(ent_nit_contratante, representante_legal, tipo_identificacion, numero_identificacion, nombre_entidad, direccion, telefono, fax, contacto, estado, ciudad, observacion, regimen, modalidad_pago, cod_prestador, depto) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
		    ps.setString(1, e.getNitEntidad());
		    ps.setString(2, e.getNombreRepresentante());
		    ps.setString(3, e.getTipoIdentificacion());
		    ps.setString(4, e.getNumeroIdentificacion());
		    ps.setString(5, e.getNombreEntidad());
		    ps.setString(6, e.getDireccion());
		    ps.setString(7, e.getTelefono());
		    ps.setString(8, e.getFax());
		    ps.setString(9, e.getContacto());
		    ps.setString(10, e.getEstado());
		    ps.setString(11, e.getCiudad());
		    ps.setString(12, e.getObservacion());
		    ps.setString(13, e.getRegimen());
		    ps.setString(14, e.getModalidadPago());
		    ps.setString(15, e.getCodPrestador());
		    ps.setString(16, e.getDepartamento());
		 	ps.executeUpdate();
			ps.close();
			con.cerrar();
		}catch(Exception ex){
        	System.err.println("ERROR EN MetodoCrearEntidad>>CrearEntidad\n"+ex);
		}
	}
	
	/**
	 * Se crea el convenio
	 * @param TarifaFacturacion
	 * @param ValorContrato
	 * @param FechaInicio
	 * @param FechaFin
	 * @param Modalidad
	 * @param TipoContrato
	 * @param NivelComplejidad
	 * @param CodigoEntidad
	 */
	public void CrearConvenio(String TarifaFacturacion, String CodigoEntidad){
		Entidad e = new Entidad();
		e.setTarifaFacturacion(TarifaFacturacion);
	/*	e.setValorContrato(ValorContrato);
		e.setFechaInicio(FechaInicio);
		e.setFechaFin(FechaFin);
		e.setModalidad(Modalidad);
		e.setTipoContrato(TipoContrato);
		e.setNivelComplejidad(NivelComplejidad);*/
		e.setCodigoEntidad(CodigoEntidad);
		try{
			PreparedStatement ps = null;
		    Conexion con = new Conexion();
		    ps = con.conn.prepareStatement("INSERT INTO adm_convenio(tarifa_facturacion, ent_nit_contratante_fk, conv_numero_contrato) VALUES(?,?,?)");
		    ps.setString(1, TarifaFacturacion);//, conv_numero_contrato
		    ps.setString(2, CodigoEntidad);
		    ps.setString(3, CodigoEntidad);
		 	ps.executeUpdate();
			ps.close();
			con.cerrar();
		}catch(Exception ex){
        	System.err.println("ERROR EN MetodoCrearEntidad>>CrearConvenio\n"+ex);
		}
	}
	/**
	 * Busca una entidad por un c&oacute;digo NIT espec&iacute;fico.
	 * @param NitEntidad NIT de la Entidad
	 * @return {@link ResultSet}
	 */
	
	public ResultSet BuscarTipoEntidad(){
        ResultSet rs = null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs = st.executeQuery("SELECT * FROM fact_tipo_entidad ORDER BY tipo_empresa");
        }
        catch(Exception ex){
        	System.err.println("ERROR en MetodoCrearEntidad>>BuscarTipoEntidad "+ex);
        }
        return rs;
    }
	
	public ResultSet BuscarEntidad(String NombreEntidad){
        ResultSet rs = null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs = st.executeQuery("SELECT ent_nit FROM adm_entidad WHERE nombre_entidad='"+NombreEntidad+"';");
        }
        catch(Exception ex){
        	System.err.println("ERROR en MetodoCrearEntidad>>BuscarEntidad\n"+ex);
        }
        return rs;
    }
	
	/**
	 * Busca una entidad con un c&oacute;digo espec&iacute;fico.
	 * @param CodEntidad
	 * @return {@link ResultSet}
	 */
	public ResultSet BuscarEntidadPorCodigo(String CodEntidad){
        ResultSet rs = null;
        Statement st = null;
        try{
        	Conexion con = new Conexion();
        	st = con.conn.createStatement();
        	rs = st.executeQuery("SELECT ent_nit_contratante, nombre_entidad, direccion, telefono, fax, contacto, depto, ciudad, observacion, regimen, modalidad_pago, cod_prestador, estado, numero_identificacion, representante_legal FROM adm_entidad WHERE ent_nit="+CodEntidad+";");
        }
        catch(Exception ex){
        	System.err.println("ERROR en MetodoCrearEntidad>>BuscarEntidadPorCodigo\n"+ex);
        }
        return rs;
    }
	
	/**
	 * 
	 * @return {@link ResultSet}
	 */
	public ResultSet BuscarTodasEntidad(){
        ResultSet rs = null;
        Statement st = null;
        try{
        	Conexion con = new Conexion();
        	st = con.conn.createStatement();
        	rs = st.executeQuery("SELECT ent_nit, nombre_entidad FROM adm_entidad order by nombre_entidad");
        }
        catch(Exception ex){
        	System.err.println("ERROR en MetodoCrearEntidad>>BuscarTodasEntidad\n"+ex);
        }	
        return rs;
    }
	
	/**
	 * Actualiza los datos de una entidad espec&iacute;fica.
	 * @param NitEntidad
	 * @param NombreEntidad
	 * @param dir
	 * @param tel
	 * @param fax
	 * @param contacto
	 * @param depto
	 * @param ciudad
	 * @param obs
	 * @param regimen
	 * @param modPago
	 * @param codPrestador
	 * @param estado
	 * @param NombreRepresentante
	 * @param TipoIdentificacion
	 * @param NumeroIdentificacion
	 * @param CodEntidad
	 */
	public void ActualizarEntidad(String NitEntidad, String NombreEntidad, String dir, String tel, String fax, String contacto, String depto, String ciudad, String obs, String regimen, String modPago,String codPrestador, String estado, String NombreRepresentante, String TipoIdentificacion, String NumeroIdentificacion, String CodEntidad){
		Entidad e = new Entidad();
		e.setNitEntidad(NitEntidad);
		e.setNombreEntidad(NombreEntidad);
		e.setNombreRepresentante(NombreRepresentante);
		e.setTipoIdentificacion(TipoIdentificacion);
		e.setNumeroIdentificacion(NumeroIdentificacion);
		e.setDireccion(dir);
		e.setTelefono(tel);
		e.setFax(fax);
		e.setContacto(contacto);
		e.setDepartamento(depto);
		e.setCiudad(ciudad);
		e.setObservacion(obs);
		e.setRegimen(regimen);
		e.setModalidadPago(modPago);
		e.setCodPrestador(codPrestador);
		e.setEstado(estado);
		PreparedStatement ps = null;
		Conexion con = null;
	    try{
	     	con = new Conexion();
	     	ps = con.conn.prepareStatement("UPDATE adm_entidad SET ent_nit_contratante=?, representante_legal=?, tipo_identificacion=?, numero_identificacion=?, nombre_entidad=?, direccion=?, telefono=?, fax=?, contacto=?, estado=?, ciudad=?, observacion=?, regimen=?, modalidad_pago=?, cod_prestador=?, depto=? WHERE ent_nit='"+CodEntidad+"';");
	     	ps.setString(1, e.getNitEntidad());
		    ps.setString(2, e.getNombreRepresentante());
		    ps.setString(3, e.getTipoIdentificacion());
		    ps.setString(4, e.getNumeroIdentificacion());
		    ps.setString(5, e.getNombreEntidad());
		    ps.setString(6, e.getDireccion());
		    ps.setString(7, e.getTelefono());
		    ps.setString(8, e.getFax());
		    ps.setString(9, e.getContacto());
		    ps.setString(10, e.getEstado());
		    ps.setString(11, e.getCiudad());
		    ps.setString(12, e.getObservacion());
		    ps.setString(13, e.getRegimen());
		    ps.setString(14, e.getModalidadPago());
		    ps.setString(15, e.getCodPrestador());
		    ps.setString(16, e.getDepartamento());
	     	ps.executeUpdate();
	     	ps.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.err.println("ERROR EN MetodoCrearEntidad>>ActualizarEntidad\n"+ex);
	     }	
	}
	
	/*
	 * Averigua que exista el NIT para una c&oacute;digo de entidad espec&iacute;fica.
	 * @param CodE
	 * @return {@link ResultSet}
	 *
	public boolean existNitEntidad(String CodE){
        ResultSet rs = null;
        Statement st = null;
        try{
        	Conexion con = new Conexion();
        	st = con.conn.createStatement();
        	rs = st.executeQuery("SELECT ent_nit_contratante, nombre_entidad, direccion, telefono, fax, contacto, depto, ciudad, observacion, regimen, modalidad_pago, cod_prestador, estado, numero_identificacion, representante_legal FROM adm_entidad WHERE ent_nit="+CodE+";");
        	rs.getStatement().getConnection().close();
        	con.cerrar();
        	return true;
        }
        catch(Exception ex){
        	System.err.println("ERROR en MetodoCrearEntidad>>BuscarEntidadPorCodigo\n"+ex);
        	return false;
        }
    }*/
	
	public void ActualizarDatos(String NitEntidad, String NombreEntidad, String regimen, String dir,String depto, String ciudad,String fax, String Estado,String CodEntidad) {
		PreparedStatement ps = null;
		try {
			
			Conexion con = new Conexion();
			ps = con.conn.prepareStatement("UPDATE cont_terceros SET numero_documento='"+NitEntidad+"', razon_social='"+NombreEntidad+"', tipo_regimen='"+regimen+"', direccion='"+dir+"', departamento='"+depto+"', ciudad='"+ciudad+"', fax='"+fax+"',estado='"+Estado+"' WHERE ent_nit='"+CodEntidad+"'");
			System.out.println("UPDATE cont_terceros SET numero_documento='"+NitEntidad+"', razon_social='"+NombreEntidad+"', tipo_regimen='"+regimen+"', direccion='"+dir+"', departamento='"+depto+"', ciudad='"+ciudad+"', fax='"+fax+"',estado='"+Estado+"' WHERE ent_nit='"+CodEntidad+"'");
			
			ps.execute();
			ps.close();
			con.cerrar();
		} catch (Exception ex) {
			ex.getMessage();
			System.out.println("Error Update cont_terceros "+ ex);
		}
	}
	
}
