/**
 * logica: MetodoAutoCompletar tiene las consultas insercion y actualizaciones
 * disponibles para autocompletar los convenios que tiene las institucion.
 */
package adm_logica;

import java.sql.ResultSet;
import java.sql.Statement;

public class MetodoAutoCompletar {
	
	
	public ResultSet listar(String entidad) throws Exception {
		  java.sql.ResultSet r=null;
	        Statement st = null;
	       Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	r=st.executeQuery("SELECT aent.nombre_entidad,aent.ent_nit FROM adm_entidad aent,adm_convenio acv,fact_convenios fcv WHERE fcv.cod_entidad=aent.ent_nit AND fcv.estado=0 AND acv.ent_nit_contratante_fk=aent.ent_nit AND aent.nombre_entidad LIKE '%"+entidad+"%'");
	        return r;
	    }

}
