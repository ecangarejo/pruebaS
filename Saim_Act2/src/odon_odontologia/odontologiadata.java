package odon_odontologia;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class odontologiadata
 */
public class odontologiadata extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public odontologiadata() {
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
		int hora, minutos, segundos;
		hora =calendario.get(Calendar.HOUR_OF_DAY);
		minutos = calendario.get(Calendar.MINUTE);
		segundos = calendario.get(Calendar.SECOND);
		String hra= hora+":"+minutos+":"+segundos; 

		
		// obtencion de datos de js de odontologia
		
		int opcion =  Integer.parseInt(request.getParameter("opcion"));
	    String codigopaciente =  request.getParameter("codigopaciente");
	    String codigoOdontologo = request.getParameter("codigoodontologo");
		String motivoConsulta =  request.getParameter("motivo");
		String observaciones = request.getParameter("observaciones");
		String evolucion = request.getParameter("evolucion");
	    String estadoTratamiento=  request.getParameter("estadoTratamiento");
		String plandetratamiento = request.getParameter("plandetratamiento");
		String [] dataExamenclinico = request.getParameter("arrayExamenClinico").split(",");
		String [] dataAntecedentes = request.getParameter("arrayAntecedentes").split(",");
		String [] nombreDientes =  request.getParameter("arraynombresdientes").split(","); 
		String [] arregloDientes = request.getParameter("arrayImagenesDientes").split("@");
	    String [] arreglogeneralidadesobtenidas = request.getParameter("generalidades").split(",");
  
	    
	    metodos_Odontologia metodos = new metodos_Odontologia();
	    boolean swInserccion = true;
		
	    if(opcion ==1){//nuevo Tratamiento
		
		
		swInserccion = metodos.insertarTratamientoOdontologico(fechacjmysql,hra, codigopaciente, "1");
		}

		String idestudioactual = ""+metodos.ultimocodTratamientoOdontologicoActivo2(codigopaciente);
		swInserccion = metodos.insertarhistoriaOdontologica(fechacjmysql , codigoOdontologo, motivoConsulta, plandetratamiento, observaciones, evolucion, idestudioactual,hra);
		int idhistoriaactual = metodos.ultimocodHistoriaOdontologica(""+idestudioactual);
		
		if(estadoTratamiento.equals("0")){// SI el tratamiento ha se actualiza  
			swInserccion= metodos.actualizarTratamientoOdontologico(idestudioactual, fechacjmysql,hra, estadoTratamiento);
		}
		
		
		
		// System.out.print("inf actual "+idhistoriaactual);
		        swInserccion = metodos.insertarDataAntecedentesMedicos
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
				 ""+idhistoriaactual);
                swInserccion =metodos.insertardataexamenclinico(
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
                		""+idhistoriaactual);
                
                int i = 0;
                int j=0; 
                swInserccion = metodos.insertarOdontograma(""+idhistoriaactual);
                Vector<String> tiposgeneralidades = metodos.seleccionartiposdegeneralidades();
                Vector<String> direccionesgeneralidades = metodos.seleccionardireccionesdegeneralidades();
                Vector<String> caracteristicaspordiente = new Vector<String>();
               
                
                
                
                while (i<nombreDientes.length && swInserccion ==true){
               	swInserccion = metodos.actualizarOdontograma(nombreDientes[i], arregloDientes[i], ""+idhistoriaactual);
                String [] generalidadespordiente = arreglogeneralidadesobtenidas[i].split("-");
                j = 0;
                
                
                
            	//System.out.print((i+1)+"\n--------"+"\n");
                while (j < generalidadespordiente.length){

                
                //	System.out.print(generalidadespordiente[j]+"\n");
                	caracteristicaspordiente = metodos.caracterizacionpordiente(tiposgeneralidades, direccionesgeneralidades, generalidadespordiente[j]);
                	swInserccion = metodos.insertargeneralidadporDiente(caracteristicaspordiente.elementAt(0), caracteristicaspordiente.elementAt(1), ""+(i+1), "1",  ""+idhistoriaactual);
                //	System.out.print("--caracteristica: "+caracteristicaspordiente.elementAt(0)+"\n");
               // 	System.out.print("--direccion: "+caracteristicaspordiente.elementAt(1)+"\n");
                	j++;
                }

            	//System.out.print("\n\n");
               	
               	
               	i++;
                }
                
              if(swInserccion==true){
            	  
              	  out.print("1,informe realizado satisfactoriamente!,"+idestudioactual+","+idhistoriaactual);
              }
              else{
            	  out.print("0,hubo un error para realizar el informe");
              }
	
		
		
		
	
	
	
	}

}
