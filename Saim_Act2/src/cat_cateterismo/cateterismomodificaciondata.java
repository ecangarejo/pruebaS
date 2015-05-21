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

import org.apache.jasper.tagplugins.jstl.core.Out;

/**
 * Servlet implementation class cateterismomodificaciondata
 */
public class cateterismomodificaciondata extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public cateterismomodificaciondata() {
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
		System.out.print("entra aqui!!!!!!");
		int opcion =  Integer.parseInt(request.getParameter("opcion"));
		metodos_modificacion_cateterismo cateterismo = new metodos_modificacion_cateterismo();
	
		
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
					
	    else if (opcion ==3){
	    	
	    	//System.out.print("entra!!!!!!!!!!!!!!!!");
	    	
			String idInformeCateterismoModificacion = request.getParameter("codInformeaModificar");
			Vector <String> contenidoEncabezadoInforme = cateterismo.buscarinfoEncabezadoInforme(idInformeCateterismoModificacion);
			Vector <String> contenidoInformeGeneral = cateterismo.buscarContenidoGeneralHemodinamia(idInformeCateterismoModificacion);
	        Vector <String> contenidoInformeAnatomiaCoronaria = cateterismo.buscarContenidoAnatomiaCoronaria(idInformeCateterismoModificacion);
			Vector <String> dataVentriculografia = cateterismo.buscarDataInformeVentriculografia(idInformeCateterismoModificacion);
	        Vector <String> dataAortograma = cateterismo.buscarDataInformeAortograma(idInformeCateterismoModificacion);
	        Vector <String> dataDominancia = cateterismo.buscarDataDominanciaAnatomiaCoronaria(idInformeCateterismoModificacion);
	        Vector <String> dataArterias = cateterismo.buscarDataArteriasAnatomiaCoronaria(idInformeCateterismoModificacion);
	        Vector <String> dataCaracteristicasArterias =  cateterismo.buscarDataLesionesAnatomiaCoronaria(idInformeCateterismoModificacion);
	      
	        
			String cadenaencabezadoInforme ="";
			String cadenacontenidoInformeGeneral ="";
			String cadenacontenidoInformeAnatomiaCoronaria="";
			String cadenadataVentriculografia="";
			String cadenadataAortograma="";
			String cadenadataDominancia = dataDominancia.elementAt(0);
			String cadenadataarterias = "";
			String cadenadataCaracteristicasArterias = "";
			String inserccionespreviasrealizadas = "";
			int i =0;

			
			for(i=0; i<contenidoEncabezadoInforme.size();i++ ){
				cadenaencabezadoInforme +=contenidoEncabezadoInforme.elementAt(i)+"&";
				
			}
		
			
			
			i =0;
			for(i=0; i<contenidoInformeGeneral.size();i++ ){
				cadenacontenidoInformeGeneral +=contenidoInformeGeneral.elementAt(i)+"&";
				
			}
			
			i =0;
			for(i=0; i<contenidoInformeAnatomiaCoronaria.size();i++ ){
				cadenacontenidoInformeAnatomiaCoronaria +=contenidoInformeAnatomiaCoronaria.elementAt(i)+"&";
				
			}
			
			
			
			if (dataVentriculografia != null){
				i =0;
				for(i=0; i<dataVentriculografia.size();i++ ){
					cadenadataVentriculografia +=dataVentriculografia.elementAt(i)+"&";
					
				}
				cadenadataVentriculografia = cadenadataVentriculografia.substring(0,cadenadataVentriculografia.length()-1);
				inserccionespreviasrealizadas +="1&";
			}
			else{
			
				cadenadataVentriculografia = null;
				inserccionespreviasrealizadas +="0&";
			}
			
			if (dataAortograma != null){
				i =0;
				for(i=0; i<dataAortograma.size();i++ ){
					cadenadataAortograma +=dataAortograma.elementAt(i)+"&";
					
				}
				cadenadataAortograma = cadenadataAortograma.substring(0,cadenadataAortograma.length()-1);
				inserccionespreviasrealizadas +="1&";
			}
			else{
				cadenadataAortograma = null;
				inserccionespreviasrealizadas +="0&";
			}
			
			i =0;
			for(i=0; i<dataArterias.size();i++ ){
				cadenadataarterias +=dataArterias.elementAt(i)+"&";
				
			}
			
			i =0;
			for(i=0; i<dataCaracteristicasArterias.size();i++ ){
				cadenadataCaracteristicasArterias +=dataCaracteristicasArterias.elementAt(i)+"&";
				
			}
			
			inserccionespreviasrealizadas +="1";
			
			
			cadenaencabezadoInforme = cadenaencabezadoInforme.substring(0, cadenaencabezadoInforme.length()-1);
			cadenacontenidoInformeGeneral = cadenacontenidoInformeGeneral.substring(0,cadenacontenidoInformeGeneral.length()-1);
			cadenacontenidoInformeAnatomiaCoronaria = cadenacontenidoInformeAnatomiaCoronaria.substring(0, cadenacontenidoInformeAnatomiaCoronaria.length()-1);
			cadenadataarterias = cadenadataarterias.substring(0, cadenadataarterias.length()-1);
			cadenadataCaracteristicasArterias = cadenadataCaracteristicasArterias.substring(0,cadenadataCaracteristicasArterias.length()-1);
			
			Vector<String> arteriasbifurcadas =new Vector<String>();
			arteriasbifurcadas = cateterismo.arteriasbifurcadas();
			String cad1 ="";
			for (i = 0 ; i < arteriasbifurcadas.size(); i++){
				cad1 += arteriasbifurcadas.elementAt(i)+"@";
			}
			cad1 = cad1.substring(0, cad1.length()-1);
			
			Vector<String> lesionesporArteria =new Vector<String>();
			lesionesporArteria= cateterismo.tipolesionesArteriasGeneral();
			String cad2 ="";
			for (i = 0 ; i < lesionesporArteria.size(); i++){
			 	cad2 += lesionesporArteria.elementAt(i)+"@";
			}
			cad2 = cad2.substring(0, cad2.length()-1);
			String cad3= cad1 +"&"+ cad2;
			
			
		/*	System.out.print("todo bien: "+idInformeCateterismoModificacion+"\n\n");
			System.out.print("cadena encabezado: \n"+cadenaencabezadoInforme+"\n\n");
			System.out.print("cadena completa informe general:\n"+cadenacontenidoInformeGeneral+"\n\n");
			System.out.print("cadena completa informe Anatomia Coronaria:\n"+cadenacontenidoInformeAnatomiaCoronaria+"\n\n");
			System.out.print("cadena completa data Ventriculografia:\n"+cadenadataVentriculografia+"\n\n");
			System.out.print("cadena completa data Aortograma:\n"+cadenadataAortograma+"\n\n");
			System.out.print("cadena dominancia:\n"+ cadenadataDominancia +"\n\n");
			System.out.print("cadena Arterias flujo timi y lecho distal:\n"+ cadenadataarterias +"\n\n");
		    System.out.print("cadena Arterias Caracteristicas:\n "+cadenadataCaracteristicasArterias+"\n\n");*/
	    
	    
	   String cadenaenvioData = 
	   cadenaencabezadoInforme+"|"+
	   cadenadataVentriculografia+"|"+
	   cadenadataAortograma+"|"+
	   cadenadataDominancia+"|"+
	   cad3+"&"+ cadenadataarterias+"|"+
	   cadenadataCaracteristicasArterias+"|"+
	   cadenacontenidoInformeGeneral+"|"+
	   cadenacontenidoInformeAnatomiaCoronaria+"|"+
	   inserccionespreviasrealizadas
	   ;
	   out.print(cadenaenvioData);
	    }
		else if (opcion ==4){//insertar, modificar o eliminar a la base de datos
			
			/*Calendar c = new GregorianCalendar();
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
				*/
			String idInformeCateterismoModificacion = request.getParameter("idinforme");
		    String [] vectorInsercionesPrevias = request.getParameter("inserccionesprevias").split(",");

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
		
			
			estado_insercion_a_bd =	cateterismo.actualizar_informe_diagnostico_hemodinamia(idInformeCateterismoModificacion, anestesiologo, indicacion, estudiorealizado);//ok revisado
		
			
			estado_insercion_a_bd = cateterismo.actualizar_contenido_informe_hemodinamia(
	    	    		idInformeCateterismoModificacion, 
			    		informeventriculografia, 
			    		informeaortograma,
			    		resumeninforme, 
			    		conclusiones, 
			    		recomendaciones,
			    		informebypass
	    	    );
			
			estado_insercion_a_bd = cateterismo.actualizar_contenido_informe_Anatomia_Coronaria(
				idInformeCateterismoModificacion,
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
		 //  System.out.print("id del informe: "+idInformeCateterismoModificacion+"\n");
    	   
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
                	
             /*   System.out.print("ventriculoizqdilatado: "+ventriculoizqdilatado +  "\n");
                System.out.print("tipocontractivibilidad: "+tipocontractivibilidad +  "\n");
                System.out.print("caracteristicacontractivilidad: "+caracteristicacontractivilidad +  "\n");
                System.out.print("ventriculoizqdilatado: "+ventriculoizqdilatado +  "\n");
                System.out.print("lugarcontractibilidad: "+lugarcontractibilidad +  "\n");
                System.out.print("fevi: "+fevi +  "\n");
                System.out.print("insuficienciamitral: "+insuficienciamitral +  "\n");
                System.out.print("aspectovalvulamitral: "+aspectovalvulamitral +  "\n");*/
             
			   if(!cadenaimagenventriculografia.isEmpty()){
                	estado_insercion_a_bd= cateterismo.insertarImagenVentriculografia
                			(idInformeCateterismoModificacion,	
                			cadenaimagenventriculografia);	
                }
                
               
                if(Integer.parseInt(vectorInsercionesPrevias[0])== 1){// ventriculografia ya insertada se actualiza
                	 estado_insercion_a_bd=cateterismo.actualizar_informe_Ventriculografia(
                     		idInformeCateterismoModificacion,
                     		ventriculoizqdilatado,
                     		tipocontractivibilidad, 
                     		gravedadcontractibilidad,
                     		caracteristicacontractivilidad,
                     		lugarcontractibilidad,
                     		insuficienciamitral, 
                     		aspectovalvulamitral,
                        	fevi
                     		);
                }
                else{// ventriculografi aun no insertada de tiene que insertar
                	 estado_insercion_a_bd=cateterismo.insertarInformeVentriculografia
                			 (ventriculoizqdilatado, 
                			 tipocontractivibilidad, 
                			 gravedadcontractibilidad, 
                			 caracteristicacontractivilidad, 
                			 lugarcontractibilidad, 
                			 fevi, 
                			 insuficienciamitral,
                			 aspectovalvulamitral,
                			 idInformeCateterismoModificacion);
                }
                
               
			}
			else{
				
				 if(Integer.parseInt(vectorInsercionesPrevias[0])== 1){// ventriculografia ya insertada que se requiere eliminar
					 
					 cateterismo.eliminar_Informe_Ventriculografia(idInformeCateterismoModificacion); 
				     cateterismo.eliminar_imagen_Ventriculografia(idInformeCateterismoModificacion);
				 }
					
				
				
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
               
                if(Integer.parseInt(vectorInsercionesPrevias[1])== 1){// aortograma ya insertado
                	  estado_insercion_a_bd=cateterismo.actualizar_informe_Aortograma(
                      idInformeCateterismoModificacion,
                      raizaortica, 
                      raizaorticamedicion, 
                      aortaascendente,
                      aortaascendentemedicion,
                      clasevalulaaortica, 
                      aspectovalvulaaortica,
                      insuficienciaaortica, 
                      estenosisaortica,
                      gradientepicopico 
                 );
                }
                else{
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
                			  idInformeCateterismoModificacion);
                	
                	
                }
                
              
            }
            else{
            	estado_insercion_a_bd=cateterismo.eliminar_Informe_Aortograma(idInformeCateterismoModificacion);
            }
            
        	
            

            String cadenaimagenanatomiacoronaria = request.getParameter("cadenaimagenanatomiacoronaria").trim();
    		if(!cadenaimagenanatomiacoronaria.isEmpty()){
    			estado_insercion_a_bd= cateterismo.insertarImagenAnatomiaCoronaria(idInformeCateterismoModificacion, cadenaimagenanatomiacoronaria);
    		}
            
          //  if(Integer.parseInt(String.valueOf(request.getParameter("inserccionanatomiacoronaria")).trim())== 1){
            	String [] vectoridsarterias = request.getParameter("idsarteriasdelinforme").split(",");
            	String [] abreviacionesporarterias = request.getParameter("abreviacionesporarterias").split(",");
            	String [] flujotimilesiones = request.getParameter("flujotimilesiones").split(",");
        		String [] lechoditales = request.getParameter("lechodistal").split(",");
        		
            	String [] porcentaje_localizacion_lesiones = request.getParameter("porcentaje_localizacion_lesiones").split(",");
        	    String [] tipolesiones = request.getParameter("tipolesiones").split(",");
        		String [] lesionesporoclusion = request.getParameter("lesionesporoclusion").split(",");
        		String [] caracteristicaslesiones = request.getParameter("caracteristicaslesiones").split(",");
        		String [] lesionesbifurcadas = request.getParameter("lesionesbifurcadas").split(",");
        		String [] medinalesiones = request.getParameter("medinalesiones").split(",");
        		String [] circulacioncolaterallesiones = request.getParameter("circulacioncolaterallesiones").split(",");
        	
        		
        		//	String [] localizacioneslesionesgeneral =  request.getParameter("localizacioneslesionesgeneral").split(",");
           		
           		
           		
           		
           		
           		
           		
        		String dominancia =  request.getParameter("dominancia");
        		
        		estado_insercion_a_bd = cateterismo.actualizarDataDominanciaAnatomiaCoronaria
        				(idInformeCateterismoModificacion, dominancia);
        		
        		
        	
       
        	    int numero_Arterias =  vectoridsarterias.length;//cateterismo.numeroArteriasRegistradas();
        	    //System.out.println("!@#$%^&* "+numero_Arterias);
        	    
        	 Vector<String> lesionesporarteria = new Vector<String>();
        	    
        	    int i =0;
        	    int j = 0;
        	    while ((i < numero_Arterias)&&(estado_insercion_a_bd == true)){
        		
        	    	estado_insercion_a_bd= cateterismo.actualizarDatalechoflujoAnatomiaCoronaria(
        	    			vectoridsarterias[i], 
        	    			lechoditales[i],
        	    			flujotimilesiones[i]);
        	    	
        	    	lesionesporarteria =cateterismo.consultarIdsLesionesporArteria(abreviacionesporarterias[i]);
        	    	
        	        j = 0;
        	    	String [] arrayporcentajes =porcentaje_localizacion_lesiones[i].split("-");
        			String [] arraycaracteristicas=caracteristicaslesiones[i].split("-");
        			String [] arraylesionescolaterales=circulacioncolaterallesiones[i].split("-");
        			String [] arraylesionesoclusiones=lesionesporoclusion[i].split("-");
        			String [] arraytipolesiones=tipolesiones[i].split("-");
        			String [] arrayramasbifurcadas=lesionesbifurcadas[i].split("-");
        			String [] arraymedinas=medinalesiones[i].split("-");
        	    	
        	    	while (j < lesionesporarteria.size()){
        	    		
        	    		//vectoridsarterias[i]
        	    		
        	    		estado_insercion_a_bd = cateterismo.actualizarDataCaracterizacionAnatomiaCoronaria(
        	    				vectoridsarterias[i],
        	    				lesionesporarteria.elementAt(j), 
        	    				arrayporcentajes[j], 
        	    				arraycaracteristicas[j], 
        	    				arraylesionescolaterales[j],
        	    				arraylesionesoclusiones[j], 
        	    				arraytipolesiones[j], 
        	    				arrayramasbifurcadas[j], 
        	    				arraymedinas[j]);
        	    		j++;
        	    	}
        	    	
 

		    	i++;
			}
         //   }
         
            
           if (estado_insercion_a_bd == true){

    		//	System.out.print("codigo de ultimo informe realizado: "+idInforme +"\n");
            	out.print(idInformeCateterismoModificacion);		
			//	out.print("ha modificado correctamente el informe en "+fechacj +" a la hora "+hra);
            }
			else{
				out.print("0");	
			//	out.print("hubo un error al momento de ingresar su informe");
			}
			
		}
	}

}
