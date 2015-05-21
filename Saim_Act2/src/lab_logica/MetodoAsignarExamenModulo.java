package lab_logica;

import java.sql.Statement;

import adm_logica.Conexion;

public class MetodoAsignarExamenModulo {
	
public java.sql.ResultSet BuscarPaciente(String TipoDocumento,String NumeroDocumento){
		
	    java.sql.ResultSet rs=null;
	    Statement st = null;
	    try{
	    	Conexion con=new Conexion();
	    	st = con.conn.createStatement();
	    	rs=st.executeQuery("select lpac.pac_codigo_paciente,lpac.nombre,lpac.primer_apellido,lpac.segundo_apellido,lpac.genero,lpac.fecha_nacimiento,(year(curdate())-year(lpac.fecha_nacimiento)) - (right(curdate(),5) < right(lpac.fecha_nacimiento, 5)) as edad,lent.nombre_entidad from adm_paciente lpac,adm_convenio lcon,adm_entidad lent where lpac.tipo_documento='"+TipoDocumento+"' and lpac.numero_documento='"+NumeroDocumento+"' and lpac.conv_numero_contrato_fk=lcon.conv_numero_contrato and lent.ent_nit=lcon.ent_nit_contratante_fk");
	    }
	    catch(Exception ex){
	    	System.out.println("ERROR EN MetodoAsignarExamenModulo>>BuscarPaciente "+ex);
	    }
	    return rs;
	}

public java.sql.ResultSet ObtenerExamenesArea(String CodArea){
	/**
	 * se obtienen los examenes de el area seleccionada.
	 */
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery("select lsa.codigo,lsa.nombre,replace(lsa.nombre,lsa.nombre,'2') as tipo from lab_area la,lab_subarea lsa where la.codigo=lsa.codarea_fk and la.codigo='"+CodArea+"' union select lex.codigo,lex.nombre,lex.tipo from lab_examen lex,lab_area la where lex.codigoarea_fk=la.codigo and la.codigo='"+CodArea+"'");
    }
    catch(Exception ex){
    	System.out.println("Error en MetodoAsignarExamenModulo>>ObtenerExamenesArea "+ex);
    }	
    return rs;
}
public java.sql.ResultSet ObtenerAreaExamenes(){
	/**
	 * se obtienen las areas de los examenes para su posterior busqueda.
	 */
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery("select codigo,nombre from lab_area where codigo>0");
    }
    catch(Exception ex){
    	System.out.println("Error en MetodoAsignarExamenModulo>>ObtenerAreaExamenes "+ex);
    }	
    return rs;
}
}
