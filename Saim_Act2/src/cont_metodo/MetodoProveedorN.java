package cont_metodo;

import java.sql.PreparedStatement;
import java.sql.Statement;

import adm_logica.Conexion;
import cont_bean.Cuentas;
import cont_bean.Proveedor;

public class MetodoProveedorN {
	
	public void CrearProveedor(
			String Tipo_identificacion,String Numero_identificacion,String Primer_nombre,String segundo_nombre,
			String Primer_apellido,String segundo_apellido,String razon_social,String regimen,
			String direccion,String telefono,String contacto,String tel_contacto,String email_contacto,
			String cbdep,String cbmun,String DiasPlazo,String FechaIngreso,String PagWeb,String fax, String ent_nit){

			try{
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("INSERT INTO cont_terceros (tipo_identificacion,numero_documento,primer_nombre,segundo_nombre," +
				    		"primer_apellido,segundo_apellido,razon_social,nombre_razonsoc, autoretenedor, tipo_regimen, direccion, telefono, contacto, " +
				    		"telcontacto,emailcontac, departamento,ciudad, operacion_extranjera," +
				    		"dias_plazo,estado,fecha_ingreso,paginaweb,digito_verificacion,fax,ent_nit)" +
				    		"VALUES (?, ?, ?, ?, ?, ?, ?, '', '', ?, ?, ?, ?, ?, ?, ?, ?, '',?, '0', ?, ?,'',?,?) ");
				    ps.setString(1,Tipo_identificacion);
				    ps.setString(2,Numero_identificacion);
				    ps.setString(3,Primer_nombre);
				    ps.setString(4,segundo_nombre);
				    ps.setString(5,Primer_apellido);
				    ps.setString(6,segundo_apellido);
				    ps.setString(7,razon_social);
				    ps.setString(8,regimen);
				    ps.setString(9,direccion);
				    ps.setString(10,telefono);
				    ps.setString(11,contacto);
				    ps.setString(12,tel_contacto);
				    ps.setString(13,email_contacto);
				    ps.setString(14,cbdep);
				    ps.setString(15,cbmun);
				    ps.setString(16,DiasPlazo);
				    ps.setString(17,FechaIngreso);
				    ps.setString(18,PagWeb);
				    ps.setString(19,fax);
				    ps.setString(20,ent_nit);
				 	ps.executeUpdate();
					ps.close();
					con.cerrar();				
				}catch(Exception ex){
		        	System.out.println("ERROR EN MetodoProveedor>>CrearProveedor "+ex);
				}

			}
	
	
	// busca el codigo del municipio
	public java.sql.ResultSet CodMun(String nombre) {
		java.sql.ResultSet rs = null;
		Statement st = null;
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery(" SELECT nombre FROM adm_municipio WHERE muni_cod='"+ nombre + "'");
		} catch (Exception ex) {
			System.out.println("Error select municipio " + ex);
		}
		return rs;
	}
	
	// busca el codigo del municipio
	public java.sql.ResultSet Tercero(String nombre) {
		java.sql.ResultSet rs = null;
		Statement st = null;
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("SELECT * FROM cont_terceros WHERE numero_documento='"+ nombre + "'");
		} catch (Exception ex) {
			System.out.println("Error select municipio " + ex);
		}
		return rs;
	}
	
	public void InsertLog(String fecha,String hora,String usuario,String accion){

			try{
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("INSERT INTO com_log (fecha,hora,usuario,accion)" +
				    		"VALUES (?, ?, ?, ?) ");
				    ps.setString(1,fecha);
				    ps.setString(2,hora);
				    ps.setString(3,usuario);
				    ps.setString(4,accion);
				 	ps.executeUpdate();
					ps.close();
					con.cerrar();				
				}catch(Exception ex){
		        	System.out.println("ERROR EN MetodoProveedor>>CrearProveedor "+ex);
				}

			}
		


		
	public java.sql.ResultSet BuscarTerceroAutocompletar(String Parametro){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM cont_terceros WHERE numero_documento LIKE '%"+Parametro+"%' limit 40");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProveedor>>BuscarTerceroAutocompletar "+ex);
        }	
        return rs;
    }



	
	public java.sql.ResultSet Departamentos(){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM adm_departamento ORDER BY nombre ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProveedor>>Departamentos "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet Municipios(String CodDept){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM adm_municipio WHERE dept_codigo_fk="+CodDept+" ORDER BY nombre ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProveedor>>Municipios "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ListarTodosTerceros(){	
		
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select * from cont_terceros where ent_nit='0' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProveedor>>ListarTodosTerceros "+ex);
        }	
        return rs;
    }


	
	public java.sql.ResultSet BuscarProveedor(String numero_documento){	
		
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select * from cont_terceros where numero_documento='"+numero_documento+"'");
        	System.out.println("select * from cont_terceros where numero_documento='"+numero_documento+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProveedor>>BuscarProveedor "+ex);
        }	
        return rs;
    }

	
	public void ActualizarTercero(String tipo_identificacion,
			String numero_documento,
			String nombre_razonsoc,
			String Nombre1,
			String Nombre2,
			String Apellido1,
			String Apellido2,
			String RazonSocialDian,
			String OperExt,
			String direccion,
			String telefono,
			String telcontacto,
			String emailcontac,
			String IndCliente,
			String IndProveedor,
			String IndEmpleado,
			String Municipio,
			String Departamento,
			String txtBanco,String cmbTipoCuenta,String cmbEstado,
			String txtPorcretIva,String txtFechaIngreso,String txtPorcretIca,String txtPagWeb,String txtDiasPlazo,String txtLineaProducto,
			String cmbAutoRetenedor,String txtNumCuenta,
			String CodTercero){

		PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE  cont_terceros SET " +
	     			" tipo_identificacion = '"+tipo_identificacion+"', numero_documento = '"+numero_documento+"', " +
	     					" primer_nombre = '"+Nombre1+"', segundo_nombre = '"+Nombre2+"', " +
	     							" primer_apellido = '"+Apellido1+"', segundo_apellido = '"+Apellido2+"', " +
	     									" razon_social = '"+nombre_razonsoc+"', nombre_razonsoc = '"+RazonSocialDian+"', " +
	     											" direccion = '"+direccion+"',telefono = '"+telefono+"' , telcontacto='"+telcontacto+"', " +
	     													" emailcontac='"+emailcontac+"', departamento='"+Departamento+"', ciudad='"+Municipio+"', " +
	     															" operacion_extranjera='"+OperExt+"',Ind_Cliente="+IndCliente+",Ind_Proveedor="+IndProveedor+", " +
	     																	" Ind_Empleado="+IndEmpleado+",porc_retiva='"+txtPorcretIva+"',porc_retica='"+txtPorcretIca+"', " +
	     																			"dias_plazo='"+txtDiasPlazo+"',estado='"+cmbEstado+"',fecha_ingreso='"+txtFechaIngreso+"', " +
	     																			"paginaweb='"+txtPagWeb+"',linea_producto='"+txtLineaProducto+"',cuenta_banco='"+Municipio+"', " +
	     																					"banco='"+txtBanco+"',tipo_cuenta='"+cmbTipoCuenta+"',autoretenedor='"+cmbAutoRetenedor+"' WHERE codigo = "+CodTercero+" ");

	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("ERROR EN MetodoProveedor>>ActualizarTercero "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	
	public void ActualizarTerceroCom(String tipo_identificacion,
			String numero_documento,
			String nombre_razonsoc,
			String direccion,
			String telefono,
			String Departamento,
			String Municipio,
			String Contacto,
			String telcontacto,
			String emailcontac,
			String fax,
			String regimen,
			String txtDiasPlazo,
			String txtFechaIngreso,
			String txtPagWeb,
			String cmbEstado,
			String CodTercero){

		PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE  cont_terceros SET " +
	     			" tipo_identificacion = '"+tipo_identificacion+"', numero_documento = '"+numero_documento+"', " +
	     			" razon_social = '"+nombre_razonsoc+"', fax = '"+fax+"', " +
	     			" direccion = '"+direccion+"',telefono = '"+telefono+"' , telcontacto='"+telcontacto+"', " +
	     			" emailcontac='"+emailcontac+"', departamento='"+Departamento+"', ciudad='"+Municipio+"', " +
	     			" contacto='"+Contacto+"'," +
	     			"dias_plazo='"+txtDiasPlazo+"',estado='"+cmbEstado+"',fecha_ingreso='"+txtFechaIngreso+"', " +
	     			"paginaweb='"+txtPagWeb+"' " +
	     			" WHERE codigo = "+CodTercero+" ");

	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("ERROR EN MetodoProveedor>>ActualizarTercero "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	
	
	public void ActualizarTerceroCont(String OperExt,
			String IndCliente,
			String IndProveedor,
			String IndEmpleado,
			String txtBanco,String cmbTipoCuenta,
			String txtPorcretIva,String txtPorcretIca, String cmbAutoRetenedor,String txtNumCuenta,
			String CodTercero){

		PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE  cont_terceros SET " +
	     								  " operacion_extranjera='"+OperExt+"',Ind_Cliente="+IndCliente+",Ind_Proveedor="+IndProveedor+", " +
	     								  " Ind_Empleado="+IndEmpleado+",porc_retiva='"+txtPorcretIva+"',porc_retica='"+txtPorcretIca+"', " +
	     								  " cuenta_banco='"+txtNumCuenta+"',banco='"+txtBanco+"',tipo_cuenta='"+cmbTipoCuenta+"',autoretenedor='"+cmbAutoRetenedor+"' " +
	     								  "WHERE codigo = "+CodTercero+" ");

	     	
			System.out.println("UPDATE  cont_terceros SET " +
					  " operacion_extranjera='"+OperExt+"',Ind_Cliente="+IndCliente+",Ind_Proveedor="+IndProveedor+", " +
						  " Ind_Empleado="+IndEmpleado+",porc_retiva='"+txtPorcretIva+"',porc_retica='"+txtPorcretIca+"', " +
						  " cuenta_banco='"+txtNumCuenta+"',banco='"+txtBanco+"',tipo_cuenta='"+cmbTipoCuenta+"',autoretenedor='"+cmbAutoRetenedor+"' " +
						  "WHERE codigo = "+CodTercero+" ");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("ERROR EN MetodoProveedor>>ActualizarTercero "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	
	
	public void ActualizarProveedor(String tipo_identificacion,String numero_documento,String nombre_razonsoc,String autoretenedor,String tipo_regimen,String direccion,String telefono,String contacto,String telcontacto,String emailcontac,String codigo){
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("update cont_terceros set tipo_identificacion='"+tipo_identificacion+"',numero_documento='"+numero_documento+"',nombre_razonsoc='"+nombre_razonsoc+"',autoretenedor='"+autoretenedor+"',tipo_regimen='"+tipo_regimen+"',direccion='"+direccion+"',telefono='"+telefono+"',contacto='"+contacto+"',telcontacto='"+telcontacto+"',emailcontac='"+emailcontac+"' where codigo='"+codigo+"'");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("ERROR EN MetodoProveedor>>ActualizarProveedor "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	
	 

/***********************************************************/
	public java.sql.ResultSet BuscarDpto(){	
		
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT Nombre FROM adm_departamento where pais_codigo_fk=" + 1);
        	//System.out.println("select * from cont_terceros where tipo_identificacion='"+tipo_identificacion+"' and numero_documento='"+numero_documento+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProveedor>>BuscarProveedor "+ex);
        }	
        return rs;
    }
	
public java.sql.ResultSet BuscarRegimen(){	
		
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT nombre FROM cont_tipo_regimen ");
        	//System.out.println("select * from cont_terceros where tipo_identificacion='"+tipo_identificacion+"' and numero_documento='"+numero_documento+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoProveedor>>BuscarProveedor "+ex);
        }	
        return rs;
    }

public java.sql.ResultSet BuscarBancosCodigo(String Codigo){	
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery("SELECT * FROM cont_bancos WHERE codigo="+Codigo+"  ");
    	
    }
    catch(Exception ex){
    	System.out.println("Error en MetodoProveedor>>BuscarBancosCodigo "+ex);
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
    	System.out.println("Error en MetodoProveedor>>BuscarBancos "+ex);
    }	
    return rs;
}


}