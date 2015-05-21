package odont_controlador;

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

import odont_metodo.MetodoCrearOdontograma;


public class Odont_datos extends HttpServlet{
private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Odont_datos() {
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
		
		MetodoCrearOdontograma metodos = new MetodoCrearOdontograma();
	
		boolean insercciones_realizadas_con_exito = false;
		String opcion = request.getParameter("opcion");
		//ResultSet busqueda_ritmo = null;
		/*
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
			*/
	    
		String codigoPaciente =  request.getParameter("codpaciente");
		String codUsuario =  request.getParameter("codusuario");
		String admPaciente =  request.getParameter("admpaciente");
		
		//antecedentes medicos y odontologicos
		String []  informe_general = request.getParameter("informe_general").split("&");
		String []  alergias = request.getParameter("alergias").split("&");
		String []  hemorragias = request.getParameter("hemorragias").split("&");
		String []  enf_respiratorias = request.getParameter("enf_respiratorias").split("&");
		String []  cardiopatias = request.getParameter("cardiopatias").split("&");
		String []  fiebre_reumatica = request.getParameter("fiebre_reumatica").split("&");
		String []  enf_renales = request.getParameter("enf_renales").split("&");
		String []  hepatitis = request.getParameter("hepatitis").split("&");
		String []  trans_gastricos = request.getParameter("trans_gastricos").split("&");
		String []  tension_arterial = request.getParameter("tension_arterial").split("&");
		String []  diabetes = request.getParameter("diabetes").split("&");
		String []  medicamentos = request.getParameter("medicamentos").split("&");
		String []  cirugias = request.getParameter("cirugias").split("&");
		String []  protesis = request.getParameter("protesis").split("&");
		String []  hiv = request.getParameter("hiv").split("&");
		String []  extracciones = request.getParameter("extracciones").split("&");
		String []  enf_orales = request.getParameter("enf_orales").split("&");
		String []  ant_familiares = request.getParameter("ant_familiares").split("&");
		String []  ant_med_otros = request.getParameter("ant_med_otros").split("&");
		
		//examen clinico
		String []  lengua = request.getParameter("lengua").split("&");
		String []  carrillos = request.getParameter("carrillos").split("&");
		String []  abrasion = request.getParameter("abrasion").split("&");
		String []  atricion = request.getParameter("atricion").split("&");
		String []  hipoplasias = request.getParameter("hipoplasias").split("&");
		String []  gin_marginal = request.getParameter("gin_marginal").split("&");
		String []  gin_difusa = request.getParameter("gin_difusa").split("&");
		String []  micrognasia = request.getParameter("micrognasia").split("&");
		String []  macrognasia = request.getParameter("macrognasia").split("&");
		String []  desviacionlm = request.getParameter("desviacionlm").split("&");
		String []  malposicion = request.getParameter("malposicion").split("&");
		String []  atm = request.getParameter("atm").split("&");
		String []  hab_orales = request.getParameter("hab_orales").split("&");
		String []  paladar_blando = request.getParameter("paladar_blando").split("&");
		String []  piso_boca = request.getParameter("piso_boca").split("&");
		String []  supernumerarios = request.getParameter("supernumerarios").split("&");
		String []  hipodoncia = request.getParameter("hipodoncia").split("&");
		String []  fracturas = request.getParameter("fracturas").split("&");
		String []  periodontitis = request.getParameter("periodontitis").split("&");
		String []  retracciones = request.getParameter("retracciones").split("&");
		String []  torus = request.getParameter("torus").split("&");
		String []  sob_vertical= request.getParameter("sob_vertical").split("&");
		String []  sob_horizontal = request.getParameter("sob_horizontal").split("&");
		String []  ex_otros = request.getParameter("ex_otros").split("&");
		
		//promocion y prevencion
		String []  prom_prev = request.getParameter("prom_prev").split("&");
		
		//educacion oral
		String []  edu_oral = request.getParameter("edu_oral").split("&");
		
		//String [] observacionesEcocardio =  request.getParameter("observaciones").split("&");
		
		
	    String imagen_odontograma =  request.getParameter("imagen_odontograma").trim();
	   

	
	   
        insercciones_realizadas_con_exito=metodos.insertarEncabesadoInformeOdont(
				fechacjmysql,
				hracjmysql, 
				codUsuario, 
				codigoPaciente, 
				"1" ,
				admPaciente
				);
		

		String codInformeOdont = ""+metodos.codigoActualInformeOdont();
		
		  insercciones_realizadas_con_exito= metodos.asociarInformeGeneral(codInformeOdont);
		  insercciones_realizadas_con_exito=metodos.actualizarInformeGeneralOdont(codInformeOdont, informe_general);
		  
		  //inserciones promocion y prevencion
		  insercciones_realizadas_con_exito= metodos.asociarPromPrev(codInformeOdont);
		  insercciones_realizadas_con_exito=metodos.actualizarprom_prev(codInformeOdont, prom_prev);
		  
		  //inserciones educacion oral
		  insercciones_realizadas_con_exito= metodos.asociarEduOral(codInformeOdont);
		  insercciones_realizadas_con_exito=metodos.actualizarEduOral(codInformeOdont, edu_oral);
		  
		  //inserciones antecedentes medicos
		  
		  //alergias
		  insercciones_realizadas_con_exito=metodos.asociarAntecedentes(codInformeOdont);
		  insercciones_realizadas_con_exito=metodos.actualizarAntecedentesOdont(codInformeOdont, alergias);
		  //hemorragias
		  insercciones_realizadas_con_exito=metodos.asociarAntecedentes(codInformeOdont);
		  insercciones_realizadas_con_exito=metodos.actualizarAntecedentesOdont(codInformeOdont, hemorragias);
		  //enfermedades respiratorias
		  insercciones_realizadas_con_exito=metodos.asociarAntecedentes(codInformeOdont);
		  insercciones_realizadas_con_exito=metodos.actualizarAntecedentesOdont(codInformeOdont, enf_respiratorias);
		  //cardiopatias
		  insercciones_realizadas_con_exito=metodos.asociarAntecedentes(codInformeOdont);
		  insercciones_realizadas_con_exito=metodos.actualizarAntecedentesOdont(codInformeOdont, cardiopatias);
		  //fiebre reumatica
		  insercciones_realizadas_con_exito=metodos.asociarAntecedentes(codInformeOdont);
		  insercciones_realizadas_con_exito=metodos.actualizarAntecedentesOdont(codInformeOdont, fiebre_reumatica);
		  //enfermedades renales
		  insercciones_realizadas_con_exito=metodos.asociarAntecedentes(codInformeOdont);
		  insercciones_realizadas_con_exito=metodos.actualizarAntecedentesOdont(codInformeOdont, enf_renales);
		  //hepatitis
		  insercciones_realizadas_con_exito=metodos.asociarAntecedentes(codInformeOdont);
		  insercciones_realizadas_con_exito=metodos.actualizarAntecedentesOdont(codInformeOdont, hepatitis);
		  //transtornos gastricos
		  insercciones_realizadas_con_exito=metodos.asociarAntecedentes(codInformeOdont);
		  insercciones_realizadas_con_exito=metodos.actualizarAntecedentesOdont(codInformeOdont, trans_gastricos);
		  //tension arterial
		  insercciones_realizadas_con_exito=metodos.asociarAntecedentes(codInformeOdont);
		  insercciones_realizadas_con_exito=metodos.actualizarAntecedentesOdont(codInformeOdont, tension_arterial);
		  //diabetes
		  insercciones_realizadas_con_exito=metodos.asociarAntecedentes(codInformeOdont);
		  insercciones_realizadas_con_exito=metodos.actualizarAntecedentesOdont(codInformeOdont, diabetes);
		  //ingesta medicamentos
		  insercciones_realizadas_con_exito=metodos.asociarAntecedentes(codInformeOdont);
		  insercciones_realizadas_con_exito=metodos.actualizarAntecedentesOdont(codInformeOdont, medicamentos);
		  //cirugias (incluso orales)
		  insercciones_realizadas_con_exito=metodos.asociarAntecedentes(codInformeOdont);
		  insercciones_realizadas_con_exito=metodos.actualizarAntecedentesOdont(codInformeOdont, cirugias);
		  //uso de protesis dental o aparatologia oral
		  insercciones_realizadas_con_exito=metodos.asociarAntecedentes(codInformeOdont);
		  insercciones_realizadas_con_exito=metodos.actualizarAntecedentesOdont(codInformeOdont, protesis);
		  //HIV
		  insercciones_realizadas_con_exito=metodos.asociarAntecedentes(codInformeOdont);
		  insercciones_realizadas_con_exito=metodos.actualizarAntecedentesOdont(codInformeOdont, hiv);
		  //extracciones dentales
		  insercciones_realizadas_con_exito=metodos.asociarAntecedentes(codInformeOdont);
		  insercciones_realizadas_con_exito=metodos.actualizarAntecedentesOdont(codInformeOdont, extracciones);
		  //enfermedades orales
		  insercciones_realizadas_con_exito=metodos.asociarAntecedentes(codInformeOdont);
		  insercciones_realizadas_con_exito=metodos.actualizarAntecedentesOdont(codInformeOdont, enf_orales);
		  //antecedentes familiares
		  insercciones_realizadas_con_exito=metodos.asociarAntecedentes(codInformeOdont);
		  insercciones_realizadas_con_exito=metodos.actualizarAntecedentesOdont(codInformeOdont, ant_familiares);
		  //otros
		  insercciones_realizadas_con_exito=metodos.asociarAntecedentes(codInformeOdont);
		  insercciones_realizadas_con_exito=metodos.actualizarAntecedentesOdont(codInformeOdont, ant_med_otros);
		  
		  
		  
  //inserciones examen clinico
		  
		  //lengua
		  insercciones_realizadas_con_exito=metodos.asociarExamenClinico(codInformeOdont);
		  insercciones_realizadas_con_exito=metodos.actualizarExamClinico(codInformeOdont, lengua);
		  //carrillos
		  insercciones_realizadas_con_exito=metodos.asociarExamenClinico(codInformeOdont);
		  insercciones_realizadas_con_exito=metodos.actualizarExamClinico(codInformeOdont, carrillos);
		  //abrasion
		  insercciones_realizadas_con_exito=metodos.asociarExamenClinico(codInformeOdont);
		  insercciones_realizadas_con_exito=metodos.actualizarExamClinico(codInformeOdont, abrasion);
		  //atricion
		  insercciones_realizadas_con_exito=metodos.asociarExamenClinico(codInformeOdont);
		  insercciones_realizadas_con_exito=metodos.actualizarExamClinico(codInformeOdont, atricion);
		  //hipoplasias
		  insercciones_realizadas_con_exito=metodos.asociarExamenClinico(codInformeOdont);
		  insercciones_realizadas_con_exito=metodos.actualizarExamClinico(codInformeOdont, hipoplasias);
		  //gingivitis marginal
		  insercciones_realizadas_con_exito=metodos.asociarExamenClinico(codInformeOdont);
		  insercciones_realizadas_con_exito=metodos.actualizarExamClinico(codInformeOdont, gin_marginal);
		  //gingivitis difusa
		  insercciones_realizadas_con_exito=metodos.asociarExamenClinico(codInformeOdont);
		  insercciones_realizadas_con_exito=metodos.actualizarExamClinico(codInformeOdont, gin_difusa);
		  //micrognasia
		  insercciones_realizadas_con_exito=metodos.asociarExamenClinico(codInformeOdont);
		  insercciones_realizadas_con_exito=metodos.actualizarExamClinico(codInformeOdont, micrognasia);
		  //macrognasia
		  insercciones_realizadas_con_exito=metodos.asociarExamenClinico(codInformeOdont);
		  insercciones_realizadas_con_exito=metodos.actualizarExamClinico(codInformeOdont, macrognasia);
		  //desviacion linea media
		  insercciones_realizadas_con_exito=metodos.asociarExamenClinico(codInformeOdont);
		  insercciones_realizadas_con_exito=metodos.actualizarExamClinico(codInformeOdont, desviacionlm);
		  //malposicion
		  insercciones_realizadas_con_exito=metodos.asociarExamenClinico(codInformeOdont);
		  insercciones_realizadas_con_exito=metodos.actualizarExamClinico(codInformeOdont, malposicion);
		  //atm
		  insercciones_realizadas_con_exito=metodos.asociarExamenClinico(codInformeOdont);
		  insercciones_realizadas_con_exito=metodos.actualizarExamClinico(codInformeOdont, atm);
		  //habitos orales
		  insercciones_realizadas_con_exito=metodos.asociarExamenClinico(codInformeOdont);
		  insercciones_realizadas_con_exito=metodos.actualizarExamClinico(codInformeOdont, hab_orales);
		  //paladar blando
		  insercciones_realizadas_con_exito=metodos.asociarExamenClinico(codInformeOdont);
		  insercciones_realizadas_con_exito=metodos.actualizarExamClinico(codInformeOdont, paladar_blando);
		  //piso de boca
		  insercciones_realizadas_con_exito=metodos.asociarExamenClinico(codInformeOdont);
		  insercciones_realizadas_con_exito=metodos.actualizarExamClinico(codInformeOdont, piso_boca);
		  //supernumerarios
		  insercciones_realizadas_con_exito=metodos.asociarExamenClinico(codInformeOdont);
		  insercciones_realizadas_con_exito=metodos.actualizarExamClinico(codInformeOdont, supernumerarios);
		  //hipodoncia
		  insercciones_realizadas_con_exito=metodos.asociarExamenClinico(codInformeOdont);
		  insercciones_realizadas_con_exito=metodos.actualizarExamClinico(codInformeOdont, hipodoncia);
		  //fracturas
		  insercciones_realizadas_con_exito=metodos.asociarExamenClinico(codInformeOdont);
		  insercciones_realizadas_con_exito=metodos.actualizarExamClinico(codInformeOdont, fracturas);
		//periodontitis
		  insercciones_realizadas_con_exito=metodos.asociarExamenClinico(codInformeOdont);
		  insercciones_realizadas_con_exito=metodos.actualizarExamClinico(codInformeOdont, periodontitis);
		//retracciones
		  insercciones_realizadas_con_exito=metodos.asociarExamenClinico(codInformeOdont);
		  insercciones_realizadas_con_exito=metodos.actualizarExamClinico(codInformeOdont, retracciones);
		//torus
		  insercciones_realizadas_con_exito=metodos.asociarExamenClinico(codInformeOdont);
		  insercciones_realizadas_con_exito=metodos.actualizarExamClinico(codInformeOdont, torus);
		//sobremordida vertical
		  insercciones_realizadas_con_exito=metodos.asociarExamenClinico(codInformeOdont);
		  insercciones_realizadas_con_exito=metodos.actualizarExamClinico(codInformeOdont, sob_vertical);
		//sobremordida horizontal
		  insercciones_realizadas_con_exito=metodos.asociarExamenClinico(codInformeOdont);
		  insercciones_realizadas_con_exito=metodos.actualizarExamClinico(codInformeOdont, sob_horizontal);
		//otros
		  insercciones_realizadas_con_exito=metodos.asociarExamenClinico(codInformeOdont);
		  insercciones_realizadas_con_exito=metodos.actualizarExamClinico(codInformeOdont, ex_otros);
		  
		/*
		  insercciones_realizadas_con_exito=metodos.asociarAnalisisDatosGeneralesRmc(codInformeRmc);
		  insercciones_realizadas_con_exito=metodos.actualizarAnalisisDatosGeneralesRmc(codInformeRmc, analisis_datos_generales);
		*/
		  insercciones_realizadas_con_exito= metodos.InsertarImagenOdontograma(codInformeOdont, imagen_odontograma);
		 
		 
        
		  if(insercciones_realizadas_con_exito == true){
	      out.print("1;Ha realizado correctamente el odontograma con fecha "+fechacjmysql+" en la hora "+hracjmysql+";"+codInformeOdont);		  
		  }else{
		  out.print("0;Hubo un error al ingresar los datos al sistema!");
		  }
	    
	    }
	}

//}
