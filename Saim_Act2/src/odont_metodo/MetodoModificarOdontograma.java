package odont_metodo;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import adm_logica.Conexion;

public class MetodoModificarOdontograma {
	
	public MetodoModificarOdontograma(){
	}
	
	
//metodo que obtiene todas las columnas de una tabla
	

	
	public Vector <String> obtenerNombresColumnasPorTabla(String nombreTabla){
		Vector <String> vectorResultado = new Vector<String>();
		java.sql.ResultSet rs = null;
		Statement st = null;
		ResultSetMetaData rsmd = null;
		         try {
		 			Conexion con = new Conexion();
		 			st = con.conn.createStatement();
		            rs = st.executeQuery("select * from "+nombreTabla);
		            rsmd=rs.getMetaData();
		        	
		            int numeroColumnas = rsmd.getColumnCount();

		            for (int i = 1; i <= numeroColumnas; i++) {
		            	
		            	vectorResultado.add(rsmd.getColumnName(i)); 
		             /*   System.out.println("columna=" + rsmd.getTableName(i) + "."
		                        + rsmd.getColumnName(i) + " --> "
		                        + rsmd.getColumnTypeName(i));*/
		            }

		        } catch (SQLException e) {
		            // TODO Auto-generated catch block
		            e.printStackTrace();
		        }catch (Exception ex) {
		        	 ex.printStackTrace();
				}
		    
		return vectorResultado;
	}
	
	
	public Vector<String> VectorEliminarDatosArreglos(Vector<String> arecorrer, int [] posvalores){
		//int nuevaposicion =0;
		System.out.print("-----------"+arecorrer.toString() +"\n");
		for (int i=0; i<posvalores.length; i++){
			arecorrer.remove(posvalores[i]);
			if(i < posvalores.length-1){
				for (int j=i; j<posvalores.length; j++){
					posvalores[j]= posvalores[j]-1;
			
				}
			}
			
		
			
		}
		return arecorrer;
		
		
	}
	
	//metodo para buscar paciente por codigo

	public java.sql.ResultSet Buscar_Paciente_Por_Codigo(
			String id_codigo_Paciente) {
		java.sql.ResultSet rs = null;
		Statement st = null;
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("SELECT "
					+ "apac.nombre,"
					+ "apac.primer_apellido,"
					+ "apac.segundo_apellido,"
					+
					// "apac.pac_codigo_paciente," +
					"(YEAR(CURDATE())-YEAR(apac.fecha_nacimiento))- (RIGHT(CURDATE(),5)<RIGHT(apac.fecha_nacimiento,5)) AS 'EDAD',"
					+ "ent.nombre_entidad,"
					+ "apac.genero  "+
					// ",aad.adm_numero_ingreso," +
					// "aad.fecha_registro," +
					// "aad.hora_registro," +
					// "aad.estado "+
					" FROM "
					+ "adm_paciente apac,"
					+ "adm_convenio con,"
					+ "adm_entidad ent"
					+
					// "," +
					// " adm_admisiones aad "+
					" WHERE " + "apac.pac_codigo_paciente="
					+ id_codigo_Paciente + " "
					+
					// "AND apac.numero_documento='"+NumeroDocumento+"' "+
					"AND apac.conv_numero_contrato_fk = con.conv_numero_contrato "
					+ "AND con.ent_nit_contratante_fk = ent.ent_nit"
			// +" AND apac.pac_codigo_paciente=aad.pac_codigo_paciente_fk"+
			// " ORDER BY aad.adm_numero_ingreso DESC "+
			// "LIMIT 1"
			);
		} catch (Exception ex) {
			System.out.println("Error en MetodoModificarOdontograma>>BuscarAdmisiones "
					+ ex);
		}
		return rs;
	}
	//insertar informe modificado
	public boolean insertarInformeMod(
			String fecha,
			String hora, 
			String cod_usuario, 
			String cod_paciente,
			String idinforme
			) {

		PreparedStatement psc = null;
		try {
			Conexion con = new Conexion();
			psc = con.conn.prepareStatement(
				   "INSERT INTO odont_informes_modificados"+
		           "(codusuario_fk," +
		           "codpaciente_fk," +
		           "fecha_modificacion," +
		           "hora_modificacion," +
		           "idinforme_fk" +
		           ")" +
		           " VALUES(?,?,?,?,?)");
			psc.setString(1, cod_usuario);
			psc.setString(2, cod_paciente);
			psc.setString(3, fecha);
			psc.setString(4, hora);
			psc.setString(5, idinforme);
			psc.executeUpdate();
			psc.close();
			con.cerrar();
			return true;
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			System.out.println("Error en MetodoModificarOdontograma>>insertarInformeMod "
					+ ex);
			return false;
		}
	}
	
	
	//metodo para buscar antecedentes medicos

	public Vector<String> buscar_tipo_antecedente(
			String cod_tipo_antecedente, 
			String idinforme) {
		java.sql.ResultSet rs = null;
		Statement st = null;
		Vector <String> miVector = null;
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("SELECT "
					+ "cod_tipo_antecedente_fk,"
					+ " tiene,"
					+ " observaciones"
					+ 
					" FROM "
					+ "odont_antecendentes_odont"
					+
					// "," +
					// " adm_admisiones aad "+
					" WHERE " + "cod_tipo_antecedente_fk='"
					+ cod_tipo_antecedente + "' AND "
					+ "idinforme_fk='"+
					idinforme + "';"
					
			);
			
			rs.next();
			if (rs != null) {
			
				
				miVector = new Vector<String>();
				miVector.add(rs.getString(1));
				miVector.add(rs.getString(2));
				miVector.add(rs.getString(3));
			}
			
		} catch (Exception ex) {
			System.out.println("Error en MetodoModificarOdontograma>> buscar_tipo_antecedente "
					+ ex);
		}
		return miVector;
	}
	
	
	
	
	//metodo para buscar antecedentes medicos

	public Vector<String> buscar_examen_clinico(
			String cod_examen_clinico, 
			String idinforme) {
		java.sql.ResultSet rs = null;
		Statement st = null;
		Vector <String> miVector = null;
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("SELECT "
					+ "cod_tipo_examen_clinico_fk,"
					+ " tiene,"
					+ " observaciones"
					+" FROM "
					+ "odont_examen_clinico"
					+" WHERE cod_tipo_examen_clinico_fk='"
					+ cod_examen_clinico + "' AND "
					+ "idinforme_fk='"+
					idinforme + "';"
					
			);
			
			rs.next();
			if (rs != null) {
			
				
				miVector = new Vector<String>();
				miVector.add(rs.getString(1));
				miVector.add(rs.getString(2));
				miVector.add(rs.getString(3));
			}
			
		} catch (Exception ex) {
			System.out.println("Error en MetodoModificarOdontograma>> buscar_examen_clinico "
					+ ex);
		}
		return miVector;
	}
	

	
	//select que trae observaciones motivo consulta, observaciones antecedentes medicos, observaciones examen clinico

	public Vector<String> buscar_informe_general(String idinforme) {
		java.sql.ResultSet rs = null;
		Statement st = null;
		Vector <String> miVector = null;
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("SELECT obs_motivo_consulta, obs_ant_medicos, " +
					"obs_examen_clinico, evolucion FROM odont_informe_general WHERE idinforme_fk='"
					+idinforme + "'");
			
			rs.next();
			if (rs != null) {
			
				
				miVector = new Vector<String>();
				miVector.add(rs.getString(1));
				miVector.add(rs.getString(2));
				miVector.add(rs.getString(3));
				miVector.add(rs.getString(4));
			}
			
		} catch (Exception ex) {
			System.out.println("Error en MetodoModificarOdontograma>> buscar_informe_general "
					+ ex);
		}
		return miVector;
	}

	//select que trae la imagen del odontograma de la BD
 
	public String buscar_img_odontograma(String idInforme) {
		java.sql.ResultSet rs = null;
		Statement st = null;
		BufferedImage  imgOdont = null;
		String cadimagen64 = null;
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("SELECT "
					+ "img_odontograma"
					+ 
					" FROM "
					+ "odont_informe_general"
					+
					// "," +
					// " adm_admisiones aad "+
					" WHERE " + "idinforme_fk='"
					+idInforme + "';"
					
			);
			if (rs.next())
			{
				Blob blob = rs.getBlob("img_odontograma");
				byte[] data = blob.getBytes(1, (int)blob.length());
				BufferedImage img = null;
				
					img = ImageIO.read(new ByteArrayInputStream(data));
				    imgOdont=img;
				    String imgstr;
			        imgstr = encodeToString(imgOdont, "png");
			        cadimagen64 = imgstr;
				   // System.out.println("/n/ncadena img metodo "+ cadimagen64);
			}
			} catch (Exception ex) {
				System.out.println("Error en MetodoModificarOdontograma>>  buscar_img_odontograma2 "
						+ ex);
			
			}
		return cadimagen64;
	}
//metodo que toma una imagen y la convierte en base 64
	public static  String encodeToString(BufferedImage image, String type) {
        String imageString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            ImageIO.write(image, type, bos);
            byte[] imageBytes = bos.toByteArray();

            BASE64Encoder encoder = new BASE64Encoder();
            imageString = encoder.encode(imageBytes);

            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println("cadena img metodo64"+ imageString);
        return imageString;
    }
	
	
//metodo que modifica los datos de la tabla odont_informe_general 

	public boolean actualizarInformeGeneralOdont(
			String IdInforme,
			String [] MotivoConsulta
			) {

		Vector <String> parametros_tabla = obtenerNombresColumnasPorTabla("odont_informe_general");	
		int [] columnasnodeseadas = {0,1,5};
		parametros_tabla = VectorEliminarDatosArreglos(parametros_tabla, columnasnodeseadas);
		if (parametros_tabla.size() > MotivoConsulta.length){
			
			String [] arregloTemporal = new String[parametros_tabla.size()];
			
			for(int i = 0 ; i< MotivoConsulta.length; i++){
				arregloTemporal[i]=MotivoConsulta[i];
			}
			
			for (int i = MotivoConsulta.length; i < parametros_tabla.size() ; i++){
				arregloTemporal[i]="";
			}
			
			MotivoConsulta = arregloTemporal;
		}
		
	for (int i =0; i < parametros_tabla.size() ; i++){
		if(!MotivoConsulta[i].equals("")){
			
			PreparedStatement psc = null;
			try {
				Conexion con = new Conexion();
				psc = con.conn.prepareStatement(
							       "UPDATE odont_informe_general"+
							       " SET "+parametros_tabla.get(i)+"=?"+
							       " WHERE idinforme_fk ='"+ IdInforme+"'");
				psc.setString(1, MotivoConsulta[i]);
				psc.executeUpdate();
				psc.close();
				con.cerrar();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		}
		
	}
			return true;
	}
	
	
//metodo que modifica los datos de la tabla antecedentes odontologicos segun el 
//codigo del informe y el idcodantecedente

	
	public boolean modificarAntecedentesOdont(
			String IdInforme,
			String codAntecedente,
			String [] antecedentes
			) {
	  Vector <String> parametros_tabla = obtenerNombresColumnasPorTabla("odont_antecendentes_odont");	
		int [] columnasnodeseadas = {0,1, 4};
		parametros_tabla = VectorEliminarDatosArreglos(parametros_tabla, columnasnodeseadas);
		if (parametros_tabla.size() > antecedentes.length){
			
			String [] arregloTemporal = new String[parametros_tabla.size()];
			
			for(int i = 0 ; i< antecedentes.length; i++){
				arregloTemporal[i]=antecedentes[i];
			}
			
			for (int i = antecedentes.length; i < parametros_tabla.size() ; i++){
				arregloTemporal[i]="";
			}
			
			antecedentes = arregloTemporal;
		}
		
	for (int i =0; i < parametros_tabla.size() ; i++){
		
		if(!antecedentes[i].equals("")){
			PreparedStatement psc = null;
			try {
				Conexion con = new Conexion();
				psc = con.conn.prepareStatement(
					       "UPDATE odont_antecendentes_odont"+
					       " SET "+parametros_tabla.get(i)+"=?"+
					       " WHERE cod_tipo_antecedente_fk='"
							+ codAntecedente + "' AND "
							+ "idinforme_fk='"+
							IdInforme + "'");
				psc.setObject(1, antecedentes[i]);
				psc.executeUpdate();
				psc.close();
				con.cerrar();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		}
		
	}
			return true;
}
	
	
	
	//metodo que modifica los datos de la tabla odont_examen_clinico  segun el 
	//codigo del informe y el idcodantecedente
	

	public boolean modificarExamClinico(
			String IdInforme,
			String cod_examen_clinico,
			String [] ExamClinico
			) {

		Vector <String> parametros_tabla = obtenerNombresColumnasPorTabla("odont_examen_clinico");	
		int [] columnasnodeseadas = {0, 1, 4};
		parametros_tabla = VectorEliminarDatosArreglos(parametros_tabla, columnasnodeseadas);
		if (parametros_tabla.size() > ExamClinico.length){
			
			String [] arregloTemporal = new String[parametros_tabla.size()];
			
			for(int i = 0 ; i< ExamClinico.length; i++){
				arregloTemporal[i]=ExamClinico[i];
			}
			
			for (int i = ExamClinico.length; i < parametros_tabla.size() ; i++){
				arregloTemporal[i]="";
			}
			
			ExamClinico = arregloTemporal;
		}
		
	for (int i =0; i < parametros_tabla.size() ; i++){
		PreparedStatement psc = null;
		if(!ExamClinico[i].equals("")){
			try {
				Conexion con = new Conexion();
				psc = con.conn.prepareStatement(
					       "UPDATE odont_examen_clinico"+
					       " SET "+parametros_tabla.get(i)+"=?"
					       +" WHERE cod_tipo_examen_clinico_fk='"
							+ cod_examen_clinico + "' AND "
							+ "idinforme_fk='"+
							IdInforme + "';");
				psc.setObject(1, ExamClinico[i]);
				psc.executeUpdate();
				psc.close();
				con.cerrar();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		}
		
	}
			return true;
	

 }
	
	//modificar imagen de la BD
	public boolean ModificarImagenOdontograma(String idInforme, String imagenOdontograma){
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] dataBytes;
		try {
			dataBytes = decoder.decodeBuffer(imagenOdontograma);
			PreparedStatement psc = null;
			try {
				Conexion con = new Conexion();
				psc = con.conn.prepareStatement(
						"UPDATE odont_informe_general "+
						"SET "+
						"img_odontograma = ? "+
 						"WHERE idinforme_fk = '"+idInforme+"'");
					
				psc.setBytes(1, dataBytes);
				psc.executeUpdate();
				psc.close();
				con.cerrar();
				return true;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
	}
	

	
	
	
}//FIN MetodoModificarOdontograma
