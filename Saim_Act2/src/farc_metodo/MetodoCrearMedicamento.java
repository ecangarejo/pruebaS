package farc_metodo;

import java.sql.PreparedStatement;
import java.sql.Statement;

import adm_logica.Conexion;
import farc_bean.CrearArticulo;

public class MetodoCrearMedicamento{
	public java.sql.ResultSet verificarMedicamento(String med, String tipo, String comp){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT fm.cod_medicamento FROM farc_medicamentos fm WHERE fm.nombre='"+med+"' AND fm.tipo="+tipo+" AND fm.composicion="+comp);
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearMedicamento>>verificarMedicamento "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet verificarTipoCUMMed(String codConc, String codMed){	
		/**
		 * consulta que obtiene las unidades del modulo de farmacia, 
		 */

        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT ffmed.CUM FROM farc_concentracionsust fcs,farc_ffmedicamento ffmed WHERE fcs.codigo="+codConc+" AND fcs.cod_ff_fk=ffmed.codigo AND ffmed.cod_medicamento_fk="+codMed);
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearArticulo>>verificarTipoCUMMed "+ex);
        }	
        return rs;
    }
	
	public void crearMedicamento(String med,String clasificacion, String tipoComp){	
		try{
			PreparedStatement ps = null;
		    Conexion con=new Conexion();
		    ps=con.conn.prepareStatement("INSERT INTO farc_medicamentos(nombre,tipo,composicion) VALUES (?,?,?)");
		    ps.setString(1,med);
		    ps.setString(2,clasificacion);
		    ps.setString(3,tipoComp);
		    ps.executeUpdate();
			ps.close();
			con.cerrar();				
		}catch(Exception ex){
        	System.out.println("ERROR EN MetodoCrearMedicamento>>crearMedicamento "+ex);
		}
	}
	
	public void crearConcentracion(String compuesto,String selff,String concentracion,String udconcen){
		
		try{
			PreparedStatement ps = null;
		    Conexion con=new Conexion();
		    ps=con.conn.prepareStatement("INSERT INTO farc_concentracionsust(concentracion_sustGenerica,cod_ud_fk,cod_sustGenerica_fk,cod_ff_fk) VALUES(?,?,?,?)");
		    ps.setString(3,compuesto);
		    ps.setString(4,selff);
		    ps.setString(1,concentracion);
		    ps.setString(2,udconcen);
		    ps.executeUpdate();
			ps.close();
			con.cerrar();				
		}catch(Exception ex){
        	System.out.println("ERROR EN MetodoCrearMedicamento>>crearConcentracion "+ex);
		}
	}
	
public void crearFFMed(String selff,String tipoMed,String codMed,String medControl,String cum,String atc){
		
		try{
			PreparedStatement ps = null;
		    Conexion con=new Conexion();
		    ps=con.conn.prepareStatement("INSERT INTO farc_ffMedicamento(cod_ff_fk,cod_clasificacion,cod_medicamento_fk,medControl,CUM,ATC) VALUES(?,?,?,?,?,?)");
		    ps.setString(1,selff);
		    ps.setString(2,tipoMed);
		    ps.setString(3,codMed);
		    ps.setString(4,medControl);
		    ps.setString(5,cum);
		    ps.setString(6,atc);
		    ps.executeUpdate();
			ps.close();
			con.cerrar();				
		}catch(Exception ex){
        	System.out.println("ERROR EN MetodoCrearMedicamento>>crearMedicamento "+ex);
		}
	}
	
	public java.sql.ResultSet obtenerConcentracion(String codMed){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT ff.descripcion, CONCAT(fcs.concentracion_sustGenerica,' ',fu.sigla),fcs.codigo,fcs.cod_ff_fk " +
        			           "FROM farc_concentracionsust fcs, farc_formafarmaceutica ff, farc_unidades fu, farc_ffmedicamento ffm " +
        			           "WHERE ffm.cod_medicamento_fk='"+codMed+"' " +
        			           "AND ffm.cod_ff_fk=ff.codigo " +
        			           "AND ffm.codigo=fcs.cod_ff_fk " +
        			           "AND fcs.cod_ud_fk=fu.codigo");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearMedicamento>>verificarMedicamento "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet obtenerPrincipioActivo(String nomMed){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT fsa.cod_sustGenerica,fsa.nombre FROM farc_sustanciagenerica fsa WHERE fsa.nombre='"+nomMed+"'");
        	System.out.println("SELECT fsa.cod_sustGenerica,fsa.nombre FROM farc_sustanciagenerica fsa WHERE fsa.nombre='"+nomMed+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearMedicamento>>obtenerPrincipioActivo "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet obtenerPrecentacion(){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT ftp.codigo,ftp.descripcion FROM farc_tipo_presentacion ftp");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearMedicamento>>obtenerPrincipioActivo "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet obtenerUDPrecentacion(){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT fup.codigo,fup.sigla FROM farc_udpresentacion fup");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearMedicamento>>obtenerPrincipioActivo "+ex);
        }	
        return rs;
    }
	
	public void crearPresentacion(String codMed,String codConc,String tipoPre,String udTipoPres,String cantPres,String codFF){
		try{
			PreparedStatement ps = null;
		    Conexion con=new Conexion();
		    ps=con.conn.prepareStatement("INSERT INTO farc_presentacion(volumen,cod_ud_fk,cod_tipoPresntacion_fk,cod_medicamento_fk,cod_concentracion_fk,cod_ffm_fk) VALUES(?,?,?,?,?,?)");
		    ps.setString(1,cantPres);
		    ps.setString(2,udTipoPres);
		    ps.setString(3,tipoPre);
		    ps.setString(4,codMed);
		    ps.setString(5,codConc);
		    ps.setString(6,codFF);
		    ps.executeUpdate();
			ps.close();
			con.cerrar();				
		}catch(Exception ex){
        	System.out.println("ERROR EN MetodoCrearMedicamento>>crearConcentracion "+ex);
		}
	}
	
	public java.sql.ResultSet obtenerPresentaciom(String codMed,String codConc){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT ftp.descripcion, CONCAT(fp.volumen,' ',fup.descripcion) AS medida " +
        			           "FROM farc_presentacion fp,farc_tipo_presentacion ftp,farc_udpresentacion fup " +
        			           "WHERE fp.cod_medicamento_fk='"+codMed+"' AND fp.cod_concentracion_fk='"+codConc+"' " +
        			           "AND fp.cod_tipoPresntacion_fk=ftp.codigo " +
        			           "AND fp.cod_ud_fk=fup.codigo");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearMedicamento>>obtenerPresentaciom "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet obtenerCodPresentacion(String codMed,String codConc){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT fp.codigo FROM farc_presentacion fp WHERE fp.cod_medicamento_fk='"+codMed+"' AND fp.cod_concentracion_fk='"+codConc+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearMedicamento>>obtenerCodPresentaciom "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet obtenerCodFFMed(String selff,String tipoMed,String codMed){
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT ffm.codigo FROM farc_ffmedicamento ffm WHERE ffm.cod_clasificacion='"+tipoMed+"' " +
        			           "AND ffm.cod_ff_fk='"+selff+"' AND ffm.cod_medicamento_fk='"+codMed+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearMedicamento>>obtenerCodFFMed "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet verificarFFConcMed(String codMed,String compuesto,String selff,String tipoMed,String concentracion,String udconcen){		
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT ffm.codigo FROM farc_ffmedicamento ffm, farc_concentracionsust fcs " +
        			           "WHERE ffm.cod_clasificacion='"+tipoMed+"' " +
        			           "AND ffm.cod_ff_fk='"+selff+"' " +
        			           "AND ffm.cod_medicamento_fk='"+codMed+"' " +
        			           "AND ffm.codigo=fcs.cod_ff_fk " +
        			           "AND fcs.concentracion_sustGenerica='"+concentracion+"' " +
        			           "AND fcs.cod_ud_fk='"+udconcen+"' " +
        			           "AND fcs.cod_sustGenerica_fk='"+compuesto+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearMedicamento>>verificarFFConcMed "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet verificarPresMedicamentos(String codMed){		
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT ffm.codigo " +
        			           "FROM farc_ffmedicamento ffm " +
        			           "WHERE ffm.cod_medicamento_fk='"+codMed+"' AND ffm.codigo NOT IN (SELECT ffm.codigo " +
        			           "FROM farc_presentacion fp, " +
        			           "farc_ffmedicamento ffm WHERE ffm.cod_medicamento_fk='"+codMed+"' " +
        			           "AND ffm.codigo=fp.cod_ffm_fk)");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearMedicamento>>verificarPresMedicamentos "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet verificarATC(String codMed){		
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT fm.ATC FROM farc_medicamentos fm WHERE fm.cod_medicamento='"+codMed+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearMedicamento>>verificarATC "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet obtenerConcentracionComp(String codMed){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT ffm.codigo,ff.forma_farmaceutica,GROUP_CONCAT(fs.nombre,' ',fcs.concentracion_sustGenerica,' ',fu.sigla SEPARATOR ' + '),fcs.codigo,ffm.cod_medicamento_fk " +
        			           "FROM farc_ffmedicamento ffm, farc_concentracionsust fcs, farc_formafarmaceutica ff, farc_sustanciagenerica fs, farc_unidades fu " +
        			           "WHERE ffm.cod_medicamento_fk='"+codMed+"' " +
        			           "AND ffm.codigo=fcs.cod_ff_fk " +
        			           "AND ffm.cod_ff_fk=ff.codigo " +
        			           "AND fcs.cod_sustGenerica_fk=fs.cod_sustGenerica " +
        			           "AND fcs.cod_ud_fk=fu.codigo " +
        			           "GROUP BY ffm.codigo");
        	
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearMedicamento>>obtenerConcentracionComp "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet obtenerInfoNuevoMed(String codMed, String codFF){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT IF(ffm.cod_clasificacion=1,ffm.ATC,''),fm.nombre,fcs.concentracion_sustGenerica concentracion, " +
        			           "fgf.descripcion,fsg.nombre AS 'nombre generico',fcs.cod_ud_fk,IF(ffm.cod_clasificacion=0,'No Pos','Pos') clasificacion, " +
        			           "IF(fm.tipo=0,'Generico','Comercial') tipo,ffm.cod_ff_fk,IF(ffm.medControl=0,'No','Si') medControl " +
        			           "FROM farc_medicamentos fm,farc_ffmedicamento ffm,farc_presentacion fp, " +
        			           "farc_concentracionsust fcs,farc_sustanciagenerica fsg,farc_unidades fu," +
        			           "farc_grupofarmacologico fgf " +
        			           "WHERE fm.cod_medicamento = "+codMed+" " +
        			           "AND fm.cod_medicamento=ffm.cod_medicamento_fk " +
        			           "AND ffm.codigo='"+codFF+"'" +
        			           "AND ffm.codigo=fp.cod_ffm_fk " +
        			           "AND ffm.codigo=fcs.cod_ff_fk " +
        			           "AND fcs.cod_sustGenerica_fk=fsg.cod_sustGenerica " +
        			           "AND fcs.cod_ud_fk=fu.codigo " +
        			           "AND fsg.cod_grupoFarmacologico_fk=fgf.cod_grupoFarmacologia");
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearMedicamento>>obtenerInfoNuevoMed "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet obtenerInfoNuevoMedComp(String codMed, String codFF){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT IF(ffm.cod_clasificacion=1,ffm.ATC,''),fm.nombre,GROUP_CONCAT(fcs.concentracion_sustGenerica SEPARATOR '/') concentracion, " +
        			           "IF(fm.tipo=0,fm.nombre,GROUP_CONCAT(fsg.nombre ORDER BY fsg.cod_sustGenerica ASC SEPARATOR '+')) AS 'nombre generico', " +
        			           "GROUP_CONCAT(fu.sigla ORDER BY fu.codigo ASC SEPARATOR '/') unidades, IF(ffm.cod_clasificacion=0,'No Pos','Pos') clasificacion, " +
        			           "IF(fm.tipo=0,'Generico','Comercial') tipo, ffm.cod_ff_fk, IF(ffm.medControl=0,'No','Si') medControl " +
        			           "FROM farc_medicamentos fm,farc_concentracionsust fcs,farc_ffmedicamento ffm,farc_unidades fu, " +
        			           "farc_sustanciagenerica fsg " +
        			           "WHERE fm.cod_medicamento = '"+codMed+"' " +
        			           "AND fm.cod_medicamento = ffm.cod_medicamento_fk " +
        			           "AND ffm.codigo='"+codFF+"'" +
        			           "AND ffm.codigo = fcs.cod_ff_fk " +
        			           "AND fcs.cod_ud_fk=fu.codigo " +
        			           "AND fcs.cod_sustGenerica_fk=fsg.cod_sustGenerica " +
        			           "GROUP BY ffm.codigo ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearMedicamento>>obtenerInfoNuevoMedComp "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet obtenerPAMedComercial(String codMed){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT fsg.cod_sustGenerica,fsg.nombre " +
        			           "FROM farc_medicamentos fm, farc_sustanciagenerica fsg, farc_concentracionsust fcs, farc_ffmedicamento ffmac " +
        			           "WHERE fm.cod_medicamento = '"+codMed+"' " +
        			           "AND fm.cod_medicamento=ffmac.cod_medicamento_fk " +
        			           "AND ffmac.codigo=fcs.cod_ff_fk " +
        			           "AND fcs.cod_sustGenerica_fk=fsg.cod_sustGenerica");
        	
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearMedicamento>>obtenerPAMedComercial "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet verificarCUM(String cum){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT ffm.CUM FROM farc_ffmedicamento ffm WHERE ffm.CUM='"+cum+"'");
        	
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearMedicamento>>verificarCUM "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet verificarMED(String nomMedi){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT fm.tipo,fm.composicion FROM farc_medicamentos fm WHERE fm.nombre='"+nomMedi+"'");
        	
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearMedicamento>>verificarMED "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet obtenerCodUd(String siglaUd){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT fu.codigo FROM farc_unidades fu WHERE fu.sigla='"+siglaUd+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearMedicamento>>obtenerCodUd "+ex);
        }	
        return rs;
    }
	
	public void crearMedicamentoTabMED(String codigoMedicamento,String nombre,String concentracion,String descripcion,String cod_grupoFK,String nombre_generico,String clasificacion,String tipo,String cod_formaFK,String control,String estado,String codMed,String codFF,String codPreMed){
		try{
			PreparedStatement ps = null;
		    Conexion con=new Conexion();
		    ps=con.conn.prepareStatement("INSERT " +
		    		                     "INTO medicamentos(codigoMedicamento,nombre,concentracion,descripcion,cod_grupoFK,nombre_generico,clasificacion,tipo,cod_formaFK,control,estado,codMed,codFFmed,codPreMed) " +
		    		                     "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		    ps.setString(1,codigoMedicamento);
		    ps.setString(2,nombre);
		    ps.setString(3,concentracion);
		    ps.setString(4,descripcion);
		    ps.setString(5,cod_grupoFK);
		    ps.setString(6,nombre_generico);
		    
		    
		    ps.setString(7,clasificacion);
		    ps.setString(8,tipo);
		    ps.setString(9,cod_formaFK);
		    ps.setString(10,control);
		    ps.setString(11,estado);
		    ps.setString(12,codMed);
		    ps.setString(13,codFF);
		    ps.setString(14,codPreMed);
		    
		    ps.executeUpdate();
			ps.close();
			con.cerrar();				
		}catch(Exception ex){
        	System.out.println("ERROR EN MetodoCrearMedicamento>>crearMedicamentoTabMED "+ex);
		}
	}
	
	public java.sql.ResultSet obtenerInfoNuevoMedParaPrograma(String codMed, String codFF){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT ffm.CUM, IF(fgf.descripcion<>'',CONCAT(fm.nombre,' [',UPPER(fgf.descripcion),'] [',UPPER(ffmac.forma_farmaceutica),'] ',fcs.concentracion_sustGenerica,'',fu.sigla),CONCAT(fm.nombre,' [',UPPER(ffmac.forma_farmaceutica),'] ',fcs.concentracion_sustGenerica,'',fu.sigla)) descripcion,fp.CUM  " +
        			           "FROM farc_medicamentos fm,farc_ffmedicamento ffm,farc_concentracionsust fcs, " +
        			           "farc_sustanciagenerica fsg,farc_unidades fu,farc_grupofarmacologico fgf,farc_formafarmaceutica ffmac,farc_presentacion fp " +
        			           "WHERE fm.cod_medicamento = "+codMed+" " +
        			           "AND fm.cod_medicamento=ffm.cod_medicamento_fk " +
        			           "AND ffm.codigo='"+codFF+"'" +
        			           "AND ffm.codigo=fcs.cod_ff_fk " +
        			           "AND fcs.cod_sustGenerica_fk = fsg.cod_sustGenerica " +
        			           "AND fcs.cod_ud_fk=fu.codigo " +
        			           "AND fsg.cod_grupoFarmacologico_fk=fgf.cod_grupoFarmacologia " +
        			           "AND ffm.cod_ff_fk=ffmac.codigo " +
        			           "AND fp.cod_ffm_fk=ffm.codigo ");
        	
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearMedicamento>>obtenerInfoNuevoMedParaPrograma "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet obtenerInfoNuevoMedCompParaPrograma(String codMed, String codFF){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT ffm.CUM," +
        			           "CONCAT(IF(fm.tipo=0,fm.nombre,GROUP_CONCAT(fsg.nombre ORDER BY fsg.cod_sustGenerica ASC SEPARATOR '+')),' [',UPPER(ffmac.descripcion),'] ', GROUP_CONCAT(fcs.concentracion_sustGenerica,fu.sigla SEPARATOR '/')),fp.CUM " +
        			           "FROM farc_medicamentos fm,farc_ffmedicamento ffm,farc_concentracionsust fcs, " +
        			           "farc_sustanciagenerica fsg,farc_formafarmaceutica ffmac,farc_unidades fu,farc_presentacion fp " +
        			           "WHERE fm.cod_medicamento = '"+codMed+"' " +
        			           "AND fm.cod_medicamento=ffm.cod_medicamento_fk " +
        			           "AND ffm.codigo='"+codFF+"' " +
        			           "AND ffm.codigo=fcs.cod_ff_fk " +
        			           "AND fcs.cod_sustGenerica_fk=fsg.cod_sustGenerica " +
        			           "AND ffm.cod_ff_fk=ffmac.codigo " +
        			           "AND fcs.cod_ud_fk=fu.codigo " +
        			           "AND fp.cod_ffm_fk=ffm.codigo ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearMedicamento>>obtenerInfoNuevoMedCompParaPrograma "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet listarPA(String texto){	
		/**
		 * consulta que obtiene las unidades del modulo de farmacia, 
		 */

        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT fsg.nombre FROM farc_sustanciagenerica fsg WHERE fsg.nombre LIKE '%"+texto+"%' ORDER BY fsg.nombre ASC LIMIT 15");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearMedicamento>>listarPA "+ex);
        }	
        return rs;
    }
	
	public void crearProgramaMedicamento(int manual_base,String cod_nc,String cod_referencia,String cod_cups,String descripcion,int especialidad,int clase_servicio,int subcentro_costo,int archivo_rip,String fecha,String hora,String cod_usuario){
		try{
			PreparedStatement ps = null;
		    Conexion con=new Conexion();
		    ps=con.conn.prepareStatement("INSERT INTO fact_programas(manual_base,nivel_complejidad ,cod_referencia,cod_cups,descripcion,especialidad,clase_servicio,subcentro_costo,archivo_rip,fecha,hora,cod_usuario) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
		    ps.setInt(1,manual_base);
		    ps.setString(2,cod_nc);
		    ps.setString(3,cod_referencia);
		    
		    ps.setString(4,cod_cups);
		    ps.setString(5,descripcion);
		    ps.setInt(6,especialidad);
		    ps.setInt(7,clase_servicio);
		    
		    ps.setInt(8,subcentro_costo);
		    ps.setInt(9,archivo_rip);
		    ps.setString(10,fecha);
		    ps.setString(11,hora);
		    ps.setString(12,cod_usuario);
		    
		    ps.executeUpdate();
			ps.close();
			con.cerrar();				
		}catch(Exception ex){
        	System.out.println("ERROR EN MetodoCrearMedicamento>>crearProgramaMedicamento "+ex);
		}
	}
	
	public java.sql.ResultSet obtenerNuevoMed(){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT m.codigo FROM medicamentos m ORDER BY m.codigo DESC LIMIT 1");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearMedicamento>>obtenerNuevoMed "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet obtenerNuevoProg(){	
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT fp.cod_programa, fp.cod_referencia, fp.descripcion FROM fact_programas fp ORDER BY fp.cod_programa DESC LIMIT 1");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearMedicamento>>obtenerNuevoProg "+ex);
        }	
        return rs;
    }
	
	public void crearProgMed(String codProg,String codMed){
		try{
			PreparedStatement ps = null;
		    Conexion con=new Conexion();
		    ps=con.conn.prepareStatement("INSERT INTO fact_prog_med(cod_prog,cod_med) VALUES (?,?)");
		    ps.setString(1,codProg);
		    ps.setString(2,codMed);
		    
		    
		    
		    ps.executeUpdate();
			ps.close();
			con.cerrar();				
		}catch(Exception ex){
        	System.out.println("ERROR EN MetodoCrearMedicamento>>crearProgramaMedicamento "+ex);
		}
	}
	
	
	public java.sql.ResultSet PrincipioActivo(String sql){	
		/**
		 * consulta que obtiene las unidades del modulo de farmacia, 
		 */

        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT fsg.cod_sustGenerica,fsg.nombre FROM farc_sustanciagenerica fsg "+sql+" ORDER BY fsg.nombre");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearArticulo>>PrincipioActivo "+ex);
        }	
        return rs;
    }
	
	public void actualizarATC(String codMed, String ATC ){	
		/**
		 */
		 PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE farc_medicamentos SET ATC='"+ATC+"' WHERE cod_medicamento='"+codMed+"'");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoCrearArticulo>>actualizarATC "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public java.sql.ResultSet obtenerConseNP(){	


        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT m.codigoMedicamento FROM medicamentos m WHERE m.codigoMedicamento LIKE 'np%' ORDER BY m.codigoMedicamento DESC LIMIT 1");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearArticulo>>obtenerConseNP "+ex);
        }	
        return rs;
    }

	/////PARA PRODUCTO //////	
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
	        	System.out.println("ERROR EN MetodoCrearMedicamento>>CrearProducto "+ex);
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
	            	System.out.println("Error en  MetodoCrearMedicamento>>ObtenerProducto "+ex);
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
	        	System.out.println("ERROR EN MetodoCrearMedicamento>>CrearProducto "+ex);
			}			
		}

	////FIN PARA PRODUCTO////
}

