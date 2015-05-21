
package seguridad_logica;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;

import adm_logica.Conexion;





import seguridad_bean.Usuario;

public class Metodo_Usuario {
	public void ActualizarDatosPersonalesUsuario(String ape,String dire, String mail,String nom, String ocu,String pro, String tel,String cedU,String est,String NumeroDocumento,String tipoDocumento,String CodDatPer){

     PreparedStatement ps = null;
     try{
    	 
    	 Conexion con=new Conexion();
    	 ps= con.conn.prepareStatement("UPDATE seg_datos_personales SET nombre=?,apellido=?,direccion=?,telefono=?,email=?,profesion=?,ocupacion=?,cedula=?,estado=?,numeroDocumento=?,tipoDocumento=? WHERE dat_codigo=?");
    	 ps.setString(1, nom);
    	 ps.setString(2, ape);
    	 ps.setString(3, dire);
    	 ps.setString(4, tel);
    	 ps.setString(5, mail);
    	 ps.setString(6, pro);
    	 ps.setString(7, ocu);
    	 ps.setString(8, cedU);
    	 ps.setString(9, est);
    	 ps.setString(10, NumeroDocumento);
    	 ps.setString(11, tipoDocumento);
    	 ps.setString(12, CodDatPer);
    	 
    	 ps.executeUpdate();
    	 ps.close();
    	 con.cerrar();
    	 
     }
     catch(Exception ex){
    	 ex.getMessage();
     }	
 }
	
	public java.sql.ResultSet ListarUsuarios(){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT CONCAT(sdp.nombre,' ',sdp.apellido) AS Nombre_Usuario,sdp.profesion,sdp.ocupacion,sdp.tipoDocumento,sdp.numeroDocumento,su.usuario FROM seg_datos_personales sdp,seg_usuario su WHERE sdp.dat_codigo=su.dat_codigo_fk ORDER BY Nombre_Usuario ");
        }
        catch(Exception ex){
        	System.out.println(ex);	
        }
        return rs;
    }

	public java.sql.ResultSet numerodocumento(String codusuario){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT numeroDocumento FROM seg_datos_personales WHERE dat_codigo = '"+codusuario+"'");
        }
        catch(Exception ex){
        	System.out.println(ex);	
        }
        return rs;
    }
	
	 public void InsertarPermisosUsuario(String CodigoOpcionDispo,String CodUsuario ){		  
			 try{
				 	Conexion con=new Conexion();			 
					PreparedStatement ps = null;				
				    ps=con.conn.prepareStatement("INSERT INTO seg_opciones_autorizadas(usu_codigo_fk,opd_codigo_fk) VALUE (?,?)");				
					ps.setString(1,CodUsuario );
					ps.setString(2,CodigoOpcionDispo );	
					ps.execute();
					ps.close();
					con.cerrar();
				}catch(Exception ex){
					System.out.println(ex);				
				}				
			}
	 public java.sql.ResultSet BuscarContraUsu(String CodUsuario){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT contrasena FROM seg_usuario WHERE usu_codigo="+CodUsuario+" ");
	        }
	        catch(Exception ex){
	        	System.out.println(ex);	
	        }
	        return rs;
	    }
	
public void IngresarLogUsuario(String codUsu,String fecha_registro,String hora_registro, String accion ){	
	/**
	 * en este metodo se guardan los movimientos mas criticos que hace el usuario dentro del sistema 
	 */
	 try{
		 	Conexion con=new Conexion();			 
			PreparedStatement ps = null;				
		    ps=con.conn.prepareStatement("INSERT INTO seg_log_usuario(codUsu,fecha_registro,hora_registro,accion)VALUES(?,?,?,?)");				
		    ps.setString(1,codUsu);
			ps.setString(2,fecha_registro);	
			ps.setString(3,hora_registro);
			ps.setString(4,accion);	
			ps.execute();
			ps.close();
			con.cerrar();
		}catch(Exception ex){
			System.out.println("Error en Metodo_Usuario>>IngresarLogUsuario "+ex);
			//System.out.println(ex);				
		}				
	}
	 
	 
	 public java.sql.ResultSet CambiarEstadoOn(String usuario,String contrasena){
			/**
			 * en este metodo corrobora el usuario y al contraseña sean la misma y cambia el estado del usuario a  1 
			 */
				
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        //String y="0";
	        try{
	        	Conexion con=new Conexion();
	        	
	        	st = con.conn.createStatement();
	        	st.executeUpdate("UPDATE seg_usuario SET estado=1 WHERE seg_usuario.usuario='"+usuario+"' AND seg_usuario.contrasena='"+contrasena+"'");
	        }
	        catch(Exception ex){ 
	        }
	        return rs;
	    }
		
		public java.sql.ResultSet CambiarEstadoOff(String codusu){
			/**
			 * en este metodo corrobora el usuario y al contraseña sean la misma y cambia el estado del usuario a  1 
			 */
				
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        //String y="0";
	        try{
	        	Conexion con=new Conexion();
	        	
	        	st = con.conn.createStatement();
	        	st.executeUpdate("UPDATE seg_usuario SET estado=0 WHERE seg_usuario.usu_codigo='"+codusu+"'");
	        }
	        catch(Exception ex){ 
	        }
	        return rs;
	    }
	
	public void OmitirAsigacionPermisos(String CodigoAsignacion){
		PreparedStatement st = null;
		try{
			Conexion con=new Conexion();
			st= con.conn.prepareStatement(" DELETE FROM seg_opciones_autorizadas WHERE opa_codigo="+CodigoAsignacion+" ");
			st.executeUpdate();
			st.close();
			con.cerrar();
		}
		catch(Exception ex){
			ex.getMessage();
		}	
	}


	public java.sql.ResultSet ProgramacionesDisponibles(String CodUsuario){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT p.codigo, p.descripcion FROM cig_tipoprogramacion p WHERE p.codigo NOT IN (SELECT p.codigo FROM  cig_tipoprogramacion p, seg_programacion_autorizadas sp WHERE sp.cod_usuariofk="+CodUsuario+" AND sp.cod_programacionfk=p.codigo)  ");
        }
        catch(Exception ex){
        	System.out.println(ex);	
        }
        return rs;
    }
	
	
	public java.sql.ResultSet ProgramacionesAutorizadas(String CodUsuario){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT sp.cod_programacionfk, p.descripcion FROM seg_programacion_autorizadas sp, cig_tipoprogramacion p WHERE sp.cod_usuariofk="+CodUsuario+" AND sp.cod_programacionfk=p.codigo  ");
        }
        catch(Exception ex){
        	System.out.println(ex);	
        }
        return rs;
    }
	
	
	 public void InsertarProgramacion(String CodigoOpcionDispo,String CodUsuario ){		  
		 try{
			 	Conexion con=new Conexion();			 
				PreparedStatement ps = null;				
			    ps=con.conn.prepareStatement("INSERT INTO seg_programacion_autorizadas(cod_usuariofk,cod_programacionfk) VALUE (?,?)");				
			    ps.setString(1,CodUsuario );
				ps.setString(2,CodigoOpcionDispo );	
				ps.execute();
				ps.close();
				con.cerrar();
			}catch(Exception ex){
				System.out.println(ex);				
			}				
		}
	
	 
	 public void OmitirPermisoPro(String CodigoAsignacion,String CodUsuario){
			PreparedStatement st = null;
			try{
				Conexion con=new Conexion();
				st= con.conn.prepareStatement("DELETE FROM seg_programacion_autorizadas WHERE cod_programacionfk="+CodigoAsignacion+" and cod_usuariofk="+CodUsuario+" ");
				st.executeUpdate();
				st.close();
				con.cerrar();
			}
			catch(Exception ex){
				System.out.print("Error en MetodoOpcionesAutorizadas>>OmitirPermisoPro "+ex);
			}	
		}
	 
	
	public java.sql.ResultSet TodosMenus(){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery(" SELECT men_codigo,nombre FROM seg_menu ");
	        }
	        catch(Exception ex){
	        	System.out.println(ex);	
	        }
	        return rs;
	    }
	
	public java.sql.ResultSet OpcionesMenu(String CodigoMenu){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT som.opm_codigo,som.nombre FROM seg_menu sm,seg_opciones_menu som WHERE sm.men_codigo=som.men_codigo_fk AND sm.men_codigo="+CodigoMenu+" ");
        }
        catch(Exception ex){
        	System.out.println(ex);	
        }
        return rs;
    }
	
	public java.sql.ResultSet OpcionesDisponibleOpcMenu(String CodigoOpcMenu,String CodUsuario){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT DISTINCT sod.opd_codigo AS CodOpcion,sod.nombre FROM seg_opciones_disponibles sod,seg_opciones_menu som WHERE sod.opd_codigo NOT IN (SELECT soa.opd_codigo_fk FROM seg_opciones_autorizadas soa,seg_opciones_disponibles sod,seg_opciones_menu som WHERE sod.opm_codigo_fk=som.opm_codigo AND sod.opm_codigo_fk="+CodigoOpcMenu+" AND soa.opd_codigo_fk=sod.opd_codigo AND soa.usu_codigo_fk="+CodUsuario+") AND som.opm_codigo=sod.opm_codigo_fk AND som.opm_codigo="+CodigoOpcMenu+"  ");
        }
        catch(Exception ex){
        	System.out.println(ex);	
        }
        return rs;
    }
	
	
	public java.sql.ResultSet OpcionesAutorizadasOpcMenu(String CodigoOpcMenu,String CodUsuario){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT soa.opa_codigo,sod.nombre FROM seg_opciones_autorizadas soa,seg_opciones_disponibles sod,seg_opciones_menu som WHERE sod.opm_codigo_fk=som.opm_codigo AND sod.opm_codigo_fk="+CodigoOpcMenu+" AND soa.opd_codigo_fk=sod.opd_codigo AND soa.usu_codigo_fk="+CodUsuario+"  ");
        }
        catch(Exception ex){
        	System.out.println(ex);	
        }
        return rs;
    }
	
	public java.sql.ResultSet BodegasDisponibles(String CodUsuario){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT b.codigo, b.nombre FROM farc_bodegas b WHERE b.codigo NOT IN (SELECT b.codigo FROM seg_bodegas_autorizadas s, farc_bodegas b WHERE s.cod_usuariofk="+CodUsuario+" AND s.cod_bodegafk=b.codigo)  ");
        }
        catch(Exception ex){
        	System.out.println(ex);	
        }
        return rs;
    }
	
	
	public java.sql.ResultSet BodegasAutorizadas(String CodUsuario){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT cod_bodegafk, b.nombre FROM seg_bodegas_autorizadas s, farc_bodegas b WHERE s.cod_usuariofk="+CodUsuario+" AND s.cod_bodegafk=b.codigo  ");
        }
        catch(Exception ex){
        	System.out.println(ex);	
        }
        return rs;
    }
	
	
	 public void InsertarBodega(String CodigoOpcionDispo,String CodUsuario ){		  
		 try{
			 	Conexion con=new Conexion();			 
				PreparedStatement ps = null;				
			    ps=con.conn.prepareStatement("INSERT INTO seg_bodegas_autorizadas(cod_usuariofk,cod_bodegafk) VALUE (?,?)");				
			    System.out.println("INSERT INTO seg_bodegas_autorizadas(cod_usuariofk,cod_bodegafk) VALUE ("+CodUsuario+","+CodigoOpcionDispo+")");
			    ps.setString(1,CodUsuario );
				ps.setString(2,CodigoOpcionDispo );	
				ps.execute();
				ps.close();
				con.cerrar();
			}catch(Exception ex){
				System.out.println(ex);				
			}				
		}
	
	 
		public void OmitirPermisoBo(String CodigoAsignacion,String CodUsuario){
			PreparedStatement st = null;
			try{
				Conexion con=new Conexion();
				st= con.conn.prepareStatement("DELETE FROM seg_bodegas_autorizadas WHERE cod_bodegafk="+CodigoAsignacion+" and cod_usuariofk="+CodUsuario+" ");
				System.out.println("DELETE FROM seg_bodegas_autorizadas WHERE cod_bodegafk="+CodigoAsignacion+" and cod_usuariofk="+CodUsuario+" ");
				st.executeUpdate();
				st.close();
				con.cerrar();
			}
			catch(Exception ex){
				System.out.print("Error en MetodoOpcionesAutorizadas>>OmitirPermisoHC "+ex);
			}	
		}
	 
	
	public java.sql.ResultSet BuscarDatosUsuario(String CodDatosPersonales){
		 /* se verifica si el usuario ya existe*/
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT usu_codigo,usuario,contrasena FROM seg_usuario WHERE dat_codigo_fk="+CodDatosPersonales+" ");
	        }
	        catch(Exception ex){
	        	System.out.println(ex);	
	        }
	        return rs;
	    }
	
	public java.sql.ResultSet BuscarDatosPersonalesUsuario(String NumeroDocumento,String tipoDocumento){
		 /*
		  * se verifica si el usuario ya existe*/
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT dat_codigo,nombre,apellido,direccion,telefono,email,profesion,ocupacion,cedula,estado,numeroDocumento,tipoDocumento FROM seg_datos_personales WHERE tipoDocumento='"+tipoDocumento+"' AND numeroDocumento='"+NumeroDocumento+"'");
	        }
	        catch(Exception ex){
	        	System.out.println(ex);	
	        }
	        return rs;
	    }
	
	public java.sql.ResultSet BuscarOcupacion(String CodProfesion){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT aes.codigo,aes.nombre_especialidad FROM seg_cargo_profesion scp,agm_especialidad aes WHERE scp.codCargo_fk=aes.codigo AND scp.codProfesion_fk='"+CodProfesion+"'");
        }
        catch(Exception ex){
        	System.out.println("Error Metodo_Usuario>>BuscarOcupacion "+ex);	
        }
        return rs;
    }
	
	public java.sql.ResultSet BuscarTiempoMedico(String TipoDocumento,String NumeroDocumento,String Estado){
		System.out.println("SELECT am.* FROM agm_medico am WHERE am.tipoDocumento='"+TipoDocumento+"' AND am.numeroDocumento='"+NumeroDocumento+"' "+Estado );
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT am.* FROM agm_medico am WHERE am.tipoDocumento='"+TipoDocumento+"' AND am.numeroDocumento='"+NumeroDocumento+"' "+Estado );
        }
        catch(Exception ex){
        	System.out.println("Error Metodo_Usuario>>BuscarTiempoMedico "+ex);	
        }
        return rs;
    }
	
	public java.sql.ResultSet BuscarProfesion(){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select * from seg_profesion");
	        }
	        catch(Exception ex){
	        	System.out.println("Error Metodo_Usuario>>BuscarProfesion "+ex);	
	        }
	        return rs;
	    }
	
	public java.sql.ResultSet BuscarEspecialidades(String NomEspecialidad){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM agm_especialidad WHERE nombre_especialidad='"+NomEspecialidad+"' ORDER BY nombre_especialidad");
        }
        catch(Exception ex){
        	System.out.println("Error Metodo_Usuario>>BuscarEspecialidades "+ex);	
        }
        return rs;
    }
	
	public java.sql.ResultSet BuscarEspecialidades(){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM agm_especialidad ORDER BY nombre_especialidad");
        }
        catch(Exception ex){
        	System.out.println("Error Metodo_Usuario>>BuscarEspecialidades "+ex);	
        }
        return rs;
    }
	
	public java.sql.ResultSet BuscarNombreEspecialidades(String NombreEspecialidad){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM agm_especialidad where nombre_especialidad WHERE nombre_especialidad='"+NombreEspecialidad+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error Metodo_Usuario>>BuscarNombreEspecialidades "+ex);	
        }
        return rs;
    }
	
	public void InsertarFirmaUsuario(InputStream inps, long size,String codUsuario) throws Exception {
		/**se inserta la firma del usuario*/
		PreparedStatement ps = null;
		Conexion con=new Conexion();
		try {						
			ps=con.conn.prepareStatement("update seg_datos_personales set firma=? where dat_codigo='"+codUsuario+"'");
			ps.setBinaryStream(1, inps, (int)size);
			ps.executeUpdate();  
			ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
				}
			}
	
	/*
	 * se buscan los usuarios que estan en la base de datos.*/
	  public ResultSet listarParaAutocompletarControl(String cod) throws Exception {
		  java.sql.ResultSet r=null;
		  Statement st = null;
		  Conexion con=new Conexion();
		  st = con.conn.createStatement();
		  r=st.executeQuery("SELECT cedula,nombre,apellido,numeroDocumento FROM seg_datos_personales WHERE cedula LIKE '"+cod+"%'and estado=0");
		  
		  return r;
	    }
	
	  
	  public java.sql.ResultSet BuscarUsuario(String NumeroDocumento){
			/**
			 * se busca el usuario para cambiarle la contraseña
			 */
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();	        	
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select sgu.usu_codigo,sgu.usuario,sgu.contrasena,sdt.nombre,sdt.apellido,sdt.cedula from seg_datos_personales sdt,seg_usuario sgu where sdt.dat_codigo=sgu.dat_codigo_fk and sdt.numeroDocumento='"+NumeroDocumento+"'");
	        }
	        catch(Exception ex){ 
	        }
	        return rs;
	    }

	public java.sql.ResultSet obtenerUsuario(String usuario,String contra){
		//System.out.print("select su.usu_codigo,sdt.profesion from seg_usuario su,seg_datos_personales sdt where su.usuario='"+usuario+"' and su.contrasena='"+contra+"' and sdt.estado=0 and sdt.dat_codigo=su.dat_codigo_fk ");
		/**
		 * en este metodo se consulta si el usuario existe o no
		 * a la hora de hacer el login.
		 * se toma como dato de entrada el usuario y contraseña.
		 * 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        //String y="0";
        try{
        	Conexion con=new Conexion();
        	
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select su.usu_codigo,sdt.profesion,sdt.tipoDocumento,sdt.numeroDocumento from seg_usuario su,seg_datos_personales sdt where su.usuario='"+usuario+"' and su.contrasena='"+contra+"' and sdt.estado=0 and sdt.dat_codigo=su.dat_codigo_fk ");
        	//System.out.println("select su.usu_codigo,sdt.profesion,sdt.tipoDocumento,sdt.numeroDocumento from seg_usuario su,seg_datos_personales sdt where su.usuario='"+usuario+"' and su.contrasena='"+contra+"' and sdt.estado=0 and sdt.dat_codigo=su.dat_codigo_fk ");
        }
        catch(Exception ex){ 
        }
        return rs;
    }
	
	
	public java.sql.ResultSet ObtenerPantallaPrincipalCex(String codu){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT soa.* FROM seg_opciones_disponibles sod,seg_opciones_autorizadas soa WHERE sod.opd_codigo=soa.opd_codigo_fk AND soa.usu_codigo_fk='"+codu+"' AND sod.url='agm_ListaMedico.jsp' ");
        }
        catch(Exception ex){ 
        }
        return rs;
    }
	
	public java.sql.ResultSet ObtenerPantallaPrincipalHc(String codu){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();        	
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM hic_usuariopermisoshc WHERE codigoUsu_fk='"+codu+"' ");
        }
        catch(Exception ex){ 
        }
        return rs;
    }
	
	public java.sql.ResultSet MedicoEnCex(String tipoDocumento,String numeroDocumento){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT am.* FROM agm_medico am WHERE am.tipoDocumento='"+tipoDocumento+"' AND am.numeroDocumento='"+numeroDocumento+"'");
        	//System.out.println("select su.usu_codigo,sdt.profesion,sdt.tipoDocumento,sdt.numeroDocumento from seg_usuario su,seg_datos_personales sdt where su.usuario='"+usuario+"' and su.contrasena='"+contra+"' and sdt.estado=0 and sdt.dat_codigo=su.dat_codigo_fk ");
        }
        catch(Exception ex){ 
        }
        return rs;
    }
	
	
	 public void insertarusuario(String ape,String dire, String mail,String nom, String ocu,String pro, String tel,String cedU,String est,String NumeroDocumento,String tipoDocumento){
	   /**
	    * se inserta los datos del usuario*/
		 Usuario us= new Usuario ();
		 us.setApellido(ape);
		 us.setDireccion(dire);
		 us.setEmail(mail);
		 us.setNombre(nom);
		 us.setOcupacion(ocu);
		 us.setProfesion(pro);
		 us.setTelefono(tel);		
		 us.setCedula(cedU);	
		 us.setEstado(est);
		 us.setNumeroDocumento(NumeroDocumento);
		 us.setTipoDocumento(tipoDocumento);
		 try{
			 	Conexion con=new Conexion();			 
				PreparedStatement ps = null;				
			    ps=con.conn.prepareStatement("insert into seg_datos_personales(nombre,apellido,direccion,telefono,email,profesion,ocupacion,cedula,estado,numeroDocumento,tipoDocumento) values(?,?,?,?,?,?,?,?,?,?,?)");				
				ps.setString(1, us.getNombre());
				ps.setString(2, us.getApellido());	
				ps.setString(3, us.getDireccion());
				ps.setString(4, us.getTelefono());
				ps.setString(5, us.getEmail());
				ps.setString(6, us.getProfesion());
				ps.setString(7, us.getOcupacion());	
				ps.setString(8, us.getCedula());
				ps.setString(9, us.getEstado());
				ps.setString(10, us.getNumeroDocumento());
				ps.setString(11, us.getTipoDocumento());
			 	ps.execute();
				ps.close();

				con.cerrar();
			}catch(Exception ex){
				System.out.println(ex);				
			}				
		}
	 
	 
	 
	 public void insertarLogin(String usu,String contra,String codus){
		 /*
		  * se inserta el nombre de usuario y contraseña delusuario
		  */
		 	Usuario us= new Usuario ();
			us.setUsuario(usu);
			us.setContrasena(contra);
			us.setCodigo(codus);
			try{
			Conexion con=new Conexion(); 
			PreparedStatement ps = null;
			ps=con.conn.prepareStatement("insert into seg_usuario(usuario,contrasena,dat_codigo_fk) values(?,?,?)");
			ps.setString(1, us.getUsuario());
			ps.setString(2, us.getContrasena());
			ps.setString(3, us.getCodigo());
			ps.execute();
			ps.close();	
			con.cerrar();			
			}
			catch(Exception ex){
				System.out.println(ex);				
			}	
	 }
	 
	 public java.sql.ResultSet obtenerCodigousuario(String codDatPer){
		 /*
		  * se obtienen el codigo del usuario*/
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT usu_codigo FROM seg_usuario WHERE dat_codigo_fk="+codDatPer+"");
	        }
	        catch(Exception ex){
	        	System.out.println(ex);	
	        }
	        return rs;
	    }
	 
	 public java.sql.ResultSet obtenerCodigoDatosPersonales(String cedU){
		 /*
		  * se obtienen el codigo del usuario*/
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select dat_codigo from seg_datos_personales where cedula='"+cedU+"'");
	        }
	        catch(Exception ex){
	        	System.out.println(ex);	
	        }
	        return rs;
	    }
	 
	 public java.sql.ResultSet VerificarExistensiaNombreUsuario(String usu){
		 /*
		  * se verifica si el nombre del usuario ya existe*/
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select usu_codigo from seg_usuario where usuario='"+usu+"'");
	        }
	        catch(Exception ex){
	        	System.out.println(ex);	
	        }
	        return rs;
	    }
	 
	 public java.sql.ResultSet VerificarExistensia(String cedU){
		 /*
		  * se verifica si el usuario ya existe*/
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select cedula from seg_datos_personales where numeroDocumento='"+cedU+"'");
	        }
	        catch(Exception ex){
	        	System.out.println(ex);	
	        }
	        return rs;
	    } 
	 
	 public void eliminar_permisos(String codigo,String codusu){
			/*
			 * se elimina los permisos de los usuarios*/
			       PreparedStatement st = null;
			        try{
			        	Conexion con=new Conexion();
			        	st= con.conn.prepareStatement("delete FROM seg_opciones_autorizadas WHERE seg_opciones_autorizadas.opd_codigo_fk ="+codigo+" and seg_opciones_autorizadas.usu_codigo_fk="+codusu+"");
			        	st.executeUpdate();
			        	st.close();
			        	con.cerrar();
			        	
			        }
			        catch(Exception ex){
			        	ex.getMessage();
			        
			        }	
			       
			    }
	 
	 public void ActualizarContraUsuario(String CodUsuario,String NuevaContra){
			
			/**
			 * en este metodo se actualizan la contraseña del usuario
			 * */	
	     PreparedStatement st = null;
	     try{
	    	 Conexion con=new Conexion();
	    	 st= con.conn.prepareStatement("update seg_usuario set contrasena='"+NuevaContra+"' where usu_codigo='"+CodUsuario+"'");
	    	 st.executeUpdate();
	    	 st.close();
	    	 con.cerrar();
	     }
	     catch(Exception ex){
	    	 ex.getMessage();
			        
	     }	
	 }
}