package eco_Cardiologia;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Cardiologia_Datos
 */
public class Cardiologia_Datos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Cardiologia_Datos() {
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
		
		Metodos_cardiologia metodos = new Metodos_cardiologia();
	
		
		String opcion = request.getParameter("opcion");
		
		
			
		
		String codigoPaciente =  request.getParameter("codpaciente");
		String codUsuario =  request.getParameter("codusuario");
		String indicacion =  request.getParameter("indicacion");
		String aspecto_aorta = request.getParameter("aspecto_aorta");
		
		
		String [] observacionesEcocardio =  request.getParameter("observaciones").split("&");
		
		
		
		String [] analisis_aorta_mediciones =  request.getParameter("analisis_aorta_mediciones").split(",");
		String [] analisis_aorta_patologias =  request.getParameter("analisis_aorta_patologias").split(",");
	
		
		String [] analisis_valvulas_aspectos =  request.getParameter("analisis_valvulas_aspectos").split(",");
	
		
		String [] analisis_valvula_aorta_insuficiencia =  request.getParameter("analisis_valvula_aorta_insuficiencia").split(",");
		String [] analisis_valvula_aorta_estenosis =  request.getParameter("analisis_valvula_aorta_estenosis").split(",");
		String [] analisis_valvula_aorta_protesica =  request.getParameter("analisis_valvula_aorta_protesica").split(",");
		
		
		String [] analisis_valvula_mitral_insuficiencia =  request.getParameter("analisis_valvula_mitral_insuficiencia").split(",");
		String [] analisis_valvula_mitral_estenosis =  request.getParameter("analisis_valvula_mitral_estenosis").split(",");
		String [] analisis_valvula_mitral_protesica =  request.getParameter("analisis_valvula_mitral_protesica").split(",");
				
	
		String [] analisis_valvula_tricuspidea_insuficiencia =  request.getParameter("analisis_valvula_tricuspidea_insuficiencia").split(",");
		String [] analisis_valvula_tricuspidea_estenosis =  request.getParameter("analisis_valvula_tricuspidea_estenosis").split(",");
		String [] analisis_valvula_tricuspidea_protesica =  request.getParameter("analisis_valvula_tricuspidea_protesica").split(",");
		
		
		String [] analisis_valvula_pulmonal_insuficiencia =  request.getParameter("analisis_valvula_pulmonal_insuficiencia").split(",");
		String [] analisis_valvula_pulmonal_estenosis =  request.getParameter("analisis_valvula_pulmonal_estenosis").split(",");
		String [] analisis_valvula_pulmonal_protesica =  request.getParameter("analisis_valvula_pulmonal_protesica").split(",");
		

		
		boolean sw_analisis_valvula_aorta_protesica = Boolean.parseBoolean(request.getParameter("sw_analisis_valvula_aorta_protesica"));
		boolean sw_analisis_valvula_mitral_protesica = Boolean.parseBoolean(request.getParameter("sw_analisis_valvula_mitral_protesica"));
		boolean sw_analisis_valvula_tricuspidea_protesica =Boolean.parseBoolean(request.getParameter("sw_analisis_valvula_tricuspidea_protesica"));
		boolean sw_analisis_valvula_pulmonal_protesica = Boolean.parseBoolean(request.getParameter("sw_analisis_valvula_pulmonal_protesica"));

		String [] analisis_ventriculo_izquierdo = request.getParameter("analisis_ventriculo_izquierdo").split(",");
		String imagen_segmentacion_ventricular =  request.getParameter("imagen_segmentacion_ventricular").trim();
		
		String [] generalidades_segmento_basal = request.getParameter("generalidades_segmento_basal").split(",");
		String [] generalidades_segmento_medio = request.getParameter("generalidades_segmento_medio").split(",");
		String [] generalidades_segmento_apical = request.getParameter("generalidades_segmento_apical").split(",");

		String [] analisis_ventriculo_derecho = request.getParameter("analisis_ventriculo_derecho").split(",");
		
		String [] analisis_auricula_izquierda = request.getParameter("analisis_auricula_izquierda").split(",");
		String [] analisis_auricula_derecha = request.getParameter("analisis_auricula_derecha").split(",");
		
		String [] analisis_vena_cava = request.getParameter("analisis_vena_cava").split(",");
		String [] analisis_pericardio = request.getParameter("analisis_pericardio").split(",");
		    
	//	Vector <String> vectorResultado1 = metodos.obtenerNombresColumnasPorTabla("eco_valvulas");
	//	Vector <String> vectorResultado2 =metodos.obtenerTipodeDatosColumnasPorTabla("eco_analisis_insuficiencia_valvula_aorta");
		
		//System.out.print(vectorResultado1.toString()+ "\n");
	//	System.out.print(vectorResultado2.toString()+ "\n");
		
		
        boolean insercciones_realizadas_con_exito = false;
		
	/*	for (int i = 0 ; i < analisis_valvulas_aspectos.length;i++ ){
			
			System.out.print((i+1)+"+++++"+analisis_valvulas_aspectos[i] + "\n");
		}*/
	/*	System.out.print("\n");
		for (int i = 0 ; i < analisis_aorta_patologias.length;i++ ){
			
			System.out.print(analisis_aorta_patologias[i] + "\n");
		}*/
	
        insercciones_realizadas_con_exito=metodos.insertarEncabesadoInformeEco(
				fechacjmysql,
				hracjmysql, 
				codUsuario, 
				codigoPaciente, 
				"1", 
				indicacion,
				aspecto_aorta
				);
		

		String codInformeEco = ""+metodos.codigoActualInformeEco();
		
		  insercciones_realizadas_con_exito= metodos.asociarObservacionesInformeEco(codInformeEco);
		  insercciones_realizadas_con_exito=metodos.actualizarObservacionesInformeEco(codInformeEco, observacionesEcocardio);
		
		
		  insercciones_realizadas_con_exito=metodos.asociarAnalisisAortaInformeEco(codInformeEco,analisis_aorta_mediciones.length);
		  insercciones_realizadas_con_exito=metodos.actualizarAnalisisAortaInformeEco(codInformeEco, analisis_aorta_mediciones, analisis_aorta_patologias);
		
  
    	
		if (sw_analisis_valvula_aorta_protesica == false){
			  insercciones_realizadas_con_exito=metodos.asociarAnalisisAspectosValvulasInformeEco(codInformeEco, "1");
			  insercciones_realizadas_con_exito=metodos.actualizarAnalisisAspectosValvulasInformeEco(codInformeEco, analisis_valvulas_aspectos, "1");
			
			  insercciones_realizadas_con_exito=metodos.asociarInsuficienciaValvulaAortaInformeEco(codInformeEco);
			  insercciones_realizadas_con_exito=metodos.actualizarInsuficienciasValvulaAortaInformeEco(codInformeEco, analisis_valvula_aorta_insuficiencia);
			  
			  insercciones_realizadas_con_exito=metodos.asociarEstenosisValvulaAortaInformeEco(codInformeEco);
			  insercciones_realizadas_con_exito=metodos.actualizarEstenosisValvulaAortaInformeEco(codInformeEco, analisis_valvula_aorta_estenosis);
		}else{
		
			  insercciones_realizadas_con_exito=metodos.asociarProtesisValvulaAorticaInformeEco(codInformeEco);
			  insercciones_realizadas_con_exito=metodos.actualizarProtesisValvulaAortaInformeEco(codInformeEco, analisis_valvula_aorta_protesica);
		}
		
		
		
		
		if (sw_analisis_valvula_mitral_protesica == false){
			  insercciones_realizadas_con_exito=metodos.asociarAnalisisAspectosValvulasInformeEco(codInformeEco, "2");
			  insercciones_realizadas_con_exito=metodos.actualizarAnalisisAspectosValvulasInformeEco(codInformeEco, analisis_valvulas_aspectos, "2");
			 
			  insercciones_realizadas_con_exito=metodos.asociarInsuficienciaValvulaMitralInformeEco(codInformeEco);
			  insercciones_realizadas_con_exito=metodos.actualizarInsuficienciasValvulaMitralInformeEco(codInformeEco, analisis_valvula_mitral_insuficiencia);
			  
			  insercciones_realizadas_con_exito=metodos.asociarEstenosisValvulaMitralInformeEco(codInformeEco);
			  insercciones_realizadas_con_exito=metodos.actualizarEstenosisValvulaMitralInformeEco(codInformeEco, analisis_valvula_mitral_estenosis);
			
		}else{
			  insercciones_realizadas_con_exito=metodos.asociarProtesisValvulaMitralInformeEco(codInformeEco);
			  insercciones_realizadas_con_exito=metodos.actualizarProtesisValvulaMitralInformeEco(codInformeEco, analisis_valvula_mitral_protesica);
		}
		
		

		if (sw_analisis_valvula_tricuspidea_protesica == false){
			  insercciones_realizadas_con_exito=metodos.asociarAnalisisAspectosValvulasInformeEco(codInformeEco, "3");
			  insercciones_realizadas_con_exito=metodos.actualizarAnalisisAspectosValvulasInformeEco(codInformeEco, analisis_valvulas_aspectos, "3");
			 
			  insercciones_realizadas_con_exito=metodos.asociarInsuficienciaValvulaTricuspideaInformeEco(codInformeEco);
			  insercciones_realizadas_con_exito=metodos.actualizarInsuficienciasValvulaTricuspideaInformeEco(codInformeEco, analisis_valvula_tricuspidea_insuficiencia);
			  
			  insercciones_realizadas_con_exito=metodos.asociarEstenosisValvulaTricuspideaInformeEco(codInformeEco);
			  insercciones_realizadas_con_exito=metodos.actualizarEstenosisValvulaTricuspideInformeEco(codInformeEco, analisis_valvula_tricuspidea_estenosis);
			
		}else{
			  insercciones_realizadas_con_exito=metodos.asociarProtesisValvulaTricuspideaInformeEco(codInformeEco);
			  insercciones_realizadas_con_exito=metodos.actualizarProtesisValvulaTricuspideaInformeEco(codInformeEco, analisis_valvula_tricuspidea_protesica);
		}
		
	
		
		
		if (sw_analisis_valvula_pulmonal_protesica == false){
			  insercciones_realizadas_con_exito=metodos.asociarAnalisisAspectosValvulasInformeEco(codInformeEco, "4");
			  insercciones_realizadas_con_exito=metodos.actualizarAnalisisAspectosValvulasInformeEco(codInformeEco, analisis_valvulas_aspectos, "4");
			 
			  insercciones_realizadas_con_exito=metodos.asociarInsuficienciaValvulaPulmonalInformeEco(codInformeEco);
			  insercciones_realizadas_con_exito=metodos.actualizarInsuficienciasValvulaPulmonalInformeEco(codInformeEco, analisis_valvula_pulmonal_insuficiencia);
			  
			  insercciones_realizadas_con_exito=metodos.asociarEstenosisValvulaPulmonalInformeEco(codInformeEco);
			  insercciones_realizadas_con_exito=metodos.actualizarEstenosisValvulaPulmonalInformeEco(codInformeEco, analisis_valvula_pulmonal_estenosis);
			  
		}else{
			  insercciones_realizadas_con_exito=metodos.asociarProtesisValvulaPulmonalInformeEco(codInformeEco);
			  insercciones_realizadas_con_exito=metodos.actualizarProtesisValvulaPulmonalInformeEco(codInformeEco, analisis_valvula_pulmonal_protesica);
		}
	
		

		
		  insercciones_realizadas_con_exito=metodos.asociarAnalisisVentriculoIzquierdoInformeEco(codInformeEco);
		  insercciones_realizadas_con_exito=metodos.actualizarVentriculoIzquierdoInformeEco(codInformeEco, analisis_ventriculo_izquierdo);
		  insercciones_realizadas_con_exito=metodos.InsertarImagenSegmentacionVentricular(codInformeEco, imagen_segmentacion_ventricular);
		  
		  insercciones_realizadas_con_exito=metodos.asociarMotilidadVentricularIzquierdaInformeEco(codInformeEco, "1", generalidades_segmento_basal);
		  insercciones_realizadas_con_exito=metodos.asociarMotilidadVentricularIzquierdaInformeEco(codInformeEco, "2", generalidades_segmento_medio);
		  insercciones_realizadas_con_exito=metodos.asociarMotilidadVentricularIzquierdaInformeEco(codInformeEco, "3", generalidades_segmento_apical);
		  insercciones_realizadas_con_exito=metodos.actualizarMotilidadPorSegmentacionVentriculoIzquierdoInformeEco(codInformeEco, "1", generalidades_segmento_basal);
		  insercciones_realizadas_con_exito=metodos.actualizarMotilidadPorSegmentacionVentriculoIzquierdoInformeEco(codInformeEco, "2", generalidades_segmento_medio);
		  insercciones_realizadas_con_exito=metodos.actualizarMotilidadPorSegmentacionVentriculoIzquierdoInformeEco(codInformeEco, "3", generalidades_segmento_apical);
		
		  insercciones_realizadas_con_exito=metodos.asociarAnalisisVentriculoDerechoInformeEco(codInformeEco);
		  insercciones_realizadas_con_exito=metodos.actualizarVentriculoDerechoInformeEco(codInformeEco, analisis_ventriculo_derecho);
		
		  insercciones_realizadas_con_exito=metodos.asociarAnalisisAuriculaIzquierda(codInformeEco);
		  insercciones_realizadas_con_exito=metodos.actualizarAuriculaIzquierdaInformeEco(codInformeEco, analisis_auricula_izquierda);
        
		  insercciones_realizadas_con_exito=metodos.asociarAnalisisAuriculaDerecha(codInformeEco);
		  insercciones_realizadas_con_exito=metodos.actualizarAuriculaDerechaInformeEco(codInformeEco, analisis_auricula_derecha);
        
		  insercciones_realizadas_con_exito=metodos.asociarAnalisisVenaCavaInformeEco(codInformeEco);
		  insercciones_realizadas_con_exito=metodos.actualizarVenaCavaInformeEco(codInformeEco, analisis_vena_cava);
        
		  insercciones_realizadas_con_exito=metodos.asociarAnalisisPericardioInformeEco(codInformeEco);
		  insercciones_realizadas_con_exito=metodos.actualizarPericardioInformeEco(codInformeEco, analisis_pericardio);
        
		  if(insercciones_realizadas_con_exito == true){
	      out.print("1;Ha realizado correctamente el informe de ecocardiograma a con fecha "+fechacjmysql+" en la hora "+hracjmysql+";"+codInformeEco);		  
		  }else{
		  out.print("0;Hubo un error al ingresar los datos al sistema!");
		  }
		
	}

	}


