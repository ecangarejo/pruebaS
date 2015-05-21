package cons_metodo;

import java.sql.Statement;

import adm_logica.Conexion;

public class MetodoConsultas {

	
	public java.sql.ResultSet BuscarRangocaso(String tcaso){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       // System.out.println("Fechai "+Fechai+"Fechaf "+Fechaf+"Ent "+ftent);
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT rangoi, rangof FROM cons_tipocasos WHERE codigo='"+tcaso+"' ");
	        	System.out.println("SELECT rangoi, rangof FROM cons_tipocasos WHERE codigo='"+tcaso+"' ");
	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoConsultas>>BuscarRangocaso"+ex);
	            }	
	            return rs;
	}
	
	
	public java.sql.ResultSet BuscarProgramas(String CodManual,String Programa){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       // System.out.println("Fechai "+Fechai+"Fechaf "+Fechaf+"Ent "+ftent);
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT fp.cod_cups,fp.cod_referencia,fp.descripcion AS NombrePrograma, ft.valor ,fmt.descripcion,ae.nombre_entidad,fp.cod_programa FROM fact_programas fp,fact_tarifas ft, fact_manuales_tarifarios fmt,fact_tarifas_convenios ftc,fact_convenios fc,adm_entidad ae WHERE fp.cod_programa = ft.programa AND fmt.cod_manual_tarifario=ft.manual_tarifario AND ftc.cod_manualTarifario=fmt.cod_manual_tarifario AND fc.cod_convenio=ftc.cod_convenio  AND fc.cod_entidad=ae.ent_nit AND fmt.manual_base='"+CodManual+"' AND (fp.cod_referencia LIKE '%"+Programa+"%'  OR fp.descripcion LIKE '%"+Programa+"%' OR fp.cod_cups LIKE '%"+Programa+"%')  GROUP BY fp.descripcion  ");
	        	System.out.println("SELECT fp.cod_cups,fp.cod_referencia,fp.descripcion AS NombrePrograma, ft.valor ,fmt.descripcion,ae.nombre_entidad,fp.cod_programa FROM fact_programas fp,fact_tarifas ft, fact_manuales_tarifarios fmt,fact_tarifas_convenios ftc,fact_convenios fc,adm_entidad ae WHERE fp.cod_programa = ft.programa AND fmt.cod_manual_tarifario=ft.manual_tarifario AND ftc.cod_manualTarifario=fmt.cod_manual_tarifario AND fc.cod_convenio=ftc.cod_convenio  AND fc.cod_entidad=ae.ent_nit AND fmt.manual_base='"+CodManual+"' AND (fp.cod_referencia LIKE '%"+Programa+"%'  OR fp.descripcion LIKE '%"+Programa+"%' OR fp.cod_cups LIKE '%"+Programa+"%')  GROUP BY fp.descripcion  ");
	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoConsultas>>BuscarRangocaso"+ex);
	            }	
	            return rs;
	}
	
	public java.sql.ResultSet BuscarProgramasUnico(String CodManual,String Programa){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       // System.out.println("Fechai "+Fechai+"Fechaf "+Fechaf+"Ent "+ftent);
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT fp.cod_cups,fp.cod_referencia,fp.descripcion AS NombrePrograma, ft.valor ,fmt.descripcion,ae.nombre_entidad,fp.cod_programa FROM fact_programas fp,fact_tarifas ft, fact_manuales_tarifarios fmt,fact_tarifas_convenios ftc,fact_convenios fc,adm_entidad ae WHERE fp.cod_programa = ft.programa AND fmt.cod_manual_tarifario=ft.manual_tarifario AND ftc.cod_manualTarifario=fmt.cod_manual_tarifario AND fc.cod_convenio=ftc.cod_convenio  AND fc.cod_entidad=ae.ent_nit AND fmt.manual_base='"+CodManual+"' AND fp.cod_programa='"+Programa+"'  ORDER BY ae.nombre_entidad  ");
	        	System.out.println("SELECT fp.cod_cups,fp.cod_referencia,fp.descripcion AS NombrePrograma, ft.valor ,fmt.descripcion,ae.nombre_entidad,fp.cod_programa FROM fact_programas fp,fact_tarifas ft, fact_manuales_tarifarios fmt,fact_tarifas_convenios ftc,fact_convenios fc,adm_entidad ae WHERE fp.cod_programa = ft.programa AND fmt.cod_manual_tarifario=ft.manual_tarifario AND ftc.cod_manualTarifario=fmt.cod_manual_tarifario AND fc.cod_convenio=ftc.cod_convenio  AND fc.cod_entidad=ae.ent_nit AND fmt.manual_base='"+CodManual+"' AND fp.cod_programa='"+Programa+"'  ORDER BY ae.nombre_entidad  ");
	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoConsultas>>BuscarRangocaso"+ex);
	            }	
	            return rs;
	}
	
	public java.sql.ResultSet BuscarDX(String DX){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       // System.out.println("Fechai "+Fechai+"Fechaf "+Fechaf+"Ent "+ftent);
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT * FROM cie10 WHERE nombre LIKE '%"+DX+"%' OR codigoCIE LIKE '%"+DX+"%' ORDER BY nombre ");
	        	System.out.println("SELECT * FROM cie10 WHERE nombre LIKE '%"+DX+"%' OR codigoCIE LIKE '%"+DX+"%' ORDER BY nombre ");
	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoConsultas>>BuscarRangocaso"+ex);
	            }	
	            return rs;
	}

}
