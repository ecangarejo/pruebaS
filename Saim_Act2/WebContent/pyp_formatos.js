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
	

//codigo clave del paciente seleccionado
var codpacientediagnostico = "";	
		
//funcion para saber si se encuentra el paciente registrado en el sistema
function pacienteActivo() {
	var TipoDocumento = document.getElementById("cbafiliacion").value;
	var NumeroDocumento = document.getElementById("txtnumdoc").value;
//	var user=document.getElementById("txtUsuario").value;
	if (NumeroDocumento == "") {
		alert("Escriba Numero Documento");
		document.getElementById('txtnumdoc').focus();
	} else {
		ajax = getXMLObject();
		ajax.open("POST", "ControlForm", true); // getname will be the
		// servlet name
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
				//alert(ajax.responseText);
				var datos = ajax.responseText.split("|");
				//codpacientediagnostico = datos[1];
				//alert(datos);
				if (datos[0] == 1) {
					
					document.getElementById('datos_ingreso_paciente').innerHTML = datos[2];
					var GenValida=document.getElementById("txtGenValida").value;
					window.location.href="pyp_FormatoHMI.jsp?GenValida="+GenValida+"&TipoDocumento="+TipoDocumento+"&NumeroDocumento="+NumeroDocumento;
					/*					
					aparecerElemento("nivel_educativo");
					aparecerElemento("convivientes");
					aparecerElemento("actividad_laboral");
					aparecerElemento("viviendad_habitat");
					aparecerElemento("antecedentes_familiares");
					aparecerElemento("antecedentes_personales");
					aparecerElemento("historia_embarazos");
					aparecerElemento("historias_menstruales");
					aparecerElemento("historia_conceptual");
					aparecerElemento("planificacion_familiar");
					aparecerElemento("examen_fisico");
					aparecerElemento("examenes_practicados");
					aparecerElemento("riesgo_reproductivo");
					aparecerElemento("metodo_reproductivo");
					aparecerElemento("controlesEnfermeria");
					aparecerElemento("conductasSeguir");
					aparecerElemento("observacionesEspeciales");
					aparecerElemento("evolucionClinica");
					aparecerElemento("encuesta_pyp");
					codpacientediagnostico = datos[1];
					
					desactivarelementoFormulario("cbafiliacion");
		            desactivarelementoFormulario("txtnumdoc");
		            desactivarelementoFormulario("btnBuscarPac");*/
					
					
	            
					//alert("Paciente Encontrado!!");		            		            
					
					
		            //
				} else {
					if (codpacientediagnostico.length == 0) {
						document.getElementById("datos_ingreso_paciente").innerHTML = datos[1];
					   	window.location.href = "adm_IngresarDemografico.jsp";
					} else {
						alert(datos[1]);
					}

				}

			}
		};
	//	alert("Datos Enviados");
		ajax.setRequestHeader('Content-Type',
				'application/x-www-form-urlencoded;charset=utf-8');
	//	alert("valor=1&TipoDocumento=" + TipoDocumento
	//			+ "&NumeroDocumento=" + NumeroDocumento);
		ajax.send("valor=1&TipoDocumento=" + TipoDocumento
				+ "&NumeroDocumento=" + NumeroDocumento); // Posting txtname
		// to Servlet
	}
}
	
function pacienteActivo2() {
	var TipoDocumento = document.getElementById("cbafiliacion").value;
	var NumeroDocumento = document.getElementById("txtnumdoc").value;
//	var user=document.getElementById("txtUsuario").value;
	if (NumeroDocumento == "") {
		//alert("Escriba Numero Documento");
		document.getElementById('txtnumdoc').focus();
	} else {
		ajax = getXMLObject();
		ajax.open("POST", "ControlForm", true); // getname will be the
		// servlet name
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
				//alert(ajax.responseText);
				var datos = ajax.responseText.split("|");
				//codpacientediagnostico = datos[1];
				//alert(datos);
				if (datos[0] == 1) {
					
					document.getElementById('datos_ingreso_paciente').innerHTML = datos[2];
					//var GenValida=document.getElementById("txtGenValida").value;
										
					aparecerElemento("nivel_educativo");
					aparecerElemento("convivientes");
					aparecerElemento("actividad_laboral");
					aparecerElemento("viviendad_habitat");
					aparecerElemento("antecedentes_familiares");
					aparecerElemento("antecedentes_personales");
					aparecerElemento("historia_embarazos");
					aparecerElemento("historias_menstruales");
					aparecerElemento("historia_conceptual");
					aparecerElemento("planificacion_familiar");
					aparecerElemento("examen_fisico");
					aparecerElemento("examenes_practicados");
					aparecerElemento("riesgo_reproductivo");
					aparecerElemento("metodo_reproductivo");
					aparecerElemento("controlesEnfermeria");
					aparecerElemento("conductasSeguir");
					aparecerElemento("observacionesEspeciales");
					aparecerElemento("evolucionClinica");
					aparecerElemento("encuesta_pyp");
					codpacientediagnostico = datos[1];
					
					desactivarelementoFormulario("cbafiliacion");
		            desactivarelementoFormulario("txtnumdoc");
		            desactivarelementoFormulario("btnBuscarPac");
					
					//window.location.href="pyp_FormatoHMI.jsp?GenValida="+GenValida;
	            
					//alert("Paciente Encontrado!!");		            		            
					
					
		            //
				} else {
					if (codpacientediagnostico.length == 0) {
						document.getElementById("datos_ingreso_paciente").innerHTML = datos[1];
					   	window.location.href = "adm_IngresarDemografico.jsp";
					} else {
						alert(datos[1]);
					}

				}

			}
		};
	//	alert("Datos Enviados");
		ajax.setRequestHeader('Content-Type',
				'application/x-www-form-urlencoded;charset=utf-8');
	//	alert("valor=1&TipoDocumento=" + TipoDocumento
	//			+ "&NumeroDocumento=" + NumeroDocumento);
		ajax.send("valor=1&TipoDocumento=" + TipoDocumento
				+ "&NumeroDocumento=" + NumeroDocumento); // Posting txtname
		// to Servlet
	}
}





function aparecerElemento(id) {
	document.getElementById(id).style.display = "inline";
}


function desaparecerElemento(id) {
	 document.getElementById(id).style.display = "none";
}


function activarelementoFormulario(idCombo) {
	document.getElementById(idCombo).disabled = false;
}

function desactivarelementoFormulario(idCombo) {
	document.getElementById(idCombo).disabled = true;
}


function recopilarinfotextbox(idText) {
	return document.getElementById(idText).value;
}


								/*VALIDACIONES TABLA CONVIVIENTES*/
function clicksiHijos(){
	document.getElementById("numHijos").disabled=false;
	document.getElementById("noNietos").checked=false;
	document.getElementById("siNietos").disabled=false;
	document.getElementById("numHijos").focus();
}

function clicksiNietos(){
	document.getElementById("numNietos").disabled=false;	
	document.getElementById("numNietos").focus();
}

function clicksiPadres(){
	document.getElementById("numPadres").disabled=false;
	document.getElementById("numPadres").focus();
}

function clicksiOtros(){
	document.getElementById("numOtros").disabled=false;
	document.getElementById("numOtros").focus();
}

function clicksiFuma(){
	document.getElementById("campoFuma").disabled=false;
	document.getElementById("campoFuma").focus();
}

function clicksiAlcohol(){
	document.getElementById("campoAlcohol").disabled=false;
	document.getElementById("campoAlcohol").focus();
}

function clicksiDrogas(){
	document.getElementById("campoDrogas").disabled=false;
	document.getElementById("campoDrogas").focus();
}

function clicksiActividadFisica(){
	document.getElementById("campoActividadFisica").disabled=false;
	document.getElementById("campoActividadFisica").focus();
}

function clickNoHijos(){
	document.getElementById("noNietos").checked=true;
	document.getElementById("siNietos").disabled=true;
	document.getElementById("numNietos").disabled=true;
}


function GuardarDatosPersonales(){	

	var nivEstudio = document.getElementById("nivelEstudio").value;
	var chk_solo = document.getElementById("chk_solo").checked;
	var chk_conyugue = document.getElementById("chk_conyugue").checked;
	var chk_convivencia = "";
	var radsiHijos = document.getElementById("siHijos").checked;
	var radnoHijos = document.getElementById("noHijos").checked;
	var numHijos = document.getElementById("numHijos").value;
	var radsiNietos = document.getElementById("siNietos").checked;
	var radnoNietos = document.getElementById("noNietos").checked;
	var numNietos = document.getElementById("numNietos").value;
	var radsiPadres = document.getElementById("siPadres").checked;
	var radnoPadres = document.getElementById("noPadres").checked;
	var numPadres = document.getElementById("numPadres").value;
	var radsiOtros = document.getElementById("siOtros").checked;
	var radnoOtros = document.getElementById("noOtros").checked;
	var numOtros = document.getElementById("numOtros").value;
	var actLaboral = document.getElementById("actividadLaboral").value;
	var radsiFuma = document.getElementById("siFuma").checked;
	var radnoFuma = document.getElementById("noFuma").checked;
	var campoFuma = document.getElementById("campoFuma").value;
	var radsiAlcohol = document.getElementById("siAlcohol").checked;
	var radnoAlcohol = document.getElementById("noAlcohol").checked;
	var campoAlcohol = document.getElementById("campoAlcohol").value;
	var radsiDrogas = document.getElementById("siDrogas").checked;
	var radnoDrogas = document.getElementById("noDrogas").checked;
	var campoDrogas = document.getElementById("campoDrogas").value;
	var radsiActividad_Fisica = document.getElementById("siActividad_Fisica").checked;
	var radnoActividad_Fisica = document.getElementById("noActividad_Fisica").checked;
	var campoActividadFisica = document.getElementById("campoActividadFisica").value;
	var vivPropiedad = document.getElementById("vivPropiedad").value;
	
	if(nivEstudio == "Seleccione"){
		   alert("Falta Seleccionar El Nivel De Estudio");
		   document.getElementById("nivelEstudio").focus();
	}else{
		if(chk_solo==false && chk_conyugue==false){
			alert("Seleccione si vive solo o con su conyugue");
		}else{
			if(chk_solo==true){
				chk_convivencia="Soltero(a)";
			}
			if(chk_conyugue==true){
				chk_convivencia="Conyugue";
			}
			
			
		    //Validacion Radio Padres
			if (radsiPadres==false && radnoPadres==false){
					alert("Seleccione si alguno de sus padres esta vivo");
			}else{
					var x=document.getElementById("numPadres").value;
					var y=x.length;
              
				if((radsiPadres==true)&&(y!=0)&&(x=="0")){
					alert("No Acepta Cero");					
			}else{
				if((radsiPadres==true)&&(y!=0)&&(x!="0")){
					numPadres;					
				}
               
				if(radsiPadres==true&&y==0){
					alert("Coloque la cantidad de padres vivos");
					document.getElementById("numPadres").focus();
			}else{	
				if(radnoPadres==true){
					numPadres="0";	
				}
			
				
			//Validacion Radio Hijos
			if (radsiHijos==false && radnoHijos==false){
					alert("Seleccione si tiene hijos o no");
			}else{
					var x=document.getElementById("numHijos").value;
					var y=x.length;
              
				if((radsiHijos==true)&&(y!=0)&&(x=="0")){
					alert("No Acepta Cero");					
			}else{
				if((radsiHijos==true)&&(y!=0)&&(x!="0")){
					numHijos;					
				}
               
				if(radsiHijos==true&&y==0){
					alert("Coloque la cantidad de hijos");
					document.getElementById("numHijos").focus();
			}else{	
				if(radnoHijos==true){
					numHijos="0";	
				}
					
				
			//Validacion Radio Nietos
			if (radsiNietos==false && radnoNietos==false){
					alert("Seleccione si tiene nietos o no");
			}else{
					var x=document.getElementById("numNietos").value;
					var y=x.length;
              
				if((radsiNietos==true)&&(y!=0)&&(x=="0")){
					alert("No Acepta Cero");					
			}else{
				if((radsiNietos==true)&&(y!=0)&&(x!="0")){
					numNietos;					
				}
               
				if(radsiNietos==true&&y==0){
					alert("Coloque la cantidad de nietos");
					document.getElementById("numNietos").focus();
			}else{	
				if(radnoNietos==true){
					numNietos="0";	
				}
				
			
			//Validacion Radio Otros
			if (radsiOtros==false && radnoOtros==false){
					alert("Seleccione otros.");
			}else{
					var x=document.getElementById("numOtros").value;
					var y=x.length;
              
				if((radsiOtros==true)&&(y!=0)&&(x=="0")){
					alert("No Acepta Cero");					
			}else{
				if((radsiOtros==true)&&(y!=0)&&(x!="0")){
					numOtros;					
				}
               
				if(radsiOtros==true&&y==0){
					alert("Coloque la cantidad de otros");
					document.getElementById("numOtros").focus();
			}else{	
				if(radnoOtros==true){
					numOtros="0";	
				}

			if(actLaboral == "Seleccione"){
				   alert("Falta Seleccionar Actividad Laboral!!!");
				   document.getElementById("actividadLaboral").focus();
			}else{
			

				//Validacion Radio Fuma
				if (radsiFuma==false && radnoFuma==false){
						alert("Seleccione si fuma o no");
				}else{
						var x=document.getElementById("campoFuma").value;
						var y=x.length;
	              
					if((radsiFuma==true)&&(y!=0)&&(x=="0")){
						alert("No Acepta Cero");					
				}else{
					if((radsiFuma==true)&&(y!=0)&&(x!="0")){
						campoFuma;					
					}
	               
					if(radsiFuma==true&&y==0){
						alert("Coloque cuantos cigarrillos fuma");
						document.getElementById("campoFuma").focus();
				}else{	
					if(radnoFuma==true){
						campoFuma="No";	
					}		
			
				//Validacion Radio Alcohol
				if (radsiAlcohol==false && radnoAlcohol==false){
						alert("Seleccione si toma o no");
				}else{
						var x=document.getElementById("campoAlcohol").value;
						var y=x.length;
	              
					if((radsiAlcohol==true)&&(y!=0)&&(x=="0")){
						alert("No Acepta Cero");					
				}else{
					if((radsiAlcohol==true)&&(y!=0)&&(x!="0")){
						campoAlcohol;					
					}
	               
					if(radsiAlcohol==true&&y==0){
						alert("Coloque cuantas botellas toma");
						document.getElementById("campoAlcohol").focus();
				}else{	
					if(radnoAlcohol==true){
						campoAlcohol="No";	
					}
										 
				//Validacion Radio Drogas
				if (radsiDrogas==false && radnoDrogas==false){
						alert("Seleccione si consume drogas o no");
				}else{
						var x=document.getElementById("campoDrogas").value;
						var y=x.length;
	              
					if((radsiDrogas==true)&&(y!=0)&&(x=="0")){
						alert("No Acepta Cero");					
				}else{
					if((radsiDrogas==true)&&(y!=0)&&(x!="0")){
						campoDrogas;					
					}
	               
					if(radsiDrogas==true&&y==0){
						alert("Coloque que drogas consume");
						document.getElementById("campoDrogas").focus();
				}else{	
					if(radnoDrogas==true){
						campoDrogas="No";	
					} 				 			
			
				//Validacion Radio Actividad Fisica
				if (radsiActividad_Fisica==false && radnoActividad_Fisica==false){
						alert("Seleccione si realiza actividad fisica o no");
				}else{
						var x=document.getElementById("campoActividadFisica").value;
						var y=x.length;
	              
					if((radsiActividad_Fisica==true)&&(y!=0)&&(x=="0")){
						alert("No Acepta Cero");					
				}else{
					if((radsiActividad_Fisica==true)&&(y!=0)&&(x!="0")){
						campoActividadFisica;					
					}
	               
					if(radsiActividad_Fisica==true&&y==0){
						alert("Coloque cuantas horas realiza actividad fisica");
						document.getElementById("campoActividadFisica").focus();
				}else{	
					if(radnoActividad_Fisica==true){
						campoActividadFisica="No";	
					}
					

						if (vivPropiedad == "Seleccione"){
							alert("Escoja una opcion de propiedad");
							document.getElementById("vivPropiedad").focus();
						}else{
							
					
					//Radio Gas
					var radGas = "";
					var radioGas = document.getElementsByName("radGas");
						for (var x = 0; x < radioGas.length; x ++) {
							if (radioGas[x].checked) {
								radGas = radioGas[x].id;
							}                               
						}
						
						if (radGas == false){
							alert("Seleccione si tiene el servicio de gas o no");
						}else{
									
					//Radio Luz	
					var radLuz = "";
					var radioLuz = document.getElementsByName("radLuz");
						for (var x = 0; x < radioLuz.length; x ++) {
							if (radioLuz[x].checked) {
								radLuz = radioLuz[x].id;
							}                               
						}
						
						if (radLuz == false){
							alert("Seleccione si tiene el servicio de luz o no");
						}else{
					
					//Radio Aseo
					var radAseo = "";
					var radioAseo = document.getElementsByName("radAseo");
						for (var x = 0; x < radioAseo.length; x ++) {
							if (radioAseo[x].checked) {
								radAseo = radioAseo[x].id;
							}                               
						}
						
						if (radAseo == false){
							alert("Seleccione si tiene el servicio de aseo o no");
						}else{
									
					//Radio HabitacionCompartida
					var radHabitacionCompartida = "";
					var radioHabitacionCompartida = document.getElementsByName("radHabitacionCompartida");
						for (var x = 0; x < radioHabitacionCompartida.length; x ++) {
							if (radioHabitacionCompartida[x].checked) {
								radHabitacionCompartida = radioHabitacionCompartida[x].id;
							}                               
						}
						
						if (radHabitacionCompartida == false){
							alert("Seleccione si comparte habitacion o no");
						}else{
						
					//Radio Alcantarillado
					var radAlcantarillado = "";
					var radioAlcantarillado = document.getElementsByName("radAlcantarillado");
						for (var x = 0; x < radioAlcantarillado.length; x ++) {
							if (radioAlcantarillado[x].checked) {
								radAlcantarillado = radioAlcantarillado[x].id;
							}                               
						}
						
						if (radAlcantarillado == false){
							alert("Seleccione si tiene alcantarillado o no");
						}else{
									
					//Radio AnimalesDomesticos
					var radAnimalesDomesticos = "";
					var radioAnimalesDomesticos = document.getElementsByName("radAnimalesDomesticos");
						for (var x = 0; x < radioAnimalesDomesticos.length; x ++) {
							if (radioAnimalesDomesticos[x].checked) {
								radAnimalesDomesticos = radioAnimalesDomesticos[x].id;
							}                               
						}
						
						if (radAnimalesDomesticos == false){
							alert("Seleccione si tiene animales domesticos o no");
						}else{		
					
					//Radio Agua
					var radAgua = "";
					var radioAgua = document.getElementsByName("radAgua");
						for (var x = 0; x < radioAgua.length; x ++) {
							if (radioAgua[x].checked) {
								radAgua = radioAgua[x].id;
							}                               
						}
						
						if (radAgua == false){
							alert("Seleccione si tiene el servicio de agua o no");
						}else{
										
					//Radio RiesgoPareja
					var radRiesgoPareja = "";
					var radioRiesgoPareja = document.getElementsByName("radRiesgoPareja");
						for (var x = 0; x < radioRiesgoPareja.length; x ++) {
							if (radioRiesgoPareja[x].checked) {
								radRiesgoPareja = radioRiesgoPareja[x].id;
							}                               
						}
						
						if (radRiesgoPareja == false){
							alert("Seleccione si se siente en riesgo o no");
						}else{
											
					//Radio MaltratoActual
					var radMaltratoActual = "";
					var radioMaltratoActual = document.getElementsByName("radMaltratoActual");
						for (var x = 0; x < radioMaltratoActual.length; x ++) {
							if (radioMaltratoActual[x].checked) {
								radMaltratoActual = radioMaltratoActual[x].id;
							}                               
						}
						
						if (radMaltratoActual == false){
							alert("Seleccione si esta siendo maltratado actualmente o no");
						}else{
										
					//Radio ActosViolencia
					var radActosViolencia = "";
					var radioActosViolencia = document.getElementsByName("radActosViolencia");
						for(var x = 0; x < radioActosViolencia.length; x ++){
							if(radioActosViolencia[x].checked){
								radActosViolencia = radioActosViolencia[x].id;
							}                               
						}
						
						if (radActosViolencia == false){
							alert("Seleccione si se han presentado actos de violencia o no");
						}else{
										
					//Radio Satisfaccion
					var radSatisfaccion = "";
					var radioSatisfaccion = document.getElementsByName("radSatisfaccion");
						for (var x = 0; x < radioSatisfaccion.length; x ++) {
							if(radioSatisfaccion[x].checked) {
								radSatisfaccion=radioSatisfaccion[x].id;
							}                               
						}
						if (radSatisfaccion == false){
							alert("Seleccione si se siente satisfecho o no");
						}else{			
						
			
			ajax=getXMLObject();
			ajax.open("POST","pypData",true);

			ajax.onreadystatechange=function() {
				if (ajax.readyState==4) {
					document.getElementById("txtCodReporte").value=ajax.responseText;
					activarelementoFormulario("BotonAntecedentes");
					alert("Ingreso Exitoso");
				}
			};
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=1&nivEstudio=" + nivEstudio
					+ "&codpaciente=" + codpacientediagnostico
					+ "&codusuario=" +  recopilarinfotextbox("txtUsuario")
					+ "&chk_convivencia=" + chk_convivencia
					+ "&numPadres=" + numPadres
					+ "&numHijos=" + numHijos
					+ "&numNietos=" + numNietos				
					+ "&numOtros=" + numOtros
					+ "&actLaboral=" + actLaboral
					+ "&campoFuma=" + campoFuma
					+ "&campoAlcohol=" + campoAlcohol
					+ "&campoDrogas=" + campoDrogas 
					+ "&campoActividadFisica=" + campoActividadFisica
					+ "&vivPropiedad=" + vivPropiedad
					+ "&radGas=" + radGas
					+ "&radLuz=" + radLuz
					+ "&radAseo=" + radAseo
					+ "&radHabitacionCompartida=" + radHabitacionCompartida
					+ "&radAlcantarillado=" + radAlcantarillado
					+ "&radAnimalesDomesticos=" + radAnimalesDomesticos
					+ "&radAgua=" + radAgua
					+ "&radRiesgoPareja=" + radRiesgoPareja
					+ "&radMaltratoActual=" + radMaltratoActual
					+ "&radActosViolencia=" + radActosViolencia
					+ "&radSatisfaccion=" + radSatisfaccion); //Posting txtname to Servlet*/														
						
						}//Cierra
						}//Cierra
					}//Cierra
					}//Cierra
				}//Cierra
					}//Cierra
					}//Cierra
				}//Cierra
					}//Cierra
					
				}//Cierra
					
					}//Cierra			
				}//Finaliza vivPropiedad
						
				}//Cierra NoAceptaCero
				}//Cierra CantidadActividadFisica
				}//Cierra RadioActividadFisica
			
				}//Cierra NoAceptaCero
				}//Cierra CantidadDrogas
				}//Cierra RadioDrogas
			
				}//Cierra NoAceptaCero
				}//Cierra CantidadAlcohol
				}//Cierra RadioAlcohol
			
				}//Cierra NoAceptaCero
				}//Cierra CantidadFuma
				}//Cierra RadioFuma
			
				}//Cierra ActividadLaboral
			
				} //Cierra NoAceptaCero
				} //Cierra CantidadOtros
				} //Cierra RadioOtros
			
			    } //Cierra NoAceptaCero
				} //Cierra CantidadNietos
				} //Cierra RadioNietos
			
				} //Cierra NoAceptaCero
				} //Cierra CantidadHijos	
				} //Cierra RadioHijos
			
				} //Cierra NoAceptaCero
				} //Cierra CantidadPadres
				} //Cierra RadioPadres
			
		} //Cierra Convivencia
	} //Cierra Estudio

}


///////////FUNCIONES ANTECEDENTES//////////////
//Selects
function AparecerSelects(combo){
	aparecerElemento(combo);
}

function DesaparecerSelects(combo){
	desaparecerElemento(combo);
}



//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/*GUARDAR ANTECEDENTES MASCULINO*/
function GuardarAntecedentesMasculino(){
		
	var NumeroDocumento = document.getElementById("txtnumdoc").value;
	var ObsAntFam = "Antecedentes Familiares";
	var ObsAntPer = "Antecedentes Personales";
	var CodReporte=document.getElementById("txtCodReporte").value;
	if(CodReporte!=""){

	//Radio FamHipertensionArterial
	var SelectHipArt = document.getElementById("HipArtCIE").value;
	var SelectHipArtFAMILIA = document.getElementById("HipArtFAMILIA").value;
	
	var radFamHipertensionArterial = "";
	var radioFamHipertensionArterial = document.getElementsByName("radFamHipertensionArterial");	
		for (var x = 0; x < radioFamHipertensionArterial.length; x ++) {
			if (radioFamHipertensionArterial[x].checked) {
				radFamHipertensionArterial = radioFamHipertensionArterial[x].id;
				
				if (radFamHipertensionArterial == "No"){
					SelectHipArt = "";
					SelectHipArtFAMILIA = "0";
				}		
			}	                               
		}
		
		if (radFamHipertensionArterial == false ){
			alert("Seleccione si algun familiar ha tenido hipertension arterial o no");
		}else{
			if (SelectHipArtFAMILIA == ""){
				alert("Escriba que familiar padece el antecedente de Hipertension Arterial");
				document.getElementById("HipArtFAMILIA").focus();
			}else{
			
	
			
	//Radio FamHepatopatias
	var SelectHep = document.getElementById("HepCIE").value;
	var SelectHepFAMILIA = document.getElementById("HepFAMILIA").value;
	
	var radFamHepatopatias = "";
	var radioFamHepatopatias = document.getElementsByName("radFamHepatopatias");	
		for (var x = 0; x < radioFamHepatopatias.length; x ++) {
			if (radioFamHepatopatias[x].checked) {
				radFamHepatopatias = radioFamHepatopatias[x].id;
				
				if (radFamHepatopatias == "No"){
					SelectHep = "";
					SelectHepFAMILIA = "0";
				}
			}                              
		}	
		
		if (radFamHepatopatias == false){
			alert("Seleccione si algun familiar ha tenido hepatopatias o no");
		}else{
			if (SelectHepFAMILIA == ""){
				alert("Escriba que familiar padece el antecedente de Hepatopatias");
				document.getElementById("HepFAMILIA").focus();
			}else{
	
			
	
			
	//Radio FamDiabetes
	var SelectDia = document.getElementById("DiaCIE").value;
	var SelectDiaFAMILIA = document.getElementById("DiaFAMILIA").value;
	
	var radFamDiabetes = "";
	var radioFamDiabetes = document.getElementsByName("radFamDiabetes");	
		for (var x = 0; x < radioFamDiabetes.length; x ++) {
			if (radioFamDiabetes[x].checked) {
				radFamDiabetes = radioFamDiabetes[x].id;
				
				if (radFamDiabetes == "No"){
					SelectDia = "";
					SelectDiaFAMILIA = "0";
				}
			}                               
		}	
		
		if (radFamDiabetes == false){
			alert("Seleccione si algun familiar ha tenido diabetes o no");
		}else{
			if (SelectDiaFAMILIA == ""){
				alert("Escriba que familiar padece el antecedente de Diabetes");
				document.getElementById("DiaFAMILIA").focus();
			}else{
		
	
			
	//Radio FamTumores
	var SelectTum = document.getElementById("TumCIE").value;
	var SelectTumFAMILIA = document.getElementById("TumFAMILIA").value;
	
	var radFamTumores = "";
	var radioFamTumores = document.getElementsByName("radFamTumores");	
		for (var x = 0; x < radioFamTumores.length; x ++) {
			if (radioFamTumores[x].checked) {
				radFamTumores = radioFamTumores[x].id;
				
				if (radFamTumores == "No"){
					SelectTum = "";
					SelectTumFAMILIA = "0";
				}
			}	                               
		}	
		
		if (radFamTumores == false){
			alert("Seleccione si algun familiar ha tenido tumores o no");
		}else{
			if (SelectTumFAMILIA == ""){
				alert("Escriba que familiar padece el antecedente de Tumores");
				document.getElementById("TumFAMILIA").focus();
			}else{
			
	
			
	//Radio FamCardiopatias
	var SelectCar = document.getElementById("CarCIE").value;
	var SelectCarFAMILIA = document.getElementById("CarFAMILIA").value;
	
	var radFamCardiopatias = "";
	var radioFamCardiopatias = document.getElementsByName("radFamCardiopatias");	
		for (var x = 0; x < radioFamCardiopatias.length; x ++) {
			if (radioFamCardiopatias[x].checked) {
				radFamCardiopatias = radioFamCardiopatias[x].id;
				
				if (radFamCardiopatias == "No"){
					SelectCar = "";
					SelectCarFAMILIA = "0";
				}
			}	                               
		}
		
		if (radFamCardiopatias == false){
			alert("Seleccione si algun familiar ha tenido cardiopatias o no");
		}else{
			if (SelectCarFAMILIA == ""){
				alert("Escriba que familiar padece el antecedente de Cardiopatias");
				document.getElementById("CarFAMILIA").focus();
			}else{
		

			
	//Radio FamMentales
	var SelectMen = document.getElementById("MenCIE").value;
	var SelectMenFAMILIA = document.getElementById("MenFAMILIA").value;

	var radFamMentales = "";
	var radioFamMentales = document.getElementsByName("radFamMentales");	
		for (var x = 0; x < radioFamMentales.length; x ++) {
			if (radioFamMentales[x].checked) {
				radFamMentales = radioFamMentales[x].id;
				
				if (radFamMentales == "No"){
					SelectMen = "";
					SelectMenFAMILIA = "0";
				}
			}                               
		}
		
		if (radFamMentales == false){
			alert("Seleccione si algun familiar ha tenido problemas mentales o no");
		}else{
			if (SelectMenFAMILIA == ""){
				alert("Escriba que familiar padece el antecedente de Mentales");
				document.getElementById("MenFAMILIA").focus();
			}else{
			
	
			
	//Radio PerHipertensionArterial		
	var SelectHipArtPer = document.getElementById("HipArtPerCIE").value;
	
	var radPerHipertension = "";
	var radioPerHipertension = document.getElementsByName("radPerHipertension");	
		for (var x = 0; x < radioPerHipertension.length; x ++) {
			if (radioPerHipertension[x].checked) {
				radPerHipertension = radioPerHipertension[x].id;
				
				if (radPerHipertension == "No"){
					SelectHipArtPer = "";
				}
			}	                               
		}
		
		if (radPerHipertension == false){
			alert("Seleccione si ha tenido hipertension o no");
		}else{
		
	
			
	//Radio PerMentales
	var SelectMenPer = document.getElementById("MenPerCIE").value;
	
	var radPerMentales = "";
	var radioPerMentales = document.getElementsByName("radPerMentales");	
		for (var x = 0; x < radioPerMentales.length; x ++) {
			if (radioPerMentales[x].checked) {
				radPerMentales = radioPerMentales[x].id;
				
				if (radPerMentales == "No"){
					SelectMenPer = "";
				}
			}                               
		}
		
		if (radPerMentales == false){
			alert("Seleccione si ha tenido problemas mentales o no");
		}else{
		

			
	//Radio PerDiabetes
	var SelectDiaPer = document.getElementById("DiaPerCIE").value;

	var radPerDiabetes = "";
	var radioPerDiabetes = document.getElementsByName("radPerDiabetes");	
		for (var x = 0; x < radioPerDiabetes.length; x ++) {
			if (radioPerDiabetes[x].checked) {
				radPerDiabetes = radioPerDiabetes[x].id;
				
				if (radPerDiabetes  == "No"){
					SelectDiaPer = "";
				}
			}                               
		}
		
		if (radPerDiabetes == false){
			alert("Seleccione si ha tenido diabetes o no");
		}else{

	
			
	//Radio PerInfeccionesPelvicas
	var radPerInfeccionesPelvicas = "";
	var radioPerInfeccionesPelvicas = document.getElementsByName("radPerInfeccionesPelvicas");	
		for (var x = 0; x < radioPerInfeccionesPelvicas.length; x ++) {
			if (radioPerInfeccionesPelvicas[x].checked) {
				radPerInfeccionesPelvicas = radioPerInfeccionesPelvicas[x].id;
			}                               
		}
		
		/*if (radPerInfeccionesPelvicas == false){
			alert("Seleccione si ha tenido infecciones pelvicas o no");
		}else{*/


		
	//Radio PerCardiopatias
	var SelectCarPer = document.getElementById("CarPerCIE").value;

	var radPerCardiopatias = "";
	var radioPerCardiopatias = document.getElementsByName("radPerCardiopatias");	
		for (var x = 0; x < radioPerCardiopatias.length; x ++) {
			if (radioPerCardiopatias[x].checked) {
				radPerCardiopatias = radioPerCardiopatias[x].id;
				
				if (radPerCardiopatias == "No"){
					SelectCarPer = "";
				}
			}	                               
		}
		
		if (radPerCardiopatias == false){
			alert("Seleccione si ha tenido cardiopatias o no");
		}else{
				
	
			
	//Radio PerInfeccionCervical
	var radPerInfeccionCervical = "";
	var radioPerInfeccionCervical = document.getElementsByName("radPerInfeccionCervical");	
		for (var x = 0; x < radioPerInfeccionCervical.length; x ++) {
			if (radioPerInfeccionCervical[x].checked) {
				radPerInfeccionCervical = radioPerInfeccionCervical[x].id;
			}	                               
		}
		
		/*if (radPerInfeccionCervical == false){
			alert("Seleccione si ha tenido infeccion cervical o no");
		}else{*/
				

		
	//Radio PerHepatopatias
	var SelectHepPer = document.getElementById("HepPerCIE").value;

	var radPerHepatopatias = "";
	var radioPerHepatopatias = document.getElementsByName("radPerHepatopatias");	
		for (var x = 0; x < radioPerHepatopatias.length; x ++) {
			if (radioPerHepatopatias[x].checked) {
				radPerHepatopatias = radioPerHepatopatias[x].id;
				
				if (radPerHepatopatias == "No"){
					SelectHepPer = "";
				}
			}	                               
		}
		
		if (radPerHepatopatias == false){
			alert("Seleccione si ha tenido hepatopatias o no");
		}else{

	
			
	//Radio PerFlujoVaginal
	var radPerFlujoVaginal = "";
	var radioPerFlujoVaginal = document.getElementsByName("radPerFlujoVaginal");	
		for (var x = 0; x < radioPerFlujoVaginal.length; x ++) {
			if (radioPerFlujoVaginal[x].checked) {
				radPerFlujoVaginal = radioPerFlujoVaginal[x].id;
			}	                               
		}
		
		/*if (radPerFlujoVaginal == false){
			alert("Seleccione si ha tenido flujo vaginal o no");
		}else{*/
			
	
		
	//Radio PerNefritis
	var SelectNef = document.getElementById("NefCIE").value;
	
	var radPerNefritis = "";
	var radioPerNefritis = document.getElementsByName("radPerNefritis");	
		for (var x = 0; x < radioPerNefritis.length; x ++) {
			if (radioPerNefritis[x].checked) {
				radPerNefritis = radioPerNefritis[x].id;
				
				if (radPerNefritis == "No"){
					SelectNef = "";
				}
			}                               
		}
		
		if (radPerNefritis == false){
			alert("Seleccione si ha tenido o no");
		}else{
				
		
			
	//Radio PerCirugiaGinecologica		
	var radPerCirugiaGinecologica = "";
	var radioPerCirugiaGinecologica = document.getElementsByName("radPerCirugiaGinecologica");	
		for (var x = 0; x < radioPerCirugiaGinecologica.length; x ++) {
			if (radioPerCirugiaGinecologica[x].checked) {
				radPerCirugiaGinecologica = radioPerCirugiaGinecologica[x].id;
			}	                               
		}
		
		/*if (radPerCirugiaGinecologica == false){
			alert("Seleccione si le han realizado cirugia ginecologica o no");
		}else{*/
				

		
	//Radio PerTumores
	var SelectTumPer = document.getElementById("TumPerCIE").value;

	var radPerTumores = "";
	var radioPerTumores = document.getElementsByName("radPerTumores");	
		for (var x = 0; x < radioPerTumores.length; x ++) {
			if (radioPerTumores[x].checked) {
				radPerTumores = radioPerTumores[x].id;
				
				if (radPerTumores == "No"){
					SelectTumPer = "";
				}
			}	                               
		}
		
		if (radPerTumores == false){
			alert("Seleccione si ha tenido tumores o no");
		}else{		
			
			
			
	//Radio PerCitologiasPrevias		
	var radPerCitologiasPrevias = "";
	var radioPerCitologiasPrevias = document.getElementsByName("radPerCitologiasPrevias");	
		for (var x = 0; x < radioPerCitologiasPrevias.length; x ++) {
			if (radioPerCitologiasPrevias[x].checked) {
				radPerCitologiasPrevias = radioPerCitologiasPrevias[x].id;
			}	                               
		}
		
		/*if (radPerCitologiasPrevias == false){
			alert("Seleccione si ha tenido citologias previas o no");
		}else{*/
				

		
	//Radio PerTromboFlebitis
	var SelectTroFle = document.getElementById("TroFleCIE").value;

	var radPerTromboFlebitis = "";
	var radioPerTromboFlebitis = document.getElementsByName("radPerTromboFlebitis");	
		for (var x = 0; x < radioPerTromboFlebitis.length; x ++) {
			if (radioPerTromboFlebitis[x].checked) {
				radPerTromboFlebitis = radioPerTromboFlebitis[x].id;
				
				if (radPerTromboFlebitis == "No"){
					SelectTroFle = "";
				}
			}	                               
		}
		
		if (radPerTromboFlebitis == false){
			alert("Seleccione si ha tenido tromboflebitis o no");
		}else{
				
			
    //Radio PerHabitoFumar
	var radPerHabitoFumar = "";
	var radioPerHabitoFumar = document.getElementsByName("radPerHabitoFumar");	
		for (var x = 0; x < radioPerHabitoFumar.length; x ++) {
			if (radioPerHabitoFumar[x].checked) {
				radPerHabitoFumar = radioPerHabitoFumar[x].id;
			}	                               
		}
		
		if (radPerHabitoFumar == false){
			alert("Seleccione si ha tenido el habito de fumar o no");
		}else{
			
		
			
		ajax=getXMLObject();
		ajax.open("POST","pypData",true);

		ajax.onreadystatechange=function() {
			if (ajax.readyState==4) {
				//document.getElementById('ANTECEDENTES').innerHTML = ajax.responseText;
				alert("Ingreso Exitoso");
			}
		};
		
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=2&radFamHipertensionArterial=" + radFamHipertensionArterial
				+ "&codpaciente=" + codpacientediagnostico
				+ "&ObsAntFam=" + ObsAntFam
				+ "&ObsAntPer=" + ObsAntPer
				+ "&radFamHepatopatias=" + radFamHepatopatias
				+ "&radFamDiabetes=" + radFamDiabetes
				+ "&radFamTumores=" + radFamTumores
				+ "&radFamCardiopatias=" + radFamCardiopatias
				+ "&radFamMentales=" + radFamMentales
				+ "&radPerHipertension=" + radPerHipertension
				+ "&radPerMentales=" + radPerMentales
				+ "&radPerDiabetes=" + radPerDiabetes
				+ "&radPerInfeccionesPelvicas=" + radPerInfeccionesPelvicas
				+ "&radPerCardiopatias=" + radPerCardiopatias
				+ "&radPerInfeccionCervical=" + radPerInfeccionCervical
				+ "&radPerHepatopatias=" + radPerHepatopatias
				+ "&radPerFlujoVaginal=" + radPerFlujoVaginal
				+ "&radPerNefritis=" + radPerNefritis
				+ "&radPerCirugiaGinecologica=" + radPerCirugiaGinecologica
				+ "&radPerTumores=" + radPerTumores
				+ "&radPerCitologiasPrevias=" + radPerCitologiasPrevias
				+ "&radPerTromboFlebitis=" + radPerTromboFlebitis
				+ "&radPerHabitoFumar=" + radPerHabitoFumar
				+ "&txtCodReporte=" + CodReporte
				+ "&SelectHipArt=" + SelectHipArt
				+ "&SelectHipArtFAMILIA=" + SelectHipArtFAMILIA
				+ "&SelectHep=" + SelectHep
				+ "&SelectHepFAMILIA=" + SelectHepFAMILIA
				+ "&SelectDia=" + SelectDia
				+ "&SelectDiaFAMILIA=" + SelectDiaFAMILIA
				+ "&SelectTum=" + SelectTum
				+ "&SelectTumFAMILIA=" + SelectTumFAMILIA
				+ "&SelectCar=" + SelectCar
				+ "&SelectCarFAMILIA=" + SelectCarFAMILIA
				+ "&SelectMen=" + SelectMen
				+ "&SelectMenFAMILIA=" + SelectMenFAMILIA
				+ "&SelectHipArtPer=" + SelectHipArtPer
				+ "&SelectMenPer=" + SelectMenPer
				+ "&SelectDiaPer=" + SelectDiaPer
				+ "&SelectCarPer=" + SelectCarPer
				+ "&SelectHepPer=" + SelectHepPer
				+ "&SelectNef=" + SelectNef
				+ "&SelectTumPer=" + SelectTumPer
				+ "&SelectTroFle=" + SelectTroFle
				+ "&codusuario=" +  recopilarinfotextbox("txtUsuario")
				+ "&NumeroDocumento=" + NumeroDocumento);
		}//
		}//
		//}//Citologias Previas
		}//
		//}//Cirugia Ginecologica
		}//
		//}//Flujo Vaginal
		}//
		//}//Infeccion Cervical
		}//
		//}//Infecciones Pelvicas
		}//
		}//
		}//
		
		}//Miembro Familia Mentales
		}//Radio Mentales Familiar
		
		}//Miembro Familia Cardiopatias
		}//Radio Cardiopatias Familiar
		
		}//Miembro Familia Tumores
		}//Radio Tumores Familiar
		
		}//Miembro Familia Diabetes
		}//Radio Diabetes Familiar
		
		}//Miembro Familia Hepatopatias
		}//Radio Hepatopatias Familiar		
		
		}//Miembro Familia Hipertension Arterial
		}//Radio Hipertension Arterial Familiar
		
	}else{
		alert("Falta Guardar Datos Personales en la pestaña Informacion Usuario");
	}
}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/*GUARDAR ANTECEDENTES FEMENINO*/
////////////////////////////////////////
function GuardarAntecedentesFemenino(){
		
	var NumeroDocumento = document.getElementById("txtnumdoc").value;
	var ObsAntFam = "Antecedentes Familiares";
	var ObsAntPer = "Antecedentes Personales";
	var CodReporte=document.getElementById("txtCodReporte").value;
	if(CodReporte!=""){

	//Radio FamHipertensionArterial
	var SelectHipArt = document.getElementById("HipArtCIE").value;
	var SelectHipArtFAMILIA = document.getElementById("HipArtFAMILIA").value;
	
	var radFamHipertensionArterial = "";
	var radioFamHipertensionArterial = document.getElementsByName("radFamHipertensionArterial");	
		for (var x = 0; x < radioFamHipertensionArterial.length; x ++) {
			if (radioFamHipertensionArterial[x].checked) {
				radFamHipertensionArterial = radioFamHipertensionArterial[x].id;
				
				if (radFamHipertensionArterial == "No"){
					SelectHipArt = "";
					SelectHipArtFAMILIA = "0";
				}
			}	                               
		}
		
		if (radFamHipertensionArterial == false ){
			alert("Seleccione si algun familiar ha tenido hipertension arterial o no");
		}else{
			if (SelectHipArtFAMILIA == ""){
				alert("Escriba que familiar padece el antecedente de Hipertension Arterial");
				document.getElementById("HipArtFAMILIA").focus();
			}else{
			
	
			
	//Radio FamHepatopatias
	var SelectHep = document.getElementById("HepCIE").value;
	var SelectHepFAMILIA = document.getElementById("HepFAMILIA").value;
	
	var radFamHepatopatias = "";
	var radioFamHepatopatias = document.getElementsByName("radFamHepatopatias");	
		for (var x = 0; x < radioFamHepatopatias.length; x ++) {
			if (radioFamHepatopatias[x].checked) {
				radFamHepatopatias = radioFamHepatopatias[x].id;
				
				if (radFamHepatopatias == "No"){
					SelectHep = "";
					SelectHepFAMILIA = "0";
				}
			}                              
		}	
		
		if (radFamHepatopatias == false){
			alert("Seleccione si algun familiar ha tenido hepatopatias o no");
		}else{
			if (SelectHepFAMILIA == ""){
				alert("Escriba que familiar padece el antecedente de Hepatopatias");
				document.getElementById("HepFAMILIA").focus();
			}else{
	
			
	
			
	//Radio FamDiabetes
	var SelectDia = document.getElementById("DiaCIE").value;
	var SelectDiaFAMILIA = document.getElementById("DiaFAMILIA").value;
	
	var radFamDiabetes = "";
	var radioFamDiabetes = document.getElementsByName("radFamDiabetes");	
		for (var x = 0; x < radioFamDiabetes.length; x ++) {
			if (radioFamDiabetes[x].checked) {
				radFamDiabetes = radioFamDiabetes[x].id;
				
				if (radFamDiabetes == "No"){
					SelectDia = "";
					SelectDiaFAMILIA = "0";
				}
			}                               
		}	
		
		if (radFamDiabetes == false){
			alert("Seleccione si algun familiar ha tenido diabetes o no");
		}else{
			if (SelectDiaFAMILIA == ""){
				alert("Escriba que familiar padece el antecedente de Diabetes");
				document.getElementById("DiaFAMILIA").focus();
			}else{
		
	
			
	//Radio FamTumores
	var SelectTum = document.getElementById("TumCIE").value;
	var SelectTumFAMILIA = document.getElementById("TumFAMILIA").value;
	
	var radFamTumores = "";
	var radioFamTumores = document.getElementsByName("radFamTumores");	
		for (var x = 0; x < radioFamTumores.length; x ++) {
			if (radioFamTumores[x].checked) {
				radFamTumores = radioFamTumores[x].id;
				
				if (radFamTumores == "No"){
					SelectTum = "";
					SelectTumFAMILIA = "0";
				}
			}	                               
		}	
		
		if (radFamTumores == false){
			alert("Seleccione si algun familiar ha tenido tumores o no");
		}else{
			if (SelectTumFAMILIA == ""){
				alert("Escriba que familiar padece el antecedente de Tumores");
				document.getElementById("TumFAMILIA").focus();
			}else{
			
	
			
	//Radio FamCardiopatias
	var SelectCar = document.getElementById("CarCIE").value;
	var SelectCarFAMILIA = document.getElementById("CarFAMILIA").value;
	
	var radFamCardiopatias = "";
	var radioFamCardiopatias = document.getElementsByName("radFamCardiopatias");	
		for (var x = 0; x < radioFamCardiopatias.length; x ++) {
			if (radioFamCardiopatias[x].checked) {
				radFamCardiopatias = radioFamCardiopatias[x].id;
				
				if (radFamCardiopatias == "No"){
					SelectCar = "";
					SelectCarFAMILIA = "0";
				}
			}	                               
		}
		
		if (radFamCardiopatias == false){
			alert("Seleccione si algun familiar ha tenido cardiopatias o no");
		}else{
			if (SelectCarFAMILIA == ""){
				alert("Escriba que familiar padece el antecedente de Cardiopatias");
				document.getElementById("CarFAMILIA").focus();
			}else{
		

			
	//Radio FamMentales
	var SelectMen = document.getElementById("MenCIE").value;
	var SelectMenFAMILIA = document.getElementById("MenFAMILIA").value;

	var radFamMentales = "";
	var radioFamMentales = document.getElementsByName("radFamMentales");	
		for (var x = 0; x < radioFamMentales.length; x ++) {
			if (radioFamMentales[x].checked) {
				radFamMentales = radioFamMentales[x].id;
				
				if (radFamMentales == "No"){
					SelectMen = "";
					SelectMenFAMILIA = "0";
				}
			}                               
		}
		
		if (radFamMentales == false){
			alert("Seleccione si algun familiar ha tenido problemas mentales o no");
		}else{
			if (SelectMenFAMILIA == ""){
				alert("Escriba que familiar padece el antecedente de Mentales");
				document.getElementById("MenFAMILIA").focus();
			}else{
			
	
			
	//Radio PerHipertensionArterial		
	var SelectHipArtPer = document.getElementById("HipArtPerCIE").value;
	
	var radPerHipertension = "";
	var radioPerHipertension = document.getElementsByName("radPerHipertension");	
		for (var x = 0; x < radioPerHipertension.length; x ++) {
			if (radioPerHipertension[x].checked) {
				radPerHipertension = radioPerHipertension[x].id;
				
				if (radPerHipertension == "No"){
					SelectHipArtPer = "";
				}
			}	                               
		}
		
		if (radPerHipertension == false){
			alert("Seleccione si ha tenido hipertension o no");
		}else{
		
	
			
	//Radio PerMentales
	var SelectMenPer = document.getElementById("MenPerCIE").value;
	
	var radPerMentales = "";
	var radioPerMentales = document.getElementsByName("radPerMentales");	
		for (var x = 0; x < radioPerMentales.length; x ++) {
			if (radioPerMentales[x].checked) {
				radPerMentales = radioPerMentales[x].id;
				
				if (radPerMentales == "No"){
					SelectMenPer = "";
				}
			}                               
		}
		
		if (radPerMentales == false){
			alert("Seleccione si ha tenido problemas mentales o no");
		}else{
		

			
	//Radio PerDiabetes
	var SelectDiaPer = document.getElementById("DiaPerCIE").value;

	var radPerDiabetes = "";
	var radioPerDiabetes = document.getElementsByName("radPerDiabetes");	
		for (var x = 0; x < radioPerDiabetes.length; x ++) {
			if (radioPerDiabetes[x].checked) {
				radPerDiabetes = radioPerDiabetes[x].id;
				
				if (radPerDiabetes  == "No"){
					SelectDiaPer = "";
				}
			}                               
		}
		
		if (radPerDiabetes == false){
			alert("Seleccione si ha tenido diabetes o no");
		}else{

	
			
	//Radio PerInfeccionesPelvicas
	var SelectInfPel = document.getElementById("InfPelCIE").value;

	var radPerInfeccionesPelvicas = "";
	var radioPerInfeccionesPelvicas = document.getElementsByName("radPerInfeccionesPelvicas");	
		for (var x = 0; x < radioPerInfeccionesPelvicas.length; x ++) {
			if (radioPerInfeccionesPelvicas[x].checked) {
				radPerInfeccionesPelvicas = radioPerInfeccionesPelvicas[x].id;
				
				if (radPerInfeccionesPelvicas == "No"){
					SelectInfPel = "";
				}
			}                               
		}
		
		if (radPerInfeccionesPelvicas == false){
			alert("Seleccione si ha tenido infecciones pelvicas o no");
		}else{

		
	//Radio PerCardiopatias
	var SelectCarPer = document.getElementById("CarPerCIE").value;

	var radPerCardiopatias = "";
	var radioPerCardiopatias = document.getElementsByName("radPerCardiopatias");	
		for (var x = 0; x < radioPerCardiopatias.length; x ++) {
			if (radioPerCardiopatias[x].checked) {
				radPerCardiopatias = radioPerCardiopatias[x].id;
				
				if (radPerCardiopatias == "No"){
					SelectCarPer = "";
				}
			}	                               
		}
		
		if (radPerCardiopatias == false){
			alert("Seleccione si ha tenido cardiopatias o no");
		}else{
				
	
			
	//Radio PerInfeccionCervical
	var SelectInfCer = document.getElementById("InfCerCIE").value;

	var radPerInfeccionCervical = "";
	var radioPerInfeccionCervical = document.getElementsByName("radPerInfeccionCervical");	
		for (var x = 0; x < radioPerInfeccionCervical.length; x ++) {
			if (radioPerInfeccionCervical[x].checked) {
				radPerInfeccionCervical = radioPerInfeccionCervical[x].id;
				
				if (radPerInfeccionCervical == "No"){
					SelectInfCer = "";
				}
			}	                               
		}
		
		if (radPerInfeccionCervical == false){
			alert("Seleccione si ha tenido infeccion cervical o no");
		}else{
				

		
	//Radio PerHepatopatias
	var SelectHepPer = document.getElementById("HepPerCIE").value;

	var radPerHepatopatias = "";
	var radioPerHepatopatias = document.getElementsByName("radPerHepatopatias");	
		for (var x = 0; x < radioPerHepatopatias.length; x ++) {
			if (radioPerHepatopatias[x].checked) {
				radPerHepatopatias = radioPerHepatopatias[x].id;
				
				if (radPerHepatopatias == "No"){
					SelectHepPer = "";
				}
			}	                               
		}
		
		if (radPerHepatopatias == false){
			alert("Seleccione si ha tenido hepatopatias o no");
		}else{

	
			
	//Radio PerFlujoVaginal
	var SelectFluVag = document.getElementById("FluVagCIE").value;

	var radPerFlujoVaginal = "";
	var radioPerFlujoVaginal = document.getElementsByName("radPerFlujoVaginal");	
		for (var x = 0; x < radioPerFlujoVaginal.length; x ++) {
			if (radioPerFlujoVaginal[x].checked) {
				radPerFlujoVaginal = radioPerFlujoVaginal[x].id;
				
				if (radPerFlujoVaginal == "No"){
					SelectFluVag = "";
				}
			}	                               
		}
		
		if (radPerFlujoVaginal == false){
			alert("Seleccione si ha tenido flujo vaginal o no");
		}else{
			
	
		
	//Radio PerNefritis
	var SelectNef = document.getElementById("NefCIE").value;
	
	var radPerNefritis = "";
	var radioPerNefritis = document.getElementsByName("radPerNefritis");	
		for (var x = 0; x < radioPerNefritis.length; x ++) {
			if (radioPerNefritis[x].checked) {
				radPerNefritis = radioPerNefritis[x].id;
				
				if (radPerNefritis == "No"){
					SelectNef = "";
				}
			}                               
		}
		
		if (radPerNefritis == false){
			alert("Seleccione si ha tenido o no");
		}else{
				
		
			
	//Radio PerCirugiaGinecologica		
	var radPerCirugiaGinecologica = "";
	var radioPerCirugiaGinecologica = document.getElementsByName("radPerCirugiaGinecologica");	
		for (var x = 0; x < radioPerCirugiaGinecologica.length; x ++) {
			if (radioPerCirugiaGinecologica[x].checked) {
				radPerCirugiaGinecologica = radioPerCirugiaGinecologica[x].id;
			}	                               
		}
		
		if (radPerCirugiaGinecologica == false){
			alert("Seleccione si le han realizado cirugia ginecologica o no");
		}else{
				

		
	//Radio PerTumores
	var SelectTumPer = document.getElementById("TumPerCIE").value;

	var radPerTumores = "";
	var radioPerTumores = document.getElementsByName("radPerTumores");	
		for (var x = 0; x < radioPerTumores.length; x ++) {
			if (radioPerTumores[x].checked) {
				radPerTumores = radioPerTumores[x].id;
				
				if (radPerTumores == "No"){
					SelectTumPer = "";
				}
			}	                               
		}
		
		if (radPerTumores == false){
			alert("Seleccione si ha tenido tumores o no");
		}else{		
			
			
			
	//Radio PerCitologiasPrevias		
	var radPerCitologiasPrevias = "";
	var radioPerCitologiasPrevias = document.getElementsByName("radPerCitologiasPrevias");	
		for (var x = 0; x < radioPerCitologiasPrevias.length; x ++) {
			if (radioPerCitologiasPrevias[x].checked) {
				radPerCitologiasPrevias = radioPerCitologiasPrevias[x].id;
			}	                               
		}
		
		if (radPerCitologiasPrevias == false){
			alert("Seleccione si ha tenido citologias previas o no");
		}else{
				
	
		
	//Radio PerTromboFlebitis
	var SelectTroFle = document.getElementById("TroFleCIE").value;
		
	var radPerTromboFlebitis = "";
	var radioPerTromboFlebitis = document.getElementsByName("radPerTromboFlebitis");	
		for (var x = 0; x < radioPerTromboFlebitis.length; x ++) {
			if (radioPerTromboFlebitis[x].checked) {
				radPerTromboFlebitis = radioPerTromboFlebitis[x].id;
				
				if (radPerTromboFlebitis == "No"){
					SelectTroFle = "";
				}
			}	                               
		}
		
		if (radPerTromboFlebitis == false){
			alert("Seleccione si ha tenido tromboflebitis o no");
		}else{
				
			
    //Radio PerHabitoFumar
	var radPerHabitoFumar = "";
	var radioPerHabitoFumar = document.getElementsByName("radPerHabitoFumar");	
		for (var x = 0; x < radioPerHabitoFumar.length; x ++) {
			if (radioPerHabitoFumar[x].checked) {
				radPerHabitoFumar = radioPerHabitoFumar[x].id;
			}	                               
		}
		
		if (radPerHabitoFumar == false){
			alert("Seleccione si ha tenido el habito de fumar o no");
		}else{
			
		
			
		ajax=getXMLObject();
		ajax.open("POST","pypData",true);

		ajax.onreadystatechange=function() {
			if (ajax.readyState==4) {
				//document.getElementById('ANTECEDENTES').innerHTML = ajax.responseText;
				alert("Ingreso Exitoso");
			}
		};
		
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=2&radFamHipertensionArterial=" + radFamHipertensionArterial
				+ "&codpaciente=" + codpacientediagnostico
				+ "&ObsAntFam=" + ObsAntFam
				+ "&ObsAntPer=" + ObsAntPer
				+ "&radFamHepatopatias=" + radFamHepatopatias
				+ "&radFamDiabetes=" + radFamDiabetes
				+ "&radFamTumores=" + radFamTumores
				+ "&radFamCardiopatias=" + radFamCardiopatias
				+ "&radFamMentales=" + radFamMentales
				+ "&radPerHipertension=" + radPerHipertension
				+ "&radPerMentales=" + radPerMentales
				+ "&radPerDiabetes=" + radPerDiabetes
				+ "&radPerInfeccionesPelvicas=" + radPerInfeccionesPelvicas
				+ "&radPerCardiopatias=" + radPerCardiopatias
				+ "&radPerInfeccionCervical=" + radPerInfeccionCervical
				+ "&radPerHepatopatias=" + radPerHepatopatias
				+ "&radPerFlujoVaginal=" + radPerFlujoVaginal
				+ "&radPerNefritis=" + radPerNefritis
				+ "&radPerCirugiaGinecologica=" + radPerCirugiaGinecologica
				+ "&radPerTumores=" + radPerTumores
				+ "&radPerCitologiasPrevias=" + radPerCitologiasPrevias
				+ "&radPerTromboFlebitis=" + radPerTromboFlebitis
				+ "&radPerHabitoFumar=" + radPerHabitoFumar
				+ "&txtCodReporte=" + CodReporte
				+ "&SelectHipArt=" + SelectHipArt
				+ "&SelectHipArtFAMILIA=" + SelectHipArtFAMILIA
				+ "&SelectHep=" + SelectHep
				+ "&SelectHepFAMILIA=" + SelectHepFAMILIA
				+ "&SelectDia=" + SelectDia
				+ "&SelectDiaFAMILIA=" + SelectDiaFAMILIA
				+ "&SelectTum=" + SelectTum
				+ "&SelectTumFAMILIA=" + SelectTumFAMILIA
				+ "&SelectCar=" + SelectCar
				+ "&SelectCarFAMILIA=" + SelectCarFAMILIA
				+ "&SelectMen=" + SelectMen
				+ "&SelectMenFAMILIA=" + SelectMenFAMILIA
				+ "&SelectHipArtPer=" + SelectHipArtPer
				+ "&SelectMenPer=" + SelectMenPer
				+ "&SelectDiaPer=" + SelectDiaPer
				+ "&SelectInfPel=" + SelectInfPel
				+ "&SelectCarPer=" + SelectCarPer
				+ "&SelectInfCer=" + SelectInfCer
				+ "&SelectHepPer=" + SelectHepPer
				+ "&SelectFluVag=" + SelectFluVag
				+ "&SelectNef=" + SelectNef	
				+ "&SelectTumPer=" + SelectTumPer	
				+ "&SelectTroFle=" + SelectTroFle
				+ "&codusuario=" +  recopilarinfotextbox("txtUsuario")
				+ "&NumeroDocumento=" + NumeroDocumento);
		}//
		}//
		}//Citologias Previas
		}//
		}//Cirugia Ginecologica
		}//
		}//Flujo Vaginal
		}//
		}//Infeccion Cervical
		}//
		}//Infecciones Pelvicas
		}//
		}//
		}//
		
		}//Miembro Familia Hipertension Arterial
		}//Radio Hipertension Arterial Familiar
		
		}//Miembro Familia Hepatopatias
		}//Radio Hepatopatias Familiar
		
		}//Miembro Familia Diabetes
		}//Radio Diabetes Familiar
		
		}//Miembro Familia Tumores
		}//Radio Tumores Familiar
		
		}//Miembro Familia Cardiopatias
		}//Radio Cardiopatias Familiar		
		
		}//Miembro Familia Mentales
		}//Radio Mentales Familiar
		
	}else{
		alert("Falta Guardar Datos Personales en la pestaña Informacion Usuario");
	}
}


/*
//Validacion Mostrar Historias Conceptuales
function oculta(id){
	var elDiv = document.getElementById(id); //se define la variable "elDiv" igual a nuestro div
		elDiv.style.display='none'; //damos un atributo display:none que oculta el div     
}
	  
function muestra(id){
  var elDiv = document.getElementById(id); //se define la variable "elDiv" igual a nuestro div
      elDiv.style.display='block';//damos un atributo display:block que  el div     
}


window.onload = function(){/*hace que se cargue la función */
	   /* "Mandamos como parametro el nombre de la Div para ocultar" */
	   /*oculta('divHistConc'); /*Ocultamos divHistConc*/
/*};*/

/*VALIDACIONES Y FUNCION GUARDAR HISTORIAS*/

function clicksiEmbarazosAntesConsulta(){
	document.getElementById("datepickerTerminacionEmbarazo").disabled=false;
	document.getElementById("mesesGestacion").disabled=false;
	document.getElementById("tipoParto").disabled=false;
	document.getElementById("aborto").disabled=false;
	document.getElementById("otrosAntecedentes").disabled=false;
	document.getElementById("datepickerTerminacionEmbarazo").focus();
}

function AsignarNAEmbarazos(){
	
	var selEmbarazos = document.getElementById("EmbarazoAntes").value;
	
	if (selEmbarazos == "No") {
		document.getElementById("datepickerTerminacionEmbarazo").value="N/A";
		document.getElementById("datepickerTerminacionEmbarazo").disabled=true;
		document.getElementById("mesesGestacion").value="N/A";
		document.getElementById("mesesGestacion").disabled=true;
		document.getElementById("tipoParto").value="N/A";
		document.getElementById("tipoParto").disabled=true;
		document.getElementById("aborto").value="N/A";
		document.getElementById("aborto").disabled=true;
		document.getElementById("otrosAntecedentes").value="N/A";
		document.getElementById("otrosAntecedentes").disabled=true;
	}else {	
		if(selEmbarazos == "Si"){
			document.getElementById("datepickerTerminacionEmbarazo").disabled=false;
			document.getElementById("datepickerTerminacionEmbarazo").focus();
			document.getElementById("datepickerTerminacionEmbarazo").value="";
			document.getElementById("mesesGestacion").disabled=false;
			document.getElementById("mesesGestacion").value="";
			document.getElementById("tipoParto").disabled=false;
			document.getElementById("tipoParto").value="Seleccione";
			document.getElementById("aborto").disabled=false;
			document.getElementById("aborto").value="Seleccione";
			document.getElementById("otrosAntecedentes").disabled=true;
			document.getElementById("otrosAntecedentes").value="";
		}
	}
}

//Validacion Trastornos Menstruales
function clicksiTrasMenstruales(){
	document.getElementById("tipoTrasMenstruales").disabled=false;
	document.getElementById("tipoTrasMenstruales").focus();
}

function clicksiMetodosAntesConsulta(){
	document.getElementById("txt_metUtilizado").disabled=false;
	document.getElementById("txt_desde").disabled=false;
	document.getElementById("txt_hasta").disabled=false;
	document.getElementById("preescrito").disabled=false;
	document.getElementById("txt_preescrito").disabled=false;
	document.getElementById("txt_problemas").disabled=false;
	document.getElementById("txt_metUtilizado").focus();
	document.getElementById("pildoras").disabled=false;
	document.getElementById("cualPildoras").disabled=false;
	document.getElementById("datepickerPildoras").disabled=false;
	document.getElementById("diu").disabled=false;
	document.getElementById("cualDIU").disabled=false;
	document.getElementById("datepickerDIU").disabled=false;
	document.getElementById("inyectables").disabled=false;
	document.getElementById("cualInyectables").disabled=false;
	document.getElementById("datepickerInyectables").disabled=false;
	document.getElementById("metOtros").disabled=false;
	document.getElementById("cualOtros").disabled=false;
	document.getElementById("datepickerOtros").disabled=false;
}

function clicknoMetodosAntesConsulta(){
	document.getElementById("pildoras").disabled=true;
	document.getElementById("cualPildoras").disabled=true;
	document.getElementById("datepickerPildoras").disabled=true;
	document.getElementById("diu").disabled=true;
	document.getElementById("cualDIU").disabled=true;
	document.getElementById("datepickerDIU").disabled=true;
	document.getElementById("inyectables").disabled=true;
	document.getElementById("cualInyectables").disabled=true;
	document.getElementById("datepickerInyectables").disabled=true;
	document.getElementById("metOtros").disabled=true;
	document.getElementById("cualOtros").disabled=true;
	document.getElementById("datepickerOtros").disabled=true;
}


//Validacion del select Preescrito
function selectEntidad(){
	
	var selpreescrito = document.getElementById("preescrito").value;
	
	if (selpreescrito == "Ninguno") {
		document.getElementById("txt_preescrito").value="N/A";
		document.getElementById("txt_preescrito").disabled=true;
	}
	else {	
		if(selpreescrito == "Seleccione"){
			document.getElementById("txt_preescrito").disabled=true;
			document.getElementById("txt_preescrito").value="";
		}else{
			document.getElementById("txt_preescrito").disabled=false;
			document.getElementById("txt_preescrito").focus();
			document.getElementById("txt_preescrito").value="";
		}
	}
}

///////////////////////////////////////

function GuardarHistorias(){	
	var sw=0;
	var EmbarazoAntes = document.getElementById("EmbarazoAntes").value;
	var terminaEmbarazo = document.getElementById("datepickerTerminacionEmbarazo").value;
	var mesesGestacion = document.getElementById("mesesGestacion").value;
	var tipoParto = document.getElementById("tipoParto").value;
	var abortos = document.getElementById("aborto").value;
	var otrosAntecedentes = document.getElementById("otrosAntecedentes").value;	
	var ultimaMenstruacion = document.getElementById("datepickerUltimaMenstruacion").value;	
	var radsiTrasMenstruales = document.getElementById("siTrasMenstruales").checked;
	var radnoTrasMenstruales = document.getElementById("noTrasMenstruales").checked;
	var tipoTrasMenstruales = document.getElementById("tipoTrasMenstruales").value;

	var metodoUtilizado = document.getElementById("txt_metUtilizado").value;
	var tiempoUtilizacionDesde = document.getElementById("txt_desde").value;
	var tiempoUtilizacionHasta = document.getElementById("txt_hasta").value;
	var selpreescrito = document.getElementById("preescrito").value;
	var preescrito = document.getElementById("txt_preescrito").value;
	var problemas = document.getElementById("txt_problemas").value;
	var CodReporte=document.getElementById("txtCodReporte").value;
	if(CodReporte!=""){
	
	
		if(EmbarazoAntes == "Seleccione"){
			alert("Seleccione si ha tenido embarazos antes de la consulta");
			document.getElementById("EmbarazoAntes").focus();
		}else{
		
		if(terminaEmbarazo == ""){
		   alert("Seleccionar la terminacion del embarazo");
		   document.getElementById("datepickerTerminacionEmbarazo").focus();
	}else{
		if (mesesGestacion == "") {
				alert("Escriba los meses de gestacion");
				document.getElementById("mesesGestacion").focus();
		}else{
			if (tipoParto == "Seleccione") {
					alert("Seleccione un tipo de parto");
					document.getElementById("tipoParto").focus();
			}else{
				if (abortos == "Seleccione") {
						alert("Seleccione si hubo aborto o no");
						document.getElementById("aborto").focus();
				}else{
					if (otrosAntecedentes == "") {
							alert("Escriba si tiene otros antecedentes, de lo contrario escriba ninguno");
							document.getElementById("otrosAntecedentes").focus();
					}else{
						
						//Radio CiclosRegulares
						var radCiclosRegulares = "";
						var radioCiclosRegulares = document.getElementsByName("radCiclosRegulares");	
							for (var x = 0; x < radioCiclosRegulares.length; x ++) {
								if (radioCiclosRegulares[x].checked) {
									radCiclosRegulares = radioCiclosRegulares[x].id;
								}	                               
							}
						
						if (radCiclosRegulares == false){
							alert("Seleccione si tiene ciclos regulares o no");
						}else{
						
							
						if(ultimaMenstruacion == ""){
							alert("Seleccionar la fecha de la ultima menstruacion");
						   document.getElementById("datepickerUltimaMenstruacion").focus();
						}else{
							
							
							/*Este Comentario No Valida Nada
							if (radsiTrasMenstruales == false && radnoTrasMenstruales == false){
								alert("Seleccione un radio para Trastornos Menstruales");
							}else {
								if(radsiTrasMenstruales == true)
									tipoTrasMenstruales;
									
								if(radnoTrasMenstruales == true)
									tipoTrasMenstruales = "0";*/					
							
							
			//Validacion Radio Trastornos Menstruales
			if (radsiTrasMenstruales==false && radnoTrasMenstruales==false){
					alert("Seleccione si tiene trastornos menstruales o no");
			}else{
					var x=document.getElementById("tipoTrasMenstruales").value;
					var y=x.length;
              
				if((radsiTrasMenstruales==true)&&(y!=0)&&(x=="0")){
					alert("No Acepta Cero");					
			}else{
				if((radsiTrasMenstruales==true)&&(y!=0)&&(x!="0")){
					tipoTrasMenstruales;					
				}
               
				if(radsiTrasMenstruales==true&&y==0){
					alert("Coloque el tipo de trastorno menstrual");
					document.getElementById("tipoTrasMenstruales").focus();
			}else{	
				if(radnoTrasMenstruales==true){
					tipoTrasMenstruales="No";
				} 
				
								  
				//Radio MetodoAnticonceptivo
				var radMetodoAnticonceptivo = "";
				var radioMetodoAnticonceptivo = document.getElementsByName("radMetodoAnticonceptivo");	
					for (var x = 0; x < radioMetodoAnticonceptivo.length; x ++) {
						if (radioMetodoAnticonceptivo[x].checked) {
							radMetodoAnticonceptivo = radioMetodoAnticonceptivo[x].id;
						}                              
					}
	
					if (radMetodoAnticonceptivo == false){
						alert("Seleccione si ha usado algun metodo anticonceptivo antes de la consulta");
					}else{
					
						
						if(radMetodoAnticonceptivo == "Si"){						
						
						//Metodo Utilizado
						if(metodoUtilizado == ""){
							alert("Escriba el metodo utilizado");
							document.getElementById("txt_metUtilizado").focus();
							sw=1;
						}else{
							//Tiempo Utilizacion Desde
							if(tiempoUtilizacionDesde == ""){
								alert("Seleccionar la fecha de tiempo de utilizacion desde");
								document.getElementById("txt_desde").focus();
								sw=1;
							}else{
								//Tiempo Utilizacion Hasta
								if(tiempoUtilizacionHasta == ""){
									alert("Seleccionar la fecha de tiempo de utilizacion hasta");
									document.getElementById("txt_hasta").focus();
									sw=1;
								}else{
							
									//Validacion Select Preescrito
									if(selpreescrito == "Seleccione"){
											alert("Seleccione donde esta preescrito");
											document.getElementById("preescrito").focus();
											sw=1;
									}else{
											var x=document.getElementById("txt_preescrito").value;
											var y=x.length;
						              
											if(selpreescrito == "Ninguno"){
												preescrito="N/A";	
												
									}
										if(y!=0){
											preescrito;	
											
										}
						               
										if(y==0){
											alert("Coloque el nombre del lugar donde usted esta preescrito");
											document.getElementById("txt_preescrito").focus();
											sw=1;
										}else{
												//Problemas
												if(problemas == ""){
													alert("Escriba los problemas");
													document.getElementById("txt_problemas").focus();
													sw=1;
										}else{										
							
										sw=0;
										
										}//Cierra Problemas
										}//Cierra Preescrito
					
				}//Cierra SelPreescrito
									
									}//Cierra Tiempo Utilizacion Hasta
									}//Cierra Tiempo Utilizacion Desde
									
									}//Cierra Metodo Utilizado
			
						}//Cierra que si usa metodo anticonceptivo
										
										
						//alert("sw: "+sw);
		if(sw==0){	
			
			
		var ajax = getXMLObject();
		ajax.open("POST", "pypData", true);
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				alert("Ingreso Exitoso");
				}
			};
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');			
			ajax.send("valor=3&EmbarazoAntes=" + EmbarazoAntes     
					+ "&terminaEmbarazo=" + terminaEmbarazo
					+ "&codpaciente=" + codpacientediagnostico
					+ "&mesesGestacion=" + mesesGestacion
					+ "&tipoParto=" + tipoParto
					+ "&aborto=" + abortos
					+ "&otrosAntecedentes=" + otrosAntecedentes
					+ "&radCiclosRegulares=" + radCiclosRegulares
					+ "&ultimaMenstruacion=" + ultimaMenstruacion
					+ "&tipoTrasMenstruales=" + tipoTrasMenstruales
					+ "&radMetodoAnticonceptivo=" + radMetodoAnticonceptivo
					+ "&metodoUtilizado=" + metodoUtilizado
					+ "&tiempoUtilizacionDesde=" + tiempoUtilizacionDesde
					+ "&tiempoUtilizacionHasta=" + tiempoUtilizacionHasta
					+ "&selpreescrito=" + selpreescrito
					+ "&preescrito=" + preescrito
					+ "&problemas=" + problemas
					+ "&txtCodReporte=" + CodReporte);
			
		}//Cierra sw				
			
									
						
					}//Cierra Metodo Anticonceptivo
			
							}//No Acepta Cero
							}//Tipo TrastornosMenstruales							
							}//Cierra TrastornosMenstruales
			
						}//Cierra UltimaMenstruacion
						}//Cierra CiclosRegulares
						}//Cierra OtrosAntecedentes
					}//Cierra Aborto
				}//Cierra TipoParto
			}//Cierra MesesGestacion
	}//Cierra TerminacionEmbarazo
	
	}//Cierra Select Embarazo Antes
	
	}else{
		alert("Falta Guardar Datos Personales en la pestaña Informacion Usuario");
	}

}

function cal(){
	var posicion = document.getElementById("edadMujer").options.selectedIndex;
	alert(document.getElementById("edadMujer").options[posicion].text);
}

/////////////////////////////////////////////////////////
function GuardarRiesgoReproductivo(){
	
//	var fechaVisita = document.getElementById("datepickerFechaVisita").value.toString("yyyy/mm/dd");
	var fechaVisita = document.getElementById("datepickerFechaVisita").value;
	var fechaUltMenstruacion = document.getElementById("datepickerFechaUltMenstruacion").value;
	var edadMujer = document.getElementById("edadMujer").options.selectedIndex;
	var nombreEdadMujer=document.getElementById("edadMujer").options[edadMujer].text;
	var socioEconomico = document.getElementById("socioEconomico").options.selectedIndex;
	var nombreSocioEconomico = document.getElementById("socioEconomico").options[socioEconomico].text;
	var pariedad = document.getElementById("pariedad").options.selectedIndex;
	var nombrePariedad = document.getElementById("pariedad").options[pariedad].text;
	var intervaloEmbarazo = document.getElementById("intervaloEmbarazo").options.selectedIndex;
	var nombreIntervaloEmbarazo = document.getElementById("intervaloEmbarazo").options[intervaloEmbarazo].text;
	var patologiaActual = document.getElementById("patologiaActual").options.selectedIndex;
	var nombrePatologiaActual = document.getElementById("patologiaActual").options[patologiaActual].text;
	var riesgoReproductivo = document.getElementById("riesgoReproductivo").value;
	var CodReporte=document.getElementById("txtCodReporte").value;
	if(CodReporte!=""){
	
	
	if (fechaVisita == "") {
		alert("Coloque la fecha de la visita");
		document.getElementById("datepickerFechaVisita").focus();
	} else {
		
		if (fechaUltMenstruacion == "") {
			alert("Coloque la fecha de la ultima menstruacion");
			document.getElementById("datepickerFechaUltMenstruacion").focus();
		} else {
			
			if (nombreEdadMujer == "Seleccione") {
				alert("Seleccione un rango de edad");
				document.getElementById("edadMujer").focus();
			} else {
			
				if (nombreSocioEconomico == "Seleccione") {
					alert("Seleccione el nivel socio economico");
					document.getElementById("socioEconomico").focus();
				} else {
					
					if (nombrePariedad == "Seleccione") {
						alert("Seleccione la pariedad");
						document.getElementById("pariedad").focus();
					} else {
						
						if (nombreIntervaloEmbarazo == "Seleccione") {
							alert("Seleccione el intervalo de embarazos");
							document.getElementById("intervaloEmbarazo").focus();
						} else {
	
	//Radio Aborto
	var radAborto = "";
	var radioAborto = document.getElementsByName("radAborto");	
		for (var x = 0; x < radioAborto.length; x ++) {
			if (radioAborto[x].checked) {
				radAborto = radioAborto[x].id;
			}	                               
		}
		
		if (radAborto == ""){
			alert("Seleccione si ha tenido Abortos");
		}else{
			
			//Radio Mortinato
			var radMortinato = "";
			var radioMortinato = document.getElementsByName("radMortinato");	
				for (var x = 0; x < radioMortinato.length; x ++) {
					if (radioMortinato[x].checked) {
						radMortinato = radioMortinato[x].id;
					}	                               
				}
				
				if (radMortinato == ""){
					alert("Seleccione si ha tenido Mortinatos");
				}else{
					
					//Radio Cesarea
					var radCesarea = "";
					var radioCesarea = document.getElementsByName("radCesarea");	
						for (var x = 0; x < radioCesarea.length; x ++) {
							if (radioCesarea[x].checked) {
								radCesarea = radioCesarea[x].id;
							}	                               
						}
						
						if (radCesarea == ""){
							alert("Seleccione si ha tenido Cesareas");
						}else{
							
							//Radio Prematuro
							var radPrematuro = "";
							var radioPrematuro = document.getElementsByName("radPrematuro");	
								for (var x = 0; x < radioPrematuro.length; x ++) {
									if (radioPrematuro[x].checked) {
										radPrematuro = radioPrematuro[x].id;
									}	                               
								}
								
								if (radPrematuro == ""){
									alert("Seleccione si ha tenido Bebes Prematuros");
								}else{
									
									if (nombrePatologiaActual == "Seleccione") {
										alert("Seleccione la patologia actual");
										document.getElementById("patologiaActual").focus();
									} else {
										
										if (riesgoReproductivo == "") {
											alert("Presione el boton Calcular");
										} else {
						
											
			var ajax = getXMLObject();
			ajax.open("POST", "pypData", true);
			ajax.onreadystatechange = function(){
				if(ajax.readyState == 4){
					alert("Ingreso Exitoso");
					}
				};
				ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');			
				ajax.send("valor=4&fechaVisita=" + fechaVisita
						+ "&codpaciente=" + codpacientediagnostico
						+ "&fechaUltMenstruacion=" + fechaUltMenstruacion
						+ "&nombreEdadMujer=" + nombreEdadMujer
						+ "&nombreSocioEconomico=" + nombreSocioEconomico
						+ "&nombrePariedad=" + nombrePariedad
						+ "&nombreIntervaloEmbarazo=" + nombreIntervaloEmbarazo
						+ "&radAborto=" + radAborto
						+ "&radMortinato=" + radMortinato
						+ "&radCesarea=" + radCesarea
						+ "&radPrematuro=" + radPrematuro
						+ "&nombrePatologiaActual=" + nombrePatologiaActual
						+ "&riesgoReproductivo=" + riesgoReproductivo
						+ "&txtCodReporte=" + CodReporte);
										
				}//Cierra Condicion Riesgo Reproductivo
				}//Cierra Patologia Actual
				}//Cierra Prematuro
				}//Cierra Cesarea
				}//Cierra Mortinato
		        }//Cierra Aborto
				}//Cierra Intervalo Embarazo
				}//Cierra Pariedad
				}//Cierra Socio Economico
				}//Cierra Edad Mujer	
				}//Cierra Fecha Utima Menstruacion
				}//Cierra Fecha Visita
	
	}else{
		alert("Falta Guardar Datos Personales en la pestaña Informacion Usuario");
	}
}


function clicksiPruebaEmbarazo(){
	document.getElementById("resPruEmbarazo").disabled=false;	
	document.getElementById("resPruEmbarazo").focus();
}

function clicksiCitologiaCervicoVaginal(){
	document.getElementById("resCitCerVaginal").disabled=false;	
	document.getElementById("resCitCerVaginal").focus();
}

function clicksiFrotisVaginal(){
	document.getElementById("resFroVaginal").disabled=false;	
	document.getElementById("resFroVaginal").focus();
}


function GuardarExamenes(){
	
	var peso = document.getElementById("peso").value;
	var tensionArterial = document.getElementById("tenArterial").value;
	var numeroSemanas = document.getElementById("numSemanas").value;
	var otrosHallazgos = document.getElementById("hallazgos").value;
	var observaciones = document.getElementById("observaciones").value;
	var radsiPruEmbarazo = document.getElementById("siPruEmbarazo").checked;
	var radnoPruEmbarazo = document.getElementById("noPruEmbarazo").checked;
	var resPruEmbarazo = document.getElementById("resPruEmbarazo").value;
	var radsiCitCerVaginal = document.getElementById("siCitCerVaginal").checked;
	var radnoCitCerVaginal = document.getElementById("noCitCerVaginal").checked;
	var resCitCerVaginal = document.getElementById("resCitCerVaginal").value;
	var radsiFroVaginal= document.getElementById("siFroVaginal").checked;
	var radnoFroVaginal = document.getElementById("noFroVaginal").checked;
	var resFroVaginal = document.getElementById("resFroVaginal").value;
	var otrosExaPracticados = document.getElementById("otrosExaPracticados").value; 
	var CodReporte=document.getElementById("txtCodReporte").value;
	if(CodReporte!=""){
	
	
	//Radio MamasNormales
	var radMamasNormales = "";
	var radioMamasNormales = document.getElementsByName("radMamasNormales");	
		for (var x = 0; x < radioMamasNormales.length; x ++) {
			if (radioMamasNormales[x].checked) {
				radMamasNormales = radioMamasNormales[x].id;
			}	                               
		}
		
		if (radMamasNormales == false){
			alert("Seleccione si tiene mamas normales o no");
		}else{
			
			//Radio Utero y Anexos Normales
			var radUteAneNormales = "";
			var radioUteAneNormales = document.getElementsByName("radUteAneNormales");	
				for (var x = 0; x < radioUteAneNormales.length; x ++) {
					if (radioUteAneNormales[x].checked) {
						radUteAneNormales = radioUteAneNormales[x].id;
					}	                               
				}
				
				if (radUteAneNormales == false){
					alert("Seleccione si tiene utero y anexos normales o no");
				}else{
					
					//Radio Signos Embarazo
					var radSigEmbarazo = "";
					var radioSigEmbarazo = document.getElementsByName("radSigEmbarazo");	
						for (var x = 0; x < radioSigEmbarazo.length; x ++) {
							if (radioSigEmbarazo[x].checked) {
								radSigEmbarazo = radioSigEmbarazo[x].id;
							}	                               
						}
						
						if (radSigEmbarazo == false){
							alert("Seleccione si tiene signos de embarazo o no");
						}else{
							
							//Radio Miembros Inferiores : Varices
							var radMiemInfVarices = "";
							var radioMiemInfVarices = document.getElementsByName("radMiemInfVarices");	
								for (var x = 0; x < radioMiemInfVarices.length; x ++) {
									if (radioMiemInfVarices[x].checked) {
										radMiemInfVarices = radioMiemInfVarices[x].id;
									}	                               
								}
								
								if (radMiemInfVarices == false){
									alert("Seleccione si tiene varices o no");
								}else{
									
									//Radio Cervix Normal
									var radCerNormal = "";
									var radioCerNormal = document.getElementsByName("radCerNormal");	
										for (var x = 0; x < radioCerNormal.length; x ++) {
											if (radioCerNormal[x].checked) {
												radCerNormal = radioCerNormal[x].id;
											}	                               
										}
										
										if (radCerNormal == false){
											alert("Seleccione si tiene cervis normal o no");
										}else{
											
											//Radio Edemas
											var radEdemas = "";
											var radioEdemas = document.getElementsByName("radEdemas");	
												for (var x = 0; x < radioEdemas.length; x ++) {
													if (radioEdemas[x].checked) {
														radEdemas = radioEdemas[x].id;
													}	                               
												}
												
												if (radEdemas == false){
													alert("Seleccione si tiene edemas o no");
												}else{
													
													//Peso
													if(peso == ""){
														alert("Escriba el peso");
														document.getElementById("peso").focus();
													}else{
														
														//Tension Arterial
														if(tensionArterial == ""){
															alert("Escriba la tension arterial");
															document.getElementById("tenArterial").focus();
														}else{
															
															//Numero Semanas
															/*if(numeroSemanas == ""){
																alert("Escriba el numero de semanas");
																document.getElementById("numSemanas").focus();
															}else{*/
																
																//Otros Hallazgos
																if(otrosHallazgos == ""){
																	alert("Escriba si tiene otros hallazgos, de lo contrario coloque ninguno");
																	document.getElementById("hallazgos").focus();
																}else{
																	
																	if (observaciones == "") {
																		alert("Escriba las observaciones");
																		document.getElementById("observaciones").focus();
																	}else {
																		
																				//Validacion Radio Prueba Embarazo
																				if (radsiPruEmbarazo==false && radnoPruEmbarazo==false){
																						alert("Seleccione si tiene pruebas de embarazo o no");
																				}else{
																						var x=document.getElementById("resPruEmbarazo").value;
																						var y=x.length;
																	              
																					if((radsiPruEmbarazo==true)&&(y!=0)&&(x=="0")){
																						alert("No Acepta Cero");					
																				}else{
																					if((radsiPruEmbarazo==true)&&(y!=0)&&(x!="0")){
																						resPruEmbarazo;					
																					}
																	               
																					if(radsiPruEmbarazo==true&&y==0){
																						alert("Escriba los resultados de la prueba de embarazo");
																						document.getElementById("resPruEmbarazo").focus();
																				}else{	
																					if(radnoPruEmbarazo==true){
																						resPruEmbarazo="No";	
																					}
																				
																				
																					//Validacion Radio Citologia Cervico Vaginal
																					if (radsiCitCerVaginal==false && radnoCitCerVaginal==false){
																							alert("Seleccione si se ha realizado citologias cervico vaginal o no");
																					}else{
																							var x=document.getElementById("resCitCerVaginal").value;
																							var y=x.length;
																		              
																						if((radsiCitCerVaginal==true)&&(y!=0)&&(x=="0")){
																							alert("No Acepta Cero");					
																					}else{
																						if((radsiCitCerVaginal==true)&&(y!=0)&&(x!="0")){
																							resCitCerVaginal;					
																						}
																		               
																						if(radsiCitCerVaginal==true&&y==0){
																							alert("Escriba los resultados la citologia cervico vaginal");
																							document.getElementById("resCitCerVaginal").focus();
																					}else{	
																						if(radnoCitCerVaginal==true){
																							resCitCerVaginal="No";	
																						}
																						
																						//Validacion Radio Frotis Vaginal
																						if (radsiFroVaginal==false && radnoFroVaginal==false){
																								alert("Seleccione si se ha realizado frotis vaginal o no");
																						}else{
																								var x=document.getElementById("resFroVaginal").value;
																								var y=x.length;
																			              
																							if((radsiFroVaginal==true)&&(y!=0)&&(x=="0")){
																								alert("No Acepta Cero");					
																						}else{
																							if((radsiFroVaginal==true)&&(y!=0)&&(x!="0")){
																								resFroVaginal;					
																							}
																			               
																							if(radsiFroVaginal==true&&y==0){
																								alert("Escriba los resultados la frotis vaginal");
																								document.getElementById("resFroVaginal").focus();
																						}else{	
																							if(radnoFroVaginal==true){
																								resFroVaginal="No";	
																							}
																							
																							if(otrosExaPracticados == ""){
																								alert("Escriba si se ha practicado otros examenes, de lo contrario coloque ninguno");
																								document.getElementById("otrosExaPracticados").focus();
																							}else {
					
																								
			var ajax = getXMLObject();
			ajax.open("POST", "pypData", true);
			ajax.onreadystatechange = function(){
				if(ajax.readyState == 4){
					alert("Ingreso Exitoso");
					}
				};
				ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');			
				ajax.send("valor=5&radMamasNormales=" + radMamasNormales
					+ "&codpaciente=" + codpacientediagnostico
					+ "&radUteAneNormales=" + radUteAneNormales
					+ "&radSigEmbarazo=" + radSigEmbarazo
					+ "&radMiemInfVarices=" + radMiemInfVarices
					+ "&radCerNormal=" + radCerNormal
					+ "&radEdemas=" + radEdemas
					+ "&peso=" + peso
					+ "&tensionArterial=" + tensionArterial
					+ "&numeroSemanas=" + numeroSemanas
					+ "&otrosHallazgos=" + otrosHallazgos
					+ "&observaciones=" + observaciones
					+ "&resPruEmbarazo=" + resPruEmbarazo
					+ "&resCitCerVaginal=" + resCitCerVaginal
					+ "&resFroVaginal=" + resFroVaginal
					+ "&otrosExaPracticados=" + otrosExaPracticados
					+ "&txtCodReporte=" + CodReporte);
				
																								}//Cierra Otros Examenes Practicados
																								
																							}//Resultados Frotis Vaginal
																					}//Cierra No 0 Frotis Vaginal
																								}//Cierra Validacion Radio Frotis Vaginal
																							
																					}//Resultados Citologia Cervico Vaginal
																					}//Cierra No 0 Citologia Cervico Vaginal
																				}//Cierra Validacion Radio Citologia Cervico Vaginal
																					
																	}//Resultados Prueba Embarazo
																}//Cierra No 0 Prueba Embarazo
															}//Cierra Validacion Radio Prueba Embarazo
																	
																}//Cierra Observaciones
																	}//Cierra Hallazgos
														//}//Cierra Numero Semanas
														}//Cierra Tension Arterial
														}//Cierra Peso
													}//Cierra Edemas
								}//Cierra Cervix Normal
								}//Cierra Miembros Inferiores : Varices
								}//Cierra Signos Embarazo
						}//Cierra Utero y Anexos Normales
		}//Cierra Mamas Normales
		
	}else{
		alert("Falta Guardar Datos Personales en la pestaña Informacion Usuario");
	}

}

/////////////////////////////////////////////////////////////////////
//Validacion del select Pildoras
function selectPildoras(){
	
	var selPildoras = document.getElementById("pildoras").value;
	
	if (selPildoras == "No") {
		document.getElementById("cualPildoras").value="N/A";
		document.getElementById("cualPildoras").disabled=true;
		document.getElementById("datepickerPildoras").value="N/A";
		document.getElementById("datepickerPildoras").disabled=true;
	}
	else {	
		if(selPildoras == "Seleccione"){
			document.getElementById("cualPildoras").disabled=true;
			document.getElementById("cualPildoras").value="";
			document.getElementById("datepickerPildoras").disabled=true;
			document.getElementById("datepickerPildoras").value="";
		}else{
			document.getElementById("cualPildoras").disabled=false;
			document.getElementById("cualPildoras").focus();
			document.getElementById("cualPildoras").value="";
			document.getElementById("datepickerPildoras").disabled=false;
			document.getElementById("datepickerPildoras").value="";
		}
	}
}


//Validacion del select DIU
function selectDIU(){
	
	var selDIU = document.getElementById("diu").value;
	
	if (selDIU == "No") {
		document.getElementById("cualDIU").value="N/A";
		document.getElementById("cualDIU").disabled=true;
		document.getElementById("datepickerDIU").value="N/A";
		document.getElementById("datepickerDIU").disabled=true;
	}
	else {	
		if(selDIU == "Seleccione"){
			document.getElementById("cualDIU").disabled=true;
			document.getElementById("cualDIU").value="";
			document.getElementById("datepickerDIU").disabled=true;
			document.getElementById("datepickerDIU").value="";
		}else{
			document.getElementById("cualDIU").disabled=false;
			document.getElementById("cualDIU").focus();
			document.getElementById("cualDIU").value="";
			document.getElementById("datepickerDIU").disabled=false;
			document.getElementById("datepickerDIU").value="";
		}
	}
}


//Validacion del select Inyectables
function selectInyectables(){
	
	var selInyectables = document.getElementById("inyectables").value;
	
	if (selInyectables == "No") {
		document.getElementById("cualInyectables").value="N/A";
		document.getElementById("cualInyectables").disabled=true;
		document.getElementById("datepickerInyectables").value="N/A";
		document.getElementById("datepickerInyectables").disabled=true;
	}
	else {	
		if(selInyectables == "Seleccione"){
			document.getElementById("cualInyectables").disabled=true;
			document.getElementById("cualInyectables").value="";
			document.getElementById("datepickerInyectables").disabled=true;
			document.getElementById("datepickerInyectables").value="";
		}else{
			document.getElementById("cualInyectables").disabled=false;
			document.getElementById("cualInyectables").focus();
			document.getElementById("cualInyectables").value="";
			document.getElementById("datepickerInyectables").disabled=false;
			document.getElementById("datepickerInyectables").value="";
		}
	}
}


//Validacion del select Otros
function selectOtros(){
	
	var selOtros = document.getElementById("metOtros").value;
	
	if (selOtros == "No") {
		document.getElementById("cualOtros").value="N/A";
		document.getElementById("cualOtros").disabled=true;
		document.getElementById("datepickerOtros").value="N/A";
		document.getElementById("datepickerOtros").disabled=true;
	}
	else {	
		if(selOtros == "Seleccione"){
			document.getElementById("cualOtros").disabled=true;
			document.getElementById("cualOtros").value="";
			document.getElementById("datepickerOtros").disabled=true;
			document.getElementById("datepickerOtros").value="";
		}else{
			document.getElementById("cualOtros").disabled=false;
			document.getElementById("cualOtros").focus();
			document.getElementById("cualOtros").value="";
			document.getElementById("datepickerOtros").disabled=false;
			document.getElementById("datepickerOtros").value="";
		}
	}
}



function GuardarMetodos(){
	
	var MetPildoras = "Pildoras";
	var selPildoras = document.getElementById("pildoras").value;
	var cualPildoras = document.getElementById("cualPildoras").value;
	var fechaPildoras = document.getElementById("datepickerPildoras").value;
	
	var MetDIU = "DIU";
	var selDIU = document.getElementById("diu").value;
	var cualDIU = document.getElementById("cualDIU").value;
	var fechaDIU = document.getElementById("datepickerDIU").value;
	
	var MetInyectables = "Inyectables";
	var selInyectables = document.getElementById("inyectables").value;
	var cualInyectables = document.getElementById("cualInyectables").value;
	var fechaInyectables = document.getElementById("datepickerInyectables").value;
	
	var MetOtros = "Otros";
	var selOtros = document.getElementById("metOtros").value;
	var cualOtros = document.getElementById("cualOtros").value;
	var fechaOtros = document.getElementById("datepickerOtros").value;
	
	var CodReporte=document.getElementById("txtCodReporte").value;
	if(CodReporte!=""){
	
	
	//Validacion Select Pildoras
	if(selPildoras == "Seleccione"){
			alert("Seleccione si usa pildoras o no");
			document.getElementById("pildoras").focus();
	}else{
			var x=document.getElementById("cualPildoras").value;
			var y=x.length;
			var a=document.getElementById("datepickerPildoras").value;
			var b=a.length;
			
			if(selPildoras == "No"){
				cualPildoras="N/A";
				fechaPildoras="N/A";
				
	}
		if(y!=0){
			cualPildoras;
			fechaPildoras;
			
		}
       
		if(y==0){
			alert("Coloque que pildoras utiliza");
			document.getElementById("cualPildoras").focus();
		}else{
			if(b==0){
				alert("Coloque la fecha que utilizo las pildoras");
				document.getElementById("datepickerPildoras").focus();
			}else{			
				
				//Validacion Select DIU
				if(selDIU == "Seleccione"){
						alert("Seleccione si usa DIU o no");
						document.getElementById("diu").focus();
				}else{
						var x=document.getElementById("cualDIU").value;
						var y=x.length;
						var a=document.getElementById("datepickerDIU").value;
						var b=a.length;
						      
						if(selDIU == "No"){
							cualDIU="N/A";
							fechaDIU="N/A";			
				}
					if(y!=0){
						cualDIU;
						fechaDIU;			
					}
			       
					if(y==0){
						alert("Coloque que DIU utiliza");
						document.getElementById("cualDIU").focus();
					}else{
						if(b==0){
							alert("Coloque la fecha que utilizo las DIU");
							document.getElementById("datepickerDIU").focus();
						}else{
							
							
							//Validacion Select Inyectables
							if(selInyectables == "Seleccione"){
									alert("Seleccione si usa inyectables o no");
									document.getElementById("inyectables").focus();
							}else{
									var x=document.getElementById("cualInyectables").value;
									var y=x.length;
									var a=document.getElementById("datepickerInyectables").value;
									var b=a.length;
									      
									if(selInyectables == "No"){
										cualInyectables="N/A";
										fechaInyectables="N/A";			
							}
								if(y!=0){
									cualInyectables;
									fechaInyectables;			
								}
						       
								if(y==0){
									alert("Coloque que inyectables utiliza");
									document.getElementById("cualInyectables").focus();
								}else{
									if(b==0){
										alert("Coloque la fecha que utilizo los inyectables");
										document.getElementById("datepickerInyectables").focus();
									}else{
										
										//Validacion Select Otros
										if(selOtros == "Seleccione"){
												alert("Seleccione si usa otros metodos o no");
												document.getElementById("metOtros").focus();
										}else{
												var x=document.getElementById("cualOtros").value;
												var y=x.length;
												var a=document.getElementById("datepickerOtros").value;
												var b=a.length;
												      
												if(selOtros == "No"){
													cualOtros="N/A";
													fechaOtros="N/A";			
										}
											if(y!=0){
												cualOtros;
												fechaOtros;			
											}
									       
											if(y==0){
												alert("Coloque que otros metodos utiliza");
												document.getElementById("cualOtros").focus();
											}else{
												if(b==0){
													alert("Coloque la fecha que utilizo los otros metodos");
													document.getElementById("datepickerOtros").focus();
												}else{

												
		var ajax = getXMLObject();
		ajax.open("POST", "pypData", true);
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				alert("Ingreso Exitoso");
				}
			};
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');			
			ajax.send("valor=6&selPildoras=" + selPildoras
					+ "&codpaciente=" + codpacientediagnostico
					+ "&MetPildoras=" + MetPildoras
					+ "&cualPildoras=" + cualPildoras
					+ "&fechaPildoras=" + fechaPildoras
					+ "&selDIU=" + selDIU
					+ "&MetDIU=" + MetDIU
					+ "&cualDIU=" + cualDIU
					+ "&fechaDIU=" + fechaDIU
					+ "&selInyectables=" + selInyectables
					+ "&MetInyectables=" + MetInyectables
					+ "&cualInyectables=" + cualInyectables
					+ "&fechaInyectables=" + fechaInyectables
					+ "&selOtros=" + selOtros
					+ "&MetOtros=" + MetOtros
					+ "&cualOtros=" + cualOtros
					+ "&fechaOtros=" + fechaOtros
					+ "&txtCodReporte=" + CodReporte);
			
			}//Cierra Fecha Otros
			}//Cierra Cuales Otros
			}//Cierra Si Usa Otros Metodos
											
			}//Cierra Fecha Inyectables
			}//Cierra Cuales Inyectables
			}//Cierra Si Usa Inyectables
									
			}//Cierra Fecha DIU
			}//Cierra Cuales DIU
			}//Cierra Si Usa DIU
			
			}//Cierra Fecha Pildoras
			}//Cierra Cuales Pildoras
			}//Cierra Si Usa Pildoras
	
	}else{
		alert("Falta Guardar Datos Personales en la pestaña Informacion Usuario");
	}

}


function GuardarControles() {

	var fechaControles = document.getElementById("datepickerFechaControles").value.toString("yyyy/mm/dd");
	var pesoControles = document.getElementById("pesoControles").value;
	var tensionControles = document.getElementById("tenArtControles").value;
	var CodReporte=document.getElementById("txtCodReporte").value;
	if(CodReporte!=""){

	// Fecha Controles
	if (fechaControles == "") {
		alert("Coloque la fecha del control");
		document.getElementById("datepickerFechaControles").focus();
	} else {

		// Peso Controles
		if (pesoControles == "") {
			alert("Escriba el peso en KG");
			document.getElementById("pesoControles").focus();
		} else {

			// Tension Arterial
			if (tensionControles == "") {
				alert("Escriba la tension arterial");
				document.getElementById("tenArtControles").focus();
			} else {

				// Radio Satisfaccion Metodo
				var radSatisfaccionMetodo = "";
				var radioSatisfaccionMetodo = document.getElementsByName("radSatisfaccionMetodoC");
				for ( var x = 0; x < radioSatisfaccionMetodo.length; x++) {
					if (radioSatisfaccionMetodo[x].checked) {
						radSatisfaccionMetodo = radioSatisfaccionMetodo[x].id;
					}
				}

				/*if (radSatisfaccionMetodo == false) {
					alert("Seleccione si se siente satisfecho con el metodo o no");
				} else {*/

					// Radio Trastornos Menstruales
					var radTrastornosMenstruales = "";
					var radioTrastornosMenstruales = document.getElementsByName("radTrastornosMenstrualesC");
					for ( var x = 0; x < radioTrastornosMenstruales.length; x++) {
						if (radioTrastornosMenstruales[x].checked) {
							radTrastornosMenstruales = radioTrastornosMenstruales[x].id;
						}
					}

					/*if (radTrastornosMenstruales == false) {
						alert("Seleccione si tiene trastornos menstruales o no");
					} else {*/

						// Radio Cambios Comportamiento
						var radCambiosComportamiento = "";
						var radioCambiosComportamiento = document.getElementsByName("radCambioComportamientoC");
						for ( var x = 0; x < radioCambiosComportamiento.length; x++) {
							if (radioCambiosComportamiento[x].checked) {
								radCambiosComportamiento = radioCambiosComportamiento[x].id;
							}
						}

						/*if (radCambiosComportamiento == false) {
							alert("Seleccione si ha tenido cambios de comportamiento o no");
						} else {*/

							// Radio Cefaleas
							var radCefaleas = "";
							var radioCefaleas = document.getElementsByName("radCefaleasC");
							for ( var x = 0; x < radioCefaleas.length; x++) {
								if (radioCefaleas[x].checked) {
									radCefaleas = radioCefaleas[x].id;
								}
							}

							if (radCefaleas == false) {
								alert("Seleccione si tiene cefaleas o no");
							} else {

								// Radio Mareos
								var radMareos = "";
								var radioMareos = document.getElementsByName("radMareosC");
								for ( var x = 0; x < radioMareos.length; x++) {
									if (radioMareos[x].checked) {
										radMareos = radioMareos[x].id;
									}
								}

								if (radMareos == false) {
									alert("Seleccione si tiene mareos o no");
								} else {

									// Radio Manchas Piel
									var radManchasPiel = "";
									var radioManchasPiel = document.getElementsByName("radManchasPielC");
									for ( var x = 0; x < radioManchasPiel.length; x++) {
										if (radioManchasPiel[x].checked) {
											radManchasPiel = radioManchasPiel[x].id;
										}
									}

									if (radManchasPiel == false) {
										alert("Seleccione si tiene manchas en la piel o no");
									} else {

										// Radio Molestias Mamas
										var radMolestiasMamas = "";
										var radioMolestiasMamas = document.getElementsByName("radMolestiasMamaC");
										for ( var x = 0; x < radioMolestiasMamas.length; x++) {
											if (radioMolestiasMamas[x].checked) {
												radMolestiasMamas = radioMolestiasMamas[x].id;
											}
										}

										/*if (radMolestiasMamas == false) {
											alert("Seleccione si tiene molestias en las mamas o no");
										} else {*/

											// Radio Edemas
											var radEdemasControles = "";
											var radioEdemasControles = document.getElementsByName("radEdemasC");
											for ( var x = 0; x < radioEdemasControles.length; x++) {
												if (radioEdemasControles[x].checked) {
													radEdemasControles = radioEdemasControles[x].id;
												}
											}

											if (radEdemasControles == false) {
												alert("Seleccione si tiene edemas o no");
											} else {

												// Radio Varices
												var radVarices = "";
												var radioVarices = document.getElementsByName("radVaricesC");
												for ( var x = 0; x < radioVarices.length; x++) {
													if (radioVarices[x].checked) {
														radVarices = radioVarices[x].id;
													}
												}

												if (radVarices == false) {
													alert("Seleccione si tiene varices o no");
												} else {

													// Radio Expulsion Dispositivo
													var radExpulsionDispositivo = "";
													var radioExpulsionDispositivo = document.getElementsByName("radExpulsionDispositivoC");
													for ( var x = 0; x < radioExpulsionDispositivo.length; x++) {
														if (radioExpulsionDispositivo[x].checked) {
															radExpulsionDispositivo = radioExpulsionDispositivo[x].id;
														}
													}

													/*if (radExpulsionDispositivo == false) {
														alert("Seleccione si se ha expulsado el dispositivo o no");
													} else {*/

														// Radio Dolor Bajo
														// Vientre
														var radDolorBajoVientre = "";
														var radioDolorBajoVientre = document.getElementsByName("radDolorBajoVientreC");
														for ( var x = 0; x < radioDolorBajoVientre.length; x++) {
															if (radioDolorBajoVientre[x].checked) {
																radDolorBajoVientre = radioDolorBajoVientre[x].id;
															}
														}

														/*if (radDolorBajoVientre == false) {
															alert("Seleccione si tiene dolores bajo vientre o no");
														} else {*/

															// Radio Flujo Vaginal
															var radFlujoVaginal = "";
															var radioFlujoVaginal = document.getElementsByName("radFlujoVaginalC");
															for ( var x = 0; x < radioFlujoVaginal.length; x++) {
																if (radioFlujoVaginal[x].checked) {
																	radFlujoVaginal = radioFlujoVaginal[x].id;
																}
															}

															/*if (radFlujoVaginal == false) {
																alert("Seleccione si tiene flujo vaginal o no");
															} else {*/

																
																// Envio por Ajax
																var ajax = getXMLObject();
																ajax.open("POST", "pypData", true);
																ajax.onreadystatechange = function() {
																	if (ajax.readyState == 4) {
																		alert("Ingreso Exitoso");
																	}
																};
																ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
																ajax.send("valor=7&fechaControles=" + fechaControles
																		+ "&codpaciente=" + codpacientediagnostico
																		+ "&pesoControles=" + pesoControles
																		+ "&tensionControles=" + tensionControles
																		+ "&radSatisfaccionMetodo=" + radSatisfaccionMetodo
																		+ "&radTrastornosMenstruales=" + radTrastornosMenstruales
																		+ "&radCambiosComportamiento=" + radCambiosComportamiento
																		+ "&radCefaleas=" + radCefaleas
																		+ "&radMareos=" + radMareos
																		+ "&radManchasPiel=" + radManchasPiel
																		+ "&radMolestiasMamas=" + radMolestiasMamas
																		+ "&radEdemasControles=" + radEdemasControles
																		+ "&radVarices=" + radVarices
																		+ "&radExpulsionDispositivo=" + radExpulsionDispositivo
																		+ "&radDolorBajoVientre=" + radDolorBajoVientre
																		+ "&radFlujoVaginal=" + radFlujoVaginal
																		+ "&txtCodReporte=" + CodReporte);

															//}//Cierra Flujo Vaginal
														//}//Cierra Dolor Bajo Vientre
													//}//Cierra Expulsion Dispositivo
												}//Cierra Varices
											}//Cierra Edemas
										//}//Cierra Molestias Mamas
									}//Cierra Manchas Piel
								}//Cierra Mareos
							}//Cierra Cefaleas
						//}//Cierra Cambios Comportamiento
					//}//Cierra Trastornos Menstruales
				//}// Cierra Satisfaccion Metodo
			}// Cierra Tension Arterial Controles
		}// Cierra Peso Controles
	}// Cierra Fecha Controles
	
	}else{
		alert("Falta Guardar Datos Personales en la pestaña Informacion Usuario");
	}

}



function GuardarConductas(){
	
	var otraEspecialidad = document.getElementById("otraEspecialidadControles").value;
	var CodReporte=document.getElementById("txtCodReporte").value;
	if(CodReporte!=""){
	
	// Radio Estilo Vida Saludable
	var radEstiloVidaSaludable = "";
	var radioEstiloVidaSaludable = document.getElementsByName("radEstiloVidaSaludableC");
	for ( var x = 0; x < radioEstiloVidaSaludable.length; x++) {
		if (radioEstiloVidaSaludable[x].checked) {
			radEstiloVidaSaludable = radioEstiloVidaSaludable[x].id;
		}
	}

	if (radEstiloVidaSaludable == false) {
		alert("Seleccione si tiene un estilo de vida saludable o no");
	} else {
		
		// Radio Nutricion Alimentacion
		var radNutricionAlimentacion = "";
		var radioNutricionAlimentacion = document.getElementsByName("radNutricionAlimentacionC");
		for ( var x = 0; x < radioNutricionAlimentacion.length; x++) {
			if (radioNutricionAlimentacion[x].checked) {
				radNutricionAlimentacion = radioNutricionAlimentacion[x].id;
			}
		}

		if (radNutricionAlimentacion == false) {
			alert("Seleccione si tiene nutricion y alimentacion o no");
		} else {
			
			// Radio Prevencion Enfermedades Transmision Sexual
			var radPrevencionEnfermedadesTranmisionSexual = "";
			var radioPrevencionEnfermedadesTranmisionSexual = document.getElementsByName("radPrevencionEnfermedadesTransmisionSexualC");
			for ( var x = 0; x < radioPrevencionEnfermedadesTranmisionSexual.length; x++) {
				if (radioPrevencionEnfermedadesTranmisionSexual[x].checked) {
					radPrevencionEnfermedadesTranmisionSexual = radioPrevencionEnfermedadesTranmisionSexual[x].id;
				}
			}

			if (radPrevencionEnfermedadesTranmisionSexual == false) {
				alert("Seleccione si se ha prevenido de las enfermedades de transmision sexual o no");
			} else {
				
				// Radio Consejeria Metodos Planificacion Familiar
				var radConsejeriaMetodos = "";
				var radioConsejeriaMetodos = document.getElementsByName("radConsejeriaMetodosC");
				for ( var x = 0; x < radioConsejeriaMetodos.length; x++) {
					if (radioConsejeriaMetodos[x].checked) {
						radConsejeriaMetodos = radioConsejeriaMetodos[x].id;
					}
				}

				if (radConsejeriaMetodos == false) {
					alert("Seleccione si tiene consejeria para los metodos de planificacion familiar o no");
				} else {
					
					// Radio Signos Sintomas Alarma Embarazo
					var radSignosSintomasAlarma = "";
					var radioSignosSintomasAlarma = document.getElementsByName("radSignosSintomasAlarmaC");
					for ( var x = 0; x < radioSignosSintomasAlarma.length; x++) {
						if (radioSignosSintomasAlarma[x].checked) {
							radSignosSintomasAlarma = radioSignosSintomasAlarma[x].id;
						}
					}

					/*if (radSignosSintomasAlarma == false) {
						alert("Seleccione si tiene signos y sintomas de alarma o no");
					} else {*/
						
						// Radio Prevencion Cancer Cuello Uterino
						var radPrevencionCancerCuelloUterino = "";
						var radioPrevencionCancerCuelloUterino = document.getElementsByName("radPrevencionCancerCuelloUterinoC");
						for ( var x = 0; x < radioPrevencionCancerCuelloUterino.length; x++) {
							if (radioPrevencionCancerCuelloUterino[x].checked) {
								radPrevencionCancerCuelloUterino = radioPrevencionCancerCuelloUterino[x].id;
							}
						}

						/*if (radPrevencionCancerCuelloUterino == false) {
							alert("Seleccione si se ha prevenido del cancer de cuello uterino o no");
						} else {*/
							
							// Radio Autoexamen Mama
							var radAutoexamenMama = "";
							var radioAutoexamenMama = document.getElementsByName("radAutoexamenMamaC");
							for ( var x = 0; x < radioAutoexamenMama.length; x++) {
								if (radioAutoexamenMama[x].checked) {
									radAutoexamenMama = radioAutoexamenMama[x].id;
								}
							}

							/*if (radAutoexamenMama == false) {
								alert("Seleccione si se ha realizado el autoexamen de mama o no");
							} else {*/
								
								// Radio Lactancia Materna
								var radLactanciaMaterna = "";
								var radioLactanciaMaterna = document.getElementsByName("radLactanciaMaternaC");
								for ( var x = 0; x < radioLactanciaMaterna.length; x++) {
									if (radioLactanciaMaterna[x].checked) {
										radLactanciaMaterna = radioLactanciaMaterna[x].id;
									}
								}

								/*if (radLactanciaMaterna == false) {
									alert("Seleccione si tiene lactancia materna o no");
								} else {*/
									
									// Radio Prevencion Diabetes Hipertension Osteoporosis
									var radPrevencionDiaHipOst = "";
									var radioPrevencionDiaHipOst = document.getElementsByName("radPrevenirDiaHipOstC");
									for ( var x = 0; x < radioPrevencionDiaHipOst.length; x++) {
										if (radioPrevencionDiaHipOst[x].checked) {
											radPrevencionDiaHipOst = radioPrevencionDiaHipOst[x].id;
										}
									}

									if (radPrevencionDiaHipOst == false) {
										alert("Seleccione si se ha prevenido de diabetes, hipertension y osteoporosis o no");
									} else {
										
										// Radio Abuso Sexual
										var radAbusoSexual = "";
										var radioAbusoSexual = document.getElementsByName("radAbusoSexualC");
										for ( var x = 0; x < radioAbusoSexual.length; x++) {
											if (radioAbusoSexual[x].checked) {
												radAbusoSexual = radioAbusoSexual[x].id;
											}
										}

										if (radAbusoSexual == false) {
											alert("Seleccione si sufre abuso sexual o no");
										} else {
											
											// Radio Violencia Intrafamiliar
											var radViolenciaIntrafamiliar = "";
											var radioViolenciaIntrafamiliar = document.getElementsByName("radViolenciaIntrafamiliarC");
											for ( var x = 0; x < radioViolenciaIntrafamiliar.length; x++) {
												if (radioViolenciaIntrafamiliar[x].checked) {
													radViolenciaIntrafamiliar = radioViolenciaIntrafamiliar[x].id;
												}
											}

											if (radViolenciaIntrafamiliar == false) {
												alert("Seleccione si sufre violencia intrafamiliar o no");
											} else {
												
												// Radio Consecuencias Consumo Alcohol Cigarrillo
												var radConsumoAlcCig = "";
												var radioConsumoAlcCig = document.getElementsByName("radConsecuenciasAlcCigC");
												for ( var x = 0; x < radioConsumoAlcCig.length; x++) {
													if (radioConsumoAlcCig[x].checked) {
														radConsumoAlcCig = radioConsumoAlcCig[x].id;
													}
												}

												if (radConsumoAlcCig == false) {
													alert("Seleccione si tiene consecuencias por el consumo de alcohol y cigarrillos o no");
												} else {
													
													// Radio Complicaciones Drogas
													var radComplicacionesDrogas = "";
													var radioComplicacionesDrogas = document.getElementsByName("radComplicacionesDrogasC");
													for ( var x = 0; x < radioComplicacionesDrogas.length; x++) {
														if (radioComplicacionesDrogas[x].checked) {
															radComplicacionesDrogas = radioComplicacionesDrogas[x].id;
														}
													}

													if (radComplicacionesDrogas == false) {
														alert("Seleccione si tiene complicaciones con las drogas o no");
													} else {
														
														// Radio Autoestima
														var radAutoestima = "";
														var radioAutoestima = document.getElementsByName("radAutoestimaC");
														for ( var x = 0; x < radioAutoestima.length; x++) {
															if (radioAutoestima[x].checked) {
																radAutoestima = radioAutoestima[x].id;
															}
														}

														if (radAutoestima == false) {
															alert("Seleccione si se autoestima o no");
														} else {
															
															// Radio Cuidados Recien Nacido
															var radCuidadoRecienNacido = "";
															var radioCuidadoRecienNacido = document.getElementsByName("radCuidadosRecienNacidoC");
															for ( var x = 0; x < radioCuidadoRecienNacido.length; x++) {
																if (radioCuidadoRecienNacido[x].checked) {
																	radCuidadoRecienNacido = radioCuidadoRecienNacido[x].id;
																}
															}

															/*if (radCuidadoRecienNacido == false) {
																alert("Seleccione si tiene cuidados con el recien nacido o no");
															} else {*/
																
																// Radio Derechos Reproductivos
																var radDerechosReproductivos = "";
																var radioDerechosReproductivos = document.getElementsByName("radDerechosReproductivosC");
																for ( var x = 0; x < radioDerechosReproductivos.length; x++) {
																	if (radioDerechosReproductivos[x].checked) {
																		radDerechosReproductivos = radioDerechosReproductivos[x].id;
																	}
																}

																if (radDerechosReproductivos == false) {
																	alert("Seleccione si tiene derechos reproductivos o no");
																} else {
																	
																	// Radio Salud Sexual Reproductiva
																	var radSaludSexualReproductiva = "";
																	var radioSaludSexualReproductiva = document.getElementsByName("radSaludSexualReproductivaC");
																	for ( var x = 0; x < radioSaludSexualReproductiva.length; x++) {
																		if (radioSaludSexualReproductiva[x].checked) {
																			radSaludSexualReproductiva = radioSaludSexualReproductiva[x].id;
																		}
																	}

																	if (radSaludSexualReproductiva == false) {
																		alert("Seleccione si tiene salud sexual reproductiva o no");
																	} else {
																		
																		// Radio Otro Programa PYP
																		var radOtroProgramaPYP = "";
																		var radioOtroProgramaPYP = document.getElementsByName("radOtroProgamaPYPC");
																		for ( var x = 0; x < radioOtroProgramaPYP.length; x++) {
																			if (radioOtroProgramaPYP[x].checked) {
																				radOtroProgramaPYP = radioOtroProgramaPYP[x].id;
																			}
																		}

																		if (radOtroProgramaPYP == false) {
																			alert("Seleccione si usa otro programa de PYP o no");
																		} else {
																			
																			// Radio Valoracion Psicologia
																			var radValoracionPsicologia = "";
																			var radioValoracionPsicologia = document.getElementsByName("radValoracionPsicologiaC");
																			for ( var x = 0; x < radioValoracionPsicologia.length; x++) {
																				if (radioValoracionPsicologia[x].checked) {
																					radValoracionPsicologia = radioValoracionPsicologia[x].id;
																				}
																			}

																			if (radValoracionPsicologia == false) {
																				alert("Seleccione si le han realizado valoracion por psicologia o no");
																			} else {
																				
																				// Radio Valoracion Nutricion
																				var radValoracionNutricion = "";
																				var radioValoracionNutricion = document.getElementsByName("radValoracionNutricionC");
																				for ( var x = 0; x < radioValoracionNutricion.length; x++) {
																					if (radioValoracionNutricion[x].checked) {
																						radValoracionNutricion = radioValoracionNutricion[x].id;
																					}
																				}

																				if (radValoracionNutricion == false) {
																					alert("Seleccione si le han realizado valoracion por nutricion o no");
																				} else {
																					
																					// Radio Valoracion Ginecologia
																					var radValoracionGinecologia = "";
																					var radioValoracionGinecologia = document.getElementsByName("radValoracionGinecologiaC");
																					for ( var x = 0; x < radioValoracionGinecologia.length; x++) {
																						if (radioValoracionGinecologia[x].checked) {
																							radValoracionGinecologia = radioValoracionGinecologia[x].id;
																						}
																					}

																					/*if (radValoracionGinecologia == false) {
																						alert("Seleccione si le han realizado valoracion por ginecologia o no");
																					} else {*/
																						
																						// Radio Valoracion Urologia
																						var radValoracionUrologia = "";
																						var radioValoracionUrologia = document.getElementsByName("radValoracionUrologiaC");
																						for ( var x = 0; x < radioValoracionUrologia.length; x++) {
																							if (radioValoracionUrologia[x].checked) {
																								radValoracionUrologia = radioValoracionUrologia[x].id;
																							}
																						}

																						if (radValoracionUrologia == false) {
																							alert("Seleccione si le han realizado valoracion por urologia o no");
																						} else {
																							
																							// Radio Consulta Medica
																							var radConsultaMedica = "";
																							var radioConsultaMedica = document.getElementsByName("radConsultaMedicaC");
																							for ( var x = 0; x < radioConsultaMedica.length; x++) {
																								if (radioConsultaMedica[x].checked) {
																									radConsultaMedica = radioConsultaMedica[x].id;
																								}
																							}

																							if (radConsultaMedica == false) {
																								alert("Seleccione si le han realizado consulta medica por enfermedad o no");
																							} else {
																								
																								if (otraEspecialidad == "") {
																									alert("Escriba si tiene otra especialidad, de lo contrario coloque ninguna.");
																									document.getElementById("otraEspecialidadControles").focus();																								
																								} else {
																									
																									
																									
																								
																							// Envio por Ajax
																							var ajax = getXMLObject();
																							ajax.open("POST", "pypData", true);
																							ajax.onreadystatechange = function() {
																								if (ajax.readyState == 4) {
																									alert("Ingreso Exitoso");
																								}
																							};
																							ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
																							ajax.send("valor=8&radEstiloVidaSaludable=" + radEstiloVidaSaludable
																									+ "&codpaciente=" + codpacientediagnostico
																									+ "&radNutricionAlimentacion=" + radNutricionAlimentacion
																									+ "&radPrevencionEnfermedadesTranmisionSexual=" + radPrevencionEnfermedadesTranmisionSexual
																									+ "&radConsejeriaMetodos=" + radConsejeriaMetodos
																									+ "&radSignosSintomasAlarma=" + radSignosSintomasAlarma
																									+ "&radPrevencionCancerCuelloUterino=" + radPrevencionCancerCuelloUterino
																									+ "&radAutoexamenMama=" + radAutoexamenMama
																									+ "&radLactanciaMaterna=" + radLactanciaMaterna
																									+ "&radPrevencionDiaHipOst=" + radPrevencionDiaHipOst
																									+ "&radAbusoSexual=" + radAbusoSexual
																									+ "&radViolenciaIntrafamiliar=" + radViolenciaIntrafamiliar 
																									+ "&radConsumoAlcCig=" + radConsumoAlcCig
																									+ "&radComplicacionesDrogas=" + radComplicacionesDrogas
																									+ "&radAutoestima=" + radAutoestima
																									+ "&radCuidadoRecienNacido=" + radCuidadoRecienNacido
																									+ "&radDerechosReproductivos=" + radDerechosReproductivos
																									+ "&radSaludSexualReproductiva=" + radSaludSexualReproductiva
																									+ "&radOtroProgramaPYP=" + radOtroProgramaPYP
																									+ "&radValoracionPsicologia=" + radValoracionPsicologia
																									+ "&radValoracionNutricion=" + radValoracionNutricion
																									+ "&radValoracionGinecologia=" + radValoracionGinecologia
																									+ "&radValoracionUrologia=" + radValoracionUrologia
																									+ "&radConsultaMedica=" + radConsultaMedica
																									+ "&otraEspecialidad=" + otraEspecialidad
																									+ "&txtCodReporte=" + CodReporte);
										
																						}//Cierra Otra Especialidad
																					}//Cierra Consulta Medica
																				}//Cierra Valoracion Urologia
																			//}//Cierra Valoracion Ginecologia
																		  }//Cierra Valoracion Nutricion
																		}//Cierra Valoracion Psicologia
																	}//Cierra Otro Programa PYP
																}//Cierra Salud Sexual Reproductiva
															}//Cierra Derechos Reproductivos
														//}//Cierra Cuidado Recien Nacido
													}//Cierra Autoestima
												}//Cierra Complicaciones Drogas
											}//Cierra Consumo Alcohol Cigarrillos
										}//Cierra Violencia Intrafamiliar
									}//Cierra Abuso Sexual
								  }//Cierra Prevencion Diabetes Hipertension Osteoporosis
							   //}//Cierra Lactancia Materna
							//}//Cierra Autoexamen Mama
						//}//Cierra Prevencion Cancer Cuello Uterino
					//}//Cierra Signos Sintomas Alarma Embarazo
				}//Cierra Consejeria Metodos
			}//Cierra Prevencion Enfermedades Tranmision Sexual
		}//Cierra Nutricion Alimentacion
	}//Cierra Estilo Vida Saludable
	
	}else{
		alert("Falta Guardar Datos Personales en la pestaña Informacion Usuario");
	}
	
}


function GuardarIndicaciones(){
	var observacionEspecial = document.getElementById("observacionEspecial").value; 
	var evolucionesClinicas = document.getElementById("evolucionesClinicas").value;
	var CodReporte=document.getElementById("txtCodReporte").value;
	if(CodReporte!=""){
		if (observacionEspecial == "") {
			alert("Escriba las observaciones especiales");
			document.getElementById("observacionEspecial").focus();
		} else {

			if (evolucionesClinicas == "") {
				alert("Escriba la evolucion clinica");
				document.getElementById("evolucionesClinicas").focus();
			} else {

				//var CodReporte=document.getElementById("txtCodReporte").value;
			
				var ajax = getXMLObject();
				ajax.open("POST", "pypData", true);
				ajax.onreadystatechange = function(){
					if(ajax.readyState == 4){
						activarelementoFormulario("guardar");
						alert("Ingreso Exitoso");
					}
				};
				ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');			
				ajax.send("valor=9&observacionEspecial=" + observacionEspecial
						+ "&codpaciente=" + codpacientediagnostico
						+ "&evolucionesClinicas=" + evolucionesClinicas
						+ "&txtCodReporte=" + CodReporte);

			}//Cierra Evolucion Clinica

		}//Cierra Observaciones Especiales
	}else{
		alert("Falta Guardar Datos Personales en la pestaña Informacion Usuario");
	}
}


function GenerarReporte(){
	var CodReporte=document.getElementById("txtCodReporte").value;
	var ajax = getXMLObject();
	ajax.open("POST", "pypData", true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			var valida=ajax.responseText;
			if(valida=="OK"){
				mostrarInformesPYPPlanFam(CodReporte);
			}else{
				alert(valida);
			}
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');			
	ajax.send("valor=VF&CodReporte=" + CodReporte);

}


function mostrarInformesPYPPlanFam(idcodInforme){
	pagina="pyp_reportePyp.jsp?CodInfPYPPlanFam="+idcodInforme;			
   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 	opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85, left=140";
   	window.open(pagina,"Informe_Pyp",opciones);
}



/*function limpiarFormularios(){
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
*/

///////////////////////////////////////////////////////
////////////////////////////////////////////////////////

//funcion de calendario TerminacionEmbarazo
$(function() {
  $( "#datepickerTerminacionEmbarazo" ).datepicker({maxDate: "+0D" });
});

//funcion de calendario UltimaMenstruacion
$(function() {
  $( "#datepickerUltimaMenstruacion" ).datepicker({maxDate: "+0D" });
});

//funcion para colocar un calendario de: a:
$(function() {
  $( "#txt_desde" ).datepicker({
    defaultDate: "+1w",
    changeMonth: true,
    numberOfMonths: 1,
    onClose: function( selectedDate ) {
      $( "#txt_hasta" ).datepicker( "option", "minDate", selectedDate );
    }
  });
  $( "#txt_hasta" ).datepicker({
    defaultDate: "+1w",
    changeMonth: true,
    numberOfMonths: 1,
    onClose: function( selectedDate ) {
      $( "#txt_desde" ).datepicker( "option", "maxDate", selectedDate );
    }
  });
});

//funcion de calendario FechaVisita
$(function() {
  $( "#datepickerFechaVisita" ).datepicker({maxDate: "+0D" });
});


//funcion de calendario FechaUltMenstruacion
$(function() {
  $( "#datepickerFechaUltMenstruacion" ).datepicker({maxDate: "+0D" });
});


//funcion de calendario Pildoras
$(function() {
  $( "#datepickerPildoras" ).datepicker();
});

//funcion de calendario DIU
$(function() {
  $( "#datepickerDIU" ).datepicker();
});

//funcion de calendario Inyectables
$(function() {
  $( "#datepickerInyectables" ).datepicker();
});

//funcion de calendario Otros
$(function() {
  $( "#datepickerOtros" ).datepicker();
});

//funcion de calendario fechaControles
$(function() {
  $( "#datepickerFechaControles" ).datepicker({maxDate: "+0D" });
});


//funcion de calendario en español
$(function($){
  $.datepicker.regional['es'] = {		
      closeText: 'Cerrar',
      prevText: '<Ant',
      nextText: 'Sig>',
      currentText: 'Hoy',
      monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
      monthNamesShort: ['Ene','Feb','Mar','Abr', 'May','Jun','Jul','Ago','Sep', 'Oct','Nov','Dic'],
      dayNames: ['Domingo', 'Lunes', 'Martes', 'Miercoles', 'Jueves', 'Viernes', 'Sabado'],
      dayNamesShort: ['Dom','Lun','Mar','Mie','Juv','Vie','Sab'],
      dayNamesMin: ['Do','Lu','Ma','Mi','Ju','Vi','Sa'],
      weekHeader: 'Sem',
      dateFormat:'yy-mm-dd',
      showAnim: 'clip',
      changeMonth: true,
      changeYear: true,
      showWeek: true,
      firstDay: 1,
      isRTL: false,
      showMonthAfterYear: false,
      yearSuffix: ''
  };
  $.datepicker.setDefaults($.datepicker.regional['es']);
});


//Deshabilitar campos
function disableCheck(field, causer) {
if (causer.checked) {
field.checked = false;
field.disabled = true;
}
else {
field.disabled = false;
}
}

function disableConyugue(field) {
disableCheck(formulario.chk_conyugue, field);
}

function disableSolo() {
field = formulario.chk_solo;

if (formulario.chk_conyugue.checked) {
field.checked = false;
field.disabled = true;
}
else {
field.disabled = false;
}
}

//funcion para activar y desactivar nacidos vivos y muertos
function disableCheck(campo, causer) {
	if (causer.checked) {
	campo.checked = false;
	campo.disabled = true;
	}
	else {
	campo.disabled = false;
	}
	}

	function disableMuertos(campo) {
	disableCheck(formulario1.chk_muertos, campo);
	}

	function disableVivos() {
	campo = formulario1.chk_vivos;

	if (formulario1.chk_muertos.checked) {
	campo.checked = false;
	campo.disabled = true;
	}
	else {
	campo.disabled = false;
	}
	}


//funcion para validar numeros
function validarNro(e) {

var key="";

if(window.event) // IE
{
key = e.keyCode;
}

else if(e.which) // Netscape/Firefox/Opera
{
key = e.which;
}
if (key < 48 || key > 57)
{

if(key == 46 || key == 8 ) // Detectar . (punto) y backspace (retroceso)
  { return true; }
else 
  { return false; }
}
return true;
}


//funcion para validar numeros
function validarNro1(e) {

var key="";

if(window.event) // IE
{
key = e.keyCode;
}

else if(e.which) // Netscape/Firefox/Opera
{
key = e.which;
}
if (key < 48 || key > 57)
{

if(key == 46 || key == 8 ) // Detectar . (punto) y backspace (retroceso)
  { return true; }
else 
  { return false; }
}
return true;
}

//funcion para validar solo letras
function soloLetras(e){
   key = e.keyCode || e.which;
   tecla = String.fromCharCode(key).toLowerCase();
   letras = " áéíóúabcdefghijklmnñopqrstuvwxyz";
   especiales = [8,37,39,46];

   tecla_especial = false;
   for(var i in especiales){
        if(key == especiales[i]){
            tecla_especial = true;
            break;
        }
    }

    if(letras.indexOf(tecla)==-1 && !tecla_especial){
        return false;
    }
}


//funcion para generar el resultado de los riesgos 
function Resultado(){ 
	
	 var i; 
	 for (i=0;i<general.radAborto.length;i++){
	   if (general.radAborto[i].checked)
	          break; 					
	 }
		
	 var i; 
	 for (i=0;i<general.radCesarea.length;i++){
	   if (general.radCesarea[i].checked) 
	          break; 					
	 }
	 
	 var i; 
	 for (i=0;i<general.radMortinato.length;i++){
	   if (general.radMortinato[i].checked) 
	          break; 					
	 }
	 
	 var i; 
	 for (i=0;i<general.radPrematuro.length;i++){
	   if (general.radPrematuro[i].checked) 
	          break; 					
	 }
	
total = eval(general.edadMujer.value) + eval(general.pariedad.value) +
        eval(general.socioEconomico.value) + eval(general.intervaloEmbarazo.value) + 
        eval(general.radAborto[i].value) + eval(general.radCesarea[i].value) +
        eval(general.radMortinato[i].value) + eval(general.radPrematuro[i].value) +
        eval(general.patologiaActual.value);
        //alert(total); 
	
RiesgoAlto = total>=10;

RiesgoMedio = total>=5 && total<=9;

RiesgoBajo = total>=0 && total<=4;

        
if(total>=10){
document.getElementById("riesgoReproductivo").value="Alto";
}

if(total>=5 && total<=9){
document.getElementById("riesgoReproductivo").value="Medio";
}

if(total>=0 && total<=4){
document.getElementById("riesgoReproductivo").value="Bajo";
}

}


//Actualizar
function PYP(){
	ajax=getXMLObject();
	ajax.open("POST","pypActualizarData",true); //getname will be the servlet name
	ajax.onreadystatechange=function() {
		if (ajax.readyState==4) {
			document.getElementById('datos').innerHTML = ajax.responseText;
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=Actualizar"); //Posting txtname to Servlet
}


function AnularInforme() {
	if (confirm("Desea Anular el informe?")) {
		// Respuesta afirmativa...
	    var minavegador = new Browser();
	    if (minavegador.name == 'firefox'){
			limpiarFormularios();
	    }
		
		window.location.reload(true);
		
}
}

function fecha_ingreso(){
	  var time1 = new Date();
	  var anos = time1.getFullYear();
	  var mes = time1.getMonth();
	  mes=mes+1;
	  
	  var dia = time1.getDate();

	  var temp1 =  "" + ((anos < 10) ? "0" : "") + anos;	
	  temp1 += ((mes < 10) ? "-0" : "-") + mes;
	  temp1 +=((dia < 10) ? "-0" : "-") + dia;
	  
	  document.getElementById("datepickerFechaControles").value = temp1;
	 id = setTimeout("fecha_ingreso()",1000);
	  }

function Fecha_Visita(){
	  var time1 = new Date();
	  var anos = time1.getFullYear();
	  var mes = time1.getMonth();
	  mes=mes+1;
	  
	  var dia = time1.getDate();

	  var temp1 =  "" + ((anos < 10) ? "0" : "") + anos;	
	  temp1 += ((mes < 10) ? "-0" : "-") + mes;
	  temp1 +=((dia < 10) ? "-0" : "-") + dia;
	  
	  document.getElementById("datepickerFechaVisita").value = temp1;
	 id = setTimeout("Fecha_Visita()",1000);
	  }

		
			

