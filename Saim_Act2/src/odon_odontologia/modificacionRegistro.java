package odon_odontologia;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class modificacionRegistro
 */
public class modificacionRegistro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public modificacionRegistro() {
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
		// TODO Auto-generated method stub
     	response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter(); // respuesta
		String codTratamiento = request.getParameter("CodTratamiento");
		String codHistoria = request.getParameter("CodHistoria");
		String va = request.getParameter("valor");
  		metodos_Odontologia metodos = new metodos_Odontologia();
       	String examenclinico= metodos.seleccionardataexamenclinico(codHistoria);
		String cadAntecedentesprevios = metodos.seleccionarDataAntecedentesMedicos(codHistoria);
		String detallesHistoria = metodos.seleccionarDetalleHistoria(codHistoria);
	    Vector <String> codHistoriasPosteriores = metodos.obtenerOdontogramasSuperiores(codTratamiento, codHistoria);
		String cadenaMatrizGeneralidades = "";
		for(int x = 0; x < codHistoriasPosteriores.size(); x++ ){
			
			cadenaMatrizGeneralidades += metodos.obtenerlaexistenciadegeneralidadesdientes(codHistoriasPosteriores.elementAt(x))+";";
		}
		
		boolean generalidadesEncontradas = false;
		
		if(cadenaMatrizGeneralidades.length() > 0){
			cadenaMatrizGeneralidades = cadenaMatrizGeneralidades.substring(0,cadenaMatrizGeneralidades.length()-1);
			generalidadesEncontradas = true;
			//System.out.print(cadenaMatrizGeneralidades.toString());
		}
				
				
	    
		
		int numero_dientes = metodos.numeroDientes();
		if(va.equals("1")){//recopilar datos
			 String cadGeneralidades ="";
			    for (int i = 0; i < numero_dientes;i++){
					cadGeneralidades += metodos.seleccionargeneralidadporDiente(codHistoria, String.valueOf((i+1)))+"@";
				}
			    cadGeneralidades = cadGeneralidades.substring(0, cadGeneralidades.length()-1);
				
			   /* System.out.print(examenclinico +"|");
			    System.out.print(cadAntecedentesprevios+"|\n ");
			    System.out.print(detallesHistoria+"|\n ");
			    System.out.print(cadGeneralidades+"|\n ");*/
			    
			   String cadevolucion = metodos.evolucionHistoriaOdontologica2(codTratamiento, codHistoria);
			   String historiaspost ="";
			   if(cadenaMatrizGeneralidades.length()>0){
				   historiaspost = codHistoriasPosteriores.toString().substring(1, codHistoriasPosteriores.toString().length()-1);
			   }
			  String nombretecnicodientes = metodos.nombretecnicodientes(); 
			   
			  String generalidades= metodos.obtenernombreGeneralidades();
			  out.print(cadGeneralidades +"|"+examenclinico+"|"+cadAntecedentesprevios+"|"+detallesHistoria+"|"+cadevolucion+"|"+generalidadesEncontradas+"|"+cadenaMatrizGeneralidades+"|"+generalidades+"|"+historiaspost+"|"+nombretecnicodientes);
			    
		}
		
		else if(va.equals("2")){ //modificacion de historia odontologica actual
			String motivoConsulta =  request.getParameter("motivo");
			String observaciones = request.getParameter("observaciones");
			String evolucion = request.getParameter("evolucion");
			String plandetratamiento = request.getParameter("plandetratamiento");
			String [] dataExamenclinico = request.getParameter("arrayExamenClinico").split(",");
			String [] dataAntecedentes = request.getParameter("arrayAntecedentes").split(",");
			String [] nombreDientes =  request.getParameter("arraynombresdientes").split(","); 
			String [] arregloDientes = request.getParameter("arrayImagenesDientes").split("@");
		    String [] arreglogeneralidadesobtenidas = request.getParameter("generalidades").split(",");
		    boolean swActualizacion = true;
		    String [] generalidadespordiente ;
		    Vector<String> tiposgeneralidades = metodos.seleccionartiposdegeneralidades();
            Vector<String> direccionesgeneralidades = metodos.seleccionardireccionesdegeneralidades();
            Vector<String> caracteristicaspordiente = new Vector<String>();
            for(int i = 0 ; i < arreglogeneralidadesobtenidas.length; i++){// 52 dientes del odontograma
            	
            	if(!arreglogeneralidadesobtenidas[i].equals("sin_cambios")){// generalidad del diente
            		
            		swActualizacion = metodos.actualizarOdontograma(nombreDientes[i], arregloDientes[i], codHistoria);	
            	    generalidadespordiente = arreglogeneralidadesobtenidas[i].split("-");
            	
            	    int k = 0;
                	while (k < generalidadespordiente.length){
    					caracteristicaspordiente = metodos.caracterizacionpordiente(tiposgeneralidades, direccionesgeneralidades, generalidadespordiente[k]);
                       	swActualizacion = metodos.insertargeneralidadporDiente(caracteristicaspordiente.elementAt(0), caracteristicaspordiente.elementAt(1), ""+(i+1), "1",  ""+codHistoria);
    					k++;
    			}
            	    
            	}
              
            	
            	
            }
            
            swActualizacion = metodos.actualizarhistoriaOdontologica(
		    		motivoConsulta, 
		    		plandetratamiento, 
		    		observaciones,
		    		evolucion, 
		    		codHistoria);
		    
		    
		    swActualizacion = metodos.actualizarDataAntecedentesMedicos
				(dataAntecedentes[0], 
				 dataAntecedentes[1], 
				 dataAntecedentes[2], 
				 dataAntecedentes[3], 
				 dataAntecedentes[4], 
				 dataAntecedentes[5], 
				 dataAntecedentes[6], 
				 dataAntecedentes[7], 
				 dataAntecedentes[8], 
				 dataAntecedentes[9], 
				 dataAntecedentes[10], 
				 dataAntecedentes[11], 
				 dataAntecedentes[12], 
				 dataAntecedentes[13], 
				 dataAntecedentes[14], 
				 dataAntecedentes[15], 
				 dataAntecedentes[16], 
				 dataAntecedentes[17], 
				 codHistoria);
		    
		    
		    
		    swActualizacion =metodos.actualizardataexamenclinico(
             		dataExamenclinico[0], 
              		dataExamenclinico[1],
              		dataExamenclinico[2],
              		dataExamenclinico[3], 
              		dataExamenclinico[4], 
              		dataExamenclinico[5], 
              		dataExamenclinico[6], 
              		dataExamenclinico[7], 
              		dataExamenclinico[8],
              		dataExamenclinico[9],
              		dataExamenclinico[10], 
              		dataExamenclinico[11], 
              		dataExamenclinico[12],
              		dataExamenclinico[13], 
              		dataExamenclinico[14], 
              		dataExamenclinico[15],
              		dataExamenclinico[16], 
              		dataExamenclinico[17], 
              		dataExamenclinico[18],
              		dataExamenclinico[19], 
              		dataExamenclinico[20], 
              		dataExamenclinico[21],
              		dataExamenclinico[22],
              		dataExamenclinico[23],
              		codHistoria);
	           
		 
               
               if (swActualizacion ==true){
            	   out.print("1|la actualizacion fue realizada exitosamente|"+codTratamiento+"|"+codHistoria);
            	   
               }
               else{
            	   out.print("0|hubo un error en la actualizacion|");
            	   
               }
            
			
		}
		
		
		else if(va.equals("3")){//modificacion del odontograma actual y superiores
			String motivoConsulta =  request.getParameter("motivo");
			String observaciones = request.getParameter("observaciones");
			String evolucion = request.getParameter("evolucion");
			String plandetratamiento = request.getParameter("plandetratamiento");
			String [] dataExamenclinico = request.getParameter("arrayExamenClinico").split(",");
			String [] dataAntecedentes = request.getParameter("arrayAntecedentes").split(",");
			String [] nombreDientes =  request.getParameter("arraynombresdientes").split(","); 
			String [] arregloDientes = request.getParameter("arrayImagenesDientes").split("@");
		    String [] arreglogeneralidadesobtenidas = request.getParameter("generalidades").split(",");
			
		    boolean swActualizacion = true;
		    codHistoriasPosteriores.add(codHistoria);
		    String [] generalidadespordiente ;
		    Vector<String> tiposgeneralidades = metodos.seleccionartiposdegeneralidades();
            Vector<String> direccionesgeneralidades = metodos.seleccionardireccionesdegeneralidades();
            Vector<String> caracteristicaspordiente = new Vector<String>();
            
            for(int i = 0 ; i < arreglogeneralidadesobtenidas.length; i++){// 52 dientes del odontograma
         		if(!arreglogeneralidadesobtenidas[i].equals("sin_cambios")){// generalidad del diente
				for(int j=0 ; j < codHistoriasPosteriores.size();j++){// historias a actualizar contando la actual
					swActualizacion = metodos.actualizarOdontograma(nombreDientes[i], arregloDientes[i], codHistoriasPosteriores.elementAt(j));	
				    int k = 0;
				    swActualizacion = metodos.cambiarestadogeneralidaddiente(codHistoriasPosteriores.elementAt(j), "0",""+(i+1));
				    generalidadespordiente = arreglogeneralidadesobtenidas[i].split("-");
				while (k < generalidadespordiente.length){
						caracteristicaspordiente = metodos.caracterizacionpordiente(tiposgeneralidades, direccionesgeneralidades, generalidadespordiente[k]);
	                   	swActualizacion = metodos.insertargeneralidadporDiente(caracteristicaspordiente.elementAt(0), caracteristicaspordiente.elementAt(1), ""+(i+1), "1",  ""+codHistoriasPosteriores.elementAt(j));
						k++;
				}
    		}
		}
   
        
				
		}
			
		    
		    swActualizacion = metodos.actualizarhistoriaOdontologica(
		    		motivoConsulta, 
		    		plandetratamiento, 
		    		observaciones,
		    		evolucion, 
		    		codHistoria);
		    
		    
		    swActualizacion = metodos.actualizarDataAntecedentesMedicos
				(dataAntecedentes[0], 
				 dataAntecedentes[1], 
				 dataAntecedentes[2], 
				 dataAntecedentes[3], 
				 dataAntecedentes[4], 
				 dataAntecedentes[5], 
				 dataAntecedentes[6], 
				 dataAntecedentes[7], 
				 dataAntecedentes[8], 
				 dataAntecedentes[9], 
				 dataAntecedentes[10], 
				 dataAntecedentes[11], 
				 dataAntecedentes[12], 
				 dataAntecedentes[13], 
				 dataAntecedentes[14], 
				 dataAntecedentes[15], 
				 dataAntecedentes[16], 
				 dataAntecedentes[17], 
				 codHistoria);
		    
		    
		    
		    swActualizacion =metodos.actualizardataexamenclinico(
             		dataExamenclinico[0], 
              		dataExamenclinico[1],
              		dataExamenclinico[2],
              		dataExamenclinico[3], 
              		dataExamenclinico[4], 
              		dataExamenclinico[5], 
              		dataExamenclinico[6], 
              		dataExamenclinico[7], 
              		dataExamenclinico[8],
              		dataExamenclinico[9],
              		dataExamenclinico[10], 
              		dataExamenclinico[11], 
              		dataExamenclinico[12],
              		dataExamenclinico[13], 
              		dataExamenclinico[14], 
              		dataExamenclinico[15],
              		dataExamenclinico[16], 
              		dataExamenclinico[17], 
              		dataExamenclinico[18],
              		dataExamenclinico[19], 
              		dataExamenclinico[20], 
              		dataExamenclinico[21],
              		dataExamenclinico[22],
              		dataExamenclinico[23],
              		codHistoria);
	           
		 
               
               if (swActualizacion ==true){
            	   out.print("1|la actualizacion fue realizada exitosamente|"+codTratamiento+"|"+codHistoria);
            	   
               }
               else{
            	   out.print("0|hubo un error en la actualizacion|");
            	   
               }
               
		}
	}
}
