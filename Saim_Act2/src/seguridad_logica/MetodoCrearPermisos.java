package seguridad_logica;

import java.sql.PreparedStatement;
import java.sql.Statement;

import seguridad_bean.CrearPermisos;

import adm_logica.Conexion;

public class MetodoCrearPermisos {
	
	
	
	public void CrearOpcionesDisponibles(String NombreOpcionDisponible,String UrlOpcionesMenu,String CodigoOpMenuFk){
		/**
		 * creamos la opciones disponibles.
		 */
		CrearPermisos cp = new CrearPermisos();
		cp.setNombreOpcionDisponible(NombreOpcionDisponible);
		cp.setUrlOpcionesMenu(UrlOpcionesMenu);
		cp.setCodigoOpMenuFk(CodigoOpMenuFk);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("insert into seg_opciones_disponibles(nombre,opm_codigo_fk,url)values(?,?,?)");
			    ps.setString(1,cp.getNombreOpcionDisponible());
			    ps.setString(3,cp.getUrlOpcionesMenu());
			    ps.setString(2,cp.getCodigoOpMenuFk());
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoCrearPermisos>>CrearOpcionesDisponibles "+ex);
			}

		}
	
	public void CrearOpcionesMenu(String NombreOpcionesMenu,String CodigoMenuFk){
		/**
		 * creamos la opcion de menu.
		 */
		CrearPermisos cp = new CrearPermisos();
		cp.setNombreOpcionesMenu(NombreOpcionesMenu);
		cp.setCodigoMenuFk(CodigoMenuFk);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("insert into seg_opciones_menu(nombre,men_codigo_fk)values(?,?)");
			    ps.setString(1,cp.getNombreOpcionesMenu());
			    ps.setString(2,cp.getCodigoMenuFk());
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoCrearPermisos>>CrearOpcionesMenu "+ex);
			}

		}
	
	public void CrearMenu(String NombreMenu,String UrlMenu){
		/**
		 * creamos el menu con su url.
		 */
		CrearPermisos cp = new CrearPermisos();
		cp.setNombreMenu(NombreMenu);
		cp.setUrlMenu(UrlMenu);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("insert into seg_menu(nombre,descripcion)values(?,?)");
			    ps.setString(1,cp.getNombreMenu());
			    ps.setString(2,cp.getUrlMenu());
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoCrearPermisos>>CrearMenu "+ex);
			}

		}
	

	public java.sql.ResultSet BuscarNombreMenuRepetido(String NombreMenu){	
		/**
		 * buscar si el nombre del menu nuevo que se va ha ingresar ya existe
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select nombre,descripcion from seg_menu where nombre='"+NombreMenu+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearPermisos>>BuscarNombreMenuRepetido "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarNombreOpcionMenuRepetido(String NombreOpcionesMenu,String CodigoMenuFk){	
		/**
		 * buscar si el nombre de la opcion de menu nuevo que se va ha ingresar ya existe
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select opm_codigo,nombre from seg_opciones_menu where nombre='"+NombreOpcionesMenu+"' and men_codigo_fk="+CodigoMenuFk+"");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearPermisos>>BuscarNombreOpcionMenuRepetido "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarNombreOpcionDisponiblesRepetido(String NombreOpcionDisponible,String CodigoOpMenuFk){	
		/**
		 * buscar si el nombre de la opcion de disponible  nueva que se va ha ingresar ya existe
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select opd_codigo,nombre,opm_codigo_fk,url from seg_opciones_disponibles where nombre='"+NombreOpcionDisponible+"' and opm_codigo_fk="+CodigoOpMenuFk+"");
        	//System.out.println("select opd_codigo,nombre,opm_codigo_fk,url from seg_opciones_disponibles where nombre='"+NombreOpcionDisponible+"' and opm_codigo_fk="+CodigoOpMenuFk+"");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearPermisos>>BuscarNombreOpcionDisponiblesRepetido "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarMenus(){	
		/**
		 * buscar los menus existente
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select nombre,descripcion,men_codigo from seg_menu");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearPermisos>>BuscarMenus "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarOpcionesMenus(String CodigoMenuFk){	
		/**
		 * buscar las opciones de menus existente
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select distinct som.nombre,sm.nombre,som.opm_codigo from seg_opciones_menu som,seg_menu sm where sm.men_codigo=som.men_codigo_fk and sm.men_codigo="+CodigoMenuFk+"");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearPermisos>>BuscarOpcionesMenus "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarOpcionesDisponibles(String CodigoOpMenuFk){	
		/**
		 * buscar las opciones disponobles existente
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select distinct sod.nombre,sod.url,sod.opd_codigo,som.nombre from seg_opciones_disponibles sod,seg_opciones_menu som where som.opm_codigo=sod.opm_codigo_fk and som.opm_codigo="+CodigoOpMenuFk+"");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearPermisos>>BuscarOpcionesDisponibles "+ex);
        }	
        return rs;
    }
}
