package pat_metodo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import pat_bean.CrearFormatoSimple;
import pat_bean.CrearPaciente;

import adm_logica.Conexion;

public class MetodoCrearPaciente {

	public java.sql.ResultSet ObtenerFormatos(){
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT distinct hf.codigo,hf.nombre_formato FROM pat_formato hf ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearPaciente>>ObtenerFormatos "+ex);
        }	
        return rs;
    }
	
	
	
	
	public void CrearBiopsiaRenal(String cod_dat_comple_fk,String DesMacro,String DescMicro1,
			String DescMicro2,String ObserLleno,
			String RE1,String RE2,String RE3,String RE4,String RE5,String RE6,String RE7,String RE8,String RE9,String RE10,
			String IT1,String IT2,String IT3,String IT4,String IT5,String IT6,String IT7,String IT8,String IT9,String IT10,
			String PTR1,String PTR2,String PTR3,String PTR4,String PTR5,String PTR6,String PTR7,String PTR8,String PTR9,String PTR10,
			String Diagnostico){
		
			try{
				
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("INSERT INTO pat_biopsiarenal(cod_dat_comple_fk,DesMacro,DescMicro1,DescMicro2,ObserLleno, " +
				    		" RE1,RE2,RE3,RE4,RE5,RE6,RE7,RE8,RE9,RE10, " +
				    		" IT1,IT2,IT3,IT4,IT5,IT6,IT7,IT8,IT9,IT10, " +
				    		" PTR1,PTR2,PTR3,PTR4,PTR5,PTR6,PTR7,PTR8,PTR9,PTR10, " +
				    		" Diagnostico) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				  
				    ps.setString(1,cod_dat_comple_fk );
				    ps.setString(2,DesMacro );
				    ps.setString(3,DescMicro1 );
				    ps.setString(4,DescMicro2 );
				    ps.setString(5,ObserLleno );
				    ps.setString(6,RE1 );
				    ps.setString(7,RE2 );
				    ps.setString(8,RE3 );
				    ps.setString(9,RE4 );
				    ps.setString(10,RE5 );
				    ps.setString(11,RE6 );
				    ps.setString(12,RE7 );
				    ps.setString(13,RE8 );
				    ps.setString(14,RE9 );
				    ps.setString(15,RE10 );
				    ps.setString(16,IT1 );
				    ps.setString(17,IT2 );
				    ps.setString(18,IT3 );
				    ps.setString(19,IT4 );
				    ps.setString(20,IT5 );
				    ps.setString(21,IT6 );
				    ps.setString(22,IT7 );
				    ps.setString(23,IT8 );
				    ps.setString(24,IT9 );
				    ps.setString(25,IT10 );
				    ps.setString(26,PTR1 );
				    ps.setString(27,PTR2 );
				    ps.setString(28,PTR3 );
				    ps.setString(29,PTR4 );
				    ps.setString(30,PTR5 );
				    ps.setString(31,PTR6 );
				    ps.setString(32,PTR7 );
				    ps.setString(33,PTR8 );
				    ps.setString(34,PTR9 );
				    ps.setString(35,PTR10 );
				    ps.setString(36,Diagnostico );
				    
				    ps.executeUpdate();
					ps.close();
					con.cerrar();				
				}catch(Exception ex){
		        	System.out.println("ERROR EN MetodoCrearPaciente>>CrearBiopsiaRenal "+ex);
				}
			}
	
	
	
	
	public void CrearDatosComplementarios(String Protocolo,String TipoEspecimen,String DiagnosticoClinico,
			String Medico,String FechaRecibo,String FechaEntrega,String CodAsignacion){
		/**
		 * se crean las areas de patologia.
		 */
//		
		   CrearFormatoSimple cfs = new CrearFormatoSimple();
		   cfs.setProtocolo(Protocolo);
		   cfs.setTipoEspecimen(TipoEspecimen);
		   cfs.setDiagnosticoClinico(DiagnosticoClinico);
		   cfs.setMedico(Medico);
		   cfs.setFechaRecibo(FechaRecibo);
		   cfs.setFechaEntrega(FechaEntrega);
		   
		
			try{
				
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("insert into pat_datoscomplementarios(fecharecibo,fechaentrega,tipoespecimen,diagnosticoclinico,medicotratante,protocolo,codAsigForm_fk) values(?,?,?,?,?,?,?)");
				    ps.setString(1, cfs.getFechaRecibo());
				    ps.setString(2, cfs.getFechaEntrega());
				    ps.setString(3, cfs.getTipoEspecimen());
				    ps.setString(4, cfs.getDiagnosticoClinico());
				    ps.setString(5, cfs.getMedico());
				    ps.setString(6, cfs.getProtocolo());
				    ps.setString(7, CodAsignacion);
				    ps.executeUpdate();
					ps.close();
					con.cerrar();				
				}catch(Exception ex){
		        	System.out.println("ERROR EN MetodoCrearPaciente>>CrearDatosComplementarios "+ex);
				}
			}
	
	
	public void ActualizarBiopsia(String CodBiopsia,String DesMacro,String DescMicro1,
			String DescMicro2,String ObserLleno,
			String RE1,String RE2,String RE3,String RE4,String RE5,String RE6,String RE7,String RE8,String RE9,String RE10,
			String IT1,String IT2,String IT3,String IT4,String IT5,String IT6,String IT7,String IT8,String IT9,String IT10,
			String PTR1,String PTR2,String PTR3,String PTR4,String PTR5,String PTR6,String PTR7,String PTR8,String PTR9,String PTR10,
			String Diagnostico){		
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement(" UPDATE pat_biopsiarenal SET  DesMacro=?, DescMicro1=?, DescMicro2=?, ObserLleno=?, RE1=?, RE2=?, RE3=?, RE4=?, RE5=?, RE6=?, RE7=?, RE8=?, RE9=?, RE10=?, IT1=?, IT2=?, IT3=?, IT4=?, IT5=?, IT6=?, IT7=?, IT8=?, IT9=?, IT10=?, PTR1=?, PTR2=?, PTR3=?, PTR4=?, PTR5=?, PTR6=?, PTR7=?, PTR8=?, PTR9=?, PTR10=?, Diagnostico=? WHERE codigo="+CodBiopsia+" ");
	     	
	     	//st.setString(1,CodBiopsia );
	     	st.setString(1,DesMacro );
	     	st.setString(2,DescMicro1 );
	     	st.setString(3,DescMicro2 );
	     	st.setString(4,ObserLleno );
	     	st.setString(5,RE1 );
	     	st.setString(6,RE2 );
	     	st.setString(7,RE3 );
	     	st.setString(8,RE4 );
	     	st.setString(9,RE5 );
	     	st.setString(10,RE6 );
	     	st.setString(11,RE7 );
	     	st.setString(12,RE8 );
	     	st.setString(13,RE9 );
	     	st.setString(14,RE10 );
	     	st.setString(15,IT1 );
	     	st.setString(16,IT2 );
	     	st.setString(17,IT3 );
	     	st.setString(18,IT4 );
	     	st.setString(19,IT5 );
	     	st.setString(20,IT6 );
		    st.setString(21,IT7 );
		    st.setString(22,IT8 );
		    st.setString(23,IT9 );
		    st.setString(24,IT10 );
		    st.setString(25,PTR1 );
		    st.setString(26,PTR2 );
		    st.setString(27,PTR3 );
		    st.setString(28,PTR4 );
		    st.setString(29,PTR5 );
		    st.setString(30,PTR6 );
		    st.setString(31,PTR7 );
		    st.setString(32,PTR8 );
		    st.setString(33,PTR9 );
		    st.setString(34,PTR10 );
		    st.setString(35,Diagnostico );
	     	
	     	
	     	st.executeUpdate();
	     	st.close();
	     	con.cerrar();	     	
	     }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoCrearPaciente>>ActualizarAsignacion "+ex);
	     	ex.getMessage();	     
	     }		    
	 }
	
	
	public void ActualizarAsignacion(String CodAsignacion,String Est){		
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement(" update pat_adm_formatos_pac set estado="+Est+" where codigo='"+CodAsignacion+"'");
	     	st.executeUpdate();
	     	st.close();
	     	con.cerrar();	     	
	     }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoCrearPaciente>>ActualizarAsignacion "+ex);
	     	ex.getMessage();	     
	     }		    
	 }
	
	public void ActualizarAprobarAsignacion(String CodAsignacion,String Est,String CodUsuario){		
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement(" update pat_adm_formatos_pac set estado="+Est+",codigo_usu_fk="+CodUsuario+" where codigo='"+CodAsignacion+"'");
	     	st.executeUpdate();
	     	st.close();
	     	con.cerrar();	     	
	     }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoCrearPaciente>>ActualizarAsignacion "+ex);
	     	ex.getMessage();	     
	     }		    
	 }
	
	
	
	public java.sql.ResultSet ObtenerDatosComplementarios(String CodAsignacion){
		/**
		 * se busca el nombre del estudio y el tipo de formato.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM pat_datoscomplementarios WHERE codAsigForm_fk="+CodAsignacion+"");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearPaciente>>ObtenerDatosComplementarios "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerNombreEstudiosTipoFormato(String CodigoEstudio){
		/**
		 * se busca el nombre del estudio y el tipo de formato.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select NombreEstudio,TipoFormato from pat_estudios where codigo="+CodigoEstudio+"");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearPaciente>>ObtenerNombreEstudiosTipoFormato "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerEstudios(){
		/**
		 * se busca todos los estudios
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo,NombreEstudio,TipoFormato from pat_estudios");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearPaciente>>ObtenerEstudios "+ex);
        }	
        return rs;
    }
		
	public java.sql.ResultSet ObtenerDatosPaciente(String TipoDocumento,String Identificacion){
		/**
		 * se busca el paciente con los parametros recibidos.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select pac_codigo_paciente,tipo_documento,numero_documento,nombre,primer_apellido,genero,(year(CURDATE())-year(fecha_nacimiento)) - (right(CURDATE(),5) < right(fecha_nacimiento, 5)) as edad from adm_paciente where tipo_documento='"+TipoDocumento+"' and numero_documento='"+Identificacion+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearPaciente>>ObtenerDatosPaciente "+ex);
        }	
        return rs;
    }
	
	/*public java.sql.ResultSet ObtenerEstudiosPacienteActu(String TipoDocumento,String Identificacion){
		/**
		 * se busca el paciente con los parametros recibidos.
		 */
      /*  java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT afp.codigo,hfor.nombre_formato,afp.fecha,afp.hora,ppa.nombre,CONCAT(ppa.primer_apellido,' ',ppa.segundo_apellido) as apellido FROM adm_paciente ppa,pat_adm_formatos_pac afp,pat_formato hfor WHERE afp.estado=1 and afp.codigo_for_fk=hfor.codigo AND afp.codigo_pac_fk=ppa.pac_codigo_paciente AND ppa.tipo_documento='"+TipoDocumento+"' AND ppa.numero_documento='"+Identificacion+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearPaciente>>ObtenerEstudiosPacienteActu "+ex);
        }	
        return rs;
    }*/
	
	public java.sql.ResultSet ObtenerEstudiosPorArea(String CodArea_fk){
		/**
		 * se busca todas las areas creadas en patologia.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select pes.NombreEstudio,par.NombreArea from pat_estudios pes,pat_area par where par.codigo=pes.codArea_fk and pes.codArea_fk="+CodArea_fk+"");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearPaciente>>ObtenerEstudiosPorArea "+ex);
        }	
        return rs;
    }
		
	public java.sql.ResultSet ObtenerAreasCreadas(){
		/**
		 * se busca todas las areas creadas en patologia.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo,NombreArea from pat_area");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearPaciente>>ObtenerAreasCreadas "+ex);
        }	
        return rs;
    }
	
	public void CrearEstudio(String NombreEstudio,String CodArea_fk){
		/**
		 * se crean los estudios a las areas creadas
		 */
		   CrearPaciente cp = new CrearPaciente();
		   cp.setNombreEstudio(NombreEstudio);
		   cp.setCodArea_fk(CodArea_fk);
		
			try{
				
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("insert into pat_estudios(NombreEstudio,codArea_fk) values(?,?)");
				    ps.setString(1, cp.getNombreEstudio());
				    ps.setString(2, cp.getCodArea_fk());	
				 	ps.executeUpdate();
					ps.close();
					con.cerrar();				
				}catch(Exception ex){
		        	System.out.println("ERROR EN MetodoCrearPaciente>>CrearEstudio "+ex);
				}
			}
	
	public void CrearArea(String NombreArea){
		/**
		 * se crean las areas de patologia.
		 */
		   CrearPaciente cp = new CrearPaciente();
		   cp.setNombreArea(NombreArea);
		
			try{
				
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("insert into pat_area(NombreArea) values(?)");
				    ps.setString(1, cp.getNombreArea());				 
				 	ps.executeUpdate();
					ps.close();
					con.cerrar();				
				}catch(Exception ex){
		        	System.out.println("ERROR EN MetodoCrearPaciente>>CrearArea "+ex);
				}
			}
	
	public void IngresarPaciente(String TipoDocumento,String Identificacion,String Nombre,String Apellidos,String FechaNacimiento,String Genero ,String Eps,String Telefono,String Direccion,String Email){
		/**
		 * se crea el paciente de patologia con todos los datos.
		 */
		   CrearPaciente cp = new CrearPaciente();
		   cp.setTipoDocumento(TipoDocumento);
		   cp.setIdentificacion(Identificacion);
		   cp.setNombre(Nombre);
		   cp.setApellidos(Apellidos);
		   cp.setFechaNacimiento(FechaNacimiento);
		   cp.setGenero(Genero);
		   cp.setEps(Eps);
		   cp.setTelefono(Telefono);
		   cp.setDireccion(Direccion);
		   cp.setEmail(Email);
			try{
				String FechaNaci=null;
				if(FechaNacimiento==""){
					FechaNaci=null;
				}else{				
				String ini,med,fin=null;
				ini=cp.getFechaNacimiento().substring(0,2);
				med=cp.getFechaNacimiento().substring(3,5);
				fin=cp.getFechaNacimiento().substring(6,10);
				FechaNaci=fin+"-"+med+"-"+ini;
				}
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("insert into adm_paciente(tipo_documento,identificacion,nombre,apellidos,fecha_nacimiento,genero,eps,telefono,direccion,email) values(?,?,?,?,?,?,?,?,?,?)");
				    ps.setString(1, cp.getTipoDocumento());
				    ps.setString(2, cp.getIdentificacion());
				    ps.setString(3, cp.getNombre());
				    ps.setString(4, cp.getApellidos());
				    ps.setString(5, FechaNaci);
				    ps.setString(6, cp.getGenero());
				    ps.setString(7, cp.getEps());
				    ps.setString(8, cp.getTelefono());
				    ps.setString(9, cp.getDireccion());
				    ps.setString(10, cp.getEmail());
				 	ps.executeUpdate();
					ps.close();
					con.cerrar();				
				}catch(Exception ex){
		        	System.out.println("ERROR EN MetodoCrearPaciente>>IngresarPaciente "+ex);
				}
			}
	
	public ResultSet AutocompletarPacientePatologia(String cod,String tipo) throws Exception {
		  java.sql.ResultSet r=null;
	        Statement st = null;
	       Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	r=st.executeQuery("SELECT numero_documento,nombre,primer_apellido FROM adm_paciente WHERE numero_documento LIKE '"+cod+"%' and tipo_documento='"+tipo+"'");
	        return r;
	    }
	 
	public ResultSet listarParaAutocompletarNombrePaciente(String cod) throws Exception {
		  java.sql.ResultSet r=null;
	        Statement st = null;
	       Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	r=st.executeQuery("SELECT numero_documento,nombre,CONCAT(primer_apellido,' ',segundo_apellido) as apellido,tipo_documento FROM adm_paciente WHERE (nombre LIKE '"+cod+"%' or primer_apellido LIKE '"+cod+"%' or segundo_apellido LIKE '"+cod+"%') ");
	        return r;
	    }
	
	
	public java.sql.ResultSet ObtenerEstudiosPacientePorAprobar(String TipoDocumento,String Identificacion){
		
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	//rs=st.executeQuery("SELECT afp.codigo,hfor.nombre_formato,afp.fecha,afp.hora,ppa.nombre,CONCAT(ppa.primer_apellido,' ',ppa.segundo_apellido) as apellido,hfor.repetido FROM adm_paciente ppa,pat_adm_formatos_pac afp,pat_formato hfor WHERE afp.estado=2 and afp.codigo_for_fk=hfor.codigo AND afp.codigo_pac_fk=ppa.pac_codigo_paciente AND ppa.tipo_documento='"+TipoDocumento+"' AND ppa.numero_documento='"+Identificacion+"' ");
        	rs=st.executeQuery("SELECT afp.codigo,hfor.nombre_formato,afp.fecha,afp.hora,ppa.nombre,CONCAT(ppa.primer_apellido,' ',ppa.segundo_apellido) as apellido,hfor.repetido,ppa.tipo_documento,ppa.numero_documento  FROM adm_paciente ppa,pat_adm_formatos_pac afp,pat_formato hfor WHERE afp.estado=2 and afp.codigo_for_fk=hfor.codigo AND afp.codigo_pac_fk=ppa.pac_codigo_paciente ");

        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearPaciente>>ObtenerEstudiosPaciente "+ex);
        }	
        return rs;
    }
	 
	public java.sql.ResultSet ObtenerEstudiosPaciente(String TipoDocumento,String Identificacion){
			
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT afp.codigo,hfor.nombre_formato,afp.fecha,afp.hora,ppa.nombre,CONCAT(ppa.primer_apellido,' ',ppa.segundo_apellido) as apellido,hfor.repetido FROM adm_paciente ppa,pat_adm_formatos_pac afp,pat_formato hfor WHERE afp.estado=1 and afp.codigo_for_fk=hfor.codigo AND afp.codigo_pac_fk=ppa.pac_codigo_paciente AND ppa.tipo_documento='"+TipoDocumento+"' AND ppa.numero_documento='"+Identificacion+"' ");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoCrearPaciente>>ObtenerEstudiosPaciente "+ex);
	        }	
	        return rs;
	    }
	
	public java.sql.ResultSet ObtenerResultadosEstudiosActu(String CodAsignacion){
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT DISTINCT hres.codigo,pdc.codigo,hfr.nombre_formato,har.nombre_area,hpr.nombre_pregunta,REPLACE(hres.resultados,'@','ñ')AS resultados,hres.fecha,hres.hora,pdc.diagnosticoclinico,pdc.fechaentrega,pdc.fecharecibo,pdc.medicotratante,pdc.protocolo,pdc.tipoespecimen FROM pat_formato hfr,pat_area har,pat_pregunta hpr,pat_resultado hres,adm_paciente apac,pat_formato_area hfa,pat_adm_formatos_pac afc,pat_datoscomplementarios pdc WHERE hpr.codigo=hres.codPreg_fk AND har.codigo=hres.codArea_Fk AND hfr.codigo=afc.codigo_for_fk AND apac.pac_codigo_paciente=hres.codPac_fk AND afc.estado=2 AND hfa.codigo_area_fk=har.codigo AND hfa.codigo_formato_fk=hfr.codigo AND afc.codigo="+CodAsignacion+" AND afc.fecha=hres.fecha AND afc.hora=hres.hora AND pdc.codAsigForm_fk=afc.codigo AND apac.pac_codigo_paciente=afc.codigo_pac_fk ");
        	//System.out.println("SELECT DISTINCT hres.codigo,pdc.codigo,hfr.nombre_formato,har.nombre_area,hpr.nombre_pregunta,REPLACE(hres.resultados,'@','ñ')AS resultados,hres.fecha,hres.hora,pdc.diagnosticoclinico,pdc.fechaentrega,pdc.fecharecibo,pdc.medicotratante,pdc.protocolo,pdc.tipoespecimen FROM pat_formato hfr,pat_area har,pat_pregunta hpr,pat_resultado hres,adm_paciente apac,pat_formato_area hfa,pat_adm_formatos_pac afc,pat_datoscomplementarios pdc WHERE hpr.codigo=hres.codPreg_fk AND har.codigo=hres.codArea_Fk AND hfr.codigo=afc.codigo_for_fk AND apac.pac_codigo_paciente=hres.codPac_fk AND afc.estado=2 AND hfa.codigo_area_fk=har.codigo AND hfa.codigo_formato_fk=hfr.codigo AND afc.codigo="+CodAsignacion+" AND afc.fecha=hres.fecha AND afc.hora=hres.hora AND pdc.codAsigForm_fk=afc.codigo AND apac.pac_codigo_paciente=afc.codigo_pac_fk ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearPaciente>>ObtenerResultadosEstudiosActu "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet ObtenerResultadosEstudiosAprobar(String CodAsignacion){
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT pdc.*,pbs.* FROM pat_adm_formatos_pac paf,pat_datoscomplementarios pdc,pat_biopsiarenal pbs WHERE paf.codigo="+CodAsignacion+" AND pdc.codAsigForm_fk=paf.codigo AND pbs.cod_dat_comple_fk=pdc.codigo ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearPaciente>>ObtenerResultadosEstudiosAprobar "+ex);
        }	
        return rs;
    }


}
