package fact_metodo;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;

import adm_logica.Conexion;

public class MetodoIngNumAuto {

	
	public ResultSet BuscarDatos(String Adm){
		/*Busca los datos del Pacientes  teniedo como filtro el Numero de la Admision */
        ResultSet rs = null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs = st.executeQuery("SELECT CONCAT(p.tipo_documento,'-',p.numero_documento) AS id, CONCAT(p.nombre,' ',p.primer_apellido,' ',p.segundo_apellido) AS nom_paciente, a.fecha_registro, a.numero_autorizacion, ae.nombre_entidad, ae.ent_nit "+
        						"FROM adm_admisiones a, adm_paciente p, adm_convenio ac, adm_entidad ae "+
        						"WHERE p.pac_codigo_paciente=a.pac_codigo_paciente_fk AND ac.conv_numero_contrato=p.conv_numero_contrato_fk AND ac.ent_nit_contratante_fk=ae.ent_nit "+
        						"AND a.adm_numero_ingreso='"+Adm+"'");
        } catch(Exception ex){
        	System.err.println("Error en MetodoIngNumAuto>>BuscarDatos\n"+ex);
        }
        return rs;
    }
	
	public ResultSet BuscarDatosFact(String Fact){
		/*Busca los datos del Pacientes  teniedo como filtro el Numero de la Factura */
        ResultSet rs = null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs = st.executeQuery("SELECT fn.consecutivo, fef.razon_social, fef.documento,fef.nombre_paciente, fef.cod_admision, fef.cod_enc_factura, fef.num_autorizacion "+
        						"FROM fact_enc_factura fef, fact_numeradas fn "+
        						"WHERE fef.cod_enc_factura=fn.cod_enc_fact_fk AND fn.estadoFact!=5 AND fn.consecutivo LIKE '%"+Fact+"%' ");
        } catch(Exception ex){
        	System.err.println("Error en MetodoIngNumAuto>>BuscarDatosFact\n"+ex);
        }
        return rs;
    }
	
	
	public ResultSet GuardarAutAdm(String Adm,String noauto){
		/*Guarda el numero de autorizacion en la tabla Admisiones */
		ResultSet rs = null;
		PreparedStatement st = null;
	    try{
	    	Conexion con=new Conexion();
	    	st= con.conn.prepareStatement(" UPDATE adm_admisiones SET numero_autorizacion='"+noauto+"' WHERE adm_numero_ingreso='"+Adm+"'");
	    	System.out.println(" UPDATE adm_admisiones SET numero_autorizacion='"+noauto+"' WHERE adm_numero_ingreso='"+Adm+"'");
	    	st.executeUpdate();
	    	st.close();
	    	con.cerrar();
	    	
	    }
	    catch(Exception ex){
	    	System.out.println("ERROR EN MetodoIngNumAuto>>GuardarAutAdm "+ex);
	    
	    }
		return rs;	
    }
	
	
	public ResultSet GuardarAutEncCero(String Adm,String Ent, String noauto){
		/*Guarda el numero de autorizacion en la tabla fact_enc_factura simepre y cuando los enc esten en cero y pertenezcan a la eps actual del paciente*/
		ResultSet rs = null;
		PreparedStatement st = null;
	    try{
	    	Conexion con=new Conexion();
	    	st= con.conn.prepareStatement("UPDATE fact_enc_factura SET num_autorizacion='"+noauto+"' WHERE cod_admision='"+Adm+"' and  estado=0 and cod_eps='"+Ent+"' ");
	    	System.out.println("UPDATE fact_enc_factura SET num_autorizacion='"+noauto+"' WHERE cod_admision='"+Adm+"' and  estado=0 and cod_eps='"+Ent+"' ");
	    	st.executeUpdate();
	    	st.close();
	    	con.cerrar();
	    	
	    }
	    catch(Exception ex){
	    	System.out.println("ERROR EN MetodoIngNumAuto>>GuardarAutEncCero"+ex);
	    
	    }
		return rs;	
    }
	
	public ResultSet ActualizarFact(String Enca,String noauto){
		/*Guarda el numero de autorizacion en la tabla fact_enc_factura simepre y cuando los enc esten en cero y pertenezcan a la eps actual del paciente*/
		ResultSet rs = null;
		PreparedStatement st = null;
	    try{
	    	Conexion con=new Conexion();
	    	st= con.conn.prepareStatement("UPDATE fact_enc_factura SET num_autorizacion='"+noauto+"' WHERE cod_enc_factura='"+Enca+"' ");
	    	System.out.println(" UPDATE fact_enc_factura SET num_autorizacion='"+noauto+"' WHERE cod_enc_factura='"+Enca+"' ");
	    	st.executeUpdate();
	    	st.close();
	    	con.cerrar();
	    	
	    }
	    catch(Exception ex){
	    	System.out.println("ERROR EN MetodoIngNumAuto>>ActualizarFact"+ex);
	    
	    }
		return rs;	
    }
	
	public ResultSet GuardarHistorico(String Adm,String user,String Ent,Date Fecha,Time Hora,String consec,String noauto){
		/*Guarda los datos de la insercion en la tabla fact_his_num_auto */
		ResultSet rs = null;
		PreparedStatement st = null;
		PreparedStatement ps = null;
	    try{
	    	Conexion con=new Conexion();
	    	ps=con.conn.prepareStatement("insert into  fact_hist_num_auto (user,cod_adm,cod_ent,fecha,hora,consec,noauto) values(?,?,?,?,?,?,?)");
	    	ps.setString(1,user);
	    	ps.setString(2,Adm);
	    	ps.setString(3,Ent);
	    	ps.setDate(4,Fecha);
	    	ps.setTime(5,Hora);
	    	ps.setString(6,consec);
	    	ps.setString(7,noauto);
	    	ps.executeUpdate();
			ps.close();
	    	con.cerrar();
	    	
	    }
	    catch(Exception ex){
	    	System.out.println("ERROR EN MetodoIngNumAuto>>GuardarHistorico"+ex);
	    
	    }
		return rs;	
    }
	
	public ResultSet GuardarHistorico2(String Adm,String user,String Ent,Date Fecha,Time Hora,String consec,String noauto){
		/*Guarda los datos de la insercion en la tabla fact_his_num_auto este se utiliza para las facturas generadas */
		ResultSet rs = null;
		PreparedStatement st = null;
		PreparedStatement ps = null;
	    try{
	    	Conexion con=new Conexion();
	    	ps=con.conn.prepareStatement("insert into  fact_hist_num_auto (user,cod_adm,cod_ent,fecha,hora,consec,noauto) values(?,?,?,?,?,?,?)");
	    	ps.setString(1,user);
	    	ps.setString(2,Adm);
	    	ps.setString(3,Ent);
	    	ps.setDate(4,Fecha);
	    	ps.setTime(5,Hora);
	    	ps.setString(6,consec);
	    	ps.setString(7,noauto);
	    	ps.executeUpdate();
			ps.close();
	    	con.cerrar();
	    	
	    }
	    catch(Exception ex){
	    	System.out.println("ERROR EN MetodoIngNumAuto>>GuardarHistorico2"+ex);
	    
	    }
		return rs;	
    }
	
	public ResultSet BuscarEnca(String Adm, String Ent){
		/*Busca las facturas relacionadas a la admision y a la entidad */
        ResultSet rs = null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs = st.executeQuery("SELECT fn.consecutivo, fef.cod_enc_factura , fef.razon_social "+
        						"FROM fact_enc_factura fef, fact_numeradas fn "+
        						"WHERE fef.cod_enc_factura=fn.cod_enc_fact_fk AND fn.estadoFact!=5 AND (fef.estado!=0 OR fef.estado!=5) AND fef.cod_eps='"+Ent+"' AND fef.cod_admision='"+Adm+"' ");
        } catch(Exception ex){
        	System.err.println("Error en MetodoIngNumAuto>>BuscarEnca\n"+ex);
        }
        return rs;
    }
	
	public ResultSet BuscarDatosEnca(String enca){
		/*Busca las facturas relacionadas a la admision y a la entidad */
        ResultSet rs = null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs = st.executeQuery("SELECT fef.cod_admision,fef.cod_eps,fn.consecutivo "+
        					"FROM fact_enc_factura fef, fact_numeradas fn "+
        					"WHERE fef.cod_enc_factura=fn.cod_enc_fact_fk AND fef.cod_enc_factura='"+enca+"' ");
        } catch(Exception ex){
        	System.err.println("Error en MetodoIngNumAuto>>BuscarDatosEnca\n"+ex);
        }
        return rs;
    }
	
}
