package pyp_controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
//import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import adm_logica.MetodoPaciente;
//import adm_logica.MetodoUsuario;
//import adm_bean.Pyp;

import pyp_metodo.MetodoFormPyp;

/**
 * Servlet implementation class pypDATA
 */
public class pypData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public pypData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		response.setContentType("text/html;charset=UTF-8");
		
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
		//String fechacj=dia+"/"+mes+"/"+annio;
		
		
		
		Calendar calendario = Calendar.getInstance();
		//Calendar calendario = new GregorianCalendar();
			int hora, minutos, segundos;
			hora =calendario.get(Calendar.HOUR_OF_DAY);
			minutos = calendario.get(Calendar.MINUTE);
			segundos = calendario.get(Calendar.SECOND);
			String hra= hora+":"+minutos+":"+segundos; 
		
		
			
		String Modcodpacientediagnostico=request.getParameter("Modcodpacientediagnostico");	
		//Variables de Sección Datos Personales
		String va=request.getParameter("valor");
		String codpaciente=request.getParameter("codpaciente");
		String codUsuario=request.getParameter("codusuario");
		String codReporte=request.getParameter("txtCodReporte");
		String nivEstudio=request.getParameter("nivEstudio");
		String chk_Convivencia=request.getParameter("chk_convivencia");
		String numHijos=request.getParameter("numHijos");
		String numNietos=request.getParameter("numNietos");
		String numPadres=request.getParameter("numPadres");
		String numOtros=request.getParameter("numOtros");
		String actLaboral=request.getParameter("actLaboral");
		String campoFuma=request.getParameter("campoFuma");
		String campoAlcohol=request.getParameter("campoAlcohol");
		String campoDrogas=request.getParameter("campoDrogas");
		String campoActividadFisica=request.getParameter("campoActividadFisica");
		String vivPropiedad=request.getParameter("vivPropiedad");
		String radGas=request.getParameter("radGas");
		String radLuz=request.getParameter("radLuz");
		String radAseo=request.getParameter("radAseo");
		String radHabitacionCompartida=request.getParameter("radHabitacionCompartida");
		String radAlcantarillado=request.getParameter("radAlcantarillado");
		String radAnimalesDomesticos=request.getParameter("radAnimalesDomesticos");
		String radAgua=request.getParameter("radAgua");
		String radRiesgoPareja=request.getParameter("radRiesgoPareja");
		String radMaltratoActual=request.getParameter("radMaltratoActual");
		String radActosViolencia=request.getParameter("radActosViolencia");
		String radSatisfaccion=request.getParameter("radSatisfaccion");
		
		
		//Variables de Sección Antecedentes
		String radFamHipertensionArterial=request.getParameter("radFamHipertensionArterial");
		String radFamHepatopatias=request.getParameter("radFamHepatopatias");
		String radFamDiabetes=request.getParameter("radFamDiabetes");
		String radFamTumores=request.getParameter("radFamTumores");
		String radFamCardiopatias=request.getParameter("radFamCardiopatias");
		String radFamMentales=request.getParameter("radFamMentales");
		String radPerHipertension=request.getParameter("radPerHipertension");
		String radPerMentales=request.getParameter("radPerMentales");
		String radPerDiabetes=request.getParameter("radPerDiabetes");
		String radPerInfeccionesPelvicas=request.getParameter("radPerInfeccionesPelvicas");
		String radPerCardiopatias=request.getParameter("radPerCardiopatias");
		String radPerInfeccionCervical=request.getParameter("radPerInfeccionCervical");
		String radPerHepatopatias=request.getParameter("radPerHepatopatias");
		String radPerFlujoVaginal=request.getParameter("radPerFlujoVaginal");
		String radPerNefritis=request.getParameter("radPerNefritis");
		String radPerCirugiaGinecologica=request.getParameter("radPerCirugiaGinecologica");
		String radPerTumores=request.getParameter("radPerTumores");
		String radPerCitologiasPrevias=request.getParameter("radPerCitologiasPrevias");
		String radPerTromboFlebitis=request.getParameter("radPerTromboFlebitis");
		String radPerHabitoFumar=request.getParameter("radPerHabitoFumar");
		
		String SelectHipArt=request.getParameter("SelectHipArt");
		String SelectHep=request.getParameter("SelectHep");
		String SelectDia=request.getParameter("SelectDia");
		String SelectTum=request.getParameter("SelectTum");
		String SelectCar=request.getParameter("SelectCar");
		String SelectMen=request.getParameter("SelectMen");
		String SelectHipArtPer=request.getParameter("SelectHipArtPer");
		String SelectMenPer=request.getParameter("SelectMenPer");
		String SelectDiaPer=request.getParameter("SelectDiaPer");
		String SelectInfPel=request.getParameter("SelectInfPel");
		String SelectCarPer=request.getParameter("SelectCarPer");
		String SelectInfCer=request.getParameter("SelectInfCer");
		String SelectHepPer=request.getParameter("SelectHepPer");
		String SelectFluVag=request.getParameter("SelectFluVag");
		String SelectNef=request.getParameter("SelectNef");
		String SelectTumPer=request.getParameter("SelectTumPer");
		String SelectTroFle=request.getParameter("SelectTroFle");
		
		String campoFamiliarHipArt=request.getParameter("campoFamiliarHipArt");
		String campoFamiliarHep=request.getParameter("campoFamiliarHep");
		String campoFamiliarDia=request.getParameter("campoFamiliarDia");
		String campoFamiliarTum=request.getParameter("campoFamiliarTum");
		String campoFamiliarCar=request.getParameter("campoFamiliarCar");
		String campoFamiliarMen=request.getParameter("campoFamiliarMen");
		String numDocumento=request.getParameter("numDocumento");
		
		//Variables Antecedentes Padece Familiar
		String SelectHipArtFAMILIA=request.getParameter("SelectHipArtFAMILIA");
		String SelectHepFAMILIA=request.getParameter("SelectHepFAMILIA");
		String SelectDiaFAMILIA=request.getParameter("SelectDiaFAMILIA");
		String SelectTumFAMILIA=request.getParameter("SelectTumFAMILIA");
		String SelectCarFAMILIA=request.getParameter("SelectCarFAMILIA");
		String SelectMenFAMILIA=request.getParameter("SelectMenFAMILIA");
		
		String CodPacienteMod=request.getParameter("CodPacienteMod");
		String CodUsuarioMod=request.getParameter("CodUsuarioMod");
		String ObsAntFam=request.getParameter("ObsAntFam");
		String ObsAntPer=request.getParameter("ObsAntPer");
		String NumeroDocumento=request.getParameter("NumeroDocumento");
		
		
		//Variables de Sección Historias
		String EmbarazoAntes=request.getParameter("EmbarazoAntes");
		String terminaEmbarazo=request.getParameter("terminaEmbarazo");
		String mesesGestacion=request.getParameter("mesesGestacion");
		String tipoParto=request.getParameter("tipoParto");
		String abortos=request.getParameter("aborto");
		String otrosAntecedentes=request.getParameter("otrosAntecedentes");
		String radCiclosRegulares=request.getParameter("radCiclosRegulares");
		String ultimaMenstruacion=request.getParameter("ultimaMenstruacion");
		String tipoTrasMenstruales=request.getParameter("tipoTrasMenstruales");
		String radMetodoAnticonceptivo=request.getParameter("radMetodoAnticonceptivo");
		String metodoUtilizado=request.getParameter("metodoUtilizado");
		String tiempoUtilizacionDesde=request.getParameter("tiempoUtilizacionDesde");
		String tiempoUtilizacionHasta=request.getParameter("tiempoUtilizacionHasta");
		String selpreescrito=request.getParameter("selpreescrito");
		String preescrito=request.getParameter("preescrito");
		String problemas=request.getParameter("problemas");
		
		
		//Variables de Seccion Planificacion Familiar
		String fechaVisita=request.getParameter("fechaVisita");
		String fechaUltMenstruacion=request.getParameter("fechaUltMenstruacion");
		String nombreEdadMujer=request.getParameter("nombreEdadMujer");
		String nombreSocioEconomico=request.getParameter("nombreSocioEconomico");
		String nombrePariedad=request.getParameter("nombrePariedad");
		String nombreIntervaloEmbarazo=request.getParameter("nombreIntervaloEmbarazo");
		String radAborto=request.getParameter("radAborto");
		String radMortinato=request.getParameter("radMortinato");
		String radCesarea=request.getParameter("radCesarea");
		String radPrematuro=request.getParameter("radPrematuro");
		String nombrePatologiaActual=request.getParameter("nombrePatologiaActual");
		
		
		//Variables de Seccion Riesgo Reproductivo
		String riesgoReproductivo=request.getParameter("riesgoReproductivo");
		
		
		//Variables de Sección Examenes
		String radMamasNormales=request.getParameter("radMamasNormales");
		String radUteAneNormales=request.getParameter("radUteAneNormales");
		String radSigEmbarazo=request.getParameter("radSigEmbarazo");
		String radMiemInfVarices=request.getParameter("radMiemInfVarices");
		String radCerNormal=request.getParameter("radCerNormal");
		String radEdemas=request.getParameter("radEdemas");
		String peso=request.getParameter("peso");
		String tensionArterial=request.getParameter("tensionArterial");
		String numeroSemanas=request.getParameter("numeroSemanas");
		String otrosHallazgos=request.getParameter("otrosHallazgos");
		String observaciones=request.getParameter("observaciones");
		String resPruEmbarazo=request.getParameter("resPruEmbarazo");
		String resCitCerVaginal=request.getParameter("resCitCerVaginal");
		String resFroVaginal=request.getParameter("resFroVaginal");
		String otrosExaPracticados=request.getParameter("otrosExaPracticados");
		
		
		//Variables de Sección Metodos
		String MetPildoras=request.getParameter("MetPildoras");
		String selPildoras=request.getParameter("selPildoras");
		String cualPildoras=request.getParameter("cualPildoras");
		String fechaPildoras=request.getParameter("fechaPildoras");
		
		String MetDIU=request.getParameter("MetDIU");
		String selDIU=request.getParameter("selDIU");
		String cualDIU=request.getParameter("cualDIU");
		String fechaDIU=request.getParameter("fechaDIU");
		
		String MetInyectables=request.getParameter("MetInyectables");
		String selInyectables=request.getParameter("selInyectables");
		String cualInyectables=request.getParameter("cualInyectables");
		String fechaInyectables=request.getParameter("fechaInyectables");
		
		String MetOtros=request.getParameter("MetOtros");
		String selOtros=request.getParameter("selOtros");
		String cualOtros=request.getParameter("cualOtros");
		String fechaOtros=request.getParameter("fechaOtros");
		
		
		//Variables de Seccion Controles
		String fechaControles=request.getParameter("fechaControles");
		String pesoControles=request.getParameter("pesoControles");
		String tensionControles=request.getParameter("tensionControles");
		String radSatisfaccionMetodo=request.getParameter("radSatisfaccionMetodo");
		String radTrastornosMenstruales=request.getParameter("radTrastornosMenstruales");
		String radCambiosComportamiento=request.getParameter("radCambiosComportamiento");
		String radCefaleas=request.getParameter("radCefaleas");
		String radMareos=request.getParameter("radMareos");
		String radManchasPiel=request.getParameter("radManchasPiel");
		String radMolestiasMamas=request.getParameter("radMolestiasMamas");
		String radEdemasControles=request.getParameter("radEdemasControles");
		String radVarices=request.getParameter("radVarices");
		String radExpulsionDispositivo=request.getParameter("radExpulsionDispositivo");
		String radDolorBajoVientre=request.getParameter("radDolorBajoVientre");
		String radFlujoVaginal=request.getParameter("radFlujoVaginal");
		
		
		//Variables de Seccion Conductas
		String radEstiloVidaSaludable=request.getParameter("radEstiloVidaSaludable");
		String radNutricionAlimentacion=request.getParameter("radNutricionAlimentacion");
		String radPrevencionEnfermedadesTranmisionSexual=request.getParameter("radPrevencionEnfermedadesTranmisionSexual");
		String radConsejeriaMetodos=request.getParameter("radConsejeriaMetodos");
		String radSignosSintomasAlarma=request.getParameter("radSignosSintomasAlarma");
		String radPrevencionCancerCuelloUterino=request.getParameter("radPrevencionCancerCuelloUterino");
		String radAutoexamenMama=request.getParameter("radAutoexamenMama");
		String radLactanciaMaterna=request.getParameter("radLactanciaMaterna");
		String radPrevencionDiaHipOst=request.getParameter("radPrevencionDiaHipOst");
		String radAbusoSexual=request.getParameter("radAbusoSexual");
		String radViolenciaIntrafamiliar=request.getParameter("radViolenciaIntrafamiliar");
		String radConsumoAlcCig=request.getParameter("radConsumoAlcCig");
		String radComplicacionesDrogas=request.getParameter("radComplicacionesDrogas");
		String radAutoestima=request.getParameter("radAutoestima");
		String radCuidadoRecienNacido=request.getParameter("radCuidadoRecienNacido");
		String radDerechosReproductivos=request.getParameter("radDerechosReproductivos");
		String radSaludSexualReproductiva=request.getParameter("radSaludSexualReproductiva");
		String radOtroProgramaPYP=request.getParameter("radOtroProgramaPYP");
		String radValoracionPsicologia=request.getParameter("radValoracionPsicologia");
		String radValoracionNutricion=request.getParameter("radValoracionNutricion");
		String radValoracionGinecologia=request.getParameter("radValoracionGinecologia");
		String radValoracionUrologia=request.getParameter("radValoracionUrologia");
		String radConsultaMedica=request.getParameter("radConsultaMedica");
		String otraEspecialidad=request.getParameter("otraEspecialidad");
				
		
		//Variables de Seccion Indicaciones
		String observacionEspecial=request.getParameter("observacionEspecial");
		String evolucionesClinicas=request.getParameter("evolucionesClinicas");
		
		//LLAMADOS DE LOS SELECTS CODIGO CIE 10 EN EL ONCHANGE 
		String CieHipArtFam=request.getParameter("CieHipArtFam");
		String CieHepFam=request.getParameter("CieHepFam");
		String CieDiaFam=request.getParameter("CieDiaFam");
		String CieTumFam=request.getParameter("CieTumFam");
		String CieCarFam=request.getParameter("CieCarFam");
		String CieMenFam=request.getParameter("CieMenFam");
		
		//VARIABLES DE FAMILIAR PADECE ANTECEDENTE
		String FAMILIARHipArtFam=request.getParameter("FAMILIARHipArtFam");
		String FAMILIARHepFam=request.getParameter("FAMILIARHepFam");
		String FAMILIARDiaFam=request.getParameter("FAMILIARDiaFam");
		String FAMILIARTumFam=request.getParameter("FAMILIARTumFam");
		String FAMILIARCarFam=request.getParameter("FAMILIARCarFam");
		String FAMILIARMenFam=request.getParameter("FAMILIARMenFam");
		
		///////////////////////////////////////////////////////		
		String CieHipArtPer=request.getParameter("CieHipArtPer");
		String CieMenPer=request.getParameter("CieMenPer");
		String CieDiaPer=request.getParameter("CieDiaPer");
		String CieInfPelPer=request.getParameter("CieInfPelPer");
		String CieCarPer=request.getParameter("CieCarPer");
		String CieInfCerPer=request.getParameter("CieInfCerPer");
		String CieHepPer=request.getParameter("CieHepPer");
		String CieFluVagPer=request.getParameter("CieFluVagPer");
		String CieNefPer=request.getParameter("CieNefPer");
		String CieTumPer=request.getParameter("CieTumPer");
		String CieTroFlePer=request.getParameter("CieTroFlePer");
		/*FIN LLAMADOS DE LOS SELECTS CODIGO CIE 10 EN EL ONCHANGE*/
		
		
		PrintWriter out=response.getWriter();
		MetodoFormPyp pypdata = new MetodoFormPyp();
		ResultSet rs=null;
			
		
		if(va.equals("1")){
			pypdata.insertarEncabezadoInformePyp(codUsuario, fechacjmysql, hra, codpaciente);
			rs=pypdata.ObtenerCodigoInforme(fechacjmysql, hra);
			String CodFo="";
			try {
				if(rs.next()){
					out.print(rs.getString(1));
					CodFo=rs.getString(1);
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pypdata.insertarEstudio(nivEstudio, CodFo);
			pypdata.insertarConvivencia(chk_Convivencia, numHijos, numNietos, numPadres, numOtros, CodFo);
			pypdata.insertarActividadLaboral(actLaboral, campoFuma, campoAlcohol, campoDrogas, campoActividadFisica, CodFo);
			pypdata.insertarViviendaHabitat(vivPropiedad, radHabitacionCompartida, radAnimalesDomesticos, radGas, radLuz, radAseo, radAlcantarillado, radAgua, radRiesgoPareja, radMaltratoActual, radActosViolencia, radSatisfaccion, CodFo);
		}
		
		/////Modificacion/////
		if(va.equals("1Mod")){
			pypdata.actualizarEstudio(Modcodpacientediagnostico, nivEstudio);
			pypdata.actualizarConvivencia(Modcodpacientediagnostico, numHijos, numNietos, numPadres, numOtros, chk_Convivencia);
			pypdata.actualizarActividadLaboral(Modcodpacientediagnostico, actLaboral, campoFuma, campoAlcohol, campoDrogas, campoActividadFisica);
			pypdata.actualizarViviendaHabitat(Modcodpacientediagnostico, vivPropiedad, radGas, radLuz, radAseo, radHabitacionCompartida, radAlcantarillado, radAnimalesDomesticos, radAgua, radRiesgoPareja, radMaltratoActual, radActosViolencia, radSatisfaccion);
		}
		
		if(va.equals("2")){		
			pypdata.insertarAntecedentesFamiliares(radFamHipertensionArterial, radFamHepatopatias, radFamDiabetes, radFamTumores, radFamCardiopatias, radFamMentales, codReporte);
			pypdata.insertarAntecedentesPersonales(radPerHipertension, radPerMentales, radPerDiabetes, radPerInfeccionesPelvicas, radPerCardiopatias, radPerInfeccionCervical, radPerHepatopatias, radPerFlujoVaginal, radPerNefritis, radPerCirugiaGinecologica, radPerTumores, radPerCitologiasPrevias, radPerTromboFlebitis, radPerHabitoFumar, codReporte);
			
			if(SelectHipArt.equals("") || SelectHipArtFAMILIA.equals("0")){
				pypdata.insertarSelectHipArt("", "", "", "", "", "", "", "");
			}else{
				rs=pypdata.ObtenerAntecedentesCodigoCieFamiliares(codpaciente, SelectHipArt);
				try {
					if (rs.next()) {
						SelectHipArt="";
						SelectHipArtFAMILIA="";
					} else {
						pypdata.insertarSelectHipArt(SelectHipArtFAMILIA, ObsAntFam, SelectHipArt, codpaciente, codUsuario, hra, fechacjmysql, NumeroDocumento);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(SelectHep.equals("")){
				pypdata.insertarSelectHep("", "", "", "", "", "", "", "");
			}else{
				rs=pypdata.ObtenerAntecedentesCodigoCieFamiliares(codpaciente, SelectHep);
				try {
					if (rs.next()) {
						SelectHep="";
						SelectHepFAMILIA="";
					} else {
						pypdata.insertarSelectHep(SelectHepFAMILIA, ObsAntFam, SelectHep, codpaciente, codUsuario, hra, fechacjmysql, NumeroDocumento);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(SelectDia.equals("")){
				pypdata.insertarSelectDia("", "", "", "", "", "", "", "");
			}else{
				rs=pypdata.ObtenerAntecedentesCodigoCieFamiliares(codpaciente, SelectDia);
				try {
					if (rs.next()) {
						SelectDia="";
						SelectDiaFAMILIA="";
					} else {
						pypdata.insertarSelectDia(SelectDiaFAMILIA, ObsAntFam, SelectDia, codpaciente, codUsuario, hra, fechacjmysql, NumeroDocumento);
						//System.out.println(codpaciente+","+SelectDia+","+codUsuario+","+fechacjmysql+","+hra+","+ObsAntFam);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
			
			if(SelectTum.equals("")){
				pypdata.insertarSelectTum("", "", "", "", "", "", "", "");
			}else{
				rs=pypdata.ObtenerAntecedentesCodigoCieFamiliares(codpaciente, SelectTum);
				try {
					if (rs.next()) {
						SelectTum="";
						SelectTumFAMILIA="";
					} else {
						pypdata.insertarSelectTum(SelectTumFAMILIA, ObsAntFam, SelectTum, codpaciente, codUsuario, hra, fechacjmysql, NumeroDocumento);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(SelectCar.equals("")){
				pypdata.insertarSelectCar("", "", "", "", "", "", "", "");
			}else{
				rs=pypdata.ObtenerAntecedentesCodigoCieFamiliares(codpaciente, SelectCar);
				try {
					if (rs.next()) {
						SelectCar="";
						SelectCarFAMILIA="";
					} else {
						pypdata.insertarSelectCar(SelectCarFAMILIA, ObsAntFam, SelectCar, codpaciente, codUsuario, hra, fechacjmysql, NumeroDocumento);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(SelectMen.equals("")){
				pypdata.insertarSelectMen("", "", "", "", "", "", "", "");
			}else{
				rs=pypdata.ObtenerAntecedentesCodigoCieFamiliares(codpaciente, SelectMen);
				try {
					if (rs.next()) {
						SelectMen="";
						SelectMenFAMILIA="";
					} else {
						pypdata.insertarSelectMen(SelectMenFAMILIA, ObsAntFam, SelectMen, codpaciente, codUsuario, hra, fechacjmysql, NumeroDocumento);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			//Personales
			if(SelectHipArtPer.equals("")){
				pypdata.insertarSelectHipArtPer("", "", "", "", "", "");
			}else{
				rs=pypdata.ObtenerAntecedentesCodigoCiePersonales(codpaciente, SelectHipArtPer, ObsAntPer);
				try {
					if (rs.next()) {
						SelectHipArtPer="";
					} else {
						pypdata.insertarSelectHipArtPer(codpaciente, SelectHipArtPer, codUsuario, fechacjmysql, hra, ObsAntPer);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(SelectMenPer.equals("")){
				pypdata.insertarSelectMenPer("", "", "", "", "", "");
			}else{
				rs=pypdata.ObtenerAntecedentesCodigoCiePersonales(codpaciente, SelectMenPer, ObsAntPer);
				try {
					if (rs.next()) {
						SelectMenPer="";
					} else {
						pypdata.insertarSelectMenPer(codpaciente, SelectMenPer, codUsuario, fechacjmysql, hra, ObsAntPer);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(SelectDiaPer.equals("")){
				pypdata.insertarSelectDiaPer("", "", "", "", "", "");
			}else{
				rs=pypdata.ObtenerAntecedentesCodigoCiePersonales(codpaciente, SelectDiaPer, ObsAntPer);
				try {
					if (rs.next()) {
						SelectDiaPer="";
					} else {
						pypdata.insertarSelectDiaPer(codpaciente, SelectDiaPer, codUsuario, fechacjmysql, hra, ObsAntPer);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				/**
				 * verificar si existe dx en la tabla adm_diagnosticoscola.hacer consulta llevando como parametro el codigo del paciente y el diagnostico
				 * si dx existe en tabla adm_diagnosticocola-- no lo ingreso
				 * sino guardo en la tabla.
				 * **/
			}
			
			if(SelectCarPer.equals("")){
				pypdata.insertarSelectCarPer("", "", "", "", "", "");
			}else{
				rs=pypdata.ObtenerAntecedentesCodigoCiePersonales(codpaciente, SelectCarPer, ObsAntPer);
				try {
					if (rs.next()) {
						SelectCarPer="";
					} else {
						pypdata.insertarSelectCarPer(codpaciente, SelectCarPer, codUsuario, fechacjmysql, hra, ObsAntPer);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(SelectHepPer.equals("")){
				pypdata.insertarSelectHepPer("", "", "", "", "", "");
			}else{
				rs=pypdata.ObtenerAntecedentesCodigoCiePersonales(codpaciente, SelectHepPer, ObsAntPer);
				try {
					if (rs.next()) {
						SelectHepPer="";
					} else {
						pypdata.insertarSelectHepPer(codpaciente, SelectHepPer, codUsuario, fechacjmysql, hra, ObsAntPer);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(SelectNef.equals("")){
				pypdata.insertarSelectNef("", "", "", "", "", "");
			}else{
				rs=pypdata.ObtenerAntecedentesCodigoCiePersonales(codpaciente, SelectNef, ObsAntPer);
				try {
					if (rs.next()) {
						SelectNef="";
					} else {
						pypdata.insertarSelectNef(codpaciente, SelectNef, codUsuario, fechacjmysql, hra, ObsAntPer);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(SelectTumPer.equals("")){
				pypdata.insertarSelectTumPer("", "", "", "", "", "");
			}else{
				rs=pypdata.ObtenerAntecedentesCodigoCiePersonales(codpaciente, SelectTumPer, ObsAntPer);
				try {
					if (rs.next()) {
						SelectTumPer="";
					} else {
						pypdata.insertarSelectTumPer(codpaciente, SelectTumPer, codUsuario, fechacjmysql, hra, ObsAntPer);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if(SelectTroFle.equals("")){
				pypdata.insertarSelectTroFle("", "", "", "", "", "");
			}else{
				rs=pypdata.ObtenerAntecedentesCodigoCiePersonales(codpaciente, SelectTroFle, ObsAntPer);
				try {
					if (rs.next()) {
						SelectTroFle="";
					} else {
						pypdata.insertarSelectTroFle(codpaciente, SelectTroFle, codUsuario, fechacjmysql, hra, ObsAntPer);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(SelectInfPel.equals("")){
				pypdata.insertarSelectInfPel("", "", "", "", "", "");
			}else{
				rs=pypdata.ObtenerAntecedentesCodigoCiePersonales(codpaciente, SelectInfPel, ObsAntPer);
				try {
					if (rs.next()) {
						SelectInfPel="";
					} else {
						pypdata.insertarSelectInfPel(codpaciente, SelectInfPel, codUsuario, fechacjmysql, hra, ObsAntPer);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(SelectInfCer.equals("")){
				pypdata.insertarSelectInfCer("", "", "", "", "", "");
			}else{
				rs=pypdata.ObtenerAntecedentesCodigoCiePersonales(codpaciente, SelectInfCer, ObsAntPer);
				try {
					if (rs.next()) {
						SelectInfCer="";
					} else {
						pypdata.insertarSelectInfCer(codpaciente, SelectInfCer, codUsuario, fechacjmysql, hra, ObsAntPer);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(SelectFluVag.equals("")){
				pypdata.insertarSelectFluVag("", "", "", "", "", "");
			}else{
				rs=pypdata.ObtenerAntecedentesCodigoCiePersonales(codpaciente, SelectFluVag, ObsAntPer);
				try {
					if (rs.next()) {
						SelectFluVag="";
					} else {
						pypdata.insertarSelectFluVag(codpaciente, SelectFluVag, codUsuario, fechacjmysql, hra, ObsAntPer);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}		
			
		}
		
		/*INSERCION DE CODIGOS CIE 10 EN LA BASE DE DATOS*/
		if(va.equals("CIE")){
			String tipo=request.getParameter("Tipo");
			if(tipo.equals("HipArtFam")){
			pypdata.CieSelectHipArtFam(FAMILIARHipArtFam, ObsAntFam, CieHipArtFam, CodPacienteMod, CodUsuarioMod, hra, fechacjmysql, numDocumento);
			}
			if(tipo.equals("HepFam")){
			pypdata.CieSelectHepFam(FAMILIARHepFam, ObsAntFam, CieHepFam, CodPacienteMod, CodUsuarioMod, hra, fechacjmysql, numDocumento);
			}
			if(tipo.equals("DiaFam")){
			pypdata.CieSelectDiaFam(FAMILIARDiaFam, ObsAntFam, CieDiaFam, CodPacienteMod, CodUsuarioMod, hra, fechacjmysql, numDocumento);
			}
			if(tipo.equals("TumFam")){
			pypdata.CieSelectTumFam(FAMILIARTumFam, ObsAntFam, CieTumFam, CodPacienteMod, CodUsuarioMod, hra, fechacjmysql, numDocumento);
			}
			if(tipo.equals("CarFam")){
			pypdata.CieSelectCarFam(FAMILIARCarFam, ObsAntFam, CieCarFam, CodPacienteMod, CodUsuarioMod, hra, fechacjmysql, numDocumento);
			}
			if(tipo.equals("MenFam")){
			pypdata.CieSelectMenFam(FAMILIARMenFam, ObsAntFam, CieMenFam, CodPacienteMod, CodUsuarioMod, hra, fechacjmysql, numDocumento);
			}
			
			//PERSONALES
			if(tipo.equals("HipArtPer")){
			pypdata.CieSelectHipArtPer(CodPacienteMod, CieHipArtPer, CodUsuarioMod, fechacjmysql, hra, ObsAntPer);
			}
			if(tipo.equals("MenPer")){
			pypdata.CieSelectMenPer(CodPacienteMod, CieMenPer, CodUsuarioMod, fechacjmysql, hra, ObsAntPer);
			}
			if(tipo.equals("DiaPer")){
			pypdata.CieSelectDiaPer(CodPacienteMod, CieDiaPer, CodUsuarioMod, fechacjmysql, hra, ObsAntPer);
			}
			if(tipo.equals("InfPelPer")){
			pypdata.CieSelectInfPelPer(CodPacienteMod, CieInfPelPer, CodUsuarioMod, fechacjmysql, hra, ObsAntPer);
			}
			if(tipo.equals("CarPer")){
			pypdata.CieSelectCarPer(CodPacienteMod, CieCarPer, CodUsuarioMod, fechacjmysql, hra, ObsAntPer);
			}
			if(tipo.equals("InfCerPer")){
			pypdata.CieSelectInfCerPer(CodPacienteMod, CieInfCerPer, CodUsuarioMod, fechacjmysql, hra, ObsAntPer);
			}
			if(tipo.equals("HepPer")){
			pypdata.CieSelectHepPer(CodPacienteMod, CieHepPer, CodUsuarioMod, fechacjmysql, hra, ObsAntPer);
			}
			if(tipo.equals("FluVagPer")){
			pypdata.CieSelectFluVagPer(CodPacienteMod, CieFluVagPer, CodUsuarioMod, fechacjmysql, hra, ObsAntPer);
			}
			if(tipo.equals("NefPer")){
			pypdata.CieSelectNefPer(CodPacienteMod, CieNefPer, CodUsuarioMod, fechacjmysql, hra, ObsAntPer);
			}
			if(tipo.equals("TumPer")){
			pypdata.CieSelectTumPer(CodPacienteMod, CieTumPer, CodUsuarioMod, fechacjmysql, hra, ObsAntPer);
			}
			if(tipo.equals("TroFlePer")){
			pypdata.CieSelectTroFlePer(CodPacienteMod, CieTroFlePer, CodUsuarioMod, fechacjmysql, hra, ObsAntPer);
			}
			
		}
		/* FIN INSERCION DE CODIGOS CIE 10 EN LA BASE DE DATOS*/
		
		
		if(va.equals("2Mod")){
			pypdata.actualizarAntecedentesFamiliares(Modcodpacientediagnostico, radFamHipertensionArterial, radFamDiabetes, radFamCardiopatias, radFamHepatopatias, radFamTumores, radFamMentales);
			pypdata.actualizarAntecedentesPersonales(Modcodpacientediagnostico, radPerHipertension, radPerDiabetes, radPerCardiopatias, radPerHepatopatias, radPerNefritis, radPerTumores, radPerTromboFlebitis, radPerHabitoFumar, radPerMentales, radPerInfeccionesPelvicas, radPerInfeccionCervical, radPerFlujoVaginal, radPerCirugiaGinecologica, radPerCitologiasPrevias);
			
			if(SelectHipArt.equals("") || campoFamiliarHipArt.equals("0")){
				pypdata.insertarSelectHipArt("", "", "", "", "", "", "", "");
			}else{
				rs=pypdata.ObtenerAntecedentesCodigoCieFamiliares(CodPacienteMod, SelectHipArt);
				try {
					if (rs.next()) {
						SelectHipArt="";
						campoFamiliarHipArt="";
					} else {
						pypdata.insertarSelectHipArt(campoFamiliarHipArt, ObsAntFam, SelectHipArt, CodPacienteMod, CodUsuarioMod, hra, fechacjmysql, numDocumento);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(SelectHep.equals("") || campoFamiliarHep.equals("0")){
				pypdata.insertarSelectHep("", "", "", "", "", "", "", "");
			}else{
				rs=pypdata.ObtenerAntecedentesCodigoCieFamiliares(CodPacienteMod, SelectHep);
				try {
					if (rs.next()) {
						SelectHep="";
						campoFamiliarHep="";
					} else {
						pypdata.insertarSelectHep(campoFamiliarHep, ObsAntFam, SelectHep, CodPacienteMod, CodUsuarioMod, hra, fechacjmysql, numDocumento);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(SelectDia.equals("") || campoFamiliarDia.equals("0")){
				pypdata.insertarSelectDia("", "", "", "", "", "", "", "");
			}else{
				rs=pypdata.ObtenerAntecedentesCodigoCieFamiliares(CodPacienteMod, SelectDia);
				try {
					if (rs.next()) {
						SelectDia="";
						campoFamiliarDia="";
					} else {
						pypdata.insertarSelectDia(campoFamiliarDia, ObsAntFam, SelectDia, CodPacienteMod, CodUsuarioMod, hra, fechacjmysql, numDocumento);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(SelectTum.equals("") || campoFamiliarTum.equals("0")){
				pypdata.insertarSelectTum("", "", "", "", "", "", "", "");
			}else{
				rs=pypdata.ObtenerAntecedentesCodigoCieFamiliares(CodPacienteMod, SelectTum);
				try {
					if (rs.next()) {
						SelectTum="";
						campoFamiliarTum="";
					} else {
						pypdata.insertarSelectTum(campoFamiliarTum, ObsAntFam, SelectTum, CodPacienteMod, CodUsuarioMod, hra, fechacjmysql, numDocumento);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(SelectCar.equals("") || campoFamiliarCar.equals("0")){
				pypdata.insertarSelectCar("", "", "", "", "", "", "", "");
			}else{
				rs=pypdata.ObtenerAntecedentesCodigoCieFamiliares(CodPacienteMod, SelectCar);
				try {
					if (rs.next()) {
						SelectCar="";
						campoFamiliarCar="";
					} else {
						pypdata.insertarSelectCar(campoFamiliarCar, ObsAntFam, SelectCar, CodPacienteMod, CodUsuarioMod, hra, fechacjmysql, numDocumento);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(SelectMen.equals("") || campoFamiliarMen.equals("0")){
				pypdata.insertarSelectMen("", "", "", "", "", "", "", "");
			}else{
				rs=pypdata.ObtenerAntecedentesCodigoCieFamiliares(CodPacienteMod, SelectMen);
				try {
					if (rs.next()) {
						SelectMen="";
						campoFamiliarMen="";
					} else {
						pypdata.insertarSelectCar(campoFamiliarMen, ObsAntFam, SelectMen, CodPacienteMod, CodUsuarioMod, hra, fechacjmysql, numDocumento);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
			//PERSONALES
			if(SelectHipArtPer.equals("")){
				pypdata.insertarSelectHipArtPer("", "", "", "", "", "");
			}else{
				rs=pypdata.ObtenerAntecedentesCodigoCiePersonales(CodPacienteMod, SelectHipArtPer, ObsAntPer);
				try {
					if (rs.next()) {
						SelectHipArtPer="";
					} else {
						pypdata.insertarSelectHipArtPer(CodPacienteMod, SelectHipArtPer, CodUsuarioMod, fechacjmysql, hra, ObsAntPer);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(SelectMenPer.equals("")){
				pypdata.insertarSelectMenPer("", "", "", "", "", "");
			}else{
				rs=pypdata.ObtenerAntecedentesCodigoCiePersonales(CodPacienteMod, SelectMenPer, ObsAntPer);
				try {
					if (rs.next()) {
						SelectMenPer="";
					} else {
						pypdata.insertarSelectMenPer(CodPacienteMod, SelectMenPer, CodUsuarioMod, fechacjmysql, hra, ObsAntPer);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(SelectDiaPer.equals("")){
				pypdata.insertarSelectDiaPer("", "", "", "", "", "");
			}else{
				rs=pypdata.ObtenerAntecedentesCodigoCiePersonales(CodPacienteMod, SelectDiaPer, ObsAntPer);
				try {
					if (rs.next()) {
						SelectDiaPer="";
					} else {
						pypdata.insertarSelectDiaPer(CodPacienteMod, SelectDiaPer, CodUsuarioMod, fechacjmysql, hra, ObsAntPer);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(SelectCarPer.equals("")){
				pypdata.insertarSelectCarPer("", "", "", "", "", "");
			}else{
				rs=pypdata.ObtenerAntecedentesCodigoCiePersonales(CodPacienteMod, SelectCarPer, ObsAntPer);
				try {
					if (rs.next()) {
						SelectCarPer="";
					} else {
						pypdata.insertarSelectCarPer(CodPacienteMod, SelectCarPer, CodUsuarioMod, fechacjmysql, hra, ObsAntPer);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(SelectHepPer.equals("")){
				pypdata.insertarSelectHepPer("", "", "", "", "", "");
			}else{
				rs=pypdata.ObtenerAntecedentesCodigoCiePersonales(CodPacienteMod, SelectHepPer, ObsAntPer);
				try {
					if (rs.next()) {
						SelectHepPer="";
					} else {
						pypdata.insertarSelectHepPer(CodPacienteMod, SelectHepPer, CodUsuarioMod, fechacjmysql, hra, ObsAntPer);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(SelectNef.equals("")){
				pypdata.insertarSelectNef("", "", "", "", "", "");
			}else{
				rs=pypdata.ObtenerAntecedentesCodigoCiePersonales(CodPacienteMod, SelectNef, ObsAntPer);
				try {
					if (rs.next()) {
						SelectNef="";
					} else {
						pypdata.insertarSelectNef(CodPacienteMod, SelectNef, CodUsuarioMod, fechacjmysql, hra, ObsAntPer);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(SelectTumPer.equals("")){
				pypdata.insertarSelectTumPer("", "", "", "", "", "");
			}else{
				rs=pypdata.ObtenerAntecedentesCodigoCiePersonales(CodPacienteMod, SelectTumPer, ObsAntPer);
				try {
					if (rs.next()) {
						SelectTumPer="";
					} else {
						pypdata.insertarSelectTumPer(CodPacienteMod, SelectTumPer, CodUsuarioMod, fechacjmysql, hra, ObsAntPer);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if(SelectTroFle.equals("")){
				pypdata.insertarSelectTroFle("", "", "", "", "", "");
			}else{
				rs=pypdata.ObtenerAntecedentesCodigoCiePersonales(CodPacienteMod, SelectTroFle, ObsAntPer);
				try {
					if (rs.next()) {
						SelectTroFle="";
					} else {
						pypdata.insertarSelectTroFle(CodPacienteMod, SelectTroFle, CodUsuarioMod, fechacjmysql, hra, ObsAntPer);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
			if(SelectInfPel.equals("")){
				pypdata.insertarSelectInfPel("", "", "", "", "", "");
			}else{
				rs=pypdata.ObtenerAntecedentesCodigoCiePersonales(CodPacienteMod, SelectInfPel, ObsAntPer);
				try {
					if (rs.next()) {
						SelectInfPel="";
					} else {
						pypdata.insertarSelectInfPel(CodPacienteMod, SelectInfPel, CodUsuarioMod, fechacjmysql, hra, ObsAntPer);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(SelectInfCer.equals("")){
				pypdata.insertarSelectInfCer("", "", "", "", "", "");
			}else{
				rs=pypdata.ObtenerAntecedentesCodigoCiePersonales(CodPacienteMod, SelectInfCer, ObsAntPer);
				try {
					if (rs.next()) {
						SelectInfCer="";
					} else {
						pypdata.insertarSelectInfCer(CodPacienteMod, SelectInfCer, CodUsuarioMod, fechacjmysql, hra, ObsAntPer);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(SelectFluVag.equals("")){
				pypdata.insertarSelectFluVag("", "", "", "", "", "");
			}else{
				rs=pypdata.ObtenerAntecedentesCodigoCiePersonales(CodPacienteMod, SelectFluVag, ObsAntPer);
				try {
					if (rs.next()) {
						SelectFluVag="";
					} else {
						pypdata.insertarSelectFluVag(CodPacienteMod, SelectFluVag, CodUsuarioMod, fechacjmysql, hra, ObsAntPer);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
		if(va.equals("3")){
			
			if(EmbarazoAntes.equals("Si")){
				pypdata.insertarHistoriasEmbarazosAnteriores(EmbarazoAntes, terminaEmbarazo, mesesGestacion, tipoParto, abortos, otrosAntecedentes, codReporte);
			}
			
			if(EmbarazoAntes.equals("No")){
				pypdata.insertarHistoriasEmbarazosAnteriores("No", "N/A", "N/A", "N/A", "N/A", "N/A", codReporte);
			}
			
			pypdata.insertarHistoriasMenstruales(radCiclosRegulares, ultimaMenstruacion, tipoTrasMenstruales, codReporte);
		
			if(radMetodoAnticonceptivo.equals("Si")){
				pypdata.insertarHistoriaConceptual(radMetodoAnticonceptivo, metodoUtilizado, tiempoUtilizacionDesde, tiempoUtilizacionHasta, selpreescrito, preescrito, problemas, codReporte);
			}
			
			if(radMetodoAnticonceptivo.equals("No")){
				pypdata.insertarHistoriaConceptual("No", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", codReporte);
			}
			
		}
		
		if(va.equals("3Mod")){
			pypdata.actualizarHistoriasEmbarazosAnteriores(Modcodpacientediagnostico, terminaEmbarazo, mesesGestacion, tipoParto, abortos, otrosAntecedentes);
			pypdata.actualizarHistoriasMenstruales(Modcodpacientediagnostico, radCiclosRegulares, ultimaMenstruacion, tipoTrasMenstruales);
		
			if(radMetodoAnticonceptivo.equals("Si")){
				pypdata.actualizarHistoriaConceptual(Modcodpacientediagnostico, radMetodoAnticonceptivo, metodoUtilizado, tiempoUtilizacionDesde, tiempoUtilizacionHasta, selpreescrito, preescrito, problemas);
			}
			
			if(radMetodoAnticonceptivo.equals("No")){
				pypdata.actualizarHistoriaConceptual(Modcodpacientediagnostico, "No", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A");
			}
			
		}
		
		if (va.equals("4")) {
			pypdata.insertarPlanificacionFamiliar(fechaVisita, fechaUltMenstruacion, nombreEdadMujer, nombrePariedad, nombreSocioEconomico, nombreIntervaloEmbarazo, radAborto, radMortinato, radCesarea, radPrematuro, nombrePatologiaActual, codReporte);
			pypdata.insertarRiesgoReproductivo(riesgoReproductivo, codReporte);
		}
		
		if (va.equals("4Mod")) {
			pypdata.actualizarPlanificacionFamiliar(Modcodpacientediagnostico, fechaVisita, fechaUltMenstruacion, nombreEdadMujer, nombrePariedad, nombreSocioEconomico, nombreIntervaloEmbarazo, radAborto, radMortinato, radCesarea, radPrematuro, nombrePatologiaActual);
			pypdata.actualizarRiesgoReproductivo(Modcodpacientediagnostico, riesgoReproductivo);
		}
		
		if (va.equals("5")) {
			 pypdata.insertarExamenFisico(radMamasNormales, radUteAneNormales, radSigEmbarazo, radMiemInfVarices, radCerNormal, radEdemas, peso, tensionArterial, numeroSemanas, otrosHallazgos, observaciones, codReporte);
			 pypdata.insertarExamenesPracticados(resPruEmbarazo, resCitCerVaginal, resFroVaginal, otrosExaPracticados, codReporte);
		}
		
		if (va.equals("5Mod")) {
			 pypdata.actualizarExamenFisico(Modcodpacientediagnostico, peso, tensionArterial, numeroSemanas, radMamasNormales, radSigEmbarazo, radCerNormal, radUteAneNormales, radMiemInfVarices, radEdemas, otrosHallazgos, observaciones);
			 pypdata.actualizarExamenesPracticados(Modcodpacientediagnostico, resPruEmbarazo, resCitCerVaginal, resFroVaginal, otrosExaPracticados);
		}
		
		if(va.equals("6")){
		   pypdata.insertarMetodoReproductivo(MetPildoras, selPildoras, cualPildoras, fechaPildoras, MetDIU, selDIU, cualDIU, fechaDIU, MetInyectables, selInyectables, cualInyectables, fechaInyectables, MetOtros, selOtros, cualOtros, fechaOtros, codReporte);
		}
		
		if(va.equals("6Mod")){
			   pypdata.actualizarMetodoReproductivo(Modcodpacientediagnostico, MetPildoras, selPildoras, cualPildoras, fechaPildoras, MetDIU, selDIU, cualDIU, fechaDIU, MetInyectables, selInyectables, cualInyectables, fechaInyectables, MetOtros, selOtros, cualOtros, fechaOtros);
		}
		
		if (va.equals("7")) {
			pypdata.insertarControlesEnfermeria(fechaControles, pesoControles, tensionControles, radSatisfaccionMetodo, radTrastornosMenstruales, radCambiosComportamiento, radCefaleas, radMareos, radManchasPiel, radMolestiasMamas, radEdemasControles, radVarices, radExpulsionDispositivo, radDolorBajoVientre, radFlujoVaginal, codReporte);
		}
		
		if (va.equals("7Mod")) {
			pypdata.actualizarControlesEnfermeria(Modcodpacientediagnostico, fechaControles, pesoControles, tensionControles, radSatisfaccionMetodo, radTrastornosMenstruales, radCambiosComportamiento, radCefaleas, radMareos, radManchasPiel, radMolestiasMamas, radEdemasControles, radVarices, radExpulsionDispositivo, radDolorBajoVientre, radFlujoVaginal);
		}
		
		if (va.equals("8")) {
			pypdata.insertarConductas(radEstiloVidaSaludable, radNutricionAlimentacion, radPrevencionEnfermedadesTranmisionSexual, radConsejeriaMetodos, radSignosSintomasAlarma, radPrevencionCancerCuelloUterino, radAutoexamenMama, radLactanciaMaterna, radPrevencionDiaHipOst, radAbusoSexual, radViolenciaIntrafamiliar, radConsumoAlcCig, radComplicacionesDrogas, radAutoestima, radCuidadoRecienNacido, radDerechosReproductivos, radSaludSexualReproductiva, radOtroProgramaPYP, radValoracionPsicologia, radValoracionNutricion, radValoracionGinecologia, radValoracionUrologia, radConsultaMedica, otraEspecialidad, codReporte);
		}
		
		if (va.equals("8Mod")) {
			pypdata.actualizarConductas(Modcodpacientediagnostico, radEstiloVidaSaludable, radNutricionAlimentacion, radPrevencionEnfermedadesTranmisionSexual, radConsejeriaMetodos, radSignosSintomasAlarma, radPrevencionCancerCuelloUterino, radAutoexamenMama, radLactanciaMaterna, radPrevencionDiaHipOst, radAbusoSexual, radViolenciaIntrafamiliar, radConsumoAlcCig, radComplicacionesDrogas, radAutoestima, radCuidadoRecienNacido, radDerechosReproductivos, radSaludSexualReproductiva, radOtroProgramaPYP, radValoracionPsicologia, radValoracionNutricion, radValoracionGinecologia, radValoracionUrologia, radConsultaMedica, otraEspecialidad);
		}
		
		if (va.equals("9")) {
			pypdata.insertarObservacionesEspeciales(observacionEspecial, codReporte);
			pypdata.insertarEvolucionClinica(evolucionesClinicas, codReporte);
		}
		
		if (va.equals("9Mod")) {
			pypdata.actualizarObservacionesEspeciales(Modcodpacientediagnostico, observacionEspecial);
			pypdata.actualizarEvolucionClinica(Modcodpacientediagnostico, evolucionesClinicas);
		}
		
		if(va.equals("VF")){			
			try {
				ResultSet rs1=null;//Antecedentes
				ResultSet rs2=null;//Controles Enfermeria
				ResultSet rs3=null;//Conductas
				ResultSet rs4=null;//Observaciones Especiales
				String CodReporte=request.getParameter("CodReporte");
				rs=pypdata.LlenoNivelEstudio(CodReporte);
				if(rs.next()){
					//entro quiere decir que esta lleno datos personales
					rs1=pypdata.LlenoAntecedentesFamiliares(CodReporte);
					if(rs1.next()){
						//entro quiere decir que esta lleno antecedentes
						rs2=pypdata.LlenoControlesEnfermeria(CodReporte);
						if(rs2.next()){
							//entro quiere decir que esta lleno controles enfermeria
							rs3=pypdata.LlenoConductas(CodReporte);
							if(rs3.next()){
								//entro quiere decir que esta lleno conductas
								rs4=pypdata.LlenoIndicaciones(CodReporte);
								if(rs4.next()){
									//entro quiere decir que esta lleno observaciones especiales
									out.print("OK");
								}else{
									out.println("Falta Guardar Indicaciones");						
								}
							}else{
								out.println("Falta Guardar Conductas");						
							}
						}else{
							out.println("Falta Guardar Controles");						
						}
					}else{
						out.println("Falta Guardar Antecedentes");						
					}
				}else{
					out.println("Falta Guardar Datos Personales");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
	
	
	}
	
	}
  

