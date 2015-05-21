package cat_cateterismo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.misc.BASE64Decoder;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

/**
 * Servlet implementation class cateterismodata
 */
public class cateterismodata extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public cateterismodata() {
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
		PrintWriter out = response.getWriter();  //respuesta
		Calendar c = new GregorianCalendar();
		String dia = Integer.toString(c.get(Calendar.DATE));
		String mes = Integer.toString(c.get(Calendar.MONTH));
		String annio = Integer.toString(c.get(Calendar.YEAR));
		int m=Integer.parseInt(mes)+1;
		mes=String.valueOf(m);
		int d=Integer.parseInt(dia);
		if(d<10){dia="0"+d;}
		if(m<10){mes="0"+m;}
		String fechacjmysql=annio+"-"+mes+"-"+dia; //para la base de dtaos
		String fechacj=dia+"/"+mes+"/"+annio;
		
		
		
		Calendar calendario = Calendar.getInstance();
		//	Calendar calendario = new GregorianCalendar();
			int hora, minutos, segundos;
			hora =calendario.get(Calendar.HOUR_OF_DAY);
			minutos = calendario.get(Calendar.MINUTE);
			segundos = calendario.get(Calendar.SECOND);
			String hra= hora+":"+minutos+":"+segundos; 
		//	System.out.print(""+hra);
			// hora para base de datos
		    /*String dias=fechacj.substring(0, 2);
			String meses=fechacj.substring(3, 5);
			String anos=fechacj.substring(6, 10);
			String fec=anos+"-"+meses+"-"+dias;*/
			int opcion =  Integer.parseInt(request.getParameter("opcion"));
			metodos_cateterismo cateterismo = new metodos_cateterismo();
			if (opcion == 1){//consultar arterias bifurcadas y lesiones de arterias
				Vector<String> arteriasbifurcadas =new Vector<String>();
				arteriasbifurcadas = cateterismo.arteriasbifurcadas();
				String cad1 ="";
				for (int i = 0 ; i < arteriasbifurcadas.size(); i++){
					cad1 += arteriasbifurcadas.elementAt(i)+",";
				}
				cad1 = cad1.substring(0, cad1.length()-1);
				
				Vector<String> lesionesporArteria =new Vector<String>();
				lesionesporArteria= cateterismo.tipolesionesArteriasGeneral();
				String cad2 ="";
				for (int i = 0 ; i < lesionesporArteria.size(); i++){
				 	cad2 += lesionesporArteria.elementAt(i)+"*";
				}
				cad2 = cad2.substring(0, cad2.length()-1);
				String cad3= cad1 +"@"+ cad2;
				out.print(cad3);
			}
		    else if(opcion ==2){
				String arteriaseleccionada = request.getParameter("arteriaSeleccionada");
				Vector<String> lesionesporArteria =new Vector<String>();
				lesionesporArteria= cateterismo.tipolesionesArteriaActual(arteriaseleccionada);
				String cad ="";
				for (int i = 0 ; i < lesionesporArteria.size(); i++){
					cad += lesionesporArteria.elementAt(i)+",";
				}
				cad = cad.substring(0, cad.length()-1);
				out.print(cad);
			}
						
			else if (opcion ==3){//insertar a la base de datos
				String codpaciente   = request.getParameter("codpaciente");
				String codUsusario   = request.getParameter("codusuario");
				String anestesiologo = request.getParameter("anestesiologo");
				String estudiorealizado = request.getParameter("estudiorealizado"); 
				String indicacion = request.getParameter("indicacion");
				String resumeninforme = request.getParameter("resumeninforme").trim();
				
				String informeventriculografia= request.getParameter("informeventriculografia").trim();
				String informeaortograma = request.getParameter("informeaortograma").trim();
				String informebypass = request.getParameter("informebypass").trim();
				
				String informeanatomiacoronariaTCI = request.getParameter("informeanatomiacoronariaTCI").trim();
				String informeanatomiacoronariaADA = request.getParameter("informeanatomiacoronariaADA").trim();		
				String informeanatomiacoronariaAC =  request.getParameter("informeanatomiacoronariaAC").trim();		
				String informeanatomiacoronariaRI =  request.getParameter("informeanatomiacoronariaRI").trim();		
				String informeanatomiacoronariaACD = request.getParameter("informeanatomiacoronariaACD").trim();
				String informeanatomiacoronariaADP = request.getParameter("informeanatomiacoronariaADP").trim();
				String informeanatomiacoronariaTPL = request.getParameter("informeanatomiacoronariaTPL").trim();
				String informeanatamiacoronariaD1 = request.getParameter("informeanatamiacoronariaD1").trim();
				String informeanatamiacoronariaD2 = request.getParameter("informeanatamiacoronariaD2").trim();
				String informeanatamiacoronariaO1 = request.getParameter("informeanatamiacoronariaO1").trim();
				String informeanatamiacoronariaO2 = request.getParameter("informeanatamiacoronariaO2").trim();
				String conclusiones = request.getParameter("conclusiones").trim();
				String recomendaciones = request.getParameter("recomendaciones").trim();

				
				boolean estado_insercion_a_bd = true;
				estado_insercion_a_bd= cateterismo.insertarEncabesadoInformeHemodinamia(codUsusario, fechacjmysql, hra, codpaciente,anestesiologo,indicacion,estudiorealizado);
				String codInformeHemodinamiaActual = String.valueOf(cateterismo.codigoActualinformeHemodinamia());
				estado_insercion_a_bd = cateterismo.insertarContenidoGeneralInformeAnatomiaCoronaria(
					codInformeHemodinamiaActual,
					informeanatomiacoronariaTCI, 
					informeanatomiacoronariaADA, 
					informeanatomiacoronariaAC,
					informeanatomiacoronariaRI,
					informeanatomiacoronariaACD, 
					informeanatomiacoronariaADP,
					informeanatomiacoronariaTPL,
					informeanatamiacoronariaD1,
					informeanatamiacoronariaD2,
					informeanatamiacoronariaO1,
					informeanatamiacoronariaO2 
					);
			  
	    	    estado_insercion_a_bd = cateterismo.insertarContenidoGeneralInformeHemodinamia(
			    		codInformeHemodinamiaActual, 
			    		informeventriculografia, 
			    		informeaortograma,
			    		resumeninforme, 
			    		conclusiones, 
			    		recomendaciones,
			    		informebypass
	    	    		);

				if (Integer.parseInt(String.valueOf(request.getParameter("inserccionventriculografia")).trim())== 1){
					String ventriculoizqdilatado = request.getParameter("ventriculoizqdilatado");
					String tipocontractivibilidad=request.getParameter("tipocontractivibilidad");
					String gravedadcontractibilidad = request.getParameter("gravedadcontractibilidad");
					String caracteristicacontractivilidad =request.getParameter("caracteristicacontractivilidad");
	                String lugarcontractibilidad = request.getParameter("lugarcontractibilidad");
					String fevi = request.getParameter("fevi"); 
	                String insuficienciamitral = request.getParameter("insuficienciamitral");
	                String aspectovalvulamitral = request.getParameter("aspectovalvulamitral");
	                String cadenaimagenventriculografia = request.getParameter("cadenaimagenventriculografia").trim();
	                	//cadenaimagenventriculografia = "R0lGODlhCwAOAMQfAP////7+/vj4+Hh4eHd3d/v7+/Dw8HV1dfLy8ubm5vX19e3t7fr6+nl5edra2nZ2dnx8fMHBwYODg/b29np6eujo6JGRkeHh4eTk5LCwsN3d3dfX13Jycp2dnevr6////yH5BAEAAB8ALAAAAAALAA4AAAVq4NFw1DNAX/o9imAsBtKpxKRd1+YEWUoIiUoiEWEAApIDMLGoRCyWiKThenkwDgeGMiggDLEXQkDoThCKNLpQDgjeAsY7MHgECgx8YR8oHwNHfwADBACGh4EDA4iGAYAEBAcQIg0DkgcEIQA7";
	                	
	              /*System.out.print("ventriculoizqdilatado: "+ventriculoizqdilatado +  "\n");
	                System.out.print("tipocontractivibilidad: "+tipocontractivibilidad +  "\n");
	                System.out.print("caracteristicacontractivilidad: "+caracteristicacontractivilidad +  "\n");
	                System.out.print("ventriculoizqdilatado: "+ventriculoizqdilatado +  "\n");
	                System.out.print("lugarcontractibilidad: "+lugarcontractibilidad +  "\n");
	                System.out.print("fevi: "+fevi +  "\n");
	                System.out.print("insuficienciamitral: "+insuficienciamitral +  "\n");
	                System.out.print("aspectovalvulamitral: "+aspectovalvulamitral +  "\n");*/
	                if(!cadenaimagenventriculografia.isEmpty()){
	                	estado_insercion_a_bd= cateterismo.insertarImagenVentriculografia(codInformeHemodinamiaActual, cadenaimagenventriculografia);	
	                }
	                
	                
	                estado_insercion_a_bd=cateterismo.insertarInformeVentriculografia(
	                		ventriculoizqdilatado,
	                		tipocontractivibilidad, 
	                		gravedadcontractibilidad,
	                		caracteristicacontractivilidad,
	                		lugarcontractibilidad,
	                		fevi, 
	                		insuficienciamitral, 
	                		aspectovalvulamitral,
	                		codInformeHemodinamiaActual
	                	
	                		);
				}
	            if (Integer.parseInt(String.valueOf(request.getParameter("inserccionaortograma")).trim())==1){
	            	String raizaortica =request.getParameter("raizaortica");
	            	String raizaorticamedicion = request.getParameter("raizaorticamedicion");
	            	String aortaascendente = request.getParameter("aortaascendente");
	            	String aortaascendentemedicion =  request.getParameter("aortaascendentemedicion");
	            	String clasevalulaaortica = request.getParameter("clasevalulaaortica");
	            	String aspectovalvulaaortica = request.getParameter("aspectovalvulaaortica");
	                String insuficienciaaortica = request.getParameter("insuficienciaaortica");
	                String estenosisaortica = request.getParameter("estenosisaotica");
	                String gradientepicopico = request.getParameter("gradientepicopico");
	                estado_insercion_a_bd=cateterismo.insertarInformeAortograma(
	                raizaortica, 
	                raizaorticamedicion, 
	                aortaascendente,
	                aortaascendentemedicion,
	                clasevalulaaortica, 
	                aspectovalvulaaortica,
	                insuficienciaaortica, 
	                estenosisaortica,
	                gradientepicopico, 
	                codInformeHemodinamiaActual);
	            }

	            String cadenaimagenanatomiacoronaria = request.getParameter("cadenaimagenanatomiacoronaria").trim();
        		if(!cadenaimagenanatomiacoronaria.isEmpty()){
        			estado_insercion_a_bd= cateterismo.insertarImagenAnatomiaCoronaria(codInformeHemodinamiaActual, cadenaimagenanatomiacoronaria);
        		}
	            
	          //  if(Integer.parseInt(String.valueOf(request.getParameter("inserccionanatomiacoronaria")).trim())== 1){
	        		String [] porcentaje_localizacion_lesiones = request.getParameter("porcentaje_localizacion_lesiones").split(",");
	        	    String [] tipolesiones = request.getParameter("tipolesiones").split(",");
	        		String [] lesionesporoclusion = request.getParameter("lesionesporoclusion").split(",");
	        		String [] caracteristicaslesiones = request.getParameter("caracteristicaslesiones").split(",");
	        		String [] flujotimilesiones = request.getParameter("flujotimilesiones").split(",");
	        		String [] lesionesbifurcadas = request.getParameter("lesionesbifurcadas").split(",");
	        		String [] lechoditales = request.getParameter("lechodistal").split(",");
	        		String [] medinalesiones = request.getParameter("medinalesiones").split(",");
	        		String [] circulacioncolaterallesiones = request.getParameter("circulacioncolaterallesiones").split(",");
	           		String [] localizacioneslesionesgeneral =  request.getParameter("localizacioneslesionesgeneral").split(",");
	        	/*	String auxiliar1="";
	        		String auxiliar2="";
	        		String auxiliar3="";
	        		String auxiliar4="";
	        		String auxiliar5="";
	        		String auxiliar6="";
	        		String auxiliar7="";
	        		String auxiliar8="";
	        		String auxiliar9="";
	        		String auxiliar10="";
	        		
	        		
	           		for (int f = 0; f < 11 ; f++ ){
	           			
	           	 		 auxiliar1 +=porcentaje_localizacion_lesiones[f]+"@";
		        		 auxiliar2+=tipolesiones[f]+"@";
		        		 auxiliar3+=lesionesporoclusion[f]+"@";
		        		 auxiliar4+=caracteristicaslesiones[f]+"@";
		        		 auxiliar5+=flujotimilesiones[f]+"@";
		        		 auxiliar6+=lesionesbifurcadas[f]+"@";
		        		 auxiliar7+=lechoditales[f]+"@";
		        		 auxiliar8+=medinalesiones[f]+"@";
		        		 auxiliar9+=circulacioncolaterallesiones[f]+"@";
		        		 auxiliar10+=localizacioneslesionesgeneral[f]+"@";
	           		}
	           		
	           		
	           		System.out.print("porcentaje_localizacion_lesiones ["+auxiliar1+"]\n");
	           		System.out.print("tipolesiones ["+auxiliar2+"]\n");
	           		System.out.print("lesionesporoclusion ["+auxiliar3+"]\n");
	           		System.out.print("caracteristicaslesiones ["+auxiliar4+"]\n");
	           		System.out.print("flujotimilesiones ["+auxiliar5+"]\n");
	           		System.out.print("lesionesbifurcadas ["+auxiliar6+"]\n");
	           		System.out.print("lechoditales ["+auxiliar7+"]\n");
	           		System.out.print("medinalesiones ["+auxiliar8+"]\n");
	           		System.out.print("circulacioncolaterallesiones ["+auxiliar9+"]\n");
	           		System.out.print("localizacioneslesionesgeneral ["+auxiliar10+"]\n");
	           		System.out.print("\n");*/
	           		
	           		
	        		String dominancia =  request.getParameter("dominancia");
	        		
	        		estado_insercion_a_bd = cateterismo.insertarInformeAnatomiaCoronaria(codInformeHemodinamiaActual,dominancia);
	        		int i =0;
	        		String idinformeanatomiacoronaria = String.valueOf(cateterismo.codigoActualinformeAnatomiaCoronaria());
	        		String idlesionactual ="";
	        	    int numero_Arterias = cateterismo.numeroArteriasRegistradas();
	        	   
	        	    while ((i < numero_Arterias)&&(estado_insercion_a_bd == true)){
	        		estado_insercion_a_bd =cateterismo.insertarlesionArteria(
	        					flujotimilesiones[i], 
	        					lechoditales[i],
	        					""+(i+1), 
	        					idinformeanatomiacoronaria);
	        	   		idlesionactual = String.valueOf(cateterismo.codigoActualLesionAnatomiaCoronaria());
	    		    	
	        	   		String [] arraylocalizaciones  = localizacioneslesionesgeneral[i].split("-");
	        	   		String [] arrayporcentajes =porcentaje_localizacion_lesiones[i].split("-");
	        			String [] arraycaracteristicas=caracteristicaslesiones[i].split("-");
	        			String [] arraylesionescolaterales=circulacioncolaterallesiones[i].split("-");
	        			String [] arraylesionesoclusiones=lesionesporoclusion[i].split("-");
	        			String [] arraytipolesiones=tipolesiones[i].split("-");
	        			String [] arrayramasbifurcadas=lesionesbifurcadas[i].split("-");
	        			String [] arraymedinas=medinalesiones[i].split("-");
	        	   		
	        	   		
	        	   	//	System.out.print("idlesion actual: "+idlesionactual +"\n");
	        	   		int j = 0;
	        	   		while (j < arraylocalizaciones.length){
	        	   			String cadcodarteria= String.valueOf(cateterismo.idlocalizacionlesionarteria(arraylocalizaciones[j]));
	        	   			cateterismo.insertarCaracterizacionLesion(idlesionactual, cadcodarteria, arrayporcentajes[j], arraycaracteristicas[j], arraylesionescolaterales[j], arraylesionesoclusiones[j], arraytipolesiones[j], arrayramasbifurcadas[j], arraymedinas[j]);
	        	   			j++;
	        	   		}
			    	i++;
				}
	         //   }
	            if (estado_insercion_a_bd == true){
	            	int idInforme = cateterismo.consultarIdUltimoInforme(fechacjmysql,hra);
	    	//		System.out.print("codigo de ultimo informe realizado: "+idInforme +"\n");
	            	out.print(idInforme);	
					//out.print("ha realizado correctamente el informe con fecha "+fechacj +" en la hora "+hra);
	            }
				else{
					out.print("0");	
					//out.print("hubo un error al momento de ingresar su informe");
				}
				
			}
			
		
      }
}