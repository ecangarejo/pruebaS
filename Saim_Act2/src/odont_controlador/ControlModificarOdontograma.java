package odont_controlador;

import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;

import odont_metodo.MetodoCrearOdontograma;
import odont_metodo.MetodoModificarOdontograma;

public class ControlModificarOdontograma extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public ControlModificarOdontograma() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();  //respuesta
		
		int opcion =  Integer.parseInt(request.getParameter("opcion"));
		MetodoModificarOdontograma odont = new MetodoModificarOdontograma();
		
		
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
		
		
		if (opcion == 1){
			String codinforme = request.getParameter("codinforme");
			//informe general
			
			//System.out.print("entra aqui!!!!!! codigo="+codinforme);
			//String codpac = request.getParameter("codpaciente");
			Vector <String> contenidoInformeGeneral = odont.buscar_informe_general(codinforme);
			String cadenacontenidoInformeGeneral ="";
			//alergias
			Vector <String> Alergias = odont.buscar_tipo_antecedente("1", codinforme);
			String cadenaAlergias ="";
			//hemorragias
			Vector <String> Hemorragias = odont.buscar_tipo_antecedente("2", codinforme);
			String cadenaHemorragias ="";
			//enfermedades respiratorias
			Vector <String> enf_respiratorias = odont.buscar_tipo_antecedente("3", codinforme);
			String cadenaenf_respiratorias ="";
			//cardiopatias
			Vector <String> cardiopatias = odont.buscar_tipo_antecedente("4", codinforme);
			String cadenacardiopatias ="";
			//fiebre reumatica
			Vector <String> fiebre_reumatica = odont.buscar_tipo_antecedente("5", codinforme);
			String cadenafiebre_reumatica ="";
			//enfermedades renales
			Vector <String> enf_renales = odont.buscar_tipo_antecedente("6", codinforme);
			String cadenaenf_renales ="";
			//hepatitis
			Vector <String> hepatitis = odont.buscar_tipo_antecedente("7", codinforme);
			String cadenahepatitis ="";
			//transtornos gastricos
			Vector <String> trans_gastricos = odont.buscar_tipo_antecedente("8", codinforme);
			String cadenatrans_gastricos ="";
			//tension arterial
			Vector <String> tension_arterial = odont.buscar_tipo_antecedente("9", codinforme);
			String cadenatension_arterial ="";
			//diabetes
			Vector <String> diabetes = odont.buscar_tipo_antecedente("10", codinforme);
			String cadenadiabetes ="";
			//ingesta medicamentos
			Vector <String> medicamentos = odont.buscar_tipo_antecedente("11", codinforme);
			String cadenamedicamentos ="";
			//cirugias
			Vector <String> cirugias = odont.buscar_tipo_antecedente("12", codinforme);
			String cadenacirugias ="";
			//protesis
			Vector <String> protesis = odont.buscar_tipo_antecedente("13", codinforme);
			String cadenaprotesis ="";
			//hiv
			Vector <String> hiv = odont.buscar_tipo_antecedente("14", codinforme);
			String cadenahiv ="";
			//extracciones dentales
			Vector <String> extracciones = odont.buscar_tipo_antecedente("15", codinforme);
			String cadenaextracciones ="";
			//enfermedades orales
			Vector <String> enf_orales = odont.buscar_tipo_antecedente("16", codinforme);
			String cadenaenf_orales ="";
			//antecedentes familiares
			Vector <String> ant_familiares = odont.buscar_tipo_antecedente("17", codinforme);
			String cadenaant_familiares ="";
			//antecedentes medicos otros
			Vector <String> med_otros = odont.buscar_tipo_antecedente("18", codinforme);
			String cadenamed_otros ="";
			
			//datos examen clinico
			
			//lengua
			Vector <String> lengua = odont.buscar_examen_clinico("1", codinforme);
			String cadenalengua ="";
			//carrillos
			Vector <String> carillos = odont.buscar_examen_clinico("2", codinforme);
			String cadenacarillos ="";
			//abrasion
			Vector <String> abrasion = odont.buscar_examen_clinico("3", codinforme);
			String cadenaabrasion ="";
			//atricion
			Vector <String> atricion = odont.buscar_examen_clinico("4", codinforme);
			String cadenaatricion ="";
			//hipoplasias
			Vector <String> hipoplasias = odont.buscar_examen_clinico("5", codinforme);
			String cadenahipoplasias ="";
			//gingivitis marginal
			Vector <String> gin_marginal = odont.buscar_examen_clinico("6", codinforme);
			String cadenagin_marginal ="";
			//gingivitis difusa
			Vector <String> gin_difusa = odont.buscar_examen_clinico("7", codinforme);
			String cadenagin_difusa ="";
			//micrognasia
			Vector <String> micrognasia = odont.buscar_examen_clinico("8", codinforme);
			String cadenamicrognasia ="";
			//macrognasia
			Vector <String> macrognasia = odont.buscar_examen_clinico("9", codinforme);
			String cadenamacrognasia ="";
			//desviacion linea media
			Vector <String> desviacionlm = odont.buscar_examen_clinico("10", codinforme);
			String cadenadesviacionlm ="";
			//malposicion
			Vector <String> malposicion = odont.buscar_examen_clinico("11", codinforme);
			String cadenamalposicion ="";
			//malposicion
			Vector <String> atm = odont.buscar_examen_clinico("12", codinforme);
			String cadenaatm ="";
			//habitos orales
			Vector <String> hab_orales = odont.buscar_examen_clinico("13", codinforme);
			String cadenahab_orales ="";
			//paladar blando
			Vector <String> paladar_blando = odont.buscar_examen_clinico("14", codinforme);
			String cadenapaladar_blando ="";
			//piso de boca
			Vector <String> piso_boca = odont.buscar_examen_clinico("15", codinforme);
			String cadenapiso_boca ="";
			//supernumerarios
			Vector <String> supernumerarios = odont.buscar_examen_clinico("16", codinforme);
			String cadenasupernumerarios ="";
			//hipodoncia
			Vector <String> hipodoncia = odont.buscar_examen_clinico("17", codinforme);
			String cadenahipodoncia ="";
			//fracturas
			Vector <String> fracturas = odont.buscar_examen_clinico("18", codinforme);
			String cadenafracturas ="";
			//periodontitis
			Vector <String> periodontitis = odont.buscar_examen_clinico("19", codinforme);
			String cadenaperiodontitis="";
			//retracciones
			Vector <String> retracciones = odont.buscar_examen_clinico("20", codinforme);
			String cadenaretracciones="";
			//torus
			Vector <String> torus = odont.buscar_examen_clinico("21", codinforme);
			String cadenatorus="";
			//sobremordida vertical
			Vector <String> sob_vertical = odont.buscar_examen_clinico("22", codinforme);
			String cadenasob_vertical="";
			//sobremordida horizontal
			Vector <String> sob_horizontal = odont.buscar_examen_clinico("23", codinforme);
			String cadenasob_horizontal="";
			//examen clinico otros
			Vector <String> ex_otros = odont.buscar_examen_clinico("24", codinforme);
			String cadenaex_otros="";
			
			
			//cadena que almacenara todo el informe
			String cadenaInforme ="";
			
			int i =0;
			for(i=0; i<contenidoInformeGeneral.size();i++ ){
				cadenacontenidoInformeGeneral +=contenidoInformeGeneral.elementAt(i)+"&";
				
			}
			i=0;
			for(i=0; i<Alergias.size();i++ ){
				cadenaAlergias +=Alergias.elementAt(i)+"&";
				
			}
			 i =0;
			for(i=0; i<Hemorragias.size();i++ ){
				cadenaHemorragias +=Hemorragias.elementAt(i)+"&";
				
			}
			
			 i =0;
				for(i=0; i<enf_respiratorias.size();i++ ){
					cadenaenf_respiratorias +=enf_respiratorias.elementAt(i)+"&";
					
				}
				i =0;
				for(i=0; i<cardiopatias.size();i++ ){
					cadenacardiopatias +=cardiopatias.elementAt(i)+"&";
					
				}
				
				i =0;
				for(i=0; i<fiebre_reumatica.size();i++ ){
					cadenafiebre_reumatica +=fiebre_reumatica.elementAt(i)+"&";
					
				}
				
				i =0;
				for(i=0; i<enf_renales.size();i++ ){
					cadenaenf_renales +=enf_renales.elementAt(i)+"&";
					
				}
				
				i =0;
				for(i=0; i<hepatitis.size();i++ ){
					cadenahepatitis +=hepatitis.elementAt(i)+"&";
					
				}
				
				i =0;
				for(i=0; i<trans_gastricos.size();i++ ){
					cadenatrans_gastricos +=trans_gastricos.elementAt(i)+"&";
					
				}
				
				i =0;
				for(i=0; i<tension_arterial.size();i++ ){
					cadenatension_arterial +=tension_arterial.elementAt(i)+"&";
					
				}
				
				i =0;
				for(i=0; i<diabetes.size();i++ ){
					cadenadiabetes +=diabetes.elementAt(i)+"&";
					
				}
				
				i =0;
				for(i=0; i<medicamentos.size();i++ ){
					cadenamedicamentos +=medicamentos.elementAt(i)+"&";
					
				}
				
				i =0;
				for(i=0; i<cirugias.size();i++ ){
					cadenacirugias +=cirugias.elementAt(i)+"&";
					
				}
				
				i =0;
				for(i=0; i<protesis.size();i++ ){
					cadenaprotesis +=protesis.elementAt(i)+"&";
					
				}
				
				i =0;
				for(i=0; i<hiv.size();i++ ){
					cadenahiv +=hiv.elementAt(i)+"&";
					
				}
				
				i =0;
				for(i=0; i<extracciones.size();i++ ){
					cadenaextracciones +=extracciones.elementAt(i)+"&";
					
				}
				
				i =0;
				for(i=0; i<enf_orales.size();i++ ){
					cadenaenf_orales +=enf_orales.elementAt(i)+"&";
					
				}
				
				i =0;
				for(i=0; i<ant_familiares.size();i++ ){
					cadenaant_familiares +=ant_familiares.elementAt(i)+"&";
					
				}
				
				i =0;
				for(i=0; i<med_otros.size();i++ ){
					cadenamed_otros +=med_otros.elementAt(i)+"&";
					
				}
				
				i =0;
				for(i=0; i<lengua.size();i++ ){
					cadenalengua +=lengua.elementAt(i)+"&";
					
				}
				
				i =0;
				for(i=0; i<carillos.size();i++ ){
					cadenacarillos +=carillos.elementAt(i)+"&";
					
				}
				
				i =0;
				for(i=0; i<abrasion.size();i++ ){
					cadenaabrasion +=abrasion.elementAt(i)+"&";
					
				}
				
				i =0;
				for(i=0; i<atricion.size();i++ ){
					cadenaatricion +=atricion.elementAt(i)+"&";
					
				}
				
				i =0;
				for(i=0; i<hipoplasias.size();i++ ){
					cadenahipoplasias +=hipoplasias.elementAt(i)+"&";
					
				}
				
				i =0;
				for(i=0; i<gin_marginal.size();i++ ){
					cadenagin_marginal +=gin_marginal.elementAt(i)+"&";
					
				}
				
				i =0;
				for(i=0; i<gin_difusa.size();i++ ){
					cadenagin_difusa +=gin_difusa.elementAt(i)+"&";
					
				}
				
				i =0;
				for(i=0; i<micrognasia.size();i++ ){
					cadenamicrognasia +=micrognasia.elementAt(i)+"&";
					
				}
				
				i =0;
				for(i=0; i<macrognasia.size();i++ ){
					cadenamacrognasia +=macrognasia.elementAt(i)+"&";
					
				}
				
				i =0;
				for(i=0; i<desviacionlm.size();i++ ){
					cadenadesviacionlm +=desviacionlm.elementAt(i)+"&";
					
				}
				
				i =0;
				for(i=0; i<malposicion.size();i++ ){
					cadenamalposicion +=malposicion.elementAt(i)+"&";
					
				}
				
				i =0;
				for(i=0; i<atm.size();i++ ){
					cadenaatm +=atm.elementAt(i)+"&";
					
				}
				
				i =0;
				for(i=0; i<hab_orales.size();i++ ){
					cadenahab_orales +=hab_orales.elementAt(i)+"&";
					
				}
				
				i =0;
				for(i=0; i<paladar_blando.size();i++ ){
					cadenapaladar_blando +=paladar_blando.elementAt(i)+"&";
					
				}
				
				i =0;
				for(i=0; i<piso_boca.size();i++ ){
					cadenapiso_boca +=piso_boca.elementAt(i)+"&";
					
				}
				
				i =0;
				for(i=0; i<supernumerarios.size();i++ ){
					cadenasupernumerarios +=supernumerarios.elementAt(i)+"&";
					
				}
				
				i =0;
				for(i=0; i<hipodoncia.size();i++ ){
					cadenahipodoncia +=hipodoncia.elementAt(i)+"&";
					
				}
				
				i =0;
				for(i=0; i<fracturas.size();i++ ){
					cadenafracturas +=fracturas.elementAt(i)+"&";
					
				}
				
				i =0;
				for(i=0; i<periodontitis.size();i++ ){
					cadenaperiodontitis +=periodontitis.elementAt(i)+"&";
					
				}
				
				i =0;
				for(i=0; i<retracciones.size();i++ ){
					cadenaretracciones +=retracciones.elementAt(i)+"&";
					
				}
				
				i =0;
				for(i=0; i<torus.size();i++ ){
					cadenatorus +=torus.elementAt(i)+"&";
					
				}
				
				i =0;
				for(i=0; i<sob_vertical.size();i++ ){
					cadenasob_vertical +=sob_vertical.elementAt(i)+"&";
					
				}
				
				i =0;
				for(i=0; i<sob_horizontal.size();i++ ){
					cadenasob_horizontal +=sob_horizontal.elementAt(i)+"&";
					
				}
				
				i =0;
				for(i=0; i<ex_otros.size();i++ ){
					cadenaex_otros +=ex_otros.elementAt(i)+"&";
					
				}
			/*
			//System.out.print("cadena= "+cadenacontenidoInformeGeneral);
			System.out.print(" \n\n datos generales= "+cadenacontenidoInformeGeneral);
			System.out.print(" \n datos alergia= "+cadenaAlergias);
			System.out.print(" \n datos hrmorragia= "+cadenaHemorragias);
			System.out.print(" \n datos enf respiratorias= "+cadenaenf_respiratorias);
			System.out.print(" \n datos cardiopatias= "+cadenacardiopatias);
			System.out.print(" \n datos fiebre reumatica= "+cadenafiebre_reumatica);
			System.out.print(" \n datos enf renales= "+cadenaenf_renales);
			System.out.print(" \n datos hepatitis= "+cadenahepatitis);
			System.out.print(" \n datos trans gastricos= "+cadenatrans_gastricos);
			System.out.print(" \n datos tension arterial= "+cadenatension_arterial);
			System.out.print(" \n datos diabetes= "+cadenadiabetes);
			System.out.print(" \n datos medicamentos= "+cadenamedicamentos);
			System.out.print(" \n datos cirugias= "+cadenacirugias);
			System.out.print(" \n datos protesis= "+cadenaprotesis);
			System.out.print(" \n datos hiv= "+cadenahiv);
			System.out.print(" \n datos extracciones= "+cadenaextracciones);
			System.out.print(" \n datos enf orales= "+cadenaenf_orales);
			System.out.print(" \n datos ant familiares= "+cadenaant_familiares);
			System.out.print(" \n datos ant medicos otros= "+cadenamed_otros);
			System.out.print(" \n datos examen clinico");
			System.out.print(" \n datos lengua= "+cadenalengua);
			System.out.print(" \n datos carillos= "+cadenacarillos);
			System.out.print(" \n datos abrasion= "+cadenaabrasion);
			System.out.print(" \n datos atricion= "+cadenaatricion);
			System.out.print(" \n datos hipoplasias= "+cadenahipoplasias);
			System.out.print(" \n datos gin marginal= "+cadenagin_marginal);
			System.out.print(" \n datos gin difusa= "+cadenagin_difusa);
			System.out.print(" \n datos micrognasia= "+cadenamicrognasia);
			System.out.print(" \n datos macrognasia= "+cadenamacrognasia);
			System.out.print(" \n datos desviacion linea media= "+cadenadesviacionlm);
			System.out.print(" \n datos malposicion= "+cadenamalposicion);
			System.out.print(" \n datos atm= "+cadenaatm);
			System.out.print(" \n datos habitos orales= "+cadenahab_orales);
			System.out.print(" \n datos paladar blando= "+cadenapaladar_blando);
			System.out.print(" \n datos piso de boca= "+cadenapiso_boca);
			System.out.print(" \n datos supernumerarios= "+cadenasupernumerarios);
			System.out.print(" \n datos hipodoncia= "+cadenahipodoncia);
			System.out.print(" \n datos fracturas= "+cadenafracturas);
			System.out.print(" \n datos periodontitis= "+cadenaperiodontitis);
			System.out.print(" \n datos retracciones= "+cadenaretracciones);
			System.out.print(" \n datos torus= "+cadenatorus);
			System.out.print(" \n datos sob vertical= "+cadenasob_vertical);
			System.out.print(" \n datos sob horizontal= "+cadenasob_horizontal);
			System.out.print(" \n datos examen otros= "+cadenaex_otros);
			*/
				//System.out.print("img controlador");
				String imagen = odont.buscar_img_odontograma(codinforme);
				//Image imagenOdont =  getImage(imagen, false);
				//out.print("<img id='imgbd' src='data:image/png;base64,"+imagen +"'/>");	
				imagen = "<img id='imgbd' src='data:image/png;base64,"+imagen +"'/>";
			cadenaInforme =cadenacontenidoInformeGeneral+"|"
			+cadenaAlergias+"|"
			+cadenaHemorragias+"|"
			+cadenaenf_respiratorias+"|"
			+cadenacardiopatias+"|"
			+cadenafiebre_reumatica+"|"
			+cadenaenf_renales+"|"
			+cadenahepatitis+"|"
			+cadenatrans_gastricos+"|"
			+cadenatension_arterial+"|"
			+cadenadiabetes+"|"
			+cadenamedicamentos+"|"
			+cadenacirugias+"|"
			+cadenaprotesis+"|"
			+cadenahiv+"|"
			+cadenaextracciones+"|"
			+cadenaenf_orales+"|"
			+cadenaant_familiares+"|"
			+cadenamed_otros+"|"
			+cadenalengua+"|"
			+cadenacarillos+"|"
			+cadenaabrasion+"|"
			+cadenaatricion+"|"
			+cadenahipoplasias+"|"
			+cadenagin_marginal+"|"
			+cadenagin_difusa+"|"
			+cadenamicrognasia+"|"
			+cadenamacrognasia+"|"
			+cadenadesviacionlm+"|"
			+cadenamalposicion+"|"
			+cadenaatm+"|"
			+cadenahab_orales+"|"
			+cadenapaladar_blando+"|"
			+cadenapiso_boca+"|"
			+cadenasupernumerarios+"|"
			+cadenahipodoncia+"|"
			+cadenafracturas+"|"
			+cadenaperiodontitis+"|"
			+cadenaretracciones+"|"
			+cadenatorus+"|"
			+cadenasob_vertical+"|"
			+cadenasob_horizontal+"|"
			+cadenaex_otros+"|"
			+imagen+"|";
			
			//System.out.print("imagen 64"+imagen);
			out.print(cadenaInforme);
			
			
			
			
		}else{
			if (opcion == 2){
				String codinforme = request.getParameter("codinforme");
				String codigoPaciente =  request.getParameter("codpaciente");
				String codUsuario =  request.getParameter("codusuario");
				String []  informe_general = request.getParameter("informe_general").split("&");
				System.out.print("modificar");
				
				//antecedentes medicos y odontologicos
				
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
				
				//String [] observacionesEcocardio =  request.getParameter("observaciones").split("&");
				
				
			    String imagen_odontograma =  request.getParameter("imagen_odontograma").trim();
				
				insercciones_realizadas_con_exito= odont.insertarInformeMod(fechacjmysql, hracjmysql, 
						codUsuario, codigoPaciente, codinforme);
				
				
				insercciones_realizadas_con_exito=metodos.actualizarInformeGeneralOdont(codinforme, informe_general);
				
				
				//inserciones antecedentes medicos
				  
				  //alergias
				  insercciones_realizadas_con_exito=odont.modificarAntecedentesOdont(codinforme, "1", alergias);
				  //hemorragias
				 insercciones_realizadas_con_exito=odont.modificarAntecedentesOdont(codinforme, "2", hemorragias);
				  //enfermedades respiratorias
				   insercciones_realizadas_con_exito=odont.modificarAntecedentesOdont(codinforme, "3", enf_respiratorias);
				  //cardiopatias
				  insercciones_realizadas_con_exito=odont.modificarAntecedentesOdont(codinforme, "4", cardiopatias);
				  //fiebre reumatica
				  insercciones_realizadas_con_exito=odont.modificarAntecedentesOdont(codinforme, "5", fiebre_reumatica);
				  //enfermedades renales
				  insercciones_realizadas_con_exito=odont.modificarAntecedentesOdont(codinforme, "6", enf_renales);
				  //hepatitis
				  insercciones_realizadas_con_exito=odont.modificarAntecedentesOdont(codinforme, "7", hepatitis);
				  //transtornos gastricos
				  insercciones_realizadas_con_exito=odont.modificarAntecedentesOdont(codinforme, "8", trans_gastricos);
				  //tension arterial
				  insercciones_realizadas_con_exito=odont.modificarAntecedentesOdont(codinforme, "9", tension_arterial);
				  //diabetes
				   insercciones_realizadas_con_exito=odont.modificarAntecedentesOdont(codinforme, "10", diabetes);
				  //ingesta medicamentos
				  insercciones_realizadas_con_exito=odont.modificarAntecedentesOdont(codinforme, "11", medicamentos);
				  //cirugias (incluso orales)
				 insercciones_realizadas_con_exito=odont.modificarAntecedentesOdont(codinforme, "12", cirugias);
				  //uso de protesis dental o aparatologia oral
				  insercciones_realizadas_con_exito=odont.modificarAntecedentesOdont(codinforme, "13", protesis);
				  //HIV
				  insercciones_realizadas_con_exito=odont.modificarAntecedentesOdont(codinforme, "14", hiv);
				  //extracciones dentales
				  insercciones_realizadas_con_exito=odont.modificarAntecedentesOdont(codinforme, "15", extracciones);
				  //enfermedades orales
				  insercciones_realizadas_con_exito=odont.modificarAntecedentesOdont(codinforme, "16", enf_orales);
				  //antecedentes familiares
				 insercciones_realizadas_con_exito=odont.modificarAntecedentesOdont(codinforme, "17", ant_familiares);
				  //otros
				  insercciones_realizadas_con_exito=odont.modificarAntecedentesOdont(codinforme, "18", ant_med_otros);
				
				
				//inserciones examen clinico
				  
				  //lengua
				  insercciones_realizadas_con_exito=odont.modificarExamClinico(codinforme, "1", lengua);
				  //carrillos
				  insercciones_realizadas_con_exito=odont.modificarExamClinico(codinforme, "2", carrillos);
				  //abrasion
				  insercciones_realizadas_con_exito=odont.modificarExamClinico(codinforme, "3", abrasion);
				  //atricion
				  insercciones_realizadas_con_exito=odont.modificarExamClinico(codinforme, "4", atricion);
				  //hipoplasias
				  insercciones_realizadas_con_exito=odont.modificarExamClinico(codinforme, "5", hipoplasias);
				  //gingivitis marginal
				  insercciones_realizadas_con_exito=odont.modificarExamClinico(codinforme, "6", gin_marginal);
				  //gingivitis difusa
				  insercciones_realizadas_con_exito=odont.modificarExamClinico(codinforme, "7", gin_difusa);
				  //micrognasia
				  insercciones_realizadas_con_exito=odont.modificarExamClinico(codinforme, "8", micrognasia);
				  //macrognasia
				  insercciones_realizadas_con_exito=odont.modificarExamClinico(codinforme, "9", macrognasia);
				  //desviacion linea media
				  insercciones_realizadas_con_exito=odont.modificarExamClinico(codinforme, "10", desviacionlm);
				  //malposicion
				  insercciones_realizadas_con_exito=odont.modificarExamClinico(codinforme, "11", malposicion);
				  //atm
				  insercciones_realizadas_con_exito=odont.modificarExamClinico(codinforme, "12", atm);
				  //habitos orales
				  insercciones_realizadas_con_exito=odont.modificarExamClinico(codinforme, "13", hab_orales);
				  //paladar blando
				  insercciones_realizadas_con_exito=odont.modificarExamClinico(codinforme, "14", paladar_blando);
				  //piso de boca
				  insercciones_realizadas_con_exito=odont.modificarExamClinico(codinforme, "15", piso_boca);
				  //supernumerarios
				   insercciones_realizadas_con_exito=odont.modificarExamClinico(codinforme, "16", supernumerarios);
				  //hipodoncia
				  insercciones_realizadas_con_exito=odont.modificarExamClinico(codinforme, "17", hipodoncia);
				  //fracturas
				  insercciones_realizadas_con_exito=odont.modificarExamClinico(codinforme, "18", fracturas);
				//periodontitis
				  insercciones_realizadas_con_exito=odont.modificarExamClinico(codinforme, "19", periodontitis);
				//retracciones
				  insercciones_realizadas_con_exito=odont.modificarExamClinico(codinforme, "20", retracciones);
				//torus
				  insercciones_realizadas_con_exito=odont.modificarExamClinico(codinforme, "21", torus);
				//sobremordida vertical
				  insercciones_realizadas_con_exito=odont.modificarExamClinico(codinforme, "22", sob_vertical);
				//sobremordida horizontal
				  insercciones_realizadas_con_exito=odont.modificarExamClinico(codinforme, "23", sob_horizontal);
				//otros
				  insercciones_realizadas_con_exito=odont.modificarExamClinico(codinforme, "24", ex_otros);
				  
				  insercciones_realizadas_con_exito= odont.ModificarImagenOdontograma(codinforme, imagen_odontograma);
				  
				if(insercciones_realizadas_con_exito == true){
				      out.print("1;Ha modificado correctamente el odontograma con fecha "+fechacjmysql+" en la hora "+hracjmysql+";"+codinforme);		  
					  }else{
					  out.print("0;Hubo un error al ingresar los datos al sistema!");
					  }
				
				
			}//fin opcion 2
			
			else{
				if (opcion == 3){
					//
				     String cod_paciente =  request.getParameter("codpaciente");
					String codinforme = metodos.codigoActualInformePaciente(cod_paciente);
					if(codinforme!="0"){
					System.out.print("codinf "+codinforme+"CODPACIENTE "+cod_paciente);
					//informe general
				
					//System.out.print("entra aqui!!!!!! codigo="+codinforme);
					//String codpac = request.getParameter("codpaciente");
					Vector <String> contenidoInformeGeneral = odont.buscar_informe_general(codinforme);
					String cadenacontenidoInformeGeneral ="";
					//alergias
					Vector <String> Alergias = odont.buscar_tipo_antecedente("1", codinforme);
					String cadenaAlergias ="";
					//hemorragias
					Vector <String> Hemorragias = odont.buscar_tipo_antecedente("2", codinforme);
					String cadenaHemorragias ="";
					//enfermedades respiratorias
					Vector <String> enf_respiratorias = odont.buscar_tipo_antecedente("3", codinforme);
					String cadenaenf_respiratorias ="";
					//cardiopatias
					Vector <String> cardiopatias = odont.buscar_tipo_antecedente("4", codinforme);
					String cadenacardiopatias ="";
					//fiebre reumatica
					Vector <String> fiebre_reumatica = odont.buscar_tipo_antecedente("5", codinforme);
					String cadenafiebre_reumatica ="";
					//enfermedades renales
					Vector <String> enf_renales = odont.buscar_tipo_antecedente("6", codinforme);
					String cadenaenf_renales ="";
					//hepatitis
					Vector <String> hepatitis = odont.buscar_tipo_antecedente("7", codinforme);
					String cadenahepatitis ="";
					//transtornos gastricos
					Vector <String> trans_gastricos = odont.buscar_tipo_antecedente("8", codinforme);
					String cadenatrans_gastricos ="";
					//tension arterial
					Vector <String> tension_arterial = odont.buscar_tipo_antecedente("9", codinforme);
					String cadenatension_arterial ="";
					//diabetes
					Vector <String> diabetes = odont.buscar_tipo_antecedente("10", codinforme);
					String cadenadiabetes ="";
					//ingesta medicamentos
					Vector <String> medicamentos = odont.buscar_tipo_antecedente("11", codinforme);
					String cadenamedicamentos ="";
					//cirugias
					Vector <String> cirugias = odont.buscar_tipo_antecedente("12", codinforme);
					String cadenacirugias ="";
					//protesis
					Vector <String> protesis = odont.buscar_tipo_antecedente("13", codinforme);
					String cadenaprotesis ="";
					//hiv
					Vector <String> hiv = odont.buscar_tipo_antecedente("14", codinforme);
					String cadenahiv ="";
					//extracciones dentales
					Vector <String> extracciones = odont.buscar_tipo_antecedente("15", codinforme);
					String cadenaextracciones ="";
					//enfermedades orales
					Vector <String> enf_orales = odont.buscar_tipo_antecedente("16", codinforme);
					String cadenaenf_orales ="";
					//antecedentes familiares
					Vector <String> ant_familiares = odont.buscar_tipo_antecedente("17", codinforme);
					String cadenaant_familiares ="";
					//antecedentes medicos otros
					Vector <String> med_otros = odont.buscar_tipo_antecedente("18", codinforme);
					String cadenamed_otros ="";
					
					//datos examen clinico
					
					//lengua
					Vector <String> lengua = odont.buscar_examen_clinico("1", codinforme);
					String cadenalengua ="";
					//carrillos
					Vector <String> carillos = odont.buscar_examen_clinico("2", codinforme);
					String cadenacarillos ="";
					//abrasion
					Vector <String> abrasion = odont.buscar_examen_clinico("3", codinforme);
					String cadenaabrasion ="";
					//atricion
					Vector <String> atricion = odont.buscar_examen_clinico("4", codinforme);
					String cadenaatricion ="";
					//hipoplasias
					Vector <String> hipoplasias = odont.buscar_examen_clinico("5", codinforme);
					String cadenahipoplasias ="";
					//gingivitis marginal
					Vector <String> gin_marginal = odont.buscar_examen_clinico("6", codinforme);
					String cadenagin_marginal ="";
					//gingivitis difusa
					Vector <String> gin_difusa = odont.buscar_examen_clinico("7", codinforme);
					String cadenagin_difusa ="";
					//micrognasia
					Vector <String> micrognasia = odont.buscar_examen_clinico("8", codinforme);
					String cadenamicrognasia ="";
					//macrognasia
					Vector <String> macrognasia = odont.buscar_examen_clinico("9", codinforme);
					String cadenamacrognasia ="";
					//desviacion linea media
					Vector <String> desviacionlm = odont.buscar_examen_clinico("10", codinforme);
					String cadenadesviacionlm ="";
					//malposicion
					Vector <String> malposicion = odont.buscar_examen_clinico("11", codinforme);
					String cadenamalposicion ="";
					//malposicion
					Vector <String> atm = odont.buscar_examen_clinico("12", codinforme);
					String cadenaatm ="";
					//habitos orales
					Vector <String> hab_orales = odont.buscar_examen_clinico("13", codinforme);
					String cadenahab_orales ="";
					//paladar blando
					Vector <String> paladar_blando = odont.buscar_examen_clinico("14", codinforme);
					String cadenapaladar_blando ="";
					//piso de boca
					Vector <String> piso_boca = odont.buscar_examen_clinico("15", codinforme);
					String cadenapiso_boca ="";
					//supernumerarios
					Vector <String> supernumerarios = odont.buscar_examen_clinico("16", codinforme);
					String cadenasupernumerarios ="";
					//hipodoncia
					Vector <String> hipodoncia = odont.buscar_examen_clinico("17", codinforme);
					String cadenahipodoncia ="";
					//fracturas
					Vector <String> fracturas = odont.buscar_examen_clinico("18", codinforme);
					String cadenafracturas ="";
					//periodontitis
					Vector <String> periodontitis = odont.buscar_examen_clinico("19", codinforme);
					String cadenaperiodontitis="";
					//retracciones
					Vector <String> retracciones = odont.buscar_examen_clinico("20", codinforme);
					String cadenaretracciones="";
					//torus
					Vector <String> torus = odont.buscar_examen_clinico("21", codinforme);
					String cadenatorus="";
					//sobremordida vertical
					Vector <String> sob_vertical = odont.buscar_examen_clinico("22", codinforme);
					String cadenasob_vertical="";
					//sobremordida horizontal
					Vector <String> sob_horizontal = odont.buscar_examen_clinico("23", codinforme);
					String cadenasob_horizontal="";
					//examen clinico otros
					Vector <String> ex_otros = odont.buscar_examen_clinico("24", codinforme);
					String cadenaex_otros="";
					
					
					//cadena que almacenara todo el informe
					String cadenaInforme ="";
					
					int i =0;
					for(i=0; i<contenidoInformeGeneral.size();i++ ){
						cadenacontenidoInformeGeneral +=contenidoInformeGeneral.elementAt(i)+"&";
						
					}
					i=0;
					for(i=0; i<Alergias.size();i++ ){
						cadenaAlergias +=Alergias.elementAt(i)+"&";
						
					}
					 i =0;
					for(i=0; i<Hemorragias.size();i++ ){
						cadenaHemorragias +=Hemorragias.elementAt(i)+"&";
						
					}
					
					 i =0;
						for(i=0; i<enf_respiratorias.size();i++ ){
							cadenaenf_respiratorias +=enf_respiratorias.elementAt(i)+"&";
							
						}
						i =0;
						for(i=0; i<cardiopatias.size();i++ ){
							cadenacardiopatias +=cardiopatias.elementAt(i)+"&";
							
						}
						
						i =0;
						for(i=0; i<fiebre_reumatica.size();i++ ){
							cadenafiebre_reumatica +=fiebre_reumatica.elementAt(i)+"&";
							
						}
						
						i =0;
						for(i=0; i<enf_renales.size();i++ ){
							cadenaenf_renales +=enf_renales.elementAt(i)+"&";
							
						}
						
						i =0;
						for(i=0; i<hepatitis.size();i++ ){
							cadenahepatitis +=hepatitis.elementAt(i)+"&";
							
						}
						
						i =0;
						for(i=0; i<trans_gastricos.size();i++ ){
							cadenatrans_gastricos +=trans_gastricos.elementAt(i)+"&";
							
						}
						
						i =0;
						for(i=0; i<tension_arterial.size();i++ ){
							cadenatension_arterial +=tension_arterial.elementAt(i)+"&";
							
						}
						
						i =0;
						for(i=0; i<diabetes.size();i++ ){
							cadenadiabetes +=diabetes.elementAt(i)+"&";
							
						}
						
						i =0;
						for(i=0; i<medicamentos.size();i++ ){
							cadenamedicamentos +=medicamentos.elementAt(i)+"&";
							
						}
						
						i =0;
						for(i=0; i<cirugias.size();i++ ){
							cadenacirugias +=cirugias.elementAt(i)+"&";
							
						}
						
						i =0;
						for(i=0; i<protesis.size();i++ ){
							cadenaprotesis +=protesis.elementAt(i)+"&";
							
						}
						
						i =0;
						for(i=0; i<hiv.size();i++ ){
							cadenahiv +=hiv.elementAt(i)+"&";
							
						}
						
						i =0;
						for(i=0; i<extracciones.size();i++ ){
							cadenaextracciones +=extracciones.elementAt(i)+"&";
							
						}
						
						i =0;
						for(i=0; i<enf_orales.size();i++ ){
							cadenaenf_orales +=enf_orales.elementAt(i)+"&";
							
						}
						
						i =0;
						for(i=0; i<ant_familiares.size();i++ ){
							cadenaant_familiares +=ant_familiares.elementAt(i)+"&";
							
						}
						
						i =0;
						for(i=0; i<med_otros.size();i++ ){
							cadenamed_otros +=med_otros.elementAt(i)+"&";
							
						}
						
						i =0;
						for(i=0; i<lengua.size();i++ ){
							cadenalengua +=lengua.elementAt(i)+"&";
							
						}
						
						i =0;
						for(i=0; i<carillos.size();i++ ){
							cadenacarillos +=carillos.elementAt(i)+"&";
							
						}
						
						i =0;
						for(i=0; i<abrasion.size();i++ ){
							cadenaabrasion +=abrasion.elementAt(i)+"&";
							
						}
						
						i =0;
						for(i=0; i<atricion.size();i++ ){
							cadenaatricion +=atricion.elementAt(i)+"&";
							
						}
						
						i =0;
						for(i=0; i<hipoplasias.size();i++ ){
							cadenahipoplasias +=hipoplasias.elementAt(i)+"&";
							
						}
						
						i =0;
						for(i=0; i<gin_marginal.size();i++ ){
							cadenagin_marginal +=gin_marginal.elementAt(i)+"&";
							
						}
						
						i =0;
						for(i=0; i<gin_difusa.size();i++ ){
							cadenagin_difusa +=gin_difusa.elementAt(i)+"&";
							
						}
						
						i =0;
						for(i=0; i<micrognasia.size();i++ ){
							cadenamicrognasia +=micrognasia.elementAt(i)+"&";
							
						}
						
						i =0;
						for(i=0; i<macrognasia.size();i++ ){
							cadenamacrognasia +=macrognasia.elementAt(i)+"&";
							
						}
						
						i =0;
						for(i=0; i<desviacionlm.size();i++ ){
							cadenadesviacionlm +=desviacionlm.elementAt(i)+"&";
							
						}
						
						i =0;
						for(i=0; i<malposicion.size();i++ ){
							cadenamalposicion +=malposicion.elementAt(i)+"&";
							
						}
						
						i =0;
						for(i=0; i<atm.size();i++ ){
							cadenaatm +=atm.elementAt(i)+"&";
							
						}
						
						i =0;
						for(i=0; i<hab_orales.size();i++ ){
							cadenahab_orales +=hab_orales.elementAt(i)+"&";
							
						}
						
						i =0;
						for(i=0; i<paladar_blando.size();i++ ){
							cadenapaladar_blando +=paladar_blando.elementAt(i)+"&";
							
						}
						
						i =0;
						for(i=0; i<piso_boca.size();i++ ){
							cadenapiso_boca +=piso_boca.elementAt(i)+"&";
							
						}
						
						i =0;
						for(i=0; i<supernumerarios.size();i++ ){
							cadenasupernumerarios +=supernumerarios.elementAt(i)+"&";
							
						}
						
						i =0;
						for(i=0; i<hipodoncia.size();i++ ){
							cadenahipodoncia +=hipodoncia.elementAt(i)+"&";
							
						}
						
						i =0;
						for(i=0; i<fracturas.size();i++ ){
							cadenafracturas +=fracturas.elementAt(i)+"&";
							
						}
						
						i =0;
						for(i=0; i<periodontitis.size();i++ ){
							cadenaperiodontitis +=periodontitis.elementAt(i)+"&";
							
						}
						
						i =0;
						for(i=0; i<retracciones.size();i++ ){
							cadenaretracciones +=retracciones.elementAt(i)+"&";
							
						}
						
						i =0;
						for(i=0; i<torus.size();i++ ){
							cadenatorus +=torus.elementAt(i)+"&";
							
						}
						
						i =0;
						for(i=0; i<sob_vertical.size();i++ ){
							cadenasob_vertical +=sob_vertical.elementAt(i)+"&";
							
						}
						
						i =0;
						for(i=0; i<sob_horizontal.size();i++ ){
							cadenasob_horizontal +=sob_horizontal.elementAt(i)+"&";
							
						}
						
						i =0;
						for(i=0; i<ex_otros.size();i++ ){
							cadenaex_otros +=ex_otros.elementAt(i)+"&";
							
						}
					/*
					//System.out.print("cadena= "+cadenacontenidoInformeGeneral);
					System.out.print(" \n\n datos generales= "+cadenacontenidoInformeGeneral);
					System.out.print(" \n datos alergia= "+cadenaAlergias);
					System.out.print(" \n datos hrmorragia= "+cadenaHemorragias);
					System.out.print(" \n datos enf respiratorias= "+cadenaenf_respiratorias);
					System.out.print(" \n datos cardiopatias= "+cadenacardiopatias);
					System.out.print(" \n datos fiebre reumatica= "+cadenafiebre_reumatica);
					System.out.print(" \n datos enf renales= "+cadenaenf_renales);
					System.out.print(" \n datos hepatitis= "+cadenahepatitis);
					System.out.print(" \n datos trans gastricos= "+cadenatrans_gastricos);
					System.out.print(" \n datos tension arterial= "+cadenatension_arterial);
					System.out.print(" \n datos diabetes= "+cadenadiabetes);
					System.out.print(" \n datos medicamentos= "+cadenamedicamentos);
					System.out.print(" \n datos cirugias= "+cadenacirugias);
					System.out.print(" \n datos protesis= "+cadenaprotesis);
					System.out.print(" \n datos hiv= "+cadenahiv);
					System.out.print(" \n datos extracciones= "+cadenaextracciones);
					System.out.print(" \n datos enf orales= "+cadenaenf_orales);
					System.out.print(" \n datos ant familiares= "+cadenaant_familiares);
					System.out.print(" \n datos ant medicos otros= "+cadenamed_otros);
					System.out.print(" \n datos examen clinico");
					System.out.print(" \n datos lengua= "+cadenalengua);
					System.out.print(" \n datos carillos= "+cadenacarillos);
					System.out.print(" \n datos abrasion= "+cadenaabrasion);
					System.out.print(" \n datos atricion= "+cadenaatricion);
					System.out.print(" \n datos hipoplasias= "+cadenahipoplasias);
					System.out.print(" \n datos gin marginal= "+cadenagin_marginal);
					System.out.print(" \n datos gin difusa= "+cadenagin_difusa);
					System.out.print(" \n datos micrognasia= "+cadenamicrognasia);
					System.out.print(" \n datos macrognasia= "+cadenamacrognasia);
					System.out.print(" \n datos desviacion linea media= "+cadenadesviacionlm);
					System.out.print(" \n datos malposicion= "+cadenamalposicion);
					System.out.print(" \n datos atm= "+cadenaatm);
					System.out.print(" \n datos habitos orales= "+cadenahab_orales);
					System.out.print(" \n datos paladar blando= "+cadenapaladar_blando);
					System.out.print(" \n datos piso de boca= "+cadenapiso_boca);
					System.out.print(" \n datos supernumerarios= "+cadenasupernumerarios);
					System.out.print(" \n datos hipodoncia= "+cadenahipodoncia);
					System.out.print(" \n datos fracturas= "+cadenafracturas);
					System.out.print(" \n datos periodontitis= "+cadenaperiodontitis);
					System.out.print(" \n datos retracciones= "+cadenaretracciones);
					System.out.print(" \n datos torus= "+cadenatorus);
					System.out.print(" \n datos sob vertical= "+cadenasob_vertical);
					System.out.print(" \n datos sob horizontal= "+cadenasob_horizontal);
					System.out.print(" \n datos examen otros= "+cadenaex_otros);
					*/
						//System.out.print("img controlador");
						String imagen = odont.buscar_img_odontograma(codinforme);
						
						//Image imagenOdont =  getImage(imagen, false);
						//out.print("<img id='imgbd' src='data:image/png;base64,"+imagen +"'/>");	
						imagen = "<img id='imgbd' src='data:image/png;base64,"+imagen +"'/>";
					cadenaInforme =cadenacontenidoInformeGeneral+"|"
					+cadenaAlergias+"|"
					+cadenaHemorragias+"|"
					+cadenaenf_respiratorias+"|"
					+cadenacardiopatias+"|"
					+cadenafiebre_reumatica+"|"
					+cadenaenf_renales+"|"
					+cadenahepatitis+"|"
					+cadenatrans_gastricos+"|"
					+cadenatension_arterial+"|"
					+cadenadiabetes+"|"
					+cadenamedicamentos+"|"
					+cadenacirugias+"|"
					+cadenaprotesis+"|"
					+cadenahiv+"|"
					+cadenaextracciones+"|"
					+cadenaenf_orales+"|"
					+cadenaant_familiares+"|"
					+cadenamed_otros+"|"
					+cadenalengua+"|"
					+cadenacarillos+"|"
					+cadenaabrasion+"|"
					+cadenaatricion+"|"
					+cadenahipoplasias+"|"
					+cadenagin_marginal+"|"
					+cadenagin_difusa+"|"
					+cadenamicrognasia+"|"
					+cadenamacrognasia+"|"
					+cadenadesviacionlm+"|"
					+cadenamalposicion+"|"
					+cadenaatm+"|"
					+cadenahab_orales+"|"
					+cadenapaladar_blando+"|"
					+cadenapiso_boca+"|"
					+cadenasupernumerarios+"|"
					+cadenahipodoncia+"|"
					+cadenafracturas+"|"
					+cadenaperiodontitis+"|"
					+cadenaretracciones+"|"
					+cadenatorus+"|"
					+cadenasob_vertical+"|"
					+cadenasob_horizontal+"|"
					+cadenaex_otros+"|"
					+imagen+"|";
					
					out.print(cadenaInforme);
				}
					
				}//fin opcion 3
			}
		}
		
	}
	

	
}
