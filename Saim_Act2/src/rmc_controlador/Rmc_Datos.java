package rmc_controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rmc_metodo.MetodoCrearRmc;

public class Rmc_Datos extends HttpServlet{

	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Rmc_Datos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter(); // respuesta
		Calendar c = new GregorianCalendar();
		String dia = Integer.toString(c.get(Calendar.DATE));
		String mes = Integer.toString(c.get(Calendar.MONTH));
		String annio = Integer.toString(c.get(Calendar.YEAR));
		int m=Integer.parseInt(mes)+1;
		mes=String.valueOf(m);
		int d=Integer.parseInt(dia);
		if(d<10){dia="0"+d;}
		if(m<10){mes="0"+m;}
		
		String fechacjmysql=annio+"-"+mes+"-"+dia; //para la base de datos

		Calendar calendario = Calendar.getInstance();
		int hora;
		int minutos, segundos;
		hora =calendario.get(Calendar.HOUR_OF_DAY);
		minutos = calendario.get(Calendar.MINUTE);
		segundos = calendario.get(Calendar.SECOND);
		String hracjmysql= hora+":"+minutos+":"+segundos; 
		
		MetodoCrearRmc metodos = new MetodoCrearRmc();
	
		boolean insercciones_realizadas_con_exito = false;
		String opcion = request.getParameter("opcion");
		ResultSet busqueda_ritmo = null;
		
		 if (opcion.equals("ritmo")){
			 String ritmo = request.getParameter("ritmo_txt");
			 busqueda_ritmo = metodos.buscarRitmo(ritmo);
			 try {
			 if(!busqueda_ritmo.next()){
		        insercciones_realizadas_con_exito=metodos.InsertarRitmo(ritmo);
		        if(insercciones_realizadas_con_exito == true){
		        	
		  	      out.print("1;insercion ritmo exitosa");		  
		  		  }else{
		  		  out.print("0;Hubo un error al ingresar el ritmo!");
		  		  }
			 }
			 } catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			    }else{
			
	    
		String codigoPaciente =  request.getParameter("codpaciente");
		String codUsuario =  request.getParameter("codusuario");
		String indicacion =  request.getParameter("indicacion");
		String exploracion =  request.getParameter("exploracion");
		String aspecto_aorta = request.getParameter("aspecto_aorta");
		String indicacion2 =  request.getParameter("indicacion2");
		String indicacion3 =  request.getParameter("indicacion3");
		String indicacion4 =  request.getParameter("indicacion4");
		
		
		String [] analisis_datos_generales = request.getParameter("analisis_datos_generales").split(",");
		
		String [] observacionesEcocardio =  request.getParameter("observaciones").split("&");
		
		//recopilar sw de tisular (fibrosis, edema, hemorragia, omv)
		boolean swfibrosis = Boolean.parseBoolean(request.getParameter("swfibrosis"));
		boolean swedema = Boolean.parseBoolean(request.getParameter("swedema"));
		boolean swhemorragia =Boolean.parseBoolean(request.getParameter("swhemorragia"));
		boolean swomv = Boolean.parseBoolean(request.getParameter("swomv"));
		String fibrosis = request.getParameter("fibrosis");
		String edema = request.getParameter("edema");
		String hemorragia = request.getParameter("hemorragia");
		String omv = request.getParameter("omv");
		
	    String [] analisis_vol_masa_general = request.getParameter("analisis_vol_masa_general").split(",");
	    String [] analisis_vol_masa_vf = request.getParameter("analisis_vol_masa_vf").split(",");
	    String [] analisis_medidas_sistole = request.getParameter("analisis_medidas_sistole").split(",");
	    String [] analisis_calculo_hipertrofia = request.getParameter("analisis_calculo_hipertrofia").split(",");
	    String [] analisis_ventriculo_derecho = request.getParameter("analisis_ventriculo_derecho").split(",");
	    String imagen_segmentacion_ventricular =  request.getParameter("imagen_segmentacion_ventricular").trim();
	    String imagen_fibrosis =  request.getParameter("imagen_fibrosis").trim();
	    String imagen_edema =  request.getParameter("imagen_edema").trim();
	    String imagen_hemorragia =  request.getParameter("imagen_hemorragia").trim();
	    String imagen_omv =  request.getParameter("imagen_omv").trim();
	    String [] analisis_auricula_izquierda = request.getParameter("analisis_auricula_izquierda").split(",");
		String [] analisis_auricula_derecha = request.getParameter("analisis_auricula_derecha").split(",");
	    String ritmo_txt = request.getParameter("ritmo_txt");
	    boolean swgrosor2 = Boolean.parseBoolean(request.getParameter("swgrosor2"));
	    boolean swgrosor3 = Boolean.parseBoolean(request.getParameter("swgrosor3"));
	    boolean swgrosor4 = Boolean.parseBoolean(request.getParameter("swgrosor4"));
	    boolean swgrosor5 = Boolean.parseBoolean(request.getParameter("swgrosor5"));
	    boolean swgrosor6 = Boolean.parseBoolean(request.getParameter("swgrosor6"));
	    boolean swgrosor7 = Boolean.parseBoolean(request.getParameter("swgrosor7"));
	    boolean swgrosor8 = Boolean.parseBoolean(request.getParameter("swgrosor8"));
	    boolean swgrosor9 = Boolean.parseBoolean(request.getParameter("swgrosor9"));
	    boolean swgrosor10 = Boolean.parseBoolean(request.getParameter("swgrosor10"));
	    boolean swgrosor11 = Boolean.parseBoolean(request.getParameter("swgrosor11"));
	    boolean swgrosor12 = Boolean.parseBoolean(request.getParameter("swgrosor12"));
	    boolean swgrosor13 = Boolean.parseBoolean(request.getParameter("swgrosor13"));
	    boolean swgrosor14 = Boolean.parseBoolean(request.getParameter("swgrosor14"));
	    boolean swgrosor15 = Boolean.parseBoolean(request.getParameter("swgrosor15"));
	    boolean swgrosor16 = Boolean.parseBoolean(request.getParameter("swgrosor16"));
		String [] analisis_grosor_septum = request.getParameter("analisis_grosor_septum").split(",");
		String [] analisis_grosor2_septum = request.getParameter("analisis_grosor2_septum").split(",");
		String [] analisis_grosor3_septum = request.getParameter("analisis_grosor3_septum").split(",");
		String [] analisis_grosor4_septum = request.getParameter("analisis_grosor4_septum").split(",");
		String [] analisis_grosor5_septum = request.getParameter("analisis_grosor5_septum").split(",");
		String [] analisis_grosor6_septum = request.getParameter("analisis_grosor6_septum").split(",");
		String [] analisis_grosor7_septum = request.getParameter("analisis_grosor7_septum").split(",");
		String [] analisis_grosor8_septum = request.getParameter("analisis_grosor8_septum").split(",");
		String [] analisis_grosor9_septum = request.getParameter("analisis_grosor9_septum").split(",");
		String [] analisis_grosor10_septum = request.getParameter("analisis_grosor10_septum").split(",");
		String [] analisis_grosor11_septum = request.getParameter("analisis_grosor11_septum").split(",");
		String [] analisis_grosor12_septum = request.getParameter("analisis_grosor12_septum").split(",");
		String [] analisis_grosor13_septum = request.getParameter("analisis_grosor13_septum").split(",");
		String [] analisis_grosor14_septum = request.getParameter("analisis_grosor14_septum").split(",");
		String [] analisis_grosor15_septum = request.getParameter("analisis_grosor15_septum").split(",");
		String [] analisis_grosor16_septum = request.getParameter("analisis_grosor16_septum").split(",");
		String [] generalidades_segmento_basal = request.getParameter("generalidades_segmento_basal").split(",");
		String [] generalidades_segmento_medio = request.getParameter("generalidades_segmento_medio").split(",");
		String [] generalidades_segmento_apical = request.getParameter("generalidades_segmento_apical").split(",");
		
		String [] analisis_pericardio = request.getParameter("analisis_pericardio").split(",");
		

		
		    
	//	Vector <String> vectorResultado1 = metodos.obtenerNombresColumnasPorTabla("eco_valvulas");
	//	Vector <String> vectorResultado2 =metodos.obtenerTipodeDatosColumnasPorTabla("eco_analisis_insuficiencia_valvula_aorta");
		
		//System.out.print(vectorResultado1.toString()+ "\n");
	//	System.out.print(vectorResultado2.toString()+ "\n");
		
		
        
		
	/*	for (int i = 0 ; i < analisis_valvulas_aspectos.length;i++ ){
			
			System.out.print((i+1)+"+++++"+analisis_valvulas_aspectos[i] + "\n");
		}*/
	/*	System.out.print("\n");
		for (int i = 0 ; i < analisis_aorta_patologias.length;i++ ){
			
			System.out.print(analisis_aorta_patologias[i] + "\n");
		}*/
	   
        insercciones_realizadas_con_exito=metodos.insertarEncabesadoInformeRmc(
				fechacjmysql,
				hracjmysql, 
				codUsuario, 
				codigoPaciente, 
				"1", 
				indicacion,
				aspecto_aorta,
				indicacion2,
				indicacion3,
				indicacion4,
				exploracion
				);
		

		String codInformeRmc = ""+metodos.codigoActualInformeRmc();
		
		  insercciones_realizadas_con_exito= metodos.asociarObservacionesInformeRmc(codInformeRmc);
		  insercciones_realizadas_con_exito=metodos.actualizarObservacionesInformeRmc(codInformeRmc, observacionesEcocardio);
		  insercciones_realizadas_con_exito=metodos.asociarAnalisisDatosGeneralesRmc(codInformeRmc);
		  insercciones_realizadas_con_exito=metodos.actualizarAnalisisDatosGeneralesRmc(codInformeRmc, analisis_datos_generales);
		//inserciones vol masa
		  insercciones_realizadas_con_exito=metodos.asociarVolMasaGeneralRmc(codInformeRmc);
		  insercciones_realizadas_con_exito=metodos.actualizarVolMasaGeneralRmc(codInformeRmc, analisis_vol_masa_general);
		  
		  insercciones_realizadas_con_exito=metodos.asociarVolMasaVfRmc(codInformeRmc);
		  insercciones_realizadas_con_exito=metodos.actualizarVolMasaVfRmc(codInformeRmc, analisis_vol_masa_vf);
		  
		  insercciones_realizadas_con_exito=metodos.asociarMedidasSistoleRmc(codInformeRmc);
		  insercciones_realizadas_con_exito=metodos.actualizarMedidasSistole(codInformeRmc, analisis_medidas_sistole);
		  
		  insercciones_realizadas_con_exito=metodos.asociarCalculoHipertrofiaRmc(codInformeRmc);
		  insercciones_realizadas_con_exito=metodos.actualizarCalculoHipertrofia(codInformeRmc, analisis_calculo_hipertrofia);
		  
		  insercciones_realizadas_con_exito=metodos.asociarVentriculoDerechoRmc(codInformeRmc);
		  insercciones_realizadas_con_exito=metodos.actualizarVentriculoDerecho(codInformeRmc, analisis_ventriculo_derecho);
		  
		  
		  
		  //insercciones_realizadas_con_exito=metodos.asociarAnalisisVentriculoIzquierdoInformeEco(codInformeRmc);
		  //insercciones_realizadas_con_exito=metodos.actualizarVentriculoIzquierdoInformeEco(codInformeRmc, analisis_ventriculo_izquierdo);
		  //fin inserciones vol masa
		  insercciones_realizadas_con_exito= metodos.InsertarImagenSegmentacionVentricular(codInformeRmc, imagen_segmentacion_ventricular);
		  //insertar imagenes en rmc_tisular
		  insercciones_realizadas_con_exito= metodos.asociarTisular(codInformeRmc);
		  //if los sw son igual a true inserte imagenes y valores
		    if (swfibrosis == true){
		    	insercciones_realizadas_con_exito= metodos.InsertarFibrosis(codInformeRmc, fibrosis);
		    	insercciones_realizadas_con_exito= metodos.InsertarImagenFibrosis(codInformeRmc, imagen_fibrosis);
		    }
		    
		    if(swedema == true){
		    	insercciones_realizadas_con_exito= metodos.InsertarEdema(codInformeRmc, edema);
		    	 insercciones_realizadas_con_exito= metodos.InsertarImagenEdema(codInformeRmc, imagen_edema);
		    }
		    
		    if (swhemorragia == true){
		    	insercciones_realizadas_con_exito= metodos.InsertarHemorragia(codInformeRmc, hemorragia);
		    	 insercciones_realizadas_con_exito= metodos.InsertarImagenHemorragia(codInformeRmc, imagen_hemorragia);
		    }
		    
		    if(swomv == true){
		    	insercciones_realizadas_con_exito= metodos.InsertarOmv(codInformeRmc, omv);
		    	insercciones_realizadas_con_exito= metodos.InsertarImagenOmv(codInformeRmc, imagen_omv);
		    }
		  //fin if los sw son igual a true inserte imagenes y valores
		 
		  insercciones_realizadas_con_exito= metodos.actualizarRitmo(codInformeRmc, ritmo_txt);
		  insercciones_realizadas_con_exito=metodos.asociarGrosorSeptumInformeRmc(codInformeRmc,analisis_grosor_septum);
		  insercciones_realizadas_con_exito=metodos.actualizarGrosorSeptumInformeRmc(codInformeRmc, analisis_grosor_septum);
		  if (swgrosor2 == true){
			  insercciones_realizadas_con_exito=metodos.asociarGrosorSeptumInformeRmc(codInformeRmc,analisis_grosor2_septum);
			  insercciones_realizadas_con_exito=metodos.actualizarGrosorSeptumInformeRmc(codInformeRmc, analisis_grosor2_septum);
		    }
		 
		  if (swgrosor3 == true){
		  insercciones_realizadas_con_exito=metodos.asociarGrosorSeptumInformeRmc(codInformeRmc,analisis_grosor3_septum);
		  insercciones_realizadas_con_exito=metodos.actualizarGrosorSeptumInformeRmc(codInformeRmc, analisis_grosor3_septum);
		  }
		  
		  if (swgrosor4 == true){
		  insercciones_realizadas_con_exito=metodos.asociarGrosorSeptumInformeRmc(codInformeRmc,analisis_grosor4_septum);
		  insercciones_realizadas_con_exito=metodos.actualizarGrosorSeptumInformeRmc(codInformeRmc, analisis_grosor4_septum);
		  }
		  
		  if (swgrosor5 == true){
		  insercciones_realizadas_con_exito=metodos.asociarGrosorSeptumInformeRmc(codInformeRmc,analisis_grosor5_septum);
		  insercciones_realizadas_con_exito=metodos.actualizarGrosorSeptumInformeRmc(codInformeRmc, analisis_grosor5_septum);
		  }
		  
		  if (swgrosor6 == true){
		  insercciones_realizadas_con_exito=metodos.asociarGrosorSeptumInformeRmc(codInformeRmc,analisis_grosor6_septum);
		  insercciones_realizadas_con_exito=metodos.actualizarGrosorSeptumInformeRmc(codInformeRmc, analisis_grosor6_septum);
		  }
		  
		  if (swgrosor7 == true){
		  insercciones_realizadas_con_exito=metodos.asociarGrosorSeptumInformeRmc(codInformeRmc,analisis_grosor7_septum);
		  insercciones_realizadas_con_exito=metodos.actualizarGrosorSeptumInformeRmc(codInformeRmc, analisis_grosor7_septum);
		  }
		  
		  if (swgrosor8 == true){
		  insercciones_realizadas_con_exito=metodos.asociarGrosorSeptumInformeRmc(codInformeRmc,analisis_grosor8_septum);
		  insercciones_realizadas_con_exito=metodos.actualizarGrosorSeptumInformeRmc(codInformeRmc, analisis_grosor8_septum);
		  }
		  
		  if (swgrosor9 == true){
		  insercciones_realizadas_con_exito=metodos.asociarGrosorSeptumInformeRmc(codInformeRmc,analisis_grosor9_septum);
		  insercciones_realizadas_con_exito=metodos.actualizarGrosorSeptumInformeRmc(codInformeRmc, analisis_grosor9_septum);
		  }
		  
		  if (swgrosor10 == true){
		  insercciones_realizadas_con_exito=metodos.asociarGrosorSeptumInformeRmc(codInformeRmc,analisis_grosor10_septum);
		  insercciones_realizadas_con_exito=metodos.actualizarGrosorSeptumInformeRmc(codInformeRmc, analisis_grosor10_septum);
		  }
		  
		  if (swgrosor11 == true){
		  insercciones_realizadas_con_exito=metodos.asociarGrosorSeptumInformeRmc(codInformeRmc,analisis_grosor11_septum);
		  insercciones_realizadas_con_exito=metodos.actualizarGrosorSeptumInformeRmc(codInformeRmc, analisis_grosor11_septum);
		  }
		  
		  if (swgrosor12 == true){
		  insercciones_realizadas_con_exito=metodos.asociarGrosorSeptumInformeRmc(codInformeRmc,analisis_grosor12_septum);
		  insercciones_realizadas_con_exito=metodos.actualizarGrosorSeptumInformeRmc(codInformeRmc, analisis_grosor12_septum);
		  }
		  
		  if (swgrosor13 == true){
		  insercciones_realizadas_con_exito=metodos.asociarGrosorSeptumInformeRmc(codInformeRmc,analisis_grosor13_septum);
		  insercciones_realizadas_con_exito=metodos.actualizarGrosorSeptumInformeRmc(codInformeRmc, analisis_grosor13_septum);
		  }
		  
		  if (swgrosor14 == true){
		  insercciones_realizadas_con_exito=metodos.asociarGrosorSeptumInformeRmc(codInformeRmc,analisis_grosor14_septum);
		  insercciones_realizadas_con_exito=metodos.actualizarGrosorSeptumInformeRmc(codInformeRmc, analisis_grosor14_septum);
		  }
		  
		  if (swgrosor15 == true){
		  insercciones_realizadas_con_exito=metodos.asociarGrosorSeptumInformeRmc(codInformeRmc,analisis_grosor15_septum);
		  insercciones_realizadas_con_exito=metodos.actualizarGrosorSeptumInformeRmc(codInformeRmc, analisis_grosor15_septum);
		  }
		  
		  if (swgrosor16 == true){
		  insercciones_realizadas_con_exito=metodos.asociarGrosorSeptumInformeRmc(codInformeRmc,analisis_grosor16_septum);
		  insercciones_realizadas_con_exito=metodos.actualizarGrosorSeptumInformeRmc(codInformeRmc, analisis_grosor16_septum);
		  }
		 
		  insercciones_realizadas_con_exito=metodos.asociarMotilidadVentricularIzquierdaInformeRmc(codInformeRmc, "1", generalidades_segmento_basal);
		  insercciones_realizadas_con_exito=metodos.asociarMotilidadVentricularIzquierdaInformeRmc(codInformeRmc, "2", generalidades_segmento_medio);
		  insercciones_realizadas_con_exito=metodos.asociarMotilidadVentricularIzquierdaInformeRmc(codInformeRmc, "3", generalidades_segmento_apical);
		  insercciones_realizadas_con_exito=metodos.actualizarMotilidadPorSegmentacionVentriculoIzquierdoInformeRmc(codInformeRmc, "1", generalidades_segmento_basal);
		  insercciones_realizadas_con_exito=metodos.actualizarMotilidadPorSegmentacionVentriculoIzquierdoInformeRmc(codInformeRmc, "2", generalidades_segmento_medio);
		  insercciones_realizadas_con_exito=metodos.actualizarMotilidadPorSegmentacionVentriculoIzquierdoInformeRmc(codInformeRmc, "3", generalidades_segmento_apical);
		
		  insercciones_realizadas_con_exito=metodos.asociarAnalisisAuriculaIzquierda(codInformeRmc);
		  insercciones_realizadas_con_exito=metodos.actualizarAuriculaIzquierda(codInformeRmc, analisis_auricula_izquierda);
        
		  insercciones_realizadas_con_exito=metodos.asociarAnalisisAuriculaDerecha(codInformeRmc);
		  insercciones_realizadas_con_exito=metodos.actualizarAuriculaDerecha(codInformeRmc, analisis_auricula_derecha);
        
		  insercciones_realizadas_con_exito=metodos.asociarPericardio(codInformeRmc);
		  insercciones_realizadas_con_exito=metodos.actualizarPericardio(codInformeRmc, analisis_pericardio);
        
		  
        
		  if(insercciones_realizadas_con_exito == true){
	      out.print("1;Ha realizado correctamente el informe RMC con fecha "+fechacjmysql+" en la hora "+hracjmysql+";"+codInformeRmc);		  
		  }else{
		  out.print("0;Hubo un error al ingresar los datos al sistema!");
		  }
	    
	    }
	}

}
