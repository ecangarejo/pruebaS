/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/** *********************************************** */
function getXMLObject() // XML OBJECT
{
	var xmlHttp = false;
	try {
		xmlHttp = new ActiveXObject("Msxml2.XMLHTTP"); // For Old Microsoft
		// Browsers
	} catch (e) {
		try {
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP"); // For Microsoft
			// IE 6.0+
		} catch (e2) {
			xmlHttp = false; // No Browser accepts the XMLHTTP Object then
			// false
		}
	}
	if (!xmlHttp && typeof XMLHttpRequest != 'undefined') {
		xmlHttp = new XMLHttpRequest(); // For Mozilla, Opera Browsers
	}
	return xmlHttp; // Mandatory Statement returning the ajax object created
}

var xmlhttp = new getXMLObject(); // xmlhttp holds the ajax object

/** *********************************************** */




// variables para el informe de ventriculografia
var ventriculoizqdilatado = 0;
var tipocontractibilidad = 0;
var gravedadcontractibilidad = 0;
var caracteristicacontractibilidad = 0;
var lugarcontractibilidad = 0;
var fevi = 0;
var insuficienciamitral = 0;
var aspectovalvulamitral = 0;
// arreglos de fevi ventriculografia
var arrayporcentajesventriculografia = new Array();
var arreglodibujorealizadosventriculografia = new Array();

/** *********************************************** */
// variables para el informe de aortograma
var raizaortica = 0;
var txtraizaorticamedicion = "";
var aortaascendente = 0;
var txtaortaascendentemedicion = "";
var clasevalvulaAortica = 0;
var aspectovalvulaAortica = 0;
var insuficienciaAortica = 0;
var estenosisaortica = 0;
var txtgradientepicopico = "";

/** *********************************************** */
// variables para el informe de anatomia coronaria
// indice para el posicionamiento en los vectores para
// saber la arteria seleccionada
var indiceVectores = 0;

// clave indicadora para saber que arteria se esta manipulando
// arreglos para guardar desglosadamente todos y cada uno de los datos
// seleccionados en el informe
var clave_analisis = "";

// arterias bifurcadas
var arterias_bifurcadas = new Array();
var tipo_lesiones_por_arteria = new Array();
var tipo_lesiones_arterias_general = new Array();
var numero_lesiones_arterias_general = new Array();
var ids_arterias_del_informe= new Array();
var idtipolesion="";



// arreglos referentes al informe de la anatomia coronaria
var porcentaje_localizacion_lesion_arterias;
var tipo_lesion_por_oclusion_arterias;
var lesion_circulacion_colateral_arterias;
var caracteristicas_lesion_arterias;
var flujo_timi_arterias;
var tipo_lesion_arterias;
var lecho_distal_arterias;
var haybifurcacion_arterias;
var clasificacionMedina_arterias;

// arreglos de dibujos de las lesiones de cada arteria
var arrayposicioneslocalizacionProximales;
var arrayposicioneslocalizacionMedios;
var arrayposicioneslocalizacionDistales;

// areas para hacer click en el canvas del informe
var array_areas_de_seleccion = new Array();

// control de lesiones seleccionadas al momento
// de hacer click en el canvas para el informe de anatomia coronaria
var array_control_seleccion_lesion = new Array();

// arreglo de coordenadas dibujadas con los nombres de los dibujos
var arreglonombresdibujosrealizadoscoronaria = new Array();
var arreglodibujosrealizadoscoronaria = new Array();

/** *********************************************** */
// cadena informes
var cadenaInformeVentriculografia ="";
var cadenaInformeAortograma ="";
var cadenaInformaBypass="";
var cadenaInformeAnatomiaCoronaria ="";

// codigo clave del paciente seleccionado
var codpacientediagnostico = "";

// switches para el envio de datos
var swEnviarInformeVentriculografia = 0;
var swEnviarInformeAortograma = 0;
var swEnviarInformeBypass = 0;
var swEnviarInformeAnatomiaCoronia = 0;
/****************************************************/
// canvas codificados a base 64 solo para navegadores chrome iexplorer 9
var ImagenVentriculografiabase64 ="";
var ImagenAnatomiaCoronariabase64 ="";

/** *********************************************** */

var informespreviosrealizados = new Array();


/*
 * function solonumeros(e){ key = e.keyCode || e.which; // tecla =
 * String.fromCharCode(key).toLowerCase(); //codigos de las teclas del teclado
 * var numeros = [13,48,49,50,51,52,53,54,55,56,57]; var elementovalido =false;
 * for(var i in numeros){ if(key == numeros[i]){ elementovalido = true; break; } }
 * return elementovalido; }
 */

/*
 * function convertCanvasToImage() { var canvas =
 * document.getElementById('anatomia_coronaria'); var image = new Image();
 * image.src = canvas.toDataURL("image/png"); return image; }
 */
/** *********************************************** */
// funcion para saber si se encuentra el paciente registrado en el sistema
function cargarInformeRealizado() {

	var codInformeaModificar = recopilarinfotextbox("txtcodInforme");
	
		ajax = getXMLObject();
		ajax.open("POST", "cateterismomodificaciondata", true); // getname will be the
		// servlet name
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
			//	alert(ajax.responseText);
				
				var datos = ajax.responseText.split("|");
			    var x= 0;
			    var dataEncabezado = "";
			
			    var dataInformeVentriculografia = "";
                var dataInformeAortograma="";
			    var dataDominanciaAnatomiaCoronaria ="";
			   
			    ///////////////////////
			
			    	
                var dataArteriaslechoflujo = "";
                var dataLesionesArterias = "";
                var abreviacionesArterias ="";
            	var dataContenidoInformeGeneral = "";
				var dataContenidoInformeAnatomiaCoronaria = "";
            //    var datainserccionesprevias = "";
                
                
                for (x=0;x<datos.length;x++){
					
					switch(x){
					case 0: //encabezado
						
					 dataEncabezado = datos[x].split("&");
					 aparecerElemento("menu_encabezado"); 
					 reemplazarindexCombobox("estudio_realizado", dataEncabezado[0]);
					 reemplazarindexCombobox("indicaciones_diagnostico_hemodinamia", dataEncabezado[1]);
    				 reemplazarinfotextbox("txt_anesteciologo", dataEncabezado[2]);
    				// alert("encabezado: "+dataEncabezado[0]);
			
					 break;
					
					case 1://ventriculografia
						
					aparecerElemento("menu_ventriculografia");
					
					
					
					if(datos[x]!='null'){
						
						dataInformeVentriculografia = datos[x].split("&");
						SeleccionarCheckbox("ventriculografia_activacion");
						aparecerElemento("informe_ventriculografia");
						
						/*"vent.ventriculo_izquierdo_dilatado,"+
						"vent.id_tipo_contractibilidad_fk,"+
						"vent.id_gravedad_contractibilidad_fk,"+
						"vent.id_caracteristica_contractibilidad_fk,"+
						"vent.id_lugar_contractibilidad_fk,"+
						"vent.id_insuficiencia_mitral_fk,"+
						"vent.id_aspecto_valvula_mitral_fk,"+
						"vent.id_porcentaje_fevi_fk"+*/
					
						
						ventriculoizqdilatado = parseInt(dataInformeVentriculografia[0]);
						tipocontractibilidad = parseInt(dataInformeVentriculografia[1]);
						gravedadcontractibilidad = parseInt(dataInformeVentriculografia[2]);
						caracteristicacontractibilidad = parseInt(dataInformeVentriculografia[3]);
						lugarcontractibilidad = parseInt(dataInformeVentriculografia[4]);
						insuficienciamitral = parseInt(dataInformeVentriculografia[5]);
						aspectovalvulamitral = parseInt(dataInformeVentriculografia[6]);
						fevi = parseInt(dataInformeVentriculografia[7]);
						arrayporcentajesventriculografia = new Array();
						arreglodibujorealizadosventriculografia = new Array();
						
						reemplazarindexCombobox("seleccion_ventriculo_izq_dilatado", ventriculoizqdilatado);
						reemplazarindexCombobox("seleccion_contratibilidad_1", tipocontractibilidad);
						
						if (tipocontractibilidad>0){
							activarelementoFormulario("seleccion_contratibilidad_2");
							activarelementoFormulario("seleccion_contratibilidad_3");
							activarelementoFormulario("seleccion_contratibilidad_4");
							
						}
						
						reemplazarindexCombobox("seleccion_contratibilidad_2", gravedadcontractibilidad);
						reemplazarindexCombobox("seleccion_contratibilidad_3", caracteristicacontractibilidad);
						reemplazarindexCombobox("seleccion_contratibilidad_4", lugarcontractibilidad);
						reemplazarindexCombobox("seleccion_FEVI", fevi);
						reemplazarindexCombobox("seleccion_aspecto_valvulamitral", aspectovalvulamitral);
						reemplazarindexCombobox("seleccion_insuficiencia_valvulamitral", insuficienciamitral);
						
						cargar_porcentajes_FEVI();
						
					
						var myImage = new Image();
    					myImage.src = "Imagenes/ventriculografia.png";
    					myImage.onload = function() {
    					document.getElementById("ventriculografia").getContext('2d').drawImage(myImage, 0, 0, 400, 320);
    					actualizarDibujoVentriculografia();
    					dibujar_ventriculografia();
    					};
						
					     
						
						swEnviarInformeVentriculografia = 1;
    				}else{
    					
    					var myImage = new Image();
    					myImage.src = "Imagenes/ventriculografia.png";
    					myImage.onload = function() {
    					document.getElementById("ventriculografia").getContext('2d').drawImage(myImage, 0, 0, 400, 320);
    					};
    				}
					
					
					break;
					
					case 2:
						aparecerElemento("menu_aortograma");
						
						if(datos[x]!='null'){
							dataInformeAortograma = datos[x].split("&");
							
					   /* 	"aor.raiz_aortica_dilatada,"+							
							"aor.medicion_raiz_aortica,"+
							"aor.aorta_ascendente,"+
							"aor.medicion_aorta_ascendente,"+
							"aor.id_clase_valvula_aortica_fk,"+
							"aor.id_aspecto_valvula_aortica_fk,"+
							"aor.id_insuficiencia_aortica_fk,"+
							"aor.id_estenosis_aortica_fk,"+
							"aor.gradiente_pico_pico "+*/
							SeleccionarCheckbox("aortograma_activacion");
							aparecerElemento("informe_aortograma");
							
							raizaortica = parseInt(dataInformeAortograma[0]);
							txtraizaorticamedicion = dataInformeAortograma[1];
							aortaascendente = parseInt(dataInformeAortograma[2]);
							txtaortaascendentemedicion = dataInformeAortograma[3];
							clasevalvulaAortica = parseInt(dataInformeAortograma[4]);
							aspectovalvulaAortica = parseInt(dataInformeAortograma[5]);
							insuficienciaAortica = parseInt(dataInformeAortograma[6]);
							estenosisaortica = parseInt(dataInformeAortograma[7]);
							txtgradientepicopico = dataInformeAortograma[8];
							swEnviarInformeAortograma = 1;
							reemplazarindexCombobox("seleccion_raiz_aortica", raizaortica);
							if (raizaortica >0){
								activarelementoFormulario("txt_medicion_raiz_aortica");
								reemplazarinfotextbox("txt_medicion_raiz_aortica", txtraizaorticamedicion);
							}
							reemplazarindexCombobox("seleccion_aorta_ascendente", aortaascendente);
							if (aortaascendente > 0){
								activarelementoFormulario("txt_medicion_aorta_ascendente");
								reemplazarinfotextbox("txt_medicion_aorta_ascendente",txtaortaascendentemedicion);
							}
							reemplazarindexCombobox("seleccion_estenosis_aortica", estenosisaortica);
							if(estenosisaortica > 0){
								activarelementoFormulario("txt_medicion_gradiente_pico_pico");
								reemplazarinfotextbox("txt_medicion_gradiente_pico_pico",txtgradientepicopico);
							}
	
							reemplazarindexCombobox("seleccion_clase_valvula_aortica",clasevalvulaAortica);
							reemplazarindexCombobox("seleccion_aspecto_valvula_aortica",aspectovalvulaAortica);
							reemplazarindexCombobox("seleccion_insuficiencia_aortica",insuficienciaAortica);

							
						}
						aparecerElemento("menu_bypass");

						/*;
						aparecerElemento("Resultados_Informes");	*/
						
					//dataContenidoInformeAnatomiaCoronaria = datos[x];
					//alert("contenido Informe General Anatomia Coronaria: "+dataContenidoInformeAnatomiaCoronaria);
						
					break;
					
					case 3:
						aparecerElemento("menu_anatomia_coronaria");
						dataDominanciaAnatomiaCoronaria = datos[x]; 
						SeleccionarCheckbox("anatomia_coronaria_activacion");
						aparecerElemento("informe_anatomia_coronaria");
						reemplazarindexCombobox('seleccion_dominancia', parseInt(dataDominanciaAnatomiaCoronaria));
						
				//	dataInformeVentriculografia = datos[x];
				//	alert("Data Informe Ventriculografia: "+dataInformeVentriculografia);
							
					break;
					
					case 4:
						dataArteriaslechoflujo=datos[x].split("&");
						arterias_bifurcadas = dataArteriaslechoflujo[0].split("@");
						tipo_lesiones_arterias_general = dataArteriaslechoflujo[1].split("@");
											   
						var i = 0;
						for (i = 0 ; i < tipo_lesiones_arterias_general.length ; i++){
					    	cadena = tipo_lesiones_arterias_general[i];
					    	var aux = cadena.split("-");
					    	numero_lesiones_arterias_general[i]=aux.length;
					    }
                       	var aux_ids_arterias_del_informe =  dataArteriaslechoflujo[2].split("@");
						ids_arterias_del_informe = aux_ids_arterias_del_informe[0].split("-");
                        
						
						//alert("ids cam: "+ids_arterias_del_informe.toString());
						
						abreviacionesArterias =dataArteriaslechoflujo[3].split("-");
						//alert("abr cam: "+abreviacionesArterias.toString());
						aparecerElemento("Formulario"+abreviacionesArterias[0]);
						
						i = 0;
						flujotimis=dataArteriaslechoflujo[4].split("-");
						lechodistales=dataArteriaslechoflujo[5].split("-");
					//	alert(flujotimis.toString());
					//	alert(lechodistales.toString());
						for (i = 0 ; i < abreviacionesArterias.length ; i++){
					    	
							if((parseInt(flujotimis[i])-1)>0){
								
								activarelementoFormulario("flujo_timi"+abreviacionesArterias[i]+"_combo");
								activarelementoFormulario("lecho_distal"+abreviacionesArterias[i]+"_combo");
								reemplazarindexCombobox("flujo_timi"+abreviacionesArterias[i]+"_combo", (parseInt(flujotimis[i])-2));
							}
							else{
								reemplazarindexCombobox("flujo_timi"+abreviacionesArterias[i]+"_combo", 0);
							}
							reemplazarindexCombobox("lecho_distal"+abreviacionesArterias[i]+"_combo", (parseInt(lechodistales[i])-1));
							
							flujo_timi_arterias = new Array(numeroItemsCombobox("Seleccion_Arteria_combo"));
							lecho_distal_arterias = new Array(numeroItemsCombobox("Seleccion_Arteria_combo"));
							
							flujo_timi_arterias[i]=(parseInt(flujotimis[i])-1);
							lecho_distal_arterias[i]=parseInt(lechodistales[i]);	
							
						
						
						}
					break;
					
					case 5:
						
						
						
    					
    					
						dataLesionesArterias = datos[x].split("&");
					
						
						var myImage = new Image();
    					myImage.src = "Imagenes/corazon.png";
    					myImage.onload = function() {
    					document.getElementById("anatomia_coronaria").getContext('2d').drawImage(myImage, 0, 0, 400, 320);
    					
    					
    					/*	  "car.id_info_lesion_arteria_fk,"+
						   "loles.nombre_localizacion,"+
						   "car.id_porcentaje_fk,"+
						   "car.id_caracteristica_lesion_fk,"+
						   "car.id_lesion_colateral_fk,"+
						   "car.id_lesion_oclusion_total_fk,"+
						   "car.id_tipo_lesion_fk,"+
						   "car.id_rama_bifurcada_fk,"+
						   "car.id_medina_fk"+*/
						
						var dataporcentajeslesiones= dataLesionesArterias[2].split("@");
						var datacaracteristicas= dataLesionesArterias[3].split("@");
						var datalesioncolateral= dataLesionesArterias[4].split("@");
						var datalesionoclusiones=dataLesionesArterias[5].split("@");
						var datatiposlesionesABC = dataLesionesArterias[6].split("@");
						
						
						var datalesionesbifurcadas=dataLesionesArterias[7].split("@");
						var datamedina=dataLesionesArterias[8].split("@");
						//alert(datalesionesbifurcadas.toString());
						//alert("oooooo: "+aux_tipo_lesiones_actual.toString());
					/*	alert("t: "+dataporcentajeslesiones.toString());
						alert("m: "+datacaracteristicas.toString());
						alert("w: "+datalesioncolateral.toString());
						alert("q: "+datalesionoclusiones.toString());
						alert("a: "+datalesionesbifurcadas.toString());
						alert("s: "+datamedina.toString());*/
						carga_logica_informe_anatomia_coronaria();
						i = 0;
						var j=0;
						var aux_tipo_lesiones_actual = "";
						
						for (i = 0 ; i < abreviacionesArterias.length ; i++){
							aux_tipo_lesiones_actual = tipo_lesiones_arterias_general[i].split("-");
                         auxdataporcentajeslesiones= dataporcentajeslesiones[i].split("-");
							auxdatacaracteristicas= datacaracteristicas[i].split("-");
							auxdatalesioncolateral= datalesioncolateral[i].split("-");
							auxdatalesionoclusiones=datalesionoclusiones[i].split("-");
							auxdatatiposlesionesABC= datatiposlesionesABC[i].split("-");
							auxdatalesionesbifurcadas=datalesionesbifurcadas[i].split("-");
							auxdatamedina=datamedina[i].split("-");
						
							
						    clave_analisis = abreviacionesArterias[i];
						    indiceVectores = i;
						 //   alert(abreviacionesArterias[i]);
							for(j=0;j< aux_tipo_lesiones_actual.length ; j++){
								tipo_lesiones_por_arteria=aux_tipo_lesiones_actual;
								reemplazarindexCombobox('porcentaje_lesion_seleccion'+clave_analisis+'_'+tipo_lesiones_por_arteria[j]+'_combo', parseInt(auxdataporcentajeslesiones[j]));
		            			reemplazarindexCombobox('tipo_lesion_circulacion_colateral'+ clave_analisis+'_'+tipo_lesiones_por_arteria[j]+'_combo',parseInt(auxdatalesioncolateral[j]));
		            			
		            		
		            			
		            			if(parseInt(auxdatalesionoclusiones[j])>0){
		            				reemplazarindexCombobox('lesion_oclusion_total'+clave_analisis+"_"+tipo_lesiones_por_arteria[j]+'_combo', parseInt(auxdatalesionoclusiones[j])-1);
		            			}
		            			else{
		            				reemplazarindexCombobox('lesion_oclusion_total'+clave_analisis+"_"+tipo_lesiones_por_arteria[j]+'_combo', parseInt(auxdatalesionoclusiones[j]));
		            			}
		            			
		            			if(parseInt(auxdatatiposlesionesABC[j])>0){
		            				reemplazarindexCombobox('tipo_de_lesion'+clave_analisis+'_'+tipo_lesiones_por_arteria[j]+'_combo',(parseInt(auxdatatiposlesionesABC[j])-1));
		            			}
		            			else{
		            				reemplazarindexCombobox('tipo_de_lesion'+clave_analisis+'_'+tipo_lesiones_por_arteria[j]+'_combo',0);
		            			}
		            			
		            			if(parseInt(auxdatacaracteristicas[j])>0){
		            				reemplazarindexCombobox('Caracteristicas_lesion'+clave_analisis+'_'+tipo_lesiones_por_arteria[j]+'_combo',(parseInt(auxdatacaracteristicas[j])-1));
		            			}
		            			else{
		            				reemplazarindexCombobox('Caracteristicas_lesion'+clave_analisis+'_'+tipo_lesiones_por_arteria[j]+'_combo',0);
		            			}
		            			
		            			if (esArteriabifurcada(clave_analisis)){
		            				var t=0;
		            				var cad="";
		            				while(t<numeroItemsCombobox('con_la_rama'+clave_analisis+'_'+tipo_lesiones_por_arteria[j]+'_combo')){
		            					cad = document.getElementById('con_la_rama'+clave_analisis+'_'+tipo_lesiones_por_arteria[j]+'_combo').options[t].value;
		            					if (parseInt(cad)==parseInt(auxdatalesionesbifurcadas[j])){
		            						reemplazarindexCombobox('con_la_rama'+clave_analisis+'_'+tipo_lesiones_por_arteria[j]+'_combo', t);
		            						reemplazarindexCombobox('bifurcada'+clave_analisis+'_'+tipo_lesiones_por_arteria[j]+'_combo', 1);
		            					}
		            					t++;
		            				}
		            				if(parseInt(auxdatamedina[j])>0){
		            					reemplazarindexCombobox('clasificacion_medina_seleccion'+clave_analisis+'_'+tipo_lesiones_por_arteria[j]+'_combo',parseInt(auxdatamedina[j])-1);
		            				}
			            			else{
			            				reemplazarindexCombobox('clasificacion_medina_seleccion'+clave_analisis+"_"+tipo_lesiones_por_arteria[j]+'_combo', parseInt(auxdatalesionoclusiones[j]));
			            			}
		            				cambiarImagenMedina(clave_analisis +'_'+tipo_lesiones_por_arteria[j],recopilarindexCombobox('clasificacion_medina_seleccion'	+ clave_analisis +'_'+tipo_lesiones_por_arteria[j]+ '_combo'));
		            			}
		            			
		            		
		            			
		            			control_Informe_Anatomia_Coronaria(1);
		            		
							}
						}
						
						clave_analisis = abreviacionesArterias[0];
						indiceVectores = 0; 
						aux_tipo_lesiones_actual = tipo_lesiones_arterias_general[0].split("-");
						tipo_lesiones_por_arteria=aux_tipo_lesiones_actual;
						
						
						
						
						//alert(dataLesionesArterias.toString());
						
						
    					
    					
    					
    					
    					
    					};
						
					
						
						
						
						
					break;
					case 6:
						aparecerElemento('Resultados_Informes');
						dataContenidoInformeGeneral =  datos[x].split("&");
						reemplazarinfotextbox('contenido_informe_ventriculografia', dataContenidoInformeGeneral[0]);	
						reemplazarinfotextbox('contenido_informe_aortograma',dataContenidoInformeGeneral[1]);
						reemplazarinfotextbox('resumen_procedimiento',dataContenidoInformeGeneral[2]);
						reemplazarinfotextbox('conclusiones', dataContenidoInformeGeneral[3]);
						reemplazarinfotextbox('recomendaciones', dataContenidoInformeGeneral[4]);
						reemplazarinfotextbox('contenido_informe_bypass', dataContenidoInformeGeneral[5]);
						
						var cadenaux =recopilarinfotextbox('contenido_informe_bypass');
						
						if (cadenaux!='no se encontr\u00f3 ning\u00fan bypass.'){
							SeleccionarCheckbox("bypass_activacion");
							aparecerElemento("informe_bypass");
							reemplazarinfotextbox("bypass",dataContenidoInformeGeneral[5]);
							
						}
						
						//dataArteriaslechoflujo = datos[x];
					//	alert("datos generales arterias: "+dataArteriaslechoflujo);
						
						
					break;
					case 7:
					//	alert("wwwww: "+datos[x].split("&"));
						dataContenidoInformeAnatomiaCoronaria = datos[x].split("&");
						
						
						for (i = 0 ; i < abreviacionesArterias.length ; i++){
							
							reemplazarinfotextbox("observacion"+abreviacionesArterias[i], dataContenidoInformeAnatomiaCoronaria[i]);
							
							
						}
						recopilarInformeAnatomiaCoronaria();
						
					break;
					
					default:
						informespreviosrealizados = datos[x].split("&");
					  //  alert("inserciones previas: "+informespreviosrealizados.toString());
						
					break;
					
					}
					
					
				} 
				
				
				
				
			
				//alert(dataEncabezado);
				
				/*	document.getElementById('datos_ingreso_paciente').innerHTML = datos[2];
					aparecerElemento("menu_encabezado");
					aparecerElemento("menu_ventriculografia");
					aparecerElemento("menu_aortograma");
					aparecerElemento("menu_bypass");
					aparecerElemento("menu_anatomia_coronaria");
					codpacientediagnostico = datos[1];
					
					
					//Cargar la imagen con JavaScript y aplicarle posteriormente las modificaciones con <canvas>

					var myImage = new Image();
					myImage.src = "Imagenes/ventriculografia.png";
					myImage.onload = function() {
					document.getElementById("ventriculografia").getContext('2d').drawImage(myImage, 0, 0, 400, 320);
					};
					
					
					var myImage2 = new Image();
					myImage2.src = "Imagenes/corazon.png";
					myImage2.onload = function() {
					document.getElementById("anatomia_coronaria").getContext('2d').drawImage(myImage2, 0, 0, 600, 430);
					};
					desactivarelementoFormulario("cbafiliacion");
		            desactivarelementoFormulario("txtnumdoc");
		            desactivarelementoFormulario("btnBuscarPac");
	                 
					
					//alert("Paciente Encontrado!!");
				*/

			}
		};
		ajax.setRequestHeader('Content-Type',
				'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("opcion=3&codInformeaModificar=" + codInformeaModificar
);// Posting txtname
		// to Servlet
	
}

/** *********************************************** */
// control de activacion y desactivacion de informes (ventriculografia,
// aotograma y anatomia coronaria)
function realizarFormulario(codFormulario) {
	switch (codFormulario) {
	case 1: // se escogio el formulario de la ventriculografia
		if (verEstadoCheckbox('ventriculografia_activacion')) {
			aparecerElemento('informe_ventriculografia');
			carga_logica_informe_ventriculografia();
		} else {
			reiniciar_Elementos_Formulario_Ventriculografia();
			desaparecerElemento('informe_ventriculografia');
		}
		
		 recopilarInformeVentriculografia();  
		break;

	case 2: // se escogio el formulario del aortograma
		if (verEstadoCheckbox('aortograma_activacion')) {
			aparecerElemento('informe_aortograma');
			carga_logica_informe_aortograma();

		} else {
			reiniciar_elementos_Formulario_Aortograma();
			desaparecerElemento('informe_aortograma');
		}
		recopilarInformeAortograma(); 
		break;

		
	case 3:
		
		if (verEstadoCheckbox('bypass_activacion')) {
			aparecerElemento('informe_bypass');
			carga_logica_informe_bypass();

		} else {
			reiniciar_elementos_Formulario_Bypass();
			desaparecerElemento('informe_bypass');
		}
		recopilarInformeBypass(); 

		break;
		
	case 4: // se escogio el formulario de anatomia coronaria
		
	//	alert(verEstadoCheckbox('anatomia_coronaria_activacion'));
		if (verEstadoCheckbox('anatomia_coronaria_activacion')) {
			aparecerElemento('informe_anatomia_coronaria');
		
			/*if (tipo_lesiones_arterias_general.length == 0 || arterias_bifurcadas.length == 0 ){
				consultarlesionesbifurcaciones();
			}*/
			//else{
				carga_logica_informe_anatomia_coronaria();
				seleccionAnalisisArteria();
		//	}
		
		} else {
			reiniciar_Elementos_Formulario_Anatomia_Coronaria();
			desaparecerElemento('informe_anatomia_coronaria');
		}
		recopilarInformeAnatomiaCoronaria();
		break;
	}
	   ActivacionDesactivacionEnvioInformes();

	   if(tamanoinfotextbox("contenido_informe_ventriculografia")==0){
		    recopilarInformeVentriculografia();  
	   }
	   if(tamanoinfotextbox("contenido_informe_aortograma")==0){
			recopilarInformeAortograma();  
	   }
	   
	   if(tamanoinfotextbox("contenido_informe_bypass")==0){
			recopilarInformeBypass(); 
	   }
	   
	   if(tamanoinfotextbox("contenido_informe_analisis_anatomia_coronaria")==0){
	     	recopilarInformeAnatomiaCoronaria();
	   }
	
}

/** ***************************************************** */
// activacion y desactivacion controles envio de informes y visor de informes
function ActivacionDesactivacionEnvioInformes() {
	if (verEstadoCheckbox('ventriculografia_activacion')
			|| verEstadoCheckbox('aortograma_activacion')
			|| verEstadoCheckbox('bypass_activacion')
			|| verEstadoCheckbox('anatomia_coronaria_activacion')
			) {
		
		aparecerElemento('Resultados_Informes');
	} else {
		desaparecerElemento('Resultados_Informes');
	}
}

/** *********************************************** */
/** ******Funciones Informe Ventriculografia******* */
/** *********************************************** */

function carga_logica_informe_ventriculografia() {
	iniciar_variables_ventriculografia();
	cargar_porcentajes_FEVI();
	actualizarDibujoVentriculografia();
	dibujar_ventriculografia();

}

function iniciar_variables_ventriculografia() {
	ventriculoizqdilatado = 0;
	tipocontractibilidad = 0;
	gravedadcontractibilidad = 0;
	caracteristicacontractibilidad = 0;
	lugarcontractibilidad = 0;
	fevi = 0;
	insuficienciamitral = 0;
	aspectovalvulamitral = 0;
	arrayporcentajesventriculografia = new Array();
	arreglodibujorealizadosventriculografia = new Array();
	swEnviarInformeVentriculografia = 1;
}


/*function cargarImagenVentriculografia() {
	
	var canvas = document.getElementById('ventriculografia');
	canvas.width = canvas.width;
	if (canvas.getContext) {
		var ctx = canvas.getContext('2d');
		var img = new Image();
		img.src = 'Imagenes/ventriculografia.png';
		canvas.width = 400;
		canvas.height = 320;
		
		img.onload = function(){
	         //incluyo la imagen en el canvas
			ctx.drawImage(img, 0, 0, canvas.width, canvas.height);
	   
		}; 
	
	
	} else {
		alert('You need Safari or Firefox 1.5+ to see this demo.');
	}
}*/

function actualizarDibujoVentriculografia() {
	/*
	 * for (i = 0, j = ArregloDibujosRealizados_informe1.length; i < j; i++) {
	 * ArregloDibujosRealizados_informe1.splice(i, 1); i--; }
	 */
	var canvas = document.getElementById('ventriculografia');
	canvas.width = canvas.width;
	if (canvas.getContext) {
		var ctx = canvas.getContext('2d');
		var img = new Image();
		img.src = 'Imagenes/ventriculografia.png';
		canvas.width = 400;
		canvas.height = 320;
        //incluyo la imagen en el canvas
		ctx.drawImage(img, 0, 0, canvas.width, canvas.height);
	} else {
		alert('You need Safari or Firefox 1.5+ to see this demo.');
	}
}

function cargar_porcentajes_FEVI() {
	// 20%
	arrayporcentajesventriculografia.push("284,115,281,118,261,106,264,103;310,131,307,134,287,122,290,119;346,297,328,297,328,301,346,301;381,251,371,226,374,222,384,247;367,217,353,196,356,193,370,213;331,156,314,139,317,136,334,153;348,188,336,163,339,161,352,185;296,289,278,289,278,293,296,293;120,270,107,243,110,240,123,266;105,239,95,214,98,210,108,235;320,300,303,294,303,290,320,296;128,105,130,108,114,124,112,121;106,127,108,130,93,146,90,144;192,289,174,289,174,293,192,293;218,289,200,289,200,293,218,293;245,289,227,289,227,293,245,293;272,289,254,289,254,293,272,293;204,85,186,85,186,89,204,89;228,85,210,85,210,89,228,89;166,289,148,289,148,293,166,293;182,85,164,85,164,89,182,89;138,289,123,274,126,271,141,286;259,100,256,103,236,91,239,88;136,97,136,101,157,91,157,87;381,258,385,261,374,283,371,279;351,294,368,284,371,289,353,299;88,154,92,154,92,178,88,178;88,185,92,185,95,207,91,207");
	// 25%
	arrayporcentajesventriculografia.push("282,121,279,124,259,112,262,109;308,136,305,139,285,127,288,124;342,293,324,293,324,297,342,297;378,251,368,226,371,222,381,247;364,218,350,197,353,194,367,214;328,159,311,142,314,139,331,156;345,191,333,166,336,164,349,188;296,284,278,284,278,288,296,288;123,268,110,241,113,238,126,264;108,236,98,211,101,207,111,232;319,296,302,290,302,286,319,292;131,107,133,110,117,126,115,123;110,128,112,131,97,147,94,145;192,284,174,284,174,288,192,288;218,284,200,284,200,288,218,288;245,284,227,284,227,288,245,288;272,284,254,284,254,288,272,288;204,91,186,91,186,95,204,95;227,91,209,91,209,95,227,95;166,284,148,284,148,288,166,288;182,91,164,91,164,95,182,95;141,286,126,271,129,268,144,283;256,106,253,109,233,97,236,94;137,102,137,106,158,96,158,92;378,256,382,259,371,281,368,277;346,291,363,281,366,286,348,296;91,152,95,152,95,176,91,176;91,182,95,182,98,204,94,204");
	// 30%
	arrayporcentajesventriculografia.push("286,126,283,129,263,117,266,114;311,141,308,144,288,132,291,129;344,287,326,287,326,291,344,291;375,248,365,223,368,219,378,244;362,219,348,198,351,195,365,215;329,164,312,147,315,144,332,161;344,193,332,168,335,166,348,190;295,278,277,278,277,282,295,282;124,255,111,228,114,225,127,251;108,222,98,197,101,193,111,218;319,290,302,284,302,280,319,286;136,112,138,115,122,131,120,128;115,133,117,136,102,152,99,150;191,277,173,277,173,281,191,281;216,277,198,277,198,281,216,281;241,277,223,277,223,281,241,281;267,277,249,277,249,281,267,281;210,96,192,96,192,100,210,100;233,96,215,96,215,100,233,100;166,277,148,277,148,281,166,281;186,96,168,96,168,100,186,100;142,275,127,260,130,257,145,272;262,111,259,114,239,102,242,99;142,107,142,111,163,101,163,97;378,250,382,253,371,275,368,271;348,284,365,274,368,279,350,289;95,160,99,160,99,184,95,184");
	// 35%
	arrayporcentajesventriculografia.push("289,135,286,138,266,126,269,123;314,150,311,153,291,141,294,138;332,280,314,280,314,284,332,284;362,237,352,212,355,208,365,233;333,174,316,157,319,154,336,171;348,205,336,180,339,178,352,202;287,269,269,269,269,273,287,273;125,250,112,223,115,220,128,246;110,220,100,195,103,191,113,216;310,283,293,277,293,273,310,279;139,119,141,122,125,138,123,135;119,138,121,141,106,157,103,155;191,269,173,269,173,273,191,273;215,269,197,269,197,273,215,273;239,269,221,269,221,273,239,273;263,269,245,269,245,273,263,273;212,105,194,105,194,109,212,109;236,105,218,105,218,109,236,109;167,269,149,269,149,273,167,273;189,105,171,105,171,109,189,109;143,269,128,254,131,251,146,266;264,120,261,123,241,111,244,108;145,115,145,119,166,109,166,105;363,242,367,244,359,268,356,264;336,278,353,268,356,273,338,283;100,163,104,163,104,187,100,187");
	// 40%
	arrayporcentajesventriculografia.push("286,140,283,143,263,131,266,128;310,154,307,157,287,145,290,142;331,275,313,275,313,279,331,279;357,234,347,209,350,205,360,230;329,177,312,160,315,157,332,174;344,204,332,179,335,177,348,201;285,264,267,264,267,268,285,268;130,245,117,218,120,215,133,241;116,215,106,190,109,186,119,211;308,277,291,271,291,267,308,273;145,118,147,121,131,137,129,134;125,137,127,140,112,156,109,154;192,264,174,264,174,268,192,268;215,264,197,264,197,268,215,268;239,264,221,264,221,268,239,268;262,264,244,264,244,268,262,268;212,112,194,112,194,116,212,116;235,112,217,112,217,116,235,116;169,264,151,264,151,268,169,268;190,112,172,112,172,116,190,116;147,263,132,248,135,245,150,260;262,126,259,129,239,117,242,114;150,115,150,120,168,116,168,111;358,237,362,238,356,263,353,259;335,274,353,264,355,268,337,278;106,159,110,159,110,183,106,183");
	// 45%
	arrayporcentajesventriculografia.push("283,148,280,151,260,139,263,136;307,163,304,166,284,154,287,151;329,268,311,268,311,272,329,272;355,227,345,202,348,198,358,223;342,198,328,184,331,181,345,195;324,182,306,168,310,164,328,178;285,258,267,258,267,262,285,262;129,237,118,217,121,213,132,233;115,211,106,190,110,187,118,207;307,271,290,265,290,261,307,267;143,129,147,132,132,144,129,141;126,144,130,147,118,159,113,157;192,257,174,257,174,261,192,261;215,257,197,257,197,261,215,261;239,257,221,257,221,261,239,261;262,257,244,257,244,261,262,261;212,119,194,119,194,123,212,123;234,119,216,119,216,123,234,123;170,257,152,257,152,261,170,261;189,119,171,119,171,123,189,123;147,256,132,241,135,238,150,253;259,134,256,137,236,125,239,122;149,124,149,129,167,125,167,120;357,230,361,231,355,256,352,252;332,266,350,256,352,260,334,270;116,162,111,162,104,183,109,183");
	// 50%
	arrayporcentajesventriculografia.push("284,156,281,159,261,147,264,144;309,171,306,174,286,162,289,159;330,258,312,258,312,262,330,262;357,220,339,199,343,196,360,216;335,197,312,177,313,173,337,192;284,245,266,245,266,249,284,249;132,226,121,206,124,202,135,222;120,201,111,180,115,177,123,197;307,259,290,253,290,249,307,255;135,133,139,136,124,148,121,145;194,245,176,245,176,249,194,249;216,245,198,245,198,249,216,249;239,245,221,245,221,249,239,249;262,245,244,245,244,249,262,249;209,125,191,125,191,129,209,129;232,125,214,125,214,129,232,129;172,245,154,245,154,249,172,249;186,125,168,125,168,129,186,129;149,245,134,230,137,227,152,242;259,141,256,144,236,132,239,129;143,129,143,134,161,130,161,125;358,224,362,225,356,250,353,246;333,259,351,249,353,253,335,263;122,152,117,152,110,173,115,173");
	// 55%
	arrayporcentajesventriculografia.push("282,163,279,166,259,154,262,151;306,177,303,180,283,168,286,165;332,244,314,244,314,248,332,248;338,223,327,201,331,198,343,220;325,198,308,184,309,178,328,194;289,236,271,236,271,240,289,240;136,218,125,200,129,196,139,214;124,196,115,175,119,172,127,192;310,247,293,242,293,237,310,243;140,129,144,132,129,144,126,141;197,236,179,236,179,240,197,240;220,236,202,236,202,240,220,240;243,236,225,236,225,240,243,240;266,236,248,236,248,240,266,240;189,127,171,127,171,131,189,131;212,127,194,127,194,131,212,131;174,236,156,236,156,240,174,240;166,127,148,127,148,131,166,131;152,236,137,221,140,218,155,233;258,149,255,152,235,140,238,137;218,127,215,131,231,138,234,135;339,226,344,227,338,245,334,242;127,147,122,147,115,168,120,168");
	// 60%
	arrayporcentajesventriculografia.push("289,178,286,181,266,169,269,166;407,238,400,239,401,258,408,259;135,206,124,188,128,184,138,202;287,234,270,232,270,227,288,230;148,139,152,142,137,154,134,151;198,227,180,227,180,231,198,231;221,227,203,227,203,231,221,231;243,227,225,227,225,231,243,231;266,227,248,227,248,231,266,231;197,136,179,136,179,140,197,140;219,137,201,137,201,141,219,141;175,227,157,227,157,231,175,231;174,136,156,136,156,140,174,140;153,225,138,210,141,207,156,222;265,163,262,166,242,154,245,151;225,140,222,144,238,151,241,148;136,158,131,158,124,179,129,179;312,224,315,205,321,205,317,229;311,201,290,185,293,180,314,196;308,232,292,235,292,230,307,226");
	// 65%
	arrayporcentajesventriculografia.push("289,183,286,186,266,174,269,171;407,238,400,239,401,258,408,259;135,195,128,175,133,171,140,192;268,225,251,223,251,218,269,221;144,150,148,153,134,166,130,162;200,218,182,218,182,222,200,222;223,218,205,218,205,222,223,222;246,218,228,218,228,222,246,222;194,146,176,146,176,150,194,150;217,146,199,146,199,150,217,150;177,218,159,218,159,222,177,222;172,146,154,146,154,150,172,150;153,215,138,200,141,197,156,212;265,169,262,172,242,160,245,157;224,147,221,151,237,158,240,155;289,209,290,190,295,192,294,214;289,222,273,225,273,220,288,216");
	// 70%
	arrayporcentajesventriculografia.push("407,238,400,239,401,258,408,259;137,194,130,174,135,170,142,191;247,220,230,218,230,213,248,216;147,152,151,155,137,168,133,164;200,213,182,213,182,217,200,217;224,213,206,213,206,217,224,217;194,150,176,150,176,154,194,154;217,150,199,150,199,154,217,154;177,213,159,213,159,217,177,217;172,150,154,150,154,154,172,154;155,212,140,197,143,194,158,209;265,176,262,179,242,167,245,164;224,153,221,157,237,164,240,161;266,201,267,182,272,184,271,206;267,214,253,219,253,214,267,208");
}

function dibujar_ventriculografia() {
	var arreglo = new Array();
	var ctx = document.getElementById('ventriculografia').getContext('2d');
	arreglodibujorealizadosventriculografia = arrayporcentajesventriculografia[fevi].split(';');
	ctx.fillStyle = "red";
	ctx.strokeStyle="red";
	ctx.lineWidth=2; 
	for ( var i = 0; i < arreglodibujorealizadosventriculografia.length; i++) {
		arreglo = arreglodibujorealizadosventriculografia[i].split(',');
		ctx.beginPath();
		ctx.moveTo(arreglo[0], arreglo[1]);
		for ( var item = 2; item < arreglo.length - 1; item += 2) {
			ctx.lineTo(arreglo[item], arreglo[item + 1]);
	
		}
		
		ctx.closePath();
		ctx.stroke();
		ctx.fill();

	}
}

function control_Informe_Ventriculografia(codElemento) {
	switch (codElemento) {
	case 1:
		ventriculoizqdilatado = recopilarindexCombobox('seleccion_ventriculo_izq_dilatado');
		break;
	case 2:
		
		 tipocontractibilidad = recopilarindexCombobox('seleccion_contratibilidad_1');
		 if(tipocontractibilidad > 0){
			 activarElementosVentriculografia(1);
		 }
		 else{
			 desactivarElementosVentriculografia(1);
			 reiniciarVariablesVentriculografia(1);
			 reemplazarindexCombobox('seleccion_contratibilidad_2', 0);
			 reemplazarindexCombobox('seleccion_contratibilidad_3', 0);
			 reemplazarindexCombobox('seleccion_contratibilidad_4', 0);
		 }
		 
		 
		 
		 break;
		 
	case 3:
		gravedadcontractibilidad = recopilarindexCombobox('seleccion_contratibilidad_2');

		break;
		 
	case 4:
		 caracteristicacontractibilidad = recopilarindexCombobox('seleccion_contratibilidad_3');
		
		break;
	case 5:
		 lugarcontractibilidad = recopilarindexCombobox('seleccion_contratibilidad_4');
		break;
	case 6:
		fevi = recopilarindexCombobox('seleccion_FEVI');
		actualizarDibujoVentriculografia();
		dibujar_ventriculografia();
		break;
	case 7:
		aspectovalvulamitral = recopilarindexCombobox('seleccion_aspecto_valvulamitral');
		break;

	case 8:
		insuficienciamitral = recopilarindexCombobox('seleccion_insuficiencia_valvulamitral');

		break;

	}
	recopilarInformeVentriculografia();

}

function activarElementosVentriculografia(idElemento){
	
	switch(idElemento){
	
	case 1:
		    activarelementoFormulario("seleccion_contratibilidad_2");
			activarelementoFormulario("seleccion_contratibilidad_3");
			activarelementoFormulario("seleccion_contratibilidad_4");
	break;
	}
	
}

function desactivarElementosVentriculografia(idElemento){
	
	switch(idElemento){
	
	case 1:
		desactivarelementoFormulario("seleccion_contratibilidad_2");
		desactivarelementoFormulario("seleccion_contratibilidad_3");
		desactivarelementoFormulario("seleccion_contratibilidad_4");
		
	break;
	}
}

function reiniciarVariablesVentriculografia(id){
	
	switch(id){
	
	case 1:
		caracteristicacontractibilidad = 0;
		lugarcontractibilidad = 0;
		gravedadcontractibilidad =0;
		break;
	
	}
	
}

function reiniciar_Elementos_Formulario_Ventriculografia() {
	reemplazarindexCombobox('seleccion_ventriculo_izq_dilatado', 0);
	reemplazarindexCombobox('seleccion_contratibilidad_1', 0);
	reemplazarindexCombobox('seleccion_contratibilidad_2', 0);
	reemplazarindexCombobox('seleccion_contratibilidad_3', 0);
	reemplazarindexCombobox('seleccion_contratibilidad_4', 0);
	reemplazarindexCombobox('seleccion_FEVI', 0);
	reemplazarindexCombobox('seleccion_insuficiencia_valvulamitral', 0);
	reemplazarindexCombobox('seleccion_aspecto_valvulamitral', 0);
	desactivarElementosVentriculografia(1);
	swEnviarInformeVentriculografia = 0;
}

/** *********************************************** */
/** **********Funciones Informe Aortograma********* */
/** *********************************************** */

function carga_logica_informe_aortograma() {
	iniciar_variables_aortograma();
}
function iniciar_variables_aortograma() {
	raizaortica = 0;
	txtraizaorticamedicion = "";
	aortaascendente = 0;
	txtaortaascendentemedicion = "";
	clasevalvulaAortica = 0;
	aspectovalvulaAortica = 0;
	insuficienciaAortica = 0;
	estenosisaortica = 0;
	txtgradientepicopico = "";
	swEnviarInformeAortograma = 1;
}

function control_Informe_Aortograma(codElemento) {
	switch (codElemento) {
	case 1:
		raizaortica = recopilarindexCombobox('seleccion_raiz_aortica');
		if (raizaortica > 0) {
			activarElementosAortograma(1);
		} else {
			reiniciarVariablesAortograma(1);
			desactivarElementosAortograma(1);
		}
		break;
	case 2:
		txtraizaorticamedicion = recopilarinfotextbox('txt_medicion_raiz_aortica');
		if (tiene_letras(txtraizaorticamedicion) == 1) {
			reemplazarinfotextbox('txt_medicion_raiz_aortica', "");
			reiniciarVariablesAortograma(1);
			alert('escriba solo datos numericos');
		}

		break;
		
	case 3:
		
		aortaascendente = recopilarindexCombobox('seleccion_aorta_ascendente');
		if (aortaascendente > 0) {
			activarElementosAortograma(2);
		} else {
			reiniciarVariablesAortograma(2);
			desactivarElementosAortograma(2);
		}
		
		
		break;
		
	case 4:
		txtaortaascendentemedicion = recopilarinfotextbox('txt_medicion_aorta_ascendente');
		if (tiene_letras(txtaortaascendentemedicion) == 1) {
			reemplazarinfotextbox('txt_medicion_aorta_ascendente', "");
			reiniciarVariablesAortograma(2);
			alert('escriba solo datos numericos');
		}
		
		break;
		
	case 5:
		clasevalvulaAortica = recopilarindexCombobox('seleccion_clase_valvula_aortica');
		break;
	case 6:
		aspectovalvulaAortica = recopilarindexCombobox('seleccion_aspecto_valvula_aortica');
		break;

	case 7:
		insuficienciaAortica = recopilarindexCombobox('seleccion_insuficiencia_aortica');
		break;
	case 8:
		estenosisaortica = recopilarindexCombobox('seleccion_estenosis_aortica');
		if (estenosisaortica > 0) {
			activarElementosAortograma(3);
		} else {
			reiniciarVariablesAortograma(3);
			desactivarElementosAortograma(3);
		}
		break;
	case 9:
		txtgradientepicopico = recopilarinfotextbox('txt_medicion_gradiente_pico_pico');
		if (tiene_letras(txtgradientepicopico) == 1) {
			reemplazarinfotextbox('txt_medicion_gradiente_pico_pico', "");
			reiniciarVariablesAortograma(3);
			alert('escriba solo datos numericos para la medicion del gradiente pico pico');
		}
		break;
	}
	recopilarInformeAortograma();
}

function activarElementosAortograma(id_activacion) {
	switch (id_activacion) {
	case 1:
		activarelementoFormulario('txt_medicion_raiz_aortica');
		enfocarMouseEnElemento('txt_medicion_raiz_aortica');
		break;

	case 2:
		activarelementoFormulario('txt_medicion_aorta_ascendente');
		enfocarMouseEnElemento('txt_medicion_aorta_ascendente');
		break;
		
	case 3:
		activarelementoFormulario('txt_medicion_gradiente_pico_pico');
		enfocarMouseEnElemento('txt_medicion_gradiente_pico_pico');
		break;
	}
}

function desactivarElementosAortograma(id_desactivacion) {
	switch (id_desactivacion) {

	case 1:
		desactivarelementoFormulario("txt_medicion_raiz_aortica");
		reemplazarinfotextbox('txt_medicion_raiz_aortica', "");

		break;
		
		
	case 2:
		desactivarelementoFormulario("txt_medicion_aorta_ascendente");
		reemplazarinfotextbox('txt_medicion_aorta_ascendente', "");
		
		break;
	case 3:
		desactivarelementoFormulario("txt_medicion_gradiente_pico_pico");
		reemplazarinfotextbox('txt_medicion_gradiente_pico_pico', "");
		break;
	}
}
function reiniciarVariablesAortograma(id_reiniciar) {
	switch (id_reiniciar) {
	case 1:
		// campo txt raiz aortica
		txtraizaorticamedicion = "";
		break;
		
	case 2:
		// campo txt aorta ascendente
		txtaortaascendentemedicion ="";
		break;
		
	case 3:
		// campo txt gradiente pico pico
		txtgradientepicopico = "";
		break;
	}
}

function tiene_letras(texto) {
	var letras = "abcdefghyjklmnñopqrstuvwxyz";
	texto = texto.toLowerCase();
	for ( var i = 0; i < texto.length; i++) {
		if (letras.indexOf(texto.charAt(i), 0) != -1) {
			return 1;
		}
	}
	return 0;
}




function tiene_numeros(){
	var numeros="0123456789";
    var texto = recopilarinfotextbox("txt_anesteciologo");
	  for(var i=0; i<texto.length; i++){
	      if (numeros.indexOf(texto.charAt(i),0)!=-1){
	    	  alert('escriba solamente letras en el nombre del anestesiologo');
	    	  reemplazarinfotextbox("txt_anesteciologo", "");
	      }
	   }
}


function reiniciar_elementos_Formulario_Aortograma() {
	reemplazarindexCombobox('seleccion_raiz_aortica', 0);
	reemplazarinfotextbox('txt_medicion_raiz_aortica', "");
	reemplazarindexCombobox('seleccion_aorta_ascendente', 0);
	reemplazarinfotextbox('txt_medicion_aorta_ascendente', "");
	reemplazarindexCombobox('seleccion_clase_valvula_aortica', 0);
	reemplazarindexCombobox('seleccion_aspecto_valvula_aortica', 0);
	reemplazarindexCombobox('seleccion_insuficiencia_aortica', 0);
	reemplazarindexCombobox('seleccion_estenosis_aortica', 0);
	reemplazarinfotextbox('txt_medicion_gradiente_pico_pico', "");
	swEnviarInformeAortograma = 0;
}

/** *********************************************** */
/** *****Funciones Informe ByPass ***************** */
/** *********************************************** */

function carga_logica_informe_bypass(){
	inicializar_variables_bypass();
}

function inicializar_variables_bypass(){
	cadenaInformaBypass = "";
	swEnviarInformeBypass = 1;
}

function reiniciar_elementos_Formulario_Bypass() {
	cadenaInformaBypass = "";
	reemplazarinfotextbox("bypass", cadenaInformaBypass);
	swEnviarInformeBypass = 0;
}

function control_Informe_Bypass(codElemento){
	
	switch(codElemento){
	case 1:
		cadenaInformaBypass = recopilarinfotextbox("bypass");
	//	reemplazarinfotextbox("contenido_informe_bypass",cadenaInformaBypass );
	   	break;
	}
	recopilarInformeBypass();
}

/** *********************************************** */
/** *****Funciones Informe Anatomia Coronaria****** */
/** *********************************************** */

function carga_logica_informe_anatomia_coronaria() {
	inicializar_variables_anatomia_coronaria();
	actualizarDibujoCanvasAnatomiaCoronaria();
	reiniciar_control_seleccion_lesion();
	cargar_areas_de_seleccion_arterias();
	cargar_coordenadas_de_lesiones();
}

function arteriasbifurcadas() {
		ajax = getXMLObject();
		ajax.open("POST", "cateterismodata", true); // getname will be the
		// servlet name
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
				var datos = ajax.responseText.split(',');
			   for(var i = 0 ; i < datos.length ;i++){
            	   arterias_bifurcadas[i]=datos[i];
               }
               
               
   			}
		};
		
		ajax.setRequestHeader('Content-Type',
				'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("opcion=1"); // Posting txtname
		// to Servlet
	
}

function inicializar_variables_anatomia_coronaria() {
	// indice para el posicionamiento en los vectores para
	// saber la arteria seleccionada
	indiceVectores = 0;
	// clave indicadora para saber que arteria se esta manipulando
	// arreglos para guardar desglosadamente todos y cada uno de los datos
	// seleccionados en el cateterismo
	clave_analisis = "";
	// arreglo de datos para enviar para base de datos
	porcentaje_localizacion_lesion_arterias = new Array(numeroItemsCombobox("Seleccion_Arteria_combo"));
	tipo_lesion_por_oclusion_arterias = new Array(numeroItemsCombobox("Seleccion_Arteria_combo"));
	lesion_circulacion_colateral_arterias = new Array(numeroItemsCombobox("Seleccion_Arteria_combo"));
	caracteristicas_lesion_arterias = new Array(numeroItemsCombobox("Seleccion_Arteria_combo"));
	flujo_timi_arterias = new Array(numeroItemsCombobox("Seleccion_Arteria_combo"));
	tipo_lesion_arterias = new Array(numeroItemsCombobox("Seleccion_Arteria_combo"));
	lecho_distal_arterias = new Array(numeroItemsCombobox("Seleccion_Arteria_combo"));
	haybifurcacion_arterias = new Array(numeroItemsCombobox("Seleccion_Arteria_combo"));
	clasificacionMedina_arterias = new Array(numeroItemsCombobox("Seleccion_Arteria_combo"));
	// arreglos de dibujos de las lesiones de cada arteria
	arrayposicioneslocalizacionProximales = new Array(numeroItemsCombobox("Seleccion_Arteria_combo"));
	arrayposicioneslocalizacionMedios = new Array(numeroItemsCombobox("Seleccion_Arteria_combo"));
	arrayposicioneslocalizacionDistales = new Array(numeroItemsCombobox("Seleccion_Arteria_combo"));
	// areas para hacer click en el canvas
	array_areas_de_seleccion = new Array();
	// control de lesiones seleccionadas al momento
	// de hacer click en el canvas
	array_control_seleccion_lesion = new Array();
	// arreglo de coordenadas dibujadas con los nombres de los dibujos
	arreglonombresdibujosrealizadoscoronaria = new Array();
	arreglodibujosrealizadoscoronaria = new Array();
	swEnviarInformeAnatomiaCoronia = 1;
}


function actualizarDibujoCanvasAnatomiaCoronaria() {
	var canvas = document.getElementById('anatomia_coronaria');
	//canvas.width = canvas.width;
	if (canvas.getContext) {
		var ctx = canvas.getContext('2d');
		var img = new Image();
		img.src = 'Imagenes/corazon.png';
  	   //defino el evento onload del objeto imagen
		canvas.width = 600;
		canvas.height = 430;
	
		 ctx.drawImage(img, 0, 0, canvas.width, canvas.height);
		
		} else {
		alert('You need Safari or Firefox 1.5+ to see this demo.');
	}
}

function dibujarAreasArterias(sw_hay_lesion) {

	refrescarArteriaenAnatomiaCoronaria(clave_analisis);
	actualizarDibujoCanvasAnatomiaCoronaria();
	if (sw_hay_lesion == true) {
		agregarDibujosAnatomiaCoronaria();
		dibujar_anatomia_coronaria();
	} else {
    	dibujar_anatomia_coronaria();
	}
}

function refrescarArteriaenAnatomiaCoronaria(claveanalisis) {
	for (i = 0, j = arreglonombresdibujosrealizadoscoronaria.length; i < j; i++) {
		if (arreglonombresdibujosrealizadoscoronaria[i] == claveanalisis) {
			arreglonombresdibujosrealizadoscoronaria.splice(i, 1);
			arreglodibujosrealizadoscoronaria.splice(i, 1);
			i--;
		}
	}
}

function agregarDibujosAnatomiaCoronaria() {
	var porcentajes_seleccionados = porcentaje_localizacion_lesion_arterias[indiceVectores].split('-');
	var array_aux = new Array();
	var opcion_dibujo=0;
	for ( var i = 0; i < porcentajes_seleccionados.length; i++) {
		if (porcentajes_seleccionados[i] > 0) {
			arreglonombresdibujosrealizadoscoronaria.push(clave_analisis);
			
			if (porcentajes_seleccionados[i]<=2){
				opcion_dibujo =0;
			}
			else if(porcentajes_seleccionados[i]<=4){
				opcion_dibujo =1;
			}
			else if(porcentajes_seleccionados[i]<=6){
				opcion_dibujo=2;
			}
			else if(porcentajes_seleccionados[i]<=8){
				opcion_dibujo=3;
			}
			else if (porcentajes_seleccionados[i]==(numeroItemsCombobox("porcentaje_lesion_seleccion"+clave_analisis+"_"+tipo_lesiones_por_arteria[0]+"_combo")-2)){
				opcion_dibujo=4;
			}
			else{
				opcion_dibujo=5;
			}
			
			if(tipo_lesiones_por_arteria[i]=='proximal'){
				array_aux = arrayposicioneslocalizacionProximales[indiceVectores];
				arreglodibujosrealizadoscoronaria.push(array_aux[opcion_dibujo]);
			}
			else if(tipo_lesiones_por_arteria[i]=='medio'){
				array_aux = arrayposicioneslocalizacionMedios[indiceVectores];
				arreglodibujosrealizadoscoronaria.push(array_aux[opcion_dibujo]);
			}
			else if (tipo_lesiones_por_arteria[i]=='distal'){
				array_aux = arrayposicioneslocalizacionDistales[indiceVectores];
				arreglodibujosrealizadoscoronaria
						.push(array_aux[opcion_dibujo]);
	
			}

		}

	}
}

function dibujar_anatomia_coronaria() {
	var arreglo = new Array();
	var ctx = document.getElementById('anatomia_coronaria').getContext('2d');
	ctx.fillStyle = "red";
	ctx.strokeStyle="red";
	ctx.lineWidth=2; 
	var minavegador = new Browser();
	
	if(minavegador.name=='chrome'){
		for ( var i = 0; i < arreglodibujosrealizadoscoronaria.length; i++) {
			arreglo = arreglodibujosrealizadoscoronaria[i].split(',');
			
			ctx.beginPath();
			ctx.moveTo(arreglo[0], arreglo[1]);
			for ( var item = 2; item < arreglo.length - 1; item += 2) {
				ctx.lineTo(arreglo[item], arreglo[item + 1]);
			}
			ctx.closePath();
			ctx.stroke();
		}
	}
	else{
		for ( var i = 0; i < arreglodibujosrealizadoscoronaria.length; i++) {
			arreglo = arreglodibujosrealizadoscoronaria[i].split(',');
			ctx.beginPath();
			ctx.moveTo(arreglo[0], arreglo[1]);
			for ( var item = 2; item < arreglo.length - 1; item += 2) {
				ctx.lineTo(arreglo[item], arreglo[item + 1]);
			}
			ctx.closePath();
			ctx.stroke();
			ctx.fill();
		}
	}
	
}

/** ***************************************** */
function control_Informe_Anatomia_Coronaria(opcion) {
	
	
	//vector de localizacion (proximal, medio,distal)
	switch (opcion) {
	case 1:
		// % lesion
		var cadenaporcentajelesiones ="";
		for (var i=0; i< tipo_lesiones_por_arteria.length ; i++){
			cadenaporcentajelesiones += recopilarindexCombobox('porcentaje_lesion_seleccion'+clave_analisis+'_'+tipo_lesiones_por_arteria[i]+'_combo')+"-";
		}
		cadenaporcentajelesiones = cadenaporcentajelesiones.substring(0, cadenaporcentajelesiones.length-1);
	    porcentaje_localizacion_lesion_arterias[indiceVectores] = cadenaporcentajelesiones;
	    var porcentajes_seleccionados = porcentaje_localizacion_lesion_arterias[indiceVectores].split('-');
		var contadorsinlesiones = 0;
		for ( var i = 0; i < porcentajes_seleccionados.length; i++) {
			if (porcentajes_seleccionados[i] > 0) {
				
				if (porcentajes_seleccionados[i] == numeroItemsCombobox('porcentaje_lesion_seleccion'+ clave_analisis+"_"+tipo_lesiones_por_arteria[0]+ '_combo') - 1) { // 6 indica que hay
					// obstrucion al 100%
					activarElementosAnatomiaCoronaria(2,i);
				}
				else{
					desactivarElementosAnatomiaCoronaria(2,i);
					reiniciarElementosAnatomiaCoronaria(2);
				}
				activarElementosAnatomiaCoronaria(1,i);
		
			}else{
				dibujarAreasArterias(false);
				desactivarElementosAnatomiaCoronaria(1,i);
				reiniciarElementosAnatomiaCoronaria(1);
    			contadorsinlesiones++;
			}
		}
		
	if(contadorsinlesiones == porcentajes_seleccionados.length){
		/*if(clave_analisis == '_TCI'){
		   	reemplazarinfotextbox('observacion'+clave_analisis, 'De buen desarrollo y distribuci\u00f3n , sin lesiones significativas.');
		   }else{
		   	reemplazarinfotextbox('observacion'+clave_analisis, 'De buen desarrollo y distribuci\u00f3n, sin lesiones significativas '+recopilarindexCombobox('lecho_distal'+ clave_analisis + '_combo') + '.');
		    }*/
			reiniciarElementosAnatomiaCoronaria(1);
	}else{
		dibujarAreasArterias(true);
		
	}
	control_areas_Seleccionadas();
		break;

	case 2:
		// seleccion del tipo de oclusion
		var cadenalesionesporoclusion ="";
		for (var i=0; i< tipo_lesiones_por_arteria.length ; i++){
			if(!estadoElemento('lesion_oclusion_total'+clave_analisis+'_'+tipo_lesiones_por_arteria[i]+"_combo")){
				cadenalesionesporoclusion += (recopilarindexCombobox('lesion_oclusion_total'+ clave_analisis +'_'+tipo_lesiones_por_arteria[i]+ '_combo')+2)+"-";
				
			}else{
				cadenalesionesporoclusion += (recopilarindexCombobox('lesion_oclusion_total'+ clave_analisis +'_'+tipo_lesiones_por_arteria[i]+ '_combo')+1)+"-";
			
			}
		}
		cadenalesionesporoclusion = cadenalesionesporoclusion.substring(0, cadenalesionesporoclusion.length-1);
		tipo_lesion_por_oclusion_arterias[indiceVectores] = cadenalesionesporoclusion;
		
		break;

	case 3:
		// seleccion de lesiones de circulacion colateral
		
		var cadenalesioncirculacion ="";
		for (var i=0; i< tipo_lesiones_por_arteria.length ; i++){
			
			
				cadenalesioncirculacion += (recopilarindexCombobox('tipo_lesion_circulacion_colateral'+clave_analisis+'_'+tipo_lesiones_por_arteria[i]+'_combo')+1)+"-";
			
		
		}
		cadenalesioncirculacion = cadenalesioncirculacion.substring(0, cadenalesioncirculacion.length-1);
		lesion_circulacion_colateral_arterias[indiceVectores] = cadenalesioncirculacion;
		
		break;
	case 4:
		// caracteristicas de las lesiones
		
		var cadenacaracteristicas ="";
		for (var i=0; i< tipo_lesiones_por_arteria.length ; i++){
			
			if(elementoEscondido("caracteristicas"+clave_analisis+'_'+tipo_lesiones_por_arteria[i])!= 'none'){
				cadenacaracteristicas += (recopilarindexCombobox('Caracteristicas_lesion'+clave_analisis+'_'+tipo_lesiones_por_arteria[i]+'_combo')+2)+"-";
			}else{
				cadenacaracteristicas += (recopilarindexCombobox('Caracteristicas_lesion'+clave_analisis+'_'+tipo_lesiones_por_arteria[i]+'_combo')+1)+"-";
			}
		}
		cadenacaracteristicas = cadenacaracteristicas.substring(0, cadenacaracteristicas.length-1);
		caracteristicas_lesion_arterias[indiceVectores] = cadenacaracteristicas;
		
		
		break;
	case 5:
		// seleccion flujo timi
		flujo_timi_arterias[indiceVectores] = recopilarindexCombobox('flujo_timi'
				+ clave_analisis + '_combo')+2;
		break;

	case 6:
		// seleccion tipo lesion (a,b,c)
		
		
		var cadenatipoabclesiones ="";
		for (var i=0; i< tipo_lesiones_por_arteria.length ; i++){
		    if(elementoEscondido("caracteristicas"+clave_analisis+'_'+tipo_lesiones_por_arteria[i])!= 'none'){
				cadenatipoabclesiones += (recopilarindexCombobox('tipo_de_lesion'+clave_analisis+'_'+tipo_lesiones_por_arteria[i]+'_combo')+2)+"-";
			}else{
				cadenatipoabclesiones += (recopilarindexCombobox('tipo_de_lesion'+clave_analisis+'_'+tipo_lesiones_por_arteria[i]+'_combo')+1)+"-";
			}
		}
		cadenatipoabclesiones = cadenatipoabclesiones.substring(0, cadenatipoabclesiones.length-1);
		tipo_lesion_arterias[indiceVectores] = cadenatipoabclesiones;
		break;

	case 7:
		// seleccion lecho distal
		lecho_distal_arterias[indiceVectores] = recopilarindexCombobox('lecho_distal'+ clave_analisis + '_combo')+1;
		
		break;
		
    case 8:
		// seleccion bifucacion (para arteria descendente anterior y
		// circunsfleja)
    
    	var l = 0;
    	var posles=0;
    	for (l=0; l <  tipo_lesiones_por_arteria.length ; l++){
    		if ( tipo_lesiones_por_arteria[l]== idtipolesion){
    			posles = l;
    			l = tipo_lesiones_por_arteria.length;
    		}
    	}
    	
    	
		if (recopilarindexCombobox('bifurcada' + clave_analisis+ '_'+idtipolesion+ '_combo') == 0) { // no
			desactivarElementosAnatomiaCoronaria(3,posles);
			reiniciarElementosAnatomiaCoronaria(3,posles);
		} else {
			activarElementosAnatomiaCoronaria(3,posles);
		} 

		break;
	case 9:
		
		var cadenabifurcacionesramasenarteria ="";
		for (var i=0; i< tipo_lesiones_por_arteria.length ; i++){
    		if(recopilarindexCombobox("bifurcada"+ clave_analisis +'_'+tipo_lesiones_por_arteria[i]+ '_combo')>0){
				cadenabifurcacionesramasenarteria += (recopilarValueCombobox('con_la_rama'+clave_analisis+'_'+tipo_lesiones_por_arteria[i]+'_combo'))+"-";
			}
			else{
				cadenabifurcacionesramasenarteria += "1"+"-";
				
			}
		
		}
		cadenabifurcacionesramasenarteria = cadenabifurcacionesramasenarteria.substring(0, cadenabifurcacionesramasenarteria.length-1);
		haybifurcacion_arterias[indiceVectores] = cadenabifurcacionesramasenarteria;

		break;

	default:
	var clasificacionmedinaarterialesiones ="";
	        
	
	for (var i=0; i< tipo_lesiones_por_arteria.length ; i++){
		
		if(recopilarindexCombobox("bifurcada"+ clave_analisis +'_'+tipo_lesiones_por_arteria[i]+ '_combo')>0){
			clasificacionmedinaarterialesiones += (recopilarindexCombobox('clasificacion_medina_seleccion'+clave_analisis+'_'+tipo_lesiones_por_arteria[i]+'_combo')+2)+"-";
		}
		else{
			clasificacionmedinaarterialesiones += (recopilarindexCombobox('clasificacion_medina_seleccion'+clave_analisis+'_'+tipo_lesiones_por_arteria[i]+'_combo')+1)+"-";
			
		}
	}
	clasificacionmedinaarterialesiones = clasificacionmedinaarterialesiones.substring(0, clasificacionmedinaarterialesiones.length-1);
//	var elementocambiado = 0;
	var indicecambiado = 0;
	if(clasificacionMedina_arterias[indiceVectores]!= null){
	    var t =0;
		var aux1 = clasificacionmedinaarterialesiones.split("-");
		var aux2 = clasificacionMedina_arterias[indiceVectores].split("-");
	    for(t = 0; t < aux2.length ; t++ ){
	    	
	    	if (aux1[t]!= aux2[t]){
	    		
	    		indicecambiado = t;
	    		t = aux2.length;
	    	}
	    }
	}/*else{
		var aux = clasificacionmedinaarterialesiones.split("-");
		var p = 0;
		for (p=0; p < p < aux.length;p++){
			
			if(aux[p]>elementocambiado){
				elementocambiado = aux[p];
				indicecambiado = p;
			}
		}
	}*/
	    clasificacionMedina_arterias[indiceVectores] = clasificacionmedinaarterialesiones;
		cambiarImagenMedina(clave_analisis +'_'+tipo_lesiones_por_arteria[indicecambiado],recopilarindexCombobox('clasificacion_medina_seleccion'	+ clave_analisis +'_'+tipo_lesiones_por_arteria[indicecambiado]+ '_combo'));
		break;
	}
   recopilarInformeAnatomiaCoronariaPorArterias();
   recopilarInformeAnatomiaCoronaria();


}




function verificarContenidoObservacionesArteria(){ // funcion para recopilar ediciones en cuadros de texto de la descripcion de las lesiones de las arterias
	if(tamanoinfotextbox("observacion"+clave_analisis)==0){
		reemplazarindexCombobox('porcentaje_lesion_proximal_seleccion'+ clave_analisis + '_combo',0);
		reemplazarindexCombobox('porcentaje_lesion_medio_seleccion'+ clave_analisis + '_combo',0);
		reemplazarindexCombobox('porcentaje_lesion_distal_seleccion'+ clave_analisis + '_combo',0);
		control_Informe_Anatomia_Coronaria(1);
	}
	recopilarInformeAnatomiaCoronaria();
	
}



function recopilarInformeAnatomiaCoronariaPorArterias(){
	var cadenaInformePorArteria = "";
	//alert("(true descativado y false activado) "+ estadoElemento('observacion'+clave_analisis));
    
	if(!estadoElemento('observacion'+clave_analisis)){
		cadenaInformePorArteria += "De buen Desarrollo y Distribuci\u00f3n con";
		var j = 0;
		while (j < tipo_lesiones_por_arteria.length){
			if(recopilarindexCombobox('porcentaje_lesion_seleccion'+clave_analisis+"_"+tipo_lesiones_por_arteria[j]+'_combo')>0){
				
				cadenaInformePorArteria += " lesi\u00f3n de "+recopilarTextCombobox('porcentaje_lesion_seleccion'+clave_analisis+"_"+tipo_lesiones_por_arteria[j]+'_combo')+" en el segmento "+tipo_lesiones_por_arteria[j];
				cadenaInformePorArteria += " , "+recopilarTextCombobox('tipo_de_lesion'+ clave_analisis+"_"+tipo_lesiones_por_arteria[j]+ '_combo');
				if (!estadoElemento('lesion_oclusion_total'+ clave_analisis +"_"+tipo_lesiones_por_arteria[j]+ '_combo')) {// esta bloqueado??
					cadenaInformePorArteria += ' , por oclusi\u00f3n '+ recopilarTextCombobox('lesion_oclusion_total'+ clave_analisis +"_"+tipo_lesiones_por_arteria[j]+ '_combo');
				}
			 	cadenaInformePorArteria += " , "+ recopilarTextCombobox('Caracteristicas_lesion'+ clave_analisis +"_"+tipo_lesiones_por_arteria[j]+ '_combo');
			   if(esArteriabifurcada(clave_analisis)){
				   if (!estadoElemento('con_la_rama' + clave_analisis +"_"+tipo_lesiones_por_arteria[j]+ '_combo')) {
						cadenaInformePorArteria += ' , bifurcada con la rama '+ recopilarTextCombobox('con_la_rama'+ clave_analisis+"_"+tipo_lesiones_por_arteria[j] + '_combo');
						cadenaInformePorArteria += ' , con una clasificaci\u00f3n de medina '+ recopilarTextCombobox('clasificacion_medina_seleccion'+ clave_analisis +"_"+tipo_lesiones_por_arteria[j]+ '_combo');
					}
				   
			   }
			   if (!estadoElemento('tipo_lesion_circulacion_colateral'+ clave_analisis +"_"+tipo_lesiones_por_arteria[j]+ '_combo')) {
					if (recopilarindexCombobox('tipo_lesion_circulacion_colateral'+ clave_analisis+"_"+tipo_lesiones_por_arteria[j] + '_combo') > 0) {
						cadenaInformePorArteria += ' , con circulaci\u00f3n '+ recopilarTextCombobox('tipo_lesion_circulacion_colateral' + clave_analisis +"_"+tipo_lesiones_por_arteria[j]+ '_combo');
					}
				}
			   cadenaInformePorArteria +=" ,";
			   
			}
			
			
			j++;
				
			}
		cadenaInformePorArteria = cadenaInformePorArteria.substring(0, cadenaInformePorArteria.length-1);
		if (!estadoElemento('flujo_timi' + clave_analisis + '_combo')){
			cadenaInformePorArteria += ", con flujo timi "+recopilarTextCombobox('flujo_timi' + clave_analisis + '_combo');
		}
		
		if (!estadoElemento('lecho_distal' + clave_analisis + '_combo')){
			cadenaInformePorArteria += " , de "+recopilarValueCombobox('lecho_distal' + clave_analisis + '_combo');
		}
		cadenaInformePorArteria += ".";
		reemplazarinfotextbox("observacion"+clave_analisis,cadenaInformePorArteria);
	
		}else{
			if(clave_analisis == '_TCI'){
			   	reemplazarinfotextbox('observacion'+clave_analisis, 'De buen desarrollo y distribuci\u00f3n , sin lesiones significativas.');
			   }else{
			   	reemplazarinfotextbox('observacion'+clave_analisis, 'De buen desarrollo y distribuci\u00f3n, sin lesiones significativas con '+recopilarValueCombobox('lecho_distal'+ clave_analisis + '_combo') + '.');
			}
			
		}
	
	}

function esArteriabifurcada(clave_analisis){
	for(var i = 0; i < arterias_bifurcadas.length ; i++){
		if (clave_analisis == arterias_bifurcadas[i]){
			return true;
		}
	}
	return false;
}

function elementoEscondido(nombreelemento){
	return document.getElementById(nombreelemento).style.display;
	
}



/** ***************************************** */
function activarElementosAnatomiaCoronaria(opcion,arteria) {
	switch (opcion) {
	case 1:
		
		activarelementoFormulario('observacion'+clave_analisis);
		aparecerElemento('caracteristicas'+clave_analisis+'_'+tipo_lesiones_por_arteria[arteria]);
		activarelementoFormulario('flujo_timi' + clave_analisis + '_combo');
		activarelementoFormulario('lecho_distal'+clave_analisis+'_combo');
		
		if (esArteriabifurcada(clave_analisis)) {
		       	i =0;
	        	cadena1="";
	    		cadena2="";

		   		while (i < tipo_lesiones_por_arteria.length){
		   			if(recopilarindexCombobox("bifurcada"+ clave_analisis +'_'+tipo_lesiones_por_arteria[i]+ '_combo')>0){
						cadena1 +=(recopilarValueCombobox('con_la_rama'+ clave_analisis +'_'+tipo_lesiones_por_arteria[i]+ '_combo'))+"-";
						cadena2 +=(recopilarindexCombobox('clasificacion_medina_seleccion'+ clave_analisis +'_'+tipo_lesiones_por_arteria[i]+ '_combo')+2)+"-";
						activarelementoFormulario('con_la_rama' + clave_analisis+'_'+tipo_lesiones_por_arteria[i]+ '_combo');
						activarelementoFormulario('clasificacion_medina_seleccion'+ clave_analisis +'_'+tipo_lesiones_por_arteria[i]+ '_combo');
						aparecerElemento('imagen_clasificacion_medina' + clave_analisis+'_'+tipo_lesiones_por_arteria[i]);
		   			
		   			}
					else{
						
						desactivarelementoFormulario('con_la_rama' + clave_analisis+'_'+tipo_lesiones_por_arteria[i]+ '_combo');
						desactivarelementoFormulario('clasificacion_medina_seleccion'+ clave_analisis +'_'+tipo_lesiones_por_arteria[i]+ '_combo');
			         	desaparecerElemento('imagen_clasificacion_medina' + clave_analisis+'_'+tipo_lesiones_por_arteria[i]);
						cadena1 += "1"+"-";
						cadena2 +=(recopilarindexCombobox('clasificacion_medina_seleccion'+ clave_analisis +'_'+tipo_lesiones_por_arteria[i]+ '_combo')+1)+"-";
						
					}	
		   			i++;
	    		}
		   		
				cadena1=cadena1.substring(0, cadena1.length-1);
				cadena2=cadena2.substring(0, cadena2.length-1);
			
				haybifurcacion_arterias[indiceVectores] = cadena1 ;
				clasificacionMedina_arterias[indiceVectores] = cadena2 ;

				
				
			
		}
		cadena1="";
		cadena2="";
		i =0;
		
		while (i < tipo_lesiones_por_arteria.length){
			
			if(elementoEscondido('caracteristicas'+clave_analisis+'_'+tipo_lesiones_por_arteria[i])=='none'){
				
				cadena1 += (recopilarindexCombobox('Caracteristicas_lesion'+ clave_analisis +'_'+tipo_lesiones_por_arteria[i]+ '_combo')+1)+"-";
				cadena2 += (recopilarindexCombobox('tipo_de_lesion'+ clave_analisis +'_'+tipo_lesiones_por_arteria[i]+ '_combo')+1)+"-";
			}else{
				cadena1 += (recopilarindexCombobox('Caracteristicas_lesion'+ clave_analisis +'_'+tipo_lesiones_por_arteria[i]+ '_combo')+2)+"-";
				cadena2 += (recopilarindexCombobox('tipo_de_lesion'+ clave_analisis +'_'+tipo_lesiones_por_arteria[i]+ '_combo')+2)+"-";
			}
		
            
			
			i++;
		}
	
		cadena1=cadena1.substring(0, cadena1.length-1);
		cadena2=cadena2.substring(0, cadena2.length-1);
		
		caracteristicas_lesion_arterias[indiceVectores] = cadena1;
		tipo_lesion_arterias[indiceVectores] = cadena2;
		
		//alert("caracteristica lesion: "+cadena1);
		//alert("tipo lesion: "+cadena2);
		
		flujo_timi_arterias[indiceVectores] = recopilarindexCombobox('flujo_timi'
				+ clave_analisis + '_combo')+2;
		lecho_distal_arterias[indiceVectores] = recopilarindexCombobox('lecho_distal'+ clave_analisis + '_combo')+1;
		
		
		
		
		
		break;

	case 2:
		activarelementoFormulario('lesion_oclusion_total' + clave_analisis
				+'_'+tipo_lesiones_por_arteria[arteria]+ '_combo');
		activarelementoFormulario('tipo_lesion_circulacion_colateral'
				+ clave_analisis+'_'+tipo_lesiones_por_arteria[arteria] + '_combo');
	
    
		
		cadena1="";
		cadena2="";
		
    	var i = 0;
		while (i < tipo_lesiones_por_arteria.length){
			if(!estadoElemento('lesion_oclusion_total'+ clave_analisis +'_'+tipo_lesiones_por_arteria[i]+ '_combo')){
				cadena1 += (recopilarindexCombobox('lesion_oclusion_total'+ clave_analisis +'_'+tipo_lesiones_por_arteria[i]+ '_combo')+2)+"-" ;
			}
			else{
				cadena1 += (recopilarindexCombobox('lesion_oclusion_total'+ clave_analisis +'_'+tipo_lesiones_por_arteria[i]+ '_combo')+1)+"-" ;
			}
			
			
			cadena2 += (recopilarindexCombobox('tipo_lesion_circulacion_colateral'
						+ clave_analisis +'_'+tipo_lesiones_por_arteria[i]+ '_combo')+1)+"-";
			
			i++;
		}
		cadena1=cadena1.substring(0, cadena1.length-1);
		cadena2=cadena2.substring(0, cadena2.length-1);

		tipo_lesion_por_oclusion_arterias[indiceVectores] = cadena1;
		lesion_circulacion_colateral_arterias[indiceVectores] = cadena2;
		break;

	default:
		activarelementoFormulario('con_la_rama' + clave_analisis+'_'+tipo_lesiones_por_arteria[arteria]  + '_combo');
		activarelementoFormulario('clasificacion_medina_seleccion'
				+ clave_analisis+'_'+tipo_lesiones_por_arteria[arteria]  + '_combo');
		aparecerElemento('imagen_clasificacion_medina' + clave_analisis+'_'+tipo_lesiones_por_arteria[arteria] );
		i =0;
    	cadena1="";
		cadena2="";

		var i = 0;
		while (i < tipo_lesiones_por_arteria.length){
			
			if(recopilarindexCombobox("bifurcada"+ clave_analisis +'_'+tipo_lesiones_por_arteria[i]+ '_combo')>0){
				
				cadena1 +=(recopilarValueCombobox('con_la_rama'+ clave_analisis +'_'+tipo_lesiones_por_arteria[i]+ '_combo'))+"-";
				cadena2 +=(recopilarindexCombobox('clasificacion_medina_seleccion'+ clave_analisis +'_'+tipo_lesiones_por_arteria[i]+ '_combo')+2)+"-";
			}
			else{
				cadena1 +="1"+"-";
				cadena2 +=(recopilarindexCombobox('clasificacion_medina_seleccion'+ clave_analisis +'_'+tipo_lesiones_por_arteria[i]+ '_combo')+1)+"-";
				
			}	
	     i++;
		}
		cadena1=cadena1.substring(0, cadena1.length-1);
		cadena2=cadena2.substring(0, cadena2.length-1);
		haybifurcacion_arterias[indiceVectores] = cadena1 ;
		clasificacionMedina_arterias[indiceVectores] = cadena2 ;
		break;
	}
}

/** ***************************************** */
function desactivarElementosAnatomiaCoronaria(opcion,arteria) {

	switch (opcion) {
	case 1:
		
		desaparecerElemento('caracteristicas'+clave_analisis+'_'+tipo_lesiones_por_arteria[arteria]);
	//	desactivarelementoFormulario('flujo_timi' + clave_analisis + '_combo');
	//	desactivarelementoFormulario('lecho_distal'+clave_analisis+'_combo');
		
		
		if (esArteriabifurcada(clave_analisis)) { // controles
			// arteria
			// circunsfleja y
			// arteria
			// descendente
			// anterior
		    //desactivarelementoFormulario('bifurcada' + clave_analisis+ '_combo');
				
		/*	reemplazarindexCombobox('con_la_rama' + clave_analisis	+'_'+tipo_lesiones_por_arteria[arteria]+ '_combo', 0);
			reemplazarindexCombobox('clasificacion_medina_seleccion'+ clave_analisis +'_'+tipo_lesiones_por_arteria[arteria]+ '_combo',0);
			cambiarImagenMedina(clave_analisis +'_'+tipo_lesiones_por_arteria[arteria],0);
			reemplazarindexCombobox('imagen_clasificacion_medina'+clave_analisis+'_'+tipo_lesiones_por_arteria[arteria],0);*/
			
			    desactivarelementoFormulario('con_la_rama' + clave_analisis	+'_'+tipo_lesiones_por_arteria[arteria]+ '_combo');
				desactivarelementoFormulario('clasificacion_medina_seleccion'+ clave_analisis +'_'+tipo_lesiones_por_arteria[arteria]+ '_combo');
				desaparecerElemento('imagen_clasificacion_medina'+clave_analisis+'_'+tipo_lesiones_por_arteria[arteria]);
		}
		break;

	case 2:

		reemplazarindexCombobox('lesion_oclusion_total' + clave_analisis
				+'_'+tipo_lesiones_por_arteria[arteria]+ '_combo', 0);
		reemplazarindexCombobox('tipo_lesion_circulacion_colateral'
				+ clave_analisis +'_'+tipo_lesiones_por_arteria[arteria]+ '_combo',0);

		
		desactivarelementoFormulario('lesion_oclusion_total' + clave_analisis
				+'_'+tipo_lesiones_por_arteria[arteria]+ '_combo');
		desactivarelementoFormulario('tipo_lesion_circulacion_colateral'
				+ clave_analisis +'_'+tipo_lesiones_por_arteria[arteria]+ '_combo');
		

		break;

	default:
	
		
		reemplazarindexCombobox('con_la_rama' + clave_analisis +'_'+tipo_lesiones_por_arteria[arteria]+ '_combo', 0);
		reemplazarindexCombobox('clasificacion_medina_seleccion'+ clave_analisis +'_'+tipo_lesiones_por_arteria[arteria]+ '_combo',0);
	    desactivarelementoFormulario('con_la_rama' + clave_analisis +'_'+tipo_lesiones_por_arteria[arteria]+ '_combo');
		desactivarelementoFormulario('clasificacion_medina_seleccion'
		+ clave_analisis +'_'+tipo_lesiones_por_arteria[arteria]+ '_combo');
		desaparecerElemento('imagen_clasificacion_medina' + clave_analisis+'_'+tipo_lesiones_por_arteria[arteria]);
		

		break;
	}
}

/** ***************************************** */
function reiniciarElementosAnatomiaCoronaria(opcion) {
	switch (opcion) {
	case 1: // no hay lesion
		i =0;
    	cadena1="";
		cadena2="";
		cadena3="";
		cadena4="";
		cadena5="";
		cadena6="";
		cadena7="";
		var arrayporcentajelesiones = new Array();
		while (i < tipo_lesiones_por_arteria.length){
			arrayporcentajelesiones[i]= recopilarindexCombobox('porcentaje_lesion_seleccion'+ clave_analisis +'_'+tipo_lesiones_por_arteria[i]+ '_combo');
			i++;
		}
		var swhayunalesion = true;
		
		i= 0;
		while (i < arrayporcentajelesiones.length){
		
			if(arrayporcentajelesiones[i]>0){
				swhayunalesion = false;
				i = arrayporcentajelesiones.length -1;
			}
			    
			i++;
		}
		if(swhayunalesion==true){
			desactivarelementoFormulario('observacion'+clave_analisis);
			desactivarelementoFormulario('flujo_timi' + clave_analisis + '_combo');
			desactivarelementoFormulario('lecho_distal'+clave_analisis+'_combo');
			
		}

		
		sw = false; 
		i=0;
		while (i < tipo_lesiones_por_arteria.length){
			sw = false; 
			
			
			if(recopilarindexCombobox('porcentaje_lesion_seleccion'+ clave_analisis +'_'+tipo_lesiones_por_arteria[i]+ '_combo') > 0){
				
				cadena1 += (recopilarindexCombobox('porcentaje_lesion_seleccion'+ clave_analisis +'_'+tipo_lesiones_por_arteria[i]+ '_combo'))+"-";
				cadena4 += (recopilarindexCombobox('Caracteristicas_lesion'
						+ clave_analisis +'_'+tipo_lesiones_por_arteria[i]+ '_combo')+2)+"-";
			
				cadena5 += (recopilarindexCombobox('tipo_de_lesion'
						+ clave_analisis +'_'+tipo_lesiones_por_arteria[i]+ '_combo')+2)+"-";
			
			
			}
			else{
			
				cadena1 += (recopilarindexCombobox('porcentaje_lesion_seleccion'+ clave_analisis +'_'+tipo_lesiones_por_arteria[i]+ '_combo'))+"-";
				cadena4 += (recopilarindexCombobox('Caracteristicas_lesion'
						+ clave_analisis +'_'+tipo_lesiones_por_arteria[i]+ '_combo')+1)+"-";
			
				cadena5 += (recopilarindexCombobox('tipo_de_lesion'
						+ clave_analisis +'_'+tipo_lesiones_por_arteria[i]+ '_combo')+1)+"-";
			
			}
			
			if(!estadoElemento('lesion_oclusion_total'+ clave_analisis +'_'+tipo_lesiones_por_arteria[i]+ '_combo')){
				cadena2 += (recopilarindexCombobox('lesion_oclusion_total'+ clave_analisis +'_'+tipo_lesiones_por_arteria[i]+ '_combo')+2)+"-" ;
			}
			else{
				cadena2 += (recopilarindexCombobox('lesion_oclusion_total'+ clave_analisis +'_'+tipo_lesiones_por_arteria[i]+ '_combo')+1)+"-" ;
			}
			
		
				cadena3 += (recopilarindexCombobox('tipo_lesion_circulacion_colateral'
						+ clave_analisis +'_'+tipo_lesiones_por_arteria[i]+ '_combo')+1)+"-";
			
			

		
			
		
			
			// ada //ac 
			if(esArteriabifurcada(clave_analisis)){ // para saber si es una arteria bifurcada
				
				sw = true;
				
				if(recopilarindexCombobox("bifurcada"+ clave_analisis +'_'+tipo_lesiones_por_arteria[i]+ '_combo')>0){
					
					cadena6 +=(recopilarValueCombobox('con_la_rama'+ clave_analisis +'_'+tipo_lesiones_por_arteria[i]+ '_combo'))+"-";
					cadena7 +=(recopilarindexCombobox('clasificacion_medina_seleccion'+ clave_analisis +'_'+tipo_lesiones_por_arteria[i]+ '_combo')+2)+"-";
				}
				else{
					cadena6 += "1"+"-";
					cadena7 +=(recopilarindexCombobox('clasificacion_medina_seleccion'+ clave_analisis +'_'+tipo_lesiones_por_arteria[i]+ '_combo')+1)+"-";
					
				}	
    		}
		
			i++;
		}
		cadena1 = cadena1.substring(0, cadena1.length-1);
		cadena2 = cadena2.substring(0, cadena2.length-1);
		cadena3 = cadena3.substring(0, cadena3.length-1);
		cadena4 = cadena4.substring(0, cadena4.length-1);
		cadena5 = cadena5.substring(0, cadena5.length-1);
		if (sw==true){
			cadena6 = cadena6.substring(0, cadena6.length-1);
			cadena7 = cadena7.substring(0, cadena7.length-1);
			haybifurcacion_arterias[indiceVectores] = cadena6;
			clasificacionMedina_arterias[indiceVectores] = cadena7;
		}
	/*	else{
			
			reiniciarElementosAnatomiaCoronaria(3); 
		}*/
		
		//alert("cadena; "+cadena1);
		porcentaje_localizacion_lesion_arterias[indiceVectores] = cadena1;
		tipo_lesion_por_oclusion_arterias[indiceVectores] = cadena2;
		lesion_circulacion_colateral_arterias[indiceVectores] = cadena3;
		caracteristicas_lesion_arterias[indiceVectores] = cadena4;
		tipo_lesion_arterias[indiceVectores] = cadena5;
		
		if(!estadoElemento('flujo_timi'+clave_analisis+"_combo")){
			flujo_timi_arterias[indiceVectores] = recopilarindexCombobox('flujo_timi'+ clave_analisis+"_combo")+2;
			lecho_distal_arterias[indiceVectores]= recopilarindexCombobox('lecho_distal'+ clave_analisis+"_combo")+1;
		}
		else{
			flujo_timi_arterias[indiceVectores] = '1';
			lecho_distal_arterias[indiceVectores]='1';
		}
	
		
		break;
	case 2:
		
		
		i =0;
    	cadena1="";
		cadena2="";

		while (i < tipo_lesiones_por_arteria.length){
        	if(!estadoElemento('lesion_oclusion_total'+ clave_analisis +'_'+tipo_lesiones_por_arteria[i]+ '_combo')){
				cadena1 += (recopilarindexCombobox('lesion_oclusion_total'+ clave_analisis +'_'+tipo_lesiones_por_arteria[i]+ '_combo')+2)+"-" ;
			}
			else{
				cadena1 += (recopilarindexCombobox('lesion_oclusion_total'+ clave_analisis +'_'+tipo_lesiones_por_arteria[i]+ '_combo')+1)+"-" ;
			}
			

				cadena2 += (recopilarindexCombobox('tipo_lesion_circulacion_colateral'
						+ clave_analisis +'_'+tipo_lesiones_por_arteria[i]+ '_combo')+1)+"-";
			
			i++;
		}
		cadena1 = cadena1.substring(0, cadena1.length-1);
		cadena2 = cadena2.substring(0, cadena2.length-1);
		
		
		tipo_lesion_por_oclusion_arterias[indiceVectores] = cadena1;
		lesion_circulacion_colateral_arterias[indiceVectores] =cadena2;
		break;

	case 3:
		// no se bifurca las lesiones en las arterias circunfleja y
		// descendente anterior
		if(esArteriabifurcada(clave_analisis)){
		i =0;
    	cadena1="";
		cadena2="";
        
		while (i < tipo_lesiones_por_arteria.length){
			
			
			if(recopilarindexCombobox("bifurcada"+ clave_analisis +'_'+tipo_lesiones_por_arteria[i]+ '_combo')>0){
				
				cadena1 +=(recopilarValueCombobox('con_la_rama'+ clave_analisis +'_'+tipo_lesiones_por_arteria[i]+ '_combo'))+"-";
				cadena2 +=(recopilarindexCombobox('clasificacion_medina_seleccion'+ clave_analisis +'_'+tipo_lesiones_por_arteria[i]+ '_combo')+2)+"-";
			}
			else{
				cadena1 +="1"+"-";
				cadena2 +=(recopilarindexCombobox('clasificacion_medina_seleccion'+ clave_analisis +'_'+tipo_lesiones_por_arteria[i]+ '_combo')+1)+"-";
				
			}	
		
			i++;
		}
		cadena1 = cadena1.substring(0, cadena1.length-1);
		cadena2 = cadena2.substring(0, cadena2.length-1);
		
		haybifurcacion_arterias[indiceVectores] = cadena1;
		clasificacionMedina_arterias[indiceVectores] =cadena2;
		}
		break;
	}
}

/** ***************************************** */
function reiniciar_control_seleccion_lesion() {
	var pos_control =0;
	for(var i =0; i < numeroItemsCombobox("Seleccion_Arteria_combo"); i++){
		var limitelesionesdearteriax =numero_lesiones_arterias_general[i];
		for (var j= 0 ; j < parseInt(limitelesionesdearteriax) ; j++){
			array_control_seleccion_lesion[pos_control]= 0 ;
			pos_control++;
		}
	}
}

function control_areas_Seleccionadas() {
	// funcion para controlar las selecciones realizadas en los porcentajes
	
	var pos_control =0;
	for(var i =0; i < numeroItemsCombobox("Seleccion_Arteria_combo"); i++){
		
		//var lesionesporarteria = tipo_lesiones_arterias_general[i];
		if (indiceVectores == i){
		for (var j=0 ; j < numero_lesiones_arterias_general[j]; j++){
			//	alert("entra");
			    array_control_seleccion_lesion[pos_control] =recopilarindexCombobox('porcentaje_lesion_seleccion'+clave_analisis+"_"+tipo_lesiones_por_arteria[j]+"_combo");
				pos_control ++;
     		}

		}else{
			pos_control = pos_control +numero_lesiones_arterias_general[i];
		}
		
	}

	//alert(array_control_seleccion_lesion.toString());
	
		
}

function cargar_areas_de_seleccion_arterias() {
	array_areas_de_seleccion.push("279,213,294,229");// T.C.I PROXIMAL
	array_areas_de_seleccion.push("294,214,308,230");// T.C.I MEDIO
	array_areas_de_seleccion.push("308,214,322,230");// T.C.I DISTAL
	
	array_areas_de_seleccion.push("330,207,401,234");// A.D.A PROXIMAL
	array_areas_de_seleccion.push("423,166,477,210");// A.D.A MEDIO
	array_areas_de_seleccion.push("501,110,558,165");// A.D.A DISTAL
	
	array_areas_de_seleccion.push("295,239,316,293");// A.C PROXIMAL
	array_areas_de_seleccion.push("131,321,283,361");// A.C MEDIO
	array_areas_de_seleccion.push("110,243,142,259");// A.C DISTAL
	
    array_areas_de_seleccion.push("339,242,382,280");// RAMUS PROXIMAL
    array_areas_de_seleccion.push("395,274,449,311");// RAMUS MEDIO
    array_areas_de_seleccion.push("456,307,504,349");// RAMUS DISTAL
	
	array_areas_de_seleccion.push("109,71,186,111");// A.D.C PROXIMAL
	array_areas_de_seleccion.push("45,95,87,144");// A.D.C MEDIO
	array_areas_de_seleccion.push("11,160,42,264");// A.D.C DISTAL
	
	array_areas_de_seleccion.push("63,278,83,318");// A.D.P PROXIMAL
	array_areas_de_seleccion.push("88,319,114,362");// A.D.P MEDIO
	array_areas_de_seleccion.push("122,367,182,393");// A.D.P DISTAL
	
	array_areas_de_seleccion.push("63,238,91,254");// A.T.P PROXIMAL
	array_areas_de_seleccion.push("92,222,122,240");// A.T.P MEDIO
	array_areas_de_seleccion.push("125,207,149,228");// A.T.P DISTAL
	
	array_areas_de_seleccion.push("421,222,440,240");// 1RA DIAG PROXIMAL
	array_areas_de_seleccion.push("440,229,454,253");// 1RA DIAG MEDIO 
	array_areas_de_seleccion.push("454,241,471,267");// 1RA DIAG DISTAL 

	array_areas_de_seleccion.push("485,182,503,203");// 2DA DIAG PROXIMAL 
	array_areas_de_seleccion.push("502,189,518,216");// 2DA DIAG MEDIO
	array_areas_de_seleccion.push("518,203,535,230");// 2DA DIAG DISTAL
	
	array_areas_de_seleccion.push("308,293,345,314");// 1RA OBX PROXIMAL
	array_areas_de_seleccion.push("345,304,378,331");// 1RA OBX MEDIO
	array_areas_de_seleccion.push("378,320,416,358");// 1RA OBX DISTAL

    array_areas_de_seleccion.push("290,335,330,359");// 2DA OBX PROXIMAL
    array_areas_de_seleccion.push("330,349,364,378");// 2DA OBX MEDIO
    array_areas_de_seleccion.push("364,366,398,400");// 2DA OBX DISTAL



}



function cargar_coordenadas_de_lesiones() {
	var i = 0;
	while (i < numeroItemsCombobox("Seleccion_Arteria_combo")) {
		var arregloproximales = new Array();
		var arreglomedios = new Array();
		var arreglodistales = new Array();
		switch (i) {// TCI
		case 0:
			// proximal con todos los porcentajes
			arregloproximales.push("281,213,288,213,295,213,291,216,285,216");
			arregloproximales.push("282,213,288,213,295,213,291,218,286,218");
			arregloproximales.push("282,213,288,213,295,213,291,220,286,220");
			arregloproximales.push("282,213,288,213,295,213,292,221,284,221");
			arregloproximales.push("282,213,288,213,295,213,292,223,284,223");
			arregloproximales.push("282,213,288,213,295,213,291,228,283,226");
			// medio con todos los porcentajes
			arreglomedios.push("293,214,296,217,303,217,307,214");
			arreglomedios.push("293,214,296,219,303,219,307,214");
			arreglomedios.push("293,214,296,222,303,222,307,214");
			arreglomedios.push("293,214,296,224,303,224,307,214");
			arreglomedios.push("293,214,296,227,302,229,307,214");
			arreglomedios.push("293,214,296,230,302,232,307,214");
			// distal con todos los porcentajes
			arreglodistales.push("305,214,321,214,317,217,309,217");
			arreglodistales.push("306,214,322,214,318,219,310,219");
			arreglodistales.push("306,214,322,214,317,220,310,220");
			arreglodistales.push("306,214,321,214,317,223,310,223");
			arreglodistales.push("306,214,321,214,317,225,310,225");
			arreglodistales.push("306,214,321,214,317,228,311,229");
			break;
		case 1:
			// ADA
			// proximal con todos los porcentajes
			arregloproximales
					.push("321,228,342,230,369,229,407,220,393,220,377,225,351,227,333,227");
			arregloproximales
					.push("321,228,342,230,369,229,407,220,393,220,377,221,352,223,333,227");
			arregloproximales
					.push("321,228,342,230,369,229,407,220,393,219,380,219,344,221,332,226");
			arregloproximales
					.push("321,228,342,230,369,229,407,220,393,219,383,217,344,220,332,226");
			arregloproximales
					.push("321,228,342,230,369,229,407,220,394,218,380,214,342,218,330,224");
			arregloproximales
					.push("321,228,342,230,369,229,407,220,394,218,377,211,340,216,330,224");
			// medio con todos los porcentajes
			arreglomedios
					.push("407,219,430,210,466,190,486,176,499,164,484,175,464,187,437,202,418,213");
			arreglomedios
					.push("407,219,430,210,466,190,486,176,499,164,485,171,462,183,436,198,420,208");
			arreglomedios
					.push("407,219,430,210,466,190,486,176,499,164,483,168,462,181,437,195,420,205");
			arreglomedios
					.push("407,219,430,210,466,190,486,176,499,164,475,170,460,179,437,194,423,203");
			arreglomedios
					.push("407,219,430,210,466,190,486,176,499,164,467,168,457,176,435,190,420,199");
			arreglomedios
					.push("407,219,430,210,466,190,486,176,499,166,464,166,453,175,434,188,422,195");
			// distal con todos los porcentajes
			arreglodistales
					.push("500,163,524,141,546,119,563,98,553,105,543,116,526,133,508,151");
			arreglodistales
					.push("500,163,524,141,546,119,563,98,552,104,542,114,525,131,509,147");
			arreglodistales
					.push("500,163,524,141,546,119,563,98,552,104,544,111,525,129,508,145");
			arreglodistales
					.push("500,163,524,141,546,119,563,98,552,104,543,109,525,127,510,140");
			arreglodistales
					.push("500,163,524,141,546,119,563,98,552,104,544,106,525,123,507,136");
			arreglodistales
					.push("500,163,524,141,546,119,563,98,547,102,542,105,524,121,506,134");
			break;
			
			
			
			
			
		case 2:
			// Circunsfleja
			// proximal con todos los porcentajes
			
			arregloproximales
					.push("313,238,315,246,315,270,311,290,310,282,312,259,313,239");
			arregloproximales
					.push("312,239,315,247,314,270,310,290,307,280,307,260,309,248");
			arregloproximales
					.push("312,239,315,247,314,270,310,290,306,280,306,259,308,247");
			arregloproximales
					.push("312,239,315,247,314,270,310,290,304,279,305,258,307,244");
			arregloproximales
					.push("312,239,315,247,314,270,310,290,301,279,304,257,305,245");
			arregloproximales
					.push("312,239,315,247,314,270,310,290,299,278,302,257,303,246");
			
			// medio con todos los porcentajes
			arreglomedios
					.push("126,333,142,345,178,357,213,357,245,350,217,354,193,355,171,352,149,345");
			arreglomedios
					.push("126,332,142,344,171,356,213,356,245,349,217,352,193,352,171,351,149,344");
			arreglomedios
					.push("126,332,142,344,171,356,213,356,245,349,218,351,193,352,171,351,137,338");
			arreglomedios
					.push("125,332,141,344,170,356,212,356,244,350,218,350,193,351,170,351,136,336");
			arreglomedios
					.push("125,332,141,344,170,356,212,356,244,350,218,350,195,350,170,350,137,335");
			arreglomedios
					.push("125,332,141,344,172,357,213,357,244,350,219,347,195,349,168,346,140,335");
			// distal con todos los porcentajes
			
			arreglodistales
			.push("100,262,122,250,147,239,142,239,127,245,114,251,106,256");
	arreglodistales
			.push("100,262,122,250,147,239,142,238,127,244,114,250,106,255");
	arreglodistales
			.push("100,262,122,250,147,239,142,237,127,243,114,249,106,254");
	arreglodistales
			.push("100,262,122,250,147,239,142,236,127,242,114,248,106,253");
	arreglodistales
			.push("100,262,122,250,147,239,142,236,127,241,114,248,106,252");
	arreglodistales
			.push("100,262,122,250,147,239,140,234,127,240,114,246,107,249");

			break;
			
		   case 3:
	    	 //RAMUS
   		   //proximal con todos los porcentajes
	     arregloproximales
					.push("315,240,347,255,365,265,390,278,382,270,371,265,349,252,330,243" );
	    	  arregloproximales
					.push("315,240,347,255,365,265,390,278,382,268,367,260,351,252,331,241" );
	    	  arregloproximales
					.push("315,240,347,255,365,265,390,278,383,266,365,257,349,248,332,239");
	    	 arregloproximales
					.push("315,240,347,255,365,265,390,278,381,263,363,253,349,246,334,238" );
	    	 arregloproximales
					.push("315,240,347,255,365,265,390,278,378,260,365,253,348,244,332,237" );
	    	arregloproximales
					.push("315,240,347,255,365,265,390,278,377,257,368,252,348,242,335,236" );
	    	
	    	   //medio con todos los porcentajes
	    	  
	    	  arreglomedios
					.push("391,277,413,290,434,303,457,317,442,305,427,295,408,284,398,279" );
	    arreglomedios
					.push("392,278,414,291,435,304,458,318,445,306,428,294,408,283,401,279" );
	    	arreglomedios
					.push("392,278,414,291,435,304,458,318,447,306,430,293,412,283,405,279" );
	    	  arreglomedios
					.push("392,278,414,291,435,304,458,318,447,303,429,292,415,283,404,278" );
	    	  arreglomedios
					.push("392,278,414,291,435,304,458,318,447,303,430,291,417,283,407,278");
	    	  arreglomedios
					.push("392,278,414,291,435,304,458,318,448,302,434,292,419,282,410,278" );
	    
	          //Distal con todos los porcentajes
	    
	    	  arreglodistales
					.push("457,318,469,324,482,333,503,348,498,342,480,330,472,324,462,318" );
	    	  arreglodistales
					.push("457,318,469,324,483,334,503,348,499,342,484,332,479,328,462,318" );
	    	  arreglodistales
					.push("457,318,469,324,483,334,503,348,499,342,486,332,483,330,467,319" );
	    	  arreglodistales
					.push("457,318,469,324,483,334,503,348,499,342,487,332,476,324,468,320");
	    	  arreglodistales
					.push("457,318,469,324,483,334,503,348,501,342,487,332,476,324,468,318" );
	    	  arreglodistales
					.push("457,318,469,324,483,334,504,349,501,340,491,332,479,324,472,318");
	    	    
	    	break;
   	
			
		case 4:
			// ACD
			// proximal con todos los porcentajes
			arregloproximales
					.push("96,103,118,94,135,94,157,98,177,106,167,100,146,92,124,91,110,95");
			arregloproximales
					.push("95,103,117,94,134,94,156,98,176,106,166,100,147,90,122,90,108,94");
			arregloproximales
					.push("95,103,118,94,134,94,156,97,176,106,160,95,149,89,121,89,110,92");
			arregloproximales
					.push("94,103,117,94,133,94,155,97,175,106,159,92,149,87,121,87,111,89");
			arregloproximales
					.push("94,103,117,94,133,94,155,97,175,106,159,92,147,85,122,85,111,85");
			arregloproximales
					.push("94,103,117,94,133,94,155,97,175,106,158,89,144,82,122,82,112,83");
			// medio con todos los porcentajes
			arreglomedios
					.push("46,145,63,130,81,113,95,104,91,103,78,112,65,125,55,134");
			arreglomedios
					.push("46,145,63,130,83,111,95,104,88,104,74,114,64,123,55,132");
			arreglomedios
					.push("46,145,63,130,83,111,95,104,85,104,71,114,63,122,54,131");
			arreglomedios
					.push("46,145,63,130,83,111,95,104,82,103,71,112,63,119,53,129");
			arreglomedios
					.push("46,145,63,130,83,111,95,104,79,101,69,109,62,116,53,124");
			arreglomedios
					.push("46,145,63,130,83,111,95,104,76,99,68,106,61,113,53,121");
			// distal con todos los porcentajes
			arreglodistales
					.push("39,157,27,186,27,220,36,246,56,255,32,244,24,219,25,191,32,166");
			arreglodistales
					.push("39,157,27,186,27,220,36,245,56,255,32,245,23,219,23,190,31,165");
			arreglodistales
					.push("39,157,27,186,27,220,36,245,56,255,31,247,22,219,22,189,30,163");
			arreglodistales
					.push("39,157,27,186,27,220,36,245,56,255,31,247,21,220,21,189,28,162");
			arreglodistales
					.push("39,157,27,186,27,220,36,245,56,255,27,247,19,220,19,188,28,162");
			arreglodistales
					.push("39,157,27,186,27,230,36,246,56,255,23,245,17,219,17,189,27,162");
			break;
		case 5:
			// ADP
			// proximal con todos los porcentajes
			arregloproximales
					.push("54,265,75,295,93,325,89,315,82,302,74,289,64,275");
			arregloproximales
					.push("54,265,75,295,93,325,90,315,84,302,75,289,65,274");
			arregloproximales
					.push("54,265,75,295,93,325,92,314,85,302,77,290,66,273");
			arregloproximales
					.push("54,265,75,295,93,325,92,314,87,303,79,291,67,272");
			arregloproximales
					.push("54,265,75,295,93,325,92,310,87,301,80,290,68,271");
			arregloproximales
					.push("54,265,75,295,93,325,93,308,89,300,81,287,71,272");
			// medio con todos los porcentajes
			arreglomedios
					.push("92,324,108,350,130,376,127,370,113,353,109,347,101,335");
			arreglomedios
					.push("92,324,109,350,130,376,127,367,114,351,111,346,102,333");
			arreglomedios
					.push("92,324,109,350,130,376,128,366,116,350,112,344,101,330");
			arreglomedios
					.push("92,324,108,349,130,376,128,363,118,350,111,341,104,330");
			arreglomedios
					.push("92,324,108,349,130,376,130,364,119,350,113,341,107,331");
			arreglomedios
					.push("92,324,108,349,130,376,131,364,121,349,116,341,110,331");
			// distal con todos los porcentajes
			arreglodistales
					.push("130,377,146,387,161,389,181,389,176,388,166,387,149,384,138,379");
			arreglodistales
					.push("130,377,144,386,161,389,181,389,176,388,163,385,151,383,144,380");
			arreglodistales
					.push("130,377,144,386,160,388,181,389,176,388,160,383,151,381,145,380");
			arreglodistales
					.push("130,377,144,386,160,388,181,389,179,386,162,382,151,381,144,377");
			arreglodistales
					.push("130,377,144,386,160,388,181,388,175,382,163,382,151,381,144,376");
			arreglodistales
					.push("130,377,144,386,160,388,181,388,176,381,165,381,155,379,148,376");
			break;
		case 6:
			// ATP
			// proximal con todos los porcentajes

			arregloproximales
			.push("73,262,73,261,99,249,93,250,86,253,76,257,67,262");
			arregloproximales
			.push("69,262,72,261,98,248,91,247,84,251,76,255,72,257");
			arregloproximales
			.push("69,262,72,261,98,248,90,246,83,250,75,253,72,257");
			arregloproximales
			.push("69,262,72,261,98,248,90,244,83,248,74,251,72,257");
			arregloproximales
			.push("68,262,71,261,97,248,90,243,83,246,74,250,71,257");
			arregloproximales
			.push("68,262,71,261,97,248,89,242,83,245,74,248,71,257");
			
			
			// medio con todos los porcentajes
			arreglomedios.push("98,250,125,235,115,238,104,244");
			arreglomedios.push("98,250,125,235,116,236,103,243");
			arreglomedios.push("98,250,125,235,117,235,103,241");
			arreglomedios.push("98,250,125,234,115,234,104,239");
			arreglomedios.push("97,250,125,234,114,233,103,238");
			arreglomedios.push("96,250,124,235,112,231,101,236");
			// distal con todos los porcentajes
			
			arreglodistales
			.push("123,235,148,224,160,218,153,219,143,224,136,228,128,231");
			arreglodistales
			.push("123,235,149,224,160,219,152,220,144,222,136,226,128,230");
	arreglodistales
			.push("123,235,149,224,160,219,152,219,144,221,136,225,129,228");
	arreglodistales
			.push("123,235,154,221,160,219,153,217,144,221,136,225,127,228");
	arreglodistales
			.push("123,235,154,221,160,219,153,217,144,221,136,225,128,227");
	arreglodistales
			.push("123,235,154,221,160,219,152,216,143,219,134,224,128,226");
			
			
			break;
			
			
		    case 7:
                // D1
		    	// proximal con todos los porcentajes
		    	arregloproximales.push("421,225,439,239,439,237,423,224"); 
		    	arregloproximales.push("421,225,439,240,439,236,424,224"); 
		    	arregloproximales.push("421,225,439,240,439,235,425,224"); 
		    	arregloproximales.push("421,225,439,240,439,234,426,224"); 
		    	arregloproximales.push("421,225,439,240,439,233,427,223"); 
		    	arregloproximales.push("421,225,439,239,439,230,430,223"); 
				// medio con todos los porcentajes
				arreglomedios.push("440,240,454,251,453,248,441,237"); 
				arreglomedios.push("440,240,453,250,454,248,441,237"); 
				arreglomedios.push("439,238,453,251,453,247,440,235"); 
				arreglomedios.push("438,237,453,251,453,246,441,234"); 
				arreglomedios.push("438,237,453,251,453,244,442,233"); 
				arreglomedios.push("439,237,453,251,453,243,443,233"); 
				// distal con todos los porcentajes
				arreglodistales.push("453,251,463,261,463,258,455,250"); 
				arreglodistales.push("453,251,463,261,462,256,456,250"); 
				arreglodistales.push("453,251,463,261,461,254,456,249"); 
				arreglodistales.push("452,249,463,261,461,254,455,248"); 
				arreglodistales.push("451,249,463,261,461,253,455,247"); 
				arreglodistales.push("451,248,463,261,461,252,456,246"); 
			break;
			case 8:
		        //D2
         		// proximal con todos los porcentajes
	        	
			
				
				
				arregloproximales.push("501,200,501,197,487,187,485,189");
	        	arregloproximales.push("501,200,501,196,488,187,485,189");
		        arregloproximales.push("501,200,501,195,488,185,485,189");
	         	arregloproximales.push("501,200,501,193,490,185,485,189");
		        arregloproximales.push("501,200,501,191,491,184,485,189");
	         	arregloproximales.push("501,200,501,189,493,183,485,189");
              	// medio con todos los porcentajes
		        arreglomedios.push("502,199,515,211,515,207,503,197");
		        arreglomedios.push("502,199,515,211,515,207,504,197");
		        arreglomedios.push("501,198,515,211,515,206,504,195");
		        arreglomedios.push("501,198,515,211,515,205,505,194");
		        arreglomedios.push("501,198,515,211,515,203,505,193");
		        arreglomedios.push("501,198,515,211,515,201,505,191");
            	// distal con todos los porcentajes
		        arreglodistales.push("515,211,524,220,524,217,517,210");
		        arreglodistales.push("515,211,524,220,524,216,518,210");
		        arreglodistales.push("515,211,524,220,524,215,517,208");
		        arreglodistales.push("514,210,524,220,524,214,517,207");
		        arreglodistales.push("514,210,524,220,524,212,517,205");
		        arreglodistales.push("514,210,524,220,524,210,518,204");
			break;
        	case 9:
        		//O1
        		// proximal con todos los porcentajes
        		arregloproximales.push("308,300,343,312,341,309,312,299");
        		arregloproximales.push("308,300,343,312,340,308,312,298");
        		arregloproximales.push("308,300,343,312,339,306,313,297");
        		arregloproximales.push("308,300,343,312,339,305,313,296");
        		arregloproximales.push("308,300,343,312,338,304,313,295");
        		arregloproximales.push("308,300,343,312,338,302,313,294");
        		// medio con todos los porcentajes
        		arreglomedios.push("344,312,377,330,374,326,347,311");
        		arreglomedios.push("344,312,377,330,373,324,347,311");
        		arreglomedios.push("344,312,377,330,373,323,347,310");
        		arreglomedios.push("344,312,377,330,372,321,348,309");
        		arreglomedios.push("345,312,377,330,373,321,347,308");
        		arreglomedios.push("345,312,377,330,373,320,347,306");
        		// distal con todos los porcentajes
        		arreglodistales.push("377,330,396,343,394,340,380,329");
        		arreglodistales.push("377,330,396,343,395,340,380,328");
        		arreglodistales.push("377,329,396,343,395,339,380,327");
        		arreglodistales.push("377,329,396,343,395,338,380,327");
        		arreglodistales.push("376,329,396,343,394,337,380,326");
        		arreglodistales.push("376,329,396,343,396,337,381,325");
	     	break;
    	    case 10:
	    	   //O2
       		   //proximal con todos los porcentajes
	    	   arregloproximales.push("292,342,330,358,327,354,297,342");
	    	   arregloproximales.push("292,342,330,358,324,352,297,342");
	    	   arregloproximales.push("292,342,330,358,324,352,299,342");
	    	   arregloproximales.push("292,342,330,358,324,351,299,341");
	    	   arregloproximales.push("292,342,330,358,324,350,299,340");
	    	   arregloproximales.push("292,342,330,358,324,349,300,339");
	    	   //medio con todos los porcentajes
	    	   arreglomedios.push("331,357,363,376,362,373,333,356");
	    	   arreglomedios.push("331,357,363,376,362,372,333,356");
	    	   arreglomedios.push("331,357,363,376,362,372,334,356");
	    	   arreglomedios.push("331,357,363,376,361,371,335,356");
	    	   arreglomedios.push("331,357,363,376,360,369,337,356");
	    	   arreglomedios.push("331,357,363,376,359,367,339,355");
	         	// distal con todos los porcentajes
	    	   arreglodistales.push("363,375,379,387,377,384,366,375");
	    	   arreglodistales.push("363,375,379,387,378,383,366,375");
	    	   arreglodistales.push("363,375,379,387,378,383,367,375");
	    	   arreglodistales.push("363,375,379,387,376,381,367,375");
	    	   arreglodistales.push("363,375,379,387,376,381,369,375");
	    	   arreglodistales.push("363,375,379,387,378,380,370,375");
	    	break;
	    	
    
			
		}
		arrayposicioneslocalizacionProximales[i] = arregloproximales;
		arrayposicioneslocalizacionMedios[i] = arreglomedios;
		arrayposicioneslocalizacionDistales[i] = arreglodistales;
		i++;
	}
}

function cambiarImagenMedina(clave,indice) {
	var img = document.getElementById('imagen_clasificacion_medina_seleccionada'+ clave);
	switch (indice) {
	case 0:
		img.src = 'Imagenes/medina_001.png';
		break;
	case 1:
		img.src = 'Imagenes/medina_010.png';
		break;
	case 2:
		img.src = 'Imagenes/medina_011.png';
		break;
	case 3:
		img.src = 'Imagenes/medina_100.png';
		break;
	case 4:
		img.src = 'Imagenes/medina_101.png';
		break;
	case 5:
		img.src = 'Imagenes/medina_110.png';
		break;
	default:
		img.src = 'Imagenes/medina_111.png';
		break;
	}
}

/** ***************************************** */

function reiniciar_Elementos_Formulario_Anatomia_Coronaria() {

	//clave_analisis = "";
	//indiceVectores = -1;
	var j=0;
		var cadaux = "";
		var arrayaux = new Array();
		while(j < tipo_lesiones_arterias_general.length){
			clave_analisis = document.getElementById("Seleccion_Arteria_combo").options[j].value;
			cadaux = tipo_lesiones_arterias_general[j];
			arrayaux = cadaux.split("-");
			var k = 0;
			while(k < arrayaux.length){
				reemplazarindexCombobox('porcentaje_lesion_seleccion'+clave_analisis+"_"+arrayaux[k]+"_combo",0);
				reemplazarindexCombobox('lesion_oclusion_total' + clave_analisis+"_"+arrayaux[k]+'_combo', 0);
				reemplazarindexCombobox('tipo_lesion_circulacion_colateral'+ clave_analisis+"_" +arrayaux[k]+ '_combo', 0);
				reemplazarindexCombobox('Caracteristicas_lesion' +clave_analisis+"_" +arrayaux[k]+ '_combo', 0);		
				reemplazarindexCombobox('tipo_de_lesion' + clave_analisis +"_" +arrayaux[k]+ '_combo', 0);
				desaparecerElemento('caracteristicas'+clave_analisis+'_'+arrayaux[k]);
				desactivarelementoFormulario('lesion_oclusion_total' + clave_analisis+'_'+arrayaux[k]+ '_combo');
				desactivarelementoFormulario('tipo_lesion_circulacion_colateral'+ clave_analisis +'_'+arrayaux[k]+ '_combo');
				
				if(esArteriabifurcada(clave_analisis)){
					reemplazarindexCombobox('bifurcada' + clave_analisis +"_" +arrayaux[k] + '_combo', 0);
					reemplazarindexCombobox('con_la_rama' + clave_analisis +"_" +arrayaux[k] + '_combo',0);
					reemplazarindexCombobox('clasificacion_medina_seleccion'+ clave_analisis +"_" +arrayaux[k] + '_combo', 0);
					desactivarelementoFormulario('con_la_rama' + clave_analisis	+'_'+arrayaux[k]+ '_combo');
					desactivarelementoFormulario('clasificacion_medina_seleccion'+ clave_analisis +'_'+arrayaux[k]+ '_combo');
					desaparecerElemento('imagen_clasificacion_medina'+clave_analisis+'_'+arrayaux[k]);
				}
				
				k++;
			}
			reemplazarindexCombobox('flujo_timi' + clave_analisis + '_combo', 0);
			reemplazarindexCombobox('lecho_distal'+ clave_analisis + '_combo', 0);
			desactivarelementoFormulario('flujo_timi' + clave_analisis + '_combo');
			desactivarelementoFormulario('lecho_distal'+ clave_analisis + '_combo');
			desactivarelementoFormulario('observacion'+clave_analisis);
			if(clave_analisis == '_TCI'){
			   	reemplazarinfotextbox('observacion'+clave_analisis, 'De buen desarrollo y distribuci\u00f3n , sin lesiones significativas.');
			   }else{
			   	reemplazarinfotextbox('observacion'+clave_analisis, 'De buen desarrollo y distribuci\u00f3n, sin lesiones significativas con '+recopilarValueCombobox('lecho_distal'+ clave_analisis + '_combo') + '.');
			}
			j++;
		}
		
		reemplazarindexCombobox('Seleccion_Arteria_combo', 0);
		
		swEnviarInformeAnatomiaCoronia = 0;
		
       // seleccionAnalisisArteria();
}

/*function consultartiposdelesionesporArteria(codArteriaseleccionada){
	ajax = getXMLObject();
	ajax.open("POST", "cateterismodata", true); // getname will be the
	// servlet name
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			var datos = ajax.responseText.split(",");
            for(var i = 0 ; i < datos.length ;i++){
            	tipo_lesiones_por_arteria[i]=datos[i];
            }
            alert(tipo_lesiones_por_arteria);
		}
	};
	
	alert('codArteriaseleccionada: '+codArteriaseleccionada);
	ajax.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("opcion=3&arteriaSeleccionada="+codArteriaseleccionada); // Posting txtname
	// to Servlet
	
}*/

function consultarlesionesbifurcaciones(){

	ajax = getXMLObject();
	ajax.open("POST", "cateterismodata", true); // getname will be the
	// servlet name
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			var datos = ajax.responseText.split("@");
			var data1 = datos[0].split(",");

			for(var i = 0 ; i < data1.length ;i++){
				arterias_bifurcadas[i]=data1[i];
            }
			
			var data2 = datos[1].split("*");
			for(var i = 0 ; i < data2.length ;i++){
			//	alert("i: "+i +"  "+data2[i]);
	           	tipo_lesiones_arterias_general[i]=data2[i];
            }

			var cadena ="";
			//alert("////"+tipo_lesiones_arterias_general.length);
		    for (var i = 0 ; i < tipo_lesiones_arterias_general.length ; i++){
		    	cadena = tipo_lesiones_arterias_general[i];
		    	var aux = cadena.split("-");
		    	numero_lesiones_arterias_general[i]=aux.length;
		    }
    		carga_logica_informe_anatomia_coronaria();
			seleccionAnalisisArteria();
		}
	};
	
	ajax.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("opcion=1"); // Posting txtname
	// to Servlet
}




/** ****************************************** */
/** *Formularios Dinamicos Anatomia Coronaria* */
/** ****************************************** */
function seleccionAnalisisArteria() {
	clave_analisis = "";
	indiceVectores = -1;
	var indiceAdquirido = recopilarindexCombobox("Seleccion_Arteria_combo");
	var arteriaSeleccionada = document.getElementById("Seleccion_Arteria_combo").options[indiceAdquirido].value;
	var indiceRecorrido = 0;
	//consultartiposdelesionesporArteria(arteriaSeleccionada);
	
	ajax = getXMLObject();
	ajax.open("POST", "cateterismomodificaciondata", true); // getname will be the
	// servlet name
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			var datos = ajax.responseText.split(",");
			for(var i = 0 ; i < datos.length ;i++){
				tipo_lesiones_por_arteria[i]=datos[i];
            }
		//	alert("arterias bifuracadas " + arterias_bifurcadas.length);
			clave_analisis = arteriaSeleccionada;
			indiceVectores = indiceAdquirido;
			
			while (indiceRecorrido < numeroItemsCombobox("Seleccion_Arteria_combo")) {
				
				if (indiceRecorrido == indiceAdquirido) {// se selecciona un formulario de una arteria x
					aparecerElemento("Formulario" + arteriaSeleccionada);
					var contadortipolesionesarteria = 0;
		
						
		    			if (esArteriabifurcada(clave_analisis)) {
		    				while (contadortipolesionesarteria < tipo_lesiones_por_arteria.length){
		    				if (recopilarindexCombobox('bifurcada' + arteriaSeleccionada
									+"_"+tipo_lesiones_por_arteria[contadortipolesionesarteria]+ '_combo') > 0) {
								aparecerElemento('imagen_clasificacion_medina'+ arteriaSeleccionada+"_"+tipo_lesiones_por_arteria[contadortipolesionesarteria]);
							} else {
								desaparecerElemento('imagen_clasificacion_medina'+ arteriaSeleccionada+"_"+tipo_lesiones_por_arteria[contadortipolesionesarteria]);
							}
		    				contadortipolesionesarteria++;
							}
						
						}
						
				
				} else {
					var arteriaNoSeleccionada = document.getElementById("Seleccion_Arteria_combo").options[indiceRecorrido].value;
				 	desaparecerElemento("Formulario" + arteriaNoSeleccionada);
						if (esArteriabifurcada(arteriaNoSeleccionada)) {
							var contadortipolesionesarteria = 0;
							while (contadortipolesionesarteria < tipo_lesiones_por_arteria.length){
							
						//	alert("entra arteria "+arteriaSeleccionada);
						    desaparecerElemento('imagen_clasificacion_medina'+ arteriaNoSeleccionada+"_"+tipo_lesiones_por_arteria[contadortipolesionesarteria]);
							contadortipolesionesarteria++;
							}
							}
    				}
				indiceRecorrido++;
			}
			
			
			
			
			
		}
	};
	ajax.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("opcion=2&arteriaSeleccionada="+arteriaSeleccionada); // Posting txtname
}


function seleccionAnalisisArteriaconclick(arreglo_arterialesion, indice) {
	clave_analisis = "";
	indiceVectores = -1;
	var indiceAdquirido = recopilarindexCombobox("Seleccion_Arteria_combo");
	var arteriaSeleccionada = document.getElementById("Seleccion_Arteria_combo").options[indiceAdquirido].value;
	var indiceRecorrido = 0;
	//consultartiposdelesionesporArteria(arteriaSeleccionada);
	
	ajax = getXMLObject();
	ajax.open("POST", "cateterismomodificaciondata", true); // getname will be the
	// servlet name
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			var datos = ajax.responseText.split(",");
			for(var i = 0 ; i < datos.length ;i++){
				tipo_lesiones_por_arteria[i]=datos[i];
            }
		//	alert("arterias bifuracadas " + arterias_bifurcadas.length);
			clave_analisis = arteriaSeleccionada;
			indiceVectores = indiceAdquirido;
			
			while (indiceRecorrido < numeroItemsCombobox("Seleccion_Arteria_combo")) {
				
				if (indiceRecorrido == indiceAdquirido) {// se selecciona un formulario de una arteria x
					aparecerElemento("Formulario" + arteriaSeleccionada);
					var contadortipolesionesarteria = 0;
		
						
		    			if (esArteriabifurcada(clave_analisis)) {
		    				while (contadortipolesionesarteria < tipo_lesiones_por_arteria.length){
		    				if (recopilarindexCombobox('bifurcada' + arteriaSeleccionada
									+"_"+tipo_lesiones_por_arteria[contadortipolesionesarteria]+ '_combo') > 0) {
								aparecerElemento('imagen_clasificacion_medina'+ arteriaSeleccionada+"_"+tipo_lesiones_por_arteria[contadortipolesionesarteria]);
							} else {
								desaparecerElemento('imagen_clasificacion_medina'+ arteriaSeleccionada+"_"+tipo_lesiones_por_arteria[contadortipolesionesarteria]);
							}
		    				contadortipolesionesarteria++;
							}
						
						}
						
				
				} else {
					var arteriaNoSeleccionada = document.getElementById("Seleccion_Arteria_combo").options[indiceRecorrido].value;
				 	desaparecerElemento("Formulario" + arteriaNoSeleccionada);
						if (esArteriabifurcada(arteriaNoSeleccionada)) {
							var contadortipolesionesarteria = 0;
							while (contadortipolesionesarteria < tipo_lesiones_por_arteria.length){
							
						//	alert("entra arteria "+arteriaSeleccionada);
						    desaparecerElemento('imagen_clasificacion_medina'+ arteriaNoSeleccionada+"_"+tipo_lesiones_por_arteria[contadortipolesionesarteria]);
							contadortipolesionesarteria++;
							}
							}
    				}
				indiceRecorrido++;
			}
			
			//alert('porcentaje_lesion_seleccion'+ clave_analisis +"_"+arreglo_arterialesion[1]+ '_combo');
		//	alert(''+numeroItemsCombobox('porcentaje_lesion_seleccion'+ clave_analisis +"_"+arreglo_arterialesion[1]+ '_combo')-1);
			if (array_control_seleccion_lesion[indice] == numeroItemsCombobox('porcentaje_lesion_seleccion'+ clave_analisis +"_"+arreglo_arterialesion[1]+ '_combo')-1) {// lesion al 100%
			//	alert("entra");
				array_control_seleccion_lesion[indice] = 0;
			} else {
		//		alert("-----"+ array_control_seleccion_lesion[indice]);
				array_control_seleccion_lesion[indice] = array_control_seleccion_lesion[indice] + 1;// + 1;
			}
		//	alert(indice + " aaa " + array_control_seleccion_lesion[indice]);
			reemplazarindexCombobox('porcentaje_lesion_seleccion'+clave_analisis+"_"+arreglo_arterialesion[1]+ '_combo',array_control_seleccion_lesion[indice]);
			control_Informe_Anatomia_Coronaria(1);
		}
	};
	ajax.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("opcion=2&arteriaSeleccionada="+arteriaSeleccionada); // Posting txtname
}

/** ************************************************** */
/** ***********Evento click sobre imagen************** */
/** ******ventriculografia y Anatomia Coronaria******* */
/** ************************************************** */

function eventoClicksobreImagen(nombrecanvas, opcion,event) {
	var canvas = document.getElementById(nombrecanvas);
	if (canvas.getContext) {
		switch (opcion) {
		case 1:
			seleccionFEVI();
			break;
		case 2:
	      	
			var array = posicionClicksobreImagen(canvas,event);
			identificacion_areas_seleccionadas(array[0], array[1]);
			break;
		}
	} else {
		alert('You need Safari or Firefox 1.5+ to see this demo.');
	}
}

function seleccionFEVI() {
	
	if (fevi == numeroItemsCombobox('seleccion_FEVI') - 1) {// fevi al 70%
		fevi = 0;
	} else {
		fevi = fevi + 1;
	}
	reemplazarindexCombobox("seleccion_FEVI", fevi);
	control_Informe_Ventriculografia(6);
}

function posicionClicksobreImagen(canvas,e) {

		var curleft = 0, curtop = 0;
		var x;
	    var y;
		var xTotal =0;
	    var yTotal =0;
		var posiciones= new Array();
	    if (canvas.offsetParent) {
	        do {
	            curleft += canvas.offsetLeft;
	            curtop += canvas.offsetTop;
	        } while (canvas = canvas.offsetParent);
	        
	     	      
	        if (e.pageX || e.pageY)
	        {
	            x = e.pageX;
	            y = e.pageY;
	        }
	        else {
	            x = e.clientX + document.body.scrollLeft + document.documentElement.scrollLeft;
	            y = e.clientY + document.body.scrollTop + document.documentElement.scrollTop;
	        }
	        
	        xTotal= x - curleft;
	        yTotal= y - curtop;

	posiciones[0]=xTotal;
	posiciones[1]=yTotal;
		
	}else{
		
		posiciones[0]=0;
		posiciones[1]=0;
	}
	return posiciones;
}

function identificaciondearteriaporclic(idlesion){
	var contadorlesionesenarterias = 0;
	var arteriaseleccion = 0;
	var array = new Array();
	for (var i = 0; i < tipo_lesiones_arterias_general.length ; i++){
		var cadena= tipo_lesiones_arterias_general[i];
		var cadenalesionesarteria = cadena.split("-");
		arteriaseleccion = 0;
		while(arteriaseleccion < cadenalesionesarteria.length){
			if(contadorlesionesenarterias == idlesion){
				array[0]= i ;
				array[1]=cadenalesionesarteria[arteriaseleccion];
				return array;
			}
			contadorlesionesenarterias ++;
			arteriaseleccion ++;
		}
	}
	
	return array;
}


function identificacion_areas_seleccionadas(posx, posy) {
	// metodo que identifica el area que se ejecuto el click
	// y automatiza el proceso de toma de informacion
	var i = 0;
	var sw = false;
	while ((i < array_areas_de_seleccion.length) && (sw == false)) {
		var array = array_areas_de_seleccion[i].split(',');
		if ((posx >= array[0]) && (posx <= array[2])) {
			if ((posy >= array[1]) && (posy <= array[3])) {
				sw = true;
				i--;
			}
		}
		i++;
	}

	if (sw == true) {
		var arreglo_arterialesion = new Array();
		arreglo_arterialesion= identificaciondearteriaporclic(i);
		//alert("tipo arteria: "+arreglo_arterialesion[0]);
		//alert("lesion: "+arreglo_arterialesion[1]);
		reemplazarindexCombobox('Seleccion_Arteria_combo',	arreglo_arterialesion[0]);
		
		seleccionAnalisisArteriaconclick(arreglo_arterialesion, i);
		 

	}
}

/** ************************************************** */
/** ***************Funciones Varias******************* */
/** ************************************************** */

function enfocarMouseEnElemento(idText){
	document.getElementById(idText).focus();
}

function verEstadoCheckbox(grupocheck) {
	for ( var i = 0; ele = document.getElementsByName(grupocheck)[i]; i++) {
		if (ele.checked == 1) {
			return true;
		}
	}
	return false;
}

function numeroItemsCombobox(idCombo) {
	
	return document.getElementById(idCombo).options.length;
}

function reemplazarinfotextbox(idText, valor) {

	document.getElementById(idText).value = valor;
}

function recopilarinfotextbox(idText) {
	return document.getElementById(idText).value;
}

function tamanoinfotextbox(idText) {
	//alert(idText);
	return document.getElementById(idText).value.length;
}

function obtenerelementobifurcado(elementname){

	idtipolesion = document.getElementById(elementname).name;
	control_Informe_Anatomia_Coronaria(8);
} 

function recopilarTextCombobox(idCombo) {
//alert(idCombo);
	var index = document.getElementById(idCombo).selectedIndex;
	var seleccion = document.getElementById(idCombo).options[index].text;
	return seleccion;
}

function recopilarValueCombobox(idCombo) {
	var index = document.getElementById(idCombo).selectedIndex;
	var seleccion = document.getElementById(idCombo).options[index].value;
	return seleccion;
}

function recopilarindexCombobox(idCombo) {
	//alert(idCombo);
	
//	alert("rrrrrr: "+idCombo);
	
	var index = document.getElementById(idCombo).selectedIndex;
	return index;
}

function reemplazarindexCombobox(idCombo, valor) {
	//alert("+++++++ "+idCombo)
	document.getElementById(idCombo).selectedIndex = valor;
}

function activarelementoFormulario(idCombo) {
	//alert("activar: "+idCombo);
	document.getElementById(idCombo).disabled = false;
}

function desactivarelementoFormulario(idCombo) {

	document.getElementById(idCombo).disabled = true;
}

function aparecerElemento(id) {

document.getElementById(id).style.display = "inline";
}

function desaparecerElemento(id) {
 document.getElementById(id).style.display = "none";
}

function estadoElemento(idCombo) {
	// true desactivado
	// false activado
//	alert(idCombo);
	return document.getElementById(idCombo).disabled;
}

function cleanArray(actual) {
	// metodo que limpia espacios en blanco de un array
	var newArray = new Array();
	for ( var i = 0, j = actual.length; i < j; i++) {
		if (actual[i]) {
			newArray.push(actual[i]);
		}
	}
	return newArray;
}

/** ************************************************** */
/** **************Funciones Globales****************** */
/** ************************************************** */
function nuevoInforme() {
	if (confirm("Desea Anular el informe?")) {
		// Respuesta afirmativa...
	    var minavegador = new Browser();
	    if (minavegador.name == 'firefox'){
			limpiarFormularios();
	    }
		
		window.location.reload(true);
		
	}
}

/*function verificarInformeAnatomiaCoronaria() {
    var indiceRecorrido = 0;
		while (indiceRecorrido < numeroItemsCombobox("Seleccion_Arteria_combo")) {
		    var arteriaSeleccionada = document.getElementById("Seleccion_Arteria_combo").options[indiceRecorrido].value;
		
		    if (!estadoElemento('observacion'+arteriaSeleccionada)){
		    	return true;
		    }
		    
		    indiceRecorrido++;
		}
	
	
	return false;
}*/

function verificarInformeAortograma() {
	if (recopilarindexCombobox('seleccion_raiz_aortica') > 0) {
		if (isNaN(parseInt(recopilarinfotextbox('txt_medicion_raiz_aortica')))){
			alert("el campo de la medicion de la raiz aortica no debe quedar vacio si se escogio la opcion de raiz aortica dilatada");
			enfocarMouseEnElemento('txt_medicion_raiz_aortica');
			return false;
		}
		else if (parseInt(recopilarinfotextbox('txt_medicion_raiz_aortica')) == 0) {
			alert("el campo de la medicion de la raiz aortica no debe tener 0 mm");
			enfocarMouseEnElemento('txt_medicion_raiz_aortica');
			return false;
		}
	} 
	
	if (recopilarindexCombobox('txt_medicion_aorta_ascendente') > 0) {
		if (isNaN(parseInt(recopilarinfotextbox('txt_medicion_aorta_ascendente')))){
			alert("el campo de la medicion de la aorta ascendente no debe quedar vacia si se escogio la opcion de aorta ascendente dilatada");
			enfocarMouseEnElemento('txt_medicion_gradiente_pico_pico');
			return false;
		}
		else if (parseInt(recopilarinfotextbox('txt_medicion_gradiente_pico_pico')) == 0) {
			alert("el campo de la medicion del gradiente pico pico no debe tener 0 mm");
			enfocarMouseEnElemento('txt_medicion_gradiente_pico_pico');
			return false;
		}
	} 
	
	if (recopilarindexCombobox('seleccion_estenosis_aortica') > 0) {
		if (isNaN(parseInt(recopilarinfotextbox('txt_medicion_gradiente_pico_pico')))){
			alert("el campo de la medicion del gradiente pico pico no debe quedar vacio si se escogio una estenosis leve, moderada o severa");
			enfocarMouseEnElemento('txt_medicion_gradiente_pico_pico');
			return false;
		}
		else if (parseInt(recopilarinfotextbox('txt_medicion_gradiente_pico_pico')) == 0) {
			alert("el campo de la medicion del gradiente pico pico no debe tener 0 mm");
			enfocarMouseEnElemento('txt_medicion_gradiente_pico_pico');
			return false;
		}
	} 
	return true;
}

function recopilarInformeVentriculografia(){
	/** *************************** */
	/** *Informe Ventriculografia** */
	/** *************************** */
	 cadenaInformeVentriculografia ="";
		if (verEstadoCheckbox('ventriculografia_activacion')) {
			cadenaInformeVentriculografia = "Ventriculo izquierdo ";
			cadenaInformeVentriculografia += recopilarValueCombobox('seleccion_ventriculo_izq_dilatado')+",";
	    	cadenaInformeVentriculografia += " de contractibilidad ";
			
			if(recopilarindexCombobox('seleccion_contratibilidad_2')>0){
				cadenaInformeVentriculografia += recopilarTextCombobox('seleccion_contratibilidad_2')+"mente ";
			}
			
			cadenaInformeVentriculografia += recopilarTextCombobox('seleccion_contratibilidad_1');
			if(recopilarindexCombobox('seleccion_contratibilidad_3')>0){
				cadenaInformeVentriculografia += " con "
					+ recopilarTextCombobox('seleccion_contratibilidad_3');
			}
			
			if(recopilarindexCombobox('seleccion_contratibilidad_4')>0){
				cadenaInformeVentriculografia += " en la pared "
					+ recopilarTextCombobox('seleccion_contratibilidad_4');
			}

			cadenaInformeVentriculografia += ".FEVI " + recopilarTextCombobox('seleccion_FEVI')+".";
			cadenaInformeVentriculografia += "\n";
			cadenaInformeVentriculografia += "Valvula mitral de aspecto "
					+ recopilarTextCombobox('seleccion_aspecto_valvulamitral');
			if (recopilarindexCombobox('seleccion_insuficiencia_valvulamitral') > 0) {
				cadenaInformeVentriculografia += " con insufiencia "
						+ recopilarTextCombobox('seleccion_insuficiencia_valvulamitral');
			} else {
				cadenaInformeVentriculografia += " sin insuficiencia";
			}
			cadenaInformeVentriculografia += ".";
			
		}else{
			cadenaInformeVentriculografia = "No se realiz\u00f3 Ventriculografia.";
		}
	
		reemplazarinfotextbox("contenido_informe_ventriculografia",cadenaInformeVentriculografia);
}


function recopilarInformeAortograma(){
	
	 cadenaInformeAortograma ="";
		/** *************************** */
		/** ****Informe aortograma***** */
		/** *************************** */
		if (verEstadoCheckbox('aortograma_activacion')) {
		    cadenaInformeAortograma = "Raiz aortica "
					+ recopilarTextCombobox('seleccion_raiz_aortica');
			if (!estadoElemento('txt_medicion_raiz_aortica')
					&& (tamanoinfotextbox('txt_medicion_raiz_aortica')) > 0) {
				cadenaInformeAortograma += " de "
						+ recopilarinfotextbox('txt_medicion_raiz_aortica') + " mm";
			}
				
			cadenaInformeAortograma += ".";
			cadenaInformeAortograma += "\n";
			
				
			cadenaInformeAortograma += "Aorta Ascendente "
					+ recopilarTextCombobox('seleccion_aorta_ascendente');
			if (!estadoElemento('txt_medicion_aorta_ascendente')
					&& (tamanoinfotextbox('txt_medicion_aorta_ascendente')) > 0) {
				cadenaInformeAortograma += " de "
						+ recopilarinfotextbox('txt_medicion_aorta_ascendente') + " mm";
			}
			cadenaInformeAortograma += ".";
			cadenaInformeAortograma += "\n";
			
			if (recopilarindexCombobox('seleccion_estenosis_aortica') > 0) {
				cadenaInformeAortograma += "Estenosis Aortica "
						+ recopilarTextCombobox('seleccion_estenosis_aortica');
				if (!estadoElemento('txt_medicion_gradiente_pico_pico')
						&& (tamanoinfotextbox('txt_medicion_gradiente_pico_pico') > 0)) {
					cadenaInformeAortograma += " con gradiente pico pico "
							+ recopilarinfotextbox('txt_medicion_gradiente_pico_pico')
							+ " mm";
				}
				cadenaInformeAortograma += ".";
				cadenaInformeAortograma += "\n";
			}
			
			cadenaInformeAortograma += "Valvula aortica "+ recopilarTextCombobox('seleccion_clase_valvula_aortica');
			cadenaInformeAortograma += " de aspecto "+ recopilarTextCombobox('seleccion_aspecto_valvula_aortica');
			if (recopilarindexCombobox('seleccion_insuficiencia_aortica') > 0) {
				cadenaInformeAortograma += " con insufiencia "+ recopilarTextCombobox('seleccion_insuficiencia_aortica');
			} else {
				cadenaInformeAortograma += " sin insuficiencia";
			}
			cadenaInformeAortograma += ".";
		}
		else{
			cadenaInformeAortograma = "No se realiz\u00f3 Aortograma.";
		}
		reemplazarinfotextbox("contenido_informe_aortograma",cadenaInformeAortograma);
}


function recopilarInformeAnatomiaCoronaria(){
	
	 cadenaInformeAnatomiaCoronaria ="";
		/** *************************** */
		/** Informe Anatomia Coronaria* */
		/** *************************** */
		if (verEstadoCheckbox('anatomia_coronaria_activacion')) {
			//if (verificarInformeAnatomiaCoronaria()) {
			    var indiceRecorrido = 0;
				while (indiceRecorrido < numeroItemsCombobox("Seleccion_Arteria_combo")) {
					/* TIENE O NO TIENE LESION */
					var arteriaSeleccionada = document.getElementById("Seleccion_Arteria_combo").options[indiceRecorrido].value ;
					cadenaInformeAnatomiaCoronaria += document.getElementById("Seleccion_Arteria_combo").options[indiceRecorrido].text +":\n";
					cadenaInformeAnatomiaCoronaria += recopilarinfotextbox('observacion'+arteriaSeleccionada)+"\n\n";
					/*
					 * if((porcentaje_localizacion_lesion_arterias[indiceRecorrido] !=
					 * null)&& (porcentaje_localizacion_lesion_arterias[indiceRecorrido] !=
					 * '')&& (porcentaje_localizacion_lesion_arterias[indiceRecorrido] !=
					 * '0,0,0')) {
					 *  } else{ asignar_arteria_sin_lesion(indiceRecorrido); }
					 */
					indiceRecorrido++;
					// cadena +=i+ "ramas
					// ["+arrayLocalizacionlesion_ramificaciones[i]+"]
					// tipo lesion ["+ tipolesion_ramificaciones[i]+"] porcentaje
					// ["+porcentaje_lesion_ramificaciones[i]+"] oclusion
					// ["+tipo_lesion_por_Oclusion_ramificaciones[i]+"] caracteristica
					// lesion ["+caracteristicas_lesion_ramificaciones[i]+"] flujo timi
					// ["+flujo_timi_ramificaciones[i]+"] bifucrcada con la rama
					// ["+haybifurcacion_ramificaciones[i]+"] medina
					// ["+clasificacionMedina_ramificaciones[i]+"] lesion colateral
					// ["+lesion_circulacion_colateral_ramificaciones[i]+"]";
				}
		  //  }
		}
		else{
			cadenaInformeAnatomiaCoronaria ="Arterias Coronarias sin lesiones significativas.";
		}
		reemplazarinfotextbox("contenido_informe_analisis_anatomia_coronaria",cadenaInformeAnatomiaCoronaria);
	
}

function recopilarInformeBypass(){
	
	if(cadenaInformaBypass.length == 0){
		reemplazarinfotextbox("contenido_informe_bypass", "no se encontr\u00f3 ning\u00fan bypass.");
	}else{
		reemplazarinfotextbox("contenido_informe_bypass", cadenaInformaBypass);
	}
	
}

function limpiarFormularios(){
	reemplazarinfotextbox("conclusiones", "");
	reemplazarinfotextbox("recomendaciones", "");
	reemplazarinfotextbox("txt_medicion_gradiente_pico_pico", "");
	reemplazarinfotextbox("txt_medicion_raiz_aortica", "");
	reemplazarinfotextbox("txt_anesteciologo", "");
	//reemplazarinfotextbox("txtnumdoc", "");
	reemplazarinfotextbox("resumen_procedimiento", "");
	desSeleccionarCheckbox("ventriculografia_activacion");
	desSeleccionarCheckbox("aortograma_activacion");
	desSeleccionarCheckbox("bypass_activacion");
	desSeleccionarCheckbox("anatomia_coronaria_activacion");
	reemplazarindexCombobox("indicaciones_diagnostico_hemodinamia", 0);
	reemplazarindexCombobox("estudio_realizado", 0);
	realizarFormulario(1);
	realizarFormulario(2);
	realizarFormulario(3);
	realizarFormulario(4);
	//activarelementoFormulario("cbafiliacion");
    //activarelementoFormulario("txtnumdoc");
    //activarelementoFormulario("btnBuscarPac");
	activarelementoFormulario('actualizar');
	

}


function enviardatos() {
	/** ****************************************** */
	/** *******Informe ventriculografia ********** */
	/** ****************************************** */
	var ventriculoizqdilatado_Data = "";
	var tipocontractibilidad_DATA = "";
	var gravedadcontractibilidad_DATA = "";
	var caracteristicacontractibilidad_DATA = "";
	var lugarcontractibilidad_DATA = "";
	var fevi_DATA = "";
	var insuficiencia_Mitral_DATA = "";
	var aspectovalvulamitral_DATA = "";
	
	if (swEnviarInformeVentriculografia == 1) {
		ventriculoizqdilatado_Data = ventriculoizqdilatado; // 0 si o 1 no
		tipocontractibilidad_DATA = tipocontractibilidad + 1;
		gravedadcontractibilidad_DATA = gravedadcontractibilidad +1;
		caracteristicacontractibilidad_DATA =  caracteristicacontractibilidad+1;
		lugarcontractibilidad_DATA = lugarcontractibilidad+1;
		fevi_DATA = fevi + 1;
		insuficiencia_Mitral_DATA = insuficienciamitral + 1;
		aspectovalvulamitral_DATA = aspectovalvulamitral + 1;
		ImagenVentriculografiabase64 =ObtenerCanvasDecodificado64("ventriculografia");
	//	alert("ventriculografia: "+ImagenVentriculografiabase64);
		/*alert("ventriculo dilatado: "+ventriculoizqdilatado_Data +
		" tipo contractibilidad: "+tipocontractibilidad_DATA+
		" gravedad_contractibilidad: "+gravedadcontractibilidad_DATA+
		" caracteristica contractibilidad: "+caracteristicacontractibilidad_DATA+
		" lugar contractibilidad: "+lugarcontractibilidad_DATA+
		" FEVI: "+fevi_DATA+
		" insuficiencia mitral: "+insuficiencia_Mitral_DATA+
		" aspecto valvula mitral: "+aspectovalvulamitral_DATA);*/
	}/*else{
		cadenaInformeVentriculografia = "ventriculografia no realizada " + "\n\n";
		
	}*/


	/** ****************************************** */
	/** **********Informe Aortograma************** */
	/** ****************************************** */
	 var raiz_aortica_DATA = "";
	 var txt_rama_aortica_medicion_DATA = "";
	 var aorta_ascendente_DATA = "";
	 var txt_aorta_ascendente_medicion_DATA = "";
	 var clase_valvula_aortica_DATA = "";
	 var aspecto_valvula_aortica_DATA = "";
	 var insuficiencia_aortica_DATA = "";
	 var estenosis_aortica_DATA = "";
	 var txt_gradiente_pico_pico_DATA = "";
	
	if (swEnviarInformeAortograma == 1) {
		if (verificarInformeAortograma()) {
			 raiz_aortica_DATA = raizaortica;
			 if(txtraizaorticamedicion == ''){
				 txt_rama_aortica_medicion_DATA = 0;
			 }
			 else{
				 txt_rama_aortica_medicion_DATA = txtraizaorticamedicion;
			 }
			 
			 aorta_ascendente_DATA = aortaascendente;
			 if(txtaortaascendentemedicion == ''){
				 txt_aorta_ascendente_medicion_DATA = 0;
			 }
			 else{
				 txt_aorta_ascendente_medicion_DATA = txtaortaascendentemedicion;
			 }
			 
			 estenosis_aortica_DATA = estenosisaortica + 1;
			 if(txtgradientepicopico == ''){
				 txt_gradiente_pico_pico_DATA = 0;
			 }else{
				 txt_gradiente_pico_pico_DATA = txtgradientepicopico;
			 }
			 clase_valvula_aortica_DATA = clasevalvulaAortica + 1;
			 aspecto_valvula_aortica_DATA = aspectovalvulaAortica + 1;
			 insuficiencia_aortica_DATA = insuficienciaAortica + 1;
			
			/* alert("raiz aortica: "+ raiz_aortica_DATA +
				  " medicion raiz aortica: "+ txt_rama_aortica_medicion_DATA +
				  " clase_valvula_aortica_DATA "+clase_valvula_aortica_DATA +
				  " aspecto valvula aortica: " +aspecto_valvula_aortica_DATA +
				  " insuficiencia aortica: "+insuficiencia_aortica_DATA	+
				  " estenosis aortica: "+estenosis_aortica_DATA +
				  " gradiente pico pico: "+txt_gradiente_pico_pico_DATA
			 );*/
		} else {
			alert('El informe de aortograma esta incompleto, por favor revice los campos de raiz aortica y de gradiente pico pico');
			return;
		}

	}/*else{
		cadenaInformeAortograma = "Aortograma no realizado " + "\n\n";
	}*/

	
	
	/** ****************************************** */
	/** *******Informe BYPASS ******************** */
	/** ****************************************** */
	
	
	
	/** ****************************************** */
	/** *******Informe anatomia coronaria********* */
	/** ****************************************** */
	var porcentaje_lesion_DATA = new Array();
	var caracteristicas_lesion_DATA = new Array();
	var flujo_timi_DATA = new Array();
	var lesion_circulacion_colateral_DATA = new Array();
	var tipo_lesion_por_Oclusion_DATA = new Array();
	var lesion_tipoABC_DATA = new Array();
	var lecho_distal_DATA = new Array();
	var haybifurcacion_DATA = new Array();
	var clasificacionMedina_DATA = new Array();

	var indiceRecorrido = 0;
    var arregloArteriasAbreviadas = new Array(); 
    	
	while (indiceRecorrido < numeroItemsCombobox("Seleccion_Arteria_combo")) {
		   arteriaSeleccionada = document.getElementById("Seleccion_Arteria_combo").options[indiceRecorrido].value ;
		   arregloArteriasAbreviadas[indiceRecorrido]= arteriaSeleccionada;
		   indiceRecorrido++;
	}
	
	ImagenAnatomiaCoronariabase64 =ObtenerCanvasDecodificado64("anatomia_coronaria");
	
//	if(swEnviarInformeAnatomiaCoronia ==1){
	//	if (verificarInformeAnatomiaCoronaria()) {
			//alert("imagen anatomia: "+ImagenAnatomiaCoronariabase64);
		
		/*alert("---"+haybifurcacion_arterias[1]);
		
		if(haybifurcacion_arterias[1]== undefined){
			alert("ok");
		}*/
		
		/*alert("lechos distales: " +lecho_distal_arterias.toString());
		alert("flujo timi: "+flujo_timi_arterias.toString());
		alert('porcentajes:'+porcentaje_localizacion_lesion_arterias.toString());
		alert('tipos de oclusion: '+ tipo_lesion_por_oclusion_arterias.toString());
		alert('circulacion: '+lesion_circulacion_colateral_arterias.toString());
		alert('caracteristicas: '+caracteristicas_lesion_arterias.toString());
		alert('tipo lesion: '+tipo_lesion_arterias.toString());
		alert('bifurcaciones:'+haybifurcacion_arterias.toString());
		alert('clasificacion medina:'+clasificacionMedina_arterias.toString());*/
		
		var j = 0;
		var cadenaauxporcentajes ="";
		var cadenaauxoclusiones  = "";
		var cadenaauxcirculacion = "";
		var cadenaauxcaracteristicas = "";
		var cadenaauxotipolesion = ""; 
		var cadenaauxbifurcaciones = "";
		var cadenaauxauxmedina = "";
		
		for (var i = 0; i < numeroItemsCombobox("Seleccion_Arteria_combo"); i++) {
			 cadenaauxporcentajes ="";
			 cadenaauxoclusiones  = "";
			 cadenaauxcirculacion = "";
			 cadenaauxcaracteristicas = "";
			 cadenaauxotipolesion = ""; 
			 cadenaauxbifurcaciones = "";
			 cadenaauxauxmedina = "";
			if (!estadoElemento('observacion'+arregloArteriasAbreviadas[i])){
				j=0;
	 			var arrayauxporcentajes =porcentaje_localizacion_lesion_arterias[i].split("-");
				while (j < parseInt(numero_lesiones_arterias_general[i])) {
					cadenaauxporcentajes += (parseInt(arrayauxporcentajes[j])+1)+"-";
					if(haybifurcacion_arterias[i]== undefined){
					//	alert("entra aca");
						cadenaauxbifurcaciones += "1"+"-";
						cadenaauxauxmedina += "1"+"-";
					}
				j++;
				}
				
				 cadenaauxporcentajes = cadenaauxporcentajes.substring(0, cadenaauxporcentajes.length-1);
				 porcentaje_lesion_DATA[i] = cadenaauxporcentajes;
				
				 caracteristicas_lesion_DATA[i] = caracteristicas_lesion_arterias[i];
				 lesion_circulacion_colateral_DATA[i] = lesion_circulacion_colateral_arterias[i];
				 tipo_lesion_por_Oclusion_DATA[i] = tipo_lesion_por_oclusion_arterias[i];
				 lesion_tipoABC_DATA[i] =tipo_lesion_arterias[i];
				 lecho_distal_DATA[i] =lecho_distal_arterias[i];
				 flujo_timi_DATA[i] = flujo_timi_arterias[i];
		
				 if(cadenaauxbifurcaciones.length == 0){
					 haybifurcacion_DATA[i] = haybifurcacion_arterias[i];
					 clasificacionMedina_DATA[i] = clasificacionMedina_arterias[i];
					 
				 }
				 else{
					 cadenaauxbifurcaciones = cadenaauxbifurcaciones.substring(0, cadenaauxbifurcaciones.length-1);
					 haybifurcacion_DATA[i] = cadenaauxbifurcaciones;
					 cadenaauxauxmedina = cadenaauxauxmedina.substring(0, cadenaauxauxmedina.length-1);
					 clasificacionMedina_DATA[i] = cadenaauxauxmedina;
					 
				 }
				
				 
			}else{
				j=0;
				while (j < parseInt(numero_lesiones_arterias_general[i])) {
					cadenaauxporcentajes +="1"+"-";
					cadenaauxoclusiones  += "1"+"-";
					cadenaauxcirculacion += "1"+"-";
					cadenaauxcaracteristicas += "1"+"-";
					cadenaauxotipolesion += "1"+"-"; 
					cadenaauxbifurcaciones += "1"+"-";
					cadenaauxauxmedina += "1"+"-";
				j++;
				}
				
				 cadenaauxporcentajes = cadenaauxporcentajes.substring(0, cadenaauxporcentajes.length-1);
				 cadenaauxoclusiones = cadenaauxoclusiones.substring(0, cadenaauxoclusiones.length-1);
				 cadenaauxcirculacion = cadenaauxcirculacion.substring(0, cadenaauxcirculacion.length-1);
				 cadenaauxcaracteristicas = cadenaauxcaracteristicas.substring(0, cadenaauxcaracteristicas.length-1);
				 cadenaauxotipolesion = cadenaauxotipolesion.substring(0, cadenaauxotipolesion.length-1);
				 cadenaauxbifurcaciones = cadenaauxbifurcaciones.substring(0, cadenaauxbifurcaciones.length-1);
				 cadenaauxauxmedina = cadenaauxauxmedina.substring(0, cadenaauxauxmedina.length-1);
				
				 porcentaje_lesion_DATA[i] = cadenaauxporcentajes;
				 caracteristicas_lesion_DATA[i] = cadenaauxcaracteristicas;
				 lesion_circulacion_colateral_DATA[i] = cadenaauxcirculacion;
				 tipo_lesion_por_Oclusion_DATA[i] = cadenaauxoclusiones;
				 lesion_tipoABC_DATA[i] =cadenaauxotipolesion;
				 haybifurcacion_DATA[i] = cadenaauxbifurcaciones;
				 clasificacionMedina_DATA[i] = cadenaauxauxmedina;
				 lecho_distal_DATA[i] = "1";
				 flujo_timi_DATA[i] = "1";
				
			}
			}
		

		/*alert(" lecho distales: "+ lecho_distal_DATA.toString());
		alert(" flujo timi: "+ flujo_timi_DATA.toString());
		alert(" porcentajes "+i + " : "+ porcentaje_lesion_DATA.toString() );
		alert(" oclusion "+i + " : "+ tipo_lesion_por_Oclusion_DATA.toString() );
		alert(" circulacion "+i + " :"+lesion_circulacion_colateral_DATA.toString() );
		alert(" caracteristicas "+i + " :"+caracteristicas_lesion_DATA.toString());
		alert(" tipo lesion "+i + " :"+ lesion_tipoABC_DATA.toString());
		alert(" bifurcacion"+i + " :"+haybifurcacion_DATA.toString());
		alert(" medina "+i + " :"+clasificacionMedina_DATA.toString());*/
		
		
	
//	}
	

	var anestesiologo = document.getElementById('txt_anesteciologo').value;
	if (anestesiologo.length ==0){
		anestesiologo = 'NO';
	}
	
    if (tamanoinfotextbox('resumen_procedimiento')>0){
		if(tamanoinfotextbox('conclusiones')>0){
			if(tamanoinfotextbox('recomendaciones')>0){
				desactivarelementoFormulario('actualizar');
				ajax = getXMLObject();
					ajax.open("POST", "cateterismomodificaciondata", true);
					ajax.onreadystatechange = function() {
						if (ajax.readyState == 4) {
							if (ajax.responseText != '0') {
								alert("ha realizado correctamente la actualizacion el informe");
								mostrarInformesHemodinamia(ajax.responseText);
							} else{
								alert("hubo un error al momento de ingresar su informe");
							}
							
							//window.location.reload();
							   var minavegador = new Browser();
							    if (minavegador.name == 'firefox'){
							    	 window.open('','_parent','');
							    }
							    
							   
							    window.close();
							    
						}							
					};
					
				    ajax.setRequestHeader('Content-Type',
							'application/x-www-form-urlencoded;charset=utf-8');
				
					
			
						ajax.send(
					// Datos ventriculagrafia
					  "ventriculoizqdilatado="+ventriculoizqdilatado_Data
					+ "&tipocontractivibilidad="+tipocontractibilidad_DATA
					+ "&gravedadcontractibilidad="+gravedadcontractibilidad_DATA
					+ "&caracteristicacontractivilidad="+caracteristicacontractibilidad_DATA 
					+ "&lugarcontractibilidad="+lugarcontractibilidad_DATA 
					+ "&fevi=" + fevi_DATA
					+ "&insuficienciamitral="+ insuficiencia_Mitral_DATA 
					+ "&aspectovalvulamitral="+aspectovalvulamitral_DATA
					+ "&inserccionventriculografia= "+swEnviarInformeVentriculografia
					+ "&cadenaimagenventriculografia= "+ encodeURIComponent(ImagenVentriculografiabase64)
					
					// Datos aortograma
					+ "&raizaortica="+ raiz_aortica_DATA
					+ "&raizaorticamedicion="+txt_rama_aortica_medicion_DATA
					+ "&aortaascendente="+aorta_ascendente_DATA
					+ "&aortaascendentemedicion="+txt_aorta_ascendente_medicion_DATA 
					+ "&estenosisaotica="+ estenosis_aortica_DATA      
					+ "&gradientepicopico="+txt_gradiente_pico_pico_DATA
					+ "&clasevalulaaortica="+clase_valvula_aortica_DATA
					+ "&aspectovalvulaaortica="+aspecto_valvula_aortica_DATA
					+ "&insuficienciaaortica="+insuficiencia_aortica_DATA
					+ "&inserccionaortograma="+swEnviarInformeAortograma
					
					//datos bypass
					//no hay
					
					
					
					// Datos anatomia coronaria
					+ "&dominancia="+ recopilarindexCombobox("seleccion_dominancia")
					+ "&porcentaje_localizacion_lesiones=" + porcentaje_lesion_DATA  
					+ "&tipolesiones="+ lesion_tipoABC_DATA 
					+ "&lesionesporoclusion="+ tipo_lesion_por_Oclusion_DATA 
					+ "&caracteristicaslesiones="+ caracteristicas_lesion_DATA 
					+ "&flujotimilesiones="+ flujo_timi_DATA 
					+ "&lechodistal="+lecho_distal_DATA
					+ "&lesionesbifurcadas="+ haybifurcacion_DATA 
					+ "&medinalesiones="+ clasificacionMedina_DATA 
					+ "&circulacioncolaterallesiones="+ lesion_circulacion_colateral_DATA 
				//	+ "&localizacioneslesionesgeneral="+tipo_lesiones_arterias_general
					+ "&inserccionanatomiacoronaria="+swEnviarInformeAnatomiaCoronia
					+ "&cadenaimagenanatomiacoronaria="+ encodeURIComponent(ImagenAnatomiaCoronariabase64)
					
					
					// Datos generales Informe
				//	+ "&codpaciente=" + codpacientediagnostico
				//	+ "&codusuario=" +  recopilarinfotextbox("txtUsuario")
					+ "&anestesiologo="+ anestesiologo
					+ "&estudiorealizado=" +(recopilarindexCombobox("estudio_realizado")+1)
					+ "&indicacion="+ (recopilarindexCombobox('indicaciones_diagnostico_hemodinamia')+1)
					+ "&resumeninforme="+ encodeURIComponent(recopilarinfotextbox('resumen_procedimiento'))
					
					+ "&informeventriculografia="+encodeURIComponent(recopilarinfotextbox('contenido_informe_ventriculografia'))
					+ "&informeaortograma="+encodeURIComponent(recopilarinfotextbox('contenido_informe_aortograma'))
					+ "&informebypass="+encodeURIComponent(recopilarinfotextbox('contenido_informe_bypass'))
					
					+ "&informeanatomiacoronariaTCI="+encodeURIComponent(recopilarinfotextbox('observacion'+arregloArteriasAbreviadas[0]))
					+ "&informeanatomiacoronariaADA="+encodeURIComponent(recopilarinfotextbox('observacion'+arregloArteriasAbreviadas[1]))
					+ "&informeanatomiacoronariaAC="+ encodeURIComponent(recopilarinfotextbox('observacion'+arregloArteriasAbreviadas[2]))
					+ "&informeanatomiacoronariaRI="+ encodeURIComponent(recopilarinfotextbox('observacion'+arregloArteriasAbreviadas[3]))
					+ "&informeanatomiacoronariaACD="+ encodeURIComponent(recopilarinfotextbox('observacion'+arregloArteriasAbreviadas[4]))
					+ "&informeanatomiacoronariaADP="+ encodeURIComponent(recopilarinfotextbox('observacion'+arregloArteriasAbreviadas[5]))
					+ "&informeanatomiacoronariaTPL="+ encodeURIComponent(recopilarinfotextbox('observacion'+arregloArteriasAbreviadas[6]))
					+ "&informeanatamiacoronariaD1="+ encodeURIComponent(recopilarinfotextbox('observacion'+arregloArteriasAbreviadas[7]))
					+ "&informeanatamiacoronariaD2="+ encodeURIComponent(recopilarinfotextbox('observacion'+arregloArteriasAbreviadas[8]))
					+ "&informeanatamiacoronariaO1="+ encodeURIComponent(recopilarinfotextbox('observacion'+arregloArteriasAbreviadas[9]))
					+ "&informeanatamiacoronariaO2="+ encodeURIComponent(recopilarinfotextbox('observacion'+arregloArteriasAbreviadas[10]))
							
					+ "&conclusiones="+ encodeURIComponent(recopilarinfotextbox('conclusiones'))
					+ "&recomendaciones="+ encodeURIComponent(recopilarinfotextbox('recomendaciones'))
				    + "&idinforme="+recopilarinfotextbox("txtcodInforme")
				    + "&inserccionesprevias="+informespreviosrealizados
					+ "&idsarteriasdelinforme="+ids_arterias_del_informe
					+ "&abreviacionesporarterias="+arregloArteriasAbreviadas
					+ "&opcion=4"
				    );
				    
				 
			}else{
				alert("coloque las recomendaciones del informe");
			}
		}else{
			alert("coloque las conclusiones del informe");
		}
	}
	else{
		alert("coloque la justificaci\u00f3n del informe");
	}
	

}




function mostrarInformesHemodinamia(idcodInforme){
	pagina="cat_reporteCateterismo.jsp?CodInfCateterismo="+idcodInforme;			
   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 	opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85, left=140";
   	window.open(pagina,"Informe_Cateterismo",opciones);
}






/*
 * function ocultarElemento(id) { // document.getElementById(id).style.display =
 * "none"; document.getElementById(id).style.visibility = "hidden"; }
 * 
 * function mostrarElemento(id) { // document.getElementById(id).style.display =
 * "inline"; document.getElementById(id).style.visibility = "visible"; }
 */

/*
 * function desSeleccionarRadios(gruporadio) { for ( var i = 0; ele =
 * document.getElementsByName(gruporadio)[i]; i++) // getElementsByName para
 * seleccionar los radios ele.checked = false; }
 */

/*
 * function recopilarinfoRadios(grupoRadios) { var Cadena = ""; for ( var i = 0;
 * ele = document.getElementsByName(grupoRadios)[i]; i++) if (ele.checked ==
 * true) { Cadena = ele.value; } return Cadena; }
 */


  function desSeleccionarCheckbox(grupocheck) { 
	for ( var i = 0; ele = document.getElementsByName(grupocheck)[i]; i++) // getElementsByName para  seleccionar los radios 
	  ele.checked = 0; 
  }
  function SeleccionarCheckbox(grupocheck) { 
		for ( var i = 0; ele = document.getElementsByName(grupocheck)[i]; i++) // getElementsByName para  seleccionar los radios 
		  ele.checked = 1; 
	  }
  
  
  function ObtenerCanvasDecodificado64(nombreCanvas) {
		var minavegador =  new Browser();
	    var propiedad = true; 
	 //   alert('entra '+minavegador.name +' '+minavegador.version);
	    if (minavegador.name =='iexplorer'){
	    	  
			if(minavegador.version ==6){
		 
				propiedad = false;
			}
	        if(minavegador.version ==7){
	      
				propiedad= false;
			}
	        if(minavegador.version ==8){
	      
	        	propiedad= false;
	        }
		}
	
	if (propiedad== true){
		
		var canvas = document.getElementById(nombreCanvas);
	    if (canvas.getContext) {
	    	 // var dataURL = canvas.toDataURL("image/png");
	    	 var dataURL = canvas.toDataURL();
	    	 dataURL = dataURL.replace(/^data:image\/(png|jpeg);base64,/, "") ;    
    	     return dataURL;            // Get the data as an image.
	    }
		
	}
		else{
			alert("tu navegador no puede guardar la imagen "+nombreCanvas+" por favor actualice su navegador");
			return "";
		}

	}
