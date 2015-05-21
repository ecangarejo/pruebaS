package cat_cateterismo;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Vector;

import sun.misc.BASE64Decoder;

import adm_logica.Conexion;

public class metodos_modificacion_cateterismo {
	
	// consultar arterias que se bifurcan
		public Vector <String> arteriasbifurcadas(){
			java.sql.ResultSet rs = null;
			Statement st = null;
			Vector<String> mivector= new Vector<String>();
			try {
				Conexion con = new Conexion();
				st = con.conn.createStatement();
				rs = st.executeQuery(
						"SELECT DISTINCT " +
						"art.abreviacion "+
						" FROM "+
						"cat_rama_bifurcada bif,"+
						"cat_arteria art "+
						"WHERE "+
						"bif.id_arteria_fk = art.id_arteria"
						);
				while(rs.next()) {
					
					mivector.addElement(rs.getString(1));
				}
			} catch (Exception ex) {
				ex.getMessage();
			}
			
			return mivector;
		}
		
		public int idlocalizacionlesionarteria(String nombre_localizacion){
			java.sql.ResultSet rs = null;
			Statement st = null;
			int valor = 1;
			try {
				Conexion con = new Conexion();
				st = con.conn.createStatement();
				rs = st.executeQuery(
						
						"SELECT les.id_localizacion"+
						" FROM " +
						"cat_localizacion_lesion les"+
						" WHERE "+
						"les.nombre_localizacion ='"+ nombre_localizacion+"'"
						);
				rs.next();
				if (rs.getString(1) != null) {
				   	valor = Integer.parseInt(rs.getString(1));
				}
				
			} catch (Exception ex) {
				ex.getMessage();
			}
			return valor;
		}
		
		public Vector <String> tipolesionesArteriaActual(String abreviacionArteriaActual){
			java.sql.ResultSet rs = null;
			Statement st = null;
			Vector<String> mivector= new Vector<String>();
			try {
				Conexion con = new Conexion();
				st = con.conn.createStatement();
				rs = st.executeQuery(
			  			 "SELECT les.nombre_localizacion "+
				         "FROM "+
				         "cat_arteria cat,"+
				         "cat_localizacion_lesion les,"+
				         "cat_segmentos_arterias seg "+
				         "WHERE "+
				         "cat.abreviacion = '"+abreviacionArteriaActual+"'"+
				         "AND seg.id_arteria_fk =cat.id_arteria "+
				         "AND seg.id_localizacion_fk=  les.id_localizacion"
						);
				while(rs.next()) {
					
					mivector.addElement(rs.getString(1));
				}
			} catch (Exception ex) {
				ex.getMessage();
			}
			return mivector;
		}
		
		public Vector <String> tipolesionesArteriasGeneral(){
			java.sql.ResultSet rs = null;
			Statement st = null;
			Vector<String> mivector= new Vector<String>();
			String cadena = "";
			int numeroArterias = numeroArteriasRegistradas();
			for (int i = 0 ; i < numeroArterias; i++ ){
				cadena ="";
				try {
					Conexion con = new Conexion();
					st = con.conn.createStatement();
					rs = st.executeQuery(
							"SELECT les.nombre_localizacion "+
					        " FROM "+ 
					        " cat_arteria cat, "+
					        " cat_localizacion_lesion les, "+
					        " cat_segmentos_arterias seg "+
					        " WHERE "+
					        " cat.id_arteria  = '" +(i+1)+"'"+
					        " AND  seg.id_arteria_fk =cat.id_arteria "+
					        " AND seg.id_localizacion_fk=  les.id_localizacion"
							);
				
					
				
					while(rs.next()) {
						
						cadena +=rs.getString(1)+"-";
					}
					cadena = cadena.substring(0, cadena.length()-1);
					mivector.add(cadena);
				
				} catch (Exception ex) {
					ex.getMessage();
				}
				
			}
		
			return mivector;
		}
		
		
		
		public int numeroArteriasRegistradas(){
			java.sql.ResultSet rs = null;
			Statement st = null;
			int valor = 1;
			try {
				Conexion con = new Conexion();
				st = con.conn.createStatement();
				rs = st.executeQuery("SELECT COUNT(id_arteria) FROM cat_arteria");
				rs.next();
				if (rs.getString(1) != null) {
					valor = Integer.parseInt(rs.getString(1));
				}
			} catch (Exception ex) {
				ex.getMessage();
			}

			return valor;
		}

	public Vector<String> buscarinfoEncabezadoInforme(String codInfome) {
		//nomenclatura [nombra anesteciologo, id tipo indicacion,idestudio realizado]
		
		java.sql.ResultSet rs = null;
		Statement st = null;
		Vector <String> miVector = null;
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery(
					"SELECT "+
					"inf.id_estudio_realizado_hemodinamia_fk,"+
					"inf.id_tipo_indicacion_diagnostico_hemodinamia_fk,"+
					"inf.nombre_Anesteciologo "+
					"FROM " +
					"cat_informe_diagnostico_hemodinamia inf "+
					"WHERE "+
					"inf.id_informe_diagnostico_hemodinamia='"+codInfome+"'");
		
			rs.next();
			if (rs != null) {
				miVector = new Vector<String>();
				miVector.add(String.valueOf((Integer.parseInt(rs.getString(1))-1)));
				miVector.add(String.valueOf((Integer.parseInt(rs.getString(2))-1)));
				miVector.add(rs.getString(3));
			}
		
		
		} catch (Exception ex) {
			System.out.println("Error en MetodoEncabezado>>buscarinfoEncabezado "
					+ ex);
		}
		return miVector;
	}
	
	
	public Vector<String> buscarContenidoGeneralHemodinamia(String codInfome) {
		java.sql.ResultSet rs = null;
		Statement st = null;
		Vector <String> miVector = null;
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery(
					"SELECT "+
					"contenido_informe_ventriculografia,"+
					"contenido_informe_aortograma,"+
					"resumen_procedimiento,"+
					"conclusiones,"+
					"recomendaciones,"+
					"contenido_informe_bypass"+
					" FROM " +
					"cat_contenido_informe_hemodinamia contgnral,"+
					"cat_informe_diagnostico_hemodinamia inf"+
					" WHERE "+
					"inf.id_informe_diagnostico_hemodinamia ='"+codInfome+"'"+
					"AND inf.id_informe_diagnostico_hemodinamia = contgnral.id_informe_diagnostico_hemodinamia_fk");
			rs.next();
			if (rs != null) {
				miVector = new Vector<String>();
				miVector.add(rs.getString(1));
				miVector.add(rs.getString(2));
				miVector.add(rs.getString(3));
				miVector.add(rs.getString(4));
				miVector.add(rs.getString(5));
				miVector.add(rs.getString(6));
			}
		} catch (Exception ex) {
			System.out.println("Error en MetodoContenidoGralHemodinamia>>buscarContenidoGeneralHemodinamia "
					+ ex);
		}
		return miVector;
	}
	
	public Vector<String> buscarContenidoAnatomiaCoronaria(String codInfome) {
		java.sql.ResultSet rs = null;
		Statement st = null;
		Vector <String> miVector = null;
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery(
					"SELECT "+
					"descripcion_arteria_TCI,"+
					"descripcion_arteria_ADA,"+
					"descripcion_arteria_AC,"+
					"descripcion_arteria_RI,"+
					"descripcion_arteria_ACD,"+
					"descripcion_arteria_ADP,"+
					"descripcion_arteria_TPL,"+
					"descripcion_arteria_D1,"+
					"descripcion_arteria_D2,"+
					"descripcion_arteria_O1,"+
					"descripcion_arteria_O2"+
					" FROM " +
					"cat_contenido_informe_anatomia_coronaria infcor,"+
					"cat_informe_diagnostico_hemodinamia inf"+
                    " WHERE "+
					"inf.id_informe_diagnostico_hemodinamia ='"+codInfome+"'"+
					"AND inf.id_informe_diagnostico_hemodinamia = infcor.id_informe_diagnostico_hemodinamia_fk");
		
			rs.next();
			if (rs.getString(1) != null) {
				miVector = new Vector<String>();
				miVector.add(rs.getString(1));
				miVector.add(rs.getString(2));
				miVector.add(rs.getString(3));
				miVector.add(rs.getString(4));
				miVector.add(rs.getString(5));
				miVector.add(rs.getString(6));
				miVector.add(rs.getString(7));
				miVector.add(rs.getString(8));
				miVector.add(rs.getString(9));
				miVector.add(rs.getString(10));
				miVector.add(rs.getString(11));
			}
		
		} catch (Exception ex) {
			System.out.println("Error en MetodoContenidoAnatomiaCoronaria>>buscarContenidoAnatomiaCoronaria "
					+ ex);
		}
		return miVector;
	}
	
	public Vector <String> buscarDataInformeVentriculografia(String codInfome) {
		java.sql.ResultSet rs = null;
		Statement st = null;
		Vector <String> miVector = null;
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery(
					"SELECT "+
			       	"vent.ventriculo_izquierdo_dilatado,"+
					"vent.id_tipo_contractibilidad_fk,"+
					"vent.id_gravedad_contractibilidad_fk,"+
					"vent.id_caracteristica_contractibilidad_fk,"+
					"vent.id_lugar_contractibilidad_fk,"+
					"vent.id_insuficiencia_mitral_fk,"+
					"vent.id_aspecto_valvula_mitral_fk,"+
					"vent.id_porcentaje_fevi_fk"+
					" FROM " +
					"cat_informe_diagnostico_hemodinamia inf,"+
					"cat_informe_ventriculografia vent"+
    				" WHERE "+
					" inf.id_informe_diagnostico_hemodinamia ='"+codInfome+"'"+
					" AND inf.id_informe_diagnostico_hemodinamia = vent.id_informe_diagnostico_hemodinamia_fk");
			if(rs.next()){
				miVector = new Vector<String>();
				miVector.add(rs.getString(1));
				miVector.add(String.valueOf(Integer.parseInt(rs.getString(2))-1));
				miVector.add(String.valueOf(Integer.parseInt(rs.getString(3))-1));
				miVector.add(String.valueOf(Integer.parseInt(rs.getString(4))-1));
				miVector.add(String.valueOf(Integer.parseInt(rs.getString(5))-1));
				miVector.add(String.valueOf(Integer.parseInt(rs.getString(6))-1));
				miVector.add(String.valueOf(Integer.parseInt(rs.getString(7))-1));
				miVector.add(String.valueOf(Integer.parseInt(rs.getString(8))-1));
			}
		} catch (Exception ex) {
			System.out.println("Error en MetodobuscarDataInformeVentriculografia>>buscarDataInformeVentriculografia "
					+ ex);
		}
		return miVector;
	}
	
	public  Vector <String>  buscarDataInformeAortograma(String codInfome) {
		java.sql.ResultSet rs = null;
		Statement st = null;
		Vector <String> miVector = null;
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery(
					"SELECT "+
			    	"aor.raiz_aortica_dilatada,"+							
					"aor.medicion_raiz_aortica,"+
					"aor.aorta_ascendente,"+
					"aor.medicion_aorta_ascendente,"+
					"aor.id_clase_valvula_aortica_fk,"+
					"aor.id_aspecto_valvula_aortica_fk,"+
					"aor.id_insuficiencia_aortica_fk,"+
					"aor.id_estenosis_aortica_fk,"+
					"aor.gradiente_pico_pico "+
					" FROM "+
					"cat_informe_diagnostico_hemodinamia inf,"+
					"cat_informe_aortograma aor"+
					" WHERE "+
					"inf.id_informe_diagnostico_hemodinamia ='"+codInfome+"'"+
					" AND inf.id_informe_diagnostico_hemodinamia = aor.id_informe_diagnostico_hemodinamia_fk");
		
			
			if (rs.next()) {
				miVector = new Vector<String>();
				miVector.add(rs.getString(1)); //raiz aortica
				miVector.add(rs.getString(2)); //raiz aortica medicion
				miVector.add(rs.getString(3));//arteria ascendente
				miVector.add(rs.getString(4));//medicion arteria ascendente
				miVector.add(String.valueOf(Integer.parseInt(rs.getString(5))-1));//clase valvula aortica
				miVector.add(String.valueOf(Integer.parseInt(rs.getString(6))-1));//aspecto valvula aortica
				miVector.add(String.valueOf(Integer.parseInt(rs.getString(7))-1));//insufciencia valvula aortica
				miVector.add(String.valueOf(Integer.parseInt(rs.getString(8))-1));// estenosis aortica
				miVector.add(rs.getString(9));//medicion gradiente pico pico
			
			}
		
		} catch (Exception ex) {
			System.out.println("Error en MetodobuscarDataInformeAortograma>>buscarDataInformeAortograma "
					+ ex);
		}
		return miVector;
	}
	
	
	public Vector <String> buscarDataDominanciaAnatomiaCoronaria(String codInfome) {
		java.sql.ResultSet rs = null;
		Statement st = null;
		Vector <String> miVector = null;
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery(
					"SELECT "+
					"infana.dominancia"+
					" FROM "+
					"cat_informe_anatomia_coronaria infana,"+
					"cat_informe_diagnostico_hemodinamia inf"+
					" WHERE "+
					" inf.id_informe_diagnostico_hemodinamia = '"+codInfome+"'"+
					" AND inf.id_informe_diagnostico_hemodinamia = infana.id_informe_diagnostico_hemodinamia_fk");
		
			
			if (rs.next()) {
				miVector = new Vector<String>();
				miVector.add(rs.getString(1)); //id
			}
		
		} catch (Exception ex) {
			System.out.println("Error en MetodobuscarDataDominanciaAnatomiaCoronaria>>buscarDataDominanciaAnatomiaCoronaria "
					+ ex);
		}
		return miVector;
	}
	
	
	
/*	SELECT seg.id_localizacion_fk 
	FROM 
	cat_arteria cat,
	cat_localizacion_lesion les,
	cat_segmentos_arterias seg 
	WHERE 
	  cat.abreviacion = '_TPL'
	  AND seg.id_arteria_fk =cat.id_arteria 
	  AND seg.id_localizacion_fk=  les.id_localizacion*/
	
	
	public 	Vector <String> buscarDataArteriasAnatomiaCoronaria(String codInfome) {
		java.sql.ResultSet rs = null;
		Statement st = null;
		Vector <String> miVector = null;
		String abreviaciones_Arterias ="";
		String ids_lesiones_arterias = "";
		String ids_flujo_timi = "";
		String ids_lecho_distal ="";
		
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery(
					"SELECT "+
					"lesart.id_info_lesion_arteria,"+
					"art.abreviacion,"+
					"lesart.id_flujo_timi_fk,"+
					"lesart.id_lecho_distal_fk"+
					" FROM "+
					"cat_informe_anatomia_coronaria infana,"+
					"cat_informe_diagnostico_hemodinamia inf,"+
					"cat_info_lesion_arteria lesart,"+
					"cat_arteria art"+
					" WHERE "+
					"inf.id_informe_diagnostico_hemodinamia ='"+codInfome+"'"+
					" AND inf.id_informe_diagnostico_hemodinamia = infana.id_informe_diagnostico_hemodinamia_fk"+
					" AND infana.id_informe_anatomia_coronaria = lesart.id_informe_anatomia_coronaria_fk"+
					" AND art.id_arteria = lesart.id_arteria_fk"
					);
			while (rs.next()){
				ids_lesiones_arterias += rs.getString(1)+"-";
				abreviaciones_Arterias += rs.getString(2)+"-";
				ids_flujo_timi += rs.getString(3)+"-";
				ids_lecho_distal += rs.getString(4)+"-";
			}
			
		} catch (Exception ex) {
			System.out.println("Error en MetodobuscarDataArteriasAnatomiaCoronaria>>buscarDataArteriasAnatomiaCoronaria "
					+ ex);
		}
		
		if (ids_lesiones_arterias.length()>0){
			
			 ids_lesiones_arterias = ids_lesiones_arterias.substring(0, ids_lesiones_arterias.length()-1);
			 abreviaciones_Arterias = abreviaciones_Arterias.substring(0,abreviaciones_Arterias.length()-1);
			 ids_flujo_timi = ids_flujo_timi.substring(0,ids_flujo_timi.length()-1);
			 ids_lecho_distal =ids_lecho_distal.substring(0,ids_lecho_distal.length()-1);
			 miVector = new Vector<String>();
			 miVector.add(ids_lesiones_arterias);
			 miVector.add(abreviaciones_Arterias);
			 miVector.add(ids_flujo_timi);
			 miVector.add(ids_lecho_distal);
		}
		
		return miVector;
	}
	
	public Vector<String> buscarDataLesionesAnatomiaCoronaria(String codInfome) {
		
		
		java.sql.ResultSet rs = null;
		Statement st = null;
		Vector <String> miVector = null;
		String ids_lesiones_arterias = "";
		String localizaciones = "";
		String ids_porcentajes ="";
		String ids_caracteristicas ="";
		String ids_lesiones_colaterales ="";
		String ids_oclusiones_totales ="";
		String ids_tipo_lesiones="";
		String ids_ramas_bifurcadas = "";
		String ids_medinas = "";
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery(
					   "SELECT "+
					   "car.id_info_lesion_arteria_fk,"+
					   "loles.nombre_localizacion,"+
					   "car.id_porcentaje_fk,"+
					   "car.id_caracteristica_lesion_fk,"+
					   "car.id_lesion_colateral_fk,"+
					   "car.id_lesion_oclusion_total_fk,"+
					   "car.id_tipo_lesion_fk,"+
					   "car.id_rama_bifurcada_fk,"+
					   "car.id_medina_fk"+
					   " FROM "+
					   "cat_informe_anatomia_coronaria infana,"+
					   "cat_informe_diagnostico_hemodinamia inf,"+
					   "cat_info_lesion_arteria lesart,"+
					   "cat_caracterizacion_arteria car,"+
					   "cat_localizacion_lesion loles"+
					   " WHERE "+
					   "inf.id_informe_diagnostico_hemodinamia = '"+codInfome+"' "+
					   "AND inf.id_informe_diagnostico_hemodinamia = infana.id_informe_diagnostico_hemodinamia_fk "+ 
					   "AND infana.id_informe_anatomia_coronaria = lesart.id_informe_anatomia_coronaria_fk "+ 
					   "AND lesart.id_info_lesion_arteria = car.id_info_lesion_arteria_fk "+
					   "AND loles.id_localizacion = car.id_localizacion_fk "+
					   "ORDER BY car.id_info_lesion_arteria_fk, loles.id_localizacion"
);

			String auxcad ="";

			 while (rs.next()){

				 if (rs.isFirst()){
					 auxcad = rs.getString(1);
				 }
				 
				 if (!auxcad.equalsIgnoreCase(rs.getString(1))){
					 ids_lesiones_arterias = ids_lesiones_arterias.substring(0,ids_lesiones_arterias.length()-1);
					 localizaciones = localizaciones.substring(0, localizaciones.length()-1);
					 ids_porcentajes =ids_porcentajes.substring(0, ids_porcentajes.length()-1);
					 ids_caracteristicas =ids_caracteristicas.substring(0,ids_caracteristicas.length()-1);
					 ids_lesiones_colaterales =ids_lesiones_colaterales.substring(0,ids_lesiones_colaterales.length()-1);
					 ids_oclusiones_totales =ids_oclusiones_totales.substring(0,ids_oclusiones_totales.length()-1);
					 ids_tipo_lesiones =ids_tipo_lesiones.substring(0,ids_tipo_lesiones.length()-1);
					 ids_ramas_bifurcadas = ids_ramas_bifurcadas.substring(0,ids_ramas_bifurcadas.length()-1);
					 ids_medinas = ids_medinas.substring(0, ids_medinas.length()-1);
					 ids_lesiones_arterias += "@";
					 localizaciones +="@";
					 ids_porcentajes +="@";
					 ids_caracteristicas +="@";
					 ids_lesiones_colaterales +="@";
					 ids_oclusiones_totales +="@";
					 ids_tipo_lesiones +="@";
					 ids_ramas_bifurcadas += "@";
					 ids_medinas += "@";
					 auxcad = rs.getString(1);
					 
				 }
				
				 ids_lesiones_arterias += rs.getString(1)+"-";
				 localizaciones += String.valueOf(rs.getString(2))+"-";
				 ids_porcentajes +=String.valueOf(Integer.parseInt(rs.getString(3))-1)+"-";
				 ids_caracteristicas +=String.valueOf(Integer.parseInt(rs.getString(4))-1)+"-";
				 ids_lesiones_colaterales +=String.valueOf(Integer.parseInt(rs.getString(5))-1)+"-";
				 ids_oclusiones_totales +=String.valueOf(Integer.parseInt(rs.getString(6))-1)+"-";
				 ids_tipo_lesiones +=String.valueOf(Integer.parseInt(rs.getString(7))-1)+"-";
				 ids_ramas_bifurcadas += rs.getString(8)+"-";
				 ids_medinas += String.valueOf(Integer.parseInt(rs.getString(9))-1)+"-";
				 
				 
				
				 
				 
			 }
			
		} catch (Exception ex) {
			System.out.println("Error en MetodobuscarDataLesionesAnatomiaCoronaria>>buscarDataLesionesAnatomiaCoronaria "
					+ ex);
		}
		
		if(ids_lesiones_arterias.length()>0){
			 ids_lesiones_arterias = ids_lesiones_arterias.substring(0,ids_lesiones_arterias.length()-1);
			 localizaciones = localizaciones.substring(0, localizaciones.length()-1);
			 ids_porcentajes =ids_porcentajes.substring(0, ids_porcentajes.length()-1);
			 ids_caracteristicas =ids_caracteristicas.substring(0,ids_caracteristicas.length()-1);
			 ids_lesiones_colaterales =ids_lesiones_colaterales.substring(0,ids_lesiones_colaterales.length()-1);
			 ids_oclusiones_totales =ids_oclusiones_totales.substring(0,ids_oclusiones_totales.length()-1);
			 ids_tipo_lesiones =ids_tipo_lesiones.substring(0,ids_tipo_lesiones.length()-1);
			 ids_ramas_bifurcadas = ids_ramas_bifurcadas.substring(0,ids_ramas_bifurcadas.length()-1);
			 ids_medinas = ids_medinas.substring(0, ids_medinas.length()-1);
			miVector = new Vector<String>();
			miVector.add(ids_lesiones_arterias);
			miVector.add(localizaciones);
			miVector.add(ids_porcentajes);
			miVector.add(ids_caracteristicas);
			miVector.add(ids_lesiones_colaterales);
			miVector.add(ids_oclusiones_totales);
			miVector.add(ids_tipo_lesiones);
			miVector.add(ids_ramas_bifurcadas);
			miVector.add(ids_medinas);
			
		}
		return miVector;
	}
	
	/**************************************/
	/***Metodo de inserccion de Imagen**/
	/*****Anatomia Coronaria **********/
	/**************************************/
	public boolean insertarImagenAnatomiaCoronaria(String idInforme, String imagenAnatomiaCoronaria){
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] dataBytes;
		try {
			dataBytes = decoder.decodeBuffer(imagenAnatomiaCoronaria);
			PreparedStatement psc = null;
			try {
				Conexion con = new Conexion();
				psc = con.conn.prepareStatement("UPDATE cat_contenido_informe_hemodinamia SET imagen_anatomia_coronaria = ? WHERE id_informe_diagnostico_hemodinamia_fk ='"+idInforme+"'");
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
	
	/**************************************/
	/***Metodo de inserccion de Imagen**/
	/*****Ventriculografia **********/
	/**************************************/
	public boolean insertarImagenVentriculografia(String idInforme, String imagenAnatomiaCoronaria){
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] dataBytes;
		try {
			dataBytes = decoder.decodeBuffer(imagenAnatomiaCoronaria);
			PreparedStatement psc = null;
			try {
				Conexion con = new Conexion();
				psc = con.conn.prepareStatement("UPDATE cat_contenido_informe_hemodinamia SET imagen_ventriculografia = ? WHERE id_informe_diagnostico_hemodinamia_fk ='"+idInforme+"'");
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
	
	/**************************************/
	/** Metodos de inserccion de Informe **/
	/********** Ventriculografia **********/
	/**************************************/
	
	public boolean insertarInformeVentriculografia(
			String ventriculo_izquierdo_dilatado,
		    String id_tipo_contractibilidad,
		    String id_gravedad_contractibilidad,
		    String id_caracteristica_contractibilidad,
		    String id_lugar_contractibilidad,
		    String id_porcentaje_fevi,
		    String id_insuficiencia_mitral,
		    String id_aspecto_valvula_mitral,
		    String id_informe_diagnostico_hemodinamia 
			){
	
		try{
			//System.out.print("\n entra: "+cadenaimagenventriculografia);
			
		/*	BASE64Decoder decoder = new BASE64Decoder();
			
			byte[] dataBytes= decoder.decodeBuffer(cadenaimagenventriculografia);
		   
			for (int i =0; i< dataBytes.length; i++){
				
				System.out.print(dataBytes[i]);
			}*/
			
			PreparedStatement psc = null;
			    try {
				Conexion con = new Conexion();
				// System.out.print(" id "+cod+"caracteristica "+id_caracteristica_lesion+" flujo "+id_flujo_timi+" les col "+id_lesion_colateral+" les ocl "+id_lesion_oclusion_total+"  tipo lesion "
				// +id_tipo_lesion +"\n");
				
				psc = con.conn.prepareStatement(
						"INSERT INTO cat_informe_ventriculografia (" +
						"ventriculo_izquierdo_dilatado," +
						"id_tipo_contractibilidad_fk," +
						"id_gravedad_contractibilidad_fk,"+
						"id_caracteristica_contractibilidad_fk," +
						"id_lugar_contractibilidad_fk," +
						"id_porcentaje_fevi_fk," +
						"id_insuficiencia_mitral_fk," +
						"id_aspecto_valvula_mitral_fk," +
						"id_informe_diagnostico_hemodinamia_fk" +
						")"+
						"VALUES (?,?,?,?,?,?,?,?,?)");
				psc.setString(1, ventriculo_izquierdo_dilatado);
				psc.setString(2, id_tipo_contractibilidad);
				psc.setString(3, id_gravedad_contractibilidad);
				psc.setString(4, id_caracteristica_contractibilidad);
				psc.setString(5, id_lugar_contractibilidad);
				psc.setString(6, id_porcentaje_fevi);
				psc.setString(7, id_insuficiencia_mitral);
				psc.setString(8, id_aspecto_valvula_mitral);
				psc.setString(9, id_informe_diagnostico_hemodinamia);
				//psc.setBytes(10, dataBytes);
				psc.executeUpdate();
				psc.close();
				con.cerrar();
				return true;
				}
			    
				catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
		
		
		
		}
		catch(Exception e){
		System.out.println(e.getMessage());	
		return false;
		}
		
		
	   
		

	
	}
	
	
	/**************************************/
	/** Metodos de inserccion de Informe **/
	/************** Aortograma*************/
	/**************************************/
	public boolean insertarInformeAortograma(
			        String raiz_aortica_dilatada, 
			        String medicion_raiz_aortica,
			        String aorta_ascendente,
			        String aorta_ascendente_medicion,
	    		    String id_clase_valvula_aortica_fk,
	    		    String id_aspecto_valvula_aortica_fk,
	    		    String id_insuficiencia_aortica_fk,
	    		    String id_estenosis_aortica_fk,
	    		    String gradiente_pico_pico,
	    		    String id_informe_diagnostico_hemodinamia_fk
			){
		try {
		    	PreparedStatement psc = null;
				Conexion con = new Conexion();
    			psc = con.conn.prepareStatement(
    			"INSERT INTO cat_informe_aortograma("+
    			"raiz_aortica_dilatada," +
    		    "medicion_raiz_aortica," +
    			"aorta_ascendente,"+
    		    "medicion_aorta_ascendente,"+
    		    "id_clase_valvula_aortica_fk," +
    		    "id_aspecto_valvula_aortica_fk," +
    		    "id_insuficiencia_aortica_fk," +
    		    "id_estenosis_aortica_fk," +
    		    "gradiente_pico_pico," +
    		    "id_informe_diagnostico_hemodinamia_fk)" +
    		    "VALUES (?,?,?,?,?,?,?,?,?,?)");
    			psc.setString(1,raiz_aortica_dilatada);
				psc.setString(2,medicion_raiz_aortica );
				psc.setString(3,aorta_ascendente);
				psc.setString(4,aorta_ascendente_medicion);
				psc.setString(5,id_clase_valvula_aortica_fk);
				psc.setString(6, id_aspecto_valvula_aortica_fk);
				psc.setString(7, id_insuficiencia_aortica_fk);
				psc.setString(8, id_estenosis_aortica_fk);
				psc.setString(9, gradiente_pico_pico);
				psc.setString(10, id_informe_diagnostico_hemodinamia_fk);
				psc.executeUpdate();
				psc.close();
				con.cerrar();
		return true;
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	
	
	public boolean actualizar_informe_diagnostico_hemodinamia(String idInforme, String nombre_anesteciologo,String indicacion_diagnostico, String estudio_realizado){
			PreparedStatement psc = null;
			try {
				Conexion con = new Conexion();
				psc = con.conn.prepareStatement(
						"UPDATE " +
						"cat_informe_diagnostico_hemodinamia" +
						" SET " +
						"nombre_Anesteciologo = ?," +
						"id_tipo_indicacion_diagnostico_hemodinamia_fk = ?," +
						"id_estudio_realizado_hemodinamia_fk = ?" +
						" WHERE id_informe_diagnostico_hemodinamia ='"+idInforme+"'");
				psc.setString(1, nombre_anesteciologo);
				psc.setString(2, indicacion_diagnostico);
				psc.setString(3, estudio_realizado);
				psc.executeUpdate();
				psc.close();
				con.cerrar();
				return true;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		
	}
	
	public boolean actualizar_contenido_informe_hemodinamia(String idInforme, String contenido_informe_ventriculografia,String contenido_informe_aortograma, String resumen_procedimiento,String conclusiones, String recomendaciones, String contenido_informe_bypass ){
		PreparedStatement psc = null;
		try {
			Conexion con = new Conexion();
			psc = con.conn.prepareStatement(
					"UPDATE " +
					"cat_contenido_informe_hemodinamia" +
					" SET " +
					"contenido_informe_ventriculografia = ?,"+
					"contenido_informe_aortograma= ?,"+
					"resumen_procedimiento = ?,"+
					"conclusiones = ?,"+
					"recomendaciones = ?,"+
					"contenido_informe_bypass = ?"+
					" WHERE id_informe_diagnostico_hemodinamia_fk ='"+idInforme+"'");
			psc.setString(1, contenido_informe_ventriculografia);
			psc.setString(2, contenido_informe_aortograma);
			psc.setString(3, resumen_procedimiento);
			psc.setString(4, conclusiones);
			psc.setString(5, recomendaciones);
			psc.setString(6, contenido_informe_bypass);
			psc.executeUpdate();
			psc.close();
			con.cerrar();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	
}
	
	public boolean actualizar_contenido_informe_Anatomia_Coronaria(
			String idInforme, 
			String descripcion_arteria_TCI,
			String descripcion_arteria_ADA, 
			String descripcion_arteria_AC,
			String descripcion_arteria_RI, 
			String descripcion_arteria_ACD, 
			String descripcion_arteria_ADP,
			String descripcion_arteria_TPL,
			String descripcion_arteria_D1,
			String descripcion_arteria_D2,
			String descripcion_arteria_O1,
			String descripcion_arteria_O2
			
			){
		PreparedStatement psc = null;
		try {
			Conexion con = new Conexion();
			psc = con.conn.prepareStatement(
					"UPDATE cat_contenido_informe_anatomia_coronaria"+
					" SET "+
					"descripcion_arteria_TCI = ?,"+
					"descripcion_arteria_ADA = ?,"+
					"descripcion_arteria_AC = ?,"+
					"descripcion_arteria_RI = ?,"+
					"descripcion_arteria_ACD = ?,"+
					"descripcion_arteria_ADP = ?,"+
					"descripcion_arteria_TPL = ?,"+
					"descripcion_arteria_D1 = ?,"+
					"descripcion_arteria_D2 = ?,"+
					"descripcion_arteria_O1 = ?,"+
					"descripcion_arteria_O2 = ?"+
					" WHERE id_informe_diagnostico_hemodinamia_fk ='"+idInforme+"'");
			psc.setString(1, descripcion_arteria_TCI);
			psc.setString(2, descripcion_arteria_ADA);
			psc.setString(3, descripcion_arteria_AC);
			psc.setString(4, descripcion_arteria_RI);
			psc.setString(5, descripcion_arteria_ACD);
			psc.setString(6, descripcion_arteria_ADP);
			psc.setString(7, descripcion_arteria_TPL);
			psc.setString(8, descripcion_arteria_D1);
			psc.setString(9, descripcion_arteria_D2);
			psc.setString(10, descripcion_arteria_O1);
			psc.setString(11, descripcion_arteria_O2);
			psc.executeUpdate();
			psc.close();
			con.cerrar();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	
}

	public boolean actualizar_informe_Ventriculografia(
			String idInforme, 
			String ventriculo_izquierdo_dilatado ,
			String id_tipo_contractibilidad_fk ,
			String id_gravedad_contractibilidad_fk,
			String id_caracteristica_contractibilidad_fk,
			String id_lugar_contractibilidad_fk ,
			String id_insuficiencia_mitral_fk ,
			String id_aspecto_valvula_mitral_fk ,
			String id_porcentaje_fevi_fk 
			
			){
		PreparedStatement psc = null;
		try {
			Conexion con = new Conexion();
			
			psc = con.conn.prepareStatement(
					"UPDATE cat_informe_ventriculografia"+
					" SET "+
					"ventriculo_izquierdo_dilatado = ?,"+
					"id_tipo_contractibilidad_fk = ?,"+
					"id_gravedad_contractibilidad_fk = ?,"+
					"id_caracteristica_contractibilidad_fk = ?,"+
					"id_lugar_contractibilidad_fk = ?,"+
					"id_insuficiencia_mitral_fk = ?,"+
					"id_aspecto_valvula_mitral_fk = ?,"+
					"id_porcentaje_fevi_fk = ?"+
    				" WHERE id_informe_diagnostico_hemodinamia_fk ='"+idInforme+"'");
			
			psc.setString(1, ventriculo_izquierdo_dilatado);
			psc.setString(2, id_tipo_contractibilidad_fk);
			psc.setString(3, id_gravedad_contractibilidad_fk);
			psc.setString(4, id_caracteristica_contractibilidad_fk);
			psc.setString(5, id_lugar_contractibilidad_fk);
			psc.setString(6, id_insuficiencia_mitral_fk);
			psc.setString(7, id_aspecto_valvula_mitral_fk);
			psc.setString(8, id_porcentaje_fevi_fk);
			psc.executeUpdate();
			psc.close();
			con.cerrar();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	
}
	
	
	

	
	public boolean actualizar_informe_Aortograma(
			String idInforme, 
			String raiz_aortica_dilatada ,
			String medicion_raiz_aortica ,
			String aorta_ascendente,
			String medicion_aorta_ascendente,
			String id_clase_valvula_aortica_fk ,
			String id_aspecto_valvula_aortica_fk ,
			String id_insuficiencia_aortica_fk ,
			String id_estenosis_aortica_fk,
			String gradiente_pico_pico
			
			){
		PreparedStatement psc = null;
		try {
			Conexion con = new Conexion();
			
			psc = con.conn.prepareStatement(
					"UPDATE cat_informe_aortograma"+
					" SET "+
					"raiz_aortica_dilatada = ?,"+
					"medicion_raiz_aortica = ?,"+
					"aorta_ascendente = ?,"+
					"medicion_aorta_ascendente = ?,"+
					"id_clase_valvula_aortica_fk = ?,"+
					"id_aspecto_valvula_aortica_fk = ?,"+
					"id_insuficiencia_aortica_fk = ?,"+
					"id_estenosis_aortica_fk = ?,"+
					"gradiente_pico_pico = ?"+
    				" WHERE id_informe_diagnostico_hemodinamia_fk ='"+idInforme+"'");
			
			psc.setString(1, raiz_aortica_dilatada);
			psc.setString(2, medicion_raiz_aortica);
			psc.setString(3, aorta_ascendente);
			psc.setString(4, medicion_aorta_ascendente);
			psc.setString(5, id_clase_valvula_aortica_fk);
			psc.setString(6, id_aspecto_valvula_aortica_fk);
			psc.setString(7, id_insuficiencia_aortica_fk);
			psc.setString(8, id_estenosis_aortica_fk);
			psc.setString(9, gradiente_pico_pico);
			psc.executeUpdate();
			psc.close();
			con.cerrar();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	
}
	
	
	public boolean actualizarDataDominanciaAnatomiaCoronaria(String idInforme, String dominancia){
		PreparedStatement psc = null;
		try {
			Conexion con = new Conexion();
			
			psc = con.conn.prepareStatement(
					"UPDATE cat_informe_anatomia_coronaria "+
					"SET dominancia = ? "+
					" WHERE id_informe_diagnostico_hemodinamia_fk ='"+idInforme+"'");
			
			psc.setString(1, dominancia);
			psc.executeUpdate();
			psc.close();
			con.cerrar();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		
	}
	
	public Vector<String> consultarIdsLesionesporArteria(String abreviacion){
		java.sql.ResultSet rs = null;
		Statement st = null;
		Vector<String> mivector= new Vector<String>();
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery(
					"SELECT seg.id_localizacion_fk "+ 
					"FROM "+
					"cat_arteria cat,"+
					"cat_localizacion_lesion les,"+
					"cat_segmentos_arterias seg "+
					"WHERE "+
					 " cat.abreviacion = '"+abreviacion+"'"+
					 " AND seg.id_arteria_fk =cat.id_arteria "+
					 " AND seg.id_localizacion_fk=  les.id_localizacion"
					);
		
			while(rs.next()) {
				mivector.add(rs.getString(1));
			}
		} catch (Exception ex) {
			ex.getMessage();
		}
		return mivector;
		
		
		
	}
	
	
	public boolean actualizarDatalechoflujoAnatomiaCoronaria(String idarteria, String lechoDistal, String flujoTimi){
		PreparedStatement psc = null;
		try {
			Conexion con = new Conexion();
			
			psc = con.conn.prepareStatement(
					
					"UPDATE cat_info_lesion_arteria "+
					" SET "+
					"id_flujo_timi_fk = ?,"+
					"id_lecho_distal_fk = ?"+
					" WHERE id_info_lesion_arteria ='"+idarteria+"'"
					
			);
			
			
			psc.setString(1, flujoTimi);
			psc.setString(2, lechoDistal);
			psc.executeUpdate();
			psc.close();
			con.cerrar();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		
	}

	
	public boolean actualizarDataCaracterizacionAnatomiaCoronaria(
			String idArteria, 
			String localizacion, 
			String porcentaje, 
			String caracteristica, 
			String lesionColateral, 
			String oclusionTotal, 
			String idTipoLesion,
			String ramaBifurcada, 
			String medina){
		PreparedStatement psc = null;
		try {
			Conexion con = new Conexion();
			
			psc = con.conn.prepareStatement(
					
					"UPDATE cat_caracterizacion_arteria"+
					" SET "+
					"id_porcentaje_fk = ?,"+
					"id_caracteristica_lesion_fk = ?,"+
					"id_lesion_colateral_fk = ?,"+
					"id_lesion_oclusion_total_fk = ?,"+
					"id_tipo_lesion_fk = ?,"+
					"id_rama_bifurcada_fk = ?,"+
					"id_medina_fk = ?"+
					" WHERE id_info_lesion_arteria_fk ='"+idArteria+"'"+
					" AND id_localizacion_fk ='"+localizacion+"'"

			);
			
			psc.setString(1, porcentaje);
			psc.setString(2, caracteristica);
			psc.setString(3, lesionColateral);
			psc.setString(4, oclusionTotal);
			psc.setString(5, idTipoLesion);
			psc.setString(6, ramaBifurcada);
			psc.setString(7, medina);
			psc.executeUpdate();
			psc.close();
			con.cerrar();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		
	}

	


	
	
	
	public boolean eliminar_Informe_Ventriculografia(String idInforme){
		
		PreparedStatement psc = null;
		try {
			Conexion con = new Conexion();
			
			psc = con.conn.prepareStatement(
					"DELETE "+
					"FROM cat_informe_ventriculografia "+
					"WHERE "+
					"id_informe_diagnostico_hemodinamia_fk ='"+idInforme+"'") ;
    		psc.executeUpdate();
			psc.close();
			con.cerrar();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		

	}
	
	
public boolean eliminar_imagen_Ventriculografia(String idInforme){
		
		PreparedStatement psc = null;
		try {
			Conexion con = new Conexion();
			
			psc = con.conn.prepareStatement(
					"UPDATE cat_contenido_informe_hemodinamia "+
					"SET "+ 
					"imagen_ventriculografia = NULL "+
					"WHERE id_informe_diagnostico_hemodinamia_fk ='"+idInforme+"'") ;
    		psc.executeUpdate();
			psc.close();
			con.cerrar();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		

	}
	
	
public boolean eliminar_Informe_Aortograma(String idInforme){
		
		PreparedStatement psc = null;
		try {
			Conexion con = new Conexion();
			
			psc = con.conn.prepareStatement(
					"DELETE "+
					"FROM cat_informe_aortograma "+
					"WHERE "+
					"id_informe_diagnostico_hemodinamia_fk ='"+idInforme+"'") ;
    		psc.executeUpdate();
			psc.close();
			con.cerrar();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		

	}
	
	
}
