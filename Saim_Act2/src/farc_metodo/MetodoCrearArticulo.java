package farc_metodo;

import java.sql.PreparedStatement;
import java.sql.Statement;

import adm_logica.Conexion;
import farc_bean.CrearArticulo;

public class MetodoCrearArticulo {

	public void CrearArticulo(String codigoArticulo,String nombre,String concentracion,String observacion,String grupo,String nombreGenerico,String cod_unidadFK,String tipo,String tipoArticulo,String cod_formaFK,String control){
	/**
		 * creamos el articulo.
	 */
		CrearArticulo ca = new CrearArticulo();
		
		ca.setcodigoArticulo(codigoArticulo);
		ca.setnombre(nombre);
		ca.setconcentracion(concentracion);
		ca.setobservacion(observacion);
		ca.setgrupo(grupo);
		ca.setnombreGenerico(nombreGenerico);
		ca.setcod_unidadFK(cod_unidadFK);
		ca.settipo(tipo);
		ca.settipoArticulo(tipoArticulo);
		ca.setcod_formaFK(cod_formaFK);
		ca.setcontrol(control);
		
		
		
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("insert into medicamentos(codigoMedicamento,nombre,concentracion,descripcion,cod_grupoFK,nombre_generico,cod_unidadFK,clasificacion,tipo,cod_formaFK,control)values(?,?,?,?,?,?,?,?,?,?,?)");
			    ps.setString(1,ca.getcodigoArticulo());
			    ps.setString(2,ca.getnombre());
			    ps.setString(3,ca.getconcentracion());
			    ps.setString(4,ca.getobservacion());
			    ps.setString(5,ca.getgrupo());
			    ps.setString(6,ca.getnombreGenerico());
			    ps.setString(7,ca.getcod_unidadFK());
			    ps.setString(8,ca.gettipo());
			    ps.setString(9,ca.gettipoArticulo());
			    ps.setString(10,ca.getcod_formaFK());
			    ps.setString(11,ca.getcontrol());
			  	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoCrearArticulo>>CrearArticulo "+ex);
			}

		}
	
	public void CrearInventarioHNJ(String cmbArticulo,String txtFechaVenci,String txtLote,String txtCantidad,String txtInvima,String cmbBodega,String fecha_sys,String hora_sys){

		try{
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("INSERT INTO farc_inventario (cod_movFk,cod_medFk,vencimiento," +
				    		"lote,cantidad,vunitario,vtotal," +
				    		"cod_ivaFk,cod_bodegaFk,fecha," +
				    		"hora,cod_proveedorFk,invima,cantinicial) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
				    ps.setString(1,"0");
				    ps.setString(2,cmbArticulo);
				    ps.setString(3,txtFechaVenci);
				    ps.setString(4,txtLote);
				    ps.setString(5,txtCantidad);
				    ps.setString(6,"0");
				    ps.setString(7,"0");
				    ps.setString(8,"1");
				    ps.setString(9,cmbBodega);
				    ps.setString(10,fecha_sys);
				    ps.setString(11,hora_sys);
				    ps.setString(12,"0");
				    ps.setString(13,txtInvima);
				    ps.setString(14,txtCantidad);
				  	ps.executeUpdate();
					ps.close();
					con.cerrar();				
				}catch(Exception ex){
		        	System.out.println("ERROR EN MetodoCrearArticulo>>CrearInventarioHNJ "+ex);
				}

			}
	
	public void CrearTarifaArticulo(String manual_tarifario,String CodigoArticulo,String Valor){

		try{
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("INSERT INTO fact_tarifas (manual_tarifario,programa,valor,fecha_ini,fecha_fin) VALUES (?,?,?,?,?)");
				    ps.setString(1,manual_tarifario);
				    ps.setString(2,CodigoArticulo);
				    ps.setString(3,Valor);
				    ps.setString(4,"2014-01-01");
				    ps.setString(5,"2014-12-31");
				  	ps.executeUpdate();
					ps.close();
					con.cerrar();				
				}catch(Exception ex){
		        	System.out.println("ERROR EN MetodoCrearArticulo>>CrearProgramaMedicamento "+ex);
				}

			}
	
	public void CrearProgramaMedicamento(String CodigoArticulo,String CodigoPrograma){

		try{
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("INSERT INTO fact_prog_med (cod_med,cod_prog) VALUES (?,?)");
				    ps.setString(1,CodigoArticulo);
				    ps.setString(2,CodigoPrograma);
				  	ps.executeUpdate();
					ps.close();
					con.cerrar();				
				}catch(Exception ex){
		        	System.out.println("ERROR EN MetodoCrearArticulo>>CrearProgramaMedicamento "+ex);
				}

			}
	
	
	public void CrearLaboratorio(String NombreLaboratorio){

		try{
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("INSERT INTO farc_laboratorio (nombre) VALUES (?)");
				    ps.setString(1,NombreLaboratorio);
				  	ps.executeUpdate();
					ps.close();
					con.cerrar();				
				}catch(Exception ex){
		        	System.out.println("ERROR EN MetodoCrearArticulo>>CrearLaboratorio "+ex);
				}

			}
	
	
	public void CrearProgramaFacturacion(String CodigoATC,String NombreArticulo,String Usuario,String Fecha,String Hora,String txtCodigoCUM){

		try{
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("INSERT INTO fact_programas (manual_base,nivel_complejidad,cod_referencia,cod_cups,descripcion,especialidad,clase_servicio,subcentro_costo,archivo_rip,rn,fecha,hora,cod_usuario,actoqx,medico,idporcentajeqx) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				    ps.setString(1,"3");
				    ps.setString(2,"2");
				    ps.setString(3,CodigoATC);
				    ps.setString(4,txtCodigoCUM);
				    ps.setString(5,NombreArticulo);
				    ps.setString(6,"86");
				    ps.setString(7,"12");
				    ps.setString(8,"4");
				    ps.setString(9,"9");
				    ps.setString(10,"0");
				    ps.setString(11,Fecha);
				    ps.setString(12,Hora);
				    ps.setString(13,Usuario);
				    ps.setString(14,"0");
				    ps.setString(15,"0");
				    ps.setString(16,"0");
				  	ps.executeUpdate();
					ps.close();
					con.cerrar();				
				}catch(Exception ex){
		        	System.out.println("ERROR EN MetodoCrearArticulo>>CrearProgramaFacturacion "+ex);
				}

			}
	
	public void ModificarMedicamentoHNJ(String cart, String codigoArticulo,String nombre,
			String concentracion,String grupo,String cod_unidadFK,String tipo,
			String tipoArticulo,String cod_formaFK,String control){
		
		System.out.println("UPDATE medicamentos SET codigoMedicamento='"+codigoArticulo+"'," +
		    		" nombre='"+nombre+"', concentracion='"+concentracion+"',  cod_grupoFK='"+grupo+"'," +
		    				" cod_unidadFK='"+cod_unidadFK+"', clasificacion='"+tipo+"'," +
		    						" tipo='"+tipoArticulo+"', cod_formaFK='"+cod_formaFK+"'," +
		    								" control='"+control+"' WHERE codigo='"+cart+"' ");
	
		/****Modificamosos el articulo.****/
			try{
				PreparedStatement st = null;
				    Conexion con=new Conexion();                                                      
				  //  ps=con.conn.prepareStatement("insert into farc_proveedor(nit,municipio_fk,telefono,fax,email,contacto,clase_contribuyente,razon_social,direccion,comentario)values(?,?,?,?,?,?,?,?,?,?)");
				    st= con.conn.prepareStatement("UPDATE medicamentos SET codigoMedicamento='"+codigoArticulo+"'," +
				    		" nombre='"+nombre+"', concentracion='"+concentracion+"',  cod_grupoFK='"+grupo+"'," +
				    				" cod_unidadFK='"+cod_unidadFK+"', clasificacion='"+tipo+"'," +
				    						" tipo='"+tipoArticulo+"', cod_formaFK='"+cod_formaFK+"'," +
				    								" control='"+control+"' WHERE codigo='"+cart+"' ");
			    	st.executeUpdate();
			    	st.close();
					con.cerrar();				
				}catch(Exception ex){
		        	System.out.println("ERROR EN MetodoCrearArticulo>>ModificarMedicamentoHNJ "+ex);
				}

			}
	
	
	public void ModificarArticulo(String cart, String codigoArticulo,String nombre,String concentracion,String observacion,String grupo,String nombreGenerico,String cod_unidadFK,String tipo,String tipoArticulo,String cod_formaFK,String control){
		/**
			 * Modificamosos el articulo.
		 */
			try{
				PreparedStatement st = null;
				    Conexion con=new Conexion();                                                      
				  //  ps=con.conn.prepareStatement("insert into farc_proveedor(nit,municipio_fk,telefono,fax,email,contacto,clase_contribuyente,razon_social,direccion,comentario)values(?,?,?,?,?,?,?,?,?,?)");
				    st= con.conn.prepareStatement(" update medicamentos set codigoMedicamento='"+codigoArticulo+"', nombre='"+nombre+"', concentracion='"+concentracion+"', descripcion='"+observacion+"', cod_grupoFK='"+grupo+"', nombre_generico='"+nombreGenerico+"', cod_unidadFK='"+cod_unidadFK+"', clasificacion='"+tipo+"', tipo='"+tipoArticulo+"', cod_formaFK='"+cod_formaFK+"', control='"+control+"' where codigo='"+cart+"' ");
			    	st.executeUpdate();
			    	st.close();
					con.cerrar();				
				}catch(Exception ex){
		        	System.out.println("ERROR EN MetodoCrearArticulo>>ModificarArticulo "+ex);
				}

			}
	
	public java.sql.ResultSet ObtenerCodigoPrograma(String Fecha,String Hora){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT cod_programa FROM fact_programas WHERE fecha='"+Fecha+"' AND hora='"+Hora+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearArticulo>>ObtenerCodigoPrograma "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet BuscarLaboratorio(String NombreLab){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM farc_laboratorio WHERE nombre ='"+NombreLab+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearArticulo>>BuscarLaboratorio "+ex);
        }	
        return rs;
    }

	
	
	
	public java.sql.ResultSet ObtenerCodigoMedicamento(String CodigoATC,String NombreMedicamento){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT codigo FROM medicamentos WHERE codigoMedicamento='"+CodigoATC+"' AND nombre='"+NombreMedicamento+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearArticulo>>ObtenerCodigoMedicamento "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerGrupos(){	
		/**
		 * consulta que obtiene los grupos del modulo de farmacia, 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo,descripcion from farc_grupo");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearArticulo>>ObtenerGrupos "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerGrupos(String cod){	
		/**
		 * consulta que obtiene los grupos del modulo de farmacia, 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo,descripcion from farc_grupo where codigo!='"+cod+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearArticulo>>ObtenerGrupos "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet ObtenerUnidad(){	
		/**
		 * consulta que obtiene las unidades del modulo de farmacia, 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo,sigla from farc_unidades where estado=1");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearArticulo>>ObtenerUnidad "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerUnidad(String cod){	
		/**
		 * consulta que obtiene las unidades del modulo de farmacia, 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo,sigla from farc_unidades where codigo!='"+cod+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearArticulo>>ObtenerUnidad "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet ObtenerUnidadp(String cod){	
		/**
		 * consulta que obtiene las unidades del modulo de farmacia, 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo,sigla from farc_unidades where codigo='"+cod+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearArticulo>>ObtenerUnidadp "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerIva(){	
		/**
		 * consulta que obtiene los diferentes tipos de ivas., 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo,descripcion from farc_iva");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearArticulo>>ObtenerIva "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarMedicamentoActualizar(String CodMedActu){	

        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT  med.codigo,  med.nombre,  med.concentracion,  med.control,  med.cod_formaFK,  ff.descripcion,  med.cod_grupoFK,  fg.descripcion,  med.cod_unidadFK,  fu.sigla,  med.tipo,  med.codigoMedicamento,  fpr.cod_programa,  fpr.cod_cups,  fpr.cod_referencia,med.clasificacion FROM  medicamentos med,  fact_prog_med fpm,  farc_formafarmaceutica ff,  fact_programas fpr,  farc_grupo fg,  farc_unidades fu WHERE med.estado = 1   AND med.codigo = fpm.cod_med   AND fpr.cod_programa = fpm.cod_prog   AND ff.codigo = med.cod_formaFK   AND fg.codigo = med.cod_grupoFK   AND fu.codigo = med.cod_unidadFK   AND med.codigo = '"+CodMedActu+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearArticulo>>BuscarMedicamentoActualizar "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerFormaFarmaceutica(){	
		/**
		 * consulta que obtiene las diferentes formas farmaceuticas 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo,descripcion  from farc_formafarmaceutica");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearArticulo>>ObtenerFormaFarmaceutica "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerFormaFarmaceutica(String cod){	
		/**
		 * consulta que obtiene las diferentes formas farmaceuticas 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo,descripcion  from farc_formafarmaceutica where codigo!='"+cod+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearArticulo>>ObtenerFormaFarmaceutica "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerFormaFarmaceuticap(String cod){	
		/**
		 * consulta que obtiene las diferentes formas farmaceuticas 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo,descripcion  from farc_formafarmaceutica where codigo='"+cod+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearArticulo>>ObtenerFormaFarmaceuticap "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerArticulo(String cod){	
		
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select m.nombre,m.codigoMedicamento,m.concentracion,m.cod_formaFK,fm.descripcion,m.cod_unidadFK,u.sigla,m.tipo,m.nombre_generico,m.clasificacion,m.cod_grupoFK,g.descripcion,m.control,m.descripcion from medicamentos m, farc_formafarmaceutica fm, farc_unidades u, farc_grupo g where m.codigo='"+cod+"' and fm.codigo=m.cod_formaFK and u.codigo=m.cod_unidadFK and g.codigo=m.cod_grupoFK ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearArticulo>>ObtenerFormaFarmaceutica "+ex);
        }	
        return rs;
    }
	
	
	
	
	////////crear articulo en programas////////////////777
	
	public void CrearPrograma(String mabase,String comp,String cref,String cups,String desc,String espe,String serv,String subc,String rips,String rn,String fecha,String hra,String user){
		
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("insert into fact_programas(manual_base,nivel_complejidad,cod_referencia,cod_cups,descripcion,especialidad,clase_servicio,subcentro_costo,archivo_rip,rn,fecha,hora,cod_usuario)values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
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
			    ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoCrearArticulo>>CrearPrograma "+ex);
			}

		}
	
	
	public void ModificarPrograma(String cart, String nombe, String fecha, String hra, String user, String cp){
		/**
			 * Modificamosos el articulo.
		 */
			try{
				PreparedStatement st = null;
				    Conexion con=new Conexion();                                                      
				    st= con.conn.prepareStatement(" update fact_programas set cod_referencia='"+cart+"', descripcion='"+nombe+"', fecha='"+fecha+"', hora='"+hra+"', cod_usuario='"+user+"' where cod_programa='"+cp+"' ");
			    	st.executeUpdate();
			    	st.close();
					con.cerrar();				
				}catch(Exception ex){
		        	System.out.println("ERROR EN MetodoCrearArticulo>>ModificarArticulo "+ex);
				}

			}
	
	
	public java.sql.ResultSet ObtenerProgramaPM(String med){	
		/**
		 * consulta que obtiene las diferentes formas farmaceuticas 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("  select cod_prog from fact_prog_med where cod_med='"+med+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearArticulo>>ObtenerFormaFarmaceutica "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet ObtenerPrograma(String fecha, String hra, String user){	
		/**
		 * consulta que obtiene las diferentes formas farmaceuticas 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("  select cod_programa  from fact_programas where fecha='"+fecha+"' and hora='"+hra+"' and cod_usuario='"+user+"'  ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearArticulo>>ObtenerFormaFarmaceutica "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerMedicamento(String codigoArticulo, String nombre, String concentracion, String observacion, String grupo, String nombreGenerico, String cod_unidadFK, String tipo, String tipoArticulo, String cod_formaFK, String control){	
		/**
		 * consulta que obtiene las diferentes formas farmaceuticas 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("  select codigo from medicamentos	where codigoMedicamento='"+codigoArticulo+"' and nombre='"+nombre+"'  and concentracion='"+concentracion+"'  and descripcion='"+observacion+"'  and cod_grupoFK='"+grupo+"'  and nombre_generico='"+nombreGenerico+"'   and cod_unidadFK='"+cod_unidadFK+"'   and clasificacion='"+tipo+"'   and   tipo='"+tipoArticulo+"'   and   cod_formaFK='"+cod_formaFK+"'  and  control='"+control+"'    ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearArticulo>>ObtenerFormaFarmaceutica "+ex);
        }	
        return rs;
    }
	
	
	public void CrearProgMed(String cp,String cm){
		
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement(" insert into fact_prog_med(cod_prog,cod_med)values(?,?) ");
			    //ps.setString(1,ci.getbodega());
			    ps.setString(1,cp);
			    ps.setString(2,cm);
			  
			    ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoCrearArticulo>>CrearProgMed "+ex);
			}

		}


	////PARA PRODUCTOS/////
	public void CrearProducto(String codigoArticulo,String nombe,String grupo,String user,String  fecha,String  hra) {
	
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();													
			    ps=con.conn.prepareStatement("INSERT INTO ord_producto (cod_referencia,nombre,cod_usuariofk,tipo_productofk,fecha_insercion,hora_insercion)values(?,?,?,?,?,?) ");
			    ps.setString(1,codigoArticulo);
			    ps.setString(2,nombe);
			    ps.setString(3,user);
			    ps.setString(4,grupo);
			    ps.setString(5,fecha);
			    ps.setString(6,hra);
			    ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoCrearArticulo>>CrearProducto "+ex);
			}			
		}
	
	
	public java.sql.ResultSet ObtenerProducto(String codigoArticulo,String nombe,String grupo,String user,String fecha,String hra){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT codigo from ord_producto where cod_referencia='"+codigoArticulo+"' and nombre='"+nombe+"' and estado='0' and cod_usuariofk='"+user+"' and tipo_productofk='"+grupo+"' and fecha_insercion='"+fecha+"' and hora_insercion='"+hra+"' ");
	        	
	        	
	        }catch(Exception ex){
	            	System.out.println("Error en  MetodoCrearArticulo>>ObtenerProducto "+ex);
	            }	
	            return rs;
	}
	
	
	public void CrearOrdProgMed(String cproducto,String  cp,String cm) {
		
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();													
			    ps=con.conn.prepareStatement("INSERT INTO ord_prod_medprog (cod_productofk,cod_medfk,cod_progfk)values(?,?,?) ");
			    ps.setString(1,cproducto);
			    ps.setString(2,cp);
			    ps.setString(3,cm);
			    ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoCrearArticulo>>CrearProducto "+ex);
			}			
		}
	
	
public void CrearAsignacionGrupo(String cproducto,String grupo,String user,String fecha,String hora ) {
		
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();													
			    ps=con.conn.prepareStatement("INSERT INTO ord_produc_grupo (cod_productofk,cod_grupofk,cod_usuariofk, fecha, hora )values(?,?,?,?,?) ");
			    ps.setString(1,cproducto);
			    ps.setString(2,grupo);
			    ps.setString(3,user);
			    ps.setString(4,fecha);
			    ps.setString(5,hora);
			    ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoCrearArticulo>>CrearAsignacionGrupo "+ex);
			}			
		}
	
	
}
